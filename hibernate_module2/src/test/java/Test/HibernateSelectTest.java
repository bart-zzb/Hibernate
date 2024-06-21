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

    //HQL排序查询
    @Test
    public void testSelect5() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 创建query对象
            //操作实体类可以帮实体类起别名
            Query query = session.createQuery("from Customer order by cid desc");
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

    //HQL分页查询
    @Test
    public void testSelect6() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 创建query对象
            //操作实体类可以帮实体类起别名
            //hql操作中，不能使用limit，因为它是mysql方言，oracle不用limit
            Query query = session.createQuery("from Customer");

            //2.1 设置分页数据, 设置开始的值 -> 相当于limit 0,3
            query.setFirstResult(0);
            //2.2 查询多少条数据
            query.setMaxResults(3);

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

    //HQL投影查询（即查询某些字段值）
    @Test
    public void testSelect7() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 创建query对象
            //操作实体类可以帮实体类起别名
            Query query = session.createQuery("select custName from Customer");

            List<Object> list = query.list();

            for (Object object : list) {
                System.out.println(object);;
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

    //HQL聚合函数查询
    @Test
    public void testSelect8() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 创建query对象
            //操作实体类可以帮实体类起别名
            //可以使用count(*),max(实体类属性),avg(实体类属性)...
            Query query = session.createQuery("select count(*) from Customer");

            Object object = query.uniqueResult();
            //默认是long类型
            long count = (Long) object;

            System.out.println("表中一共有"+count+"条数据");

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
