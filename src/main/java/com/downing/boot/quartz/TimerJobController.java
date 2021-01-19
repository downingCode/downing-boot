package com.downing.boot.quartz;

import com.downing.boot.common.DowningResult;
import com.downing.boot.entity.SysJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public DowningResult getList(){
        return new DowningResult("");
    }

    public DowningResult add(@RequestBody SysJob sysJob){

        return new DowningResult("");
    }
}
