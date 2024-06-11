package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.ExpenseManager
import domain.model.Expense
import getColorsTheme
import presentation.ExpensesUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpensesScreen(uiState: ExpensesUiState, onExpenseClicked: (expense: Expense)-> Unit) {
    val colors = getColorsTheme()
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stickyHeader {
            Column(modifier = Modifier.background(colors.backgroundColor)) {
                ExpensesTotalHeader(uiState.total)
                AllExpensesHeader()
            }
        }
        items(uiState.expense) {exp ->
            ExpensesItem(exp, onExpenseClicked = {
                onExpenseClicked(it)
            })
        }
    }
}

@Composable
fun ExpensesTotalHeader(total: Double) {
    Card(shape = RoundedCornerShape(30), backgroundColor = Color.Black, elevation = 5.dp) {
        Box(
            Modifier.fillMaxWidth().height(130.dp).padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = "$total", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
            Text(modifier =  Modifier.align(Alignment.CenterEnd), text = "EUR", color = Color.Gray)
        }
    }
}

@Composable
fun AllExpensesHeader() {
    val colors = getColorsTheme()
    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.weight(1f),
            text = "All Expenses",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            color = colors.textColor,)

        Button(
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
            enabled = false,
            onClick = {

            }) {
            Text("View All")
        }
    }
}

@Composable
fun ExpensesItem(expense: Expense, onExpenseClicked: (expense: Expense)-> Unit) {
    val colors = getColorsTheme()
    Card(modifier = Modifier.clickable {
        onExpenseClicked(expense)
    },
        backgroundColor = colors.colorExpenseItem,
        shape = RoundedCornerShape(30),) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(35),
                color = colors.purple
            ) {
                Image(
                    modifier = Modifier.padding(10.dp),
                    imageVector = expense.icon,
                    colorFilter = ColorFilter.tint(Color.White),
                    contentScale = ContentScale.Fit,
                    contentDescription = "Imagen Icono de la expensa"
                )
            }
            Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                Text(
                    text = expense.category.name,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = colors.textColor,
                )
                Text(
                    text = expense.description,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Color.Gray,
                )
            }
            Text(
                text = "€${expense.amount}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colors.textColor,
            )
        }
    }
}