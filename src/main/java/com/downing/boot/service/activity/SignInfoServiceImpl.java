package com.downing.boot.service.activity;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.downing.boot.entity.SignInfo;
import com.downing.boot.exception.LogicException;
import com.downing.boot.mapper.SignInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author downing
 * @desc 签到
 * @date 2020/7/21 15:47
 */
@Service
public class SignInfoServiceImpl implements SignInfoService {

    @Autowired
    private SignInfoMapper signInfoMapper;

    @Override
    public SignInfo signIn() throws Exception {
        Integer uid = 1;
        QueryWrapper<SignInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", uid);
        SignInfo signInfo = signInfoMapper.selectOne(wrapper);
        if (signInfo == null) {
            SignInfo insertSign = new SignInfo();
            insertSign.setUserId(uid);
            insertSign.setContinueSign(1);
            insertSign.setUpdateTime(new Date());
            insertSign.setCreateTime(new Date());
            signInfoMapper.insert(insertSign);
            return insertSign;
        } else {
            // 判断最后签到日期与当前日期是否超过一天
            LocalDate nowTime = LocalDate.now();
            LocalDate updateTime = signInfo.getUpdateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long diffDay = ChronoUnit.DAYS.between(updateTime, nowTime);
            if (diffDay <= 0) {
                throw new LogicException("已经签到过了哦~");
            }
            if (diffDay > 1) {
                // 1, 超过一天, 把连续签到的天数重置为 1
                signInfo.setContinueSign(1);
            } else {
                // 2, 没有超过一天, 把连续签到的天数+1
                signInfo.setContinueSign(signInfo.getContinueSign() + 1);
            }
            signInfo.setUpdateTime(new Date());
            signInfoMapper.updateById(signInfo);

        }
        return signInfo;
    }
}
