package previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import data.ExpenseManager
import presentation.ExpensesUiState
import ui.AllExpensesHeader
import ui.ExpensesItem
import ui.ExpensesScreen
import ui.ExpensesTotalHeader

@Preview(showBackground = true)
@Composable
private fun ExpensesScreenHeaderPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        ExpensesTotalHeader(total = 2019.20)
    }
}

@Preview(showBackground = true)
@Composable
private fun AllExpensesScreenPreview() {
    AllExpensesHeader()
}

@Preview(showBackground = true)
@Composable
fun ExpenseItemPreview() {
    Box(Modifier.padding(8.dp)) {
        ExpensesItem(
            expense = ExpenseManager.fakeExpenseList.first()) {
            
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseScreenPreview() {
    ExpensesScreen(
        uiState = ExpensesUiState(
            expense = ExpenseManager.fakeExpenseList,
            total = 1051.4)
    ) {

    }
}