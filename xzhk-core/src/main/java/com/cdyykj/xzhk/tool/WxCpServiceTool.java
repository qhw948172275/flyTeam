package com.cdyykj.xzhk.tool;

import com.cdyykj.system.commons.SystemConstants;
import com.cdyykj.xzhk.entity.FlightReport;
import com.cdyykj.xzhk.response.ArticleMemberResponse;
import com.cdyykj.xzhk.response.MemberResponse;
import com.cdyykj.xzhk.service.DepartmentMemberService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.WxCpMessageSendResult;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdyykj.xzhk.config.WxCpServiceConfig;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceApacheHttpClientImpl;
import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import java.util.ArrayList;
import java.util.List;

@Component
public class WxCpServiceTool {

    private Logger log= LoggerFactory.getLogger(WxCpServiceTool.class);
    @Autowired
    WxCpServiceConfig wxCpServiceConfig;
    @Autowired
    DepartmentMemberService departmentMemberService;

    /**
     * 获取用户得userId
     * @return
     */
    public static String getUserI(WxCpService wxCpService,String code){
        try {
            WxCpOauth2UserInfo userInfo = wxCpService.getOauth2Service().getUserInfo(code);
            if(null != userInfo.getUserId()) {
            	return userInfo.getUserId();
            }
            String userId = wxCpService.getUserService().openid2UserId(userInfo.getOpenId());
            return userId;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取微信工具server---通讯录
     * @return
     */
    public WxCpService getWxCpService(){
        WxCpConfigStorage wxCpConfigStorage=new WxCpDefaultConfigImpl();
        ((WxCpDefaultConfigImpl) wxCpConfigStorage).setCorpId(wxCpServiceConfig.getCorpid());
        ((WxCpDefaultConfigImpl) wxCpConfigStorage).setCorpSecret(wxCpServiceConfig.getCorpsecret());
        ((WxCpDefaultConfigImpl) wxCpConfigStorage).setToken(wxCpServiceConfig.getToken());
        ((WxCpDefaultConfigImpl) wxCpConfigStorage).setAesKey(wxCpServiceConfig.getAesKey());
        WxCpService wxCpService = new WxCpServiceApacheHttpClientImpl();
        wxCpService.setWxCpConfigStorage(wxCpConfigStorage);
        return wxCpService;
    }

    /**
     * 根据传入的密钥创建WxCpService
     * @param secret
     * @return
     */
    public WxCpService getWxCpService(String secret){
        WxCpConfigStorage wxCpConfigStorage=new WxCpDefaultConfigImpl();
        ((WxCpDefaultConfigImpl) wxCpConfigStorage).setCorpId(wxCpServiceConfig.getCorpid());
        ((WxCpDefaultConfigImpl) wxCpConfigStorage).setCorpSecret(secret);
        WxCpService wxCpService = new WxCpServiceApacheHttpClientImpl();
        wxCpService.setWxCpConfigStorage(wxCpConfigStorage);
        return wxCpService;
    }

    /**
     * 文章发送图文消息
     */
    public void sendMessageArticle(ArticleMemberResponse articleMemberResponse){
        try {
            WxCpMessage wxCpMessage=new WxCpMessage();
            StringBuffer stringBuffer=new StringBuffer();//成员userId
            int memberLength=articleMemberResponse.getMemberResponses().size();
            for(int i=0;i<memberLength;i++){
               MemberResponse memberResponse1=articleMemberResponse.getMemberResponses().get(i);
               stringBuffer.append(memberResponse1.getUserId());
               if(i+1<memberLength){
                   stringBuffer.append("|");
               }
            }
            wxCpMessage.setToUser(stringBuffer.toString());
            NewArticle newArticle=new NewArticle();
            newArticle.setUrl(SystemConstants.ARTICLE_DETAIL_URL+articleMemberResponse.getId());
            newArticle.setDescription(articleMemberResponse.getContent());
            newArticle.setTitle(articleMemberResponse.getTitle());
            newArticle.setPicUrl(articleMemberResponse.getCover());
            List<NewArticle> newArticles=new ArrayList<>();
            newArticles.add(newArticle);
            wxCpMessage.setArticles(newArticles);
            this.sendMessage(wxCpMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 发送图文消息
     */
    public void sendMessageFlightReport(FlightReport flightReport){
        try {
           List<String> userIds= departmentMemberService.selectUserId();

            StringBuffer stringBuffer=new StringBuffer();//成员userId
            int memberLength=userIds.size();
            for(int i=0;i<memberLength;i++){
                stringBuffer.append(userIds.get(i));
                if(i+1<memberLength){
                    stringBuffer.append("|");
                }
            }
            WxCpMessage   wxCpMessage=new WxCpMessage();
            wxCpMessage.setToUser(stringBuffer.toString());
            NewArticle newArticle=new NewArticle();
            newArticle.setUrl(SystemConstants.FLIGHT_REPORT_DETAIL_URL+flightReport.getId());
            newArticle.setDescription(flightReport.getEventContent());
            newArticle.setTitle(flightReport.getTitle());
            List<NewArticle> newArticles=new ArrayList<>();
            newArticles.add(newArticle);
            wxCpMessage.setArticles(newArticles);
            this.sendMessage(wxCpMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 发送信息
     * @param wxCpMessage
     */
    private void sendMessage(WxCpMessage wxCpMessage) throws WxErrorException {
        WxCpService wxCpService=getWxCpService(SystemConstants.SYSTEM_SECRET);
        wxCpMessage.setAppId(wxCpServiceConfig.getCorpid());
        wxCpMessage.setMsgType(WxConsts.MaterialType.NEWS);
        wxCpMessage.setAgentId(SystemConstants.AGRENT_ID);
        WxCpMessageSendResult wxCpMessageSendResult= wxCpService.messageSend(wxCpMessage);
        log.info(wxCpMessageSendResult.getErrCode().toString());
        log.info(wxCpMessageSendResult.getErrMsg());
    }
}
