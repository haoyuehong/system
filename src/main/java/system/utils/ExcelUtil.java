package system.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.applet.Main;
import system.common.JsonData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 13:15 2018/3/15
 */
@Slf4j
public class ExcelUtil {

    /**
     * 文件导入工具类
     * @param fileName 文件名
     * @param sheetIndex 表索引  从0开始
     * @param startRowIndex 开始行的索引号
     * @return
     */
    public  static JsonData  export(String fileName,Integer sheetIndex,Integer startRowIndex)throws Exception{
        FileInputStream fileInputStream = null;
        try {
            Map<String,List<String>> map = new HashMap<>();
            if(fileName.endsWith(".xls") || fileName.endsWith(".xlsx")){
                    boolean is03Excel = fileName.endsWith(".xls");
                    Workbook workbook  = null;
                    try {
                        fileInputStream = new FileInputStream(fileName);
                    } catch (FileNotFoundException e) {
                        log.error("【导入数据】未找到上传的文件，fileName={}",fileName);
                        return JsonData.createError("未找到文件上传的文件");
                    }

                    try {
                        workbook = is03Excel?new HSSFWorkbook(fileInputStream):new XSSFWorkbook(fileInputStream);
                    } catch (IOException e) {
                        deleteFile(fileName);
                        log.error("【导入数据】创建工作簿失败，fileName={}",fileName);
                        return JsonData.createError("创建工作簿失败");
                    }
                    //获取索引为sheetIndex的工作表
                    Sheet sheet = workbook.getSheetAt(sheetIndex);
                    //循环获取每一行
                    for(int rowNum = startRowIndex;rowNum <= sheet.getLastRowNum(); rowNum++){
                        //存放本行每个单元格值得容器
                        List<String> list = new ArrayList<>();
                        //获取第rowNum+1行
                        Row row = sheet.getRow(rowNum);
                        //获取最后一列的索引号
                        short lastCellNum = row.getLastCellNum();
                        //遍历本行的每个单元格，获取其值
                        for(int i = 0; i <= lastCellNum-1; i++){
                            //获取当前单元格的值
                            row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                            String value = row.getCell(i).getStringCellValue();
                            //将本单元格的值放入容器
                            list.add(value);
                        }
                        //将存放本行数据的list放入map中,以当前行为key
                        map.put(rowNum+"",list);
                    }
                    //deleteFile(fileName);
                    //将结果返回给调用者，用于取值并封装对象
                    return JsonData.createSuccess(map);
            }
            deleteFile(fileName);
            return JsonData.createError("表格格式有误");
        } catch (Exception e) {
            return JsonData.createError("未知异常");
        } finally {
            if(fileInputStream != null){
                fileInputStream.close();
            }
        }
    }


    private static boolean deleteFile(String fileName){
        File file = new File(fileName);
        if(!file.exists()){
            return false;
        }else{
            return file.delete();
        }
    }

    /*public static void main(String[] args) throws Exception{
        String fileName = "E:\\123.xlsx";
        JsonData export = export(fileName, 0, 1);
        Map map = export.toMap();
        System.out.println(map.get("status"));
        //System.out.println(map.get("msg"));
        Map<String,List<String>> m = (Map<String, List<String>>) map.get("data");
        Iterator<Map.Entry<String, List<String>>> iterator = m.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, List<String>> entry = iterator.next();
            System.out.println(entry.getValue());
        }
    }
    */

}



