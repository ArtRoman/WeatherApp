package ru.yandex.weather.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="code" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root
public class Message {

	@Attribute(required = false)
	protected String code;

	/**
	 * Gets the value of the code property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the value of the code property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCode(String value) {
		this.code = value;
	}

}
