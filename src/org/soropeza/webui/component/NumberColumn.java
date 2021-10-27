package org.soropeza.webui.component;

import java.math.BigDecimal;

import org.adempiere.webui.editor.WNumberEditor;
import org.compiere.util.Env;

public class NumberColumn extends WNumberEditor implements IColumnGrid{

	public NumberColumn(BigDecimal value, Boolean isEditable) {
		super();
		
		setValue(value==null?Env.ZERO:value);
		getComponent().setEnabled(isEditable);
	}
	public NumberColumn(BigDecimal value) {
		this(value, true);
	}
}
