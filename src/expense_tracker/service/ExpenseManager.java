package expense_tracker.service;

import java.util.List;
import java.util.ArrayList;
import expense_tracker.model.Expense;

public class ExpenseManager {
	private List<Expense> expenses;

	public ExpenseManager() {
		expenses = new ArrayList<Expense>();
	}

	// Add Method
	public void addExpense(Expense e) {
		expenses.add(e);
	}
	
	public void displayExpenses() {
		expenses.forEach(e -> System.out.println(e));
	}
	
	
	

}
