package expense_tracker;

import java.math.BigDecimal;
import java.time.LocalDate;

import expense_tracker.model.Category;
import expense_tracker.model.Expense;

public class Main {
	public static void main(String[] args) {

		Expense[] allExpenses = {
				new Expense(1, "Ice Cream", new BigDecimal("12.99"), LocalDate.of(2026, 8, 24), Category.FOOD),

				new Expense(2, "Taxi Fare", new BigDecimal("19.99"), LocalDate.of(2026, 8, 25), Category.TRANSPORT),

				new Expense(3, "Internet package", new BigDecimal("16.99"), LocalDate.of(2026, 8, 1), Category.BILLS) };

		for (Expense e : allExpenses) {
			System.out.println("Id: " + e.getId());
			System.out.println("Description: " + e.getDescription());
			System.out.println("Amount: " + e.getAmount());
			System.out.println("Date: " + e.getDate());
			System.out.println("Category: " + e.getCategory());
			
			System.out.println("\n--------------------\n");

		}

	}
}
