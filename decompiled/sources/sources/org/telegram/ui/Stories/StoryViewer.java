package org.telegram.ui.Stories;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.window.OnBackInvokedDispatcher;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FileStreamLoadOperation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.pip.source.IPipSourceDelegate;
import org.telegram.messenger.pip.utils.PipUtils;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.messenger.video.VideoPlayerHolderBase;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ArticleViewer;
import org.telegram.ui.AspectRatioFrameLayout;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgress;
import org.telegram.ui.Components.ReactionsContainerLayout;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.PeerStoriesView;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoriesListPlaceProvider;
import org.telegram.ui.Stories.StoriesUtilities;
import org.telegram.ui.Stories.recorder.LivePlayerView;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes3.dex */
public class StoryViewer implements NotificationCenter.NotificationCenterDelegate, BaseFragment.AttachedSheet, IPipSourceDelegate, AudioManager.OnAudioFocusChangeListener {
    public static boolean animationInProgress;
    private static boolean checkSilentMode;
    public static float currentSpeed;
    public static ArrayList<StoryViewer> globalInstances;
    private static boolean isInSilentMode;
    private static TL_stories.StoryItem lastStoryItem;
    private static final LongSparseArray<CharSequence> replyDrafts = null;
    private static boolean runOpenAnimationAfterLayout;
    public boolean ATTACHED_FRAGMENT_IS_EDGE_TO_EDGE;
    public boolean ATTACH_TO_FRAGMENT;
    public boolean USE_SURFACE_VIEW;
    boolean allowIntercept;
    public boolean allowScreenshots;
    boolean allowSelfStoriesView;
    boolean allowSwipeToDissmiss;
    boolean allowSwipeToReply;
    private boolean allowTouchesByViewpager;
    private boolean animateAvatar;
    StoriesListPlaceProvider.AvatarOverlaysView animateFromCell;
    AspectRatioFrameLayout aspectRatioFrameLayout;
    private volatile boolean audioFocusPaused;
    private volatile boolean audioFocusRequested;
    private final AudioManager audioManager;
    RectF avatarRectTmp;
    float clipBottom;
    float clipTop;
    HwFrameLayout containerView;
    public int currentAccount;
    Dialog currentDialog;
    PeerStoriesView.VideoPlayerSharedScope currentPlayerScope;
    BaseFragment.AttachedSheet currentSheet;
    public int dayStoryId;
    private Runnable delayedTapRunnable;
    ArrayList<Runnable> doOnAnimationReadyRunnables;
    private boolean flingCalled;
    public boolean foundViewToClose;
    BaseFragment fragment;
    public boolean fromBottomSheet;
    float fromDismissOffset;
    float fromHeight;
    private int[] fromRadius;
    float fromWidth;
    float fromX;
    float fromXCell;
    float fromY;
    float fromYCell;
    private boolean fullyVisible;
    GestureDetector gestureDetector;
    private volatile boolean hasAudioFocus;
    private float hideEnterViewProgress;
    boolean inSeekingMode;
    boolean inSwipeToDissmissMode;
    Paint inputBackgroundPaint;
    private boolean invalidateOutRect;
    private boolean isBulletinVisible;
    private boolean isCaption;
    private boolean isCaptionPartVisible;
    private boolean isClosed;
    private boolean isHintVisible;
    private boolean isInPinchToZoom;
    private boolean isInTextSelectionMode;
    private boolean isInTouchMode;
    private boolean isLikesReactions;
    public boolean isLongpressed;
    private boolean isOverlayVisible;
    private boolean isPopupVisible;
    private boolean isRecording;
    boolean isShowing;
    boolean isSingleStory;
    private boolean isSwiping;
    public boolean isTranslating;
    private boolean isWaiting;
    boolean keyboardVisible;
    long lastDialogId;
    int lastPosition;
    private float lastStoryContainerHeight;
    Uri lastUri;
    LivePlayer livePlayer;
    public LivePlayerView liveView;
    AnimationNotificationsLocker locker;
    Runnable longPressRunnable;
    private int messageId;
    private Runnable onCloseListener;
    ValueAnimator openCloseAnimator;
    boolean openedFromLightNavigationBar;
    private boolean opening;
    TL_stories.PeerStories overrideUserStories;
    LaunchActivity parentActivity;
    private boolean paused;
    public LivePlayerView pipLiveView;
    public PlaceProvider placeProvider;
    VideoPlayerHolder playerHolder;
    private long playerSavedPosition;
    float[] pointPosition;
    ArrayList<VideoPlayerHolder> preparedPlayers;
    float progressToDismiss;
    float progressToOpen;
    private int realKeyboardHeight;
    Theme.ResourcesProvider resourcesProvider;
    boolean reversed;
    public LongSparseIntArray savedPositions;
    float selfStoriesViewsOffset;
    SelfStoryViewsView selfStoryViewsView;
    private boolean showViewsAfterOpening;
    TL_stories.StoryItem singleStory;
    boolean singleStoryDeleted;
    private StoriesIntro storiesIntro;
    StoriesController.StoriesList storiesList;
    public StoriesViewPager storiesViewPager;
    private SurfaceView surfaceView;
    float swipeToDismissHorizontalDirection;
    float swipeToDismissHorizontalOffset;
    float swipeToDismissOffset;
    ValueAnimator swipeToDissmissBackAnimator;
    ValueAnimator swipeToReplyBackAnimator;
    float swipeToReplyOffset;
    float swipeToReplyProgress;
    boolean swipeToReplyWaitingKeyboard;
    ValueAnimator swipeToViewsAnimator;
    private TextureView textureView;
    public final TransitionViewHolder transitionViewHolder;
    public boolean unreadStateChanged;
    boolean verticalScrollDetected;
    private StoriesVolumeControl volumeControl;
    WindowManager.LayoutParams windowLayoutParams;
    WindowManager windowManager;
    public SizeNotifierFrameLayout windowView;

    /* renamed from: org.telegram.ui.Stories.StoryViewer$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 implements GestureDetector.OnGestureListener {
        final /* synthetic */ StoryViewer this$0;

        public AnonymousClass1(StoryViewer r1) {
            this.this$0 = r1;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r4) {
            StoryViewer.m19798$$Nest$fputflingCalled(this.this$0, false);
            StoryViewer r3 = this.this$0;
            if (StoryViewer.m19819$$Nest$mfindClickableView(r3, r3.windowView, r4.getX(), r4.getY(), false) == false) goto L5;
            return false;
        L5:
            return true;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent r3, MotionEvent r4, float r5, float r6) {
            StoryViewer r32 = this.this$0;
            if (r32.swipeToReplyOffset != 0.0f) goto L5;
        L13:
            StoryViewer r33 = this.this$0;
            if (r33.selfStoriesViewsOffset != 0.0f) goto L16;
        L26:
            StoryViewer.m19798$$Nest$fputflingCalled(this.this$0, true);
            return false;
        L16:
            if (r6 >= (-1000.0f)) goto L19;
            r33.cancelSwipeToViews(true);
            goto L26
        L19:
            if (r6 <= 1000.0f) goto L22;
            r33.cancelSwipeToViews(false);
            goto L26
        L22:
            if (r33.selfStoryViewsView.progressToOpen <= 0.5f) goto L24;
            boolean r42 = true;
        L25:
            r33.cancelSwipeToViews(r42);
            goto L26
        L24:
            r42 = false;
            goto L25
        L5:
            if (StoryViewer.m19791$$Nest$fgetstoriesIntro(r32) != null) goto L13;
            if (r6 >= (-1000.0f)) goto L13;
            StoryViewer r34 = this.this$0;
            if (r34.swipeToReplyWaitingKeyboard == true) goto L13;
            r34.swipeToReplyWaitingKeyboard = true;
            r34.windowView.performHapticFeedback(3);     // Catch: Exception -> L28
        L12:
            StoryViewer.m19825$$Nest$mshowKeyboard(this.this$0);
            goto L13
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent r1) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent r4, MotionEvent r5, float r6, float r7) {
            StoryViewer r42 = this.this$0;
            if (r42.inSwipeToDissmissMode == true) goto L5;
            return false;
        L5:
            if (r42.allowSwipeToReply == false) goto L19;
            r42.swipeToReplyOffset += r7;
            int r43 = AndroidUtilities.dp(200.0f);
            StoryViewer r52 = this.this$0;
            float r44 = r43;
            if (r52.swipeToReplyOffset > r44) goto L9;
        L12:
            StoryViewer r53 = this.this$0;
            r53.swipeToReplyProgress = Utilities.clamp(r53.swipeToReplyOffset / r44, 1.0f, 0.0f);
            if (this.this$0.storiesViewPager.getCurrentPeerView() == null) goto L15;
            this.this$0.storiesViewPager.getCurrentPeerView().invalidate();
        L15:
            StoryViewer r45 = this.this$0;
            if (r45.swipeToReplyOffset >= 0.0f) goto L18;
            r45.swipeToReplyOffset = 0.0f;
            r45.allowSwipeToReply = false;
            goto L19
        L18:
            return true;
        L9:
            if (r52.swipeToReplyWaitingKeyboard == true) goto L12;
            r52.swipeToReplyWaitingKeyboard = true;
            StoryViewer.m19825$$Nest$mshowKeyboard(r52);
            this.this$0.windowView.performHapticFeedback(3);     // Catch: Exception -> L49
        L19:
            StoryViewer r46 = this.this$0;
            if (r46.allowSelfStoriesView == false) goto L34;
            float r54 = r46.selfStoriesViewsOffset;
            if (r54 > r46.selfStoryViewsView.maxSelfStoriesViewsOffset) goto L24;
        L26:
            r46.selfStoriesViewsOffset = r54 + r7;
        L27:
            Bulletin.hideVisible(r46.windowView);
            if (this.this$0.storiesViewPager.getCurrentPeerView() == null) goto L30;
            this.this$0.storiesViewPager.getCurrentPeerView().invalidate();
        L30:
            this.this$0.containerView.invalidate();
            StoryViewer r47 = this.this$0;
            if (r47.selfStoriesViewsOffset >= 0.0f) goto L33;
            r47.selfStoriesViewsOffset = 0.0f;
            r47.allowSelfStoriesView = false;
            goto L34
        L33:
            return true;
        L24:
            if (r7 <= 0.0f) goto L26;
            r46.selfStoriesViewsOffset = r54 + (0.05f * r7);
        L34:
            StoryViewer r48 = this.this$0;
            if (r48.progressToDismiss <= 0.8f) goto L45;
            float r55 = -r7;
            if (r55 <= 0.0f) goto L41;
            if (r48.swipeToDismissOffset <= 0.0f) goto L41;
        L44:
            float r56 = 0.3f;
        L46:
            r48.swipeToDismissOffset -= r7 * r56;
            Bulletin.hideVisible(r48.windowView);
            StoryViewer.m19828$$Nest$mupdateProgressToDismiss(this.this$0);
            return true;
        L41:
            if (r55 >= 0.0f) goto L45;
            if (r48.swipeToDismissOffset < 0.0f) goto L44;
        L45:
            r56 = 0.6f;
            goto L46
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent r1) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent r4) {
            StoryViewer r0 = this.this$0;
            if (r0.selfStoriesViewsOffset == 0.0f) goto L6;
            return false;
        L6:
            if (r0.allowIntercept == true) goto L8;
        L29:
            return false;
        L8:
            if (r0.keyboardVisible == false) goto L10;
        L28:
            this.this$0.closeKeyboardOrEmoji();
            goto L29
        L10:
            if (StoryViewer.m19777$$Nest$fgetisCaption(r0) == true) goto L28;
            if (StoryViewer.m19778$$Nest$fgetisCaptionPartVisible(this.this$0) == true) goto L28;
            if (StoryViewer.m19780$$Nest$fgetisHintVisible(this.this$0) == true) goto L28;
            if (StoryViewer.m19782$$Nest$fgetisInTextSelectionMode(this.this$0) == true) goto L28;
            PeerStoriesView r02 = this.this$0.getCurrentPeerView();
            if (r02 != null) goto L21;
        L23:
            StoryViewer r03 = this.this$0;
            if (r4.getX() <= (this.this$0.containerView.getMeasuredWidth() * 0.33f)) goto L26;
            boolean r3 = true;
        L27:
            r03.switchByTap(r3);
            goto L29
        L26:
            r3 = false;
            goto L27
        L21:
            if (r02.currentStory.isLive() == false) goto L23;
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoryViewer$10, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ StoryViewer this$0;

        public AnonymousClass10(StoryViewer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            StoryViewer.m19817$$Nest$mcleanupBeforeExit(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoryViewer$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        float lastTouchX;
        SparseArray<Float> lastX;
        final RectF outFromRectAvatar;
        final RectF outFromRectContainer;
        final Path path;
        final float[] radii;
        final RectF rect1;
        final RectF rect2;
        final RectF rect3;
        float startX;
        float startY;
        final /* synthetic */ StoryViewer this$0;
        final /* synthetic */ BaseFragment val$fragment;

        /* renamed from: org.telegram.ui.Stories.StoryViewer$2$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass2 this$1;

            public AnonymousClass1(AnonymousClass2 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                StoryViewer r0 = this.this$1.this$0;
                r0.swipeToDismissHorizontalOffset = 0.0f;
                StoryViewer.m19828$$Nest$mupdateProgressToDismiss(r0);
            }
        }

        /* renamed from: org.telegram.ui.Stories.StoryViewer$2$2, reason: invalid class name and collision with other inner class name */
        public class C00682 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass2 this$1;

            public C00682(AnonymousClass2 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                StoryViewer r0 = this.this$1.this$0;
                r0.swipeToDismissOffset = 0.0f;
                r0.swipeToReplyOffset = 0.0f;
                StoryViewer.m19828$$Nest$mupdateProgressToDismiss(r0);
            }
        }

        /* renamed from: org.telegram.ui.Stories.StoryViewer$2$3, reason: invalid class name */
        public class AnonymousClass3 implements Bulletin.Delegate {
            float[] position;
            final /* synthetic */ AnonymousClass2 this$1;

            public AnonymousClass3(AnonymousClass2 r1) {
                this.this$1 = r1;
                this.position = new float[2];
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getBottomOffset(int r4) {
                PeerStoriesView r42 = this.this$1.this$0.getCurrentPeerView();
                if (r42 != null) goto L6;
                return 0;
            L6:
                AndroidUtilities.getViewPositionInParent(r42.storyContainer, this.this$1.this$0.windowView, this.position);
                return (int) (this.this$1.getMeasuredHeight() - (this.position[1] + r42.storyContainer.getMeasuredHeight()));
            }
        }

        public static /* synthetic */ void $r8$lambda$5rtTT8iOZP3RLVfIJU_EH7ICwAU(AnonymousClass2 r0, ValueAnimator r1) {
            r0.lambda$dispatchTouchEvent$0(r1);
        }

        /* renamed from: $r8$lambda$8ez5i_jJ-rLBPta8JgpZvF6faQ8, reason: not valid java name */
        public static /* synthetic */ void m19832$r8$lambda$8ez5i_jJrLBPta8JgpZvF6faQ8(AnonymousClass2 r0) {
            r0.lambda$onInterceptTouchEvent$1();
        }

        public static /* synthetic */ void $r8$lambda$kggqMoeA28_Rg9Hq10wLXSeh_Ss(AnonymousClass2 r0, ValueAnimator r1) {
            r0.lambda$onTouchEvent$2(r1);
        }

        public AnonymousClass2(StoryViewer r1, Context r2, BaseFragment r3) {
            this.this$0 = r1;
            this.val$fragment = r3;
            super(r2);
            this.radii = new float[8];
            this.path = new Path();
            this.rect1 = new RectF();
            this.rect2 = new RectF();
            this.rect3 = new RectF();
            this.outFromRectAvatar = new RectF();
            this.outFromRectContainer = new RectF();
            this.lastX = new SparseArray();
        }

        private /* synthetic */ void lambda$dispatchTouchEvent$0(ValueAnimator r2) {
            StoryViewer r0 = this.this$0;
            r0.swipeToDismissHorizontalOffset = ((Float) r2.getAnimatedValue()).floatValue();
            StoryViewer.m19828$$Nest$mupdateProgressToDismiss(this.this$0);
        }

        private /* synthetic */ void lambda$onInterceptTouchEvent$1() {
            StoryViewer.m19823$$Nest$msetInTouchMode(this.this$0, true);
        }

        private /* synthetic */ void lambda$onTouchEvent$2(ValueAnimator r2) {
            StoryViewer r0 = this.this$0;
            r0.swipeToDismissOffset = ((Float) r2.getAnimatedValue()).floatValue();
            StoryViewer.m19828$$Nest$mupdateProgressToDismiss(this.this$0);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r21) {
            r21.drawColor(ColorUtils.setAlphaComponent(-16777216, (int) (StoryViewer.m19820$$Nest$mgetBlackoutAlpha(this.this$0) * 255.0f)));
            StoryViewer r2 = this.this$0;
            if (r2.ATTACH_TO_FRAGMENT == true) goto L5;
        L13:
            PeerStoriesView r22 = this.this$0.storiesViewPager.getCurrentPeerView();
            if (r22 == null) goto L37;
            PeerStoriesView.PeerHeaderView r3 = r22.headerView;
            if (StoryViewer.m19770$$Nest$fgetanimateAvatar(this.this$0) == false) goto L22;
            ImageReceiver r4 = r3.backupImageView.getImageReceiver();
            if (this.this$0.progressToOpen != 1.0f) goto L20;
            boolean r5 = true;
        L21:
            r4.setVisible(r5, true);
        L24:
            if (StoryViewer.m19776$$Nest$fgetinvalidateOutRect(this.this$0) == false) goto L36;
            StoryViewer.m19801$$Nest$fputinvalidateOutRect(this.this$0, false);
            View r42 = r3.backupImageView;
            float r52 = 0.0f;
            float r6 = 0.0f;
        L26:
            if (r42 == this) goto L35;
            if (r42.getParent() != this) goto L32;
            r52 = r52 + r42.getLeft();
            float r7 = r42.getTop();
        L30:
            r6 = r6 + r7;
        L34:
            r42 = (View) r42.getParent();
            goto L26
        L32:
            if (r42.getParent() == this.this$0.storiesViewPager) goto L34;
            r52 = r52 + r42.getX();
            r7 = r42.getY();
            goto L30
        L35:
            this.outFromRectAvatar.set(r52, r6, r3.backupImageView.getMeasuredWidth() + r52, r3.backupImageView.getMeasuredHeight() + r6);
            this.outFromRectContainer.set(0.0f, r22.getTop() + r22.storyContainer.getTop(), this.this$0.containerView.getMeasuredWidth(), this.this$0.containerView.getMeasuredHeight());
            this.this$0.containerView.getMatrix().mapRect(this.outFromRectAvatar);
            this.this$0.containerView.getMatrix().mapRect(this.outFromRectContainer);
        L36:
            PeerStoriesView.PeerHeaderView r14 = r3;
        L38:
            StoryViewer.m19794$$Nest$fgetvolumeControl(this.this$0).setAlpha(1.0f - this.this$0.progressToDismiss);
            StoryViewer r32 = this.this$0;
            if (r32.swipeToDismissHorizontalOffset != 0.0f) goto L41;
            float r33 = 1.0f - Utilities.clamp(Math.abs(r32.swipeToDismissOffset / getMeasuredHeight()), 1.0f, 0.0f);
        L42:
            StoryViewer r43 = this.this$0;
            r43.storiesViewPager.setHorizontalProgressToDismiss((r43.swipeToDismissHorizontalOffset / r43.containerView.getMeasuredWidth()) * this.this$0.progressToOpen);
            StoryViewer r44 = this.this$0;
            if (r44.fromX == 0.0f) goto L45;
        L46:
            float r53 = r44.progressToOpen;
            if (r53 != 1.0f) goto L50;
        L48:
            r44.containerView.setAlpha(r44.progressToOpen);
            StoryViewer r23 = this.this$0;
            float r45 = ((r23.progressToOpen * 0.1f) + 0.75f) + (r33 * 0.15f);
            r23.containerView.setScaleX(r45);
            this.this$0.containerView.setScaleY(r45);
            StoryViewer r24 = this.this$0;
            r24.containerView.setTranslationY(r24.swipeToDismissOffset);
            StoryViewer r25 = this.this$0;
            r25.containerView.setTranslationX(r25.swipeToDismissHorizontalOffset);
            super.dispatchDraw(r21);
        L225:
            if (StoryViewer.m19830$$Nest$sfgetrunOpenAnimationAfterLayout() == false) goto L235;
            StoryViewer.m19826$$Nest$mstartOpenAnimation(this.this$0);
            StoryViewer.m19831$$Nest$sfputrunOpenAnimationAfterLayout(false);
            return;
        L235:
            return;
        L50:
            if (StoryViewer.m19779$$Nest$fgetisClosed(r44) == true) goto L52;
        L54:
            this.this$0.containerView.setAlpha(1.0f);
            float r15 = r53;
        L56:
            if (StoryViewer.m19779$$Nest$fgetisClosed(this.this$0) == false) goto L62;
            StoryViewer r46 = this.this$0;
            TransitionViewHolder r72 = r46.transitionViewHolder;
            if (r72 == null) goto L62;
            if (r72.storyImage == null) goto L62;
            HwFrameLayout r47 = r46.containerView;
            float r16 = 0.15f;
            float r18 = 1.0f;
            r47.setAlpha(r47.getAlpha() * ((float) Math.pow(r15, 0.20000000298023224d)));
        L63:
            StoryViewer r48 = this.this$0;
            HwFrameLayout r62 = r48.containerView;
            float r49 = (r48.fromX - r62.getLeft()) - (this.this$0.containerView.getMeasuredWidth() / 2.0f);
            StoryViewer r73 = this.this$0;
            float r12 = r73.progressToOpen;
            r62.setTranslationX((r49 * (r18 - r12)) + (r73.swipeToDismissHorizontalOffset * r12));
            StoryViewer r410 = this.this$0;
            HwFrameLayout r63 = r410.containerView;
            float r411 = (r410.fromY - r63.getTop()) - (this.this$0.containerView.getMeasuredHeight() / 2.0f);
            StoryViewer r74 = this.this$0;
            float r122 = r74.progressToOpen;
            r63.setTranslationY((r411 * (r18 - r122)) + (r74.swipeToDismissOffset * r122));
            float r34 = AndroidUtilities.lerp(this.this$0.fromWidth / r4.containerView.getMeasuredWidth(), (r33 * r16) + 0.85f, r53);
            this.this$0.containerView.setScaleX(r34);
            this.this$0.containerView.setScaleY(r34);
            this.path.rewind();
            RectF r35 = this.rect1;
            StoryViewer r412 = this.this$0;
            float r64 = r412.fromX;
            float r75 = r412.fromWidth;
            float r8 = r412.fromY;
            float r413 = r412.fromHeight;
            r35.set(r64 - (r75 / 2.0f), r8 - (r413 / 2.0f), r64 + (r75 / 2.0f), r8 + (r413 / 2.0f));
            if (StoryViewer.m19779$$Nest$fgetisClosed(this.this$0) == true) goto L66;
        L68:
            RectF r36 = this.rect2;
            if (r22 == null) goto L71;
            r36.set(0.0f, r22.storyContainer.getTop() + this.this$0.fromDismissOffset, getMeasuredWidth(), getMeasuredHeight() + this.this$0.fromDismissOffset);
        L73:
            if (StoryViewer.m19779$$Nest$fgetisClosed(this.this$0) == true) goto L75;
        L77:
            float r26 = AndroidUtilities.lerp(this.rect1.centerX(), this.rect2.centerX(), this.this$0.progressToOpen);
            float r414 = AndroidUtilities.lerp(this.rect1.centerY(), this.rect2.centerY(), this.this$0.progressToOpen);
            float r65 = AndroidUtilities.lerp(this.rect1.height(), this.rect2.height(), r53);
            float r76 = AndroidUtilities.lerp(this.rect1.width(), this.rect2.width(), r53);
            if (StoryViewer.m19779$$Nest$fgetisClosed(this.this$0) == true) goto L80;
        L82:
            RectF r37 = AndroidUtilities.rectTmp;
            float r77 = r76 / 2.0f;
            float r66 = r65 / 2.0f;
            r37.set(r26 - r77, r414 - r66, r26 + r77, r414 + r66);
            if (StoryViewer.m19770$$Nest$fgetanimateAvatar(this.this$0) == false) goto L85;
            float[] r27 = this.radii;
            float r415 = AndroidUtilities.lerp(this.this$0.fromWidth / 2.0f, 0.0f, r53);
            r27[7] = r415;
            r27[6] = r415;
            r27[5] = r415;
            r27[4] = r415;
            r27[3] = r415;
            r27[2] = r415;
            r27[1] = r415;
            r27[0] = r415;
        L89:
            this.path.addRoundRect(r37, this.radii, Path.Direction.CCW);
            r21.save();
            StoryViewer r28 = this.this$0;
            float r38 = r28.clipTop;
            if (r38 != 0.0f) goto L92;
        L94:
            StoryViewer r29 = this.this$0;
            TransitionViewHolder r39 = r29.transitionViewHolder;
            if (r39 == null) goto L103;
            StoriesUtilities.AvatarStoryParams r310 = r39.params;
            if (r310 == null) goto L103;
            if (r310.drawnLive == false) goto L103;
            if (StoryViewer.m19770$$Nest$fgetanimateAvatar(r29) == false) goto L103;
            r21.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (r15 * 255.0f), 31);
        L104:
            r21.clipPath(this.path);
            super.dispatchDraw(r21);
            StoryViewer r210 = this.this$0;
            TransitionViewHolder r311 = r210.transitionViewHolder;
            if (r311 != null) goto L107;
        L119:
            float r17 = 0.0f;
        L120:
            r21.restore();
            if (r14 == null) goto L214;
            StoryViewer r211 = this.this$0;
            float r312 = r211.swipeToDismissHorizontalOffset;
            float r416 = r211.swipeToDismissOffset;
            View r54 = r14.backupImageView;
            if (StoryViewer.m19779$$Nest$fgetisClosed(r211) == false) goto L127;
            if (StoryViewer.m19770$$Nest$fgetanimateAvatar(this.this$0) == false) goto L127;
            this.rect2.set(this.outFromRectAvatar);
        L141:
            AndroidUtilities.lerp(this.rect1, this.rect2, this.this$0.progressToOpen, this.rect3);
            int r11 = r21.getSaveCount();
            StoryViewer r212 = this.this$0;
            TransitionViewHolder r313 = r212.transitionViewHolder;
            if (r313 == null) goto L147;
            HolderClip r314 = r313.drawClip;
            if (r314 == null) goto L147;
            r314.clip(r21, this.rect3, r18 - r15, StoryViewer.m19787$$Nest$fgetopening(r212));
        L147:
            if (StoryViewer.m19770$$Nest$fgetanimateAvatar(this.this$0) == false) goto L213;
            StoryViewer r213 = this.this$0;
            TransitionViewHolder r315 = r213.transitionViewHolder;
            if (r315 != null) goto L151;
        L153:
            boolean r123 = false;
        L154:
            PeerStoriesView r214 = r213.storiesViewPager.getCurrentPeerView();
            if (r214 == null) goto L161;
            PeerStoriesView.StoryItemHolder r316 = r214.currentStory;
            if (r316 == null) goto L161;
            if (r316.storyItem == null) goto L161;
            TLRPC.Chat r215 = MessagesController.getInstance(this.this$0.currentAccount).getChat(Long.valueOf(-r214.currentStory.storyItem.dialogId));
        L162:
            if (r215 != null) goto L164;
        L166:
            boolean r13 = false;
        L167:
            if (r123 == true) goto L169;
        L170:
            TransitionViewHolder r216 = this.this$0.transitionViewHolder;
            if (r216 == null) goto L177;
            StoriesUtilities.AvatarStoryParams r217 = r216.params;
            if (r217 == null) goto L177;
            if (r217.drawnLive == false) goto L177;
            r21.saveLayerAlpha(this.rect3.left - AndroidUtilities.dp(4.0f), this.rect3.top - AndroidUtilities.dp(4.0f), this.rect3.right + AndroidUtilities.dp(4.0f), this.rect3.bottom + AndroidUtilities.dp(4.0f), 255, 31);
        L177:
            r14.backupImageView.getImageReceiver().setImageCoords(this.rect3);
            TransitionViewHolder r218 = this.this$0.transitionViewHolder;
            if (r218 == null) goto L180;
            Integer r219 = r218.getAvatarImageRoundRadius();
        L181:
            int r317 = ExteraConfig.getAvatarCorners(this.rect3.width(), true, r13);
            if (r219 == null) goto L184;
            int r220 = r219.intValue();
        L185:
            r14.backupImageView.getImageReceiver().setRoundRadius(AndroidUtilities.lerp(r317, r220, r18 - this.this$0.progressToOpen));
            r14.backupImageView.getImageReceiver().setVisible(true, false);
            if (r123 == false) goto L188;
            float r221 = this.this$0.progressToOpen;
        L189:
            TransitionViewHolder r318 = this.this$0.transitionViewHolder;
            if (r318 != null) goto L192;
        L196:
            float r319 = r221;
        L197:
            r14.backupImageView.getImageReceiver().setAlpha(r319);
            r14.drawUploadingProgress(r21, this.rect3, !StoryViewer.m19830$$Nest$sfgetrunOpenAnimationAfterLayout(), this.this$0.progressToOpen);
            r14.backupImageView.getImageReceiver().draw(r21);
            r14.backupImageView.getImageReceiver().setAlpha(r221);
            r14.backupImageView.getImageReceiver().setVisible(false, false);
            TransitionViewHolder r222 = this.this$0.transitionViewHolder;
            if (r222 == null) goto L204;
            StoriesUtilities.AvatarStoryParams r223 = r222.params;
            if (r223 == null) goto L204;
            if (r223.drawnLive == false) goto L204;
            RectF r224 = AndroidUtilities.rectTmp;
            r224.set(this.rect3);
            StoriesUtilities.drawLive(r21, r224, r18 - this.this$0.progressToOpen, true, r17);
            r21.restore();
        L204:
            StoryViewer r225 = this.this$0;
            if (r225.progressToOpen == r18) goto L208;
            if (r123 == false) goto L208;
            r225.avatarRectTmp.set(r225.transitionViewHolder.crossfadeToAvatarImage.getImageX(), this.this$0.transitionViewHolder.crossfadeToAvatarImage.getImageY(), this.this$0.transitionViewHolder.crossfadeToAvatarImage.getImageX2(), this.this$0.transitionViewHolder.crossfadeToAvatarImage.getImageY2());
            int r226 = this.this$0.transitionViewHolder.crossfadeToAvatarImage.getRoundRadius()[0];
            boolean r320 = this.this$0.transitionViewHolder.crossfadeToAvatarImage.getVisible();
            this.this$0.transitionViewHolder.crossfadeToAvatarImage.setImageCoords(this.rect3);
            this.this$0.transitionViewHolder.crossfadeToAvatarImage.setRoundRadius(ExteraConfig.getAvatarCorners(this.rect3.width(), true, r13));
            this.this$0.transitionViewHolder.crossfadeToAvatarImage.setVisible(true, false);
            r21.saveLayerAlpha(this.rect3, (int) ((r18 - this.this$0.progressToOpen) * 255.0f), 31);
            this.this$0.transitionViewHolder.crossfadeToAvatarImage.draw(r21);
            r21.restore();
            this.this$0.transitionViewHolder.crossfadeToAvatarImage.setVisible(r320, false);
            StoryViewer r321 = this.this$0;
            r321.transitionViewHolder.crossfadeToAvatarImage.setImageCoords(r321.avatarRectTmp);
            this.this$0.transitionViewHolder.crossfadeToAvatarImage.setRoundRadius(r226);
        L208:
            StoryViewer r227 = this.this$0;
            TransitionViewHolder r322 = r227.transitionViewHolder;
            if (r322 == null) goto L213;
            HolderDrawAbove r323 = r322.drawAbove;
            if (r323 == null) goto L213;
            r323.draw(r21, this.rect3, r18 - r15, StoryViewer.m19787$$Nest$fgetopening(r227));
            goto L213
        L192:
            if (r318.alpha >= r18) goto L196;
            Paint r324 = r318.bgPaint;
            if (r324 == null) goto L196;
            r324.setAlpha((int) ((r18 - r15) * 255.0f));
            r21.drawCircle(this.rect3.centerX(), this.rect3.centerY(), this.rect3.width() / 2.0f, this.this$0.transitionViewHolder.bgPaint);
            r319 = AndroidUtilities.lerp(this.this$0.transitionViewHolder.alpha, r221, r15);
            goto L197
        L188:
            r221 = r18;
            goto L189
        L184:
            r220 = ExteraConfig.getAvatarCorners(this.rect3.width(), true, r13);
            goto L185
        L180:
            r219 = null;
            goto L181
        L169:
            if (this.this$0.progressToOpen == r17) goto L204;
        L164:
            if (r215.forum == false) goto L166;
            r13 = true;
        L161:
            r215 = null;
            goto L162
        L151:
            if (r315.crossfadeToAvatarImage == null) goto L153;
            r123 = true;
        L213:
            r21.restoreToCount(r11);
        L127:
            if (r54 == this) goto L140;
            if (r54 == null) goto L140;
            if (r54.getParent() != this) goto L134;
            r312 = r312 + r54.getLeft();
            float r228 = r54.getTop();
        L132:
            r416 = r416 + r228;
        L137:
            if ((r54.getParent() instanceof View) == false) goto L140;
            r54 = (View) r54.getParent();
            goto L127
        L134:
            if (r54.getParent() == this.this$0.storiesViewPager) goto L137;
            r312 = r312 + r54.getX();
            r228 = r54.getY();
        L140:
            this.rect2.set(r312, r416, r14.backupImageView.getMeasuredWidth() + r312, r14.backupImageView.getMeasuredHeight() + r416);
        L214:
            StoryViewer r229 = this.this$0;
            if (r229.animateFromCell == null) goto L223;
            float r325 = r18;
            float r230 = Utilities.clamp(r229.progressToOpen / 0.4f, r325, 0.0f);
            if (r230 == r325) goto L223;
            RectF r326 = AndroidUtilities.rectTmp;
            StoryViewer r417 = this.this$0;
            float r55 = r417.fromX;
            float r67 = r417.fromY;
            r326.set(r55, r67, r417.fromWidth + r55, r417.fromHeight + r67);
            r326.inset(-AndroidUtilities.dp(16.0f), -AndroidUtilities.dp(16.0f));
            if (r230 == 0.0f) goto L221;
            r21.saveLayerAlpha(r326, (int) ((1.0f - r230) * 255.0f), 31);
        L222:
            StoryViewer r231 = this.this$0;
            r21.translate(r231.fromXCell, r231.fromYCell);
            this.this$0.animateFromCell.drawAvatarOverlays(r21);
            r21.restore();
            goto L223
        L221:
            r21.save();
        L223:
            r21.restore();
            goto L225
        L107:
            if (r311.storyImage == null) goto L119;
            PeerStoriesView r232 = r210.storiesViewPager.getCurrentPeerView();
            if (r232 == null) goto L119;
            if (r232.storyContainer == null) goto L119;
            boolean r327 = this.this$0.transitionViewHolder.storyImage.getVisible();
            this.rect2.set(((this.this$0.swipeToDismissHorizontalOffset + r5.containerView.getLeft()) + r232.getX()) + r232.storyContainer.getX(), ((this.this$0.swipeToDismissOffset + r5.containerView.getTop()) + r232.getY()) + r232.storyContainer.getY(), ((this.this$0.swipeToDismissHorizontalOffset + r5.containerView.getRight()) - (this.this$0.containerView.getWidth() - r232.getRight())) - (r232.getWidth() - r232.storyContainer.getRight()), ((this.this$0.swipeToDismissOffset + r5.containerView.getBottom()) - (this.this$0.containerView.getHeight() - r232.getBottom())) - (r232.getHeight() - r232.storyContainer.getBottom()));
            AndroidUtilities.lerp(this.rect1, this.rect2, r15, this.rect3);
            float r233 = this.this$0.transitionViewHolder.storyImage.getImageX();
            float r418 = this.this$0.transitionViewHolder.storyImage.getImageY();
            float r56 = this.this$0.transitionViewHolder.storyImage.getImageWidth();
            float r68 = this.this$0.transitionViewHolder.storyImage.getImageHeight();
            this.this$0.transitionViewHolder.storyImage.setImageCoords(this.rect3);
            float r112 = r18 - r15;
            this.this$0.transitionViewHolder.storyImage.setAlpha(r112);
            this.this$0.transitionViewHolder.storyImage.setVisible(true, false);
            int r78 = r21.getSaveCount();
            StoryViewer r82 = this.this$0;
            HolderClip r124 = r82.transitionViewHolder.drawClip;
            r17 = 0.0f;
            if (r124 == null) goto L115;
            r124.clip(r21, this.rect3, r112, StoryViewer.m19787$$Nest$fgetopening(r82));
        L115:
            this.this$0.transitionViewHolder.storyImage.draw(r21);
            StoryViewer r83 = this.this$0;
            HolderDrawAbove r125 = r83.transitionViewHolder.drawAbove;
            if (r125 == null) goto L118;
            r125.draw(r21, this.rect3, r112, StoryViewer.m19787$$Nest$fgetopening(r83));
        L118:
            this.this$0.transitionViewHolder.storyImage.setVisible(r327, false);
            this.this$0.transitionViewHolder.storyImage.setImageCoords(r233, r418, r56, r68);
            r21.restoreToCount(r78);
        L103:
            r21.save();
            goto L104
        L92:
            if (r28.clipBottom == 0.0f) goto L94;
            float r234 = AndroidUtilities.lerp(0.0f, r38, (float) Math.pow(r18 - r28.progressToOpen, 0.4000000059604645d));
            float r328 = getMeasuredWidth();
            float r419 = getMeasuredHeight();
            StoryViewer r57 = this.this$0;
            r21.clipRect(0.0f, r234, r328, AndroidUtilities.lerp(r419, r57.clipBottom, r18 - r57.progressToOpen));
            goto L94
        L85:
            int[] r235 = StoryViewer.m19773$$Nest$fgetfromRadius(this.this$0);
            float[] r420 = this.radii;
            if (r235 == null) goto L88;
            float r236 = AndroidUtilities.lerp(StoryViewer.m19773$$Nest$fgetfromRadius(this.this$0)[0], 0, r15);
            r420[1] = r236;
            r420[0] = r236;
            float[] r237 = this.radii;
            float r421 = AndroidUtilities.lerp(StoryViewer.m19773$$Nest$fgetfromRadius(this.this$0)[1], 0, r15);
            r237[3] = r421;
            r237[2] = r421;
            float[] r238 = this.radii;
            float r422 = AndroidUtilities.lerp(StoryViewer.m19773$$Nest$fgetfromRadius(this.this$0)[2], 0, r15);
            r238[5] = r422;
            r238[4] = r422;
            float[] r239 = this.radii;
            float r423 = AndroidUtilities.lerp(StoryViewer.m19773$$Nest$fgetfromRadius(this.this$0)[3], 0, r15);
            r239[7] = r423;
            r239[6] = r423;
            goto L89
        L88:
            r420[7] = 0.0f;
            r420[6] = 0.0f;
            r420[5] = 0.0f;
            r420[4] = 0.0f;
            r420[3] = 0.0f;
            r420[2] = 0.0f;
            r420[1] = 0.0f;
            r420[0] = 0.0f;
            goto L89
        L80:
            if (StoryViewer.m19770$$Nest$fgetanimateAvatar(this.this$0) == false) goto L82;
            this.rect1.inset(-AndroidUtilities.dp(12.0f), -AndroidUtilities.dp(12.0f));
            goto L82
        L75:
            if (StoryViewer.m19770$$Nest$fgetanimateAvatar(this.this$0) == false) goto L77;
            this.rect1.inset(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
            goto L77
        L71:
            r36.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            goto L73
        L66:
            if (StoryViewer.m19770$$Nest$fgetanimateAvatar(this.this$0) == false) goto L68;
            this.rect2.set(this.outFromRectContainer);
        L62:
            r16 = 0.15f;
            r18 = 1.0f;
            StoryViewer.m19779$$Nest$fgetisClosed(this.this$0);
            goto L63
        L52:
            if (StoryViewer.m19770$$Nest$fgetanimateAvatar(this.this$0) == false) goto L54;
            float r58 = this.this$0.progressToOpen;
            float r424 = 1.0f - Utilities.clamp(((1.0f - r58) - 0.8f) / 0.100000024f, 1.0f, 0.0f);
            float r79 = Utilities.clamp(r58 - ((1.0f - r424) * 0.05f), 1.0f, 0.0f);
            this.this$0.containerView.setAlpha(r424);
            r15 = r58;
            r53 = r79;
            goto L56
        L45:
            if (r44.fromY == 0.0f) goto L48;
        L41:
            r33 = 1.0f;
            goto L42
        L20:
            r5 = false;
            goto L21
        L22:
            r3.backupImageView.getImageReceiver().setVisible(true, false);
            goto L24
        L37:
            r14 = null;
            goto L38
        L5:
            if ((r2.progressToOpen * (1.0f - r2.progressToDismiss)) != 1.0f) goto L7;
            boolean r329 = true;
        L9:
            if (StoryViewer.m19774$$Nest$fgetfullyVisible(r2) == r329) goto L13;
            StoryViewer.m19799$$Nest$fputfullyVisible(this.this$0, r329);
            if (this.val$fragment.getLayoutContainer() == null) goto L13;
            this.val$fragment.getLayoutContainer().invalidate();
            goto L13
        L7:
            r329 = false;
            goto L9
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEventPreIme(KeyEvent r4) {
            if (r4.getKeyCode() != 24) goto L5;
        L15:
            StoryViewer.m19818$$Nest$mdispatchVolumeEvent(this.this$0, r4);
            return true;
        L5:
            if (r4.getKeyCode() == 25) goto L15;
            if (r4.getKeyCode() != 4) goto L14;
            if (r4.getAction() != 1) goto L14;
            this.this$0.onAttachedBackPressed();
            return true;
        L14:
            return super.dispatchKeyEventPreIme(r4);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r19) {
            PeerStoriesView r2 = this.this$0.storiesViewPager.getCurrentPeerView();
            if (r2 == null) goto L8;
            if (r2.checkTextSelectionEvent(r19) == false) goto L8;
            return true;
        L8:
            if (StoryViewer.m19784$$Nest$fgetisLikesReactions(this.this$0) == false) goto L14;
            if (r2 == null) goto L14;
            if (r2.checkReactionEvent(r19) == false) goto L14;
            return true;
        L14:
            if (r19.getAction() != 1) goto L16;
        L19:
            StoryViewer r4 = this.this$0;
            r4.inSwipeToDissmissMode = false;
            AndroidUtilities.cancelRunOnUIThread(r4.longPressRunnable);
            StoryViewer r42 = this.this$0;
            float r9 = r42.swipeToDismissHorizontalOffset;
            if (r9 == 0.0f) goto L22;
            r42.swipeToDissmissBackAnimator = ValueAnimator.ofFloat(new float[]{r9, 0.0f});
            this.this$0.swipeToDissmissBackAnimator.addUpdateListener(new StoryViewer$2$$ExternalSyntheticLambda1(this));
            this.this$0.swipeToDissmissBackAnimator.addListener(new AnonymousClass1(this));
            this.this$0.swipeToDissmissBackAnimator.setDuration(250);
            this.this$0.swipeToDissmissBackAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
            this.this$0.swipeToDissmissBackAnimator.start();
        L22:
            StoryViewer r43 = this.this$0;
            if (r43.progressToDismiss < 0.3f) goto L25;
            r43.close(true);
        L25:
            StoryViewer.m19823$$Nest$msetInTouchMode(this.this$0, false);
            StoryViewer.m19824$$Nest$msetLongPressed(this.this$0, false);
            boolean r44 = true;
        L27:
            if (r19.getAction() != 0) goto L32;
            this.this$0.swipeToReplyWaitingKeyboard = false;
            if (r2 == null) goto L31;
            r2.onActionDown(r19);
        L31:
            this.this$0.storiesViewPager.onTouchEvent(MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0));
        L32:
            StoryViewer r92 = this.this$0;
            if (r92.keyboardVisible == false) goto L35;
        L39:
            boolean r93 = false;
        L40:
            StoryViewer r10 = this.this$0;
            if (r10.selfStoriesViewsOffset == 0.0f) goto L43;
        L76:
            boolean r5 = false;
        L77:
            if (r2 == null) goto L90;
            StoryViewer r94 = this.this$0;
            if (r94.selfStoriesViewsOffset != 0.0f) goto L90;
            if (r94.inSwipeToDissmissMode == true) goto L90;
            if (StoryViewer.m19777$$Nest$fgetisCaption(r94) == true) goto L90;
            if (StoryViewer.m19785$$Nest$fgetisRecording(this.this$0) == true) goto L90;
            StoryViewer r95 = this.this$0;
            if (r95.storiesViewPager.currentState == 1) goto L90;
            AndroidUtilities.getViewPositionInParent(r2.storyContainer, this, r95.pointPosition);
            float[] r22 = this.this$0.pointPosition;
            r19.offsetLocation(-r22[0], -r22[1]);
            this.this$0.storiesViewPager.getCurrentPeerView().checkPinchToZoom(r19);
            float[] r23 = this.this$0.pointPosition;
            r19.offsetLocation(r23[0], r23[1]);
        L90:
            if (r19.getAction() != 1) goto L92;
        L98:
            this.lastX.clear();
        L99:
            if (r5 == false) goto L101;
            return true;
        L101:
            boolean r24 = super.dispatchTouchEvent(r19);
            if (r19.getAction() != 1) goto L104;
        L105:
            StoryViewer r1 = this.this$0;
            if (r1.selfStoriesViewsOffset != 0.0f) goto L108;
        L116:
            PeerStoriesView r12 = this.this$0.getCurrentPeerView();
            if (r12 == null) goto L119;
            r12.cancelTouch();
        L119:
            if (r44 == false) goto L123;
            StoryViewer r13 = this.this$0;
            if (r13.swipeToReplyWaitingKeyboard == true) goto L123;
            r13.cancelSwipeToReply();
        L123:
            if (r24 == false) goto L125;
        L130:
            return true;
        L125:
            if (StoryViewer.animationInProgress == true) goto L127;
        L129:
            return false;
        L127:
            if (StoryViewer.m19783$$Nest$fgetisInTouchMode(this.this$0) == false) goto L129;
        L108:
            if (StoryViewer.m19772$$Nest$fgetflingCalled(r1) == true) goto L116;
            if (StoryViewer.m19789$$Nest$fgetrealKeyboardHeight(this.this$0) >= AndroidUtilities.dp(20.0f)) goto L116;
            StoryViewer r14 = this.this$0;
            if (r14.selfStoryViewsView.progressToOpen <= 0.5f) goto L114;
            boolean r52 = true;
        L115:
            r14.cancelSwipeToViews(r52);
            goto L116
        L114:
            r52 = false;
            goto L115
        L104:
            if (r19.getAction() != 3) goto L119;
        L92:
            if (r19.getAction() == 3) goto L98;
            int r25 = 0;
        L96:
            if (r25 >= r19.getPointerCount()) goto L99;
            this.lastX.put(r19.getPointerId(r25), Float.valueOf(r19.getX(r25)));
            r25 = r25 + 1;
            goto L96
        L43:
            if (r10.inSwipeToDissmissMode == true) goto L76;
            if (r10.storiesViewPager.currentState != 1) goto L76;
            if (r19.getAction() != 2) goto L76;
            if (r93 == false) goto L76;
            float r53 = this.lastX.get(r19.getPointerId(0), Float.valueOf(0.0f)).floatValue() - r19.getX(0);
            if (r53 == 0.0f) goto L54;
            if (this.this$0.storiesViewPager.canScroll(r53) == true) goto L54;
        L55:
            StoryViewer r102 = this.this$0;
            float r11 = r102.swipeToDismissHorizontalOffset;
            if (r11 != 0.0f) goto L59;
            r102.swipeToDismissHorizontalDirection = -r53;
        L59:
            if (r53 < 0.0f) goto L61;
        L62:
            if (r53 > 0.0f) goto L64;
        L66:
            r102.swipeToDismissHorizontalOffset = r11 - r53;
            StoryViewer.m19828$$Nest$mupdateProgressToDismiss(r102);
            StoryViewer r54 = this.this$0;
            float r96 = r54.swipeToDismissHorizontalOffset;
            if (r96 <= 0.0f) goto L71;
            if (r54.swipeToDismissHorizontalDirection >= 0.0f) goto L71;
        L74:
            r54.swipeToDismissHorizontalOffset = 0.0f;
        L75:
            r5 = true;
        L71:
            if (r96 >= 0.0f) goto L75;
            if (r54.swipeToDismissHorizontalDirection <= 0.0f) goto L75;
        L64:
            if (r102.swipeToDismissHorizontalDirection >= 0.0f) goto L66;
        L65:
            r53 = r53 * 0.2f;
            goto L66
        L61:
            if (r102.swipeToDismissHorizontalDirection > 0.0f) goto L65;
        L54:
            if (this.this$0.swipeToDismissHorizontalOffset == 0.0f) goto L76;
        L35:
            if (StoryViewer.m19779$$Nest$fgetisClosed(r92) == true) goto L39;
            if (StoryViewer.m19785$$Nest$fgetisRecording(this.this$0) == true) goto L39;
            r93 = true;
            goto L40
        L16:
            if (r19.getAction() == 3) goto L19;
            r44 = false;
            goto L27
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != this.this$0.aspectRatioFrameLayout) goto L7;
            return false;
        L7:
            return super.drawChild(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            BaseFragment r0 = this.val$fragment;
            if (r0 == null) goto L9;
            StoryViewer r1 = this.this$0;
            if (r1.ATTACH_TO_FRAGMENT == false) goto L9;
            if (r1.ATTACHED_FRAGMENT_IS_EDGE_TO_EDGE == true) goto L9;
            AndroidUtilities.requestAdjustResize(r0.getParentActivity(), this.val$fragment.getClassGuid());
        L9:
            Bulletin.addDelegate(this, new AnonymousClass3(this));
            NotificationCenter.getInstance(this.this$0.currentAccount).addObserver(this.this$0, NotificationCenter.storiesListUpdated);
            NotificationCenter.getInstance(this.this$0.currentAccount).addObserver(this.this$0, NotificationCenter.storiesUpdated);
            NotificationCenter.getInstance(this.this$0.currentAccount).addObserver(this.this$0, NotificationCenter.articleClosed);
            NotificationCenter.getInstance(this.this$0.currentAccount).addObserver(this.this$0, NotificationCenter.openArticle);
            NotificationCenter.getInstance(this.this$0.currentAccount).addObserver(this.this$0, NotificationCenter.storyDeleted);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            Bulletin.removeDelegate(this);
            NotificationCenter.getInstance(this.this$0.currentAccount).removeObserver(this.this$0, NotificationCenter.storiesListUpdated);
            NotificationCenter.getInstance(this.this$0.currentAccount).removeObserver(this.this$0, NotificationCenter.storiesUpdated);
            NotificationCenter.getInstance(this.this$0.currentAccount).removeObserver(this.this$0, NotificationCenter.articleClosed);
            NotificationCenter.getInstance(this.this$0.currentAccount).removeObserver(this.this$0, NotificationCenter.openArticle);
            NotificationCenter.getInstance(this.this$0.currentAccount).removeObserver(this.this$0, NotificationCenter.storyDeleted);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r15) {
            if (r15.getAction() != 0) goto L47;
            if (this.this$0.progressToOpen != 1.0f) goto L47;
            float r0 = r15.getX();
            this.lastTouchX = r0;
            this.startX = r0;
            this.startY = r15.getY();
            StoryViewer r02 = this.this$0;
            r02.verticalScrollDetected = false;
            if (StoryViewer.m19785$$Nest$fgetisRecording(r02) == true) goto L11;
            StoryViewer r4 = this.this$0;
            if (StoryViewer.m19819$$Nest$mfindClickableView(r4, r4.windowView, r15.getX(), r15.getY(), false) == true) goto L11;
            boolean r42 = true;
        L12:
            r02.allowIntercept = r42;
            StoryViewer r03 = this.this$0;
            if (StoryViewer.m19785$$Nest$fgetisRecording(r03) == true) goto L17;
            StoryViewer r43 = this.this$0;
            if (StoryViewer.m19819$$Nest$mfindClickableView(r43, r43.windowView, r15.getX(), r15.getY(), true) == true) goto L17;
            boolean r44 = true;
        L18:
            r03.allowSwipeToDissmiss = r44;
            StoryViewer r04 = this.this$0;
            if (r04.allowIntercept == true) goto L21;
        L23:
            boolean r45 = false;
        L24:
            StoryViewer.m19823$$Nest$msetInTouchMode(r04, r45);
            PeerStoriesView r05 = this.this$0.getCurrentPeerView();
            if (this.this$0.allowIntercept == false) goto L30;
            if (r05 == null) goto L30;
            LiveCommentsView r06 = r05.liveCommentsView;
            if (r06 == null) goto L30;
            r06.setAllowTouches(false);
        L30:
            StoryViewer r07 = this.this$0;
            if (r07.allowIntercept == true) goto L33;
        L37:
            StoryViewer r08 = this.this$0;
            if (r08.allowIntercept == true) goto L40;
        L163:
            StoryViewer r09 = this.this$0;
            SelfStoryViewsView r46 = r09.selfStoryViewsView;
            if (r46 != null) goto L166;
        L168:
            boolean r1 = false;
        L170:
            if (r09.inSwipeToDissmissMode == true) goto L174;
            if (r1 == true) goto L174;
            r09.gestureDetector.onTouchEvent(r15);
        L174:
            if (this.this$0.inSwipeToDissmissMode == false) goto L176;
            return true;
        L176:
            if (super.onInterceptTouchEvent(r15) == false) goto L179;
            return true;
        L179:
            return false;
        L166:
            if (r46.progressToOpen != 1.0f) goto L168;
            r1 = true;
            goto L170
        L40:
            if (r08.keyboardVisible == true) goto L163;
            if (StoryViewer.m19785$$Nest$fgetisRecording(r08) == true) goto L163;
            if (StoryViewer.m19782$$Nest$fgetisInTextSelectionMode(this.this$0) == true) goto L163;
            AndroidUtilities.runOnUIThread(this.this$0.longPressRunnable, 400);
            goto L163
        L33:
            if (StoryViewer.m19785$$Nest$fgetisRecording(r07) == true) goto L37;
            if (StoryViewer.m19778$$Nest$fgetisCaptionPartVisible(this.this$0) == false) goto L37;
            StoryViewer.m19797$$Nest$fputdelayedTapRunnable(this.this$0, new StoryViewer$2$$ExternalSyntheticLambda0(this));
            AndroidUtilities.runOnUIThread(StoryViewer.m19771$$Nest$fgetdelayedTapRunnable(this.this$0), 150);
            goto L37
        L21:
            if (StoryViewer.m19778$$Nest$fgetisCaptionPartVisible(r04) == true) goto L23;
            r45 = true;
        L17:
            r44 = false;
        L11:
            r42 = false;
        L47:
            if (r15.getAction() != 2) goto L147;
            float r010 = Math.abs(this.startY - r15.getY());
            float r47 = Math.abs(this.startX - r15.getX());
            StoryViewer r6 = this.this$0;
            if (r6.isLongpressed == false) goto L85;
            if (r6.inSeekingMode == false) goto L85;
            if (StoryViewer.m19781$$Nest$fgetisInPinchToZoom(r6) == true) goto L85;
            StoryViewer r62 = this.this$0;
            if (r62.inSwipeToDissmissMode == true) goto L85;
            PeerStoriesView.VideoPlayerSharedScope r7 = r62.currentPlayerScope;
            if (r7 == null) goto L85;
            if (r7.player == null) goto L85;
            PeerStoriesView r63 = r62.storiesViewPager.getCurrentPeerView();
            if (r63 == null) goto L85;
            PeerStoriesView.StoryItemHolder r72 = r63.currentStory;
            if (r72 == null) goto L85;
            if (r72.uploadingStory != null) goto L85;
            if (r72.isVideo() == false) goto L85;
            long r73 = r63.videoDuration;
            if (r73 > 0) goto L78;
            TL_stories.StoryItem r11 = r63.currentStory.storyItem;
            if (r11 == null) goto L78;
            TLRPC.MessageMedia r112 = r11.media;
            if (r112 == null) goto L78;
            TLRPC.Document r113 = r112.document;
            if (r113 == null) goto L78;
            r73 = (long) (MessageObject.getDocumentDuration(r113) * 1000.0d);
        L78:
            if (r73 <= 0) goto L85;
            float r9 = r15.getX();
            VideoPlayerHolder r10 = this.this$0.currentPlayerScope.player;
            if (((int) (r10.seek((r9 - this.lastTouchX) / AndroidUtilities.dp(220.0f), r73) * 10.0f)) != ((int) (r10.currentSeek * 10.0f))) goto L181;
        L83:
            r63.storyContainer.invalidate();
            this.lastTouchX = r9;
            goto L85
        L181:
            r63.performHapticFeedback(9, 1);     // Catch: Exception -> L180
        L85:
            if (r010 <= r47) goto L93;
            StoryViewer r74 = this.this$0;
            if (r74.inSeekingMode == true) goto L93;
            if (r74.verticalScrollDetected == true) goto L93;
            if (r010 <= (AndroidUtilities.touchSlop * 2.0f)) goto L93;
            r74.verticalScrollDetected = true;
        L93:
            StoryViewer r75 = this.this$0;
            if (r75.inSwipeToDissmissMode == true) goto L163;
            if (r75.inSeekingMode == true) goto L163;
            if (r75.keyboardVisible == true) goto L163;
            if (r75.allowSwipeToDissmiss == false) goto L163;
            if (r010 > r47) goto L103;
        L145:
            StoryViewer.m19821$$Nest$mlayoutAndFindView(this.this$0);
            goto L163
        L103:
            if (r010 <= (AndroidUtilities.touchSlop * 2.0f)) goto L145;
            r75.inSwipeToDissmissMode = true;
            PeerStoriesView r011 = r75.storiesViewPager.getCurrentPeerView();
            if (r011 == null) goto L107;
            r011.cancelTextSelection();
        L107:
            if (r011 != null) goto L109;
        L111:
            boolean r48 = false;
        L112:
            StoryViewer r64 = this.this$0;
            if (r48 == true) goto L122;
            if (r011 == null) goto L122;
            if (r011.isChannel == true) goto L122;
            if (r011.isPremiumBlocked == true) goto L122;
            if (StoryViewer.m19791$$Nest$fgetstoriesIntro(r64) != null) goto L122;
            boolean r76 = true;
        L123:
            r64.allowSwipeToReply = r76;
            StoryViewer r65 = this.this$0;
            if (r48 == true) goto L126;
        L132:
            boolean r012 = false;
        L133:
            r65.allowSelfStoriesView = r012;
            StoryViewer r013 = this.this$0;
            if (r013.allowSelfStoriesView == false) goto L139;
            if (this.keyboardHeight == 0) goto L139;
            r013.allowSelfStoriesView = false;
        L139:
            if (r013.allowSelfStoriesView == false) goto L141;
            r013.checkSelfStoriesView();
        L141:
            StoryViewer r014 = this.this$0;
            r014.swipeToReplyOffset = 0.0f;
            if (StoryViewer.m19771$$Nest$fgetdelayedTapRunnable(r014) == null) goto L144;
            AndroidUtilities.cancelRunOnUIThread(StoryViewer.m19771$$Nest$fgetdelayedTapRunnable(this.this$0));
            StoryViewer.m19771$$Nest$fgetdelayedTapRunnable(this.this$0).run();
            StoryViewer.m19797$$Nest$fputdelayedTapRunnable(this.this$0, null);
        L144:
            AndroidUtilities.cancelRunOnUIThread(this.this$0.longPressRunnable);
            goto L145
        L126:
            if (r011.unsupported == true) goto L132;
            if (r011.currentStory.storyItem == null) goto L132;
            if (StoryViewer.m19791$$Nest$fgetstoriesIntro(r65) != null) goto L132;
            r012 = true;
        L122:
            r76 = false;
            goto L123
        L109:
            if (r011.viewsAllowed() == false) goto L111;
            r48 = true;
            goto L112
        L147:
            if (r15.getAction() != 1) goto L149;
        L150:
            AndroidUtilities.cancelRunOnUIThread(this.this$0.longPressRunnable);
            if (StoryViewer.m19771$$Nest$fgetdelayedTapRunnable(this.this$0) == null) goto L153;
            AndroidUtilities.cancelRunOnUIThread(StoryViewer.m19771$$Nest$fgetdelayedTapRunnable(this.this$0));
            StoryViewer.m19797$$Nest$fputdelayedTapRunnable(this.this$0, null);
        L153:
            StoryViewer.m19823$$Nest$msetInTouchMode(this.this$0, false);
            StoryViewer r015 = this.this$0;
            r015.verticalScrollDetected = false;
            r015.inSeekingMode = false;
            PeerStoriesView.VideoPlayerSharedScope r016 = r015.currentPlayerScope;
            if (r016 == null) goto L158;
            VideoPlayerHolder r017 = r016.player;
            if (r017 == null) goto L158;
            r017.setSeeking(false);
        L158:
            PeerStoriesView r018 = this.this$0.getCurrentPeerView();
            if (r018 == null) goto L163;
            LiveCommentsView r019 = r018.liveCommentsView;
            if (r019 == null) goto L163;
            r019.setAllowTouches(true);
            goto L163
        L149:
            if (r15.getAction() != 3) goto L163;
            goto L150
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) StoryViewer.m19794$$Nest$fgetvolumeControl(this.this$0).getLayoutParams();
            r0.topMargin = AndroidUtilities.statusBarHeight - AndroidUtilities.dp(2.0f);
            ViewGroup.LayoutParams r02 = StoryViewer.m19794$$Nest$fgetvolumeControl(this.this$0).getLayoutParams();
            r02.height = AndroidUtilities.dp(2.0f);
            super.onMeasure(r5, r6);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r7) {
            if (r7.getAction() != 1) goto L5;
        L6:
            StoryViewer r0 = this.this$0;
            r0.inSwipeToDissmissMode = false;
            StoryViewer.m19823$$Nest$msetInTouchMode(r0, false);
            StoryViewer r02 = this.this$0;
            if (r02.progressToDismiss < 1.0f) goto L10;
            r02.close(true);
        L12:
            PeerStoriesView r03 = this.this$0.getCurrentPeerView();
            if (r03 == null) goto L17;
            LiveCommentsView r04 = r03.liveCommentsView;
            if (r04 == null) goto L17;
            r04.setAllowTouches(true);
        L17:
            StoryViewer r05 = this.this$0;
            if (r05.inSwipeToDissmissMode == false) goto L20;
        L33:
            this.this$0.gestureDetector.onTouchEvent(r7);
            return true;
        L20:
            if (r05.keyboardVisible == true) goto L33;
            if (r05.swipeToReplyOffset != 0.0f) goto L33;
            if (r05.selfStoriesViewsOffset == 0.0f) goto L30;
            if (r05.allowIntercept == true) goto L33;
            if (r05.verticalScrollDetected == true) goto L33;
        L30:
            if (StoryViewer.m19782$$Nest$fgetisInTextSelectionMode(r05) == true) goto L33;
            return false;
        L10:
            if (StoryViewer.m19779$$Nest$fgetisClosed(r02) == true) goto L12;
            StoryViewer r06 = this.this$0;
            r06.swipeToDissmissBackAnimator = ValueAnimator.ofFloat(new float[]{r06.swipeToDismissOffset, 0.0f});
            this.this$0.swipeToDissmissBackAnimator.addUpdateListener(new StoryViewer$2$$ExternalSyntheticLambda2(this));
            this.this$0.swipeToDissmissBackAnimator.addListener(new C00682(this));
            this.this$0.swipeToDissmissBackAnimator.setDuration(150);
            this.this$0.swipeToDissmissBackAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
            this.this$0.swipeToDissmissBackAnimator.start();
            goto L12
        L5:
            if (r7.getAction() != 3) goto L17;
            goto L6
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void requestDisallowInterceptTouchEvent(boolean r1) {
            super.requestDisallowInterceptTouchEvent(r1);
            this.this$0.allowIntercept = false;
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoryViewer$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends HwFrameLayout {
        final /* synthetic */ StoryViewer this$0;

        public AnonymousClass3(StoryViewer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            PeerStoriesView r0 = this.this$0.storiesViewPager.getCurrentPeerView();
            StoryViewer r1 = this.this$0;
            SelfStoryViewsView r2 = r1.selfStoryViewsView;
            if (r2 == null) goto L17;
            if (r0 == null) goto L17;
            r2.setOffset(r1.selfStoriesViewsOffset);
            StoryViewer r12 = this.this$0;
            if (r12.selfStoryViewsView.progressToOpen != 1.0f) goto L8;
            r12.storiesViewPager.setVisibility(4);
        L9:
            this.this$0.storiesViewPager.checkPageVisibility();
            float r13 = r0.getTop() + r0.storyContainer.getTop();
            float r22 = this.this$0.selfStoryViewsView.progressToOpen;
            getMeasuredHeight();
            float r4 = this.this$0.selfStoriesViewsOffset;
            getMeasuredHeight();
            if (r0.storyContainer.getMeasuredHeight() <= 0) goto L12;
            StoryViewer.m19813$$Nest$fputlastStoryContainerHeight(this.this$0, r0.storyContainer.getMeasuredHeight());
        L12:
            StoryViewer r42 = this.this$0;
            float r3 = AndroidUtilities.lerp(1.0f, r42.selfStoryViewsView.toHeight / StoryViewer.m19786$$Nest$fgetlastStoryContainerHeight(r42), r22);
            this.this$0.storiesViewPager.setPivotY(r13);
            this.this$0.storiesViewPager.setPivotX(getMeasuredWidth() / 2.0f);
            this.this$0.storiesViewPager.setScaleX(r3);
            this.this$0.storiesViewPager.setScaleY(r3);
            r0.forceUpdateOffsets = true;
            StoryViewer r43 = this.this$0;
            if (r43.selfStoriesViewsOffset != 0.0f) goto L15;
            r0.setViewsThumbImageReceiver(0.0f, 0.0f, 0.0f, null);
        L16:
            r0.invalidate();
            r0.outlineProvider.radiusInDp = (int) AndroidUtilities.lerp(10.0f, 6.0f / r5, this.this$0.selfStoryViewsView.progressToOpen);
            r0.storyContainer.invalidateOutline();
            StoryViewer r32 = this.this$0;
            r32.storiesViewPager.setTranslationY((r32.selfStoryViewsView.toY - r13) * r22);
            goto L17
        L15:
            r0.setViewsThumbImageReceiver(r22, r3, r13, r43.selfStoryViewsView.getCrossfadeToImage());
            goto L16
        L8:
            r12.storiesViewPager.setVisibility(0);
        L17:
            if (r0 == null) goto L19;
            StoryViewer.m19794$$Nest$fgetvolumeControl(this.this$0).setTranslationY(((r0.getY() + r0.storyContainer.getY()) - StoryViewer.m19794$$Nest$fgetvolumeControl(this.this$0).getTop()) - AndroidUtilities.dp(4.0f));
        L19:
            super.dispatchDraw(r9);
        }

        public int measureKeyboardHeight() {
            View r0 = getRootView();
            Rect r1 = AndroidUtilities.rectTmp2;
            getWindowVisibleDisplayFrame(r1);
            if (r1.bottom == 0) goto L5;
        L7:
            int r4 = r0.getHeight();
            if (r1.top == 0) goto L10;
            int r3 = AndroidUtilities.statusBarHeight;
        L12:
            return Math.max(0, ((r4 - r3) - AndroidUtilities.getViewInset(r0)) - (r1.bottom - r1.top));
        L10:
            r3 = 0;
            goto L12
        L5:
            if (r1.top != 0) goto L7;
            return 0;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r7, int r8) {
            int r0 = View.MeasureSpec.getSize(r8);
            StoryViewer r1 = this.this$0;
            if (r1.ATTACH_TO_FRAGMENT == true) goto L5;
        L6:
            r1.setKeyboardHeightFromParent(measureKeyboardHeight());
            r0 = r0 + StoryViewer.m19789$$Nest$fgetrealKeyboardHeight(this.this$0);
        L7:
            int r12 = View.MeasureSpec.getSize(r7);
            int r2 = (int) ((r12 * 16.0f) / 9.0f);
            StoryViewer r5 = this.this$0;
            if (r0 <= r2) goto L10;
            r5.storiesViewPager.getLayoutParams().width = -1;
            r0 = r2;
        L11:
            this.this$0.aspectRatioFrameLayout.getLayoutParams().height = r0 + 1;
            this.this$0.aspectRatioFrameLayout.getLayoutParams().width = r12;
            ((FrameLayout.LayoutParams) this.this$0.aspectRatioFrameLayout.getLayoutParams()).topMargin = AndroidUtilities.statusBarHeight;
            super.onMeasure(r7, r8);
            return;
        L10:
            int r22 = (int) ((r0 / 16.0f) * 9.0f);
            r5.storiesViewPager.getLayoutParams().width = r22;
            r12 = r22;
            goto L11
        L5:
            if (r1.ATTACHED_FRAGMENT_IS_EDGE_TO_EDGE == false) goto L7;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoryViewer$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends HwStoriesViewPager {
        final /* synthetic */ StoryViewer this$0;

        public AnonymousClass4(StoryViewer r1, int r2, Context r3, StoryViewer r4, Theme.ResourcesProvider r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Stories.StoriesViewPager
        public void onStateChanged() {
            StoryViewer r2 = this.this$0;
            if (r2.storiesViewPager.currentState != 1) goto L6;
            AndroidUtilities.cancelRunOnUIThread(r2.longPressRunnable);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoryViewer$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 implements PeerStoriesView.Delegate {
        final /* synthetic */ StoryViewer this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ ArrayList val$peerIds;
        final /* synthetic */ StoriesController.StoriesList val$storiesList;

        public static /* synthetic */ void $r8$lambda$ChnW69nh0znF3RiGn2r_AWeREUs(AnonymousClass5 r0, ArrayList r1, int r2) {
            r0.lambda$switchToNextAndRemoveCurrentPeer$1(r1, r2);
        }

        /* renamed from: $r8$lambda$EQxtl-BD3jLezUG0ewZVwjDSR78, reason: not valid java name */
        public static /* synthetic */ void m19833$r8$lambda$EQxtlBD3jLezUG0ewZVwjDSR78(AnonymousClass5 r0, VideoPlayerHolder r1) {
            r0.lambda$preparePlayer$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$boYW5ZKsINk_jnV4fOt14jnKltk(AnonymousClass5 r0, StoriesController.StoriesList r1, ArrayList r2) {
            r0.lambda$switchToNextAndRemoveCurrentPeer$0(r1, r2);
        }

        public AnonymousClass5(StoryViewer r1, StoriesController.StoriesList r2, ArrayList r3, Context r4) {
            this.this$0 = r1;
            this.val$storiesList = r2;
            this.val$peerIds = r3;
            this.val$context = r4;
        }

        private /* synthetic */ void lambda$preparePlayer$2(VideoPlayerHolder r2) {
            PeerStoriesView r0 = this.this$0.storiesViewPager.getCurrentPeerView();
            if (r0 == null) goto L12;
            FrameLayout r02 = r0.storyContainer;
            if (r02 == null) goto L13;
            PeerStoriesView.VideoPlayerSharedScope r1 = this.this$0.currentPlayerScope;
            if (r1 != null) goto L9;
            return;
        L9:
            if (r1.player != r2) goto L15;
            r02.invalidate();
            return;
        L15:
            return;
        L13:
            return;
        }

        private /* synthetic */ void lambda$switchToNextAndRemoveCurrentPeer$0(StoriesController.StoriesList r4, ArrayList r5) {
            StoryViewer r3 = this.this$0;
            r3.storiesViewPager.setDays(r4.dialogId, r5, r3.currentAccount);
        }

        private /* synthetic */ void lambda$switchToNextAndRemoveCurrentPeer$1(ArrayList r2, int r3) {
            StoryViewer r1 = this.this$0;
            r1.storiesViewPager.setPeerIds(r2, r1.currentAccount, r3);
        }

        private void switchToLive(boolean r5, boolean r6) {
            LivePlayerView r0 = this.this$0.liveView;
            int r1 = 8;
            if (r0 == null) goto L9;
            if (r5 == false) goto L6;
            int r3 = 0;
        L7:
            r0.setVisibility(r3);
            goto L9
        L6:
            r3 = 8;
        L9:
            if (StoryViewer.m19792$$Nest$fgetsurfaceView(this.this$0) == null) goto L18;
            SurfaceView r02 = StoryViewer.m19792$$Nest$fgetsurfaceView(this.this$0);
            if (r5 == false) goto L13;
            int r62 = 8;
        L16:
            r02.setVisibility(r62);
            goto L18
        L13:
            if (r6 == false) goto L15;
            r62 = 0;
            goto L16
        L15:
            r62 = 4;
        L18:
            if (StoryViewer.m19793$$Nest$fgettextureView(this.this$0) == null) goto L25;
            TextureView r4 = StoryViewer.m19793$$Nest$fgettextureView(this.this$0);
            if (r5 == true) goto L23;
            r1 = 0;
        L23:
            r4.setVisibility(r1);
            return;
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public int getKeyboardHeight() {
            return StoryViewer.m19789$$Nest$fgetrealKeyboardHeight(this.this$0);
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public float getProgressToDismiss() {
            return this.this$0.progressToDismiss;
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public boolean isClosed() {
            return StoryViewer.m19779$$Nest$fgetisClosed(this.this$0);
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void onPeerSelected(long r3, int r5) {
            StoryViewer r2 = this.this$0;
            if (r2.lastPosition == r5) goto L5;
        L8:
            r2.lastDialogId = r3;
            r2.lastPosition = r5;
            return;
        L5:
            if (r2.lastDialogId != r3) goto L8;
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void preparePlayer(ArrayList<TLRPC.Document> r8, ArrayList<Uri> r9) {
            if (SharedConfig.deviceIsHigh() == true) goto L5;
            return;
        L5:
            if (SharedConfig.allowPreparingHevcPlayers() == true) goto L8;
            return;
        L8:
            if (StoryViewer.m19779$$Nest$fgetisClosed(this.this$0) == true) goto L37;
            int r1 = 0;
        L12:
            if (r1 >= this.this$0.preparedPlayers.size()) goto L21;
            int r2 = 0;
        L15:
            if (r2 >= r9.size()) goto L20;
            if (r9.get(r2).equals(this.this$0.preparedPlayers.get(r1).uri) == false) goto L19;
            r9.remove(r2);
        L19:
            r2 = r2 + 1;
            goto L15
        L20:
            r1 = r1 + 1;
            goto L12
        L21:
            int r12 = 0;
        L23:
            if (r12 >= r9.size()) goto L38;
            Uri r22 = r9.get(r12);
            StoryViewer r4 = this.this$0;
            VideoPlayerHolder r3 = new VideoPlayerHolder(r4, StoryViewer.m19792$$Nest$fgetsurfaceView(r4), StoryViewer.m19793$$Nest$fgettextureView(this.this$0));
            r3.setOnSeekUpdate(new StoryViewer$5$$ExternalSyntheticLambda0(this, r3));
            r3.uri = r22;
            TLRPC.Document r42 = r8.get(r12);
            r3.document = r42;
            FileStreamLoadOperation.setPriorityForDocument(r42, 0);
            r3.preparePlayer(r22, StoryViewer.m19829$$Nest$sfgetisInSilentMode(), StoryViewer.currentSpeed);
            this.this$0.preparedPlayers.add(r3);
            if (this.this$0.preparedPlayers.size() <= 2) goto L27;
            this.this$0.preparedPlayers.remove(0).release(null);
        L27:
            r12 = r12 + 1;
            goto L23
        L38:
            return;
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public boolean releasePlayer(Runnable r2) {
            VideoPlayerHolder r0 = this.this$0.playerHolder;
            if (r0 == null) goto L6;
            boolean r22 = r0.release(r2);
            this.this$0.playerHolder = null;
            return r22;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void requestAdjust(boolean r1) {
            StoryViewer.m19822$$Nest$mrequestAdjust(this.this$0, r1);
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void requestPlayer(TLRPC.Document r23, Uri r24, long r25, PeerStoriesView.VideoPlayerSharedScope r27) {
            if (StoryViewer.m19779$$Nest$fgetisClosed(this.this$0) == true) goto L64;
            StoryViewer r2 = this.this$0;
            if (r2.progressToOpen < 0.9f) goto L64;
            Uri r22 = r2.lastUri;
            if (r22 != null) goto L10;
            String r26 = null;
        L11:
            if (r24 != null) goto L13;
            String r5 = null;
        L14:
            boolean r12 = TextUtils.equals(r26, r5);
            boolean r13 = true;
            if (r12 == false) goto L21;
            StoryViewer r28 = this.this$0;
            VideoPlayerHolder r52 = r28.playerHolder;
            if (r52 == null) goto L21;
            if (r12 == false) goto L56;
            r28.currentPlayerScope = r27;
            r27.player = r52;
            r27.livePlayer = null;
            r52.setSpeed(StoryViewer.currentSpeed);
            StoryViewer r1 = this.this$0;
            PeerStoriesView.VideoPlayerSharedScope r29 = r1.currentPlayerScope;
            r29.firstFrameRendered = r1.playerHolder.firstFrameRendered;
            r29.renderView = r1.aspectRatioFrameLayout;
            r29.textureView = StoryViewer.m19793$$Nest$fgettextureView(r1);
            StoryViewer r14 = this.this$0;
            r14.currentPlayerScope.surfaceView = StoryViewer.m19792$$Nest$fgetsurfaceView(r14);
            FileLog.d("StoryViewer requestPlayer: same url");
        L56:
            if (r24 != null) goto L59;
            r13 = false;
        L59:
            switchToLive(false, r13);
            StoryViewer.m19814$$Nest$fputplayerSavedPosition(this.this$0, 0);
            if (r12 == true) goto L62;
            StoryViewer.m19796$$Nest$fputaudioFocusPaused(this.this$0, false);
        L62:
            this.this$0.updatePlayingMode();
            return;
        L21:
            StoryViewer r210 = this.this$0;
            r210.lastUri = r24;
            LivePlayerView r211 = r210.liveView;
            if (r211 == null) goto L24;
            r211.setScope(0, null);
        L24:
            LivePlayer r212 = this.this$0.livePlayer;
            if (r212 != null) goto L27;
        L31:
            VideoPlayerHolder r213 = this.this$0.playerHolder;
            if (r213 == null) goto L34;
            r213.release(null);
            this.this$0.playerHolder = null;
        L34:
            PeerStoriesView.VideoPlayerSharedScope r214 = this.this$0.currentPlayerScope;
            if (r214 == null) goto L37;
            r214.player = null;
            r214.livePlayer = null;
            r214.firstFrameRendered = false;
            r214.renderView = null;
            r214.textureView = null;
            r214.surfaceView = null;
            r214.invalidate();
            this.this$0.currentPlayerScope = null;
        L37:
            if (r24 == null) goto L55;
            this.this$0.currentPlayerScope = r27;
            int r15 = 0;
        L40:
            if (r15 >= this.this$0.preparedPlayers.size()) goto L45;
            if (this.this$0.preparedPlayers.get(r15).uri.equals(r24) == true) goto L43;
            r15 = r15 + 1;
            goto L40
        L43:
            StoryViewer r215 = this.this$0;
            r215.playerHolder = r215.preparedPlayers.remove(r15);
        L45:
            StoryViewer r16 = this.this$0;
            if (r16.playerHolder != null) goto L48;
            r16.playerHolder = new VideoPlayerHolder(r16, StoryViewer.m19792$$Nest$fgetsurfaceView(r16), StoryViewer.m19793$$Nest$fgettextureView(this.this$0));
            this.this$0.playerHolder.document = r23;
        L48:
            VideoPlayerHolder r17 = this.this$0.playerHolder;
            r17.uri = r24;
            r17.setSpeed(StoryViewer.currentSpeed);
            StoryViewer r18 = this.this$0;
            PeerStoriesView.VideoPlayerSharedScope r216 = r18.currentPlayerScope;
            r216.player = r18.playerHolder;
            r216.firstFrameRendered = false;
            r216.renderView = r18.aspectRatioFrameLayout;
            r216.textureView = StoryViewer.m19793$$Nest$fgettextureView(r18);
            StoryViewer r19 = this.this$0;
            r19.currentPlayerScope.surfaceView = StoryViewer.m19792$$Nest$fgetsurfaceView(r19);
            StoryViewer r110 = this.this$0;
            r110.currentPlayerScope.livePlayer = null;
            FileStreamLoadOperation.setPriorityForDocument(r110.playerHolder.document, 3);
            FileLoader.getInstance(this.this$0.currentAccount).changePriority(3, this.this$0.playerHolder.document, null, null, null, null, null);
            if (r25 == 0) goto L51;
        L53:
            long r53 = r25;
        L54:
            FileLog.d("StoryViewer requestPlayer: currentPlayerScope.player start " + r24);
            StoryViewer r111 = this.this$0;
            r111.currentPlayerScope.player.start(false, r111.isPaused(), r24, r53, StoryViewer.m19829$$Nest$sfgetisInSilentMode(), StoryViewer.currentSpeed);
            this.this$0.currentPlayerScope.invalidate();
            goto L56
        L51:
            if (StoryViewer.m19788$$Nest$fgetplayerSavedPosition(this.this$0) == 0) goto L53;
            long r112 = StoryViewer.m19788$$Nest$fgetplayerSavedPosition(this.this$0);
            this.this$0.currentPlayerScope.firstFrameRendered = true;
            r53 = r112;
            goto L54
        L55:
            FileLog.d("StoryViewer requestPlayer: url is null (1)");
            goto L56
        L27:
            if (r212.outgoing == false) goto L29;
            r212.setDisplaySink(null);
        L30:
            this.this$0.livePlayer = null;
            goto L31
        L29:
            r212.destroy();
            goto L30
        L13:
            r5 = r24.toString();
            goto L14
        L10:
            r26 = r22.toString();
        L64:
            LivePlayerView r217 = this.this$0.liveView;
            if (r217 == null) goto L67;
            r217.setScope(0, null);
        L67:
            LivePlayer r218 = this.this$0.livePlayer;
            if (r218 != null) goto L70;
        L74:
            FileLog.d("StoryViewer requestPlayer ignored, because closed: " + StoryViewer.m19779$$Nest$fgetisClosed(this.this$0) + ", " + this.this$0.progressToOpen);
            r27.firstFrameRendered = false;
            r27.player = null;
            r27.livePlayer = null;
            return;
        L70:
            if (r218.outgoing == false) goto L72;
            r218.setDisplaySink(null);
        L73:
            this.this$0.livePlayer = null;
            goto L74
        L72:
            r218.destroy();
            goto L73
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void requestPlayer(TL_stories.StoryItem r13, long r14, int r16, boolean r17, TLRPC.InputGroupCall r18, PeerStoriesView.VideoPlayerSharedScope r19) {
            switchToLive(true, true);
            LivePlayer r0 = this.this$0.livePlayer;
            if (r0 != null) goto L5;
        L8:
            LivePlayerView r02 = this.this$0.liveView;
            if (r02 == null) goto L12;
            r02.setScope(r14, null);
            this.this$0.liveView.reset();
        L12:
            if (LiveStoryPipOverlay.isVisible() == true) goto L14;
        L18:
            LivePlayer r03 = this.this$0.livePlayer;
            if (r03 != null) goto L21;
        L32:
            if (LiveStoryPipOverlay.isVisible() == false) goto L34;
            LiveStoryPipOverlay.dismiss();
        L34:
            VideoPlayerHolder r04 = this.this$0.playerHolder;
            if (r04 == null) goto L37;
            r04.release(null);
            this.this$0.playerHolder = null;
        L37:
            PeerStoriesView.VideoPlayerSharedScope r05 = this.this$0.currentPlayerScope;
            if (r05 == null) goto L41;
            r05.player = null;
            r05.livePlayer = null;
            r05.firstFrameRendered = false;
            r05.renderView = null;
            r05.textureView = null;
            r05.surfaceView = null;
            r05.invalidate();
            this.this$0.currentPlayerScope = null;
        L41:
            if (this.this$0.livePlayer != null) goto L48;
            LivePlayer r06 = LivePlayer.recording;
            if (r06 != null) goto L45;
        L47:
            StoryViewer r11 = this.this$0;
            r11.livePlayer = new LivePlayer(this.val$context, r11.currentAccount, r13, r14, r16, r17, r18);
            goto L48
        L45:
            if (r06.equals(r18) == false) goto L47;
            StoryViewer r132 = this.this$0;
            r132.livePlayer = LivePlayer.recording;
        L48:
            StoryViewer r133 = this.this$0;
            LivePlayerView r07 = r133.pipLiveView;
            if (r07 == null) goto L51;
            r133.livePlayer.setDisplaySink(r07.getSink());
        L52:
            StoryViewer r134 = this.this$0;
            r134.currentPlayerScope = r19;
            r19.firstFrameRendered = false;
            r19.renderView = r134.aspectRatioFrameLayout;
            LivePlayerView r08 = r134.liveView;
            r19.textureView = r08.textureView;
            r19.surfaceView = r08.surfaceView;
            r19.livePlayer = r134.livePlayer;
            r08.setScope(r14, r19);
            this.this$0.currentPlayerScope.invalidate();
            return;
        L51:
            r133.livePlayer.setDisplaySink(r133.liveView.getSink());
            goto L52
        L21:
            if (r03.outgoing == true) goto L27;
            if (LiveStoryPipOverlay.isVisible(r03) == true) goto L27;
            this.this$0.livePlayer.destroy();
        L30:
            this.this$0.livePlayer = null;
        L27:
            if (this.this$0.livePlayer.getDisplaySink() == this.this$0.liveView.getSink()) goto L54;
            this.this$0.livePlayer.setDisplaySink(null);
            goto L30
        L54:
            return;
        L14:
            if (LiveStoryPipOverlay.getLivePlayer() == null) goto L18;
            if (LiveStoryPipOverlay.getLivePlayer().equals(r18) == false) goto L18;
            StoryViewer r09 = this.this$0;
            r09.livePlayer = LiveStoryPipOverlay.takeLivePlayer();
            LiveStoryPipOverlay.dismiss(false);
            goto L32
        L5:
            if (r0.dialogId != r14) goto L8;
            if (r0.equals(r18) == false) goto L8;
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setAllowTouchesByViewPager(boolean r2) {
            StoryViewer r22 = this.this$0;
            StoryViewer.m19795$$Nest$fputallowTouchesByViewpager(r22, StoryViewer.m19769$$Nest$fgetallowTouchesByViewpager(r22));
            this.this$0.updatePlayingMode();
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setBulletinIsVisible(boolean r2) {
            StoryViewer.m19802$$Nest$fputisBulletinVisible(this.this$0, r2);
            this.this$0.updatePlayingMode();
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setHideEnterViewProgress(float r2) {
            if (StoryViewer.m19775$$Nest$fgethideEnterViewProgress(this.this$0) == r2) goto L6;
            StoryViewer.m19800$$Nest$fputhideEnterViewProgress(this.this$0, r2);
            this.this$0.containerView.invalidate();
            return;
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setIsCaption(boolean r2) {
            StoryViewer.m19803$$Nest$fputisCaption(this.this$0, r2);
            this.this$0.updatePlayingMode();
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setIsCaptionPartVisible(boolean r1) {
            StoryViewer.m19804$$Nest$fputisCaptionPartVisible(this.this$0, r1);
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setIsHintVisible(boolean r2) {
            StoryViewer.m19805$$Nest$fputisHintVisible(this.this$0, r2);
            this.this$0.updatePlayingMode();
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setIsInPinchToZoom(boolean r3) {
            if (StoryViewer.m19781$$Nest$fgetisInPinchToZoom(this.this$0) == true) goto L15;
            if (r3 == false) goto L15;
            StoryViewer r0 = this.this$0;
            if (r0.inSeekingMode == false) goto L15;
            r0.inSeekingMode = false;
            PeerStoriesView.VideoPlayerSharedScope r02 = r0.currentPlayerScope;
            if (r02 == null) goto L12;
            VideoPlayerHolder r03 = r02.player;
            if (r03 == null) goto L12;
            r03.setSeeking(false);
        L12:
            PeerStoriesView r04 = this.this$0.getCurrentPeerView();
            if (r04 == null) goto L15;
            r04.invalidate();
        L15:
            StoryViewer.m19806$$Nest$fputisInPinchToZoom(this.this$0, r3);
            this.this$0.updatePlayingMode();
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setIsInSelectionMode(boolean r2) {
            StoryViewer.m19807$$Nest$fputisInTextSelectionMode(this.this$0, r2);
            this.this$0.updatePlayingMode();
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setIsLikesReaction(boolean r2) {
            StoryViewer.m19808$$Nest$fputisLikesReactions(this.this$0, r2);
            this.this$0.updatePlayingMode();
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setIsRecording(boolean r2) {
            StoryViewer.m19810$$Nest$fputisRecording(this.this$0, r2);
            this.this$0.updatePlayingMode();
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setIsSwiping(boolean r2) {
            StoryViewer.m19811$$Nest$fputisSwiping(this.this$0, r2);
            this.this$0.updatePlayingMode();
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setIsWaiting(boolean r2) {
            StoryViewer.m19812$$Nest$fputisWaiting(this.this$0, r2);
            this.this$0.updatePlayingMode();
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setKeyboardVisible(boolean r2) {
            StoryViewer r1 = this.this$0;
            if (r1.keyboardVisible == r2) goto L6;
            r1.keyboardVisible = r2;
            r1.updatePlayingMode();
            return;
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setPopupIsVisible(boolean r2) {
            StoryViewer.m19809$$Nest$fputisPopupVisible(this.this$0, r2);
            this.this$0.updatePlayingMode();
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void setTranslating(boolean r1) {
            StoryViewer r0 = this.this$0;
            r0.isTranslating = r1;
            r0.updatePlayingMode();
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void shouldSwitchToNext() {
            if (this.this$0.storiesViewPager.getCurrentPeerView().switchToNext(true) == false) goto L5;
            return;
        L5:
            if (this.this$0.storiesViewPager.switchToNext(true) == true) goto L9;
            this.this$0.close(true);
            return;
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void showDialog(Dialog r1) {
            this.this$0.showDialog(r1);
        }

        @Override // org.telegram.ui.Stories.PeerStoriesView.Delegate
        public void switchToNextAndRemoveCurrentPeer() {
            if (this.val$storiesList != null) goto L5;
            ArrayList r0 = new ArrayList(this.val$peerIds);
            int r3 = r0.indexOf(Long.valueOf(this.this$0.storiesViewPager.getCurrentPeerView().getCurrentPeer()));
            if (r3 < 0) goto L28;
            r0.remove(r3);
            boolean r1 = this.this$0.storiesViewPager.switchToNext(true);
            StoryViewer r4 = this.this$0;
            if (r1 == true) goto L26;
            r4.close(false);
            return;
        L26:
            r4.storiesViewPager.onNextIdle(new StoryViewer$5$$ExternalSyntheticLambda2(this, r0, r3));
            return;
        L28:
            this.this$0.close(false);
            return;
        L5:
            if (this.this$0.storiesViewPager.days != null) goto L7;
            return;
        L7:
            ArrayList r02 = new ArrayList(this.this$0.storiesViewPager.days);
            if (this.this$0.storiesViewPager.getCurrentPeerView() != null) goto L10;
            int r32 = -1;
        L11:
            if (r32 < 0) goto L18;
            r02.remove(r32);
            boolean r12 = this.this$0.storiesViewPager.switchToNext(true);
            StoryViewer r33 = this.this$0;
            if (r12 == true) goto L16;
            r33.close(false);
            return;
        L16:
            r33.storiesViewPager.onNextIdle(new StoryViewer$5$$ExternalSyntheticLambda1(this, this.val$storiesList, r02));
            return;
        L18:
            this.this$0.close(false);
            return;
        L10:
            r32 = r02.indexOf(this.this$0.storiesViewPager.getCurrentPeerView().getCurrentDay());
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoryViewer$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends HwTextureView {
        final /* synthetic */ StoryViewer this$0;

        public AnonymousClass6(StoryViewer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Stories.HwTextureView, android.view.View
        public void invalidate() {
            super.invalidate();
            PeerStoriesView.VideoPlayerSharedScope r0 = this.this$0.currentPlayerScope;
            if (r0 == null) goto L6;
            r0.invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoryViewer$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ StoryViewer this$0;
        final /* synthetic */ boolean val$open;

        public AnonymousClass7(StoryViewer r1, boolean r2) {
            this.this$0 = r1;
            this.val$open = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.locker.unlock();
            StoryViewer r22 = this.this$0;
            if (this.val$open == false) goto L5;
            float r0 = r22.selfStoryViewsView.maxSelfStoriesViewsOffset;
        L6:
            r22.selfStoriesViewsOffset = r0;
            PeerStoriesView r23 = r22.storiesViewPager.getCurrentPeerView();
            if (r23 == null) goto L9;
            r23.invalidate();
        L9:
            this.this$0.containerView.invalidate();
            this.this$0.swipeToViewsAnimator = null;
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoryViewer$8, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ StoryViewer this$0;

        public AnonymousClass8(StoryViewer r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            StoryViewer r1 = this.this$0;
            PeerStoriesView r22 = null;
            r1.swipeToReplyBackAnimator = null;
            r1.swipeToReplyOffset = 0.0f;
            r1.swipeToReplyProgress = 0.0f;
            StoriesViewPager r12 = r1.storiesViewPager;
            if (r12 == null) goto L6;
            r22 = r12.getCurrentPeerView();
        L6:
            if (r22 == null) goto L9;
            r22.invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.StoryViewer$9, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ StoryViewer this$0;

        /* renamed from: org.telegram.ui.Stories.StoryViewer$9$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass9 this$1;

            public AnonymousClass1(AnonymousClass9 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                super.onAnimationEnd(r2);
                if (StoryViewer.m19791$$Nest$fgetstoriesIntro(this.this$1.this$0) == null) goto L5;
                StoryViewer.m19791$$Nest$fgetstoriesIntro(this.this$1.this$0).stopAnimation();
                StoryViewer r22 = this.this$1.this$0;
                r22.containerView.removeView(StoryViewer.m19791$$Nest$fgetstoriesIntro(r22));
            L5:
                StoryViewer.m19816$$Nest$fputstoriesIntro(this.this$1.this$0, null);
                this.this$1.this$0.updatePlayingMode();
            }
        }

        /* renamed from: org.telegram.ui.Stories.StoryViewer$9$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass9 this$1;

            public AnonymousClass2(AnonymousClass9 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                super.onAnimationEnd(r1);
                if (StoryViewer.m19791$$Nest$fgetstoriesIntro(this.this$1.this$0) == null) goto L6;
                StoryViewer.m19791$$Nest$fgetstoriesIntro(this.this$1.this$0).startAnimation(true);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$AOJtHDpRYn0agvf8bfl8W8pYmgU(AnonymousClass9 r0, View r1) {
            r0.lambda$onAnimationEnd$0(r1);
        }

        public AnonymousClass9(StoryViewer r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0(View r3) {
            StoryViewer.m19791$$Nest$fgetstoriesIntro(this.this$0).animate().alpha(0.0f).setDuration(150).setListener(new AnonymousClass1(this)).start();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r6) {
            StoryViewer r62 = this.this$0;
            r62.progressToOpen = 1.0f;
            r62.checkNavBarColor();
            StoryViewer.animationInProgress = false;
            HwFrameLayout r1 = this.this$0.containerView;
            if (r1 == null) goto L5;
            r1.disableHwAcceleration();
        L5:
            SizeNotifierFrameLayout r12 = this.this$0.windowView;
            if (r12 == null) goto L8;
            r12.invalidate();
        L8:
            StoryViewer r13 = this.this$0;
            ImageReceiver r2 = r13.transitionViewHolder.avatarImage;
            if (r2 != null) goto L11;
        L13:
            StoryViewer r14 = this.this$0;
            ImageReceiver r22 = r14.transitionViewHolder.storyImage;
            if (r22 != null) goto L16;
        L18:
            PeerStoriesView r15 = this.this$0.getCurrentPeerView();
            if (r15 == null) goto L21;
            r15.updatePosition();
        L21:
            StoryViewer.m19827$$Nest$mupdateLiveVolume(this.this$0);
            if (StoryViewer.m19790$$Nest$fgetshowViewsAfterOpening(this.this$0) == false) goto L25;
            StoryViewer.m19815$$Nest$fputshowViewsAfterOpening(this.this$0, false);
            this.this$0.openViews();
        L35:
            this.this$0.updatePlayingMode();
            this.this$0.locker.unlock();
            return;
        L25:
            if (SharedConfig.storiesIntroShown == true) goto L35;
            if (StoryViewer.m19791$$Nest$fgetstoriesIntro(this.this$0) != null) goto L32;
            StoryViewer r63 = this.this$0;
            if (r63.containerView == null) goto L32;
            StoryViewer.m19816$$Nest$fputstoriesIntro(r63, new StoriesIntro(this.this$0.containerView.getContext(), this.this$0.windowView));
            StoryViewer.m19791$$Nest$fgetstoriesIntro(this.this$0).setAlpha(0.0f);
            StoryViewer r64 = this.this$0;
            r64.containerView.addView(StoryViewer.m19791$$Nest$fgetstoriesIntro(r64));
        L32:
            if (StoryViewer.m19791$$Nest$fgetstoriesIntro(this.this$0) == null) goto L34;
            StoryViewer.m19791$$Nest$fgetstoriesIntro(this.this$0).setOnClickListener(new StoryViewer$9$$ExternalSyntheticLambda0(this));
            StoryViewer.m19791$$Nest$fgetstoriesIntro(this.this$0).animate().alpha(1.0f).setDuration(150).setListener(new AnonymousClass2(this)).start();
        L34:
            SharedConfig.setStoriesIntroShown(true);
            goto L35
        L16:
            if (r14.foundViewToClose == true) goto L18;
            r22.setAlpha(1.0f);
            this.this$0.transitionViewHolder.storyImage.setVisible(true, true);
            this.this$0.transitionViewHolder.storyImage = null;
            goto L18
        L11:
            if (r13.foundViewToClose == true) goto L13;
            r2.setVisible(true, true);
            this.this$0.transitionViewHolder.avatarImage = null;
            goto L13
        }
    }

    /* loaded from: classes7.dex */
    public interface HolderClip {
        void clip(Canvas r1, RectF r2, float r3, boolean r4);
    }

    /* loaded from: classes7.dex */
    public interface HolderDrawAbove {
        void draw(Canvas r1, RectF r2, float r3, boolean r4);
    }

    /* loaded from: classes7.dex */
    public interface PlaceProvider {
        boolean findView(long r1, int r3, int r4, int r5, TransitionViewHolder r6);

        default void loadNext(boolean r1) {
        }

        default void preLayout(long r1, int r3, Runnable r4) {
            r4.run();
        }
    }

    /* loaded from: classes7.dex */
    public static class TransitionViewHolder {
        public float alpha;
        public ImageReceiver avatarImage;
        public Paint bgPaint;
        public boolean checkParentScale;
        public float clipBottom;
        public View clipParent;
        public float clipTop;
        public ImageReceiver crossfadeToAvatarImage;
        public HolderDrawAbove drawAbove;
        public HolderClip drawClip;
        public boolean isLive;
        public StoriesUtilities.AvatarStoryParams params;
        public RadialProgress radialProgressUpload;
        public int storyId;
        public ImageReceiver storyImage;
        public View view;

        public TransitionViewHolder() {
            this.alpha = 1.0f;
        }

        public void clear() {
            this.view = null;
            this.params = null;
            this.avatarImage = null;
            this.storyImage = null;
            this.drawAbove = null;
            this.drawClip = null;
            this.clipParent = null;
            this.radialProgressUpload = null;
            this.isLive = false;
            this.crossfadeToAvatarImage = null;
            this.clipTop = 0.0f;
            this.clipBottom = 0.0f;
            this.storyId = 0;
            this.bgPaint = null;
            this.alpha = 1.0f;
        }

        public Integer getAvatarImageRoundRadius() {
            if (this.avatarImage != null) goto L5;
            return null;
        L5:
            if (this.checkParentScale == false) goto L11;
            View r0 = this.view;
            if (r0 == null) goto L11;
            if (r0.getParent() == null) goto L11;
            float r02 = ((ViewGroup) this.view.getParent()).getScaleY();
        L13:
            return Integer.valueOf((int) (this.avatarImage.getRoundRadius()[0] * r02));
        L11:
            r02 = 1.0f;
            goto L13
        }
    }

    /* loaded from: classes7.dex */
    public class VideoPlayerHolder extends VideoPlayerHolderBase {
        volatile int audioTrackState;
        boolean logBuffering;
        final /* synthetic */ StoryViewer this$0;

        public static /* synthetic */ void $r8$lambda$9jdGF2ToQ723DQQAyIOPWnwhwgg(VideoPlayerHolder r0) {
            r0.lambda$onStateChanged$1();
        }

        /* renamed from: $r8$lambda$jCC5wop1g2k-wqutEkeYPtek6jM, reason: not valid java name */
        public static /* synthetic */ void m19834$r8$lambda$jCC5wop1g2kwqutEkeYPtek6jM(VideoPlayerHolder r0, int r1) {
            r0.lambda$onAudioTrackChanged$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$lD12POQRC8v5mxNs1MX06MUvyRw(VideoPlayerHolder r0) {
            r0.lambda$onStateChanged$0();
        }

        public VideoPlayerHolder(StoryViewer r2, SurfaceView r3, TextureView r4) {
            this.this$0 = r2;
            this.audioTrackState = 0;
            if (r2.USE_SURFACE_VIEW == false) goto L6;
            with(r3);
            return;
        L6:
            with(r4);
        }

        private /* synthetic */ void lambda$onAudioTrackChanged$2(int r3) {
            StoryViewer r0 = this.this$0;
            if (r0.playerHolder == this) goto L6;
            return;
        L6:
            if (r3 != 1) goto L8;
            StoryViewer.m19796$$Nest$fputaudioFocusPaused(r0, false);
        L8:
            this.this$0.updatePlayingMode();
        }

        private /* synthetic */ void lambda$onStateChanged$0() {
            PeerStoriesView r3 = this.this$0.getCurrentPeerView();
            if (r3 != null) goto L5;
            return;
        L5:
            if (r3.currentStory.storyItem == null) goto L9;
            FileLog.d("StoryViewer displayed story buffering dialogId=" + r3.getCurrentPeer() + " storyId=" + r3.currentStory.storyItem.id);
            return;
        }

        private /* synthetic */ void lambda$onStateChanged$1() {
            PeerStoriesView r3 = this.this$0.getCurrentPeerView();
            if (r3 != null) goto L5;
            return;
        L5:
            if (r3.currentStory.storyItem == null) goto L9;
            FileLog.d("StoryViewer displayed story playing dialogId=" + r3.getCurrentPeer() + " storyId=" + r3.currentStory.storyItem.id);
            return;
        }

        @Override // org.telegram.messenger.video.VideoPlayerHolderBase
        public boolean needRepeat() {
            return StoryViewer.m19778$$Nest$fgetisCaptionPartVisible(this.this$0);
        }

        @Override // org.telegram.messenger.video.VideoPlayerHolderBase
        public void onAudioTrackChanged(int r2) {
            this.audioTrackState = r2;
            if (r2 != 0) goto L5;
            return;
        L5:
            AndroidUtilities.runOnUIThread(new StoryViewer$VideoPlayerHolder$$ExternalSyntheticLambda0(this, r2));
        }

        @Override // org.telegram.messenger.video.VideoPlayerHolderBase
        public void onRenderedFirstFrame() {
            PeerStoriesView.VideoPlayerSharedScope r0 = this.this$0.currentPlayerScope;
            if (r0 == null) goto L13;
            r0.firstFrameRendered = true;
            this.firstFrameRendered = true;
            r0.invalidate();
            if (this.paused == true) goto L8;
            return;
        L8:
            if (StoryViewer.m19792$$Nest$fgetsurfaceView(this.this$0) == null) goto L12;
            prepareStub();
            return;
        L12:
            return;
        }

        @Override // org.telegram.messenger.video.VideoPlayerHolderBase
        public void onStateChanged(boolean r3, int r4) {
            if (r4 == 3) goto L6;
            if (r4 == 2) goto L6;
            return;
        L6:
            if (this.firstFrameRendered == false) goto L10;
            if (r4 != 2) goto L10;
            this.logBuffering = true;
            AndroidUtilities.runOnUIThread(new StoryViewer$VideoPlayerHolder$$ExternalSyntheticLambda1(this));
        L10:
            if (this.logBuffering == false) goto L15;
            if (r4 != 3) goto L16;
            this.logBuffering = false;
            AndroidUtilities.runOnUIThread(new StoryViewer$VideoPlayerHolder$$ExternalSyntheticLambda2(this));
            return;
        L16:
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$7WO8fG2CQhUMMyVbecx6NxOYo5A(StoryViewer r0, ValueAnimator r1) {
        r0.lambda$startOpenAnimation$9(r1);
    }

    /* renamed from: $r8$lambda$C2N8F78X6Jm8xlUwJr-S-smKDds, reason: not valid java name */
    public static /* synthetic */ void m19765$r8$lambda$C2N8F78X6Jm8xlUwJrSsmKDds(StoryViewer r0, ValueAnimator r1) {
        r0.lambda$cancelSwipeToViews$3(r1);
    }

    /* renamed from: $r8$lambda$FYzPP5Tv-AxvLpALlCIv34YK8-E, reason: not valid java name */
    public static /* synthetic */ void m19766$r8$lambda$FYzPP5TvAxvLpALlCIv34YK8E(StoryViewer r0, ValueAnimator r1) {
        r0.lambda$cancelSwipeToReply$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$Np4h9Q_S4kYlT2GmXHk23pc_Du0(StoryViewer r0) {
        r0.lambda$layoutAndFindView$7();
    }

    public static /* synthetic */ void $r8$lambda$Ve8QkeFbtUaQDy9YTuxK7_T9pXY(StoryViewer r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$WREvDYjj9TniB4sc4Z46v1fIlS0(StoryViewer r0) {
        r0.lambda$openViews$13();
    }

    public static /* synthetic */ void $r8$lambda$aOSIKmSELaVJ14kWE_QUXwI7Tcs(StoryViewer r0, ValueAnimator r1) {
        r0.lambda$startCloseAnimation$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$jBCmlvpF01hZSOsfce1YZxbKNmA(StoryViewer r0) {
        r0.lambda$cleanupBeforeExit$10();
    }

    public static /* synthetic */ void $r8$lambda$nsTCQeBpS4Jb83c2nn5JkZwsN4E(StoryViewer r0) {
        r0.lambda$listenToAttachedSheet$5();
    }

    /* renamed from: $r8$lambda$pSCs54wB-EfW4iqF5ks6nFenqBw, reason: not valid java name */
    public static /* synthetic */ void m19767$r8$lambda$pSCs54wBEfW4iqF5ks6nFenqBw(StoryViewer r0) {
        r0.lambda$open$2();
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$qJXh6tuCXdD4Y2hJGfBcF1X8TtI(StoryViewer r0, View r1, WindowInsetsCompat r2) {
        return r0.lambda$open$1(r1, r2);
    }

    /* renamed from: $r8$lambda$s-5ieMD1Q0gK7WFRK1AyOzsKi8w, reason: not valid java name */
    public static /* synthetic */ void m19768$r8$lambda$s5ieMD1Q0gK7WFRK1AyOzsKi8w(StoryViewer r0, DialogInterface r1) {
        r0.lambda$showDialog$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$wBuEOUM9MhZZCP0H7ZvGxuamDMU(StoryViewer r0) {
        r0.lambda$startCloseAnimation$12();
    }

    public static /* synthetic */ void $r8$lambda$x6MU0B9NkEDD6KvrhMr7uYqybug(StoryViewer r0, int r1) {
        r0.lambda$onAudioFocusChange$8(r1);
    }

    /* renamed from: -$$Nest$fgetallowTouchesByViewpager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19769$$Nest$fgetallowTouchesByViewpager(StoryViewer r0) {
        return r0.allowTouchesByViewpager;
    }

    /* renamed from: -$$Nest$fgetanimateAvatar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19770$$Nest$fgetanimateAvatar(StoryViewer r0) {
        return r0.animateAvatar;
    }

    /* renamed from: -$$Nest$fgetdelayedTapRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m19771$$Nest$fgetdelayedTapRunnable(StoryViewer r0) {
        return r0.delayedTapRunnable;
    }

    /* renamed from: -$$Nest$fgetflingCalled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19772$$Nest$fgetflingCalled(StoryViewer r0) {
        return r0.flingCalled;
    }

    /* renamed from: -$$Nest$fgetfromRadius, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m19773$$Nest$fgetfromRadius(StoryViewer r0) {
        return r0.fromRadius;
    }

    /* renamed from: -$$Nest$fgetfullyVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19774$$Nest$fgetfullyVisible(StoryViewer r0) {
        return r0.fullyVisible;
    }

    /* renamed from: -$$Nest$fgethideEnterViewProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19775$$Nest$fgethideEnterViewProgress(StoryViewer r0) {
        return r0.hideEnterViewProgress;
    }

    /* renamed from: -$$Nest$fgetinvalidateOutRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19776$$Nest$fgetinvalidateOutRect(StoryViewer r0) {
        return r0.invalidateOutRect;
    }

    /* renamed from: -$$Nest$fgetisCaption, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19777$$Nest$fgetisCaption(StoryViewer r0) {
        return r0.isCaption;
    }

    /* renamed from: -$$Nest$fgetisCaptionPartVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19778$$Nest$fgetisCaptionPartVisible(StoryViewer r0) {
        return r0.isCaptionPartVisible;
    }

    /* renamed from: -$$Nest$fgetisClosed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19779$$Nest$fgetisClosed(StoryViewer r0) {
        return r0.isClosed;
    }

    /* renamed from: -$$Nest$fgetisHintVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19780$$Nest$fgetisHintVisible(StoryViewer r0) {
        return r0.isHintVisible;
    }

    /* renamed from: -$$Nest$fgetisInPinchToZoom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19781$$Nest$fgetisInPinchToZoom(StoryViewer r0) {
        return r0.isInPinchToZoom;
    }

    /* renamed from: -$$Nest$fgetisInTextSelectionMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19782$$Nest$fgetisInTextSelectionMode(StoryViewer r0) {
        return r0.isInTextSelectionMode;
    }

    /* renamed from: -$$Nest$fgetisInTouchMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19783$$Nest$fgetisInTouchMode(StoryViewer r0) {
        return r0.isInTouchMode;
    }

    /* renamed from: -$$Nest$fgetisLikesReactions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19784$$Nest$fgetisLikesReactions(StoryViewer r0) {
        return r0.isLikesReactions;
    }

    /* renamed from: -$$Nest$fgetisRecording, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19785$$Nest$fgetisRecording(StoryViewer r0) {
        return r0.isRecording;
    }

    /* renamed from: -$$Nest$fgetlastStoryContainerHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19786$$Nest$fgetlastStoryContainerHeight(StoryViewer r0) {
        return r0.lastStoryContainerHeight;
    }

    /* renamed from: -$$Nest$fgetopening, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19787$$Nest$fgetopening(StoryViewer r0) {
        return r0.opening;
    }

    /* renamed from: -$$Nest$fgetplayerSavedPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m19788$$Nest$fgetplayerSavedPosition(StoryViewer r2) {
        return r2.playerSavedPosition;
    }

    /* renamed from: -$$Nest$fgetrealKeyboardHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19789$$Nest$fgetrealKeyboardHeight(StoryViewer r0) {
        return r0.realKeyboardHeight;
    }

    /* renamed from: -$$Nest$fgetshowViewsAfterOpening, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19790$$Nest$fgetshowViewsAfterOpening(StoryViewer r0) {
        return r0.showViewsAfterOpening;
    }

    /* renamed from: -$$Nest$fgetstoriesIntro, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesIntro m19791$$Nest$fgetstoriesIntro(StoryViewer r0) {
        return r0.storiesIntro;
    }

    /* renamed from: -$$Nest$fgetsurfaceView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SurfaceView m19792$$Nest$fgetsurfaceView(StoryViewer r0) {
        return r0.surfaceView;
    }

    /* renamed from: -$$Nest$fgettextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m19793$$Nest$fgettextureView(StoryViewer r0) {
        return r0.textureView;
    }

    /* renamed from: -$$Nest$fgetvolumeControl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesVolumeControl m19794$$Nest$fgetvolumeControl(StoryViewer r0) {
        return r0.volumeControl;
    }

    /* renamed from: -$$Nest$fputallowTouchesByViewpager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19795$$Nest$fputallowTouchesByViewpager(StoryViewer r0, boolean r1) {
        r0.allowTouchesByViewpager = r1;
    }

    /* renamed from: -$$Nest$fputaudioFocusPaused, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19796$$Nest$fputaudioFocusPaused(StoryViewer r0, boolean r1) {
        r0.audioFocusPaused = r1;
    }

    /* renamed from: -$$Nest$fputdelayedTapRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19797$$Nest$fputdelayedTapRunnable(StoryViewer r0, Runnable r1) {
        r0.delayedTapRunnable = r1;
    }

    /* renamed from: -$$Nest$fputflingCalled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19798$$Nest$fputflingCalled(StoryViewer r0, boolean r1) {
        r0.flingCalled = r1;
    }

    /* renamed from: -$$Nest$fputfullyVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19799$$Nest$fputfullyVisible(StoryViewer r0, boolean r1) {
        r0.fullyVisible = r1;
    }

    /* renamed from: -$$Nest$fputhideEnterViewProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19800$$Nest$fputhideEnterViewProgress(StoryViewer r0, float r1) {
        r0.hideEnterViewProgress = r1;
    }

    /* renamed from: -$$Nest$fputinvalidateOutRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19801$$Nest$fputinvalidateOutRect(StoryViewer r0, boolean r1) {
        r0.invalidateOutRect = r1;
    }

    /* renamed from: -$$Nest$fputisBulletinVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19802$$Nest$fputisBulletinVisible(StoryViewer r0, boolean r1) {
        r0.isBulletinVisible = r1;
    }

    /* renamed from: -$$Nest$fputisCaption, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19803$$Nest$fputisCaption(StoryViewer r0, boolean r1) {
        r0.isCaption = r1;
    }

    /* renamed from: -$$Nest$fputisCaptionPartVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19804$$Nest$fputisCaptionPartVisible(StoryViewer r0, boolean r1) {
        r0.isCaptionPartVisible = r1;
    }

    /* renamed from: -$$Nest$fputisHintVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19805$$Nest$fputisHintVisible(StoryViewer r0, boolean r1) {
        r0.isHintVisible = r1;
    }

    /* renamed from: -$$Nest$fputisInPinchToZoom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19806$$Nest$fputisInPinchToZoom(StoryViewer r0, boolean r1) {
        r0.isInPinchToZoom = r1;
    }

    /* renamed from: -$$Nest$fputisInTextSelectionMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19807$$Nest$fputisInTextSelectionMode(StoryViewer r0, boolean r1) {
        r0.isInTextSelectionMode = r1;
    }

    /* renamed from: -$$Nest$fputisLikesReactions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19808$$Nest$fputisLikesReactions(StoryViewer r0, boolean r1) {
        r0.isLikesReactions = r1;
    }

    /* renamed from: -$$Nest$fputisPopupVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19809$$Nest$fputisPopupVisible(StoryViewer r0, boolean r1) {
        r0.isPopupVisible = r1;
    }

    /* renamed from: -$$Nest$fputisRecording, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19810$$Nest$fputisRecording(StoryViewer r0, boolean r1) {
        r0.isRecording = r1;
    }

    /* renamed from: -$$Nest$fputisSwiping, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19811$$Nest$fputisSwiping(StoryViewer r0, boolean r1) {
        r0.isSwiping = r1;
    }

    /* renamed from: -$$Nest$fputisWaiting, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19812$$Nest$fputisWaiting(StoryViewer r0, boolean r1) {
        r0.isWaiting = r1;
    }

    /* renamed from: -$$Nest$fputlastStoryContainerHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19813$$Nest$fputlastStoryContainerHeight(StoryViewer r0, float r1) {
        r0.lastStoryContainerHeight = r1;
    }

    /* renamed from: -$$Nest$fputplayerSavedPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19814$$Nest$fputplayerSavedPosition(StoryViewer r0, long r1) {
        r0.playerSavedPosition = r1;
    }

    /* renamed from: -$$Nest$fputshowViewsAfterOpening, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19815$$Nest$fputshowViewsAfterOpening(StoryViewer r0, boolean r1) {
        r0.showViewsAfterOpening = r1;
    }

    /* renamed from: -$$Nest$fputstoriesIntro, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19816$$Nest$fputstoriesIntro(StoryViewer r0, StoriesIntro r1) {
        r0.storiesIntro = r1;
    }

    /* renamed from: -$$Nest$mcleanupBeforeExit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19817$$Nest$mcleanupBeforeExit(StoryViewer r0) {
        r0.cleanupBeforeExit();
    }

    /* renamed from: -$$Nest$mdispatchVolumeEvent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19818$$Nest$mdispatchVolumeEvent(StoryViewer r0, KeyEvent r1) {
        r0.dispatchVolumeEvent(r1);
    }

    /* renamed from: -$$Nest$mfindClickableView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19819$$Nest$mfindClickableView(StoryViewer r0, FrameLayout r1, float r2, float r3, boolean r4) {
        return r0.findClickableView(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$mgetBlackoutAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19820$$Nest$mgetBlackoutAlpha(StoryViewer r0) {
        return r0.getBlackoutAlpha();
    }

    /* renamed from: -$$Nest$mlayoutAndFindView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19821$$Nest$mlayoutAndFindView(StoryViewer r0) {
        r0.layoutAndFindView();
    }

    /* renamed from: -$$Nest$mrequestAdjust, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19822$$Nest$mrequestAdjust(StoryViewer r0, boolean r1) {
        r0.requestAdjust(r1);
    }

    /* renamed from: -$$Nest$msetInTouchMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19823$$Nest$msetInTouchMode(StoryViewer r0, boolean r1) {
        r0.setInTouchMode(r1);
    }

    /* renamed from: -$$Nest$msetLongPressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19824$$Nest$msetLongPressed(StoryViewer r0, boolean r1) {
        r0.setLongPressed(r1);
    }

    /* renamed from: -$$Nest$mshowKeyboard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19825$$Nest$mshowKeyboard(StoryViewer r0) {
        r0.showKeyboard();
    }

    /* renamed from: -$$Nest$mstartOpenAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19826$$Nest$mstartOpenAnimation(StoryViewer r0) {
        r0.startOpenAnimation();
    }

    /* renamed from: -$$Nest$mupdateLiveVolume, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19827$$Nest$mupdateLiveVolume(StoryViewer r0) {
        r0.updateLiveVolume();
    }

    /* renamed from: -$$Nest$mupdateProgressToDismiss, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19828$$Nest$mupdateProgressToDismiss(StoryViewer r0) {
        r0.updateProgressToDismiss();
    }

    /* renamed from: -$$Nest$sfgetisInSilentMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19829$$Nest$sfgetisInSilentMode() {
        return isInSilentMode;
    }

    /* renamed from: -$$Nest$sfgetrunOpenAnimationAfterLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19830$$Nest$sfgetrunOpenAnimationAfterLayout() {
        return runOpenAnimationAfterLayout;
    }

    /* renamed from: -$$Nest$sfputrunOpenAnimationAfterLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19831$$Nest$sfputrunOpenAnimationAfterLayout(boolean r0) {
        runOpenAnimationAfterLayout = r0;
    }

    static {
        globalInstances = new ArrayList();
        currentSpeed = 1.0f;
        checkSilentMode = true;
        replyDrafts = new LongSparseArray();
    }

    public StoryViewer(BaseFragment r4) {
        this.USE_SURFACE_VIEW = SharedConfig.useSurfaceInStories;
        this.ATTACH_TO_FRAGMENT = true;
        this.ATTACHED_FRAGMENT_IS_EDGE_TO_EDGE = false;
        this.foundViewToClose = false;
        this.allowScreenshots = true;
        this.resourcesProvider = new DarkThemeResourceProvider();
        this.avatarRectTmp = new RectF();
        this.pointPosition = new float[2];
        this.transitionViewHolder = new TransitionViewHolder();
        this.allowTouchesByViewpager = false;
        this.doOnAnimationReadyRunnables = new ArrayList();
        this.isClosed = true;
        this.locker = new AnimationNotificationsLocker();
        this.preparedPlayers = new ArrayList();
        this.isTranslating = false;
        this.longPressRunnable = new StoryViewer$$ExternalSyntheticLambda3(this);
        this.savedPositions = new LongSparseIntArray();
        this.inputBackgroundPaint = new Paint(1);
        this.fragment = r4;
        this.audioManager = (AudioManager) ApplicationLoader.applicationContext.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
    }

    private void checkInSilentMode() {
        if (checkSilentMode == false) goto L9;
        boolean r0 = false;
        checkSilentMode = false;
        if (((AudioManager) this.windowView.getContext().getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND)).getRingerMode() == 2) goto L7;
        r0 = true;
    L7:
        isInSilentMode = r0;
        return;
    }

    private void cleanupBeforeExit() {
        HwFrameLayout r0 = this.containerView;
        if (r0 == null) goto L5;
        r0.disableHwAcceleration();
    L5:
        checkNavBarColor();
        this.locker.unlock();
        StoriesIntro r02 = this.storiesIntro;
        if (r02 == null) goto L8;
        r02.stopAnimation();
        AndroidUtilities.removeFromParent(this.storiesIntro);
        this.storiesIntro = null;
    L8:
        ImageReceiver r03 = this.transitionViewHolder.avatarImage;
        if (r03 == null) goto L11;
        r03.setVisible(true, true);
        this.transitionViewHolder.avatarImage = null;
    L11:
        ImageReceiver r04 = this.transitionViewHolder.storyImage;
        if (r04 == null) goto L15;
        r04.setAlpha(1.0f);
        this.transitionViewHolder.storyImage.setVisible(true, true);
    L15:
        if (this.transitionViewHolder.radialProgressUpload == null) goto L21;
        PeerStoriesView r05 = getCurrentPeerView();
        if (r05 == null) goto L21;
        RadialProgress r06 = r05.headerView.radialProgress;
        if (r06 == null) goto L21;
        this.transitionViewHolder.radialProgressUpload.copyParams(r06);
    L21:
        PeerStoriesView.VideoPlayerSharedScope r07 = this.currentPlayerScope;
        if (r07 == null) goto L24;
        r07.invalidate();
    L24:
        SurfaceView r08 = this.surfaceView;
        if (r08 == null) goto L27;
        r08.setVisibility(4);
    L27:
        release();
        AndroidUtilities.runOnUIThread(new StoryViewer$$ExternalSyntheticLambda10(this));     // Catch: Exception -> L33
    L29:
        this.isShowing = false;
        this.foundViewToClose = false;
        Runnable r09 = this.onCloseListener;
        if (r09 == null) goto L36;
        r09.run();
        this.onCloseListener = null;
        return;
    }

    public static void closeGlobalInstances() {
        int r1 = 0;
    L4:
        if (r1 >= globalInstances.size()) goto L6;
        globalInstances.get(r1).close(false);
        r1 = r1 + 1;
        goto L4
    L6:
        globalInstances.clear();
    }

    private void dispatchVolumeEvent(KeyEvent r3) {
        if (isInSilentMode == false) goto L6;
        toggleSilentMode();
        return;
    L6:
        PeerStoriesView r0 = this.storiesViewPager.getCurrentPeerView();
        if (r0 != null) goto L9;
    L14:
        this.volumeControl.onKeyDown(r3.getKeyCode(), r3);
        return;
    L9:
        if (r0.currentStory.hasSound() == true) goto L14;
        if (r0.currentStory.isVideo() == false) goto L14;
        r0.showNoSoundHint(true);
    }

    private long draftHash(long r3, TL_stories.StoryItem r5) {
        return (r3 + (r3 >> 16)) + (r5.id << 16);
    }

    private boolean findClickableView(FrameLayout r7, float r8, float r9, boolean r10) {
        if (r7 != null) goto L6;
        return false;
    L6:
        if (this.isPopupVisible == false) goto L9;
        return true;
    L9:
        if (this.selfStoryViewsView != null) goto L11;
    L13:
        PeerStoriesView r1 = this.storiesViewPager.getCurrentPeerView();
        if (r1 != null) goto L16;
    L21:
        if (r10 == false) goto L23;
        return false;
    L23:
        if (r1 == null) goto L31;
        ChatActivityEnterView r102 = r1.chatActivityEnterView;
        if (r102 == null) goto L31;
        if (r102.getVisibility() != 0) goto L31;
        if (r9 <= (((this.containerView.getY() + this.storiesViewPager.getY()) + r1.getY()) + r1.chatActivityEnterView.getY())) goto L31;
        return true;
    L31:
        if (r1 == null) goto L38;
        ChatActivityEnterView r103 = r1.chatActivityEnterView;
        if (r103 == null) goto L38;
        if (r103.isRecordingAudioVideo() == false) goto L38;
        return true;
    L38:
        if (this.storiesIntro == null) goto L41;
        return true;
    L41:
        return AndroidUtilities.findClickableView(r7, r8, r9, r1);
    L16:
        if (r1.findClickableView(r1, ((r8 - this.containerView.getX()) - this.storiesViewPager.getX()) - r1.getX(), ((r9 - this.containerView.getY()) - this.storiesViewPager.getY()) - r1.getY(), r10) == false) goto L19;
        return true;
    L19:
        if (r1.keyboardVisible == false) goto L21;
        return false;
    L11:
        if (this.selfStoriesViewsOffset == 0.0f) goto L13;
        return true;
    }

    private float getBlackoutAlpha() {
        return this.progressToOpen * (((1.0f - this.progressToDismiss) * 0.5f) + 0.5f);
    }

    public static boolean isShowingImage(MessageObject r3) {
        if (lastStoryItem != null) goto L5;
    L17:
        return false;
    L5:
        if (r3.type == 23) goto L9;
        if (r3.isWebpage() == false) goto L17;
    L9:
        if (runOpenAnimationAfterLayout == true) goto L17;
        if (lastStoryItem.messageId != r3.getId()) goto L17;
        if (lastStoryItem.messageType == 3) goto L17;
        return true;
    }

    private /* synthetic */ void lambda$cancelSwipeToReply$6(ValueAnimator r3) {
        this.swipeToReplyOffset = ((Float) r3.getAnimatedValue()).floatValue();
        this.swipeToReplyProgress = Utilities.clamp(this.swipeToReplyOffset / AndroidUtilities.dp(200.0f), 1.0f, 0.0f);
        StoriesViewPager r2 = this.storiesViewPager;
        if (r2 != null) goto L5;
        PeerStoriesView r22 = null;
    L6:
        if (r22 == null) goto L9;
        r22.invalidate();
        return;
    L9:
        return;
    L5:
        r22 = r2.getCurrentPeerView();
        goto L6
    }

    private /* synthetic */ void lambda$cancelSwipeToViews$3(ValueAnimator r1) {
        this.selfStoriesViewsOffset = ((Float) r1.getAnimatedValue()).floatValue();
        this.containerView.invalidate();
    }

    private /* synthetic */ void lambda$cleanupBeforeExit$10() {
        SizeNotifierFrameLayout r0 = this.windowView;     // Catch: Exception -> L11
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.ATTACH_TO_FRAGMENT == false) goto L8;
        AndroidUtilities.removeFromParent(r0);     // Catch: Exception -> L11
    L9:
        this.windowView = null;     // Catch: Exception -> L11
        return;
    L8:
        this.windowManager.removeView(r0);     // Catch: Exception -> L11
    }

    private /* synthetic */ void lambda$layoutAndFindView$7() {
        updateTransitionParams();
        ImageReceiver r0 = this.transitionViewHolder.avatarImage;
        if (r0 == null) goto L5;
        r0.setVisible(false, true);
    L5:
        ImageReceiver r3 = this.transitionViewHolder.storyImage;
        if (r3 == null) goto L9;
        r3.setVisible(false, true);
        return;
    }

    private /* synthetic */ void lambda$listenToAttachedSheet$5() {
        this.currentSheet = null;
        updatePlayingMode();
    }

    private /* synthetic */ void lambda$new$0() {
        setLongPressed(true);
    }

    private /* synthetic */ void lambda$onAudioFocusChange$8(int r4) {
        if (this.isClosed == false) goto L5;
    L24:
        requestAudioFocus(false);
        return;
    L5:
        if (SharedConfig.pauseMusicOnMedia == false) goto L24;
        if (r4 != 1) goto L12;
        this.audioFocusPaused = false;
        this.hasAudioFocus = true;
        this.audioFocusRequested = true;
        updateLiveVolume();
        updatePlayingMode();
        return;
    L12:
        if (r4 != (-1)) goto L14;
    L17:
        this.hasAudioFocus = false;
        this.audioFocusPaused = true;
        VideoPlayerHolder r42 = this.playerHolder;
        if (r42 != null) goto L20;
    L22:
        updateLiveVolume();
        updatePlayingMode();
        return;
    L20:
        if (r42.isPlaying() == false) goto L22;
        this.playerHolder.pause();
        goto L22
    L14:
        if (r4 == (-2)) goto L17;
    }

    private /* synthetic */ WindowInsetsCompat lambda$open$1(View r4, WindowInsetsCompat r5) {
        int r42 = 0;
        Insets r0 = AndroidUtilities.getDefaultWindowInsets(r5, false);
        ViewGroup.MarginLayoutParams r1 = (ViewGroup.MarginLayoutParams) this.containerView.getLayoutParams();
        if (this.ATTACHED_FRAGMENT_IS_EDGE_TO_EDGE == true) goto L6;
        r42 = r5.getSystemWindowInsetTop();
    L6:
        r1.topMargin = r42;
        if (this.ATTACHED_FRAGMENT_IS_EDGE_TO_EDGE == false) goto L9;
        int r43 = r5.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
    L10:
        r1.bottomMargin = r43;
        r1.leftMargin = r0.left;
        r1.rightMargin = r0.right;
        SizeNotifierFrameLayout r44 = this.windowView;
        if (r44 == null) goto L13;
        r44.requestLayout();
    L13:
        HwFrameLayout r3 = this.containerView;
        if (r3 == null) goto L17;
        r3.requestLayout();
    L17:
        return WindowInsetsCompat.CONSUMED;
    L9:
        r43 = r5.getSystemWindowInsetBottom();
        goto L10
    }

    private /* synthetic */ void lambda$open$2() {
        LaunchActivity r0 = LaunchActivity.instance;
        if (r0 == null) goto L6;
        r0.onBackPressed();
        return;
    L6:
        onAttachedBackPressed();
    }

    private /* synthetic */ void lambda$openViews$13() {
        this.allowSelfStoriesView = true;
        cancelSwipeToViews(true);
    }

    private /* synthetic */ void lambda$showDialog$4(DialogInterface r2) {
        if (r2 != this.currentDialog) goto L6;
        this.currentDialog = null;
        updatePlayingMode();
        return;
    }

    private /* synthetic */ void lambda$startCloseAnimation$11(ValueAnimator r1) {
        this.progressToOpen = ((Float) r1.getAnimatedValue()).floatValue();
        checkNavBarColor();
        SizeNotifierFrameLayout r12 = this.windowView;
        if (r12 == null) goto L5;
        r12.invalidate();
    L5:
        updateLiveVolume();
    }

    private /* synthetic */ void lambda$startCloseAnimation$12() {
        if (this.openCloseAnimator != null) goto L5;
        return;
    L5:
        HwFrameLayout r0 = this.containerView;
        if (r0 == null) goto L8;
        r0.enableHwAcceleration();
    L8:
        this.openCloseAnimator.addListener(new AnonymousClass10(this));
        this.openCloseAnimator.setDuration(320);
        this.openCloseAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.openCloseAnimator.start();
    }

    private /* synthetic */ void lambda$startOpenAnimation$9(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.progressToOpen = r22;
        HwFrameLayout r0 = this.containerView;
        if (r0 == null) goto L5;
        r0.checkHwAcceleration(r22);
    L5:
        updateLiveVolume();
        checkNavBarColor();
        SizeNotifierFrameLayout r1 = this.windowView;
        if (r1 == null) goto L9;
        r1.invalidate();
        return;
    }

    private void layoutAndFindView() {
        this.foundViewToClose = true;
        ImageReceiver r1 = this.transitionViewHolder.avatarImage;
        if (r1 == null) goto L5;
        r1.setVisible(true, true);
    L5:
        ImageReceiver r12 = this.transitionViewHolder.storyImage;
        if (r12 == null) goto L9;
        r12.setAlpha(1.0f);
        this.transitionViewHolder.storyImage.setVisible(true, true);
    L9:
        if (this.storiesList == null) goto L18;
        PeerStoriesView r0 = this.storiesViewPager.getCurrentPeerView();
        if (r0 == null) goto L18;
        int r02 = r0.getSelectedPosition();
        if (r02 < 0) goto L18;
        if (r02 >= this.storiesList.messageObjects.size()) goto L18;
        this.messageId = this.storiesList.messageObjects.get(r02).getId();
    L18:
        if (this.placeProvider == null) goto L36;
        long r03 = this.storiesViewPager.getCurrentDialogId();
        int r2 = this.messageId;
        if ((this.storiesList instanceof StoriesController.StoryRepostsList) == false) goto L34;
        PeerStoriesView r3 = this.storiesViewPager.getCurrentPeerView();
        if (r3 != null) goto L24;
        int r4 = 0;
    L25:
        if (r3 == null) goto L31;
        if (r4 < 0) goto L31;
        if (r4 >= r3.storyItems.size()) goto L31;
        TL_stories.StoryItem r32 = r3.storyItems.get(r4);
    L32:
        if (r32 == null) goto L34;
        r03 = r32.dialogId;
        r2 = r32.id;
    L31:
        r32 = null;
        goto L32
    L24:
        r4 = r3.getSelectedPosition();
    L34:
        this.placeProvider.preLayout(r03, r2, new StoryViewer$$ExternalSyntheticLambda8(this));
        return;
    }

    private void lockOrientation(boolean r2) {
        Activity r1 = AndroidUtilities.findActivity(this.fragment.getContext());
        if (r1 == null) goto L16;
        if (r2 == false) goto L6;
        int r0 = 1;
    L14:
        r1.setRequestedOrientation(r0);     // Catch: Exception -> L13
    L9:
        if (r2 == false) goto L11;
        r1.getWindow().addFlags(128);
        return;
    L11:
        r1.getWindow().clearFlags(128);
        return;
    L6:
        r0 = -1;
        goto L14
    }

    private void requestAdjust(boolean r2) {
        if (this.ATTACH_TO_FRAGMENT == true) goto L5;
        WindowManager.LayoutParams r0 = this.windowLayoutParams;
        if (r2 == false) goto L15;
        int r22 = 48;
    L16:
        r0.softInputMode = r22;
        this.windowManager.updateViewLayout(this.windowView, r0);     // Catch: Exception -> L19
        return;
    L19:
        e = move-exception;
        FileLog.e(e);
        return;
    L15:
        r22 = 16;
        goto L16
    L5:
        if (this.ATTACHED_FRAGMENT_IS_EDGE_TO_EDGE == true) goto L24;
        BaseFragment r02 = this.fragment;
        if (r2 == false) goto L10;
        AndroidUtilities.requestAdjustNothing(r02.getParentActivity(), this.fragment.getClassGuid());
        return;
    L10:
        AndroidUtilities.requestAdjustResize(r02.getParentActivity(), this.fragment.getClassGuid());
        return;
    }

    private void requestAudioFocus(boolean r3) {
        if (this.audioManager == null) goto L21;
        if (r3 == false) goto L15;
        if (SharedConfig.pauseMusicOnMedia == false) goto L15;
        if (this.hasAudioFocus == false) goto L11;
        return;
    L11:
        if (this.audioManager.requestAudioFocus(this, 3, 2) != 1) goto L19;
        this.hasAudioFocus = true;
        this.audioFocusRequested = true;
        return;
    L19:
        return;
    L15:
        if (this.audioFocusRequested == false) goto L20;
        this.hasAudioFocus = false;
        this.audioFocusPaused = false;
        this.audioFocusRequested = false;
        this.audioManager.abandonAudioFocus(this);
        updateLiveVolume();
        MediaController.getInstance().tryResumePausedAudio();
        return;
    L20:
        return;
    }

    private void setInTouchMode(boolean r1) {
        this.isInTouchMode = r1;
        if (r1 == false) goto L6;
        this.volumeControl.hide();
    L8:
        updatePlayingMode();
        return;
    L6:
        if (this.audioFocusPaused == false) goto L8;
        this.audioFocusPaused = false;
        updateLiveVolume();
        goto L8
    }

    private void setLongPressed(boolean r3) {
        if (this.isLongpressed == r3) goto L39;
        this.isLongpressed = r3;
        if (r3 == true) goto L7;
    L33:
        updatePlayingMode();
        StoriesViewPager r32 = this.storiesViewPager;
        if (r32 == null) goto L40;
        PeerStoriesView r33 = r32.getCurrentPeerView();
        if (r33 == null) goto L41;
        r33.setLongpressed(this.isLongpressed);
        return;
    L41:
        return;
    L40:
        return;
    L7:
        if (this.isInPinchToZoom == true) goto L33;
        PeerStoriesView r34 = this.storiesViewPager.getCurrentPeerView();
        if (r34 == null) goto L33;
        PeerStoriesView.StoryItemHolder r0 = r34.currentStory;
        if (r0 == null) goto L33;
        if (r0.isLive() == true) goto L33;
        if (r34.currentStory.uploadingStory != null) goto L33;
        if (this.inSeekingMode == false) goto L19;
    L25:
        PeerStoriesView.VideoPlayerSharedScope r35 = this.currentPlayerScope;
        if (r35 == null) goto L32;
        VideoPlayerHolder r36 = r35.player;
        if (r36 == null) goto L32;
        if (this.inSeekingMode == true) goto L32;
        r36.setSeeking(true);
    L32:
        this.inSeekingMode = true;
        goto L33
    L19:
        if (this.inSwipeToDissmissMode == true) goto L25;
        PeerStoriesView.VideoPlayerSharedScope r02 = this.currentPlayerScope;
        if (r02 == null) goto L25;
        if (r02.player == null) goto L25;
        r34.storyContainer.invalidate();
        BotWebViewVibrationEffect.IMPACT_LIGHT.vibrate();
        goto L25
    }

    private void setNavigationButtonsColor(boolean r3) {
        LaunchActivity r0 = LaunchActivity.instance;
        if (this.ATTACH_TO_FRAGMENT == false) goto L11;
        if (r0 == null) goto L12;
        if (r3 == false) goto L8;
        this.openedFromLightNavigationBar = r0.isLightNavigationBar();
    L8:
        if (this.openedFromLightNavigationBar == false) goto L13;
        AndroidUtilities.setLightNavigationBar(r0, !r3);
        return;
    L13:
        return;
    L12:
        return;
    }

    private void showKeyboard() {
        PeerStoriesView r0 = this.storiesViewPager.getCurrentPeerView();
        if (r0 != null) goto L5;
    L8:
        cancelSwipeToReply();
        return;
    L5:
        if (r0.showKeyboard() == false) goto L8;
        AndroidUtilities.runOnUIThread(new StoryViewer$$ExternalSyntheticLambda11(this), 200);
    }

    private void startCloseAnimation(boolean r5) {
        setNavigationButtonsColor(false);
        updateTransitionParams();
        this.locker.lock();
        this.fromDismissOffset = this.swipeToDismissOffset;
        this.opening = false;
        ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{this.progressToOpen, 0.0f});
        this.openCloseAnimator = r2;
        r2.addUpdateListener(new StoryViewer$$ExternalSyntheticLambda0(this));
        if (r5 == true) goto L11;
        this.fromY = 0.0f;
        this.fromX = 0.0f;
        ImageReceiver r52 = this.transitionViewHolder.avatarImage;
        if (r52 == null) goto L7;
        r52.setVisible(true, true);
    L7:
        ImageReceiver r53 = this.transitionViewHolder.storyImage;
        if (r53 == null) goto L10;
        r53.setVisible(true, true);
    L10:
        TransitionViewHolder r54 = this.transitionViewHolder;
        r54.storyImage = null;
        r54.avatarImage = null;
    L12:
        AndroidUtilities.runOnUIThread(new StoryViewer$$ExternalSyntheticLambda1(this), 16);
        return;
    L11:
        layoutAndFindView();
        goto L12
    }

    private void startOpenAnimation() {
        updateTransitionParams();
        this.progressToOpen = 0.0f;
        setNavigationButtonsColor(true);
        int r1 = 0;
        this.foundViewToClose = false;
        animationInProgress = true;
        this.fromDismissOffset = this.swipeToDismissOffset;
        if (this.transitionViewHolder.radialProgressUpload == null) goto L9;
        PeerStoriesView r2 = getCurrentPeerView();
        if (r2 == null) goto L9;
        RadialProgress r22 = r2.headerView.radialProgress;
        if (r22 == null) goto L9;
        r22.copyParams(this.transitionViewHolder.radialProgressUpload);
    L9:
        this.opening = true;
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.openCloseAnimator = r0;
        r0.addUpdateListener(new StoryViewer$$ExternalSyntheticLambda12(this));
        this.locker.lock();
        HwFrameLayout r02 = this.containerView;
        if (r02 == null) goto L12;
        r02.enableHwAcceleration();
    L12:
        this.openCloseAnimator.addListener(new AnonymousClass9(this));
        this.openCloseAnimator.setStartDelay(40);
        this.openCloseAnimator.setDuration(250);
        this.openCloseAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.openCloseAnimator.start();
        if (this.doOnAnimationReadyRunnables.isEmpty() == true) goto L20;
    L14:
        int r03 = this.doOnAnimationReadyRunnables.size();
        ArrayList<Runnable> r23 = this.doOnAnimationReadyRunnables;
        if (r1 >= r03) goto L17;
        r23.get(r1).run();
        r1 = r1 + 1;
        goto L14
    L17:
        r23.clear();
        return;
    }

    private void updateLiveVolume() {
        LivePlayer r0 = this.livePlayer;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.audioFocusPaused == false) goto L7;
        float r3 = 0.0f;
    L8:
        r0.setVolume(r3);
        return;
    L7:
        float r1 = 1.0f - this.progressToDismiss;
        r3 = this.progressToOpen * r1;
        goto L8
    }

    private void updatePipSource() {
    }

    private void updateProgressToDismiss() {
        float r0 = Utilities.clamp01(Math.abs(Math.max(this.swipeToDismissHorizontalOffset, this.swipeToDismissOffset) / AndroidUtilities.dp(80.0f)));
        if (this.progressToDismiss == r0) goto L8;
        this.progressToDismiss = r0;
        checkNavBarColor();
        PeerStoriesView r02 = this.storiesViewPager.getCurrentPeerView();
        if (r02 == null) goto L7;
        r02.progressToDismissUpdated();
    L7:
        updateLiveVolume();
    L8:
        SizeNotifierFrameLayout r2 = this.windowView;
        if (r2 == null) goto L12;
        r2.invalidate();
        return;
    }

    private void updateTransitionParams() {
        if (this.placeProvider == null) goto L84;
        ImageReceiver r1 = this.transitionViewHolder.avatarImage;
        if (r1 == null) goto L7;
        r1.setVisible(true, true);
    L7:
        ImageReceiver r12 = this.transitionViewHolder.storyImage;
        if (r12 == null) goto L10;
        r12.setAlpha(1.0f);
        this.transitionViewHolder.storyImage.setVisible(true, true);
    L10:
        PeerStoriesView r13 = this.storiesViewPager.getCurrentPeerView();
        if (r13 != null) goto L13;
        int r5 = 0;
    L14:
        if (r13 == null) goto L20;
        if (r5 < 0) goto L20;
        if (r5 >= r13.storyItems.size()) goto L20;
        int r6 = r13.storyItems.get(r5).id;
    L22:
        if (r13 == null) goto L28;
        if (r5 < 0) goto L28;
        if (r5 >= r13.storyItems.size()) goto L28;
        TL_stories.StoryItem r14 = r13.storyItems.get(r5);
    L29:
        if (r14 == null) goto L31;
    L33:
        long r8 = this.storiesViewPager.getCurrentDialogId();
        StoriesController.StoriesList r52 = this.storiesList;
        if ((r52 instanceof StoriesController.SearchStoriesList) == false) goto L39;
        if (r14 == null) goto L39;
        r8 = r14.dialogId;
        r6 = r14.messageId;
    L37:
        int r142 = r6;
        long r11 = r8;
        this.transitionViewHolder.clear();
        PlaceProvider r10 = this.placeProvider;
        int r132 = this.messageId;
        if (r14 != null) goto L48;
        int r15 = -1;
    L50:
        if (r10.findView(r11, r132, r142, r15, this.transitionViewHolder) == false) goto L82;
        TransitionViewHolder r16 = this.transitionViewHolder;
        r16.storyId = r142;
        View r17 = r16.view;
        if (r17 == null) goto L80;
        int[] r53 = new int[2];
        r17.getLocationOnScreen(r53);
        View r18 = this.transitionViewHolder.view;
        if ((r18 instanceof ChatMessageCell) == false) goto L56;
        r53[1] = r53[1] + r18.getPaddingTop();
    L56:
        int r19 = r53[0];
        this.fromXCell = r19;
        this.fromYCell = r53[1];
        TransitionViewHolder r62 = this.transitionViewHolder;
        KeyEvent.Callback r82 = r62.view;
        if ((r82 instanceof StoriesListPlaceProvider.AvatarOverlaysView) == false) goto L59;
        this.animateFromCell = (StoriesListPlaceProvider.AvatarOverlaysView) r82;
    L60:
        this.animateAvatar = false;
        ImageReceiver r7 = r62.avatarImage;
        if (r7 == null) goto L69;
        this.fromX = r19 + r7.getCenterX();
        this.fromY = r53[1] + this.transitionViewHolder.avatarImage.getCenterY();
        this.fromWidth = this.transitionViewHolder.avatarImage.getImageWidth();
        this.fromHeight = this.transitionViewHolder.avatarImage.getImageHeight();
        StoriesUtilities.AvatarStoryParams r110 = this.transitionViewHolder.params;
        if (r110 == null) goto L66;
        this.fromWidth *= r110.getScale();
        this.fromHeight *= this.transitionViewHolder.params.getScale();
    L66:
        if ((this.transitionViewHolder.view.getParent() instanceof View) == false) goto L68;
        View r111 = (View) this.transitionViewHolder.view.getParent();
        this.fromX = r53[0] + (this.transitionViewHolder.avatarImage.getCenterX() * r111.getScaleX());
        this.fromY = r53[1] + (this.transitionViewHolder.avatarImage.getCenterY() * r111.getScaleY());
        this.fromWidth *= r111.getScaleX();
        this.fromHeight *= r111.getScaleY();
    L68:
        this.animateAvatar = true;
    L72:
        this.transitionViewHolder.clipParent.getLocationOnScreen(r53);
        TransitionViewHolder r112 = this.transitionViewHolder;
        float r3 = r112.clipTop;
        if (r3 == 0.0f) goto L75;
    L78:
        int r2 = r53[1];
        this.clipTop = r2 + r3;
        this.clipBottom = r2 + r112.clipBottom;
        return;
    L75:
        if (r112.clipBottom != 0.0f) goto L78;
        this.clipTop = 0.0f;
        this.clipBottom = 0.0f;
        return;
    L69:
        ImageReceiver r32 = r62.storyImage;
        if (r32 == null) goto L72;
        this.fromX = r19 + r32.getCenterX();
        this.fromY = r53[1] + this.transitionViewHolder.storyImage.getCenterY();
        this.fromWidth = this.transitionViewHolder.storyImage.getImageWidth();
        this.fromHeight = this.transitionViewHolder.storyImage.getImageHeight();
        this.fromRadius = this.transitionViewHolder.storyImage.getRoundRadius();
        goto L72
    L59:
        this.animateFromCell = null;
        goto L60
    L80:
        this.animateAvatar = false;
        this.fromY = 0.0f;
        this.fromX = 0.0f;
        return;
    L82:
        this.animateAvatar = false;
        this.fromY = 0.0f;
        this.fromX = 0.0f;
        return;
    L48:
        r15 = r14.messageType;
    L39:
        if ((r52 instanceof StoriesController.StoryRepostsList) == false) goto L42;
        if (r14 == null) goto L42;
        r8 = r14.dialogId;
        r6 = r14.id;
    L42:
        if (r52 == null) goto L37;
        r6 = this.dayStoryId;
        goto L37
    L31:
        if (this.isSingleStory == false) goto L33;
        r14 = this.singleStory;
    L28:
        r14 = null;
    L20:
        r6 = 0;
        goto L22
    L13:
        r5 = r13.getSelectedPosition();
        goto L14
    L84:
        this.animateAvatar = false;
        this.fromY = 0.0f;
        this.fromX = 0.0f;
    }

    public void allowScreenshots(boolean r3) {
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L6;
        return;
    L6:
        if (this.isShowing == false) goto L10;
        if (r3 == true) goto L10;
        boolean r32 = false;
    L12:
        if (this.allowScreenshots == r32) goto L40;
        this.allowScreenshots = r32;
        SurfaceView r0 = this.surfaceView;
        if (r0 == null) goto L16;
        r0.setSecure(!r32);
    L16:
        LivePlayerView r02 = this.liveView;
        if (r02 == null) goto L20;
        r02.setSecure(!r32);
    L20:
        if (this.ATTACH_TO_FRAGMENT == true) goto L22;
        WindowManager.LayoutParams r03 = this.windowLayoutParams;
        if (r32 == false) goto L32;
        r03.flags &= -8193;
        AndroidUtilities.logFlagSecure();
    L38:
        this.windowManager.updateViewLayout(this.windowView, this.windowLayoutParams);     // Catch: Exception -> L35
        return;
    L35:
        e = move-exception;
        FileLog.e(e);
        return;
    L32:
        r03.flags |= 8192;
        AndroidUtilities.logFlagSecure();
        goto L38
    L22:
        if (this.fragment.getParentActivity() == null) goto L41;
        BaseFragment r2 = this.fragment;
        if (r32 == false) goto L27;
        r2.getParentActivity().getWindow().clearFlags(8192);
        AndroidUtilities.logFlagSecure();
        return;
    L27:
        r2.getParentActivity().getWindow().addFlags(8192);
        AndroidUtilities.logFlagSecure();
        return;
    L41:
        return;
    L40:
        return;
    L10:
        r32 = true;
        goto L12
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean attachedToParent() {
        if (this.ATTACH_TO_FRAGMENT == true) goto L5;
    L8:
        return false;
    L5:
        if (this.windowView == null) goto L8;
        return true;
    }

    public void cancelSwipeToReply() {
        if (this.swipeToReplyBackAnimator != null) goto L6;
        this.inSwipeToDissmissMode = false;
        this.allowSwipeToReply = false;
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{this.swipeToReplyOffset, 0.0f});
        this.swipeToReplyBackAnimator = r0;
        r0.addUpdateListener(new StoryViewer$$ExternalSyntheticLambda13(this));
        this.swipeToReplyBackAnimator.addListener(new AnonymousClass8(this));
        this.swipeToReplyBackAnimator.setDuration(250);
        this.swipeToReplyBackAnimator.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        this.swipeToReplyBackAnimator.start();
        return;
    }

    public void cancelSwipeToViews(boolean r6) {
        if (this.swipeToViewsAnimator == null) goto L6;
        return;
    L6:
        if (this.realKeyboardHeight == 0) goto L9;
        AndroidUtilities.hideKeyboard(this.selfStoryViewsView);
        return;
    L9:
        float r1 = 0.0f;
        if (this.allowSelfStoriesView == false) goto L12;
    L15:
        this.locker.lock();
        if (r6 == true) goto L20;
        float r0 = this.selfStoriesViewsOffset;
        SelfStoryViewsView r2 = this.selfStoryViewsView;
        float r3 = r2.maxSelfStoriesViewsOffset;
        if (r0 != r3) goto L20;
        this.selfStoriesViewsOffset = r3 - 1.0f;
        r2.setOffset(r3 - 1.0f);
    L20:
        float r02 = this.selfStoriesViewsOffset;
        if (r6 == false) goto L23;
        r1 = this.selfStoryViewsView.maxSelfStoriesViewsOffset;
    L23:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
        this.swipeToViewsAnimator = r03;
        r03.addUpdateListener(new StoryViewer$$ExternalSyntheticLambda5(this));
        this.swipeToViewsAnimator.addListener(new AnonymousClass7(this, r6));
        ValueAnimator r04 = this.swipeToViewsAnimator;
        if (r6 == false) goto L26;
        r04.setDuration(350);
        this.swipeToViewsAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
    L27:
        this.swipeToViewsAnimator.start();
        return;
    L26:
        r04.setDuration(350);
        this.swipeToViewsAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        goto L27
    L12:
        if (this.selfStoriesViewsOffset != 0.0f) goto L15;
    }

    public void checkNavBarColor() {
        if (this.ATTACH_TO_FRAGMENT == false) goto L8;
        LaunchActivity r1 = LaunchActivity.instance;
        if (r1 == null) goto L9;
        r1.checkSystemBarColors(true, true, true);
        return;
    L9:
        return;
    }

    public void checkSelfStoriesView() {
        int r1 = 0;
        if (this.selfStoryViewsView != null) goto L5;
        SelfStoryViewsView r0 = new SelfStoryViewsView(this.containerView.getContext(), this);
        this.selfStoryViewsView = r0;
        this.containerView.addView(r0, 0);
    L5:
        PeerStoriesView r02 = this.storiesViewPager.getCurrentPeerView();
        if (r02 != null) goto L8;
        return;
    L8:
        if (this.storiesList == null) goto L15;
        ArrayList<TL_stories.StoryItem> r2 = new ArrayList();
    L11:
        if (r1 >= this.storiesList.messageObjects.size()) goto L13;
        r2.add(this.storiesList.messageObjects.get(r1).storyItem);
        r1 = r1 + 1;
        goto L11
    L13:
        this.selfStoryViewsView.setItems(this.storiesList.dialogId, r2, r02.getListPosition());
        return;
    L15:
        this.selfStoryViewsView.setItems(r02.getCurrentPeer(), r02.getStoryItems(), r02.getSelectedPosition());
    }

    public void clearDraft(long r3, TL_stories.StoryItem r5) {
        if (r3 == 0) goto L8;
        if (r5 == null) goto L9;
        replyDrafts.remove(draftHash(r3, r5));
        return;
    L9:
        return;
    }

    public void close(boolean r2) {
        AndroidUtilities.hideKeyboard(this.windowView);
        this.isClosed = true;
        this.invalidateOutRect = true;
        updatePlayingMode();
        requestAudioFocus(false);
        startCloseAnimation(r2);
        if (this.unreadStateChanged == false) goto L6;
        this.unreadStateChanged = false;
        return;
    }

    public boolean closeKeyboardOrEmoji() {
        StoriesViewPager r1 = this.storiesViewPager;
        if (r1 != null) goto L5;
        return false;
    L5:
        PeerStoriesView r12 = r1.getCurrentPeerView();
        if (r12 != null) goto L8;
        return false;
    L8:
        return r12.closeKeyboardOrEmoji();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r8, int r9, Object... r10) {
        int r0 = 0;
        if (r8 != NotificationCenter.storiesListUpdated) goto L19;
        if (this.storiesList != ((StoriesController.StoriesList) r10[0])) goto L86;
        getCurrentPeerView();
        StoriesViewPager r82 = this.storiesViewPager;
        StoriesController.StoriesList r92 = this.storiesList;
        r82.setDays(r92.dialogId, r92.getDays(), this.currentAccount);
        SelfStoryViewsView r83 = this.selfStoryViewsView;
        if (r83 == null) goto L87;
        TL_stories.StoryItem r84 = r83.getSelectedStory();
        ArrayList<TL_stories.StoryItem> r93 = new ArrayList();
        int r102 = 0;
    L10:
        if (r0 >= this.storiesList.messageObjects.size()) goto L16;
        if (r84 == null) goto L15;
        if (r84.id != this.storiesList.messageObjects.get(r0).storyItem.id) goto L15;
        r102 = r0;
    L15:
        r93.add(this.storiesList.messageObjects.get(r0).storyItem);
        r0 = r0 + 1;
        goto L10
    L16:
        this.selfStoryViewsView.setItems(this.storiesList.dialogId, r93, r102);
        return;
    L87:
        return;
    L86:
        return;
    L19:
        if (r8 != NotificationCenter.storiesUpdated) goto L49;
        PlaceProvider r85 = this.placeProvider;
        if ((r85 instanceof StoriesListPlaceProvider) == false) goto L45;
        StoriesListPlaceProvider r86 = (StoriesListPlaceProvider) r85;
        if (r86.hasPaginationParams == true) goto L25;
        return;
    L25:
        if (r86.onlySelfStories == true) goto L96;
        StoriesController r94 = MessagesController.getInstance(this.currentAccount).getStoriesController();
        if (r86.hiddedStories == false) goto L30;
        ArrayList<TL_stories.PeerStories> r103 = r94.getHiddenList();
    L31:
        ArrayList<Long> r2 = this.storiesViewPager.getDialogIds();
        boolean r3 = false;
    L33:
        if (r0 >= r103.size()) goto L43;
        long r4 = DialogObject.getPeerDialogId(r103.get(r0).peer);
        if (r86.onlyUnreadStories == false) goto L40;
        if (r94.hasUnreadStories(r4) == true) goto L40;
    L42:
        r0 = r0 + 1;
    L40:
        if (r2.contains(Long.valueOf(r4)) == true) goto L42;
        r2.add(Long.valueOf(r4));
        r3 = true;
        goto L42
    L43:
        if (r3 == false) goto L45;
        this.storiesViewPager.getAdapter().notifyDataSetChanged();
        goto L45
    L30:
        r103 = r94.getDialogListStories();
        goto L31
    L96:
        return;
    L45:
        SelfStoryViewsView r7 = this.selfStoryViewsView;
        if (r7 == null) goto L89;
        r7.selfStoriesPreviewView.update();
        return;
    L89:
        return;
    L49:
        int r95 = NotificationCenter.openArticle;
        if (r8 != r95) goto L52;
    L64:
        updatePlayingMode();
        if (r8 != r95) goto L73;
        VideoPlayerHolder r87 = this.playerHolder;
        if (r87 == null) goto L70;
        this.playerSavedPosition = r87.currentPosition;
        this.playerHolder.release(null);
        this.playerHolder = null;
        return;
    L70:
        this.playerSavedPosition = 0;
        return;
    L73:
        if (this.paused == false) goto L75;
        return;
    L75:
        if (getCurrentPeerView() == null) goto L95;
        getCurrentPeerView().updatePosition();
        return;
    L95:
        return;
    L52:
        if (r8 == NotificationCenter.articleClosed) goto L64;
        if (r8 != NotificationCenter.storyDeleted) goto L90;
        long r88 = ((Long) r10[0]).longValue();
        int r104 = ((Integer) r10[1]).intValue();
        TL_stories.StoryItem r02 = this.singleStory;
        if (r02 != null) goto L59;
        return;
    L59:
        if (r02.dialogId == r88) goto L61;
        return;
    L61:
        if (r02.id != r104) goto L93;
        this.singleStoryDeleted = true;
        return;
    L93:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet, android.content.DialogInterface
    public void dismiss() {
        close(true);
    }

    public void dismissVisibleDialogs() {
        Dialog r0 = this.currentDialog;
        if (r0 == null) goto L5;
        r0.dismiss();
    L5:
        BaseFragment.AttachedSheet r02 = this.currentSheet;
        if (r02 == null) goto L8;
        r02.dismiss();
    L8:
        PeerStoriesView r1 = getCurrentPeerView();
        if (r1 == null) goto L20;
        ReactionsContainerLayout r03 = r1.reactionsContainerLayout;
        if (r03 != null) goto L13;
    L15:
        ShareAlert r04 = r1.shareAlert;
        if (r04 == null) goto L18;
        r04.dismiss();
    L18:
        r1.needEnterText();
        return;
    L13:
        if (r03.getReactionsWindow() == null) goto L15;
        r1.reactionsContainerLayout.getReactionsWindow().dismiss();
        goto L15
    }

    public void dispatchKeyEvent(KeyEvent r3) {
        if (r3.getKeyCode() != 24) goto L5;
    L8:
        dispatchVolumeEvent(r3);
        return;
    L5:
        if (r3.getKeyCode() == 25) goto L8;
    }

    public void doOnAnimationReady(Runnable r1) {
        if (r1 == null) goto L5;
        this.doOnAnimationReadyRunnables.add(r1);
        return;
    }

    public FrameLayout getContainerForBulletin() {
        PeerStoriesView r0 = this.storiesViewPager.getCurrentPeerView();
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.storyContainer;
    }

    public PeerStoriesView getCurrentPeerView() {
        StoriesViewPager r0 = this.storiesViewPager;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getCurrentPeerView();
    }

    public CharSequence getDraft(long r3, TL_stories.StoryItem r5) {
        if (r3 == 0) goto L8;
        if (r5 == null) goto L8;
        return replyDrafts.get(draftHash(r3, r5), _UrlKt.FRAGMENT_ENCODE_SET);
    L8:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public int getNavigationBarColor(int r2) {
        return ColorUtils.blendARGB(r2, -16777216, getBlackoutAlpha());
    }

    public float getProgressToSelfViews() {
        SelfStoryViewsView r0 = this.selfStoryViewsView;
        if (r0 != null) goto L7;
        return 0.0f;
    L7:
        return r0.progressToOpen;
    }

    public Theme.ResourcesProvider getResourceProvider() {
        return this.resourcesProvider;
    }

    public boolean getStoryRect(RectF r8) {
        StoriesViewPager r0 = this.storiesViewPager;
        if (r0 != null) goto L5;
        return false;
    L5:
        PeerStoriesView r02 = r0.getCurrentPeerView();
        if (r02 != null) goto L8;
    L20:
        return false;
    L8:
        if (r02.storyContainer == null) goto L20;
        SizeNotifierFrameLayout r1 = this.windowView;
        float r2 = 0.0f;
        if (r1 != null) goto L13;
        float r12 = 0.0f;
    L14:
        SizeNotifierFrameLayout r3 = this.windowView;
        if (r3 == null) goto L18;
        r2 = r3.getY();
    L18:
        r8.set((((this.swipeToDismissHorizontalOffset + r12) + this.containerView.getLeft()) + r02.getX()) + r02.storyContainer.getX(), (((this.swipeToDismissOffset + r2) + this.containerView.getTop()) + r02.getY()) + r02.storyContainer.getY(), (((r12 + this.swipeToDismissHorizontalOffset) + this.containerView.getRight()) - (this.containerView.getWidth() - r02.getRight())) - (r02.getWidth() - r02.storyContainer.getRight()), (((r2 + this.swipeToDismissOffset) + this.containerView.getBottom()) - (this.containerView.getHeight() - r02.getBottom())) - (r02.getHeight() - r02.storyContainer.getBottom()));
        return true;
    L13:
        r12 = r1.getX();
        goto L14
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public View getWindowView() {
        return this.windowView;
    }

    public void instantClose() {
        if (this.isShowing == false) goto L26;
        AndroidUtilities.hideKeyboard(this.windowView);
        this.isClosed = true;
        this.fullyVisible = false;
        this.progressToOpen = 0.0f;
        this.progressToDismiss = 0.0f;
        updatePlayingMode();
        this.fromY = 0.0f;
        this.fromX = 0.0f;
        ImageReceiver r2 = this.transitionViewHolder.avatarImage;
        if (r2 == null) goto L8;
        r2.setVisible(true, true);
    L8:
        ImageReceiver r22 = this.transitionViewHolder.storyImage;
        if (r22 == null) goto L11;
        r22.setVisible(true, true);
    L11:
        TransitionViewHolder r0 = this.transitionViewHolder;
        r0.storyImage = null;
        r0.avatarImage = null;
        HwFrameLayout r02 = this.containerView;
        if (r02 == null) goto L14;
        r02.disableHwAcceleration();
    L14:
        this.locker.unlock();
        PeerStoriesView.VideoPlayerSharedScope r03 = this.currentPlayerScope;
        if (r03 == null) goto L17;
        r03.invalidate();
    L17:
        release();
        if (this.ATTACH_TO_FRAGMENT == false) goto L20;
        AndroidUtilities.removeFromParent(this.windowView);
    L21:
        this.windowView = null;
        this.isShowing = false;
        this.foundViewToClose = false;
        checkNavBarColor();
        Runnable r04 = this.onCloseListener;
        if (r04 == null) goto L25;
        r04.run();
        this.onCloseListener = null;
        return;
    L25:
        return;
    L20:
        this.windowManager.removeView(this.windowView);
        goto L21
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean isAttachedLightStatusBar() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean isFullyVisible() {
        return this.fullyVisible;
    }

    public boolean isPaused() {
        if (this.isPopupVisible == false) goto L5;
        return true;
    L5:
        if (this.isTranslating == false) goto L7;
        return true;
    L7:
        if (this.isBulletinVisible == false) goto L9;
        return true;
    L9:
        if (this.isCaption == false) goto L11;
        return true;
    L11:
        if (this.isWaiting == false) goto L13;
        return true;
    L13:
        if (this.isInTouchMode == false) goto L15;
        return true;
    L15:
        if (this.keyboardVisible == false) goto L17;
        return true;
    L17:
        if (this.currentDialog == null) goto L19;
        return true;
    L19:
        if (this.currentSheet == null) goto L21;
        return true;
    L21:
        if (this.allowTouchesByViewpager == false) goto L23;
        return true;
    L23:
        if (this.isClosed == false) goto L25;
        return true;
    L25:
        if (this.isRecording == false) goto L27;
        return true;
    L27:
        if (this.progressToOpen == 1.0f) goto L29;
        return true;
    L29:
        if (this.selfStoriesViewsOffset == 0.0f) goto L31;
        return true;
    L31:
        if (this.isHintVisible == false) goto L33;
        return true;
    L33:
        if (this.isSwiping == false) goto L37;
        if (this.USE_SURFACE_VIEW == false) goto L37;
        return true;
    L37:
        if (this.isOverlayVisible == false) goto L39;
        return true;
    L39:
        if (this.isInTextSelectionMode == false) goto L41;
        return true;
    L41:
        if (this.isLikesReactions == false) goto L43;
        return true;
    L43:
        if (this.progressToDismiss == 0.0f) goto L45;
        return true;
    L45:
        if (this.storiesIntro == null) goto L47;
        return true;
    L47:
        if (this.audioFocusPaused == false) goto L49;
        return true;
    L49:
        if (this.ATTACH_TO_FRAGMENT == false) goto L55;
        BaseFragment r0 = this.fragment;
        if (r0 != null) goto L53;
        return false;
    L53:
        if (r0.getLastStoryViewer() != this) goto L82;
        return false;
    L82:
        return true;
    L55:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean isShown() {
        return !this.isClosed;
    }

    public boolean listenToAttachedSheet(BaseFragment.AttachedSheet r2) {
        this.currentSheet = r2;
        r2.setOnDismissListener(new StoryViewer$$ExternalSyntheticLambda2(this));
        return true;
    }

    public void onActivityResult(int r1, int r2, Intent r3) {
        PeerStoriesView r0 = this.storiesViewPager.getCurrentPeerView();
        if (r0 == null) goto L6;
        r0.onActivityResult(r1, r2, r3);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean onAttachedBackPressed() {
        if (this.selfStoriesViewsOffset == 0.0f) goto L10;
        if (this.selfStoryViewsView.onBackPressed() == false) goto L7;
        return true;
    L7:
        cancelSwipeToViews(false);
        return true;
    L10:
        if (closeKeyboardOrEmoji() == false) goto L12;
        return true;
    L12:
        close(true);
        return true;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int r2) {
        AndroidUtilities.runOnUIThread(new StoryViewer$$ExternalSyntheticLambda4(this, r2));
    }

    public void onPause() {
        this.paused = true;
        VideoPlayerHolder r0 = this.playerHolder;
        if (r0 == null) goto L6;
        r0.release(null);
        this.playerHolder = null;
    L6:
        if (this.pipLiveView != null) goto L17;
        LivePlayerView r02 = this.liveView;
        if (r02 == null) goto L10;
        r02.setScope(0, null);
    L10:
        LivePlayer r03 = this.livePlayer;
        if (r03 == null) goto L17;
        if (r03.outgoing == false) goto L15;
        r03.setDisplaySink(null);
    L16:
        this.livePlayer = null;
        goto L17
    L15:
        r03.destroy();
    L17:
        StoriesIntro r04 = this.storiesIntro;
        if (r04 == null) goto L20;
        r04.stopAnimation();
    L20:
        requestAudioFocus(false);
    }

    public void onResume() {
        this.paused = false;
        if (ArticleViewer.getInstance().isVisible() == false) goto L5;
    L7:
        StoriesIntro r2 = this.storiesIntro;
        if (r2 == null) goto L11;
        r2.startAnimation(false);
    L11:
        if (LiveStoryPipOverlay.isVisible() == false) goto L14;
        LiveStoryPipOverlay.dismiss();
        return;
    L14:
        return;
    L5:
        if (getCurrentPeerView() == null) goto L7;
        getCurrentPeerView().updatePosition();
        goto L7
    }

    public void open(int r21, Context r22, TL_stories.StoryItem r23, ArrayList<Long> r24, int r25, StoriesController.StoriesList r26, TL_stories.PeerStories r27, PlaceProvider r28, boolean r29) {
        if (AndroidUtilities.isContextSafe(r22) == true) goto L6;
        this.doOnAnimationReadyRunnables.clear();
        return;
    L6:
        ValueAnimator r4 = this.openCloseAnimator;
        if (r4 == null) goto L10;
        r4.cancel();
        this.openCloseAnimator = null;
    L10:
        if (this.isShowing == false) goto L13;
        this.doOnAnimationReadyRunnables.clear();
        return;
    L13:
        setSpeed(1.0f);
        if (AndroidUtilities.isTablet() == false) goto L16;
    L18:
        boolean r42 = false;
    L19:
        this.ATTACH_TO_FRAGMENT = r42;
        if (SharedConfig.useSurfaceInStories == false) goto L23;
        if (r42 == false) goto L23;
        boolean r43 = true;
    L24:
        this.USE_SURFACE_VIEW = r43;
        if (r23 != null) goto L27;
        int r44 = 0;
    L28:
        this.messageId = r44;
        if (r23 == null) goto L33;
        if (r26 != null) goto L33;
        if (r27 != null) goto L33;
        boolean r45 = true;
    L34:
        this.isSingleStory = r45;
        this.singleStoryDeleted = false;
        if (r23 == null) goto L37;
        this.singleStory = r23;
        lastStoryItem = r23;
    L37:
        this.storiesList = r26;
        this.overrideUserStories = r27;
        this.placeProvider = r28;
        this.reversed = r29;
        this.currentAccount = r21;
        this.swipeToDismissOffset = 0.0f;
        this.swipeToDismissHorizontalOffset = 0.0f;
        StoriesViewPager r0 = this.storiesViewPager;
        if (r0 == null) goto L40;
        r0.setHorizontalProgressToDismiss(0.0f);
        this.storiesViewPager.currentState = 0;
    L40:
        this.swipeToReplyProgress = 0.0f;
        this.swipeToReplyOffset = 0.0f;
        this.allowSwipeToReply = false;
        this.progressToDismiss = 0.0f;
        this.isShowing = true;
        this.isLongpressed = false;
        this.isTranslating = false;
        this.savedPositions.clear();
        AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
        WindowManager.LayoutParams r02 = new WindowManager.LayoutParams();
        this.windowLayoutParams = r02;
        r02.height = -1;
        r02.format = -3;
        r02.width = -1;
        r02.gravity = 51;
        r02.type = 99;
        r02.softInputMode = 16;
        AndroidUtilities.applyEdgeToEdgeLayoutParams(r02);
        this.windowLayoutParams.flags = -2147417728;
        this.isClosed = false;
        requestAudioFocus(false);
        this.unreadStateChanged = false;
        BaseFragment r12 = LaunchActivity.getLastFragment();
        if (this.windowView != null) goto L44;
        this.gestureDetector = new GestureDetector(new AnonymousClass1(this));
        this.windowView = new AnonymousClass2(this, r22, r12);
    L44:
        if (this.containerView != null) goto L50;
        this.containerView = new AnonymousClass3(this, r22);
        AnonymousClass4 r03 = new AnonymousClass4(this, this.currentAccount, r22, this, this.resourcesProvider);
        this.storiesViewPager = r03;
        r03.setDelegate(new AnonymousClass5(this, r26, r24, r22));
        this.containerView.addView(this.storiesViewPager, LayoutHelper.createFrame(-1, -1, 1));
        this.aspectRatioFrameLayout = new AspectRatioFrameLayout(r22);
        if (this.USE_SURFACE_VIEW == false) goto L48;
        SurfaceView r04 = new SurfaceView(r22);
        this.surfaceView = r04;
        r04.setZOrderMediaOverlay(false);
        this.surfaceView.setZOrderOnTop(false);
        this.aspectRatioFrameLayout.addView(this.surfaceView);
    L49:
        LivePlayerView r05 = new LivePlayerView(r22, this.currentAccount, false);
        this.liveView = r05;
        r05.setVisibility(8);
        this.aspectRatioFrameLayout.addView(this.liveView);
        StoriesVolumeControl r06 = new StoriesVolumeControl(r22);
        this.volumeControl = r06;
        this.containerView.addView(r06, LayoutHelper.createFrame(-1, -1.0f, 0, 4.0f, 0.0f, 4.0f, 0.0f));
        goto L50
    L48:
        AnonymousClass6 r07 = new AnonymousClass6(this, r22);
        this.textureView = r07;
        this.aspectRatioFrameLayout.addView(r07);
    L50:
        LivePlayerView r08 = this.liveView;
        if (r08 == null) goto L53;
        r08.setAccount(this.currentAccount);
    L53:
        AndroidUtilities.removeFromParent(this.aspectRatioFrameLayout);
        this.windowView.addView(this.aspectRatioFrameLayout);
        SurfaceView r09 = this.surfaceView;
        if (r09 == null) goto L56;
        r09.setVisibility(4);
    L56:
        AndroidUtilities.removeFromParent(this.containerView);
        this.windowView.addView(this.containerView);
        this.windowView.setClipChildren(false);
        if (this.isSingleStory == false) goto L59;
        updateTransitionParams();
    L59:
        StoriesViewPager r010 = this.storiesViewPager;
        if (r26 == null) goto L62;
        r010.setDays(r26.dialogId, r26.getDays(), this.currentAccount);
    L63:
        this.windowManager = (WindowManager) r22.getSystemService("window");
        if (r12 != null) goto L66;
    L69:
        this.ATTACH_TO_FRAGMENT = false;
    L71:
        if (this.ATTACH_TO_FRAGMENT == false) goto L76;
        if (r12 == null) goto L76;
        if (r12.isSupportEdgeToEdge() == false) goto L76;
        boolean r011 = true;
    L77:
        this.ATTACHED_FRAGMENT_IS_EDGE_TO_EDGE = r011;
        ViewCompat.setOnApplyWindowInsetsListener(this.containerView, new StoryViewer$$ExternalSyntheticLambda6(this));
        boolean r012 = this.ATTACH_TO_FRAGMENT;
        SizeNotifierFrameLayout r2 = this.windowView;
        if (r012 == false) goto L82;
        AndroidUtilities.removeFromParent(r2);
        this.windowView.setTag(R.id.sheet_attached_to_fragment_tag, new Object());
        r12.getLayoutContainer().addView(this.windowView);
        if (this.ATTACHED_FRAGMENT_IS_EDGE_TO_EDGE == true) goto L87;
        AndroidUtilities.requestAdjustResize(r12.getParentActivity(), r12.getClassGuid());
    L87:
        this.windowView.requestLayout();
        runOpenAnimationAfterLayout = true;
        updateTransitionParams();
        this.progressToOpen = 0.0f;
        checkNavBarColor();
        animationInProgress = true;
        checkInSilentMode();
        if (this.ATTACH_TO_FRAGMENT == false) goto L91;
        lockOrientation(true);
    L91:
        if (this.ATTACH_TO_FRAGMENT == true) goto L93;
        globalInstances.add(this);
    L93:
        if (r12 == null) goto L96;
        AndroidUtilities.hideKeyboard(r12.getFragmentView());
        return;
    L96:
        return;
    L82:
        r2.setFocusable(false);
        this.containerView.setFocusable(false);
        this.containerView.setSystemUiVisibility(1792);
        AndroidUtilities.setPreferredMaxRefreshRate(this.windowManager, this.windowView, this.windowLayoutParams);
        this.windowManager.addView(this.windowView, this.windowLayoutParams);
        if (Build.VERSION.SDK_INT < 33) goto L87;
        OnBackInvokedDispatcher r013 = this.windowView.findOnBackInvokedDispatcher();
        if (r013 == null) goto L87;
        r013.registerOnBackInvokedCallback(0, new StoryViewer$$ExternalSyntheticLambda7(this));
    L76:
        r011 = false;
        goto L77
    L66:
        if (r12.getLayoutContainer() == null) goto L69;
        if (r12.isSupportEdgeToEdge() == false) goto L71;
    L62:
        r010.setPeerIds(r24, this.currentAccount, r25);
    L33:
        r45 = false;
        goto L34
    L27:
        r44 = r23.messageId;
    L23:
        r43 = false;
        goto L24
    L16:
        if (this.fromBottomSheet == true) goto L18;
        r42 = true;
        goto L19
    }

    public void open(int r12, Context r13, TL_stories.StoryItem r14, PlaceProvider r15) {
        if (r14 == null) goto L16;
        this.currentAccount = r12;
        if (r14.dialogId <= 0) goto L10;
        if (MessagesController.getInstance(r12).getUser(Long.valueOf(r14.dialogId)) != null) goto L10;
        return;
    L10:
        if (r14.dialogId < 0) goto L12;
    L14:
        ArrayList<Long> r5 = new ArrayList();
        r5.add(Long.valueOf(r14.dialogId));
        open(r12, r13, r14, r5, 0, null, null, r15, false);
        return;
    L12:
        if (MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r14.dialogId)) != null) goto L14;
        return;
    }

    public void open(Context r11, int r12, StoriesController.StoriesList r13, PlaceProvider r14) {
        this.currentAccount = UserConfig.selectedAccount;
        ArrayList<Long> r4 = new ArrayList();
        r4.add(Long.valueOf(r13.dialogId));
        this.dayStoryId = r12;
        open(r11, null, r4, 0, r13, null, r14, false);
    }

    public void open(Context r11, long r12, PlaceProvider r14) {
        this.currentAccount = UserConfig.selectedAccount;
        ArrayList<Long> r4 = new ArrayList();
        r4.add(Long.valueOf(r12));
        MessagesController.getInstance(this.currentAccount).getStoriesController().checkExpiredStories(r12);
        open(r11, null, r4, 0, null, null, r14, false);
    }

    public void open(Context r11, TL_stories.PeerStories r12, PlaceProvider r13) {
        if (r12 == null) goto L10;
        ArrayList<TL_stories.StoryItem> r0 = r12.stories;
        if (r0 == null) goto L10;
        if (r0.isEmpty() == true) goto L10;
        this.currentAccount = UserConfig.selectedAccount;
        ArrayList<Long> r4 = new ArrayList();
        r4.add(Long.valueOf(DialogObject.getPeerDialogId(r12.peer)));
        open(r11, r12.stories.get(0), r4, 0, null, r12, r13, false);
        return;
    L10:
        this.doOnAnimationReadyRunnables.clear();
    }

    public void open(Context r10, TL_stories.StoryItem r11, int r12, StoriesController.StoriesList r13, boolean r14, PlaceProvider r15) {
        this.currentAccount = UserConfig.selectedAccount;
        ArrayList<Long> r3 = new ArrayList();
        r3.add(Long.valueOf(r13.dialogId));
        this.dayStoryId = r12;
        open(r10, r11, r3, 0, r13, null, r15, r14);
    }

    @SuppressLint({"WrongConstant"})
    public void open(Context r11, TL_stories.StoryItem r12, ArrayList<Long> r13, int r14, StoriesController.StoriesList r15, TL_stories.PeerStories r16, PlaceProvider r17, boolean r18) {
        open(UserConfig.selectedAccount, r11, r12, r13, r14, r15, r16, r17, r18);
    }

    public void open(Context r2, TL_stories.StoryItem r3, PlaceProvider r4) {
        open(UserConfig.selectedAccount, r2, r3, r4);
    }

    public void openFor(BaseFragment r5, RecyclerListView r6, ChatActionCell r7) {
        MessageObject r72 = r7.getMessageObject();
        if (r5 != null) goto L5;
        return;
    L5:
        if (r5.getContext() != null) goto L8;
        return;
    L8:
        if (r72.type != 24) goto L12;
        TLRPC.MessageMedia r0 = r72.messageOwner.media;
        TL_stories.StoryItem r1 = r0.storyItem;
        r1.dialogId = DialogObject.getPeerDialogId(r0.peer);
        r1.messageId = r72.getId();
        open(r5.getContext(), r72.messageOwner.media.storyItem, StoriesListPlaceProvider.of(r6));
        return;
    }

    public void openViews() {
        checkSelfStoriesView();
        AndroidUtilities.runOnUIThread(new StoryViewer$$ExternalSyntheticLambda14(this), 30);
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public View pipCreatePictureInPictureView() {
        LivePlayerView r0 = new LivePlayerView(this.liveView.getContext(), this.currentAccount, false);
        this.pipLiveView = r0;
        return r0;
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public Bitmap pipCreatePictureInPictureViewBitmap() {
        LivePlayerView r0 = this.pipLiveView;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.isAvailable() == true) goto L8;
        return null;
    L8:
        return this.pipLiveView.getBitmap();
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public Bitmap pipCreatePrimaryWindowViewBitmap() {
        LivePlayerView r0 = this.liveView;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.isAvailable() == true) goto L8;
        return null;
    L8:
        return this.liveView.getBitmap();
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public void pipHidePrimaryWindowView(Runnable r2) {
        LivePlayerView r0 = this.pipLiveView;
        if (r0 == null) goto L6;
        r0.setOnFirstFrameCallback(r2);
        this.livePlayer.setDisplaySink(this.pipLiveView.getSink());
    L6:
        if (this.ATTACH_TO_FRAGMENT == false) goto L8;
        AndroidUtilities.removeFromParent(this.windowView);
    L9:
        this.windowView.invalidate();
        return;
    L8:
        this.windowManager.removeView(this.windowView);
        goto L9
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public boolean pipIsAvailable() {
        BaseFragment r0 = this.fragment;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0 != null) goto L7;
    L18:
        return false;
    L7:
        if (getCurrentPeerView() == null) goto L18;
        if (AndroidUtilities.findActivity(this.fragment.getContext()) == null) goto L18;
        if (this.livePlayer == null) goto L18;
        if (this.liveView == null) goto L18;
        if (this.isClosed == true) goto L18;
        return true;
    }

    @Override // org.telegram.messenger.pip.source.IPipSourceDelegate
    public void pipShowPrimaryWindowView(Runnable r3) {
        LivePlayerView r0 = this.pipLiveView;
        if (r0 == null) goto L6;
        r0.setOnFirstFrameCallback(r3);
    L6:
        if (this.ATTACH_TO_FRAGMENT == false) goto L8;
        AndroidUtilities.removeFromParent(this.windowView);
        this.fragment.getLayoutContainer().addView(this.windowView);
    L9:
        LivePlayerView r32 = this.pipLiveView;
        if (r32 == null) goto L12;
        r32.release();
        this.pipLiveView = null;
    L12:
        this.windowView.invalidate();
        this.livePlayer.setDisplaySink(this.liveView.getSink());
        return;
    L8:
        this.windowManager.addView(this.windowView, this.windowLayoutParams);
        goto L9
    }

    public void presentFragment(BaseFragment r3) {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.ATTACH_TO_FRAGMENT == false) goto L8;
        r0.presentFragment(r3);
    L9:
        cleanupBeforeExit();
        return;
    L8:
        r0.presentFragment(r3);
        close(false);
        goto L9
    }

    public void release() {
        this.lastUri = null;
        setInTouchMode(false);
        allowScreenshots(true);
        VideoPlayerHolder r2 = this.playerHolder;
        if (r2 == null) goto L5;
        r2.release(null);
        this.playerHolder = null;
    L5:
        requestAudioFocus(false);
        LivePlayerView r22 = this.liveView;
        if (r22 == null) goto L8;
        r22.setScope(0, null);
    L8:
        LivePlayer r23 = this.livePlayer;
        if (r23 != null) goto L11;
    L16:
        this.livePlayer = null;
        int r24 = 0;
    L17:
        int r3 = this.preparedPlayers.size();
        ArrayList<VideoPlayerHolder> r4 = this.preparedPlayers;
        if (r24 >= r3) goto L20;
        r4.get(r24).release(null);
        r24 = r24 + 1;
        goto L17
    L20:
        r4.clear();
        MessagesController.getInstance(this.currentAccount).getStoriesController().stopAllPollers();
        if (this.ATTACH_TO_FRAGMENT == false) goto L23;
        lockOrientation(false);
    L23:
        BaseFragment r1 = this.fragment;
        if (r1 == null) goto L26;
        r1.removeSheet(this);
    L26:
        globalInstances.remove(this);
        this.doOnAnimationReadyRunnables.clear();
        this.selfStoriesViewsOffset = 0.0f;
        lastStoryItem = null;
        return;
    L11:
        if (LiveStoryPipOverlay.isVisible(r23) == true) goto L16;
        LivePlayer r25 = this.livePlayer;
        if (r25.outgoing == false) goto L15;
        r25.setDisplaySink(null);
        goto L16
    L15:
        r25.destroy();
        goto L16
    }

    public void saveDraft(long r3, TL_stories.StoryItem r5, CharSequence r6) {
        if (r3 == 0) goto L8;
        if (r5 == null) goto L9;
        replyDrafts.put(draftHash(r3, r5), r6);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void setKeyboardHeightFromParent(int r2) {
        if (this.realKeyboardHeight == r2) goto L8;
        this.realKeyboardHeight = r2;
        this.storiesViewPager.setKeyboardHeight(r2);
        this.storiesViewPager.requestLayout();
        SelfStoryViewsView r1 = this.selfStoryViewsView;
        if (r1 == null) goto L9;
        r1.setKeyboardHeight(r2);
        return;
    L9:
        return;
    }

    public void setOnCloseListener(Runnable r1) {
        this.onCloseListener = r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void setOnDismissListener(Runnable r1) {
    }

    public void setOverlayVisible(boolean r1) {
        this.isOverlayVisible = r1;
        updatePlayingMode();
    }

    public void setSelfStoriesViewsOffset(float r1) {
        this.selfStoriesViewsOffset = r1;
        PeerStoriesView r12 = this.storiesViewPager.getCurrentPeerView();
        if (r12 == null) goto L5;
        r12.invalidate();
    L5:
        HwFrameLayout r0 = this.containerView;
        if (r0 == null) goto L9;
        r0.invalidate();
        return;
    }

    public void setSpeed(float r1) {
        currentSpeed = r1;
        VideoPlayerHolder r0 = this.playerHolder;
        if (r0 == null) goto L6;
        r0.setSpeed(r1);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public boolean showDialog(Dialog r2) {
        this.currentDialog = r2;     // Catch: Throwable -> L5
        r2.setOnDismissListener(new StoryViewer$$ExternalSyntheticLambda9(this));     // Catch: Throwable -> L5
        r2.show();     // Catch: Throwable -> L5
        updatePlayingMode();     // Catch: Throwable -> L5
        return true;
    L5:
        th = move-exception;
        FileLog.e(th);
        this.currentDialog = null;
        return false;
    }

    public void showViewsAfterOpening() {
        this.showViewsAfterOpening = true;
    }

    public boolean soundEnabled() {
        return !isInSilentMode;
    }

    public void startActivityForResult(Intent r2, int r3) {
        if (this.fragment.getParentActivity() != null) goto L5;
        return;
    L5:
        this.fragment.getParentActivity().startActivityForResult(r2, r3);
    }

    public void switchByTap(boolean r3) {
        PeerStoriesView r0 = this.storiesViewPager.getCurrentPeerView();
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.switchToNext(r3) == false) goto L8;
        return;
    L8:
        if (this.storiesViewPager.switchToNext(r3) == true) goto L16;
        if (r3 == false) goto L12;
        close(true);
        return;
    L12:
        VideoPlayerHolder r2 = this.playerHolder;
        if (r2 == null) goto L19;
        r2.loopBack();
        return;
    L19:
        return;
    L16:
        this.storiesViewPager.lockTouchEvent(150);
    }

    public void switchToPip() {
        if (this.livePlayer == null) goto L13;
        BaseFragment r0 = this.fragment;
        if (r0 != null) goto L7;
        return;
    L7:
        if (this.liveView == null) goto L16;
        Activity r02 = AndroidUtilities.findActivity(r0.getContext());
        if (PipUtils.checkAnyPipPermissions(r02) == false) goto L15;
        LiveStoryPipOverlay.show(r02, this.livePlayer);
        dismiss();
        return;
    L15:
        return;
    L16:
        return;
    }

    public void toggleSilentMode() {
        boolean r0 = isInSilentMode;
        isInSilentMode = !r0;
        VideoPlayerHolder r1 = this.playerHolder;
        int r2 = 0;
        if (r1 == null) goto L6;
        r1.setAudioEnabled(r0, false);
    L6:
        if (r2 >= this.preparedPlayers.size()) goto L8;
        this.preparedPlayers.get(r2).setAudioEnabled(!isInSilentMode, true);
        r2 = r2 + 1;
        goto L6
    L8:
        PeerStoriesView r02 = this.storiesViewPager.getCurrentPeerView();
        if (r02 == null) goto L12;
        r02.sharedResources.setIconMuted(!soundEnabled(), true);
    L12:
        if (isInSilentMode == true) goto L17;
        this.volumeControl.unmute();
        return;
    }

    public void updatePlayingMode() {
        updatePipSource();
        if (this.storiesViewPager != null) goto L5;
        return;
    L5:
        LivePlayer r0 = this.livePlayer;
        boolean r1 = false;
        if (r0 != null) goto L8;
    L10:
        boolean r02 = false;
    L11:
        if (r02 == true) goto L15;
        if (this.playerHolder != null) goto L15;
    L32:
        boolean r03 = isPaused();
        if (this.ATTACH_TO_FRAGMENT == false) goto L38;
        if (this.fragment.isPaused() == false) goto L38;
        r03 = true;
    L38:
        if (ArticleViewer.getInstance().isVisible() == false) goto L40;
        r03 = true;
    L40:
        this.storiesViewPager.setPaused(r03);
        VideoPlayerHolder r3 = this.playerHolder;
        if (r3 == null) goto L45;
        if (r03 == false) goto L44;
        r3.pause();
        goto L45
    L44:
        r3.play(currentSpeed);
    L45:
        StoriesViewPager r04 = this.storiesViewPager;
        if (this.keyboardVisible == false) goto L48;
    L60:
        r04.enableTouch(r1);
        return;
    L48:
        if (this.isClosed == true) goto L60;
        if (this.isRecording == true) goto L60;
        if (this.isLongpressed == true) goto L60;
        if (this.isInPinchToZoom == true) goto L60;
        if (this.selfStoriesViewsOffset != 0.0f) goto L60;
        if (this.isInTextSelectionMode == true) goto L60;
        r1 = true;
    L15:
        if (this.isShowing == false) goto L32;
        if (this.isClosed == true) goto L32;
        if (r02 == true) goto L28;
        if (this.playerHolder.audioTrackState == 1) goto L28;
        if (this.hasAudioFocus == false) goto L32;
        if (this.playerHolder.audioTrackState != 2) goto L32;
        requestAudioFocus(false);
    L28:
        if (this.hasAudioFocus == true) goto L32;
        if (this.audioFocusPaused == true) goto L32;
        requestAudioFocus(true);
        goto L32
    L8:
        if (r0.outgoing == true) goto L10;
        r02 = true;
        goto L11
    }
}
