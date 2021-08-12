package by.dm13y.core.cglib.service.impl;

import by.dm13y.core.cglib.service.ResourceService;

import java.util.Collections;
import java.util.List;

public class ResourceServiceImpl implements ResourceService {
    private final List<String> itemList;

    public ResourceServiceImpl() {
        this.itemList = Collections.emptyList();
    }

    public ResourceServiceImpl(List<String> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String getServiceName() {
        return "ResourceServiceImplementation";
    }

    @Override
    public Integer getCountItems() {
        return itemList.size();
    }

    @Override
    public String getType() {
        return getServiceName().toUpperCase();
    }

    public String getAdditionalInfo() {
        return "ADDITIONAL_INFO" + getType();
    }
}
