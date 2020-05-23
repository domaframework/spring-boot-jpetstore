package sample.web.cart;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CartForm {

  @Valid @NotNull private Map<String, CartItemForm> items = new HashMap<>();

  public Map<String, CartItemForm> getItems() {
    return items;
  }

  public void setItems(Map<String, CartItemForm> itemIds) {
    this.items = itemIds;
  }
}
