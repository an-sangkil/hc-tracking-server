package com.tracking.server.repository.jpa;

import com.tracking.server.data.cassandra.AccessLogEntity;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

/**
 * <pre>
 * Description : 엑세스 로그 레파지토리
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|Mezzomedia. All right reserved.
 * @since 2019-11-29
 */
public interface AccessLogRepository extends ReactiveCassandraRepository<AccessLogEntity, Integer> {


}
