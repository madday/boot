package com.appz9001.boot.base;

import com.alibaba.fastjson.JSON;
import com.appz9001.boot.service.AppRoomService;
import com.appz9001.boot.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceKey = ThreadLocal.withInitial(() -> "defaultDataSource");

    public static Map<Object, Object> dataSourcesMap = new ConcurrentHashMap<>(10);

    static {
        dataSourcesMap.put("defaultDataSource", SpringUtil.getBean("defaultDataSource"));
    }

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSource.dataSourceKey.get();
    }

    public static void setDataSource(String dataSource) {
        DynamicDataSource.dataSourceKey.set(dataSource);
        DynamicDataSource dynamicDataSource = (DynamicDataSource) SpringUtil.getBean("dataSource");
        dynamicDataSource.afterPropertiesSet();
    }

    public static String getDataSource() {
        return DynamicDataSource.dataSourceKey.get();
    }

    public static void clear() {
        DynamicDataSource.dataSourceKey.remove();
    }

    public static boolean containsDataSource(String dataSourceId){
        return dataSourcesMap.containsKey(dataSourceId);
    }
}
