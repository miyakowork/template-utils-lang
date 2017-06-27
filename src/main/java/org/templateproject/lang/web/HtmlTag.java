package org.templateproject.lang.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 有关web中html转义的相关工具类方法
 * Created By wuwenbin on in 2016/9/2
 */
public class HtmlTag {
    /**
     * <b>Author</b> : Wuwenbin<br>
     * <b>Title</b> : html2Text<br>
     * <b>Description</b> : 将html转译为文本内容<br>
     *
     * @param inputString
     * @return
     */
    public String html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            textStr = htmlStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textStr;// 返回文本字符串
    }

    /**
     * <b>Author</b> : Wuwenbin<br>
     * <b>Title</b> : filterHtmlTag<br>
     * <b>Description</b> : 过滤指定标签<br>
     *
     * @param str
     * @param tag
     * @return
     */
    public String filterHtmlTag(String str, String tag) {
        String reg_xp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
        Pattern pattern = Pattern.compile(reg_xp);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 替换指定的标签
     *
     * @param str
     * @param beforeTag 要替换的标签
     * @param tagAttr   要替换的标签属性值
     * @param startTag  新标签开始标记
     * @param endTag    新标签结束标记
     * @return
     */
    public String replaceHtmlTag(String str, String beforeTag, String tagAttr, String startTag, String endTag) {
        String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";
        String regxpForTagAttrib = tagAttr + "=\"([^\"]+)\"";
        Pattern patternForTag = Pattern.compile(regxpForTag);
        Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
        Matcher matcherForTag = patternForTag.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = matcherForTag.find();
        while (result) {
            StringBuffer sbReplace = new StringBuffer();
            Matcher matcherForAttr = patternForAttrib.matcher(matcherForTag.group(1));
            if (matcherForAttr.find()) {
                matcherForAttr.appendReplacement(sbReplace, startTag + matcherForAttr.group(1) + endTag);
            }
            matcherForTag.appendReplacement(sb, sbReplace.toString());
            result = matcherForTag.find();
        }
        matcherForTag.appendTail(sb);
        return sb.toString();
    }

}
