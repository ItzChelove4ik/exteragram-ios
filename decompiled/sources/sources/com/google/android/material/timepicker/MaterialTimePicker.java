package com.google.android.material.timepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.TooltipCompat;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.timepicker.TimePickerView;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class MaterialTimePicker extends DialogFragment implements TimePickerView.OnDoubleTapListener {
    public static final int INPUT_MODE_CLOCK = 0;
    static final String INPUT_MODE_EXTRA = "TIME_PICKER_INPUT_MODE";
    public static final int INPUT_MODE_KEYBOARD = 1;
    static final String NEGATIVE_BUTTON_TEXT_EXTRA = "TIME_PICKER_NEGATIVE_BUTTON_TEXT";
    static final String NEGATIVE_BUTTON_TEXT_RES_EXTRA = "TIME_PICKER_NEGATIVE_BUTTON_TEXT_RES";
    static final String OVERRIDE_THEME_RES_ID = "TIME_PICKER_OVERRIDE_THEME_RES_ID";
    static final String POSITIVE_BUTTON_TEXT_EXTRA = "TIME_PICKER_POSITIVE_BUTTON_TEXT";
    static final String POSITIVE_BUTTON_TEXT_RES_EXTRA = "TIME_PICKER_POSITIVE_BUTTON_TEXT_RES";
    static final String TIME_MODEL_EXTRA = "TIME_PICKER_TIME_MODEL";
    static final String TITLE_RES_EXTRA = "TIME_PICKER_TITLE_RES";
    static final String TITLE_TEXT_EXTRA = "TIME_PICKER_TITLE_TEXT";
    private TimePickerPresenter activePresenter;
    private Button cancelButton;
    private final Set<DialogInterface.OnCancelListener> cancelListeners;
    private int clockIcon;
    private final Set<DialogInterface.OnDismissListener> dismissListeners;
    private int inputMode;
    private int keyboardIcon;
    private MaterialButton modeButton;
    private final Set<View.OnClickListener> negativeButtonListeners;
    private CharSequence negativeButtonText;
    private int negativeButtonTextResId;
    private Button okButton;
    private int overrideThemeResId;
    private final Set<View.OnClickListener> positiveButtonListeners;
    private CharSequence positiveButtonText;
    private int positiveButtonTextResId;
    private ViewStub textInputStub;
    private TimeModel time;
    private TimePickerClockPresenter timePickerClockPresenter;
    private TimePickerTextInputPresenter timePickerTextInputPresenter;
    private TimePickerView timePickerView;
    private int titleResId;
    private CharSequence titleText;

    public static final class Builder {
        private Integer inputMode;
        private CharSequence negativeButtonText;
        private int negativeButtonTextResId;
        private int overrideThemeResId;
        private CharSequence positiveButtonText;
        private int positiveButtonTextResId;
        private TimeModel time;
        private CharSequence titleText;
        private int titleTextResId;

        public Builder() {
            this.time = new TimeModel();
            this.titleTextResId = 0;
            this.positiveButtonTextResId = 0;
            this.negativeButtonTextResId = 0;
            this.overrideThemeResId = 0;
        }

        public static /* synthetic */ TimeModel access$000(Builder r0) {
            return r0.time;
        }

        public static /* synthetic */ Integer access$100(Builder r0) {
            return r0.inputMode;
        }

        public static /* synthetic */ int access$200(Builder r0) {
            return r0.titleTextResId;
        }

        public static /* synthetic */ CharSequence access$300(Builder r0) {
            return r0.titleText;
        }

        public static /* synthetic */ int access$400(Builder r0) {
            return r0.positiveButtonTextResId;
        }

        public static /* synthetic */ CharSequence access$500(Builder r0) {
            return r0.positiveButtonText;
        }

        public static /* synthetic */ int access$600(Builder r0) {
            return r0.negativeButtonTextResId;
        }

        public static /* synthetic */ CharSequence access$700(Builder r0) {
            return r0.negativeButtonText;
        }

        public static /* synthetic */ int access$800(Builder r0) {
            return r0.overrideThemeResId;
        }

        public MaterialTimePicker build() {
            return MaterialTimePicker.access$900(this);
        }

        public Builder setHour(int r2) {
            this.time.setHourOfDay(r2);
            return this;
        }

        public Builder setInputMode(int r1) {
            this.inputMode = Integer.valueOf(r1);
            return this;
        }

        public Builder setMinute(int r2) {
            this.time.setMinute(r2);
            return this;
        }

        public Builder setNegativeButtonText(int r1) {
            this.negativeButtonTextResId = r1;
            return this;
        }

        public Builder setNegativeButtonText(CharSequence r1) {
            this.negativeButtonText = r1;
            return this;
        }

        public Builder setPositiveButtonText(int r1) {
            this.positiveButtonTextResId = r1;
            return this;
        }

        public Builder setPositiveButtonText(CharSequence r1) {
            this.positiveButtonText = r1;
            return this;
        }

        public Builder setTheme(int r1) {
            this.overrideThemeResId = r1;
            return this;
        }

        public Builder setTimeFormat(int r4) {
            TimeModel r0 = this.time;
            int r1 = r0.hour;
            int r02 = r0.minute;
            TimeModel r2 = new TimeModel(r4);
            this.time = r2;
            r2.setMinute(r02);
            this.time.setHourOfDay(r1);
            return this;
        }

        public Builder setTitleText(int r1) {
            this.titleTextResId = r1;
            return this;
        }

        public Builder setTitleText(CharSequence r1) {
            this.titleText = r1;
            return this;
        }
    }

    public static final class ModeButtonData {
        final int contentDescriptionResId;
        final int iconResId;
        final int tooltipTextResId;

        public ModeButtonData(int r1, int r2, int r3) {
            this.iconResId = r1;
            this.contentDescriptionResId = r2;
            this.tooltipTextResId = r3;
        }
    }

    public static /* synthetic */ void $r8$lambda$3KnCEPbsynU1u2HHOPD3mxvQf58(MaterialTimePicker r2, ViewGroup r3, View r4) {
        TimePickerPresenter r0 = r2.activePresenter;
        if ((r0 instanceof TimePickerTextInputPresenter) == false) goto L8;
        TimePickerTextInputPresenter r02 = (TimePickerTextInputPresenter) r0;
        if (r02.hasError() == false) goto L8;
        r02.vibrateAndMaybeBeep(r3);
        r02.accessibilityFocusOnError();
        return;
    L8:
        Iterator<View.OnClickListener> r32 = r2.positiveButtonListeners.iterator();
    L10:
        if (r32.hasNext() == false) goto L12;
        r32.next().onClick(r4);
        goto L10
    L12:
        r2.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$6LI4ynSIuOQcLg2QaQGbiTlU2Hc(MaterialTimePicker r2, View r3) {
        Iterator<View.OnClickListener> r0 = r2.negativeButtonListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onClick(r3);
        goto L4
    L6:
        r2.dismiss();
    }

    /* renamed from: $r8$lambda$pi-nxgCSKLWy6LHDG69A2DAj8qk, reason: not valid java name */
    public static /* synthetic */ void m1910$r8$lambda$pinxgCSKLWy6LHDG69A2DAj8qk(MaterialTimePicker r0, View r1) {
        if (r0.inputMode != 0) goto L5;
        int r12 = 1;
    L6:
        r0.inputMode = r12;
        r0.updateInputMode(r0.modeButton);
        return;
    L5:
        r12 = 0;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$vk_2VB5ZvE1tpGsL0xuRIBlnO1s(MaterialTimePicker r1) {
        TimePickerPresenter r12 = r1.activePresenter;
        if ((r12 instanceof TimePickerTextInputPresenter) == false) goto L6;
        ((TimePickerTextInputPresenter) r12).resetChecked();
        return;
    }

    public MaterialTimePicker() {
        this.positiveButtonListeners = new LinkedHashSet();
        this.negativeButtonListeners = new LinkedHashSet();
        this.cancelListeners = new LinkedHashSet();
        this.dismissListeners = new LinkedHashSet();
        this.titleResId = 0;
        this.positiveButtonTextResId = 0;
        this.negativeButtonTextResId = 0;
        this.inputMode = 0;
        this.overrideThemeResId = 0;
    }

    public static /* synthetic */ MaterialTimePicker access$900(Builder r0) {
        return newInstance(r0);
    }

    private ModeButtonData getModeButtonData(int r3) {
        if (r3 == 0) goto L10;
        if (r3 == 1) goto L6;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("no button data for mode: ", r3);
        return null;
    L6:
        return new ModeButtonData(this.clockIcon, R.string.material_timepicker_clock_mode_description, R.string.material_timepicker_clock_mode_tooltip);
    L10:
        return new ModeButtonData(this.keyboardIcon, R.string.material_timepicker_text_input_mode_description, R.string.material_timepicker_text_input_mode_tooltip);
    }

    private int getThemeResId() {
        int r0 = this.overrideThemeResId;
        if (r0 == 0) goto L5;
        return r0;
    L5:
        TypedValue r1 = MaterialAttributes.resolve(requireContext(), R.attr.materialTimePickerTheme);
        if (r1 != null) goto L10;
        return 0;
    L10:
        return r1.data;
    }

    private TimePickerPresenter initializeOrRetrieveActivePresenterForMode(int r1, TimePickerView r2, ViewStub r3) {
        if (r1 != 0) goto L9;
        TimePickerClockPresenter r12 = this.timePickerClockPresenter;
        if (r12 != null) goto L6;
        r12 = new TimePickerClockPresenter(r2, this.time);
    L6:
        this.timePickerClockPresenter = r12;
        return r12;
    L9:
        if (this.timePickerTextInputPresenter != null) goto L11;
        this.timePickerTextInputPresenter = new TimePickerTextInputPresenter((LinearLayout) r3.inflate(), this.time);
    L11:
        this.timePickerTextInputPresenter.clearError();
        this.timePickerTextInputPresenter.clearCheck();
        return this.timePickerTextInputPresenter;
    }

    private static MaterialTimePicker newInstance(Builder r4) {
        MaterialTimePicker r0 = new MaterialTimePicker();
        Bundle r1 = new Bundle();
        r1.putParcelable(TIME_MODEL_EXTRA, Builder.access$000(r4));
        if (Builder.access$100(r4) == null) goto L5;
        r1.putInt(INPUT_MODE_EXTRA, Builder.access$100(r4).intValue());
    L5:
        r1.putInt(TITLE_RES_EXTRA, Builder.access$200(r4));
        if (Builder.access$300(r4) == null) goto L8;
        r1.putCharSequence(TITLE_TEXT_EXTRA, Builder.access$300(r4));
    L8:
        r1.putInt(POSITIVE_BUTTON_TEXT_RES_EXTRA, Builder.access$400(r4));
        if (Builder.access$500(r4) == null) goto L11;
        r1.putCharSequence(POSITIVE_BUTTON_TEXT_EXTRA, Builder.access$500(r4));
    L11:
        r1.putInt(NEGATIVE_BUTTON_TEXT_RES_EXTRA, Builder.access$600(r4));
        if (Builder.access$700(r4) == null) goto L14;
        r1.putCharSequence(NEGATIVE_BUTTON_TEXT_EXTRA, Builder.access$700(r4));
    L14:
        r1.putInt(OVERRIDE_THEME_RES_ID, Builder.access$800(r4));
        r0.setArguments(r1);
        return r0;
    }

    private void restoreState(Bundle r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        TimeModel r0 = (TimeModel) r4.getParcelable(TIME_MODEL_EXTRA);
        this.time = r0;
        if (r0 != null) goto L7;
        this.time = new TimeModel();
    L7:
        int r1 = 1;
        if (getResources().getBoolean(R.bool.timepicker_force_input_mode_keyboard) == false) goto L10;
    L13:
        this.inputMode = r4.getInt(INPUT_MODE_EXTRA, r1);
        this.titleResId = r4.getInt(TITLE_RES_EXTRA, 0);
        this.titleText = r4.getCharSequence(TITLE_TEXT_EXTRA);
        this.positiveButtonTextResId = r4.getInt(POSITIVE_BUTTON_TEXT_RES_EXTRA, 0);
        this.positiveButtonText = r4.getCharSequence(POSITIVE_BUTTON_TEXT_EXTRA);
        this.negativeButtonTextResId = r4.getInt(NEGATIVE_BUTTON_TEXT_RES_EXTRA, 0);
        this.negativeButtonText = r4.getCharSequence(NEGATIVE_BUTTON_TEXT_EXTRA);
        this.overrideThemeResId = r4.getInt(OVERRIDE_THEME_RES_ID, 0);
        return;
    L10:
        if (this.time.format == 1) goto L13;
        r1 = 0;
        goto L13
    }

    private void updateCancelButtonVisibility() {
        Button r0 = this.cancelButton;
        if (r0 != null) goto L5;
        return;
    L5:
        if (isCancelable() == false) goto L7;
        int r1 = 0;
    L8:
        r0.setVisibility(r1);
        return;
    L7:
        r1 = 8;
        goto L8
    }

    private void updateInputMode(MaterialButton r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (this.timePickerView != null) goto L6;
        return;
    L6:
        if (this.textInputStub == null) goto L15;
        TimePickerPresenter r0 = this.activePresenter;
        if (r0 == null) goto L11;
        r0.hide();
    L11:
        TimePickerPresenter r02 = initializeOrRetrieveActivePresenterForMode(this.inputMode, this.timePickerView, this.textInputStub);
        this.activePresenter = r02;
        r02.show();
        this.activePresenter.invalidate();
        ModeButtonData r03 = getModeButtonData(this.inputMode);
        r4.setIconResource(r03.iconResId);
        r4.setContentDescription(getResources().getString(r03.contentDescriptionResId));
        TooltipCompat.setTooltipText(r4, getResources().getString(r03.tooltipTextResId));
        r4.sendAccessibilityEvent(4);
        return;
    }

    public boolean addOnCancelListener(DialogInterface.OnCancelListener r1) {
        return this.cancelListeners.add(r1);
    }

    public boolean addOnDismissListener(DialogInterface.OnDismissListener r1) {
        return this.dismissListeners.add(r1);
    }

    public boolean addOnNegativeButtonClickListener(View.OnClickListener r1) {
        return this.negativeButtonListeners.add(r1);
    }

    public boolean addOnPositiveButtonClickListener(View.OnClickListener r1) {
        return this.positiveButtonListeners.add(r1);
    }

    public void clearOnCancelListeners() {
        this.cancelListeners.clear();
    }

    public void clearOnDismissListeners() {
        this.dismissListeners.clear();
    }

    public void clearOnNegativeButtonClickListeners() {
        this.negativeButtonListeners.clear();
    }

    public void clearOnPositiveButtonClickListeners() {
        this.positiveButtonListeners.clear();
    }

    public int getHour() {
        return this.time.hour % 24;
    }

    public int getInputMode() {
        return this.inputMode;
    }

    public int getMinute() {
        return this.time.minute;
    }

    public TimePickerClockPresenter getTimePickerClockPresenter() {
        return this.timePickerClockPresenter;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface r3) {
        Iterator<DialogInterface.OnCancelListener> r0 = this.cancelListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onCancel(r3);
        goto L4
    L6:
        super.onCancel(r3);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle r1) {
        super.onCreate(r1);
        if (r1 != null) goto L5;
        r1 = getArguments();
    L5:
        restoreState(r1);
    }

    @Override // androidx.fragment.app.DialogFragment
    public final Dialog onCreateDialog(Bundle r7) {
        Dialog r72 = new Dialog(requireContext(), getThemeResId());
        Context r0 = r72.getContext();
        MaterialShapeDrawable r1 = new MaterialShapeDrawable(r0, null, R.attr.materialTimePickerStyle, R.style.Widget_MaterialComponents_TimePicker);
        TypedArray r2 = r0.obtainStyledAttributes(null, R.styleable.MaterialTimePicker, R.attr.materialTimePickerStyle, R.style.Widget_MaterialComponents_TimePicker);
        this.clockIcon = r2.getResourceId(R.styleable.MaterialTimePicker_clockIcon, 0);
        this.keyboardIcon = r2.getResourceId(R.styleable.MaterialTimePicker_keyboardIcon, 0);
        int r6 = r2.getColor(R.styleable.MaterialTimePicker_backgroundTint, 0);
        r2.recycle();
        r1.initializeElevationOverlay(r0);
        r1.setFillColor(ColorStateList.valueOf(r6));
        Window r62 = r72.getWindow();
        r62.setBackgroundDrawable(r1);
        r62.requestFeature(1);
        r62.setLayout(-2, -2);
        r1.setElevation(r62.getDecorView().getElevation());
        return r72;
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater r1, ViewGroup r2, Bundle r3) {
        ViewGroup r12 = (ViewGroup) r1.inflate(R.layout.material_timepicker_dialog, r2);
        TimePickerView r22 = (TimePickerView) r12.findViewById(R.id.material_timepicker_view);
        this.timePickerView = r22;
        r22.setOnDoubleTapListener(this);
        this.textInputStub = (ViewStub) r12.findViewById(R.id.material_textinput_timepicker);
        this.modeButton = (MaterialButton) r12.findViewById(R.id.material_timepicker_mode_button);
        this.okButton = (Button) r12.findViewById(R.id.material_timepicker_ok_button);
        this.cancelButton = (Button) r12.findViewById(R.id.material_timepicker_cancel_button);
        TextView r23 = (TextView) r12.findViewById(R.id.header_title);
        int r32 = this.titleResId;
        if (r32 == 0) goto L6;
        r23.setText(r32);
    L8:
        updateInputMode(this.modeButton);
        this.okButton.setOnClickListener(new MaterialTimePicker$$ExternalSyntheticLambda0(this, r12));
        int r24 = this.positiveButtonTextResId;
        if (r24 == 0) goto L12;
        this.okButton.setText(r24);
    L14:
        this.cancelButton.setOnClickListener(new MaterialTimePicker$$ExternalSyntheticLambda1(this));
        int r25 = this.negativeButtonTextResId;
        if (r25 == 0) goto L18;
        this.cancelButton.setText(r25);
    L20:
        updateCancelButtonVisibility();
        this.modeButton.setOnClickListener(new MaterialTimePicker$$ExternalSyntheticLambda2(this));
        return r12;
    L18:
        if (TextUtils.isEmpty(this.negativeButtonText) == true) goto L20;
        this.cancelButton.setText(this.negativeButtonText);
        goto L20
    L12:
        if (TextUtils.isEmpty(this.positiveButtonText) == true) goto L14;
        this.okButton.setText(this.positiveButtonText);
        goto L14
    L6:
        if (TextUtils.isEmpty(this.titleText) == true) goto L8;
        r23.setText(this.titleText);
        goto L8
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.activePresenter = null;
        this.timePickerClockPresenter = null;
        this.timePickerTextInputPresenter = null;
        TimePickerView r1 = this.timePickerView;
        if (r1 == null) goto L6;
        r1.setOnDoubleTapListener(null);
        this.timePickerView = null;
        return;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r3) {
        Iterator<DialogInterface.OnDismissListener> r0 = this.dismissListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onDismiss(r3);
        goto L4
    L6:
        super.onDismiss(r3);
    }

    @Override // com.google.android.material.timepicker.TimePickerView.OnDoubleTapListener
    public void onDoubleTap() {
        this.inputMode = 1;
        updateInputMode(this.modeButton);
        this.timePickerTextInputPresenter.resetChecked();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle r3) {
        super.onSaveInstanceState(r3);
        r3.putParcelable(TIME_MODEL_EXTRA, this.time);
        r3.putInt(INPUT_MODE_EXTRA, this.inputMode);
        r3.putInt(TITLE_RES_EXTRA, this.titleResId);
        r3.putCharSequence(TITLE_TEXT_EXTRA, this.titleText);
        r3.putInt(POSITIVE_BUTTON_TEXT_RES_EXTRA, this.positiveButtonTextResId);
        r3.putCharSequence(POSITIVE_BUTTON_TEXT_EXTRA, this.positiveButtonText);
        r3.putInt(NEGATIVE_BUTTON_TEXT_RES_EXTRA, this.negativeButtonTextResId);
        r3.putCharSequence(NEGATIVE_BUTTON_TEXT_EXTRA, this.negativeButtonText);
        r3.putInt(OVERRIDE_THEME_RES_ID, this.overrideThemeResId);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View r3, Bundle r4) {
        super.onViewCreated(r3, r4);
        if ((this.activePresenter instanceof TimePickerTextInputPresenter) == false) goto L6;
        r3.postDelayed(new MaterialTimePicker$$ExternalSyntheticLambda3(this), 100);
        return;
    }

    public boolean removeOnCancelListener(DialogInterface.OnCancelListener r1) {
        return this.cancelListeners.remove(r1);
    }

    public boolean removeOnDismissListener(DialogInterface.OnDismissListener r1) {
        return this.dismissListeners.remove(r1);
    }

    public boolean removeOnNegativeButtonClickListener(View.OnClickListener r1) {
        return this.negativeButtonListeners.remove(r1);
    }

    public boolean removeOnPositiveButtonClickListener(View.OnClickListener r1) {
        return this.positiveButtonListeners.remove(r1);
    }

    public void setActivePresenter(TimePickerPresenter r1) {
        this.activePresenter = r1;
    }

    @Override // androidx.fragment.app.DialogFragment
    public void setCancelable(boolean r1) {
        super.setCancelable(r1);
        updateCancelButtonVisibility();
    }

    public void setHour(int r2) {
        this.time.setHourOfDay(r2);
        TimePickerPresenter r1 = this.activePresenter;
        if (r1 == null) goto L6;
        r1.invalidate();
        return;
    }

    public void setMinute(int r2) {
        this.time.setMinute(r2);
        TimePickerPresenter r1 = this.activePresenter;
        if (r1 == null) goto L6;
        r1.invalidate();
        return;
    }
}
