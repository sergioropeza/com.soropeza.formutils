package org.soropeza.webui.component;

import org.adempiere.webui.component.Checkbox;

public class BooleanColumn extends Checkbox implements  IColumnGrid{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BooleanColumn(String value, Boolean isEditable) {
		super();
		setChecked(value.equals("Y"));
		setEnabled(isEditable);
	}
	public BooleanColumn(String value) {
		this(value, true);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Object getValue() {
		return isChecked();
	}

}
