package org.app.ds.others;


public interface IProcessReport {

	String getProcessId();

	long getArrivedAt();

	long getStartedAt();

	long getFinishedAt();

	long getWaitingTime();

}
