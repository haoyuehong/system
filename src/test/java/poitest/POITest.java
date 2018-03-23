package poitest;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 8:56 2018/3/15
 */
public class POITest {
    private String fileName = "E:\\corner\\下载 (12).xls";

    @Test
    public void testPOi()throws Exception{
        if(fileName.endsWith(".xls") || fileName.endsWith(".xlsx")){
            boolean is03Excel = fileName.endsWith(".xls");
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Workbook workbook = is03Excel?new HSSFWorkbook(fileInputStream):new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            System.out.println(lastRowNum);
            for(int rowNum = 2;rowNum < sheet.getLastRowNum(); rowNum++){
               Row row = sheet.getRow(rowNum);
               System.out.println("单位："+row.getCell(0).getStringCellValue());
               row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
               System.out.println("已导入学生人数："+row.getCell(1).getStringCellValue());
               row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
               System.out.println("考试人数："+row.getCell(2).getStringCellValue());
               row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
               System.out.println("学习人数："+row.getCell(3).getStringCellValue());
               row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
               System.out.println("及格率："+row.getCell(4).getStringCellValue());
           }
        }
    }



}
