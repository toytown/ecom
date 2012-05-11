package com.ecom.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "geoLocation")
public class GeoLocation implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area1 == null) ? 0 : area1.hashCode());
		result = prime * result + ((area2 == null) ? 0 : area2.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((iso2 == null) ? 0 : iso2.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		long temp;
		temp = Double.doubleToLongBits(lat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lng);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((region1 == null) ? 0 : region1.hashCode());
		result = prime * result + ((region2 == null) ? 0 : region2.hashCode());
		result = prime * result + ((region3 == null) ? 0 : region3.hashCode());
		result = prime * result + ((region4 == null) ? 0 : region4.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoLocation other = (GeoLocation) obj;
		if (area1 == null) {
			if (other.area1 != null)
				return false;
		} else if (!area1.equals(other.area1))
			return false;
		if (area2 == null) {
			if (other.area2 != null)
				return false;
		} else if (!area2.equals(other.area2))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (iso2 == null) {
			if (other.iso2 != null)
				return false;
		} else if (!iso2.equals(other.iso2))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
			return false;
		if (Double.doubleToLongBits(lng) != Double.doubleToLongBits(other.lng))
			return false;
		if (region1 == null) {
			if (other.region1 != null)
				return false;
		} else if (!region1.equals(other.region1))
			return false;
		if (region2 == null) {
			if (other.region2 != null)
				return false;
		} else if (!region2.equals(other.region2))
			return false;
		if (region3 == null) {
			if (other.region3 != null)
				return false;
		} else if (!region3.equals(other.region3))
			return false;
		if (region4 == null) {
			if (other.region4 != null)
				return false;
		} else if (!region4.equals(other.region4))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;

	private String country;
    
    private String language;
    
    @Id
    private String id;

    private String iso2;
    
    @Indexed
    private String region1;
    
    private String region2;
    
    private String region3;
    
    private String region4;
    
    @Indexed
    private String zip;
    
    @Indexed
    private String city;
    
    private String area1;
    
    private String area2;

    private double lat;
    
    private double lng;
    
    private String timeZone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getRegion1() {
        return region1;
    }

    public void setRegion1(String region1) {
        this.region1 = region1;
    }

    public String getRegion2() {
        return region2;
    }

    public void setRegion2(String region2) {
        this.region2 = region2;
    }

    public String getRegion3() {
        return region3;
    }

    public void setRegion3(String region3) {
        this.region3 = region3;
    }

    public String getRegion4() {
        return region4;
    }

    public void setRegion4(String region4) {
        this.region4 = region4;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea1() {
        return area1;
    }

    public void setArea1(String area1) {
        this.area1 = area1;
    }

    public String getArea2() {
        return area2;
    }

    public void setArea2(String area2) {
        this.area2 = area2;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
    
    public boolean isLocationFound() {
    	return getLat() != 0.0d &&  getLng() != 0.0d;
    }
    
    public String getZipAndCity() {
    	return this.getZip() + " " + getCity();
    }
}
