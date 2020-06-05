package sol.desk.demo1115.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sol.desk.demo1115.models.Bicycle;
import sol.desk.demo1115.repositories.BicycleRepository;
import sol.desk.demo1115.services.BicycleService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
public class BicycleController {

    @Autowired
    private BicycleRepository bicycleRepository;

    public BicycleController(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @Autowired
    private BicycleService bicycleService;

    @RequestMapping(value = "/bicycles/map",method = RequestMethod.GET)
    public String viewBicyclesOnTheMap(Model model){
        model.addAttribute("bicycles",bicycleRepository.findAll());
        return "bicycles/bicyclemap";
    }

    @GetMapping("/bicycles/list")
    public String bicyclePaging(HttpServletRequest request, Model model){
        int page = 0;
        int size = 10;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("bicycles", bicycleRepository.findAll(PageRequest.of(page, size)));
        return "bicycles/bicyclelist";
    }

    @RequestMapping("/bicycles/add")
    public ModelAndView addBicycle(Model model){
        model.addAttribute("bicycleList",bicycleRepository.findAll());
        ModelAndView modelAndView = new ModelAndView();
        Bicycle bicycle = new Bicycle();
        modelAndView.addObject(bicycle);
        modelAndView.setViewName("bicycles/addbicycle");
        return modelAndView;
    }

    @ModelAttribute
    @RequestMapping(value = "/bicycles/add", method = {RequestMethod.POST})
    public ModelAndView create(@Valid Bicycle bicycle,
                               Model model, RedirectAttributes redirectAttributes) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(bicycle);
        modelAndView.setViewName("redirect:/bicycles/add");
        bicycleRepository.save(bicycle);
        model.addAttribute("bicycleList",bicycleRepository.findAll());
        redirectAttributes.addFlashAttribute("successMessage","자전거가 성공적으로 등록되었습니다.");

        return modelAndView;
    }

    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }

    @RequestMapping(value="bicycle/delete/{bicycleId}", method = RequestMethod.GET)
    public String delete(@PathVariable("bicycleId") int bicycleId, HttpServletRequest request,
                         RedirectAttributes redirectAttributes) throws IOException {
        this.bicycleService.deleteByBicycleId(bicycleId);
        redirectAttributes.addFlashAttribute("deleteMessage","자전거가 삭제되었습니다.");
        return getPreviousPageByRequest(request).orElse("/bicycles/list");
    }

}
