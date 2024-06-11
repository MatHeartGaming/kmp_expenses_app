import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.TitleTopBarTypes
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    PreComposeApp {
        val colors = getColorsTheme()
        AppTheme {
            val navigator = rememberNavigator()
            val titleTopBar = getTitleTopAppBar(navigator)
            val isEditOrAddExpenses = titleTopBar != TitleTopBarTypes.DASHBOARD.value
            Scaffold(
                Modifier.fillMaxSize(),
                backgroundColor = colors.backgroundColor,
                floatingActionButton = {
                    if (!isEditOrAddExpenses) {
                        FloatingActionButton(
                            onClick = {
                                navigator.navigate("/addExpenses")
                            },
                            modifier = Modifier.padding(8.dp),
                            shape = RoundedCornerShape(50),
                            backgroundColor = colors.addIconColor,
                            contentColor = Color.White,
                            ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                tint = Color.White,
                                contentDescription = "Floating Icon"
                            )
                        }
                    }
                },
                topBar = {
                    TopAppBar(elevation = 0.dp,
                        title = {
                        Text(text = titleTopBar,
                            fontSize = 25.sp,
                            color = colors.textColor)
                    },
                        backgroundColor = colors.backgroundColor,
                        navigationIcon = {
                            IconButton(onClick = {
                                if(isEditOrAddExpenses) navigator.popBackStack()
                            },) {
                                Icon(modifier = Modifier.padding(start = 16.dp),
                                    imageVector = if(!isEditOrAddExpenses) Icons.Default.Apps else Icons.AutoMirrored.Filled.ArrowBack,
                                    tint = colors.textColor,
                                    contentDescription = if(!isEditOrAddExpenses) "Dashboard Icon" else "Arrow Back"
                                )
                            }
                        }
                    )}
            ) {
                Navigation(navigator)
            }
        }
    }
}

@Composable
fun getTitleTopAppBar(navigator: Navigator): String {
    var titleTopBar = TitleTopBarTypes.DASHBOARD
    val isOnAddExpenses = navigator.currentEntry.collectAsState(null).value?.route?.route.equals("/addExpenses/{id}?")
    if (isOnAddExpenses) titleTopBar = TitleTopBarTypes.ADD
    val isOnEditExpenses = navigator.currentEntry.collectAsState(null).value?.path<Long>("id")
    isOnEditExpenses?.let {
        titleTopBar = TitleTopBarTypes.EDIT
    }
    return titleTopBar.value
}