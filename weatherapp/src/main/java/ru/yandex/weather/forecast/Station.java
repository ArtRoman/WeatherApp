package ru.yandex.weather.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="distance" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="lang" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root
public class Station {

	@Text(required = false)
	protected String content;
	@Attribute(required = false)
	protected String distance;
	@Attribute(required = false)
	protected String lang;

	/**
	 * Gets the value of the content property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the value of the content property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setContent(String value) {
		this.content = value;
	}

	/**
	 * Gets the value of the distance property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getDistance() {
		return distance;
	}

	/**
	 * Sets the value of the distance property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setDistance(String value) {
		this.distance = value;
	}

	/**
	 * Gets the value of the lang property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * Sets the value of the lang property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setLang(String value) {
		this.lang = value;
	}

}
