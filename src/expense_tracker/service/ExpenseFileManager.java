package expense_tracker.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import expense_tracker.model.Expense;

public class ExpenseFileManager {
	public void saveToFile(List<Expense> expenses, String fileName) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

			for (Expense e : expenses) {
				String expenseInfo = e.getId() + "|" + e.getDescription() + "|" + e.getAmount() + "|"
						+ e.getDate() + "|" + e.getCategory();

				writer.write(expenseInfo);
				writer.newLine();

			}
			
			System.out.println("All expense saved.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
