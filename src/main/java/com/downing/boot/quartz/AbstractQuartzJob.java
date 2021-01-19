package com.downing.boot.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author downing
 * @desc 任务类
 * @date 2021/1/18 18:28
 */
public class AbstractQuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务执行了...");
    }
}
