package sample.entity;

import java.io.Serializable;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;

@Entity(metamodel = @Metamodel)
public class Profile implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "USERID")
  private String username;

  @Column(name = "FAVCATEGORY")
  private String favouriteCategoryId;

  @Column(name = "LANGPREF")
  private String languagePreference;

  @Column(name = "MYLISTOPT")
  private boolean listOption;

  @Column(name = "BANNEROPT")
  private boolean bannerOption;

  @Override
  public String toString() {
    return "Profile [username="
        + username
        + ", favouriteCategoryId="
        + favouriteCategoryId
        + ", languagePreference="
        + languagePreference
        + ", listOption="
        + listOption
        + ", bannerOption="
        + bannerOption
        + "]";
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFavouriteCategoryId() {
    return favouriteCategoryId;
  }

  public void setFavouriteCategoryId(String favouriteCategoryId) {
    this.favouriteCategoryId = favouriteCategoryId;
  }

  public String getLanguagePreference() {
    return languagePreference;
  }

  public void setLanguagePreference(String languagePreference) {
    this.languagePreference = languagePreference;
  }

  public boolean isListOption() {
    return listOption;
  }

  public void setListOption(boolean listOption) {
    this.listOption = listOption;
  }

  public boolean isBannerOption() {
    return bannerOption;
  }

  public void setBannerOption(boolean bannerOption) {
    this.bannerOption = bannerOption;
  }
}
