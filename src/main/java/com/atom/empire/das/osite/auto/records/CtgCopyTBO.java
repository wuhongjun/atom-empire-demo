
package com.atom.empire.das.osite.auto.records;



import com.atom.empire.das.osite.auto.OSiteDB;
import com.atom.empire.das.osite.auto.records.OSiteTBO;
import com.atom.empire.das.osite.auto.OSiteDB.T_CtgCopy;

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
public class CtgCopyTBO extends OSiteTBO<T_CtgCopy> {

	private static final long serialVersionUID = 1L;

	public CtgCopyTBO(OSiteDB db) {
		super(db.T_CTG_COPY);
		// super(OSiteDB.get().T_CTG_COPY);
	}
	
	// Access methods for all columns

	public java.lang.String getNo() {
		return (java.lang.String)super.getValue(getTable().C_NO);
	}
	public void setNo(java.lang.String val) {
		super.setValue(getTable().C_NO, val);
	}

	public java.lang.String getCtg() {
		return (java.lang.String)super.getValue(getTable().C_CTG);
	}
	public void setCtg(java.lang.String val) {
		super.setValue(getTable().C_CTG, val);
	}

	public java.lang.String getFlag() {
		return (java.lang.String)super.getValue(getTable().C_FLAG);
	}
	public void setFlag(java.lang.String val) {
		super.setValue(getTable().C_FLAG, val);
	}

	public java.lang.String getSort() {
		return (java.lang.String)super.getValue(getTable().C_SORT);
	}
	public void setSort(java.lang.String val) {
		super.setValue(getTable().C_SORT, val);
	}

	public java.lang.String getTitle() {
		return (java.lang.String)super.getValue(getTable().C_TITLE);
	}
	public void setTitle(java.lang.String val) {
		super.setValue(getTable().C_TITLE, val);
	}

	public java.lang.String getExt() {
		return (java.lang.String)super.getValue(getTable().C_EXT);
	}
	public void setExt(java.lang.String val) {
		super.setValue(getTable().C_EXT, val);
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