package de.hardt.docCreator.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hardt.docCreator.Config;

@Service
public class CreatorService {
	
	@Autowired
	private Config config;

	public String convertToPDF(String inputFileName, String templateFileName, String outputFileName) throws IOException, FOPException, TransformerException {
		String response = "";
		// the XSL FO file
		File xsltFile = new File(config.getRootPath() + "\\templates\\" + templateFileName + ".xsl");
		
		// the XML file which provides the input
		StreamSource xmlSource = new StreamSource(new File(config.getRootPath() + "\\input\\" + inputFileName + ".xml"));
		
		// create an instance of fop factory
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		
		// a user agent is needed for transformation
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		
		// Setup output
		String fullOutputFileName = config.getRootPath() + "\\output\\";
		if (outputFileName != null) {
			fullOutputFileName =  fullOutputFileName + outputFileName + ".pdf";
		} else {
			fullOutputFileName = fullOutputFileName + inputFileName + ".pdf";
		}
			
		OutputStream out = new FileOutputStream(fullOutputFileName);

		try {
			// Construct fop with desired output format
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

			// Setup XSLT
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

			// Resulting SAX events (the generated FO) must be piped through to
			// FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Start XSLT transformation and FOP processing
			// That's where the XML is first transformed to XSL-FO and then
			// PDF is created
			transformer.transform(xmlSource, res);
		} finally {
			out.close();
			response = "Generated PDF";
		}
		
		return response;
	}
	
    public void convertToFO()  throws IOException, FOPException, TransformerException {
        // the XSL FO file
        File xsltFile = new File("F:\\Temp\\template.xsl");
        
        
        /*TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource("F:\\Temp\\template.xsl"));*/
        
        // the XML file which provides the input
        StreamSource xmlSource = new StreamSource(new File("F:\\Temp\\Employees.xml"));
        
        // a user agent is needed for transformation
        /*FOUserAgent foUserAgent = fopFactory.newFOUserAgent();*/
        // Setup output
        OutputStream out;
        
        out = new java.io.FileOutputStream("F:\\Temp\\temp.fo");
    
        try {
            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            // Resulting SAX events (the generated FO) must be piped through to FOP
            //Result res = new SAXResult(fop.getDefaultHandler());

            Result res = new StreamResult(out);

            //Start XSLT transformation and FOP processing
            transformer.transform(xmlSource, res);


            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then 
            // PDF is created
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }
}
