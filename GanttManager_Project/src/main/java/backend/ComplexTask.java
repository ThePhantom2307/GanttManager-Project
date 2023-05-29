package backend;
import java.util.ArrayList;
import java.util.List;

public class ComplexTask extends Task{
	private ArrayList<SimpleTask> subTasks;
	
	public ComplexTask(String taskName, int mamaID, int cost, int id, int startTime, int finishTime) {
		super(taskName, mamaID, cost, id, startTime, finishTime);
		this.subTasks = new ArrayList<SimpleTask>();
	}
	
	public void addSubTask(SimpleTask task) {
		this.subTasks.add(task);
	}
	
	public ArrayList<SimpleTask> getSubTasks() {
		return this.subTasks;
	}
	
	public void setSubTasks(List<SimpleTask> tasks) {
		this.subTasks = (ArrayList<SimpleTask>) tasks;
	}
	
	private void setCost(int cost) {
		super.cost = cost;
	}
	
	private void setStartTime(int startTime) {
		super.startTime = startTime;
	}
	
	private void setFinishTime(int finishTime) {
		super.finishTime = finishTime;
	}
	
	public void calculateCost() {
		int calculatedCost = 0;
		SimpleTask task;
		
		for(int i = 0; i < this.subTasks.size(); i++) {
			task = this.subTasks.get(i);
			calculatedCost += task.getCost();
		}
		this.setCost(calculatedCost);
	}
	
	public void calculateStartFinishTime() {
		int findStartTime = 0;
		int findFinishTime = 0;
		SimpleTask task;
		
		for(int i = 0; i < this.subTasks.size(); i++) {
			task = this.subTasks.get(i);
			if (i == 0) {
				findStartTime = task.getStartTime();
				findFinishTime = task.getFinishTime();
			}
			if (task.getStartTime() < findStartTime) {
				findStartTime = task.getStartTime();
			}
				
			if (task.getFinishTime() > findFinishTime) {
				findFinishTime = task.getFinishTime();
			}
		}
		this.setStartTime(findStartTime);
		this.setFinishTime(findFinishTime);
	}
	
}