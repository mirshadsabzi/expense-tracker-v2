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
				new Expense(10, "Ice Cream", new BigDecimal("12.99"), LocalDate.of(2026, 8, 24), Category.FOOD));
		manager.addExpense(
				new Expense(25, "Taxi Fare", new BigDecimal("19.99"), LocalDate.of(2026, 8, 25), Category.TRANSPORT));
		manager.addExpense(
				new Expense(55, "Internet package", new BigDecimal("16.99"), LocalDate.of(2026, 8, 1), Category.BILLS));
		manager.addExpense(new Expense(101, "Milk", new BigDecimal("19.56"), LocalDate.of(2026, 8, 28), Category.FOOD));

		
		
		System.out.println(manager.getTotalByCategory(Category.FOOD));
		
	}
	
	
	
	
	
}
