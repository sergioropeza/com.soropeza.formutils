package org.soropeza.webui.component;

import org.adempiere.webui.editor.WStringEditor;

public class StringColumn extends WStringEditor implements IColumnGrid{
	
	public StringColumn(String value, Boolean isEditable) {
		super();
		setValue(value);
		getComponent().setDisabled(isEditable);
	}
	public StringColumn(String value) {
		this(value, true);
	}
}
