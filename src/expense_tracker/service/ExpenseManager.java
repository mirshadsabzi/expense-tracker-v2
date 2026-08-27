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

	// Finding index of expense by id
	public int findIndexById(int id) {

		for (int i = 0; i < expenses.size(); i++) {
			if (expenses.get(i).getId() == id) {
				return i;
			}
		}

		return -1;
	}

	// Remove expense by id
	public boolean removeById(int id) {
		int index = findIndexById(id);

		if (index != -1) {

			expenses.remove(index);
			return true;

		}

		System.out.println("There is no expense in the list with id " + id + ".\n");

		return false;
	}

}
