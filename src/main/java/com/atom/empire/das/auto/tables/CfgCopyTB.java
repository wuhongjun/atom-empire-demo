
package com.atom.empire.das.auto.tables;

import org.apache.empire.data.DataType;
import org.apache.empire.db.DBTableColumn;
import com.atom.empire.das.auto.OSiteDB;

public class CfgCopyTB extends OSiteTable {

	private static final long serialVersionUID = 1L;
	
	// define columns
	public final DBTableColumn CATG;
	public final DBTableColumn NAME;
	public final DBTableColumn TITLE;
	public final DBTableColumn VALUE;
	public final DBTableColumn VALUE_EXT;
	public final DBTableColumn NEW_TIME;
	public final DBTableColumn UPD_TIME;
	
	public CfgCopyTB(OSiteDB db) {
		super("jee_cfg_copy", db);
		
		// create columns
		CATG = addColumn("catg", DataType.TEXT, 64.0, true, "");
		NAME = addColumn("name", DataType.TEXT, 64.0, true, "");
		TITLE = addColumn("title", DataType.TEXT, 128.0, false, null);
		VALUE = addColumn("value", DataType.TEXT, 1024.0, false, null);
		VALUE_EXT = addColumn("value_ext", DataType.CLOB, 65535.0, false, null);
		NEW_TIME = addColumn("new_time", DataType.TEXT, 32.0, false, null);
		UPD_TIME = addColumn("upd_time", DataType.TEXT, 32.0, false, null);

		// configure key columns (primary key)
		DBTableColumn[] keyColumns = new DBTableColumn[] {
			CATG,
			NAME };
		setPrimaryKey(keyColumns);

		// Optimistic locking column
		/*no locking column specified*/
	}
}
