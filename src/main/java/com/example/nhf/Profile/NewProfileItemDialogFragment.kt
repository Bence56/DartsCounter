package com.example.nhf.Profile

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.nhf.R
import com.google.android.material.internal.ViewUtils.getContentView

class NewProfileItemDialogFragment : DialogFragment() {

    private lateinit var nameEditText: EditText
    private lateinit var numberOfGamesEditText: EditText
    private lateinit var numberOf180EditText: EditText
    private lateinit var genderCheckBox: CheckBox


    interface NewProfileItemDialogListener {
        fun onProfileItemCreated(newItem: ProfileItem)
    }

    private lateinit var listener: NewProfileItemDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewProfileItemDialogListener
            ?: throw RuntimeException("Activity must implement the NewShoppingItemDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_profile_item)
            .setView(getContentView())
            .setPositiveButton(R.string.ok) { dialogInterface, i ->
                if (isValid()) {
                    listener.onProfileItemCreated(getProfileItem());
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    private fun isValid() = nameEditText.text.isNotEmpty()

    private fun getProfileItem() = ProfileItem(
        id = null,
        name = nameEditText.text.toString(),
        number_of_games = Integer.parseInt(numberOfGamesEditText.text.toString()),
        number_of_180s =Integer.parseInt(numberOf180EditText.text.toString()),
        gender = genderCheckBox.isChecked
    )

    companion object {
        const val TAG = "NewProfileItemCreated"
    }
    private fun getContentView(): View {
        val contentView =
            LayoutInflater.from(context).inflate(R.layout.dialog_new_profile_item, null)
        nameEditText = contentView.findViewById(R.id.ProfileItemNameEditText)
        numberOfGamesEditText = contentView.findViewById(R.id.ProfileItemNumberOfGamesEditText)
        numberOf180EditText = contentView.findViewById(R.id.ProfileItemNumberOf180EditText)
        genderCheckBox = contentView.findViewById(R.id.GenderCheckBox)
        return contentView
    }
}