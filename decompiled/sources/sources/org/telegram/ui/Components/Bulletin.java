package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Property;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.math.MathUtils;
import androidx.core.util.Consumer;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AndroidUtilities$$ExternalSyntheticApiModelOutline1;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.support.SparseLongArray;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.ReactionsContainerLayout;
import org.telegram.ui.Components.quickforward.BlurVisibilityDrawable;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ViewPagerActivity;

/* loaded from: classes3.dex */
public class Bulletin {

    @SuppressLint({"StaticFieldLeak"})
    private static Bulletin visibleBulletin;
    private boolean allowBlurAnimation;
    private SpringAnimation bottomOffsetSpring;
    private boolean canHide;
    private final BaseFragment containerFragment;
    private final FrameLayout containerLayout;
    private View.OnLayoutChangeListener containerLayoutListener;
    public int currentBottomOffset;
    private Delegate currentDelegate;
    private int duration;
    public int hash;
    public boolean hideAfterBottomSheet;
    private final Runnable hideRunnable;
    private boolean ignoreDetach;
    public int lastBottomOffset;
    private final Layout layout;
    private Layout.Transition layoutTransition;
    private boolean loaded;
    private Runnable onHideListener;
    private final ParentLayout parentLayout;
    public boolean setCanHideOnShow;
    private boolean showing;
    private boolean skipShowAnimation;
    public int tag;

    /* renamed from: org.telegram.ui.Components.Bulletin$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends ParentLayout {
        final /* synthetic */ Bulletin this$0;
        final /* synthetic */ FrameLayout val$containerLayout;

        public AnonymousClass1(Bulletin r1, Layout r2, FrameLayout r3) {
            this.this$0 = r1;
            this.val$containerLayout = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.Bulletin.ParentLayout
        public void onHide() {
            this.this$0.hide();
        }

        @Override // org.telegram.ui.Components.Bulletin.ParentLayout
        public void onPressedStateChanged(boolean r3) {
            this.this$0.setCanHide(!r3);
            if (this.val$containerLayout.getParent() == null) goto L6;
            this.val$containerLayout.getParent().requestDisallowInterceptTouchEvent(r3);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Bulletin$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 implements View.OnLayoutChangeListener {
        final /* synthetic */ Bulletin this$0;
        final /* synthetic */ boolean val$top;

        /* renamed from: org.telegram.ui.Components.Bulletin$2$1, reason: invalid class name */
        public class AnonymousClass1 implements Delegate {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ BaseFragment val$finalFragment;

            public AnonymousClass1(AnonymousClass2 r1, BaseFragment r2) {
                this.this$1 = r1;
                this.val$finalFragment = r2;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getBottomOffset(int r1) {
                return this.val$finalFragment.getBottomInset();
            }
        }

        public static /* synthetic */ void $r8$lambda$FSCnGi7bFyexTkU3eRd_oNUmw9Y(AnonymousClass2 r0) {
            r0.lambda$onLayoutChange$0();
        }

        public static /* synthetic */ void $r8$lambda$kbc9jQGDoUUsr65uEn9MQRgn3hU(AnonymousClass2 r0, boolean r1, Float r2) {
            r0.lambda$onLayoutChange$1(r1, r2);
        }

        public AnonymousClass2(Bulletin r1, boolean r2) {
            this.this$0 = r1;
            this.val$top = r2;
        }

        private /* synthetic */ void lambda$onLayoutChange$0() {
            Bulletin.m9050$$Nest$fgetlayout(this.this$0).transitionRunningEnter = false;
            Bulletin.m9050$$Nest$fgetlayout(this.this$0).onEnterTransitionEnd();
            Bulletin r2 = this.this$0;
            if (r2.setCanHideOnShow == false) goto L6;
            r2.setCanHide(true);
            return;
        }

        private /* synthetic */ void lambda$onLayoutChange$1(boolean r2, Float r3) {
            if (Bulletin.m9049$$Nest$fgetcurrentDelegate(this.this$0) == null) goto L7;
            if (r2 == true) goto L8;
            Bulletin.m9049$$Nest$fgetcurrentDelegate(this.this$0).onBottomOffsetChange(Bulletin.m9050$$Nest$fgetlayout(this.this$0).getHeight() - r3.floatValue());
            return;
        L8:
            return;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
            Bulletin.m9050$$Nest$fgetlayout(this.this$0).removeOnLayoutChangeListener(this);
            if (Bulletin.m9052$$Nest$fgetshowing(this.this$0) == false) goto L40;
            Bulletin.m9050$$Nest$fgetlayout(this.this$0).onShow();
            BaseFragment r12 = Bulletin.m9047$$Nest$fgetcontainerFragment(this.this$0);
            if (this.val$top == true) goto L7;
        L9:
            Bulletin r22 = this.this$0;
            Bulletin.m9054$$Nest$fputcurrentDelegate(r22, Bulletin.m9056$$Nest$smfindDelegate(r12, Bulletin.m9048$$Nest$fgetcontainerLayout(r22)));
            if (Bulletin.m9049$$Nest$fgetcurrentDelegate(this.this$0) != null) goto L14;
            if (r12 == null) goto L14;
            Bulletin.m9054$$Nest$fputcurrentDelegate(this.this$0, new AnonymousClass1(this, r12));
        L14:
            if (Bulletin.m9045$$Nest$fgetbottomOffsetSpring(this.this$0) != null) goto L16;
        L17:
            Bulletin r13 = this.this$0;
            if (Bulletin.m9049$$Nest$fgetcurrentDelegate(r13) == null) goto L20;
            int r23 = Bulletin.m9049$$Nest$fgetcurrentDelegate(this.this$0).getBottomOffset(this.this$0.tag);
        L21:
            r13.lastBottomOffset = r23;
        L23:
            if (Bulletin.m9049$$Nest$fgetcurrentDelegate(this.this$0) == null) goto L26;
            Bulletin.m9049$$Nest$fgetcurrentDelegate(this.this$0).onShow(this.this$0);
        L26:
            if (Bulletin.m9057$$Nest$smisTransitionsEnabled() == true) goto L28;
        L31:
            Bulletin.m9050$$Nest$fgetlayout(this.this$0).delegate = Bulletin.m9049$$Nest$fgetcurrentDelegate(this.this$0);
            if (Bulletin.m9049$$Nest$fgetcurrentDelegate(this.this$0) != null) goto L34;
        L36:
            this.this$0.updatePosition();
            Bulletin.m9050$$Nest$fgetlayout(this.this$0).onEnterTransitionStart();
            Bulletin.m9050$$Nest$fgetlayout(this.this$0).onEnterTransitionEnd();
            Bulletin r0 = this.this$0;
            if (r0.setCanHideOnShow == false) goto L41;
            r0.setCanHide(true);
            return;
        L41:
            return;
        L34:
            if (this.val$top == true) goto L36;
            Bulletin.m9049$$Nest$fgetcurrentDelegate(this.this$0).onBottomOffsetChange(Bulletin.m9050$$Nest$fgetlayout(this.this$0).getHeight() - this.this$0.currentBottomOffset);
            goto L36
        L28:
            if (Bulletin.m9053$$Nest$fgetskipShowAnimation(this.this$0) == true) goto L31;
            Bulletin.m9055$$Nest$mensureLayoutTransitionCreated(this.this$0);
            Bulletin.m9050$$Nest$fgetlayout(this.this$0).transitionRunningEnter = true;
            Bulletin.m9050$$Nest$fgetlayout(this.this$0).delegate = Bulletin.m9049$$Nest$fgetcurrentDelegate(this.this$0);
            Bulletin.m9050$$Nest$fgetlayout(this.this$0).invalidate();
            Layout.Transition r24 = Bulletin.m9051$$Nest$fgetlayoutTransition(this.this$0);
            Layout r32 = Bulletin.m9050$$Nest$fgetlayout(this.this$0);
            Layout r14 = Bulletin.m9050$$Nest$fgetlayout(this.this$0);
            Objects.requireNonNull(r14);
            r24.animateEnter(r32, new Bulletin$2$$ExternalSyntheticLambda0(r14), new Bulletin$2$$ExternalSyntheticLambda1(this), new Bulletin$2$$ExternalSyntheticLambda2(this, this.val$top), this.this$0.currentBottomOffset);
            return;
        L20:
            r23 = 0;
            goto L21
        L16:
            if (Bulletin.m9045$$Nest$fgetbottomOffsetSpring(this.this$0).isRunning() == true) goto L23;
        L7:
            if ((r12 instanceof ViewPagerActivity) == false) goto L9;
            r12 = ((ViewPagerActivity) r12).getCurrentVisibleFragment();
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Components.Bulletin$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 implements View.OnAttachStateChangeListener {
        final /* synthetic */ Bulletin this$0;

        public AnonymousClass3(Bulletin r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r3) {
            Bulletin.m9050$$Nest$fgetlayout(this.this$0).removeOnAttachStateChangeListener(this);
            this.this$0.hide(false, 0);
        }
    }

    /* loaded from: classes7.dex */
    public static class BulletinWindow extends Dialog {
        private final BulletinWindowLayout container;
        private WindowManager.LayoutParams params;

        /* renamed from: org.telegram.ui.Components.Bulletin$BulletinWindow$1, reason: invalid class name */
        public class AnonymousClass1 implements Delegate {
            final /* synthetic */ BulletinWindow this$0;
            final /* synthetic */ Delegate val$delegate;

            public AnonymousClass1(BulletinWindow r1, Delegate r2) {
                this.this$0 = r1;
                this.val$delegate = r2;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public boolean clipWithGradient(int r1) {
                Delegate r0 = this.val$delegate;
                if (r0 != null) goto L5;
                return false;
            L5:
                if (r0.clipWithGradient(r1) == false) goto L10;
                return true;
            L10:
                return false;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getBottomOffset(int r1) {
                Delegate r0 = this.val$delegate;
                if (r0 != null) goto L7;
                return 0;
            L7:
                return r0.getBottomOffset(r1);
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getTopOffset(int r1) {
                Delegate r0 = this.val$delegate;
                if (r0 != null) goto L7;
                return AndroidUtilities.statusBarHeight;
            L7:
                return r0.getTopOffset(r1);
            }
        }

        public class BulletinWindowLayout extends FrameLayout {
            final /* synthetic */ BulletinWindow this$0;

            public BulletinWindowLayout(BulletinWindow r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.view.ViewGroup
            public void addView(View r1) {
                super.addView(r1);
                this.this$0.show();
            }

            public WindowManager.LayoutParams getLayout() {
                return BulletinWindow.m9060$$Nest$fgetparams(this.this$0);
            }

            @Override // android.view.ViewGroup, android.view.ViewManager
            public void removeView(View r1) {
                super.removeView(r1);
                this.this$0.dismiss();     // Catch: Exception -> L6
            L4:
                Bulletin.removeDelegate(BulletinWindow.m9059$$Nest$fgetcontainer(this.this$0));
            }

            public void setTouchable(boolean r2) {
                if (BulletinWindow.m9060$$Nest$fgetparams(this.this$0) != null) goto L5;
                return;
            L5:
                BulletinWindow r0 = this.this$0;
                if (r2 == true) goto L8;
                BulletinWindow.m9060$$Nest$fgetparams(r0).flags |= 16;
            L9:
                this.this$0.getWindow().setAttributes(BulletinWindow.m9060$$Nest$fgetparams(this.this$0));
                return;
            L8:
                BulletinWindow.m9060$$Nest$fgetparams(r0).flags &= -17;
                goto L9
            }

            public void updateLayout() {
                this.this$0.getWindow().setAttributes(BulletinWindow.m9060$$Nest$fgetparams(this.this$0));
            }
        }

        /* renamed from: $r8$lambda$llpCh_ycPNZN14g9DrViU_NU-p4, reason: not valid java name */
        public static /* synthetic */ WindowInsetsCompat m9058$r8$lambda$llpCh_ycPNZN14g9DrViU_NUp4(BulletinWindow r0, View r1, WindowInsetsCompat r2) {
            return r0.lambda$new$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetcontainer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BulletinWindowLayout m9059$$Nest$fgetcontainer(BulletinWindow r0) {
            return r0.container;
        }

        /* renamed from: -$$Nest$fgetparams, reason: not valid java name */
        public static /* bridge */ /* synthetic */ WindowManager.LayoutParams m9060$$Nest$fgetparams(BulletinWindow r0) {
            return r0.params;
        }

        private BulletinWindow(Context r4, Delegate r5) {
            super(r4);
            AndroidUtilities.enableEdgeToEdge(getWindow());
            BulletinWindowLayout r0 = new BulletinWindowLayout(this, r4);
            this.container = r0;
            setContentView(r0, new ViewGroup.LayoutParams(-1, -1));
            ViewCompat.setOnApplyWindowInsetsListener(r0, new Bulletin$BulletinWindow$$ExternalSyntheticLambda0(this));
            int r42 = Build.VERSION.SDK_INT;
            if (r42 < 30) goto L5;
            r0.setSystemUiVisibility(1792);
        L6:
            Bulletin.addDelegate(r0, new AnonymousClass1(this, r5));
            Window r52 = getWindow();     // Catch: Exception -> L16
            r52.setWindowAnimations(R.style.DialogNoAnimation);     // Catch: Exception -> L16
            r52.setBackgroundDrawable(null);     // Catch: Exception -> L16
            WindowManager.LayoutParams r02 = r52.getAttributes();     // Catch: Exception -> L16
            this.params = r02;     // Catch: Exception -> L16
            r02.width = -1;     // Catch: Exception -> L16
            r02.height = -1;     // Catch: Exception -> L16
            r02.gravity = 51;     // Catch: Exception -> L16
            r02.dimAmount = 0.0f;     // Catch: Exception -> L16
            r02.format = -3;     // Catch: Exception -> L16
            r02.flags = (((-3) & r02.flags) | (-1946091240)) & (-1025);     // Catch: Exception -> L16
            boolean r2 = true;
            if (r42 < 28) goto L10;
            AndroidUtilities$$ExternalSyntheticApiModelOutline1.m(r02, 1);     // Catch: Exception -> L16
        L10:
            r52.setAttributes(r02);     // Catch: Exception -> L16
            if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(Theme.key_windowBackgroundGray)) > 0.721f) goto L14;
            r2 = false;
        L14:
            AndroidUtilities.setLightNavigationBar(this, r2);     // Catch: Exception -> L16
            return;
        L19:
            return;
        L5:
            r0.setSystemUiVisibility(1280);
            goto L6
        }

        private void applyInsets(Insets r4) {
            BulletinWindowLayout r3 = this.container;
            if (r3 == null) goto L6;
            r3.setPadding(r4.left, r4.top, r4.right, r4.bottom);
            return;
        }

        private /* synthetic */ WindowInsetsCompat lambda$new$0(View r2, WindowInsetsCompat r3) {
            applyInsets(AndroidUtilities.getDefaultWindowInsets(r3, false));
            r2.requestLayout();
            return WindowInsetsCompat.CONSUMED;
        }

        public static BulletinWindowLayout make(Context r2) {
            return new BulletinWindow(r2, null).container;
        }

        public static BulletinWindowLayout make(Context r1, Delegate r2) {
            return new BulletinWindow(r1, r2).container;
        }

        @Override // android.app.Dialog
        public void show() {
            if (AndroidUtilities.isSafeToShow(getContext()) == true) goto L5;
            return;
        L5:
            super.show();
        }
    }

    @SuppressLint({"ViewConstructor"})
    /* loaded from: classes7.dex */
    public static abstract class Button extends FrameLayout implements Layout.Callback {
        public Button(Context r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout.Callback
        public void onEnterTransitionEnd(Layout r1) {
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout.Callback
        public void onEnterTransitionStart(Layout r1) {
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout.Callback
        public void onExitTransitionEnd(Layout r1) {
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout.Callback
        public void onExitTransitionStart(Layout r1) {
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout.Callback
        public void onHide(Layout r1) {
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout.Callback
        public void onShow(Layout r1) {
        }
    }

    @SuppressLint({"ViewConstructor"})
    /* loaded from: classes7.dex */
    public static class ButtonLayout extends Layout {
        private Button button;
        private int childrenMeasuredWidth;
        Theme.ResourcesProvider resourcesProvider;
        public TimerView timerView;
        private boolean wrapWidth;

        public ButtonLayout(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
            this.resourcesProvider = r2;
        }

        public Button getButton() {
            return this.button;
        }

        @Override // android.view.ViewGroup
        public void measureChildWithMargins(View r7, int r8, int r9, int r10, int r11) {
            Button r0 = this.button;
            if (r0 == null) goto L6;
            if (r7 == r0) goto L6;
            r9 = r9 + (r0.getMeasuredWidth() - AndroidUtilities.dp(12.0f));
        L6:
            super.measureChildWithMargins(r7, r8, r9, r10, r11);
            if (r7 == this.button) goto L10;
            ViewGroup.MarginLayoutParams r6 = (ViewGroup.MarginLayoutParams) r7.getLayoutParams();
            this.childrenMeasuredWidth = Math.max(this.childrenMeasuredWidth, (r6.leftMargin + r6.rightMargin) + r7.getMeasuredWidth());
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            this.childrenMeasuredWidth = 0;
            if (this.wrapWidth == false) goto L5;
            r3 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), Integer.MIN_VALUE);
        L5:
            super.onMeasure(r3, r4);
            if (this.button != null) goto L8;
            return;
        L8:
            if (View.MeasureSpec.getMode(r3) != Integer.MIN_VALUE) goto L12;
            setMeasuredDimension(this.childrenMeasuredWidth + this.button.getMeasuredWidth(), getMeasuredHeight());
            return;
        }

        public void setButton(Button r3) {
            Button r0 = this.button;
            if (r0 == null) goto L5;
            removeCallback(r0);
            removeView(this.button);
        L5:
            this.button = r3;
            if (r3 == null) goto L9;
            addCallback(r3);
            addView(r3, 0, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, 8388629));
            return;
        }

        public void setTimer() {
            TimerView r0 = new TimerView(getContext(), this.resourcesProvider);
            this.timerView = r0;
            r0.timeLeft = 5000;
            addView(r0, LayoutHelper.createFrameRelatively(20.0f, 20.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 21.0f, 0.0f, 21.0f, 0.0f));
        }

        public void setWrapWidth() {
            this.wrapWidth = true;
        }
    }

    public interface Delegate {
        default boolean allowLayoutChanges() {
            return true;
        }

        default boolean bottomOffsetAnimated() {
            return true;
        }

        default boolean clipWithGradient(int r1) {
            return false;
        }

        default int getBottomOffset(int r1) {
            return 0;
        }

        default int getTopOffset(int r1) {
            return 0;
        }

        default void onBottomOffsetChange(float r1) {
        }

        default void onHide(Bulletin r1) {
        }

        default void onShow(Bulletin r1) {
        }
    }

    /* loaded from: classes7.dex */
    public static class EmptyBulletin extends Bulletin {
        public EmptyBulletin() {
            super(null);
        }

        @Override // org.telegram.ui.Components.Bulletin
        public Bulletin show() {
            return this;
        }
    }

    /* loaded from: classes7.dex */
    public static abstract class Layout extends FrameLayout {
        public static final FloatPropertyCompat<Layout> IN_OUT_OFFSET_Y = null;
        public static final Property<Layout, Float> IN_OUT_OFFSET_Y2 = null;
        Drawable background;
        private BlurVisibilityDrawable blurVisibilityDrawable;
        protected Bulletin bulletin;
        private final List<Callback> callbacks;
        private LinearGradient clipGradient;
        private Matrix clipMatrix;
        private Paint clipPaint;
        Delegate delegate;
        private boolean hasCustomBackground;
        public float inOutOffset;
        public View.OnClickListener onClickListener;
        private final Theme.ResourcesProvider resourcesProvider;
        public boolean top;
        public boolean transitionRunningEnter;
        public boolean transitionRunningExit;
        private int wideScreenGravity;
        private int wideScreenWidth;

        /* renamed from: org.telegram.ui.Components.Bulletin$Layout$1, reason: invalid class name */
        public class AnonymousClass1 extends FloatPropertyCompat<Layout> {
            public AnonymousClass1(String r1) {
                super(r1);
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public /* bridge */ /* synthetic */ float getValue(Layout r1) {
                return getValue2(r1);
            }

            /* renamed from: getValue, reason: avoid collision after fix types in other method */
            public float getValue2(Layout r1) {
                return r1.inOutOffset;
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public /* bridge */ /* synthetic */ void setValue(Layout r1, float r2) {
                setValue2(r1, r2);
            }

            /* renamed from: setValue, reason: avoid collision after fix types in other method */
            public void setValue2(Layout r1, float r2) {
                Layout.m9062$$Nest$msetInOutOffset(r1, r2);
            }
        }

        /* renamed from: org.telegram.ui.Components.Bulletin$Layout$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimationProperties.FloatProperty<Layout> {
            public AnonymousClass2(String r1) {
                super(r1);
            }

            public Float get(Layout r1) {
                return Float.valueOf(r1.inOutOffset);
            }

            @Override // android.util.Property
            public /* bridge */ /* synthetic */ Float get(Object r1) {
                return get((Layout) r1);
            }

            @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
            public /* bridge */ /* synthetic */ void setValue(Layout r1, float r2) {
                setValue2(r1, r2);
            }

            /* renamed from: setValue, reason: avoid collision after fix types in other method */
            public void setValue2(Layout r1, float r2) {
                Layout.m9062$$Nest$msetInOutOffset(r1, r2);
            }
        }

        public interface Callback {
            default void onAttach(Layout r1, Bulletin r2) {
            }

            default void onDetach(Layout r1) {
            }

            default void onEnterTransitionEnd(Layout r1) {
            }

            default void onEnterTransitionStart(Layout r1) {
            }

            default void onExitTransitionEnd(Layout r1) {
            }

            default void onExitTransitionStart(Layout r1) {
            }

            void onHide(Layout r1);

            void onShow(Layout r1);
        }

        public static class DefaultTransition implements Transition {
            long duration;

            /* renamed from: org.telegram.ui.Components.Bulletin$Layout$DefaultTransition$1, reason: invalid class name */
            public class AnonymousClass1 extends AnimatorListenerAdapter {
                final /* synthetic */ DefaultTransition this$0;
                final /* synthetic */ Runnable val$endAction;
                final /* synthetic */ Runnable val$startAction;

                public AnonymousClass1(DefaultTransition r1, Runnable r2, Runnable r3) {
                    this.this$0 = r1;
                    this.val$startAction = r2;
                    this.val$endAction = r3;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r1) {
                    Runnable r0 = this.val$endAction;
                    if (r0 == null) goto L6;
                    r0.run();
                    return;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator r1) {
                    Runnable r0 = this.val$startAction;
                    if (r0 == null) goto L6;
                    r0.run();
                    return;
                }
            }

            /* renamed from: org.telegram.ui.Components.Bulletin$Layout$DefaultTransition$2, reason: invalid class name */
            public class AnonymousClass2 extends AnimatorListenerAdapter {
                final /* synthetic */ DefaultTransition this$0;
                final /* synthetic */ Runnable val$endAction;
                final /* synthetic */ Runnable val$startAction;

                public AnonymousClass2(DefaultTransition r1, Runnable r2, Runnable r3) {
                    this.this$0 = r1;
                    this.val$startAction = r2;
                    this.val$endAction = r3;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r1) {
                    Runnable r0 = this.val$endAction;
                    if (r0 == null) goto L6;
                    r0.run();
                    return;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator r1) {
                    Runnable r0 = this.val$startAction;
                    if (r0 == null) goto L6;
                    r0.run();
                    return;
                }
            }

            /* renamed from: $r8$lambda$-_dAL8EiYJhs_PJMBkDR1lgzuaI, reason: not valid java name */
            public static /* synthetic */ void m9064$r8$lambda$_dAL8EiYJhs_PJMBkDR1lgzuaI(Consumer r0, Layout r1, ValueAnimator r2) {
                r0.accept(Float.valueOf(r1.getTranslationY()));
            }

            public static /* synthetic */ void $r8$lambda$DQfGi1PDMuRxwJqkCozf3FiWw7c(Consumer r0, Layout r1, ValueAnimator r2) {
                r0.accept(Float.valueOf(r1.getTranslationY()));
            }

            public DefaultTransition() {
                this.duration = 255;
            }

            @Override // org.telegram.ui.Components.Bulletin.Layout.Transition
            public void animateEnter(Layout r4, Runnable r5, Runnable r6, Consumer<Float> r7, int r8) {
                Layout.m9062$$Nest$msetInOutOffset(r4, r4.getMeasuredHeight());
                if (r7 == null) goto L5;
                r7.accept(Float.valueOf(r4.getTranslationY()));
            L5:
                ObjectAnimator r82 = ObjectAnimator.ofFloat(r4, Layout.IN_OUT_OFFSET_Y2, new float[]{0.0f});
                r82.setDuration(this.duration);
                r82.setInterpolator(Easings.easeOutQuad);
                if (r5 != null) goto L8;
                if (r6 != null) goto L8;
            L9:
                if (r7 == null) goto L11;
                r82.addUpdateListener(new Bulletin$Layout$DefaultTransition$$ExternalSyntheticLambda1(r7, r4));
            L11:
                r82.start();
                return;
            L8:
                r82.addListener(new AnonymousClass1(this, r5, r6));
                goto L9
            }

            @Override // org.telegram.ui.Components.Bulletin.Layout.Transition
            public void animateExit(Layout r4, Runnable r5, Runnable r6, Consumer<Float> r7, int r8) {
                ObjectAnimator r82 = ObjectAnimator.ofFloat(r4, Layout.IN_OUT_OFFSET_Y2, new float[]{r4.getHeight()});
                r82.setDuration(175);
                r82.setInterpolator(Easings.easeInQuad);
                if (r5 != null) goto L5;
                if (r6 != null) goto L5;
            L6:
                if (r7 == null) goto L8;
                r82.addUpdateListener(new Bulletin$Layout$DefaultTransition$$ExternalSyntheticLambda0(r7, r4));
            L8:
                r82.start();
                return;
            L5:
                r82.addListener(new AnonymousClass2(this, r5, r6));
                goto L6
            }
        }

        public static class SpringTransition implements Transition {
            public static /* synthetic */ void $r8$lambda$PHmnhKumdkiFxUnowk2C5oLOEDw(Consumer r0, Layout r1, DynamicAnimation r2, float r3, float r4) {
                r0.accept(Float.valueOf(r1.getTranslationY()));
            }

            /* renamed from: $r8$lambda$l75tbtgbX7e0-wzIDlEvtqUbJ8Y, reason: not valid java name */
            public static /* synthetic */ void m9065$r8$lambda$l75tbtgbX7e0wzIDlEvtqUbJ8Y(Layout r0, Runnable r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
                Layout.m9062$$Nest$msetInOutOffset(r0, 0.0f);
                if (r3 == true) goto L6;
                r1.run();
                return;
            }

            public static /* synthetic */ void $r8$lambda$rSAb8tEiJp0HQ6S06SDw6bq0Jh0(Runnable r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
                if (r2 == true) goto L5;
                r0.run();
                return;
            }

            public static /* synthetic */ void $r8$lambda$s8_54houU3UvhgMxgOp0ZQLdKw8(Consumer r0, Layout r1, DynamicAnimation r2, float r3, float r4) {
                r0.accept(Float.valueOf(r1.getTranslationY()));
            }

            public SpringTransition() {
            }

            @Override // org.telegram.ui.Components.Bulletin.Layout.Transition
            public void animateEnter(Layout r2, Runnable r3, Runnable r4, Consumer<Float> r5, int r6) {
                Layout.m9062$$Nest$msetInOutOffset(r2, r2.getMeasuredHeight());
                if (r5 == null) goto L5;
                r5.accept(Float.valueOf(r2.getTranslationY()));
            L5:
                SpringAnimation r1 = new SpringAnimation(r2, Layout.IN_OUT_OFFSET_Y, 0.0f);
                r1.getSpring().setDampingRatio(0.8f);
                r1.getSpring().setStiffness(400.0f);
                if (r4 == null) goto L8;
                r1.addEndListener(new Bulletin$Layout$SpringTransition$$ExternalSyntheticLambda2(r2, r4));
            L8:
                if (r5 == null) goto L10;
                r1.addUpdateListener(new Bulletin$Layout$SpringTransition$$ExternalSyntheticLambda3(r5, r2));
            L10:
                r1.start();
                if (r3 == null) goto L14;
                r3.run();
                return;
            }

            @Override // org.telegram.ui.Components.Bulletin.Layout.Transition
            public void animateExit(Layout r2, Runnable r3, Runnable r4, Consumer<Float> r5, int r6) {
                SpringAnimation r1 = new SpringAnimation(r2, Layout.IN_OUT_OFFSET_Y, r2.getHeight());
                r1.getSpring().setDampingRatio(0.8f);
                r1.getSpring().setStiffness(400.0f);
                if (r4 == null) goto L5;
                r1.addEndListener(new Bulletin$Layout$SpringTransition$$ExternalSyntheticLambda0(r4));
            L5:
                if (r5 == null) goto L7;
                r1.addUpdateListener(new Bulletin$Layout$SpringTransition$$ExternalSyntheticLambda1(r5, r2));
            L7:
                r1.start();
                if (r3 == null) goto L11;
                r3.run();
                return;
            }
        }

        public interface Transition {
            void animateEnter(Layout r1, Runnable r2, Runnable r3, Consumer<Float> r4, int r5);

            void animateExit(Layout r1, Runnable r2, Runnable r3, Consumer<Float> r4, int r5);
        }

        /* renamed from: -$$Nest$misNeedSwipeAlphaAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m9061$$Nest$misNeedSwipeAlphaAnimation(Layout r0, boolean r1) {
            return r0.isNeedSwipeAlphaAnimation(r1);
        }

        /* renamed from: -$$Nest$msetInOutOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9062$$Nest$msetInOutOffset(Layout r0, float r1) {
            r0.setInOutOffset(r1);
        }

        /* renamed from: -$$Nest$msetWideScreenParams, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9063$$Nest$msetWideScreenParams(Layout r0, int r1, int r2) {
            r0.setWideScreenParams(r1, r2);
        }

        static {
            IN_OUT_OFFSET_Y = new AnonymousClass1("offsetY");
            IN_OUT_OFFSET_Y2 = new AnonymousClass2("offsetY");
        }

        public Layout(Context r3, Theme.ResourcesProvider r4) {
            super(r3);
            this.callbacks = new ArrayList();
            this.wideScreenWidth = -2;
            this.wideScreenGravity = 1;
            this.resourcesProvider = r4;
            setMinimumHeight(AndroidUtilities.dp(48.0f));
            setBackground(getThemedColor(Theme.key_undo_background));
            updateSize();
            setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
            setWillNotDraw(false);
            ScaleStateListAnimator.apply(this, 0.02f, 1.5f);
        }

        @SuppressLint({"RtlHardcoded"})
        private boolean isNeedSwipeAlphaAnimation(boolean r4) {
            if (isWideScreen() == true) goto L5;
        L17:
            return false;
        L5:
            if (this.wideScreenWidth == (-1)) goto L17;
            int r3 = this.wideScreenGravity;
            if (r3 != 1) goto L11;
            return true;
        L11:
            if (r4 == false) goto L15;
            if (r3 != 5) goto L14;
            return true;
        L14:
            return false;
        L15:
            if (r3 == 5) goto L17;
            return true;
        }

        private boolean isWideScreen() {
            if (AndroidUtilities.isTablet() == true) goto L9;
            Point r1 = AndroidUtilities.displaySize;
            if (r1.x >= r1.y) goto L11;
            return false;
        L11:
            return true;
        L9:
            return true;
        }

        private void setInOutOffset(float r1) {
            this.inOutOffset = r1;
            updatePosition();
            Bulletin r12 = this.bulletin;
            if (r12 != null) goto L5;
            return;
        L5:
            if (Bulletin.m9044$$Nest$fgetallowBlurAnimation(r12) == false) goto L9;
            invalidate();
            return;
        }

        private void setWideScreenParams(int r3, int r4) {
            boolean r1 = true;
            if (this.wideScreenWidth == r3) goto L5;
            this.wideScreenWidth = r3;
            boolean r32 = true;
        L7:
            if (this.wideScreenGravity == r4) goto L9;
            this.wideScreenGravity = r4;
        L11:
            if (isWideScreen() == false) goto L15;
            if (r1 == false) goto L16;
            updateSize();
            return;
        L16:
            return;
        L15:
            return;
        L9:
            r1 = r32;
            goto L11
        L5:
            r32 = false;
            goto L7
        }

        private void updateSize() {
            boolean r0 = isWideScreen();
            if (r0 == false) goto L5;
            int r1 = this.wideScreenWidth;
        L6:
            boolean r2 = this.top;
            int r3 = 80;
            if (r0 == false) goto L11;
            if (r2 == false) goto L10;
            r3 = 48;
        L10:
            r3 = r3 | this.wideScreenGravity;
        L13:
            setLayoutParams(LayoutHelper.createFrame(r1, -2, r3));
            return;
        L11:
            if (r2 == false) goto L13;
            r3 = 48;
            goto L13
        L5:
            r1 = -1;
            goto L6
        }

        public void addCallback(Callback r1) {
            this.callbacks.add(r1);
        }

        public Transition createTransition() {
            return new SpringTransition();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            Bulletin r0 = this.bulletin;
            if (r0 != null) goto L5;
        L14:
            dispatchDrawImpl(r8, false, 255);
            return;
        L5:
            if (Bulletin.m9044$$Nest$fgetallowBlurAnimation(r0) == false) goto L14;
            if (this.blurVisibilityDrawable != null) goto L10;
            this.blurVisibilityDrawable = new BlurVisibilityDrawable(new Bulletin$Layout$$ExternalSyntheticLambda0(this));
        L10:
            if (this.blurVisibilityDrawable.hasBitmap() == true) goto L12;
            this.blurVisibilityDrawable.render(getMeasuredWidth(), getMeasuredHeight(), AndroidUtilities.dp(10.0f), 6.0f);
        L12:
            this.blurVisibilityDrawable.setAlpha(MathUtils.clamp((int) ((1.0f - (this.inOutOffset / getMeasuredHeight())) * 255.0f), 0, 255));
            this.blurVisibilityDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            this.blurVisibilityDrawable.draw(r8);
        }

        public void dispatchDrawImpl(Canvas r24, boolean r25, int r26) {
            Canvas r1 = r24;
            if (this.bulletin == null) goto L52;
            if (r26 == 0) goto L53;
            this.background.setBounds(getPaddingLeft(), getPaddingTop(), getMeasuredWidth() - getPaddingRight(), getMeasuredBackgroundHeight() - getPaddingBottom());
            if (isTransitionRunning() == true) goto L9;
        L50:
            this.background.draw(r1);
            super.dispatchDraw(r24);
            return;
        L9:
            if (this.delegate == null) goto L50;
            float r8 = r2.getTopOffset(this.bulletin.tag) - getY();
            float r9 = (((View) getParent()).getMeasuredHeight() - getBottomOffset()) - getY();
            if (r25 == false) goto L13;
        L15:
            boolean r12 = false;
        L16:
            r1.save();
            if (r25 == true) goto L19;
            r1.clipRect(0.0f, r8, getMeasuredWidth(), r9);
        L19:
            if (r12 == false) goto L21;
        L24:
            boolean r14 = true;
        L25:
            if (r14 == false) goto L27;
            r1.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), r26, 31);
        L27:
            this.background.draw(r1);
            super.dispatchDraw(r24);
            if (r12 == true) goto L30;
        L46:
            if (r14 == false) goto L48;
            r1.restore();
        L48:
            r1.restore();
            invalidate();
            return;
        L30:
            if (this.clipPaint != null) goto L37;
            Paint r2 = new Paint(1);
            this.clipPaint = r2;
            r2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            float r22 = AndroidUtilities.dp(8.0f);
            if (this.top == false) goto L35;
            int[] r4 = {-16777216, 0};
        L36:
            this.clipGradient = new LinearGradient(0.0f, 0.0f, 0.0f, r22, r4, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            Matrix r23 = new Matrix();
            this.clipMatrix = r23;
            this.clipGradient.setLocalMatrix(r23);
            this.clipPaint.setShader(this.clipGradient);
            goto L37
        L35:
            r4 = new int[]{0, -16777216};
        L37:
            r1.save();
            this.clipMatrix.reset();
            Matrix r27 = this.clipMatrix;
            if (this.top == false) goto L40;
            float r42 = r8;
        L41:
            r27.postTranslate(0.0f, r42);
            this.clipGradient.setLocalMatrix(this.clipMatrix);
            if (this.top == false) goto L44;
            r1.drawRect(0.0f, r8, getWidth(), r8 + AndroidUtilities.dp(8.0f), this.clipPaint);
            r1 = r24;
        L45:
            r1.restore();
            goto L46
        L44:
            r1 = r24;
            r1.drawRect(0.0f, r9 - AndroidUtilities.dp(8.0f), getWidth(), r9, this.clipPaint);
            goto L45
        L40:
            r42 = r9 - AndroidUtilities.dp(8.0f);
            goto L41
        L21:
            if (r26 != 255) goto L24;
            r14 = false;
            goto L25
        L13:
            if (this.delegate.clipWithGradient(this.bulletin.tag) == false) goto L15;
            r12 = true;
            goto L16
        L53:
            return;
        }

        public void dispatchDrawImplBlur(Canvas r2, int r3) {
            dispatchDrawImpl(r2, true, r3);
        }

        public CharSequence getAccessibilityText() {
            return null;
        }

        public float getBottomOffset() {
            if (this.bulletin == null) goto L15;
            Delegate r0 = this.delegate;
            if (r0 == null) goto L9;
            if (r0.bottomOffsetAnimated() == false) goto L15;
        L9:
            if (Bulletin.m9045$$Nest$fgetbottomOffsetSpring(this.bulletin) == null) goto L15;
            if (Bulletin.m9045$$Nest$fgetbottomOffsetSpring(this.bulletin).isRunning() == false) goto L15;
            int r1 = this.bulletin.lastBottomOffset;
        L14:
            return r1;
        L15:
            Delegate r02 = this.delegate;
            if (r02 == null) goto L22;
            Bulletin r12 = this.bulletin;
            if (r12 == null) goto L20;
            int r13 = r12.tag;
        L21:
            r1 = r02.getBottomOffset(r13);
            goto L14
        L20:
            r13 = 0;
            goto L21
        L22:
            return 0.0f;
        }

        public Bulletin getBulletin() {
            return this.bulletin;
        }

        public int getMeasuredBackgroundHeight() {
            return getMeasuredHeight();
        }

        public int getThemedColor(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        public float getTopOffset() {
            if (this.delegate == null) goto L10;
            Bulletin r1 = this.bulletin;
            if (r1 == null) goto L7;
            int r12 = r1.tag;
        L9:
            return r0.getTopOffset(r12);
        L7:
            r12 = 0;
            goto L9
        L10:
            return 0.0f;
        }

        public boolean isAttachedToBulletin() {
            if (this.bulletin == null) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isTransitionRunning() {
            if (this.transitionRunningEnter == false) goto L5;
            return true;
        L5:
            if (this.transitionRunningExit == true) goto L11;
            return false;
        L11:
            return true;
        }

        public void onAttach(Bulletin r4) {
            this.bulletin = r4;
            int r0 = this.callbacks.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.callbacks.get(r1).onAttach(this, r4);
            r1 = r1 + 1;
            goto L3
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration r1) {
            super.onConfigurationChanged(r1);
            updateSize();
        }

        public void onDetach() {
            this.bulletin = null;
            int r0 = this.callbacks.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.callbacks.get(r1).onDetach(this);
            r1 = r1 + 1;
            goto L3
        L5:
            BlurVisibilityDrawable r3 = this.blurVisibilityDrawable;
            if (r3 == null) goto L10;
            r3.recycle();
            return;
        }

        public void onEnterTransitionEnd() {
            int r0 = this.callbacks.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.callbacks.get(r1).onEnterTransitionEnd(this);
            r1 = r1 + 1;
            goto L3
        }

        public void onEnterTransitionStart() {
            int r0 = this.callbacks.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.callbacks.get(r1).onEnterTransitionStart(this);
            r1 = r1 + 1;
            goto L3
        }

        public void onExitTransitionEnd() {
            int r0 = this.callbacks.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.callbacks.get(r1).onExitTransitionEnd(this);
            r1 = r1 + 1;
            goto L3
        }

        public void onExitTransitionStart() {
            int r0 = this.callbacks.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.callbacks.get(r1).onExitTransitionStart(this);
            r1 = r1 + 1;
            goto L3
        }

        public void onHide() {
            int r0 = this.callbacks.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.callbacks.get(r1).onHide(this);
            r1 = r1 + 1;
            goto L3
        }

        public void onShow() {
            int r0 = this.callbacks.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.callbacks.get(r1).onShow(this);
            r1 = r1 + 1;
            goto L3
        }

        public void removeCallback(Callback r1) {
            this.callbacks.remove(r1);
        }

        public void setBackground(int r2) {
            setBackground(r2, 16);
        }

        public void setBackground(int r2, int r3) {
            if (this.hasCustomBackground == true) goto L6;
            this.background = Theme.createRoundRectDrawable(AndroidUtilities.dp(r3), r2);
            return;
        }

        public void setCustomBackground(Drawable r1) {
            this.background = r1;
            this.hasCustomBackground = true;
        }

        @Override // android.view.View
        public void setOnClickListener(View.OnClickListener r1) {
            this.onClickListener = r1;
        }

        public void setTop(boolean r2) {
            if (this.top == r2) goto L6;
            this.top = r2;
            updateSize();
            return;
        }

        public void updatePosition() {
            float r1 = 0.0f;
            if (this.delegate != null) goto L5;
        L12:
            float r0 = -r1;
            float r12 = this.inOutOffset;
            if (this.top == false) goto L15;
            int r2 = -1;
        L16:
            setTranslationY(r0 + (r12 * r2));
            return;
        L15:
            r2 = 1;
            goto L16
        L5:
            if (this.top == false) goto L11;
            Bulletin r22 = this.bulletin;
            if (r22 == null) goto L9;
            int r23 = r22.tag;
        L10:
            r1 = 0.0f - r0.getTopOffset(r23);
            goto L12
        L9:
            r23 = 0;
            goto L10
        L11:
            r1 = 0.0f + getBottomOffset();
            goto L12
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (this.background != r2) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* loaded from: classes7.dex */
    public interface LoadingLayout {
        void onTextLoaded(CharSequence r1);
    }

    /* loaded from: classes7.dex */
    public static class LoadingLottieLayout extends LottieLayout implements LoadingLayout {
        public LinkSpanDrawable.LinksTextView textLoadingView;

        public LoadingLottieLayout(Context r8, Theme.ResourcesProvider r9) {
            super(r8, r9);
            LinkSpanDrawable.LinksTextView r92 = new LinkSpanDrawable.LinksTextView(r8);
            this.textLoadingView = r92;
            r92.setDisablePaddingsOffset(true);
            this.textLoadingView.setSingleLine();
            this.textLoadingView.setTypeface(Typeface.SANS_SERIF);
            this.textLoadingView.setTextSize(1, 15.0f);
            this.textLoadingView.setEllipsize(TextUtils.TruncateAt.END);
            this.textLoadingView.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
            this.textView.setVisibility(8);
            addView(this.textLoadingView, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 56.0f, 0.0f, 8.0f, 0.0f));
            setTextColor(getThemedColor(Theme.key_undo_infoColor));
        }

        @Override // org.telegram.ui.Components.Bulletin.LoadingLayout
        public void onTextLoaded(CharSequence r3) {
            this.textView.setText(r3);
            AndroidUtilities.updateViewShow(this.textLoadingView, false, false, true);
            AndroidUtilities.updateViewShow(this.textView, true, false, true);
        }

        @Override // org.telegram.ui.Components.Bulletin.LottieLayout
        public void setTextColor(int r1) {
            super.setTextColor(r1);
            LinkSpanDrawable.LinksTextView r0 = this.textLoadingView;
            if (r0 == null) goto L6;
            r0.setTextColor(r1);
            return;
        }
    }

    /* loaded from: classes7.dex */
    public static class LottieLayout extends ButtonLayout {
        public RLottieImageView imageView;
        private int textColor;
        public TextView textView;

        /* renamed from: org.telegram.ui.Components.Bulletin$LottieLayout$1, reason: invalid class name */
        public class AnonymousClass1 extends LinkSpanDrawable.LinksTextView {
            final /* synthetic */ LottieLayout this$0;

            public AnonymousClass1(LottieLayout r1, Context r2) {
                this.this$0 = r1;
                super(r2);
                setDisablePaddingsOffset(true);
            }

            @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView, android.widget.TextView
            public void setText(CharSequence r3, TextView.BufferType r4) {
                super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
            }
        }

        public LottieLayout(Context r8, Theme.ResourcesProvider r9) {
            super(r8, r9);
            RLottieImageView r92 = new RLottieImageView(r8);
            this.imageView = r92;
            r92.setScaleType(ImageView.ScaleType.CENTER);
            addView(this.imageView, LayoutHelper.createFrameRelatively(56.0f, 48.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER));
            AnonymousClass1 r93 = new AnonymousClass1(this, r8);
            this.textView = r93;
            NotificationCenter.listenEmojiLoading(r93);
            this.textView.setSingleLine();
            this.textView.setTypeface(AndroidUtilities.regular());
            this.textView.setTextSize(1, 15.0f);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
            addView(this.textView, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 56.0f, 0.0f, 16.0f, 0.0f));
            this.textView.setLinkTextColor(getThemedColor(Theme.key_undo_cancelColor));
            setTextColor(getThemedColor(Theme.key_undo_infoColor));
            setBackground(getThemedColor(Theme.key_undo_background));
        }

        public LottieLayout(Context r1, Theme.ResourcesProvider r2, int r3, int r4) {
            this(r1, r2);
            setBackground(r3);
            setTextColor(r4);
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public CharSequence getAccessibilityText() {
            return this.textView.getText();
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public void onShow() {
            super.onShow();
            this.imageView.playAnimation();
        }

        public void setAnimation(int r3, int r4, int r5, String... r6) {
            this.imageView.setAnimation(r3, r4, r5);
            int r32 = r6.length;
            int r42 = 0;
        L3:
            if (r42 >= r32) goto L5;
            String r52 = r6[r42];
            this.imageView.setLayerColor(r52, this.textColor);
            r42 = r42 + 1;
            goto L3
        }

        public void setAnimation(int r2, String... r3) {
            setAnimation(r2, 32, 32, r3);
        }

        public void setAnimation(TLRPC.Document r3, int r4, int r5, String... r6) {
            this.imageView.setAutoRepeat(true);
            this.imageView.setAnimation(r3, r4, r5);
            int r32 = r6.length;
            int r42 = 0;
        L3:
            if (r42 >= r32) goto L5;
            String r52 = r6[r42];
            this.imageView.setLayerColor(r52, this.textColor);
            r42 = r42 + 1;
            goto L3
        }

        public void setDrawable(int r2) {
            this.imageView.setImageDrawable(ContextCompat.getDrawable(ApplicationLoader.applicationContext, r2));
        }

        public void setIconPaddingBottom(int r9) {
            this.imageView.setLayoutParams(LayoutHelper.createFrameRelatively(56.0f, 48 - r9, NavigationBarView.ITEM_GRAVITY_START_CENTER, 0.0f, 0.0f, 0.0f, r9));
        }

        public void setTextColor(int r1) {
            this.textColor = r1;
            this.textView.setTextColor(r1);
        }
    }

    /* loaded from: classes7.dex */
    public static class LottieLayoutWithReactions extends LottieLayout implements NotificationCenter.NotificationCenterDelegate {
        private Bulletin bulletin;
        private final BaseFragment fragment;
        private final int messagesCount;
        private SparseLongArray newMessagesByIds;
        private ReactionsContainerLayout reactionsContainerLayout;

        /* renamed from: org.telegram.ui.Components.Bulletin$LottieLayoutWithReactions$1, reason: invalid class name */
        public class AnonymousClass1 extends ReactionsContainerLayout {
            final /* synthetic */ LottieLayoutWithReactions this$0;

            public static /* synthetic */ void $r8$lambda$G72iLddfqGHzobLvLRqU64b2gA4(AnonymousClass1 r0, View r1) {
                r0.lambda$onShownCustomEmojiReactionDialog$0(r1);
            }

            public AnonymousClass1(LottieLayoutWithReactions r1, int r2, BaseFragment r3, Context r4, int r5, Theme.ResourcesProvider r6) {
                this.this$0 = r1;
                super(r2, r3, r4, r5, r6);
            }

            private /* synthetic */ void lambda$onShownCustomEmojiReactionDialog$0(View r1) {
                this.this$0.hideReactionsDialog();
                Bulletin.hideVisible();
            }

            @Override // org.telegram.ui.Components.ReactionsContainerLayout, android.view.ViewGroup, android.view.View
            public boolean dispatchTouchEvent(MotionEvent r3) {
                if (r3.getAction() != 0) goto L8;
                if (LottieLayoutWithReactions.m9066$$Nest$fgetbulletin(this.this$0) == null) goto L13;
                LottieLayoutWithReactions.m9066$$Nest$fgetbulletin(this.this$0).setCanHide(false);
            L13:
                return super.dispatchTouchEvent(r3);
            L8:
                if (r3.getAction() != 1) goto L13;
                if (LottieLayoutWithReactions.m9066$$Nest$fgetbulletin(this.this$0) == null) goto L13;
                LottieLayoutWithReactions.m9066$$Nest$fgetbulletin(this.this$0).setCanHide(true);
                goto L13
            }

            @Override // org.telegram.ui.Components.ReactionsContainerLayout
            public void onShownCustomEmojiReactionDialog() {
                Bulletin r0 = Bulletin.getVisibleBulletin();
                if (r0 == null) goto L5;
                r0.setCanHide(false);
            L5:
                LottieLayoutWithReactions.m9070$$Nest$fgetreactionsContainerLayout(this.this$0).getReactionsWindow().windowView.setOnClickListener(new Bulletin$LottieLayoutWithReactions$1$$ExternalSyntheticLambda0(this));
            }
        }

        /* renamed from: org.telegram.ui.Components.Bulletin$LottieLayoutWithReactions$2, reason: invalid class name */
        public class AnonymousClass2 implements ReactionsContainerLayout.ReactionsContainerDelegate {
            final /* synthetic */ LottieLayoutWithReactions this$0;

            /* renamed from: $r8$lambda$UC3vxyU24W1FSJxXmIS48LVH-Z0, reason: not valid java name */
            public static /* synthetic */ void m9071$r8$lambda$UC3vxyU24W1FSJxXmIS48LVHZ0(int r3, int r4, BaseFragment r5) {
                Bundle r0 = new Bundle();
                r0.putLong("user_id", UserConfig.getInstance(r3).getClientUserId());
                r0.putInt("message_id", r4);
                r5.presentFragment(new ChatActivity(r0));
            }

            public static /* synthetic */ void $r8$lambda$wwGjAG_A4LrOcTjsKwjbigawTpE(AnonymousClass2 r0, ReactionsLayoutInBubble.VisibleReaction r1, boolean r2, int r3, int r4) {
                r0.lambda$showTaggedReactionToast$1(r1, r2, r3, r4);
            }

            public AnonymousClass2(LottieLayoutWithReactions r1) {
                this.this$0 = r1;
            }

            private /* synthetic */ void lambda$showTaggedReactionToast$1(ReactionsLayoutInBubble.VisibleReaction r6, boolean r7, int r8, int r9) {
                BaseFragment r0 = LaunchActivity.getLastFragment();
                long r1 = r6.documentId;
                if (r1 != 0) goto L8;
                TLRPC.TL_availableReaction r62 = MediaDataController.getInstance(UserConfig.selectedAccount).getReactionsMap().get(r6.emojicon);
                if (r62 == null) goto L19;
                TLRPC.Document r63 = r62.activate_animation;
            L9:
                if (r63 == null) goto L18;
                if (r0 == null) goto L20;
                BulletinFactory r12 = BulletinFactory.of(r0);
                int r5 = LottieLayoutWithReactions.m9068$$Nest$fgetmessagesCount(this.this$0);
                if (r7 == false) goto L15;
                Bulletin$LottieLayoutWithReactions$2$$ExternalSyntheticLambda1 r72 = new Bulletin$LottieLayoutWithReactions$2$$ExternalSyntheticLambda1(r8, r9, r0);
            L16:
                r12.createMessagesTaggedBulletin(r5, r63, r72).show(true);
                return;
            L15:
                r72 = null;
                goto L16
            L20:
                return;
            L18:
                return;
            L19:
                return;
            L8:
                r63 = AnimatedEmojiDrawable.findDocument(UserConfig.selectedAccount, r1);
                goto L9
            }

            private void showTaggedReactionToast(ReactionsLayoutInBubble.VisibleReaction r7, int r8, int r9, boolean r10) {
                AndroidUtilities.runOnUIThread(new Bulletin$LottieLayoutWithReactions$2$$ExternalSyntheticLambda0(this, r7, r10, r8, r9), 300);
            }

            @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
            public void onReactionClicked(View r14, ReactionsLayoutInBubble.VisibleReaction r15, boolean r16, boolean r17) {
                if (LottieLayoutWithReactions.m9069$$Nest$fgetnewMessagesByIds(this.this$0) != null) goto L5;
                return;
            L5:
                long r0 = UserConfig.getInstance(LottieLayoutWithReactions.m9067$$Nest$fgetfragment(this.this$0).getCurrentAccount()).getClientUserId();
                if ((LottieLayoutWithReactions.m9067$$Nest$fgetfragment(this.this$0) instanceof ChatActivity) == true) goto L8;
            L10:
                boolean r10 = false;
            L11:
                int r02 = 0;
                int r11 = 0;
            L12:
                int r1 = LottieLayoutWithReactions.m9069$$Nest$fgetnewMessagesByIds(this.this$0).size();
                LottieLayoutWithReactions r2 = this.this$0;
                if (r11 >= r1) goto L15;
                int r03 = LottieLayoutWithReactions.m9069$$Nest$fgetnewMessagesByIds(r2).keyAt(r11);
                TLRPC.Message r12 = new TLRPC.Message();
                r12.dialog_id = LottieLayoutWithReactions.m9067$$Nest$fgetfragment(this.this$0).getUserConfig().getClientUserId();
                r12.id = r03;
                MessageObject r13 = new MessageObject(LottieLayoutWithReactions.m9067$$Nest$fgetfragment(this.this$0).getCurrentAccount(), r12, false, false);
                ArrayList<ReactionsLayoutInBubble.VisibleReaction> r22 = new ArrayList();
                r22.add(r15);
                LottieLayoutWithReactions.m9067$$Nest$fgetfragment(this.this$0).getSendMessagesHelper().sendReaction(r13, r22, r15, false, false, LottieLayoutWithReactions.m9067$$Nest$fgetfragment(this.this$0), null);
                r02 = r12.id;
                r11 = r11 + 1;
                goto L12
            L15:
                r2.hideReactionsDialog();
                Bulletin.hideVisible();
                showTaggedReactionToast(r15, LottieLayoutWithReactions.m9067$$Nest$fgetfragment(this.this$0).getCurrentAccount(), r02, !r10);
                return;
            L8:
                if (((ChatActivity) LottieLayoutWithReactions.m9067$$Nest$fgetfragment(this.this$0)).getDialogId() != r0) goto L10;
                r10 = true;
                goto L11
            }
        }

        /* renamed from: -$$Nest$fgetbulletin, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Bulletin m9066$$Nest$fgetbulletin(LottieLayoutWithReactions r0) {
            return r0.bulletin;
        }

        /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BaseFragment m9067$$Nest$fgetfragment(LottieLayoutWithReactions r0) {
            return r0.fragment;
        }

        /* renamed from: -$$Nest$fgetmessagesCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m9068$$Nest$fgetmessagesCount(LottieLayoutWithReactions r0) {
            return r0.messagesCount;
        }

        /* renamed from: -$$Nest$fgetnewMessagesByIds, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseLongArray m9069$$Nest$fgetnewMessagesByIds(LottieLayoutWithReactions r0) {
            return r0.newMessagesByIds;
        }

        /* renamed from: -$$Nest$fgetreactionsContainerLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ReactionsContainerLayout m9070$$Nest$fgetreactionsContainerLayout(LottieLayoutWithReactions r0) {
            return r0.reactionsContainerLayout;
        }

        public LottieLayoutWithReactions(BaseFragment r3, int r4) {
            super(r3.getContext(), r3.getResourceProvider());
            this.fragment = r3;
            this.messagesCount = r4;
            init();
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 != NotificationCenter.savedMessagesForwarded) goto L6;
            this.newMessagesByIds = (SparseLongArray) r3[0];
            return;
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public int getMeasuredBackgroundHeight() {
            return this.textView.getMeasuredHeight() + AndroidUtilities.dp(30.0f);
        }

        public void hideReactionsDialog() {
            if (this.reactionsContainerLayout.getReactionsWindow() == null) goto L8;
            this.reactionsContainerLayout.dismissWindow();
            if (this.reactionsContainerLayout.getReactionsWindow().containerView == null) goto L9;
            this.reactionsContainerLayout.getReactionsWindow().containerView.animate().alpha(0.0f).setDuration(180).start();
            return;
        L9:
            return;
        }

        public void init() {
            this.textView.setLayoutParams(LayoutHelper.createFrameRelatively(-2.0f, -2.0f, 8388659, 56.0f, 6.0f, 8.0f, 0.0f));
            this.imageView.setLayoutParams(LayoutHelper.createFrameRelatively(56.0f, 48.0f, 8388659));
            AnonymousClass1 r2 = new AnonymousClass1(this, 3, this.fragment, getContext(), this.fragment.getCurrentAccount(), this.fragment.getResourceProvider());
            this.reactionsContainerLayout = r2;
            r2.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(0.0f));
            this.reactionsContainerLayout.setDelegate(new AnonymousClass2(this));
            this.reactionsContainerLayout.setTop(true);
            this.reactionsContainerLayout.setClipChildren(false);
            this.reactionsContainerLayout.setClipToPadding(false);
            this.reactionsContainerLayout.setVisibility(0);
            this.reactionsContainerLayout.setBubbleOffset(-AndroidUtilities.dp(80.0f));
            this.reactionsContainerLayout.setHint(LocaleController.getString(R.string.SavedTagReactionsHint));
            addView(this.reactionsContainerLayout, LayoutHelper.createFrame(-2, 92.5f, 1, 0.0f, 36.0f, 0.0f, 0.0f));
            this.reactionsContainerLayout.setMessage(null, null, true);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            NotificationCenter.getInstance(UserConfig.selectedAccount).addObserver(this, NotificationCenter.savedMessagesForwarded);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getInstance(UserConfig.selectedAccount).removeObserver(this, NotificationCenter.savedMessagesForwarded);
        }

        public void setBulletin(Bulletin r1) {
            this.bulletin = r1;
        }
    }

    @SuppressLint({"ViewConstructor"})
    /* loaded from: classes7.dex */
    public static class MultiLineLayout extends ButtonLayout {
        public final BackupImageView imageView;
        public final TextView textView;

        public MultiLineLayout(Context r9, Theme.ResourcesProvider r10) {
            super(r9, r10);
            BackupImageView r92 = new BackupImageView(getContext());
            this.imageView = r92;
            TextView r102 = new TextView(getContext());
            this.textView = r102;
            addView(r92, LayoutHelper.createFrameRelatively(30.0f, 30.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 12.0f, 8.0f, 12.0f, 8.0f));
            r102.setGravity(PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES);
            r102.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
            r102.setTextColor(getThemedColor(Theme.key_undo_infoColor));
            r102.setTextSize(1, 15.0f);
            r102.setTypeface(Typeface.SANS_SERIF);
            addView(r102, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 56.0f, 0.0f, 16.0f, 0.0f));
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public CharSequence getAccessibilityText() {
            return this.textView.getText();
        }
    }

    /* loaded from: classes7.dex */
    public static abstract class ParentLayout extends FrameLayout {
        private final GestureDetector gestureDetector;
        private boolean hideAnimationRunning;
        private final Layout layout;
        private boolean needLeftAlphaAnimation;
        private boolean needRightAlphaAnimation;
        private boolean pressed;
        private long pressedTime;
        private final Rect rect;
        private boolean scrolling;
        private float translationX;
        private float tx;
        private float ty;
        private boolean wasCanHide;

        /* renamed from: org.telegram.ui.Components.Bulletin$ParentLayout$1, reason: invalid class name */
        public class AnonymousClass1 extends GestureDetector.SimpleOnGestureListener {
            final /* synthetic */ ParentLayout this$0;
            final /* synthetic */ Layout val$layout;

            public static /* synthetic */ void $r8$lambda$MDzNnFlgu6vHtTknmpc3g9JeEj8(AnonymousClass1 r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
                r0.lambda$onFling$2(r1, r2, r3, r4);
            }

            /* renamed from: $r8$lambda$V6A0CjIhWmV-fAcEIQPQ3X99qLc, reason: not valid java name */
            public static /* synthetic */ void m9086$r8$lambda$V6A0CjIhWmVfAcEIQPQ3X99qLc(DynamicAnimation r0, float r1, float r2) {
                if (r1 > 0.0f) goto L6;
                r0.cancel();
                return;
            }

            /* renamed from: $r8$lambda$d-AkVcT2AvP9R3eIciaX3FoyM5o, reason: not valid java name */
            public static /* synthetic */ void m9087$r8$lambda$dAkVcT2AvP9R3eIciaX3FoyM5o(AnonymousClass1 r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
                r0.lambda$onFling$0(r1, r2, r3, r4);
            }

            public static /* synthetic */ void $r8$lambda$xoZIPdijjfJwdC9lJa1tRQDP12M(Layout r0, DynamicAnimation r1, float r2, float r3) {
                if (Math.abs(r2) <= r0.getWidth()) goto L6;
                r1.cancel();
                return;
            }

            public AnonymousClass1(ParentLayout r1, Layout r2) {
                this.this$0 = r1;
                this.val$layout = r2;
            }

            private /* synthetic */ void lambda$onFling$0(DynamicAnimation r1, boolean r2, float r3, float r4) {
                this.this$0.onHide();
            }

            private /* synthetic */ void lambda$onFling$2(DynamicAnimation r1, boolean r2, float r3, float r4) {
                this.this$0.onHide();
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent r4) {
                if (ParentLayout.m9072$$Nest$fgethideAnimationRunning(this.this$0) == true) goto L6;
                ParentLayout.m9080$$Nest$fputneedLeftAlphaAnimation(this.this$0, Layout.m9061$$Nest$misNeedSwipeAlphaAnimation(this.val$layout, true));
                ParentLayout.m9081$$Nest$fputneedRightAlphaAnimation(this.this$0, Layout.m9061$$Nest$misNeedSwipeAlphaAnimation(this.val$layout, false));
                return true;
            L6:
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent r6, MotionEvent r7, float r8, float r9) {
                boolean r72 = false;
                if (Math.abs(r8) > 2000.0f) goto L5;
                return false;
            L5:
                if (r8 >= 0.0f) goto L9;
                if (ParentLayout.m9073$$Nest$fgetneedLeftAlphaAnimation(this.this$0) == false) goto L9;
            L12:
                r72 = true;
            L13:
                SpringAnimation r92 = new SpringAnimation(this.val$layout, DynamicAnimation.TRANSLATION_X, (Math.signum(r8) * this.val$layout.getWidth()) * 2.0f);
                if (r72 == true) goto L16;
                r92.addEndListener(new Bulletin$ParentLayout$1$$ExternalSyntheticLambda0(this));
                r92.addUpdateListener(new Bulletin$ParentLayout$1$$ExternalSyntheticLambda1(this.val$layout));
            L16:
                r92.getSpring().setDampingRatio(1.0f);
                r92.getSpring().setStiffness(100.0f);
                r92.setStartVelocity(r8);
                r92.start();
                if (r72 == false) goto L19;
                SpringAnimation r73 = new SpringAnimation(this.val$layout, DynamicAnimation.ALPHA, 0.0f);
                r73.addEndListener(new Bulletin$ParentLayout$1$$ExternalSyntheticLambda2(this));
                r73.addUpdateListener(new Bulletin$ParentLayout$1$$ExternalSyntheticLambda3());
                r92.getSpring().setDampingRatio(1.0f);
                r92.getSpring().setStiffness(10.0f);
                r92.setStartVelocity(r8);
                r73.start();
            L19:
                ParentLayout.m9079$$Nest$fputhideAnimationRunning(this.this$0, true);
                return true;
            L9:
                if (r8 <= 0.0f) goto L13;
                if (ParentLayout.m9074$$Nest$fgetneedRightAlphaAnimation(this.this$0) == false) goto L13;
                goto L12
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent r3, MotionEvent r4, float r5, float r6) {
                ParentLayout r32 = this.this$0;
                ParentLayout.m9084$$Nest$fputtx(r32, ParentLayout.m9076$$Nest$fgettx(r32) + r5);
                ParentLayout r33 = this.this$0;
                ParentLayout.m9085$$Nest$fputty(r33, ParentLayout.m9077$$Nest$fgetty(r33) + r6);
                if (Utilities.dist(0.0f, 0.0f, ParentLayout.m9076$$Nest$fgettx(this.this$0), ParentLayout.m9077$$Nest$fgetty(this.this$0)) <= AndroidUtilities.touchSlop) goto L6;
                ParentLayout.m9082$$Nest$fputscrolling(this.this$0, true);
            L6:
                if (ParentLayout.m9078$$Nest$fgetwasCanHide(this.this$0) == true) goto L9;
                return false;
            L9:
                Layout r34 = this.val$layout;
                ParentLayout r0 = this.this$0;
                float r1 = ParentLayout.m9075$$Nest$fgettranslationX(r0) - r5;
                ParentLayout.m9083$$Nest$fputtranslationX(r0, r1);
                r34.setTranslationX(r1);
                if (ParentLayout.m9075$$Nest$fgettranslationX(this.this$0) != 0.0f) goto L12;
            L19:
                this.val$layout.setAlpha(1.0f - (Math.abs(ParentLayout.m9075$$Nest$fgettranslationX(this.this$0)) / this.val$layout.getWidth()));
            L20:
                return true;
            L12:
                if (ParentLayout.m9075$$Nest$fgettranslationX(this.this$0) >= 0.0f) goto L16;
                if (ParentLayout.m9073$$Nest$fgetneedLeftAlphaAnimation(this.this$0) == true) goto L19;
            L16:
                if (ParentLayout.m9075$$Nest$fgettranslationX(this.this$0) <= 0.0f) goto L20;
                if (ParentLayout.m9074$$Nest$fgetneedRightAlphaAnimation(this.this$0) == false) goto L20;
                goto L19
            }
        }

        public static /* synthetic */ void $r8$lambda$77bO2FqXZUX5_EIYe07uDy1cdm8(ParentLayout r0, float r1) {
            r0.lambda$onTouchEvent$0(r1);
        }

        /* renamed from: -$$Nest$fgethideAnimationRunning, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m9072$$Nest$fgethideAnimationRunning(ParentLayout r0) {
            return r0.hideAnimationRunning;
        }

        /* renamed from: -$$Nest$fgetneedLeftAlphaAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m9073$$Nest$fgetneedLeftAlphaAnimation(ParentLayout r0) {
            return r0.needLeftAlphaAnimation;
        }

        /* renamed from: -$$Nest$fgetneedRightAlphaAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m9074$$Nest$fgetneedRightAlphaAnimation(ParentLayout r0) {
            return r0.needRightAlphaAnimation;
        }

        /* renamed from: -$$Nest$fgettranslationX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m9075$$Nest$fgettranslationX(ParentLayout r0) {
            return r0.translationX;
        }

        /* renamed from: -$$Nest$fgettx, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m9076$$Nest$fgettx(ParentLayout r0) {
            return r0.tx;
        }

        /* renamed from: -$$Nest$fgetty, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m9077$$Nest$fgetty(ParentLayout r0) {
            return r0.ty;
        }

        /* renamed from: -$$Nest$fgetwasCanHide, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m9078$$Nest$fgetwasCanHide(ParentLayout r0) {
            return r0.wasCanHide;
        }

        /* renamed from: -$$Nest$fputhideAnimationRunning, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9079$$Nest$fputhideAnimationRunning(ParentLayout r0, boolean r1) {
            r0.hideAnimationRunning = r1;
        }

        /* renamed from: -$$Nest$fputneedLeftAlphaAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9080$$Nest$fputneedLeftAlphaAnimation(ParentLayout r0, boolean r1) {
            r0.needLeftAlphaAnimation = r1;
        }

        /* renamed from: -$$Nest$fputneedRightAlphaAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9081$$Nest$fputneedRightAlphaAnimation(ParentLayout r0, boolean r1) {
            r0.needRightAlphaAnimation = r1;
        }

        /* renamed from: -$$Nest$fputscrolling, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9082$$Nest$fputscrolling(ParentLayout r0, boolean r1) {
            r0.scrolling = r1;
        }

        /* renamed from: -$$Nest$fputtranslationX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9083$$Nest$fputtranslationX(ParentLayout r0, float r1) {
            r0.translationX = r1;
        }

        /* renamed from: -$$Nest$fputtx, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9084$$Nest$fputtx(ParentLayout r0, float r1) {
            r0.tx = r1;
        }

        /* renamed from: -$$Nest$fputty, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9085$$Nest$fputty(ParentLayout r0, float r1) {
            r0.ty = r1;
        }

        public ParentLayout(Layout r4) {
            super(r4.getContext());
            this.rect = new Rect();
            this.layout = r4;
            GestureDetector r0 = new GestureDetector(r4.getContext(), new AnonymousClass1(this, r4));
            this.gestureDetector = r0;
            r0.setIsLongpressEnabled(false);
            addView(r4);
        }

        private boolean inLayoutHitRect(float r3, float r4) {
            this.layout.getHitRect(this.rect);
            return this.rect.contains((int) r3, (int) r4);
        }

        private /* synthetic */ void lambda$onTouchEvent$0(float r2) {
            if (this.layout.getTranslationX() != r2) goto L6;
            onHide();
            return;
        }

        public abstract void onHide();

        public abstract void onPressedStateChanged(boolean r1);

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r9) {
            boolean r1 = false;
            if (this.pressed == false) goto L5;
        L8:
            this.gestureDetector.onTouchEvent(r9);
            int r92 = r9.getActionMasked();
            float r0 = 0.0f;
            if (r92 == 0) goto L11;
            if (r92 == 1) goto L26;
            if (r92 == 3) goto L26;
        L58:
            return true;
        L26:
            if (this.pressed == false) goto L58;
            if (this.hideAnimationRunning == false) goto L30;
        L47:
            if (r92 == 1) goto L49;
        L55:
            this.pressed = false;
            onPressedStateChanged(false);
            Layout r8 = this.layout;
            if (r8.onClickListener == null) goto L58;
            r8.setPressed(false);
            goto L58
        L49:
            if (this.layout.isPressed() == false) goto L55;
            Layout r93 = this.layout;
            View.OnClickListener r02 = r93.onClickListener;
            if (r02 == null) goto L55;
            if (this.scrolling == true) goto L55;
            r02.onClick(r93);
            goto L55
        L30:
            if (Math.abs(this.translationX) <= (this.layout.getWidth() / 3.0f)) goto L46;
            float r94 = Math.signum(this.translationX) * this.layout.getWidth();
            float r3 = this.translationX;
            if (r3 >= 0.0f) goto L36;
            if (this.needLeftAlphaAnimation == false) goto L36;
        L39:
            boolean r32 = true;
        L41:
            ViewPropertyAnimator r7 = this.layout.animate().translationX(r94);
            if (r32 == true) goto L45;
            r0 = 1.0f;
        L45:
            r7.alpha(r0).setDuration(200).setInterpolator(AndroidUtilities.accelerateInterpolator).withEndAction(new Bulletin$ParentLayout$$ExternalSyntheticLambda0(this, r94)).start();
        L36:
            if (r3 > 0.0f) goto L38;
        L40:
            r32 = false;
            goto L41
        L38:
            if (this.needRightAlphaAnimation == false) goto L40;
        L46:
            this.layout.animate().translationX(0.0f).alpha(1.0f).setDuration(200).start();
            goto L47
        L11:
            if (this.pressed == true) goto L58;
            if (this.hideAnimationRunning == true) goto L58;
            this.layout.animate().cancel();
            this.ty = 0.0f;
            this.tx = 0.0f;
            this.scrolling = false;
            this.translationX = this.layout.getTranslationX();
            this.pressedTime = System.currentTimeMillis();
            Bulletin r95 = this.layout.bulletin;
            if (r95 != null) goto L17;
        L18:
            r1 = true;
        L19:
            this.wasCanHide = r1;
            this.pressed = true;
            onPressedStateChanged(true);
            Layout r82 = this.layout;
            if (r82.onClickListener == null) goto L58;
            r82.setPressed(true);
            goto L58
        L17:
            if (Bulletin.m9046$$Nest$fgetcanHide(r95) == false) goto L19;
        L5:
            if (inLayoutHitRect(r9.getX(), r9.getY()) == true) goto L8;
            return false;
        }
    }

    /* loaded from: classes7.dex */
    public static class ProgressLayout extends ButtonLayout {
        public BackupImageView imageView;
        private boolean inprogress;
        public float progress;
        public FrameLayout progressView;
        public AnimatedTextView textView;

        /* renamed from: org.telegram.ui.Components.Bulletin$ProgressLayout$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            private final AnimatedFloat animatedDone;
            private final AnimatedFloat animatedProgress;
            private final RectF rect;
            private final long start;
            private final Paint strokePaint;
            final /* synthetic */ ProgressLayout this$0;

            public AnonymousClass1(ProgressLayout r3, Context r4) {
                this.this$0 = r3;
                super(r4);
                CubicBezierInterpolator r42 = CubicBezierInterpolator.EASE_OUT_QUINT;
                this.animatedProgress = new AnimatedFloat(this, 320, r42);
                this.animatedDone = new AnimatedFloat(this, 320, r42);
                Paint r32 = new Paint(1);
                this.strokePaint = r32;
                r32.setStyle(Paint.Style.STROKE);
                r32.setColor(268435455);
                r32.setStrokeWidth(AndroidUtilities.dp(1.66f));
                r32.setStrokeCap(Paint.Cap.ROUND);
                r32.setStrokeJoin(Paint.Join.ROUND);
                this.rect = new RectF();
                this.start = System.currentTimeMillis();
            }

            @Override // android.view.View
            public void onDraw(Canvas r13) {
                float r0 = this.animatedProgress.set(this.this$0.progress);
                AnimatedFloat r1 = this.animatedDone;
                int r4 = 0;
                if (this.this$0.progress < 1.0f) goto L5;
                boolean r2 = true;
            L6:
                float r12 = r1.set(r2);
                float r22 = getWidth() / 2.0f;
                float r6 = getHeight() / 2.0f;
                this.rect.set(r22 - AndroidUtilities.dpf2(13.0f), r6 - AndroidUtilities.dpf2(13.0f), r22 + AndroidUtilities.dpf2(13.0f), r6 + AndroidUtilities.dpf2(13.0f));
                float r23 = ((System.currentTimeMillis() - this.start) * 0.45f) % 5400.0f;
                float r5 = Math.max(0.0f, ((1520.0f * r23) / 5400.0f) - 20.0f);
            L8:
                if (r4 >= 4) goto L10;
                FastOutSlowInInterpolator r62 = CircularProgressDrawable.interpolator;
                r62.getInterpolation((r23 - (r4 * 1350)) / 667.0f);
                r5 = r5 + (r62.getInterpolation((r23 - (r7 + 667)) / 667.0f) * 250.0f);
                r4 = r4 + 1;
                goto L8
            L10:
                this.strokePaint.setColor(Theme.multAlpha(-1, (1.0f - r12) * 1.0f));
                r13.drawArc(this.rect, (-90.0f) - r5, Math.max(0.02f, r0) * (-360.0f), false, this.strokePaint);
                if (r0 < 1.0f) goto L13;
            L15:
                super.onDraw(r13);
                return;
            L13:
                if (r12 >= 1.0f) goto L15;
                invalidate();
                goto L15
            L5:
                r2 = false;
                goto L6
            }
        }

        public ProgressLayout(Context r9, Theme.ResourcesProvider r10) {
            super(r9, r10);
            AnonymousClass1 r102 = new AnonymousClass1(this, r9);
            this.progressView = r102;
            r102.setWillNotDraw(false);
            addView(this.progressView, LayoutHelper.createFrameRelatively(32.0f, 32.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 12.0f, 8.0f, 12.0f, 8.0f));
            BackupImageView r103 = new BackupImageView(r9);
            this.imageView = r103;
            r103.setRoundRadius(AndroidUtilities.dp(14.0f));
            this.progressView.addView(this.imageView, LayoutHelper.createFrame(28, 28, 17));
            AnimatedTextView r104 = new AnimatedTextView(r9);
            this.textView = r104;
            r104.setTextSize(AndroidUtilities.dp(15.0f));
            this.textView.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
            addView(this.textView, LayoutHelper.createFrameRelatively(-2.0f, 18.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 56.0f, 0.0f, 8.0f, 0.0f));
            setTextColor(getThemedColor(Theme.key_undo_infoColor));
            setBackground(getThemedColor(Theme.key_undo_background));
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public CharSequence getAccessibilityText() {
            return this.textView.getText();
        }

        public void setProgress(float r7) {
            boolean r0 = this.inprogress;
            float r1 = 1.0f;
            boolean r3 = false;
            if (r7 >= 1.0f) goto L5;
            boolean r5 = true;
        L6:
            if (r0 == r5) goto L17;
            if (r7 >= 1.0f) goto L9;
            r3 = true;
        L9:
            this.inprogress = r3;
            ViewPropertyAnimator r02 = this.imageView.animate();
            if (this.inprogress == false) goto L12;
            float r2 = 0.78f;
        L13:
            ViewPropertyAnimator r03 = r02.scaleX(r2);
            if (this.inprogress == false) goto L16;
            r1 = 0.78f;
        L16:
            r03.scaleY(r1).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            goto L17
        L12:
            r2 = 1.0f;
        L17:
            this.progress = r7;
            this.progressView.invalidate();
            return;
        L5:
            r5 = false;
            goto L6
        }

        public void setTextColor(int r1) {
            this.textView.setTextColor(r1);
        }
    }

    /* loaded from: classes7.dex */
    public static class SimpleLayout extends ButtonLayout {
        public final ImageView imageView;
        public final LinkSpanDrawable.LinksTextView textView;

        public SimpleLayout(Context r11, Theme.ResourcesProvider r12) {
            super(r11, r12);
            int r122 = getThemedColor(Theme.key_undo_infoColor);
            ImageView r0 = new ImageView(r11);
            this.imageView = r0;
            r0.setColorFilter(new PorterDuffColorFilter(r122, PorterDuff.Mode.MULTIPLY));
            addView(r0, LayoutHelper.createFrameRelatively(24.0f, 24.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 16.0f, 12.0f, 16.0f, 12.0f));
            LinkSpanDrawable.LinksTextView r02 = new LinkSpanDrawable.LinksTextView(r11);
            this.textView = r02;
            r02.setDisablePaddingsOffsetY(true);
            r02.setSingleLine();
            r02.setTextColor(r122);
            r02.setTypeface(AndroidUtilities.regular());
            r02.setTextSize(1, 15.0f);
            addView(r02, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 56.0f, 0.0f, 16.0f, 0.0f));
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public CharSequence getAccessibilityText() {
            return this.textView.getText();
        }
    }

    /* loaded from: classes7.dex */
    public static class TimerView extends View {
        private long lastUpdateTime;
        private int prevSeconds;
        private final Paint progressPaint;
        RectF rect;
        private TextPaint textPaint;
        private int textWidth;
        int textWidthOut;
        StaticLayout timeLayout;
        StaticLayout timeLayoutOut;
        public long timeLeft;
        private String timeLeftString;
        float timeReplaceProgress;

        public TimerView(Context r3, Theme.ResourcesProvider r4) {
            super(r3);
            this.timeReplaceProgress = 1.0f;
            this.rect = new RectF();
            TextPaint r32 = new TextPaint(1);
            this.textPaint = r32;
            r32.setTextSize(AndroidUtilities.dp(12.0f));
            this.textPaint.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
            Paint r33 = new Paint(1);
            this.progressPaint = r33;
            r33.setStyle(Paint.Style.STROKE);
            r33.setStrokeWidth(AndroidUtilities.dp(2.0f));
            r33.setStrokeCap(Paint.Cap.ROUND);
            setColor(Theme.getColor(Theme.key_undo_infoColor, r4));
        }

        @Override // android.view.View
        public void onDraw(Canvas r14) {
            super.onDraw(r14);
            if (this.timeLeft <= 0) goto L5;
            int r0 = (int) Math.ceil(r0 / 1000.0f);
        L6:
            this.rect.set(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), getMeasuredWidth() - AndroidUtilities.dp(1.0f), getMeasuredHeight() - AndroidUtilities.dp(1.0f));
            if (this.prevSeconds == r0) goto L12;
            this.prevSeconds = r0;
            this.timeLeftString = String.valueOf(Math.max(0, r0));
            StaticLayout r1 = this.timeLayout;
            if (r1 == null) goto L11;
            this.timeLayoutOut = r1;
            this.timeReplaceProgress = 0.0f;
            this.textWidthOut = this.textWidth;
        L11:
            this.textWidth = (int) Math.ceil(this.textPaint.measureText(r0));
            this.timeLayout = new StaticLayout(this.timeLeftString, this.textPaint, Integer.MAX_VALUE, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        L12:
            float r02 = this.timeReplaceProgress;
            if (r02 >= 1.0f) goto L18;
            float r03 = r02 + 0.10666667f;
            this.timeReplaceProgress = r03;
            if (r03 <= 1.0f) goto L17;
            this.timeReplaceProgress = 1.0f;
            goto L18
        L17:
            invalidate();
        L18:
            int r04 = this.textPaint.getAlpha();
            if (this.timeLayoutOut == null) goto L24;
            float r12 = this.timeReplaceProgress;
            if (r12 >= 1.0f) goto L24;
            this.textPaint.setAlpha((int) (r04 * (1.0f - r12)));
            r14.save();
            r14.translate(this.rect.centerX() - (this.textWidthOut / 2.0f), ((this.rect.centerY() - (this.timeLayoutOut.getHeight() / 2.0f)) + (AndroidUtilities.dp(10.0f) * this.timeReplaceProgress)) - AndroidUtilities.dp(0.5f));
            this.timeLayoutOut.draw(r14);
            this.textPaint.setAlpha(r04);
            r14.restore();
        L24:
            if (this.timeLayout == null) goto L32;
            float r13 = this.timeReplaceProgress;
            if (r13 == 1.0f) goto L28;
            this.textPaint.setAlpha((int) (r04 * r13));
        L28:
            r14.save();
            r14.translate(this.rect.centerX() - (this.textWidth / 2.0f), ((this.rect.centerY() - (this.timeLayout.getHeight() / 2.0f)) - (AndroidUtilities.dp(10.0f) * (1.0f - this.timeReplaceProgress))) - AndroidUtilities.dp(0.5f));
            this.timeLayout.draw(r14);
            if (this.timeReplaceProgress == 1.0f) goto L31;
            this.textPaint.setAlpha(r04);
        L31:
            r14.restore();
        L32:
            r14.drawArc(this.rect, -90.0f, (Math.max(0, this.timeLeft) / 5000.0f) * (-360.0f), false, this.progressPaint);
            if (this.lastUpdateTime == 0) goto L35;
            long r05 = System.currentTimeMillis();
            this.timeLeft -= r05 - this.lastUpdateTime;
            this.lastUpdateTime = r05;
        L36:
            invalidate();
            return;
        L35:
            this.lastUpdateTime = System.currentTimeMillis();
            goto L36
        L5:
            r0 = 0;
            goto L6
        }

        public void setColor(int r2) {
            this.textPaint.setColor(r2);
            this.progressPaint.setColor(r2);
        }
    }

    /* loaded from: classes7.dex */
    public static class TwoLineAnimatedLottieLayout extends ButtonLayout {
        public final RLottieImageView imageView;
        private final LinearLayout linearLayout;
        public final AnimatedTextView subtitleTextView;
        private final int textColor;
        public final AnimatedTextView titleTextView;

        public TwoLineAnimatedLottieLayout(Context r10, Theme.ResourcesProvider r11) {
            super(r10, r11);
            int r112 = Theme.key_undo_infoColor;
            this.textColor = getThemedColor(r112);
            setBackground(getThemedColor(Theme.key_undo_background));
            RLottieImageView r0 = new RLottieImageView(r10);
            this.imageView = r0;
            r0.setScaleType(ImageView.ScaleType.CENTER);
            addView(r0, LayoutHelper.createFrameRelatively(56.0f, 48.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER));
            int r113 = getThemedColor(r112);
            getThemedColor(Theme.key_undo_cancelColor);
            LinearLayout r02 = new LinearLayout(r10);
            this.linearLayout = r02;
            r02.setOrientation(1);
            addView(r02, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 52.0f, 8.0f, 8.0f, 8.0f));
            AnimatedTextView r2 = new AnimatedTextView(r10, true, true, true);
            this.titleTextView = r2;
            r2.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            r2.setTextColor(r113);
            r2.setTextSize(AndroidUtilities.dp(14.0f));
            r2.setTypeface(AndroidUtilities.bold());
            r2.setEllipsizeByGradient(true);
            r02.addView(r2, LayoutHelper.createLinear(-1, 20));
            AnimatedTextView r22 = new AnimatedTextView(r10, true, true, true);
            this.subtitleTextView = r22;
            r22.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            r22.setTextColor(r113);
            r22.setTextSize(AndroidUtilities.dp(13.0f));
            r22.setEllipsizeByGradient(true);
            r02.addView(r22, LayoutHelper.createLinear(-1, 18));
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public CharSequence getAccessibilityText() {
            return this.titleTextView.getText() + ".\n" + this.subtitleTextView.getText();
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public void onShow() {
            super.onShow();
            this.imageView.playAnimation();
        }

        public void setAnimation(int r3, int r4, int r5, String... r6) {
            this.imageView.setAnimation(r3, r4, r5);
            int r32 = r6.length;
            int r42 = 0;
        L3:
            if (r42 >= r32) goto L5;
            String r52 = r6[r42];
            this.imageView.setLayerColor(r52, this.textColor);
            r42 = r42 + 1;
            goto L3
        }

        public void setAnimation(int r2, String... r3) {
            setAnimation(r2, 32, 32, r3);
        }
    }

    /* loaded from: classes7.dex */
    public static class TwoLineBackupLayout extends ButtonLayout {
        public final BackupImageView imageView;
        private final LinearLayout linearLayout;
        public final LinkSpanDrawable.LinksTextView subtitleTextView;
        private final int textColor;
        public final LinkSpanDrawable.LinksTextView titleTextView;

        public TwoLineBackupLayout(Context r11, Theme.ResourcesProvider r12) {
            super(r11, r12);
            int r122 = Theme.key_undo_infoColor;
            this.textColor = getThemedColor(r122);
            setBackground(getThemedColor(Theme.key_undo_background));
            BackupImageView r0 = new BackupImageView(r11);
            this.imageView = r0;
            addView(r0, LayoutHelper.createFrameRelatively(32.0f, 32.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 12.0f, 0.0f, 12.0f, 0.0f));
            int r123 = getThemedColor(r122);
            int r02 = getThemedColor(Theme.key_undo_cancelColor);
            LinearLayout r1 = new LinearLayout(r11);
            this.linearLayout = r1;
            r1.setOrientation(1);
            addView(r1, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 52.0f, 8.0f, 8.0f, 8.0f));
            LinkSpanDrawable.LinksTextView r3 = new LinkSpanDrawable.LinksTextView(r11);
            this.titleTextView = r3;
            r3.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            r3.setTextColor(r123);
            r3.setTextSize(1, 14.0f);
            r3.setTypeface(AndroidUtilities.bold());
            r1.addView(r3);
            LinkSpanDrawable.LinksTextView r32 = new LinkSpanDrawable.LinksTextView(r11);
            this.subtitleTextView = r32;
            r32.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            r32.setTextColor(r123);
            r32.setLinkTextColor(r02);
            r32.setTypeface(Typeface.SANS_SERIF);
            r32.setTextSize(1, 13.0f);
            r1.addView(r32);
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public CharSequence getAccessibilityText() {
            return this.titleTextView.getText() + ".\n" + this.subtitleTextView.getText();
        }
    }

    @SuppressLint({"ViewConstructor"})
    /* loaded from: classes7.dex */
    public static class TwoLineLayout extends ButtonLayout {
        public final BackupImageView imageView;
        private final LinearLayout linearLayout;
        public final TextView subtitleTextView;
        public final TextView titleTextView;

        public TwoLineLayout(Context r10, Theme.ResourcesProvider r11) {
            super(r10, r11);
            int r112 = getThemedColor(Theme.key_undo_infoColor);
            BackupImageView r0 = new BackupImageView(r10);
            this.imageView = r0;
            addView(r0, LayoutHelper.createFrameRelatively(29.0f, 29.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 12.0f, 12.0f, 12.0f, 12.0f));
            LinearLayout r02 = new LinearLayout(r10);
            this.linearLayout = r02;
            r02.setOrientation(1);
            addView(r02, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 54.0f, 8.0f, 12.0f, 8.0f));
            TextView r2 = new TextView(r10);
            this.titleTextView = r2;
            r2.setSingleLine();
            r2.setTextColor(r112);
            r2.setTextSize(1, 14.0f);
            r2.setTypeface(AndroidUtilities.bold());
            r02.addView(r2);
            TextView r22 = new TextView(r10);
            this.subtitleTextView = r22;
            r22.setMaxLines(2);
            r22.setTextColor(r112);
            r22.setLinkTextColor(getThemedColor(Theme.key_undo_cancelColor));
            r22.setMovementMethod(new LinkMovementMethod());
            r22.setTypeface(AndroidUtilities.regular());
            r22.setTextSize(1, 13.0f);
            r02.addView(r22);
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public CharSequence getAccessibilityText() {
            return this.titleTextView.getText() + ".\n" + this.subtitleTextView.getText();
        }
    }

    /* loaded from: classes7.dex */
    public static class TwoLineLottieLayout extends ButtonLayout {
        public final RLottieImageView imageView;
        private final LinearLayout linearLayout;
        public final StickerImageView stickerImageView;
        public final LinkSpanDrawable.LinksTextView subtitleTextView;
        private final int textColor;
        public final LinkSpanDrawable.LinksTextView titleTextView;

        public TwoLineLottieLayout(Context r11, Theme.ResourcesProvider r12) {
            super(r11, r12);
            int r122 = Theme.key_undo_infoColor;
            this.textColor = getThemedColor(r122);
            setBackground(getThemedColor(Theme.key_undo_background));
            StickerImageView r0 = new StickerImageView(r11, UserConfig.selectedAccount);
            this.stickerImageView = r0;
            r0.setStickerPackName("exteraGramPlaceholders");
            r0.setVisibility(8);
            r0.setSize(AndroidUtilities.dp(32.0f), AndroidUtilities.dp(32.0f));
            addView(r0, LayoutHelper.createFrameRelatively(56.0f, 48.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER));
            RLottieImageView r02 = new RLottieImageView(r11);
            this.imageView = r02;
            r02.setScaleType(ImageView.ScaleType.CENTER);
            addView(r02, LayoutHelper.createFrameRelatively(56.0f, 48.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER));
            int r123 = getThemedColor(r122);
            int r03 = getThemedColor(Theme.key_undo_cancelColor);
            LinearLayout r1 = new LinearLayout(r11);
            this.linearLayout = r1;
            r1.setOrientation(1);
            addView(r1, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 52.0f, 8.0f, 8.0f, 8.0f));
            LinkSpanDrawable.LinksTextView r3 = new LinkSpanDrawable.LinksTextView(r11);
            this.titleTextView = r3;
            r3.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            r3.setTextColor(r123);
            r3.setTextSize(1, 14.0f);
            r3.setTypeface(AndroidUtilities.bold());
            r1.addView(r3);
            LinkSpanDrawable.LinksTextView r32 = new LinkSpanDrawable.LinksTextView(r11);
            this.subtitleTextView = r32;
            r32.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            r32.setTextColor(r123);
            r32.setLinkTextColor(r03);
            r32.setTypeface(AndroidUtilities.regular());
            r32.setTextSize(1, 13.0f);
            r1.addView(r32);
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public CharSequence getAccessibilityText() {
            return this.titleTextView.getText() + ".\n" + this.subtitleTextView.getText();
        }

        public void hideImage() {
            this.imageView.setVisibility(8);
            ((ViewGroup.MarginLayoutParams) this.linearLayout.getLayoutParams()).setMarginStart(AndroidUtilities.dp(10.0f));
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public void onShow() {
            super.onShow();
            this.imageView.playAnimation();
        }

        public void setAnimation(int r3, int r4, int r5, String... r6) {
            this.imageView.setAnimation(r3, r4, r5);
            int r32 = r6.length;
            int r42 = 0;
        L3:
            if (r42 >= r32) goto L5;
            String r52 = r6[r42];
            this.imageView.setLayerColor(r52, this.textColor);
            r42 = r42 + 1;
            goto L3
        }

        public void setAnimation(int r2, String... r3) {
            setAnimation(r2, 32, 32, r3);
        }

        public void setAnimation(TLRPC.Document r3, int r4, int r5, String... r6) {
            this.imageView.setAutoRepeat(true);
            this.imageView.setAnimation(r3, r4, r5);
            int r32 = r6.length;
            int r42 = 0;
        L3:
            if (r42 >= r32) goto L5;
            String r52 = r6[r42];
            this.imageView.setLayerColor(r52, this.textColor);
            r42 = r42 + 1;
            goto L3
        }

        public void setDrawable(int r2) {
            this.imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), r2));
        }

        public void setSticker(int r2) {
            this.stickerImageView.setStickerNum(r2);
            this.stickerImageView.setVisibility(0);
            this.imageView.setVisibility(8);
        }
    }

    @SuppressLint({"ViewConstructor"})
    /* loaded from: classes7.dex */
    public static final class UndoButton extends Button {
        private Bulletin bulletin;
        private Runnable delayedAction;
        private boolean isUndone;
        private final Theme.ResourcesProvider resourcesProvider;
        private Runnable undoAction;
        public TextView undoTextView;

        /* renamed from: $r8$lambda$SmBtl9g9ZAq7hRGvfWM6-aMczwU, reason: not valid java name */
        public static /* synthetic */ void m9088$r8$lambda$SmBtl9g9ZAq7hRGvfWM6aMczwU(UndoButton r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public UndoButton(Context r2, boolean r3) {
            this(r2, r3, null);
        }

        public UndoButton(Context r2, boolean r3, Theme.ResourcesProvider r4) {
            this(r2, r3, !r3, r4);
        }

        public UndoButton(Context r12, boolean r13, boolean r14, Theme.ResourcesProvider r15) {
            super(r12);
            this.resourcesProvider = r15;
            int r152 = getThemedColor(Theme.key_undo_cancelColor);
            if (r13 == false) goto L9;
            TextView r4 = new TextView(r12);
            this.undoTextView = r4;
            r4.setBackground(Theme.createRadSelectorDrawable((r152 & 16777215) | 419430400, 12, 12));
            this.undoTextView.setTextSize(1, 14.0f);
            this.undoTextView.setTypeface(AndroidUtilities.bold());
            this.undoTextView.setTextColor(r152);
            this.undoTextView.setText(LocaleController.getString(R.string.UndoNoCaps));
            this.undoTextView.setGravity(16);
            TextView r122 = this.undoTextView;
            if (r14 == false) goto L7;
            float r42 = 34.0f;
        L8:
            ViewHelper.setPaddingRelative(r122, r42, 8.0f, 12.0f, 8.0f);
            addView(this.undoTextView, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, 16, 8.0f, 0.0f, 8.0f, 0.0f));
            goto L9
        L7:
            r42 = 12.0f;
        L9:
            if (r14 == false) goto L14;
            ImageView r123 = new ImageView(getContext());
            r123.setImageResource(R.drawable.chats_undo);
            r123.setColorFilter(new PorterDuffColorFilter(r152, PorterDuff.Mode.MULTIPLY));
            if (r13 == true) goto L13;
            r123.setBackground(Theme.createSelectorDrawable((r152 & 16777215) | 419430400));
        L13:
            ViewHelper.setPaddingRelative(r123, 0.0f, 12.0f, 0.0f, 12.0f);
            addView(r123, LayoutHelper.createFrameRelatively(56.0f, 48.0f, 16));
        L14:
            setOnClickListener(new Bulletin$UndoButton$$ExternalSyntheticLambda0(this));
            ScaleStateListAnimator.apply(this);
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            undo();
        }

        public int getThemedColor(int r1) {
            Theme.ResourcesProvider r0 = this.resourcesProvider;
            if (r0 == null) goto L7;
            return r0.getColor(r1);
        L7:
            return Theme.getColor(r1);
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout.Callback
        public void onAttach(Layout r1, Bulletin r2) {
            this.bulletin = r2;
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout.Callback
        public void onDetach(Layout r1) {
            this.bulletin = null;
            Runnable r12 = this.delayedAction;
            if (r12 != null) goto L5;
            return;
        L5:
            if (this.isUndone == true) goto L9;
            r12.run();
            return;
        }

        public UndoButton setDelayedAction(Runnable r1) {
            this.delayedAction = r1;
            return this;
        }

        public UndoButton setText(CharSequence r2) {
            TextView r0 = this.undoTextView;
            if (r0 == null) goto L5;
            r0.setText(r2);
        L5:
            return this;
        }

        public UndoButton setUndoAction(Runnable r1) {
            this.undoAction = r1;
            return this;
        }

        public void undo() {
            if (this.bulletin == null) goto L11;
            this.isUndone = true;
            Runnable r0 = this.undoAction;
            if (r0 == null) goto L7;
            r0.run();
        L7:
            Bulletin r1 = this.bulletin;
            if (r1 == null) goto L12;
            r1.hide();
            return;
        L12:
            return;
        }
    }

    /* loaded from: classes7.dex */
    public static class UsersLayout extends ButtonLayout {
        public AvatarsImageView avatarsImageView;
        LinearLayout linearLayout;
        public TextView subtitleView;
        public TextView textView;

        /* renamed from: org.telegram.ui.Components.Bulletin$UsersLayout$1, reason: invalid class name */
        public class AnonymousClass1 extends LinkSpanDrawable.LinksTextView {
            final /* synthetic */ UsersLayout this$0;

            public AnonymousClass1(UsersLayout r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView, android.widget.TextView
            public void setText(CharSequence r3, TextView.BufferType r4) {
                super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
            }
        }

        /* renamed from: org.telegram.ui.Components.Bulletin$UsersLayout$2, reason: invalid class name */
        public class AnonymousClass2 extends LinkSpanDrawable.LinksTextView {
            final /* synthetic */ UsersLayout this$0;

            public AnonymousClass2(UsersLayout r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView, android.widget.TextView
            public void setText(CharSequence r3, TextView.BufferType r4) {
                super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
            }
        }

        public UsersLayout(Context r10, boolean r11, Theme.ResourcesProvider r12) {
            super(r10, r12);
            AvatarsImageView r122 = new AvatarsImageView(r10, false);
            this.avatarsImageView = r122;
            r122.setStyle(11);
            this.avatarsImageView.setAvatarsTextSize(AndroidUtilities.dp(18.0f));
            addView(this.avatarsImageView, LayoutHelper.createFrameRelatively(56.0f, 48.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 12.0f, 0.0f, 0.0f, 0.0f));
            int r123 = 3;
            if (r11 == true) goto L8;
            AnonymousClass1 r112 = new AnonymousClass1(this, r10);
            this.textView = r112;
            NotificationCenter.listenEmojiLoading(r112);
            this.textView.setTypeface(Typeface.SANS_SERIF);
            this.textView.setTextSize(1, 15.0f);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
            TextView r102 = this.textView;
            if (LocaleController.isRTL == false) goto L7;
            r123 = 5;
        L7:
            r102.setGravity(r123);
            addView(this.textView, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 70.0f, 0.0f, 12.0f, 0.0f));
        L9:
            this.textView.setLinkTextColor(getThemedColor(Theme.key_undo_cancelColor));
            setTextColor(getThemedColor(Theme.key_undo_infoColor));
            setBackground(getThemedColor(Theme.key_undo_background));
            return;
        L8:
            LinearLayout r113 = new LinearLayout(getContext());
            this.linearLayout = r113;
            r113.setOrientation(1);
            addView(this.linearLayout, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 76.0f, 6.0f, 12.0f, 6.0f));
            AnonymousClass2 r114 = new AnonymousClass2(this, r10);
            this.textView = r114;
            NotificationCenter.listenEmojiLoading(r114);
            TextView r115 = this.textView;
            Typeface r2 = Typeface.SANS_SERIF;
            r115.setTypeface(r2);
            this.textView.setTextSize(1, 14.0f);
            this.textView.setTypeface(AndroidUtilities.bold());
            TextView r116 = this.textView;
            TextUtils.TruncateAt r3 = TextUtils.TruncateAt.END;
            r116.setEllipsize(r3);
            this.textView.setMaxLines(1);
            this.linearLayout.addView(this.textView);
            LinkSpanDrawable.LinksTextView r117 = new LinkSpanDrawable.LinksTextView(r10);
            this.subtitleView = r117;
            r117.setTypeface(r2);
            this.subtitleView.setTextSize(1, 12.0f);
            this.subtitleView.setEllipsize(r3);
            this.subtitleView.setSingleLine(false);
            this.subtitleView.setMaxLines(3);
            this.subtitleView.setLinkTextColor(getThemedColor(Theme.key_undo_cancelColor));
            this.linearLayout.addView(this.subtitleView, LayoutHelper.createLinear(-2, -2, 0, 0, 0, 0, 0));
            goto L9
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public CharSequence getAccessibilityText() {
            return this.textView.getText();
        }

        @Override // org.telegram.ui.Components.Bulletin.Layout
        public void onShow() {
            super.onShow();
        }

        public void setTextColor(int r2) {
            this.textView.setTextColor(r2);
            TextView r1 = this.subtitleView;
            if (r1 == null) goto L6;
            r1.setTextColor(r2);
            return;
        }
    }

    /* renamed from: $r8$lambda$5i39nLEjQxv-154KgYh2GqTrjVM, reason: not valid java name */
    public static /* synthetic */ void m9042$r8$lambda$5i39nLEjQxv154KgYh2GqTrjVM(Bulletin r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$show$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$G60zcaaw0k0HGsEJUWd1vk0H--k, reason: not valid java name */
    public static /* synthetic */ void m9043$r8$lambda$G60zcaaw0k0HGsEJUWd1vk0Hk(Bulletin r0, Float r1) {
        r0.lambda$hide$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$JcHtyJx7CeENtRWgYXMYfPOGfAk(Bulletin r0) {
        r0.lambda$hide$3();
    }

    public static /* synthetic */ void $r8$lambda$NlgGlkFcZiaFrHe8O2PLrjHZeKI(Bulletin r0) {
        r0.lambda$hide$5();
    }

    public static /* synthetic */ void $r8$lambda$RzdIX0FtHh9AZfpdguRq0afSmzM(Bulletin r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
        r0.lambda$show$1(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$lHwf8i6M9K_gja5Xck44XIak3yY(Bulletin r0, boolean r1, View r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10) {
        r0.lambda$show$2(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    /* renamed from: -$$Nest$fgetallowBlurAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9044$$Nest$fgetallowBlurAnimation(Bulletin r0) {
        return r0.allowBlurAnimation;
    }

    /* renamed from: -$$Nest$fgetbottomOffsetSpring, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m9045$$Nest$fgetbottomOffsetSpring(Bulletin r0) {
        return r0.bottomOffsetSpring;
    }

    /* renamed from: -$$Nest$fgetcanHide, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9046$$Nest$fgetcanHide(Bulletin r0) {
        return r0.canHide;
    }

    /* renamed from: -$$Nest$fgetcontainerFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m9047$$Nest$fgetcontainerFragment(Bulletin r0) {
        return r0.containerFragment;
    }

    /* renamed from: -$$Nest$fgetcontainerLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m9048$$Nest$fgetcontainerLayout(Bulletin r0) {
        return r0.containerLayout;
    }

    /* renamed from: -$$Nest$fgetcurrentDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m9049$$Nest$fgetcurrentDelegate(Bulletin r0) {
        return r0.currentDelegate;
    }

    /* renamed from: -$$Nest$fgetlayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Layout m9050$$Nest$fgetlayout(Bulletin r0) {
        return r0.layout;
    }

    /* renamed from: -$$Nest$fgetlayoutTransition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Layout.Transition m9051$$Nest$fgetlayoutTransition(Bulletin r0) {
        return r0.layoutTransition;
    }

    /* renamed from: -$$Nest$fgetshowing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9052$$Nest$fgetshowing(Bulletin r0) {
        return r0.showing;
    }

    /* renamed from: -$$Nest$fgetskipShowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9053$$Nest$fgetskipShowAnimation(Bulletin r0) {
        return r0.skipShowAnimation;
    }

    /* renamed from: -$$Nest$fputcurrentDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9054$$Nest$fputcurrentDelegate(Bulletin r0, Delegate r1) {
        r0.currentDelegate = r1;
    }

    /* renamed from: -$$Nest$mensureLayoutTransitionCreated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9055$$Nest$mensureLayoutTransitionCreated(Bulletin r0) {
        r0.ensureLayoutTransitionCreated();
    }

    /* renamed from: -$$Nest$smfindDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m9056$$Nest$smfindDelegate(BaseFragment r0, FrameLayout r1) {
        return findDelegate(r0, r1);
    }

    /* renamed from: -$$Nest$smisTransitionsEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9057$$Nest$smisTransitionsEnabled() {
        return isTransitionsEnabled();
    }

    private Bulletin() {
        this.hideRunnable = new Bulletin$$ExternalSyntheticLambda5(this);
        this.loaded = true;
        this.hideAfterBottomSheet = true;
        this.setCanHideOnShow = true;
        this.layout = null;
        this.parentLayout = null;
        this.containerFragment = null;
        this.containerLayout = null;
    }

    private Bulletin(BaseFragment r3, FrameLayout r4, Layout r5, int r6) {
        this.hideRunnable = new Bulletin$$ExternalSyntheticLambda5(this);
        this.hideAfterBottomSheet = true;
        this.setCanHideOnShow = true;
        this.layout = r5;
        this.loaded = true ^ (r5 instanceof LoadingLayout);
        this.parentLayout = new AnonymousClass1(this, r5, r4);
        this.containerFragment = r3;
        this.containerLayout = r4;
        this.duration = r6;
    }

    public /* synthetic */ Bulletin(BulletinIA r1) {
        this();
    }

    public static void addDelegate(FrameLayout r1, Delegate r2) {
        if (r1 == null) goto L5;
        r1.setTag(R.id.bulletin_delegate_tag, r2);
        return;
    }

    public static void addDelegate(BaseFragment r0, Delegate r1) {
        if (r0 == null) goto L5;
        r0.setBulletinDelegate(r1);
        return;
    }

    private void ensureLayoutTransitionCreated() {
        Layout r0 = this.layout;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.layoutTransition != null) goto L9;
        this.layoutTransition = r0.createTransition();
        return;
    }

    public static Bulletin find(FrameLayout r4) {
        int r0 = r4.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        View r2 = r4.getChildAt(r1);
        if ((r2 instanceof Layout) == true) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return ((Layout) r2).bulletin;
    L9:
        return null;
    }

    private static Delegate findDelegate(BaseFragment r0, FrameLayout r1) {
        if (r0 == null) goto L6;
        Delegate r02 = r0.getBulletinDelegate();
        if (r02 == null) goto L6;
        return r02;
    L6:
        if (r1 == null) goto L11;
        Object r03 = r1.getTag(R.id.bulletin_delegate_tag);
        if ((r03 instanceof Delegate) == true) goto L10;
        return null;
    L10:
        return (Delegate) r03;
    L11:
        return null;
    }

    public static Bulletin getVisibleBulletin() {
        return visibleBulletin;
    }

    public static void hide(FrameLayout r1) {
        hide(r1, true);
    }

    public static void hide(FrameLayout r2, boolean r3) {
        Bulletin r22 = find(r2);
        if (r22 == null) goto L11;
        if (r3 == true) goto L6;
    L8:
        boolean r32 = false;
    L9:
        r22.hide(r32, 0);
        return;
    L6:
        if (isTransitionsEnabled() == false) goto L8;
        r32 = true;
        goto L9
    }

    public static void hideVisible() {
        Bulletin r0 = visibleBulletin;
        if (r0 == null) goto L6;
        r0.hide();
        return;
    }

    public static void hideVisible(ViewGroup r2) {
        Bulletin r0 = visibleBulletin;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.containerLayout != r2) goto L9;
        r0.hide();
        return;
    }

    private static boolean isTransitionsEnabled() {
        return MessagesController.getGlobalMainSettings().getBoolean("view_animations", true);
    }

    private /* synthetic */ void lambda$hide$3() {
        Delegate r0 = this.currentDelegate;
        if (r0 != null) goto L5;
    L7:
        Layout r02 = this.layout;
        r02.transitionRunningExit = false;
        r02.onExitTransitionEnd();
        this.layout.onHide();
        this.containerLayout.removeView(this.parentLayout);
        this.containerLayout.removeOnLayoutChangeListener(this.containerLayoutListener);
        this.layout.onDetach();
        Runnable r2 = this.onHideListener;
        if (r2 == null) goto L11;
        r2.run();
        return;
    L11:
        return;
    L5:
        if (this.layout.top == true) goto L7;
        r0.onBottomOffsetChange(0.0f);
        this.currentDelegate.onHide(this);
        goto L7
    }

    private /* synthetic */ void lambda$hide$4(Float r3) {
        Delegate r0 = this.currentDelegate;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.layout.top == true) goto L9;
        r0.onBottomOffsetChange(r2.getHeight() - r3.floatValue());
        return;
    }

    private /* synthetic */ void lambda$hide$5() {
        this.containerLayout.removeView(this.parentLayout);
        this.containerLayout.removeOnLayoutChangeListener(this.containerLayoutListener);
    }

    private /* synthetic */ void lambda$show$0(DynamicAnimation r1, float r2, float r3) {
        this.lastBottomOffset = (int) r2;
        updatePosition();
    }

    private /* synthetic */ void lambda$show$1(DynamicAnimation r1, boolean r2, float r3, float r4) {
        if (this.bottomOffsetSpring != r1) goto L6;
        this.bottomOffsetSpring = null;
        return;
    }

    private /* synthetic */ void lambda$show$2(boolean r1, View r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10) {
        Delegate r22 = this.currentDelegate;
        if (r22 != null) goto L5;
    L7:
        if (r1 == true) goto L23;
        Delegate r12 = this.currentDelegate;
        if (r12 == null) goto L11;
        int r13 = r12.getBottomOffset(this.tag);
    L13:
        if (this.lastBottomOffset == r13) goto L24;
        SpringAnimation r23 = this.bottomOffsetSpring;
        if (r23 != null) goto L17;
    L20:
        SpringAnimation r14 = new SpringAnimation(new FloatValueHolder(this.lastBottomOffset)).setSpring(new SpringForce().setFinalPosition(r13).setStiffness(900.0f).setDampingRatio(1.0f));
        this.bottomOffsetSpring = r14;
        r14.addUpdateListener(new Bulletin$$ExternalSyntheticLambda6(this));
        this.bottomOffsetSpring.addEndListener(new Bulletin$$ExternalSyntheticLambda7(this));
    L21:
        this.bottomOffsetSpring.start();
        return;
    L17:
        if (r23.isRunning() == false) goto L20;
        this.bottomOffsetSpring.getSpring().setFinalPosition(r13);
        goto L21
    L24:
        return;
    L11:
        r13 = 0;
        goto L13
    L23:
        return;
    L5:
        if (r22.allowLayoutChanges() == true) goto L7;
    }

    public static Bulletin make(FrameLayout r2, Layout r3, int r4) {
        if (r2 != null) goto L6;
        return new EmptyBulletin();
    L6:
        return new Bulletin(null, r2, r3, r4);
    }

    @SuppressLint({"RtlHardcoded"})
    public static Bulletin make(BaseFragment r2, Layout r3, int r4) {
        if (r2 != null) goto L6;
        return new EmptyBulletin();
    L6:
        if ((r2 instanceof ChatActivity) == false) goto L9;
        Layout.m9063$$Nest$msetWideScreenParams(r3, -2, 1);
    L12:
        return new Bulletin(r2, r2.getBulletinLayoutContainer(), r3, r4);
    L9:
        if ((r2 instanceof DialogsActivity) == false) goto L12;
        Layout.m9063$$Nest$msetWideScreenParams(r3, -1, 0);
        goto L12
    }

    public static void removeDelegate(FrameLayout r2) {
        if (r2 == null) goto L5;
        r2.setTag(R.id.bulletin_delegate_tag, null);
        return;
    }

    public static void removeDelegate(BaseFragment r1) {
        if (r1 == null) goto L5;
        r1.setBulletinDelegate(null);
        return;
    }

    public static void updateCurrentPosition() {
        Bulletin r0 = visibleBulletin;
        if (r0 == null) goto L8;
        Layout r02 = r0.layout;
        if (r02 == null) goto L9;
        r02.updatePosition();
        return;
    L9:
        return;
    }

    public Bulletin allowBlur() {
        this.allowBlurAnimation = true;
        return this;
    }

    public Layout getLayout() {
        return this.layout;
    }

    public void hide() {
        hide(isTransitionsEnabled(), 0);
    }

    public void hide(long r2) {
        hide(isTransitionsEnabled(), r2);
    }

    public void hide(boolean r10, long r11) {
        Layout r0 = this.layout;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.showing == false) goto L34;
        this.showing = false;
        if (visibleBulletin != this) goto L10;
        visibleBulletin = null;
    L10:
        int r8 = this.currentBottomOffset;
        this.currentBottomOffset = 0;
        if (ViewCompat.isLaidOut(r0) == false) goto L13;
    L14:
        this.layout.removeCallbacks(this.hideRunnable);
        if (r10 == false) goto L22;
        Layout r102 = this.layout;
        r102.transitionRunningExit = true;
        r102.delegate = this.currentDelegate;
        r102.invalidate();
        if (r11 < 0) goto L19;
        Layout.DefaultTransition r103 = new Layout.DefaultTransition();
        r103.duration = r11;
        this.layoutTransition = r103;
    L20:
        Layout.Transition r3 = this.layoutTransition;
        Layout r4 = this.layout;
        Objects.requireNonNull(r4);
        r3.animateExit(r4, new Bulletin$$ExternalSyntheticLambda1(r4), new Bulletin$$ExternalSyntheticLambda2(this), new Bulletin$$ExternalSyntheticLambda3(this), r8);
        return;
    L19:
        ensureLayoutTransitionCreated();
    L22:
        Delegate r104 = this.currentDelegate;
        if (r104 != null) goto L25;
    L27:
        this.layout.onExitTransitionStart();
        this.layout.onExitTransitionEnd();
        this.layout.onHide();
        if (this.containerLayout == null) goto L30;
        AndroidUtilities.runOnUIThread(new Bulletin$$ExternalSyntheticLambda4(this));
    L30:
        this.layout.onDetach();
        Runnable r9 = this.onHideListener;
        if (r9 == null) goto L35;
        r9.run();
        return;
    L35:
        return;
    L25:
        if (this.layout.top == true) goto L27;
        r104.onBottomOffsetChange(0.0f);
        this.currentDelegate.onHide(this);
        goto L27
    L13:
        if (this.ignoreDetach == false) goto L22;
    }

    public Bulletin hideAfterBottomSheet(boolean r1) {
        this.hideAfterBottomSheet = r1;
        return this;
    }

    public Bulletin ignoreDetach() {
        this.ignoreDetach = true;
        return this;
    }

    public boolean isShowing() {
        return this.showing;
    }

    public void onLoaded(CharSequence r4) {
        this.loaded = true;
        ViewParent r1 = this.layout;
        if ((r1 instanceof LoadingLayout) == false) goto L5;
        ((LoadingLayout) r1).onTextLoaded(r4);
    L5:
        setCanHide(true);
    }

    public void setCanHide(boolean r4) {
        if (r4 == true) goto L4;
    L6:
        boolean r42 = false;
    L8:
        if (this.canHide == r42) goto L19;
        Layout r0 = this.layout;
        if (r0 == null) goto L20;
        this.canHide = r42;
        if (r42 == false) goto L17;
        int r43 = this.duration;
        if (r43 < 0) goto L21;
        r0.postDelayed(this.hideRunnable, r43);
        return;
    L21:
        return;
    L17:
        r0.removeCallbacks(this.hideRunnable);
        return;
    L20:
        return;
    L19:
        return;
    L4:
        if (this.loaded == false) goto L6;
        r42 = true;
        goto L8
    }

    public Bulletin setDuration(int r1) {
        this.duration = r1;
        return this;
    }

    public Bulletin setImageScale(float r3) {
        Layout r0 = this.layout;
        if ((r0 instanceof TwoLineLottieLayout) == false) goto L5;
        RLottieImageView r02 = ((TwoLineLottieLayout) r0).imageView;
        r02.setScaleX(r3);
        r02.setScaleY(r3);
    L5:
        return this;
    }

    public Bulletin setOnClickListener(View.OnClickListener r2) {
        Layout r0 = this.layout;
        if (r0 == null) goto L5;
        r0.setOnClickListener(r2);
    L5:
        return this;
    }

    public Bulletin setOnHideListener(Runnable r1) {
        this.onHideListener = r1;
        return this;
    }

    public Bulletin setTag(int r1) {
        this.tag = r1;
        return this;
    }

    public Bulletin show() {
        return show(false);
    }

    public Bulletin show(boolean r3) {
        if (this.showing == false) goto L5;
        return this;
    L5:
        if (this.containerLayout == null) goto L22;
        this.showing = true;
        this.layout.setTop(r3);
        CharSequence r0 = this.layout.getAccessibilityText();
        if (r0 == null) goto L10;
        AndroidUtilities.makeAccessibilityAnnouncement(r0);
    L10:
        if (this.layout.getParent() != this.parentLayout) goto L19;
        Bulletin r02 = visibleBulletin;
        if (r02 == null) goto L14;
        r02.hide();
    L14:
        visibleBulletin = this;
        this.layout.onAttach(this);
        FrameLayout r03 = this.containerLayout;
        Bulletin$$ExternalSyntheticLambda0 r1 = new Bulletin$$ExternalSyntheticLambda0(this, r3);
        this.containerLayoutListener = r1;
        r03.addOnLayoutChangeListener(r1);
        this.layout.addOnLayoutChangeListener(new AnonymousClass2(this, r3));
        if (this.ignoreDetach == true) goto L17;
        this.layout.addOnAttachStateChangeListener(new AnonymousClass3(this));
    L17:
        this.containerLayout.addView(this.parentLayout);
        return this;
    L19:
        Segment$$ExternalSyntheticBUOutline1.m("Layout has incorrect parent");
        return null;
    L22:
        return this;
    }

    public Bulletin skipShowAnimation() {
        this.skipShowAnimation = true;
        return this;
    }

    public void updatePosition() {
        Layout r0 = this.layout;
        if (r0 == null) goto L6;
        r0.updatePosition();
        return;
    }

    public Bulletin wrapContent() {
        if ((this.layout.getLayoutParams() instanceof FrameLayout.LayoutParams) == false) goto L5;
        ((FrameLayout.LayoutParams) this.layout.getLayoutParams()).width = -2;
        ((FrameLayout.LayoutParams) this.layout.getLayoutParams()).gravity |= 1;
    L5:
        return this;
    }
}
