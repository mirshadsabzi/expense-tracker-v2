package expense_tracker.service;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import expense_tracker.model.Category;
import expense_tracker.model.Expense;

public class ExpenseManager {
	private final List<Expense> expenses;

	public ExpenseManager() {
		expenses = new ArrayList<>();
	}

	// Add Method
	public void addExpense(Expense e) {

		expenses.add(e);

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

		return false;
	}

	public List<Expense> getAllExpenses() {

		return new ArrayList<>(expenses);

	}

	public BigDecimal getTotalAmount() {

		return calculateTotal(expenses);
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
		return expenses.stream().filter(e -> e.getCategory() == category).collect(Collectors.toList());

	}

	public List<Expense> sortByAmount() {

		List<Expense> sortedExpenses = new ArrayList<>(expenses);

		Collections.sort(sortedExpenses, (e1, e2) -> e1.getAmount().compareTo(e2.getAmount()));

		return sortedExpenses;

	}

	public List<Expense> sortByDate() {

		List<Expense> sortedExpenses = new ArrayList<>(expenses);

		Collections.sort(sortedExpenses, (e1, e2) -> -e1.getDate().compareTo(e2.getDate()));

		return sortedExpenses;

	}

	public List<Expense> findByMaxAmount(BigDecimal maxAmount) {
		return expenses.stream().filter(e -> e.getAmount().compareTo(maxAmount) <= 0).collect(Collectors.toList());
	}

	public List<Expense> findByDate(LocalDate date) {

		return expenses.stream().filter(e -> e.getDate().equals(date)).collect(Collectors.toList());

	}

	public List<Expense> findByDateRange(LocalDate d1, LocalDate d2) {
		return expenses.stream().filter(e -> !e.getDate().isBefore(d1) && !e.getDate().isAfter(d2))
				.collect(Collectors.toList());

	}

	public List<Expense> findByMinAmount(BigDecimal min) {

		return expenses.stream().filter(e -> e.getAmount().compareTo(min) >= 0).collect(Collectors.toList());

	}

	public BigDecimal getTotalByCategory(Category category) {

		return calculateTotal(findByCategory(category));
	}

	private BigDecimal calculateTotal(List<Expense> expenses) {

		return expenses.stream().map(e -> e.getAmount()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

	}

	public void clear() {

		expenses.clear();

	}

}
