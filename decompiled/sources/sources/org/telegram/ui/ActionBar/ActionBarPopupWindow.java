package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import androidx.annotation.Keep;
import com.exteragram.messenger.ExteraConfig;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PopupSwipeBackLayout;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;

/* loaded from: classes6.dex */
public class ActionBarPopupWindow extends PopupWindow {
    private static final ViewTreeObserver.OnScrollChangedListener NOP = null;
    private static DecelerateInterpolator decelerateInterpolator;
    private static Method layoutInScreenMethod;
    private static final Field superListenerField = null;
    private boolean animationEnabled;
    private int currentAccount;
    private int dismissAnimationDuration;
    private boolean isClosingAnimated;
    private ViewTreeObserver.OnScrollChangedListener mSuperScrollListener;
    private ViewTreeObserver mViewTreeObserver;
    private AnimationNotificationsLocker notificationsLocker;
    private long outEmptyTime;
    private boolean pauseNotifications;
    private boolean scaleOut;
    private AnimatorSet windowAnimatorSet;

    /* renamed from: org.telegram.ui.ActionBar.ActionBarPopupWindow$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarPopupWindowLayout val$content;

        public AnonymousClass1(ActionBarPopupWindowLayout r1) {
            this.val$content = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            int r0 = 0;
            ActionBarPopupWindowLayout.m5548$$Nest$fputstartAnimationPending(this.val$content, false);
            int r42 = this.val$content.getItemsCount();
        L3:
            if (r0 >= r42) goto L13;
            View r1 = this.val$content.getItemAt(r0);
            if ((r1 instanceof GapView) == true) goto L12;
            r1.setTranslationY(0.0f);
            if (r1.isEnabled() == false) goto L10;
            float r2 = 1.0f;
        L11:
            r1.setAlpha(r2);
            goto L12
        L10:
            r2 = 0.5f;
        L12:
            r0 = r0 + 1;
            goto L3
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarPopupWindow$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarPopupWindow this$0;

        public AnonymousClass2(ActionBarPopupWindow r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            ActionBarPopupWindowLayout r0 = null;
            ActionBarPopupWindow.m5536$$Nest$fputwindowAnimatorSet(this.this$0, null);
            ViewGroup r3 = (ViewGroup) this.this$0.getContentView();
            int r1 = 0;
            if ((r3 instanceof ActionBarPopupWindowLayout) == false) goto L5;
            ActionBarPopupWindowLayout r32 = (ActionBarPopupWindowLayout) r3;
            ActionBarPopupWindowLayout.m5548$$Nest$fputstartAnimationPending(r32, false);
        L13:
            int r42 = r32.getItemsCount();
        L14:
            if (r1 >= r42) goto L24;
            View r02 = r32.getItemAt(r1);
            if ((r02 instanceof GapView) == true) goto L23;
            if (r02.isEnabled() == false) goto L21;
            float r2 = 1.0f;
        L22:
            r02.setAlpha(r2);
            goto L23
        L21:
            r2 = 0.5f;
        L23:
            r1 = r1 + 1;
            goto L14
        L24:
            return;
        L5:
            int r43 = 0;
        L7:
            if (r43 >= r3.getChildCount()) goto L12;
            if ((r3.getChildAt(r43) instanceof ActionBarPopupWindowLayout) == false) goto L11;
            r0 = (ActionBarPopupWindowLayout) r3.getChildAt(r43);
            ActionBarPopupWindowLayout.m5548$$Nest$fputstartAnimationPending(r0, false);
        L11:
            r43 = r43 + 1;
            goto L7
        L12:
            r32 = r0;
            goto L13
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.ActionBarPopupWindow$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ ActionBarPopupWindow this$0;

        public AnonymousClass3(ActionBarPopupWindow r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ActionBarPopupWindow.m5536$$Nest$fputwindowAnimatorSet(this.this$0, null);
            ActionBarPopupWindow.m5535$$Nest$fputisClosingAnimated(this.this$0, false);
            this.this$0.setFocusable(false);
            ActionBarPopupWindow.access$001(this.this$0);     // Catch: Exception -> L8
        L4:
            ActionBarPopupWindow.m5537$$Nest$munregisterListener(this.this$0);
            if (ActionBarPopupWindow.m5534$$Nest$fgetpauseNotifications(this.this$0) == false) goto L11;
            ActionBarPopupWindow.m5533$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            return;
        }
    }

    /* loaded from: classes3.dex */
    public static class ActionBarPopupWindowLayout extends FrameLayout {
        private boolean animationEnabled;
        private int backAlpha;
        private float backScaleX;
        private float backScaleY;
        private int backgroundColor;
        protected Drawable backgroundDrawable;
        private final Rect bgPaddings;
        private boolean cascadeEnabled;
        public boolean clipChildren;
        private boolean fitItems;
        private int gapEndY;
        private int gapStartY;
        private BlurredBackgroundDrawableViewFactory glassBackgroundFactory;
        private ArrayList<AnimatorSet> itemAnimators;
        private int lastStartedChild;
        protected LinearLayout linearLayout;
        private OnDispatchKeyEventListener mOnDispatchKeyEventListener;
        ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
        private onSizeChangedListener onSizeChangedListener;
        Path path;
        private HashMap<View, Integer> positions;
        private float reactionsEnterProgress;
        Rect rect;
        private final Theme.ResourcesProvider resourcesProvider;
        private ScrollView scrollView;
        public boolean shownFromBottom;
        private boolean startAnimationPending;
        public int subtractBackgroundHeight;
        public boolean swipeBackGravityBottom;
        public boolean swipeBackGravityCenterHorizontal;
        public boolean swipeBackGravityRight;
        private PopupSwipeBackLayout swipeBackLayout;
        private View topView;
        public boolean updateAnimation;
        protected ActionBarPopupWindow window;

        /* renamed from: org.telegram.ui.ActionBar.ActionBarPopupWindow$ActionBarPopupWindowLayout$1, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass1 implements ViewTreeObserver.OnScrollChangedListener {
            final /* synthetic */ ActionBarPopupWindowLayout this$0;

            public AnonymousClass1(ActionBarPopupWindowLayout r1) {
                this.this$0 = r1;
            }

            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public void onScrollChanged() {
                this.this$0.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.ActionBarPopupWindow$ActionBarPopupWindowLayout$2, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass2 extends LinearLayout {
            final /* synthetic */ ActionBarPopupWindowLayout this$0;

            /* renamed from: $r8$lambda$8ZI32vj5vXQCNvZs-TOqHvI75oo, reason: not valid java name */
            public static /* synthetic */ void m5549$r8$lambda$8ZI32vj5vXQCNvZsTOqHvI75oo(View r4, int r5) {
                ViewPropertyAnimator r0 = r4.animate();
                if (r4.isEnabled() == false) goto L5;
                float r42 = 1.0f;
            L6:
                r0.alpha(r42).translationY(0.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setStartDelay((r5 * 35) + 10).setDuration(400).start();
                return;
            L5:
                r42 = 0.5f;
                goto L6
            }

            /* renamed from: $r8$lambda$wXjAZBZifSKRK7rNq_cj-o-czxc, reason: not valid java name */
            public static /* synthetic */ boolean m5550$r8$lambda$wXjAZBZifSKRK7rNq_cjoczxc(View r1) {
                if ((r1 instanceof ActionBarMenuSubItem) == true) goto L5;
                return false;
            L5:
                if (r1.getVisibility() != 0) goto L10;
                return true;
            L10:
                return false;
            }

            public AnonymousClass2(ActionBarPopupWindowLayout r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.view.ViewGroup
            public void addView(View r3, int r4, ViewGroup.LayoutParams r5) {
                r3.setAlpha(0.0f);
                if (this.this$0.shownFromBottom == false) goto L5;
                float r1 = 12.0f;
            L6:
                r3.setTranslationY(AndroidUtilities.dp(r1));
                super.addView(r3, r4, r5);
                if (ActionBarPopupWindowLayout.m5540$$Nest$fgetcascadeEnabled(this.this$0) == false) goto L15;
                if ((r3 instanceof ActionBarMenuSubItem) == false) goto L15;
                if (ExteraConfig.getGroupMessageMenu() == false) goto L15;
                r3.post(new ActionBarPopupWindow$ActionBarPopupWindowLayout$2$$ExternalSyntheticLambda2(r3, (int) IntStream.range(0, getChildCount()).mapToObj(new ActionBarPopupWindow$ActionBarPopupWindowLayout$2$$ExternalSyntheticLambda0(this)).filter(new ActionBarPopupWindow$ActionBarPopupWindowLayout$2$$ExternalSyntheticLambda1()).count()));
                return;
            L15:
                if (r3.isEnabled() == false) goto L17;
                float r2 = 1.0f;
            L18:
                r3.setAlpha(r2);
                r3.setTranslationY(0.0f);
                return;
            L17:
                r2 = 0.5f;
                goto L18
            L5:
                r1 = -12.0f;
                goto L6
            }

            @Override // android.view.ViewGroup
            public boolean drawChild(Canvas r2, View r3, long r4) {
                if ((r3 instanceof GapView) == false) goto L9;
                if (this.this$0.backgroundDrawable == null) goto L9;
                return false;
            L9:
                return super.drawChild(r2, r3, r4);
            }

            @Override // android.widget.LinearLayout, android.view.View
            public void onMeasure(int r16, int r17) {
                if (ActionBarPopupWindowLayout.m5541$$Nest$fgetfitItems(this.this$0) == false) goto L29;
                ActionBarPopupWindowLayout.m5546$$Nest$fputgapStartY(this.this$0, -1000000);
                ActionBarPopupWindowLayout.m5545$$Nest$fputgapEndY(this.this$0, -1000000);
                int r0 = getChildCount();
                int r1 = 0;
                ArrayList r2 = null;
                int r3 = 0;
                int r4 = 0;
                int r5 = 0;
            L5:
                if (r3 >= r0) goto L25;
                View r7 = getChildAt(r3);
                if (r7.getVisibility() == 8) goto L24;
                Object r12 = r7.getTag(R.id.width_tag);
                Object r13 = r7.getTag(R.id.object_tag);
                Object r14 = r7.getTag(R.id.fit_width_tag);
                if (r12 == null) goto L12;
                r7.getLayoutParams().width = -2;
            L12:
                measureChildWithMargins(r7, r16, 0, r17, 0);
                if (r14 != null) goto L21;
                boolean r8 = r12 instanceof Integer;
                if (r8 == true) goto L19;
                if (r13 != null) goto L19;
                r4 = Math.max(r4, r7.getMeasuredWidth());
            L19:
                if (r8 == false) goto L21;
                r5 = Math.max(((Integer) r12).intValue(), r7.getMeasuredWidth());
                ActionBarPopupWindowLayout.m5546$$Nest$fputgapStartY(this.this$0, r7.getMeasuredHeight());
                ActionBarPopupWindowLayout r82 = this.this$0;
                ActionBarPopupWindowLayout.m5545$$Nest$fputgapEndY(r82, ActionBarPopupWindowLayout.m5542$$Nest$fgetgapStartY(r82) + AndroidUtilities.dp(8.0f));
            L21:
                if (r2 != null) goto L23;
                r2 = new ArrayList();
            L23:
                r2.add(r7);
            L24:
                r3 = r3 + 1;
                goto L5
            L25:
                if (r2 == null) goto L29;
                int r02 = r2.size();
            L27:
                if (r1 >= r02) goto L29;
                ((View) r2.get(r1)).getLayoutParams().width = Math.max(r4, r5);
                r1 = r1 + 1;
            L29:
                super.onMeasure(r16, r17);
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.ActionBarPopupWindow$ActionBarPopupWindowLayout$3, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ ActionBarPopupWindowLayout this$0;
            final /* synthetic */ AnimatorSet val$animatorSet;
            final /* synthetic */ View val$child;

            public AnonymousClass3(ActionBarPopupWindowLayout r1, AnimatorSet r2, View r3) {
                this.this$0 = r1;
                this.val$animatorSet = r2;
                this.val$child = r3;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                ActionBarPopupWindowLayout.m5543$$Nest$fgetitemAnimators(this.this$0).remove(this.val$animatorSet);
                View r1 = this.val$child;
                if ((r1 instanceof ActionBarMenuSubItem) == false) goto L6;
                ((ActionBarMenuSubItem) r1).onItemShown();
                return;
            }
        }

        /* renamed from: -$$Nest$fgetbackScaleY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m5539$$Nest$fgetbackScaleY(ActionBarPopupWindowLayout r0) {
            return r0.backScaleY;
        }

        /* renamed from: -$$Nest$fgetcascadeEnabled, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m5540$$Nest$fgetcascadeEnabled(ActionBarPopupWindowLayout r0) {
            return r0.cascadeEnabled;
        }

        /* renamed from: -$$Nest$fgetfitItems, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m5541$$Nest$fgetfitItems(ActionBarPopupWindowLayout r0) {
            return r0.fitItems;
        }

        /* renamed from: -$$Nest$fgetgapStartY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5542$$Nest$fgetgapStartY(ActionBarPopupWindowLayout r0) {
            return r0.gapStartY;
        }

        /* renamed from: -$$Nest$fgetitemAnimators, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m5543$$Nest$fgetitemAnimators(ActionBarPopupWindowLayout r0) {
            return r0.itemAnimators;
        }

        /* renamed from: -$$Nest$fgetpositions, reason: not valid java name */
        public static /* bridge */ /* synthetic */ HashMap m5544$$Nest$fgetpositions(ActionBarPopupWindowLayout r0) {
            return r0.positions;
        }

        /* renamed from: -$$Nest$fputgapEndY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5545$$Nest$fputgapEndY(ActionBarPopupWindowLayout r0, int r1) {
            r0.gapEndY = r1;
        }

        /* renamed from: -$$Nest$fputgapStartY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5546$$Nest$fputgapStartY(ActionBarPopupWindowLayout r0, int r1) {
            r0.gapStartY = r1;
        }

        /* renamed from: -$$Nest$fputlastStartedChild, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5547$$Nest$fputlastStartedChild(ActionBarPopupWindowLayout r0, int r1) {
            r0.lastStartedChild = r1;
        }

        /* renamed from: -$$Nest$fputstartAnimationPending, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5548$$Nest$fputstartAnimationPending(ActionBarPopupWindowLayout r0, boolean r1) {
            r0.startAnimationPending = r1;
        }

        public ActionBarPopupWindowLayout(Context r2) {
            this(r2, null);
        }

        public ActionBarPopupWindowLayout(Context r2, int r3, Theme.ResourcesProvider r4) {
            this(r2, r3, r4, 0);
        }

        public ActionBarPopupWindowLayout(Context r7, int r8, Theme.ResourcesProvider r9, int r10) {
            super(r7);
            this.backScaleX = 1.0f;
            this.backScaleY = 1.0f;
            this.startAnimationPending = false;
            this.backAlpha = 255;
            this.lastStartedChild = 0;
            this.animationEnabled = true;
            this.cascadeEnabled = false;
            this.positions = new HashMap();
            this.gapStartY = -1000000;
            this.gapEndY = -1000000;
            Rect r3 = new Rect();
            this.bgPaddings = r3;
            this.reactionsEnterProgress = 1.0f;
            this.backgroundColor = -1;
            this.resourcesProvider = r9;
            if (r8 == 0) goto L5;
            this.backgroundDrawable = getResources().getDrawable(r8).mutate();
            setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        L5:
            Drawable r82 = this.backgroundDrawable;
            if (r82 == null) goto L8;
            r82.getPadding(r3);
            setBackgroundColor(getThemedColor(Theme.key_actionBarDefaultSubmenuBackground));
        L8:
            setWillNotDraw(false);
            if ((r10 & 2) <= 0) goto L12;
            this.shownFromBottom = true;
        L12:
            if ((r10 & 1) <= 0) goto L14;
            PopupSwipeBackLayout r83 = new PopupSwipeBackLayout(r7, r9);
            this.swipeBackLayout = r83;
            addView(r83, LayoutHelper.createFrame(-2, -2.0f));
        L14:
            int r92 = 48;
            if ((r10 & 4) == 0) goto L40;
        L28:
            AnonymousClass2 r84 = new AnonymousClass2(this, r7);
            this.linearLayout = r84;
            r84.setOrientation(1);
            ScrollView r72 = this.scrollView;
            if (r72 == null) goto L31;
            r72.addView(this.linearLayout, new FrameLayout.LayoutParams(-2, -2));
            return;
        L31:
            PopupSwipeBackLayout r73 = this.swipeBackLayout;
            View r85 = this.linearLayout;
            if (r73 != null) goto L34;
            addView(r85, LayoutHelper.createFrame(-2, -2.0f));
            return;
        L34:
            if (this.shownFromBottom == false) goto L36;
            r92 = 80;
        L36:
            r73.addView(r85, LayoutHelper.createFrame(-2, -2, r92));
            return;
        L40:
            this.scrollView = new ScrollView(r7);     // Catch: Throwable -> L23
            this.onScrollChangedListener = new AnonymousClass1(this);     // Catch: Throwable -> L23
            this.scrollView.setVerticalScrollBarEnabled(false);     // Catch: Throwable -> L23
            PopupSwipeBackLayout r86 = this.swipeBackLayout;     // Catch: Throwable -> L23
            if (r86 == null) goto L25;
            ScrollView r1 = this.scrollView;     // Catch: Throwable -> L23
            if (this.shownFromBottom == false) goto L21;
            int r4 = 80;
        L22:
            r86.addView(r1, LayoutHelper.createFrame(-2, -2, r4));     // Catch: Throwable -> L23
        L39:
            goto L28
        L21:
            r4 = 48;
            goto L22
        L25:
            addView(this.scrollView, LayoutHelper.createFrame(-2, -2.0f));     // Catch: Throwable -> L23
        L23:
            th = move-exception;
            FileLog.e(th);
            goto L28
        }

        public ActionBarPopupWindowLayout(Context r2, Theme.ResourcesProvider r3) {
            this(r2, R.drawable.popup_fixed_alert2, r3);
        }

        private int getSwipeBackScaledLeft(int r3) {
            PopupSwipeBackLayout r0 = this.swipeBackLayout;
            if (r0 != null) goto L5;
        L8:
            if (r0 != null) goto L10;
            return 0;
        L10:
            if (r0.stickToCenterHorizontal == true) goto L12;
            return 0;
        L12:
            return (getMeasuredWidth() - r3) / 2;
        L5:
            if (r0.stickToRight == false) goto L8;
            return getMeasuredWidth() - r3;
        }

        private void startChildAnimation(View r9) {
            if (this.animationEnabled == false) goto L17;
            AnimatorSet r0 = new AnimatorSet();
            if (r9.isEnabled() == false) goto L7;
            float r1 = 1.0f;
        L8:
            ObjectAnimator r12 = ObjectAnimator.ofFloat(r9, View.ALPHA, new float[]{0.0f, r1});
            if (this.shownFromBottom == false) goto L11;
            float r3 = 6.0f;
        L12:
            r0.playTogether(new Animator[]{r12, ObjectAnimator.ofFloat(r9, View.TRANSLATION_Y, new float[]{AndroidUtilities.dp(r3), 0.0f})});
            r0.setDuration(180);
            r0.addListener(new AnonymousClass3(this, r0, r9));
            r0.setInterpolator(ActionBarPopupWindow.m5538$$Nest$sfgetdecelerateInterpolator());
            r0.start();
            if (this.itemAnimators != null) goto L15;
            this.itemAnimators = new ArrayList();
        L15:
            this.itemAnimators.add(r0);
            return;
        L11:
            r3 = -6.0f;
            goto L12
        L7:
            r1 = 0.5f;
            goto L8
        }

        @Override // android.view.ViewGroup
        public void addView(View r1) {
            this.linearLayout.addView(r1);
        }

        public void addView(View r1, LinearLayout.LayoutParams r2) {
            this.linearLayout.addView(r1, r2);
        }

        public int addViewToSwipeBack(View r4) {
            PopupSwipeBackLayout r0 = this.swipeBackLayout;
            if (this.shownFromBottom == false) goto L5;
            int r1 = 80;
        L6:
            r0.addView(r4, LayoutHelper.createFrame(-2, -2, r1));
            return this.swipeBackLayout.getChildCount() - 1;
        L5:
            r1 = 48;
            goto L6
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r21) {
            float r8 = 16.0f;
            float r9 = 1.0f;
            if (this.swipeBackGravityRight == false) goto L8;
            setTranslationX(getMeasuredWidth() * (1.0f - this.backScaleX));
            View r1 = this.topView;
            if (r1 == null) goto L8;
            r1.setTranslationX(getMeasuredWidth() * (1.0f - this.backScaleX));
            this.topView.setAlpha(1.0f - this.swipeBackLayout.transitionProgress);
            float r12 = (-(this.topView.getMeasuredHeight() - AndroidUtilities.dp(16.0f))) * this.swipeBackLayout.transitionProgress;
            this.topView.setTranslationY(r12);
            setTranslationY(r12);
        L8:
            if (this.swipeBackGravityBottom == false) goto L11;
            setTranslationY(getMeasuredHeight() * (1.0f - this.backScaleY));
        L11:
            if (this.backgroundDrawable == null) goto L36;
            int r13 = this.gapStartY;
            ScrollView r2 = this.scrollView;
            if (r2 != null) goto L15;
            int r22 = 0;
        L16:
            int r11 = r13 - r22;
            int r14 = this.gapEndY;
            ScrollView r23 = this.scrollView;
            if (r23 != null) goto L19;
            int r24 = 0;
        L20:
            int r122 = r14 - r24;
            int r15 = 0;
        L22:
            if (r15 >= this.linearLayout.getChildCount()) goto L29;
            if ((this.linearLayout.getChildAt(r15) instanceof GapView) == false) goto L28;
            if (this.linearLayout.getChildAt(r15).getVisibility() != 0) goto L28;
            boolean r142 = true;
        L30:
            int r152 = 0;
        L32:
            if (r152 >= 2) goto L36;
            if (r152 == 1) goto L35;
        L37:
            int r16 = r21.getSaveCount();
            if (r142 == true) goto L40;
        L42:
            int r17 = 255;
            float r162 = r8;
            float r18 = r9;
            int r82 = r16;
            int r92 = -1000000;
            Canvas r19 = r21;
            if (this.gapStartY == (-1000000)) goto L45;
            r19.save();
            r19.clipRect(0, this.bgPaddings.top, getMeasuredWidth(), getMeasuredHeight());
        L45:
            boolean r25 = true;
        L46:
            Drawable r3 = this.backgroundDrawable;
            if (r25 == false) goto L49;
            int r26 = this.backAlpha;
        L50:
            r3.setAlpha(r26);
            if (this.shownFromBottom == false) goto L54;
            int r27 = getMeasuredHeight();
            int r32 = (int) (getMeasuredWidth() * this.backScaleX);
            int r4 = getSwipeBackScaledLeft(r32);
            AndroidUtilities.rectTmp2.set(r4, (int) (r27 * (r18 - this.backScaleY)), r32 + r4, r27);
        L80:
            if (this.reactionsEnterProgress != r18) goto L82;
        L85:
            Drawable r28 = this.backgroundDrawable;
            Rect r33 = AndroidUtilities.rectTmp2;
            r28.setBounds(r33);
            this.backgroundDrawable.draw(r19);
            if (this.clipChildren == false) goto L88;
            int r29 = r33.left;
            Rect r42 = this.bgPaddings;
            r33.left = r29 + r42.left;
            r33.top += r42.top;
            r33.right -= r42.right;
            r33.bottom -= r42.bottom;
            r19.clipRect(r33);
        L88:
            if (r142 == false) goto L115;
            r19.save();
            RectF r210 = AndroidUtilities.rectTmp;
            r210.set(this.backgroundDrawable.getBounds());
            r210.inset(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
            Path r34 = this.path;
            if (r34 != null) goto L92;
            this.path = new Path();
        L93:
            this.path.addRoundRect(r210, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), Path.Direction.CW);
            r19.clipPath(this.path);
            int r211 = 0;
        L95:
            if (r211 >= this.linearLayout.getChildCount()) goto L114;
            if ((this.linearLayout.getChildAt(r211) instanceof GapView) == false) goto L113;
            if (this.linearLayout.getChildAt(r211).getVisibility() != 0) goto L113;
            r19.save();
            GapView r35 = (GapView) this.linearLayout.getChildAt(r211);
            float r43 = 0.0f;
            View r6 = r35;
            float r5 = 0.0f;
        L101:
            if (r6 == this) goto L104;
            r43 = r43 + r6.getX();
            r5 = r5 + r6.getY();
            r6 = (View) r6.getParent();
            if (r6 != null) goto L101;
        L104:
            ScrollView r62 = this.scrollView;
            if (r62 != null) goto L107;
            float r63 = r18;
        L108:
            float r52 = r5 * r63;
            ScrollView r64 = this.scrollView;
            if (r64 != null) goto L111;
            int r65 = 0;
        L112:
            r19.translate(r43, r52 - r65);
            r35.draw(r19);
            r19.restore();
            goto L113
        L111:
            r65 = r64.getScrollY();
            goto L112
        L107:
            r63 = r62.getScaleY();
        L113:
            r211 = r211 + 1;
            goto L95
        L114:
            r19.restore();
            goto L115
        L92:
            r34.rewind();
        L115:
            r19.restoreToCount(r82);
        L116:
            r152 = r152 + 1;
            r8 = r162;
            r9 = r18;
            goto L32
        L82:
            if (this.rect != null) goto L84;
            this.rect = new Rect();
        L84:
            Rect r212 = this.rect;
            Rect r36 = AndroidUtilities.rectTmp2;
            int r44 = r36.right;
            int r53 = r36.top;
            r212.set(r44, r53, r44, r53);
            AndroidUtilities.lerp(this.rect, r36, this.reactionsEnterProgress, r36);
            goto L85
        L54:
            if (r11 <= (-AndroidUtilities.dp(r162))) goto L74;
            int r213 = (int) (getMeasuredHeight() * this.backScaleY);
            if (r152 != 0) goto L69;
            int r37 = (int) (getMeasuredWidth() * this.backScaleX);
            int r45 = getSwipeBackScaledLeft(r37);
            Rect r54 = AndroidUtilities.rectTmp2;
            ScrollView r66 = this.scrollView;
            if (r66 != null) goto L60;
            int r67 = 0;
        L62:
            if (this.gapStartY == r92) goto L64;
            int r7 = AndroidUtilities.dp(r18);
        L65:
            int r68 = r67 + r7;
            int r38 = r37 + r45;
            if (this.gapStartY == r92) goto L68;
            r213 = Math.min(r213, AndroidUtilities.dp(r162) + r11);
        L68:
            r54.set(r45, r68, r38, r213 - this.subtractBackgroundHeight);
            goto L80
        L64:
            r7 = 0;
            goto L65
        L60:
            r67 = -r66.getScrollY();
            goto L62
        L69:
            if (r213 < r122) goto L71;
            int r39 = (int) (getMeasuredWidth() * this.backScaleX);
            int r46 = getSwipeBackScaledLeft(r39);
            AndroidUtilities.rectTmp2.set(r46, r122, r39 + r46, r213 - this.subtractBackgroundHeight);
            goto L80
        L71:
            if (this.gapStartY == r92) goto L116;
            r19.restore();
            goto L116
        L74:
            int r214 = (int) (getMeasuredWidth() * this.backScaleX);
            int r310 = getSwipeBackScaledLeft(r214);
            Rect r47 = AndroidUtilities.rectTmp2;
            if (this.gapStartY >= 0) goto L77;
            int r55 = 0;
        L78:
            r47.set(r310, r55, r214 + r310, ((int) (getMeasuredHeight() * this.backScaleY)) - this.subtractBackgroundHeight);
            goto L80
        L77:
            r55 = -AndroidUtilities.dp(r162);
            goto L78
        L49:
            r26 = r17;
            goto L50
        L40:
            if (this.backAlpha == 255) goto L42;
            r17 = 255;
            r18 = r9;
            r92 = -1000000;
            r162 = r8;
            r82 = r16;
            r19 = r21;
            r19.saveLayerAlpha(0.0f, this.bgPaddings.top, getMeasuredWidth(), getMeasuredHeight(), this.backAlpha, 31);
            r25 = false;
            goto L46
        L35:
            if (r11 >= (-AndroidUtilities.dp(r8))) goto L37;
        L28:
            r15 = r15 + 1;
            goto L22
        L29:
            r142 = false;
            goto L30
        L19:
            r24 = r23.getScrollY();
            goto L20
        L15:
            r22 = r2.getScrollY();
        L36:
            float r182 = r9;
            float r215 = this.reactionsEnterProgress;
            if (r215 == r182) goto L121;
            Rect r83 = AndroidUtilities.rectTmp2;
            r21.saveLayerAlpha(r83.left, r83.top, r83.right, r83.bottom, (int) (r215 * 255.0f), 31);
            float r216 = (this.reactionsEnterProgress * 0.5f) + 0.5f;
            r21.scale(r216, r216, r83.right, r83.top);
            super.dispatchDraw(r21);
            r21.restore();
            return;
        L121:
            super.dispatchDraw(r21);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent r2) {
            OnDispatchKeyEventListener r0 = this.mOnDispatchKeyEventListener;
            if (r0 == null) goto L6;
            r0.onDispatchKeyEvent(r2);
        L6:
            return super.dispatchKeyEvent(r2);
        }

        @Keep
        public int getBackAlpha() {
            return this.backAlpha;
        }

        public float getBackScaleX() {
            return this.backScaleX;
        }

        public float getBackScaleY() {
            return this.backScaleY;
        }

        public int getBackgroundColor() {
            return this.backgroundColor;
        }

        public Drawable getBackgroundDrawable() {
            return this.backgroundDrawable;
        }

        public BlurredBackgroundDrawableViewFactory getGlassBackgroundFactory() {
            return this.glassBackgroundFactory;
        }

        public View getItemAt(int r1) {
            return this.linearLayout.getChildAt(r1);
        }

        public int getItemsCount() {
            return this.linearLayout.getChildCount();
        }

        public Rect getPadding() {
            return this.bgPaddings;
        }

        public PopupSwipeBackLayout getSwipeBack() {
            return this.swipeBackLayout;
        }

        public int getThemedColor(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        public int getViewsCount() {
            return this.linearLayout.getChildCount();
        }

        public int getVisibleHeight() {
            return (int) (getMeasuredHeight() * this.backScaleY);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (this.onScrollChangedListener == null) goto L6;
            getViewTreeObserver().addOnScrollChangedListener(this.onScrollChangedListener);
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.onScrollChangedListener != null) goto L5;
            return;
        L5:
            if (getViewTreeObserver().isAlive() == false) goto L9;
            getViewTreeObserver().removeOnScrollChangedListener(this.onScrollChangedListener);
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            PopupSwipeBackLayout r12 = this.swipeBackLayout;
            if (r12 == null) goto L6;
            r12.invalidateTransforms(!this.startAnimationPending);
            return;
        }

        public int precalculateHeight() {
            int r0 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE);
            this.linearLayout.measure(r0, r0);
            return this.linearLayout.getMeasuredHeight();
        }

        public void removeInnerViews() {
            this.linearLayout.removeAllViews();
        }

        public void scrollToTop() {
            ScrollView r1 = this.scrollView;
            if (r1 == null) goto L6;
            r1.scrollTo(0, 0);
            return;
        }

        public void setAnimationEnabled(boolean r1) {
            this.animationEnabled = r1;
        }

        @Keep
        public void setBackAlpha(int r2) {
            if (this.backAlpha == r2) goto L5;
            invalidate();
        L5:
            this.backAlpha = r2;
        }

        @Keep
        public void setBackScaleX(float r2) {
            if (this.backScaleX == r2) goto L8;
            this.backScaleX = r2;
            invalidate();
            onSizeChangedListener r1 = this.onSizeChangedListener;
            if (r1 == null) goto L9;
            r1.onSizeChanged();
            return;
        L9:
            return;
        }

        @Keep
        public void setBackScaleY(float r8) {
            if (this.backScaleY == r8) goto L56;
            this.backScaleY = r8;
            if (this.animationEnabled == true) goto L7;
        L41:
            invalidate();
            onSizeChangedListener r7 = this.onSizeChangedListener;
            if (r7 == null) goto L57;
            r7.onSizeChanged();
            return;
        L57:
            return;
        L7:
            if (this.updateAnimation == false) goto L41;
            int r0 = getMeasuredHeight() - AndroidUtilities.dp(16.0f);
            if (this.shownFromBottom == false) goto L26;
            int r1 = this.lastStartedChild;
        L11:
            if (r1 < 0) goto L41;
            View r2 = getItemAt(r1);
            if (r2 == null) goto L25;
            if (r2.getVisibility() != 0) goto L25;
            if ((r2 instanceof GapView) == true) goto L25;
            if (this.positions.get(r2) != null) goto L22;
        L24:
            this.lastStartedChild = r1 - 1;
            startChildAnimation(r2);
            goto L25
        L22:
            if ((r0 - ((r3.intValue() * AndroidUtilities.dp(48.0f)) + AndroidUtilities.dp(32.0f))) <= (r0 * r8)) goto L24;
        L25:
            r1 = r1 - 1;
            goto L11
        L26:
            int r12 = getItemsCount();
            int r22 = 0;
            int r3 = 0;
        L27:
            if (r22 >= r12) goto L41;
            View r4 = getItemAt(r22);
            if (r4.getVisibility() != 0) goto L40;
            r3 = r3 + r4.getMeasuredHeight();
            if (r22 < this.lastStartedChild) goto L40;
            if (this.positions.get(r4) != null) goto L37;
        L39:
            this.lastStartedChild = r22 + 1;
            startChildAnimation(r4);
            goto L40
        L37:
            if ((r3 - AndroidUtilities.dp(24.0f)) <= (r0 * r8)) goto L39;
        L40:
            r22 = r22 + 1;
            goto L27
        }

        @Override // android.view.View
        public void setBackgroundColor(int r3) {
            if (this.backgroundColor == r3) goto L8;
            Drawable r0 = this.backgroundDrawable;
            if (r0 == null) goto L9;
            this.backgroundColor = r3;
            r0.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.MULTIPLY));
            return;
        L9:
            return;
        }

        @Override // android.view.View
        public void setBackgroundDrawable(Drawable r2) {
            this.backgroundColor = -1;
            this.backgroundDrawable = r2;
            if (r2 == null) goto L6;
            r2.getPadding(this.bgPaddings);
            return;
        }

        public void setCascadeEnabled(boolean r1) {
            this.cascadeEnabled = r1;
        }

        public void setDispatchKeyEventListener(OnDispatchKeyEventListener r1) {
            this.mOnDispatchKeyEventListener = r1;
        }

        public void setFitItems(boolean r1) {
            this.fitItems = r1;
        }

        public void setGlassBackgroundFactory(BlurredBackgroundDrawableViewFactory r1) {
            this.glassBackgroundFactory = r1;
        }

        public void setOnSizeChangedListener(onSizeChangedListener r1) {
            this.onSizeChangedListener = r1;
        }

        public void setParentWindow(ActionBarPopupWindow r1) {
            this.window = r1;
        }

        public void setReactionsTransitionProgress(float r1) {
            this.reactionsEnterProgress = r1;
            invalidate();
        }

        public void setShownFromBottom(boolean r1) {
            this.shownFromBottom = r1;
        }

        public void setSwipeBackForegroundColor(int r1) {
            getSwipeBack().setForegroundColor(r1);
        }

        public void setTopView(View r1) {
            this.topView = r1;
        }

        public void setupRadialSelectors(int r8) {
            int r0 = this.linearLayout.getChildCount();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L13;
            View r3 = this.linearLayout.getChildAt(r2);
            int r4 = 10;
            if (r2 != 0) goto L7;
            int r5 = 10;
        L9:
            if (r2 == (r0 - 1)) goto L12;
            r4 = 0;
        L12:
            r3.setBackground(Theme.createRadSelectorDrawable(r8, r5, r4));
            r2 = r2 + 1;
            goto L3
        L7:
            r5 = 0;
            goto L9
        }

        public void updateRadialSelectors() {
            int r0 = this.linearLayout.getChildCount();
            View r1 = null;
            View r3 = null;
            int r4 = 0;
        L3:
            if (r4 >= r0) goto L11;
            View r5 = this.linearLayout.getChildAt(r4);
            if (r5.getVisibility() != 0) goto L10;
            if (r1 != null) goto L9;
            r1 = r5;
        L9:
            r3 = r5;
        L10:
            r4 = r4 + 1;
            goto L3
        L11:
            int r42 = 0;
            boolean r52 = false;
        L12:
            if (r42 >= r0) goto L32;
            View r6 = this.linearLayout.getChildAt(r42);
            if (r6.getVisibility() != 0) goto L31;
            Object r7 = r6.getTag(R.id.object_tag);
            if ((r6 instanceof ActionBarMenuSubItem) == false) goto L28;
            ActionBarMenuSubItem r8 = (ActionBarMenuSubItem) r6;
            if (r6 == r1) goto L23;
            if (r52 == true) goto L23;
            boolean r53 = false;
        L24:
            if (r6 != r3) goto L26;
            boolean r62 = true;
        L27:
            r8.updateSelectorBackground(r53, r62);
            goto L28
        L26:
            r62 = false;
        L23:
            r53 = true;
        L28:
            if (r7 == null) goto L30;
            r52 = true;
            goto L31
        L30:
            r52 = false;
        L31:
            r42 = r42 + 1;
            goto L12
        }
    }

    public static class GapView extends FrameLayout {
        boolean dividerVisible;
        Drawable shadowDrawable;

        public GapView(Context r2, int r3, int r4) {
            super(r2);
            this.dividerVisible = true;
            this.shadowDrawable = Theme.getThemedDrawable(getContext(), R.drawable.greydivider, r4);
            setBackgroundColor(r3);
        }

        public GapView(Context r2, Theme.ResourcesProvider r3) {
            this(r2, r3, Theme.key_actionBarDefaultSubmenuSeparator);
        }

        public GapView(Context r2, Theme.ResourcesProvider r3, int r4) {
            this(r2, Theme.getColor(r4, r3), Theme.getColor(Theme.key_windowBackgroundGrayShadow, r3));
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            super.onDraw(r5);
            if (this.dividerVisible == false) goto L8;
            Drawable r0 = this.shadowDrawable;
            if (r0 == null) goto L9;
            r0.setBounds(0, 0, getWidth(), getHeight());
            this.shadowDrawable.draw(r5);
            return;
        L9:
            return;
        }

        public void setColor(int r1) {
            setBackgroundColor(r1);
        }

        public void setDividerVisible(boolean r1) {
            this.dividerVisible = r1;
            invalidate();
        }
    }

    public interface OnDispatchKeyEventListener {
        void onDispatchKeyEvent(KeyEvent r1);
    }

    public interface onSizeChangedListener {
        void onSizeChanged();
    }

    public static /* synthetic */ void $r8$lambda$HbVcyVBCStk9DX84U8bwJXYdotU() {
    }

    /* renamed from: $r8$lambda$uFZgHjmSb-gu1taDPToT2FyUMrU, reason: not valid java name */
    public static /* synthetic */ boolean m5532$r8$lambda$uFZgHjmSbgu1taDPToT2FyUMrU(ActionBarPopupWindow r0, View r1, View r2, MotionEvent r3) {
        return r0.lambda$init$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$wAiJ8FMguTOFvn8w6tLNnCCkblA(ActionBarPopupWindowLayout r7, ValueAnimator r8) {
        int r0 = r7.getItemsCount();
        float r82 = ((Float) r8.getAnimatedValue()).floatValue();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L17;
        View r2 = r7.getItemAt(r1);
        if ((r2 instanceof GapView) == true) goto L16;
        if (r7.shownFromBottom == false) goto L10;
        float r3 = (r0 - 1) - r1;
    L11:
        float r32 = AndroidUtilities.cascade(r82, r3, r0, 4.0f);
        float r4 = 1.0f;
        r2.setTranslationY((1.0f - r32) * AndroidUtilities.dp(-6.0f));
        if (r2.isEnabled() == true) goto L15;
        r4 = 0.5f;
    L15:
        r2.setAlpha(r32 * r4);
        goto L16
    L10:
        r3 = r1;
    L16:
        r1 = r1 + 1;
        goto L3
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m5533$$Nest$fgetnotificationsLocker(ActionBarPopupWindow r0) {
        return r0.notificationsLocker;
    }

    /* renamed from: -$$Nest$fgetpauseNotifications, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5534$$Nest$fgetpauseNotifications(ActionBarPopupWindow r0) {
        return r0.pauseNotifications;
    }

    /* renamed from: -$$Nest$fputisClosingAnimated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5535$$Nest$fputisClosingAnimated(ActionBarPopupWindow r0, boolean r1) {
        r0.isClosingAnimated = r1;
    }

    /* renamed from: -$$Nest$fputwindowAnimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5536$$Nest$fputwindowAnimatorSet(ActionBarPopupWindow r0, AnimatorSet r1) {
        r0.windowAnimatorSet = r1;
    }

    /* renamed from: -$$Nest$munregisterListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5537$$Nest$munregisterListener(ActionBarPopupWindow r0) {
        r0.unregisterListener();
    }

    /* renamed from: -$$Nest$sfgetdecelerateInterpolator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DecelerateInterpolator m5538$$Nest$sfgetdecelerateInterpolator() {
        return decelerateInterpolator;
    }

    static {
        decelerateInterpolator = new DecelerateInterpolator();
        Field r0 = null;
        r0 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");     // Catch: NoSuchFieldException -> L6
        r0.setAccessible(true);     // Catch: NoSuchFieldException -> L6
    L4:
        superListenerField = r0;
        NOP = new ActionBarPopupWindow$$ExternalSyntheticLambda1();
    }

    public ActionBarPopupWindow(Context r3) {
        super(r3);
        this.animationEnabled = true;
        this.dismissAnimationDuration = 150;
        this.currentAccount = UserConfig.selectedAccount;
        this.outEmptyTime = -1;
        this.notificationsLocker = new AnimationNotificationsLocker();
        init();
    }

    public ActionBarPopupWindow(View r1, int r2, int r3) {
        super(r1, r2, r3);
        this.animationEnabled = true;
        this.dismissAnimationDuration = 150;
        this.currentAccount = UserConfig.selectedAccount;
        this.outEmptyTime = -1;
        this.notificationsLocker = new AnimationNotificationsLocker();
        init();
    }

    public static /* synthetic */ void access$001(ActionBarPopupWindow r0) {
        super.dismiss();
    }

    private void dismissDim() {
        View r0 = getContentView().getRootView();
        WindowManager r4 = (WindowManager) getContentView().getContext().getSystemService("window");
        if (r0.getLayoutParams() != null) goto L5;
        return;
    L5:
        if ((r0.getLayoutParams() instanceof WindowManager.LayoutParams) == false) goto L17;
        WindowManager.LayoutParams r1 = (WindowManager.LayoutParams) r0.getLayoutParams();
        int r2 = r1.flags;     // Catch: Exception -> L12
        if ((r2 & 2) == 0) goto L16;
        r1.flags = r2 & (-3);     // Catch: Exception -> L12
        r1.dimAmount = 0.0f;     // Catch: Exception -> L12
        r4.updateViewLayout(r0, r1);     // Catch: Exception -> L12
        return;
    L16:
        return;
    L18:
        return;
    }

    private void init() {
        View r0 = getContentView();
        if ((r0 instanceof ActionBarPopupWindowLayout) == true) goto L5;
    L7:
        Field r02 = superListenerField;
        if (r02 == null) goto L15;
        this.mSuperScrollListener = (ViewTreeObserver.OnScrollChangedListener) r02.get(this);     // Catch: Exception -> L11
        r02.set(this, NOP);     // Catch: Exception -> L11
        return;
    L11:
        this.mSuperScrollListener = null;
        return;
    L15:
        return;
    L5:
        if (((ActionBarPopupWindowLayout) r0).getSwipeBack() == null) goto L7;
        setTouchInterceptor(new ActionBarPopupWindow$$ExternalSyntheticLambda2(this, r0));
        goto L7
    }

    private /* synthetic */ boolean lambda$init$1(View r2, View r3, MotionEvent r4) {
        if (r4.getAction() != 0) goto L8;
        Drawable r32 = ((ActionBarPopupWindowLayout) r2).getBackgroundDrawable();
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(r32.getBounds());
        r0.offset(r2.getX(), r2.getY());
        if (r0.contains(r4.getX(), r4.getY()) == true) goto L10;
        dismiss();
        return true;
    L10:
        return false;
    L8:
        return false;
    }

    private void registerListener(View r3) {
        if (this.mSuperScrollListener != null) goto L5;
        return;
    L5:
        if (r3.getWindowToken() == null) goto L7;
        ViewTreeObserver r32 = r3.getViewTreeObserver();
    L8:
        ViewTreeObserver r0 = this.mViewTreeObserver;
        if (r32 == r0) goto L19;
        if (r0 != null) goto L12;
    L14:
        this.mViewTreeObserver = r32;
        if (r32 == null) goto L20;
        r32.addOnScrollChangedListener(this.mSuperScrollListener);
        return;
    L20:
        return;
    L12:
        if (r0.isAlive() == false) goto L14;
        this.mViewTreeObserver.removeOnScrollChangedListener(this.mSuperScrollListener);
        goto L14
    L19:
        return;
    L7:
        r32 = null;
        goto L8
    }

    public static AnimatorSet startAnimation(ActionBarPopupWindowLayout r10) {
        ActionBarPopupWindowLayout.m5548$$Nest$fputstartAnimationPending(r10, true);
        r10.setTranslationY(0.0f);
        float r2 = 1.0f;
        r10.setAlpha(1.0f);
        r10.setPivotX(r10.getMeasuredWidth());
        r10.setPivotY(0.0f);
        int r3 = r10.getItemsCount();
        ActionBarPopupWindowLayout.m5544$$Nest$fgetpositions(r10).clear();
        int r5 = 0;
        int r6 = 0;
    L3:
        if (r5 >= r3) goto L13;
        View r7 = r10.getItemAt(r5);
        if ((r7 instanceof GapView) == true) goto L11;
        r7.setAlpha(0.0f);
        if (r7.getVisibility() != 0) goto L11;
        ActionBarPopupWindowLayout.m5544$$Nest$fgetpositions(r10).put(r7, Integer.valueOf(r6));
        r6 = r6 + 1;
    L11:
        r5 = r5 + 1;
        goto L3
    L13:
        if (r10.shownFromBottom == false) goto L15;
        ActionBarPopupWindowLayout.m5547$$Nest$fputlastStartedChild(r10, r3 - 1);
    L17:
        if (r10.getSwipeBack() == null) goto L19;
        r10.getSwipeBack().invalidateTransforms();
        r2 = ActionBarPopupWindowLayout.m5539$$Nest$fgetbackScaleY(r10);
    L19:
        AnimatorSet r32 = new AnimatorSet();
        ValueAnimator r72 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r72.addUpdateListener(new ActionBarPopupWindow$$ExternalSyntheticLambda0(r10));
        r10.updateAnimation = false;
        r10.clipChildren = true;
        r32.playTogether(new Animator[]{ObjectAnimator.ofFloat(r10, "backScaleY", new float[]{0.0f, r2}), ObjectAnimator.ofInt(r10, "backAlpha", new int[]{0, 255}), r72});
        r32.setDuration((r6 * 16) + 150);
        r32.addListener(new AnonymousClass1(r10));
        r32.start();
        return r32;
    L15:
        ActionBarPopupWindowLayout.m5547$$Nest$fputlastStartedChild(r10, 0);
        goto L17
    }

    private void unregisterListener() {
        if (this.mSuperScrollListener == null) goto L11;
        ViewTreeObserver r0 = this.mViewTreeObserver;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.isAlive() == false) goto L9;
        this.mViewTreeObserver.removeOnScrollChangedListener(this.mSuperScrollListener);
    L9:
        this.mViewTreeObserver = null;
        return;
    }

    public void dimBehind() {
        dimBehind(0.2f);
    }

    public void dimBehind(float r4) {
        View r0 = getContentView().getRootView();
        WindowManager r3 = (WindowManager) getContentView().getContext().getSystemService("window");
        WindowManager.LayoutParams r1 = (WindowManager.LayoutParams) r0.getLayoutParams();
        r1.flags |= 2;
        r1.dimAmount = r4;
        r3.updateViewLayout(r0, r1);
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        dismiss(true);
    }

    public void dismiss(boolean r10) {
        setFocusable(false);
        dismissDim();
        AnimatorSet r1 = this.windowAnimatorSet;
        ActionBarPopupWindowLayout r2 = null;
        if (r1 == null) goto L9;
        if (r10 == true) goto L6;
    L8:
        r1.cancel();
        this.windowAnimatorSet = null;
        goto L9
    L6:
        if (this.isClosingAnimated == false) goto L8;
        return;
    L9:
        this.isClosingAnimated = false;
        if (this.animationEnabled == false) goto L50;
        if (r10 == false) goto L50;
        this.isClosingAnimated = true;
        ViewGroup r12 = (ViewGroup) getContentView();
        int r3 = 0;
    L14:
        if (r3 >= r12.getChildCount()) goto L19;
        if ((r12.getChildAt(r3) instanceof ActionBarPopupWindowLayout) == false) goto L18;
        r2 = (ActionBarPopupWindowLayout) r12.getChildAt(r3);
    L18:
        r3 = r3 + 1;
        goto L14
    L19:
        if (r2 != null) goto L21;
    L28:
        AnimatorSet r32 = new AnimatorSet();
        this.windowAnimatorSet = r32;
        if (this.outEmptyTime <= 0) goto L32;
        r32.playTogether(new Animator[]{ValueAnimator.ofFloat(new float[]{0.0f, 1.0f})});
        this.windowAnimatorSet.setDuration(this.outEmptyTime);
    L41:
        this.windowAnimatorSet.addListener(new AnonymousClass3(this));
        if (this.pauseNotifications == false) goto L44;
        this.notificationsLocker.lock();
    L44:
        this.windowAnimatorSet.start();
        return;
    L32:
        if (this.scaleOut == false) goto L34;
        r32.playTogether(new Animator[]{ObjectAnimator.ofFloat(r12, View.SCALE_Y, new float[]{0.8f}), ObjectAnimator.ofFloat(r12, View.SCALE_X, new float[]{0.8f}), ObjectAnimator.ofFloat(r12, View.ALPHA, new float[]{0.0f})});
        this.windowAnimatorSet.setDuration(this.dismissAnimationDuration);
        goto L41
    L34:
        Property r4 = View.TRANSLATION_Y;
        if (r2 != null) goto L37;
    L39:
        float r22 = -5.0f;
    L40:
        r32.playTogether(new Animator[]{ObjectAnimator.ofFloat(r12, r4, new float[]{AndroidUtilities.dp(r22)}), ObjectAnimator.ofFloat(r12, View.ALPHA, new float[]{0.0f})});
        this.windowAnimatorSet.setDuration(this.dismissAnimationDuration);
        goto L41
    L37:
        if (r2.shownFromBottom == false) goto L39;
        r22 = 5.0f;
        goto L40
    L21:
        if (ActionBarPopupWindowLayout.m5543$$Nest$fgetitemAnimators(r2) == null) goto L28;
        if (ActionBarPopupWindowLayout.m5543$$Nest$fgetitemAnimators(r2).isEmpty() == true) goto L28;
        int r33 = ActionBarPopupWindowLayout.m5543$$Nest$fgetitemAnimators(r2).size();
        int r42 = 0;
    L25:
        if (r42 >= r33) goto L27;
        AnimatorSet r5 = (AnimatorSet) ActionBarPopupWindowLayout.m5543$$Nest$fgetitemAnimators(r2).get(r42);
        r5.removeAllListeners();
        r5.cancel();
        r42 = r42 + 1;
        goto L25
    L27:
        ActionBarPopupWindowLayout.m5543$$Nest$fgetitemAnimators(r2).clear();
    L50:
        super.dismiss();     // Catch: Exception -> L49
    L47:
        unregisterListener();
    }

    public void setAnimationEnabled(boolean r1) {
        this.animationEnabled = r1;
    }

    public void setDismissAnimationDuration(int r1) {
        this.dismissAnimationDuration = r1;
    }

    public void setLayoutInScreen(boolean r3) {
    L7:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (layoutInScreenMethod != null) goto L5;
        Method r32 = PopupWindow.class.getDeclaredMethod("setLayoutInScreenEnabled", new Class[]{Boolean.TYPE});     // Catch: Exception -> L7
        layoutInScreenMethod = r32;     // Catch: Exception -> L7
        r32.setAccessible(true);     // Catch: Exception -> L7
    L5:
        layoutInScreenMethod.invoke(this, new Object[]{Boolean.TRUE});     // Catch: Exception -> L7
    }

    public void setPauseNotifications(boolean r1) {
        this.pauseNotifications = r1;
    }

    public void setScaleOut(boolean r1) {
        this.scaleOut = r1;
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View r1, int r2, int r3) {
        super.showAsDropDown(r1, r2, r3);     // Catch: Exception -> L4
        registerListener(r1);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    @Override // android.widget.PopupWindow
    public void showAtLocation(View r1, int r2, int r3, int r4) {
        super.showAtLocation(r1, r2, r3, r4);
        unregisterListener();
    }

    public void startAnimation() {
        if (this.animationEnabled == true) goto L5;
        return;
    L5:
        if (this.windowAnimatorSet != null) goto L41;
        ViewGroup r0 = (ViewGroup) getContentView();
        if ((r0 instanceof ActionBarPopupWindowLayout) == false) goto L10;
        ActionBarPopupWindowLayout r02 = (ActionBarPopupWindowLayout) r0;
        ActionBarPopupWindowLayout.m5548$$Nest$fputstartAnimationPending(r02, true);
    L18:
        r02.setTranslationY(0.0f);
        float r4 = 1.0f;
        r02.setAlpha(1.0f);
        r02.setPivotX(r02.getMeasuredWidth());
        r02.setPivotY(0.0f);
        int r5 = r02.getItemsCount();
        ActionBarPopupWindowLayout.m5544$$Nest$fgetpositions(r02).clear();
        int r6 = 0;
        int r7 = 0;
    L19:
        if (r6 >= r5) goto L26;
        View r8 = r02.getItemAt(r6);
        r8.setAlpha(0.0f);
        if (r8.getVisibility() != 0) goto L24;
        ActionBarPopupWindowLayout.m5544$$Nest$fgetpositions(r02).put(r8, Integer.valueOf(r7));
        r7 = r7 + 1;
    L24:
        r6 = r6 + 1;
        goto L19
    L26:
        if (r02.shownFromBottom == false) goto L28;
        ActionBarPopupWindowLayout.m5547$$Nest$fputlastStartedChild(r02, r5 - 1);
    L30:
        if (r02.getSwipeBack() == null) goto L32;
        r02.getSwipeBack().invalidateTransforms();
        r4 = ActionBarPopupWindowLayout.m5539$$Nest$fgetbackScaleY(r02);
    L32:
        AnimatorSet r52 = new AnimatorSet();
        this.windowAnimatorSet = r52;
        r52.playTogether(new Animator[]{ObjectAnimator.ofFloat(r02, "backScaleY", new float[]{0.0f, r4}), ObjectAnimator.ofInt(r02, "backAlpha", new int[]{0, 255})});
        this.windowAnimatorSet.setDuration((r7 * 16) + 150);
        this.windowAnimatorSet.addListener(new AnonymousClass2(this));
        this.windowAnimatorSet.start();
        return;
    L28:
        ActionBarPopupWindowLayout.m5547$$Nest$fputlastStartedChild(r02, 0);
        goto L30
    L10:
        ActionBarPopupWindowLayout r1 = null;
        int r42 = 0;
    L12:
        if (r42 >= r0.getChildCount()) goto L17;
        if ((r0.getChildAt(r42) instanceof ActionBarPopupWindowLayout) == false) goto L16;
        r1 = (ActionBarPopupWindowLayout) r0.getChildAt(r42);
        ActionBarPopupWindowLayout.m5548$$Nest$fputstartAnimationPending(r1, true);
    L16:
        r42 = r42 + 1;
        goto L12
    L17:
        r02 = r1;
        goto L18
    }

    @Override // android.widget.PopupWindow
    public void update(View r1, int r2, int r3) {
        super.update(r1, r2, r3);
        registerListener(r1);
    }

    @Override // android.widget.PopupWindow
    public void update(View r1, int r2, int r3, int r4, int r5) {
        super.update(r1, r2, r3, r4, r5);
        registerListener(r1);
    }
}
