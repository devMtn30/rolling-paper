package com.memorymakerpeople.memoryrollingpaper.repository;

import com.memorymakerpeople.memoryrollingpaper.domain.Card;
import com.memorymakerpeople.memoryrollingpaper.domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findByPaperId(int paperId); //아아디로 된 페이퍼 모두 찾기
}
