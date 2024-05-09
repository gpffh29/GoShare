package com.GoShare.repository;

import com.GoShare.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{

//중복회원 검사
    Member findByEmail(String email);

}
