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
 *         &lt;element ref="{http://weather.yandex.ru/forecast}fact"/>
 *       &lt;/sequence>
 *       &lt;attribute name="city" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="climate" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="country" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="country_id" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="exactname" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="geoid" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="lat" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="link" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="lon" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="part" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="part_id" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="region" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="slug" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="source" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="zoom" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root
public class Forecast {

	@Element(required = false)
	protected Fact fact;
	@Element(required = false)
	protected Yesterday yesterday;
	@Element(required = false)
	protected Informer informer;
	@ElementList(required = false, inline = true)
	protected List<Day> day;
	@Attribute(required = false)
	protected String city;
	@Attribute(required = false)
	protected String climate;
	@Attribute(required = false)
	protected String country;
	@Attribute(name = "country_id", required = false)
	protected String countryId;
	@Attribute(required = false)
	protected String exactname;
	@Attribute(required = false)
	protected String geoid;
	@Attribute(required = false)
	protected String id;
	@Attribute(required = false)
	protected String lat;
	@Attribute(required = false)
	protected String link;
	@Attribute(required = false)
	protected String lon;
	@Attribute(required = false)
	protected String part;
	@Attribute(name = "part_id", required = false)
	protected String partId;
	@Attribute(required = false)
	protected String region;
	@Attribute(required = false)
	protected String slug;
	@Attribute(required = false)
	protected String source;
	@Attribute(required = false)
	protected String zoom;

	/**
	 * Gets the value of the fact property.
	 *
	 * @return possible object is
	 * {@link Fact }
	 */
	public Fact getFact() {
		return fact;
	}

	/**
	 * Sets the value of the fact property.
	 *
	 * @param value allowed object is
	 *              {@link Fact }
	 */
	public void setFact(Fact value) {
		this.fact = value;
	}

	/**
	 * Gets the value of the yesterday property.
	 *
	 * @return possible object is
	 * {@link Yesterday }
	 */
	public Yesterday getYesterday() {
		return yesterday;
	}

	/**
	 * Sets the value of the yesterday property.
	 *
	 * @param value allowed object is
	 *              {@link Yesterday }
	 */
	public void setYesterday(Yesterday value) {
		this.yesterday = value;
	}

	/**
	 * Gets the value of the informer property.
	 *
	 * @return possible object is
	 * {@link Informer }
	 */
	public Informer getInformer() {
		return informer;
	}

	/**
	 * Sets the value of the informer property.
	 *
	 * @param value allowed object is
	 *              {@link Informer }
	 */
	public void setInformer(Informer value) {
		this.informer = value;
	}

	/**
	 * Gets the value of the day property.
	 * <p/>
	 * <p/>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the day property.
	 * <p/>
	 * <p/>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getDay().add(newItem);
	 * </pre>
	 * <p/>
	 * <p/>
	 * <p/>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Day }
	 */
	public List<Day> getDay() {
		if (day == null) {
			day = new ArrayList<Day>();
		}
		return this.day;
	}

	/**
	 * Gets the value of the city property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the value of the city property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCity(String value) {
		this.city = value;
	}

	/**
	 * Gets the value of the climate property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getClimate() {
		return climate;
	}

	/**
	 * Sets the value of the climate property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setClimate(String value) {
		this.climate = value;
	}

	/**
	 * Gets the value of the country property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the value of the country property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCountry(String value) {
		this.country = value;
	}

	/**
	 * Gets the value of the countryId property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * Sets the value of the countryId property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCountryId(String value) {
		this.countryId = value;
	}

	/**
	 * Gets the value of the exactname property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getExactname() {
		return exactname;
	}

	/**
	 * Sets the value of the exactname property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setExactname(String value) {
		this.exactname = value;
	}

	/**
	 * Gets the value of the geoid property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getGeoid() {
		return geoid;
	}

	/**
	 * Sets the value of the geoid property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setGeoid(String value) {
		this.geoid = value;
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

	/**
	 * Gets the value of the lat property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * Sets the value of the lat property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setLat(String value) {
		this.lat = value;
	}

	/**
	 * Gets the value of the link property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets the value of the link property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setLink(String value) {
		this.link = value;
	}

	/**
	 * Gets the value of the lon property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getLon() {
		return lon;
	}

	/**
	 * Sets the value of the lon property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setLon(String value) {
		this.lon = value;
	}

	/**
	 * Gets the value of the part property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getPart() {
		return part;
	}

	/**
	 * Sets the value of the part property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setPart(String value) {
		this.part = value;
	}

	/**
	 * Gets the value of the partId property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getPartId() {
		return partId;
	}

	/**
	 * Sets the value of the partId property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setPartId(String value) {
		this.partId = value;
	}

	/**
	 * Gets the value of the region property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Sets the value of the region property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setRegion(String value) {
		this.region = value;
	}

	/**
	 * Gets the value of the slug property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * Sets the value of the slug property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setSlug(String value) {
		this.slug = value;
	}

	/**
	 * Gets the value of the source property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Sets the value of the source property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setSource(String value) {
		this.source = value;
	}

	/**
	 * Gets the value of the zoom property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getZoom() {
		return zoom;
	}

	/**
	 * Sets the value of the zoom property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setZoom(String value) {
		this.zoom = value;
	}

}
