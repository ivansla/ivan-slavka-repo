//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2011.09.12 at 09:47:01 AM BRT
//


package ivan.slavka.utils.jaxb.beans.play.card;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}from"/>
 *         &lt;element ref="{}to"/>
 *         &lt;element ref="{}type"/>
 *         &lt;element ref="{mtg:card}card"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"from",
		"to",
		"type",
		"card"
})
@XmlRootElement(name = "card")
public class Card {

	@XmlElement(required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NCName")
	protected String from;
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NCName")
	protected String to;
	@XmlElement(required = true)
	protected String type;
	@XmlElement(namespace = "mtg:card", required = true)
	protected ivan.slavka.utils.jaxb.beans.mtg.card.Card card;

	/**
	 * Gets the value of the from property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 * 
	 */
	public String getFrom() {
		return this.from;
	}

	/**
	 * Sets the value of the from property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 * 
	 */
	public void setFrom(String value) {
		this.from = value;
	}

	/**
	 * Gets the value of the to property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 * 
	 */
	public String getTo() {
		return this.to;
	}

	/**
	 * Sets the value of the to property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 * 
	 */
	public void setTo(String value) {
		this.to = value;
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 * 
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 * 
	 */
	public void setType(String value) {
		this.type = value;
	}

	/**
	 * Gets the value of the card property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link mtg.card.Card }
	 * 
	 */
	public ivan.slavka.utils.jaxb.beans.mtg.card.Card getCard() {
		return this.card;
	}

	/**
	 * Sets the value of the card property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link mtg.card.Card }
	 * 
	 */
	public void setCard(ivan.slavka.utils.jaxb.beans.mtg.card.Card value) {
		this.card = value;
	}

}