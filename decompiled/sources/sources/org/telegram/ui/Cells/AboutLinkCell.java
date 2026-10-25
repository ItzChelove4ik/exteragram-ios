package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.StaticLayoutEx;
import org.telegram.ui.Components.URLSpanNoUnderline;

/* loaded from: classes6.dex */
public abstract class AboutLinkCell extends FrameLayout {
    private static final int COLLAPSED_HEIGHT = 0;
    private static final int MAX_OPEN_HEIGHT = 0;
    private static final int MOST_SPEC = 0;
    final float SPACE;
    private CharSequence accessibilityText;
    private Paint backgroundPaint;
    private ValueAnimator collapseAnimator;
    private FrameLayout container;
    private LoadingDrawable currentLoading;
    private Browser.Progress currentProgress;
    private float expandT;
    private boolean expanded;
    private StaticLayout firstThreeLinesLayout;
    private int lastInlineLine;
    private int lastMaxWidth;
    private LinkSpanDrawable.LinkCollector links;
    Runnable longPressedRunnable;
    private boolean moreButtonDisabled;
    private boolean needDivider;
    private boolean needSpace;
    private StaticLayout[] nextLinesLayouts;
    private Point[] nextLinesLayoutsPositions;
    private String oldText;
    private BaseFragment parentFragment;
    private LinkSpanDrawable pressedLink;
    private Layout pressedLinkLayout;
    private float pressedLinkYOffset;
    private float rawCollapseT;
    private Theme.ResourcesProvider resourcesProvider;
    private Drawable rippleBackground;
    private boolean shouldExpand;
    private Drawable showMoreBackgroundDrawable;
    private FrameLayout showMoreTextBackgroundView;
    private TextView showMoreTextView;
    private SpannableStringBuilder stringBuilder;
    private StaticLayout textLayout;
    private int textX;
    private int textY;
    private LinkPath urlPath;
    private Point urlPathOffset;
    private TextView valueTextView;

    /* renamed from: org.telegram.ui.Cells.AboutLinkCell$1, reason: invalid class name */
    public class AnonymousClass1 extends TextView {
        private boolean pressed;
        final /* synthetic */ AboutLinkCell this$0;

        public AnonymousClass1(AboutLinkCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.pressed = false;
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r5) {
            if (this.pressed == false) goto L5;
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, 0.0f, getWidth(), getHeight());
            r5.drawRoundRect(r0, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), Theme.chat_urlPaint);
        L5:
            super.onDraw(r5);
        }

        @Override // android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r7) {
            getParent().requestDisallowInterceptTouchEvent(true);
            boolean r0 = this.pressed;
            int r2 = r7.getAction();
            if (r2 != 0) goto L5;
            this.pressed = true;
        L28:
            if (r0 == this.pressed) goto L30;
            invalidate();
        L30:
            return true;
        L5:
            if (r2 == 1) goto L23;
            if (r2 != 2) goto L9;
            float r22 = r7.getX();
            float r72 = r7.getY();
            if (r22 >= 0.0f) goto L15;
        L21:
            this.pressed = false;
            goto L28
        L15:
            if (r22 >= getWidth()) goto L21;
            if (r72 < 0.0f) goto L21;
            if (r72 >= getHeight()) goto L21;
        L9:
            if (r2 != 3) goto L28;
            this.pressed = false;
            goto L28
        L23:
            if (this.pressed == false) goto L25;
            this.this$0.updateCollapse(true, true);
        L25:
            this.pressed = false;
            goto L28
        }
    }

    /* renamed from: org.telegram.ui.Cells.AboutLinkCell$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ AboutLinkCell this$0;

        public static /* synthetic */ void $r8$lambda$21Qh_bbK8eP3C7r0lGPuiL5mdlc(AnonymousClass2 r0, DialogInterface r1) {
            r0.lambda$run$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$rsH2gaoC9o6504jFqgZs4utXGGI(AnonymousClass2 r0, ClickableSpan r1, Layout r2, float r3, String r4, DialogInterface r5, int r6) {
            r0.lambda$run$0(r1, r2, r3, r4, r5, r6);
        }

        public AnonymousClass2(AboutLinkCell r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$run$0(ClickableSpan r1, Layout r2, float r3, String r4, DialogInterface r5, int r6) {
            if (r6 != 0) goto L6;
            AboutLinkCell.m6609$$Nest$monLinkClick(this.this$0, r1, r2, r3);
            return;
        L6:
            if (r6 != 1) goto L22;
            AndroidUtilities.addToClipboard(r4);
            if (AndroidUtilities.shouldShowClipboardToast() == true) goto L10;
            return;
        L10:
            if (r4.startsWith("@") == false) goto L14;
            BulletinFactory.of(AboutLinkCell.m6599$$Nest$fgetparentFragment(this.this$0)).createSimpleBulletin(R.raw.copy, LocaleController.getString(R.string.UsernameCopied)).show();
            return;
        L14:
            if (r4.startsWith("#") == false) goto L16;
        L20:
            BulletinFactory.of(AboutLinkCell.m6599$$Nest$fgetparentFragment(this.this$0)).createSimpleBulletin(R.raw.copy, LocaleController.getString(R.string.HashtagCopied)).show();
            return;
        L16:
            if (r4.startsWith("$") == true) goto L20;
            BulletinFactory.of(AboutLinkCell.m6599$$Nest$fgetparentFragment(this.this$0)).createSimpleBulletin(R.raw.copy, LocaleController.getString(R.string.LinkCopied)).show();
            return;
        }

        private /* synthetic */ void lambda$run$1(DialogInterface r1) {
            AboutLinkCell.m6610$$Nest$mresetPressedLink(this.this$0);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AboutLinkCell.m6600$$Nest$fgetpressedLink(this.this$0) == null) goto L23;
            boolean r0 = AboutLinkCell.m6600$$Nest$fgetpressedLink(this.this$0).getSpan() instanceof URLSpanNoUnderline;
            AboutLinkCell r1 = this.this$0;
            if (r0 == false) goto L8;
            String r02 = ((URLSpanNoUnderline) AboutLinkCell.m6600$$Nest$fgetpressedLink(r1).getSpan()).getURL();
        L7:
            String r6 = r02;
            this.this$0.performHapticFeedback(0, 2);     // Catch: Exception -> L20
        L14:
            Layout r4 = AboutLinkCell.m6601$$Nest$fgetpressedLinkLayout(this.this$0);
            float r5 = AboutLinkCell.m6602$$Nest$fgetpressedLinkYOffset(this.this$0);
            if (this.this$0.getContext() == null) goto L17;
            ClickableSpan r3 = (ClickableSpan) AboutLinkCell.m6600$$Nest$fgetpressedLink(this.this$0).getSpan();
            BottomSheet.Builder r7 = new BottomSheet.Builder(this.this$0.getContext());
            r7.setTitle(r6);
            CharSequence[] r03 = {LocaleController.getString(R.string.Open), LocaleController.getString(R.string.Copy)};
            AnonymousClass2 r2 = this;
            r7.setItems(r03, new AboutLinkCell$2$$ExternalSyntheticLambda0(r2, r3, r4, r5, r6));
            r7.setOnPreDismissListener(new AboutLinkCell$2$$ExternalSyntheticLambda1(r2));
            r7.show();
        L18:
            AboutLinkCell.m6608$$Nest$fputpressedLink(r2.this$0, null);
            return;
        L17:
            r2 = this;
            goto L18
        L8:
            boolean r04 = AboutLinkCell.m6600$$Nest$fgetpressedLink(r1).getSpan() instanceof URLSpan;
            AboutLinkCell r12 = this.this$0;
            if (r04 == false) goto L11;
            r02 = ((URLSpan) AboutLinkCell.m6600$$Nest$fgetpressedLink(r12).getSpan()).getURL();
            goto L7
        L11:
            r02 = AboutLinkCell.m6600$$Nest$fgetpressedLink(r12).getSpan().toString();
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Cells.AboutLinkCell$3, reason: invalid class name */
    public class AnonymousClass3 extends Browser.Progress {
        final /* synthetic */ AboutLinkCell this$0;
        LoadingDrawable thisLoading;
        final /* synthetic */ Layout val$layout;
        final /* synthetic */ ClickableSpan val$pressedLink;
        final /* synthetic */ float val$yOffset;

        /* renamed from: $r8$lambda$3qAZlQ8-3S6AgX3hOVzqJHaSTZg, reason: not valid java name */
        public static /* synthetic */ void m6611$r8$lambda$3qAZlQ83S6AgX3hOVzqJHaSTZg(AnonymousClass3 r0) {
            r0.lambda$end$0();
        }

        public AnonymousClass3(AboutLinkCell r1, Layout r2, ClickableSpan r3, float r4) {
            this.this$0 = r1;
            this.val$layout = r2;
            this.val$pressedLink = r3;
            this.val$yOffset = r4;
        }

        private /* synthetic */ void lambda$end$0() {
            if (this.thisLoading == null) goto L6;
            AboutLinkCell.m6598$$Nest$fgetlinks(this.this$0).removeLoading(this.thisLoading, true);
            return;
        }

        @Override // org.telegram.messenger.browser.Browser.Progress
        public void end(boolean r2) {
            AboutLinkCell$3$$ExternalSyntheticLambda0 r0 = new AboutLinkCell$3$$ExternalSyntheticLambda0(this);
            if (r2 == false) goto L5;
            long r1 = 0;
        L6:
            AndroidUtilities.runOnUIThread(r0, r1);
            return;
        L5:
            r1 = 350;
            goto L6
        }

        @Override // org.telegram.messenger.browser.Browser.Progress
        public void init() {
            if (AboutLinkCell.m6597$$Nest$fgetcurrentLoading(this.this$0) == null) goto L5;
            AboutLinkCell.m6598$$Nest$fgetlinks(this.this$0).removeLoading(AboutLinkCell.m6597$$Nest$fgetcurrentLoading(this.this$0), true);
        L5:
            AboutLinkCell r0 = this.this$0;
            LoadingDrawable r1 = LinkSpanDrawable.LinkCollector.makeLoading(this.val$layout, this.val$pressedLink, this.val$yOffset);
            this.thisLoading = r1;
            AboutLinkCell.m6606$$Nest$fputcurrentLoading(r0, r1);
            AboutLinkCell r02 = this.this$0;
            int r03 = r02.processColor(Theme.getColor(Theme.key_chat_linkSelectBackground, AboutLinkCell.m6603$$Nest$fgetresourcesProvider(r02)));
            this.thisLoading.setColors(Theme.multAlpha(r03, 0.8f), Theme.multAlpha(r03, 1.3f), Theme.multAlpha(r03, 1.0f), Theme.multAlpha(r03, 4.0f));
            this.thisLoading.strokePaint.setStrokeWidth(AndroidUtilities.dpf2(1.25f));
            AboutLinkCell.m6598$$Nest$fgetlinks(this.this$0).addLoading(this.thisLoading);
        }
    }

    /* renamed from: org.telegram.ui.Cells.AboutLinkCell$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ AboutLinkCell this$0;

        public AnonymousClass4(AboutLinkCell r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.didResizeEnd();
            if (AboutLinkCell.m6596$$Nest$fgetcontainer(this.this$0).getBackground() != null) goto L5;
            AboutLinkCell.m6596$$Nest$fgetcontainer(this.this$0).setBackground(AboutLinkCell.m6604$$Nest$fgetrippleBackground(this.this$0));
        L5:
            AboutLinkCell.m6607$$Nest$fputexpanded(this.this$0, true);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.didResizeStart();
        }
    }

    /* renamed from: org.telegram.ui.Cells.AboutLinkCell$5, reason: invalid class name */
    public class AnonymousClass5 extends ClickableSpan {
        final /* synthetic */ AboutLinkCell this$0;
        final /* synthetic */ ClickableSpan val$original;

        public AnonymousClass5(AboutLinkCell r1, ClickableSpan r2) {
            this.this$0 = r1;
            this.val$original = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r3) {
            AboutLinkCell r32 = this.this$0;
            AboutLinkCell.m6609$$Nest$monLinkClick(r32, this.val$original, AboutLinkCell.m6605$$Nest$fgettextLayout(r32), 0.0f);
        }
    }

    public class SpringInterpolator {
        public float friction;
        private final float mass;
        private float position;
        public float tension;
        final /* synthetic */ AboutLinkCell this$0;
        private float velocity;

        public SpringInterpolator(AboutLinkCell r1, float r2, float r3) {
            this.this$0 = r1;
            this.mass = 1.0f;
            this.position = 0.0f;
            this.velocity = 0.0f;
            this.tension = r2;
            this.friction = r3;
        }

        private void step(float r6) {
            float r0 = (-this.tension) * 1.0E-6f;
            float r1 = this.position;
            float r02 = r0 * (r1 - 1.0f);
            float r3 = (-this.friction) * 0.001f;
            float r4 = this.velocity;
            float r42 = r4 + (((r02 + (r3 * r4)) / 1.0f) * r6);
            this.velocity = r42;
            this.position = r1 + (r42 * r6);
        }

        public float getValue(float r2) {
            float r22 = Math.min(r2, 250.0f);
        L4:
            if (r22 <= 0.0f) goto L7;
            float r0 = Math.min(r22, 18.0f);
            step(r0);
            r22 = r22 - r0;
            goto L4
        L7:
            return this.position;
        }
    }

    public static /* synthetic */ void $r8$lambda$Pt22f2MUtmcmKf_HHfwHegxdhSk(AboutLinkCell r0, AtomicReference r1, float r2, float r3, SpringInterpolator r4, ValueAnimator r5) {
        r0.lambda$updateCollapse$0(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$fgetcontainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m6596$$Nest$fgetcontainer(AboutLinkCell r0) {
        return r0.container;
    }

    /* renamed from: -$$Nest$fgetcurrentLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LoadingDrawable m6597$$Nest$fgetcurrentLoading(AboutLinkCell r0) {
        return r0.currentLoading;
    }

    /* renamed from: -$$Nest$fgetlinks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinkSpanDrawable.LinkCollector m6598$$Nest$fgetlinks(AboutLinkCell r0) {
        return r0.links;
    }

    /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m6599$$Nest$fgetparentFragment(AboutLinkCell r0) {
        return r0.parentFragment;
    }

    /* renamed from: -$$Nest$fgetpressedLink, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinkSpanDrawable m6600$$Nest$fgetpressedLink(AboutLinkCell r0) {
        return r0.pressedLink;
    }

    /* renamed from: -$$Nest$fgetpressedLinkLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Layout m6601$$Nest$fgetpressedLinkLayout(AboutLinkCell r0) {
        return r0.pressedLinkLayout;
    }

    /* renamed from: -$$Nest$fgetpressedLinkYOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m6602$$Nest$fgetpressedLinkYOffset(AboutLinkCell r0) {
        return r0.pressedLinkYOffset;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m6603$$Nest$fgetresourcesProvider(AboutLinkCell r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetrippleBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m6604$$Nest$fgetrippleBackground(AboutLinkCell r0) {
        return r0.rippleBackground;
    }

    /* renamed from: -$$Nest$fgettextLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StaticLayout m6605$$Nest$fgettextLayout(AboutLinkCell r0) {
        return r0.textLayout;
    }

    /* renamed from: -$$Nest$fputcurrentLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6606$$Nest$fputcurrentLoading(AboutLinkCell r0, LoadingDrawable r1) {
        r0.currentLoading = r1;
    }

    /* renamed from: -$$Nest$fputexpanded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6607$$Nest$fputexpanded(AboutLinkCell r0, boolean r1) {
        r0.expanded = r1;
    }

    /* renamed from: -$$Nest$fputpressedLink, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6608$$Nest$fputpressedLink(AboutLinkCell r0, LinkSpanDrawable r1) {
        r0.pressedLink = r1;
    }

    /* renamed from: -$$Nest$monLinkClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6609$$Nest$monLinkClick(AboutLinkCell r0, ClickableSpan r1, Layout r2, float r3) {
        r0.onLinkClick(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mresetPressedLink, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6610$$Nest$mresetPressedLink(AboutLinkCell r0) {
        r0.resetPressedLink();
    }

    static {
        int r0 = AndroidUtilities.dp(76.0f);
        COLLAPSED_HEIGHT = r0;
        MAX_OPEN_HEIGHT = r0;
        MOST_SPEC = View.MeasureSpec.makeMeasureSpec(999999, Integer.MIN_VALUE);
    }

    public AboutLinkCell(Context r19, BaseFragment r20, Theme.ResourcesProvider r21) {
        super(r19);
        this.urlPathOffset = new Point();
        this.urlPath = new LinkPath(true);
        this.nextLinesLayouts = null;
        this.lastInlineLine = -1;
        this.needSpace = false;
        this.backgroundPaint = new Paint();
        this.SPACE = AndroidUtilities.dp(3.0f);
        this.longPressedRunnable = new AnonymousClass2(this);
        this.expandT = 0.0f;
        this.rawCollapseT = 0.0f;
        this.expanded = false;
        this.lastMaxWidth = 0;
        this.shouldExpand = false;
        this.resourcesProvider = r21;
        this.parentFragment = r20;
        FrameLayout r7 = new FrameLayout(r19);
        this.container = r7;
        r7.setImportantForAccessibility(2);
        this.links = new LinkSpanDrawable.LinkCollector(this.container);
        this.rippleBackground = Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, r21), 0, 0);
        TextView r72 = new TextView(r19);
        this.valueTextView = r72;
        r72.setVisibility(8);
        this.valueTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r21));
        this.valueTextView.setTextSize(1, 13.0f);
        this.valueTextView.setTypeface(AndroidUtilities.regular());
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        TextView r73 = this.valueTextView;
        int r10 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r9 = 5;
    L6:
        r73.setGravity(r9);
        this.valueTextView.setImportantForAccessibility(2);
        this.valueTextView.setFocusable(false);
        FrameLayout r74 = this.container;
        TextView r8 = this.valueTextView;
        if (LocaleController.isRTL == false) goto L9;
        r10 = 5;
    L9:
        r74.addView(r8, LayoutHelper.createFrame(-2, -2.0f, r10 | 80, 18.0f, 0.0f, 18.0f, 10.0f));
        addView(this.container, LayoutHelper.createFrame(-1, -1, 55));
        AnonymousClass1 r3 = new AnonymousClass1(this, r19);
        this.showMoreTextView = r3;
        r3.setClickable(true);
        this.showMoreTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, r21));
        this.showMoreTextView.setTextSize(1, 16.0f);
        this.showMoreTextView.setTypeface(AndroidUtilities.regular());
        this.showMoreTextView.setLines(1);
        this.showMoreTextView.setMaxLines(1);
        this.showMoreTextView.setSingleLine(true);
        this.showMoreTextView.setText(LocaleController.getString(R.string.DescriptionMore));
        this.showMoreTextView.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        this.showMoreTextBackgroundView = new FrameLayout(r19);
        Drawable r1 = r19.getResources().getDrawable(R.drawable.gradient_left).mutate();
        this.showMoreBackgroundDrawable = r1;
        int r4 = Theme.key_windowBackgroundWhite;
        r1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r4, r21), PorterDuff.Mode.MULTIPLY));
        this.showMoreTextBackgroundView.setBackground(this.showMoreBackgroundDrawable);
        FrameLayout r12 = this.showMoreTextBackgroundView;
        r12.setPadding(r12.getPaddingLeft() + AndroidUtilities.dp(4.0f), AndroidUtilities.dp(1.0f), 0, AndroidUtilities.dp(3.0f));
        this.showMoreTextBackgroundView.addView(this.showMoreTextView, LayoutHelper.createFrame(-2, -2.0f));
        addView(this.showMoreTextBackgroundView, LayoutHelper.createFrame(-2, -2.0f, 85, 18.0f - (r1.getPaddingLeft() / AndroidUtilities.density), 0.0f, 18.0f - (this.showMoreTextBackgroundView.getPaddingRight() / AndroidUtilities.density), 6.0f));
        this.backgroundPaint.setColor(Theme.getColor(r4, r21));
        setWillNotDraw(false);
        return;
    L5:
        r9 = 3;
        goto L6
    }

    private CharSequence buildAccessibilityText() {
        CharSequence r0 = this.accessibilityText;
        if (r0 == null) goto L5;
        return r0;
    L5:
        SpannableStringBuilder r02 = this.stringBuilder;
        if (r02 != null) goto L9;
        return null;
    L9:
        int r3 = 0;
        ClickableSpan[] r03 = (ClickableSpan[]) r02.getSpans(0, r02.length(), ClickableSpan.class);
        if (r03 != null) goto L12;
    L24:
        SpannableStringBuilder r04 = this.stringBuilder;
        this.accessibilityText = r04;
        return r04;
    L12:
        if (r03.length == 0) goto L24;
        SpannableStringBuilder r1 = new SpannableStringBuilder(this.stringBuilder);
        int r2 = r03.length;
    L15:
        if (r3 >= r2) goto L22;
        ClickableSpan r4 = r03[r3];
        int r5 = r1.getSpanStart(r4);
        int r6 = r1.getSpanEnd(r4);
        if (r5 < 0) goto L21;
        if (r6 <= r5) goto L21;
        r1.removeSpan(r4);
        r1.setSpan(new AnonymousClass5(this, r4), r5, r6, 33);
    L21:
        r3 = r3 + 1;
        goto L15
    L22:
        this.accessibilityText = r1;
        return r1;
    }

    private void checkTextLayout(int r10, boolean r11) {
        int r1 = 0;
        if (this.moreButtonDisabled == false) goto L5;
        this.shouldExpand = false;
    L5:
        SpannableStringBuilder r0 = this.stringBuilder;
        if (r0 != null) goto L8;
    L59:
        TextView r102 = this.showMoreTextView;
        if (this.shouldExpand == true) goto L63;
        r1 = 8;
    L63:
        r102.setVisibility(r1);
        if (this.shouldExpand == true) goto L69;
        if (this.container.getBackground() != null) goto L69;
        this.container.setBackground(this.rippleBackground);
    L69:
        if (this.shouldExpand == true) goto L71;
        return;
    L71:
        if (this.expandT < 1.0f) goto L73;
        return;
    L73:
        if (this.container.getBackground() == null) goto L83;
        this.container.setBackground(null);
        return;
    L83:
        return;
    L8:
        if (r10 != this.lastMaxWidth) goto L10;
        if (r11 == false) goto L59;
    L10:
        StaticLayout r112 = makeTextLayout(r0, r10);
        this.textLayout = r112;
        if (r112.getLineCount() >= 4) goto L13;
    L15:
        boolean r113 = false;
    L16:
        this.shouldExpand = r113;
        int r02 = 3;
        if (this.textLayout.getLineCount() < 3) goto L48;
        if (this.shouldExpand == false) goto L48;
        int r114 = Math.max(this.textLayout.getLineStart(2), this.textLayout.getLineEnd(2));
        if (this.stringBuilder.charAt(r114 - 1) != '\n') goto L23;
        r114 = r114 - 1;
    L23:
        int r6 = r114 - 1;
        if (this.stringBuilder.charAt(r6) != ' ') goto L26;
    L28:
        boolean r4 = false;
    L29:
        this.needSpace = r4;
        this.firstThreeLinesLayout = makeTextLayout(this.stringBuilder.subSequence(0, r114), r10);
        this.nextLinesLayouts = new StaticLayout[this.textLayout.getLineCount() - 3];
        this.nextLinesLayoutsPositions = new Point[this.textLayout.getLineCount() - 3];
        float r115 = this.firstThreeLinesLayout.getLineRight(this.firstThreeLinesLayout.getLineCount() - 1);
        if (this.needSpace == false) goto L32;
        float r42 = this.SPACE;
    L33:
        float r116 = r115 + r42;
        this.lastInlineLine = -1;
        if (this.showMoreTextBackgroundView.getMeasuredWidth() > 0) goto L37;
        FrameLayout r5 = this.showMoreTextBackgroundView;
        int r62 = MOST_SPEC;
        r5.measure(r62, r62);
    L37:
        if (r02 >= this.textLayout.getLineCount()) goto L45;
        int r52 = this.textLayout.getLineStart(r02);
        int r63 = this.textLayout.getLineEnd(r02);
        StaticLayout r53 = makeTextLayout(this.stringBuilder.subSequence(Math.min(r52, r63), Math.max(r52, r63)), r10);
        int r7 = r02 - 3;
        this.nextLinesLayouts[r7] = r53;
        this.nextLinesLayoutsPositions[r7] = new Point();
        if (this.lastInlineLine != (-1)) goto L43;
        if (r116 <= ((r10 - this.showMoreTextBackgroundView.getMeasuredWidth()) + this.showMoreTextBackgroundView.getPaddingLeft())) goto L43;
        this.lastInlineLine = r7;
    L43:
        r116 = r116 + (r53.getLineRight(0) + this.SPACE);
        r02 = r02 + 1;
        goto L37
    L45:
        if (r116 >= ((r10 - this.showMoreTextBackgroundView.getMeasuredWidth()) + this.showMoreTextBackgroundView.getPaddingLeft())) goto L48;
        this.shouldExpand = false;
        goto L48
    L32:
        r42 = 0.0f;
        goto L33
    L26:
        if (this.stringBuilder.charAt(r6) == '\n') goto L28;
        r4 = true;
    L48:
        if (this.shouldExpand == true) goto L50;
        this.firstThreeLinesLayout = null;
        this.nextLinesLayouts = null;
    L50:
        this.lastMaxWidth = r10;
        this.container.setMinimumHeight(textHeight());
        if (this.shouldExpand == false) goto L59;
        if (this.firstThreeLinesLayout == null) goto L59;
        int r103 = fromHeight() - AndroidUtilities.dp(8.0f);
        StaticLayout r117 = this.firstThreeLinesLayout;
        int r104 = ((r103 - r117.getLineBottom(r117.getLineCount() - 1)) - this.showMoreTextBackgroundView.getPaddingBottom()) - this.showMoreTextView.getPaddingBottom();
        if (this.showMoreTextView.getLayout() != null) goto L57;
        int r118 = 0;
    L58:
        setShowMoreMarginBottom(r104 - r118);
        goto L59
    L57:
        r118 = this.showMoreTextView.getLayout().getHeight() - this.showMoreTextView.getLayout().getLineBottom(this.showMoreTextView.getLineCount() - 1);
        goto L58
    L13:
        if (this.moreButtonDisabled == true) goto L15;
        r113 = true;
        goto L16
    }

    private LinkSpanDrawable checkTouchTextLayout(StaticLayout r6, int r7, int r8, int r9, int r10) {
        int r72 = r9 - r7;
        int r0 = r10 - r8;
        int r1 = r6.getLineForVertical(r0);     // Catch: Exception -> L16
        float r73 = r72;
        int r2 = r6.getOffsetForHorizontal(r1, r73);     // Catch: Exception -> L16
        float r3 = r6.getLineLeft(r1);     // Catch: Exception -> L16
        if (r3 <= r73) goto L6;
        return null;
    L6:
        if ((r3 + r6.getLineWidth(r1)) < r73) goto L23;
        if (r0 >= 0) goto L9;
        return null;
    L9:
        if (r0 > r6.getHeight()) goto L25;
        Spannable r74 = (Spannable) r6.getText();     // Catch: Exception -> L16
        ClickableSpan[] r02 = (ClickableSpan[]) r74.getSpans(r2, r2, ClickableSpan.class);     // Catch: Exception -> L16
        if (r02.length != 0) goto L13;
        return null;
    L13:
        if (AndroidUtilities.isAccessibilityScreenReaderEnabled() == true) goto L27;
        LinkSpanDrawable r12 = new LinkSpanDrawable(r02[0], this.resourcesProvider, r9, r10);     // Catch: Exception -> L16
        r12.setColor(processColor(Theme.getColor(Theme.key_chat_linkSelectBackground, this.resourcesProvider)));     // Catch: Exception -> L16
        int r92 = r74.getSpanStart(r02[0]);     // Catch: Exception -> L16
        int r75 = r74.getSpanEnd(r02[0]);     // Catch: Exception -> L16
        LinkPath r102 = r12.obtainNewPath();     // Catch: Exception -> L16
        float r82 = r8;
        this.pressedLinkYOffset = r82;     // Catch: Exception -> L16
        r102.setCurrentLayout(r6, r92, r82);     // Catch: Exception -> L16
        r6.getSelectionPath(r92, r75, r102);     // Catch: Exception -> L16
        return r12;
    L27:
        return null;
    L25:
        return null;
    L23:
        return null;
    L16:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private void drawText(Canvas r19) {
        r19.save();
        r19.clipRect(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(8.0f), getWidth() - AndroidUtilities.dp(18.0f), getHeight());
        int r2 = AndroidUtilities.dp(18.0f);
        this.textX = r2;
        float r8 = 0.0f;
        r19.translate(r2, 0.0f);
        LinkSpanDrawable.LinkCollector r22 = this.links;
        if (r22 != null) goto L5;
    L7:
        int r23 = AndroidUtilities.dp(8.0f);
        this.textY = r23;
        r19.translate(0.0f, r23);
        Theme.profile_aboutTextPaint.setColor(processColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider)));     // Catch: Exception -> L16
        ((TextPaint) Theme.profile_aboutTextPaint).linkColor = processColor(Theme.getColor(Theme.key_chat_messageLinkIn, this.resourcesProvider));     // Catch: Exception -> L16
        StaticLayout r24 = this.firstThreeLinesLayout;     // Catch: Exception -> L16
        if (r24 != null) goto L11;
    L37:
        StaticLayout r0 = this.textLayout;     // Catch: Exception -> L16
        if (r0 == null) goto L42;
        r0.draw(r19);     // Catch: Exception -> L16
    L42:
        r19.restore();
        return;
    L11:
        if (this.shouldExpand == false) goto L37;
        r24.draw(r19);     // Catch: Exception -> L16
        int r25 = this.firstThreeLinesLayout.getLineCount() - 1;
        float r9 = this.firstThreeLinesLayout.getLineTop(r25) + this.firstThreeLinesLayout.getTopPadding();     // Catch: Exception -> L16
        float r3 = this.firstThreeLinesLayout.getLineRight(r25);     // Catch: Exception -> L16
        if (this.needSpace == false) goto L18;
        float r4 = this.SPACE;     // Catch: Exception -> L16
    L19:
        float r32 = r3 + r4;     // Catch: Exception -> L16
        float r26 = (this.firstThreeLinesLayout.getLineBottom(r25) - this.firstThreeLinesLayout.getLineTop(r25)) - this.firstThreeLinesLayout.getBottomPadding();     // Catch: Exception -> L16
        float r10 = 1.0f;
        float r11 = easeInOutCubic(1.0f - ((float) Math.pow(this.expandT, 0.25d)));     // Catch: Exception -> L16
        if (this.nextLinesLayouts == null) goto L42;
        float r13 = r26;
        float r14 = r32;
        int r15 = 0;
    L22:
        StaticLayout[] r27 = this.nextLinesLayouts;     // Catch: Exception -> L16
        if (r15 >= r27.length) goto L42;
        StaticLayout r28 = r27[r15];     // Catch: Exception -> L16
        if (r28 == null) goto L35;
        int r33 = r19.save();     // Catch: Exception -> L16
        Point r42 = this.nextLinesLayoutsPositions[r15];     // Catch: Exception -> L16
        if (r42 == null) goto L29;
        r42.set((int) (this.textX + (r14 * r11)), (int) ((this.textY + r9) + ((r10 - r11) * r13)));     // Catch: Exception -> L16
    L29:
        int r43 = this.lastInlineLine;     // Catch: Exception -> L16
        if (r43 == (-1)) goto L33;
        if (r43 > r15) goto L33;
        r19.translate(r8, r9 + r13);     // Catch: Exception -> L16
        StaticLayout r82 = r28;
        float r16 = r10;
        int r102 = r33;
        r19.saveLayerAlpha(0.0f, 0.0f, r28.getWidth(), r28.getHeight(), (int) (this.expandT * 255.0f), 31);     // Catch: Exception -> L16
    L34:
        r82.draw(r19);     // Catch: Exception -> L16
        r19.restoreToCount(r102);     // Catch: Exception -> L16
        r14 = r14 + (r82.getLineRight(0) + this.SPACE);     // Catch: Exception -> L16
        r13 = r13 + ((r82.getLineBottom(0) + r82.getTopPadding()) - 1);     // Catch: Exception -> L16
    L36:
        r15 = r15 + 1;     // Catch: Exception -> L16
        r10 = r16;
        r8 = 0.0f;
    L33:
        r82 = r28;
        r16 = r10;
        r102 = r33;
        r19.translate(r14 * r11, ((r16 - r11) * r13) + r9);     // Catch: Exception -> L16
        goto L34
    L35:
        r16 = r10;
        goto L36
    L18:
        r4 = 0.0f;
    L16:
        e = move-exception;
        FileLog.e(e);
        goto L42
    L5:
        if (r22.draw(r19) == false) goto L7;
        invalidate();
        goto L7
    }

    private float easeInOutCubic(float r5) {
        if (r5 >= 0.5d) goto L7;
        return ((4.0f * r5) * r5) * r5;
    L7:
        return 1.0f - (((float) Math.pow((r5 * (-2.0f)) + 2.0f, 3.0d)) / 2.0f);
    }

    private int fromHeight() {
        if (this.shouldExpand == false) goto L7;
        StaticLayout r0 = this.firstThreeLinesLayout;
        if (r0 == null) goto L7;
        int r02 = r0.getHeight() + AndroidUtilities.dp(16.0f);
    L9:
        if (this.valueTextView.getVisibility() != 0) goto L12;
        r02 = r02 + AndroidUtilities.dp(23.0f);
    L12:
        return Math.min(r02, textHeight());
    L7:
        r02 = COLLAPSED_HEIGHT;
        goto L9
    }

    private LinkSpanDrawable hitLink(int r9, int r10) {
        if (getMeasuredWidth() > 0) goto L5;
    L7:
        StaticLayout r3 = this.firstThreeLinesLayout;
        if (r3 != null) goto L10;
    L25:
        AboutLinkCell r2 = this;
        int r6 = r9;
        int r7 = r10;
    L26:
        LinkSpanDrawable r8 = r2.checkTouchTextLayout(r2.textLayout, r2.textX, r2.textY, r6, r7);
        if (r8 == null) goto L29;
        return r8;
    L29:
        return null;
    L10:
        if (this.expandT >= 1.0f) goto L25;
        if (this.shouldExpand == false) goto L25;
        r2 = this;
        r6 = r9;
        r7 = r10;
        LinkSpanDrawable r82 = r2.checkTouchTextLayout(r3, this.textX, this.textY, r6, r7);
        if (r82 == null) goto L17;
        return r82;
    L17:
        if (r2.nextLinesLayouts == null) goto L26;
        int r83 = 0;
    L19:
        StaticLayout[] r92 = r2.nextLinesLayouts;
        if (r83 >= r92.length) goto L26;
        StaticLayout r32 = r92[r83];
        Point r93 = r2.nextLinesLayoutsPositions[r83];
        LinkSpanDrawable r94 = r2.checkTouchTextLayout(r32, r93.x, r93.y, r6, r7);
        if (r94 != null) goto L23;
        r83 = r83 + 1;
        goto L19
    L23:
        return r94;
    L5:
        if (r9 <= (getMeasuredWidth() - AndroidUtilities.dp(18.0f))) goto L7;
        return null;
    }

    private /* synthetic */ void lambda$updateCollapse$0(AtomicReference r3, float r4, float r5, SpringInterpolator r6, ValueAnimator r7) {
        Float r0 = (Float) r7.getAnimatedValue();
        float r1 = ((r0.floatValue() - ((Float) r3.getAndSet(r0)).floatValue()) * 1000.0f) * 8.0f;
        this.rawCollapseT = AndroidUtilities.lerp(r4, r5, ((Float) r7.getAnimatedValue()).floatValue());
        float r32 = AndroidUtilities.lerp(r4, r5, r6.getValue(r1));
        this.expandT = r32;
        if (r32 > 0.8f) goto L5;
    L7:
        this.showMoreTextBackgroundView.setAlpha(1.0f - this.expandT);
        updateHeight();
        this.container.invalidate();
        return;
    L5:
        if (this.container.getBackground() != null) goto L7;
        this.container.setBackground(this.rippleBackground);
        goto L7
    }

    private StaticLayout makeTextLayout(CharSequence r3, int r4) {
        StaticLayout.Builder r2 = StaticLayout.Builder.obtain(r3, 0, r3.length(), Theme.profile_aboutTextPaint, Math.max(1, r4)).setBreakStrategy(0).setHyphenationFrequency(0);
        if (LocaleController.isRTL == false) goto L5;
        Layout.Alignment r32 = StaticLayoutEx.ALIGN_RIGHT();
    L7:
        return r2.setAlignment(r32).build();
    L5:
        r32 = StaticLayoutEx.ALIGN_LEFT();
        goto L7
    }

    private void onLinkClick(ClickableSpan r8, Layout r9, float r10) {
        Browser.Progress r0 = this.currentProgress;
        AnonymousClass3 r1 = null;
        if (r0 == null) goto L5;
        r0.cancel();
        this.currentProgress = null;
    L5:
        if (r9 == null) goto L8;
        if (r8 == null) goto L8;
        r1 = new AnonymousClass3(this, r9, r8, r10);
    L8:
        this.currentProgress = r1;
        if ((r8 instanceof URLSpanNoUnderline) == false) goto L23;
        String r82 = ((URLSpanNoUnderline) r8).getURL();
        if (r82.startsWith("@") == false) goto L13;
    L20:
        didPressUrl(r82, this.currentProgress);
        return;
    L13:
        if (r82.startsWith("#") == true) goto L20;
        if (r82.startsWith("$") == true) goto L20;
        if (r82.startsWith("/") == true) goto L20;
        return;
    L23:
        if ((r8 instanceof URLSpan) == false) goto L30;
        String r12 = ((URLSpan) r8).getURL();
        if (AndroidUtilities.shouldShowUrlInAlert(r12) == false) goto L28;
        AlertsCreator.showOpenUrlAlert(this.parentFragment, r12, true, true, true, this.currentProgress, null);
        return;
    L28:
        Browser.openUrl(getContext(), Uri.parse(r12), true, true, this.currentProgress);
        return;
    L30:
        r8.onClick(this);
    }

    private void resetPressedLink() {
        this.links.clear();
        this.pressedLink = null;
        AndroidUtilities.cancelRunOnUIThread(this.longPressedRunnable);
        invalidate();
    }

    private void setHeight(int r4) {
        RecyclerView.LayoutParams r0 = (RecyclerView.LayoutParams) getLayoutParams();
        boolean r1 = true;
        if (r0 != null) goto L10;
        if (getMinimumHeight() != 0) goto L7;
        getHeight();
    L8:
        r0 = new RecyclerView.LayoutParams(-1, r4);
    L14:
        if (r1 == false) goto L17;
        setLayoutParams(r0);
        return;
    L17:
        return;
    L7:
        getMinimumHeight();
        goto L8
    L10:
        if (((ViewGroup.MarginLayoutParams) r0).height != r4) goto L13;
        r1 = false;
    L13:
        ((ViewGroup.MarginLayoutParams) r0).height = r4;
        goto L14
    }

    private void setShowMoreMarginBottom(int r3) {
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.showMoreTextBackgroundView.getLayoutParams();
        if (r0.bottomMargin == r3) goto L6;
        r0.bottomMargin = r3;
        this.showMoreTextBackgroundView.setLayoutParams(r0);
        return;
    }

    private int textHeight() {
        StaticLayout r0 = this.textLayout;
        if (r0 == null) goto L5;
        int r02 = r0.getHeight();
    L6:
        int r03 = r02 + AndroidUtilities.dp(16.0f);
        if (this.valueTextView.getVisibility() == 0) goto L9;
        return r03;
    L9:
        return r03 + AndroidUtilities.dp(23.0f);
    L5:
        r02 = AndroidUtilities.dp(20.0f);
        goto L6
    }

    private void updateGradientColor(int r4) {
        Drawable r0 = this.showMoreBackgroundDrawable;
        if (r0 == null) goto L5;
        r0.setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.MULTIPLY));
    L5:
        Paint r02 = this.backgroundPaint;
        if (r02 == null) goto L8;
        r02.setColor(r4);
    L8:
        FrameLayout r3 = this.showMoreTextBackgroundView;
        if (r3 == null) goto L12;
        r3.invalidate();
        return;
    }

    private int updateHeight() {
        int r0 = textHeight();
        float r1 = fromHeight();
        if (this.shouldExpand == false) goto L5;
        r0 = (int) AndroidUtilities.lerp(r1, r0, this.expandT);
    L5:
        setHeight(r0);
        return r0;
    }

    public void didExtend() {
    }

    public abstract void didPressUrl(String r1, Browser.Progress r2);

    public abstract void didResizeEnd();

    public abstract void didResizeStart();

    @Override // android.view.View
    public void draw(Canvas r12) {
        super.draw(r12);
        if (((View) getParent()) != null) goto L6;
        float r1 = 1.0f;
    L5:
        float r8 = r1;
        drawText(r12);
        float r13 = this.showMoreTextBackgroundView.getAlpha();
        float r9 = 0.0f;
        if (r13 <= 0.0f) goto L10;
        r12.save();
        r12.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (r13 * 255.0f), 31);
        this.showMoreBackgroundDrawable.setAlpha((int) (r8 * 255.0f));
        r12.translate(this.showMoreTextBackgroundView.getLeft(), this.showMoreTextBackgroundView.getTop());
        this.showMoreTextBackgroundView.draw(r12);
        r12.restore();
    L10:
        this.container.draw(r12);
        if (this.needDivider == true) goto L13;
        return;
    L13:
        if (LocaleController.isRTL == true) goto L16;
        r9 = AndroidUtilities.dp(20.0f);
    L16:
        float r2 = getMeasuredHeight() - 1;
        int r4 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L19;
        int r3 = AndroidUtilities.dp(20.0f);
    L20:
        r12.drawLine(r9, r2, r4 - r3, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L19:
        r3 = 0;
        goto L20
    L6:
        r1 = (float) Math.pow(r1.getAlpha(), 2.0d);
        goto L5
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r1, View r2, long r3) {
        return false;
    }

    public boolean onClick() {
        if (this.shouldExpand == true) goto L5;
        return false;
    L5:
        if (this.expandT > 0.0f) goto L10;
        updateCollapse(true, true);
        return true;
    L10:
        return false;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
        super.onInitializeAccessibilityNodeInfo(r4);
        if (this.textLayout == null) goto L10;
        CharSequence r0 = buildAccessibilityText();
        CharSequence r3 = this.valueTextView.getText();
        r4.setClassName("android.widget.TextView");
        if (TextUtils.isEmpty(r3) == false) goto L8;
        r4.setText(r0);
        return;
    L8:
        r4.setText(TextUtils.concat(new CharSequence[]{r3, ": ", r0}));
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onMeasure(int r2, int r3) {
        checkTextLayout(View.MeasureSpec.getSize(r2) - AndroidUtilities.dp(36.0f), false);
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(updateHeight(), TLObject.FLAG_30));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        int r0 = (int) r8.getX();
        int r1 = (int) r8.getY();
        if (this.textLayout != null) goto L7;
        if (this.nextLinesLayouts != null) goto L7;
    L29:
        boolean r2 = false;
    L31:
        if (r8.getActionMasked() == 0) goto L35;
        if (r8.getActionMasked() == 2) goto L35;
    L37:
        if (r2 == false) goto L39;
        return true;
    L39:
        if (super.onTouchEvent(r8) == false) goto L42;
        return true;
    L42:
        return false;
    L35:
        if (this.container.getBackground() == null) goto L37;
        this.container.getBackground().setHotspot(r0, r1);
    L7:
        if (r8.getAction() == 0) goto L17;
        if (this.pressedLink == null) goto L14;
        if (r8.getAction() == 1) goto L17;
    L14:
        if (r8.getAction() != 3) goto L29;
        resetPressedLink();
    L17:
        if (r8.getAction() != 0) goto L21;
        resetPressedLink();
        LinkSpanDrawable r22 = hitLink(r0, r1);
        if (r22 == null) goto L29;
        this.pressedLinkLayout = this.textLayout;
        LinkSpanDrawable.LinkCollector r5 = this.links;
        this.pressedLink = r22;
        r5.addLink(r22);
        AndroidUtilities.runOnUIThread(this.longPressedRunnable, ViewConfiguration.getLongPressTimeout());
    L28:
        r2 = true;
        goto L31
    L21:
        LinkSpanDrawable r23 = this.pressedLink;
        if (r23 == null) goto L29;
        onLinkClick((ClickableSpan) r23.getSpan(), this.textLayout, this.pressedLinkYOffset);     // Catch: Exception -> L25
    L27:
        resetPressedLink();
    L25:
        e = move-exception;
        FileLog.e(e);
        goto L27
    }

    public abstract int processColor(int r1);

    @Override // android.view.View
    public void setBackground(Drawable r2) {
        super.setBackground(r2);
        if ((r2 instanceof ShapeDrawable) == false) goto L6;
        int r22 = ((ShapeDrawable) r2).getPaint().getColor();
    L9:
        if (r22 == 0) goto L12;
        updateGradientColor(r22);
        return;
    L12:
        return;
    L6:
        if ((r2 instanceof ColorDrawable) == false) goto L8;
        r22 = ((ColorDrawable) r2).getColor();
        goto L9
    L8:
        r22 = 0;
        goto L9
    }

    @Override // android.view.View
    public void setBackgroundColor(int r1) {
        super.setBackgroundColor(r1);
        updateGradientColor(r1);
    }

    public void setMoreButtonDisabled(boolean r1) {
        this.moreButtonDisabled = r1;
    }

    public void setTextAndValue(String r4, String r5, boolean r6, boolean r7) {
        if (TextUtils.isEmpty(r4) == false) goto L5;
        return;
    L5:
        if (TextUtils.equals(r4, this.oldText) == true) goto L25;
        this.oldText = AndroidUtilities.getSafeString(r4);     // Catch: Throwable -> L9
    L10:
        SpannableStringBuilder r42 = new SpannableStringBuilder(this.oldText);
        this.stringBuilder = r42;
        this.accessibilityText = null;
        MessageObject.addLinks(false, r42, false, false, !r6);
        Emoji.replaceEmoji(this.stringBuilder, Theme.profile_aboutTextPaint.getFontMetricsInt(), false);
        if (this.lastMaxWidth > 0) goto L13;
        this.lastMaxWidth = AndroidUtilities.displaySize.x - AndroidUtilities.dp(36.0f);
    L13:
        checkTextLayout(this.lastMaxWidth, true);
        updateHeight();
        int r43 = this.valueTextView.getVisibility();
        boolean r62 = TextUtils.isEmpty(r5);
        TextView r2 = this.valueTextView;
        if (r62 == false) goto L16;
        r2.setVisibility(8);
    L17:
        this.needDivider = r7;
        if (r43 == this.valueTextView.getVisibility()) goto L20;
        checkTextLayout(this.lastMaxWidth, true);
    L20:
        requestLayout();
        return;
    L16:
        r2.setText(r5);
        this.valueTextView.setVisibility(0);
    L9:
        this.oldText = r4;
        goto L10
    }

    public void updateCollapse(boolean r8, boolean r9) {
        ValueAnimator r0 = this.collapseAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.collapseAnimator = null;
    L5:
        float r4 = this.expandT;
        if (r8 == false) goto L8;
        float r5 = 1.0f;
    L9:
        if (r9 == true) goto L11;
        this.expandT = r5;
        forceLayout();
        return;
    L11:
        if (r5 <= 0.0f) goto L13;
        didExtend();
    L13:
        float r82 = textHeight();
        float r92 = Math.min(COLLAPSED_HEIGHT, r82);
        Math.abs(AndroidUtilities.lerp(r92, r82, r5) - AndroidUtilities.lerp(r92, r82, r4));
        this.collapseAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        float r83 = (Math.abs(r4 - r5) * 1250.0f) * 2.0f;
        SpringInterpolator r6 = new SpringInterpolator(this, 380.0f, 20.17f);
        this.collapseAnimator.addUpdateListener(new AboutLinkCell$$ExternalSyntheticLambda0(this, new AtomicReference(Float.valueOf(r4)), r4, r5, r6));
        this.collapseAnimator.addListener(new AnonymousClass4(this));
        this.collapseAnimator.setDuration((long) r83);
        this.collapseAnimator.start();
        return;
    L8:
        r5 = 0.0f;
        goto L9
    }

    public void updateColors() {
        Theme.profile_aboutTextPaint.setColor(processColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider)));
        com.exteragram.messenger.utils.ui.TextPaint r0 = Theme.profile_aboutTextPaint;
        ((TextPaint) r0).linkColor = processColor(Theme.getColor(Theme.key_chat_messageLinkIn, this.resourcesProvider));
    }
}
