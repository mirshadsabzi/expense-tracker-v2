package expense_tracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
	private int id;
	private String description;
	private BigDecimal amount;
	private LocalDate date;
	private Category category;

	public Expense(int id, String description, BigDecimal amount, LocalDate date, Category category) {
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.date = date;
		this.category = category;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return String.format("Id: %d\nDescription: %s\nAmount: %.2f\nDate: %s\nCategory: %s\n\n----------\n", id,
				description, amount, date , category);
	}

}
