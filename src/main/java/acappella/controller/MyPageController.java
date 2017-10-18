package acappella.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import acappella.domain.Band;
import acappella.domain.LoginUser;
import acappella.domain.Video;
import acappella.repository.BandRepository;
import acappella.repository.UserLoveBandsRepository;
import acappella.repository.VideoRepository;


/**
 * ログインフォーム画面のコントローラクラス.
 * @author shuhei.kaneko
 *
 */
@Controller
@RequestMapping("/my-page")
public class MyPageController {

	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private BandRepository bandRepository;
	
	@Autowired
	private UserLoveBandsRepository loveBandrepository;
	
	/**
	 * マイページを表示.
	 */
	@RequestMapping("")
	public String myPage(@AuthenticationPrincipal LoginUser loginUser) {
		
		return "my_page";
	}
	
	/**
	 * ログインユーザーがいいねした動画一覧をJSONで返す.
	 */
	@ResponseBody
	@RequestMapping("/like-videos")
	public List<Video> getLikeVideosJson(@AuthenticationPrincipal LoginUser loginUser) {
		
		return videoRepository.selectLikeVideos(loginUser.getUser().getId());
	}
	
	/**
	 * ログインユーザーがお気に入り登録したバンド一覧をJSONで返す.
	 */
	@ResponseBody
	@RequestMapping("/love-bands")
	public List<Band> getLoveBandsJson(@AuthenticationPrincipal LoginUser loginUser) {
		
		return bandRepository.selectLoveBands(loginUser.getUser().getId());
	}
	
	/**
	 * ログインユーザーがお気に入り登録したバンド1件を削除.
	 */
	@RequestMapping("/delete-love-bands")
	public String deleteLoveBand(String bandId,@AuthenticationPrincipal LoginUser loginUser) {
		
		loveBandrepository.deleteloveBand(loginUser.getUser().getId(), bandId);
		
		return "redirect:/my-page";
	}

}
