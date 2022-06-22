package in.raunaq.expensetrackerapi.service;

import java.util.Date;
import java.util.List;

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
	
	List<Expense> readByCategory(String category, Pageable page);
	
	List<Expense> readByName(String name, Pageable page);
	
	List<Expense> readByDate(Date startDate, Date endDate, Pageable page);
}
