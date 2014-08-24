package com.atom.empire.das.osite.auto;

import org.apache.empire.data.DataType;
// import org.apache.empire.db.DBCommandExpr;
import org.apache.empire.db.DBDatabase;
import org.apache.empire.db.DBTableColumn;
// import org.apache.empire.exceptions.NotImplementedException;


public class OSiteDB extends DBDatabase {

	private static OSiteDB instance;
	private static final long serialVersionUID = 1L;

	public final T_Cfg T_CFG = new T_Cfg(this);
	public final T_Ctg T_CTG = new T_Ctg(this);

	
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
	
						

  public static class T_Cfg extends OSiteTable {

	private static final long serialVersionUID = 1L;
	
	// define columns
	public final DBTableColumn C_CATG;
	public final DBTableColumn C_NAME;
	public final DBTableColumn C_TITLE;
	public final DBTableColumn C_VALUE;
	public final DBTableColumn C_VALUE_EXT;
	public final DBTableColumn C_NEW_TIME;
	public final DBTableColumn C_UPD_TIME;
	
	public T_Cfg(OSiteDB db) {
		super("jee_cfg", db);
		
		// create columns
		C_CATG = addColumn("catg", DataType.TEXT, 64.0, true, null);
		C_NAME = addColumn("name", DataType.TEXT, 64.0, true, null);
		C_TITLE = addColumn("title", DataType.TEXT, 128.0, false, null);
		C_VALUE = addColumn("value", DataType.TEXT, 1024.0, false, null);
		C_VALUE_EXT = addColumn("value_ext", DataType.CLOB, 2.147483647E9, false, null);
		C_NEW_TIME = addColumn("new_time", DataType.TEXT, 32.0, false, null);
		C_UPD_TIME = addColumn("upd_time", DataType.TEXT, 32.0, false, null);

		// configure key columns (primary key)
		DBTableColumn[] keyColumns = new DBTableColumn[] {
			C_CATG,
			C_NAME };
		setPrimaryKey(keyColumns);

		// Optimistic locking column
		/*no locking column specified*/
	}
  }
					

  public static class T_Ctg extends OSiteTable {

	private static final long serialVersionUID = 1L;
	
	// define columns
	public final DBTableColumn C_NO;
	public final DBTableColumn C_CTG;
	public final DBTableColumn C_FLAG;
	public final DBTableColumn C_SORT;
	public final DBTableColumn C_TITLE;
	public final DBTableColumn C_EXT;
	public final DBTableColumn C_NEW_TIME;
	public final DBTableColumn C_UPD_TIME;
	
	public T_Ctg(OSiteDB db) {
		super("jee_ctg", db);
		
		// create columns
		C_NO = addColumn("no", DataType.TEXT, 64.0, true, null);
		C_CTG = addColumn("ctg", DataType.TEXT, 64.0, false, null);
		C_FLAG = addColumn("flag", DataType.TEXT, 128.0, false, null);
		C_SORT = addColumn("sort", DataType.TEXT, 16.0, false, null);
		C_TITLE = addColumn("title", DataType.TEXT, 256.0, false, null);
		C_EXT = addColumn("ext", DataType.TEXT, 1024.0, false, null);
		C_NEW_TIME = addColumn("new_time", DataType.TEXT, 32.0, false, null);
		C_UPD_TIME = addColumn("upd_time", DataType.TEXT, 32.0, false, null);

		// configure key columns (primary key)
		setPrimaryKey(C_NO);

		// Optimistic locking column
		/*no locking column specified*/
	}
  }
			
					
}
