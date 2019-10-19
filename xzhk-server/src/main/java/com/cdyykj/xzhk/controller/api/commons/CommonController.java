package com.cdyykj.xzhk.controller.api.commons;

import com.cdyykj.commons.upyun.UpYunUtils;
import com.cdyykj.system.commons.BytesUtils;
import com.cdyykj.system.commons.CalendarUtils;
import com.cdyykj.system.commons.JsonUtils;
import com.cdyykj.system.commons.SystemConstants;
import com.cdyykj.system.commons.result.JsonResult;
import com.cdyykj.system.commons.result.JsonResultUtils;
import com.cdyykj.xzhk.dto.JsApiSigParam;
import com.cdyykj.xzhk.entity.AnnexManage;
import com.cdyykj.xzhk.service.AnnexManageService;
import com.cdyykj.xzhk.tool.WxCpServiceTool;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.cp.api.WxCpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.UUID;

@Controller(value="consoleCommonController")
@RequestMapping(value = "commons")
public class CommonController {

    @Autowired
    AnnexManageService annexManageService;
    @Autowired
    WxCpServiceTool wxCpServiceTool;
    /**
     * 公共图片文件上传
     *
     * @return
     * @author chenbiao
     * @date 2017年3月14日 下午9:21:59 参数说明
     */
    @ResponseBody
    @RequestMapping(value = "upload/img", method = RequestMethod.POST)
    protected ObjectNode uploadImage(@RequestParam(value = "file") MultipartFile file, Model model) {
        ObjectNode json = JsonUtils.getMapperInstance().createObjectNode();
        if (null != file && file.getSize() > 0) {
            String fileName = UpYunUtils.uploadFtpFile(file, SystemConstants.UPLOAD_DIR);
            json.put("code", 0);
            json.put("msg", "");
            ObjectNode objectNode = JsonUtils.getMapperInstance().createObjectNode();
            objectNode.put("src", SystemConstants.VISIT_DIR + fileName);
            json.putPOJO("data", objectNode);
        }
        return json;
    }


    /**
     * 单个文件上传
     * @param file
     * @return
     */
    @ApiOperation(value="单个文件上传")
    @PostMapping(value="upload/file")
    @ResponseBody
    @ApiImplicitParam(value = "0-文章，1-飞行报告",name = "type")
    public JsonResult uploadFile(@RequestParam("file") MultipartFile file, Integer applyType){
        try{
            if(file.isEmpty()){
                return JsonResultUtils.buildJsonFail("文件为空");
            }
            String newFileName = UpYunUtils.uploadFtpFile(file, SystemConstants.UPLOAD_DIR);
            String newFilePath= SystemConstants.VISIT_DIR + newFileName;
            AnnexManage annexManage=new AnnexManage();
            annexManage.setName(file.getOriginalFilename());
            annexManage.setUrl(newFilePath);
            annexManage.setSize(BytesUtils.bytes2kb(file.getSize()));
            annexManageService.insert(annexManage);
            return JsonResultUtils.buildJsonOK(annexManage);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }

    }
    /**
     * 公共图片文件上传
     *
     * @return
     * @author chenbiao
     * @date 2017年3月14日 下午9:21:59 参数说明
     */
    @ResponseBody
    @RequestMapping(value = "upload/imgs", method = RequestMethod.POST)
    protected JsonResult uploadImages(@RequestParam(value = "file") CommonsMultipartFile[] files, Model model) {
        if (files.length > 0) {
            ArrayNode arrayNode = JsonUtils.getMapperInstance().createArrayNode();
            for (CommonsMultipartFile file : files) {
                if (null != file && file.getSize() > 0) {
                    String fileName = UpYunUtils.uploadFile(file, SystemConstants.UPLOAD_DIR);
                    arrayNode.add(SystemConstants.VISIT_DIR + fileName);
                }
            }
            return JsonResultUtils.buildJsonOK(arrayNode);
        }
        return JsonResultUtils.buildJsonFail();
    }
    /**
     * 创建JSapi签名
     * @return
     */
    @ApiOperation(value = "创建JSapi签名")
    @PostMapping(value = "createJsApiSig")
    @ResponseBody
    public JsonResult createJsApiSig(@RequestBody JsApiSigParam jsApiSigParam){
        try {
        WxCpService wxCpService= wxCpServiceTool.getWxCpService(SystemConstants.SYSTEM_SECRET);
        WxJsapiSignature wxJsapiSignature=wxCpService.createJsapiSignature(jsApiSigParam.getUrl());
        ObjectNode json=JsonUtils.getMapperInstance().createObjectNode();
        json.putPOJO("wxJsapiSignature",wxJsapiSignature);
        return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }

    /**
     * 将文件上传到本地
     * @param file
     * @param applyType
     * @return
     */
    @PostMapping(value = "localUploadFile")
    @ResponseBody
    @ApiOperation(value = "将文件上传到本地")
    public JsonResult localUploadFile(@RequestBody MultipartFile file,@RequestParam(required = false,defaultValue = "0") Integer applyType){
        try {
            ObjectNode json =JsonUtils.getMapperInstance().createObjectNode();
            String fileName=file.getOriginalFilename();
            String buffix = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = CalendarUtils.dateToString(CalendarUtils.getDate(),CalendarUtils.yyyyMMddHHmmss)+ UUID.randomUUID().toString()+buffix;
            String newFilePath=SystemConstants.LOCAL_UPLOAD_DIR+newFileName;
            OutputStream fileOutputStream=new FileOutputStream(newFilePath);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
            AnnexManage annexManage=new AnnexManage();
            annexManage.setStatus((byte)0);
            annexManage.setName(file.getOriginalFilename());
            annexManage.setUrl(SystemConstants.prefix+newFileName);
            annexManage.setSize(BytesUtils.bytes2kb(file.getSize()));
            annexManageService.insert(annexManage);
            json.putPOJO("annexManage",annexManage);
            return JsonResultUtils.buildJsonOK(json);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtils.buildJsonFailMsg(e.getMessage());
        }
    }
}
