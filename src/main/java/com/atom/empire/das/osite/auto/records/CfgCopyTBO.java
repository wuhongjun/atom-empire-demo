
package com.atom.empire.das.osite.auto.records;



import com.atom.empire.das.osite.auto.OSiteDB;
import com.atom.empire.das.osite.auto.records.OSiteTBO;
import com.atom.empire.das.osite.auto.OSiteDB.T_CfgCopy;

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
public class CfgCopyTBO extends OSiteTBO<T_CfgCopy> {

	private static final long serialVersionUID = 1L;

	public CfgCopyTBO(OSiteDB db) {
		super(db.T_CFG_COPY);
		// super(OSiteDB.get().T_CFG_COPY);
	}
	
	// Access methods for all columns

	public java.lang.String getCatg() {
		return (java.lang.String)super.getValue(getTable().C_CATG);
	}
	public void setCatg(java.lang.String val) {
		super.setValue(getTable().C_CATG, val);
	}

	public java.lang.String getName() {
		return (java.lang.String)super.getValue(getTable().C_NAME);
	}
	public void setName(java.lang.String val) {
		super.setValue(getTable().C_NAME, val);
	}

	public java.lang.String getTitle() {
		return (java.lang.String)super.getValue(getTable().C_TITLE);
	}
	public void setTitle(java.lang.String val) {
		super.setValue(getTable().C_TITLE, val);
	}

	public java.lang.String getValue_() {
		return (java.lang.String)super.getValue(getTable().C_VALUE);
	}
	public void setValue_(java.lang.String val) {
		super.setValue(getTable().C_VALUE, val);
	}

	public java.lang.String getValueExt() {
		return (java.lang.String)super.getValue(getTable().C_VALUE_EXT);
	}
	public void setValueExt(java.lang.String val) {
		super.setValue(getTable().C_VALUE_EXT, val);
	}

	public java.lang.String getNewTime() {
		return (java.lang.String)super.getValue(getTable().C_NEW_TIME);
	}
	public void setNewTime(java.lang.String val) {
		super.setValue(getTable().C_NEW_TIME, val);
	}

	public java.lang.String getUpdTime() {
		return (java.lang.String)super.getValue(getTable().C_UPD_TIME);
	}
	public void setUpdTime(java.lang.String val) {
		super.setValue(getTable().C_UPD_TIME, val);
	}


}