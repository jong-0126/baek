package com.icia.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.icia.common.util.StringUtil;
import com.icia.web.model.KakaoPayApprove;
import com.icia.web.model.KakaoPayOrder;
import com.icia.web.model.KakaoPayReady;
import com.icia.web.model.Response;
import com.icia.web.service.KakaoPayService;
import com.icia.web.util.CookieUtil;
import com.icia.web.util.HttpUtil;

@Controller("kakaoPayController")
public class KakaoPayController 
{
	private static Logger logger = LoggerFactory.getLogger(KakaoPayController.class);
	
	@Autowired
	private KakaoPayService kakaoPayService;
	
	//쿠키명
	@Value("#{env['auth.cookie.name']}")
	private String AUTH_COOKIE_NAME;
	
	@RequestMapping(value="/kakao/pay")
	public String pay(HttpServletRequest request, HttpServletResponse response)
	{
		return "/kakao/pay";
	}
	
	@RequestMapping(value="/kakao/payReady", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> payReady(HttpServletRequest request, HttpServletResponse response)
	{
		Response<Object> ajaxResponse = new Response<Object>();
		
		String orderId = StringUtil.uniqueValue();
		String userId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		String itemCode = HttpUtil.get(request, "itemCode", "");
		String itemName = HttpUtil.get(request, "itemName", "");
		int quantity = HttpUtil.get(request, "quantity", 0);
		int totalAmount = HttpUtil.get(request, "totalAmount", 0);
		int taxFreeAmount = HttpUtil.get(request, "taxFreeAmount", 0);
		int vatAmount = HttpUtil.get(request, "vatAmount", 0);
		
		KakaoPayOrder kakaoPayOrder = new KakaoPayOrder();
		
		kakaoPayOrder.setPartnerOrderId(orderId);
		kakaoPayOrder.setPartnerUserId(userId);
		kakaoPayOrder.setItemCode(itemCode);
		kakaoPayOrder.setItemName(itemName);
		kakaoPayOrder.setQuantity(quantity);
		kakaoPayOrder.setTotalAmount(totalAmount);
		kakaoPayOrder.setTaxFreeAmount(taxFreeAmount);
		kakaoPayOrder.setVatAmount(vatAmount);
		
		KakaoPayReady kakaoPayReady = kakaoPayService.kakaoPayReady(kakaoPayOrder);
		
		if(kakaoPayReady != null)
		{
			logger.debug("[KakaoPayController] payReady : " + kakaoPayReady);
			
			kakaoPayOrder.settId(kakaoPayReady.getTid());
			
			JsonObject json = new JsonObject();
			
			json.addProperty("orderId", orderId);
			json.addProperty("tId", kakaoPayReady.getTid());
			json.addProperty("appUrl", kakaoPayReady.getNext_redirect_app_url());
			json.addProperty("mobileUrl", kakaoPayReady.getNext_redirect_mobile_url());
			json.addProperty("pcUrl", kakaoPayReady.getNext_redirect_pc_url());
			
			ajaxResponse.setResponse(0, "success", json);
		}
		else
		{
			ajaxResponse.setResponse(-1, "fail", null);
		}
		
		return ajaxResponse;
	}
	
	@RequestMapping(value="/kakao/payPopUp", method=RequestMethod.POST)
	public String payPopUp(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		String pcUrl = HttpUtil.get(request, "pcUrl", "");
		String orderId = HttpUtil.get(request, "orderId", "");
		String tId = HttpUtil.get(request, "tId", "");
		String userId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		
		logger.debug("===============================================");
		logger.debug("pcUrl : " + pcUrl);
		logger.debug("===============================================");
		
		model.addAttribute("pcUrl", pcUrl);
		model.addAttribute("orderId", orderId);
		model.addAttribute("tId", tId);
		model.addAttribute("userId", userId);
		
		return "/kakao/payPopUp";
	}
	
	@RequestMapping(value="/kakao/paySuccess")
	public String paySuccess(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		KakaoPayApprove kakaoPayApprove = null;
		
		String pgToken = HttpUtil.get(request, "pg_token", "");
		
		model.addAttribute("pgToken", pgToken);
		
		return "/kakao/paySuccess";
	}
	
	@RequestMapping(value="/kakao/payResult")
	public String payResult(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		KakaoPayApprove kakaoPayApprove = null;
		
		String orderId = HttpUtil.get(request, "orderId", "");
		String tId = HttpUtil.get(request, "tId", "");
		String userId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		String pgToken = HttpUtil.get(request, "pgToken", "");
		
		KakaoPayOrder kakaoPayOrder = new KakaoPayOrder();
		
		kakaoPayOrder.setPartnerOrderId(orderId);
		kakaoPayOrder.setPartnerUserId(userId);
		kakaoPayOrder.settId(tId);
		kakaoPayOrder.setPgToken(pgToken);
		
		kakaoPayApprove = kakaoPayService.kakaoPayApprove(kakaoPayOrder);
		
		model.addAttribute("kakaoPayApprove", kakaoPayApprove);
		
		return "/kakao/payResult";
	}
	
}
