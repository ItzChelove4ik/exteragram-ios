package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Insets;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.widget.DrawableUtils$$ExternalSyntheticApiModelOutline0;
import androidx.appcompat.widget.DrawableUtils$$ExternalSyntheticApiModelOutline2;
import androidx.appcompat.widget.DrawableUtils$$ExternalSyntheticApiModelOutline3;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsCompat$Impl30$$ExternalSyntheticApiModelOutline0;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import kotlin.time.DurationKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AndroidUtilities$$ExternalSyntheticApiModelOutline1;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.camera.CameraView;
import org.telegram.messenger.utils.LeakDetector;
import org.telegram.messenger.utils.WindowVisibilityManager;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes3.dex */
public class BottomSheet extends Dialog implements BaseFragment.AttachedSheet {
    private static final boolean AVOID_SYSTEM_CUTOUT_FULLSCREEN = false;
    private boolean allowCustomAnimation;
    protected boolean allowNestedScroll;
    private boolean applyBottomPadding;
    private boolean applyTopPadding;
    public BaseFragment attachedFragment;
    private OnBackAnimationCallback backAnimationCallback;
    protected SheetBackDrawable backDrawable;
    protected int backgroundPaddingLeft;
    protected int backgroundPaddingTop;
    protected int behindKeyboardColor;
    protected int behindKeyboardColorKey;
    private boolean bigTitle;
    private int bottomInset;
    protected boolean calcMandatoryInsets;
    private boolean canDismissWithSwipe;
    private boolean canDismissWithTouchOutside;
    private int cellType;
    public final ContainerView container;
    protected ViewGroup containerView;
    protected int currentAccount;
    private float currentPanTranslationY;
    protected AnimatorSet currentSheetAnimation;
    protected int currentSheetAnimationType;
    private View customView;
    protected int customViewGravity;
    protected BottomSheetDelegateInterface delegate;
    protected boolean dimBehind;
    protected int dimBehindAlpha;
    private boolean disableScroll;
    private Runnable dismissRunnable;
    private boolean dismissed;
    public boolean doNotOverlayNavigationBar;
    public boolean drawDoubleNavigationBar;
    public boolean drawNavigationBar;
    private boolean focusable;
    private int focusableSoftInputMode;
    private boolean forceKeyboardOnDismiss;
    private boolean fullHeight;
    protected boolean fullWidth;
    private float hideSystemVerticalInsetsProgress;
    private int internalBackgroundColor;
    private boolean isBackCallbackRegistered;
    protected boolean isFullscreen;
    protected boolean isPortrait;
    private int[] itemIcons;
    private ArrayList<BottomSheetCell> itemViews;
    private CharSequence[] items;
    public ValueAnimator keyboardContentAnimator;
    protected int keyboardHeight;
    protected boolean keyboardVisible;
    private WindowInsets lastInsets;
    private int lastKeyboardHeight;
    private int layoutCount;
    private int leftInset;
    private boolean multipleLinesTitle;
    protected int navBarColor;
    protected int navBarColorKey;
    protected float navigationBarAlpha;
    protected ValueAnimator navigationBarAnimation;
    protected int navigationBarHeight;
    protected View nestedScrollChild;
    private AnimationNotificationsLocker notificationsLocker;
    public boolean occupyNavigationBar;
    public boolean occupyNavigationBarWithoutKeyboard;
    private OnBackInvokedDispatcher onBackInvokedDispatcher;
    private DialogInterface.OnClickListener onClickListener;
    private DialogInterface.OnDismissListener onHideListener;
    public int openDuration;
    public Interpolator openInterpolator;
    private boolean openNoDelay;
    protected int openedLayerNum;
    private int overlayDrawNavBarColor;
    public boolean pauseAllHeavyOperations;
    protected int playingImagesLayerNum;
    private boolean predictiveBackAnimationInProgress;
    protected Theme.ResourcesProvider resourcesProvider;
    private int rightInset;
    public boolean scrollNavBar;
    private Integer selectedPos;
    protected Drawable shadowDrawable;
    private boolean showWithoutAnimation;
    boolean showing;
    private boolean skipDismissAnimation;
    private long smoothContainerViewLayoutUntil;
    public boolean smoothKeyboardAnimationEnabled;
    public boolean smoothKeyboardByBottom;
    protected Runnable startAnimationRunnable;
    private int statusBarHeight;
    private int tag;
    private CharSequence title;
    private TextView titleView;
    public FrameLayout topBulletinContainer;
    private int touchSlop;
    private boolean transitionFromRight;
    public boolean useBackgroundTopPadding;
    private boolean useFastDismiss;
    protected boolean useHardwareLayer;
    protected boolean useLightNavBar;
    protected boolean useLightStatusBar;
    protected boolean useSmoothKeyboard;
    protected boolean waitingKeyboard;
    private WindowVisibilityManager windowVisibilityManager;

    /* renamed from: org.telegram.ui.ActionBar.BottomSheet$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends ContainerView {
        final /* synthetic */ BottomSheet this$0;

        /* renamed from: org.telegram.ui.ActionBar.BottomSheet$1$1, reason: invalid class name and collision with other inner class name */
        public class C00341 implements Bulletin.Delegate {
            final /* synthetic */ AnonymousClass1 this$1;

            public C00341(AnonymousClass1 r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getTopOffset(int r1) {
                return AndroidUtilities.statusBarHeight;
            }
        }

        public AnonymousClass1(BottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet.ContainerView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r1) {
            super.dispatchDraw(r1);
            this.this$0.mainContainerDispatchDraw(r1);
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet.ContainerView, android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            return super.drawChild(r1, r2, r3);
        L4:
            e = move-exception;
            FileLog.e(e);
            return true;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (Build.VERSION.SDK_INT < 34) goto L5;
            BottomSheet.m5711$$Nest$mregisterBackCallback(this.this$0);
        L5:
            Bulletin.addDelegate(this, new C00341(this));
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration r2) {
            BottomSheet.m5697$$Nest$fputlastInsets(this.this$0, null);
            this.this$0.container.requestApplyInsets();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (Build.VERSION.SDK_INT < 34) goto L5;
            BottomSheet.m5713$$Nest$munregisterBackCallback(this.this$0);
        L5:
            Bulletin.removeDelegate(this);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.BottomSheet$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ BottomSheet this$0;

        public AnonymousClass2(BottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public boolean hasOverlappingRendering() {
            return false;
        }

        @Override // android.view.View
        public void setTranslationY(float r4) {
            super.setTranslationY(r4);
            FrameLayout r1 = this.this$0.topBulletinContainer;
            if (r1 == null) goto L5;
            r1.setTranslationY((-(r0.container.getHeight() - this.this$0.containerView.getY())) + this.this$0.backgroundPaddingTop);
        L5:
            this.this$0.onContainerTranslationYChanged(r4);
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.BottomSheet$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends TextView {
        final /* synthetic */ BottomSheet this$0;

        public AnonymousClass3(BottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, r4);
            if (BottomSheet.m5678$$Nest$fgetmultipleLinesTitle(this.this$0) == false) goto L17;
            int r32 = getMeasuredHeight();
            View r42 = BottomSheet.m5669$$Nest$fgetcustomView(this.this$0);
            BottomSheet r0 = this.this$0;
            if (r42 == null) goto L9;
            ((ViewGroup.MarginLayoutParams) BottomSheet.m5669$$Nest$fgetcustomView(r0).getLayoutParams()).topMargin = r32;
            return;
        L9:
            if (r0.containerView == null) goto L21;
            int r43 = 1;
        L12:
            if (r43 >= this.this$0.containerView.getChildCount()) goto L22;
            View r02 = this.this$0.containerView.getChildAt(r43);
            if ((r02 instanceof BottomSheetCell) == false) goto L16;
            ((ViewGroup.MarginLayoutParams) r02.getLayoutParams()).topMargin = r32;
            r32 = r32 + AndroidUtilities.dp(48.0f);
        L16:
            r43 = r43 + 1;
            goto L12
        L22:
            return;
        L21:
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.BottomSheet$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ BottomSheet this$0;

        public AnonymousClass4(BottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomSheet r0 = this.this$0;
            if (r0.startAnimationRunnable == this) goto L5;
            return;
        L5:
            if (BottomSheet.m5672$$Nest$fgetdismissed(r0) == true) goto L10;
            BottomSheet r2 = this.this$0;
            r2.startAnimationRunnable = null;
            BottomSheet.m5712$$Nest$mstartOpenAnimation(r2);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.BottomSheet$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ BottomSheet this$0;

        public AnonymousClass5(BottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            AnimatorSet r0 = this.this$0.currentSheetAnimation;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.equals(r2) == false) goto L9;
            BottomSheet r1 = this.this$0;
            r1.currentSheetAnimation = null;
            r1.currentSheetAnimationType = 0;
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            AnimatorSet r0 = this.this$0.currentSheetAnimation;
            if (r0 == null) goto L16;
            if (r0.equals(r4) == false) goto L16;
            BottomSheet r42 = this.this$0;
            r42.currentSheetAnimation = null;
            r42.currentSheetAnimationType = 0;
            r42.onOpenAnimationEnd();
            BottomSheetDelegateInterface r43 = this.this$0.delegate;
            if (r43 == null) goto L9;
            r43.onOpenAnimationEnd();
        L9:
            BottomSheet r44 = this.this$0;
            if (r44.useHardwareLayer == false) goto L12;
            r44.container.setLayerType(0, null);
        L12:
            BottomSheet r45 = this.this$0;
            if (r45.isFullscreen == false) goto L16;
            WindowManager.LayoutParams r46 = r45.getWindow().getAttributes();
            r46.flags &= -1025;
            this.this$0.getWindow().setAttributes(r46);
        L16:
            if (this.this$0.pauseAllHeavyOperations == false) goto L18;
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
        L18:
            BottomSheet.m5679$$Nest$fgetnotificationsLocker(this.this$0).unlock();
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.BottomSheet$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ BottomSheet this$0;
        final /* synthetic */ int val$item;

        public static /* synthetic */ void $r8$lambda$xaX7gZ5YwqYRNeNpf6ZXFkhZCu4(AnonymousClass6 r0) {
            r0.lambda$onAnimationEnd$0();
        }

        public AnonymousClass6(BottomSheet r1, int r2) {
            this.this$0 = r1;
            this.val$item = r2;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0() {
            if (BottomSheet.m5681$$Nest$fgetonHideListener(this.this$0) == null) goto L10;
            BottomSheet.m5681$$Nest$fgetonHideListener(this.this$0).onDismiss(this.this$0);
        L10:
            this.this$0.dismissInternal();     // Catch: Exception -> L7
            return;
        L7:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            AnimatorSet r0 = this.this$0.currentSheetAnimation;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.equals(r2) == false) goto L9;
            BottomSheet r1 = this.this$0;
            r1.currentSheetAnimation = null;
            r1.currentSheetAnimationType = 0;
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            AnimatorSet r0 = this.this$0.currentSheetAnimation;
            if (r0 != null) goto L5;
        L10:
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
            return;
        L5:
            if (r0.equals(r3) == false) goto L10;
            BottomSheet r32 = this.this$0;
            r32.currentSheetAnimation = null;
            r32.currentSheetAnimationType = 0;
            if (BottomSheet.m5680$$Nest$fgetonClickListener(r32) == null) goto L9;
            BottomSheet.m5680$$Nest$fgetonClickListener(this.this$0).onClick(this.this$0, this.val$item);
        L9:
            AndroidUtilities.runOnUIThread(new BottomSheet$6$$ExternalSyntheticLambda0(this));
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.BottomSheet$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ BottomSheet this$0;

        public static /* synthetic */ void $r8$lambda$YLqon77yhb8UUbghQf2qtKqQAbM(AnonymousClass7 r0) {
            r0.lambda$onAnimationEnd$0();
        }

        public AnonymousClass7(BottomSheet r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0() {
            this.this$0.dismissInternal();     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            AnimatorSet r0 = this.this$0.currentSheetAnimation;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.equals(r2) == false) goto L9;
            BottomSheet r1 = this.this$0;
            r1.currentSheetAnimation = null;
            r1.currentSheetAnimationType = 0;
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            AnimatorSet r0 = this.this$0.currentSheetAnimation;
            if (r0 != null) goto L5;
        L7:
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
            return;
        L5:
            if (r0.equals(r2) == false) goto L7;
            BottomSheet r22 = this.this$0;
            r22.currentSheetAnimation = null;
            r22.currentSheetAnimationType = 0;
            AndroidUtilities.runOnUIThread(new BottomSheet$7$$ExternalSyntheticLambda0(this));
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.ActionBar.BottomSheet$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 implements OnBackAnimationCallback {
        final /* synthetic */ BottomSheet this$0;

        /* renamed from: org.telegram.ui.ActionBar.BottomSheet$8$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass8 this$1;

            public AnonymousClass1(AnonymousClass8 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (ContainerView.m5719$$Nest$fgetcurrentAnimation(this.this$1.this$0.container) != null) goto L5;
                return;
            L5:
                if (ContainerView.m5719$$Nest$fgetcurrentAnimation(this.this$1.this$0.container).equals(r2) == false) goto L9;
                ContainerView.m5721$$Nest$fputcurrentAnimation(this.this$1.this$0.container, null);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$czmTbaRR9UgksfAJ4geYvh2LcBc(AnonymousClass8 r0, ValueAnimator r1) {
            r0.lambda$onBackCancelled$0(r1);
        }

        public AnonymousClass8(BottomSheet r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onBackCancelled$0(ValueAnimator r1) {
            this.this$0.onContainerViewTranslation();
        }

        public void onBackCancelled() {
            BottomSheet.m5703$$Nest$fputpredictiveBackAnimationInProgress(this.this$0, false);
            ContainerView r0 = this.this$0.container;
            if (r0 != null) goto L5;
            return;
        L5:
            r0.resetTouch();
            ContainerView.m5723$$Nest$mcancelCurrentAnimation(this.this$0.container);
            ObjectAnimator r02 = ObjectAnimator.ofFloat(this.this$0.containerView, View.TRANSLATION_Y, new float[]{0.0f});
            r02.addUpdateListener(new BottomSheet$8$$ExternalSyntheticLambda0(this));
            ContainerView.m5721$$Nest$fputcurrentAnimation(this.this$0.container, new AnimatorSet());
            ContainerView.m5719$$Nest$fgetcurrentAnimation(this.this$0.container).playTogether(new Animator[]{r02});
            BottomSheet r03 = this.this$0;
            if (r03.backDrawable == null) goto L12;
            AnimatorSet r04 = ContainerView.m5719$$Nest$fgetcurrentAnimation(r03.container);
            BottomSheet r2 = this.this$0;
            SheetBackDrawable r4 = r2.backDrawable;
            Property<Drawable, Integer> r5 = AnimationProperties.COLOR_DRAWABLE_ALPHA;
            if (r2.dimBehind == false) goto L10;
            int r22 = r2.dimBehindAlpha;
        L11:
            r04.playTogether(new Animator[]{ObjectAnimator.ofInt(r4, r5, new int[]{r22})});
            goto L12
        L10:
            r22 = 0;
        L12:
            ContainerView.m5719$$Nest$fgetcurrentAnimation(this.this$0.container).setDuration(250);
            ContainerView.m5719$$Nest$fgetcurrentAnimation(this.this$0.container).setInterpolator(CubicBezierInterpolator.DEFAULT);
            ContainerView.m5719$$Nest$fgetcurrentAnimation(this.this$0.container).addListener(new AnonymousClass1(this));
            ContainerView.m5719$$Nest$fgetcurrentAnimation(this.this$0.container).start();
        }

        public void onBackInvoked() {
            BottomSheetDelegateInterface r0 = this.this$0.delegate;
            if (r0 != null) goto L5;
        L8:
            BottomSheet.m5703$$Nest$fputpredictiveBackAnimationInProgress(this.this$0, false);
            ContainerView r02 = this.this$0.container;
            if (r02 == null) goto L11;
            r02.resetTouch();
        L11:
            this.this$0.dismiss();
            return;
        L5:
            if (r0.canDismiss() == true) goto L8;
            onBackCancelled();
        }

        public void onBackProgressed(BackEvent r3) {
            if (BottomSheet.m5672$$Nest$fgetdismissed(this.this$0) == false) goto L5;
            return;
        L5:
            if (this.this$0.container == null) goto L13;
            float r32 = r3.getProgress();
            this.this$0.containerView.setTranslationY(this.this$0.containerView.getMeasuredHeight() * (0.15f * r32));
            this.this$0.onContainerViewTranslation();
            SheetBackDrawable r0 = this.this$0.backDrawable;
            if (r0 == null) goto L12;
            r0.setAlpha((int) (r2.dimBehindAlpha * (1.0f - r32)));
            return;
        L12:
            return;
        }

        public void onBackStarted(BackEvent r2) {
            BottomSheet.m5703$$Nest$fputpredictiveBackAnimationInProgress(this.this$0, true);
            ContainerView r1 = this.this$0.container;
            if (r1 == null) goto L6;
            r1.resetTouch();
            return;
        }
    }

    public static class BottomSheetCell extends FrameLayout {
        private boolean checked;
        int currentType;
        private ImageView imageView;
        private ImageView imageView2;
        public boolean isSelected;
        private final Theme.ResourcesProvider resourcesProvider;
        private AnimatedEmojiSpan.TextViewEmojis textView;

        /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageView m5715$$Nest$fgetimageView(BottomSheetCell r0) {
            return r0.imageView;
        }

        /* renamed from: -$$Nest$fgettextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedEmojiSpan.TextViewEmojis m5716$$Nest$fgettextView(BottomSheetCell r0) {
            return r0.textView;
        }

        public BottomSheetCell(Context r2, int r3) {
            this(r2, r3, null);
        }

        public BottomSheetCell(Context r9, int r10, Theme.ResourcesProvider r11) {
            super(r9);
            this.isSelected = false;
            this.resourcesProvider = r11;
            this.currentType = r10;
            if (r10 == Builder.CELL_TYPE_CALL) goto L5;
            setBackgroundDrawable(Theme.getSelectorDrawable(false, r11));
        L5:
            ImageView r1 = new ImageView(r9);
            this.imageView = r1;
            ImageView.ScaleType r2 = ImageView.ScaleType.CENTER;
            r1.setScaleType(r2);
            this.imageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_dialogIcon), PorterDuff.Mode.MULTIPLY));
            ImageView r12 = this.imageView;
            int r4 = 3;
            if (LocaleController.isRTL == false) goto L8;
            int r3 = 5;
        L9:
            addView(r12, LayoutHelper.createFrame(56, 48, r3 | 16));
            ImageView r13 = new ImageView(r9);
            this.imageView2 = r13;
            r13.setScaleType(r2);
            this.imageView2.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_radioBackgroundChecked, r11), PorterDuff.Mode.SRC_IN));
            ImageView r112 = this.imageView2;
            if (LocaleController.isRTL == false) goto L12;
            int r14 = 3;
        L13:
            addView(r112, LayoutHelper.createFrame(56, 48, r14 | 16));
            AnimatedEmojiSpan.TextViewEmojis r113 = new AnimatedEmojiSpan.TextViewEmojis(r9);
            this.textView = r113;
            r113.setLines(1);
            this.textView.setSingleLine(true);
            this.textView.setGravity(1);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            if (r10 != 0) goto L16;
        L26:
            this.textView.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
            this.textView.setTextSize(1, 16.0f);
            AnimatedEmojiSpan.TextViewEmojis r92 = this.textView;
            if (LocaleController.isRTL == false) goto L29;
            r4 = 5;
        L29:
            addView(r92, LayoutHelper.createFrame(-2, -2, r4 | 16));
            return;
        L16:
            if (r10 == Builder.CELL_TYPE_CALL) goto L26;
            if (r10 != 1) goto L23;
            this.textView.setGravity(17);
            this.textView.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
            this.textView.setTextSize(1, 14.0f);
            this.textView.setTypeface(AndroidUtilities.bold());
            addView(this.textView, LayoutHelper.createFrame(-1, -1.0f));
            return;
        L23:
            if (r10 != 2) goto L31;
            this.textView.setGravity(17);
            this.textView.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
            this.textView.setTextSize(1, 14.0f);
            this.textView.setTypeface(AndroidUtilities.bold());
            this.textView.setBackground(Theme.AdaptiveRipple.filledRect(getThemedColor(Theme.key_featuredStickers_addButton), new float[]{6.0f}));
            addView(this.textView, LayoutHelper.createFrame(-1, -1.0f, 0, 16.0f, 16.0f, 16.0f, 16.0f));
            return;
        L31:
            return;
        L12:
            r14 = 5;
            goto L13
        L8:
            r3 = 3;
            goto L9
        }

        public ImageView getImageView() {
            return this.imageView;
        }

        public AnimatedEmojiSpan.TextViewEmojis getTextView() {
            return this.textView;
        }

        public int getThemedColor(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        public boolean isChecked() {
            return this.checked;
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            if (this.isSelected == false) goto L6;
            r1.setSelected(true);
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            int r42 = this.currentType;
            if (r42 != 2) goto L5;
            int r0 = 80;
        L7:
            if (r42 != 0) goto L9;
            r3 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30);
        L9:
            super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r0), TLObject.FLAG_30));
            return;
        L5:
            r0 = 48;
            goto L7
        }

        public void setChecked(boolean r2) {
            ImageView r0 = this.imageView2;
            this.checked = r2;
            if (r2 == false) goto L5;
            int r1 = R.drawable.checkbig;
        L6:
            r0.setImageResource(r1);
            return;
        L5:
            r1 = 0;
            goto L6
        }

        public void setGravity(int r1) {
            this.textView.setGravity(r1);
        }

        public void setIconColor(int r3) {
            this.imageView.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.MULTIPLY));
        }

        public void setTextAndIcon(CharSequence r3, int r4) {
            setTextAndIcon(r3, r4, null, false);
        }

        public void setTextAndIcon(CharSequence r4, int r5, Drawable r6, boolean r7) {
            this.textView.setText(r4);
            float r42 = 16.0f;
            float r0 = 21.0f;
            if (r5 != 0) goto L15;
            if (r6 != null) goto L15;
            this.imageView.setVisibility(4);
            AnimatedEmojiSpan.TextViewEmojis r3 = this.textView;
            if (r7 == false) goto L9;
            float r52 = 21.0f;
        L10:
            int r53 = AndroidUtilities.dp(r52);
            if (r7 == false) goto L13;
            r42 = 21.0f;
        L13:
            r3.setPadding(r53, 0, AndroidUtilities.dp(r42), 0);
            return;
        L9:
            r52 = 16.0f;
        L15:
            ImageView r2 = this.imageView;
            if (r6 == null) goto L18;
            r2.setImageDrawable(r6);
        L19:
            this.imageView.setVisibility(0);
            AnimatedEmojiSpan.TextViewEmojis r54 = this.textView;
            if (r7 == false) goto L39;
            if (LocaleController.isRTL == false) goto L24;
            float r43 = 21.0f;
        L25:
            int r44 = AndroidUtilities.dp(r43);
            if (LocaleController.isRTL == false) goto L28;
            r0 = 72.0f;
        L28:
            r54.setPadding(r44, 0, AndroidUtilities.dp(r0), 0);
            ImageView r32 = this.imageView;
            if (LocaleController.isRTL == false) goto L31;
            int r45 = 0;
        L33:
            if (LocaleController.isRTL == false) goto L35;
            int r55 = AndroidUtilities.dp(5.0f);
        L36:
            r32.setPadding(r45, 0, r55, 0);
            return;
        L35:
            r55 = 5;
            goto L36
        L31:
            r45 = AndroidUtilities.dp(5.0f);
            goto L33
        L24:
            r43 = 72.0f;
            goto L25
        L39:
            if (LocaleController.isRTL == false) goto L41;
            float r72 = 16.0f;
        L42:
            int r73 = AndroidUtilities.dp(r72);
            if (LocaleController.isRTL == false) goto L45;
            r42 = 72.0f;
        L45:
            r54.setPadding(r73, 0, AndroidUtilities.dp(r42), 0);
            this.imageView.setPadding(0, 0, 0, 0);
            return;
        L41:
            r72 = 72.0f;
            goto L42
        L18:
            r2.setImageResource(r5);
            goto L19
        }

        public void setTextAndIcon(CharSequence r2, Drawable r3) {
            setTextAndIcon(r2, 0, r3, false);
        }

        public void setTextColor(int r1) {
            this.textView.setTextColor(r1);
        }
    }

    public static class BottomSheetDelegate implements BottomSheetDelegateInterface {
        public BottomSheetDelegate() {
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegateInterface
        public boolean canDismiss() {
            return true;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegateInterface
        public void onOpenAnimationEnd() {
        }

        public void onOpenAnimationStart() {
        }
    }

    public interface BottomSheetDelegateInterface {
        boolean canDismiss();

        void onOpenAnimationEnd();
    }

    public static class Builder {
        public static int CELL_TYPE_CALL = 4;
        public BottomSheet bottomSheet;

        static {
        }

        public Builder(Context r2) {
            this(r2, false);
        }

        public Builder(Context r2, boolean r3) {
            this(r2, r3, null);
        }

        public Builder(Context r2, boolean r3, Theme.ResourcesProvider r4) {
            BottomSheet r0 = new BottomSheet(r2, r3, r4);
            this.bottomSheet = r0;
            r0.fixNavigationBar();
        }

        public Builder(Context r2, boolean r3, Theme.ResourcesProvider r4, int r5) {
            BottomSheet r0 = new BottomSheet(r2, r3, r4);
            this.bottomSheet = r0;
            r0.setBackgroundColor(r5);
            this.bottomSheet.fixNavigationBar(r5);
        }

        public BottomSheet create() {
            return this.bottomSheet;
        }

        public Runnable getDismissRunnable() {
            return BottomSheet.m5671$$Nest$fgetdismissRunnable(this.bottomSheet);
        }

        public Builder selectedPos(Integer r2) {
            BottomSheet.m5705$$Nest$fputselectedPos(this.bottomSheet, r2);
            return this;
        }

        public Builder setApplyBottomPadding(boolean r2) {
            BottomSheet.m5689$$Nest$fputapplyBottomPadding(this.bottomSheet, r2);
            return this;
        }

        public Builder setApplyTopPadding(boolean r2) {
            BottomSheet.m5690$$Nest$fputapplyTopPadding(this.bottomSheet, r2);
            return this;
        }

        public Builder setCellType(int r2) {
            BottomSheet.m5693$$Nest$fputcellType(this.bottomSheet, r2);
            return this;
        }

        public Builder setCustomView(View r2) {
            BottomSheet.m5694$$Nest$fputcustomView(this.bottomSheet, r2);
            return this;
        }

        public Builder setCustomView(View r2, int r3) {
            BottomSheet.m5694$$Nest$fputcustomView(this.bottomSheet, r2);
            this.bottomSheet.customViewGravity = r3;
            return this;
        }

        public Builder setDelegate(BottomSheetDelegate r2) {
            this.bottomSheet.setDelegate(r2);
            return this;
        }

        public BottomSheet setDimBehind(boolean r1) {
            BottomSheet r0 = this.bottomSheet;
            r0.dimBehind = r1;
            return r0;
        }

        public Builder setItems(CharSequence[] r2, DialogInterface.OnClickListener r3) {
            BottomSheet.m5696$$Nest$fputitems(this.bottomSheet, r2);
            BottomSheet.m5702$$Nest$fputonClickListener(this.bottomSheet, r3);
            return this;
        }

        public Builder setItems(CharSequence[] r2, int[] r3, DialogInterface.OnClickListener r4) {
            BottomSheet.m5696$$Nest$fputitems(this.bottomSheet, r2);
            BottomSheet.m5695$$Nest$fputitemIcons(this.bottomSheet, r3);
            BottomSheet.m5702$$Nest$fputonClickListener(this.bottomSheet, r4);
            return this;
        }

        public Builder setOnPreDismissListener(DialogInterface.OnDismissListener r2) {
            this.bottomSheet.setOnHideListener(r2);
            return this;
        }

        public Builder setTitle(CharSequence r2) {
            return setTitle(r2, false);
        }

        public Builder setTitle(CharSequence r2, boolean r3) {
            BottomSheet.m5708$$Nest$fputtitle(this.bottomSheet, r2);
            BottomSheet.m5691$$Nest$fputbigTitle(this.bottomSheet, r3);
            return this;
        }

        public Builder setTitleMultipleLines(boolean r2) {
            BottomSheet.m5701$$Nest$fputmultipleLinesTitle(this.bottomSheet, r2);
            return this;
        }

        public BottomSheet show() {
            this.bottomSheet.show();
            if (ExteraConfig.getInAppVibration() == true) goto L6;
            VibratorUtils.disableHapticFeedback(this.bottomSheet.containerView);
            VibratorUtils.disableHapticFeedback(BottomSheet.m5669$$Nest$fgetcustomView(this.bottomSheet));
            VibratorUtils.disableHapticFeedback(this.bottomSheet.container);
        L6:
            return this.bottomSheet;
        }
    }

    public class ContainerView extends FrameLayout {
        private boolean allowedSwipeToBack;
        private Paint backgroundPaint;
        private AnimatorSet currentAnimation;
        private final Paint internalBackgroundPaint;
        private int internalPaddingBottom;
        private boolean keyboardChanged;
        private boolean maybeStartTracking;
        private NestedScrollingParentHelper nestedScrollingParentHelper;
        private Rect rect;
        private boolean startedTracking;
        private int startedTrackingPointerId;
        private int startedTrackingX;
        private int startedTrackingY;
        private float swipeBackX;
        final /* synthetic */ BottomSheet this$0;
        private VelocityTracker velocityTracker;
        private float y;

        /* renamed from: org.telegram.ui.ActionBar.BottomSheet$ContainerView$1, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ ContainerView this$1;

            public AnonymousClass1(ContainerView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (ContainerView.m5719$$Nest$fgetcurrentAnimation(this.this$1) != null) goto L5;
            L7:
                NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
                return;
            L5:
                if (ContainerView.m5719$$Nest$fgetcurrentAnimation(this.this$1).equals(r2) == false) goto L7;
                ContainerView.m5721$$Nest$fputcurrentAnimation(this.this$1, null);
                goto L7
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.BottomSheet$ContainerView$2, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ ContainerView this$1;

            public AnonymousClass2(ContainerView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                ContainerView.m5722$$Nest$fputswipeBackX(this.this$1, 0.0f);
                this.this$1.this$0.containerView.setTranslationX(0.0f);
                this.this$1.this$0.container.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.BottomSheet$ContainerView$3, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ ContainerView this$1;

            public AnonymousClass3(ContainerView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                BottomSheet.m5706$$Nest$fputskipDismissAnimation(this.this$1.this$0, true);
                this.this$1.this$0.containerView.setTranslationX(r2.getMeasuredWidth());
                this.this$1.this$0.dismiss();
                this.this$1.this$0.container.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.ActionBar.BottomSheet$ContainerView$4, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass4 extends AnimatorListenerAdapter {
            final /* synthetic */ ContainerView this$1;

            public AnonymousClass4(ContainerView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                this.this$1.this$0.containerView.setTranslationY(0.0f);
                this.this$1.this$0.onContainerViewTranslation();
                BottomSheet r22 = this.this$1.this$0;
                r22.onSmoothContainerViewLayout(r22.containerView.getTranslationY());
                this.this$1.invalidate();
            }
        }

        /* renamed from: $r8$lambda$-bQELLq0lhRf2qF7iPOs7k23XOY, reason: not valid java name */
        public static /* synthetic */ void m5717$r8$lambda$bQELLq0lhRf2qF7iPOs7k23XOY(ContainerView r0, ValueAnimator r1) {
            r0.lambda$processTouchEvent$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$5VpB_QZHUWenFrNS8IqTpvvCDZ4(ContainerView r0, ValueAnimator r1) {
            r0.lambda$processTouchEvent$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$AFgPrtKbfeZdSaUf5Elm9s95d8k(ContainerView r0, ValueAnimator r1) {
            r0.lambda$checkDismiss$0(r1);
        }

        /* renamed from: $r8$lambda$F-gSEbN1hYWSjiLu5d1STrgV6FQ, reason: not valid java name */
        public static /* synthetic */ void m5718$r8$lambda$FgSEbN1hYWSjiLu5d1STrgV6FQ(ContainerView r0, ValueAnimator r1) {
            r0.lambda$onLayout$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$Zu6cW2qa4E9rHzGbJnNi8bRqIP0(ContainerView r0, ValueAnimator r1) {
            r0.lambda$processTouchEvent$2(r1);
        }

        /* renamed from: -$$Nest$fgetcurrentAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatorSet m5719$$Nest$fgetcurrentAnimation(ContainerView r0) {
            return r0.currentAnimation;
        }

        /* renamed from: -$$Nest$fgetinternalPaddingBottom, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5720$$Nest$fgetinternalPaddingBottom(ContainerView r0) {
            return r0.internalPaddingBottom;
        }

        /* renamed from: -$$Nest$fputcurrentAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5721$$Nest$fputcurrentAnimation(ContainerView r0, AnimatorSet r1) {
            r0.currentAnimation = r1;
        }

        /* renamed from: -$$Nest$fputswipeBackX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5722$$Nest$fputswipeBackX(ContainerView r0, float r1) {
            r0.swipeBackX = r1;
        }

        /* renamed from: -$$Nest$mcancelCurrentAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5723$$Nest$mcancelCurrentAnimation(ContainerView r0) {
            r0.cancelCurrentAnimation();
        }

        public ContainerView(BottomSheet r2, Context r3) {
            this.this$0 = r2;
            super(r3);
            this.velocityTracker = null;
            this.startedTrackingPointerId = -1;
            this.maybeStartTracking = false;
            this.startedTracking = false;
            this.currentAnimation = null;
            this.rect = new Rect();
            this.backgroundPaint = new Paint();
            this.y = 0.0f;
            this.swipeBackX = 0.0f;
            this.internalBackgroundPaint = new Paint(1);
            this.nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
            setWillNotDraw(false);
        }

        private void cancelCurrentAnimation() {
            AnimatorSet r0 = this.currentAnimation;
            if (r0 == null) goto L5;
            r0.cancel();
            this.currentAnimation = null;
        L5:
            this.this$0.onSwipeStarts();
        }

        private void checkDismiss(float r10, float r11) {
            if (this.this$0.containerView.getTranslationY() >= AndroidUtilities.getPixelsInCM(0.8f, false)) goto L9;
            if (r11 >= 3500.0f) goto L7;
        L12:
            this.maybeStartTracking = false;
            this.currentAnimation = new AnimatorSet();
            ValueAnimator r112 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r112.addUpdateListener(new BottomSheet$ContainerView$$ExternalSyntheticLambda1(this));
            this.currentAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.this$0.containerView, "translationY", new float[]{0.0f}), r112});
            this.currentAnimation.setDuration((int) ((Math.max(0.0f, r0) / AndroidUtilities.getPixelsInCM(0.8f, false)) * 250.0f));
            this.currentAnimation.setInterpolator(CubicBezierInterpolator.DEFAULT);
            this.currentAnimation.addListener(new AnonymousClass1(this));
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
            this.currentAnimation.start();
            return;
        L7:
            if (Math.abs(r11) < Math.abs(r10)) goto L12;
        L9:
            if (r11 < 0.0f) goto L11;
        L14:
            boolean r102 = BottomSheet.m5666$$Nest$fgetallowCustomAnimation(this.this$0);
            BottomSheet.m5688$$Nest$fputallowCustomAnimation(this.this$0, false);
            BottomSheet.m5709$$Nest$fputuseFastDismiss(this.this$0, true);
            this.this$0.dismiss();
            BottomSheet.m5688$$Nest$fputallowCustomAnimation(this.this$0, r102);
            return;
        L11:
            if (Math.abs(r11) < 3500.0f) goto L14;
            goto L12
        }

        private /* synthetic */ void lambda$checkDismiss$0(ValueAnimator r1) {
            ContainerView r12 = this.this$0.container;
            if (r12 == null) goto L5;
            r12.invalidate();
        L5:
            this.this$0.onContainerViewTranslation();
        }

        private /* synthetic */ void lambda$onLayout$4(ValueAnimator r2) {
            this.this$0.containerView.setTranslationY(((Float) r2.getAnimatedValue()).floatValue());
            this.this$0.onContainerViewTranslation();
            BottomSheet r22 = this.this$0;
            r22.onSmoothContainerViewLayout(r22.containerView.getTranslationY());
            invalidate();
        }

        private /* synthetic */ void lambda$processTouchEvent$1(ValueAnimator r2) {
            float r22 = ((Float) r2.getAnimatedValue()).floatValue();
            this.swipeBackX = r22;
            this.this$0.containerView.setTranslationX(r22);
            this.this$0.container.invalidate();
        }

        private /* synthetic */ void lambda$processTouchEvent$2(ValueAnimator r2) {
            float r22 = ((Float) r2.getAnimatedValue()).floatValue();
            this.swipeBackX = r22;
            this.this$0.containerView.setTranslationX(r22);
            this.this$0.container.invalidate();
        }

        private /* synthetic */ void lambda$processTouchEvent$3(ValueAnimator r3) {
            float r32 = ((Float) r3.getAnimatedValue()).floatValue();
            BottomSheet r2 = this.this$0;
            SheetBackDrawable r0 = r2.backDrawable;
            if (r2.dimBehind == false) goto L5;
            int r22 = (int) (r2.dimBehindAlpha * r32);
        L6:
            r0.setAlpha(r22);
            return;
        L5:
            r22 = 0;
            goto L6
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            BottomSheet r1 = this.this$0;
            float r7 = 1.0f;
            if (r1.containerView != null) goto L5;
        L7:
            int r2 = Build.VERSION.SDK_INT;
            if (r2 < 26) goto L13;
            BottomSheet r3 = this.this$0;
            int r4 = r3.navBarColorKey;
            Paint r5 = this.backgroundPaint;
            if (r4 < 0) goto L12;
            r5.setColor(r3.getThemedColor(r4));
        L14:
            BottomSheet r32 = this.this$0;
            if (r32.drawDoubleNavigationBar == false) goto L20;
            if (r32.shouldOverlayCameraViewOverNavBar() == true) goto L20;
            drawNavigationBar(r9, 1.0f);
        L20:
            if (this.backgroundPaint.getAlpha() >= 255) goto L35;
            BottomSheet r33 = this.this$0;
            if (r33.drawNavigationBar == false) goto L35;
            float r52 = 0.0f;
            if (r33.scrollNavBar == false) goto L26;
        L29:
            r52 = Math.max(0.0f, this.this$0.getBottomInset() - (this.this$0.containerView.getMeasuredHeight() - this.this$0.containerView.getTranslationY()));
        L30:
            BottomSheet r22 = this.this$0;
            if (r22.drawNavigationBar == false) goto L33;
            int r23 = r22.getBottomInset();
        L34:
            r9.save();
            r9.clipRect(this.this$0.containerView.getLeft() + this.this$0.backgroundPaddingLeft, ((getMeasuredHeight() - r23) + r52) - BottomSheet.m5668$$Nest$fgetcurrentPanTranslationY(this.this$0), this.this$0.containerView.getRight() - this.this$0.backgroundPaddingLeft, r52 + getMeasuredHeight(), Region.Op.DIFFERENCE);
            super.dispatchDraw(r9);
            r9.restore();
        L36:
            BottomSheet r24 = this.this$0;
            if (r24.doNotOverlayNavigationBar == true) goto L75;
            if (r24.shouldOverlayCameraViewOverNavBar() == true) goto L44;
            BottomSheet r25 = this.this$0;
            if (r25.drawDoubleNavigationBar == false) goto L43;
            r7 = r25.navigationBarAlpha * 0.7f;
        L43:
            drawNavigationBar(r9, r7);
        L44:
            BottomSheet r26 = this.this$0;
            if (r26.drawNavigationBar == true) goto L47;
        L55:
            BottomSheet r12 = this.this$0;
            if (r12.drawNavigationBar == false) goto L67;
            if (BottomSheet.m5677$$Nest$fgetleftInset(r12) == 0) goto L67;
            if (BottomSheet.m5677$$Nest$fgetleftInset(this.this$0) <= BottomSheet.m5684$$Nest$fgetrightInset(this.this$0)) goto L67;
            BottomSheet r13 = this.this$0;
            if (r13.fullWidth == false) goto L67;
            Point r27 = AndroidUtilities.displaySize;
            if (r27.x <= r27.y) goto L67;
            r9.drawRect(0.0f, r13.containerView.getTranslationY(), this.this$0.containerView.getLeft() + this.this$0.backgroundPaddingLeft, getMeasuredHeight(), this.backgroundPaint);
        L67:
            if ((this.this$0.containerView.getY() + this.this$0.containerView.getMeasuredHeight()) >= getMeasuredHeight()) goto L82;
            Paint r14 = this.backgroundPaint;
            BottomSheet r28 = this.this$0;
            int r34 = r28.behindKeyboardColorKey;
            if (r34 < 0) goto L71;
            int r29 = r28.getThemedColor(r34);
        L72:
            r14.setColor(r29);
            int r15 = this.this$0.containerView.getLeft();
            r9.drawRect(r15 + r2.backgroundPaddingLeft, this.this$0.containerView.getY() + this.this$0.containerView.getMeasuredHeight(), this.this$0.containerView.getRight() - this.this$0.backgroundPaddingLeft, getMeasuredHeight(), this.backgroundPaint);
            return;
        L71:
            r29 = r28.behindKeyboardColor;
            goto L72
        L82:
            return;
        L47:
            if (BottomSheet.m5684$$Nest$fgetrightInset(r26) == 0) goto L55;
            if (BottomSheet.m5684$$Nest$fgetrightInset(this.this$0) <= BottomSheet.m5677$$Nest$fgetleftInset(this.this$0)) goto L55;
            BottomSheet r210 = this.this$0;
            if (r210.fullWidth == false) goto L55;
            Point r35 = AndroidUtilities.displaySize;
            if (r35.x <= r35.y) goto L55;
            int r211 = r210.containerView.getRight();
            r9.drawRect(r211 - r3.backgroundPaddingLeft, this.this$0.containerView.getTranslationY(), this.this$0.containerView.getRight() + BottomSheet.m5684$$Nest$fgetrightInset(this.this$0), getMeasuredHeight(), this.backgroundPaint);
            goto L55
        L75:
            if (((getMeasuredHeight() - this.this$0.containerView.getY()) - this.this$0.containerView.getMeasuredHeight()) <= AndroidUtilities.dp(48.0f)) goto L83;
            Paint r16 = this.backgroundPaint;
            BottomSheet r212 = this.this$0;
            int r36 = r212.behindKeyboardColorKey;
            if (r36 < 0) goto L79;
            int r213 = r212.getThemedColor(r36);
        L80:
            r16.setColor(r213);
            int r17 = this.this$0.containerView.getLeft();
            r9.drawRect(r17 + r2.backgroundPaddingLeft, this.this$0.containerView.getY() + this.this$0.containerView.getMeasuredHeight(), this.this$0.containerView.getRight() - this.this$0.backgroundPaddingLeft, getMeasuredHeight(), this.backgroundPaint);
            return;
        L79:
            r213 = r212.behindKeyboardColor;
            goto L80
        L83:
            return;
        L33:
            r23 = 0;
            goto L34
        L26:
            if (r2 < 29) goto L30;
            if (BottomSheet.m5710$$Nest$mgetAdditionalMandatoryOffsets(r33) <= 0) goto L30;
        L35:
            super.dispatchDraw(r9);
            goto L36
        L12:
            r5.setColor(r3.navBarColor);
            goto L14
        L13:
            this.backgroundPaint.setColor(-16777216);
            goto L14
        L5:
            if (this.internalPaddingBottom <= 0) goto L7;
            this.internalBackgroundPaint.setColor(BottomSheet.m5674$$Nest$fgetinternalBackgroundColor(r1));
            r9.drawRect(0.0f, ((getMeasuredHeight() - this.internalPaddingBottom) + this.this$0.containerView.getTranslationY()) - 1.0f, getMeasuredWidth(), getMeasuredHeight() + this.this$0.containerView.getTranslationY(), this.internalBackgroundPaint);
            goto L7
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if ((r3 instanceof CameraView) == false) goto L10;
            if (this.this$0.shouldOverlayCameraViewOverNavBar() == false) goto L8;
            drawNavigationBar(r2, 1.0f);
        L8:
            return super.drawChild(r2, r3, r4);
        L10:
            return super.drawChild(r2, r3, r4);
        }

        public void drawNavigationBar(Canvas r22, float r23) {
            int r1 = Build.VERSION.SDK_INT;
            if (r1 < 26) goto L8;
            BottomSheet r2 = this.this$0;
            int r3 = r2.navBarColorKey;
            Paint r4 = this.backgroundPaint;
            if (r3 < 0) goto L7;
            r4.setColor(r2.getThemedColor(r3));
        L10:
            if (BottomSheet.m5687$$Nest$fgettransitionFromRight(this.this$0) == true) goto L12;
        L14:
            BottomSheet r24 = this.this$0;
            float r42 = 0.0f;
            if (r24.drawNavigationBar == false) goto L19;
            if (BottomSheet.m5667$$Nest$fgetbottomInset(r24) == 0) goto L19;
        L20:
            BottomSheet r25 = this.this$0;
            if (r25.drawNavigationBar == false) goto L23;
            int r26 = r25.getBottomInset();
        L24:
            BottomSheet r32 = this.this$0;
            if (r32.scrollNavBar == false) goto L27;
        L32:
            BottomSheet r12 = this.this$0;
            if (r12.drawDoubleNavigationBar == false) goto L35;
            float r13 = Math.max(0.0f, Math.min(r26 - BottomSheet.m5668$$Nest$fgetcurrentPanTranslationY(r12), this.this$0.containerView.getTranslationY()));
        L36:
            int r33 = this.backgroundPaint.getAlpha();
            if (BottomSheet.m5687$$Nest$fgettransitionFromRight(this.this$0) == false) goto L39;
            float r5 = this.this$0.containerView.getAlpha() * r23;
        L40:
            boolean r6 = BottomSheet.m5687$$Nest$fgettransitionFromRight(this.this$0);
            BottomSheet r7 = this.this$0;
            if (r6 == false) goto L43;
            int r62 = (int) r7.containerView.getX();
        L45:
            if (r5 >= 1.0f) goto L47;
            this.backgroundPaint.setAlpha((int) (r33 * r5));
        L47:
            r22.drawRect(this.this$0.backgroundPaddingLeft + r62, ((getMeasuredHeight() - r26) + r13) - BottomSheet.m5668$$Nest$fgetcurrentPanTranslationY(this.this$0), this.this$0.containerView.getRight() - this.this$0.backgroundPaddingLeft, getMeasuredHeight() + r13, this.backgroundPaint);
            this.backgroundPaint.setAlpha(r33);
            if (BottomSheet.m5682$$Nest$fgetoverlayDrawNavBarColor(this.this$0) == 0) goto L59;
            this.backgroundPaint.setColor(BottomSheet.m5682$$Nest$fgetoverlayDrawNavBarColor(this.this$0));
            int r34 = this.backgroundPaint.getAlpha();
            float r8 = AndroidUtilities.getNavigationBarThirdButtonsFactor(r26);
            if (r5 >= 1.0f) goto L52;
            this.backgroundPaint.setAlpha((int) ((r34 * r5) * r8));
        L54:
            if (this.backgroundPaint.getAlpha() <= 0) goto L56;
            r22.drawRect(r62 + this.this$0.backgroundPaddingLeft, ((getMeasuredHeight() - r26) + r42) - BottomSheet.m5668$$Nest$fgetcurrentPanTranslationY(this.this$0), this.this$0.containerView.getRight() - this.this$0.backgroundPaddingLeft, getMeasuredHeight() + r42, this.backgroundPaint);
        L56:
            this.backgroundPaint.setAlpha(r34);
            return;
        L52:
            r42 = r13;
            goto L54
        L59:
            return;
        L43:
            r62 = r7.containerView.getLeft();
            goto L45
        L39:
            r5 = r23;
            goto L40
        L35:
            r13 = Math.max(0.0f, this.this$0.getBottomInset() - (r12.containerView.getMeasuredHeight() - this.this$0.containerView.getTranslationY()));
            goto L36
        L27:
            if (r1 >= 29) goto L29;
        L31:
            r13 = 0.0f;
            goto L36
        L29:
            if (BottomSheet.m5710$$Nest$mgetAdditionalMandatoryOffsets(r32) <= 0) goto L31;
        L23:
            r26 = 0;
        L19:
            if (BottomSheet.m5668$$Nest$fgetcurrentPanTranslationY(this.this$0) != 0.0f) goto L20;
            return;
        L12:
            if (this.this$0.containerView.getVisibility() == 0) goto L14;
            return;
        L7:
            r4.setColor(r2.navBarColor);
            goto L10
        L8:
            this.backgroundPaint.setColor(-16777216);
            goto L10
        }

        @Override // android.view.ViewGroup
        public int getNestedScrollAxes() {
            return this.nestedScrollingParentHelper.getNestedScrollAxes();
        }

        @Override // android.view.View
        public boolean hasOverlappingRendering() {
            return false;
        }

        @Override // android.view.View
        public void onDraw(Canvas r18) {
            int r3 = 0;
            if (this.backgroundPaint.getAlpha() >= 255) goto L18;
            BottomSheet r1 = this.this$0;
            if (r1.drawNavigationBar == false) goto L18;
            float r4 = 0.0f;
            if (r1.scrollNavBar == false) goto L9;
        L12:
            r4 = Math.max(0.0f, this.this$0.getBottomInset() - (this.this$0.containerView.getMeasuredHeight() - this.this$0.containerView.getTranslationY()));
        L13:
            BottomSheet r12 = this.this$0;
            if (r12.drawNavigationBar == false) goto L16;
            int r13 = r12.getBottomInset();
        L17:
            r18.save();
            r18.clipRect(this.this$0.containerView.getLeft() + this.this$0.backgroundPaddingLeft, ((getMeasuredHeight() - r13) + r4) - BottomSheet.m5668$$Nest$fgetcurrentPanTranslationY(this.this$0), this.this$0.containerView.getRight() - this.this$0.backgroundPaddingLeft, getMeasuredHeight() + r4, Region.Op.DIFFERENCE);
            boolean r14 = true;
        L19:
            super.onDraw(r18);
            BottomSheet r2 = this.this$0;
            if (r2.drawNavigationBar == true) goto L22;
        L37:
            this.this$0.onContainerDraw(r18);
            if (r14 == false) goto L41;
            r18.restore();
            return;
        L41:
            return;
        L22:
            if (BottomSheet.m5675$$Nest$fgetlastInsets(r2) == null) goto L37;
            BottomSheet r22 = this.this$0;
            if (r22.keyboardHeight == 0) goto L37;
            Paint r42 = this.backgroundPaint;
            int r5 = r22.behindKeyboardColorKey;
            if (r5 < 0) goto L28;
            int r23 = r22.getThemedColor(r5);
        L29:
            r42.setColor(r23);
            float r122 = this.this$0.containerView.getLeft() + this.this$0.backgroundPaddingLeft;
            int r24 = getMeasuredHeight();
            BottomSheet r43 = this.this$0;
            int r25 = r24 - r43.keyboardHeight;
            if (r43.drawNavigationBar == false) goto L32;
            int r44 = r43.getBottomInset();
        L33:
            float r132 = r25 - r44;
            float r142 = this.this$0.containerView.getRight() - this.this$0.backgroundPaddingLeft;
            int r26 = getMeasuredHeight();
            BottomSheet r45 = this.this$0;
            if (r45.drawNavigationBar == false) goto L36;
            r3 = r45.getBottomInset();
        L36:
            r18.drawRect(r122, r132, r142, r26 - r3, this.backgroundPaint);
            goto L37
        L32:
            r44 = 0;
            goto L33
        L28:
            r23 = r22.behindKeyboardColor;
            goto L29
        L16:
            r13 = 0;
            goto L17
        L9:
            if (Build.VERSION.SDK_INT < 29) goto L13;
            if (BottomSheet.m5710$$Nest$mgetAdditionalMandatoryOffsets(r1) <= 0) goto L13;
        L18:
            r14 = false;
            goto L19
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (BottomSheet.m5683$$Nest$fgetpredictiveBackAnimationInProgress(this.this$0) == false) goto L6;
            return true;
        L6:
            if (this.this$0.canDismissWithSwipe() == true) goto L13;
            if (this.this$0.canSwipeToBack(r3) == true) goto L13;
            return super.onInterceptTouchEvent(r3);
        L13:
            return processTouchEvent(r3, true);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r17, int r18, int r19, int r20, int r21) {
            int r1 = r18;
            int r4 = r19;
            int r2 = r20;
            int r7 = r21 - this.internalPaddingBottom;
            this.this$0.onContainerLayout(r1, r4, r2, r7);
            BottomSheet r3 = this.this$0;
            BottomSheet.m5699$$Nest$fputlayoutCount(r3, BottomSheet.m5676$$Nest$fgetlayoutCount(r3) - 1);
            ViewGroup r32 = this.this$0.containerView;
            if (r32 == null) goto L50;
            int r5 = (r7 - r4) - r32.getMeasuredHeight();
            if (BottomSheet.m5675$$Nest$fgetlastInsets(this.this$0) == null) goto L18;
            r1 = r1 + this.this$0.getLeftInset();
            r2 = r2 - this.this$0.getRightInset();
            BottomSheet r33 = this.this$0;
            if (r33.useSmoothKeyboard == false) goto L10;
            r5 = 0;
            goto L18
        L10:
            if (r33.occupyNavigationBar == true) goto L18;
            float r52 = r5;
            float r34 = BottomSheet.m5675$$Nest$fgetlastInsets(r33).getSystemWindowInsetBottom() * (1.0f - BottomSheet.m5673$$Nest$fgethideSystemVerticalInsetsProgress(this.this$0));
            BottomSheet r6 = this.this$0;
            if (r6.drawNavigationBar == false) goto L14;
            int r62 = 0;
        L15:
            r5 = (int) (r52 - (r34 - r62));
            if (Build.VERSION.SDK_INT < 29) goto L18;
            r5 = r5 - BottomSheet.m5710$$Nest$mgetAdditionalMandatoryOffsets(this.this$0);
            goto L18
        L14:
            r62 = r6.getBottomInset();
        L18:
            int r35 = ((r2 - r1) - this.this$0.containerView.getMeasuredWidth()) / 2;
            if (BottomSheet.m5675$$Nest$fgetlastInsets(this.this$0) == null) goto L21;
            r35 = r35 + this.this$0.getLeftInset();
        L21:
            BottomSheet r63 = this.this$0;
            if (r63.smoothKeyboardAnimationEnabled == false) goto L37;
            if (r63.startAnimationRunnable != null) goto L37;
            if (this.keyboardChanged == false) goto L37;
            if (BottomSheet.m5672$$Nest$fgetdismissed(r63) == true) goto L37;
            BottomSheet r64 = this.this$0;
            boolean r11 = r64.smoothKeyboardByBottom;
            ViewGroup r65 = r64.containerView;
            if (r11 == false) goto L35;
            if (r65.getBottom() == (this.this$0.containerView.getMeasuredHeight() + r5)) goto L37;
        L40:
            BottomSheet r66 = this.this$0;
            ViewGroup r112 = r66.containerView;
            if (r66.smoothKeyboardByBottom == false) goto L44;
            int r67 = r112.getBottom() - (this.this$0.containerView.getMeasuredHeight() + r5);
        L45:
            r112.setTranslationY(r67);
            this.this$0.onContainerViewTranslation();
            BottomSheet r68 = this.this$0;
            r68.onSmoothContainerViewLayout(r68.containerView.getTranslationY());
            ValueAnimator r69 = this.this$0.keyboardContentAnimator;
            if (r69 == null) goto L48;
            r69.cancel();
        L48:
            BottomSheet r610 = this.this$0;
            r610.keyboardContentAnimator = ValueAnimator.ofFloat(new float[]{r610.containerView.getTranslationY(), 0.0f});
            this.this$0.keyboardContentAnimator.addUpdateListener(new BottomSheet$ContainerView$$ExternalSyntheticLambda0(this));
            this.this$0.keyboardContentAnimator.addListener(new AnonymousClass4(this));
            this.this$0.keyboardContentAnimator.setDuration(250).setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
            this.this$0.keyboardContentAnimator.start();
            BottomSheet.m5707$$Nest$fputsmoothContainerViewLayoutUntil(this.this$0, -1);
        L49:
            ViewGroup r611 = this.this$0.containerView;
            r611.layout(r35, r5, r611.getMeasuredWidth() + r35, this.this$0.containerView.getMeasuredHeight() + r5);
            goto L50
        L44:
            r67 = r112.getTop() - r5;
            goto L45
        L35:
            if (r65.getTop() != r5) goto L40;
        L37:
            if (BottomSheet.m5685$$Nest$fgetsmoothContainerViewLayoutUntil(this.this$0) <= 0) goto L49;
            if (System.currentTimeMillis() >= BottomSheet.m5685$$Nest$fgetsmoothContainerViewLayoutUntil(this.this$0)) goto L49;
        L50:
            int r36 = r1;
            int r53 = r2;
            int r113 = getChildCount();
            int r12 = 0;
        L51:
            if (r12 >= r113) goto L88;
            View r22 = getChildAt(r12);
            if (r22.getVisibility() == 8) goto L86;
            BottomSheet r13 = this.this$0;
            if (r22 == r13.containerView) goto L86;
            if (r13.drawNavigationBar == false) goto L60;
            int r612 = r13.getBottomInset();
        L62:
            if (r13.onCustomLayout(r22, r36, r4, r53, r7 - r612) == true) goto L86;
            FrameLayout.LayoutParams r14 = (FrameLayout.LayoutParams) r22.getLayoutParams();
            int r42 = r22.getMeasuredWidth();
            int r613 = r22.getMeasuredHeight();
            int r132 = r14.gravity;
            if (r132 != (-1)) goto L66;
            r132 = 51;
        L66:
            int r142 = r132 & 112;
            int r133 = r132 & 7;
            if (r133 != 1) goto L69;
            int r134 = (((r53 - r36) - r42) / 2) + r14.leftMargin;
            int r15 = r14.rightMargin;
        L72:
            int r135 = r134 - r15;
        L75:
            if (r142 != 16) goto L77;
            int r143 = (((r7 - r19) - r613) / 2) + r14.topMargin;
            int r16 = r14.bottomMargin;
        L80:
            int r110 = r143 - r16;
        L83:
            if (BottomSheet.m5675$$Nest$fgetlastInsets(this.this$0) == null) goto L85;
            r135 = r135 + this.this$0.getLeftInset();
        L85:
            r22.layout(r135, r110, r42 + r135, r613 + r110);
            goto L86
        L77:
            if (r142 == 80) goto L79;
            r110 = r14.topMargin;
            goto L83
        L79:
            r143 = (r7 - r19) - r613;
            r16 = r14.bottomMargin;
            goto L80
        L69:
            if (r133 == 5) goto L71;
            r135 = r14.leftMargin;
            goto L75
        L71:
            r134 = r53 - r42;
            r15 = r14.rightMargin;
            goto L72
        L60:
            r612 = 0;
        L86:
            r12 = r12 + 1;
            r4 = r19;
            goto L51
        L88:
            if (BottomSheet.m5676$$Nest$fgetlayoutCount(this.this$0) != 0) goto L94;
            BottomSheet r111 = this.this$0;
            Runnable r23 = r111.startAnimationRunnable;
            if (r23 == null) goto L94;
            if (r111.waitingKeyboard == true) goto L94;
            AndroidUtilities.cancelRunOnUIThread(r23);
            this.this$0.startAnimationRunnable.run();
            this.this$0.startAnimationRunnable = null;
        L94:
            BottomSheet r114 = this.this$0;
            if (r114.waitingKeyboard == true) goto L97;
        L102:
            this.keyboardChanged = false;
            return;
        L97:
            if (r114.keyboardVisible == false) goto L102;
            Runnable r115 = r114.startAnimationRunnable;
            if (r115 == null) goto L101;
            AndroidUtilities.cancelRunOnUIThread(r115);
            this.this$0.startAnimationRunnable.run();
        L101:
            this.this$0.waitingKeyboard = false;
            goto L102
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r12, int r13) {
            int r1 = View.MeasureSpec.getSize(r12);
            int r2 = View.MeasureSpec.getSize(r13);
            View r3 = getRootView();
            getWindowVisibleDisplayFrame(this.rect);
            BottomSheet r4 = this.this$0;
            int r5 = r4.keyboardHeight;
            Rect r6 = this.rect;
            int r10 = 0;
            if (r6.bottom != 0) goto L5;
        L15:
            r4.keyboardHeight = 0;
        L16:
            BottomSheet r32 = this.this$0;
            int r42 = r32.keyboardHeight;
            boolean r62 = true;
            if (r5 == r42) goto L19;
            this.keyboardChanged = true;
        L19:
            boolean r52 = r32.keyboardVisible;
            if (r42 <= AndroidUtilities.dp(20.0f)) goto L22;
            boolean r43 = true;
        L23:
            r32.keyboardVisible = r43;
            BottomSheet r33 = this.this$0;
            if (r52 == r33.keyboardVisible) goto L29;
            if (Build.VERSION.SDK_INT < 34) goto L29;
            BottomSheet.m5714$$Nest$mupdateBackCallbackState(r33);
        L29:
            if (BottomSheet.m5675$$Nest$fgetlastInsets(this.this$0) == null) goto L47;
            BottomSheet r34 = this.this$0;
            BottomSheet.m5692$$Nest$fputbottomInset(r34, BottomSheet.m5675$$Nest$fgetlastInsets(r34).getSystemWindowInsetBottom());
            BottomSheet r35 = this.this$0;
            BottomSheet.m5700$$Nest$fputleftInset(r35, BottomSheet.m5675$$Nest$fgetlastInsets(r35).getSystemWindowInsetLeft());
            BottomSheet r36 = this.this$0;
            BottomSheet.m5704$$Nest$fputrightInset(r36, BottomSheet.m5675$$Nest$fgetlastInsets(r36).getSystemWindowInsetRight());
            if (Build.VERSION.SDK_INT < 29) goto L33;
            BottomSheet r37 = this.this$0;
            BottomSheet.m5692$$Nest$fputbottomInset(r37, BottomSheet.m5667$$Nest$fgetbottomInset(r37) + BottomSheet.m5710$$Nest$mgetAdditionalMandatoryOffsets(this.this$0));
        L33:
            BottomSheet r38 = this.this$0;
            if (r38.keyboardVisible == false) goto L40;
            Rect r53 = this.rect;
            if (r53.bottom == 0) goto L40;
            if (r53.top == 0) goto L40;
            BottomSheet.m5692$$Nest$fputbottomInset(r38, BottomSheet.m5667$$Nest$fgetbottomInset(r38) - this.this$0.keyboardHeight);
        L40:
            BottomSheet r39 = this.this$0;
            if (r39.drawNavigationBar == true) goto L47;
            if (r39.occupyNavigationBar == true) goto L47;
            if (r39.occupyNavigationBarWithoutKeyboard == true) goto L47;
            int r310 = r2 - r39.getBottomInset();
        L48:
            this.internalPaddingBottom = r2 - r310;
            setMeasuredDimension(r1, r2);
            BottomSheet r311 = this.this$0;
            r311.navigationBarHeight = 0;
            if (BottomSheet.m5675$$Nest$fgetlastInsets(r311) != null) goto L51;
        L61:
            int r7 = r2;
            if (BottomSheet.m5675$$Nest$fgetlastInsets(this.this$0) == null) goto L64;
            r1 = r1 - (this.this$0.getRightInset() + this.this$0.getLeftInset());
        L64:
            int r8 = r1;
            BottomSheet r14 = this.this$0;
            if (r8 < r7) goto L68;
            r62 = false;
        L68:
            r14.isPortrait = r62;
            ViewGroup r22 = r14.containerView;
            if (r22 != null) goto L71;
        L78:
            int r9 = getChildCount();
        L79:
            if (r10 >= r9) goto L92;
            View r15 = getChildAt(r10);
            if (r15.getVisibility() == 8) goto L91;
            BottomSheet r23 = this.this$0;
            if (r15 == r23.containerView) goto L91;
            if ((r15 instanceof ItemOptions.DimView) == false) goto L89;
            measureChildWithMargins(r15, View.MeasureSpec.makeMeasureSpec(r8, TLObject.FLAG_30), 0, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r13), TLObject.FLAG_30), 0);
            goto L91
        L89:
            if (r23.onCustomMeasure(r15, r8, r7) == true) goto L91;
            measureChildWithMargins(r15, View.MeasureSpec.makeMeasureSpec(r8, TLObject.FLAG_30), 0, View.MeasureSpec.makeMeasureSpec(r7, TLObject.FLAG_30), 0);
        L91:
            r10 = r10 + 1;
            goto L79
        L92:
            return;
        L71:
            if (r14.fullWidth == false) goto L73;
            r22.measure(View.MeasureSpec.makeMeasureSpec((r14.backgroundPaddingLeft * 2) + r8, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r7, Integer.MIN_VALUE));
            goto L78
        L73:
            if (AndroidUtilities.isTablet() == false) goto L75;
            float r16 = AndroidUtilities.dp(500.0f);
            Point r24 = AndroidUtilities.displaySize;
            int r17 = View.MeasureSpec.makeMeasureSpec(((int) Math.min(r16, Math.min(r24.x, r24.y) * 0.8f)) + (this.this$0.backgroundPaddingLeft * 2), TLObject.FLAG_30);
        L76:
            this.this$0.containerView.measure(r17, View.MeasureSpec.makeMeasureSpec(r7, Integer.MIN_VALUE));
            goto L78
        L75:
            BottomSheet r18 = this.this$0;
            r17 = View.MeasureSpec.makeMeasureSpec(r18.getBottomSheetWidth(r18.isPortrait, r8, r7) + (this.this$0.backgroundPaddingLeft * 2), TLObject.FLAG_30);
            goto L76
        L51:
            if (this.this$0.occupyNavigationBar == true) goto L61;
            int r312 = (int) (BottomSheet.m5675$$Nest$fgetlastInsets(r3).getSystemWindowInsetBottom() * (1.0f - BottomSheet.m5673$$Nest$fgethideSystemVerticalInsetsProgress(this.this$0)));
            if (Build.VERSION.SDK_INT < 29) goto L56;
            r312 = r312 + BottomSheet.m5710$$Nest$mgetAdditionalMandatoryOffsets(this.this$0);
        L56:
            if (this.this$0.occupyNavigationBarWithoutKeyboard == true) goto L58;
        L59:
            r2 = r2 - r312;
        L60:
            this.this$0.navigationBarHeight = Math.min(r312, AndroidUtilities.navigationBarHeight);
            goto L61
        L58:
            if (r312 <= (AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(10.0f))) goto L60;
        L47:
            r310 = r2;
            goto L48
        L22:
            r43 = false;
            goto L23
        L5:
            if (r6.top == 0) goto L15;
            float r44 = r3.getHeight();
            if (this.rect.top == 0) goto L9;
            float r63 = AndroidUtilities.statusBarHeight * (1.0f - BottomSheet.m5673$$Nest$fgethideSystemVerticalInsetsProgress(this.this$0));
        L10:
            int r313 = (int) ((r44 - r63) - (AndroidUtilities.getViewInset(r3) * (1.0f - BottomSheet.m5673$$Nest$fgethideSystemVerticalInsetsProgress(this.this$0))));
            BottomSheet r45 = this.this$0;
            Rect r64 = this.rect;
            r45.keyboardHeight = Math.max(0, r313 - (r64.bottom - r64.top));
            int r314 = this.this$0.keyboardHeight;
            int r46 = AndroidUtilities.dp(20.0f);
            BottomSheet r65 = this.this$0;
            if (r314 >= r46) goto L13;
            r65.keyboardHeight = 0;
        L14:
            BottomSheet r315 = this.this$0;
            BottomSheet.m5692$$Nest$fputbottomInset(r315, BottomSheet.m5667$$Nest$fgetbottomInset(r315) - this.this$0.keyboardHeight);
            goto L16
        L13:
            BottomSheet.m5698$$Nest$fputlastKeyboardHeight(r65, r65.keyboardHeight);
            goto L14
        L9:
            r63 = 0.0f;
            goto L10
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public boolean onNestedFling(View r1, float r2, float r3, boolean r4) {
            return false;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public boolean onNestedPreFling(View r1, float r2, float r3) {
            return false;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void onNestedPreScroll(View r2, int r3, int r4, int[] r5) {
            if (BottomSheet.m5672$$Nest$fgetdismissed(this.this$0) == false) goto L5;
            return;
        L5:
            if (this.this$0.allowNestedScroll == false) goto L19;
            cancelCurrentAnimation();
            float r22 = this.this$0.containerView.getTranslationY();
            float r32 = 0.0f;
            if (r22 <= 0.0f) goto L17;
            if (r4 <= 0) goto L18;
            float r23 = r22 - r4;
            r5[1] = r4;
            if (r23 < 0.0f) goto L14;
            r32 = r23;
        L14:
            this.this$0.containerView.setTranslationY(r32);
            this.this$0.onContainerViewTranslation();
            this.this$0.container.invalidate();
            return;
        L18:
            return;
        L17:
            return;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5) {
            if (BottomSheet.m5672$$Nest$fgetdismissed(this.this$0) == false) goto L5;
            return;
        L5:
            if (this.this$0.allowNestedScroll == false) goto L16;
            cancelCurrentAnimation();
            if (r5 == 0) goto L15;
            float r12 = this.this$0.containerView.getTranslationY() - r5;
            if (r12 >= 0.0f) goto L12;
            r12 = 0.0f;
        L12:
            this.this$0.containerView.setTranslationY(r12);
            this.this$0.onContainerViewTranslation();
            this.this$0.container.invalidate();
            return;
        L15:
            return;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void onNestedScrollAccepted(View r2, View r3, int r4) {
            this.nestedScrollingParentHelper.onNestedScrollAccepted(r2, r3, r4);
            if (BottomSheet.m5672$$Nest$fgetdismissed(this.this$0) == false) goto L5;
            return;
        L5:
            if (this.this$0.allowNestedScroll == false) goto L10;
            cancelCurrentAnimation();
            return;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public boolean onStartNestedScroll(View r2, View r3, int r4) {
            BottomSheet r32 = this.this$0;
            View r0 = r32.nestedScrollChild;
            if (r0 == null) goto L6;
            if (r2 == r0) goto L6;
            return false;
        L6:
            if (BottomSheet.m5672$$Nest$fgetdismissed(r32) == true) goto L17;
            BottomSheet r1 = this.this$0;
            if (r1.allowNestedScroll == true) goto L10;
            return false;
        L10:
            if (r4 == 2) goto L12;
            return false;
        L12:
            if (r1.canDismissWithSwipe() == true) goto L20;
            return true;
        L20:
            return false;
        L17:
            return false;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void onStopNestedScroll(View r2) {
            this.nestedScrollingParentHelper.onStopNestedScroll(r2);
            if (BottomSheet.m5672$$Nest$fgetdismissed(this.this$0) == false) goto L5;
            return;
        L5:
            if (this.this$0.allowNestedScroll == false) goto L10;
            checkDismiss(0.0f, 0.0f);
            return;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (BottomSheet.m5683$$Nest$fgetpredictiveBackAnimationInProgress(this.this$0) == false) goto L7;
            return true;
        L7:
            return processTouchEvent(r2, false);
        }

        public boolean processTouchEvent(MotionEvent r11, boolean r12) {
            if (BottomSheet.m5672$$Nest$fgetdismissed(this.this$0) == false) goto L6;
            return false;
        L6:
            if (this.this$0.onContainerTouchEvent(r11) == false) goto L9;
            return true;
        L9:
            if (this.this$0.canSwipeToBack(r11) == false) goto L11;
        L85:
            if (r11 != null) goto L87;
        L97:
            if (r11 != null) goto L99;
        L121:
            if (r11 == null) goto L131;
            if (r11.getPointerId(0) == this.startedTrackingPointerId) goto L125;
        L142:
            if (r12 == true) goto L146;
            if (this.maybeStartTracking == false) goto L146;
        L153:
            return true;
        L146:
            if (this.startedTracking == true) goto L153;
            if (this.this$0.canDismissWithSwipe() == false) goto L150;
        L152:
            return false;
        L150:
            if (this.this$0.canSwipeToBack(r11) == true) goto L152;
        L125:
            if (r11.getAction() == 3) goto L131;
            if (r11.getAction() == 1) goto L131;
            if (r11.getAction() != 6) goto L142;
        L131:
            if (this.velocityTracker != null) goto L133;
            this.velocityTracker = VelocityTracker.obtain();
        L133:
            float r0 = this.velocityTracker.getXVelocity();
            float r4 = this.velocityTracker.getYVelocity();
            if (this.swipeBackX < (this.this$0.containerView.getMeasuredWidth() / 3.0f)) goto L136;
        L140:
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.swipeBackX, getMeasuredWidth()});
            r02.addUpdateListener(new BottomSheet$ContainerView$$ExternalSyntheticLambda3(this));
            r02.addListener(new AnonymousClass3(this));
            CubicBezierInterpolator r42 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r02.setInterpolator(r42);
            r02.setDuration(320);
            r02.start();
            ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
            r03.addUpdateListener(new BottomSheet$ContainerView$$ExternalSyntheticLambda4(this));
            r03.setInterpolator(r42);
            r03.setDuration(320);
            r03.start();
        L141:
            this.maybeStartTracking = false;
            this.startedTracking = false;
            this.startedTrackingPointerId = -1;
            this.allowedSwipeToBack = false;
            goto L142
        L136:
            if (r0 >= 3500.0f) goto L138;
        L139:
            float r04 = Math.max(this.swipeBackX, 0.0f);
            this.swipeBackX = r04;
            ValueAnimator r05 = ValueAnimator.ofFloat(new float[]{r04, 0.0f});
            r05.addUpdateListener(new BottomSheet$ContainerView$$ExternalSyntheticLambda2(this));
            r05.addListener(new AnonymousClass2(this));
            r05.setInterpolator(CubicBezierInterpolator.DEFAULT);
            r05.setDuration(220);
            r05.start();
            goto L141
        L138:
            if (r0 >= r4) goto L140;
        L99:
            if (r11.getAction() != 2) goto L121;
            if (r11.getPointerId(0) != this.startedTrackingPointerId) goto L121;
            float r06 = r11.getX() - this.startedTrackingX;
            float r3 = r11.getY() - this.startedTrackingY;
            if (this.velocityTracker != null) goto L105;
            this.velocityTracker = VelocityTracker.obtain();
        L105:
            this.velocityTracker.addMovement(r11);
            if (BottomSheet.m5670$$Nest$fgetdisableScroll(this.this$0) == true) goto L119;
            if (this.maybeStartTracking == false) goto L119;
            if (this.startedTracking == true) goto L119;
            if (r06 <= 0.0f) goto L119;
            if ((r06 / 3.0f) <= Math.abs(r3)) goto L119;
            if (Math.abs(r06) < BottomSheet.m5686$$Nest$fgettouchSlop(this.this$0)) goto L119;
            this.startedTrackingX = (int) r11.getX();
            this.maybeStartTracking = false;
            this.startedTracking = true;
        L119:
            if (this.startedTracking == false) goto L142;
            float r32 = this.swipeBackX + r06;
            this.swipeBackX = r32;
            this.this$0.containerView.setTranslationX(Math.max(r32, 0.0f));
            this.startedTrackingX = (int) r11.getX();
            this.this$0.container.invalidate();
            goto L142
        L87:
            if (r11.getAction() == 0) goto L91;
            if (r11.getAction() != 2) goto L97;
        L91:
            if (this.startedTracking == true) goto L97;
            if (this.maybeStartTracking == true) goto L97;
            if (r11.getPointerCount() != 1) goto L97;
            this.allowedSwipeToBack = true;
            this.startedTrackingX = (int) r11.getX();
            this.startedTrackingY = (int) r11.getY();
            this.startedTrackingPointerId = r11.getPointerId(0);
            this.maybeStartTracking = true;
            cancelCurrentAnimation();
            goto L142
        L11:
            if (this.allowedSwipeToBack == true) goto L85;
            if (this.this$0.canDismissWithTouchOutside() == false) goto L34;
            if (r11 == null) goto L34;
            if (r11.getAction() == 0) goto L21;
            if (r11.getAction() != 2) goto L34;
        L21:
            if (this.startedTracking == true) goto L34;
            if (this.maybeStartTracking == true) goto L34;
            if (r11.getPointerCount() != 1) goto L34;
            this.startedTrackingX = (int) r11.getX();
            int r07 = (int) r11.getY();
            this.startedTrackingY = r07;
            boolean r08 = this.this$0.isTouchOutside(this.startedTrackingX, r07);
            BottomSheet r33 = this.this$0;
            if (r08 == false) goto L30;
            r33.onDismissWithTouchOutside();
            return true;
        L30:
            r33.onScrollUpBegin(this.y);
            this.startedTrackingPointerId = r11.getPointerId(0);
            this.maybeStartTracking = true;
            cancelCurrentAnimation();
            VelocityTracker r09 = this.velocityTracker;
            if (r09 == null) goto L142;
            r09.clear();
        L34:
            if (this.this$0.canDismissWithSwipe() == false) goto L62;
            if (r11 == null) goto L62;
            if (r11.getAction() != 2) goto L62;
            if (r11.getPointerId(0) != this.startedTrackingPointerId) goto L62;
            if (this.velocityTracker != null) goto L43;
            this.velocityTracker = VelocityTracker.obtain();
        L43:
            float r010 = Math.abs((int) (r11.getX() - this.startedTrackingX));
            float r34 = ((int) r11.getY()) - this.startedTrackingY;
            boolean r43 = this.this$0.onScrollUp(this.y + r34);
            this.velocityTracker.addMovement(r11);
            if (BottomSheet.m5670$$Nest$fgetdisableScroll(this.this$0) == true) goto L57;
            if (this.maybeStartTracking == false) goto L57;
            if (this.startedTracking == true) goto L57;
            if (r34 <= 0.0f) goto L57;
            if ((r34 / 3.0f) <= Math.abs(r010)) goto L57;
            if (Math.abs(r34) < BottomSheet.m5686$$Nest$fgettouchSlop(this.this$0)) goto L57;
            this.startedTrackingY = (int) r11.getY();
            this.maybeStartTracking = false;
            this.startedTracking = true;
            requestDisallowInterceptTouchEvent(true);
        L57:
            if (this.startedTracking == false) goto L142;
            float r011 = this.y + r34;
            this.y = r011;
            if (r43 == true) goto L61;
            this.y = Math.max(r011, 0.0f);
        L61:
            this.this$0.containerView.setTranslationY(Math.max(this.y, 0.0f));
            this.this$0.onContainerViewTranslation();
            this.startedTrackingY = (int) r11.getY();
            this.this$0.container.invalidate();
        L62:
            if (r11 == null) goto L72;
            if (r11.getPointerId(0) != this.startedTrackingPointerId) goto L142;
            if (r11.getAction() == 3) goto L72;
            if (r11.getAction() == 1) goto L72;
            if (r11.getAction() != 6) goto L142;
        L72:
            if (this.velocityTracker != null) goto L74;
            this.velocityTracker = VelocityTracker.obtain();
        L74:
            this.velocityTracker.computeCurrentVelocity(1000);
            this.this$0.onScrollUpEnd(this.y);
            if (this.startedTracking == false) goto L77;
        L80:
            checkDismiss(this.velocityTracker.getXVelocity(), this.velocityTracker.getYVelocity());
        L81:
            this.startedTracking = false;
            VelocityTracker r012 = this.velocityTracker;
            if (r012 == null) goto L84;
            r012.recycle();
            this.velocityTracker = null;
        L84:
            this.startedTrackingPointerId = -1;
            goto L142
        L77:
            if (this.y > 0.0f) goto L80;
            this.maybeStartTracking = false;
            goto L81
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void requestDisallowInterceptTouchEvent(boolean r2) {
            if (this.maybeStartTracking == true) goto L5;
        L7:
            super.requestDisallowInterceptTouchEvent(r2);
            return;
        L5:
            if (this.startedTracking == true) goto L7;
            onTouchEvent(null);
            goto L7
        }

        public void resetTouch() {
            this.startedTracking = false;
            this.maybeStartTracking = false;
            this.startedTrackingPointerId = -1;
            VelocityTracker r0 = this.velocityTracker;
            if (r0 == null) goto L6;
            r0.recycle();
            this.velocityTracker = null;
            return;
        }
    }

    /* loaded from: classes6.dex */
    public enum EdgeToEdge extends Enum<EdgeToEdge> {
        private static final /* synthetic */ EdgeToEdge[] $VALUES = null;
        public static final EdgeToEdge NONE = null;
        public static final EdgeToEdge V1 = null;
        public static final EdgeToEdge V2 = null;

        private static /* synthetic */ EdgeToEdge[] $values() {
            return new EdgeToEdge[]{NONE, V1, V2};
        }

        static {
            NONE = new EdgeToEdge("NONE", 0);
            V1 = new EdgeToEdge("V1", 1);
            V2 = new EdgeToEdge("V2", 2);
            $VALUES = $values();
        }

        EdgeToEdge(String r1, int r2) {
        }

        public static EdgeToEdge valueOf(String r1) {
            return (EdgeToEdge) Enum.valueOf(EdgeToEdge.class, r1);
        }

        public static EdgeToEdge[] values() {
            return (EdgeToEdge[]) $VALUES.clone();
        }
    }

    public static class SheetBackDrawable extends Drawable {
        private final Paint bgPaint;
        public final Rect boundsWithInsets;
        public final Rect insets;

        /* renamed from: -$$Nest$fgetbgPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m5724$$Nest$fgetbgPaint(SheetBackDrawable r0) {
            return r0.bgPaint;
        }

        public SheetBackDrawable() {
            Paint r0 = new Paint(1);
            this.bgPaint = r0;
            this.insets = new Rect();
            this.boundsWithInsets = new Rect();
            r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            r0.setColor(-16777216);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r2) {
            if (this.boundsWithInsets.isEmpty() == false) goto L5;
            return;
        L5:
            if (getAlpha() != 0) goto L7;
            return;
        L7:
            if (AndroidUtilities.makingGlobalBlurBitmap == true) goto L13;
            r2.drawRect(this.boundsWithInsets, this.bgPaint);
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public int getAlpha() {
            return this.bgPaint.getAlpha();
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect r4) {
            super.onBoundsChange(r4);
            this.boundsWithInsets.set(r4);
            this.boundsWithInsets.left += Math.max(0, this.insets.left);
            this.boundsWithInsets.top += Math.max(0, this.insets.top);
            this.boundsWithInsets.right -= Math.max(0, this.insets.right);
            this.boundsWithInsets.bottom -= Math.max(0, this.insets.bottom);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r2) {
            this.bgPaint.setAlpha(r2);
            invalidateSelf();
        }

        public void setBackgroundInsets(int r3, int r4, int r5, int r6) {
            Rect r0 = this.insets;
            if (r0.left == r3) goto L5;
        L12:
            r0.set(r3, r4, r5, r6);
            onBoundsChange(getBounds());
            invalidateSelf();
            return;
        L5:
            if (r0.top != r4) goto L12;
            if (r0.right != r5) goto L12;
            if (r0.bottom != r6) goto L12;
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.bgPaint.setColorFilter(r1);
        }
    }

    /* renamed from: $r8$lambda$2PvBSg_IL-rmiVQ6Fp-XUTWUXFk, reason: not valid java name */
    public static /* synthetic */ void m5660$r8$lambda$2PvBSg_ILrmiVQ6FpXUTWUXFk(BottomSheet r0, ValueAnimator r1) {
        r0.lambda$startOpenAnimation$5(r1);
    }

    /* renamed from: $r8$lambda$5jo9BBY6JZTAhY40ZV-t-MbuHLk, reason: not valid java name */
    public static /* synthetic */ WindowInsets m5661$r8$lambda$5jo9BBY6JZTAhY40ZVtMbuHLk(BottomSheet r0, View r1, WindowInsets r2) {
        return r0.lambda$new$1(r1, r2);
    }

    /* renamed from: $r8$lambda$6QmiMoEaE_Kyufvf-EKfBTpEeiI, reason: not valid java name */
    public static /* synthetic */ void m5662$r8$lambda$6QmiMoEaE_KyufvfEKfBTpEeiI(BottomSheet r0, ValueAnimator r1) {
        r0.lambda$dismissWithButtonClick$7(r1);
    }

    /* renamed from: $r8$lambda$Awh56dAq1Tpw-QZrJYxlzkjGp4I, reason: not valid java name */
    public static /* synthetic */ void m5663$r8$lambda$Awh56dAq1TpwQZrJYxlzkjGp4I(BottomSheet r0) {
        r0.lambda$onCreateInternal$2();
    }

    public static /* synthetic */ boolean $r8$lambda$Gd4n5DMvY6tsqBn0MFClxifpt3E(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$NHXKXFKcf6SyXgI4np2gijyWFMU(Runnable r0, DialogInterface r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$SKUSPcMr81H9zFzTGpHhuEfRZy0(BottomSheet r0) {
        r0.registerBackCallback();
    }

    public static /* synthetic */ void $r8$lambda$TcIWMbE14w8MV0w_ZePvCTppzgw(BottomSheet r0, ValueAnimator r1) {
        r0.lambda$dismissWithButtonClick$8(r1);
    }

    /* renamed from: $r8$lambda$Ufe6-rIuCkKCNFcNQJA5h-s5GR8, reason: not valid java name */
    public static /* synthetic */ void m5664$r8$lambda$Ufe6rIuCkKCNFcNQJA5hs5GR8(BottomSheet r0, View r1) {
        r0.lambda$onCreateInternal$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$drTSpdlFr3oU2oWpCgoJIvOxdtI(BottomSheet r0, ValueAnimator r1) {
        r0.lambda$setHideSystemVerticalInsets$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$hSOxx01ku5Ur7vpuxQ5vXaZ8QEI(BottomSheet r0, ValueAnimator r1) {
        r0.lambda$dismiss$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$k1U2yrdbDKk4E01feGLP3evpzTg(BottomSheet r0, ValueAnimator r1) {
        r0.lambda$dismiss$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$n6lpQHxdJAl9eZvT5PogtkXO2C8(BottomSheet r0) {
        r0.lambda$dismiss$10();
    }

    public static /* synthetic */ void $r8$lambda$nuI1W4Vn4DordFNKRZO7VU_P56Q(BottomSheet r0, ValueAnimator r1) {
        r0.lambda$dismiss$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$o_thqw8ZrhWCOUph7EV3rHfRGq0(BottomSheet r0, int r1, ValueAnimator r2) {
        r0.lambda$dismissWithButtonClick$9(r1, r2);
    }

    /* renamed from: $r8$lambda$xa-Jvz70UB3X2wi0cQb6rLIL5bs, reason: not valid java name */
    public static /* synthetic */ void m5665$r8$lambda$xaJvz70UB3X2wi0cQb6rLIL5bs(BottomSheet r0, ValueAnimator r1) {
        r0.lambda$startOpenAnimation$6(r1);
    }

    /* renamed from: -$$Nest$fgetallowCustomAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5666$$Nest$fgetallowCustomAnimation(BottomSheet r0) {
        return r0.allowCustomAnimation;
    }

    /* renamed from: -$$Nest$fgetbottomInset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5667$$Nest$fgetbottomInset(BottomSheet r0) {
        return r0.bottomInset;
    }

    /* renamed from: -$$Nest$fgetcurrentPanTranslationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5668$$Nest$fgetcurrentPanTranslationY(BottomSheet r0) {
        return r0.currentPanTranslationY;
    }

    /* renamed from: -$$Nest$fgetcustomView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m5669$$Nest$fgetcustomView(BottomSheet r0) {
        return r0.customView;
    }

    /* renamed from: -$$Nest$fgetdisableScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5670$$Nest$fgetdisableScroll(BottomSheet r0) {
        return r0.disableScroll;
    }

    /* renamed from: -$$Nest$fgetdismissRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5671$$Nest$fgetdismissRunnable(BottomSheet r0) {
        return r0.dismissRunnable;
    }

    /* renamed from: -$$Nest$fgetdismissed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5672$$Nest$fgetdismissed(BottomSheet r0) {
        return r0.dismissed;
    }

    /* renamed from: -$$Nest$fgethideSystemVerticalInsetsProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m5673$$Nest$fgethideSystemVerticalInsetsProgress(BottomSheet r0) {
        return r0.hideSystemVerticalInsetsProgress;
    }

    /* renamed from: -$$Nest$fgetinternalBackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5674$$Nest$fgetinternalBackgroundColor(BottomSheet r0) {
        return r0.internalBackgroundColor;
    }

    /* renamed from: -$$Nest$fgetlastInsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowInsets m5675$$Nest$fgetlastInsets(BottomSheet r0) {
        return r0.lastInsets;
    }

    /* renamed from: -$$Nest$fgetlayoutCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5676$$Nest$fgetlayoutCount(BottomSheet r0) {
        return r0.layoutCount;
    }

    /* renamed from: -$$Nest$fgetleftInset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5677$$Nest$fgetleftInset(BottomSheet r0) {
        return r0.leftInset;
    }

    /* renamed from: -$$Nest$fgetmultipleLinesTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5678$$Nest$fgetmultipleLinesTitle(BottomSheet r0) {
        return r0.multipleLinesTitle;
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m5679$$Nest$fgetnotificationsLocker(BottomSheet r0) {
        return r0.notificationsLocker;
    }

    /* renamed from: -$$Nest$fgetonClickListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DialogInterface.OnClickListener m5680$$Nest$fgetonClickListener(BottomSheet r0) {
        return r0.onClickListener;
    }

    /* renamed from: -$$Nest$fgetonHideListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DialogInterface.OnDismissListener m5681$$Nest$fgetonHideListener(BottomSheet r0) {
        return r0.onHideListener;
    }

    /* renamed from: -$$Nest$fgetoverlayDrawNavBarColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5682$$Nest$fgetoverlayDrawNavBarColor(BottomSheet r0) {
        return r0.overlayDrawNavBarColor;
    }

    /* renamed from: -$$Nest$fgetpredictiveBackAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5683$$Nest$fgetpredictiveBackAnimationInProgress(BottomSheet r0) {
        return r0.predictiveBackAnimationInProgress;
    }

    /* renamed from: -$$Nest$fgetrightInset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5684$$Nest$fgetrightInset(BottomSheet r0) {
        return r0.rightInset;
    }

    /* renamed from: -$$Nest$fgetsmoothContainerViewLayoutUntil, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m5685$$Nest$fgetsmoothContainerViewLayoutUntil(BottomSheet r2) {
        return r2.smoothContainerViewLayoutUntil;
    }

    /* renamed from: -$$Nest$fgettouchSlop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5686$$Nest$fgettouchSlop(BottomSheet r0) {
        return r0.touchSlop;
    }

    /* renamed from: -$$Nest$fgettransitionFromRight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5687$$Nest$fgettransitionFromRight(BottomSheet r0) {
        return r0.transitionFromRight;
    }

    /* renamed from: -$$Nest$fputallowCustomAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5688$$Nest$fputallowCustomAnimation(BottomSheet r0, boolean r1) {
        r0.allowCustomAnimation = r1;
    }

    /* renamed from: -$$Nest$fputapplyBottomPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5689$$Nest$fputapplyBottomPadding(BottomSheet r0, boolean r1) {
        r0.applyBottomPadding = r1;
    }

    /* renamed from: -$$Nest$fputapplyTopPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5690$$Nest$fputapplyTopPadding(BottomSheet r0, boolean r1) {
        r0.applyTopPadding = r1;
    }

    /* renamed from: -$$Nest$fputbigTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5691$$Nest$fputbigTitle(BottomSheet r0, boolean r1) {
        r0.bigTitle = r1;
    }

    /* renamed from: -$$Nest$fputbottomInset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5692$$Nest$fputbottomInset(BottomSheet r0, int r1) {
        r0.bottomInset = r1;
    }

    /* renamed from: -$$Nest$fputcellType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5693$$Nest$fputcellType(BottomSheet r0, int r1) {
        r0.cellType = r1;
    }

    /* renamed from: -$$Nest$fputcustomView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5694$$Nest$fputcustomView(BottomSheet r0, View r1) {
        r0.customView = r1;
    }

    /* renamed from: -$$Nest$fputitemIcons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5695$$Nest$fputitemIcons(BottomSheet r0, int[] r1) {
        r0.itemIcons = r1;
    }

    /* renamed from: -$$Nest$fputitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5696$$Nest$fputitems(BottomSheet r0, CharSequence[] r1) {
        r0.items = r1;
    }

    /* renamed from: -$$Nest$fputlastInsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5697$$Nest$fputlastInsets(BottomSheet r0, WindowInsets r1) {
        r0.lastInsets = r1;
    }

    /* renamed from: -$$Nest$fputlastKeyboardHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5698$$Nest$fputlastKeyboardHeight(BottomSheet r0, int r1) {
        r0.lastKeyboardHeight = r1;
    }

    /* renamed from: -$$Nest$fputlayoutCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5699$$Nest$fputlayoutCount(BottomSheet r0, int r1) {
        r0.layoutCount = r1;
    }

    /* renamed from: -$$Nest$fputleftInset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5700$$Nest$fputleftInset(BottomSheet r0, int r1) {
        r0.leftInset = r1;
    }

    /* renamed from: -$$Nest$fputmultipleLinesTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5701$$Nest$fputmultipleLinesTitle(BottomSheet r0, boolean r1) {
        r0.multipleLinesTitle = r1;
    }

    /* renamed from: -$$Nest$fputonClickListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5702$$Nest$fputonClickListener(BottomSheet r0, DialogInterface.OnClickListener r1) {
        r0.onClickListener = r1;
    }

    /* renamed from: -$$Nest$fputpredictiveBackAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5703$$Nest$fputpredictiveBackAnimationInProgress(BottomSheet r0, boolean r1) {
        r0.predictiveBackAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$fputrightInset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5704$$Nest$fputrightInset(BottomSheet r0, int r1) {
        r0.rightInset = r1;
    }

    /* renamed from: -$$Nest$fputselectedPos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5705$$Nest$fputselectedPos(BottomSheet r0, Integer r1) {
        r0.selectedPos = r1;
    }

    /* renamed from: -$$Nest$fputskipDismissAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5706$$Nest$fputskipDismissAnimation(BottomSheet r0, boolean r1) {
        r0.skipDismissAnimation = r1;
    }

    /* renamed from: -$$Nest$fputsmoothContainerViewLayoutUntil, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5707$$Nest$fputsmoothContainerViewLayoutUntil(BottomSheet r0, long r1) {
        r0.smoothContainerViewLayoutUntil = r1;
    }

    /* renamed from: -$$Nest$fputtitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5708$$Nest$fputtitle(BottomSheet r0, CharSequence r1) {
        r0.title = r1;
    }

    /* renamed from: -$$Nest$fputuseFastDismiss, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5709$$Nest$fputuseFastDismiss(BottomSheet r0, boolean r1) {
        r0.useFastDismiss = r1;
    }

    /* renamed from: -$$Nest$mgetAdditionalMandatoryOffsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5710$$Nest$mgetAdditionalMandatoryOffsets(BottomSheet r0) {
        return r0.getAdditionalMandatoryOffsets();
    }

    /* renamed from: -$$Nest$mregisterBackCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5711$$Nest$mregisterBackCallback(BottomSheet r0) {
        r0.registerBackCallback();
    }

    /* renamed from: -$$Nest$mstartOpenAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5712$$Nest$mstartOpenAnimation(BottomSheet r0) {
        r0.startOpenAnimation();
    }

    /* renamed from: -$$Nest$munregisterBackCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5713$$Nest$munregisterBackCallback(BottomSheet r0) {
        r0.unregisterBackCallback();
    }

    /* renamed from: -$$Nest$mupdateBackCallbackState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5714$$Nest$mupdateBackCallbackState(BottomSheet r0) {
        r0.updateBackCallbackState();
    }

    public BottomSheet(Context r2, boolean r3) {
        this(r2, r3, null);
    }

    public BottomSheet(Context r9, boolean r10, EdgeToEdge r11, Theme.ResourcesProvider r12) {
        super(r9, R.style.TransparentDialog);
        this.currentAccount = UserConfig.selectedAccount;
        this.useHardwareLayer = true;
        this.backDrawable = new SheetBackDrawable();
        this.useLightStatusBar = true;
        int r1 = Theme.key_dialogBackground;
        this.behindKeyboardColorKey = r1;
        this.canDismissWithSwipe = true;
        this.canDismissWithTouchOutside = true;
        this.allowCustomAnimation = true;
        this.statusBarHeight = AndroidUtilities.statusBarHeight;
        this.openDuration = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
        this.openInterpolator = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.focusableSoftInputMode = 16;
        this.dimBehind = true;
        this.dimBehindAlpha = 51;
        this.allowNestedScroll = true;
        this.applyTopPadding = true;
        this.applyBottomPadding = true;
        this.itemViews = new ArrayList();
        this.dismissRunnable = new BottomSheet$$ExternalSyntheticLambda7(this);
        this.navigationBarAlpha = 0.0f;
        this.navBarColorKey = Theme.key_windowBackgroundGray;
        this.pauseAllHeavyOperations = true;
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.useBackgroundTopPadding = true;
        this.customViewGravity = 51;
        this.smoothContainerViewLayoutUntil = -1;
        this.resourcesProvider = r12;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L5;
        LeakDetector.getInstance().add(this);
    L5:
        EdgeToEdge r122 = EdgeToEdge.V2;
        if (r11 != r122) goto L8;
        AndroidUtilities.enableEdgeToEdge(getWindow());
        this.drawNavigationBar = false;
        this.doNotOverlayNavigationBar = false;
        this.drawDoubleNavigationBar = false;
    L8:
        int r3 = Build.VERSION.SDK_INT;
        if (r3 < 30) goto L13;
        getWindow().addFlags(-2147483392);
        if (r11 == EdgeToEdge.NONE) goto L14;
        this.focusableSoftInputMode = 48;
    L14:
        this.touchSlop = ViewConfiguration.get(r9).getScaledTouchSlop();
        Rect r5 = new Rect();
        Drawable r92 = r9.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        this.shadowDrawable = r92;
        int r13 = getThemedColor(r1);
        this.internalBackgroundColor = r13;
        r92.setColorFilter(new PorterDuffColorFilter(r13, PorterDuff.Mode.MULTIPLY));
        this.shadowDrawable.getPadding(r5);
        this.backgroundPaddingLeft = r5.left;
        this.backgroundPaddingTop = r5.top;
        AnonymousClass1 r93 = new AnonymousClass1(this, getContext());
        this.container = r93;
        r93.setClipChildren(false);
        r93.setClipToPadding(false);
        r93.setBackground(this.backDrawable);
        this.focusable = r10;
        if (r11 != EdgeToEdge.NONE) goto L17;
        r93.setFitsSystemWindows(true);
        r93.setOnApplyWindowInsetsListener(new BottomSheet$$ExternalSyntheticLambda8(this));
    L17:
        if (r3 < 30) goto L19;
        r93.setSystemUiVisibility(1792);
    L20:
        this.backDrawable.setAlpha(0);
        if (r11 != r122) goto L24;
        ViewCompat.setOnApplyWindowInsetsListener(r93, new BottomSheet$$ExternalSyntheticLambda9(this));
        return;
    L24:
        return;
    L19:
        r93.setSystemUiVisibility(1280);
        goto L20
    L13:
        getWindow().addFlags(-2147417856);
        goto L14
    }

    public BottomSheet(Context r2, boolean r3, Theme.ResourcesProvider r4) {
        this(r2, r3, false, r4);
    }

    public BottomSheet(Context r1, boolean r2, boolean r3, Theme.ResourcesProvider r4) {
        if (r3 == false) goto L4;
        EdgeToEdge r32 = EdgeToEdge.V1;
    L5:
        this(r1, r2, r32, r4);
        return;
    L4:
        r32 = EdgeToEdge.NONE;
        goto L5
    }

    private int getAdditionalMandatoryOffsets() {
        if (this.calcMandatoryInsets == false) goto L18;
        WindowInsets r0 = this.lastInsets;
        if (r0 == null) goto L18;
        Insets r02 = r0.getSystemGestureInsets();
        if (this.keyboardVisible == true) goto L18;
        if (this.drawNavigationBar == false) goto L18;
        if (r02 == null) goto L18;
        if (DrawableUtils$$ExternalSyntheticApiModelOutline0.m(r02) != 0) goto L17;
        if (DrawableUtils$$ExternalSyntheticApiModelOutline2.m(r02) == 0) goto L18;
    L17:
        return DrawableUtils$$ExternalSyntheticApiModelOutline3.m(r02);
    L18:
        return 0;
    }

    private /* synthetic */ void lambda$dismiss$10() {
        dismissInternal();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$dismiss$11(ValueAnimator r1) {
        this.navigationBarAlpha = ((Float) r1.getAnimatedValue()).floatValue();
        ContainerView r0 = this.container;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$dismiss$12(ValueAnimator r1) {
        onContainerViewTranslation();
    }

    private /* synthetic */ void lambda$dismiss$13(ValueAnimator r1) {
        onContainerViewTranslation();
    }

    private /* synthetic */ void lambda$dismissWithButtonClick$7(ValueAnimator r1) {
        onContainerViewTranslation();
    }

    private /* synthetic */ void lambda$dismissWithButtonClick$8(ValueAnimator r2) {
        int r22 = ((Integer) r2.getAnimatedValue()).intValue();
        setItemColor(this.selectedPos.intValue(), r22, r22);
    }

    private /* synthetic */ void lambda$dismissWithButtonClick$9(int r1, ValueAnimator r2) {
        int r22 = ((Integer) r2.getAnimatedValue()).intValue();
        setItemColor(r1, r22, r22);
    }

    private /* synthetic */ WindowInsets lambda$new$1(View r1, WindowInsets r2) {
        processLegacyContainerInsets(r2);
        if (Build.VERSION.SDK_INT < 30) goto L7;
        return WindowInsetsCompat$Impl30$$ExternalSyntheticApiModelOutline0.m();
    L7:
        return r2.consumeSystemWindowInsets();
    }

    private /* synthetic */ void lambda$onCreateInternal$2() {
        AndroidUtilities.removeFromParent(this.container);
        this.attachedFragment.getLayoutContainer().addView(this.container);
    }

    private /* synthetic */ void lambda$onCreateInternal$4(View r1) {
        dismissWithButtonClick(((Integer) r1.getTag()).intValue());
    }

    private /* synthetic */ void lambda$setHideSystemVerticalInsets$0(ValueAnimator r1) {
        this.hideSystemVerticalInsetsProgress = ((Float) r1.getAnimatedValue()).floatValue();
        this.container.requestLayout();
        this.containerView.requestLayout();
    }

    private /* synthetic */ void lambda$startOpenAnimation$5(ValueAnimator r1) {
        this.navigationBarAlpha = ((Float) r1.getAnimatedValue()).floatValue();
        ContainerView r0 = this.container;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$startOpenAnimation$6(ValueAnimator r1) {
        onContainerViewTranslation();
    }

    private void onCreateInternal() {
        BaseFragment r1 = this.attachedFragment;
        if (r1 == null) goto L12;
        r1.addSheet(this);
        if (this.attachedFragment.getLayoutContainer() != null) goto L8;
        return;
    L8:
        if (((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.attachedFragment.getLayoutContainer().getWindowToken(), 2) == false) goto L10;
        AndroidUtilities.runOnUIThread(new BottomSheet$$ExternalSyntheticLambda4(this), 80);
    L11:
        Window r12 = null;
    L14:
        if (this.useLightStatusBar == false) goto L19;
        if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(Theme.key_actionBarDefault, null, true)) < 0.721f) goto L19;
        this.container.setSystemUiVisibility(this.container.getSystemUiVisibility() | 8192);
    L19:
        if (this.useLightNavBar == false) goto L24;
        if (Build.VERSION.SDK_INT < 26) goto L24;
        AndroidUtilities.setLightNavigationBar(this, false);
    L24:
        if (this.containerView != null) goto L34;
        AnonymousClass2 r4 = new AnonymousClass2(this, getContext());
        this.containerView = r4;
        r4.setBackgroundDrawable(this.shadowDrawable);
        ViewGroup r42 = this.containerView;
        int r8 = this.backgroundPaddingLeft;
        if (this.applyTopPadding == false) goto L28;
        int r9 = AndroidUtilities.dp(8.0f);
    L29:
        int r92 = (r9 + this.backgroundPaddingTop) - 1;
        int r10 = this.backgroundPaddingLeft;
        if (this.applyBottomPadding == false) goto L32;
        int r11 = AndroidUtilities.dp(8.0f);
    L33:
        r42.setPadding(r8, r92, r10, r11);
        goto L34
    L32:
        r11 = 0;
        goto L33
    L28:
        r9 = 0;
    L34:
        this.containerView.setVisibility(4);
        this.container.addView(this.containerView, 0, LayoutHelper.createFrame(-1, -2, 80));
        if (this.topBulletinContainer != null) goto L38;
        FrameLayout r43 = new FrameLayout(getContext());
        this.topBulletinContainer = r43;
        ContainerView r82 = this.container;
        r82.addView(r43, r82.indexOfChild(this.containerView) + 1, LayoutHelper.createFrame(-1, -2, 80));
    L38:
        if (this.title == null) goto L60;
        AnonymousClass3 r44 = new AnonymousClass3(this, getContext());
        this.titleView = r44;
        r44.setText(this.title);
        boolean r45 = this.bigTitle;
        TextView r93 = this.titleView;
        if (r45 == false) goto L46;
        r93.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        this.titleView.setTextSize(1, 20.0f);
        this.titleView.setTypeface(AndroidUtilities.bold());
        TextView r46 = this.titleView;
        int r102 = AndroidUtilities.dp(21.0f);
        if (this.multipleLinesTitle == false) goto L44;
        float r112 = 14.0f;
    L45:
        r46.setPadding(r102, AndroidUtilities.dp(r112), AndroidUtilities.dp(21.0f), AndroidUtilities.dp(8.0f));
    L51:
        boolean r47 = this.multipleLinesTitle;
        TextView r7 = this.titleView;
        if (r47 == false) goto L54;
        r7.setSingleLine(false);
        this.titleView.setMaxLines(5);
        this.titleView.setEllipsize(TextUtils.TruncateAt.END);
    L55:
        this.titleView.setGravity(16);
        ViewGroup r48 = this.containerView;
        TextView r72 = this.titleView;
        if (this.multipleLinesTitle == false) goto L58;
        float r94 = -2.0f;
    L59:
        r48.addView(r72, LayoutHelper.createFrame(-1, r94));
        this.titleView.setOnTouchListener(new BottomSheet$$ExternalSyntheticLambda5());
        int r49 = 48;
    L61:
        View r73 = this.customView;
        if (r73 == null) goto L71;
        if (r73.getParent() == null) goto L66;
        ((ViewGroup) this.customView.getParent()).removeView(this.customView);
    L66:
        boolean r3 = this.useBackgroundTopPadding;
        ViewGroup r74 = this.containerView;
        if (r3 == true) goto L69;
        r74.setClipToPadding(false);
        this.containerView.setClipChildren(false);
        this.container.setClipToPadding(false);
        this.container.setClipChildren(false);
        float r13 = r49;
        this.containerView.addView(this.customView, LayoutHelper.createFrame(-1, -2.0f, this.customViewGravity, 0.0f, r13, 0.0f, 0.0f));
        ((ViewGroup.MarginLayoutParams) this.customView.getLayoutParams()).topMargin = (-this.backgroundPaddingTop) + AndroidUtilities.dp(r13);
    L85:
        if (this.attachedFragment != null) goto L105;
        if (r12 == null) goto L103;
        WindowManager.LayoutParams r32 = r12.getAttributes();
        r32.width = -1;
        r32.gravity = 51;
        r32.dimAmount = 0.0f;
        int r410 = r32.flags & (-3);
        r32.flags = r410;
        if (this.focusable == false) goto L91;
        r32.softInputMode = this.focusableSoftInputMode;
    L93:
        if (this.isFullscreen == false) goto L95;
        r32.flags |= -2147416832;
        this.container.setSystemUiVisibility(1284);
    L95:
        r32.height = -1;
        if (Build.VERSION.SDK_INT < 28) goto L98;
        AndroidUtilities$$ExternalSyntheticApiModelOutline1.m(r32, 1);
    L98:
        r12.setAttributes(r32);
        return;
    L91:
        r32.flags = r410 | 131072;
        goto L93
    L103:
        return;
    L105:
        return;
    L69:
        r74.addView(this.customView, LayoutHelper.createFrame(-1, -2.0f, this.customViewGravity, 0.0f, r49, 0.0f, 0.0f));
        goto L85
    L71:
        if (this.items == null) goto L85;
        int r75 = 0;
    L73:
        CharSequence[] r95 = this.items;
        if (r75 >= r95.length) goto L85;
        if (r95[r75] == null) goto L83;
        BottomSheetCell r96 = new BottomSheetCell(getContext(), this.cellType, this.resourcesProvider);
        CharSequence r103 = this.items[r75];
        int[] r113 = this.itemIcons;
        if (r113 == null) goto L81;
        int r114 = r113[r75];
    L82:
        r96.setTextAndIcon(r103, r114, null, this.bigTitle);
        this.containerView.addView(r96, LayoutHelper.createFrame(-1, 48.0f, 51, 0.0f, r49, 0.0f, 0.0f));
        r49 = r49 + 48;
        r96.setTag(Integer.valueOf(r75));
        r96.setOnClickListener(new BottomSheet$$ExternalSyntheticLambda6(this));
        this.itemViews.add(r96);
        goto L83
    L81:
        r114 = 0;
    L83:
        r75 = r75 + 1;
        goto L73
    L58:
        r94 = 48.0f;
        goto L59
    L54:
        r7.setLines(1);
        this.titleView.setSingleLine(true);
        this.titleView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        goto L55
    L44:
        r112 = 6.0f;
        goto L45
    L46:
        r93.setTextColor(getThemedColor(Theme.key_dialogTextGray2));
        this.titleView.setTextSize(1, 16.0f);
        TextView r411 = this.titleView;
        int r104 = AndroidUtilities.dp(16.0f);
        if (this.multipleLinesTitle == false) goto L49;
        float r115 = 8.0f;
    L50:
        r411.setPadding(r104, AndroidUtilities.dp(r115), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        goto L51
    L49:
        r115 = 0.0f;
        goto L50
    L60:
        r49 = 0;
        goto L61
    L10:
        AndroidUtilities.removeFromParent(this.container);
        this.attachedFragment.getLayoutContainer().addView(this.container);
        goto L11
    L12:
        r12 = getWindow();
        r12.setWindowAnimations(R.style.DialogNoAnimation);
        setContentView(this.container, new ViewGroup.LayoutParams(-1, -1));
        goto L14
    }

    private void registerBackCallback() {
        if (this.backAnimationCallback == null) goto L5;
        return;
    L5:
        if (ExteraConfig.getPredictiveBackIntensity() == 0.0f) goto L35;
        ContainerView r0 = this.container;
        if (r0 == null) goto L13;
        if (r0.isAttachedToWindow() == false) goto L13;
        this.onBackInvokedDispatcher = this.container.findOnBackInvokedDispatcher();
    L13:
        if (this.onBackInvokedDispatcher != null) goto L18;
        if (getWindow() == null) goto L18;
        this.onBackInvokedDispatcher = getWindow().getOnBackInvokedDispatcher();
    L18:
        if (this.onBackInvokedDispatcher != null) goto L23;
        if ((getContext() instanceof Activity) == false) goto L23;
        this.onBackInvokedDispatcher = ((Activity) getContext()).getOnBackInvokedDispatcher();
    L23:
        if (this.onBackInvokedDispatcher != null) goto L28;
        ContainerView r02 = this.container;
        if (r02 == null) goto L33;
        r02.post(new BottomSheet$$ExternalSyntheticLambda17(this));
        return;
    L33:
        return;
    L28:
        AnonymousClass8 r03 = new AnonymousClass8(this);
        this.backAnimationCallback = r03;
        if (this.keyboardVisible == true) goto L34;
        this.onBackInvokedDispatcher.registerOnBackInvokedCallback(DurationKt.NANOS_IN_MILLIS, r03);
        this.isBackCallbackRegistered = true;
        return;
    L34:
        return;
    }

    private void setShowing(boolean r2) {
        if (this.showing == r2) goto L13;
        this.showing = r2;
        if (this.openedLayerNum <= 0) goto L12;
        if (r2 == false) goto L10;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{Integer.valueOf(this.openedLayerNum)});
        return;
    L10:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{Integer.valueOf(this.openedLayerNum)});
        return;
    L12:
        return;
    }

    private void startOpenAnimation() {
        if (this.dismissed == true) goto L34;
        int r1 = 0;
        this.containerView.setVisibility(0);
        if (onCustomOpenAnimation() == false) goto L8;
        return;
    L8:
        if (this.useHardwareLayer == false) goto L10;
        this.container.setLayerType(2, null);
    L10:
        boolean r0 = this.transitionFromRight;
        ViewGroup r3 = this.containerView;
        if (r0 == false) goto L13;
        r3.setTranslationX(AndroidUtilities.dp(48.0f));
        this.containerView.setAlpha(0.0f);
        this.containerView.setTranslationY(0.0f);
    L14:
        onContainerViewTranslation();
        this.currentSheetAnimationType = 1;
        ValueAnimator r32 = this.navigationBarAnimation;
        if (r32 == null) goto L17;
        r32.cancel();
    L17:
        ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{this.navigationBarAlpha, 1.0f});
        this.navigationBarAnimation = r2;
        r2.addUpdateListener(new BottomSheet$$ExternalSyntheticLambda15(this));
        this.currentSheetAnimation = new AnimatorSet();
        ArrayList<Animator> r22 = new ArrayList();
        r22.add(ObjectAnimator.ofFloat(this.containerView, View.TRANSLATION_X, new float[]{0.0f}));
        r22.add(ObjectAnimator.ofFloat(this.containerView, View.ALPHA, new float[]{1.0f}));
        ObjectAnimator r33 = ObjectAnimator.ofFloat(this.containerView, View.TRANSLATION_Y, new float[]{0.0f});
        r33.addUpdateListener(new BottomSheet$$ExternalSyntheticLambda16(this));
        r22.add(r33);
        SheetBackDrawable r34 = this.backDrawable;
        Property<Drawable, Integer> r4 = AnimationProperties.COLOR_DRAWABLE_ALPHA;
        if (this.dimBehind == false) goto L20;
        r1 = this.dimBehindAlpha;
    L20:
        r22.add(ObjectAnimator.ofInt(r34, r4, new int[]{r1}));
        r22.add(this.navigationBarAnimation);
        appendOpenAnimator(true, r22);
        this.currentSheetAnimation.playTogether(r22);
        boolean r02 = this.transitionFromRight;
        AnimatorSet r12 = this.currentSheetAnimation;
        if (r02 == false) goto L23;
        r12.setDuration(250);
        this.currentSheetAnimation.setInterpolator(CubicBezierInterpolator.DEFAULT);
    L24:
        AnimatorSet r03 = this.currentSheetAnimation;
        if (this.waitingKeyboard == false) goto L27;
        long r13 = 0;
    L28:
        r03.setStartDelay(r13);
        this.currentSheetAnimation.setInterpolator(this.openInterpolator);
        this.notificationsLocker.lock();
        this.currentSheetAnimation.addListener(new AnonymousClass5(this));
        if (this.pauseAllHeavyOperations == false) goto L31;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
    L31:
        this.currentSheetAnimation.start();
        return;
    L27:
        r13 = 20;
        goto L28
    L23:
        r12.setDuration(this.openDuration);
        this.currentSheetAnimation.setInterpolator(this.openInterpolator);
        goto L24
    L13:
        r3.setTranslationY(((getContainerViewHeight() + this.keyboardHeight) + AndroidUtilities.dp(10.0f)) + Math.max(0, Math.min(AndroidUtilities.navigationBarHeight, getBottomInset())));
        goto L14
    }

    private void unregisterBackCallback() {
        if (this.isBackCallbackRegistered == false) goto L9;
        OnBackInvokedDispatcher r0 = this.onBackInvokedDispatcher;
        if (r0 == null) goto L9;
        OnBackAnimationCallback r1 = this.backAnimationCallback;
        if (r1 == null) goto L9;
        r0.unregisterOnBackInvokedCallback(r1);
    L9:
        this.isBackCallbackRegistered = false;
        this.backAnimationCallback = null;
        this.onBackInvokedDispatcher = null;
    }

    private void updateBackCallbackState() {
        OnBackInvokedDispatcher r0 = this.onBackInvokedDispatcher;
        if (r0 == null) goto L18;
        OnBackAnimationCallback r1 = this.backAnimationCallback;
        if (r1 == null) goto L21;
        boolean r2 = this.keyboardVisible;
        if (r2 == true) goto L10;
    L13:
        if (r2 == false) goto L15;
        return;
    L15:
        if (this.isBackCallbackRegistered == true) goto L20;
        r0.registerOnBackInvokedCallback(DurationKt.NANOS_IN_MILLIS, r1);
        this.isBackCallbackRegistered = true;
        return;
    L20:
        return;
    L10:
        if (this.isBackCallbackRegistered == false) goto L13;
        r0.unregisterOnBackInvokedCallback(r1);
        this.isBackCallbackRegistered = false;
        return;
    L21:
        return;
    }

    public void appendOpenAnimator(boolean r1, ArrayList<Animator> r2) {
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean attachedToParent() {
        ContainerView r0 = this.container;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isAttachedToWindow() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean canDismissWithSwipe() {
        return this.canDismissWithSwipe;
    }

    public boolean canDismissWithTouchOutside() {
        return this.canDismissWithTouchOutside;
    }

    public boolean canSwipeToBack(MotionEvent r1) {
        return false;
    }

    public void cancelSheetAnimation() {
        AnimatorSet r0 = this.currentSheetAnimation;
        if (r0 == null) goto L5;
        r0.cancel();
        this.currentSheetAnimation = null;
    L5:
        this.currentSheetAnimationType = 0;
    }

    @Override // android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        BottomSheetDelegateInterface r0 = this.delegate;
        if (r0 == null) goto L8;
        if (r0.canDismiss() == true) goto L8;
        return;
    L8:
        if (this.dismissed == false) goto L10;
        return;
    L10:
        this.dismissed = true;
        DialogInterface.OnDismissListener r1 = this.onHideListener;
        if (r1 == null) goto L13;
        r1.onDismiss(this);
    L13:
        cancelSheetAnimation();
        onDismissAnimationStart();
        if (this.skipDismissAnimation == false) goto L17;
        AndroidUtilities.runOnUIThread(new BottomSheet$$ExternalSyntheticLambda10(this));
    L21:
        long r5 = 0;
    L40:
        Bulletin r02 = Bulletin.getVisibleBulletin();
        if (r02 != null) goto L43;
    L50:
        setShowing(false);
        return;
    L43:
        if (r02.isShowing() == false) goto L50;
        if (r02.hideAfterBottomSheet == false) goto L50;
        if (r5 <= 0) goto L49;
        r02.hide((long) (r5 * 0.6f));
        goto L50
    L49:
        r02.hide();
        goto L50
    L17:
        if (this.allowCustomAnimation == true) goto L19;
    L22:
        AndroidUtilities.hideKeyboard(this.container);
        this.currentSheetAnimationType = 2;
        ValueAnimator r52 = this.navigationBarAnimation;
        if (r52 == null) goto L25;
        r52.cancel();
    L25:
        ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{this.navigationBarAlpha, 0.0f});
        this.navigationBarAnimation = r12;
        r12.addUpdateListener(new BottomSheet$$ExternalSyntheticLambda11(this));
        this.currentSheetAnimation = new AnimatorSet();
        ArrayList<Animator> r13 = new ArrayList();
        ViewGroup r6 = this.containerView;
        if (r6 != null) goto L28;
    L35:
        r13.add(ObjectAnimator.ofInt(this.backDrawable, AnimationProperties.COLOR_DRAWABLE_ALPHA, new int[]{0}));
        r13.add(this.navigationBarAnimation);
        appendOpenAnimator(false, r13);
        this.currentSheetAnimation.playTogether(r13);
        boolean r03 = this.transitionFromRight;
        AnimatorSet r14 = this.currentSheetAnimation;
        if (r03 == false) goto L38;
        r14.setDuration(200);
        this.currentSheetAnimation.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r5 = 0;
    L39:
        this.currentSheetAnimation.addListener(new AnonymousClass7(this));
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
        this.currentSheetAnimation.start();
        goto L40
    L38:
        r5 = 250;
        r14.setDuration(250);
        this.currentSheetAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        goto L39
    L28:
        if (this.transitionFromRight == false) goto L30;
        ObjectAnimator r62 = ObjectAnimator.ofFloat(r6, View.TRANSLATION_X, new float[]{AndroidUtilities.dp(48.0f)});
        r62.addUpdateListener(new BottomSheet$$ExternalSyntheticLambda12(this));
        r13.add(r62);
        r13.add(ObjectAnimator.ofFloat(this.containerView, View.ALPHA, new float[]{0.0f}));
        goto L35
    L30:
        Property r53 = View.TRANSLATION_Y;
        int r7 = getContainerViewHeight();
        if (this.forceKeyboardOnDismiss == false) goto L33;
        int r8 = this.lastKeyboardHeight;
    L34:
        ObjectAnimator r04 = ObjectAnimator.ofFloat(r6, r53, new float[]{((r7 + r8) + AndroidUtilities.dp(10.0f)) + Math.max(0, Math.min(AndroidUtilities.navigationBarHeight, getBottomInset()))});
        r04.addUpdateListener(new BottomSheet$$ExternalSyntheticLambda13(this));
        r13.add(r04);
        goto L35
    L33:
        r8 = this.keyboardHeight;
        goto L34
    L19:
        if (onCustomCloseAnimation() == true) goto L21;
        goto L21
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss(boolean r1) {
        dismiss();
    }

    public void dismissInternal() {
        BaseFragment r0 = this.attachedFragment;
        if (r0 == null) goto L11;
        r0.removeSheet(this);
        AndroidUtilities.removeFromParent(this.container);
        return;
    L11:
        super.dismiss();     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    public void dismissWithButtonClick(int r9) {
        if (this.dismissed == true) goto L17;
        this.dismissed = true;
        cancelSheetAnimation();
        this.currentSheetAnimationType = 2;
        this.currentSheetAnimation = new AnimatorSet();
        ObjectAnimator r2 = ObjectAnimator.ofFloat(this.containerView, View.TRANSLATION_Y, new float[]{((getContainerViewHeight() + this.keyboardHeight) + AndroidUtilities.dp(10.0f)) + Math.max(0, Math.min(AndroidUtilities.navigationBarHeight, getBottomInset()))});
        r2.addUpdateListener(new BottomSheet$$ExternalSyntheticLambda0(this));
        this.currentSheetAnimation.playTogether(new Animator[]{r2, ObjectAnimator.ofInt(this.backDrawable, AnimationProperties.COLOR_DRAWABLE_ALPHA, new int[]{0})});
        AnimatorSet r0 = this.currentSheetAnimation;
        if (this.cellType != Builder.CELL_TYPE_CALL) goto L8;
        long r1 = 330;
    L9:
        r0.setDuration(r1);
        this.currentSheetAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.currentSheetAnimation.addListener(new AnonymousClass6(this, r9));
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
        this.currentSheetAnimation.start();
        if (this.cellType == Builder.CELL_TYPE_CALL) goto L12;
        return;
    L12:
        if (this.selectedPos == null) goto L16;
        int r02 = getItemViews().get(this.selectedPos.intValue()).getTextView().getCurrentTextColor();
        int r12 = getItemViews().get(r9).getTextView().getCurrentTextColor();
        ValueAnimator r22 = ValueAnimator.ofArgb(new int[]{r02, r12});
        r22.addUpdateListener(new BottomSheet$$ExternalSyntheticLambda1(this));
        r22.setDuration(130);
        CubicBezierInterpolator r5 = CubicBezierInterpolator.DEFAULT;
        r22.setInterpolator(r5);
        r22.start();
        ValueAnimator r03 = ValueAnimator.ofArgb(new int[]{r12, r02});
        r03.addUpdateListener(new BottomSheet$$ExternalSyntheticLambda2(this, r9));
        r03.setDuration(130);
        r03.setInterpolator(r5);
        r03.start();
        return;
    L16:
        return;
    L8:
        r1 = 180;
        goto L9
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent r1) {
        return super.dispatchKeyEvent(r1);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (this.dismissed == false) goto L7;
        return false;
    L7:
        return super.dispatchTouchEvent(r2);
    }

    public void fixNavigationBar() {
        fixNavigationBar(getThemedColor(Theme.key_dialogBackground));
    }

    public void fixNavigationBar(int r3) {
        this.drawNavigationBar = !this.occupyNavigationBar;
        this.drawDoubleNavigationBar = true;
        this.scrollNavBar = true;
        this.navBarColorKey = -1;
        this.behindKeyboardColorKey = -1;
        this.behindKeyboardColor = r3;
        this.navBarColor = r3;
        setOverlayNavBarColor(r3);
    }

    public void forceKeyboardOnDismiss() {
        this.forceKeyboardOnDismiss = true;
    }

    public Drawable getBackDrawable() {
        return this.backDrawable;
    }

    public int getBackgroundPaddingLeft() {
        return this.backgroundPaddingLeft;
    }

    public int getBackgroundPaddingTop() {
        return this.backgroundPaddingTop;
    }

    public int getBottomInset() {
        return (int) (this.bottomInset * (1.0f - this.hideSystemVerticalInsetsProgress));
    }

    public int getBottomSheetWidth(boolean r1, int r2, int r3) {
        if (r1 == false) goto L5;
        return r2;
    L5:
        return (int) Math.max(r2 * 0.8f, Math.min(AndroidUtilities.dp(480.0f), r2));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public BulletinFactory getBulletinFactory() {
        return BulletinFactory.of(this.topBulletinContainer, this.resourcesProvider);
    }

    public ContainerView getContainer() {
        return this.container;
    }

    public ViewGroup getContainerView() {
        return this.containerView;
    }

    public int getContainerViewHeight() {
        ViewGroup r0 = this.containerView;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.getMeasuredHeight();
    }

    public int getCurrentAccount() {
        return this.currentAccount;
    }

    public ArrayList<BottomSheetCell> getItemViews() {
        return this.itemViews;
    }

    public int getLeftInset() {
        if (this.lastInsets != null) goto L5;
        return 0;
    L5:
        return (int) (r0.getSystemWindowInsetLeft() * (1.0f - this.hideSystemVerticalInsetsProgress));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public int getNavigationBarColor(int r5) {
        if (attachedToParent() == false) goto L14;
        ViewGroup r0 = this.containerView;
        if (r0 == null) goto L14;
        if (this.transitionFromRight == false) goto L10;
        float r02 = r0.getAlpha();
    L16:
        return ColorUtils.blendARGB(r5, this.navBarColor, r02);
    L10:
        int r03 = (getContainerViewHeight() + this.keyboardHeight) + AndroidUtilities.dp(10.0f);
        int r2 = 0;
        if (this.scrollNavBar == false) goto L13;
        r2 = Math.max(0, Math.min(AndroidUtilities.navigationBarHeight, getBottomInset()));
    L13:
        r02 = Utilities.clamp01(1.0f - (this.containerView.getTranslationY() / (r03 + r2)));
    L14:
        r02 = 0.0f;
        goto L16
    }

    public Theme.ResourcesProvider getResourcesProvider() {
        return this.resourcesProvider;
    }

    public int getRightInset() {
        if (this.lastInsets != null) goto L5;
        return 0;
    L5:
        return (int) (r0.getSystemWindowInsetRight() * (1.0f - this.hideSystemVerticalInsetsProgress));
    }

    public int getSheetAnimationType() {
        return this.currentSheetAnimationType;
    }

    public ViewGroup getSheetContainer() {
        return this.containerView;
    }

    public int getStatusBarHeight() {
        return (int) (this.statusBarHeight * (1.0f - this.hideSystemVerticalInsetsProgress));
    }

    public int getSystemBottomInset() {
        WindowInsets r0 = this.lastInsets;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getSystemWindowInsetBottom();
    }

    public int getTag() {
        return this.tag;
    }

    public int getTargetOpenTranslationY() {
        return 0;
    }

    public ArrayList<ThemeDescription> getThemeDescriptions() {
        return null;
    }

    public int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public TextView getTitleView() {
        return this.titleView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public View getWindowView() {
        return this.container;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean isAttachedLightStatusBar() {
        return this.useLightStatusBar;
    }

    public boolean isDismissed() {
        return this.dismissed;
    }

    public boolean isFocusable() {
        return this.focusable;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean isFullyVisible() {
        return false;
    }

    public boolean isKeyboardVisible() {
        return this.keyboardVisible;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean isShown() {
        return !this.dismissed;
    }

    public boolean isTouchOutside(float r5, float r6) {
        FrameLayout r0 = this.topBulletinContainer;
        if (r0 == null) goto L16;
        if (r0.getChildCount() <= 0) goto L16;
        View r02 = this.topBulletinContainer.getChildAt(0);
        if (r6 < (this.topBulletinContainer.getY() + r02.getY())) goto L16;
        if (r6 > ((this.topBulletinContainer.getY() + r02.getY()) + r02.getHeight())) goto L16;
        if (r5 < (this.topBulletinContainer.getX() + r02.getX())) goto L16;
        if (r5 > ((this.topBulletinContainer.getX() + r02.getX()) + r02.getWidth())) goto L16;
        return false;
    L16:
        if (r6 >= this.containerView.getTop()) goto L18;
        return true;
    L18:
        if (r5 >= this.containerView.getLeft()) goto L20;
        return true;
    L20:
        if (r5 > this.containerView.getRight()) goto L26;
        return false;
    L26:
        return true;
    }

    public void mainContainerDispatchDraw(Canvas r1) {
    }

    public void makeAttached(BaseFragment r2) {
        if (AndroidUtilities.isTablet() == true) goto L14;
        if (r2 != null) goto L7;
    L9:
        this.attachedFragment = r2;
        SheetBackDrawable r1 = this.backDrawable;
        if (r1 == null) goto L13;
        SheetBackDrawable.m5724$$Nest$fgetbgPaint(r1).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        return;
    L13:
        return;
    L7:
        if (r2.isSupportEdgeToEdge() == false) goto L9;
        return;
    }

    public WindowVisibilityManager.Controller obtainWindowVisibilityController() {
        if (this.windowVisibilityManager != null) goto L6;
        this.windowVisibilityManager = new WindowVisibilityManager(getWindow());
    L6:
        return this.windowVisibilityManager.obtainController();
    }

    public WindowInsetsCompat onApplyWindowInsetsToRoot(View r1, WindowInsetsCompat r2) {
        return r2;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean onAttachedBackPressed() {
        onBackPressed();
        return true;
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        if (this.attachedFragment != null) goto L6;
        super.onBackPressed();
        return;
    L6:
        dismiss();
    }

    public void onConfigurationChanged(Configuration r1) {
    }

    public void onContainerDraw(Canvas r1) {
    }

    public void onContainerLayout(int r1, int r2, int r3, int r4) {
    }

    public boolean onContainerTouchEvent(MotionEvent r1) {
        return false;
    }

    public void onContainerTranslationYChanged(float r1) {
        ContainerView r0 = this.container;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    public void onContainerViewTranslation() {
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle r1) {
        super.onCreate(r1);
        onCreateInternal();
    }

    public boolean onCustomCloseAnimation() {
        return false;
    }

    public boolean onCustomLayout(View r1, int r2, int r3, int r4, int r5) {
        return false;
    }

    public boolean onCustomMeasure(View r1, int r2, int r3) {
        return false;
    }

    public boolean onCustomOpenAnimation() {
        return false;
    }

    public void onDismissAnimationStart() {
    }

    public void onDismissWithTouchOutside() {
        dismiss();
    }

    public void onInsetsChanged() {
    }

    public void onOpenAnimationEnd() {
    }

    public boolean onScrollUp(float r1) {
        return false;
    }

    public void onScrollUpBegin(float r1) {
    }

    public void onScrollUpEnd(float r1) {
    }

    public void onSmoothContainerViewLayout(float r1) {
        ContainerView r0 = this.container;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
    }

    public void onSwipeStarts() {
    }

    public void processLegacyContainerInsets(WindowInsets r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        int r0 = r3.getSystemWindowInsetTop();
        if (r0 != 0) goto L9;
        if (AndroidUtilities.isInMultiwindow == true) goto L9;
    L11:
        this.lastInsets = r3;
        this.container.requestLayout();
        onInsetsChanged();
        return;
    L9:
        if (this.statusBarHeight == r0) goto L11;
        this.statusBarHeight = r0;
        goto L11
    }

    public void release() {
        dismissInternal();
    }

    public void setAllowNestedScroll(boolean r2) {
        this.allowNestedScroll = r2;
        if (r2 == true) goto L6;
        this.containerView.setTranslationY(0.0f);
        onContainerViewTranslation();
        return;
    }

    public void setApplyBottomPadding(boolean r1) {
        this.applyBottomPadding = r1;
    }

    public void setApplyTopPadding(boolean r1) {
        this.applyTopPadding = r1;
    }

    public void setBackgroundColor(int r4) {
        this.shadowDrawable.setColorFilter(r4, PorterDuff.Mode.MULTIPLY);
        if (this.internalBackgroundColor == r4) goto L8;
        this.internalBackgroundColor = r4;
        ContainerView r42 = this.container;
        if (r42 == null) goto L9;
        r42.invalidate(0, r42.getMeasuredHeight() - ContainerView.m5720$$Nest$fgetinternalPaddingBottom(this.container), this.container.getMeasuredWidth(), this.container.getMeasuredHeight());
        return;
    L9:
        return;
    }

    public void setCalcMandatoryInsets(boolean r1) {
        this.calcMandatoryInsets = r1;
        this.drawNavigationBar = r1;
    }

    public void setCanDismissWithSwipe(boolean r1) {
        this.canDismissWithSwipe = r1;
    }

    public void setCanDismissWithTouchOutside(boolean r1) {
        this.canDismissWithTouchOutside = r1;
    }

    public void setCurrentPanTranslationY(float r1) {
        this.currentPanTranslationY = r1;
        this.container.invalidate();
    }

    public void setCustomView(View r1) {
        this.customView = r1;
    }

    public void setDelegate(BottomSheetDelegateInterface r1) {
        this.delegate = r1;
    }

    public BottomSheet setDimBehind(boolean r1) {
        this.dimBehind = r1;
        return this;
    }

    public void setDimBehindAlpha(int r1) {
        this.dimBehindAlpha = r1;
    }

    public void setDisableScroll(boolean r1) {
        this.disableScroll = r1;
    }

    public void setFocusable(boolean r3) {
        if (this.focusable != r3) goto L5;
        return;
    L5:
        this.focusable = r3;
        Window r32 = getWindow();
        WindowManager.LayoutParams r0 = r32.getAttributes();
        if (this.focusable == false) goto L8;
        r0.softInputMode = this.focusableSoftInputMode;
        r0.flags &= -131073;
    L9:
        r32.setAttributes(r0);
        return;
    L8:
        r0.softInputMode = 48;
        r0.flags |= 131072;
        goto L9
    }

    public void setHideSystemVerticalInsets(boolean r4) {
        float r0 = this.hideSystemVerticalInsetsProgress;
        if (r4 == false) goto L5;
        float r42 = 1.0f;
    L6:
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r0, r42}).setDuration(180);
        r43.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r43.addUpdateListener(new BottomSheet$$ExternalSyntheticLambda14(this));
        r43.start();
        return;
    L5:
        r42 = 0.0f;
        goto L6
    }

    public void setImageReceiverNumLevel(int r1, int r2) {
        this.playingImagesLayerNum = r1;
        this.openedLayerNum = r2;
    }

    public void setItemColor(int r2, int r3, int r4) {
        if (r2 >= 0) goto L4;
        return;
    L4:
        if (r2 >= this.itemViews.size()) goto L9;
        BottomSheetCell r1 = this.itemViews.get(r2);
        BottomSheetCell.m5716$$Nest$fgettextView(r1).setTextColor(r3);
        BottomSheetCell.m5715$$Nest$fgetimageView(r1).setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.MULTIPLY));
        return;
    }

    public void setItemText(int r2, CharSequence r3) {
        if (r2 >= 0) goto L4;
        return;
    L4:
        if (r2 >= this.itemViews.size()) goto L9;
        BottomSheetCell.m5716$$Nest$fgettextView(this.itemViews.get(r2)).setText(r3);
        return;
    }

    public void setItems(CharSequence[] r1, int[] r2, DialogInterface.OnClickListener r3) {
        this.items = r1;
        this.itemIcons = r2;
        this.onClickListener = r3;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void setKeyboardHeightFromParent(int r1) {
    }

    @Override // android.app.Dialog
    public void setOnDismissListener(DialogInterface.OnDismissListener r1) {
        super.setOnDismissListener(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void setOnDismissListener(Runnable r2) {
        if (r2 == null) goto L5;
        setOnHideListener(new BottomSheet$$ExternalSyntheticLambda3(r2));
        return;
    }

    public void setOnHideListener(DialogInterface.OnDismissListener r1) {
        this.onHideListener = r1;
    }

    public void setOpenNoDelay(boolean r1) {
        this.openNoDelay = r1;
    }

    public void setOverlayNavBarColor(int r7) {
        this.overlayDrawNavBarColor = r7;
        ContainerView r72 = this.container;
        if (r72 == null) goto L5;
        r72.invalidate();
    L5:
        boolean r0 = false;
        if (this.attachedFragment == null) goto L12;
        LaunchActivity.instance.checkSystemBarColors(true, true, true);
        View r73 = getWindowView();
        if (AndroidUtilities.computePerceivedBrightness(getNavigationBarColor(getThemedColor(Theme.key_windowBackgroundGray))) < 0.721f) goto L10;
        r0 = true;
    L10:
        AndroidUtilities.setLightNavigationBar(r73, r0);
        return;
    L12:
        AndroidUtilities.setNavigationBarColor(this, this.overlayDrawNavBarColor);
        if (AndroidUtilities.computePerceivedBrightness(this.overlayDrawNavBarColor) <= 0.721d) goto L15;
        r0 = true;
    L15:
        AndroidUtilities.setLightNavigationBar(this, r0);
    }

    public void setShowWithoutAnimation(boolean r1) {
        this.showWithoutAnimation = r1;
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence r2) {
        setTitle(r2, false);
    }

    public void setTitle(CharSequence r1, boolean r2) {
        this.title = r1;
        this.bigTitle = r2;
    }

    public void setTitleColor(int r1) {
        TextView r0 = this.titleView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setTextColor(r1);
    }

    public void setUseLightStatusBar(boolean r4) {
        this.useLightStatusBar = r4;
        float r42 = AndroidUtilities.computePerceivedBrightness(Theme.getColor(Theme.key_actionBarDefault, null, true));
        int r0 = this.container.getSystemUiVisibility();
        if (this.useLightStatusBar == true) goto L5;
    L7:
        int r43 = r0 & (-8193);
    L8:
        this.container.setSystemUiVisibility(r43);
        if (this.attachedFragment == null) goto L12;
        LaunchActivity.instance.checkSystemBarColors(true, true, true);
        return;
    L12:
        return;
    L5:
        if (r42 < 0.721f) goto L7;
        r43 = r0 | 8192;
        goto L8
    }

    public boolean shouldOverlayCameraViewOverNavBar() {
        return false;
    }

    @Override // android.app.Dialog
    public void show() {
        if (AndroidUtilities.isSafeToShow(getContext()) == true) goto L6;
        return;
    L6:
        if (this.attachedFragment == null) goto L8;
        onCreateInternal();
    L9:
        setShowing(true);
        if (this.focusable == false) goto L12;
        getWindow().setSoftInputMode(this.focusableSoftInputMode);
    L12:
        int r0 = 0;
        this.dismissed = false;
        cancelSheetAnimation();
        this.containerView.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.x + (this.backgroundPaddingLeft * 2), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.y, Integer.MIN_VALUE));
        boolean r1 = this.showWithoutAnimation;
        SheetBackDrawable r2 = this.backDrawable;
        if (r1 == true) goto L15;
        r2.setAlpha(0);
        this.layoutCount = 2;
        ViewGroup r12 = this.containerView;
        float r22 = (AndroidUtilities.statusBarHeight * (1.0f - this.hideSystemVerticalInsetsProgress)) + r12.getMeasuredHeight();
        if (this.scrollNavBar == false) goto L22;
        r0 = Math.max(0, Math.min(AndroidUtilities.navigationBarHeight, getBottomInset()));
    L22:
        r12.setTranslationY(r22 + r0);
        onContainerViewTranslation();
        if (this.openNoDelay == false) goto L25;
        long r02 = 0;
    L27:
        if (this.waitingKeyboard == false) goto L29;
        r02 = 500;
    L29:
        AnonymousClass4 r23 = new AnonymousClass4(this);
        this.startAnimationRunnable = r23;
        AndroidUtilities.runOnUIThread(r23, r02);
        return;
    L25:
        r02 = 150;
        goto L27
    L15:
        if (this.dimBehind == false) goto L17;
        r0 = this.dimBehindAlpha;
    L17:
        r2.setAlpha(r0);
        this.containerView.setTranslationY(0.0f);
        onContainerViewTranslation();
        return;
    L8:
        super.show();
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean showDialog(Dialog r1) {
        return false;
    }

    public void skipDismissAnimation() {
        this.skipDismissAnimation = true;
    }

    public void smoothContainerViewLayout() {
        this.smoothContainerViewLayoutUntil = System.currentTimeMillis() + 80;
    }

    public void transitionFromRight(boolean r1) {
        this.transitionFromRight = r1;
    }
}
