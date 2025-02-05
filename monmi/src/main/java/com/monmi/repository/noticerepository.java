package com.monmi.repository;

import com.monmi.domian.notice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface noticerepository extends JpaRepository<notice, Long> {
    Optional<notice> findByTitle(String title);
}