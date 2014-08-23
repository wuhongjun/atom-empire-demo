
package com.atom.empire.das.auto.records;



import com.atom.empire.das.auto.OSiteDB;
import com.atom.empire.das.auto.records.OSiteTBO;
import com.atom.empire.das.auto.tables.CtgTB;

/**
 * Auto-generated class that represents one record (or row) of data from a
 * database table.  One of these is generated for each table or view in the
 * database.  The interface defines getters for auto-generated data (e.g. 
 * primary key, time stamp field for optimistic locking).  It generates both
 * getter and setter method for all other columns in the table, with the 
 * exception of foreign key references.
 *
 * This class provides protected method that subclasses should use to provide
 * access to related records.
 */
public class CtgTBO extends OSiteTBO<CtgTB> {

	private static final long serialVersionUID = 1L;

	public CtgTBO(OSiteDB db) {
		super(db.CTG);
		// super(OSiteDB.get().CTG);
	}
	
	// Access methods for all columns

	public java.lang.String getNo() {
		return (java.lang.String)super.getValue(getTable().NO);
	}
	public void setNo(java.lang.String val) {
		super.setValue(getTable().NO, val);
	}

	public java.lang.String getCtg() {
		return (java.lang.String)super.getValue(getTable().CTG);
	}
	public void setCtg(java.lang.String val) {
		super.setValue(getTable().CTG, val);
	}

	public java.lang.String getFlag() {
		return (java.lang.String)super.getValue(getTable().FLAG);
	}
	public void setFlag(java.lang.String val) {
		super.setValue(getTable().FLAG, val);
	}

	public java.lang.String getSort() {
		return (java.lang.String)super.getValue(getTable().SORT);
	}
	public void setSort(java.lang.String val) {
		super.setValue(getTable().SORT, val);
	}

	public java.lang.String getTitle() {
		return (java.lang.String)super.getValue(getTable().TITLE);
	}
	public void setTitle(java.lang.String val) {
		super.setValue(getTable().TITLE, val);
	}

	public java.lang.String getExt() {
		return (java.lang.String)super.getValue(getTable().EXT);
	}
	public void setExt(java.lang.String val) {
		super.setValue(getTable().EXT, val);
	}

	public java.lang.String getNewTime() {
		return (java.lang.String)super.getValue(getTable().NEW_TIME);
	}
	public void setNewTime(java.lang.String val) {
		super.setValue(getTable().NEW_TIME, val);
	}

	public java.lang.String getUpdTime() {
		return (java.lang.String)super.getValue(getTable().UPD_TIME);
	}
	public void setUpdTime(java.lang.String val) {
		super.setValue(getTable().UPD_TIME, val);
	}


}