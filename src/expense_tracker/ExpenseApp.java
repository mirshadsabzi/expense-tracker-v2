package expense_tracker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import expense_tracker.model.Category;
import expense_tracker.model.Expense;
import expense_tracker.service.ExpenseFileManager;
import expense_tracker.service.ExpenseManager;

public class ExpenseApp {
	private final ExpenseManager manager;
	private final ExpenseFileManager fileManager;
	private final Scanner scanner;

	public ExpenseApp(ExpenseManager manager, ExpenseFileManager fileManager) {
		this.manager = manager;
		this.fileManager = fileManager;
		this.scanner = new Scanner(System.in);
	}

	public void run() {

		fileManager.loadFromFile(manager, "expenses.txt");

		boolean running = true;

		while (running) {
			System.out.println("\n==== EXPENSE TRACKER ====");
			System.out.println("1. Add expense");
			System.out.println("2. Remove expense");
			System.out.println("3. Update expense");
			System.out.println("4. Find expense by ID");
			System.out.println("5. Find by category");
			System.out.println("6. Find by date");
			System.out.println("7. Find by date range");
			System.out.println("8. Find by minimum amount");
			System.out.println("9. Find by maximum amount");
			System.out.println("10. Sort by amount");
			System.out.println("11. Sort by date");
			System.out.println("12. Show total amount");
			System.out.println("13. Save to file");
			System.out.println("14. Load from file");
			System.out.println("15. Display all expenses: ");
			System.out.println("0. Exit");

			System.out.print("Choose an option: ");
			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1:
				addExpense();
				break;

			case 2:
				removeExpense();
				break;
			case 3:
				updateExpense();

				break;

			case 4:
				System.out.println(manager.findById(getId()));
				break;
			case 5:

				printList(manager.findByCategory(getCategory()));
				break;
			case 6:
				printList(manager.findByDate(getDate()));
				break;
			case 7:
				printListByDateRange();
				break;
			case 8:
				printList(manager.findByMinAmount(getAmount()));
				break;
			case 9:
				printList(manager.findByMaxAmount(getAmount()));
				break;
			case 10:
				printList(manager.sortByAmount());
				break;
			case 11:
				printList(manager.sortByDate());
				break;
			case 12:
				System.out.println(manager.getTotalAmount());
				break;
			case 13:
					saveToFile();
				break;
			case 14:
				loadFromFile();
				break;

			case 15:
				manager.displayExpenses();
				break;

			case 0:
				running = false;
				break;
			default:
				System.out.println("Invalid Option.");
			}

		}

	}

	private int getId() {
		System.out.print("Enter Id: ");
		
		return Integer.parseInt(scanner.nextLine());
	}

	private String getDescription() {
		System.out.print("Enter description: ");
		String description = scanner.nextLine();
		return description;
	}

	private BigDecimal getAmount() {
		System.out.print("Enter amount: ");
		
		return new BigDecimal(scanner.nextLine());
	}

	private LocalDate getDate() {
		System.out.print("Enter date: (yyyy-mm-dd) ");
		
		return LocalDate.parse(scanner.nextLine());

	}

	private Category getCategory() {
		System.out.print("Enter category: [FOOD, TRANSPORT, BILLS, ENTERTAINMENT, OTHERS] ");
		
		return Category.valueOf(scanner.nextLine().toUpperCase());

	}

	private void addExpense() {

		int id = getId();
		boolean added = manager.addExpense(new Expense(id, getDescription(), getAmount(), getDate(), getCategory()));

		if (added) {
			System.out.println("Expense added successfully.");
		}

	}

	private void removeExpense() {

		int id = getId();
		if (manager.removeById(id)) {
			System.out.println("The item with id " + id + " removed.");
		} else {
			System.out.println("Id " + id + " not found.");
		}
	}

	private void updateExpense() {
		boolean updated = manager.updateExpense(getId(), getDescription(), getAmount(), getDate(), getCategory());

		if (updated) {
			System.out.println("Expense updated successfully.");
		} else {
			System.out.println("Expense not found.");
		}
	}
	

	private void printList(List<Expense> expenses) {
		System.out.println(expenses);
	}
	private void printListByDateRange() {
		System.out.println("From: ");
		LocalDate from = getDate();
		System.out.println("To: ");
		LocalDate to = getDate();
		printList(manager.findByDateRange(from, to));
	}
	
	
	
	private void saveToFile() {
		fileManager.saveToFile(manager.getAllExpenses(), "expenses.txt");


	}
	
	private void loadFromFile() {
		fileManager.loadFromFile(manager, "expenses.txt");
	}
}
