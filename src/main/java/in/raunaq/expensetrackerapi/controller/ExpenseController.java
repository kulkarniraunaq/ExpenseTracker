package in.raunaq.expensetrackerapi.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.raunaq.expensetrackerapi.entity.Expense;
import in.raunaq.expensetrackerapi.service.ExpenseService;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	// Get all expenses
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/expenses")
	public List<Expense> getAllExpenses(Pageable page) {
		return expenseService.getAllExpenses(page).toList();
	}

	// Get expense by id
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/expenses/{id}")
	public Expense getExpenseById(@PathVariable Long id) {
		return expenseService.getExpenseById(id);
	}

	// Delete expense by id
	
	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping("/expenses")
	public void deleteExpenseById(@RequestParam Long id) {
		expenseService.deleteExpenseById(id);
	}

	// Save new expense
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/expenses")
	public Expense saveExpense(@Valid @RequestBody Expense expense) {
		return expenseService.saveExpense(expense);
	}

	// Update expense by id
	
	@PatchMapping("/expenses/{id}")
	public Expense updateExpenseById(@Valid @PathVariable Long id, @RequestBody Expense expense) {
		return expenseService.updateExpenseDetails(id, expense);
	}

	// Filter expense by category
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/expenses/category")
	public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page) {
		return expenseService.readByCategory(category, page);
	}

	// Filter expnese by name
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/expenses/name")
	public List<Expense> getExpensesByName(@RequestParam String name, Pageable page) {
		return expenseService.readByName(name, page);
	}
	
	// Filter expense by startDate and endDate
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/expenses/date")
	public List<Expense> getExpensesByDates(@RequestParam(required = false) Date startDate,
			@RequestParam(required = false) Date endDate, Pageable page) {
		return expenseService.readByDate(startDate, endDate, page);
	}

}