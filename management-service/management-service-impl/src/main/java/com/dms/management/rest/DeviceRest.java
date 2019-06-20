package com.dms.management.rest;

import com.dms.management.api.DeviceApi;
import com.dms.management.model.Action;
import com.dms.management.model.Device;
import com.dms.management.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RestController;

import java.beans.FeatureDescriptor;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.elasticsearch.search.aggregations.AggregationBuilders.terms;

@RestController
@AllArgsConstructor
public class DeviceRest implements DeviceApi {

    private DeviceRepository deviceRepository;
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<Device> findDevices(Pageable pageable) {
        return deviceRepository.findAll(pageable).getContent();
    }

    @Override
    public List<String> findDeviceTypes(Pageable pageable) {
        Aggregations agg = elasticsearchTemplate.query(
                new NativeSearchQueryBuilder()
                        .withIndices("dms_device")
                        .withPageable(PageRequest.of(0, 1))
                        .addAggregation(terms("types").field("type").size(pageable.getPageSize()))
                        .build(), searchResponse -> searchResponse.getAggregations());

        return extractValuesFromAggregationsByName("types", agg);
    }

    @Override
    public Device findDeviceById(String id) {
        return deviceRepository.findById(id).orElse(null);
    }

    @Override
    public Device saveDevice(Device device) {
        Device deviceToSave = device;
        if (Objects.nonNull(device.getId())) {
            deviceToSave = deviceRepository.findById(device.getId()).orElse(deviceToSave);
            copyPropertyIgnoreNull(device, deviceToSave);
        }
        return deviceRepository.save(deviceToSave);
    }

    @Override
    public void addAction(String id, Action action) {
        deviceRepository.findById(id).ifPresent(device -> {
            device.setActions(Optional.ofNullable(device.getActions()).orElse(new ArrayList<>()));
            device.getActions().add(action);
            deviceRepository.save(device);
        });
    }

    @Override
    public void removeAction(String id, String name, String method) {
        deviceRepository.findById(id).ifPresent(device -> {
            device.getActions().removeIf(action -> Objects.equals(action.getName(), name) && Objects.equals(action.getMethod(), method));
            deviceRepository.save(device);
        });
    }

    private List<String> extractValuesFromAggregationsByName(String name, Aggregations aggregations) {
        Terms terms = aggregations.get(name);
        Collection<Terms.Bucket> buckets = (Collection<Terms.Bucket>) terms.getBuckets();
        return buckets.stream()
                .map(MultiBucketsAggregation.Bucket::getKeyAsString)
                .collect(Collectors.toList());
    }

    public <S> void copyPropertyIgnoreNull(S source, S target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private String[] getNullPropertyNames(Object object) {
        BeanWrapper wrappedSource = new BeanWrapperImpl(object);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

}
