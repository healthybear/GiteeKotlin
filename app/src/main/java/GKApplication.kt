
import android.app.Application
import com.blankj.utilcode.util.AppUtils
import com.tencent.bugly.crashreport.CrashReport

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 10:20
 * @Description:
 */
// TODO 窝草，不能定义自定义Application，不然会报错
class GKApplication : Application() {

    companion object {
    }


    override fun onCreate() {
        super.onCreate()
        TODO("秘钥之类的东西还是不能明文保存")
        CrashReport.initCrashReport(getApplicationContext(), "379ddfdabe", AppUtils.isAppDebug());
    }
}