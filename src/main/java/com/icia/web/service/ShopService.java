package com.icia.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.icia.web.dao.HiBoardDao;
import com.icia.web.dao.ShopDao;
import com.icia.web.model.HiBoard;
import com.icia.web.model.HiBoardFile;
import com.icia.web.model.Product;
import com.icia.web.model.ProductFile;

@Service("shopService")
public class ShopService {
	
	//파일 저장 디렉토리
	@Value("#{env['upload.save.dir']}")
	private String UPLOAD_SAVE_DIR;
	
	@Autowired
	private ShopDao shopDao;
	
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
	
	
	
}
