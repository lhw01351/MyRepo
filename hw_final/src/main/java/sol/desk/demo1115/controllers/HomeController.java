package sol.desk.demo1115.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sol.desk.demo1115.models.Inquiry;
import sol.desk.demo1115.services.InquiryService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @RequestMapping(value={"/", "/home"})
    public String index(){
        return "index";
    }
}
