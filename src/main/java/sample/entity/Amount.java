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

import org.seasar.doma.Domain;

@Domain(valueType = BigDecimal.class)
public class Amount implements Serializable {

    private static final long serialVersionUID = 1L;

    public static Amount ZERO = new Amount(BigDecimal.ZERO);

    private final BigDecimal value;

    public Amount(int value) {
        this(new BigDecimal(value));
    }

    public Amount(BigDecimal value) {
        if (value == null) {
            this.value = ZERO.value;
        } else {
            this.value = value;
        }
    }

    public BigDecimal getValue() {
        return value;
    }

    public Amount add(Amount amount) {
        return new Amount(value.add(amount.getValue()));
    }

    public Amount add(int quantity) {
        return new Amount(value.add(new BigDecimal(quantity)));
    }

    public Amount multiply(Amount amount) {
        return new Amount(value.multiply(amount.getValue()));
    }

    public Amount multiply(int quantity) {
        return new Amount(value.multiply(new BigDecimal(quantity)));
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Amount other = (Amount) obj;
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

}
