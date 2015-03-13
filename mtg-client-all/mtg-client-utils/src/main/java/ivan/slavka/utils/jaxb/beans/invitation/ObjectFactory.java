//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2011.09.08 at 01:22:16 PM BRT
//


package ivan.slavka.utils.jaxb.beans.invitation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the generated package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _To_QNAME = new QName("", "to");
	private final static QName _Session_QNAME = new QName("", "session");
	private final static QName _Answer_QNAME = new QName("", "answer");
	private final static QName _From_QNAME = new QName("", "from");
	private final static QName _TurnOwner_QNAME = new QName("", "turnOwner");
	private final static QName _Type_QNAME = new QName("", "type");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link Invite }
	 * 
	 */
	public Invite createInvite() {
		return new Invite();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "", name = "to")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	public JAXBElement<String> createTo(String value) {
		return new JAXBElement<String>(_To_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "", name = "session")
	public JAXBElement<String> createSession(String value) {
		return new JAXBElement<String>(_Session_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "", name = "answer")
	public JAXBElement<String> createAnswer(String value) {
		return new JAXBElement<String>(_Answer_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "", name = "from")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	public JAXBElement<String> createFrom(String value) {
		return new JAXBElement<String>(_From_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "", name = "turnOwner")
	public JAXBElement<String> createTurnOwner(String value) {
		return new JAXBElement<String>(_TurnOwner_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "", name = "type")
	public JAXBElement<String> createType(String value) {
		return new JAXBElement<String>(_Type_QNAME, String.class, null, value);
	}

}