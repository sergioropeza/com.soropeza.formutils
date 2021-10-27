package org.soropeza.webui.component;

public class BooleanColumn extends ColumnGrid{

	public BooleanColumn(String value, Boolean isEditable) {
		super(value, isEditable);
	}
	public BooleanColumn(String value) {
		super(value, true);
	}

}
