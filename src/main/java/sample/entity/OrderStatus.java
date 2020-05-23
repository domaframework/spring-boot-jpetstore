package sample.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;

import java.time.LocalDate;

@Entity(metamodel = @Metamodel)
@Table(name = "ORDERSTATUS")
public class OrderStatus {

    @Id
    @Column(name = "ORDERID")
    private Integer orderId;

    @Id
    @Column(name = "LINENUM")
    private Integer lineNumber;

    @Column(name = "TIMESTAMP")
    private LocalDate timestamp;

    @Column(name = "STATUS")
    private String status;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}