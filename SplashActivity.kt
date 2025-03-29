package kr.ac.hallym.portfolio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.airbnb.lottie.LottieAnimationView
import kr.ac.hallym.portfolio.databinding.ActivitySplashBinding
import kotlin.random.Random

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash)
        var id = intent.getStringExtra("id")
        Log.d("id","id 전달 $id")

        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val splashImage = binding.splashImage as LottieAnimationView
        splashImage.playAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ListActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
            finish()
        }, 1000+Random.nextLong(1000))
    }
}