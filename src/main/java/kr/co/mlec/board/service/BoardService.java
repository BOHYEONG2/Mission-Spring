package kr.co.mlec.board.service;

import java.util.List;

import kr.co.mlec.board.vo.BoardVO;

public interface BoardService {

	public List<BoardVO> getBoardList();
	public BoardVO getBoardByNo(int boardNo);
	void addBoard(BoardVO board);
	
}

/**
 * 주문현황
 * 상품테이블, 회원테이블
 */