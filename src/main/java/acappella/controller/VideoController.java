package acappella.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import acappella.controller.form.BandForm;
import acappella.controller.form.VideoForm;
import acappella.domain.LoginUser;
import acappella.domain.Video;
import acappella.repository.UserLikeVideosRepository;
import acappella.service.VideoService;


@Controller
@RequestMapping("/video")
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private UserLikeVideosRepository likeVideosRepository;
	
	@ModelAttribute
	public BandForm setUpBandForm() {
		
		return new BandForm();
	}
	
	@ModelAttribute
	public VideoForm setUpVideoForm() {
		
		return new VideoForm();
	}
	
	/**
	 * 動画一覧ページを表示.
	 * @return
	 */
	@RequestMapping("/list")
	public String itemListPage(Model model) {
		
		return "top_page";
	}
	
	/**
	 * 動画詳細ページを表示.
	 * @return
	 */
	@RequestMapping("/detail")
	public String videoDetailPage(String youtubeId,Model model) {
		return "video_detail";
	}
	
	/**
	 * 動画登録ページを表示.
	 */
	@RequestMapping("/regist")
	public String regist() {
		
		return "video_band_regist";
	}
	
	/**
	 * 動画全件のJSONを取得.
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get-all-json")
	public List<Video> getAllVideosJSON() {
		
		List<Video> videoList = videoService.findAll();
		return videoList;
	}
	
	/**
	 * 指定IDの動画全件のJSONを取得.
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get-band-videos-json")
	public List<Video> getAllVideosLimitBand(String bandId) {
		
		List<Video> videoList = videoService.findAllLimitBand(bandId);
		return videoList;
	}
	
	/**
	 * 動画1件のJSONを取得.
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get-one-json")
	public Video getOneVideoJSON(String videoId) {
		
		Video video = videoService.findByVideoById(videoId);
		return video;
	}
	
	/**
	 * 動画1件のJSONをYouTubeIDから取得.
	 */
	@ResponseBody
	@RequestMapping("/get-one-json-by-youtubeId")
	public Video getOneVideoJSONByYouTubeId(String youtubeId) {
		
		Video video = videoService.findByVideoByYouTubeId(youtubeId);
		return video;
	}
	
	/**
	 * ログインユーザーがいいねした動画一覧をJSONで返す.
	 */
	@ResponseBody
	@RequestMapping("/get-one-json-by-login-user")
	public List<Video> getLikeVideosJson(@AuthenticationPrincipal LoginUser loginUser) {
		
		return videoService.findByVideoByLoginUser(loginUser.getUser().getId());
	}
	
	/**
	 * 動画を１件登録.
	 * @return
	 */
	@RequestMapping("/save")
	public String save(@Validated VideoForm videoForm,BindingResult result,Model model) {

		if(videoForm.getBandId() == ""){
			result.rejectValue("bandId", null, "バンドを選択して下さい");
		}
		
		Video newVideo = videoService.findByVideoByYouTubeId(videoForm.getYoutubeId());
		if (newVideo != null) {
			result.rejectValue("youtubeId", null, "その動画は既に登録されています");
		}
		
		// 入力値エラーがあれば登録フォームに戻す
		if (result.hasErrors()) {
			return regist();
		}
		newVideo = new Video();
		BeanUtils.copyProperties(videoForm, newVideo);
		newVideo.setBandId(Integer.parseInt(videoForm.getBandId()));
		videoService.save(newVideo);

		return "redirect:/band/regist";
	}
	
	/**
	 * 動画にいいねをする.
	 * @return
	 */
	@RequestMapping("/addlikes")
	public String addLikes(String videoId,@AuthenticationPrincipal LoginUser loginUser) {

		videoService.updateLike(videoId,loginUser.getUser().getId());
		return "redirect:/video/list";
	}	
	
	/**
	 * 動画1件のいいね数を取得.
	 */
	@ResponseBody
	@RequestMapping("/get-one-like-count")
	public Integer getOneVideoLikes(String videoId) {
		
		Integer likeNum = videoService.countLikesByVideoId(videoId);
		return likeNum;
	}
	
	/**
	 * ログインユーザーの動画いいねを1件を取消.
	 */
	@RequestMapping("/delete-like-videos")
	public String deleteLikedVideo(String videoId,@AuthenticationPrincipal LoginUser loginUser) {
		
		likeVideosRepository.deletelikeVideo(loginUser.getUser().getId(), videoId);
		
		return "redirect:/video/list";
	}
	
}
