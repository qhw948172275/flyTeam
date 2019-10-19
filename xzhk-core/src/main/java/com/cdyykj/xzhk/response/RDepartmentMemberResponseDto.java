package com.cdyykj.xzhk.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RDepartmentMemberResponseDto extends RDepartmentMemberResponse {

    /**
     *部门的层级区分
     * @return
     */
    public static List<RDepartmentMemberResponseDto> getRecursiver(List<RDepartmentMemberResponseDto> topList,List<RDepartmentMemberResponseDto> childList ){
        Map<Integer,Integer> newHashMapWithExpectedSize = new HashMap<>(childList.size());

        for(RDepartmentMemberResponseDto rDepartmentMemberResponseDto:topList){
            getChild(rDepartmentMemberResponseDto,childList,newHashMapWithExpectedSize);
        }

        return topList;
    }
    private static  void  getChild(RDepartmentMemberResponseDto organDto,List<RDepartmentMemberResponseDto> organDtos,Map<Integer,Integer>map){
        List<RDepartmentMemberResponseDto>childList = new ArrayList<>();
        for(RDepartmentMemberResponseDto organDto1:organDtos){
            if(!map.containsKey(organDto1.getId())&&organDto.getId().intValue()==organDto1.getParentId().intValue()){
                map.put(organDto1.getId(),organDto1.getParentId());
                getChild(organDto1,organDtos,map);
                childList.add(organDto1);
            }
        }
        organDto.setResponseDtos(childList);

    }
}
