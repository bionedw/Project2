package com.javaweb.repository.impl;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.JdbcConnectionUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {
    @Override
    public List<RentAreaEntity> getValueByBuildingId(Integer buildingId) {
        List<RentAreaEntity> rentAreas=new ArrayList<>();
        String sql=" SELECT * from rentArea ra where ra.buildingId="+buildingId+" ";
        try(Connection conn= JdbcConnectionUtil.getConnection();
            Statement stm =conn.createStatement();
            ResultSet rs=stm.executeQuery(sql)){
            while(rs.next()) {
                RentAreaEntity rentAreaEntity=new RentAreaEntity();
                rentAreaEntity.setValue(rs.getInt("value"));
                rentAreas.add(rentAreaEntity);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return  rentAreas;
    }
}
