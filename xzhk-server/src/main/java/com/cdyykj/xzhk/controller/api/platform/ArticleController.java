package com.cdyykj.xzhk.controller.api.platform;

import com.cdyykj.commons.OutPutFolderConfig;
import com.cdyykj.commons.excel.BaseUtils;
import com.cdyykj.commons.task.JobUtils;
import com.cdyykj.system.commons.CalendarUtils;
import com.cdyykj.system.commons.ConvertUtils;
import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.xzhk.controller.api.BaseController;
import com.cdyykj.xzhk.entity.AnnexManage;
import com.cdyykj.xzhk.entity.Article;
import com.cdyykj.xzhk.response.ArticleMemberResponse;
import com.cdyykj.xzhk.response.MemberReadDetailResponse;
import com.cdyykj.xzhk.response.MemberResponse;
import com.cdyykj.xzhk.service.AnnexManageService;
import com.cdyykj.xzhk.service.ArticleService;
import com.cdyykj.xzhk.service.MemberArticleService;
import com.cdyykj.xzhk.service.RAnnexApplyService;
import com.cdyykj.xzhk.tool.WxCpServiceTool;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/article")
public class ArticleController extends BaseController {
    @Autowired
    ArticleService articleService;
    @Autowired
    JobUtils jobUtils;
    @Autowired
    MemberArticleService memberArticleService;
    @Autowired
    OutPutFolderConfig outPutFolderConfig;
    @Autowired
    AnnexManageService annexManageService;
    @Autowired
    WxCpServiceTool wxCpServiceTool;
    @Autowired
    RAnnexApplyService rAnnexApplyService;

    private static String[] MEMB_READ_DETAIL_TILE={"姓名","所属部门","开始阅读时间","停止阅读时间","是否成功阅读","IP地址"};
    /**
     * 文章列表
     * @param keyword
     * @param dateStr
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "index")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "标题或ID",name="keyword"),
            @ApiImplicitParam(value = "日期格式yyyy-MM-dd",name="dateStr")
    })
    @ApiOperation(value = "文章列表",response = Article.class)
    public JsonResult index( String  keyword,String dateStr, @RequestParam(required =false,defaultValue = "1") int page
            , @RequestParam(required =false,defaultValue = "20") int limit){
        try {
            ObjectNode json= JsonUtils.getMapperInstance().createObjectNode();
            PageHelper.startPage(page,limit);
            if(StringUtils.isNotEmpty(keyword)){
                keyword="%"+keyword+"%";
            }else {
                keyword=null;
            }
           if(StringUtils.isEmpty(dateStr)){
               dateStr=null;
           }
            List<Article> articleList=articleService.selectByKeyword(keyword,dateStr);
            json.putPOJO("articleList",new PageInfo<>(articleList));
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 保存文章
     * @param articleMemberResponse
     * @return
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "保存文章")
    public JsonResult save(@RequestBody ArticleMemberResponse articleMemberResponse){
            try {
                ObjectNode json= articleService.save(memberArticleService,rAnnexApplyService,articleMemberResponse,getSystemUser());
               if(json.get("result").textValue().equals("ok")){
                   return JsonResultUtils.buildJsonOK(json);
               }else{
                   return JsonResultUtils.buildJsonFail(json);
               }
            }catch (Exception e){
                e.printStackTrace();
                return JsonResultUtils.buildJsonFailMsg(e.getMessage());
            }
    }

    /**
     * 编辑文章保存
     * @return
     */
    @ApiOperation(value="编辑文章保存")
    @PostMapping(value = "editSave")
    public JsonResult editSave(@RequestBody ArticleMemberResponse articleMemberResponse){
            try {

              ObjectNode json=articleService.editSave(memberArticleService,rAnnexApplyService,articleMemberResponse,getSystemUser());
                if(json.get("result").textValue().equals("ok")){
                    return JsonResultUtils.buildJsonOK();
                }
                return JsonResultUtils.buildJsonFail(json);
            }catch (Exception e){
                e.printStackTrace();
                return JsonResultUtils.buildJsonFailMsg(e.getMessage());
            }
    }

    @ApiOperation(value = "根据文章ID获取文章",response = ArticleMemberResponse.class)
    @GetMapping(value = "getArticleById")
    public JsonResult getArticleById(@RequestParam Integer articleId){
        try {
            ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
            Article article=articleService.selectArticleByArticleId(articleId);
            ArticleMemberResponse articleMemberResponse=new ArticleMemberResponse();
            BeanUtils.copyProperties(article,articleMemberResponse);
            List<MemberResponse> memberResponses=memberArticleService.selectMemberResponseByArticleId(articleId);
            articleMemberResponse.setMemberResponses(memberResponses);
            List<AnnexManage> annexManages=annexManageService.selectAnnexManage(articleId,0);
            articleMemberResponse.setAnnexManages(annexManages);
            json.putPOJO("articleMemberResponse",articleMemberResponse);
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    @ApiOperation(value = "删除文章")
    @GetMapping(value = "delArticle")
    public JsonResult delArticle(@RequestParam Integer articleId){
        try {
            Article article=new Article();
            article.setId(articleId);
            article.setStatus((byte)1);//1表示删除
            articleService.updateById(article);
            return JsonResultUtils.buildJsonOK();
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 阅读详情
     * @param articleId
     * @return
     */
    @ApiOperation(value="阅读详情",response = MemberReadDetailResponse.class)
    @GetMapping(value = "readDetail")
    public JsonResult readDetail(@RequestParam Integer articleId,String keyword,
                                 @RequestParam(required =false,defaultValue = "1") int page
            , @RequestParam(required =false,defaultValue = "20") int limit){
        try {
            ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
            PageHelper.startPage(page,limit);
            List<MemberReadDetailResponse> memberReadDetailResponses=memberArticleService.selectMemberReadDetailResponse(articleId,keyword);
            json.putPOJO("memberReadDetailResponses",new PageInfo<>(memberReadDetailResponses));
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 获取导出的文件名
     * @param articleId
     * @return
     */
    @GetMapping(value = "getFileName")
    @ApiOperation(value = "获取导出的文件名")

    @ApiImplicitParams({
            @ApiImplicitParam(value = "文章ID",name="articleId"),
            @ApiImplicitParam(value = ",拼接id",name="ids")
    })
    public JsonResult getFileName(@RequestParam Integer articleId,String ids){
        try {
            ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
            List<Integer> idIs=new ArrayList<>();
            List<MemberReadDetailResponse> memberReadDetailResponseList=new ArrayList<>();
            if(StringUtils.isNotEmpty(ids)){
                List<Integer> ints= ConvertUtils.convertString(ids);
                idIs.addAll(ints);
            }
            if(idIs.size()>0){
                List<MemberReadDetailResponse> memberReadDetailResponses=memberArticleService.selectMemberReadDetailResponseOutPut(idIs);
                memberReadDetailResponseList.addAll(memberReadDetailResponses);
            }else{
                List<MemberReadDetailResponse> memberReadDetailResponses=memberArticleService.selectMemberReadDetailResponse(articleId,null);
                memberReadDetailResponseList.addAll(memberReadDetailResponses);
            }
            List<List<String>> listArrayList=new ArrayList<>();
            if(memberReadDetailResponseList.size()>0){
                for(MemberReadDetailResponse memberReadDetailResponse:memberReadDetailResponseList){
                    List<String> stringList=new ArrayList<>();
                    stringList.add(memberReadDetailResponse.getMemberName());
                    stringList.add(memberReadDetailResponse.getDepartmentName());
                    if(memberReadDetailResponse.getStartReadTime()!=null){
                        stringList.add(memberReadDetailResponse.getStartReadTime().toString());
                    }else{
                        stringList.add("");
                    }
                    if(memberReadDetailResponse.getEndReadTime()!=null){
                        stringList.add(memberReadDetailResponse.getEndReadTime().toString());
                    }else{
                        stringList.add("");
                    }

                    if(memberReadDetailResponse.getIsReadSuccess().toString().equals("0")){
                        stringList.add("否");
                    }else if(memberReadDetailResponse.getIsReadSuccess().toString().equals("1")){
                        stringList.add("是");
                    }
                    stringList.add(memberReadDetailResponse.getIp());
                    listArrayList.add(stringList);
                }
            }
            if(listArrayList.size()>0){
                String[] regionTitle = new String[] {"成员阅读详情", CalendarUtils.dateToString(CalendarUtils.getDate(),CalendarUtils.yyyy_MM_dd__HH_mm_ss)};
                String fileName = BaseUtils.outputExcel("成员阅读详情",regionTitle,MEMB_READ_DETAIL_TILE,listArrayList,outPutFolderConfig.getPrefix(),false);
                json.put("fileName",fileName);
            }
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

}
