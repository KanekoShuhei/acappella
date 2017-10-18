package acappella.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;


@Repository
public class UserLoveBandsRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * バンドをお気に入り登録する.
	 */
	public void loveBand(Long userId,String bandId) {
		String sql = "INSERT INTO user_love_bands (user_id,band_id) VALUES (:userId,:bandId)";
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("userId",userId).addValue("bandId", Integer.parseInt(bandId));
		try {
			template.update(sql, paramMap);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * お気に入りバンドを1件削除する.
	 */
	public void deleteloveBand(Long userId,String bandId) {
		String sql = "DELETE FROM user_love_bands WHERE user_id = :userId AND band_id = :bandId";
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("userId",userId).addValue("bandId", Integer.parseInt(bandId));
		try {
			template.update(sql, paramMap);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 指定バンドIDのお気に入り数をカウントする.
	 */
	public Integer countLovesByBandId(String bandId) {
		String sql = "SELECT count(*) FROM user_like_bands WHERE band_id=:bandId";
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("bandId",Integer.parseInt(bandId));
		try {
			Integer num = template.queryForObject(sql, paramMap,Integer.class);
			return num;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
