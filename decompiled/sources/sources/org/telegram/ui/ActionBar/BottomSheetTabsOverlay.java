package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.PixelCopy;
import android.view.Surface;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.zxing.common.detector.MathUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotFullscreenButtons$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BottomSheetTabs;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Text;
import org.telegram.ui.GradientClip;
import org.telegram.ui.bots.BotWebViewSheet;

/* loaded from: classes3.dex */
public class BottomSheetTabsOverlay extends View {
    private OverlayAccessibilityHelper accessibilityHelper;
    private View actionBarLayout;
    private final AnimatedFloat animatedCount;
    private ValueAnimator animator;
    private Bitmap blurBitmap;
    private Paint blurBitmapPaint;
    private BitmapShader blurBitmapShader;
    private Matrix blurMatrix;
    private final Path clipPath;
    private final RectF clipRect;
    private Drawable closeAllButtonBackground;
    private boolean closeAllButtonBackgroundDark;
    private Text closeAllButtonText;
    private float dismissProgress;
    private Sheet dismissingSheet;
    private BottomSheetTabs.TabDrawable dismissingTab;
    private GradientClip gradientClip;
    private boolean hitCloseAllButton;
    private boolean horizontallySwiping;
    public boolean isOpen;
    private float lastY;
    private final int maximumVelocity;
    private final int minimumVelocity;
    private int navigationBarInset;
    public float offset;
    private ValueAnimator openAnimator;
    private float openProgress;
    private float openingProgress;
    private Sheet openingSheet;
    private BottomSheetTabs.TabDrawable openingTab;
    private float openingTabScroll;
    private final int[] pos;
    private final int[] pos2;
    private final int[] pos3;
    private TabPreview pressTab;
    private boolean pressTabClose;
    private final RectF rect;
    private final RectF rect2;
    private ValueAnimator scrollAnimator;
    private final OverScroller scroller;
    private boolean slowerDismiss;
    private long startTime;
    private float startX;
    private float startY;
    private final ArrayList<TabPreview> tabs;
    private BottomSheetTabs tabsView;
    private final RectF tabsViewBounds;
    private VelocityTracker velocityTracker;
    private boolean verticallyScrolling;

    /* renamed from: org.telegram.ui.ActionBar.BottomSheetTabsOverlay$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ BottomSheetTabsOverlay this$0;
        final /* synthetic */ Sheet val$sheet;
        final /* synthetic */ BottomSheetTabs.WebTabData val$tab;

        public static /* synthetic */ void $r8$lambda$GCfik7AYPJcOu1GrqTlmVf7Z84w(BottomSheetTabs.WebTabData r0, Sheet r1, Bitmap r2) {
            r0.previewBitmap = r2;
            r1.mo6194getWindowView().setDrawingFromOverlay(false);
            r1.release();
        }

        public AnonymousClass2(BottomSheetTabsOverlay r1, BottomSheetTabs.WebTabData r2, Sheet r3) {
            this.this$0 = r1;
            this.val$tab = r2;
            this.val$sheet = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r7) {
            if (BottomSheetTabsOverlay.m5734$$Nest$fgetanimator(this.this$0) != null) goto L5;
        L7:
            BottomSheetTabs.WebTabData r72 = this.val$tab;
            View r0 = r72.webView;
            if (r0 != null) goto L11;
            r0 = r72.view2;
        L11:
            if (r0 != null) goto L13;
        L23:
            this.val$sheet.mo6194getWindowView().setDrawingFromOverlay(false);
            this.val$sheet.release();
            BottomSheetTabsOverlay.m5741$$Nest$fputdismissingSheet(this.this$0, null);
            BottomSheetTabsOverlay.m5742$$Nest$fputdismissingTab(this.this$0, null);
            this.this$0.invalidate();
            return;
        L13:
            if (r72.previewBitmap != null) goto L23;
            int r2 = r72.viewWidth;
            if (r2 <= 0) goto L23;
            int r3 = r72.viewHeight;
            if (r3 <= 0) goto L23;
            if (Build.VERSION.SDK_INT < 26) goto L22;
            BottomSheetTabsOverlay.renderHardwareViewToBitmap(r0, -r72.viewScroll, new BottomSheetTabsOverlay$2$$ExternalSyntheticLambda0(r72, this.val$sheet));
            BottomSheetTabsOverlay.m5741$$Nest$fputdismissingSheet(this.this$0, null);
            BottomSheetTabsOverlay.m5742$$Nest$fputdismissingTab(this.this$0, null);
            this.this$0.invalidate();
            return;
        L22:
            r72.previewBitmap = Bitmap.createBitmap(r2, r3, Bitmap.Config.RGB_565);
            Canvas r73 = new Canvas(this.val$tab.previewBitmap);
            r73.translate(0.0f, -this.val$tab.viewScroll);
            r0.draw(r73);
            goto L23
        L5:
            if (BottomSheetTabsOverlay.m5734$$Nest$fgetanimator(this.this$0).equals(r7) == false) goto L7;
            BottomSheetTabsOverlay.m5740$$Nest$fputanimator(this.this$0, null);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.BottomSheetTabsOverlay$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ BottomSheetTabsOverlay this$0;

        public AnonymousClass3(BottomSheetTabsOverlay r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (BottomSheetTabsOverlay.m5739$$Nest$fgettabsView(this.this$0) == null) goto L5;
            BottomSheetTabsOverlay.m5739$$Nest$fgettabsView(this.this$0).drawTabs = true;
            BottomSheetTabsOverlay.m5739$$Nest$fgettabsView(this.this$0).invalidate();
        L5:
            BottomSheetTabsOverlay r22 = this.this$0;
            if (r22.isOpen == false) goto L8;
            float r0 = 1.0f;
        L9:
            BottomSheetTabsOverlay.m5743$$Nest$fputopenProgress(r22, r0);
            this.this$0.invalidate();
            BottomSheetTabsOverlay r23 = this.this$0;
            if (r23.isOpen == false) goto L12;
            return;
        L12:
            if (BottomSheetTabsOverlay.m5737$$Nest$fgetopeningSheet(r23) != null) goto L16;
            BottomSheetTabsOverlay.m5744$$Nest$mclearTabs(this.this$0);
            return;
        L16:
            return;
        L8:
            r0 = 0.0f;
            goto L9
        }
    }

    public class OverlayAccessibilityHelper extends ExploreByTouchHelper {
        final /* synthetic */ BottomSheetTabsOverlay this$0;
        private final Rect tmpRect;

        public static /* synthetic */ void $r8$lambda$ZAyovw34PBSaAkiIZQaswTPs_sM(OverlayAccessibilityHelper r0, TabPreview r1, Boolean r2) {
            r0.lambda$onPerformActionForVirtualView$0(r1, r2);
        }

        public OverlayAccessibilityHelper(BottomSheetTabsOverlay r1, View r2) {
            this.this$0 = r1;
            super(r2);
            this.tmpRect = new Rect();
        }

        private /* synthetic */ void lambda$onPerformActionForVirtualView$0(TabPreview r1, Boolean r2) {
            if (r2.booleanValue() == false) goto L8;
            r1.animateDismiss(1.0f);
            if (BottomSheetTabsOverlay.m5739$$Nest$fgettabsView(this.this$0).getTabs().isEmpty() == false) goto L10;
            this.this$0.closeTabsView();
            return;
        L10:
            return;
        L8:
            r1.animateDismiss(0.0f);
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public int getVirtualViewAt(float r6, float r7) {
            if (BottomSheetTabsOverlay.m5736$$Nest$fgetopenProgress(this.this$0) >= 0.5f) goto L6;
            return -1;
        L6:
            if (BottomSheetTabsOverlay.m5735$$Nest$fgetcloseAllButtonBackground(this.this$0) != null) goto L8;
        L10:
            int r0 = BottomSheetTabsOverlay.m5738$$Nest$fgettabs(this.this$0).size() - 1;
        L11:
            if (r0 < 0) goto L26;
            TabPreview r2 = (TabPreview) BottomSheetTabsOverlay.m5738$$Nest$fgettabs(this.this$0).get(r0);
            if (Math.abs(r2.dismissProgress) >= 0.4f) goto L17;
            if (r2.clickBounds.contains(r6, r7) == false) goto L17;
            Rect r5 = r2.tabDrawable.closeRipple.getBounds();
            if (r5.isEmpty() == true) goto L25;
            RectF r1 = r2.clickBounds;
            if (r5.contains((int) (r6 - r1.left), (int) ((r7 - r1.top) - AndroidUtilities.dp(24.0f))) == false) goto L25;
            return r0 + CastStatusCodes.AUTHENTICATION_FAILED;
        L25:
            return r0 + 1000;
        L17:
            r0 = r0 - 1;
            goto L11
        L26:
            return -1;
        L8:
            if (BottomSheetTabsOverlay.m5735$$Nest$fgetcloseAllButtonBackground(this.this$0).getBounds().contains((int) r6, (int) r7) == false) goto L10;
            return 1;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void getVisibleVirtualViews(List<Integer> r5) {
            if (BottomSheetTabsOverlay.m5736$$Nest$fgetopenProgress(this.this$0) >= 0.5f) goto L6;
            return;
        L6:
            if (BottomSheetTabsOverlay.m5735$$Nest$fgetcloseAllButtonBackground(this.this$0) != null) goto L8;
        L10:
            int r0 = 0;
        L12:
            if (r0 >= BottomSheetTabsOverlay.m5738$$Nest$fgettabs(this.this$0).size()) goto L32;
            TabPreview r1 = (TabPreview) BottomSheetTabsOverlay.m5738$$Nest$fgettabs(this.this$0).get(r0);
            if (Math.abs(r1.dismissProgress) >= 0.4f) goto L24;
            if (r1.clickBounds.isEmpty() == true) goto L24;
            r5.add(Integer.valueOf(r0 + 1000));
            BottomSheetTabs.TabDrawable r12 = r1.tabDrawable;
            if (r12 == null) goto L24;
            if (r12.closeRipple.getBounds().isEmpty() == true) goto L24;
            r5.add(Integer.valueOf(r0 + CastStatusCodes.AUTHENTICATION_FAILED));
        L24:
            r0 = r0 + 1;
            goto L12
        L32:
            return;
        L8:
            if (BottomSheetTabsOverlay.m5735$$Nest$fgetcloseAllButtonBackground(this.this$0).getBounds().isEmpty() == true) goto L10;
            r5.add(1);
            goto L10
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public boolean onPerformActionForVirtualView(int r3, int r4, Bundle r5) {
            if (r4 == 16) goto L6;
            return false;
        L6:
            if (r3 != 1) goto L13;
            if (BottomSheetTabsOverlay.m5739$$Nest$fgettabsView(this.this$0) == null) goto L10;
            BottomSheetTabsOverlay.m5739$$Nest$fgettabsView(this.this$0).removeAll();
        L10:
            this.this$0.closeTabsView();
            return true;
        L13:
            if (r3 < 2000) goto L16;
            int r32 = r3 - CastStatusCodes.AUTHENTICATION_FAILED;
            boolean r52 = true;
        L18:
            if (r32 >= 0) goto L20;
        L32:
            return false;
        L20:
            if (r32 >= BottomSheetTabsOverlay.m5738$$Nest$fgettabs(this.this$0).size()) goto L32;
            TabPreview r33 = (TabPreview) BottomSheetTabsOverlay.m5738$$Nest$fgettabs(this.this$0).get(r32);
            BottomSheetTabsOverlay r0 = this.this$0;
            if (r52 == false) goto L29;
            if (BottomSheetTabsOverlay.m5739$$Nest$fgettabsView(r0) == null) goto L27;
            BottomSheetTabsOverlay.m5739$$Nest$fgettabsView(this.this$0).removeTab(r33.tabData, new BottomSheetTabsOverlay$OverlayAccessibilityHelper$$ExternalSyntheticLambda0(this, r33));
        L27:
            return true;
        L29:
            if (BottomSheetTabsOverlay.m5739$$Nest$fgettabsView(r0) == null) goto L31;
            this.this$0.closeTabsView();
            r33.webView = null;
            BottomSheetTabsOverlay.m5739$$Nest$fgettabsView(this.this$0).openTab(r33.tabData);
        L31:
            return true;
        L16:
            if (r3 < 1000) goto L32;
            r32 = r3 - 1000;
            r52 = false;
            goto L18
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForVirtualView(int r9, AccessibilityNodeInfoCompat r10) {
            r10.setClassName("android.widget.Button");
            r10.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            if (r9 != 1) goto L11;
            Drawable r92 = BottomSheetTabsOverlay.m5735$$Nest$fgetcloseAllButtonBackground(this.this$0);
            Rect r2 = this.tmpRect;
            if (r92 == null) goto L7;
            r2.set(BottomSheetTabsOverlay.m5735$$Nest$fgetcloseAllButtonBackground(this.this$0).getBounds());
        L8:
            r10.setBoundsInParent(this.tmpRect);
            r10.setContentDescription(LocaleController.getString(R.string.BotCloseAllTabs));
            return;
        L7:
            r2.set(0, 0, 1, 1);
            r10.setVisibleToUser(false);
            goto L8
        L11:
            if (r9 < 2000) goto L14;
            int r93 = r9 - CastStatusCodes.AUTHENTICATION_FAILED;
            boolean r22 = true;
        L16:
            if (r93 >= 0) goto L18;
        L40:
            this.tmpRect.set(0, 0, 1, 1);
            r10.setBoundsInParent(this.tmpRect);
            r10.setVisibleToUser(false);
            return;
        L18:
            if (r93 >= BottomSheetTabsOverlay.m5738$$Nest$fgettabs(this.this$0).size()) goto L40;
            TabPreview r94 = (TabPreview) BottomSheetTabsOverlay.m5738$$Nest$fgettabs(this.this$0).get(r93);
            BottomSheetTabs.WebTabData r0 = r94.tabData;
            if (r0 != null) goto L23;
        L25:
            String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        L27:
            if (r22 == false) goto L34;
            Rect r23 = r94.tabDrawable.closeRipple.getBounds();
            RectF r3 = r94.clickBounds;
            int r4 = (int) (r3.left + r23.left);
            int r32 = (int) ((r3.top + AndroidUtilities.dp(24.0f)) + r23.top);
            RectF r95 = r94.clickBounds;
            this.tmpRect.set(r4, r32, (int) (r95.left + r23.right), (int) ((r95.top + AndroidUtilities.dp(24.0f)) + r23.bottom));
            r10.setBoundsInParent(this.tmpRect);
            if (TextUtils.isEmpty(r02) == false) goto L31;
            String r8 = LocaleController.getString(R.string.Close);
        L32:
            r10.setContentDescription(r8);
            return;
        L31:
            r8 = LocaleController.getString(R.string.Close) + ", " + r02;
            goto L32
        L34:
            Rect r24 = this.tmpRect;
            RectF r96 = r94.clickBounds;
            r24.set((int) r96.left, (int) r96.top, (int) r96.right, (int) r96.bottom);
            r10.setBoundsInParent(this.tmpRect);
            if (TextUtils.isEmpty(r02) == false) goto L37;
            String r82 = LocaleController.getString(R.string.Open);
        L38:
            r10.setContentDescription(r82);
            return;
        L37:
            r82 = LocaleController.getString(R.string.Open) + ", " + r02;
            goto L38
        L23:
            if (r0.getTitle() == null) goto L25;
            r02 = r94.tabData.getTitle();
            goto L27
        L14:
            if (r9 < 1000) goto L42;
            r93 = r9 - 1000;
            r22 = false;
            goto L16
        L42:
            this.tmpRect.set(0, 0, 1, 1);
            r10.setBoundsInParent(this.tmpRect);
            r10.setVisibleToUser(false);
        }
    }

    public interface Sheet {
        void dismiss(boolean r1);

        int getNavigationBarColor(int r1);

        /* renamed from: getWindowView */
        SheetView mo6194getWindowView();

        boolean hadDialog();

        void release();

        BottomSheetTabs.WebTabData saveState();

        boolean setDialog(BottomSheetTabDialog r1);

        default void setLastVisible(boolean r1) {
        }
    }

    /* loaded from: classes6.dex */
    public interface SheetView {
        float drawInto(Canvas r1, RectF r2, float r3, RectF r4, float r5, boolean r6);

        Context getContext();

        RectF getRect();

        void setDrawingFromOverlay(boolean r1);
    }

    public static class TabPreview {
        private final Paint backgroundPaint;
        private final Paint bitmapPaint;
        public final ButtonBounce bounce;
        public final RectF clickBounds;
        private final Path clipPath;
        private ValueAnimator dismissAnimator;
        public float dismissProgress;
        private final float[] dst;
        private final RadialGradient gradient;
        private final Matrix gradientMatrix;
        private final Paint gradientPaint;
        private final Matrix matrix;
        public final View parentView;
        private final Paint shadowPaint;
        private final float[] src;
        private final RectF tabBounds;
        public final BottomSheetTabs.WebTabData tabData;
        public final BottomSheetTabs.TabDrawable tabDrawable;
        public WebView webView;

        /* renamed from: org.telegram.ui.ActionBar.BottomSheetTabsOverlay$TabPreview$1, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ TabPreview this$0;
            final /* synthetic */ float val$dismissTo;

            public AnonymousClass1(TabPreview r1, float r2) {
                this.this$0 = r1;
                this.val$dismissTo = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                TabPreview r12 = this.this$0;
                r12.dismissProgress = this.val$dismissTo;
                View r0 = r12.parentView;
                if (r0 == null) goto L6;
                r0.invalidate();
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$XsrZJvjMTFjNgHkf5b0RznTprJ4(TabPreview r0, ValueAnimator r1) {
            r0.lambda$animateDismiss$0(r1);
        }

        /* renamed from: -$$Nest$fgetdst, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float[] m5745$$Nest$fgetdst(TabPreview r0) {
            return r0.dst;
        }

        /* renamed from: -$$Nest$fgetmatrix, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Matrix m5746$$Nest$fgetmatrix(TabPreview r0) {
            return r0.matrix;
        }

        /* renamed from: -$$Nest$fgetsrc, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float[] m5747$$Nest$fgetsrc(TabPreview r0) {
            return r0.src;
        }

        public TabPreview(View r12, BottomSheetTabs.WebTabData r13, BottomSheetTabs.TabDrawable r14) {
            this.clickBounds = new RectF();
            Paint r0 = new Paint(1);
            this.backgroundPaint = r0;
            this.matrix = new Matrix();
            this.src = new float[8];
            this.dst = new float[8];
            this.dismissProgress = 0.0f;
            this.shadowPaint = new Paint(1);
            this.tabBounds = new RectF();
            this.clipPath = new Path();
            this.bitmapPaint = new Paint(3);
            this.gradient = new RadialGradient(0.0f, 0.0f, 255.0f, new int[]{0, 805306368}, new float[]{0.5f, 1.0f}, Shader.TileMode.CLAMP);
            this.gradientMatrix = new Matrix();
            this.gradientPaint = new Paint(1);
            this.parentView = r12;
            this.tabData = r13;
            this.tabDrawable = r14;
            this.webView = null;
            this.bounce = new ButtonBounce(r12);
            r0.setColor(r13.backgroundColor);
        }

        private /* synthetic */ void lambda$animateDismiss$0(ValueAnimator r1) {
            this.dismissProgress = ((Float) r1.getAnimatedValue()).floatValue();
            View r0 = this.parentView;
            if (r0 == null) goto L6;
            r0.invalidate();
            return;
        }

        public void animateDismiss(float r6) {
            cancelDismissAnimator();
            ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{this.dismissProgress, r6});
            this.dismissAnimator = r0;
            r0.addUpdateListener(new BottomSheetTabsOverlay$TabPreview$$ExternalSyntheticLambda0(this));
            this.dismissAnimator.addListener(new AnonymousClass1(this, r6));
            float r62 = Math.abs(r6);
            ValueAnimator r02 = this.dismissAnimator;
            if (r62 >= 0.1f) goto L5;
            AndroidUtilities.applySpring(r02, 285.0d, 20.0d);
        L6:
            this.dismissAnimator.start();
            return;
        L5:
            r02.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            goto L6
        }

        public void cancelDismissAnimator() {
            ValueAnimator r0 = this.dismissAnimator;
            if (r0 == null) goto L6;
            r0.cancel();
            return;
        }

        public void draw(Canvas r20, RectF r21, boolean r22, float r23, float r24, float r25, float r26) {
            float r12 = r23 * Utilities.clamp(1.0f - ((Math.abs(this.dismissProgress) - 0.3f) / 0.7f), 1.0f, 0.0f);
            if (r12 > 0.0f) goto L5;
            return;
        L5:
            float r13 = 1.0f - r25;
            float r2 = r24 * r13;
            float r14 = AndroidUtilities.lerp(1.0f, 1.3f, r2);
            float r3 = ((AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.dp(50.0f)) * r25;
            r20.save();
            r20.rotate(this.dismissProgress * 20.0f, r21.centerX() + (AndroidUtilities.dp(50.0f) * this.dismissProgress), r21.bottom + AndroidUtilities.dp(350.0f));
            float r4 = this.bounce.getScale(0.01f);
            r20.scale(r4, r4, r21.centerX(), r21.centerY());
            float r42 = AndroidUtilities.lerp(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), r24);
            if (r22 == false) goto L9;
            this.shadowPaint.setColor(0);
            this.shadowPaint.setShadowLayer(AndroidUtilities.dp(30.0f), 0.0f, AndroidUtilities.dp(10.0f), Theme.multAlpha(536870912, (r12 * r24) * r13));
            r20.drawRoundRect(r21, r42, r42, this.shadowPaint);
            this.backgroundPaint.setAlpha((int) (r12 * 255.0f));
            r20.drawRoundRect(r21, r42, r42, this.backgroundPaint);
            r20.restore();
            return;
        L9:
            this.clipPath.rewind();
            this.clipPath.addRoundRect(r21, r42, r42, Path.Direction.CW);
            r20.save();
            this.shadowPaint.setColor(0);
            float r10 = r12 * r24;
            this.shadowPaint.setShadowLayer(AndroidUtilities.dp(30.0f), 0.0f, AndroidUtilities.dp(10.0f), Theme.multAlpha(536870912, r10 * r13));
            r20.drawPath(this.clipPath, this.shadowPaint);
            r20.clipPath(this.clipPath);
            float r15 = (r12 * 255.0f) * r24;
            int r6 = (int) r15;
            this.backgroundPaint.setAlpha(r6);
            r20.drawRoundRect(r21, r42, r42, this.backgroundPaint);
            r20.save();
            r20.translate(r21.left, (r21.top + (AndroidUtilities.dp(50.0f) * r14)) + r3);
            r20.scale(1.0f, AndroidUtilities.lerp(1.0f, 1.25f, r2));
            BottomSheetTabs.WebTabData r27 = this.tabData;
            if (r27 == null) goto L19;
            Object r28 = r27.previewNode;
            if (r28 == null) goto L19;
            if (Build.VERSION.SDK_INT < 29) goto L19;
            if (BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(r28).hasDisplayList() == false) goto L19;
            RenderNode r29 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(this.tabData.previewNode);
            float r32 = r21.width() / r29.getWidth();
            r20.scale(r32, r32);
            r29.setAlpha(r10);
            r20.drawRenderNode(r29);
        L18:
            float r102 = r3;
            float r18 = r42;
        L27:
            r20.restore();
            r20.save();
            this.gradientPaint.setAlpha((int) (r15 * r13));
            this.gradientMatrix.reset();
            float r210 = r21.height() / 255.0f;
            this.gradientMatrix.postScale(r210, r210);
            this.gradientMatrix.postTranslate(r21.centerX(), r21.top);
            this.gradient.setLocalMatrix(this.gradientMatrix);
            this.gradientPaint.setShader(this.gradient);
            r20.drawRect(r21, this.gradientPaint);
            r20.restore();
            this.tabBounds.set(r21);
            RectF r211 = this.tabBounds;
            r211.bottom = r211.top + Math.min(r21.height(), AndroidUtilities.dp(50.0f));
            this.tabBounds.offset(0.0f, r102);
            this.tabDrawable.setExpandProgress(r24);
            r20.scale(1.0f, r14, this.tabBounds.centerX(), this.tabBounds.top);
            this.tabDrawable.draw(r20, this.tabBounds, r18, r12 * r12, r26);
            r20.restore();
            r20.restore();
            return;
        L19:
            BottomSheetTabs.WebTabData r212 = this.tabData;
            if (r212 == null) goto L25;
            if (r212.previewBitmap == null) goto L25;
            float r213 = r21.width() / this.tabData.previewBitmap.getWidth();
            r20.scale(r213, r213);
            this.bitmapPaint.setAlpha(r6);
            r20.drawBitmap(this.tabData.previewBitmap, 0.0f, 0.0f, this.bitmapPaint);
        L25:
            if (this.webView == null) goto L18;
            float r214 = r21.width() / this.webView.getWidth();
            r20.scale(r214, r214);
            r18 = r42;
            r102 = r3;
            r20.saveLayerAlpha(0.0f, 0.0f, this.webView.getWidth(), this.webView.getHeight(), r6, 31);
            this.webView.draw(r20);
            r20.restore();
            goto L27
        }

        public boolean isPressed() {
            return this.bounce.isPressed();
        }

        public void setPressed(boolean r1) {
            this.bounce.setPressed(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$HTsAKLgJBWNXkqa2kxDVhJe9UtM(BottomSheetTabsOverlay r0, ValueAnimator r1) {
        r0.lambda$dismissSheet$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$LMRCuHQeSzdTIZan9hTansvC2lc(BottomSheetTabsOverlay r0, TabPreview r1, Boolean r2) {
        r0.lambda$dispatchTouchEvent$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$RvwWnJRtEvXrcMAgwMZkYCfqFus(BottomSheetTabsOverlay r0, ValueAnimator r1) {
        r0.lambda$scrollTo$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$XU58vQduemRbiaLID3mmX5TDEEs(Utilities.Callback r0, Bitmap r1, Surface r2, SurfaceTexture r3, int r4) {
        if (r4 != 0) goto L4;
        r0.run(r1);
    L5:
        r2.release();
        r3.release();
        return;
    L4:
        r1.recycle();
        r0.run(null);
        goto L5
    }

    public static /* synthetic */ void $r8$lambda$XYhORhJjs7Z2H8aPx6t1LyaiAgY(BottomSheetTabsOverlay r0, TabPreview r1, Boolean r2) {
        r0.lambda$dispatchTouchEvent$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$hXGnNPWO7I1wkl5xjJT0SgMoWSw(Sheet r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r1.mo6194getWindowView() == null) goto L8;
        r1.mo6194getWindowView().setDrawingFromOverlay(true);
        return;
    }

    public static /* synthetic */ void $r8$lambda$q8oaELqmUrifEmLsY5YXbLlymXk(BottomSheetTabsOverlay r0, ValueAnimator r1) {
        r0.lambda$animateOpen$6(r1);
    }

    /* renamed from: $r8$lambda$x-WSzfctxdH9hkQS_jlwqUKjkIw, reason: not valid java name */
    public static /* synthetic */ WindowInsetsCompat m5733$r8$lambda$xWSzfctxdH9hkQS_jlwqUKjkIw(BottomSheetTabsOverlay r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    /* renamed from: -$$Nest$fgetanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m5734$$Nest$fgetanimator(BottomSheetTabsOverlay r0) {
        return r0.animator;
    }

    /* renamed from: -$$Nest$fgetcloseAllButtonBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m5735$$Nest$fgetcloseAllButtonBackground(BottomSheetTabsOverlay r0) {
        return r0.closeAllButtonBackground;
    }

    /* renamed from: -$$Nest$fgetopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5736$$Nest$fgetopenProgress(BottomSheetTabsOverlay r0) {
        return r0.openProgress;
    }

    /* renamed from: -$$Nest$fgetopeningSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Sheet m5737$$Nest$fgetopeningSheet(BottomSheetTabsOverlay r0) {
        return r0.openingSheet;
    }

    /* renamed from: -$$Nest$fgettabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5738$$Nest$fgettabs(BottomSheetTabsOverlay r0) {
        return r0.tabs;
    }

    /* renamed from: -$$Nest$fgettabsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BottomSheetTabs m5739$$Nest$fgettabsView(BottomSheetTabsOverlay r0) {
        return r0.tabsView;
    }

    /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5740$$Nest$fputanimator(BottomSheetTabsOverlay r0, ValueAnimator r1) {
        r0.animator = r1;
    }

    /* renamed from: -$$Nest$fputdismissingSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5741$$Nest$fputdismissingSheet(BottomSheetTabsOverlay r0, Sheet r1) {
        r0.dismissingSheet = r1;
    }

    /* renamed from: -$$Nest$fputdismissingTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5742$$Nest$fputdismissingTab(BottomSheetTabsOverlay r0, BottomSheetTabs.TabDrawable r1) {
        r0.dismissingTab = r1;
    }

    /* renamed from: -$$Nest$fputopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5743$$Nest$fputopenProgress(BottomSheetTabsOverlay r0, float r1) {
        r0.openProgress = r1;
    }

    /* renamed from: -$$Nest$mclearTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5744$$Nest$mclearTabs(BottomSheetTabsOverlay r0) {
        r0.clearTabs();
    }

    public BottomSheetTabsOverlay(Context r8) {
        super(r8);
        this.animatedCount = new AnimatedFloat(this, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.tabsViewBounds = new RectF();
        this.tabs = new ArrayList();
        this.pos = new int[2];
        this.pos2 = new int[2];
        this.pos3 = new int[2];
        this.rect = new RectF();
        this.rect2 = new RectF();
        this.clipRect = new RectF();
        this.clipPath = new Path();
        setWillNotDraw(false);
        this.scroller = new OverScroller(r8);
        ViewConfiguration r82 = ViewConfiguration.get(r8);
        this.maximumVelocity = r82.getScaledMaximumFlingVelocity();
        this.minimumVelocity = r82.getScaledMinimumFlingVelocity();
        OverlayAccessibilityHelper r83 = new OverlayAccessibilityHelper(this, this);
        this.accessibilityHelper = r83;
        ViewCompat.setAccessibilityDelegate(this, r83);
        setImportantForAccessibility(2);
        ViewCompat.setOnApplyWindowInsetsListener(this, new BottomSheetTabsOverlay$$ExternalSyntheticLambda7(this));
    }

    private void animateOpen(boolean r4) {
        if (this.isOpen != r4) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.openAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        this.isOpen = r4;
        BottomSheetTabs r02 = this.tabsView;
        if (r02 == null) goto L11;
        r02.drawTabs = false;
        r02.invalidate();
    L11:
        setModalAccessibility(r4);
        invalidate();
        float r03 = this.openProgress;
        if (r4 == false) goto L14;
        float r42 = 1.0f;
    L15:
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r03, r42});
        this.openAnimator = r43;
        r43.addUpdateListener(new BottomSheetTabsOverlay$$ExternalSyntheticLambda1(this));
        this.openAnimator.addListener(new AnonymousClass3(this));
        this.openAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.openAnimator.setDuration(320);
        this.openAnimator.start();
        return;
    L14:
        r42 = 0.0f;
        goto L15
    }

    private void clearTabs() {
        this.tabs.clear();
    }

    private void drawDismissingTab(Canvas r15) {
        if (this.dismissingSheet == null) goto L9;
        getLocationOnScreen(this.pos2);
        this.tabsView.getLocationOnScreen(this.pos);
        this.tabsView.getTabBounds(this.rect, 0.0f);
        RectF r0 = this.rect;
        int r3 = this.pos[0];
        int[] r4 = this.pos2;
        r0.offset(r3 - r4[0], r1[1] - r4[1]);
        r15.save();
        r15.clipRect(0, 0, getMeasuredWidth(), getMeasuredHeight() - this.navigationBarInset);
        SheetView r1 = this.dismissingSheet.mo6194getWindowView();
        RectF r32 = this.rect;
        float r42 = this.dismissProgress;
        float r11 = r1.drawInto(r15, r32, r42, this.clipRect, r42, false);
        if (this.dismissingTab == null) goto L7;
        this.clipPath.rewind();
        this.clipPath.addRoundRect(this.clipRect, r11, r11, Path.Direction.CW);
        r15.save();
        r15.clipPath(this.clipPath);
        float r152 = this.clipRect.top - (AndroidUtilities.dp(50.0f) * (1.0f - this.dismissProgress));
        RectF r12 = this.rect;
        RectF r33 = this.clipRect;
        r12.set(r33.left, r152, r33.right, AndroidUtilities.dp(50.0f) + r152);
        this.tabsView.setupTab(this.dismissingTab);
        this.dismissingTab.draw(r15, this.rect, r11, this.dismissProgress, 1.0f);
        r15.restore();
    L7:
        r15.restore();
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void drawTabsPreview(Canvas r43) {
        float r9 = 0.0f;
        if (this.openProgress <= 0.0f) goto L5;
    L7:
        r43.save();
        View r2 = this.actionBarLayout;
        int[] r3 = this.pos;
        int r11 = 1;
        if (r2 == null) goto L10;
        r2.getLocationOnScreen(r3);
        getLocationOnScreen(this.pos2);
        RectF r22 = this.tabsViewBounds;
        int r4 = this.pos[0];
        int r6 = this.pos2[0];
        r22.set(r4 - r6, r3[1] - r5[1], (r4 - r6) + this.actionBarLayout.getWidth(), (this.pos[1] - this.pos2[1]) + this.actionBarLayout.getHeight());
    L11:
        r43.clipRect(this.tabsViewBounds);
        RectF r23 = this.tabsViewBounds;
        r43.translate(r23.left, r23.top);
        float r42 = this.tabsViewBounds.width();
        float r5 = this.tabsViewBounds.height();
        float r12 = 255.0f;
        if (this.blurBitmap == null) goto L14;
        this.blurMatrix.reset();
        float r24 = this.tabsViewBounds.width() / this.blurBitmap.getWidth();
        this.blurMatrix.postScale(r24, r24);
        this.blurBitmapShader.setLocalMatrix(this.blurMatrix);
        this.blurBitmapPaint.setAlpha((int) (this.openProgress * 255.0f));
        r43.drawRect(0.0f, 0.0f, r42, r5, this.blurBitmapPaint);
    L14:
        Canvas r1 = r43;
        r1.saveLayerAlpha(0.0f, 0.0f, r42, r5, 255, 31);
        float r15 = (AndroidUtilities.statusBarHeight + AndroidUtilities.dp(40.0f)) + AndroidUtilities.dp(55.0f);
        float r25 = AndroidUtilities.dp(68.0f);
        int r32 = (int) Math.min(AndroidUtilities.dp(340.0f), 0.95f * r42);
        if (AndroidUtilities.isTablet() == false) goto L17;
        float r44 = this.tabsViewBounds.height() * 0.5f;
    L18:
        int r45 = (int) r44;
        float r18 = r42 / 2.0f;
        float r62 = 0.0f;
        int r52 = 0;
    L19:
        float r8 = 1.0f;
        if (r52 >= this.tabs.size()) goto L26;
        if (this.tabs.get(r52).tabDrawable.index >= 0) goto L25;
        r8 = 0.0f;
    L25:
        r62 = r62 + r8;
        r52 = r52 + 1;
        goto L19
    L26:
        float r19 = this.animatedCount.set(r62);
        if (getScrollWindow() > 0.0f) goto L29;
        float r53 = 0.0f;
    L30:
        float r54 = AndroidUtilities.lerp(0.0f, 1.0f - Utilities.clamp(r53, 1.0f, 0.0f), this.openProgress);
        int r63 = -1;
        int r7 = 0;
    L32:
        if (r7 >= (this.tabs.size() + r11)) goto L105;
        if (r7 != this.tabs.size()) goto L41;
        if (r63 >= 0) goto L37;
    L40:
        float r26 = r25;
        int r29 = r32;
        int r28 = r45;
        float r92 = r54;
        int r10 = r7;
        float r242 = r8;
        int r222 = r11;
        float r21 = r12;
        int r112 = r63;
    L103:
        r63 = r112;
    L104:
        r7 = r10 + 1;
        r54 = r92;
        r12 = r21;
        r11 = r222;
        r8 = r242;
        r25 = r26;
        r45 = r28;
        r32 = r29;
        r9 = 0.0f;
        goto L32
    L37:
        if (this.openingProgress <= 0.5f) goto L40;
        TabPreview r102 = this.tabs.get(r63);
    L39:
        r21 = r12;
        if (r7 >= this.tabs.size()) goto L49;
        r222 = r11;
        if (r102.tabDrawable == this.openingTab) goto L47;
    L50:
        BottomSheetTabs.TabDrawable r113 = r102.tabDrawable;
        BottomSheetTabs.TabDrawable r122 = this.openingTab;
        if (r113 != r122) goto L53;
        float r232 = r8;
    L54:
        if (r113 != r122) goto L56;
        float r123 = this.openingProgress;
    L57:
        float r114 = (r19 - r8) - r113.getPosition();
        if (r102.tabDrawable != this.openingTab) goto L61;
        float r82 = this.openingTabScroll;
    L62:
        Math.max(r82, 0.0f);
        r26 = r25;
        Math.max(Math.min(r82, 1.0f), -4.0f);
        float r27 = (AndroidUtilities.dp(6.0f) * Math.min(5.0f, r114)) + r15;
        float r210 = r45;
        float r83 = r27 + ((((r5 - r26) - (0.26f * r210)) - r27) * r82);
        float r211 = r32 / 2.0f;
        r29 = r32;
        r28 = r45;
        this.rect2.set(r18 - r211, r83, r18 + r211, r83 + r210);
        BottomSheetTabs.TabDrawable r212 = r102.tabDrawable;
        BottomSheetTabs.TabDrawable r33 = this.openingTab;
        if (r212 == r33) goto L73;
        RectF r84 = this.rect2;
        if (r84.top > r5) goto L71;
        if (r84.bottom < 0.0f) goto L71;
        if (r54 >= 0.1f) goto L73;
    L71:
        if (r114 >= (r19 - 3.0f)) goto L73;
        float r85 = 0.1f;
        int r46 = r222;
    L74:
        Sheet r93 = this.openingSheet;
        if (r93 == null) goto L78;
        if (r212 != r33) goto L78;
        this.rect.set(r93.mo6194getWindowView().getRect());
        RectF r213 = this.rect2;
        AndroidUtilities.lerpCentered(r213, this.rect, r123, r213);
        int r30 = r46;
        float r272 = r85;
    L79:
        BottomSheetTabs r214 = this.tabsView;
        if (r214 == null) goto L83;
        r214.setupTab(r102.tabDrawable);
    L83:
        if (r102.tabDrawable == this.openingTab) goto L89;
        RectF r215 = this.rect2;
        if (r215.top <= r5) goto L87;
    L88:
        r92 = r54;
        r112 = r63;
        r10 = r7;
        r242 = 1.0f;
        goto L103
    L87:
        if (r215.bottom < 0.0f) goto L88;
    L89:
        r1.save();
        r102.clickBounds.set(this.rect2);
        TabPreview.m5746$$Nest$fgetmatrix(r102).reset();
        TabPreview.m5747$$Nest$fgetsrc(r102)[0] = this.rect2.left;
        TabPreview.m5747$$Nest$fgetsrc(r102)[r222] = this.rect2.top;
        TabPreview.m5747$$Nest$fgetsrc(r102)[2] = this.rect2.right;
        TabPreview.m5747$$Nest$fgetsrc(r102)[3] = this.rect2.top;
        TabPreview.m5747$$Nest$fgetsrc(r102)[4] = this.rect2.right;
        float[] r216 = TabPreview.m5747$$Nest$fgetsrc(r102);
        RectF r34 = this.rect2;
        r216[5] = r34.top + (r34.height() * 1.0f);
        TabPreview.m5747$$Nest$fgetsrc(r102)[6] = this.rect2.left;
        float[] r217 = TabPreview.m5747$$Nest$fgetsrc(r102);
        RectF r47 = this.rect2;
        r217[7] = r47.top + (r47.height() * 1.0f);
        TabPreview.m5745$$Nest$fgetdst(r102)[0] = this.rect2.left;
        TabPreview.m5745$$Nest$fgetdst(r102)[r222] = this.rect2.top - AndroidUtilities.dp(0.0f);
        TabPreview.m5745$$Nest$fgetdst(r102)[2] = this.rect2.right;
        TabPreview.m5745$$Nest$fgetdst(r102)[3] = this.rect2.top - AndroidUtilities.dp(0.0f);
        float r94 = r232 * (1.0f - r123);
        TabPreview.m5745$$Nest$fgetdst(r102)[4] = this.rect2.centerX() + ((this.rect2.width() / 2.0f) * AndroidUtilities.lerp(1.0f, 0.83f, r94));
        TabPreview.m5745$$Nest$fgetdst(r102)[5] = (this.rect2.top - AndroidUtilities.dp(0.0f)) + (((this.rect2.height() * 1.0f) + AndroidUtilities.dp(0.0f)) * AndroidUtilities.lerp(1.0f, 0.6f, r94));
        TabPreview.m5745$$Nest$fgetdst(r102)[6] = this.rect2.centerX() - (AndroidUtilities.lerp(1.0f, 0.83f, r94) * (this.rect2.width() / 2.0f));
        TabPreview.m5745$$Nest$fgetdst(r102)[7] = (this.rect2.top - AndroidUtilities.dp(0.0f)) + (((this.rect2.height() * 1.0f) + AndroidUtilities.dp(0.0f)) * AndroidUtilities.lerp(1.0f, 0.6f, r94));
        TabPreview.m5746$$Nest$fgetmatrix(r102).setPolyToPoly(TabPreview.m5747$$Nest$fgetsrc(r102), 0, TabPreview.m5745$$Nest$fgetdst(r102), 0, 4);
        r1.concat(TabPreview.m5746$$Nest$fgetmatrix(r102));
        RectF r35 = this.rect2;
        BottomSheetTabs.TabDrawable r218 = r102.tabDrawable;
        if (r218 != this.openingTab) goto L92;
        float r219 = 1.0f;
    L94:
        if (r102.tabDrawable != this.openingTab) goto L96;
        r94 = 1.0f;
    L96:
        float r86 = AndroidUtilities.lerp(Utilities.clamp01((r114 - r19) + 2.0f), 1.0f, Utilities.clamp01((r232 - r272) / 0.8f));
        r242 = 1.0f;
        r112 = r63;
        float r64 = r94;
        r92 = r54;
        float r55 = r219;
        Canvas r220 = r1;
        TabPreview r13 = r102;
        r10 = r7;
        float r72 = r123;
        r13.draw(r220, r35, r30, r55, r64, r72, r86);
        Sheet r221 = this.openingSheet;
        if (r221 != null) goto L99;
    L101:
        r1 = r43;
    L102:
        r1.restore();
        goto L103
    L99:
        if (r13.tabDrawable != this.openingTab) goto L101;
        SheetView r14 = r221.mo6194getWindowView();
        RectF r36 = this.rect2;
        r14.drawInto(r43, r36, 1.0f, r36, r72, true);
        r1 = r43;
        goto L102
    L92:
        r219 = AndroidUtilities.lerp(r218.getAlpha(), 1.0f, this.openProgress);
    L78:
        r30 = r46;
        r272 = r85;
        this.tabsView.getTabBounds(this.rect, Utilities.clamp(r212.getPosition(), 1.0f, 0.0f));
        this.rect.offset(this.tabsView.getX(), this.tabsView.getY());
        RectF r223 = this.rect;
        RectF r37 = this.rect2;
        AndroidUtilities.lerpCentered(r223, r37, r54, r37);
    L73:
        r85 = 0.1f;
        r46 = 0;
        goto L74
    L61:
        r82 = (r114 - Math.max(getScrollMin(), getScrollOffset())) / getScrollWindow();
        goto L62
    L56:
        r123 = r9;
        goto L57
    L53:
        r232 = r54;
        goto L54
    L47:
        if (this.openingProgress <= 0.5f) goto L50;
        r26 = r25;
        r29 = r32;
        r28 = r45;
        r92 = r54;
        r63 = r7;
        r10 = r63;
        r242 = r8;
        goto L104
    L49:
        r222 = r11;
        goto L50
    L41:
        r102 = this.tabs.get(r7);
        goto L39
    L105:
        boolean r224 = r11;
        float r2110 = r12;
        r1.save();
        if (this.gradientClip != null) goto L108;
        this.gradientClip = new GradientClip();
    L108:
        RectF r225 = AndroidUtilities.rectTmp;
        r225.set(0.0f, 0.0f, r42, r15);
        this.gradientClip.draw(r1, r225, r224, this.openProgress);
        r1.restore();
        r1.restore();
        if (this.closeAllButtonText != null) goto L112;
        this.closeAllButtonText = new Text(LocaleController.getString(R.string.BotCloseAllTabs), 14.0f, AndroidUtilities.bold());
    L112:
        if (this.closeAllButtonBackground != null) goto L114;
    L115:
        boolean r226 = Theme.isCurrentThemeDark();
        this.closeAllButtonBackgroundDark = r226;
        if (r226 == false) goto L118;
        this.closeAllButtonBackground = Theme.createSimpleSelectorRoundRectDrawable(64, 553648127, 872415231);
    L119:
        this.closeAllButtonBackground.setCallback(this);
    L120:
        float r227 = this.closeAllButtonText.getCurrentWidth() + AndroidUtilities.dp(24.0f);
        float r56 = (r42 - r227) / 2.0f;
        this.closeAllButtonBackground.setBounds((int) r56, (int) ((r15 - (AndroidUtilities.dp(95.0f) / 2.0f)) - AndroidUtilities.dp(14.0f)), (int) ((r227 + r42) / 2.0f), (int) ((r15 - (AndroidUtilities.dp(95.0f) / 2.0f)) + AndroidUtilities.dp(14.0f)));
        this.closeAllButtonBackground.setAlpha((int) (this.openProgress * r2110));
        this.closeAllButtonBackground.draw(r1);
        this.closeAllButtonText.draw(r1, r56 + AndroidUtilities.dp(12.0f), r15 - (AndroidUtilities.dp(95.0f) / 2.0f), -1, this.openProgress);
        r43.restore();
        return;
    L118:
        this.closeAllButtonBackground = Theme.createSimpleSelectorRoundRectDrawable(64, 771751936, 1140850688);
        goto L119
    L114:
        if (this.closeAllButtonBackgroundDark == Theme.isCurrentThemeDark()) goto L120;
    L29:
        r53 = ((getScrollMin() - getScrollOffset()) / (getScrollWindow() * 0.15f)) * 0.2f;
        goto L30
    L17:
        r44 = 0.75f * r5;
        goto L18
    L10:
        r3[1] = 0;
        r3[0] = 0;
        this.tabsViewBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
        goto L11
    L5:
        if (this.openingProgress > 0.0f) goto L7;
    }

    private float getScrollStep() {
        return AndroidUtilities.dp(200.0f);
    }

    private TabPreview getTabAt(float r6, float r7) {
        if (this.openProgress >= 1.0f) goto L5;
        return null;
    L5:
        int r0 = this.tabs.size() - 1;
    L6:
        if (r0 < 0) goto L13;
        TabPreview r2 = this.tabs.get(r0);
        if (Math.abs(r2.dismissProgress) >= 0.4f) goto L12;
        if (r2.clickBounds.contains(r6, r7) == false) goto L12;
        return r2;
    L12:
        r0 = r0 - 1;
        goto L6
    L13:
        return null;
    }

    private /* synthetic */ void lambda$animateOpen$6(ValueAnimator r1) {
        this.openProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$dismissSheet$4(ValueAnimator r1) {
        this.dismissProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$dispatchTouchEvent$0(TabPreview r2, Boolean r3) {
        if (r3.booleanValue() == true) goto L5;
        r2.animateDismiss(0.0f);
        return;
    L5:
        if (r2.dismissProgress >= 0.0f) goto L7;
        float r32 = -1.0f;
    L8:
        r2.animateDismiss(r32);
        scrollTo(Utilities.clamp(this.offset, getScrollMax(false), getScrollMin(false)));
        if (this.tabsView.getTabs().isEmpty() == false) goto L14;
        closeTabsView();
        return;
    L14:
        return;
    L7:
        r32 = 1.0f;
        goto L8
    }

    private /* synthetic */ void lambda$dispatchTouchEvent$1(TabPreview r2, Boolean r3) {
        if (r3.booleanValue() == false) goto L8;
        r2.animateDismiss(1.0f);
        scrollTo(Utilities.clamp(this.offset, getScrollMax(false), getScrollMin(false)));
        if (this.tabsView.getTabs().isEmpty() == false) goto L10;
        closeTabsView();
        return;
    L10:
        return;
    L8:
        r2.animateDismiss(0.0f);
    }

    private /* synthetic */ void lambda$scrollTo$5(ValueAnimator r1) {
        this.offset = ((Float) r1.getAnimatedValue()).floatValue();
    }

    private WindowInsetsCompat onApplyWindowInsets(View r1, WindowInsetsCompat r2) {
        this.navigationBarInset = r2.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
        invalidate();
        return WindowInsetsCompat.CONSUMED;
    }

    private void prepareBlur(View r4) {
        AndroidUtilities.makingGlobalBlurBitmap = true;
        this.blurBitmap = AndroidUtilities.makeBlurBitmap(r4, 14.0f, 14);
        AndroidUtilities.makingGlobalBlurBitmap = false;
        Paint r42 = new Paint(1);
        this.blurBitmapPaint = r42;
        Bitmap r1 = this.blurBitmap;
        Shader.TileMode r2 = Shader.TileMode.CLAMP;
        BitmapShader r0 = new BitmapShader(r1, r2, r2);
        this.blurBitmapShader = r0;
        r42.setShader(r0);
        ColorMatrix r43 = new ColorMatrix();
        if (Theme.isCurrentThemeDark() == false) goto L5;
        float r02 = 0.08f;
    L6:
        AndroidUtilities.adjustSaturationColorMatrix(r43, r02);
        this.blurBitmapPaint.setColorFilter(new ColorMatrixColorFilter(r43));
        this.blurMatrix = new Matrix();
        return;
    L5:
        r02 = 0.25f;
        goto L6
    }

    private void prepareTabs() {
        ArrayList<BottomSheetTabs.WebTabData> r0 = this.tabsView.getTabs();
        ArrayList<BottomSheetTabs.TabDrawable> r1 = this.tabsView.getTabDrawables();
        int r2 = r0.size() - 1;
    L3:
        if (r2 < 0) goto L16;
        BottomSheetTabs.WebTabData r4 = r0.get(r2);
        int r5 = 0;
    L6:
        if (r5 >= r1.size()) goto L11;
        BottomSheetTabs.TabDrawable r6 = r1.get(r5);
        if (r6.tab == r4) goto L12;
        r5 = r5 + 1;
    L12:
        if (r6 == null) goto L15;
        this.tabs.add(new TabPreview(this, r4, r6));
    L15:
        r2 = r2 - 1;
        goto L3
    L11:
        r6 = null;
        goto L12
    L16:
        this.animatedCount.set(this.tabs.size(), true);
        setScrollOffset(getScrollMax());
    }

    public static void renderHardwareViewToBitmap(View r5, float r6, Utilities.Callback<Bitmap> r7) {
        if (r5 == null) goto L11;
        if (r7 == null) goto L11;
        if (r5.getWidth() <= 0) goto L11;
        if (r5.getHeight() <= 0) goto L11;
        SurfaceTexture r0 = BottomSheetTabsOverlay$$ExternalSyntheticApiModelOutline0.m(false);
        r0.setDefaultBufferSize(r5.getWidth(), r5.getHeight());
        Surface r1 = new Surface(r0);
        Bitmap r2 = Bitmap.createBitmap(r5.getWidth(), r5.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas r3 = r1.lockHardwareCanvas();
        r3.translate(0.0f, r6);
        r5.draw(r3);
        r1.unlockCanvasAndPost(r3);
        PixelCopy.request(r1, r2, new BottomSheetTabsOverlay$$ExternalSyntheticLambda9(r7, r2, r1, r0), new Handler());
        return;
    L11:
        if (r7 == null) goto L14;
        r7.run(null);
        return;
    }

    private void scrollTo(float r4) {
        ValueAnimator r0 = this.scrollAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.scrollAnimator = null;
    L5:
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{this.offset, r4});
        this.scrollAnimator = r42;
        r42.addUpdateListener(new BottomSheetTabsOverlay$$ExternalSyntheticLambda8(this));
        this.scrollAnimator.setDuration(250);
        this.scrollAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.scrollAnimator.start();
    }

    private void setModalAccessibility(boolean r6) {
        if (r6 == false) goto L4;
        int r0 = 1;
    L5:
        setImportantForAccessibility(r0);
        ViewParent r02 = getParent();
        if ((r02 instanceof ViewGroup) == false) goto L18;
        ViewGroup r03 = (ViewGroup) r02;
        int r2 = 0;
    L9:
        if (r2 >= r03.getChildCount()) goto L18;
        View r3 = r03.getChildAt(r2);
        if (r3 == this) goto L17;
        if (r6 == false) goto L15;
        int r4 = 4;
    L16:
        r3.setImportantForAccessibility(r4);
        goto L17
    L15:
        r4 = 0;
    L17:
        r2 = r2 + 1;
    L18:
        OverlayAccessibilityHelper r04 = this.accessibilityHelper;
        if (r04 == null) goto L21;
        r04.invalidateRoot();
    L21:
        if (r6 == false) goto L27;
        sendAccessibilityEvent(32);
        return;
    L27:
        return;
    L4:
        r0 = 2;
        goto L5
    }

    public void closeTabsView() {
        animateOpen(false);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.scroller.computeScrollOffset() == false) goto L6;
        setScrollOffset(this.scroller.getCurrY() / getScrollStep());
        postInvalidateOnAnimation();
        return;
    }

    public boolean dismissSheet(Sheet r12) {
        if (r12 != null) goto L6;
        return false;
    L6:
        if (this.tabsView != null) goto L9;
        return false;
    L9:
        if (this.dismissingSheet == null) goto L11;
    L12:
        ValueAnimator r1 = this.animator;
        if (r1 == null) goto L15;
        r1.end();
        this.animator = null;
    L15:
        this.dismissingSheet = r12;
        r12.setLastVisible(false);
        ValueAnimator r13 = this.animator;
        if (r13 == null) goto L18;
        r13.cancel();
    L18:
        BottomSheetTabs.WebTabData r14 = r12.saveState();
        this.dismissingTab = this.tabsView.pushTab(r14);
        post(new BottomSheetTabsOverlay$$ExternalSyntheticLambda2(r12));
        invalidate();
        this.dismissProgress = 0.0f;
        ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.animator = r2;
        r2.addUpdateListener(new BottomSheetTabsOverlay$$ExternalSyntheticLambda3(this));
        this.animator.addListener(new AnonymousClass2(this, r14, r12));
        AndroidUtilities.applySpring(this.animator, 220.0d, 30.0d, 1.0d);
        this.animator.setDuration((long) (r12.getDuration() * 1.1f));
        this.animator.start();
        this.slowerDismiss = false;
        return true;
    L11:
        if (this.openingSheet == null) goto L15;
        goto L12
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r1) {
        super.dispatchDraw(r1);
        drawDismissingTab(r1);
        drawTabsPreview(r1);
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent r3) {
        if (this.openProgress <= 0.0f) goto L11;
        OverlayAccessibilityHelper r0 = this.accessibilityHelper;
        if (r0 == null) goto L11;
        if (r0.dispatchHoverEvent(r3) == false) goto L11;
        return true;
    L11:
        return super.dispatchHoverEvent(r3);
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent r21) {
        if (AndroidUtilities.isTablet() == false) goto L10;
        if (r21.getAction() != 0) goto L10;
        if (this.tabsViewBounds.contains(r21.getX(), r21.getY()) == true) goto L10;
        return false;
    L10:
        if (this.openProgress > 0.0f) goto L12;
        return false;
    L12:
        if (this.velocityTracker != null) goto L14;
        this.velocityTracker = VelocityTracker.obtain();
    L14:
        this.velocityTracker.addMovement(r21);
        if (r21.getAction() != 0) goto L49;
        this.startTime = System.currentTimeMillis();
        this.startX = r21.getX();
        this.startY = r21.getY();
        this.pressTab = getTabAt(r21.getX(), r21.getY());
        Drawable r1 = this.closeAllButtonBackground;
        if (r1 != null) goto L19;
    L21:
        boolean r12 = false;
    L22:
        this.hitCloseAllButton = r12;
        if (r12 == false) goto L25;
        this.pressTab = null;
    L25:
        Drawable r13 = this.closeAllButtonBackground;
        if (r13 == null) goto L32;
        r13.setHotspot(r21.getX(), r21.getY());
        Drawable r14 = this.closeAllButtonBackground;
        if (this.hitCloseAllButton == false) goto L30;
        int[] r10 = {android.R.attr.state_pressed, android.R.attr.state_enabled};
    L31:
        r14.setState(r10);
        goto L32
    L30:
        r10 = new int[0];
    L32:
        this.verticallyScrolling = false;
        this.horizontallySwiping = false;
        this.pressTabClose = false;
        TabPreview r15 = this.pressTab;
        if (r15 == null) goto L42;
        r15.cancelDismissAnimator();
        boolean r16 = this.pressTab.tabDrawable.closeRipple.getBounds().contains((int) (r21.getX() - this.pressTab.clickBounds.left), (int) ((r21.getY() - this.pressTab.clickBounds.top) - AndroidUtilities.dp(24.0f)));
        this.pressTabClose = r16;
        if (r16 == false) goto L37;
        this.pressTab.tabDrawable.closeRipple.setHotspot((int) (r21.getX() - this.rect.left), (int) (r21.getY() - this.rect.centerY()));
    L37:
        this.pressTab.setPressed(!this.pressTabClose);
        Drawable r17 = this.pressTab.tabDrawable.closeRipple;
        if (this.pressTabClose == false) goto L40;
        int[] r5 = {android.R.attr.state_pressed, android.R.attr.state_enabled};
    L41:
        r17.setState(r5);
        goto L42
    L40:
        r5 = new int[0];
    L42:
        this.lastY = r21.getY();
        if (this.scroller.isFinished() == true) goto L45;
        this.scroller.abortAnimation();
    L45:
        ValueAnimator r18 = this.scrollAnimator;
        if (r18 == null) goto L200;
        r18.cancel();
        this.scrollAnimator = null;
    L200:
        return true;
    L19:
        if (r1.getBounds().contains((int) r21.getX(), (int) r21.getY()) == false) goto L21;
        r12 = true;
        goto L22
    L49:
        if (r21.getAction() != 2) goto L138;
        TabPreview r19 = this.pressTab;
        if (r19 != null) goto L53;
    L122:
        Drawable r110 = this.closeAllButtonBackground;
        if (r110 != null) goto L125;
    L136:
        this.lastY = r21.getY();
        goto L200
    L125:
        if (this.hitCloseAllButton == false) goto L136;
        if (this.pressTab != null) goto L132;
        if (r110 == null) goto L132;
        if (r110.getBounds().contains((int) r21.getX(), (int) r21.getY()) == false) goto L132;
        boolean r111 = true;
    L133:
        this.hitCloseAllButton = r111;
        if (r111 == true) goto L136;
        this.closeAllButtonBackground.setState(new int[0]);
    L132:
        r111 = false;
        goto L133
    L53:
        if (r19.isPressed() == false) goto L82;
        if (this.horizontallySwiping == true) goto L62;
        if (this.verticallyScrolling == true) goto L62;
        if (MathUtils.distance(this.startX, r21.getY(), r21.getX(), r21.getY()) <= AndroidUtilities.touchSlop) goto L62;
        this.horizontallySwiping = true;
    L62:
        if (this.verticallyScrolling == true) goto L75;
        if (this.horizontallySwiping == true) goto L75;
        if (MathUtils.distance(r21.getX(), this.startY, r21.getX(), r21.getY()) <= AndroidUtilities.touchSlop) goto L75;
        if (this.scroller.isFinished() == true) goto L70;
        this.scroller.abortAnimation();
    L70:
        ValueAnimator r112 = this.scrollAnimator;
        if (r112 == null) goto L73;
        r112.cancel();
        this.scrollAnimator = null;
    L73:
        this.verticallyScrolling = true;
    L75:
        if (this.tabsView == null) goto L111;
        if (this.verticallyScrolling == false) goto L79;
    L80:
        this.pressTab.setPressed(false);
        this.pressTab.cancelDismissAnimator();
        goto L111
    L79:
        if (this.horizontallySwiping == true) goto L80;
    L111:
        if (this.pressTab.isPressed() == false) goto L113;
    L121:
        invalidate();
        goto L122
    L113:
        if (this.horizontallySwiping == false) goto L116;
        this.pressTab.dismissProgress = (r21.getX() - this.startX) / AndroidUtilities.dp(300.0f);
        goto L121
    L116:
        if (this.verticallyScrolling == false) goto L121;
        float r113 = r21.getY() - this.lastY;
        if (this.offset >= getScrollMin()) goto L120;
        r113 = r113 * (1.0f - (Utilities.clamp((getScrollMin() - this.offset) / getScrollStep(), 1.0f, 0.0f) * 0.5f));
    L120:
        setScrollOffset(Utilities.clamp(((getScrollOffset() * getScrollStep()) - r113) / getScrollStep(), getScrollMax(), getScrollMin() - (getScrollStep() * 1.4f)));
        invalidate();
        goto L121
    L82:
        if (this.pressTabClose == true) goto L91;
        if (this.horizontallySwiping == true) goto L91;
        if (this.verticallyScrolling == true) goto L91;
        if (MathUtils.distance(this.startX, r21.getY(), r21.getX(), r21.getY()) <= AndroidUtilities.touchSlop) goto L91;
        this.horizontallySwiping = true;
    L91:
        if (this.pressTabClose == true) goto L106;
        if (this.verticallyScrolling == true) goto L106;
        if (this.horizontallySwiping == true) goto L106;
        if (MathUtils.distance(r21.getX(), this.startY, r21.getX(), r21.getY()) <= AndroidUtilities.touchSlop) goto L106;
        if (this.scroller.isFinished() == true) goto L101;
        this.scroller.abortAnimation();
    L101:
        ValueAnimator r114 = this.scrollAnimator;
        if (r114 == null) goto L104;
        r114.cancel();
        this.scrollAnimator = null;
    L104:
        this.verticallyScrolling = true;
    L106:
        if (this.pressTabClose == false) goto L111;
        boolean r115 = this.pressTab.tabDrawable.closeRipple.getBounds().contains((int) (r21.getX() - this.pressTab.clickBounds.left), (int) ((r21.getY() - this.pressTab.clickBounds.top) - AndroidUtilities.dp(24.0f)));
        this.pressTabClose = r115;
        if (r115 == true) goto L111;
        this.pressTab.tabDrawable.closeRipple.setState(new int[0]);
        goto L111
    L138:
        if (r21.getAction() != 1) goto L190;
        TabPreview r116 = this.pressTab;
        if (r116 == null) goto L172;
        if (this.tabsView != null) goto L144;
    L146:
        this.pressTab.animateDismiss(0.0f);
        if (this.tabsView == null) goto L152;
        if (this.pressTab.isPressed() == false) goto L152;
        closeTabsView();
        TabPreview r117 = this.pressTab;
        r117.webView = null;
        this.tabsView.openTab(r117.tabData);
    L164:
        this.pressTab.setPressed(false);
        if (this.pressTabClose == false) goto L168;
        this.pressTabClose = this.pressTab.tabDrawable.closeRipple.getBounds().contains((int) (r21.getX() - this.pressTab.clickBounds.left), (int) ((r21.getY() - this.pressTab.clickBounds.top) - AndroidUtilities.dp(24.0f)));
    L168:
        if (this.pressTabClose == false) goto L170;
        TabPreview r118 = this.pressTab;
        this.tabsView.removeTab(r118.tabData, new BottomSheetTabsOverlay$$ExternalSyntheticLambda5(this, r118));
    L170:
        this.pressTab.tabDrawable.closeRipple.setState(new int[0]);
    L183:
        this.pressTab = null;
        this.pressTabClose = false;
        VelocityTracker r119 = this.velocityTracker;
        if (r119 == null) goto L186;
        r119.recycle();
        this.velocityTracker = null;
    L186:
        this.hitCloseAllButton = false;
        Drawable r0 = this.closeAllButtonBackground;
        if (r0 == null) goto L200;
        r0.setState(new int[0]);
    L152:
        if (this.verticallyScrolling == false) goto L164;
        if (this.offset >= (getScrollMin() - (getScrollWindow() * 0.15f))) goto L157;
        closeTabsView();
    L163:
        this.velocityTracker.recycle();
        this.velocityTracker = null;
        postInvalidateOnAnimation();
        goto L164
    L157:
        if (this.offset >= getScrollMin()) goto L159;
        scrollTo(getScrollMin());
        goto L163
    L159:
        this.velocityTracker.computeCurrentVelocity(1000, this.maximumVelocity);
        float r120 = this.velocityTracker.getYVelocity();
        float r3 = Math.abs(r120);
        float r6 = this.minimumVelocity;
        OverScroller r9 = this.scroller;
        if (r3 <= r6) goto L162;
        r9.fling(0, (int) (getScrollOffset() * getScrollStep()), 0, (int) (-r120), 0, 0, (int) (getScrollMin() * getScrollStep()), (int) (getScrollMax() * getScrollStep()), 0, (int) (getScrollStep() * 0.1f));
        goto L163
    L162:
        r9.startScroll(0, (int) (getScrollOffset() * getScrollStep()), 0, 0, 0);
        goto L163
    L144:
        if (Math.abs(r116.dismissProgress) <= 0.4f) goto L146;
        TabPreview r121 = this.pressTab;
        this.tabsView.removeTab(r121.tabData, new BottomSheetTabsOverlay$$ExternalSyntheticLambda4(this, r121));
        goto L164
    L172:
        if (this.hitCloseAllButton == false) goto L175;
        this.tabsView.removeAll();
        closeTabsView();
        goto L183
    L175:
        if (MathUtils.distance(this.startX, this.startY, r21.getX(), r21.getY()) > AndroidUtilities.touchSlop) goto L183;
        if (this.verticallyScrolling == true) goto L183;
        if (this.horizontallySwiping == true) goto L183;
        if ((System.currentTimeMillis() - this.startTime) > (ViewConfiguration.getTapTimeout() * 1.2f)) goto L183;
        closeTabsView();
        goto L183
    L190:
        if (r21.getAction() != 3) goto L200;
        TabPreview r122 = this.pressTab;
        if (r122 == null) goto L194;
        r122.animateDismiss(0.0f);
        this.pressTab.setPressed(false);
        this.pressTab.tabDrawable.closeRipple.setState(new int[0]);
    L194:
        this.pressTab = null;
        this.pressTabClose = false;
        VelocityTracker r123 = this.velocityTracker;
        if (r123 == null) goto L197;
        r123.recycle();
        this.velocityTracker = null;
    L197:
        this.hitCloseAllButton = false;
        Drawable r02 = this.closeAllButtonBackground;
        if (r02 == null) goto L200;
        r02.setState(new int[0]);
        goto L200
    }

    public float getScrollMax() {
        return getScrollMax(true);
    }

    public float getScrollMax(boolean r4) {
        return (getScrollRange(r4) - getScrollWindow(r4)) - ((getScrollWindow(r4) / 3.0f) * Utilities.clamp(4.0f - getScrollRange(r4), 0.5f, 0.0f));
    }

    public float getScrollMin() {
        return getScrollMin(true);
    }

    public float getScrollMin(boolean r3) {
        return ((-getScrollWindow()) / 3.0f) * Utilities.clamp(getScrollRange(r3), 1.0f, 0.0f);
    }

    public float getScrollOffset() {
        return this.offset;
    }

    public float getScrollRange() {
        return getScrollRange(true);
    }

    public float getScrollRange(boolean r5) {
        int r1 = 0;
        float r2 = 0.0f;
    L4:
        if (r1 >= this.tabs.size()) goto L10;
        if (this.tabs.get(r1).tabDrawable.index < 0) goto L8;
        float r3 = 1.0f;
    L9:
        r2 = r2 + r3;
        r1 = r1 + 1;
        goto L4
    L8:
        r3 = 0.0f;
        goto L9
    L10:
        if (r5 == true) goto L12;
        return r2;
    L12:
        return this.animatedCount.set(r2);
    }

    public float getScrollWindow() {
        return Math.min(3.0f, getScrollRange());
    }

    public float getScrollWindow(boolean r2) {
        return Math.min(3.0f, getScrollRange(r2));
    }

    public boolean onBackPressed() {
        if (this.isOpen == false) goto L6;
        closeTabsView();
        return true;
    L6:
        return false;
    }

    public void openTabsView() {
        BottomSheetTabs r0 = this.tabsView;
        if (r0 != null) goto L5;
        return;
    L5:
        if ((r0.getParent() instanceof View) == true) goto L8;
        return;
    L8:
        if (BotWebViewSheet.activeSheets.isEmpty() == true) goto L15;
        Iterator r02 = new HashSet(BotWebViewSheet.activeSheets).iterator();
    L11:
        if (r02.hasNext() == false) goto L13;
        ((BotWebViewSheet) r02.next()).dismiss(true);
        goto L11
    L13:
        AndroidUtilities.runOnUIThread(new BottomSheetTabsOverlay$$ExternalSyntheticLambda6(this), 100);
        return;
    L15:
        stopAnimations();
        View r03 = (View) this.tabsView.getParent();
        this.actionBarLayout = r03;
        int[] r2 = this.pos;
        if (r03 == null) goto L18;
        r03.getLocationOnScreen(r2);
    L19:
        getLocationOnScreen(this.pos2);
        RectF r04 = this.tabsViewBounds;
        int r4 = this.pos[0];
        int r3 = this.pos2[0];
        r04.set(r4 - r3, r2[1] - r5[1], (r4 - r3) + this.actionBarLayout.getWidth(), (this.pos[1] - this.pos2[1]) + this.actionBarLayout.getHeight());
        prepareBlur(this.actionBarLayout);
        clearTabs();
        prepareTabs();
        animateOpen(true);
        return;
    L18:
        r2[1] = 0;
        r2[0] = 0;
        goto L19
    }

    public void setScrollOffset(float r1) {
        this.offset = r1;
    }

    public void setSlowerDismiss(boolean r1) {
        this.slowerDismiss = r1;
    }

    public void setTabsView(BottomSheetTabs r1) {
        this.tabsView = r1;
    }

    public void stopAnimations() {
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L6;
        r0.cancel();
        this.animator = null;
        return;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.closeAllButtonBackground) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
