// Interface
package com.AJP.demo1.service;

import com.AJP.demo1.entity.IssueRecord;
import java.util.List;
import java.util.Optional;

public interface IssueRecordService {
    List<IssueRecord> findAllIssueRecords();
    Optional<IssueRecord> findIssueRecordById(Long id);
    IssueRecord saveIssueRecord(IssueRecord issueRecord);
    void deleteIssueRecordById(Long id);
    IssueRecord processReturn(Long id); // Method to mark a book as returned
}