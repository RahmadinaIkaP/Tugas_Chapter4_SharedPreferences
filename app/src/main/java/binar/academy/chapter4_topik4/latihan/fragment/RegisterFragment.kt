package binar.academy.chapter4_topik4.latihan.fragment

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import binar.academy.chapter4_topik4.R
import binar.academy.chapter4_topik4.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("User", MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {
            register()
        }

    }

    private fun register() {
        binding.apply {
            when{
                etRegisUsername.text.isEmpty() -> etRegisUsername.error = "Username tidak boleh kosong"
                etRegisNama.text.isEmpty() -> etRegisNama.error = "Nama tidak boleh kosong"
                etRegisPassword.text.isEmpty() -> etRegisPassword.error = "Password tidak boleh kosong"
                etKonfirPassword.text.isEmpty() -> etKonfirPassword.error = "Silahkan input ulang password"
                else -> {
                    val username = etRegisUsername.text.toString().trim()
                    val nama = etRegisNama.text.toString()
                    val password = etRegisPassword.text.toString()
                    val konfirmPass = etKonfirPassword.text.toString()

                    if (password == konfirmPass){
                        val saveData = sharedPreferences.edit()

                        saveData.putString("username", username)
                        saveData.putString("nama", nama)
                        saveData.putString("password", password)
                        saveData.apply()

                        Toast.makeText(context, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    }else
                        Toast.makeText(context, "Password tidak sama!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}