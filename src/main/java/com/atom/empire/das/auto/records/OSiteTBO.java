
package com.atom.empire.das.auto.records;

import org.apache.empire.db.DBRecord;
import com.atom.empire.das.auto.tables.OSiteTable;


public abstract class OSiteTBO<T extends OSiteTable> extends DBRecord {

	private static final long serialVersionUID = 1L;

	public OSiteTBO(T table) {
		super(table);
	}

	/**
	 * Returns the table this record is based upon.
	 * @return The table this record is based upon.
	 */
	@SuppressWarnings("unchecked")
	public T getTable() {
		return (T)super.getRowSet();
	}
}