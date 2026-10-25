package com.google.android.material.timepicker;

import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.timepicker.ClockHandView;
import com.google.android.material.timepicker.TimePickerView;

/* loaded from: classes5.dex */
class TimePickerClockPresenter implements ClockHandView.OnRotateListener, TimePickerView.OnSelectionChange, TimePickerView.OnPeriodChangeListener, ClockHandView.OnActionUpListener, TimePickerPresenter {
    private static final int DEGREES_PER_HOUR = 30;
    private static final int DEGREES_PER_MINUTE = 6;
    private static final String[] HOUR_CLOCK_24_VALUES = null;
    private static final String[] HOUR_CLOCK_VALUES = null;
    private static final String[] MINUTE_CLOCK_VALUES = null;
    private boolean broadcasting;
    private float hourRotation;
    private float minuteRotation;
    private final TimeModel time;
    private final TimePickerView timePickerView;

    /* renamed from: com.google.android.material.timepicker.TimePickerClockPresenter$1, reason: invalid class name */
    public class AnonymousClass1 extends ClickActionDelegate {
        final /* synthetic */ TimePickerClockPresenter this$0;

        public AnonymousClass1(TimePickerClockPresenter r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // com.google.android.material.timepicker.ClickActionDelegate, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r2, AccessibilityNodeInfoCompat r3) {
            super.onInitializeAccessibilityNodeInfo(r2, r3);
            r3.setContentDescription(r2.getResources().getString(TimePickerClockPresenter.access$000(this.this$0).getHourContentDescriptionResId(), new Object[]{String.valueOf(TimePickerClockPresenter.access$000(this.this$0).getHourForDisplay())}));
        }
    }

    /* renamed from: com.google.android.material.timepicker.TimePickerClockPresenter$2, reason: invalid class name */
    public class AnonymousClass2 extends ClickActionDelegate {
        final /* synthetic */ TimePickerClockPresenter this$0;

        public AnonymousClass2(TimePickerClockPresenter r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // com.google.android.material.timepicker.ClickActionDelegate, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r2, AccessibilityNodeInfoCompat r3) {
            super.onInitializeAccessibilityNodeInfo(r2, r3);
            r3.setContentDescription(r2.getResources().getString(R.string.material_minute_suffix, new Object[]{String.valueOf(TimePickerClockPresenter.access$000(this.this$0).minute)}));
        }
    }

    static {
        HOUR_CLOCK_VALUES = new String[]{"12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        HOUR_CLOCK_24_VALUES = new String[]{"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        MINUTE_CLOCK_VALUES = new String[]{"00", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};
    }

    public TimePickerClockPresenter(TimePickerView r2, TimeModel r3) {
        this.broadcasting = false;
        this.timePickerView = r2;
        this.time = r3;
        initialize();
    }

    public static /* synthetic */ TimeModel access$000(TimePickerClockPresenter r0) {
        return r0.time;
    }

    private String[] getHourClockValues() {
        if (this.time.format != 1) goto L7;
        return HOUR_CLOCK_24_VALUES;
    L7:
        return HOUR_CLOCK_VALUES;
    }

    private int getHourRotation() {
        return (this.time.getHourForDisplay() * 30) % 360;
    }

    private void performHapticFeedback(int r3, int r4) {
        TimeModel r0 = this.time;
        if (r0.minute == r4) goto L5;
    L8:
        this.timePickerView.performHapticFeedback(4);
        return;
    L5:
        if (r0.hour != r3) goto L8;
    }

    private void updateCurrentLevel() {
        TimeModel r0 = this.time;
        int r3 = 1;
        if (r0.selection == 10) goto L5;
    L9:
        this.timePickerView.setCurrentLevel(r3);
        return;
    L5:
        if (r0.format != 1) goto L9;
        if (r0.hour < 12) goto L9;
        r3 = 2;
        goto L9
    }

    private void updateTime() {
        TimePickerView r0 = this.timePickerView;
        TimeModel r1 = this.time;
        r0.updateTime(r1.period, r1.getHourForDisplay(), this.time.minute);
    }

    private void updateValues() {
        updateValues(HOUR_CLOCK_VALUES, TimeModel.NUMBER_FORMAT);
        updateValues(HOUR_CLOCK_24_VALUES, TimeModel.NUMBER_FORMAT);
        updateValues(MINUTE_CLOCK_VALUES, TimeModel.ZERO_LEADING_NUMBER_FORMAT);
    }

    private void updateValues(String[] r4, String r5) {
        int r0 = 0;
    L4:
        if (r0 >= r4.length) goto L6;
        r4[r0] = TimeModel.formatText(this.timePickerView.getResources(), r4[r0], r5);
        r0 = r0 + 1;
        goto L4
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void hide() {
        this.timePickerView.setVisibility(8);
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void initialize() {
        if (this.time.format != 0) goto L5;
        this.timePickerView.showToggle();
    L5:
        this.timePickerView.addOnRotateListener(this);
        this.timePickerView.setOnSelectionChangeListener(this);
        this.timePickerView.setOnPeriodChangeListener(this);
        this.timePickerView.setOnActionUpListener(this);
        updateValues();
        invalidate();
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void invalidate() {
        this.hourRotation = getHourRotation();
        TimeModel r0 = this.time;
        this.minuteRotation = r0.minute * 6;
        setSelection(r0.selection, false);
        updateTime();
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnActionUpListener
    public void onActionUp(float r7, boolean r8) {
        this.broadcasting = true;
        TimeModel r1 = this.time;
        int r2 = r1.minute;
        int r3 = r1.hour;
        if (r1.selection != 10) goto L10;
        this.timePickerView.setHandRotation(this.hourRotation, false);
        AccessibilityManager r72 = (AccessibilityManager) ContextCompat.getSystemService(this.timePickerView.getContext(), AccessibilityManager.class);
        if (r72 != null) goto L7;
    L9:
        setSelection(12, true);
    L14:
        this.broadcasting = false;
        updateTime();
        performHapticFeedback(r3, r2);
        return;
    L7:
        if (r72.isTouchExplorationEnabled() == false) goto L9;
    L10:
        int r73 = Math.round(r7);
        if (r8 == true) goto L13;
        this.time.setMinute(((r73 + 15) / 30) * 5);
        this.minuteRotation = this.time.minute * 6;
    L13:
        this.timePickerView.setHandRotation(this.minuteRotation, r8);
        goto L14
    }

    @Override // com.google.android.material.timepicker.TimePickerView.OnPeriodChangeListener
    public void onPeriodChange(int r1) {
        this.time.setPeriod(r1);
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnRotateListener
    public void onRotate(float r5, boolean r6) {
        if (this.broadcasting == true) goto L17;
        if (r6 == true) goto L18;
        TimeModel r62 = this.time;
        int r0 = r62.hour;
        int r63 = r62.minute;
        int r52 = Math.round(r5);
        TimeModel r1 = this.time;
        if (r1.selection != 12) goto L9;
        r1.setMinute((r52 + 3) / 6);
        this.minuteRotation = (float) Math.floor(this.time.minute * 6);
    L15:
        updateTime();
        performHapticFeedback(r0, r63);
        return;
    L9:
        int r53 = (r52 + 15) / 30;
        if (r1.format != 1) goto L14;
        r53 = r53 % 12;
        if (this.timePickerView.getCurrentLevel() != 2) goto L14;
        r53 = r53 + 12;
    L14:
        this.time.setHour(r53);
        this.hourRotation = getHourRotation();
        goto L15
    L18:
        return;
    }

    @Override // com.google.android.material.timepicker.TimePickerView.OnSelectionChange
    public void onSelectionChanged(int r2) {
        setSelection(r2, true);
    }

    public void setSelection(int r5, boolean r6) {
        if (r5 != 12) goto L5;
        boolean r0 = true;
    L6:
        this.timePickerView.setAnimateOnTouchUp(r0);
        this.time.selection = r5;
        TimePickerView r1 = this.timePickerView;
        if (r0 == false) goto L9;
        String[] r2 = MINUTE_CLOCK_VALUES;
    L10:
        if (r0 == false) goto L12;
        int r3 = R.string.material_minute_suffix;
    L13:
        r1.setValues(r2, r3);
        updateCurrentLevel();
        TimePickerView r12 = this.timePickerView;
        if (r0 == false) goto L16;
        float r02 = this.minuteRotation;
    L17:
        r12.setHandRotation(r02, r6);
        this.timePickerView.setActiveSelection(r5);
        this.timePickerView.setMinuteHourDelegate(new AnonymousClass1(this, this.timePickerView.getContext(), R.string.material_hour_selection));
        this.timePickerView.setHourClickDelegate(new AnonymousClass2(this, this.timePickerView.getContext(), R.string.material_minute_selection));
        return;
    L16:
        r02 = this.hourRotation;
        goto L17
    L12:
        r3 = this.time.getHourContentDescriptionResId();
        goto L13
    L9:
        r2 = getHourClockValues();
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void show() {
        this.timePickerView.setVisibility(0);
    }
}
