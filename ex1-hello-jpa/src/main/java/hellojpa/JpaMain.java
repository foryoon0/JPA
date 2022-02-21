package hellojpa;

import java.util.List;

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
			
//			Member findMember = em.find(Member.class, 1L);
			
			// JPQL : 멤버 객체를 대상으로 쿼리를 진행함. 객체 지향 쿼리 (페이징 시 메리트있음)
		//	List<Member> result = em.createQuery("select m from Member as m", Member.class)
		//			.setFirstResult(5)
		//			.setMaxResults(8)
		//			.getResultList();	
			
		//	for(Member member : result) {
		//		System.out.println("member.name=" + member.getName());
		//	} 
			
			// 비영속상태 :  JPA 와 관련이 없고 DB에도 들어가지않음
//			Member member = new Member();
//			member.setId(101L);
//			member.setName("HelloJPA");
			
			// 영속 상태 : 이 때 DB에 저장되는것은 아님. 
//			System.out.println("=====BEFORE====");
//			em.persist(member);
//			System.out.println("====AFTER====");

//			Member findMember = em.find(Member.class, 101L);
			
//			System.out.println("findMember.id = " + findMember.getId());
//			System.out.println("findMember.name = " + findMember.getName());
		
			
			//영속
			Member member1 = new Member(150L, "A");
			Member member2 = new Member(160L, "B");
			
			em.persist(member1);
			em.persist(member2);
			
			System.out.println("=======================");
			
			
			// 이 때 COMMIT 후 저장됨. 
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
	
	}
}
