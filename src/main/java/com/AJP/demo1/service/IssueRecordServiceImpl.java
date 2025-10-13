// Implementation
package com.AJP.demo1.service;

import com.AJP.demo1.entity.IssueRecord;
import com.AJP.demo1.repository.IssueRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IssueRecordServiceImpl implements IssueRecordService {

    private final IssueRecordRepository issueRecordRepository;
    // Add BookService/UserRepository if complex logic is needed, but omitted for simplicity

    @Autowired
    public IssueRecordServiceImpl(IssueRecordRepository issueRecordRepository) {
        this.issueRecordRepository = issueRecordRepository;
    }

    @Override
    public List<IssueRecord> findAllIssueRecords() {
        return issueRecordRepository.findAll();
    }

    @Override
    public Optional<IssueRecord> findIssueRecordById(Long id) {
        return issueRecordRepository.findById(id);
    }

    @Override
    public IssueRecord saveIssueRecord(IssueRecord issueRecord) {
        if (issueRecord.getIssueDate() == null) {
            issueRecord.setIssueDate(LocalDate.now());
        }
        // Set due date to 14 days from issue
        if (issueRecord.getDueDate() == null) {
            issueRecord.setDueDate(issueRecord.getIssueDate().plusDays(14));
        }
        return issueRecordRepository.save(issueRecord);
    }

    @Override
    public void deleteIssueRecordById(Long id) {
        issueRecordRepository.deleteById(id);
    }

    @Override
    public IssueRecord processReturn(Long id) {
        IssueRecord record = issueRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid issue record Id:" + id));

        if (record.getReturnDate() == null) {
            record.setReturnDate(LocalDate.now());
            // In a real app, you would also increment the book quantity here
            issueRecordRepository.save(record);
        }
        return record;
    }
}