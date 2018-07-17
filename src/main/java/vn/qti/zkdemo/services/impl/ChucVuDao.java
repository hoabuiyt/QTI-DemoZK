package vn.qti.zkdemo.services.impl;

import vn.qti.zkdemo.entity.ChucVu;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ChucVuDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<ChucVu> queryAll() {
		//Query query = em.createQuery("SELECT c FROM chucvu c");
		//Query query = em.createQuery("from chucvu");
		Query query = em.createQuery( "SELECT r FROM ChucVu r" );
		List<ChucVu> result = query.getResultList();
		return result;
	}

	@Transactional(readOnly = true)
	public ChucVu get(Integer id) {
		return em.find(ChucVu.class, id);
	}

	@Transactional
	public ChucVu save(ChucVu chucvu) {
		em.persist(chucvu);
		em.flush();
		return chucvu;
	}

	@Transactional
	public void delete(ChucVu chucvu) {
		ChucVu r = get(chucvu.getId());
		if(r != null) {
			em.remove(r);
		}
	}

	@Transactional
	public void update(ChucVu chucvu) {
		// TODO Auto-generated method stub
		em.merge(chucvu);
		em.flush();
//		ChucVu r = get(chucvu.getId());
//		if(r != null) {
//			em.merge(r);
//		}
		
	}

}