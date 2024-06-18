package Test;

import org.example.entity.User;
import org.example.utils.HibernateUtils;
import org.hibernate.*;
import org.junit.Test;

import java.util.List;

public class HibernateAPITest {
    //使用query对象
    @Test
    public void testQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSession();
            tx = session.beginTransaction();

            //创建Query对象
            //方法里面写hql
            //from + 实体类对象
            Query query = session.createQuery("from User");
            List<User> list = query.list();
            for (User user : list) {
                System.out.println(user);
            }

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            session.close();
            sessionFactory.close();
        }
    }

    //使用Criteria对象
    @Test
    public void testCriteria(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSession();
            tx = session.beginTransaction();

            //创建Criteria对象
            Criteria criteria = session.createCriteria(User.class);
            List<User> list = criteria.list();
            for (User user : list) {
                System.out.println(user);
            }

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            session.close();
            sessionFactory.close();
        }
    }
}
