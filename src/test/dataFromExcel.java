package test;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class dataFromExcel {
	public static List<String[]> readExcel(String path) { 
	     SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
	     List<String[]> list = null;
	     try { 
             //同时支持Excel 2003、2007
	    	 File excelFile = new File(path); //创建文件对象 
	    	 FileInputStream is = new FileInputStream(excelFile); //文件流 
	    	 Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的 
	    	 int sheetCount = workbook.getNumberOfSheets(); //Sheet的数量
	    	 //存储数据容器 
	    	 list = new ArrayList<String[]>();
	    	 //遍历每个Sheet 
	    	 for (int s = 0; s < sheetCount; s++) { 
	    		 Sheet sheet = workbook.getSheetAt(s); 
	    		 int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数   
	    		 System.out.println("行"+rowCount);
	    		 //遍历每一行 
	    		 for (int r = 0; r < rowCount; r++) { 
	    			 Row row = sheet.getRow(r); 
	    			 int cellCount = row.getPhysicalNumberOfCells(); //获取总列数 
//	    			 System.out.println("列"+cellCount);
	    			 //用来存储每行数据的容器 
	    			 String[] model = new String[cellCount];
	    			 //遍历每一列 
	    			 for (int c = 0; c < cellCount; c++) { 
	    				 Cell cell = row.getCell(c); 
	    				 int cellType = cell.getCellType();
//	    				 if(c == 0) continue;//第一列ID为标志列，不解析
	    				 String cellValue = null; 
	    				 switch(cellType) { 
	    				 case Cell.CELL_TYPE_STRING: //文本 
	    					 cellValue = cell.getStringCellValue(); 
	    					 break; 
	    				 
	    			     case Cell.CELL_TYPE_NUMERIC: //数字、日期 
	    			    	if(DateUtil.isCellDateFormatted(cell)) { 
	    			    		cellValue = fmt.format(cell.getDateCellValue()); //日期型 
	    			    		} 
	    			    	else{ 
	    			    		cell.setCellType(Cell.CELL_TYPE_STRING);
	    			    		String temp = cell.getStringCellValue();
//	    			    		cellValue = String.valueOf(cell.getNumericCellValue()); //数字 
//	    			    		if(temp.indexOf(".") > -1){
//	    			    			cellValue = String.valueOf(new Double(temp)).trim();
//	    			    		}else{
//	    			    			cellValue =temp.trim();
//	    			    		};
	    			    		cellValue =temp.trim();
	    			    	}
	    			    	break; 
	    			    case Cell.CELL_TYPE_BOOLEAN: //布尔型 
	    			    	cellValue = String.valueOf(cell.getBooleanCellValue()); 
	    			    	break; 
	    			    case Cell.CELL_TYPE_BLANK: //空白 
	    			    	cellValue = cell.getStringCellValue(); 
	    			    	break; 
	    			    case Cell.CELL_TYPE_ERROR: //错误 
	    			    	cellValue = "错误"; 
	    			    	break; 
	    			    case Cell.CELL_TYPE_FORMULA: //公式 
	    			    	cellValue = "错误"; 
	    			    	break; 
	    			    default:  
	    			    	cellValue = "错误"; 
	    			   } 
	    			  System.out.print(cellValue + "  "); 
	    			  model[c] = cellValue;
	    		  } 
	    	     //model放入list容器中 
	    		 list.add(model);
	    		 System.out.println(); 
	    	  } 
	        } 
	     is.close();
	     } 
	     catch (Exception e) { 
	    	 e.printStackTrace();
	    	 }
	     return list;
	  }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        List<String[]> aa = readExcel("/Users/dimengze/Documents/file.xlsx");
//        int len = aa.size();
//        for(int i = 0 ; i<len ; i++) {
//    		System.out.println(aa.get(i));
//        }

		

	}

}
