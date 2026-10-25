package com.google.android.material.timepicker;

import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;

/* loaded from: classes5.dex */
class TimePickerTextInputKeyController implements TextView.OnEditorActionListener, View.OnKeyListener {
    private final ChipTextInputComboView hourLayoutComboView;
    private boolean keyListenerRunning;
    private final ChipTextInputComboView minuteLayoutComboView;
    private final TimeModel time;

    public TimePickerTextInputKeyController(ChipTextInputComboView r2, ChipTextInputComboView r3, TimeModel r4) {
        this.keyListenerRunning = false;
        this.hourLayoutComboView = r2;
        this.minuteLayoutComboView = r3;
        this.time = r4;
    }

    private void clearPrefilledText(EditText r2) {
        if (r2.getSelectionStart() == 0) goto L5;
        return;
    L5:
        if (r2.length() != 2) goto L9;
        r2.getText().clear();
        return;
    }

    private void moveSelection(int r5) {
        ChipTextInputComboView r0 = this.minuteLayoutComboView;
        boolean r2 = false;
        if (r5 != 12) goto L5;
        boolean r1 = true;
    L6:
        r0.setChecked(r1);
        ChipTextInputComboView r02 = this.hourLayoutComboView;
        if (r5 != 10) goto L9;
        r2 = true;
    L9:
        r02.setChecked(r2);
        this.time.selection = r5;
        return;
    L5:
        r1 = false;
        goto L6
    }

    private boolean onHourKeyPress(int r7, KeyEvent r8, EditText r9) {
        Editable r0 = r9.getText();
        if (r0 != null) goto L6;
        return false;
    L6:
        if (r7 < 7) goto L18;
        if (r7 > 16) goto L18;
        if (r8.getAction() != 1) goto L18;
        if (r9.getSelectionStart() != 2) goto L18;
        if (r0.length() != 2) goto L18;
        if (this.hourLayoutComboView.hasError() == true) goto L18;
        moveSelection(12);
        return true;
    L18:
        if (r7 < 7) goto L21;
        if (r7 > 16) goto L21;
        clearPrefilledText(r9);
    L21:
        return false;
    }

    private boolean onMinuteKeyPress(int r2, KeyEvent r3, EditText r4) {
        if (r2 != 67) goto L11;
        if (r3.getAction() != 0) goto L11;
        if (TextUtils.isEmpty(r4.getText()) == false) goto L11;
        moveSelection(10);
        return true;
    L11:
        if (r2 >= 7) goto L13;
        return false;
    L13:
        if (r2 > 16) goto L18;
        clearPrefilledText(r4);
        return false;
    L18:
        return false;
    }

    public void bind() {
        TextInputLayout r0 = this.hourLayoutComboView.getTextInput();
        TextInputLayout r1 = this.minuteLayoutComboView.getTextInput();
        EditText r02 = r0.getEditText();
        EditText r12 = r1.getEditText();
        r02.setImeOptions(268435461);
        r12.setImeOptions(268435462);
        r02.setOnEditorActionListener(this);
        r02.setOnKeyListener(this);
        r12.setOnKeyListener(this);
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L5;
        boolean r12 = true;
    L6:
        if (r12 == false) goto L8;
        moveSelection(12);
    L8:
        return r12;
    L5:
        r12 = false;
        goto L6
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View r4, int r5, KeyEvent r6) {
        if (this.keyListenerRunning == false) goto L5;
        return false;
    L5:
        this.keyListenerRunning = true;
        EditText r42 = (EditText) r4;
        if (this.time.selection != 12) goto L8;
        boolean r43 = onMinuteKeyPress(r5, r6, r42);
    L9:
        this.keyListenerRunning = false;
        return r43;
    L8:
        r43 = onHourKeyPress(r5, r6, r42);
        goto L9
    }
}
