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
import java.math.BigDecimal;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String itemId;

    @Column(name = "PRODUCTID")
    private String productId;

    @Column(insertable = false, updatable = false)
    private String productName;

    @Column(insertable = false, updatable = false)
    private String productCategoryId;

    @Column(insertable = false, updatable = false)
    private String productDescription;

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

    @Column(name = "QTY", insertable = false, updatable = false)
    private Integer quantity;

    @Override
    public String toString() {
        return "Item [itemId=" + itemId + ", productId=" + productId
                + ", productName=" + productName + ", productCategoryId="
                + productCategoryId + ", productDescription="
                + productDescription + ", listPrice=" + listPrice
                + ", unitCost=" + unitCost + ", supplierId=" + supplierId
                + ", status=" + status + ", attribute1=" + attribute1
                + ", attribute2=" + attribute2 + ", attribute3=" + attribute3
                + ", attribute4=" + attribute4 + ", attribute5=" + attribute5
                + ", quantity=" + quantity + "]";
    }

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
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
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
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
