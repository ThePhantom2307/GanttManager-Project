package backend.display;

import java.util.ArrayList;

public class DisplayByID extends Display<Integer> {
	
	public DisplayByID(ArrayList<String[]> allTasks) {
		super(allTasks);
	}

	// Search tasks with specific ID from this.allTasksData and returns them
	public ArrayList<String[]> SearchTaskToDisplay(Integer id) {
		ArrayList<String[]> tasksWithID = new ArrayList<String[]>();
		for (int i = 0; i < super.allTasksData.size(); i++) {
			if (Integer.parseInt(super.allTasksData.get(i)[2]) == id || Integer.parseInt(super.allTasksData.get(i)[0]) == id) {
				tasksWithID.add(super.allTasksData.get(i));
			}
		}
		return tasksWithID;
	}
}
