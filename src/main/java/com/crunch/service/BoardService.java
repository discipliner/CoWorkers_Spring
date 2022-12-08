package com.crunch.service;

import com.crunch.domain.BoardDTO;

import java.util.List;

public interface BoardService {

    void register(BoardDTO boardDTO);

    BoardDTO get(int postID);

    public boolean modify(BoardDTO boardDTO);

    public boolean remove(int postID);

    public List<BoardDTO> getList();

}
