package ecom.com.app.idgenerator;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.SequenceGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ecom.com.app.utility.EcommerceTools;



public class IdGenerator extends SequenceGenerator {

	private static final Logger logger = LoggerFactory.getLogger(IdGenerator.class);

	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		String sequenceName = super.getSequenceName();
		String generatedId = null;
		String prefix = null;
		logger.info("sequencename=" + sequenceName);
	          if(sequenceName.contains("customer")) prefix = "customer";
	          if(sequenceName.contains("department")) prefix = "dept";
	          if(sequenceName.contains("store")) prefix = "store";
	          if(sequenceName.contains("prod")) prefix = "p";
	          if(sequenceName.contains("order")) prefix = "o";
			try {
				generatedId = EcommerceTools.getGeneratedId(sequenceName, session.connection(), prefix);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new HibernateException("Unable to generate GiftRegistryIdGenerator sequence");
			}
		
		return generatedId;
	}
}
