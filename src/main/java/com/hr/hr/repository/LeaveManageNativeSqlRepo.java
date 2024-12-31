package com.hr.hr.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hr.hr.models.LeaveDetails;

@Repository
public class LeaveManageNativeSqlRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<LeaveDetails> getAllLeavesOnStatus(String status) {
        String sql = "SELECT * FROM leave_details WHERE status = :status";
        Query query = entityManager.createNativeQuery(sql, LeaveDetails.class);
        query.setParameter("status", status);  

        try {
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error fetching leave details: " + e.getMessage());
            throw e;
        }
    }
}
