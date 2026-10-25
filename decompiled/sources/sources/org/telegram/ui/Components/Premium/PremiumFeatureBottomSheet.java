package org.telegram.ui.Components.Premium;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.BottomPagesView;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.ThemePreviewActivity;
import org.telegram.ui.bots.AffiliateProgramFragment;

/* loaded from: classes7.dex */
public class PremiumFeatureBottomSheet extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    ActionBar actionBar;
    private final BaseFragment baseFragment;
    private FrameLayout buttonContainer;
    FrameLayout closeLayout;
    boolean containerViewsForward;
    float containerViewsProgress;
    FrameLayout content;
    int contentHeight;
    boolean enterAnimationIsRunning;
    private boolean forceAbout;
    boolean fullscreenNext;
    private int gradientAlpha;
    private final boolean onlySelectedType;
    private PremiumButtonView premiumButtonView;
    ArrayList<PremiumPreviewFragment.PremiumFeatureData> premiumFeatures;
    float progress;
    float progressToFullscreenView;
    float progressToGradient;
    int selectedPosition;
    private PremiumPreviewFragment.SubscriptionTier selectedTier;
    private final int startType;
    SvgHelper.SvgDrawable svgIcon;
    int toPosition;
    int topCurrentOffset;
    int topGlobalOffset;
    ViewPager viewPager;

    /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ PremiumFeatureBottomSheet this$0;

        public AnonymousClass1(PremiumFeatureBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            boolean r0 = PremiumFeatureBottomSheet.access$000(this.this$0);
            PremiumFeatureBottomSheet r1 = this.this$0;
            if (r0 == false) goto L5;
            r1.contentHeight = View.MeasureSpec.getSize(r4);
        L6:
            super.onMeasure(r4, r5);
            return;
        L5:
            r1.contentHeight = (int) (Math.min(View.MeasureSpec.getSize(r4), View.MeasureSpec.getSize(r5)) * 0.8f);
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$10, reason: invalid class name */
    public class AnonymousClass10 extends RecyclerView.OnScrollListener {
        final /* synthetic */ PremiumFeatureBottomSheet this$0;

        public AnonymousClass10(PremiumFeatureBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            PremiumFeatureBottomSheet.access$1400(this.this$0).invalidate();
            this.this$0.checkTopOffset();
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$11, reason: invalid class name */
    public class AnonymousClass11 extends PremiumStickersPreviewRecycler {
        final /* synthetic */ PremiumFeatureBottomSheet this$0;

        public AnonymousClass11(PremiumFeatureBottomSheet r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.Premium.PremiumStickersPreviewRecycler, org.telegram.ui.Components.Premium.PagerHeaderView
        public void setOffset(float r2) {
            if (r2 != 0.0f) goto L5;
            boolean r0 = true;
        L6:
            setAutoPlayEnabled(r0);
            super.setOffset(r2);
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$12, reason: invalid class name */
    public class AnonymousClass12 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ PremiumFeatureBottomSheet this$0;
        final /* synthetic */ PremiumAppIconsPreviewView val$premiumAppIconsPreviewView;

        public AnonymousClass12(PremiumFeatureBottomSheet r1, PremiumAppIconsPreviewView r2) {
            this.this$0 = r1;
            this.val$premiumAppIconsPreviewView = r2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r1) {
            this.val$premiumAppIconsPreviewView.setOffset(((Float) r1.getAnimatedValue()).floatValue());
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$13, reason: invalid class name */
    public class AnonymousClass13 extends AnimatorListenerAdapter {
        final /* synthetic */ PremiumFeatureBottomSheet this$0;
        final /* synthetic */ PremiumAppIconsPreviewView val$premiumAppIconsPreviewView;

        public AnonymousClass13(PremiumFeatureBottomSheet r1, PremiumAppIconsPreviewView r2) {
            this.this$0 = r1;
            this.val$premiumAppIconsPreviewView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            this.this$0.enterAnimationIsRunning = false;
            this.val$premiumAppIconsPreviewView.setOffset(0.0f);
            super.onAnimationEnd(r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ PremiumFeatureBottomSheet this$0;
        final /* synthetic */ PremiumGradient.PremiumGradientTools val$gradientTools;

        public AnonymousClass2(PremiumFeatureBottomSheet r1, Context r2, PremiumGradient.PremiumGradientTools r3) {
            this.this$0 = r1;
            this.val$gradientTools = r3;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            this.val$gradientTools.gradientMatrix(0, 0, getMeasuredWidth(), getMeasuredHeight(), 0.0f, 0.0f);
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, AndroidUtilities.dp(2.0f), getMeasuredWidth(), getMeasuredHeight() + AndroidUtilities.dp(18.0f));
            r8.save();
            r8.clipRect(0, 0, getMeasuredWidth(), getMeasuredHeight());
            this.val$gradientTools.paint.setAlpha(PremiumFeatureBottomSheet.m12032$$Nest$fgetgradientAlpha(this.this$0));
            r8.drawRoundRect(r0, AndroidUtilities.dp(24.0f) - 1, AndroidUtilities.dp(24.0f) - 1, this.val$gradientTools.paint);
            r8.restore();
            super.dispatchDraw(r8);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(this.this$0.contentHeight + AndroidUtilities.dp(2.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends ViewPager {
        long lastTapTime;
        private Scroller scroller;
        private boolean smoothScroll;
        final /* synthetic */ PremiumFeatureBottomSheet this$0;

        /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$3$1, reason: invalid class name */
        public class AnonymousClass1 extends Scroller {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.widget.Scroller
            public void startScroll(int r2, int r3, int r4, int r5, int r6) {
                if (AnonymousClass3.m12038$$Nest$fgetsmoothScroll(this.this$1) == false) goto L5;
                int r0 = 3;
            L6:
                super.startScroll(r2, r3, r4, r5, r6 * r0);
                return;
            L5:
                r0 = 1;
                goto L6
            }
        }

        /* renamed from: -$$Nest$fgetsmoothScroll, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m12038$$Nest$fgetsmoothScroll(AnonymousClass3 r0) {
            return r0.smoothScroll;
        }

        public AnonymousClass3(PremiumFeatureBottomSheet r2, Context r3) {
            this.this$0 = r2;
            super(r3);
            Field r22 = ViewPager.class.getDeclaredField("mScroller");     // Catch: Exception -> L5
            r22.setAccessible(true);     // Catch: Exception -> L5
            AnonymousClass1 r32 = new AnonymousClass1(this, getContext());     // Catch: Exception -> L5
            this.scroller = r32;     // Catch: Exception -> L5
            r22.set(this, r32);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        private boolean processTap(MotionEvent r7, boolean r8) {
            if (r7.getAction() != 0) goto L7;
            this.lastTapTime = System.currentTimeMillis();
            return true;
        L7:
            if (r7.getAction() != 1) goto L24;
            if ((System.currentTimeMillis() - this.lastTapTime) > ViewConfiguration.getTapTimeout()) goto L26;
            Scroller r82 = this.scroller;
            if (r82 == null) goto L26;
            if (r82.isFinished() == false) goto L26;
            this.smoothScroll = true;
            float r72 = r7.getX();
            float r83 = getWidth() * 0.45f;
            PremiumFeatureBottomSheet r84 = this.this$0;
            if (r72 > r83) goto L17;
            int r73 = r84.selectedPosition;
            if ((r73 - 1) < 0) goto L22;
            setCurrentItem(r73 - 1, true);
        L22:
            this.smoothScroll = false;
            goto L26
        L17:
            if ((r84.selectedPosition + 1) >= r84.premiumFeatures.size()) goto L22;
            setCurrentItem(this.this$0.selectedPosition + 1, true);
        L26:
            return false;
        L24:
            if (r7.getAction() != 3) goto L26;
            this.lastTapTime = -1;
            goto L26
        }

        @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r2) {
            processTap(r2, true);     // Catch: Exception -> L5
            return super.onInterceptTouchEvent(r2);
        L5:
            return false;
        }

        @Override // androidx.viewpager.widget.ViewPager, android.view.View
        public void onMeasure(int r3, int r4) {
            int r42 = AndroidUtilities.dp(100.0f);
            if (getChildCount() <= 0) goto L5;
            getChildAt(0).measure(r3, View.MeasureSpec.makeMeasureSpec(0, 0));
            r42 = getChildAt(0).getMeasuredHeight();
        L5:
            super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(r42 + this.this$0.topGlobalOffset, TLObject.FLAG_30));
        }

        @Override // androidx.viewpager.widget.ViewPager, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (this.this$0.enterAnimationIsRunning == false) goto L5;
            return false;
        L5:
            boolean r0 = processTap(r3, false);
            if (super.onTouchEvent(r3) == true) goto L10;
            if (r0 == true) goto L12;
            return false;
        L12:
            return true;
        L10:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends PagerAdapter {
        final /* synthetic */ PremiumFeatureBottomSheet this$0;

        public AnonymousClass4(PremiumFeatureBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup r1, int r2, Object r3) {
            r1.removeView((View) r3);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.this$0.premiumFeatures.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup r4, int r5) {
            PremiumFeatureBottomSheet r1 = this.this$0;
            ViewPage r0 = new ViewPage(r1, r1.getContext(), r5);
            r4.addView(r0);
            r0.position = r5;
            r0.setFeatureDate(this.this$0.premiumFeatures.get(r5));
            return r0;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View r1, Object r2) {
            if (r1 != r2) goto L5;
            return true;
        L5:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ PremiumFeatureBottomSheet this$0;
        final /* synthetic */ BottomPagesView val$bottomPages;

        public static /* synthetic */ void $r8$lambda$EkFIaT7FdICwxg_rpSMJsE_YQcI(AnonymousClass5 r0) {
            r0.lambda$checkPage$0();
        }

        public AnonymousClass5(PremiumFeatureBottomSheet r1, BottomPagesView r2) {
            this.this$0 = r1;
            this.val$bottomPages = r2;
        }

        private void checkPage() {
            int r1 = 0;
        L3:
            int r2 = this.this$0.viewPager.getChildCount();
            PremiumFeatureBottomSheet r3 = this.this$0;
            float r4 = 0.0f;
            if (r1 >= r2) goto L20;
            ViewPage r22 = (ViewPage) r3.viewPager.getChildAt(r1);
            PremiumFeatureBottomSheet r32 = this.this$0;
            if (r32.enterAnimationIsRunning == true) goto L8;
        L9:
            int r5 = r22.position;
            if (r5 != r32.selectedPosition) goto L12;
            PagerHeaderView r33 = r22.topHeader;
            r4 = (-r22.getMeasuredWidth()) * this.this$0.progress;
            r33.setOffset(r4);
        L17:
            if ((r22.topView instanceof PremiumAppIconsPreviewView) == false) goto L19;
            r22.setTranslationX(-r4);
            r22.title.setTranslationX(r4);
            r22.description.setTranslationX(r4);
        L19:
            r1 = r1 + 1;
            goto L3
        L12:
            int r34 = r32.toPosition;
            PagerHeaderView r6 = r22.topHeader;
            if (r5 != r34) goto L15;
            r4 = r22.getMeasuredWidth() + ((-r22.getMeasuredWidth()) * this.this$0.progress);
            r6.setOffset(r4);
            goto L17
        L15:
            r6.setOffset(r22.getMeasuredWidth());
            goto L17
        L8:
            if ((r22.topView instanceof PremiumAppIconsPreviewView) == true) goto L17;
        L20:
            r3.containerViewsProgress = r3.progress;
            int r12 = r3.toPosition;
            int r23 = r3.selectedPosition;
            if (r12 <= r23) goto L23;
            boolean r13 = true;
        L24:
            r3.containerViewsForward = r13;
            if (r23 >= 0) goto L27;
        L31:
            boolean r14 = false;
        L32:
            PremiumFeatureBottomSheet r24 = this.this$0;
            int r35 = r24.toPosition;
            if (r35 >= 0) goto L35;
        L39:
            boolean r25 = false;
        L41:
            if (r14 == false) goto L47;
            if (r25 == false) goto L47;
            PremiumFeatureBottomSheet r0 = this.this$0;
            r0.progressToGradient = 1.0f;
            float r15 = r0.progress;
            if (r15 != 0.0f) goto L46;
            r15 = 1.0f;
        L46:
            r0.progressToFullscreenView = r15;
            r0.fullscreenNext = true;
        L53:
            PremiumFeatureBottomSheet r02 = this.this$0;
            int r16 = (int) ((1.0f - r02.progressToFullscreenView) * 255.0f);
            if (r16 == PremiumFeatureBottomSheet.m12032$$Nest$fgetgradientAlpha(r02)) goto L60;
            PremiumFeatureBottomSheet.m12035$$Nest$fputgradientAlpha(this.this$0, r16);
            this.this$0.content.invalidate();
            AndroidUtilities.runOnUIThread(new PremiumFeatureBottomSheet$5$$ExternalSyntheticLambda0(this));
            return;
        L60:
            return;
        L47:
            if (r14 == false) goto L49;
            PremiumFeatureBottomSheet r03 = this.this$0;
            float r17 = 1.0f - r03.progress;
            r03.progressToFullscreenView = r17;
            r03.progressToGradient = r17;
            r03.fullscreenNext = true;
            goto L53
        L49:
            PremiumFeatureBottomSheet r18 = this.this$0;
            if (r25 == false) goto L52;
            float r26 = r18.progress;
            r18.progressToFullscreenView = r26;
            r18.progressToGradient = r26;
            r18.fullscreenNext = false;
            goto L53
        L52:
            r18.progressToFullscreenView = 0.0f;
            r18.progressToGradient = 0.0f;
            r18.fullscreenNext = true;
            goto L53
        L35:
            if (r35 >= r24.premiumFeatures.size()) goto L39;
            PremiumFeatureBottomSheet r27 = this.this$0;
            if (PremiumFeatureBottomSheet.m12036$$Nest$misFullscreenType(r27, r27.premiumFeatures.get(r27.toPosition).type) == false) goto L39;
            r25 = true;
            goto L41
        L27:
            if (r23 >= r3.premiumFeatures.size()) goto L31;
            PremiumFeatureBottomSheet r19 = this.this$0;
            if (PremiumFeatureBottomSheet.m12036$$Nest$misFullscreenType(r19, r19.premiumFeatures.get(r19.selectedPosition).type) == false) goto L31;
            r14 = true;
            goto L32
        L23:
            r13 = false;
            goto L24
        }

        private /* synthetic */ void lambda$checkPage$0() {
            this.this$0.checkTopOffset();
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r1) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r2, float r3, int r4) {
            this.val$bottomPages.setPageOffset(r2, r3);
            PremiumFeatureBottomSheet r0 = this.this$0;
            r0.selectedPosition = r2;
            if (r4 <= 0) goto L5;
            int r22 = r2 + 1;
        L6:
            r0.toPosition = r22;
            r0.progress = r3;
            checkPage();
            return;
        L5:
            r22 = r2 - 1;
            goto L6
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r4) {
            int r0 = this.this$0.premiumFeatures.get(r4).type;
            PremiumFeatureBottomSheet r1 = this.this$0;
            if (r0 != 0) goto L5;
            r1.actionBar.setTitle(LocaleController.getString(R.string.DoubledLimits));
            this.this$0.actionBar.requestLayout();
        L14:
            checkPage();
            return;
        L5:
            int r02 = r1.premiumFeatures.get(r4).type;
            PremiumFeatureBottomSheet r12 = this.this$0;
            if (r02 != 14) goto L8;
            r12.actionBar.setTitle(LocaleController.getString(R.string.UpgradedStories));
            this.this$0.actionBar.requestLayout();
            goto L14
        L8:
            int r03 = r12.premiumFeatures.get(r4).type;
            PremiumFeatureBottomSheet r13 = this.this$0;
            if (r03 != 40) goto L12;
            r13.actionBar.setTitle(LocaleController.getString(R.string.FeaturePreviewGifts));
            this.this$0.actionBar.requestLayout();
            goto L14
        L12:
            if (r13.premiumFeatures.get(r4).type != 28) goto L14;
            this.this$0.actionBar.setTitle(LocaleController.getString(R.string.TelegramBusiness));
            this.this$0.actionBar.requestLayout();
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$6, reason: invalid class name */
    public class AnonymousClass6 extends FrameLayout {
        int lastSize;
        private final Path path;
        final /* synthetic */ PremiumFeatureBottomSheet this$0;
        final /* synthetic */ Drawable val$headerShadowDrawable;
        final /* synthetic */ ScrollView val$scrollView;

        public AnonymousClass6(PremiumFeatureBottomSheet r1, Context r2, ScrollView r3, Drawable r4) {
            this.this$0 = r1;
            this.val$scrollView = r3;
            this.val$headerShadowDrawable = r4;
            super(r2);
            this.path = new Path();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            Drawable r0 = PremiumFeatureBottomSheet.access$400(this.this$0);
            PremiumFeatureBottomSheet r1 = this.this$0;
            r0.setBounds(0, ((r1.topCurrentOffset + PremiumFeatureBottomSheet.access$300(r1)) - AndroidUtilities.dp(2.0f)) + 1, getMeasuredWidth(), getMeasuredHeight());
            PremiumFeatureBottomSheet.access$500(this.this$0).draw(r7);
            super.dispatchDraw(r7);
            ActionBar r02 = this.this$0.actionBar;
            if (r02 != null) goto L5;
            return;
        L5:
            if (r02.getVisibility() == 0) goto L7;
            return;
        L7:
            if (this.this$0.actionBar.getAlpha() == 0.0f) goto L12;
            this.val$headerShadowDrawable.setBounds(0, this.this$0.actionBar.getBottom(), getMeasuredWidth(), this.this$0.actionBar.getBottom() + this.val$headerShadowDrawable.getIntrinsicHeight());
            this.val$headerShadowDrawable.setAlpha((int) (this.this$0.actionBar.getAlpha() * 255.0f));
            this.val$headerShadowDrawable.draw(r7);
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r4) {
            if (r4.getAction() != 0) goto L8;
            float r0 = r4.getY();
            PremiumFeatureBottomSheet r1 = this.this$0;
            if (r0 >= ((r1.topCurrentOffset - PremiumFeatureBottomSheet.access$600(r1)) + AndroidUtilities.dp(2.0f))) goto L8;
            this.this$0.dismiss();
        L8:
            return super.dispatchTouchEvent(r4);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r7, View r8, long r9) {
            if (r8 != this.val$scrollView) goto L7;
            r7.save();
            this.path.rewind();
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(getPaddingLeft(), this.this$0.topCurrentOffset + AndroidUtilities.dp(18.0f), getMeasuredWidth() - getPaddingRight(), getMeasuredHeight());
            this.path.addRoundRect(r0, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), Path.Direction.CW);
            r7.clipPath(this.path);
            super.drawChild(r7, r8, r9);
            r7.restore();
            return true;
        L7:
            return super.drawChild(r7, r8, r9);
        }

        @Override // android.view.View
        public boolean hasOverlappingRendering() {
            return false;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            this.lastSize = (r4 + r5) << 16;
            this.this$0.topGlobalOffset = 0;
            this.val$scrollView.measure(r4, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5), Integer.MIN_VALUE));
            PremiumFeatureBottomSheet r0 = this.this$0;
            r0.topGlobalOffset = (View.MeasureSpec.getSize(r5) - this.val$scrollView.getMeasuredHeight()) + PremiumFeatureBottomSheet.access$200(this.this$0);
            super.onMeasure(r4, r5);
            this.this$0.checkTopOffset();
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            PremiumFeatureBottomSheet.access$100(this.this$0, r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$7, reason: invalid class name */
    public class AnonymousClass7 extends ActionBar {
        final /* synthetic */ PremiumFeatureBottomSheet this$0;

        public AnonymousClass7(PremiumFeatureBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setAlpha(float r2) {
            if (getAlpha() == r2) goto L6;
            super.setAlpha(r2);
            PremiumFeatureBottomSheet.access$700(this.this$0).invalidate();
            return;
        }

        @Override // android.view.View
        public void setTag(Object r1) {
            super.setTag(r1);
            PremiumFeatureBottomSheet.m12037$$Nest$mupdateStatusBar(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$8, reason: invalid class name */
    public class AnonymousClass8 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PremiumFeatureBottomSheet this$0;

        public AnonymousClass8(PremiumFeatureBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.dismiss();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet$9, reason: invalid class name */
    public class AnonymousClass9 extends RecyclerView.OnScrollListener {
        final /* synthetic */ PremiumFeatureBottomSheet this$0;

        public AnonymousClass9(PremiumFeatureBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            PremiumFeatureBottomSheet.access$1300(this.this$0).invalidate();
            this.this$0.checkTopOffset();
        }
    }

    public class ViewPage extends LinearLayout {
        LinkSpanDrawable.LinksTextView description;
        LinearLayout featuresLayout;
        public int position;
        final /* synthetic */ PremiumFeatureBottomSheet this$0;
        TextView title;
        PagerHeaderView topHeader;
        View topView;
        boolean topViewOnFullHeight;

        public ViewPage(PremiumFeatureBottomSheet r10, Context r11, int r12) {
            this.this$0 = r10;
            super(r11);
            setOrientation(1);
            View r122 = r10.getViewForPosition(r11, r12);
            this.topView = r122;
            addView(r122);
            this.topHeader = (PagerHeaderView) this.topView;
            TextView r123 = new TextView(r11);
            this.title = r123;
            r123.setGravity(1);
            TextView r124 = this.title;
            int r1 = Theme.key_dialogTextBlack;
            r124.setTextColor(PremiumFeatureBottomSheet.access$800(r10, r1));
            this.title.setTextSize(1, 20.0f);
            this.title.setTypeface(AndroidUtilities.bold());
            addView(this.title, LayoutHelper.createFrame(-1, -2.0f, 0, 21.0f, 20.0f, 21.0f, 0.0f));
            LinkSpanDrawable.LinksTextView r125 = new LinkSpanDrawable.LinksTextView(r11);
            this.description = r125;
            r125.setGravity(1);
            this.description.setTextSize(1, 15.0f);
            this.description.setTextColor(PremiumFeatureBottomSheet.access$900(r10, r1));
            if (PremiumFeatureBottomSheet.m12033$$Nest$fgetonlySelectedType(r10) == true) goto L5;
            this.description.setLines(2);
        L5:
            addView(this.description, LayoutHelper.createLinear(-1, -2, 1, 21, 10, 21, 16));
            setImportantForAccessibility(2);
            setClipChildren(false);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r4, View r5, long r6) {
            if (r5 != this.topView) goto L14;
            boolean r0 = r5 instanceof BaseListPageView;
            if (r0 == false) goto L7;
            setTranslationY(0.0f);
        L8:
            if (r0 == true) goto L10;
            r4.save();
            r4.clipRect(0, 0, r5.getMeasuredWidth(), r5.getMeasuredHeight());
            boolean r3 = super.drawChild(r4, r5, r6);
            r4.restore();
            return r3;
        L10:
            return super.drawChild(r4, r5, r6);
        L7:
            setTranslationY(this.this$0.topGlobalOffset);
            goto L8
        L14:
            return super.drawChild(r4, r5, r6);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            this.title.setVisibility(0);
            View r0 = this.topView;
            if ((r0 instanceof BaseListPageView) == false) goto L5;
            ((BaseListPageView) r0).setTopOffset(this.this$0.topGlobalOffset);
        L5:
            ViewGroup.LayoutParams r02 = this.topView.getLayoutParams();
            r02.height = this.this$0.contentHeight;
            this.description.setVisibility(0);
            ((ViewGroup.MarginLayoutParams) this.topView.getLayoutParams()).bottomMargin = 0;
            super.onMeasure(r5, r6);
            if (this.topViewOnFullHeight == false) goto L9;
            ViewGroup.LayoutParams r03 = this.topView.getLayoutParams();
            r03.height = getMeasuredHeight() - AndroidUtilities.dp(16.0f);
            ViewGroup.MarginLayoutParams r04 = (ViewGroup.MarginLayoutParams) this.topView.getLayoutParams();
            r04.bottomMargin = AndroidUtilities.dp(16.0f);
            this.title.setVisibility(8);
            this.description.setVisibility(8);
            super.onMeasure(r5, r6);
            return;
        }

        public void setFeatureDate(PremiumPreviewFragment.PremiumFeatureData r12) {
            int r0 = r12.type;
            int r1 = 8;
            if (r0 != 0) goto L5;
        L50:
            this.title.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.description.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.topViewOnFullHeight = true;
        L51:
            LinkSpanDrawable.LinksTextView r02 = this.description;
            r02.setMaxWidth(HintView2.cutInFancyHalf(r02.getText(), this.description.getPaint()));
            requestLayout();
            if (r12.type != 40) goto L54;
            boolean r122 = true;
        L55:
            if (r122 == true) goto L57;
        L59:
            LinearLayout r03 = this.featuresLayout;
            if (r03 == null) goto L64;
            if (r122 == false) goto L63;
            r1 = 0;
        L63:
            r03.setVisibility(r1);
        L64:
            ViewGroup.MarginLayoutParams r11 = (ViewGroup.MarginLayoutParams) this.description.getLayoutParams();
            if (r122 == false) goto L68;
            float r123 = 6.0f;
        L69:
            r11.topMargin = AndroidUtilities.dp(r123);
            return;
        L68:
            r123 = 10.0f;
            goto L69
        L57:
            if (this.featuresLayout != null) goto L59;
            LinearLayout r04 = new LinearLayout(getContext());
            this.featuresLayout = r04;
            r04.setOrientation(1);
            AffiliateProgramFragment.FeatureCell r2 = new AffiliateProgramFragment.FeatureCell(getContext(), true, PremiumFeatureBottomSheet.access$1000(this.this$0));
            r2.set(R.drawable.menu_feature_unique, LocaleController.getString(R.string.GiftsFeature1Title), LocaleController.getString(R.string.GiftsFeature1Text));
            this.featuresLayout.addView(r0[0], LayoutHelper.createLinear(-1, -2));
            AffiliateProgramFragment.FeatureCell r22 = new AffiliateProgramFragment.FeatureCell(getContext(), true, PremiumFeatureBottomSheet.access$1100(this.this$0));
            r22.set(R.drawable.menu_feature_tradable, LocaleController.getString(R.string.GiftsFeature2Title), LocaleController.getString(R.string.GiftsFeature2Text));
            this.featuresLayout.addView(r0[1], LayoutHelper.createLinear(-1, -2));
            AffiliateProgramFragment.FeatureCell r23 = new AffiliateProgramFragment.FeatureCell(getContext(), true, PremiumFeatureBottomSheet.access$1200(this.this$0));
            AffiliateProgramFragment.FeatureCell[] r05 = {r2, r22, r23};
            r23.set(R.drawable.menu_wear, LocaleController.getString(R.string.GiftsFeature3Title), LocaleController.getString(R.string.GiftsFeature3Text));
            this.featuresLayout.addView(r05[2], LayoutHelper.createLinear(-1, -2));
            addView(this.featuresLayout, LayoutHelper.createLinear(-1, -2, 0.0f, -4.0f, 0.0f, 0.0f));
            goto L59
        L54:
            r122 = false;
            goto L55
        L5:
            if (r0 == 14) goto L50;
            if (r0 == 28) goto L50;
            if (PremiumFeatureBottomSheet.m12033$$Nest$fgetonlySelectedType(this.this$0) == true) goto L12;
            this.title.setText(r12.title);
            this.description.setText(AndroidUtilities.replaceTags(r12.description));
            this.topViewOnFullHeight = false;
            goto L51
        L12:
            if (PremiumFeatureBottomSheet.m12034$$Nest$fgetstartType(this.this$0) != 4) goto L15;
            this.title.setText(LocaleController.getString(R.string.AdditionalReactions));
            this.description.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.AdditionalReactionsDescription)));
        L48:
            this.topViewOnFullHeight = false;
            goto L51
        L15:
            if (PremiumFeatureBottomSheet.m12034$$Nest$fgetstartType(this.this$0) != 3) goto L18;
            this.title.setText(LocaleController.getString(R.string.PremiumPreviewNoAds));
            this.description.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PremiumPreviewNoAdsDescription2)));
            goto L48
        L18:
            if (PremiumFeatureBottomSheet.m12034$$Nest$fgetstartType(this.this$0) != 24) goto L21;
            this.title.setText(LocaleController.getString(R.string.PremiumPreviewTags));
            this.description.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PremiumPreviewTagsDescription)));
            goto L48
        L21:
            if (PremiumFeatureBottomSheet.m12034$$Nest$fgetstartType(this.this$0) != 10) goto L24;
            this.title.setText(LocaleController.getString(R.string.PremiumPreviewAppIcon));
            this.description.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PremiumPreviewAppIconDescription2)));
            goto L48
        L24:
            if (PremiumFeatureBottomSheet.m12034$$Nest$fgetstartType(this.this$0) != 2) goto L27;
            this.title.setText(LocaleController.getString(R.string.PremiumPreviewDownloadSpeed));
            this.description.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PremiumPreviewDownloadSpeedDescription2)));
            goto L48
        L27:
            if (PremiumFeatureBottomSheet.m12034$$Nest$fgetstartType(this.this$0) != 9) goto L30;
            this.title.setText(LocaleController.getString(R.string.PremiumPreviewAdvancedChatManagement));
            this.description.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PremiumPreviewAdvancedChatManagementDescription2)));
            goto L48
        L30:
            if (PremiumFeatureBottomSheet.m12034$$Nest$fgetstartType(this.this$0) != 8) goto L33;
            this.title.setText(LocaleController.getString(R.string.PremiumPreviewVoiceToText));
            this.description.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PremiumPreviewVoiceToTextDescription2)));
            goto L48
        L33:
            if (PremiumFeatureBottomSheet.m12034$$Nest$fgetstartType(this.this$0) != 13) goto L36;
            this.title.setText(LocaleController.getString(R.string.PremiumPreviewTranslations));
            this.description.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PremiumPreviewTranslationsDescription)));
            goto L48
        L36:
            if (PremiumFeatureBottomSheet.m12034$$Nest$fgetstartType(this.this$0) != 38) goto L39;
            this.title.setText(LocaleController.getString(R.string.PremiumPreviewEffects));
            this.description.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PremiumPreviewEffectsDescription)));
            goto L48
        L39:
            if (PremiumFeatureBottomSheet.m12034$$Nest$fgetstartType(this.this$0) != 22) goto L42;
            this.title.setText(LocaleController.getString(R.string.PremiumPreviewWallpaper));
            this.description.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PremiumPreviewWallpaperDescription)));
            goto L48
        L42:
            if (PremiumFeatureBottomSheet.m12034$$Nest$fgetstartType(this.this$0) != 23) goto L44;
            this.title.setText(LocaleController.getString(R.string.PremiumPreviewProfileColor));
            this.description.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PremiumPreviewProfileColorDescription)));
            goto L48
        L44:
            int r06 = PremiumFeatureBottomSheet.m12034$$Nest$fgetstartType(this.this$0);
            TextView r6 = this.title;
            if (r06 != 41) goto L47;
            r6.setText(LocaleController.getString(R.string.PremiumPreviewSharingDisable));
            this.description.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PremiumPreviewSharingDisableDescription)));
            goto L48
        L47:
            r6.setText(r12.title);
            this.description.setText(AndroidUtilities.replaceTags(r12.description));
            goto L48
        }
    }

    /* renamed from: $r8$lambda$T6zRECCH-ykDKTFwDE0rtn1fjM4, reason: not valid java name */
    public static /* synthetic */ void m12030$r8$lambda$T6zRECCHykDKTFwDE0rtn1fjM4(PremiumFeatureBottomSheet r0, BaseFragment r1, boolean r2, PremiumPreviewFragment.PremiumFeatureData r3, View r4) {
        r0.lambda$new$1(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$W1IfZOVqlTjvp0KHGozz84vI5v8(PremiumFeatureBottomSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$WG6-6hPZwo33LKUv3t_HO7nyQFA, reason: not valid java name */
    public static /* synthetic */ void m12031$r8$lambda$WG66hPZwo33LKUv3t_HO7nyQFA(PremiumFeatureBottomSheet r0, View r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: -$$Nest$fgetgradientAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12032$$Nest$fgetgradientAlpha(PremiumFeatureBottomSheet r0) {
        return r0.gradientAlpha;
    }

    /* renamed from: -$$Nest$fgetonlySelectedType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12033$$Nest$fgetonlySelectedType(PremiumFeatureBottomSheet r0) {
        return r0.onlySelectedType;
    }

    /* renamed from: -$$Nest$fgetstartType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12034$$Nest$fgetstartType(PremiumFeatureBottomSheet r0) {
        return r0.startType;
    }

    /* renamed from: -$$Nest$fputgradientAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12035$$Nest$fputgradientAlpha(PremiumFeatureBottomSheet r0, int r1) {
        r0.gradientAlpha = r1;
    }

    /* renamed from: -$$Nest$misFullscreenType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12036$$Nest$misFullscreenType(PremiumFeatureBottomSheet r0, int r1) {
        return r0.isFullscreenType(r1);
    }

    /* renamed from: -$$Nest$mupdateStatusBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12037$$Nest$mupdateStatusBar(PremiumFeatureBottomSheet r0) {
        r0.updateStatusBar();
    }

    public PremiumFeatureBottomSheet(Context r10, int r11, boolean r12, Theme.ResourcesProvider r13) {
        this(null, r10, UserConfig.selectedAccount, false, r11, r12, null, r13);
    }

    public PremiumFeatureBottomSheet(BaseFragment r2, int r3, boolean r4) {
        this(r2, r3, r4, null);
    }

    public PremiumFeatureBottomSheet(BaseFragment r9, int r10, boolean r11, PremiumPreviewFragment.SubscriptionTier r12) {
        this(r9, r9.getContext(), r9.getCurrentAccount(), false, r10, r11, r12);
    }

    public PremiumFeatureBottomSheet(BaseFragment r9, Context r10, int r11, int r12, boolean r13) {
        this(r9, r10, r11, false, r12, r13, null);
    }

    public PremiumFeatureBottomSheet(BaseFragment r10, Context r11, int r12, boolean r13, int r14, boolean r15, PremiumPreviewFragment.SubscriptionTier r16) {
        this(r10, r11, r12, r13, r14, r15, r16, getResourceProvider(r10));
    }

    public PremiumFeatureBottomSheet(BaseFragment r24, Context r25, int r26, boolean r27, int r28, boolean r29, PremiumPreviewFragment.SubscriptionTier r30, Theme.ResourcesProvider r31) {
        super(r25, false, r31);
        this.premiumFeatures = new ArrayList();
        this.gradientAlpha = 255;
        this.baseFragment = r24;
        this.selectedTier = r30;
        fixNavigationBar(getThemedColor(Theme.key_dialogBackground));
        this.startType = r28;
        this.onlySelectedType = r29;
        this.svgIcon = SvgHelper.getDrawable(AndroidUtilities.readRes(R.raw.star_loader));
        AnonymousClass1 r7 = new AnonymousClass1(this, getContext());
        if (r27 == false) goto L5;
    L8:
        PremiumPreviewFragment.fillBusinessFeaturesList(this.premiumFeatures, r26, false);
        PremiumPreviewFragment.fillBusinessFeaturesList(this.premiumFeatures, r26, true);
    L10:
        if (r28 != 40) goto L12;
        this.premiumFeatures.clear();
        this.premiumFeatures.add(new PremiumPreviewFragment.PremiumFeatureData(40, R.drawable.gift, LocaleController.getString(R.string.FeaturePreviewGifts), LocaleController.getString(R.string.FeaturePreviewGiftsDescription)));
    L12:
        int r10 = 0;
    L14:
        if (r10 >= this.premiumFeatures.size()) goto L19;
        if (this.premiumFeatures.get(r10).type == r28) goto L20;
        r10 = r10 + 1;
    L20:
        if (r29 == false) goto L22;
        PremiumPreviewFragment.PremiumFeatureData r102 = this.premiumFeatures.get(r10);
        this.premiumFeatures.clear();
        this.premiumFeatures.add(r102);
        r10 = 0;
    L22:
        PremiumPreviewFragment.PremiumFeatureData r11 = this.premiumFeatures.get(r10);
        setApplyTopPadding(false);
        setApplyBottomPadding(false);
        this.useBackgroundTopPadding = false;
        PremiumGradient.PremiumGradientTools r12 = new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradientBottomSheet1, Theme.key_premiumGradientBottomSheet2, Theme.key_premiumGradientBottomSheet3, -1);
        r12.x1 = 0.0f;
        r12.y1 = 1.1f;
        r12.x2 = 1.5f;
        r12.y2 = -0.2f;
        r12.exactly = true;
        this.content = new AnonymousClass2(this, getContext(), r12);
        FrameLayout r122 = new FrameLayout(getContext());
        this.closeLayout = r122;
        r122.setContentDescription(LocaleController.getString(R.string.Close));
        ImageView r123 = new ImageView(getContext());
        r123.setImageResource(R.drawable.msg_close);
        r123.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(12.0f), ColorUtils.setAlphaComponent(-1, 40), ColorUtils.setAlphaComponent(-1, 100)));
        this.closeLayout.addView(r123, LayoutHelper.createFrame(24, 24, 17));
        this.closeLayout.setOnClickListener(new PremiumFeatureBottomSheet$$ExternalSyntheticLambda0(this));
        r7.addView(this.content, LayoutHelper.createLinear(-1, -2, 1, 0, 16, 0, 0));
        AnonymousClass3 r124 = new AnonymousClass3(this, getContext());
        this.viewPager = r124;
        r124.setOverScrollMode(2);
        this.viewPager.setOffscreenPageLimit(0);
        this.viewPager.setAdapter(new AnonymousClass4(this));
        ViewPager r125 = this.viewPager;
        this.selectedPosition = r10;
        r125.setCurrentItem(r10);
        r7.addView(this.viewPager, LayoutHelper.createFrame(-1, 100.0f, 0, 0.0f, 18.0f, 0.0f, 0.0f));
        r7.addView(this.closeLayout, LayoutHelper.createFrame(52, 52.0f, 53, 0.0f, 24.0f, 0.0f, 0.0f));
        BottomPagesView r103 = new BottomPagesView(getContext(), this.viewPager, this.premiumFeatures.size());
        this.viewPager.addOnPageChangeListener(new AnonymousClass5(this, r103));
        LinearLayout r126 = new LinearLayout(getContext());
        r126.addView(r7);
        r126.setOrientation(1);
        r103.setColor(Theme.key_chats_unreadCounterMuted, Theme.key_chats_actionBackground);
        if (r29 == true) goto L25;
        r126.addView(r103, LayoutHelper.createLinear(this.premiumFeatures.size() * 11, 5, 1, 0, 0, 0, 10));
    L25:
        PremiumButtonView r72 = new PremiumButtonView(getContext(), true, r31);
        this.premiumButtonView = r72;
        r72.buttonLayout.setOnClickListener(new PremiumFeatureBottomSheet$$ExternalSyntheticLambda1(this, r24, r29, r11));
        this.premiumButtonView.overlayTextView.setOnClickListener(new PremiumFeatureBottomSheet$$ExternalSyntheticLambda2(this));
        FrameLayout r1 = new FrameLayout(getContext());
        this.buttonContainer = r1;
        r1.addView(this.premiumButtonView, LayoutHelper.createFrame(-1, 48.0f, 16, 16.0f, 0.0f, 16.0f, 0.0f));
        this.buttonContainer.setBackgroundColor(getThemedColor(Theme.key_dialogBackground));
        r126.addView(this.buttonContainer, LayoutHelper.createLinear(-1, 68, 80));
        if (r28 != 40) goto L29;
        this.premiumButtonView.setOverlayText(StarGiftSheet.replaceUnderstood(LocaleController.getString(R.string.Understood)), true, false);
    L31:
        ScrollView r13 = new ScrollView(getContext());
        r13.addView(r126);
        setCustomView(r13);
        MediaDataController.getInstance(r26).preloadPremiumPreviewStickers();
        setButtonText();
        this.customViewGravity = 83;
        AnonymousClass6 r3 = new AnonymousClass6(this, getContext(), r13, ContextCompat.getDrawable(getContext(), R.drawable.header_shadow).mutate());
        this.containerView = r3;
        int r14 = this.backgroundPaddingLeft;
        r3.setPadding(r14, this.backgroundPaddingTop - 1, r14, 0);
        return;
    L29:
        if (UserConfig.getInstance(r26).isPremium() == false) goto L31;
        this.premiumButtonView.setOverlayText(LocaleController.getString(R.string.OK), false, false);
        goto L31
    L19:
        r10 = 0;
        goto L20
    L5:
        if (r28 == 35) goto L8;
        PremiumPreviewFragment.fillPremiumFeaturesList(this.premiumFeatures, r26, false);
        goto L10
    }

    public static /* synthetic */ boolean access$000(PremiumFeatureBottomSheet r0) {
        return r0.isPortrait;
    }

    public static /* synthetic */ void access$100(PremiumFeatureBottomSheet r0, float r1) {
        r0.onContainerTranslationYChanged(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1000(PremiumFeatureBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1100(PremiumFeatureBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1200(PremiumFeatureBottomSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$1300(PremiumFeatureBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$1400(PremiumFeatureBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$200(PremiumFeatureBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$300(PremiumFeatureBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ Drawable access$400(PremiumFeatureBottomSheet r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ Drawable access$500(PremiumFeatureBottomSheet r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ int access$600(PremiumFeatureBottomSheet r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ ViewGroup access$700(PremiumFeatureBottomSheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$800(PremiumFeatureBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$900(PremiumFeatureBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    private static Theme.ResourcesProvider getResourceProvider(BaseFragment r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        if (r1.getLastStoryViewer() == null) goto L10;
        if (r1.getLastStoryViewer().isShown() == false) goto L10;
        return r1.getLastStoryViewer().getResourceProvider();
    L10:
        return r1.getResourceProvider();
    }

    private boolean isFullscreenType(int r1) {
        if (r1 != 0) goto L4;
        return true;
    L4:
        if (r1 != 14) goto L6;
        return true;
    L6:
        if (r1 == 28) goto L13;
        return false;
    L13:
        return true;
    }

    private boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(getThemedColor(Theme.key_dialogBackground)) <= 0.699999988079071d) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(BaseFragment r6, boolean r7, PremiumPreviewFragment.PremiumFeatureData r8, View r9) {
        if ((r6 instanceof ChatActivity) == false) goto L7;
        ChatActivity r92 = (ChatActivity) r6;
        r92.closeMenu();
        ChatAttachAlert r93 = r92.chatAttachAlert;
        if (r93 == null) goto L7;
        r93.dismiss(true);
    L7:
        BaseFragment r94 = LaunchActivity.getLastFragment();
        int r2 = 0;
    L9:
        if (r2 >= 2) goto L22;
        if (r2 != 0) goto L12;
        BaseFragment r3 = r6;
    L13:
        if (r3 != null) goto L15;
    L17:
        if (r3 == null) goto L21;
        if (r3.getVisibleDialog() == null) goto L21;
        r3.getVisibleDialog().dismiss();
    L21:
        r2 = r2 + 1;
        goto L9
    L15:
        if (r3.getLastStoryViewer() == null) goto L17;
        r3.getLastStoryViewer().dismissVisibleDialogs();
        goto L17
    L12:
        r3 = r94;
        goto L13
    L22:
        if (r7 == false) goto L24;
    L27:
        PremiumPreviewFragment r72 = new PremiumPreviewFragment(PremiumPreviewFragment.featureTypeToServerString(r8.type));
        if ((r6 instanceof ThemePreviewActivity) == false) goto L30;
        BaseFragment.BottomSheetParams r82 = new BaseFragment.BottomSheetParams();
        r82.transitionFromLeft = true;
        r82.allowNestedScroll = false;
        r6.showAsSheet(r72, r82);
    L35:
        dismiss();
        return;
    L30:
        if (r6 == null) goto L32;
        r6.presentFragment(r72);
        goto L35
    L32:
        BaseFragment r62 = LaunchActivity.getSafeLastFragment();
        if (r62 == null) goto L35;
        r62.presentFragment(r72);
        goto L35
    L24:
        if (this.forceAbout == true) goto L27;
        PremiumPreviewFragment.buyPremium(r6, this.selectedTier, PremiumPreviewFragment.featureTypeToServerString(r8.type));
        goto L35
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        dismiss();
    }

    private void setButtonText() {
        if (this.forceAbout == false) goto L7;
        this.premiumButtonView.buttonTextView.setText(LocaleController.getString(R.string.AboutTelegramPremium));
        return;
    L7:
        if (this.onlySelectedType == false) goto L18;
        int r0 = this.startType;
        if (r0 != 4) goto L12;
        this.premiumButtonView.buttonTextView.setText(LocaleController.getString(R.string.UnlockPremiumReactions));
        this.premiumButtonView.setIcon(R.raw.unlock_icon);
        return;
    L12:
        PremiumButtonView r1 = this.premiumButtonView;
        if (r0 != 10) goto L16;
        r1.buttonTextView.setText(LocaleController.getString(R.string.UnlockPremiumIcons));
        this.premiumButtonView.setIcon(R.raw.unlock_icon);
        return;
    L16:
        r1.buttonTextView.setText(LocaleController.getString(R.string.AboutTelegramPremium));
        return;
    L18:
        this.premiumButtonView.buttonTextView.setText(PremiumPreviewFragment.getPremiumButtonText(this.currentAccount, this.selectedTier));
    }

    private void updateStatusBar() {
        ActionBar r0 = this.actionBar;
        if (r0 != null) goto L5;
    L8:
        BaseFragment r02 = this.baseFragment;
        if (r02 == null) goto L12;
        AndroidUtilities.setLightStatusBar(this, r02.isLightStatusBar());
        return;
    L12:
        return;
    L5:
        if (r0.getTag() == null) goto L8;
        AndroidUtilities.setLightStatusBar(this, isLightStatusBar());
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        int r0 = 0;
    L4:
        if (r0 >= this.viewPager.getChildCount()) goto L12;
        ViewPage r1 = (ViewPage) this.viewPager.getChildAt(r0);
        if (r1.position != this.selectedPosition) goto L11;
        if ((r1.topView instanceof BaseListPageView) == false) goto L11;
        return !((BaseListPageView) r1).recyclerListView.canScrollVertically(-1);
    L11:
        r0 = r0 + 1;
        goto L4
    L12:
        return true;
    }

    public void checkTopOffset() {
        int r0 = -1;
        boolean r1 = false;
        int r2 = -1;
        int r3 = 0;
    L4:
        if (r3 >= this.viewPager.getChildCount()) goto L26;
        ViewPage r4 = (ViewPage) this.viewPager.getChildAt(r3);
        if (r4.position != this.selectedPosition) goto L16;
        View r5 = r4.topView;
        if ((r5 instanceof BaseListPageView) == false) goto L16;
        View r02 = ((BaseListPageView) r5).layoutManager.findViewByPosition(0);
        if (r02 != null) goto L12;
    L11:
        r0 = 0;
        goto L16
    L12:
        r0 = r02.getTop();
        if (r0 < 0) goto L11;
    L16:
        if (r4.position != this.toPosition) goto L25;
        View r42 = r4.topView;
        if ((r42 instanceof BaseListPageView) == false) goto L25;
        View r22 = ((BaseListPageView) r42).layoutManager.findViewByPosition(0);
        if (r22 != null) goto L22;
    L21:
        r2 = 0;
        goto L25
    L22:
        r2 = r22.getTop();
        if (r2 < 0) goto L21;
    L25:
        r3 = r3 + 1;
        goto L4
    L26:
        int r32 = this.topGlobalOffset;
        if (r0 < 0) goto L29;
        float r52 = 1.0f - this.progress;
        r32 = Math.min(r32, (int) ((r0 * r52) + (r32 * (1.0f - r52))));
    L29:
        if (r2 < 0) goto L31;
        float r03 = this.progress;
        r32 = Math.min(r32, (int) ((r2 * r03) + (this.topGlobalOffset * (1.0f - r03))));
    L31:
        this.closeLayout.setAlpha(1.0f - this.progressToGradient);
        float r04 = this.progressToFullscreenView;
        FrameLayout r23 = this.closeLayout;
        if (r04 != 1.0f) goto L34;
        r23.setVisibility(4);
    L35:
        FrameLayout r05 = this.content;
        if (this.fullscreenNext == false) goto L39;
        int r24 = r05.getMeasuredWidth();
    L40:
        r05.setTranslationX(r24 * this.progressToGradient);
        if (r32 == this.topCurrentOffset) goto L66;
        this.topCurrentOffset = r32;
        int r06 = 0;
    L44:
        if (r06 >= this.viewPager.getChildCount()) goto L49;
        if (((ViewPage) this.viewPager.getChildAt(r06)).topViewOnFullHeight == true) goto L48;
        this.viewPager.getChildAt(r06).setTranslationY(this.topCurrentOffset);
    L48:
        r06 = r06 + 1;
        goto L44
    L49:
        this.content.setTranslationY(this.topCurrentOffset);
        this.closeLayout.setTranslationY(this.topCurrentOffset);
        this.containerView.invalidate();
        int r07 = this.topCurrentOffset;
        if (this.startType != 40) goto L52;
        float r25 = 5.0f;
    L54:
        if (r07 >= AndroidUtilities.dp(r25)) goto L56;
        r1 = true;
    L56:
        AndroidUtilities.updateViewVisibilityAnimated(this.actionBar, r1, 1.0f, true);
        return;
    L52:
        r25 = 30.0f;
        goto L54
    L66:
        return;
    L39:
        r24 = -r05.getMeasuredWidth();
        goto L40
    L34:
        r23.setVisibility(0);
        goto L35
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.billingProductDetailsUpdated) goto L5;
    L15:
        setButtonText();
        return;
    L5:
        if (r1 == NotificationCenter.premiumPromoUpdated) goto L15;
        if (r1 != NotificationCenter.currentUserPremiumStatusChanged) goto L17;
        boolean r12 = UserConfig.getInstance(this.currentAccount).isPremium();
        PremiumButtonView r0 = this.premiumButtonView;
        if (r12 == false) goto L13;
        r0.setOverlayText(LocaleController.getString(R.string.OK), false, true);
        return;
    L13:
        r0.clearOverlayText();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.billingProductDetailsUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.premiumPromoUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{16});
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        if (this.viewPager == null) goto L27;
        int r1 = 0;
    L6:
        if (r1 >= this.viewPager.getChildCount()) goto L18;
        View r2 = ((ViewPage) this.viewPager.getChildAt(r1)).topView;
        if ((r2 instanceof VideoScreenPreview) == false) goto L15;
        ((VideoScreenPreview) r2).onDestroy();     // Catch: Exception -> L12
    L17:
        r1 = r1 + 1;
    L12:
        e = move-exception;
        FileLog.e(e);
        goto L17
    L15:
        if ((r2 instanceof PremiumStickersPreviewRecycler) == false) goto L17;
        ((PremiumStickersPreviewRecycler) r2).autoPlayEnabled = false;
        goto L17
    L18:
        this.viewPager = null;
        return;
    }

    public View getViewForPosition(Context r7, int r8) {
        PremiumPreviewFragment.PremiumFeatureData r82 = this.premiumFeatures.get(r8);
        int r0 = r82.type;
        if (r0 != 0) goto L7;
        DoubleLimitsPageView r83 = new DoubleLimitsPageView(r7, this.resourcesProvider);
        r83.recyclerListView.setOnScrollListener(new AnonymousClass9(this));
        return r83;
    L7:
        if (r0 == 14) goto L20;
        if (r0 == 28) goto L20;
        if (r0 != 5) goto L15;
        return new AnonymousClass11(this, r7, this.currentAccount);
    L15:
        if (r0 != 10) goto L19;
        return new PremiumAppIconsPreviewView(r7, this.resourcesProvider);
    L19:
        return new VideoScreenPreview(r7, this.svgIcon, this.currentAccount, r82.type, this.resourcesProvider);
    L20:
        if (r0 != 28) goto L22;
        int r72 = 1;
    L23:
        FeaturesPageView r84 = new FeaturesPageView(r7, r72, this.resourcesProvider);
        r84.recyclerListView.setOnScrollListener(new AnonymousClass10(this));
        return r84;
    L22:
        r72 = 0;
        goto L23
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onCreate(Bundle r11) {
        super.onCreate(r11);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.billingProductDetailsUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.premiumPromoUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        AnonymousClass7 r112 = new AnonymousClass7(this, getContext());
        this.actionBar = r112;
        r112.setBackgroundColor(getThemedColor(Theme.key_dialogBackground));
        this.actionBar.setTitleColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        this.actionBar.setItemsBackgroundColor(getThemedColor(Theme.key_actionBarActionModeDefaultSelector), false);
        ActionBar r113 = this.actionBar;
        int r0 = Theme.key_actionBarActionModeDefaultIcon;
        r113.setItemsColor(getThemedColor(r0), false);
        this.actionBar.setItemsColor(getThemedColor(r0), true);
        this.actionBar.setCastShadows(true);
        this.actionBar.setExtraHeight(AndroidUtilities.dp(2.0f));
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass8(this));
        this.containerView.addView(this.actionBar, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f));
        FrameLayout.LayoutParams r114 = (FrameLayout.LayoutParams) this.actionBar.getLayoutParams();
        r114.topMargin = (-this.backgroundPaddingTop) - AndroidUtilities.dp(2.0f);
        AndroidUtilities.updateViewVisibilityAnimated(this.actionBar, false, 1.0f, false);
        if (this.premiumFeatures.get(this.selectedPosition).type != 14) goto L7;
        this.actionBar.setTitle(LocaleController.getString(R.string.UpgradedStories));
        this.actionBar.requestLayout();
        return;
    L7:
        if (this.premiumFeatures.get(this.selectedPosition).type != 28) goto L10;
        this.actionBar.setTitle(LocaleController.getString(R.string.TelegramBusiness));
        this.actionBar.requestLayout();
        return;
    L10:
        int r115 = this.premiumFeatures.get(this.selectedPosition).type;
        ActionBar r02 = this.actionBar;
        if (r115 != 40) goto L14;
        r02.setTitle(LocaleController.getString(R.string.FeaturePreviewGifts));
        this.actionBar.requestLayout();
        return;
    L14:
        r02.setTitle(LocaleController.getString(R.string.DoubledLimits));
        this.actionBar.requestLayout();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean onCustomOpenAnimation() {
        if (this.viewPager.getChildCount() <= 0) goto L8;
        View r2 = ((ViewPage) this.viewPager.getChildAt(0)).topView;
        if ((r2 instanceof PremiumAppIconsPreviewView) == false) goto L8;
        PremiumAppIconsPreviewView r22 = (PremiumAppIconsPreviewView) r2;
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{r0.getMeasuredWidth(), 0.0f});
        r22.setOffset(r0.getMeasuredWidth());
        this.enterAnimationIsRunning = true;
        r1.addUpdateListener(new AnonymousClass12(this, r22));
        r1.addListener(new AnonymousClass13(this, r22));
        r1.setDuration(500);
        r1.setStartDelay(100);
        r1.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r1.start();
    L8:
        return super.onCustomOpenAnimation();
    }

    public PremiumFeatureBottomSheet setForceAbout() {
        this.forceAbout = true;
        this.premiumButtonView.clearOverlayText();
        setButtonText();
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{16});
    }
}
