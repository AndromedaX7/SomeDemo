package moe.lemonneko.translucentstatusbar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置全透明
        window.clearFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
        )
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
        //加载布局
        setContentView(R.layout.activity_main)
        //获取View的实例
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val topView = findViewById<View>(R.id.top_view)
        //将自己的应用栏设置为系统应用栏
        setSupportActionBar(toolbar)
        //设置顶部填充View的高度
        val statusBarHeightId = resources.getIdentifier("status_bar_height", "dimen", "android")
        val statusBarHeight = resources.getDimensionPixelSize(statusBarHeightId)
        val params =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, statusBarHeight)
        topView.layoutParams = params
        //设置顶部填充View的颜色
        val toolbarColor = toolbar.background as ColorDrawable
        topView.background = ColorDrawable(toolbarColor.color)
        //细节处理
        toolbar.setTitleTextColor(Color.WHITE)
    }
}
