package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{

	static final String DB_URL="jdbc:mysql://localhost:3306/estatebasic";
	static final String USER="root";
	static final String PASS="Thinh123";
	
	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params) {
		StringBuilder sql=new StringBuilder("SELECT * FROM building b WHERE 1=1 ");
		if (params.containsKey("name") && params.get("name") != null && params.get("name") != "") {
			sql.append("AND b.name LIKE '%").append(params.get("name")).append("%' ");
		}

		if (params.containsKey("districtId") && params.get("districtId") != null) {
			sql.append("AND b.name LIKE ").append(params.get("name")).append(" ");
		}
		List<BuildingEntity> result=new ArrayList<>();
		
		try(Connection conn=DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stm =conn.createStatement();
				ResultSet rs=stm.executeQuery(sql.toString())){
			
			while(rs.next()) {
				BuildingEntity building=new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setNumberOfBasement(rs.getInt("numberofbasement"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				
				result.add(building);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
