package com.green.greengram.feed;


import com.green.greengram.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    //@Query(value = "INSERT INTO feed_comment (feed_id, user_id, comment, created_at, updated_at) VALUES (:feedId, :userId, :comment, now(), now())", nativeQuery = true)
    //FeedComment saveFeedComment(Long feedId, Long userId, String comment);

    @Query(value = "INSERT INTO feed(feed_id, user_id ,contents, location,created_at)VALUES(:feedId,:writerId,:contents,:null,:now())",nativeQuery = true)
    Feed saveFeed(Long feedId, Long writerId, String contents, Long now);


}
