package ru.yandex.weather.forecast;

import org.simpleframework.xml.Attribute;
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
 *         &lt;element ref="{http://weather.yandex.ru/forecast}temperature"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_condition"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}image"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}image-v2"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}image-v3"/>
 *       &lt;/sequence>
 *       &lt;attribute name="at" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root
public class Hour {

	@Element(required = false)
	protected Temperature temperature;
	@Element(name = "weather-condition", required = false)
	protected WeatherCondition weatherCondition;
	@Element(required = false)
	protected Image image;
	@Element(name = "image-v2", required = false)
	protected ImageV2 imageV2;
	@Element(name = "image-v3", required = false)
	protected ImageV3 imageV3;
	@Attribute(required = false)
	protected String at;

	/**
	 * Gets the value of the temperature property.
	 *
	 * @return possible object is
	 * {@link Temperature }
	 */
	public Temperature getTemperature() {
		return temperature;
	}

	/**
	 * Sets the value of the temperature property.
	 *
	 * @param value allowed object is
	 *              {@link Temperature }
	 */
	public void setTemperature(Temperature value) {
		this.temperature = value;
	}

	/**
	 * Gets the value of the weatherCondition property.
	 *
	 * @return possible object is
	 * {@link WeatherCondition }
	 */
	public WeatherCondition getWeatherCondition() {
		return weatherCondition;
	}

	/**
	 * Sets the value of the weatherCondition property.
	 *
	 * @param value allowed object is
	 *              {@link WeatherCondition }
	 */
	public void setWeatherCondition(WeatherCondition value) {
		this.weatherCondition = value;
	}

	/**
	 * Gets the value of the image property.
	 *
	 * @return possible object is
	 * {@link Image }
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Sets the value of the image property.
	 *
	 * @param value allowed object is
	 *              {@link Image }
	 */
	public void setImage(Image value) {
		this.image = value;
	}

	/**
	 * Gets the value of the imageV2 property.
	 *
	 * @return possible object is
	 * {@link ImageV2 }
	 */
	public ImageV2 getImageV2() {
		return imageV2;
	}

	/**
	 * Sets the value of the imageV2 property.
	 *
	 * @param value allowed object is
	 *              {@link ImageV2 }
	 */
	public void setImageV2(ImageV2 value) {
		this.imageV2 = value;
	}

	/**
	 * Gets the value of the imageV3 property.
	 *
	 * @return possible object is
	 * {@link ImageV3 }
	 */
	public ImageV3 getImageV3() {
		return imageV3;
	}

	/**
	 * Sets the value of the imageV3 property.
	 *
	 * @param value allowed object is
	 *              {@link ImageV3 }
	 */
	public void setImageV3(ImageV3 value) {
		this.imageV3 = value;
	}

	/**
	 * Gets the value of the at property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getAt() {
		return at;
	}

	/**
	 * Sets the value of the at property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setAt(String value) {
		this.at = value;
	}

}
