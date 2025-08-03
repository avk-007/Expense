
package com.exExpense.exExpense.service.impl;

import com.exExpense.exExpense.dto.ExpenseRequestDTO;
import com.exExpense.exExpense.dto.ExpenseResponseDTO;
import com.exExpense.exExpense.entity.Expense;
import com.exExpense.exExpense.exception.ResourceNotFoundException;
import com.exExpense.exExpense.repository.ExpenseRepository;
import com.exExpense.exExpense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private ExpenseResponseDTO mapToDTO(Expense entity) {
        return ExpenseResponseDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .amount(entity.getAmount())
                .expenseDate(entity.getExpenseDate())
                .build();
    }

    @Override
    public ExpenseResponseDTO createExpense(ExpenseRequestDTO dto) {
        Expense expense = new Expense();
        BeanUtils.copyProperties(dto, expense);
        Expense saved = expenseRepository.save(expense);
        return mapToDTO(saved);
    }

    @Override
    public ExpenseResponseDTO getExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
        return mapToDTO(expense);
    }

    @Override
    public Page<ExpenseResponseDTO> searchExpenses(String keyword, Pageable pageable) {
        return expenseRepository
                .findAllByTitleContainingIgnoreCase(keyword, pageable)
                .map(this::mapToDTO);
    }

    @Override
    public ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO dto) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
        expense.setTitle(dto.getTitle());
        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setExpenseDate(dto.getExpenseDate());
        return mapToDTO(expenseRepository.save(expense));
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
