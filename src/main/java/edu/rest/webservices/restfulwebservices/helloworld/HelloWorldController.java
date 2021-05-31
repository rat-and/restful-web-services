package edu.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

//    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World you dumb ass";
    }

//    New school way, automatically checks Accept-Language and responses with "good.morning.message" from messages_xx.properties
    @GetMapping(path = "/hello-world-i18n")
    public String helloWorldInternationalized() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

//    Old school way
//    @GetMapping(path = "/hello-world-i18n")
//    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
//        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
//    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World from Bean you dumb ass");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World from Bean you %s", name));
    }
}
