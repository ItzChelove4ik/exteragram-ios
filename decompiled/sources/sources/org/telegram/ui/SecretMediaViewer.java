package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.util.Property;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.core.view.WindowInsetsCompat$Impl30$$ExternalSyntheticApiModelOutline0;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.utils.WindowVisibilityManager;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PlayPauseDrawable;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.Scroller;
import org.telegram.ui.Components.TimerParticles;
import org.telegram.ui.Components.TranslateAlert2;
import org.telegram.ui.Components.VideoPlayer;
import org.telegram.ui.Components.VideoPlayerSeekBar;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes6.dex */
public class SecretMediaViewer implements NotificationCenter.NotificationCenterDelegate, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    @SuppressLint({"StaticFieldLeak"})
    private static volatile SecretMediaViewer Instance;
    public final Property<SecretMediaViewer, Float> ANIMATION_VALUE;
    public final Property<SecretMediaViewer, Float> VIDEO_CROSSFADE_ALPHA;
    private ActionBar actionBar;
    private WindowVisibilityManager.Controller activityVisibilityController;
    private int[] animateFromRadius;
    private float animateToClipBottom;
    private float animateToClipBottomOrigin;
    private float animateToClipHorizontal;
    private float animateToClipTop;
    private float animateToClipTopOrigin;
    private boolean animateToRadius;
    private float animateToScale;
    private float animateToX;
    private float animateToY;
    private long animationStartTime;

    @Keep
    private float animationValue;
    private AspectRatioFrameLayout aspectRatioFrameLayout;
    private Paint blackPaint;
    private boolean canDragDown;
    private FrameLayout captionContainer;
    private boolean captionHwLayerEnabled;
    private PhotoViewer.CaptionScrollView captionScrollView;
    private PhotoViewer.CaptionTextViewSwitcher captionTextViewSwitcher;
    private ImageReceiver centerImage;
    private float clipBottom;
    private float clipBottomOrigin;
    private float clipHorizontal;
    private float clipTop;
    private float clipTopOrigin;
    private boolean closeAfterAnimation;
    private long closeTime;
    private boolean closeVideoAfterWatch;
    private FrameLayoutDrawer containerView;
    private int[] coords;
    private int currentAccount;
    private AnimatorSet currentActionBarAnimation;
    private long currentDialogId;
    private MessageObject currentMessageObject;
    private PhotoViewer.PhotoViewerProvider currentProvider;
    private float[] currentRadii;
    private ImageReceiver.BitmapHolder currentThumb;
    private boolean disableShowCheck;
    private boolean discardTap;
    private boolean doubleTap;
    private float dragY;
    private boolean draggingDown;
    private GestureDetector gestureDetector;
    private final Runnable hideActionBarRunnable;
    private boolean ignoreDelete;
    private AnimatorSet imageMoveAnimation;
    private DecelerateInterpolator interpolator;
    private boolean invalidCoords;
    private boolean isActionBarVisible;
    private boolean isPhotoVisible;
    private boolean isPlaying;
    private boolean isVideo;
    private boolean isVisible;
    private Object lastInsets;
    private float maxX;
    private float maxY;
    private float minX;
    private float minY;
    private float moveStartX;
    private float moveStartY;
    private boolean moving;
    private View navigationBar;
    private Runnable onClose;
    private long openTime;
    private Activity parentActivity;
    private Runnable photoAnimationEndRunnable;
    private int photoAnimationInProgress;
    private PhotoBackgroundDrawable photoBackgroundDrawable;
    private long photoTransitionAnimationStartTime;
    private float pinchCenterX;
    private float pinchCenterY;
    private float pinchStartDistance;
    private float pinchStartScale;
    private float pinchStartX;
    private float pinchStartY;
    private ImageView playButton;
    private PlayPauseDrawable playButtonDrawable;
    private boolean playButtonShown;
    private int playerRetryPlayCount;
    private Path roundRectPath;
    private float scale;
    private Scroller scroller;
    private SecretDeleteTimer secretDeleteTimer;
    private HintView2 secretHint;
    private VideoPlayerSeekBar seekbar;
    private View seekbarBackground;
    private VideoPlayerControlFrameLayout seekbarContainer;
    private View seekbarView;
    private TextSelectionHelper.SimpleTextSelectionHelper textSelectionHelper;
    private boolean textureUploaded;
    private float translationX;
    private float translationY;
    private final Runnable updateProgressRunnable;
    private boolean useOvershootForScale;
    private VelocityTracker velocityTracker;
    private float videoCrossfadeAlpha;
    private long videoCrossfadeAlphaLastTime;
    private boolean videoCrossfadeStarted;
    private int videoHeight;
    private VideoPlayer videoPlayer;
    private final int[] videoPlayerCurrentTime;
    private SimpleTextView videoPlayerTime;
    private final int[] videoPlayerTotalTime;
    private TextureView videoTextureView;
    private boolean videoWatchedOneTime;
    private int videoWidth;
    private boolean wasLightNavigationBar;
    private int wasNavigationBarColor;
    private WindowManager.LayoutParams windowLayoutParams;
    private FrameLayout windowView;
    private boolean zoomAnimation;
    private boolean zooming;

    /* renamed from: org.telegram.ui.SecretMediaViewer$1, reason: invalid class name */
    public class AnonymousClass1 extends VideoPlayer {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass1(SecretMediaViewer r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.VideoPlayer
        public void pause() {
            super.pause();
            SecretMediaViewer.m18542$$Nest$fgetplayButtonDrawable(this.this$0).setPause(false);
        }

        @Override // org.telegram.ui.Components.VideoPlayer
        public void play() {
            super.play();
            SecretMediaViewer.m18542$$Nest$fgetplayButtonDrawable(this.this$0).setPause(true);
        }

        @Override // org.telegram.ui.Components.VideoPlayer
        public void setPlayWhenReady(boolean r1) {
            super.setPlayWhenReady(r1);
            SecretMediaViewer.m18542$$Nest$fgetplayButtonDrawable(this.this$0).setPause(r1);
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$10, reason: invalid class name */
    public class AnonymousClass10 extends PhotoViewer.CaptionScrollView {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass10(SecretMediaViewer r1, Context r2, PhotoViewer.CaptionTextViewSwitcher r3, FrameLayout r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.PhotoViewer.CaptionScrollView
        public void onScrollEnd() {
            if (SecretMediaViewer.m18537$$Nest$fgetisVideo(this.this$0) == true) goto L5;
            return;
        L5:
            if (getScrollY() > 0) goto L9;
            AndroidUtilities.runOnUIThread(SecretMediaViewer.m18531$$Nest$fgethideActionBarRunnable(this.this$0), 3000);
            return;
        }

        @Override // org.telegram.ui.PhotoViewer.CaptionScrollView
        public void onScrollStart() {
            AndroidUtilities.cancelRunOnUIThread(SecretMediaViewer.m18531$$Nest$fgethideActionBarRunnable(this.this$0));
        }

        @Override // org.telegram.ui.PhotoViewer.CaptionScrollView
        public void onScrollUpdate() {
            if (SecretMediaViewer.m18533$$Nest$fgetimageMoveAnimation(this.this$0) != null) goto L12;
            SecretMediaViewer r0 = this.this$0;
            if (getScrollY() < (getMeasuredHeight() / 3.0f)) goto L7;
        L9:
            boolean r4 = false;
        L10:
            SecretMediaViewer.m18568$$Nest$mshowPlayButton(r0, r4, true);
            return;
        L7:
            if (SecretMediaViewer.m18534$$Nest$fgetisActionBarVisible(this.this$0) == false) goto L9;
            r4 = true;
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$11, reason: invalid class name */
    public class AnonymousClass11 extends Fade {
        final /* synthetic */ SecretMediaViewer this$0;
        final /* synthetic */ boolean val$isCaptionEmpty;
        final /* synthetic */ boolean val$isCurrentCaptionEmpty;

        /* renamed from: org.telegram.ui.SecretMediaViewer$11$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass11 this$1;

            public AnonymousClass1(AnonymousClass11 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$1.this$0).backgroundAlpha = 1.0f;
            }
        }

        /* renamed from: $r8$lambda$g4FMKBkGzyuSD3MVwc6x7-n17wY, reason: not valid java name */
        public static /* synthetic */ void m18570$r8$lambda$g4FMKBkGzyuSD3MVwc6x7n17wY(AnonymousClass11 r0, ValueAnimator r1) {
            r0.lambda$onAppear$0(r1);
        }

        public AnonymousClass11(SecretMediaViewer r1, int r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            this.val$isCurrentCaptionEmpty = r3;
            this.val$isCaptionEmpty = r4;
            super(r2);
        }

        private /* synthetic */ void lambda$onAppear$0(ValueAnimator r2) {
            PhotoViewer.CaptionScrollView r0 = SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0);
            r0.backgroundAlpha = ((Float) r2.getAnimatedValue()).floatValue();
            SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0).invalidate();
        }

        @Override // android.transition.Fade, android.transition.Visibility
        public Animator onAppear(ViewGroup r1, View r2, TransitionValues r3, TransitionValues r4) {
            Animator r12 = super.onAppear(r1, r2, r3, r4);
            if (this.val$isCurrentCaptionEmpty == true) goto L5;
        L9:
            return r12;
        L5:
            if (this.val$isCaptionEmpty == true) goto L9;
            if (r2 != SecretMediaViewer.m18524$$Nest$fgetcaptionTextViewSwitcher(this.this$0)) goto L9;
            r12.addListener(new AnonymousClass1(this));
            ((ObjectAnimator) r12).addUpdateListener(new SecretMediaViewer$11$$ExternalSyntheticLambda0(this));
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$12, reason: invalid class name */
    public class AnonymousClass12 extends Fade {
        final /* synthetic */ SecretMediaViewer this$0;
        final /* synthetic */ boolean val$isCaptionEmpty;
        final /* synthetic */ boolean val$isCurrentCaptionEmpty;

        /* renamed from: org.telegram.ui.SecretMediaViewer$12$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass12 this$1;

            public AnonymousClass1(AnonymousClass12 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$1.this$0).setVisibility(4);
                SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$1.this$0).backgroundAlpha = 1.0f;
            }
        }

        /* renamed from: $r8$lambda$IMiHMsj-uqqWYAUL2IoKCrI15t0, reason: not valid java name */
        public static /* synthetic */ void m18571$r8$lambda$IMiHMsjuqqWYAUL2IoKCrI15t0(AnonymousClass12 r0, ValueAnimator r1) {
            r0.lambda$onDisappear$0(r1);
        }

        public AnonymousClass12(SecretMediaViewer r1, int r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            this.val$isCurrentCaptionEmpty = r3;
            this.val$isCaptionEmpty = r4;
            super(r2);
        }

        private /* synthetic */ void lambda$onDisappear$0(ValueAnimator r2) {
            PhotoViewer.CaptionScrollView r0 = SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0);
            r0.backgroundAlpha = ((Float) r2.getAnimatedValue()).floatValue();
            SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0).invalidate();
        }

        @Override // android.transition.Fade, android.transition.Visibility
        public Animator onDisappear(ViewGroup r1, View r2, TransitionValues r3, TransitionValues r4) {
            Animator r12 = super.onDisappear(r1, r2, r3, r4);
            if (this.val$isCurrentCaptionEmpty == false) goto L5;
        L9:
            return r12;
        L5:
            if (this.val$isCaptionEmpty == false) goto L9;
            if (r2 != SecretMediaViewer.m18524$$Nest$fgetcaptionTextViewSwitcher(this.this$0)) goto L9;
            r12.addListener(new AnonymousClass1(this));
            ((ObjectAnimator) r12).addUpdateListener(new SecretMediaViewer$12$$ExternalSyntheticLambda0(this));
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$13, reason: invalid class name */
    public class AnonymousClass13 extends Transition {
        final /* synthetic */ SecretMediaViewer this$0;

        /* renamed from: org.telegram.ui.SecretMediaViewer$13$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass13 this$1;

            public AnonymousClass1(AnonymousClass13 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                SecretMediaViewer.m18524$$Nest$fgetcaptionTextViewSwitcher(this.this$1.this$0).getNextView().setText(null);
                SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$1.this$0).applyPendingTopMargin();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator r1) {
                SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$1.this$0).stopScrolling();
            }
        }

        /* renamed from: org.telegram.ui.SecretMediaViewer$13$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass13 this$1;

            public AnonymousClass2(AnonymousClass13 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                SecretMediaViewer.m18524$$Nest$fgetcaptionTextViewSwitcher(this.this$1.this$0).setTranslationY(0.0f);
            }
        }

        public static /* synthetic */ void $r8$lambda$0MLp920X3rCkRhztWQkw_B_tThY(AnonymousClass13 r0, ValueAnimator r1) {
            r0.lambda$createAnimator$0(r1);
        }

        public AnonymousClass13(SecretMediaViewer r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$createAnimator$0(ValueAnimator r2) {
            SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0).scrollTo(0, ((Integer) r2.getAnimatedValue()).intValue());
        }

        @Override // android.transition.Transition
        public void captureEndValues(TransitionValues r3) {
            if (r3.view != SecretMediaViewer.m18524$$Nest$fgetcaptionTextViewSwitcher(this.this$0)) goto L6;
            r3.values.put("translationY", Integer.valueOf(SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0).getPendingMarginTopDiff()));
            return;
        }

        @Override // android.transition.Transition
        public void captureStartValues(TransitionValues r3) {
            if (r3.view != SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0)) goto L6;
            r3.values.put("scrollY", Integer.valueOf(SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0).getScrollY()));
            return;
        }

        @Override // android.transition.Transition
        public Animator createAnimator(ViewGroup r4, TransitionValues r5, TransitionValues r6) {
            if (r5.view != SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0)) goto L7;
            ValueAnimator r42 = ValueAnimator.ofInt(new int[]{((Integer) r5.values.get("scrollY")).intValue(), 0});
            r42.addListener(new AnonymousClass1(this));
            r42.addUpdateListener(new SecretMediaViewer$13$$ExternalSyntheticLambda0(this));
            return r42;
        L7:
            if (r6.view != SecretMediaViewer.m18524$$Nest$fgetcaptionTextViewSwitcher(this.this$0)) goto L12;
            int r43 = ((Integer) r6.values.get("translationY")).intValue();
            if (r43 == 0) goto L14;
            ObjectAnimator r44 = ObjectAnimator.ofFloat(SecretMediaViewer.m18524$$Nest$fgetcaptionTextViewSwitcher(this.this$0), View.TRANSLATION_Y, new float[]{0.0f, r43});
            r44.addListener(new AnonymousClass2(this));
            return r44;
        L14:
            return null;
        L12:
            return null;
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$14, reason: invalid class name */
    public class AnonymousClass14 extends AnimatorListenerAdapter {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass14(SecretMediaViewer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (SecretMediaViewer.m18541$$Nest$fgetphotoAnimationEndRunnable(this.this$0) == null) goto L6;
            SecretMediaViewer.m18541$$Nest$fgetphotoAnimationEndRunnable(this.this$0).run();
            SecretMediaViewer.m18558$$Nest$fputphotoAnimationEndRunnable(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$15, reason: invalid class name */
    public class AnonymousClass15 extends AnimatorListenerAdapter {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass15(SecretMediaViewer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (SecretMediaViewer.m18529$$Nest$fgetcurrentActionBarAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (SecretMediaViewer.m18529$$Nest$fgetcurrentActionBarAnimation(this.this$0).equals(r2) == false) goto L9;
            SecretMediaViewer.m18520$$Nest$fgetactionBar(this.this$0).setVisibility(8);
            SecretMediaViewer.m18554$$Nest$fputcurrentActionBarAnimation(this.this$0, null);
            SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0).scrollTo(0, 0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$16, reason: invalid class name */
    public class AnonymousClass16 extends AnimationProperties.FloatProperty<SecretMediaViewer> {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass16(SecretMediaViewer r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        public Float get(SecretMediaViewer r1) {
            return Float.valueOf(r1.getVideoCrossfadeAlpha());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((SecretMediaViewer) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(SecretMediaViewer r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(SecretMediaViewer r1, float r2) {
            r1.setVideoCrossfadeAlpha(r2);
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$17, reason: invalid class name */
    public class AnonymousClass17 extends AnimatorListenerAdapter {
        final /* synthetic */ SecretMediaViewer this$0;
        final /* synthetic */ PhotoViewer.PlaceProviderObject val$object;

        /* renamed from: $r8$lambda$6ZaHqdPxgo-pg7oNK7tBxfJOnJk, reason: not valid java name */
        public static /* synthetic */ void m18572$r8$lambda$6ZaHqdPxgopg7oNK7tBxfJOnJk(AnonymousClass17 r0) {
            r0.lambda$onAnimationEnd$0();
        }

        public AnonymousClass17(SecretMediaViewer r1, PhotoViewer.PlaceProviderObject r2) {
            this.this$0 = r1;
            this.val$object = r2;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0() {
            if (SecretMediaViewer.m18541$$Nest$fgetphotoAnimationEndRunnable(this.this$0) == null) goto L6;
            SecretMediaViewer.m18541$$Nest$fgetphotoAnimationEndRunnable(this.this$0).run();
            SecretMediaViewer.m18558$$Nest$fputphotoAnimationEndRunnable(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            PhotoViewer.PlaceProviderObject r22 = this.val$object;
            if (r22 == null) goto L5;
            r22.imageReceiver.setVisible(true, true);
        L5:
            SecretMediaViewer.m18557$$Nest$fputisVisible(this.this$0, false);
            AndroidUtilities.runOnUIThread(new SecretMediaViewer$17$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$18, reason: invalid class name */
    public class AnonymousClass18 extends AnimatorListenerAdapter {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass18(SecretMediaViewer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (SecretMediaViewer.m18541$$Nest$fgetphotoAnimationEndRunnable(this.this$0) == null) goto L6;
            SecretMediaViewer.m18541$$Nest$fgetphotoAnimationEndRunnable(this.this$0).run();
            SecretMediaViewer.m18558$$Nest$fputphotoAnimationEndRunnable(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$19, reason: invalid class name */
    public class AnonymousClass19 extends AnimatorListenerAdapter {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass19(SecretMediaViewer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            SecretMediaViewer.m18555$$Nest$fputimageMoveAnimation(this.this$0, null);
            SecretMediaViewer.m18527$$Nest$fgetcontainerView(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$2, reason: invalid class name */
    public class AnonymousClass2 implements VideoPlayer.VideoPlayerDelegate {
        final /* synthetic */ SecretMediaViewer this$0;
        final /* synthetic */ File val$file;

        /* renamed from: $r8$lambda$MU_okJKz02-GKU-qZE-pkMO5cxQ, reason: not valid java name */
        public static /* synthetic */ void m18573$r8$lambda$MU_okJKz02GKUqZEpkMO5cxQ(AnonymousClass2 r0, File r1) {
            r0.lambda$onError$0(r1);
        }

        public AnonymousClass2(SecretMediaViewer r1, File r2) {
            this.this$0 = r1;
            this.val$file = r2;
        }

        private /* synthetic */ void lambda$onError$0(File r1) {
            SecretMediaViewer.m18566$$Nest$mpreparePlayer(this.this$0, r1);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onError(VideoPlayer r1, Exception r2) {
            if (SecretMediaViewer.m18543$$Nest$fgetplayerRetryPlayCount(this.this$0) <= 0) goto L6;
            SecretMediaViewer.m18559$$Nest$fputplayerRetryPlayCount(this.this$0, SecretMediaViewer.m18543$$Nest$fgetplayerRetryPlayCount(r1) - 1);
            AndroidUtilities.runOnUIThread(new SecretMediaViewer$2$$ExternalSyntheticLambda0(this, this.val$file), 100);
            return;
        L6:
            FileLog.e(r2);
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onRenderedFirstFrame() {
            if (SecretMediaViewer.m18550$$Nest$fgettextureUploaded(this.this$0) == true) goto L6;
            SecretMediaViewer.m18561$$Nest$fputtextureUploaded(this.this$0, true);
            SecretMediaViewer.m18527$$Nest$fgetcontainerView(this.this$0).invalidate();
            return;
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onStateChanged(boolean r4, int r5) {
            if (SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0) != null) goto L5;
            return;
        L5:
            if (SecretMediaViewer.m18530$$Nest$fgetcurrentMessageObject(this.this$0) == null) goto L48;
            AndroidUtilities.cancelRunOnUIThread(SecretMediaViewer.m18551$$Nest$fgetupdateProgressRunnable(this.this$0));
            AndroidUtilities.runOnUIThread(SecretMediaViewer.m18551$$Nest$fgetupdateProgressRunnable(this.this$0));
            if (r5 == 4) goto L38;
            if (r5 == 1) goto L38;
            SecretMediaViewer.m18540$$Nest$fgetparentActivity(this.this$0).getWindow().addFlags(128);     // Catch: Exception -> L12
        L19:
            if (r5 != 3) goto L24;
            if (SecretMediaViewer.m18522$$Nest$fgetaspectRatioFrameLayout(this.this$0).getVisibility() == 0) goto L24;
            SecretMediaViewer.m18522$$Nest$fgetaspectRatioFrameLayout(this.this$0).setVisibility(0);
        L24:
            if (SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0).isPlaying() == false) goto L30;
            if (r5 == 4) goto L30;
            if (SecretMediaViewer.m18536$$Nest$fgetisPlaying(this.this$0) == true) goto L43;
            SecretMediaViewer.m18556$$Nest$fputisPlaying(this.this$0, true);
            return;
        L43:
            return;
        L30:
            if (SecretMediaViewer.m18536$$Nest$fgetisPlaying(this.this$0) == false) goto L45;
            SecretMediaViewer.m18556$$Nest$fputisPlaying(this.this$0, false);
            if (r5 != 4) goto L46;
            SecretMediaViewer.m18564$$Nest$fputvideoWatchedOneTime(this.this$0, true);
            boolean r42 = SecretMediaViewer.m18526$$Nest$fgetcloseVideoAfterWatch(this.this$0);
            SecretMediaViewer r52 = this.this$0;
            if (r42 == false) goto L36;
            r52.closePhoto(true, !SecretMediaViewer.m18532$$Nest$fgetignoreDelete(r52));
            return;
        L36:
            SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(r52).seekTo(0);
            SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0).play();
            return;
        L46:
            return;
        L45:
            return;
        L12:
            e = move-exception;
            FileLog.e(e);
        L38:
            SecretMediaViewer.m18540$$Nest$fgetparentActivity(this.this$0).getWindow().clearFlags(128);     // Catch: Exception -> L16
        L16:
            e = move-exception;
            FileLog.e(e);
            goto L19
        }

        @Override // org.telegram.ui.Components.VideoPlayer.VideoPlayerDelegate
        public void onVideoSizeChanged(int r3, int r4, int r5, float r6) {
            if (SecretMediaViewer.m18522$$Nest$fgetaspectRatioFrameLayout(this.this$0) != null) goto L5;
            return;
        L5:
            if (r5 != 90) goto L7;
        L10:
            AspectRatioFrameLayout r2 = SecretMediaViewer.m18522$$Nest$fgetaspectRatioFrameLayout(this.this$0);
            if (r3 != 0) goto L13;
            float r32 = 1.0f;
        L14:
            r2.setAspectRatio(r32, r5);
            return;
        L13:
            r32 = (r4 * r6) / r3;
            goto L14
        L7:
            if (r5 == 270) goto L10;
            r4 = r3;
            r3 = r4;
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$20, reason: invalid class name */
    public class AnonymousClass20 extends AnimationProperties.FloatProperty<SecretMediaViewer> {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass20(SecretMediaViewer r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        public Float get(SecretMediaViewer r1) {
            return Float.valueOf(r1.getAnimationValue());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((SecretMediaViewer) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(SecretMediaViewer r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(SecretMediaViewer r1, float r2) {
            r1.setAnimationValue(r2);
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass3(SecretMediaViewer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            if (SecretMediaViewer.m18538$$Nest$fgetlastInsets(this.this$0) == null) goto L5;
            int r32 = ((WindowInsets) SecretMediaViewer.m18538$$Nest$fgetlastInsets(this.this$0)).getSystemWindowInsetLeft();
        L6:
            SecretMediaViewer.m18527$$Nest$fgetcontainerView(this.this$0).layout(r32, 0, SecretMediaViewer.m18527$$Nest$fgetcontainerView(this.this$0).getMeasuredWidth() + r32, SecretMediaViewer.m18527$$Nest$fgetcontainerView(this.this$0).getMeasuredHeight());
            if (r2 == true) goto L9;
            return;
        L9:
            if (SecretMediaViewer.m18533$$Nest$fgetimageMoveAnimation(this.this$0) != null) goto L11;
            SecretMediaViewer.m18560$$Nest$fputscale(this.this$0, 1.0f);
            SecretMediaViewer.m18562$$Nest$fputtranslationX(this.this$0, 0.0f);
            SecretMediaViewer.m18563$$Nest$fputtranslationY(this.this$0, 0.0f);
        L11:
            SecretMediaViewer r1 = this.this$0;
            SecretMediaViewer.m18569$$Nest$mupdateMinMax(r1, SecretMediaViewer.m18544$$Nest$fgetscale(r1));
            return;
        L5:
            r32 = 0;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            int r32 = View.MeasureSpec.getSize(r3);
            int r42 = View.MeasureSpec.getSize(r4);
            if (SecretMediaViewer.m18538$$Nest$fgetlastInsets(this.this$0) == null) goto L11;
            WindowInsets r0 = (WindowInsets) SecretMediaViewer.m18538$$Nest$fgetlastInsets(this.this$0);
            if (AndroidUtilities.incorrectDisplaySizeFix == false) goto L10;
            int r1 = AndroidUtilities.displaySize.y;
            if (r42 <= r1) goto L9;
            r42 = r1;
        L9:
            r42 = r42 + AndroidUtilities.statusBarHeight;
        L10:
            r42 = r42 - r0.getSystemWindowInsetBottom();
            r32 = r32 - r0.getSystemWindowInsetRight();
        L14:
            setMeasuredDimension(r32, r42);
            if (SecretMediaViewer.m18538$$Nest$fgetlastInsets(this.this$0) == null) goto L17;
            r32 = r32 - ((WindowInsets) SecretMediaViewer.m18538$$Nest$fgetlastInsets(this.this$0)).getSystemWindowInsetLeft();
        L17:
            SecretMediaViewer.m18527$$Nest$fgetcontainerView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r32, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r42, TLObject.FLAG_30));
            return;
        L11:
            int r02 = AndroidUtilities.displaySize.y;
            if (r42 <= r02) goto L14;
            r42 = r02;
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$4, reason: invalid class name */
    public class AnonymousClass4 extends FrameLayoutDrawer {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass4(SecretMediaViewer r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
            super.onLayout(r5, r6, r7, r8, r9);
            if (SecretMediaViewer.m18545$$Nest$fgetsecretDeleteTimer(this.this$0) == null) goto L6;
            int r52 = ((ActionBar.getCurrentActionBarHeight() - SecretMediaViewer.m18545$$Nest$fgetsecretDeleteTimer(this.this$0).getMeasuredHeight()) / 2) + AndroidUtilities.statusBarHeight;
            SecretMediaViewer.m18545$$Nest$fgetsecretDeleteTimer(this.this$0).layout(SecretMediaViewer.m18545$$Nest$fgetsecretDeleteTimer(this.this$0).getLeft(), r52, SecretMediaViewer.m18545$$Nest$fgetsecretDeleteTimer(this.this$0).getRight(), SecretMediaViewer.m18545$$Nest$fgetsecretDeleteTimer(this.this$0).getMeasuredHeight() + r52);
        L6:
            if (SecretMediaViewer.m18546$$Nest$fgetsecretHint(this.this$0) == null) goto L11;
            if (SecretMediaViewer.m18545$$Nest$fgetsecretDeleteTimer(this.this$0) == null) goto L11;
            int r53 = ((((ActionBar.getCurrentActionBarHeight() - SecretMediaViewer.m18545$$Nest$fgetsecretDeleteTimer(this.this$0).getMeasuredHeight()) / 2) + AndroidUtilities.statusBarHeight) + SecretMediaViewer.m18545$$Nest$fgetsecretDeleteTimer(this.this$0).getMeasuredHeight()) - AndroidUtilities.dp(10.0f);
            SecretMediaViewer.m18546$$Nest$fgetsecretHint(this.this$0).layout(SecretMediaViewer.m18546$$Nest$fgetsecretHint(this.this$0).getLeft(), r53, SecretMediaViewer.m18546$$Nest$fgetsecretHint(this.this$0).getRight(), SecretMediaViewer.m18546$$Nest$fgetsecretHint(this.this$0).getMeasuredHeight() + r53);
        L11:
            if (SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0) == null) goto L14;
            int r54 = ActionBar.getCurrentActionBarHeight() + AndroidUtilities.statusBarHeight;
            SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0).layout(SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0).getLeft(), r54, SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0).getRight(), SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0).getMeasuredHeight() + r54);
        L14:
            if (SecretMediaViewer.m18539$$Nest$fgetnavigationBar(this.this$0) == null) goto L17;
            int r92 = r9 - r7;
            SecretMediaViewer.m18539$$Nest$fgetnavigationBar(this.this$0).layout(0, r92, r8 - r6, AndroidUtilities.navigationBarHeight + r92);
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            super.onMeasure(r5, r6);
            int r52 = getMeasuredWidth();
            int r62 = getMeasuredHeight();
            if (SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0) == null) goto L10;
            PhotoViewer.CaptionScrollView r0 = SecretMediaViewer.m18523$$Nest$fgetcaptionScrollView(this.this$0);
            int r2 = View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30);
            int r63 = (r62 - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.statusBarHeight;
            if (SecretMediaViewer.m18548$$Nest$fgetseekbarContainer(this.this$0).getVisibility() == 0) goto L7;
            int r3 = 0;
        L8:
            r0.measure(r2, View.MeasureSpec.makeMeasureSpec(r63 - r3, TLObject.FLAG_30));
            goto L10
        L7:
            r3 = SecretMediaViewer.m18548$$Nest$fgetseekbarContainer(this.this$0).getMeasuredHeight();
        L10:
            if (SecretMediaViewer.m18539$$Nest$fgetnavigationBar(this.this$0) == null) goto L13;
            SecretMediaViewer.m18539$$Nest$fgetnavigationBar(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.navigationBarHeight, TLObject.FLAG_30));
            return;
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$5, reason: invalid class name */
    public class AnonymousClass5 extends ActionBar {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass5(SecretMediaViewer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setAlpha(float r2) {
            super.setAlpha(r2);
            SecretMediaViewer.m18546$$Nest$fgetsecretHint(this.this$0).setAlpha(r2);
            SecretMediaViewer.m18545$$Nest$fgetsecretDeleteTimer(this.this$0).setAlpha(r2);
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$6, reason: invalid class name */
    public class AnonymousClass6 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass6(SecretMediaViewer r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.closePhoto(true, false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$7, reason: invalid class name */
    public class AnonymousClass7 implements VideoPlayerSeekBar.SeekBarDelegate {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass7(SecretMediaViewer r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.VideoPlayerSeekBar.SeekBarDelegate
        public void onSeekBarContinuousDrag(float r5) {
            if (SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0) == null) goto L8;
            SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0).pause();
            long r0 = SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0).getDuration();
            if (r0 == (-9223372036854775807L)) goto L9;
            SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0).seekTo((long) (r5 * r0), false);
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.Components.VideoPlayerSeekBar.SeekBarDelegate
        public void onSeekBarDrag(float r5) {
            if (SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0) == null) goto L9;
            long r0 = SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0).getDuration();
            if (r0 == (-9223372036854775807L)) goto L7;
            SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0).seekTo((long) (r5 * r0), false);
        L7:
            SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0).play();
            return;
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$8, reason: invalid class name */
    public class AnonymousClass8 extends View {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass8(SecretMediaViewer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r2) {
            SecretMediaViewer.m18547$$Nest$fgetseekbar(this.this$0).draw(r2, this);
        }
    }

    /* renamed from: org.telegram.ui.SecretMediaViewer$9, reason: invalid class name */
    public class AnonymousClass9 extends TextSelectionHelper.SimpleTextSelectionHelper {
        final /* synthetic */ SecretMediaViewer this$0;

        public AnonymousClass9(SecretMediaViewer r1, TextSelectionHelper.SimpleSelectabeleView r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public int getParentBottomPadding() {
            return 0;
        }
    }

    public class FrameLayoutDrawer extends FrameLayout {
        final /* synthetic */ SecretMediaViewer this$0;

        public FrameLayoutDrawer(SecretMediaViewer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            setWillNotDraw(false);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != SecretMediaViewer.m18522$$Nest$fgetaspectRatioFrameLayout(this.this$0)) goto L5;
            return false;
        L5:
            if (super.drawChild(r2, r3, r4) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            SecretMediaViewer.m18525$$Nest$fgetcenterImage(this.this$0).onAttachedToWindow();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            SecretMediaViewer.m18525$$Nest$fgetcenterImage(this.this$0).onDetachedFromWindow();
        }

        @Override // android.view.View
        public void onDraw(Canvas r1) {
            SecretMediaViewer.m18565$$Nest$monDraw(this.this$0, r1);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r1) {
            SecretMediaViewer.m18567$$Nest$mprocessTouchEvent(this.this$0, r1);
            return true;
        }
    }

    public class PhotoBackgroundDrawable extends ColorDrawable {
        private Runnable drawRunnable;
        private int frame;
        final /* synthetic */ SecretMediaViewer this$0;

        /* renamed from: -$$Nest$fputdrawRunnable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18574$$Nest$fputdrawRunnable(PhotoBackgroundDrawable r0, Runnable r1) {
            r0.drawRunnable = r1;
        }

        /* renamed from: -$$Nest$fputframe, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18575$$Nest$fputframe(PhotoBackgroundDrawable r0, int r1) {
            r0.frame = r1;
        }

        public PhotoBackgroundDrawable(SecretMediaViewer r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.graphics.drawable.ColorDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas r2) {
            super.draw(r2);
            if (getAlpha() != 0) goto L5;
            return;
        L5:
            if (this.frame != 2) goto L9;
            Runnable r22 = this.drawRunnable;
            if (r22 == null) goto L9;
            r22.run();
            this.drawRunnable = null;
        L10:
            this.frame++;
            return;
        L9:
            invalidateSelf();
            goto L10
        }

        @Override // android.graphics.drawable.ColorDrawable, android.graphics.drawable.Drawable
        @Keep
        public void setAlpha(int r3) {
            if (SecretMediaViewer.m18521$$Nest$fgetactivityVisibilityController(this.this$0) == null) goto L11;
            WindowVisibilityManager.Controller r0 = SecretMediaViewer.m18521$$Nest$fgetactivityVisibilityController(this.this$0);
            if (SecretMediaViewer.m18535$$Nest$fgetisPhotoVisible(this.this$0) == true) goto L7;
        L9:
            boolean r1 = false;
        L10:
            r0.setHidden(r1);
            goto L11
        L7:
            if (r3 != 255) goto L9;
            r1 = true;
        L11:
            super.setAlpha(r3);
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(int r2, int r3, int r4, int r5) {
            super.setBounds(r2, r3, r4, r5 + AndroidUtilities.navigationBarHeight);
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(Rect r3) {
            r3.bottom += AndroidUtilities.navigationBarHeight;
            super.setBounds(r3);
        }
    }

    public class SecretDeleteTimer extends FrameLayout {
        private Paint afterDeleteProgressPaint;
        private Paint circlePaint;
        private RectF deleteProgressRect;
        private long destroyTime;
        private long destroyTtl;
        private RLottieDrawable drawable;
        private boolean once;
        private StaticLayout onceLayout;
        private float onceLayoutHeight;
        private float onceLayoutWidth;
        private TextPaint oncePaint;
        private Paint particlePaint;
        final /* synthetic */ SecretMediaViewer this$0;
        private TimerParticles timerParticles;
        private boolean useVideoProgress;

        /* renamed from: -$$Nest$msetDestroyTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18576$$Nest$msetDestroyTime(SecretDeleteTimer r0, long r1, long r3, boolean r5) {
            r0.setDestroyTime(r1, r3, r5);
        }

        /* renamed from: -$$Nest$msetOnce, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18577$$Nest$msetOnce(SecretDeleteTimer r0) {
            r0.setOnce();
        }

        public SecretDeleteTimer(SecretMediaViewer r4, Context r5) {
            this.this$0 = r4;
            super(r5);
            this.deleteProgressRect = new RectF();
            this.timerParticles = new TimerParticles();
            this.oncePaint = new TextPaint(1);
            setWillNotDraw(false);
            Paint r42 = new Paint(1);
            this.particlePaint = r42;
            r42.setStrokeWidth(AndroidUtilities.dp(1.5f));
            this.particlePaint.setColor(-1644826);
            Paint r43 = this.particlePaint;
            Paint.Cap r1 = Paint.Cap.ROUND;
            r43.setStrokeCap(r1);
            Paint r44 = this.particlePaint;
            Paint.Style r2 = Paint.Style.STROKE;
            r44.setStyle(r2);
            Paint r45 = new Paint(1);
            this.afterDeleteProgressPaint = r45;
            r45.setStyle(r2);
            this.afterDeleteProgressPaint.setStrokeCap(r1);
            this.afterDeleteProgressPaint.setColor(-1644826);
            this.afterDeleteProgressPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
            Paint r46 = new Paint(1);
            this.circlePaint = r46;
            r46.setColor(2130706432);
            RLottieDrawable r47 = new RLottieDrawable(R.raw.fire_on, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            this.drawable = r47;
            r47.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
            this.drawable.setMasterParent(this);
            this.drawable.start();
        }

        private void setDestroyTime(long r2, long r4, boolean r6) {
            this.once = false;
            this.destroyTime = r2;
            this.destroyTtl = r4;
            this.useVideoProgress = r6;
            this.drawable.start();
            invalidate();
        }

        private void setOnce() {
            this.once = true;
            this.oncePaint.setTextSize(AndroidUtilities.dp(13.0f));
            this.oncePaint.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
            this.oncePaint.setColor(-1);
            StaticLayout r2 = new StaticLayout("1", this.oncePaint, 999, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.onceLayout = r2;
            if (r2.getLineCount() <= 0) goto L5;
            float r0 = this.onceLayout.getLineWidth(0);
        L6:
            this.onceLayoutWidth = r0;
            this.onceLayoutHeight = this.onceLayout.getHeight();
            invalidate();
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }

        @Override // android.view.View
        @SuppressLint({"DrawAllocation"})
        public void onDraw(Canvas r14) {
            if (SecretMediaViewer.m18530$$Nest$fgetcurrentMessageObject(this.this$0) != null) goto L5;
            return;
        L5:
            if (SecretMediaViewer.m18530$$Nest$fgetcurrentMessageObject(this.this$0).messageOwner.destroyTime != 0) goto L10;
            if (SecretMediaViewer.m18530$$Nest$fgetcurrentMessageObject(this.this$0).messageOwner.ttl == Integer.MAX_VALUE) goto L10;
            return;
        L10:
            if (this.useVideoProgress == false) goto L20;
            if (SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0) == null) goto L18;
            long r2 = SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0).getDuration();
            long r4 = SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0).getCurrentPosition();
            if (r2 == (-9223372036854775807L)) goto L18;
            if (r4 == (-9223372036854775807L)) goto L18;
            float r0 = 1.0f - (r4 / r2);
        L24:
            if (this.once == false) goto L30;
            r14.save();
            r14.translate(this.deleteProgressRect.centerX() - (this.onceLayoutWidth / 2.0f), this.deleteProgressRect.centerY() - (this.onceLayoutHeight / 2.0f));
            this.onceLayout.draw(r14);
            r14.restore();
            r14.drawArc(this.deleteProgressRect, 90.0f, 180.0f, false, this.afterDeleteProgressPaint);
            float r142 = 19.285715f;
            int r02 = 0;
        L27:
            if (r02 >= 5) goto L29;
            r14.drawArc(this.deleteProgressRect, r142 + 270.0f, 12.857143f, false, this.afterDeleteProgressPaint);
            r142 = r142 + 32.14286f;
            r02 = r02 + 1;
            goto L27
        L29:
            this.timerParticles.draw(r14, this.particlePaint, this.deleteProgressRect, 0.0f, 1.0f);
        L31:
            invalidate();
            return;
        L30:
            float r143 = this.deleteProgressRect.centerX();
            float r22 = this.deleteProgressRect.centerY() - AndroidUtilities.dp(1.0f);
            float r1 = AndroidUtilities.dp(8.0f);
            this.drawable.setBounds((int) (r143 - r1), (int) (r22 - r1), (int) (r143 + r1), (int) (r22 + r1));
            this.drawable.draw(r14);
            float r10 = r0 * (-360.0f);
            r14.drawArc(this.deleteProgressRect, -90.0f, r10, false, this.afterDeleteProgressPaint);
            this.timerParticles.draw(r14, this.particlePaint, this.deleteProgressRect, r10, 1.0f);
        L18:
            r0 = 1.0f;
            goto L24
        L20:
            if (this.destroyTime == 0) goto L18;
            long r23 = System.currentTimeMillis() + (ConnectionsManager.getInstance(SecretMediaViewer.m18528$$Nest$fgetcurrentAccount(this.this$0)).getTimeDifference() * 1000);
            r0 = Math.max(0, this.destroyTime - r23) / (this.destroyTtl * 1000.0f);
            goto L24
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            super.onMeasure(r6, r7);
            float r62 = getMeasuredWidth() - AndroidUtilities.dp(35.0f);
            float r72 = getMeasuredHeight() / 2.0f;
            float r0 = AndroidUtilities.dpf2(10.5f);
            this.deleteProgressRect.set(r62 - r0, r72 - r0, r62 + r0, r0 + r72);
            setPivotX(r62);
            setPivotY(r72);
        }
    }

    public class VideoPlayerControlFrameLayout extends FrameLayout {
        public final Property<VideoPlayerControlFrameLayout, Float> SEEKBAR_ALPHA;
        private boolean ignoreLayout;
        private int lastTimeWidth;
        private int parentHeight;
        private int parentWidth;
        private float progress;
        private boolean seekBarTransitionEnabled;
        final /* synthetic */ SecretMediaViewer this$0;
        private SpringAnimation timeSpring;
        private FloatValueHolder timeValue;
        private boolean translationYAnimationEnabled;

        /* renamed from: org.telegram.ui.SecretMediaViewer$VideoPlayerControlFrameLayout$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimationProperties.FloatProperty<VideoPlayerControlFrameLayout> {
            final /* synthetic */ VideoPlayerControlFrameLayout this$1;

            public AnonymousClass1(VideoPlayerControlFrameLayout r1, String r2) {
                this.this$1 = r1;
                super(r2);
            }

            public Float get(VideoPlayerControlFrameLayout r1) {
                return Float.valueOf(r1.getProgress());
            }

            @Override // android.util.Property
            public /* bridge */ /* synthetic */ Float get(Object r1) {
                return get((VideoPlayerControlFrameLayout) r1);
            }

            @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
            public /* bridge */ /* synthetic */ void setValue(VideoPlayerControlFrameLayout r1, float r2) {
                setValue2(r1, r2);
            }

            /* renamed from: setValue, reason: avoid collision after fix types in other method */
            public void setValue2(VideoPlayerControlFrameLayout r1, float r2) {
                r1.setProgress(r2);
            }
        }

        public static /* synthetic */ void $r8$lambda$IMujwW4wl06bT4dYEgHgDxINKuw(VideoPlayerControlFrameLayout r0, DynamicAnimation r1, float r2, float r3) {
            r0.lambda$new$0(r1, r2, r3);
        }

        public VideoPlayerControlFrameLayout(SecretMediaViewer r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.progress = 1.0f;
            this.seekBarTransitionEnabled = true;
            this.translationYAnimationEnabled = true;
            this.timeValue = new FloatValueHolder(0.0f);
            this.timeSpring = new SpringAnimation(this.timeValue).setSpring(new SpringForce(0.0f).setStiffness(750.0f).setDampingRatio(1.0f)).addUpdateListener(new SecretMediaViewer$VideoPlayerControlFrameLayout$$ExternalSyntheticLambda0(this));
            this.SEEKBAR_ALPHA = new AnonymousClass1(this, "progress");
            setWillNotDraw(false);
        }

        private /* synthetic */ void lambda$new$0(DynamicAnimation r3, float r4, float r5) {
            if (this.parentWidth <= this.parentHeight) goto L5;
            int r32 = AndroidUtilities.dp(48.0f);
        L6:
            SecretMediaViewer.m18547$$Nest$fgetseekbar(this.this$0).setSize((int) (((getMeasuredWidth() - AndroidUtilities.dp(16.0f)) - r4) - r32), getMeasuredHeight());
            return;
        L5:
            r32 = 0;
            goto L6
        }

        private void onProgressChanged(float r4) {
            SecretMediaViewer.m18553$$Nest$fgetvideoPlayerTime(this.this$0).setAlpha(r4);
            if (this.seekBarTransitionEnabled == false) goto L7;
            SecretMediaViewer.m18553$$Nest$fgetvideoPlayerTime(this.this$0).setPivotX(SecretMediaViewer.m18553$$Nest$fgetvideoPlayerTime(this.this$0).getWidth());
            SecretMediaViewer.m18553$$Nest$fgetvideoPlayerTime(this.this$0).setPivotY(SecretMediaViewer.m18553$$Nest$fgetvideoPlayerTime(this.this$0).getHeight());
            float r42 = 1.0f - r4;
            float r1 = 1.0f - (0.1f * r42);
            SecretMediaViewer.m18553$$Nest$fgetvideoPlayerTime(this.this$0).setScaleX(r1);
            SecretMediaViewer.m18553$$Nest$fgetvideoPlayerTime(this.this$0).setScaleY(r1);
            SecretMediaViewer.m18547$$Nest$fgetseekbar(this.this$0).setTransitionProgress(r42);
            return;
        L7:
            if (this.translationYAnimationEnabled == false) goto L9;
            setTranslationY(AndroidUtilities.dpf2(24.0f) * (1.0f - r4));
        L9:
            SecretMediaViewer.m18549$$Nest$fgetseekbarView(this.this$0).setAlpha(r4);
        }

        public float getProgress() {
            return this.progress;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.timeValue.setValue(0.0f);
            this.lastTimeWidth = 0;
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0) == null) goto L5;
            float r12 = SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0).getCurrentPosition() / SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0).getDuration();
        L6:
            SecretMediaViewer.m18547$$Nest$fgetseekbar(this.this$0).setProgress(r12);
            return;
        L5:
            r12 = 0.0f;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            this.ignoreLayout = true;
            FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) SecretMediaViewer.m18553$$Nest$fgetvideoPlayerTime(this.this$0).getLayoutParams();
            if (this.parentWidth <= this.parentHeight) goto L5;
            int r1 = AndroidUtilities.dp(48.0f);
            r0.rightMargin = AndroidUtilities.dp(47.0f);
        L6:
            this.ignoreLayout = false;
            super.onMeasure(r9, r10);
            long r2 = 0;
            if (SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0) == null) goto L12;
            long r92 = SecretMediaViewer.m18552$$Nest$fgetvideoPlayer(this.this$0).getDuration();
            if (r92 == (-9223372036854775807L)) goto L12;
            r2 = r92;
        L12:
            long r22 = r2 / 1000;
            long r4 = r22 / 60;
            if (r4 <= 60) goto L15;
            String r93 = String.format(Locale.ROOT, "%02d:%02d:%02d", new Object[]{Long.valueOf(r4 / 60), Long.valueOf(r4 % 60), Long.valueOf(r22 % 60)});
        L16:
            int r94 = (int) Math.ceil(SecretMediaViewer.m18553$$Nest$fgetvideoPlayerTime(this.this$0).getPaint().measureText(String.format(Locale.ROOT, "%1$s / %1$s", new Object[]{r93})));
            this.timeSpring.cancel();
            if (this.lastTimeWidth == 0) goto L21;
            float r02 = r94;
            if (this.timeValue.getValue() == r02) goto L21;
            this.timeSpring.getSpring().setFinalPosition(r02);
            this.timeSpring.start();
        L22:
            this.lastTimeWidth = r94;
            return;
        L21:
            SecretMediaViewer.m18547$$Nest$fgetseekbar(this.this$0).setSize(((getMeasuredWidth() - AndroidUtilities.dp(16.0f)) - r94) - r1, getMeasuredHeight());
            this.timeValue.setValue(r94);
            goto L22
        L15:
            r93 = String.format(Locale.ROOT, "%02d:%02d", new Object[]{Long.valueOf(r4), Long.valueOf(r22 % 60)});
            goto L16
        L5:
            r0.rightMargin = AndroidUtilities.dp(12.0f);
            r1 = 0;
            goto L6
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r5) {
            if (this.progress >= 1.0f) goto L7;
            return false;
        L7:
            if (SecretMediaViewer.m18547$$Nest$fgetseekbar(this.this$0).onTouch(r5.getAction(), r5.getX() - AndroidUtilities.dp(2.0f), r5.getY()) == false) goto L9;
            getParent().requestDisallowInterceptTouchEvent(true);
            SecretMediaViewer.m18549$$Nest$fgetseekbarView(this.this$0).invalidate();
        L9:
            return true;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }

        public void setProgress(float r2) {
            if (this.progress == r2) goto L6;
            this.progress = r2;
            onProgressChanged(r2);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$9_bBPmc18HLT1Aq1wXrbVY6wSxE(SecretMediaViewer r0, PhotoViewer.PlaceProviderObject r1) {
        r0.lambda$closePhoto$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$BCy5P08fbt0ycfYQ10yQTXXjEaY(SecretMediaViewer r0) {
        r0.lambda$new$6();
    }

    /* renamed from: $r8$lambda$EWQ7Sl9piLT-cczkh9yi_b-drXQ, reason: not valid java name */
    public static /* synthetic */ void m18517$r8$lambda$EWQ7Sl9piLTcczkh9yi_bdrXQ(SecretMediaViewer r0, View r1) {
        r0.lambda$openMedia$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$Gp2LGaC3LJXX4ig72SEPpNGIakk(SecretMediaViewer r0, PhotoViewer.PlaceProviderObject r1) {
        r0.lambda$openMedia$5(r1);
    }

    public static /* synthetic */ WindowInsets $r8$lambda$LYEiahgzo6Rj4iN7bD3NKI9fid0(SecretMediaViewer r0, View r1, WindowInsets r2) {
        return r0.lambda$setParentActivity$1(r1, r2);
    }

    /* renamed from: $r8$lambda$QGDTjLK7maK1kSY3-ro-2_aU_qo, reason: not valid java name */
    public static /* synthetic */ void m18518$r8$lambda$QGDTjLK7maK1kSY3ro2_aU_qo(SecretMediaViewer r0, ClickableSpan r1, TextView r2) {
        r0.onLinkClick(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$QiUGmfKr7jQQJRynQBs59jiLWxY(SecretMediaViewer r0, ClickableSpan r1, TextView r2, Runnable r3) {
        r0.onLinkLongPress(r1, r2, r3);
    }

    public static /* synthetic */ View $r8$lambda$RKdrl_5fQ5FPnxyr51MY_aIXUfE(SecretMediaViewer r0, Activity r1) {
        return r0.lambda$setParentActivity$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$fydE63aVfJjYVLIPyLtuGUWR60E(SecretMediaViewer r0) {
        r0.lambda$onPhotoClosed$9();
    }

    /* renamed from: $r8$lambda$iHYg6kKKtUy-3elqMLe7GxagX-o, reason: not valid java name */
    public static /* synthetic */ void m18519$r8$lambda$iHYg6kKKtUy3elqMLe7GxagXo(SecretMediaViewer r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$irj9U8yasrMtxHVMBxW6kwkAKm0(SecretMediaViewer r0, PhotoViewer.PlaceProviderObject r1) {
        r0.lambda$closePhoto$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$wRZBueEE62yGHJgOTdLZ46x6ld4(SecretMediaViewer r0, Runnable r1, MessageObject r2) {
        r0.lambda$openMedia$4(r1, r2);
    }

    /* renamed from: -$$Nest$fgetactionBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBar m18520$$Nest$fgetactionBar(SecretMediaViewer r0) {
        return r0.actionBar;
    }

    /* renamed from: -$$Nest$fgetactivityVisibilityController, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowVisibilityManager.Controller m18521$$Nest$fgetactivityVisibilityController(SecretMediaViewer r0) {
        return r0.activityVisibilityController;
    }

    /* renamed from: -$$Nest$fgetaspectRatioFrameLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AspectRatioFrameLayout m18522$$Nest$fgetaspectRatioFrameLayout(SecretMediaViewer r0) {
        return r0.aspectRatioFrameLayout;
    }

    /* renamed from: -$$Nest$fgetcaptionScrollView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoViewer.CaptionScrollView m18523$$Nest$fgetcaptionScrollView(SecretMediaViewer r0) {
        return r0.captionScrollView;
    }

    /* renamed from: -$$Nest$fgetcaptionTextViewSwitcher, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoViewer.CaptionTextViewSwitcher m18524$$Nest$fgetcaptionTextViewSwitcher(SecretMediaViewer r0) {
        return r0.captionTextViewSwitcher;
    }

    /* renamed from: -$$Nest$fgetcenterImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageReceiver m18525$$Nest$fgetcenterImage(SecretMediaViewer r0) {
        return r0.centerImage;
    }

    /* renamed from: -$$Nest$fgetcloseVideoAfterWatch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18526$$Nest$fgetcloseVideoAfterWatch(SecretMediaViewer r0) {
        return r0.closeVideoAfterWatch;
    }

    /* renamed from: -$$Nest$fgetcontainerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayoutDrawer m18527$$Nest$fgetcontainerView(SecretMediaViewer r0) {
        return r0.containerView;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18528$$Nest$fgetcurrentAccount(SecretMediaViewer r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcurrentActionBarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m18529$$Nest$fgetcurrentActionBarAnimation(SecretMediaViewer r0) {
        return r0.currentActionBarAnimation;
    }

    /* renamed from: -$$Nest$fgetcurrentMessageObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m18530$$Nest$fgetcurrentMessageObject(SecretMediaViewer r0) {
        return r0.currentMessageObject;
    }

    /* renamed from: -$$Nest$fgethideActionBarRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m18531$$Nest$fgethideActionBarRunnable(SecretMediaViewer r0) {
        return r0.hideActionBarRunnable;
    }

    /* renamed from: -$$Nest$fgetignoreDelete, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18532$$Nest$fgetignoreDelete(SecretMediaViewer r0) {
        return r0.ignoreDelete;
    }

    /* renamed from: -$$Nest$fgetimageMoveAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m18533$$Nest$fgetimageMoveAnimation(SecretMediaViewer r0) {
        return r0.imageMoveAnimation;
    }

    /* renamed from: -$$Nest$fgetisActionBarVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18534$$Nest$fgetisActionBarVisible(SecretMediaViewer r0) {
        return r0.isActionBarVisible;
    }

    /* renamed from: -$$Nest$fgetisPhotoVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18535$$Nest$fgetisPhotoVisible(SecretMediaViewer r0) {
        return r0.isPhotoVisible;
    }

    /* renamed from: -$$Nest$fgetisPlaying, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18536$$Nest$fgetisPlaying(SecretMediaViewer r0) {
        return r0.isPlaying;
    }

    /* renamed from: -$$Nest$fgetisVideo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18537$$Nest$fgetisVideo(SecretMediaViewer r0) {
        return r0.isVideo;
    }

    /* renamed from: -$$Nest$fgetlastInsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m18538$$Nest$fgetlastInsets(SecretMediaViewer r0) {
        return r0.lastInsets;
    }

    /* renamed from: -$$Nest$fgetnavigationBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m18539$$Nest$fgetnavigationBar(SecretMediaViewer r0) {
        return r0.navigationBar;
    }

    /* renamed from: -$$Nest$fgetparentActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Activity m18540$$Nest$fgetparentActivity(SecretMediaViewer r0) {
        return r0.parentActivity;
    }

    /* renamed from: -$$Nest$fgetphotoAnimationEndRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m18541$$Nest$fgetphotoAnimationEndRunnable(SecretMediaViewer r0) {
        return r0.photoAnimationEndRunnable;
    }

    /* renamed from: -$$Nest$fgetplayButtonDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PlayPauseDrawable m18542$$Nest$fgetplayButtonDrawable(SecretMediaViewer r0) {
        return r0.playButtonDrawable;
    }

    /* renamed from: -$$Nest$fgetplayerRetryPlayCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18543$$Nest$fgetplayerRetryPlayCount(SecretMediaViewer r0) {
        return r0.playerRetryPlayCount;
    }

    /* renamed from: -$$Nest$fgetscale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m18544$$Nest$fgetscale(SecretMediaViewer r0) {
        return r0.scale;
    }

    /* renamed from: -$$Nest$fgetsecretDeleteTimer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SecretDeleteTimer m18545$$Nest$fgetsecretDeleteTimer(SecretMediaViewer r0) {
        return r0.secretDeleteTimer;
    }

    /* renamed from: -$$Nest$fgetsecretHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HintView2 m18546$$Nest$fgetsecretHint(SecretMediaViewer r0) {
        return r0.secretHint;
    }

    /* renamed from: -$$Nest$fgetseekbar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayerSeekBar m18547$$Nest$fgetseekbar(SecretMediaViewer r0) {
        return r0.seekbar;
    }

    /* renamed from: -$$Nest$fgetseekbarContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayerControlFrameLayout m18548$$Nest$fgetseekbarContainer(SecretMediaViewer r0) {
        return r0.seekbarContainer;
    }

    /* renamed from: -$$Nest$fgetseekbarView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m18549$$Nest$fgetseekbarView(SecretMediaViewer r0) {
        return r0.seekbarView;
    }

    /* renamed from: -$$Nest$fgettextureUploaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18550$$Nest$fgettextureUploaded(SecretMediaViewer r0) {
        return r0.textureUploaded;
    }

    /* renamed from: -$$Nest$fgetupdateProgressRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m18551$$Nest$fgetupdateProgressRunnable(SecretMediaViewer r0) {
        return r0.updateProgressRunnable;
    }

    /* renamed from: -$$Nest$fgetvideoPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoPlayer m18552$$Nest$fgetvideoPlayer(SecretMediaViewer r0) {
        return r0.videoPlayer;
    }

    /* renamed from: -$$Nest$fgetvideoPlayerTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SimpleTextView m18553$$Nest$fgetvideoPlayerTime(SecretMediaViewer r0) {
        return r0.videoPlayerTime;
    }

    /* renamed from: -$$Nest$fputcurrentActionBarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18554$$Nest$fputcurrentActionBarAnimation(SecretMediaViewer r0, AnimatorSet r1) {
        r0.currentActionBarAnimation = r1;
    }

    /* renamed from: -$$Nest$fputimageMoveAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18555$$Nest$fputimageMoveAnimation(SecretMediaViewer r0, AnimatorSet r1) {
        r0.imageMoveAnimation = r1;
    }

    /* renamed from: -$$Nest$fputisPlaying, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18556$$Nest$fputisPlaying(SecretMediaViewer r0, boolean r1) {
        r0.isPlaying = r1;
    }

    /* renamed from: -$$Nest$fputisVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18557$$Nest$fputisVisible(SecretMediaViewer r0, boolean r1) {
        r0.isVisible = r1;
    }

    /* renamed from: -$$Nest$fputphotoAnimationEndRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18558$$Nest$fputphotoAnimationEndRunnable(SecretMediaViewer r0, Runnable r1) {
        r0.photoAnimationEndRunnable = r1;
    }

    /* renamed from: -$$Nest$fputplayerRetryPlayCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18559$$Nest$fputplayerRetryPlayCount(SecretMediaViewer r0, int r1) {
        r0.playerRetryPlayCount = r1;
    }

    /* renamed from: -$$Nest$fputscale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18560$$Nest$fputscale(SecretMediaViewer r0, float r1) {
        r0.scale = r1;
    }

    /* renamed from: -$$Nest$fputtextureUploaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18561$$Nest$fputtextureUploaded(SecretMediaViewer r0, boolean r1) {
        r0.textureUploaded = r1;
    }

    /* renamed from: -$$Nest$fputtranslationX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18562$$Nest$fputtranslationX(SecretMediaViewer r0, float r1) {
        r0.translationX = r1;
    }

    /* renamed from: -$$Nest$fputtranslationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18563$$Nest$fputtranslationY(SecretMediaViewer r0, float r1) {
        r0.translationY = r1;
    }

    /* renamed from: -$$Nest$fputvideoWatchedOneTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18564$$Nest$fputvideoWatchedOneTime(SecretMediaViewer r0, boolean r1) {
        r0.videoWatchedOneTime = r1;
    }

    /* renamed from: -$$Nest$monDraw, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18565$$Nest$monDraw(SecretMediaViewer r0, Canvas r1) {
        r0.onDraw(r1);
    }

    /* renamed from: -$$Nest$mpreparePlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18566$$Nest$mpreparePlayer(SecretMediaViewer r0, File r1) {
        r0.preparePlayer(r1);
    }

    /* renamed from: -$$Nest$mprocessTouchEvent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18567$$Nest$mprocessTouchEvent(SecretMediaViewer r0, MotionEvent r1) {
        return r0.processTouchEvent(r1);
    }

    /* renamed from: -$$Nest$mshowPlayButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18568$$Nest$mshowPlayButton(SecretMediaViewer r0, boolean r1, boolean r2) {
        r0.showPlayButton(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateMinMax, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18569$$Nest$mupdateMinMax(SecretMediaViewer r0, float r1) {
        r0.updateMinMax(r1);
    }

    static {
    }

    public SecretMediaViewer() {
        this.centerImage = new ImageReceiver();
        this.coords = new int[2];
        this.isActionBarVisible = true;
        this.photoBackgroundDrawable = new PhotoBackgroundDrawable(this, -16777216);
        this.blackPaint = new Paint();
        this.scale = 1.0f;
        this.interpolator = new DecelerateInterpolator(1.5f);
        this.pinchStartScale = 1.0f;
        this.canDragDown = true;
        this.updateProgressRunnable = new SecretMediaViewer$$ExternalSyntheticLambda2(this);
        this.videoPlayerCurrentTime = new int[2];
        this.videoPlayerTotalTime = new int[2];
        this.hideActionBarRunnable = new SecretMediaViewer$$ExternalSyntheticLambda3(this);
        this.roundRectPath = new Path();
        this.VIDEO_CROSSFADE_ALPHA = new AnonymousClass16(this, "videoCrossfadeAlpha");
        this.ANIMATION_VALUE = new AnonymousClass20(this, "animationValue");
    }

    private void animateTo(float r7, float r8, float r9, boolean r10) {
        animateTo(r7, r8, r9, r10, 250);
    }

    private void animateTo(float r2, float r3, float r4, boolean r5, int r6) {
        if (this.scale == r2) goto L5;
    L9:
        this.zoomAnimation = r5;
        this.animateToScale = r2;
        this.animateToX = r3;
        this.animateToY = r4;
        this.animationStartTime = System.currentTimeMillis();
        AnimatorSet r22 = new AnimatorSet();
        this.imageMoveAnimation = r22;
        r22.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, "animationValue", new float[]{0.0f, 1.0f})});
        this.imageMoveAnimation.setInterpolator(this.interpolator);
        this.imageMoveAnimation.setDuration(r6);
        this.imageMoveAnimation.addListener(new AnonymousClass19(this));
        this.imageMoveAnimation.start();
        return;
    L5:
        if (this.translationX != r3) goto L9;
        if (this.translationY != r4) goto L9;
    }

    private void checkMinMax(boolean r6) {
        float r0 = this.translationX;
        float r1 = this.translationY;
        updateMinMax(this.scale);
        float r2 = this.translationX;
        float r3 = this.minX;
        if (r2 >= r3) goto L5;
    L4:
        r0 = r3;
    L8:
        float r22 = this.translationY;
        float r32 = this.minY;
        if (r22 >= r32) goto L11;
    L10:
        r1 = r32;
    L14:
        animateTo(this.scale, r0, r1, r6);
        return;
    L11:
        r32 = this.maxY;
        if (r22 <= r32) goto L14;
    L5:
        r3 = this.maxX;
        if (r2 <= r3) goto L8;
        goto L8
    }

    private boolean checkPhotoAnimation() {
        if (this.photoAnimationInProgress == 0) goto L11;
        if (Math.abs(this.photoTransitionAnimationStartTime - System.currentTimeMillis()) < 500) goto L11;
        Runnable r0 = this.photoAnimationEndRunnable;
        if (r0 == null) goto L9;
        r0.run();
        this.photoAnimationEndRunnable = null;
    L9:
        this.photoAnimationInProgress = 0;
    L11:
        if (this.photoAnimationInProgress == 0) goto L14;
        return true;
    L14:
        return false;
    }

    private int getContainerViewHeight() {
        return this.containerView.getHeight();
    }

    private int getContainerViewWidth() {
        return this.containerView.getWidth();
    }

    public static SecretMediaViewer getInstance() {
        SecretMediaViewer r0 = Instance;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(PhotoViewer.class);
        SecretMediaViewer r02 = Instance;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = new SecretMediaViewer();     // Catch: Throwable -> L9
        Instance = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(PhotoViewer.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static boolean hasInstance() {
        if (Instance == null) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$closePhoto$7(PhotoViewer.PlaceProviderObject r4) {
        this.imageMoveAnimation = null;
        this.photoAnimationInProgress = 0;
        this.containerView.setLayerType(0, null);
        this.containerView.setVisibility(4);
        onPhotoClosed(r4);
    }

    private /* synthetic */ void lambda$closePhoto$8(PhotoViewer.PlaceProviderObject r4) {
        FrameLayoutDrawer r0 = this.containerView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setLayerType(0, null);
        this.containerView.setVisibility(4);
        this.photoAnimationInProgress = 0;
        onPhotoClosed(r4);
        this.containerView.setScaleX(1.0f);
        this.containerView.setScaleY(1.0f);
    }

    private /* synthetic */ void lambda$new$0() {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L18;
        long r02 = r0.getCurrentPosition();
        long r2 = this.videoPlayer.getDuration();
        if (r2 != (-9223372036854775807L)) goto L9;
        r02 = 0;
        r2 = 0;
    L9:
        if (r2 > 0) goto L11;
    L13:
        updateVideoPlayerTime();
        if (this.videoPlayer.isPlaying() == false) goto L17;
        AndroidUtilities.runOnUIThread(this.updateProgressRunnable, 17);
        return;
    L17:
        return;
    L11:
        if (this.seekbar.isDragging() == true) goto L13;
        this.seekbar.setProgress(r02 / r2);
        this.seekbarView.invalidate();
        goto L13
    }

    private /* synthetic */ void lambda$new$6() {
        toggleActionBar(false, true);
    }

    private /* synthetic */ void lambda$onPhotoClosed$9() {
        ImageReceiver.BitmapHolder r0 = this.currentThumb;
        if (r0 == null) goto L5;
        r0.release();
        this.currentThumb = null;
    L5:
        this.centerImage.setImageBitmap(null);
    L10:
        e = move-exception;
        FileLog.e(e);
    L12:
        this.isPhotoVisible = false;
        return;
    L7:
        if (this.windowView.getParent() == null) goto L12;
        ((WindowManager) this.parentActivity.getSystemService("window")).removeView(this.windowView);     // Catch: Exception -> L10
        goto L12
    }

    private /* synthetic */ void lambda$openMedia$3(View r2) {
        MessageObject r22 = this.currentMessageObject;
        if (r22 == null) goto L15;
        TLRPC.Message r23 = r22.messageOwner;
        if (r23.destroyTime != 0) goto L10;
        if (r23.ttl == Integer.MAX_VALUE) goto L10;
        return;
    L10:
        if (this.secretHint.shown() == false) goto L13;
        this.secretHint.hide();
        return;
    L13:
        showSecretHint();
        return;
    }

    private /* synthetic */ void lambda$openMedia$4(Runnable r8, MessageObject r9) {
        this.photoAnimationInProgress = 0;
        this.imageMoveAnimation = null;
        if (r8 == null) goto L5;
        r8.run();
    L5:
        FrameLayoutDrawer r82 = this.containerView;
        if (r82 == null) goto L20;
        r82.setLayerType(0, null);
        this.containerView.invalidate();
        SecretDeleteTimer.m18576$$Nest$msetDestroyTime(this.secretDeleteTimer, r9.messageOwner.destroyTimeMillis, r8.ttl, false);
        if (this.closeAfterAnimation == false) goto L13;
        closePhoto(true, true);
        return;
    L13:
        if (this.ignoreDelete == true) goto L15;
        return;
    L15:
        if (MessagesController.getGlobalMainSettings().getInt("viewoncehint", 0) >= 3) goto L19;
        showSecretHint();
        return;
    L19:
        return;
    }

    private /* synthetic */ void lambda$openMedia$5(PhotoViewer.PlaceProviderObject r2) {
        this.disableShowCheck = false;
        r2.imageReceiver.setVisible(false, true);
    }

    private /* synthetic */ WindowInsets lambda$setParentActivity$1(View r2, WindowInsets r3) {
        WindowInsets r22 = (WindowInsets) this.lastInsets;
        this.lastInsets = r3;
        if (r22 != null) goto L5;
    L6:
        this.windowView.requestLayout();
    L8:
        if (Build.VERSION.SDK_INT < 30) goto L12;
        return WindowInsetsCompat$Impl30$$ExternalSyntheticApiModelOutline0.m();
    L12:
        return r3.consumeSystemWindowInsets();
    L5:
        if (r22.toString().equals(r3.toString()) == true) goto L8;
        goto L6
    }

    private /* synthetic */ View lambda$setParentActivity$2(Activity r7) {
        return new PhotoViewer.CaptionTextView(r7, this.captionScrollView, this.textSelectionHelper, new SecretMediaViewer$$ExternalSyntheticLambda10(this), new SecretMediaViewer$$ExternalSyntheticLambda11(this));
    }

    private void onDraw(Canvas r23) {
        if (this.isPhotoVisible == true) goto L5;
        return;
    L5:
        float r6 = 1.0f;
        if (this.imageMoveAnimation == null) goto L28;
        if (this.scroller.isFinished() == true) goto L11;
        this.scroller.abortAnimation();
    L11:
        if (this.useOvershootForScale == false) goto L17;
        float r2 = this.animationValue;
        if (r2 >= 0.9f) goto L15;
        float r22 = r2 / 0.9f;
        float r7 = this.scale;
        float r72 = r7 + (((this.animateToScale * 1.02f) - r7) * r22);
    L16:
        float r8 = this.translationY;
        float r82 = r8 + ((this.animateToY - r8) * r22);
        float r9 = this.translationX;
        float r92 = r9 + ((this.animateToX - r9) * r22);
        float r10 = this.clipTop;
        float r102 = r10 + ((this.animateToClipTop - r10) * r22);
        float r11 = this.clipBottom;
        float r112 = r11 + ((this.animateToClipBottom - r11) * r22);
        float r12 = this.clipTopOrigin;
        float r122 = r12 + ((this.animateToClipTopOrigin - r12) * r22);
        float r13 = this.clipBottomOrigin;
        float r132 = r13 + ((this.animateToClipBottomOrigin - r13) * r22);
        float r14 = this.clipHorizontal;
        float r142 = r14 + ((this.animateToClipHorizontal - r14) * r22);
    L19:
        if (this.animateToScale == 1.0f) goto L21;
    L25:
        float r24 = -1.0f;
    L26:
        this.containerView.invalidate();
    L50:
        if (this.animateFromRadius == null) goto L65;
        int r4 = 8;
        if (this.currentRadii != null) goto L54;
        this.currentRadii = new float[8];
    L54:
        boolean r15 = this.animateToRadius;
        float r3 = this.animationValue;
        if (r15 == true) goto L58;
        r3 = 1.0f - r3;
    L58:
        int r152 = 0;
        boolean r18 = true;
    L59:
        if (r152 >= r4) goto L64;
        float[] r42 = this.currentRadii;
        float r20 = r6;
        float r62 = AndroidUtilities.lerp(this.animateFromRadius[r152 / 2] * 2.0f, 0.0f, r3);
        r42[r152 + 1] = r62;
        r42[r152] = r62;
        if (this.currentRadii[r152] <= 0.0f) goto L63;
        r18 = false;
    L63:
        r152 = r152 + 2;
        r6 = r20;
        r4 = 8;
    L64:
        float r202 = r6;
        if (this.photoAnimationInProgress != 3) goto L69;
    L81:
        float r43 = 0.0f;
        float r32 = 1.0f;
    L82:
        AspectRatioFrameLayout r25 = this.aspectRatioFrameLayout;
        if (r25 != null) goto L85;
    L87:
        boolean r17 = false;
    L88:
        r23.save();
        float r73 = r72 - r43;
        r23.translate((getContainerViewWidth() / 2) + r92, (getContainerViewHeight() / 2) + r82);
        r23.scale(r73, r73);
        int r26 = this.centerImage.getBitmapWidth();
        int r44 = this.centerImage.getBitmapHeight();
        int r63 = this.videoWidth;
        if (r63 == 0) goto L93;
        int r83 = this.videoHeight;
        if (r83 == 0) goto L93;
        r26 = r63;
        r44 = r83;
    L93:
        if (r17 == true) goto L95;
    L99:
        float r45 = r44;
        float r27 = r26;
        float r64 = Math.min(getContainerViewHeight() / r45, getContainerViewWidth() / r27);
        int r28 = (int) (r27 * r64);
        int r46 = (int) (r45 * r64);
        float r65 = (-r28) / 2;
        float r143 = r142 / r73;
        float r84 = r65 + r143;
        float r93 = (-r46) / 2;
        float r153 = (r28 / 2) - r143;
        float r144 = r46 / 2;
        r23.clipRect(r84, (r102 / r73) + r93, r153, r144 - (r112 / r73));
        if (r18 == true) goto L102;
        this.roundRectPath.reset();
        RectF r103 = AndroidUtilities.rectTmp;
        r103.set(r84, (r122 / r73) + r93, r153, r144 - (r132 / r73));
        this.roundRectPath.addRoundRect(r103, this.currentRadii, Path.Direction.CW);
        r23.clipPath(this.roundRectPath);
    L102:
        if (r17 == true) goto L104;
    L109:
        this.centerImage.setAlpha(r32);
        this.centerImage.setImageCoords(r65, r93, r28, r46);
        this.centerImage.draw(r23);
    L110:
        if (r17 == true) goto L112;
    L123:
        r23.restore();
        return;
    L112:
        if (this.videoCrossfadeStarted == false) goto L114;
    L116:
        r23.translate(r65, r93);
        this.videoTextureView.setAlpha(r32 * this.videoCrossfadeAlpha);
        this.aspectRatioFrameLayout.draw(r23);
        if (this.videoCrossfadeStarted == false) goto L123;
        if (this.videoCrossfadeAlpha >= 1.0f) goto L123;
        long r29 = System.currentTimeMillis();
        long r47 = r29 - this.videoCrossfadeAlphaLastTime;
        this.videoCrossfadeAlphaLastTime = r29;
        this.videoCrossfadeAlpha += r47 / 200.0f;
        this.containerView.invalidate();
        if (this.videoCrossfadeAlpha <= 1.0f) goto L123;
        this.videoCrossfadeAlpha = 1.0f;
        goto L123
    L114:
        if (this.textureUploaded == false) goto L116;
        this.videoCrossfadeStarted = true;
        this.videoCrossfadeAlpha = 0.0f;
        this.videoCrossfadeAlphaLastTime = System.currentTimeMillis();
        goto L116
    L104:
        if (this.textureUploaded == false) goto L109;
        if (this.videoCrossfadeStarted == false) goto L109;
        if (this.videoCrossfadeAlpha == 1.0f) goto L110;
    L95:
        if (this.textureUploaded == false) goto L99;
        if (Math.abs((r26 / r44) - (this.videoTextureView.getMeasuredWidth() / this.videoTextureView.getMeasuredHeight())) <= 0.01f) goto L99;
        r26 = this.videoTextureView.getMeasuredWidth();
        r44 = this.videoTextureView.getMeasuredHeight();
        goto L99
    L85:
        if (r25.getVisibility() != 0) goto L87;
        r17 = true;
        goto L88
    L69:
        if (this.scale == r202) goto L71;
    L75:
        this.photoBackgroundDrawable.setAlpha(255);
    L77:
        if (this.zoomAnimation == true) goto L81;
        float r210 = this.maxX;
        if (r92 <= r210) goto L81;
        float r33 = Math.min(r202, (r92 - r210) / r23.getWidth());
        r43 = 0.3f * r33;
        r32 = r202 - r33;
        r92 = this.maxX;
        goto L82
    L71:
        if (r24 == (-1.0f)) goto L75;
        if (this.zoomAnimation == true) goto L75;
        float r34 = getContainerViewHeight() / 4.0f;
        this.photoBackgroundDrawable.setAlpha((int) Math.max(127.0f, (r202 - (Math.min(Math.abs(r24), r34) / r34)) * 255.0f));
        goto L77
    L65:
        r18 = true;
        goto L64
    L21:
        if (this.scale != 1.0f) goto L25;
        if (this.translationX != 0.0f) goto L25;
        r24 = r82;
        goto L26
    L15:
        float r85 = this.animateToScale;
        r72 = r85 + ((0.01999998f * r85) * (1.0f - ((r2 - 0.9f) / 0.100000024f)));
        r22 = 1.0f;
        goto L16
    L17:
        float r211 = this.scale;
        float r74 = this.animateToScale - r211;
        float r86 = this.animationValue;
        r72 = (r74 * r86) + r211;
        float r212 = this.translationY;
        float r213 = r212 + ((this.animateToY - r212) * r86);
        float r94 = this.translationX;
        r92 = r94 + ((this.animateToX - r94) * r86);
        float r104 = this.clipTop;
        r102 = r104 + ((this.animateToClipTop - r104) * r86);
        float r113 = this.clipBottom;
        r112 = r113 + ((this.animateToClipBottom - r113) * r86);
        float r123 = this.clipTopOrigin;
        r122 = r123 + ((this.animateToClipTopOrigin - r123) * r86);
        float r133 = this.clipBottomOrigin;
        r132 = r133 + ((this.animateToClipBottomOrigin - r133) * r86);
        float r145 = this.clipHorizontal;
        r142 = r145 + ((this.animateToClipHorizontal - r145) * r86);
        r82 = r213;
        goto L19
    L28:
        if (this.animationStartTime == 0) goto L31;
        this.translationX = this.animateToX;
        this.translationY = this.animateToY;
        this.clipBottom = this.animateToClipBottom;
        this.clipTop = this.animateToClipTop;
        this.clipTopOrigin = this.animateToClipTopOrigin;
        this.clipBottomOrigin = this.animateToClipBottomOrigin;
        this.clipHorizontal = this.animateToClipHorizontal;
        float r214 = this.animateToScale;
        this.scale = r214;
        this.animationStartTime = 0;
        updateMinMax(r214);
        this.zoomAnimation = false;
        this.useOvershootForScale = false;
    L31:
        if (this.scroller.isFinished() == false) goto L33;
    L45:
        r72 = this.scale;
        r82 = this.translationY;
        r92 = this.translationX;
        r102 = this.clipTop;
        r112 = this.clipBottom;
        r122 = this.clipTopOrigin;
        r132 = this.clipBottomOrigin;
        r142 = this.clipHorizontal;
        if (this.moving == true) goto L48;
        r24 = r82;
        goto L50
    L48:
        r24 = -1.0f;
        goto L50
    L33:
        if (this.scroller.computeScrollOffset() == false) goto L45;
        if (this.scroller.getStartX() >= this.maxX) goto L40;
        if (this.scroller.getStartX() <= this.minX) goto L40;
        this.translationX = this.scroller.getCurrX();
    L40:
        if (this.scroller.getStartY() < this.maxY) goto L42;
    L44:
        this.containerView.invalidate();
        goto L45
    L42:
        if (this.scroller.getStartY() <= this.minY) goto L44;
        this.translationY = this.scroller.getCurrY();
        goto L44
    }

    private void onLinkClick(ClickableSpan r1, TextView r2) {
    }

    private void onLinkLongPress(ClickableSpan r1, TextView r2, Runnable r3) {
    }

    private void onPhotoClosed(PhotoViewer.PlaceProviderObject r3) {
        this.isVisible = false;
        this.currentProvider = null;
        this.disableShowCheck = false;
        releasePlayer();
        new ArrayList();
        AndroidUtilities.runOnUIThread(new SecretMediaViewer$$ExternalSyntheticLambda4(this), 50);
    }

    private void preparePlayer(File r7) {
        if (this.parentActivity != null) goto L5;
        return;
    L5:
        releasePlayer();
        if (this.videoTextureView != null) goto L8;
        AspectRatioFrameLayout r0 = new AspectRatioFrameLayout(this.parentActivity);
        this.aspectRatioFrameLayout = r0;
        r0.setVisibility(0);
        this.containerView.addView(this.aspectRatioFrameLayout, 0, LayoutHelper.createFrame(-1, -1, 17));
        TextureView r02 = new TextureView(this.parentActivity);
        this.videoTextureView = r02;
        r02.setOpaque(false);
        this.aspectRatioFrameLayout.addView(this.videoTextureView, LayoutHelper.createFrame(-1, -1, 17));
    L8:
        this.textureUploaded = false;
        this.videoCrossfadeStarted = false;
        this.videoTextureView.setAlpha(1.0f);
        if (this.videoPlayer != null) goto L11;
        AnonymousClass1 r03 = new AnonymousClass1(this);
        this.videoPlayer = r03;
        r03.setTextureView(this.videoTextureView);
        this.videoPlayer.setDelegate(new AnonymousClass2(this, r7));
    L11:
        this.videoPlayer.preparePlayer(Uri.fromFile(r7), "other");
        this.videoPlayer.setPlayWhenReady(true);
        this.playButtonDrawable.setPause(true);
    }

    private boolean processTouchEvent(MotionEvent r13) {
        if (this.photoAnimationInProgress == 0) goto L5;
    L168:
        return false;
    L5:
        if (this.animationStartTime != 0) goto L168;
        if (r13.getPointerCount() != 1) goto L16;
        if (this.gestureDetector.onTouchEvent(r13) == false) goto L16;
        if (this.doubleTap == false) goto L16;
        this.doubleTap = false;
        this.moving = false;
        this.zooming = false;
        checkMinMax(false);
        return true;
    L16:
        if (r13.getActionMasked() != 0) goto L18;
    L153:
        this.discardTap = false;
        if (this.scroller.isFinished() == true) goto L157;
        this.scroller.abortAnimation();
    L157:
        if (this.draggingDown == true) goto L168;
        if (r13.getPointerCount() != 2) goto L164;
        this.pinchStartDistance = (float) Math.hypot(r13.getX(1) - r13.getX(0), r13.getY(1) - r13.getY(0));
        this.pinchStartScale = this.scale;
        this.pinchCenterX = (r13.getX(0) + r13.getX(1)) / 2.0f;
        this.pinchCenterY = (r13.getY(0) + r13.getY(1)) / 2.0f;
        this.pinchStartX = this.translationX;
        this.pinchStartY = this.translationY;
        this.zooming = true;
        this.moving = false;
        VelocityTracker r12 = this.velocityTracker;
        if (r12 == null) goto L168;
        r12.clear();
        goto L168
    L164:
        if (r13.getPointerCount() != 1) goto L168;
        this.moveStartX = r13.getX();
        float r132 = r13.getY();
        this.moveStartY = r132;
        this.dragY = r132;
        this.draggingDown = false;
        this.canDragDown = true;
        VelocityTracker r122 = this.velocityTracker;
        if (r122 == null) goto L168;
        r122.clear();
        goto L168
    L18:
        if (r13.getActionMasked() == 5) goto L153;
        float r9 = 0.0f;
        if (r13.getActionMasked() != 2) goto L99;
        if (r13.getPointerCount() != 2) goto L30;
        if (this.draggingDown == true) goto L30;
        if (this.zooming == false) goto L30;
        this.discardTap = true;
        this.scale = (((float) Math.hypot(r13.getX(1) - r13.getX(0), r13.getY(1) - r13.getY(0))) / this.pinchStartDistance) * this.pinchStartScale;
        this.translationX = (this.pinchCenterX - (getContainerViewWidth() / 2)) - (((this.pinchCenterX - (getContainerViewWidth() / 2)) - this.pinchStartX) * (this.scale / this.pinchStartScale));
        float r133 = this.pinchCenterY - (getContainerViewHeight() / 2);
        float r0 = (this.pinchCenterY - (getContainerViewHeight() / 2)) - this.pinchStartY;
        float r2 = this.scale;
        this.translationY = r133 - (r0 * (r2 / this.pinchStartScale));
        updateMinMax(r2);
        this.containerView.invalidate();
    L30:
        if (r13.getPointerCount() != 1) goto L168;
        VelocityTracker r02 = this.velocityTracker;
        if (r02 == null) goto L34;
        r02.addMovement(r13);
    L34:
        float r03 = Math.abs(r13.getX() - this.moveStartX);
        float r6 = Math.abs(r13.getY() - this.dragY);
        if (r03 <= AndroidUtilities.dp(3.0f)) goto L37;
    L38:
        this.discardTap = true;
    L40:
        if (this.canDragDown == false) goto L54;
        if (this.draggingDown == true) goto L54;
        if (this.scale != 1.0f) goto L54;
        if (r6 < AndroidUtilities.dp(30.0f)) goto L54;
        if ((r6 / 2.0f) <= r03) goto L54;
        this.draggingDown = true;
        this.moving = false;
        this.dragY = r13.getY();
        if (this.isActionBarVisible == false) goto L52;
        toggleActionBar(false, true);
    L52:
        return true;
    L54:
        if (this.draggingDown == false) goto L57;
        this.translationY = r13.getY() - this.dragY;
        this.containerView.invalidate();
        goto L168
    L57:
        if (this.invalidCoords == false) goto L59;
    L97:
        this.invalidCoords = false;
        this.moveStartX = r13.getX();
        this.moveStartY = r13.getY();
        goto L168
    L59:
        if (this.animationStartTime != 0) goto L97;
        float r04 = this.moveStartX - r13.getX();
        float r3 = this.moveStartY - r13.getY();
        if (this.moving == true) goto L69;
        if (this.scale != 1.0f) goto L67;
        if ((Math.abs(r3) + AndroidUtilities.dp(12.0f)) < Math.abs(r04)) goto L69;
    L67:
        if (this.scale == 1.0f) goto L168;
    L69:
        if (this.moving == true) goto L71;
        this.moving = true;
        this.canDragDown = false;
        r04 = 0.0f;
        r3 = 0.0f;
    L71:
        this.moveStartX = r13.getX();
        this.moveStartY = r13.getY();
        updateMinMax(this.scale);
        float r134 = this.translationX;
        if (r134 >= this.minX) goto L74;
    L75:
        r04 = r04 / 3.0f;
    L76:
        float r22 = this.maxY;
        if (r22 != 0.0f) goto L86;
        float r4 = this.minY;
        if (r4 != 0.0f) goto L86;
        float r5 = this.translationY;
        if ((r5 - r3) >= r4) goto L84;
        this.translationY = r4;
    L93:
        this.translationX = r134 - r04;
        if (this.scale == 1.0f) goto L96;
        this.translationY -= r9;
    L96:
        this.containerView.invalidate();
        goto L168
    L84:
        if ((r5 - r3) <= r22) goto L91;
        this.translationY = r22;
    L91:
        r9 = r3;
    L86:
        float r42 = this.translationY;
        if (r42 >= this.minY) goto L89;
    L92:
        r9 = r3 / 3.0f;
        goto L93
    L89:
        if (r42 <= r22) goto L91;
    L74:
        if (r134 <= this.maxX) goto L76;
    L37:
        if (r6 <= AndroidUtilities.dp(3.0f)) goto L40;
    L99:
        if (r13.getActionMasked() == 3) goto L105;
        if (r13.getActionMasked() == 1) goto L105;
        if (r13.getActionMasked() != 6) goto L168;
    L105:
        if (this.zooming == false) goto L127;
        this.invalidCoords = true;
        float r135 = this.scale;
        if (r135 >= 1.0f) goto L110;
        updateMinMax(1.0f);
        animateTo(1.0f, 0.0f, 0.0f, true);
    L125:
        this.zooming = false;
        goto L168
    L110:
        if (r135 <= 3.0f) goto L124;
        float r136 = (this.pinchCenterX - (getContainerViewWidth() / 2)) - (((this.pinchCenterX - (getContainerViewWidth() / 2)) - this.pinchStartX) * (3.0f / this.pinchStartScale));
        float r05 = (this.pinchCenterY - (getContainerViewHeight() / 2)) - (((this.pinchCenterY - (getContainerViewHeight() / 2)) - this.pinchStartY) * (3.0f / this.pinchStartScale));
        updateMinMax(3.0f);
        float r32 = this.minX;
        if (r136 >= r32) goto L114;
    L113:
        r136 = r32;
    L117:
        float r33 = this.minY;
        if (r05 >= r33) goto L120;
    L119:
        r05 = r33;
    L123:
        animateTo(3.0f, r136, r05, true);
        goto L125
    L120:
        r33 = this.maxY;
        if (r05 <= r33) goto L123;
    L114:
        r32 = this.maxX;
        if (r136 <= r32) goto L117;
    L124:
        checkMinMax(true);
        goto L125
    L127:
        if (this.draggingDown == false) goto L134;
        if (Math.abs(this.dragY - r13.getY()) <= (getContainerViewHeight() / 6.0f)) goto L131;
        closePhoto(true, false);
    L132:
        this.draggingDown = false;
        goto L168
    L131:
        animateTo(1.0f, 0.0f, 0.0f, false);
        goto L132
    L134:
        if (this.moving == false) goto L168;
        float r137 = this.translationX;
        float r06 = this.translationY;
        updateMinMax(this.scale);
        this.moving = false;
        this.canDragDown = true;
        VelocityTracker r23 = this.velocityTracker;
        if (r23 != null) goto L138;
    L140:
        float r24 = this.translationX;
        float r34 = this.minX;
        if (r24 >= r34) goto L143;
    L142:
        r137 = r34;
    L146:
        float r25 = this.translationY;
        float r35 = this.minY;
        if (r25 >= r35) goto L149;
    L148:
        r06 = r35;
    L152:
        animateTo(this.scale, r137, r06, false);
        goto L168
    L149:
        r35 = this.maxY;
        if (r25 <= r35) goto L152;
    L143:
        r34 = this.maxX;
        if (r24 <= r34) goto L146;
    L138:
        if (this.scale != 1.0f) goto L140;
        r23.computeCurrentVelocity(1000);
        goto L140
    }

    private void releasePlayer() {
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L19;
        this.playerRetryPlayCount = 0;
        r0.releasePlayer(true);
        this.videoPlayer = null;
    L19:
        Activity r02 = this.parentActivity;     // Catch: Exception -> L9
        if (r02 == null) goto L11;
        r02.getWindow().clearFlags(128);     // Catch: Exception -> L9
    L11:
        AspectRatioFrameLayout r03 = this.aspectRatioFrameLayout;
        if (r03 == null) goto L15;
        this.containerView.removeView(r03);
        this.aspectRatioFrameLayout = null;
    L15:
        if (this.videoTextureView == null) goto L17;
        this.videoTextureView = null;
    L17:
        this.isPlaying = false;
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L11
    }

    private void setCaptionHwLayerEnabled(boolean r3) {
        if (this.captionHwLayerEnabled == r3) goto L6;
        this.captionHwLayerEnabled = r3;
        this.captionTextViewSwitcher.setLayerType(2, null);
        this.captionTextViewSwitcher.getCurrentView().setLayerType(2, null);
        this.captionTextViewSwitcher.getNextView().setLayerType(2, null);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setCurrentCaption(MessageObject r19, CharSequence r20, boolean r21, boolean r22) {
        CharSequence r3 = AnimatedEmojiSpan.cloneSpans(r20, 3);
        boolean r7 = false;
        int r72 = 0;
        if (this.captionScrollView != null) goto L6;
        FrameLayout r4 = new FrameLayout(this.containerView.getContext());
        this.captionContainer = r4;
        this.captionTextViewSwitcher.setContainer(r4);
        AnonymousClass10 r42 = new AnonymousClass10(this, this.containerView.getContext(), this.captionTextViewSwitcher, this.captionContainer);
        this.captionScrollView = r42;
        this.captionTextViewSwitcher.setScrollView(r42);
        this.captionContainer.setClipChildren(false);
        this.captionScrollView.addView(this.captionContainer, new ViewGroup.LayoutParams(-1, -2));
        this.containerView.addView(this.captionScrollView, LayoutHelper.createFrame(-1, -1.0f, 80, 0.0f, 0.0f, 0.0f, 0.0f));
        this.textSelectionHelper.getOverlayView(this.containerView.getContext()).bringToFront();
    L6:
        if (this.captionTextViewSwitcher.getParent() == this.captionContainer) goto L8;
        this.captionTextViewSwitcher.setMeasureAllChildren(true);
        this.captionContainer.addView(this.captionTextViewSwitcher, -1, -2);
    L8:
        boolean r43 = TextUtils.isEmpty(r3);
        boolean r5 = TextUtils.isEmpty(this.captionTextViewSwitcher.getCurrentView().getText());
        PhotoViewer.CaptionTextViewSwitcher r8 = this.captionTextViewSwitcher;
        if (r22 == false) goto L11;
        TextView r82 = r8.getNextView();
    L12:
        int r10 = r82.getMaxLines();
        if (r10 != 1) goto L16;
        this.captionTextViewSwitcher.getCurrentView().setSingleLine(false);
        this.captionTextViewSwitcher.getNextView().setSingleLine(false);
    L16:
        if (r10 == Integer.MAX_VALUE) goto L18;
        this.captionTextViewSwitcher.getCurrentView().setMaxLines(Integer.MAX_VALUE);
        this.captionTextViewSwitcher.getNextView().setMaxLines(Integer.MAX_VALUE);
        this.captionTextViewSwitcher.getCurrentView().setEllipsize(null);
        this.captionTextViewSwitcher.getNextView().setEllipsize(null);
    L18:
        r82.setScrollX(0);
        PhotoViewer.CaptionScrollView r102 = this.captionScrollView;
        r102.dontChangeTopMargin = false;
        if (r22 == false) goto L27;
        TransitionManager.endTransitions(r102);
        TransitionSet r103 = new TransitionSet().addTransition(new AnonymousClass12(this, 2, r5, r43)).addTransition(new AnonymousClass11(this, 1, r5, r43)).setDuration(200);
        if (r5 == true) goto L23;
        this.captionScrollView.dontChangeTopMargin = true;
        r103.addTransition(new AnonymousClass13(this));
    L23:
        if (r5 == false) goto L26;
        if (r43 == true) goto L26;
        r103.addTarget(this.captionTextViewSwitcher);
    L26:
        TransitionManager.beginDelayedTransition(this.captionScrollView, r103);
        Object[] r104 = true;
    L32:
        if (r43 == true) goto L62;
        Theme.createChatResources(null, true);
        if (r19 == null) goto L42;
        TLRPC.Message r44 = r19.messageOwner;
        if (r44 == null) goto L42;
        if (r44.translatedText == null) goto L42;
        if (TextUtils.equals(r44.translatedToLanguage, TranslateAlert2.getToLanguage()) == false) goto L42;
    L50:
        this.captionTextViewSwitcher.setTag(r3);
        this.captionTextViewSwitcher.setText(r3, r22, false);     // Catch: Exception -> L55
        PhotoViewer.CaptionScrollView r0 = this.captionScrollView;     // Catch: Exception -> L55
        if (r0 == null) goto L57;
        r0.updateTopMargin();     // Catch: Exception -> L55
    L57:
        r82.setScrollY(0);
        r82.setTextColor(-1);
        PhotoViewer.CaptionTextViewSwitcher r02 = this.captionTextViewSwitcher;
        if (this.isActionBarVisible == true) goto L61;
        r72 = 4;
    L61:
        r02.setVisibility(r72);
    L68:
        if ((this.captionTextViewSwitcher.getCurrentView() instanceof PhotoViewer.CaptionTextView) == false) goto L73;
        ((PhotoViewer.CaptionTextView) this.captionTextViewSwitcher.getCurrentView()).setLoading(r21);
        return;
    L73:
        return;
    L55:
        e = move-exception;
        FileLog.e(e);
    L42:
        if (r19 != null) goto L44;
    L49:
        r3 = Emoji.replaceEmoji(new SpannableStringBuilder(r3), r82.getPaint().getFontMetricsInt(), false);
        goto L50
    L44:
        if (r19.messageOwner.entities.isEmpty() == true) goto L49;
        SpannableString r13 = new SpannableString(r3);
        r19.addEntitiesToText(r13, true, false);
        if (r19.isVideo() == false) goto L48;
        MessageObject.addUrlsByPattern(r19.isOutOwner(), r13, false, 3, (int) r19.getDuration(), false);
    L48:
        r3 = Emoji.replaceEmoji(r13, r82.getPaint().getFontMetricsInt(), false);
        goto L50
    L62:
        this.captionTextViewSwitcher.setText(null, r22);
        this.captionTextViewSwitcher.getCurrentView().setTextColor(-1);
        PhotoViewer.CaptionTextViewSwitcher r03 = this.captionTextViewSwitcher;
        if (r104 == false) goto L65;
        if (r5 == true) goto L65;
    L66:
        r03.setVisibility(4, r7);
        this.captionTextViewSwitcher.setTag(null);
    L65:
        r7 = true;
        goto L66
    L27:
        this.captionTextViewSwitcher.getCurrentView().setText(null);
        PhotoViewer.CaptionScrollView r105 = this.captionScrollView;
        if (r105 == null) goto L30;
        r105.scrollTo(0, 0);
    L30:
        r104 = false;
        goto L32
    L11:
        r82 = r8.getCurrentView();
        goto L12
    }

    private void showPlayButton(boolean r5, boolean r6) {
        if (this.isVideo == false) goto L6;
        if (r5 == false) goto L6;
        boolean r52 = true;
    L8:
        if (this.playButtonShown != r52) goto L11;
        if (r6 == false) goto L11;
        return;
    L11:
        this.playButtonShown = r52;
        this.playButton.animate().cancel();
        ImageView r0 = this.playButton;
        float r1 = 0.0f;
        float r2 = 0.6f;
        if (r6 == false) goto L25;
        ViewPropertyAnimator r4 = r0.animate();
        if (r52 == false) goto L16;
        float r62 = 1.0f;
    L17:
        ViewPropertyAnimator r42 = r4.scaleX(r62);
        if (r52 == false) goto L20;
        r2 = 1.0f;
    L20:
        ViewPropertyAnimator r43 = r42.scaleY(r2);
        if (r52 == false) goto L23;
        r1 = 1.0f;
    L23:
        r43.alpha(r1).setDuration(340).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        return;
    L16:
        r62 = 0.6f;
        goto L17
    L25:
        if (r52 == false) goto L27;
        float r63 = 1.0f;
    L28:
        r0.setScaleX(r63);
        ImageView r64 = this.playButton;
        if (r52 == false) goto L31;
        r2 = 1.0f;
    L31:
        r64.setScaleY(r2);
        ImageView r44 = this.playButton;
        if (r52 == false) goto L34;
        r1 = 1.0f;
    L34:
        r44.setAlpha(r1);
        return;
    L27:
        r63 = 0.6f;
    L6:
        r52 = false;
        goto L8
    }

    private void showSecretHint() {
        this.secretHint.setMultilineText(true);
        if (this.isVideo == false) goto L5;
        int r0 = R.string.VideoShownOnce;
    L6:
        String r02 = LocaleController.getString(r0);
        HintView2 r2 = this.secretHint;
        r2.setMaxWidthPx(HintView2.cutInFancyHalf(r02, r2.getTextPaint()));
        this.secretHint.setText(r02);
        this.secretHint.setInnerPadding(12.0f, 7.0f, 11.0f, 7.0f);
        this.secretHint.setIconMargin(2);
        this.secretHint.setIconTranslate(0.0f, 0.0f);
        this.secretHint.setIcon(R.raw.fire_on);
        this.secretHint.show();
        MessagesController.getGlobalMainSettings().edit().putInt("viewoncehint", MessagesController.getGlobalMainSettings().getInt("viewoncehint", 0) + 1).commit();
        return;
    L5:
        r0 = R.string.PhotoShownOnce;
        goto L6
    }

    private void toggleActionBar(boolean r10, boolean r11) {
        AndroidUtilities.cancelRunOnUIThread(this.hideActionBarRunnable);
        if (r10 == false) goto L8;
        if (this.isVideo == false) goto L8;
        AndroidUtilities.runOnUIThread(this.hideActionBarRunnable, 3000);
    L8:
        if (r10 == false) goto L10;
        this.actionBar.setVisibility(0);
    L10:
        this.actionBar.setEnabled(r10);
        this.isActionBarVisible = r10;
        showPlayButton(r10, r11);
        float r1 = 0.0f;
        if (r11 == false) goto L36;
        ArrayList r112 = new ArrayList();
        ActionBar r3 = this.actionBar;
        Property r4 = View.ALPHA;
        if (r10 == false) goto L15;
        float r5 = 1.0f;
    L16:
        r112.add(ObjectAnimator.ofFloat(r3, r4, new float[]{r5}));
        VideoPlayerControlFrameLayout r32 = this.seekbarContainer;
        Property<VideoPlayerControlFrameLayout, Float> r52 = r32.SEEKBAR_ALPHA;
        if (r10 == false) goto L19;
        float r7 = 1.0f;
    L20:
        r112.add(ObjectAnimator.ofFloat(r32, r52, new float[]{r7}));
        PhotoViewer.CaptionScrollView r33 = this.captionScrollView;
        if (r10 == false) goto L23;
        float r53 = 1.0f;
    L24:
        r112.add(ObjectAnimator.ofFloat(r33, r4, new float[]{r53}));
        View r34 = this.seekbarBackground;
        if (r10 == false) goto L27;
        float r54 = 1.0f;
    L28:
        r112.add(ObjectAnimator.ofFloat(r34, r4, new float[]{r54}));
        View r35 = this.navigationBar;
        if (r10 == false) goto L31;
        r1 = 1.0f;
    L31:
        r112.add(ObjectAnimator.ofFloat(r35, r4, new float[]{r1}));
        AnimatorSet r0 = new AnimatorSet();
        this.currentActionBarAnimation = r0;
        r0.playTogether(r112);
        if (r10 == true) goto L34;
        this.currentActionBarAnimation.addListener(new AnonymousClass15(this));
    L34:
        this.currentActionBarAnimation.setDuration(200);
        this.currentActionBarAnimation.start();
        return;
    L27:
        r54 = 0.0f;
        goto L28
    L23:
        r53 = 0.0f;
        goto L24
    L19:
        r7 = 0.0f;
        goto L20
    L15:
        r5 = 0.0f;
        goto L16
    L36:
        ActionBar r113 = this.actionBar;
        if (r10 == false) goto L39;
        float r36 = 1.0f;
    L40:
        r113.setAlpha(r36);
        PhotoViewer.CaptionScrollView r114 = this.captionScrollView;
        if (r10 == false) goto L43;
        float r37 = 1.0f;
    L44:
        r114.setAlpha(r37);
        View r115 = this.seekbarBackground;
        if (r10 == false) goto L47;
        float r38 = 1.0f;
    L48:
        r115.setAlpha(r38);
        View r116 = this.navigationBar;
        if (r10 == false) goto L51;
        r1 = 1.0f;
    L51:
        r116.setAlpha(r1);
        if (r10 == true) goto L55;
        this.actionBar.setVisibility(8);
        this.captionScrollView.scrollTo(0, 0);
        return;
    L55:
        return;
    L47:
        r38 = 0.0f;
        goto L48
    L43:
        r37 = 0.0f;
        goto L44
    L39:
        r36 = 0.0f;
        goto L40
    }

    private void updateMinMax(float r4) {
        int r0 = ((int) ((this.centerImage.getImageWidth() * r4) - getContainerViewWidth())) / 2;
        int r42 = ((int) ((this.centerImage.getImageHeight() * r4) - getContainerViewHeight())) / 2;
        if (r0 <= 0) goto L5;
        this.minX = -r0;
        this.maxX = r0;
    L6:
        if (r42 <= 0) goto L9;
        this.minY = -r42;
        this.maxY = r42;
        return;
    L9:
        this.maxY = 0.0f;
        this.minY = 0.0f;
        return;
    L5:
        this.maxX = 0.0f;
        this.minX = 0.0f;
        goto L6
    }

    private void updateVideoPlayerTime() {
        Arrays.fill(this.videoPlayerCurrentTime, 0);
        Arrays.fill(this.videoPlayerTotalTime, 0);
        VideoPlayer r0 = this.videoPlayer;
        if (r0 == null) goto L5;
        long r3 = Math.max(0, r0.getCurrentPosition());
        long r32 = r3 / 1000;
        long r5 = Math.max(0, this.videoPlayer.getDuration()) / 1000;
        int[] r02 = this.videoPlayerCurrentTime;
        r02[0] = (int) (r32 / 60);
        r02[1] = (int) (r32 % 60);
        int[] r03 = this.videoPlayerTotalTime;
        r03[0] = (int) (r5 / 60);
        r03[1] = (int) (r5 % 60);
    L5:
        int r04 = this.videoPlayerCurrentTime[0];
        if (r04 < 60) goto L8;
        String r05 = String.format(Locale.ROOT, "%02d:%02d:%02d", new Object[]{Integer.valueOf(r04 / 60), Integer.valueOf(this.videoPlayerCurrentTime[0] % 60), Integer.valueOf(this.videoPlayerCurrentTime[1])});
    L9:
        int r6 = this.videoPlayerTotalTime[0];
        if (r6 < 60) goto L12;
        String r1 = String.format(Locale.ROOT, "%02d:%02d:%02d", new Object[]{Integer.valueOf(r6 / 60), Integer.valueOf(this.videoPlayerTotalTime[0] % 60), Integer.valueOf(this.videoPlayerTotalTime[1])});
    L13:
        this.videoPlayerTime.setText(String.format(Locale.ROOT, "%s / %s", new Object[]{r05, r1}));
        return;
    L12:
        r1 = String.format(Locale.ROOT, "%02d:%02d", new Object[]{Integer.valueOf(r6), Integer.valueOf(this.videoPlayerTotalTime[1])});
        goto L13
    L8:
        r05 = String.format(Locale.ROOT, "%02d:%02d", new Object[]{Integer.valueOf(r04), Integer.valueOf(this.videoPlayerCurrentTime[1])});
        goto L9
    }

    public boolean closePhoto(boolean r26, boolean r27) {
        if (this.parentActivity == null) goto L62;
        if (this.isPhotoVisible == false) goto L62;
        if (checkPhotoAnimation() == true) goto L62;
        if (this.ignoreDelete == false) goto L13;
        if (r27 == false) goto L13;
        return false;
    L13:
        Activity r1 = this.parentActivity;
        if (r1 == null) goto L19;
        AndroidUtilities.setLightNavigationBar(r1, this.wasLightNavigationBar);
        AndroidUtilities.setNavigationBarColor(this.parentActivity, this.wasNavigationBarColor);
        Activity r12 = this.parentActivity;
        if ((r12 instanceof LaunchActivity) == false) goto L18;
        ((LaunchActivity) r12).animateNavigationBarColor(this.wasNavigationBarColor);
        goto L19
    L18:
        AndroidUtilities.setNavigationBarColor(r12, this.wasNavigationBarColor);
    L19:
        WindowVisibilityManager.Controller r13 = this.activityVisibilityController;
        if (r13 == null) goto L22;
        r13.destroy();
        this.activityVisibilityController = null;
    L22:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagesDeleted);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateMessageMedia);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didCreatedNewDeleteTask);
        this.isActionBarVisible = false;
        VelocityTracker r14 = this.velocityTracker;
        if (r14 == null) goto L25;
        r14.recycle();
        this.velocityTracker = null;
    L25:
        this.closeTime = System.currentTimeMillis();
        PhotoViewer.PhotoViewerProvider r6 = this.currentProvider;
        if (r6 == null) goto L33;
        MessageObject r7 = this.currentMessageObject;
        TLRPC.MessageMedia r15 = r7.messageOwner.media;
        if ((r15.photo instanceof TLRPC.TL_photoEmpty) == true) goto L33;
        if ((r15.document instanceof TLRPC.TL_documentEmpty) == true) goto L33;
        PhotoViewer.PlaceProviderObject r16 = r6.getPlaceForPhoto(r7, null, 0, true, false);
    L34:
        VideoPlayer r4 = this.videoPlayer;
        if (r4 == null) goto L38;
        r4.pause();
    L38:
        if (r26 == false) goto L57;
        this.photoAnimationInProgress = 3;
        this.containerView.invalidate();
        this.imageMoveAnimation = new AnimatorSet();
        if (r16 != null) goto L42;
    L47:
        char r162 = 7;
        char r17 = 6;
        char r18 = 5;
        char r19 = 4;
        int r5 = AndroidUtilities.displaySize.y + AndroidUtilities.statusBarHeight;
        if (this.translationY >= 0.0f) goto L51;
        r5 = -r5;
    L51:
        this.animateToY = r5;
    L52:
        this.animateToRadius = false;
        showPlayButton(false, true);
        if (this.isVideo == false) goto L55;
        this.videoCrossfadeStarted = false;
        this.textureUploaded = false;
        AnimatorSet r52 = this.imageMoveAnimation;
        ObjectAnimator r72 = ObjectAnimator.ofInt(this.photoBackgroundDrawable, AnimationProperties.COLOR_DRAWABLE_ALPHA, new int[]{0});
        ObjectAnimator r8 = ObjectAnimator.ofFloat(this, this.ANIMATION_VALUE, new float[]{0.0f, 1.0f});
        ActionBar r132 = this.actionBar;
        Property r142 = View.ALPHA;
        ObjectAnimator r133 = ObjectAnimator.ofFloat(r132, r142, new float[]{0.0f});
        ObjectAnimator r9 = ObjectAnimator.ofFloat(this.captionScrollView, r142, new float[]{0.0f});
        ObjectAnimator r122 = ObjectAnimator.ofFloat(this.navigationBar, r142, new float[]{0.0f});
        VideoPlayerControlFrameLayout r152 = this.seekbarContainer;
        ObjectAnimator r2 = ObjectAnimator.ofFloat(r152, r152.SEEKBAR_ALPHA, new float[]{0.0f});
        ObjectAnimator r42 = ObjectAnimator.ofFloat(this.seekbarContainer, r142, new float[]{0.0f});
        ObjectAnimator r3 = ObjectAnimator.ofFloat(this.secretHint, r142, new float[]{0.0f});
        ObjectAnimator r143 = ObjectAnimator.ofFloat(this, this.VIDEO_CROSSFADE_ALPHA, new float[]{0.0f});
        Animator[] r62 = new Animator[9];
        r62[0] = r72;
        r62[1] = r8;
        r62[2] = r133;
        r62[3] = r9;
        r62[r19] = r122;
        r62[r18] = r2;
        r62[r17] = r42;
        r62[r162] = r3;
        r62[8] = r143;
        r52.playTogether(r62);
        int r24 = 2;
    L56:
        this.photoAnimationEndRunnable = new SecretMediaViewer$$ExternalSyntheticLambda0(this, r16);
        this.imageMoveAnimation.setInterpolator(new DecelerateInterpolator());
        this.imageMoveAnimation.setDuration(250);
        this.imageMoveAnimation.addListener(new AnonymousClass17(this, r16));
        this.photoTransitionAnimationStartTime = System.currentTimeMillis();
        this.containerView.setLayerType(r24, null);
        this.imageMoveAnimation.start();
        Runnable r43 = null;
    L58:
        Runnable r110 = this.onClose;
        if (r110 == null) goto L61;
        r110.run();
        this.onClose = r43;
    L61:
        return true;
    L55:
        this.centerImage.setManualAlphaAnimator(true);
        AnimatorSet r22 = this.imageMoveAnimation;
        ObjectAnimator r32 = ObjectAnimator.ofInt(this.photoBackgroundDrawable, AnimationProperties.COLOR_DRAWABLE_ALPHA, new int[]{0});
        ObjectAnimator r44 = ObjectAnimator.ofFloat(this, this.ANIMATION_VALUE, new float[]{0.0f, 1.0f});
        ActionBar r53 = this.actionBar;
        Property r73 = View.ALPHA;
        ObjectAnimator r54 = ObjectAnimator.ofFloat(r53, r73, new float[]{0.0f});
        ObjectAnimator r82 = ObjectAnimator.ofFloat(this.captionScrollView, r73, new float[]{0.0f});
        ObjectAnimator r92 = ObjectAnimator.ofFloat(this.navigationBar, r73, new float[]{0.0f});
        VideoPlayerControlFrameLayout r123 = this.seekbarContainer;
        ObjectAnimator r124 = ObjectAnimator.ofFloat(r123, r123.SEEKBAR_ALPHA, new float[]{0.0f});
        ObjectAnimator r134 = ObjectAnimator.ofFloat(this.seekbarContainer, r73, new float[]{0.0f});
        ObjectAnimator r74 = ObjectAnimator.ofFloat(this.secretHint, r73, new float[]{0.0f});
        r24 = 2;
        ObjectAnimator r10 = ObjectAnimator.ofFloat(this.centerImage, AnimationProperties.IMAGE_RECEIVER_ALPHA, new float[]{0.0f});
        Animator[] r63 = new Animator[9];
        r63[0] = r32;
        r63[1] = r44;
        r63[2] = r54;
        r63[3] = r82;
        r63[r19] = r92;
        r63[r18] = r124;
        r63[r17] = r134;
        r63[r162] = r74;
        r63[8] = r10;
        r22.playTogether(r63);
        goto L56
    L42:
        if (r16.imageReceiver.getThumbBitmap() == null) goto L47;
        if (r27 == true) goto L47;
        if (this.onClose != null) goto L47;
        r16.imageReceiver.setVisible(false, true);
        RectF r135 = r16.imageReceiver.getDrawRegion();
        float r144 = r135.right - r135.left;
        r162 = 7;
        float r153 = r135.bottom - r135.top;
        Point r55 = AndroidUtilities.displaySize;
        r17 = 6;
        r18 = 5;
        r19 = 4;
        this.animateToScale = Math.max(r144 / r55.x, r153 / (r55.y + AndroidUtilities.statusBarHeight));
        float r75 = r16.viewX;
        float r83 = r135.left;
        this.animateToX = ((r75 + r83) + (r144 / 2.0f)) - (r6 / 2);
        this.animateToY = ((r16.viewY + r135.top) + (r153 / 2.0f)) - (r5 / 2);
        this.animateToClipHorizontal = Math.abs(r83 - r16.imageReceiver.getImageX());
        int r56 = (int) Math.abs(r135.top - r16.imageReceiver.getImageY());
        r16.parentView.getLocationInWindow(new int[2]);
        float r76 = (r6[1] - (r16.viewY + r135.top)) + r16.clipTopAddition;
        this.animateToClipTop = r76;
        float r57 = r56;
        this.animateToClipTop = Math.max(0.0f, Math.max(r76, r57));
        float r77 = (((r16.viewY + r135.top) + ((int) r153)) - (r6[1] + r16.parentView.getHeight())) + r16.clipBottomAddition;
        this.animateToClipBottom = r77;
        this.animateToClipBottom = Math.max(0.0f, Math.max(r77, r57));
        this.animateToClipTopOrigin = 0.0f;
        this.animateToClipTopOrigin = Math.max(0.0f, Math.max(0.0f, r57));
        this.animateToClipBottomOrigin = 0.0f;
        this.animateToClipBottomOrigin = Math.max(0.0f, Math.max(0.0f, r57));
        this.animationStartTime = System.currentTimeMillis();
        this.zoomAnimation = true;
        goto L52
    L57:
        showPlayButton(false, true);
        AnimatorSet r33 = new AnimatorSet();
        ObjectAnimator r45 = ObjectAnimator.ofFloat(this.containerView, View.SCALE_X, new float[]{0.9f});
        ObjectAnimator r58 = ObjectAnimator.ofFloat(this.containerView, View.SCALE_Y, new float[]{0.9f});
        ObjectAnimator r64 = ObjectAnimator.ofInt(this.photoBackgroundDrawable, AnimationProperties.COLOR_DRAWABLE_ALPHA, new int[]{0});
        ActionBar r78 = this.actionBar;
        Property r84 = View.ALPHA;
        ObjectAnimator r79 = ObjectAnimator.ofFloat(r78, r84, new float[]{0.0f});
        ObjectAnimator r93 = ObjectAnimator.ofFloat(this.captionScrollView, r84, new float[]{0.0f});
        ObjectAnimator r102 = ObjectAnimator.ofFloat(this.navigationBar, r84, new float[]{0.0f});
        VideoPlayerControlFrameLayout r125 = this.seekbarContainer;
        r33.playTogether(new Animator[]{r45, r58, r64, r79, r93, r102, ObjectAnimator.ofFloat(r125, r125.SEEKBAR_ALPHA, new float[]{0.0f}), ObjectAnimator.ofFloat(this.seekbarContainer, r84, new float[]{0.0f})});
        this.photoAnimationInProgress = 2;
        this.photoAnimationEndRunnable = new SecretMediaViewer$$ExternalSyntheticLambda1(this, r16);
        r33.setDuration(200);
        r33.addListener(new AnonymousClass18(this));
        this.photoTransitionAnimationStartTime = System.currentTimeMillis();
        r43 = null;
        this.containerView.setLayerType(2, null);
        r33.start();
    L33:
        r16 = null;
    L62:
        return false;
    }

    public void destroyPhotoViewer() {
        Runnable r0 = this.onClose;
        if (r0 == null) goto L5;
        r0.run();
        this.onClose = null;
    L5:
        WindowVisibilityManager.Controller r02 = this.activityVisibilityController;
        if (r02 == null) goto L8;
        r02.destroy();
        this.activityVisibilityController = null;
    L8:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagesDeleted);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateMessageMedia);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didCreatedNewDeleteTask);
        this.isVisible = false;
        this.currentProvider = null;
        ImageReceiver.BitmapHolder r03 = this.currentThumb;
        if (r03 == null) goto L11;
        r03.release();
        this.currentThumb = null;
    L11:
        releasePlayer();
        if (this.parentActivity == null) goto L23;
        FrameLayout r04 = this.windowView;
        if (r04 == null) goto L23;
    L18:
        e = move-exception;
        FileLog.e(e);
        goto L23
    L16:
        if (r04.getParent() == null) goto L20;
        ((WindowManager) this.parentActivity.getSystemService("window")).removeViewImmediate(this.windowView);     // Catch: Exception -> L18
    L20:
        this.windowView = null;     // Catch: Exception -> L18
    L23:
        Instance = null;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r8, int r9, Object... r10) {
        if (r8 != NotificationCenter.messagesDeleted) goto L26;
        if (((Boolean) r10[2]).booleanValue() == false) goto L8;
        return;
    L8:
        if (this.currentMessageObject != null) goto L11;
        return;
    L11:
        if (((Long) r10[1]).longValue() == 0) goto L14;
        return;
    L14:
        if (((ArrayList) r10[0]).contains(Integer.valueOf(this.currentMessageObject.getId())) == true) goto L16;
        return;
    L16:
        if (this.isVideo == false) goto L22;
        if (this.videoWatchedOneTime == true) goto L22;
        this.closeVideoAfterWatch = true;
        return;
    L22:
        if (closePhoto(true, true) == true) goto L65;
        this.closeAfterAnimation = true;
        return;
    L65:
        return;
    L26:
        if (r8 != NotificationCenter.didCreatedNewDeleteTask) goto L48;
        if (this.currentMessageObject != null) goto L30;
        return;
    L30:
        if (this.secretDeleteTimer != null) goto L33;
        return;
    L33:
        if (((Long) r10[0]).longValue() != this.currentDialogId) goto L74;
        SparseArray r82 = (SparseArray) r10[1];
        int r92 = 0;
    L37:
        if (r92 >= r82.size()) goto L75;
        int r102 = r82.keyAt(r92);
        ArrayList r1 = (ArrayList) r82.get(r102);
        int r2 = 0;
    L40:
        if (r2 >= r1.size()) goto L46;
        if (this.currentMessageObject.getId() == ((Integer) r1.get(r2)).intValue()) goto L43;
        r2 = r2 + 1;
        goto L40
    L43:
        this.currentMessageObject.messageOwner.destroyTime = r102;
        this.secretDeleteTimer.invalidate();
        return;
    L46:
        r92 = r92 + 1;
        goto L37
    L75:
        return;
    L74:
        return;
    L48:
        if (r8 == NotificationCenter.updateMessageMedia) goto L50;
        return;
    L50:
        if (this.currentMessageObject.getId() == ((TLRPC.Message) r10[0]).id) goto L52;
        return;
    L52:
        if (this.isVideo == false) goto L58;
        if (this.videoWatchedOneTime == true) goto L58;
        this.closeVideoAfterWatch = true;
        return;
    L58:
        if (closePhoto(true, true) == true) goto L69;
        this.closeAfterAnimation = true;
        return;
    }

    @Keep
    public float getAnimationValue() {
        return this.animationValue;
    }

    public long getCloseTime() {
        return this.closeTime;
    }

    public MessageObject getCurrentMessageObject() {
        return this.currentMessageObject;
    }

    public long getOpenTime() {
        return this.openTime;
    }

    @Keep
    public float getVideoCrossfadeAlpha() {
        return this.videoCrossfadeAlpha;
    }

    public boolean isShowingImage(MessageObject r2) {
        if (this.isVisible == true) goto L5;
        return false;
    L5:
        if (this.disableShowCheck == true) goto L15;
        if (r2 == null) goto L16;
        MessageObject r1 = this.currentMessageObject;
        if (r1 != null) goto L10;
        return false;
    L10:
        if (r1.getId() != r2.getId()) goto L18;
        return true;
    L18:
        return false;
    L16:
        return false;
    L15:
        return false;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent r10) {
        float r0 = this.scale;
        if (r0 != 1.0f) goto L10;
        if (this.translationY == 0.0f) goto L7;
    L8:
        return false;
    L7:
        if (this.translationX != 0.0f) goto L8;
    L10:
        if (this.animationStartTime == 0) goto L12;
    L32:
        return false;
    L12:
        if (this.photoAnimationInProgress != 0) goto L32;
        if (r0 != 1.0f) goto L29;
        float r02 = (r10.getX() - (getContainerViewWidth() / 2)) - (((r10.getX() - (getContainerViewWidth() / 2)) - this.translationX) * (3.0f / this.scale));
        float r1 = (r10.getY() - (getContainerViewHeight() / 2)) - (((r10.getY() - (getContainerViewHeight() / 2)) - this.translationY) * (3.0f / this.scale));
        updateMinMax(3.0f);
        float r102 = this.minX;
        if (r02 >= r102) goto L19;
    L18:
        r02 = r102;
    L22:
        float r103 = this.minY;
        if (r1 >= r103) goto L25;
    L24:
        r1 = r103;
    L28:
        animateTo(3.0f, r02, r1, true);
    L30:
        this.doubleTap = true;
        return true;
    L25:
        r103 = this.maxY;
        if (r1 <= r103) goto L28;
    L19:
        r102 = this.maxX;
        if (r02 <= r102) goto L22;
    L29:
        animateTo(1.0f, 0.0f, 0.0f, true);
        goto L30
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent r1) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent r1) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent r10, MotionEvent r11, float r12, float r13) {
        if (this.scale == 1.0f) goto L7;
        this.scroller.abortAnimation();
        this.scroller.fling(Math.round(this.translationX), Math.round(this.translationY), Math.round(r12), Math.round(r13), (int) this.minX, (int) this.maxX, (int) this.minY, (int) this.maxY);
        this.containerView.postInvalidate();
        return false;
    L7:
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent r1) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent r1, MotionEvent r2, float r3, float r4) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent r1) {
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent r5) {
        if (this.discardTap == false) goto L7;
        return false;
    L7:
        if (this.videoPlayer != null) goto L9;
    L22:
        toggleActionBar(!this.isActionBarVisible, true);
    L23:
        return true;
    L9:
        if (this.isActionBarVisible == false) goto L22;
        if (r5.getX() < this.playButton.getX()) goto L22;
        if (r5.getY() < this.playButton.getY()) goto L22;
        if (r5.getX() > (this.playButton.getX() + this.playButton.getMeasuredWidth())) goto L22;
        if (r5.getX() > (this.playButton.getX() + this.playButton.getMeasuredWidth())) goto L22;
        this.videoPlayer.setPlayWhenReady(!r5.getPlayWhenReady());
        if (this.videoPlayer.getPlayWhenReady() == false) goto L21;
        toggleActionBar(true, true);
        goto L23
    L21:
        showPlayButton(true, true);
        goto L23
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent r1) {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v5, types: [android.graphics.Paint, android.view.View$OnClickListener] */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r14v7 */
    public void openMedia(MessageObject r20, PhotoViewer.PhotoViewerProvider r21, Runnable r22, Runnable r23) {
        if (this.parentActivity == null) goto L101;
        if (r20 != null) goto L6;
        return;
    L6:
        if (r20.needDrawBluredPreview() == false) goto L103;
        if (r21 == null) goto L104;
        PhotoViewer.PlaceProviderObject r12 = r21.getPlaceForPhoto(r20, null, 0, true, false);
        if (r12 != null) goto L13;
        return;
    L13:
        if (r20.messageOwner.ttl != Integer.MAX_VALUE) goto L15;
        boolean r0 = true;
    L16:
        this.ignoreDelete = r0;
        this.onClose = r23;
        this.currentProvider = r21;
        this.openTime = System.currentTimeMillis();
        this.closeTime = 0;
        this.isActionBarVisible = true;
        this.isPhotoVisible = true;
        this.draggingDown = false;
        AspectRatioFrameLayout r02 = this.aspectRatioFrameLayout;
        if (r02 == null) goto L19;
        r02.setVisibility(4);
    L19:
        releasePlayer();
        this.pinchStartDistance = 0.0f;
        this.pinchStartScale = 1.0f;
        this.pinchCenterX = 0.0f;
        this.pinchCenterY = 0.0f;
        this.pinchStartX = 0.0f;
        this.pinchStartY = 0.0f;
        this.moveStartX = 0.0f;
        this.moveStartY = 0.0f;
        this.zooming = false;
        this.moving = false;
        this.doubleTap = false;
        this.invalidCoords = false;
        this.canDragDown = true;
        updateMinMax(this.scale);
        this.photoBackgroundDrawable.setAlpha(0);
        this.containerView.setAlpha(1.0f);
        this.containerView.setVisibility(0);
        this.secretDeleteTimer.setAlpha(1.0f);
        this.isVideo = false;
        this.videoWatchedOneTime = false;
        this.closeVideoAfterWatch = false;
        this.disableShowCheck = true;
        this.centerImage.setManualAlphaAnimator(false);
        this.videoWidth = 0;
        this.videoHeight = 0;
        RectF r4 = new RectF(r12.imageReceiver.getDrawRegion());
        r4.left = Math.max(r4.left, r12.imageReceiver.getImageX());
        r4.top = Math.max(r4.top, r12.imageReceiver.getImageY());
        r4.right = Math.min(r4.right, r12.imageReceiver.getImageX2());
        r4.bottom = Math.min(r4.bottom, r12.imageReceiver.getImageY2());
        float r03 = r4.width();
        float r5 = r4.height();
        Point r6 = AndroidUtilities.displaySize;
        this.scale = Math.max(r03 / r6.x, r5 / (r6.y + AndroidUtilities.statusBarHeight));
        int[] r8 = r12.radius;
        if (r8 == null) goto L25;
        this.animateFromRadius = new int[r8.length];
        int r82 = 0;
        char r212 = 4;
    L22:
        int[] r15 = r12.radius;
        if (r82 >= r15.length) goto L26;
        this.animateFromRadius[r82] = r15[r82];
        r82 = r82 + 1;
    L26:
        float r83 = r12.viewX;
        float r14 = r4.left;
        this.translationX = ((r83 + r14) + (r03 / 2.0f)) - (r7 / 2);
        this.translationY = ((r12.viewY + r4.top) + (r5 / 2.0f)) - (r6 / 2);
        this.clipHorizontal = Math.abs(r14 - r12.imageReceiver.getImageX());
        int r04 = (int) Math.abs(r4.top - r12.imageReceiver.getImageY());
        r12.parentView.getLocationInWindow(new int[2]);
        float r7 = (r6[1] - (r12.viewY + r4.top)) + r12.clipTopAddition;
        this.clipTop = r7;
        float r05 = r04;
        this.clipTop = Math.max(0.0f, Math.max(r7, r05));
        float r72 = (((r12.viewY + r4.top) + ((int) r5)) - (r6[1] + r12.parentView.getHeight())) + r12.clipBottomAddition;
        this.clipBottom = r72;
        this.clipBottom = Math.max(0.0f, Math.max(r72, r05));
        this.clipTopOrigin = 0.0f;
        this.clipTopOrigin = Math.max(0.0f, Math.max(0.0f, r05));
        this.clipBottomOrigin = 0.0f;
        this.clipBottomOrigin = Math.max(0.0f, Math.max(0.0f, r05));
        this.animationStartTime = System.currentTimeMillis();
        this.animateToX = 0.0f;
        this.animateToY = 0.0f;
        this.animateToClipBottom = 0.0f;
        this.animateToClipBottomOrigin = 0.0f;
        this.animateToClipHorizontal = 0.0f;
        this.animateToClipTop = 0.0f;
        this.animateToClipTopOrigin = 0.0f;
        this.animateToScale = 1.0f;
        this.animateToRadius = true;
        this.zoomAnimation = true;
        WindowVisibilityManager.Controller r06 = this.activityVisibilityController;
        if (r06 == null) goto L29;
        r06.destroy();
        this.activityVisibilityController = null;
    L29:
        this.activityVisibilityController = LaunchActivity.obtainActivityVisibilityController();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagesDeleted);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateMessageMedia);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didCreatedNewDeleteTask);
        this.currentDialogId = MessageObject.getPeerId(r20.messageOwner.peer_id);
        this.currentMessageObject = r20;
        TLRPC.Document r07 = r20.getDocument();
        ImageReceiver.BitmapHolder r42 = this.currentThumb;
        if (r42 == null) goto L32;
        r42.release();
        this.currentThumb = null;
    L32:
        this.currentThumb = r12.imageReceiver.getThumbBitmapSafe();
        this.seekbarContainer.setVisibility(8);
        if (r07 == null) goto L67;
        int r43 = 0;
    L36:
        if (r43 >= r07.attributes.size()) goto L42;
        TLRPC.DocumentAttribute r52 = r07.attributes.get(r43);
        if ((r52 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L39;
        r43 = r43 + 1;
        goto L36
    L39:
        TLRPC.TL_documentAttributeVideo r53 = (TLRPC.TL_documentAttributeVideo) r52;
        this.videoWidth = r53.w;
        this.videoHeight = r53.h;
    L42:
        if (MessageObject.isGifDocument(r07) == false) goto L55;
        this.actionBar.setTitle(LocaleController.getString(R.string.DisappearingGif));
        String r44 = r20.messageOwner.attachPath;
        if (r44 != null) goto L46;
    L49:
        ImageLocation r08 = ImageLocation.getForDocument(r07);
    L50:
        ImageReceiver r2 = this.centerImage;
        if (this.currentThumb == null) goto L53;
        BitmapDrawable r54 = new BitmapDrawable(this.currentThumb.bitmap);
    L54:
        ?? r142 = 0;
        float r17 = 0.0f;
        float r18 = 1.0f;
        r2.setImage(r08, null, r54, -1, null, r20, 1);
    L74:
        setCurrentCaption(r20, _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        setCurrentCaption(r20, r20.caption, false, true);
        toggleActionBar(true, false);
        showPlayButton(false, false);
        this.playButtonDrawable.setPause(true);
        boolean r09 = this.ignoreDelete;
        SecretDeleteTimer r24 = this.secretDeleteTimer;
        if (r09 == false) goto L77;
        SecretDeleteTimer.m18577$$Nest$msetOnce(r24);
        this.secretDeleteTimer.setOnClickListener(new SecretMediaViewer$$ExternalSyntheticLambda7(this));
    L96:
    L82:
        e = move-exception;
        FileLog.e(e);
    L84:
        ((WindowManager) this.parentActivity.getSystemService("window")).addView(this.windowView, this.windowLayoutParams);
        this.secretDeleteTimer.invalidate();
        this.isVisible = true;
        Window r010 = this.parentActivity.getWindow();
        this.wasLightNavigationBar = AndroidUtilities.getLightNavigationBar(r010);
        AndroidUtilities.setLightNavigationBar(this.parentActivity, false);
        AndroidUtilities.setLightNavigationBar(this.windowView, false);
        Activity r25 = this.parentActivity;
        if ((r25 instanceof LaunchActivity) == false) goto L87;
        this.wasNavigationBarColor = ((LaunchActivity) r25).getNavigationBarColor();
        ((LaunchActivity) this.parentActivity).animateNavigationBarColor(-16777216);
    L88:
        AnimatorSet r011 = new AnimatorSet();
        this.imageMoveAnimation = r011;
        ActionBar r26 = this.actionBar;
        Property r3 = View.ALPHA;
        ObjectAnimator r27 = ObjectAnimator.ofFloat(r26, r3, new float[]{0.0f, 1.0f});
        ObjectAnimator r45 = ObjectAnimator.ofFloat(this.captionScrollView, r3, new float[]{0.0f, 1.0f});
        ObjectAnimator r55 = ObjectAnimator.ofFloat(this.secretHint, r3, new float[]{0.0f, 1.0f});
        ObjectAnimator r62 = ObjectAnimator.ofInt(this.photoBackgroundDrawable, AnimationProperties.COLOR_DRAWABLE_ALPHA, new int[]{0, 255});
        ObjectAnimator r73 = ObjectAnimator.ofFloat(this, this.ANIMATION_VALUE, new float[]{0.0f, 1.0f});
        VideoPlayerControlFrameLayout r84 = this.seekbarContainer;
        ObjectAnimator r85 = ObjectAnimator.ofFloat(r84, r84.SEEKBAR_ALPHA, new float[]{r18});
        VideoPlayerControlFrameLayout r10 = this.seekbarContainer;
        if (this.isVideo == false) goto L91;
        r17 = r18;
    L91:
        ObjectAnimator r32 = ObjectAnimator.ofFloat(r10, r3, new float[]{r17});
        Animator[] r102 = new Animator[7];
        r102[0] = r27;
        r102[1] = r45;
        r102[2] = r55;
        r102[3] = r62;
        r102[r212] = r73;
        r102[5] = r85;
        r102[6] = r32;
        r011.playTogether(r102);
        this.photoAnimationInProgress = 3;
        this.photoAnimationEndRunnable = new SecretMediaViewer$$ExternalSyntheticLambda8(this, r22, r20);
        this.imageMoveAnimation.setDuration(250);
        this.imageMoveAnimation.addListener(new AnonymousClass14(this));
        this.photoTransitionAnimationStartTime = System.currentTimeMillis();
        if (SharedConfig.getDevicePerformanceClass() != 0) goto L94;
        this.containerView.setLayerType(2, r142);
    L94:
        this.imageMoveAnimation.setInterpolator(new DecelerateInterpolator());
        PhotoBackgroundDrawable.m18575$$Nest$fputframe(this.photoBackgroundDrawable, 0);
        PhotoBackgroundDrawable.m18574$$Nest$fputdrawRunnable(this.photoBackgroundDrawable, new SecretMediaViewer$$ExternalSyntheticLambda9(this, r12));
        this.imageMoveAnimation.start();
        return;
    L87:
        this.wasNavigationBarColor = r010.getNavigationBarColor();
        AndroidUtilities.setNavigationBarColor(this.parentActivity, -16777216);
        goto L88
    L79:
        if (this.windowView.getParent() == null) goto L84;
        ((WindowManager) this.parentActivity.getSystemService("window")).removeView(this.windowView);     // Catch: Exception -> L82
        goto L84
    L77:
        r24.setOnClickListener(r142);
        goto L96
    L53:
        r54 = null;
        goto L54
    L46:
        if (r20.attachPathExists == false) goto L49;
        r08 = ImageLocation.getForPath(r44);
        goto L50
    L55:
        r17 = 0.0f;
        r18 = 1.0f;
        r142 = 0;
        this.playerRetryPlayCount = 1;
        this.actionBar.setTitle(LocaleController.getString(R.string.DisappearingVideo));
        File r012 = new File(r20.messageOwner.attachPath);
        if (r012.exists() == false) goto L58;
        preparePlayer(r012);
    L62:
        this.isVideo = true;
        this.seekbarContainer.setVisibility(0);
        ImageReceiver r28 = this.centerImage;
        if (this.currentThumb == null) goto L65;
        BitmapDrawable r56 = new BitmapDrawable(this.currentThumb.bitmap);
    L66:
        r28.setImage(null, null, r56, -1, null, r20, 2);
        goto L74
    L65:
        r56 = null;
        goto L66
    L58:
        File r013 = FileLoader.getInstance(this.currentAccount).getPathToMessage(r20.messageOwner);
        File r29 = new File(r013.getAbsolutePath() + ".enc");
        if (r29.exists() == false) goto L61;
        r013 = r29;
    L61:
        preparePlayer(r013);
        goto L62
    L67:
        r17 = 0.0f;
        r18 = 1.0f;
        Object r143 = null;
        this.actionBar.setTitle(LocaleController.getString(R.string.DisappearingPhoto));
        TLRPC.PhotoSize r014 = FileLoader.getClosestPhotoSizeWithSize(r20.photoThumbs, AndroidUtilities.getPhotoSize());
        ImageReceiver r210 = this.centerImage;
        ImageLocation r33 = ImageLocation.getForObject(r014, r20.photoThumbsObject);
        if (this.currentThumb == null) goto L70;
        BitmapDrawable r57 = new BitmapDrawable(this.currentThumb.bitmap);
    L71:
        r210.setImage(r33, null, r57, -1, null, r20, 2);
        r142 = r143;
        if (r014 == null) goto L74;
        this.videoWidth = r014.w;
        this.videoHeight = r014.h;
        r142 = r143;
        goto L74
    L70:
        r57 = null;
        goto L71
    L25:
        r212 = 4;
        this.animateFromRadius = null;
        goto L26
    L15:
        r0 = false;
        goto L16
    L104:
        return;
    L103:
        return;
    }

    @Keep
    public void setAnimationValue(float r1) {
        this.animationValue = r1;
        this.containerView.invalidate();
    }

    public void setOnClose(Runnable r1) {
        this.onClose = r1;
    }

    public void setParentActivity(Activity r18) {
        int r2 = UserConfig.selectedAccount;
        this.currentAccount = r2;
        this.centerImage.setCurrentAccount(r2);
        if (this.parentActivity != r18) goto L5;
        return;
    L5:
        this.parentActivity = r18;
        this.scroller = new Scroller(r18);
        AnonymousClass3 r22 = new AnonymousClass3(this, r18);
        this.windowView = r22;
        r22.setBackgroundDrawable(this.photoBackgroundDrawable);
        this.windowView.setFocusable(true);
        this.windowView.setFocusableInTouchMode(true);
        this.windowView.setClipChildren(false);
        this.windowView.setClipToPadding(false);
        this.containerView = new AnonymousClass4(this, r18);
        View r23 = new View(r18);
        this.navigationBar = r23;
        r23.setBackgroundColor(2130706432);
        this.containerView.addView(this.navigationBar, LayoutHelper.createFrame(-1, -2, 80));
        this.containerView.setFocusable(false);
        this.windowView.addView(this.containerView);
        FrameLayout.LayoutParams r24 = (FrameLayout.LayoutParams) this.containerView.getLayoutParams();
        r24.width = -1;
        r24.height = -1;
        r24.gravity = 51;
        this.containerView.setLayoutParams(r24);
        this.containerView.setFitsSystemWindows(true);
        this.containerView.setOnApplyWindowInsetsListener(new SecretMediaViewer$$ExternalSyntheticLambda5(this));
        this.containerView.setSystemUiVisibility(1792);
        GestureDetector r25 = new GestureDetector(this.containerView.getContext(), this);
        this.gestureDetector = r25;
        r25.setOnDoubleTapListener(this);
        AnonymousClass5 r26 = new AnonymousClass5(this, r18);
        this.actionBar = r26;
        r26.setTitleColor(-1);
        this.actionBar.setSubtitleColor(-1);
        this.actionBar.setBackgroundColor(2130706432);
        this.actionBar.setOccupyStatusBar(true);
        this.actionBar.setItemsBackgroundColor(1090519039, false);
        this.actionBar.setItemsColor(-1, false);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setTitleRightMargin(AndroidUtilities.dp(70.0f));
        this.containerView.addView(this.actionBar, LayoutHelper.createFrame(-1, -2.0f));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass6(this));
        HintView2 r27 = new HintView2(r18, 1);
        this.secretHint = r27;
        r27.setJoint(1.0f, -26.0f);
        this.secretHint.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.containerView.addView(this.secretHint, LayoutHelper.createFrame(-1, 80.0f, 53, 0.0f, 48.0f, 0.0f, 0.0f));
        SecretDeleteTimer r28 = new SecretDeleteTimer(this, r18);
        this.secretDeleteTimer = r28;
        this.containerView.addView(r28, LayoutHelper.createFrame(119, 48.0f, 53, 0.0f, 0.0f, 0.0f, 0.0f));
        AnonymousClass7 r29 = new AnonymousClass7(this);
        this.seekbarContainer = new VideoPlayerControlFrameLayout(this, r18);
        View r6 = new View(r18);
        this.seekbarBackground = r6;
        r6.setBackgroundColor(2130706432);
        this.seekbarContainer.addView(this.seekbarBackground, LayoutHelper.createFrame(-1, -1, 119));
        SimpleTextView r5 = new SimpleTextView(this.containerView.getContext());
        this.videoPlayerTime = r5;
        r5.setTextColor(-1);
        this.videoPlayerTime.setGravity(53);
        this.videoPlayerTime.setTextSize(14);
        this.videoPlayerTime.setImportantForAccessibility(2);
        this.seekbarContainer.addView(this.videoPlayerTime, LayoutHelper.createFrame(-2, -2.0f, 53, 0.0f, 15.0f, 12.0f, 0.0f));
        AnonymousClass8 r52 = new AnonymousClass8(this, r18);
        this.seekbarView = r52;
        VideoPlayerSeekBar r62 = new VideoPlayerSeekBar(r52);
        this.seekbar = r62;
        r62.setHorizontalPadding(AndroidUtilities.dp(2.0f));
        this.seekbar.setColors(872415231, 872415231, -1, -1, -1, 1509949439);
        this.seekbar.setDelegate(r29);
        this.seekbarContainer.addView(this.seekbarView);
        this.containerView.addView(this.seekbarContainer, LayoutHelper.createFrame(-1, 48, 80));
        AnonymousClass9 r210 = new AnonymousClass9(this, null, new DarkThemeResourceProvider());
        this.textSelectionHelper = r210;
        r210.allowScrollPrentRelative = true;
        r210.useMovingOffset = false;
        PhotoViewer.CaptionTextViewSwitcher r211 = new PhotoViewer.CaptionTextViewSwitcher(this.containerView.getContext());
        this.captionTextViewSwitcher = r211;
        r211.setFactory(new SecretMediaViewer$$ExternalSyntheticLambda6(this, r18));
        this.captionTextViewSwitcher.setVisibility(4);
        setCaptionHwLayerEnabled(true);
        ImageView r212 = new ImageView(r18);
        this.playButton = r212;
        r212.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(64.0f), 1711276032));
        PlayPauseDrawable r1 = new PlayPauseDrawable(28);
        this.playButtonDrawable = r1;
        r1.setCallback(this.playButton);
        this.playButton.setImageDrawable(this.playButtonDrawable);
        this.playButton.setScaleType(ImageView.ScaleType.CENTER);
        this.playButton.setScaleX(0.6f);
        this.playButton.setScaleY(0.6f);
        this.playButton.setAlpha(0.0f);
        this.playButton.setPivotX(AndroidUtilities.dp(32.0f));
        this.playButton.setPivotY(AndroidUtilities.dp(32.0f));
        this.containerView.addView(this.playButton, LayoutHelper.createFrame(64, 64, 17));
        WindowManager.LayoutParams r12 = new WindowManager.LayoutParams();
        this.windowLayoutParams = r12;
        r12.height = -1;
        r12.format = -3;
        r12.width = -1;
        r12.gravity = 48;
        r12.type = 99;
        r12.flags = (-2147417848) | 8192;
        AndroidUtilities.logFlagSecure();
        this.centerImage.setParentView(this.containerView);
        this.centerImage.setForceCrossfade(true);
        TextSelectionHelper<Cell>.TextSelectionOverlay r13 = this.textSelectionHelper.getOverlayView(this.windowView.getContext());
        if (r13 == null) goto L8;
        AndroidUtilities.removeFromParent(r13);
        this.containerView.addView(r13);
    L8:
        this.textSelectionHelper.setParentView(this.containerView);
        this.textSelectionHelper.setInvalidateParent();
    }

    @Keep
    public void setVideoCrossfadeAlpha(float r1) {
        this.videoCrossfadeAlpha = r1;
        this.containerView.invalidate();
    }
}
