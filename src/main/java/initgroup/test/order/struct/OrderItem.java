package initgroup.test.order.struct;

import com.google.gson.annotations.SerializedName;
import initgroup.test.protocol.annotation.Tag;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

import static initgroup.test.order.struct.OrderItem.TAGS.*;

public class OrderItem {

	public interface TAGS {
		int PRODUCT = 11;
		int PRICE = 12;
		int COUNT = 13;
		int COST = 14;
	}

	@SerializedName("name")
	private String product;
	@SerializedName("price")
	private BigDecimal price;
	@SerializedName("quantity")
	private BigDecimal count;
	@SerializedName("sum")
	private BigDecimal cost;

	public OrderItem() {
	}

	public OrderItem(String product, BigDecimal price, BigDecimal count, BigDecimal cost) {
		this.product = product;
		this.price = price;
		this.count = count;
		this.cost = cost;
	}

	@Tag(PRODUCT)
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Tag(PRICE)
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Tag(COUNT)
	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	@Tag(COST)
	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		OrderItem orderItem = (OrderItem) o;

		return new EqualsBuilder()
				.append(product, orderItem.product)
				.append(price, orderItem.price)
				.append(count, orderItem.count)
				.append(cost, orderItem.cost)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(product)
				.append(price)
				.append(count)
				.append(cost)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("product", product)
				.append("price", price)
				.append("count", count)
				.append("cost", cost)
				.toString();
	}
}
