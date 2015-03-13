package ivan.slavka.writers;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.xml.transform.Result;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TemplatesHandler;
import javax.xml.transform.sax.TransformerHandler;

import org.apache.xml.serializer.OutputPropertiesFactory;
import org.apache.xml.serializer.Serializer;
import org.apache.xml.serializer.SerializerFactory;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class DeckWriter {

	Properties serializerProperties = new Properties();

	public DeckWriter(){

		try{
			// Instantiate a TransformerFactory.
			TransformerFactory tFactory = TransformerFactory.newInstance();
			// Verify that the TransformerFactory implementation you are using
			// supports SAX input and output (Xalan-Java does!).
			if (tFactory.getFeature(javax.xml.transform.sax.SAXSource.FEATURE) &&
					tFactory.getFeature(javax.xml.transform.sax.SAXResult.FEATURE))
			{
				// Cast the TransformerFactory to SAXTransformerFactory.
				SAXTransformerFactory saxTFactory = (SAXTransformerFactory) tFactory;
				// Create a Templates ContentHandler to handle parsing of the
				// stylesheet.
				TemplatesHandler templatesHandler = saxTFactory.newTemplatesHandler();

				// Create an XMLReader and set its ContentHandler.
				XMLReader reader = XMLReaderFactory.createXMLReader();
				reader.setContentHandler(templatesHandler);

				// Parse the stylesheet.
				reader.parse("foo.xsl");

				// Get the Templates object (generated during the parsing of the stylesheet)
				// from the TemplatesHandler.
				Templates templates = templatesHandler.getTemplates();
				// Create a Transformer ContentHandler to handle parsing of
				// the XML Source.
				TransformerHandler transformerHandler = saxTFactory.newTransformerHandler(templates);
				// Reset the XMLReader's ContentHandler to the TransformerHandler.
				reader.setContentHandler(transformerHandler);

				// Set the ContentHandler to also function as a LexicalHandler, which
				// can process "lexical" events (such as comments and CDATA).
				reader.setProperty("http://xml.org/sax/properties/lexical-handler",
						transformerHandler);

				// Set up a Serializer to serialize the Result to a file.
				Serializer serializer = SerializerFactory.getSerializer(OutputPropertiesFactory.getDefaultMethodProperties("xml"));
				serializer.setOutputStream(new FileOutputStream("foo.out"));
				// The Serializer functions as a SAX ContentHandler.
				Result result = new SAXResult(serializer.asContentHandler());
				transformerHandler.setResult(result);

				// Parse the XML input document.
				reader.parse("foo.xml");
			}
		}catch (Exception e){}

	}
}
