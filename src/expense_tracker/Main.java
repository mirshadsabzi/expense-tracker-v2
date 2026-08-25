package expense_tracker;

import java.math.BigDecimal;
import java.time.LocalDate;

import expense_tracker.model.Category;
import expense_tracker.model.Expense;
import expense_tracker.service.ExpenseManager;

public class Main {
	public static void main(String[] args) {

		ExpenseManager manager = new ExpenseManager();

		manager.addExpense(
				new Expense(1, "Ice Cream", new BigDecimal("12.99"), LocalDate.of(2026, 8, 24), Category.FOOD));
		manager.addExpense(
				new Expense(2, "Taxi Fare", new BigDecimal("19.99"), LocalDate.of(2026, 8, 25), Category.TRANSPORT));
		manager.addExpense(
				new Expense(3, "Internet package", new BigDecimal("16.99"), LocalDate.of(2026, 8, 1), Category.BILLS));
		
		manager.displayExpenses();

	}
}
