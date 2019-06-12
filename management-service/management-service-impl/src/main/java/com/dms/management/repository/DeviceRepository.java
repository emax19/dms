package com.dms.management.repository;

import com.dms.management.model.Device;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends ElasticsearchRepository<Device, String> {
}
