package Test;

import org.example.entity.Customer;
import org.example.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

public class HibernateHQLMultiTableSelectTest {
    //HQL内连接查询, 返回数据是数组的集合
    @Test
    public void testSelect1() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 创建query对象
            Query query = session.createQuery("from Customer c inner join c.setLinkMan");

            List list = query.list();

            for (Object object : list) {
                System.out.println(object);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    //HQL迫切内连接查询, 返回的是对象集合
    @Test
    public void testSelect2() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 创建query对象
            Query query = session.createQuery("from Customer c inner join fetch c.setLinkMan");
            List<Customer> list = query.list();

            for (Customer object : list) {
                System.out.println(object.getCid()+object.getCustName()+object.getCustPhone()+object.getSetLinkMan());
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    //HQL左外连接查询, 返回的是数组集合
    @Test
    public void testSelect3() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 创建query对象
            Query query = session.createQuery("from Customer c left outer join c.setLinkMan");
            List list = query.list();

            for (Object object : list) {
                System.out.println(object);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    //HQL迫切左外连接查询, 返回的是对象集合, 注意没有迫切右外连接
    @Test
    public void testSelect4() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 创建query对象
            Query query = session.createQuery("from Customer c left outer join fetch c.setLinkMan");
            List<Customer> list = query.list();

            for (Customer object : list) {
                System.out.println(object.getCid()+object.getCustName()+object.getCustPhone()+object.getSetLinkMan());
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
