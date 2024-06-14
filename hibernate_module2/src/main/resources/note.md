# Hibernate核心api
1. Configuration

```java
Configuration cfg = new Configuration().configure();
cfg.configure();
```

2. SessionFactory(重点)
- 使用configuration对象创建sessionFactory对象
  - 根据核心配置文件中，到数据库配置，有映射文件部分，到数据库里面根据映射关系把表创建好
- 创建sessionFactory过程中，这个过程特别消耗资源的
  - 在hibernate操作中，建议一个项目一般创建一个sessionFactory对象
- 具体shixian
  - 写工具类，写静态代码块

3. Session(重点) 
- 类似JDBC中connection
- 调用session里面不同的方法实现CRUD操作
  - 添加save方法
  - 修改update方法
  - 删除delete方法
  - 根据id查询get方法
- session对象单线程对象
  - session对象不能共用，只能自己使用

4. Transaction
- 开启事务
- 提交事务
- 回滚事务
- ACID

5. 注意事项
- 实体类属性建议不使用基本数据类型，使用其包装类型，因为包装类可以使用null
- hibernate 主键两大常用变量值：native和uuid