package org.soropeza.webui.component;

import org.adempiere.webui.editor.WTableDirEditor;
import org.compiere.model.Lookup;

public class TableDirEditorColumn extends  WTableDirEditor implements IColumnGrid{

	public TableDirEditorColumn(Lookup lookup, Object value, Boolean isEditable) {
		super(lookup.getColumnName(), false, !isEditable, false, lookup);
		getComponent().setEnabled(isEditable);
		setValue(value);

	}
	
	public TableDirEditorColumn(Lookup lookup, Object value) {
		this(lookup, value, true);
	}


}
