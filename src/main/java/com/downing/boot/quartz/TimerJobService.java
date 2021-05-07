package com.downing.boot.quartz;

import com.downing.boot.constant.ScheduleConstants;
import com.downing.boot.entity.SysJob;
import com.downing.boot.exception.TaskException;
import com.downing.boot.mapper.SysJobMapper;
import com.downing.boot.utils.ScheduleUtils;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author downing
 * @desc
 * @date 2021/1/18 16:53
 */
@Service
public class TimerJobService {

    @Autowired
    private SysJobMapper jobMapper;

    @Autowired
    private Scheduler scheduler;

    public List<SysJob> listTask() throws SchedulerException {
        List<SysJob> jobs = new ArrayList<SysJob>();
        //再获取Scheduler下的所有group
        List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
        for (String groupName : triggerGroupNames) {
            //组装group的匹配，为了模糊获取所有的triggerKey或者jobKey
            GroupMatcher groupMatcher = GroupMatcher.groupEquals(groupName);
            //获取所有的triggerKey
            Set<TriggerKey> triggerKeySet = scheduler.getTriggerKeys(groupMatcher);
            for (TriggerKey triggerKey : triggerKeySet) {
                //通过triggerKey在scheduler中获取trigger对象
                CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                //获取trigger拥有的Job
                JobKey jobKey = trigger.getJobKey();
                JobDetailImpl jobDetail = (JobDetailImpl) scheduler.getJobDetail(jobKey);
                //组装页面需要显示的数据
                SysJob quartzJobsVO = new SysJob();
                quartzJobsVO.setJobGroup(groupName);
                quartzJobsVO.setJobName(jobDetail.getName());
                quartzJobsVO.setCronExpression(trigger.getCronExpression());
                jobs.add(quartzJobsVO);
            }
        }
        return jobs;
    }

    public int addTask(SysJob job) throws TaskException, SchedulerException {
        //参数校验
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = jobMapper.insert(job);
        if (rows > 0) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
        return rows;
    }
}
