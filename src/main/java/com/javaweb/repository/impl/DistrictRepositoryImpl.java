package com.javaweb.repository.impl;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;


@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
    static final String DB_URL="jdbc:mysql://localhost:3306/estatebasic";
    static final String USER="root";
    static final String PASS="Thinh123";
    @Override
    public DistrictEntity findNameById(Integer id){
        String sql = "SELECT * FROM district d WHERE d.id = "+id+" ";
        DistrictEntity district=new DistrictEntity();
        try(Connection conn=DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stm =conn.createStatement();
            ResultSet rs=stm.executeQuery(sql.toString())){
            while(rs.next()) {
                district.setName(rs.getString("name"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return district;
    }
}
