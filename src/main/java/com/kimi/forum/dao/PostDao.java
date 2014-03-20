package com.kimi.forum.dao;

import org.springframework.stereotype.Repository;

import com.kimi.forum.domain.Post;

@Repository
public class PostDao extends BaseDao<Post>{
	protected final String GET_PAGED_POSTS = "from Post where topic.topicId =? order by createTime desc"; 

	
	public Page getPagedPosts(int topicId, int pageNo, int pageSize) {
		return pagedQuery(GET_PAGED_POSTS,pageNo,pageSize,topicId);
	}
    
	/**
	 * 删除主题下的所有帖子
	 * @param topicId 主题ID
	 */
	public void deleteTopicPosts(int topicId) {
		getSession().delete(topicId);
	}
}
