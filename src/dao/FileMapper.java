package dao;

import java.util.List;
/**
 * 电影信息数据访问接口
 * @author dimengze
 *
 */
public interface FileMapper {
	  /** 
     * 功能：查询所有电影 
     * @return 
     */  
    public List<File> getAllFilm();
    
    /**
     * 功能：按编号获取电影
     * 
     * @return
     */
    public File getFilmById(int id);


    /**
     * 功能：添加影片
     * 
     * @param film
     */
    public void insertFilm(File film);

    /**
     * 功能：修改影片
     * 
     * @param film
     */
    public void updateFilm(File film);

    /**
     * 功能：删除影片
     * 
     * @param id
     */
    public void deleteFilm(int id);

}
