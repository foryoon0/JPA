package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain { //JPA의 모든 데이터 변경은 트랙잭션 안에서 실행
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		// 하나만 생성해서 애플리케이션 전체에 공유		
		EntityManager em = emf.createEntityManager();
		// 고객의 요청이 올때 마다 썼다가 닫았다가 해야됨. 사용하고 버리기! 쓰레드 간에 공유XXXX
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	
		try{
			//SELECT 쿼리
			//Member findMember = em.find(Member.class, 1L);
			//System.out.println("findMember = " + findMember.getId());
			//System.out.println("findMember = " + findMember.getName());
			
			//DELETE 쿼리
			//em.remove(findMember);
			
			Member findMember = em.find(Member.class, 1L);
			findMember.setName("HelloJPA");
			
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
	
	}
}
