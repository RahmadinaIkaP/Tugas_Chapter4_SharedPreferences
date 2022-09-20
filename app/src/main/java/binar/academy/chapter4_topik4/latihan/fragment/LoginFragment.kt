package binar.academy.chapter4_topik4.latihan.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import binar.academy.chapter4_topik4.R
import binar.academy.chapter4_topik4.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    companion object{
        const val STATUS_SEDANG_LOGIN = "User login"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)

        binding.btnToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        binding.apply {
            when{
                etLoginUsername.text.isEmpty() -> etLoginUsername.error = "Username tidak boleh kosong"
                etLoginPassword.text.isEmpty() -> etLoginPassword.error = "Password tidak boleh kosong"
                else -> {
                    val etUsername = etLoginUsername.text.toString().trim()
                    val etPassword = etLoginPassword.text.toString()

                    val getUsername = sharedPreferences.getString("username", "kosong")
                    val getPass = sharedPreferences.getString("password", "kosong")


                    if (etUsername != getUsername && etPassword != getPass){
                        Toast.makeText(context, "Username/Password Salah!", Toast.LENGTH_SHORT).show()
                    }else{
                        val editor = sharedPreferences.edit()
                        editor.putBoolean(STATUS_SEDANG_LOGIN, true)
                        editor.apply()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}