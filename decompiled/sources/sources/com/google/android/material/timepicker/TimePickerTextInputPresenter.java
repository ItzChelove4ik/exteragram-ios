package com.google.android.material.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.timepicker.TimePickerView;
import java.util.Locale;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes5.dex */
class TimePickerTextInputPresenter implements TimePickerView.OnSelectionChange, TimePickerPresenter {
    private static final int HOURS_MAX_LENGTH = 2;
    private static final int HOURS_MAX_VALUE_12H = 12;
    private static final int HOURS_MAX_VALUE_24H = 23;
    private static final int MINUTES_MAX_LENGTH = 2;
    private static final int MINUTES_MAX_VALUE = 59;
    private final TimePickerTextInputKeyController controller;
    private final EditText hourEditText;
    private final String hourError24hText;
    private final String hourErrorText;
    private final TextView hourLabel;
    private final String hourText;
    private final ChipTextInputComboView hourTextInput;
    private final TextWatcher hourTextWatcher;
    private final EditText minuteEditText;
    private final String minuteErrorText;
    private final TextView minuteLabel;
    private final String minuteText;
    private final ChipTextInputComboView minuteTextInput;
    private final TextWatcher minuteTextWatcher;
    private final TimeModel time;
    private final LinearLayout timePickerView;
    private MaterialButtonToggleGroup toggle;

    /* renamed from: com.google.android.material.timepicker.TimePickerTextInputPresenter$1, reason: invalid class name */
    public class AnonymousClass1 extends TextWatcherAdapter {
        final /* synthetic */ TimePickerTextInputPresenter this$0;

        public AnonymousClass1(TimePickerTextInputPresenter r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
        L17:
            TimePickerTextInputPresenter.access$300(this.this$0);
            return;
        L3:
            if (TextUtils.isEmpty(r3) == false) goto L7;
            TimePickerTextInputPresenter.access$000(this.this$0).setMinute(0);     // Catch: NumberFormatException -> L17
            TimePickerTextInputPresenter.access$100(this.this$0);     // Catch: NumberFormatException -> L17
            return;
        L7:
            if (r3.length() <= 2) goto L10;
            r3.delete(2, r3.length());     // Catch: NumberFormatException -> L17
            TimePickerTextInputPresenter r32 = this.this$0;     // Catch: NumberFormatException -> L17
            r32.vibrateAndMaybeBeep(TimePickerTextInputPresenter.access$200(r32));     // Catch: NumberFormatException -> L17
            return;
        L10:
            int r33 = Integer.parseInt(r3.toString());     // Catch: NumberFormatException -> L17
            TimePickerTextInputPresenter r0 = this.this$0;
            if (r33 <= 59) goto L14;
            TimePickerTextInputPresenter.access$300(r0);     // Catch: NumberFormatException -> L17
        L15:
            TimePickerTextInputPresenter.access$000(this.this$0).setMinute(r33);     // Catch: NumberFormatException -> L17
            return;
        L14:
            TimePickerTextInputPresenter.access$100(r0);     // Catch: NumberFormatException -> L17
            goto L15
        }
    }

    /* renamed from: com.google.android.material.timepicker.TimePickerTextInputPresenter$2, reason: invalid class name */
    public class AnonymousClass2 extends TextWatcherAdapter {
        final /* synthetic */ TimePickerTextInputPresenter this$0;

        public AnonymousClass2(TimePickerTextInputPresenter r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
        L22:
            TimePickerTextInputPresenter.access$600(this.this$0);
            return;
        L3:
            if (TextUtils.isEmpty(r3) == false) goto L7;
            TimePickerTextInputPresenter.access$000(this.this$0).setHour(0);     // Catch: NumberFormatException -> L22
            TimePickerTextInputPresenter.access$400(this.this$0);     // Catch: NumberFormatException -> L22
            return;
        L7:
            if (r3.length() <= 2) goto L10;
            r3.delete(2, r3.length());     // Catch: NumberFormatException -> L22
            TimePickerTextInputPresenter r32 = this.this$0;     // Catch: NumberFormatException -> L22
            r32.vibrateAndMaybeBeep(TimePickerTextInputPresenter.access$500(r32));     // Catch: NumberFormatException -> L22
            return;
        L10:
            int r33 = Integer.parseInt(r3.toString());     // Catch: NumberFormatException -> L22
            if (TimePickerTextInputPresenter.access$000(this.this$0).format != 0) goto L15;
            if (r33 <= 12) goto L15;
        L18:
            TimePickerTextInputPresenter.access$600(this.this$0);     // Catch: NumberFormatException -> L22
        L20:
            TimePickerTextInputPresenter.access$000(this.this$0).setHour(r33);     // Catch: NumberFormatException -> L22
            return;
        L15:
            if (TimePickerTextInputPresenter.access$000(this.this$0).format == 1) goto L17;
        L19:
            TimePickerTextInputPresenter.access$400(this.this$0);     // Catch: NumberFormatException -> L22
            goto L20
        L17:
            if (r33 <= 23) goto L19;
            goto L18
        }
    }

    /* renamed from: com.google.android.material.timepicker.TimePickerTextInputPresenter$3, reason: invalid class name */
    public class AnonymousClass3 extends ClickActionDelegate {
        final /* synthetic */ TimePickerTextInputPresenter this$0;
        final /* synthetic */ Resources val$res;
        final /* synthetic */ TimeModel val$time;

        public AnonymousClass3(TimePickerTextInputPresenter r1, Context r2, int r3, Resources r4, TimeModel r5) {
            this.this$0 = r1;
            this.val$res = r4;
            this.val$time = r5;
            super(r2, r3);
        }

        @Override // com.google.android.material.timepicker.ClickActionDelegate, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r4, AccessibilityNodeInfoCompat r5) {
            super.onInitializeAccessibilityNodeInfo(r4, r5);
            r5.setContentDescription(this.val$res.getString(R.string.material_timepicker_hour) + " " + r4.getResources().getString(this.val$time.getHourContentDescriptionResId(), new Object[]{String.valueOf(this.val$time.getHourForDisplay())}));
        }
    }

    /* renamed from: com.google.android.material.timepicker.TimePickerTextInputPresenter$4, reason: invalid class name */
    public class AnonymousClass4 extends ClickActionDelegate {
        final /* synthetic */ TimePickerTextInputPresenter this$0;
        final /* synthetic */ Resources val$res;
        final /* synthetic */ TimeModel val$time;

        public AnonymousClass4(TimePickerTextInputPresenter r1, Context r2, int r3, Resources r4, TimeModel r5) {
            this.this$0 = r1;
            this.val$res = r4;
            this.val$time = r5;
            super(r2, r3);
        }

        @Override // com.google.android.material.timepicker.ClickActionDelegate, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r4, AccessibilityNodeInfoCompat r5) {
            super.onInitializeAccessibilityNodeInfo(r4, r5);
            r5.setContentDescription(this.val$res.getString(R.string.material_timepicker_minute) + " " + r4.getResources().getString(R.string.material_minute_suffix, new Object[]{String.valueOf(this.val$time.minute)}));
        }
    }

    /* renamed from: com.google.android.material.timepicker.TimePickerTextInputPresenter$5, reason: invalid class name */
    public class AnonymousClass5 extends View.AccessibilityDelegate {
        final /* synthetic */ TimePickerTextInputPresenter this$0;
        final /* synthetic */ int val$contentDescriptionResId;
        final /* synthetic */ Resources val$res;

        public AnonymousClass5(TimePickerTextInputPresenter r1, Resources r2, int r3) {
            this.this$0 = r1;
            this.val$res = r2;
            this.val$contentDescriptionResId = r3;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            r2.setText(this.val$res.getString(this.val$contentDescriptionResId));
        }
    }

    /* renamed from: $r8$lambda$ZpoZPJ-823VpZ9oqwqdZ_RXwQG8, reason: not valid java name */
    public static /* synthetic */ void m1911$r8$lambda$ZpoZPJ823VpZ9oqwqdZ_RXwQG8(TimePickerTextInputPresenter r1, View r2) {
        r1.getClass();
        r1.onSelectionChanged(((Integer) r2.getTag(R.id.selection_type)).intValue());
    }

    public static /* synthetic */ void $r8$lambda$n_snvJXUcsOZPGqz3GV6Xx8FPG8(TimePickerTextInputPresenter r0, MaterialButtonToggleGroup r1, int r2, boolean r3) {
        r0.getClass();
        if (r3 == true) goto L6;
        return;
    L6:
        if (r2 != R.id.material_clock_period_pm_button) goto L8;
        int r12 = 1;
    L9:
        r0.time.setPeriod(r12);
        return;
    L8:
        r12 = 0;
        goto L9
    }

    public TimePickerTextInputPresenter(LinearLayout r9, TimeModel r10) {
        this.minuteTextWatcher = new AnonymousClass1(this);
        this.hourTextWatcher = new AnonymousClass2(this);
        this.timePickerView = r9;
        this.time = r10;
        Resources r5 = r9.getResources();
        ChipTextInputComboView r0 = (ChipTextInputComboView) r9.findViewById(R.id.material_minute_text_input);
        this.minuteTextInput = r0;
        ChipTextInputComboView r7 = (ChipTextInputComboView) r9.findViewById(R.id.material_hour_text_input);
        this.hourTextInput = r7;
        TextView r1 = (TextView) r0.findViewById(R.id.material_label);
        this.minuteLabel = r1;
        TextView r2 = (TextView) r7.findViewById(R.id.material_label);
        this.hourLabel = r2;
        r1.setText(r5.getString(R.string.material_timepicker_minute));
        r1.setImportantForAccessibility(2);
        r2.setText(r5.getString(R.string.material_timepicker_hour));
        r2.setImportantForAccessibility(2);
        this.minuteText = r5.getString(R.string.material_timepicker_minute);
        this.hourText = r5.getString(R.string.material_timepicker_hour);
        this.minuteErrorText = r5.getString(R.string.material_timepicker_minute_error);
        this.hourErrorText = r5.getString(R.string.material_timepicker_hour_error);
        this.hourError24hText = r5.getString(R.string.material_timepicker_hour_error_24h);
        r0.setTag(R.id.selection_type, 12);
        r7.setTag(R.id.selection_type, 10);
        if (r10.format != 0) goto L5;
        setupPeriodToggle();
    L5:
        TimePickerTextInputPresenter$$ExternalSyntheticLambda1 r12 = new TimePickerTextInputPresenter$$ExternalSyntheticLambda1(this);
        r7.setOnClickListener(r12);
        r0.setOnClickListener(r12);
        EditText r13 = r7.getTextInput().getEditText();
        this.hourEditText = r13;
        r13.setAccessibilityDelegate(setTimeUnitAccessibilityLabel(r9.getResources(), R.string.material_timepicker_hour));
        EditText r14 = r0.getTextInput().getEditText();
        this.minuteEditText = r14;
        r14.setAccessibilityDelegate(setTimeUnitAccessibilityLabel(r9.getResources(), R.string.material_timepicker_minute));
        this.controller = new TimePickerTextInputKeyController(r7, r0, r10);
        r7.setChipDelegate(new AnonymousClass3(this, r9.getContext(), R.string.material_hour_selection, r5, r10));
        r0.setChipDelegate(new AnonymousClass4(this, r9.getContext(), R.string.material_minute_selection, r5, r10));
        initialize();
    }

    public static /* synthetic */ TimeModel access$000(TimePickerTextInputPresenter r0) {
        return r0.time;
    }

    public static /* synthetic */ void access$100(TimePickerTextInputPresenter r0) {
        r0.clearMinuteError();
    }

    public static /* synthetic */ EditText access$200(TimePickerTextInputPresenter r0) {
        return r0.minuteEditText;
    }

    public static /* synthetic */ void access$300(TimePickerTextInputPresenter r0) {
        r0.setMinuteError();
    }

    public static /* synthetic */ void access$400(TimePickerTextInputPresenter r0) {
        r0.clearHourError();
    }

    public static /* synthetic */ EditText access$500(TimePickerTextInputPresenter r0) {
        return r0.hourEditText;
    }

    public static /* synthetic */ void access$600(TimePickerTextInputPresenter r0) {
        r0.setHourError();
    }

    private void addTextWatchers() {
        this.hourEditText.addTextChangedListener(this.hourTextWatcher);
        this.minuteEditText.addTextChangedListener(this.minuteTextWatcher);
    }

    private void beep(Context r1) {
        AudioManager r0 = (AudioManager) r1.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        if (r0 == null) goto L6;
        r0.playSoundEffect(9);
        return;
    }

    private void clearHourError() {
        this.hourTextInput.setError(false);
        this.hourLabel.setText(this.hourText);
    }

    private void clearMinuteError() {
        this.minuteTextInput.setError(false);
        this.minuteLabel.setText(this.minuteText);
    }

    private static boolean isTouchExplorationEnabled(Context r1) {
        AccessibilityManager r12 = (AccessibilityManager) r1.getSystemService("accessibility");
        if (r12 != null) goto L5;
        return false;
    L5:
        if (r12.isTouchExplorationEnabled() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private void removeTextWatchers() {
        this.hourEditText.removeTextChangedListener(this.hourTextWatcher);
        this.minuteEditText.removeTextChangedListener(this.minuteTextWatcher);
    }

    private void requestAccessibilityFocusAndAnnounce(ChipTextInputComboView r1, TextView r2) {
        r1.requestAccessibilityFocus();
        r2.announceForAccessibility(r2.getText());
    }

    @SuppressLint({"FlaggedApi"})
    private void setHourError() {
        this.hourTextInput.setError(true);
        TextView r0 = this.hourLabel;
        if (this.time.format != 1) goto L5;
        String r1 = this.hourError24hText;
    L6:
        r0.setText(r1);
        TextView r02 = this.hourLabel;
        r02.announceForAccessibility(r02.getText());
        vibrateAndMaybeBeep(this.hourLabel);
        return;
    L5:
        r1 = this.hourErrorText;
        goto L6
    }

    @SuppressLint({"FlaggedApi"})
    private void setMinuteError() {
        this.minuteTextInput.setError(true);
        this.minuteLabel.setText(this.minuteErrorText);
        TextView r0 = this.minuteLabel;
        r0.announceForAccessibility(r0.getText());
        vibrateAndMaybeBeep(this.minuteLabel);
    }

    private void setTime(TimeModel r5) {
        removeTextWatchers();
        Locale r0 = this.timePickerView.getResources().getConfiguration().locale;
        String r1 = String.format(r0, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r5.minute)});
        String r02 = String.format(r0, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r5.getHourForDisplay())});
        this.minuteTextInput.setText(r1);
        this.hourTextInput.setText(r02);
        addTextWatchers();
        onSelectionChanged(r5.selection);
    }

    private View.AccessibilityDelegate setTimeUnitAccessibilityLabel(Resources r2, int r3) {
        return new AnonymousClass5(this, r2, r3);
    }

    private void setupPeriodToggle() {
        MaterialButtonToggleGroup r0 = (MaterialButtonToggleGroup) this.timePickerView.findViewById(R.id.material_clock_period_toggle);
        this.toggle = r0;
        r0.addOnButtonCheckedListener(new TimePickerTextInputPresenter$$ExternalSyntheticLambda0(this));
        this.toggle.setVisibility(0);
        updateSelection();
    }

    private void updateSelection() {
        MaterialButtonToggleGroup r0 = this.toggle;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.time.period != 0) goto L8;
        int r1 = R.id.material_clock_period_am_button;
    L9:
        r0.check(r1);
        return;
    L8:
        r1 = R.id.material_clock_period_pm_button;
        goto L9
    }

    private void vibrate(View r1) {
        ViewCompat.performHapticFeedback(r1, 17);
    }

    public void accessibilityFocusOnError() {
        if (this.hourTextInput.hasError() == false) goto L7;
        requestAccessibilityFocusAndAnnounce(this.hourTextInput, this.hourLabel);
        return;
    L7:
        if (this.minuteTextInput.hasError() == false) goto L10;
        requestAccessibilityFocusAndAnnounce(this.minuteTextInput, this.minuteLabel);
        return;
    }

    public void clearCheck() {
        this.minuteTextInput.setChecked(false);
        this.hourTextInput.setChecked(false);
    }

    public void clearError() {
        clearMinuteError();
        clearHourError();
    }

    public boolean hasError() {
        if (this.minuteTextInput.hasError() == false) goto L5;
        return true;
    L5:
        if (this.hourTextInput.hasError() == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void hide() {
        View r0 = this.timePickerView.getFocusedChild();
        if (r0 == null) goto L5;
        ViewUtils.hideKeyboard(r0, false);
    L5:
        this.timePickerView.setVisibility(8);
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void initialize() {
        addTextWatchers();
        setTime(this.time);
        this.controller.bind();
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void invalidate() {
        setTime(this.time);
    }

    @Override // com.google.android.material.timepicker.TimePickerView.OnSelectionChange
    public void onSelectionChanged(int r5) {
        this.time.selection = r5;
        ChipTextInputComboView r0 = this.minuteTextInput;
        boolean r2 = false;
        if (r5 != 12) goto L5;
        boolean r1 = true;
    L6:
        r0.setChecked(r1);
        ChipTextInputComboView r02 = this.hourTextInput;
        if (r5 != 10) goto L9;
        r2 = true;
    L9:
        r02.setChecked(r2);
        updateSelection();
        return;
    L5:
        r1 = false;
        goto L6
    }

    public void resetChecked() {
        ChipTextInputComboView r0 = this.minuteTextInput;
        boolean r3 = false;
        if (this.time.selection != 12) goto L5;
        boolean r1 = true;
    L6:
        r0.setChecked(r1);
        ChipTextInputComboView r02 = this.hourTextInput;
        if (this.time.selection != 10) goto L9;
        r3 = true;
    L9:
        r02.setChecked(r3);
        return;
    L5:
        r1 = false;
        goto L6
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void show() {
        this.timePickerView.setVisibility(0);
        onSelectionChanged(this.time.selection);
    }

    public void vibrateAndMaybeBeep(View r2) {
        vibrate(r2);
        if (isTouchExplorationEnabled(r2.getContext()) == true) goto L6;
        beep(r2.getContext());
        return;
    }
}
