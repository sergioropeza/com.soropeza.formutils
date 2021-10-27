package org.soropeza.webui.component;

import org.adempiere.webui.component.Label;
import org.compiere.util.KeyNamePair;

public class KeyNamePairColumn extends Label implements IColumnGrid{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Record_ID; 

	public KeyNamePairColumn(KeyNamePair knPair) {
		setValue(knPair.getName());
		this.Record_ID = knPair.getKey();
		
	}
	public int getRecord_ID() {
		return Record_ID;
	}
	public void setRecord_ID(int record_ID) {
		Record_ID = record_ID;
	}

}
