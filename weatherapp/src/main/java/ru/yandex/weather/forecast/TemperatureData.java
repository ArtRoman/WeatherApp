package ru.yandex.weather.forecast;

import org.simpleframework.xml.Element;
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
 *       &lt;sequence>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}avg"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element ref="{http://weather.yandex.ru/forecast}from"/>
 *           &lt;element ref="{http://weather.yandex.ru/forecast}to"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root(name = "temperature_data")
public class TemperatureData {

	@Element(required = false)
	protected Avg avg;
	@Element(required = false)
	protected String from;
	@Element(required = false)
	protected String to;

	/**
	 * Gets the value of the avg property.
	 *
	 * @return possible object is
	 * {@link Avg }
	 */
	public Avg getAvg() {
		return avg;
	}

	/**
	 * Sets the value of the avg property.
	 *
	 * @param value allowed object is
	 *              {@link Avg }
	 */
	public void setAvg(Avg value) {
		this.avg = value;
	}

	/**
	 * Gets the value of the from property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Sets the value of the from property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setFrom(String value) {
		this.from = value;
	}

	/**
	 * Gets the value of the to property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Sets the value of the to property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setTo(String value) {
		this.to = value;
	}

}
