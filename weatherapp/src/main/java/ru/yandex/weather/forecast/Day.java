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
 *         &lt;element ref="{http://weather.yandex.ru/forecast}sunrise"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}sunset"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}moon_phase"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}moonrise"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}moonset"/>
 *         &lt;element ref="{http://weather.yandex.ru/forecast}dayPart" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="date" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root
public class Day {

	@Element(required = false)
	protected String sunrise;
	@Element(required = false)
	protected String sunset;
	@Element(name = "moon_phase", required = false)
	protected MoonPhase moonPhase;
	@Element(required = false)
	protected String moonrise;
	@Element(required = false)
	protected String moonset;
	@Element(required = false)
	protected Biomet biomet;
	@ElementList(name = "day_part", required = false, inline = true)
	protected List<DayPart> dayPart;
	@ElementList(required = false, inline = true)
	protected List<Hour> hour;
	@Attribute(required = false)
	protected String date;

	/**
	 * Gets the value of the sunrise property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getSunrise() {
		return sunrise;
	}

	/**
	 * Sets the value of the sunrise property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setSunrise(String value) {
		this.sunrise = value;
	}

	/**
	 * Gets the value of the sunset property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getSunset() {
		return sunset;
	}

	/**
	 * Sets the value of the sunset property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setSunset(String value) {
		this.sunset = value;
	}

	/**
	 * Gets the value of the moonPhase property.
	 *
	 * @return possible object is
	 * {@link MoonPhase }
	 */
	public MoonPhase getMoonPhase() {
		return moonPhase;
	}

	/**
	 * Sets the value of the moonPhase property.
	 *
	 * @param value allowed object is
	 *              {@link MoonPhase }
	 */
	public void setMoonPhase(MoonPhase value) {
		this.moonPhase = value;
	}

	/**
	 * Gets the value of the moonrise property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getMoonrise() {
		return moonrise;
	}

	/**
	 * Sets the value of the moonrise property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setMoonrise(String value) {
		this.moonrise = value;
	}

	/**
	 * Gets the value of the moonset property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getMoonset() {
		return moonset;
	}

	/**
	 * Sets the value of the moonset property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setMoonset(String value) {
		this.moonset = value;
	}

	/**
	 * Gets the value of the biomet property.
	 *
	 * @return possible object is
	 * {@link Biomet }
	 */
	public Biomet getBiomet() {
		return biomet;
	}

	/**
	 * Sets the value of the biomet property.
	 *
	 * @param value allowed object is
	 *              {@link Biomet }
	 */
	public void setBiomet(Biomet value) {
		this.biomet = value;
	}

	/**
	 * Gets the value of the dayPart property.
	 * <p/>
	 * <p/>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the dayPart property.
	 * <p/>
	 * <p/>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getDayPart().add(newItem);
	 * </pre>
	 * <p/>
	 * <p/>
	 * <p/>
	 * Objects of the following type(s) are allowed in the list
	 * {@link DayPart }
	 */
	public List<DayPart> getDayPart() {
		if (dayPart == null) {
			dayPart = new ArrayList<DayPart>();
		}
		return this.dayPart;
	}

	/**
	 * Gets the value of the hour property.
	 * <p/>
	 * <p/>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the hour property.
	 * <p/>
	 * <p/>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getHour().add(newItem);
	 * </pre>
	 * <p/>
	 * <p/>
	 * <p/>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Hour }
	 */
	public List<Hour> getHour() {
		if (hour == null) {
			hour = new ArrayList<Hour>();
		}
		return this.hour;
	}

	/**
	 * Gets the value of the date property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the value of the date property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setDate(String value) {
		this.date = value;
	}

}
