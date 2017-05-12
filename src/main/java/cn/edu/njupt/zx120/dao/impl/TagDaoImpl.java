package cn.edu.njupt.zx120.dao.impl;

import cn.edu.njupt.zx120.dao.AbstractDao;
import cn.edu.njupt.zx120.dao.TagDao;
import cn.edu.njupt.zx120.model.TagModel;
import org.springframework.stereotype.Repository;

/**
 * Created by wallance on 3/7/17.
 */
@Repository("tagDao")
public class TagDaoImpl extends AbstractDao<Integer, TagModel> implements TagDao {

    @Override
    public void createNewTag(TagModel tagModel) {
        super.save(tagModel);
    }

    @Override
    public void deleteTag(TagModel tagModel) {
        super.delete(tagModel);
    }

    @Override
    public void updateTag(TagModel tagModel) {

    }

    @Override
    public TagModel queryByTagId(String tagId) {
        return null;
    }

}
