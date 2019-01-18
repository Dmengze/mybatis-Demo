package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import dao.City;
import dao.CityMapper;

public class testExcel {
    public static void testBaties() throws IOException {

        // 指定MyBatis配置文件
        String RESOURCE = "mybatis-config.xml";

        // 1、指定MyBaties配置文件
        InputStream inputstream = Resources.getResourceAsStream(RESOURCE);
        // 2、创建SqlSessionFactory()
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputstream);

        SqlSession session = null;
        try {

            // 3、获取SqlSession
            session = sessionFactory.openSession();

            // 4、获取DAO接口对象
            CityMapper mapper = session.getMapper(CityMapper.class);

            // 5、CRUD操作
            List <City> list = readExcel("/Users/dimengze/Documents/file1.xlsx");
            for(int i = 0 ;i<list.size();i++) {
            	mapper.updatebyId(list.get(i));
            }
//            // 5.1--添加影片   ---批量插入
//            List<File> files = readData();
//            for(int i = 0 ; i <files.size();i++) {
//            	mapper.insertFilm(files.get(i));
//            }
//            File film = new File();
//            film.setName("我和你的倾城时光");
//            film.setPrice(56.75);
            
            session.commit();// 添加、修改、删除操作最后需要提交事务



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭SqlSession
            if (session != null) {
                session.close();
            }
        }

    }
    public static List<City> readExcel(String path) {
        List<City> list = null;
        try {
            File excelFile = new File(path); //创建文件对象
            FileInputStream is = new FileInputStream(excelFile); //文件流
            Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的
            int sheetCount = workbook.getNumberOfSheets(); //Sheet的数量
            list = new ArrayList<City>(); //存储数据容器
            for (int s = 0; s < sheetCount; s++) {
                Sheet sheet = workbook.getSheetAt(s);
                int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数   
                System.out.println("行"+rowCount);
                for (int r = 2; r < 12; r++) {
                    Row row = sheet.getRow(r);
                    row.getCell(0).setCellType(1);
                    row.getCell(1).setCellType(1);
                    row.getCell(2).setCellType(1);
                    String id = row.getCell(0).getStringCellValue();
                    System.out.println(id);
                    String areCode = row.getCell(1).getStringCellValue();
                    System.out.println(areCode);
                    String schoolCode = row.getCell(2).getStringCellValue();
                    System.out.println(schoolCode);
                    City city = new City();
                    city.setId(Integer.valueOf(id));
                    city.setAreaCode(areCode);
                    city.setSchoolCode(schoolCode);
                    list.add(city);
                }
            }
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static int a(int a) {

		if(a==1) {
			return a ;
		}
		return 0;
    	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		try {
//			testBaties();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(a(1));
		
		
	}

}
