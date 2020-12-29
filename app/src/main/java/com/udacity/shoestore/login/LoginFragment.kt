package com.udacity.shoestore.login

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.appcompat.app.AppCompatActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    lateinit var binding: DataBindingUtil
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<LoginFragmentBinding>(inflater, R.layout.login_fragment, container, false)


        binding.loginButton.setOnClickListener { view: View ->
            val username = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()
            if(validateUser(username, password)){

                view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }

        }

        binding.signupButton.setOnClickListener { view: View ->
            val username = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()
            if(validateUser(username, password)){
                view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }

        return binding.root
    }

    private fun validateUser(username: String, password: String): Boolean {
        if(username == "" || password == ""){
            Toast.makeText(context, "You should enter user and pass", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}