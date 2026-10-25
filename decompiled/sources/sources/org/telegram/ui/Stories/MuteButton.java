package org.telegram.ui.Stories;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.blur3.StrokeDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;

/* loaded from: classes7.dex */
public class MuteButton extends FrameLayout {
    private ValueAnimator animator;
    private final StrokeDrawable background;
    private boolean connected;
    private final View filledBackgroundView;
    private final ImageView image;
    private final FrameLayout layout;
    private final View loadingView;
    private ValueAnimator loadingViewAnimator;
    private boolean muted;
    private float mutedT;

    /* renamed from: org.telegram.ui.Stories.MuteButton$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        private final CircularProgressDrawable progressDrawable;
        final /* synthetic */ MuteButton this$0;

        public AnonymousClass1(MuteButton r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.progressDrawable = new CircularProgressDrawable(AndroidUtilities.dp(36.0f), AndroidUtilities.dp(2.0f), -13522392);
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            int r0 = AndroidUtilities.dp(1.0f);
            this.progressDrawable.setBounds(r0, r0, (getWidth() - r0) - r0, (getHeight() - r0) - r0);
            this.progressDrawable.draw(r5);
            invalidate();
        }
    }

    /* renamed from: $r8$lambda$VWY2f6HuE-ONL1SAvkOSG8zI4d4, reason: not valid java name */
    public static /* synthetic */ void m19419$r8$lambda$VWY2f6HuEONL1SAvkOSG8zI4d4(MuteButton r0, ValueAnimator r1) {
        r0.lambda$updateFill$1(r1);
    }

    /* renamed from: $r8$lambda$guiBkbJqKze-HKF0Q8r_7gNBaac, reason: not valid java name */
    public static /* synthetic */ void m19420$r8$lambda$guiBkbJqKzeHKF0Q8r_7gNBaac(MuteButton r0, ValueAnimator r1) {
        r0.lambda$setConnected$0(r1);
    }

    public MuteButton(Context r6, BlurredBackgroundColorProvider r7) {
        super(r6);
        ScaleStateListAnimator.apply(this);
        FrameLayout r0 = new FrameLayout(r6);
        this.layout = r0;
        StrokeDrawable r1 = new StrokeDrawable();
        this.background = r1;
        r1.setColorProvider(r7);
        r1.setBackgroundColor(-14670806);
        r1.setPadding(AndroidUtilities.dp(1.0f));
        r0.setBackground(r1);
        addView(r0, LayoutHelper.createFrame(40, 40, 17));
        View r2 = new View(r6);
        this.filledBackgroundView = r2;
        r2.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(40.0f), -13522392));
        r0.addView(r2, LayoutHelper.createFrame(38, 38, 17));
        r2.setAlpha(0.0f);
        r2.setScaleX(0.0f);
        r2.setScaleY(0.0f);
        View r22 = new AnonymousClass1(this, r6);
        this.loadingView = r22;
        addView(r22, LayoutHelper.createFrame(42, 42, 17));
        ImageView r23 = new ImageView(r6);
        this.image = r23;
        r23.setScaleType(ImageView.ScaleType.CENTER);
        r23.setScaleX(0.75f);
        r23.setScaleY(0.75f);
        r23.setColorFilter(new PorterDuffColorFilter(-2960428, PorterDuff.Mode.SRC_IN));
        r0.addView(r23, LayoutHelper.createFrame(40, 40, 17));
        setMuted(false, false);
    }

    private /* synthetic */ void lambda$setConnected$0(ValueAnimator r1) {
        this.loadingView.setAlpha(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$updateFill$1(ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        this.mutedT = r52;
        this.filledBackgroundView.setAlpha(1.0f - r52);
        this.filledBackgroundView.setScaleX(1.0f - this.mutedT);
        this.filledBackgroundView.setScaleY(1.0f - this.mutedT);
        this.image.setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(-1, -2960428, this.mutedT), PorterDuff.Mode.SRC_IN));
        this.layout.invalidate();
    }

    private void updateFill(boolean r4, boolean r5) {
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.animator = null;
    L5:
        float r02 = 0.0f;
        if (r5 == true) goto L11;
        if (r4 == false) goto L9;
        r02 = 1.0f;
    L9:
        this.mutedT = r02;
        this.filledBackgroundView.setAlpha(1.0f - r02);
        this.filledBackgroundView.setScaleX(1.0f - this.mutedT);
        this.filledBackgroundView.setScaleY(1.0f - this.mutedT);
        this.image.setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(-1, -2960428, this.mutedT), PorterDuff.Mode.SRC_IN));
        this.layout.invalidate();
        return;
    L11:
        float r52 = this.mutedT;
        if (r4 == false) goto L14;
        r02 = 1.0f;
    L14:
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{r52, r02});
        this.animator = r42;
        r42.addUpdateListener(new MuteButton$$ExternalSyntheticLambda1(this));
        this.animator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.animator.setDuration(420);
        this.animator.start();
    }

    public void setConnected(boolean r6, boolean r7) {
        if (this.connected != r6) goto L6;
        if (r7 == false) goto L6;
        return;
    L6:
        this.connected = r6;
        ValueAnimator r0 = this.loadingViewAnimator;
        if (r0 == null) goto L9;
        r0.cancel();
        this.loadingViewAnimator = null;
    L9:
        View r02 = this.loadingView;
        boolean r1 = true;
        float r2 = 1.0f;
        if (r7 == true) goto L18;
        if (r6 == false) goto L13;
        r2 = 0.0f;
    L13:
        r02.setAlpha(r2);
        View r03 = this.loadingView;
        if (r6 == false) goto L16;
        int r22 = 8;
    L17:
        r03.setVisibility(r22);
    L23:
        if (this.muted == true) goto L27;
        if (r6 == false) goto L27;
        r1 = false;
    L27:
        updateFill(r1, r7);
        return;
    L16:
        r22 = 0;
        goto L17
    L18:
        r02.setVisibility(0);
        float r04 = this.loadingView.getAlpha();
        if (r6 == false) goto L21;
        r2 = 0.0f;
    L21:
        ValueAnimator r05 = ValueAnimator.ofFloat(new float[]{r04, r2});
        this.loadingViewAnimator = r05;
        r05.addUpdateListener(new MuteButton$$ExternalSyntheticLambda0(this));
        this.loadingViewAnimator.setDuration(320);
        this.loadingViewAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.loadingViewAnimator.start();
        goto L23
    }

    public void setMuted(boolean r3, boolean r4) {
        this.muted = r3;
        ImageView r0 = this.image;
        if (r4 == true) goto L8;
        if (r3 == false) goto L6;
        int r1 = R.drawable.msg_voice_muted;
    L7:
        AndroidUtilities.updateImageViewImageAnimated(r0, r1);
    L12:
        if (r3 == false) goto L14;
    L17:
        boolean r32 = true;
    L18:
        updateFill(r32, r4);
        return;
    L14:
        if (this.connected == false) goto L17;
        r32 = false;
        goto L18
    L6:
        r1 = R.drawable.msg_voice_unmuted;
        goto L7
    L8:
        if (r3 == false) goto L10;
        int r12 = R.drawable.msg_voice_muted;
    L11:
        r0.setImageResource(r12);
        goto L12
    L10:
        r12 = R.drawable.msg_voice_unmuted;
        goto L11
    }
}
