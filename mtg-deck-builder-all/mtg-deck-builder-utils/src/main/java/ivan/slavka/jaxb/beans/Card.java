//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.08.10 at 12:32:28 PM BRT 
//


package ivan.slavka.jaxb.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for card complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="card">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}name"/>
 *         &lt;element ref="{}cost"/>
 *         &lt;element ref="{}loyalty"/>
 *         &lt;element ref="{}typelist"/>
 *         &lt;element ref="{}pow"/>
 *         &lt;element ref="{}tgh"/>
 *         &lt;element ref="{}hand"/>
 *         &lt;element ref="{}life"/>
 *         &lt;element ref="{}rulelist"/>
 *         &lt;element ref="{}multi"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "card", propOrder = {
    "name",
    "cost",
    "loyalty",
    "typelist",
    "pow",
    "tgh",
    "hand",
    "life",
    "rulelist",
    "multi"
})
public class Card {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String cost;
    @XmlElement(required = true)
    protected String loyalty;
    @XmlElement(required = true)
    protected Typelist typelist;
    @XmlElement(required = true)
    protected String pow;
    @XmlElement(required = true)
    protected String tgh;
    @XmlElement(required = true)
    protected String hand;
    @XmlElement(required = true)
    protected String life;
    @XmlElement(required = true)
    protected Rulelist rulelist;
    @XmlElement(required = true)
    protected Multi multi;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the cost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCost() {
        return cost;
    }

    /**
     * Sets the value of the cost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCost(String value) {
        this.cost = value;
    }

    /**
     * Gets the value of the loyalty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoyalty() {
        return loyalty;
    }

    /**
     * Sets the value of the loyalty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoyalty(String value) {
        this.loyalty = value;
    }

    /**
     * Gets the value of the typelist property.
     * 
     * @return
     *     possible object is
     *     {@link Typelist }
     *     
     */
    public Typelist getTypelist() {
        return typelist;
    }

    /**
     * Sets the value of the typelist property.
     * 
     * @param value
     *     allowed object is
     *     {@link Typelist }
     *     
     */
    public void setTypelist(Typelist value) {
        this.typelist = value;
    }

    /**
     * Gets the value of the pow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPow() {
        return pow;
    }

    /**
     * Sets the value of the pow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPow(String value) {
        this.pow = value;
    }

    /**
     * Gets the value of the tgh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTgh() {
        return tgh;
    }

    /**
     * Sets the value of the tgh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTgh(String value) {
        this.tgh = value;
    }

    /**
     * Gets the value of the hand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHand() {
        return hand;
    }

    /**
     * Sets the value of the hand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHand(String value) {
        this.hand = value;
    }

    /**
     * Gets the value of the life property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLife() {
        return life;
    }

    /**
     * Sets the value of the life property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLife(String value) {
        this.life = value;
    }

    /**
     * Gets the value of the rulelist property.
     * 
     * @return
     *     possible object is
     *     {@link Rulelist }
     *     
     */
    public Rulelist getRulelist() {
        return rulelist;
    }

    /**
     * Sets the value of the rulelist property.
     * 
     * @param value
     *     allowed object is
     *     {@link Rulelist }
     *     
     */
    public void setRulelist(Rulelist value) {
        this.rulelist = value;
    }

    /**
     * Gets the value of the multi property.
     * 
     * @return
     *     possible object is
     *     {@link Multi }
     *     
     */
    public Multi getMulti() {
        return multi;
    }

    /**
     * Sets the value of the multi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Multi }
     *     
     */
    public void setMulti(Multi value) {
        this.multi = value;
    }

}
