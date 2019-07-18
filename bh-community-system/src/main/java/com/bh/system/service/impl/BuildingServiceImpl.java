package com.bh.system.service.impl;

import com.bh.common.constants.ParamConstant;
import com.bh.common.result.PageResult;
import com.bh.common.result.RestResult;
import com.bh.common.result.ResultUtils;
import com.bh.common.utils.DateUtil;
import com.bh.model.dao.BuildingMapper;
import com.bh.model.dao.CommunityMapper;
import com.bh.model.dao.PropertyManagementMapper;
import com.bh.model.domain.Building;
import com.bh.model.domain.Community;
import com.bh.model.domain.PropertyManagement;
import com.bh.system.redis.JWTRedisDAO;
import com.bh.system.service.BuildingService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.BH
 */
@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

    private CommunityMapper communityMapper;

    @Autowired
    private JWTRedisDAO jwtRedisDAO;

    @Override
    public RestResult saveBuilding(Building building) {

        Date date = DateUtil.getCurrentDate();

        building.setStatus(0);
        building.setDelStatus(0);
        building.setCreateTime(date);

        buildingMapper.insertSelective(building);

        if (null == building.getId()){
            throw new IllegalArgumentException();
        }

        return ResultUtils.success("保存成功");
    }

    @Override
    public RestResult deleteBuilding(List<Integer> pkIds) {
        if(pkIds == null || pkIds.isEmpty()){
            return ResultUtils.error(1,"没有选择需要删除的数据");
        }

        Integer[] ids = new Integer[pkIds.size()];

        if (pkIds.size() > 0){
            for (int i = 0; i < pkIds.size(); i++) {
                ids[i] = pkIds.get(i);
            }
        }

        // 楼栋信息 逻辑删除
        buildingMapper.deleteBatch(ids);
        return ResultUtils.success("删除成功");
    }

    @Override
    public RestResult updateBuilding(Building building) {

        buildingMapper.updateByPrimaryKeySelective(building);

        return ResultUtils.success("编辑成功");
    }

    @Override
    public RestResult getBuilding(Integer id) {
        return ResultUtils.success(buildingMapper.selectByPrimaryKey(id));
    }

    @Override
    public RestResult listBuilding(String keyword, Integer pageNum, Integer pageSize) {

        int n = pageNum == 1 ? pageNum = 0 : (pageNum = (pageNum - 1) * pageSize);

        // 查询参数
        Map domainMap = new HashMap();
        domainMap.put(ParamConstant.KEY_WORD,keyword);
        domainMap.put(ParamConstant.PAGE_SIZE,pageSize);
        domainMap.put(ParamConstant.PAGE_NUM,pageNum);

        List<Map> maps = buildingMapper.listPage(domainMap);
        int count = buildingMapper.countPage(domainMap);

        return ResultUtils.success(new PageResult<Map>(maps,count));
    }

    @Override
    public RestResult findListByCommunityId(Integer communityId){
        return ResultUtils.success(buildingMapper.findListByCommunityId(communityId));
    }

    @Override
    public RestResult findTreeByCommunityId(Integer communityId){
        List<String> byStagingList = buildingMapper.findByStagingByCommunityId(communityId);
        JSONArray array1 = new JSONArray();
        for(int i=0; i < byStagingList.size(); i++){
            JSONArray array2 = new JSONArray();
            JSONObject obj1 = new JSONObject();
            List<String> buildList = buildingMapper.findBuilding(communityId, byStagingList.get(i));
            for(int j = 0; j < buildList.size(); j++){
                JSONObject obj2 = new JSONObject();
                List<Building> unitList = buildingMapper.findUnit(communityId, byStagingList.get(i), buildList.get(j));
                obj2.put(buildList.get(j),JSONArray.fromObject(unitList));
                array2.add(obj2);
            }
            obj1.put(byStagingList.get(i),array2);
            array1.add(obj1);
        }

        return ResultUtils.success(array1);
    }
}
