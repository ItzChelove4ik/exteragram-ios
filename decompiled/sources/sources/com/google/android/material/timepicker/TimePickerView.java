package com.google.android.material.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Locale;

/* loaded from: classes5.dex */
class TimePickerView extends ConstraintLayout implements TimePickerControls {
    static final String GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME = "android.view.View";
    private final ClockFaceView clockFace;
    private final ClockHandView clockHandView;
    private final Chip hourView;
    private final Chip minuteView;
    private OnDoubleTapListener onDoubleTapListener;
    private OnPeriodChangeListener onPeriodChangeListener;
    private OnSelectionChange onSelectionChangeListener;
    private final View.OnClickListener selectionListener;
    private final MaterialButtonToggleGroup toggle;

    /* renamed from: com.google.android.material.timepicker.TimePickerView$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ TimePickerView this$0;

        public AnonymousClass1(TimePickerView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r2) {
            if (TimePickerView.access$000(this.this$0) == null) goto L6;
            TimePickerView.access$000(this.this$0).onSelectionChanged(((Integer) r2.getTag(R.id.selection_type)).intValue());
            return;
        }
    }

    /* renamed from: com.google.android.material.timepicker.TimePickerView$2, reason: invalid class name */
    public class AnonymousClass2 extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ TimePickerView this$0;

        public AnonymousClass2(TimePickerView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent r1) {
            OnDoubleTapListener r0 = TimePickerView.access$100(this.this$0);
            if (r0 == null) goto L6;
            r0.onDoubleTap();
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: com.google.android.material.timepicker.TimePickerView$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnTouchListener {
        final /* synthetic */ TimePickerView this$0;
        final /* synthetic */ GestureDetector val$gestureDetector;

        public AnonymousClass3(TimePickerView r1, GestureDetector r2) {
            this.this$0 = r1;
            this.val$gestureDetector = r2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View r1, MotionEvent r2) {
            if (((Checkable) r1).isChecked() == true) goto L5;
            return false;
        L5:
            return this.val$gestureDetector.onTouchEvent(r2);
        }
    }

    public interface OnDoubleTapListener {
        void onDoubleTap();
    }

    public interface OnPeriodChangeListener {
        void onPeriodChange(int r1);
    }

    public interface OnSelectionChange {
        void onSelectionChanged(int r1);
    }

    public static /* synthetic */ void $r8$lambda$N5NRqpZD74A3LGLNZxV7Y_usDfU(TimePickerView r0, MaterialButtonToggleGroup r1, int r2, boolean r3) {
        if (r3 == true) goto L5;
        r0.getClass();
        return;
    L5:
        OnPeriodChangeListener r02 = r0.onPeriodChangeListener;
        if (r02 != null) goto L8;
        return;
    L8:
        if (r2 != R.id.material_clock_period_pm_button) goto L10;
        int r12 = 1;
    L11:
        r02.onPeriodChange(r12);
        return;
    L10:
        r12 = 0;
        goto L11
    }

    /* renamed from: $r8$lambda$aD1oT4on2FtZictvw3Rw--jUfCE, reason: not valid java name */
    public static /* synthetic */ void m1912$r8$lambda$aD1oT4on2FtZictvw3RwjUfCE(TimePickerView r1) {
        if (r1.hourView.isChecked() == false) goto L8;
        OnSelectionChange r12 = r1.onSelectionChangeListener;
        if (r12 == null) goto L9;
        r12.onSelectionChanged(12);
        return;
    L9:
        return;
    }

    public TimePickerView(Context r2) {
        this(r2, null);
    }

    public TimePickerView(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public TimePickerView(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
        this.selectionListener = new AnonymousClass1(this);
        LayoutInflater.from(r1).inflate(R.layout.material_timepicker, this);
        ClockFaceView r12 = (ClockFaceView) findViewById(R.id.material_clock_face);
        this.clockFace = r12;
        MaterialButtonToggleGroup r22 = (MaterialButtonToggleGroup) findViewById(R.id.material_clock_period_toggle);
        this.toggle = r22;
        r22.addOnButtonCheckedListener(new TimePickerView$$ExternalSyntheticLambda0(this));
        this.minuteView = (Chip) findViewById(R.id.material_minute_tv);
        this.hourView = (Chip) findViewById(R.id.material_hour_tv);
        this.clockHandView = (ClockHandView) findViewById(R.id.material_clock_hand);
        r12.setOnEnterKeyPressedListener(new TimePickerView$$ExternalSyntheticLambda1(this));
        setupDoubleTap();
        setUpDisplay();
    }

    public static /* synthetic */ OnSelectionChange access$000(TimePickerView r0) {
        return r0.onSelectionChangeListener;
    }

    public static /* synthetic */ OnDoubleTapListener access$100(TimePickerView r0) {
        return r0.onDoubleTapListener;
    }

    private void setUpDisplay() {
        this.minuteView.setTag(R.id.selection_type, 12);
        this.hourView.setTag(R.id.selection_type, 10);
        this.minuteView.setOnClickListener(this.selectionListener);
        this.hourView.setOnClickListener(this.selectionListener);
        this.minuteView.setAccessibilityClassName(GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME);
        this.hourView.setAccessibilityClassName(GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void setupDoubleTap() {
        AnonymousClass3 r1 = new AnonymousClass3(this, new GestureDetector(getContext(), new AnonymousClass2(this)));
        this.minuteView.setOnTouchListener(r1);
        this.hourView.setOnTouchListener(r1);
    }

    private void updateSelection(Chip r1, boolean r2) {
        r1.setChecked(r2);
        if (r2 == false) goto L5;
        int r0 = 2;
    L6:
        r1.setAccessibilityLiveRegion(r0);
        return;
    L5:
        r0 = 0;
        goto L6
    }

    public void addOnRotateListener(ClockHandView.OnRotateListener r1) {
        this.clockHandView.addOnRotateListener(r1);
    }

    public int getCurrentLevel() {
        return this.clockFace.getCurrentLevel();
    }

    @Override // android.view.View
    public void onVisibilityChanged(View r1, int r2) {
        super.onVisibilityChanged(r1, r2);
        if (r1 != this) goto L7;
        if (r2 != 0) goto L8;
        this.hourView.sendAccessibilityEvent(8);
        return;
    L8:
        return;
    }

    @Override // com.google.android.material.timepicker.TimePickerControls
    public void setActiveSelection(int r5) {
        Chip r0 = this.minuteView;
        boolean r2 = false;
        if (r5 != 12) goto L5;
        boolean r1 = true;
    L6:
        updateSelection(r0, r1);
        Chip r02 = this.hourView;
        if (r5 != 10) goto L9;
        r2 = true;
    L9:
        updateSelection(r02, r2);
        return;
    L5:
        r1 = false;
        goto L6
    }

    public void setAnimateOnTouchUp(boolean r1) {
        this.clockHandView.setAnimateOnTouchUp(r1);
    }

    public void setCurrentLevel(int r1) {
        this.clockFace.setCurrentLevel(r1);
    }

    @Override // com.google.android.material.timepicker.TimePickerControls
    public void setHandRotation(float r1) {
        this.clockHandView.setHandRotation(r1);
    }

    public void setHandRotation(float r1, boolean r2) {
        this.clockHandView.setHandRotation(r1, r2);
    }

    public void setHourClickDelegate(AccessibilityDelegateCompat r1) {
        ViewCompat.setAccessibilityDelegate(this.minuteView, r1);
    }

    public void setMinuteHourDelegate(AccessibilityDelegateCompat r1) {
        ViewCompat.setAccessibilityDelegate(this.hourView, r1);
    }

    public void setOnActionUpListener(ClockHandView.OnActionUpListener r1) {
        this.clockHandView.setOnActionUpListener(r1);
    }

    public void setOnDoubleTapListener(OnDoubleTapListener r1) {
        this.onDoubleTapListener = r1;
    }

    public void setOnPeriodChangeListener(OnPeriodChangeListener r1) {
        this.onPeriodChangeListener = r1;
    }

    public void setOnSelectionChangeListener(OnSelectionChange r1) {
        this.onSelectionChangeListener = r1;
    }

    @Override // com.google.android.material.timepicker.TimePickerControls
    public void setValues(String[] r1, int r2) {
        this.clockFace.setValues(r1, r2);
    }

    public void showToggle() {
        this.toggle.setVisibility(0);
    }

    @Override // com.google.android.material.timepicker.TimePickerControls
    @SuppressLint({"DefaultLocale"})
    public void updateTime(int r2, int r3, int r4) {
        if (r2 != 1) goto L5;
        int r22 = R.id.material_clock_period_pm_button;
    L6:
        this.toggle.check(r22);
        Locale r23 = getResources().getConfiguration().locale;
        String r42 = String.format(r23, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r4)});
        String r24 = String.format(r23, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r3)});
        if (TextUtils.equals(this.minuteView.getText(), r42) == true) goto L10;
        this.minuteView.setText(r42);
    L10:
        if (TextUtils.equals(this.hourView.getText(), r24) == true) goto L13;
        this.hourView.setText(r24);
        return;
    L13:
        return;
    L5:
        r22 = R.id.material_clock_period_am_button;
        goto L6
    }
}
