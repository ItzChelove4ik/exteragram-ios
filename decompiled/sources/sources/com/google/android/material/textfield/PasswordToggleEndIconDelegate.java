package com.google.android.material.textfield;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.R;

/* loaded from: classes5.dex */
class PasswordToggleEndIconDelegate extends EndIconDelegate {
    private EditText editText;
    private int iconResId;
    private final View.OnClickListener onIconClickListener;

    public static /* synthetic */ void $r8$lambda$T4f7xxY_3Sk8DJHDU1zyNxpd1D4(PasswordToggleEndIconDelegate r2, View r3) {
        EditText r32 = r2.editText;
        if (r32 != null) goto L5;
        return;
    L5:
        int r33 = r32.getSelectionEnd();
        boolean r0 = r2.hasPasswordTransformation();
        EditText r1 = r2.editText;
        if (r0 == false) goto L8;
        r1.setTransformationMethod(null);
    L9:
        if (r33 < 0) goto L11;
        r2.editText.setSelection(r33);
    L11:
        r2.refreshIconState();
        return;
    L8:
        r1.setTransformationMethod(PasswordTransformationMethod.getInstance());
        goto L9
    }

    public PasswordToggleEndIconDelegate(EndCompoundLayout r1, int r2) {
        super(r1);
        this.iconResId = R.drawable.design_password_eye;
        this.onIconClickListener = new PasswordToggleEndIconDelegate$$ExternalSyntheticLambda0(this);
        if (r2 == 0) goto L6;
        this.iconResId = r2;
        return;
    }

    private boolean hasPasswordTransformation() {
        EditText r0 = this.editText;
        if (r0 != null) goto L5;
        return false;
    L5:
        if ((r0.getTransformationMethod() instanceof PasswordTransformationMethod) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isInputTypePassword(EditText r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (r2.getInputType() != 16) goto L6;
        return true;
    L6:
        if (r2.getInputType() != 128) goto L8;
        return true;
    L8:
        if (r2.getInputType() != 144) goto L10;
        return true;
    L10:
        if (r2.getInputType() != 224) goto L18;
        return true;
    L18:
        return false;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void beforeEditTextChanged(CharSequence r1, int r2, int r3, int r4) {
        refreshIconState();
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public int getIconContentDescriptionResId() {
        return R.string.password_toggle_content_description;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public int getIconDrawableResId() {
        return this.iconResId;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public View.OnClickListener getOnIconClickListener() {
        return this.onIconClickListener;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public boolean isIconCheckable() {
        return true;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public boolean isIconChecked() {
        return !hasPasswordTransformation();
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void onEditTextAttached(EditText r1) {
        this.editText = r1;
        refreshIconState();
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void setUp() {
        if (isInputTypePassword(this.editText) == false) goto L6;
        this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        return;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void tearDown() {
        EditText r1 = this.editText;
        if (r1 == null) goto L6;
        r1.setTransformationMethod(PasswordTransformationMethod.getInstance());
        return;
    }
}
