package vn.qti.zkdemo;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import vn.qti.zkdemo.entity.ChucVu;
import vn.qti.zkdemo.services.ChucVuService;

import java.util.HashMap;
import org.zkoss.bind.BindUtils;
import java.util.Map;

public class ChucVuEditModel {
	 @Wire("#winedit")
	    private Window win;
	    private String tenChucVu;
	    private ChucVu selectChucVu;
	    private ChucVuService chucVuService;
	    public ChucVu getSelectChucVu() {
			return selectChucVu;
		}

		public void setSelectChucVu(ChucVu selectChucVu) {
			this.selectChucVu = selectChucVu;
		}

		public String getTenChucVu() {
			return tenChucVu;
		}

		public void setTenChucVu(String tenChucVu) {
			this.tenChucVu = tenChucVu;
		}

		@Init
	    public void init(@ContextParam(ContextType.VIEW) Component view,
	            @ExecutionArgParam("selectChucVu") ChucVu cv
	            ) {
	        Selectors.wireComponents(view, this, false);
	        this.selectChucVu = cv;
	    }

	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    @Command
	    public void save() {
	    	
	        Map args = new HashMap();
	        args.put("selectChucVu", this.selectChucVu);
	        BindUtils.postGlobalCommand(null, null, "capnhatchucvu", args);
	        win.detach();
	    }
	    
	    @Command
	    public void closeThis() {
	        win.detach();
	    }

}
