package backend.savefile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveHTML extends Save {
	
	public SaveHTML(String path, ArrayList<String[]> allTasks) {
		super(path, allTasks);
	}
	
	public void saveTasks() throws IOException {		
		File reportFile = new File(this.path+".html");
		FileWriter fileWriter = new FileWriter(reportFile);
		
		fileWriter.write("<!DOCKTYPE html>");
		fileWriter.write("<html>");
		fileWriter.write("<head>");
		fileWriter.write("<title>HTML Report</title>");
		fileWriter.write("</head>");
		fileWriter.write("<body>");
		fileWriter.write("<table>");
		fileWriter.write("<tr>");
		fileWriter.write("<td>TaskID</td>");
		fileWriter.write("<td>TaskText</td>");
		fileWriter.write("<td>MamaID</td>");
		fileWriter.write("<td>Start</td>");
		fileWriter.write("<td>End</td>");
		fileWriter.write("<td>Cost</td>");
		fileWriter.write("</tr>");
		
		for (int i = 0; i < this.allTasks.size(); i++) {
			fileWriter.write("<tr>");
			if (this.allTasks.get(i)[2].equals("0")) {
				for (int j = 0; j < this.allTasks.get(i).length; j++) {
					fileWriter.write("<td><b>");
					fileWriter.write(this.allTasks.get(i)[j]);
					fileWriter.write("</b></td>");
				}
			} else {
				for (int j = 0; j < this.allTasks.get(i).length; j++) {
					fileWriter.write("<td>");
					fileWriter.write(this.allTasks.get(i)[j]);
					fileWriter.write("</td>");
				}
			}
			fileWriter.write("</tr>");
		}
		
		
		fileWriter.write("</table>");
		fileWriter.write("</body>");
		fileWriter.write("</html>");
		
		fileWriter.close();
	}
}
