package com.icia.web.dao;

import org.springframework.stereotype.Repository;

import com.icia.web.model.Product;
import com.icia.web.model.ProductFile;


@Repository("shopDao")
public interface ShopDao {
	
	//상품 등록
	public int productInsert(Product product);
	
	//상품 첨부파일 등록
	public int productFileInsert(ProductFile productFile);
	

}
