package de.hardt.docCreator.service;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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

	public String convertPDFToHTML(String inputFileName, String outputFileName) throws IOException, ParserConfigurationException, TransformerException {
		String response = "";
		// load the PDF file using PDFBox 
		PDDocument pdf = PDDocument.load(new java.io.File(config.getRootPath() + "//input//" + inputFileName)); 
		
		// create the DOM parser 
		PDFDomTree parser = new PDFDomTree(); 
		
		// parse the file and get the DOM Document 
		Document dom = parser.createDOM(pdf);
		
		// Get HTML via javax.transformer
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        trans.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        trans.setOutputProperty(OutputKeys.METHOD, "html");
        StringWriter sw = new StringWriter();
        trans.transform(new DOMSource(dom), new StreamResult(sw));
        String html = sw.toString();
	      
		return response;
	}
}
