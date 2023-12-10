package com.example.feedservice.repository;

import com.example.feedservice.entity.Comment;
import com.example.feedservice.entity.Reply;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends MongoRepository<Reply, String> {

    List<Reply> findByCommentId(String commentId);

}
