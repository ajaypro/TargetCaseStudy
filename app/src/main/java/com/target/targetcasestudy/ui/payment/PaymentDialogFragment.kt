package com.target.targetcasestudy.ui.payment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.target.targetcasestudy.data.validateCard
import com.target.targetcasestudy.databinding.DialogPaymentBinding

/**
 * Dialog that displays a minimal credit card entry form.
 *
 * Your task here is to enable the "submit" button when the credit card number is valid and
 * disable the button when the credit card number is not valid.
 *
 * You do not need to input any of your actual credit card information. See `Validators.kt` for
 * info to help you get fake credit card numbers.
 *
 * You do not need to make any changes to the layout of this screen (though you are welcome to do
 * so if you wish).
 */
class PaymentDialogFragment : DialogFragment() {

    lateinit var binding: DialogPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogPaymentBinding.inflate(inflater)

        binding.cardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s.toString().validateCard().apply {
                    if (this) {
                        binding.submit.isEnabled = this
                        binding.submit.setOnClickListener { dismiss() }
                    } else {
                        binding.submit.isEnabled = false
                    }

                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.cancel.setOnClickListener { dismiss() }

        // TODO enable the submit button based on card number validity using Validators.validateCreditCard()

        return binding.root
    }

}