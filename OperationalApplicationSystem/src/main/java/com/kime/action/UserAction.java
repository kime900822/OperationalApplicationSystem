package com.kime.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.base.ActionBase;
import com.kime.biz.DepartmentBIZ;
import com.kime.biz.MenuBIZ;
import com.kime.biz.RoleBIZ;
import com.kime.biz.UserBIZ;
import com.kime.infoenum.Message;
import com.kime.model.Dict;
import com.kime.model.Menu;
import com.kime.model.QueryResult;
import com.kime.model.Result;
import com.kime.model.User;
import com.kime.utils.LogUtil;
import com.kime.utils.PropertiesUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class UserAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserBIZ userBIZ;
	@Autowired
	private User user;
	@Autowired
	private RoleBIZ roleBIZ;
	@Autowired
	private DepartmentBIZ departmentBIZ;
	@Autowired
	private MenuBIZ menuBIZ;

	private String name;
	private String password;
	private String position;
	private String oldpassword;
	private String rid;
	private String uid;
	private String date;
	private String email;
	private String did;
	private String quitDate;
	private String adName;


	public String getQuitDate() {
		return quitDate;
	}
	public void setQuitDate(String quitDate) {
		this.quitDate = quitDate;
	}
	public MenuBIZ getMenuBIZ() {
		return menuBIZ;
	}
	public void setMenuBIZ(MenuBIZ menuBIZ) {
		this.menuBIZ = menuBIZ;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public DepartmentBIZ getDepartmentBIZ() {
		return departmentBIZ;
	}
	public void setDepartmentBIZ(DepartmentBIZ departmentBIZ) {
		this.departmentBIZ = departmentBIZ;
	}
	public UserBIZ getUserBIZ() {
		return userBIZ;
	}
	public void setUserBIZ(UserBIZ userBIZ) {
		this.userBIZ = userBIZ;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public RoleBIZ getRoleBIZ() {
		return roleBIZ;
	}
	public void setRoleBIZ(RoleBIZ roleBIZ) {
		this.roleBIZ = roleBIZ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}


	/**
	 * 用户登录
	 * @return
	 */
	
	@Action(value="login",
			results={@org.apache.struts2.convention.annotation.Result(name="success",location="/UI/index.jsp"),
			@org.apache.struts2.convention.annotation.Result(name="error",location="/UI/login.jsp")})
	public String Login(){
	
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		session.setMaxInactiveInterval(30*60);

		session.removeAttribute("login_message");
		
		String err_message = null;
		try {
			if (uid==null||password==null) {
				return ERROR;
			}
			
			if ("".equals(uid)||"".equals(password)) {
				err_message="Please enter your id and password";
			}else{
				user=userBIZ.login(uid, password);
				if (user==null) {
					err_message="Id or password wrong！";
					session.setAttribute("login_message", "User id or password error");
					logUtil.logInfo("登录失败！"+err_message.toString());
					return ERROR;
				}else{
					//获取菜单
					if (PropertiesUtil.ReadProperties(Message.SYSTEM_PROPERTIES, "id").equals(user.getUid())) {
						List lMenus=menuBIZ.getParentMenu();
						session.setAttribute("parentMenu", lMenus); 
						for (Object object : lMenus) {
							Menu m=(Menu)object;
							String string=menuBIZ.getChildMenu(m.getId());
							session.setAttribute(m.getId(), string); 
						}
						
					}else{			
						List<Menu> lMenus=menuBIZ.getParentMenuByRole(user.getRole().getName());
						session.setAttribute("parentMenu", lMenus); 
						for (Menu m : lMenus) {
							String string=menuBIZ.getChildMenu_R(m.getId(), user.getRole().getName());
							session.setAttribute(m.getId(), string); 
						}
						
					}
					
				}
				
			}


			
		} catch (Exception e1) {
			err_message=e1.getMessage();	
			e1.printStackTrace();
		}
		
		if (err_message==null) {
			session.removeAttribute("user");
			session.setAttribute("user", user);
			logUtil.logInfo("登录成功！");
			return SUCCESS;
			
		}else{
			session.setAttribute("login_message", err_message.toString());
			logUtil.logInfo("登录失败！"+err_message.toString());
			return ERROR;
			
		}
		
		
	}
	
	
	/**
	 * 注册账号
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Action(value="register",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String Register() throws UnsupportedEncodingException{
		
		Date d1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:dd");
		user.setUid(uid);
		user.setName(name);
		user.setPassword(password);
		user.setPosition(position);
		user.setRid(Message.NORMAL_USER);
		user.setDate(sdf.format(d1));
		user.setEmail(email);
		
		try {
			String rString=userBIZ.register(user);
			if (rString.equals("1")) {
				logUtil.logInfo("注册成功");
				result.setMessage(Message.REGISTER_MESSAGE_SUCCESS);
				result.setStatusCode("200");
			}else{
				logUtil.logInfo(rString);
				result.setMessage(rString);
				result.setStatusCode("300");				
			}
			
		} catch (Exception e1) {
			result.setMessage("注册失败！"+e1.getMessage());
			logUtil.logInfo(e1.getMessage());
			result.setStatusCode("300");
			e1.printStackTrace();
		}
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	@Action(value="forgetPassword",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String forgetPassword() throws UnsupportedEncodingException{
		
		List<User> list=userBIZ.getUser(" where uid='"+uid+"'");
		if (list.size()>0) {
			try {
				String string=userBIZ.forgetPassword(list.get(0));
				if (string.equals("1")) {
					result.setMessage("Success");
					result.setStatusCode("200");
				}else{					
					result.setMessage(string);
					result.setStatusCode("200");
				}
				
			} catch (Exception e1) {
				result.setMessage(e1.getMessage());
				result.setStatusCode("300");
			}	
		}
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	/**
	 * 修改密码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Action(value="change",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String Change() throws UnsupportedEncodingException{
		ActionContext actionContext = ActionContext.getContext();  
        Map session = actionContext.getSession();  
        user=(User)session.get("user");
		if (oldpassword.equals(user.getPassword())) {	
			if (!password.equals(user.getPassword())) {
				user.setPassword(password);
				try {
					userBIZ.modUser(user);
					logUtil.logInfo("修改密码成功");
					result.setMessage(Message.MOD_MESSAGE_SUCCESS);
					result.setStatusCode("200");
				} catch (Exception e1) {
					result.setMessage(e1.getMessage());
					logUtil.logInfo("修改密码失败！"+e1.getMessage());
					result.setStatusCode("300");
					e1.printStackTrace();
				}
			}else{
				result.setMessage(Message.MOD_MESSAGE_ERROR_REGISTER_1);
				result.setStatusCode("300");
			}
	
		}
		else{
			result.setMessage(Message.MOD_MESSAGE_ERROR_REGISTER_2);
			result.setStatusCode("300");
		}
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	/**
	 * 注销登录
	 * @return
	 */
	@Action(value="logout",results={@org.apache.struts2.convention.annotation.Result(name="success",location="/UI/login.jsp")})
	public String Logout(){
		
		ActionContext actionContext = ActionContext.getContext();  
        Map session = actionContext.getSession();  
        session.clear();
        logUtil.logInfo("注销登陆");
        return SUCCESS;
		
	}
	
	/**
	 * 用户查询
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Action(value="getUser",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String GetUser() throws UnsupportedEncodingException{
		
		String where="";
		if (!"".equals(rid)&&rid!=null) {
			where += " rid='"+rid+"'";
		}
		if (!"".equals(name)&&name!=null) {
			if (!"".equals(where)) {
				where +=" and ";
			}
			where += " name like '%"+name+"%'";
		}
		if (!"".equals(uid)&&uid!=null) {
			if (!"".equals(where)) {
				where +=" and ";
			}
			where += " uid like '%"+uid+"%'";
		}
		if (!"".equals(did)&&did!=null) {
			if (!"".equals(where)) {
				where +=" and ";
			}
			where += " did ='"+did+"'";
		}
		
		if (!"".equals(position)&&position!=null) {
			if (!"".equals(where)) {
				where +=" and ";
			}
			where += " position = '"+position+"'";
		}
		
		if (!"".equals(date)&&date!=null) {
			if (!"".equals(where)) {
				where +=" and ";
			}
			where += " date = '"+date+"'";
		}
		
		if (!"".equals(email)&&email!=null) {
			if (!"".equals(where)) {
				where +=" and ";
			}
			where += " email like '%"+email+"%'";
		}
		
		if (!"".equals(where)) {
			where =" where "+where;
		}
				
		
		List<User> luser=userBIZ.getUser(where,Integer.parseInt(pageSize),Integer.parseInt(pageCurrent));
		int total=userBIZ.getUser(where).size();
		
		queryResult.setList(luser);
		queryResult.setTotalRow(total);
		queryResult.setFirstPage(Integer.parseInt(pageCurrent)==1?true:false);
		queryResult.setPageNumber(Integer.parseInt(pageCurrent));
		queryResult.setLastPage(total/Integer.parseInt(pageSize) +1==Integer.parseInt(pageCurrent)&&Integer.parseInt(pageCurrent)!=1?true:false);
		queryResult.setTotalPage(total/Integer.parseInt(pageSize) +1);
		queryResult.setPageSize(Integer.parseInt(pageSize));
		String r=callback+"("+new Gson().toJson(queryResult)+")";
		
		reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		
		logUtil.logInfo("查询用户信息，条件:"+where);
		return SUCCESS;
	}
	
	/**
	 * 用户编辑（删除，保存）
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Action(value="modUser",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String ModUser() throws UnsupportedEncodingException{
		
		Date d1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		user.setName(name);
		user.setPassword(password);
		user.setPosition(position);
		user.setDid(did);
		user.setRid(rid);
		user.setDate(sdf.format(d1));
		user.setUid(uid);
		user.setEmail(email);
		user.setQuitDate(quitDate);
		user.setAdName(adName);
		boolean b=true;
		String rString="";
		try {			
			if (uid==null||"".equals(uid)) {
				rString=userBIZ.register(user);		

			}else{
				rString=userBIZ.modUser(user);
			}			
			
			if (rString.equals("1")) {
				result.setMessage("Success");
				result.setStatusCode("200");
				user=userBIZ.getUser(" where uid='"+uid+"'").get(0);
				logUtil.logInfo("修改用户信息，用户:"+user.getUid());
			}else{					
				result.setMessage(rString);
				result.setStatusCode("300");
				logUtil.logInfo("修改用户信息，用户:"+rString);
				b=false;
			}	

		} catch (Exception e1) {
			b=false;
			result.setMessage(e1.getMessage());
			result.setStatusCode("300");
			e1.printStackTrace();
			logUtil.logInfo("修改用户信息，用户:"+e1.getMessage());
		}
		
		
		if (b) {
			String r=callback+"("+new Gson().toJson(user)+")";
			reslutJson=new ByteArrayInputStream(r.getBytes("UTF-8"));  
		}else{
			reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		}
		
		
		return SUCCESS;
		
		
	}
	
	/**
	 * 用户删除
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Action(value="deleteUser",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String DeleteUser() throws UnsupportedEncodingException{
		List<User> luser=new Gson().fromJson(json, new TypeToken<ArrayList<User>>() {}.getType());
		try {
			for (User u : luser) {
				userBIZ.deleteUser(u);			
				logUtil.logInfo("删除用户！"+u.getUid());
			}
			result.setMessage(Message.DEL_MESSAGE_SUCCESS);
			result.setStatusCode("200");
		} catch (Exception e) {
			logUtil.logInfo("删除用户！"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}

		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	
	
	@Action(value="getUserByID",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getUserByID() throws UnsupportedEncodingException{
		List<User> list=userBIZ.getUser("WHERE UID='"+uid+"'");
		reslutJson=new ByteArrayInputStream(new Gson().toJson(list).getBytes("UTF-8"));  
		return SUCCESS;
	}
	
	@Action(value="getSignUserByID",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getSignUserByID() throws UnsupportedEncodingException{
		List<User> list=userBIZ.getUser("WHERE UID='"+uid+"'");
		reslutJson=new ByteArrayInputStream(new Gson().toJson(list).getBytes("UTF-8"));  
		return SUCCESS;
	}
   
    
    /**
     * excel导出
     * @return
     */
	@Action(value="exportUserExcel",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson",
					"contentType","application/vnd.ms-excel",
					"contentDisposition","attachment;filename=%{fileName}",
					"bufferSize","1024"
			})})
    public String ExportUserExcel() {
        try {
            //第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            //第二步，在webbook中添加一个sheet，对应Excel文件中的 sheet
            HSSFSheet sheet = wb.createSheet("UserInformation");
            //第三步，在sheet中添加表头第0行，注意老版本poi对Excel的行数列数有限制
            HSSFRow row = sheet.createRow(0);
            //第四步，创建单元格样式：居中
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
            style.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            
            //第五步，创建表头单元格，并设置样式
            HSSFCell cell;

            cell = row.createCell(0);
            cell.setCellValue("ID");
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue("Name");
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue("Password");
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue("Position");
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue("Email");
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue("Role");
            cell.setCellStyle(style);
            
            cell = row.createCell(6);
            cell.setCellValue("Department");
            cell.setCellStyle(style);
            
            cell = row.createCell(7);
            cell.setCellValue("Date");
            cell.setCellStyle(style);
            
            sheet.setColumnWidth(0, 3000);
            sheet.setColumnWidth(1, 9000);
            sheet.setColumnWidth(2, 3000);
            sheet.setColumnWidth(3, 3000);
            sheet.setColumnWidth(4, 6000);
            sheet.setColumnWidth(5, 3000);
            sheet.setColumnWidth(6, 3000);
            sheet.setColumnWidth(7, 6000);
            
            //第六步，写入实体数据，实际应用中这些数据从数据库得到
            
    		String where="";
    		if (!"".equals(rid)&&rid!=null) {
    			where += " type='"+rid+"'";
    		}
    		if (!"".equals(name)&&name!=null) {
    			if (!"".equals(where)) {
    				where +=" and ";
    			}
    			where += " name like '%"+name+"%'";
    		}
    		if (!"".equals(uid)&&uid!=null) {
    			if (!"".equals(where)) {
    				where +=" and ";
    			}
    			where += " uid like '%"+uid+"%'";
    		}
    		
    		if (!"".equals(position)&&position!=null) {
    			if (!"".equals(where)) {
    				where +=" and ";
    			}
    			where += " position = '"+position+"'";
    		}
    		
    		if (!"".equals(date)&&date!=null) {
    			if (!"".equals(where)) {
    				where +=" and ";
    			}
    			where += " date = '"+date+"'";
    		}
    		
    		if (!"".equals(did)&&did!=null) {
    			if (!"".equals(where)) {
    				where +=" and ";
    			}
    			where += " did = '"+did+"'";
    		}
    	
    		
    		if (!"".equals(where)) {
    			where =" where "+where;
    		}
            List<User> lUsers=userBIZ.getUser(where);
            
            int i=0;
            for (User user : lUsers) {
            	i++;
                row = sheet.createRow(i);
                row.createCell(0).setCellValue(user.getUid());
                row.createCell(1).setCellValue(user.getName());
                row.createCell(2).setCellValue(user.getPassword());
                row.createCell(3).setCellValue(user.getPosition());
                row.createCell(4).setCellValue(user.getEmail());
                row.createCell(5).setCellValue(user.getRole().getName());
                row.createCell(6).setCellValue(user.getDepartment().getName());
                row.createCell(7).setCellValue(user.getDate());
			}
            
        	HttpServletResponse response = (HttpServletResponse)
        			ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");

            //第七步，将文件存到流中
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);
            byte[] fileContent = os.toByteArray();
            ByteArrayInputStream is = new ByteArrayInputStream(fileContent);
    		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");		 
    		fileName = "User"+sf.format(new Date()).toString()+ ".xls";
    		fileName= new String(fileName.getBytes(), "ISO8859-1");
    		//文件流
            reslutJson = is;   
            logUtil.logInfo("导出用户，条件："+where);
        }
        catch(Exception e) {
        	logUtil.logInfo("导出用户!"+e.getMessage());
            e.printStackTrace();
        }

        return "success";
    }
    
    /**
     * excel导入
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	@Action(value="importUser",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
    public String  ImportUser() throws FileNotFoundException, IOException{
        try {
        	List<User> lUsers=new ArrayList<User>();
	    	if (upfile!=null) {
	        	POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(upfile));   
	        	HSSFWorkbook wb = new HSSFWorkbook(fs); 
	        	HSSFSheet sheet = wb.getSheetAt(0); 
	        	Date d1=new Date();
	    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:dd");
	            // 循环遍历表,sheet.getLastRowNum()是获取一个表最后一条记录的记录号
	            for (int i = Integer.parseInt(first)-1 ; i <= sheet.getLastRowNum(); i++) {
	                // 创建一个行对象
	                HSSFRow row = sheet.getRow(i);
	
	    				User user=new User();
	    				HSSFCell cell=row.getCell(0);
	    				cell.setCellType(CellType.STRING);
	    				user.setUid(cell.getStringCellValue().trim());
	    				
	    				cell=row.getCell(1);
	    				cell.setCellType(CellType.STRING);
	    				user.setName(cell.getStringCellValue().trim());
	    				
	    				cell=row.getCell(2);
	    				cell.setCellType(CellType.STRING);
	    				user.setPassword(cell.getStringCellValue().trim());	 
	    				
	    				cell=row.getCell(3);
	    				cell.setCellType(CellType.STRING);
	    				user.setPosition(cell.getStringCellValue().trim());
	    				
	    				cell=row.getCell(4);
	    				cell.setCellType(CellType.STRING);
	    				user.setEmail(cell.getStringCellValue().trim());
	    				
	    				cell=row.getCell(5);
	    				cell.setCellType(CellType.STRING);
	    				String rid=roleBIZ.getRoleID(cell.getStringCellValue().trim());
	    				if (rid==null) {
							throw new Exception(row.getCell(0).getStringCellValue().trim() +" 的权限不存在！");
						}
	    				user.setRid(rid);
	    				
	    				cell=row.getCell(6);
	    				cell.setCellType(CellType.STRING);
	    				user.setDid(cell.getStringCellValue().trim());
	    				user.setDate(sdf.format(d1));
	    				lUsers.add(user);
	            }
	            userBIZ.importUser(lUsers);
	            wb.close();
	            fs.close();
	            for (User u :lUsers) {
	            	logUtil.logInfo("导入用户!"+u.getUid());
				}
	            logUtil.logInfo("导入用户!成功");
	            result.setMessage(Message.UPLOAD_MESSAGE_SUCCESS);
				result.setStatusCode("200");
			}else{
				logUtil.logInfo("导入用户!失败");
				result.setMessage(Message.UPLOAD_MESSAGE_ERROE);
				result.setStatusCode("300");
			}
		} catch (Exception e) {
			logUtil.logInfo("导入用户!"+e.getMessage());
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
		}
        reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8"));  
    	return SUCCESS;
    }
    
    
    
	/**
	 * 用户查询
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Action(value="getUser4Find",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String getUser4Find() throws UnsupportedEncodingException{
		

				
		List<User> luser=userBIZ.getUser("");
		
		reslutJson=new ByteArrayInputStream(new Gson().toJson(luser).getBytes("UTF-8"));
		return SUCCESS;
	}

}

