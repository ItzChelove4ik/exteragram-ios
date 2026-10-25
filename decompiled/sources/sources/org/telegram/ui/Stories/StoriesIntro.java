package org.telegram.ui.Stories;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class StoriesIntro extends FrameLayout {
    private int current;
    private final ArrayList<StoriesIntroItemView> items;
    private int prev;
    private final Runnable startItemAnimationRunnable;
    private ValueAnimator valueAnimator;

    /* renamed from: org.telegram.ui.Stories.StoriesIntro$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ StoriesIntro this$0;
        final /* synthetic */ TextView val$bottomText;
        final /* synthetic */ View val$parentView;
        final /* synthetic */ TextView val$subHeader;

        public AnonymousClass1(StoriesIntro r1, TextView r2, View r3, TextView r4) {
            this.this$0 = r1;
            this.val$bottomText = r2;
            this.val$parentView = r3;
            this.val$subHeader = r4;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            int[] r0 = new int[2];
            this.val$bottomText.getLocationOnScreen(r0);
            if ((r0[1] + AndroidUtilities.dp(24.0f)) <= this.val$parentView.getMeasuredHeight()) goto L5;
            this.val$bottomText.setLayoutParams(LayoutHelper.createLinear(-2, -2, 0.0f, 13.0f, 0.0f, 0.0f));
            this.val$subHeader.setLayoutParams(LayoutHelper.createLinear(-2, -2, 68.0f, 8.0f, 68.0f, 13.0f));
            this.this$0.requestLayout();
        L5:
            this.this$0.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoriesIntro$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ StoriesIntro this$0;

        public AnonymousClass2(StoriesIntro r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            super.onAnimationStart(r1);
            ((StoriesIntroItemView) StoriesIntro.m19721$$Nest$fgetitems(this.this$0).get(StoriesIntro.m19720$$Nest$fgetcurrent(this.this$0))).startIconAnimation();
        }
    }

    @SuppressLint({"ViewConstructor"})
    public static class StoriesIntroItemView extends View {
        private final Paint backgroundPaint;
        private final String header;
        private final TextPaint headerTextPaint;
        private final RLottieDrawable lottieDrawable;
        private float progress;
        private final RectF rectF;
        private final String subHeader;
        private final TextPaint subHeaderTextPaint;
        private final Rect textBounds;

        public StoriesIntroItemView(Context r7, int r8, String r9, String r10) {
            super(r7);
            this.textBounds = new Rect();
            this.header = r9;
            this.subHeader = r10;
            RLottieDrawable r0 = new RLottieDrawable(r8, AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), true, null);
            this.lottieDrawable = r0;
            r0.setAutoRepeat(1);
            r0.setMasterParent(this);
            Paint r82 = new Paint(1);
            this.backgroundPaint = r82;
            r82.setColor(383310040);
            TextPaint r83 = new TextPaint(1);
            this.headerTextPaint = r83;
            r83.setColor(-1);
            r83.setTextSize(TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
            r83.setTypeface(AndroidUtilities.bold());
            TextPaint r84 = new TextPaint(1);
            this.subHeaderTextPaint = r84;
            r84.setColor(-1761607681);
            r84.setTextSize(TypedValue.applyDimension(1, 14.0f, getResources().getDisplayMetrics()));
            this.rectF = new RectF();
        }

        public long getLottieAnimationDuration() {
            return this.lottieDrawable.getDuration() * 2;
        }

        public int getRequiredWidth() {
            TextPaint r0 = this.headerTextPaint;
            String r1 = this.header;
            r0.getTextBounds(r1, 0, r1.length(), this.textBounds);
            int r02 = this.textBounds.width();
            TextPaint r12 = this.subHeaderTextPaint;
            String r2 = this.subHeader;
            r12.getTextBounds(r2, 0, r2.length(), this.textBounds);
            int r6 = this.textBounds.width();
            return (AndroidUtilities.dp(88.0f) + AndroidUtilities.dp(8.0f)) + Math.max(r02, r6);
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            super.onDraw(r10);
            int r0 = AndroidUtilities.dp(40.0f);
            int r1 = getMeasuredHeight() / 2;
            int r2 = (int) (AndroidUtilities.dp(36.0f) + (AndroidUtilities.dp(8.0f) * this.progress));
            int r3 = r2 / 2;
            int r02 = r0 - r3;
            int r12 = r1 - r3;
            this.lottieDrawable.setBounds(r02, r12, r02 + r2, r2 + r12);
            this.lottieDrawable.draw(r10);
            if (this.progress <= 0.0f) goto L5;
            float r03 = AndroidUtilities.dpf2(4.0f) * (1.0f - this.progress);
            float r7 = r03 * 2.0f;
            this.rectF.set(r03, r03, getMeasuredWidth() - r7, getMeasuredHeight() - r7);
            this.backgroundPaint.setAlpha((int) (this.progress * 30.0f));
            r10.drawRoundRect(this.rectF, AndroidUtilities.dpf2(12.0f), AndroidUtilities.dpf2(12.0f), this.backgroundPaint);
            r10.save();
            float r04 = this.progress;
            r10.scale((r04 * 0.05f) + 1.0f, (r04 * 0.05f) + 1.0f, getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f);
        L5:
            r10.drawText(this.header, AndroidUtilities.dpf2(80.0f), (getMeasuredHeight() / 2.0f) - AndroidUtilities.dpf2(4.0f), this.headerTextPaint);
            r10.drawText(this.subHeader, AndroidUtilities.dpf2(80.0f), (getMeasuredHeight() / 2.0f) + AndroidUtilities.dpf2(18.0f), this.subHeaderTextPaint);
            if (this.progress <= 0.0f) goto L9;
            r10.restore();
            return;
        }

        @Override // android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, r4);
            int r32 = AndroidUtilities.dp(40.0f);
            int r42 = getMeasuredHeight() / 2;
            int r0 = AndroidUtilities.dp(36.0f);
            int r1 = r0 / 2;
            int r33 = r32 - r1;
            int r43 = r42 - r1;
            RLottieDrawable r2 = this.lottieDrawable;
            r2.setBounds(r33, r43, r33 + r0, r0 + r43);
        }

        public void setProgress(float r1) {
            this.progress = r1;
            invalidate();
        }

        public void startIconAnimation() {
            this.lottieDrawable.setAutoRepeatCount(2);
            this.lottieDrawable.start();
        }

        public void stopAnimation() {
            this.lottieDrawable.setCurrentFrame(0);
            this.lottieDrawable.stop();
            this.progress = 0.0f;
            invalidate();
        }
    }

    public static /* synthetic */ void $r8$lambda$CgZaFbGKsxnAno0i3oY9vmZ0m7g(StoriesIntro r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$MSh3c4BPOVya_7_ULQCvqwahpFg(StoriesIntro r0, ValueAnimator r1) {
        r0.lambda$startAnimation$1(r1);
    }

    /* renamed from: -$$Nest$fgetcurrent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19720$$Nest$fgetcurrent(StoriesIntro r0) {
        return r0.current;
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m19721$$Nest$fgetitems(StoriesIntro r0) {
        return r0.items;
    }

    public StoriesIntro(Context r18, View r19) {
        super(r18);
        this.prev = -1;
        int r4 = 0;
        this.current = 0;
        this.startItemAnimationRunnable = new StoriesIntro$$ExternalSyntheticLambda1(this);
        ImageView r5 = new ImageView(r18);
        r5.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(r5, -1, -1);
        View r6 = new View(r18);
        r6.setBackgroundColor(1677721600);
        addView(r6, -1, -1);
        LinearLayout r62 = new LinearLayout(r18);
        r62.setOrientation(1);
        r62.setPadding(0, AndroidUtilities.dp(48.0f), 0, AndroidUtilities.dp(48.0f));
        r62.setGravity(1);
        TextView r8 = new TextView(r18);
        r8.setTextColor(-1);
        r8.setTypeface(AndroidUtilities.bold());
        r8.setText(LocaleController.getString(R.string.StoriesIntroHeader));
        r8.setTextSize(1, 20.0f);
        r62.addView(r8, LayoutHelper.createLinear(-2, -2));
        TextView r82 = new TextView(r18);
        r82.setTextColor(-1761607681);
        r82.setText(LocaleController.getString(R.string.StoriesIntroSubHeader));
        r82.setTextSize(1, 14.0f);
        r82.setGravity(1);
        r62.addView(r82, LayoutHelper.createLinear(-2, -2, 68.0f, 8.0f, 68.0f, 36.0f));
        ArrayList<StoriesIntroItemView> r11 = new ArrayList(4);
        this.items = r11;
        r11.add(new StoriesIntroItemView(r18, R.raw.stories_intro_go_forward, LocaleController.getString(R.string.StoriesIntroGoForwardHeader), LocaleController.getString(R.string.StoriesIntroGoForwardSubHeader)));
        r11.add(new StoriesIntroItemView(r18, R.raw.stories_intro_pause, LocaleController.getString(R.string.StoriesIntroPauseAndSeekHeader), LocaleController.getString(R.string.StoriesIntroPauseAndSeekSubHeader)));
        r11.add(new StoriesIntroItemView(r18, R.raw.stories_intro_go_back, LocaleController.getString(R.string.StoriesIntroGoBackHeader), LocaleController.getString(R.string.StoriesIntroGoBackSubHeader)));
        r11.add(new StoriesIntroItemView(r18, R.raw.stories_intro_go_to_next, LocaleController.getString(R.string.StoriesIntroGoToNextAuthorHeader), LocaleController.getString(R.string.StoriesIntroGoToNextAuthorSubHeader)));
        int r12 = r19.getMeasuredWidth() - AndroidUtilities.dp(100.0f);
        int r13 = r11.size();
        int r14 = 0;
    L3:
        if (r14 >= r13) goto L8;
        StoriesIntroItemView r15 = r11.get(r14);
        r14 = r14 + 1;
        int r152 = r15.getRequiredWidth();
        if (r152 <= r12) goto L3;
        r12 = r152;
        goto L3
    L8:
        if ((AndroidUtilities.dp(8.0f) + r12) <= r19.getMeasuredWidth()) goto L10;
        r12 = r19.getMeasuredWidth() - AndroidUtilities.dp(8.0f);
    L10:
        LinearLayout.LayoutParams r112 = new LinearLayout.LayoutParams(r12, AndroidUtilities.dp(64.0f));
        r112.setMargins(0, AndroidUtilities.dp(5.0f), 0, AndroidUtilities.dp(5.0f));
        ArrayList<StoriesIntroItemView> r122 = this.items;
        int r132 = r122.size();
    L11:
        if (r4 >= r132) goto L13;
        StoriesIntroItemView r142 = r122.get(r4);
        r4 = r4 + 1;
        r62.addView(r142, r112);
        goto L11
    L13:
        TextView r42 = new TextView(r18);
        r42.setTextColor(-1);
        r42.setTypeface(AndroidUtilities.bold());
        r42.setText(LocaleController.getString(R.string.StoriesIntroDismiss));
        r42.setTextSize(1, 14.0f);
        r62.addView(r42, LayoutHelper.createLinear(-2, -2, 0.0f, 73.0f, 0.0f, 0.0f));
        addView(r62, LayoutHelper.createFrame(-1, -2, 17));
        BitmapDrawable r1 = new BitmapDrawable(getContext().getResources(), AndroidUtilities.makeBlurBitmap(r19, 12.0f, 10));
        r1.setColorFilter(new PorterDuffColorFilter(-587202560, PorterDuff.Mode.DST_OVER));
        r5.setImageDrawable(r1);
        getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass1(this, r42, r19, r82));
    }

    private /* synthetic */ void lambda$new$0() {
        updateCurrentAnimatedItem();
        startAnimation(true);
    }

    private /* synthetic */ void lambda$startAnimation$1(ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.items.get(this.current).setProgress(r32);
        int r0 = this.prev;
        if (r0 == (-1)) goto L6;
        this.items.get(r0).setProgress(1.0f - r32);
        return;
    }

    private void updateCurrentAnimatedItem() {
        int r0 = this.current + 1;
        this.current = r0;
        if (r0 < this.items.size()) goto L5;
        this.current = 0;
    L5:
        int r02 = this.prev + 1;
        this.prev = r02;
        if (r02 < this.items.size()) goto L9;
        this.prev = 0;
        return;
    }

    public void startAnimation(boolean r5) {
        ValueAnimator r0 = this.valueAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.valueAnimator = r02;
        if (r5 == false) goto L8;
        r02.setStartDelay(50);
    L8:
        this.valueAnimator.setDuration(350);
        this.valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        this.valueAnimator.getCurrentPlayTime();
        this.valueAnimator.addListener(new AnonymousClass2(this));
        this.valueAnimator.addUpdateListener(new StoriesIntro$$ExternalSyntheticLambda0(this));
        this.valueAnimator.start();
        AndroidUtilities.runOnUIThread(this.startItemAnimationRunnable, this.items.get(this.current).getLottieAnimationDuration() + 100);
    }

    public void stopAnimation() {
        AndroidUtilities.cancelRunOnUIThread(this.startItemAnimationRunnable);
        ValueAnimator r0 = this.valueAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.valueAnimator = null;
    L5:
        int r02 = this.prev;
        if (r02 == (-1)) goto L8;
        this.items.get(r02).stopAnimation();
    L8:
        this.items.get(this.current).stopAnimation();
        updateCurrentAnimatedItem();
    }
}
