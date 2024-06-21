package Test;

import org.example.entity.Customer;
import org.example.entity.LinkMan;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Set;

public class HibernateSelectTest {

    @Test
    public void testSelect() {
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
}
