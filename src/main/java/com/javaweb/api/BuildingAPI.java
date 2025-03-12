package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;

import customException.FieldRequiredException;

@RestController
public class BuildingAPI {

	@Autowired
	private BuildingService buildingService;
	
	@GetMapping(value = "api/building/") // new
	// @RequestMapping(value="/api/building", method= RequestMethod.GET)
	// @ResponseBody //tra ve theo cau truc JSON (co the dung Map hoac Beans)
	public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params) {
		List<BuildingDTO> result=buildingService.findAll(params);
		return result;
	}

	/*
	 * public Object getBuilding(@RequestParam(value="name", required=false) String
	 * name,
	 * 
	 * @RequestParam(value="numberOfBasement", required=false) Integer
	 * numberOfBasement,
	 * 
	 * @RequestParam(value="ward", required=false) String ward) {
	 * 
	 * BuildingDTO result=new BuildingDTO(); result.setName(name);
	 * result.setNumberOfBasement(numberOfBasement); result.setWard(ward);
	 * 
	 * List<BuildingDTO> listBuilding=new ArrayList<>(); BuildingDTO
	 * buildingDTO1=new BuildingDTO(); buildingDTO1.setName("ABC Building");
	 * buildingDTO1.setNumberOfBasement(10); buildingDTO1.setWard("Dinh Cong");
	 * BuildingDTO buildingDTO2=new BuildingDTO();
	 * buildingDTO2.setName("XYZ Building"); buildingDTO2.setNumberOfBasement(12);
	 * buildingDTO2.setWard("Le Trong Tan"); listBuilding.add(buildingDTO1);
	 * listBuilding.add(buildingDTO2);
	 * 
	 * //error try { System.out.print(5/0); } catch(Exception e){ ErrorResponeDTO
	 * errorResponeDTO=new ErrorResponeDTO();
	 * errorResponeDTO.setError(e.getMessage()); List<String> details=new
	 * ArrayList<>(); details.add("Cannot divide by 0");
	 * errorResponeDTO.setDetail(details); return errorResponeDTO; }
	 * 
	 * 
	 * return listBuilding; }
	 */

	public void valiDate(BuildingDTO buildingDTO)  {
		if(buildingDTO.getName()==null||buildingDTO.getName().equals("")||buildingDTO.getNumberOfBasement()==null) {
			throw new FieldRequiredException("The data is null");
		}

	}

	@RequestMapping(value = "/api/building", method = RequestMethod.POST) // old
	public void getBuilding2(@RequestBody BuildingDTO buildingDTO) {
		System.out.print("ok");
	}

	@DeleteMapping(value = "api/building/{id}")
	public void deleteBuilding(@PathVariable Integer id) {
		System.out.print("Da xoa toa nha");
	}

}