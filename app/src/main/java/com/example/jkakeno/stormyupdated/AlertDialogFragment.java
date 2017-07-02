package com.example.jkakeno.stormyupdated;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

public class AlertDialogFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//Typicaly we set the cotext as activity_name.this or this but since this class is called from a different class we can use the getActivity() to get the context
//Since we are going to use the cotext in a different place we make this a variable
//AlertDialog.Builder is an example of Factory Method Pattern where Builder is a sub class of AlertDialog class
        Context context = getActivity();
//Configure the builder by chaining methods and assign it to builder
//Create string resources by placing cursor on the string Alt + Enter name the string and press Enter the string then gets placed in string.xml
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.error_title))
                .setMessage(context.getString(R.string.error_message))
                .setPositiveButton(context.getString(R.string.error_ok_button_text),null);
//Create the dialog using the builder
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
