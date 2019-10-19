package com.cdyykj.xzhk.service;

import com.cdyykj.system.commons.StringUtils;
import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.system.dto.DepartmentDto;
import com.cdyykj.system.dto.ResourceDto;
import com.cdyykj.system.entity.SystemResource;
import com.cdyykj.xzhk.dao.DepartmentMapper;
import com.cdyykj.xzhk.entity.Department;
import com.cdyykj.xzhk.response.RDepartmentMemberResponse;
import com.cdyykj.xzhk.response.RDepartmentMemberResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentService extends AbstractBaseCrudService<Department,Integer> {
    @Autowired
    DepartmentMapper departmentMapper;
    /**
     * 清空部门表
     */
    public void deleteAll() {
        departmentMapper.deleteAll();
    }

    /**
     * 批量插入部门
     * @param departments
     * @return
     */
    public int insertListFor(List<Department> departments){
      return   departmentMapper.insertListFor(departments);
    }


    /**
     * 查询所有部门
     * @return
     */
    public List<DepartmentDto> selectAll(){
        //查询顶级数据
        Example example = new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("parentId",0);
        example.setOrderByClause("dp_order desc ,id desc");
        List<Department> topList= mapper.selectByExample(example);

        //查询非定级数据结构
        Example example2 = new Example(tClass);
        Example.Criteria criteria2=example2.createCriteria();
        criteria2.andNotEqualTo("parentId",0);
        example2.setOrderByClause("dp_order desc ,id  desc");
        List<Department> list= mapper.selectByExample(example2);

        //对象转换
        List<DepartmentDto> topListDto = changeList(topList);
        List<DepartmentDto> notListDto = changeList(list);

        Map<Integer,Integer> newHashMapWithExpectedSize = new HashMap<>(notListDto.size());

        for(DepartmentDto organDto:topListDto){
            getChild(organDto,notListDto,newHashMapWithExpectedSize);
        }

        return topListDto;

    }
    private List<DepartmentDto> changeList(List<Department> oldListDto){
        List<DepartmentDto> resourceDtoList=new ArrayList<>();
        for(Department  organ:oldListDto){
            DepartmentDto organDto = new DepartmentDto();
            BeanUtils.copyProperties(organ,organDto);
            resourceDtoList.add(organDto);
        }
        return resourceDtoList;
    }

    private  void  getChild(DepartmentDto organDto,List<DepartmentDto>organDtos,Map<Integer,Integer>map){
        List<DepartmentDto>childList = new ArrayList<>();
        for(DepartmentDto organDto1:organDtos){
            if(!map.containsKey(organDto1.getId())&&organDto.getId().intValue()==organDto1.getParentId().intValue()){
                map.put(organDto1.getId(),organDto1.getParentId());
                getChild(organDto1,organDtos,map);
                childList.add(organDto1);
            }
        }
        organDto.setDepartments(childList);

    }

    /**
     * 根据关键字搜索部门
     * @param keyword
     * @return
     */
    public List<Department> selectByKeyword(String keyword) {
        Example example=new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        if(StringUtils.isNotEmpty(keyword)){
            criteria.andLike("name",keyword);
        }
        example.setOrderByClause("dp_order desc");
        return mapper.selectByExample(example);
    }

    /**
     * 获取全部部门和成员列表，除开顶级部门
     * @return
     */
    public List<RDepartmentMemberResponseDto> selectRDepartmentMemberResponse() {
        return departmentMapper.selectRDepartmentMemberResponse();
    }

    /**
     * 获取全部顶级部门和成员列表
     * @return
     */
    public List<RDepartmentMemberResponseDto> selectRDepartmentMemberResponseParent() {
        return departmentMapper.selectRDepartmentMemberResponseParent();
    }
}
