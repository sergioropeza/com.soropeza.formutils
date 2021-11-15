package org.soropeza.webui.component;

import org.adempiere.webui.editor.WSearchEditor;
import org.compiere.model.Lookup;

public class SearchEditorColumn extends WSearchEditor implements IColumnGrid{

	public SearchEditorColumn(Lookup lookup, Object value, Boolean isEditable) {
		super(lookup.getColumnName(), false, !isEditable, false, lookup);
		getComponent().setEnabled(isEditable);
		getComponent().setAttribute("searchEditor", this);
		setValue(value);

	}
	
	public SearchEditorColumn(Lookup lookup, Object value) {
		this(lookup, value, true);
	}


}
