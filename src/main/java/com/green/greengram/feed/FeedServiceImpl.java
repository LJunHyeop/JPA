package com.green.greengram.feed;

import com.green.greengram.common.CustomFileUtils;
import com.green.greengram.common.GlobalConst;
import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedPics;
import com.green.greengram.entity.User;
import com.green.greengram.feed.model.*;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.security.AuthenticationFacade;
import com.green.greengram.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.green.greengram.common.GlobalConst.COMMENT_SIZE_PER_FEED;

@RequiredArgsConstructor
@Service
@Slf4j
public class FeedServiceImpl implements FeedService {
    private final FeedMapper mapper;
    private final CustomFileUtils customFileUtils;
    private final AuthenticationFacade authenticationFacade;
    private final FeedRepository repository;
    private final UserRepository userRepository;
    private final FeedPicRepository picsRepository;

    @Transactional
    public FeedPostRes postFeed(List<MultipartFile> pics, FeedPostReq p){
        Feed feed = new Feed();
        User user = userRepository.getReferenceById(authenticationFacade.getLoginUserId());

        feed.setUserId(user);
        feed.setContents(p.getContents());
        feed.setLocation(p.getLocation());
        repository.save(feed);

        Long feedId = feed.getFeedId();

        if(pics == null || pics.isEmpty()) {
            return FeedPostRes.builder()
                    .feedId(feedId)
                    .build();
        }

        List<String> picsInsert = new ArrayList<>();
        List<FeedPics> feedPicsList = new ArrayList<>();
        try {
            String path = String.format("feed/%d", feedId);
            for (MultipartFile file : pics) {
                String fileName = customFileUtils.makeRandomFileName(file);
                picsInsert.add(fileName);
                customFileUtils.makeFolders(path);
                String target = String.format("%s/%s" , path, fileName);
                customFileUtils.transferTo(file, target);
                FeedPics feedPics = new FeedPics();
                feedPics.setPic(fileName);
                feedPics.setFeed(repository.getReferenceById(feedId));
                feedPicsList.add(feedPics);
            }
            picsRepository.saveAll(feedPicsList);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Feed등록 오류");
        }
        /*\
        JPA Batch Insert(대량 )는 쓰기 지연을 사용해 동작
        이는 DB에 insert 후 id 가 할당되는 identity 전략을 사용하면 사용 할 수 없음
        yaml 파일에 # JAP Batch 작업 insert,update 주석 부분과 DB URL 에 파라미터 값 추가
         */

        return FeedPostRes.builder()
                .feedId(feedId)
                .pics(picsInsert)
                .build();
    }

    @Override
    public List<FeedGetRes> getFeed(FeedGetReq p){
        p.setSignedUserId(authenticationFacade.getLoginUserId());
        List<FeedGetRes> list = mapper.getFeed(p);

        if(list == null){
            return Collections.emptyList();
        }
        for(FeedGetRes res : list){
            //피드 하나당 포함된
            //사진 리스트
            List<String> pics = mapper.getFeedPicsByFeedId(res.getFeedId());
            res.setPics(pics);

            //댓글 리스트
            List<FeedCommentGetRes> comment = mapper.getFeedComment(res.getFeedId());
            boolean hasMoreComment = comment.size() == GlobalConst.COMMENT_SIZE_PER_FEED ;
            res.setIsMoreComment( hasMoreComment ? 1 : 0 );
            if(hasMoreComment){
                comment.remove(comment.size() - 1);
            }
            res.setComments(comment);
        }
        return list;
    }
}
