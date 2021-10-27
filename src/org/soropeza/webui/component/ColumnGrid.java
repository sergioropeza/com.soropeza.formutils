package org.soropeza.webui.component;

public class ColumnGrid implements IColumnGrid {

	private Boolean isEditable;
	private Object  value;


	public ColumnGrid(Object value, Boolean isEditable) {
		this.value = value;
		this.isEditable = isEditable;
	}


	@Override
	public Object getValue() {
		return value;

	}

	@Override
	public Boolean isEditable() {
		return isEditable;
	}

}
