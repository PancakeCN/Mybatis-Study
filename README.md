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
3. 