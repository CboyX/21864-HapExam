package hbi.core.dto;
/**
 * 封装了companyName、customerName、totalBill的订单头
 */

import com.hand.hap.core.annotation.Children;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import com.hand.hap.system.dto.BaseDTO;
import net.shibboleth.utilities.java.support.annotation.constraint.NotEmpty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@ExtensionAttribute(disable=true)
public class OmOrder extends BaseDTO {
    public static final String FIELD_HEADER_ID = "headerId";
    public static final String FIELD_COMPANY_ID = "companyId";
    public static final String FIELD_CUSTOMER_ID = "customerId";
    public static final String FIELD_ORDER_NUMBER = "orderNumber";
    public static final String FIELD_COMPANY_NAME = "companyName";
    public static final String FIELD_CUSTOMER_NAME = "customerName";
    public static final String FIELD_ORDER_DATE = "orderDate";
    public static final String FIELD_ORDER_STATUS = "orderStatus";
    public static final String FIELD_TOTAL_BILL ="totalBill";
    @Id
    @GeneratedValue
    private Long headerId;
    @NotNull
    private Long companyId;
    @NotNull
    private Long customerId;
    @NotEmpty
    @Length(max = 60)
    private String orderNumber;
    @Length(max = 240)
    private String companyName;
    @Length(max = 240)
    private String customerName;
    @NotNull
    private Date orderDate;
    @NotEmpty
    @Length(max = 30)
    private String orderStatus;
    @Transient
    private double totalBill;
    @Transient
    private String itemCode;
    @Transient
    @Children
    private List<OmOrderLines> lines;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Long getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Long headerId) {
        this.headerId = headerId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }


    public List<OmOrderLines> getLines() {
        return lines;
    }

    public void setLines(List<OmOrderLines> lines) {
        this.lines = lines;
    }
}
