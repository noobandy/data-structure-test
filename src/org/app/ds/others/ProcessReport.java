/**
 * 
 */
package org.app.ds.others;

/**
 * @author anandm
 * 
 */
public class ProcessReport implements IProcessReport {

	private String id;
	private long arrivedAt;
	private long startedAt;
	private long finishedAt;
	private long waitingTime;

	/**
	 * @param id
	 * @param arrivedAt
	 */
	public ProcessReport(String id, long arrivedAt) {
		super();
		this.id = id;
		this.arrivedAt = arrivedAt;
	}

	public String getId() {
		return id;
	}

	public void setArrivedAt(long arrivedAt) {
		this.arrivedAt = arrivedAt;
	}

	public void setStartedAt(long startedAt) {
		this.startedAt = startedAt;
	}

	public void setFinishedAt(long finishedAt) {
		this.finishedAt = finishedAt;
	}

	public void setWaitingTime(long waitingTime) {
		this.waitingTime = waitingTime;
	}

	@Override
	public String getProcessId() {

		return id;
	}

	@Override
	public long getArrivedAt() {

		return arrivedAt;
	}

	@Override
	public long getStartedAt() {

		return startedAt;
	}

	@Override
	public long getFinishedAt() {

		return finishedAt;
	}

	@Override
	public long getWaitingTime() {

		return waitingTime;
	}

}
