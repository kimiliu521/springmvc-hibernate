package com.kimi.forum.dao;

import org.springframework.stereotype.Repository;

import com.kimi.forum.domain.Board;

@Repository
public class BoardDao extends BaseDao<Board>{
	protected final String GET_BOARD_NUM = "SELECT COUNT(f.boardId) FROM Board f";
	
	public long getBoardNum() {
		return 0;
	}
}
