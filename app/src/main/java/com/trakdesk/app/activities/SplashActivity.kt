package com.trakdesk.app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import com.trakdesk.app.R

class SplashActivity : BaseActivity() {
    private val tag = SplashActivity::class.java.simpleName
    //    private AppSharedPreferences appSharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
//        ivLogo.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP)

        // Duration of wait

        val splashDisplayLength = 3000

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        Handler().postDelayed({

            /* val intent = if (!AppPreferences.firstRun) {
                 Log.d(tag, "First run : ${AppPreferences.firstRun}")
                 Intent(this@SplashActivity, LoginActivity::class.java)
             } else {
                 Log.d(tag, "Secon: ${AppPreferences.firstRun}")
                 Intent(this@SplashActivity, MainActivity::class.java)
             }*/


            //                }
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, splashDisplayLength.toLong())
    }
}
