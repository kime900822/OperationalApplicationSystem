package com.kime.utils.XmlWorker;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class XmlWorkerHelperUtil {

	 public static void htmlToPDF(String htmlString,String pdfPath) {
	        try {
	            Document document = new Document(PageSize.A4);
	            PdfWriter pdfWriter = PdfWriter.getInstance(document,
	                    new FileOutputStream(pdfPath));
	            document.open();
	            document.addAuthor("pdf作者");
	            document.addCreator("pdf创建者");
	            document.addSubject("pdf主题");
	            document.addCreationDate();
	            document.addTitle("pdf标题,可在html中指定title");
	            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
	            InputStream inputStream=null;
	            worker.parseXHtml(pdfWriter, document, new ByteArrayInputStream(htmlString.getBytes("UTF-8")),inputStream,Charset.forName("UTF-8"),new AsianFontProvider());
	            document.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
}
