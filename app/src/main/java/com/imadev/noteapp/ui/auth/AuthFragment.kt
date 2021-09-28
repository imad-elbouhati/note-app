package com.imadev.noteapp.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.imadev.noteapp.R
import com.imadev.noteapp.databinding.FragmentAuthBinding
import com.imadev.noteapp.other.ConnectionLiveData
import com.imadev.noteapp.other.Resource
import com.imadev.noteapp.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>(R.layout.fragment_auth) {

    private val viewModel: AuthViewModel by viewModels()

    @Inject
    lateinit var connectionLiveData: ConnectionLiveData


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnRegister.setOnClickListener {

            preformSignUp()


        }
    }

    private fun preformSignUp() {

        Timber.d("Connection live data ${connectionLiveData.value}")

        viewModel.register("test", "pppp").asLiveData().observe(viewLifecycleOwner, {
            val data = it.data

            when (it) {
                is Resource.Loading -> {
                    Timber.d("Loading...")
                }
                is Resource.Error -> {
                    Timber.d("${it.error}")
                }
                is Resource.Success -> {
                    data?.message?.let { it1 -> showSnackbar(it1) }
                   // findNavController().navigate(R.id.action_authFragment_to_notesFragment)

                }
                is Resource.Unauthorized -> {
                    showSnackbar("Unauthorized")
                }
            }
        })
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAuthBinding.inflate(inflater, container, false)
}