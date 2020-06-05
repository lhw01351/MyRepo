package sol.desk.demo1115.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sol.desk.demo1115.models.Customers;
import sol.desk.demo1115.models.Notice;
import sol.desk.demo1115.services.CustomerService;
import sol.desk.demo1115.services.NoticeService;
import sol.desk.demo1115.services.NotifyService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class NoticeController {


    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/notice/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Notice notice = this.noticeService.findById(id);
        if (notice == null) {
            notifyService.addErrorMessage(id + "를 찾을 수 없습니다.");
            return "redirect:/notice";
        }
        model.addAttribute("notice",notice);
        return "notice/view";
    }

    @RequestMapping("/notice/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView();
        Notice notice = new Notice();
        modelAndView.addObject(notice);
        modelAndView.setViewName("notice/create");
        return modelAndView;
    }

    @RequestMapping(value = "/notice/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid Notice notice, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("notice/create");

        if( notice.getTitle().isEmpty() ){ //if 문으로 에러 체크
            bindingResult.rejectValue("title", "error.notice", "제목을 입력해주세요.");
        }

        if(notice.getContent().isEmpty()){
            bindingResult.rejectValue("content", "error.notice", "내용을 입력해주세요.");
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customers customer = this.customerService.findByCustomerId(auth.getName());

        if( customer==null ){
            bindingResult.rejectValue("customers", "error.notice", "작성자를 입력해주세요.");
        }
        if( !bindingResult.hasErrors() ){
            notice.setCustomers(customer);
            this.noticeService.create(notice);
            modelAndView.addObject("successMessage", "공지 게시물이 등록되었습니다.");
            modelAndView.addObject("notice", new Notice());
        }
        return modelAndView;
    }

    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }

    @RequestMapping("/notice/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, HttpServletRequest request){
        Notice notice = this.noticeService.findById(id);
        if( notice==null ){
            notifyService.addErrorMessage("게시물을 찾을 수 없습니다. #" + id);
        } else {
            this.noticeService.deleteById(id);
        }
        redirectAttributes.addFlashAttribute("deleteMessage","게시물이 삭제되었습니다.");
        return getPreviousPageByRequest(request).orElse("/notice");
    }

    @RequestMapping( "/notice/edit/{id}" )
    public String edit(@PathVariable("id") Long id, Model model){
        Notice notice = this.noticeService.findById(id);
        if( notice == null ){
            notifyService.addErrorMessage("게시물을 찾을 수 없습니다. #" + id);
            return "redirect:/notice/";
        }
        model.addAttribute("notice", notice);
        return "notice/edit";
    }

    @RequestMapping(value = "/notice/edit", method = RequestMethod.POST )
    public ModelAndView edit(@Valid Notice notice, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("notice/edit");

        if( notice.getTitle().isEmpty() ){
            bindingResult.rejectValue("title", "error.notice", "제목을 입력해주세요.");
        }
        if( notice.getContent().isEmpty() ){
            bindingResult.rejectValue("content", "error.notice", "내용을 입력해주세요.");
        }

        Customers customer = this.customerService.findById(notice.getCustomers().getId());

        if( customer==null ){
            bindingResult.rejectValue("customers", "error.notice", "작성자를 입력해주세요.");
        }
        if( !bindingResult.hasErrors() ){
            notice.setCustomers(customer);
            this.noticeService.create(notice);
            modelAndView.addObject("successMessage", "게시물이 수정되었습니다.");
            modelAndView.addObject("notice", notice);
        }
        return modelAndView;
    }

    @RequestMapping("/notice")
    public String index(Model model, @PageableDefault(sort = {"id"}, value = 10) Pageable pageable){
        Page<Notice> notice = this.noticeService.findAll(pageable);
        model.addAttribute("notice", notice);
        return "notice/index";
    }


}


