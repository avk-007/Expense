
package com.exExpense.exExpense.controller;

import com.exExpense.exExpense.dto.ExpenseRequestDTO;
import com.exExpense.exExpense.dto.ExpenseResponseDTO;
import com.exExpense.exExpense.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> create(@Valid @RequestBody ExpenseRequestDTO dto) {
        return new ResponseEntity<>(expenseService.createExpense(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpense(id));
    }

    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(expenseService.searchExpenses(keyword, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> update(@PathVariable Long id,
                                                     @Valid @RequestBody ExpenseRequestDTO dto) {
        return ResponseEntity.ok(expenseService.updateExpense(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
