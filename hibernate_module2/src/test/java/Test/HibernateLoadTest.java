package Test;

import org.example.entity.Customer;
import org.example.entity.LinkMan;
import org.example.entity.User;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Set;

public class HibernateLoadTest {
    //立即查询与延迟查询
    @Test
    public void testLoad() {
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

            //4.根据id查询，get方法立即查询
            //User getUser = session.get(User.class, 2);
            //延迟查询有两个：类级别的延迟查询, 关联级别查询
            User loadUser = session.load(User.class, 2);

            //5.输出
            //TODO 需要断点debug看效果，暂时有bug
            //System.out.println(getUser.getUid());
            System.out.println(loadUser.getUid());
            System.out.println(loadUser.getPassword());

            //6.不用写提交
            //session.update(user);

            //7.提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            //8.关闭
            session.close();
            sessionFactory.close();
        }
    }

    //默认关联级别查询
    @Test
    public void testGet() {
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

            //4.根据id查询
            Customer customer = session.get(Customer.class, 1);

            //5.输出, 默认是延迟查询
            Set<LinkMan> setLinkMan = customer.getSetLinkMan();
            System.out.println(setLinkMan);

            //6.不用写提交
            //session.update(user);

            //7.提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            //8.关闭
            session.close();
            sessionFactory.close();
        }
    }
}
