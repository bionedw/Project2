package com.javaweb.repository;

import com.javaweb.repository.entity.AssignmentBuildingEntity;
import com.javaweb.repository.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface AssignmentBuildingRepository {
    List<AssignmentBuildingEntity> findAll(Map<String,Object> params);
}
