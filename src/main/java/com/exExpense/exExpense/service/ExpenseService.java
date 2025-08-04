
package com.exExpense.exExpense.service;

import com.exExpense.exExpense.dto.ExpenseRequestDTO;
import com.exExpense.exExpense.dto.ExpenseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {
    ExpenseResponseDTO createExpense(ExpenseRequestDTO dto); //This defines a contract for your service
    ExpenseResponseDTO getExpense(Long id);
    Page<ExpenseResponseDTO> searchExpenses(String keyword, Pageable pageable);
    ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO dto);
    void deleteExpense(Long id);
}
