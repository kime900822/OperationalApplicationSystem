package com.kime.utils;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.kime.model.HeadColumn;
import com.sign.model.paymentVisit.PaymentVisit;
import com.sign.model.paymentVisit.PaymentVisitEmployee;
import com.sign.other.PaymentVisitHelp;

import freemarker.template.utility.StringUtil;

public class PDFUtil {

	
	
	public static <T>ByteArrayOutputStream  exportPDF(String title, Class class1, Collection<T> dataset,  String pattern,List<HeadColumn> lColumns) throws Exception {
		

		ByteArrayOutputStream out=new ByteArrayOutputStream();
		
	    String[] headers=new String[lColumns.size()];
	    String[] fields=new String[lColumns.size()];
	    String[] aligns=new String[lColumns.size()];
	    float[] widths=new float[lColumns.size()];
	    float total=0;
		for (int i = 0; i < lColumns.size(); i++) {
			headers[i]=lColumns.get(i).getLabel();
			fields[i]=lColumns.get(i).getName();
			aligns[i]=lColumns.get(i).getAlign();
			widths[i]=Float.parseFloat(lColumns.get(i).getWidth());
			total+=widths[i];
		}
		for (int i = 0; i < lColumns.size(); i++) {
			widths[i]=Float.parseFloat(lColumns.get(i).getWidth())/total*520;
		}
		
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, out);
		writer.setViewerPreferences(PdfWriter.PageModeUseThumbs);
		document.setPageSize(PageSize.A4);
		document.open();
		PdfPTable table = new PdfPTable(widths);
		table.setLockedWidth(true);
		table.setTotalWidth(520);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
                BaseFont.NOT_EMBEDDED);
        Font titleFont= new Font(bfChinese, 12, Font.BOLD);
        Font headFont= new Font(bfChinese, 6, Font.BOLD);
        Font cellFont= new Font(bfChinese, 6, Font.NORMAL);
		//表头
    	PdfPCell pdfCell = new PdfPCell();
    	pdfCell.setMinimumHeight(20);
    	pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	pdfCell.setVerticalAlignment(Element.ALIGN_CENTER);
    	pdfCell.setRowspan(1);
    	pdfCell.setColspan(lColumns.size());

		Paragraph paragraph = new Paragraph(title, titleFont);
		pdfCell.setPhrase(paragraph);
	    table.addCell(pdfCell);
    	
	    for (int i = 0; i < headers.length; i++) {    	
        	pdfCell = new PdfPCell();
        	pdfCell.setMinimumHeight(16);
        	pdfCell.setBackgroundColor(BaseColor.GRAY); 
        	pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        	pdfCell.setVerticalAlignment(Element.ALIGN_CENTER);
			paragraph = new Paragraph(headers[i], headFont);
        	pdfCell.setPhrase(paragraph);
			table.addCell(pdfCell);
	    }
		
		
		Iterator<T> it = dataset.iterator();
	    int index = 0;
	    while (it.hasNext()) {
	        index++;
	        T t = (T) it.next();
		
	        for (int i = 0; i < headers.length; i++) {
	        	pdfCell = new PdfPCell();
	        	pdfCell.setMinimumHeight(12);
	        	pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	if (aligns[i].equals("center")) {
	        		 pdfCell.setVerticalAlignment(Element.ALIGN_CENTER);
				}else if (aligns[i].equals("left")) {
	        		 pdfCell.setVerticalAlignment(Element.ALIGN_LEFT);
				}else{
					pdfCell.setVerticalAlignment(Element.ALIGN_RIGHT);
				}
	        	
                String fieldName = fields[i];
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                
                
                Class tCls = t.getClass();
                Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                Object value = getMethod.invoke(t, new Object[] {});

                
                paragraph = new Paragraph(StringUtil.tryToString(value), cellFont);
                pdfCell.setPhrase(paragraph);
                table.addCell(pdfCell);
	        }
		
	    }
	    document.add(table);
	    document.close();
		
		return out;	
	}
	
	
	public static Font getPdfChineseFont() throws Exception {
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
                BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
        return fontChinese;
    }
	
	
	public static ByteArrayOutputStream  printPDF(Map<String, String> map,String url) throws Exception {
		ByteArrayOutputStream ba=new ByteArrayOutputStream();

		PdfReader reader = new PdfReader(url);
		PdfStamper stamper = new PdfStamper(reader, ba);
		//BaseFont bf = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		BaseFont bf = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		
		AcroFields form = stamper.getAcroFields();
        form.addSubstitutionFont(bf);
        form.setFieldProperty("companyOfSender", "textfont", bf, null);
		
        for (Map.Entry<String, String> entry : map.entrySet()) {
        	   String key = entry.getKey().toString();
        	   String value = entry.getValue().toString();
        	   form.setField(key,value);
        }
        stamper.setFormFlattening(true);
        stamper.close();
            
        return ba;	
		
	}
	
	
	/**
	 * 出差申请单打印
	 * @param paymentVisit
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayOutputStream  printPaymentVisitTravelPDF(PaymentVisit paymentVisit) throws Exception {
		ByteArrayOutputStream ba=new ByteArrayOutputStream();
		
		
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, ba);
		writer.setViewerPreferences(PdfWriter.PageModeUseThumbs);
		document.setPageSize(PageSize.A4);
		document.open();
		
//		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
//                BaseFont.NOT_EMBEDDED);
		BaseFont bfChinese=BaseFont.createFont("c:\\Windows\\fonts\\SIMSUN.TTC,0",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		Font titleFont= new Font(bfChinese, 10, Font.BOLD);
        Font cellFont= new Font(bfChinese, 6, Font.NORMAL);
		//表头
    	PdfPCell pdfCell = new PdfPCell();
		String[] headers=new String[11];
		headers[0]="Visit Employee No.*\n 出差人员*";
		headers[1]="Visit Employee BU No.\n 出差人员部门代码";
		headers[2]="Visit Employee Name\n 出差人员姓名";
		headers[3]="预付款金额\n Advance Amount*";
		headers[4]="A.是否HR预定酒店 \n Hotel Booking by HR";
		headers[5]="酒店名称  \n Hotel Name";
		headers[6]="B.是否HR派车\n Car Arrange by HR";
		headers[7]="派车时间\n Car Arrange Period";
		headers[8]="C.是否HR定机票\nAir Ticket Booking by HR";
		headers[9]="具体航班号\nFlight No.";
		headers[10]="D.是否HR办理签证\nVisar Arrange by HR";
		
		float[] widths1={120,400};
		float[] widths={40,50,50,40,50,70,40,50,50,30,50};
		String[] columns={"employeeNo","employeeBUNo","employeeName","advanceAmount","hotelBookingByHR","hotelName","carArrangeByHR","carArrangePeriod","airTickerBookingByHR","flightNO","visarArrangeByHR"};
		
		Paragraph paragraph=new Paragraph("出差申请单",titleFont);
		paragraph.setAlignment(1);
		document.add(paragraph);
		paragraph=new Paragraph(" ",cellFont);
		document.add(paragraph);
		paragraph=new Paragraph("Business Travel Application",titleFont);
		paragraph.setAlignment(1);
		document.add(paragraph);
		paragraph=new Paragraph(" ",cellFont);
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(widths1);
		table.setLockedWidth(true);
		table.setTotalWidth(520);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		
		pdfCell=new PdfPCell();
		pdfCell.setMinimumHeight(16);
    	pdfCell.setVerticalAlignment(Element.ALIGN_CENTER);
    	paragraph = new Paragraph("Reference No. \n 单号", cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(paymentVisit.getReferenceNo(), cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Applicant Date \n 申请日期", cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(paymentVisit.getApplicantDate(), cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Visit Purpose * \n 出差目的*", cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(PaymentVisitHelp.getVisitPurpose(paymentVisit.getVisitPurpose()), cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Project No * \n 项目号*", cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(paymentVisit.getProjectNo(), cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Visit Date * \n 出差期间*", cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Form:"+paymentVisit.getVisitDateFrom()+" TO:"+paymentVisit.getVisitDateTo(), cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Total Leave Work Hours * \n 总共出差工作天数时数*", cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(String.valueOf(paymentVisit.getTotalLeaveWorkHours()), cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Domestic/Oversea * \n 国内国外*", cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(paymentVisit.getBusinessTrip(), cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Visit Detail Place* \n 出差具体目的地*", cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(paymentVisit.getVisitDetailPlace(), cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Visit Detail Purpose* \n 出差具体事由*", cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(paymentVisit.getVisitDetailPurpose(), cellFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		
		document.add(table);
	
		paragraph=new Paragraph(" ",cellFont);
		document.add(paragraph);
		
		table = new PdfPTable(widths);
		table.setLockedWidth(true);
		table.setTotalWidth(520);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		paragraph=new Paragraph();
		
    	for (int i = 0; i < headers.length; i++) {    	
        	pdfCell = new PdfPCell();
        	pdfCell.setMinimumHeight(16);
        	pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        	pdfCell.setVerticalAlignment(Element.ALIGN_CENTER);
        	paragraph = new Paragraph(headers[i], cellFont);
        	pdfCell.setPhrase(paragraph);
			table.addCell(pdfCell);
	    }
    	
    	
    	for (PaymentVisitEmployee paymentVisitEmployee : paymentVisit.getEmployees()) {
	        for (int i = 0; i < headers.length; i++) {
	        	pdfCell = new PdfPCell();
	        	pdfCell.setMinimumHeight(12);
	        	pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	pdfCell.setVerticalAlignment(Element.ALIGN_LEFT);
                String fieldName = columns[i];
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Class tCls = paymentVisitEmployee.getClass();
                Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                Object value = getMethod.invoke(paymentVisitEmployee, new Object[] {});
                paragraph = new Paragraph(StringUtil.tryToString(value), cellFont);
                pdfCell.setPhrase(paragraph);
                table.addCell(pdfCell);
	        }
		}
		
    	document.add(table);
    	document.close();

        return ba;	
		
	}
	

}
