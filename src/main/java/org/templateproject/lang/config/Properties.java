package org.templateproject.lang.config;

import org.templateproject.lang.TP;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <b>author</b>: 伍文彬 Wuwenbin<br>
 * <b>date</b>: 2016年8月2日<br>
 * <b>time</b>: 下午4:57:51 导入<br>
 * <b>ClassName</b>: Properties<br>
 * <b>Description</b>: 读取或者写入properties文件的工具类<br>
 * <b>Version</b>: Ver 1.0.0<br>
 */
public final class Properties {

    private final String UTF_8 = "UTF-8";

    private final ConcurrentMap<String, java.util.Properties> PROPS = new ConcurrentHashMap<>();


    /**
     * @param filePath properties文件路径(classpath中的相对路径)
     * @param name
     * @return
     * @功能: 根据name获取properties文件中的value
     * @作者: yangc
     * @创建日期: 2013-11-21 下午07:01:48
     */
    public String getProperty(String filePath, String name) {
        if (TP.stringhelper.isBlank(filePath) || TP.stringhelper.isBlank(name)) {
            throw new IllegalArgumentException("The parameters must not be null");
        }
        try {
            java.util.Properties prop = PROPS.get(filePath);
            if (prop == null) {
                prop = new java.util.Properties();
                prop.load(Properties.class.getResourceAsStream(filePath));
                PROPS.put(filePath, prop);
            }
            return prop.getProperty(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param filePath     properties文件路径(classpath中的相对路径)
     * @param name
     * @param defaultValue
     * @return
     * @功能: 根据name获取properties文件中的value, 如果为空返回默认值
     * @作者: yangc
     * @创建日期: 2013-11-21 下午07:01:48
     */
    public String getProperty(String filePath, String name, String defaultValue) {
        if (TP.stringhelper.isBlank(filePath) || TP.stringhelper.isBlank(name) || TP.stringhelper.isBlank(defaultValue)) {
            throw new IllegalArgumentException("The parameters must not be null");
        }
        try {
            java.util.Properties prop = PROPS.get(filePath);
            if (prop == null) {
                prop = new java.util.Properties();
                prop.load(Properties.class.getResourceAsStream(filePath));
                PROPS.put(filePath, prop);
            }
            String value = prop.getProperty(name);
            return TP.stringhelper.isBlank(value) ? defaultValue : value;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }


}
