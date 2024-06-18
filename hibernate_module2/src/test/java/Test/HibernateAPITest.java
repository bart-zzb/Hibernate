package Test;

import org.example.entity.User;
import org.example.utils.HibernateUtils;
import org.hibernate.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

//查询三种方法
public class HibernateAPITest {
    //1.使用query对象
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

    //2.使用Criteria对象
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

    //3.使用SQLQuery对象
    @Test
    public void testSQLQuery(){
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSession();
            tx = session.beginTransaction();

            //创建SQLQuery对象
            //参数是普通的sql语句
            SQLQuery sqlQuery = session.createSQLQuery("select * from t_user");
            //返回list集合，默认里面每部分数组结构
//            List<Object[]> list = sqlQuery.list();
//            for (Object[] objects : list) {
//                System.out.println(Arrays.toString(objects));
//            }

            //返回的list中每部分是对象形式
            sqlQuery.addEntity(User.class);
            List<User> list = sqlQuery.list();
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
