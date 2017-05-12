package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.MemberModel;

import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 1/22/17.
 */
public interface MemberDao {

    List<MemberModel> queryAll();

    MemberModel queryById(String id);

    MemberModel queryByName(String name);

    List<MemberModel> queryByType(int typeId);

    // 通过所属单位Id查询所有属于该单位的成员
    List<MemberModel> queryByOrgId(String orgId);

    // 通过所属科室名称查询所有属于该科室的成员
    List<MemberModel> queryByDepartment(String department);

    void updateOrgId(String id, String orgId);

    void updateDepartment(String id, String department);

    void updateLastModifiedTime(String id, Date lastModifiedTime);

    void deleteMember(MemberModel memberModel);

    void createNewMember(MemberModel newMember);

    void cancelMember(String id);

}
