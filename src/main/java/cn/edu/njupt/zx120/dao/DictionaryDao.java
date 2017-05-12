package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.DictionaryModel;

import java.util.List;

/**
 * Created by wallance on 4/23/17.
 */
public interface DictionaryDao {

    List<DictionaryModel> queryByParam(String param);

}
