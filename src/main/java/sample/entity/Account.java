package sample.entity;

import java.io.Serializable;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Transient;

@Entity(metamodel = @Metamodel)
public class Account implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "USERID")
  private String username;

  private String email;

  private String firstName;

  private String lastName;

  private String status;

  @Column(name = "ADDR1")
  private String address1;

  @Column(name = "ADDR2")
  private String address2;

  private String city;

  private String state;

  private String zip;

  private String country;

  private String phone;

  @Transient private Profile profile = new Profile();
  @Transient private Signon signon = new Signon();
  @Transient private BannerData bannerdata = new BannerData();

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
    profile.setUsername(username);
    signon.setUsername(username);
  }

  public String getPassword() {
    return signon.getPassword();
  }

  public void setPassword(String password) {
    signon.setPassword(password);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFavouriteCategoryId() {
    return profile.getFavouriteCategoryId();
  }

  public void setFavouriteCategoryId(String favouriteCategoryId) {
    profile.setFavouriteCategoryId(favouriteCategoryId);
  }

  public String getLanguagePreference() {
    return profile.getLanguagePreference();
  }

  public void setLanguagePreference(String languagePreference) {
    profile.setLanguagePreference(languagePreference);
  }

  public boolean isListOption() {
    return profile.isListOption();
  }

  public void setListOption(boolean listOption) {
    profile.setListOption(listOption);
  }

  public boolean isBannerOption() {
    return profile.isBannerOption();
  }

  public void setBannerOption(boolean bannerOption) {
    profile.setBannerOption(bannerOption);
  }

  public String getBannerName() {
    return bannerdata.getBannerName();
  }

  public void setBannerName(String bannerName) {
    bannerdata.setBannerName(bannerName);
  }

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public Signon getSignon() {
    return signon;
  }

  public void setSignon(Signon signon) {
    this.signon = signon;
  }

  public BannerData getBannerData() {
    return bannerdata;
  }

  public void setBannerData(BannerData bannerdata) {
    this.bannerdata = bannerdata;
  }
}
