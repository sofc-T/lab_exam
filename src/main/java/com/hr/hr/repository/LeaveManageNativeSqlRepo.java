package com.hr.hr.repository;

import java.util.List;

// import javax.persistence.EntityManager;
// import javax.persistence.PersistenceContext;
// import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.hr.hr.models.LeaveDetails;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class LeaveManageNativeSqlRepo {

    @PersistenceContext
    EntityManager entityManager;
    @SuppressWarnings("unchecked")
    public List<LeaveDetails> getAllLeavesOnStatus(String status) {

        
        String sql = "SELECT * FROM leave_details WHERE status = :status";
        Query query = entityManager.createNativeQuery(sql, LeaveDetails.class);
        query.setParameter("status", status);  

        return query.getResultList();
    }
}
