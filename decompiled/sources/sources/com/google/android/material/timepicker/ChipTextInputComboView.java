package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
class ChipTextInputComboView extends FrameLayout implements Checkable {
    private final Chip chip;
    private CharSequence chipText;
    private final EditText editText;
    private final AccessibilityDelegateCompat editTextAccessibilityDelegate;
    private boolean hasError;
    private TextView label;
    private ColorStateList originalChipBackgroundColor;
    private int originalChipStrokeColor;
    private ColorStateList originalChipTextColor;
    private ColorStateList originalEditTextColor;
    private ColorStateList originalEditTextCursorColor;
    private ColorStateList originalLabelColor;
    private final TextInputLayout textInputLayout;
    private TextWatcher watcher;

    /* renamed from: com.google.android.material.timepicker.ChipTextInputComboView$1, reason: invalid class name */
    public class AnonymousClass1 extends AccessibilityDelegateCompat {
        final /* synthetic */ ChipTextInputComboView this$0;

        public AnonymousClass1(ChipTextInputComboView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            r2.setText(((EditText) r1).getText());
            r2.setHintText(ChipTextInputComboView.access$100(this.this$0).getText());
            r2.setMaxTextLength(2);
        }
    }

    public class TextFormatter extends TextWatcherAdapter {
        private static final String DEFAULT_TEXT = "00";
        final /* synthetic */ ChipTextInputComboView this$0;

        private TextFormatter(ChipTextInputComboView r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ TextFormatter(ChipTextInputComboView r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable r4) {
            boolean r0 = TextUtils.isEmpty(r4);
            ChipTextInputComboView r1 = this.this$0;
            if (r0 == false) goto L6;
            ChipTextInputComboView.access$202(r1, ChipTextInputComboView.access$300(r1, DEFAULT_TEXT));
            return;
        L6:
            String r42 = ChipTextInputComboView.access$300(r1, r4);
            ChipTextInputComboView r02 = this.this$0;
            if (TextUtils.isEmpty(r42) == false) goto L9;
            r42 = ChipTextInputComboView.access$300(this.this$0, DEFAULT_TEXT);
        L9:
            ChipTextInputComboView.access$202(r02, r42);
        }
    }

    public ChipTextInputComboView(Context r2) {
        this(r2, null);
    }

    public ChipTextInputComboView(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public ChipTextInputComboView(Context r4, AttributeSet r5, int r6) {
        super(r4, r5, r6);
        this.chipText = _UrlKt.FRAGMENT_ENCODE_SET;
        this.hasError = false;
        LayoutInflater r42 = LayoutInflater.from(r4);
        Chip r62 = (Chip) r42.inflate(R.layout.material_time_chip, this, false);
        this.chip = r62;
        r62.setAccessibilityClassName("android.view.View");
        TextInputLayout r43 = (TextInputLayout) r42.inflate(R.layout.material_time_input, this, false);
        this.textInputLayout = r43;
        EditText r0 = r43.getEditText();
        this.editText = r0;
        r0.setVisibility(4);
        TextFormatter r1 = new TextFormatter(this, null);
        this.watcher = r1;
        r0.addTextChangedListener(r1);
        updateHintLocales();
        addView(r62);
        addView(r43);
        this.label = (TextView) findViewById(R.id.material_label);
        r0.setId(View.generateViewId());
        this.label.setLabelFor(r0.getId());
        r0.setSaveEnabled(false);
        r0.setLongClickable(false);
        this.editTextAccessibilityDelegate = new AnonymousClass1(this);
    }

    public static /* synthetic */ TextView access$100(ChipTextInputComboView r0) {
        return r0.label;
    }

    public static /* synthetic */ CharSequence access$202(ChipTextInputComboView r0, CharSequence r1) {
        r0.chipText = r1;
        return r1;
    }

    public static /* synthetic */ String access$300(ChipTextInputComboView r0, CharSequence r1) {
        return r0.formatText(r1);
    }

    private void applyErrorColors() {
        this.originalChipBackgroundColor = this.chip.getChipBackgroundColor();
        this.originalChipTextColor = this.chip.getTextColors();
        this.originalEditTextColor = this.editText.getTextColors();
        this.originalLabelColor = this.label.getTextColors();
        this.originalChipStrokeColor = this.textInputLayout.getBoxStrokeColor();
        int r0 = MaterialColors.getColor(this, R$attr.colorError);
        ColorStateList r1 = MaterialColors.getColorStateListOrNull(getContext(), R.attr.colorErrorContainer);
        ColorStateList r2 = MaterialColors.getColorStateListOrNull(getContext(), R.attr.colorOnErrorContainer);
        if (r1 == null) goto L9;
        if (r2 == null) goto L10;
        this.chip.setChipBackgroundColor(r1);
        this.chip.setTextColor(r2);
        this.editText.setTextColor(r2);
        this.textInputLayout.setBoxStrokeColor(r0);
        this.label.setTextColor(r0);
        if (Build.VERSION.SDK_INT < 29) goto L11;
        this.originalEditTextCursorColor = this.textInputLayout.getCursorColor();
        this.textInputLayout.setCursorColor(r2);
        return;
    L11:
        return;
    L10:
        return;
    }

    private void clearErrorColors() {
        this.chip.setChipBackgroundColor(this.originalChipBackgroundColor);
        this.chip.setTextColor(this.originalChipTextColor);
        this.editText.setTextColor(this.originalEditTextColor);
        this.textInputLayout.setBoxStrokeColor(this.originalChipStrokeColor);
        this.label.setTextColor(this.originalLabelColor);
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.textInputLayout.setCursorColor(this.originalEditTextCursorColor);
        return;
    }

    private String formatText(CharSequence r1) {
        return TimeModel.formatText(getResources(), r1);
    }

    private void updateHintLocales() {
        LocaleList r0 = getContext().getResources().getConfiguration().getLocales();
        this.editText.setImeHintLocales(r0);
    }

    public void addInputFilter(InputFilter r3) {
        InputFilter[] r0 = this.editText.getFilters();
        InputFilter[] r1 = (InputFilter[]) Arrays.copyOf(r0, r0.length + 1);
        r1[r0.length] = r3;
        this.editText.setFilters(r1);
    }

    public CharSequence getChipText() {
        return this.chipText;
    }

    public TextInputLayout getTextInput() {
        return this.textInputLayout;
    }

    public boolean hasError() {
        return this.hasError;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.chip.isChecked();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        updateHintLocales();
    }

    public void requestAccessibilityFocus() {
        if (this.editText.getVisibility() != 0) goto L6;
        this.editText.sendAccessibilityEvent(8);
        return;
    L6:
        this.chip.sendAccessibilityEvent(8);
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean r3) {
        this.chip.setChecked(r3);
        Chip r0 = this.chip;
        if (r3 == false) goto L5;
        r0.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.chip.setImportantForAccessibility(2);
    L6:
        EditText r02 = this.editText;
        if (r3 == false) goto L9;
        int r32 = 0;
    L10:
        r02.setVisibility(r32);
        if (isChecked() == false) goto L14;
        ViewUtils.requestFocusAndShowKeyboard(this.editText, false);
        return;
    L14:
        return;
    L9:
        r32 = 4;
        goto L10
    L5:
        r0.setText(this.chipText);
        this.chip.setImportantForAccessibility(1);
        goto L6
    }

    public void setChipDelegate(AccessibilityDelegateCompat r1) {
        ViewCompat.setAccessibilityDelegate(this.chip, r1);
    }

    public void setCursorVisible(boolean r1) {
        this.editText.setCursorVisible(r1);
    }

    public void setError(boolean r2) {
        if (this.hasError != r2) goto L5;
        return;
    L5:
        this.hasError = r2;
        if (r2 == false) goto L9;
        applyErrorColors();
        return;
    L9:
        clearErrorColors();
    }

    public void setHelperText(CharSequence r1) {
        this.label.setText(r1);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener r1) {
        this.chip.setOnClickListener(r1);
    }

    @Override // android.view.View
    public void setTag(int r1, Object r2) {
        this.chip.setTag(r1, r2);
    }

    public void setText(CharSequence r3) {
        String r32 = formatText(r3);
        this.chipText = r32;
        this.chip.setText(r32);
        if (TextUtils.isEmpty(r32) == true) goto L6;
        this.editText.removeTextChangedListener(this.watcher);
        this.editText.setText(r32);
        ViewCompat.setAccessibilityDelegate(this.editText, this.editTextAccessibilityDelegate);
        this.editText.addTextChangedListener(this.watcher);
        return;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        this.chip.toggle();
    }
}
