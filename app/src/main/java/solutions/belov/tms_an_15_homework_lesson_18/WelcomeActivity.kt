package solutions.belov.tms_an_15_homework_lesson_18

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.textview.MaterialTextView
import solutions.belov.tms_an_15_homework_lesson_18.databinding.ActivityLoginBinding
import solutions.belov.tms_an_15_homework_lesson_18.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            with(binding) {
                if (it.getBoolean(CB1)) {
                    cb1.isVisible = true
                    loginField.text = getString(R.string.login_field, it.getString(LOGIN))
                    passwordField.text =
                        getString(R.string.password_field, it.getString(PASSWORD))
                }

                if (it.getBoolean(CB2)) {
                    cb2.isVisible = true
                    loginField.text = getString(R.string.login_field, it.getString(LOGIN))
                    passwordField.text =
                        getString(R.string.password_field, it.getString(PASSWORD))
                }

                if (it.getBoolean(CB3)) {
                    cb3.isVisible = true
                    rb.isVisible = true
                    loginField.text = getString(R.string.login_field, it.getString(LOGIN))
                    passwordField.text = getString(R.string.password_field, it.getString(PASSWORD))
                    rb.text = getString(R.string.rb_selected, it.getString(RB))
                }
            }
        }
    }
}