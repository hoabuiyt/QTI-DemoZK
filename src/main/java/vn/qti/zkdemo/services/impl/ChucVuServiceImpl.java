package vn.qti.zkdemo.services.impl;

import vn.qti.zkdemo.entity.ChucVu;
import vn.qti.zkdemo.services.ChucVuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("chucVuService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ChucVuServiceImpl implements ChucVuService {

	@Autowired
	ChucVuDao dao;
	
	public List<ChucVu>getTodoList() {
	        return dao.queryAll();
	    }

	public ChucVu addChucVu(ChucVu chucvu) {
		return dao.save(chucvu);
	}

	public List<ChucVu> getChucVus() {
		return dao.queryAll();
	}

	public void deleteChucVu(ChucVu chucvu) {
		dao.delete(chucvu);
	}
	
	public void updateChucVu(ChucVu chucvu) {
		dao.update(chucvu);
	}

}