package org.soropeza.webui.component;

import org.adempiere.webui.component.Label;
import org.compiere.util.KeyNamePair;

public class KeyNamePairColumn extends Label implements IColumnGrid{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KeyNamePairColumn(KeyNamePair knPair) {
		setValue(knPair.getName());
		setAttribute("Record_ID",  knPair.getKey());
		
	}
	public int getRecord_ID() {
		return (Integer) getAttribute("Record_ID");
	}

}
