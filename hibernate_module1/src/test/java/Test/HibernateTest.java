package Test;

import org.example.entity.User;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest {

    @Test
    public void testAdd(){
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
        user.setUsername("Jon");
        user.setAddress("HongKong");
        user.setPassword("789");
       //调用session的方法实现添加
        session.save(user);

        //6.提交事务
        transaction.commit();

        //7.关闭资源
        session.close();
        sessionFactory.close();
    }
}
