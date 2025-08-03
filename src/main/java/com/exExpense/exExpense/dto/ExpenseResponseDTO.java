
package com.exExpense.exExpense.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ExpenseResponseDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal amount;
    private LocalDate expenseDate;
}
