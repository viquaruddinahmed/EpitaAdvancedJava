package fr.epita.IamViquarCore.test;

import java.util.Date;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.IamViquarCore.datamodel.Identity;




// integrate spring and junit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestHibernateIntegration {

	@Inject
	private SessionFactory sessionFactory;

	@Test
	public void testFirstIntegration() {
		final Identity identity = new Identity("viquar", "viquar10@gmail.com", new Date());


		final Session session = sessionFactory.openSession();
		final Transaction tx = session.beginTransaction();
		session.save(identity);
		tx.commit();

	}

}
