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

import acappella.domain.Band;

@Repository
public class BandRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Band用RowMapper.
	 * 
	 * @return video
	 */
	private static final RowMapper<Band> bandRowMapper = (rs, i) -> {
	    Band band = new Band();
	    band.setId(rs.getInt("id"));
	    band.setName(rs.getString("name"));
		band.setPopularity(rs.getInt("popularity"));
		band.setTwitter(rs.getString("twitter_id"));
		return band;
	};
	
	/**
	 * バンドの全件検索(ID順).
	 */
	public List<Band> findAllBandsOrderById() {
		String sql = "SELECT b.id,b.name,b.twitter_id,count(u.id) as popularity FROM bands b LEFT OUTER JOIN user_love_bands u ON b.id = u.band_id GROUP BY b.id";
		return template.query(sql, bandRowMapper);
	}
	
	/**
	 * バンドの複数検索（指定ユーザーのお気に入り登録）.
	 */
	public List<Band> selectLoveBands(Long userId) {
		String sql = "SELECT b.id,b.name,b.twitter_id,count(u.id) as popularity FROM bands b LEFT OUTER JOIN user_love_bands u ON b.id = u.band_id WHERE u.user_id = :userId GROUP BY b.id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId",userId);
		try {
			return template.query(sql, param, bandRowMapper);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * バンドの1件ID検索.
	 */
	public Band findOneBandById(String bandId) {
		String sql = "SELECT b.id,b.name,b.twitter_id,count(u.id) as popularity FROM bands b LEFT OUTER JOIN user_love_bands u ON b.id = u.band_id WHERE b.id = :id GROUP BY b.id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", Integer.parseInt(bandId));
		return template.queryForObject(sql, param,bandRowMapper);
	}
	
	/**
	 * バンドの1件Twitter検索.
	 */
	public Band findOneBandByTwitter(String twitterId) {
		String sql = "SELECT b.id,b.name,b.twitter_id,count(u.id) as popularity FROM bands b LEFT OUTER JOIN user_love_bands u ON b.id = u.band_id WHERE twitter_id = :twitter_id GROUP BY b.id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("twitter_id", twitterId);
		try {
			return template.queryForObject(sql, param,bandRowMapper);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * バンドを１件登録.
	 */
	public void save(Band band) {
		SqlParameterSource paramMap = new BeanPropertySqlParameterSource(band);
		String sql = "INSERT INTO bands (name,twitter_id) values (:name,:twitter)";
		try {
			template.update(sql, paramMap);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * バンドのお気に入り数をプラス１する.
	 */
	public void updateLove(String bandId) {
		String sql = "UPDATE bands SET popularity = popularity + 1 WHERE id=:bandId";
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("bandId", Integer.parseInt(bandId));
		try {
			template.update(sql, paramMap);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
}
