package test;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import dao.File;
import dao.FileMapper;
import jxl.Sheet;
import jxl.Workbook;
public class mainTest {

    /**
     * 共6步操作完成CRUD
     * 
     * @throws IOException
     */
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
            FileMapper mapper = session.getMapper(FileMapper.class);

            // 5、CRUD操作

            // 5.1--添加影片   ---批量插入
            List<File> files = readData();
            for(int i = 0 ; i <files.size();i++) {
            	mapper.insertFilm(files.get(i));
            }
//            File film = new File();
//            film.setName("我和你的倾城时光");
//            film.setPrice(56.75);
            
            session.commit();// 添加、修改、删除操作最后需要提交事务

//            // 5.2--修改影片"笑傲江湖"为"喜剧之王"
//            film = mapper.getFilmById(1);
//            film.setName("喜剧之王");
//            mapper.updateFilm(film);
//            session.commit();
//
//            // 5.3--删除影片"笑傲江湖"，其ID为14
////            mapper.deleteFilm(1);
////            session.commit();
//
//            // 5.4--获取所有电影信息
//            List<File> filmList = mapper.getAllFilm();
//
//            // 显示所有电影信息
//            for (File filmObj : filmList) {
//
//                System.out.println("电影ID：" + filmObj.getId() + " 电影名：" + filmObj.getName());
//
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、关闭SqlSession
            if (session != null) {
                session.close();
            }
        }

    }
    /*  第一版  只能读取xls文件的内容
     * 从本地Excel文件读取内容
     */
    public static List<File> readData() {
    	List<File> list=new ArrayList<File>();
        try {
            Workbook rwb=Workbook.getWorkbook(new java.io.File("/Users/dimengze/Documents/file.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            System.out.println("表的列数："+clos+" 表的行数:"+rows);
            for (int i = 3; i < 12; i++) {
                for (int j = 0; j < 3; j++) {
                    //第一个是列数，第二个是行数
                    String id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String name=rs.getCell(j++, i).getContents();
                    String price=rs.getCell(j++, i).getContents();
                    System.out.println("id:"+id+" name:"+name+" price:"+price);
                    File f = new File();
                    f.setId(Integer.parseInt(id));
                    f.setName(name);
                    f.setPrice(Double.parseDouble(price));
                    list.add(f);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
       return list;
    	
    }
   
    
    
    //主方法
	public static void main(String[] args) throws IOException {
		testBaties();
		
	}
}
