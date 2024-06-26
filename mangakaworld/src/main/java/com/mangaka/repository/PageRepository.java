package com.mangaka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangaka.model.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
}
