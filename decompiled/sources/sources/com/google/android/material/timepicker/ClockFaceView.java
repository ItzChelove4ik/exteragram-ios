package com.google.android.material.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Arrays;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
class ClockFaceView extends RadialViewGroup implements ClockHandView.OnRotateListener {
    private static final float EPSILON = 0.001f;
    private static final int INITIAL_CAPACITY = 12;
    private static final String VALUE_PLACEHOLDER = "";
    private final int clockHandPadding;
    private final ClockHandView clockHandView;
    private final int clockSize;
    private float currentHandRotation;
    private final int[] gradientColors;
    private final float[] gradientPositions;
    private final int minimumHeight;
    private final int minimumWidth;
    private OnEnterKeyPressedListener onEnterKeyPressedListener;
    private final RectF scratch;
    private final Rect scratchLineBounds;
    private final ColorStateList textColor;
    private final SparseArray<TextView> textViewPool;
    private final Rect textViewRect;
    private final AccessibilityDelegateCompat valueAccessibilityDelegate;
    private String[] values;

    /* renamed from: com.google.android.material.timepicker.ClockFaceView$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewOutlineProvider {
        final /* synthetic */ ClockFaceView this$0;

        public AnonymousClass1(ClockFaceView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r2, Outline r3) {
            r3.setOval(0, 0, r2.getWidth(), r2.getHeight());
        }
    }

    /* renamed from: com.google.android.material.timepicker.ClockFaceView$2, reason: invalid class name */
    public class AnonymousClass2 extends AccessibilityDelegateCompat {
        final /* synthetic */ ClockFaceView this$0;

        public AnonymousClass2(ClockFaceView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r8, AccessibilityNodeInfoCompat r9) {
            super.onInitializeAccessibilityNodeInfo(r8, r9);
            int r3 = ((Integer) r8.getTag(R.id.material_value_index)).intValue();
            if (r3 <= 0) goto L5;
            r9.setTraversalAfter((View) ClockFaceView.access$000(this.this$0).get(r3 - 1));
        L5:
            r9.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, r3, 1, false, r8.isSelected()));
            r9.setClickable(true);
            r9.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View r10, int r11, Bundle r12) {
            if (r11 != 16) goto L7;
            long r1 = SystemClock.uptimeMillis();
            r10.getHitRect(ClockFaceView.access$100(this.this$0));
            float r6 = ClockFaceView.access$100(this.this$0).centerX();
            float r7 = ClockFaceView.access$100(this.this$0).centerY();
            ClockFaceView.access$200(this.this$0).onTouchEvent(MotionEvent.obtain(r1, r1, 0, r6, r7, 0));
            ClockFaceView.access$200(this.this$0).onTouchEvent(MotionEvent.obtain(r1, r1, 1, r6, r7, 0));
            return true;
        L7:
            return super.performAccessibilityAction(r10, r11, r12);
        }
    }

    public interface OnEnterKeyPressedListener {
        void onEnterKeyPressed();
    }

    public ClockFaceView(Context r2) {
        this(r2, null);
    }

    public ClockFaceView(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.materialClockStyle);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public ClockFaceView(Context r6, AttributeSet r7, int r8) {
        super(r6, r7, r8);
        this.textViewRect = new Rect();
        this.scratch = new RectF();
        this.scratchLineBounds = new Rect();
        this.textViewPool = new SparseArray();
        this.gradientPositions = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray r72 = r6.obtainStyledAttributes(r7, R.styleable.ClockFaceView, r8, R.style.Widget_MaterialComponents_TimePicker_Clock);
        Resources r82 = getResources();
        ColorStateList r0 = MaterialResources.getColorStateList(r6, r72, R.styleable.ClockFaceView_clockNumberTextColor);
        this.textColor = r0;
        LayoutInflater.from(r6).inflate(R.layout.material_clockface_view, this, true);
        ClockHandView r1 = (ClockHandView) findViewById(R.id.material_clock_hand);
        this.clockHandView = r1;
        this.clockHandPadding = r82.getDimensionPixelSize(R.dimen.material_clock_hand_padding);
        int r2 = r0.getColorForState(new int[]{android.R.attr.state_selected}, r0.getDefaultColor());
        this.gradientColors = new int[]{r2, r2, r0.getDefaultColor()};
        r1.addOnRotateListener(this);
        int r02 = AppCompatResources.getColorStateList(r6, R.color.material_timepicker_clockface).getDefaultColor();
        ColorStateList r62 = MaterialResources.getColorStateList(r6, r72, R.styleable.ClockFaceView_clockFaceBackgroundColor);
        if (r62 == null) goto L6;
        r02 = r62.getDefaultColor();
    L6:
        setBackgroundColor(r02);
        r72.recycle();
        setOutlineProvider(new AnonymousClass1(this));
        setFocusable(true);
        setClipToOutline(true);
        this.valueAccessibilityDelegate = new AnonymousClass2(this);
        String[] r63 = new String[12];
        Arrays.fill(r63, "");
        setValues(r63, 0);
        this.minimumHeight = r82.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_height);
        this.minimumWidth = r82.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_width);
        this.clockSize = r82.getDimensionPixelSize(R.dimen.material_clock_size);
    }

    public static /* synthetic */ SparseArray access$000(ClockFaceView r0) {
        return r0.textViewPool;
    }

    public static /* synthetic */ Rect access$100(ClockFaceView r0) {
        return r0.textViewRect;
    }

    public static /* synthetic */ ClockHandView access$200(ClockFaceView r0) {
        return r0.clockHandView;
    }

    private void findIntersectingTextView() {
        RectF r0 = this.clockHandView.getCurrentSelectorBox();
        TextView r1 = getSelectedTextView(r0);
        int r3 = 0;
    L4:
        if (r3 >= this.textViewPool.size()) goto L13;
        TextView r4 = this.textViewPool.get(r3);
        if (r4 == null) goto L12;
        if (r4 != r1) goto L10;
        boolean r5 = true;
    L11:
        r4.setSelected(r5);
        r4.getPaint().setShader(getGradientForTextView(r0, r4));
        r4.invalidate();
        goto L12
    L10:
        r5 = false;
    L12:
        r3 = r3 + 1;
        goto L4
    }

    private RadialGradient getGradientForTextView(RectF r8, TextView r9) {
        r9.getHitRect(this.textViewRect);
        this.scratch.set(this.textViewRect);
        r9.getLineBounds(0, this.scratchLineBounds);
        RectF r92 = this.scratch;
        Rect r0 = this.scratchLineBounds;
        r92.inset(r0.left, r0.top);
        if (RectF.intersects(r8, this.scratch) == true) goto L7;
        return null;
    L7:
        return new RadialGradient(r8.centerX() - this.scratch.left, r8.centerY() - this.scratch.top, r8.width() * 0.5f, this.gradientColors, this.gradientPositions, Shader.TileMode.CLAMP);
    }

    private int getSelectedIndex() {
        int r0 = 0;
    L4:
        if (r0 >= this.textViewPool.size()) goto L10;
        TextView r1 = this.textViewPool.valueAt(r0);
        if (r1.isSelected() == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return ((Integer) r1.getTag(R.id.material_value_index)).intValue();
    L10:
        return -1;
    }

    private TextView getSelectedTextView(RectF r7) {
        float r0 = Float.MAX_VALUE;
        TextView r1 = null;
        int r2 = 0;
    L4:
        if (r2 >= this.textViewPool.size()) goto L12;
        TextView r3 = this.textViewPool.get(r2);
        if (r3 == null) goto L11;
        r3.getHitRect(this.textViewRect);
        this.scratch.set(this.textViewRect);
        this.scratch.union(r7);
        float r4 = this.scratch.width() * this.scratch.height();
        if (r4 >= r0) goto L11;
        r1 = r3;
        r0 = r4;
    L11:
        r2 = r2 + 1;
        goto L4
    L12:
        return r1;
    }

    private static float max3(float r0, float r1, float r2) {
        return Math.max(Math.max(r0, r1), r2);
    }

    private void updateTextViews(int r11) {
        LayoutInflater r0 = LayoutInflater.from(getContext());
        int r1 = this.textViewPool.size();
        int r3 = 0;
        boolean r4 = false;
    L4:
        if (r3 >= Math.max(this.values.length, r1)) goto L17;
        TextView r5 = this.textViewPool.get(r3);
        if (r3 < this.values.length) goto L8;
        removeView(r5);
        this.textViewPool.remove(r3);
    L16:
        r3 = r3 + 1;
        goto L4
    L8:
        if (r5 != null) goto L10;
        r5 = (TextView) r0.inflate(R.layout.material_clockface_textview, this, false);
        this.textViewPool.put(r3, r5);
        addView(r5);
    L10:
        r5.setText(this.values[r3]);
        r5.setTag(R.id.material_value_index, Integer.valueOf(r3));
        int r6 = (r3 / 12) + 1;
        r5.setTag(R.id.material_clock_level, Integer.valueOf(r6));
        if (r6 <= 1) goto L13;
        r4 = true;
    L13:
        ViewCompat.setAccessibilityDelegate(r5, this.valueAccessibilityDelegate);
        r5.setTextColor(this.textColor);
        if (r11 == 0) goto L16;
        r5.setContentDescription(getResources().getString(r11, new Object[]{this.values[r3]}));
        goto L16
    L17:
        this.clockHandView.setMultiLevel(r4);
    }

    public int getCurrentLevel() {
        return this.clockHandView.getCurrentLevel();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        AccessibilityNodeInfoCompat.wrap(r3).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, this.values.length, false, 1));
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r6, KeyEvent r7) {
        int r0 = getSelectedIndex();
        if (isShown() == false) goto L27;
        if (r0 == (-1)) goto L27;
        if (r6 == 66) goto L22;
        switch(r6) {
            case 19: goto L13;
            case 20: goto L12;
            case 21: goto L12;
            case 22: goto L13;
            case 23: goto L22;
            default: goto L11;
        };
    L12:
        String[] r3 = this.values;
        int r1 = ((r0 - 1) + r3.length) % r3.length;
    L14:
        if (r1 == r0) goto L21;
        int r62 = (r1 / 12) + 1;
        if (r62 == getCurrentLevel()) goto L18;
        setCurrentLevel(r62);
    L18:
        setHandRotation((r1 % 12) * 30.0f);
        return true;
    L21:
        return super.onKeyDown(r6, r7);
    L13:
        r1 = (r0 + 1) % this.values.length;
        goto L14
    L11:
        return super.onKeyDown(r6, r7);
    L22:
        OnEnterKeyPressedListener r5 = this.onEnterKeyPressedListener;
        if (r5 == null) goto L25;
        r5.onEnterKeyPressed();
    L25:
        return true;
    L27:
        return super.onKeyDown(r6, r7);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        findIntersectingTextView();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        DisplayMetrics r0 = getResources().getDisplayMetrics();
        int r02 = (int) (this.clockSize / max3(this.minimumHeight / r0.heightPixels, this.minimumWidth / r0.widthPixels, 1.0f));
        if (View.MeasureSpec.getMode(r5) == 0) goto L6;
        r02 = Math.min(r02, View.MeasureSpec.getSize(r5));
    L6:
        if (View.MeasureSpec.getMode(r6) == 0) goto L8;
        r02 = Math.min(r02, View.MeasureSpec.getSize(r6));
    L8:
        int r52 = View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30);
        int r03 = ((r02 / 2) - this.clockHandView.getSelectorRadius()) - this.clockHandPadding;
        if (r03 == getRadius()) goto L11;
        setRadius(r03);
    L11:
        super.onMeasure(r52, r52);
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnRotateListener
    public void onRotate(float r2, boolean r3) {
        if (Math.abs(this.currentHandRotation - r2) <= EPSILON) goto L6;
        this.currentHandRotation = r2;
        findIntersectingTextView();
        return;
    }

    public void setCurrentLevel(int r1) {
        this.clockHandView.setCurrentLevel(r1);
    }

    public void setHandRotation(float r2) {
        this.clockHandView.setHandRotation(r2);
        findIntersectingTextView();
    }

    public void setOnEnterKeyPressedListener(OnEnterKeyPressedListener r1) {
        this.onEnterKeyPressedListener = r1;
    }

    @Override // com.google.android.material.timepicker.RadialViewGroup
    public void setRadius(int r2) {
        if (r2 == getRadius()) goto L6;
        super.setRadius(r2);
        this.clockHandView.setCircleRadius(getRadius());
        return;
    }

    public void setValues(String[] r1, int r2) {
        this.values = r1;
        updateTextViews(r2);
    }

    @Override // com.google.android.material.timepicker.RadialViewGroup
    public void updateLayoutParams() {
        super.updateLayoutParams();
        int r1 = 0;
    L4:
        if (r1 >= this.textViewPool.size()) goto L6;
        this.textViewPool.get(r1).setVisibility(0);
        r1 = r1 + 1;
        goto L4
    }
}
