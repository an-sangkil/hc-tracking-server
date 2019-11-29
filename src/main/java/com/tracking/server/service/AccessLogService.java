package com.tracking.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracking.server.data.cassandra.AccessLogEntity;
import com.tracking.server.data.model.LogModel;
import com.tracking.server.repository.jpa.AccessLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|Mezzomedia. All right reserved.
 * @since 2019-11-29
 */
@Service
@Slf4j
public class AccessLogService {
    private AccessLogRepository accessLogRepository;

    public AccessLogService(AccessLogRepository accessLogRepository) {
        this.accessLogRepository = accessLogRepository;
    }

    public void saveAccessLog(String data) throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        LogModel  logModel =objectMapper.convertValue(data, LogModel.class);

        AccessLogEntity accessLogEntity = new AccessLogEntity();
        accessLogEntity.setData(logModel.getData());
        accessLogEntity.setTime(logModel.getTime());

        accessLogRepository.save(accessLogEntity);
    }
}
