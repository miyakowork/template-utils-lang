package org.templateproject.lang.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 一般Controller层或者需要对WEB操作的工具方法
 * Created by wuwenbin on 2017/5/11.
 */
public class RequestWeb {

    /**
     * text[]Map to text Map
     *
     * @param requestWebMap
     * @return
     */
    public Map<String, Object> arrayMapToStringMap(Map<String, String[]> requestWebMap) {
        Iterator<?> iterator = requestWebMap.entrySet().iterator();
        HashMap map;
        String key;
        String val;
        for (map = new HashMap(); iterator.hasNext(); map.put(key, val)) {
            Map.Entry entry = (Map.Entry) iterator.next();
            key = entry.getKey().toString();
            Object value = entry.getValue();
            if (value instanceof String[]) {
                String[] strings = ((String[]) value);
                val = strings[0];
            } else {
                val = value.toString();
            }
        }
        return map;
    }
}
