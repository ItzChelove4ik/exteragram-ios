package com.google.android.material.theme;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes5.dex */
public class MaterialComponentsViewInflater extends AppCompatViewInflater {
    public MaterialComponentsViewInflater() {
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatAutoCompleteTextView createAutoCompleteTextView(Context r1, AttributeSet r2) {
        return new MaterialAutoCompleteTextView(r1, r2);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatButton createButton(Context r1, AttributeSet r2) {
        return new MaterialButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatCheckBox createCheckBox(Context r1, AttributeSet r2) {
        return new MaterialCheckBox(r1, r2);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatRadioButton createRadioButton(Context r1, AttributeSet r2) {
        return new MaterialRadioButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatTextView createTextView(Context r1, AttributeSet r2) {
        return new MaterialTextView(r1, r2);
    }
}
