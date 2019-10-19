package com.cdyykj.commons.task;

import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.xzhk.entity.Article;
import com.cdyykj.xzhk.entity.TestSystem;
import com.cdyykj.xzhk.service.ArticleService;
import com.cdyykj.xzhk.service.TestSystemService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 限时练习定时任务
 */
@Component
public class TestSystemJob implements Job {

    @Autowired
    TestSystemService testSystemService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            /**
             * 从当前任务获取测试系统对象
             */
            String  articlestr =  (String) jobExecutionContext.getJobDetail().getJobDataMap().get("object");
            String type = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("jobOperateType");
            TestSystem testSystem = JsonUtils.jsonToBean(articlestr, TestSystem.class);

            if(Integer.parseInt(type)==0){
                //指定定时发布测试系统
                testSystemService.updateSendTime(testSystem,jobExecutionContext.getFireTime());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
