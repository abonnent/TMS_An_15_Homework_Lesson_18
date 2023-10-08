package solutions.belov.tms_an_15_homework_lesson_18

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import solutions.belov.tms_an_15_homework_lesson_18.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var cb1 = false
    private var cb2 = false
    private var cb3 = false
    private var rb = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb1 -> {
                    rb = binding.rb1.text.toString()
                    Snackbar.make(group, getString(R.string.rb1_msg), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                R.id.rb2 -> {
                    rb = binding.rb2.text.toString()
                    Snackbar.make(group, getString(R.string.rb2_msg), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                R.id.rb3 -> {
                    rb = binding.rb3.text.toString()
                    Snackbar.make(group, getString(R.string.rb3_msg), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        }

        with(binding) {
            cb1.setOnCheckedChangeListener(listener)
            cb2.setOnCheckedChangeListener(listener)
            cb3.setOnCheckedChangeListener(listener)
        }

        setupListeners()

        binding.loginButton.setOnClickListener {
            if (isValidate()) {
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra(LOGIN, binding.loginField.text.toString())
                intent.putExtra(PASSWORD, binding.passwordField.text.toString())
                intent.putExtra(CB1, cb1)
                intent.putExtra(CB2, cb2)
                intent.putExtra(CB3, cb3)
                intent.putExtra(RB, rb)
                startActivity(intent)
            }
        }
    }

    private val listener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        when (buttonView.id) {
            R.id.cb1 -> {
                cb1 = isChecked
            }

            R.id.cb2 -> {
                cb2 = isChecked
            }

            R.id.cb3 -> {
                if (isChecked) {
                    cb3 = true
                    binding.radioWrapper.isVisible = true
                } else {
                    cb3 = false
                    binding.radioWrapper.isVisible = false
                    binding.rb4.isChecked = true
                    //binding.radioGroup.clearCheck()
                    //binding.radioGroup.setOnCheckedChangeListener(null)
                }
            }
        }
    }

    private fun isValidate(): Boolean =
        validateUserName() && validatePassword() && validateConfirmPassword()

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            when (view.id) {
                R.id.login_field -> {
                    validateUserName()
                }

                R.id.password_field -> {
                    validatePassword()
                }

                R.id.confirm_password_field -> {
                    validateConfirmPassword()
                }
            }
        }
    }

    private fun validateUserName(): Boolean {
        return if (binding.loginField.text.toString().trim().isEmpty()) {
            binding.loginFieldLayout.error = getString(R.string.required_field)
            binding.loginField.requestFocus()
            false
        } else {
            binding.loginFieldLayout.isErrorEnabled = false
            true
        }
    }

    private fun validatePassword(): Boolean {
        return if (binding.passwordField.text.toString().trim().isEmpty()) {
            binding.passwordFieldLayout.error = getString(R.string.required_field)
            binding.passwordField.requestFocus()
            false
        } else if (binding.passwordField.text.toString().length < MIN_LENGTH) {
            binding.passwordFieldLayout.error = getString(R.string.no_less)
            binding.passwordField.requestFocus()
            false
        } else {
            binding.passwordFieldLayout.isErrorEnabled = false
            true
        }
    }

    private fun validateConfirmPassword(): Boolean {
        return when {
            binding.confirmPasswordField.text.toString().trim().isEmpty() -> {
                binding.confirmPasswordFieldLayout.error = getString(R.string.required_field)
                binding.confirmPasswordField.requestFocus()
                false
            }

            binding.confirmPasswordField.text.toString() != binding.passwordField.text.toString() -> {
                binding.confirmPasswordFieldLayout.error = getString(R.string.do_not_match)
                binding.confirmPasswordField.requestFocus()
                false
            }

            else -> {
                binding.confirmPasswordFieldLayout.isErrorEnabled = false
                true
            }
        }
    }

    private fun setupListeners() {
        with(binding) {
            loginField.addTextChangedListener(TextFieldValidation(binding.loginField))
            passwordField.addTextChangedListener(TextFieldValidation(binding.passwordField))
            confirmPasswordField.addTextChangedListener(TextFieldValidation(binding.confirmPasswordField))
        }
    }
}