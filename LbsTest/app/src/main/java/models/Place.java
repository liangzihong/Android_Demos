package models;

/**
 * Created by Liang Zihong on 2018/3/18.
 */

public class Place {
    private String country;
    private String province;
    private String city;
    private String district;
    private String street;

    @Override
    public String toString() {
        return "Place\n" +
                "国家：" + country + '\n' +
                "省份：" + province + '\n' +
                "城市：" + city + '\n' +
                "地区：" + district + '\n' +
                "街道：" + street + '\n'
                ;
    }

    public Place(String country, String province, String city, String district, String street) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
