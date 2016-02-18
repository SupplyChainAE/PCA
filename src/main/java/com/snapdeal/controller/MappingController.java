package com.snapdeal.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.snapdeal.component.SessionDetails;
import com.snapdeal.dao.Dao;
import com.snapdeal.dto.FileBean;
import com.snapdeal.dto.ParameterPrice;
import com.snapdeal.dto.ParameterWeight;
import com.snapdeal.entity.Courier;
import com.snapdeal.entity.Warehouse;
import com.snapdeal.service.CourierService;
import com.snapdeal.service.WarehouseService;

@Controller
@RequestMapping("/Mapping")
public class MappingController {
	@Autowired
	Dao localDao;

	@Inject
	@Named("warehouseService")
	WarehouseService warehouseService;

	@Inject
	@Named("courierService")
	CourierService courierService;

	@Inject
	@Named("sessionDetails")
	SessionDetails sessionDetails;

	@RequestMapping(value = "/pincodeWarehouse")
	public String pincodeWarehouse(ModelMap map) {
		List<Warehouse> whList = warehouseService.getEnabledWarehouses();
		map.put("warehouse", whList);
		// map.put("name", sessionDetails.getSessionUser().getUsername());
		return "/uploadPincode";
	}

	@RequestMapping(value = "/courierAwb")
	public String courierAwb(ModelMap map) {
		// map.put("name", sessionDetails.getSessionUser().getUsername());

		List<Courier> crList = courierService.getEnabledCourier();
		map.put("courier", crList);
		return "/uploadAwb";
	}

	@RequestMapping(value = "/parameterPrice")
	public String parametersPrice(ModelMap map) {

		List<ParameterPrice> prList = localDao.getParameterPrice();
		map.put("parameterPrice", prList);

		return "/updatePrice";

	}

	@RequestMapping(value = "/parameterWeight")
	public String parametersWeight(ModelMap map) {

		List<ParameterWeight> prList = localDao.getParameterWeight();

		map.put("parameterWeight", prList);
		return "/updateWeight";

	}

	@RequestMapping(value = "/pincodeCourier")
	public String pincodeCourier(ModelMap map) {

		List<Courier> crList = courierService.getEnabledCourier();
		map.put("courier", crList);
		return "/uploadCourierPincode";
	}

	@RequestMapping("/saveParameterPrice")
	public String savePriority(@RequestParam("id") Long[] idList,
			@RequestParam("price") Double[] price, ModelMap map) {
		Long userId = sessionDetails.getSessionUser().getId();
		int i = 0;
		for (Long id : idList) {
			System.out.println(id);
			System.out.println(price[i]);

			localDao.updateParameterPrice(id, price[i], userId);
			i++;
		}
		map.put("message", "Data Saved Successfully");
		List<ParameterPrice> prList = localDao.getParameterPrice();
		map.put("parameterPrice", prList);
		return "/updatePrice";
	}

	@RequestMapping("/saveParameterWeight")
	public String saveWeight(@RequestParam("para") String[] parameterNames,
			@RequestParam("weight") Double[] weight, ModelMap map) {
		for (int i=0;i<parameterNames.length;i++) 
		{
			localDao.updateParameterWeight(parameterNames[i], weight[i]);
			System.out.println(parameterNames[i]+" : "+weight[i]);
		}

		map.put("message", "Data Saved Successfully");
		List<ParameterWeight> prList = localDao.getParameterWeight();
		map.put("parameterWeight", prList);

		return "/updateWeight";
	}

	@RequestMapping("/downloadPincodeTemplate")
	public void downloadPincode(ModelMap map, HttpServletResponse response)
			throws ParseException {
		try {
			String line = "Pincode";
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition",
					"attachment; filename=PincodeTemplate.csv");
			response.setContentLength(line.length());
			response.getWriter().write(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/updatePincode")
	public String updatePincode(@ModelAttribute("postedFile") FileBean file,
			@ModelAttribute("action") String action,
			@ModelAttribute("warehouse") Long whId, ModelMap map)
			throws ParseException {
		try {
			MultipartFile postedFile = file.getPostedFile();
			Long userId = sessionDetails.getSessionUser().getId();
			if (postedFile != null) {
				InputStream inputStream;

				inputStream = postedFile.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream));
				String line = "";
				int header = 1;
				int error = 0;
				while ((line = bufferedReader.readLine()) != null) {
					if (header == 1) {
						if (!line.trim().equals("Pincode")) {
							error = 1;
						}
						header = 0;
					} else {
						if (action.equals("Add")) {

							localDao.insertPincode(whId, line, userId);
							error = 2;
						} else {
							localDao.updatePincode(whId, line, userId);
							error = 2;
						}
					}
				}
				if (error == 1) {
					map.put("errorFlag", true);
					map.put("message", "Invalid File Uploaded!!");

				} else if (error == 2) {
					map.put("errorFlag", false);
					map.put("message", "Data saved Successfully");
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Warehouse> whList = warehouseService.getEnabledWarehouses();
		map.put("warehouse", whList);
		return "/uploadPincode";
	}

	@RequestMapping("/updateCourierPincode")
	public String updateCourierPincode(
			@ModelAttribute("postedFile") FileBean file,
			@RequestParam("action") String action,
			@RequestParam("courier") Long crId, ModelMap map) {

		try {
			MultipartFile postedFile = file.getPostedFile();
			Long userId = sessionDetails.getSessionUser().getId();
			if (postedFile != null) {
				InputStream inputStream;
				inputStream = postedFile.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream));
				String line = "";
				int header = 1;
				int error = 0;
				while ((line = bufferedReader.readLine()) != null) {
					System.out.println("line" + line);
					if (header == 1) {
						if (!line.trim().equals("Pincode")) {
							error = 1;
						}
						header = 0;
					} else {
						if (action.equals("Add")) {

							localDao.insertCourierPincode(crId, line, userId);
							error = 2;
						} else {
							localDao.updateCourierPincode(crId, line, userId);
							error = 2;
						}
					}
				}
				if (error == 1) {
					map.put("errorFlag", true);
					map.put("message", "Invalid File Uploaded!!");

				} else if (error == 2) {
					map.put("errorFlag", false);
					map.put("message", "Data saved Successfully");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Courier> crList = courierService.getEnabledCourier();
		map.put("courier", crList);
		return "/uploadCourierPincode";
	}

	@RequestMapping("/updateAwb")
	public String updateAwbRequest(@ModelAttribute("postedFile") FileBean file,
			@RequestParam("action") String action,
			@RequestParam("courier") Long crId, ModelMap map) {

		try {
			MultipartFile postedFile = file.getPostedFile();
			Long userId = sessionDetails.getSessionUser().getId();
			if (postedFile != null) {
				InputStream inputStream;
				inputStream = postedFile.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream));
				String line = "";
				String[] columns;
				int header = 1;
				int error = 0;
				while ((line = bufferedReader.readLine()) != null) {

					System.out.println("linenew" + line);
					if (header == 1) {
						if (!line.trim().equals("Awb No")
								|| !line.trim().equals("Reference Number")) {
							error = 1;
						}
						header = 0;
					} else {

						columns = line.split(",");

						if (line.trim().length() > 2) {
							if (action.equals("Add")) {

								localDao.insertAwb(crId, columns[0], userId,
										columns[1]);
								error = 2;
							} else {
								localDao.updateAwb(crId, columns[0], userId);
								error = 2;
							}
						}
					}
				}
				if (error == 1) {
					map.put("errorFlag", true);
					map.put("message", "Invalid File Uploaded!!");

				} else if (error == 2) {
					map.put("errorFlag", false);
					map.put("message", "Data saved Successfully");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Courier> crList = courierService.getEnabledCourier();
		map.put("courier", crList);
		return "/uploadAwb";
	}

	@RequestMapping("/downloadCourierTemplate")
	public void downloadCourier(ModelMap map, HttpServletResponse response)
			throws ParseException {
		try {
			String line = "Awb No,Reference Number";
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition",
					"attachment; filename=AwbTemplate.csv");
			response.setContentLength(line.length());
			response.getWriter().write(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
