package acappella.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acappella.domain.Band;
import acappella.repository.BandRepository;
import acappella.repository.UserLoveBandsRepository;

@Service
public class BandService {
	
	@Autowired
	private BandRepository bandRepository;
	
	@Autowired
	private UserLoveBandsRepository loveBandsRepository;
	
	/**
	 * バンドの全件検索.
	 */
	public List<Band> findAllBand() {
		return bandRepository.findAllBandsOrderById();
	}
	
	/**
	 * バンド1件ID検索.
	 */
	public Band findByBandById(String bandId) {
		return bandRepository.findOneBandById(bandId);
	}
	
	/**
	 * バンド1件TwitterID検索.
	 */
	public Band findByBandByTwitter(String twitterId) {
		return bandRepository.findOneBandByTwitter(twitterId);
	}
	
	/**
	 * ログインユーザーがいいねしたバンドリストの検索.
	 */
	public List<Band> findLoveBandsByLoginUser(Long userId) {
		return bandRepository.selectLoveBands(userId);
	}

	
	/**
	 * バンドの登録.
	 */
	public void save(Band band) {
			bandRepository.save(band);
	}
	
	/**
	 * バンドをお気に入り登録.
	 */
	public void loveBand(String bandId,Long userId) {
			
			loveBandsRepository.loveBand(userId, bandId);
			bandRepository.updateLove(bandId);
	}
	
	/**
	 * 指定バンドIDのお気に入り数を調べる.
	 */
	public Integer countLovesByBandId(String bandId) {
		return loveBandsRepository.countLovesByBandId(bandId);
	}

}
