package de.hardt.docCreator.service;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import de.hardt.docCreator.Config;

@Service
public class ConverterService {
	
	@Autowired
	private Config config;

	public String convertPDFToHTML() throws IOException, ParserConfigurationException {
		String response = "";
		// load the PDF file using PDFBox 
		PDDocument pdf = PDDocument.load(new java.io.File("file.pdf")); 
		
		// create the DOM parser 
		PDFDomTree parser = new PDFDomTree(); 
		
		// parse the file and get the DOM Document 
		Document dom = parser.createDOM(pdf);
		
		return response;
	}
}
