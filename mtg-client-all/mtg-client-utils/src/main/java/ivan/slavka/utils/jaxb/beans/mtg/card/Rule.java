//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2011.09.09 at 04:41:45 PM BRT
//


package ivan.slavka.utils.jaxb.beans.mtg.card;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for rule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rule">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="reminder" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rule", propOrder = {
		"value"
})
public class Rule {

	@XmlValue
	protected String value;
	@XmlAttribute(required = true)
	@XmlSchemaType(name = "anySimpleType")
	protected String reminder;

	/**
	 * Gets the value of the value property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 * 
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the value of the reminder property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 * 
	 */
	public String getReminder() {
		return this.reminder;
	}

	/**
	 * Sets the value of the reminder property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 * 
	 */
	public void setReminder(String value) {
		this.reminder = value;
	}

}
