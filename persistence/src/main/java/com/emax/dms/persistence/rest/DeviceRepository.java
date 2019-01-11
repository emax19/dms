package com.emax.dms.persistence.rest;

import com.emax.dms.persistence.model.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "device", path = "devices")
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Page<Device> findByNameLike(String name, Pageable pageable);

}
