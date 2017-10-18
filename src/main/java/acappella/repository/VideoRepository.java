package acappella.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import acappella.domain.Video;

@Repository
public class VideoRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Video用RowMapper.
	 * 
	 * @return video
	 */
	private static final RowMapper<Video> videoRowMapper = (rs, i) -> {
		Video video = new Video();
		video.setId(rs.getInt("id"));
		video.setYoutubeId(rs.getString("youtube_id"));
		video.setBandId(rs.getInt("band_id"));
		video.setLikes(rs.getInt("likes"));
		video.setBandName(rs.getString("name"));
		return video;
	};

	/**
	 * 動画の全件検索(いいね順).
	 */
	public List<Video> findAllItemsOrderById() {
		String sql = "SELECT v.id,v.youtube_id,v.band_id,count(u.id) as likes,b.name FROM videos v LEFT OUTER JOIN user_like_videos u ON v.id = u.video_id JOIN bands b ON v.band_id = b.id GROUP BY v.id,b.name order by v.likes DESC";
		return template.query(sql, videoRowMapper);
	}

	/**
	 * 特定バンドの動画の全件検索(ID順).
	 */
	public List<Video> findAllVideosWhereBandOrderById(String bandId) {
		String sql = "SELECT v.id,v.youtube_id,v.band_id,count(u.id) as likes,b.name FROM videos v LEFT OUTER JOIN user_like_videos u ON v.id = u.video_id JOIN bands b ON v.band_id = b.id WHERE band_id = :id GROUP BY v.id,b.name order by v.likes DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", Integer.parseInt(bandId));
		return template.query(sql, param, videoRowMapper);
	}

	/**
	 * 動画の1件検索.
	 */
	public Video findOneVideoById(String videoId) {
		String sql = "SELECT v.id,youtube_id,band_id,likes,name FROM videos v JOIN bands b ON v.band_id = b.id WHERE v.video_id = :videoId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("videoId", videoId);
		return template.queryForObject(sql, param, videoRowMapper);
	}

	/**
	 * 動画の1件検索（YouTubeId）.
	 */
	public Video findOneVideoByYouTubeId(String YouTubeId) {
		String sql = "SELECT v.id,v.youtube_id,v.band_id,count(u.id) as likes,b.name FROM videos v LEFT OUTER JOIN user_like_videos u ON v.id = u.video_id JOIN bands b ON v.band_id = b.id WHERE youtube_id = :YouTubeId GROUP BY v.id,b.name order by v.likes DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("YouTubeId", YouTubeId);
		return template.queryForObject(sql, param, videoRowMapper);
	}

	/**
	 * 動画を1件登録.
	 */
	public void save(Video video) {
		SqlParameterSource paramMap = new BeanPropertySqlParameterSource(video);
		String sql = "INSERT INTO videos (youtube_id,band_id) values (:youtubeId,:bandId)";
		try {
			template.update(sql, paramMap);
		} catch (DataAccessException e) {
			e.printStackTrace(); 
		}
	}

	/**
	 * 動画のいいね数をプラス１する.
	 */
	public void updateLike(String videoId) {
		String sql = "UPDATE videos SET likes = likes + 1 WHERE id=:videoId";
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("videoId", Integer.parseInt(videoId));
		try {
			template.update(sql, paramMap);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 指定ユーザーがいいねした動画を検索する.
	 */
	public List<Video> selectLikeVideos(Long userId) {
		String sql = "SELECT v.id,youtube_id,band_id,count(u.id) as likes,b.name FROM (videos v JOIN user_like_videos u ON v.id = u.video_id) JOIN bands b ON v.band_id = b.id WHERE u.user_id = :userId GROUP BY v.id,b.name";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId",userId);
		try {
			return template.query(sql, param, videoRowMapper);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
