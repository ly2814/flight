package com.isoft.routers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InsertRouter {
    @GetMapping("/Insert")
    public ModelAndView toInsert(ModelAndView view){
        view.setViewName("Insert");
        return view;
    }
    @GetMapping("/Edit")
    public ModelAndView toEdit(ModelAndView view){
        view.setViewName("Edit");
        return view;
    }
}
