package com.icia.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.icia.common.util.FileUtil;
import com.icia.web.dao.HiBoardDao;
import com.icia.web.model.HiBoard;
import com.icia.web.model.HiBoardFile;

@Service("hiBoardService")
public class HiBoardService 
{
	private static Logger logger = LoggerFactory.getLogger(HiBoardService.class);
			
	//파일 저장 디렉토리
	@Value("#{env['upload.save.dir']}")
	private String UPLOAD_SAVE_DIR;
	
	@Autowired
	private HiBoardDao hiBoardDao;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int boardInsert(HiBoard hiBoard) throws Exception
	{	//Propagation.REQUIRED : 트랜잭션이 있으면 그 트랜젝션에서 실행 없으면 새로운 트랜잭션을 실행(기본설정) 
		int count = hiBoardDao.boardInsert(hiBoard);
		
		//게시물 등록 후 첨부파일이 있으면 첨부파일 등록
		if(count > 0 && hiBoard.getHiBoardFile() != null)
		{
			HiBoardFile hiBoardFile = hiBoard.getHiBoardFile();
			hiBoardFile.setHiBbsSeq(hiBoard.getHiBbsSeq());	
			hiBoardFile.setFileSeq((short)1);
			
			hiBoardDao.boardFileInsert(hiBoard.getHiBoardFile());
			//hiBoardDao.boardFileInsert(hiBoardFile); 
			
		}
		
		return count;
	}
	
	public long boardListCount(HiBoard hiBoard)
	{
		long count = 0;
		
		try
		{
			count = hiBoardDao.boardListCount(hiBoard);
		}
		catch(Exception e)
		{
			logger.error("[HiBoardService]boardListCount Exception ", e);
		}
		
		return count;
	}
	
	public List<HiBoard> boardList(HiBoard hiBoard)
	{
		List<HiBoard> list = null;
		
		try
		{
			list = hiBoardDao.boardList(hiBoard);
		}
		catch(Exception e)
		{
			logger.error("[HiBoardService] boardList Exception", e);
		}
		
		return list;
	}
	
	//게시물 보기(첨부파일 첨부)
	public HiBoard boardView(long hiBbsSeq)
	{
		HiBoard hiBoard = null;
		
		try
		{
			hiBoard = hiBoardDao.boardSelect(hiBbsSeq);
			
			if(hiBoard != null)
			{
				//조회수 증가
				hiBoardDao.boardReadCntPlus(hiBbsSeq);
				//첨부파일 있을 경우 첨부파일 조회	
				HiBoardFile hiBoardFile = hiBoardDao.boardFileSelect(hiBoard.getHiBbsSeq());
				
				if(hiBoardFile != null)
				{
					hiBoard.setHiBoardFile(hiBoardFile);
				}
			}
			
		}
		catch(Exception e)
		{
			logger.error("[HiBoardService] boardView Exception", e);
		}
		
		return hiBoard;
	}
	
	//게시물 조회
	public HiBoard boardSelect(long hiBbsSeq)
	{
		HiBoard hiBoard = null;
		
		try
		{
			hiBoard = hiBoardDao.boardSelect(hiBbsSeq);
		}
		
		catch(Exception e)
		{
			logger.error("[HiBoardService] boardSelect Exception", e);
		}
		
		return hiBoard;
	}
	
	//답글 등록
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public int boardReplyInsert(HiBoard hiBoard) throws Exception
	{
		int count = 0;
		hiBoardDao.boardGroupOrderUpdate(hiBoard);
		
		count = hiBoardDao.boardReplyInsert(hiBoard);
		
		//첨부파일이 있는 경우 등록
		if(count > 0 && hiBoard.getHiBoardFile() != null)
		{
			HiBoardFile hiBoardFile = hiBoard.getHiBoardFile();
			hiBoardFile.setHiBbsSeq(hiBoard.getHiBbsSeq());
			hiBoardFile.setFileSeq((short)1);
			
			hiBoardDao.boardFileInsert(hiBoard.getHiBoardFile());;
		}
		
		
		return count; 
	}
	
	
	//첨부파일 조회
	public HiBoardFile boardFileSelect(long hiBbsSeq)
	{
		HiBoardFile hiBoardFile = null;
		try 
		{
			hiBoardFile = hiBoardDao.boardFileSelect(hiBbsSeq);
		}
		catch(Exception e)
		{
			logger.error("[HiBoardService] boardFileSelect Exception", e);
		}
		
		return hiBoardFile;
	}
	
	//게시물 삭제시 답변 글수 조회
	public int boardAnswersCount(long hiBbsSeq)
	{
		int count = 0;
		
		try
		{
			count= hiBoardDao.boardAnswersCount(hiBbsSeq);
		}
		catch(Exception e)
		{
			logger.error("[HiBoardService] boardAnswersCount Exception",e);
		}
		
		return count;
	}
	
	//게시물 삭제 (첨부파일 존재시 첨부파일 삭제 포함)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int boardDelete(long hiBbsSeq) throws Exception
	{
		int count = 0;
		
		HiBoard hiBoard = hiBoardDao.boardSelect(hiBbsSeq);
			
		if(hiBoard != null)
		{
			count = hiBoardDao.boardDelete(hiBbsSeq);
			
			if(count > 0)
			{
				HiBoardFile hiBoardFile = hiBoardDao.boardFileSelect(hiBbsSeq);
				
				if(hiBoardFile != null)
				{
					if(hiBoardDao.boardFileDelete(hiBbsSeq) > 0)
					{
						FileUtil.deleteFile(UPLOAD_SAVE_DIR + FileUtil.getFileSeparator() + hiBoardFile.getFileName());
						
						logger.debug("#############################################################");
						logger.debug("UPLOAD_SAVE_DIR : " + UPLOAD_SAVE_DIR);
						logger.debug("FileUtil.getFileSeparator() : " + FileUtil.getFileSeparator());
						logger.debug("hiBoardFile.getFileName() : " + hiBoardFile.getFileName());
						logger.debug("#############################################################");
						
					}
				}
			}
		}
		
		return count;
	}
	
	
	
	//게시물 보기 수정 페이지
	public HiBoard boardSelectView(long hiBbsSeq)
	{
		HiBoard hiBoard = null;
		
		try
		{
			hiBoard = hiBoardDao.boardSelect(hiBbsSeq);
			if(hiBoard != null)
			{
				HiBoardFile hiBoardFile = hiBoardDao.boardFileSelect(hiBoard.getHiBbsSeq());
				
				if(hiBoardFile != null)
				{
					hiBoard.setHiBoardFile(hiBoardFile);
				}
			}
		}
		catch(Exception e)
		{
			logger.error("[HiBoardService] boardSelectView Exception", e);
		}
		
		return hiBoard;
	}
	
	//게시물 수정
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int boardUpdate(HiBoard hiBoard) throws Exception
	{
		int count = hiBoardDao.boardUpdate(hiBoard);
		
		if(count > 0 && hiBoard.getHiBoardFile() != null)
		{
			HiBoardFile delHiBoardFile = hiBoardDao.boardFileSelect(hiBoard.getHiBbsSeq());
			
			//기존 파일이 있는 경우 삭제
			if(delHiBoardFile != null)
			{
				FileUtil.deleteFile(UPLOAD_SAVE_DIR + FileUtil.getFileSeparator() + delHiBoardFile.getFileName());
				
				hiBoardDao.boardFileDelete(hiBoard.getHiBbsSeq());
			}
			
			HiBoardFile hiBoardFile = hiBoard.getHiBoardFile();
			
			hiBoardFile.setHiBbsSeq(hiBoard.getHiBbsSeq());
			hiBoardFile.setFileSeq((short)1);
			
			hiBoardDao.boardFileInsert(hiBoard.getHiBoardFile());
			
		}
		
		return count;
	}
}


