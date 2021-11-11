## Mybatis Study

>这是咱个人的Mybatis的学习代码库。

### 1. CRUD 增删改查

1. namespace
namespace中的包名要和Dao/Mapper接口的包名一致
2. select
选择，查询语句
+ id:就是对应的namespace中的方法名
+ resultType: sql语句执行的返回值   
+ parameterType: 参数类型
3. insert
4. update
5. delete

#### 步骤
1. 编写接口
2. 编写对应的mapper中的sql语句
3. 测试

> 注意！增删改需要提交事务！
> `sqlSession.commit();`

#### 万能的Map
假设实体类或者数据库中的字段过多，应该考虑使用`Map<String, Object>`。
例如：
```java
public interface UserMapper {
    /**
     * 万能的Map的用法！！！
     * @param map map
     * @return User
     */
    User addUser2(Map<String, Object> map);
}
```
```xml
    <!--  对象中的属性，可以直接取出来
          传递map中的key
     -->
    <insert id="addUser2" parameterType="map">
        insert into mybatis.user (id, name, pwd) value (#{userId}, #{userName}, #{passWord});
    </insert>
```
```java
public class UserMapperTest {
    @Test
    public void addUser2Test() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = new HashMap<>();
        map.put("userId", 8);
        map.put("userName", "xiaoxiao");
        map.put("passWord", "123456");


        mapper.addUser2(map);

        //【增删改需要提交事务！！！】
        sqlSession.commit();
        sqlSession.close();
    }
}
```
> 1.Map传递参数，直接在SQL中取出key即可  `parameterType="map"`
> 
> 2.对象传递参数，直接在SQl中取出对象的属性的值即可！  `parameterType="Object"`
> 
> 3.只有一个基本参数类型的情况下，可以直接在SQL中取到。
> 
> 4.多个参数用Map**或者注解！**

#### 模糊查询使用方法
1. java代码执行的时候，传递通配符 % %
2. 在SQL拼接中使用通配符！ 
```sql
select * from mybatis.user where name like "%"#{value}"%";
```
### 2. 配置解析
#### 1. 核心配置文件
+ mybatis-config.xml
+ configuration（配置）
  + **properties（属性）**
  + **settings（设置）**
  + **typeAliases（类型别名）**
  + typeHandlers（类型处理器）
  + objectFactory（对象工厂）
  + plugins（插件）
  + **environments（环境配置）**
    + environment（环境变量）
      + transactionManager（事务管理器）
      + dataSource（数据源）  用于连接数据库的，如：dbcp,c3p0,druid
  + databaseIdProvider（数据库厂商标识）
  + **mappers（映射器）**

#### 2. 环境配置（environments）
MyBatis 可以配置成适应多种环境

**记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。**
学会配置多套运行环境（environments->environment）
Mybatis默认事务管理器为JDBC，默认数据源为POOLED（连接池）

#### 3. 属性（properties）
我们可以使用properties属性可以引用配置文件

这些属性可以在外部进行配置，并可以进行动态替换。你既可以在典型的 Java 属性文件中配置这些属性，也可以在 properties 元素的子元素中设置
即：**【db.proproperties】**

1. 编写一个配置文件
```properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://xxx.xxx.xxx.xxx:3306/mybatis?useSSL=false&useUnicode=true&amp;characterEncoding=UTF-8&serverTimezone=GMT%2B8
username=mybatis
password=??????????
```
2. 在核心配置文件中引入（`<properties></properties>`应该放在mybatis-config.xml最上面）
```xml
    <!--  引入外部配置文件  -->
    <properties resource="db.properties">
        <property name="username" value="mybatis"/>
        <property name="password" value="1111111"/>
    </properties>
```
+ 可以直接引入外部配置文件
+ 可以在其中增加一些属性配置
+ 如果两个字段有相同配置的，**优先使用外部配置文件的**！！！
> 【官网原话】
> 
> 1.首先读取在 properties 元素体内指定的属性。
> 
> 2.然后根据 properties 元素中的 resource 属性读取类路径下属性文件，或根据 url 属性指定的路径读取属性文件，并覆盖之前读取过的同名属性。
> 
> 3.最后读取作为方法参数传递的属性，并覆盖之前读取过的同名属性。
