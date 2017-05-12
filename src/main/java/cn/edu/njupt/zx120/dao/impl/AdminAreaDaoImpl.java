package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.AdminAreaDao;
import cn.edu.njupt.zx120.model.AdminAreaModel;
import org.springframework.stereotype.Repository;

/**
 * Created by wallance on 3/19/17.
 */
@Repository("adminAreaDao")
public class AdminAreaDaoImpl extends AbstractDao<Integer, AdminAreaModel> implements AdminAreaDao {

    @Override
    public String getAreaNameByAdminCode(int adminCode) {
        return getSession()
                .createQuery("SELECT adminArea.city from AdminAreaModel adminArea where adminArea.code=:adminCode", AdminAreaModel.class)
                .setParameter("adminCode", adminCode)
                .toString();
    }

    @Override
    public int getAdminCodeByAreaName(String areaName) {
        return Integer.parseInt(
                getSession()
                        .createQuery("select adminArea.code from AdminAreaModel adminArea where adminArea.city=:areaName", AdminAreaModel.class)
                        .setParameter("areaName", areaName)
                        .toString());
    }
}
