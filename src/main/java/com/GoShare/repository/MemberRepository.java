package com.GoShare.repository;

import com.GoShare.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{

    Member findByEmail(String email);
}
