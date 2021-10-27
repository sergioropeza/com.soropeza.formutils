package org.soropeza.webui.component;

public class StringColumn extends ColumnGrid{

	public StringColumn(String value, Boolean isEditable) {
		super(value, isEditable);
	}
	public StringColumn(String value) {
		super(value, true);
	}

}
