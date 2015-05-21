/**
 * 
 */
package org.app.ds.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author anandm
 * 
 */
public class ProcessSchedulerReport implements IProcessSchedulerReport {

	private long startedAt;
	private long stoopedAt;
	private Map<String, IProcessReport> processReportMap;
	private long idleTime;

	/**
	 * 
	 */
	public ProcessSchedulerReport() {
		super();
		processReportMap = new HashMap<String, IProcessReport>();

	}

	public void setStartedAt(long startedAt) {
		this.startedAt = startedAt;
	}

	public void setStoopedAt(long stoopedAt) {
		this.stoopedAt = stoopedAt;
	}

	public void setIdleTime(long idleTime) {
		this.idleTime = idleTime;
	}

	public void putProcessReport(IProcessReport processReport) {
		processReportMap.put(processReport.getProcessId(), processReport);
	}

	@Override
	public long getStartedAt() {

		return startedAt;
	}

	@Override
	public long getStoppedAt() {

		return stoopedAt;
	}

	@Override
	public int getCountOfExecutedProcess() {

		return processReportMap.size();
	}

	@Override
	public long getIdleTime() {

		return idleTime;
	}

	@Override
	public List<IProcessReport> getProcessReports() {

		return new ArrayList<IProcessReport>(processReportMap.values());
	}

	@Override
	public double getAverageWaitingTime() {
		long totalWaitingTime = 0;

		for (IProcessReport processReport : getProcessReports()) {
			totalWaitingTime = totalWaitingTime
					+ processReport.getWaitingTime();
		}

		return totalWaitingTime / getCountOfExecutedProcess();
	}

	@Override
	public ProcessReport getProcessReport(String id) {

		return null;
	}

}
