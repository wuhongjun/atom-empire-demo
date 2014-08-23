
package com.atom.empire.das.auto.tables;

import org.apache.empire.data.DataType;
import org.apache.empire.db.DBTableColumn;
import com.atom.empire.das.auto.OSiteDB;

public class CtgCopyTB extends OSiteTable {

	private static final long serialVersionUID = 1L;
	
	// define columns
	public final DBTableColumn NO;
	public final DBTableColumn CTG;
	public final DBTableColumn FLAG;
	public final DBTableColumn SORT;
	public final DBTableColumn TITLE;
	public final DBTableColumn EXT;
	public final DBTableColumn NEW_TIME;
	public final DBTableColumn UPD_TIME;
	
	public CtgCopyTB(OSiteDB db) {
		super("jee_ctg_copy", db);
		
		// create columns
		NO = addColumn("no", DataType.TEXT, 64.0, true, "");
		CTG = addColumn("ctg", DataType.TEXT, 64.0, false, null);
		FLAG = addColumn("flag", DataType.TEXT, 128.0, false, null);
		SORT = addColumn("sort", DataType.TEXT, 16.0, false, null);
		TITLE = addColumn("title", DataType.TEXT, 256.0, false, null);
		EXT = addColumn("ext", DataType.TEXT, 1024.0, false, null);
		NEW_TIME = addColumn("new_time", DataType.TEXT, 32.0, false, null);
		UPD_TIME = addColumn("upd_time", DataType.TEXT, 32.0, false, null);

		// configure key columns (primary key)
		setPrimaryKey(NO);

		// Optimistic locking column
		/*no locking column specified*/
	}
}
