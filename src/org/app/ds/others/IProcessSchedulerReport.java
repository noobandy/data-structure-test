/**
 * 
 */
package org.app.ds.others;

import java.util.List;

/**
 * @author anandm
 * 
 */
public interface IProcessSchedulerReport {

	long getStartedAt();

	long getStoppedAt();

	int getCountOfExecutedProcess();

	long getIdleTime();

	ProcessReport getProcessReport(String id);
	
	List<IProcessReport> getProcessReports();

	double getAverageWaitingTime();
}
