package sample.entity;

import java.io.Serializable;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;

@Entity(metamodel = @Metamodel)
public class Category implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "CATID")
  private String categoryId;

  private String name;

  @Column(name = "DESCN")
  private String description;

  @Override
  public String toString() {
    return "Category [categoryId="
        + categoryId
        + ", name="
        + name
        + ", description="
        + description
        + "]";
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
