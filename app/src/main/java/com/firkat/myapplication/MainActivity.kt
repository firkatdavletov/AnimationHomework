package com.firkat.myapplication

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
private const val ANIM_LENGTH = 5000L

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<Button>(R.id.start_animation_button)
        val animatedView = findViewById<CircleView>(R.id.animatedView)

        val animatorY = ValueAnimator.ofFloat(0f, 1000f)
            .apply {
                interpolator = BounceInterpolator()
                duration = ANIM_LENGTH
                addUpdateListener {
                    animatedView.translationY = it.animatedValue as Float
                }
            }
        val animatorX = ValueAnimator.ofFloat(0f, 600f)
            .apply {
                interpolator = DecelerateInterpolator()
                duration = ANIM_LENGTH
                addUpdateListener {
                    animatedView.translationX = it.animatedValue as Float
                }
            }

        button.setOnClickListener {
            animatorY.start()
            animatorX.start()
        }
    }
}