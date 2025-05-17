package com.javaweb.repository;

import com.javaweb.repository.entity.AssignmentBuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

import java.util.List;
import java.util.Map;

public interface RentAreaRepository {
    List<RentAreaEntity> getValueByBuildingId(Integer buildingId);
}
