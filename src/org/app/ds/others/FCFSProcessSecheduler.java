/**
 * 
 */
package org.app.ds.others;

import java.util.Queue;

import org.app.ds.queue.IQueue;

/**
 * @author anandm
 * 
 */
public class FCFSProcessSecheduler implements ProcessScheduler {

	private boolean running;

	private Queue<Process> queue

	private ProcessSchedulerReport processSchedulerReport;

	@Override
	public void schedule(Process process) {
		if (!isRunning()) {
			throw new IllegalStateException(
					"either not started or already stopped");
		}

		IProcessReport processReport = new ProcessReport(process.getId(),
				System.currentTimeMillis());

		processSchedulerReport.putProcessReport(processReport);

		queue.enqueue(process);
	}

	@Override
	public IProcessSchedulerReport getProcessSchedulerReport() {
		return processSchedulerReport;
	}

	public void run() {
		Process nextProcess = queue.getFront();
		if (nextProcess != null) {

		}
		ProcessReport processReport = processSchedulerReport
				.getProcessReport(nextProcess.getId());

		nextProcess.execute();

		processReport.setFinishedAt(System.currentTimeMillis());
	}

}
