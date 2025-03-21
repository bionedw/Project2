package com.javaweb.repository.impl;

import com.javaweb.repository.BuildingRentTypeRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingRentTypeEntity;
import com.javaweb.repository.entity.DistrictEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildingRentTypeRepositoryImpl implements BuildingRentTypeRepository {
    static final String DB_URL="jdbc:mysql://localhost:3306/estatebasic";
    static final String USER="root";
    static final String PASS="Thinh123";
    @Override
    public List<BuildingRentTypeEntity> findAll(){

        StringBuilder sql=new StringBuilder("SELECT * FROM buildingRentType brt WHERE 1=1 ");

        ArrayList<BuildingRentTypeEntity> result = new ArrayList<>();

        try(Connection conn= DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stm =conn.createStatement();
            ResultSet rs=stm.executeQuery(sql.toString())){

            while(rs.next()) {
                BuildingRentTypeEntity bte=new BuildingRentTypeEntity();
                bte.setId(rs.getInt("id"));
                bte.setBuildingId(rs.getInt("buildingId"));
                bte.setRentTypeId(rs.getInt("rentTypeId"));
                result.add(bte);
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
