package com.cdyykj.xzhk.controller.api.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdyykj.system.commons.SystemConstants;
import com.cdyykj.xzhk.tool.WxCpServiceTool;
import com.cdyykj.xzhk.entity.Department;
import com.cdyykj.xzhk.entity.DepartmentMember;
import com.cdyykj.xzhk.entity.RDepartmentMember;
import com.cdyykj.xzhk.service.DepartmentMemberService;
import com.cdyykj.xzhk.service.DepartmentService;
import com.cdyykj.xzhk.service.RDepartmentMemberService;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 企业微信回调
 */
@RestController
@RequestMapping(value = "callBack")
public class TestCallBack {

    @Autowired
    DepartmentMemberService departmentMemberService;
    @Autowired
    RDepartmentMemberService rDepartmentMemberService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    WxCpServiceTool wxCpServiceTool;

    /**
     * 验证回调
     * @param msg_signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @param response
     */
    @GetMapping(value="")
    public void index(String msg_signature, String timestamp, String nonce, String echostr, HttpServletResponse response){
            try {

                WxCpService wxCpService = wxCpServiceTool.getWxCpService();
                boolean checkResult = wxCpService.checkSignature( msg_signature,  timestamp,  nonce, echostr);
                if(checkResult){
                	WxCpCryptUtil cpCryptUtil = new WxCpCryptUtil(wxCpService.getWxCpConfigStorage());
                	String res = cpCryptUtil.decrypt(echostr);
                    System.out.println(res);
                    response.getWriter().print(res);
                    return ;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
    }

    /**
     *接收业务数据
     * @param msg_signature
     * @param timestamp
     * @param nonce
     */
    @PostMapping(value="")
    public void back(HttpServletRequest request,String msg_signature, String timestamp, String nonce,HttpServletResponse response){
            try {
                WxCpService wxCpService =wxCpServiceTool.getWxCpService();
                String encryptedXml= IOUtils.toString(request.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
                WxCpXmlMessage wxCpXmlMessage=WxCpXmlMessage.fromEncryptedXml(encryptedXml,wxCpService.getWxCpConfigStorage(),timestamp,nonce,msg_signature);
                   if(wxCpXmlMessage.getChangeType().equals(SystemConstants.CREATE_USER)) {
                       createUser(wxCpXmlMessage);
                   }else if(wxCpXmlMessage.getChangeType().equals(SystemConstants.UPDATE_USER)){
                       updateUser(wxCpXmlMessage);
                   }else if(wxCpXmlMessage.getChangeType().equals(SystemConstants.DALETE_USER)){
                       deleteUser(wxCpXmlMessage);
                   }else if (wxCpXmlMessage.getChangeType().equals(SystemConstants.CREATE_PARTY)){
                       createParty(wxCpXmlMessage);
                   }else if(wxCpXmlMessage.getChangeType().equals(SystemConstants.UPDATE_PARTY)){
                       updateParty(wxCpXmlMessage);
                   }else if (wxCpXmlMessage.getChangeType().equals(SystemConstants.DALETE_PARTY)){
                        deleteParty(wxCpXmlMessage);
                   }
                  response.getWriter().print("");
            }catch (Exception e){
                e.printStackTrace();
            }
    }



    /**
     * 添加成员
     * @param wxCpXmlMessage
     */
    private void createUser(WxCpXmlMessage wxCpXmlMessage){
        //插入成员信息
        DepartmentMember departmentMember=insertMember(wxCpXmlMessage);
        //创建成员与部门的关系
        insertListRDepartmentMember(wxCpXmlMessage,departmentMember.getId());
    }

    /**
     * 更新成员
     * @param wxCpXmlMessage
     */
    private void updateUser(WxCpXmlMessage wxCpXmlMessage){

        String userId=wxCpXmlMessage.getUserId();
        String newUserId=wxCpXmlMessage.getNewUserId();
        DepartmentMember departmentMember1=departmentMemberService.selectByUserId(userId);
        if(departmentMember1!=null){
            //status--激活状态：1=已激活 2=已禁用 4=未激活
            departmentMember1.setStatus(Byte.valueOf(wxCpXmlMessage.getStatus()));
            departmentMemberService.updateById(departmentMember1);
        }
        if(newUserId!=null){
            if(departmentMember1!=null){
                //删除成员信息和成员与部门的关系
                rDepartmentMemberService.deleteByMemberId(departmentMember1.getId());
                departmentMemberService.delete(departmentMember1);
            }
            wxCpXmlMessage.setUserId(newUserId);//把旧userId替换成新的
            //创建新成员信息和成员与部门的关系
            DepartmentMember departmentMember=insertMember(wxCpXmlMessage);
            insertListRDepartmentMember(wxCpXmlMessage,departmentMember.getId());
        }
    }

    /**
     * 插入成员信息
     * @param wxCpXmlMessage
     * @return
     */
    private DepartmentMember insertMember(WxCpXmlMessage wxCpXmlMessage){
        DepartmentMember departmentMember=new DepartmentMember();
        departmentMember.setStatus((byte)0);
        departmentMember.setName(wxCpXmlMessage.getName());
        departmentMember.setPosition(wxCpXmlMessage.getPosition());
        departmentMember.setMobile(wxCpXmlMessage.getMobile());
        departmentMember.setTelephone(wxCpXmlMessage.getTelephone());
        departmentMember.setGender(wxCpXmlMessage.getGender().byteValue());
        departmentMember.setEmail(wxCpXmlMessage.getEmail());
        departmentMember.setAvatar(wxCpXmlMessage.getAvatar());
        departmentMember.setUserId(wxCpXmlMessage.getUserId());
        departmentMemberService.insert(departmentMember);
        return departmentMember;
    }

    /**
     * 批量插入成员与部门的关系
     * @param wxCpXmlMessage
     * @param memberId
     */
    private void insertListRDepartmentMember(WxCpXmlMessage wxCpXmlMessage,Integer memberId){
        Long[] deptIds= wxCpXmlMessage.getDepartments();
        Integer[] isLeader=wxCpXmlMessage.getIsLeaderInDept();
        List<RDepartmentMember> rDepartmentMembers=new ArrayList<>();
        for(int i=0;i<deptIds.length;i++){
            RDepartmentMember rDepartmentMember=new RDepartmentMember();
            rDepartmentMember.setDepartmentId(deptIds[i].intValue());
            rDepartmentMember.setMemberId(memberId);
            rDepartmentMember.setIsLeaderInDept(isLeader[i].byteValue());
            rDepartmentMembers.add(rDepartmentMember);
        }
        rDepartmentMemberService.insertList(rDepartmentMembers);
    }

    /**
     * 删除成员
     */
    private void deleteUser(WxCpXmlMessage wxCpXmlMessage){
        String userId=wxCpXmlMessage.getUserId();
        DepartmentMember departmentMember1=departmentMemberService.selectByUserId(userId);
        //删除成员信息和成员与部门的关系
        departmentMemberService.delete(departmentMember1);
        rDepartmentMemberService.deleteByMemberId(departmentMember1.getId());
    }

    /**
     * 新增部门
     * @param wxCpXmlMessage
     */
    private void createParty(WxCpXmlMessage wxCpXmlMessage){
        Department department=new Department();
        department.setId(wxCpXmlMessage.getId().intValue());
        department.setName(wxCpXmlMessage.getName());
        department.setDpOrder(Byte.valueOf(wxCpXmlMessage.getOrder()));//部门排序
        department.setParentId(Integer.valueOf(wxCpXmlMessage.getParentId()));
        Department department1=departmentService.getById(Integer.valueOf(wxCpXmlMessage.getParentId()));
        department.setLevelPath(department1.getLevelPath()+"/"+department.getId());
        List<Department> departments=new ArrayList<>();
        departments.add(department);
        departmentService.insertListFor(departments);
    }

    /**
     *
     * @param wxCpXmlMessage
     */
    private  void updateParty(WxCpXmlMessage wxCpXmlMessage){
        Department department=new Department();
        department.setName(wxCpXmlMessage.getName());
        department.setId(wxCpXmlMessage.getId().intValue());
        departmentService.updateById(department);
    }

    /**
     * 删除部门
     * @param wxCpXmlMessage
     */
    private void deleteParty(WxCpXmlMessage wxCpXmlMessage){
        Department department=new Department();
        department.setId(wxCpXmlMessage.getId().intValue());
        departmentService.delete(department);
        //删除该部门与成员的关联
        rDepartmentMemberService.deleteByDepartmentId(department.getId());
    }
}
