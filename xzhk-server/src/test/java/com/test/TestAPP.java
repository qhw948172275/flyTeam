package com.test;

import com.cdyykj.xzhk.ServiceApplication;
import com.cdyykj.xzhk.entity.Department;
import com.cdyykj.xzhk.entity.DepartmentMember;
import com.cdyykj.xzhk.entity.RDepartmentMember;
import com.cdyykj.xzhk.service.DepartmentMemberService;
import com.cdyykj.xzhk.service.DepartmentService;
import com.cdyykj.xzhk.service.RDepartmentMemberService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpDepartmentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpUserService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceApacheHttpClientImpl;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ignore
@SpringBootTest(classes=ServiceApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAPP {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentMemberService departmentMemberService;
    @Autowired
    RDepartmentMemberService rDepartmentMemberService;
    @Ignore
    @Test
    public void oasda() throws WxErrorException {
        WxCpService wxCpService=new WxCpServiceApacheHttpClientImpl();
        WxCpConfigStorage wxCpConfigStorage=new WxCpDefaultConfigImpl();
        ((WxCpDefaultConfigImpl) wxCpConfigStorage).setCorpId("wweceece51131a8a05");
        ((WxCpDefaultConfigImpl) wxCpConfigStorage).setCorpSecret("_BFCb5dwPwhMMZZiCSsByo7v5IS3TCLAFYoZxxBqTT0");
         wxCpService.setWxCpConfigStorage(wxCpConfigStorage);
        System.out.println("------"+wxCpService.getAccessToken()+"-------");
        WxCpDepartmentService wxCpDepartmentService=wxCpService.getDepartmentService();
        WxCpUserService wxCpUserService= wxCpService.getUserService();


         List<WxCpDepart> list=wxCpDepartmentService.list(null);
        System.out.println("------"+list+"-------");
        List<WxCpUser> wxCpUserList=wxCpUserService.listByDepartment(1L,true,null);
        System.out.println("------"+wxCpUserList+"-------");
        List<Department> departments=new ArrayList<>();//部门列表
        List<Department> topListDto =new ArrayList<>();
        List<Department> notListDto =new ArrayList<>();
        for(WxCpDepart wxCpDepart:list) {
            Department department = new Department();
            department.setId(wxCpDepart.getId().intValue());
            department.setName(wxCpDepart.getName());
            department.setDpOrder(wxCpDepart.getOrder().byteValue());
            department.setParentId(wxCpDepart.getParentId().intValue());
            departments.add(department);
            if(wxCpDepart.getId().intValue()==1){
                topListDto.add(department);
            }else{
                notListDto.add(department);
            }
        }

        Map<Integer,Integer> newHashMapWithExpectedSize = new HashMap<>(notListDto.size());

        for(Department organDto:topListDto){
            organDto.setLevelPath("0/"+organDto.getId());
            getChild(organDto,notListDto,newHashMapWithExpectedSize);
        }

        if(departments.size()>0){
         rDepartmentMemberService.deleteAll();
         departmentService.deleteAll();//清空部门表
         departmentService.insertListFor(departments);//批量插入部门
       }
       List<DepartmentMember> departmentMembers=new ArrayList<>();//部门下得成员
       List<RDepartmentMember> rDepartmentMembers=new ArrayList<>();
        Map<String,DepartmentMember> departmentMemberMap=new HashMap<>();
       for(WxCpUser wxCpUser:wxCpUserList){
           DepartmentMember departmentMember=new DepartmentMember();
           departmentMember.setUserId(wxCpUser.getUserId());
           departmentMember.setAvatar(wxCpUser.getAvatar());
           departmentMember.setEmail(wxCpUser.getEmail());
           if(wxCpUser.getGender()!=null){
               departmentMember.setGender( Byte.valueOf(wxCpUser.getGender().getCode()));
           }
           departmentMember.setTelephone(wxCpUser.getTelephone());
           departmentMember.setMobile(wxCpUser.getMobile());
           departmentMember.setPosition(wxCpUser.getPosition());
           departmentMember.setName(wxCpUser.getName());
           departmentMember.setStatus(wxCpUser.getStatus().byteValue());
           departmentMembers.add(departmentMember);

           departmentMemberMap.put(wxCpUser.getUserId(),departmentMember);

           for(int i=0;i<wxCpUser.getDepartIds().length;i++){
               RDepartmentMember rDepartmentMember=new RDepartmentMember();
               rDepartmentMember.setDepartmentId(wxCpUser.getDepartIds()[i].intValue());
               rDepartmentMember.setIsLeaderInDept(wxCpUser.getIsLeaderInDept()[i].byteValue());
               rDepartmentMember.setMbOrder(wxCpUser.getOrders()[i]);
               rDepartmentMember.setUserId(wxCpUser.getUserId());
               rDepartmentMembers.add(rDepartmentMember);
           }
       }

       if(departmentMembers.size()>0){
           departmentMemberService.deleteAll();
           departmentMemberService.insertList(departmentMembers);
           if(rDepartmentMembers.size()>0){
               for(RDepartmentMember rDepartmentMember:rDepartmentMembers){
                   rDepartmentMember.setMemberId(departmentMemberMap.get(rDepartmentMember.getUserId()).getId());
               }

               rDepartmentMemberService.insertList(rDepartmentMembers);
               System.out.println("------同步完成-----");
           }
       }
    }


    private  void  getChild(Department organDto,List<Department>organDtos,Map<Integer,Integer>map){
        for(Department organDto1:organDtos){
            if(!map.containsKey(organDto1.getId())&&organDto.getId().intValue()==organDto1.getParentId().intValue()){
                map.put(organDto1.getId(),organDto1.getParentId());
                organDto1.setLevelPath(organDto.getLevelPath()+"/"+organDto1.getId());
                getChild(organDto1,organDtos,map);
            }
        }
    }
}
