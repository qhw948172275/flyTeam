package com.cdyykj.commons.task;

import com.cdyykj.system.commons.CalendarUtils;
import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.xzhk.entity.Article;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobUtils {
    private Logger log= LoggerFactory.getLogger(JobUtils.class);
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    public  static final String LIMIT="limit";
    public  static final String SPECIAL="special";


    /**
     * 创建文章定时任务
     * @param className
     * @param groupName
     * @param jobTime
     * @throws Exception
     */
    public void addCron(String className, String groupName, Date jobTime, String objectToJson, Integer jobOperateType)throws Exception {
        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(className).getClass()).
                withIdentity(className, groupName)
                .build();
        jobDetail.getJobDataMap().put("object", objectToJson);
        jobDetail.getJobDataMap().put("jobOperateType",jobOperateType+"");//任务操作类型： 0-开始，1-结束
        //表达式调度构建器(即任务执行的时间)
        String cronExpression= CalendarUtils.dateToString(jobTime,CalendarUtils.CRON_DATE_FORMAT);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().
                withIdentity(className, groupName)
                .withSchedule(scheduleBuilder)
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            log.error("创建定时任务失败" + e);
        }
    }

    public static Job getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (Job) class1.newInstance();
    }


    /**
     * 删除定时任务
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    public void jobDelete(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

}
