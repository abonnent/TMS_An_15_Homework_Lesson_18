package solutions.belov.tms_an_15_homework_lesson_18

import android.R.attr.value
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import solutions.belov.tms_an_15_homework_lesson_18.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    var cb1 = false
    var cb2 = false
    var cb3 = false
    var rb = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rg = binding.radioGroup
        rg.setOnCheckedChangeListener { group, checkedId ->
            when (rg.checkedRadioButtonId) {
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
                intent.putExtra("LOGIN", binding.loginField.text.toString())
                intent.putExtra("PASSWORD", binding.passwordField.text.toString())
                intent.putExtra("CB1", cb1)
                intent.putExtra("CB2", cb2)
                intent.putExtra("CB3", cb3)
                intent.putExtra("RB", rb)
                startActivity(intent)
            }
        }
    }

    val listener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
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
                    binding.radioWrapper.visibility = View.VISIBLE
                } else {
                    binding.radioWrapper.visibility = View.GONE
                    binding.rb4.isChecked = true
                    //binding.radioGroup.setOnCheckedChangeListener(null)
                    //binding.radioGroup.clearCheck()
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
        if (binding.loginField.text.toString().trim().isEmpty()) {
            binding.loginFieldLayout.error = getString(R.string.required_field)
            binding.loginField.requestFocus()
            return false
        } else {
            binding.loginFieldLayout.isErrorEnabled = false
        }

        return true
    }

    private fun validatePassword(): Boolean {
        if (binding.passwordField.text.toString().trim().isEmpty()) {
            binding.passwordFieldLayout.error = getString(R.string.required_field)
            binding.passwordField.requestFocus()
            return false
        } else if (binding.passwordField.text.toString().length < 6) {
            binding.passwordFieldLayout.error = getString(R.string.no_less)
            binding.passwordField.requestFocus()
            return false
        } else {
            binding.passwordFieldLayout.isErrorEnabled = false
        }

        return true
    }

    private fun validateConfirmPassword(): Boolean {
        when {
            binding.confirmPasswordField.text.toString().trim().isEmpty() -> {
                binding.confirmPasswordFieldLayout.error = getString(R.string.required_field)
                binding.confirmPasswordField.requestFocus()
                return false
            }

            binding.confirmPasswordField.text.toString() != binding.passwordField.text.toString() -> {
                binding.confirmPasswordFieldLayout.error = getString(R.string.do_not_match)
                binding.confirmPasswordField.requestFocus()
                return false
            }

            else -> {
                binding.confirmPasswordFieldLayout.isErrorEnabled = false
            }
        }

        return true
    }

    private fun setupListeners() {
        binding.loginField.addTextChangedListener(TextFieldValidation(binding.loginField))
        binding.passwordField.addTextChangedListener(TextFieldValidation(binding.passwordField))
        binding.confirmPasswordField.addTextChangedListener(TextFieldValidation(binding.confirmPasswordField))
    }
}