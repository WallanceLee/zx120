package cn.edu.njupt.zx120.dao;

/**
 * Created by wallance on 3/19/17.
 */
public interface AdminAreaDao {

    String getAreaNameByAdminCode(int adminCode);

    int getAdminCodeByAreaName(String areaName);

}
