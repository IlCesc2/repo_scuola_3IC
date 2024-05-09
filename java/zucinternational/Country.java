package zucinternational;

public class Country {
    private String name;
    private String alpha2;
    private String alpha3;
    private String countryCode;
    private String iso_3166_2;
    private String region;
    private String subRegion;
    private String intermediateRegion;
    private String regionCode;
    private String subRegionCode;
    private String intermediateRegionCode;


    
    /** 
     * returns country info
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", alpha2='" + getAlpha2() + "'" +
            ", alpha3='" + getAlpha3() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", iso_3166_2='" + getIso_3166_2() + "'" +
            ", region='" + getRegion() + "'" +
            ", subRegion='" + getSubRegion() + "'" +
            ", intermediateRegion='" + getIntermediateRegion() + "'" +
            ", regionCode='" + getRegionCode() + "'" +
            ", subRegionCode='" + getSubRegionCode() + "'" +
            ", intermediateRegionCode='" + getIntermediateRegionCode() + "'" +
            "}";
    }
    public Country(String name, String alpha2, String alpha3, String countryCode, String iso_3166_2, String region,
            String subRegion, String intermediateRegion, String regionCode, String subRegionCode,
            String intermediateRegionCode) {
        this.name = name;
        this.alpha2 = alpha2;
        this.alpha3 = alpha3;
        this.countryCode = countryCode;
        this.iso_3166_2 = iso_3166_2;
        this.region = region;
        this.subRegion = subRegion;
        this.intermediateRegion = intermediateRegion;
        this.regionCode = regionCode;
        this.subRegionCode = subRegionCode;
        this.intermediateRegionCode = intermediateRegionCode;
    }
     /** 
     * checks if code is valid
     * @return boolean
     */
    public boolean hasCode(String code) {
        switch (code.length()) {
            case 2:
                return code.toUpperCase().equals(this.alpha2);
            case 3:
                return code.toUpperCase().equals(this.alpha3);
        }
        return false;
    }
   
    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getAlpha2() {
        return this.alpha2;
    }

    public void setAlpha2(String value) {
        this.alpha2 = value;
    }

    public String getAlpha3() {
        return this.alpha3;
    }

    public void setAlpha3(String value) {
        this.alpha3 = value;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    public String getIso_3166_2() {
        return this.iso_3166_2;
    }

    public void setIso_3166_2(String value) {
        this.iso_3166_2 = value;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String value) {
        this.region = value;
    }

    public String getSubRegion() {
        return this.subRegion;
    }

    public void setSubRegion(String value) {
        this.subRegion = value;
    }

    public String getIntermediateRegion() {
        return this.intermediateRegion;
    }

    public void setIntermediateRegion(String value) {
        this.intermediateRegion = value;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public void setRegionCode(String value) {
        this.regionCode = value;
    }

    public String getSubRegionCode() {
        return this.subRegionCode;
    }

    public void setSubRegionCode(String value) {
        this.subRegionCode = value;
    }

    public String getIntermediateRegionCode() {
        return this.intermediateRegionCode;
    }

    public void setIntermediateRegionCode(String value) {
        this.intermediateRegionCode = value;
    }
}
