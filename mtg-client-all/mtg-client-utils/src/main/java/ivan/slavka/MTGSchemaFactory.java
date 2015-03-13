package ivan.slavka;

import ivan.slavka.utils.enums.MTGSchemaEnum;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

public class MTGSchemaFactory {

	private static final Log logger = LogFactory.getLog(MTGSchemaFactory.class);

	private static MTGSchemaFactory schemaFactory = null;
	private final Map<MTGSchemaEnum, Schema> schemas;

	private MTGSchemaFactory(){
		this.schemas = new HashMap<MTGSchemaEnum, Schema>();
		logger.warn("### WARNING ### - MTGSchemaFactory instantiated without schemas!");
	}

	private MTGSchemaFactory(Properties properties){
		this.schemas = new HashMap<MTGSchemaEnum, Schema>();

		SchemaFactory schemaFactory;
		Schema schema;
		for(MTGSchemaEnum schemaEnum : MTGSchemaEnum.values()){
			schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			try{
				schema = schemaFactory.newSchema(new File(properties.getProperty(schemaEnum.toString())));
				this.schemas.put(schemaEnum, schema);
			} catch(SAXException e){
				logger.error("### ERROR ### - Error while loading XML Schema: " + schemaEnum, e);
			}
		}
	}

	/**
	 * Returns MTGSchemaFactory instance, or creates new blank MTGSchemaFactory.
	 * @return
	 */
	public static MTGSchemaFactory getInstance(){
		if(schemaFactory == null){
			schemaFactory = new MTGSchemaFactory();
		}
		return schemaFactory;
	}

	/**
	 * Returns MTGSchemaFactory instance, or creates new MTGSchemaFactory with given properties.
	 * @param properties
	 * @return
	 */
	public static MTGSchemaFactory getInstance(Properties properties){
		if(schemaFactory == null){
			schemaFactory = new MTGSchemaFactory(properties);
		}
		return schemaFactory;
	}

	public Map<MTGSchemaEnum, Schema> getSchemas() {
		return this.schemas;
	}

	public Schema getSchema(MTGSchemaEnum schemaEnum){
		return this.schemas.get(schemaEnum);
	}
}
