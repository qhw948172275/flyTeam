package com.cdyykj.commons.excel;


import com.cdyykj.system.commons.CalendarUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * excel基础解析帮助类
* @author chenbiao
* @date 2018年9月6日 下午2:48:07 
*
 */
public class BaseUtils {

	static int TITLE_ROWS = 3;
	
	public static final String YES = "是";
	public static final String NO = "否";
	/**
	 * 导出错误的课程信息
	 * 
	 * @author chenbiao
	 * @date 2018年8月27日 下午7:09:37 参数说明
	 *
	 * @param response
	 */
	public static void outputExcel(String masterTitle,String[] regionTitle,String[] titleColumnError,List<List<String>> datas, HttpServletResponse response) throws Exception {
		outputExcel(masterTitle, regionTitle, titleColumnError, datas, "",true);
	}
	
	public static String outputExcel(String masterTitle,String[] regionTitle,String[] titleColumnError,List<List<String>> datas, String prefix,boolean hasErrorMsg) throws Exception {
		// 工作簿
		SXSSFWorkbook wb = new SXSSFWorkbook(100);
		SXSSFSheet sheet = wb.createSheet(masterTitle);
		CellStyle cellStyle = getCellStyle(wb);
		if(null != regionTitle) {
			for(int i=0 ;i< regionTitle.length ;i++) {
				CellRangeAddress cellRangeAddress = new CellRangeAddress(i, i, 0, titleColumnError.length);//合并单元格
				sheet.addMergedRegion(cellRangeAddress);
				sheet.createRow(i).createCell(0, CellType.STRING).setCellValue(regionTitle[i]);
				sheet.getRow(i).getCell(0).setCellStyle(cellStyle);
			}
		}
		//每列标题信息
		SXSSFRow rowTitle = sheet.createRow((null != regionTitle) ? regionTitle.length : 0);
		for(int i = 0 ; i < titleColumnError.length ; i++) {
			rowTitle.createCell(i, CellType.STRING).setCellValue(titleColumnError[i]);
			rowTitle.getCell(i).setCellStyle(cellStyle);
		}
		int titleCount = (null != regionTitle) ? (regionTitle.length + 1) : 1; //计算标题行数（需要合并的行数+一行的标题字段）
		if(null != datas && datas.size() > 0) {	
			List<String> entity;
			SXSSFRow row;
			for(int i = 0 , j=datas.size() ; i < j ; i++) {
				entity = datas.get(i);
				row = sheet.createRow(i+titleCount);
				for(int m = 0 ;m < entity.size() ; m++) {
					row.createCell(m, CellType.STRING).setCellValue(entity.get(m));
				}
				//统一设置单元格样式，错误信息默认左对齐
				for(int m = 0 ; m < titleColumnError.length ; m++) {
					if(hasErrorMsg && m == (titleColumnError.length - 1)) { //最后一行设置字体颜色
						row.getCell(m).setCellStyle(getCellStyleByErrorMsg(wb));;
					}else {
						row.getCell(m).setCellStyle(cellStyle);
					}
				}
			}
		}
//		OutputStream out = null;
		try {
			String fileName = masterTitle+"[" + CalendarUtils.dateToString(CalendarUtils.getDate(), CalendarUtils.yyyyMMddHHmmss) + "].xlsx";
//            String headStr = "attachment; filename=\"" + new String(fileName.getBytes(SystemConstants.DEFAULT_CHARSET),"UTF-8" ) + "\"";
//            response.setContentType("application/octet-stream;;charset=UTF-8");
//            response.setCharacterEncoding(SystemConstants.DEFAULT_CHARSET);
//            response.setHeader("Content-Disposition", headStr);

//            out = response.getOutputStream();//
			String finalFileName=prefix+fileName;
			FileOutputStream outFile = new FileOutputStream(finalFileName);
            wb.write(outFile);
            return fileName;
		} catch (IOException e) {
			e.printStackTrace();

		}finally {
			wb.close(); //关闭流
		}
		return null;
	}

	/**
	 * 设置单元格样式
	* @author chenbiao
	* @date 2018年8月27日 下午8:03:47
	* 参数说明 
	* 
	* @param wb
	* @return
	 */
	protected static CellStyle getCellStyle(SXSSFWorkbook wb) {
		//设置单元格样式
		CellStyle cellStyle = wb.createCellStyle();
		//垂直居中
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		//设置一个边框
//		cellStyle.setBorderTop(BorderStyle.THICK);
		return cellStyle;
	}
	/**
	 * 错误信息的单元格样式
	* @author chenbiao
	* @date 2018年9月5日 上午10:28:07
	* 参数说明 
	* 
	* @param wb
	* @return
	 */
	protected static CellStyle getCellStyleByErrorMsg(SXSSFWorkbook wb) {
		CellStyle cellStyle = getCellStyle(wb);
		XSSFFont font = (XSSFFont) wb.createFont();
		font.setColor(XSSFFont.COLOR_RED);
		cellStyle.setFont(font);
		return cellStyle;
	}

}
