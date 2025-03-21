package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;
import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{

	static final String DB_URL="jdbc:mysql://localhost:3306/estatebasic";
	static final String USER="root";
	static final String PASS="Thinh123";

	public static void joinTable(Map<String, Object> params, List<String> typeCode, StringBuilder sql){
		String staffId= (String) params.get("staffId");
		if(StringUtil.checkString(staffId)){
			sql.append(" INNER JOIN assignmentBuilding ab on b.id=ab.buildingId ");
		}
		if(typeCode!=null&& !typeCode.isEmpty()){
			sql.append(" INNER JOIN buildingRentType brt on b.id=brt.buildingId ");
			sql.append(" INNER JOIN rentType rt on rt.id = brt.rentTypeId ");
		}
		String rentAreaTo=(String) params.get("areaTo");
		String rentAreaFrom=(String)  params.get("areaFrom");
		if(StringUtil.checkString((rentAreaTo)) || StringUtil.checkString((rentAreaFrom))){
			sql.append(" INNER JOIN rentArea ra on ra.buildingId = b.id ");
		}
	}

	public static void queryNormal(Map<String, Object> params, StringBuilder where){//normal means interacting with fields inside the table
		for(Map.Entry<String, Object> item:params.entrySet()){
			if(!item.getKey().equals("staffId")&&
					!item.getKey().equals("typeCode")&&
					!item.getKey().startsWith("area")&&
					!item.getKey().equals("rentPrice")){
				String value=item.getValue().toString();
				if(NumberUtil.isNumber(value)){
					where.append(" AND b."+item.getKey()+" = "+value+" ");
				}
				else{
					where.append(" AND b."+item.getKey()+ " LIKE '%"+value+"%' ");
				}
			}
		}
	}

	public static void querySpecial(Map<String, Object> params, StringBuilder where, List<String> typeCode){//special means interacting with fields outside the table
		String staffId= (String) params.get("staffId");
		String rentAreaTo=(String) params.get("areaTo");
		String rentAreaFrom=(String)  params.get("areaFrom");
		String rentPriceTo=(String) params.get("rentPriceTo");
		String rentPriceFrom=(String)  params.get("rentPriceFrom");
		if(StringUtil.checkString(staffId)){
			where.append(" AND ab.staffId="+staffId+" ");
		}
		if(StringUtil.checkString(rentAreaFrom)||StringUtil.checkString(rentAreaTo)){
			if(StringUtil.checkString(rentAreaFrom)){
				where.append(" AND b.floorArea >="+rentAreaFrom+" ");
			}
			if(StringUtil.checkString(rentAreaTo)){
				where.append(" AND b.floorArea <="+rentAreaTo+" ");
			}
		}
		if(StringUtil.checkString(rentPriceFrom)||StringUtil.checkString(rentPriceTo)){
			if(StringUtil.checkString(rentPriceFrom)){
				where.append(" AND b.rentPrice >="+rentPriceFrom+" ");
			}
			if(StringUtil.checkString(rentPriceTo)){
				where.append(" AND b.rentPrice <="+rentPriceTo+" ");
			}
		}
		//java 7
		if(typeCode!=null&&!typeCode.isEmpty()){
			List<String> code=new ArrayList<>();
			for(String item:typeCode){
				code.add("'"+item+"'");
			}
			where.append(" AND rt.code IN("+String.join(",", code)+") ");
		}
	}

	
	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
		StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.districtId, b.floorArea, b.ward, b.street, " +
				"b.numberOfBasement, b.direction, b.level, b.rentPrice, b.managerName, b.managerPhoneNumber, " +
				"b.serviceFee, b.brokerageFee FROM building b ");
		joinTable(params, typeCode, sql);
		StringBuilder where= new StringBuilder(" WHERE 1=1 ");
		queryNormal(params, where);
		querySpecial(params, where, typeCode);
		where.append(" GROUP BY b.id ");

		sql.append(where);

		System.out.println(sql.toString());

		List<BuildingEntity> result=new ArrayList<>();

		try(Connection conn=DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stm =conn.createStatement();
				ResultSet rs=stm.executeQuery(sql.toString())){
			
			while(rs.next()) {
				BuildingEntity building=new BuildingEntity();
				building.setId(rs.getInt("id"));
				building.setName(rs.getString("name"));  // Tên tòa nhà
				building.setDistrictId(rs.getInt("districtId"));  // ID quận/huyện
				building.setStreet(rs.getString("street"));  // Đường phố
				building.setWard(rs.getString("ward"));  // Phường/xã
				building.setNumberOfBasement(rs.getInt("numberOfBasement"));  // Số tầng hầm
				building.setFloorArea(rs.getInt("floorArea"));
				building.setRentPrice(rs.getInt("rentPrice"));
				building.setManagerName(rs.getString("managerName"));
				building.setManagerPhoneNumber(rs.getString("managerPhoneNumber"));
				building.setServiceFee(rs.getString("serviceFee"));
				building.setBrokerageFee(rs.getFloat("brokerageFee"));


				result.add(building);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
