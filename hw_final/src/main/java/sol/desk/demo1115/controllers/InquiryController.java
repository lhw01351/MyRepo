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
import sol.desk.demo1115.models.Inquiry;
import sol.desk.demo1115.services.CustomerService;
import sol.desk.demo1115.services.InquiryService;
import sol.desk.demo1115.services.NotifyService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/inquiry/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Inquiry inquiry = this.inquiryService.findById(id);

        if (inquiry == null) {
            notifyService.addErrorMessage(id + "를 찾을 수 없습니다.");
            return "redirect:/inquiry";
        }
        model.addAttribute("inquiry",inquiry);
        return "inquiry/view";
    }

    @RequestMapping("/inquiry/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView();
        Inquiry inquiry = new Inquiry();
        modelAndView.addObject(inquiry);
        modelAndView.setViewName("inquiry/create");
        return modelAndView;
    }

    @RequestMapping(value = "/inquiry/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid Inquiry inquiry, BindingResult bindingResult){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("inquiry/create");

        if( inquiry.getTitle().isEmpty() ){
            bindingResult.rejectValue("title", "error.inquiry", "제목을 입력해주세요.");
        }
        if(inquiry.getContent().isEmpty()){
            bindingResult.rejectValue("content", "error.inquiry", "내용을 입력해주세요.");
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Customers customer = this.customerService.findByCustomerId(auth.getName());


        if( customer==null ){
            bindingResult.rejectValue("customers", "error.inquiry", "작성자를 입력해주세요.");
        }
        if( !bindingResult.hasErrors() ){
            inquiry.setCustomers(customer);

            this.inquiryService.create(inquiry);

            modelAndView.addObject("successMessage", "문의 게시물이 등록되었습니다.");
            modelAndView.addObject("inquiry", new Inquiry());
        }
        return modelAndView;
    }

    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }

    @RequestMapping("/inquiry/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, HttpServletRequest request){
        Inquiry inquiry = this.inquiryService.findById(id);
        if( inquiry==null ){
            notifyService.addErrorMessage("게시물을 찾을 수 없습니다. #" + id);
        } else {
            this.inquiryService.deleteById(id);
        }
        redirectAttributes.addFlashAttribute("deleteMessage","게시물이 삭제되었습니다.");
        return getPreviousPageByRequest(request).orElse("/inquiry");
    }

    @RequestMapping( "/inquiry/edit/{id}" )
    public String edit(@PathVariable("id") Long id, Model model){
        Inquiry inquiry = this.inquiryService.findById(id);
        if( inquiry == null ){
            notifyService.addErrorMessage("게시물을 찾을 수 없습니다. #" + id);
            return "redirect:/inquiry/";
        }
        model.addAttribute("inquiry", inquiry);
        return "inquiry/edit";
    }

    @RequestMapping(value = "/inquiry/edit", method = RequestMethod.POST )
    public ModelAndView edit(@Valid Inquiry inquiry, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("inquiry/edit");

        if( inquiry.getTitle().isEmpty() ){
            bindingResult.rejectValue("title", "error.inquiry", "제목을 입력해주세요.");
        }
        if( inquiry.getContent().isEmpty() ){
            bindingResult.rejectValue("content", "error.inquiry", "내용을 입력해주세요.");
        }

        Customers customer = this.customerService.findById(inquiry.getCustomers().getId());

        if( customer==null ){
            bindingResult.rejectValue("customers", "error.inquiry", "작성자를 입력해주세요.");
        }
        if( !bindingResult.hasErrors() ){
            inquiry.setCustomers(customer);
            this.inquiryService.create(inquiry);
            modelAndView.addObject("successMessage", "게시물이 수정되었습니다.");
            modelAndView.addObject("inquiry", inquiry);
        }
        return modelAndView;
    }

    @RequestMapping("/inquiry")
    public String index(Model model, @PageableDefault(sort = {"id"}, value = 10) Pageable pageable){
        Page<Inquiry> inquiry = this.inquiryService.findAll(pageable);
        model.addAttribute("inquiry", inquiry);
        return "inquiry/index";
    }
}
