package vn.qti.zkdemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import vn.qti.zkdemo.entity.ChucVu;
import vn.qti.zkdemo.services.ChucVuService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ChucVuViewModel {

	@WireVariable
	private ChucVuService chucVuService;
	private ListModelList<ChucVu> chucVuListModel;
	private String tenChucVu;
	

	public ChucVu getSelectChucVu() {
		return selectChucVu;
	}
	
	public void setSelectChucVu(ChucVu selectChucVu) {
		this.selectChucVu = selectChucVu;
	}

	
	private ChucVu selectChucVu;
	
	
	public String getTenChucVu() {
		return tenChucVu;
	}
	
	
	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}

	@Init
	public void init() {
		List<ChucVu> chucVuList = chucVuService.getChucVus();
		chucVuListModel = new ListModelList<ChucVu>(chucVuList);
	}

	public ListModel<ChucVu> getchucVuListModel() {
		return chucVuListModel;
	}

	@Command
	@NotifyChange("chucVuListModel")
	public void addChucVu(@BindingParam("cmp")  Window x) {
		if(Strings.isBlank(tenChucVu)) {
			return;
		}
		ChucVu chucvu = new ChucVu(tenChucVu);
		chucvu = chucVuService.addChucVu(chucvu);
		chucVuListModel.add(chucvu);
		x.detach();
		BindUtils.postGlobalCommand(null, null, "refreshadd", null);
	}
	
	@GlobalCommand
	@NotifyChange("chucVuListModel")
	public void refreshadd(){
		init();
	}

	@Command
	public void deleteChucVu(@BindingParam("chucvu") ChucVu chucvu) {
		chucVuService.deleteChucVu(chucvu);
		chucVuListModel.remove(chucvu);
	}
	
	@Command
	public void editChucVu(@BindingParam("chucvu") ChucVu chucvu) {
		tenChucVu = chucvu.getTenChucVu();
		selectChucVu = chucvu;
//		BindUtils.postNotifyChange(null,null,this,"tenChucVu");
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectChucVu", selectChucVu);
		final Window win = (Window) Executions.createComponents(
				"/layout/edit-template.zul", null, map);
		win.setMaximizable(true);
		win.doModal();
	}
	
	@GlobalCommand
    @NotifyChange({"selectChucVu"})
    public void capnhatchucvu(@BindingParam("selectChucVu") ChucVu selectChucVu)
    {
        this.selectChucVu = selectChucVu;
        chucVuService.updateChucVu(this.selectChucVu);
    }

}