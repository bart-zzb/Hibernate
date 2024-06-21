package Test;

import org.example.entity.Customer;
import org.example.entity.LinkMan;
import org.example.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class HibernateSelectTest {

    @Test
    public void testSelect1() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //对象导航查询
            //根据cid=1客户，再查询这个客户有多个联系人
            Customer customer = session.get(Customer.class, 1);
            Set<LinkMan> setLinkMan = customer.getSetLinkMan();
            for (LinkMan linkMan : setLinkMan) {
                System.out.println(linkMan);
            }

            //oid查询
            //Customer customer = session.get(Customer.class, 1);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    //HQL查询所有
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
            Query query = session.createQuery("from Customer");
            //2 调用方法得到结果
            List<Customer> list = query.list();

            for (Customer customer : list) {
                System.out.println(customer.getCid()+"::"+customer.getCustName());
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

    //HQL条件查询
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
            //操作实体类可以帮实体类起别名
            Query query = session.createQuery("from Customer where cid = ? and custName = ?");
            //2 设置条件值
            // int类型是？位置，从0开始
            query.setParameter(0, 1);
            query.setParameter(1, "传智播客");
            List<Customer> list = query.list();

            for (Customer customer : list) {
                System.out.println(customer.getCid()+"::"+customer.getCustName());
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
    //HQL模糊查询
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
            //操作实体类可以帮实体类起别名
            Query query = session.createQuery("from Customer where custName like ?");
            //2 设置条件值
            // int类型是？位置，从0开始
            query.setParameter(0, "传%");
            List<Customer> list = query.list();

            for (Customer customer : list) {
                System.out.println(customer.getCid()+"::"+customer.getCustName());
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
