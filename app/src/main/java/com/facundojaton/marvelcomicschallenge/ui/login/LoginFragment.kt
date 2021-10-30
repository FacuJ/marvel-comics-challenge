package com.facundojaton.marvelcomicschallenge.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.facundojaton.marvelcomicschallenge.R
import com.facundojaton.marvelcomicschallenge.databinding.FragmentLoginBinding
import com.facundojaton.marvelcomicschallenge.utils.SessionController
import com.facundojaton.marvelcomicschallenge.utils.toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult

class LoginFragment : Fragment() {

    companion object {
        val TAG = LoginFragment::class.simpleName
    }

    private lateinit var binding : FragmentLoginBinding

    private val signInLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) { result ->
            this.onSignInResult(result)
        }


    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            SessionController.initializeSignInData()
            navigateToMainScreen()
        } else {
            SessionController.logout()
            Log.e(TAG, response?.error?.message.toString())
            toast(R.string.error_signin_in)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentLoginBinding.inflate(layoutInflater)
        if (SessionController.userEmail != null) {
            navigateToMainScreen()
        }
        return binding.root
    }

    private fun navigateToMainScreen() {
        this.findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener {
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.FacebookBuilder().build())

            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signInIntent)
        }
    }
}