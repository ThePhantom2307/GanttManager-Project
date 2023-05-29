package backend.savefile;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Save {
	public String path;
	public ArrayList<String[]> allTasks;
	
	public Save(String path, ArrayList<String[]> allTasks) {
		this.path = path;
		this.allTasks = allTasks;
	}
	
	public abstract void saveTasks() throws IOException;
}
