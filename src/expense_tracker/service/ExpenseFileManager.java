package expense_tracker.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import expense_tracker.model.Category;

import expense_tracker.model.Expense;

public class ExpenseFileManager {
	public void saveToFile(List<Expense> expenses, String fileName) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

			for (Expense e : expenses) {
				String expenseInfo = e.getId() + "|" + e.getDescription() + "|" + e.getAmount() + "|" + e.getDate()
						+ "|" + e.getCategory();

				writer.write(expenseInfo);
				writer.newLine();

			}

			System.out.println("All expense saved.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean loadFromFile(ExpenseManager manager, String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

			manager.clear();

			String line = reader.readLine();
			while (line != null) {
				String[] parts = line.split("\\|");

				int id = Integer.parseInt(parts[0]);
				String description = parts[1];
				BigDecimal amount = new BigDecimal(parts[2]);
				LocalDate date = LocalDate.parse(parts[3]);
				Category category = Category.valueOf(parts[4]);
				
				manager.addExpense(new Expense(id, description, amount, date, category));

				line = reader.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
