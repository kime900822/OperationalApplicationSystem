package com.kime.utils;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kime.model.HeadColumn;

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
	

}
