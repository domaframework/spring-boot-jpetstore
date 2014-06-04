package sample.web.cart;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class CartItemForm {

    @Max(99)
    @NotNull
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
