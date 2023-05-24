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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.icia.common.model.FileData;
import com.icia.common.util.StringUtil;
import com.icia.web.model.HiBoard;
import com.icia.web.model.HiBoardFile;
import com.icia.web.model.Product;
import com.icia.web.model.ProductFile;
import com.icia.web.model.Response;
import com.icia.web.model.User;
import com.icia.web.service.ShopService;
import com.icia.web.service.UserService;
import com.icia.web.util.CookieUtil;
import com.icia.web.util.HttpUtil;

@Controller("shopController")
public class ShopController {
	private static Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	//쿠키명
	@Value("#{env['auth.cookie.name']}")
	private String AUTH_COOKIE_NAME;
	
	//파일 저장 경로 
	@Value("#{env['upload.save.dir']}")
	private String UPLOAD_SAVE_DIR;
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private UserService userService;
	
	
	//강아지 페이지
	@RequestMapping(value="/shop/dogMain")
	public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		
		return "/shop/dogMain";
	
	}
	
	//상품 상세 정보 페이지
	@RequestMapping(value="/shop/detail")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		
		return "/shop/detail";
	
	}
	
	//상품 등록 페이지
	@RequestMapping(value="/shop/productForm")
	public String productForm(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);

		User user = userService.userSelect(cookieUserId);
		model.addAttribute("user", user);
		
		return "/shop/productForm";
	}
	
	//상품 등록
	@RequestMapping(value = "/shop/productProc", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> productProc(MultipartHttpServletRequest request, HttpServletResponse response)
	{
		Response<Object> ajaxResponse = new Response<Object>();
		
		
		String productName = HttpUtil.get(request, "productName", "");
		String productPrice = HttpUtil.get(request, "productPrice", "");
		String productContent = HttpUtil.get(request, "productContent", ""); 
		
		FileData fileData = HttpUtil.getFile(request, "productFile", UPLOAD_SAVE_DIR);	
		
		if(!StringUtil.isEmpty(productName) && !StringUtil.isEmpty(productPrice) && !StringUtil.isEmpty(productContent))
		{
			Product product = new Product();
			
			product.setProductName(productName);
			product.setProductPrice(productPrice);
			product.setProductContent(productContent);
			
		
			if(fileData != null && fileData.getFileSize() > 0)
			{
				ProductFile productFile = new ProductFile();
				
				productFile.setFileName(fileData.getFileName());
				productFile.setFileOrgName(fileData.getFileOrgName());
				productFile.setFileExt(fileData.getFileExt());
				productFile.setFileSize(fileData.getFileSize());
				
				product.setProductFile(productFile);
			}
			
			try
			{
				if(shopService.productInsert(product) > 0)
				{
					ajaxResponse.setResponse(0, "Success");
				}
				else
				{
					ajaxResponse.setResponse(500, "ubterbak server error");
				}
			}
			catch(Exception e)
			{	
				logger.error("[ShopController] /shop/productProc Exception" ,e);
				ajaxResponse.setResponse(500, "internal server error");
			}
		}
		else
		{
			ajaxResponse.setResponse(400, "board request");
		}
		
		return ajaxResponse;
	}
}
