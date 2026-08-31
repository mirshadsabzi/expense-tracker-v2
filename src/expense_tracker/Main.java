package expense_tracker;




import expense_tracker.service.ExpenseFileManager;
import expense_tracker.service.ExpenseManager;

public class Main {
	public static void main(String[] args) {

		ExpenseManager manager = new ExpenseManager();
		ExpenseFileManager fileManager = new ExpenseFileManager();
		
		ExpenseApp app = new ExpenseApp(manager, fileManager);
		
		app.run();
	}
}
