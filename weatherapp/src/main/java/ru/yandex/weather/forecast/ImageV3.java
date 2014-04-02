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
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root(name = "image-v3")
public class ImageV3 {

	@Text(required = false)
	protected String content;
	@Attribute(required = false)
	protected String type;

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
	 * Gets the value of the type property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setType(String value) {
		this.type = value;
	}

}
