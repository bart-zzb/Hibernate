package Test;

import org.example.entity.Customer;
import org.example.entity.LinkMan;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;



public class HibernateOnetoMany {
	//演示一对多修改
	@Test
	public void testUpdate() {
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

			//1 根据id查询lucy联系人，根据id查询百度的客户
			Customer baidu = session.get(Customer.class, 1);
			LinkMan lucy = session.get(LinkMan.class, 2);
			//2 设置持久态对象值
			//把联系人放到客户里面
			baidu.getSetLinkMan().add(lucy);
			//把客户放到联系人里面
			lucy.setCustomer(baidu);

			//提交事务
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory不需要关闭
			sessionFactory.close();
		}
	}

	//演示一对多级联删除
	@Test
	public void testDeleteDemo() {
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

			// 1 根据id查询客户对象
			Customer customer = session.get(Customer.class, 1);
			//2 调用方法删除
			session.delete(customer);

			//提交事务
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory不需要关闭
			sessionFactory.close();
		}
	}

	//演示一对多级联保存
	@Test
	public void testAddDemo2() {
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

			// 添加一个客户，为这个客户添加一个联系人
			//1 创建客户和联系人对象
			Customer customer = new Customer();
			customer.setCustName("百度");
			customer.setCustLevel("普通客户");
			customer.setCustSource("网络");
			customer.setCustPhone("110");
			customer.setCustMobile("999");

			LinkMan linkman = new LinkMan();
			linkman.setLkm_name("小宏");
			linkman.setLkm_gender("男");
			linkman.setLkm_phone("911");

			//2 把联系人放到客户里面
			//需要在customer.hbm.xml配置cascade="save-update"
			customer.getSetLinkMan().add(linkman);

			//3 保存客户
			session.save(customer);

			//提交事务
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory不需要关闭
			sessionFactory.close();
		}
	}

	//演示一对多级联保存
	@Test
	public void testAddDemo1() {
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
			
			// 添加一个客户，为这个客户添加一个联系人
			//1 创建客户和联系人对象
			Customer customer = new Customer();
			customer.setCustName("传智播客");
			customer.setCustLevel("vip");
			customer.setCustSource("网络");
			customer.setCustPhone("110");
			customer.setCustMobile("999");
			
			LinkMan linkman = new LinkMan();
			linkman.setLkm_name("lucy");
			linkman.setLkm_gender("男");
			linkman.setLkm_phone("911");
			
			//2 在客户表示所有联系人，在联系人表示客户		
			// 建立客户对象和联系人对象关系
			//2.1 把联系人对象 放到客户对象的set集合里面
			customer.getSetLinkMan().add(linkman);
			//2.2 把客户对象放到联系人里面
			linkman.setCustomer(customer);
			
			//3 保存到数据库
			session.save(customer);
			session.save(linkman);
			
			//提交事务
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			//session.close();
			//sessionFactory不需要关闭
			//sessionFactory.close();
		}
	}
}


