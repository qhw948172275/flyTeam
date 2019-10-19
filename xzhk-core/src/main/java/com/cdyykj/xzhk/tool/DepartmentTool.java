package com.cdyykj.xzhk.tool;

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
import me.chanjar.weixin.cp.bean.WxCpDepart;
import me.chanjar.weixin.cp.bean.WxCpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DepartmentTool {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentMemberService departmentMemberService;
    @Autowired
    RDepartmentMemberService rDepartmentMemberService;
    @Autowired
    WxCpServiceTool wxCpServiceTool;

    /**
     *部门成员同步
     * @throws WxErrorException
     */
    @Transactional
    public boolean startSynchro(){
        try {
            WxCpService wxCpService=wxCpServiceTool.getWxCpService();

            WxCpDepartmentService wxCpDepartmentService=wxCpService.getDepartmentService();
            WxCpUserService wxCpUserService= wxCpService.getUserService();


            List<WxCpDepart> list=wxCpDepartmentService.list(null);
            //System.out.println("------"+list+"-------");
            List<WxCpUser> wxCpUserList=wxCpUserService.listByDepartment(1L,true,null);
            //System.out.println("------"+wxCpUserList+"-------");
            List<Department> departments=new ArrayList<>();//部门列表
            List<Department> topListDto =new ArrayList<>();
            List<Department> notListDto =new ArrayList<>();
            Department department;
            for(WxCpDepart wxCpDepart:list) {
                 department = new Department();
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
                rDepartmentMemberService.deleteAll();//清空部门和成员的关系
                departmentService.deleteAll();//清空部门表
                departmentService.insertListFor(departments);//批量插入部门
            }

            List<DepartmentMember> oldDepartmentMembers=new ArrayList<>();//部门下得成员--以前存在的成员
            List<DepartmentMember> newDepartmentMembers=new ArrayList<>();//部门下得成员--新增加的成员

            List<RDepartmentMember> rDepartmentMembers=new ArrayList<>();
            Map<String,DepartmentMember> departmentMemberMap=new HashMap<>();


            List<DepartmentMember> departmentMembers1=departmentMemberService.selectAllList(null);//已存在成员

            Map<String,Integer> memberIDs=new HashMap<>(departmentMembers1.size());//本地成员ID与userID的关系

            for(DepartmentMember departmentMember:departmentMembers1){
                memberIDs.put(departmentMember.getUserId(),departmentMember.getId());
            }
            DepartmentMember departmentMember;//成员对象
            RDepartmentMember rDepartmentMember;//成员与部门关系对象
            for(WxCpUser wxCpUser:wxCpUserList){
                departmentMember=new DepartmentMember();
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

                if(memberIDs.containsKey(wxCpUser.getUserId())){//本地成员信息存在的
                    departmentMember.setId(memberIDs.get(wxCpUser.getUserId()));
                    oldDepartmentMembers.add(departmentMember);
                }else{
                    newDepartmentMembers.add(departmentMember);
                }

                departmentMemberMap.put(wxCpUser.getUserId(),departmentMember);

                for(int i=0;i<wxCpUser.getDepartIds().length;i++){
                    rDepartmentMember=new RDepartmentMember();
                    rDepartmentMember.setDepartmentId(wxCpUser.getDepartIds()[i].intValue());
                    rDepartmentMember.setIsLeaderInDept(wxCpUser.getIsLeaderInDept()[i].byteValue());
                    rDepartmentMember.setMbOrder(wxCpUser.getOrders()[i]);
                    rDepartmentMember.setUserId(wxCpUser.getUserId());
                    rDepartmentMembers.add(rDepartmentMember);
                }
            }
            departmentMemberService.deleteAll();//删除所有成员
            if(!oldDepartmentMembers.isEmpty()){
                departmentMemberService.insertListById(oldDepartmentMembers);//先插入以前存在的
            }
            if(newDepartmentMembers.size()>0){
                departmentMemberService.insertList(newDepartmentMembers);//插入新增的
            }

            if(rDepartmentMembers.size()>0){
                for(RDepartmentMember rDepartmentMemberT:rDepartmentMembers){
                    rDepartmentMemberT.setMemberId(departmentMemberMap.get(rDepartmentMemberT.getUserId()).getId());
                }
                rDepartmentMemberService.insertList(rDepartmentMembers);
                System.out.println("------同步完成-----");
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
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

    /**
     * 增量同步
     */
    @Transactional
    public boolean spickSynchroAdd(){
        try {
            WxCpService wxCpService=wxCpServiceTool.getWxCpService();

            WxCpDepartmentService wxCpDepartmentService=wxCpService.getDepartmentService();
            WxCpUserService wxCpUserService= wxCpService.getUserService();


            List<WxCpDepart> list=wxCpDepartmentService.list(null);//部门

            List<Department> departments=departmentService.selectAllList(null);//旧部门

            List<Integer> oldDepartmentIds=new ArrayList<>(departments.size());//旧部门ID
            Map<Integer,Department> oldDepartmentMap=new HashMap<>(departments.size());//旧部门ID与对象
            for(Department department:departments){
                oldDepartmentIds.add(department.getId());
                oldDepartmentMap.put(department.getId(),department);
            }

            Map<Integer,Department> newDepartmentMap=new HashMap<>(list.size());//新部门ID与对象的集合
            List<Integer> newDepartmentIds=new ArrayList<>(list.size());//新部门ID
            Department department;
            for(WxCpDepart wxCpDepart:list){
                department = new Department();
                department.setId(wxCpDepart.getId().intValue());
                department.setName(wxCpDepart.getName());
                department.setDpOrder(wxCpDepart.getOrder().byteValue());
                department.setParentId(wxCpDepart.getParentId().intValue());
                if(wxCpDepart.getParentId().intValue()==0){
                    department.setLevelPath("0/"+department.getId());
                }else{
                    Department oldDepartment=oldDepartmentMap.get(department.getParentId());
                    department.setLevelPath(oldDepartment.getLevelPath()+"/"+department.getId());
                }

                newDepartmentIds.add(department.getId());
                newDepartmentMap.put(wxCpDepart.getId().intValue(),department);
            }
            //新部门与旧部门的差集
            newDepartmentIds.removeAll(oldDepartmentIds);
            if(!newDepartmentIds.isEmpty()){
                List<Department> newDepartmentList=new ArrayList<>(newDepartmentIds.size());
                for(Integer id:newDepartmentIds){
                    newDepartmentList.add(newDepartmentMap.get(id));
                }
                departmentService.insertListFor(newDepartmentList);
            }

            List<WxCpUser> wxCpUserList=wxCpUserService.listByDepartment(1L,true,null);//成员
            //旧成员
            List<DepartmentMember> departmentMembers=departmentMemberService.selectAllList(null);
            List<String> oldMemberUserIds=new ArrayList<>(departmentMembers.size());

            for(DepartmentMember departmentMember:departmentMembers){
                oldMemberUserIds.add(departmentMember.getUserId());
            }
            //新成员
            List<String> newMemberUserIds=new ArrayList<>(wxCpUserList.size());
            Map<String,DepartmentMember> newDepartmentMemberMap=new HashMap<>(wxCpUserList.size());
            DepartmentMember departmentMember;
            Map<String,Long[]> depmentIdsMap=new HashMap<>(wxCpUserList.size());
            for(WxCpUser wxCpUser:wxCpUserList){
                departmentMember=new DepartmentMember();
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

                depmentIdsMap.put(wxCpUser.getUserId(),wxCpUser.getDepartIds());
                newMemberUserIds.add(wxCpUser.getUserId());
                newDepartmentMemberMap.put(wxCpUser.getUserId(),departmentMember);
            }
            newMemberUserIds.removeAll(oldMemberUserIds);
            if(!newMemberUserIds.isEmpty()){
                List<DepartmentMember> newDepartmentList=new ArrayList<>(newMemberUserIds.size());
                for(String s:newMemberUserIds){
                    newDepartmentList.add(newDepartmentMemberMap.get(s));


                }
                departmentMemberService.insertList(newDepartmentList);
                RDepartmentMember rDepartmentMember;
                List<RDepartmentMember> rDepartmentMembers=new ArrayList<>(newMemberUserIds.size());
                for(String s:newMemberUserIds){
                    Long[] dpIds=depmentIdsMap.get(s);
                    for(int i=0;i<dpIds.length;i++){
                        rDepartmentMember=new RDepartmentMember();
                        rDepartmentMember.setDepartmentId(dpIds[i].intValue());
                        rDepartmentMember.setMemberId(newDepartmentMemberMap.get(s).getId());
                        rDepartmentMembers.add(rDepartmentMember);
                    }
                }
                rDepartmentMemberService.insertList(rDepartmentMembers);
            }

            return true;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
