package cn.edu.njupt.zx120.service;

import cn.edu.njupt.zx120.dto.admin.ReaderDto;
import cn.edu.njupt.zx120.model.ReaderModel;

import java.util.List;

/**
 * Created by wallance on 1/17/17.
 */
public interface ReaderService {

    List<ReaderDto> queryAll();

    ReaderModel queryById(int id);

    boolean isReaderExisted(ReaderModel readerModel);

    void addNewReader(ReaderModel readerModel);

    void deleteReaderById(int id);

    void activeReaderById(int id);

    void shutdownReaderById(int id);

    void updateReaderContainer(int readerId, long containerId);

    void saveReader(ReaderModel readerModel);

    void deleteReader(ReaderModel readerModel);

    //TODO: exception
}
