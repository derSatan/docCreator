package de.hardt.docCreator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatorController {

    private static final String template = "Hello, this is a simple REST service creating a pdf file with FOP";

    @RequestMapping("/creator/info")
    public String info() {
        return String.format(template);
    }
}
