/**
 * 
 */
package org.app.ds.others;

/**
 * @author anandm
 * 
 */
public interface ProcessScheduler {

	void run();

	void schedule(Process process);

	IProcessSchedulerReport getProcessSchedulerReport();

}
