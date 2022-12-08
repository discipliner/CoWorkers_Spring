package com.crunch.service;

import com.crunch.domain.BoardDTO;
import com.crunch.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    @Override
    public void register(BoardDTO boardDTO) {

        log.info("BoardServiceImpl의 register() 실행");
        log.info("register({})", boardDTO);
        mapper.boardInsert(boardDTO);
    }

    @Override
    public BoardDTO get(int postID) {

        log.info("BoardServiceImpl의 get() 실행");
        return null;
    }

    @Override
    public boolean modify(BoardDTO boardDTO) {

        log.info("BoardServiceImpl의 modify() 실행");
        return false;
    }

    @Override
    public boolean remove(int postID) {

        log.info("BoardServiceImpl의 remove() 실행");
        return false;
    }

    @Override
    public List<BoardDTO> getList() {

        log.info("BoardServiceImpl의 getList() 실행");

        /*int currentPage = 1;
        try {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        } catch (NumberFormatException e) {
        }*/

        return null;
    }

}
