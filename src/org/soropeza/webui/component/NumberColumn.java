package org.soropeza.webui.component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.adempiere.webui.component.NumberBox;
import org.compiere.util.Env;

public class NumberColumn extends NumberBox implements IColumnGrid{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object oldValue;
	
	public NumberColumn(BigDecimal value, Boolean isEditable) {
		super(false);	
		this.setValue(value==null?Env.ZERO:value);
		setEnabled(isEditable);
	}
	public NumberColumn(BigDecimal value) {
		this(value, true);
	}
	
	@Override
    public void setValue(Object value)
    {
    	if (value == null)
    		setOldValue(null);
    	else if (value instanceof BigDecimal)
    		setOldValue(((BigDecimal) value).setScale(2, RoundingMode.HALF_UP));
    	else if (value instanceof Number)
    		setOldValue(BigDecimal.valueOf(((Number)value).doubleValue()).setScale(2, RoundingMode.HALF_UP));
    	else
    		setOldValue((new BigDecimal(value.toString())).setScale(2, RoundingMode.HALF_UP));
    	super.setValue(getOldValue());
    }
	public Object getOldValue() {
		return oldValue;
	}
	public void setOldValue(Object oldValue) {
		this.oldValue = oldValue;
	}
}
