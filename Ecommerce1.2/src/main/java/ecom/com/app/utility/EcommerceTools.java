package ecom.com.app.utility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ecom.com.app.model.Dept;
import ecom.com.app.model.Store;



public class EcommerceTools {
	
	private static final Logger logger = LoggerFactory.getLogger(EcommerceTools.class);
	
	public static String getGeneratedId(String sequenceName, Connection connection, String prefix) throws SQLException {
		String code = null;
		String query = "call incrementSequenceValue(?,?);";
		logger.info("query=" + query);
		CallableStatement cStmt = connection.prepareCall(query);
		cStmt.setString(1, sequenceName);
		cStmt.registerOutParameter(2, java.sql.Types.INTEGER);
		cStmt.executeUpdate();
		int id = cStmt.getInt(2);
		code = prefix + id;
		logger.info("sequence generated=" + code);
		return code;
	}
	
	public static boolean isBlank(String input) {
		return input == null || input.trim().isEmpty();
	}


}
