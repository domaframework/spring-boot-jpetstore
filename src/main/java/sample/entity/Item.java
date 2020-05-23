package sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Transient;

@Entity(metamodel = @Metamodel)
public class Item implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id private String itemId;

  @Column(name = "PRODUCTID")
  private String productId;

  private Amount listPrice;

  private BigDecimal unitCost;

  @Column(name = "SUPPLIER")
  private int supplierId;

  private String status;

  @Column(name = "ATTR1")
  private String attribute1;

  @Column(name = "ATTR2")
  private String attribute2;

  @Column(name = "ATTR3")
  private String attribute3;

  @Column(name = "ATTR4")
  private String attribute4;

  @Column(name = "ATTR5")
  private String attribute5;

  @Transient private Inventory inventory = new Inventory();

  @Transient private Product product = new Product();

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return product.getName();
  }

  public void setProductName(String productName) {
    product.setName(productName);
  }

  public String getProductCategoryId() {
    return product.getCategoryId();
  }

  public void setProductCategoryId(String productCategoryId) {
    product.setCategoryId(productCategoryId);
  }

  public String getProductDescription() {
    return product.getDescription();
  }

  public void setProductDescription(String productDescription) {
    product.setDescription(productDescription);
  }

  public Amount getListPrice() {
    return listPrice;
  }

  public void setListPrice(Amount listPrice) {
    this.listPrice = listPrice;
  }

  public BigDecimal getUnitCost() {
    return unitCost;
  }

  public void setUnitCost(BigDecimal unitCost) {
    this.unitCost = unitCost;
  }

  public int getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(int supplierId) {
    this.supplierId = supplierId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getAttribute1() {
    return attribute1;
  }

  public void setAttribute1(String attribute1) {
    this.attribute1 = attribute1;
  }

  public String getAttribute2() {
    return attribute2;
  }

  public void setAttribute2(String attribute2) {
    this.attribute2 = attribute2;
  }

  public String getAttribute3() {
    return attribute3;
  }

  public void setAttribute3(String attribute3) {
    this.attribute3 = attribute3;
  }

  public String getAttribute4() {
    return attribute4;
  }

  public void setAttribute4(String attribute4) {
    this.attribute4 = attribute4;
  }

  public String getAttribute5() {
    return attribute5;
  }

  public void setAttribute5(String attribute5) {
    this.attribute5 = attribute5;
  }

  public Integer getQuantity() {
    return inventory.getQuantity();
  }

  public void setQuantity(Integer quantity) {
    inventory.setQuantity(quantity);
  }

  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
