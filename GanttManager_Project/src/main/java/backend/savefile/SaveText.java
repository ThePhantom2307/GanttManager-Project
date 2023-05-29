package backend.savefile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveText extends Save{
	
	public SaveText(String path, ArrayList<String[]> allTasks) {
		super(path, allTasks);
	}
	
	public void saveTasks() throws IOException {
		File reportFile = new File(this.path+".txt");
		FileWriter fileWriter = new FileWriter(reportFile);
		
		fileWriter.write("TaskID\tTaskText\tMamaID\tStart\tEnd\tCost");
		fileWriter.write(System.lineSeparator());
		
		for (int i = 0; i < this.allTasks.size(); i++) {
			for (int j = 0; j < this.allTasks.get(i).length; j++) {
				fileWriter.write(this.allTasks.get(i)[j]);
				fileWriter.write("\t");
			}
			fileWriter.write(System.lineSeparator());
		}
		fileWriter.close();
	}
}