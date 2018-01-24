package com.kime.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.analysis.model.Sales;

public class ExcelUtil {
	

    private final static String excel2003L = ".xls"; // 2003- 版本的excel
    private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel
	
    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * 
     * @param inStr
     *            ,fileName
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr); // 2003-
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr); // 2007+
        } else {
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 判断行是否为空
     * @param row
     * @return
     */
    public static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
                return false;
        }
        return true;
    }
	
	/**
	 * 根据excel单元格类型获取excel单元格值
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC: {
				short format = cell.getCellStyle().getDataFormat();
				if(format == 14 || format == 31 || format == 57 || format == 58){ 	//excel中的时间格式
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	                double value = cell.getNumericCellValue();  
	                Date date = DateUtil.getJavaDate(value);  
	                cellvalue = sdf.format(date);  
				}
				// 判断当前的cell是否为Date
				else if (HSSFDateUtil.isCellDateFormatted(cell)) {  //先注释日期类型的转换，在实际测试中发现HSSFDateUtil.isCellDateFormatted(cell)只识别2014/02/02这种格式。
					// 如果是Date类型则，取得该Cell的Date值           // 对2014-02-02格式识别不出是日期格式
					Date date = cell.getDateCellValue();
					DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue= formater.format(date);
				} else { // 如果是纯数字
					// 取得当前Cell的数值
					cellvalue = NumberToTextConverter.toText(cell.getNumericCellValue()); 
					
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getStringCellValue().replaceAll("'", "''");
				break;
			case  HSSFCell.CELL_TYPE_BLANK:
				cellvalue = null;
				break;
			// 默认的Cell值
			default:{
				cellvalue = " ";
			}
			}
		} else {
			cellvalue = "";
		}
		if (cellvalue.equals(".00")) {
			cellvalue="0";
		}
		return cellvalue;
	}
	
	
	
    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     *
     * @param title
     *            表格标题名
     * @param headers
     *            表格属性列名数组
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern
     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
public static <T>ByteArrayOutputStream  exportExcel(String title, Class class1, Collection<T> dataset,  String pattern) {
	
	//获取表头民
	Field[] fs = class1.getDeclaredFields();
	String[] headers=new String[fs.length];
	for (int i = 0; i < fs.length; i++) {
			headers[i]=fs[i].getName();
	}
	
	ByteArrayOutputStream out=new ByteArrayOutputStream();
    // 声明一个工作薄
    HSSFWorkbook workbook = new HSSFWorkbook();
    // 生成一个表格
    HSSFSheet sheet = workbook.createSheet(title);
    // 设置表格默认列宽度为15个字节
    sheet.setDefaultColumnWidth(15);
    // 生成一个样式
    HSSFCellStyle style = workbook.createCellStyle();
    // 设置这些样式
    style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    // 生成一个字体
    HSSFFont font = workbook.createFont();
    font.setColor(HSSFColor.VIOLET.index);
    font.setFontHeightInPoints((short) 10);
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    // 把字体应用到当前的样式
    style.setFont(font);
    // 生成并设置另一个样式
    HSSFCellStyle style2 = workbook.createCellStyle();
    style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
    style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
    style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
    style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    // 生成另一个字体
    HSSFFont font2 = workbook.createFont();
    font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
    // 把字体应用到当前的样式
    style2.setFont(font2);

    //设置单元格个税文本类型
    HSSFDataFormat format = workbook.createDataFormat(); 
    style.setDataFormat(format.getFormat("@")); 
    style2.setDataFormat(format.getFormat("@")); 
    
    
    // 声明一个画图的顶级管理器
    HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
    // 定义注释的大小和位置,详见文档
    HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
    // 设置注释内容
//    comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
    // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
    comment.setAuthor("Analysis");
    

    // 产生表格标题行
    HSSFRow row = sheet.createRow(0);
    for (int i = 0; i < headers.length; i++) {
        HSSFCell cell = row.createCell(i);
        cell.setCellStyle(style);
        HSSFRichTextString text = new HSSFRichTextString(headers[i]);
        cell.setCellValue(text);
    }

    // 遍历集合数据，产生数据行
    Iterator<T> it = dataset.iterator();
    int index = 0;
    while (it.hasNext()) {
        index++;
        row = sheet.createRow(index);
        T t = (T) it.next();
        // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
        Field[] fields = t.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style2);
            Field field = fields[i];
            String fieldName = field.getName();
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Class tCls = t.getClass();
                Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                Object value = getMethod.invoke(t, new Object[] {});
                // 判断值的类型后进行强制类型转换
                String textValue = null;
                if (value==null) {
                    value="";
                }
                if (value instanceof Boolean) {
                    boolean bValue = (Boolean) value;
                    textValue = "男";
                    if (!bValue) {
                        textValue = "女";
                    }
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    textValue = sdf.format(date);
                } else if (value instanceof byte[]) {
                    // 有图片时，设置行高为60px;
                    row.setHeightInPoints(60);
                    // 设置图片所在列宽度为80px,注意这里单位的一个换算
                    sheet.setColumnWidth(i, (short) (35.7 * 80));
                    // sheet.autoSizeColumn(i);
                    byte[] bsValue = (byte[]) value;
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
                            index);
                    anchor.setAnchorType(2);
                    patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                } else {
                    // 其它数据类型都当作字符串简单处理
                    textValue = value.toString();
                }
                // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                if (textValue != null) {
                    HSSFRichTextString richString = new HSSFRichTextString(textValue);
                    HSSFFont font3 = workbook.createFont();
                    font3.setColor(HSSFColor.BLUE.index);
                    richString.applyFont(font3);
                    cell.setCellValue(richString);
/*                    Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
                    Matcher matcher = p.matcher(textValue);
                    if (matcher.matches()) {
                        // 是数字当作double处理
                        cell.setCellValue(Double.parseDouble(textValue));
                    } else {
                        HSSFRichTextString richString = new HSSFRichTextString(textValue);
                        HSSFFont font3 = workbook.createFont();
                        font3.setColor(HSSFColor.BLUE.index);
                        richString.applyFont(font3);
                        cell.setCellValue(richString);
                    }*/
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                // 清理资源
            }
        }

    }
    try {
        workbook.write(out);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return out;
}

/**
 * Excel转List
 * @param class1 类型
 * @param file 文件
 * @param first 起始位置
 * @return
 * @throws IOException 
 * @throws FileNotFoundException 
 */
public static  List FileToList(Class c,File file,String first,String filename,int start) throws Exception{
	List list=new ArrayList<>();
//	POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(file));   
//	HSSFWorkbook wb = new HSSFWorkbook(fs); 
	Workbook wb=getWorkbook(new FileInputStream(file), filename);
	//HSSFSheet sheet = wb.getSheetAt(0); 
	Sheet sheet=wb.getSheetAt(0);
	
	//获取表头民
	Field[] fields = c.getDeclaredFields();
	String[] headers=new String[fields.length];
	for (int i = 0; i < fields.length; i++) {
		headers[i]=fields[i].getName();
	}
	
    // 循环遍历表,sheet.getLastRowNum()是获取一个表最后一条记录的记录号
    for (int i = Integer.parseInt(first)-1 ; i <= sheet.getLastRowNum(); i++) {
        // 创建一个行对象
        //HSSFRow row = sheet.getRow(i);
        Row row=sheet.getRow(i);
        if (row==null) {
			break;
		}
    	Object o = c.newInstance();
        //导入时去除对象第一位ID
        for (int j = start-1; j < headers.length; j++) {
        	String methodName = "set"+headers[j];
            Method setMethod = c.getMethod(methodName,String.class);
            setMethod.invoke(o, new Object[]{ExcelUtil.getCellValue(row.getCell(j-1))});
		}
       
        list.add(o);
    }
    wb.close();
    //fs.close();
	
	return list;	
}
}