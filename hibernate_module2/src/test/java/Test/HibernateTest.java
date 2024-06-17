package Test;

import org.example.entity.User;
import org.example.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest {

    @Test
    public void testAdd() {
        //1.加载hibernate核心配置文件
        // 到src下面找到名称是hibernate.cfg.xml
        //在hibernate里面封装对象
//        Configuration cfg = new Configuration().configure();

        //2.创建SessionFactory对象
        // 读取hibernate核心配置文件内容，创建sessionFactory
        // 在过程中，根据映射关系，在配置数据库里面把表创建出来
//        SessionFactory sessionFactory = cfg.buildSessionFactory();

        //使用HibernateUtils工具类，让其一个项目只创建一次即可，节省资源
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        //3.使用SessionFactory创建session对象
        // 类似于JDBC连接
        Session session = sessionFactory.openSession();

        //4.开启事务
        Transaction transaction = session.beginTransaction();

        //5.具体的CRUD操作
        User user = new User();
        user.setUsername("Stephen");
        user.setAddress("HongKong");
        user.setPassword("123456");
        //调用session的方法实现添加
        session.save(user);

        //6.提交事务
        transaction.commit();

        //7.关闭资源
        session.close();
        sessionFactory.close();
    }

    //根据id查询数据
    @Test
    public void testGet() {
        //1.调用工具类得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        //2.获取session
        Session session = sessionFactory.openSession();

        //3.开启事务
        Transaction transaction = session.beginTransaction();

        //4.根据id查询
        //调用session里面的get的方法
        //第一个参数：实体类的class
        //第二个参数：id值
        User user = session.get(User.class, 2);
        System.out.println(user);

        //5.提交事务
        transaction.commit();

        //6.关闭
        session.close();
        sessionFactory.close();
    }

    //修改数据
    @Test
    public void testUpdate() {
        //1.调用工具类得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        //2.获取session
        Session session = sessionFactory.openSession();

        //3.开启事务
        Transaction transaction = session.beginTransaction();

        //4.根据id查询
        //调用session里面的get的方法
        //第一个参数：实体类的class
        //第二个参数：id值
        User user = session.get(User.class, 1);
        user.setUsername("TonyChan");
        //调用session.update的方法
        session.update(user);

        //5.提交事务
        transaction.commit();

        //6.关闭
        session.close();
        sessionFactory.close();
    }

    //删除数据
    @Test
    public void testDelete() {
        //1.调用工具类得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        //2.获取session
        Session session = sessionFactory.openSession();

        //3.开启事务
        Transaction transaction = session.beginTransaction();

        //4.删除操作
        //第一种
        User user = session.get(User.class, 1);
        //调用session.delete的方法
        session.delete(user);

        //第二种
//        User user = new User();
//        user.setUid(1);
//        session.delete(user);


        //5.提交事务
        transaction.commit();

        //6.关闭
        session.close();
        sessionFactory.close();
    }

    //新增或者修改数据
    @Test
    public void testSaveOrUpdate() {
        //1.调用工具类得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        //2.获取session
        Session session = sessionFactory.openSession();

        //3.开启事务
        Transaction transaction = session.beginTransaction();

        //4.新增或者修改操作（最核心区分就是看有没有设置uid）
        User user = new User();
        user.setUsername("Stephen");
        //新增
        session.saveOrUpdate(user);

        //修改
//        user.setUid(1);
//        session.saveOrUpdate(user);

        //5.提交事务
        transaction.commit();

        //6.关闭
        session.close();
        sessionFactory.close();
    }

    //测试Hibernate一级缓存
    @Test
    public void testCahche() {
        //1.调用工具类得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        //2.获取session
        Session session = sessionFactory.openSession();

        //3.开启事务
        Transaction transaction = session.beginTransaction();

        //4.根据id查询第一次，控制台有查询sql输出
        User user1 = session.get(User.class, 2);
        //5.根据id查询第二次，控制台没有输出
        User user2 = session.get(User.class, 2);

        //6.提交事务
        transaction.commit();

        //7.关闭
        session.close();
        sessionFactory.close();
    }

    //一级缓存特性，持久态的对象会自动更新数据库
    @Test
    public void testAutoUpdate() {
        //1.调用工具类得到sessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        //2.获取session
        Session session = sessionFactory.openSession();

        //3.开启事务
        Transaction transaction = session.beginTransaction();

        //4.根据id查询，生成一级缓存和快照区（副本）
        User user = session.get(User.class, 2);

        //5.修改user，同时修改hibernate的一级缓存的对象，但不会更新到快照区
        user.setUsername("Stephen Zhow");

        //6.不用写提交
        //session.update(user);

        //7.提交事务，会比较一级缓存中对象与快照区中是否一致，不一致，就把一级缓存更新到数据库，控制台有update的sql
        transaction.commit();

        //8.关闭
        session.close();
        sessionFactory.close();
    }

    //事务规范代码,try catch (rollback()) finally
    @Test
    public void testTx() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try {
            //1.调用工具类得到sessionFactory
             sessionFactory = HibernateUtils.getSessionFactory();

            //2.获取session
             session = sessionFactory.openSession();

            //3.开启事务
             transaction = session.beginTransaction();

            //4.根据id查询，生成一级缓存和快照区（副本）
            User user = session.get(User.class, 2);

            //5.修改user，同时修改hibernate的一级缓存的对象，但不会更新到快照区
            user.setUsername("Stephen Zhow");

            //6.不用写提交
            //session.update(user);

            //7.提交事务，会比较一级缓存中对象与快照区中是否一致，不一致，就把一级缓存更新到数据库，控制台有update的sql
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            //8.关闭
            session.close();
            sessionFactory.close();
        }
    }
}
