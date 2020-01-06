package com.xzq.commubity.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {
    @RequestMapping("/test")
    public String  test(){
        return "test springboot";
    }
//    ctrl+p 函数参数提示
    @RequestMapping("/test2/{id}")
    public ModelAndView test2(@PathVariable("id") String id){
        ModelAndView mov=new ModelAndView("test");
        mov.addObject("new_id",Integer.parseInt(id)+1);
        return mov;
    }
}
