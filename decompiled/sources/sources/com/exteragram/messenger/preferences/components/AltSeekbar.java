package com.exteragram.messenger.preferences.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.ui.MaterialSliderUiHelper;
import com.google.android.material.slider.Slider;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SeekBarView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class AltSeekbar extends FrameLayout {
    protected float currentValue;
    private final AnimatedTextView headerValue;
    protected final TextView leftTextView;
    private final int max;
    private final int min;
    private final OnDrag onDrag;
    protected final TextView rightTextView;
    private int roundedValue;
    public SeekBarView seekBarView;
    public Slider slider;
    private int vibro;

    /* renamed from: com.exteragram.messenger.preferences.components.AltSeekbar$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatedTextView {
        final Drawable backgroundDrawable;
        final /* synthetic */ AltSeekbar this$0;

        public AnonymousClass1(AltSeekbar r1, Context r2, boolean r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
            this.backgroundDrawable = Theme.createRoundRectDrawable(AndroidUtilities.dp(4.0f), Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader), 0.15f));
        }

        @Override // org.telegram.ui.Components.AnimatedTextView, android.view.View
        public void onDraw(Canvas r5) {
            this.backgroundDrawable.setBounds(0, 0, (int) ((getPaddingLeft() + getDrawable().getCurrentWidth()) + getPaddingRight()), getMeasuredHeight());
            this.backgroundDrawable.draw(r5);
            super.onDraw(r5);
        }
    }

    public interface OnDrag {
        void run(float r1);
    }

    /* renamed from: $r8$lambda$FReRN_ZEZiaAoianWFiV6EpT-iU, reason: not valid java name */
    public static /* synthetic */ void m1708$r8$lambda$FReRN_ZEZiaAoianWFiV6EpTiU(AltSeekbar r0, Slider r1, float r2, boolean r3) {
        r0.lambda$initSlider$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$j2X4XMsyry42i-ZoHV0eGGUDdS0, reason: not valid java name */
    public static /* synthetic */ void m1709$r8$lambda$j2X4XMsyry42iZoHV0eGGUDdS0(AltSeekbar r0, boolean r1, float r2) {
        r0.lambda$initSlider$1(r1, r2);
    }

    public AltSeekbar(Context r21, OnDrag r22, int r23, int r24, String r25, String r26, String r27) {
        super(r21);
        this.vibro = -1;
        this.onDrag = r22;
        this.max = r24;
        this.min = r23;
        LinearLayout r6 = new LinearLayout(r21);
        if (LocaleController.isRTL == false) goto L5;
        int r0 = 5;
    L6:
        r6.setGravity(r0);
        TextView r02 = new TextView(r21);
        r02.setTextSize(1, 15.0f);
        r02.setTypeface(AndroidUtilities.bold());
        int r10 = Theme.key_windowBackgroundWhiteBlueHeader;
        r02.setTextColor(Theme.getColor(r10));
        if (LocaleController.isRTL == false) goto L9;
        int r3 = 5;
    L10:
        r02.setGravity(r3);
        r02.setText(r25);
        r6.addView(r02, LayoutHelper.createLinear(-2, -2, 16));
        AnonymousClass1 r12 = new AnonymousClass1(this, r21, false, true, true);
        this.headerValue = r12;
        r12.setAnimationProperties(0.45f, 0, 240, CubicBezierInterpolator.EASE_OUT_QUINT);
        r12.setAllowCancel(true);
        r12.setTypeface(AndroidUtilities.bold());
        r12.setPadding(AndroidUtilities.dp(5.33f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(5.33f), AndroidUtilities.dp(2.0f));
        r12.setTextSize(AndroidUtilities.dp(12.0f));
        r12.setTextColor(Theme.getColor(r10));
        r6.addView(r12, LayoutHelper.createLinear(-2, 17, 16, 6, 1, 0, 0));
        addView(r6, LayoutHelper.createFrame(-1, -2.0f, 55, 21.0f, 17.0f, 21.0f, 0.0f));
        FrameLayout r03 = new FrameLayout(r21);
        TextView r32 = new TextView(r21);
        this.leftTextView = r32;
        r32.setTextSize(1, 13.0f);
        int r5 = Theme.key_windowBackgroundWhiteGrayText;
        r32.setTextColor(Theme.getColor(r5));
        r32.setGravity(3);
        r32.setText(r26);
        r03.addView(r32, LayoutHelper.createFrame(-2, -2, 19));
        TextView r33 = new TextView(r21);
        this.rightTextView = r33;
        r33.setTextSize(1, 13.0f);
        r33.setTextColor(Theme.getColor(r5));
        r33.setGravity(5);
        r33.setText(r27);
        r03.addView(r33, LayoutHelper.createFrame(-2, -2, 21));
        addView(r03, LayoutHelper.createFrame(-1, -2.0f, 55, 21.0f, 52.0f, 21.0f, 0.0f));
        initSlider();
        return;
    L9:
        r3 = 3;
        goto L10
    L5:
        r0 = 3;
        goto L6
    }

    private void checkEndpointHaptic(float r4) {
        if (useExactEndpointHaptic() == false) goto L10;
        int r0 = this.min;
        if (r4 <= r0) goto L16;
        r0 = this.max;
        if (r4 >= r0) goto L16;
    L15:
        r0 = -1;
    L16:
        if (r0 != (-1)) goto L18;
        this.vibro = -1;
        return;
    L18:
        if (r0 == this.vibro) goto L23;
        this.vibro = r0;
        performHapticFeedback(4, 2);
        return;
    L23:
        return;
    L10:
        r0 = this.roundedValue;
        if (r0 == this.min) goto L16;
        if (r0 != this.max) goto L15;
        goto L15
    }

    private float clamp(float r2) {
        return MathUtils.clamp(r2, this.min, this.max);
    }

    private void initSlider() {
        if (ExteraConfig.getNewSliderStyle() == false) goto L5;
        Slider r0 = MaterialSliderUiHelper.create(getContext());
        this.slider = r0;
        MaterialSliderUiHelper.applyContinuousStyle(r0);
        this.slider.addOnChangeListener(new AltSeekbar$$ExternalSyntheticLambda0(this));
        MaterialSliderUiHelper.applyColors(this.slider, Theme.getColor(Theme.key_player_progress), Theme.getColor(Theme.key_player_progressBackground));
        this.slider.setValueFrom(this.min);
        this.slider.setValueTo(this.max);
        MaterialSliderUiHelper.setValue(this.slider, this.currentValue);
        addView(this.slider, LayoutHelper.createFrame(-1, 56.0f, 48, 7.0f, 68.0f, 7.0f, 6.0f));
    L6:
        setProgress(this.currentValue);
        return;
    L5:
        SeekBarView r02 = new SeekBarView(getContext(), true, null);
        this.seekBarView = r02;
        r02.setReportChanges(true);
        this.seekBarView.setDelegate(new AltSeekbar$$ExternalSyntheticLambda1(this));
        addView(this.seekBarView, LayoutHelper.createFrame(-1, 44.0f, 48, 6.0f, 68.0f, 6.0f, 0.0f));
        goto L6
    }

    private /* synthetic */ void lambda$initSlider$0(Slider r1, float r2, boolean r3) {
        if (r3 == false) goto L5;
        this.onDrag.run(r2);
    L5:
        if (Math.round(r2) == this.roundedValue) goto L8;
        setProgress(r2);
        return;
    }

    private /* synthetic */ void lambda$initSlider$1(boolean r3, float r4) {
        float r0 = this.min + ((this.max - r3) * r4);
        this.onDrag.run(r0);
        if (Math.round(r0) == this.roundedValue) goto L6;
        setProgress(r0);
        return;
    }

    private void updateValues() {
        int r0 = this.max;
        int r1 = this.min;
        float r2 = this.currentValue;
        float r3 = (((r0 - r1) / 2) + r1) * 1.5f;
        if (r2 < (r3 - (r1 * 0.5f))) goto L6;
        TextView r02 = this.rightTextView;
        int r12 = Theme.key_windowBackgroundWhiteGrayText;
        int r22 = Theme.getColor(r12);
        int r4 = Theme.getColor(Theme.key_windowBackgroundWhiteBlueText);
        float r6 = this.currentValue;
        int r7 = this.min;
        r02.setTextColor(ColorUtils.blendARGB(r22, r4, (r6 - (r3 - (r7 * 0.5f))) / (this.max - (r3 - (r7 * 0.5f)))));
        this.leftTextView.setTextColor(Theme.getColor(r12));
        return;
    L6:
        float r13 = (r1 + r0) * 0.5f;
        TextView r23 = this.leftTextView;
        if (r2 > r13) goto L10;
        int r14 = Theme.key_windowBackgroundWhiteGrayText;
        r23.setTextColor(ColorUtils.blendARGB(Theme.getColor(r14), Theme.getColor(Theme.key_windowBackgroundWhiteBlueText), (this.currentValue - ((r0 + r7) * 0.5f)) / (this.min - ((r0 + r7) * 0.5f))));
        this.rightTextView.setTextColor(Theme.getColor(r14));
        return;
    L10:
        int r03 = Theme.key_windowBackgroundWhiteGrayText;
        r23.setTextColor(Theme.getColor(r03));
        this.rightTextView.setTextColor(Theme.getColor(r03));
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof AltSeekbar) == false) goto L28;
        AltSeekbar r52 = (AltSeekbar) r5;
        if (Objects.equals(this.headerValue, r52.headerValue) == false) goto L28;
        if (Objects.equals(this.leftTextView, r52.leftTextView) == false) goto L28;
        if (Objects.equals(this.rightTextView, r52.rightTextView) == false) goto L28;
        if (Objects.equals(this.seekBarView, r52.seekBarView) == false) goto L28;
        if (Objects.equals(this.slider, r52.slider) == false) goto L28;
        if (this.min != r52.min) goto L28;
        if (this.max != r52.max) goto L28;
        if (Float.compare(this.currentValue, r52.currentValue) != 0) goto L28;
        if (this.roundedValue != r52.roundedValue) goto L28;
        if (this.vibro != r52.vibro) goto L28;
        return true;
    L28:
        return false;
    }

    public CharSequence getTextForHeader() {
        int r0 = this.roundedValue;
        if (r0 != this.min) goto L6;
        CharSequence r2 = this.leftTextView.getText();
    L10:
        return r2.toString().toUpperCase();
    L6:
        if (r0 != this.max) goto L8;
        r2 = this.rightTextView.getText();
        goto L10
    L8:
        r2 = String.valueOf(r0);
        goto L10
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(112.0f), TLObject.FLAG_30));
    }

    public void setProgress(float r4) {
        float r42 = clamp(r4);
        this.currentValue = r42;
        this.roundedValue = Math.round(r42);
        Slider r43 = this.slider;
        if (r43 == null) goto L5;
        MaterialSliderUiHelper.setValue(r43, this.currentValue);
    L8:
        this.headerValue.cancelAnimation();
        this.headerValue.setText(getTextForHeader(), true);
        checkEndpointHaptic(this.currentValue);
        updateValues();
        return;
    L5:
        SeekBarView r44 = this.seekBarView;
        if (r44 == null) goto L8;
        r44.setProgress((this.currentValue - this.min) / (this.max - r1));
        goto L8
    }

    public void updateHeader(float r3) {
        float r32 = clamp(r3);
        this.currentValue = r32;
        this.roundedValue = Math.round(r32);
        CharSequence r33 = getTextForHeader();
        if (TextUtils.equals(this.headerValue.getText(), r33) == true) goto L5;
        this.headerValue.setText(r33, true);
    L5:
        checkEndpointHaptic(this.currentValue);
        updateValues();
    }

    public void updateStyle() {
        Slider r0 = this.slider;
        if (r0 == null) goto L5;
        removeView(r0);
        this.slider = null;
    L5:
        SeekBarView r02 = this.seekBarView;
        if (r02 == null) goto L8;
        removeView(r02);
        this.seekBarView = null;
    L8:
        initSlider();
    }

    public boolean useExactEndpointHaptic() {
        return false;
    }
}
