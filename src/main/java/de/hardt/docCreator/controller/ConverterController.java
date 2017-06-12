package de.hardt.docCreator.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.hardt.docCreator.service.ConverterService;

@RestController
public class ConverterController {

    private static final String template = "Hello, this is a simple REST service converting files";
    
    private static final Logger LOGGER = LogManager.getLogger();
    
    @Autowired
    private ConverterService cs;

    @RequestMapping("/converter/info")
    public String info() {
        return String.format(template);
    }
    
    @RequestMapping("/converter/pdf2html")
    public String pdf(@RequestParam(name="inputFileName")String inputFileName, 
    		@RequestParam(name="outputFileName", required=false)String outputFileName) {
    	
    	LOGGER.debug("Got call with param inputFileName >" + inputFileName + "<, outputFileName >" + outputFileName + "<");
    	String response = "";
    	
    	try {
			response = cs.convertPDFToHTML(inputFileName, outputFileName);
		} catch (ParserConfigurationException | IOException | TransformerException e) {
			LOGGER.error("Something went wrong - check Log!");
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			response = "Something went wrong!";
		}
    	
        return response;
    }
}
