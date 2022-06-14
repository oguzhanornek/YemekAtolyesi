package com.ta.yemekatolyesi.fragments.welcome

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ta.yemekatolyesi.databinding.FragmentWelcomeBinding
import com.ta.yemekatolyesi.utils.Constants

class WelcomeFragment : Fragment() {
    private var _binding : FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var name : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener(){
        binding.apply {
            goOnButton.setOnClickListener {
                if (nameEditText.text.isNotEmpty()){
                    tvPleaseEnterName.visibility = View.INVISIBLE
                    val action = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
                    findNavController().navigate(action)
                    val preferences = requireActivity().getSharedPreferences("yemek-atolyesi", Context.MODE_PRIVATE)
                    val editor = preferences.edit()
                    editor.putString(Constants.Name,nameEditText.text.toString())
                    editor.apply()
                }else{
                    tvPleaseEnterName.visibility = View.VISIBLE
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}