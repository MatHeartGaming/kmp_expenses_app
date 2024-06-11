package data

import domain.ExpenseRepository
import domain.model.Expense
import domain.model.ExpenseCategory

class ExpenseRepoImpl(private val db: ExpenseManager): ExpenseRepository {

    override fun getAllExpenses(): List<Expense> {
        return db.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        db.addNewExpense(expense)
    }

    override fun editExpense(expense: Expense) {
        db.editExpense(expense)
    }

    override fun getCategories(): List<ExpenseCategory> {
        return db.getCategories()
    }

    override fun deleteExpense(expense: Expense) {
        db.deleteExpense(expense)
    }
}