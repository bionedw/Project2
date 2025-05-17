package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String,Object> params, List<String> typeCode){
        BuildingSearchBuilder buildingSearchBuilder=new BuildingSearchBuilder.Builder()
                .setName(MapUtil.getObject(params, String.class, "name"))
                .setFloorArea(MapUtil.getObject(params, Long.class, "floorArea"))
                .setWard(MapUtil.getObject(params, String.class, "ward"))
                .setStreet(MapUtil.getObject(params, String.class, "street"))
                .setDistrictCode(MapUtil.getObject(params, String.class, "districtCode"))
                .setNumberOfBasement(MapUtil.getObject(params, Integer.class, "numberOfBasement"))
                .setTypeCode(typeCode)
                .setManagerName(MapUtil.getObject(params, String.class, "managerName"))
                .setManagerPhoneNumber(MapUtil.getObject(params, String.class, "managerPhoneNumber"))
                .setRentPriceFrom(MapUtil.getObject(params, Long.class, "rentPriceFrom"))
                .setRentPriceTo(MapUtil.getObject(params, Long.class, "rentPriceTo"))
                .setAreaFrom(MapUtil.getObject(params, Long.class, "areaFrom"))
                .setAreaTo(MapUtil.getObject(params, Long.class, "areaTo"))
                .setStaffId(MapUtil.getObject(params, Long.class, "staffId"))
                .setLevel(MapUtil.getObject(params, String.class, "level"))
                .build();
        return null;
    }
}
