package expense_tracker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
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
		loadExpenses();

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
			System.out.println("16. Clear all expenses: ");
			System.out.println("0. Exit");

			System.out.print("Choose an option: ");
			int choice = readMenuChoice();

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
				Expense expense = manager.findById(getId());

				if (expense == null) {
					System.out.println("Expense not found");
				} else {

					System.out.println(expense);
				}

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
				saveExpenses();
				break;

			case 14:
				loadExpenses();
				break;

			case 15:
				
				printList(manager.getAllExpenses());
				break;
			case 16:
				manager.clear();
				if (saveExpenses()) {
					System.out.println("List cleared.");
				} else {
					loadExpenses();
				}
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
		while (true) {
			System.out.print("Enter Id: ");
			try {
				int id = Integer.parseInt(scanner.nextLine());

				if (id <= 0) {
					System.out.println("Enter a positive integer.");

				} else {
					return id;
				}

			} catch (NumberFormatException e) {
				System.out.println("Enter a positive integer.");
			}
		}

	}

	private int getNewId() {
		while (true) {
			int id = getId();

			if (manager.findIndexById(id) == -1) {
				return id;
			}

			System.out.println("This ID already exists.");
		}
	}

	private int getExistingID() {
		while (true) {
			int id = getId();
			if (manager.findIndexById(id) != -1) {
				return id;
			} else {
				System.out.println("This ID doesn't exist.");
			}
		}
	}

	private String getDescription() {

		while (true) {
			System.out.print("Enter description: ");

			String description = scanner.nextLine().trim();

			if (!description.isEmpty()) {
				return description;
			}

			System.out.println("Desctiption cannot be empty.");
		}

	}

	private BigDecimal getAmount() {

		while (true) {

			System.out.print("Enter amount: ");
			try {
				BigDecimal amount = new BigDecimal(scanner.nextLine());

				if (amount.compareTo(BigDecimal.ZERO) > 0) {
					return amount;
				}

				System.out.println("Enter a positive number");
			} catch (NumberFormatException e) {
				System.out.println("Enter a positive number");
			}

		}

	}

	private LocalDate getDate() {

		while (true) {
			System.out.print("Enter date: (yyyy-mm-dd) ");
			try {
				LocalDate date = LocalDate.parse(scanner.nextLine());

				return date;

			} catch (DateTimeParseException e) {
				System.out.println("Enter the date in the correct format. For example: 2026-08-31");
			}
		}

	}

	private Category getCategory() {

		while (true) {
			System.out.println("Enter category: " + Arrays.toString(Category.values()));
			try {

				return Category.valueOf(scanner.nextLine().trim().toUpperCase());
			} catch (IllegalArgumentException e) {
				System.out.println("Choose a category from the list.");

			}
		}

	}

	private void addExpense() {
		int id = getNewId();

		manager.addExpense(new Expense(id, getDescription(), getAmount(), getDate(), getCategory()));

		System.out.println("Expense added successfully.");

	}

	private void removeExpense() {

		int id = getExistingID();
		manager.removeById(id);
		System.out.println("The item with id " + id + " removed.");

	}

	private void updateExpense() {

		int id = getExistingID();

		manager.updateExpense(id, getDescription(), getAmount(), getDate(), getCategory());

		System.out.println("Expense updated successfully.");

	}

	private void printList(List<Expense> expenses) {

		if (expenses.isEmpty()) {

			System.out.println("No expenses found");

		} else

		{

			System.out.println(expenses);
		}

	}

	private void printListByDateRange() {
		LocalDate from;
		LocalDate to;

		while (true) {

			System.out.println("From: ");
			from = getDate();
			System.out.println("To: ");
			to = getDate();

			if (from.isAfter(to)) {
				System.out.println("'From' must not be after 'To'. Please enter a valid range.");
			} else {

				printList(manager.findByDateRange(from, to));
				break;
			}
		}
	}

	private boolean saveToFile() {
		return fileManager.saveToFile(manager.getAllExpenses(), "expenses.txt");

	}

	private boolean loadFromFile() {
		return fileManager.loadFromFile(manager, "expenses.txt");

	}

	private int readMenuChoice() {

		while (true) {
			try {
				int choice = Integer.parseInt(scanner.nextLine());

				if (choice >= 0 && choice <= 16) {
					return choice;
				}

				System.out.println("Enter a valid option: ");
			} catch (NumberFormatException e) {
				System.out.println("Enter a valid option: ");
			}
		}

	}

	private void loadExpenses() {
		boolean loaded = loadFromFile();
		if (loaded) {
			System.out.println("Expenses loaded successfully.");
		} else {
			System.out.println("Failed to load from the file.");
		}
	}

	private boolean saveExpenses() {
		boolean saved = saveToFile();
		if (saved) {
			System.out.println("All expense saved.");
			return true;
		} else {
			System.out.println("Could not save expenses to file.");
			return false;
		}
	}

	
}
