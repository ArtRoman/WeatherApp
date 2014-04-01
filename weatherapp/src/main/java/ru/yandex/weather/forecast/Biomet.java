package ru.yandex.weather.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}message" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="geomag" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="index" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="uv" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root
public class Biomet {

	@ElementList(required = false, inline = true)
	protected List<Message> message;
	@Attribute(required = false)
	protected String geomag;
	@Attribute(required = false)
	protected String index;
	@Attribute(required = false)
	protected String uv;

	/**
	 * Gets the value of the message property.
	 * <p/>
	 * <p/>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the message property.
	 * <p/>
	 * <p/>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getMessage().add(newItem);
	 * </pre>
	 * <p/>
	 * <p/>
	 * <p/>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Message }
	 */
	public List<Message> getMessage() {
		if (message == null) {
			message = new ArrayList<Message>();
		}
		return this.message;
	}

	/**
	 * Gets the value of the geomag property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getGeomag() {
		return geomag;
	}

	/**
	 * Sets the value of the geomag property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setGeomag(String value) {
		this.geomag = value;
	}

	/**
	 * Gets the value of the index property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * Sets the value of the index property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIndex(String value) {
		this.index = value;
	}

	/**
	 * Gets the value of the uv property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getUv() {
		return uv;
	}

	/**
	 * Sets the value of the uv property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setUv(String value) {
		this.uv = value;
	}

}
