package backend.savefile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveMarkdown extends Save{
	
	public SaveMarkdown(String path, ArrayList<String[]> allTasks) {
		super(path, allTasks);
	}
	
	public void saveTasks() throws IOException {
		File reportFile = new File(this.path+".md");
		FileWriter fileWriter = new FileWriter(reportFile);
		
		fileWriter.write("| *TaskID* | *TaskText* | *MamaID* | *Start* | *End* | *Cost* |");
		fileWriter.write(System.lineSeparator());
		fileWriter.write("| --- | --- | --- | --- | --- | --- |");
		fileWriter.write(System.lineSeparator());
		
		for (int i = 0; i < this.allTasks.size(); i++) {
			if (this.allTasks.get(i)[2].equals("0")) {
				for (int j = 0; j < this.allTasks.get(i).length; j++) {
					fileWriter.write("| **");
					fileWriter.write(this.allTasks.get(i)[j]);
					fileWriter.write("** ");
				}
			} else {
				for (int j = 0; j < this.allTasks.get(i).length; j++) {
					fileWriter.write("| ");
					fileWriter.write(this.allTasks.get(i)[j]);
					fileWriter.write(" ");
				}
			}
			fileWriter.write("|");
			fileWriter.write(System.lineSeparator());
		}
		
		fileWriter.close();
	}
}
