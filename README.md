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

####步骤
1. 编写接口
2. 编写对应的mapper中的sql语句
3. 测试

> 注意！增删改需要提交事务！
> `sqlSession.commit();`