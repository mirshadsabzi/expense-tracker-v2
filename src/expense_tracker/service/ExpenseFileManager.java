package expense_tracker.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import expense_tracker.model.Category;

import expense_tracker.model.Expense;

public class ExpenseFileManager {
	public boolean saveToFile(List<Expense> expenses, String fileName) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

			for (Expense e : expenses) {
				String expenseInfo = e.getId() + "|" + e.getDescription() + "|" + e.getAmount() + "|" + e.getDate()
						+ "|" + e.getCategory();

				writer.write(expenseInfo);
				writer.newLine();

			}

			return true;

		} catch (IOException e) {

			return false;
		}

	}

	private Expense parseExpense(String line) {

		String[] parts = line.split("\\|");

		if (parts.length != 5) {
			System.out.println("{" + line + "}" + " is not a valid expense format, so it cannot be added to the list.");
			return null;
		}

		int id;
		try {
			id = Integer.parseInt(parts[0]);
		} catch (NumberFormatException e) {
			System.out.println("Expense with ID '" + parts[0] + "' not added because the ID format is not correct.");
			return null;

		}

		String description = parts[1].trim();

		if (description.isEmpty()) {
			System.out.println("Description cannot be empty, so expense with ID " + id + " was not added.");
			return null;
		}

		BigDecimal amount;
		try {
			amount = new BigDecimal(parts[2]);

		} catch (NumberFormatException e) {
			System.out.println("Expense with ID of " + id + " not added.");
			return null;

		}

		LocalDate date;

		try {
			date = LocalDate.parse(parts[3]);
		} catch (DateTimeParseException e) {
			System.out.println("Expense with ID of " + id + " not added.");
			return null;
		}

		Category category;
		try {
			category = Category.valueOf(parts[4]);
		} catch (IllegalArgumentException e) {
			System.out.println(parts[4] + " is not in the category list then expense with ID " + id + " not added.");
			return null;
		}

		return new Expense(id, description, amount, date, category);
	}

	public boolean loadFromFile(ExpenseManager manager, String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

			manager.clear();

			String line = reader.readLine();

			while (line != null) {
				Expense expense = parseExpense(line);

				if (expense != null) {
					manager.addExpense(expense);
				}

				line = reader.readLine();
			}

			return true;

		} catch (IOException e) {

			return false;
		}

	}

}
