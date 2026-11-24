package com.gangu.springdatajpademo.repository;

import com.gangu.springdatajpademo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student,Long> {

    Optional<Student> findByEmailLike(String pattern);

    @Query(value = """
             select * from student
             where email = ?1 AND name = ?2
            """, nativeQuery = true)
    Optional<Student> findByEmail(@Param ("email")String email);
}
