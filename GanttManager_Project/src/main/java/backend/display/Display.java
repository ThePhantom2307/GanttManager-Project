package backend.display;

import java.util.ArrayList;

public abstract class Display<Type> {
	public ArrayList<String[]> allTasksData;

	public Display(ArrayList<String[]> allTasks) {
		this.allTasksData = allTasks;
	}
	
	// Search tasks from this.allTasksData and returns them
	public abstract ArrayList<String[]> SearchTaskToDisplay(Type item);
}
