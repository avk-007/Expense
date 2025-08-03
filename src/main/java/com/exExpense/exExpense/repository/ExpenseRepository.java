
package com.exExpense.exExpense.repository;

import com.exExpense.exExpense.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findAllByTitleContainingIgnoreCase(String keyword, Pageable pageable);
}
