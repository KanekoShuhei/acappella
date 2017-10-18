package acappella.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acappella.domain.Video;
import acappella.repository.UserLikeVideosRepository;
import acappella.repository.VideoRepository;

@Service
public class VideoService {
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private UserLikeVideosRepository likeVideosRepository;
	
	/**
	 * 動画の全件検索.
	 */
	public List<Video> findAll() {
		return videoRepository.findAllItemsOrderById();
	}
	
	/**
	 * 指定バンドの動画の全件検索.
	 */
	public List<Video> findAllLimitBand(String bandId) {
		return videoRepository.findAllVideosWhereBandOrderById(bandId);
	}
	
	/**
	 * 動画の1件検索.
	 */
	public Video findByVideoById(String videoId) {
		return videoRepository.findOneVideoById(videoId);
	}
	
	/**
	 * 動画の1件検索（YouTubeId）.
	 */
	public Video findByVideoByYouTubeId(String youtubeId) {
		return videoRepository.findOneVideoByYouTubeId(youtubeId);
	}
	
	/**
	 * ログインユーザーがいいねした動画リストの検索.
	 */
	public List<Video> findByVideoByLoginUser(Long userId) {
		return videoRepository.selectLikeVideos(userId);
	}
	
	/**
	 * 動画の登録.
	 */
	public void save(Video video) {
			videoRepository.save(video);
	}
	
	/**
	 * 動画のいいね数を１増やす.
	 */
	public void updateLike(String videoId,Long userId) {
			
			likeVideosRepository.updateLike(videoId, userId);
			videoRepository.updateLike(videoId);
	}
	
	/**
	 * 指定動画IDのいいね数を調べる.
	 */
	public Integer countLikesByVideoId(String videoId) {
		return likeVideosRepository.countLikesByVideoId(videoId);
	}

}
