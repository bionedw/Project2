package com.javaweb.repository.impl;

import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.entity.AssignmentBuildingEntity;
import com.javaweb.repository.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AssignmentBuildingRepositoryImpl implements AssignmentBuildingRepository {
    static final String DB_URL="jdbc:mysql://localhost:3306/estatebasic";
    static final String USER="root";
    static final String PASS="Thinh123";
    @Override
    public List<AssignmentBuildingEntity> findAll(Map<String,Object> params){
        StringBuilder sql=new StringBuilder("SELECT * FROM assignmentBuilding ab WHERE 1=1 ");
        if (params.containsKey("staffId") && params.get("staffId") != null) {
            sql.append("AND ab.staffId = ").append(params.get("staffId")).append(" ");
        }

        ArrayList<AssignmentBuildingEntity> result = new ArrayList<>();

        try(Connection conn= DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stm =conn.createStatement();
            ResultSet rs=stm.executeQuery(sql.toString())){

            while(rs.next()) {
                AssignmentBuildingEntity assignmentBuilding=new AssignmentBuildingEntity();
                assignmentBuilding.setId(rs.getInt("id"));
                assignmentBuilding.setBuildingId(rs.getInt("buildingId"));
                assignmentBuilding.setStaffId(rs.getInt("staffId"));

                result.add(assignmentBuilding);
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
