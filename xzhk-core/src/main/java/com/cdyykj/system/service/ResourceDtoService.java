package com.cdyykj.system.service;

import com.cdyykj.system.commons.service.impl.AbstractBaseCrudService;
import com.cdyykj.system.dao.SystemResourceMapper;
import com.cdyykj.system.dto.ResourceDto;
import com.cdyykj.system.entity.SystemResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceDtoService extends AbstractBaseCrudService<SystemResource, Integer> {

   @Autowired
   private SystemResourceMapper systemResourceMapper;

    /**
     * 查询本系统所有资源权限
     * @return
     */
    public List<ResourceDto> selectAll(){
        //查询顶级数据
        Example example = new Example(tClass);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("parentId",-1).andEqualTo("status",0);
        example.setOrderByClause("seq ,id ");
        List<SystemResource> topList= mapper.selectByExample(example);

        //查询非定级数据结构
        Example example2 = new Example(tClass);
        Example.Criteria criteria2=example2.createCriteria();
        criteria2.andNotEqualTo("parentId",-1).andEqualTo("status",0);
        example2.setOrderByClause("seq ,id  ");
        List<SystemResource> list= mapper.selectByExample(example2);

        //对象转换
        List<ResourceDto> topListDto = changeList(topList);
        List<ResourceDto> notListDto = changeList(list);

        Map<Integer,Integer> newHashMapWithExpectedSize = new HashMap<>(notListDto.size());

        for(ResourceDto organDto:topListDto){
            getChild(organDto,notListDto,newHashMapWithExpectedSize);
        }

        return topListDto;

    }
    private List<ResourceDto> changeList( List<SystemResource> oldListDto){
        List<ResourceDto> resourceDtoList=new ArrayList<>();
        for(SystemResource  organ:oldListDto){
            ResourceDto organDto = new ResourceDto();
            BeanUtils.copyProperties(organ,organDto);
            resourceDtoList.add(organDto);
        }
        return resourceDtoList;
    }

    private  void  getChild(ResourceDto organDto,List<ResourceDto>organDtos,Map<Integer,Integer>map){
        List<ResourceDto>childList = new ArrayList<>();
        for(ResourceDto organDto1:organDtos){
            if(!map.containsKey(organDto1.getId())&&organDto.getId().intValue()==organDto1.getParentId().intValue()){
                map.put(organDto1.getId(),organDto1.getParentId());
                getChild(organDto1,organDtos,map);
                childList.add(organDto1);
            }
        }
        organDto.setResourceDtos(childList);

    }

    /**
     * 根据当前用户ID查询对应角色对应的资源
     * @param userId
     * @return
     */
 public  List<ResourceDto> selectByUserId(Integer userId){
    List<SystemResource>  systemResources=systemResourceMapper.selectByUserId(userId);
    List<ResourceDto> topResource=new ArrayList<>();
    List<ResourceDto> notTopResource=new ArrayList<>();
    for(SystemResource systemResource:systemResources){
        ResourceDto resourceDto=new ResourceDto();
        BeanUtils.copyProperties(systemResource,resourceDto);
        if(systemResource.getParentId()==-1){
            topResource.add(resourceDto);
        }else{
            notTopResource.add(resourceDto);
        }
    }
     Map<Integer,Integer> newHashMapWithExpectedSize = new HashMap<>(notTopResource.size());

     for(ResourceDto organDto:topResource){
         getChild(organDto,notTopResource,newHashMapWithExpectedSize);
     }

     return topResource;
 }

}
