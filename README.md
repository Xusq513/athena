君子生非异也，善假于物也。人类在进化的过程中学会了使用工具，并且不断的对生产工具进行改良，所以今天才能够站立在食物链的顶端。<br>
作为程序员，我们也应该时刻有这样的觉悟，能够避免重复琐碎冗余的工作，提升工作效率以及开发质量。<br>
Athena是雅典娜，本项目以智慧女神为名，希望能成为一个优秀的开源项目。<br>
本项目的整体架构是前后端分离的，后端主要采用SpringBoot+Mybatis+Mysql+Redis(后期也会不断的进行扩充),前端主要采用Vue+IView。<br>
后端工程要求的环境有：jdk1.8，maven，idea(eclipse也可以，推荐idea),热部署插件JRebel,lombok插件<br>
前端工程要求的环境有：node.js 8以上版本，IDE推荐XBuilder<br>
常用的工具:Git,PostMan,Navicat<br>
本项目第一阶段要实现的目标是：通过完成一个JavaBean，能够直接生成一个模块。JavaBean如下所示，可以看到注解内容比较多，其中包含一些字段的
中文释义，字段的数据库类型以及前端显示的控件以及字段的校验等内容，没错这样的一个JavaBean设计完成之后，我们不需要做什么，只需要按照这些元注解
信息进行Controller,Service,Mapper,数据库表,前端的内容以及模块的配置等都已经完成，对于开发人员来说，只需要设计一个JavaBean全部搞定。<br>
```
@Title("学生信息")
public class Student extends BaseBean implements Serializable{
	
	@Ignore
	private static final long serialVersionUID = 4040484417229617858L;

	@Title("姓名")
	@Validate(required=true,length=30)
	private String name;
	
	@Title("年龄")
	@Validate(required=true,maxNum=200,minNum=1)
	private Integer age;
	
	@Title("性别")
	@Select(type="性别")
	private String gender;
	
	@Title("生日")
	@Column(columnType="DATE")
	@Calender(javaFormatter="yyyy-MM-dd")
	@Validate(required=true,startTime="1900-01-01 00:00:00",endTime="now")
	private Date birthDay;
	
	@Title("住址")
	@Column(columnType="VARCHAR(200)")
	private String address;
	
	@Title("国家")
	private String country;
	
	@Title("爱好")
	@Column(columnType="VARCHAR(200)")
}
```
如果没有前端经验的开发人员来说，可以只关注后端的工程。如果有前端的大神，请直接联系我。在开发设计过程中，我可能会遗留许多滞后或者待完善的工作(也可能是Bug)，这些地方我一般会打标记
为“TODO”，所以大家可以直接搜索工程中带TODO的地方可以直接进行完善，另外本人水平也很有限，工程中如果有可以优化或者有问题的地方，或者是大家有
好的点子，欢迎提出，在issue里提出就可以了。另外如果第一阶段完毕或者收尾的时候，我也会提出第二阶段做的一些想法。希望大家可以参与进来。
另外本人邮箱121489995@qq.com,有事可以发邮件或者QQ联系。
