package in.raunaq.expensetrackerapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.raunaq.expensetrackerapi.entity.Expense;
import in.raunaq.expensetrackerapi.exceptions.ResourceNotFoundException;
import in.raunaq.expensetrackerapi.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public Page<Expense> getAllExpenses(Pageable page) {
		return expenseRepository.findAll(page);
	}

	@Override
	public Expense getExpenseById(Long id) {
		Optional<Expense> expense = expenseRepository.findById(id);
		{
			if (expense.isPresent()) {
				return expense.get();
			}
			throw new ResourceNotFoundException("Expense not found for id " + id);
		}
	}

	@Override
	public void deleteExpenseById(Long id) {
		Optional<Expense> expense = expenseRepository.findById(id);
		{
			if (expense.isPresent()) {
				expenseRepository.deleteById(id);
			} else {
				throw new ResourceNotFoundException("Expense not found for id " + id);
			}
		}
	}

	@Override
	public Expense saveExpense(Expense expense) {
		return expenseRepository.save(expense);
	}

	@Override
	public Expense updateExpenseDetails(Long id, Expense expense) {
		Expense existingExpense = getExpenseById(id);
		existingExpense.setName(expense.getName() != null ? expense.getName() : existingExpense.getName());
		existingExpense.setDescription(
				expense.getDescription() != null ? expense.getDescription() : existingExpense.getDescription());
		existingExpense.setAmount(expense.getAmount() != null ? expense.getAmount() : existingExpense.getAmount());
		existingExpense
				.setCategory(expense.getCategory() != null ? expense.getCategory() : existingExpense.getCategory());
		existingExpense.setDate(expense.getDate() != null ? expense.getDate() : existingExpense.getDate());
		return expenseRepository.save(existingExpense);
	}

}
