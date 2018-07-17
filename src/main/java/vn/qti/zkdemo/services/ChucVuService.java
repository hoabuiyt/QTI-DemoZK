package vn.qti.zkdemo.services;

import vn.qti.zkdemo.entity.ChucVu;
import java.util.List;

public interface ChucVuService {

	ChucVu addChucVu(ChucVu chucvu);

	List<ChucVu> getChucVus();

	void deleteChucVu(ChucVu chucvu);

	void updateChucVu(ChucVu chucvu);
}