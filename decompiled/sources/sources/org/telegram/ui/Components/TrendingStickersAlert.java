package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.SizeNotifierFrameLayout;

/* loaded from: classes7.dex */
public class TrendingStickersAlert extends BottomSheet {
    private final AlertContainerView alertContainerView;
    private final TrendingStickersLayout layout;
    private int scrollOffsetY;
    private final GradientDrawable shapeDrawable;
    private final int topOffset;

    /* renamed from: org.telegram.ui.Components.TrendingStickersAlert$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerView.OnScrollListener {
        private int scrolledY;
        final /* synthetic */ TrendingStickersAlert this$0;

        public AnonymousClass1(TrendingStickersAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 0) goto L5;
            this.scrolledY = 0;
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            this.scrolledY += r3;
            if (r1.getScrollState() == 1) goto L5;
        L10:
            if (r3 == 0) goto L13;
            TrendingStickersAlert.m13434$$Nest$mupdateLayout(this.this$0);
            return;
        L13:
            return;
        L5:
            if (Math.abs(this.scrolledY) <= AndroidUtilities.dp(96.0f)) goto L10;
            View r12 = TrendingStickersAlert.m13430$$Nest$fgetlayout(this.this$0).findFocus();
            if (r12 != null) goto L9;
            r12 = TrendingStickersAlert.m13430$$Nest$fgetlayout(this.this$0);
        L9:
            AndroidUtilities.hideKeyboard(r12);
            goto L10
        }
    }

    public class AlertContainerView extends SizeNotifierFrameLayout {
        private boolean gluedToTop;
        private boolean ignoreLayout;
        private final Paint paint;
        private float[] radii;
        private float statusBarAlpha;
        private ValueAnimator statusBarAnimator;
        private boolean statusBarOpen;
        private boolean statusBarVisible;
        final /* synthetic */ TrendingStickersAlert this$0;

        /* renamed from: org.telegram.ui.Components.TrendingStickersAlert$AlertContainerView$1, reason: invalid class name */
        public class AnonymousClass1 implements SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate {
            private boolean lastIsWidthGreater;
            private int lastKeyboardHeight;
            final /* synthetic */ AlertContainerView this$1;
            final /* synthetic */ TrendingStickersAlert val$this$0;

            public AnonymousClass1(AlertContainerView r1, TrendingStickersAlert r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.Components.SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate
            public void onSizeChanged(int r2, boolean r3) {
                if (this.lastKeyboardHeight == r2) goto L5;
            L6:
                this.lastKeyboardHeight = r2;
                this.lastIsWidthGreater = r3;
                if (r2 > AndroidUtilities.dp(20.0f)) goto L9;
                return;
            L9:
                if (AlertContainerView.m13436$$Nest$fgetgluedToTop(this.this$1) == true) goto L14;
                this.this$1.this$0.setAllowNestedScroll(false);
                AlertContainerView.m13437$$Nest$fputgluedToTop(this.this$1, true);
                return;
            L14:
                return;
            L5:
                if (this.lastIsWidthGreater != r3) goto L6;
            }
        }

        /* renamed from: $r8$lambda$SA65Q87Km0hJGJDzgv-8JTCx1IA, reason: not valid java name */
        public static /* synthetic */ void m13435$r8$lambda$SA65Q87Km0hJGJDzgv8JTCx1IA(AlertContainerView r0, ValueAnimator r1) {
            r0.lambda$setStatusBarVisible$0(r1);
        }

        /* renamed from: -$$Nest$fgetgluedToTop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13436$$Nest$fgetgluedToTop(AlertContainerView r0) {
            return r0.gluedToTop;
        }

        /* renamed from: -$$Nest$fputgluedToTop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13437$$Nest$fputgluedToTop(AlertContainerView r0, boolean r1) {
            r0.gluedToTop = r1;
        }

        public AlertContainerView(TrendingStickersAlert r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.paint = new Paint(1);
            this.gluedToTop = false;
            this.ignoreLayout = false;
            this.statusBarVisible = false;
            this.statusBarAlpha = 0.0f;
            this.radii = new float[8];
            setWillNotDraw(false);
            setPadding(TrendingStickersAlert.access$000(r3), 0, TrendingStickersAlert.access$100(r3), 0);
            setDelegate(new AnonymousClass1(this, r3));
        }

        private float getFraction() {
            return Math.min(1.0f, Math.max(0.0f, TrendingStickersAlert.m13431$$Nest$fgetscrollOffsetY(this.this$0) / (TrendingStickersAlert.m13433$$Nest$fgettopOffset(this.this$0) * 2.0f)));
        }

        private /* synthetic */ void lambda$setStatusBarVisible$0(ValueAnimator r1) {
            this.statusBarAlpha = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        private void setStatusBarVisible(boolean r7, boolean r8) {
            if (this.statusBarVisible == r7) goto L23;
            ValueAnimator r0 = this.statusBarAnimator;
            if (r0 == null) goto L7;
            r0.cancel();
        L7:
            this.statusBarVisible = r7;
            float r02 = 0.0f;
            if (r8 == false) goto L19;
            ValueAnimator r82 = this.statusBarAnimator;
            float r2 = this.statusBarAlpha;
            if (r82 != null) goto L14;
            if (r7 == false) goto L13;
            r02 = 1.0f;
        L13:
            ValueAnimator r72 = ValueAnimator.ofFloat(new float[]{r2, r02});
            this.statusBarAnimator = r72;
            r72.addUpdateListener(new TrendingStickersAlert$AlertContainerView$$ExternalSyntheticLambda1(this));
            this.statusBarAnimator.setDuration(200);
        L17:
            this.statusBarAnimator.start();
            return;
        L14:
            if (r7 == false) goto L16;
            r02 = 1.0f;
        L16:
            r82.setFloatValues(new float[]{r2, r02});
            goto L17
        L19:
            if (r7 == false) goto L21;
            r02 = 1.0f;
        L21:
            this.statusBarAlpha = r02;
            invalidate();
            return;
        }

        private void updateLightStatusBar(boolean r7) {
            if (this.statusBarOpen == r7) goto L16;
            this.statusBarOpen = r7;
            boolean r2 = false;
            if (AndroidUtilities.computePerceivedBrightness(TrendingStickersAlert.access$1000(this.this$0, Theme.key_dialogBackground)) <= 0.721f) goto L7;
            boolean r0 = true;
        L9:
            if (AndroidUtilities.computePerceivedBrightness(Theme.blendOver(TrendingStickersAlert.access$1100(this.this$0, Theme.key_actionBarDefault), 855638016)) <= 0.721f) goto L11;
            r2 = true;
        L11:
            if (r7 == true) goto L14;
            r0 = r2;
        L14:
            AndroidUtilities.setLightStatusBar(this.this$0.getWindow(), r0);
            return;
        L7:
            r0 = false;
            goto L9
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r12) {
            float r0 = getFraction();
            boolean r3 = false;
            if (r0 == 0.0f) goto L5;
        L7:
            boolean r2 = false;
        L8:
            setStatusBarVisible(r2, true);
            if (this.statusBarAlpha <= 0.5f) goto L11;
            r3 = true;
        L11:
            updateLightStatusBar(r3);
            if (this.statusBarAlpha <= 0.0f) goto L14;
            this.paint.setColor(TrendingStickersAlert.access$1200(this.this$0, Theme.key_dialogBackground));
            Canvas r5 = r12;
            r5.drawRect(TrendingStickersAlert.access$1300(this.this$0), AndroidUtilities.lerp(r2, -AndroidUtilities.statusBarHeight, this.statusBarAlpha), getMeasuredWidth() - TrendingStickersAlert.access$1400(this.this$0), (int) Math.max(0.0f, ((TrendingStickersAlert.m13431$$Nest$fgetscrollOffsetY(this.this$0) + (TrendingStickersAlert.m13433$$Nest$fgettopOffset(this.this$0) * (1.0f - getFraction()))) + AndroidUtilities.dp(24.0f)) + (TrendingStickersAlert.m13430$$Nest$fgetlayout(this.this$0).getTranslationY() + (AndroidUtilities.statusBarHeight - TrendingStickersAlert.m13433$$Nest$fgettopOffset(this.this$0)))), this.paint);
        L15:
            super.dispatchDraw(r5);
            r5.save();
            r5.translate(0.0f, (TrendingStickersAlert.m13430$$Nest$fgetlayout(this.this$0).getTranslationY() + AndroidUtilities.statusBarHeight) - TrendingStickersAlert.m13433$$Nest$fgettopOffset(this.this$0));
            int r122 = AndroidUtilities.dp(36.0f);
            int r1 = AndroidUtilities.dp(4.0f);
            int r22 = (int) ((r1 * 2.0f) * (1.0f - r0));
            TrendingStickersAlert.m13432$$Nest$fgetshapeDrawable(this.this$0).setCornerRadius(AndroidUtilities.dp(2.0f));
            TrendingStickersAlert.m13432$$Nest$fgetshapeDrawable(this.this$0).setColor(ColorUtils.setAlphaComponent(TrendingStickersAlert.access$1500(this.this$0, Theme.key_sheet_scrollUp), (int) (Color.alpha(r3) * r0)));
            TrendingStickersAlert.m13432$$Nest$fgetshapeDrawable(this.this$0).setBounds((getWidth() - r122) / 2, (TrendingStickersAlert.m13431$$Nest$fgetscrollOffsetY(this.this$0) + AndroidUtilities.dp(10.0f)) + r22, (getWidth() + r122) / 2, ((TrendingStickersAlert.m13431$$Nest$fgetscrollOffsetY(this.this$0) + AndroidUtilities.dp(10.0f)) + r22) + r1);
            TrendingStickersAlert.m13432$$Nest$fgetshapeDrawable(this.this$0).draw(r5);
            r5.restore();
            return;
        L14:
            r5 = r12;
            goto L15
        L5:
            if (this.this$0.isDismissed() == true) goto L7;
            r2 = true;
            goto L8
        }

        @Override // android.view.View
        public float getTranslationY() {
            return TrendingStickersAlert.m13430$$Nest$fgetlayout(this.this$0).getTranslationY();
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration r3) {
            super.onConfigurationChanged(r3);
            AndroidUtilities.runOnUIThread(new TrendingStickersAlert$AlertContainerView$$ExternalSyntheticLambda0(this), 200);
        }

        @Override // android.view.View
        public void onDraw(Canvas r11) {
            TrendingStickersAlert.m13434$$Nest$mupdateLayout(this.this$0);
            super.onDraw(r11);
            float r0 = getFraction();
            int r1 = (int) (TrendingStickersAlert.m13433$$Nest$fgettopOffset(this.this$0) * (1.0f - r0));
            int r3 = AndroidUtilities.statusBarHeight - TrendingStickersAlert.m13433$$Nest$fgettopOffset(this.this$0);
            r11.save();
            r11.translate(0.0f, TrendingStickersAlert.m13430$$Nest$fgetlayout(this.this$0).getTranslationY() + r3);
            Drawable r4 = TrendingStickersAlert.access$500(this.this$0);
            int r6 = (TrendingStickersAlert.m13431$$Nest$fgetscrollOffsetY(this.this$0) - TrendingStickersAlert.access$400(this.this$0)) + r1;
            int r7 = getMeasuredWidth();
            int r8 = getMeasuredHeight();
            if (r3 >= 0) goto L5;
            int r32 = -r3;
        L6:
            r4.setBounds(0, r6, r7, r8 + r32);
            TrendingStickersAlert.access$600(this.this$0).draw(r11);
            if (r0 > 0.0f) goto L9;
        L11:
            r11.restore();
            return;
        L9:
            if (r0 >= 1.0f) goto L11;
            float r2 = AndroidUtilities.dp(12.0f) * r0;
            TrendingStickersAlert.m13432$$Nest$fgetshapeDrawable(this.this$0).setColor(TrendingStickersAlert.access$700(this.this$0, Theme.key_dialogBackground));
            float[] r02 = this.radii;
            r02[3] = r2;
            r02[2] = r2;
            r02[1] = r2;
            r02[0] = r2;
            TrendingStickersAlert.m13432$$Nest$fgetshapeDrawable(this.this$0).setCornerRadii(this.radii);
            TrendingStickersAlert.m13432$$Nest$fgetshapeDrawable(this.this$0).setBounds(TrendingStickersAlert.access$800(this.this$0), TrendingStickersAlert.m13431$$Nest$fgetscrollOffsetY(this.this$0) + r1, getWidth() - TrendingStickersAlert.access$900(this.this$0), (TrendingStickersAlert.m13431$$Nest$fgetscrollOffsetY(this.this$0) + r1) + AndroidUtilities.dp(24.0f));
            TrendingStickersAlert.m13432$$Nest$fgetshapeDrawable(this.this$0).draw(r11);
            goto L11
        L5:
            r32 = 0;
            goto L6
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L11;
            if (TrendingStickersAlert.m13431$$Nest$fgetscrollOffsetY(this.this$0) == 0) goto L11;
            if (r3.getY() >= TrendingStickersAlert.m13431$$Nest$fgetscrollOffsetY(this.this$0)) goto L11;
            this.this$0.dismiss();
            return true;
        L11:
            return super.onInterceptTouchEvent(r3);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r8, int r9, int r10, int r11, int r12) {
            int r0 = AndroidUtilities.statusBarHeight;
            int r1 = View.MeasureSpec.getSize(getMeasuredHeight()) - r0;
            int r2 = measureKeyboardHeight();
            int r13 = (int) ((r1 + r2) * 0.2f);
            this.ignoreLayout = true;
            int r4 = AndroidUtilities.dp(20.0f);
            TrendingStickersAlert r5 = this.this$0;
            if (r2 <= r4) goto L5;
            TrendingStickersAlert.m13430$$Nest$fgetlayout(r5).glueToTop(true);
            this.this$0.setAllowNestedScroll(false);
            this.gluedToTop = true;
        L6:
            TrendingStickersAlert.m13430$$Nest$fgetlayout(this.this$0).setContentViewPaddingTop(r13);
            if (getPaddingTop() == r0) goto L9;
            setPadding(TrendingStickersAlert.access$200(this.this$0), r0, TrendingStickersAlert.access$300(this.this$0), 0);
        L9:
            this.ignoreLayout = false;
            super.onLayout(r8, r9, r10, r11, r12);
            return;
        L5:
            TrendingStickersAlert.m13430$$Nest$fgetlayout(r5).glueToTop(false);
            this.this$0.setAllowNestedScroll(true);
            this.gluedToTop = false;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.isDismissed() == false) goto L5;
            return false;
        L5:
            if (super.onTouchEvent(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == true) goto L6;
            super.requestLayout();
            return;
        }

        @Override // android.view.View
        public void setTranslationY(float r2) {
            TrendingStickersAlert.m13430$$Nest$fgetlayout(this.this$0).setTranslationY(r2);
            invalidate();
        }
    }

    /* renamed from: -$$Nest$fgetlayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TrendingStickersLayout m13430$$Nest$fgetlayout(TrendingStickersAlert r0) {
        return r0.layout;
    }

    /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13431$$Nest$fgetscrollOffsetY(TrendingStickersAlert r0) {
        return r0.scrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetshapeDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GradientDrawable m13432$$Nest$fgetshapeDrawable(TrendingStickersAlert r0) {
        return r0.shapeDrawable;
    }

    /* renamed from: -$$Nest$fgettopOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13433$$Nest$fgettopOffset(TrendingStickersAlert r0) {
        return r0.topOffset;
    }

    /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13434$$Nest$mupdateLayout(TrendingStickersAlert r0) {
        r0.updateLayout();
    }

    public TrendingStickersAlert(Context r2, BaseFragment r3, TrendingStickersLayout r4, Theme.ResourcesProvider r5) {
        super(r2, true, r5);
        this.topOffset = AndroidUtilities.dp(12.0f);
        this.shapeDrawable = new GradientDrawable();
        AlertContainerView r52 = new AlertContainerView(this, r2);
        this.alertContainerView = r52;
        r52.addView(r4, LayoutHelper.createFrame(-1, -1.0f));
        this.containerView = r52;
        this.layout = r4;
        r4.setParentFragment(r3);
        r4.setOnScrollListener(new AnonymousClass1(this));
    }

    public static /* synthetic */ int access$000(TrendingStickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$100(TrendingStickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1000(TrendingStickersAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1100(TrendingStickersAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1200(TrendingStickersAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1300(TrendingStickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1400(TrendingStickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1500(TrendingStickersAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$200(TrendingStickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$300(TrendingStickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$400(TrendingStickersAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ Drawable access$500(TrendingStickersAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ Drawable access$600(TrendingStickersAlert r0) {
        return r0.shadowDrawable;
    }

    public static /* synthetic */ int access$700(TrendingStickersAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$800(TrendingStickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$900(TrendingStickersAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    private void updateLayout() {
        if (this.layout.update() == false) goto L6;
        this.scrollOffsetY = this.layout.getContentTopOffset();
        this.containerView.invalidate();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        this.layout.recycle();
        setHeavyOperationsEnabled(true);
    }

    public TrendingStickersLayout getLayout() {
        return this.layout;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r0 = new ArrayList();
        TrendingStickersLayout r1 = this.layout;
        Objects.requireNonNull(r1);
        r1.getThemeDescriptions(r0, new TrendingStickersAlert$$ExternalSyntheticLambda0(r1));
        r0.add(new ThemeDescription(this.alertContainerView, 0, null, null, new Drawable[]{this.shadowDrawable}, null, Theme.key_dialogBackground));
        r0.add(new ThemeDescription(this.alertContainerView, 0, null, null, null, null, Theme.key_sheet_scrollUp));
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void setAllowNestedScroll(boolean r1) {
        this.allowNestedScroll = r1;
    }

    public void setHeavyOperationsEnabled(boolean r2) {
        NotificationCenter r1 = NotificationCenter.getGlobalInstance();
        if (r2 == false) goto L5;
        int r22 = NotificationCenter.startAllHeavyOperations;
    L6:
        r1.postNotificationName(r22, new Object[]{2});
        return;
    L5:
        r22 = NotificationCenter.stopAllHeavyOperations;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        setHeavyOperationsEnabled(false);
    }
}
