package sol.desk.demo1115.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sol.desk.demo1115.forms.LoginForm;
import sol.desk.demo1115.models.Customers;
import sol.desk.demo1115.services.CustomerService;

import javax.servlet.http.Cookie;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customer/login")
    public String login (LoginForm loginForm){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if((auth instanceof AnonymousAuthenticationToken)) {
            System.out.println(auth.isAuthenticated());
            System.out.println(auth.getAuthorities());
            System.out.println(auth.getPrincipal());
            System.out.println("인증");
            return "customer/login";
        } else {
            return "redirect:/";
        }
    }

    //
    @RequestMapping("/customer/register")
    public ModelAndView registration() {

        ModelAndView modelAndView = new ModelAndView();
        Customers customers = new Customers();
        modelAndView.addObject(customers);
        modelAndView.setViewName("customer/register");
        return modelAndView;
    }

    @RequestMapping(value = "/customer/register", method = RequestMethod.POST)

    public ModelAndView registration(@Valid Customers customers, BindingResult bindingResult) { // BindingResult 문자열이 왔을때 타입을 맞혀 준다
        ModelAndView modelAndView = new ModelAndView();

        Customers customersExistsId = this.customerService.findByCustomerId(customers.getCustomerId());
        Customers customersExistsEmail = this.customerService.findByCustomerEmail(customers.getCustomerEmail());
        Customers customersExistsPhoneNum = this.customerService.findByPhoneNum(customers.getPhoneNum());

        modelAndView.setViewName("customer/register");

        if( customersExistsId != null ) {
            bindingResult.rejectValue("customerId", "error.customers", "사용중인 ID입니다.");
        }

        if( customersExistsEmail != null ) {
            bindingResult.rejectValue("customerEmail", "error.customers", "사용중인 이메일입니다.");
        }

        if( customersExistsPhoneNum != null ) {
            bindingResult.rejectValue("phoneNum", "error.customers", "사용중인 전화번호입니다.");
        }

        if ( !bindingResult.hasErrors() ) {
            this.customerService.create(customers);
            modelAndView.addObject("successMessage", "사용자가 생성 되었습니다.");
            modelAndView.addObject("customers", new Customers());
        }
        return modelAndView;
    }
}
