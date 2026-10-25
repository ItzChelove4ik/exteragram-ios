package org.telegram.ui.Components.Premium;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.core.math.MathUtils;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EllipsizeSpanAnimator;
import org.telegram.ui.Components.EmptyStubSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes7.dex */
public class LimitPreviewView extends LinearLayout {
    private boolean animate;
    private boolean animateArrowFadeIn;
    private boolean animateArrowFadeOut;
    private boolean animateBackgroundFade;
    private boolean animateIncrease;
    private int animateIncreaseWidth;
    private Runnable animateStarRatingRunnable;
    private boolean animatingRotation;
    boolean animationCanPlay;
    private ValueAnimator arrowAnimator;
    private int currentValue;
    private DarkGradientProvider darkGradientProvider;
    TextView defaultCount;
    private final FrameLayout defaultLayout;
    private final AnimatedTextView defaultText;
    private boolean drawFromRight;
    public int gradientTotalHeight;
    int gradientYOffset;
    private boolean hideNegativeValues;
    int icon;
    float iconScale;
    boolean inc;
    public boolean invalidationEnabled;
    private boolean isBoostsStyle;
    private boolean isRatingNegative;
    private boolean isRatingStyle;
    private boolean isSimpleStyle;
    public boolean isStatistic;
    CounterView limitIcon;
    float limitIconRotation;
    FrameLayout limitsContainer;
    private View parentVideForGradient;
    private float percent;
    private float position;
    AnimatedTextView premiumCount;
    private final FrameLayout premiumLayout;
    private final int premiumLimit;
    private boolean premiumLocked;
    private final TextView premiumText;
    float progress;
    private final Paint ratingPaint;
    Theme.ResourcesProvider resourcesProvider;
    PremiumGradient.PremiumGradientTools staticGradient;
    boolean wasAnimation;
    boolean wasHaptic;
    int width1;

    /* renamed from: org.telegram.ui.Components.Premium.LimitPreviewView$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        Paint grayPaint;
        final /* synthetic */ LimitPreviewView this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
        Paint whitePaint;

        public AnonymousClass1(LimitPreviewView r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            this.val$resourcesProvider = r3;
            super(r2);
            this.grayPaint = new Paint();
            Paint r12 = new Paint();
            this.whitePaint = r12;
            r12.setColor(-1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r14) {
            if (LimitPreviewView.m11968$$Nest$fgetisBoostsStyle(this.this$0) == false) goto L11;
            LimitPreviewView r0 = this.this$0;
            if (r0.isStatistic == false) goto L7;
        L10:
            this.grayPaint.setColor(Theme.getColor(Theme.key_listSelector, this.val$resourcesProvider));
        L12:
            RectF r02 = AndroidUtilities.rectTmp;
            r02.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            if (LimitPreviewView.m11979$$Nest$mhasDarkGradientProvider(this.this$0) == false) goto L15;
            r14.drawRoundRect(r02, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), LimitPreviewView.m11964$$Nest$fgetdarkGradientProvider(this.this$0).setDarkGradientLocation(((ViewGroup) getParent()).getX() + getX(), ((ViewGroup) getParent()).getY() + getY()));
        L16:
            r14.save();
            if (LimitPreviewView.m11968$$Nest$fgetisBoostsStyle(this.this$0) == true) goto L19;
            r14.clipRect(this.this$0.width1, 0, getMeasuredWidth(), getMeasuredHeight());
        L19:
            boolean r03 = LimitPreviewView.m11970$$Nest$fgetisRatingStyle(this.this$0);
            LimitPreviewView r1 = this.this$0;
            if (r03 == false) goto L23;
            Paint r04 = LimitPreviewView.m11976$$Nest$fgetratingPaint(r1);
        L27:
            if (LimitPreviewView.m11971$$Nest$fgetparentVideForGradient(this.this$0) == null) goto L35;
            View r12 = LimitPreviewView.m11971$$Nest$fgetparentVideForGradient(this.this$0);
            PremiumGradient.PremiumGradientTools r5 = this.this$0.staticGradient;
            if (r5 == null) goto L31;
            r04 = r5.paint;
            r5.gradientMatrixLinear(r4.gradientTotalHeight, -r4.gradientYOffset);
        L36:
            int r13 = r04.getAlpha();
            if (LimitPreviewView.m11960$$Nest$fgetanimateArrowFadeOut(this.this$0) == false) goto L42;
            if (LimitPreviewView.m11962$$Nest$fgetarrowAnimator(this.this$0) == null) goto L42;
            r04.setAlpha((int) (r13 * (1.0f - ((Float) LimitPreviewView.m11962$$Nest$fgetarrowAnimator(this.this$0).getAnimatedValue()).floatValue())));
        L47:
            if (LimitPreviewView.m11968$$Nest$fgetisBoostsStyle(this.this$0) == true) goto L49;
        L55:
            r14.drawRoundRect(AndroidUtilities.rectTmp, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), r04);
            r04.setAlpha(r13);
            r14.restore();
            LimitPreviewView r05 = this.this$0;
            if (r05.staticGradient == null) goto L58;
        L60:
            super.dispatchDraw(r14);
            return;
        L58:
            if (r05.invalidationEnabled == false) goto L60;
            invalidate();
            goto L60
        L49:
            if (LimitPreviewView.m11969$$Nest$fgetisRatingNegative(this.this$0) == false) goto L51;
        L54:
            AndroidUtilities.rectTmp.set(this.this$0.width1, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            goto L55
        L51:
            if (LimitPreviewView.m11967$$Nest$fgetdrawFromRight(this.this$0) == true) goto L54;
            AndroidUtilities.rectTmp.set(0.0f, 0.0f, this.this$0.width1, getMeasuredHeight());
        L42:
            if (LimitPreviewView.m11959$$Nest$fgetanimateArrowFadeIn(this.this$0) == false) goto L47;
            if (LimitPreviewView.m11962$$Nest$fgetarrowAnimator(this.this$0) == null) goto L47;
            r04.setAlpha((int) (r13 * ((Float) LimitPreviewView.m11962$$Nest$fgetarrowAnimator(this.this$0).getAnimatedValue()).floatValue()));
            goto L47
        L31:
            View r4 = this;
            float r52 = 0.0f;
        L32:
            if (r4 == r12) goto L34;
            r52 = r52 + r4.getY();
            r4 = (View) r4.getParent();
            goto L32
        L34:
            PremiumGradient.getInstance().updateMainGradientMatrix(0, 0, r12.getMeasuredWidth(), r12.getMeasuredHeight(), LimitPreviewView.m11978$$Nest$mgetGlobalXOffset(this.this$0) - getLeft(), -r52);
            goto L36
        L35:
            PremiumGradient.getInstance().updateMainGradientMatrix(0, 0, this.this$0.getMeasuredWidth(), this.this$0.getMeasuredHeight(), LimitPreviewView.m11978$$Nest$mgetGlobalXOffset(this.this$0) - getLeft(), -getTop());
            goto L36
        L23:
            if (LimitPreviewView.m11979$$Nest$mhasDarkGradientProvider(r1) == false) goto L25;
            r04 = this.whitePaint;
            goto L27
        L25:
            r04 = PremiumGradient.getInstance().getMainGradientPaint();
            goto L27
        L15:
            r14.drawRoundRect(r02, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.grayPaint);
            goto L16
        L7:
            if (LimitPreviewView.m11970$$Nest$fgetisRatingStyle(r0) == true) goto L10;
            this.grayPaint.setColor(Theme.getColor(Theme.key_graySection, this.val$resourcesProvider));
            goto L12
        L11:
            this.grayPaint.setColor(Theme.getColor(Theme.key_windowBackgroundGray, this.val$resourcesProvider));
            goto L12
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r9, int r10, int r11, int r12, int r13) {
            if (getChildCount() != 2) goto L6;
            View r0 = getChildAt(0);
            View r8 = getChildAt(1);
            int r1 = r0.getMeasuredWidth();
            int r132 = r13 - r11;
            r0.layout(0, 0, r1, r132);
            r8.layout(r1, 0, r12 - r10, r132);
            return;
        L6:
            super.onLayout(r9, r10, r11, r12, r13);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            if (getChildCount() != 2) goto L43;
            int r92 = View.MeasureSpec.getSize(r9);
            int r102 = View.MeasureSpec.getSize(r10);
            LimitPreviewView.m11965$$Nest$fgetdefaultLayout(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r92, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r102, TLObject.FLAG_30));
            int r0 = LimitPreviewView.m11965$$Nest$fgetdefaultLayout(this.this$0).getMeasuredWidth();
            int r4 = AndroidUtilities.dp(24.0f) + LimitPreviewView.m11966$$Nest$fgetdefaultText(this.this$0).getMeasuredWidth();
            int r6 = 0;
            if (this.this$0.defaultCount.getVisibility() != 0) goto L7;
            int r5 = AndroidUtilities.dp(24.0f) + this.this$0.defaultCount.getMeasuredWidth();
        L8:
            int r02 = Math.max(r0, r4 + r5);
            LimitPreviewView.m11973$$Nest$fgetpremiumLayout(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r92, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r102, TLObject.FLAG_30));
            boolean r1 = LimitPreviewView.m11968$$Nest$fgetisBoostsStyle(this.this$0);
            LimitPreviewView r42 = this.this$0;
            if (r1 == false) goto L37;
            float r03 = LimitPreviewView.m11972$$Nest$fgetpercent(r42);
            float r12 = 0.0f;
            LimitPreviewView r2 = this.this$0;
            if (r03 != 0.0f) goto L17;
            r2.width1 = 0;
            if (LimitPreviewView.m11959$$Nest$fgetanimateArrowFadeIn(r2) == false) goto L15;
        L41:
            setMeasuredDimension(r92, r102);
            return;
        L15:
            if (LimitPreviewView.m11960$$Nest$fgetanimateArrowFadeOut(this.this$0) == true) goto L41;
            LimitPreviewView.m11980$$Nest$mupdateProgressTextColors(this.this$0);
            goto L41
        L17:
            float r04 = LimitPreviewView.m11972$$Nest$fgetpercent(r2);
            LimitPreviewView r22 = this.this$0;
            if (r04 < 1.0f) goto L20;
            r22.width1 = r92;
            if (LimitPreviewView.m11959$$Nest$fgetanimateArrowFadeIn(r22) == true) goto L41;
            if (LimitPreviewView.m11960$$Nest$fgetanimateArrowFadeOut(this.this$0) == true) goto L41;
            LimitPreviewView.m11980$$Nest$mupdateProgressTextColors(this.this$0);
            goto L41
        L20:
            if (LimitPreviewView.m11969$$Nest$fgetisRatingNegative(r22) == false) goto L22;
            float r05 = 0.0f;
        L24:
            if (LimitPreviewView.m11969$$Nest$fgetisRatingNegative(this.this$0) == true) goto L27;
            r12 = LimitPreviewView.m11973$$Nest$fgetpremiumLayout(this.this$0).getMeasuredWidth() - AndroidUtilities.dp(8.0f);
        L27:
            float r23 = (r92 - r05) - r12;
            LimitPreviewView r13 = this.this$0;
            r13.width1 = (int) (r05 + (r23 * LimitPreviewView.m11972$$Nest$fgetpercent(r13)));
            if (LimitPreviewView.m11959$$Nest$fgetanimateArrowFadeIn(this.this$0) == true) goto L41;
            if (LimitPreviewView.m11960$$Nest$fgetanimateArrowFadeOut(this.this$0) == true) goto L41;
            LimitPreviewView.m11980$$Nest$mupdateProgressTextColors(this.this$0);
            goto L41
        L22:
            r05 = LimitPreviewView.m11965$$Nest$fgetdefaultLayout(this.this$0).getMeasuredWidth() - AndroidUtilities.dp(8.0f);
            goto L24
        L37:
            int r14 = LimitPreviewView.m11973$$Nest$fgetpremiumLayout(r42).getMeasuredWidth();
            int r43 = AndroidUtilities.dp(24.0f) + LimitPreviewView.m11975$$Nest$fgetpremiumText(this.this$0).getMeasuredWidth();
            if (this.this$0.premiumCount.getVisibility() != 0) goto L40;
            r6 = AndroidUtilities.dp(24.0f) + this.this$0.premiumCount.getMeasuredWidth();
        L40:
            int r15 = Math.max(r14, r43 + r6);
            LimitPreviewView r24 = this.this$0;
            r24.width1 = (int) Utilities.clamp(r92 * LimitPreviewView.m11972$$Nest$fgetpercent(r24), r92 - r15, r02);
            LimitPreviewView.m11965$$Nest$fgetdefaultLayout(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(this.this$0.width1, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r102, TLObject.FLAG_30));
            LimitPreviewView.m11973$$Nest$fgetpremiumLayout(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r92 - this.this$0.width1, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r102, TLObject.FLAG_30));
            goto L41
        L7:
            r5 = 0;
            goto L8
        L43:
            super.onMeasure(r9, r10);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.LimitPreviewView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ LimitPreviewView this$0;
        final /* synthetic */ boolean val$animatingRotate;

        public AnonymousClass2(LimitPreviewView r1, boolean r2) {
            this.this$0 = r1;
            this.val$animatingRotate = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.val$animatingRotate == false) goto L6;
            LimitPreviewView.m11977$$Nest$fputanimatingRotation(this.this$0, false);
        L6:
            if (LimitPreviewView.m11961$$Nest$fgetanimateStarRatingRunnable(this.this$0) == null) goto L9;
            AndroidUtilities.cancelRunOnUIThread(LimitPreviewView.m11961$$Nest$fgetanimateStarRatingRunnable(this.this$0));
            LimitPreviewView.m11961$$Nest$fgetanimateStarRatingRunnable(this.this$0).run();
            return;
        }
    }

    public class CounterView extends View {
        ArrayList<AnimatedLayout> animatedLayouts;
        StaticLayout animatedStableLayout;
        boolean animationInProgress;
        float arrowCenter;
        Paint dstOutPaint;
        boolean invalidatePath;
        Paint overlayPaint;
        Path path;
        PathEffect pathEffect;
        CharSequence text;
        StaticLayout textLayout;
        TextPaint textPaint;
        float textWidth;
        final /* synthetic */ LimitPreviewView this$0;

        /* renamed from: org.telegram.ui.Components.Premium.LimitPreviewView$CounterView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ CounterView this$1;
            final /* synthetic */ AnimatedLayout val$layout;

            public AnonymousClass1(CounterView r1, AnimatedLayout r2) {
                this.this$1 = r1;
                this.val$layout = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                this.val$layout.valueAnimator = null;
                CounterView.m11981$$Nest$mcheckAnimationComplete(this.this$1);
            }
        }

        /* renamed from: org.telegram.ui.Components.Premium.LimitPreviewView$CounterView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ CounterView this$1;
            final /* synthetic */ AnimatedLayout val$layout;

            public AnonymousClass2(CounterView r1, AnimatedLayout r2) {
                this.this$1 = r1;
                this.val$layout = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                this.val$layout.valueAnimator = null;
                CounterView.m11981$$Nest$mcheckAnimationComplete(this.this$1);
            }
        }

        public class AnimatedLayout {
            public boolean direction;
            float progress;
            public boolean replace;
            ArrayList<StaticLayout> staticLayouts;
            final /* synthetic */ CounterView this$1;
            ValueAnimator valueAnimator;
            float x;

            private AnimatedLayout(CounterView r1) {
                this.this$1 = r1;
                this.staticLayouts = new ArrayList();
            }

            public /* synthetic */ AnimatedLayout(CounterView r1, LimitPreviewViewIA r2) {
                this(r1);
            }
        }

        public static /* synthetic */ void $r8$lambda$I9pBmqx8YQRH2ID5h9YHFRLCWbA(CounterView r0, AnimatedLayout r1, ValueAnimator r2) {
            r0.lambda$createAnimationLayouts$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$eYPOIutpnkUR7P3qJHkVdVJVo_E(CounterView r0, AnimatedLayout r1, ValueAnimator r2) {
            r0.lambda$createAnimationLayoutsDiff$1(r1, r2);
        }

        /* renamed from: -$$Nest$mcheckAnimationComplete, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11981$$Nest$mcheckAnimationComplete(CounterView r0) {
            r0.checkAnimationComplete();
        }

        public CounterView(LimitPreviewView r2, Context r3) {
            this.this$0 = r2;
            super(r3);
            this.path = new Path();
            this.pathEffect = new CornerPathEffect(AndroidUtilities.dp(6.0f));
            this.textPaint = new TextPaint(1);
            this.animatedLayouts = new ArrayList();
            this.dstOutPaint = new Paint();
            this.overlayPaint = new Paint();
            this.textPaint.setTypeface(AndroidUtilities.bold());
            this.textPaint.setTextSize(AndroidUtilities.dp(22.0f));
            TextPaint r32 = this.textPaint;
            if (r2.isStatistic == false) goto L5;
            int r22 = -1;
        L6:
            r32.setColor(r22);
            this.dstOutPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            this.overlayPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
            return;
        L5:
            r22 = Theme.getColor(Theme.key_featuredStickers_buttonText);
            goto L6
        }

        private void checkAnimationComplete() {
            int r1 = 0;
        L3:
            int r2 = this.animatedLayouts.size();
            ArrayList<AnimatedLayout> r3 = this.animatedLayouts;
            if (r1 >= r2) goto L9;
            if (r3.get(r1).valueAnimator != null) goto L7;
            r1 = r1 + 1;
            goto L3
        L7:
            return;
        L9:
            r3.clear();
            this.animationInProgress = false;
            invalidate();
        }

        private /* synthetic */ void lambda$createAnimationLayouts$0(AnimatedLayout r1, ValueAnimator r2) {
            r1.progress = ((Float) r2.getAnimatedValue()).floatValue();
            invalidate();
        }

        private /* synthetic */ void lambda$createAnimationLayoutsDiff$1(AnimatedLayout r1, ValueAnimator r2) {
            r1.progress = ((Float) r2.getAnimatedValue()).floatValue();
            invalidate();
        }

        private void updatePath() {
            int r0 = getMeasuredHeight() - AndroidUtilities.dp(8.0f);
            float r2 = getMeasuredWidth() * this.arrowCenter;
            float r3 = Utilities.clamp(AndroidUtilities.dp(8.0f) + r2, getMeasuredWidth(), 0.0f);
            float r4 = 10.0f;
            float r6 = Utilities.clamp(AndroidUtilities.dp(10.0f) + r2, getMeasuredWidth(), AndroidUtilities.dp(24.0f));
            if (this.arrowCenter < 0.7f) goto L6;
            r4 = 24.0f;
        L6:
            float r42 = Utilities.clamp(r2 - AndroidUtilities.dp(r4), getMeasuredWidth(), 0.0f);
            float r5 = Utilities.clamp(r2 - AndroidUtilities.dp(8.0f), getMeasuredWidth(), 0.0f);
            this.path.rewind();
            float r8 = r0;
            float r11 = r8 - (r8 / 2.0f);
            this.path.moveTo(r42, r11 - AndroidUtilities.dp(2.0f));
            this.path.lineTo(r42, r8);
            this.path.lineTo(r5, r8);
            this.path.lineTo(r2, r0 + AndroidUtilities.dp(8.0f));
            if (this.arrowCenter >= 0.7f) goto L9;
            this.path.lineTo(r3, r8);
        L9:
            this.path.lineTo(r6, r8);
            this.path.lineTo(r6, r11 - AndroidUtilities.dp(2.0f));
            this.path.close();
        }

        public void createAnimationLayouts() {
            this.animatedLayouts.clear();
            if (LimitPreviewView.m11968$$Nest$fgetisBoostsStyle(this.this$0) == true) goto L5;
        L7:
            SpannableStringBuilder r3 = new SpannableStringBuilder(this.text);
            int r1 = 0;
            int r2 = 0;
            int r5 = 0;
            boolean r4 = true;
        L9:
            if (r2 >= this.text.length()) goto L26;
            if (Character.isDigit(this.text.charAt(r2)) == false) goto L25;
            AnimatedLayout r6 = new AnimatedLayout(this, null);
            this.animatedLayouts.add(r6);
            r6.x = this.textLayout.getSecondaryHorizontal(r2);
            r6.direction = r4;
            if (r5 < 1) goto L15;
            r4 = !r4;
            r5 = 0;
        L15:
            r5 = r5 + 1;
            int r7 = this.text.charAt(r2) - '0';
            if (r7 != 0) goto L18;
            r7 = 10;
        L18:
            int r9 = 1;
        L19:
            if (r9 > r7) goto L24;
            if (r9 != 10) goto L22;
            int r11 = 0;
        L23:
            r6.staticLayouts.add(new StaticLayout(_UrlKt.FRAGMENT_ENCODE_SET + r11, this.textPaint, (int) this.textWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false));
            r9 = r9 + 1;
            goto L19
        L22:
            r11 = r9;
            goto L23
        L24:
            r3.setSpan(new EmptyStubSpan(), r2, r2 + 1, 0);
        L25:
            r2 = r2 + 1;
            goto L9
        L26:
            this.animatedStableLayout = new StaticLayout(r3, this.textPaint, ((int) this.textWidth) + AndroidUtilities.dp(12.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        L28:
            if (r1 >= this.animatedLayouts.size()) goto L38;
            this.animationInProgress = true;
            AnimatedLayout r22 = this.animatedLayouts.get(r1);
            ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r22.valueAnimator = r32;
            r32.addUpdateListener(new LimitPreviewView$CounterView$$ExternalSyntheticLambda1(this, r22));
            r22.valueAnimator.addListener(new AnonymousClass1(this, r22));
            r22.valueAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            r22.valueAnimator.setDuration(750);
            r22.valueAnimator.setStartDelay(((this.animatedLayouts.size() - 1) - r1) * 60);
            r22.valueAnimator.start();
            r1 = r1 + 1;
            goto L28
        L38:
            return;
        L5:
            if (LimitPreviewView.m11963$$Nest$fgetcurrentValue(this.this$0) != 0) goto L7;
        }

        public void createAnimationLayoutsDiff(CharSequence r25) {
            if (this.textLayout == null) goto L24;
            this.animatedLayouts.clear();
            SpannableStringBuilder r3 = new SpannableStringBuilder(this.text);
            int r1 = this.text.length() - 1;
            int r11 = 0;
            int r2 = 0;
        L6:
            if (r1 < 0) goto L20;
            if (r1 >= r25.length()) goto L10;
            char r5 = r25.charAt(r1);
        L12:
            if (r5 == this.text.charAt(r1)) goto L19;
            if (Character.isDigit(this.text.charAt(r1)) == false) goto L19;
            AnimatedLayout r6 = new AnimatedLayout(this, null);
            this.animatedLayouts.add(r6);
            r6.x = this.textLayout.getSecondaryHorizontal(r1);
            r6.replace = true;
            if (r2 < 1) goto L18;
            r2 = 0;
        L18:
            r2 = r2 + 1;
            String r13 = _UrlKt.FRAGMENT_ENCODE_SET + r5;
            TextPaint r14 = this.textPaint;
            int r15 = (int) this.textWidth;
            Layout.Alignment r16 = Layout.Alignment.ALIGN_NORMAL;
            r6.staticLayouts.add(new StaticLayout(r13, r14, r15, r16, 1.0f, 0.0f, false));
            r6.staticLayouts.add(new StaticLayout(_UrlKt.FRAGMENT_ENCODE_SET + this.text.charAt(r1), this.textPaint, (int) this.textWidth, r16, 1.0f, 0.0f, false));
            r3.setSpan(new EmptyStubSpan(), r1, r1 + 1, 0);
        L19:
            r1 = r1 - 1;
            goto L6
        L10:
            r5 = ' ';
            goto L12
        L20:
            this.animatedStableLayout = new StaticLayout(r3, this.textPaint, AndroidUtilities.dp(12.0f) + ((int) this.textWidth), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        L22:
            if (r11 >= this.animatedLayouts.size()) goto L30;
            this.animationInProgress = true;
            AnimatedLayout r12 = this.animatedLayouts.get(r11);
            ValueAnimator r22 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r12.valueAnimator = r22;
            r22.addUpdateListener(new LimitPreviewView$CounterView$$ExternalSyntheticLambda0(this, r12));
            r12.valueAnimator.addListener(new AnonymousClass2(this, r12));
            r12.valueAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            r12.valueAnimator.setDuration(250);
            r12.valueAnimator.setStartDelay(((this.animatedLayouts.size() - 1) - r11) * 60);
            r12.valueAnimator.start();
            r11 = r11 + 1;
            goto L22
        L30:
            return;
        }

        public float getArrowCenter() {
            return this.arrowCenter;
        }

        @Override // android.view.View
        public void onDraw(Canvas r20) {
            int r2 = getMeasuredHeight() - AndroidUtilities.dp(8.0f);
            TextPaint r3 = this.textPaint;
            if (this.this$0.isStatistic == false) goto L5;
            int r4 = -1;
        L6:
            r3.setColor(r4);
            if (LimitPreviewView.m11974$$Nest$fgetpremiumLocked(this.this$0) == false) goto L11;
            r2 = getMeasuredHeight();
            PremiumGradient.getInstance().updateMainGradientMatrix(0, 0, this.this$0.getMeasuredWidth(), this.this$0.getMeasuredHeight(), LimitPreviewView.m11978$$Nest$mgetGlobalXOffset(this.this$0) - getX(), -getTop());
            RectF r32 = AndroidUtilities.rectTmp;
            r32.set(0.0f, AndroidUtilities.dp(3.0f), getMeasuredWidth(), r2 - AndroidUtilities.dp(3.0f));
            float r42 = r2 / 2.0f;
            r20.drawRoundRect(r32, r42, r42, PremiumGradient.getInstance().getPremiumLocakedPaint());
        L9:
            int r12 = r2;
            if (LimitPreviewView.m11979$$Nest$mhasDarkGradientProvider(this.this$0) == false) goto L39;
            r20.saveLayer(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.dstOutPaint, 31);
        L39:
            float r22 = (getMeasuredWidth() - this.textWidth) / 2.0f;
            float r33 = (r12 - this.textLayout.getHeight()) / 2.0f;
            if (this.animationInProgress == false) goto L42;
            r20.save();
            r20.clipRect(0, 0, getMeasuredWidth(), getMeasuredHeight() - AndroidUtilities.dp(8.0f));
            if (this.animatedStableLayout == null) goto L47;
            r20.save();
            r20.translate(r22, r33);
            this.animatedStableLayout.draw(r20);
            r20.restore();
        L47:
            int r43 = 0;
        L49:
            if (r43 >= this.animatedLayouts.size()) goto L67;
            AnimatedLayout r5 = this.animatedLayouts.get(r43);
            r20.save();
            if (r5.replace == false) goto L56;
            r20.translate(r5.x + r22, ((r12 * r5.progress) + r33) - ((1 - r5.staticLayouts.size()) * r12));
            int r6 = 0;
        L54:
            if (r6 >= r5.staticLayouts.size()) goto L66;
            r20.translate(0.0f, -r12);
            r5.staticLayouts.get(r6).draw(r20);
            r6 = r6 + 1;
        L66:
            r20.restore();
            r43 = r43 + 1;
            goto L49
        L56:
            boolean r62 = r5.direction;
            float r7 = r5.x;
            if (r62 == false) goto L62;
            r20.translate(r7 + r22, (r33 - ((r12 * 10) * r5.progress)) + ((10 - r5.staticLayouts.size()) * r12));
            int r63 = 0;
        L60:
            if (r63 >= r5.staticLayouts.size()) goto L66;
            r20.translate(0.0f, r12);
            r5.staticLayouts.get(r63).draw(r20);
            r63 = r63 + 1;
            goto L60
        L62:
            r20.translate(r7 + r22, (((r12 * 10) * r5.progress) + r33) - ((10 - r5.staticLayouts.size()) * r12));
            int r64 = 0;
        L64:
            if (r64 >= r5.staticLayouts.size()) goto L66;
            r20.translate(0.0f, -r12);
            r5.staticLayouts.get(r64).draw(r20);
            r64 = r64 + 1;
            goto L64
        L67:
            r20.restore();
        L69:
            if (LimitPreviewView.m11979$$Nest$mhasDarkGradientProvider(this.this$0) == false) goto L76;
            r20.restore();
            r20.saveLayer(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.overlayPaint, 31);
            r20.drawRect(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(10.0f), getMeasuredWidth() - AndroidUtilities.dp(12.0f), getMeasuredHeight() - AndroidUtilities.dp(10.0f), LimitPreviewView.m11964$$Nest$fgetdarkGradientProvider(this.this$0).setDarkGradientLocation(getX(), getY()));
            r20.restore();
            return;
        L76:
            return;
        L42:
            if (this.textLayout == null) goto L69;
            r20.save();
            r20.translate(r22, r33);
            this.textLayout.draw(r20);
            r20.restore();
            goto L69
        L11:
            if (this.invalidatePath == false) goto L13;
            this.invalidatePath = false;
            updatePath();
        L13:
            PremiumGradient.getInstance().updateMainGradientMatrix(0, 0, this.this$0.getMeasuredWidth(), this.this$0.getMeasuredHeight(), LimitPreviewView.m11978$$Nest$mgetGlobalXOffset(this.this$0) - getX(), -getTop());
            RectF r34 = AndroidUtilities.rectTmp;
            float r52 = r2;
            r34.set(0.0f, 0.0f, getMeasuredWidth(), r52);
            float r53 = r52 / 2.0f;
            boolean r44 = LimitPreviewView.m11970$$Nest$fgetisRatingStyle(this.this$0);
            LimitPreviewView r65 = this.this$0;
            if (r44 == false) goto L17;
            Paint r45 = LimitPreviewView.m11976$$Nest$fgetratingPaint(r65);
        L20:
            r20.drawRoundRect(r34, r53, r53, r45);
            PremiumGradient.getInstance().getMainGradientPaint().setPathEffect(this.pathEffect);
            if (LimitPreviewView.m11979$$Nest$mhasDarkGradientProvider(this.this$0) == false) goto L23;
            this.textPaint.setPathEffect(this.pathEffect);
        L23:
            Path r35 = this.path;
            boolean r46 = LimitPreviewView.m11970$$Nest$fgetisRatingStyle(this.this$0);
            LimitPreviewView r54 = this.this$0;
            if (r46 == false) goto L27;
            Paint r47 = LimitPreviewView.m11976$$Nest$fgetratingPaint(r54);
        L30:
            r20.drawPath(r35, r47);
            PremiumGradient.getInstance().getMainGradientPaint().setPathEffect(null);
            if (LimitPreviewView.m11979$$Nest$mhasDarkGradientProvider(this.this$0) == false) goto L34;
            this.textPaint.setPathEffect(null);
        L34:
            if (this.this$0.invalidationEnabled == false) goto L9;
            invalidate();
            goto L9
        L27:
            if (LimitPreviewView.m11979$$Nest$mhasDarkGradientProvider(r54) == false) goto L29;
            r47 = this.textPaint;
            goto L30
        L29:
            r47 = PremiumGradient.getInstance().getMainGradientPaint();
            goto L30
        L17:
            if (LimitPreviewView.m11979$$Nest$mhasDarkGradientProvider(r65) == false) goto L19;
            r45 = this.textPaint;
            goto L20
        L19:
            r45 = PremiumGradient.getInstance().getMainGradientPaint();
            goto L20
        L5:
            r4 = Theme.getColor(Theme.key_featuredStickers_buttonText);
            goto L6
        }

        @Override // android.view.View
        public void onMeasure(int r9, int r10) {
            this.textWidth = HintView2.measureCorrectly(this.text, this.textPaint);
            this.textLayout = new StaticLayout(this.text, this.textPaint, ((int) this.textWidth) + AndroidUtilities.dp(12.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.textWidth = 0.0f;
            int r92 = 0;
        L3:
            int r102 = this.textLayout.getLineCount();
            float r0 = this.textWidth;
            if (r92 >= r102) goto L6;
            this.textWidth = Math.max(r0, this.textLayout.getLineWidth(r92));
            r92 = r92 + 1;
            goto L3
        L6:
            setMeasuredDimension((int) ((r0 + getPaddingRight()) + getPaddingLeft()), AndroidUtilities.dp(44.0f) + AndroidUtilities.dp(8.0f));
            updatePath();
        }

        public void setArrowCenter(float r2) {
            if (this.arrowCenter == r2) goto L6;
            this.arrowCenter = r2;
            this.invalidatePath = true;
            invalidate();
            return;
        }

        public void setText(CharSequence r1, boolean r2) {
            if (r2 == true) goto L5;
            this.text = r1;
            return;
        L5:
            CharSequence r22 = this.text;
            this.text = r1;
            createAnimationLayoutsDiff(r22);
        }

        @Override // android.view.View
        public void setTranslationX(float r2) {
            if (r2 == getTranslationX()) goto L6;
            super.setTranslationX(r2);
            invalidate();
            return;
        }
    }

    public interface DarkGradientProvider {
        Paint setDarkGradientLocation(float r1, float r2);
    }

    public class TextViewHolder extends FrameLayout {
        private final boolean isLeft;
        private final Paint paint;
        final /* synthetic */ LimitPreviewView this$0;

        public TextViewHolder(LimitPreviewView r2, Context r3, boolean r4) {
            this.this$0 = r2;
            super(r3);
            Paint r22 = new Paint();
            this.paint = r22;
            setLayerType(2, null);
            r22.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            this.isLeft = r4;
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r18, View r19, long r20) {
            if ((r19 instanceof TextView) == false) goto L24;
            boolean r2 = super.drawChild(r18, r19, r20);
            boolean r4 = false;
            if (LimitPreviewView.m11972$$Nest$fgetpercent(this.this$0) != 0.0f) goto L7;
        L11:
            boolean r3 = false;
        L13:
            if (LimitPreviewView.m11972$$Nest$fgetpercent(this.this$0) == 1.0f) goto L15;
        L17:
            if (r3 == true) goto L20;
            if (r4 == true) goto L20;
        L22:
            return r2;
        L20:
            if (LimitPreviewView.m11979$$Nest$mhasDarkGradientProvider(this.this$0) == false) goto L22;
            r18.saveLayer(r19.getLeft(), r19.getTop(), r19.getRight(), r19.getBottom(), this.paint, 31);
            r18.drawRect(r19.getLeft(), r19.getTop(), r19.getRight(), r19.getBottom(), LimitPreviewView.m11964$$Nest$fgetdarkGradientProvider(this.this$0).setDarkGradientLocation(((ViewGroup) getParent()).getX() + getX(), ((ViewGroup) getParent()).getY() + getY()));
            r18.restore();
            invalidate();
            goto L22
        L15:
            if (this.isLeft == true) goto L17;
            r4 = true;
            goto L17
        L7:
            if (LimitPreviewView.m11972$$Nest$fgetpercent(this.this$0) > 1.0f) goto L11;
            if (this.isLeft == false) goto L11;
            r3 = true;
            goto L13
        L24:
            return super.drawChild(r18, r19, r20);
        }
    }

    public static /* synthetic */ void $r8$lambda$1RxhhhGHW_zy37xrDfTXaHBOGew(LimitPreviewView r0, ValueAnimator r1) {
        r0.lambda$onLayout$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$JjJ8WvXYkLPJkIDpReIPxy2d8aY(LimitPreviewView r0, boolean r1, float r2, float r3, float r4, float r5, boolean r6, float r7, boolean r8, boolean r9, ValueAnimator r10) {
        r0.lambda$onLayout$0(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$ZnqkLBxLJXFI6qdVqE5lE6u49Ls(LimitPreviewView r0, TL_stars.Tl_starsRating r1) {
        r0.lambda$animateStarRating$3(r1);
    }

    /* renamed from: $r8$lambda$koBUKvp600QjA-V15vjidzH9AGs, reason: not valid java name */
    public static /* synthetic */ void m11958$r8$lambda$koBUKvp600QjAV15vjidzH9AGs(LimitPreviewView r0, TL_stars.Tl_starsRating r1) {
        r0.lambda$animateStarRating$2(r1);
    }

    /* renamed from: -$$Nest$fgetanimateArrowFadeIn, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11959$$Nest$fgetanimateArrowFadeIn(LimitPreviewView r0) {
        return r0.animateArrowFadeIn;
    }

    /* renamed from: -$$Nest$fgetanimateArrowFadeOut, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11960$$Nest$fgetanimateArrowFadeOut(LimitPreviewView r0) {
        return r0.animateArrowFadeOut;
    }

    /* renamed from: -$$Nest$fgetanimateStarRatingRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m11961$$Nest$fgetanimateStarRatingRunnable(LimitPreviewView r0) {
        return r0.animateStarRatingRunnable;
    }

    /* renamed from: -$$Nest$fgetarrowAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m11962$$Nest$fgetarrowAnimator(LimitPreviewView r0) {
        return r0.arrowAnimator;
    }

    /* renamed from: -$$Nest$fgetcurrentValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11963$$Nest$fgetcurrentValue(LimitPreviewView r0) {
        return r0.currentValue;
    }

    /* renamed from: -$$Nest$fgetdarkGradientProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DarkGradientProvider m11964$$Nest$fgetdarkGradientProvider(LimitPreviewView r0) {
        return r0.darkGradientProvider;
    }

    /* renamed from: -$$Nest$fgetdefaultLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m11965$$Nest$fgetdefaultLayout(LimitPreviewView r0) {
        return r0.defaultLayout;
    }

    /* renamed from: -$$Nest$fgetdefaultText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView m11966$$Nest$fgetdefaultText(LimitPreviewView r0) {
        return r0.defaultText;
    }

    /* renamed from: -$$Nest$fgetdrawFromRight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11967$$Nest$fgetdrawFromRight(LimitPreviewView r0) {
        return r0.drawFromRight;
    }

    /* renamed from: -$$Nest$fgetisBoostsStyle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11968$$Nest$fgetisBoostsStyle(LimitPreviewView r0) {
        return r0.isBoostsStyle;
    }

    /* renamed from: -$$Nest$fgetisRatingNegative, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11969$$Nest$fgetisRatingNegative(LimitPreviewView r0) {
        return r0.isRatingNegative;
    }

    /* renamed from: -$$Nest$fgetisRatingStyle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11970$$Nest$fgetisRatingStyle(LimitPreviewView r0) {
        return r0.isRatingStyle;
    }

    /* renamed from: -$$Nest$fgetparentVideForGradient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m11971$$Nest$fgetparentVideForGradient(LimitPreviewView r0) {
        return r0.parentVideForGradient;
    }

    /* renamed from: -$$Nest$fgetpercent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11972$$Nest$fgetpercent(LimitPreviewView r0) {
        return r0.percent;
    }

    /* renamed from: -$$Nest$fgetpremiumLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m11973$$Nest$fgetpremiumLayout(LimitPreviewView r0) {
        return r0.premiumLayout;
    }

    /* renamed from: -$$Nest$fgetpremiumLocked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11974$$Nest$fgetpremiumLocked(LimitPreviewView r0) {
        return r0.premiumLocked;
    }

    /* renamed from: -$$Nest$fgetpremiumText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m11975$$Nest$fgetpremiumText(LimitPreviewView r0) {
        return r0.premiumText;
    }

    /* renamed from: -$$Nest$fgetratingPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11976$$Nest$fgetratingPaint(LimitPreviewView r0) {
        return r0.ratingPaint;
    }

    /* renamed from: -$$Nest$fputanimatingRotation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11977$$Nest$fputanimatingRotation(LimitPreviewView r0, boolean r1) {
        r0.animatingRotation = r1;
    }

    /* renamed from: -$$Nest$mgetGlobalXOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11978$$Nest$mgetGlobalXOffset(LimitPreviewView r0) {
        return r0.getGlobalXOffset();
    }

    /* renamed from: -$$Nest$mhasDarkGradientProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11979$$Nest$mhasDarkGradientProvider(LimitPreviewView r0) {
        return r0.hasDarkGradientProvider();
    }

    /* renamed from: -$$Nest$mupdateProgressTextColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11980$$Nest$mupdateProgressTextColors(LimitPreviewView r0) {
        r0.updateProgressTextColors();
    }

    @SuppressLint({"SetTextI18n"})
    public LimitPreviewView(Context r22, int r23, int r24, int r25, float r26, Theme.ResourcesProvider r27) {
        super(r22);
        this.iconScale = 1.0f;
        this.animationCanPlay = true;
        this.ratingPaint = new Paint(1);
        this.invalidationEnabled = true;
        this.resourcesProvider = r27;
        this.percent = MathUtils.clamp(r26, 0.1f, 0.9f);
        this.icon = r23;
        this.currentValue = r24;
        this.premiumLimit = r25;
        setOrientation(1);
        int r7 = 0;
        setClipChildren(false);
        setClipToPadding(false);
        if (r23 == 0) goto L5;
        setPadding(0, AndroidUtilities.dp(16.0f), 0, 0);
        this.limitIcon = new CounterView(this, r22);
        setIconValue(r24, false);
        this.limitIcon.setPadding(AndroidUtilities.dp(19.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(19.0f), AndroidUtilities.dp(14.0f));
        addView(this.limitIcon, LayoutHelper.createLinear(-2, -2, 0.0f, 3));
    L5:
        TextViewHolder r3 = new TextViewHolder(this, r22, true);
        this.defaultLayout = r3;
        AnimatedTextView r5 = new AnimatedTextView(r22);
        this.defaultText = r5;
        r5.setTextSize(AndroidUtilities.dp(14.0f));
        r5.setTypeface(AndroidUtilities.bold());
        r5.setText(LocaleController.getString(R.string.LimitFree));
        r5.setGravity(16);
        int r10 = Theme.key_windowBackgroundWhiteBlackText;
        r5.setTextColor(Theme.getColor(r10, r27));
        TextView r11 = new TextView(r22);
        this.defaultCount = r11;
        r11.setTypeface(AndroidUtilities.bold());
        this.defaultCount.setText(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r25)}));
        this.defaultCount.setGravity(16);
        this.defaultCount.setTextColor(Theme.getColor(r10, r27));
        if (LocaleController.isRTL == false) goto L8;
        r3.addView(r5, LayoutHelper.createFrame(-1, 30.0f, 5, 12.0f, 0.0f, 12.0f, 0.0f));
        r3.addView(this.defaultCount, LayoutHelper.createFrame(-2, 30.0f, 3, 12.0f, 0.0f, 12.0f, 0.0f));
    L9:
        TextViewHolder r52 = new TextViewHolder(this, r22, false);
        this.premiumLayout = r52;
        TextView r102 = new TextView(r22);
        this.premiumText = r102;
        r102.setTypeface(AndroidUtilities.bold());
        r102.setText(LocaleController.getString(R.string.LimitPremium));
        r102.setGravity(16);
        r102.setTextColor(-1);
        AnimatedTextView r112 = new AnimatedTextView(r22);
        this.premiumCount = r112;
        r112.setTextSize(AndroidUtilities.dp(14.0f));
        this.premiumCount.setTypeface(AndroidUtilities.bold());
        this.premiumCount.setText(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r25)}));
        this.premiumCount.setGravity(21);
        this.premiumCount.setTextColor(-1);
        if (LocaleController.isRTL == false) goto L12;
        r52.addView(r102, LayoutHelper.createFrame(-1, 30.0f, 5, 12.0f, 0.0f, 12.0f, 0.0f));
        r52.addView(this.premiumCount, LayoutHelper.createFrame(-2, 30.0f, 3, 12.0f, 0.0f, 12.0f, 0.0f));
    L13:
        AnonymousClass1 r6 = new AnonymousClass1(this, r22, r27);
        this.limitsContainer = r6;
        r6.addView(r3, LayoutHelper.createFrame(-1, 30.0f));
        this.limitsContainer.addView(r52, LayoutHelper.createFrame(-1, 30.0f));
        FrameLayout r1 = this.limitsContainer;
        if (r23 == 0) goto L17;
        r7 = 12;
    L17:
        addView(r1, LayoutHelper.createLinear(-1, 30, 0.0f, 0, 14, r7, 14, 0));
        return;
    L12:
        r52.addView(r102, LayoutHelper.createFrame(-1, 30.0f, 3, 12.0f, 0.0f, 12.0f, 0.0f));
        r52.addView(this.premiumCount, LayoutHelper.createFrame(-2, 30.0f, 5, 12.0f, 0.0f, 12.0f, 0.0f));
        goto L13
    L8:
        r3.addView(r5, LayoutHelper.createFrame(-1, 30.0f, 3, 12.0f, 0.0f, 12.0f, 0.0f));
        r3.addView(this.defaultCount, LayoutHelper.createFrame(-2, 30.0f, 5, 12.0f, 0.0f, 12.0f, 0.0f));
        goto L9
    }

    public LimitPreviewView(Context r8, int r9, int r10, int r11, Theme.ResourcesProvider r12) {
        this(r8, r9, r10, r11, 0.5f, r12);
    }

    private int getEmptyTextColor() {
        if (hasDarkGradientProvider() == false) goto L7;
        return -1;
    L7:
        if (this.isBoostsStyle == true) goto L9;
        int r0 = Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider);
    L17:
        return getTextColorForBackground(r0);
    L9:
        if (this.isStatistic == false) goto L11;
    L14:
        r0 = Theme.getColor(Theme.key_listSelector, this.resourcesProvider);
        goto L17
    L11:
        if (this.isRatingStyle == true) goto L14;
        r0 = Theme.getColor(Theme.key_graySection, this.resourcesProvider);
        goto L17
    }

    private int getFilledTextColor() {
        if (this.isRatingStyle == false) goto L7;
        return getTextColorForBackground(this.ratingPaint.getColor());
    L7:
        return Theme.getColor(Theme.key_featuredStickers_buttonText, this.resourcesProvider);
    }

    private float getGlobalXOffset() {
        return (((-getMeasuredWidth()) * 0.1f) * this.progress) - (getMeasuredWidth() * 0.2f);
    }

    private int getTextColorForBackground(int r2) {
        if (AndroidUtilities.computePerceivedBrightness(r2) > 0.721f) goto L5;
        return -1;
    L5:
        return Theme.getColor(Theme.key_featuredStickers_buttonText, this.resourcesProvider);
    }

    private boolean hasDarkGradientProvider() {
        if (this.darkGradientProvider == null) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$animateStarRating$2(TL_stars.Tl_starsRating r12) {
        this.animateStarRatingRunnable = null;
        if (isAttachedToWindow() == true) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.arrowAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        this.isRatingNegative = false;
        this.ratingPaint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider));
        if (r12.stars > 0) goto L12;
        this.percent = 0.0f;
        this.defaultText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.premiumCount.setText(LocaleController.getString(R.string.StarRatingLevelNegative));
        this.ratingPaint.setColor(Theme.getColor(Theme.key_color_red, this.resourcesProvider));
        this.isRatingNegative = true;
    L15:
        setArrowX(0.0f);
        this.limitIcon.setScaleX(0.6f);
        this.limitIcon.setScaleY(0.6f);
        this.limitIcon.setAlpha(0.0f);
        this.animate = true;
        this.animateArrowFadeIn = true;
        this.animateArrowFadeOut = false;
        this.animateBackgroundFade = false;
        this.animateIncreaseWidth = this.width1;
        this.limitsContainer.requestLayout();
        requestLayout();
        ViewPropertyAnimator r1 = this.defaultText.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(320);
        CubicBezierInterpolator r4 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r1.setInterpolator(r4).start();
        this.premiumCount.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(320).setInterpolator(r4).start();
        updateProgressTextColors();
        setIconValue((int) r12.stars, (int) r12.next_level_stars, true, false);
        return;
    L12:
        if (r12.next_level_stars != 0) goto L14;
        this.percent = 1.0f;
        this.defaultText.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r12.level - 1)}));
        this.premiumCount.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r12.level)}));
        goto L15
    L14:
        long r3 = r12.current_level_stars;
        this.percent = MathUtils.clamp((r1 - r3) / (r9 - r3), 0.0f, 1.0f);
        this.defaultText.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r12.level)}));
        this.premiumCount.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r12.level + 1)}));
        goto L15
    }

    private /* synthetic */ void lambda$animateStarRating$3(TL_stars.Tl_starsRating r12) {
        this.animateStarRatingRunnable = null;
        if (isAttachedToWindow() == true) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.arrowAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        this.isRatingNegative = false;
        this.ratingPaint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider));
        if (r12.stars > 0) goto L12;
        this.percent = 0.5f;
        this.defaultText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.premiumCount.setText(LocaleController.getString(R.string.StarRatingLevelNegative));
        this.ratingPaint.setColor(Theme.getColor(Theme.key_color_red, this.resourcesProvider));
        this.isRatingNegative = true;
    L15:
        setArrowX(1.0f);
        this.limitIcon.setScaleX(0.6f);
        this.limitIcon.setScaleY(0.6f);
        this.limitIcon.setAlpha(0.0f);
        this.animate = true;
        this.animateArrowFadeIn = true;
        this.animateArrowFadeOut = false;
        this.animateBackgroundFade = false;
        this.animateIncreaseWidth = this.width1;
        this.limitsContainer.requestLayout();
        requestLayout();
        ViewPropertyAnimator r1 = this.defaultText.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(320);
        CubicBezierInterpolator r4 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r1.setInterpolator(r4).start();
        this.premiumCount.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(320).setInterpolator(r4).start();
        updateProgressTextColors();
        setIconValue((int) r12.stars, (int) r12.next_level_stars, true, false);
        return;
    L12:
        if (r12.next_level_stars != 0) goto L14;
        this.percent = 1.0f;
        this.defaultText.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r12.level - 1)}));
        this.premiumCount.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r12.level)}));
        goto L15
    L14:
        long r3 = r12.current_level_stars;
        this.percent = MathUtils.clamp((r1 - r3) / (r9 - r3), 0.0f, 1.0f);
        this.defaultText.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r12.level)}));
        this.premiumCount.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r12.level + 1)}));
        goto L15
    }

    private /* synthetic */ void lambda$onLayout$0(boolean r7, float r8, float r9, float r10, float r11, boolean r12, float r13, boolean r14, boolean r15, ValueAnimator r16) {
        float r0 = ((Float) r16.getAnimatedValue()).floatValue();
        float r2 = Math.min(1.0f, r0);
        if (r0 <= 1.0f) goto L11;
        if (r7 == false) goto L11;
        if (this.wasHaptic == true) goto L9;
        this.wasHaptic = true;
        this.limitIcon.performHapticFeedback(3);     // Catch: Exception -> L26
    L9:
        this.limitIcon.setRotation(this.limitIconRotation + ((r0 - 1.0f) * 60.0f));
    L14:
        if (r16 != this.arrowAnimator) goto L16;
        this.limitIcon.setTranslationX(AndroidUtilities.lerp(r8, r9, r2));
        float r72 = AndroidUtilities.lerp(r10, r11, r2);
        this.limitIcon.setArrowCenter(r72);
        this.limitIcon.setPivotX(r8.getMeasuredWidth() * r72);
    L16:
        float r73 = Math.min(1.0f, 2.0f * r2);
        if (r12 == true) goto L19;
        this.limitIcon.setScaleX(r73);
        this.limitIcon.setScaleY(r73);
    L21:
        if (r14 == false) goto L23;
        this.limitIcon.setScaleX(AndroidUtilities.lerp(0.6f, 1.0f, r0));
        this.limitIcon.setScaleY(AndroidUtilities.lerp(0.6f, 1.0f, r0));
        this.limitIcon.setAlpha(r0);
        return;
    L23:
        if (r15 == false) goto L30;
        float r92 = 1.0f - r0;
        this.limitIcon.setScaleX(AndroidUtilities.lerp(0.6f, 1.0f, r92));
        this.limitIcon.setScaleY(AndroidUtilities.lerp(0.6f, 1.0f, r92));
        this.limitIcon.setAlpha(r92);
        return;
    L30:
        return;
    L19:
        this.width1 = (int) AndroidUtilities.lerp(this.animateIncreaseWidth, r13, r2);
        this.limitsContainer.invalidate();
    L11:
        if (this.animatingRotation == true) goto L14;
        this.limitIcon.setRotation(this.limitIconRotation);
        goto L14
    }

    private /* synthetic */ void lambda$onLayout$1(ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        if (r42 >= 0.5f) goto L5;
        float r43 = (r42 / 0.5f) * (-7.0f);
    L6:
        this.limitIconRotation = r43;
        return;
    L5:
        r43 = (1.0f - ((r42 - 0.5f) / 0.5f)) * (-7.0f);
        goto L6
    }

    private void setArrowX(float r7) {
        if (r7 < 1.0f) goto L5;
        int r0 = this.limitsContainer.getMeasuredWidth();
    L6:
        this.width1 = r0;
        float r2 = AndroidUtilities.dp(14.0f);
        this.limitIcon.setTranslationX(Utilities.clamp((Math.max(this.width1, (getMeasuredWidth() - (r0 * 2)) * r7) + r2) - (this.limitIcon.getMeasuredWidth() / 2.0f), (getMeasuredWidth() - r0) - this.limitIcon.getMeasuredWidth(), r2));
        this.limitIcon.setArrowCenter(r7);
        this.limitIcon.setPivotX(r6.getMeasuredWidth() * r7);
        return;
    L5:
        r0 = 0;
        goto L6
    }

    private void updateProgressTextColors() {
        if (this.defaultText != null) goto L5;
        return;
    L5:
        if (this.premiumCount == null) goto L29;
        int r0 = getFilledTextColor();
        int r1 = getEmptyTextColor();
        float r2 = this.percent;
        if (r2 > 0.0f) goto L17;
        AnimatedTextView r22 = this.premiumCount;
        if (this.isRatingNegative == false) goto L12;
    L15:
        r22.setTextColor(r0);
        this.defaultText.setTextColor(r1);
        return;
    L12:
        if (this.drawFromRight == true) goto L15;
        r0 = r1;
        goto L15
    L17:
        AnimatedTextView r3 = this.premiumCount;
        if (r2 < 1.0f) goto L20;
        r3.setTextColor(r0);
        this.defaultText.setTextColor(r0);
        return;
    L20:
        if (this.isRatingNegative == false) goto L22;
    L23:
        r1 = r0;
    L24:
        r3.setTextColor(r1);
        this.defaultText.setTextColor(r0);
        return;
    L22:
        if (this.drawFromRight == false) goto L24;
    }

    public void animateStarRating(TL_stars.Tl_starsRating r19, TL_stars.Tl_starsRating r20) {
        AndroidUtilities.cancelRunOnUIThread(this.animateStarRatingRunnable);
        this.animateStarRatingRunnable = null;
        Paint r3 = this.ratingPaint;
        int r4 = Theme.key_featuredStickers_addButton;
        r3.setColor(Theme.getColor(r4, this.resourcesProvider));
        this.isRatingNegative = false;
        int r5 = r19.level;
        int r6 = r20.level;
        if (r5 != r6) goto L14;
        if (r20.stars > 0) goto L8;
        this.percent = 0.0f;
        this.defaultText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.premiumCount.setText(LocaleController.getString(R.string.StarRatingLevelNegative));
        this.ratingPaint.setColor(Theme.getColor(Theme.key_color_red, this.resourcesProvider));
        this.isRatingNegative = true;
    L11:
        this.animate = true;
        this.animateArrowFadeIn = false;
        this.animateArrowFadeOut = false;
        this.animateBackgroundFade = false;
        this.animateIncreaseWidth = this.width1;
        this.limitsContainer.requestLayout();
        requestLayout();
        updateProgressTextColors();
        setIconValue((int) r20.stars, (int) r20.next_level_stars, true, false);
        return;
    L8:
        if (r20.next_level_stars != 0) goto L10;
        this.percent = 1.0f;
        this.defaultText.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r6 - 1)}));
        this.premiumCount.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r20.level)}));
        goto L11
    L10:
        long r9 = r20.current_level_stars;
        this.percent = MathUtils.clamp((r4 - r9) / (r12 - r9), 0.0f, 1.0f);
        this.defaultText.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r20.level)}));
        this.premiumCount.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r20.level + 1)}));
        goto L11
    L14:
        if (r6 <= r5) goto L31;
        long r42 = r19.stars;
        if (r42 > 0) goto L18;
        this.isRatingNegative = true;
    L18:
        this.percent = 1.0f;
        this.animate = true;
        this.animateArrowFadeIn = false;
        this.animateArrowFadeOut = true;
        if (r42 > 0) goto L21;
        boolean r43 = true;
    L23:
        if (r20.stars > 0) goto L25;
        boolean r52 = true;
    L26:
        if (r43 != r52) goto L28;
        boolean r44 = true;
    L29:
        this.animateBackgroundFade = r44;
        this.animateIncreaseWidth = this.width1;
        this.limitsContainer.requestLayout();
        requestLayout();
        updateProgressTextColors();
        ViewPropertyAnimator r45 = this.defaultText.animate().alpha(0.0f).scaleX(0.7f).scaleY(0.7f).setDuration(320);
        CubicBezierInterpolator r53 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r45.setInterpolator(r53).start();
        this.premiumCount.animate().alpha(0.0f).scaleX(0.7f).scaleY(0.7f).setDuration(320).setInterpolator(r53).start();
        setIconValue((int) r19.stars, (int) r19.next_level_stars, true, false);
        LimitPreviewView$$ExternalSyntheticLambda2 r1 = new LimitPreviewView$$ExternalSyntheticLambda2(this, r20);
        this.animateStarRatingRunnable = r1;
        AndroidUtilities.runOnUIThread(r1, 600);
        return;
    L28:
        r44 = false;
        goto L29
    L25:
        r52 = false;
        goto L26
    L21:
        r43 = false;
        goto L23
    L31:
        if (r6 >= r5) goto L48;
        this.ratingPaint.setColor(Theme.getColor(r4, this.resourcesProvider));
        this.isRatingNegative = false;
        long r46 = r19.stars;
        if (r46 > 0) goto L35;
        this.isRatingNegative = true;
    L35:
        this.percent = 0.0f;
        this.animate = true;
        this.animateArrowFadeIn = false;
        this.animateArrowFadeOut = true;
        if (r46 > 0) goto L38;
        boolean r47 = true;
    L40:
        if (r20.stars > 0) goto L42;
        boolean r54 = true;
    L43:
        if (r47 != r54) goto L45;
        boolean r48 = true;
    L46:
        this.animateBackgroundFade = r48;
        this.animateIncreaseWidth = this.width1;
        this.limitsContainer.requestLayout();
        requestLayout();
        ViewPropertyAnimator r49 = this.defaultText.animate().alpha(0.0f).scaleX(0.7f).scaleY(0.7f).setDuration(320);
        CubicBezierInterpolator r55 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r49.setInterpolator(r55).start();
        this.premiumCount.animate().alpha(0.0f).scaleX(0.7f).scaleY(0.7f).setDuration(320).setInterpolator(r55).start();
        updateProgressTextColors();
        setIconValue((int) r19.stars, (int) r19.next_level_stars, true, false);
        LimitPreviewView$$ExternalSyntheticLambda3 r12 = new LimitPreviewView$$ExternalSyntheticLambda3(this, r20);
        this.animateStarRatingRunnable = r12;
        AndroidUtilities.runOnUIThread(r12, 600);
        return;
    L45:
        r48 = false;
        goto L46
    L42:
        r54 = false;
        goto L43
    L38:
        r47 = false;
        goto L40
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r4) {
        if (this.staticGradient != null) goto L13;
        boolean r0 = this.inc;
        float r1 = this.progress;
        if (r0 == false) goto L9;
        float r12 = r1 + 0.016f;
        this.progress = r12;
        if (r12 <= 3.0f) goto L12;
        this.inc = false;
    L12:
        invalidate();
        goto L13
    L9:
        float r13 = r1 - 0.016f;
        this.progress = r13;
        if (r13 >= 1.0f) goto L12;
        this.inc = true;
    L13:
        super.dispatchDraw(r4);
    }

    public void increaseCurrentValue(int r3, int r4, int r5) {
        this.currentValue++;
        this.percent = MathUtils.clamp(r4 / r5, 0.0f, 1.0f);
        this.animateIncrease = true;
        this.animateIncreaseWidth = this.width1;
        setIconValue(r3, true);
        this.limitsContainer.requestLayout();
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r18, int r19, int r20, int r21, int r22) {
        super.onLayout(r18, r19, r20, r21, r22);
        float r4 = 0.5f;
        if (this.animateIncrease == false) goto L5;
    L40:
        int r0 = AndroidUtilities.dp(14.0f);
        if (this.animate == false) goto L43;
    L46:
        boolean r2 = true;
    L47:
        this.animateIncrease = false;
        this.animate = false;
        if (r2 == false) goto L50;
        float r7 = this.limitIcon.getTranslationX();
    L51:
        float r8 = r0;
        int r15 = r0 * 2;
        float r3 = (Math.max(this.width1, (getMeasuredWidth() - r15) * this.position) + r8) - (this.limitIcon.getMeasuredWidth() / 2.0f);
        if (this.isSimpleStyle == false) goto L62;
        float r42 = this.limitIcon.getArrowCenter();
        float r02 = Utilities.clamp(r3, (getMeasuredWidth() - r0) - this.limitIcon.getMeasuredWidth(), r8);
        int r32 = this.width1;
        if (r32 > 0) goto L57;
        float r43 = r02;
        float r03 = r42;
        float r33 = 0.0f;
    L69:
        boolean r9 = this.animateArrowFadeIn;
        boolean r10 = this.animateArrowFadeOut;
        if (r9 == true) goto L73;
        if (r10 == true) goto L73;
        this.limitIcon.setAlpha(1.0f);
    L73:
        this.limitIcon.setTranslationX(r7);
        this.limitIcon.setPivotX(r6.getMeasuredWidth() / 2.0f);
        this.limitIcon.setPivotY(r6.getMeasuredHeight());
        if (r2 == true) goto L76;
        this.limitIcon.setScaleX(0.0f);
        this.limitIcon.setScaleY(0.0f);
        this.limitIcon.createAnimationLayouts();
    L76:
        ValueAnimator r152 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.arrowAnimator = r152;
        float r82 = this.width1;
        if (r2 == false) goto L79;
        this.width1 = this.animateIncreaseWidth;
    L79:
        boolean r5 = !this.animatingRotation;
        this.animatingRotation = true;
        float r52 = r03;
        r152.addUpdateListener(new LimitPreviewView$$ExternalSyntheticLambda0(this, r5, r7, r43, r52, r33, r2, r82, r9, r10));
        this.arrowAnimator.addListener(new AnonymousClass2(this, r5));
        this.arrowAnimator.setInterpolator(new OvershootInterpolator());
        if (this.animateIncrease == false) goto L82;
        ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r04.addUpdateListener(new LimitPreviewView$$ExternalSyntheticLambda1(this));
        r04.setDuration(500);
        r04.start();
        this.arrowAnimator.setDuration(600);
    L88:
        this.arrowAnimator.start();
        this.wasAnimation = true;
        return;
    L82:
        if (r10 == false) goto L84;
        this.arrowAnimator.setInterpolator(CubicBezierInterpolator.EASE_IN);
        this.arrowAnimator.setDuration(320);
        goto L88
    L84:
        ValueAnimator r05 = this.arrowAnimator;
        if (r9 == false) goto L87;
        r05.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.arrowAnimator.setDuration(500);
        goto L88
    L87:
        r05.setDuration(1000);
        this.arrowAnimator.setStartDelay(200);
        goto L88
    L57:
        if (r32 < (getMeasuredWidth() - r15)) goto L60;
        float r34 = r42;
    L59:
        r43 = r02;
        r03 = r34;
        r33 = 1.0f;
        goto L69
    L60:
        r33 = Utilities.clamp((this.width1 - (r02 - r8)) / this.limitIcon.getMeasuredWidth(), 1.0f, 0.0f);
        r43 = r02;
        r03 = r42;
        goto L69
    L62:
        if (r3 >= r8) goto L64;
        r34 = 0.0f;
        r4 = 0.0f;
    L66:
        if (r8 <= ((getMeasuredWidth() - r0) - this.limitIcon.getMeasuredWidth())) goto L68;
        r02 = (getMeasuredWidth() - r0) - this.limitIcon.getMeasuredWidth();
        goto L59
    L68:
        r03 = r34;
        r33 = r4;
        r43 = r8;
        goto L69
    L64:
        r8 = r3;
        r34 = 0.5f;
        goto L66
    L50:
        r7 = 0.0f;
        goto L51
    L43:
        if (this.animateIncrease == true) goto L46;
        r2 = false;
        goto L47
    L5:
        if (this.animate == true) goto L40;
        if (this.wasAnimation == true) goto L16;
        if (this.limitIcon == null) goto L16;
        if (this.animationCanPlay == false) goto L16;
        if (this.premiumLocked == false) goto L40;
    L16:
        if (this.isBoostsStyle == false) goto L24;
        if (this.animateArrowFadeIn == false) goto L20;
        return;
    L20:
        if (this.animateArrowFadeOut == true) goto L91;
        this.limitIcon.setAlpha(1.0f);
        this.limitIcon.setScaleX(1.0f);
        this.limitIcon.setScaleY(1.0f);
        return;
    L91:
        return;
    L24:
        if (this.premiumLocked == false) goto L36;
        float r23 = (AndroidUtilities.dp(14.0f) + ((getMeasuredWidth() - (r0 * 2)) * 0.5f)) - (this.limitIcon.getMeasuredWidth() / 2.0f);
        boolean r06 = this.wasAnimation;
        if (r06 == false) goto L28;
    L30:
        CounterView r35 = this.limitIcon;
        if (r06 == true) goto L33;
        r35.setAlpha(0.0f);
        this.limitIcon.setScaleX(0.0f);
        this.limitIcon.setScaleY(0.0f);
    L34:
        this.limitIcon.setTranslationX(r23);
        return;
    L33:
        r35.setAlpha(1.0f);
        this.limitIcon.setScaleX(1.0f);
        this.limitIcon.setScaleY(1.0f);
        goto L34
    L28:
        if (this.animationCanPlay == false) goto L30;
        this.wasAnimation = true;
        this.limitIcon.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(200).setInterpolator(new OvershootInterpolator()).start();
        goto L34
    L36:
        CounterView r07 = this.limitIcon;
        if (r07 == null) goto L92;
        r07.setAlpha(0.0f);
        return;
    }

    public void setBagePosition(float r3) {
        this.position = MathUtils.clamp(r3, 0.1f, 0.9f);
    }

    public void setBoosts(TL_stories.TL_premium_boostsStatus r6, boolean r7) {
        this.isBoostsStyle = true;
        if (r6.current_level_boosts != r6.boosts) goto L6;
        if (r7 == false) goto L6;
    L7:
        this.percent = 1.0f;
        this.defaultText.setText(LocaleController.formatString("BoostsLevel", R.string.BoostsLevel, new Object[]{Integer.valueOf(r6.level - 1)}));
        this.premiumCount.setText(LocaleController.formatString("BoostsLevel", R.string.BoostsLevel, new Object[]{Integer.valueOf(r6.level)}));
    L9:
        ((FrameLayout.LayoutParams) this.premiumCount.getLayoutParams()).gravity = 5;
        setType(17);
        this.defaultCount.setVisibility(8);
        this.premiumText.setVisibility(8);
        updateProgressTextColors();
        setIconValue(r6.boosts, false);
        return;
    L6:
        if (r6.next_level_boosts == 0) goto L7;
        this.percent = MathUtils.clamp((r2 - r1) / (r7 - r1), 0.0f, 1.0f);
        this.defaultText.setText(LocaleController.formatString("BoostsLevel", R.string.BoostsLevel, new Object[]{Integer.valueOf(r6.level)}));
        this.premiumCount.setText(LocaleController.formatString("BoostsLevel", R.string.BoostsLevel, new Object[]{Integer.valueOf(r6.level + 1)}));
        goto L9
    }

    public void setDarkGradientProvider(DarkGradientProvider r1) {
        this.darkGradientProvider = r1;
    }

    public void setDelayedAnimation() {
        this.animationCanPlay = false;
    }

    public void setHideNegativeValues(boolean r1) {
        this.hideNegativeValues = r1;
    }

    public void setIconScale(float r1) {
        this.iconScale = r1;
    }

    public void setIconValue(int r7, int r8, boolean r9, boolean r10) {
        if (r7 >= 0) goto L5;
        setIconValue(r7, r10);
        return;
    L5:
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        r0.append("d").setSpan(new ColoredImageSpan(this.icon), 0, 1, 0);
        r0.append(" ").setSpan(new RelativeSizeSpan(0.8f), 1, 2, 0);
        if (r9 == false) goto L9;
        if (r7 <= 1200) goto L9;
        String r72 = LocaleController.formatShortNumber(r7, null);
    L10:
        r0.append(r72);
        int r73 = r0.length();
        r0.append("\u200a/\u200a");
        if (r9 == false) goto L14;
        if (r8 <= 1200) goto L14;
        String r82 = LocaleController.formatShortNumber(r8, null);
    L15:
        r0.append(r82);
        r0.setSpan(new EllipsizeSpanAnimator.TextAlphaSpan(170), r73, r0.length(), 33);
        r0.setSpan(new RelativeSizeSpan(0.65f), r73, r0.length(), 33);
        this.limitIcon.setText(r0, r10);
        this.limitIcon.requestLayout();
        return;
    L14:
        r82 = LocaleController.formatNumber(r8, ',');
    L9:
        r72 = LocaleController.formatNumber(r7, ',');
        goto L10
    }

    public void setIconValue(int r7, boolean r8) {
        if (r7 >= 0) goto L4;
        ColoredImageSpan r0 = new ColoredImageSpan(R.drawable.warning_sign);
    L5:
        SpannableStringBuilder r1 = new SpannableStringBuilder();
        r1.append("d").setSpan(r0, 0, 1, 0);
        if (r7 < 0) goto L8;
    L9:
        r1.append(" ").setSpan(new RelativeSizeSpan(0.8f), 1, 2, 0);
        r1.append(LocaleController.formatNumber(r7, ','));
    L10:
        this.limitIcon.setText(r1, r8);
        this.limitIcon.requestLayout();
        return;
    L8:
        if (this.hideNegativeValues == true) goto L10;
    L4:
        r0 = new ColoredImageSpan(this.icon);
        float r12 = this.iconScale;
        r0.setScale(r12, r12);
        goto L5
    }

    public void setParentViewForGradien(ViewGroup r1) {
        this.parentVideForGradient = r1;
    }

    public void setPremiumLocked() {
        this.limitsContainer.setVisibility(8);
        CounterView r0 = this.limitIcon;
        if (r0 == null) goto L5;
        r0.setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(3.0f));
    L5:
        this.premiumLocked = true;
    }

    public void setStarRating(TL_stars.Tl_starsRating r11) {
        this.isRatingNegative = false;
        this.isBoostsStyle = true;
        this.isSimpleStyle = true;
        this.isRatingStyle = true;
        this.ratingPaint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider));
        long r2 = r11.current_level_stars;
        if (r11.stars > 0) goto L6;
        this.percent = 0.5f;
        this.defaultText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.premiumCount.setText(LocaleController.getString(R.string.StarRatingLevelNegative));
        this.ratingPaint.setColor(Theme.getColor(Theme.key_color_red, this.resourcesProvider));
        this.isRatingNegative = true;
    L9:
        ((FrameLayout.LayoutParams) this.premiumCount.getLayoutParams()).gravity = 5;
        setType(17);
        this.defaultCount.setVisibility(8);
        this.premiumText.setVisibility(8);
        updateProgressTextColors();
        setIconValue((int) r11.stars, (int) r11.next_level_stars, true, false);
        return;
    L6:
        if (r11.next_level_stars != 0) goto L8;
        this.percent = 1.0f;
        this.defaultText.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r11.level - 1)}));
        this.premiumCount.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r11.level)}));
        goto L9
    L8:
        this.percent = MathUtils.clamp((r4 - r2) / (r8 - r2), 0.0f, 1.0f);
        this.defaultText.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r11.level)}));
        this.premiumCount.setText(LocaleController.formatString(R.string.StarRatingLevel, new Object[]{Integer.valueOf(r11.level + 1)}));
        goto L9
    }

    public void setStarsUpgradePrice(TL_stars.StarGiftUpgradePrice r5, long r6, TL_stars.StarGiftUpgradePrice r8) {
        this.drawFromRight = true;
        this.isBoostsStyle = true;
        this.isSimpleStyle = true;
        this.isRatingStyle = true;
        this.ratingPaint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider));
        this.percent = AndroidUtilities.ilerp(r6, r5.upgrade_stars, r8.upgrade_stars);
        this.defaultText.setText(LocaleController.formatPluralStringComma("Stars", (int) r5.upgrade_stars));
        this.premiumCount.setText(LocaleController.formatPluralStringComma("Stars", (int) r8.upgrade_stars));
        ((FrameLayout.LayoutParams) this.premiumCount.getLayoutParams()).gravity = 5;
        setType(17);
        this.defaultCount.setVisibility(8);
        this.premiumText.setVisibility(8);
        updateProgressTextColors();
        setIconValue((int) r6, false);
    }

    public void setStaticGradinet(PremiumGradient.PremiumGradientTools r1) {
        this.staticGradient = r1;
    }

    @Keep
    public void setStatus(int r5, int r6, boolean r7) {
        if (this.currentValue != r5) goto L5;
        r7 = false;
    L5:
        this.currentValue = r5;
        this.percent = MathUtils.clamp(r5 / r6, 0.0f, 1.0f);
        if (r7 == false) goto L8;
        this.animateIncrease = true;
        this.animateIncreaseWidth = this.width1;
        this.limitsContainer.requestLayout();
        requestLayout();
    L8:
        ((FrameLayout.LayoutParams) this.premiumCount.getLayoutParams()).gravity = 5;
        this.defaultCount.setVisibility(8);
        this.premiumText.setVisibility(8);
        this.defaultText.setText(MVEL.VERSION_SUB);
        this.premiumCount.setText(_UrlKt.FRAGMENT_ENCODE_SET + r6);
        setIconValue(r5, false);
        this.isBoostsStyle = true;
        this.isSimpleStyle = true;
    }

    public void setType(int r7) {
        if (r7 != 6) goto L14;
        if (this.limitIcon == null) goto L11;
        SpannableStringBuilder r72 = new SpannableStringBuilder();
        r72.append("d ").setSpan(new ColoredImageSpan(this.icon), 0, 1, 0);
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == false) goto L9;
        String r1 = "4 GB";
    L10:
        r72.append(r1);
        this.limitIcon.setText(r72, false);
        goto L11
    L9:
        r1 = "2 GB";
    L11:
        this.premiumCount.setText("4 GB");
        return;
    L14:
        if (r7 == 11) goto L16;
        return;
    L16:
        if (this.limitIcon == null) goto L18;
        SpannableStringBuilder r73 = new SpannableStringBuilder();
        r73.append("d").setSpan(new ColoredImageSpan(this.icon), 0, 1, 0);
        this.limitIcon.setText(r73, false);
    L18:
        this.premiumCount.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    public void startDelayedAnimation() {
        this.animationCanPlay = true;
        requestLayout();
    }
}
