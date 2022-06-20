package in.raunaq.expensetrackerapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.raunaq.expensetrackerapi.entity.Expense;

@Service
public interface ExpenseService {

	Page<Expense> getAllExpenses(Pageable page);

	Expense getExpenseById(Long id);

	void deleteExpenseById(Long id);

	Expense saveExpense(Expense expense);

	Expense updateExpenseDetails(Long id, Expense expense);
}
