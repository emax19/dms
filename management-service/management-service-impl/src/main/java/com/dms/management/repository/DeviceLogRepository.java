package com.dms.management.repository;

import com.dms.management.model.DeviceLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceLogRepository extends ElasticsearchRepository<DeviceLog, String> {
}
