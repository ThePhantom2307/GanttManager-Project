package backend;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class LoadFromText {
	private String filename;
	private String delimiter;

	// Constructor
	LoadFromText(String filename, String delimiter) {
		this.filename = filename;
		this.delimiter = delimiter;
	}

	// Load a file with a filename
	public File loadFile() throws FileNotFoundException {
		if (!this.filename.substring(this.filename.length() - 3).equals("tsv")) {
			return null;
		}
		File file = new File(this.filename);
		return file;
	}

	// Read the text file and got the Top-Level tasks
	public ArrayList<Task> getTopLevelFromFile(File delimitedFile) throws FileNotFoundException {
		Scanner scanner = new Scanner(delimitedFile);
		ArrayList<Task> topLevel = new ArrayList<Task>();
		String line;
		String[] fields;
		Task topLevelTask;
		
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			fields = line.split(delimiter);
			
			if (fields[2].equals("0")) {
				topLevelTask = this.createTask(fields);
				topLevel.add(topLevelTask);
			}
			
		}
		
		scanner.close();
		return topLevel;
	}

	// Reading the text file and got the subtasks for each complex Top-Level task
	public void getSubTasksFromFile(File delimitedFile, ArrayList<Task> topLevel) throws FileNotFoundException{
		Scanner scanner;
		String line;
		String[] fields;
		SimpleTask simpleTask;
		ComplexTask complexTask;
		
		for (int i = 0; i < topLevel.size(); i++) {
			if (topLevel.get(i).checkComplex()) {
				scanner = new Scanner(delimitedFile);
				complexTask = (ComplexTask) topLevel.get(i);

				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					fields = line.split(delimiter);
					if (!fields[2].equals("0") && complexTask.getID() == Integer.parseInt(fields[2])) {
						simpleTask = (SimpleTask) this.createTask(fields);
						complexTask.addSubTask(simpleTask);
					}
				}
				scanner.close();
			}
		}
	}
	
	//Transfer all methods bellow to Task class
	// Creates a task item
	private Task createTask(String[] fields) {
		String taskName = "";
		int id = 0;
		int mamaID = 0;
		int cost = 0;
		int startTime = 0;
		int finishTime = 0;
		int counter = 0;
		
		for (String field: fields) {
			switch (counter) {
				case 0:
					id = Integer.parseInt(field);
					break;
				case 1:
					taskName = field;
					break;
				case 2:
					mamaID = Integer.parseInt(field);
					break;
				case 3:
					startTime = Integer.parseInt(field);
					break;
				case 4:
					finishTime = Integer.parseInt(field);
					break;
				case 5:
					cost = Integer.parseInt(field);
					break;
			}
			counter++;
		}
		if (fields.length == 3) {
			return new ComplexTask(taskName, mamaID, cost, id, startTime, finishTime);
		} else {
			return new SimpleTask(taskName, mamaID, cost, id, startTime, finishTime);
		}
	}
	
	
	// Sorts the Top-Level tasks
	public ArrayList<Task> sortTopLevelTasks(ArrayList<Task> tasks) {
		Task currentTask;
		Task task;
		Task temp;

		for (int i = 0; i < tasks.size(); i++) {
			for (int j = 1; j < (tasks.size()-i); j++) {
				task = tasks.get(j-1);
				currentTask = tasks.get(j);
				if (currentTask.getStartTime() < task.getStartTime()) {
					temp = tasks.get(j);
					tasks.set(j, tasks.get(j-1));
					tasks.set(j-1, temp);
				} else if ((currentTask.getStartTime() == task.getStartTime()) && currentTask.getID() < task.getID()) {
					temp = tasks.get(j);
					tasks.set(j, tasks.get(j-1));
					tasks.set(j-1, temp);
				}
			}
		}
		return tasks;
	}
	
	
	// Sorts the subTasks of each complex Top-Level task
	public void sortSubTasks(ComplexTask complexTask) {
		ArrayList<SimpleTask> tasks = complexTask.getSubTasks();
		SimpleTask currentTask;
		SimpleTask temp;
		Task prevTask;
		
		for (int i = 0; i < tasks.size(); i++) {
			for (int j = 1; j < (tasks.size()-i); j++) {
				prevTask = tasks.get(j-1);
				currentTask = tasks.get(j);
				if (currentTask.getStartTime() < prevTask.getStartTime()) {
					temp = tasks.get(j);
					tasks.set(j, (SimpleTask) prevTask);
					tasks.set(j-1, temp);
				} else if ((currentTask.getStartTime() == prevTask.getStartTime()) && currentTask.getID() < prevTask.getID()) {
					temp = currentTask;
					tasks.set(j, (SimpleTask) prevTask);
					tasks.set(j-1, temp);
				}
			}
		}
		complexTask.setSubTasks(tasks);
	}
}
	
