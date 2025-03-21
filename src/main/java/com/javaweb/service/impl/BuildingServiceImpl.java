package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRentTypeRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.AssignmentBuildingEntity;
import com.javaweb.repository.entity.BuildingRentTypeEntity;
import com.javaweb.repository.entity.DistrictEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private DistrictRepository districtRepository;


	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
		List<BuildingEntity> buildingEntities=buildingRepository.findAll(params, typeCode);
		List<BuildingDTO> result=new ArrayList<>();
		for(BuildingEntity item:buildingEntities) {
			BuildingDTO building=new BuildingDTO();
			DistrictEntity districtEntity=districtRepository.findNameById(item.getDistrictId());
			building.setName(item.getName());
			building.setAddress(item.getStreet()+", "+item.getWard()+", "+districtEntity.getName());

			result.add(building);
		}
		return result;
	}
}
