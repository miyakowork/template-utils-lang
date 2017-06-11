package me.wuwenbin.lang.common;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * <b>ClassName</b>: Validate<br>
 * <b>Description</b>: 判断对象、字符串、集合是否为空、不为空<br>
 * <b>Version</b>: Ver 1.0<br>
 * <p>
 * <b>author</b>:  Wuwenbin<br>
 * <b>date</b>: 2016年8月31日<br>
 * <b>time</b>: 下午2:08:22 <br>
 */
public final class Validate {

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return boolean
     * @author chenssy
     * @date Dec 23, 2013
     */
    @SuppressWarnings("unused")
    private <T> boolean isEmptyArray(T[] array) {
        if (array == null || array.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断数组是否不为空
     *
     * @param array
     * @return boolean
     * @author chenssy
     * @date Dec 23, 2013
     */
    public <T> boolean isNotEmptyArray(T[] array) {
        if (array != null && array.length > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param string
     * @return boolean
     * @author chenssy
     * @date Dec 23, 2013
     */
    public boolean isEmptyString(String string) {
        if (string == null || string.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否不为空
     *
     * @param string
     * @return boolean
     * @author chenssy
     * @date Dec 23, 2013
     */
    public boolean isNotEmptyString(String string) {
        if (string != null && string.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return boolean
     * @author chenssy
     * @date Dec 26, 2013
     */
    public boolean isEmptyCollection(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断集合是否不为空
     *
     * @param collection
     * @return boolean
     * @author chenssy
     * @date Dec 26, 2013
     */
    public boolean isNotEmptyCollection(Collection<?> collection) {
        if (collection != null && !collection.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断map集合是否不为空
     *
     * @param map
     * @return boolean
     * @author chenssy
     * @date Dec 26, 2013
     */
    @SuppressWarnings("rawtypes")
    public boolean isNotEmptyMap(Map map) {
        if (map != null && !map.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断map集合是否为空
     *
     * @param map
     * @return boolean
     * @author ming.chen
     * @date Dec 26, 2013
     */
    @SuppressWarnings("rawtypes")
    public boolean isEmptyMap(Map map) {
        if (map == null || map.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检验对象是否为空,String 中只有空格在对象中也算空.
     *
     * @param object
     * @return 为空返回true, 否则false.
     */
    @SuppressWarnings("rawtypes")
    public boolean isEmpty(Object object) {
        if (null == object)
            return true;
        else if (object instanceof String)
            return "".equals(object.toString().trim());
        else if (object instanceof Iterable)
            return !((Iterable) object).iterator().hasNext();
        else if (object.getClass().isArray())
            return Array.getLength(object) == 0;
        else if (object instanceof Map)
            return ((Map) object).size() == 0;
        else if (Number.class.isAssignableFrom(object.getClass()))
            return false;
        else if (Date.class.isAssignableFrom(object.getClass()))
            return false;
        else
            return false;
    }
}
