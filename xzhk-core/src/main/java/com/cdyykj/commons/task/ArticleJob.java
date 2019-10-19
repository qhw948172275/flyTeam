package com.cdyykj.commons.task;

import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.xzhk.entity.Article;
import com.cdyykj.xzhk.response.ArticleMemberResponse;
import com.cdyykj.xzhk.service.ArticleService;
import com.cdyykj.xzhk.tool.WxCpServiceTool;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 限时练习定时任务
 */
@Component
public class ArticleJob implements Job {

    @Autowired
    ArticleService articleService;
    @Autowired
    WxCpServiceTool wxCpServiceTool;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            /**
             * 从当前任务获取文章对象
             */
            String  articlestr =  (String) jobExecutionContext.getJobDetail().getJobDataMap().get("object");
            String type = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("jobOperateType");
            ArticleMemberResponse article=JsonUtils.jsonToBean(articlestr, ArticleMemberResponse.class);
            if(Integer.parseInt(type)==0){
                //指定定时发布文章
                articleService.updateSendTime(article,jobExecutionContext.getFireTime());
                wxCpServiceTool.sendMessageArticle(article);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
