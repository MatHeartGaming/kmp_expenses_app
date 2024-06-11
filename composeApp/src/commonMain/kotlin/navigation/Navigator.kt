package navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import data.ExpenseManager
import data.ExpenseRepoImpl
import getColorsTheme
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import presentation.ExpensesViewModel
import ui.ExpensesDetailScreen
import ui.ExpensesScreen

@Composable
fun Navigation(navigator: Navigator) {
    val colors = getColorsTheme()
    val viewModel = viewModel(modelClass = ExpensesViewModel::class) {
        ExpensesViewModel(ExpenseRepoImpl(ExpenseManager))
    }
    NavHost(
        modifier = Modifier.background(colors.backgroundColor),
        navigator = navigator,
        initialRoute = "/home"
        ) {
        scene(route = "/home") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(
                uiState = uiState,
                onExpenseClicked = { expense ->
                    navigator.navigate("/addExpenses/${expense.id}")
                })
        }

        scene(route = "/addExpenses/{id}?") { it ->
            val idFromPath = it.path<Long>("id")
            val expenseToEditOrAdd = idFromPath?.let {id ->
                viewModel.getExpenseWithId(id)
            }
            ExpensesDetailScreen(expenseToEdit = expenseToEditOrAdd,
                categoryList = viewModel.getCategories()) {exp ->
                if(expenseToEditOrAdd == null) {
                    viewModel.addExpense(exp)
                } else {
                    viewModel.editExpense(exp)
                }
                navigator.popBackStack()
            }
        }
    }
}