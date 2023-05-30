package com.icia.web.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.icia.web.dao.ShopDao;
import com.icia.web.model.Product;
import com.icia.web.model.ProductFile;



@Service("shopService")
public class ShopService {
	
	//파일 저장 디렉토리
	@Value("#{env['upload.save.dir']}")
	private String UPLOAD_SAVE_DIR;
	
	@Autowired
	private ShopDao shopDao;
	
	private static Logger logger = LoggerFactory.getLogger(ShopService.class);
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int productInsert(Product product) throws Exception
	{	//Propagation.REQUIRED : 트랜잭션이 있으면 그 트랜젝션에서 실행 없으면 새로운 트랜잭션을 실행(기본설정) 
		int count = shopDao.productInsert(product);
		
		//게시물 등록 후 첨부파일이 있으면 첨부파일 등록
		if(count > 0 && product.getProductFile() != null)
		{
			ProductFile productFile = product.getProductFile();
			productFile.setProductNo(product.getProductNo());	
			productFile.setFileSeq((short)1);
			
			shopDao.productFileInsert(product.getProductFile());
			//hiBoardDao.boardFileInsert(hiBoardFile); 
			
		}
		
		return count;
	}
	
	//상품 조회
	public long allProductCount(Product product)
	{
		long count = 0;
		
		try
		{
			count = shopDao.allProductCount(product);
		}
		catch(Exception e)
		{
			logger.error("[ShopService] allProductCount Exception", e);
		}
		return count;
	}
	
	//모든 상품 조회
	public List<Product> allProductList(Product product)
	{
		List<Product> list = null;
		
		try
		{
			list = shopDao.allProductList(product);
		}
		catch(Exception e)
		{
			logger.error("[ProductService] allProductList Exception", e);
		}
		
		return list;
	}
	
	//상품 파일 조회
	public ProductFile productFileSelect(long productNo)
	{
		ProductFile productFile = null;

		
		try {
			
			productFile = shopDao.productFileSelect(productNo);
			
		} catch (Exception e) {
			logger.error("[ShopService] productFileSelect Exception", e);
		
		
		}
		
		return productFile;
	}
	
	
}
