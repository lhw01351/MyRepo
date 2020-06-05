package sol.desk.demo1115.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sol.desk.demo1115.models.Bicycle;
import sol.desk.demo1115.models.Customers;
import sol.desk.demo1115.models.Rent;
import sol.desk.demo1115.repositories.BicycleRepository;
import sol.desk.demo1115.repositories.CustomerRepository;
import sol.desk.demo1115.repositories.RentRepository;
import sol.desk.demo1115.services.RentService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class RentController {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private BicycleRepository bicycleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RentService rentService;

    @RequestMapping("/bicycles/rent")
    public ModelAndView letsRent(Model model){
        model.addAttribute("rent",rentRepository.findAll());
        model.addAttribute("bicycle",bicycleRepository.findAll());
        model.addAttribute("customer",customerRepository.findAll());
        ModelAndView modelAndView = new ModelAndView();
        Rent rent = new Rent();
        modelAndView.addObject(rent);
        modelAndView.setViewName("bicycles/rentbicycle");
        return modelAndView;
    }

    @RequestMapping(value="/bicycles/rent", method = RequestMethod.POST)
    public ModelAndView startRent(@Valid Rent rent, Model model) throws IOException {
        model.addAttribute("rent",rentRepository.findAll());
        model.addAttribute("bicycle",bicycleRepository.findAll());
        model.addAttribute("customer",customerRepository.findAll());
        ModelAndView modelAndView = new ModelAndView();
        Bicycle bicycle = new Bicycle();
        Customers customer = new Customers();
        modelAndView.addObject(rent);
        modelAndView.addObject(bicycle);
        modelAndView.addObject(customer);
        rentRepository.save(rent);
        modelAndView.setViewName("redirect:/bicycles/rent/"+rent.getBicycleRentId()); //대여 직후 바로 대여 현황 URL로 redirection.
        return modelAndView;
    }

    @GetMapping("/bicycles/rent/{bicycleRentId}")
    public ModelAndView eachRent(@PathVariable(name="bicycleRentId") Long bicycleRentId, Model model, ModelAndView modelAndView){
        model.addAttribute("rent",rentService.findByBicycleRentId(bicycleRentId));
        modelAndView.setViewName("bicycles/usingabicycle");
        return modelAndView;
    }

    @RequestMapping(value = "/bicycles/rent/{bicycleRentId}", method = RequestMethod.POST)
    public ModelAndView returnBicycle(@PathVariable(name="bicycleRentId") Long bicycleRentId, Rent rent, ModelAndView modelAndView) {
        rent.setBicycleRentId(bicycleRentId);
        modelAndView.setViewName("redirect:/bicycles/map");
        this.rentService.returnCycle(rent);
        return modelAndView;
    }

}
