package com.kime.utils;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils.Null;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.kime.infoenum.Message;
import com.kime.model.HeadColumn;
import com.sign.model.Payment;
import com.sign.model.paymentVisit.PaymentVisit;
import com.sign.model.paymentVisit.PaymentVisitEmployee;
import com.sign.other.PaymentHelp;
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
		BaseFont bf = BaseFont.createFont(Message.FONT_ARAILBD_APTH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		
		
		
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
		BaseFont bfChinese = BaseFont.createFont(Message.FONT_ARAILBD_APTH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		Font titleFont= new Font(bfChinese, 10, Font.BOLD);
        Font cellFont= new Font(bfChinese, 6, Font.NORMAL);
        Font formFont= new Font(bfChinese, 12, Font.NORMAL);
		//表头
    	PdfPCell pdfCell = new PdfPCell();
		String[] headers=new String[10];
		headers[0]="Visit Employee No.*\n 出差人员*";
		headers[1]="Visit Employee BU No.\n 出差人员部门代码";
		headers[2]="Visit Employee Name\n 出差人员姓名";
		headers[3]="A.是否HR预定酒店 \n Hotel Booking by HR";
		headers[4]="酒店名称  \n Hotel Name";
		headers[5]="B.是否HR派车\n Car Arrange by HR";
		headers[6]="派车时间\n Car Arrange Period";
		headers[7]="C.是否HR定机票\nAir Ticket Booking by HR";
		headers[8]="具体航班号\nFlight No.";
		headers[9]="D.是否HR办理签证\nVisar Arrange by HR";
		
		float[] widths1={150,370};
		float[] widths={40,50,50,50,70,40,50,50,30,50};
		String[] columns={"employeeNo","employeeBUNo","employeeName","hotelBookingByHR","hotelName","carArrangeByHR","carArrangePeriod","airTickerBookingByHR","flightNO","visarArrangeByHR"};
		
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
    	pdfCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
    	paragraph = new Paragraph("Reference No. \n 单号", formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(paymentVisit.getReferenceNo(), formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Applicant Date \n 申请日期", formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(paymentVisit.getApplicantDate(), formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Visit Purpose * \n 出差目的*", formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(PaymentVisitHelp.getVisitPurpose(paymentVisit.getVisitPurpose()), formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Project No * \n 项目号*", formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(paymentVisit.getProjectNo(), formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Currency \n 币种", formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(paymentVisit.getCurrency(), formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Advance Amount \n 预付款金额", formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(CommonUtil.formatAmount(String.valueOf(paymentVisit.getAdvanceAmount())) , formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Visit Date * \n 出差期间*", formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Form:"+paymentVisit.getVisitDateFrom()+" TO:"+paymentVisit.getVisitDateTo(), formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Total Leave Work Hours * \n 总共出差工作天数时数*", formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(String.valueOf(paymentVisit.getTotalLeaveWorkHours()), formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Domestic/Oversea * \n 国内国外*", formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(paymentVisit.getBusinessTrip(), formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Visit Detail Place* \n 出差具体目的地*", formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(paymentVisit.getVisitDetailPlace(), formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph("Visit Detail Purpose* \n 出差具体事由*", formFont);
    	pdfCell.setPhrase(paragraph);
		table.addCell(pdfCell);
		paragraph = new Paragraph(paymentVisit.getVisitDetailPurpose(), formFont);
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
	
	
	/**
	 * 付款申请打印
	 * @param map
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayOutputStream  printPaymentPDF(Payment payment,String url) throws Exception {
		ByteArrayOutputStream ba=new ByteArrayOutputStream();

		PdfReader reader = new PdfReader(url);
		PdfStamper stamper = new PdfStamper(reader, ba);
		//BaseFont bf = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		BaseFont bf = BaseFont.createFont(Message.FONT_ARAILBD_APTH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		
		Font cellFont= new Font(bf, 6, Font.NORMAL);
		AcroFields form = stamper.getAcroFields();
        form.addSubstitutionFont(bf);
        form.setFieldProperty("companyOfSender", "textfont", cellFont, null);

        form.setField("code",payment.getCode());
        if (payment.getUrgent()!=null&&payment.getUrgent().equals("1")) {
        	form.setField("urgent","Urgent : ●");
		}
        form.setField("applicationDate",payment.getApplicationDate());
        form.setField("requestPaymentDate",payment.getRequestPaymentDate());
        form.setField("contacturalPaymentDate",payment.getContacturalPaymentDate());
        if(payment.getPayType().equals("Cash")) {
        	form.setField("cash","√");
        }else if (payment.getPayType().equals("Banking")) {
        	form.setField("banking","√");
		}else if (payment.getPayType().equals("AdvanceWriteoff")) {
			form.setField("advance","√");
			form.setField("advanceAmount",payment.getAdvanceWriteoffWay()+" ,Amount: "+ payment.getAdvanceWriteOffCurrency()+" "+CommonUtil.formatAmount(payment.getAdvanceWriteOffAmount()));
		}
        
        form.setField("uId",payment.getUID());
        form.setField("uName",payment.getUName());
        form.setField("departmentName",payment.getDepartmentName());
        form.setField("departmentID",payment.getDepartmentID());
        form.setField("beneficiary",payment.getBeneficiary());
        form.setField("beneficiaryE",payment.getBeneficiaryE());
        form.setField("beneficiaryAccountNO",payment.getBeneficiaryAccountNO());
        form.setField("beneficiaryAccountBank",payment.getBeneficiaryAccountBank());
        if (payment.getBeneficiaryChange()!=null&&payment.getBeneficiaryChange().equals("1")) {
        	form.setField("beneficiaryChange","Y");
		}
        if (payment.getBeneficiaryAccountNOChange()!=null&&payment.getBeneficiaryAccountNOChange().equals("1")) {
        	form.setField("beneficiaryAccountNOChange","Y");
		}
        form.setField("supplierCode",payment.getSupplierCode());
        form.setField("refNoofBank",payment.getRefNoofBank());
        form.setField("paymentSubject_"+payment.getPaymentSubject(),"Y");
        form.setField("paymentDays_"+payment.getPaymentTerm(),payment.getPaymentDays_1());
        if (payment.getAmount_1()!=null&&!payment.getAmount_1().equals("")&&!payment.getAmount_1().equals("0.00")) {
        	form.setField("receivingOrApprovalDate_1",payment.getReceivingOrApprovalDate_1());
        	form.setField("amount_1",CommonUtil.formatAmount(payment.getAmount_1()));
        	form.setField("PONo_1",payment.getPONo_1());
        	form.setField("currency_1",payment.getCurrency_1());
		}
        
        if (payment.getAmount_2()!=null&&!payment.getAmount_2().equals("")&&!payment.getAmount_2().equals("0.00")) {
        	form.setField("receivingOrApprovalDate_2",payment.getReceivingOrApprovalDate_2());
        	form.setField("amount_2",CommonUtil.formatAmount(payment.getAmount_2()));
        	form.setField("PONo_2",payment.getPONo_2());
        	form.setField("currency_2",payment.getCurrency_2());
		}
        
        if (payment.getAmount_3()!=null&&!payment.getAmount_3().equals("")&&!payment.getAmount_3().equals("0.00")) {
        	form.setField("receivingOrApprovalDate_3",payment.getReceivingOrApprovalDate_3());
        	form.setField("amount_3",CommonUtil.formatAmount(payment.getAmount_3()));
        	form.setField("PONo_3",payment.getPONo_3());
        	form.setField("currency_3",payment.getCurrency_3());
		}
        
        if (payment.getAmount_4()!=null&&!payment.getAmount_4().equals("")&&!payment.getAmount_4().equals("0.00")) {
        	form.setField("receivingOrApprovalDate_4",payment.getReceivingOrApprovalDate_4());
        	form.setField("amount_4",payment.getAmount_4());
        	form.setField("PONo_4",payment.getPONo_4());
        	form.setField("currency_4",payment.getCurrency_4());
		}
        
        if (payment.getAmount_5()!=null&&!payment.getAmount_5().equals("")&&!payment.getAmount_5().equals("0.00")) {
        	form.setField("receivingOrApprovalDate_5",payment.getReceivingOrApprovalDate_5());
        	form.setField("amount_5",CommonUtil.formatAmount(payment.getAmount_5()));
        	form.setField("PONo_5",payment.getPONo_5());
        	form.setField("currency_5",payment.getCurrency_5());
		}
        
        if (payment.getAmount_6()!=null&&!payment.getAmount_6().equals("")&&!payment.getAmount_6().equals("0.00")) {
        	form.setField("receivingOrApprovalDate_6",payment.getReceivingOrApprovalDate_6());
        	form.setField("amount_6",CommonUtil.formatAmount(payment.getAmount_6()));
        	form.setField("PONo_6",payment.getPONo_6());
        	form.setField("currency_6",payment.getCurrency_6());
		}
        
        form.setField("usageDescription",payment.getUsageDescription());
        form.setField("handingFee",payment.getHandingFee());
        form.setField("amountInFigures","RMB "+CommonUtil.formatAmount(payment.getAmountInFigures().replaceAll(",", "")));
        form.setField("amountInBig",CommonUtil.digitUppercase(Double.parseDouble(payment.getAmountInFigures())));
        form.setField("documentAudit",payment.getDocumentAudit());
        form.setField("deptManager",payment.getDeptManager()+"/"+payment.getDeptManagerDate());
       
        if(payment.getState().equals(PaymentHelp.INVALIDPAYMENT)) {
        	form.setField("invalid","INVALID");
        }
        
        stamper.setFormFlattening(true);
        stamper.close();
            
        return ba;	
		
	}
	

}
