package com.harjot.jetpacknavigation

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.harjot.jetpacknavigation.databinding.FragmentThirdBinding
import com.harjot.jetpacknavigation.databinding.GifCongratsLayoutBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {
    lateinit var binding: FragmentThirdBinding
    var mainActivity : MainActivity ?= null
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmit.setOnClickListener {
            if (binding.etNewPassword.text.toString().trim().isNullOrEmpty()){
                binding.etNewPassword.error = "Enter New Password"
            }else if (binding.etConfPassword.text.toString().trim().isNullOrEmpty()){
                binding.etConfPassword.error = "Enter Confirm Password"
            }else if (binding.etNewPassword.text.toString().trim() != binding.etConfPassword.text.toString().trim()){
                Toast.makeText(mainActivity, "Password Doesn't match", Toast.LENGTH_SHORT).show()
                binding.etNewPassword.error = "Password Doesn't match"
                binding.etConfPassword.error = "Password Doesn't match"
            }else{
                var dialog = Dialog(mainActivity!!)
                var dialogBinding = GifCongratsLayoutBinding.inflate(layoutInflater)
                dialog.setContentView(dialogBinding.root)
                dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                dialog.show()
                mainActivity?.navController?.navigate(R.id.action_thirdFragment_to_firstFragment)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThirdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}