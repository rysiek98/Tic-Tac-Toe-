package com.example.xoro
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.start_dialog.view.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fullScreen()

        btExit.setOnClickListener{
            finish()
            exitProcess(0)
        }

        btHelp.setOnClickListener {
            val help = Intent(applicationContext, info::class.java)
            startActivity(help)
        }

        btStartGame.setOnClickListener {

            val dialogView = LayoutInflater.from(this).inflate(R.layout.start_dialog, null)
            val mBuilder = AlertDialog.Builder(this).setView(dialogView)
            val mAlert = mBuilder.show()
            mAlert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogView.rgAIvsPlayer.setOnCheckedChangeListener { group, checkedId ->
                    val gameTypeData: RadioButton = dialogView.findViewById(checkedId)
                    dialogView.btPlay.setOnClickListener {
                            val startGame = Intent(applicationContext,Game::class.java)
                             startGame.putExtra("ID_gameType", gameTypeData.text)
                            startActivity(startGame)
                            mAlert.dismiss()
                        }
                    }
            dialogView.btBack.setOnClickListener {
                mAlert.dismiss()
                fullScreen()
            }
        }
}

    override fun onResume() {
        super.onResume()
        fullScreen()
    }

    fun fullScreen(){
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

}




