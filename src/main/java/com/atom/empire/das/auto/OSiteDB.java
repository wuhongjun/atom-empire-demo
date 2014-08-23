
package com.atom.empire.das.auto;

import org.apache.empire.db.DBDatabase;

import com.atom.empire.das.auto.tables.*;
// com.atom.empire.das.auto.tables vs com.atom.empire.das.auto 
// import com.atom.empire.das.auto.views.*;

public class OSiteDB extends DBDatabase {

	private static OSiteDB instance;
	private static final long serialVersionUID = 1L;

	public final CfgTB CFG = new CfgTB(this);
	public final CfgCopyTB CFG_COPY = new CfgCopyTB(this);
	public final CtgTB CTG = new CtgTB(this);
	public final CtgCopyTB CTG_COPY = new CtgCopyTB(this);

	
	/**
	 * Init
	 */
	public void init() {
	    instance = this;
	}
	
	/**
	 * Returns the instance of the database.
	 */
	public static OSiteDB get() {
		// if (instance == null) {
		// 	instance = new OSiteDB();
		// }
		return instance;
	}
	
	/**
	 * Default constructor for the OSiteDB.
	 */
	private OSiteDB() {

		// Define foreign key relations
		// foreign key relations done
	}	
	
	
		
}
