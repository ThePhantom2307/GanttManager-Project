package backend;

import dom2app.SimpleTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import backend.display.*;
import backend.savefile.SaveHTML;
import backend.savefile.SaveMarkdown;
import backend.savefile.SaveText;

public class MainController implements IMainController {
	
	private LoadFromText loadFromText;
	private String[] columnNames = {"ID", "Task", "MamaID", "StartTime", "FinishTime", "Cost"};
	private ArrayList<String[]> allTasks;
	
	public MainController() {
		 this.loadFromText = null;
		 this.allTasks = new ArrayList<String[]>();
	}
	
	@Override
	public SimpleTableModel load(String fileName, String delimiter) {
		this.loadFromText = new LoadFromText(fileName, delimiter);
		ArrayList<String[]> allTasks = new ArrayList<String[]>();
		ArrayList<Task> topLevelTasks = null;
		File file = null;
		ComplexTask complexTask;
		
		try {
			file = loadFromText.loadFile();
			if (file == null) {
				JFrame frame = new JFrame("Error");
				String message = "The type of the file that loaded is not a tsv file";
				JOptionPane.showMessageDialog(frame, message,"Error", JOptionPane.ERROR_MESSAGE);
				return new SimpleTableModel("All Tasks", "Load From Text", columnNames, new ArrayList<String[]>());
			}
			topLevelTasks = this.loadFromText.getTopLevelFromFile(file);
			this.loadFromText.getSubTasksFromFile(file, topLevelTasks);
		
			for (Task task: topLevelTasks) {
				if (task.checkComplex()) {
					complexTask = (ComplexTask) task;
					complexTask.calculateCost();
					complexTask.calculateStartFinishTime();
				}
			}
			
			this.loadFromText.sortTopLevelTasks(topLevelTasks);
			
			for(Task topLevel: topLevelTasks) {
				allTasks.add(topLevel.getFields());
				if (topLevel.checkComplex()) {
					complexTask = (ComplexTask) topLevel;
					this.loadFromText.sortSubTasks(complexTask);
					for(SimpleTask subTask: complexTask.getSubTasks()) {
						allTasks.add(subTask.getFields());
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.allTasks = allTasks;
		
		// Change it to return a SimpleTableModel
		return new SimpleTableModel("All Tasks", "Load From Text", columnNames, allTasks);
	}

	@Override
	public SimpleTableModel getTasksByPrefix(String prefix) {
		DisplayByPrefix display = new DisplayByPrefix(this.allTasks);
		ArrayList<String[]> tasksByPrefix = display.SearchTaskToDisplay(prefix);
		return new SimpleTableModel("Tasks By Prefix", prefix, columnNames, tasksByPrefix);
	}

	@Override
	public SimpleTableModel getTaskById(int id) {
		DisplayByID display = new DisplayByID(this.allTasks);
		ArrayList<String[]> tasksByID = display.SearchTaskToDisplay(id);
		return new SimpleTableModel("Tasks By ID", Integer.toString(id), columnNames, tasksByID);
	}

	@Override
	public SimpleTableModel getTopLevelTasks() {
		DisplayTopLevel display = new DisplayTopLevel(this.allTasks);
		ArrayList<String[]> topLevelTasks = display.SearchTaskToDisplay(0);
		return new SimpleTableModel("Tasks", "Top Level", columnNames, topLevelTasks);
	}

	@Override
	public int createReport(String path, ReportType type) {
		if (type == ReportType.HTML) {
			SaveHTML reportHTML = new SaveHTML(path, this.allTasks);
			try {
				reportHTML.saveTasks();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (type == ReportType.MD) {
			SaveMarkdown reportMD = new SaveMarkdown(path, this.allTasks);
			try {
				reportMD.saveTasks();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (type == ReportType.TEXT) {
			SaveText reportTEXT = new SaveText(path, this.allTasks);
			try {
				reportTEXT.saveTasks();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
