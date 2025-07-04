//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.09.11 at 10:10:35 AM CEST 
//


package jaxb.albaran;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Articulos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Articulos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="articulo" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nombreProducto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="cantidad">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *                         &lt;maxExclusive value="100"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element ref="{}comentario" minOccurs="0"/>
 *                   &lt;element name="fechaEnvio" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="codigo" use="required" type="{}PER" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Articulos", propOrder = {
    "articulo"
})
public class Articulos {

    @XmlElement(required = true)
    protected List<Articulos.Articulo> articulo;

    /**
     * Gets the value of the articulo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the articulo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArticulo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Articulos.Articulo }
     * 
     * 
     */
    public List<Articulos.Articulo> getArticulo() {
        if (articulo == null) {
            articulo = new ArrayList<Articulos.Articulo>();
        }
        return this.articulo;
    }


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
     *         &lt;element name="nombreProducto" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="cantidad">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
     *               &lt;maxExclusive value="100"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element ref="{}comentario" minOccurs="0"/>
     *         &lt;element name="fechaEnvio" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="codigo" use="required" type="{}PER" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nombreProducto",
        "cantidad",
        "precio",
        "comentario",
        "fechaEnvio"
    })
    public static class Articulo {

        @XmlElement(required = true)
        protected String nombreProducto;
        protected int cantidad;
        @XmlElement(required = true)
        protected BigDecimal precio;
        protected String comentario;
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar fechaEnvio;
        @XmlAttribute(name = "codigo", required = true)
        protected String codigo;

        /**
         * Gets the value of the nombreProducto property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombreProducto() {
            return nombreProducto;
        }

        /**
         * Sets the value of the nombreProducto property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombreProducto(String value) {
            this.nombreProducto = value;
        }

        /**
         * Gets the value of the cantidad property.
         * 
         */
        public int getCantidad() {
            return cantidad;
        }

        /**
         * Sets the value of the cantidad property.
         * 
         */
        public void setCantidad(int value) {
            this.cantidad = value;
        }

        /**
         * Gets the value of the precio property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getPrecio() {
            return precio;
        }

        /**
         * Sets the value of the precio property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setPrecio(BigDecimal value) {
            this.precio = value;
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
         * Gets the value of the fechaEnvio property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getFechaEnvio() {
            return fechaEnvio;
        }

        /**
         * Sets the value of the fechaEnvio property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setFechaEnvio(XMLGregorianCalendar value) {
            this.fechaEnvio = value;
        }

        /**
         * Gets the value of the codigo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigo() {
            return codigo;
        }

        /**
         * Sets the value of the codigo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigo(String value) {
            this.codigo = value;
        }

    }

}
