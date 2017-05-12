package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.TagModel;

/**
 * Created by wallance on 3/7/17.
 */
public interface TagDao {

    void createNewTag(TagModel tagModel);

    void deleteTag(TagModel tagModel);

    void updateTag(TagModel tagModel);

    TagModel queryByTagId(String tagId);

}
