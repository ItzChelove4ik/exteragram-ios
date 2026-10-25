package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class RateCallLayout extends FrameLayout {
    private final VoIPBackgroundProvider backgroundProvider;
    private OnRateSelected onRateSelected;
    private final RateCallContainer rateCallContainer;
    private final FrameLayout starsContainer;
    private final StarContainer[] startsViews;

    public interface OnRateSelected {
        void onRateSelected(int r1);
    }

    public static class RateCallContainer extends FrameLayout {
        private final VoIPBackgroundProvider backgroundProvider;
        private final RectF bgRect;
        private final TextView messageTextView;
        private final TextView titleTextView;

        public RateCallContainer(Context r12, VoIPBackgroundProvider r13) {
            super(r12);
            this.bgRect = new RectF();
            this.backgroundProvider = r13;
            r13.attach(this);
            setWillNotDraw(false);
            TextView r132 = new TextView(r12);
            this.titleTextView = r132;
            r132.setTextColor(-1);
            r132.setText(LocaleController.getString(R.string.VoipRateCallTitle));
            r132.setTextSize(1, 15.0f);
            r132.setGravity(1);
            r132.setTypeface(AndroidUtilities.bold());
            TextView r3 = new TextView(r12);
            this.messageTextView = r3;
            r3.setTextSize(1, 15.0f);
            r3.setTextColor(-1);
            r3.setGravity(1);
            r3.setText(LocaleController.getString(R.string.VoipRateCallDescription));
            addView(r132, LayoutHelper.createFrame(-1, -2.0f, 3, 0.0f, 24.0f, 0.0f, 0.0f));
            addView(r3, LayoutHelper.createFrame(-1, -2.0f, 3, 0.0f, 50.0f, 0.0f, 0.0f));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            this.bgRect.set(0.0f, 0.0f, getWidth(), getHeight());
            this.backgroundProvider.setDarkTranslation(getX() + ((View) getParent()).getX(), getY() + ((View) getParent()).getY());
            r5.drawRoundRect(this.bgRect, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), this.backgroundProvider.getDarkPaint());
            super.dispatchDraw(r5);
        }
    }

    public static class StarContainer extends FrameLayout {
        private AllStarsProvider allStarsProvider;
        public RLottieImageView defaultStar;
        private OnSelectedStar onSelectedStar;
        private int pos;
        private final Drawable rippleDrawable;
        public RLottieImageView selectedStar;

        public interface AllStarsProvider {
            StarContainer[] getAllStartsViews();
        }

        public interface OnSelectedStar {
            void onSelected(float r1, float r2, int r3);
        }

        public StarContainer(Context r5) {
            super(r5);
            this.pos = 0;
            setWillNotDraw(false);
            this.defaultStar = new RLottieImageView(r5);
            this.selectedStar = new RLottieImageView(r5);
            this.defaultStar.setAnimation(R.raw.star_stroke, 37, 37);
            this.selectedStar.setAnimation(R.raw.star_fill, 37, 37);
            this.selectedStar.setAlpha(0.0f);
            addView(this.defaultStar, LayoutHelper.createFrame(37, 37.0f));
            addView(this.selectedStar, LayoutHelper.createFrame(37, 37.0f));
            Drawable r52 = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(37.0f), 0, ColorUtils.setAlphaComponent(-1, 76));
            this.rippleDrawable = r52;
            r52.setCallback(this);
            setClickable(true);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r11) {
            int r0 = r11.getAction();
            int r2 = 0;
            if (r0 == 0) goto L22;
            if (r0 != 1) goto L6;
            AllStarsProvider r02 = this.allStarsProvider;
            if (r02 == null) goto L20;
            StarContainer[] r03 = r02.getAllStartsViews();
            int r1 = 0;
        L17:
            if (r1 > this.pos) goto L20;
            StarContainer r7 = r03[r1];
            RLottieImageView r8 = r7.defaultStar;
            RLottieImageView r72 = r7.selectedStar;
            r8.animate().scaleX(1.0f).scaleY(1.0f).setDuration(250).start();
            r72.animate().scaleX(1.0f).scaleY(1.0f).setDuration(250).start();
            r1 = r1 + 1;
        L20:
            if (this.onSelectedStar == null) goto L33;
            int[] r04 = new int[2];
            getLocationOnScreen(r04);
            this.onSelectedStar.onSelected(r04[0] + (getWidth() / 2.0f), r04[1] + (getHeight() / 2.0f), this.pos + 1);
        L33:
            return super.dispatchTouchEvent(r11);
        L6:
            if (r0 != 3) goto L33;
            AllStarsProvider r05 = this.allStarsProvider;
            if (r05 == null) goto L33;
            StarContainer[] r06 = r05.getAllStartsViews();
            int r5 = r06.length;
        L11:
            if (r2 >= r5) goto L33;
            StarContainer r73 = r06[r2];
            RLottieImageView r82 = r73.defaultStar;
            RLottieImageView r74 = r73.selectedStar;
            r82.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(250).start();
            r74.animate().alpha(0.0f).scaleX(1.0f).scaleY(1.0f).setDuration(250).start();
            r2 = r2 + 1;
            goto L11
        L22:
            AllStarsProvider r07 = this.allStarsProvider;
            if (r07 == null) goto L33;
            StarContainer[] r08 = r07.getAllStartsViews();
        L25:
            int r75 = this.pos;
            if (r2 > r75) goto L28;
            StarContainer r76 = r08[r2];
            RLottieImageView r83 = r76.defaultStar;
            RLottieImageView r77 = r76.selectedStar;
            r83.animate().alpha(0.0f).scaleX(0.8f).scaleY(0.8f).setDuration(250).start();
            r77.animate().alpha(1.0f).scaleX(0.8f).scaleY(0.8f).setDuration(250).start();
            r2 = r2 + 1;
            goto L25
        L28:
            int r78 = r75 + 1;
        L30:
            if (r78 >= r08.length) goto L33;
            StarContainer r22 = r08[r78];
            RLottieImageView r52 = r22.defaultStar;
            RLottieImageView r23 = r22.selectedStar;
            r52.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(250).start();
            r23.animate().alpha(0.0f).scaleX(1.0f).scaleY(1.0f).setDuration(250).start();
            r78 = r78 + 1;
            goto L30
        }

        @Override // android.view.ViewGroup, android.view.View
        public void drawableStateChanged() {
            super.drawableStateChanged();
            Drawable r0 = this.rippleDrawable;
            if (r0 == null) goto L6;
            r0.setState(getDrawableState());
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void jumpDrawablesToCurrentState() {
            super.jumpDrawablesToCurrentState();
            Drawable r0 = this.rippleDrawable;
            if (r0 == null) goto L6;
            r0.jumpToCurrentState();
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            super.onDraw(r5);
            this.rippleDrawable.setBounds(0, 0, getWidth(), getHeight());
            this.rippleDrawable.draw(r5);
        }

        public void setAllStarsProvider(AllStarsProvider r1) {
            this.allStarsProvider = r1;
        }

        public void setOnSelectedStar(OnSelectedStar r1, int r2) {
            this.onSelectedStar = r1;
            this.pos = r2;
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (this.rippleDrawable != r2) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public static /* synthetic */ StarContainer[] $r8$lambda$8aSLVOIpavH4Dtk8RTaiZgIy2sA(RateCallLayout r0) {
        return r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$fuPVe0odXcJSuPj0DphleEBfGmc(RateCallLayout r0, RLottieImageView r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$hm5mV5LsQ9axSTOLS61IflXG9ZI(RateCallLayout r0, RLottieImageView r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$k0VtckU77XhixXgV6tjc2I59LIU(RateCallLayout r0, Context r1, float r2, float r3, int r4) {
        r0.lambda$new$3(r1, r2, r3, r4);
    }

    public RateCallLayout(Context r12, VoIPBackgroundProvider r13) {
        super(r12);
        this.startsViews = new StarContainer[5];
        this.backgroundProvider = r13;
        int r1 = 0;
        setWillNotDraw(false);
        RateCallContainer r2 = new RateCallContainer(r12, r13);
        this.rateCallContainer = r2;
        FrameLayout r132 = new FrameLayout(r12);
        this.starsContainer = r132;
        r2.setVisibility(8);
        r132.setVisibility(8);
    L3:
        if (r1 >= 5) goto L5;
        this.startsViews[r1] = new StarContainer(r12);
        this.startsViews[r1].setAllStarsProvider(new RateCallLayout$$ExternalSyntheticLambda0(this));
        this.startsViews[r1].setOnSelectedStar(new RateCallLayout$$ExternalSyntheticLambda1(this, r12), r1);
        this.starsContainer.addView(this.startsViews[r1], LayoutHelper.createFrame(-2, -2.0f, 51, r1 * 41, 0.0f, 0.0f, 0.0f));
        r1 = r1 + 1;
        goto L3
    L5:
        addView(this.rateCallContainer, LayoutHelper.createFrame(300, 152.0f, 49, 0.0f, 0.0f, 0.0f, 0.0f));
        addView(this.starsContainer, LayoutHelper.createFrame(201, 100.0f, 49, 0.0f, 90.0f, 0.0f, 0.0f));
    }

    private /* synthetic */ StarContainer[] lambda$new$0() {
        return this.startsViews;
    }

    private /* synthetic */ void lambda$new$1(RLottieImageView r1) {
        removeView(r1);
    }

    private /* synthetic */ void lambda$new$2(RLottieImageView r2) {
        AndroidUtilities.runOnUIThread(new RateCallLayout$$ExternalSyntheticLambda3(this, r2));
    }

    private /* synthetic */ void lambda$new$3(Context r7, float r8, float r9, int r10) {
        if (r10 < 4) goto L5;
        RLottieImageView r0 = new RLottieImageView(r7);
        int r1 = AndroidUtilities.dp(133.0f);
        r0.setAnimation(R.raw.rate, 133, 133);
        int[] r2 = new int[2];
        getLocationOnScreen(r2);
        int r4 = r2[0];
        int r22 = r2[1];
        addView(r0, LayoutHelper.createFrame(133, 133.0f));
        float r72 = r1 / 2.0f;
        r0.setTranslationX((r8 - r4) - r72);
        r0.setTranslationY((r9 - r22) - r72);
        r0.setOnAnimationEndListener(new RateCallLayout$$ExternalSyntheticLambda2(this, r0));
        r0.playAnimation();
    L5:
        OnRateSelected r6 = this.onRateSelected;
        if (r6 == null) goto L9;
        r6.onRateSelected(r10);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void show(OnRateSelected r23) {
        this.onRateSelected = r23;
        this.rateCallContainer.setVisibility(0);
        this.starsContainer.setVisibility(0);
        AnimatorSet r1 = new AnimatorSet();
        Property r6 = View.ALPHA;
        ObjectAnimator r3 = ObjectAnimator.ofFloat(this.rateCallContainer, r6, new float[]{0.0f, 1.0f});
        Property r8 = View.SCALE_X;
        ObjectAnimator r5 = ObjectAnimator.ofFloat(this.rateCallContainer, r8, new float[]{0.7f, 1.0f});
        Property r10 = View.SCALE_Y;
        boolean r11 = true;
        float r13 = 0.0f;
        Property r14 = View.TRANSLATION_Y;
        r1.playTogether(new Animator[]{r3, r5, ObjectAnimator.ofFloat(this.rateCallContainer, r10, new float[]{0.7f, 1.0f}), ObjectAnimator.ofFloat(this.rateCallContainer, r14, new float[]{AndroidUtilities.dp(24.0f), 0.0f})});
        r1.setInterpolator(CubicBezierInterpolator.DEFAULT);
        Object[] r52 = 3;
        r1.setDuration(250);
        int r7 = 0;
    L4:
        if (r7 >= this.startsViews.length) goto L6;
        AnimatorSet r9 = new AnimatorSet();
        this.startsViews[r7].setAlpha(r13);
        Object[] r16 = r52;
        ObjectAnimator r53 = ObjectAnimator.ofFloat(this.startsViews[r7], r6, new float[]{0.0f, 1.0f});
        boolean r17 = r11;
        ObjectAnimator r112 = ObjectAnimator.ofFloat(this.startsViews[r7], r8, new float[]{0.3f, 1.0f});
        float r18 = r13;
        ObjectAnimator r132 = ObjectAnimator.ofFloat(this.startsViews[r7], r10, new float[]{0.3f, 1.0f});
        StarContainer r15 = this.startsViews[r7];
        float[] r32 = new float[2];
        r32[0] = AndroidUtilities.dp(30.0f);
        r32[r17 ? 1 : 0] = r18;
        ObjectAnimator r2 = ObjectAnimator.ofFloat(r15, r14, r32);
        Animator[] r33 = new Animator[4];
        r33[0] = r53;
        r33[r17 ? 1 : 0] = r112;
        r33[2] = r132;
        r33[r16 == true ? 1 : 0] = r2;
        r9.playTogether(r33);
        r9.setDuration(250);
        r9.setStartDelay(r7 * 16);
        r9.start();
        r7 = r7 + 1;
        r52 = r16 == true ? 1 : 0;
        r11 = r17 ? 1 : 0;
        r13 = r18;
        goto L4
    L6:
        r1.start();
    }
}
