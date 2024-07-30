package com.green.greengram.feed;

import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedPics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedPicRepository  extends JpaRepository<FeedPics, Long> {

    @Query(value = "INSERT INTO feed_pic(feed_id,pic)VALUES(:feedId,:pic)",nativeQuery = true)
    Feed saveFeedPic(Long feedId, String pic);

}
