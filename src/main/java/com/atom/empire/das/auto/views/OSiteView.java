
package com.atom.empire.das.auto.views;

import org.apache.empire.db.DBDatabase;
import org.apache.empire.db.DBView;

public abstract class OSiteView extends DBView {

	private static final long serialVersionUID = 1L;

	public OSiteView(String name, DBDatabase db) {
		super(name, db);
	}
}
