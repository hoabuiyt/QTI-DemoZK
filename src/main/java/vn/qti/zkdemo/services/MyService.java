package vn.qti.zkdemo.services;

import vn.qti.zkdemo.entity.Log;
import java.util.List;

public interface MyService {

	Log addLog(Log log);

	List<Log> getLogs();

	void deleteLog(Log log);
}
