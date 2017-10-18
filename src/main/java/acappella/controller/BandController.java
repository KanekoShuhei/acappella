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
import acappella.domain.Band;
import acappella.domain.LoginUser;
import acappella.repository.UserLoveBandsRepository;
import acappella.service.BandService;

@Controller
@RequestMapping("/band")
public class BandController {
	
	@Autowired
	private BandService bandService;
	
	@Autowired
	private UserLoveBandsRepository loveBandrepository;
	
	@ModelAttribute
	public BandForm setUpBandForm() {
		
		return new BandForm();
	}
	
	@ModelAttribute
	public VideoForm setUpVideoForm() {
		
		return new VideoForm();
	}
	
	/**
	 * バンド詳細ページを表示.
	 */
	@RequestMapping("/detail")
	public String showBandDetail() {
		
		return "band_detail";
	}
	
	/**
	 * バンド登録ページを表示.
	 */
	@RequestMapping("/regist")
	public String regist() {
		
		return "video_band_regist";
	}
	
	/**
	 * バンドランキングページを表示.
	 */
	@RequestMapping("/list")
	public String bandList() {
		
		return "band_list";
	}
	
	/**
	 * バンドランキングページを表示.
	 * @return
	 */
	@RequestMapping("/ranking")
	public String bandRanking() {
		
		return "band_ranking";
	}
		
	/**
	 * バンド1件のJSONを取得.
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get-one-json")
	public Band getOneVideoJSON(String bandId) {
		
		Band band = bandService.findByBandById(bandId);
		return band;
	}
	
	/**
	 * バンド全件のJSONを取得.
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get-all-json")
	public List<Band> getAllVideoJSON() {
		return bandService.findAllBand();
	}
	
	/**
	 * ログインユーザーがいいねしたバンド一覧のJSONを取得.
	 */
	@ResponseBody
	@RequestMapping("/get-love-bands-by-login-user")
	public List<Band> getLoveBandsJson(@AuthenticationPrincipal LoginUser loginUser) {
		
		return bandService.findLoveBandsByLoginUser(loginUser.getUser().getId());
	}
	
	/**
	 * バンドを１件登録.
	 * @return
	 */
	@RequestMapping("/save")
	public String save(@Validated BandForm form,BindingResult result,Model model) {

		Band band = bandService.findByBandByTwitter(form.getTwitter());
		if (band != null) {
			result.rejectValue("twitter", null, "そのバンド（TwitterID）は既に登録されています");
		}

		// 入力値エラーがあれば登録フォームに戻す
		if (result.hasErrors()) {
			return regist();
		}
		band = new Band();
		BeanUtils.copyProperties(form, band);
		bandService.save(band);

		return "redirect:/band/regist";
	}
	
	/**
	 * バンドをお気に入り登録.
	 * @return
	 */
	@RequestMapping("/love")
	public String addLikes(String bandId,@AuthenticationPrincipal LoginUser loginUser) {

		bandService.loveBand(bandId, loginUser.getUser().getId());
		return "redirect:/band/detail?bandId=" + bandId;
	}
	
	/**
	 * バンド1件のお気に入り数を取得.
	 */
	@ResponseBody
	@RequestMapping("/get-one-love-count")
	public Integer getOneBandLoves(String bandId) {
		
		Integer loveCount = bandService.countLovesByBandId(bandId);
		return loveCount;
	}
	
	/**
	 * ログインユーザーがバンドお気に入りを1件を取消.
	 */
	@RequestMapping("/delete-love-bands")
	public String deleteLoveBand(String bandId,@AuthenticationPrincipal LoginUser loginUser) {
		
		loveBandrepository.deleteloveBand(loginUser.getUser().getId(), bandId);
		
		return "redirect:/band/detail?bandId=" + bandId;
	}
	
}
