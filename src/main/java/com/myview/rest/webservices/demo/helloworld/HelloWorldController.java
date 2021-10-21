package com.myview.rest.webservices.demo.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;
    //@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello bean");
    }

    @GetMapping(path = "/hello-world-bean/{name}")
    public HelloWorldBean helloWorldBeanName(@PathVariable String name){
        return new HelloWorldBean( String.format("Hello world, %s", name));
    }

    /*@GetMapping(path = "/hello-world-inter")
    public String helloWorldInter(@RequestHeader(name = "Accept-Language", required = false)
                                              Locale locale){
        return messageSource.getMessage("good.morning.message", null,"Default Message", locale);
    }*/

    @GetMapping(path = "/hello-world-inter")
    public String helloWorldInter(){
        return messageSource.getMessage("good.morning.message", null,"Default Message", LocaleContextHolder.getLocale());
    }
}
