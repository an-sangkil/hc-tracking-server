package com.tracking.server.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
 * @since 2019-11-25
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Data
public class LogModel {

    private String id;
    /**
     * @DateTimeFormat -> 데이터 binding 시에 사용
     * @JsonFormat -> Json String 변환시 사용
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
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
    private Double vtr;

    /**
     * 비용(집행금액).
     */
    private Long spend;


    public LogModel() {
    }

    public String toJson() {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }

    }

}
