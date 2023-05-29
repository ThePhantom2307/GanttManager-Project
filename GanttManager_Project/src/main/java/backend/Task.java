package backend;

public abstract class Task {
	private String taskName;
	private int mamaID;
	public int cost;
	private int id;
	public int startTime;
	public int finishTime;
	
	Task(String taskName, int mamaID, int cost, int id, int startTime, int finishTime) {
		this.taskName = taskName;
		this.mamaID = mamaID;
		this.cost = cost;
		this.id = id;
		this.startTime = startTime;
		this.finishTime = finishTime;
		
	}

	public String getTaskName() {
		return this.taskName;
	}
	
	public int getMamaID() {
		return this.mamaID;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public int getID() {
		return this.id;
	}
	
	public int getStartTime() {
		return this.startTime;
	}
	
	public int getFinishTime() {
		return this.finishTime;
	}
	
	public Boolean checkTopLevel() {
		if(this.mamaID == 0) {
			return true;
		}
		return false;
	}
	
	public Boolean checkComplex() {
		if(this.getClass().getName().equals("backend.ComplexTask")) {
			return true;
		}else {
			return false;
		}
	}
	
	public String[] getFields() {
		String[] fields = new String[6];
		
		fields[0] = Integer.toString(this.getID());
		fields[1] = this.taskName;
		fields[2] = Integer.toString(this.getMamaID());
		fields[3] = Integer.toString(this.getStartTime());
		fields[4] = Integer.toString(this.getFinishTime());
		fields[5] = Integer.toString(this.getCost());
		
		return fields;
	}
}
