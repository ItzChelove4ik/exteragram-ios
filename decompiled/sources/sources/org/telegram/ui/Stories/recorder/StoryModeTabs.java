package org.telegram.ui.Stories.recorder;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Stories.recorder.FlashViews;

/* loaded from: classes7.dex */
public abstract class StoryModeTabs extends FrameLayout implements FlashViews.Invertable {
    private ValueAnimator animator;
    private float invert;
    private final LinearLayout layout;
    private final TextView live;
    private final FrameLayout liveLayout;
    private float mode;
    private Utilities.Callback<Integer> onSwitchModeListener;
    private Utilities.Callback<Float> onSwitchingModeListener;
    private final TextView photo;
    private final FrameLayout photoLayout;
    private int toMode;
    private final TextView video;
    private final FrameLayout videoLayout;

    /* renamed from: org.telegram.ui.Stories.recorder.StoryModeTabs$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayout {

        /* renamed from: a, reason: collision with root package name */
        private final RectF f520a;

        /* renamed from: b, reason: collision with root package name */
        private final RectF f521b;
        private final Paint backgroundPaint;

        /* renamed from: c, reason: collision with root package name */
        private final RectF f522c;
        final /* synthetic */ StoryModeTabs this$0;

        public AnonymousClass1(StoryModeTabs r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.f520a = new RectF();
            this.f521b = new RectF();
            this.f522c = new RectF();
            this.backgroundPaint = new Paint(1);
        }

        private void setRect(int r3, RectF r4) {
            StoryModeTabs r2 = this.this$0;
            if (r3 > (-1)) goto L6;
            FrameLayout r22 = StoryModeTabs.m20239$$Nest$fgetliveLayout(r2);
        L9:
            r4.set(r22.getLeft(), r22.getBottom() - AndroidUtilities.dp(30.0f), r22.getRight(), r22.getBottom());
            return;
        L6:
            if (r3 < 1) goto L8;
            r22 = StoryModeTabs.m20242$$Nest$fgetvideoLayout(r2);
            goto L9
        L8:
            r22 = StoryModeTabs.m20241$$Nest$fgetphotoLayout(r2);
            goto L9
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            setRect((int) Math.floor(StoryModeTabs.m20240$$Nest$fgetmode(this.this$0)), this.f520a);
            setRect((int) Math.ceil(StoryModeTabs.m20240$$Nest$fgetmode(this.this$0)), this.f521b);
            AndroidUtilities.lerp(this.f520a, this.f521b, StoryModeTabs.m20240$$Nest$fgetmode(this.this$0) - ((float) Math.floor(StoryModeTabs.m20240$$Nest$fgetmode(this.this$0))), this.f522c);
            this.backgroundPaint.setColor(Theme.multAlpha(ColorUtils.blendARGB(-1, -16777216, StoryModeTabs.m20238$$Nest$fgetinvert(this.this$0)), 0.15f));
            RectF r0 = this.f522c;
            r6.drawRoundRect(r0, r0.height() / 2.0f, this.f522c.height() / 2.0f, this.backgroundPaint);
            super.dispatchDraw(r6);
        }
    }

    public static /* synthetic */ void $r8$lambda$7D5QLkr54Z1n9DNRAVJUknL4OTQ(StoryModeTabs r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$TrifBszH4TOVRsZjhCf8lJrcTT0(StoryModeTabs r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$wG7ZXxKSMQWAC8NjMJWWw_vpzSk(StoryModeTabs r0, ValueAnimator r1) {
        r0.lambda$switchMode$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$xOE3PVcqHzmGfDhSZq9PmgM1E8Y(StoryModeTabs r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetinvert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20238$$Nest$fgetinvert(StoryModeTabs r0) {
        return r0.invert;
    }

    /* renamed from: -$$Nest$fgetliveLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20239$$Nest$fgetliveLayout(StoryModeTabs r0) {
        return r0.liveLayout;
    }

    /* renamed from: -$$Nest$fgetmode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20240$$Nest$fgetmode(StoryModeTabs r0) {
        return r0.mode;
    }

    /* renamed from: -$$Nest$fgetphotoLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20241$$Nest$fgetphotoLayout(StoryModeTabs r0) {
        return r0.photoLayout;
    }

    /* renamed from: -$$Nest$fgetvideoLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20242$$Nest$fgetvideoLayout(StoryModeTabs r0) {
        return r0.videoLayout;
    }

    public StoryModeTabs(Context r15) {
        super(r15);
        AnonymousClass1 r0 = new AnonymousClass1(this, r15);
        this.layout = r0;
        r0.setOrientation(0);
        FrameLayout r1 = new FrameLayout(r15);
        this.liveLayout = r1;
        TextView r2 = new TextView(r15);
        this.live = r2;
        r2.setTextSize(1, 14.0f);
        r2.setTypeface(AndroidUtilities.bold());
        r2.setTextColor(-1);
        r2.setText(LocaleController.getString(R.string.StoryLive));
        r1.addView(r2, LayoutHelper.createFrame(-2, -2.0f, 80, 16.0f, 0.0f, 16.0f, 7.0f));
        r0.addView(r1, LayoutHelper.createLinear(-2, -1, 112, 0.0f, 0.0f, 6.66f, 0.0f));
        r1.setOnClickListener(new StoryModeTabs$$ExternalSyntheticLambda1(this));
        ScaleStateListAnimator.apply(r1);
        FrameLayout r12 = new FrameLayout(r15);
        this.photoLayout = r12;
        TextView r22 = new TextView(r15);
        this.photo = r22;
        r22.setTextSize(1, 14.0f);
        r22.setTypeface(AndroidUtilities.bold());
        r22.setTextColor(-1);
        r22.setText(LocaleController.getString(R.string.StoryPhoto));
        r12.addView(r22, LayoutHelper.createFrame(-2, -2.0f, 80, 16.0f, 0.0f, 16.0f, 7.0f));
        r0.addView(r12, LayoutHelper.createLinear(-2, -1, 112, 0.0f, 0.0f, 6.66f, 0.0f));
        r12.setOnClickListener(new StoryModeTabs$$ExternalSyntheticLambda2(this));
        ScaleStateListAnimator.apply(r12);
        FrameLayout r13 = new FrameLayout(r15);
        this.videoLayout = r13;
        TextView r23 = new TextView(r15);
        this.video = r23;
        r23.setTextSize(1, 14.0f);
        r23.setTypeface(AndroidUtilities.bold());
        r23.setTextColor(-1);
        r23.setText(LocaleController.getString(R.string.StoryVideo));
        r13.addView(r23, LayoutHelper.createFrame(-2, -2.0f, 80, 16.0f, 0.0f, 16.0f, 7.0f));
        r0.addView(r13, LayoutHelper.createLinear(-2, -1, 112, 0, 0, 0, 0));
        r13.setOnClickListener(new StoryModeTabs$$ExternalSyntheticLambda3(this));
        ScaleStateListAnimator.apply(r13);
        addView(r0, LayoutHelper.createFrame(-2, -1, 113));
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        switchModeInternal(-1);
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        switchModeInternal(0);
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        switchModeInternal(1);
    }

    private /* synthetic */ void lambda$switchMode$3(ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        this.mode = r42;
        Utilities.Callback<Float> r0 = this.onSwitchingModeListener;
        if (r0 == null) goto L5;
        r0.run(Float.valueOf(Utilities.clamp(r42, 1.0f, -1.0f)));
    L5:
        this.layout.invalidate();
    }

    private void switchModeInternal(int r2) {
        if (this.toMode == r2) goto L10;
        switchMode(r2);
        Utilities.Callback<Integer> r1 = this.onSwitchModeListener;
        if (r1 == null) goto L9;
        r1.run(Integer.valueOf(r2));
        return;
    L9:
        return;
    }

    public abstract boolean allowTouch();

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (allowTouch() == true) goto L7;
        return false;
    L7:
        return super.dispatchTouchEvent(r2);
    }

    @Override // android.view.View, org.telegram.ui.Stories.recorder.FlashViews.Invertable
    public void invalidate() {
        super.invalidate();
        this.layout.invalidate();
    }

    @Override // org.telegram.ui.Stories.recorder.FlashViews.Invertable
    public void setInvert(float r5) {
        this.invert = r5;
        this.live.setTextColor(ColorUtils.blendARGB(-1, -16777216, r5));
        this.photo.setTextColor(ColorUtils.blendARGB(-1, -16777216, r5));
        this.video.setTextColor(ColorUtils.blendARGB(-1, -16777216, r5));
    }

    public void setOnSwitchModeListener(Utilities.Callback<Integer> r1) {
        this.onSwitchModeListener = r1;
    }

    public void setOnSwitchingModeListener(Utilities.Callback<Float> r1) {
        this.onSwitchingModeListener = r1;
    }

    public void switchMode(int r4) {
        if (this.toMode != r4) goto L5;
        return;
    L5:
        this.toMode = r4;
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{this.mode, r4});
        this.animator = r42;
        r42.addUpdateListener(new StoryModeTabs$$ExternalSyntheticLambda0(this));
        this.animator.setDuration(320);
        this.animator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.animator.start();
    }
}
