package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.Keep;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Bitmaps;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.AspectRatioFrameLayout;

/* loaded from: classes3.dex */
public class PipRoundVideoView implements NotificationCenter.NotificationCenterDelegate {

    @SuppressLint({"StaticFieldLeak"})
    private static PipRoundVideoView instance;
    private AspectRatioFrameLayout aspectRatioFrameLayout;
    private Bitmap bitmap;
    private int currentAccount;
    private DecelerateInterpolator decelerateInterpolator;
    private AnimatorSet hideShowAnimation;
    private ImageView imageView;
    private Runnable onCloseRunnable;
    private Activity parentActivity;
    private SharedPreferences preferences;
    private RectF rect;
    private TextureView textureView;
    private int videoHeight;
    private int videoWidth;
    private WindowManager.LayoutParams windowLayoutParams;
    private WindowManager windowManager;
    private FrameLayout windowView;

    /* renamed from: org.telegram.ui.Components.PipRoundVideoView$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends PipFrameLayout {
        private boolean dragging;
        private boolean startDragging;
        private float startX;
        private float startY;
        final /* synthetic */ PipRoundVideoView this$0;

        public AnonymousClass1(PipRoundVideoView r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            Drawable r0 = Theme.chat_roundVideoShadow;
            if (r0 == null) goto L6;
            r0.setAlpha((int) (getAlpha() * 255.0f));
            Theme.chat_roundVideoShadow.setBounds(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(125.0f), AndroidUtilities.dp(125.0f));
            Theme.chat_roundVideoShadow.draw(r7);
            Theme.chat_docBackPaint.setColor(Theme.getColor(Theme.key_chat_inBubble));
            Theme.chat_docBackPaint.setAlpha((int) (getAlpha() * 255.0f));
            r7.drawCircle(AndroidUtilities.dp(63.0f), AndroidUtilities.dp(63.0f), AndroidUtilities.dp(59.5f), Theme.chat_docBackPaint);
            return;
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L5;
            this.startX = r3.getRawX();
            this.startY = r3.getRawY();
            this.startDragging = true;
        L5:
            return true;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r10) {
            if (this.startDragging == false) goto L5;
        L7:
            float r0 = r10.getRawX();
            float r2 = r10.getRawY();
            if (r10.getAction() != 2) goto L41;
            float r102 = r0 - this.startX;
            float r3 = r2 - this.startY;
            if (this.startDragging == false) goto L17;
            if (Math.abs(r102) < AndroidUtilities.getPixelsInCM(0.3f, true)) goto L14;
        L15:
            this.dragging = true;
            this.startDragging = false;
        L53:
            return true;
        L14:
            if (Math.abs(r3) < AndroidUtilities.getPixelsInCM(0.3f, false)) goto L53;
        L17:
            if (this.dragging == false) goto L53;
            PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).x = (int) (r6.x + r102);
            PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).y = (int) (r10.y + r3);
            int r103 = PipRoundVideoView.m11790$$Nest$fgetvideoWidth(this.this$0) / 2;
            int r32 = PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).x;
            int r4 = -r103;
            PipRoundVideoView r6 = this.this$0;
            if (r32 >= r4) goto L22;
            PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(r6).x = r4;
        L24:
            int r33 = PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).x;
            PipRoundVideoView r42 = this.this$0;
            float r7 = 1.0f;
            if (r33 >= 0) goto L28;
            r7 = 1.0f + ((PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(r42).x / r103) * 0.5f);
        L31:
            if (PipRoundVideoView.m11793$$Nest$fgetwindowView(this.this$0).getAlpha() == r7) goto L33;
            PipRoundVideoView.m11793$$Nest$fgetwindowView(this.this$0).setAlpha(r7);
        L33:
            int r104 = PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).y;
            PipRoundVideoView r34 = this.this$0;
            if (r104 >= 0) goto L37;
            PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(r34).y = 0;
        L39:
            PipRoundVideoView.m11792$$Nest$fgetwindowManager(this.this$0).updateViewLayout(PipRoundVideoView.m11793$$Nest$fgetwindowView(this.this$0), PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0));
            this.startX = r0;
            this.startY = r2;
            goto L53
        L37:
            if (PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(r34).y <= (AndroidUtilities.displaySize.y - PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).height)) goto L39;
            PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).y = AndroidUtilities.displaySize.y - PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).height;
            goto L39
        L28:
            if (PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(r42).x <= (AndroidUtilities.displaySize.x - PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).width)) goto L31;
            r7 = 1.0f - ((((PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).x - AndroidUtilities.displaySize.x) + PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).width) / r103) * 0.5f);
            goto L31
        L22:
            if (PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(r6).x <= ((AndroidUtilities.displaySize.x - PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).width) + r103)) goto L24;
            PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).x = (AndroidUtilities.displaySize.x - PipRoundVideoView.m11791$$Nest$fgetwindowLayoutParams(this.this$0).width) + r103;
            goto L24
        L41:
            if (r10.getAction() != 1) goto L53;
            if (this.startDragging == true) goto L45;
        L52:
            this.dragging = false;
            this.startDragging = false;
            PipRoundVideoView.m11795$$Nest$manimateToBoundsMaybe(this.this$0);
            goto L53
        L45:
            if (this.dragging == true) goto L52;
            MessageObject r105 = MediaController.getInstance().getPlayingMessageObject();
            if (r105 == null) goto L52;
            if (MediaController.getInstance().isMessagePaused() == false) goto L51;
            MediaController.getInstance().playMessage(r105);
            goto L52
        L51:
            MediaController.getInstance().pauseMessage(r105);
            goto L52
        L5:
            if (this.dragging == true) goto L7;
            return false;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void requestDisallowInterceptTouchEvent(boolean r1) {
            super.requestDisallowInterceptTouchEvent(r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.PipRoundVideoView$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends AspectRatioFrameLayout {
        final /* synthetic */ PipRoundVideoView this$0;

        public AnonymousClass2(PipRoundVideoView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r7, View r8, long r9) {
            boolean r92 = super.drawChild(r7, r8, r9);
            if (r8 != PipRoundVideoView.m11789$$Nest$fgettextureView(this.this$0)) goto L7;
            MessageObject r82 = MediaController.getInstance().getPlayingMessageObject();
            if (r82 == null) goto L7;
            PipRoundVideoView.m11788$$Nest$fgetrect(this.this$0).set(AndroidUtilities.dpf2(1.5f), AndroidUtilities.dpf2(1.5f), getMeasuredWidth() - AndroidUtilities.dpf2(1.5f), getMeasuredHeight() - AndroidUtilities.dpf2(1.5f));
            r7.drawArc(PipRoundVideoView.m11788$$Nest$fgetrect(this.this$0), -90.0f, r82.audioProgress * 360.0f, false, Theme.chat_radialProgressPaint);
        L7:
            return r92;
        }
    }

    /* renamed from: org.telegram.ui.Components.PipRoundVideoView$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends ViewOutlineProvider {
        final /* synthetic */ PipRoundVideoView this$0;

        public AnonymousClass3(PipRoundVideoView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r2, Outline r3) {
            r3.setOval(0, 0, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.PipRoundVideoView$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ PipRoundVideoView this$0;

        public AnonymousClass4(PipRoundVideoView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(PipRoundVideoView.m11786$$Nest$fgethideShowAnimation(this.this$0)) == false) goto L6;
            PipRoundVideoView.m11794$$Nest$fputhideShowAnimation(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.PipRoundVideoView$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ PipRoundVideoView this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass5(PipRoundVideoView r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (r2.equals(PipRoundVideoView.m11786$$Nest$fgethideShowAnimation(this.this$0)) == false) goto L6;
            PipRoundVideoView.m11794$$Nest$fputhideShowAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(PipRoundVideoView.m11786$$Nest$fgethideShowAnimation(this.this$0)) == true) goto L5;
            return;
        L5:
            if (this.val$show == true) goto L7;
            this.this$0.close(false);
        L7:
            PipRoundVideoView.m11794$$Nest$fputhideShowAnimation(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.PipRoundVideoView$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ PipRoundVideoView this$0;

        public AnonymousClass6(PipRoundVideoView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.close(false);
            if (PipRoundVideoView.m11787$$Nest$fgetonCloseRunnable(this.this$0) == null) goto L6;
            PipRoundVideoView.m11787$$Nest$fgetonCloseRunnable(this.this$0).run();
            return;
        }
    }

    /* loaded from: classes7.dex */
    public class PipFrameLayout extends FrameLayout {
        final /* synthetic */ PipRoundVideoView this$0;

        public PipFrameLayout(PipRoundVideoView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }
    }

    /* renamed from: -$$Nest$fgethideShowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m11786$$Nest$fgethideShowAnimation(PipRoundVideoView r0) {
        return r0.hideShowAnimation;
    }

    /* renamed from: -$$Nest$fgetonCloseRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m11787$$Nest$fgetonCloseRunnable(PipRoundVideoView r0) {
        return r0.onCloseRunnable;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m11788$$Nest$fgetrect(PipRoundVideoView r0) {
        return r0.rect;
    }

    /* renamed from: -$$Nest$fgettextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m11789$$Nest$fgettextureView(PipRoundVideoView r0) {
        return r0.textureView;
    }

    /* renamed from: -$$Nest$fgetvideoWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11790$$Nest$fgetvideoWidth(PipRoundVideoView r0) {
        return r0.videoWidth;
    }

    /* renamed from: -$$Nest$fgetwindowLayoutParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager.LayoutParams m11791$$Nest$fgetwindowLayoutParams(PipRoundVideoView r0) {
        return r0.windowLayoutParams;
    }

    /* renamed from: -$$Nest$fgetwindowManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager m11792$$Nest$fgetwindowManager(PipRoundVideoView r0) {
        return r0.windowManager;
    }

    /* renamed from: -$$Nest$fgetwindowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m11793$$Nest$fgetwindowView(PipRoundVideoView r0) {
        return r0.windowView;
    }

    /* renamed from: -$$Nest$fputhideShowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11794$$Nest$fputhideShowAnimation(PipRoundVideoView r0, AnimatorSet r1) {
        r0.hideShowAnimation = r1;
    }

    /* renamed from: -$$Nest$manimateToBoundsMaybe, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11795$$Nest$manimateToBoundsMaybe(PipRoundVideoView r0) {
        r0.animateToBoundsMaybe();
    }

    public PipRoundVideoView() {
        this.rect = new RectF();
    }

    private void animateToBoundsMaybe() {
        int r1 = getSideCoord(true, 0, 0.0f, this.videoWidth);
        int r5 = getSideCoord(true, 1, 0.0f, this.videoWidth);
        int r6 = getSideCoord(false, 0, 0.0f, this.videoHeight);
        int r7 = getSideCoord(false, 1, 0.0f, this.videoHeight);
        SharedPreferences.Editor r8 = this.preferences.edit();
        int r9 = AndroidUtilities.dp(20.0f);
        if (Math.abs(r1 - this.windowLayoutParams.x) <= r9) goto L8;
        int r10 = this.windowLayoutParams.x;
        if (r10 >= 0) goto L10;
        if (r10 > ((-this.videoWidth) / 4)) goto L8;
    L10:
        if (Math.abs(r5 - r10) <= r9) goto L25;
        int r102 = this.windowLayoutParams.x;
        int r15 = AndroidUtilities.displaySize.x;
        float r16 = 0.0f;
        int r4 = this.videoWidth;
        float r17 = 1.0f;
        if (r102 <= (r15 - r4)) goto L17;
        if (r102 >= (r15 - ((r4 / 4) * 3))) goto L17;
    L26:
        ArrayList r12 = new ArrayList();
        r8.putInt("sidex", 1);
        if (this.windowView.getAlpha() == r17) goto L29;
        r12.add(ObjectAnimator.ofFloat(this.windowView, View.ALPHA, new float[]{r17}));
    L29:
        r12.add(ObjectAnimator.ofInt(this, "x", new int[]{r5}));
    L24:
        boolean r42 = false;
    L34:
        if (r42 == false) goto L36;
    L50:
        if (r12 != null) goto L52;
        return;
    L52:
        if (this.decelerateInterpolator != null) goto L54;
        this.decelerateInterpolator = new DecelerateInterpolator();
    L54:
        AnimatorSet r52 = new AnimatorSet();
        r52.setInterpolator(this.decelerateInterpolator);
        r52.setDuration(150);
        if (r42 == false) goto L57;
        r12.add(ObjectAnimator.ofFloat(this.windowView, View.ALPHA, new float[]{r16}));
        r52.addListener(new AnonymousClass6(this));
    L57:
        r52.playTogether(r12);
        r52.start();
        return;
    L36:
        if (Math.abs(r6 - this.windowLayoutParams.y) > r9) goto L38;
    L46:
        if (r12 != null) goto L48;
        r12 = new ArrayList();
    L48:
        r8.putInt("sidey", 0);
        r12.add(ObjectAnimator.ofInt(this, "y", new int[]{r6}));
    L49:
        r8.apply();
        goto L50
    L38:
        if (this.windowLayoutParams.y <= ActionBar.getCurrentActionBarHeight()) goto L46;
        if (Math.abs(r7 - this.windowLayoutParams.y) > r9) goto L45;
        if (r12 != null) goto L44;
        r12 = new ArrayList();
    L44:
        r8.putInt("sidey", 1);
        r12.add(ObjectAnimator.ofInt(this, "y", new int[]{r7}));
        goto L49
    L45:
        r8.putFloat("py", (this.windowLayoutParams.y - r6) / (r7 - r6));
        r8.putInt("sidey", 2);
    L17:
        if (this.windowView.getAlpha() == 1.0f) goto L23;
        r12 = new ArrayList();
        if (this.windowLayoutParams.x >= 0) goto L21;
        r12.add(ObjectAnimator.ofInt(this, "x", new int[]{-this.videoWidth}));
    L22:
        r42 = true;
        goto L34
    L21:
        r12.add(ObjectAnimator.ofInt(this, "x", new int[]{AndroidUtilities.displaySize.x}));
        goto L22
    L23:
        r8.putFloat("px", (this.windowLayoutParams.x - r1) / (r5 - r1));
        r8.putInt("sidex", 2);
        r12 = null;
        goto L24
    L25:
        r16 = 0.0f;
        r17 = 1.0f;
    L8:
        r16 = 0.0f;
        ArrayList r43 = new ArrayList();
        r8.putInt("sidex", 0);
        if (this.windowView.getAlpha() == 1.0f) goto L33;
        r43.add(ObjectAnimator.ofFloat(this.windowView, View.ALPHA, new float[]{1.0f}));
    L33:
        r43.add(ObjectAnimator.ofInt(this, "x", new int[]{r1}));
        r12 = r43;
        goto L24
    }

    public static PipRoundVideoView getInstance() {
        return instance;
    }

    private static int getSideCoord(boolean r2, int r3, float r4, int r5) {
        if (r2 == false) goto L5;
        int r0 = AndroidUtilities.displaySize.x;
    L4:
        int r02 = r0 - r5;
        if (r3 != 0) goto L10;
        int r32 = AndroidUtilities.dp(10.0f);
    L13:
        if (r2 == false) goto L15;
        return r32;
    L15:
        return r32 + ActionBar.getCurrentActionBarHeight();
    L10:
        if (r3 != 1) goto L12;
        r32 = r02 - AndroidUtilities.dp(10.0f);
        goto L13
    L12:
        r32 = Math.round((r02 - AndroidUtilities.dp(20.0f)) * r4) + AndroidUtilities.dp(10.0f);
        goto L13
    L5:
        r0 = AndroidUtilities.displaySize.y - r5;
        r5 = ActionBar.getCurrentActionBarHeight();
        goto L4
    }

    private void runShowHideAnimation(boolean r11) {
        AnimatorSet r0 = this.hideShowAnimation;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        AnimatorSet r02 = new AnimatorSet();
        this.hideShowAnimation = r02;
        FrameLayout r1 = this.windowView;
        Property r2 = View.ALPHA;
        float r3 = 1.0f;
        if (r11 == false) goto L8;
        float r4 = 1.0f;
    L9:
        ObjectAnimator r12 = ObjectAnimator.ofFloat(r1, r2, new float[]{r4});
        FrameLayout r22 = this.windowView;
        Property r42 = View.SCALE_X;
        if (r11 == false) goto L12;
        float r8 = 1.0f;
    L13:
        ObjectAnimator r23 = ObjectAnimator.ofFloat(r22, r42, new float[]{r8});
        FrameLayout r43 = this.windowView;
        Property r82 = View.SCALE_Y;
        if (r11 == true) goto L17;
        r3 = 0.8f;
    L17:
        r02.playTogether(new Animator[]{r12, r23, ObjectAnimator.ofFloat(r43, r82, new float[]{r3})});
        this.hideShowAnimation.setDuration(150);
        if (this.decelerateInterpolator != null) goto L20;
        this.decelerateInterpolator = new DecelerateInterpolator();
    L20:
        this.hideShowAnimation.addListener(new AnonymousClass5(this, r11));
        this.hideShowAnimation.setInterpolator(this.decelerateInterpolator);
        this.hideShowAnimation.start();
        return;
    L12:
        r8 = 0.8f;
        goto L13
    L8:
        r4 = 0.0f;
        goto L9
    }

    public void close(boolean r4) {
        if (r4 == false) goto L20;
        TextureView r42 = this.textureView;
        if (r42 != null) goto L7;
        return;
    L7:
        if (r42.getParent() != null) goto L9;
        return;
    L9:
        if (this.textureView.getWidth() > 0) goto L11;
    L34:
        this.textureView.getBitmap(this.bitmap);     // Catch: Throwable -> L15
    L16:
        this.imageView.setImageBitmap(this.bitmap);
        this.aspectRatioFrameLayout.removeView(this.textureView);     // Catch: Exception -> L28
    L18:
        this.imageView.setVisibility(0);
        runShowHideAnimation(false);
        return;
    L15:
        this.bitmap = null;
        goto L16
    L11:
        if (this.textureView.getHeight() <= 0) goto L34;
        this.bitmap = Bitmaps.createBitmap(this.textureView.getWidth(), this.textureView.getHeight(), Bitmap.Config.ARGB_8888);
        goto L34
    L20:
        if (this.bitmap == null) goto L30;
        this.imageView.setImageDrawable(null);
        this.bitmap.recycle();
        this.bitmap = null;
    L30:
        this.windowManager.removeView(this.windowView);     // Catch: Exception -> L29
    L24:
        if (instance != this) goto L26;
        instance = null;
    L26:
        this.parentActivity = null;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.messagePlayingProgressDidChanged) goto L8;
        AspectRatioFrameLayout r0 = this.aspectRatioFrameLayout;
        if (r0 == null) goto L9;
        r0.invalidate();
        return;
    L9:
        return;
    }

    public TextureView getTextureView() {
        return this.textureView;
    }

    @Keep
    public int getX() {
        return this.windowLayoutParams.x;
    }

    @Keep
    public int getY() {
        return this.windowLayoutParams.y;
    }

    public void onConfigurationChanged() {
        int r0 = this.preferences.getInt("sidex", 1);
        int r1 = this.preferences.getInt("sidey", 0);
        float r3 = this.preferences.getFloat("px", 0.0f);
        float r5 = this.preferences.getFloat("py", 0.0f);
        WindowManager.LayoutParams r6 = this.windowLayoutParams;
        r6.x = getSideCoord(true, r0, r3, this.videoWidth);
        WindowManager.LayoutParams r02 = this.windowLayoutParams;
        r02.y = getSideCoord(false, r1, r5, this.videoHeight);
        this.windowManager.updateViewLayout(this.windowView, this.windowLayoutParams);
    }

    @Keep
    public void setX(int r2) {
        WindowManager.LayoutParams r0 = this.windowLayoutParams;
        r0.x = r2;
        this.windowManager.updateViewLayout(this.windowView, r0);     // Catch: Exception -> L5
        return;
    }

    @Keep
    public void setY(int r2) {
        WindowManager.LayoutParams r0 = this.windowLayoutParams;
        r0.y = r2;
        this.windowManager.updateViewLayout(this.windowView, r0);     // Catch: Exception -> L5
        return;
    }

    public void show(Activity r12, Runnable r13) {
        if (r12 != null) goto L4;
        return;
    L4:
        instance = this;
        this.onCloseRunnable = r13;
        AnonymousClass1 r132 = new AnonymousClass1(this, r12);
        this.windowView = r132;
        r132.setWillNotDraw(false);
        this.videoWidth = AndroidUtilities.dp(126.0f);
        this.videoHeight = AndroidUtilities.dp(126.0f);
        AnonymousClass2 r133 = new AnonymousClass2(this, r12);
        this.aspectRatioFrameLayout = r133;
        r133.setOutlineProvider(new AnonymousClass3(this));
        this.aspectRatioFrameLayout.setClipToOutline(true);
        this.aspectRatioFrameLayout.setAspectRatio(1.0f, 0);
        this.windowView.addView(this.aspectRatioFrameLayout, LayoutHelper.createFrame(120, 120.0f, 51, 3.0f, 3.0f, 0.0f, 0.0f));
        this.windowView.setAlpha(1.0f);
        this.windowView.setScaleX(0.8f);
        this.windowView.setScaleY(0.8f);
        this.textureView = new TextureView(r12);
        float r2 = (AndroidUtilities.dpf2(120.0f) + AndroidUtilities.dpf2(2.0f)) / AndroidUtilities.dpf2(120.0f);
        this.textureView.setScaleX(r2);
        this.textureView.setScaleY(r2);
        this.aspectRatioFrameLayout.addView(this.textureView, LayoutHelper.createFrame(-1, -1.0f));
        ImageView r134 = new ImageView(r12);
        this.imageView = r134;
        this.aspectRatioFrameLayout.addView(r134, LayoutHelper.createFrame(-1, -1.0f));
        this.imageView.setVisibility(4);
        this.windowManager = (WindowManager) r12.getSystemService("window");
        SharedPreferences r135 = ApplicationLoader.applicationContext.getSharedPreferences("pipconfig", 0);
        this.preferences = r135;
        int r136 = r135.getInt("sidex", 1);
        int r22 = this.preferences.getInt("sidey", 0);
        float r3 = this.preferences.getFloat("px", 0.0f);
        float r4 = this.preferences.getFloat("py", 0.0f);
        WindowManager.LayoutParams r5 = new WindowManager.LayoutParams();     // Catch: Exception -> L8
        this.windowLayoutParams = r5;     // Catch: Exception -> L8
        int r6 = this.videoWidth;     // Catch: Exception -> L8
        r5.width = r6;     // Catch: Exception -> L8
        r5.height = this.videoHeight;     // Catch: Exception -> L8
        r5.x = getSideCoord(true, r136, r3, r6);     // Catch: Exception -> L8
        WindowManager.LayoutParams r137 = this.windowLayoutParams;     // Catch: Exception -> L8
        r137.y = getSideCoord(false, r22, r4, this.videoHeight);     // Catch: Exception -> L8
        WindowManager.LayoutParams r138 = this.windowLayoutParams;     // Catch: Exception -> L8
        r138.format = -3;     // Catch: Exception -> L8
        r138.gravity = 51;     // Catch: Exception -> L8
        r138.type = 99;     // Catch: Exception -> L8
        r138.flags = 16777736;     // Catch: Exception -> L8
        AndroidUtilities.setPreferredMaxRefreshRate(this.windowManager, this.windowView, r138);     // Catch: Exception -> L8
        this.windowManager.addView(this.windowView, this.windowLayoutParams);     // Catch: Exception -> L8
        this.parentActivity = r12;
        int r122 = UserConfig.selectedAccount;
        this.currentAccount = r122;
        NotificationCenter.getInstance(r122).addObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
        runShowHideAnimation(true);
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    public void showTemporary(boolean r11) {
        AnimatorSet r0 = this.hideShowAnimation;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        AnimatorSet r02 = new AnimatorSet();
        this.hideShowAnimation = r02;
        FrameLayout r1 = this.windowView;
        Property r2 = View.ALPHA;
        float r3 = 1.0f;
        if (r11 == false) goto L8;
        float r4 = 1.0f;
    L9:
        ObjectAnimator r12 = ObjectAnimator.ofFloat(r1, r2, new float[]{r4});
        FrameLayout r22 = this.windowView;
        Property r42 = View.SCALE_X;
        if (r11 == false) goto L12;
        float r8 = 1.0f;
    L13:
        ObjectAnimator r23 = ObjectAnimator.ofFloat(r22, r42, new float[]{r8});
        FrameLayout r43 = this.windowView;
        Property r82 = View.SCALE_Y;
        if (r11 == true) goto L17;
        r3 = 0.8f;
    L17:
        r02.playTogether(new Animator[]{r12, r23, ObjectAnimator.ofFloat(r43, r82, new float[]{r3})});
        this.hideShowAnimation.setDuration(150);
        if (this.decelerateInterpolator != null) goto L20;
        this.decelerateInterpolator = new DecelerateInterpolator();
    L20:
        this.hideShowAnimation.addListener(new AnonymousClass4(this));
        this.hideShowAnimation.setInterpolator(this.decelerateInterpolator);
        this.hideShowAnimation.start();
        return;
    L12:
        r8 = 0.8f;
        goto L13
    L8:
        r4 = 0.0f;
        goto L9
    }
}
