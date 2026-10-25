package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ArticleViewer;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedColor;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Text;
import org.telegram.ui.EmptyBaseFragment;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.bots.BotButtons;
import org.telegram.ui.bots.BotSensors;
import org.telegram.ui.bots.BotWebViewSheet;
import org.telegram.ui.bots.WebViewRequestProps;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes3.dex */
public class BottomSheetTabs extends FrameLayout {
    public static final HashMap<Integer, ArrayList<TabDrawable>> tabDrawables = null;
    public static final HashMap<Integer, ArrayList<WebTabData>> tabs = null;
    private static TextPaint textPaint;
    private TabsAccessibilityHelper accessibilityHelper;
    private final ActionBarLayout actionBarLayout;
    private int backgroundColor;
    private AnimatedColor backgroundColorAnimated;
    private final Paint backgroundPaint;
    private ValueAnimator bottomTabsAnimator;
    public int bottomTabsHeight;
    public float bottomTabsProgress;
    private boolean closeRippleHit;
    public int currentAccount;
    public boolean doNotDismiss;
    public boolean drawTabs;
    private boolean hit;
    private final HashSet<Runnable> invalidateListeners;
    private final RectF rect;
    private final HashSet<Runnable> relayoutListeners;
    private int tabColor;
    private AnimatedColor tabColorAnimated;
    private AnimatedFloat tabDarkAnimated;
    private boolean tabIsDark;

    /* renamed from: org.telegram.ui.ActionBar.BottomSheetTabs$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ BottomSheetTabs this$0;

        public AnonymousClass1(BottomSheetTabs r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (BottomSheetTabs.m5727$$Nest$fgetbottomTabsAnimator(this.this$0) != r2) goto L8;
            BottomSheetTabs r1 = this.this$0;
            r1.bottomTabsProgress = r1.bottomTabsHeight;
            Iterator r12 = BottomSheetTabs.m5728$$Nest$fgetinvalidateListeners(r1).iterator();
        L6:
            if (r12.hasNext() == false) goto L10;
            ((Runnable) r12.next()).run();
            goto L6
        L10:
            return;
        }
    }

    public static class ClipTools {
        private final Path clipPath;
        private final float[] clipRadius;
        private final RectF clipRect;
        private final Paint clipShadowPaint;
        private final BottomSheetTabs tabs;

        public ClipTools(BottomSheetTabs r3) {
            this.clipRect = new RectF();
            this.clipRadius = new float[8];
            this.clipPath = new Path();
            this.clipShadowPaint = new Paint(1);
            this.tabs = r3;
        }

        public void clip(Canvas r5, boolean r6, boolean r7, int r8, int r9, float r10) {
            if (r7 == false) goto L5;
            int r72 = 0;
        L6:
            int r73 = (int) (r72 * r10);
            int r102 = Math.min(1, r73 / AndroidUtilities.dp(60.0f)) * AndroidUtilities.dp(10.0f);
            if (r73 > 0) goto L9;
            return;
        L9:
            float[] r1 = this.clipRadius;
            r1[3] = 0.0f;
            r1[2] = 0.0f;
            r1[1] = 0.0f;
            r1[0] = 0.0f;
            float r92 = r102;
            r1[7] = r92;
            r1[6] = r92;
            r1[5] = r92;
            r1[4] = r92;
            this.clipPath.rewind();
            this.clipRect.set(0.0f, 0.0f, r8, (this.tabs.getY() + this.tabs.getHeight()) - r73);
            this.clipPath.addRoundRect(this.clipRect, this.clipRadius, Path.Direction.CW);
            this.clipShadowPaint.setAlpha(0);
            if (r6 == false) goto L12;
            this.clipShadowPaint.setShadowLayer(AndroidUtilities.dp(2.0f), 0.0f, AndroidUtilities.dp(1.0f), 268435456);
            r5.drawPath(this.clipPath, this.clipShadowPaint);
        L12:
            r5.clipPath(this.clipPath);
            return;
        L5:
            r72 = this.tabs.getHeight(true);
            goto L6
        }
    }

    public static class TabDrawable {
        public final AnimatedFloat animatedAlpha;
        public final AnimatedFloat animatedPosition;
        private int backgroundColor;
        private boolean backgroundIsDark;
        private final Paint backgroundPaint;
        private final Path closePath;
        public final Drawable closeRipple;
        public int closeRippleColor;
        private final Path expandPath;
        private float expandProgress;
        private Bitmap favicon;
        private final Paint faviconPaint;
        private Drawable iconDrawable;
        private int iconDrawableColor;
        private final Paint iconPaint;
        public int index;
        private Text overrideTitle;
        public final View parentView;
        private int position;
        private float progress;
        private final Paint progressPaint;
        private final float[] radii;
        private final Path rectPath;
        public final WebTabData tab;
        private int tabColor;
        private boolean tabIsDark;
        private final Text title;

        /* renamed from: -$$Nest$fgetposition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5730$$Nest$fgetposition(TabDrawable r0) {
            return r0.position;
        }

        /* renamed from: -$$Nest$fputposition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5731$$Nest$fputposition(TabDrawable r0, int r1) {
            r0.position = r1;
        }

        public TabDrawable(View r9, WebTabData r10) {
            boolean r1 = true;
            this.backgroundPaint = new Paint(1);
            this.progressPaint = new Paint(1);
            Paint r0 = new Paint(1);
            this.iconPaint = r0;
            this.faviconPaint = new Paint(3);
            Drawable r2 = Theme.createSelectorDrawable(822083583, 1);
            this.closeRipple = r2;
            this.iconDrawableColor = -1;
            this.radii = new float[8];
            this.rectPath = new Path();
            Path r3 = new Path();
            this.closePath = r3;
            Path r4 = new Path();
            this.expandPath = r4;
            this.parentView = r9;
            this.tab = r10;
            r2.setCallback(r9);
            r0.setStyle(Paint.Style.STROKE);
            r0.setStrokeJoin(Paint.Join.ROUND);
            r0.setStrokeCap(Paint.Cap.ROUND);
            CubicBezierInterpolator r22 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.animatedPosition = new AnimatedFloat(r9, 320, r22);
            this.animatedAlpha = new AnimatedFloat(r9, 320, r22);
            this.favicon = r10.favicon;
            this.title = new Text(Emoji.replaceEmoji(r10.getTitle(), BottomSheetTabs.m5729$$Nest$smgetTextPaint().getFontMetricsInt(), false), 17.0f, AndroidUtilities.bold());
            int r02 = r10.actionBarColor;
            this.tabColor = r02;
            if (AndroidUtilities.computePerceivedBrightness(r02) < 0.721f) goto L6;
            r1 = false;
        L6:
            this.tabIsDark = r1;
            if (r10.isArticle() == false) goto L9;
            this.iconDrawable = r9.getContext().getResources().getDrawable(R.drawable.msg_instant).mutate();
        L9:
            this.progress = r10.articleProgress;
            r3.rewind();
            r3.moveTo(0.0f, 0.0f);
            r3.lineTo(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
            r3.moveTo(AndroidUtilities.dp(12.0f), 0.0f);
            r3.lineTo(0.0f, AndroidUtilities.dp(12.0f));
            r4.rewind();
            r4.moveTo(0.0f, AndroidUtilities.dp(6.33f) / 2.0f);
            r4.lineTo(AndroidUtilities.dp(12.66f) / 2.0f, (-AndroidUtilities.dp(6.33f)) / 2.0f);
            r4.lineTo(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(6.33f) / 2.0f);
        }

        public void draw(Canvas r18, RectF r19, float r20, float r21, float r22) {
            int r3 = ColorUtils.blendARGB(this.backgroundColor, this.tabColor, this.expandProgress);
            this.backgroundPaint.setColor(r3);
            float r9 = r21 * 255.0f;
            this.backgroundPaint.setAlpha((int) r9);
            float r10 = 1.0f;
            float r12 = 0.0f;
            this.backgroundPaint.setShadowLayer(AndroidUtilities.dp(2.33f), 0.0f, AndroidUtilities.dp(1.0f), Theme.multAlpha(268435456, r21));
            float[] r4 = this.radii;
            r4[3] = r20;
            r4[2] = r20;
            r4[1] = r20;
            int r11 = 0;
            r4[0] = r20;
            float r2 = AndroidUtilities.lerp(r20, 0.0f, this.expandProgress);
            r4[7] = r2;
            r4[6] = r2;
            r4[5] = r2;
            r4[4] = r2;
            this.rectPath.rewind();
            this.rectPath.addRoundRect(r19, this.radii, Path.Direction.CW);
            r18.drawPath(this.rectPath, this.backgroundPaint);
            if (this.progress <= 0.0f) goto L14;
            if (this.expandProgress <= 0.0f) goto L14;
            if (r21 <= 0.0f) goto L14;
            r18.save();
            r18.clipPath(this.rectPath);
            Paint r23 = this.progressPaint;
            if (AndroidUtilities.computePerceivedBrightness(r3) <= 0.721f) goto L11;
            int r32 = -16777216;
        L12:
            r23.setColor(Theme.multAlpha(r32, (0.07f * r21) * this.expandProgress));
            float r24 = r19.left;
            r18.drawRect(r24, r19.top, (r19.width() * this.progress) + r24, r19.bottom, this.progressPaint);
            r18.restore();
            goto L14
        L11:
            r32 = -1;
        L14:
            if (this.backgroundIsDark == false) goto L16;
            float r25 = 1.0f;
        L18:
            if (this.tabIsDark == false) goto L20;
            r12 = 1.0f;
        L20:
            float r26 = AndroidUtilities.lerp(r25, r12, this.expandProgress);
            int r42 = ColorUtils.blendARGB(-16777216, -1, r26);
            this.iconPaint.setColor(r42);
            this.iconPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
            r18.save();
            r18.translate(r19.left, r19.centerY());
            int r27 = ColorUtils.blendARGB(553648127, 553648127, r26);
            this.closeRipple.setBounds(AndroidUtilities.dp(25.0f) + (-AndroidUtilities.dp(25.0f)), -AndroidUtilities.dp(25.0f), AndroidUtilities.dp(25.0f) + AndroidUtilities.dp(25.0f), AndroidUtilities.dp(25.0f));
            if (this.closeRippleColor == r27) goto L23;
            Drawable r33 = this.closeRipple;
            this.closeRippleColor = r27;
            Theme.setSelectorDrawableColor(r33, r27, false);
        L23:
            this.closeRipple.draw(r18);
            r18.restore();
            r18.save();
            r18.translate(r19.left + AndroidUtilities.dp(18.0f), r19.centerY() - AndroidUtilities.dp(6.0f));
            float r92 = r9 * r22;
            int r34 = (int) r92;
            this.iconPaint.setAlpha(r34);
            r18.drawPath(this.closePath, this.iconPaint);
            r18.restore();
            r18.save();
            r18.translate(r19.right - AndroidUtilities.dp(30.66f), r19.centerY());
            this.iconPaint.setAlpha((int) (r92 * (1.0f - this.expandProgress)));
            r18.drawPath(this.expandPath, this.iconPaint);
            r18.restore();
            if (this.favicon == null) goto L27;
            int r28 = AndroidUtilities.dp(24.0f);
            r18.save();
            Rect r6 = AndroidUtilities.rectTmp2;
            float r13 = r28;
            float r5 = r13 / 2.0f;
            r6.set((int) (r19.left + AndroidUtilities.dp(56.0f)), (int) (r19.centerY() - r5), (int) ((r19.left + AndroidUtilities.dp(56.0f)) + r13), (int) (r19.centerY() + r5));
            this.faviconPaint.setAlpha(r34);
            r18.drawBitmap(this.favicon, null, r6, this.faviconPaint);
            r18.restore();
            r11 = r28 + AndroidUtilities.dp(4.0f);
        L32:
            Text r29 = this.overrideTitle;
            if (r29 == null) goto L35;
            float r35 = r19.width() - AndroidUtilities.dp(100.0f);
            Text r210 = r29.ellipsize((int) (r35 - r5));
            float r36 = (r19.left + AndroidUtilities.dp(60.0f)) + r11;
            int r52 = r42;
            r210.draw(r18, r36, r19.centerY(), r52, ((1.0f - this.expandProgress) * r21) * r22);
        L36:
            Text r1 = this.title;
            float r211 = r19.width() - AndroidUtilities.dp(100.0f);
            Text r14 = r1.ellipsize((int) (r211 - r3));
            float r212 = (r19.left + AndroidUtilities.dp(60.0f)) + r11;
            float r37 = r19.centerY();
            if (this.overrideTitle == null) goto L40;
            r10 = this.expandProgress;
        L40:
            r14.draw(r18, r212, r37, r52, (r10 * r21) * r22);
            return;
        L35:
            r52 = r42;
            goto L36
        L27:
            if (this.iconDrawable == null) goto L32;
            float r213 = AndroidUtilities.dp(24.0f);
            int r62 = (int) ((r213 / this.iconDrawable.getIntrinsicHeight()) * this.iconDrawable.getIntrinsicWidth());
            Rect r112 = AndroidUtilities.rectTmp2;
            float r214 = (r213 / 2.0f) * 0.7f;
            r112.set((int) (r19.left + AndroidUtilities.dp(56.0f)), (int) (r19.centerY() - r214), (int) ((r19.left + AndroidUtilities.dp(56.0f)) + (r62 * 0.7f)), (int) (r19.centerY() + r214));
            if (r42 == this.iconDrawableColor) goto L31;
            Drawable r215 = this.iconDrawable;
            this.iconDrawableColor = r42;
            r215.setColorFilter(new PorterDuffColorFilter(r42, PorterDuff.Mode.SRC_IN));
        L31:
            this.iconDrawable.setAlpha(r34);
            this.iconDrawable.setBounds(r112);
            this.iconDrawable.draw(r18);
            r11 = r62 - AndroidUtilities.dp(2.0f);
            goto L32
        L16:
            r25 = 0.0f;
            goto L18
        }

        public float getAlpha() {
            float r0 = getPosition();
            if (r0 >= 0.0f) goto L6;
            float r02 = r0 + 1.0f;
        L11:
            AnimatedFloat r1 = this.animatedAlpha;
            if (this.index < 0) goto L14;
            boolean r4 = true;
        L16:
            return r02 * r1.set(r4);
        L14:
            r4 = false;
            goto L16
        L6:
            if (r0 >= 0.0f) goto L8;
        L10:
            r02 = (1.0f - Math.min(1.0f, r0 - 1.0f)) * 0.87f;
            goto L11
        L8:
            if (r0 >= 1.0f) goto L10;
            r02 = AndroidUtilities.lerp(1.0f, 0.87f, r0);
            goto L11
        }

        public float getPosition() {
            if (this.index >= 0) goto L7;
            return this.position;
        L7:
            return this.animatedPosition.set(this.position);
        }

        public void setBackgroundColor(int r1, boolean r2) {
            this.backgroundColor = r1;
            this.backgroundIsDark = r2;
        }

        public void setExpandProgress(float r1) {
            this.expandProgress = r1;
        }

        public void setOverrideTitle(CharSequence r4) {
            if (r4 != null) goto L5;
            this.overrideTitle = null;
            return;
        L5:
            this.overrideTitle = new Text(r4, 17.0f, AndroidUtilities.bold());
        }
    }

    public class TabsAccessibilityHelper extends ExploreByTouchHelper {
        final /* synthetic */ BottomSheetTabs this$0;
        private final Rect tmpRect;
        private final RectF tmpRectF;

        /* renamed from: $r8$lambda$rKrZd3N_R6LXhNBcOtjYQ-dSVzc, reason: not valid java name */
        public static /* synthetic */ void m5732$r8$lambda$rKrZd3N_R6LXhNBcOtjYQdSVzc(Boolean r0) {
        }

        public TabsAccessibilityHelper(BottomSheetTabs r1, View r2) {
            this.this$0 = r1;
            super(r2);
            this.tmpRectF = new RectF();
            this.tmpRect = new Rect();
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public int getVirtualViewAt(float r6, float r7) {
            BottomSheetTabs r0 = this.this$0;
            if (r0.drawTabs == true) goto L5;
            return -1;
        L5:
            ArrayList<WebTabData> r02 = r0.getTabs();
            if (r02.isEmpty() == false) goto L8;
            return -1;
        L8:
            WebTabData r03 = r02.get(0);
            TabDrawable r04 = this.this$0.findTabDrawable(r03);
            if (r04 != null) goto L11;
            return -1;
        L11:
            this.this$0.getTabBounds(this.tmpRectF, r04.getPosition());
            Rect r05 = r04.closeRipple.getBounds();
            if (r05.isEmpty() == true) goto L18;
            RectF r1 = this.tmpRectF;
            if (r05.contains((int) (r6 - r1.left), (int) (r7 - r1.centerY())) == false) goto L18;
            return 2;
        L18:
            if (this.tmpRectF.contains(r6, r7) == false) goto L21;
            return 1;
        L21:
            return -1;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void getVisibleVirtualViews(List<Integer> r3) {
            BottomSheetTabs r0 = this.this$0;
            if (r0.drawTabs == false) goto L13;
            ArrayList<WebTabData> r02 = r0.getTabs();
            if (r02.isEmpty() == false) goto L9;
            return;
        L9:
            if (this.this$0.findTabDrawable(r02.get(0)) != null) goto L11;
            return;
        L11:
            r3.add(1);
            r3.add(2);
            return;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public boolean onPerformActionForVirtualView(int r3, int r4, Bundle r5) {
            if (r4 == 16) goto L5;
            return false;
        L5:
            ArrayList<WebTabData> r42 = this.this$0.getTabs();
            if (r42.isEmpty() == false) goto L8;
            return false;
        L8:
            WebTabData r43 = r42.get(0);
            if (r3 != 1) goto L13;
            this.this$0.click();
            return true;
        L13:
            if (r3 != 2) goto L16;
            this.this$0.removeTab(r43, new BottomSheetTabs$TabsAccessibilityHelper$$ExternalSyntheticLambda0());
            return true;
        L16:
            return false;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForVirtualView(int r8, AccessibilityNodeInfoCompat r9) {
            ArrayList<WebTabData> r0 = this.this$0.getTabs();
            TabDrawable r2 = null;
            if (r0.isEmpty() == false) goto L5;
            WebTabData r02 = null;
        L6:
            if (r02 == null) goto L9;
            r2 = this.this$0.findTabDrawable(r02);
        L9:
            r9.setClassName("android.widget.Button");
            r9.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r2 != null) goto L13;
            this.tmpRect.set(0, 0, 1, 1);
            r9.setBoundsInParent(this.tmpRect);
            r9.setContentDescription(_UrlKt.FRAGMENT_ENCODE_SET);
            r9.setVisibleToUser(false);
            return;
        L13:
            this.this$0.getTabBounds(this.tmpRectF, r2.getPosition());
            if (r02.getTitle() == null) goto L18;
            r1 = r02.getTitle();
        L18:
            if (r8 != 2) goto L25;
            Rect r82 = r2.closeRipple.getBounds();
            RectF r03 = this.tmpRectF;
            int r22 = (int) (r03.left + r82.left);
            int r04 = (int) (r03.centerY() + r82.top);
            RectF r4 = this.tmpRectF;
            this.tmpRect.set(r22, r04, (int) (r4.left + r82.right), (int) (r4.centerY() + r82.bottom));
            r9.setBoundsInParent(this.tmpRect);
            if (TextUtils.isEmpty(r1) == false) goto L22;
            String r7 = LocaleController.getString(R.string.Close);
        L23:
            r9.setContentDescription(r7);
            return;
        L22:
            r7 = LocaleController.getString(R.string.Close) + ", " + r1;
            goto L23
        L25:
            Rect r83 = this.tmpRect;
            RectF r05 = this.tmpRectF;
            r83.set((int) r05.left, (int) r05.top, (int) r05.right, (int) r05.bottom);
            r9.setBoundsInParent(this.tmpRect);
            if (TextUtils.isEmpty(r1) == false) goto L28;
            String r72 = LocaleController.getString(R.string.Open);
        L29:
            r9.setContentDescription(r72);
            return;
        L28:
            r72 = LocaleController.getString(R.string.Open) + ", " + r1;
            goto L29
        L5:
            r02 = r0.get(0);
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class WebTabData {
        public int actionBarColor;
        public int actionBarColorKey;
        public boolean allowSwipes;
        public float articleProgress;
        public ArticleViewer articleViewer;
        public boolean backButton;
        public int backgroundColor;
        public BotButtons.ButtonsState buttons;
        public boolean confirmDismiss;
        public String currentUrl;
        public boolean error;
        public String errorDescription;
        public boolean expanded;
        public float expandedOffset;
        public Bitmap favicon;
        public boolean fullscreen;
        public boolean fullscreenBlur;
        public boolean fullsize;
        public boolean isWeb;
        public String lastUrl;
        public int navigationBarColor;
        public boolean needsContext;
        public boolean orientationLocked;
        public boolean overrideActionBarColor;
        public boolean overrideBackgroundColor;
        public Bitmap previewBitmap;
        public Object previewNode;
        public WebViewRequestProps props;
        public Object proxy;
        public boolean ready;
        public boolean sameOrigin;
        public BotSensors sensors;
        public boolean settings;
        public boolean themeIsDark;
        public String title;
        public String trustedOrigin;
        public View view2;
        public int viewHeight;
        public int viewScroll;
        public int viewWidth;
        public BotWebViewContainer.MyWebView webView;

        public WebTabData() {
            this.expandedOffset = Float.MAX_VALUE;
            this.allowSwipes = true;
        }

        public void destroy() {
            BotWebViewContainer.MyWebView r0 = this.webView;     // Catch: Exception -> L9
            if (r0 == null) goto L5;
            r0.destroy();     // Catch: Exception -> L9
            this.webView = null;     // Catch: Exception -> L9
        L5:
            ArticleViewer r1 = this.articleViewer;     // Catch: Exception -> L9
            if (r1 == null) goto L14;
            r1.destroy();     // Catch: Exception -> L9
            return;
        L14:
            return;
        L9:
            e = move-exception;
            FileLog.e(e);
        }

        public String getTitle() {
            if (this.isWeb == true) goto L14;
            if (this.articleViewer != null) goto L14;
            WebViewRequestProps r0 = this.props;
            if (r0 != null) goto L12;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            return UserObject.getUserName(MessagesController.getInstance(r0.currentAccount).getUser(Long.valueOf(this.props.botId)));
        L14:
            if (TextUtils.isEmpty(this.title) == false) goto L18;
            return LocaleController.getString(R.string.WebEmpty);
        L18:
            return this.title;
        }

        public boolean isArticle() {
            ArticleViewer r0 = this.articleViewer;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.isLastArticle() == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    public static /* synthetic */ void $r8$lambda$6r2txJ0Yr4DK9EpwLcLpfe0Mu_M(Boolean r0) {
    }

    public static /* synthetic */ void $r8$lambda$DHX3tDYOdbiroYGzmt96_44TTqk(BottomSheetTabs r0, ArrayList r1, WebTabData r2) {
        r0.lambda$removeTab$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$KDhLOI7PODyeb3ZJQKyTx3hphBA(BottomSheetTabs r0, ValueAnimator r1) {
        r0.lambda$updateVisibility$7(r1);
    }

    /* renamed from: $r8$lambda$YlC0jcJtF6j5q-66U5tcU_4s1F8, reason: not valid java name */
    public static /* synthetic */ void m5725$r8$lambda$YlC0jcJtF6j5q66U5tcU_4s1F8(BottomSheetTabs r0, boolean[] r1, WebTabData r2, Utilities.Callback r3, AlertDialog[] r4, AlertDialog r5, int r6) {
        r0.lambda$removeTab$2(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$jPNRf9BTF_xqb6Li3oucaooqE3E(BottomSheetTabs r0, WebTabData r1, BaseFragment r2) {
        r0.lambda$openTab$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$jiJ7BtkC4NyWansFr7HjxaornNg(BottomSheetTabs r0, BaseFragment r1, BaseFragment r2) {
        r0.lambda$openTab$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kds1RKsT7D42Om7XAx69ikKtHJA(boolean[] r0, Utilities.Callback r1, AlertDialog[] r2, AlertDialog r3, int r4) {
        r0[0] = true;
        r1.run(Boolean.FALSE);
        r2[0].dismiss();
    }

    /* renamed from: $r8$lambda$wY--mzJHTOpTZmii2flzYplpdWs, reason: not valid java name */
    public static /* synthetic */ void m5726$r8$lambda$wYmzJHTOpTZmii2flzYplpdWs(boolean[] r1, Utilities.Callback r2, DialogInterface r3) {
        if (r1[0] == true) goto L6;
        r2.run(Boolean.FALSE);
        r1[0] = true;
        return;
    }

    /* renamed from: -$$Nest$fgetbottomTabsAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m5727$$Nest$fgetbottomTabsAnimator(BottomSheetTabs r0) {
        return r0.bottomTabsAnimator;
    }

    /* renamed from: -$$Nest$fgetinvalidateListeners, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashSet m5728$$Nest$fgetinvalidateListeners(BottomSheetTabs r0) {
        return r0.invalidateListeners;
    }

    /* renamed from: -$$Nest$smgetTextPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextPaint m5729$$Nest$smgetTextPaint() {
        return getTextPaint();
    }

    static {
        tabs = new HashMap();
        tabDrawables = new HashMap();
    }

    public BottomSheetTabs(Context r9, ActionBarLayout r10) {
        super(r9);
        this.backgroundPaint = new Paint(1);
        this.drawTabs = true;
        this.doNotDismiss = false;
        CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.backgroundColorAnimated = new AnimatedColor(this, 0, 200, r7);
        this.tabColorAnimated = new AnimatedColor(this, 0, 200, r7);
        this.tabDarkAnimated = new AnimatedFloat(this, 0, 200, r7);
        this.currentAccount = UserConfig.selectedAccount;
        this.rect = new RectF();
        this.invalidateListeners = new HashSet();
        this.relayoutListeners = new HashSet();
        this.actionBarLayout = r10;
        setNavigationBarColor(Theme.getColor(Theme.key_windowBackgroundGray));
        TabsAccessibilityHelper r8 = new TabsAccessibilityHelper(this, this);
        this.accessibilityHelper = r8;
        ViewCompat.setAccessibilityDelegate(this, r8);
        updateMultipleTitle();
        updateVisibility(false);
    }

    private static TextPaint getTextPaint() {
        if (textPaint != null) goto L6;
        TextPaint r0 = new TextPaint(1);
        textPaint = r0;
        r0.setTypeface(AndroidUtilities.bold());
        textPaint.setTextSize(AndroidUtilities.dp(17.0f));
    L6:
        return textPaint;
    }

    private /* synthetic */ void lambda$openTab$0(WebTabData r5, BaseFragment r6) {
        if (r6 != null) goto L5;
        return;
    L5:
        if ((r6 instanceof ChatActivity) == false) goto L10;
        ChatActivity r0 = (ChatActivity) r6;
        if (r0.getChatActivityEnterView() == null) goto L10;
        r0.getChatActivityEnterView().closeKeyboard();
        r0.getChatActivityEnterView().hidePopup(true, false);
    L10:
        if (r6.getContext() != null) goto L12;
        return;
    L12:
        if (r6.getParentActivity() == null) goto L21;
        BotWebViewSheet r02 = new BotWebViewSheet(r6.getContext(), r6.getResourceProvider());
        r02.setParentActivity(r6.getParentActivity());
        if (r02.restoreState(r6, r5) == false) goto L19;
        removeTab(r5, false);
        r02.show();
        return;
    L19:
        return;
    }

    private /* synthetic */ void lambda$openTab$1(BaseFragment r1, BaseFragment r2) {
        r1.presentFragment(r2);
        this.doNotDismiss = false;
    }

    private /* synthetic */ void lambda$removeTab$2(boolean[] r1, WebTabData r2, Utilities.Callback r3, AlertDialog[] r4, AlertDialog r5, int r6) {
        r1[0] = true;
        removeTab(r2, true);
        r3.run(Boolean.TRUE);
        r4[0].dismiss();
    }

    private /* synthetic */ void lambda$removeTab$5(ArrayList r3, WebTabData r4) {
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L9;
        if (((TabDrawable) r3.get(r0)).tab != r4) goto L8;
        r3.remove(r0);
        r0 = r0 - 1;
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        invalidate();
    }

    private /* synthetic */ void lambda$updateVisibility$7(ValueAnimator r2) {
        this.bottomTabsProgress = ((Float) r2.getAnimatedValue()).floatValue();
        Iterator<Runnable> r22 = this.invalidateListeners.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r22.next().run();
        goto L4
    L6:
        invalidate();
    }

    private void updateMultipleTitle() {
        ArrayList<WebTabData> r0 = getTabs();
        ArrayList<TabDrawable> r1 = getTabDrawables();
        CharSequence r5 = null;
        int r4 = 0;
    L4:
        if (r4 >= r1.size()) goto L14;
        TabDrawable r52 = r1.get(r4);
        if (r0.size() > 1) goto L8;
    L11:
        CharSequence r6 = Emoji.replaceEmoji(r52.tab.getTitle(), getTextPaint().getFontMetricsInt(), false);
        r52.setOverrideTitle(null);
    L10:
        r5 = r6;
        r4 = r4 + 1;
        goto L4
    L8:
        if (TabDrawable.m5730$$Nest$fgetposition(r52) != 0) goto L11;
        r6 = Emoji.replaceEmoji(LocaleController.formatPluralString("BotMoreTabs", r0.size() - 1, new Object[]{r52.tab.getTitle()}), getTextPaint().getFontMetricsInt(), false);
        r52.setOverrideTitle(r6);
        goto L10
    L14:
        if (r0.isEmpty() == false) goto L17;
        setImportantForAccessibility(2);
        setContentDescription(LocaleController.formatString(R.string.AccDescrTabs, new Object[]{_UrlKt.FRAGMENT_ENCODE_SET}));
        return;
    L17:
        setImportantForAccessibility(1);
        int r02 = R.string.AccDescrTabs;
        if (r5 != null) goto L20;
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        setContentDescription(LocaleController.formatString(r02, new Object[]{r5}));
    }

    public static String urlWithoutFragment(String r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        int r0 = r2.indexOf(35);
        if (r0 >= 0) goto L8;
        return r2;
    L8:
        return r2.substring(0, r0 + 1);
    }

    public void click() {
        ArrayList<WebTabData> r0 = getTabs();
        int r1 = r0.size();
        if (r1 != 0) goto L5;
        return;
    L5:
        WebTabData r02 = r0.get(r0.size() - 1);
        LaunchActivity r2 = LaunchActivity.instance;
        if (r2 != null) goto L8;
        BottomSheetTabsOverlay r22 = null;
    L9:
        if (r22 == null) goto L11;
        r22.stopAnimations();
    L11:
        if (r1 == 1) goto L16;
        if (r22 == null) goto L16;
        r22.openTabsView();
        return;
    L16:
        openTab(r02);
        return;
    L8:
        r22 = r2.getBottomSheetTabsOverlay();
        goto L9
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r14) {
        getTabs();
        ArrayList<TabDrawable> r0 = getTabDrawables();
        if (this.bottomTabsProgress <= 0.0f) goto L26;
        this.backgroundPaint.setColor(this.backgroundColorAnimated.set(this.backgroundColor));
        super.dispatchDraw(r14);
        int r1 = this.tabColorAnimated.set(this.tabColor);
        float r3 = this.tabDarkAnimated.set(this.tabIsDark);
        if (this.drawTabs == false) goto L22;
        int r5 = 0;
    L9:
        if (r5 >= r0.size()) goto L27;
        TabDrawable r7 = r0.get(r5);
        float r6 = r7.getPosition();
        float r11 = r7.getAlpha();
        if (r11 > 0.0f) goto L14;
    L12:
        Canvas r8 = r14;
    L21:
        r5 = r5 + 1;
        r14 = r8;
        goto L9
    L14:
        if (r6 > 1.99f) goto L12;
        getTabBounds(this.rect, r6);
        r7.setExpandProgress(0.0f);
        if (r3 <= 0.5f) goto L19;
        boolean r62 = true;
    L20:
        r7.setBackgroundColor(r1, r62);
        r8 = r14;
        r7.draw(r8, this.rect, AndroidUtilities.dp(18.0f), r11, 1.0f);
        goto L21
    L19:
        r62 = false;
        goto L20
    L27:
        return;
    L22:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchHoverEvent(MotionEvent r2) {
        if (this.drawTabs == false) goto L13;
        if (getTabs().isEmpty() == true) goto L13;
        TabsAccessibilityHelper r0 = this.accessibilityHelper;
        if (r0 == null) goto L13;
        if (r0.dispatchHoverEvent(r2) == false) goto L13;
        return true;
    L13:
        return super.dispatchHoverEvent(r2);
    }

    public TabDrawable findTabDrawable(WebTabData r3) {
        ArrayList<TabDrawable> r2 = getTabDrawables();
        int r0 = 0;
    L4:
        if (r0 >= r2.size()) goto L10;
        if (r2.get(r0).tab == r3) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return r2.get(r0);
    L10:
        return null;
    }

    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }

    public int getExpandedHeight() {
        int r1 = getTabs().size();
        if (r1 != 0) goto L7;
        return 0;
    L7:
        if (r1 != 1) goto L11;
        return AndroidUtilities.dp(60.0f);
    L11:
        return AndroidUtilities.dp(68.0f);
    }

    public int getHeight(boolean r1) {
        if (r1 == false) goto L6;
        return (int) this.bottomTabsProgress;
    L6:
        return this.bottomTabsHeight;
    }

    public void getTabBounds(RectF r6, float r7) {
        r6.set(AndroidUtilities.dp(4.0f), (getHeight() - AndroidUtilities.dp(4.0f)) - AndroidUtilities.dp(50.0f), getWidth() - AndroidUtilities.dp(4.0f), getHeight() - AndroidUtilities.dp(4.0f));
        r6.offset(0.0f, (-AndroidUtilities.dp(8.0f)) * r7);
        float r5 = AndroidUtilities.lerp(1.0f, 0.95f, Math.abs(r7));
        float r72 = r6.centerX();
        float r0 = r6.centerY();
        float r1 = r6.width();
        float r2 = r6.height();
        float r12 = (r1 / 2.0f) * r5;
        r6.left = r72 - r12;
        r6.right = r72 + r12;
        float r22 = (r2 / 2.0f) * r5;
        r6.top = r0 - r22;
        r6.bottom = r0 + r22;
    }

    public ArrayList<TabDrawable> getTabDrawables() {
        return getTabDrawables(this.currentAccount);
    }

    public ArrayList<TabDrawable> getTabDrawables(int r2) {
        HashMap<Integer, ArrayList<TabDrawable>> r1 = tabDrawables;
        ArrayList<TabDrawable> r0 = r1.get(Integer.valueOf(r2));
        if (r0 != null) goto L6;
        Integer r22 = Integer.valueOf(r2);
        ArrayList<TabDrawable> r02 = new ArrayList();
        r1.put(r22, r02);
        return r02;
    L6:
        return r0;
    }

    public ArrayList<WebTabData> getTabs() {
        return getTabs(this.currentAccount);
    }

    public ArrayList<WebTabData> getTabs(int r2) {
        HashMap<Integer, ArrayList<WebTabData>> r1 = tabs;
        ArrayList<WebTabData> r0 = r1.get(Integer.valueOf(r2));
        if (r0 != null) goto L6;
        Integer r22 = Integer.valueOf(r2);
        ArrayList<WebTabData> r02 = new ArrayList();
        r1.put(r22, r02);
        return r02;
    L6:
        return r0;
    }

    public void listen(Runnable r2, Runnable r3) {
        this.invalidateListeners.add(r2);
        this.relayoutListeners.add(r3);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r4) {
        if (touchEvent(r4.getAction(), r4.getX(), r4.getY()) == false) goto L5;
        return true;
    L5:
        if (super.onTouchEvent(r4) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void openTab(WebTabData r7) {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getParentActivity() == null) goto L26;
        boolean r1 = r0 instanceof ChatActivity;
        if (r1 == false) goto L13;
        ChatActivity r4 = (ChatActivity) r0;
        if (r4.getChatActivityEnterView() == null) goto L13;
        r4.getChatActivityEnterView().closeKeyboard();
        r4.getChatActivityEnterView().hidePopup(true, false);
    L13:
        if (r7.articleViewer == null) goto L16;
        EmptyBaseFragment r02 = this.actionBarLayout.getSheetFragment();
        ArticleViewer r12 = r7.articleViewer;
        BottomSheetTabDialog.checkSheet(r12.sheet);
        r02.addSheet(r12.sheet);
        r12.sheet.reset();
        r12.setParentActivity(r02.getParentActivity(), r02);
        r12.sheet.attachInternal(r02);
        r12.sheet.animateOpen(true, true, null);
        removeTab(r7, false);
        return;
    L16:
        new BottomSheetTabs$$ExternalSyntheticLambda0(this, r7).run(r0);
        if (r7.needsContext == false) goto L24;
        if (r1 == true) goto L20;
    L21:
        this.doNotDismiss = true;
        AndroidUtilities.runOnUIThread(new BottomSheetTabs$$ExternalSyntheticLambda1(this, r0, ChatActivity.of(r7.props.botId)), 220);
        return;
    L20:
        if (((ChatActivity) r0).getDialogId() != r7.props.botId) goto L21;
        return;
    L24:
        return;
    }

    public TabDrawable pushTab(WebTabData r7) {
        ArrayList<WebTabData> r0 = getTabs();
        ArrayList<TabDrawable> r1 = getTabDrawables();
        TabDrawable r2 = new TabDrawable(this, r7);
        r2.animatedPosition.set(-1.0f, true);
        r2.animatedAlpha.set(0.0f, true);
        r1.add(r2);
        int r3 = 0;
        r0.add(0, r7);
    L4:
        if (r3 >= r1.size()) goto L9;
        TabDrawable r72 = r1.get(r3);
        int r4 = r0.indexOf(r72.tab);
        r72.index = r4;
        if (r4 < 0) goto L8;
        TabDrawable.m5731$$Nest$fputposition(r72, r4);
    L8:
        r3 = r3 + 1;
        goto L4
    L9:
        updateMultipleTitle();
        updateVisibility(true);
        invalidate();
        TabsAccessibilityHelper r6 = this.accessibilityHelper;
        if (r6 == null) goto L12;
        r6.invalidateRoot();
    L12:
        return r2;
    }

    public boolean removeAll() {
        ArrayList<WebTabData> r0 = getTabs();
        ArrayList<TabDrawable> r1 = getTabDrawables();
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r3 >= r0.size()) goto L6;
        r0.get(r3).destroy();
        r3 = r3 + 1;
        goto L4
    L6:
        r0.clear();
    L8:
        if (r2 >= r1.size()) goto L10;
        r1.get(r2).index = -1;
        r2 = r2 + 1;
        goto L8
    L10:
        updateMultipleTitle();
        updateVisibility(true);
        invalidate();
        return r0.isEmpty();
    }

    public void removeTab(WebTabData r10, Utilities.Callback<Boolean> r11) {
        if (r10 != null) goto L6;
        r11.run(Boolean.TRUE);
        return;
    L6:
        if (r10.confirmDismiss == true) goto L9;
        removeTab(r10, true);
        r11.run(Boolean.TRUE);
        return;
    L9:
        TLRPC.User r0 = MessagesController.getInstance(r10.props.currentAccount).getUser(Long.valueOf(r10.props.botId));
        if (r0 == null) goto L12;
        String r02 = ContactsController.formatName(r0.first_name, r0.last_name);
    L13:
        boolean[] r4 = {false};
        AlertDialog r9 = new AlertDialog.Builder(getContext()).setTitle(r02).setMessage(LocaleController.getString(R.string.BotWebViewChangesMayNotBeSaved)).setPositiveButton(LocaleController.getString(R.string.BotWebViewCloseAnyway), new BottomSheetTabs$$ExternalSyntheticLambda4(this, r4, r10, r11, r7)).setNegativeButton(LocaleController.getString(R.string.Cancel), new BottomSheetTabs$$ExternalSyntheticLambda5(r4, r11, r7)).create();
        AlertDialog[] r7 = {r9};
        r9.setOnDismissListener(new BottomSheetTabs$$ExternalSyntheticLambda6(r4, r11));
        r7[0].show();
        ((TextView) r7[0].getButton(-1)).setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L12:
        r02 = null;
        goto L13
    }

    public boolean removeTab(int r4, WebTabData r5, boolean r6) {
        ArrayList<WebTabData> r0 = getTabs(r4);
        ArrayList<TabDrawable> r42 = getTabDrawables(r4);
        r0.remove(r5);
        if (r6 == false) goto L5;
        r5.destroy();
    L5:
        int r62 = 0;
    L7:
        if (r62 >= r42.size()) goto L12;
        TabDrawable r1 = r42.get(r62);
        int r2 = r0.indexOf(r1.tab);
        r1.index = r2;
        if (r2 < 0) goto L11;
        TabDrawable.m5731$$Nest$fputposition(r1, r2);
    L11:
        r62 = r62 + 1;
        goto L7
    L12:
        updateMultipleTitle();
        AndroidUtilities.runOnUIThread(new BottomSheetTabs$$ExternalSyntheticLambda7(this, r42, r5), 320);
        updateVisibility(true);
        invalidate();
        TabsAccessibilityHelper r3 = this.accessibilityHelper;
        if (r3 == null) goto L16;
        r3.invalidateRoot();
    L16:
        return r0.isEmpty();
    }

    public boolean removeTab(WebTabData r2, boolean r3) {
        return removeTab(this.currentAccount, r2, r3);
    }

    public void setCurrentAccount(int r2) {
        if (this.currentAccount == r2) goto L6;
        this.currentAccount = r2;
        updateVisibility(false);
        invalidate();
        return;
    }

    public void setNavigationBarColor(int r2) {
        setNavigationBarColor(r2, true);
    }

    public void setNavigationBarColor(int r6, boolean r7) {
        if (r6 == this.backgroundColor) goto L24;
        ActionBarLayout r0 = this.actionBarLayout;
        boolean r2 = false;
        if (r0.startedTracking == true) goto L7;
    L8:
        r7 = false;
    L9:
        this.backgroundColor = r6;
        if (AndroidUtilities.computePerceivedBrightness(r6) >= 0.721f) goto L12;
        boolean r02 = true;
    L13:
        if (r02 == false) goto L15;
        float r03 = 0.08f;
    L16:
        int r62 = Theme.blendOver(r6, Theme.multAlpha(-1, r03));
        this.tabColor = r62;
        if (AndroidUtilities.computePerceivedBrightness(r62) >= 0.721f) goto L19;
        r2 = true;
    L19:
        this.tabIsDark = r2;
        if (r7 == true) goto L22;
        this.backgroundColorAnimated.set(this.backgroundColor, true);
        this.tabColorAnimated.set(this.tabColor, true);
        this.tabDarkAnimated.set(this.tabIsDark, true);
    L22:
        invalidate();
        return;
    L15:
        r03 = 0.75f;
        goto L16
    L12:
        r02 = false;
        goto L13
    L7:
        if (r0.animationInProgress == false) goto L9;
    }

    public void setupTab(TabDrawable r3) {
        int r0 = this.tabColorAnimated.set(this.tabColor);
        float r2 = this.tabDarkAnimated.set(this.tabIsDark);
        r3.setExpandProgress(0.0f);
        if (r2 <= 0.5f) goto L5;
        boolean r22 = true;
    L6:
        r3.setBackgroundColor(r0, r22);
        return;
    L5:
        r22 = false;
        goto L6
    }

    public void stopListening(Runnable r2, Runnable r3) {
        this.invalidateListeners.remove(r2);
        this.relayoutListeners.remove(r3);
    }

    public boolean touchEvent(int r8, float r9, float r10) {
        ArrayList<WebTabData> r0 = getTabs();
        ArrayList<TabDrawable> r1 = getTabDrawables();
        if (this.drawTabs == true) goto L5;
        this.hit = false;
        this.closeRippleHit = false;
    L47:
        if (this.hit == false) goto L49;
    L52:
        return true;
    L49:
        if (this.closeRippleHit == true) goto L52;
        return false;
    L5:
        if (r0.isEmpty() == false) goto L7;
        WebTabData r02 = null;
    L8:
        TabDrawable r2 = findTabDrawable(r02);
        if (r2 == null) goto L44;
        getTabBounds(this.rect, r2.getPosition());
        if (r8 == 0) goto L26;
        if (r8 == 2) goto L26;
        if (r8 == 1) goto L18;
        if (r8 == 3) goto L18;
    L37:
        int r82 = 0;
    L39:
        if (r82 >= r1.size()) goto L47;
        if (r1.get(r82) == r2) goto L43;
        r1.get(r82).closeRipple.setState(new int[0]);
    L43:
        r82 = r82 + 1;
    L18:
        if (this.hit == false) goto L22;
        if (r8 != 1) goto L22;
        click();
    L25:
        this.closeRippleHit = false;
        this.hit = false;
        r2.closeRipple.setState(new int[0]);
    L22:
        if (this.closeRippleHit == false) goto L25;
        if (r8 != 1) goto L25;
        removeTab(r02, new BottomSheetTabs$$ExternalSyntheticLambda3());
    L26:
        Rect r83 = r2.closeRipple.getBounds();
        RectF r03 = this.rect;
        boolean r84 = r83.contains((int) (r9 - r03.left), (int) (r10 - r03.centerY()));
        this.closeRippleHit = r84;
        if (r84 == false) goto L29;
    L31:
        boolean r85 = false;
    L32:
        this.hit = r85;
        Drawable r86 = r2.closeRipple;
        if (this.closeRippleHit == false) goto L35;
        int[] r92 = {android.R.attr.state_pressed, android.R.attr.state_enabled};
    L36:
        r86.setState(r92);
        goto L37
    L35:
        r92 = new int[0];
        goto L36
    L29:
        if (this.rect.contains(r9, r10) == false) goto L31;
        r85 = true;
        goto L32
    L44:
        this.hit = false;
        this.closeRippleHit = false;
        goto L47
    L7:
        r02 = r0.get(0);
        goto L8
    }

    public boolean tryRemoveTabWith(ArticleViewer r8) {
        int r1 = 0;
    L3:
        HashMap<Integer, ArrayList<WebTabData>> r2 = tabs;
        if (r1 >= r2.size()) goto L14;
        ArrayList<WebTabData> r22 = r2.get(Integer.valueOf(r1));
        if (r22 == null) goto L13;
        int r3 = r22.size();
        int r4 = 0;
    L8:
        if (r4 >= r3) goto L13;
        WebTabData r5 = r22.get(r4);
        r4 = r4 + 1;
        WebTabData r52 = r5;
        if (r52.articleViewer != r8) goto L8;
        return removeTab(r1, r52, true);
    L13:
        r1 = r1 + 1;
        goto L3
    L14:
        return false;
    }

    public WebTabData tryReopenTab(String r8) {
        if (TextUtils.isEmpty(r8) == false) goto L5;
        return null;
    L5:
        ArrayList<WebTabData> r0 = getTabs();
        int r3 = 0;
    L7:
        if (r3 >= r0.size()) goto L31;
        WebTabData r4 = r0.get(r3);
        ArticleViewer r5 = r4.articleViewer;
        if (r5 == null) goto L30;
        if (r5.pagesStack.isEmpty() == true) goto L30;
        Object r52 = r4.articleViewer.pagesStack.get(r5.size() - 1);
        if ((r52 instanceof ArticleViewer.CachedWeb) == false) goto L30;
        BotWebViewContainer.MyWebView r53 = ((ArticleViewer.CachedWeb) r52).webView;
        if (r53 != null) goto L21;
        ArticleViewer.PageLayout[] r6 = r4.articleViewer.pages;
        if (r6 == null) goto L21;
        ArticleViewer.PageLayout r62 = r6[0];
        if (r62 == null) goto L21;
        r53 = r62.getWebView();
    L21:
        if (r53 == null) goto L30;
        if (r53.canGoBack() == false) goto L25;
        String r54 = r53.getUrl();
    L27:
        if (TextUtils.equals(urlWithoutFragment(r54), urlWithoutFragment(r8)) == false) goto L30;
        openTab(r4);
        return r4;
    L25:
        r54 = r53.getOpenURL();
    L30:
        r3 = r3 + 1;
        goto L7
    L31:
        return null;
    }

    public WebTabData tryReopenTab(MessageObject r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        TLRPC.Message r22 = r2.messageOwner;
        if (r22 != null) goto L8;
        return null;
    L8:
        TLRPC.MessageMedia r23 = r22.media;
        if (r23 != null) goto L11;
        return null;
    L11:
        TLRPC.WebPage r24 = r23.webpage;
        if (r24 != null) goto L15;
        return null;
    L15:
        return tryReopenTab(r24);
    }

    public WebTabData tryReopenTab(TLRPC.WebPage r9) {
        if (r9 != null) goto L5;
        return null;
    L5:
        ArrayList<WebTabData> r1 = getTabs();
        int r2 = 0;
    L7:
        if (r2 >= r1.size()) goto L19;
        WebTabData r3 = r1.get(r2);
        ArticleViewer r4 = r3.articleViewer;
        if (r4 == null) goto L18;
        if (r4.pagesStack.isEmpty() == true) goto L18;
        Object r42 = r3.articleViewer.pagesStack.get(r4.size() - 1);
        if ((r42 instanceof TLRPC.WebPage) == false) goto L18;
        if (((TLRPC.WebPage) r42).id != r9.id) goto L18;
        openTab(r3);
        return r3;
    L18:
        r2 = r2 + 1;
        goto L7
    L19:
        return null;
    }

    public WebTabData tryReopenTab(WebViewRequestProps r6) {
        HashMap<Integer, ArrayList<WebTabData>> r0 = tabs;
        ArrayList<WebTabData> r1 = r0.get(Integer.valueOf(this.currentAccount));
        if (r1 != null) goto L6;
        Integer r12 = Integer.valueOf(this.currentAccount);
        ArrayList<WebTabData> r2 = new ArrayList();
        r0.put(r12, r2);
        r1 = r2;
    L6:
        if (r6 != null) goto L8;
        return null;
    L8:
        int r22 = 0;
    L10:
        if (r22 >= r1.size()) goto L16;
        WebTabData r3 = r1.get(r22);
        if (r6.equals(r3.props) == true) goto L13;
        r22 = r22 + 1;
        goto L10
    L13:
        openTab(r3);
        return r3;
    L16:
        return null;
    }

    public void updateCurrentAccount() {
        setCurrentAccount(UserConfig.selectedAccount);
    }

    public void updateVisibility(boolean r4) {
        if (this.bottomTabsHeight == getExpandedHeight()) goto L21;
        ValueAnimator r0 = this.bottomTabsAnimator;
        if (r0 == null) goto L8;
        this.bottomTabsAnimator = null;
        r0.cancel();
    L8:
        this.bottomTabsHeight = getExpandedHeight();
        Iterator<Runnable> r02 = this.relayoutListeners.iterator();
    L10:
        if (r02.hasNext() == false) goto L12;
        r02.next().run();
        goto L10
    L12:
        if (r4 == false) goto L14;
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{this.bottomTabsProgress, this.bottomTabsHeight});
        this.bottomTabsAnimator = r42;
        r42.addUpdateListener(new BottomSheetTabs$$ExternalSyntheticLambda2(this));
        this.bottomTabsAnimator.addListener(new AnonymousClass1(this));
        this.bottomTabsAnimator.setDuration(250);
        this.bottomTabsAnimator.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        this.bottomTabsAnimator.start();
    L15:
        Object r3 = getParent();
        if ((r3 instanceof View) == false) goto L20;
        ViewCompat.requestApplyInsets((View) r3);
        return;
    L20:
        return;
    L14:
        this.bottomTabsProgress = this.bottomTabsHeight;
        invalidate();
        goto L15
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r1) {
        super.verifyDrawable(r1);
        return true;
    }
}
