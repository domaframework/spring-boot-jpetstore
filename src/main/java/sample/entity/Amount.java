package sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.seasar.doma.Domain;

@Domain(valueType = BigDecimal.class)
public class Amount implements Serializable {

  private static final long serialVersionUID = 1L;

  public static final Amount ZERO = new Amount(BigDecimal.ZERO);

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
    final var prime = 31;
    var result = 1;
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
    var other = (Amount) obj;
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
