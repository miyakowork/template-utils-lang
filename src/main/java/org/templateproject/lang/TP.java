package org.templateproject.lang;

import org.templateproject.lang.base.*;
import org.templateproject.lang.clazz.ClassKit;
import org.templateproject.lang.clazz.ClassScan;
import me.wuwenbin.lang.base.*;
import org.templateproject.lang.config.Properties;
import org.templateproject.lang.config.XmlDom4j;
import org.templateproject.lang.config.XmlW3c;
import org.templateproject.lang.file.FilePath;
import org.templateproject.lang.file.Files;
import org.templateproject.lang.file.Zip;
import org.templateproject.lang.idcard.IDCard;
import org.templateproject.lang.okane.Amount;
import org.templateproject.lang.okane.Money;
import org.templateproject.lang.text.PinYin;
import org.templateproject.lang.text.Placeholder;
import org.templateproject.lang.text.StringHelper;
import me.wuwenbin.lang.web.*;
import org.templateproject.lang.base.Runtime;
import org.templateproject.lang.web.*;

/**
 * 工具集合入口
 * Created by wuwenbin on 2017/6/11.
 */
public class TP {

    /**
     * 金额操作
     */
    public static Amount amount = new Amount();

    /**
     * java class扫描
     */
    public static ClassScan classScan = new ClassScan();

    /**
     * 有关web中html转义的相关工具类方法
     */
    public static HtmlTag htmlTag = new HtmlTag();

    /**
     * 身份证校验工具类，提供较为精确的身份证校验
     */
    public static IDCard idCard = new IDCard();

    /**
     * 图片处理工具类：功能：缩放图像、切割图像、图像类型转换、彩色转黑白、文字水印、图片水印等
     */
    public static Image image = new Image();

    /**
     * 金钱处理工具类
     */
    public static Money money = new Money();

    /**
     * 带多音字的拼音处理工具
     */
    public static PinYin pinYin = new PinYin();

    /**
     * 字符串占位模板替换工具
     */
    public static Placeholder placeholder = new Placeholder();

    /**
     * 随机工具类
     */
    public static Random random = new Random();

    /**
     * 正则相关操作
     */
    public static Regex regex = new Regex();

    /**
     * 一般Controller层或者需要对WEB操作的工具方法
     */
    public static RequestWeb requestWeb = new RequestWeb();

    /**
     * 字符串工具类
     */
    public static StringHelper stringhelper = new StringHelper();

    /**
     * url解码/编码
     */
    public static Url url = new Url();

    /**
     * 判断对象、字符串、集合是否为空、不为空
     */
    public static Validate validate = new Validate();

    /**
     * 有关web或Controller层获取到的相关日期的操作
     */
    public static WebDate webDate = new WebDate();

    /**
     * Web防火墙工具类
     */
    public static WebFireWall webFireWall = new WebFireWall();

    /**
     * 文件压缩、解压工具类。文件压缩格式为zip
     */
    public static Zip zip = new Zip();

    /**
     * 读取或者写入properties文件的工具类
     */
    public static Properties properties = new Properties();

    /**
     * dom4j方式处理xml
     */
    public static XmlDom4j dom4jXml = new XmlDom4j();

    /**
     * w3c方式处理xml
     */
    public static XmlW3c w3cXml = new XmlW3c();

    /**
     * 文件名及文件路径相关的操作
     */
    public static FilePath filePath = new FilePath();

    /**
     * 文件工具类
     */
    public static Files files = new Files();

    /**
     * 运行时工具，获取运行时，相关信息
     */
    public static Runtime runtime = new Runtime();

    /**
     * 类操作相关工具
     */
    public static ClassKit classKit = new ClassKit();

}
