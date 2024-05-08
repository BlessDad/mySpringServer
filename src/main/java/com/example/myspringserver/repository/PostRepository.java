package com.example.myspringserver.repository;
import com.example.myspringserver.dto.PostScreenDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostScreenDto, Long> {
}
