package solutions.belov.tms_an_15_homework_lesson_18

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import solutions.belov.tms_an_15_homework_lesson_18.databinding.ActivityLoginBinding
import solutions.belov.tms_an_15_homework_lesson_18.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            with(binding) {
                if (extras.getBoolean("CB1")) {
                    cb1.visibility = View.VISIBLE
                    loginField.text = getString(R.string.login_field, extras.getString("LOGIN"))
                    passwordField.text =
                        getString(R.string.password_field, extras.getString("PASSWORD"))
                }

                if (extras.getBoolean("CB2")) {
                    cb2.visibility = View.VISIBLE
                    loginField.text = getString(R.string.login_field, extras.getString("LOGIN"))
                    passwordField.text =
                        getString(R.string.password_field, extras.getString("PASSWORD"))
                }

                if (extras.getBoolean("CB3")) {
                    cb3.visibility = View.VISIBLE
                    rb.visibility = View.VISIBLE
                    loginField.text = getString(R.string.login_field, extras.getString("LOGIN"))
                    passwordField.text =
                        getString(R.string.password_field, extras.getString("PASSWORD"))
                    rb.text = getString(R.string.rb_selected, extras.getString("RB"))
                }
            }

        }
    }
}