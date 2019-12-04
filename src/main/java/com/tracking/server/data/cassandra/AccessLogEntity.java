package com.tracking.server.data.cassandra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

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
@Table(value = "access_log")
@Getter
@Setter
@ToString
public class AccessLogEntity {

    @PrimaryKey
    private String id;

    private LocalDateTime time;

    /**
     * 노출수.
     */
    private Long impressions;

    /**
     * 클릭수.
     */
    private Long clicks;

    /**
     * 광고대비클릭율.
     */
    private Double ctr;

    /**
     * 동영상 조회수.
     */
    private Long videoViews;

    /**
     * VTR (조회율).
     */
    @Transient
    private Double vtr;

    /**
     * 비용(집행금액).
     */
    private Long spend;


}
