package com.snapdeal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.snapdeal.dto.ParameterWeight;
import com.snapdeal.dto.ParameterPrice;
import com.snapdeal.dto.Priority;
import com.snapdeal.dto.Request;
import com.snapdeal.dto.RequestDetails;

public class Dao {


	private DataSource dataSourceLocal;

	public DataSource getDataSourceLocal() {
		return dataSourceLocal;
	}


	public void setDataSourceLocal(DataSource dataSourceLocal) {
		this.dataSourceLocal = dataSourceLocal;
	}



	public List<Request> getRequestData(String condition)
	{
		Connection connection = null;
		Statement statement = null;
		List<Request> requestList = new ArrayList<Request>();

		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();

			String query = "SELECT id, concat_ws('/',sellercode,created,id)as requestid,SellerCode,seller_name,requeststatus,created,processed FROM Seller_Request sr " +condition ;
			System.out.println(query);
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()){
				Request req = new Request();
				req.setId(resultSet.getLong("id"));
				req.setRequestId(resultSet.getString("requestid"));
				req.setRequestStatus(resultSet.getString("requeststatus"));
				req.setSellerCode(resultSet.getString("SellerCode"));
				req.setSellerName(resultSet.getString("seller_name"));
				req.setCreatedOn(resultSet.getString("created"));
				req.setProcessed(resultSet.getString("processed"));
				requestList.add(req);
			}
			resultSet.close();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return requestList;
	}




	public List<RequestDetails>  getDownloadData(String cond)
	{
		Connection connection = null;
		Statement statement = null;
		List<RequestDetails> rd = new ArrayList<RequestDetails>();

		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();

			String query = "Select concat_ws('/',sellercode,sr.created,sr.id)as requestid,requestStatus," +
			" if (c.Code IS NULL,'',c.code) as courierCode,if (c.name IS NULL,'',c.name) as cn ," +
			" if (wh.code IS NULL,'',wh.code) as wc , if (wh.name IS NULL,'',wh.name) as wn, " +
			" if (wh.address1 IS NULL ,'',wh.address1) as wadd1, " +
			" if (wh.address2 IS NULL ,'', wh.address2) as wadd2," +
			" if (wh.city IS NULL ,'' ,wh.city) as wcity, " +
			" if (wh.pincode IS NULL,'',wh.pincode) as wpincode " +
			",SellerCode,seller_name,sr.created as created,seller_mobile,seller_email, " +
			" if (sr.ref_number IS NULL,'',sr.ref_number) as refNumber, REPLACE(seller_address, ',', ';') as seller_address,seller_city,seller_state,seller_pincode, REPLACE(svd.tin_number,',',';') as tin, " +
			" if (awb IS NULL ,'',awb) as awb," +
			"if (requeststatus = 'Request Created' || requeststatus = 'Request Processing' ,para1,para1d)  para1  ," +
			"if (requeststatus = 'Request Created' || requeststatus = 'Request Processing' ,para2,para2d) para2 , " +
			"if (requeststatus = 'Request Created' || requeststatus = 'Request Processing' ,para3,para3d)  para3 , "+
			"if (requeststatus = 'Request Created' || requeststatus = 'Request Processing' ,para4,para4d)  para4 , "+
			"if (requeststatus = 'Request Created' || requeststatus = 'Request Processing' ,para5,para5d)  para5 , "+
			"if (requeststatus = 'Request Created' || requeststatus = 'Request Processing' ,para6,para6d)  para6 , "+
			"if (requeststatus = 'Request Created' || requeststatus = 'Request Processing' ,para7,para7d)  para7 , "+
			"if (requeststatus = 'Request Created' || requeststatus = 'Request Processing' ,para8,para8d)  para8 , "+
			"if (requeststatus = 'Request Created' || requeststatus = 'Request Processing' ,para9,para9d)  para9 , "+
			"if (requeststatus = 'Request Created' || requeststatus = 'Request Processing' ,para10,para10d) para10 , " +
			"if (requeststatus = 'Request Created' || requeststatus = 'Request Processing' ,para11,para11d) para11 " +
			" from Seller_Request sr " +
			"Left join  couriers c on sr.courierCode = c.id " +
			"Left join warehouse wh on sr.warehouse = wh.id  " +
			"Left join shipping.vendor_detail svd on sr.SellerCode = svd.code  "+ cond;
			System.out.println(query);
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()){
				RequestDetails req = new RequestDetails();
				req.setId(resultSet.getString("requestid"));
				req.setCreated(resultSet.getString("created"));
				req.setStatus(resultSet.getString("requeststatus"));
				req.setWarehouseCode(resultSet.getString("wc"));
				req.setWarehouseName(resultSet.getString("wn"));
				req.setCourierCode(resultSet.getString("courierCode"));
				req.setCourierName(resultSet.getString("cn"));
				req.setSellerCode(resultSet.getString("SellerCode"));
				req.setSellerName(resultSet.getString("seller_name"));
				req.setSellerMobile(resultSet.getString("seller_mobile"));
				req.setSellerCity(resultSet.getString("seller_city"));
				req.setSellerState(resultSet.getString("seller_State"));
				req.setSellerAddress(resultSet.getString("seller_address"));
				req.setSellerEmail(resultSet.getString("seller_email"));
				req.setSellerPincode(resultSet.getString("seller_pincode"));
				req.setAwb(resultSet.getString("awb"));
				req.setPara1(resultSet.getLong("para1"));
				req.setTin(resultSet.getString("tin"));
				
				req.setPara2(resultSet.getLong("para2"));
				
				req.setPara3(resultSet.getLong("para3"));
				
				req.setPara4(resultSet.getLong("para4"));
				
				req.setPara5(resultSet.getLong("para5"));
				
				req.setPara6(resultSet.getLong("para6"));
				
				req.setPara7(resultSet.getLong("para7"));
				
				
				req.setPara8(resultSet.getLong("para8"));
				
				req.setPara9(resultSet.getLong("para9"));
				req.setPara10(resultSet.getLong("para10"));
				req.setPara11(resultSet.getLong("para11"));
				req.setRefNumber(resultSet.getString("refNumber"));
				req.setWarehouseAddress1(resultSet.getString("wadd1"));
				req.setWarehouseAddress2(resultSet.getString("wadd2"));
				req.setWarehouseCity(resultSet.getString("wcity"));
				req.setWarhousePincode(resultSet.getString("wpincode"));
				
				rd.add(req);
			}
			resultSet.close();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rd;
	}

	public RequestDetails  getRequestDataBasedonID(Long id)
	{
		Connection connection = null;
		Statement statement = null;
		RequestDetails req = new RequestDetails();
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();

			String query = "Select concat_ws('/',sellercode,sr.created,sr.id)as requestid,requestStatus,c.Code as courierCode,c.name as cn ,wh.code as wc ,wh.name as wn," +
			"sellerCode,seller_name,sr.created as created,seller_mobile,seller_email, REPLACE(seller_address, ',', ';') as seller_address,seller_city,seller_state,seller_pincode,awb,para1,para1d,para2,para2d,para3 "+
			" ,para3d,para4,para4d,para5,para5d,para6,para6d,para7,para7d,para8,para8d,para9,para9d,para10,para10d,para11,para11d " +
			" from Seller_Request sr Left join  couriers c on sr.courierCode = c.id Left join warehouse wh on sr.warehouse = wh.id  where sr.id = "+id;
			System.out.println(query);
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()){

				req.setWarehouseCode(resultSet.getString("wc"));
				req.setWarehouseName(resultSet.getString("wn"));
				req.setCourierCode(resultSet.getString("courierCode"));
				req.setCourierName(resultSet.getString("cn"));
				req.setAwb(resultSet.getString("awb"));
				req.setPara1(resultSet.getLong("para1"));
				req.setPara1d(resultSet.getLong("para1d"));
				req.setPara2(resultSet.getLong("para2"));
				req.setPara2d(resultSet.getLong("para2d"));

				req.setPara3(resultSet.getLong("para3"));
				req.setPara3d(resultSet.getLong("para3d"));

				req.setPara4(resultSet.getLong("para4"));
				req.setPara4d(resultSet.getLong("para4d"));

				req.setPara5(resultSet.getLong("para5"));
				req.setPara5d(resultSet.getLong("para5d"));

				req.setPara6(resultSet.getLong("para6"));
				req.setPara6d(resultSet.getLong("para6d"));

				req.setPara7(resultSet.getLong("para7"));
				req.setPara7d(resultSet.getLong("para7d"));

				req.setPara8(resultSet.getLong("para8"));
				req.setPara8d(resultSet.getLong("para8d"));

				req.setPara9(resultSet.getLong("para9"));
				req.setPara9d(resultSet.getLong("para9d"));
				req.setPara10(resultSet.getLong("para10"));
				req.setPara10d(resultSet.getLong("para10d"));
				req.setPara11(resultSet.getLong("para11"));
				req.setPara11d(resultSet.getLong("para11d"));

			}
			resultSet.close();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return req;
	}



	public Boolean UpdateData(String [] data,Long userId,Long courierId,Long warehouseId)
	{
		Connection connection = null;
		Statement statement = null;
		Boolean flag;

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		//		
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "";
			String[] id =data[0].split("/");
			System.out.println("length" + data.length);
			if (data.length == 17){
				query = "update Seller_Request set requeststatus = '"+ data[1] +"', "
				+"para1d = "+ data[5] +"  , para2d = "+ data[6] +" ,  para4d = "+ data[7] +" , " +
				"para5d = "+ data[8] + ", para6d = "+ data[9] + " , para7d = "+ data[10] +" , para8d = "+ data[13] +", para10d = "+ data[11] +" , para11d = " + data[12] +" , para9d = "+ data[14] +" , comment = '"+data[15] +"' , "+ 
				"courierCode = " + courierId +" , AWB = '" + data[3] + "' , mail_flag = 0 , updated = '"+ date + "' , warehouse  = " + warehouseId +" , userId =" + userId + " dispatch_date='"+data[16]+"' where id = "+ id[2] ;
				statement.executeUpdate(query);
				query="insert into Request_History(requestid,requeststatus,comment, created) values("+ id[2] +",'"+ data[1] +"','"+ data[15] +"','"+ date +"' )";
				System.out.println(query);
				statement.executeUpdate(query);
				flag =  true;


			}
			else{
				query = "update Seller_Request set requeststatus = '"+ data[1] +"', "
				+"para1d = "+ data[5] +"  , para2d = "+ data[6] +",   para4d = "+ data[7] +" , " +
				"para5d = "+ data[8] + ", para6d = "+ data[9] + " , para7d = "+ data[10] +" , para8d = " + data[13] +", para10d = "+ data[11] +" , para11d = " + data[12] +" , para9d = "+ data[14] +" , comment = ''  , "+ 
				" courierCode = " + courierId +" , AWB = '" + data[3] + "' , mail_flag = 0, updated = '"+ date + "' , warehouse  = " + warehouseId +" , userId =" + userId + " dispatch_date='' where id = "+ id[2] ;
				statement.executeUpdate(query);
				query="insert into Request_History(requestid,requeststatus,comment, created) values("+ id[2] +",'"+ data[1] +"',' ','"+ date +"' )";
				System.out.println(query);
				statement.executeUpdate(query);
				flag =  true;

			}
				
//			System.out.println(query);
			
		}catch(SQLException se){
			flag =  false;
			se.printStackTrace();
		}catch(Exception e){
			flag =  false;
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				flag =  false;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}


	public Boolean checkQty(String colName,String data,Long Qty)
	{
		Connection connection = null;
		Statement statement = null;
		Boolean flag;

		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String[] id = data.split("/");

			String query = "select id from Seller_Request where "  + colName + " < " + Qty +" and id = " +id[2] ;
			System.out.println("query "+query);
			ResultSet resultSet = statement.executeQuery(query);
			flag = true;
			while (resultSet.next()){
				flag = false;
			}

		}catch(SQLException se){
			flag =  false;
			se.printStackTrace();
		}catch(Exception e){
			flag =  false;
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				flag =  false;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}

	public void insertPincode(Long whId ,String pincode,Long userId)
	{
		Connection connection = null;
		Statement statement = null;

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		//		
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "insert into pincodeWarehouse(created,updated,warehouseId,Pincode,createdBy_id,updatedBy_id,enabled,processed) values('"+ date +"','"+ date +"',"+ whId +",'"+ pincode+"',"+userId+","+userId+",1,0)";
			System.out.println(query);
			statement.executeUpdate(query);
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public void insertAwb(Long crId ,String awb,Long userId,String refNumber)
	{
		Connection connection = null;
		Statement statement = null;

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		//		
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "insert into courierAwb(created,updated,courierId,awb,ref_number,createdBy_id,updatedBy_id,enabled,processed) values('"+ date +"','"+ date +"',"+ crId +",'"+ awb +"','"+refNumber+"',"+userId+","+userId+",1,0)";
			statement.executeUpdate(query);
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void insertCourierPincode(Long crId ,String awb,Long userId)
	{
		Connection connection = null;
		Statement statement = null;

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		//		
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "insert into courierPincode(created,updated,courierId,pincode,createdBy_id,updatedBy_id,enabled) values('"+ date +"','"+ date +"',"+ crId +",'"+ awb +"',"+userId+","+userId+",1)";
			statement.executeUpdate(query);
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	public void updatePincode(Long whId ,String pincode,Long userId)
	{
		Connection connection = null;
		Statement statement = null;

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		//		
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "update pincodeWarehouse set updated = '"+ date +"' ,updatedBy_id ="+userId+"  ,enabled = 0 where pincode = '"+ pincode+"' and warehouseId = "+ whId ;
			System.out.println(query);
			statement.executeUpdate(query);

		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}


	public void updateAwb(Long crId ,String awb,Long userId)
	{
		Connection connection = null;
		Statement statement = null;

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		//		
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "update courierAwb set updated = '"+ date +"' ,updatedBy_id ="+userId+"  ,enabled = 0 where awb = '"+ awb+"' and courierId = "+ crId;
			System.out.println(query);
			statement.executeUpdate(query);

		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}
	
	public void updateCourierPincode(Long crId ,String pincode,Long userId)
	{
		Connection connection = null;
		Statement statement = null;

		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		//		
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "update courierPincode set updated = '"+ date +"' ,updatedBy_id ="+userId+"  ,enabled = 0 where pincode = '"+ pincode +"' and courierId = "+ crId;
			System.out.println(query);
			statement.executeUpdate(query);

		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

	public List<Priority> getPriorityData(String type)
	{
		Connection connection = null;
		Statement statement = null;
		List<Priority> prList =new ArrayList<Priority>();
	
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "Select id,code,name,priority,load_limit from couriers where type = '"+ type +"' order by priority";
			System.out.println(query);
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()){
				Priority pr = new Priority();
				pr.setId(resultSet.getLong("id"));
				pr.setCode(resultSet.getString("code"));
				pr.setName(resultSet.getString("name"));
				pr.setPriority(resultSet.getLong("priority"));
				pr.setLoad(resultSet.getLong("load_limit"));
				prList.add(pr);
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return prList;		

	}

	public List<ParameterPrice> getParameterPrice()
	{
		Connection connection = null;
		Statement statement = null;
		List<ParameterPrice> prList =new ArrayList<ParameterPrice>();
		//		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		//		
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "Select id,parameter,price from parameterPrice  order by id";
			System.out.println(query);
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()){
				ParameterPrice pr = new ParameterPrice();
				pr.setId(resultSet.getLong("id"));
				pr.setParameterName(resultSet.getString("parameter"));
				pr.setPrice(resultSet.getDouble("price"));
				prList.add(pr);
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return prList;		

	}

	
	
	public void updatePriority(Long id, Long priority,Long load) {

		Connection connection = null;
		Statement statement = null;
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "Update  couriers set priority = " + priority + ", load_limit = "+ load +" where id = "+ id ;
			statement.executeUpdate(query);
		}
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updateParameterPrice(Long id, Double price,Long userID) {

		Connection connection = null;
		Statement statement = null;
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "Update  parameterPrice set price = " + price + ",user = " + userID +"  where id = "+ id ;
			statement.executeUpdate(query);
		}
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void UpdateStatus(Long userId,Long requestId,String status)
	{
		Connection connection = null;
		Statement statement = null;
	
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		//		
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "update Seller_Request set requeststatus = '"+ status +"', updated = '"+ date + "' , userId =" + userId + " where id = "+ requestId ;
			
			System.out.println(query);
			statement.executeUpdate(query);
			query="insert into Request_History(requestid,requeststatus,created) values("+ requestId +",'"+ status +"','"+ date +"' )";
			System.out.println(query);
			statement.executeUpdate(query);
			

		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}


	public List<ParameterWeight> getParameterWeight() {
		Connection connection = null;
		Statement statement = null;
		List<ParameterWeight> prList =new ArrayList<ParameterWeight>();
		
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "Select parameter,weight,name from parameterWeight";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()){
				ParameterWeight pr = new ParameterWeight();
				pr.setParameter(resultSet.getString("parameter"));
				pr.setWeight(resultSet.getDouble("weight"));
				pr.setName(resultSet.getString("name"));
				prList.add(pr);
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return prList;		
	}
	
	public void updateParameterWeight(String parameter,Double weight) {

		Connection connection = null;
		Statement statement = null;
		try{
			connection = (Connection) dataSourceLocal.getConnection();
			statement = (Statement) connection.createStatement();
			String query = "Update  parameterWeight set weight = " + weight + " where parameter = '"+ parameter +"'";
			statement.executeUpdate(query);
		}
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
	}
}
	