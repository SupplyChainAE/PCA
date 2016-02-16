package com.snapdeal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snapdeal.dao.Dao;
import com.snapdeal.dto.Priority;
import com.snapdeal.entity.BaseEntity;
import com.snapdeal.entity.Courier;
import com.snapdeal.entity.Warehouse;
import com.snapdeal.service.CourierService;
import com.snapdeal.service.WarehouseService;

@Controller
@RequestMapping("/Courier")
public class CourierController {
	@Autowired
	Dao localDao;
	
	@Inject
	@Named("courierService")
	CourierService courierService;
//	
//	@Inject
//	@Named("shippingService")
//	ShippingService shippingService;
//	
//	@Inject
//	@Named("trackingNumbersService")
//	TrackingNumbersService trackingNumbersService;
//	
	@Inject
	@Named("warehouseService")
	WarehouseService warehouseService;
	
	/** Loads the courier creation page **/
	@RequestMapping("/create")
	public String createCourier(ModelMap map){
		/** Get all Enabled warehouse from the DB **/
		List<Warehouse> warehouseList = warehouseService.getEnabledWarehouses(); 
//		Courier courier = new Courier();
//		
		map.put("warehouseList",warehouseList);
//		map.put("courier",courier);		
		return "Courier/create";
	}
	
//	/** Check for the existing email in the database corresponding to a courier entity.**/
//	@RequestMapping("/checkCourierEmail")
//	public @ResponseBody String checkCourierEmail(@ModelAttribute("email") String email, ModelMap map){
//		boolean result = courierService.checkCourierByEmail(email);
//		
//		/** If exists then return false to the jquery validator else true.**/
//		if(result)
//			return "false";
//		else
//			return "true";
//	}
//	
//	/** Create and save courier after successful completion of courier details. **/
	@RequestMapping("/save")
	public String saveCourier(@ModelAttribute("courier") Courier courier, @RequestParam(value="warehouse[]",required=false) Long[] warehouse, ModelMap map){
		try {
			
			List<Warehouse> newWarehouseList = null;
			System.out.println("type"+courier.getType());
			if(warehouse != null)
				newWarehouseList = getListFromArray(warehouse,Warehouse.class);
			
			if(courier.getId() != null)
			{
				Courier persistedCourier = courierService.findCourierByid(courier.getId());
				persistedCourier.setName(courier.getName());
				persistedCourier.setCode(courier.getCode());
				persistedCourier.setType(courier.getType());
				persistedCourier.setWarehouseList(newWarehouseList);
				courierService.saveCourier(persistedCourier);
			}
			else {
				courier.setWarehouseList(newWarehouseList);
				courier.setEnabled(true);
				courierService.saveCourier(courier);
				map.put("message", "Courier saved succesfully ");
				
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		List<Courier> courier1 = courierService.getAllcourier();
		map.put("courier", courier1);
		return "Courier/view";
	}
		
		@RequestMapping("/savePriority")
		public String savePriority(@RequestParam("id") Long[] idList,@RequestParam("priority") Long[] priorityList, ModelMap map){
			int i = 0;
			for (Long id : idList){
				System.out.println(id);
				System.out.println(priorityList[i]);
				localDao.updatePriority(id,priorityList[i]);
				i++;
				
			}
		/** Get all Enabled warehouse from the DB **/
//		List<Courier> courier1 = courierService.getAllcourier();
//		map.put("courier", courier1);
			
		map.put("message", "Data Saved Successfully");
		return "Courier/priority";
	}
	
	
//	@RequestMapping("/getData")
//	public @ResponseBody RequestDetails getData(@RequestParam("id") Long id,ModelMap map) throws ParseException{
//		RequestDetails rd = localDao.getRequestDataBasedonID(id);
//		
//	    return rd;
//		
//	}
//	

	
	
	@RequestMapping("/priority")
	public String priority(ModelMap map)
	{
//		List<Courier> courier = courierService.getCourierByType(type);
//		map.put("courier", courier);
//		System.out.println("courier"+ courier.get(0));
		return "Courier/priority";
	}
	
	
	@RequestMapping("/getPriorityData")
	public @ResponseBody List<Priority> getPriorityData(@RequestParam("type") String type,ModelMap map)
	{
		System.out.println("type"+ type);
		
		List<Priority> prList = localDao.getPriorityData(type);
		return prList;
	}
	
	
	@RequestMapping("/view")
	public String viewAllWarehouse(ModelMap map)
	{
		List<Courier> courier = courierService.getAllcourier();
		map.put("courier", courier);
		return "Courier/view";
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String editCourier(@PathVariable("id") Long id,ModelMap map)
	{
		Courier courier = courierService.findCourierByid(id);
		map.put("courier",courier);
		
		map.put("edit", true);
		List<Warehouse> warehouseList = warehouseService.getEnabledWarehouses(); 
//		Courier courier = new Courier();
//		
		map.put("warehouseList",warehouseList);
		
		return "Courier/create";
	}

	@RequestMapping(value="/disable/{id}",method=RequestMethod.GET)
	public String disableCourier(@PathVariable("id") Long id,ModelMap map)
	{
		courierService.disableCourier(id);
		List<Courier> courier = courierService.getAllcourier();
		map.put("courier", courier);
		map.put("message", "Courier disabled successfully");
		return "Courier/view";
	}

	@RequestMapping(value="/enable/{id}",method=RequestMethod.GET)
	public String enableCourier(@PathVariable("id") Long id,ModelMap map)
	{
		courierService.enableCourier(id);
		List<Courier> courier = courierService.getAllcourier();
		map.put("courier", courier);
		map.put("message", "Courier enabled successfully");
		return "Courier/view";
	}

	
	@RequestMapping("/checkName")
	public @ResponseBody String checkName(@ModelAttribute("name") String courierName)
	{
		boolean result = courierService.checkName(courierName);
		if(result)
		{
			return "success";
		}
		else{
			return "failure";
		}
	}

	@RequestMapping("/checkCode")
	public @ResponseBody String checkCode(@ModelAttribute("code") String courierCode)
	{
		boolean result = courierService.checkCode(courierCode);
		if(result)
		{
			return "success";
		}
		else{
			return "failure";
		}
	}
	
	
//	
//	/** Get both enabled and disabled courier from database.**/
//	@RequestMapping("/search")
//	public String searchCourier(ModelMap map){
//		List<String> courierCodeList = courierService.getAllCourierCode();
//		map.put("courierCodeList", courierCodeList);
//		return "Courier/search";
//	}
//	
//	/** Get the courier details from the database corresponding to a particular courier code. **/
//	@RequestMapping("/info")
//	public String showCourierInfo(@RequestParam("code") String code, ModelMap map){
//		List<String> courierCodeList = courierService.getAllCourierCode();
//		map.put("courierCodeList", courierCodeList);
//		
//		Courier courier = courierService.searchCourierByCode(code);
//		if(courier == null)
//			map.put("message", "Courier not found");
//		else
//			map.put("courier", courier);
//		
//		/** Get all Enabled warehouse from the DB **/
//		List<Warehouse> warehouseList = warehouseService.getEnabledWarehouses();		
//		map.put("warehouseList",warehouseList);
//		
//		return "Courier/search";
//	}
//	
//	/** Update the courier details.**/
//	@RequestMapping("/update")
//	public String updateCourier(@ModelAttribute Courier courier, @RequestParam(value="warehouse[]",required=false) Long[] warehouse, ModelMap map){
//		List<String> courierCodeList = courierService.getAllCourierCode();
//		map.put("courierCodeList", courierCodeList);
//		
//		try {	
//			List<Warehouse> newWarehouseList = null;
//			if(warehouse != null)
//				newWarehouseList = getListFromArray(warehouse,Warehouse.class);
//			else
//				newWarehouseList = warehouseService.getEnabledWarehouses();
//			
//			courier.setWarehouseList(newWarehouseList);
//			
//			courierService.updateCourier(courier);
//			map.put("messageUpdate", "Courier details updated successfully");
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//		
//		/** Get all Enabled warehouse from the DB **/
//		List<Warehouse> warehouseList = warehouseService.getEnabledWarehouses();		
//		map.put("warehouseList",warehouseList);
//		return "Courier/search";
//	}
//	
//	/** Loads the shipping Location page **/
//	@RequestMapping("/shippingLocation")
//	public String shippingLocation(ModelMap map){
//		FileBean file = new FileBean();
//		map.put("fileData", file);
//		return "Courier/shippingLocation";
//	}
//	
//	/** Save the shipping Location **/
//	@RequestMapping("/saveLocation")
//	public String saveLocation(@ModelAttribute("fileData") FileBean file,ModelMap map){
//		
//		/** Get the posted file containing - courier code and pincode. **/
//		MultipartFile postedFile = file.getPostedFile();
//		if(postedFile != null)
//		{
//			InputStream inputStream;
//			try {
//				inputStream = postedFile.getInputStream();
//				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//				String line = "";
//				String splitBy = ",";
//				int header = 1;
//				int error = 0;
//
//				/** Get the enabled courier code List from database. **/
//				List<String> courierCodeList = courierService.getAllEnabledCourierCode();
//				
//				List<String> areaCodeList = new ArrayList<String>();
//				List<Set<String>> pincodeList = new ArrayList<Set<String>>();
//				String courierCode = new String();
//				
//				while ((line = bufferedReader.readLine()) != null)
//				{
//					String [] data = line.split(splitBy);
//					if(header == 1)
//					{
//						/** Checks the file header which must contains - Courier code, Pincode, Area Code **/
//						//System.out.println(data[0] + " " + data[1] + " " + data[2]);
//						if(!data[0].equals("Courier Code")||!data[1].equals("Pincode")||!data[2].equals("Area Code"))
//						{
//							error = 1;
//							break;
//						}
//						else{
//							header = 2;
//						}
//					}
//					else if(header == 2){
//						/** Checks the provided courier code is enabled or not from courierCodeList **/
//						if(courierCodeList.contains(data[0])){
//							courierCode = data[0];
//							header = -1;
//						}
//						else{
//							error = 2;
//							break;
//						}
//					}
//					if(header == -1){
//						/** Checks that courier code is same for whole one file and Pincode must not be null **/
//						if(data.length >= 2 && courierCode.equals(data[0]) && !data[1].equals(null) && !data[1].equals("")){
//							String areaCode;
//							String pincode = data[1];
//							if(data.length < 3 || data[2].equals(null) || data[2].equals("")){
//								areaCode = new String("");
//							}
//							else
//								areaCode = data[2];
//							
//							if(areaCodeList.contains(areaCode)){
//								int position = areaCodeList.indexOf(areaCode);
//								pincodeList.get(position).add(pincode);
//							}
//							else{
//								areaCodeList.add(areaCode);
//								Set<String> pincodeSet = new HashSet<String>();
//								pincodeSet.add(pincode);
//								pincodeList.add(pincodeSet);
//							}
//						}
//					}
//				}
//				if(error == 1){
//					map.put("message", "Invalid file uploaded.");
//				}
//				else if(error == 2){
//					map.put("message", "Courier code not present.");
//				}
//				else{
//					/** Save the shipping Location and input provided are - courier code and pincode list **/
//					shippingService.saveShippingLocation(courierCode,pincodeList,areaCodeList);
//					map.put("success", true);
//					map.put("message","Records saved successfully of courier code : " + courierCode);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		else {
//			map.put("message", "Empty File Uploaded.");
//		}
//		
//		FileBean newFile = new FileBean();
//		map.put("fileData", newFile);
//		
//		return "Courier/shippingLocation";
//	}
//	
//	/** Generate the template file for shipping Location. **/
//	@RequestMapping("/templateShippingLocation")
//	public void templateShippingLocation(HttpServletResponse response){
//		try {
//			String line="Courier Code,Pincode,Area Code\n";
//			response.setContentType("text/csv");
//			response.setHeader("Content-Disposition", "attachment; filename=Template.csv");
//			response.setContentLength(line.length());
//			response.getWriter().write(line);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/** Loads the tracking numbers page. **/
////	@RequestMapping("/trackingNumbers")
////	public String trackingNumbers(ModelMap map){
////		FileBean file = new FileBean();
////		map.put("fileData", file);
////		return "Courier/trackingNumbers";
////	}
//	
//	/** Loads and Save the tracking numbers into database **/
//	@RequestMapping("/trackingNumbers")
//	public String saveTrackingNumbers(@ModelAttribute("fileData") FileBean file,ModelMap map){
//		
//		/** Get the posted file containing - courier code and tracking numbers. **/
//		MultipartFile postedFile = file.getPostedFile();
//		if(postedFile != null)
//		{
//			InputStream inputStream;
//			try {
//				inputStream = postedFile.getInputStream();
//				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//				String line = "";
//				String splitBy = ",";
//				int header = 1;
//				int error = 0;
//
//				/** Get the enabled courier code List from database. **/
//				List<String> courierCodeList = courierService.getAllEnabledCourierCode();
//				List<String> trackingNumberList = new ArrayList<String>();
//				String courierCode = new String();
//				
//				while ((line = bufferedReader.readLine()) != null)
//				{
//					String [] data = line.split(splitBy);
//					if(header == 1)
//					{
//						/** Checks the file header which must contains - Courier code and Tracking Number **/
//						if(!data[0].equals("Courier Code")||!data[1].equals("Tracking Number"))
//						{
//							error = 1;
//							break;
//						}
//						else{
//							header = 2;
//						}
//					}
//					else if(header == 2){
//						/** Checks the provided courier code is enabled or not from courierCodeList **/
//						if(courierCodeList.contains(data[0])){
//							courierCode = data[0];
//							header = -1;
//						}
//						else{
//							error = 2;
//							break;
//						}
//					}
//					if(header == -1){
//						/** Checks that courier code is same for whole one file and tracking numbers must not be null **/
//						if(courierCode.equals(data[0]) && data[1] != null && data[1] != ""){
//							trackingNumberList.add(data[1]);
//						}
//					}
//				}
//				if(error == 1){
//					map.put("message", "Invalid file uploaded.");
//				}
//				else if(error == 2){
//					map.put("message", "Courier code not present.");
//				}
//				else{
//					/** Save the tracking numbers and input provided are - courier code and tracking number list **/
//					trackingNumbersService.saveTrackingNubmers(courierCode, trackingNumberList);
//					map.put("success", true);
//					map.put("message","Records saved successfully of courier code : " + courierCode);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
////		else {
////			map.put("message", "Empty File Uploaded.");
////		}
//		
//		FileBean newFile = new FileBean();
//		map.put("fileData", newFile);
//		
//		return "Courier/trackingNumbers";
//	}
//	
//	/** Loads the tracking numbers page. **/
//	@RequestMapping("/trackingNumbers/remove")
//	public String removeTrackingNumbers(@ModelAttribute("fileData") FileBean file,ModelMap map){
//		
//		/** Get the posted file containing - courier code and tracking numbers. **/
//		MultipartFile postedFile = file.getPostedFile();
//		if(postedFile != null)
//		{
//			InputStream inputStream;
//			try {
//				inputStream = postedFile.getInputStream();
//				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//				String line = "";
//				String splitBy = ",";
//				int header = 1;
//				int error = 0;
//
//				/** Get the enabled courier code List from database. **/
//				List<String> courierCodeList = courierService.getAllEnabledCourierCode();
//				List<String> trackingNumberList = new ArrayList<String>();
//				String courierCode = new String();
//				
//				while ((line = bufferedReader.readLine()) != null)
//				{
//					String [] data = line.split(splitBy);
//					if(header == 1)
//					{
//						/** Checks the file header which must contains - Courier code and Tracking Number **/
//						if(!data[0].equals("Courier Code")||!data[1].equals("Tracking Number"))
//						{
//							error = 1;
//							break;
//						}
//						else{
//							header = 2;
//						}
//					}
//					else if(header == 2){
//						/** Checks the provided courier code is enabled or not from courierCodeList **/
//						if(courierCodeList.contains(data[0])){
//							courierCode = data[0];
//							header = -1;
//						}
//						else{
//							error = 2;
//							break;
//						}
//					}
//					if(header == -1){
//						/** Checks that courier code is same for whole one file and tracking numbers must not be null **/
//						if(courierCode.equals(data[0]) && data[1] != null && data[1] != ""){
//							trackingNumberList.add(data[1]);
//						}
//					}
//				}
//				if(error == 1){
//					map.put("message", "Invalid file uploaded.");
//				}
//				else if(error == 2){
//					map.put("message", "Courier code not present.");
//				}
//				else{
//					/** Save the tracking numbers and input provided are - courier code and tracking number list **/
//					trackingNumbersService.removeTrackingNubmers(courierCode, trackingNumberList);
//					map.put("success", true);
//					map.put("message","Records saved successfully of courier code : " + courierCode);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		FileBean newFile = new FileBean();
//		map.put("fileData", newFile);
//		
//		return "Courier/removeTrackingNumbers";
//	}
//
//	
//	/** Generate the template file for tracking numbers. **/
//	@RequestMapping("/templateTrackingNumbers")
//	public void templateTrackingNumbers(HttpServletResponse response){
//		try {
//			String line="Courier Code,Tracking Number\n";
//			response.setContentType("text/csv");
//			response.setHeader("Content-Disposition", "attachment; filename=Template.csv");
//			response.setContentLength(line.length());
//			response.getWriter().write(line);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
	public static<T extends BaseEntity> List<T> getListFromArray(Long [] objectIds,Class<T> objectClass) throws InstantiationException, IllegalAccessException {
		if(objectIds != null)
		{
			List<T> finalList = new ArrayList<T>();
			for(Long id : objectIds)
			{
				T object = objectClass.newInstance();
				object.setId(id);
				finalList.add(object);
			}
			return finalList;
		}
		else{
			return null;
		}
	}
}
