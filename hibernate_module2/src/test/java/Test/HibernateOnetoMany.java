package Test;

import org.example.entity.Customer;
import org.example.entity.LinkMan;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;



public class HibernateOnetoMany {


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


