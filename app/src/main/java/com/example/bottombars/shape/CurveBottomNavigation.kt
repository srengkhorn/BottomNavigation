package com.example.bottombars.shape

import android.graphics.PointF
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.bottombars.R
import com.example.bottombars.animation.AnimatedBottomNavigation1
import com.example.bottombars.ui.theme.FontSize
import com.example.bottombars.ui.theme.Purple40
import com.example.bottombars.ui.theme.spacing

private const val CURVE_CIRCLE_RADIUS = 96

// the coordinates of the first curve
private val mFirstCurveStartPoint = PointF()
private val mFirstCurveControlPoint1 = PointF()
private val mFirstCurveControlPoint2 = PointF()
private val mFirstCurveEndPoint = PointF()

private val mSecondCurveControlPoint1 = PointF()
private val mSecondCurveControlPoint2 = PointF()
private var mSecondCurveStartPoint = PointF()
private var mSecondCurveEndPoint = PointF()

class CurveShape : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        return Outline.Generic(path = Path().apply {
            val curveDepth = CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4F)
            // the coordinates (x,y) of the start point before curve
            mFirstCurveStartPoint.set(
                (size.width / 2) - (CURVE_CIRCLE_RADIUS * 2) - (CURVE_CIRCLE_RADIUS / 3),
                curveDepth
            )

            // the coordinates (x,y) of the end point after curve
            mFirstCurveEndPoint.set(
                size.width / 2,
                0F
            )

            // same thing for the second curve
            mSecondCurveStartPoint = mFirstCurveEndPoint;
            mSecondCurveEndPoint.set(
                (size.width / 2) + (CURVE_CIRCLE_RADIUS * 2) + (CURVE_CIRCLE_RADIUS / 3),
                curveDepth
            )

            // the coordinates (x,y)  of the 1st control point on a cubic curve
            mFirstCurveControlPoint1.set(
                mFirstCurveStartPoint.x + curveDepth,
                mFirstCurveStartPoint.y
            )

            // the coordinates (x,y)  of the 2nd control point on a cubic curve
            mFirstCurveControlPoint2.set(
                mFirstCurveEndPoint.x - (CURVE_CIRCLE_RADIUS * 2) + CURVE_CIRCLE_RADIUS,
                mFirstCurveEndPoint.y
            )
            mSecondCurveControlPoint1.set(
                mSecondCurveStartPoint.x + (CURVE_CIRCLE_RADIUS * 2) - CURVE_CIRCLE_RADIUS,
                mSecondCurveStartPoint.y
            )
            mSecondCurveControlPoint2.set(
                mSecondCurveEndPoint.x - (curveDepth),
                mSecondCurveEndPoint.y
            )

            moveTo(0f, curveDepth)
            lineTo(mFirstCurveStartPoint.x, mFirstCurveStartPoint.y)
            cubicTo(
                mFirstCurveControlPoint1.x, mFirstCurveControlPoint1.y,
                mFirstCurveControlPoint2.x, mFirstCurveControlPoint2.y,
                mFirstCurveEndPoint.x, mFirstCurveEndPoint.y
            )
            cubicTo(
                mSecondCurveControlPoint1.x, mSecondCurveControlPoint1.y,
                mSecondCurveControlPoint2.x, mSecondCurveControlPoint2.y,
                mSecondCurveEndPoint.x, mSecondCurveEndPoint.y
            )
            lineTo(size.width, curveDepth)
            lineTo(size.width, size.height)
            lineTo(0F, size.height)
        })
    }
}

@Composable
fun CurveNavigationBar(
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxWidth()
            .background(Transparent)
            .height(150.dp),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .matchParentSize()
                .clip(CurveShape())
                .background(MaterialTheme.colorScheme.primary.copy(.5f), shape = CurveShape())
        ) {
            content()
        }
        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = MaterialTheme.spacing.tiny8)
                .size(QR_CODE_ICON.dp),
            painter = painterResource(R.drawable.ic_tab_scan),
            contentDescription = "scan",
        )
    }
}

@Composable
fun CurveBottomNavItem(
    accentColor: Color = Purple40,
    initialSelectedIndex: Int = 0,
    navigationItems: List<GlassBottomNavItem>,
    onItemSelected: (index: Int, item: GlassBottomNavItem) -> Unit = { _, _ -> },
) {
    require(navigationItems.isNotEmpty()) { "Items list cannot be empty" }
    require(initialSelectedIndex in navigationItems.indices) {
        "Initial selected index must be within items range"
    }

    var elementSelectedPos by remember { mutableIntStateOf(initialSelectedIndex) }

    Row(
        modifier = Modifier
            .padding(top = MaterialTheme.spacing.tiny8)
            .fillMaxSize(),
    ) {
        navigationItems.mapIndexed { index, item ->
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(MaterialTheme.spacing.large)
                    .pointerInput(Unit) {
                        detectTapGestures {
                            elementSelectedPos = index
                            onItemSelected(index, item)
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                if(index == elementSelectedPos) {
                    Icon(
                        imageVector = item.outlinedIcon,
                        contentDescription = item.title,
                        modifier = Modifier.fillMaxSize(),
                        tint = accentColor,
                    )
                } else {
                    Icon(
                        imageVector = item.filledIcon,
                        contentDescription = item.title,
                        modifier = Modifier.fillMaxSize(),
                        tint = accentColor,
                    )
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.tiny))
                if (item.filledIcon != Icons.Default.Favorite) {
                    Text(
                        text = "${item.title}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = FontSize.small
                        ),
                    )
                }
            }
        }
    }
}

// Assuming GlassBottomNavItem is defined as above
// Modify GlassBottomNavItem to better handle title visibility
//data class GlassBottomNavItem(
//    val title: String,
//    val outlinedIcon: ImageVector,
//    val filledIcon: ImageVector,
//    val showTitle: Boolean = true, // Added for better control
//)

@Composable
fun RowScope.CurveBottomNavItem(
    // Make it a RowScope extension if it's always used in a Row
    item: GlassBottomNavItem,
    isSelected: Boolean,
    accentColor: Color, // Pass from parent
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
            .clickable(
                onClick = onClick,
                indication = null, // Disable default ripple if you have custom feedback
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(vertical = MaterialTheme.spacing.small, horizontal = MaterialTheme.spacing.small), // Adjust padding as needed
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = if (isSelected) item.filledIcon else item.outlinedIcon,
            contentDescription = item.title, // Good for accessibility
            modifier = Modifier.size(24.dp), // Fixed size for icons is common
            tint = if (isSelected) accentColor else MaterialTheme.colorScheme.onSurfaceVariant, // Use theme colors
        )
        if (item.title?.isNotEmpty() == true) { // Use the new flag
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(
                text = item.title,
                style = MaterialTheme.typography.labelSmall, // labelSmall is often used for nav items
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) accentColor else MaterialTheme.colorScheme.onSurfaceVariant // Use theme colors
            )
        }
    }
}

@Composable
fun CurveBottomNavHost(
    // Renamed for clarity, this hosts the items
    modifier: Modifier = Modifier,
    accentColor: Color = MaterialTheme.colorScheme.primary, // Use theme color
    initialSelectedIndex: Int = 0,
    navigationItems: List<GlassBottomNavItem>,
    onItemSelected: (index: Int, item: GlassBottomNavItem) -> Unit = { _, _ -> },
) {
    require(navigationItems.isNotEmpty()) { "Items list cannot be empty" }
    require(initialSelectedIndex in navigationItems.indices) {
        "Initial selected index must be within items range"
    }

    var selectedIndex by remember { mutableIntStateOf(initialSelectedIndex) }

    Row(
        modifier = modifier
            .padding(top = MaterialTheme.spacing.small) // Adjust as needed, consider the curve depth
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically // Align items vertically in the row
    ) {
        navigationItems.forEachIndexed { index, item ->
            // Special handling for the central "dummy" item if it's part of the list
            // Or, if the central button is separate, you might filter it out here
            // or adjust the layout (e.g., using Spacer with weight around the actual items)

            // For this example, assuming all items in the list are actual clickable tabs
            CurveBottomNavItem(
                item = item,
                isSelected = selectedIndex == index,
                accentColor = accentColor,
                onClick = {
                    selectedIndex = index
                    onItemSelected(index, item)
                }
            )
        }
    }
}

// In CurveBottomNavigation, you'd use CurveBottomNavHost:
@Composable
fun CurveBottomNavigation(
    initialSelectedIndex: Int = 0,
    navigationItems: List<GlassBottomNavItem> = DefaultGlassBottomNavItems,
    onItemSelected: (index: Int, item: GlassBottomNavItem) -> Unit = { _, _ -> },
) {
    CurveNavigationBar { // BoxScope
        CurveBottomNavHost( // The Row of items
            // Pass necessary parameters
            navigationItems = navigationItems,
            initialSelectedIndex = initialSelectedIndex,
            onItemSelected = onItemSelected,
            accentColor = Purple40, // Or from MaterialTheme.colorScheme.primary
            //modifier = Modifier.align(Alignment.BottomCenter) // Align items row to the bottom of the curved Box
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimation1(){
    var selectedIndex by remember { mutableIntStateOf(0) }
    CurveBottomNavigation(
        initialSelectedIndex = selectedIndex,
        onItemSelected = { index, item ->
            selectedIndex = index
        }
    )
}