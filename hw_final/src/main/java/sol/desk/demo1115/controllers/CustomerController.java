package sol.desk.demo1115.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sol.desk.demo1115.models.Customers;
import sol.desk.demo1115.services.CustomerService;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customer")
    public String index(Model model, @PageableDefault(sort = {"customerId"}, value = 10) Pageable pageable) {
        Page<Customers> customers = this.customerService.findAll(pageable);
        model.addAttribute("customers", customers);
        return "customer/index";
    }
}
