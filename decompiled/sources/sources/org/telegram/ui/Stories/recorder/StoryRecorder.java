package org.telegram.ui.Stories.recorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.util.Pair;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.exteragram.messenger.utils.system.SystemUtils;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.camera.CameraController;
import org.telegram.messenger.camera.CameraView;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.messenger.utils.WindowVisibilityManager;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.AccountFrozenAlert;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.AvatarSpan;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.ShareDialogCell;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.Crop.CropRotationWheel;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.EditTextEmoji;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.FilterGLThread;
import org.telegram.ui.Components.FilterShaders;
import org.telegram.ui.Components.GestureDetectorFixDoubleTap;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.RenderView;
import org.telegram.ui.Components.Paint.Views.EntitiesContainerView;
import org.telegram.ui.Components.Paint.Views.EntityView;
import org.telegram.ui.Components.Paint.Views.MessageEntityView;
import org.telegram.ui.Components.Paint.Views.PhotoView;
import org.telegram.ui.Components.Paint.Views.RoundView;
import org.telegram.ui.Components.PermissionRequest;
import org.telegram.ui.Components.PhotoFilterBlurControl;
import org.telegram.ui.Components.PhotoFilterCurvesControl;
import org.telegram.ui.Components.PhotoFilterView;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RectOld;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.ThanosEffect;
import org.telegram.ui.Components.URLSpanUserMention;
import org.telegram.ui.Components.VideoEditTextureView;
import org.telegram.ui.Components.ZoomControlView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.DialogStoriesCell;
import org.telegram.ui.Stories.LivePlayer;
import org.telegram.ui.Stories.PeerStoriesView;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoryViewer;
import org.telegram.ui.Stories.StoryWaveEffectView;
import org.telegram.ui.Stories.recorder.CollageLayoutButton;
import org.telegram.ui.Stories.recorder.DownloadButton;
import org.telegram.ui.Stories.recorder.FlashViews;
import org.telegram.ui.Stories.recorder.PreviewButtons;
import org.telegram.ui.Stories.recorder.PreviewView;
import org.telegram.ui.Stories.recorder.QRScanner;
import org.telegram.ui.Stories.recorder.RecordControl;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;
import org.telegram.ui.Stories.recorder.TimelineView;
import org.telegram.ui.WrappedResourceProvider;

/* loaded from: classes3.dex */
public class StoryRecorder implements NotificationCenter.NotificationCenterDelegate {
    private static boolean firstOpen = true;
    private static StoryRecorder instance;
    private LinearLayout actionBarButtons;
    private FrameLayout actionBarContainer;
    private final Activity activity;
    private final WindowVisibilityManager.Controller activityVisibilityController;
    private Runnable afterPlayerAwait;
    private boolean animatedRecording;
    private boolean animatedRecordingWasInCheck;
    private boolean applyContainerViewTranslation2;
    private Runnable audioGrantedCallback;
    private boolean awaitingPlayer;
    private FlashViews.ImageViewInvertable backButton;
    private BlurringShader.BlurManager blurManager;
    private TLRPC.InputMedia botEdit;
    private long botId;
    private String botLang;
    private HintView2 cameraHint;
    private DualCameraView cameraView;
    private float cameraZoom;
    private boolean canChangePeer;
    private FrameLayout captionContainer;
    private CaptionStory captionEdit;
    private View captionEditOverlay;
    private View changeDayNightView;
    private ValueAnimator changeDayNightViewAnimator;
    private float changeDayNightViewProgress;
    private final Path clipPath;
    private Runnable closeListener;
    private ClosingViewProvider closingSourceProvider;
    private CollageLayoutButton collageButton;
    private HintTextView collageHintTextView;
    private CollageLayoutView2 collageLayoutView;
    private CollageLayoutButton.CollageLayoutListView collageListView;
    private ToggleButton2 collageRemoveButton;
    private ContainerView containerView;
    private ValueAnimator containerViewBackAnimator;
    private FrameLayout controlContainer;
    private ButtonWithCounterView coverButton;
    private TimelineView coverTimelineView;
    private long coverValue;
    private CropEditor cropEditor;
    private CropInlineEditor cropInlineEditor;
    private final int currentAccount;
    private int currentEditMode;
    private int currentPage;
    private RoundVideoRecorder currentRoundRecorder;
    private float dismissProgress;
    private DownloadButton downloadButton;
    private DraftSavedHint draftSavedHint;
    private ToggleButton dualButton;
    private HintView2 dualHint;
    private AnimatorSet editModeAnimator;
    private ArrayList<StoryEntry> entries;
    private boolean fastClose;
    private ToggleButton2 flashButton;
    private String flashButtonMode;
    private int flashButtonResId;
    private FlashViews flashViews;
    private boolean forceBackgroundVisible;
    private boolean fromGallery;
    private final RectF fromRect;
    private float fromRounding;
    private SourceView fromSourceView;
    private int frontfaceFlashMode;
    private ArrayList<String> frontfaceFlashModes;
    private Float frozenDismissProgress;
    private final RectF fullRectF;
    private boolean galleryClosing;
    private Runnable galleryLayouted;
    private GalleryListView galleryListView;
    private Boolean galleryListViewOpening;
    private ValueAnimator galleryOpenCloseAnimator;
    private SpringAnimation galleryOpenCloseSpringAnimator;
    private HintTextView hintTextView;
    private int insetBottom;
    private int insetLeft;
    private int insetRight;
    private int insetTop;
    private boolean isBackgroundVisible;
    private boolean isDark;
    private boolean isReposting;
    private boolean isShown;
    private CollageLayout lastCollageLayout;
    private Parcelable lastGalleryScrollPosition;
    private MediaController.AlbumEntry lastGallerySelectedAlbum;
    private TLRPC.InputPeer livePeer;
    private SelectPeerView livePeerView;
    private StoryPrivacyBottomSheet.StoryPrivacy livePrivacy;
    private FlashViews.ImageViewInvertable liveSettingsButton;
    private int mode;
    private StoryModeTabs modeSwitcherView;
    private RLottieImageView muteButton;
    private RLottieDrawable muteButtonDrawable;
    private HintView2 muteHint;
    private FrameLayout navbarContainer;
    private boolean noCameraPermission;
    private AnimationNotificationsLocker notificationsLocker;
    private Runnable onCloseListener;
    private Utilities.Callback4<Long, Runnable, Boolean, Long> onClosePrepareListener;
    private Runnable onFullyOpenListener;
    private ValueAnimator openCloseAnimator;
    private float openProgress;
    private int openType;
    private StoryEntry outputEntry;
    private File outputFile;
    private AnimatorSet pageAnimator;
    private PaintView paintView;
    private View paintViewEntitiesView;
    private View paintViewRenderInputView;
    private RenderView paintViewRenderView;
    private View paintViewSelectionContainerView;
    private View paintViewTextDim;
    private PhotoFilterView.EnhanceView photoFilterEnhanceView;
    private PhotoFilterView photoFilterView;
    private PhotoFilterBlurControl photoFilterViewBlurControl;
    private PhotoFilterCurvesControl photoFilterViewCurvesControl;
    private TextureView photoFilterViewTextureView;
    private PlayPauseButton playButton;
    private boolean prepareClosing;
    private boolean preparingUpload;
    private boolean previewAlreadySet;
    private PreviewButtons previewButtons;
    private FrameLayout previewContainer;
    private int previewH;
    private PreviewHighlightView previewHighlight;
    private Touchable previewTouchable;
    private PreviewView previewView;
    private int previewW;
    private StoryPrivacyBottomSheet privacySheet;
    private ScannedLinkPreview qrLinkView;
    private QRScanner qrScanner;
    private RecordControl recordControl;
    private final RecordControl.Delegate recordControlDelegate;
    private AnimatorSet recordingAnimator;
    private final Rect rect;
    private final RectF rectF;
    private HintView2 removeCollageHint;
    private boolean requestedCameraPermission;
    private final Theme.ResourcesProvider resourcesProvider;
    private FlashViews.ImageViewInvertable rotateButton;
    private HintView2 savedDualHint;
    private boolean scrollingX;
    private boolean scrollingY;
    long selectedDialogId;
    private ArrayList<Integer> selectedEntries;
    private ArrayList<Integer> selectedEntriesOrder;
    private int shiftDp;
    private boolean showSavedDraftHint;
    private boolean shownLimitReached;
    private ButtonWithCounterView startLiveButton;
    private boolean stoppingTakingVideo;
    private MultipleStoriesSelector storiesSelector;
    private boolean switchingStory;
    private boolean takingPhoto;
    private boolean takingVideo;
    private ThanosEffect thanosEffect;
    private ImageView themeButton;
    private RLottieDrawable themeButtonDrawable;
    private TimelineView timelineView;
    private SimpleTextView titleTextView;
    private TrashView trash;
    private int underControls;
    private boolean underStatusBar;
    private boolean videoError;
    private PreviewView.TextureViewHolder videoTextureHolder;
    private VideoTimeView videoTimeView;
    private FrameLayout videoTimelineContainerView;
    private boolean videoTimerShown;
    private VideoTimerView videoTimerView;
    private boolean wasGalleryOpen;
    private boolean wasSend;
    private long wasSendPeer;
    private Runnable whenOpenDone;
    private final WindowManager.LayoutParams windowLayoutParams;
    private final WindowManager windowManager;
    private WindowView windowView;
    private AnimatorSet zoomControlAnimation;
    private Runnable zoomControlHideRunnable;
    private ZoomControlView zoomControlView;

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ StoryRecorder this$0;
        final /* synthetic */ Runnable val$onDone;
        final /* synthetic */ float val$value;

        public AnonymousClass1(StoryRecorder r1, float r2, Runnable r3) {
            this.this$0 = r1;
            this.val$value = r2;
            this.val$onDone = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            StoryRecorder.m20497$$Nest$fputfrozenDismissProgress(this.this$0, null);
            StoryRecorder.m20507$$Nest$fputopenProgress(this.this$0, this.val$value);
            StoryRecorder.m20524$$Nest$mapplyOpenProgress(this.this$0);
            StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).invalidate();
            StoryRecorder.m20486$$Nest$fgetwindowView(this.this$0).invalidate();
            Runnable r42 = this.val$onDone;
            if (r42 == null) goto L6;
            r42.run();
        L6:
            if (StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0) == null) goto L8;
            StoryRecorder.m20485$$Nest$fgetwaveEffect(this.this$0);
        L8:
            StoryRecorder.m20442$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
            NotificationCenter.getGlobalInstance().runDelayedNotifications();
            StoryRecorder.m20525$$Nest$mcheckBackgroundVisibility(this.this$0);
            if (StoryRecorder.m20443$$Nest$fgetonFullyOpenListener(this.this$0) == null) goto L11;
            StoryRecorder.m20443$$Nest$fgetonFullyOpenListener(this.this$0).run();
            StoryRecorder.m20506$$Nest$fputonFullyOpenListener(this.this$0, null);
        L11:
            StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).invalidate();
            StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$10, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass10 extends View {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass10(StoryRecorder r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r4) {
            r4.save();
            r4.translate(StoryRecorder.m20400$$Nest$fgetcaptionContainer(this.this$0).getX() + StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).getX(), StoryRecorder.m20400$$Nest$fgetcaptionContainer(this.this$0).getY() + StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).getY());
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).drawOver2(r4, StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).getBounds(), StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).getOver2Alpha());
            r4.restore();
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$11, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass11 extends MultipleStoriesSelector {
        private final Path path;
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass11(StoryRecorder r1, Context r2, Theme.ResourcesProvider r3, BlurringShader.BlurManager r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
            this.path = new Path();
        }

        @Override // org.telegram.ui.Stories.recorder.MultipleStoriesSelector
        public boolean customBlur() {
            return StoryRecorder.m20394$$Nest$fgetblurManager(this.this$0).hasRenderNode();
        }

        @Override // org.telegram.ui.Stories.recorder.MultipleStoriesSelector
        public void drawBlur(BlurringShader.StoryBlurDrawer r1, Canvas r2, RectF r3, float r4, boolean r5, float r6, float r7, boolean r8, float r9) {
            if (r2.isHardwareAccelerated() == true) goto L5;
            return;
        L5:
            r2.save();
            this.path.rewind();
            this.path.addRoundRect(r3, r4, r4, Path.Direction.CW);
            r2.clipPath(this.path);
            r2.translate(r6, r7);
            r1.drawRect(r2, 0.0f, 0.0f, r9);
            r2.restore();
        }

        @Override // org.telegram.ui.Stories.recorder.MultipleStoriesSelector
        public void onSwitchToStory(int r3, StoryEntry r4) {
            showList(false, true);
            if (r4 == StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0)) goto L6;
            StoryRecorder.m20548$$Nest$mswitchTo(this.this$0, r3);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$12, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass12 extends StoryModeTabs {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass12(StoryRecorder r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Stories.recorder.StoryModeTabs
        public boolean allowTouch() {
            return !StoryRecorder.m20536$$Nest$minCheck(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$13, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass13 implements RecordControl.Delegate {
        final /* synthetic */ StoryRecorder this$0;

        /* renamed from: $r8$lambda$7YBzQWzDHtpc--uRXxDlL1HkJEo, reason: not valid java name */
        public static /* synthetic */ void m20552$r8$lambda$7YBzQWzDHtpcuRXxDlL1HkJEo(AnonymousClass13 r0, Utilities.Callback r1) {
            r0.takePicture(r1);
        }

        public static /* synthetic */ void $r8$lambda$NbRzAX6vg4JRPknEK_kDMaxJRkg(AnonymousClass13 r0, boolean r1, Runnable r2) {
            r0.lambda$onVideoRecordStart$3(r1, r2);
        }

        /* renamed from: $r8$lambda$av0cvAthB-DFcSOCDDY0NzKE_iU, reason: not valid java name */
        public static /* synthetic */ void m20553$r8$lambda$av0cvAthBDFcSOCDDY0NzKE_iU(AnonymousClass13 r0, Utilities.Callback r1, Integer r2) {
            r0.lambda$takePicture$1(r1, r2);
        }

        /* renamed from: $r8$lambda$dJEOgd0Eq4gNt1W8tm-eUuVFk6M, reason: not valid java name */
        public static /* synthetic */ void m20554$r8$lambda$dJEOgd0Eq4gNt1W8tmeUuVFk6M(AnonymousClass13 r0, String r1, long r2) {
            r0.lambda$startRecording$5(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$gZm1XK4FUlSMM7eK4Vsoqu7DDlU(AnonymousClass13 r0) {
            r0.lambda$takePicture$0();
        }

        public static /* synthetic */ void $r8$lambda$hgIhKent5nHX64l4JrykxeSvuhk(AnonymousClass13 r0) {
            r0.lambda$takePicture$2();
        }

        public static /* synthetic */ void $r8$lambda$mGIcVU4h06vdP6vfSx7HPNmLQrU(AnonymousClass13 r0) {
            r0.lambda$onVideoRecordEnd$7();
        }

        public static /* synthetic */ void $r8$lambda$qaCYR8paBG84W5e6jQhS4PlnuKI(AnonymousClass13 r0) {
            r0.lambda$startRecording$4();
        }

        public static /* synthetic */ void $r8$lambda$wSbuE_foWzQVtlcinIMDNfmxHR4(AnonymousClass13 r0, Runnable r1, boolean r2) {
            r0.lambda$startRecording$6(r1, r2);
        }

        public AnonymousClass13(StoryRecorder r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onVideoRecordEnd$7() {
            if (StoryRecorder.m20463$$Nest$fgetqrScanner(this.this$0) == null) goto L6;
            StoryRecorder.m20463$$Nest$fgetqrScanner(this.this$0).setPaused(false);
        L6:
            if (StoryRecorder.m20473$$Nest$fgettakingVideo(this.this$0) == true) goto L8;
            return;
        L8:
            if (StoryRecorder.m20471$$Nest$fgetstoppingTakingVideo(this.this$0) == true) goto L10;
            return;
        L10:
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0) == null) goto L15;
            StoryRecorder.m20547$$Nest$mshowZoomControls(this.this$0, false, true);
            CameraController.getInstance().stopVideoRecording(StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).getCameraSessionRecording(), false, false);
            return;
        }

        private /* synthetic */ void lambda$onVideoRecordStart$3(boolean r1, Runnable r2) {
            startRecording(r1, r2);
        }

        private /* synthetic */ void lambda$startRecording$4() {
            this.this$0.navigateTo(1, true);
        }

        private /* synthetic */ void lambda$startRecording$5(String r6, long r7) {
            if (StoryRecorder.m20464$$Nest$fgetrecordControl(this.this$0) == null) goto L6;
            StoryRecorder.m20464$$Nest$fgetrecordControl(this.this$0).stopRecordingLoading(true);
        L6:
            if (StoryRecorder.m20551$$Nest$museDisplayFlashlight(this.this$0) == false) goto L9;
            StoryRecorder.m20420$$Nest$fgetflashViews(this.this$0).flashOut();
        L9:
            if (StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0) != null) goto L11;
            return;
        L11:
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0) == null) goto L56;
            StoryRecorder.m20516$$Nest$fputtakingVideo(this.this$0, false);
            StoryRecorder.m20514$$Nest$fputstoppingTakingVideo(this.this$0, false);
            if (StoryRecorder.m20463$$Nest$fgetqrScanner(this.this$0) == null) goto L16;
            StoryRecorder.m20463$$Nest$fgetqrScanner(this.this$0).setPaused(false);
        L16:
            StoryRecorder r3 = this.this$0;
            if (r7 > 800) goto L30;
            StoryRecorder.m20522$$Nest$manimateRecording(r3, false, true);
            StoryRecorder.m20544$$Nest$msetAwakeLock(this.this$0, false);
            StoryRecorder.m20483$$Nest$fgetvideoTimerView(this.this$0).setRecording(false, true);
            if (StoryRecorder.m20464$$Nest$fgetrecordControl(this.this$0) == null) goto L51;
            StoryRecorder.m20464$$Nest$fgetrecordControl(this.this$0).stopRecordingLoading(true);
        L51:
            StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0).delete();     // Catch: Exception -> L23
            StoryRecorder.m20509$$Nest$fputoutputFile(this.this$0, null);     // Catch: Exception -> L23
        L25:
            if (r6 == null) goto L54;
            new File(r6).delete();     // Catch: Exception -> L28
            return;
        L28:
            e = move-exception;
            FileLog.e(e);
            return;
        L54:
            return;
        L23:
            e = move-exception;
            FileLog.e(e);
            goto L25
        L30:
            StoryRecorder.m20546$$Nest$mshowVideoTimer(r3, false, true);
            StoryEntry r62 = StoryEntry.fromVideoShoot(StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0), r6, r7);
            r62.botId = StoryRecorder.m20395$$Nest$fgetbotId(this.this$0);
            r62.botLang = StoryRecorder.m20396$$Nest$fgetbotLang(this.this$0);
            r62.setupMultipleStoriesSelector();
            StoryRecorder.m20522$$Nest$manimateRecording(this.this$0, false, true);
            StoryRecorder.m20544$$Nest$msetAwakeLock(this.this$0, false);
            StoryRecorder.m20483$$Nest$fgetvideoTimerView(this.this$0).setRecording(false, true);
            if (StoryRecorder.m20464$$Nest$fgetrecordControl(this.this$0) == null) goto L33;
            StoryRecorder.m20464$$Nest$fgetrecordControl(this.this$0).stopRecordingLoading(true);
        L33:
            boolean r72 = StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).hasLayout();
            StoryRecorder r8 = this.this$0;
            if (r72 == false) goto L43;
            StoryRecorder.m20509$$Nest$fputoutputFile(r8, null);
            r62.videoVolume = 1.0f;
            if (StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).push(r62) == false) goto L41;
            StoryRecorder r63 = this.this$0;
            StoryRecorder.m20508$$Nest$fputoutputEntry(r63, StoryEntry.asCollage(StoryRecorder.m20407$$Nest$fgetcollageLayoutView(r63).getLayout(), StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).getContent()));
            StoryPrivacySelector.applySaved(StoryRecorder.m20413$$Nest$fgetcurrentAccount(this.this$0), StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0));
            StoryRecorder.m20496$$Nest$fputfromGallery(this.this$0, false);
            int r64 = StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).getVideoWidth();
            int r73 = StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).getVideoHeight();
            if (r64 <= 0) goto L41;
            if (r73 <= 0) goto L41;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).width = r64;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).height = r73;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).setupMatrix();
        L41:
            StoryRecorder.m20549$$Nest$mupdateActionBarButtons(this.this$0, true);
            return;
        L43:
            StoryRecorder.m20508$$Nest$fputoutputEntry(r8, r62);
            StoryPrivacySelector.applySaved(StoryRecorder.m20413$$Nest$fgetcurrentAccount(this.this$0), StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0));
            StoryRecorder.m20496$$Nest$fputfromGallery(this.this$0, false);
            int r65 = StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).getVideoWidth();
            int r74 = StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).getVideoHeight();
            if (r65 <= 0) goto L47;
            if (r74 <= 0) goto L47;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).width = r65;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).height = r74;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).setupMatrix();
        L47:
            this.this$0.navigateToPreviewWithPlayerAwait(new StoryRecorder$13$$ExternalSyntheticLambda8(this), 0);
            return;
        }

        private /* synthetic */ void lambda$startRecording$6(Runnable r2, boolean r3) {
            r2.run();
            HintTextView r22 = StoryRecorder.m20434$$Nest$fgethintTextView(this.this$0);
            if (r3 == false) goto L5;
            int r32 = R.string.StoryHintSwipeToZoom;
        L6:
            r22.setText(LocaleController.getString(r32), false);
            StoryRecorder.m20522$$Nest$manimateRecording(this.this$0, true, true);
            StoryRecorder.m20544$$Nest$msetAwakeLock(this.this$0, true);
            StoryRecorder.m20408$$Nest$fgetcollageListView(this.this$0).setVisible(false, true);
            StoryRecorder.m20483$$Nest$fgetvideoTimerView(this.this$0).setRecording(true, true);
            StoryRecorder.m20546$$Nest$mshowVideoTimer(this.this$0, true, true);
            return;
        L5:
            r32 = R.string.StoryHintPinchToZoom;
            goto L6
        }

        private /* synthetic */ void lambda$takePicture$0() {
            this.this$0.navigateTo(1, true);
        }

        private /* synthetic */ void lambda$takePicture$1(Utilities.Callback r7, Integer r8) {
            if (StoryRecorder.m20551$$Nest$museDisplayFlashlight(this.this$0) == true) goto L52;
        L5:
            StoryRecorder.m20515$$Nest$fputtakingPhoto(this.this$0, false);
            if (StoryRecorder.m20463$$Nest$fgetqrScanner(this.this$0) == null) goto L9;
            StoryRecorder.m20463$$Nest$fgetqrScanner(this.this$0).setPaused(false);
        L9:
            if (StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0) != null) goto L50;
            return;
        L50:
            BitmapFactory.Options r3 = new BitmapFactory.Options();     // Catch: Exception -> L15
            r3.inJustDecodeBounds = true;     // Catch: Exception -> L15
            BitmapFactory.decodeFile(StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0).getAbsolutePath(), r3);     // Catch: Exception -> L15
            int r4 = r3.outWidth;     // Catch: Exception -> L15
            int r32 = r3.outHeight;     // Catch: Exception -> L47
        L18:
            if (r8.intValue() != (-1)) goto L20;
            int r5 = 0;
        L22:
            if (r8.intValue() != (-1)) goto L25;
            if (r4 <= r32) goto L28;
            r5 = 270;
        L28:
            StoryEntry r82 = StoryEntry.fromPhotoShoot(StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0), r5);
            if (r82 == null) goto L31;
            r82.botId = StoryRecorder.m20395$$Nest$fgetbotId(this.this$0);
            r82.botLang = StoryRecorder.m20396$$Nest$fgetbotLang(this.this$0);
        L31:
            boolean r0 = StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).hasLayout();
            StoryRecorder r33 = this.this$0;
            if (r0 == false) goto L41;
            StoryRecorder.m20509$$Nest$fputoutputFile(r33, null);
            if (StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).push(r82) == false) goto L38;
            StoryRecorder r83 = this.this$0;
            StoryRecorder.m20508$$Nest$fputoutputEntry(r83, StoryEntry.asCollage(StoryRecorder.m20407$$Nest$fgetcollageLayoutView(r83).getLayout(), StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).getContent()));
            StoryPrivacySelector.applySaved(StoryRecorder.m20413$$Nest$fgetcurrentAccount(this.this$0), StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0));
            StoryRecorder.m20496$$Nest$fputfromGallery(this.this$0, false);
            if (r7 == null) goto L40;
            r7.run(null);
        L40:
            StoryRecorder.m20549$$Nest$mupdateActionBarButtons(this.this$0, true);
            return;
        L38:
            if (r7 == null) goto L40;
            r7.run(null);
            goto L40
        L41:
            StoryRecorder.m20508$$Nest$fputoutputEntry(r33, r82);
            StoryPrivacySelector.applySaved(StoryRecorder.m20413$$Nest$fgetcurrentAccount(this.this$0), StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0));
            StoryRecorder.m20496$$Nest$fputfromGallery(this.this$0, false);
            if (r7 == null) goto L44;
            r7.run(new StoryRecorder$13$$ExternalSyntheticLambda7(this));
            return;
        L44:
            this.this$0.navigateTo(1, true);
            return;
        L25:
            if (r32 <= r4) goto L28;
            if (r5 == 0) goto L28;
            r5 = 0;
            goto L28
        L20:
            r5 = 90;
        L16:
            r32 = -1;
        L15:
            r4 = -1;
            goto L16
        L52:
            StoryRecorder.m20486$$Nest$fgetwindowView(this.this$0).performHapticFeedback(3, 1);     // Catch: Exception -> L46
            goto L5
        }

        private /* synthetic */ void lambda$takePicture$2() {
            this.this$0.navigateTo(1, true);
        }

        private void startRecording(boolean r10, Runnable r11) {
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0) == null) goto L18;
            CameraController.getInstance().recordVideo(StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).getCameraSessionObject(), StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0), false, new StoryRecorder$13$$ExternalSyntheticLambda3(this), new StoryRecorder$13$$ExternalSyntheticLambda4(this, r11, r10), StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0), true);
            boolean r112 = true;
            if (StoryRecorder.m20439$$Nest$fgetmode(this.this$0) == 1) goto L17;
            StoryRecorder.m20505$$Nest$fputmode(this.this$0, 1);
            StoryRecorder.m20408$$Nest$fgetcollageListView(this.this$0).setVisible(false, true);
            StoryRecorder r102 = this.this$0;
            if (StoryRecorder.m20439$$Nest$fgetmode(r102) != 1) goto L10;
            boolean r1 = true;
        L11:
            StoryRecorder.m20546$$Nest$mshowVideoTimer(r102, r1, true);
            StoryRecorder.m20440$$Nest$fgetmodeSwitcherView(this.this$0).switchMode(StoryRecorder.m20439$$Nest$fgetmode(this.this$0));
            RecordControl r103 = StoryRecorder.m20464$$Nest$fgetrecordControl(this.this$0);
            if (StoryRecorder.m20439$$Nest$fgetmode(this.this$0) == 1) goto L15;
            r112 = false;
        L15:
            r103.startAsVideo(r112);
            return;
        L10:
            r1 = false;
            goto L11
        L17:
            return;
        }

        private void takePicture(Utilities.Callback<Runnable> r7) {
            if (StoryRecorder.m20551$$Nest$museDisplayFlashlight(this.this$0) == true) goto L6;
            StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).startTakePictureAnimation(true);
        L6:
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).isDual() == false) goto L10;
            if (TextUtils.equals(StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).getCameraSession().getCurrentFlashMode(), "off") == false) goto L10;
        L12:
            if (StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).hasLayout() == true) goto L14;
            StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).pauseAsTakingPicture();
        L14:
            Bitmap r0 = StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).getTextureView().getBitmap();
            FileOutputStream r3 = new FileOutputStream(StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0).getAbsoluteFile());     // Catch: Exception -> L27
            r0.compress(Bitmap.CompressFormat.JPEG, 100, r3);     // Catch: Throwable -> L21
            r3.close();     // Catch: Exception -> L19
            boolean r4 = true;
        L30:
            r0.recycle();
        L32:
            StoryRecorder r02 = this.this$0;
            if (r4 == false) goto L35;
            StoryRecorder.m20515$$Nest$fputtakingPhoto(r02, false);
            if (StoryRecorder.m20463$$Nest$fgetqrScanner(this.this$0) == null) goto L41;
            StoryRecorder.m20463$$Nest$fgetqrScanner(this.this$0).setPaused(false);
        L41:
            StoryEntry r03 = StoryEntry.fromPhotoShoot(StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0), 0);
            r03.botId = StoryRecorder.m20395$$Nest$fgetbotId(this.this$0);
            r03.botLang = StoryRecorder.m20396$$Nest$fgetbotLang(this.this$0);
            boolean r32 = StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).hasLayout();
            StoryRecorder r42 = this.this$0;
            if (r32 == false) goto L51;
            StoryRecorder.m20509$$Nest$fputoutputFile(r42, null);
            if (StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).push(r03) == false) goto L48;
            StoryRecorder r04 = this.this$0;
            StoryRecorder.m20508$$Nest$fputoutputEntry(r04, StoryEntry.asCollage(StoryRecorder.m20407$$Nest$fgetcollageLayoutView(r04).getLayout(), StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).getContent()));
            StoryPrivacySelector.applySaved(StoryRecorder.m20413$$Nest$fgetcurrentAccount(this.this$0), StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0));
            StoryRecorder.m20496$$Nest$fputfromGallery(this.this$0, false);
            if (r7 == null) goto L50;
            r7.run(null);
        L50:
            StoryRecorder.m20549$$Nest$mupdateActionBarButtons(this.this$0, true);
            return;
        L48:
            if (r7 == null) goto L50;
            r7.run(null);
            goto L50
        L51:
            StoryRecorder.m20508$$Nest$fputoutputEntry(r42, r03);
            StoryPrivacySelector.applySaved(StoryRecorder.m20413$$Nest$fgetcurrentAccount(this.this$0), StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0));
            StoryRecorder.m20496$$Nest$fputfromGallery(this.this$0, false);
            if (r7 == null) goto L54;
            r7.run(new StoryRecorder$13$$ExternalSyntheticLambda6(this));
            return;
        L54:
            this.this$0.navigateTo(1, true);
            return;
        L35:
            if (StoryRecorder.m20463$$Nest$fgetqrScanner(r02) == null) goto L37;
            StoryRecorder.m20463$$Nest$fgetqrScanner(this.this$0).setPaused(true);
        L37:
            StoryRecorder.m20515$$Nest$fputtakingPhoto(this.this$0, CameraController.getInstance().takePicture(StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0), true, StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).getCameraSessionObject(), new StoryRecorder$13$$ExternalSyntheticLambda5(this, r7)));
            return;
        L19:
            e = e;
            r4 = true;
        L29:
            FileLog.e(e);
            goto L30
        L21:
            th = move-exception;
            r3.close();     // Catch: Throwable -> L24
        L26:
            throw th;     // Catch: Exception -> L27
        L24:
            th = move-exception;
            th.addSuppressed(th);     // Catch: Exception -> L27
        L27:
            e = e;
            r4 = false;
        L10:
            if (StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).hasLayout() == true) goto L12;
            r4 = false;
            goto L32
        }

        @Override // org.telegram.ui.Stories.recorder.RecordControl.Delegate
        public boolean canRecordAudio() {
            return StoryRecorder.m20541$$Nest$mrequestAudioPermission(this.this$0);
        }

        @Override // org.telegram.ui.Stories.recorder.RecordControl.Delegate
        public long getMaxVideoDuration() {
            return 177000;
        }

        @Override // org.telegram.ui.Stories.recorder.RecordControl.Delegate
        public void onCheckClick() {
            ArrayList<StoryEntry> r0 = StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).getContent();
            int r1 = r0.size();
            StoryRecorder r2 = this.this$0;
            int r3 = 0;
            if (r1 != 1) goto L5;
            StoryRecorder.m20508$$Nest$fputoutputEntry(r2, r0.get(0));
        L6:
            StoryRecorder r02 = this.this$0;
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(r02) != null) goto L9;
        L11:
            StoryRecorder.m20505$$Nest$fputmode(r02, r3);
            if (StoryRecorder.m20440$$Nest$fgetmodeSwitcherView(this.this$0) == null) goto L14;
            StoryRecorder.m20440$$Nest$fgetmodeSwitcherView(this.this$0).switchMode(StoryRecorder.m20439$$Nest$fgetmode(this.this$0));
        L14:
            StoryPrivacySelector.applySaved(StoryRecorder.m20413$$Nest$fgetcurrentAccount(this.this$0), StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0));
            this.this$0.navigateTo(1, true);
            return;
        L9:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).isVideo == false) goto L11;
            r3 = 1;
            goto L11
        L5:
            StoryRecorder.m20508$$Nest$fputoutputEntry(r2, StoryEntry.asCollage(StoryRecorder.m20407$$Nest$fgetcollageLayoutView(r2).getLayout(), StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).getContent()));
            goto L6
        }

        @Override // org.telegram.ui.Stories.recorder.RecordControl.Delegate
        public void onFlipClick() {
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0) != null) goto L5;
            return;
        L5:
            if (StoryRecorder.m20393$$Nest$fgetawaitingPlayer(this.this$0) == false) goto L7;
            return;
        L7:
            if (StoryRecorder.m20472$$Nest$fgettakingPhoto(this.this$0) == false) goto L9;
            return;
        L9:
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).isInited() == true) goto L11;
            return;
        L11:
            if (StoryRecorder.m20415$$Nest$fgetcurrentPage(this.this$0) == 0) goto L14;
            return;
        L14:
            if (StoryRecorder.m20468$$Nest$fgetsavedDualHint(this.this$0) == null) goto L17;
            StoryRecorder.m20468$$Nest$fgetsavedDualHint(this.this$0).hide();
        L17:
            if (StoryRecorder.m20551$$Nest$museDisplayFlashlight(this.this$0) == true) goto L19;
        L23:
            StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).switchCamera();
            StoryRecorder r0 = this.this$0;
            StoryRecorder.m20543$$Nest$msaveCameraFace(r0, StoryRecorder.m20398$$Nest$fgetcameraView(r0).isFrontface());
            boolean r02 = StoryRecorder.m20551$$Nest$museDisplayFlashlight(this.this$0);
            StoryRecorder r4 = this.this$0;
            if (r02 == false) goto L27;
            StoryRecorder.m20420$$Nest$fgetflashViews(r4).flashIn(null);
            return;
        L27:
            StoryRecorder.m20420$$Nest$fgetflashViews(r4).flashOut();
            return;
        L19:
            if (StoryRecorder.m20425$$Nest$fgetfrontfaceFlashModes(this.this$0) == null) goto L23;
            if (StoryRecorder.m20425$$Nest$fgetfrontfaceFlashModes(this.this$0).isEmpty() == true) goto L23;
            String r03 = (String) StoryRecorder.m20425$$Nest$fgetfrontfaceFlashModes(this.this$0).get(StoryRecorder.m20424$$Nest$fgetfrontfaceFlashMode(this.this$0));
            ApplicationLoader.applicationContext.getSharedPreferences("camera", 0).edit().putString("flashMode", r03).commit();
            goto L23
        }

        @Override // org.telegram.ui.Stories.recorder.RecordControl.Delegate
        public void onFlipLongClick() {
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0) == null) goto L6;
            StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).toggleDual();
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.RecordControl.Delegate
        public void onGalleryClick() {
            if (StoryRecorder.m20415$$Nest$fgetcurrentPage(this.this$0) == 0) goto L5;
            return;
        L5:
            if (StoryRecorder.m20472$$Nest$fgettakingPhoto(this.this$0) == false) goto L7;
            return;
        L7:
            if (StoryRecorder.m20473$$Nest$fgettakingVideo(this.this$0) == false) goto L9;
            return;
        L9:
            if (StoryRecorder.m20542$$Nest$mrequestGalleryPermission(this.this$0) == false) goto L15;
            StoryRecorder.m20521$$Nest$manimateGalleryListView(this.this$0, true);
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.RecordControl.Delegate
        public void onPhotoShoot() {
            if (StoryRecorder.m20472$$Nest$fgettakingPhoto(this.this$0) == false) goto L5;
            return;
        L5:
            if (StoryRecorder.m20393$$Nest$fgetawaitingPlayer(this.this$0) == false) goto L7;
            return;
        L7:
            if (StoryRecorder.m20415$$Nest$fgetcurrentPage(this.this$0) == 0) goto L9;
            return;
        L9:
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0) != null) goto L11;
            return;
        L11:
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).isInited() == false) goto L38;
            StoryRecorder.m20397$$Nest$fgetcameraHint(this.this$0).hide();
            if (StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0) == null) goto L18;
            StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0).delete();     // Catch: Exception -> L30
        L16:
            StoryRecorder.m20509$$Nest$fputoutputFile(this.this$0, null);
        L18:
            if (StoryRecorder.m20463$$Nest$fgetqrScanner(this.this$0) == null) goto L20;
            StoryRecorder.m20463$$Nest$fgetqrScanner(this.this$0).setPaused(true);
        L20:
            StoryRecorder r0 = this.this$0;
            StoryRecorder.m20509$$Nest$fputoutputFile(r0, StoryEntry.makeCacheFile(StoryRecorder.m20413$$Nest$fgetcurrentAccount(r0), false));
            StoryRecorder.m20515$$Nest$fputtakingPhoto(this.this$0, true);
            StoryRecorder.m20526$$Nest$mcheckFrontfaceFlashModes(this.this$0);
            StoryRecorder.m20503$$Nest$fputisDark(this.this$0, false);
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).isFrontface() == false) goto L26;
            if (StoryRecorder.m20424$$Nest$fgetfrontfaceFlashMode(this.this$0) != 1) goto L26;
            StoryRecorder.m20527$$Nest$mcheckIsDark(this.this$0);
        L26:
            if (StoryRecorder.m20551$$Nest$museDisplayFlashlight(this.this$0) == false) goto L28;
            StoryRecorder.m20420$$Nest$fgetflashViews(this.this$0).flash(new StoryRecorder$13$$ExternalSyntheticLambda1(this));
            return;
        L28:
            takePicture(null);
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.RecordControl.Delegate
        public void onVideoDuration(long r2) {
            StoryRecorder.m20483$$Nest$fgetvideoTimerView(this.this$0).setDuration(r2, true);
        }

        @Override // org.telegram.ui.Stories.recorder.RecordControl.Delegate
        public void onVideoRecordEnd(boolean r3) {
            if (StoryRecorder.m20471$$Nest$fgetstoppingTakingVideo(this.this$0) == false) goto L5;
            return;
        L5:
            if (StoryRecorder.m20473$$Nest$fgettakingVideo(this.this$0) == false) goto L14;
            StoryRecorder.m20514$$Nest$fputstoppingTakingVideo(this.this$0, true);
            StoryRecorder$13$$ExternalSyntheticLambda0 r0 = new StoryRecorder$13$$ExternalSyntheticLambda0(this);
            if (r3 == false) goto L10;
            long r2 = 0;
        L11:
            AndroidUtilities.runOnUIThread(r0, r2);
            return;
        L10:
            r2 = 400;
            goto L11
        }

        @Override // org.telegram.ui.Stories.recorder.RecordControl.Delegate
        public void onVideoRecordLocked() {
            StoryRecorder.m20434$$Nest$fgethintTextView(this.this$0).setText(LocaleController.getString(R.string.StoryHintPinchToZoom), true);
        }

        @Override // org.telegram.ui.Stories.recorder.RecordControl.Delegate
        public void onVideoRecordStart(boolean r4, Runnable r5) {
            if (StoryRecorder.m20473$$Nest$fgettakingVideo(this.this$0) == false) goto L5;
            return;
        L5:
            if (StoryRecorder.m20471$$Nest$fgetstoppingTakingVideo(this.this$0) == false) goto L7;
            return;
        L7:
            if (StoryRecorder.m20393$$Nest$fgetawaitingPlayer(this.this$0) == false) goto L9;
            return;
        L9:
            if (StoryRecorder.m20415$$Nest$fgetcurrentPage(this.this$0) == 0) goto L11;
            return;
        L11:
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0) != null) goto L13;
            return;
        L13:
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).getCameraSession() != null) goto L16;
            return;
        L16:
            if (StoryRecorder.m20419$$Nest$fgetdualHint(this.this$0) == null) goto L19;
            StoryRecorder.m20419$$Nest$fgetdualHint(this.this$0).hide();
        L19:
            if (StoryRecorder.m20468$$Nest$fgetsavedDualHint(this.this$0) == null) goto L21;
            StoryRecorder.m20468$$Nest$fgetsavedDualHint(this.this$0).hide();
        L21:
            StoryRecorder.m20397$$Nest$fgetcameraHint(this.this$0).hide();
            StoryRecorder.m20516$$Nest$fputtakingVideo(this.this$0, true);
            if (StoryRecorder.m20463$$Nest$fgetqrScanner(this.this$0) == null) goto L25;
            StoryRecorder.m20463$$Nest$fgetqrScanner(this.this$0).setPaused(true);
        L25:
            if (StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0) != null) goto L39;
        L28:
            StoryRecorder r0 = this.this$0;
            StoryRecorder.m20509$$Nest$fputoutputFile(r0, StoryEntry.makeCacheFile(StoryRecorder.m20413$$Nest$fgetcurrentAccount(r0), true));
            StoryRecorder.m20526$$Nest$mcheckFrontfaceFlashModes(this.this$0);
            StoryRecorder.m20503$$Nest$fputisDark(this.this$0, false);
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0).isFrontface() == false) goto L34;
            if (StoryRecorder.m20424$$Nest$fgetfrontfaceFlashMode(this.this$0) != 1) goto L34;
            StoryRecorder.m20527$$Nest$mcheckIsDark(this.this$0);
        L34:
            if (StoryRecorder.m20551$$Nest$museDisplayFlashlight(this.this$0) == false) goto L36;
            StoryRecorder.m20420$$Nest$fgetflashViews(this.this$0).flashIn(new StoryRecorder$13$$ExternalSyntheticLambda2(this, r4, r5));
            return;
        L36:
            startRecording(r4, r5);
            return;
        L39:
            StoryRecorder.m20448$$Nest$fgetoutputFile(this.this$0).delete();     // Catch: Exception -> L38
        L27:
            StoryRecorder.m20509$$Nest$fputoutputFile(this.this$0, null);
            goto L28
        }

        @Override // org.telegram.ui.Stories.recorder.RecordControl.Delegate
        public void onZoom(float r3) {
            StoryRecorder.m20487$$Nest$fgetzoomControlView(this.this$0).setZoom(r3, true);
            StoryRecorder.m20547$$Nest$mshowZoomControls(this.this$0, false, true);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$14, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass14 extends AnimatorListenerAdapter {
        final /* synthetic */ StoryRecorder this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass14(StoryRecorder r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.val$show == true) goto L5;
            StoryRecorder.m20487$$Nest$fgetzoomControlView(this.this$0).setVisibility(8);
        L5:
            StoryRecorder.m20519$$Nest$fputzoomControlAnimation(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$15, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass15 extends CharacterStyle {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass15(StoryRecorder r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setAlpha(128);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$16, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass16 extends AnimatorListenerAdapter {
        final /* synthetic */ StoryRecorder this$0;
        final /* synthetic */ int val$oldPage;
        final /* synthetic */ int val$page;

        public AnonymousClass16(StoryRecorder r1, int r2, int r3) {
            this.this$0 = r1;
            this.val$oldPage = r2;
            this.val$page = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            StoryRecorder.m20538$$Nest$monNavigateEnd(this.this$0, this.val$oldPage, this.val$page);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$17, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass17 extends AnimatorListenerAdapter {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass17(StoryRecorder r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            StoryRecorder.m20493$$Nest$fputcontainerViewBackAnimator(this.this$0, null);
            StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).setTranslationY(0.0f);
            StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).setTranslationY2(0.0f);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$19, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass19 extends GalleryListView {
        final /* synthetic */ StoryRecorder this$0;

        public static /* synthetic */ void $r8$lambda$GKJQSazxj6TiEp9OkVjnUBAJgXU(AnonymousClass19 r0) {
            r0.lambda$onFullScreen$0();
        }

        public AnonymousClass19(StoryRecorder r1, int r2, Context r3, Theme.ResourcesProvider r4, MediaController.AlbumEntry r5, boolean r6, float r7, boolean r8, boolean r9) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8, r9);
        }

        private /* synthetic */ void lambda$onFullScreen$0() {
            StoryRecorder.m20531$$Nest$mdestroyCameraView(this.this$0, true);
            StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).setCameraThumb(StoryRecorder.m20533$$Nest$mgetCameraThumb(this.this$0));
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L9;
            if (r3.getY() >= top()) goto L9;
            StoryRecorder.m20498$$Nest$fputgalleryClosing(this.this$0, true);
            StoryRecorder.m20521$$Nest$manimateGalleryListView(this.this$0, false);
            return true;
        L9:
            return super.dispatchTouchEvent(r3);
        }

        @Override // org.telegram.ui.Stories.recorder.GalleryListView
        public void firstLayout() {
            StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0).setTranslationY(StoryRecorder.m20486$$Nest$fgetwindowView(this.this$0).getMeasuredHeight() - StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0).top());
            if (StoryRecorder.m20429$$Nest$fgetgalleryLayouted(this.this$0) == null) goto L6;
            StoryRecorder.m20429$$Nest$fgetgalleryLayouted(this.this$0).run();
            StoryRecorder.m20499$$Nest$fputgalleryLayouted(this.this$0, null);
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.GalleryListView
        public void onFullScreen(boolean r2) {
            if (StoryRecorder.m20415$$Nest$fgetcurrentPage(this.this$0) != 0) goto L7;
            if (r2 == false) goto L8;
            AndroidUtilities.runOnUIThread(new StoryRecorder$19$$ExternalSyntheticLambda0(this));
            return;
        L8:
            return;
        }

        @Override // android.view.View
        public void setTranslationY(float r4) {
            super.setTranslationY(r4);
            if (StoryRecorder.m20392$$Nest$fgetapplyContainerViewTranslation2(this.this$0) == false) goto L6;
            float r42 = Utilities.clamp(1.0f - (r4 / (StoryRecorder.m20486$$Nest$fgetwindowView(this.this$0).getMeasuredHeight() - StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0).top())), 1.0f, 0.0f);
            StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).setTranslationY2(AndroidUtilities.dp(-32.0f) * r42);
            StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).setAlpha(1.0f - (0.6f * r42));
            StoryRecorder.m20390$$Nest$fgetactionBarContainer(this.this$0).setAlpha(1.0f - r42);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 implements FlashViews.Invertable {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass2(StoryRecorder r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Stories.recorder.FlashViews.Invertable
        public void invalidate() {
        }

        @Override // org.telegram.ui.Stories.recorder.FlashViews.Invertable
        public void setInvert(float r5) {
            WindowView r0 = StoryRecorder.m20486$$Nest$fgetwindowView(this.this$0);
            boolean r1 = false;
            if (r5 <= 0.5f) goto L5;
            boolean r3 = true;
        L6:
            AndroidUtilities.setLightNavigationBar(r0, r3);
            WindowView r4 = StoryRecorder.m20486$$Nest$fgetwindowView(this.this$0);
            if (r5 <= 0.5f) goto L9;
            r1 = true;
        L9:
            AndroidUtilities.setLightStatusBar(r4, r1);
            return;
        L5:
            r3 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$20, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass20 extends AnimatorListenerAdapter {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass20(StoryRecorder r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            StoryRecorder.m20486$$Nest$fgetwindowView(this.this$0).removeView(StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0));
            StoryRecorder.m20500$$Nest$fputgalleryListView(this.this$0, null);
            StoryRecorder.m20502$$Nest$fputgalleryOpenCloseAnimator(this.this$0, null);
            StoryRecorder.m20501$$Nest$fputgalleryListViewOpening(this.this$0, null);
            KeyboardNotifier r22 = StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).keyboardNotifier;
            boolean r0 = true;
            if (StoryRecorder.m20415$$Nest$fgetcurrentPage(this.this$0) != 1) goto L6;
            r0 = false;
        L6:
            r22.ignore(r0);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$21, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass21 implements TimelineView.TimelineDelegate {
        final /* synthetic */ StoryRecorder this$0;
        final /* synthetic */ Utilities.Callback2 val$videoLeftSet;

        public AnonymousClass21(StoryRecorder r1, Utilities.Callback2 r2) {
            this.this$0 = r1;
            this.val$videoLeftSet = r2;
        }

        @Override // org.telegram.ui.Stories.recorder.TimelineView.TimelineDelegate
        public void onVideoLeftChange(boolean r1, float r2) {
            this.val$videoLeftSet.run(Boolean.FALSE, Float.valueOf(r2));
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$22, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass22 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass22(StoryRecorder r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r1) {
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).invalidateBlur();
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$23, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass23 extends AnimatorListenerAdapter {
        final /* synthetic */ StoryRecorder this$0;
        final /* synthetic */ int val$editMode;
        final /* synthetic */ int val$oldEditMode;

        public AnonymousClass23(StoryRecorder r1, int r2, int r3) {
            this.this$0 = r1;
            this.val$oldEditMode = r2;
            this.val$editMode = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            int r22 = this.val$oldEditMode;
            int r0 = this.val$editMode;
            if (r22 == r0) goto L6;
            StoryRecorder.m20539$$Nest$monSwitchEditModeEnd(this.this$0, r22, r0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$24, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass24 extends PaintView {
        private boolean multitouch;
        final /* synthetic */ StoryRecorder this$0;

        public static /* synthetic */ void $r8$lambda$8tpBM1Z3fQcYMLUiettsWPl8Zh4(PhotoView r1) {
            r1.onSwitchSegmentedAnimationStarted(true);
        }

        /* renamed from: $r8$lambda$Mjl5la2c3qiQbVre8u-19Wu6OzI, reason: not valid java name */
        public static /* synthetic */ void m20555$r8$lambda$Mjl5la2c3qiQbVre8u19Wu6OzI(AnonymousClass24 r0) {
            r0.lambda$showTrash$0();
        }

        /* renamed from: $r8$lambda$YkfwDNzmVRzurNJKj5M81lU-0R8, reason: not valid java name */
        public static /* synthetic */ void m20556$r8$lambda$YkfwDNzmVRzurNJKj5M81lU0R8() {
        }

        public static /* synthetic */ void $r8$lambda$riyjGBjYugHRuzlPJFD2w853BWg(AnonymousClass24 r0, boolean r1) {
            r0.lambda$onAudioSelect$1(r1);
        }

        public AnonymousClass24(StoryRecorder r24, Context r25, boolean r26, File r27, boolean r28, boolean r29, WindowView r30, Activity r31, int r32, Bitmap r33, Bitmap r34, Bitmap r35, int r36, ArrayList r37, StoryEntry r38, int r39, int r40, MediaController.CropState r41, Runnable r42, BlurringShader.BlurManager r43, Theme.ResourcesProvider r44, PreviewView.TextureViewHolder r45, PreviewView r46) {
            this.this$0 = r24;
            super(r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46);
        }

        private /* synthetic */ void lambda$onAudioSelect$1(boolean r1) {
            if (r1 == true) goto L5;
            StoryRecorder.m20454$$Nest$fgetplayButton(this.this$0).setVisibility(8);
            return;
        }

        private /* synthetic */ void lambda$showTrash$0() {
            StoryRecorder.m20478$$Nest$fgettrash(this.this$0).setVisibility(8);
        }

        @Override // org.telegram.ui.Stories.recorder.PaintView
        public boolean checkAudioPermission(Runnable r7) {
            if (StoryRecorder.m20391$$Nest$fgetactivity(this.this$0) != null) goto L5;
            return true;
        L5:
            int r0 = Build.VERSION.SDK_INT;
            StoryRecorder r2 = this.this$0;
            if (r0 < 33) goto L12;
            if (StoryRecorder.m20391$$Nest$fgetactivity(r2).checkSelfPermission("android.permission.READ_MEDIA_AUDIO") == 0) goto L15;
            StoryRecorder.m20391$$Nest$fgetactivity(this.this$0).requestPermissions(new String[]{"android.permission.READ_MEDIA_AUDIO"}, 115);
            StoryRecorder.m20488$$Nest$fputaudioGrantedCallback(this.this$0, r7);
            return false;
        L15:
            return true;
        L12:
            if (StoryRecorder.m20391$$Nest$fgetactivity(r2).checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) goto L15;
            StoryRecorder.m20391$$Nest$fgetactivity(this.this$0).requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 115);
            StoryRecorder.m20488$$Nest$fputaudioGrantedCallback(this.this$0, r7);
            return false;
        }

        @Override // org.telegram.ui.Stories.recorder.PaintView
        public void dismiss() {
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).editText.closeKeyboard();
            this.this$0.switchToEditMode(-1, true);
        }

        @Override // org.telegram.ui.Stories.recorder.PaintView
        public void editSelectedTextEntity() {
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).editText.closeKeyboard();
            this.this$0.switchToEditMode(0, true);
            super.editSelectedTextEntity();
        }

        @Override // org.telegram.ui.Stories.recorder.PaintView
        public void onAudioSelect(MessageObject r6) {
            StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).setupAudio(r6, true);
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0) != null) goto L5;
        L11:
            StoryRecorder r62 = this.this$0;
            if (StoryRecorder.m20407$$Nest$fgetcollageLayoutView(r62).hasLayout() == true) goto L14;
        L18:
            int r5 = -1;
        L19:
            r62.switchToEditMode(r5, true, true);
            return;
        L14:
            if (StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).hasVideo() == false) goto L18;
            if (TextUtils.isEmpty(StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).audioPath) == true) goto L18;
            r5 = 2;
            goto L19
        L5:
            if (StoryRecorder.m20439$$Nest$fgetmode(this.this$0) == 1) goto L11;
            boolean r63 = TextUtils.isEmpty(StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).audioPath);
            boolean r0 = !r63;
            StoryRecorder.m20454$$Nest$fgetplayButton(this.this$0).drawable.setPause(!StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).isPlaying(), false);
            StoryRecorder.m20454$$Nest$fgetplayButton(this.this$0).setVisibility(0);
            ViewPropertyAnimator r2 = StoryRecorder.m20454$$Nest$fgetplayButton(this.this$0).animate();
            if (r63 == true) goto L9;
            float r64 = 1.0f;
        L10:
            r2.alpha(r64).withEndAction(new StoryRecorder$24$$ExternalSyntheticLambda3(this, r0)).start();
            goto L11
        L9:
            r64 = 0.0f;
            goto L10
        }

        @Override // org.telegram.ui.Stories.recorder.PaintView
        public void onCreateRound(RoundView r2) {
            if (StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0) == null) goto L6;
            StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).attachRoundView(r2);
        L6:
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0) == null) goto L9;
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).setHasRoundVideo(true);
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.PaintView
        public void onDeleteRound() {
            if (StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0) == null) goto L6;
            StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).setupRound(null, null, true);
        L6:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0) == null) goto L9;
            StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).deleteRound();
        L9:
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0) == null) goto L12;
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).setHasRoundVideo(false);
        L12:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0) != null) goto L14;
            return;
        L14:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).round == null) goto L18;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).round.delete();     // Catch: Exception -> L23
        L16:
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).round = null;
        L18:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundThumb == null) goto L29;
            new File(StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundThumb).delete();     // Catch: Exception -> L22
        L20:
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundThumb = null;
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.PaintView
        public void onDeselectRound(RoundView r1) {
            if (StoryRecorder.m20477$$Nest$fgettimelineView(this.this$0) == null) goto L6;
            StoryRecorder.m20477$$Nest$fgettimelineView(this.this$0).selectRound(false);
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.PaintView, org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
        public void onEntityDragEnd(boolean r10) {
            if (isEntityDeletable() == true) goto L5;
            r10 = false;
        L5:
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).clearAnimation();
            ViewPropertyAnimator r0 = StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).animate();
            float r3 = 0.0f;
            if (StoryRecorder.m20414$$Nest$fgetcurrentEditMode(this.this$0) != (-1)) goto L8;
            float r2 = 1.0f;
        L9:
            ViewPropertyAnimator r02 = r0.alpha(r2).setDuration(180);
            CubicBezierInterpolator r22 = CubicBezierInterpolator.EASE_OUT;
            r02.setInterpolator(r22).start();
            StoryRecorder.m20482$$Nest$fgetvideoTimelineContainerView(this.this$0).clearAnimation();
            ViewPropertyAnimator r03 = StoryRecorder.m20482$$Nest$fgetvideoTimelineContainerView(this.this$0).animate();
            if (StoryRecorder.m20414$$Nest$fgetcurrentEditMode(this.this$0) != (-1)) goto L12;
        L13:
            r3 = 1.0f;
        L14:
            r03.alpha(r3).setDuration(180).setInterpolator(r22).start();
            showTrash(false, r10);
            if (r10 == false) goto L17;
            removeCurrentEntity();
        L17:
            super.onEntityDragEnd(r10);
            this.multitouch = false;
            return;
        L12:
            if (StoryRecorder.m20414$$Nest$fgetcurrentEditMode(this.this$0) != 2) goto L14;
        L8:
            r2 = 0.0f;
            goto L9
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
        public void onEntityDragMultitouchEnd() {
            this.multitouch = false;
            showTrash(isEntityDeletable(), false);
            StoryRecorder.m20458$$Nest$fgetpreviewHighlight(this.this$0).show(false, false, null);
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
        public void onEntityDragMultitouchStart() {
            this.multitouch = true;
            StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).showReactionsLayout(false);
            showTrash(false, false);
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
        public void onEntityDragStart() {
            StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).showReactionsLayout(false);
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).clearAnimation();
            ViewPropertyAnimator r0 = StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).animate().alpha(0.0f).setDuration(180);
            CubicBezierInterpolator r5 = CubicBezierInterpolator.EASE_OUT;
            r0.setInterpolator(r5).start();
            if (StoryRecorder.m20414$$Nest$fgetcurrentEditMode(this.this$0) == 2) goto L5;
            StoryRecorder.m20482$$Nest$fgetvideoTimelineContainerView(this.this$0).clearAnimation();
            StoryRecorder.m20482$$Nest$fgetvideoTimelineContainerView(this.this$0).animate().alpha(0.0f).setDuration(180).setInterpolator(r5).start();
        L5:
            showTrash(isEntityDeletable(), false);
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
        public void onEntityDragTrash(boolean r2) {
            StoryRecorder.m20478$$Nest$fgettrash(this.this$0).onDragInfo(r2, false);
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
        public void onEntityDraggedBottom(boolean r3) {
            StoryRecorder.m20458$$Nest$fgetpreviewHighlight(this.this$0).updateCaption(StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).getText());
            PreviewHighlightView r0 = StoryRecorder.m20458$$Nest$fgetpreviewHighlight(this.this$0);
            if (r3 == true) goto L5;
        L7:
            boolean r2 = false;
        L8:
            r0.show(false, r2, null);
            return;
        L5:
            if (this.multitouch == false) goto L7;
            r2 = true;
            goto L8
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
        public void onEntityDraggedTop(boolean r3) {
            StoryRecorder.m20458$$Nest$fgetpreviewHighlight(this.this$0).show(true, r3, StoryRecorder.m20390$$Nest$fgetactionBarContainer(this.this$0));
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
        public void onEntityHandleTouched() {
            StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).showReactionsLayout(false);
        }

        @Override // org.telegram.ui.Stories.recorder.PaintView
        public void onGalleryClick() {
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).keyboardNotifier.ignore(true);
            StoryRecorder.m20532$$Nest$mdestroyGalleryListView(this.this$0);
            StoryRecorder.m20529$$Nest$mcreateGalleryListView(this.this$0, true);
            StoryRecorder.m20521$$Nest$manimateGalleryListView(this.this$0, true);
        }

        @Override // org.telegram.ui.Stories.recorder.PaintView
        public void onOpenCloseStickersAlert(boolean r4) {
            if (StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0) == null) goto L8;
            StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).updatePauseReason(6, r4);
            if (StoryRecorder.m20454$$Nest$fgetplayButton(this.this$0) == null) goto L8;
            StoryRecorder.m20454$$Nest$fgetplayButton(this.this$0).drawable.setPause(StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).isPlaying(), true);
        L8:
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0) == null) goto L11;
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).ignoreTouches = r4;
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).keyboardNotifier.ignore(r4);
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.PaintView
        public void onSelectRound(RoundView r1) {
            if (StoryRecorder.m20477$$Nest$fgettimelineView(this.this$0) == null) goto L6;
            StoryRecorder.m20477$$Nest$fgettimelineView(this.this$0).selectRound(true);
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.PaintView
        public void onSwitchSegmentedAnimation(PhotoView r14) {
            if (r14 != null) goto L4;
            return;
        L4:
            ThanosEffect r0 = this.this$0.getThanosEffect();
            if (r0 != null) goto L8;
            r14.onSwitchSegmentedAnimationStarted(false);
            return;
        L8:
            Bitmap r2 = r14.getSegmentedOutBitmap();
            if (r2 != null) goto L12;
            r14.onSwitchSegmentedAnimationStarted(false);
            return;
        L12:
            Matrix r1 = new Matrix();
            float r3 = r14.getWidth();
            float r4 = r14.getHeight();
            float r6 = 0.0f;
            if (r14.getRotation() == 0.0f) goto L15;
            float r5 = r2.getWidth();
            float r62 = r2.getHeight();
            float r8 = r5 / 2.0f;
            float r9 = r62 / 2.0f;
            float r82 = (float) Math.sqrt((r8 * r8) + (r9 * r9));
            float r92 = r82 * 2.0f;
            int r10 = (int) r92;
            Bitmap r102 = Bitmap.createBitmap(r10, r10, Bitmap.Config.ARGB_8888);
            Canvas r11 = new Canvas(r102);
            r11.save();
            r11.rotate(r14.getRotation(), r82, r82);
            r11.drawBitmap(r2, (r92 - r5) / 2.0f, (r92 - r62) / 2.0f, null);
            r2.recycle();
            float r22 = r3 / 2.0f;
            float r52 = r4 / 2.0f;
            float r23 = ((float) Math.sqrt((r22 * r22) + (r52 * r52))) * 2.0f;
            r6 = (-(r23 - r3)) / 2.0f;
            float r32 = (-(r23 - r4)) / 2.0f;
            r4 = r23;
            float r53 = r32;
            r3 = r4;
            r2 = r102;
        L16:
            r1.postScale(r3, r4);
            r1.postScale(r14.getScaleX(), r14.getScaleY(), r3 / 2.0f, r4 / 2.0f);
            r1.postTranslate(((StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getX() + StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0).getX()) + r14.getX()) + r6, ((StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getY() + StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0).getY()) + r14.getY()) + r53);
            r0.animate(r1, r2, new StoryRecorder$24$$ExternalSyntheticLambda1(r14), new StoryRecorder$24$$ExternalSyntheticLambda2());
            return;
        L15:
            r53 = 0.0f;
            goto L16
        }

        @Override // org.telegram.ui.Stories.recorder.PaintView
        public void onTryDeleteRound() {
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0) == null) goto L6;
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).showRemoveRoundAlert();
            return;
        }

        public void showTrash(boolean r6, boolean r7) {
            StoryRecorder r0 = this.this$0;
            if (r6 == false) goto L6;
            StoryRecorder.m20478$$Nest$fgettrash(r0).setVisibility(0);
            StoryRecorder.m20478$$Nest$fgettrash(this.this$0).setAlpha(0.0f);
            StoryRecorder.m20478$$Nest$fgettrash(this.this$0).clearAnimation();
            StoryRecorder.m20478$$Nest$fgettrash(this.this$0).animate().alpha(1.0f).setDuration(180).setInterpolator(CubicBezierInterpolator.EASE_OUT).start();
            return;
        L6:
            StoryRecorder.m20478$$Nest$fgettrash(r0).onDragInfo(false, r7);
            StoryRecorder.m20478$$Nest$fgettrash(this.this$0).clearAnimation();
            ViewPropertyAnimator r5 = StoryRecorder.m20478$$Nest$fgettrash(this.this$0).animate().alpha(0.0f).withEndAction(new StoryRecorder$24$$ExternalSyntheticLambda0(this)).setDuration(180).setInterpolator(CubicBezierInterpolator.EASE_OUT);
            if (r7 == false) goto L9;
            long r62 = 500;
        L10:
            r5.setStartDelay(r62).start();
            return;
        L9:
            r62 = 0;
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$25, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass25 extends DualCameraView {
        final /* synthetic */ StoryRecorder this$0;

        public static /* synthetic */ void $r8$lambda$VyzOdqG0g1Rp5y9nqemDH2Vi0Hk(AnonymousClass25 r0) {
            r0.lambda$onSavedDualCameraSuccess$0();
        }

        public AnonymousClass25(StoryRecorder r1, Context r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        private /* synthetic */ void lambda$onSavedDualCameraSuccess$0() {
            if (StoryRecorder.m20473$$Nest$fgettakingVideo(this.this$0) == false) goto L5;
            return;
        L5:
            if (StoryRecorder.m20472$$Nest$fgettakingPhoto(this.this$0) == false) goto L7;
            return;
        L7:
            if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0) != null) goto L9;
            return;
        L9:
            if (StoryRecorder.m20415$$Nest$fgetcurrentPage(this.this$0) == 0) goto L12;
            return;
        L12:
            if (StoryRecorder.m20468$$Nest$fgetsavedDualHint(this.this$0) != null) goto L14;
            return;
        L14:
            if (isFrontface() == false) goto L17;
            int r0 = R.string.StoryCameraSavedDualBackHint;
        L16:
            String r02 = LocaleController.getString(r0);
            StoryRecorder.m20468$$Nest$fgetsavedDualHint(this.this$0).setMaxWidthPx(HintView2.cutInFancyHalf(r02, StoryRecorder.m20468$$Nest$fgetsavedDualHint(this.this$0).getTextPaint()));
            StoryRecorder.m20468$$Nest$fgetsavedDualHint(this.this$0).setText(r02);
            StoryRecorder.m20468$$Nest$fgetsavedDualHint(this.this$0).show();
            MessagesController.getGlobalMainSettings().edit().putInt("storysvddualhint", MessagesController.getGlobalMainSettings().getInt("storysvddualhint", 0) + 1).apply();
            return;
        L17:
            r0 = R.string.StoryCameraSavedDualFrontHint;
            goto L16
        }

        @Override // org.telegram.ui.Stories.recorder.DualCameraView
        public void onEntityDraggedBottom(boolean r3) {
            StoryRecorder.m20458$$Nest$fgetpreviewHighlight(this.this$0).updateCaption(StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).getText());
            StoryRecorder.m20458$$Nest$fgetpreviewHighlight(this.this$0).show(false, r3, StoryRecorder.m20410$$Nest$fgetcontrolContainer(this.this$0));
        }

        @Override // org.telegram.ui.Stories.recorder.DualCameraView
        public void onEntityDraggedTop(boolean r3) {
            StoryRecorder.m20458$$Nest$fgetpreviewHighlight(this.this$0).show(true, r3, StoryRecorder.m20390$$Nest$fgetactionBarContainer(this.this$0));
        }

        @Override // org.telegram.ui.Stories.recorder.DualCameraView
        public void onSavedDualCameraSuccess() {
            if (MessagesController.getGlobalMainSettings().getInt("storysvddualhint", 0) >= 2) goto L5;
            AndroidUtilities.runOnUIThread(new StoryRecorder$25$$ExternalSyntheticLambda0(this), 340);
        L5:
            StoryRecorder.m20418$$Nest$fgetdualButton(this.this$0).setValue(isDual());
            ToggleButton r0 = StoryRecorder.m20418$$Nest$fgetdualButton(this.this$0);
            if (isDual() == false) goto L8;
            int r3 = R.string.AccDescrDualCameraOn;
        L9:
            r0.setContentDescription(LocaleController.getString(r3));
            return;
        L8:
            r3 = R.string.AccDescrDualCameraOff;
            goto L9
        }

        @Override // org.telegram.messenger.camera.CameraView
        public void receivedAmplitude(double r3) {
            if (StoryRecorder.m20464$$Nest$fgetrecordControl(this.this$0) == null) goto L6;
            StoryRecorder.m20464$$Nest$fgetrecordControl(this.this$0).setAmplitude(Utilities.clamp((float) (r3 / 1800.0d), 1.0f, 0.0f), true);
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.DualCameraView, org.telegram.messenger.camera.CameraView
        public void toggleDual() {
            super.toggleDual();
            StoryRecorder.m20418$$Nest$fgetdualButton(this.this$0).setValue(isDual());
            ToggleButton r0 = StoryRecorder.m20418$$Nest$fgetdualButton(this.this$0);
            if (isDual() == false) goto L5;
            int r1 = R.string.AccDescrDualCameraOn;
        L6:
            r0.setContentDescription(LocaleController.getString(r1));
            StoryRecorder r2 = this.this$0;
            StoryRecorder.m20545$$Nest$msetCameraFlashModeIcon(r2, StoryRecorder.m20535$$Nest$mgetCurrentFlashMode(r2), true);
            return;
        L5:
            r1 = R.string.AccDescrDualCameraOff;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$26, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass26 extends BaseFragment {
        final /* synthetic */ StoryRecorder this$0;

        /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$26$1, reason: invalid class name */
        public class AnonymousClass1 extends WrappedResourceProvider {
            final /* synthetic */ AnonymousClass26 this$1;

            public AnonymousClass1(AnonymousClass26 r1, Theme.ResourcesProvider r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.WrappedResourceProvider
            public void appendColors() {
                this.sparseIntArray.append(Theme.key_dialogBackground, -14737633);
                this.sparseIntArray.append(Theme.key_windowBackgroundGray, -13421773);
            }
        }

        public AnonymousClass26(StoryRecorder r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Activity getParentActivity() {
            return StoryRecorder.m20391$$Nest$fgetactivity(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Theme.ResourcesProvider getResourceProvider() {
            return new AnonymousClass1(this, StoryRecorder.m20467$$Nest$fgetresourcesProvider(this.this$0));
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public boolean isLightStatusBar() {
            return false;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public boolean presentFragment(BaseFragment r1) {
            StoryRecorder.m20540$$Nest$mopenPremium(this.this$0);
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$27, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass27 extends LimitReachedBottomSheet {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass27(StoryRecorder r7, BaseFragment r8, Context r9, int r10, int r11, Theme.ResourcesProvider r12) {
            this.this$0 = r7;
            super(r8, r9, r10, r11, r12);
            this.storiesCount = StoryRecorder.m20534$$Nest$mgetCount(r7);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$28, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass28 extends BaseFragment {
        final /* synthetic */ StoryRecorder this$0;

        /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$28$1, reason: invalid class name */
        public class AnonymousClass1 extends WrappedResourceProvider {
            final /* synthetic */ AnonymousClass28 this$1;

            public AnonymousClass1(AnonymousClass28 r1, Theme.ResourcesProvider r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.WrappedResourceProvider
            public void appendColors() {
                this.sparseIntArray.append(Theme.key_dialogBackground, -14803426);
                this.sparseIntArray.append(Theme.key_windowBackgroundGray, -16777216);
            }
        }

        public AnonymousClass28(StoryRecorder r1) {
            this.this$0 = r1;
            this.currentAccount = StoryRecorder.m20413$$Nest$fgetcurrentAccount(r1);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Activity getParentActivity() {
            return StoryRecorder.m20391$$Nest$fgetactivity(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Theme.ResourcesProvider getResourceProvider() {
            return new AnonymousClass1(this, StoryRecorder.m20467$$Nest$fgetresourcesProvider(this.this$0));
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public boolean isLightStatusBar() {
            return false;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Dialog showDialog(Dialog r1) {
            r1.show();
            return r1;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$29, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass29 implements Bulletin.Delegate {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass29(StoryRecorder r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public boolean clipWithGradient(int r1) {
            return true;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getTopOffset(int r1) {
            return 0;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends FrameLayout {
        private final Rect leftExclRect;
        private RenderNode renderNode;
        private final Rect rightExclRect;
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass3(StoryRecorder r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.leftExclRect = new Rect();
            this.rightExclRect = new Rect();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            int r0 = Build.VERSION.SDK_INT;
            if (r0 >= 31) goto L5;
        L12:
            boolean r3 = false;
            Canvas r1 = r7;
        L13:
            super.dispatchDraw(r1);
            if (r3 == false) goto L21;
            if (r0 < 31) goto L22;
            this.renderNode.endRecording();
            if (StoryRecorder.m20394$$Nest$fgetblurManager(this.this$0) == null) goto L19;
            StoryRecorder.m20394$$Nest$fgetblurManager(this.this$0).setRenderNode(this, this.renderNode, -14737633);
        L19:
            r7.drawRenderNode(this.renderNode);
            return;
        L22:
            return;
        L21:
            return;
        L5:
            if (r7.isHardwareAccelerated() == false) goto L12;
            if (AndroidUtilities.makingGlobalBlurBitmap == true) goto L12;
            if (this.renderNode != null) goto L11;
            this.renderNode = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("StoryRecorder.PreviewView");
        L11:
            this.renderNode.setPosition(0, 0, getWidth(), getHeight());
            r1 = this.renderNode.beginRecording();
            r3 = true;
            goto L13
        }

        @Override // android.view.View
        public void invalidate() {
            if (StoryRecorder.m20444$$Nest$fgetopenCloseAnimator(this.this$0) != null) goto L5;
        L7:
            super.invalidate();
            return;
        L5:
            if (StoryRecorder.m20444$$Nest$fgetopenCloseAnimator(this.this$0).isRunning() == false) goto L7;
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
            super.onLayout(r4, r5, r6, r7, r8);
            if (Build.VERSION.SDK_INT < 29) goto L6;
            int r72 = r7 - r5;
            int r82 = r8 - r6;
            this.leftExclRect.set(0, r82 - AndroidUtilities.dp(120.0f), AndroidUtilities.dp(40.0f), r82);
            this.rightExclRect.set(r72 - AndroidUtilities.dp(40.0f), r82 - AndroidUtilities.dp(120.0f), r72, r82);
            setSystemGestureExclusionRects(Arrays.asList(new Rect[]{this.leftExclRect, this.rightExclRect}));
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, r4);
            if (StoryRecorder.m20453$$Nest$fgetphotoFilterViewCurvesControl(this.this$0) == null) goto L6;
            StoryRecorder.m20453$$Nest$fgetphotoFilterViewCurvesControl(this.this$0).setActualArea(0.0f, 0.0f, StoryRecorder.m20453$$Nest$fgetphotoFilterViewCurvesControl(this.this$0).getMeasuredWidth(), StoryRecorder.m20453$$Nest$fgetphotoFilterViewCurvesControl(this.this$0).getMeasuredHeight());
        L6:
            if (StoryRecorder.m20452$$Nest$fgetphotoFilterViewBlurControl(this.this$0) == null) goto L9;
            StoryRecorder.m20452$$Nest$fgetphotoFilterViewBlurControl(this.this$0).setActualAreaSize(StoryRecorder.m20452$$Nest$fgetphotoFilterViewBlurControl(this.this$0).getMeasuredWidth(), StoryRecorder.m20452$$Nest$fgetphotoFilterViewBlurControl(this.this$0).getMeasuredHeight());
            return;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (StoryRecorder.m20459$$Nest$fgetpreviewTouchable(this.this$0) == null) goto L7;
            StoryRecorder.m20459$$Nest$fgetpreviewTouchable(this.this$0).onTouch(r2);
            return true;
        L7:
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$30, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass30 extends ImageSpan {
        final /* synthetic */ Drawable val$cameraDrawable;

        public AnonymousClass30(Drawable r1, Drawable r2) {
            this.val$cameraDrawable = r2;
            super(r1);
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(Canvas r3, CharSequence r4, int r5, int r6, float r7, int r8, int r9, int r10, Paint r11) {
            r3.save();
            r3.translate(0.0f, ((r10 - r8) / 2) + AndroidUtilities.dp(1.0f));
            this.val$cameraDrawable.setAlpha(r11.getAlpha());
            super.draw(r3, r4, r5, r6, r7, r8, r9, r10, r11);
            r3.restore();
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return (super.getSize(r1, r2, r3, r4, r5) / 3) * 2;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$31, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass31 extends View {
        final /* synthetic */ StoryRecorder this$0;
        final /* synthetic */ Bitmap val$bitmap;
        final /* synthetic */ Canvas val$bitmapCanvas;
        final /* synthetic */ Paint val$bitmapPaint;
        final /* synthetic */ float val$cx;
        final /* synthetic */ float val$cy;
        final /* synthetic */ boolean val$isDark;
        final /* synthetic */ float val$r;
        final /* synthetic */ float val$x;
        final /* synthetic */ Paint val$xRefPaint;
        final /* synthetic */ float val$y;

        public AnonymousClass31(StoryRecorder r1, Context r2, boolean r3, Canvas r4, float r5, float r6, float r7, Paint r8, Bitmap r9, Paint r10, float r11, float r12) {
            this.this$0 = r1;
            this.val$isDark = r3;
            this.val$bitmapCanvas = r4;
            this.val$cx = r5;
            this.val$cy = r6;
            this.val$r = r7;
            this.val$xRefPaint = r8;
            this.val$bitmap = r9;
            this.val$bitmapPaint = r10;
            this.val$x = r11;
            this.val$y = r12;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            super.onDraw(r7);
            if (this.val$isDark == true) goto L5;
            r7.drawCircle(this.val$cx, this.val$cy, this.val$r * (1.0f - StoryRecorder.m20404$$Nest$fgetchangeDayNightViewProgress(this.this$0)), this.val$bitmapPaint);
        L9:
            r7.save();
            r7.translate(this.val$x, this.val$y);
            StoryRecorder.m20475$$Nest$fgetthemeButton(this.this$0).draw(r7);
            r7.restore();
            return;
        L5:
            if (StoryRecorder.m20404$$Nest$fgetchangeDayNightViewProgress(this.this$0) <= 0.0f) goto L7;
            this.val$bitmapCanvas.drawCircle(this.val$cx, this.val$cy, this.val$r * StoryRecorder.m20404$$Nest$fgetchangeDayNightViewProgress(this.this$0), this.val$xRefPaint);
        L7:
            r7.drawBitmap(this.val$bitmap, 0.0f, 0.0f, this.val$bitmapPaint);
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$32, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass32 implements ValueAnimator.AnimatorUpdateListener {
        boolean changedNavigationBarColor;
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass32(StoryRecorder r1) {
            this.this$0 = r1;
            this.changedNavigationBarColor = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            StoryRecorder.m20492$$Nest$fputchangeDayNightViewProgress(this.this$0, ((Float) r2.getAnimatedValue()).floatValue());
            if (StoryRecorder.m20403$$Nest$fgetchangeDayNightView(this.this$0) == null) goto L6;
            StoryRecorder.m20403$$Nest$fgetchangeDayNightView(this.this$0).invalidate();
        L6:
            if (this.changedNavigationBarColor == false) goto L8;
            return;
        L8:
            if (StoryRecorder.m20404$$Nest$fgetchangeDayNightViewProgress(this.this$0) <= 0.5f) goto L12;
            this.changedNavigationBarColor = true;
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$33, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass33 extends AnimatorListenerAdapter {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass33(StoryRecorder r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            if (StoryRecorder.m20403$$Nest$fgetchangeDayNightView(this.this$0) != null) goto L5;
        L8:
            StoryRecorder.m20491$$Nest$fputchangeDayNightViewAnimator(this.this$0, null);
            super.onAnimationEnd(r4);
            return;
        L5:
            if (StoryRecorder.m20403$$Nest$fgetchangeDayNightView(this.this$0).getParent() == null) goto L7;
            ((ViewGroup) StoryRecorder.m20403$$Nest$fgetchangeDayNightView(this.this$0).getParent()).removeView(StoryRecorder.m20403$$Nest$fgetchangeDayNightView(this.this$0));
        L7:
            StoryRecorder.m20490$$Nest$fputchangeDayNightView(this.this$0, null);
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$34, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass34 extends AnimatorListenerAdapter {
        final /* synthetic */ StoryRecorder this$0;
        final /* synthetic */ View val$view;
        final /* synthetic */ boolean val$visible;

        public AnonymousClass34(StoryRecorder r1, boolean r2, View r3) {
            this.this$0 = r1;
            this.val$visible = r2;
            this.val$view = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            StoryRecorder.m20550$$Nest$mupdateActionBarButtonsOffsets(this.this$0);
            if (this.val$visible == true) goto L6;
            this.val$view.setVisibility(8);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$35, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass35 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass35(StoryRecorder r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r1) {
            StoryRecorder.m20550$$Nest$mupdateActionBarButtonsOffsets(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$36, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass36 extends CropEditor {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass36(StoryRecorder r1, Context r2, PreviewView r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Stories.recorder.CropEditor
        public void close() {
            this.this$0.switchToEditMode(-1, true);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$37, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass37 extends CropInlineEditor {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass37(StoryRecorder r1, Context r2, PreviewView r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Stories.recorder.CropInlineEditor
        public void close() {
            this.this$0.switchToEditMode(-1, true);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends FrameLayout {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass4(StoryRecorder r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setTranslationY(float r2) {
            if (getTranslationY() != r2) goto L5;
            return;
        L5:
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0) == null) goto L9;
            super.setTranslationY(r2);
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).updateMentionsLayoutPosition();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 implements Bulletin.Delegate {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass5(StoryRecorder r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public boolean clipWithGradient(int r1) {
            return true;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return super.getBottomOffset(r1);
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getTopOffset(int r1) {
            return AndroidUtilities.dp(56.0f);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends CollageLayoutView2 {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass6(StoryRecorder r1, Context r2, BlurringShader.BlurManager r3, FrameLayout r4, Theme.ResourcesProvider r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Stories.recorder.CollageLayoutView2
        public void onLayoutUpdate(CollageLayout r5) {
            StoryRecorder.m20408$$Nest$fgetcollageListView(this.this$0).setVisible(false, true);
            if (r5 != null) goto L5;
        L7:
            StoryRecorder.m20406$$Nest$fgetcollageButton(this.this$0).setSelected(false, true);
        L8:
            StoryRecorder.m20549$$Nest$mupdateActionBarButtons(this.this$0, true);
            if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0) == null) goto L12;
            StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0).setMultipleOnClick(StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).hasLayout());
            StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0).setMaxCount(Math.min(10, CollageLayout.getMaxCount() - StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$0).getFilledCount()));
            return;
        L12:
            return;
        L5:
            if (r5.parts.size() <= 1) goto L7;
            CollageLayoutButton r0 = StoryRecorder.m20406$$Nest$fgetcollageButton(this.this$0);
            StoryRecorder.m20504$$Nest$fputlastCollageLayout(this.this$0, r5);
            r0.setIcon(new CollageLayoutButton.CollageLayoutDrawable(r5), true);
            StoryRecorder.m20406$$Nest$fgetcollageButton(this.this$0).setSelected(true, true);
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 extends PreviewView {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass7(StoryRecorder r1, Context r2, BlurringShader.BlurManager r3, PreviewView.TextureViewHolder r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Stories.recorder.PreviewView
        public boolean additionalTouchEvent(MotionEvent r2) {
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0) == null) goto L9;
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).isRecording() == false) goto L9;
            return false;
        L9:
            return StoryRecorder.m20450$$Nest$fgetphotoFilterEnhanceView(this.this$0).onTouch(r2);
        }

        @Override // org.telegram.ui.Stories.recorder.PreviewView
        public void applyMatrix() {
            super.applyMatrix();
            StoryRecorder.m20523$$Nest$mapplyFilterMatrix(this.this$0);
        }

        @Override // org.telegram.ui.Stories.recorder.PreviewView
        public void invalidateTextureViewHolder() {
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0) != null) goto L5;
            return;
        L5:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).isRepostMessage == true) goto L7;
            return;
        L7:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).isVideo == true) goto L9;
            return;
        L9:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0) != null) goto L11;
            return;
        L11:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).entitiesView == null) goto L26;
            int r0 = 0;
        L14:
            if (r0 >= StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).entitiesView.getChildCount()) goto L27;
            View r1 = StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).entitiesView.getChildAt(r0);
            if ((r1 instanceof MessageEntityView) == false) goto L18;
            ((MessageEntityView) r1).invalidateAll();
        L18:
            r0 = r0 + 1;
            goto L14
        L27:
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.PreviewView
        public void onAudioChanged() {
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0) == null) goto L12;
            PaintView r0 = StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0);
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0) != null) goto L7;
        L9:
            boolean r2 = false;
        L10:
            r0.setHasAudio(r2);
            return;
        L7:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).audioPath == null) goto L9;
            r2 = true;
            goto L10
        }

        @Override // org.telegram.ui.Stories.recorder.PreviewView
        public void onEntityDraggedBottom(boolean r1) {
            StoryRecorder.m20458$$Nest$fgetpreviewHighlight(this.this$0).updateCaption(StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).getText());
        }

        @Override // org.telegram.ui.Stories.recorder.PreviewView
        public void onEntityDraggedTop(boolean r3) {
            StoryRecorder.m20458$$Nest$fgetpreviewHighlight(this.this$0).show(true, r3, StoryRecorder.m20390$$Nest$fgetactionBarContainer(this.this$0));
        }

        @Override // org.telegram.ui.Stories.recorder.PreviewView
        public void onRoundRemove() {
            if (StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0) == null) goto L6;
            StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).setupRound(null, null, true);
        L6:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0) == null) goto L9;
            StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).deleteRound();
        L9:
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0) == null) goto L12;
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).setHasRoundVideo(false);
        L12:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0) != null) goto L14;
            return;
        L14:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).round == null) goto L18;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).round.delete();     // Catch: Exception -> L23
        L16:
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).round = null;
        L18:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundThumb == null) goto L29;
            new File(StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundThumb).delete();     // Catch: Exception -> L22
        L20:
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundThumb = null;
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.PreviewView
        public void onRoundSelectChange(boolean r2) {
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0) == null) goto L20;
            if (r2 == false) goto L7;
        L10:
            if (r2 == true) goto L12;
            return;
        L12:
            if ((StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).getSelectedEntity() instanceof RoundView) == false) goto L14;
            return;
        L14:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).findRoundView() == null) goto L19;
            StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).selectEntity(StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).findRoundView());
            return;
        L19:
            return;
        L7:
            if ((StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).getSelectedEntity() instanceof RoundView) == false) goto L10;
            StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).selectEntity(null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$8, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass8 extends CaptionStory {
        private final Path path;
        final /* synthetic */ StoryRecorder this$0;

        /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$8$1, reason: invalid class name */
        public class AnonymousClass1 extends ClickableSpan {
            final /* synthetic */ AnonymousClass8 this$1;

            public AnonymousClass1(AnonymousClass8 r1) {
                this.this$1 = r1;
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View r1) {
                StoryRecorder.m20540$$Nest$mopenPremium(this.this$1.this$0);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint r1) {
                r1.setUnderlineText(false);
            }
        }

        /* renamed from: $r8$lambda$1dTcR2kSg975WYnQlFTf8Q-t6F4, reason: not valid java name */
        public static /* synthetic */ void m20557$r8$lambda$1dTcR2kSg975WYnQlFTf8Qt6F4(AnonymousClass8 r0) {
            r0.lambda$putRecorder$1();
        }

        /* renamed from: $r8$lambda$2gBK8Fo-GHMOEwZPEkYTOcZzfq8, reason: not valid java name */
        public static /* synthetic */ void m20558$r8$lambda$2gBK8FoGHMOEwZPEkYTOcZzfq8(AnonymousClass8 r0, RoundVideoRecorder r1, File r2, String r3, Long r4) {
            r0.lambda$putRecorder$0(r1, r2, r3, r4);
        }

        public AnonymousClass8(StoryRecorder r1, Context r2, FrameLayout r3, SizeNotifierFrameLayout r4, FrameLayout r5, Theme.ResourcesProvider r6, BlurringShader.BlurManager r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
            this.path = new Path();
        }

        private /* synthetic */ void lambda$putRecorder$0(RoundVideoRecorder r5, File r6, String r7, Long r8) {
            if (StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0) == null) goto L6;
            StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).mute(false);
            StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).seek(0);
        L6:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0) == null) goto L15;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).round = r6;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundThumb = r7;
            StoryEntry r62 = StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0);
            r62.roundDuration = r8.longValue();
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundLeft = 0.0f;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundRight = 1.0f;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundOffset = 0;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundVolume = 1.0f;
            StoryRecorder.m20530$$Nest$mcreatePhotoPaintView(this.this$0);
            if (StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0) != null) goto L10;
        L13:
            r5.destroy(false);
            return;
        L10:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0) == null) goto L13;
            RoundView r63 = StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).createRound(StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundThumb, true);
            setHasRoundVideo(true);
            StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).setupRound(StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0), r63, true);
            r5.hideTo(r63);
            return;
        }

        private /* synthetic */ void lambda$putRecorder$1() {
            if (StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0) == null) goto L6;
            StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).mute(false);
            StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).seek(0);
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.CaptionStory
        public boolean canRecord() {
            if (CameraView.isCameraAllowed() == true) goto L7;
            return false;
        L7:
            return StoryRecorder.m20541$$Nest$mrequestAudioPermission(this.this$0);
        }

        @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
        public boolean captionLimitToast() {
            if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == false) goto L5;
            return false;
        L5:
            Bulletin r0 = Bulletin.getVisibleBulletin();
            if (r0 != null) goto L8;
        L10:
            int r02 = MessagesController.getInstance(this.currentAccount).storyCaptionLengthLimitPremium;
            SpannableStringBuilder r03 = AndroidUtilities.replaceTags(LocaleController.formatPluralString("CaptionPremiumSubtitle", Math.round(r02 / MessagesController.getInstance(this.currentAccount).storyCaptionLengthLimitDefault), new Object[]{_UrlKt.FRAGMENT_ENCODE_SET + r02}));
            int r3 = r03.toString().indexOf("__");
            if (r3 < 0) goto L15;
            r03.replace(r3, r3 + 2, _UrlKt.FRAGMENT_ENCODE_SET);
            int r4 = r03.toString().indexOf("__");
            if (r4 < 0) goto L15;
            r03.replace(r4, r4 + 2, _UrlKt.FRAGMENT_ENCODE_SET);
            r03.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_chat_messageLinkIn, this.resourcesProvider)), r3, r4, 33);
            r03.setSpan(new AnonymousClass1(this), r3, r4, 33);
        L15:
            Bulletin r8 = BulletinFactory.of(StoryRecorder.m20400$$Nest$fgetcaptionContainer(this.this$0), this.resourcesProvider).createSimpleBulletin(R.raw.caption_limit, LocaleController.getString(R.string.CaptionPremiumTitle), r03);
            r8.tag = 2;
            r8.setDuration(5000);
            r8.show(false);
            return true;
        L8:
            if (r0.tag != 2) goto L10;
            return false;
        }

        @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
        public boolean customBlur() {
            return StoryRecorder.m20394$$Nest$fgetblurManager(this.this$0).hasRenderNode();
        }

        @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
        public void drawBlur(BlurringShader.StoryBlurDrawer r1, Canvas r2, RectF r3, float r4, boolean r5, float r6, float r7, boolean r8, float r9) {
            if (r2.isHardwareAccelerated() == true) goto L5;
            return;
        L5:
            r2.save();
            this.path.rewind();
            this.path.addRoundRect(r3, r4, r4, Path.Direction.CW);
            r2.clipPath(this.path);
            r2.translate(r6, r7);
            r1.drawRect(r2, 0.0f, 0.0f, r9);
            r2.restore();
        }

        @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
        public void drawBlurBitmap(Bitmap r2, float r3) {
            StoryRecorder.m20486$$Nest$fgetwindowView(this.this$0).drawBlurBitmap(r2, r3);
            super.drawBlurBitmap(r2, r3);
        }

        @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
        public boolean drawOver2FromParent() {
            return true;
        }

        @Override // org.telegram.ui.Stories.recorder.CaptionStory
        public int getTimelineHeight() {
            if (StoryRecorder.m20482$$Nest$fgetvideoTimelineContainerView(this.this$0) != null) goto L5;
            return 0;
        L5:
            if (StoryRecorder.m20477$$Nest$fgettimelineView(this.this$0) != null) goto L7;
            return 0;
        L7:
            if (StoryRecorder.m20477$$Nest$fgettimelineView(this.this$0).getVisibility() == 0) goto L9;
            return 0;
        L9:
            return StoryRecorder.m20477$$Nest$fgettimelineView(this.this$0).getTimelineHeight();
        }

        @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
        public boolean ignoreTouches(float r8, float r9) {
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0) != null) goto L5;
        L19:
            return false;
        L5:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).entitiesView == null) goto L19;
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).keyboardShown == true) goto L19;
            float r82 = r8 + StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).getX();
            float r92 = r9 + StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).getY();
            float r83 = r82 + StoryRecorder.m20400$$Nest$fgetcaptionContainer(this.this$0).getX();
            float r93 = r92 + StoryRecorder.m20400$$Nest$fgetcaptionContainer(this.this$0).getY();
            float r84 = r83 - StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0).getX();
            float r94 = r93 - StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0).getY();
            int r0 = 0;
        L11:
            if (r0 >= StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).entitiesView.getChildCount()) goto L19;
            View r2 = StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).entitiesView.getChildAt(r0);
            if ((r2 instanceof EntityView) == false) goto L18;
            RectOld r22 = ((EntityView) r2).getSelectionBounds();
            RectF r3 = AndroidUtilities.rectTmp;
            float r4 = r22.x;
            float r5 = r22.y;
            r3.set(r4, r5, r22.width + r4, r22.height + r5);
            if (r3.contains(r84, r94) == false) goto L18;
            return true;
        L18:
            r0 = r0 + 1;
            goto L11
        }

        @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
        public void invalidateDrawOver2() {
            if (StoryRecorder.m20402$$Nest$fgetcaptionEditOverlay(this.this$0) == null) goto L6;
            StoryRecorder.m20402$$Nest$fgetcaptionEditOverlay(this.this$0).invalidate();
            return;
        }

        @Override // org.telegram.ui.Stories.recorder.CaptionContainerView
        public void onCaptionLimitUpdate(boolean r3) {
            PreviewButtons r0 = StoryRecorder.m20455$$Nest$fgetpreviewButtons(this.this$0);
            if (StoryRecorder.m20481$$Nest$fgetvideoError(this.this$0) == true) goto L12;
            if (r3 == true) goto L12;
            if (MessagesController.getInstance(this.currentAccount).getStoriesController().hasStoryLimit(StoryRecorder.m20534$$Nest$mgetCount(this.this$0)) == true) goto L8;
        L11:
            boolean r2 = true;
        L13:
            r0.setShareEnabled(r2);
            return;
        L8:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0) == null) goto L12;
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).isEdit == true) goto L11;
        L12:
            r2 = false;
            goto L13
        }

        @Override // org.telegram.ui.Stories.recorder.CaptionStory
        public void putRecorder(RoundVideoRecorder r4) {
            if (StoryRecorder.m20416$$Nest$fgetcurrentRoundRecorder(this.this$0) == null) goto L6;
            StoryRecorder.m20416$$Nest$fgetcurrentRoundRecorder(this.this$0).destroy(true);
        L6:
            if (StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0) == null) goto L8;
            StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).mute(true);
            StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).seek(0);
        L8:
            r4.onDone(new StoryRecorder$8$$ExternalSyntheticLambda0(this, r4));
            r4.onDestroy(new StoryRecorder$8$$ExternalSyntheticLambda1(this));
            FrameLayout r0 = StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0);
            StoryRecorder.m20494$$Nest$fputcurrentRoundRecorder(this.this$0, r4);
            r0.addView(r4, LayoutHelper.createFrame(-1, -1.0f));
        }

        @Override // org.telegram.ui.Stories.recorder.CaptionStory
        public void removeRound() {
            if (StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0) == null) goto L6;
            StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).setupRound(null, null, true);
        L6:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0) == null) goto L9;
            StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).deleteRound();
        L9:
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0) == null) goto L12;
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).setHasRoundVideo(false);
        L12:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0) != null) goto L14;
            return;
        L14:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).round == null) goto L18;
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).round.delete();     // Catch: Exception -> L23
        L16:
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).round = null;
        L18:
            if (StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundThumb == null) goto L29;
            new File(StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundThumb).delete();     // Catch: Exception -> L22
        L20:
            StoryRecorder.m20447$$Nest$fgetoutputEntry(this.this$0).roundThumb = null;
            return;
        }

        @Override // android.view.View
        public void setVisibility(int r1) {
            super.setVisibility(r1);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$9, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass9 implements Bulletin.Delegate {
        final /* synthetic */ StoryRecorder this$0;

        public AnonymousClass9(StoryRecorder r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).getEditTextHeight() + AndroidUtilities.dp(12.0f);
        }
    }

    /* loaded from: classes7.dex */
    public interface ClosingViewProvider {
        SourceView getView(long r1);

        void preLayout(long r1, Runnable r3);
    }

    /* loaded from: classes7.dex */
    public class ContainerView extends FrameLayout {
        final /* synthetic */ StoryRecorder this$0;
        private LinearGradient topGradient;
        private final Paint topGradientPaint;
        private float translationY1;
        private float translationY2;

        public ContainerView(StoryRecorder r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.topGradientPaint = new Paint(1);
        }

        private void measureChildExactly(View r1, int r2, int r3) {
            r1.measure(View.MeasureSpec.makeMeasureSpec(r2, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r3, TLObject.FLAG_30));
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r9, View r10, long r11) {
            boolean r112 = super.drawChild(r9, r10, r11);
            if (r10 == StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0)) goto L5;
        L12:
            return r112;
        L5:
            if (StoryRecorder.m20480$$Nest$fgetunderStatusBar(this.this$0) == false) goto L7;
            float r2 = AndroidUtilities.statusBarHeight;
        L9:
            if (this.topGradient != null) goto L11;
            LinearGradient r0 = new LinearGradient(0.0f, r2, 0.0f, r2 + AndroidUtilities.dp(72.0f), new int[]{TLObject.FLAG_30, 0}, new float[]{r2 / (AndroidUtilities.dp(72.0f) + r2), 1.0f}, Shader.TileMode.CLAMP);
            this.topGradient = r0;
            this.topGradientPaint.setShader(r0);
        L11:
            this.topGradientPaint.setAlpha(255);
            RectF r102 = AndroidUtilities.rectTmp;
            r102.set(0.0f, 0.0f, getWidth(), AndroidUtilities.dp(84.0f) + r2);
            r9.drawRoundRect(r102, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), this.topGradientPaint);
            goto L12
        L7:
            r2 = 0.0f;
            goto L9
        }

        public float getTranslationY1() {
            return this.translationY1;
        }

        public float getTranslationY2() {
            return this.translationY2;
        }

        @Override // android.view.View
        public void invalidate() {
            if (StoryRecorder.m20444$$Nest$fgetopenCloseAnimator(this.this$0) != null) goto L5;
        L7:
            super.invalidate();
            return;
        L5:
            if (StoryRecorder.m20444$$Nest$fgetopenCloseAnimator(this.this$0).isRunning() == false) goto L7;
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
            if (StoryRecorder.m20480$$Nest$fgetunderStatusBar(this.this$0) == false) goto L5;
            int r42 = StoryRecorder.m20438$$Nest$fgetinsetTop(this.this$0);
        L6:
            int r62 = getMeasuredWidth();
            int r72 = getMeasuredHeight();
            StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0).layout(0, 0, StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0));
            StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0).setPivotX(StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0) * 0.5f);
            StoryRecorder.m20390$$Nest$fgetactionBarContainer(this.this$0).layout(0, r42, StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), StoryRecorder.m20390$$Nest$fgetactionBarContainer(this.this$0).getMeasuredHeight() + r42);
            StoryRecorder.m20410$$Nest$fgetcontrolContainer(this.this$0).layout(0, StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0) - StoryRecorder.m20410$$Nest$fgetcontrolContainer(this.this$0).getMeasuredHeight(), StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0));
            StoryRecorder.m20441$$Nest$fgetnavbarContainer(this.this$0).layout(0, StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0), StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0) + StoryRecorder.m20441$$Nest$fgetnavbarContainer(this.this$0).getMeasuredHeight());
            StoryRecorder.m20400$$Nest$fgetcaptionContainer(this.this$0).layout(0, 0, StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0));
            if (StoryRecorder.m20402$$Nest$fgetcaptionEditOverlay(this.this$0) == null) goto L9;
            StoryRecorder.m20402$$Nest$fgetcaptionEditOverlay(this.this$0).layout(0, 0, r62, r72);
        L9:
            StoryRecorder.m20420$$Nest$fgetflashViews(this.this$0).foregroundView.layout(0, 0, r62, r72);
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).mentionContainer == null) goto L13;
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).mentionContainer.layout(0, 0, StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0));
            StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).updateMentionsLayoutPosition();
        L13:
            if (StoryRecorder.m20451$$Nest$fgetphotoFilterView(this.this$0) == null) goto L16;
            StoryRecorder.m20451$$Nest$fgetphotoFilterView(this.this$0).layout(0, 0, StoryRecorder.m20451$$Nest$fgetphotoFilterView(this.this$0).getMeasuredWidth(), StoryRecorder.m20451$$Nest$fgetphotoFilterView(this.this$0).getMeasuredHeight());
        L16:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0) == null) goto L18;
            StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).layout(0, 0, StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).getMeasuredWidth(), StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).getMeasuredHeight());
        L18:
            int r43 = 0;
        L20:
            if (r43 >= getChildCount()) goto L25;
            View r82 = getChildAt(r43);
            if ((r82 instanceof ItemOptions.DimView) == false) goto L24;
            r82.layout(0, 0, r62, r72);
        L24:
            r43 = r43 + 1;
            goto L20
        L25:
            setPivotX(r62 / 2.0f);
            setPivotY((-r72) * 0.2f);
            return;
        L5:
            r42 = 0;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            int r42 = View.MeasureSpec.getSize(r4);
            int r52 = View.MeasureSpec.getSize(r5);
            measureChildExactly(StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0), StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0));
            StoryRecorder.m20523$$Nest$mapplyFilterMatrix(this.this$0);
            measureChildExactly(StoryRecorder.m20390$$Nest$fgetactionBarContainer(this.this$0), StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), AndroidUtilities.dp(150.0f));
            measureChildExactly(StoryRecorder.m20410$$Nest$fgetcontrolContainer(this.this$0), StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), AndroidUtilities.dp(220.0f));
            measureChildExactly(StoryRecorder.m20441$$Nest$fgetnavbarContainer(this.this$0), StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), StoryRecorder.m20479$$Nest$fgetunderControls(this.this$0));
            measureChildExactly(StoryRecorder.m20400$$Nest$fgetcaptionContainer(this.this$0), StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0));
            measureChildExactly(StoryRecorder.m20420$$Nest$fgetflashViews(this.this$0).foregroundView, r42, r52);
            if (StoryRecorder.m20402$$Nest$fgetcaptionEditOverlay(this.this$0) == null) goto L6;
            measureChildExactly(StoryRecorder.m20402$$Nest$fgetcaptionEditOverlay(this.this$0), r42, r52);
        L6:
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).mentionContainer == null) goto L9;
            measureChildExactly(StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).mentionContainer, StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0));
        L9:
            if (StoryRecorder.m20451$$Nest$fgetphotoFilterView(this.this$0) == null) goto L12;
            measureChildExactly(StoryRecorder.m20451$$Nest$fgetphotoFilterView(this.this$0), r42, r52);
        L12:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0) == null) goto L14;
            measureChildExactly(StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0), r42, r52);
        L14:
            int r0 = 0;
        L16:
            if (r0 >= getChildCount()) goto L21;
            View r1 = getChildAt(r0);
            if ((r1 instanceof ItemOptions.DimView) == false) goto L20;
            measureChildExactly(r1, r42, r52);
        L20:
            r0 = r0 + 1;
            goto L16
        L21:
            setMeasuredDimension(r42, r52);
        }

        @Override // android.view.View
        public void setTranslationY(float r4) {
            this.translationY1 = r4;
            super.setTranslationY(this.translationY2 + r4);
            StoryRecorder.m20495$$Nest$fputdismissProgress(this.this$0, Utilities.clamp((r4 / getMeasuredHeight()) * 4.0f, 1.0f, 0.0f));
            StoryRecorder.m20525$$Nest$mcheckBackgroundVisibility(this.this$0);
            StoryRecorder.m20486$$Nest$fgetwindowView(this.this$0).invalidate();
            float r1 = 1.0f - (Utilities.clamp(getTranslationY() / AndroidUtilities.dp(320.0f), 1.0f, 0.0f) * 0.1f);
            setScaleX(r1);
            setScaleY(r1);
        }

        public void setTranslationY2(float r2) {
            float r0 = this.translationY1;
            this.translationY2 = r2;
            super.setTranslationY(r0 + r2);
        }

        public void updateBackground() {
            if (StoryRecorder.m20446$$Nest$fgetopenType(this.this$0) != 0) goto L6;
            setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(12.0f), -16777216));
            return;
        L6:
            setBackground(null);
        }
    }

    /* loaded from: classes7.dex */
    public static class SourceView {
        Drawable backgroundDrawable;
        ImageReceiver backgroundImageReceiver;
        Paint backgroundPaint;
        boolean hasShadow;
        Drawable iconDrawable;
        int iconSize;
        float rounding;
        RectF screenRect;
        int type;
        View view;

        /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$SourceView$1, reason: invalid class name */
        public class AnonymousClass1 extends SourceView {
            final /* synthetic */ ProfileActivity.AvatarImageView val$avatarImage;

            public AnonymousClass1(ProfileActivity.AvatarImageView r1) {
                this.val$avatarImage = r1;
            }

            @Override // org.telegram.ui.Stories.recorder.StoryRecorder.SourceView
            public void hide() {
                ProfileActivity.AvatarImageView r1 = this.val$avatarImage;
                r1.drawAvatar = false;
                r1.invalidate();
            }

            @Override // org.telegram.ui.Stories.recorder.StoryRecorder.SourceView
            public void show(boolean r1) {
                ProfileActivity.AvatarImageView r0 = this.val$avatarImage;
                r0.drawAvatar = true;
                r0.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$SourceView$2, reason: invalid class name */
        public class AnonymousClass2 extends SourceView {
            final /* synthetic */ StoryViewer val$storyViewer;

            public AnonymousClass2(StoryViewer r1) {
                this.val$storyViewer = r1;
            }

            @Override // org.telegram.ui.Stories.recorder.StoryRecorder.SourceView
            public void hide() {
                PeerStoriesView r1 = this.val$storyViewer.getCurrentPeerView();
                if (r1 == null) goto L6;
                r1.animateOut(true);
                return;
            }

            @Override // org.telegram.ui.Stories.recorder.StoryRecorder.SourceView
            public void show(boolean r2) {
                PeerStoriesView r22 = this.val$storyViewer.getCurrentPeerView();
                if (r22 == null) goto L5;
                r22.animateOut(false);
            L5:
                View r23 = this.view;
                if (r23 == null) goto L9;
                r23.setTranslationX(0.0f);
                this.view.setTranslationY(0.0f);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$SourceView$4, reason: invalid class name */
        public class AnonymousClass4 extends SourceView {
            final /* synthetic */ BackupImageView val$imageView;

            /* renamed from: $r8$lambda$-cj9lHMozBdXrsj7EzHzmVeEpZQ, reason: not valid java name */
            public static /* synthetic */ void m20559$r8$lambda$cj9lHMozBdXrsj7EzHzmVeEpZQ(BackupImageView r1) {
                r1.setVisibility(8);
            }

            public AnonymousClass4(BackupImageView r1) {
                this.val$imageView = r1;
            }

            @Override // org.telegram.ui.Stories.recorder.StoryRecorder.SourceView
            public void hide() {
                BackupImageView r1 = this.val$imageView;
                r1.post(new StoryRecorder$SourceView$4$$ExternalSyntheticLambda0(r1));
            }

            @Override // org.telegram.ui.Stories.recorder.StoryRecorder.SourceView
            public void show(boolean r1) {
                this.val$imageView.setVisibility(0);
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.StoryRecorder$SourceView$5, reason: invalid class name */
        public class AnonymousClass5 extends SourceView {
            final /* synthetic */ float val$radius;
            final /* synthetic */ DialogStoriesCell.StoryCell val$storyCell;

            public static /* synthetic */ void $r8$lambda$CddIKtIMy4frrCudqDPnaYUkxdE(DialogStoriesCell.StoryCell r1) {
                r1.drawAvatar = false;
                r1.invalidate();
            }

            public AnonymousClass5(DialogStoriesCell.StoryCell r1, float r2) {
                this.val$storyCell = r1;
                this.val$radius = r2;
            }

            @Override // org.telegram.ui.Stories.recorder.StoryRecorder.SourceView
            public void drawAbove(Canvas r6, float r7) {
                DialogStoriesCell.StoryCell r0 = this.val$storyCell;
                float r5 = this.val$radius;
                r0.drawPlus(r6, r5, r5, (float) Math.pow(r7, 16.0d));
            }

            @Override // org.telegram.ui.Stories.recorder.StoryRecorder.SourceView
            public void hide() {
                DialogStoriesCell.StoryCell r1 = this.val$storyCell;
                r1.post(new StoryRecorder$SourceView$5$$ExternalSyntheticLambda0(r1));
            }

            @Override // org.telegram.ui.Stories.recorder.StoryRecorder.SourceView
            public void show(boolean r5) {
                DialogStoriesCell.StoryCell r0 = this.val$storyCell;
                r0.drawAvatar = true;
                r0.invalidate();
                if (r5 == false) goto L6;
                this.val$storyCell.getLocationInWindow(new int[2]);
                LaunchActivity.makeRipple(r5[0] + (this.val$storyCell.getWidth() / 2.0f), r5[1] + (this.val$storyCell.getHeight() / 2.0f), 1.0f);
                return;
            }
        }

        public SourceView() {
            this.type = 0;
            this.screenRect = new RectF();
        }

        public static SourceView fromAvatarImage(ProfileActivity.AvatarImageView r7, boolean r8) {
            if (r7 != null) goto L4;
            return null;
        L4:
            if (r7.getRootView() == null) goto L14;
            float r0 = ((View) r7.getParent()).getScaleX();
            float r1 = r7.getImageReceiver().getImageWidth() * r0;
            if (r8 == false) goto L9;
            float r82 = 0.32f * r1;
        L10:
            AnonymousClass1 r2 = new AnonymousClass1(r7);
            float[] r3 = new float[2];
            r7.getRootView().getLocationOnScreen(new int[2]);
            AndroidUtilities.getViewPositionInParent(r7, (ViewGroup) r7.getRootView(), r3);
            float r6 = (r4[0] + r3[0]) + (r7.getImageReceiver().getImageX() * r0);
            float r4 = (r4[1] + r3[1]) + (r7.getImageReceiver().getImageY() * r0);
            r2.screenRect.set(r6, r4, r6 + r1, r1 + r4);
            r2.backgroundImageReceiver = r7.getImageReceiver();
            r2.rounding = r82;
            return r2;
        L9:
            r82 = r1;
            goto L10
        L14:
            return null;
        }

        public static SourceView fromShareCell(ShareDialogCell r11) {
            if (r11 != null) goto L5;
            return null;
        L5:
            BackupImageView r1 = r11.getImageView();
            AnonymousClass4 r2 = new AnonymousClass4(r1);
            int[] r3 = new int[2];
            r1.getLocationOnScreen(r3);
            r2.screenRect.set(r3[0], r3[1], r6 + r1.getWidth(), r3[1] + r1.getHeight());
            r2.backgroundDrawable = new ShareDialogCell.RepostStoryDrawable(r1.getContext(), null, false, r11.resourcesProvider);
            r2.rounding = Math.max(r2.screenRect.width(), r2.screenRect.height()) / 2.0f;
            return r2;
        }

        public static SourceView fromStoryCell(DialogStoriesCell.StoryCell r7) {
            if (r7 != null) goto L4;
            return null;
        L4:
            if (r7.getRootView() == null) goto L10;
            float r0 = r7.avatarImage.getImageWidth();
            AnonymousClass5 r3 = new AnonymousClass5(r7, r0 / 2.0f);
            float[] r2 = new float[2];
            r7.getRootView().getLocationOnScreen(new int[2]);
            AndroidUtilities.getViewPositionInParent(r7, (ViewGroup) r7.getRootView(), r2);
            float r6 = (r4[0] + r2[0]) + r7.avatarImage.getImageX();
            float r4 = (r4[1] + r2[1]) + r7.avatarImage.getImageY();
            r3.screenRect.set(r6, r4, r6 + r0, r0 + r4);
            r3.backgroundImageReceiver = r7.avatarImage;
            r3.rounding = Math.max(r3.screenRect.width(), r3.screenRect.height()) / 2.0f;
            return r3;
        L10:
            return null;
        }

        public static SourceView fromStoryViewer(StoryViewer r3) {
            if (r3 != null) goto L5;
            return null;
        L5:
            AnonymousClass2 r1 = new AnonymousClass2(r3);
            if (r3.getStoryRect(r1.screenRect) == true) goto L8;
            return null;
        L8:
            r1.type = 1;
            r1.rounding = AndroidUtilities.dp(8.0f);
            PeerStoriesView r32 = r3.getCurrentPeerView();
            if (r32 == null) goto L11;
            r1.view = r32.storyContainer;
        L11:
            return r1;
        }

        public void drawAbove(Canvas r1, float r2) {
        }

        public abstract void hide();

        public abstract void show(boolean r1);
    }

    /* loaded from: classes7.dex */
    public interface Touchable {
        boolean onTouch(MotionEvent r1);
    }

    /* loaded from: classes7.dex */
    public class WindowView extends SizeNotifierFrameLayout {
        private boolean allowModeScroll;
        private boolean flingDetected;
        private GestureDetectorFixDoubleTap gestureDetector;
        private boolean ignoreLayout;
        private ScaleGestureDetector scaleGestureDetector;
        private boolean scaling;
        private float stx;
        private float sty;
        final /* synthetic */ StoryRecorder this$0;
        private boolean touchInCollageList;
        private float ty;

        public final class GestureListener extends GestureDetectorFixDoubleTap.OnGestureListener {
            final /* synthetic */ WindowView this$1;

            private GestureListener(WindowView r1) {
                this.this$1 = r1;
            }

            public /* synthetic */ GestureListener(WindowView r1, StoryRecorderIA r2) {
                this(r1);
            }

            @Override // org.telegram.ui.Components.GestureDetectorFixDoubleTap.OnGestureListener
            public boolean hasDoubleTap(MotionEvent r1) {
                if (StoryRecorder.m20415$$Nest$fgetcurrentPage(this.this$1.this$0) == 0) goto L5;
                return false;
            L5:
                if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0) != null) goto L7;
                return false;
            L7:
                if (StoryRecorder.m20393$$Nest$fgetawaitingPlayer(this.this$1.this$0) == false) goto L9;
                return false;
            L9:
                if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0).isInited() == true) goto L11;
                return false;
            L11:
                if (StoryRecorder.m20472$$Nest$fgettakingPhoto(this.this$1.this$0) == false) goto L13;
                return false;
            L13:
                if (StoryRecorder.m20464$$Nest$fgetrecordControl(this.this$1.this$0).isTouch() == false) goto L15;
                return false;
            L15:
                if (StoryRecorder.m20462$$Nest$fgetqrLinkView(this.this$1.this$0) == null) goto L19;
                if (StoryRecorder.m20462$$Nest$fgetqrLinkView(this.this$1.this$0).inTouch() == false) goto L19;
                return false;
            L19:
                if (StoryRecorder.m20537$$Nest$misGalleryOpen(this.this$1.this$0) == false) goto L21;
                return false;
            L21:
                if (StoryRecorder.m20431$$Nest$fgetgalleryListViewOpening(this.this$1.this$0) != null) goto L33;
                return true;
            L33:
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent r2) {
                if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0) != null) goto L5;
                return false;
            L5:
                if (StoryRecorder.m20393$$Nest$fgetawaitingPlayer(this.this$1.this$0) == false) goto L7;
                return false;
            L7:
                if (StoryRecorder.m20472$$Nest$fgettakingPhoto(this.this$1.this$0) == false) goto L9;
                return false;
            L9:
                if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0).isInited() == true) goto L11;
                return false;
            L11:
                if (StoryRecorder.m20415$$Nest$fgetcurrentPage(this.this$1.this$0) == 0) goto L13;
                return false;
            L13:
                if (StoryRecorder.m20439$$Nest$fgetmode(this.this$1.this$0) == (-1)) goto L28;
                StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0).switchCamera();
                StoryRecorder.m20464$$Nest$fgetrecordControl(this.this$1.this$0).rotateFlip(180.0f);
                StoryRecorder r22 = this.this$1.this$0;
                StoryRecorder.m20543$$Nest$msaveCameraFace(r22, StoryRecorder.m20398$$Nest$fgetcameraView(r22).isFrontface());
                boolean r23 = StoryRecorder.m20551$$Nest$museDisplayFlashlight(this.this$1.this$0);
                WindowView r1 = this.this$1;
                if (r23 == false) goto L18;
                StoryRecorder.m20420$$Nest$fgetflashViews(r1.this$0).flashIn(null);
                return true;
            L18:
                StoryRecorder.m20420$$Nest$fgetflashViews(r1.this$0).flashOut();
                return true;
            L28:
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTapEvent(MotionEvent r1) {
                if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0) == null) goto L7;
                StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0).clearTapFocus();
                return false;
            L7:
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent r2) {
                WindowView.m20568$$Nest$fputsty(this.this$1, 0.0f);
                WindowView.m20567$$Nest$fputstx(this.this$1, 0.0f);
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent r4, MotionEvent r5, float r6, float r7) {
                if (StoryRecorder.m20444$$Nest$fgetopenCloseAnimator(this.this$1.this$0) == null) goto L7;
                if (StoryRecorder.m20444$$Nest$fgetopenCloseAnimator(this.this$1.this$0).isRunning() == false) goto L7;
            L72:
                return false;
            L7:
                if (StoryRecorder.m20462$$Nest$fgetqrLinkView(this.this$1.this$0) == null) goto L11;
                if (StoryRecorder.m20462$$Nest$fgetqrLinkView(this.this$1.this$0).inTouch() == true) goto L72;
            L11:
                if (StoryRecorder.m20464$$Nest$fgetrecordControl(this.this$1.this$0).isTouch() == true) goto L72;
                if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0) == null) goto L17;
                if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0).isDualTouch() == true) goto L72;
            L17:
                if (WindowView.m20560$$Nest$fgetscaling(this.this$1) == true) goto L72;
                if (StoryRecorder.m20487$$Nest$fgetzoomControlView(this.this$1.this$0) == null) goto L23;
                if (StoryRecorder.m20487$$Nest$fgetzoomControlView(this.this$1.this$0).isTouch() == true) goto L72;
            L23:
                if (StoryRecorder.m20536$$Nest$minCheck(this.this$1.this$0) == true) goto L72;
                boolean r0 = true;
                WindowView.m20565$$Nest$fputflingDetected(this.this$1, true);
                WindowView.m20564$$Nest$fputallowModeScroll(this.this$1, true);
                if (StoryRecorder.m20470$$Nest$fgetscrollingY(this.this$1.this$0) == true) goto L28;
            L65:
                r0 = false;
            L66:
                StoryRecorder.m20498$$Nest$fputgalleryClosing(this.this$1.this$0, false);
                StoryRecorder.m20513$$Nest$fputscrollingY(this.this$1.this$0, false);
                StoryRecorder.m20512$$Nest$fputscrollingX(this.this$1.this$0, false);
                if (r0 == true) goto L69;
            L71:
                return r0;
            L69:
                if (StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$1.this$0) == null) goto L71;
                StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$1.this$0).cancelTouch();
                goto L71
            L28:
                if (Math.abs(StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$1.this$0).getTranslationY1()) < AndroidUtilities.dp(1.0f)) goto L40;
                if (r7 <= 0.0f) goto L36;
                if (Math.abs(r7) <= 2000.0f) goto L36;
                if (Math.abs(r7) <= Math.abs(r6)) goto L36;
            L37:
                this.this$1.this$0.close(true);
            L36:
                if (StoryRecorder.m20417$$Nest$fgetdismissProgress(this.this$1.this$0) > 0.4f) goto L37;
                StoryRecorder.m20520$$Nest$manimateContainerBack(this.this$1.this$0);
                goto L66
            L40:
                if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$1.this$0) == null) goto L65;
                if (StoryRecorder.m20428$$Nest$fgetgalleryClosing(this.this$1.this$0) == true) goto L65;
                if (StoryRecorder.m20439$$Nest$fgetmode(this.this$1.this$0) == (-1)) goto L65;
                if (Math.abs(r7) > 200.0f) goto L48;
            L58:
                StoryRecorder r42 = this.this$1.this$0;
                if (StoryRecorder.m20473$$Nest$fgettakingVideo(r42) == false) goto L61;
            L63:
                boolean r62 = false;
            L64:
                StoryRecorder.m20521$$Nest$manimateGalleryListView(r42, r62);
                goto L66
            L61:
                if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$1.this$0).getTranslationY() >= StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$1.this$0).getPadding()) goto L63;
                r62 = true;
                goto L64
            L48:
                if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$1.this$0).listView.canScrollVertically(-1) == true) goto L50;
            L51:
                StoryRecorder r43 = this.this$1.this$0;
                if (StoryRecorder.m20473$$Nest$fgettakingVideo(r43) == false) goto L54;
            L56:
                boolean r63 = false;
            L57:
                StoryRecorder.m20521$$Nest$manimateGalleryListView(r43, r63);
                goto L66
            L54:
                if (r7 >= 0.0f) goto L56;
                r63 = true;
                goto L57
            L50:
                if (StoryRecorder.m20484$$Nest$fgetwasGalleryOpen(this.this$1.this$0) == true) goto L58;
                goto L51
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent r1) {
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent r4, MotionEvent r5, float r6, float r7) {
                boolean r52 = false;
                if (StoryRecorder.m20444$$Nest$fgetopenCloseAnimator(this.this$1.this$0) == null) goto L7;
                if (StoryRecorder.m20444$$Nest$fgetopenCloseAnimator(this.this$1.this$0).isRunning() == false) goto L7;
            L82:
                return r52;
            L7:
                if (StoryRecorder.m20433$$Nest$fgetgalleryOpenCloseSpringAnimator(this.this$1.this$0) != null) goto L82;
                if (StoryRecorder.m20432$$Nest$fgetgalleryOpenCloseAnimator(this.this$1.this$0) != null) goto L82;
                if (StoryRecorder.m20462$$Nest$fgetqrLinkView(this.this$1.this$0) == null) goto L15;
                if (StoryRecorder.m20462$$Nest$fgetqrLinkView(this.this$1.this$0).inTouch() == true) goto L82;
            L15:
                if (StoryRecorder.m20464$$Nest$fgetrecordControl(this.this$1.this$0).isTouch() == true) goto L82;
                if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0) == null) goto L21;
                if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0).isDualTouch() == true) goto L82;
            L21:
                if (WindowView.m20560$$Nest$fgetscaling(this.this$1) == true) goto L82;
                if (StoryRecorder.m20487$$Nest$fgetzoomControlView(this.this$1.this$0) == null) goto L27;
                if (StoryRecorder.m20487$$Nest$fgetzoomControlView(this.this$1.this$0).isTouch() == true) goto L82;
            L27:
                if (StoryRecorder.m20536$$Nest$minCheck(this.this$1.this$0) == true) goto L82;
                if (StoryRecorder.m20473$$Nest$fgettakingVideo(this.this$1.this$0) == true) goto L82;
                if (StoryRecorder.m20472$$Nest$fgettakingPhoto(this.this$1.this$0) == true) goto L82;
                if (StoryRecorder.m20415$$Nest$fgetcurrentPage(this.this$1.this$0) != 0) goto L82;
                r52 = true;
                if (StoryRecorder.m20469$$Nest$fgetscrollingX(this.this$1.this$0) == true) goto L47;
                WindowView r42 = this.this$1;
                WindowView.m20568$$Nest$fputsty(r42, WindowView.m20562$$Nest$fgetsty(r42) + r7);
                if (StoryRecorder.m20470$$Nest$fgetscrollingY(this.this$1.this$0) == true) goto L47;
                if (Math.abs(WindowView.m20562$$Nest$fgetsty(this.this$1)) < AndroidUtilities.touchSlop) goto L47;
                if (StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$1.this$0) == null) goto L45;
                StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$1.this$0).cancelTouch();
            L45:
                StoryRecorder.m20513$$Nest$fputscrollingY(this.this$1.this$0, true);
            L47:
                if (StoryRecorder.m20470$$Nest$fgetscrollingY(this.this$1.this$0) == false) goto L73;
                int r43 = (StoryRecorder.m20486$$Nest$fgetwindowView(this.this$1.this$0).getMeasuredHeight() - ((int) (AndroidUtilities.displaySize.y * 0.35f))) - (AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight());
                if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$1.this$0) == null) goto L54;
                float r1 = r43;
                if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$1.this$0).getTranslationY() >= r1) goto L54;
                WindowView r0 = this.this$1;
                WindowView.m20569$$Nest$fputty(r0, StoryRecorder.m20430$$Nest$fgetgalleryListView(r0.this$0).getTranslationY() - r1);
            L56:
                if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$1.this$0) != null) goto L58;
            L60:
                WindowView r02 = this.this$1;
                WindowView.m20569$$Nest$fputty(r02, WindowView.m20563$$Nest$fgetty(r02) - r7);
                WindowView r72 = this.this$1;
                WindowView.m20569$$Nest$fputty(r72, Math.max(-r43, WindowView.m20563$$Nest$fgetty(r72)));
                if (StoryRecorder.m20415$$Nest$fgetcurrentPage(this.this$1.this$0) != 1) goto L63;
                WindowView r73 = this.this$1;
                WindowView.m20569$$Nest$fputty(r73, Math.max(0.0f, WindowView.m20563$$Nest$fgetty(r73)));
            L63:
                float r74 = WindowView.m20563$$Nest$fgetty(this.this$1);
                WindowView r03 = this.this$1;
                if (r74 < 0.0f) goto L68;
                StoryRecorder.m20409$$Nest$fgetcontainerView(r03.this$0).setTranslationY(WindowView.m20563$$Nest$fgetty(this.this$1));
                if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$1.this$0) == null) goto L73;
                StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$1.this$0).setTranslationY(r43);
                goto L73
            L68:
                StoryRecorder.m20409$$Nest$fgetcontainerView(r03.this$0).setTranslationY(0.0f);
                if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$1.this$0) != null) goto L71;
                StoryRecorder.m20528$$Nest$mcreateGalleryListView(this.this$1.this$0);
            L71:
                StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$1.this$0).setTranslationY(r43 + WindowView.m20563$$Nest$fgetty(this.this$1));
                goto L73
            L58:
                if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$1.this$0).listView.canScrollVertically(-1) == false) goto L60;
                r7 = Math.max(0.0f, r7);
            L54:
                WindowView r04 = this.this$1;
                WindowView.m20569$$Nest$fputty(r04, StoryRecorder.m20409$$Nest$fgetcontainerView(r04.this$0).getTranslationY1());
            L73:
                if (StoryRecorder.m20470$$Nest$fgetscrollingY(this.this$1.this$0) == true) goto L82;
                WindowView r44 = this.this$1;
                WindowView.m20567$$Nest$fputstx(r44, WindowView.m20561$$Nest$fgetstx(r44) + r6);
                if (StoryRecorder.m20469$$Nest$fgetscrollingX(this.this$1.this$0) == true) goto L82;
                if (Math.abs(WindowView.m20561$$Nest$fgetstx(this.this$1)) < AndroidUtilities.touchSlop) goto L82;
                if (StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$1.this$0) == null) goto L81;
                StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$1.this$0).cancelTouch();
            L81:
                StoryRecorder.m20512$$Nest$fputscrollingX(this.this$1.this$0, true);
                goto L82
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onShowPress(MotionEvent r1) {
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent r1) {
                if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0) == null) goto L6;
                StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0).allowToTapFocus();
                return true;
            L6:
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent r4) {
                StoryRecorder.m20513$$Nest$fputscrollingY(this.this$1.this$0, false);
                StoryRecorder.m20512$$Nest$fputscrollingX(this.this$1.this$0, false);
                if (hasDoubleTap(r4) == true) goto L8;
                if (onSingleTapConfirmed(r4) == false) goto L8;
                return true;
            L8:
                if (StoryRecorder.m20537$$Nest$misGalleryOpen(this.this$1.this$0) == true) goto L10;
            L13:
                return false;
            L10:
                if (r4.getY() >= StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$1.this$0).top()) goto L13;
                StoryRecorder.m20521$$Nest$manimateGalleryListView(this.this$1.this$0, false);
                return true;
            }
        }

        public final class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
            final /* synthetic */ WindowView this$1;

            private ScaleListener(WindowView r1) {
                this.this$1 = r1;
            }

            public /* synthetic */ ScaleListener(WindowView r1, StoryRecorderIA r2) {
                this(r1);
            }

            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector r5) {
                if (WindowView.m20560$$Nest$fgetscaling(this.this$1) == true) goto L5;
            L18:
                return false;
            L5:
                if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0) == null) goto L18;
                if (StoryRecorder.m20415$$Nest$fgetcurrentPage(this.this$1.this$0) != 0) goto L18;
                if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0).isDualTouch() == true) goto L18;
                if (StoryRecorder.m20407$$Nest$fgetcollageLayoutView(this.this$1.this$0).getFilledProgress() >= 1.0f) goto L18;
                float r52 = (r5.getScaleFactor() - 1.0f) * 0.75f;
                StoryRecorder r0 = this.this$1.this$0;
                StoryRecorder.m20489$$Nest$fputcameraZoom(r0, StoryRecorder.m20399$$Nest$fgetcameraZoom(r0) + r52);
                StoryRecorder r53 = this.this$1.this$0;
                StoryRecorder.m20489$$Nest$fputcameraZoom(r53, Utilities.clamp(StoryRecorder.m20399$$Nest$fgetcameraZoom(r53), 1.0f, 0.0f));
                StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0).setZoom(StoryRecorder.m20399$$Nest$fgetcameraZoom(this.this$1.this$0));
                if (StoryRecorder.m20487$$Nest$fgetzoomControlView(this.this$1.this$0) == null) goto L16;
                StoryRecorder.m20487$$Nest$fgetzoomControlView(this.this$1.this$0).setZoom(StoryRecorder.m20399$$Nest$fgetcameraZoom(this.this$1.this$0), false);
            L16:
                StoryRecorder.m20547$$Nest$mshowZoomControls(this.this$1.this$0, true, true);
                return true;
            }

            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector r3) {
                if (StoryRecorder.m20398$$Nest$fgetcameraView(this.this$1.this$0) != null) goto L5;
                return false;
            L5:
                if (StoryRecorder.m20415$$Nest$fgetcurrentPage(this.this$1.this$0) == 0) goto L7;
                return false;
            L7:
                if (StoryRecorder.m20484$$Nest$fgetwasGalleryOpen(this.this$1.this$0) == true) goto L14;
                WindowView.m20566$$Nest$fputscaling(this.this$1, true);
                return super.onScaleBegin(r3);
            L14:
                return false;
            }

            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector r3) {
                WindowView.m20566$$Nest$fputscaling(this.this$1, false);
                StoryRecorder.m20521$$Nest$manimateGalleryListView(this.this$1.this$0, false);
                StoryRecorder.m20520$$Nest$manimateContainerBack(this.this$1.this$0);
                super.onScaleEnd(r3);
            }
        }

        /* renamed from: -$$Nest$fgetscaling, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m20560$$Nest$fgetscaling(WindowView r0) {
            return r0.scaling;
        }

        /* renamed from: -$$Nest$fgetstx, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m20561$$Nest$fgetstx(WindowView r0) {
            return r0.stx;
        }

        /* renamed from: -$$Nest$fgetsty, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m20562$$Nest$fgetsty(WindowView r0) {
            return r0.sty;
        }

        /* renamed from: -$$Nest$fgetty, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m20563$$Nest$fgetty(WindowView r0) {
            return r0.ty;
        }

        /* renamed from: -$$Nest$fputallowModeScroll, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20564$$Nest$fputallowModeScroll(WindowView r0, boolean r1) {
            r0.allowModeScroll = r1;
        }

        /* renamed from: -$$Nest$fputflingDetected, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20565$$Nest$fputflingDetected(WindowView r0, boolean r1) {
            r0.flingDetected = r1;
        }

        /* renamed from: -$$Nest$fputscaling, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20566$$Nest$fputscaling(WindowView r0, boolean r1) {
            r0.scaling = r1;
        }

        /* renamed from: -$$Nest$fputstx, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20567$$Nest$fputstx(WindowView r0, float r1) {
            r0.stx = r1;
        }

        /* renamed from: -$$Nest$fputsty, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20568$$Nest$fputsty(WindowView r0, float r1) {
            r0.sty = r1;
        }

        /* renamed from: -$$Nest$fputty, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20569$$Nest$fputty(WindowView r0, float r1) {
            r0.ty = r1;
        }

        public WindowView(StoryRecorder r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.scaling = false;
            this.allowModeScroll = true;
            this.gestureDetector = new GestureDetectorFixDoubleTap(r4, new GestureListener(this, null));
            this.scaleGestureDetector = new ScaleGestureDetector(r4, new ScaleListener(this, null));
        }

        private void measureChildExactly(View r1, int r2, int r3) {
            r1.measure(View.MeasureSpec.makeMeasureSpec(r2, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r3, TLObject.FLAG_30));
        }

        public void cancelGestures() {
            this.scaleGestureDetector.onTouchEvent(AndroidUtilities.emptyMotionEvent());
            this.gestureDetector.onTouchEvent(AndroidUtilities.emptyMotionEvent());
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r15) {
            Float r1 = StoryRecorder.m20426$$Nest$fgetfrozenDismissProgress(this.this$0);
            StoryRecorder r2 = this.this$0;
            if (r1 == null) goto L5;
            float r12 = StoryRecorder.m20426$$Nest$fgetfrozenDismissProgress(r2).floatValue();
        L7:
            if (StoryRecorder.m20446$$Nest$fgetopenType(this.this$0) != 0) goto L9;
            r15.drawColor(ColorUtils.setAlphaComponent(-16777216, (int) ((StoryRecorder.m20445$$Nest$fgetopenProgress(this.this$0) * 255.0f) * (1.0f - r12))));
        L9:
            float r10 = AndroidUtilities.lerp(StoryRecorder.m20422$$Nest$fgetfromRounding(this.this$0), 0.0f, StoryRecorder.m20445$$Nest$fgetopenProgress(this.this$0));
            if (StoryRecorder.m20445$$Nest$fgetopenProgress(this.this$0) == 1.0f) goto L17;
            int r13 = StoryRecorder.m20446$$Nest$fgetopenType(this.this$0);
            StoryRecorder r22 = this.this$0;
            boolean r132 = true;
            if (r13 != 0) goto L15;
            StoryRecorder.m20427$$Nest$fgetfullRectF(r22).set(0.0f, 0.0f, getWidth(), getHeight());
            StoryRecorder.m20427$$Nest$fgetfullRectF(this.this$0).offset(StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getTranslationX(), StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getTranslationY());
            AndroidUtilities.lerp(StoryRecorder.m20421$$Nest$fgetfromRect(this.this$0), StoryRecorder.m20427$$Nest$fgetfullRectF(this.this$0), StoryRecorder.m20445$$Nest$fgetopenProgress(this.this$0), StoryRecorder.m20466$$Nest$fgetrectF(this.this$0));
            r15.save();
            StoryRecorder.m20405$$Nest$fgetclipPath(this.this$0).rewind();
            StoryRecorder.m20405$$Nest$fgetclipPath(this.this$0).addRoundRect(StoryRecorder.m20466$$Nest$fgetrectF(this.this$0), r10, r10, Path.Direction.CW);
            r15.clipPath(StoryRecorder.m20405$$Nest$fgetclipPath(this.this$0));
            r15.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (Utilities.clamp(StoryRecorder.m20445$$Nest$fgetopenProgress(this.this$0) * 3.0f, 1.0f, 0.0f) * 255.0f), 31);
            r15.translate(StoryRecorder.m20466$$Nest$fgetrectF(this.this$0).left, StoryRecorder.m20466$$Nest$fgetrectF(this.this$0).top - (StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getTranslationY() * StoryRecorder.m20445$$Nest$fgetopenProgress(this.this$0)));
            float r14 = Math.max(StoryRecorder.m20466$$Nest$fgetrectF(this.this$0).width() / getWidth(), StoryRecorder.m20466$$Nest$fgetrectF(this.this$0).height() / getHeight());
            r15.scale(r14, r14);
        L19:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0) == null) goto L21;
            StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).onParentPreDraw();
        L21:
            super.dispatchDraw(r15);
            if (r132 == false) goto L42;
            r15.restore();
            r15.restore();
            if (StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0) == null) goto L43;
            float r16 = Utilities.clamp(1.0f - (StoryRecorder.m20445$$Nest$fgetopenProgress(this.this$0) * 1.5f), 1.0f, 0.0f);
            float r23 = StoryRecorder.m20466$$Nest$fgetrectF(this.this$0).centerX();
            float r3 = StoryRecorder.m20466$$Nest$fgetrectF(this.this$0).centerY();
            Math.min(StoryRecorder.m20466$$Nest$fgetrectF(this.this$0).width(), StoryRecorder.m20466$$Nest$fgetrectF(this.this$0).height());
            ImageReceiver r4 = StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).backgroundImageReceiver;
            StoryRecorder r5 = this.this$0;
            if (r4 == null) goto L28;
            StoryRecorder.m20423$$Nest$fgetfromSourceView(r5).backgroundImageReceiver.setImageCoords(StoryRecorder.m20466$$Nest$fgetrectF(this.this$0));
            int r42 = StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).backgroundImageReceiver.getRoundRadius()[0];
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).backgroundImageReceiver.setRoundRadius((int) r10);
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).backgroundImageReceiver.setAlpha(r16);
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).backgroundImageReceiver.draw(r15);
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).backgroundImageReceiver.setRoundRadius(r42);
        L38:
            if (StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).iconDrawable == null) goto L40;
            StoryRecorder.m20465$$Nest$fgetrect(this.this$0).set(StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).iconDrawable.getBounds());
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).iconDrawable.setBounds((int) (r23 - (StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).iconSize / 2)), (int) (r3 - (StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).iconSize / 2)), (int) (r23 + (StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).iconSize / 2)), (int) (r3 + (StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).iconSize / 2)));
            int r24 = StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).iconDrawable.getAlpha();
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).iconDrawable.setAlpha((int) (r24 * r16));
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).iconDrawable.draw(r15);
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).iconDrawable.setBounds(StoryRecorder.m20465$$Nest$fgetrect(this.this$0));
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).iconDrawable.setAlpha(r24);
        L40:
            r15.save();
            r15.translate(StoryRecorder.m20421$$Nest$fgetfromRect(this.this$0).left, StoryRecorder.m20421$$Nest$fgetfromRect(this.this$0).top);
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).drawAbove(r15, r16);
            r15.restore();
            return;
        L28:
            Drawable r43 = StoryRecorder.m20423$$Nest$fgetfromSourceView(r5).backgroundDrawable;
            StoryRecorder r52 = this.this$0;
            if (r43 == null) goto L32;
            StoryRecorder.m20423$$Nest$fgetfromSourceView(r52).backgroundDrawable.setBounds((int) StoryRecorder.m20466$$Nest$fgetrectF(this.this$0).left, (int) StoryRecorder.m20466$$Nest$fgetrectF(this.this$0).top, (int) StoryRecorder.m20466$$Nest$fgetrectF(this.this$0).right, (int) StoryRecorder.m20466$$Nest$fgetrectF(this.this$0).bottom);
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).backgroundDrawable.setAlpha((int) (((255.0f * r16) * r16) * r16));
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).backgroundDrawable.draw(r15);
            goto L38
        L32:
            if (StoryRecorder.m20423$$Nest$fgetfromSourceView(r52).backgroundPaint == null) goto L38;
            if (StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).hasShadow == false) goto L36;
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).backgroundPaint.setShadowLayer(AndroidUtilities.dp(2.0f), 0.0f, AndroidUtilities.dp(3.0f), Theme.multAlpha(855638016, r16));
        L36:
            StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).backgroundPaint.setAlpha((int) (255.0f * r16));
            r15.drawRoundRect(StoryRecorder.m20466$$Nest$fgetrectF(this.this$0), r10, r10, StoryRecorder.m20423$$Nest$fgetfromSourceView(this.this$0).backgroundPaint);
            goto L38
        L43:
            return;
        L42:
            return;
        L15:
            if (StoryRecorder.m20446$$Nest$fgetopenType(r22) != 1) goto L17;
            StoryRecorder.m20524$$Nest$mapplyOpenProgress(this.this$0);
        L17:
            r132 = false;
            goto L19
        L5:
            r12 = StoryRecorder.m20417$$Nest$fgetdismissProgress(r2);
            goto L7
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEventPreIme(KeyEvent r3) {
            if (r3 == null) goto L10;
            if (r3.getKeyCode() != 4) goto L10;
            if (r3.getAction() != 1) goto L10;
            this.this$0.onBackPressed();
            return true;
        L10:
            return super.dispatchKeyEventPreIme(r3);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r7) {
            boolean r0 = false;
            this.flingDetected = false;
            boolean r3 = true;
            if (StoryRecorder.m20408$$Nest$fgetcollageListView(this.this$0) == null) goto L21;
            if (StoryRecorder.m20408$$Nest$fgetcollageListView(this.this$0).isVisible() == false) goto L21;
            float r1 = (StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getY() + StoryRecorder.m20390$$Nest$fgetactionBarContainer(this.this$0).getY()) + StoryRecorder.m20408$$Nest$fgetcollageListView(this.this$0).getY();
            if (r7.getY() < r1) goto L11;
            if (r7.getY() > (r1 + StoryRecorder.m20408$$Nest$fgetcollageListView(this.this$0).getHeight())) goto L11;
        L13:
            if (r7.getAction() != 1) goto L15;
        L17:
            this.touchInCollageList = r0;
            return super.dispatchTouchEvent(r7);
        L15:
            if (r7.getAction() == 3) goto L17;
            r0 = true;
        L11:
            if (this.touchInCollageList == true) goto L13;
            StoryRecorder.m20408$$Nest$fgetcollageListView(this.this$0).setVisible(false, true);
            StoryRecorder.m20549$$Nest$mupdateActionBarButtons(this.this$0, true);
        L21:
            if (this.touchInCollageList == true) goto L23;
        L27:
            this.scaleGestureDetector.onTouchEvent(r7);
            this.gestureDetector.onTouchEvent(r7);
            if (r7.getAction() != 1) goto L52;
            if (this.flingDetected == true) goto L52;
            this.allowModeScroll = true;
            float r12 = StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getTranslationY();
            StoryRecorder r4 = this.this$0;
            if (r12 <= 0.0f) goto L38;
            float r13 = StoryRecorder.m20417$$Nest$fgetdismissProgress(r4);
            StoryRecorder r2 = this.this$0;
            if (r13 <= 0.4f) goto L36;
            r2.close(true);
        L50:
            StoryRecorder.m20498$$Nest$fputgalleryClosing(this.this$0, false);
            StoryRecorder.m20513$$Nest$fputscrollingY(this.this$0, false);
            StoryRecorder.m20512$$Nest$fputscrollingX(this.this$0, false);
            goto L52
        L36:
            StoryRecorder.m20520$$Nest$manimateContainerBack(r2);
            goto L50
        L38:
            if (StoryRecorder.m20430$$Nest$fgetgalleryListView(r4) == null) goto L50;
            if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0).getTranslationY() <= 0.0f) goto L50;
            if (StoryRecorder.m20428$$Nest$fgetgalleryClosing(this.this$0) == true) goto L50;
            StoryRecorder r14 = this.this$0;
            if (StoryRecorder.m20473$$Nest$fgettakingVideo(r14) == false) goto L46;
        L48:
            r3 = false;
        L49:
            StoryRecorder.m20521$$Nest$manimateGalleryListView(r14, r3);
            goto L50
        L46:
            if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0).getTranslationY() >= StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0).getPadding()) goto L48;
        L52:
            return super.dispatchTouchEvent(r7);
        L23:
            if (r7.getAction() != 1) goto L25;
        L26:
            this.touchInCollageList = false;
            goto L27
        L25:
            if (r7.getAction() != 3) goto L27;
            goto L26
        }

        public void drawBlurBitmap(Bitmap r6, float r7) {
            Canvas r0 = new Canvas(r6);
            r0.drawColor(-16777216);
            float r62 = r6.getWidth() / StoryRecorder.m20486$$Nest$fgetwindowView(this.this$0).getWidth();
            r0.scale(r62, r62);
            TextureView r1 = StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).getTextureView();
            if (r1 != null) goto L5;
            r1 = StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0).filterTextureView;
        L5:
            if (r1 == null) goto L9;
            r0.save();
            r0.translate(StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getX() + StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0).getX(), StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getY() + StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0).getY());
            Bitmap r72 = r1.getBitmap((int) (r1.getWidth() / r7), (int) (r1.getHeight() / r7));     // Catch: Exception -> L34
            float r12 = 1.0f / r62;     // Catch: Exception -> L34
            r0.scale(r12, r12);     // Catch: Exception -> L34
            r0.drawBitmap(r72, 0.0f, 0.0f, new Paint(2));     // Catch: Exception -> L34
            r72.recycle();     // Catch: Exception -> L34
        L8:
            r0.restore();
        L9:
            r0.save();
            r0.translate(StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getX(), StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getY());
            int r73 = 0;
        L11:
            if (r73 >= StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getChildCount()) goto L32;
            View r13 = StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getChildAt(r73);
            r0.save();
            r0.translate(r13.getX(), r13.getY());
            if (r13.getVisibility() != 0) goto L31;
            if (r13 != StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0)) goto L29;
            int r14 = 0;
        L19:
            if (r14 >= StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0).getChildCount()) goto L30;
            View r2 = StoryRecorder.m20456$$Nest$fgetpreviewContainer(this.this$0).getChildAt(r14);
            if (r2 == StoryRecorder.m20460$$Nest$fgetpreviewView(this.this$0)) goto L28;
            if (r2 == StoryRecorder.m20398$$Nest$fgetcameraView(this.this$0)) goto L28;
            if (r2.getVisibility() != 0) goto L28;
            r0.save();
            r0.translate(r2.getX(), r2.getY());
            r2.draw(r0);
            r0.restore();
        L28:
            r14 = r14 + 1;
        L30:
            r0.restore();
            goto L31
        L29:
            r13.draw(r0);
        L31:
            r73 = r73 + 1;
            goto L11
        L32:
            r0.restore();
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public int getBottomPadding() {
            return (getHeight() - StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getBottom()) + StoryRecorder.m20479$$Nest$fgetunderControls(this.this$0);
        }

        public int getBottomPadding2() {
            return getHeight() - StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getBottom();
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        public int getPaddingUnderContainer() {
            return (getHeight() - StoryRecorder.m20435$$Nest$fgetinsetBottom(this.this$0)) - StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).getBottom();
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
            if (this.ignoreLayout == true) goto L65;
            int r62 = getMeasuredWidth();
            int r72 = getMeasuredHeight();
            int r82 = StoryRecorder.m20438$$Nest$fgetinsetTop(this.this$0);
            int r92 = StoryRecorder.m20441$$Nest$fgetnavbarContainer(this.this$0).getMeasuredHeight();
            if (StoryRecorder.m20480$$Nest$fgetunderStatusBar(this.this$0) == false) goto L8;
            r82 = 0;
        L8:
            int r102 = MathUtils.clamp((r62 - StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0)) / 2, StoryRecorder.m20436$$Nest$fgetinsetLeft(this.this$0), (r62 - StoryRecorder.m20437$$Nest$fgetinsetRight(this.this$0)) - StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0));
            int r1 = StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0) + r102;
            boolean r2 = StoryRecorder.m20480$$Nest$fgetunderStatusBar(this.this$0);
            StoryRecorder r3 = this.this$0;
            if (r2 == false) goto L12;
            int r22 = StoryRecorder.m20457$$Nest$fgetpreviewH(r3);
        L22:
            StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).layout(r102, r82, r1, (r22 + r82) + r92);
            StoryRecorder.m20420$$Nest$fgetflashViews(this.this$0).backgroundView.layout(0, 0, r62, r72);
            if (StoryRecorder.m20474$$Nest$fgetthanosEffect(this.this$0) == null) goto L26;
            StoryRecorder.m20474$$Nest$fgetthanosEffect(this.this$0).layout(0, 0, r62, r72);
        L26:
            if (StoryRecorder.m20403$$Nest$fgetchangeDayNightView(this.this$0) == null) goto L29;
            StoryRecorder.m20403$$Nest$fgetchangeDayNightView(this.this$0).layout(0, 0, r62, r72);
        L29:
            if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0) == null) goto L31;
            StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0).layout((r62 - StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0).getMeasuredWidth()) / 2, 0, (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0).getMeasuredWidth() + r62) / 2, r72);
        L31:
            StoryRecorder.m20476$$Nest$fgetthemeSheet(this.this$0);
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0) == null) goto L37;
            EmojiView r93 = StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).editText.getEmojiView();
            if (r93 == null) goto L37;
            r93.layout(StoryRecorder.m20436$$Nest$fgetinsetLeft(this.this$0), (r72 - StoryRecorder.m20435$$Nest$fgetinsetBottom(this.this$0)) - r93.getMeasuredHeight(), r62 - StoryRecorder.m20437$$Nest$fgetinsetRight(this.this$0), r72 - StoryRecorder.m20435$$Nest$fgetinsetBottom(this.this$0));
        L37:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0) == null) goto L50;
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).emojiView == null) goto L42;
            StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).emojiView.layout(StoryRecorder.m20436$$Nest$fgetinsetLeft(this.this$0), (r72 - StoryRecorder.m20435$$Nest$fgetinsetBottom(this.this$0)) - StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).emojiView.getMeasuredHeight(), r62 - StoryRecorder.m20437$$Nest$fgetinsetRight(this.this$0), r72 - StoryRecorder.m20435$$Nest$fgetinsetBottom(this.this$0));
        L42:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).reactionLayout == null) goto L50;
            StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).reactionLayout.layout(StoryRecorder.m20436$$Nest$fgetinsetLeft(this.this$0), StoryRecorder.m20438$$Nest$fgetinsetTop(this.this$0), StoryRecorder.m20436$$Nest$fgetinsetLeft(this.this$0) + StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).reactionLayout.getMeasuredWidth(), StoryRecorder.m20438$$Nest$fgetinsetTop(this.this$0) + StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).reactionLayout.getMeasuredHeight());
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).reactionLayout.getReactionsWindow() == null) goto L46;
            FrameLayout r94 = StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).reactionLayout.getReactionsWindow().windowView;
        L47:
            if (r94 == null) goto L50;
            r94.layout(StoryRecorder.m20436$$Nest$fgetinsetLeft(this.this$0), StoryRecorder.m20438$$Nest$fgetinsetTop(this.this$0), StoryRecorder.m20436$$Nest$fgetinsetLeft(this.this$0) + r94.getMeasuredWidth(), StoryRecorder.m20438$$Nest$fgetinsetTop(this.this$0) + r94.getMeasuredHeight());
            goto L50
        L46:
            r94 = null;
        L50:
            if (StoryRecorder.m20411$$Nest$fgetcropEditor(this.this$0) == null) goto L53;
            StoryRecorder.m20411$$Nest$fgetcropEditor(this.this$0).controlsLayout.setPadding(0, StoryRecorder.m20438$$Nest$fgetinsetTop(this.this$0), 0, StoryRecorder.m20435$$Nest$fgetinsetBottom(this.this$0));
            StoryRecorder.m20411$$Nest$fgetcropEditor(this.this$0).layout(0, 0, r62, r72);
            StoryRecorder.m20411$$Nest$fgetcropEditor(this.this$0).contentView.layout(0, 0, r62, r72);
        L53:
            if (StoryRecorder.m20412$$Nest$fgetcropInlineEditor(this.this$0) == null) goto L55;
            StoryRecorder.m20412$$Nest$fgetcropInlineEditor(this.this$0).controlsLayout.setPadding(0, StoryRecorder.m20438$$Nest$fgetinsetTop(this.this$0), 0, StoryRecorder.m20435$$Nest$fgetinsetBottom(this.this$0));
            StoryRecorder.m20412$$Nest$fgetcropInlineEditor(this.this$0).layout(0, 0, r62, r72);
            StoryRecorder.m20412$$Nest$fgetcropInlineEditor(this.this$0).contentView.layout(0, 0, r62, r72);
        L55:
            int r95 = 0;
        L57:
            if (r95 >= getChildCount()) goto L70;
            View r103 = getChildAt(r95);
            if ((r103 instanceof DownloadButton.PreparingVideoToast) == false) goto L62;
            r103.layout(0, 0, r62, r72);
        L64:
            r95 = r95 + 1;
            goto L57
        L62:
            if ((r103 instanceof Bulletin.ParentLayout) == false) goto L64;
            r103.layout(0, r82, r103.getMeasuredWidth(), r103.getMeasuredHeight() + r82);
            goto L64
        L70:
            return;
        L12:
            int r23 = (((((r72 - r82) - StoryRecorder.m20435$$Nest$fgetinsetBottom(r3)) - StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0)) - r92) / 2) + r82;
            if (StoryRecorder.m20446$$Nest$fgetopenType(this.this$0) != 1) goto L18;
            if (((StoryRecorder.m20421$$Nest$fgetfromRect(this.this$0).top + StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0)) + r92) >= (r72 - StoryRecorder.m20435$$Nest$fgetinsetBottom(this.this$0))) goto L18;
            r82 = (int) StoryRecorder.m20421$$Nest$fgetfromRect(this.this$0).top;
        L21:
            r22 = StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0);
        L18:
            if ((r23 - r82) < AndroidUtilities.dp(40.0f)) goto L21;
            r82 = r23;
            goto L21
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r14, int r15) {
            int r0 = View.MeasureSpec.getSize(r14);
            int r1 = View.MeasureSpec.getSize(r15);
            int r2 = (r0 - StoryRecorder.m20436$$Nest$fgetinsetLeft(this.this$0)) - StoryRecorder.m20437$$Nest$fgetinsetRight(this.this$0);
            int r3 = StoryRecorder.m20438$$Nest$fgetinsetTop(this.this$0);
            int r4 = StoryRecorder.m20435$$Nest$fgetinsetBottom(this.this$0);
            int r5 = (int) Math.ceil((r2 / 9.0f) * 16.0f);
            StoryRecorder.m20517$$Nest$fputunderControls(this.this$0, AndroidUtilities.dp(48.0f));
            int r8 = StoryRecorder.m20479$$Nest$fgetunderControls(this.this$0) + r5;
            int r10 = r1 - r4;
            StoryRecorder r11 = this.this$0;
            if (r8 > r10) goto L9;
            StoryRecorder.m20511$$Nest$fputpreviewW(r11, r2);
            StoryRecorder.m20510$$Nest$fputpreviewH(this.this$0, r5);
            StoryRecorder r42 = this.this$0;
            if ((StoryRecorder.m20457$$Nest$fgetpreviewH(r42) + StoryRecorder.m20479$$Nest$fgetunderControls(this.this$0)) <= (r10 - r3)) goto L7;
            boolean r52 = true;
        L8:
            StoryRecorder.m20518$$Nest$fputunderStatusBar(r42, r52);
        L10:
            StoryRecorder r43 = this.this$0;
            int r53 = r1 - StoryRecorder.m20457$$Nest$fgetpreviewH(r43);
            if (StoryRecorder.m20480$$Nest$fgetunderStatusBar(this.this$0) == false) goto L13;
            int r6 = 0;
        L14:
            StoryRecorder.m20517$$Nest$fputunderControls(r43, Utilities.clamp(r53 - r6, AndroidUtilities.dp(68.0f), AndroidUtilities.dp(48.0f)));
            int r44 = getSystemUiVisibility();
            if (StoryRecorder.m20480$$Nest$fgetunderStatusBar(this.this$0) == false) goto L17;
            int r45 = r44 | 4;
        L18:
            setSystemUiVisibility(r45);
            StoryRecorder.m20409$$Nest$fgetcontainerView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(StoryRecorder.m20457$$Nest$fgetpreviewH(this.this$0) + StoryRecorder.m20479$$Nest$fgetunderControls(this.this$0), TLObject.FLAG_30));
            StoryRecorder.m20420$$Nest$fgetflashViews(this.this$0).backgroundView.measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30));
            if (StoryRecorder.m20474$$Nest$fgetthanosEffect(this.this$0) == null) goto L22;
            StoryRecorder.m20474$$Nest$fgetthanosEffect(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30));
        L22:
            if (StoryRecorder.m20403$$Nest$fgetchangeDayNightView(this.this$0) == null) goto L24;
            StoryRecorder.m20403$$Nest$fgetchangeDayNightView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30));
        L24:
            StoryRecorder.m20476$$Nest$fgetthemeSheet(this.this$0);
            if (StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0) == null) goto L28;
            StoryRecorder.m20430$$Nest$fgetgalleryListView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(StoryRecorder.m20461$$Nest$fgetpreviewW(this.this$0), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30));
        L28:
            if (StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0) == null) goto L35;
            EmojiView r46 = StoryRecorder.m20401$$Nest$fgetcaptionEdit(this.this$0).editText.getEmojiView();
            if (measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L32;
            this.ignoreLayout = false;
        L32:
            if (r46 == null) goto L35;
            r46.measure(View.MeasureSpec.makeMeasureSpec(r2, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r46.getLayoutParams().height, TLObject.FLAG_30));
        L35:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0) != null) goto L37;
        L44:
            int r142 = 0;
        L46:
            if (r142 >= getChildCount()) goto L59;
            View r152 = getChildAt(r142);
            if ((r152 instanceof DownloadButton.PreparingVideoToast) == false) goto L51;
            r152.measure(View.MeasureSpec.makeMeasureSpec(r2, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30));
        L57:
            r142 = r142 + 1;
            goto L46
        L51:
            if ((r152 instanceof Bulletin.ParentLayout) == false) goto L57;
            int r47 = View.MeasureSpec.makeMeasureSpec(r2, TLObject.FLAG_30);
            int r54 = AndroidUtilities.dp(340.0f);
            if (StoryRecorder.m20480$$Nest$fgetunderStatusBar(this.this$0) == false) goto L55;
            int r7 = 0;
        L56:
            r152.measure(r47, View.MeasureSpec.makeMeasureSpec(Math.min(r54, r1 - r7), TLObject.FLAG_30));
            goto L57
        L55:
            r7 = r3;
            goto L56
        L59:
            if (StoryRecorder.m20411$$Nest$fgetcropEditor(this.this$0) == null) goto L62;
            measureChildExactly(StoryRecorder.m20411$$Nest$fgetcropEditor(this.this$0), r0, r1);
            measureChildExactly(StoryRecorder.m20411$$Nest$fgetcropEditor(this.this$0).contentView, r0, r1);
        L62:
            if (StoryRecorder.m20412$$Nest$fgetcropInlineEditor(this.this$0) == null) goto L64;
            measureChildExactly(StoryRecorder.m20412$$Nest$fgetcropInlineEditor(this.this$0), r0, r1);
            measureChildExactly(StoryRecorder.m20412$$Nest$fgetcropInlineEditor(this.this$0).contentView, r0, r1);
        L64:
            setMeasuredDimension(r0, r1);
            return;
        L37:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).emojiView == null) goto L40;
            StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).emojiView.measure(View.MeasureSpec.makeMeasureSpec(r2, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).emojiView.getLayoutParams().height, TLObject.FLAG_30));
        L40:
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).reactionLayout == null) goto L44;
            measureChild(StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).reactionLayout, r14, r15);
            if (StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).reactionLayout.getReactionsWindow() == null) goto L44;
            measureChild(StoryRecorder.m20449$$Nest$fgetpaintView(this.this$0).reactionLayout.getReactionsWindow().windowView, r14, r15);
            goto L44
        L17:
            r45 = r44 & (-5);
            goto L18
        L13:
            r6 = r3;
            goto L14
        L7:
            r52 = false;
            goto L8
        L9:
            StoryRecorder.m20518$$Nest$fputunderStatusBar(r11, false);
            StoryRecorder r55 = this.this$0;
            StoryRecorder.m20510$$Nest$fputpreviewH(r55, ((r1 - StoryRecorder.m20479$$Nest$fgetunderControls(r55)) - r4) - r3);
            StoryRecorder.m20511$$Nest$fputpreviewW(this.this$0, (int) Math.ceil((StoryRecorder.m20457$$Nest$fgetpreviewH(r4) * 9.0f) / 16.0f));
            goto L10
        }
    }

    /* renamed from: $r8$lambda$-ytOIesGKltBFJ--xw4cUw9iPaI, reason: not valid java name */
    public static /* synthetic */ void m20344$r8$lambda$ytOIesGKltBFJxw4cUw9iPaI(StoryRecorder r0, FilterGLThread r1) {
        r0.lambda$onNavigateStart$80(r1);
    }

    /* renamed from: $r8$lambda$0Gqp1xfutr-IgoLPIM68QKcXADI, reason: not valid java name */
    public static /* synthetic */ void m20345$r8$lambda$0Gqp1xfutrIgoLPIM68QKcXADI(StoryEntry r2, boolean r3, ArrayList r4, File r5, File r6, File r7, File r8, File r9, List r10, Runnable r11) {
        File r0 = r2.paintFile;     // Catch: Exception -> L29
        if (r0 == null) goto L32;
        r0.delete();     // Catch: Exception -> L29
    L32:
        File r02 = r2.paintEntitiesFile;     // Catch: Exception -> L30
        if (r02 == null) goto L36;
        r02.delete();     // Catch: Exception -> L30
    L36:
        File r03 = r2.paintBlurFile;     // Catch: Exception -> L31
        if (r03 == null) goto L11;
        r03.delete();     // Catch: Exception -> L31
    L11:
        r2.paintFile = null;
        r2.paintEntitiesFile = null;
        r2.paintBlurFile = null;
        File r1 = r2.backgroundFile;
        if (r1 != null) goto L38;
    L18:
        File r12 = r2.messageVideoMaskFile;
        if (r12 != null) goto L40;
    L25:
        r2.editedMedia = r3 | r2.editedMedia;
        r2.mediaEntities = r4;
        r2.paintFile = r5;
        r2.backgroundFile = r6;
        r2.paintEntitiesFile = r7;
        r2.messageVideoMaskFile = r8;
        r2.paintBlurFile = r9;
        r2.stickers = r10;
        if (r11 == null) goto L42;
        r11.run();
        return;
    L42:
        return;
    L40:
        r12.delete();     // Catch: Exception -> L22
    L24:
        r2.messageVideoMaskFile = null;
    L22:
        e = move-exception;
        FileLog.e(e);
        goto L24
    L38:
        r1.delete();     // Catch: Exception -> L15
    L17:
        r2.backgroundFile = null;
    L15:
        e = move-exception;
        FileLog.e(e);
        goto L17
    }

    public static /* synthetic */ void $r8$lambda$1dIveoK2DQuFa2Ay6vLkxSgs9Sc(StoryRecorder r0, View r1) {
        r0.lambda$initViews$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$20zVowLnYRM6hl0k9SCOGQar_q0() {
        MessagesController.getGlobalMainSettings().edit().putInt("storydualhint", MessagesController.getGlobalMainSettings().getInt("storydualhint", 0) + 1).apply();
    }

    /* renamed from: $r8$lambda$2oBWyX2b5uzzbbnEs7o3Kc-tZ3E, reason: not valid java name */
    public static /* synthetic */ void m20346$r8$lambda$2oBWyX2b5uzzbbnEs7o3KctZ3E(StoryRecorder r0, DialogInterface r1) {
        r0.lambda$openPremium$113(r1);
    }

    public static /* synthetic */ void $r8$lambda$2sOnso6_2YCRw_N8XYn87m3045I(StoryRecorder r0, View r1) {
        r0.lambda$initViews$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$3T1dmUEWPz68PcBM7k3q5MIrZto(StoryRecorder r0, QRScanner.Detected r1) {
        r0.lambda$createCameraView$101(r1);
    }

    public static /* synthetic */ void $r8$lambda$3U3hN38q8YS6R9G1wcjMlgWyhFI(StoryRecorder r0) {
        r0.lambda$destroyCameraView$105();
    }

    /* renamed from: $r8$lambda$3faRa7L-fWpEjRbTfxuVaXQXDwY, reason: not valid java name */
    public static /* synthetic */ void m20347$r8$lambda$3faRa7LfWpEjRbTfxuVaXQXDwY(StoryRecorder r0) {
        r0.lambda$createPhotoPaintView$87();
    }

    public static /* synthetic */ void $r8$lambda$4Q9qhnNixMVIYC7WYM3dvCon0uc(StoryRecorder r0, float r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        r0.lambda$animateGalleryListView$76(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$4zhLGEpig6PiDkC-V8uZ9FajUME, reason: not valid java name */
    public static /* synthetic */ boolean m20348$r8$lambda$4zhLGEpig6PiDkCV8uZ9FajUME(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$5l7_8Bh2gLeS0Hq2BSGLoe627TU(StoryRecorder r0) {
        r0.lambda$initViews$14();
    }

    public static /* synthetic */ void $r8$lambda$639wkgAyDxAsts1UhoXQXvhop4c(StoryRecorder r0, View r1) {
        r0.lambda$initViews$28(r1);
    }

    /* renamed from: $r8$lambda$6IVf5IDBrkBuv9aVrZ-xPx8hRm4, reason: not valid java name */
    public static /* synthetic */ void m20349$r8$lambda$6IVf5IDBrkBuv9aVrZxPx8hRm4(StoryRecorder r0, View r1) {
        r0.lambda$initViews$39(r1);
    }

    public static /* synthetic */ void $r8$lambda$6aoMO0ZtBiQBp9Ji9qh2gNhk2qg(StoryRecorder r0, Boolean r1, Float r2) {
        r0.lambda$onNavigateStart$81(r1, r2);
    }

    /* renamed from: $r8$lambda$7DMcMTV9WHW-1mYsoW85sY5w9ck, reason: not valid java name */
    public static /* synthetic */ void m20350$r8$lambda$7DMcMTV9WHW1mYsoW85sY5w9ck(StoryRecorder r0, boolean r1) {
        r0.lambda$close$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$7hCTqI72SJ0PCoHOMf59Sy0WyXw(StoryRecorder r0, StoryPrivacyBottomSheet.StoryPrivacy r1) {
        r0.lambda$processDone$44(r1);
    }

    public static /* synthetic */ void $r8$lambda$81KRhDCAFwT9dpEHyKFSBLzXs3w(StoryRecorder r0, TLRPC.InputPeer r1) {
        r0.lambda$initViews$16(r1);
    }

    /* renamed from: $r8$lambda$82Ck1q0B1DRsE8Pu_K54H2mdG-E, reason: not valid java name */
    public static /* synthetic */ void m20351$r8$lambda$82Ck1q0B1DRsE8Pu_K54H2mdGE(StoryRecorder r0) {
        r0.lambda$initViews$9();
    }

    public static /* synthetic */ void $r8$lambda$8N8ONAL4zS8QnRjxRR7HUcda938(StoryRecorder r0, boolean r1) {
        r0.lambda$openEdit$0(r1);
    }

    /* renamed from: $r8$lambda$8R3Vn7HiXvJefq_-TeviHQE9V78, reason: not valid java name */
    public static /* synthetic */ void m20352$r8$lambda$8R3Vn7HiXvJefq_TeviHQE9V78(StoryRecorder r0, Float r1) {
        r0.lambda$initViews$22(r1);
    }

    /* renamed from: $r8$lambda$93zC-nBXf9NCK28lgjBfKVcufIo, reason: not valid java name */
    public static /* synthetic */ void m20353$r8$lambda$93zCnBXf9NCK28lgjBfKVcufIo(StoryRecorder r0) {
        r0.lambda$createPhotoPaintView$86();
    }

    public static /* synthetic */ void $r8$lambda$9WTA01oag0zajV_qt_EPZ6OC_CA(StoryRecorder r0, AlertDialog r1, int r2) {
        r0.lambda$showDismissEntry$103(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$9jEsJoKUSk57VYxkixSAQy8GXiw(StoryRecorder r0, boolean r1, Object r2, Bitmap r3) {
        r0.lambda$createGalleryListView$73(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$AOlNYAMnJIS1txGZaLzhnrteETc(StoryRecorder r0, Float r1) {
        r0.lambda$initViews$23(r1);
    }

    /* renamed from: $r8$lambda$BP00od4hhB-fvZVRQj5-SPCSf4c, reason: not valid java name */
    public static /* synthetic */ void m20354$r8$lambda$BP00od4hhBfvZVRQj5SPCSf4c(StoryEntry r2, boolean r3, ArrayList r4, File r5, File r6, File r7, File r8, File r9, List r10, Runnable r11) {
        File r0 = r2.paintFile;     // Catch: Exception -> L29
        if (r0 == null) goto L32;
        r0.delete();     // Catch: Exception -> L29
    L32:
        File r02 = r2.paintEntitiesFile;     // Catch: Exception -> L30
        if (r02 == null) goto L36;
        r02.delete();     // Catch: Exception -> L30
    L36:
        File r03 = r2.paintBlurFile;     // Catch: Exception -> L31
        if (r03 == null) goto L11;
        r03.delete();     // Catch: Exception -> L31
    L11:
        r2.paintFile = null;
        r2.paintEntitiesFile = null;
        r2.paintBlurFile = null;
        File r1 = r2.backgroundFile;
        if (r1 != null) goto L38;
    L18:
        File r12 = r2.messageVideoMaskFile;
        if (r12 != null) goto L40;
    L25:
        r2.editedMedia = r3 | r2.editedMedia;
        r2.mediaEntities = r4;
        r2.paintFile = r5;
        r2.backgroundFile = r6;
        r2.paintEntitiesFile = r7;
        r2.messageVideoMaskFile = r8;
        r2.paintBlurFile = r9;
        r2.stickers = r10;
        if (r11 == null) goto L42;
        r11.run();
        return;
    L42:
        return;
    L40:
        r12.delete();     // Catch: Exception -> L22
    L24:
        r2.messageVideoMaskFile = null;
    L22:
        e = move-exception;
        FileLog.e(e);
        goto L24
    L38:
        r1.delete();     // Catch: Exception -> L15
    L17:
        r2.backgroundFile = null;
    L15:
        e = move-exception;
        FileLog.e(e);
        goto L17
    }

    public static /* synthetic */ void $r8$lambda$C3qGE0yIMp_lnIvXYp9oPrAOX7k(StoryRecorder r0, TLObject r1, TL_stories.TL_startLive r2, boolean r3, long r4, boolean r6, TLRPC.TL_error r7, Runnable r8) {
        r0.lambda$startLive$55(r1, r2, r3, r4, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$DG762jU-S9YNSqcHi1O1DAWY-cM, reason: not valid java name */
    public static /* synthetic */ void m20355$r8$lambda$DG762jUS9YNSqcHi1O1DAWYcM(StoryRecorder r0, Integer r1) {
        r0.lambda$initViews$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$Dx9_fHDUxA7qkyBICPj9X05F4Sg(StoryRecorder r0) {
        r0.lambda$getThanosEffect$114();
    }

    public static /* synthetic */ void $r8$lambda$E8MOEtcJ8OazoNokFOF_ieT2IUs(StoryRecorder r0, float r1, float r2, ValueAnimator r3) {
        r0.lambda$animateContainerBack$70(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$EYKMM7Bu5hK2RrLcdUA33OfUAfk(StoryRecorder r0) {
        r0.lambda$createCameraView$100();
    }

    public static /* synthetic */ void $r8$lambda$EhtaiztlzHSLKW4PeITSF6hD86k(StoryRecorder r0) {
        r0.createFilterPhotoView();
    }

    public static /* synthetic */ void $r8$lambda$F2hEd4pQDOA6x9xfH5WSHjT_et4(StoryRecorder r0, ValueAnimator r1) {
        r0.lambda$switchToEditMode$83(r1);
    }

    public static /* synthetic */ void $r8$lambda$FJo7uQuaNNb__dvSh58AbYJGObM(StoryRecorder r0) {
        r0.lambda$createGalleryListView$72();
    }

    /* renamed from: $r8$lambda$Ffa8lR831gQFmg_onC-AMCbSeGQ, reason: not valid java name */
    public static /* synthetic */ void m20356$r8$lambda$Ffa8lR831gQFmg_onCAMCbSeGQ(StoryRecorder r0) {
        r0.lambda$toggleTheme$117();
    }

    public static /* synthetic */ void $r8$lambda$G61alH39KH37aKcIGlk361fcLGc(StoryRecorder r0, boolean r1, StoryEntry r2) {
        r0.lambda$uploadInternal$61(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$GSYPBzWp9HMD8AbY5tK0g4vcefA(StoryRecorder r0, ValueAnimator r1) {
        r0.lambda$switchToEditMode$85(r1);
    }

    public static /* synthetic */ void $r8$lambda$H0m7BHngYY5fXkCWw7TCAhZBJIQ(StoryRecorder r0) {
        r0.lambda$initViews$13();
    }

    public static /* synthetic */ void $r8$lambda$IRNIRRVbyqqFWB18ulQiPjXulCY(StoryRecorder r0) {
        r0.createCameraView();
    }

    public static /* synthetic */ void $r8$lambda$JIEcJd6L_5Cilkl17piSelHs6S8(StoryRecorder r0, Integer r1) {
        r0.lambda$initViews$43(r1);
    }

    public static /* synthetic */ void $r8$lambda$JJwq52YLe21SbXmOd7mmoGAA_dY(StoryRecorder r0) {
        r0.lambda$onNavigateStart$79();
    }

    /* renamed from: $r8$lambda$KkXdlmlK6-v7HlchrnHi4yTL5k8, reason: not valid java name */
    public static /* synthetic */ Bitmap m20357$r8$lambda$KkXdlmlK6v7HlchrnHi4yTL5k8(StoryRecorder r0) {
        return r0.getUiBlurBitmap();
    }

    /* renamed from: $r8$lambda$Kxx0eTlYJzyd3Z-05OGANBqGKzo, reason: not valid java name */
    public static /* synthetic */ void m20358$r8$lambda$Kxx0eTlYJzyd3Z05OGANBqGKzo(StoryRecorder r0, StoryEntry r1, File r2) {
        r0.lambda$prepareThumb$64(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Kz13ZEGxcHVUkAXjb1wv7MTSsBM(StoryRecorder r0) {
        r0.lambda$onNavigateStart$78();
    }

    public static /* synthetic */ void $r8$lambda$M1qfv6RZ3eu2Rkbwl5hZsj9lUIw(StoryRecorder r0, HashSet r1) {
        r0.lambda$processDone$46(r1);
    }

    public static /* synthetic */ void $r8$lambda$Mzg51oVAjB2WawyGN97F3Zpp_ak(StoryRecorder r0, boolean r1) {
        r0.lambda$animateGalleryListView$75(r1);
    }

    public static /* synthetic */ void $r8$lambda$NZLeKHIoWkeMbFmDjr5dDRLfsuc(StoryRecorder r0, StoryPrivacyBottomSheet.StoryPrivacy r1) {
        r0.lambda$startLive$52(r1);
    }

    public static /* synthetic */ void $r8$lambda$NlyIeDZR6vhTb6wQuqlis5uNkUI(StoryRecorder r0, View r1) {
        r0.lambda$initViews$31(r1);
    }

    public static /* synthetic */ void $r8$lambda$O1s7MO6Jg8bGfOvIIAglbzTJZa0(StoryRecorder r0, AlertDialog r1, int r2) {
        r0.lambda$onRequestPermissionsResultInternal$111(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$O_ajhsxBpkdGPBDwLqqmx6fch9c(StoryRecorder r0, Bitmap r1, Runnable r2) {
        r0.lambda$saveLastCameraBitmap$102(r1, r2);
    }

    /* renamed from: $r8$lambda$OeCh5EypcPyWasG24bNEAo8-MUM, reason: not valid java name */
    public static /* synthetic */ void m20359$r8$lambda$OeCh5EypcPyWasG24bNEAo8MUM(Bitmap r2, File r3) {
        r2.compress(Bitmap.CompressFormat.WEBP, 100, new FileOutputStream(r3));     // Catch: Throwable -> L7 Exception -> L9
    L4:
        if (r2.isRecycled() == true) goto L21;
        r2.recycle();
        return;
    L21:
        return;
    L7:
        th = move-exception;
        if (r2 != null) goto L18;
    L20:
        throw th;
    L18:
        if (r2.isRecycled() == true) goto L20;
        r2.recycle();
    L9:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L7
        if (r2 == null) goto L21;
        if (r2.isRecycled() == true) goto L21;
        r2.recycle();
        goto L21
    }

    /* renamed from: $r8$lambda$OtEc7N3OwGx4-GshRomG8-zYpr0, reason: not valid java name */
    public static /* synthetic */ void m20360$r8$lambda$OtEc7N3OwGx4GshRomG8zYpr0(StoryRecorder r0, ValueAnimator r1) {
        r0.lambda$switchToEditMode$82(r1);
    }

    public static /* synthetic */ void $r8$lambda$PX7sq6EmwwQTLWXATGXrgj6_C3I(StoryRecorder r0, Runnable r1, boolean r2, TLRPC.InputPeer r3, StoryPrivacyBottomSheet.StoryPrivacy r4, boolean r5, boolean r6, int r7, Boolean r8) {
        r0.lambda$startLive$57(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$QY_9uS0E3YZNhKesQd7gDMJTA9I(Utilities.Callback r1) {
        r1.run(LaunchActivity.getSafeLastFragment());
    }

    public static /* synthetic */ void $r8$lambda$S0qytbEgJ6aXKvtiU8CjpHWojR8(StoryRecorder r0) {
        r0.lambda$uploadInternal$62();
    }

    public static /* synthetic */ void $r8$lambda$SfNsKkBKRKj_zfchdXeLoIuqlSw(StoryRecorder r0) {
        r0.lambda$onCloseDone$4();
    }

    /* renamed from: $r8$lambda$U5Pbrfe1foPFJHLFl_Omj1h-MHg, reason: not valid java name */
    public static /* synthetic */ Bitmap m20361$r8$lambda$U5Pbrfe1foPFJHLFl_Omj1hMHg(StoryRecorder r0, BitmapFactory.Options r1) {
        return r0.lambda$createFilterPhotoView$97(r1);
    }

    public static /* synthetic */ void $r8$lambda$UKyrLfcjIl7Rq6guTKpeTuD4VX4(StoryRecorder r0, Bitmap r1) {
        r0.lambda$initViews$40(r1);
    }

    /* renamed from: $r8$lambda$UbEuQ83ZiSf-y24S1S9clncXFXs, reason: not valid java name */
    public static /* synthetic */ void m20362$r8$lambda$UbEuQ83ZiSfy24S1S9clncXFXs(StoryRecorder r0, TLRPC.InputPeer r1) {
        r0.lambda$processDone$45(r1);
    }

    /* renamed from: $r8$lambda$Um2wOf--KYEcV9-jU5VUCl1Aei4, reason: not valid java name */
    public static /* synthetic */ void m20363$r8$lambda$Um2wOfKYEcV9jU5VUCl1Aei4(StoryRecorder r0, Bitmap r1, boolean r2, File r3, StoryEntry r4) {
        r0.lambda$prepareThumb$65(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$Up-1KtMY7qxMTBNXifFh-STm2Dw, reason: not valid java name */
    public static /* synthetic */ void m20364$r8$lambda$Up1KtMY7qxMTBNXifFhSTm2Dw(StoryRecorder r0, Bitmap r1) {
        r0.lambda$processDone$49(r1);
    }

    public static /* synthetic */ void $r8$lambda$WRmc4JXPXoElR0a1Ztt0nUKSsE8(StoryRecorder r0, float r1) {
        r0.lambda$initViews$32(r1);
    }

    public static /* synthetic */ void $r8$lambda$WgSwBh5D89rsF2FY6Rs7bfVrI7A(StoryRecorder r0, Float r1) {
        r0.lambda$initViews$37(r1);
    }

    /* renamed from: $r8$lambda$Wrer02mxB_gJf__Rn-BmXn6ZPvw, reason: not valid java name */
    public static /* synthetic */ void m20365$r8$lambda$Wrer02mxB_gJf__RnBmXn6ZPvw(StoryRecorder r0, boolean r1, long r2) {
        r0.lambda$uploadInternal$63(r1, r2);
    }

    /* renamed from: $r8$lambda$XjTU-UXoZ0dqOTSJueGsZfDABLg, reason: not valid java name */
    public static /* synthetic */ void m20366$r8$lambda$XjTUUXoZ0dqOTSJueGsZfDABLg(StoryRecorder r0, Runnable r1) {
        r0.lambda$processDone$47(r1);
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$XlERzVdQZP8QcOLhK7rQrjb64dc(StoryRecorder r0, View r1, WindowInsetsCompat r2) {
        return r0.lambda$initViews$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Xu9FjYpAVpXEki5Tc7lfhAs97VE(StoryRecorder r0) {
        r0.lambda$destroyCameraView$106();
    }

    public static /* synthetic */ boolean $r8$lambda$YOdICaOAPQM8BWy5Nex6GssmRuE(StoryRecorder r0, View r1) {
        return r0.lambda$initViews$25(r1);
    }

    public static /* synthetic */ void $r8$lambda$YY9qleqQHKXEgNKAQYuDUcAlfZs(StoryRecorder r0) {
        r0.lambda$initViews$41();
    }

    /* renamed from: $r8$lambda$Z0x2eCarQyWimFGevVLfuTN-wIQ, reason: not valid java name */
    public static /* synthetic */ void m20367$r8$lambda$Z0x2eCarQyWimFGevVLfuTNwIQ(StoryRecorder r0, ValueAnimator r1) {
        r0.lambda$animateOpenTo$3(r1);
    }

    /* renamed from: $r8$lambda$Z9_a4AFdsBpjkIb-tJN4Hmt4Vq4, reason: not valid java name */
    public static /* synthetic */ void m20368$r8$lambda$Z9_a4AFdsBpjkIbtJN4Hmt4Vq4(StoryRecorder r0, Runnable r1) {
        r0.lambda$navigateToPreviewWithPlayerAwait$69(r1);
    }

    public static /* synthetic */ void $r8$lambda$Zdf9MGuqBAeKup61EPDVCxdJ1YI(StoryRecorder r0, View r1) {
        r0.lambda$initViews$26(r1);
    }

    public static /* synthetic */ void $r8$lambda$_GfejxcbmYEuxYcc29yYOmUo0Sk(StoryRecorder r0) {
        r0.lambda$initViews$24();
    }

    /* renamed from: $r8$lambda$_Tg_vRkZDDN0nvcRJKh7-s_AVvY, reason: not valid java name */
    public static /* synthetic */ void m20369$r8$lambda$_Tg_vRkZDDN0nvcRJKh7s_AVvY(StoryRecorder r0) {
        r0.onCloseDone();
    }

    public static /* synthetic */ void $r8$lambda$d8tca9MY9yKuXwAzuG0QP8Rl9vc(StoryRecorder r0) {
        r0.openPremium();
    }

    public static /* synthetic */ void $r8$lambda$epIQSqFlPw9pygnbNz2Ct9BVnsw(StoryRecorder r0, Runnable r1) {
        r0.lambda$initViews$18(r1);
    }

    /* renamed from: $r8$lambda$eyKE-mTcMIme47Nrs5jl11ILaf8, reason: not valid java name */
    public static /* synthetic */ void m20370$r8$lambda$eyKEmTcMIme47Nrs5jl11ILaf8(StoryRecorder r0) {
        r0.lambda$processDone$50();
    }

    public static /* synthetic */ void $r8$lambda$fEABZkW5pFaqhHU3TyetXS5zh58(StoryRecorder r0, Integer r1) {
        r0.lambda$initViews$11(r1);
    }

    /* renamed from: $r8$lambda$fEwR6S4ewjC-eCQGmA3gcL4U-rc, reason: not valid java name */
    public static /* synthetic */ void m20371$r8$lambda$fEwR6S4ewjCeCQGmA3gcL4Urc(StoryRecorder r0) {
        r0.lambda$initViews$33();
    }

    public static /* synthetic */ void $r8$lambda$g5ozfy4FlXQm3sitZ9vXh0OBJJw(StoryRecorder r0, Boolean r1, ArrayList r2, ArrayList r3) {
        r0.lambda$createGalleryListView$74(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$gh_UyUiHovxtc_nMuhTuiNVP2U8(StoryRecorder r0, CollageLayout r1) {
        r0.lambda$initViews$29(r1);
    }

    public static /* synthetic */ void $r8$lambda$gnVX2WyvY65V0GhMAFnwp0HS42Y(StoryRecorder r0, AlertDialog r1, int r2) {
        r0.lambda$onRequestPermissionsResultInternal$110(r1, r2);
    }

    /* renamed from: $r8$lambda$guA1JVtExtJP0JjLoyXPvwb-8WM, reason: not valid java name */
    public static /* synthetic */ void m20372$r8$lambda$guA1JVtExtJP0JjLoyXPvwb8WM(StoryRecorder r0, View r1) {
        r0.lambda$initViews$42(r1);
    }

    public static /* synthetic */ void $r8$lambda$hnt94ScvEJXkQ9ntKwQIVQ6Jk10(StoryRecorder r0, ValueAnimator r1) {
        r0.lambda$animateGalleryListView$77(r1);
    }

    /* renamed from: $r8$lambda$iHPC2nUP7ABMHUWxAlpcDaD4b-g, reason: not valid java name */
    public static /* synthetic */ void m20373$r8$lambda$iHPC2nUP7ABMHUWxAlpcDaD4bg(Bitmap r2, File r3) {
        r2.compress(Bitmap.CompressFormat.WEBP, 100, new FileOutputStream(r3));     // Catch: Throwable -> L5 Exception -> L7
        AndroidUtilities.recycleBitmap(r2);
        return;
    L5:
        th = move-exception;
        AndroidUtilities.recycleBitmap(r2);
        throw th;
    L7:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L5
        AndroidUtilities.recycleBitmap(r2);
    }

    /* renamed from: $r8$lambda$iJzIB5C3J6jrPH55m4U3aanb-II, reason: not valid java name */
    public static /* synthetic */ void m20374$r8$lambda$iJzIB5C3J6jrPH55m4U3aanbII(StoryRecorder r0, ValueAnimator r1) {
        r0.lambda$switchToEditMode$84(r1);
    }

    public static /* synthetic */ void $r8$lambda$iOyw70QndudV9iihNGyCttcXuR0(StoryRecorder r0, AlertDialog r1, int r2) {
        r0.lambda$onRequestPermissionsResultInternal$109(r1, r2);
    }

    /* renamed from: $r8$lambda$ivhII5KgFUxb-0j7aHD3CMLjap0, reason: not valid java name */
    public static /* synthetic */ void m20375$r8$lambda$ivhII5KgFUxb0j7aHD3CMLjap0(StoryRecorder r0, PaintView r1, int r2, int r3, StoryEntry r4, boolean r5, boolean r6, Runnable r7) {
        r0.lambda$applyPaintInBackground$89(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$jL4Wcmu6nHWeC1TSB9IDZ25zqIs(StoryRecorder r0, Integer r1) {
        r0.lambda$initViews$36(r1);
    }

    public static /* synthetic */ void $r8$lambda$kiUNXdMXUTU3FWU4pziIO8x5GkM(StoryRecorder r0) {
        r0.lambda$initViews$6();
    }

    public static /* synthetic */ void $r8$lambda$kktI1yoCH87fBbL76xgwd2hMqWE(StoryRecorder r0, TLRPC.InputPeer r1) {
        r0.lambda$startLive$53(r1);
    }

    public static /* synthetic */ void $r8$lambda$kpS36TI9lhC7HJol_gzW2lkVCyY(Bitmap r2, File r3) {
        r2.compress(Bitmap.CompressFormat.PNG, 87, new FileOutputStream(r3));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$lRWIYcOkDoX0l3rlMhs0hEwPO3g(StoryRecorder r0, View r1) {
        r0.lambda$getThemeButton$115(r1);
    }

    public static /* synthetic */ void $r8$lambda$layLMPzZu4ISw5ym5LQH0HHr4GM(Bitmap r2, File r3) {
        r2.compress(Bitmap.CompressFormat.PNG, 87, new FileOutputStream(r3));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$lgMCyeIdma54db96Zmig2YfOqPM(StoryRecorder r0, DialogInterface r1) {
        r0.lambda$processDone$51(r1);
    }

    public static /* synthetic */ void $r8$lambda$mqSd1CCUb1yoYDIOlM_XMeH5HK0(StoryRecorder r0, boolean r1) {
        r0.lambda$upload$59(r1);
    }

    public static /* synthetic */ void $r8$lambda$nE7j1lRtp8nCmHsGhAD7rM18TLw(StoryRecorder r0, View r1) {
        r0.lambda$createFilterPhotoView$98(r1);
    }

    public static /* synthetic */ void $r8$lambda$nI8uhbZayveqvhuTsGRZt1Cczlg(StoryRecorder r0) {
        r0.lambda$showZoomControls$68();
    }

    public static /* synthetic */ void $r8$lambda$nWtCuO5dKXPuupBiIN1QS3VSbz8(StoryRecorder r0, TL_stories.TL_startLive r1, boolean r2, long r3, boolean r5, Runnable r6, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$startLive$56(r1, r2, r3, r5, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$necwsF_MR12ODRX83xkq-F6_Uk4, reason: not valid java name */
    public static /* synthetic */ void m20376$r8$lambda$necwsF_MR12ODRX83xkqF6_Uk4(StoryRecorder r0, int r1) {
        r0.showPremiumPeriodBulletin(r1);
    }

    public static /* synthetic */ void $r8$lambda$npElp1e9uiMTk89wmBxkzJaJUN4(StoryRecorder r0, StoryPrivacyBottomSheet.StoryPrivacy r1, boolean r2, boolean r3, boolean r4, boolean r5, TLRPC.InputPeer r6, int r7, Runnable r8, Runnable r9) {
        r0.lambda$processDone$48(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    /* renamed from: $r8$lambda$oRU1Y2FUy-UoJTn8BD7PmwH5ltQ, reason: not valid java name */
    public static /* synthetic */ void m20377$r8$lambda$oRU1Y2FUyUoJTn8BD7PmwH5ltQ(StoryRecorder r0, boolean r1) {
        r0.lambda$showVideoTimer$66(r1);
    }

    public static /* synthetic */ void $r8$lambda$oSA21Q0RMlsjSanyJggsP05q6C0(StoryRecorder r0) {
        r0.lambda$onResumeInternal$108();
    }

    public static /* synthetic */ void $r8$lambda$p1EnWTor5yK7OeqRAHiXSX8JJ7M(Bitmap r2, File r3) {
        r2.compress(Bitmap.CompressFormat.PNG, 87, new FileOutputStream(r3));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    /* renamed from: $r8$lambda$pCj-aFG53Fud9sHwjoSGDMrJcnY, reason: not valid java name */
    public static /* synthetic */ void m20378$r8$lambda$pCjaFG53Fud9sHwjoSGDMrJcnY(StoryRecorder r0, Boolean r1) {
        r0.lambda$initViews$12(r1);
    }

    /* renamed from: $r8$lambda$qNB-0_IPGroewvt-5Gj7Z3Uu4P8, reason: not valid java name */
    public static /* synthetic */ void m20379$r8$lambda$qNB0_IPGroewvt5Gj7Z3Uu4P8(StoryRecorder r0, View r1) {
        r0.lambda$initViews$20(r1);
    }

    public static /* synthetic */ void $r8$lambda$qWBIkukI3oinGjjDCuwdRRtuofw(StoryRecorder r0, View r1) {
        r0.lambda$initViews$27(r1);
    }

    /* renamed from: $r8$lambda$qnH5nF9y_MSo-q67R3KuJpA1oNU, reason: not valid java name */
    public static /* synthetic */ void m20380$r8$lambda$qnH5nF9y_MSoq67R3KuJpA1oNU(StoryRecorder r0, View r1) {
        r0.lambda$initViews$38(r1);
    }

    /* renamed from: $r8$lambda$qw6C-ujLJvncNmeHVZDgeXZQN-c, reason: not valid java name */
    public static /* synthetic */ void m20381$r8$lambda$qw6CujLJvncNmeHVZDgeXZQNc(StoryRecorder r0, View r1) {
        r0.lambda$initViews$19(r1);
    }

    /* renamed from: $r8$lambda$rkjlqFcR-rgq2weFHKTSRNhcMfg, reason: not valid java name */
    public static /* synthetic */ void m20382$r8$lambda$rkjlqFcRrgq2weFHKTSRNhcMfg(StoryRecorder r0, View r1) {
        r0.lambda$initViews$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$s7wBDQQLo2IJw_7aUtTP5Kf5D5Y(StoryRecorder r0, View r1) {
        r0.lambda$createFilterPhotoView$99(r1);
    }

    /* renamed from: $r8$lambda$siDUpHxZ-3Gb83TuQrHLkJaoIbI, reason: not valid java name */
    public static /* synthetic */ void m20383$r8$lambda$siDUpHxZ3Gb83TuQrHLkJaoIbI(StoryRecorder r0, Context r1, View r2) {
        r0.lambda$initViews$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$uSIVhLMqsd6SNC0jESTape2hufo(StoryRecorder r0, PaintView r1, int r2, int r3, StoryEntry r4, boolean r5, boolean r6, Runnable r7) {
        r0.lambda$applyPaintSwitching$96(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$w2cQYJc0vyKXVt1vaymCZle7dtg(StoryRecorder r0) {
        r0.lambda$initViews$8();
    }

    /* renamed from: $r8$lambda$wB1D5gDsKOnWyHKFs-dK_c6TZio, reason: not valid java name */
    public static /* synthetic */ void m20384$r8$lambda$wB1D5gDsKOnWyHKFsdK_c6TZio(StoryRecorder r0, boolean r1, DialogInterface r2) {
        r0.lambda$showLimitReachedSheet$112(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$wJQH60hA6Q5ssEm36mDOlLRtuz4(StoryRecorder r0, StoryPrivacyBottomSheet.StoryPrivacy r1, boolean r2, boolean r3, boolean r4, boolean r5, TLRPC.InputPeer r6, int r7, Runnable r8, Runnable r9) {
        r0.lambda$startLive$58(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    /* renamed from: $r8$lambda$wOM1r5gFzOFJSBA-LEMSGCI0rck, reason: not valid java name */
    public static /* synthetic */ void m20385$r8$lambda$wOM1r5gFzOFJSBALEMSGCI0rck(StoryRecorder r0, AlertDialog r1, int r2) {
        r0.lambda$requestCameraPermission$107(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$xHpFCbNE0wkUYJnFCNjwxPxIevA(StoryRecorder r0, boolean r1, TL_stories.StoryItem r2, long r3, TLRPC.InputGroupCall r5, boolean r6) {
        r0.lambda$startLive$54(r1, r2, r3, r5, r6);
    }

    /* renamed from: $r8$lambda$y5X4eE-jOWPusM6BV3Vqw5ll9-U, reason: not valid java name */
    public static /* synthetic */ void m20386$r8$lambda$y5X4eEjOWPusM6BV3Vqw5ll9U(StoryRecorder r0) {
        r0.lambda$showZoomControls$67();
    }

    /* renamed from: $r8$lambda$yF2mJztZwcP6aP-HGBJyjb8NQsA, reason: not valid java name */
    public static /* synthetic */ void m20387$r8$lambda$yF2mJztZwcP6aPHGBJyjb8NQsA(StoryRecorder r0, AlertDialog r1, int r2) {
        r0.lambda$showDismissEntry$104(r1, r2);
    }

    /* renamed from: $r8$lambda$zXRPCJ-9XMMzMY0xMov4ce0W0Q8, reason: not valid java name */
    public static /* synthetic */ void m20388$r8$lambda$zXRPCJ9XMMzMY0xMov4ce0W0Q8(StoryRecorder r0, boolean r1) {
        r0.lambda$openForward$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$zeNLJ6cAdRuEMlyAlt8ZnAv7bto(StoryRecorder r0) {
        r0.onOpenDone();
    }

    public static /* synthetic */ void $r8$lambda$zj0Tzywu1PI7UX_Bj4mtFIUlAMU(StoryRecorder r0, Utilities.Callback r1) {
        r0.lambda$initViews$35(r1);
    }

    /* renamed from: $r8$lambda$zneqw439M_Jt-lDq1PgPq1R-mSs, reason: not valid java name */
    public static /* synthetic */ void m20389$r8$lambda$zneqw439M_JtlDq1PgPq1RmSs(StoryRecorder r0, int r1) {
        r0.lambda$switchTo$60(r1);
    }

    /* renamed from: -$$Nest$fgetactionBarContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20390$$Nest$fgetactionBarContainer(StoryRecorder r0) {
        return r0.actionBarContainer;
    }

    /* renamed from: -$$Nest$fgetactivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Activity m20391$$Nest$fgetactivity(StoryRecorder r0) {
        return r0.activity;
    }

    /* renamed from: -$$Nest$fgetapplyContainerViewTranslation2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20392$$Nest$fgetapplyContainerViewTranslation2(StoryRecorder r0) {
        return r0.applyContainerViewTranslation2;
    }

    /* renamed from: -$$Nest$fgetawaitingPlayer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20393$$Nest$fgetawaitingPlayer(StoryRecorder r0) {
        return r0.awaitingPlayer;
    }

    /* renamed from: -$$Nest$fgetblurManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurringShader.BlurManager m20394$$Nest$fgetblurManager(StoryRecorder r0) {
        return r0.blurManager;
    }

    /* renamed from: -$$Nest$fgetbotId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m20395$$Nest$fgetbotId(StoryRecorder r2) {
        return r2.botId;
    }

    /* renamed from: -$$Nest$fgetbotLang, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m20396$$Nest$fgetbotLang(StoryRecorder r0) {
        return r0.botLang;
    }

    /* renamed from: -$$Nest$fgetcameraHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HintView2 m20397$$Nest$fgetcameraHint(StoryRecorder r0) {
        return r0.cameraHint;
    }

    /* renamed from: -$$Nest$fgetcameraView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DualCameraView m20398$$Nest$fgetcameraView(StoryRecorder r0) {
        return r0.cameraView;
    }

    /* renamed from: -$$Nest$fgetcameraZoom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20399$$Nest$fgetcameraZoom(StoryRecorder r0) {
        return r0.cameraZoom;
    }

    /* renamed from: -$$Nest$fgetcaptionContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20400$$Nest$fgetcaptionContainer(StoryRecorder r0) {
        return r0.captionContainer;
    }

    /* renamed from: -$$Nest$fgetcaptionEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CaptionStory m20401$$Nest$fgetcaptionEdit(StoryRecorder r0) {
        return r0.captionEdit;
    }

    /* renamed from: -$$Nest$fgetcaptionEditOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m20402$$Nest$fgetcaptionEditOverlay(StoryRecorder r0) {
        return r0.captionEditOverlay;
    }

    /* renamed from: -$$Nest$fgetchangeDayNightView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m20403$$Nest$fgetchangeDayNightView(StoryRecorder r0) {
        return r0.changeDayNightView;
    }

    /* renamed from: -$$Nest$fgetchangeDayNightViewProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20404$$Nest$fgetchangeDayNightViewProgress(StoryRecorder r0) {
        return r0.changeDayNightViewProgress;
    }

    /* renamed from: -$$Nest$fgetclipPath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m20405$$Nest$fgetclipPath(StoryRecorder r0) {
        return r0.clipPath;
    }

    /* renamed from: -$$Nest$fgetcollageButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CollageLayoutButton m20406$$Nest$fgetcollageButton(StoryRecorder r0) {
        return r0.collageButton;
    }

    /* renamed from: -$$Nest$fgetcollageLayoutView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CollageLayoutView2 m20407$$Nest$fgetcollageLayoutView(StoryRecorder r0) {
        return r0.collageLayoutView;
    }

    /* renamed from: -$$Nest$fgetcollageListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CollageLayoutButton.CollageLayoutListView m20408$$Nest$fgetcollageListView(StoryRecorder r0) {
        return r0.collageListView;
    }

    /* renamed from: -$$Nest$fgetcontainerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContainerView m20409$$Nest$fgetcontainerView(StoryRecorder r0) {
        return r0.containerView;
    }

    /* renamed from: -$$Nest$fgetcontrolContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20410$$Nest$fgetcontrolContainer(StoryRecorder r0) {
        return r0.controlContainer;
    }

    /* renamed from: -$$Nest$fgetcropEditor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CropEditor m20411$$Nest$fgetcropEditor(StoryRecorder r0) {
        return r0.cropEditor;
    }

    /* renamed from: -$$Nest$fgetcropInlineEditor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CropInlineEditor m20412$$Nest$fgetcropInlineEditor(StoryRecorder r0) {
        return r0.cropInlineEditor;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20413$$Nest$fgetcurrentAccount(StoryRecorder r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcurrentEditMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20414$$Nest$fgetcurrentEditMode(StoryRecorder r0) {
        return r0.currentEditMode;
    }

    /* renamed from: -$$Nest$fgetcurrentPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20415$$Nest$fgetcurrentPage(StoryRecorder r0) {
        return r0.currentPage;
    }

    /* renamed from: -$$Nest$fgetcurrentRoundRecorder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RoundVideoRecorder m20416$$Nest$fgetcurrentRoundRecorder(StoryRecorder r0) {
        return r0.currentRoundRecorder;
    }

    /* renamed from: -$$Nest$fgetdismissProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20417$$Nest$fgetdismissProgress(StoryRecorder r0) {
        return r0.dismissProgress;
    }

    /* renamed from: -$$Nest$fgetdualButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ToggleButton m20418$$Nest$fgetdualButton(StoryRecorder r0) {
        return r0.dualButton;
    }

    /* renamed from: -$$Nest$fgetdualHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HintView2 m20419$$Nest$fgetdualHint(StoryRecorder r0) {
        return r0.dualHint;
    }

    /* renamed from: -$$Nest$fgetflashViews, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FlashViews m20420$$Nest$fgetflashViews(StoryRecorder r0) {
        return r0.flashViews;
    }

    /* renamed from: -$$Nest$fgetfromRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m20421$$Nest$fgetfromRect(StoryRecorder r0) {
        return r0.fromRect;
    }

    /* renamed from: -$$Nest$fgetfromRounding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20422$$Nest$fgetfromRounding(StoryRecorder r0) {
        return r0.fromRounding;
    }

    /* renamed from: -$$Nest$fgetfromSourceView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SourceView m20423$$Nest$fgetfromSourceView(StoryRecorder r0) {
        return r0.fromSourceView;
    }

    /* renamed from: -$$Nest$fgetfrontfaceFlashMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20424$$Nest$fgetfrontfaceFlashMode(StoryRecorder r0) {
        return r0.frontfaceFlashMode;
    }

    /* renamed from: -$$Nest$fgetfrontfaceFlashModes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20425$$Nest$fgetfrontfaceFlashModes(StoryRecorder r0) {
        return r0.frontfaceFlashModes;
    }

    /* renamed from: -$$Nest$fgetfrozenDismissProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Float m20426$$Nest$fgetfrozenDismissProgress(StoryRecorder r0) {
        return r0.frozenDismissProgress;
    }

    /* renamed from: -$$Nest$fgetfullRectF, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m20427$$Nest$fgetfullRectF(StoryRecorder r0) {
        return r0.fullRectF;
    }

    /* renamed from: -$$Nest$fgetgalleryClosing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20428$$Nest$fgetgalleryClosing(StoryRecorder r0) {
        return r0.galleryClosing;
    }

    /* renamed from: -$$Nest$fgetgalleryLayouted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m20429$$Nest$fgetgalleryLayouted(StoryRecorder r0) {
        return r0.galleryLayouted;
    }

    /* renamed from: -$$Nest$fgetgalleryListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GalleryListView m20430$$Nest$fgetgalleryListView(StoryRecorder r0) {
        return r0.galleryListView;
    }

    /* renamed from: -$$Nest$fgetgalleryListViewOpening, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Boolean m20431$$Nest$fgetgalleryListViewOpening(StoryRecorder r0) {
        return r0.galleryListViewOpening;
    }

    /* renamed from: -$$Nest$fgetgalleryOpenCloseAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m20432$$Nest$fgetgalleryOpenCloseAnimator(StoryRecorder r0) {
        return r0.galleryOpenCloseAnimator;
    }

    /* renamed from: -$$Nest$fgetgalleryOpenCloseSpringAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m20433$$Nest$fgetgalleryOpenCloseSpringAnimator(StoryRecorder r0) {
        return r0.galleryOpenCloseSpringAnimator;
    }

    /* renamed from: -$$Nest$fgethintTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HintTextView m20434$$Nest$fgethintTextView(StoryRecorder r0) {
        return r0.hintTextView;
    }

    /* renamed from: -$$Nest$fgetinsetBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20435$$Nest$fgetinsetBottom(StoryRecorder r0) {
        return r0.insetBottom;
    }

    /* renamed from: -$$Nest$fgetinsetLeft, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20436$$Nest$fgetinsetLeft(StoryRecorder r0) {
        return r0.insetLeft;
    }

    /* renamed from: -$$Nest$fgetinsetRight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20437$$Nest$fgetinsetRight(StoryRecorder r0) {
        return r0.insetRight;
    }

    /* renamed from: -$$Nest$fgetinsetTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20438$$Nest$fgetinsetTop(StoryRecorder r0) {
        return r0.insetTop;
    }

    /* renamed from: -$$Nest$fgetmode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20439$$Nest$fgetmode(StoryRecorder r0) {
        return r0.mode;
    }

    /* renamed from: -$$Nest$fgetmodeSwitcherView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryModeTabs m20440$$Nest$fgetmodeSwitcherView(StoryRecorder r0) {
        return r0.modeSwitcherView;
    }

    /* renamed from: -$$Nest$fgetnavbarContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20441$$Nest$fgetnavbarContainer(StoryRecorder r0) {
        return r0.navbarContainer;
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m20442$$Nest$fgetnotificationsLocker(StoryRecorder r0) {
        return r0.notificationsLocker;
    }

    /* renamed from: -$$Nest$fgetonFullyOpenListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m20443$$Nest$fgetonFullyOpenListener(StoryRecorder r0) {
        return r0.onFullyOpenListener;
    }

    /* renamed from: -$$Nest$fgetopenCloseAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m20444$$Nest$fgetopenCloseAnimator(StoryRecorder r0) {
        return r0.openCloseAnimator;
    }

    /* renamed from: -$$Nest$fgetopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20445$$Nest$fgetopenProgress(StoryRecorder r0) {
        return r0.openProgress;
    }

    /* renamed from: -$$Nest$fgetopenType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20446$$Nest$fgetopenType(StoryRecorder r0) {
        return r0.openType;
    }

    /* renamed from: -$$Nest$fgetoutputEntry, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryEntry m20447$$Nest$fgetoutputEntry(StoryRecorder r0) {
        return r0.outputEntry;
    }

    /* renamed from: -$$Nest$fgetoutputFile, reason: not valid java name */
    public static /* bridge */ /* synthetic */ File m20448$$Nest$fgetoutputFile(StoryRecorder r0) {
        return r0.outputFile;
    }

    /* renamed from: -$$Nest$fgetpaintView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaintView m20449$$Nest$fgetpaintView(StoryRecorder r0) {
        return r0.paintView;
    }

    /* renamed from: -$$Nest$fgetphotoFilterEnhanceView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoFilterView.EnhanceView m20450$$Nest$fgetphotoFilterEnhanceView(StoryRecorder r0) {
        return r0.photoFilterEnhanceView;
    }

    /* renamed from: -$$Nest$fgetphotoFilterView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoFilterView m20451$$Nest$fgetphotoFilterView(StoryRecorder r0) {
        return r0.photoFilterView;
    }

    /* renamed from: -$$Nest$fgetphotoFilterViewBlurControl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoFilterBlurControl m20452$$Nest$fgetphotoFilterViewBlurControl(StoryRecorder r0) {
        return r0.photoFilterViewBlurControl;
    }

    /* renamed from: -$$Nest$fgetphotoFilterViewCurvesControl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoFilterCurvesControl m20453$$Nest$fgetphotoFilterViewCurvesControl(StoryRecorder r0) {
        return r0.photoFilterViewCurvesControl;
    }

    /* renamed from: -$$Nest$fgetplayButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PlayPauseButton m20454$$Nest$fgetplayButton(StoryRecorder r0) {
        return r0.playButton;
    }

    /* renamed from: -$$Nest$fgetpreviewButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PreviewButtons m20455$$Nest$fgetpreviewButtons(StoryRecorder r0) {
        return r0.previewButtons;
    }

    /* renamed from: -$$Nest$fgetpreviewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20456$$Nest$fgetpreviewContainer(StoryRecorder r0) {
        return r0.previewContainer;
    }

    /* renamed from: -$$Nest$fgetpreviewH, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20457$$Nest$fgetpreviewH(StoryRecorder r0) {
        return r0.previewH;
    }

    /* renamed from: -$$Nest$fgetpreviewHighlight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PreviewHighlightView m20458$$Nest$fgetpreviewHighlight(StoryRecorder r0) {
        return r0.previewHighlight;
    }

    /* renamed from: -$$Nest$fgetpreviewTouchable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Touchable m20459$$Nest$fgetpreviewTouchable(StoryRecorder r0) {
        return r0.previewTouchable;
    }

    /* renamed from: -$$Nest$fgetpreviewView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PreviewView m20460$$Nest$fgetpreviewView(StoryRecorder r0) {
        return r0.previewView;
    }

    /* renamed from: -$$Nest$fgetpreviewW, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20461$$Nest$fgetpreviewW(StoryRecorder r0) {
        return r0.previewW;
    }

    /* renamed from: -$$Nest$fgetqrLinkView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScannedLinkPreview m20462$$Nest$fgetqrLinkView(StoryRecorder r0) {
        return r0.qrLinkView;
    }

    /* renamed from: -$$Nest$fgetqrScanner, reason: not valid java name */
    public static /* bridge */ /* synthetic */ QRScanner m20463$$Nest$fgetqrScanner(StoryRecorder r0) {
        return r0.qrScanner;
    }

    /* renamed from: -$$Nest$fgetrecordControl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecordControl m20464$$Nest$fgetrecordControl(StoryRecorder r0) {
        return r0.recordControl;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Rect m20465$$Nest$fgetrect(StoryRecorder r0) {
        return r0.rect;
    }

    /* renamed from: -$$Nest$fgetrectF, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m20466$$Nest$fgetrectF(StoryRecorder r0) {
        return r0.rectF;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m20467$$Nest$fgetresourcesProvider(StoryRecorder r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetsavedDualHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HintView2 m20468$$Nest$fgetsavedDualHint(StoryRecorder r0) {
        return r0.savedDualHint;
    }

    /* renamed from: -$$Nest$fgetscrollingX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20469$$Nest$fgetscrollingX(StoryRecorder r0) {
        return r0.scrollingX;
    }

    /* renamed from: -$$Nest$fgetscrollingY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20470$$Nest$fgetscrollingY(StoryRecorder r0) {
        return r0.scrollingY;
    }

    /* renamed from: -$$Nest$fgetstoppingTakingVideo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20471$$Nest$fgetstoppingTakingVideo(StoryRecorder r0) {
        return r0.stoppingTakingVideo;
    }

    /* renamed from: -$$Nest$fgettakingPhoto, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20472$$Nest$fgettakingPhoto(StoryRecorder r0) {
        return r0.takingPhoto;
    }

    /* renamed from: -$$Nest$fgettakingVideo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20473$$Nest$fgettakingVideo(StoryRecorder r0) {
        return r0.takingVideo;
    }

    /* renamed from: -$$Nest$fgetthanosEffect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ThanosEffect m20474$$Nest$fgetthanosEffect(StoryRecorder r0) {
        return r0.thanosEffect;
    }

    /* renamed from: -$$Nest$fgetthemeButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m20475$$Nest$fgetthemeButton(StoryRecorder r0) {
        return r0.themeButton;
    }

    /* renamed from: -$$Nest$fgetthemeSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryThemeSheet m20476$$Nest$fgetthemeSheet(StoryRecorder r0) {
        r0.getClass();
        return null;
    }

    /* renamed from: -$$Nest$fgettimelineView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TimelineView m20477$$Nest$fgettimelineView(StoryRecorder r0) {
        return r0.timelineView;
    }

    /* renamed from: -$$Nest$fgettrash, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TrashView m20478$$Nest$fgettrash(StoryRecorder r0) {
        return r0.trash;
    }

    /* renamed from: -$$Nest$fgetunderControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20479$$Nest$fgetunderControls(StoryRecorder r0) {
        return r0.underControls;
    }

    /* renamed from: -$$Nest$fgetunderStatusBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20480$$Nest$fgetunderStatusBar(StoryRecorder r0) {
        return r0.underStatusBar;
    }

    /* renamed from: -$$Nest$fgetvideoError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20481$$Nest$fgetvideoError(StoryRecorder r0) {
        return r0.videoError;
    }

    /* renamed from: -$$Nest$fgetvideoTimelineContainerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20482$$Nest$fgetvideoTimelineContainerView(StoryRecorder r0) {
        return r0.videoTimelineContainerView;
    }

    /* renamed from: -$$Nest$fgetvideoTimerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoTimerView m20483$$Nest$fgetvideoTimerView(StoryRecorder r0) {
        return r0.videoTimerView;
    }

    /* renamed from: -$$Nest$fgetwasGalleryOpen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20484$$Nest$fgetwasGalleryOpen(StoryRecorder r0) {
        return r0.wasGalleryOpen;
    }

    /* renamed from: -$$Nest$fgetwaveEffect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryWaveEffectView m20485$$Nest$fgetwaveEffect(StoryRecorder r0) {
        r0.getClass();
        return null;
    }

    /* renamed from: -$$Nest$fgetwindowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowView m20486$$Nest$fgetwindowView(StoryRecorder r0) {
        return r0.windowView;
    }

    /* renamed from: -$$Nest$fgetzoomControlView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ZoomControlView m20487$$Nest$fgetzoomControlView(StoryRecorder r0) {
        return r0.zoomControlView;
    }

    /* renamed from: -$$Nest$fputaudioGrantedCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20488$$Nest$fputaudioGrantedCallback(StoryRecorder r0, Runnable r1) {
        r0.audioGrantedCallback = r1;
    }

    /* renamed from: -$$Nest$fputcameraZoom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20489$$Nest$fputcameraZoom(StoryRecorder r0, float r1) {
        r0.cameraZoom = r1;
    }

    /* renamed from: -$$Nest$fputchangeDayNightView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20490$$Nest$fputchangeDayNightView(StoryRecorder r0, View r1) {
        r0.changeDayNightView = r1;
    }

    /* renamed from: -$$Nest$fputchangeDayNightViewAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20491$$Nest$fputchangeDayNightViewAnimator(StoryRecorder r0, ValueAnimator r1) {
        r0.changeDayNightViewAnimator = r1;
    }

    /* renamed from: -$$Nest$fputchangeDayNightViewProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20492$$Nest$fputchangeDayNightViewProgress(StoryRecorder r0, float r1) {
        r0.changeDayNightViewProgress = r1;
    }

    /* renamed from: -$$Nest$fputcontainerViewBackAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20493$$Nest$fputcontainerViewBackAnimator(StoryRecorder r0, ValueAnimator r1) {
        r0.containerViewBackAnimator = r1;
    }

    /* renamed from: -$$Nest$fputcurrentRoundRecorder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20494$$Nest$fputcurrentRoundRecorder(StoryRecorder r0, RoundVideoRecorder r1) {
        r0.currentRoundRecorder = r1;
    }

    /* renamed from: -$$Nest$fputdismissProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20495$$Nest$fputdismissProgress(StoryRecorder r0, float r1) {
        r0.dismissProgress = r1;
    }

    /* renamed from: -$$Nest$fputfromGallery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20496$$Nest$fputfromGallery(StoryRecorder r0, boolean r1) {
        r0.fromGallery = r1;
    }

    /* renamed from: -$$Nest$fputfrozenDismissProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20497$$Nest$fputfrozenDismissProgress(StoryRecorder r0, Float r1) {
        r0.frozenDismissProgress = r1;
    }

    /* renamed from: -$$Nest$fputgalleryClosing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20498$$Nest$fputgalleryClosing(StoryRecorder r0, boolean r1) {
        r0.galleryClosing = r1;
    }

    /* renamed from: -$$Nest$fputgalleryLayouted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20499$$Nest$fputgalleryLayouted(StoryRecorder r0, Runnable r1) {
        r0.galleryLayouted = r1;
    }

    /* renamed from: -$$Nest$fputgalleryListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20500$$Nest$fputgalleryListView(StoryRecorder r0, GalleryListView r1) {
        r0.galleryListView = r1;
    }

    /* renamed from: -$$Nest$fputgalleryListViewOpening, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20501$$Nest$fputgalleryListViewOpening(StoryRecorder r0, Boolean r1) {
        r0.galleryListViewOpening = r1;
    }

    /* renamed from: -$$Nest$fputgalleryOpenCloseAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20502$$Nest$fputgalleryOpenCloseAnimator(StoryRecorder r0, ValueAnimator r1) {
        r0.galleryOpenCloseAnimator = r1;
    }

    /* renamed from: -$$Nest$fputisDark, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20503$$Nest$fputisDark(StoryRecorder r0, boolean r1) {
        r0.isDark = r1;
    }

    /* renamed from: -$$Nest$fputlastCollageLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20504$$Nest$fputlastCollageLayout(StoryRecorder r0, CollageLayout r1) {
        r0.lastCollageLayout = r1;
    }

    /* renamed from: -$$Nest$fputmode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20505$$Nest$fputmode(StoryRecorder r0, int r1) {
        r0.mode = r1;
    }

    /* renamed from: -$$Nest$fputonFullyOpenListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20506$$Nest$fputonFullyOpenListener(StoryRecorder r0, Runnable r1) {
        r0.onFullyOpenListener = r1;
    }

    /* renamed from: -$$Nest$fputopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20507$$Nest$fputopenProgress(StoryRecorder r0, float r1) {
        r0.openProgress = r1;
    }

    /* renamed from: -$$Nest$fputoutputEntry, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20508$$Nest$fputoutputEntry(StoryRecorder r0, StoryEntry r1) {
        r0.outputEntry = r1;
    }

    /* renamed from: -$$Nest$fputoutputFile, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20509$$Nest$fputoutputFile(StoryRecorder r0, File r1) {
        r0.outputFile = r1;
    }

    /* renamed from: -$$Nest$fputpreviewH, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20510$$Nest$fputpreviewH(StoryRecorder r0, int r1) {
        r0.previewH = r1;
    }

    /* renamed from: -$$Nest$fputpreviewW, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20511$$Nest$fputpreviewW(StoryRecorder r0, int r1) {
        r0.previewW = r1;
    }

    /* renamed from: -$$Nest$fputscrollingX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20512$$Nest$fputscrollingX(StoryRecorder r0, boolean r1) {
        r0.scrollingX = r1;
    }

    /* renamed from: -$$Nest$fputscrollingY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20513$$Nest$fputscrollingY(StoryRecorder r0, boolean r1) {
        r0.scrollingY = r1;
    }

    /* renamed from: -$$Nest$fputstoppingTakingVideo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20514$$Nest$fputstoppingTakingVideo(StoryRecorder r0, boolean r1) {
        r0.stoppingTakingVideo = r1;
    }

    /* renamed from: -$$Nest$fputtakingPhoto, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20515$$Nest$fputtakingPhoto(StoryRecorder r0, boolean r1) {
        r0.takingPhoto = r1;
    }

    /* renamed from: -$$Nest$fputtakingVideo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20516$$Nest$fputtakingVideo(StoryRecorder r0, boolean r1) {
        r0.takingVideo = r1;
    }

    /* renamed from: -$$Nest$fputunderControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20517$$Nest$fputunderControls(StoryRecorder r0, int r1) {
        r0.underControls = r1;
    }

    /* renamed from: -$$Nest$fputunderStatusBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20518$$Nest$fputunderStatusBar(StoryRecorder r0, boolean r1) {
        r0.underStatusBar = r1;
    }

    /* renamed from: -$$Nest$fputzoomControlAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20519$$Nest$fputzoomControlAnimation(StoryRecorder r0, AnimatorSet r1) {
        r0.zoomControlAnimation = r1;
    }

    /* renamed from: -$$Nest$manimateContainerBack, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20520$$Nest$manimateContainerBack(StoryRecorder r0) {
        r0.animateContainerBack();
    }

    /* renamed from: -$$Nest$manimateGalleryListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20521$$Nest$manimateGalleryListView(StoryRecorder r0, boolean r1) {
        r0.animateGalleryListView(r1);
    }

    /* renamed from: -$$Nest$manimateRecording, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20522$$Nest$manimateRecording(StoryRecorder r0, boolean r1, boolean r2) {
        r0.animateRecording(r1, r2);
    }

    /* renamed from: -$$Nest$mapplyFilterMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20523$$Nest$mapplyFilterMatrix(StoryRecorder r0) {
        r0.applyFilterMatrix();
    }

    /* renamed from: -$$Nest$mapplyOpenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20524$$Nest$mapplyOpenProgress(StoryRecorder r0) {
        r0.applyOpenProgress();
    }

    /* renamed from: -$$Nest$mcheckBackgroundVisibility, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20525$$Nest$mcheckBackgroundVisibility(StoryRecorder r0) {
        r0.checkBackgroundVisibility();
    }

    /* renamed from: -$$Nest$mcheckFrontfaceFlashModes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20526$$Nest$mcheckFrontfaceFlashModes(StoryRecorder r0) {
        r0.checkFrontfaceFlashModes();
    }

    /* renamed from: -$$Nest$mcheckIsDark, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20527$$Nest$mcheckIsDark(StoryRecorder r0) {
        r0.checkIsDark();
    }

    /* renamed from: -$$Nest$mcreateGalleryListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20528$$Nest$mcreateGalleryListView(StoryRecorder r0) {
        r0.createGalleryListView();
    }

    /* renamed from: -$$Nest$mcreateGalleryListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20529$$Nest$mcreateGalleryListView(StoryRecorder r0, boolean r1) {
        r0.createGalleryListView(r1);
    }

    /* renamed from: -$$Nest$mcreatePhotoPaintView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20530$$Nest$mcreatePhotoPaintView(StoryRecorder r0) {
        r0.createPhotoPaintView();
    }

    /* renamed from: -$$Nest$mdestroyCameraView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20531$$Nest$mdestroyCameraView(StoryRecorder r0, boolean r1) {
        r0.destroyCameraView(r1);
    }

    /* renamed from: -$$Nest$mdestroyGalleryListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20532$$Nest$mdestroyGalleryListView(StoryRecorder r0) {
        r0.destroyGalleryListView();
    }

    /* renamed from: -$$Nest$mgetCameraThumb, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m20533$$Nest$mgetCameraThumb(StoryRecorder r0) {
        return r0.getCameraThumb();
    }

    /* renamed from: -$$Nest$mgetCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20534$$Nest$mgetCount(StoryRecorder r0) {
        return r0.getCount();
    }

    /* renamed from: -$$Nest$mgetCurrentFlashMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m20535$$Nest$mgetCurrentFlashMode(StoryRecorder r0) {
        return r0.getCurrentFlashMode();
    }

    /* renamed from: -$$Nest$minCheck, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20536$$Nest$minCheck(StoryRecorder r0) {
        return r0.inCheck();
    }

    /* renamed from: -$$Nest$misGalleryOpen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20537$$Nest$misGalleryOpen(StoryRecorder r0) {
        return r0.isGalleryOpen();
    }

    /* renamed from: -$$Nest$monNavigateEnd, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20538$$Nest$monNavigateEnd(StoryRecorder r0, int r1, int r2) {
        r0.onNavigateEnd(r1, r2);
    }

    /* renamed from: -$$Nest$monSwitchEditModeEnd, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20539$$Nest$monSwitchEditModeEnd(StoryRecorder r0, int r1, int r2) {
        r0.onSwitchEditModeEnd(r1, r2);
    }

    /* renamed from: -$$Nest$mopenPremium, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20540$$Nest$mopenPremium(StoryRecorder r0) {
        r0.openPremium();
    }

    /* renamed from: -$$Nest$mrequestAudioPermission, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20541$$Nest$mrequestAudioPermission(StoryRecorder r0) {
        return r0.requestAudioPermission();
    }

    /* renamed from: -$$Nest$mrequestGalleryPermission, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20542$$Nest$mrequestGalleryPermission(StoryRecorder r0) {
        return r0.requestGalleryPermission();
    }

    /* renamed from: -$$Nest$msaveCameraFace, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20543$$Nest$msaveCameraFace(StoryRecorder r0, boolean r1) {
        r0.saveCameraFace(r1);
    }

    /* renamed from: -$$Nest$msetAwakeLock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20544$$Nest$msetAwakeLock(StoryRecorder r0, boolean r1) {
        r0.setAwakeLock(r1);
    }

    /* renamed from: -$$Nest$msetCameraFlashModeIcon, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20545$$Nest$msetCameraFlashModeIcon(StoryRecorder r0, String r1, boolean r2) {
        r0.setCameraFlashModeIcon(r1, r2);
    }

    /* renamed from: -$$Nest$mshowVideoTimer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20546$$Nest$mshowVideoTimer(StoryRecorder r0, boolean r1, boolean r2) {
        r0.showVideoTimer(r1, r2);
    }

    /* renamed from: -$$Nest$mshowZoomControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20547$$Nest$mshowZoomControls(StoryRecorder r0, boolean r1, boolean r2) {
        r0.showZoomControls(r1, r2);
    }

    /* renamed from: -$$Nest$mswitchTo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20548$$Nest$mswitchTo(StoryRecorder r0, int r1) {
        r0.switchTo(r1);
    }

    /* renamed from: -$$Nest$mupdateActionBarButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20549$$Nest$mupdateActionBarButtons(StoryRecorder r0, boolean r1) {
        r0.updateActionBarButtons(r1);
    }

    /* renamed from: -$$Nest$mupdateActionBarButtonsOffsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20550$$Nest$mupdateActionBarButtonsOffsets(StoryRecorder r0) {
        r0.updateActionBarButtonsOffsets();
    }

    /* renamed from: -$$Nest$museDisplayFlashlight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20551$$Nest$museDisplayFlashlight(StoryRecorder r0) {
        return r0.useDisplayFlashlight();
    }

    static {
    }

    public StoryRecorder(Activity r5, int r6) {
        this.resourcesProvider = new DarkThemeResourceProvider();
        this.wasSendPeer = 0;
        this.fromRect = new RectF();
        this.canChangePeer = true;
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.rectF = new RectF();
        this.fullRectF = new RectF();
        this.clipPath = new Path();
        this.rect = new Rect();
        this.currentPage = 0;
        this.currentEditMode = -1;
        this.livePrivacy = new StoryPrivacyBottomSheet.StoryPrivacy();
        this.entries = null;
        this.selectedEntries = null;
        this.selectedEntriesOrder = null;
        this.mode = 0;
        this.takingPhoto = false;
        this.takingVideo = false;
        this.stoppingTakingVideo = false;
        this.awaitingPlayer = false;
        this.shiftDp = -3;
        this.preparingUpload = false;
        this.recordControlDelegate = new AnonymousClass13(this);
        this.videoTimerShown = true;
        this.applyContainerViewTranslation2 = true;
        this.frontfaceFlashMode = -1;
        this.activity = r5;
        this.currentAccount = r6;
        this.activityVisibilityController = LaunchActivity.obtainActivityVisibilityController();
        WindowManager.LayoutParams r62 = new WindowManager.LayoutParams();
        this.windowLayoutParams = r62;
        r62.height = -1;
        r62.width = -1;
        r62.format = -3;
        r62.gravity = 51;
        r62.type = 99;
        AndroidUtilities.applyEdgeToEdgeLayoutParams(r62);
        r62.flags = 134283520 | Integer.MIN_VALUE;
        r62.softInputMode = 16;
        this.windowManager = (WindowManager) r5.getSystemService("window");
        initViews();
    }

    private void animateContainerBack() {
        ValueAnimator r0 = this.containerViewBackAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.containerViewBackAnimator = null;
    L5:
        this.applyContainerViewTranslation2 = false;
        float r02 = this.containerView.getTranslationY1();
        float r1 = this.containerView.getTranslationY2();
        this.containerView.getAlpha();
        ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        this.containerViewBackAnimator = r2;
        r2.addUpdateListener(new StoryRecorder$$ExternalSyntheticLambda100(this, r02, r1));
        this.containerViewBackAnimator.setDuration(340);
        this.containerViewBackAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.containerViewBackAnimator.addListener(new AnonymousClass17(this));
        this.containerViewBackAnimator.start();
    }

    private void animateGalleryListView(boolean r8) {
        this.wasGalleryOpen = r8;
        Boolean r0 = this.galleryListViewOpening;
        if (r0 == null) goto L8;
        if (r0.booleanValue() != r8) goto L8;
        return;
    L8:
        if (this.galleryListView != null) goto L15;
        if (r8 == false) goto L12;
        createGalleryListView();
    L12:
        if (this.galleryListView != null) goto L15;
        return;
    L15:
        if (this.galleryListView.firstLayout == false) goto L18;
        this.galleryLayouted = new StoryRecorder$$ExternalSyntheticLambda15(this, r8);
        return;
    L18:
        ValueAnimator r02 = this.galleryOpenCloseAnimator;
        if (r02 == null) goto L21;
        r02.cancel();
        this.galleryOpenCloseAnimator = null;
    L21:
        SpringAnimation r03 = this.galleryOpenCloseSpringAnimator;
        if (r03 == null) goto L25;
        r03.cancel();
        this.galleryOpenCloseSpringAnimator = null;
    L25:
        if (this.galleryListView != null) goto L31;
        if (r8 == false) goto L29;
        createGalleryListView();
    L29:
        if (this.galleryListView != null) goto L31;
        return;
    L31:
        GalleryListView r04 = this.galleryListView;
        if (r04 == null) goto L35;
        r04.ignoreScroll = false;
    L35:
        if (r8 == false) goto L39;
        DraftSavedHint r3 = this.draftSavedHint;
        if (r3 == null) goto L39;
        r3.hide(true);
    L39:
        ContainerView r32 = this.containerView;
        int r4 = 4;
        if (r32 == null) goto L45;
        if (r8 == false) goto L43;
        int r5 = 4;
    L44:
        r32.setImportantForAccessibility(r5);
        goto L45
    L43:
        r5 = 0;
    L45:
        GalleryListView r33 = this.galleryListView;
        if (r8 == false) goto L48;
        r4 = 0;
    L48:
        r33.setImportantForAccessibility(r4);
        this.galleryListViewOpening = Boolean.valueOf(r8);
        float r34 = this.galleryListView.getTranslationY();
        if (r8 == false) goto L51;
        float r42 = 0.0f;
    L52:
        Math.max(1, this.windowView.getHeight());
        this.galleryListView.ignoreScroll = !r8;
        if (this.containerViewBackAnimator != null) goto L55;
        boolean r52 = true;
    L56:
        this.applyContainerViewTranslation2 = r52;
        if (r8 == false) goto L59;
        SpringAnimation r05 = new SpringAnimation(this.galleryListView, DynamicAnimation.TRANSLATION_Y, r42);
        this.galleryOpenCloseSpringAnimator = r05;
        r05.getSpring().setDampingRatio(0.75f);
        this.galleryOpenCloseSpringAnimator.getSpring().setStiffness(350.0f);
        this.galleryOpenCloseSpringAnimator.addEndListener(new StoryRecorder$$ExternalSyntheticLambda16(this, r42));
        this.galleryOpenCloseSpringAnimator.start();
    L60:
        if (r8 == false) goto L62;
    L64:
        if (r8 == false) goto L66;
        return;
    L66:
        if (this.currentPage == 0) goto L68;
        return;
    L68:
        if (this.noCameraPermission == true) goto L73;
        createCameraView();
        return;
    L73:
        return;
    L62:
        if (this.awaitingPlayer == true) goto L64;
        this.lastGalleryScrollPosition = null;
        goto L64
    L59:
        ValueAnimator r06 = ValueAnimator.ofFloat(new float[]{r34, r42});
        this.galleryOpenCloseAnimator = r06;
        r06.addUpdateListener(new StoryRecorder$$ExternalSyntheticLambda17(this));
        this.galleryOpenCloseAnimator.addListener(new AnonymousClass20(this));
        this.galleryOpenCloseAnimator.setDuration(450);
        this.galleryOpenCloseAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.galleryOpenCloseAnimator.start();
        goto L60
    L55:
        r52 = false;
        goto L56
    L51:
        r42 = (this.windowView.getHeight() - this.galleryListView.top()) + (AndroidUtilities.navigationBarHeight * 2.5f);
        goto L52
    }

    private void animateOpenTo(float r6, boolean r7, Runnable r8) {
        ValueAnimator r0 = this.openCloseAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
        this.openCloseAnimator = null;
    L6:
        if (r7 == false) goto L25;
        this.notificationsLocker.lock();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
        this.frozenDismissProgress = Float.valueOf(this.dismissProgress);
        ValueAnimator r72 = ValueAnimator.ofFloat(new float[]{this.openProgress, r6});
        this.openCloseAnimator = r72;
        r72.addUpdateListener(new StoryRecorder$$ExternalSyntheticLambda89(this));
        this.openCloseAnimator.addListener(new AnonymousClass1(this, r6, r8));
        if (r6 >= 1.0f) goto L13;
        if (this.wasSend == false) goto L13;
        this.openCloseAnimator.setDuration(250);
        this.openCloseAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
    L24:
        this.openCloseAnimator.start();
    L30:
        if (r6 <= 0.0f) goto L33;
        firstOpen = false;
        return;
    L33:
        return;
    L13:
        if (r6 <= 0.0f) goto L15;
    L23:
        this.openCloseAnimator.setDuration(300);
        this.openCloseAnimator.setInterpolator(new FastOutSlowInInterpolator());
        goto L24
    L15:
        if (this.containerView.getTranslationY1() < AndroidUtilities.dp(20.0f)) goto L23;
        if (r6 < 0.0f) goto L20;
    L22:
        this.openCloseAnimator.setDuration(400);
        this.openCloseAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        goto L24
    L20:
        if (this.fastClose == false) goto L22;
        this.openCloseAnimator.setDuration(200);
        this.openCloseAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.fastClose = false;
        goto L24
    L25:
        this.frozenDismissProgress = null;
        this.openProgress = r6;
        applyOpenProgress();
        this.containerView.invalidate();
        this.windowView.invalidate();
        if (r8 == null) goto L28;
        r8.run();
    L28:
        checkBackgroundVisibility();
        goto L30
    }

    private void animateRecording(boolean r14, boolean r15) {
        if (r14 == false) goto L16;
        HintView2 r0 = this.dualHint;
        if (r0 == null) goto L6;
        r0.hide();
    L6:
        HintView2 r02 = this.savedDualHint;
        if (r02 == null) goto L9;
        r02.hide();
    L9:
        HintView2 r03 = this.muteHint;
        if (r03 == null) goto L12;
        r03.hide();
    L12:
        HintView2 r04 = this.cameraHint;
        if (r04 == null) goto L16;
        r04.hide();
    L16:
        if (this.animatedRecording == r14) goto L18;
    L20:
        AnimatorSet r05 = this.recordingAnimator;
        if (r05 == null) goto L23;
        r05.cancel();
        this.recordingAnimator = null;
    L23:
        this.animatedRecording = r14;
        this.animatedRecordingWasInCheck = inCheck();
        if (r14 == false) goto L30;
        CollageLayoutButton.CollageLayoutListView r1 = this.collageListView;
        if (r1 == null) goto L30;
        if (r1.isVisible() == false) goto L30;
        this.collageListView.setVisible(false, r15);
    L30:
        updateActionBarButtons(r15);
        float r12 = 0.6f;
        float r2 = 1.0f;
        float r4 = 0.0f;
        if (r15 == false) goto L80;
        AnimatorSet r152 = new AnimatorSet();
        this.recordingAnimator = r152;
        HintTextView r5 = this.hintTextView;
        Property r6 = View.ALPHA;
        if (r14 == true) goto L35;
    L39:
        float r7 = 0.0f;
    L40:
        ObjectAnimator r52 = ObjectAnimator.ofFloat(r5, r6, new float[]{r7});
        HintTextView r72 = this.hintTextView;
        Property r9 = View.TRANSLATION_Y;
        if (r14 == true) goto L43;
    L47:
        float r10 = AndroidUtilities.dp(16.0f);
    L48:
        ObjectAnimator r73 = ObjectAnimator.ofFloat(r72, r9, new float[]{r10});
        HintTextView r102 = this.collageHintTextView;
        if (r14 == false) goto L51;
    L55:
        r12 = 0.0f;
    L56:
        ObjectAnimator r13 = ObjectAnimator.ofFloat(r102, r6, new float[]{r12});
        HintTextView r103 = this.collageHintTextView;
        if (r14 == false) goto L59;
    L63:
        float r11 = AndroidUtilities.dp(16.0f);
    L64:
        ObjectAnimator r104 = ObjectAnimator.ofFloat(r103, r9, new float[]{r11});
        StoryModeTabs r112 = this.modeSwitcherView;
        if (r14 == false) goto L67;
    L70:
        r2 = 0.0f;
    L71:
        ObjectAnimator r22 = ObjectAnimator.ofFloat(r112, r6, new float[]{r2});
        StoryModeTabs r62 = this.modeSwitcherView;
        if (r14 == false) goto L74;
    L77:
        r4 = AndroidUtilities.dp(16.0f);
    L78:
        r152.playTogether(new Animator[]{r52, r73, r13, r104, r22, ObjectAnimator.ofFloat(r62, r9, new float[]{r4})});
        this.recordingAnimator.setDuration(260);
        this.recordingAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.recordingAnimator.start();
        return;
    L74:
        if (this.currentPage != 0) goto L77;
        if (inCheck() == false) goto L78;
    L67:
        if (this.currentPage != 0) goto L70;
        if (inCheck() == false) goto L71;
    L59:
        if (this.currentPage != 0) goto L63;
        if (inCheck() == false) goto L63;
        r11 = 0.0f;
        goto L64
    L51:
        if (this.currentPage != 0) goto L55;
        if (inCheck() == false) goto L55;
    L43:
        if (this.currentPage != 0) goto L47;
        if (inCheck() == true) goto L47;
        r10 = 0.0f;
        goto L48
    L35:
        if (this.currentPage != 0) goto L39;
        if (inCheck() == true) goto L39;
        r7 = 1.0f;
        goto L40
    L80:
        HintTextView r153 = this.hintTextView;
        if (r14 == true) goto L83;
    L87:
        float r06 = 0.0f;
    L88:
        r153.setAlpha(r06);
        HintTextView r154 = this.hintTextView;
        if (r14 == true) goto L91;
    L95:
        float r07 = AndroidUtilities.dp(16.0f);
    L96:
        r154.setTranslationY(r07);
        HintTextView r155 = this.collageHintTextView;
        if (r14 == false) goto L99;
    L103:
        r12 = 0.0f;
    L104:
        r155.setAlpha(r12);
        HintTextView r156 = this.collageHintTextView;
        if (r14 == false) goto L107;
    L111:
        float r08 = AndroidUtilities.dp(16.0f);
    L112:
        r156.setTranslationY(r08);
        StoryModeTabs r157 = this.modeSwitcherView;
        if (r14 == false) goto L115;
    L118:
        r2 = 0.0f;
    L119:
        r157.setAlpha(r2);
        StoryModeTabs r158 = this.modeSwitcherView;
        if (r14 == false) goto L122;
    L125:
        r4 = AndroidUtilities.dp(16.0f);
    L126:
        r158.setTranslationY(r4);
        return;
    L122:
        if (this.currentPage != 0) goto L125;
        if (inCheck() == false) goto L126;
    L115:
        if (this.currentPage != 0) goto L118;
        if (inCheck() == false) goto L119;
    L107:
        if (this.currentPage != 0) goto L111;
        if (inCheck() == false) goto L111;
        r08 = 0.0f;
        goto L112
    L99:
        if (this.currentPage != 0) goto L103;
        if (inCheck() == false) goto L103;
    L91:
        if (this.currentPage != 0) goto L95;
        if (inCheck() == true) goto L95;
        r07 = 0.0f;
        goto L96
    L83:
        if (this.currentPage != 0) goto L87;
        if (inCheck() == true) goto L87;
        r06 = 1.0f;
        goto L88
    L18:
        if (this.animatedRecordingWasInCheck != inCheck()) goto L20;
    }

    private void applyFilter(Runnable r4) {
        PhotoFilterView r0 = this.photoFilterView;
        if (r0 == null) goto L15;
        StoryEntry r1 = this.outputEntry;
        if (r1 == null) goto L15;
        boolean r2 = r1.editedMedia;
        r1.editedMedia = r0.hasChanges() | r2;
        this.outputEntry.updateFilter(this.photoFilterView, r4);
        if (r4 != null) goto L18;
        StoryEntry r42 = this.outputEntry;
        if (r42.isVideo == true) goto L19;
        PreviewView r3 = this.previewView;
        if (r3 == null) goto L20;
        r3.set(r42);
        return;
    L20:
        return;
    L19:
        return;
    L18:
        return;
    L15:
        if (r4 == null) goto L21;
        r4.run();
        return;
    }

    private void applyFilterMatrix() {
        if (this.outputEntry != null) goto L5;
        return;
    L5:
        if (this.photoFilterViewTextureView != null) goto L7;
        return;
    L7:
        if (this.previewContainer.getMeasuredWidth() > 0) goto L9;
        return;
    L9:
        if (this.previewContainer.getMeasuredHeight() <= 0) goto L20;
        Matrix r0 = new Matrix();
        r0.reset();
        if (this.outputEntry.orientation == 0) goto L15;
        r0.postRotate(-r1, this.previewContainer.getMeasuredWidth() / 2.0f, this.previewContainer.getMeasuredHeight() / 2.0f);
        if (((this.outputEntry.orientation / 90) % 2) != 1) goto L15;
        r0.postScale(this.previewContainer.getMeasuredWidth() / this.previewContainer.getMeasuredHeight(), this.previewContainer.getMeasuredHeight() / this.previewContainer.getMeasuredWidth(), this.previewContainer.getMeasuredWidth() / 2.0f, this.previewContainer.getMeasuredHeight() / 2.0f);
    L15:
        r0.postScale((1.0f / this.previewContainer.getMeasuredWidth()) * this.outputEntry.width, (1.0f / this.previewContainer.getMeasuredHeight()) * this.outputEntry.height);
        r0.postConcat(this.outputEntry.matrix);
        r0.postScale(this.previewContainer.getMeasuredWidth() / this.outputEntry.resultWidth, this.previewContainer.getMeasuredHeight() / this.outputEntry.resultHeight);
        this.photoFilterViewTextureView.setTransform(r0);
        this.photoFilterViewTextureView.invalidate();
        return;
    }

    private void applyOpenProgress() {
        if (this.openType != 1) goto L15;
        this.fullRectF.set(this.previewContainer.getLeft(), this.previewContainer.getTop(), this.previewContainer.getMeasuredWidth(), this.previewContainer.getMeasuredHeight());
        this.fullRectF.offset(this.containerView.getX(), this.containerView.getY());
        AndroidUtilities.lerp(this.fromRect, this.fullRectF, this.openProgress, this.rectF);
        this.previewContainer.setAlpha(this.openProgress);
        this.previewContainer.setTranslationX((this.rectF.left - r0.getLeft()) - this.containerView.getX());
        this.previewContainer.setTranslationY((this.rectF.top - r0.getTop()) - this.containerView.getY());
        SourceView r0 = this.fromSourceView;
        if (r0 == null) goto L10;
        View r02 = r0.view;
        if (r02 == null) goto L10;
        r02.setTranslationX((this.fullRectF.left - this.fromRect.left) * this.openProgress);
        this.fromSourceView.view.setTranslationY((this.fullRectF.top - this.fromRect.top) * this.openProgress);
    L10:
        this.previewContainer.setScaleX(this.rectF.width() / this.previewContainer.getMeasuredWidth());
        this.previewContainer.setScaleY(this.rectF.height() / this.previewContainer.getMeasuredHeight());
        this.actionBarContainer.setAlpha(this.openProgress);
        this.controlContainer.setAlpha(this.openProgress);
        this.captionContainer.setAlpha(this.openProgress);
        if (this.currentPage != 2) goto L14;
        this.coverButton.setAlpha(this.openProgress);
        return;
    L14:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v35, types: [java.io.FileOutputStream, java.io.OutputStream] */
    private void applyPaint() {
        if (this.paintView == null) goto L137;
        StoryEntry r0 = this.outputEntry;
        if (r0 == null) goto L138;
        r0.clearPaint();
        this.outputEntry.editedMedia |= this.paintView.hasChanges();
        StoryEntry r02 = this.outputEntry;
        ArrayList<VideoEditedInfo.MediaEntity> r2 = r02.mediaEntities;
        if (r2 != null) goto L10;
        r02.mediaEntities = new ArrayList();
    L11:
        PaintView r3 = this.paintView;
        StoryEntry r11 = this.outputEntry;
        r3.getBitmap(r11.mediaEntities, r11.resultWidth, r11.resultHeight, false, false, false, false, r11);
        StoryEntry r03 = this.outputEntry;
        if (r03.isVideo == true) goto L14;
        r03.averageDuration = Utilities.clamp(this.paintView.getLcm(), 7500, 5000);
    L14:
        List<TLRPC.InputDocument> r04 = this.paintView.getMasks();
        StoryEntry r22 = this.outputEntry;
        if (r04 == null) goto L17;
        ArrayList r4 = new ArrayList(r04);
    L18:
        r22.stickers = r4;
        StoryEntry r05 = this.outputEntry;
        boolean r23 = r05.isVideo;
        boolean r42 = r05.wouldBeVideo();
        this.outputEntry.mediaEntities = new ArrayList();
        PaintView r6 = this.paintView;
        StoryEntry r14 = this.outputEntry;
        Bitmap r15 = r6.getBitmap(r14.mediaEntities, r14.resultWidth, r14.resultHeight, true, false, false, !r23, r14);
        if (this.outputEntry.mediaEntities.isEmpty() == false) goto L131;
        this.outputEntry.mediaEntities = null;
    L131:
        File r06 = this.outputEntry.paintFile;     // Catch: Exception -> L117
        if (r06 == null) goto L123;
        r06.delete();     // Catch: Exception -> L117
    L123:
        File r07 = this.outputEntry.paintEntitiesFile;     // Catch: Exception -> L118
        if (r07 == null) goto L127;
        r07.delete();     // Catch: Exception -> L118
    L127:
        File r08 = this.outputEntry.paintBlurFile;     // Catch: Exception -> L119
        if (r08 == null) goto L30;
        r08.delete();     // Catch: Exception -> L119
    L30:
        StoryEntry r09 = this.outputEntry;
        r09.paintFile = null;
        r09.paintEntitiesFile = null;
        r09.paintBlurFile = null;
        FileLoader r24 = FileLoader.getInstance(this.currentAccount);
        Bitmap.CompressFormat r16 = Bitmap.CompressFormat.PNG;
        StoryEntry r62 = this.outputEntry;
        r09.paintFile = r24.getPathToAttach(ImageLoader.scaleAndSaveImage(r15, r16, r62.resultWidth, r62.resultHeight, 87, false, 101, 101), true);
        if (r15 != null) goto L33;
    L35:
        StoryEntry r010 = this.outputEntry;
        if (r010.isRepostMessage == false) goto L66;
        File r011 = r010.backgroundFile;
        if (r011 != null) goto L134;
    L44:
        StoryEntry r012 = this.outputEntry;
        long r8 = r012.backgroundWallpaperPeerId;
        if (r8 == Long.MIN_VALUE) goto L66;
        Drawable r013 = r012.backgroundDrawable;
        if (r013 != null) goto L49;
        r013 = PreviewView.getBackgroundDrawable(null, this.currentAccount, r8, this.isDark);
    L49:
        if (r013 == null) goto L66;
        this.outputEntry.backgroundFile = StoryEntry.makeCacheFile(this.currentAccount, "webp");
        StoryEntry r25 = this.outputEntry;
        Bitmap r26 = Bitmap.createBitmap(r25.resultWidth, r25.resultHeight, Bitmap.Config.ARGB_8888);
        StoryEntry.drawBackgroundDrawable(new Canvas(r26), r013, r26.getWidth(), r26.getHeight());
        r26.compress(Bitmap.CompressFormat.WEBP, 100, new FileOutputStream(this.outputEntry.backgroundFile));     // Catch: Throwable -> L55 Exception -> L57
    L53:
        if (r26.isRecycled() == true) goto L66;
    L54:
        r26.recycle();
    L55:
        th = move-exception;
        if (r26.isRecycled() == true) goto L65;
        r26.recycle();
    L65:
        throw th;
    L57:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L55
        if (r26.isRecycled() == true) goto L66;
    L134:
        r011.delete();     // Catch: Exception -> L41
    L43:
        this.outputEntry.backgroundFile = null;
    L41:
        e = move-exception;
        FileLog.e(e);
    L66:
        StoryEntry r014 = this.outputEntry;
        boolean r27 = r014.isRepostMessage;
        File r28 = r27;
        if (r27 == false) goto L103;
        File r015 = r014.messageVideoMaskFile;
        if (r015 != null) goto L129;
    L75:
        StoryEntry r016 = this.outputEntry;
        boolean r29 = r016.isRepostMessage;
        r28 = r29;
        if (r29 == false) goto L103;
        boolean r210 = r016.isVideo;
        r28 = r210;
        if (r210 == false) goto L103;
        int r211 = r016.width;
        int r017 = r016.height;
        MessageEntityView r82 = this.paintView.findMessageView();
        r28 = r211;
        if (r82 == null) goto L103;
        r28 = r211;
        r28 = r211;
        r28 = r211;
        if (r82.listView.getChildCount() != 1) goto L103;
        if (r211 <= 0) goto L103;
        if (r017 <= 0) goto L103;
        int r10 = 0;
        r28 = r211;
        if ((r82.listView.getChildAt(0) instanceof ChatMessageCell) == false) goto L103;
        ImageReceiver r83 = ((ChatMessageCell) r82.listView.getChildAt(0)).getPhotoImage();
        r28 = r211;
        if (r83 == null) goto L103;
        r28 = r211;
        if (((int) r83.getImageWidth()) <= 0) goto L103;
        r28 = r211;
        if (((int) r83.getImageHeight()) <= 0) goto L103;
        float r212 = r211;
        float r018 = r017;
        float r9 = Math.max(r83.getImageWidth() / r212, r83.getImageHeight() / r018);
        int r213 = (int) ((r212 * r9) / 2.0f);
        int r019 = (int) ((r018 * r9) / 2.0f);
        Bitmap r92 = Bitmap.createBitmap(r213, r019, Bitmap.Config.ARGB_8888);
        float[] r12 = new float[8];
    L95:
        if (r10 >= r83.getRoundRadius().length) goto L97;
        int r13 = r10 * 2;
        r12[r13] = r83.getRoundRadius()[r10];
        r12[r13 + 1] = r83.getRoundRadius()[r10];
        r10 = r10 + 1;
        goto L95
    L97:
        Canvas r102 = new Canvas(r92);
        Path r132 = new Path();
        r102.scale(0.5f, 0.5f);
        RectF r142 = AndroidUtilities.rectTmp;
        float r214 = (r213 * 2.0f) / 2.0f;
        float r020 = (r019 * 2.0f) / 2.0f;
        r142.set(r214 - (r83.getImageWidth() / 2.0f), r020 - (r83.getImageHeight() / 2.0f), r214 + (r83.getImageWidth() / 2.0f), r020 + (r83.getImageHeight() / 2.0f));
        r132.addRoundRect(r142, r12, Path.Direction.CW);
        Paint r021 = new Paint(1);
        ?? r215 = -1;
        r021.setColor(-1);
        r102.drawPath(r132, r021);
        this.outputEntry.messageVideoMaskFile = StoryEntry.makeCacheFile(this.currentAccount, "webp");     // Catch: Exception -> L100
        Bitmap.CompressFormat r022 = Bitmap.CompressFormat.WEBP;     // Catch: Exception -> L100
        r215 = new FileOutputStream(this.outputEntry.messageVideoMaskFile);     // Catch: Exception -> L100
        r92.compress(r022, 100, r215);     // Catch: Exception -> L100
        boolean r216 = r215;
    L102:
        r92.recycle();
        r28 = r216;
    L100:
        e = move-exception;
        FileLog.e(e);
        this.outputEntry.messageVideoMaskFile = null;
        r216 = r215;
        goto L102
    L129:
        r015.delete();     // Catch: Exception -> L72
    L74:
        this.outputEntry.messageVideoMaskFile = null;
    L72:
        e = move-exception;
        FileLog.e(e);
    L103:
        if (r42 == true) goto L109;
        PaintView r103 = this.paintView;
        ArrayList<VideoEditedInfo.MediaEntity> r112 = new ArrayList();
        StoryEntry r023 = this.outputEntry;
        Bitmap r18 = r103.getBitmap(r112, r023.resultWidth, r023.resultHeight, false, true, false, false, r023);
        StoryEntry r024 = this.outputEntry;
        FileLoader r217 = FileLoader.getInstance(this.currentAccount);
        Bitmap.CompressFormat r19 = Bitmap.CompressFormat.PNG;
        StoryEntry r32 = this.outputEntry;
        r28 = r217.getPathToAttach(ImageLoader.scaleAndSaveImage(r18, r19, r32.resultWidth, r32.resultHeight, 87, false, 101, 101), true);
        r024.paintEntitiesFile = r28;
        if (r18 == null) goto L109;
        if (r18.isRecycled() == true) goto L109;
        r18.recycle();
    L109:
        r26 = r28;
        if (this.paintView.hasBlur() == false) goto L120;
        Bitmap r63 = this.paintView.getBlurBitmap();
        StoryEntry r025 = this.outputEntry;
        FileLoader r218 = FileLoader.getInstance(this.currentAccount);
        Bitmap.CompressFormat r7 = Bitmap.CompressFormat.PNG;
        StoryEntry r1 = this.outputEntry;
        r025.paintBlurFile = r218.getPathToAttach(ImageLoader.scaleAndSaveImage(r63, r7, r1.resultWidth, r1.resultHeight, 87, false, 101, 101), true);
        r26 = r218;
        if (r63 == null) goto L120;
        r26 = r218;
        if (r63.isRecycled() == true) goto L120;
        r63.recycle();
        r26 = r218;
    L120:
        return;
    L33:
        if (r15.isRecycled() == true) goto L35;
        r15.recycle();
        goto L35
    L17:
        r4 = null;
        goto L18
    L10:
        r2.clear();
        goto L11
    L138:
        return;
    }

    private void applyPaintInBackground(Runnable r11) {
        PaintView r2 = this.paintView;
        StoryEntry r5 = this.outputEntry;
        if (r2 == null) goto L8;
        if (r5 == null) goto L8;
        r5.clearPaint();
        boolean r7 = r2.hasChanges();
        boolean r6 = r2.hasBlur();
        int r3 = r5.resultWidth;
        int r4 = r5.resultHeight;
        Utilities.searchQueue.postRunnable(new StoryRecorder$$ExternalSyntheticLambda112(this, r2, r3, r4, r5, r6, r7, r11));
        return;
    L8:
        r11.run();
    }

    private void applyPaintMessage() {
        if (this.paintView == null) goto L43;
        StoryEntry r0 = this.outputEntry;
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.isRepostMessage == false) goto L52;
        File r02 = r0.messageFile;
        if (r02 != null) goto L47;
    L16:
        StoryEntry r03 = this.outputEntry;
        r03.messageFile = StoryEntry.makeCacheFile(this.currentAccount, "webp");
        PaintView r3 = this.paintView;
        StoryEntry r11 = this.outputEntry;
        ArrayList<VideoEditedInfo.MediaEntity> r4 = r11.mediaEntities;
        int r5 = r11.resultWidth;
        int r6 = r11.resultHeight;
        boolean r2 = true;
        if (this.mode != 1) goto L20;
        r2 = false;
    L20:
        Bitmap r22 = r3.getBitmap(r4, r5, r6, false, false, true, r2, r11);
        r22.compress(Bitmap.CompressFormat.WEBP, 100, new FileOutputStream(this.outputEntry.messageFile));     // Catch: Throwable -> L25 Exception -> L27
    L23:
        if (r22.isRecycled() == true) goto L53;
    L24:
        r22.recycle();
        return;
    L53:
        return;
    L25:
        th = move-exception;
        if (r22 != null) goto L40;
        throw th;
    L40:
        if (r22.isRecycled() == true) goto L51;
        r22.recycle();
        throw th;
    L51:
        throw th;
    L27:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L25
        this.outputEntry.messageFile.delete();     // Catch: Throwable -> L25 Exception -> L31
    L33:
        this.outputEntry.messageFile = null;     // Catch: Throwable -> L25
        if (r22 != null) goto L36;
        return;
    L36:
        if (r22.isRecycled() == false) goto L24;
        return;
    L31:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L25
        goto L33
    L47:
        r02.delete();     // Catch: Exception -> L13
    L15:
        this.outputEntry.messageFile = null;
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L15
    L52:
        return;
    }

    private void applyPaintSwitching(Runnable r11) {
        PaintView r2 = this.paintView;
        StoryEntry r5 = this.outputEntry;
        if (r2 == null) goto L12;
        if (r5 == null) goto L12;
        if (r2.hasChanges() == true) goto L10;
        r11.run();
        return;
    L10:
        r5.clearPaint();
        boolean r7 = r2.hasChanges();
        boolean r6 = r2.hasBlur();
        int r3 = r5.resultWidth;
        int r4 = r5.resultHeight;
        Utilities.searchQueue.postRunnable(new StoryRecorder$$ExternalSyntheticLambda115(this, r2, r3, r4, r5, r6, r7, r11));
        return;
    L12:
        r11.run();
    }

    public static CharSequence cameraBtnSpan(Context r5) {
        SpannableString r0 = new SpannableString("c");
        Drawable r52 = r5.getResources().getDrawable(R.drawable.story_camera).mutate();
        int r1 = AndroidUtilities.dp(35.0f);
        int r2 = -r1;
        r52.setBounds(r2 / 4, r2, (r1 / 4) * 3, 0);
        r0.setSpan(new AnonymousClass30(r52, r52), 0, 1, 33);
        return r0;
    }

    private void checkBackgroundVisibility() {
        if (this.dismissProgress == 0.0f) goto L5;
    L10:
        boolean r0 = true;
    L12:
        if (r0 != this.isBackgroundVisible) goto L14;
        return;
    L14:
        WindowVisibilityManager.Controller r1 = this.activityVisibilityController;
        if (r1 == null) goto L17;
        r1.setHidden(!r0);
    L17:
        this.isBackgroundVisible = r0;
        return;
    L5:
        if (this.openProgress < 1.0f) goto L10;
        if (this.forceBackgroundVisible == true) goto L10;
        r0 = false;
        goto L12
    }

    private void checkFrontfaceFlashModes() {
        if (this.frontfaceFlashMode >= 0) goto L6;
        this.frontfaceFlashMode = MessagesController.getGlobalMainSettings().getInt("frontflash", 1);
        ArrayList<String> r0 = new ArrayList();
        this.frontfaceFlashModes = r0;
        r0.add("off");
        this.frontfaceFlashModes.add("auto");
        this.frontfaceFlashModes.add("on");
        this.flashViews.setWarmth(MessagesController.getGlobalMainSettings().getFloat("frontflash_warmth", 0.9f));
        this.flashViews.setIntensity(MessagesController.getGlobalMainSettings().getFloat("frontflash_intensity", 1.0f));
        return;
    }

    private void checkIsDark() {
        DualCameraView r0 = this.cameraView;
        boolean r1 = false;
        if (r0 != null) goto L5;
    L23:
        this.isDark = false;
        return;
    L5:
        if (r0.getTextureView() == null) goto L23;
        Bitmap r02 = this.cameraView.getTextureView().getBitmap();
        if (r02 != null) goto L11;
        this.isDark = false;
        return;
    L11:
        int r2 = r02.getWidth() / 12;
        int r3 = r02.getHeight() / 12;
        float r4 = 0.0f;
        int r5 = 0;
    L13:
        if (r5 >= 10) goto L18;
        int r7 = 0;
    L15:
        if (r7 >= 10) goto L17;
        r7 = r7 + 1;
        r4 = r4 + AndroidUtilities.computePerceivedBrightness(r02.getPixel((r5 + 1) * r2, r7 * r3));
        goto L15
    L17:
        r5 = r5 + 1;
        goto L13
    L18:
        float r42 = r4 / 100.0f;
        r02.recycle();
        if (r42 >= 0.22f) goto L21;
        r1 = true;
    L21:
        this.isDark = r1;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void createCameraView() {
        if (this.cameraView == null) goto L5;
        return;
    L5:
        if (getContext() == null) goto L39;
        this.cameraView = new AnonymousClass25(this, getContext(), getCameraFace(), false);
        RecordControl r0 = this.recordControl;
        float r1 = 0.0f;
        if (r0 == null) goto L10;
        r0.setAmplitude(0.0f, false);
    L10:
        this.cameraView.recordHevc = !this.collageLayoutView.hasLayout();
        this.cameraView.setThumbDrawable(getCameraThumb());
        this.cameraView.initTexture();
        this.cameraView.setDelegate(new StoryRecorder$$ExternalSyntheticLambda13(this));
        ToggleButton r02 = this.dualButton;
        if (this.cameraView.dualAvailable() == true) goto L13;
    L15:
        boolean r2 = false;
    L16:
        setActionBarButtonVisible(r02, r2, true);
        CollageLayoutButton r03 = this.collageButton;
        if (this.cameraView.dualAvailable() == true) goto L20;
        r1 = AndroidUtilities.dp(46.0f);
    L20:
        r03.setTranslationX(r1);
        this.collageLayoutView.setCameraView(this.cameraView);
        if (MessagesController.getGlobalMainSettings().getInt("storyhint2", 0) >= 1) goto L24;
        this.cameraHint.show();
        MessagesController.getGlobalMainSettings().edit().putInt("storyhint2", MessagesController.getGlobalMainSettings().getInt("storyhint2", 0) + 1).apply();
    L31:
        if (this.qrScanner != null) goto L33;
        this.qrScanner = new QRScanner(getContext(), new StoryRecorder$$ExternalSyntheticLambda14(this));
    L33:
        this.qrScanner.attach(this.cameraView);
        ScannedLinkPreview r04 = this.qrLinkView;
        if (r04 == null) goto L38;
        CollageLayoutView2 r5 = this.collageLayoutView;
        r04.setBlurRenderNode(r5, r5.getBlurRenderNode());
        return;
    L38:
        return;
    L24:
        if (this.cameraView.isSavedDual() == true) goto L31;
        if (this.cameraView.dualAvailable() == false) goto L31;
        if (MessagesController.getGlobalMainSettings().getInt("storydualhint", 0) >= 2) goto L31;
        this.dualHint.show();
        goto L31
    L13:
        if (this.currentPage != 0) goto L15;
        r2 = true;
        goto L16
    }

    private void createFilterPhotoView() {
        if (this.photoFilterView != null) goto L40;
        StoryEntry r0 = this.outputEntry;
        if (r0 == null) goto L41;
        MediaController.SavedFilterState r2 = null;
        if (r0.isVideo == false) goto L10;
        Bitmap r6 = null;
    L15:
        if (r6 == null) goto L17;
    L19:
        Activity r4 = this.activity;
        VideoEditTextureView r5 = this.previewView.getTextureView();
        int r7 = this.previewView.getOrientation();
        StoryEntry r02 = this.outputEntry;
        if (r02 == null) goto L23;
        r2 = r02.filterState;
    L23:
        PhotoFilterView r3 = new PhotoFilterView(r4, r5, r6, r7, r2, null, 0, false, false, this.blurManager, this.resourcesProvider);
        this.photoFilterView = r3;
        this.containerView.addView(r3);
        PhotoFilterView.EnhanceView r03 = this.photoFilterEnhanceView;
        if (r03 == null) goto L26;
        r03.setFilterView(this.photoFilterView);
    L26:
        TextureView r04 = this.photoFilterView.getMyTextureView();
        this.photoFilterViewTextureView = r04;
        if (r04 == null) goto L29;
        r04.setOpaque(false);
    L29:
        this.previewView.setFilterTextureView(this.photoFilterViewTextureView, this.photoFilterView);
        TextureView r05 = this.photoFilterViewTextureView;
        if (r05 == null) goto L32;
        r05.setAlpha(0.0f);
        this.photoFilterViewTextureView.animate().alpha(1.0f).setDuration(220).start();
    L32:
        applyFilterMatrix();
        PhotoFilterBlurControl r06 = this.photoFilterView.getBlurControl();
        this.photoFilterViewBlurControl = r06;
        if (r06 == null) goto L35;
        this.previewContainer.addView(r06);
    L35:
        PhotoFilterCurvesControl r07 = this.photoFilterView.getCurveControl();
        this.photoFilterViewCurvesControl = r07;
        if (r07 == null) goto L38;
        this.previewContainer.addView(r07);
    L38:
        orderPreviewViews();
        this.photoFilterView.getDoneTextView().setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda78(this));
        this.photoFilterView.getCancelTextView().setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda79(this));
        this.photoFilterView.getToolsView().setVisibility(8);
        this.photoFilterView.getToolsView().setAlpha(0.0f);
        this.photoFilterView.getToolsView().setTranslationY(AndroidUtilities.dp(186.0f));
        this.photoFilterView.init();
        return;
    L17:
        if (this.outputEntry.isVideo == true) goto L19;
        return;
    L10:
        if (r0.filterFile != null) goto L13;
        Bitmap r08 = this.previewView.getPhotoBitmap();
    L12:
        r6 = r08;
        goto L15
    L13:
        StoryRecorder$$ExternalSyntheticLambda77 r09 = new StoryRecorder$$ExternalSyntheticLambda77(this);
        Point r1 = AndroidUtilities.displaySize;
        r08 = StoryEntry.getScaledBitmap(r09, r1.x, r1.y, true, true);
        goto L12
    L41:
        return;
    }

    private void createGalleryListView() {
        createGalleryListView(false);
    }

    private void createGalleryListView(boolean r14) {
        if (this.galleryListView == null) goto L13;
        CollageLayoutView2 r0 = this.collageLayoutView;
        if (r0 != null) goto L7;
    L9:
        boolean r02 = false;
    L11:
        if (r02 != this.galleryListView.onlyCollaging) goto L13;
        return;
    L7:
        if (r0.hasLayout() == false) goto L9;
        r02 = true;
    L13:
        if (getContext() != null) goto L16;
        return;
    L16:
        if (this.galleryListView == null) goto L18;
        destroyGalleryListView();
    L18:
        int r5 = this.currentAccount;
        Context r6 = getContext();
        Theme.ResourcesProvider r7 = this.resourcesProvider;
        MediaController.AlbumEntry r8 = this.lastGallerySelectedAlbum;
        boolean r11 = !r14;
        CollageLayoutView2 r03 = this.collageLayoutView;
        if (r03 != null) goto L21;
    L23:
        boolean r12 = false;
    L24:
        AnonymousClass19 r3 = new AnonymousClass19(this, r5, r6, r7, r8, r14, 1.39f, r11, r12);
        this.galleryListView = r3;
        r3.allowSearch(false);
        this.galleryListView.setMultipleOnClick(this.collageLayoutView.hasLayout());
        this.galleryListView.setMaxCount(Math.min(10, CollageLayout.getMaxCount() - this.collageLayoutView.getFilledCount()));
        this.galleryListView.setOnBackClickListener(new StoryRecorder$$ExternalSyntheticLambda92(this));
        this.galleryListView.setOnSelectListener(new StoryRecorder$$ExternalSyntheticLambda93(this, r14));
        this.galleryListView.setOnSelectMultipleListener(new StoryRecorder$$ExternalSyntheticLambda94(this));
        Parcelable r13 = this.lastGalleryScrollPosition;
        if (r13 == null) goto L27;
        this.galleryListView.layoutManager.onRestoreInstanceState(r13);
    L27:
        this.windowView.addView(this.galleryListView, LayoutHelper.createFrame(-1, -1, 119));
        return;
    L21:
        if (r03.hasLayout() == false) goto L23;
        r12 = true;
        goto L24
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.util.ArrayList] */
    private void createPhotoPaintView() {
        if (this.paintView == null) goto L5;
        return;
    L5:
        Pair<Integer, Integer> r0 = this.previewView.getPaintSize();
        StoryEntry r2 = this.outputEntry;
        if (r2 != null) goto L8;
    L16:
        Bitmap r22 = null;
    L17:
        if (r22 != null) goto L19;
        r22 = Bitmap.createBitmap(((Integer) r0.first).intValue(), ((Integer) r0.second).intValue(), Bitmap.Config.ARGB_8888);
    L19:
        Bitmap r10 = r22;
        StoryEntry r23 = this.outputEntry;
        if (r23 != null) goto L22;
    L33:
        Bitmap r24 = null;
    L32:
        boolean r4 = false;
    L34:
        if (r24 != null) goto L36;
        r24 = Bitmap.createBitmap(((Integer) r0.first).intValue(), ((Integer) r0.second).intValue(), Bitmap.Config.ARGB_8888);
    L36:
        Bitmap r11 = r24;
        int r16 = this.previewContainer.getMeasuredWidth();
        int r17 = this.previewContainer.getMeasuredHeight();
        Activity r25 = this.activity;
        StoryEntry r5 = this.outputEntry;
        if (r5 != null) goto L39;
    L41:
        File r6 = null;
        boolean r3 = false;
    L42:
        if (r5 != null) goto L44;
        File r7 = r6;
    L45:
        if (r5 != null) goto L47;
    L49:
        boolean r8 = false;
    L50:
        if (r5 != null) goto L52;
    L55:
        Object r52 = r6;
        boolean r62 = false;
    L54:
        boolean r9 = r4;
        File r42 = r7;
        WindowView r72 = this.windowView;
        int r92 = this.currentAccount;
        int r13 = this.previewView.getOrientation();
        StoryEntry r15 = this.outputEntry;
        Object r53 = r52;
        if (r15 != null) goto L59;
    L58:
        ?? r14 = r53;
        AnonymousClass24 r02 = new AnonymousClass24(this, r25, r3, r42, r8, r62, r72, r25, r92, r10, r11, null, r13, r14, r15, r16, r17, new MediaController.CropState(), null, this.blurManager, this.resourcesProvider, this.videoTextureHolder, this.previewView);
        this.paintView = r02;
        StoryEntry r26 = this.outputEntry;
        if (r26 != null) goto L63;
    L65:
        boolean r27 = false;
    L66:
        r02.setHasAudio(r27);
        this.paintView.setBlurManager(this.blurManager);
        this.containerView.addView(this.paintView);
        RenderView r03 = this.paintView.getRenderView();
        this.paintViewRenderView = r03;
        if (r03 == null) goto L69;
        r03.getPainting().hasBlur = r9;
        this.previewContainer.addView(this.paintViewRenderView);
    L69:
        View r04 = this.paintView.getRenderInputView();
        this.paintViewRenderInputView = r04;
        if (r04 == null) goto L72;
        this.previewContainer.addView(r04);
    L72:
        View r05 = this.paintView.getTextDimView();
        this.paintViewTextDim = r05;
        if (r05 == null) goto L75;
        this.previewContainer.addView(r05);
    L75:
        View r06 = this.paintView.getEntitiesView();
        this.paintViewEntitiesView = r06;
        if (r06 == null) goto L78;
        this.previewContainer.addView(r06);
    L78:
        View r07 = this.paintView.getSelectionEntitiesView();
        this.paintViewSelectionContainerView = r07;
        if (r07 == null) goto L81;
        this.previewContainer.addView(r07);
    L81:
        orderPreviewViews();
        this.paintView.setOnDoneButtonClickedListener(new StoryRecorder$$ExternalSyntheticLambda75(this));
        this.paintView.setOnCancelButtonClickedListener(new StoryRecorder$$ExternalSyntheticLambda76(this));
        this.paintView.init();
        return;
    L63:
        if (r26.audioPath == null) goto L65;
        r27 = true;
        goto L66
    L59:
        r53 = r15.mediaEntities;
        goto L58
    L52:
        if (r5.botId == 0) goto L55;
        r52 = r6;
        r62 = true;
        goto L54
    L47:
        if (r5.isVideo == false) goto L49;
        r8 = true;
        goto L50
    L44:
        r7 = r5.file;
        goto L45
    L39:
        if (r5.fileDeletable == true) goto L41;
        r6 = null;
        r3 = true;
        goto L42
    L22:
        if (r23.isDraft == false) goto L24;
    L27:
        File r28 = r23.paintBlurFile;
        if (r28 == null) goto L33;
        r24 = BitmapFactory.decodeFile(r28.getPath());
        if (r24 == null) goto L32;
        r4 = true;
        goto L34
    L24:
        if (r23.isEdit == true) goto L27;
        if (this.entries == null) goto L33;
    L8:
        if (r2.isDraft == false) goto L10;
    L13:
        File r29 = r2.paintFile;
        if (r29 == null) goto L16;
        r22 = BitmapFactory.decodeFile(r29.getPath());
        goto L17
    L10:
        if (r2.isEdit == true) goto L13;
        if (this.entries == null) goto L16;
        goto L13
    }

    private void destroyCameraView(boolean r3) {
        QRScanner r0 = this.qrScanner;
        if (r0 == null) goto L7;
        r0.destroy();
        this.qrScanner = null;
        CollageLayoutView2 r02 = this.collageLayoutView;
        if (r02 == null) goto L7;
        r02.qrDrawer.setQrDetected(null);
    L7:
        ScannedLinkPreview r03 = this.qrLinkView;
        if (r03 == null) goto L11;
        r03.setBlurRenderNode(null, null);
    L11:
        if (this.cameraView == null) goto L20;
        if (r3 == false) goto L15;
        saveLastCameraBitmap(new StoryRecorder$$ExternalSyntheticLambda11(this));
        return;
    L15:
        saveLastCameraBitmap(new StoryRecorder$$ExternalSyntheticLambda12(this));
        this.cameraView.destroy(true, null);
        AndroidUtilities.removeFromParent(this.cameraView);
        CollageLayoutView2 r32 = this.collageLayoutView;
        if (r32 == null) goto L18;
        r32.setCameraView(null);
    L18:
        this.cameraView = null;
        return;
    }

    private void destroyGalleryListView() {
        GalleryListView r0 = this.galleryListView;
        if (r0 != null) goto L5;
        return;
    L5:
        this.windowView.removeView(r0);
        this.galleryListView = null;
        ValueAnimator r1 = this.galleryOpenCloseAnimator;
        if (r1 == null) goto L8;
        r1.cancel();
        this.galleryOpenCloseAnimator = null;
    L8:
        SpringAnimation r12 = this.galleryOpenCloseSpringAnimator;
        if (r12 == null) goto L11;
        r12.cancel();
        this.galleryOpenCloseSpringAnimator = null;
    L11:
        this.galleryListViewOpening = null;
    }

    public static void destroyInstance() {
        StoryRecorder r0 = instance;
        if (r0 == null) goto L5;
        r0.close(false);
    L5:
        instance = null;
    }

    private void destroyPhotoFilterView() {
        PhotoFilterView r0 = this.photoFilterView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.shutdown();
        this.photoFilterEnhanceView.setFilterView(null);
        this.containerView.removeView(this.photoFilterView);
        TextureView r02 = this.photoFilterViewTextureView;
        if (r02 == null) goto L8;
        this.previewContainer.removeView(r02);
        this.photoFilterViewTextureView = null;
    L8:
        this.previewView.setFilterTextureView(null, null);
        PhotoFilterBlurControl r03 = this.photoFilterViewBlurControl;
        if (r03 == null) goto L11;
        this.previewContainer.removeView(r03);
        this.photoFilterViewBlurControl = null;
    L11:
        PhotoFilterCurvesControl r04 = this.photoFilterViewCurvesControl;
        if (r04 == null) goto L14;
        this.previewContainer.removeView(r04);
        this.photoFilterViewCurvesControl = null;
    L14:
        this.photoFilterView = null;
    }

    private void destroyPhotoPaintView() {
        PaintView r0 = this.paintView;
        if (r0 == null) goto L22;
        r0.onCleanupEntities();
        this.paintView.shutdown();
        this.containerView.removeView(this.paintView);
        this.paintView = null;
        RenderView r1 = this.paintViewRenderView;
        if (r1 == null) goto L8;
        this.previewContainer.removeView(r1);
        this.paintViewRenderView = null;
    L8:
        View r12 = this.paintViewTextDim;
        if (r12 == null) goto L11;
        this.previewContainer.removeView(r12);
        this.paintViewTextDim = null;
    L11:
        View r13 = this.paintViewRenderInputView;
        if (r13 == null) goto L14;
        this.previewContainer.removeView(r13);
        this.paintViewRenderInputView = null;
    L14:
        View r14 = this.paintViewEntitiesView;
        if (r14 == null) goto L17;
        this.previewContainer.removeView(r14);
        this.paintViewEntitiesView = null;
    L17:
        View r15 = this.paintViewSelectionContainerView;
        if (r15 == null) goto L21;
        this.previewContainer.removeView(r15);
        this.paintViewSelectionContainerView = null;
        return;
    L21:
        return;
    }

    private boolean getCameraFace() {
        return MessagesController.getGlobalMainSettings().getBoolean("stories_camera", false);
    }

    private Drawable getCameraThumb() {
        Bitmap r0 = BitmapFactory.decodeFile(new File(ApplicationLoader.getFilesDirFixed(), "cthumb.jpg").getAbsolutePath());     // Catch: Throwable -> L4
    L5:
        if (r0 == null) goto L9;
        return new BitmapDrawable(r0);
    L9:
        return getContext().getResources().getDrawable(R.drawable.icplaceholder);
    L4:
        r0 = null;
        goto L5
    }

    private int getCount() {
        ArrayList<Integer> r0 = this.selectedEntries;
        if (r0 != null) goto L5;
        StoryEntry r1 = this.outputEntry;
        if (r1 != null) goto L9;
        return 1;
    L9:
        return r1.getTotalCount();
    L5:
        return r0.size();
    }

    private String getCurrentFlashMode() {
        DualCameraView r0 = this.cameraView;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.getCameraSession() != null) goto L8;
        return null;
    L8:
        if (this.cameraView.isFrontface() == false) goto L14;
        if (this.cameraView.getCameraSession().hasFlashModes() == true) goto L14;
        checkFrontfaceFlashModes();
        return this.frontfaceFlashModes.get(this.frontfaceFlashMode);
    L14:
        return this.cameraView.getCameraSession().getCurrentFlashMode();
    }

    private DraftSavedHint getDraftSavedHint() {
        if (this.draftSavedHint != null) goto L6;
        DraftSavedHint r0 = new DraftSavedHint(getContext());
        this.draftSavedHint = r0;
        this.controlContainer.addView(r0, LayoutHelper.createFrame(-1, -2.0f, 87, 0.0f, 0.0f, 0.0f, 78.0f));
    L6:
        return this.draftSavedHint;
    }

    public static StoryRecorder getInstance(Activity r2, int r3) {
        StoryRecorder r0 = instance;
        if (r0 == null) goto L10;
        if (r0.activity == r2) goto L7;
    L8:
        r0.close(false);
        instance = null;
        goto L10
    L7:
        if (r0.currentAccount != r3) goto L8;
    L10:
        if (instance != null) goto L13;
        instance = new StoryRecorder(r2, r3);
    L13:
        return instance;
    }

    private TLRPC.InputPeer getLivePeer() {
        TLRPC.InputPeer r0 = this.livePeer;
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (this.selectedDialogId == 0) goto L10;
        return MessagesController.getInstance(this.currentAccount).getInputPeer(this.selectedDialogId);
    L10:
        return new TLRPC.TL_inputPeerSelf();
    }

    private String getNextFlashMode() {
        DualCameraView r0 = this.cameraView;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.getCameraSession() != null) goto L8;
        return null;
    L8:
        if (this.cameraView.isFrontface() == false) goto L18;
        if (this.cameraView.getCameraSession().hasFlashModes() == true) goto L18;
        checkFrontfaceFlashModes();
        ArrayList<String> r02 = this.frontfaceFlashModes;
        if ((this.frontfaceFlashMode + 1) < r02.size()) goto L14;
        int r3 = 0;
    L16:
        return r02.get(r3);
    L14:
        r3 = this.frontfaceFlashMode + 1;
    L18:
        return this.cameraView.getCameraSession().getNextFlashMode();
    }

    private Bitmap getUiBlurBitmap() {
        PhotoFilterView r0 = this.photoFilterView;
        if (r0 == null) goto L5;
        Bitmap r02 = r0.getUiBlurBitmap();
    L6:
        if (r02 != null) goto L13;
        PreviewView r1 = this.previewView;
        if (r1 == null) goto L13;
        if (r1.getTextureView() == null) goto L13;
        return this.previewView.getTextureView().getUiBlurBitmap();
    L13:
        return r02;
    L5:
        r02 = null;
        goto L6
    }

    private ArrayList<String> getUsersFrom(CharSequence r10) {
        ArrayList<String> r0 = new ArrayList();
        int r2 = 0;
        if ((r10 instanceof Spanned) == false) goto L19;
        URLSpanUserMention[] r1 = (URLSpanUserMention[]) ((Spanned) r10).getSpans(0, r10.length(), URLSpanUserMention.class);
        int r3 = 0;
    L6:
        if (r3 >= r1.length) goto L19;
        URLSpanUserMention r4 = r1[r3];
        if (r4 == null) goto L18;
        TLRPC.User r42 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(Long.parseLong(r4.getURL())));     // Catch: Exception -> L59
        if (r42 == null) goto L18;
        if (UserObject.isUserSelf(r42) == true) goto L18;
        if (UserObject.getPublicUsername(r42) == null) goto L18;
        if (r0.contains(r42) == true) goto L18;
        r0.add(UserObject.getPublicUsername(r42));     // Catch: Exception -> L59
    L18:
        r3 = r3 + 1;
    L19:
        if (r10 == null) goto L58;
        int r32 = -1;
    L22:
        if (r2 >= r10.length()) goto L44;
        char r43 = r10.charAt(r2);
        if (r43 != '@') goto L27;
        r32 = r2 + 1;
    L43:
        r2 = r2 + 1;
        goto L22
    L27:
        if (r43 != ' ') goto L43;
        if (r32 == (-1)) goto L42;
        String r33 = r10.subSequence(r32, r2).toString();
        TLObject r44 = MessagesController.getInstance(this.currentAccount).getUserOrChat(r33);
        if ((r44 instanceof TLRPC.User) == false) goto L42;
        TLRPC.User r45 = (TLRPC.User) r44;
        if (r45.bot == true) goto L42;
        if (UserObject.isUserSelf(r45) == true) goto L42;
        if (r45.id == 777000) goto L42;
        if (UserObject.isReplyUser(r45) == true) goto L42;
        if (r0.contains(r33) == true) goto L42;
        r0.add(r33);
    L42:
        r32 = -1;
        goto L43
    L44:
        if (r32 == (-1)) goto L58;
        String r102 = r10.subSequence(r32, r10.length()).toString();
        TLObject r9 = MessagesController.getInstance(this.currentAccount).getUserOrChat(r102);
        if ((r9 instanceof TLRPC.User) == false) goto L58;
        TLRPC.User r92 = (TLRPC.User) r9;
        if (r92.bot == true) goto L58;
        if (UserObject.isUserSelf(r92) == true) goto L58;
        if (r92.id == 777000) goto L58;
        if (UserObject.isReplyUser(r92) == true) goto L58;
        if (r0.contains(r102) == true) goto L58;
        r0.add(r102);
    L58:
        return r0;
    }

    private void hidePhotoPaintView() {
        PaintView r0 = this.paintView;
        if (r0 != null) goto L5;
        return;
    L5:
        this.previewTouchable = null;
        r0.getTopLayout().setAlpha(0.0f);
        this.paintView.getTopLayout().setTranslationY(-AndroidUtilities.dp(16.0f));
        this.paintView.getBottomLayout().setAlpha(0.0f);
        this.paintView.getBottomLayout().setTranslationY(AndroidUtilities.dp(48.0f));
        this.paintView.getWeightChooserView().setTranslationX(-AndroidUtilities.dp(32.0f));
        this.paintView.setVisibility(8);
    }

    private boolean inCheck() {
        if (this.collageLayoutView.hasLayout() == false) goto L5;
        float r0 = this.collageLayoutView.getFilledProgress();
    L7:
        if (this.animatedRecording == false) goto L9;
        return false;
    L9:
        if (r0 < 1.0f) goto L14;
        return true;
    L14:
        return false;
    L5:
        r0 = 0.0f;
        goto L7
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void initViews() {
        Context r2 = getContext();
        WindowView r0 = new WindowView(this, r2);
        this.windowView = r0;
        ViewCompat.setOnApplyWindowInsetsListener(r0, new StoryRecorder$$ExternalSyntheticLambda18(this));
        this.windowView.setFocusable(true);
        this.windowView.setImportantForAccessibility(2);
        FlashViews r02 = new FlashViews(r2, this.windowManager, this.windowView, this.windowLayoutParams);
        this.flashViews = r02;
        r02.add(new AnonymousClass2(this));
        this.windowView.addView(this.flashViews.backgroundView, new ViewGroup.LayoutParams(-1, -1));
        WindowView r03 = this.windowView;
        ContainerView r3 = new ContainerView(this, r2);
        this.containerView = r3;
        r03.addView(r3);
        ContainerView r04 = this.containerView;
        AnonymousClass3 r32 = new AnonymousClass3(this, r2);
        this.previewContainer = r32;
        r04.addView(r32);
        this.containerView.addView(this.flashViews.foregroundView, new ViewGroup.LayoutParams(-1, -1));
        this.blurManager = new BlurringShader.BlurManager(this.previewContainer);
        this.videoTextureHolder = new PreviewView.TextureViewHolder();
        ContainerView r05 = this.containerView;
        FrameLayout r33 = new FrameLayout(r2);
        this.actionBarContainer = r33;
        r05.addView(r33);
        ContainerView r06 = this.containerView;
        FrameLayout r34 = new FrameLayout(r2);
        this.controlContainer = r34;
        r06.addView(r34);
        ContainerView r07 = this.containerView;
        AnonymousClass4 r35 = new AnonymousClass4(this, r2);
        this.captionContainer = r35;
        r07.addView(r35);
        this.captionContainer.setVisibility(8);
        this.captionContainer.setAlpha(0.0f);
        ContainerView r08 = this.containerView;
        FrameLayout r36 = new FrameLayout(r2);
        this.navbarContainer = r36;
        r08.addView(r36);
        Bulletin.addDelegate(this.windowView, new AnonymousClass5(this));
        AnonymousClass6 r09 = new AnonymousClass6(this, r2, this.blurManager, this.containerView, this.resourcesProvider);
        this.collageLayoutView = r09;
        WindowView r37 = this.windowView;
        Objects.requireNonNull(r37);
        r09.setCancelGestures(new StoryRecorder$$ExternalSyntheticLambda29(r37));
        this.collageLayoutView.setResetState(new StoryRecorder$$ExternalSyntheticLambda40(this));
        this.previewContainer.addView(this.collageLayoutView, LayoutHelper.createFrame(-1, -1, 119));
        this.collageLayoutView.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda48(this));
        FrameLayout r010 = this.previewContainer;
        int r38 = this.openType;
        if (r38 == 1) goto L7;
        if (r38 == 0) goto L7;
        int r39 = -14737633;
    L8:
        r010.setBackgroundColor(r39);
        this.previewContainer.setOutlineProvider(ViewOutlineProviderImpl.boundsWithPaddingRoundRect(0, AndroidUtilities.dp(12.0f)));
        this.previewContainer.setClipToOutline(true);
        this.photoFilterEnhanceView = new PhotoFilterView.EnhanceView(r2, new StoryRecorder$$ExternalSyntheticLambda49(this));
        AnonymousClass7 r011 = new AnonymousClass7(this, r2, this.blurManager, this.videoTextureHolder);
        this.previewView = r011;
        r011.setCollageView(this.collageLayoutView);
        this.previewView.invalidateBlur = new StoryRecorder$$ExternalSyntheticLambda50(this);
        this.previewView.setOnTapListener(new StoryRecorder$$ExternalSyntheticLambda51(this));
        this.previewView.setVisibility(8);
        this.previewView.whenError(new StoryRecorder$$ExternalSyntheticLambda52(this));
        this.previewContainer.addView(this.previewView, LayoutHelper.createFrame(-1, -1, 119));
        this.previewContainer.addView(this.photoFilterEnhanceView, LayoutHelper.createFrame(-1, -1, 119));
        WindowView r310 = this.windowView;
        AnonymousClass8 r012 = new AnonymousClass8(this, r2, r310, r310, this.containerView, this.resourcesProvider, this.blurManager);
        this.captionEdit = r012;
        r012.setAccount(this.currentAccount);
        this.captionEdit.setUiBlurBitmap(new StoryRecorder$$ExternalSyntheticLambda53(this));
        Bulletin.addDelegate(this.captionContainer, new AnonymousClass9(this));
        this.captionEdit.setOnHeightUpdate(new StoryRecorder$$ExternalSyntheticLambda54(this));
        this.captionEdit.setOnPeriodUpdate(new StoryRecorder$$ExternalSyntheticLambda19(this));
        long r22 = this.selectedDialogId;
        if (r22 == 0) goto L11;
        this.captionEdit.setDialogId(r22);
    L11:
        this.captionEdit.setOnPremiumHint(new StoryRecorder$$ExternalSyntheticLambda20(this));
        this.captionEdit.setOnKeyboardOpen(new StoryRecorder$$ExternalSyntheticLambda21(this));
        AnonymousClass10 r013 = new AnonymousClass10(this, r2);
        this.captionEditOverlay = r013;
        this.containerView.addView(r013);
        TimelineView r014 = new TimelineView(r2, this.containerView, this.previewContainer, this.resourcesProvider, this.blurManager);
        this.timelineView = r014;
        r014.setOnTimelineClick(new StoryRecorder$$ExternalSyntheticLambda22(this));
        this.timelineView.setOnHeightChange(new StoryRecorder$$ExternalSyntheticLambda23(this));
        this.previewView.setVideoTimelineView(this.timelineView);
        this.timelineView.setVisibility(8);
        this.timelineView.setAlpha(0.0f);
        this.timelineView.setMaxCount(1);
        FrameLayout r015 = new FrameLayout(r2);
        this.videoTimelineContainerView = r015;
        r015.addView(this.timelineView, LayoutHelper.createFrame(-1, TimelineView.heightDp(), 87, 0.0f, 0.0f, 0.0f, 0.0f));
        VideoTimeView r016 = new VideoTimeView(r2);
        this.videoTimeView = r016;
        r016.setVisibility(8);
        this.videoTimeView.show(false, false);
        this.videoTimelineContainerView.addView(this.videoTimeView, LayoutHelper.createFrame(-1, 25.0f, 55, 0.0f, 0.0f, 0.0f, 0.0f));
        this.captionContainer.addView(this.videoTimelineContainerView, LayoutHelper.createFrame(-1, TimelineView.heightDp() + 25, 87, 0.0f, 0.0f, 0.0f, 68.0f));
        this.captionContainer.addView(this.captionEdit, LayoutHelper.createFrame(-1, -1.0f, 87, 0.0f, 200.0f, 0.0f, 0.0f));
        this.collageLayoutView.setTimelineView(this.timelineView);
        this.collageLayoutView.setPreviewView(this.previewView);
        TimelineView r017 = new TimelineView(r2, this.containerView, this.previewContainer, this.resourcesProvider, this.blurManager);
        this.coverTimelineView = r017;
        r017.setCover();
        this.coverTimelineView.setVisibility(8);
        this.coverTimelineView.setAlpha(0.0f);
        this.captionContainer.addView(this.coverTimelineView, LayoutHelper.createFrame(-1, TimelineView.heightDp(), 87, 0.0f, 0.0f, 0.0f, 6.0f));
        AnonymousClass11 r018 = new AnonymousClass11(this, r2, this.resourcesProvider, this.blurManager);
        this.storiesSelector = r018;
        r018.setVisibility(8);
        this.captionContainer.addView(this.storiesSelector, LayoutHelper.createFrame(-1, -2, 85));
        FlashViews.ImageViewInvertable r019 = new FlashViews.ImageViewInvertable(r2);
        this.backButton = r019;
        r019.setContentDescription(LocaleController.getString(R.string.AccDescrGoBack));
        FlashViews.ImageViewInvertable r020 = this.backButton;
        ImageView.ScaleType r7 = ImageView.ScaleType.CENTER;
        r020.setScaleType(r7);
        this.backButton.setImageResource(R.drawable.msg_photo_back);
        FlashViews.ImageViewInvertable r021 = this.backButton;
        PorterDuff.Mode r15 = PorterDuff.Mode.MULTIPLY;
        r021.setColorFilter(new PorterDuffColorFilter(-1, r15));
        this.backButton.setBackground(Theme.createSelectorDrawable(553648127));
        this.backButton.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda24(this));
        this.actionBarContainer.addView(this.backButton, LayoutHelper.createFrame(56, 56, 51));
        this.flashViews.add(this.backButton);
        SelectPeerView r022 = new SelectPeerView(r2, this.currentAccount);
        this.livePeerView = r022;
        r022.setShowing(false, false);
        this.livePeerView.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda25(this, r2));
        this.actionBarContainer.addView(this.livePeerView, LayoutHelper.createFrame(-1, 56.0f, 51, 50.0f, 0.0f, 50.0f, 0.0f));
        SimpleTextView r023 = new SimpleTextView(r2);
        this.titleTextView = r023;
        r023.setTextSize(20);
        this.titleTextView.setGravity(19);
        this.titleTextView.setTextColor(-1);
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        this.titleTextView.setText(LocaleController.getString(R.string.RecorderNewStory));
        this.titleTextView.getPaint().setShadowLayer(AndroidUtilities.dpf2(1.0f), 0.0f, 1.0f, TLObject.FLAG_30);
        this.titleTextView.setAlpha(0.0f);
        this.titleTextView.setVisibility(8);
        this.titleTextView.setEllipsizeByGradient(true);
        this.titleTextView.setRightPadding(AndroidUtilities.dp(144.0f));
        this.actionBarContainer.addView(this.titleTextView, LayoutHelper.createFrame(-1, 56.0f, 55, 71.0f, 0.0f, 0.0f, 0.0f));
        LinearLayout r024 = new LinearLayout(r2);
        this.actionBarButtons = r024;
        r024.setOrientation(0);
        this.actionBarButtons.setGravity(5);
        this.actionBarContainer.addView(this.actionBarButtons, LayoutHelper.createFrame(-1, 56.0f, 7, 0.0f, 0.0f, 8.0f, 0.0f));
        this.downloadButton = new DownloadButton(r2, new StoryRecorder$$ExternalSyntheticLambda26(this), this.currentAccount, this.windowView, this.resourcesProvider);
        HintView2 r025 = new HintView2(this.activity, 1).setJoint(1.0f, -71.0f).setDuration(2000).setBounce(false).setAnimatedTextHacks(true, true, false);
        this.muteHint = r025;
        r025.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.actionBarContainer.addView(this.muteHint, LayoutHelper.createFrame(-1, -1.0f, 48, 0.0f, 52.0f, 0.0f, 0.0f));
        RLottieImageView r026 = new RLottieImageView(r2);
        this.muteButton = r026;
        r026.setScaleType(r7);
        RLottieImageView r027 = this.muteButton;
        StoryEntry r311 = this.outputEntry;
        if (r311 != null) goto L14;
    L16:
        int r312 = R.drawable.media_mute;
    L17:
        r027.setImageResource(r312);
        this.muteButton.setColorFilter(new PorterDuffColorFilter(-1, r15));
        this.muteButton.setBackground(Theme.createSelectorDrawable(553648127));
        this.muteButton.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda27(this));
        this.muteButton.setVisibility(8);
        this.muteButton.setAlpha(0.0f);
        PlayPauseButton r028 = new PlayPauseButton(r2);
        this.playButton = r028;
        r028.setBackground(Theme.createSelectorDrawable(553648127));
        this.playButton.setVisibility(8);
        this.playButton.setAlpha(0.0f);
        this.playButton.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda28(this));
        this.actionBarButtons.addView(this.playButton, LayoutHelper.createLinear(46, 56, 53));
        this.actionBarButtons.addView(this.muteButton, LayoutHelper.createLinear(46, 56, 53));
        this.actionBarButtons.addView(this.downloadButton, LayoutHelper.createFrame(46, 56, 53));
        ToggleButton2 r029 = new ToggleButton2(r2);
        this.flashButton = r029;
        r029.setBackground(Theme.createSelectorDrawable(553648127));
        this.flashButton.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda30(this));
        this.flashButton.setOnLongClickListener(new StoryRecorder$$ExternalSyntheticLambda31(this));
        this.flashButton.setVisibility(8);
        this.flashButton.setAlpha(0.0f);
        this.flashViews.add(this.flashButton);
        this.actionBarContainer.addView(this.flashButton, LayoutHelper.createFrame(56, 56, 53));
        ToggleButton r030 = new ToggleButton(r2, R.drawable.media_dual_camera2_shadow, R.drawable.media_dual_camera2);
        this.dualButton = r030;
        r030.setContentDescription(LocaleController.getString(R.string.AccDescrDualCameraOn));
        this.dualButton.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda32(this));
        boolean r031 = DualCameraView.dualAvailableStatic(r2);
        ToggleButton r23 = this.dualButton;
        if (r031 == false) goto L20;
        int r313 = 0;
    L21:
        r23.setVisibility(r313);
        ToggleButton r24 = this.dualButton;
        if (r031 == false) goto L24;
        float r032 = 1.0f;
    L25:
        r24.setAlpha(r032);
        this.flashViews.add(this.dualButton);
        this.actionBarContainer.addView(this.dualButton, LayoutHelper.createFrame(56, 56, 53));
        CollageLayoutButton r033 = new CollageLayoutButton(r2);
        this.collageButton = r033;
        r033.setContentDescription(LocaleController.getString(R.string.AccDescrCollage));
        this.collageButton.setBackground(Theme.createSelectorDrawable(553648127));
        if (this.lastCollageLayout != null) goto L28;
        this.lastCollageLayout = CollageLayout.getLayouts().get(6);
    L28:
        this.collageButton.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda33(this));
        this.collageButton.setIcon(new CollageLayoutButton.CollageLayoutDrawable(this.lastCollageLayout), false);
        this.collageButton.setSelected(false);
        this.collageButton.setVisibility(0);
        this.collageButton.setAlpha(1.0f);
        this.flashViews.add(this.collageButton);
        this.actionBarContainer.addView(this.collageButton, LayoutHelper.createFrame(56, 56, 53));
        ToggleButton2 r034 = new ToggleButton2(r2);
        this.collageRemoveButton = r034;
        r034.setContentDescription(LocaleController.getString(R.string.AccDescrCollageClose));
        this.collageRemoveButton.setBackground(Theme.createSelectorDrawable(553648127));
        this.collageRemoveButton.setIcon(new CollageLayoutButton.CollageLayoutDrawable(new CollageLayout("../../.."), true), false);
        this.collageRemoveButton.setVisibility(8);
        this.collageRemoveButton.setAlpha(0.0f);
        this.collageRemoveButton.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda34(this));
        this.flashViews.add(this.collageRemoveButton);
        this.actionBarContainer.addView(this.collageRemoveButton, LayoutHelper.createFrame(56, 56, 53));
        CollageLayoutButton.CollageLayoutListView r035 = new CollageLayoutButton.CollageLayoutListView(r2, this.flashViews);
        this.collageListView = r035;
        r035.listView.scrollToPosition(6);
        this.collageListView.setSelected(null);
        this.collageListView.setOnLayoutClick(new StoryRecorder$$ExternalSyntheticLambda35(this));
        this.actionBarContainer.addView(this.collageListView, LayoutHelper.createFrame(-1, 56, 53));
        HintView2 r036 = new HintView2(this.activity, 1).setJoint(1.0f, -20.0f).setDuration(5000).setCloseButton(true).setText(LocaleController.getString(R.string.StoryCameraDualHint)).setOnHiddenListener(new StoryRecorder$$ExternalSyntheticLambda36());
        this.dualHint = r036;
        r036.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.actionBarContainer.addView(this.dualHint, LayoutHelper.createFrame(-1, -1.0f, 48, 0.0f, 52.0f, 0.0f, 0.0f));
        HintView2 r037 = new HintView2(this.activity, 2).setJoint(0.0f, 28.0f).setDuration(5000).setMultilineText(true);
        this.savedDualHint = r037;
        this.actionBarContainer.addView(r037, LayoutHelper.createFrame(-1, -1.0f, 48, 0.0f, 0.0f, 52.0f, 0.0f));
        HintView2 r038 = new HintView2(this.activity, 1).setJoint(1.0f, -20.0f).setDuration(5000).setText(LocaleController.getString(R.string.StoryCollageRemoveGrid));
        this.removeCollageHint = r038;
        r038.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.actionBarContainer.addView(this.removeCollageHint, LayoutHelper.createFrame(-1, -1.0f, 48, 0.0f, 52.0f, 0.0f, 0.0f));
        this.videoTimerView = new VideoTimerView(r2);
        showVideoTimer(false, false);
        this.actionBarContainer.addView(this.videoTimerView, LayoutHelper.createFrame(-1, 45.0f, 55, 56.0f, 0.0f, 56.0f, 0.0f));
        this.flashViews.add(this.videoTimerView);
        MediaController.loadGalleryPhotosAlbums(0);
        RecordControl r039 = new RecordControl(r2);
        this.recordControl = r039;
        r039.setDelegate(this.recordControlDelegate);
        RecordControl r040 = this.recordControl;
        if (this.mode != 1) goto L31;
        boolean r25 = true;
    L32:
        r040.startAsVideo(r25);
        this.controlContainer.addView(this.recordControl, LayoutHelper.createFrame(-1, 100, 87));
        this.flashViews.add(this.recordControl);
        RecordControl r041 = this.recordControl;
        if (this.collageLayoutView.hasLayout() == false) goto L35;
        float r26 = this.collageLayoutView.getFilledProgress();
    L36:
        r041.setCollageProgress(r26, true);
        ButtonWithCounterView r042 = new ButtonWithCounterView(r2, this.resourcesProvider);
        this.startLiveButton = r042;
        r042.setRoundRadius(24);
        this.startLiveButton.setColor(-2473124);
        this.startLiveButton.setText(LocaleController.getString(R.string.LiveStoryStart), false);
        this.startLiveButton.setAlpha(0.0f);
        this.startLiveButton.setScaleX(0.8f);
        this.startLiveButton.setScaleY(0.8f);
        this.startLiveButton.setVisibility(8);
        this.controlContainer.addView(this.startLiveButton, LayoutHelper.createFrame(-1, 48.0f, 87, 20.0f, 20.0f, 20.0f, 20.0f));
        this.startLiveButton.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda37(this));
        HintView2 r043 = new HintView2(this.activity, 3).setMultilineText(true).setText(LocaleController.getString(R.string.StoryCameraHint2)).setMaxWidth(320.0f).setDuration(5000).setTextAlign(Layout.Alignment.ALIGN_CENTER);
        this.cameraHint = r043;
        this.controlContainer.addView(r043, LayoutHelper.createFrame(-1, -1.0f, 80, 0.0f, 0.0f, 0.0f, 100.0f));
        ZoomControlView r044 = new ZoomControlView(r2);
        this.zoomControlView = r044;
        r044.enabledTouch = false;
        r044.setAlpha(0.0f);
        this.controlContainer.addView(this.zoomControlView, LayoutHelper.createFrame(-1, 50.0f, 81, 0.0f, 0.0f, 0.0f, 108.0f));
        this.zoomControlView.setDelegate(new StoryRecorder$$ExternalSyntheticLambda38(this));
        ZoomControlView r045 = this.zoomControlView;
        this.cameraZoom = 0.0f;
        r045.setZoom(0.0f, false);
        ScannedLinkPreview r046 = new ScannedLinkPreview(r2, this.currentAccount, new StoryRecorder$$ExternalSyntheticLambda39(this));
        this.qrLinkView = r046;
        r046.whenClicked(new StoryRecorder$$ExternalSyntheticLambda41(this));
        this.controlContainer.addView(this.qrLinkView, LayoutHelper.createFrame(-1, 80.0f, 87, 0.0f, 0.0f, 0.0f, 90.0f));
        AnonymousClass12 r047 = new AnonymousClass12(this, r2);
        this.modeSwitcherView = r047;
        r047.setOnSwitchModeListener(new StoryRecorder$$ExternalSyntheticLambda42(this));
        this.modeSwitcherView.setOnSwitchingModeListener(new StoryRecorder$$ExternalSyntheticLambda43(this));
        this.navbarContainer.addView(this.modeSwitcherView, LayoutHelper.createFrame(-1, 48, 55));
        this.flashViews.add(this.modeSwitcherView);
        FlashViews.ImageViewInvertable r048 = new FlashViews.ImageViewInvertable(r2);
        this.rotateButton = r048;
        r048.setContentDescription(LocaleController.getString(R.string.AccDescrSwitchCamera));
        this.rotateButton.setImageResource(R.drawable.stream_flip);
        this.rotateButton.setScaleType(r7);
        this.rotateButton.setColorFilter(new PorterDuffColorFilter(-1, r15));
        this.rotateButton.setBackground(Theme.createSelectorDrawable(553648127));
        this.navbarContainer.addView(this.rotateButton, LayoutHelper.createFrame(24, 24.0f, 19, 20.0f, 0.0f, 20.0f, 4.0f));
        this.flashViews.add(this.rotateButton);
        this.rotateButton.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda44(this));
        FlashViews.ImageViewInvertable r049 = new FlashViews.ImageViewInvertable(r2);
        this.liveSettingsButton = r049;
        r049.setContentDescription(LocaleController.getString(R.string.LiveStorySettings));
        this.liveSettingsButton.setImageResource(R.drawable.stream_settings);
        this.liveSettingsButton.setScaleType(r7);
        this.liveSettingsButton.setColorFilter(new PorterDuffColorFilter(-1, r15));
        this.liveSettingsButton.setBackground(Theme.createSelectorDrawable(553648127));
        this.navbarContainer.addView(this.liveSettingsButton, LayoutHelper.createFrame(24, 24.0f, 21, 20.0f, 0.0f, 20.0f, 4.0f));
        this.flashViews.add(this.liveSettingsButton);
        this.liveSettingsButton.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda45(this));
        HintTextView r050 = new HintTextView(r2);
        this.hintTextView = r050;
        this.navbarContainer.addView(r050, LayoutHelper.createFrame(-1, 32.0f, 17, 8.0f, 0.0f, 8.0f, 8.0f));
        this.flashViews.add(this.hintTextView);
        HintTextView r051 = new HintTextView(r2);
        this.collageHintTextView = r051;
        r051.setText(LocaleController.getString(R.string.StoryCollageReorderHint), false);
        this.collageHintTextView.setAlpha(0.0f);
        this.navbarContainer.addView(this.collageHintTextView, LayoutHelper.createFrame(-1, 32.0f, 17, 8.0f, 0.0f, 8.0f, 8.0f));
        this.flashViews.add(this.collageHintTextView);
        ButtonWithCounterView r052 = new ButtonWithCounterView(r2, this.resourcesProvider).setRound();
        this.coverButton = r052;
        r052.setVisibility(8);
        this.coverButton.setAlpha(0.0f);
        this.coverButton.setText(LocaleController.getString(R.string.StoryCoverSave), false);
        this.coverButton.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda46(this));
        this.navbarContainer.addView(this.coverButton, LayoutHelper.createFrame(-1, 48.0f, 119, 10.0f, 10.0f, 10.0f, 10.0f));
        PreviewButtons r053 = new PreviewButtons(r2);
        this.previewButtons = r053;
        r053.setVisibility(8);
        this.previewButtons.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda47(this));
        this.navbarContainer.addView(this.previewButtons, LayoutHelper.createFrame(-1, 52, 23));
        TrashView r054 = new TrashView(r2);
        this.trash = r054;
        r054.setAlpha(0.0f);
        this.trash.setVisibility(8);
        this.previewContainer.addView(this.trash, LayoutHelper.createFrame(-1, 120.0f, 81, 0.0f, 0.0f, 0.0f, 16.0f));
        PreviewHighlightView r055 = new PreviewHighlightView(r2, this.currentAccount, this.resourcesProvider);
        this.previewHighlight = r055;
        this.previewContainer.addView(r055, LayoutHelper.createFrame(-1, -1, 119));
        updateActionBarButtons(false);
        return;
    L35:
        r26 = 0.0f;
        goto L36
    L31:
        r25 = false;
        goto L32
    L24:
        r032 = 0.0f;
        goto L25
    L20:
        r313 = 8;
        goto L21
    L14:
        if (r311.muted == false) goto L16;
        r312 = R.drawable.media_unmute;
    L7:
        r39 = 0;
        goto L8
    }

    private boolean isBot() {
        StoryEntry r0 = this.outputEntry;
        if (r0 == null) goto L7;
        if (r0.botId == 0) goto L7;
        return true;
    L7:
        if (this.botId != 0) goto L12;
        return false;
    L12:
        return true;
    }

    private boolean isGalleryOpen() {
        if (this.scrollingY == true) goto L10;
        GalleryListView r0 = this.galleryListView;
        if (r0 != null) goto L7;
        return false;
    L7:
        if (r0.getTranslationY() >= ((this.windowView.getMeasuredHeight() - ((int) (AndroidUtilities.displaySize.y * 0.35f))) - (AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight()))) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public static boolean isVisible() {
        StoryRecorder r0 = instance;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isShown == false) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$animateContainerBack$70(float r2, float r3, ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        this.containerView.setTranslationY(r2 * r42);
        this.containerView.setTranslationY2(r3 * r42);
    }

    private /* synthetic */ void lambda$animateGalleryListView$75(boolean r1) {
        animateGalleryListView(r1);
    }

    private /* synthetic */ void lambda$animateGalleryListView$76(float r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        if (r3 == false) goto L4;
        return;
    L4:
        this.galleryListView.setTranslationY(r1);
        this.galleryListView.ignoreScroll = false;
        this.galleryOpenCloseSpringAnimator = null;
        this.galleryListViewOpening = null;
    }

    private /* synthetic */ void lambda$animateGalleryListView$77(ValueAnimator r1) {
        this.galleryListView.setTranslationY(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$animateOpenTo$3(ValueAnimator r1) {
        this.openProgress = ((Float) r1.getAnimatedValue()).floatValue();
        checkBackgroundVisibility();
        this.containerView.invalidate();
        this.windowView.invalidate();
    }

    private /* synthetic */ void lambda$applyPaintInBackground$89(PaintView r33, int r34, int r35, StoryEntry r36, boolean r37, boolean r38, Runnable r39) {
        ArrayList<VideoEditedInfo.MediaEntity> r3 = new ArrayList();
        r33.getBitmap(r3, r34, r35, false, false, false, false, r36);
        if (r36.isVideo == true) goto L5;
        r36.averageDuration = Utilities.clamp(r33.getLcm(), 7500, 5000);
    L5:
        List<TLRPC.InputDocument> r0 = r33.getMasks();
        if (r0 == null) goto L8;
        ArrayList r12 = new ArrayList(r0);
    L9:
        boolean r02 = r36.isVideo;
        boolean r13 = r36.wouldBeVideo();
        r3.clear();
        int r14 = 1;
        Bitmap r15 = r33.getBitmap(r3, r34, r35, true, false, false, !r02, r36);
        if (r3.isEmpty() == false) goto L12;
        ArrayList<VideoEditedInfo.MediaEntity> r23 = null;
    L13:
        File r16 = FileLoader.getInstance(this.currentAccount).getPathToAttach(ImageLoader.scaleAndSaveImage(r15, Bitmap.CompressFormat.PNG, r36.resultWidth, r36.resultHeight, 87, false, 101, 101), true);
        if (r15 == null) goto L19;
        if (r15.isRecycled() == true) goto L19;
        r15.recycle();
    L19:
        if (r36.isRepostMessage == false) goto L44;
        long r6 = r36.backgroundWallpaperPeerId;
        if (r6 == Long.MIN_VALUE) goto L44;
        Drawable r03 = r36.backgroundDrawable;
        if (r03 != null) goto L25;
        r03 = PreviewView.getBackgroundDrawable(null, this.currentAccount, r6, this.isDark);
    L25:
        if (r03 == null) goto L42;
        File r62 = StoryEntry.makeCacheFile(this.currentAccount, "webp");
        Bitmap r7 = Bitmap.createBitmap(r34, r35, Bitmap.Config.ARGB_8888);
        StoryEntry.drawBackgroundDrawable(new Canvas(r7), r03, r7.getWidth(), r7.getHeight());
        r7.compress(Bitmap.CompressFormat.WEBP, 100, new FileOutputStream(r62));     // Catch: Throwable -> L31 Exception -> L33
    L29:
        if (r7.isRecycled() == true) goto L43;
    L30:
        r7.recycle();
    L43:
        File r152 = r62;
    L46:
        if (r36.isRepostMessage == true) goto L48;
    L74:
        File r04 = null;
    L75:
        if (r13 == true) goto L82;
        int r4 = r34;
        int r5 = r35;
        Bitmap r24 = r33.getBitmap(new ArrayList(), r4, r5, false, true, false, false, r36);
        File r2 = FileLoader.getInstance(this.currentAccount).getPathToAttach(ImageLoader.scaleAndSaveImage(r24, Bitmap.CompressFormat.PNG, r4, r5, 87, false, 101, 101), true);
        if (r24 != null) goto L79;
    L81:
        File r63 = r2;
    L83:
        if (r37 == false) goto L91;
        Bitmap r242 = r33.getBlurBitmap();
        File r11 = FileLoader.getInstance(this.currentAccount).getPathToAttach(ImageLoader.scaleAndSaveImage(r242, Bitmap.CompressFormat.PNG, r4, r5, 87, false, 101, 101), true);
        if (r242 != null) goto L87;
    L89:
        File r8 = r11;
    L92:
        ArrayList<VideoEditedInfo.MediaEntity> r32 = r23;
        AndroidUtilities.runOnUIThread(new StoryRecorder$$ExternalSyntheticLambda113(r36, r38, r32, r16, r152, r63, r04, r8, r12, r39));
        return;
    L87:
        if (r242.isRecycled() == true) goto L89;
        r242.recycle();
        goto L89
    L91:
        r8 = null;
        goto L92
    L79:
        if (r24.isRecycled() == true) goto L81;
        r24.recycle();
        goto L81
    L82:
        r4 = r34;
        r5 = r35;
        r63 = null;
        goto L83
    L48:
        if (r36.isVideo == false) goto L74;
        int r05 = r36.width;
        int r64 = r36.height;
        MessageEntityView r72 = r33.findMessageView();
        if (r72 != null) goto L52;
    L58:
        ImageReceiver r73 = null;
    L59:
        if (r73 == null) goto L74;
        if (((int) r73.getImageWidth()) <= 0) goto L74;
        if (((int) r73.getImageHeight()) <= 0) goto L74;
        float r06 = r05;
        float r65 = r64;
        float r82 = Math.max(r73.getImageWidth() / r06, r73.getImageHeight() / r65);
        int r07 = (int) ((r06 * r82) / 2.0f);
        int r66 = (int) ((r65 * r82) / 2.0f);
        Bitmap r83 = Bitmap.createBitmap(r07, r66, Bitmap.Config.ARGB_8888);
        float[] r9 = new float[8];
        int r112 = 0;
    L66:
        if (r112 >= r73.getRoundRadius().length) goto L68;
        int r22 = r112 * 2;
        r9[r22] = r73.getRoundRadius()[r112];
        r9[r22 + 1] = r73.getRoundRadius()[r112];
        r112 = r112 + 1;
        r14 = r14;
        goto L66
    L68:
        int r21 = r14;
        Canvas r25 = new Canvas(r83);
        Path r113 = new Path();
        r25.scale(0.5f, 0.5f);
        RectF r142 = AndroidUtilities.rectTmp;
        float r08 = (r07 * 2.0f) / 2.0f;
        float r67 = (r66 * 2.0f) / 2.0f;
        r142.set(r08 - (r73.getImageWidth() / 2.0f), r67 - (r73.getImageHeight() / 2.0f), r08 + (r73.getImageWidth() / 2.0f), r67 + (r73.getImageHeight() / 2.0f));
        r113.addRoundRect(r142, r9, Path.Direction.CW);
        Paint r09 = new Paint(r21);
        r09.setColor(-1);
        r25.drawPath(r113, r09);
        r04 = StoryEntry.makeCacheFile(this.currentAccount, "webp");     // Catch: Exception -> L71
        r83.compress(Bitmap.CompressFormat.WEBP, 100, new FileOutputStream(r04));     // Catch: Exception -> L71
    L73:
        r83.recycle();
    L71:
        e = move-exception;
        FileLog.e(e);
        r04 = null;
        goto L73
    L52:
        if (r72.listView.getChildCount() != 1) goto L58;
        if (r05 <= 0) goto L58;
        if (r64 <= 0) goto L58;
        if ((r72.listView.getChildAt(0) instanceof ChatMessageCell) == false) goto L58;
        r73 = ((ChatMessageCell) r72.listView.getChildAt(0)).getPhotoImage();
    L33:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L31
        if (r7.isRecycled() == true) goto L43;
    L31:
        th = move-exception;
        if (r7.isRecycled() == true) goto L41;
        r7.recycle();
    L41:
        throw th;
    L42:
        r62 = null;
    L44:
        r152 = null;
        goto L46
    L12:
        r23 = r3;
        goto L13
    L8:
        r12 = null;
        goto L9
    }

    private /* synthetic */ void lambda$applyPaintSwitching$96(PaintView r25, int r26, int r27, StoryEntry r28, boolean r29, boolean r30, Runnable r31) {
        ArrayList r11 = new ArrayList();
        ArrayList<VideoEditedInfo.MediaEntity> r2 = new ArrayList();
        r25.getBitmap(r2, r26, r27, false, false, false, false, r28);
        if (r28.isVideo == true) goto L5;
        r28.averageDuration = Utilities.clamp(r25.getLcm(), 7500, 5000);
    L5:
        List<TLRPC.InputDocument> r1 = r25.getMasks();
        if (r1 == null) goto L8;
        ArrayList r12 = new ArrayList(r1);
    L9:
        boolean r13 = r28.isVideo;
        boolean r132 = r28.wouldBeVideo();
        r2.clear();
        Bitmap r5 = r25.getBitmap(r2, r26, r27, true, false, false, !r13, r28);
        if (r2.isEmpty() == false) goto L12;
        ArrayList<VideoEditedInfo.MediaEntity> r15 = null;
    L13:
        File r14 = StoryEntry.makeCacheFile(this.currentAccount, "png");
        r11.add(new StoryRecorder$$ExternalSyntheticLambda117(r5, r14));
        if (r28.isRepostMessage == false) goto L24;
        long r52 = r28.backgroundWallpaperPeerId;
        if (r52 == Long.MIN_VALUE) goto L24;
        Drawable r3 = r28.backgroundDrawable;
        if (r3 != null) goto L20;
        r3 = PreviewView.getBackgroundDrawable(null, this.currentAccount, r52, this.isDark);
    L20:
        if (r3 == null) goto L22;
        File r53 = StoryEntry.makeCacheFile(this.currentAccount, "webp");
        Bitmap r6 = Bitmap.createBitmap(r26, r27, Bitmap.Config.ARGB_8888);
        File r17 = r14;
        StoryEntry.drawBackgroundDrawable(new Canvas(r6), r3, r6.getWidth(), r6.getHeight());
        r11.add(new StoryRecorder$$ExternalSyntheticLambda118(r6, r53));
    L23:
        File r10 = r53;
    L26:
        if (r28.isRepostMessage == true) goto L28;
    L50:
        String r19 = "png";
        File r142 = null;
    L52:
        if (r25.hasChanges() == false) goto L60;
        if (r132 == true) goto L55;
        StoryEntry r9 = r28;
        String r133 = r19;
        Bitmap r22 = r25.getBitmap(new ArrayList(), r26, r27, false, true, false, false, r9);
        File r16 = StoryEntry.makeCacheFile(this.currentAccount, r133);
        r11.add(new StoryRecorder$$ExternalSyntheticLambda120(r22, r16));
    L56:
        if (r29 == false) goto L59;
        Bitmap r23 = r25.getBlurBitmap();
        File r0 = StoryEntry.makeCacheFile(this.currentAccount, r133);
        r11.add(new StoryRecorder$$ExternalSyntheticLambda121(r23, r0));
    L58:
        File r8 = r0;
        File r62 = r16;
    L61:
        ArrayList<VideoEditedInfo.MediaEntity> r32 = r15;
        File r4 = r17;
        AndroidUtilities.runOnUIThread(new StoryRecorder$$ExternalSyntheticLambda122(r9, r30, r32, r4, r10, r62, r142, r8, r12, r31));
        int r02 = r11.size();
        int r143 = 0;
    L62:
        if (r143 >= r02) goto L64;
        Object r18 = r11.get(r143);
        r143 = r143 + 1;
        ((Runnable) r18).run();
        goto L62
    L64:
        return;
    L59:
        r62 = r16;
        r8 = null;
        goto L61
    L55:
        r9 = r28;
        r133 = r19;
        r16 = null;
        goto L56
    L60:
        r9 = r28;
        r16 = r9.paintEntitiesFile;
        r0 = r9.paintBlurFile;
        goto L58
    L28:
        if (r28.isVideo == false) goto L50;
        int r110 = r28.width;
        int r33 = r28.height;
        MessageEntityView r54 = r25.findMessageView();
        if (r54 == null) goto L39;
        if (r54.listView.getChildCount() != 1) goto L39;
        if (r110 <= 0) goto L39;
        if (r33 <= 0) goto L39;
        if ((r54.listView.getChildAt(0) instanceof ChatMessageCell) == false) goto L39;
        ImageReceiver r55 = ((ChatMessageCell) r54.listView.getChildAt(0)).getPhotoImage();
    L40:
        if (r55 == null) goto L50;
        if (((int) r55.getImageWidth()) <= 0) goto L50;
        if (((int) r55.getImageHeight()) <= 0) goto L50;
        float r111 = r110;
        float r34 = r33;
        float r63 = Math.max(r55.getImageWidth() / r111, r55.getImageHeight() / r34);
        int r112 = (int) ((r111 * r63) / 2.0f);
        int r35 = (int) ((r34 * r63) / 2.0f);
        Bitmap r64 = Bitmap.createBitmap(r112, r35, Bitmap.Config.ARGB_8888);
        float[] r144 = new float[8];
        r19 = "png";
        ImageReceiver r20 = r55;
        int r24 = 0;
    L47:
        if (r24 >= r20.getRoundRadius().length) goto L49;
        int r56 = r24 * 2;
        r144[r56] = r20.getRoundRadius()[r22];
        r144[r56 + 1] = r20.getRoundRadius()[r22];
        r24 = r24 + 1;
        goto L47
    L49:
        Canvas r210 = new Canvas(r64);
        Path r57 = new Path();
        r210.scale(0.5f, 0.5f);
        RectF r7 = AndroidUtilities.rectTmp;
        float r113 = (r112 * 2.0f) / 2.0f;
        float r36 = (r35 * 2.0f) / 2.0f;
        r7.set(r113 - (r20.getImageWidth() / 2.0f), r36 - (r20.getImageHeight() / 2.0f), r113 + (r20.getImageWidth() / 2.0f), r36 + (r20.getImageHeight() / 2.0f));
        r57.addRoundRect(r7, r144, Path.Direction.CW);
        Paint r114 = new Paint(1);
        r114.setColor(-1);
        r210.drawPath(r57, r114);
        File r115 = StoryEntry.makeCacheFile(this.currentAccount, "webp");
        r11.add(new StoryRecorder$$ExternalSyntheticLambda119(r64, r115));
        r142 = r115;
    L39:
        r55 = null;
        goto L40
    L22:
        r17 = r14;
        r53 = null;
    L24:
        r17 = r14;
        r10 = null;
        goto L26
    L12:
        r15 = r2;
        goto L13
    L8:
        r12 = null;
        goto L9
    }

    private /* synthetic */ void lambda$close$2(boolean r2) {
        this.onClosePrepareListener = null;
        this.prepareClosing = false;
        close(r2);
    }

    private /* synthetic */ void lambda$createCameraView$100() {
        String r0 = getCurrentFlashMode();
        String r2 = null;
        if (TextUtils.equals(r0, getNextFlashMode()) == false) goto L6;
        r0 = null;
    L6:
        if (this.currentPage != 0) goto L8;
        r2 = r0;
    L8:
        setCameraFlashModeIcon(r2, true);
        ZoomControlView r1 = this.zoomControlView;
        if (r1 == null) goto L11;
        this.cameraZoom = 0.0f;
        r1.setZoom(0.0f, false);
    L11:
        updateActionBarButtons(true);
    }

    private /* synthetic */ void lambda$createCameraView$101(QRScanner.Detected r3) {
        if (this.qrScanner == null) goto L17;
        ScannedLinkPreview r0 = this.qrLinkView;
        QRScanner.Detected r1 = null;
        if (r3 != null) goto L8;
        String r32 = null;
    L9:
        r0.setLink(r32);
        CollageLayoutView2 r33 = this.collageLayoutView;
        if (r33 == null) goto L16;
        QRScanner.QrRegionDrawer r34 = r33.qrDrawer;
        if (this.qrLinkView.isResolved() == false) goto L14;
        r1 = this.qrScanner.getDetected();
    L14:
        r34.setQrDetected(r1);
        return;
    L16:
        return;
    L8:
        r32 = r3.link;
        goto L9
    }

    private /* synthetic */ Bitmap lambda$createFilterPhotoView$97(BitmapFactory.Options r1) {
        return BitmapFactory.decodeFile(this.outputEntry.file.getAbsolutePath(), r1);
    }

    private /* synthetic */ void lambda$createFilterPhotoView$98(View r2) {
        switchToEditMode(-1, true);
    }

    private /* synthetic */ void lambda$createFilterPhotoView$99(View r2) {
        switchToEditMode(-1, true);
    }

    private /* synthetic */ void lambda$createGalleryListView$72() {
        animateGalleryListView(false);
        this.lastGallerySelectedAlbum = null;
    }

    private /* synthetic */ void lambda$createGalleryListView$73(boolean r5, Object r6, Bitmap r7) {
        if (r6 != null) goto L4;
        return;
    L4:
        if (this.galleryListViewOpening == null) goto L6;
        return;
    L6:
        if (this.scrollingY == false) goto L8;
        return;
    L8:
        if (isGalleryOpen() == false) goto L64;
        int r0 = 0;
        if (r5 == true) goto L13;
        showVideoTimer(false, true);
        this.modeSwitcherView.switchMode(this.mode);
        RecordControl r52 = this.recordControl;
        if (this.mode != 1) goto L25;
        boolean r2 = true;
    L26:
        r52.startAsVideo(r2);
        animateGalleryListView(false);
        boolean r53 = r6 instanceof MediaController.PhotoEntry;
        if (r53 == false) goto L44;
        MediaController.PhotoEntry r62 = (MediaController.PhotoEntry) r6;
        if (r62.isVideo == true) goto L31;
    L33:
        this.mode = r0;
        StoryEntry r63 = StoryEntry.fromPhotoEntry(r62);
        r63.blurredVideoThumb = r7;
        r63.botId = this.botId;
        r63.botLang = this.botLang;
        r63.setupMatrix();
        this.fromGallery = true;
        if (this.collageLayoutView.hasLayout() == false) goto L39;
        this.outputFile = null;
        r63.videoVolume = 1.0f;
        if (this.collageLayoutView.push(r63) == false) goto L38;
        this.outputEntry = StoryEntry.asCollage(this.collageLayoutView.getLayout(), this.collageLayoutView.getContent());
    L38:
        updateActionBarButtons(true);
    L55:
        GalleryListView r54 = this.galleryListView;
        if (r54 == null) goto L63;
        this.lastGalleryScrollPosition = r54.layoutManager.onSaveInstanceState();
        this.lastGallerySelectedAlbum = this.galleryListView.getSelectedAlbum();
        return;
    L63:
        return;
    L39:
        r63.setupMultipleStoriesSelector();
        this.outputEntry = r63;
        if (r53 == false) goto L42;
        StoryPrivacySelector.applySaved(this.currentAccount, r63);
    L42:
        navigateTo(1, true);
        goto L55
    L31:
        if (r62.isLivePhoto() == true) goto L33;
        r0 = 1;
        goto L33
    L44:
        if ((r6 instanceof StoryEntry) == false) goto L62;
        StoryEntry r64 = (StoryEntry) r6;
        if (r64.file == null) goto L48;
    L51:
        r64.botId = this.botId;
        r64.botLang = this.botLang;
        this.mode = r64.isVideo ? 1 : 0;
        r64.blurredVideoThumb = r7;
        this.fromGallery = false;
        this.collageLayoutView.set(r64, true);
        this.outputEntry = r64;
        if (r53 == false) goto L54;
        StoryPrivacySelector.applySaved(this.currentAccount, r64);
    L54:
        navigateTo(1, true);
        goto L55
    L48:
        if (r64.isCollage() == true) goto L51;
        this.downloadButton.showToast(R.raw.error, "Failed to load draft");
        MessagesController.getInstance(this.currentAccount).getStoriesController().getDraftsController().delete(r64);
        return;
    L62:
        return;
    L25:
        r2 = false;
        goto L26
    L13:
        if (this.outputEntry == null) goto L65;
        createPhotoPaintView();
        this.outputEntry.editedMedia = true;
        if ((r6 instanceof MediaController.PhotoEntry) == false) goto L19;
        PaintView r55 = this.paintView;
        r55.appearAnimation(r55.createPhoto(((MediaController.PhotoEntry) r6).path, false));
    L21:
        animateGalleryListView(false);
        goto L55
    L19:
        if ((r6 instanceof TLObject) == false) goto L21;
        PaintView r56 = this.paintView;
        r56.appearAnimation(r56.createPhoto((TLObject) r6, false));
        goto L21
    L65:
        return;
    }

    private /* synthetic */ void lambda$createGalleryListView$74(Boolean r8, ArrayList r9, ArrayList r10) {
        if (this.currentPage != 0) goto L93;
        if (r9 != null) goto L7;
        return;
    L7:
        if (r9.isEmpty() == false) goto L9;
        return;
    L9:
        if (this.galleryListViewOpening == null) goto L11;
        return;
    L11:
        if (this.scrollingY == false) goto L13;
        return;
    L13:
        if (isGalleryOpen() == false) goto L94;
        this.entries = null;
        this.selectedEntries = null;
        this.selectedEntriesOrder = null;
        if (r8.booleanValue() == true) goto L18;
    L37:
        this.fromGallery = true;
        int r1 = 0;
    L39:
        if (r1 >= r9.size()) goto L57;
        StoryEntry r4 = StoryEntry.fromPhotoEntry((MediaController.PhotoEntry) r9.get(r1));
        r4.blurredVideoThumb = (Bitmap) r10.get(r1);
        r4.botId = this.botId;
        r4.botLang = this.botLang;
        r4.setupMatrix();
        if (r8.booleanValue() == false) goto L46;
        if (this.collageLayoutView.push(r4) == false) goto L55;
        this.outputEntry = StoryEntry.asCollage(this.collageLayoutView.getLayout(), this.collageLayoutView.getContent());
    L55:
        r1 = r1 + 1;
        goto L39
    L46:
        if (this.outputEntry != null) goto L49;
        this.outputEntry = r4;
        goto L55
    L49:
        if (this.entries != null) goto L52;
        ArrayList<StoryEntry> r5 = new ArrayList();
        this.entries = r5;
        r5.add(this.outputEntry);
    L52:
        if (this.entries.size() >= 10) goto L57;
        this.entries.add(r4);
    L57:
        if (this.entries == null) goto L73;
        showVideoTimer(false, true);
        this.modeSwitcherView.switchMode(this.mode);
        RecordControl r82 = this.recordControl;
        if (this.mode != 1) goto L61;
        boolean r92 = true;
    L62:
        r82.startAsVideo(r92);
        this.selectedEntries = new ArrayList();
        this.selectedEntriesOrder = new ArrayList();
        int r83 = 0;
    L64:
        if (r83 >= this.entries.size()) goto L66;
        this.selectedEntries.add(Integer.valueOf(r83));
        this.selectedEntriesOrder.add(Integer.valueOf(r83));
        r83 = r83 + 1;
        goto L64
    L66:
        this.collageLayoutView.set(null, true);
        this.collageListView.setVisible(false, true);
        updateActionBarButtons(true);
        animateGalleryListView(false);
        navigateTo(1, true);
        MultipleStoriesSelector r84 = this.storiesSelector;
        if (r84 == null) goto L69;
        r84.showHint();
    L69:
        GalleryListView r85 = this.galleryListView;
        if (r85 == null) goto L91;
        this.lastGalleryScrollPosition = r85.layoutManager.onSaveInstanceState();
        this.lastGallerySelectedAlbum = this.galleryListView.getSelectedAlbum();
        return;
    L91:
        return;
    L61:
        r92 = false;
        goto L62
    L73:
        StoryEntry r86 = this.outputEntry;
        if (r86 == null) goto L76;
        r86.setupMultipleStoriesSelector();
    L76:
        this.collageListView.setVisible(false, true);
        updateActionBarButtons(true);
        animateGalleryListView(false);
        GalleryListView r87 = this.galleryListView;
        if (r87 == null) goto L92;
        this.lastGalleryScrollPosition = r87.layoutManager.onSaveInstanceState();
        this.lastGallerySelectedAlbum = this.galleryListView.getSelectedAlbum();
        return;
    L92:
        return;
    L18:
        if ((this.collageLayoutView.getFilledCount() + r9.size()) <= this.collageLayoutView.getTotalCount()) goto L37;
        CollageLayout r12 = CollageLayout.of(this.collageLayoutView.getFilledCount() + r9.size());
        CollageLayoutView2 r42 = this.collageLayoutView;
        if (r12 != null) goto L26;
        r42.setLayout(null, true);
        this.collageLayoutView.clear(true);
        this.collageListView.setSelected(null);
        DualCameraView r88 = this.cameraView;
        if (r88 == null) goto L24;
        r88.recordHevc = !this.collageLayoutView.hasLayout();
    L24:
        this.collageListView.setVisible(false, true);
        updateActionBarButtons(true);
        return;
    L26:
        this.lastCollageLayout = r12;
        r42.setLayout(r12, true);
        this.collageListView.setSelected(r12);
        int r43 = CollageLayout.getLayouts().indexOf(r12);
        if (r43 < 0) goto L29;
        this.collageListView.listView.scrollToPosition(r43);
    L29:
        DualCameraView r44 = this.cameraView;
        if (r44 == null) goto L32;
        r44.recordHevc = !this.collageLayoutView.hasLayout();
    L32:
        this.collageButton.setDrawable(new CollageLayoutButton.CollageLayoutDrawable(r12));
        setActionBarButtonVisible(this.collageRemoveButton, this.collageListView.isVisible(), true);
        RecordControl r13 = this.recordControl;
        if (this.collageLayoutView.hasLayout() == false) goto L35;
        float r45 = this.collageLayoutView.getFilledProgress();
    L36:
        r13.setCollageProgress(r45, true);
        goto L37
    L35:
        r45 = 0.0f;
        goto L36
    L94:
        return;
    }

    private /* synthetic */ void lambda$createPhotoPaintView$86() {
        switchToEditMode(-1, true);
    }

    private /* synthetic */ void lambda$createPhotoPaintView$87() {
        switchToEditMode(-1, true);
    }

    private /* synthetic */ void lambda$destroyCameraView$105() {
        this.collageLayoutView.setCameraThumb(getCameraThumb());
        DualCameraView r0 = this.cameraView;
        if (r0 == null) goto L9;
        r0.destroy(true, null);
        AndroidUtilities.removeFromParent(this.cameraView);
        CollageLayoutView2 r02 = this.collageLayoutView;
        if (r02 == null) goto L7;
        r02.setCameraView(null);
    L7:
        this.cameraView = null;
        return;
    }

    private /* synthetic */ void lambda$destroyCameraView$106() {
        this.collageLayoutView.setCameraThumb(getCameraThumb());
    }

    private /* synthetic */ void lambda$getThanosEffect$114() {
        ThanosEffect r0 = this.thanosEffect;
        if (r0 == null) goto L6;
        this.thanosEffect = null;
        this.windowView.removeView(r0);
        return;
    }

    private /* synthetic */ void lambda$getThemeButton$115(View r1) {
        toggleTheme();
    }

    private /* synthetic */ void lambda$initViews$10(Integer r6) {
        FrameLayout r62 = this.videoTimelineContainerView;
        if (r62 != null) goto L5;
    L10:
        MultipleStoriesSelector r63 = this.storiesSelector;
        if (r63 == null) goto L17;
        int r2 = -(this.captionEdit.getEditTextHeight() + AndroidUtilities.dp(24.0f));
        TimelineView r3 = this.timelineView;
        if (r3 != null) goto L15;
        int r32 = 0;
    L16:
        r63.setTranslationY(r2 - r32);
        goto L17
    L15:
        r32 = r3.getContentHeight() - AndroidUtilities.dp(5.0f);
    L17:
        Bulletin r64 = Bulletin.getVisibleBulletin();
        if (r64 == null) goto L23;
        if (r64.tag != 2) goto L23;
        r64.updatePosition();
    L23:
        if (this.captionEdit.keyboardShown == false) goto L28;
        MultipleStoriesSelector r5 = this.storiesSelector;
        if (r5 == null) goto L29;
        r5.showList(false, true);
        return;
    L29:
        return;
    L28:
        return;
    L5:
        if (this.currentEditMode != 2) goto L8;
        int r1 = AndroidUtilities.dp(68.0f);
    L9:
        r62.setTranslationY(r1);
        goto L10
    L8:
        r1 = (-(this.captionEdit.getEditTextHeight() + AndroidUtilities.dp(12.0f))) + AndroidUtilities.dp(64.0f);
        goto L9
    }

    private /* synthetic */ void lambda$initViews$11(Integer r2) {
        StoryEntry r1 = this.outputEntry;
        if (r1 == null) goto L6;
        r1.period = r2.intValue();
        MessagesController.getGlobalMainSettings().edit().putInt("story_period", r2.intValue()).apply();
        return;
    }

    private /* synthetic */ void lambda$initViews$12(Boolean r4) {
        if (r4.booleanValue() == false) goto L7;
        TimelineView r0 = this.timelineView;
        if (r0 == null) goto L7;
        r0.onBackPressed();
    L7:
        this.previewView.updatePauseReason(2, r4.booleanValue());
        this.videoTimelineContainerView.clearAnimation();
        ViewPropertyAnimator r3 = this.videoTimelineContainerView.animate();
        if (r4.booleanValue() == false) goto L10;
        float r42 = 0.0f;
    L11:
        r3.alpha(r42).setDuration(120).start();
        Bulletin r32 = Bulletin.getVisibleBulletin();
        if (r32 != null) goto L14;
        return;
    L14:
        if (r32.tag != 2) goto L18;
        r32.updatePosition();
        return;
    L18:
        return;
    L10:
        r42 = 1.0f;
        goto L11
    }

    private /* synthetic */ void lambda$initViews$13() {
        if (this.currentPage == 1) goto L5;
        return;
    L5:
        switchToEditMode(2, true);
    }

    private /* synthetic */ void lambda$initViews$14() {
        MultipleStoriesSelector r0 = this.storiesSelector;
        if (r0 == null) goto L10;
        int r1 = -(this.captionEdit.getEditTextHeight() + AndroidUtilities.dp(24.0f));
        TimelineView r3 = this.timelineView;
        if (r3 != null) goto L7;
        int r32 = 0;
    L8:
        r0.setTranslationY(r1 - r32);
        return;
    L7:
        r32 = r3.getContentHeight() - AndroidUtilities.dp(5.0f);
        goto L8
    }

    private /* synthetic */ void lambda$initViews$15(View r1) {
        if (this.awaitingPlayer == false) goto L5;
        return;
    L5:
        onBackPressed();
    }

    private /* synthetic */ void lambda$initViews$16(TLRPC.InputPeer r2) {
        SelectPeerView r0 = this.livePeerView;
        this.livePeer = r2;
        r0.set(r2);
    }

    private /* synthetic */ void lambda$initViews$17(Context r8, View r9) {
        new StoryPrivacyBottomSheet.ChoosePeerSheet(r8, this.currentAccount, true, this.livePeer, new StoryRecorder$$ExternalSyntheticLambda91(this), this.resourcesProvider).show();
    }

    private /* synthetic */ void lambda$initViews$18(Runnable r1) {
        applyPaint();
        applyPaintMessage();
        applyFilter(r1);
    }

    private /* synthetic */ void lambda$initViews$19(View r7) {
        StoryEntry r72 = this.outputEntry;
        if (r72 != null) goto L5;
        return;
    L5:
        if (this.awaitingPlayer == true) goto L36;
        r72.muted = !r72.muted;
        ArrayList<StoryEntry> r73 = r72.collageContent;
        boolean r0 = false;
        if (r73 == null) goto L12;
        int r2 = r73.size();
        int r3 = 0;
    L10:
        if (r3 >= r2) goto L12;
        StoryEntry r4 = r73.get(r3);
        r3 = r3 + 1;
        r4.muted = this.outputEntry.muted;
    L12:
        boolean r74 = TextUtils.isEmpty(this.outputEntry.audioPath);
        StoryEntry r22 = this.outputEntry;
        if (r22.round == null) goto L16;
        r0 = true;
    L16:
        if (this.currentEditMode != (-1)) goto L32;
        HintView2 r32 = this.muteHint;
        if (r22.muted == false) goto L25;
        if (r74 == false) goto L23;
        if (r0 == true) goto L23;
        int r75 = R.string.StorySoundMuted;
    L24:
        String r76 = LocaleController.getString(r75);
    L31:
        r32.setText(r76, this.muteHint.shown());
        this.muteHint.show();
    L23:
        r75 = R.string.StoryOriginalSoundMuted;
        goto L24
    L25:
        if (r74 == false) goto L29;
        if (r0 == true) goto L29;
        int r77 = R.string.StorySoundNotMuted;
    L30:
        r76 = LocaleController.getString(r77);
    L29:
        r77 = R.string.StoryOriginalSoundNotMuted;
    L32:
        setIconMuted(this.outputEntry.muted, true);
        this.previewView.checkVolumes();
        return;
    }

    private /* synthetic */ void lambda$initViews$20(View r3) {
        boolean r32 = this.previewView.isPlaying();
        this.previewView.play(!r32);
        this.playButton.drawable.setPause(!r32, true);
    }

    private /* synthetic */ void lambda$initViews$21(View r2) {
        if (this.cameraView != null) goto L5;
        return;
    L5:
        if (this.awaitingPlayer == true) goto L16;
        String r22 = getCurrentFlashMode();
        String r0 = getNextFlashMode();
        if (r22 != null) goto L10;
        return;
    L10:
        if (r22.equals(r0) == true) goto L17;
        setCurrentFlashMode(r0);
        setCameraFlashModeIcon(r0, true);
        return;
    L17:
        return;
    }

    private /* synthetic */ void lambda$initViews$22(Float r1) {
        this.flashViews.setWarmth(r1.floatValue());
    }

    private /* synthetic */ void lambda$initViews$23(Float r1) {
        this.flashViews.setIntensity(r1.floatValue());
    }

    private /* synthetic */ void lambda$initViews$24() {
        saveFrontFaceFlashMode();
        this.flashViews.previewEnd();
        this.flashButton.setSelected(false);
    }

    private /* synthetic */ boolean lambda$initViews$25(View r6) {
        DualCameraView r62 = this.cameraView;
        if (r62 != null) goto L5;
    L9:
        return false;
    L5:
        if (r62.isFrontface() == false) goto L9;
        checkFrontfaceFlashModes();
        this.flashButton.setSelected(true);
        this.flashViews.previewStart();
        ItemOptions.makeOptions(this.containerView, this.resourcesProvider, this.flashButton).addView(new SliderView(getContext(), 1).setValue(this.flashViews.warmth).setOnValueChange(new StoryRecorder$$ExternalSyntheticLambda95(this))).addSpaceGap().addView(new SliderView(getContext(), 2).setMinMax(0.65f, 1.0f).setValue(this.flashViews.intensity).setOnValueChange(new StoryRecorder$$ExternalSyntheticLambda96(this))).setOnDismiss(new StoryRecorder$$ExternalSyntheticLambda97(this)).setDimAlpha(0).setGravity(5).translate(AndroidUtilities.dp(46.0f), -AndroidUtilities.dp(4.0f)).setBackgroundColor(-1155851493).show();
        return true;
    }

    private /* synthetic */ void lambda$initViews$26(View r3) {
        DualCameraView r32 = this.cameraView;
        if (r32 != null) goto L5;
        return;
    L5:
        if (this.currentPage != 0) goto L17;
        r32.toggleDual();
        this.dualButton.setValue(this.cameraView.isDual());
        ToggleButton r33 = this.dualButton;
        if (this.cameraView.isDual() == false) goto L10;
        int r0 = R.string.AccDescrDualCameraOn;
    L11:
        r33.setContentDescription(LocaleController.getString(r0));
        this.dualHint.hide();
        MessagesController.getGlobalMainSettings().edit().putInt("storydualhint", 2).apply();
        if (this.savedDualHint.shown() == false) goto L14;
        MessagesController.getGlobalMainSettings().edit().putInt("storysvddualhint", 2).apply();
    L14:
        this.savedDualHint.hide();
        return;
    L10:
        r0 = R.string.AccDescrDualCameraOff;
        goto L11
    }

    private /* synthetic */ void lambda$initViews$27(View r4) {
        if (this.currentPage == 0) goto L5;
        return;
    L5:
        if (this.animatedRecording == true) goto L25;
        DualCameraView r42 = this.cameraView;
        if (r42 == null) goto L13;
        if (r42.isDual() == false) goto L13;
        this.cameraView.toggleDual();
    L13:
        if (this.collageListView.isVisible() == false) goto L15;
    L22:
        this.collageListView.setVisible(!r4.isVisible(), true);
        updateActionBarButtons(true);
        return;
    L15:
        if (this.collageLayoutView.hasLayout() == true) goto L22;
        this.collageLayoutView.setLayout(this.lastCollageLayout, true);
        this.collageListView.setSelected(this.lastCollageLayout);
        this.collageButton.setIcon(new CollageLayoutButton.CollageLayoutDrawable(this.lastCollageLayout), true);
        this.collageButton.setSelected(true);
        DualCameraView r43 = this.cameraView;
        if (r43 == null) goto L19;
        r43.recordHevc = !this.collageLayoutView.hasLayout();
    L19:
        GalleryListView r44 = this.galleryListView;
        if (r44 == null) goto L22;
        r44.setMultipleOnClick(this.collageLayoutView.hasLayout());
        this.galleryListView.setMaxCount(Math.min(10, CollageLayout.getMaxCount() - this.collageLayoutView.getFilledCount()));
        goto L22
    }

    private /* synthetic */ void lambda$initViews$28(View r3) {
        this.collageLayoutView.setLayout(null, true);
        this.collageLayoutView.clear(true);
        this.collageListView.setSelected(null);
        DualCameraView r32 = this.cameraView;
        if (r32 == null) goto L5;
        r32.recordHevc = !this.collageLayoutView.hasLayout();
    L5:
        this.collageListView.setVisible(false, true);
        updateActionBarButtons(true);
        GalleryListView r33 = this.galleryListView;
        if (r33 == null) goto L9;
        r33.setMultipleOnClick(this.collageLayoutView.hasLayout());
        this.galleryListView.setMaxCount(Math.min(10, CollageLayout.getMaxCount() - this.collageLayoutView.getFilledCount()));
        return;
    }

    private /* synthetic */ void lambda$initViews$29(CollageLayout r4) {
        CollageLayoutView2 r0 = this.collageLayoutView;
        this.lastCollageLayout = r4;
        r0.setLayout(r4, true);
        this.collageListView.setSelected(r4);
        DualCameraView r02 = this.cameraView;
        if (r02 == null) goto L5;
        r02.recordHevc = !this.collageLayoutView.hasLayout();
    L5:
        this.collageButton.setDrawable(new CollageLayoutButton.CollageLayoutDrawable(r4));
        setActionBarButtonVisible(this.collageRemoveButton, this.collageListView.isVisible(), true);
        RecordControl r42 = this.recordControl;
        if (this.collageLayoutView.hasLayout() == false) goto L8;
        float r03 = this.collageLayoutView.getFilledProgress();
    L9:
        r42.setCollageProgress(r03, true);
        GalleryListView r43 = this.galleryListView;
        if (r43 == null) goto L13;
        r43.setMultipleOnClick(this.collageLayoutView.hasLayout());
        this.galleryListView.setMaxCount(Math.min(10, CollageLayout.getMaxCount() - this.collageLayoutView.getFilledCount()));
        return;
    L13:
        return;
    L8:
        r03 = 0.0f;
        goto L9
    }

    private /* synthetic */ void lambda$initViews$31(View r1) {
        startLive();
    }

    private /* synthetic */ void lambda$initViews$32(float r2) {
        DualCameraView r0 = this.cameraView;
        if (r0 == null) goto L5;
        this.cameraZoom = r2;
        r0.setZoom(r2);
    L5:
        showZoomControls(true, true);
    }

    private /* synthetic */ void lambda$initViews$33() {
        CollageLayoutView2 r0 = this.collageLayoutView;
        if (r0 == null) goto L10;
        QRScanner.QrRegionDrawer r02 = r0.qrDrawer;
        if (this.qrLinkView.isResolved() == false) goto L7;
        QRScanner.Detected r2 = this.qrScanner.getDetected();
    L8:
        r02.setQrDetected(r2);
        return;
    L7:
        r2 = null;
        goto L8
    }

    private /* synthetic */ void lambda$initViews$35(Utilities.Callback r3) {
        this.fastClose = true;
        close(true);
        AndroidUtilities.runOnUIThread(new StoryRecorder$$ExternalSyntheticLambda74(r3), 210);
    }

    private /* synthetic */ void lambda$initViews$36(Integer r6) {
        if (this.takingPhoto == false) goto L5;
        return;
    L5:
        if (this.takingVideo == true) goto L43;
        int r0 = r6.intValue();
        this.mode = r0;
        SelectPeerView r1 = this.livePeerView;
        if (r0 != (-1)) goto L10;
        boolean r02 = true;
    L11:
        r1.setShowing(r02, true);
        if (this.mode == 1) goto L14;
    L16:
        boolean r03 = false;
    L17:
        showVideoTimer(r03, true);
        this.modeSwitcherView.switchMode(r6.intValue());
        RecordControl r04 = this.recordControl;
        if (r6.intValue() != 1) goto L20;
        boolean r12 = true;
    L21:
        r04.startAsVideo(r12);
        if (r6.intValue() != (-1)) goto L40;
        DualCameraView r62 = this.cameraView;
        if (r62 != null) goto L26;
    L28:
        HintView2 r63 = this.dualHint;
        if (r63 == null) goto L31;
        r63.hide();
    L31:
        HintView2 r64 = this.savedDualHint;
        if (r64 == null) goto L34;
        r64.hide();
    L34:
        HintView2 r65 = this.cameraHint;
        if (r65 == null) goto L37;
        r65.hide();
    L37:
        this.collageLayoutView.setLayout(null, true);
        this.collageLayoutView.clear(true);
        this.collageListView.setSelected(null);
        DualCameraView r66 = this.cameraView;
        if (r66 == null) goto L40;
        r66.recordHevc = !this.collageLayoutView.hasLayout();
        goto L40
    L26:
        if (r62.isDual() == false) goto L28;
        this.cameraView.toggleDual();
    L40:
        this.collageListView.setVisible(false, true);
        updateActionBarButtons(true);
        return;
    L20:
        r12 = false;
        goto L21
    L14:
        if (this.collageListView.isVisible() == true) goto L16;
        r03 = true;
        goto L17
    L10:
        r02 = false;
        goto L11
    }

    private /* synthetic */ void lambda$initViews$37(Float r8) {
        this.recordControl.startAsVideoT(r8.floatValue());
        RecordControl r0 = this.recordControl;
        int r3 = 8;
        if (r8.floatValue() > (-1.0f)) goto L5;
        int r1 = 8;
    L6:
        r0.setVisibility(r1);
        this.recordControl.setAlpha(Utilities.clamp01(r8.floatValue() + 1.0f));
        ButtonWithCounterView r02 = this.startLiveButton;
        if (r8.floatValue() >= 0.0f) goto L9;
        r3 = 0;
    L9:
        r02.setVisibility(r3);
        this.startLiveButton.setAlpha(AndroidUtilities.ilerp(r8.floatValue(), 0.0f, -1.0f));
        this.startLiveButton.setScaleX(AndroidUtilities.lerp(0.8f, 1.0f, AndroidUtilities.ilerp(r8.floatValue(), 0.0f, -1.0f)));
        this.startLiveButton.setScaleY(AndroidUtilities.lerp(0.8f, 1.0f, AndroidUtilities.ilerp(r8.floatValue(), 0.0f, -1.0f)));
        this.startLiveButton.setTranslationY(AndroidUtilities.lerp(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.ilerp(r8.floatValue(), 0.0f, -1.0f)));
        if (r8.floatValue() >= 0.0f) goto L13;
        animateGalleryListView(false);
        return;
    L13:
        return;
    L5:
        r1 = 0;
        goto L6
    }

    private /* synthetic */ void lambda$initViews$38(View r2) {
        DualCameraView r22 = this.cameraView;
        if (r22 != null) goto L5;
        return;
    L5:
        if (this.awaitingPlayer == false) goto L7;
        return;
    L7:
        if (this.takingPhoto == false) goto L9;
        return;
    L9:
        if (r22.isInited() == true) goto L11;
        return;
    L11:
        if (this.currentPage != 0) goto L23;
        this.cameraView.switchCamera();
        this.recordControl.rotateFlip(180.0f);
        saveCameraFace(this.cameraView.isFrontface());
        boolean r23 = useDisplayFlashlight();
        FlashViews r1 = this.flashViews;
        if (r23 == false) goto L17;
        r1.flashIn(null);
        return;
    L17:
        r1.flashOut();
        return;
    }

    private /* synthetic */ void lambda$initViews$39(View r1) {
        startLive();
    }

    private /* synthetic */ void lambda$initViews$40(Bitmap r2) {
        StoryEntry r0 = this.outputEntry;
        if (r0 == null) goto L10;
        AndroidUtilities.recycleBitmap(r0.coverBitmap);
        this.outputEntry.coverBitmap = r2;
        StoryPrivacyBottomSheet r1 = this.privacySheet;
        if (r1 != null) goto L8;
        return;
    L8:
        r1.setCover(r2);
        return;
    }

    private /* synthetic */ void lambda$initViews$41() {
        if (this.outputEntry.isEditingCover == false) goto L5;
    L9:
        navigateTo(1, true);
        return;
    L5:
        if (this.privacySheet == null) goto L9;
        PreviewView r0 = this.previewView;
        if (r0 == null) goto L9;
        r0.getCoverBitmap(new StoryRecorder$$ExternalSyntheticLambda103(this), new View[]{this.previewView, this.paintViewRenderView, this.paintViewEntitiesView});
        goto L9
    }

    private /* synthetic */ void lambda$initViews$42(View r3) {
        StoryEntry r32 = this.outputEntry;
        if (r32 == null) goto L13;
        r32.coverSet = true;
        r32.cover = this.coverValue;
        processDone();
        StoryEntry r33 = this.outputEntry;
        if (r33 != null) goto L8;
        return;
    L8:
        if (r33.isEditingCover == true) goto L12;
        AndroidUtilities.runOnUIThread(new StoryRecorder$$ExternalSyntheticLambda80(this), 400);
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$initViews$43(Integer r4) {
        if (this.outputEntry != null) goto L5;
        return;
    L5:
        if (this.captionEdit.isRecording() == true) goto L42;
        this.captionEdit.clearFocus();
        if (r4.intValue() != 5) goto L12;
        processDone();
        return;
    L12:
        if (r4.intValue() != 0) goto L18;
        switchToEditMode(0, true);
        PaintView r3 = this.paintView;
        if (r3 == null) goto L38;
        r3.enteredThroughText = false;
        r3.openPaint();
        return;
    L38:
        return;
    L18:
        if (r4.intValue() != 1) goto L24;
        switchToEditMode(0, true);
        PaintView r42 = this.paintView;
        if (r42 == null) goto L39;
        r42.openText();
        this.paintView.enteredThroughText = true;
        return;
    L39:
        return;
    L24:
        if (r4.intValue() != 2) goto L30;
        createPhotoPaintView();
        hidePhotoPaintView();
        PaintView r32 = this.paintView;
        if (r32 == null) goto L40;
        r32.openStickers();
        return;
    L40:
        return;
    L30:
        if (r4.intValue() != 4) goto L34;
        switchToEditMode(1, true);
        return;
    L34:
        if (r4.intValue() != 3) goto L41;
        switchToEditMode(3, true);
        return;
    L41:
        return;
    }

    private /* synthetic */ WindowInsetsCompat lambda$initViews$5(View r1, WindowInsetsCompat r2) {
        Insets r12 = AndroidUtilities.getDefaultWindowInsets(r2, false);
        this.insetLeft = r12.left;
        this.insetTop = r12.top;
        this.insetRight = r12.right;
        this.insetBottom = r12.bottom;
        this.windowView.requestLayout();
        return WindowInsetsCompat.CONSUMED;
    }

    private /* synthetic */ void lambda$initViews$6() {
        updateActionBarButtons(true);
    }

    private /* synthetic */ void lambda$initViews$7(View r1) {
        if (this.noCameraPermission == false) goto L6;
        requestCameraPermission(true);
        return;
    }

    private /* synthetic */ void lambda$initViews$8() {
        if (this.currentEditMode == (-1)) goto L5;
        return;
    L5:
        if (this.currentPage != 1) goto L23;
        CaptionStory r0 = this.captionEdit;
        if (r0.keyboardShown == true) goto L24;
        if (r0 == null) goto L13;
        if (r0.isRecording() == false) goto L13;
        return;
    L13:
        if (this.timelineView.onBackPressed() == false) goto L16;
        return;
    L16:
        if (this.storiesSelector.onBackPressed() == true) goto L28;
        switchToEditMode(0, true);
        PaintView r02 = this.paintView;
        if (r02 == null) goto L25;
        r02.openText();
        this.paintView.enteredThroughText = true;
        return;
    L25:
        return;
    L28:
        return;
    L24:
        return;
    }

    private /* synthetic */ void lambda$initViews$9() {
        this.videoError = true;
        this.previewButtons.setShareEnabled(false);
        this.downloadButton.showFailedVideo();
    }

    private /* synthetic */ void lambda$navigateToPreviewWithPlayerAwait$69(Runnable r3) {
        animateGalleryListView(false);
        AndroidUtilities.cancelRunOnUIThread(this.afterPlayerAwait);
        this.afterPlayerAwait = null;
        this.awaitingPlayer = false;
        r3.run();
    }

    private /* synthetic */ void lambda$onCloseDone$4() {
        if (this.windowManager == null) goto L10;
        WindowView r0 = this.windowView;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.getParent() == null) goto L12;
        this.windowManager.removeView(this.windowView);
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$onNavigateStart$78() {
        this.collageLayoutView.setCameraThumb(getCameraThumb());
    }

    private /* synthetic */ void lambda$onNavigateStart$79() {
        BulletinFactory.of(this.windowView, this.resourcesProvider).createSimpleBulletin(R.raw.voip_invite, premiumText(LocaleController.getString(R.string.StoryPremiumFormatting))).show(true);
    }

    private /* synthetic */ void lambda$onNavigateStart$80(FilterGLThread r1) {
        if (r1 == null) goto L9;
        StoryEntry r0 = this.outputEntry;
        if (r0 == null) goto L10;
        MediaController.SavedFilterState r02 = r0.filterState;
        if (r02 == null) goto L11;
        r1.setFilterGLThreadDelegate(FilterShaders.getFilterShadersDelegate(r02));
        return;
    L11:
        return;
    L10:
        return;
    }

    private /* synthetic */ void lambda$onNavigateStart$81(Boolean r5, Float r6) {
        if (this.previewView.getDuration() >= 100) goto L5;
        long r0 = this.outputEntry.duration;
    L6:
        float r52 = r6.floatValue() + ((r6.floatValue() / 0.96f) * 0.04f);
        StoryEntry r62 = this.outputEntry;
        float r2 = r62.right;
        float r63 = r62.left;
        float r02 = r0;
        long r1 = (long) ((r52 * (r2 - r63)) * r02);
        PreviewView r53 = this.previewView;
        long r03 = (long) ((r63 * r02) + r1);
        this.coverValue = r03;
        r53.seekTo(r03, false);
        PaintView r54 = this.paintView;
        if (r54 == null) goto L9;
        r54.setCoverTime(this.coverValue);
    L9:
        StoryEntry r4 = this.outputEntry;
        if (r4 != null) goto L12;
        return;
    L12:
        if (r4.isEdit == false) goto L16;
        r4.editedMedia = true;
        return;
    L16:
        return;
    L5:
        r0 = this.previewView.getDuration();
        goto L6
    }

    private /* synthetic */ void lambda$onRequestPermissionsResultInternal$109(AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        this.activity.startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onRequestPermissionsResultInternal$110(AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        this.activity.startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onRequestPermissionsResultInternal$111(AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        this.activity.startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onResumeInternal$108() {
        requestCameraPermission(false);
    }

    private /* synthetic */ void lambda$openEdit$0(boolean r3) {
        animateOpenTo(1.0f, r3, new StoryRecorder$$ExternalSyntheticLambda10(this));
        this.previewButtons.appear(true, true);
    }

    private /* synthetic */ void lambda$openForward$1(boolean r3) {
        animateOpenTo(1.0f, r3, new StoryRecorder$$ExternalSyntheticLambda10(this));
    }

    private /* synthetic */ void lambda$openPremium$113(DialogInterface r2) {
        PreviewView r1 = this.previewView;
        if (r1 == null) goto L6;
        r1.updatePauseReason(4, false);
        return;
    }

    private /* synthetic */ void lambda$prepareThumb$64(StoryEntry r1, File r2) {
        r1.draftThumbFile = r2;
        MultipleStoriesSelector r0 = this.storiesSelector;
        if (r0 == null) goto L6;
        r0.update();
        return;
    }

    private /* synthetic */ void lambda$prepareThumb$65(Bitmap r3, boolean r4, File r5, StoryEntry r6) {
        Bitmap.CompressFormat r0 = Bitmap.CompressFormat.JPEG;     // Catch: Exception -> L8
        if (r4 == false) goto L5;
        int r42 = 95;
    L6:
        r3.compress(r0, r42, new FileOutputStream(r5));     // Catch: Exception -> L8
    L10:
        AndroidUtilities.runOnUIThread(new StoryRecorder$$ExternalSyntheticLambda109(this, r6, r5));
        return;
    L5:
        r42 = 99;
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L10
    }

    private /* synthetic */ void lambda$processDone$44(StoryPrivacyBottomSheet.StoryPrivacy r4) {
        StoryEntry r0 = this.outputEntry;
        if (r0 == null) goto L5;
        r0.privacy = r4;
    L5:
        ArrayList<StoryEntry> r3 = this.entries;
        if (r3 == null) goto L10;
        int r02 = r3.size();
        int r1 = 0;
    L8:
        if (r1 >= r02) goto L12;
        StoryEntry r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.privacy = r4;
        goto L8
    L12:
        return;
    }

    private /* synthetic */ void lambda$processDone$45(TLRPC.InputPeer r5) {
        StoryEntry r0 = this.outputEntry;
        if (r0 == null) goto L14;
        if (r5 != null) goto L7;
        r5 = new TLRPC.TL_inputPeerSelf();
    L7:
        r0.peer = r5;
        ArrayList<StoryEntry> r52 = this.entries;
        if (r52 == null) goto L12;
        int r02 = r52.size();
        int r1 = 0;
    L10:
        if (r1 >= r02) goto L15;
        StoryEntry r2 = r52.get(r1);
        r1 = r1 + 1;
        TLRPC.InputPeer r3 = this.outputEntry.peer;
        r2.peer = r3;
        goto L10
    L15:
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$processDone$46(HashSet r4) {
        StoryEntry r0 = this.outputEntry;
        if (r0 == null) goto L12;
        r0.albums = r4;
        ArrayList<StoryEntry> r3 = this.entries;
        if (r3 == null) goto L10;
        int r02 = r3.size();
        int r1 = 0;
    L8:
        if (r1 >= r02) goto L13;
        StoryEntry r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.albums = r4;
        goto L8
    L13:
        return;
    L10:
        return;
    }

    private /* synthetic */ void lambda$processDone$47(Runnable r1) {
        r1.run();
        upload(true);
    }

    private /* synthetic */ void lambda$processDone$48(StoryPrivacyBottomSheet.StoryPrivacy r4, boolean r5, boolean r6, boolean r7, boolean r8, TLRPC.InputPeer r9, int r10, Runnable r11, Runnable r12) {
        if (this.outputEntry != null) goto L5;
        return;
    L5:
        this.previewView.updatePauseReason(5, true);
        this.outputEntry.privacy = r4;
        StoryPrivacySelector.save(this.currentAccount, r4);
        StoryEntry r52 = this.outputEntry;
        r52.pinned = r7;
        r52.allowScreenshots = r6;
        r52.privacyRules.clear();
        this.outputEntry.privacyRules.addAll(r4.rules);
        StoryEntry r53 = this.outputEntry;
        r53.editedPrivacy = true;
        r53.peer = r9;
        ArrayList<StoryEntry> r54 = this.entries;
        if (r54 == null) goto L10;
        int r82 = r54.size();
        int r122 = 0;
    L8:
        if (r122 >= r82) goto L10;
        StoryEntry r0 = r54.get(r122);
        r122 = r122 + 1;
        StoryEntry r02 = r0;
        r02.privacy = r4;
        r02.pinned = r7;
        r02.allowScreenshots = r6;
        r02.privacyRules.clear();
        r02.privacyRules.addAll(r4.rules);
        r02.editedPrivacy = true;
        r02.peer = r9;
    L10:
        applyFilter(new StoryRecorder$$ExternalSyntheticLambda123(this, r11));
    }

    private /* synthetic */ void lambda$processDone$49(Bitmap r2) {
        StoryEntry r0 = this.outputEntry;
        if (r0 == null) goto L13;
        Bitmap r02 = r0.coverBitmap;
        if (r02 == null) goto L8;
        r02.recycle();
    L8:
        this.outputEntry.coverBitmap = r2;
        StoryPrivacyBottomSheet r1 = this.privacySheet;
        if (r1 != null) goto L11;
        return;
    L11:
        r1.setCover(r2);
        return;
    }

    private /* synthetic */ void lambda$processDone$50() {
        StoryPrivacyBottomSheet r0 = this.privacySheet;
        if (r0 == null) goto L5;
        r0.dismiss();
    L5:
        navigateTo(2, true);
    }

    private /* synthetic */ void lambda$processDone$51(DialogInterface r3) {
        this.previewView.updatePauseReason(3, false);
        this.privacySheet = null;
    }

    private /* synthetic */ void lambda$requestCameraPermission$107(AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        this.activity.startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$saveLastCameraBitmap$102(Bitmap r8, Runnable r9) {
        if (r8 != null) goto L13;
    L11:
        AndroidUtilities.runOnUIThread(r9);
        return;
    L13:
        Bitmap r7 = Bitmap.createBitmap(r8, 0, 0, r8.getWidth(), r8.getHeight(), this.cameraView.getMatrix(), true);     // Catch: Throwable -> L9
        r8.recycle();     // Catch: Throwable -> L9
        Bitmap r82 = Bitmap.createScaledBitmap(r7, 80, (int) (r7.getHeight() / (r7.getWidth() / 80.0f)), true);     // Catch: Throwable -> L9
        if (r82 == null) goto L11;
        if (r82 == r7) goto L7;
        r7.recycle();     // Catch: Throwable -> L9
    L7:
        Utilities.blurBitmap(r82, 7);     // Catch: Throwable -> L9
        FileOutputStream r0 = new FileOutputStream(new File(ApplicationLoader.getFilesDirFixed(), "cthumb.jpg"));     // Catch: Throwable -> L9
        r82.compress(Bitmap.CompressFormat.JPEG, 87, r0);     // Catch: Throwable -> L9
        r82.recycle();     // Catch: Throwable -> L9
        r0.close();     // Catch: Throwable -> L9
    L9:
        AndroidUtilities.runOnUIThread(r9);
    }

    private /* synthetic */ void lambda$showDismissEntry$103(AlertDialog r3, int r4) {
        StoryEntry r32 = this.outputEntry;
        if (r32 != null) goto L5;
        return;
    L5:
        r32.captionEntitiesAllowed = MessagesController.getInstance(this.currentAccount).storyEntitiesAllowed();
        this.showSavedDraftHint = !this.outputEntry.isDraft;
        applyFilter(null);
        applyPaint();
        applyPaintMessage();
        destroyPhotoFilterView();
        StoryEntry r0 = this.outputEntry;
        r0.destroy(true);
        r0.caption = this.captionEdit.getText();
        this.outputEntry = null;
        prepareThumb(r0, true);
        DraftsController r33 = MessagesController.getInstance(this.currentAccount).getStoriesController().getDraftsController();
        if (r0.isDraft == false) goto L8;
        r33.edit(r0);
    L9:
        navigateTo(0, true);
        return;
    L8:
        r33.append(r0);
        goto L9
    }

    private /* synthetic */ void lambda$showDismissEntry$104(AlertDialog r2, int r3) {
        StoryEntry r22 = this.outputEntry;
        if (r22 != null) goto L5;
    L13:
        StoryEntry r23 = this.outputEntry;
        if (r23 != null) goto L16;
    L25:
        navigateTo(0, true);
        return;
    L16:
        if (r23.isShare == false) goto L18;
    L23:
        close(true);
        return;
    L18:
        if (r23.isEdit == true) goto L23;
        if (r23.isRepost == false) goto L25;
        if (r23.isRepostMessage == true) goto L25;
    L5:
        if (r22.isEdit == true) goto L13;
        if (r22.isRepost == false) goto L11;
        if (r22.isRepostMessage == false) goto L13;
    L11:
        if (r22.isDraft == false) goto L13;
        MessagesController.getInstance(this.currentAccount).getStoriesController().getDraftsController().delete(this.outputEntry);
        this.outputEntry = null;
        goto L13
    }

    private /* synthetic */ void lambda$showLimitReachedSheet$112(boolean r3, DialogInterface r4) {
        this.shownLimitReached = false;
        this.previewView.updatePauseReason(7, true);
        if (r3 == false) goto L6;
        close(true);
        return;
    }

    private /* synthetic */ void lambda$showVideoTimer$66(boolean r1) {
        if (r1 == true) goto L5;
        this.videoTimerView.setRecording(false, false);
        return;
    }

    private /* synthetic */ void lambda$showZoomControls$67() {
        showZoomControls(false, true);
        this.zoomControlHideRunnable = null;
    }

    private /* synthetic */ void lambda$showZoomControls$68() {
        showZoomControls(false, true);
        this.zoomControlHideRunnable = null;
    }

    private /* synthetic */ void lambda$startLive$52(StoryPrivacyBottomSheet.StoryPrivacy r1) {
        this.livePrivacy = r1;
    }

    private /* synthetic */ void lambda$startLive$53(TLRPC.InputPeer r2) {
        SelectPeerView r0 = this.livePeerView;
        this.livePeer = r2;
        r0.set(r2);
    }

    private /* synthetic */ void lambda$startLive$54(boolean r12, TL_stories.StoryItem r13, long r14, TLRPC.InputGroupCall r16, boolean r17) {
        if (r12 == true) goto L4;
        LivePlayer.recording = new LivePlayer(getContext(), this.currentAccount, r13, r14, r13.id, r12, r16, true, r17);
    L4:
        SourceView r122 = this.fromSourceView;
        if (r122 == null) goto L7;
        r122.show(false);
    L7:
        this.fromSourceView = null;
        this.openType = 0;
        RectF r0 = this.fromRect;
        Point r1 = AndroidUtilities.displaySize;
        r0.set(0.0f, 0.0f, r1.x, r1.y);
        this.fromRounding = AndroidUtilities.dp(8.0f);
        close(true);
        BaseFragment r15 = LaunchActivity.getSafeLastFragment();
        r13.dialogId = r14;
        r13.justUploaded = true;
        r15.getOrCreateStoryViewer().open(getContext(), r13, null);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.liveStoryUpdated, new Object[]{Long.valueOf(r16.id)});
    }

    private /* synthetic */ void lambda$startLive$55(TLObject r15, TL_stories.TL_startLive r16, boolean r17, long r18, boolean r20, TLRPC.TL_error r21, Runnable r22) {
        if ((r15 instanceof TLRPC.Updates) == false) goto L36;
        TLRPC.Updates r0 = (TLRPC.Updates) r15;
        int r3 = 0;
        MessagesController.getInstance(this.currentAccount).processUpdates(r0, false);
        DualCameraView r2 = this.cameraView;
        if (r2 == null) goto L7;
        r2.destroy(true, null);
    L7:
        ArrayList r23 = MessagesController.findUpdates(r0, TL_update.TL_updateStoryID.class);
        int r4 = r23.size();
        int r6 = 0;
    L9:
        if (r6 >= r4) goto L13;
        Object r8 = r23.get(r6);
        r6 = r6 + 1;
        TL_update.TL_updateStoryID r82 = (TL_update.TL_updateStoryID) r8;
        if (r82.random_id != r16.random_id) goto L9;
        int r24 = r82.id;
    L14:
        ArrayList r02 = MessagesController.findUpdates(r0, TL_stories.TL_updateStory.class);
        int r42 = r02.size();
    L15:
        if (r3 >= r42) goto L22;
        Object r62 = r02.get(r3);
        r3 = r3 + 1;
        TL_stories.StoryItem r63 = ((TL_stories.TL_updateStory) r62).story;
        if (r63 == null) goto L15;
        if (r63.id == r24) goto L21;
        if (r24 != (-1)) goto L15;
    L21:
        TL_stories.StoryItem r32 = r63;
    L23:
        if (r32 == null) goto L27;
        TLRPC.MessageMedia r03 = r32.media;
        if ((r03 instanceof TLRPC.TL_messageMediaVideoStream) == false) goto L27;
        TLRPC.InputGroupCall r64 = ((TLRPC.TL_messageMediaVideoStream) r03).call;
    L28:
        if (r64 == null) goto L57;
        LivePlayer r04 = LivePlayer.recording;
        if (r04 == null) goto L34;
        r04.destroy();
        if (LivePlayer.recording == null) goto L34;
        LivePlayer.recording = null;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.liveStoryUpdated, new Object[]{Long.valueOf(LivePlayer.recording.getCallId())});
    L34:
        AndroidUtilities.runOnUIThread(new StoryRecorder$$ExternalSyntheticLambda5(this, r17, r32, r18, r64, r20), 100);
        return;
    L57:
        return;
    L27:
        r64 = null;
        goto L28
    L22:
        r32 = null;
        goto L23
    L13:
        r24 = -1;
        goto L14
    L36:
        if (r21 != null) goto L38;
        return;
    L38:
        if (r21.text.startsWith("STORY_LIVE_ALREADY_") == false) goto L41;
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.LiveStoryAlreadyStreamingTitle)).setMessage(LocaleController.getString(R.string.LiveStoryAlreadyStreaming)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
        return;
    L41:
        StoryPrivacyBottomSheet r05 = this.privacySheet;
        if (r05 == null) goto L59;
        BulletinFactory.of(r05.container, this.resourcesProvider).showForError(r21, true);
        if (r22 == null) goto L60;
        r22.run();
        return;
    L60:
        return;
    }

    private /* synthetic */ void lambda$startLive$56(TL_stories.TL_startLive r11, boolean r12, long r13, boolean r15, Runnable r16, TLObject r17, TLRPC.TL_error r18) {
        AndroidUtilities.runOnUIThread(new StoryRecorder$$ExternalSyntheticLambda4(this, r17, r11, r12, r13, r15, r18, r16));
    }

    private /* synthetic */ void lambda$startLive$57(Runnable r4, boolean r5, TLRPC.InputPeer r6, StoryPrivacyBottomSheet.StoryPrivacy r7, boolean r8, boolean r9, int r10, Boolean r11) {
        if (r11.booleanValue() == true) goto L7;
        if (r4 == null) goto L28;
        r4.run();
        return;
    L28:
        return;
    L7:
        DualCameraView r112 = this.cameraView;
        if (r112 != null) goto L10;
    L14:
        boolean r113 = true;
    L15:
        TL_stories.TL_startLive r52 = new TL_stories.TL_startLive();
        r52.noforwards = true ^ r5;
        if (r6 != null) goto L18;
        TLRPC.InputPeer r0 = new TLRPC.TL_inputPeerSelf();
    L19:
        r52.peer = r0;
        if (r6 != null) goto L22;
    L25:
        long r02 = UserConfig.getInstance(this.currentAccount).getClientUserId();
    L26:
        r52.privacy_rules.addAll(r7.rules);
        r52.random_id = Utilities.random.nextLong();
        r52.rtmp_stream = r8;
        r52.messages_enabled = Boolean.valueOf(r9);
        r52.send_paid_messages_stars = Long.valueOf(r10);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, new StoryRecorder$$ExternalSyntheticLambda126(this, r52, r8, r02, r113, r4));
        return;
    L22:
        if ((r6 instanceof TLRPC.TL_inputPeerSelf) == true) goto L25;
        r02 = DialogObject.getPeerDialogId(r6);
        goto L26
    L18:
        r0 = r6;
        goto L19
    L10:
        if (r112.isFrontface() == true) goto L14;
        r113 = false;
        goto L15
    }

    private /* synthetic */ void lambda$startLive$58(StoryPrivacyBottomSheet.StoryPrivacy r13, boolean r14, boolean r15, boolean r16, boolean r17, TLRPC.InputPeer r18, int r19, Runnable r20, Runnable r21) {
        int r0 = R.raw.permission_request_camera;
        int r1 = R.string.PermissionNoCameraMicVideo;
        if (r17 == false) goto L5;
        String[] r2 = new String[0];
    L6:
        PermissionRequest.ensureAllPermissions(r0, r1, r2, new StoryRecorder$$ExternalSyntheticLambda125(this, r21, r15, r18, r13, r17, r14, r19));
        return;
    L5:
        r2 = new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
        goto L6
    }

    private /* synthetic */ void lambda$switchTo$60(int r8) {
        applyPaintMessage();
        this.switchingStory = false;
        File r1 = this.outputEntry.draftThumbFile;
        if (r1 == null) goto L5;
        r1.delete();
        this.outputEntry.draftThumbFile = null;
    L5:
        prepareThumb(this.outputEntry, true);
        CharSequence[] r3 = {this.captionEdit.getText()};
        if (MessagesController.getInstance(this.currentAccount).storyEntitiesAllowed() == false) goto L8;
        ArrayList<TLRPC.MessageEntity> r12 = MediaDataController.getInstance(this.currentAccount).getEntities(r3, true);
    L9:
        CharSequence[] r5 = {this.outputEntry.caption};
        if (MessagesController.getInstance(this.currentAccount).storyEntitiesAllowed() == false) goto L12;
        ArrayList<TLRPC.MessageEntity> r4 = MediaDataController.getInstance(this.currentAccount).getEntities(r5, true);
    L13:
        StoryEntry r52 = this.outputEntry;
        if (TextUtils.equals(r52.caption, r3[0]) == true) goto L16;
    L19:
        boolean r13 = true;
    L20:
        r52.editedCaption = r13;
        this.outputEntry.caption = new SpannableString(this.captionEdit.getText());
        destroyPhotoPaintView();
        destroyPhotoFilterView();
        StoryEntry r14 = this.outputEntry;
        if (r14 != null) goto L23;
    L25:
        int r15 = 0;
    L26:
        this.mode = r15;
        this.outputEntry = this.entries.get(r8);
        onNavigateStart(0, 1);
        onNavigateEnd(0, 1);
        this.storiesSelector.update();
        this.captionEdit.setText(this.outputEntry.caption);
        return;
    L23:
        if (r14.isVideo == false) goto L25;
        r15 = 1;
        goto L26
    L16:
        if (MediaDataController.entitiesEqual(r12, r4) == false) goto L19;
        r13 = false;
        goto L20
    L12:
        r4 = new ArrayList();
        goto L13
    L8:
        r12 = new ArrayList();
        goto L9
    }

    private /* synthetic */ void lambda$switchToEditMode$82(ValueAnimator r1) {
        this.cropEditor.setAppearProgress(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$switchToEditMode$83(ValueAnimator r1) {
        this.cropEditor.setAppearProgress(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$switchToEditMode$84(ValueAnimator r1) {
        this.cropInlineEditor.setAppearProgress(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$switchToEditMode$85(ValueAnimator r1) {
        this.cropInlineEditor.setAppearProgress(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$toggleTheme$117() {
        StoryEntry r0 = this.outputEntry;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.isDark = !r0.isDark;
        PreviewView r1 = this.previewView;
        int r3 = 0;
        if (r1 == null) goto L8;
        r1.setupWallpaper(r0, false);
    L8:
        PaintView r02 = this.paintView;
        if (r02 != null) goto L11;
    L18:
        updateThemeButtonDrawable(true);
        return;
    L11:
        if (r02.entitiesView == null) goto L18;
    L13:
        if (r3 >= this.paintView.entitiesView.getChildCount()) goto L18;
        View r03 = this.paintView.entitiesView.getChildAt(r3);
        if ((r03 instanceof MessageEntityView) == false) goto L17;
        ((MessageEntityView) r03).setupTheme(this.outputEntry);
    L17:
        r3 = r3 + 1;
        goto L13
    }

    private /* synthetic */ void lambda$upload$59(boolean r2) {
        applyPaintMessage();
        this.preparingUpload = false;
        uploadInternal(r2);
    }

    private /* synthetic */ void lambda$uploadInternal$61(boolean r2, StoryEntry r3) {
        prepareThumb(r3, false);
        MessagesController.getInstance(this.currentAccount).getStoriesController().uploadStory(r3, r2);
        if (r3.isDraft == true) goto L5;
    L7:
        r3.cancelCheckStickers();
        return;
    L5:
        if (r3.isEdit == true) goto L7;
        MessagesController.getInstance(this.currentAccount).getStoriesController().getDraftsController().delete(r3);
        goto L7
    }

    private /* synthetic */ void lambda$uploadInternal$62() {
        close(true);
    }

    private /* synthetic */ void lambda$uploadInternal$63(boolean r3, long r4) {
        if (r3 == false) goto L31;
        SourceView r32 = this.fromSourceView;
        if (r32 == null) goto L7;
        r32.show(true);
        this.fromSourceView = null;
    L7:
        Runnable r33 = this.closeListener;
        if (r33 == null) goto L10;
        r33.run();
        this.closeListener = null;
    L10:
        ClosingViewProvider r34 = this.closingSourceProvider;
        if (r34 == null) goto L13;
        SourceView r35 = r34.getView(r4);
    L14:
        this.fromSourceView = r35;
        if (r35 == null) goto L25;
        this.openType = r35.type;
        this.containerView.updateBackground();
        FrameLayout r36 = this.previewContainer;
        int r42 = this.openType;
        if (r42 == 1) goto L21;
        if (r42 == 0) goto L21;
        int r43 = -14737633;
    L22:
        r36.setBackgroundColor(r43);
        this.fromRect.set(this.fromSourceView.screenRect);
        SourceView r37 = this.fromSourceView;
        this.fromRounding = r37.rounding;
        r37.hide();
        if (SharedConfig.getDevicePerformanceClass() <= 1) goto L25;
        LiteMode.isEnabled(LiteMode.FLAGS_CHAT);
    L21:
        r43 = 0;
    L25:
        this.closingSourceProvider = null;
        Activity r38 = this.activity;
        if ((r38 instanceof LaunchActivity) == false) goto L29;
        ((LaunchActivity) r38).drawerLayoutContainer.post(new StoryRecorder$$ExternalSyntheticLambda114(this));
        return;
    L29:
        close(true);
        return;
    L13:
        r35 = null;
        goto L14
    L31:
        close(true);
    }

    private void onCloseDone() {
        this.isShown = false;
        AndroidUtilities.unlockOrientation(this.activity);
        if (this.cameraView != null) goto L5;
    L8:
        PreviewView r1 = this.previewView;
        if (r1 == null) goto L11;
        r1.set(null);
    L11:
        destroyPhotoPaintView();
        destroyPhotoFilterView();
        File r12 = this.outputFile;
        if (r12 != null) goto L14;
    L16:
        this.outputFile = null;
        AndroidUtilities.runOnUIThread(new StoryRecorder$$ExternalSyntheticLambda90(this), 16);
        SourceView r13 = this.fromSourceView;
        if (r13 == null) goto L20;
        r13.show(false);
    L20:
        if (this.whenOpenDone == null) goto L22;
        this.whenOpenDone = null;
    L22:
        this.lastGalleryScrollPosition = null;
        StoryRecorder r14 = instance;
        if (r14 == null) goto L25;
        r14.close(false);
    L25:
        instance = null;
        Runnable r0 = this.onCloseListener;
        if (r0 == null) goto L28;
        r0.run();
        this.onCloseListener = null;
    L28:
        WindowView r02 = this.windowView;
        if (r02 == null) goto L31;
        Bulletin.removeDelegate(r02);
    L31:
        FrameLayout r03 = this.captionContainer;
        if (r03 == null) goto L34;
        Bulletin.removeDelegate(r03);
    L34:
        CollageLayoutView2 r04 = this.collageLayoutView;
        if (r04 == null) goto L37;
        r04.clear(true);
    L37:
        CaptionStory r5 = this.captionEdit;
        if (r5 == null) goto L46;
        EditTextEmoji r52 = r5.editText;
        if (r52 == null) goto L47;
        r52.onDestroy();
        return;
    L47:
        return;
    L46:
        return;
    L14:
        if (this.wasSend == true) goto L16;
        r12.delete();     // Catch: Exception -> L43
        goto L16
    L5:
        if (this.takingVideo == false) goto L7;
        CameraController.getInstance().stopVideoRecording(this.cameraView.getCameraSession(), false);
    L7:
        destroyCameraView(false);
        goto L8
    }

    private void onNavigateEnd(int r10, int r11) {
        int r0 = 8;
        if (r10 != 0) goto L6;
        destroyCameraView(false);
        this.recordControl.setVisibility(8);
        this.zoomControlView.setVisibility(8);
        this.modeSwitcherView.setVisibility(8);
        animateRecording(false, false);
        setAwakeLock(false);
    L6:
        if (r10 != 2) goto L13;
        this.coverTimelineView.setVisibility(8);
        FrameLayout r4 = this.captionContainer;
        if (r11 != 1) goto L10;
        int r5 = 0;
    L11:
        r4.setVisibility(r5);
        this.captionEdit.setVisibility(8);
        this.coverButton.setVisibility(8);
        goto L13
    L10:
        r5 = 8;
    L13:
        if (r10 != 1) goto L33;
        this.previewButtons.setVisibility(8);
        FrameLayout r102 = this.captionContainer;
        if (r11 != 2) goto L17;
        int r52 = 0;
    L18:
        r102.setVisibility(r52);
        this.muteButton.setVisibility(8);
        this.playButton.setVisibility(8);
        this.downloadButton.setVisibility(8);
        ImageView r103 = this.themeButton;
        if (r103 == null) goto L21;
        r103.setVisibility(8);
    L21:
        PreviewView r104 = this.previewView;
        if (r11 != 2) goto L24;
        int r53 = 0;
    L25:
        r104.setVisibility(r53);
        this.timelineView.setVisibility(8);
        if (r11 == 2) goto L28;
        destroyPhotoPaintView();
        destroyPhotoFilterView();
    L28:
        SimpleTextView r105 = this.titleTextView;
        if (r11 != 2) goto L31;
        int r54 = 0;
    L32:
        r105.setVisibility(r54);
        destroyGalleryListView();
        this.trash.setAlpha(0.0f);
        this.trash.setVisibility(8);
        this.videoTimeView.setVisibility(8);
        this.entries = null;
        this.selectedEntries = null;
        this.selectedEntriesOrder = null;
        goto L33
    L31:
        r54 = 8;
        goto L32
    L24:
        r53 = 8;
        goto L25
    L17:
        r52 = 8;
    L33:
        if (r11 != 1) goto L60;
        StoryEntry r106 = this.outputEntry;
        if (r106 != null) goto L37;
    L38:
        createPhotoPaintView();
        hidePhotoPaintView();
    L39:
        StoryEntry r107 = this.outputEntry;
        if (r107 != null) goto L42;
    L44:
        PhotoFilterView.EnhanceView r108 = this.photoFilterEnhanceView;
        if (r108 == null) goto L47;
        r108.setAllowTouch(false);
    L47:
        this.previewView.updatePauseReason(2, false);
        this.previewView.updatePauseReason(3, false);
        this.previewView.updatePauseReason(4, false);
        this.previewView.updatePauseReason(5, false);
        this.previewView.updatePauseReason(7, false);
        VideoTimeView r109 = this.videoTimeView;
        StoryEntry r55 = this.outputEntry;
        if (r55 != null) goto L50;
    L52:
        int r56 = 8;
    L53:
        r109.setVisibility(r56);
        this.captionContainer.setAlpha(1.0f);
        this.captionContainer.setTranslationY(0.0f);
        CaptionStory r1010 = this.captionEdit;
        StoryEntry r42 = this.outputEntry;
        if (r42 != null) goto L56;
    L58:
        r0 = 0;
    L59:
        r1010.setVisibility(r0);
        goto L60
    L56:
        if (r42.botId == 0) goto L58;
    L50:
        if (r55.duration < NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L52;
        r56 = 0;
        goto L53
    L42:
        if (r107.isRepost == true) goto L44;
        boolean r1011 = r107.isRepostMessage;
        goto L44
    L37:
        if (r106.isRepost == true) goto L39;
    L60:
        if (r11 == 0) goto L62;
    L64:
        this.showSavedDraftHint = false;
        PhotoFilterView.EnhanceView r1012 = this.photoFilterEnhanceView;
        if (r1012 == null) goto L73;
        if (r11 != 1) goto L71;
        int r02 = this.currentEditMode;
        if (r02 == (-1)) goto L70;
        if (r02 != 1) goto L71;
    L70:
        boolean r03 = true;
    L72:
        r1012.setAllowTouch(r03);
    L71:
        r03 = false;
    L73:
        CaptionStory r1013 = this.captionEdit;
        if (r1013 == null) goto L79;
        if (r11 == 1) goto L77;
        boolean r04 = true;
    L78:
        r1013.ignoreTouches = r04;
        goto L79
    L77:
        r04 = false;
    L79:
        if (r11 != 1) goto L82;
        MediaDataController.getInstance(this.currentAccount).checkStickers(0);
        MediaDataController.getInstance(this.currentAccount).loadRecents(0, false, true, false);
        MediaDataController.getInstance(this.currentAccount).loadRecents(2, false, true, false);
        MessagesController.getInstance(this.currentAccount).getStoriesController().loadBlocklistAtFirst();
        MessagesController.getInstance(this.currentAccount).getStoriesController().loadSendAs();
        return;
    L82:
        return;
    L62:
        if (this.showSavedDraftHint == false) goto L64;
        getDraftSavedHint().setVisibility(0);
        getDraftSavedHint().show();
        this.recordControl.updateGalleryImage();
        goto L64
    }

    private void onNavigateStart(int r17, int r18) {
        StoryRecorder$$ExternalSyntheticLambda58 r4 = null;
        boolean r7 = true;
        if (r18 != 0) goto L14;
        requestCameraPermission(false);
        this.recordControl.setVisibility(0);
        RecordControl r9 = this.recordControl;
        if (r9 == null) goto L7;
        r9.stopRecordingLoading(false);
    L7:
        this.modeSwitcherView.setVisibility(0);
        this.zoomControlView.setVisibility(0);
        this.zoomControlView.setAlpha(0.0f);
        this.videoTimerView.setDuration(0, true);
        StoryEntry r92 = this.outputEntry;
        if (r92 == null) goto L10;
        r92.destroy(false);
        this.outputEntry = null;
    L10:
        CollageLayoutView2 r93 = this.collageLayoutView;
        if (r93 == null) goto L14;
        r93.clear(true);
        this.recordControl.setCollageProgress(0.0f, false);
    L14:
        if (r17 != 0) goto L21;
        setCameraFlashModeIcon(null, true);
        saveLastCameraBitmap(new StoryRecorder$$ExternalSyntheticLambda57(this));
        DraftSavedHint r10 = this.draftSavedHint;
        if (r10 == null) goto L18;
        r10.setVisibility(8);
    L18:
        this.cameraHint.hide();
        HintView2 r102 = this.dualHint;
        if (r102 == null) goto L21;
        r102.hide();
    L21:
        if (r18 == 1) goto L23;
        if (r17 == 1) goto L23;
    L88:
        if (r18 == 2) goto L90;
        if (r17 == 2) goto L90;
    L96:
        if (r18 != 2) goto L98;
        this.titleTextView.setText(LocaleController.getString(R.string.RecorderEditCover));
    L98:
        if (r18 != 1) goto L237;
        this.videoError = false;
        StoryEntry r11 = this.outputEntry;
        if (r11 != null) goto L102;
    L104:
        boolean r12 = false;
    L105:
        if (r11 != null) goto L107;
    L109:
        boolean r112 = false;
    L110:
        PreviewButtons r13 = this.previewButtons;
        if (r112 == false) goto L113;
        int r14 = R.string.Done;
    L116:
        r13.setShareText(LocaleController.getString(r14), !r12);
        this.coverTimelineView.setVisibility(8);
        this.coverButton.setVisibility(8);
        if (this.previewAlreadySet == true) goto L124;
        StoryEntry r132 = this.outputEntry;
        if (r132 != null) goto L121;
    L123:
        this.previewView.set(r132);
        goto L124
    L121:
        if (r132.isRepostMessage == false) goto L123;
        this.previewView.preset(r132);
    L124:
        this.previewAlreadySet = false;
        EditTextCaption r133 = this.captionEdit.editText.getEditText();
        if (MessagesController.getInstance(this.currentAccount).storyEntitiesAllowed() == true) goto L128;
        r4 = new StoryRecorder$$ExternalSyntheticLambda58(this);
    L128:
        r133.setOnPremiumMenuLockClickListener(r4);
        MultipleStoriesSelector r42 = this.storiesSelector;
        if (this.entries != null) goto L131;
        int r134 = 8;
    L132:
        r42.setVisibility(r134);
        ArrayList<StoryEntry> r43 = this.entries;
        if (r43 == null) goto L135;
        this.storiesSelector.set(r43, this.selectedEntriesOrder, this.selectedEntries);
        this.storiesSelector.setSelected(this.entries.indexOf(this.outputEntry));
    L135:
        TimelineView r44 = this.timelineView;
        if (r12 == true) goto L147;
        if (r112 == true) goto L147;
        if (this.entries != null) goto L147;
        StoryEntry r113 = this.outputEntry;
        if (r113 != null) goto L143;
    L146:
        int r114 = 3;
    L148:
        r44.setMaxCount(r114);
        if (r17 == 2) goto L171;
        StoryEntry r45 = this.outputEntry;
        if (r45 != null) goto L153;
    L169:
        if (r17 == 2) goto L171;
        this.captionEdit.clear();
        goto L171
    L153:
        if (r45.isDraft == true) goto L159;
        if (r45.isEdit == true) goto L159;
        if (this.isReposting == false) goto L169;
    L159:
        if (r45.paintFile == null) goto L161;
        destroyPhotoPaintView();
        createPhotoPaintView();
        hidePhotoPaintView();
    L161:
        StoryEntry r46 = this.outputEntry;
        if (r46.isVideo == true) goto L164;
    L168:
        this.captionEdit.setText(this.outputEntry.caption);
        goto L171
    L164:
        if (r46.filterState == null) goto L168;
        VideoEditTextureView r47 = this.previewView.getTextureView();
        if (r47 == null) goto L168;
        r47.setDelegate(new StoryRecorder$$ExternalSyntheticLambda59(this));
    L171:
        PreviewButtons r48 = this.previewButtons;
        StoryEntry r115 = this.outputEntry;
        if (r115 != null) goto L174;
    L181:
        boolean r116 = true;
    L182:
        r48.setButtonVisible(4, r116);
        PreviewButtons r49 = this.previewButtons;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L191;
        StoryEntry r117 = this.outputEntry;
        if (r117 == null) goto L191;
        if (r117.isRepostMessage == true) goto L191;
        if (r117.isCollage() == true) goto L191;
        boolean r118 = true;
    L192:
        r49.setButtonVisible(3, r118);
        PreviewButtons r410 = this.previewButtons;
        if (this.videoError == false) goto L195;
    L205:
        boolean r119 = false;
    L206:
        r410.setShareEnabled(r119);
        RLottieImageView r411 = this.muteButton;
        StoryEntry r1110 = this.outputEntry;
        if (r1110 != null) goto L209;
    L211:
        int r1111 = R.drawable.media_mute;
    L212:
        r411.setImageResource(r1111);
        this.previewView.setVisibility(0);
        this.timelineView.setVisibility(0);
        this.titleTextView.setVisibility(0);
        this.titleTextView.setTranslationX(0.0f);
        StoryEntry r3 = this.outputEntry;
        String r412 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r3 != null) goto L215;
    L217:
        if (r3 != null) goto L219;
    L221:
        if (r3 != null) goto L223;
    L225:
        if (r3 != null) goto L227;
    L236:
        this.titleTextView.setText(LocaleController.getString(R.string.RecorderNewStory));
        goto L237
    L227:
        if (r3.isRepost == false) goto L236;
        SpannableStringBuilder r32 = new SpannableStringBuilder();
        AvatarSpan r1112 = new AvatarSpan(this.titleTextView, this.currentAccount, 32.0f);
        this.titleTextView.setTranslationX(-AndroidUtilities.dp(6.0f));
        SpannableString r122 = new SpannableString("a");
        r122.setSpan(r1112, 0, 1, 33);
        boolean r135 = this.outputEntry.repostPeer instanceof TLRPC.TL_peerUser;
        int r142 = this.currentAccount;
        if (r135 == false) goto L231;
        TLRPC.User r413 = MessagesController.getInstance(r142).getUser(Long.valueOf(this.outputEntry.repostPeer.user_id));
        r1112.setUser(r413);
        r32.append(r122).append("  ");
        r32.append(UserObject.getUserName(r413));
    L235:
        this.titleTextView.setText(r32);
        goto L237
    L231:
        TLRPC.Chat r8 = MessagesController.getInstance(r142).getChat(Long.valueOf(-DialogObject.getPeerDialogId(this.outputEntry.repostPeer)));
        r1112.setChat(r8);
        r32.append(r122).append("  ");
        if (r8 == null) goto L234;
        r412 = r8.title;
    L234:
        r32.append(r412);
        goto L235
    L223:
        if (r3.isRepostMessage == false) goto L225;
        this.titleTextView.setText(LocaleController.getString(R.string.RecorderRepost));
        goto L237
    L219:
        if (r3.isEdit == false) goto L221;
        this.titleTextView.setText(LocaleController.getString(R.string.RecorderEditStory));
        goto L237
    L215:
        if (r3.botId == 0) goto L217;
        this.titleTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L237
    L209:
        if (r1110.muted == false) goto L211;
        r1111 = R.drawable.media_unmute;
        goto L212
    L195:
        if (this.captionEdit.isCaptionOverLimit() == true) goto L205;
        if (MessagesController.getInstance(this.currentAccount).getStoriesController().hasStoryLimit(getCount()) == false) goto L204;
        StoryEntry r1113 = this.outputEntry;
        if (r1113 == null) goto L205;
        if (r1113.isEdit == true) goto L204;
        if (r1113.botId == 0) goto L205;
    L204:
        r119 = true;
    L191:
        r118 = false;
        goto L192
    L174:
        if (r115.isRepostMessage == false) goto L178;
        if (r115.isVideo == true) goto L178;
    L180:
        r116 = false;
    L178:
        if (r115.isCollage() == true) goto L180;
    L143:
        if (r113.isCollage() == true) goto L147;
        if (this.outputEntry.isVideo == true) goto L146;
    L147:
        r114 = 1;
        goto L148
    L131:
        r134 = 0;
        goto L132
    L113:
        if (r12 == false) goto L115;
        r14 = R.string.UploadBotPreview;
        goto L116
    L115:
        r14 = R.string.Next;
        goto L116
    L107:
        if (r11.isEdit == false) goto L109;
        r112 = true;
        goto L110
    L102:
        if (r11.botId == 0) goto L104;
        r12 = true;
    L237:
        if (r17 != 1) goto L239;
        this.captionEdit.hidePeriodPopup();
        this.muteHint.hide();
        this.storiesSelector.onBackPressed();
    L239:
        if (r18 != 2) goto L253;
        StoryEntry r1 = this.outputEntry;
        if (r1 == null) goto L253;
        if (r1.cover >= 0) goto L245;
        r1.cover = 0;
    L245:
        this.coverValue = r1.cover;
        if (this.previewView.getDuration() >= 100) goto L248;
        long r33 = this.outputEntry.duration;
    L249:
        StoryEntry r15 = this.outputEntry;
        if (r15.duration > 0) goto L252;
        r15.duration = r33;
    L252:
        TimelineView r82 = this.coverTimelineView;
        String r103 = r15.getOriginalFile().getAbsolutePath();
        StoryEntry r16 = this.outputEntry;
        r82.setVideo(false, r103, r16.duration, r16.videoVolume);
        TimelineView r19 = this.coverTimelineView;
        StoryEntry r5 = this.outputEntry;
        float r83 = r33;
        r19.setCoverVideo((long) (r5.left * r83), (long) (r5.right * r83));
        StoryRecorder$$ExternalSyntheticLambda60 r110 = new StoryRecorder$$ExternalSyntheticLambda60(this);
        this.coverTimelineView.setDelegate(new AnonymousClass21(this, r110));
        float r52 = (this.coverValue / Math.max(1, r33)) * 0.96f;
        this.coverTimelineView.setVideoLeft(r52);
        this.coverTimelineView.setVideoRight(0.04f + r52);
        r110.run(Boolean.TRUE, Float.valueOf(r52));
        goto L253
    L248:
        r33 = this.previewView.getDuration();
    L253:
        PhotoFilterView.EnhanceView r111 = this.photoFilterEnhanceView;
        if (r111 == null) goto L256;
        r111.setAllowTouch(false);
    L256:
        HintView2 r120 = this.savedDualHint;
        if (r120 == null) goto L259;
        r120.hide();
    L259:
        Bulletin.hideVisible();
        CaptionStory r121 = this.captionEdit;
        if (r121 == null) goto L262;
        r121.closeKeyboard();
        this.captionEdit.ignoreTouches = true;
    L262:
        PreviewView r123 = this.previewView;
        if (r123 == null) goto L269;
        if (r18 == 1) goto L267;
        boolean r414 = true;
    L268:
        r123.updatePauseReason(8, r414);
        goto L269
    L267:
        r414 = false;
    L269:
        PaintView r124 = this.paintView;
        if (r124 == null) goto L275;
        if (r18 == 1) goto L273;
        boolean r415 = true;
    L274:
        r124.setCoverPreview(r415);
        goto L275
    L273:
        r415 = false;
    L275:
        HintView2 r125 = this.removeCollageHint;
        if (r125 == null) goto L278;
        r125.hide();
    L278:
        CollageLayoutView2 r0 = this.collageLayoutView;
        if (r18 == 1) goto L281;
    L283:
        r7 = false;
    L284:
        r0.setPreview(r7);
        return;
    L281:
        if (r0.hasLayout() == false) goto L283;
    L90:
        this.titleTextView.setVisibility(0);
        this.coverTimelineView.setVisibility(0);
        StoryEntry r1114 = this.outputEntry;
        if (r1114 != null) goto L93;
    L95:
        this.captionContainer.setVisibility(0);
        this.coverButton.setVisibility(0);
        goto L96
    L93:
        if (r1114.isEditingCover == false) goto L95;
        this.titleTextView.setText(LocaleController.getString(R.string.RecorderEditCover));
    L23:
        DownloadButton r104 = this.downloadButton;
        if (r18 != 1) goto L26;
        StoryEntry r1115 = this.outputEntry;
    L27:
        r104.setEntry(r1115);
        if (this.mode != 1) goto L36;
        this.muteButton.setVisibility(0);
        StoryEntry r105 = this.outputEntry;
        if (r105 != null) goto L32;
    L34:
        boolean r106 = false;
    L35:
        setIconMuted(r106, false);
        this.playButton.setVisibility(0);
        this.previewView.play(true);
        this.playButton.drawable.setPause(this.previewView.isPlaying(), false);
        this.titleTextView.setRightPadding(AndroidUtilities.dp(144.0f));
    L42:
        this.downloadButton.setVisibility(0);
        StoryEntry r107 = this.outputEntry;
        if (r107 != null) goto L45;
    L47:
        ImageView r108 = this.themeButton;
        if (r108 == null) goto L50;
        r108.setVisibility(8);
    L50:
        this.previewButtons.setVisibility(0);
        this.previewView.setVisibility(0);
        CaptionStory r109 = this.captionEdit;
        if (isBot() == false) goto L53;
        int r1116 = 8;
    L54:
        r109.setVisibility(r1116);
        FrameLayout.LayoutParams r1010 = (FrameLayout.LayoutParams) this.videoTimelineContainerView.getLayoutParams();
        if (isBot() == false) goto L58;
        float r1117 = 12.0f;
    L59:
        r1010.bottomMargin = AndroidUtilities.dp(r1117);
        this.videoTimelineContainerView.setLayoutParams(r1010);
        this.captionContainer.setVisibility(0);
        this.captionContainer.clearFocus();
        CaptionStory r1011 = this.captionEdit;
        StoryEntry r1118 = this.outputEntry;
        if (r1118 != null) goto L62;
        int r1119 = 86400;
    L63:
        r1011.setPeriod(r1119, false);
        CaptionStory r1012 = this.captionEdit;
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == true) goto L70;
        StoryEntry r1120 = this.outputEntry;
        if (r1120 != null) goto L68;
    L69:
        boolean r1121 = true;
    L71:
        r1012.setPeriodVisible(r1121);
        CaptionStory r1013 = this.captionEdit;
        StoryEntry r1122 = this.outputEntry;
        if (r1122 != null) goto L74;
    L76:
        boolean r1123 = false;
    L77:
        r1013.setHasRoundVideo(r1123);
        setReply();
        TimelineView r1014 = this.timelineView;
        StoryEntry r1124 = this.outputEntry;
        if (r1124 != null) goto L80;
    L85:
        boolean r1125 = true;
    L86:
        r1014.setOpen(r1125, false);
        goto L88
    L80:
        if (r1124.isCollage() == false) goto L85;
        if (this.outputEntry.hasVideo() == false) goto L85;
        r1125 = false;
        goto L86
    L74:
        if (r1122.round == null) goto L76;
        r1123 = true;
        goto L77
    L68:
        if (r1120.isEdit == false) goto L69;
    L70:
        r1121 = false;
        goto L71
    L62:
        r1119 = r1118.period;
        goto L63
    L58:
        r1117 = 68.0f;
        goto L59
    L53:
        r1116 = 0;
        goto L54
    L45:
        if (r107.isRepostMessage == false) goto L47;
        getThemeButton().setVisibility(0);
        updateThemeButtonDrawable(false);
        goto L50
    L32:
        if (r105.muted == false) goto L34;
        r106 = true;
        goto L35
    L36:
        StoryEntry r1015 = this.outputEntry;
        if (r1015 != null) goto L39;
    L41:
        this.titleTextView.setRightPadding(AndroidUtilities.dp(48.0f));
        goto L42
    L39:
        if (TextUtils.isEmpty(r1015.audioPath) == true) goto L41;
        this.muteButton.setVisibility(8);
        this.playButton.setVisibility(0);
        this.playButton.drawable.setPause(true, false);
        this.titleTextView.setRightPadding(AndroidUtilities.dp(48.0f));
        goto L42
    L26:
        r1115 = null;
        goto L27
    }

    private void onOpenDone() {
        this.isShown = true;
        this.wasSend = false;
        if (this.openType != 1) goto L7;
        this.previewContainer.setAlpha(1.0f);
        this.previewContainer.setTranslationX(0.0f);
        this.previewContainer.setTranslationY(0.0f);
        this.actionBarContainer.setAlpha(1.0f);
        this.controlContainer.setAlpha(1.0f);
        this.windowView.setBackgroundColor(-16777216);
        if (this.currentPage != 2) goto L7;
        this.coverButton.setAlpha(1.0f);
    L7:
        Runnable r0 = this.whenOpenDone;
        if (r0 == null) goto L10;
        r0.run();
        this.whenOpenDone = null;
    L11:
        StoryEntry r02 = this.outputEntry;
        if (r02 != null) goto L14;
    L17:
        if (r02 != null) goto L19;
        return;
    L19:
        if (r02.isRepostMessage == true) goto L21;
        return;
    L21:
        if (r02.isVideo == false) goto L23;
        this.previewView.setupVideoPlayer(r02, null, 0);
    L23:
        createFilterPhotoView();
        return;
    L14:
        if (r02.isRepost == false) goto L17;
        createPhotoPaintView();
        hidePhotoPaintView();
        createFilterPhotoView();
        return;
    L10:
        onResumeInternal();
        goto L11
    }

    public static void onPause() {
        StoryRecorder r0 = instance;
        if (r0 == null) goto L6;
        r0.onPauseInternal();
        return;
    }

    private void onPauseInternal() {
        destroyCameraView(false);
        CaptionStory r1 = this.captionEdit;
        if (r1 == null) goto L5;
        r1.onPause();
    L5:
        PreviewView r2 = this.previewView;
        if (r2 == null) goto L9;
        r2.updatePauseReason(0, true);
        return;
    }

    public static void onRequestPermissionsResult(int r1, String[] r2, int[] r3) {
        StoryRecorder r0 = instance;
        if (r0 == null) goto L6;
        r0.onRequestPermissionsResultInternal(r1, r2, r3);
        return;
    }

    private void onRequestPermissionsResultInternal(int r5, String[] r6, int[] r7) {
        if (r7 != null) goto L5;
    L9:
        boolean r72 = false;
    L11:
        if (r5 != 111) goto L23;
        this.noCameraPermission = !r72;
        if (r72 == true) goto L15;
        return;
    L15:
        if (this.currentPage != 0) goto L45;
        this.collageLayoutView.setCameraThumb(null);
        if (CameraController.getInstance().isCameraInitied() == false) goto L20;
        createCameraView();
        return;
    L20:
        CameraController.getInstance().initCamera(new StoryRecorder$$ExternalSyntheticLambda0(this));
        return;
    L45:
        return;
    L23:
        if (r5 != 114) goto L30;
        if (r72 == false) goto L27;
        MediaController.loadGalleryPhotosAlbums(0);
        animateGalleryListView(true);
        return;
    L27:
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTopAnimation(R.raw.permission_request_folder, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PermissionStorageWithHint))).setPositiveButton(LocaleController.getString(R.string.PermissionOpenSettings), new StoryRecorder$$ExternalSyntheticLambda1(this)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).create().show();
        return;
    L30:
        if (r5 != 112) goto L35;
        if (r72 == true) goto L46;
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTopAnimation(R.raw.permission_request_camera, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PermissionNoCameraMicVideo))).setPositiveButton(LocaleController.getString(R.string.PermissionOpenSettings), new StoryRecorder$$ExternalSyntheticLambda2(this)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).create().show();
        return;
    L46:
        return;
    L35:
        if (r5 != 115) goto L47;
        if (r72 == true) goto L38;
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTopAnimation(R.raw.permission_request_folder, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PermissionNoAudioStorageStory))).setPositiveButton(LocaleController.getString(R.string.PermissionOpenSettings), new StoryRecorder$$ExternalSyntheticLambda3(this)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).create().show();
    L38:
        if (r72 == false) goto L42;
        Runnable r52 = this.audioGrantedCallback;
        if (r52 == null) goto L42;
        r52.run();
    L42:
        this.audioGrantedCallback = null;
        return;
    L47:
        return;
    L5:
        if (r7.length != 1) goto L9;
        if (r7[0] != 0) goto L9;
        r72 = true;
        goto L11
    }

    public static void onResume() {
        StoryRecorder r0 = instance;
        if (r0 == null) goto L6;
        r0.onResumeInternal();
        return;
    }

    private void onResumeInternal() {
        if (this.currentPage != 0) goto L10;
        ValueAnimator r0 = this.openCloseAnimator;
        if (r0 != null) goto L7;
    L9:
        requestCameraPermission(false);
        goto L10
    L7:
        if (r0.isRunning() == false) goto L9;
        this.whenOpenDone = new StoryRecorder$$ExternalSyntheticLambda9(this);
    L10:
        CaptionStory r02 = this.captionEdit;
        if (r02 == null) goto L13;
        r02.onResume();
    L13:
        RecordControl r03 = this.recordControl;
        if (r03 == null) goto L16;
        r03.updateGalleryImage();
    L16:
        PreviewHighlightView r04 = this.previewHighlight;
        if (r04 == null) goto L19;
        r04.updateCount();
    L19:
        PaintView r05 = this.paintView;
        if (r05 == null) goto L22;
        r05.onResume();
    L22:
        PreviewView r06 = this.previewView;
        if (r06 == null) goto L25;
        r06.updatePauseReason(0, false);
    L25:
        MessagesController.getInstance(this.currentAccount).getStoriesController().getDraftsController().load();
    }

    private void onSwitchEditModeEnd(int r7, int r8) {
        if (r8 != 0) goto L5;
        this.backButton.setVisibility(8);
    L5:
        if (r7 != 0) goto L9;
        PaintView r1 = this.paintView;
        if (r1 == null) goto L9;
        r1.setVisibility(8);
    L9:
        boolean r2 = false;
        if (r7 != (-1)) goto L34;
        this.captionEdit.setVisibility(8);
        RLottieImageView r3 = this.muteButton;
        if (r8 != 2) goto L14;
        int r5 = 0;
    L15:
        r3.setVisibility(r5);
        PlayPauseButton r32 = this.playButton;
        if (r8 != 2) goto L18;
        int r52 = 0;
    L19:
        r32.setVisibility(r52);
        DownloadButton r33 = this.downloadButton;
        if (r8 != 2) goto L22;
        int r53 = 0;
    L23:
        r33.setVisibility(r53);
        ImageView r34 = this.themeButton;
        if (r34 == null) goto L29;
        if (r8 != 2) goto L27;
        int r54 = 0;
    L28:
        r34.setVisibility(r54);
        goto L29
    L27:
        r54 = 8;
    L29:
        TimelineView r35 = this.timelineView;
        if (r8 != 2) goto L32;
        int r4 = 0;
    L33:
        r35.setVisibility(r4);
        this.titleTextView.setVisibility(8);
        goto L34
    L32:
        r4 = 8;
        goto L33
    L22:
        r53 = 8;
        goto L23
    L18:
        r52 = 8;
        goto L19
    L14:
        r5 = 8;
    L34:
        PreviewView r36 = this.previewView;
        if (r8 != (-1)) goto L37;
        boolean r55 = true;
    L38:
        r36.setAllowCropping(r55);
        if (r8 == 0) goto L41;
        if (r7 == 0) goto L41;
    L44:
        PhotoFilterView.EnhanceView r37 = this.photoFilterEnhanceView;
        if (r37 == null) goto L51;
        if (r8 == 1) goto L48;
        if (r8 == (-1)) goto L48;
    L49:
        r37.setAllowTouch(r2);
    L48:
        r2 = true;
    L51:
        if (r8 != 3) goto L55;
        CropEditor r38 = this.cropEditor;
        if (r38 == null) goto L60;
        r38.setAppearProgress(1.0f);
    L60:
        if (r8 != 4) goto L65;
        CropInlineEditor r6 = this.cropInlineEditor;
        if (r6 == null) goto L70;
        r6.setAppearProgress(1.0f);
        return;
    L70:
        return;
    L65:
        if (r7 != 4) goto L71;
        CropInlineEditor r72 = this.cropInlineEditor;
        if (r72 == null) goto L72;
        r72.setVisibility(8);
        this.cropInlineEditor.setAppearProgress(0.0f);
        this.cropInlineEditor.stop();
        return;
    L72:
        return;
    L71:
        return;
    L55:
        if (r7 != 3) goto L60;
        CropEditor r39 = this.cropEditor;
        if (r39 == null) goto L60;
        r39.setVisibility(8);
        this.cropEditor.setAppearProgress(0.0f);
        this.cropEditor.stop();
    L41:
        PaintView r310 = this.paintView;
        if (r310 == null) goto L44;
        r310.onAnimationStateChanged(false);
        goto L44
    L37:
        r55 = false;
        goto L38
    }

    private void onSwitchEditModeStart(int r6, int r7) {
        if (r7 != (-1)) goto L24;
        this.backButton.setVisibility(0);
        this.captionEdit.setVisibility(0);
        PaintView r3 = this.paintView;
        if (r3 == null) goto L7;
        r3.clearSelection();
    L7:
        this.downloadButton.setVisibility(0);
        StoryEntry r32 = this.outputEntry;
        if (r32 != null) goto L10;
    L12:
        ImageView r33 = this.themeButton;
        if (r33 == null) goto L15;
        r33.setVisibility(8);
    L15:
        this.titleTextView.setVisibility(0);
        if (this.mode != 1) goto L18;
        this.muteButton.setVisibility(0);
        this.playButton.setVisibility(0);
    L23:
        this.timelineView.setVisibility(0);
        goto L24
    L18:
        StoryEntry r34 = this.outputEntry;
        if (r34 == null) goto L23;
        if (TextUtils.isEmpty(r34.audioPath) == true) goto L23;
        this.muteButton.setVisibility(8);
        this.playButton.setVisibility(0);
        goto L23
    L10:
        if (r32.isRepostMessage == false) goto L12;
        getThemeButton().setVisibility(0);
        updateThemeButtonDrawable(false);
    L24:
        if (r7 != 0) goto L28;
        PaintView r35 = this.paintView;
        if (r35 == null) goto L28;
        r35.setVisibility(0);
    L28:
        if (r7 == 0) goto L30;
        if (r6 == 0) goto L30;
    L33:
        PaintView r36 = this.paintView;
        if (r36 == null) goto L40;
        KeyboardNotifier r37 = r36.keyboardNotifier;
        if (r7 == 0) goto L38;
        boolean r4 = true;
    L39:
        r37.ignore(r4);
        goto L40
    L38:
        r4 = false;
    L40:
        KeyboardNotifier r38 = this.captionEdit.keyboardNotifier;
        if (r7 == (-1)) goto L43;
        boolean r2 = true;
    L44:
        r38.ignore(r2);
        Bulletin.hideVisible();
        if (this.photoFilterView == null) goto L48;
        if (r6 != 1) goto L48;
        applyFilter(null);
    L48:
        PhotoFilterView.EnhanceView r0 = this.photoFilterEnhanceView;
        if (r0 == null) goto L51;
        r0.setAllowTouch(false);
    L51:
        this.muteHint.hide();
        if (r7 != 3) goto L56;
        createCropEditor();
        this.cropEditor.setVisibility(0);
        StoryEntry r02 = this.outputEntry;
        if (r02 == null) goto L61;
        this.cropEditor.setEntry(r02);
    L61:
        if (r7 != 4) goto L64;
        createCropInlineEditor();
        this.cropInlineEditor.setVisibility(0);
        return;
    L64:
        if (r6 != 4) goto L69;
        this.previewView.applyMatrix();
        CropInlineEditor r5 = this.cropInlineEditor;
        if (r5 == null) goto L70;
        r5.disappearStarts();
        return;
    L70:
        return;
    L69:
        return;
    L56:
        if (r6 != 3) goto L61;
        this.previewView.applyMatrix();
        CropEditor r03 = this.cropEditor;
        if (r03 == null) goto L61;
        r03.disappearStarts();
        goto L61
    L43:
        r2 = false;
    L30:
        PaintView r39 = this.paintView;
        if (r39 == null) goto L33;
        r39.onAnimationStateChanged(true);
        goto L33
    }

    private void openPremium() {
        PreviewView r0 = this.previewView;
        if (r0 == null) goto L5;
        r0.updatePauseReason(4, true);
    L5:
        CaptionStory r02 = this.captionEdit;
        if (r02 == null) goto L8;
        r02.hidePeriodPopup();
    L8:
        PremiumFeatureBottomSheet r03 = new PremiumFeatureBottomSheet(new AnonymousClass28(this), 14, false);
        r03.setOnDismissListener(new StoryRecorder$$ExternalSyntheticLambda101(this));
        r03.show();
    }

    private void orderPreviewViews() {
        RenderView r0 = this.paintViewRenderView;
        if (r0 == null) goto L5;
        r0.bringToFront();
    L5:
        View r02 = this.paintViewRenderInputView;
        if (r02 == null) goto L8;
        r02.bringToFront();
    L8:
        View r03 = this.paintViewTextDim;
        if (r03 == null) goto L11;
        r03.bringToFront();
    L11:
        View r04 = this.paintViewEntitiesView;
        if (r04 == null) goto L14;
        r04.bringToFront();
    L14:
        View r05 = this.paintViewSelectionContainerView;
        if (r05 == null) goto L17;
        r05.bringToFront();
    L17:
        TrashView r06 = this.trash;
        if (r06 == null) goto L20;
        r06.bringToFront();
    L20:
        PhotoFilterView.EnhanceView r07 = this.photoFilterEnhanceView;
        if (r07 == null) goto L23;
        r07.bringToFront();
    L23:
        PhotoFilterBlurControl r08 = this.photoFilterViewBlurControl;
        if (r08 == null) goto L26;
        r08.bringToFront();
    L26:
        PhotoFilterCurvesControl r09 = this.photoFilterViewCurvesControl;
        if (r09 == null) goto L29;
        r09.bringToFront();
    L29:
        PreviewHighlightView r010 = this.previewHighlight;
        if (r010 == null) goto L32;
        r010.bringToFront();
    L32:
        RoundVideoRecorder r1 = this.currentRoundRecorder;
        if (r1 == null) goto L36;
        r1.bringToFront();
        return;
    }

    private CharSequence premiumText(String r4) {
        return AndroidUtilities.replaceSingleTag(r4, Theme.key_chat_messageLinkIn, 0, new StoryRecorder$$ExternalSyntheticLambda88(this), this.resourcesProvider);
    }

    private File prepareThumb(StoryEntry r13, boolean r14) {
        if (r13 != null) goto L5;
    L63:
        return null;
    L5:
        if (this.previewView.getWidth() <= 0) goto L63;
        if (this.previewView.getHeight() <= 0) goto L63;
        if (r14 == false) goto L11;
        File r1 = r13.draftThumbFile;
    L12:
        if (r1 == null) goto L15;
        r1.delete();
    L15:
        if (this.collageLayoutView.hasLayout() == false) goto L17;
        View r12 = this.collageLayoutView;
    L18:
        if (r14 == false) goto L20;
        float r2 = 0.33333334f;
    L21:
        int r3 = (int) (r12.getWidth() * r2);
        Bitmap r8 = Bitmap.createBitmap(r3, (int) (r12.getHeight() * r2), Bitmap.Config.RGB_565);
        Canvas r4 = new Canvas(r8);
        r4.save();
        r4.scale(r2, r2);
        AndroidUtilities.makingGlobalBlurBitmap = true;
        r12.draw(r4);
        AndroidUtilities.makingGlobalBlurBitmap = false;
        r4.restore();
        Paint r6 = new Paint(2);
        VideoEditTextureView r7 = this.previewView.getTextureView();
        if (r13.isVideo == true) goto L24;
    L30:
        File r0 = r13.paintBlurFile;
        if (r0 != null) goto L68;
    L36:
        File r02 = r13.paintFile;
        if (r02 != null) goto L64;
    L42:
        PaintView r03 = this.paintView;
        if (r03 != null) goto L45;
    L47:
        Bitmap r22 = Bitmap.createScaledBitmap(r8, 40, 22, true);
        File r10 = StoryEntry.makeCacheFile(this.currentAccount, false);
        if (r14 == false) goto L50;
        StoryEntry r11 = r13;
        boolean r9 = r14;
        Utilities.searchQueue.postRunnable(new StoryRecorder$$ExternalSyntheticLambda102(this, r8, r9, r10, r11));
    L59:
        if (r9 == true) goto L61;
        r11.uploadThumbFile = r10;
    L61:
        r11.thumbBitmap = r22;
        return r10;
    L50:
        r11 = r13;
        r9 = r14;
        Bitmap.CompressFormat r122 = Bitmap.CompressFormat.JPEG;     // Catch: Exception -> L57
        if (r9 == false) goto L54;
        int r132 = 95;
    L55:
        r8.compress(r122, r132, new FileOutputStream(r10));     // Catch: Exception -> L57
        goto L59
    L54:
        r132 = 99;
    L57:
        e = move-exception;
        FileLog.e(e);
        goto L59
    L45:
        if (r03.entitiesView == null) goto L47;
        r4.save();
        r4.scale(r2, r2);
        PaintView r04 = this.paintView;
        r04.drawForThemeToggle = true;
        EntitiesContainerView r05 = r04.entitiesView;
        r05.drawForThumb = true;
        r05.draw(r4);
        PaintView r06 = this.paintView;
        r06.entitiesView.drawForThumb = false;
        r06.drawForThemeToggle = false;
        r4.restore();
        goto L47
    L64:
        Bitmap r07 = BitmapFactory.decodeFile(r02.getPath());     // Catch: Exception -> L40
        r4.save();     // Catch: Exception -> L40
        float r32 = r3 / r07.getWidth();     // Catch: Exception -> L40
        r4.scale(r32, r32);     // Catch: Exception -> L40
        r4.drawBitmap(r07, 0.0f, 0.0f, r6);     // Catch: Exception -> L40
        r4.restore();     // Catch: Exception -> L40
        r07.recycle();     // Catch: Exception -> L40
    L40:
        e = move-exception;
        FileLog.e(e);
        goto L42
    L68:
        Bitmap r08 = BitmapFactory.decodeFile(r0.getPath());     // Catch: Exception -> L34
        r4.save();     // Catch: Exception -> L34
        float r92 = r3 / r08.getWidth();     // Catch: Exception -> L34
        r4.scale(r92, r92);     // Catch: Exception -> L34
        r4.drawBitmap(r08, 0.0f, 0.0f, r6);     // Catch: Exception -> L34
        r4.restore();     // Catch: Exception -> L34
        r08.recycle();     // Catch: Exception -> L34
    L34:
        e = move-exception;
        FileLog.e(e);
        goto L36
    L24:
        if (r13.isRepostMessage == true) goto L30;
        if (r7 == null) goto L30;
        Bitmap r93 = r7.getBitmap();
        Matrix r09 = r7.getTransform(null);
        if (r09 == null) goto L29;
        Matrix r72 = new Matrix(r09);
        r72.postScale(r2, r2);
        r09 = r72;
    L29:
        r4.drawBitmap(r93, r09, r6);
        r93.recycle();
        goto L30
    L20:
        r2 = 1.0f;
        goto L21
    L17:
        r12 = this.previewView;
        goto L18
    L11:
        r1 = r13.uploadThumbFile;
        goto L12
    }

    private void processDone() {
        StoryPrivacyBottomSheet r0 = this.privacySheet;
        if (r0 == null) goto L6;
        r0.dismiss();
        this.privacySheet = null;
    L6:
        if (this.videoError == false) goto L9;
        this.downloadButton.showFailedVideo();
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        PreviewButtons.ShareButtonView r02 = this.previewButtons.shareButton;
        int r1 = -this.shiftDp;
        this.shiftDp = r1;
        AndroidUtilities.shakeViewSpring(r02, r1);
        return;
    L9:
        CaptionStory r03 = this.captionEdit;
        if (r03 != null) goto L12;
    L15:
        StoryEntry r04 = this.outputEntry;
        if (r04 != null) goto L18;
    L21:
        StoriesController.StoryLimit r05 = MessagesController.getInstance(this.currentAccount).storiesController.checkStoryLimit();
        if (r05 != null) goto L24;
    L27:
        this.outputEntry.captionEntitiesAllowed = MessagesController.getInstance(this.currentAccount).storyEntitiesAllowed();
        CaptionStory r06 = this.captionEdit;
        if (r06 != null) goto L30;
    L39:
        StoryEntry r07 = this.outputEntry;
        if (r07.isEdit == false) goto L42;
    L63:
        r07.editedPrivacy = false;
        applyFilter(null);
        upload(true);
        return;
    L42:
        if (r07.botId != 0) goto L63;
        if (this.selectedDialogId == 0) goto L47;
        r07.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.selectedDialogId);
    L47:
        this.previewView.updatePauseReason(3, true);
        StoryPrivacyBottomSheet r08 = new StoryPrivacyBottomSheet(this.activity, this.outputEntry.period, this.resourcesProvider).setValue(this.outputEntry.privacy).setPeer(this.outputEntry.peer).setCanChangePeer(this.canChangePeer).whenDismiss(new StoryRecorder$$ExternalSyntheticLambda67(this)).allowCover(!this.collageLayoutView.hasLayout());
        ArrayList<Integer> r2 = this.selectedEntries;
        if (r2 != null) goto L50;
        int r22 = this.outputEntry.getTotalCount();
    L51:
        this.privacySheet = r08.setCount(r22).isEdit(false).setWarnUsers(getUsersFrom(this.captionEdit.getText())).whenSelectedPeer(new StoryRecorder$$ExternalSyntheticLambda68(this)).whenSelectedAlbums(new StoryRecorder$$ExternalSyntheticLambda69(this)).whenSelectedRules(new StoryRecorder$$ExternalSyntheticLambda70(this), false);
        StoryEntry r09 = this.outputEntry;
        if (r09.isVideo == false) goto L61;
        PreviewView r23 = this.previewView;
        if (r23 != null) goto L56;
    L60:
        this.privacySheet.setCover(this.outputEntry.coverBitmap, new StoryRecorder$$ExternalSyntheticLambda72(this));
        goto L61
    L56:
        if (r09.coverSet == true) goto L60;
        if (this.currentPage == 2) goto L60;
        r09.cover = r23.getCurrentPosition();
        this.previewView.getCoverBitmap(new StoryRecorder$$ExternalSyntheticLambda71(this), new View[]{this.previewView, this.paintViewRenderView, this.paintViewEntitiesView});
    L61:
        this.privacySheet.setOnDismissListener(new StoryRecorder$$ExternalSyntheticLambda73(this));
        this.privacySheet.show();
        return;
    L50:
        r22 = r2.size();
        goto L51
    L30:
        if (this.outputEntry.captionEntitiesAllowed == true) goto L39;
        CharSequence r010 = r06.getText();
        if ((r010 instanceof Spannable) == false) goto L39;
        Spannable r6 = (Spannable) r010;
        if (((TextStyleSpan[]) r6.getSpans(0, r010.length(), TextStyleSpan.class)).length <= 0) goto L36;
    L37:
        BulletinFactory.of(this.windowView, this.resourcesProvider).createSimpleBulletin(R.raw.voip_invite, premiumText(LocaleController.getString(R.string.StoryPremiumFormatting))).show(true);
        CaptionStory r011 = this.captionEdit;
        int r12 = -this.shiftDp;
        this.shiftDp = r12;
        AndroidUtilities.shakeViewSpring(r011, r12);
        return;
    L36:
        if (((URLSpan[]) r6.getSpans(0, r010.length(), URLSpan.class)).length <= 0) goto L39;
    L24:
        if (r05.active(this.currentAccount, getCount()) == false) goto L27;
        showLimitReachedSheet(r05, false);
        return;
    L18:
        if (r04.isEdit == true) goto L27;
        if (r04.botId != 0) goto L27;
    L12:
        if (r03.isCaptionOverLimit() == false) goto L15;
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        AnimatedTextView r012 = this.captionEdit.limitTextView;
        int r13 = -this.shiftDp;
        this.shiftDp = r13;
        AndroidUtilities.shakeViewSpring(r012, r13);
        this.captionEdit.captionLimitToast();
    }

    private boolean requestAudioPermission() {
        Activity r0 = this.activity;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.checkSelfPermission("android.permission.RECORD_AUDIO") == 0) goto L11;
        this.activity.requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 112);
        return false;
    L11:
        return true;
    }

    private void requestCameraPermission(boolean r7) {
        if (this.requestedCameraPermission == false) goto L6;
        if (r7 == true) goto L6;
        return;
    L6:
        this.noCameraPermission = false;
        Activity r0 = this.activity;
        if (r0 == null) goto L20;
        if (r0.checkSelfPermission("android.permission.CAMERA") == 0) goto L11;
        boolean r02 = true;
    L12:
        this.noCameraPermission = r02;
        if (r02 == false) goto L20;
        Drawable r03 = getContext().getResources().getDrawable(R.drawable.story_camera).mutate();
        r03.setColorFilter(new PorterDuffColorFilter(1040187391, PorterDuff.Mode.MULTIPLY));
        CombinedDrawable r3 = new CombinedDrawable(new ColorDrawable(-14540254), r03);
        r3.setIconSize(AndroidUtilities.dp(64.0f), AndroidUtilities.dp(64.0f));
        this.collageLayoutView.setCameraThumb(r3);
        if (this.activity.shouldShowRequestPermissionRationale("android.permission.CAMERA") == false) goto L18;
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTopAnimation(R.raw.permission_request_camera, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PermissionNoCameraWithHint))).setPositiveButton(LocaleController.getString(R.string.PermissionOpenSettings), new StoryRecorder$$ExternalSyntheticLambda86(this)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).create().show();
        return;
    L18:
        this.activity.requestPermissions(new String[]{"android.permission.CAMERA"}, 111);
        this.requestedCameraPermission = true;
        goto L20
    L11:
        r02 = false;
    L20:
        if (this.noCameraPermission == false) goto L22;
        return;
    L22:
        if (CameraController.getInstance().isCameraInitied() == false) goto L25;
        createCameraView();
        return;
    L25:
        CameraController.getInstance().initCamera(new StoryRecorder$$ExternalSyntheticLambda0(this));
    }

    private boolean requestGalleryPermission() {
        if (this.activity == null) goto L8;
        boolean r0 = SystemUtils.isImagesAndVideoPermissionGranted();
        if (r0 == true) goto L7;
        SystemUtils.requestImagesAndVideoPermission(this.activity);
    L7:
        return r0;
    L8:
        return true;
    }

    private void saveCameraFace(boolean r2) {
        MessagesController.getGlobalMainSettings().edit().putBoolean("stories_camera", r2).apply();
    }

    private void saveFrontFaceFlashMode() {
        if (this.frontfaceFlashMode < 0) goto L6;
        MessagesController.getGlobalMainSettings().edit().putFloat("frontflash_warmth", this.flashViews.warmth).putFloat("frontflash_intensity", this.flashViews.intensity).apply();
        return;
    }

    private void saveLastCameraBitmap(Runnable r4) {
        DualCameraView r0 = this.cameraView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getTextureView() == null) goto L13;
        Bitmap r02 = this.cameraView.getTextureView().getBitmap();     // Catch: Throwable -> L9
        Utilities.themeQueue.postRunnable(new StoryRecorder$$ExternalSyntheticLambda56(this, r02, r4));     // Catch: Throwable -> L9
        return;
    L14:
        return;
    }

    private void setAwakeLock(boolean r2) {
        WindowManager.LayoutParams r0 = this.windowLayoutParams;
        if (r2 == false) goto L5;
        r0.flags |= 128;
    L11:
        this.windowManager.updateViewLayout(this.windowView, this.windowLayoutParams);     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        return;
    L5:
        r0.flags &= -129;
        goto L11
    }

    private void setCameraFlashModeIcon(String r4, boolean r5) {
        this.flashButton.clearAnimation();
        DualCameraView r0 = this.cameraView;
        if (r0 == null) goto L7;
        if (r0.isDual() == false) goto L7;
    L8:
        r4 = null;
    L9:
        this.flashButtonMode = r4;
        boolean r02 = false;
        if (r4 != null) goto L13;
        setActionBarButtonVisible(this.flashButton, false, r5);
        return;
    L13:
        int r1 = r4.hashCode();
        if (r1 == 3551) goto L25;
        if (r1 != 109935) goto L18;
        r4.equals("off");
    L27:
        int r42 = R.drawable.media_photo_flash_off2;
        this.flashButton.setContentDescription(LocaleController.getString(R.string.AccDescrCameraFlashOff));
    L28:
        ToggleButton2 r12 = this.flashButton;
        this.flashButtonResId = r42;
        r12.setIcon(r42, false);
        ToggleButton2 r43 = this.flashButton;
        if (this.currentPage == 0) goto L31;
    L37:
        setActionBarButtonVisible(r43, r02, r5);
        return;
    L31:
        if (this.collageListView.isVisible() == true) goto L37;
        if (this.flashButtonMode == null) goto L37;
        if (inCheck() == true) goto L37;
        r02 = true;
        goto L37
    L18:
        if (r1 != 3005871) goto L27;
        if (r4.equals("auto") == false) goto L27;
        r42 = R.drawable.media_photo_flash_auto2;
        this.flashButton.setContentDescription(LocaleController.getString(R.string.AccDescrCameraFlashAuto));
        goto L28
    L25:
        if (r4.equals("on") == false) goto L27;
        r42 = R.drawable.media_photo_flash_on2;
        this.flashButton.setContentDescription(LocaleController.getString(R.string.AccDescrCameraFlashOn));
    L7:
        if (this.animatedRecording == false) goto L9;
        goto L8
    }

    private void setCurrentFlashMode(String r2) {
        DualCameraView r0 = this.cameraView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getCameraSession() != null) goto L8;
        return;
    L8:
        if (this.cameraView.isFrontface() == true) goto L10;
    L15:
        this.cameraView.getCameraSession().setCurrentFlashMode(r2);
        return;
    L10:
        if (this.cameraView.getCameraSession().hasFlashModes() == true) goto L15;
        int r22 = this.frontfaceFlashModes.indexOf(r2);
        if (r22 < 0) goto L18;
        this.frontfaceFlashMode = r22;
        MessagesController.getGlobalMainSettings().edit().putInt("frontflash", this.frontfaceFlashMode).apply();
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setReply() {
        CaptionStory r0 = this.captionEdit;
        if (r0 != null) goto L5;
        return;
    L5:
        StoryEntry r1 = this.outputEntry;
        if (r1 != null) goto L8;
    L23:
        r0.setReply(null, null);
        return;
    L8:
        if (r1.isRepost == false) goto L23;
        TLRPC.Peer r02 = r1.repostPeer;
        boolean r12 = r02 instanceof TLRPC.TL_peerUser;
        int r2 = this.currentAccount;
        if (r12 == false) goto L13;
        String r03 = UserObject.getUserName(MessagesController.getInstance(r2).getUser(Long.valueOf(r02.user_id)));
        StoryEntry r13 = this.outputEntry;
        SpannableStringBuilder r04 = new SpannableStringBuilder(MessageObject.userSpan()).append(" ").append(r03);
        r13.repostPeerName = r04;
    L18:
        String r14 = this.outputEntry.repostCaption;
        boolean r22 = TextUtils.isEmpty(r14);
        String r15 = r14;
        if (r22 == false) goto L21;
        SpannableString r16 = new SpannableString(LocaleController.getString(R.string.Story));
        r16.setSpan(new AnonymousClass15(this), 0, r16.length(), 33);
        r15 = r16;
    L21:
        this.captionEdit.setReply(r04, r15);
        return;
    L13:
        TLRPC.Chat r05 = MessagesController.getInstance(r2).getChat(Long.valueOf(-DialogObject.getPeerDialogId(r02)));
        if (r05 != null) goto L16;
        String r06 = _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        StoryEntry r17 = this.outputEntry;
        r04 = new SpannableStringBuilder(MessageObject.userSpan()).append(" ").append(r06);
        r17.repostPeerName = r04;
        goto L18
    L16:
        r06 = r05.title;
        goto L17
    }

    private void setupBackDispatcher() {
        if (Build.VERSION.SDK_INT < 33) goto L10;
        OnBackInvokedDispatcher r0 = this.windowView.findOnBackInvokedDispatcher();
        if (r0 != null) goto L8;
        return;
    L8:
        r0.registerOnBackInvokedCallback(0, new StoryRecorder$$ExternalSyntheticLambda87(this));
        return;
    }

    private void showDismissEntry() {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        r0.setTitle(LocaleController.getString(R.string.DiscardChanges));
        r0.setMessage(LocaleController.getString(R.string.PhotoEditorDiscardAlert));
        StoryEntry r1 = this.outputEntry;
        if (r1 != null) goto L5;
    L13:
        StoryEntry r12 = this.outputEntry;
        if (r12 != null) goto L16;
    L21:
        int r13 = R.string.Discard;
    L20:
        String r14 = LocaleController.getString(r13);
        r0.setPositiveButton(r14, new StoryRecorder$$ExternalSyntheticLambda66(this));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r02 = r0.create();
        r02.show();
        View r03 = r02.getButton(-1);
        if ((r03 instanceof TextView) == false) goto L26;
        int r2 = Theme.key_text_RedBold;
        ((TextView) r03).setTextColor(Theme.getColor(r2, this.resourcesProvider));
        r03.setBackground(Theme.createRadSelectorDrawable(ColorUtils.setAlphaComponent(Theme.getColor(r2, this.resourcesProvider), 51), 6, 6));
        return;
    L26:
        return;
    L16:
        if (r12.isDraft == false) goto L21;
        if (r12.isEdit == true) goto L21;
        r13 = R.string.StoryDeleteDraft;
        goto L20
    L5:
        if (r1.isEdit == true) goto L13;
        if (r1.isShare == true) goto L13;
        if (r1.isDraft == false) goto L11;
        int r15 = R.string.StoryKeepDraft;
    L12:
        r0.setNeutralButton(LocaleController.getString(r15), new StoryRecorder$$ExternalSyntheticLambda65(this));
        goto L13
    L11:
        r15 = R.string.StorySaveDraft;
        goto L12
    }

    private void showLimitReachedSheet(StoriesController.StoryLimit r9, boolean r10) {
        if (this.shownLimitReached == false) goto L5;
        return;
    L5:
        AnonymousClass27 r1 = new AnonymousClass27(this, new AnonymousClass26(this), this.activity, r9.getLimitReachedType(), this.currentAccount, null);
        r1.setOnDismissListener(new StoryRecorder$$ExternalSyntheticLambda8(this, r10));
        this.previewView.updatePauseReason(7, true);
        this.shownLimitReached = true;
        r1.show();
    }

    private void showPremiumPeriodBulletin(int r6) {
        int r62 = r6 / 3600;
        Bulletin.BulletinWindow.BulletinWindowLayout r0 = Bulletin.BulletinWindow.make(this.activity, new AnonymousClass29(this));
        WindowManager.LayoutParams r1 = r0.getLayout();
        if (r1 == null) goto L5;
        r1.height = -2;
        r1.width = this.containerView.getWidth();
        r1.y = (int) (this.containerView.getY() + AndroidUtilities.dp(56.0f));
        r0.updateLayout();
    L5:
        r0.setTouchable(true);
        BulletinFactory.of(r0, this.resourcesProvider).createSimpleBulletin(R.raw.fire_on, premiumText(LocaleController.formatPluralString("StoryPeriodPremium", r62, new Object[0])), 3).show(true);
    }

    private void showVideoTimer(boolean r4, boolean r5) {
        if (this.videoTimerShown == r4) goto L20;
        this.videoTimerShown = r4;
        VideoTimerView r0 = this.videoTimerView;
        float r1 = 0.0f;
        if (r5 == false) goto L12;
        ViewPropertyAnimator r52 = r0.animate();
        if (r4 == false) goto L10;
        r1 = 1.0f;
    L10:
        r52.alpha(r1).setDuration(350).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).withEndAction(new StoryRecorder$$ExternalSyntheticLambda55(this, r4)).start();
        return;
    L12:
        r0.clearAnimation();
        VideoTimerView r53 = this.videoTimerView;
        if (r4 == false) goto L15;
        r1 = 1.0f;
    L15:
        r53.setAlpha(r1);
        if (r4 == true) goto L19;
        this.videoTimerView.setRecording(false, false);
        return;
    L19:
        return;
    }

    private void showZoomControls(boolean r9, boolean r10) {
        if (this.zoomControlView.getTag() == null) goto L6;
        if (r9 == false) goto L6;
    L8:
        if (r9 == false) goto L32;
        Runnable r92 = this.zoomControlHideRunnable;
        if (r92 == null) goto L12;
        AndroidUtilities.cancelRunOnUIThread(r92);
    L12:
        StoryRecorder$$ExternalSyntheticLambda84 r93 = new StoryRecorder$$ExternalSyntheticLambda84(this);
        this.zoomControlHideRunnable = r93;
        AndroidUtilities.runOnUIThread(r93, 2000);
        return;
    L32:
        return;
    L6:
        if (this.zoomControlView.getTag() != null) goto L14;
        if (r9 == false) goto L8;
    L14:
        AnimatorSet r102 = this.zoomControlAnimation;
        if (r102 == null) goto L17;
        r102.cancel();
    L17:
        ZoomControlView r103 = this.zoomControlView;
        if (r9 == false) goto L20;
        Integer r3 = 1;
    L21:
        r103.setTag(r3);
        AnimatorSet r104 = new AnimatorSet();
        this.zoomControlAnimation = r104;
        r104.setDuration(180);
        if (r9 == false) goto L24;
        this.zoomControlView.setVisibility(0);
    L24:
        AnimatorSet r32 = this.zoomControlAnimation;
        ZoomControlView r4 = this.zoomControlView;
        Property r5 = View.ALPHA;
        if (r9 == false) goto L27;
        float r6 = 1.0f;
    L28:
        r32.playTogether(new Animator[]{ObjectAnimator.ofFloat(r4, r5, new float[]{r6})});
        this.zoomControlAnimation.addListener(new AnonymousClass14(this, r9));
        this.zoomControlAnimation.start();
        if (r9 == false) goto L33;
        StoryRecorder$$ExternalSyntheticLambda85 r94 = new StoryRecorder$$ExternalSyntheticLambda85(this);
        this.zoomControlHideRunnable = r94;
        AndroidUtilities.runOnUIThread(r94, 2000);
        return;
    L33:
        return;
    L27:
        r6 = 0.0f;
        goto L28
    L20:
        r3 = null;
        goto L21
    }

    private void startLive() {
        StoryPrivacyBottomSheet r0 = this.privacySheet;
        if (r0 == null) goto L5;
        r0.dismiss();
        this.privacySheet = null;
    L5:
        StoryPrivacyBottomSheet r02 = new StoryPrivacyBottomSheet(this.activity, 86400, this.resourcesProvider).setLive(true).setValue(this.livePrivacy).setPeer(getLivePeer()).setCanChangePeer(this.canChangePeer).whenDismiss(new StoryRecorder$$ExternalSyntheticLambda81(this)).allowCover(false).setCount(1).isEdit(false).whenSelectedPeer(new StoryRecorder$$ExternalSyntheticLambda82(this)).whenSelectedRules(new StoryRecorder$$ExternalSyntheticLambda83(this), false);
        this.privacySheet = r02;
        r02.show();
    }

    private void switchTo(int r2) {
        if (this.switchingStory == false) goto L5;
        return;
    L5:
        this.storiesSelector.setSelected(r2);
        this.switchingStory = true;
        applyPaintSwitching(new StoryRecorder$$ExternalSyntheticLambda108(this, r2));
    }

    private void updateActionBarButtons(boolean r6) {
        boolean r1 = false;
        if (this.currentPage == 0) goto L5;
    L11:
        boolean r0 = false;
    L12:
        showVideoTimer(r0, r6);
        this.collageButton.setSelected(this.collageLayoutView.hasLayout());
        FlashViews.ImageViewInvertable r02 = this.backButton;
        CollageLayoutButton.CollageLayoutListView r3 = this.collageListView;
        if (r3 != null) goto L15;
    L18:
        boolean r32 = true;
    L19:
        setActionBarButtonVisible(r02, r32, r6);
        ToggleButton2 r03 = this.flashButton;
        if (this.animatedRecording == false) goto L22;
    L32:
        boolean r33 = false;
    L33:
        setActionBarButtonVisible(r03, r33, r6);
        ToggleButton r04 = this.dualButton;
        if (this.animatedRecording == false) goto L36;
    L48:
        boolean r34 = false;
    L49:
        setActionBarButtonVisible(r04, r34, r6);
        CollageLayoutButton r05 = this.collageButton;
        if (this.animatedRecording == false) goto L52;
    L58:
        boolean r35 = false;
    L59:
        setActionBarButtonVisible(r05, r35, r6);
        setActionBarButtonVisible(this.collageRemoveButton, this.collageListView.isVisible(), r6);
        if (this.collageLayoutView.hasLayout() == false) goto L62;
        float r06 = this.collageLayoutView.getFilledProgress();
    L63:
        this.recordControl.setCollageProgress(r06, r6);
        this.removeCollageHint.show(this.collageListView.isVisible());
        animateRecording(this.animatedRecording, r6);
        FlashViews.ImageViewInvertable r62 = this.liveSettingsButton;
        if (this.mode == (-1)) goto L66;
    L68:
        boolean r07 = false;
    L69:
        AndroidUtilities.updateViewShow(r62, r07);
        FlashViews.ImageViewInvertable r63 = this.rotateButton;
        if (this.mode == (-1)) goto L72;
    L74:
        AndroidUtilities.updateViewShow(r63, r1);
        return;
    L72:
        if (this.currentPage != 0) goto L74;
        r1 = true;
        goto L74
    L66:
        if (this.currentPage != 0) goto L68;
        r07 = true;
        goto L69
    L62:
        r06 = 0.0f;
        goto L63
    L52:
        if (this.mode == (-1)) goto L58;
        if (this.currentPage != 0) goto L58;
        if (this.collageListView.isVisible() == true) goto L58;
        r35 = true;
        goto L59
    L36:
        if (this.mode == (-1)) goto L48;
        if (this.currentPage != 0) goto L48;
        DualCameraView r36 = this.cameraView;
        if (r36 == null) goto L48;
        if (r36.dualAvailable() == false) goto L48;
        if (this.collageListView.isVisible() == true) goto L48;
        if (this.collageLayoutView.hasLayout() == true) goto L48;
        r34 = true;
        goto L49
    L22:
        if (this.mode == (-1)) goto L32;
        if (this.currentPage != 0) goto L32;
        if (this.flashButtonMode == null) goto L32;
        if (this.collageListView.isVisible() == true) goto L32;
        if (inCheck() == true) goto L32;
        r33 = true;
        goto L33
    L15:
        if (r3.isVisible() == false) goto L18;
        r32 = false;
        goto L19
    L5:
        if (this.mode != 1) goto L11;
        if (this.collageListView.isVisible() == true) goto L11;
        if (inCheck() == true) goto L11;
        r0 = true;
        goto L12
    }

    private void updateActionBarButtonsOffsets() {
        this.collageRemoveButton.setTranslationX(-0.0f);
        float r1 = (AndroidUtilities.dp(46.0f) * this.collageRemoveButton.getAlpha()) + 0.0f;
        this.dualButton.setTranslationX(-r1);
        float r12 = r1 + (AndroidUtilities.dp(46.0f) * this.dualButton.getAlpha());
        this.collageButton.setTranslationX(-r12);
        float r13 = r12 + (AndroidUtilities.dp(46.0f) * this.collageButton.getAlpha());
        this.flashButton.setTranslationX(-r13);
        float r14 = r13 + (AndroidUtilities.dp(46.0f) * this.flashButton.getAlpha());
        this.backButton.setTranslationX(0.0f);
        this.collageListView.setBounds(((AndroidUtilities.dp(46.0f) * this.backButton.getAlpha()) + 0.0f) + AndroidUtilities.dp(8.0f), r14 + AndroidUtilities.dp(8.0f));
    }

    private void upload(boolean r2) {
        if (this.preparingUpload == false) goto L5;
        return;
    L5:
        this.preparingUpload = true;
        applyPaintInBackground(new StoryRecorder$$ExternalSyntheticLambda99(this, r2));
    }

    private void uploadInternal(boolean r13) {
        if (this.outputEntry != null) goto L6;
        close(true);
        return;
    L6:
        destroyPhotoFilterView();
        StoryRecorder$$ExternalSyntheticLambda106 r0 = new StoryRecorder$$ExternalSyntheticLambda106(this, r13);
        boolean r3 = false;
        if (this.entries != null) goto L15;
        ArrayList<StoryEntry> r2 = this.outputEntry.cutIntoEntries();
        this.entries = r2;
        if (r2 == null) goto L15;
        this.selectedEntries = new ArrayList();
        this.selectedEntriesOrder = new ArrayList();
        int r22 = 0;
    L12:
        if (r22 >= this.entries.size()) goto L15;
        this.selectedEntries.add(Integer.valueOf(r22));
        this.selectedEntriesOrder.add(Integer.valueOf(r22));
        r22 = r22 + 1;
    L15:
        if (this.entries == null) goto L43;
        ArrayList<Integer> r23 = this.selectedEntriesOrder;
        int r4 = r23.size();
        int r5 = 0;
    L17:
        if (r5 >= r4) goto L57;
        Integer r6 = r23.get(r5);
        r5 = r5 + 1;
        Integer r62 = r6;
        if (this.selectedEntries.contains(r62) == false) goto L17;
        StoryEntry r63 = this.entries.get(r62.intValue());
        StoryEntry r7 = this.outputEntry;
        if (r7 != r63) goto L40;
        CharSequence[] r8 = {this.captionEdit.getText()};
        if (MessagesController.getInstance(this.currentAccount).storyEntitiesAllowed() == false) goto L26;
        ArrayList<TLRPC.MessageEntity> r72 = MediaDataController.getInstance(this.currentAccount).getEntities(r8, true);
    L27:
        CharSequence[] r10 = {this.outputEntry.caption};
        if (MessagesController.getInstance(this.currentAccount).storyEntitiesAllowed() == false) goto L30;
        ArrayList<TLRPC.MessageEntity> r9 = MediaDataController.getInstance(this.currentAccount).getEntities(r10, true);
    L31:
        StoryEntry r102 = this.outputEntry;
        if (TextUtils.equals(r102.caption, r8[0]) == true) goto L34;
    L37:
        boolean r73 = true;
    L38:
        r102.editedCaption = r73;
        this.outputEntry.caption = new SpannableString(this.captionEdit.getText());
    L42:
        r0.run(r63);
        goto L17
    L34:
        if (MediaDataController.entitiesEqual(r72, r9) == false) goto L37;
        r73 = false;
        goto L38
    L30:
        r9 = new ArrayList();
        goto L31
    L26:
        r72 = new ArrayList();
        goto L27
    L40:
        if (r63.caption != null) goto L42;
        r7.editedCaption = false;
        r7.caption = new SpannableString(_UrlKt.FRAGMENT_ENCODE_SET);
    L57:
        long r24 = UserConfig.getInstance(this.currentAccount).clientUserId;
        TLRPC.InputPeer r02 = this.outputEntry.peer;
        if (r02 != null) goto L60;
    L62:
        this.outputEntry = null;
        this.wasSend = true;
        this.wasSendPeer = r24;
        this.forceBackgroundVisible = true;
        checkBackgroundVisibility();
        StoryRecorder$$ExternalSyntheticLambda107 r03 = new StoryRecorder$$ExternalSyntheticLambda107(this, r13, r24);
        ClosingViewProvider r12 = this.closingSourceProvider;
        if (r12 == null) goto L65;
        r12.preLayout(r24, r03);
    L66:
        MessagesController.getGlobalMainSettings().edit().putInt("storyhint2", 2).apply();
        return;
    L65:
        r03.run();
        goto L66
    L60:
        if ((r02 instanceof TLRPC.TL_inputPeerSelf) == true) goto L62;
        r24 = DialogObject.getPeerDialogId(r02);
        goto L62
    L43:
        CharSequence[] r42 = {this.captionEdit.getText()};
        if (MessagesController.getInstance(this.currentAccount).storyEntitiesAllowed() == false) goto L46;
        ArrayList<TLRPC.MessageEntity> r25 = MediaDataController.getInstance(this.currentAccount).getEntities(r42, true);
    L47:
        CharSequence[] r64 = {this.outputEntry.caption};
        if (MessagesController.getInstance(this.currentAccount).storyEntitiesAllowed() == false) goto L50;
        ArrayList<TLRPC.MessageEntity> r52 = MediaDataController.getInstance(this.currentAccount).getEntities(r64, true);
    L51:
        StoryEntry r65 = this.outputEntry;
        if (TextUtils.equals(r65.caption, r42[0]) == true) goto L54;
    L55:
        r3 = true;
    L56:
        r65.editedCaption = r3;
        this.outputEntry.caption = new SpannableString(this.captionEdit.getText());
        r0.run(this.outputEntry);
        goto L57
    L54:
        if (MediaDataController.entitiesEqual(r25, r52) == true) goto L56;
    L50:
        r52 = new ArrayList();
        goto L51
    L46:
        r25 = new ArrayList();
        goto L47
    }

    private boolean useDisplayFlashlight() {
        if (this.takingPhoto == false) goto L5;
    L6:
        DualCameraView r0 = this.cameraView;
        if (r0 != null) goto L9;
        return false;
    L9:
        if (r0.isFrontface() == false) goto L19;
        int r02 = this.frontfaceFlashMode;
        if (r02 == 2) goto L15;
        if (r02 == 1) goto L14;
        return false;
    L14:
        if (this.isDark == true) goto L15;
        return false;
    L15:
        return true;
    L19:
        return false;
    L5:
        if (this.takingVideo == true) goto L6;
        return false;
    }

    public void addNotificationObservers() {
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.albumsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesDraftsUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesLimitUpdate);
    }

    public StoryRecorder canChangePeer(boolean r1) {
        this.canChangePeer = r1;
        return this;
    }

    public void close(boolean r8) {
        if (this.isShown == false) goto L39;
        StoryPrivacyBottomSheet r0 = this.privacySheet;
        if (r0 == null) goto L8;
        r0.dismiss();
        this.privacySheet = null;
    L8:
        StoryEntry r02 = this.outputEntry;
        if (r02 != null) goto L11;
    L20:
        this.outputEntry = null;
        Utilities.Callback4<Long, Runnable, Boolean, Long> r03 = this.onClosePrepareListener;
        if (r03 == null) goto L29;
        PreviewView r4 = this.previewView;
        if (r4 == null) goto L29;
        if (this.prepareClosing == false) goto L27;
        return;
    L27:
        this.prepareClosing = true;
        r03.run(Long.valueOf(r4.release()), new StoryRecorder$$ExternalSyntheticLambda6(this, r8), Boolean.valueOf(this.wasSend), Long.valueOf(this.wasSendPeer));
        return;
    L29:
        PreviewView r04 = this.previewView;
        if (r04 == null) goto L33;
        if (r8 == true) goto L33;
        r04.set(null);
    L33:
        animateOpenTo(0.0f, r8, new StoryRecorder$$ExternalSyntheticLambda7(this));
        int r82 = this.openType;
        if (r82 == 1) goto L36;
        if (r82 == 0) goto L36;
    L37:
        removeNotificationObservers();
        return;
    L36:
        this.windowView.setBackgroundColor(0);
        this.previewButtons.appear(false, true);
        goto L37
    L11:
        if (r02.isEditSaved == true) goto L20;
        if (this.wasSend == false) goto L17;
        if (r02.isEdit == false) goto L17;
    L18:
        r02.editedMedia = false;
    L19:
        r02.destroy(false);
    L17:
        if (r02.draftId == 0) goto L19;
    }

    public StoryRecorder closeToWhenSent(ClosingViewProvider r1) {
        this.closingSourceProvider = r1;
        return this;
    }

    public void createCropEditor() {
        if (this.cropEditor == null) goto L5;
        return;
    L5:
        AnonymousClass36 r0 = new AnonymousClass36(this, getContext(), this.previewView, this.resourcesProvider);
        this.cropEditor = r0;
        this.windowView.addView(r0.contentView);
        this.windowView.addView(this.cropEditor);
    }

    public void createCropInlineEditor() {
        if (this.cropInlineEditor == null) goto L5;
        return;
    L5:
        AnonymousClass37 r0 = new AnonymousClass37(this, getContext(), this.previewView, this.resourcesProvider);
        this.cropInlineEditor = r0;
        this.windowView.addView(r0.contentView);
        this.windowView.addView(this.cropInlineEditor);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r5, int r6, Object... r7) {
        boolean r72 = false;
        int r73 = 0;
        r72 = false;
        r72 = false;
        r72 = false;
        if (r5 != NotificationCenter.albumsDidLoad) goto L21;
        RecordControl r52 = this.recordControl;
        if (r52 == null) goto L8;
        r52.updateGalleryImage();
    L8:
        if (this.lastGallerySelectedAlbum != null) goto L10;
        return;
    L10:
        if (MediaController.allMediaAlbums != null) goto L12;
        return;
    L12:
        if (r73 >= MediaController.allMediaAlbums.size()) goto L71;
        MediaController.AlbumEntry r53 = MediaController.allMediaAlbums.get(r73);
        int r62 = r53.bucketId;
        MediaController.AlbumEntry r0 = this.lastGallerySelectedAlbum;
        if (r62 != r0.bucketId) goto L19;
        if (r53.videoOnly != r0.videoOnly) goto L19;
        this.lastGallerySelectedAlbum = r53;
        return;
    L19:
        r73 = r73 + 1;
        goto L12
    L71:
        return;
    L21:
        if (r5 != NotificationCenter.storiesDraftsUpdated) goto L29;
        RecordControl r54 = this.recordControl;
        if (r54 != null) goto L25;
        return;
    L25:
        if (this.showSavedDraftHint == true) goto L65;
        r54.updateGalleryImage();
        return;
    L65:
        return;
    L29:
        if (r5 != NotificationCenter.storiesLimitUpdate) goto L66;
        int r55 = this.currentPage;
        if (r55 != 1) goto L47;
        PreviewButtons r56 = this.previewButtons;
        if (this.videoError == false) goto L35;
    L45:
        r56.setShareEnabled(r72);
        return;
    L35:
        if (this.captionEdit.isCaptionOverLimit() == true) goto L45;
        if (MessagesController.getInstance(this.currentAccount).getStoriesController().hasStoryLimit(getCount()) == false) goto L44;
        StoryEntry r4 = this.outputEntry;
        if (r4 == null) goto L45;
        if (r4.isEdit == true) goto L44;
        if (r4.botId == 0) goto L45;
    L44:
        r72 = true;
        goto L45
    L47:
        if (r55 != 0) goto L67;
        StoriesController.StoryLimit r57 = MessagesController.getInstance(this.currentAccount).getStoriesController().checkStoryLimit();
        if (r57 != null) goto L51;
        return;
    L51:
        if (r57.active(this.currentAccount) == false) goto L69;
        StoryEntry r74 = this.outputEntry;
        if (r74 != null) goto L55;
    L56:
        showLimitReachedSheet(r57, true);
        return;
    L55:
        if (r74.botId == 0) goto L56;
        return;
    L69:
        return;
    L67:
        return;
    }

    public Context getContext() {
        return this.activity;
    }

    public ThanosEffect getThanosEffect() {
        if (ThanosEffect.supports() == true) goto L7;
        return null;
    L7:
        if (this.thanosEffect != null) goto L10;
        WindowView r0 = this.windowView;
        ThanosEffect r1 = new ThanosEffect(getContext(), new StoryRecorder$$ExternalSyntheticLambda116(this));
        this.thanosEffect = r1;
        r0.addView(r1);
    L10:
        return this.thanosEffect;
    }

    public ImageView getThemeButton() {
        if (this.themeButton != null) goto L13;
        RLottieDrawable r1 = new RLottieDrawable(R.raw.sun_outline, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), true, null);
        this.themeButtonDrawable = r1;
        r1.setPlayInDirectionOfCustomEndFrame(true);
        StoryEntry r0 = this.outputEntry;
        if (r0 != null) goto L7;
    L10:
        this.themeButtonDrawable.setCustomEndFrame(0);
        this.themeButtonDrawable.setCurrentFrame(0);
    L11:
        this.themeButtonDrawable.beginApplyLayerColors();
        int r02 = Theme.getColor(Theme.key_chats_menuName, this.resourcesProvider);
        this.themeButtonDrawable.setLayerColor("Sunny", r02);
        this.themeButtonDrawable.setLayerColor("Path 6", r02);
        this.themeButtonDrawable.setLayerColor("Path", r02);
        this.themeButtonDrawable.setLayerColor("Path 5", r02);
        this.themeButtonDrawable.commitApplyLayerColors();
        ImageView r03 = new ImageView(getContext());
        this.themeButton = r03;
        r03.setScaleType(ImageView.ScaleType.CENTER);
        this.themeButton.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.MULTIPLY));
        this.themeButton.setBackground(Theme.createSelectorDrawable(553648127));
        this.themeButton.setOnClickListener(new StoryRecorder$$ExternalSyntheticLambda104(this));
        this.themeButton.setVisibility(8);
        this.themeButton.setImageDrawable(this.themeButtonDrawable);
        this.themeButton.setAlpha(0.0f);
        this.actionBarButtons.addView(this.themeButton, 0, LayoutHelper.createLinear(46, 56, 53));
        goto L13
    L7:
        if (r0.isDark == false) goto L10;
        this.themeButtonDrawable.setCurrentFrame(35);
        this.themeButtonDrawable.setCustomEndFrame(36);
    L13:
        return this.themeButton;
    }

    public void invalidateBlur() {
        CaptionStory r0 = this.captionEdit;
        if (r0 == null) goto L6;
        r0.invalidateBlur();
        return;
    }

    public void navigateTo(int r19, boolean r20) {
        int r3 = this.currentPage;
        if (r19 != r3) goto L5;
        return;
    L5:
        this.currentPage = r19;
        AnimatorSet r4 = this.pageAnimator;
        if (r4 == null) goto L8;
        r4.cancel();
    L8:
        onNavigateStart(r3, r19);
        PreviewButtons r42 = this.previewButtons;
        if (r42 == null) goto L14;
        if (r19 != 1) goto L12;
        boolean r7 = true;
    L13:
        r42.appear(r7, r20);
        goto L14
    L12:
        r7 = false;
    L14:
        if (r19 == 0) goto L16;
    L22:
        boolean r43 = false;
    L23:
        showVideoTimer(r43, r20);
        if (r19 == 1) goto L26;
        this.videoTimeView.show(false, r20);
    L26:
        setActionBarButtonVisible(this.backButton, !this.collageListView.isVisible(), r20);
        ToggleButton2 r44 = this.flashButton;
        if (this.animatedRecording == true) goto L36;
        if (r19 != 0) goto L36;
        if (this.collageListView.isVisible() == true) goto L36;
        if (this.flashButtonMode == null) goto L36;
        if (inCheck() == true) goto L36;
        boolean r72 = true;
    L37:
        setActionBarButtonVisible(r44, r72, r20);
        ToggleButton r45 = this.dualButton;
        if (this.animatedRecording == true) goto L49;
        if (r19 != 0) goto L49;
        DualCameraView r73 = this.cameraView;
        if (r73 == null) goto L49;
        if (r73.dualAvailable() == false) goto L49;
        if (this.collageListView.isVisible() == true) goto L49;
        if (this.collageLayoutView.hasLayout() == true) goto L49;
        boolean r74 = true;
    L50:
        setActionBarButtonVisible(r45, r74, true);
        CollageLayoutButton r46 = this.collageButton;
        if (this.animatedRecording == true) goto L56;
        if (r19 != 0) goto L56;
        if (this.collageListView.isVisible() == true) goto L56;
        boolean r75 = true;
    L57:
        setActionBarButtonVisible(r46, r75, r20);
        updateActionBarButtons(r20);
        float r76 = 0.6f;
        if (r20 == false) goto L196;
        this.pageAnimator = new AnimatorSet();
        ArrayList r2 = new ArrayList();
        DualCameraView r11 = this.cameraView;
        if (r11 == null) goto L66;
        Property r12 = View.ALPHA;
        if (r19 != 0) goto L64;
        float r13 = 1.0f;
    L65:
        r2.add(ObjectAnimator.ofFloat(r11, r12, new float[]{r13}));
        goto L66
    L64:
        r13 = 0.0f;
    L66:
        PreviewView r112 = this.previewView;
        Property r122 = View.ALPHA;
        if (r19 == 1) goto L69;
    L70:
        if (r19 != 2) goto L72;
    L71:
        float r132 = 1.0f;
    L73:
        r2.add(ObjectAnimator.ofFloat(r112, r122, new float[]{r132}));
        CollageLayoutView2 r113 = this.collageLayoutView;
        if (r19 == 0) goto L80;
        if (r19 == 1) goto L77;
    L79:
        float r133 = 0.0f;
    L81:
        r2.add(ObjectAnimator.ofFloat(r113, r122, new float[]{r133}));
        RecordControl r114 = this.recordControl;
        if (r19 != 0) goto L84;
        float r134 = 1.0f;
    L85:
        r2.add(ObjectAnimator.ofFloat(r114, r122, new float[]{r134}));
        RecordControl r115 = this.recordControl;
        Property r135 = View.TRANSLATION_Y;
        if (r19 != 0) goto L89;
        float r15 = 0.0f;
    L90:
        r2.add(ObjectAnimator.ofFloat(r115, r135, new float[]{r15}));
        ScannedLinkPreview r47 = this.qrLinkView;
        if (r19 != 0) goto L93;
        float r116 = 1.0f;
    L94:
        r2.add(ObjectAnimator.ofFloat(r47, r122, new float[]{r116}));
        StoryModeTabs r48 = this.modeSwitcherView;
        if (r19 == 0) goto L97;
    L99:
        float r117 = 0.0f;
    L100:
        r2.add(ObjectAnimator.ofFloat(r48, r122, new float[]{r117}));
        StoryModeTabs r49 = this.modeSwitcherView;
        if (r19 == 0) goto L103;
    L105:
        float r118 = AndroidUtilities.dp(24.0f);
    L106:
        r2.add(ObjectAnimator.ofFloat(r49, r135, new float[]{r118}));
        HintTextView r410 = this.hintTextView;
        if (r19 == 0) goto L109;
    L113:
        float r119 = 0.0f;
    L114:
        r2.add(ObjectAnimator.ofFloat(r410, r122, new float[]{r119}));
        HintTextView r411 = this.collageHintTextView;
        if (r19 == 0) goto L117;
    L121:
        r76 = 0.0f;
    L122:
        r2.add(ObjectAnimator.ofFloat(r411, r122, new float[]{r76}));
        FrameLayout r412 = this.captionContainer;
        if (r19 != 1) goto L129;
        StoryEntry r77 = this.outputEntry;
        float r17 = 0.0f;
        if (r77 != null) goto L127;
    L131:
        float r78 = 1.0f;
    L133:
        r2.add(ObjectAnimator.ofFloat(r412, r122, new float[]{r78}));
        FrameLayout r413 = this.captionContainer;
        if (r19 != 1) goto L139;
        StoryEntry r79 = this.outputEntry;
        if (r79 != null) goto L138;
    L140:
        float r710 = r17;
    L142:
        r2.add(ObjectAnimator.ofFloat(r413, r135, new float[]{r710}));
        CaptionStory r414 = this.captionEdit;
        if (r19 != 2) goto L145;
        float r711 = r17;
    L146:
        r2.add(ObjectAnimator.ofFloat(r414, r122, new float[]{r711}));
        SimpleTextView r415 = this.titleTextView;
        if (r19 == 1) goto L151;
        if (r19 == 2) goto L151;
        float r712 = r17;
    L152:
        r2.add(ObjectAnimator.ofFloat(r415, r122, new float[]{r712}));
        ButtonWithCounterView r416 = this.coverButton;
        if (r19 != 2) goto L155;
        float r713 = 1.0f;
    L156:
        r2.add(ObjectAnimator.ofFloat(r416, r122, new float[]{r713}));
        TimelineView r417 = this.timelineView;
        if (r19 != 1) goto L159;
        float r714 = 1.0f;
    L160:
        r2.add(ObjectAnimator.ofFloat(r417, r122, new float[]{r714}));
        TimelineView r418 = this.coverTimelineView;
        if (r19 != 2) goto L163;
        float r715 = 1.0f;
    L164:
        r2.add(ObjectAnimator.ofFloat(r418, r122, new float[]{r715}));
        RLottieImageView r419 = this.muteButton;
        if (r19 == 1) goto L167;
    L169:
        float r716 = r17;
    L170:
        r2.add(ObjectAnimator.ofFloat(r419, r122, new float[]{r716}));
        PlayPauseButton r420 = this.playButton;
        if (r19 == 1) goto L173;
    L179:
        float r717 = r17;
    L180:
        r2.add(ObjectAnimator.ofFloat(r420, r122, new float[]{r717}));
        DownloadButton r421 = this.downloadButton;
        if (r19 != 1) goto L183;
        float r718 = 1.0f;
    L184:
        r2.add(ObjectAnimator.ofFloat(r421, r122, new float[]{r718}));
        ImageView r422 = this.themeButton;
        if (r422 == null) goto L194;
        if (r19 != 1) goto L192;
        StoryEntry r719 = this.outputEntry;
        if (r719 == null) goto L192;
        if (r719.isRepostMessage == false) goto L192;
        float r9 = 1.0f;
    L193:
        r2.add(ObjectAnimator.ofFloat(r422, r122, new float[]{r9}));
    L192:
        r9 = r17;
    L194:
        r2.add(ObjectAnimator.ofFloat(this.zoomControlView, r122, new float[]{r17}));
        this.pageAnimator.playTogether(r2);
        this.pageAnimator.addListener(new AnonymousClass16(this, r3, r19));
        this.pageAnimator.setDuration(460);
        this.pageAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.pageAnimator.start();
        return;
    L183:
        r718 = r17;
        goto L184
    L173:
        if (this.mode == 1) goto L178;
        StoryEntry r720 = this.outputEntry;
        if (r720 == null) goto L179;
        if (TextUtils.isEmpty(r720.audioPath) == true) goto L179;
    L178:
        r717 = 1.0f;
        goto L180
    L167:
        if (this.mode != 1) goto L169;
        r716 = 1.0f;
        goto L170
    L163:
        r715 = r17;
        goto L164
    L159:
        r714 = r17;
        goto L160
    L155:
        r713 = r17;
    L151:
        r712 = 1.0f;
        goto L152
    L145:
        r711 = 1.0f;
        goto L146
    L138:
        if (r79.botId == 0) goto L140;
    L139:
        if (r19 == 2) goto L140;
        r710 = AndroidUtilities.dp(12.0f);
        goto L142
    L127:
        if (r77.botId == 0) goto L131;
    L130:
        if (r19 == 2) goto L131;
        r78 = r17;
        goto L133
    L129:
        r17 = 0.0f;
        goto L130
    L117:
        if (this.animatedRecording == true) goto L121;
        if (inCheck() == false) goto L121;
    L109:
        if (this.animatedRecording == false) goto L113;
        if (inCheck() == true) goto L113;
        r119 = 1.0f;
        goto L114
    L103:
        if (inCheck() == true) goto L105;
        r118 = 0.0f;
        goto L106
    L97:
        if (inCheck() == true) goto L99;
        r117 = 1.0f;
        goto L100
    L93:
        r116 = 0.0f;
        goto L94
    L89:
        r15 = AndroidUtilities.dp(24.0f);
        goto L90
    L84:
        r134 = 0.0f;
        goto L85
    L77:
        if (r113.hasLayout() == false) goto L79;
    L80:
        r133 = 1.0f;
        goto L81
    L72:
        r132 = 0.0f;
        goto L73
    L69:
        if (this.collageLayoutView.hasLayout() == false) goto L71;
    L196:
        DualCameraView r22 = this.cameraView;
        if (r22 == null) goto L202;
        if (r19 != 0) goto L200;
        float r423 = 1.0f;
    L201:
        r22.setAlpha(r423);
        goto L202
    L200:
        r423 = 0.0f;
    L202:
        PreviewView r23 = this.previewView;
        if (r19 == 1) goto L205;
    L206:
        if (r19 != 2) goto L208;
    L207:
        float r424 = 1.0f;
    L209:
        r23.setAlpha(r424);
        CollageLayoutView2 r24 = this.collageLayoutView;
        if (r19 == 0) goto L216;
        if (r19 == 1) goto L213;
    L215:
        float r425 = 0.0f;
    L217:
        r24.setAlpha(r425);
        RecordControl r25 = this.recordControl;
        if (r19 != 0) goto L220;
        float r426 = 1.0f;
    L221:
        r25.setAlpha(r426);
        RecordControl r26 = this.recordControl;
        if (r19 != 0) goto L224;
        float r5 = 0.0f;
    L225:
        r26.setTranslationY(r5);
        ScannedLinkPreview r27 = this.qrLinkView;
        if (r19 != 0) goto L228;
        float r52 = 1.0f;
    L229:
        r27.setAlpha(r52);
        StoryModeTabs r28 = this.modeSwitcherView;
        if (r19 == 0) goto L232;
    L234:
        float r53 = 0.0f;
    L235:
        r28.setAlpha(r53);
        StoryModeTabs r29 = this.modeSwitcherView;
        if (r19 == 0) goto L238;
    L240:
        float r427 = AndroidUtilities.dp(16.0f);
    L241:
        r29.setTranslationY(r427);
        HintTextView r210 = this.hintTextView;
        if (r19 == 0) goto L244;
    L248:
        float r428 = 0.0f;
    L249:
        r210.setAlpha(r428);
        HintTextView r211 = this.collageHintTextView;
        if (r19 == 0) goto L252;
    L256:
        r76 = 0.0f;
    L257:
        r211.setAlpha(r76);
        FrameLayout r212 = this.captionContainer;
        if (r19 == 1) goto L262;
        if (r19 == 2) goto L262;
        float r429 = 0.0f;
    L263:
        r212.setAlpha(r429);
        FrameLayout r213 = this.captionContainer;
        if (r19 == 1) goto L268;
        if (r19 == 2) goto L268;
        float r430 = AndroidUtilities.dp(12.0f);
    L269:
        r213.setTranslationY(r430);
        CaptionStory r214 = this.captionEdit;
        if (r19 != 2) goto L272;
        float r431 = 0.0f;
    L273:
        r214.setAlpha(r431);
        RLottieImageView r215 = this.muteButton;
        if (r19 == 1) goto L276;
    L278:
        float r432 = 0.0f;
    L279:
        r215.setAlpha(r432);
        PlayPauseButton r216 = this.playButton;
        if (r19 == 1) goto L282;
    L288:
        float r433 = 0.0f;
    L289:
        r216.setAlpha(r433);
        DownloadButton r217 = this.downloadButton;
        if (r19 != 1) goto L292;
        float r434 = 1.0f;
    L293:
        r217.setAlpha(r434);
        ImageView r218 = this.themeButton;
        if (r218 == null) goto L303;
        if (r19 != 1) goto L301;
        StoryEntry r435 = this.outputEntry;
        if (r435 == null) goto L301;
        if (r435.isRepostMessage == false) goto L301;
        float r436 = 1.0f;
    L302:
        r218.setAlpha(r436);
    L301:
        r436 = 0.0f;
    L303:
        TimelineView r219 = this.timelineView;
        if (r19 != 1) goto L306;
        float r437 = 1.0f;
    L307:
        r219.setAlpha(r437);
        TimelineView r220 = this.coverTimelineView;
        if (r19 != 2) goto L310;
        float r438 = 1.0f;
    L311:
        r220.setAlpha(r438);
        SimpleTextView r221 = this.titleTextView;
        if (r19 == 1) goto L316;
        if (r19 == 2) goto L316;
        float r439 = 0.0f;
    L317:
        r221.setAlpha(r439);
        ButtonWithCounterView r222 = this.coverButton;
        if (r19 != 2) goto L320;
        float r92 = 1.0f;
    L321:
        r222.setAlpha(r92);
        onNavigateEnd(r3, r19);
        return;
    L320:
        r92 = 0.0f;
    L316:
        r439 = 1.0f;
        goto L317
    L310:
        r438 = 0.0f;
        goto L311
    L306:
        r437 = 0.0f;
        goto L307
    L292:
        r434 = 0.0f;
        goto L293
    L282:
        if (this.mode == 1) goto L287;
        StoryEntry r440 = this.outputEntry;
        if (r440 == null) goto L288;
        if (TextUtils.isEmpty(r440.audioPath) == true) goto L288;
    L287:
        r433 = 1.0f;
        goto L289
    L276:
        if (this.mode != 1) goto L278;
        r432 = 1.0f;
        goto L279
    L272:
        r431 = 1.0f;
    L268:
        r430 = 0.0f;
    L262:
        r429 = 1.0f;
        goto L263
    L252:
        if (this.animatedRecording == true) goto L256;
        if (inCheck() == false) goto L256;
    L244:
        if (this.animatedRecording == false) goto L248;
        if (inCheck() == true) goto L248;
        r428 = 1.0f;
        goto L249
    L238:
        if (inCheck() == true) goto L240;
        r427 = 0.0f;
        goto L241
    L232:
        if (inCheck() == true) goto L234;
        r53 = 1.0f;
        goto L235
    L228:
        r52 = 0.0f;
        goto L229
    L224:
        r5 = AndroidUtilities.dp(16.0f);
        goto L225
    L220:
        r426 = 0.0f;
        goto L221
    L213:
        if (r24.hasLayout() == false) goto L215;
    L216:
        r425 = 1.0f;
        goto L217
    L208:
        r424 = 0.0f;
        goto L209
    L205:
        if (this.collageLayoutView.hasLayout() == false) goto L207;
    L56:
        r75 = false;
    L49:
        r74 = false;
    L36:
        r72 = false;
        goto L37
    L16:
        if (this.mode != 1) goto L22;
        if (this.collageListView.isVisible() == true) goto L22;
        if (inCheck() == true) goto L22;
        r43 = true;
        goto L23
    }

    public void navigateToPreviewWithPlayerAwait(Runnable r7, long r8) {
        navigateToPreviewWithPlayerAwait(r7, r8, 800);
    }

    public void navigateToPreviewWithPlayerAwait(Runnable r4, long r5, long r7) {
        if (this.awaitingPlayer == false) goto L5;
        return;
    L5:
        if (this.outputEntry == null) goto L13;
        Runnable r0 = this.afterPlayerAwait;
        if (r0 == null) goto L10;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L10:
        this.previewAlreadySet = true;
        this.awaitingPlayer = true;
        this.afterPlayerAwait = new StoryRecorder$$ExternalSyntheticLambda105(this, r4);
        this.previewView.setAlpha(0.0f);
        this.previewView.setVisibility(0);
        this.previewView.set(this.outputEntry, this.afterPlayerAwait, r5);
        this.previewView.setupAudio(this.outputEntry, false);
        AndroidUtilities.runOnUIThread(this.afterPlayerAwait, r7);
        return;
    }

    public boolean onBackPressed() {
        ValueAnimator r0 = this.openCloseAnimator;
        if (r0 != null) goto L5;
    L7:
        CaptionStory r02 = this.captionEdit;
        if (r02 == null) goto L13;
        if (r02.stopRecording() == false) goto L13;
        return false;
    L13:
        if (this.takingVideo == false) goto L17;
        this.recordControl.stopRecording();
        return false;
    L17:
        if (this.takingPhoto == false) goto L20;
        return false;
    L20:
        if (this.captionEdit.onBackPressed() == false) goto L23;
        return false;
    L23:
        if (this.storiesSelector.onBackPressed() == false) goto L25;
        return false;
    L25:
        GalleryListView r03 = this.galleryListView;
        if (r03 == null) goto L33;
        if (r03.onBackPressed() == false) goto L30;
        return false;
    L30:
        animateGalleryListView(false);
        this.lastGallerySelectedAlbum = null;
        return false;
    L33:
        if (this.currentEditMode != 0) goto L40;
        PaintView r04 = this.paintView;
        if (r04 == null) goto L40;
        if (r04.onBackPressed() == false) goto L40;
        return false;
    L40:
        if (this.currentEditMode <= (-1)) goto L44;
        switchToEditMode(-1, true);
        return false;
    L44:
        if (this.currentPage != 0) goto L50;
        if (this.collageLayoutView.hasContent() == false) goto L50;
        this.collageLayoutView.clear(true);
        updateActionBarButtons(true);
        return false;
    L50:
        if (this.currentPage != 1) goto L111;
        StoryEntry r05 = this.outputEntry;
        if (r05 == null) goto L58;
        if (r05.isRepost == true) goto L111;
        if (r05.isRepostMessage == true) goto L111;
    L58:
        if (this.isReposting == true) goto L111;
        if (r05 != null) goto L61;
    L70:
        PaintView r06 = this.paintView;
        if (r06 == null) goto L76;
        if (r06.onBackPressed() == false) goto L76;
        return false;
    L76:
        if (this.botId != 0) goto L106;
        if (this.fromGallery == false) goto L90;
        if (this.collageLayoutView.hasLayout() == true) goto L90;
        PaintView r07 = this.paintView;
        if (r07 != null) goto L84;
    L85:
        StoryEntry r08 = this.outputEntry;
        if (r08 != null) goto L88;
    L91:
        StoryEntry r09 = this.outputEntry;
        if (r09 == null) goto L100;
        if (r09.isEdit == false) goto L100;
        if (r09.isRepost == true) goto L106;
        if (r09.isRepostMessage == true) goto L106;
    L100:
        if (this.isReposting == true) goto L106;
        if (r09 != null) goto L103;
    L104:
        navigateTo(0, true);
    L109:
        return false;
    L103:
        if (r09.isShare == true) goto L106;
    L88:
        if (r08.filterFile == null) goto L91;
    L84:
        if (r07.hasChanges() == false) goto L85;
    L90:
        if (this.previewButtons.isShareEnabled() == false) goto L91;
    L106:
        if (this.botId == 0) goto L108;
        close(true);
        goto L109
    L108:
        showDismissEntry();
        goto L109
    L61:
        if (r05.isEdit == false) goto L70;
        PaintView r010 = this.paintView;
        if (r010 != null) goto L65;
    L66:
        StoryEntry r011 = this.outputEntry;
        if (r011.editedMedia == true) goto L70;
        if (r011.editedCaption == false) goto L111;
    L65:
        if (r010.hasChanges() == true) goto L70;
    L111:
        if (this.currentPage != 2) goto L118;
        StoryEntry r012 = this.outputEntry;
        if (r012 == null) goto L118;
        if (r012.isEditingCover == true) goto L118;
        processDone();
        navigateTo(1, true);
        return false;
    L118:
        close(true);
        return true;
    L5:
        if (r0.isRunning() == false) goto L7;
        return false;
    }

    public void open(SourceView r2) {
        open(r2, true);
    }

    public void open(SourceView r10, boolean r11) {
        if (this.isShown == false) goto L6;
        return;
    L6:
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L9;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L9:
        int r0 = 0;
        this.isReposting = false;
        this.prepareClosing = false;
        this.forceBackgroundVisible = false;
        this.videoTextureHolder.active = false;
        if (this.windowManager == null) goto L16;
        WindowView r1 = this.windowView;
        if (r1 == null) goto L16;
        if (r1.getParent() != null) goto L16;
        AndroidUtilities.setPreferredMaxRefreshRate(this.windowManager, this.windowView, this.windowLayoutParams);
        this.windowManager.addView(this.windowView, this.windowLayoutParams);
        setupBackDispatcher();
    L16:
        this.collageLayoutView.setCameraThumb(getCameraThumb());
        if (this.botId != 0) goto L23;
        StoriesController.StoryLimit r12 = MessagesController.getInstance(this.currentAccount).getStoriesController().checkStoryLimit();
        if (r12 == null) goto L23;
        if (r12.active(this.currentAccount) == false) goto L23;
        showLimitReachedSheet(r12, true);
    L23:
        navigateTo(0, false);
        switchToEditMode(-1, false);
        if (r10 == null) goto L26;
        this.fromSourceView = r10;
        this.openType = r10.type;
        this.fromRect.set(r10.screenRect);
        this.fromRounding = r10.rounding;
        this.fromSourceView.hide();
    L27:
        this.containerView.updateBackground();
        FrameLayout r102 = this.previewContainer;
        int r5 = this.openType;
        if (r5 == 1) goto L32;
        if (r5 == 0) goto L32;
        r0 = -14737633;
    L32:
        r102.setBackgroundColor(r0);
        this.containerView.setTranslationX(0.0f);
        this.containerView.setTranslationY(0.0f);
        this.containerView.setTranslationY2(0.0f);
        this.containerView.setScaleX(1.0f);
        this.containerView.setScaleY(1.0f);
        this.dismissProgress = 0.0f;
        AndroidUtilities.lockOrientation(this.activity, 1);
        animateOpenTo(1.0f, r11, new StoryRecorder$$ExternalSyntheticLambda10(this));
        addNotificationObservers();
        this.botId = 0;
        this.botLang = _UrlKt.FRAGMENT_ENCODE_SET;
        this.botEdit = null;
        return;
    L26:
        this.openType = 0;
        this.fromRect.set(0.0f, AndroidUtilities.dp(100.0f), AndroidUtilities.displaySize.x, AndroidUtilities.dp(100.0f) + AndroidUtilities.displaySize.y);
        this.fromRounding = AndroidUtilities.dp(8.0f);
        goto L27
    }

    public void openBot(long r2, String r4, SourceView r5) {
        this.botId = r2;
        this.botLang = r4;
        this.botEdit = null;
        open(r5, true);
        this.botId = r2;
        this.botLang = r4;
    }

    public void openBotEntry(long r5, String r7, StoryEntry r8, SourceView r9) {
        if (this.isShown == true) goto L32;
        if (r8 != null) goto L7;
        return;
    L7:
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L10;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L10:
        this.botId = r5;
        this.botLang = r7;
        this.isReposting = false;
        this.prepareClosing = false;
        this.forceBackgroundVisible = false;
        if (this.windowManager == null) goto L17;
        WindowView r1 = this.windowView;
        if (r1 == null) goto L17;
        if (r1.getParent() != null) goto L17;
        AndroidUtilities.setPreferredMaxRefreshRate(this.windowManager, this.windowView, this.windowLayoutParams);
        this.windowManager.addView(this.windowView, this.windowLayoutParams);
        setupBackDispatcher();
    L17:
        this.outputEntry = r8;
        r8.botId = r5;
        r8.botLang = r7;
        this.mode = r8.isVideo ? 1 : 0;
        this.videoTextureHolder.active = false;
        if (r9 == null) goto L20;
        this.fromSourceView = r9;
        this.openType = r9.type;
        this.fromRect.set(r9.screenRect);
        this.fromRounding = r9.rounding;
        this.fromSourceView.hide();
    L21:
        this.containerView.updateBackground();
        FrameLayout r6 = this.previewContainer;
        int r72 = this.openType;
        if (r72 == 1) goto L26;
        if (r72 == 0) goto L26;
        int r73 = -14737633;
    L27:
        r6.setBackgroundColor(r73);
        this.containerView.setTranslationX(0.0f);
        this.containerView.setTranslationY(0.0f);
        this.containerView.setTranslationY2(0.0f);
        this.containerView.setScaleX(1.0f);
        this.containerView.setScaleY(1.0f);
        this.dismissProgress = 0.0f;
        AndroidUtilities.lockOrientation(this.activity, 1);
        StoryEntry r52 = this.outputEntry;
        if (r52 == null) goto L30;
        this.captionEdit.setText(r52.caption);
    L30:
        navigateTo(1, false);
        switchToEditMode(-1, false);
        this.previewButtons.appear(false, false);
        this.previewButtons.appear(true, true);
        animateOpenTo(1.0f, true, new StoryRecorder$$ExternalSyntheticLambda10(this));
        addNotificationObservers();
        return;
    L26:
        r73 = 0;
        goto L27
    L20:
        this.openType = 0;
        this.fromRect.set(0.0f, AndroidUtilities.dp(100.0f), AndroidUtilities.displaySize.x, AndroidUtilities.dp(100.0f) + AndroidUtilities.displaySize.y);
        this.fromRounding = AndroidUtilities.dp(8.0f);
        goto L21
    }

    public void openEdit(SourceView r7, StoryEntry r8, long r9, boolean r11) {
        if (this.isShown == false) goto L6;
        return;
    L6:
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L9;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L9:
        this.isReposting = false;
        this.prepareClosing = false;
        this.forceBackgroundVisible = false;
        if (this.windowManager == null) goto L16;
        WindowView r1 = this.windowView;
        if (r1 == null) goto L16;
        if (r1.getParent() != null) goto L16;
        AndroidUtilities.setPreferredMaxRefreshRate(this.windowManager, this.windowView, this.windowLayoutParams);
        this.windowManager.addView(this.windowView, this.windowLayoutParams);
        setupBackDispatcher();
    L16:
        this.outputEntry = r8;
        int r12 = 1;
        if (r8 != null) goto L19;
    L21:
        int r82 = 0;
    L22:
        this.mode = r82;
        this.videoTextureHolder.active = false;
        if (r7 == null) goto L25;
        this.fromSourceView = r7;
        this.openType = r7.type;
        this.fromRect.set(r7.screenRect);
        this.fromRounding = r7.rounding;
        this.fromSourceView.hide();
    L26:
        this.containerView.updateBackground();
        FrameLayout r72 = this.previewContainer;
        int r2 = this.openType;
        if (r2 == 1) goto L31;
        if (r2 == 0) goto L31;
        int r22 = -14737633;
    L32:
        r72.setBackgroundColor(r22);
        this.containerView.setTranslationX(0.0f);
        this.containerView.setTranslationY(0.0f);
        this.containerView.setTranslationY2(0.0f);
        this.containerView.setScaleX(1.0f);
        this.containerView.setScaleY(1.0f);
        this.dismissProgress = 0.0f;
        AndroidUtilities.lockOrientation(this.activity, 1);
        StoryEntry r73 = this.outputEntry;
        if (r73 == null) goto L35;
        this.captionEdit.setText(r73.caption);
    L35:
        navigateToPreviewWithPlayerAwait(new StoryRecorder$$ExternalSyntheticLambda98(this, r11), r9);
        if (this.outputEntry.isEditingCover == false) goto L38;
        r12 = 2;
    L38:
        navigateTo(r12, false);
        switchToEditMode(-1, false);
        this.previewButtons.appear(false, false);
        addNotificationObservers();
        this.botId = 0;
        this.botLang = _UrlKt.FRAGMENT_ENCODE_SET;
        this.botEdit = null;
        return;
    L31:
        r22 = 0;
        goto L32
    L25:
        this.openType = 0;
        this.fromRect.set(0.0f, AndroidUtilities.dp(100.0f), AndroidUtilities.displaySize.x, AndroidUtilities.dp(100.0f) + AndroidUtilities.displaySize.y);
        this.fromRounding = AndroidUtilities.dp(8.0f);
        goto L26
    L19:
        if (r8.isVideo == false) goto L21;
        r82 = 1;
        goto L22
    }

    public void openForward(SourceView r7, StoryEntry r8, long r9, boolean r11) {
        if (this.isShown == false) goto L6;
        return;
    L6:
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L9;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L9:
        this.isReposting = false;
        this.prepareClosing = false;
        this.forceBackgroundVisible = false;
        if (this.windowManager == null) goto L16;
        WindowView r1 = this.windowView;
        if (r1 == null) goto L16;
        if (r1.getParent() != null) goto L16;
        AndroidUtilities.setPreferredMaxRefreshRate(this.windowManager, this.windowView, this.windowLayoutParams);
        this.windowManager.addView(this.windowView, this.windowLayoutParams);
        setupBackDispatcher();
    L16:
        this.outputEntry = r8;
        StoryPrivacySelector.applySaved(this.currentAccount, r8);
        StoryEntry r82 = this.outputEntry;
        if (r82 != null) goto L19;
    L21:
        int r83 = 0;
    L22:
        this.mode = r83;
        this.videoTextureHolder.active = false;
        if (r7 == null) goto L25;
        this.fromSourceView = r7;
        this.openType = r7.type;
        this.fromRect.set(r7.screenRect);
        this.fromRounding = r7.rounding;
        this.fromSourceView.hide();
    L26:
        this.containerView.updateBackground();
        FrameLayout r72 = this.previewContainer;
        int r2 = this.openType;
        if (r2 == 1) goto L31;
        if (r2 == 0) goto L31;
        int r22 = -14737633;
    L32:
        r72.setBackgroundColor(r22);
        this.containerView.setTranslationX(0.0f);
        this.containerView.setTranslationY(0.0f);
        this.containerView.setTranslationY2(0.0f);
        this.containerView.setScaleX(1.0f);
        this.containerView.setScaleY(1.0f);
        this.dismissProgress = 0.0f;
        AndroidUtilities.lockOrientation(this.activity, 1);
        StoryEntry r73 = this.outputEntry;
        if (r73 == null) goto L35;
        this.captionEdit.setText(r73.caption);
    L35:
        navigateToPreviewWithPlayerAwait(new StoryRecorder$$ExternalSyntheticLambda124(this, r11), r9);
        this.previewButtons.appear(true, false);
        navigateTo(1, false);
        switchToEditMode(-1, false);
        addNotificationObservers();
        this.botId = 0;
        this.botLang = _UrlKt.FRAGMENT_ENCODE_SET;
        this.botEdit = null;
        return;
    L31:
        r22 = 0;
        goto L32
    L25:
        this.openType = 0;
        this.fromRect.set(0.0f, AndroidUtilities.dp(100.0f), AndroidUtilities.displaySize.x, AndroidUtilities.dp(100.0f) + AndroidUtilities.displaySize.y);
        this.fromRounding = AndroidUtilities.dp(8.0f);
        goto L26
    L19:
        if (r82.isVideo == false) goto L21;
        r83 = 1;
        goto L22
    }

    public void openRepost(SourceView r9, StoryEntry r10) {
        if (this.isShown == false) goto L6;
        return;
    L6:
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L9;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L9:
        this.isReposting = true;
        this.prepareClosing = false;
        this.forceBackgroundVisible = false;
        if (this.windowManager == null) goto L16;
        WindowView r2 = this.windowView;
        if (r2 == null) goto L16;
        if (r2.getParent() != null) goto L16;
        AndroidUtilities.setPreferredMaxRefreshRate(this.windowManager, this.windowView, this.windowLayoutParams);
        this.windowManager.addView(this.windowView, this.windowLayoutParams);
        setupBackDispatcher();
    L16:
        this.outputEntry = r10;
        StoryPrivacySelector.applySaved(this.currentAccount, r10);
        StoryEntry r102 = this.outputEntry;
        if (r102 != null) goto L19;
    L21:
        int r22 = 0;
    L22:
        this.mode = r22;
        PreviewView.TextureViewHolder r3 = this.videoTextureHolder;
        if (r102 != null) goto L25;
    L28:
        boolean r103 = false;
    L29:
        r3.active = r103;
        if (this.botId != 0) goto L37;
        StoriesController.StoryLimit r104 = MessagesController.getInstance(this.currentAccount).getStoriesController().checkStoryLimit();
        if (r104 == null) goto L37;
        if (r104.active(this.currentAccount) == false) goto L37;
        showLimitReachedSheet(r104, true);
    L37:
        if (r9 == null) goto L39;
        this.fromSourceView = r9;
        this.openType = r9.type;
        this.fromRect.set(r9.screenRect);
        this.fromRounding = r9.rounding;
        this.fromSourceView.hide();
    L40:
        this.containerView.updateBackground();
        FrameLayout r92 = this.previewContainer;
        int r23 = this.openType;
        if (r23 == 1) goto L45;
        if (r23 == 0) goto L45;
        int r24 = -14737633;
    L46:
        r92.setBackgroundColor(r24);
        this.containerView.setTranslationX(0.0f);
        this.containerView.setTranslationY(0.0f);
        this.containerView.setTranslationY2(0.0f);
        this.containerView.setScaleX(1.0f);
        this.containerView.setScaleY(1.0f);
        this.dismissProgress = 0.0f;
        AndroidUtilities.lockOrientation(this.activity, 1);
        StoryEntry r93 = this.outputEntry;
        if (r93 == null) goto L49;
        this.captionEdit.setText(r93.caption);
    L49:
        this.previewButtons.appear(true, false);
        navigateTo(1, false);
        switchToEditMode(-1, false);
        animateOpenTo(1.0f, true, new StoryRecorder$$ExternalSyntheticLambda10(this));
        addNotificationObservers();
        this.botId = 0;
        this.botLang = _UrlKt.FRAGMENT_ENCODE_SET;
        this.botEdit = null;
        return;
    L45:
        r24 = 0;
        goto L46
    L39:
        this.openType = 0;
        this.fromRect.set(0.0f, AndroidUtilities.dp(100.0f), AndroidUtilities.displaySize.x, AndroidUtilities.dp(100.0f) + AndroidUtilities.displaySize.y);
        this.fromRounding = AndroidUtilities.dp(8.0f);
        goto L40
    L25:
        if (r102.isRepostMessage == false) goto L28;
        if (r22 != 1) goto L28;
        r103 = true;
        goto L29
    L19:
        if (r102.isVideo == false) goto L21;
        r22 = 1;
        goto L22
    }

    public void removeNotificationObservers() {
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.albumsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesDraftsUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesLimitUpdate);
    }

    public void replaceSourceView(SourceView r6) {
        int r0 = 0;
        if (r6 == null) goto L5;
        this.fromSourceView = r6;
        this.openType = r6.type;
        this.fromRect.set(r6.screenRect);
        this.fromRounding = r6.rounding;
    L6:
        FrameLayout r62 = this.previewContainer;
        int r5 = this.openType;
        if (r5 == 1) goto L11;
        if (r5 == 0) goto L11;
        r0 = -14737633;
    L11:
        r62.setBackgroundColor(r0);
        return;
    L5:
        this.fromSourceView = null;
        this.openType = 0;
        this.fromRect.set(0.0f, AndroidUtilities.dp(100.0f), AndroidUtilities.displaySize.x, AndroidUtilities.dp(100.0f) + AndroidUtilities.displaySize.y);
        this.fromRounding = AndroidUtilities.dp(8.0f);
        goto L6
    }

    public StoryRecorder selectedPeerId(long r2) {
        this.selectedDialogId = r2;
        CaptionStory r0 = this.captionEdit;
        if (r0 == null) goto L5;
        r0.setDialogId(r2);
    L5:
        return this;
    }

    public void setActionBarButtonVisible(View r4, boolean r5, boolean r6) {
        if (r4 != null) goto L4;
        return;
    L4:
        float r0 = 0.0f;
        int r2 = 0;
        if (r6 == false) goto L11;
        r4.setVisibility(0);
        ViewPropertyAnimator r62 = r4.animate();
        if (r5 == false) goto L9;
        r0 = 1.0f;
    L9:
        r62.alpha(r0).setUpdateListener(new AnonymousClass35(this)).setListener(new AnonymousClass34(this, r5, r4)).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        return;
    L11:
        r4.animate().cancel();
        if (r5 == true) goto L15;
        r2 = 8;
    L15:
        r4.setVisibility(r2);
        if (r5 == false) goto L18;
        r0 = 1.0f;
    L18:
        r4.setAlpha(r0);
        updateActionBarButtonsOffsets();
    }

    public void setIconMuted(boolean r8, boolean r9) {
        if (this.muteButtonDrawable != null) goto L5;
        RLottieDrawable r1 = new RLottieDrawable(R.raw.media_mute_unmute, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), true, null);
        this.muteButtonDrawable = r1;
        r1.multiplySpeed(1.5f);
    L5:
        this.muteButton.setAnimation(this.muteButtonDrawable);
        int r0 = 20;
        if (r9 == true) goto L13;
        RLottieDrawable r7 = this.muteButtonDrawable;
        if (r8 == true) goto L11;
        r0 = 0;
    L11:
        r7.setCurrentFrame(r0, false);
        return;
    L13:
        RLottieDrawable r92 = this.muteButtonDrawable;
        if (r8 == false) goto L21;
        if (r92.getCurrentFrame() <= 20) goto L18;
        this.muteButtonDrawable.setCurrentFrame(0, false);
    L18:
        this.muteButtonDrawable.setCustomEndFrame(20);
        this.muteButtonDrawable.start();
        return;
    L21:
        if (r92.getCurrentFrame() != 0) goto L23;
        return;
    L23:
        if (this.muteButtonDrawable.getCurrentFrame() >= 43) goto L28;
        this.muteButtonDrawable.setCustomEndFrame(43);
        this.muteButtonDrawable.start();
        return;
    }

    public StoryRecorder setMode(int r3) {
        if (this.mode != r3) goto L5;
        return this;
    L5:
        this.mode = r3;
        StoryModeTabs r0 = this.modeSwitcherView;
        if (r0 == null) goto L9;
        r0.switchMode(r3);
    L9:
        if (r3 != 1) goto L11;
        boolean r32 = true;
    L12:
        showVideoTimer(r32, true);
        CollageLayoutButton.CollageLayoutListView r33 = this.collageListView;
        if (r33 == null) goto L15;
        r33.setVisible(false, true);
    L15:
        updateActionBarButtons(false);
        return this;
    L11:
        r32 = false;
        goto L12
    }

    public void setOnFullyOpenListener(Runnable r1) {
        this.onFullyOpenListener = r1;
    }

    public void setOnPrepareCloseListener(Utilities.Callback4<Long, Runnable, Boolean, Long> r1) {
        this.onClosePrepareListener = r1;
    }

    public void switchToEditMode(int r2, boolean r3) {
        switchToEditMode(r2, false, r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v1 */
    /* JADX WARN: Type inference failed for: r20v2 */
    /* JADX WARN: Type inference failed for: r20v3 */
    public void switchToEditMode(int r19, boolean r20, boolean r21) {
        if (this.currentEditMode != r19) goto L7;
        if (r20 == true) goto L7;
        return;
    L7:
        if (r19 == (-1)) goto L13;
        CaptionStory r4 = this.captionEdit;
        if (r4 == null) goto L13;
        if (r4.isRecording() == false) goto L13;
        return;
    L13:
        int r42 = this.currentEditMode;
        this.currentEditMode = r19;
        AnimatorSet r5 = this.editModeAnimator;
        if (r5 == null) goto L16;
        r5.cancel();
        this.editModeAnimator = null;
    L16:
        if (r42 == r19) goto L18;
        onSwitchEditModeStart(r42, r19);
    L18:
        PreviewButtons r52 = this.previewButtons;
        boolean r8 = true;
        float r10 = 0.0f;
        if (r19 == (-1)) goto L22;
        if (r19 == 2) goto L22;
    L24:
        boolean r11 = false;
    L25:
        r52.appear(r11, r21);
        ArrayList r53 = new ArrayList();
        PhotoFilterView r112 = this.photoFilterView;
        if (r112 != null) goto L29;
        if (r19 != 1) goto L29;
        boolean r12 = true;
    L30:
        float r13 = 1.0f;
        if (r19 != 1) goto L39;
        createFilterPhotoView();
        PhotoFilterView r113 = this.photoFilterView;
        this.previewTouchable = r113;
        if (r113 == null) goto L35;
        FrameLayout r114 = r113.getToolsView();
    L36:
        if (r114 == null) goto L38;
        r114.setAlpha(0.0f);
        r114.setVisibility(0);
        r53.add(ObjectAnimator.ofFloat(r114, View.TRANSLATION_Y, new float[]{0.0f}));
        r53.add(ObjectAnimator.ofFloat(r114, View.ALPHA, new float[]{1.0f}));
    L38:
        ?? r202 = 0;
    L43:
        if (r19 != 0) goto L46;
        createPhotoPaintView();
        this.previewTouchable = this.paintView;
        FlashViews.ImageViewInvertable r6 = this.backButton;
        Property r15 = View.ALPHA;
        float[] r7 = new float[1];
        r7[r202] = 0.0f;
        r53.add(ObjectAnimator.ofFloat(r6, r15, r7));
        r53.add(ObjectAnimator.ofFloat(this.paintView.getTopLayout(), r15, new float[]{0.0f, 1.0f}));
        View r62 = this.paintView.getTopLayout();
        Property r72 = View.TRANSLATION_Y;
        float[] r115 = new float[2];
        r115[r202] = -AndroidUtilities.dp(16.0f);
        r115[1] = 0.0f;
        r53.add(ObjectAnimator.ofFloat(r62, r72, r115));
        r53.add(ObjectAnimator.ofFloat(this.paintView.getBottomLayout(), r15, new float[]{0.0f, 1.0f}));
        View r63 = this.paintView.getBottomLayout();
        float[] r14 = new float[2];
        r14[r202] = AndroidUtilities.dp(48.0f);
        r14[1] = 0.0f;
        r53.add(ObjectAnimator.ofFloat(r63, r72, r14));
        View r64 = this.paintView.getWeightChooserView();
        Property r73 = View.TRANSLATION_X;
        float[] r142 = new float[2];
        r142[r202] = -AndroidUtilities.dp(32.0f);
        r142[1] = 0.0f;
        r53.add(ObjectAnimator.ofFloat(r64, r73, r142));
    L50:
        CropEditor r65 = this.cropEditor;
        if (r65 != null) goto L53;
        float r16 = 52.0f;
    L59:
        CropInlineEditor r66 = this.cropInlineEditor;
        if (r66 != null) goto L62;
    L66:
        RLottieImageView r67 = this.muteButton;
        Property r74 = View.ALPHA;
        if (r19 == (-1)) goto L70;
        if (r19 == 2) goto L70;
    L72:
        float r116 = 0.0f;
    L73:
        float[] r143 = new float[1];
        r143[r202] = r116;
        r53.add(ObjectAnimator.ofFloat(r67, r74, r143));
        PlayPauseButton r68 = this.playButton;
        if (r19 == (-1)) goto L77;
        if (r19 == 2) goto L77;
    L83:
        float r117 = 0.0f;
    L85:
        float[] r144 = new float[1];
        r144[r202] = r117;
        r53.add(ObjectAnimator.ofFloat(r68, r74, r144));
        DownloadButton r69 = this.downloadButton;
        if (r19 == (-1)) goto L90;
        if (r19 == 2) goto L90;
        float r118 = 0.0f;
    L91:
        float[] r145 = new float[1];
        r145[r202] = r118;
        r53.add(ObjectAnimator.ofFloat(r69, r74, r145));
        ImageView r610 = this.themeButton;
        if (r610 == null) goto L102;
        if (r19 == (-1)) goto L95;
        if (r19 == 2) goto L95;
    L100:
        float r119 = 0.0f;
    L101:
        float[] r146 = new float[1];
        r146[r202] = r119;
        r53.add(ObjectAnimator.ofFloat(r610, r74, r146));
    L95:
        StoryEntry r1110 = this.outputEntry;
        if (r1110 == null) goto L100;
        if (r1110.isRepostMessage == false) goto L100;
        r119 = 1.0f;
    L102:
        SimpleTextView r611 = this.titleTextView;
        int r1111 = this.currentPage;
        if (r1111 == 1) goto L105;
        if (r1111 == 2) goto L105;
    L107:
        float r1112 = 0.0f;
    L108:
        float[] r147 = new float[1];
        r147[r202] = r1112;
        r53.add(ObjectAnimator.ofFloat(r611, r74, r147));
        if (r19 != 1) goto L111;
        this.previewContainer.setPivotY(r6.getMeasuredHeight() * 0.2f);
        int r612 = AndroidUtilities.dp(164.0f);
    L116:
        if (r612 <= 0) goto L118;
        float r613 = (r11 - r612) / (this.previewContainer.getHeight() - ((int) this.previewContainer.getPivotY()));
    L119:
        FrameLayout r1113 = this.previewContainer;
        Property r148 = View.SCALE_X;
        float[] r152 = new float[1];
        r152[r202] = r613;
        r53.add(ObjectAnimator.ofFloat(r1113, r148, r152));
        FrameLayout r1114 = this.previewContainer;
        Property r149 = View.SCALE_Y;
        float[] r153 = new float[1];
        r153[r202] = r613;
        r53.add(ObjectAnimator.ofFloat(r1114, r149, r153));
        if (r19 != (-1)) goto L122;
        FrameLayout r614 = this.previewContainer;
        Property r1115 = View.TRANSLATION_Y;
        float[] r1410 = new float[1];
        r1410[r202] = 0.0f;
        r53.add(ObjectAnimator.ofFloat(r614, r1115, r1410));
    L122:
        PhotoFilterCurvesControl r615 = this.photoFilterViewCurvesControl;
        if (r615 == null) goto L128;
        if (r19 != 1) goto L126;
        float r1116 = 1.0f;
    L127:
        float[] r1411 = new float[1];
        r1411[r202] = r1116;
        r53.add(ObjectAnimator.ofFloat(r615, r74, r1411));
        goto L128
    L126:
        r1116 = 0.0f;
    L128:
        PhotoFilterBlurControl r616 = this.photoFilterViewBlurControl;
        if (r616 == null) goto L134;
        if (r19 != 1) goto L132;
        float r1117 = 1.0f;
    L133:
        float[] r1412 = new float[1];
        r1412[r202] = r1117;
        r53.add(ObjectAnimator.ofFloat(r616, r74, r1412));
        goto L134
    L132:
        r1117 = 0.0f;
    L134:
        CaptionStory r617 = this.captionEdit;
        if (r19 != (-1)) goto L137;
        float r1118 = 1.0f;
    L138:
        float[] r1413 = new float[1];
        r1413[r202] = r1118;
        r53.add(ObjectAnimator.ofFloat(r617, r74, r1413));
        FrameLayout r618 = this.captionContainer;
        if (r19 == (-1)) goto L143;
        if (r19 == 2) goto L143;
        float r1119 = 0.0f;
    L144:
        float[] r1414 = new float[1];
        r1414[r202] = r1119;
        r53.add(ObjectAnimator.ofFloat(r618, r74, r1414));
        FrameLayout r619 = this.captionContainer;
        Property r1120 = View.TRANSLATION_Y;
        if (r19 == (-1)) goto L149;
        if (r19 == 2) goto L149;
        float r1415 = AndroidUtilities.dp(120.0f);
    L150:
        float[] r154 = new float[1];
        r154[r202] = r1415;
        r53.add(ObjectAnimator.ofFloat(r619, r1120, r154));
        FrameLayout r620 = this.videoTimelineContainerView;
        if (r19 == (-1)) goto L155;
        if (r19 == 2) goto L155;
        r13 = 0.0f;
    L155:
        float[] r3 = new float[1];
        r3[r202] = r13;
        r53.add(ObjectAnimator.ofFloat(r620, r74, r3));
        FrameLayout r32 = this.videoTimelineContainerView;
        if (r19 != 2) goto L159;
        int r621 = AndroidUtilities.dp(68.0f);
    L160:
        float[] r75 = new float[1];
        r75[r202] = r621;
        r53.add(ObjectAnimator.ofFloat(r32, r1120, r75));
        this.actionBarButtons.setPivotX(r3.getMeasuredWidth() - AndroidUtilities.dp(23.0f));
        LinearLayout r33 = this.actionBarButtons;
        Property r622 = View.ROTATION;
        if (r19 != 2) goto L163;
        float r76 = -90.0f;
    L164:
        float[] r1121 = new float[1];
        r1121[r202] = r76;
        r53.add(ObjectAnimator.ofFloat(r33, r622, r1121));
        PlayPauseButton r34 = this.playButton;
        if (r19 != 2) goto L167;
        float r1122 = 90.0f;
    L168:
        float[] r132 = new float[1];
        r132[r202] = r1122;
        r53.add(ObjectAnimator.ofFloat(r34, r622, r132));
        RLottieImageView r35 = this.muteButton;
        if (r19 != 2) goto L171;
        float r1123 = 90.0f;
    L172:
        float[] r133 = new float[1];
        r133[r202] = r1123;
        r53.add(ObjectAnimator.ofFloat(r35, r622, r133));
        DownloadButton r36 = this.downloadButton;
        if (r19 != 2) goto L175;
        float r1124 = 90.0f;
    L176:
        float[] r134 = new float[1];
        r134[r202] = r1124;
        r53.add(ObjectAnimator.ofFloat(r36, r622, r134));
        ImageView r37 = this.themeButton;
        if (r37 == null) goto L182;
        if (r19 != 2) goto L180;
        r10 = 90.0f;
    L180:
        float[] r77 = new float[1];
        r77[r202] = r10;
        r53.add(ObjectAnimator.ofFloat(r37, r622, r77));
    L182:
        if (this.blurManager.hasRenderNode() == false) goto L184;
        ValueAnimator r38 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r38.addUpdateListener(new AnonymousClass22(this));
        r53.add(r38);
    L184:
        TimelineView r39 = this.timelineView;
        if (r39 == null) goto L196;
        StoryEntry r623 = this.outputEntry;
        if (r623 != null) goto L189;
    L195:
        r39.setOpen(r8, r21);
        goto L196
    L189:
        if (r623.isCollage() == false) goto L195;
        if (this.outputEntry.hasVideo() == false) goto L195;
        if (r19 == 2) goto L195;
        r8 = r202;
    L196:
        if (r21 == false) goto L202;
        AnimatorSet r2 = new AnimatorSet();
        this.editModeAnimator = r2;
        r2.playTogether(r53);
        this.editModeAnimator.setDuration(320);
        this.editModeAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.editModeAnimator.addListener(new AnonymousClass23(this, r42, r19));
        if (r12 == false) goto L200;
        this.editModeAnimator.setStartDelay(120);
    L200:
        this.editModeAnimator.start();
        return;
    L202:
        int r78 = r202;
    L204:
        if (r78 >= r53.size()) goto L206;
        Animator r22 = (Animator) r53.get(r78);
        r22.setDuration(1);
        r22.start();
        r78 = r78 + 1;
        goto L204
    L206:
        if (r42 == r19) goto L210;
        onSwitchEditModeEnd(r42, r19);
        return;
    L210:
        return;
    L175:
        r1124 = 0.0f;
        goto L176
    L171:
        r1123 = 0.0f;
        goto L172
    L167:
        r1122 = 0.0f;
        goto L168
    L163:
        r76 = 0.0f;
        goto L164
    L159:
        r621 = (-(this.captionEdit.getEditTextHeight() + AndroidUtilities.dp(12.0f))) + AndroidUtilities.dp(64.0f);
    L149:
        r1415 = 0.0f;
    L143:
        r1119 = 1.0f;
        goto L144
    L137:
        r1118 = 0.0f;
        goto L138
    L118:
        r613 = 1.0f;
        goto L119
    L111:
        if (r19 != 0) goto L113;
        this.previewContainer.setPivotY(r6.getMeasuredHeight() * 0.6f);
        r612 = AndroidUtilities.dp(40.0f);
        goto L116
    L113:
        if (r19 != 2) goto L115;
        this.previewContainer.setPivotY(0.0f);
        r612 = this.timelineView.getContentHeight() + AndroidUtilities.dp(8.0f);
        goto L116
    L115:
        r612 = r202;
    L105:
        if (r19 != (-1)) goto L107;
        r1112 = 1.0f;
    L90:
        r118 = 1.0f;
    L77:
        if (this.mode == 1) goto L84;
        StoryEntry r1125 = this.outputEntry;
        if (r1125 == null) goto L83;
        if (TextUtils.isEmpty(r1125.audioPath) == true) goto L83;
    L84:
        r117 = 1.0f;
    L70:
        if (this.mode != 1) goto L72;
        r116 = 1.0f;
        goto L73
    L62:
        if (r19 != 4) goto L64;
        CropRotationWheel r624 = r66.wheel;
        Property r79 = View.ALPHA;
        r53.add(ObjectAnimator.ofFloat(r624, r79, new float[]{0.0f, 1.0f}));
        CropRotationWheel r625 = this.cropInlineEditor.wheel;
        Property r1126 = View.TRANSLATION_Y;
        float[] r155 = new float[2];
        r155[r202] = AndroidUtilities.dp(r16);
        r155[1] = 0.0f;
        r53.add(ObjectAnimator.ofFloat(r625, r1126, r155));
        r53.add(ObjectAnimator.ofFloat(this.cropInlineEditor.buttonsLayout, r79, new float[]{0.0f, 1.0f}));
        FrameLayout r626 = this.cropInlineEditor.buttonsLayout;
        float[] r1416 = new float[2];
        r1416[r202] = AndroidUtilities.dp(r16);
        r1416[1] = 0.0f;
        r53.add(ObjectAnimator.ofFloat(r626, r1126, r1416));
        float[] r710 = new float[2];
        r710[r202] = this.cropInlineEditor.getAppearProgress();
        r710[1] = 1.0f;
        ValueAnimator r627 = ValueAnimator.ofFloat(r710);
        r627.addUpdateListener(new StoryRecorder$$ExternalSyntheticLambda63(this));
        r53.add(r627);
        goto L66
    L64:
        if (r42 != 4) goto L66;
        CropRotationWheel r628 = r66.wheel;
        Property r711 = View.ALPHA;
        r53.add(ObjectAnimator.ofFloat(r628, r711, new float[]{1.0f, 0.0f}));
        CropRotationWheel r629 = this.cropInlineEditor.wheel;
        Property r1127 = View.TRANSLATION_Y;
        float r1417 = AndroidUtilities.dp(r16);
        float[] r156 = new float[2];
        r156[r202] = 0.0f;
        r156[1] = r1417;
        r53.add(ObjectAnimator.ofFloat(r629, r1127, r156));
        r53.add(ObjectAnimator.ofFloat(this.cropInlineEditor.buttonsLayout, r711, new float[]{1.0f, 0.0f}));
        FrameLayout r630 = this.cropInlineEditor.buttonsLayout;
        float r712 = AndroidUtilities.dp(r16);
        float[] r1418 = new float[2];
        r1418[r202] = 0.0f;
        r1418[1] = r712;
        r53.add(ObjectAnimator.ofFloat(r630, r1127, r1418));
        float[] r713 = new float[2];
        r713[r202] = this.cropInlineEditor.getAppearProgress();
        r713[1] = 0.0f;
        ValueAnimator r631 = ValueAnimator.ofFloat(r713);
        r631.addUpdateListener(new StoryRecorder$$ExternalSyntheticLambda64(this));
        r53.add(r631);
        goto L66
    L53:
        if (r19 != 3) goto L55;
        CropRotationWheel r632 = r65.wheel;
        Property r1128 = View.ALPHA;
        r53.add(ObjectAnimator.ofFloat(r632, r1128, new float[]{0.0f, 1.0f}));
        CropRotationWheel r633 = this.cropEditor.wheel;
        Property r1419 = View.TRANSLATION_Y;
        r16 = 52.0f;
        float[] r714 = new float[2];
        r714[r202] = AndroidUtilities.dp(52.0f);
        r714[1] = 0.0f;
        r53.add(ObjectAnimator.ofFloat(r633, r1419, r714));
        r53.add(ObjectAnimator.ofFloat(this.cropEditor.buttonsLayout, r1128, new float[]{0.0f, 1.0f}));
        FrameLayout r634 = this.cropEditor.buttonsLayout;
        float[] r1129 = new float[2];
        r1129[r202] = AndroidUtilities.dp(52.0f);
        r1129[1] = 0.0f;
        r53.add(ObjectAnimator.ofFloat(r634, r1419, r1129));
        float[] r715 = new float[2];
        r715[r202] = this.cropEditor.getAppearProgress();
        r715[1] = 1.0f;
        ValueAnimator r635 = ValueAnimator.ofFloat(r715);
        r635.addUpdateListener(new StoryRecorder$$ExternalSyntheticLambda61(this));
        r53.add(r635);
        goto L59
    L55:
        r16 = 52.0f;
        if (r42 != 3) goto L59;
        CropRotationWheel r636 = r65.wheel;
        Property r716 = View.ALPHA;
        r53.add(ObjectAnimator.ofFloat(r636, r716, new float[]{1.0f, 0.0f}));
        CropRotationWheel r637 = this.cropEditor.wheel;
        Property r1130 = View.TRANSLATION_Y;
        float r1420 = AndroidUtilities.dp(52.0f);
        float[] r157 = new float[2];
        r157[r202] = 0.0f;
        r157[1] = r1420;
        r53.add(ObjectAnimator.ofFloat(r637, r1130, r157));
        r53.add(ObjectAnimator.ofFloat(this.cropEditor.buttonsLayout, r716, new float[]{1.0f, 0.0f}));
        FrameLayout r638 = this.cropEditor.buttonsLayout;
        float r717 = AndroidUtilities.dp(52.0f);
        float[] r1421 = new float[2];
        r1421[r202] = 0.0f;
        r1421[1] = r717;
        r53.add(ObjectAnimator.ofFloat(r638, r1130, r1421));
        float[] r718 = new float[2];
        r718[r202] = this.cropEditor.getAppearProgress();
        r718[1] = 0.0f;
        ValueAnimator r639 = ValueAnimator.ofFloat(r718);
        r639.addUpdateListener(new StoryRecorder$$ExternalSyntheticLambda62(this));
        r53.add(r639);
        goto L59
    L46:
        if (r42 != 0) goto L50;
        if (this.paintView == null) goto L50;
        this.previewTouchable = null;
        FlashViews.ImageViewInvertable r640 = this.backButton;
        Property r719 = View.ALPHA;
        float[] r1131 = new float[1];
        r1131[r202] = 1.0f;
        r53.add(ObjectAnimator.ofFloat(r640, r719, r1131));
        View r641 = this.paintView.getTopLayout();
        float[] r1132 = new float[1];
        r1132[r202] = 0.0f;
        r53.add(ObjectAnimator.ofFloat(r641, r719, r1132));
        View r642 = this.paintView.getTopLayout();
        Property r1133 = View.TRANSLATION_Y;
        float[] r158 = new float[1];
        r158[r202] = -AndroidUtilities.dp(16.0f);
        r53.add(ObjectAnimator.ofFloat(r642, r1133, r158));
        View r643 = this.paintView.getBottomLayout();
        float[] r1422 = new float[1];
        r1422[r202] = 0.0f;
        r53.add(ObjectAnimator.ofFloat(r643, r719, r1422));
        View r644 = this.paintView.getBottomLayout();
        float[] r1423 = new float[1];
        r1423[r202] = AndroidUtilities.dp(48.0f);
        r53.add(ObjectAnimator.ofFloat(r644, r1133, r1423));
        View r645 = this.paintView.getWeightChooserView();
        Property r720 = View.TRANSLATION_X;
        float[] r1424 = new float[1];
        r1424[r202] = -AndroidUtilities.dp(32.0f);
        r53.add(ObjectAnimator.ofFloat(r645, r720, r1424));
        goto L50
    L35:
        r114 = null;
        goto L36
    L39:
        if (r42 != 1) goto L38;
        if (r112 == null) goto L38;
        this.previewTouchable = null;
        r202 = 0;
        r53.add(ObjectAnimator.ofFloat(r112.getToolsView(), View.TRANSLATION_Y, new float[]{AndroidUtilities.dp(226.0f)}));
        r53.add(ObjectAnimator.ofFloat(this.photoFilterView.getToolsView(), View.ALPHA, new float[]{0.0f}));
    L29:
        r12 = false;
    L22:
        if (this.openProgress <= 0.0f) goto L24;
        r11 = true;
        goto L25
    }

    public void toggleTheme() {
        if (this.outputEntry != null) goto L5;
        return;
    L5:
        if (this.changeDayNightView == null) goto L7;
        return;
    L7:
        if (this.themeButton == null) goto L37;
        ValueAnimator r0 = this.changeDayNightViewAnimator;
        if (r0 != null) goto L11;
    L13:
        boolean r4 = this.outputEntry.isDark;
        Bitmap r10 = Bitmap.createBitmap(this.windowView.getWidth(), this.windowView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas r5 = new Canvas(r10);
        this.themeButton.setAlpha(0.0f);
        PreviewView r02 = this.previewView;
        if (r02 == null) goto L16;
        r02.drawForThemeToggle = true;
    L16:
        PaintView r03 = this.paintView;
        if (r03 == null) goto L19;
        r03.drawForThemeToggle = true;
    L19:
        this.windowView.draw(r5);
        PreviewView r04 = this.previewView;
        if (r04 == null) goto L22;
        r04.drawForThemeToggle = false;
    L22:
        PaintView r05 = this.paintView;
        if (r05 == null) goto L25;
        r05.drawForThemeToggle = false;
    L25:
        this.themeButton.setAlpha(1.0f);
        Paint r9 = new Paint(1);
        r9.setColor(-16777216);
        r9.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint r11 = new Paint(1);
        r11.setFilterBitmap(true);
        int[] r3 = new int[2];
        this.themeButton.getLocationInWindow(r3);
        float r12 = r3[0];
        float r13 = r3[1];
        float r8 = Math.max(r10.getHeight(), r10.getWidth()) + AndroidUtilities.navigationBarHeight;
        Shader.TileMode r2 = Shader.TileMode.CLAMP;
        r11.setShader(new BitmapShader(r10, r2, r2));
        AnonymousClass31 r1 = new AnonymousClass31(this, getContext(), r4, r5, r12 + (this.themeButton.getMeasuredWidth() / 2.0f), r13 + (this.themeButton.getMeasuredHeight() / 2.0f), r8, r9, r10, r11, r12, r13);
        this.changeDayNightView = r1;
        r1.setOnTouchListener(new StoryRecorder$$ExternalSyntheticLambda110());
        this.changeDayNightViewProgress = 0.0f;
        ValueAnimator r15 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.changeDayNightViewAnimator = r15;
        r15.addUpdateListener(new AnonymousClass32(this));
        this.changeDayNightViewAnimator.addListener(new AnonymousClass33(this));
        this.changeDayNightViewAnimator.setStartDelay(80);
        ValueAnimator r152 = this.changeDayNightViewAnimator;
        if (r4 == false) goto L28;
        long r06 = 320;
    L29:
        r152.setDuration(r06);
        ValueAnimator r153 = this.changeDayNightViewAnimator;
        if (r4 == false) goto L32;
        CubicBezierInterpolator r07 = CubicBezierInterpolator.EASE_IN;
    L33:
        r153.setInterpolator(r07);
        this.changeDayNightViewAnimator.start();
        this.windowView.addView(this.changeDayNightView, new ViewGroup.LayoutParams(-1, -1));
        AndroidUtilities.runOnUIThread(new StoryRecorder$$ExternalSyntheticLambda111(this));
        return;
    L32:
        r07 = CubicBezierInterpolator.EASE_OUT_QUINT;
        goto L33
    L28:
        r06 = 450;
        goto L29
    L11:
        if (r0.isRunning() == false) goto L13;
        return;
    }

    public void updateThemeButtonDrawable(boolean r4) {
        RLottieDrawable r0 = this.themeButtonDrawable;
        if (r0 == null) goto L24;
        StoryEntry r1 = this.outputEntry;
        int r2 = 0;
        if (r4 == false) goto L15;
        if (r1 != null) goto L8;
    L10:
        r0.setCustomEndFrame(r2);
        RLottieDrawable r3 = this.themeButtonDrawable;
        if (r3 == null) goto L25;
        r3.start();
        return;
    L25:
        return;
    L8:
        if (r1.isDark == false) goto L10;
        r2 = r0.getFramesCount();
        goto L10
    L15:
        if (r1 != null) goto L17;
    L19:
        int r02 = 0;
    L20:
        this.themeButtonDrawable.setCurrentFrame(r02, false, true);
        this.themeButtonDrawable.setCustomEndFrame(r02);
        ImageView r32 = this.themeButton;
        if (r32 == null) goto L26;
        r32.invalidate();
        return;
    L26:
        return;
    L17:
        if (r1.isDark == false) goto L19;
        r02 = r0.getFramesCount() - 1;
        goto L20
    }
}
