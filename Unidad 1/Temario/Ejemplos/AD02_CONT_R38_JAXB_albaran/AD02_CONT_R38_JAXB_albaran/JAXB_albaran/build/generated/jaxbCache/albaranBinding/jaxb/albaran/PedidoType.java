//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.09.11 at 10:10:35 AM CEST 
//


package jaxb.albaran;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for PedidoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PedidoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="facturarA" type="{}Direccion"/>
 *         &lt;element ref="{}comentario" minOccurs="0"/>
 *         &lt;element name="articulos" type="{}Articulos"/>
 *       &lt;/sequence>
 *       &lt;attribute name="fechaPedido" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PedidoType", propOrder = {
    "facturarA",
    "comentario",
    "articulos"
})
public class PedidoType {

    @XmlElement(required = true)
    protected Direccion facturarA;
    protected String comentario;
    @XmlElement(required = true)
    protected Articulos articulos;
    @XmlAttribute(name = "fechaPedido")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaPedido;

    /**
     * Gets the value of the facturarA property.
     * 
     * @return
     *     possible object is
     *     {@link Direccion }
     *     
     */
    public Direccion getFacturarA() {
        return facturarA;
    }

    /**
     * Sets the value of the facturarA property.
     * 
     * @param value
     *     allowed object is
     *     {@link Direccion }
     *     
     */
    public void setFacturarA(Direccion value) {
        this.facturarA = value;
    }

    /**
     * Gets the value of the comentario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Sets the value of the comentario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentario(String value) {
        this.comentario = value;
    }

    /**
     * Gets the value of the articulos property.
     * 
     * @return
     *     possible object is
     *     {@link Articulos }
     *     
     */
    public Articulos getArticulos() {
        return articulos;
    }

    /**
     * Sets the value of the articulos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Articulos }
     *     
     */
    public void setArticulos(Articulos value) {
        this.articulos = value;
    }

    /**
     * Gets the value of the fechaPedido property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaPedido() {
        return fechaPedido;
    }

    /**
     * Sets the value of the fechaPedido property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaPedido(XMLGregorianCalendar value) {
        this.fechaPedido = value;
    }

}
