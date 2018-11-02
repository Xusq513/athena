package com.refutrue.athena.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.util.HtmlUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil extends StringUtils {

    private static final Log logger = LogFactory.getLog(StringUtil.class);

    /**
     * 文件路径替换
     */
    public static final String FILE_SEPARATOR_REG = File.separator.equals("/") ? File.separator
            : File.separator + File.separator;

    /**
     * Groovy脚本请求文件包分隔符
     */
    public static final String FILE_PACKAGE_SEPARATOR = "\\.";

    /**
     * 替换格式:":变量名称"
     * SQL_VARIABLE_PATTERN 匹配任何非空白字符
     * DEFAULT_VARIABLE_PATTERN 匹配任何单词字符
     */
    private static Pattern SQL_VARIABLE_PATTERN = Pattern.compile(":(\\S*)");
    private static Pattern DEFAULT_VARIABLE_PATTERN = Pattern.compile(":(\\w*)");

    /**
     * 解析文件路径，classpath前缀
     */
    private static final String FILE_PRE_CLASSPATH = "classpath:";

    /**
     * 解析文件路径，classpath前缀
     */
    private static final String FILE_PRE_WEBROOT = "webroot:";

    /**
     * 创建一个新的实例 StringUtil.
     */
    private StringUtil() {

    }

    /**
     * 对象为NULL时，转换为空
     *
     * @param obj 验证对象
     * @return 空对象
     */
    public static String convertEmptyWhenNull(String obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    /**
     * 验收数字是否为正数
     *
     * @param str 验证对象
     * @return 正数:true,其他:false
     */
    public static boolean isPositiveNum(String str) {
        Pattern pattern = null;
        if (str == null) {
            return false;
        }
        pattern = Pattern.compile("^\\d+$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 验证两个对象是否相等
     *
     * @param obj1 对象一
     * @param obj2 对象二
     * @return 相等 true，不相等：false
     */
    public static boolean isEqual(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        } else if (obj1 == null) {
            return false;
        } else if (obj2 == null) {
            return false;
        } else {
            return obj1.equals(obj2);
        }
    }

    /**
     * 验证对象是否为空或NULl
     *
     * @param str 验证对象
     * @return 处理结果 空/Null:true,否则:false
     */
    public static boolean isEmptyOrNull(Object str) {
        if ("".equals(str) || null == str) {
            return true;
        }
        return false;
    }

    /**
     * 验证字符串是否为空字符串或NULl
     *
     * @param str 验证对象
     * @return 处理结果 空/Null:true,否则:false
     */
    public static boolean isSpaceOrNull(String str) {
        if (null == str || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 验证对象不为空也不为NULL
     *
     * @param str 验证对象
     * @return 处理结果 空/Null:true,否则:false
     */
    public static boolean isNotEmptyOrNull(Object str) {
        if (!"".equals(str) && null != str) {
            return true;
        }
        return false;
    }

    /**
     * 验证对象是否为null
     *
     * @param str 验证对象
     * @return 处理结果 null:true,否则:false
     */
    public static boolean isNull(Object str) {
        if ("".equals(str) || null == str) {
            return true;
        }
        return false;
    }

    /**
     * 验证字符串为数据
     *
     * @param str 字符串
     * @return 数字：true，其他：false
     */
    public static boolean isNumeric(String str) {
        if (isSpaceOrNull(str)) {
            return false;
        }
        Pattern pattern = null;
        pattern = Pattern.compile("^-?\\d+$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 自定义正则表达式进行各类验证
     *
     * @param str   待验证字符串
     * @param regex
     * @return 验证通过：true, 不通过：false
     */
    public static boolean regularValid(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if (!m.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 将字符串分解
     *
     * @param str      字符串
     * @param separate 分隔符
     * @return 分隔数组
     */
    public static String[] splitToArr(String str, String separate) {
        if (isEmptyOrNull(str)) {
            return new String[0];
        } else {
            return str.split(separate);
        }
    }

    /**
     * 字符串前自动补充所需字符
     *
     * @param c       所需补充的字符
     * @param length  希望补充后字符总长度
     * @param content 需要格式化的字符串
     * @return 补0后的结果字符串
     */
    public static String flushLeft(char c, long length, String content) {
        String str = "";
        String cs = "";
        if (content.length() > length) {
            str = content;
        } else {
            for (int i = 0; i < length - content.length(); i++) {
                cs = cs + c;
            }
        }
        str = cs + content;
        return str;
    }

    /**
     * 汉字转拼音缩写
     *
     * @param str 要转换的汉字字符串
     * @return 拼音缩写
     */
    public static String getPYString(String str) {
        String tempStr = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 字母和符号原样保留
            if ((int) c >= 33 && (int) c <= 126) {
                tempStr += String.valueOf(c);
            } else {
                // 累加拼音声母
                tempStr += getPYChar(String.valueOf(c));
            }
        }
        return tempStr;
    }

    /**
     * 获取小数点后两个小数点的字符串
     *
     * @param value 数值
     * @return 小数值
     */
    public static String getMoneyStr(double value) {
        DecimalFormat df = new DecimalFormat("##.##");
        Double fmtValue = Double.parseDouble(df.format(value)) + 0.001;
        df = new DecimalFormat("##.###");
        String tempStr = df.format(fmtValue);
        return tempStr.substring(0, tempStr.length() - 1);

    }

    /**
     * 替换字符串中的变量
     * <p/>
     * <pre>
     * String sql = "select * from cust where custId = :custId and custName = :custName";
     * Map<String, Object> map = new HashMap<String, Object>();
     * map.put("custId", 123123);
     * map.put("custName", "客户");
     * System.out.println(replaceDftVariable(sql, map));
     * 输出：
     * select * from cust where custId = 123123 and custName = 客户
     * </pre>
     *
     * @param variableSql    含变量的字符串，变量标识方法【:variableKey】
     * @param variableValues 含变量的键值对
     * @return 返回替换后的SQL
     */
    public static String replaceDftVariable(String variableSql, Map<String, Object> variableValues) {
        // 缓存字符区
        StringBuffer sb = new StringBuffer();
        // 先对 inXML 进行seq 占位符替换
        Matcher matcher = null;
        matcher = SQL_VARIABLE_PATTERN.matcher(variableSql);
        while (matcher.find()) {
            String variableName = matcher.group(1);
            String variableValue = String.valueOf(variableValues.get(variableName));
            variableValue = variableValue.replaceAll("\\\\","\\\\\\\\").replaceAll("\\$", "\\\\\\$");// 对 \ , $ 的特殊字符转义
            if (!(variableValue.startsWith("'") && variableValue.endsWith("'"))) {
                variableValue = "'" + variableValue + "'";
            }
            matcher.appendReplacement(sb, variableValue);
        }
        matcher.appendTail(sb);

        // 替换的字符串
        return sb.toString();
    }
    
    /**
     * 不增加单引号的  替换字符串中的变量
     * @param variableSql
     * @param variableValues
     * @return
     */
    public static String replaceDftVariableWithOutSQ(String variableSql, Map<String, Object> variableValues) {
        // 缓存字符区
        StringBuffer sb = new StringBuffer();
        // 先对 inXML 进行seq 占位符替换
        Matcher matcher = null;
        matcher = SQL_VARIABLE_PATTERN.matcher(variableSql);
        while (matcher.find()) {
            String variableName = matcher.group(1);
            String variableValue = String.valueOf(variableValues.get(variableName));
            variableValue = variableValue.replaceAll("\\\\","\\\\\\\\").replaceAll("\\$", "\\\\\\$");// 对 \ , $ 的特殊字符转义
            matcher.appendReplacement(sb, variableValue);
        }
        matcher.appendTail(sb);

        // 替换的字符串
        return sb.toString();
    }
    


    /**
     * 获取字符串中的变量
     *
     * @param variableSql 含变量的字符串，变量标识方法【:variableKey】
     * @return variableList 包含所有变量名称的list
     */
    public static List<String> findDftVariable(String variableSql) {

        List<String> variableList = new ArrayList<String>();
        // 先对 inXML 进行seq 占位符替换
        Matcher matcher = null;
        matcher = SQL_VARIABLE_PATTERN.matcher(variableSql);
        while (matcher.find()) {
            String variableName = matcher.group(1);
            variableList.add(variableName);
        }
        // 替换的字符串
        return variableList;
    }

    /**
     * 取单个字符的拼音声母
     *
     * @param c 要转换的单个汉字
     * @return 拼音声母
     */
    public static String getPYChar(String c) {
        /** 对出错的空格进行过滤字符进行过滤 **/
        if (c.trim().length() == 0) {
            return c;
        }
        byte[] array = new byte[2];
        array = String.valueOf(c).getBytes();
        int i = (short) (array[0] - '\0' + 256) * 256 + ((short) (array[1] - '\0' + 256));

        if (i < 0xB0A1) {
            return "*";
        } else if (i < 0xB0C5) {
            return "A";
        } else if (i < 0xB2C1) {
            return "B";
        } else if (i < 0xB4EE) {
            return "C";
        } else if (i < 0xB6EA) {
            return "D";
        } else if (i < 0xB7A2) {
            return "E";
        } else if (i < 0xB8C1) {
            return "F";
        } else if (i < 0xB9FE) {
            return "G";
        } else if (i < 0xBBF7) {
            return "H";
        } else if (i < 0xBFA6) {
            return "J";
        } else if (i < 0xC0AC) {
            return "K";
        } else if (i < 0xC2E8) {
            return "L";
        } else if (i < 0xC4C3) {
            return "M";
        } else if (i < 0xC5B6) {
            return "N";
        } else if (i < 0xC5BE) {
            return "O";
        } else if (i < 0xC6DA) {
            return "P";
        } else if (i < 0xC8BB) {
            return "Q";
        } else if (i < 0xC8F6) {
            return "R";
        } else if (i < 0xCBFA) {
            return "S";
        } else if (i < 0xCDDA) {
            return "T";
        } else if (i < 0xCEF4) {
            return "W";
        } else if (i < 0xD1B9) {
            return "X";
        } else if (i < 0xD4D1) {
            return "Y";
        } else if (i < 0xD7FA) {
            return "Z";
        } else {
            return "*";
        }
    }

    /**
     * @param target       需要转化的对象
     * @param defaultValue 缺省值
     * @return 转化结果:如果为null，则返回缺省值，否则，返回toString()
     * @throws
     * @author liyx
     */
    public static String obj2Str(Object target, String defaultValue) {
        String value = defaultValue;
        if (target != null) {
            value = String.valueOf(target);
        }
        return value;

    }

    /**
     * @param target 需要转化的对象
     * @return 转化结果 :如果为null，空字符串，不为空则toString()
     * @throws
     * @author liyx
     */
    public static String obj2Str(Object target) {
        return obj2Str(target, "");
    }

    /**
     * @param str    待检索的字符串
     * @param search 需要搜索的字符串
     * @return 转化结果 :如果为null，空字符串，不为空则toString()
     * @throws
     * @author liyx
     */
    public static boolean contains(String str, String search) {
        if (str == null || search == null) {
            return false;
        }
        return str.contains(search);
    }

    /**
     * 转换HTML特殊字符
     * <p/>
     * <pre>
     * 例如:<br/>
     * 输入：<a href=\"http:\\\\www.baidu.com\">
     * 输出：&lt;a href=&quot;http:\\www.baidu.com&quot;&gt;
     * </pre>
     *
     * @param inStr 正常字符
     * @return 转换后的字符
     */
    public static String htmlEscape(String inStr) {
        return HtmlUtils.htmlEscape(inStr);
    }

    /**
     * HTML字符反向处理
     * <p/>
     * <pre>
     * 例如:<br/>
     * 输入：&lt;a href=&quot;http:\\www.baidu.com&quot;&gt;
     * 输出：<a href="http:\\www.baidu.com">
     * </pre>
     *
     * @param inStr 含HTML的特殊字符的字符串
     * @return 转换后的字符串
     */
    public static String htmlUnescape(String inStr) {
        return HtmlUtils.htmlUnescape(inStr);
    }





    /**
     * @param maxLen 限定长度
     * @return 转化结果 :从0位截取限定长度的串
     * @author gaocy
     */
    public static String maxStr(String oldStr, int maxLen) {
        if (oldStr == null || "".equals(oldStr)) {
            return "";
        }
        if (oldStr.length() > maxLen)
            oldStr = oldStr.substring(0, maxLen);
        return oldStr;
    }

    /**
     * <p>
     * 剔除inStr中的特殊字符
     * </p>
     *
     * @param inStr
     * @return
     * @author liwq
     */
    public static String trimSymbol(String inStr) {
        if (inStr == null || "".equals(inStr.trim())) {
            return "";
        }
        return inStr.replaceAll("\\D", "");
    }

    /**
     * 去逗号(去string最后一位)
     *
     * @return
     */
    public static String transStrComma(String str) {
        if (!"".equals(str)) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * <p>
     * 字符串格式化，不足右补空格 例如： 字符串 target="aaa" defaultValue=" " fixedLong=5 则 转换后为"aaa  "
     * </p>
     *
     * @param target    需要转化的对象
     * @param fixedLong 固定长度函数
     * @return String 转化结果:不足又补缺省值
     * @throws
     * @author liuyhd 2012-12-06
     */
    public static String str2Fix(Object target, int fixedLong) {
        if (target == null) {
            target = obj2Str(target);
        }
        return str2Fix(target.toString(), " ", fixedLong);
    }

    /**
     * <p>
     * 字符串格式化，不足右补字符串（自定义）,超出截取
     * 例如： 字符串 target="aaa"  defaultValue=" " fixedLong=5 则 转换后为"aaa  "
     * </p>
     *
     * @param target       需要转化的对象
     * @param defaultValue 缺省值(1位）
     * @param fixedLong    固定长度函数
     * @return 转化结果:不足又补缺省值
     * @throws
     * @author liuyhd 2012-12-06
     */
    public static String str2Fix(String target, String defaultValue, int fixedLong) {
        if (target == null) {
            target = "";
        }
        int len = target.length();
        if (len == fixedLong) {
            return target;
        } else if (len < fixedLong) {
            StringBuilder sb = new StringBuilder();
            sb.append(target);
            for (int i = 0; i < fixedLong - len; i++) {
                sb.append(defaultValue);
            }
            return sb.toString();
        } else {
            return target.substring(0, fixedLong);
        }
    }

    /**
     * <p>
     * 字符串格式化，这里注意获取的是字节长度，不是字符长度，不足右补字符串（自定义）,超出不处理
     * 例如： 字符串 target="aaa"  defaultValue=" " fixedLong=5 则 转换后为"aaa  "
     * </p>
     *
     * @param target       需要转化的对象
     * @param defaultValue 缺省值(1位)
     * @param fixedLong    固定长度函数
     * @return 转化结果:不足又补缺省值
     * @author sunhao 2013-03-27
     */
    public static String strByte2Fix(String target, String defaultValue, int fixedLong) {
        if (target == null) {
            target = "";
        }
        int len = target.getBytes().length;
        if (len == fixedLong) {
            return target;
        } else if (len < fixedLong) {
            StringBuilder sb = new StringBuilder();
            sb.append(target);
            for (int i = 0; i < fixedLong - len; i++) {
                sb.append(defaultValue);
            }
            return sb.toString();
        } else {
            return target;
        }
    }
    
    /**
     * 过滤不合法的 XML 字符
     * @param in
     * @return
     */
    public static String stripNonValidXMLCharacters(String in) {
	    StringBuffer out = new StringBuffer(); // Used to hold the output.
	    char current; // Used to reference the current character.

	    if (in == null || ("".equals(in)))
	        return ""; // vacancy test.
	    for (int i = 0; i < in.length(); i++) {
	        current = in.charAt(i); // NOTE: No IndexOutOfBoundsException caught
	                                // here; it should not happen.
	        if ((current == 0x9) || (current == 0xA) || (current == 0xD)
	                || ((current >= 0x20) && (current <= 0xD7FF))
	                || ((current >= 0xE000) && (current <= 0xFFFD))
	                || ((current >= 0x10000) && (current <= 0x10FFFF)))
	            out.append(current);
	    }
	    return out.toString();
	}
    
    /**
	 * XML文件中的合法Unicode字符范围（十六进制）    Char ::= #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
	 * any Unicode character, excluding the surrogate blocks, FFFE, and FFFF. 
	 * 
	 * 需要过滤的字符的范围为：
	 *	0x00-0x08
	 *	0x0b-0x0c
	 *	0x0e-0x1f
	 *
	 * 和(and) &   &amp; 
	 * 单引号  '    &apos; 
	 * 双引号  "    &quot;
	 * 大于号  >    &gt;
	 * 小于号  <    &lt;
	 */
//	public static String format(String s) {
//		String reg = "[//x00-//x08//x0b-//x0c//x0e-//x1f]";// 过滤掉非法字符
//		if (s == null)
//			return "";
//		else {
//			s = s.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
//					.replaceAll(">", "&gt;").replaceAll("\"", "&quot;")
//					.replaceAll(reg, "");
//			return s;
//		}
//	}

    /**
     * 过滤路径中的特殊字符，防止xss漏洞攻击
     * @param value %27:' %22:" %3E:> %3C:< %3D:= %2F:/
     * @return
     */
    public static String xssFilter (String value) {
        if (isEmptyOrNull(value)) {
            return "";
        }
        value = value.replaceAll("\r", "")
                .replaceAll("\n", "")
                .replaceAll("(?i)script", "")
                .replaceAll("(?i)alert", "")
                .replaceAll("(?i)prompt", "")
                .replaceAll("%27", "")
                .replaceAll("%22", "")
                .replaceAll("%3E", "")
                .replaceAll("%3C", "")
                .replaceAll("%3D", "")
                .replaceAll("%2F", "");
        return value;
    }

    /**
     * 根据操作系统的不一致转换文件路劲，可以读取的文件格式为
     * classpath:dir1/dir1/file1 :基于classpath读取
     * webroot:dir1/dir1/file1 :基于web工程读取
     * d:/dir1/dir1/file1 :据对路径读取
     *
     * @param inPath 输入文件路径
     * @return 与操作系统对应的文件路径
     */
//    public static String formatFilePath(String inPath) {
//        if (logger.isDebugEnabled()) {
//            logger.debug("调用【formatFilePath】方法开始，入参： [inPath]=" + inPath);
//        }
//        String realFilePath = "";
//        if (StringUtil.isNotEmptyOrNull(inPath) && inPath.startsWith(FILE_PRE_CLASSPATH)) {
//            ClassLoader classLoader = StringUtil.class.getClassLoader();
//            String fileClassPath = inPath.substring(10, inPath.length());
//            URL url = classLoader.getResource(fileClassPath);
//            if (url == null) {
//                /*throw new SystemException(UtilConstant.MODEL_CODE_UTIL,
//                        UtilErrorMessage.FILE_READ_ERROR, fileClassPath);*/
//            } else {
//                realFilePath = url.getFile();
//            }
//        } else if (StringUtil.isNotEmptyOrNull(inPath) && inPath.startsWith(FILE_PRE_WEBROOT)) {
//            String webRoot = App.getAppPath();
//            if (!webRoot.endsWith(File.separator)) {
//                webRoot += File.separator;
//            }
//            realFilePath = webRoot + inPath.substring(8, inPath.length());
//        } else {
//            realFilePath = inPath;
//        }
//        // 特殊字符替换
//        realFilePath = realFilePath.replaceAll("\\\\", FILE_SEPARATOR_REG);
//        realFilePath = realFilePath.replaceAll("/", FILE_SEPARATOR_REG);
//        // 返回处理结果
//        try {
//            realFilePath = java.net.URLDecoder.decode(realFilePath, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            logger.error("路径转换编码错误: ", e);
//        }
//        if (logger.isDebugEnabled()) {
//            logger.debug("调用【formatFilePath】方法结束，出参： [realFilePath]=" + realFilePath);
//        }
//        return realFilePath;
//    }

    /**
     * 格式化文件夹目录，输入路径中自动补全文件分隔符
     * @param inPath 参考formatFilePath方法入参
     * @return 末尾包含文件夹分隔符的路径
     */
//    public static String formatDirPath(String inPath) {
//        if (logger.isDebugEnabled()) {
//            logger.debug("调用【formatDirPath】方法开始，入参： [inPath]=" + inPath);
//        }
//        String realFilePath = formatFilePath(inPath);
//        if (!realFilePath.endsWith(FILE_SEPARATOR_REG)) {
//            realFilePath = realFilePath + FILE_SEPARATOR_REG;
//        }
//        if (logger.isDebugEnabled()) {
//            logger.debug("调用【formatDirPath】方法结束，出参： [realFilePath]=" + realFilePath);
//        }
//        return realFilePath;
//    }
    /**
     * 将double格式的数字格式化为含两位小数的字符串
     *
     * @param money double数值
     * @return 两位小数的字符串
     */
    public static String formatDouble(Double money) {
        if (money == null) {
            return "0.00";
        } else {
            Format format = new DecimalFormat("#0.00");
            return format.format(money);
        }
    }
}
