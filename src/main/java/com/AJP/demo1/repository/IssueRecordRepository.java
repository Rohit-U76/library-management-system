package com.AJP.demo1.repository;

import com.AJP.demo1.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRecordRepository extends JpaRepository<IssueRecord, Long> {
}