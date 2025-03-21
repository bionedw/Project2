package com.javaweb.repository;

import com.javaweb.repository.entity.BuildingRentTypeEntity;

import java.util.List;

public interface BuildingRentTypeRepository {
    List<BuildingRentTypeEntity> findAll();
}
