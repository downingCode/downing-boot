package com.downing.boot.quartz;

import com.downing.boot.common.DowningResult;
import com.downing.boot.entity.SysJob;
import com.downing.boot.exception.TaskException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author downing
 * @desc
 * @date 2021/1/18 16:54
 */
@RestController
@RequestMapping("/api/timer")
public class TimerJobController {

    @Autowired
    private TimerJobService timerJobService;

    @GetMapping("/")
    public DowningResult getList() throws SchedulerException {
        return new DowningResult("获取成功", timerJobService.listTask());
    }

    @PostMapping("/add")
    public DowningResult add(@RequestBody SysJob sysJob) throws TaskException, SchedulerException {
        timerJobService.addTask(sysJob);
        return new DowningResult("添加成功");
    }
}
