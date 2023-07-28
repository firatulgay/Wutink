package com.fulgay.wutink.repository;

import com.fulgay.wutink.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 28.08.2021
 */

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {
    @Query(value = "select com from Comment com left join com.experience ex where ex.id = :id")
    List<Comment> findCommentsByExperienceId(@Param("id")Long id);

    @Query(value = "select com from Comment com left join com.user user where user.userName = :username")
    List<Comment> findCommentsByUsername(@Param("username")String username);
}
