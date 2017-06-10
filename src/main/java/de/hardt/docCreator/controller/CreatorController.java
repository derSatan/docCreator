package de.hardt.docCreator.controller;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hardt.docCreator.CreatorService;

@RestController
public class CreatorController {

    private static final String template = "Hello, this is a simple REST service creating a pdf file with FOP";
    
    @Autowired
    private CreatorService cs;

    @RequestMapping("/creator/info")
    public String info() {
        return String.format(template);
    }
    
    @RequestMapping("/creator/pdf")
    public String pdf() {
    	String response = "";
    	
    	try {
			response = cs.convertToPDF();
		} catch (FOPException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = "Something went wrong!";
		}
    	
        return response;
    }
}
