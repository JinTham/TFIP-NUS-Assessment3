package ibf2022.paf.assessment.server.services;

import ibf2022.paf.assessment.server.models.Task;

public class InsertException extends Exception {
    
    private Task task;

    public InsertException() {
		super();
	}

	public InsertException(String msg) {
		super(msg);
	}

	public void setTransferInfo(Task taskInfo) { this.task = task; }
	public Task getTransferInfo() { return this.task; }

}
