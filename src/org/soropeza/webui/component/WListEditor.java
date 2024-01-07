package org.soropeza.webui.component;

import java.util.Properties;

import org.adempiere.webui.editor.WTableDirEditor;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

public class WListEditor {
	
	public static WTableDirEditor get(String Table_name, String ColumnName) throws Exception {
		return get(Table_name, ColumnName, null);
	}
	
	public static WTableDirEditor get(String Table_name, String ColumnName, String where) throws Exception {
		MLookup lookup = null;
		Properties ctx = Env.getCtx();
		int M_Column_ID = MColumn.getColumn_ID(Table_name, ColumnName);
		MColumn column = new MColumn(ctx, M_Column_ID, null);
		if (where==null) {
			where = "";
			
		}
		if (column.getAD_Reference_ID()==17 || column.getAD_Reference_ID()==18 || column.getAD_Reference_ID()==19) {
			 lookup = MLookupFactory.get(ctx, 0, 0, M_Column_ID, DisplayType.List);
		}else {
			lookup = MLookupFactory.get(Env.getCtx(), 0, M_Column_ID, DisplayType.TableDir, Env.getLanguage(ctx),
					ColumnName, 0, false, where);
		}

		return new WTableDirEditor(ColumnName, true, false, false, lookup);
		
	}

}
