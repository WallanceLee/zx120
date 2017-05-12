package cn.edu.njupt.zx120.service.impl;

import cn.edu.njupt.zx120.dao.*;
import cn.edu.njupt.zx120.dto.admin.MemberBindingInfoDto;
import cn.edu.njupt.zx120.dto.serviceManage.MemberDutyDto;
import cn.edu.njupt.zx120.dto.serviceManage.MemberDynamicDto;
import cn.edu.njupt.zx120.model.*;
import cn.edu.njupt.zx120.service.MemberService;
import cn.edu.njupt.zx120.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 2/24/17.
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private StockDao stockDao;

    @Autowired
    private ResourceBindingDao resourceBindingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ContainerDao containerDao;

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public List<MemberModel> queryAll() {
        return memberDao.queryAll();
    }

    @Override
    public MemberModel queryById(String id) {
        return memberDao.queryById(id);
    }

    @Override
    public MemberModel queryByName(String name) {
        return memberDao.queryByName(name);
    }

    @Override
    public List<MemberModel> queryByType(int typeId) {
        return memberDao.queryByType(typeId);
    }

    @Override
    public List<MemberModel> queryByOrdId(String orgId) {
        return memberDao.queryByOrgId(orgId);
    }

    @Override
    public List<MemberModel> queryAvailableByOrgId(String orgId) {
        List<MemberModel> memberModels = memberDao.queryByOrgId(orgId);
        List<MemberModel> availableMemberModels = new ArrayList<>();
        for (MemberModel memberModel : memberModels) {
            List<EventModel> eventModels = eventDao.queryByResourceId(memberModel.getMemberId(), ResourceTableTypeEnum.MEMBER.getType());
            if (eventModels.size() == 0) {
                availableMemberModels.add(memberModel);
                continue;
            }
            EventModel eventModel = eventModels.get(eventModels.size() - 1);
            if (eventModel.getEventType().equals("check_in")) {
                availableMemberModels.add(memberModel);
            }
        }
        return availableMemberModels;
    }

    @Override
    public List<MemberModel> queryOutByOrgId(String orgId) {
        List<MemberModel> memberModels = memberDao.queryByOrgId(orgId);
        List<MemberModel> outMemberModels = new ArrayList<>();
        for (MemberModel memberModel : memberModels) {
            List<EventModel> eventModels = eventDao.queryByResourceId(memberModel.getMemberId(), ResourceTableTypeEnum.MEMBER.getType());
            if (eventModels.size() == 0) {
//                outMemberModels.add(memberModel);
                continue;
            }
            EventModel eventModel = eventModels.get(eventModels.size() - 1);
            if (eventModel.getEventType().equals("check_out")) {
                outMemberModels.add(memberModel);
            }
        }
        return outMemberModels;
    }

    @Override
    public List<MemberModel> queryByDepartment(String department) {
        return memberDao.queryByDepartment(department);
    }

    @Override
    public String queryPhoneNumberByName(String name) {
        return memberDao.queryByName(name).getPhoneNumber();
    }

    @Override
    public String queryPhoneNumberById(String id) {
        return memberDao.queryById(id).getPhoneNumber();
    }

    @Override
    public void cancelMember(String id) {
        memberDao.updateLastModifiedTime(id, new Date());
    }

    /**
     * 为成员创建相应的账户
     * @param id
     * @return
     */
    @Override
    public UserModel createAccountBasedOnMemberModel(String id) {
        MemberModel member = queryById(id);
        UserModel user = new UserModel();
        // set username according to member type
        user.setUsername(member.getType());
        user.setAccount(member.getName());
        // set default user password
        user.setPassword("123456");
        EncryptUtil encryptUtil = new EncryptUtil();
        encryptUtil.encryptPassword(user);
        userDao.saveUser(user);
        return user;
    }

    @Override
    public void createNewMember(MemberModel memberModel) {
        memberDao.createNewMember(memberModel);
    }

    @Override
    public void deleteMember(MemberModel memberModel) {
        memberDao.deleteMember(memberModel);
    }

    /**
     * 休息的员工需要更新库存，
     * 并且在event_info表中添加相应的记录
     * 同时更新member表中的状态字段
     * @param id
     */
    @Transactional
    @Override
    public void onRest(String id) {

        ContainerModel containerModel = containerDao.queryByResourceId(id, ResourceTableTypeEnum.MEMBER.getType());
        containerDao.deleteItem(containerModel);

        StockModel stockModel = stockDao.queryByRfidTag(
                resourceBindingDao.queryByResourceId(ResourceTableTypeEnum.MEMBER.getType(), id).getTag());
        stockDao.deleteItem(stockModel);

        EventModel eventModel = new EventModel();
//        eventModel.setContainerId(resourceBindingDao.queryByResourceId(ResourceTableTypeEnum.MEMBER.getType(), id).getTag());
        eventModel.setEventDescription("Member " + id + " is rest.");
        eventModel.setEventTime(new Date());
        eventModel.setEventResourceId(id);
        eventModel.setEventResourceTable(ResourceTableTypeEnum.MEMBER.getType());
        eventModel.setEventType(EventTypeEnum.MEMBER_REST.getType());
        eventDao.createNewEvent(eventModel);
    }

    /**
     * 员工上班
     * 增加库存记录
     * 在event_info表中记录相应的信息
     * @param id
     */
    @Transactional
    @Override
    public void hasArrivedOffice(String id) {

        ContainerModel containerModel = containerDao.queryByResourceId(id, ResourceTableTypeEnum.MEMBER.getType());
        containerDao.deleteItem(containerModel);

        StockModel stockModel = stockDao.queryByRfidTag(
                resourceBindingDao.queryByResourceId(ResourceTableTypeEnum.MEMBER.getType(), id).getTag());
        stockDao.deleteItem(stockModel);

        EventModel eventModel = new EventModel();
//        eventModel.setContainerId(containerDao.queryByResourceId(id, ResourceTableTypeEnum.MEMBER.getType()).getContainerId());
        eventModel.setEventTime(new Date());
        eventModel.setEventResourceId(id);
        eventModel.setEventResourceTable(ResourceTableTypeEnum.MEMBER.getType());
        eventModel.setEventType(EventTypeEnum.MEMBER_IN_OFFICE.getType());
        eventDao.createNewEvent(eventModel);
    }

    @Transactional
    @Override
    public void onCar(String id, String carId) {
        // delete original container record
        ContainerModel containerModel = containerDao.queryByResourceId(id, ResourceTableTypeEnum.MEMBER.getType());
        containerDao.deleteItem(containerModel);

        // add new container record
//        containerModel.setContainerType(ContainerTypeEnum.AMBULANCE.getType());
//        containerModel.setContainerId(carId);
        containerModel.setResourceTable(ResourceTableTypeEnum.MEMBER.getType());
        containerModel.setResourceId(id);
        containerModel.setLastScanTime(new Date());
        containerDao.addNewItem(containerModel);

        // delete original stock record
        StockModel stockModel = stockDao.queryByRfidTag(
                resourceBindingDao.queryByResourceId(ResourceTableTypeEnum.MEMBER.getType(), id).getTag());
        stockDao.deleteItem(stockModel);

        // add new event record
        EventModel eventModel = new EventModel();
        eventModel.setEventResourceId(id);
        eventModel.setEventResourceTable(ResourceTableTypeEnum.MEMBER.getType());
        eventModel.setEventType(EventTypeEnum.MEMBER_IN_AMBULANCE.getType());
//        eventModel.setContainerId(carId);
        eventModel.setEventTime(new Date());
        eventDao.createNewEvent(eventModel);
    }

    @Override
    public List<MemberDynamicDto> queryAllDynamicInfo() {

        List<MemberModel> memberModels = memberDao.queryAll();

        List<MemberDynamicDto> memberDynamicDtos = new ArrayList<>();

        for (MemberModel memberModel : memberModels) {

            // 得到人员所在的容器
            // 获取该人员所有的事件
            List<EventModel> eventModels = eventDao.queryByResourceId(memberModel.getMemberId(), ResourceTableTypeEnum.MEMBER.getType());

            // 如果没有查询到事件，直接跳过
            if (eventModels.size() == 0) continue;

            for (EventModel eventModel : eventModels) {
                MemberDynamicDto memberDynamicDto = new MemberDynamicDto();
                memberDynamicDto.setMemberName(memberModel.getName());
                memberDynamicDto.setContainerName(containerDao.queryByContainerId(eventModel.getContainerId()).getContainerName());
                memberDynamicDto.setTime(eventModel.getEventTime());
                if (eventModel.getEventType().equals("check_in")) {
                    memberDynamicDto.setDesc("进入");
                } else if (eventModel.getEventType().equals("check_out")) {
                    memberDynamicDto.setDesc("离开");
                }
                memberDynamicDtos.add(memberDynamicDto);
            }

        }

        return memberDynamicDtos;

    }

    @Override
    public List<MemberDutyDto> queryAllDutyInfo() {

        List<MemberModel> memberModels = memberDao.queryAll();

        List<MemberDutyDto> memberDutyDtos = new ArrayList<>();

        for (MemberModel memberModel : memberModels) {

            MemberDutyDto memberDutyDto = new MemberDutyDto();

            memberDutyDto.setMemberName(memberModel.getName());
            memberDutyDto.setDepartment(memberModel.getDepartment());
            memberDutyDto.setMemberType(memberModel.getType());

            // 根据相应车辆的RFID标签查询库存表
            // 如果库存表中存在相应车辆的库存记录
            // 那么根据记录中的容器id找到容器名称
            // 如果不存在库存记录
            // 那么设置位置为nothing
            ResourceBindingModel resourceBindingModel = resourceBindingDao.queryByResourceId(ResourceTableTypeEnum.MEMBER.getType(), memberModel.getMemberId());
            StockModel stockModel = null;
            if (resourceBindingModel != null) {
                stockModel = stockDao.queryByRfidTag(resourceBindingModel.getTag());
            } else continue;

            if (stockModel == null) {
                memberDutyDto.setContainerName("nothing");
                memberDutyDto.setDuty("离开");
            } else {
                memberDutyDto.setContainerName(containerDao.queryByContainerId(stockModel.getContainerId()).getContainerName());
                memberDutyDto.setDuty("在岗");
            }

            memberDutyDtos.add(memberDutyDto);
        }

        return memberDutyDtos;
    }

    @Override
    public List<MemberBindingInfoDto> queryAllBindingInfo() {

        List<MemberBindingInfoDto> memberBindingInfoDtos = new ArrayList<>();

        List<ResourceBindingModel> resourceBindingModels = resourceBindingDao.queryByResourceTable(ResourceTableTypeEnum.MEMBER.getType());

        for (ResourceBindingModel resourceBindingModel : resourceBindingModels) {
            MemberBindingInfoDto memberBindingInfoDto = new MemberBindingInfoDto();
            MemberModel memberModel = memberDao.queryById(resourceBindingModel.getResourceId());
            memberBindingInfoDto.setDepartment(memberModel.getDepartment());
            memberBindingInfoDto.setMemberId(memberModel.getMemberId());
            memberBindingInfoDto.setMemberName(memberModel.getName());
            memberBindingInfoDto.setOrgName(organizationDao.queryById(memberModel.getOrgId()).getName());
            memberBindingInfoDto.setTag(resourceBindingModel.getTag());
            memberBindingInfoDto.setTime(resourceBindingModel.getBindTime());
            memberBindingInfoDtos.add(memberBindingInfoDto);
        }

        return memberBindingInfoDtos;
    }

    @Override
    public List<MemberBindingInfoDto> queryAllUnboundInfo() {

        List<MemberBindingInfoDto> memberBindingInfoDtos = new ArrayList<>();
        List<MemberModel> memberModels = memberDao.queryAll();

        for (MemberModel memberModel : memberModels) {
            ResourceBindingModel resourceBindingModel = resourceBindingDao.queryByResourceId(ResourceTableTypeEnum.MEMBER.getType(), memberModel.getMemberId());
            if (resourceBindingModel == null) {
                MemberBindingInfoDto memberBindingInfoDto = new MemberBindingInfoDto();
                memberBindingInfoDto.setTime(null);
                memberBindingInfoDto.setTag(null);
                memberBindingInfoDto.setOrgName(organizationDao.queryById(memberModel.getOrgId()).getName());
                memberBindingInfoDto.setMemberName(memberModel.getName());
                memberBindingInfoDto.setMemberId(memberModel.getMemberId());
                memberBindingInfoDto.setDepartment(memberModel.getDepartment());
                memberBindingInfoDtos.add(memberBindingInfoDto);
            }
        }
        return memberBindingInfoDtos;
    }

    @Override
    public List<MemberBindingInfoDto> queryAllTodayBindingInfo() {

        List<MemberBindingInfoDto> memberBindingInfoDtos = new ArrayList<>();
        List<ResourceBindingModel> resourceBindingModels = resourceBindingDao.queryByResourceTable(ResourceTableTypeEnum.MEMBER.getType());

        for (ResourceBindingModel resourceBindingModel : resourceBindingModels) {
            // 判断绑定日期是否是当天
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (!format.format(resourceBindingModel.getBindTime()).equals(format.format(new Date())))
                continue;
            MemberBindingInfoDto memberBindingInfoDto = new MemberBindingInfoDto();
            MemberModel memberModel = memberDao.queryById(resourceBindingModel.getResourceId());
            memberBindingInfoDto.setDepartment(memberModel.getDepartment());
            memberBindingInfoDto.setMemberId(memberModel.getMemberId());
            memberBindingInfoDto.setMemberName(memberModel.getName());
            memberBindingInfoDto.setOrgName(organizationDao.queryById(memberModel.getOrgId()).getName());
            memberBindingInfoDto.setTag(resourceBindingModel.getTag());
            memberBindingInfoDto.setTime(resourceBindingModel.getBindTime());
            memberBindingInfoDtos.add(memberBindingInfoDto);
        }

        return memberBindingInfoDtos;
    }
}
