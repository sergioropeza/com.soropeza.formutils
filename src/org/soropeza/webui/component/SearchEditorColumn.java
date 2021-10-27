package org.soropeza.webui.component;

import org.compiere.model.Lookup;

public class SearchEditorColumn extends ColumnGrid {

	private Lookup lookup;
	private String columnName;

	public SearchEditorColumn(Lookup lookup, Object value, Boolean isEditable) {
		super(value, isEditable);
		this.setLookup(lookup);
		this.columnName = lookup.getColumnName();

	}

	public SearchEditorColumn(Lookup lookup, Object value) {
		this(lookup, value, true);
	}

	public Lookup getLookup() {
		return lookup;
	}

	public void setLookup(Lookup lookup) {
		this.lookup = lookup;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

}
