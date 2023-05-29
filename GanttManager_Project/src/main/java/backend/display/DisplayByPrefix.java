package backend.display;

import java.util.ArrayList;

public class DisplayByPrefix extends Display<String>{
	
	public DisplayByPrefix(ArrayList<String[]> allTasks) {
		super(allTasks);
	}

	// Search tasks with specific prefix from this.allTasksData and returns them
	public ArrayList<String[]> SearchTaskToDisplay(String prefix) {
		ArrayList<String[]> tasksWithPrefix = new ArrayList<String[]>();
		for (int i = 0; i < super.allTasksData.size(); i++) {
			if (super.allTasksData.get(i)[1].startsWith(prefix)) {
				tasksWithPrefix.add(super.allTasksData.get(i));
			}
		}
		return tasksWithPrefix;
	}
}
