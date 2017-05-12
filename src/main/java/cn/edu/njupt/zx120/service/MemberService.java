package cn.edu.njupt.zx120.service;

import cn.edu.njupt.zx120.dto.admin.MemberBindingInfoDto;
import cn.edu.njupt.zx120.dto.serviceManage.MemberDutyDto;
import cn.edu.njupt.zx120.dto.serviceManage.MemberDynamicDto;
import cn.edu.njupt.zx120.model.MemberModel;
import cn.edu.njupt.zx120.model.UserModel;

import java.util.List;

/**
 * Created by wallance on 2/24/17.
 */
public interface MemberService {

    List<MemberModel> queryAll();

    // 根据员工工号(id)查询该员工信息
    MemberModel queryById(String id);

    // 根据员工用户名查询该员工信息
    MemberModel queryByName(String name);

    // 查询所有相同职务的员工
    // 调用该接口必须由MemberTypeEnum获取typeId
    List<MemberModel> queryByType(int typeId);

    // 查询属于同一单位的员工
    List<MemberModel> queryByOrdId(String orgId);

    // 查询属于统一单位的外出员工
    List<MemberModel> queryAvailableByOrgId(String orgId);

    // 查询属于统一单位的在单位员工
    List<MemberModel> queryOutByOrgId(String orgId);

    // 查询所有属于同一科室的员工
    List<MemberModel> queryByDepartment(String department);

    // 根据员工姓名查询员工电话号码
    String queryPhoneNumberByName(String name);

    // 根据员工id查询员工电话号码
    String queryPhoneNumberById(String id);

    // 注销员工工号
    void cancelMember(String id);

    // 为员工创建账户
    UserModel createAccountBasedOnMemberModel(String id);

    void createNewMember(MemberModel memberModel);

    void deleteMember(MemberModel memberModel);

    void onRest(String id);

    void hasArrivedOffice(String id);

    void onCar(String id, String carId);

    List<MemberDynamicDto> queryAllDynamicInfo();

    List<MemberDutyDto> queryAllDutyInfo();

    List<MemberBindingInfoDto> queryAllBindingInfo();

    List<MemberBindingInfoDto> queryAllUnboundInfo();

    List<MemberBindingInfoDto> queryAllTodayBindingInfo();

}
