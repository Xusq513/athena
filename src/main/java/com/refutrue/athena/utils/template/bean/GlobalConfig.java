package com.refutrue.athena.utils.template.bean;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.refutrue.athena.utils.StringUtil;
import com.refutrue.athena.utils.template.exception.TemplateException;

/**
 * 
 * <p>
 * Title: 模板全局配置
 * </p>
 * <p>
 * Description: 此模型为充血模型
 * </p>
 * 
 * @author Xusq
 * @date 2018年11月5日
 */
public class GlobalConfig {

	private static GlobalConfig globalConfig = new GlobalConfig();

	private GlobalConfig() {

	}

	public static GlobalConfig getInstance() {
		return globalConfig;
	}

	/**
	 * 默认配置文件位置
	 */
	public static final String DEFAULT_CONFIG_PATH = "GlobalConfig.properties";
	
	public static final String CONFIG_BASE_PACKAGE = "Base_Package";
	
	public static final String CONFIG_DB_TYPE = "DBType";
	
	public static final String CONFIG_TABLE_PREFIX = "Table_Prefix";
	
	public static final String CONFIG_COLUMN_PREFIX = "Column_Prefix";
	
	public static final String CONFIG_GENERATE_METHOD = "Generate_Method";
	
	public static final String CONFIG_BASE_DIR = "Base_Dir";
	
	public static final String CONFIG_GENERATE_CONTROLLER_DIR = "Generate_Controller_Dir";
	
	public static final String CONFIG_GENERATE_SERVICE_DIR = "Generate_Service_Dir";
	
	public static final String CONFIG_GENERATE_MAPPER_DIR = "Generate_Mapper_Dir";
	
	public static final String CONFIG_TYPE_PREFIX = "Type_Convert_";
	

	/**
	 * 工程基础包
	 */
	private String basePackage;

	/**
	 * 工程数据库类型 目前仅支持MYSQL
	 */
	private String dbType = "MYSQL";

	/**
	 * 表前缀，在实体的基础上加入前缀，如ProdOffer转化为t_prod_offer,t为前缀
	 */
	private String tablePrefix = "t";
	
	/**
	 * 生成文件的两种方式，Project和Local两种方式
	 */
	private String generateMethod = "Project";
	
	/**
	 * Local方式本地文件存放的位置
	 */
	private String baseDir;
	
	/**
	 * controller的目录
	 */
	private String generateControllerDir = "controller";
	
	/**
	 * service的目录
	 */
	private String generateServiceDir = "service";
	
	/**
	 * mapper的目录
	 */
	private String generateMapperDir = "mapper";

	/**
	 * 字段前缀，同表前缀
	 */
	private String columnPrefix = "";

	/**
	 * 实体类型和数据库类型的默认转化
	 */
	private Map<Class<?>, String> typeConvertRelMap;

	/**
	 * 初始化配置方法
	 * @param configPath
	 * @return
	 * @throws TemplateException
	 */
	public GlobalConfig init(String configPath) throws TemplateException{
		if(configPath == null) {
			configPath = DEFAULT_CONFIG_PATH;
		}
		Properties properties = new Properties();
		InputStream in = null;
		try {
			// 使用ClassLoader加载properties配置文件生成对应的输入流
			in = GlobalConfig.class.getClassLoader().getResourceAsStream(configPath);
			// 使用properties对象加载输入流
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			throw new TemplateException("读取全局配置文件异常，请检查配置文件！");
		}finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		bindProperties(properties);
		return globalConfig;
	}
	
	/**
	 * 重载初始化
	 * @return
	 * @throws TemplateException
	 */
	public GlobalConfig init() throws TemplateException{
		return init(null);
	}
	
	/**
	 * 绑定变量
	 * @param properties
	 */
	private void bindProperties(Properties properties) {
		String basePackage = properties.getProperty(CONFIG_BASE_PACKAGE,"");
		globalConfig.setBasePackage(basePackage.trim());
		String dbType = properties.getProperty(CONFIG_DB_TYPE,"");
		globalConfig.setDbType(dbType.trim());
		String tablePrefix = properties.getProperty(CONFIG_TABLE_PREFIX,"");
		globalConfig.setTablePrefix(tablePrefix.trim());
		String columnPrefix = properties.getProperty(CONFIG_COLUMN_PREFIX,"");
		globalConfig.setColumnPrefix(columnPrefix.trim());
		String generateMethod = properties.getProperty(CONFIG_GENERATE_METHOD,"");
		globalConfig.setGenerateMethod(generateMethod.trim());
		String baseDir = properties.getProperty(CONFIG_BASE_DIR,"");
		globalConfig.setBaseDir(baseDir.trim());
		String generateControllerDir = properties.getProperty(CONFIG_GENERATE_CONTROLLER_DIR,"");
		globalConfig.setGenerateControllerDir(generateControllerDir.trim());
		String generateServiceDir = properties.getProperty(CONFIG_GENERATE_SERVICE_DIR,"");
		globalConfig.setGenerateServiceDir(generateServiceDir.trim());
		String generateMapperDir = properties.getProperty(CONFIG_GENERATE_MAPPER_DIR,"");
		globalConfig.setGenerateMapperDir(generateMapperDir.trim());
		Map<Class<?>,String> typeConvertRelMap = new HashMap<Class<?>, String>();
		Set<Object> keys = properties.keySet();
		for(Object o : keys) {
			String key = StringUtil.obj2Str(o);
			if(key.startsWith(CONFIG_TYPE_PREFIX)) {
				switch(key) {
					case CONFIG_TYPE_PREFIX + "Integer":
						typeConvertRelMap.put(int.class, properties.getProperty(key, ""));
						typeConvertRelMap.put(Integer.class, properties.getProperty(key, ""));
						break;
					case CONFIG_TYPE_PREFIX + "Short":
						typeConvertRelMap.put(short.class, properties.getProperty(key, ""));
						typeConvertRelMap.put(Short.class, properties.getProperty(key, ""));
						break;
					case CONFIG_TYPE_PREFIX + "BigDecimal":
						typeConvertRelMap.put(BigDecimal.class, properties.getProperty(key, ""));
						break;
					case CONFIG_TYPE_PREFIX + "String":
						typeConvertRelMap.put(String.class, properties.getProperty(key, ""));
						break;
					case CONFIG_TYPE_PREFIX + "Long":
						typeConvertRelMap.put(long.class, properties.getProperty(key, ""));
						typeConvertRelMap.put(Long.class, properties.getProperty(key, ""));
						break;
					case CONFIG_TYPE_PREFIX + "Boolean":
						typeConvertRelMap.put(boolean.class, properties.getProperty(key, ""));
						typeConvertRelMap.put(Boolean.class, properties.getProperty(key, ""));
						break;
					case CONFIG_TYPE_PREFIX + "Date":
						typeConvertRelMap.put(Date.class, properties.getProperty(key, ""));
						break;
					default:
						throw new TemplateException("配置转换类型错误，此类型不支持！");
				}
			}
		}
		globalConfig.setTypeConvertRelMap(typeConvertRelMap);
		
	}
	
	public static void main(String[] args) {
		GlobalConfig config = GlobalConfig.getInstance().init();
		System.out.println(config.getTypeConvertRelMap());
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public String getColumnPrefix() {
		return columnPrefix;
	}

	public void setColumnPrefix(String columnPrefix) {
		this.columnPrefix = columnPrefix;
	}

	public Map<Class<?>, String> getTypeConvertRelMap() {
		return typeConvertRelMap;
	}

	public void setTypeConvertRelMap(Map<Class<?>, String> typeConvertRelMap) {
		this.typeConvertRelMap = typeConvertRelMap;
	}

	public String getGenerateMethod() {
		return generateMethod;
	}

	public void setGenerateMethod(String generateMethod) {
		this.generateMethod = generateMethod;
	}

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public String getGenerateControllerDir() {
		return generateControllerDir;
	}

	public void setGenerateControllerDir(String generateControllerDir) {
		this.generateControllerDir = generateControllerDir;
	}

	public String getGenerateServiceDir() {
		return generateServiceDir;
	}

	public void setGenerateServiceDir(String generateServiceDir) {
		this.generateServiceDir = generateServiceDir;
	}

	public String getGenerateMapperDir() {
		return generateMapperDir;
	}

	public void setGenerateMapperDir(String generateMapperDir) {
		this.generateMapperDir = generateMapperDir;
	}
	
	

}
