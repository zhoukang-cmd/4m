package com.fourm.client.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

//TODO 若不再使用此类，请删除
@Deprecated
public class FourmClientScheduleListener implements org.quartz.SchedulerListener , org.quartz.JobListener {

	Log log = LogFactory.getLog(FourmClientScheduleListener.class);

	public void jobScheduled(Trigger trigger) {
		String jobName = trigger.getJobName();
		log.info(jobName + " has been scheduled");
	}

	public void jobUnscheduled(String triggerName, String triggerGroup) {

		if (triggerName == null) {
			// triggerGroup is being unscheduled
			log.info(triggerGroup + " is being unscheduled");
		} else {
			log.info(triggerName + " is being unscheduled");
		}
	}

	public void triggerFinalized(Trigger trigger) {
		String jobName = trigger.getJobName();
		log.info("Trigger is finished for " + jobName);
	}

	public void triggersPaused(String triggerName, String triggerGroup) {

		if (triggerName == null) {
			// triggerGroup is being unscheduled
			log.info(triggerGroup + " is being paused");
		} else {
			log.info(triggerName + " is being paused");
		}
	}

	public void triggersResumed(String triggerName, String triggerGroup) {

		if (triggerName == null) {
			// triggerGroup is being unscheduled
			log.info(triggerGroup + " is now resuming");
		} else {
			log.info(triggerName + " is now resuming");
		}
	}

	public void jobsPaused(String jobName, String jobGroup) {
		if (jobName == null) {
			// triggerGroup is being unscheduled
			log.info(jobGroup + " is pausing");
		} else {
			log.info(jobName + " is pausing");
		}
	}

	public void jobsResumed(String jobName, String jobGroup) {
		if (jobName == null) {
			// triggerGroup is being unscheduled
			log.info(jobGroup + " is now resuming");
		} else {
			log.info(jobName + " is now resuming");
		}
	}

	public void schedulerError(String msg, SchedulerException cause) {
		log.error(msg, cause.getUnderlyingException());
	}

	public void schedulerShutdown() {
		log.info("Scheduler is being shutdown");
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void jobExecutionVetoed(JobExecutionContext ctx) {
		// TODO Auto-generated method stub
		
	}

	public void jobToBeExecuted(JobExecutionContext ctx) {
		// TODO Auto-generated method stub
		
	}

	public void jobWasExecuted(JobExecutionContext ctx, JobExecutionException ex) {
		// TODO Auto-generated method stub
		
	}
}
