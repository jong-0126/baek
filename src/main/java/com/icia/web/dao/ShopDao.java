package com.icia.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.web.model.Product;
import com.icia.web.model.ProductFile;


@Repository("shopDao")
public interface ShopDao {
	
	//상품 등록
	public int productInsert(Product product);
	
	//상품 첨부파일 등록
	public int productFileInsert(ProductFile productFile);
	
	
	//상품 조회
	public long allProductCount(Product product);
	
	//모든상품 조회
	public List<Product> allProductList(Product product);
	
	//상품 파일 조회
	public ProductFile productFileSelect (long productNo);	
	
	//상품 넘버 조회
	public long productNoSelect (Product product);

}
