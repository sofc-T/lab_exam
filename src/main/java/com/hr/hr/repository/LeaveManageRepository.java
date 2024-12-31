package com.hr.hr.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hr.hr.models.LeaveDetails;

@Repository(value = "leaveManageRepository")
public interface LeaveManageRepository extends JpaRepository<LeaveDetails, Serializable> {

    @Query(nativeQuery = true, value = "SELECT CONCAT(employee_name, ' on leave') AS title, DATE_FORMAT(from_date, '%Y-%m-%d') AS start, DATE_FORMAT(to_date, '%Y-%m-%d') AS end FROM leave_details")
    public List<Object> getAllLeavesAsJsonArray();

    @Query(nativeQuery = true, value = "SELECT * FROM leave_details WHERE active=true")
    public List<LeaveDetails> getAllActiveLeaves();

    @Query(nativeQuery = true, value = "SELECT * FROM leave_details WHERE username=? ORDER BY id DESC")
    public List<LeaveDetails> getAllLeavesOfUser(String username);

    // MySQL query for find one by id
    @Query(nativeQuery = true, value = "SELECT * FROM leave_details WHERE id=?")
    public LeaveDetails findOneById(int id);

    // MySQL query for dynamic status filtering (pending, accepted, rejected)
    @Query(nativeQuery = true, value = "SELECT * FROM leave_details WHERE " +
            "(active = true AND :pending = true) OR " +
            "(active = false AND accept_reject_flag = true AND :accepted = true) OR " +
            "(active = false AND accept_reject_flag = false AND :rejected = true)")
    public List<LeaveDetails> getAllLeavesOnStatus(boolean pending, boolean accepted, boolean rejected);
}
