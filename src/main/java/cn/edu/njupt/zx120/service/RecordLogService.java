package cn.edu.njupt.zx120.service;

import cn.edu.njupt.zx120.dto.realtime.RecordLogDto;

import java.util.List;

/**
 * Created by wallance on 4/14/17.
 */
public interface RecordLogService {

    List<RecordLogDto> queryAll();

}
