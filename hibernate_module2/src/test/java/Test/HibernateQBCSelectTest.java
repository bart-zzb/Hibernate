package Test;

import org.example.entity.Customer;
import org.example.entity.LinkMan;
import org.example.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class HibernateQBCSelectTest {
    @Test
    public void testSelect1() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 创建对象
            Criteria criteria = session.createCriteria(Customer.class);
            //2 调用方法得到结果
            List<Customer> list = criteria.list();
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

    //QBC条件查询
    @Test
    public void testSelect2() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 创建对象
            Criteria criteria = session.createCriteria(Customer.class);

            //2 首先使用add方法, 表示设置条件值
            //类似于cid=?
//            criteria.add(Restrictions.eq("cid",2));
//            criteria.add(Restrictions.eq("custName", "google"));

            criteria.add(Restrictions.like("custName","go%"));

            //3 调用方法得到结果
            List<Customer> list = criteria.list();
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
