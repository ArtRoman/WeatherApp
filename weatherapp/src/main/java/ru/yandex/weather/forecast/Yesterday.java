package ru.yandex.weather.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
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
 *         &lt;element ref="{http://weather.yandex.ru/forecast}station" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}observation_time"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}uptime"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}temperature"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_condition"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}image"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}image-v2"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}image-v3"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_type"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_type_short"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_type_tt"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_type_short_tt"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_type_tr"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_type_short_tr"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_type_kz"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_type_short_kz"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_type_ua"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_type_short_ua"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_type_by"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}weather_type_short_by"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}wind_direction"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}wind_speed"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}humidity"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}pressure"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}mslp_pressure"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}water_temperature"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}season"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}ipad_image"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root
public class Yesterday {

	@ElementList(inline = true, required = false)
	protected List<Station> station;
	@Element(name = "observation_time", required = false)
	protected String observationTime;
	@Element(required = false)
	protected String uptime;
	@Element(required = false)
	protected Temperature temperature;
	@Element(name = "weather_condition", required = false)
	protected WeatherCondition weatherCondition;
	@Element(required = false)
	protected Image image;
	@Element(name = "image-v2", required = false)
	protected ImageV2 imageV2;
	@Element(name = "image-v3", required = false)
	protected ImageV3 imageV3;
	@Element(name = "weather_type", required = false)
	protected String weatherType;
	@Element(name = "weather_type_short", required = false)
	protected String weatherTypeShort;
	@Element(name = "weather_type_tt", required = false)
	protected String weatherTypeTt;
	@Element(name = "weather_type_short_tt", required = false)
	protected String weatherTypeShortTt;
	@Element(name = "weather_type_tr", required = false)
	protected String weatherTypeTr;
	@Element(name = "weather_type_short_tr", required = false)
	protected String weatherTypeShortTr;
	@Element(name = "weather_type_kz", required = false)
	protected String weatherTypeKz;
	@Element(name = "weather_type_short_kz", required = false)
	protected String weatherTypeShortKz;
	@Element(name = "weather_type_ua", required = false)
	protected String weatherTypeUa;
	@Element(name = "weather_type_short_ua", required = false)
	protected String weatherTypeShortUa;
	@Element(name = "weather_type_by", required = false)
	protected String weatherTypeBy;
	@Element(name = "weather_type_short_by", required = false)
	protected String weatherTypeShortBy;
	@Element(name = "wind_direction", required = false)
	protected String windDirection;
	@Element(name = "wind_speed", required = false)
	protected String windSpeed;
	@Element(required = false)
	protected String humidity;
	@Element(required = false)
	protected Pressure pressure;
	@Element(name = "mslp_pressure", required = false)
	protected MslpPressure mslpPressure;
	@Element(name = "water_temperature", required = false)
	protected String waterTemperature;
	@Element(required = false)
	protected Season season;
	@Element(name = "ipad_image", required = false)
	protected String ipadImage;
	@Attribute(required = false)
	protected String id;

	/**
	 * Gets the value of the station property.
	 * <p/>
	 * <p/>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the station property.
	 * <p/>
	 * <p/>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getStation().add(newItem);
	 * </pre>
	 * <p/>
	 * <p/>
	 * <p/>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Station }
	 */
	public List<Station> getStation() {
		if (station == null) {
			station = new ArrayList<Station>();
		}
		return this.station;
	}

	/**
	 * Gets the value of the observationTime property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getObservationTime() {
		return observationTime;
	}

	/**
	 * Sets the value of the observationTime property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setObservationTime(String value) {
		this.observationTime = value;
	}

	/**
	 * Gets the value of the uptime property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getUptime() {
		return uptime;
	}

	/**
	 * Sets the value of the uptime property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setUptime(String value) {
		this.uptime = value;
	}

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
	 * Gets the value of the weatherType property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWeatherType() {
		return weatherType;
	}

	/**
	 * Sets the value of the weatherType property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWeatherType(String value) {
		this.weatherType = value;
	}

	/**
	 * Gets the value of the weatherTypeShort property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWeatherTypeShort() {
		return weatherTypeShort;
	}

	/**
	 * Sets the value of the weatherTypeShort property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWeatherTypeShort(String value) {
		this.weatherTypeShort = value;
	}

	/**
	 * Gets the value of the weatherTypeTt property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWeatherTypeTt() {
		return weatherTypeTt;
	}

	/**
	 * Sets the value of the weatherTypeTt property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWeatherTypeTt(String value) {
		this.weatherTypeTt = value;
	}

	/**
	 * Gets the value of the weatherTypeShortTt property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWeatherTypeShortTt() {
		return weatherTypeShortTt;
	}

	/**
	 * Sets the value of the weatherTypeShortTt property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWeatherTypeShortTt(String value) {
		this.weatherTypeShortTt = value;
	}

	/**
	 * Gets the value of the weatherTypeTr property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWeatherTypeTr() {
		return weatherTypeTr;
	}

	/**
	 * Sets the value of the weatherTypeTr property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWeatherTypeTr(String value) {
		this.weatherTypeTr = value;
	}

	/**
	 * Gets the value of the weatherTypeShortTr property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWeatherTypeShortTr() {
		return weatherTypeShortTr;
	}

	/**
	 * Sets the value of the weatherTypeShortTr property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWeatherTypeShortTr(String value) {
		this.weatherTypeShortTr = value;
	}

	/**
	 * Gets the value of the weatherTypeKz property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWeatherTypeKz() {
		return weatherTypeKz;
	}

	/**
	 * Sets the value of the weatherTypeKz property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWeatherTypeKz(String value) {
		this.weatherTypeKz = value;
	}

	/**
	 * Gets the value of the weatherTypeShortKz property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWeatherTypeShortKz() {
		return weatherTypeShortKz;
	}

	/**
	 * Sets the value of the weatherTypeShortKz property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWeatherTypeShortKz(String value) {
		this.weatherTypeShortKz = value;
	}

	/**
	 * Gets the value of the weatherTypeUa property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWeatherTypeUa() {
		return weatherTypeUa;
	}

	/**
	 * Sets the value of the weatherTypeUa property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWeatherTypeUa(String value) {
		this.weatherTypeUa = value;
	}

	/**
	 * Gets the value of the weatherTypeShortUa property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWeatherTypeShortUa() {
		return weatherTypeShortUa;
	}

	/**
	 * Sets the value of the weatherTypeShortUa property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWeatherTypeShortUa(String value) {
		this.weatherTypeShortUa = value;
	}

	/**
	 * Gets the value of the weatherTypeBy property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWeatherTypeBy() {
		return weatherTypeBy;
	}

	/**
	 * Sets the value of the weatherTypeBy property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWeatherTypeBy(String value) {
		this.weatherTypeBy = value;
	}

	/**
	 * Gets the value of the weatherTypeShortBy property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWeatherTypeShortBy() {
		return weatherTypeShortBy;
	}

	/**
	 * Sets the value of the weatherTypeShortBy property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWeatherTypeShortBy(String value) {
		this.weatherTypeShortBy = value;
	}

	/**
	 * Gets the value of the windDirection property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWindDirection() {
		return windDirection;
	}

	/**
	 * Sets the value of the windDirection property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWindDirection(String value) {
		this.windDirection = value;
	}

	/**
	 * Gets the value of the windSpeed property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWindSpeed() {
		return windSpeed;
	}

	/**
	 * Sets the value of the windSpeed property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWindSpeed(String value) {
		this.windSpeed = value;
	}

	/**
	 * Gets the value of the humidity property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getHumidity() {
		return humidity;
	}

	/**
	 * Sets the value of the humidity property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setHumidity(String value) {
		this.humidity = value;
	}

	/**
	 * Gets the value of the pressure property.
	 *
	 * @return possible object is
	 * {@link Pressure }
	 */
	public Pressure getPressure() {
		return pressure;
	}

	/**
	 * Sets the value of the pressure property.
	 *
	 * @param value allowed object is
	 *              {@link Pressure }
	 */
	public void setPressure(Pressure value) {
		this.pressure = value;
	}

	/**
	 * Gets the value of the mslpPressure property.
	 *
	 * @return possible object is
	 * {@link MslpPressure }
	 */
	public MslpPressure getMslpPressure() {
		return mslpPressure;
	}

	/**
	 * Sets the value of the mslpPressure property.
	 *
	 * @param value allowed object is
	 *              {@link MslpPressure }
	 */
	public void setMslpPressure(MslpPressure value) {
		this.mslpPressure = value;
	}

	/**
	 * Gets the value of the waterTemperature property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getWaterTemperature() {
		return waterTemperature;
	}

	/**
	 * Sets the value of the waterTemperature property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setWaterTemperature(String value) {
		this.waterTemperature = value;
	}

	/**
	 * Gets the value of the season property.
	 *
	 * @return possible object is
	 * {@link Season }
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * Sets the value of the season property.
	 *
	 * @param value allowed object is
	 *              {@link Season }
	 */
	public void setSeason(Season value) {
		this.season = value;
	}

	/**
	 * Gets the value of the ipadImage property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIpadImage() {
		return ipadImage;
	}

	/**
	 * Sets the value of the ipadImage property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIpadImage(String value) {
		this.ipadImage = value;
	}

	/**
	 * Gets the value of the id property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setId(String value) {
		this.id = value;
	}

}
