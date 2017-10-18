package acappella.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class UserLikeVideosRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
//	/**
//	 * UserLikeVideos用RowMapper.
//	 * 
//	 * @return video
//	 */
//	private static final RowMapper<User> bandRowMapper = (rs, i) -> {
//	    User user = new User();
//	    user.setId(rs.getLong("id"));
//	    user.setName(rs.getString("name"));
//		user.setPopularity(rs.getInt("popularity"));
//		user.setTwitter(rs.getString("twitter_id"));
//		
//		return user;
//	};
	
	/**
	 * 動画のいいね数をプラス１する.
	 */
	public void updateLike(String videoId,Long userId) {
		String sql = "INSERT INTO user_like_videos (user_id,video_id) VALUES (:userId,:videoId)";
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("userId",userId).addValue("videoId", Integer.parseInt(videoId));
		try {
			template.update(sql, paramMap);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 動画のいいねを取り消す.
	 */
	public void deletelikeVideo(Long userId,String videoId) {
		String sql = "DELETE FROM user_like_videos WHERE user_id = :userId AND video_id = :videoId";
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("userId",userId).addValue("videoId", Integer.parseInt(videoId));
		try {
			template.update(sql, paramMap);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 指定動画IDのいいね数をカウントする.
	 */
	public Integer countLikesByVideoId(String videoId) {
		String sql = "SELECT count(*) FROM user_like_videos WHERE video_id=:videoId";
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("videoId",Integer.parseInt(videoId));
		try {
			Integer num = template.queryForObject(sql, paramMap,Integer.class);
			return num;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
