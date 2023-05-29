package backend.display;

import java.util.ArrayList;

public class DisplayTopLevel extends Display<Integer> {
	
	public DisplayTopLevel(ArrayList<String[]> allTasks) {
		super(allTasks);
	}

	// Search top level tasks from this.allTasksData and returns them
	public ArrayList<String[]> SearchTaskToDisplay(Integer mamaID) {
		ArrayList<String[]> tasksWithID = new ArrayList<String[]>();
		for (int i = 0; i < super.allTasksData.size(); i++) {
			if (Integer.parseInt(super.allTasksData.get(i)[2]) == mamaID) {
				tasksWithID.add(super.allTasksData.get(i));
			}
		}
		return tasksWithID;
	}
}
