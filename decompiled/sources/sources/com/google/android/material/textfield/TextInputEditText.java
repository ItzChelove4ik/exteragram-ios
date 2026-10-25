package com.google.android.material.textfield;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.AppCompatEditText;
import com.google.android.material.R;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class TextInputEditText extends AppCompatEditText {
    private final Rect parentRect;
    private boolean textInputLayoutFocusedRectEnabled;

    public TextInputEditText(Context r2) {
        this(r2, null);
    }

    public TextInputEditText(Context r2, AttributeSet r3) {
        this(r2, r3, R$attr.editTextStyle);
    }

    public TextInputEditText(Context r9, AttributeSet r10, int r11) {
        super(MaterialThemeOverlay.wrap(r9, r10, r11, 0), r10, r11);
        this.parentRect = new Rect();
        TypedArray r92 = ThemeEnforcement.obtainStyledAttributes(r9, r10, R.styleable.TextInputEditText, r11, R.style.Widget_Design_TextInputEditText, new int[0]);
        setTextInputLayoutFocusedRectEnabled(r92.getBoolean(R.styleable.TextInputEditText_textInputLayoutFocusedRectEnabled, false));
        r92.recycle();
    }

    private String getAccessibilityNodeInfoText(TextInputLayout r4) {
        Editable r3 = getText();
        CharSequence r42 = r4.getHint();
        boolean r0 = TextUtils.isEmpty(r3);
        boolean r1 = TextUtils.isEmpty(r42);
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 == true) goto L5;
        String r43 = r42.toString();
    L6:
        if (r0 == true) goto L13;
        StringBuilder r02 = new StringBuilder();
        r02.append(r3);
        if (TextUtils.isEmpty(r43) == true) goto L10;
        r2 = ", " + r43;
    L10:
        r02.append(r2);
        return r02.toString();
    L13:
        if (TextUtils.isEmpty(r43) == true) goto L15;
        return r43;
    L15:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        r43 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private CharSequence getHintFromLayout() {
        TextInputLayout r0 = getTextInputLayout();
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getHint();
    }

    private TextInputLayout getTextInputLayout() {
        ViewParent r1 = getParent();
    L4:
        if ((r1 instanceof View) == false) goto L10;
        if ((r1 instanceof TextInputLayout) == true) goto L8;
        r1 = r1.getParent();
        goto L4
    L8:
        return (TextInputLayout) r1;
    L10:
        return null;
    }

    private boolean shouldUseTextInputLayoutFocusedRect(TextInputLayout r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (this.textInputLayoutFocusedRectEnabled == false) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect r3) {
        super.getFocusedRect(r3);
        TextInputLayout r0 = getTextInputLayout();
        if (shouldUseTextInputLayoutFocusedRect(r0) == false) goto L7;
        if (r3 == null) goto L8;
        r0.getFocusedRect(this.parentRect);
        r3.bottom = this.parentRect.bottom;
        return;
    L8:
        return;
    }

    @Override // android.view.View
    public boolean getGlobalVisibleRect(Rect r3, Point r4) {
        TextInputLayout r0 = getTextInputLayout();
        if (shouldUseTextInputLayoutFocusedRect(r0) == false) goto L10;
        boolean r32 = r0.getGlobalVisibleRect(r3, r4);
        if (r32 == false) goto L8;
        if (r4 == null) goto L8;
        r4.offset(-getScrollX(), -getScrollY());
    L8:
        return r32;
    L10:
        return super.getGlobalVisibleRect(r3, r4);
    }

    @Override // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout r0 = getTextInputLayout();
        if (r0 == null) goto L9;
        if (r0.isProvidingHint() == false) goto L9;
        return r0.getHint();
    L9:
        return super.getHint();
    }

    public boolean isTextInputLayoutFocusedRectEnabled() {
        return this.textInputLayoutFocusedRectEnabled;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout r0 = getTextInputLayout();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isProvidingHint() == true) goto L7;
        return;
    L7:
        if (super.getHint() == null) goto L9;
        return;
    L9:
        if (ManufacturerUtils.isMeizuDevice() == false) goto L15;
        setHint(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo r3) {
        InputConnection r0 = super.onCreateInputConnection(r3);
        if (r0 != null) goto L5;
    L7:
        return r0;
    L5:
        if (r3.hintText != null) goto L7;
        r3.hintText = getHintFromLayout();
        goto L7
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
        super.onInitializeAccessibilityNodeInfo(r1);
        getTextInputLayout();
    }

    @Override // android.view.View
    public boolean requestRectangleOnScreen(Rect r6) {
        TextInputLayout r0 = getTextInputLayout();
        if (shouldUseTextInputLayoutFocusedRect(r0) == false) goto L8;
        if (r6 == null) goto L8;
        int r02 = r0.getHeight() - getHeight();
        this.parentRect.set(r6.left, r6.top, r6.right, r6.bottom + r02);
        return super.requestRectangleOnScreen(this.parentRect);
    L8:
        return super.requestRectangleOnScreen(r6);
    }

    public void setTextInputLayoutFocusedRectEnabled(boolean r1) {
        this.textInputLayoutFocusedRectEnabled = r1;
    }
}
