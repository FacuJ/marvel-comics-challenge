package com.facundojaton.marvelcomicschallenge.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.facundojaton.marvelcomicschallenge.databinding.FragmentLoginBinding
import com.facundojaton.marvelcomicschallenge.utils.SessionController
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
            // Successfully signed in
            SessionController.initializeSignInData()
            // ...
        } else {
            Log.e(TAG, "EPIC FAIL")
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
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