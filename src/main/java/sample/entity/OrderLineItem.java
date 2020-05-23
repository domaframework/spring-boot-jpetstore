/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package sample.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;
import org.seasar.doma.Transient;

import java.io.Serializable;

@Entity(metamodel = @Metamodel)
@Table(name = "LINEITEM")
public class OrderLineItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer orderId;

    @Id
    @Column(name = "LINENUM")
    private int lineNumber;

    private int quantity;

    private String itemId;

    private Amount unitPrice;

    @Transient
    private Item item = new Item();

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Amount getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Amount unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Amount getListPrice() {
        return item.getListPrice();
    }

    public String getProductName() {
        return item.getProductName();
    }

    public String getAttribute1() {
        return item.getAttribute1();
    }

    public String getAttribute2() {
        return item.getAttribute2();
    }

    public String getAttribute3() {
        return item.getAttribute3();
    }

    public String getAttribute4() {
        return item.getAttribute4();
    }

    public String getAttribute5() {
        return item.getAttribute5();
    }

    public Amount getTotal() {
        return getListPrice().multiply(quantity);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
