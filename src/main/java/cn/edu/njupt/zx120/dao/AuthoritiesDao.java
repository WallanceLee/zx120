package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.AuthorityModel;

import java.util.List;

/**
 * Created by wallance on 1/11/17.
 */
public interface AuthoritiesDao {

    List<AuthorityModel> queryAll();

    AuthorityModel queryById(int id);

    AuthorityModel queryByType(String type);

    void addNewAuthority(AuthorityModel authorityModel);

    void deleteAuthorityByType(String type);

}
