package com.harjot.jetpacknavigation

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.harjot.jetpacknavigation.databinding.FragmentSecondBinding
import com.harjot.jetpacknavigation.databinding.GifCongratsLayoutBinding
import com.harjot.jetpacknavigation.databinding.GifSorryLayoutBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    lateinit var binding: FragmentSecondBinding
    var mainActivity : MainActivity?=null
    var otp: String?=null
    var num: String?= null
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            otp = it.getString("OTP")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Toast.makeText(mainActivity, "$otp", Toast.LENGTH_SHORT).show()
        binding = FragmentSecondBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Start of Code

        binding.etOtp1.doOnTextChanged { text, _, _, _ ->
            if ((text?.length ?: 0) > 0) {
                binding.etOtp2.requestFocus()
            }
        }
        binding.etOtp2.doOnTextChanged { text, _, _, _ ->
            if ((text?.length ?: 0) > 0) {
                binding.etOtp3.requestFocus()
            }else if ((text?.length?:0)==0){
                binding.etOtp1.requestFocus()
            }
        }
        binding.etOtp3.doOnTextChanged{text,_,_,_->
            if((text?.length?:0)>0){
                binding.etOtp4.requestFocus()
            }else if ((text?.length?:0)==0){
                binding.etOtp2.requestFocus()
            }
        }
        binding.etOtp4.doOnTextChanged{text,_,_,_ ->
            if ((text?.length?:0)==0){
                binding.etOtp3.requestFocus()
            }
        }

        binding.btnVerify.setOnClickListener {
            num = binding.etOtp1.text.toString().trim() +
                    binding.etOtp2.text.toString().trim() +
                    binding.etOtp3.text.toString().trim() +
                    binding.etOtp4.text.toString().trim()

            if (binding.etOtp1.text.toString().trim().isNullOrEmpty() ||
                binding.etOtp2.text.toString().trim().isNullOrEmpty() ||
                binding.etOtp3.text.toString().trim().isNullOrEmpty() ||
                binding.etOtp4.text.toString().trim().isNullOrEmpty()){
                binding.etOtp1.error = "Enter OTP"
            }else if (otp==num){
                mainActivity?.navController?.navigate(R.id.action_secondFragment_to_thirdFragment)
                Toast.makeText(mainActivity, "Matched", Toast.LENGTH_SHORT).show()
            }
            else{

                var dialog = Dialog(mainActivity!!)
                var dialogBinding = GifSorryLayoutBinding.inflate(layoutInflater)
                dialog.setContentView(dialogBinding.root)
                dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
                dialog.show()

                Toast.makeText(mainActivity, "Not Matched", Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}