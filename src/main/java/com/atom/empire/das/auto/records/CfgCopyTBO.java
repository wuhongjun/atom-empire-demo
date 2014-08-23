
package com.atom.empire.das.auto.records;



import com.atom.empire.das.auto.OSiteDB;
import com.atom.empire.das.auto.records.OSiteTBO;
import com.atom.empire.das.auto.tables.CfgCopyTB;

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
public class CfgCopyTBO extends OSiteTBO<CfgCopyTB> {

	private static final long serialVersionUID = 1L;

	public CfgCopyTBO(OSiteDB db) {
		super(db.CFG_COPY);
		// super(OSiteDB.get().CFG_COPY);
	}
	
	// Access methods for all columns

	public java.lang.String getCatg() {
		return (java.lang.String)super.getValue(getTable().CATG);
	}
	public void setCatg(java.lang.String val) {
		super.setValue(getTable().CATG, val);
	}

	public java.lang.String getName() {
		return (java.lang.String)super.getValue(getTable().NAME);
	}
	public void setName(java.lang.String val) {
		super.setValue(getTable().NAME, val);
	}

	public java.lang.String getTitle() {
		return (java.lang.String)super.getValue(getTable().TITLE);
	}
	public void setTitle(java.lang.String val) {
		super.setValue(getTable().TITLE, val);
	}

	public java.lang.String getValueColumn() {
		return (java.lang.String)super.getValue(getTable().VALUE);
	}
	public void setValueColumn(java.lang.String val) {
		super.setValue(getTable().VALUE, val);
	}

	public java.lang.String getValueExt() {
		return (java.lang.String)super.getValue(getTable().VALUE_EXT);
	}
	public void setValueExt(java.lang.String val) {
		super.setValue(getTable().VALUE_EXT, val);
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