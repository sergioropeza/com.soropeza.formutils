package org.soropeza.webui.component;

import java.math.BigDecimal;

public class NumberColumn extends ColumnGrid{

	public NumberColumn(BigDecimal value, Boolean isEditable) {
		super(value, isEditable);
	}
	public NumberColumn(BigDecimal value) {
		super(value, true);
	}
}
