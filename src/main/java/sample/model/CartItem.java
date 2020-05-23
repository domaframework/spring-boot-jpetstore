package sample.model;

import java.io.Serializable;
import sample.entity.Amount;
import sample.entity.Item;

public class CartItem implements Serializable {

  private static final long serialVersionUID = 1L;

  private Item item;
  private int quantity;
  private boolean inStock;
  private Amount total;

  public boolean isInStock() {
    return inStock;
  }

  public void setInStock(boolean inStock) {
    this.inStock = inStock;
  }

  public Amount getTotal() {
    return total;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
    calculateTotal();
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
    calculateTotal();
  }

  public void incrementQuantity() {
    quantity++;
    calculateTotal();
  }

  private void calculateTotal() {
    if (item != null && item.getListPrice() != null) {
      total = item.getListPrice().multiply(quantity);
    } else {
      total = null;
    }
  }
}
