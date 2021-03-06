package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
//import javax.transaction.Transactional; 로 사용하는 불상사는 없기를
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /*
        Q. update 메소드에 DB에 쿼리를 날리는 부분이 없다?
        A. JPA의 영속성 컨텍스트 때문!

        # 영속성 컨텍스트
        엔티티를 영구 저장하는 환경
        일종의 논리적 개념

        [더티 체킹(Dirty Checking)]
        JPA 엔티티 매니저가 활성화된 상태로
        트랜잭션 안에서 DB에서 데이터를 가져오면
        이 데이터는 영속성 컨텍스트가 유지된 상태

        이 상태에서 데이터 값 변경 시 트랜잭션이 끝나는 시점에
        해당 테이블에 변경분 반영 -> 즉, Entity 객체 값만 변경해도 별도로 update 쿼리를 날릴 필요 x
    */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );
        return new PostsResponseDto(entity);
    }

    // readOnly = true : 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회속도 개선하기 위한 속성
    // CRUD 기능이 없는 서비스 메소드에만 사용
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );
        postsRepository.delete(posts);
    }
}
