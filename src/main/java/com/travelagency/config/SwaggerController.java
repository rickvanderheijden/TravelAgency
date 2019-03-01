package com.travelagency.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {

    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/swagger")
    public String swagger() {
        return "redirect:swagger-ui.html";
    }
}
