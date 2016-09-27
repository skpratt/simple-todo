package com.codepath.simpletodo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class EditNameDialogFragment extends DialogFragment {

    private EditText mEditText;

    public EditNameDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EditNameDialogFragment newInstance(int pos) {
        EditNameDialogFragment frag = new EditNameDialogFragment();
        Bundle args = new Bundle();
        args.putInt("number", pos);
        frag.setArguments(args);
        return frag;
    }

    public interface EditDialogueListener {
        void onFinishEditDialog(String inputText, int pos, View v);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mEditText = (EditText) view.findViewById(R.id.etChangeItem);
        // Fetch arguments from bundle and set title
        final int pos = getArguments().getInt("number");
        // 2. Setup a callback when the "Done" button is pressed on keyboard
        Button button = (Button) view.findViewById(R.id.btnChange);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Return input text back to activity through the implemented listener
                EditDialogueListener listener = (EditDialogueListener) getActivity();
                listener.onFinishEditDialog(mEditText.getText().toString(), pos, v);
                mEditText.setText("");
                // Close the dialog and return back to the parent activity
                dismiss();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_item, container);
    }
}