package cn.edu.njupt.zx120.service.impl;

import cn.edu.njupt.zx120.dao.AmbulanceDao;
import cn.edu.njupt.zx120.dao.ContainerDao;
import cn.edu.njupt.zx120.dao.OrganizationDao;
import cn.edu.njupt.zx120.dao.impl.ReaderDaoImpl;
import cn.edu.njupt.zx120.dto.admin.ReaderDto;
import cn.edu.njupt.zx120.model.ContainerModel;
import cn.edu.njupt.zx120.model.ReaderModel;
import cn.edu.njupt.zx120.model.ResourceTableTypeEnum;
import cn.edu.njupt.zx120.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 1/17/17.
 */
@Service("readerService")
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderDaoImpl readerDao;

    @Autowired
    private ContainerDao containerDao;

    @Autowired
    private AmbulanceDao ambulanceDao;

    @Autowired
    private OrganizationDao organizationDao;

    /**
     * 查询所有读卡器的信息
     * @return 读卡器信息列表
     */
    @Override
    public List<ReaderDto> queryAll() {
        List<ReaderModel> readerModels = readerDao.queryAll();
        List<ReaderDto> readerDtos = new ArrayList<>();
        for (ReaderModel readerModel : readerModels) {
            ReaderDto readerDto = new ReaderDto();
            readerDto.setActivated(readerModel.isActiveFlag());
            if (readerDto.isActivated()) {
                readerDto.setActiveTime(readerModel.getActiveTime());
            } else {
                readerDto.setShutdownTime(readerModel.getShutdownTime());
            }
            ContainerModel containerModel = containerDao.queryByContainerId(readerModel.getBindContainerId());
            readerDto.setContainerId(containerModel.getContainerId());
            readerDto.setContainerName(containerModel.getContainerName());

            if (containerModel.getResourceTable().equals(ResourceTableTypeEnum.AMBULANCE.getType())) {
                String orgId = ambulanceDao.queryById(containerModel.getResourceId()).getOrgId();
                readerDto.setOrgId(orgId);
                readerDto.setDepartment(organizationDao.queryById(orgId).getName());
            } else if (containerModel.getResourceTable().equals(ResourceTableTypeEnum.ORGANIZATION.getType())) {
                String orgId = containerModel.getResourceId();
                readerDto.setDepartment(organizationDao.queryById(orgId).getName());
            }

            readerDto.setReaderId(readerModel.getId());
            readerDtos.add(readerDto);
        }
        return readerDtos;
    }

    /**
     * 根据读卡器id查询单个读卡器信息
     * @param id 读卡器id
     * @return 读卡器信息
     */
    @Override
    public ReaderModel queryById(int id) {
        return readerDao.queryById(id);
    }

    /**
     * 判断读卡器是否存放在数据库中
     * @param readerModel
     * @return
     */
    @Override
    public boolean isReaderExisted(ReaderModel readerModel) {
        ReaderModel currentReaderModel = queryById(readerModel.getId());
        return (currentReaderModel != null);
    }

    /**
     * 添加新的读卡器
     * @Transactional 声明式事务支持
     * @param readerModel
     */
    @Override
    @Transactional
    public void addNewReader(ReaderModel readerModel) {
//        logger.info("Add a new readerModel");
        readerDao.save(readerModel);
//        logger.info("The new readerModel id is " + readerModel.getId());
    }

    /**
     * 删除指定id的读卡器信息
     * @Transactional 声明式事务支持
     * @param id
     */
    @Override
    @Transactional
    public void deleteReaderById(int id) {
//        logger.info("Delete the readerModel. id = " + id);
        ReaderModel readerModel = readerDao.queryById(id);
//        if (readerModel == null) {
//            logger.error("The readerModel id: " + id + " not found.");
//        } else {
//            readerDao.delete(readerModel);
//        }
    }

    /**
     * 激活指定id的读卡器
     * 设定active标志位
     * 设定active时间
     * @param id
     */
    @Override
    @Transactional
    public void activeReaderById(int id) {
        readerDao.activeReader(id);
        readerDao.setActiveTime(id, new Date());
    }

    /**
     * 关闭指定id的读卡器
     * 设定读卡器的active标志
     * 设定读卡器关闭的时间
     * @param id
     */
    @Override
    @Transactional
    public void shutdownReaderById(int id) {
        readerDao.shutdownReader(id);
        readerDao.setShutdownTime(id, new Date());
    }

    @Override
    public void saveReader(ReaderModel readerModel) {
        readerDao.save(readerModel);
    }

    @Override
    public void deleteReader(ReaderModel readerModel) {
        readerDao.delete(readerModel);
    }

    @Override
    public void updateReaderContainer(int readerId, long containerId) {
        readerDao.updateContainerId(readerId, containerId);
    }
}
