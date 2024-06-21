package Test;

import org.example.entity.Permission;
import org.example.entity.Role;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateManyToMany {
    //演示多对多级联保存
    @Test
    public void testSave() {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            //得到sessionFactory
            sessionFactory = HibernateUtils.getSessionFactory();
            //得到session
            session = sessionFactory.openSession();
            //开启事务
            tx = session.beginTransaction();

            Role role1 = new Role();
            role1.setName("User");

            Role role2 = new Role();
            role2.setName("Admin");

            Permission permission1 = new Permission();
            permission1.setName("Select");

            Permission permission2 = new Permission();
            permission2.setName("Insert");

            Permission permission3 = new Permission();
            permission3.setName("Delete");

            role1.getPermissions().add(permission1);
            role2.getPermissions().add(permission1);
            role2.getPermissions().add(permission2);
            role2.getPermissions().add(permission3);

            session.save(role1);
            session.save(role2);

            //提交事务
            tx.commit();

        }catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
            //sessionFactory不需要关闭
            sessionFactory.close();
        }
    }
}
