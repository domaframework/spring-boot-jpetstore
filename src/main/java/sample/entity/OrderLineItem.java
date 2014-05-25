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

import java.io.Serializable;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Entity
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

    @Column(insertable = false, updatable = false)
    private Amount listPrice;

    @Column(insertable = false, updatable = false)
    private String productName;

    @Column(insertable = false, updatable = false)
    private String attribute1;

    @Column(insertable = false, updatable = false)
    private String attribute2;

    @Column(insertable = false, updatable = false)
    private String attribute3;

    @Column(insertable = false, updatable = false)
    private String attribute4;

    @Column(insertable = false, updatable = false)
    private String attribute5;

    public Amount getTotal() {
        return listPrice.multiply(quantity);
    }

    @Override
    public String toString() {
        return "OrderLineItem [orderId=" + orderId + ", lineNumber="
                + lineNumber + ", quantity=" + quantity + ", itemId=" + itemId
                + ", unitPrice=" + unitPrice + ", listPrice=" + listPrice
                + ", productName=" + productName + ", attribute1=" + attribute1
                + ", attribute2=" + attribute2 + ", attribute3=" + attribute3
                + ", attribute4=" + attribute4 + ", attribute5=" + attribute5
                + "]";
    }

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
        return listPrice;
    }

    public void setListPrice(Amount listPrice) {
        this.listPrice = listPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

}
