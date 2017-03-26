package initgroup.test.order.struct;

import com.google.gson.annotations.SerializedName;
import initgroup.test.protocol.annotation.Tag;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static initgroup.test.order.struct.Order.TAGS.*;

/**
 * Примечание: Аннотации зло. Они порождают хаос и веру в магию. В добавок,
 * * не может быть повторение tag в одной структуре
 * * все проверки выполняются при запуске
 * Я бы предложил протокол с порядком полей.
 * С начала идет код структуры (tag), а потом значение полей. Поля упорядочиваем. Здесь небольшая будет заморочка с null
 * значениями, но это решаемо.
 */
public class Order {

	/**
	 * Замечание: мы не можем хранить все значение в одном enum т.к. потенциально может быть много проектов
	 */
	public interface TAGS {
		int DATE = 1;
		int NUMBER = 2;
		int CUSTOMER = 3;
		int ITEMS = 4;
	}

	@SerializedName("dateTime")
	private Date date;
	@SerializedName("orderNumber")
	private BigDecimal number;
	@SerializedName("customerName")
	private String customer;
	@SerializedName("items")
	private List<OrderItem> items;

	public Order() {
	}

	public Order(Date date, BigDecimal number, String customer, List<OrderItem> items) {
		this.date = date;
		this.number = number;
		this.customer = customer;
		this.items = items;
	}

	@Tag(DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Tag(NUMBER)
	public BigDecimal getNumber() {
		return number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	@Tag(CUSTOMER)
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	@Tag(ITEMS)
	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Order order = (Order) o;

		return new EqualsBuilder()
				.append(date, order.date)
				.append(number, order.number)
				.append(customer, order.customer)
				.append(items, order.items)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(date)
				.append(number)
				.append(customer)
				.append(items)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("date", date)
				.append("number", number)
				.append("customer", customer)
				.append("items", items)
				.toString();
	}
}
