package expense_tracker.service;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import expense_tracker.model.Category;
import expense_tracker.model.Expense;

public class ExpenseManager {
	private List<Expense> expenses;

	public ExpenseManager() {
		expenses = new ArrayList<>();
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

	public List<Expense> getAllExpenses() {

		return new ArrayList<>(expenses);

	}

	public BigDecimal getTotalAmount() {
		BigDecimal total = BigDecimal.ZERO;

		for (Expense e : expenses) {
			total = total.add(e.getAmount());

		}

		return total;
	}

	public Expense findById(int id) {

		int index = findIndexById(id);

		return index == -1 ? null : expenses.get(index);
	}

	public boolean updateExpense(int id, String description, BigDecimal amount, LocalDate date, Category category) {
		int index = findIndexById(id);

		if (index == -1) {
			return false;
		}

		Expense expense = expenses.get(index);

		expense.setAmount(amount);
		expense.setDescription(description);
		expense.setDate(date);
		expense.setCategory(category);

		return true;
	}

	public List<Expense> findByCategory(Category category) {
		List<Expense> matchingExpenses = new ArrayList<>();

		for (Expense e : expenses) {
			if (e.getCategory() == category) {
				matchingExpenses.add(e);
			}
		}

		return matchingExpenses;
	}

	public List<Expense> sortByAmount() {

		List<Expense> sortedExpenses = new ArrayList<>(expenses);

		Collections.sort(sortedExpenses , (e1, e2) -> e1.getAmount().compareTo(e2.getAmount()));

		return sortedExpenses ;

	}

}
