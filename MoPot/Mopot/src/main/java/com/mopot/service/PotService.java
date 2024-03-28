package com.mopot.service;

import com.mopot.domain.Content;
import com.mopot.domain.PotContent;
import com.mopot.repository.PotRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PotService {

    @Autowired
    PotRepository potRepository;
    
    /* 폼 입력하기 페이지 */
	public PotContent insert(PotContent potContent) {
		return potRepository.save(potContent);
	}
	
	/* 폼 수정하기  */
	public PotContent update(PotContent potContent) {
		PotContent rPotcontent = potRepository.findById(potContent.getPotNo()).orElse(null);
        rPotcontent.setPotTitle(potContent.getPotTitle());
        rPotcontent.setPotDetail(potContent.getPotDetail());
        rPotcontent.setPotWriter(potContent.getPotWriter());
        rPotcontent.setPotTag(potContent.getPotTag());
        rPotcontent.setPotCategory(potContent.getPotCategory());
        return potRepository.save(rPotcontent);
    }
	
	/* 폼 삭제하기 */
	public void potContentDelete(long potNo) {
		potRepository.deleteById((long) potNo);
		
	}
	
	/* 폼 출력하기 */
	public Page<PotContent> potList(Pageable pageable) {
		return potRepository.findAll(pageable);
    }

	public List<PotContent> findPotsByCategory(String potCategory){        
		// 카테고리에 따라 데이터베이스에서 데이터 조회
        return potRepository.findByPotCategory(potCategory);
    }
	
	
	
	
}
