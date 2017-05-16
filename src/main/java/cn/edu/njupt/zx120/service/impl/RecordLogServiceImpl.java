package cn.edu.njupt.zx120.service.impl;

import cn.edu.njupt.zx120.dao.RecordLogDao;
import cn.edu.njupt.zx120.dto.realtime.RecordLogDto;
import cn.edu.njupt.zx120.model.RecordLogModel;
import cn.edu.njupt.zx120.service.RecordLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wallance on 4/14/17.
 */
@Service("recordLogService")
public class RecordLogServiceImpl implements RecordLogService {

    @Autowired
    private RecordLogDao recordLogDao;

    @Override
    public List<RecordLogDto> queryLatestTenRecords() {
        List<RecordLogDto> recordLogDtos = new ArrayList<>();
        List<RecordLogModel> recordLogModels = recordLogDao.queryLatestTenRecords();
        for (RecordLogModel recordLogModel : recordLogModels) {
            RecordLogDto recordLogDto = new RecordLogDto();
            recordLogDto.setReaderId(recordLogModel.getReaderId());
            recordLogDto.setRecordId(recordLogModel.getId());
            recordLogDto.setRfidTag(recordLogModel.getRfidTag());
            recordLogDto.setStoreTime(recordLogModel.getStoreTime());
            recordLogDtos.add(recordLogDto);
        }
        return recordLogDtos;
    }

}
