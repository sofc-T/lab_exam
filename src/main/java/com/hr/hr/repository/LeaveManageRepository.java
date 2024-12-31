package com.hr.hr.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hr.hr.models.LeaveDetails;

@Repository(value = "leaveManageRepository")
public interface LeaveManageRepository extends JpaRepository<LeaveDetails, Serializable> {

    // MySQL-compatible version
    @Query(nativeQuery = true, value = "SELECT CONCAT(employee_name, ' on leave') AS title, DATE_FORMAT(from_date, '%Y-%m-%d') AS start, DATE_FORMAT(to_date, '%Y-%m-%d') AS end FROM leave_details")
    public List<Object> getAllLeavesAsJsonArray();

    // Query for active leaves (MySQL-compatible)
    @Query(nativeQuery = true, value = "SELECT * FROM leave_details WHERE active=true")
    public List<com.hr.hr.models.LeaveDetails> getAllActiveLeaves();

    // Query for leaves of a specific user (MySQL-compatible)
    @Query(nativeQuery = true, value = "SELECT * FROM leave_details WHERE username=? ORDER BY id DESC")
    public List<com.hr.hr.models.LeaveDetails> getAllLeavesOfUser(String username);
}
