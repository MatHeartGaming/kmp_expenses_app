package data

import domain.model.Expense
import domain.model.ExpenseCategory

object ExpenseManager {
    private var currentId = 1L
    val fakeExpenseList = mutableListOf(
        Expense(id = currentId++, amount = 200.0, category = ExpenseCategory.SNACKS, description = "Weekly Buy"),
        Expense(id = currentId++, amount = 178.90, category = ExpenseCategory.GROCERIES, description = "Vegetables"),
        Expense(id = currentId++, amount = 23178.0, category = ExpenseCategory.CAR, description = "Audi A5"),
        Expense(id = currentId++, amount = 45.0, category = ExpenseCategory.PARTY, description = "Botellon"),
        Expense(id = currentId++, amount = 35.0, category = ExpenseCategory.HOUSE, description = "Clening"),
        Expense(id = currentId++, amount = 55.50, category = ExpenseCategory.OTHER, description = "Services"),
    )

    fun addNewExpense(expense: Expense) {
        fakeExpenseList.add(expense.copy(id = currentId++))
    }

    fun editExpense(expense: Expense) {
        val index = fakeExpenseList.indexOfFirst {
            it.id == expense.id
        }
        if (index == -1) return
        fakeExpenseList[index] = fakeExpenseList[index].copy(
            amount = expense.amount,
            description = expense.description,
            category = expense.category,
        )
    }

    fun deleteExpense(expense: Expense) {
        val index = fakeExpenseList.indexOfFirst {
            it.id == expense.id
        }
        if (index == -1) return
        fakeExpenseList.removeAt(index)
    }

    fun getCategories(): List<ExpenseCategory> {
        return ExpenseCategory.entries
    }

}