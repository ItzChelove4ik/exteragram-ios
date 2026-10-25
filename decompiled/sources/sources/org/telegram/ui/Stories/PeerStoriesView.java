package org.telegram.ui.Stories;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ReplacementSpan;
import android.text.style.URLSpan;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.google.android.gms.cast.MediaError;
import j$.net.URLDecoder;
import j$.net.URLEncoder;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.NotificationsSettingsFacade;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.camera.CameraView;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.AccountFrozenAlert;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.AvatarSpan;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.ChooseSpeedLayout;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.AvatarsImageView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BitmapShaderTools;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.BulletinFactory$$ExternalSyntheticLambda5;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.ChatAttachAlertDocumentLayout;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.CustomPopupMenu;
import org.telegram.ui.Components.DotDividerSpan;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.EmojiPacksAlert;
import org.telegram.ui.Components.HashtagActivity;
import org.telegram.ui.Components.HintView;
import org.telegram.ui.Components.InstantCameraView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.MediaActivity;
import org.telegram.ui.Components.MentionsContainerView;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RadialProgress;
import org.telegram.ui.Components.Reactions.AnimatedEmojiEffect;
import org.telegram.ui.Components.Reactions.ReactionImageHolder;
import org.telegram.ui.Components.Reactions.ReactionsEffectOverlay;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.Reactions.ReactionsUtils;
import org.telegram.ui.Components.ReactionsContainerLayout;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SenderSelectView;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.SpeedIconDrawable;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TranslateAlert2;
import org.telegram.ui.Components.URLSpanMono;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.URLSpanReplacement;
import org.telegram.ui.Components.URLSpanUserMention;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProviderThemed;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSource;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.chat.layouts.ChatActivitySideControlsButtonsLayout;
import org.telegram.ui.Components.voip.CellFlickerDrawable;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.EmojiAnimationsOverlay;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.MessageStatisticActivity;
import org.telegram.ui.NotificationsCustomSettingsActivity;
import org.telegram.ui.PinchToZoomHelper;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.ReportBottomSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.DialogStoriesCell;
import org.telegram.ui.Stories.PaidReactionButton;
import org.telegram.ui.Stories.SelfStoriesPreviewView;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoryCaptionView;
import org.telegram.ui.Stories.StoryViewer;
import org.telegram.ui.Stories.recorder.CaptionContainerView;
import org.telegram.ui.Stories.recorder.DraftsController;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.Stories.recorder.LivePlayerView;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;
import org.telegram.ui.Stories.recorder.StoryRecorder;
import org.telegram.ui.WrappedResourceProvider;
import org.telegram.ui.recyclerview.ChatListItemAnimator;

/* loaded from: classes7.dex */
public abstract class PeerStoriesView extends SizeNotifierFrameLayout implements NotificationCenter.NotificationCenterDelegate {
    public static boolean DISABLE_STORY_REPOSTING = false;
    private static int activeCount;
    private boolean BIG_SCREEN;
    private ActionBarMenuSubItem albumItem;
    private ViewGroup albumLayout;
    private boolean allowDrawSurface;
    Runnable allowDrawSurfaceRunnable;
    private boolean allowRepost;
    private boolean allowShare;
    private boolean allowShareLink;
    private float alpha;
    boolean animateKeyboardOpening;
    private float animatingKeyboardHeight;
    boolean areLiveCommentsDisabled;
    private boolean attachedToWindow;
    private final AvatarDrawable avatarDrawable;
    private final BitmapShaderTools bitmapShaderTools;
    private BlurredBackgroundColorProviderThemed blurredBackgroundColorProvider;
    private BlurredBackgroundDrawableViewFactory blurredBackgroundDrawableFactory;
    private final BlurredBackgroundSourceColor blurredBackgroundSourceFallback;
    private final BlurredBackgroundSourceRenderNode blurredBackgroundSourceRenderNodeWithSaturation;
    private final BlurredBackgroundSource blurredBackgroundSourceWithSaturation;
    private TL_stories.TL_premium_boostsStatus boostsStatus;
    private final LinearLayout bottomActionsLinearLayout;
    private ChannelBoostsController.CanApplyBoost canApplyBoost;
    private Runnable cancellableViews;
    private ValueAnimator changeBoundAnimator;
    ChatActivityEnterView chatActivityEnterView;
    private ChatAttachAlert chatAttachAlert;
    boolean checkBlackoutMode;
    private int classGuid;
    private final Path clipPath;
    private CommentButton commentButton;
    int count;
    private int currentAccount;
    private long currentImageTime;
    public final StoryItemHolder currentStory;
    ArrayList<Integer> day;
    Delegate delegate;
    private boolean deletedPeer;
    private long dialogId;
    ArrayList<TLRPC.Document> documentsToPrepare;
    private boolean drawAnimatedEmojiAsMovingReaction;
    private boolean drawReactionEffect;
    public boolean editOpened;
    ActionBarMenuSubItem editStoryItem;
    private boolean editedPrivacy;
    private EmojiAnimationsOverlay emojiAnimationsOverlay;
    BlurredBackgroundDrawable emojiKeyboardBackground;
    private AnimatedEmojiEffect emojiReactionEffect;
    private int enterViewBottomOffset;
    private StoryFailView failView;
    private ViewPropertyAnimator failViewAnimator;
    public boolean forceUpdateOffsets;
    PeerHeaderView headerView;
    HintView2 highlightMessageHintView;
    private boolean imageChanged;
    private final ImageReceiver imageReceiver;
    boolean inBlackoutMode;
    Paint inputBackgroundPaint;
    Paint inputBottomBorderPaint;
    BlurredBackgroundDrawable inputFieldBackground;
    Paint inputTopBorderPaint;
    private InstantCameraView instantCameraView;
    boolean isActive;
    private boolean isCaptionPartVisible;
    boolean isChannel;
    private boolean isEditing;
    private boolean isFailed;
    boolean isGroup;
    private boolean isLongPressed;
    boolean isPremiumBlocked;
    private boolean isRecording;
    boolean isSelf;
    private boolean isUploading;
    private boolean isVisible;
    ValueAnimator keyboardAnimator;
    public boolean keyboardVisible;
    float lastAnimatingKeyboardHeight;
    private long lastDrawTime;
    int lastKeyboardHeight;
    private boolean lastNoThumb;
    int lastOpenedKeyboardHeight;
    private final ImageReceiver leftPreloadImageReceiver;
    private final FrameLayout likeButtonContainer;
    private ReactionsContainerLayout likesReactionLayout;
    private float likesReactionShowProgress;
    private boolean likesReactionShowing;
    private AnimatedFloat linesAlpha;
    private int linesCount;
    private int linesPosition;
    private int listPosition;
    public final View liveCommentsShadowView;
    public final LiveCommentsView liveCommentsView;
    private HintView mediaBanTooltip;
    private MentionsContainerView mentionContainer;
    private MentionsContainerView.Delegate mentionsDelegate;
    private boolean messageSent;
    private long messageStars;
    private boolean movingReaction;
    private int movingReactionFromSize;
    private int movingReactionFromX;
    private int movingReactionFromY;
    private float movingReactionProgress;
    private MuteButton muteButton;
    private final FrameLayout muteIconContainer;
    private final RLottieImageView muteIconView;
    private float muteIconViewAlpha;
    private final ImageView noSoundIconView;
    final AnimationNotificationsLocker notificationsLocker;
    private Runnable onImageReceiverThumbLoaded;
    private final ImageView optionsIconView;
    private ValueAnimator outAnimator;
    private float outT;
    RoundRectOutlineProvider outlineProvider;
    private boolean paused;
    public PinchToZoomHelper pinchToZoomHelper;
    private final ImageView pipIconView;
    final VideoPlayerSharedScope playerSharedScope;
    CustomPopupMenu popupMenu;
    private final ArrayList<ReactionImageHolder> preloadReactionHolders;
    private LinearLayout premiumBlockedText;
    private TextView premiumBlockedText1;
    private TextView premiumBlockedText2;
    private float prevToHideProgress;
    private int previousSelectedPotision;
    private final StoryPrivacyButton privacyButton;
    private HintView2 privacyHint;
    float progressToDismiss;
    private AnimatedFloat progressToHideInterface;
    float progressToKeyboard;
    AnimatedFloat progressToRecording;
    float progressToReply;
    AnimatedFloat progressToStickerExpanded;
    AnimatedFloat progressToTextA;
    private ImageReceiver reactionEffectImageReceiver;
    private AnimatedEmojiDrawable reactionMoveDrawable;
    private ImageReceiver reactionMoveImageReceiver;
    private int reactionsContainerIndex;
    ReactionsContainerLayout reactionsContainerLayout;
    private AnimatedTextView.AnimatedTextDrawable reactionsCounter;
    private AnimatedFloat reactionsCounterProgress;
    private boolean reactionsCounterVisible;
    private HintView2 reactionsLongpressTooltip;
    private Runnable reactionsTooltipRunnable;
    private int realKeyboardHeight;
    private TextView replyDisabledTextView;
    private ImageView repostButton;
    private FrameLayout repostButtonContainer;
    private AnimatedTextView.AnimatedTextDrawable repostCounter;
    private AnimatedFloat repostCounterProgress;
    private boolean repostCounterVisible;
    private final Theme.ResourcesProvider resourcesProvider;
    private final ImageReceiver rightPreloadImageReceiver;
    private int selectedPosition;
    private View selfAvatarsContainer;
    private AvatarsImageView selfAvatarsView;
    private TextView selfStatusView;
    private FrameLayout selfView;
    private TLRPC.TL_channels_sendAsPeers sendAsPeersObj;
    public ShareAlert shareAlert;
    private final ImageView shareButton;
    final SharedResources sharedResources;
    private int shiftDp;
    private final Runnable showTapToSoundHint;
    boolean showViewsProgress;
    ChatActivitySideControlsButtonsLayout sideControlsButtonsLayout;
    private HintView2 soundTooltip;
    private ActionBarMenuSubItem speedItem;
    private ChooseSpeedLayout speedLayout;
    private PaidReactionButton starsButton;
    private PaidReactionButton.PaidReactionButtonEffectsView starsButtonEffectsView;
    long starsPriceBlocked;
    private boolean stealthModeIsActive;
    StoriesController storiesController;
    private StoriesLikeButton storiesLikeButton;
    private StoryMediaAreasView storyAreasView;
    private final StoryCaptionView storyCaptionView;
    public FrameLayout storyContainer;
    private CaptionContainerView storyEditCaptionView;
    final ArrayList<TL_stories.StoryItem> storyItems;
    private final StoryLinesDrawable storyLines;
    private StoryPositionView storyPositionView;
    private final StoryViewer storyViewer;
    private boolean switchEventSent;
    private long titleLastDialogId;
    private boolean titleLastLive;
    public FrameLayout topBulletinContainer;
    private int totalStoriesCount;
    public boolean unsupported;
    private FrameLayout unsupportedContainer;
    Runnable updateStealthModeTimer;
    final ArrayList<StoriesController.UploadingStory> uploadingStories;
    ArrayList<Uri> uriesToPrepare;
    private boolean userCanSeeViews;
    TL_stories.PeerStories userStories;
    public long videoDuration;
    private float viewsThumbAlpha;
    private SelfStoriesPreviewView.ImageHolder viewsThumbImageReceiver;
    private float viewsThumbPivotY;
    private float viewsThumbScale;
    private boolean wasBigScreen;
    private int watchersCount;

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$1, reason: invalid class name */
    public class AnonymousClass1 implements PinchToZoomHelper.Callback {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass1(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PinchToZoomHelper.Callback
        public void onZoomFinished(MessageObject r1) {
            this.this$0.delegate.setIsInPinchToZoom(false);
        }

        @Override // org.telegram.ui.PinchToZoomHelper.Callback
        public void onZoomStarted(MessageObject r1) {
            this.this$0.delegate.setIsInPinchToZoom(true);
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$10, reason: invalid class name */
    public class AnonymousClass10 extends LiveCommentsView {
        final /* synthetic */ PeerStoriesView this$0;
        final /* synthetic */ StoryViewer val$storyViewer;

        public AnonymousClass10(PeerStoriesView r1, Context r2, StoryViewer r3, ViewGroup r4, View r5, FrameLayout r6, StoryViewer r7) {
            this.this$0 = r1;
            this.val$storyViewer = r7;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Stories.LiveCommentsView
        public TLRPC.Peer getDefaultSendAs() {
            LivePlayer r0 = this.val$storyViewer.livePlayer;
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.getDefaultSendAs();
        }

        @Override // org.telegram.ui.Stories.LiveCommentsView
        public boolean isMe(long r6) {
            if (r6 != UserConfig.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getClientUserId()) goto L5;
            return true;
        L5:
            LivePlayer r0 = this.val$storyViewer.livePlayer;
            if (r0 == null) goto L11;
            if (r6 != DialogObject.getPeerDialogId(r0.getDefaultSendAs())) goto L11;
            return true;
        L11:
            if (PeerStoriesView.m19512$$Nest$fgetsendAsPeersObj(this.this$0) == null) goto L19;
            int r02 = 0;
        L14:
            if (r02 >= PeerStoriesView.m19512$$Nest$fgetsendAsPeersObj(this.this$0).peers.size()) goto L19;
            if (r6 == DialogObject.getPeerDialogId(PeerStoriesView.m19512$$Nest$fgetsendAsPeersObj(this.this$0).peers.get(r02).peer)) goto L17;
            r02 = r02 + 1;
            goto L14
        L17:
            return true;
        L19:
            return false;
        }

        @Override // org.telegram.ui.Stories.LiveCommentsView
        public void onCancelledStarReaction(long r2) {
            if (PeerStoriesView.m19518$$Nest$fgetstarsButtonEffectsView(this.this$0) != null) goto L5;
            return;
        L5:
            PeerStoriesView.m19518$$Nest$fgetstarsButtonEffectsView(this.this$0).removeChipsFrom(r2);
        }

        @Override // org.telegram.ui.Stories.LiveCommentsView
        public void onMessagesCountUpdated() {
            if (PeerStoriesView.m19460$$Nest$fgetcommentButton(this.this$0) == null) goto L6;
            PeerStoriesView.m19460$$Nest$fgetcommentButton(this.this$0).setCount(getUnreadMessagesCount());
            return;
        }

        @Override // org.telegram.ui.Stories.LiveCommentsView
        public void onStarReaction(long r2, int r4, int r5) {
            if (PeerStoriesView.m19518$$Nest$fgetstarsButtonEffectsView(this.this$0) != null) goto L5;
            return;
        L5:
            PeerStoriesView.m19518$$Nest$fgetstarsButtonEffectsView(this.this$0).pushChip(r2, r4, r5);
        }

        @Override // org.telegram.ui.Stories.LiveCommentsView
        public void onStarsButtonCancelled() {
            PeerStoriesView.m19517$$Nest$fgetstarsButton(this.this$0).stopEffects();
        }

        @Override // org.telegram.ui.Stories.LiveCommentsView
        public void onStarsButtonPressed(long r1, boolean r3) {
            PeerStoriesView r0 = this.this$0;
            if (r3 == false) goto L6;
            PeerStoriesView.m19517$$Nest$fgetstarsButton(r0).playEffect(r1);
            return;
        L6:
            PeerStoriesView.m19517$$Nest$fgetstarsButton(r0).stopEffects();
        }

        @Override // org.telegram.ui.Stories.LiveCommentsView
        public void onStarsCountUpdated() {
            PeerStoriesView.m19517$$Nest$fgetstarsButton(this.this$0).setCount((int) getStarsCount());
            PeerStoriesView.m19517$$Nest$fgetstarsButton(this.this$0).setFilled(areSendingStars());
        }

        @Override // org.telegram.ui.Stories.LiveCommentsView
        public void setCollapsed(boolean r2, boolean r3) {
            super.setCollapsed(r2, r3);
            if (PeerStoriesView.m19460$$Nest$fgetcommentButton(this.this$0) == null) goto L6;
            PeerStoriesView.m19460$$Nest$fgetcommentButton(this.this$0).setCollapsed(r2, r3);
            return;
        }

        @Override // android.view.View
        public void setVisibility(int r1) {
            super.setVisibility(r1);
            this.this$0.liveCommentsShadowView.setVisibility(r1);
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$11, reason: invalid class name */
    public class AnonymousClass11 extends TextSelectionHelper.Callback {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass11(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.Callback
        public void onStateChanged(boolean r1) {
            PeerStoriesView r0 = this.this$0;
            r0.delegate.setIsInSelectionMode(PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(r0).textSelectionHelper.isInSelectionMode());
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$14, reason: invalid class name */
    public class AnonymousClass14 extends AnimatorListenerAdapter {
        final /* synthetic */ PeerStoriesView this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass14(PeerStoriesView r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.val$show == true) goto L6;
            PeerStoriesView.m19478$$Nest$fgetlikesReactionLayout(this.this$0).setVisibility(8);
            PeerStoriesView.m19478$$Nest$fgetlikesReactionLayout(this.this$0).reset();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$15, reason: invalid class name */
    public class AnonymousClass15 extends AnimatorListenerAdapter {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass15(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            PeerStoriesView.m19542$$Nest$fputlikesReactionShowProgress(this.this$0, 0.0f);
            PeerStoriesView.m19478$$Nest$fgetlikesReactionLayout(this.this$0).setAlpha(1.0f);
            PeerStoriesView.m19478$$Nest$fgetlikesReactionLayout(this.this$0).setVisibility(8);
            PeerStoriesView.m19478$$Nest$fgetlikesReactionLayout(this.this$0).reset();
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$16, reason: invalid class name */
    public class AnonymousClass16 extends AnimatorListenerAdapter {
        final /* synthetic */ PeerStoriesView this$0;
        final /* synthetic */ View val$oldLikeButton;

        public AnonymousClass16(PeerStoriesView r1, View r2) {
            this.this$0 = r1;
            this.val$oldLikeButton = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            AndroidUtilities.removeFromParent(this.val$oldLikeButton);
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$17, reason: invalid class name */
    public class AnonymousClass17 extends BaseFragment {
        final /* synthetic */ PeerStoriesView this$0;

        /* renamed from: org.telegram.ui.Stories.PeerStoriesView$17$1, reason: invalid class name */
        public class AnonymousClass1 extends WrappedResourceProvider {
            final /* synthetic */ AnonymousClass17 this$1;

            public AnonymousClass1(AnonymousClass17 r1, Theme.ResourcesProvider r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.WrappedResourceProvider
            public void appendColors() {
                this.sparseIntArray.append(Theme.key_dialogBackground, -14737633);
                this.sparseIntArray.append(Theme.key_windowBackgroundGray, -13421773);
            }
        }

        public AnonymousClass17(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Activity getParentActivity() {
            return PeerStoriesView.m19570$$Nest$mfindActivity(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Theme.ResourcesProvider getResourceProvider() {
            return new AnonymousClass1(this, PeerStoriesView.m19508$$Nest$fgetresourcesProvider(this.this$0));
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public boolean isLightStatusBar() {
            return false;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public boolean presentFragment(BaseFragment r2) {
            if (PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0) == null) goto L7;
            PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).presentFragment(r2);
            return true;
        L7:
            return true;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Dialog showDialog(Dialog r2) {
            if (PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0) == null) goto L6;
            PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).showDialog(r2);
            return r2;
        L6:
            if (r2 == null) goto L8;
            r2.show();
        L8:
            return r2;
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$18, reason: invalid class name */
    public class AnonymousClass18 extends WrappedResourceProvider {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass18(PeerStoriesView r1, Theme.ResourcesProvider r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.WrappedResourceProvider
        public void appendColors() {
            this.sparseIntArray.put(Theme.key_chat_emojiPanelBackground, -1071635414);
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$19, reason: invalid class name */
    public class AnonymousClass19 extends ChatActivityEnterView {
        private int chatActivityEnterViewAnimateFromTop;
        int lastContentViewHeight;
        private Animator messageEditTextAnimator;
        int messageEditTextPredrawHeigth;
        int messageEditTextPredrawScrollY;
        final /* synthetic */ PeerStoriesView this$0;

        /* renamed from: org.telegram.ui.Stories.PeerStoriesView$19$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass19 this$1;

            public AnonymousClass1(AnonymousClass19 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                this.this$1.this$0.invalidate();
                this.this$1.setAnimatedTop(0);
                AnonymousClass19 r42 = this.this$1;
                r42.this$0.forceUpdateOffsets = true;
                if (AnonymousClass19.access$000(r42) != null) goto L5;
            L7:
                PeerStoriesView.m19532$$Nest$fputchangeBoundAnimator(this.this$1.this$0, null);
                return;
            L5:
                if (AnonymousClass19.access$100(this.this$1).getVisibility() != 0) goto L7;
                AnonymousClass19.access$400(this.this$1).setTranslationY(AnonymousClass19.access$200(this.this$1) + ((1.0f - this.this$1.getTopViewEnterProgress()) * AnonymousClass19.access$300(this.this$1).getLayoutParams().height));
                goto L7
            }
        }

        public static /* synthetic */ void $r8$lambda$amlAc174B2VOfATVCBlOlzAiqD8(AnonymousClass19 r0, ValueAnimator r1) {
            r0.lambda$checkAnimation$0(r1);
        }

        /* renamed from: $r8$lambda$mGndjUyN1uf1Yl_w0Zur-6TgumA, reason: not valid java name */
        public static /* synthetic */ void m19594$r8$lambda$mGndjUyN1uf1Yl_w0Zur6TgumA(AnonymousClass19 r0, ValueAnimator r1) {
            r0.lambda$checkAnimation$1(r1);
        }

        public AnonymousClass19(PeerStoriesView r1, Activity r2, SizeNotifierFrameLayout r3, ChatActivity r4, boolean r5, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        public static /* synthetic */ View access$000(AnonymousClass19 r0) {
            return r0.topView;
        }

        public static /* synthetic */ View access$100(AnonymousClass19 r0) {
            return r0.topView;
        }

        public static /* synthetic */ int access$200(AnonymousClass19 r0) {
            return r0.animatedTop;
        }

        public static /* synthetic */ View access$300(AnonymousClass19 r0) {
            return r0.topView;
        }

        public static /* synthetic */ View access$400(AnonymousClass19 r0) {
            return r0.topView;
        }

        private void checkRecording() {
            boolean r0 = PeerStoriesView.m19473$$Nest$fgetisRecording(this.this$0);
            PeerStoriesView r1 = this.this$0;
            if (r1.chatActivityEnterView.isRecordingAudioVideo() == false) goto L5;
        L12:
            boolean r2 = true;
        L13:
            PeerStoriesView.m19539$$Nest$fputisRecording(r1, r2);
            if (r0 == PeerStoriesView.m19473$$Nest$fgetisRecording(this.this$0)) goto L20;
            PeerStoriesView r02 = this.this$0;
            if (r02.isActive == false) goto L18;
            r02.delegate.setIsRecording(PeerStoriesView.m19473$$Nest$fgetisRecording(r02));
        L18:
            invalidate();
            this.this$0.storyContainer.invalidate();
            return;
        L20:
            return;
        L5:
            if (this.this$0.chatActivityEnterView.seekbarVisible() == true) goto L12;
            FrameLayout r22 = this.recordedAudioPanel;
            if (r22 != null) goto L9;
        L11:
            r2 = false;
            goto L13
        L9:
            if (r22.getVisibility() != 0) goto L11;
            goto L11
        }

        private /* synthetic */ void lambda$checkAnimation$0(ValueAnimator r2) {
            setAnimatedTop((int) ((Float) r2.getAnimatedValue()).floatValue());
            PeerStoriesView r22 = this.this$0;
            r22.forceUpdateOffsets = true;
            r22.invalidate();
            invalidate();
        }

        private /* synthetic */ void lambda$checkAnimation$1(ValueAnimator r1) {
            this.messageEditText.setOffsetY(((Float) r1.getAnimatedValue()).floatValue());
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public boolean areLiveCommentsFree() {
            return PeerStoriesView.m19567$$Nest$mdisabledPaidFeatures(this.this$0, true);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public void checkAnimation() {
            int r0 = getBackgroundTop();
            int r1 = this.chatActivityEnterViewAnimateFromTop;
            if (r1 == 0) goto L15;
            if (r0 == r1) goto L15;
            int r8 = (this.animatedTop + r1) - r0;
            setAnimatedTop(r8);
            PeerStoriesView r02 = this.this$0;
            r02.forceUpdateOffsets = true;
            if (PeerStoriesView.m19457$$Nest$fgetchangeBoundAnimator(r02) == null) goto L8;
            PeerStoriesView.m19457$$Nest$fgetchangeBoundAnimator(this.this$0).removeAllListeners();
            PeerStoriesView.m19457$$Nest$fgetchangeBoundAnimator(this.this$0).cancel();
        L8:
            View r03 = this.topView;
            if (r03 != null) goto L11;
        L13:
            this.this$0.invalidate();
            PeerStoriesView.m19532$$Nest$fputchangeBoundAnimator(this.this$0, ValueAnimator.ofFloat(new float[]{r8, 0.0f}));
            PeerStoriesView.m19457$$Nest$fgetchangeBoundAnimator(this.this$0).addUpdateListener(new PeerStoriesView$19$$ExternalSyntheticLambda0(this));
            PeerStoriesView.m19457$$Nest$fgetchangeBoundAnimator(this.this$0).addListener(new AnonymousClass1(this));
            PeerStoriesView.m19457$$Nest$fgetchangeBoundAnimator(this.this$0).setDuration(250);
            PeerStoriesView.m19457$$Nest$fgetchangeBoundAnimator(this.this$0).setInterpolator(ChatListItemAnimator.DEFAULT_INTERPOLATOR);
            PeerStoriesView.m19457$$Nest$fgetchangeBoundAnimator(this.this$0).start();
            this.chatActivityEnterViewAnimateFromTop = 0;
            goto L15
        L11:
            if (r03.getVisibility() != 0) goto L13;
            this.topView.setTranslationY(this.animatedTop + ((1.0f - getTopViewEnterProgress()) * this.topView.getLayoutParams().height));
        L15:
            if (this.shouldAnimateEditTextWithBounds == false) goto L20;
            float r04 = (this.messageEditTextPredrawHeigth - this.messageEditText.getMeasuredHeight()) + (this.messageEditTextPredrawScrollY - this.messageEditText.getScrollY());
            EditTextCaption r12 = this.messageEditText;
            r12.setOffsetY(r12.getOffsetY() - r04);
            ValueAnimator r05 = ValueAnimator.ofFloat(new float[]{this.messageEditText.getOffsetY(), 0.0f});
            r05.addUpdateListener(new PeerStoriesView$19$$ExternalSyntheticLambda1(this));
            Animator r13 = this.messageEditTextAnimator;
            if (r13 == null) goto L19;
            r13.cancel();
        L19:
            this.messageEditTextAnimator = r05;
            r05.setDuration(250);
            r05.setInterpolator(ChatListItemAnimator.DEFAULT_INTERPOLATOR);
            r05.start();
            this.shouldAnimateEditTextWithBounds = false;
            PeerStoriesView.m19593$$Nest$mupdateViewOffsets(this.this$0);
        L20:
            this.lastContentViewHeight = getMeasuredHeight();
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r6) {
            if (isEnabled() == true) goto L35;
            RectF r0 = AndroidUtilities.rectTmp;
            float r1 = getWidth();
            if (PeerStoriesView.m19496$$Nest$fgetpremiumBlockedText2(this.this$0) == null) goto L7;
            float r4 = this.attachLayoutPaddingTranslationX * 1.5f;
        L8:
            r0.set(0.0f, 0.0f, r1 + r4, getHeight());
            boolean r02 = r0.contains(r6.getX(), r6.getY());
            if (r6.getAction() != 0) goto L15;
            if (r02 == false) goto L29;
            if (PeerStoriesView.m19496$$Nest$fgetpremiumBlockedText2(this.this$0) == null) goto L29;
            PeerStoriesView.m19496$$Nest$fgetpremiumBlockedText2(this.this$0).setPressed(true);
        L29:
            if (PeerStoriesView.m19496$$Nest$fgetpremiumBlockedText2(this.this$0) != null) goto L31;
        L33:
            return false;
        L31:
            if (PeerStoriesView.m19496$$Nest$fgetpremiumBlockedText2(this.this$0).isPressed() == false) goto L33;
            return true;
        L15:
            if (r6.getAction() != 1) goto L24;
            if (PeerStoriesView.m19496$$Nest$fgetpremiumBlockedText2(this.this$0) == null) goto L29;
            if (r02 == true) goto L20;
        L22:
            PeerStoriesView.m19496$$Nest$fgetpremiumBlockedText2(this.this$0).setPressed(false);
            goto L29
        L20:
            if (PeerStoriesView.m19496$$Nest$fgetpremiumBlockedText2(this.this$0).isPressed() == false) goto L22;
            PeerStoriesView.m19587$$Nest$mshowPremiumBlockedToast(this.this$0);
            goto L22
        L24:
            if (r6.getAction() != 3) goto L29;
            if (PeerStoriesView.m19496$$Nest$fgetpremiumBlockedText2(this.this$0) == null) goto L29;
            PeerStoriesView.m19496$$Nest$fgetpremiumBlockedText2(this.this$0).setPressed(false);
            goto L29
        L7:
            r4 = 0.0f;
            goto L8
        L35:
            return super.dispatchTouchEvent(r6);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public void extendActionMode(Menu r3) {
            ChatActivity.fillActionModeMenu(r3, null, false, !this.this$0.currentStory.isLive());
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public int getMessagesCount() {
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == false) goto L7;
            return 1;
        L7:
            return super.getMessagesCount();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public long getStarsPrice() {
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == false) goto L7;
            return Math.max(PeerStoriesView.m19574$$Nest$mgetMessageMinPrice(this.this$0), PeerStoriesView.m19485$$Nest$fgetmessageStars(this.this$0));
        L7:
            return super.getStarsPrice();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public void isRecordingStateChanged() {
            super.isRecordingStateChanged();
            checkRecording();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public void onLineCountChanged(int r1, int r2) {
            if (this.this$0.chatActivityEnterView == null) goto L6;
            this.shouldAnimateEditTextWithBounds = true;
            this.messageEditTextPredrawHeigth = this.messageEditText.getMeasuredHeight();
            this.messageEditTextPredrawScrollY = this.messageEditText.getScrollY();
            invalidate();
            this.this$0.invalidate();
            this.chatActivityEnterViewAnimateFromTop = this.this$0.chatActivityEnterView.getBackgroundTop();
            return;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public boolean sendMessage() {
            if (this.sendButtonContainer.getAlpha() >= 0.5f) goto L7;
            openKeyboard();
            return false;
        L7:
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == false) goto L30;
            long r2 = Math.max(PeerStoriesView.m19485$$Nest$fgetmessageStars(this.this$0), PeerStoriesView.m19574$$Nest$mgetMessageMinPrice(this.this$0));
            TLRPC.TL_textWithEntities r0 = getTextWithEntities();
            CharSequence r4 = MessageObject.formatTextWithEntities(r0, false, new TextPaint());
            if (r4.length() <= HighlightMessageSheet.getMaxLength(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0))) goto L16;
            NumberTextView r02 = this.captionLimitView;
            if (r02 == null) goto L14;
            AndroidUtilities.shakeViewSpring(r02, 3.5f);
            this.captionLimitView.performHapticFeedback(3, 2);     // Catch: Exception -> L31
        L14:
            return false;
        L16:
            if (PeerStoriesView.m19567$$Nest$mdisabledPaidFeatures(this.this$0, true) == false) goto L18;
        L27:
            this.this$0.liveCommentsView.send(r0, r2);
            this.messageEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            AndroidUtilities.hideKeyboard(this);
            PeerStoriesView.m19544$$Nest$fputmessageStars(this.this$0, 0);
            PeerStoriesView.m19562$$Nest$mcheckStealthMode(this.this$0, true);
            checkSendButton(true);
            return true;
        L18:
            if ((r4 instanceof Spannable) == false) goto L20;
            Spannable r5 = (Spannable) r4;
            int r7 = ((AnimatedEmojiSpan[]) r5.getSpans(0, r4.length(), AnimatedEmojiSpan.class)).length + ((Emoji.EmojiSpan[]) r5.getSpans(0, r4.length(), Emoji.EmojiSpan.class)).length;
        L21:
            int r8 = (int) r2;
            if (r7 <= HighlightMessageSheet.getTierOption(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0), r8, HighlightMessageSheet.TIER_EMOJIS)) goto L24;
        L25:
            PeerStoriesView.m19579$$Nest$monHighlightLiveMessage(this.this$0);
            return false;
        L24:
            if (r4.length() <= HighlightMessageSheet.getTierOption(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0), r8, HighlightMessageSheet.TIER_LENGTH)) goto L27;
        L20:
            r7 = 0;
            goto L21
        L30:
            return super.sendMessage();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public boolean sendMessageInternal(boolean r8, int r9, int r10, long r11, boolean r13) {
            if (MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).isFrozen() == false) goto L7;
            AccountFrozenAlert.show(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0));
            return false;
        L7:
            return super.sendMessageInternal(r8, r9, r10, r11, r13);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public void setHorizontalPadding(float r3, float r4, float r5, boolean r6) {
            if (PeerStoriesView.m19495$$Nest$fgetpremiumBlockedText(this.this$0) == null) goto L5;
            PeerStoriesView.m19495$$Nest$fgetpremiumBlockedText(this.this$0).setTranslationX((1.0f - r5) * r3);
        L5:
            super.setHorizontalPadding(r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public boolean showConfirmAlert(Runnable r1) {
            return PeerStoriesView.m19560$$Nest$mapplyMessageToChat(this.this$0, r1);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public void updateRecordInterface(int r1, boolean r2) {
            super.updateRecordInterface(r1, r2);
            checkRecording();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView
        public void updateSendAsButton(boolean r3) {
            PeerStoriesView r0 = this.this$0;
            if (r0.isPremiumBlocked == false) goto L5;
        L8:
            boolean r02 = true;
        L9:
            super.updateSendAsButton(r02, r3);
            return;
        L5:
            if (r0.areLiveCommentsDisabled == true) goto L8;
            r02 = false;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$2, reason: invalid class name */
    public class AnonymousClass2 extends ImageReceiver {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass2(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.messenger.ImageReceiver
        public boolean setImageBitmapByKey(Drawable r1, String r2, int r3, boolean r4, int r5) {
            boolean r12 = super.setImageBitmapByKey(r1, r2, r3, r4, r5);
            if (r3 == 1) goto L5;
        L7:
            return r12;
        L5:
            if (PeerStoriesView.m19490$$Nest$fgetonImageReceiverThumbLoaded(this.this$0) == null) goto L7;
            PeerStoriesView.m19490$$Nest$fgetonImageReceiverThumbLoaded(this.this$0).run();
            PeerStoriesView.m19550$$Nest$fputonImageReceiverThumbLoaded(this.this$0, null);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$20, reason: invalid class name */
    public class AnonymousClass20 implements ChatActivityEnterView.ChatActivityEnterViewDelegate {
        final /* synthetic */ PeerStoriesView this$0;

        /* renamed from: $r8$lambda$O_UKnfRnL5tdZ5jznMSMCSd-J30, reason: not valid java name */
        public static /* synthetic */ void m19595$r8$lambda$O_UKnfRnL5tdZ5jznMSMCSdJ30(AnonymousClass20 r0, long r1) {
            r0.lambda$onMessageSend$0(r1);
        }

        public AnonymousClass20(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onMessageSend$0(long r3) {
            PeerStoriesView r2 = this.this$0;
            if (r3 > 0) goto L5;
            boolean r32 = true;
        L6:
            PeerStoriesView.m19559$$Nest$mafterMessageSend(r2, r32);
            return;
        L5:
            r32 = false;
            goto L6
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void didPressAttachButton() {
            PeerStoriesView.m19581$$Nest$mopenAttachMenu(this.this$0);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void didPressSuggestionButton() {
            PeerStoriesView.m19579$$Nest$monHighlightLiveMessage(this.this$0);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public int getContentViewHeight() {
            return this.this$0.getHeight();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public TLRPC.Peer getDefaultSendAs() {
            if (PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0) != null) goto L5;
        L11:
            return null;
        L5:
            if (PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).livePlayer == null) goto L11;
            if (PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).livePlayer.sendAsDisabled() == false) goto L10;
            return null;
        L10:
            return PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).livePlayer.getDefaultSendAs();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public TL_stories.StoryItem getReplyToStory() {
            return this.this$0.currentStory.storyItem;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public TLRPC.TL_channels_sendAsPeers getSendAsPeers() {
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == true) goto L5;
            return null;
        L5:
            if (PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0) == null) goto L12;
            if (PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).livePlayer == null) goto L12;
            if (PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).livePlayer.sendAsDisabled() == false) goto L12;
            return null;
        L12:
            return PeerStoriesView.m19512$$Nest$fgetsendAsPeersObj(this.this$0);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public boolean isVideoRecordingPaused() {
            if (PeerStoriesView.m19468$$Nest$fgetinstantCameraView(this.this$0) != null) goto L5;
            return false;
        L5:
            if (PeerStoriesView.m19468$$Nest$fgetinstantCameraView(this.this$0).isPaused() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needChangeVideoPreviewState(int r2, float r3) {
            if (PeerStoriesView.m19468$$Nest$fgetinstantCameraView(this.this$0) == null) goto L6;
            PeerStoriesView.m19468$$Nest$fgetinstantCameraView(this.this$0).changeVideoPreviewState(r2, r3);
            return;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needSendTyping() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needShowMediaBanHint() {
            PeerStoriesView r0 = this.this$0;
            if (r0.isGroup == false) goto L7;
            PeerStoriesView.m19587$$Nest$mshowPremiumBlockedToast(r0);
            return;
        L7:
            if (PeerStoriesView.m19483$$Nest$fgetmediaBanTooltip(r0) != null) goto L9;
            PeerStoriesView.m19543$$Nest$fputmediaBanTooltip(this.this$0, new HintView(this.this$0.getContext(), 9, PeerStoriesView.m19508$$Nest$fgetresourcesProvider(this.this$0)));
            PeerStoriesView.m19483$$Nest$fgetmediaBanTooltip(this.this$0).setVisibility(8);
            PeerStoriesView r02 = this.this$0;
            r02.addView(PeerStoriesView.m19483$$Nest$fgetmediaBanTooltip(r02), LayoutHelper.createFrame(-2, -2.0f, 51, 10.0f, 0.0f, 10.0f, 0.0f));
        L9:
            long r03 = PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0);
            PeerStoriesView r1 = this.this$0;
            if (r03 < 0) goto L12;
            String r04 = UserObject.getFirstName(MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r1)).getUser(Long.valueOf(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0))));
        L16:
            HintView r12 = PeerStoriesView.m19483$$Nest$fgetmediaBanTooltip(this.this$0);
            if (this.this$0.chatActivityEnterView.isInVideoMode() == false) goto L19;
            int r2 = R.string.VideoMessagesRestrictedByPrivacy;
        L20:
            r12.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r2, new Object[]{r04})));
            PeerStoriesView.m19483$$Nest$fgetmediaBanTooltip(this.this$0).showForView(this.this$0.chatActivityEnterView.getAudioVideoButtonContainer(), true);
            return;
        L19:
            r2 = R.string.VoiceMessagesRestrictedByPrivacy;
            goto L20
        L12:
            TLRPC.Chat r05 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r1)).getChat(Long.valueOf(-PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0)));
            if (r05 == null) goto L15;
            r04 = r05.title;
            goto L16
        L15:
            r04 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L16
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needStartRecordAudio(int r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void needStartRecordVideo(int r11, boolean r12, int r13, int r14, int r15, long r16, long r18) {
            PeerStoriesView.m19561$$Nest$mcheckInstantCameraView(this.this$0);
            if (PeerStoriesView.m19468$$Nest$fgetinstantCameraView(this.this$0) == null) goto L26;
            boolean r142 = false;
            if (r11 != 0) goto L9;
            PeerStoriesView.m19468$$Nest$fgetinstantCameraView(this.this$0).showCamera(false);
            return;
        L9:
            if (r11 != 1) goto L11;
        L24:
            PeerStoriesView.m19468$$Nest$fgetinstantCameraView(this.this$0).send(r11, r12, r13, 0, r15, r16, r18);
            return;
        L11:
            if (r11 == 3) goto L24;
            if (r11 == 4) goto L24;
            if (r11 != 2) goto L18;
        L19:
            InstantCameraView r10 = PeerStoriesView.m19468$$Nest$fgetinstantCameraView(this.this$0);
            if (r11 != 2) goto L22;
            r142 = true;
        L22:
            r10.cancel(r142);
            return;
        L18:
            if (r11 == 5) goto L19;
            return;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onAttachButtonHidden() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onAttachButtonShow() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onAudioVideoInterfaceUpdated() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onMessageEditEnd(boolean r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onMessageSend(CharSequence r1, boolean r2, int r3, int r4, long r5) {
            if (PeerStoriesView.m19473$$Nest$fgetisRecording(this.this$0) == false) goto L6;
            AndroidUtilities.runOnUIThread(new PeerStoriesView$20$$ExternalSyntheticLambda0(this, r5), 200);
            return;
        L6:
            PeerStoriesView r0 = this.this$0;
            if (r5 > 0) goto L9;
            boolean r12 = true;
        L10:
            PeerStoriesView.m19559$$Nest$mafterMessageSend(r0, r12);
            return;
        L9:
            r12 = false;
            goto L10
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onPreAudioVideoRecord() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onSendLongClick() {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onStickersExpandedChange() {
            this.this$0.requestLayout();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onStickersTab(boolean r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onSwitchRecordMode(boolean r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onTextChanged(CharSequence r7, boolean r8, boolean r9) {
            if (PeerStoriesView.m19484$$Nest$fgetmentionContainer(this.this$0) != null) goto L6;
            PeerStoriesView.m19563$$Nest$mcreateMentionsContainer(this.this$0);
        L6:
            if (PeerStoriesView.m19484$$Nest$fgetmentionContainer(this.this$0).getAdapter() == null) goto L11;
            PeerStoriesView.m19484$$Nest$fgetmentionContainer(this.this$0).setDialogId(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0));
            boolean r82 = StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory);
            PeerStoriesView r92 = this.this$0;
            if (r82 == false) goto L10;
            PeerStoriesView.m19484$$Nest$fgetmentionContainer(r92).getAdapter().clear(true);
            goto L11
        L10:
            PeerStoriesView.m19484$$Nest$fgetmentionContainer(r92).getAdapter().setUserOrChat(MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getUser(Long.valueOf(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0))), MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getChat(Long.valueOf(-PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0))));
            PeerStoriesView.m19484$$Nest$fgetmentionContainer(this.this$0).getAdapter().searchUsernameOrHashtag(r7, this.this$0.chatActivityEnterView.getCursorPosition(), null, false, false);
        L11:
            this.this$0.invalidate();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onTextSelectionChanged(int r1, int r2) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onTextSpansChanged(CharSequence r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onUpdateSlowModeButton(View r1, boolean r2, CharSequence r3) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void onWindowSizeChanged(int r1) {
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public boolean onceVoiceAvailable() {
            if (PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0) < 0) goto L12;
            TLRPC.User r4 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getUser(Long.valueOf(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0)));
            if (r4 == null) goto L12;
            if (UserObject.isUserSelf(r4) == true) goto L12;
            if (r4.bot == true) goto L12;
            return true;
        L12:
            return false;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public boolean setDefaultSendAs(long r3, long r5) {
            TL_stories.StoryItem r32 = this.this$0.currentStory.storyItem;
            if (r32 != null) goto L5;
        L10:
            return true;
        L5:
            if ((r32.media instanceof TLRPC.TL_messageMediaVideoStream) == false) goto L10;
            TL_phone.saveDefaultSendAs r33 = new TL_phone.saveDefaultSendAs();
            PeerStoriesView r0 = this.this$0;
            r33.call = ((TLRPC.TL_messageMediaVideoStream) r0.currentStory.storyItem.media).call;
            r33.send_as = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r0)).getInputPeer(r5);
            ConnectionsManager.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r33, null);
            if (PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).livePlayer == null) goto L9;
            PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).livePlayer.setDefaultSendAs(MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getPeer(r5));
        L9:
            PeerStoriesView.m19562$$Nest$mcheckStealthMode(this.this$0, true);
            this.this$0.chatActivityEnterView.updateSendAsButton(true);
            this.this$0.chatActivityEnterView.checkSendButton(true);
            this.this$0.updatePosition();
            goto L10
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void setFrontface(boolean r2) {
            if (PeerStoriesView.m19468$$Nest$fgetinstantCameraView(this.this$0) == null) goto L6;
            PeerStoriesView.m19468$$Nest$fgetinstantCameraView(this.this$0).setFrontface(r2);
            return;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.ChatActivityEnterViewDelegate
        public void toggleVideoRecordingPause() {
            if (PeerStoriesView.m19468$$Nest$fgetinstantCameraView(this.this$0) == null) goto L6;
            PeerStoriesView.m19468$$Nest$fgetinstantCameraView(this.this$0).togglePause();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$21, reason: invalid class name */
    public class AnonymousClass21 extends MentionsContainerView {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass21(PeerStoriesView r1, Context r2, long r3, long r5, BaseFragment r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r5, r7, r8);
        }

        @Override // org.telegram.ui.Components.MentionsContainerView
        public void drawRoundRect(Canvas r7, Rect r8, float r9) {
            PeerStoriesView.m19455$$Nest$fgetbitmapShaderTools(this.this$0).setBounds(getX(), -getY(), getX() + getMeasuredWidth(), (-getY()) + getMeasuredHeight());
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(r8);
            r0.offset(0.0f, 0.0f);
            r7.drawRoundRect(r0, r9, r9, PeerStoriesView.m19455$$Nest$fgetbitmapShaderTools(this.this$0).paint);
            r7.drawRoundRect(r0, r9, r9, this.this$0.inputBackgroundPaint);
            if (r0.top >= (getMeasuredHeight() - 1)) goto L6;
            r7.drawRect(0.0f, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight() - 1, PeerStoriesView.m19508$$Nest$fgetresourcesProvider(this.this$0).getPaint("paintDivider"));
            return;
        }

        @Override // org.telegram.ui.Components.MentionsContainerView
        public boolean isStories() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$22, reason: invalid class name */
    public class AnonymousClass22 implements MentionsContainerView.Delegate {
        final /* synthetic */ PeerStoriesView this$0;

        /* renamed from: $r8$lambda$r5IF4-jpaZ2_p5krM2IpskTR_Tk, reason: not valid java name */
        public static /* synthetic */ void m19596$r8$lambda$r5IF4jpaZ2_p5krM2IpskTR_Tk(AnonymousClass22 r0, TLRPC.BotInlineResult r1, boolean r2, int r3, Long r4) {
            r0.lambda$sendBotInlineResult$1(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$vY6yKBhDWZfaoJJyEiM2JPsie2Y(AnonymousClass22 r0, TLRPC.TL_document r1, String r2, Object r3, Long r4) {
            r0.lambda$onStickerSelected$0(r1, r2, r3, r4);
        }

        public AnonymousClass22(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onStickerSelected$0(TLRPC.TL_document r24, String r25, Object r26, Long r27) {
            SendMessagesHelper.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).sendSticker(r24, r25, PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), null, null, this.this$0.currentStory.storyItem, null, null, true, 0, 0, false, r26, null, r27.longValue(), this.this$0.chatActivityEnterView.getSendMonoForumPeerId(), this.this$0.chatActivityEnterView.getSendMessageSuggestionParams());
            this.this$0.chatActivityEnterView.addStickerToRecent(r24);
            this.this$0.chatActivityEnterView.setFieldText(_UrlKt.FRAGMENT_ENCODE_SET);
            PeerStoriesView r0 = this.this$0;
            if (r27.longValue() > 0) goto L5;
            boolean r1 = true;
        L6:
            PeerStoriesView.m19559$$Nest$mafterMessageSend(r0, r1);
            return;
        L5:
            r1 = false;
            goto L6
        }

        private /* synthetic */ void lambda$sendBotInlineResult$1(TLRPC.BotInlineResult r26, boolean r27, int r28, Long r29) {
            long r1 = PeerStoriesView.m19484$$Nest$fgetmentionContainer(this.this$0).getAdapter().getContextBotId();
            HashMap r4 = new HashMap();
            r4.put("id", r26.id);
            r4.put("query_id", _UrlKt.FRAGMENT_ENCODE_SET + r26.query_id);
            r4.put("bot", _UrlKt.FRAGMENT_ENCODE_SET + r1);
            r4.put("bot_name", PeerStoriesView.m19484$$Nest$fgetmentionContainer(this.this$0).getAdapter().getContextBotName());
            SendMessagesHelper.prepareSendingBotContextResult(PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).fragment, PeerStoriesView.m19571$$Nest$mgetAccountInstance(this.this$0), r26, r4, PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), null, null, this.this$0.currentStory.storyItem, null, r27, r28, 0, null, 0, r29.longValue());
            this.this$0.chatActivityEnterView.setFieldText(_UrlKt.FRAGMENT_ENCODE_SET);
            PeerStoriesView r12 = this.this$0;
            if (r29.longValue() > 0) goto L5;
            boolean r2 = true;
        L6:
            PeerStoriesView.m19559$$Nest$mafterMessageSend(r12, r2);
            MediaDataController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).increaseInlineRating(r1);
            return;
        L5:
            r2 = false;
            goto L6
        }

        @Override // org.telegram.ui.Components.MentionsContainerView.Delegate
        public void addEmojiToRecent(String r1) {
            this.this$0.chatActivityEnterView.addEmojiToRecent(r1);
        }

        @Override // org.telegram.ui.Components.MentionsContainerView.Delegate
        public Paint.FontMetricsInt getFontMetrics() {
            return this.this$0.chatActivityEnterView.getEditField().getPaint().getFontMetricsInt();
        }

        @Override // org.telegram.ui.Components.MentionsContainerView.Delegate
        public void onStickerSelected(TLRPC.TL_document r5, String r6, Object r7) {
            AlertsCreator.ensurePaidMessageConfirmation(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0), PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), 1, new PeerStoriesView$22$$ExternalSyntheticLambda1(this, r5, r6, r7));
        }

        @Override // org.telegram.ui.Components.MentionsContainerView.Delegate
        public void replaceText(int r1, int r2, CharSequence r3, boolean r4) {
            this.this$0.chatActivityEnterView.replaceWithText(r1, r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.MentionsContainerView.Delegate
        public void sendBotInlineResult(TLRPC.BotInlineResult r5, boolean r6, int r7) {
            AlertsCreator.ensurePaidMessageConfirmation(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0), PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), 1, new PeerStoriesView$22$$ExternalSyntheticLambda0(this, r5, r6, r7));
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$23, reason: invalid class name */
    public class AnonymousClass23 extends ChatAttachAlert {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass23(PeerStoriesView r1, Context r2, BaseFragment r3, boolean r4, boolean r5, boolean r6, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public void onDismissAnimationStart() {
            if (PeerStoriesView.m19458$$Nest$fgetchatAttachAlert(this.this$0) == null) goto L5;
            PeerStoriesView.m19458$$Nest$fgetchatAttachAlert(this.this$0).setFocusable(false);
        L5:
            ChatActivityEnterView r0 = this.this$0.chatActivityEnterView;
            if (r0 != null) goto L8;
            return;
        L8:
            if (r0.getEditField() == null) goto L12;
            this.this$0.chatActivityEnterView.getEditField().requestFocus();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$24, reason: invalid class name */
    public class AnonymousClass24 implements ChatAttachAlert.ChatAttachViewDelegate {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass24(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r36, boolean r37, boolean r38, int r39, int r40, long r41, boolean r43, boolean r44, long r45) {
            if (PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).isShowing == false) goto L74;
            PeerStoriesView r2 = this.this$0;
            TL_stories.StoryItem r10 = r2.currentStory.storyItem;
            if (r10 != null) goto L8;
            return;
        L8:
            if ((r10 instanceof TL_stories.TL_storyItemSkipped) == false) goto L11;
            return;
        L11:
            if (r36 == 8) goto L23;
            if (r36 == 7) goto L23;
            if (r36 != 4) goto L19;
            if (PeerStoriesView.m19458$$Nest$fgetchatAttachAlert(r2).getPhotoLayout().getSelectedPhotos().isEmpty() == false) goto L23;
        L19:
            if (PeerStoriesView.m19458$$Nest$fgetchatAttachAlert(this.this$0) == null) goto L72;
            PeerStoriesView.m19458$$Nest$fgetchatAttachAlert(this.this$0).dismissWithButtonClick(r36);
            return;
        L72:
            return;
        L23:
            if (r36 == 8) goto L25;
            PeerStoriesView.m19458$$Nest$fgetchatAttachAlert(this.this$0).dismiss(true);
        L25:
            HashMap<Object, Object> r4 = PeerStoriesView.m19458$$Nest$fgetchatAttachAlert(this.this$0).getPhotoLayout().getSelectedPhotos();
            ArrayList<Object> r5 = PeerStoriesView.m19458$$Nest$fgetchatAttachAlert(this.this$0).getPhotoLayout().getSelectedPhotosOrder();
            if (r4.isEmpty() == true) goto L73;
            int r6 = 0;
            int r7 = 0;
        L29:
            if (r7 >= Math.ceil(r4.size() / 10.0f)) goto L59;
            int r9 = r7 * 10;
            int r8 = Math.min(10, r4.size() - r9);
            ArrayList r11 = new ArrayList();
            int r12 = r6;
        L31:
            if (r12 >= r8) goto L49;
            int r13 = r9 + r12;
            if (r13 >= r5.size()) goto L48;
            MediaController.PhotoEntry r132 = (MediaController.PhotoEntry) r4.get(r5.get(r13));
            SendMessagesHelper.SendingMediaInfo r14 = new SendMessagesHelper.SendingMediaInfo();
            boolean r15 = r132.isVideo;
            if (r15 == true) goto L40;
            String r22 = r132.imagePath;
            if (r22 == null) goto L40;
            r14.path = r22;
        L43:
            r14.thumbPath = r132.thumbPath;
            r14.coverPath = r132.coverPath;
            r14.isVideo = r15;
            CharSequence r23 = r132.caption;
            if (r23 == null) goto L46;
            String r24 = r23.toString();
        L47:
            r14.caption = r24;
            r14.entities = r132.entities;
            r14.masks = r132.stickers;
            r14.ttl = r132.ttl;
            r14.videoEditedInfo = r132.editedInfo;
            r14.canDeleteAfter = r132.canDeleteAfter;
            r14.updateStickersOrder = SendMessagesHelper.checkUpdateStickersOrder(r132.caption);
            r14.hasMediaSpoilers = r132.hasSpoiler;
            r11.add(r14);
            r132.reset();
            goto L48
        L46:
            r24 = null;
        L40:
            String r25 = r132.path;
            if (r25 == null) goto L43;
            r14.path = r25;
        L48:
            r12 = r12 + 1;
            goto L31
        L49:
            if (r7 != 0) goto L51;
            boolean r19 = ((SendMessagesHelper.SendingMediaInfo) r11.get(r6)).updateStickersOrder;
        L52:
            AccountInstance r26 = PeerStoriesView.m19571$$Nest$mgetAccountInstance(this.this$0);
            long r82 = PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0);
            if (r36 == 4) goto L57;
            if (r44 == true) goto L57;
            boolean r122 = r6;
        L58:
            SendMessagesHelper.prepareSendingMedia(r26, r11, r82, null, null, r10, null, r122, r37, null, r38, r39, r40, 0, r19, null, null, 0, false, 0, this.this$0.chatActivityEnterView.getSendMonoForumPeerId(), this.this$0.chatActivityEnterView.getSendMessageSuggestionParams());
            r7 = r7 + 1;
            r4 = r4;
            r5 = r5;
            r6 = r6;
        L57:
            r122 = 1;
            goto L58
        L51:
            r19 = r6;
            goto L52
        L59:
            int r32 = r6;
            this.this$0.chatActivityEnterView.setFieldText(_UrlKt.FRAGMENT_ENCODE_SET);
            PeerStoriesView r0 = this.this$0;
            if (r45 > 0) goto L62;
            boolean r27 = 1;
        L63:
            PeerStoriesView.m19559$$Nest$mafterMessageSend(r0, r27);
            return;
        L62:
            r27 = r32;
            goto L63
        L73:
            return;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void doOnIdle(Runnable r1) {
            NotificationCenter.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).doOnIdle(r1);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public boolean needEnterComment() {
            return this.this$0.needEnterText();
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void onCameraOpened() {
            this.this$0.chatActivityEnterView.closeKeyboard();
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void sendAudio(ArrayList<MessageObject> r22, CharSequence r23, boolean r24, int r25, int r26, long r27, boolean r29, long r30) {
            PeerStoriesView r1 = this.this$0;
            TL_stories.StoryItem r10 = r1.currentStory.storyItem;
            if (r10 != null) goto L5;
            return;
        L5:
            if ((r10 instanceof TL_stories.TL_storyItemSkipped) == true) goto L18;
            AccountInstance r3 = PeerStoriesView.m19571$$Nest$mgetAccountInstance(r1);
            if (r23 == null) goto L10;
            CharSequence r5 = r23;
        L11:
            SendMessagesHelper.prepareSendingAudioDocuments(r3, r22, r5, PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), null, null, r10, r24, r25, r26, null, null, r27, r29, r30);
            PeerStoriesView r0 = this.this$0;
            if (r30 > 0) goto L14;
            boolean r12 = true;
        L15:
            PeerStoriesView.m19559$$Nest$mafterMessageSend(r0, r12);
            return;
        L14:
            r12 = false;
            goto L15
        L10:
            r5 = null;
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$25, reason: invalid class name */
    public class AnonymousClass25 implements ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass25(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
        public void didSelectFiles(ArrayList<String> r26, String r27, ArrayList<TLRPC.MessageEntity> r28, ArrayList<MessageObject> r29, boolean r30, int r31, int r32, long r33, boolean r35, long r36) {
            PeerStoriesView r1 = this.this$0;
            TL_stories.StoryItem r13 = r1.currentStory.storyItem;
            if (r13 != null) goto L5;
            return;
        L5:
            if ((r13 instanceof TL_stories.TL_storyItemSkipped) == true) goto L14;
            SendMessagesHelper.prepareSendingDocuments(PeerStoriesView.m19571$$Nest$mgetAccountInstance(r1), r26, r26, null, r27, null, PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), null, null, r13, null, null, r30, r31, null, null, 0, false, r36);
            PeerStoriesView r0 = this.this$0;
            if (r36 > 0) goto L10;
            boolean r12 = true;
        L11:
            PeerStoriesView.m19559$$Nest$mafterMessageSend(r0, r12);
            return;
        L10:
            r12 = false;
            goto L11
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
        public void startDocumentSelectActivity() {
            Intent r0 = new Intent("android.intent.action.GET_CONTENT");     // Catch: Exception -> L4
            r0.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);     // Catch: Exception -> L4
            r0.setType("*/*");     // Catch: Exception -> L4
            PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).startActivityForResult(r0, 21);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$26, reason: invalid class name */
    public class AnonymousClass26 extends WrappedResourceProvider {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass26(PeerStoriesView r1, Theme.ResourcesProvider r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.WrappedResourceProvider
        public void appendColors() {
            this.sparseIntArray.put(Theme.key_chat_emojiPanelBackground, ColorUtils.blendARGB(-16777216, -1, 0.2f));
            this.sparseIntArray.put(Theme.key_chat_messagePanelIcons, ColorUtils.blendARGB(-16777216, -1, 0.5f));
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$27, reason: invalid class name */
    public class AnonymousClass27 extends ShareAlert {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass27(PeerStoriesView r16, Context r17, ChatActivity r18, ArrayList r19, String r20, String r21, String r22, boolean r23, String r24, String r25, boolean r26, boolean r27, boolean r28, Integer r29, Theme.ResourcesProvider r30) {
            this.this$0 = r16;
            super(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30);
        }

        @Override // org.telegram.ui.Components.ShareAlert, org.telegram.ui.ActionBar.BottomSheet
        public void dismissInternal() {
            super.dismissInternal();
            this.this$0.shareAlert = null;
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onSend(LongSparseArray<TLRPC.Dialog> r7, int r8, TLRPC.TL_forumTopic r9, boolean r10) {
            if (r10 == false) goto L26;
            super.onSend(r7, r8, r9, r10);
            BulletinFactory r82 = BulletinFactory.of(this.this$0.storyContainer, this.resourcesProvider);
            if (r82 != null) goto L7;
            return;
        L7:
            if (r7.size() != 1) goto L19;
            long r2 = r7.keyAt(0);
            if (r2 != UserConfig.getInstance(this.currentAccount).clientUserId) goto L11;
            r82.createSimpleBulletin(R.raw.saved_messages, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.StorySharedToSavedMessages, new Object[0])), 5000).hideAfterBottomSheet(false).show();
        L23:
            this.this$0.performHapticFeedback(3);     // Catch: Exception -> L22
            return;
        L27:
            return;
        L11:
            int r0 = this.currentAccount;
            if (r2 >= 0) goto L18;
            TLRPC.Chat r72 = MessagesController.getInstance(r0).getChat(Long.valueOf(-r2));
            int r02 = R.raw.forward;
            int r22 = R.string.StorySharedTo;
            if (r9 == null) goto L16;
            String r73 = r9.title;
        L17:
            r82.createSimpleBulletin(r02, AndroidUtilities.replaceTags(LocaleController.formatString(r22, new Object[]{r73})), 5000).hideAfterBottomSheet(false).show();
            goto L23
        L16:
            r73 = r72.title;
            goto L17
        L18:
            TLRPC.User r74 = MessagesController.getInstance(r0).getUser(Long.valueOf(r2));
            r82.createSimpleBulletin(R.raw.forward, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.StorySharedTo, new Object[]{r74.first_name})), 5000).hideAfterBottomSheet(false).show();
            goto L23
        L19:
            r82.createSimpleBulletin(R.raw.forward, AndroidUtilities.replaceTags(LocaleController.formatPluralString("StorySharedToManyChats", r7.size(), new Object[]{Integer.valueOf(r7.size())}))).hideAfterBottomSheet(false).show();
            goto L23
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onShareStory(View r1) {
            PeerStoriesView.m19589$$Nest$mtryToOpenRepostStory(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$28, reason: invalid class name */
    public class AnonymousClass28 implements ShareAlert.ShareAlertDelegate {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass28(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ShareAlert.ShareAlertDelegate
        public boolean didCopy() {
            PeerStoriesView.m19580$$Nest$monLinkCopied(this.this$0);
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$29, reason: invalid class name */
    public class AnonymousClass29 implements RequestDelegate {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass29(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.tgnet.RequestDelegate
        public void run(TLObject r1, TLRPC.TL_error r2) {
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$3, reason: invalid class name */
    public class AnonymousClass3 extends StoryMediaAreasView {
        final /* synthetic */ PeerStoriesView this$0;
        final /* synthetic */ StoryViewer val$storyViewer;

        public AnonymousClass3(PeerStoriesView r1, Context r2, View r3, Theme.ResourcesProvider r4, StoryViewer r5) {
            this.this$0 = r1;
            this.val$storyViewer = r5;
            super(r2, r3, r4);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return super.dispatchTouchEvent(r1);
        }

        @Override // org.telegram.ui.Stories.StoryMediaAreasView
        public Bitmap getPlayingBitmap() {
            return this.this$0.getPlayingBitmap();
        }

        @Override // org.telegram.ui.Stories.StoryMediaAreasView
        public void onHintVisible(boolean r1) {
            Delegate r0 = this.this$0.delegate;
            if (r0 == null) goto L6;
            r0.setIsHintVisible(r1);
            return;
        }

        @Override // org.telegram.ui.Stories.StoryMediaAreasView, android.view.View
        public boolean onTouchEvent(MotionEvent r1) {
            return super.onTouchEvent(r1);
        }

        @Override // org.telegram.ui.Stories.StoryMediaAreasView
        public void presentFragment(BaseFragment r1) {
            StoryViewer r0 = this.val$storyViewer;
            if (r0 == null) goto L6;
            r0.presentFragment(r1);
            return;
        }

        @Override // org.telegram.ui.Stories.StoryMediaAreasView
        public void showEffect(StoryReactionWidgetView r3) {
            PeerStoriesView r0 = this.this$0;
            if (r0.isSelf == false) goto L5;
        L9:
            r3.performHapticFeedback(3);
            r3.playAnimation();
            PeerStoriesView.m19464$$Nest$fgetemojiAnimationsOverlay(this.this$0).showAnimationForWidget(r3);
            return;
        L5:
            if (r0.currentStory.storyItem == null) goto L9;
            ReactionsLayoutInBubble.VisibleReaction r02 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r3.mediaArea.reaction);
            if (Objects.equals(r02, ReactionsLayoutInBubble.VisibleReaction.fromTL(this.this$0.currentStory.storyItem.sent_reaction)) == true) goto L9;
            PeerStoriesView.m19578$$Nest$mlikeStory(this.this$0, r02);
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$30, reason: invalid class name */
    public class AnonymousClass30 extends FrameLayout {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass30(PeerStoriesView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r3) {
            if (PeerStoriesView.m19509$$Nest$fgetselfAvatarsContainer(this.this$0).getVisibility() != 0) goto L7;
            int r0 = (int) (((PeerStoriesView.m19510$$Nest$fgetselfStatusView(this.this$0).getX() + PeerStoriesView.m19510$$Nest$fgetselfStatusView(this.this$0).getMeasuredWidth()) - PeerStoriesView.m19509$$Nest$fgetselfAvatarsContainer(this.this$0).getX()) + AndroidUtilities.dp(10.0f));
            if (PeerStoriesView.m19509$$Nest$fgetselfAvatarsContainer(this.this$0).getLayoutParams().width == r0) goto L7;
            PeerStoriesView.m19509$$Nest$fgetselfAvatarsContainer(this.this$0).getLayoutParams().width = r0;
            PeerStoriesView.m19509$$Nest$fgetselfAvatarsContainer(this.this$0).invalidate();
            PeerStoriesView.m19509$$Nest$fgetselfAvatarsContainer(this.this$0).requestLayout();
        L7:
            super.dispatchDraw(r3);
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$31, reason: invalid class name */
    public class AnonymousClass31 extends View {
        AnimatedFloat animatedFloat;
        LoadingDrawable loadingDrawable;
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass31(PeerStoriesView r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.loadingDrawable = new LoadingDrawable();
            this.animatedFloat = new AnimatedFloat(250, CubicBezierInterpolator.DEFAULT);
        }

        @Override // android.view.View
        public void onDraw(Canvas r12) {
            super.onDraw(r12);
            this.animatedFloat.setParent(this);
            AnimatedFloat r0 = this.animatedFloat;
            if (this.this$0.showViewsProgress == false) goto L5;
            float r1 = 1.0f;
        L6:
            r0.set(r1, false);
            if (this.animatedFloat.get() != 0.0f) goto L9;
            return;
        L9:
            if (this.animatedFloat.get() == 1.0f) goto L11;
            Canvas r4 = r12;
            r4.saveLayerAlpha(0.0f, 0.0f, getLayoutParams().width, getMeasuredHeight(), (int) (this.animatedFloat.get() * 255.0f), 31);
        L12:
            RectF r122 = AndroidUtilities.rectTmp;
            r122.set(0.0f, 0.0f, getLayoutParams().width, getMeasuredHeight());
            this.loadingDrawable.setBounds(r122);
            this.loadingDrawable.setRadiiDp(24.0f);
            this.loadingDrawable.setColors(ColorUtils.setAlphaComponent(-1, 20), ColorUtils.setAlphaComponent(-1, 50), ColorUtils.setAlphaComponent(-1, 50), ColorUtils.setAlphaComponent(-1, 70));
            this.loadingDrawable.draw(r4);
            invalidate();
            r4.restore();
            return;
        L11:
            r4 = r12;
            r4.save();
            goto L12
        L5:
            r1 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$32, reason: invalid class name */
    public class AnonymousClass32 extends ReplacementSpan {
        private final Paint bg;
        private final RectF rect;
        private final Text text;
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass32(PeerStoriesView r4) {
            this.this$0 = r4;
            this.rect = new RectF();
            this.bg = new Paint(1);
            this.text = new Text(LocaleController.getString(R.string.LiveStoryBadge), 9.0f, AndroidUtilities.bold());
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r7, CharSequence r8, int r9, int r10, float r11, int r12, int r13, int r14, Paint r15) {
            float r3 = ((r12 + r14) / 2.0f) + AndroidUtilities.dp(1.33f);
            this.rect.set(r11, r3 - AndroidUtilities.dp(7.0f), (this.text.getWidth() + r11) + AndroidUtilities.dp(12.0f), AndroidUtilities.dp(7.0f) + r3);
            this.bg.setColor(-572850);
            RectF r82 = this.rect;
            r7.drawRoundRect(r82, r82.height() / 2.0f, this.rect.height() / 2.0f, this.bg);
            this.text.draw(r7, AndroidUtilities.dp(6.0f) + r11, r3, -1, 1.0f);
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return (int) (this.text.getWidth() + AndroidUtilities.dp(12.0f));
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$33, reason: invalid class name */
    public class AnonymousClass33 extends TextView {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass33(PeerStoriesView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$34, reason: invalid class name */
    public class AnonymousClass34 implements Runnable {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass34(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            PeerStoriesView r0 = this.this$0;
            if (r0.isActive == true) goto L5;
            return;
        L5:
            if (PeerStoriesView.m19451$$Nest$fgetallowDrawSurface(r0) == false) goto L9;
            this.this$0.delegate.setIsSwiping(false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$35, reason: invalid class name */
    public class AnonymousClass35 extends AnimatorListenerAdapter {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass35(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            super.onAnimationEnd(r2);
            this.this$0.notificationsLocker.unlock();
            PeerStoriesView.m19531$$Nest$fputanimatingKeyboardHeight(this.this$0, r2.lastKeyboardHeight);
            ChatActivityEnterView r22 = this.this$0.chatActivityEnterView;
            if (r22 == null) goto L5;
            r22.onOverrideAnimationEnd();
        L5:
            PeerStoriesView r23 = this.this$0;
            if (r23.isActive == true) goto L8;
        L10:
            PeerStoriesView r24 = this.this$0;
            if (r24.keyboardVisible == false) goto L13;
        L15:
            PeerStoriesView r1 = this.this$0;
            r1.forceUpdateOffsets = true;
            r1.invalidate();
            return;
        L13:
            if (PeerStoriesView.m19484$$Nest$fgetmentionContainer(r24) == null) goto L15;
            PeerStoriesView.m19484$$Nest$fgetmentionContainer(this.this$0).setVisibility(8);
            goto L15
        L8:
            if (r23.keyboardVisible == true) goto L10;
            r23.delegate.setKeyboardVisible(false);
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$36, reason: invalid class name */
    public class AnonymousClass36 implements InstantCameraView.Delegate {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass36(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.InstantCameraView.Delegate
        public int getClassGuid() {
            return PeerStoriesView.m19459$$Nest$fgetclassGuid(this.this$0);
        }

        @Override // org.telegram.ui.Components.InstantCameraView.Delegate
        public long getDialogId() {
            return PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0);
        }

        @Override // org.telegram.ui.Components.InstantCameraView.Delegate
        public View getFragmentView() {
            return this.this$0;
        }

        @Override // org.telegram.ui.Components.InstantCameraView.Delegate
        public Activity getParentActivity() {
            return AndroidUtilities.findActivity(this.this$0.getContext());
        }

        @Override // org.telegram.ui.Components.InstantCameraView.Delegate
        public void sendMedia(MediaController.PhotoEntry r31, VideoEditedInfo r32, boolean r33, int r34, int r35, boolean r36, long r37) {
            if (r31 == null) goto L29;
            PeerStoriesView r2 = this.this$0;
            TL_stories.StoryItem r12 = r2.currentStory.storyItem;
            if (r12 != null) goto L8;
            return;
        L8:
            if ((r12 instanceof TL_stories.TL_storyItemSkipped) == true) goto L30;
            r12.dialogId = PeerStoriesView.m19463$$Nest$fgetdialogId(r2);
            if (r31.isVideo == false) goto L17;
            PeerStoriesView r22 = this.this$0;
            if (r32 == null) goto L15;
            SendMessagesHelper.prepareSendingVideo(PeerStoriesView.m19571$$Nest$mgetAccountInstance(r22), r31.path, r32, null, null, PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), null, null, r12, null, r31.entities, r31.ttl, null, r33, r34, r35, r36, r31.hasSpoiler, r31.caption, null, 0, r37);
        L22:
            PeerStoriesView r0 = this.this$0;
            if (r37 > 0) goto L25;
            boolean r1 = true;
        L26:
            PeerStoriesView.m19559$$Nest$mafterMessageSend(r0, r1);
            return;
        L25:
            r1 = false;
            goto L26
        L15:
            SendMessagesHelper.prepareSendingVideo(PeerStoriesView.m19571$$Nest$mgetAccountInstance(r22), r31.path, null, null, null, PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), null, null, r12, null, r31.entities, r31.ttl, null, r33, r34, r35, r36, r31.hasSpoiler, r31.caption, null, 0, r37);
            goto L22
        L17:
            if (r31.imagePath == null) goto L20;
            SendMessagesHelper.prepareSendingPhoto(PeerStoriesView.m19571$$Nest$mgetAccountInstance(this.this$0), r31.imagePath, r31.thumbPath, null, PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), null, null, r12, null, r31.entities, r31.stickers, null, r31.ttl, null, r32, r33, r34, r35, r36, r31.caption, null, 0, r37);
            goto L22
        L20:
            if (r31.path == null) goto L22;
            SendMessagesHelper.prepareSendingPhoto(PeerStoriesView.m19571$$Nest$mgetAccountInstance(this.this$0), r31.path, r31.thumbPath, null, PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), null, null, r12, null, r31.entities, r31.stickers, null, r31.ttl, null, r32, r33, r34, r35, r36, r31.caption, null, 0, r37);
            goto L22
        L30:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$37, reason: invalid class name */
    public class AnonymousClass37 extends WrappedResourceProvider {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass37(PeerStoriesView r1, Theme.ResourcesProvider r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.WrappedResourceProvider
        public void appendColors() {
            this.sparseIntArray.put(Theme.key_chat_emojiPanelBackground, ColorUtils.setAlphaComponent(-1, 30));
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$38, reason: invalid class name */
    public class AnonymousClass38 implements ReactionsContainerLayout.ReactionsContainerDelegate {
        final /* synthetic */ PeerStoriesView this$0;

        /* renamed from: $r8$lambda$-dkWTqfgsO8iz93BknNIsZtixHY, reason: not valid java name */
        public static /* synthetic */ void m19597$r8$lambda$dkWTqfgsO8iz93BknNIsZtixHY(AnonymousClass38 r0, boolean r1, ReactionsLayoutInBubble.VisibleReaction r2, View r3, Long r4) {
            r0.lambda$onReactionClickedInternal$2(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$76ZL30jyQIP9lNcWUFmmSBbRFG4(AnonymousClass38 r0, View r1, ReactionsLayoutInBubble.VisibleReaction r2, boolean r3, boolean r4) {
            r0.lambda$onReactionClickedInternal$0(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$pWUbn13BovyAxEwGiDzGDKlGmWI(AnonymousClass38 r0) {
            r0.lambda$onReactionClickedInternal$1();
        }

        public AnonymousClass38(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onReactionClickedInternal$0(View r7, ReactionsLayoutInBubble.VisibleReaction r8, boolean r9, boolean r10) {
            onReactionClickedInternal(r7, r8, r9, r10, false);
        }

        private /* synthetic */ void lambda$onReactionClickedInternal$1() {
            PeerStoriesView.m19582$$Nest$mopenChat(this.this$0);
        }

        private /* synthetic */ void lambda$onReactionClickedInternal$2(boolean r14, ReactionsLayoutInBubble.VisibleReaction r15, View r16, Long r17) {
            if (r14 == true) goto L5;
        L8:
            ReactionsEffectOverlay r0 = new ReactionsEffectOverlay(r16.getContext(), null, this.this$0.reactionsContainerLayout, null, r16, r2.getMeasuredWidth() / 2.0f, this.this$0.getMeasuredHeight() / 2.0f, r15, PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0), 2, true);
        L9:
            ReactionsEffectOverlay.currentOverlay = r0;
            r0.windowView.setTag(R.id.parent_tag, 1);
            this.this$0.addView(r0.windowView);
            r0.started = true;
            r0.startTime = System.currentTimeMillis();
            String r142 = r15.emojicon;
            PeerStoriesView r02 = this.this$0;
            if (r142 == null) goto L12;
            TLRPC.Document r143 = MediaDataController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r02)).getEmojiAnimatedSticker(r15.emojicon);
            SendMessagesHelper.SendMessageParams r03 = SendMessagesHelper.SendMessageParams.of(r15.emojicon, PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0));
            r03.replyToStoryItem = this.this$0.currentStory.storyItem;
            r03.payStars = r17.longValue();
            SendMessagesHelper.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).sendMessage(r03);
        L21:
            if (r17.longValue() > 0) goto L24;
            PeerStoriesView r04 = this.this$0;
            BulletinFactory.of(r04.storyContainer, PeerStoriesView.m19508$$Nest$fgetresourcesProvider(r04)).createEmojiBulletin(r143, LocaleController.getString(R.string.ReactionSent), LocaleController.getString(R.string.ViewInChat), new PeerStoriesView$38$$ExternalSyntheticLambda2(this)).setDuration(5000).show();
        L24:
            if (this.this$0.reactionsContainerLayout.getReactionsWindow() == null) goto L26;
            this.this$0.reactionsContainerLayout.getReactionsWindow().dismissWithAlpha();
        L26:
            this.this$0.closeKeyboardOrEmoji();
            return;
        L12:
            r143 = AnimatedEmojiDrawable.findDocument(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r02), r15.documentId);
            String r05 = MessageObject.findAnimatedEmojiEmoticon(r143, null);
            PeerStoriesView r1 = this.this$0;
            if (r05 == null) goto L15;
            SendMessagesHelper.SendMessageParams r12 = SendMessagesHelper.SendMessageParams.of(r05, PeerStoriesView.m19463$$Nest$fgetdialogId(r1));
            r12.entities = new ArrayList();
            TLRPC.TL_messageEntityCustomEmoji r2 = new TLRPC.TL_messageEntityCustomEmoji();
            r2.document_id = r15.documentId;
            r2.offset = 0;
            r2.length = r05.length();
            r12.entities.add(r2);
            r12.replyToStoryItem = this.this$0.currentStory.storyItem;
            r12.payStars = r17.longValue();
            SendMessagesHelper.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).sendMessage(r12);
            goto L21
        L15:
            if (r1.reactionsContainerLayout.getReactionsWindow() == null) goto L17;
            this.this$0.reactionsContainerLayout.getReactionsWindow().dismissWithAlpha();
        L17:
            this.this$0.closeKeyboardOrEmoji();
            return;
        L5:
            if (r15.emojicon == null) goto L8;
            this.this$0.performHapticFeedback(0);     // Catch: Exception -> L28
        L7:
            r0 = new ReactionsEffectOverlay(r16.getContext(), null, this.this$0.reactionsContainerLayout, null, r16, r2.getMeasuredWidth() / 2.0f, this.this$0.getMeasuredHeight() / 2.0f, r15, PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0), 0, true);
            goto L9
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public void drawRoundRect(Canvas r2, RectF r3, float r4, float r5, float r6, int r7, boolean r8) {
            float r52 = -r5;
            float r62 = -r6;
            PeerStoriesView.m19455$$Nest$fgetbitmapShaderTools(this.this$0).setBounds(r52, r62, this.this$0.getMeasuredWidth() + r52, this.this$0.getMeasuredHeight() + r62);
            PeerStoriesView r63 = this.this$0;
            if (r4 <= 0.0f) goto L6;
            r2.drawRoundRect(r3, r4, r4, PeerStoriesView.m19455$$Nest$fgetbitmapShaderTools(r63).paint);
            r2.drawRoundRect(r3, r4, r4, this.this$0.inputBackgroundPaint);
            return;
        L6:
            r2.drawRect(r3, PeerStoriesView.m19455$$Nest$fgetbitmapShaderTools(r63).paint);
            r2.drawRect(r3, this.this$0.inputBackgroundPaint);
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public boolean needEnterText() {
            return this.this$0.needEnterText();
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public void onEmojiWindowDismissed() {
            this.this$0.delegate.requestAdjust(false);
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public void onReactionClicked(View r7, ReactionsLayoutInBubble.VisibleReaction r8, boolean r9, boolean r10) {
            onReactionClickedInternal(r7, r8, r9, r10, !r9);
        }

        public void onReactionClickedInternal(View r7, ReactionsLayoutInBubble.VisibleReaction r8, boolean r9, boolean r10, boolean r11) {
            if (r11 == false) goto L6;
            AnonymousClass38 r1 = this;
            View r2 = r7;
            ReactionsLayoutInBubble.VisibleReaction r3 = r8;
            boolean r4 = r9;
            if (PeerStoriesView.m19560$$Nest$mapplyMessageToChat(this.this$0, new PeerStoriesView$38$$ExternalSyntheticLambda0(r1, r2, r3, r4, r10)) == false) goto L7;
            return;
        L7:
            AlertsCreator.ensurePaidMessageConfirmation(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r1.this$0), PeerStoriesView.m19463$$Nest$fgetdialogId(r1.this$0), 1, new PeerStoriesView$38$$ExternalSyntheticLambda1(r1, r4, r3, r2));
            return;
        L6:
            r1 = this;
            r2 = r7;
            r3 = r8;
            r4 = r9;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$39, reason: invalid class name */
    public class AnonymousClass39 extends WrappedResourceProvider {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass39(PeerStoriesView r1, Theme.ResourcesProvider r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.WrappedResourceProvider
        public void appendColors() {
            this.sparseIntArray.put(Theme.key_chat_emojiPanelBackground, ColorUtils.setAlphaComponent(-1, 30));
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$4, reason: invalid class name */
    public class AnonymousClass4 extends HwFrameLayout {
        boolean drawOverlayed;
        final CellFlickerDrawable loadingDrawable;
        final AnimatedFloat loadingDrawableAlpha;
        final AnimatedFloat loadingDrawableAlpha2;
        final AnimatedFloat progressToAudio;
        final AnimatedFloat progressToFullBlackoutA;
        boolean splitDrawing;
        final /* synthetic */ PeerStoriesView this$0;
        final /* synthetic */ SharedResources val$sharedResources;
        final /* synthetic */ StoryViewer val$storyViewer;

        /* renamed from: org.telegram.ui.Stories.PeerStoriesView$4$1, reason: invalid class name */
        public class AnonymousClass1 implements Bulletin.Delegate {
            final /* synthetic */ AnonymousClass4 this$1;

            public AnonymousClass1(AnonymousClass4 r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public boolean clipWithGradient(int r2) {
                if (r2 != 1) goto L5;
                return true;
            L5:
                if (r2 != 2) goto L7;
                return true;
            L7:
                if (r2 == 3) goto L13;
                return false;
            L13:
                return true;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getBottomOffset(int r1) {
                if (PeerStoriesView.m19448$$Nest$fgetBIG_SCREEN(this.this$1.this$0) == false) goto L7;
                return 0;
            L7:
                return AndroidUtilities.dp(64.0f);
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getTopOffset(int r1) {
                return AndroidUtilities.dp(58.0f);
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public void onHide(Bulletin r2) {
                if (r2 != null) goto L4;
                return;
            L4:
                if (r2.tag != 2) goto L10;
                Delegate r1 = this.this$1.this$0.delegate;
                if (r1 == null) goto L11;
                r1.setBulletinIsVisible(false);
                return;
            L11:
                return;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public void onShow(Bulletin r2) {
                if (r2 != null) goto L4;
                return;
            L4:
                if (r2.tag != 2) goto L10;
                Delegate r1 = this.this$1.this$0.delegate;
                if (r1 == null) goto L11;
                r1.setBulletinIsVisible(true);
                return;
            L11:
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$YMbgCUMQfj0WoJMGWVfs9nBVSr8(AnonymousClass4 r0) {
            r0.lambda$drawLines$0();
        }

        public AnonymousClass4(PeerStoriesView r1, Context r2, SharedResources r3, StoryViewer r4) {
            this.this$0 = r1;
            this.val$sharedResources = r3;
            this.val$storyViewer = r4;
            super(r2);
            CubicBezierInterpolator r22 = CubicBezierInterpolator.DEFAULT;
            this.progressToAudio = new AnimatedFloat(this, 150, r22);
            this.progressToFullBlackoutA = new AnimatedFloat(this, 150, r22);
            this.loadingDrawable = new CellFlickerDrawable(32, 102, 240);
            AnimatedFloat r12 = new AnimatedFloat(this);
            this.loadingDrawableAlpha2 = r12;
            AnimatedFloat r23 = new AnimatedFloat(this);
            this.loadingDrawableAlpha = r23;
            r12.setDuration(500);
            r23.setDuration(100);
        }

        private void drawLines(Canvas r21) {
            if (PeerStoriesView.m19467$$Nest$fgetimageReceiver(this.this$0).hasNotThumb() == false) goto L5;
        L12:
            this.this$0.currentStory.checkSendView();
        L13:
            float r0 = PeerStoriesView.m19573$$Nest$mgetHideInterfaceAlpha(this.this$0);
            boolean r1 = this.this$0.currentStory.isVideo();
            PeerStoriesView r2 = this.this$0;
            if (r1 == false) goto L26;
            StoryViewer.VideoPlayerHolder r12 = r2.playerSharedScope.player;
            if (r12 == null) goto L22;
            float r13 = Utilities.clamp(r12.getPlaybackProgress(r2.videoDuration), 1.0f, 0.0f);
            PeerStoriesView r22 = this.this$0;
            if (r22.playerSharedScope.firstFrameRendered == true) goto L20;
        L23:
            invalidate();
        L24:
            float r132 = r13;
            PeerStoriesView r14 = this.this$0;
            VideoPlayerSharedScope r23 = r14.playerSharedScope;
            if (r23 == null) goto L58;
            StoryViewer.VideoPlayerHolder r24 = r23.player;
            if (r24 == null) goto L58;
            float r25 = r24.currentSeek;
            if (r25 < 0.0f) goto L58;
            float r19 = r25;
        L59:
            boolean r9 = true;
            if (PeerStoriesView.m19525$$Nest$fgetswitchEventSent(r14) == false) goto L62;
        L70:
            StoriesController.StoriesList r15 = this.val$storyViewer.storiesList;
            if (r15 != null) goto L73;
        L78:
            r21.save();
            r21.translate(0.0f, AndroidUtilities.dp(8.0f) - (AndroidUtilities.dp(8.0f) * PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0)));
            if (this.this$0.currentStory.isVideo() == true) goto L81;
        L83:
            boolean r17 = false;
        L85:
            if (PeerStoriesView.m19472$$Nest$fgetisLongPressed(this.this$0) == false) goto L95;
            StoryItemHolder r02 = this.this$0.currentStory;
            if (r02 == null) goto L95;
            if (StoryItemHolder.m19635$$Nest$fgetisVideo(r02) == false) goto L95;
            StoryViewer r03 = this.val$storyViewer;
            if (r03 == null) goto L95;
            if (r03.inSeekingMode == false) goto L95;
            boolean r18 = true;
        L96:
            AnimatedFloat r04 = PeerStoriesView.m19479$$Nest$fgetlinesAlpha(this.this$0);
            if (PeerStoriesView.m19472$$Nest$fgetisLongPressed(this.this$0) == false) goto L101;
            if (r18 == true) goto L101;
            r9 = false;
        L101:
            PeerStoriesView.m19522$$Nest$fgetstoryLines(this.this$0).draw(r21, getMeasuredWidth(), PeerStoriesView.m19481$$Nest$fgetlinesPosition(this.this$0), r132, PeerStoriesView.m19480$$Nest$fgetlinesCount(this.this$0), r04.set(r9), PeerStoriesView.m19454$$Nest$fgetalpha(this.this$0) * (1.0f - PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0)), r17, r18, r19);
            r21.restore();
            return;
        L95:
            r18 = false;
            goto L96
        L81:
            if (this.this$0.playerSharedScope.isBuffering() == false) goto L83;
            r17 = true;
            goto L85
        L73:
            if (r15.type == 3) goto L78;
            if (PeerStoriesView.m19523$$Nest$fgetstoryPositionView(this.this$0) != null) goto L77;
            PeerStoriesView.m19557$$Nest$fputstoryPositionView(this.this$0, new StoryPositionView());
        L77:
            PeerStoriesView.m19523$$Nest$fgetstoryPositionView(this.this$0).draw(r21, (1.0f - PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0)) * (r0 * PeerStoriesView.m19454$$Nest$fgetalpha(this.this$0)), PeerStoriesView.m19482$$Nest$fgetlistPosition(this.this$0), this.val$storyViewer.storiesList.getCount(), this, this.this$0.headerView);
            goto L78
        L62:
            if (r132 != 1.0f) goto L70;
            if (StoryItemHolder.m19635$$Nest$fgetisVideo(this.this$0.currentStory) == false) goto L68;
            if (PeerStoriesView.m19469$$Nest$fgetisCaptionPartVisible(this.this$0) == true) goto L70;
        L68:
            if (PeerStoriesView.m19472$$Nest$fgetisLongPressed(this.this$0) == true) goto L70;
            PeerStoriesView.m19558$$Nest$fputswitchEventSent(this.this$0, true);
            post(new PeerStoriesView$4$$ExternalSyntheticLambda0(this));
        L58:
            r19 = r132;
            goto L59
        L20:
            if (PeerStoriesView.m19520$$Nest$fgetstoryAreasView(r22) == null) goto L23;
            PeerStoriesView.m19520$$Nest$fgetstoryAreasView(this.this$0).shine();
            goto L23
        L22:
            r13 = 0.0f;
            goto L23
        L26:
            if (PeerStoriesView.m19493$$Nest$fgetpaused(r2) == true) goto L50;
            PeerStoriesView r16 = this.this$0;
            if (r16.isActive == false) goto L50;
            if (PeerStoriesView.m19474$$Nest$fgetisUploading(r16) == true) goto L50;
            if (PeerStoriesView.m19470$$Nest$fgetisEditing(this.this$0) == true) goto L50;
            if (PeerStoriesView.m19471$$Nest$fgetisFailed(this.this$0) == true) goto L50;
            if (PeerStoriesView.m19467$$Nest$fgetimageReceiver(this.this$0).hasNotThumb() == false) goto L50;
            long r3 = System.currentTimeMillis();
            if (PeerStoriesView.m19475$$Nest$fgetlastDrawTime(this.this$0) != 0) goto L40;
        L49:
            PeerStoriesView.m19540$$Nest$fputlastDrawTime(this.this$0, r3);
            r13 = Utilities.clamp(PeerStoriesView.m19462$$Nest$fgetcurrentImageTime(this.this$0) / 10000.0f, 1.0f, 0.0f);
            invalidate();
            goto L24
        L40:
            if (PeerStoriesView.m19469$$Nest$fgetisCaptionPartVisible(this.this$0) == true) goto L49;
            if (PeerStoriesView.m19462$$Nest$fgetcurrentImageTime(this.this$0) <= 0) goto L44;
        L48:
            PeerStoriesView r110 = this.this$0;
            PeerStoriesView.m19533$$Nest$fputcurrentImageTime(r110, PeerStoriesView.m19462$$Nest$fgetcurrentImageTime(r110) + (r3 - PeerStoriesView.m19475$$Nest$fgetlastDrawTime(this.this$0)));
            goto L49
        L44:
            if ((r3 - PeerStoriesView.m19475$$Nest$fgetlastDrawTime(this.this$0)) <= 0) goto L48;
            if (PeerStoriesView.m19520$$Nest$fgetstoryAreasView(this.this$0) == null) goto L48;
            PeerStoriesView.m19520$$Nest$fgetstoryAreasView(this.this$0).shine();
        L50:
            r13 = Utilities.clamp(PeerStoriesView.m19462$$Nest$fgetcurrentImageTime(this.this$0) / 10000.0f, 1.0f, 0.0f);
            goto L24
        L5:
            if (StoryItemHolder.m19635$$Nest$fgetisVideo(this.this$0.currentStory) == false) goto L9;
            if (this.this$0.playerSharedScope.firstFrameRendered == true) goto L12;
        L9:
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == false) goto L13;
            if (this.this$0.playerSharedScope.firstFrameRendered == false) goto L13;
            goto L12
        }

        private /* synthetic */ void lambda$drawLines$0() {
            PeerStoriesView r0 = this.this$0;
            if (r0.delegate != null) goto L5;
            return;
        L5:
            if (PeerStoriesView.m19474$$Nest$fgetisUploading(r0) == false) goto L7;
        L13:
            boolean r02 = this.this$0.currentStory.isVideo();
            PeerStoriesView r2 = this.this$0;
            if (r02 == false) goto L17;
            r2.playerSharedScope.player.loopBack();
            return;
        L17:
            PeerStoriesView.m19533$$Nest$fputcurrentImageTime(r2, 0);
            return;
        L7:
            if (PeerStoriesView.m19470$$Nest$fgetisEditing(this.this$0) == true) goto L13;
            if (PeerStoriesView.m19471$$Nest$fgetisFailed(this.this$0) == true) goto L13;
            this.this$0.delegate.shouldSwitchToNext();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r19) {
            Canvas r1 = r19;
            PeerStoriesView r2 = this.this$0;
            boolean r9 = true;
            if (r2.isActive == true) goto L5;
            r2.headerView.backupImageView.getImageReceiver().setVisible(true, true);
        L5:
            PeerStoriesView r22 = this.this$0;
            float r11 = 1.0f;
            if (r22.unsupported == false) goto L8;
            r1.drawColor(ColorUtils.blendARGB(-16777216, -1, 0.2f));
        L109:
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getAlpha() <= 0.0f) goto L116;
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getAlpha() != 1.0f) goto L113;
            r1.save();
        L114:
            PeerStoriesView.m19520$$Nest$fgetstoryAreasView(this.this$0).draw(r1);
            r1.restore();
            goto L116
        L113:
            r1.saveLayerAlpha(0.0f, 0.0f, PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getMeasuredWidth(), PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getMeasuredHeight(), (int) (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getAlpha() * 255.0f), 31);
        L116:
            if (PeerStoriesView.m19476$$Nest$fgetlastNoThumb(this.this$0) == false) goto L118;
        L120:
            float r23 = PeerStoriesView.m19573$$Nest$mgetHideInterfaceAlpha(this.this$0);
            SharedResources.m19633$$Nest$fgettopOverlayGradient(this.val$sharedResources).setAlpha(255);
            SharedResources.m19633$$Nest$fgettopOverlayGradient(this.val$sharedResources).draw(r1);
            PeerStoriesView r3 = this.this$0;
            if (r3.isSelf == true) goto L127;
            if (PeerStoriesView.m19448$$Nest$fgetBIG_SCREEN(r3) == false) goto L127;
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getVisibility() == 0) goto L127;
        L191:
            float r14 = 0.0f;
        L193:
            if (PeerStoriesView.m19526$$Nest$fgetviewsThumbAlpha(this.this$0) != 0.0f) goto L195;
        L197:
            AnimatedFloat r24 = this.progressToAudio;
            if (PeerStoriesView.m19473$$Nest$fgetisRecording(this.this$0) == true) goto L201;
            r11 = 0.0f;
        L201:
            r24.set(r11);
            PeerStoriesView r25 = this.this$0;
            if (r25.isActive == false) goto L219;
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(r25).getVisibility() != 0) goto L210;
            PeerStoriesView r26 = this.this$0;
            if (r26.inBlackoutMode == false) goto L208;
        L209:
            boolean r27 = true;
        L211:
            PeerStoriesView r32 = this.this$0;
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(r32).getVisibility() == 0) goto L214;
        L216:
            r9 = false;
        L217:
            PeerStoriesView.m19538$$Nest$fputisCaptionPartVisible(r32, r9);
            this.this$0.delegate.setIsCaption(r27);
            PeerStoriesView r28 = this.this$0;
            r28.delegate.setIsCaptionPartVisible(PeerStoriesView.m19469$$Nest$fgetisCaptionPartVisible(r28));
            goto L219
        L214:
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getProgressToBlackout() <= 0.0f) goto L216;
        L208:
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(r26).isTouched() == true) goto L209;
        L210:
            r27 = false;
        L219:
            if (r14 > 0.0f) goto L222;
            super.dispatchDraw(r19);
            drawLines(r19);
        L222:
            if (PeerStoriesView.m19464$$Nest$fgetemojiAnimationsOverlay(this.this$0) == null) goto L225;
            PeerStoriesView.m19464$$Nest$fgetemojiAnimationsOverlay(this.this$0).draw(r1);
            return;
        L225:
            return;
        L195:
            if (PeerStoriesView.m19527$$Nest$fgetviewsThumbImageReceiver(this.this$0) == null) goto L197;
            Canvas r29 = r1;
            PeerStoriesView.m19527$$Nest$fgetviewsThumbImageReceiver(this.this$0).draw(r29, PeerStoriesView.m19526$$Nest$fgetviewsThumbAlpha(this.this$0), PeerStoriesView.m19528$$Nest$fgetviewsThumbScale(this.this$0), 0, 0, getMeasuredWidth(), getMeasuredHeight() + 1);
            r1 = r29;
        L127:
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getVisibility() != 0) goto L177;
            int r33 = AndroidUtilities.dp(72.0f);
            int r4 = ((int) (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getTextTop() - AndroidUtilities.dp(24.0f))) + PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getTop();
            int r34 = r33 + r4;
            float r6 = getMeasuredHeight() * 0.65f;
            boolean r7 = PeerStoriesView.m19575$$Nest$mhideCaptionWithInterface(this.this$0);
            if (((r6 - r4) / AndroidUtilities.dp(60.0f)) > 0.0f) goto L131;
        L137:
            PeerStoriesView r5 = this.this$0;
            if (r5.checkBlackoutMode == false) goto L143;
            r5.checkBlackoutMode = false;
            if (((r6 - (((int) (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(r5).getMaxTop() - AndroidUtilities.dp(24.0f))) + PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getTop())) / AndroidUtilities.dp(60.0f)) <= 0.0f) goto L145;
            this.this$0.inBlackoutMode = true;
        L145:
            if (r7 == true) goto L148;
            r23 = 1.0f;
        L148:
            AnimatedFloat r52 = this.progressToFullBlackoutA;
            if (this.this$0.inBlackoutMode == false) goto L151;
            float r62 = 1.0f;
        L152:
            r14 = r52.set(r62);
            if (r14 <= 0.0f) goto L156;
            this.splitDrawing = true;
            this.drawOverlayed = false;
            super.dispatchDraw(r19);
            this.splitDrawing = false;
            drawLines(r19);
            SharedResources.m19629$$Nest$fgetgradientBackgroundPaint(this.val$sharedResources).setColor(ColorUtils.setAlphaComponent(-16777216, (int) ((153.0f * r14) * r23)));
            r1.drawPaint(SharedResources.m19629$$Nest$fgetgradientBackgroundPaint(this.val$sharedResources));
        L156:
            if (r14 < 1.0f) goto L158;
        L160:
            if (r14 > 0.0f) goto L162;
        L168:
            r1 = r19;
        L169:
            StoryCaptionView r210 = PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0);
            if (r14 <= 0.0f) goto L172;
            boolean r35 = true;
        L173:
            r210.disableDraw(r35);
            if (r14 <= 0.0f) goto L193;
            this.splitDrawing = true;
            this.drawOverlayed = true;
            super.dispatchDraw(r19);
            this.splitDrawing = false;
            goto L193
        L172:
            r35 = false;
            goto L173
        L162:
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getAlpha() <= 0.0f) goto L168;
            PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).disableDraw(false);
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getAlpha() == 1.0f) goto L166;
            r1 = r19;
            r1.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), (int) (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getAlpha() * 255.0f), 31);
        L167:
            r1.translate(PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getX(), PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getY() - PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getScrollY());
            PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).draw(r1);
            r1.restore();
            goto L169
        L166:
            r1 = r19;
            r1.save();
            goto L167
        L158:
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == true) goto L160;
            r1.save();
            float r63 = 1.0f - r14;
            SharedResources.m19629$$Nest$fgetgradientBackgroundPaint(this.val$sharedResources).setColor(ColorUtils.setAlphaComponent(-16777216, (int) ((129.03f * r63) * r23)));
            SharedResources.m19626$$Nest$fgetbottomOverlayGradient(this.val$sharedResources).setAlpha((int) ((r63 * 255.0f) * r23));
            SharedResources.m19626$$Nest$fgetbottomOverlayGradient(this.val$sharedResources).setBounds(0, r4, getMeasuredWidth(), r34);
            SharedResources.m19626$$Nest$fgetbottomOverlayGradient(this.val$sharedResources).draw(r1);
            r1.drawRect(0.0f, r34, getMeasuredWidth(), getMeasuredHeight(), SharedResources.m19629$$Nest$fgetgradientBackgroundPaint(this.val$sharedResources));
            r19.restore();
            goto L160
        L151:
            r62 = 0.0f;
            goto L152
        L143:
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(r5).getProgressToBlackout() != 0.0f) goto L145;
            this.this$0.inBlackoutMode = false;
            goto L145
        L131:
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).isTouched() == false) goto L137;
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).hasScroll() == false) goto L137;
            if (((r6 - (((int) (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getMaxTop() - AndroidUtilities.dp(24.0f))) + PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getTop())) / AndroidUtilities.dp(60.0f)) <= 0.0f) goto L145;
            this.this$0.inBlackoutMode = true;
            goto L145
        L177:
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == true) goto L191;
            if (PeerStoriesView.m19448$$Nest$fgetBIG_SCREEN(this.this$0) == false) goto L182;
            float r36 = 56.0f;
        L181:
            int r37 = AndroidUtilities.dp(r36);
            PeerStoriesView r42 = this.this$0;
            if (r42.isSelf == true) goto L188;
            if (PeerStoriesView.m19448$$Nest$fgetBIG_SCREEN(r42) == false) goto L188;
        L190:
            SharedResources.m19626$$Nest$fgetbottomOverlayGradient(this.val$sharedResources).setBounds(0, this.this$0.storyContainer.getMeasuredHeight() - r37, getMeasuredWidth(), this.this$0.storyContainer.getMeasuredHeight());
            SharedResources.m19626$$Nest$fgetbottomOverlayGradient(this.val$sharedResources).setAlpha((int) (r23 * 255.0f));
            SharedResources.m19626$$Nest$fgetbottomOverlayGradient(this.val$sharedResources).draw(r1);
        L188:
            if (PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).getVisibility() != 0) goto L190;
            r37 = (int) (r37 * 2.5f);
            goto L190
        L182:
            r36 = 110.0f;
            goto L181
        L118:
            if (PeerStoriesView.m19467$$Nest$fgetimageReceiver(this.this$0).hasNotThumb() == false) goto L120;
            PeerStoriesView.m19541$$Nest$fputlastNoThumb(this.this$0, true);
            this.this$0.invalidate();
            goto L120
        L8:
            if (r22.playerSharedScope.renderView == null) goto L10;
        L15:
            invalidate();
        L16:
            r1.save();
            this.this$0.pinchToZoomHelper.applyTransform(r1);
            PeerStoriesView r211 = this.this$0;
            VideoPlayerSharedScope r38 = r211.playerSharedScope;
            View r64 = r38.renderView;
            if (r64 != null) goto L19;
        L54:
            if (r64 == null) goto L56;
            invalidate();
        L56:
            PeerStoriesView r212 = this.this$0;
            if (r212.currentStory.skipped == false) goto L60;
            r1.drawColor(ColorUtils.blendARGB(-16777216, -1, 0.2f));
        L63:
            r1.restore();
            if (PeerStoriesView.m19466$$Nest$fgetimageChanged(this.this$0) == false) goto L66;
            this.loadingDrawableAlpha2.set(0.0f, true);
            this.loadingDrawableAlpha.set(0.0f, true);
        L66:
            boolean r213 = StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory);
            PeerStoriesView r39 = this.this$0;
            if (r213 == false) goto L76;
            VideoPlayerSharedScope r214 = r39.playerSharedScope;
            LivePlayer r310 = r214.livePlayer;
            if (r310 != null) goto L71;
        L75:
            boolean r215 = false;
        L92:
            AnimatedFloat r311 = this.loadingDrawableAlpha2;
            PeerStoriesView r43 = this.this$0;
            if (r43.isActive == false) goto L98;
            if (r215 == true) goto L98;
            if (r43.currentStory.uploadingStory != null) goto L98;
            float r216 = 1.0f;
        L99:
            r311.set(r216);
            AnimatedFloat r217 = this.loadingDrawableAlpha;
            if (this.loadingDrawableAlpha2.get() != 1.0f) goto L102;
            float r312 = 1.0f;
        L103:
            r217.set(r312);
            if (this.loadingDrawableAlpha.get() <= 0.0f) goto L106;
            RectF r218 = AndroidUtilities.rectTmp;
            r218.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            this.loadingDrawable.setAlpha((int) (this.loadingDrawableAlpha.get() * 255.0f));
            this.loadingDrawable.setParentWidth(getMeasuredWidth() * 2);
            CellFlickerDrawable r313 = this.loadingDrawable;
            r313.animationSpeedScale = 1.3f;
            r313.draw(r1, r218, AndroidUtilities.dp(10.0f), this);
        L106:
            PeerStoriesView.m19537$$Nest$fputimageChanged(this.this$0, false);
            goto L109
        L102:
            r312 = 0.0f;
        L98:
            r216 = 0.0f;
            goto L99
        L71:
            if (r214.firstFrameRendered == false) goto L73;
        L74:
            r215 = true;
            goto L92
        L73:
            if (r310.isEmptyStream() == false) goto L75;
        L76:
            boolean r219 = StoryItemHolder.m19635$$Nest$fgetisVideo(r39.currentStory);
            PeerStoriesView r314 = this.this$0;
            if (r219 == false) goto L91;
            VideoPlayerSharedScope r220 = r314.playerSharedScope;
            if (r220.renderView == null) goto L75;
            StoryViewer.VideoPlayerHolder r315 = r220.player;
            if (r315 == null) goto L75;
            if (r220.firstFrameRendered == false) goto L75;
            if (r315.progress != 0.0f) goto L74;
            if (r220.isBuffering() == false) goto L74;
            if (this.this$0.playerSharedScope.player.paused == false) goto L75;
        L91:
            r215 = PeerStoriesView.m19467$$Nest$fgetimageReceiver(r314).hasNotThumb();
            goto L92
        L60:
            if (PeerStoriesView.m19467$$Nest$fgetimageReceiver(r212).hasBitmapImage() == true) goto L62;
            this.val$sharedResources.imageBackgroundDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight() + 1);
            this.val$sharedResources.imageBackgroundDrawable.draw(r1);
        L62:
            PeerStoriesView.m19467$$Nest$fgetimageReceiver(this.this$0).setImageCoords(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight() + 1);
            PeerStoriesView.m19467$$Nest$fgetimageReceiver(this.this$0).draw(r1);
            goto L63
        L19:
            if (r38.firstFrameRendered == true) goto L23;
            if (r38.livePlayer == null) goto L54;
        L23:
            if (PeerStoriesView.m19467$$Nest$fgetimageReceiver(r211).hasBitmapImage() == true) goto L25;
            this.val$sharedResources.imageBackgroundDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight() + 1);
            this.val$sharedResources.imageBackgroundDrawable.draw(r1);
        L25:
            PeerStoriesView.m19467$$Nest$fgetimageReceiver(this.this$0).setImageCoords(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight() + 1);
            PeerStoriesView.m19467$$Nest$fgetimageReceiver(this.this$0).draw(r1);
            PeerStoriesView r221 = this.this$0;
            if (r221.isActive == false) goto L63;
            if (this.val$storyViewer.USE_SURFACE_VIEW == false) goto L39;
            StoryViewer.VideoPlayerHolder r316 = r221.playerSharedScope.player;
            if (r316 == null) goto L39;
            if (r316.paused == false) goto L39;
            if (r316.playerStubBitmap == null) goto L39;
            if (r316.stubAvailable == false) goto L39;
            r1.save();
            r1.scale(getMeasuredWidth() / this.this$0.playerSharedScope.player.playerStubBitmap.getWidth(), getMeasuredHeight() / this.this$0.playerSharedScope.player.playerStubBitmap.getHeight());
            StoryViewer.VideoPlayerHolder r222 = this.this$0.playerSharedScope.player;
            r1.drawBitmap(r222.playerStubBitmap, 0.0f, 0.0f, r222.playerStubPaint);
            r1.restore();
        L39:
            if (Build.VERSION.SDK_INT >= 29) goto L41;
        L45:
            boolean r223 = false;
        L47:
            if (this.val$storyViewer.USE_SURFACE_VIEW == true) goto L49;
        L53:
            this.this$0.playerSharedScope.renderView.draw(r1);
            goto L63
        L49:
            if (PeerStoriesView.m19451$$Nest$fgetallowDrawSurface(this.this$0) == false) goto L63;
            if (this.val$storyViewer.isShown() == false) goto L63;
            if (r223 == true) goto L63;
        L41:
            if (PeerStoriesView.m19456$$Nest$fgetblurredBackgroundSourceRenderNodeWithSaturation(r221) == null) goto L45;
            if (PeerStoriesView.m19456$$Nest$fgetblurredBackgroundSourceRenderNodeWithSaturation(this.this$0).isRecordingCanvas(r1) == false) goto L45;
            r223 = true;
            goto L47
        L10:
            if (PeerStoriesView.m19520$$Nest$fgetstoryAreasView(r22) == null) goto L16;
            if (PeerStoriesView.m19520$$Nest$fgetstoryAreasView(this.this$0).hasSelectedForScale() == true) goto L15;
            if (PeerStoriesView.m19520$$Nest$fgetstoryAreasView(this.this$0).parentHighlightScaleAlpha.isInProgress() == false) goto L16;
            goto L15
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            PeerStoriesView r0 = this.this$0;
            if (r0.isActive == false) goto L18;
            if (r0.unsupported == true) goto L18;
            VideoPlayerSharedScope r02 = r0.playerSharedScope;
            if (r02.renderView == null) goto L18;
            if (r02 == null) goto L18;
            LivePlayer r03 = r02.livePlayer;
            if (r03 == null) goto L18;
            if (r03.isEmptyStream() == false) goto L18;
            if (this.this$0.playerSharedScope.renderView.dispatchTouchEvent(r3) == false) goto L18;
            return true;
        L18:
            return super.dispatchTouchEvent(r3);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r3, View r4, long r5) {
            if (r4 != PeerStoriesView.m19520$$Nest$fgetstoryAreasView(this.this$0)) goto L6;
            return true;
        L6:
            if (this.splitDrawing == false) goto L19;
            if (Bulletin.getVisibleBulletin() == null) goto L17;
            if (r4 != Bulletin.getVisibleBulletin().getLayout()) goto L17;
            if (this.drawOverlayed == true) goto L14;
            return true;
        L14:
            return super.drawChild(r3, r4, r5);
        L17:
            return super.drawChild(r3, r4, r5);
        L19:
            return super.drawChild(r3, r4, r5);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            PeerStoriesView.m19464$$Nest$fgetemojiAnimationsOverlay(this.this$0).onAttachedToWindow();
            Bulletin.addDelegate(this, new AnonymousClass1(this));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            PeerStoriesView.m19464$$Nest$fgetemojiAnimationsOverlay(this.this$0).onDetachedFromWindow();
            Bulletin.removeDelegate(this);
            Delegate r1 = this.this$0.delegate;
            if (r1 == null) goto L6;
            r1.setBulletinIsVisible(false);
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) PeerStoriesView.m19487$$Nest$fgetmuteIconContainer(this.this$0).getLayoutParams();
            if (PeerStoriesView.m19568$$Nest$mdrawLinesAsCounter(this.this$0) == false) goto L5;
            r0.rightMargin = AndroidUtilities.dp(2.0f);
            r0.topMargin = AndroidUtilities.dp(55.0f);
        L6:
            super.onMeasure(r3, r4);
            return;
        L5:
            r0.rightMargin = AndroidUtilities.dp(42.0f);
            r0.topMargin = AndroidUtilities.dp(15.0f);
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$40, reason: invalid class name */
    public class AnonymousClass40 implements ReactionsContainerLayout.ReactionsContainerDelegate {
        final /* synthetic */ PeerStoriesView this$0;

        /* renamed from: org.telegram.ui.Stories.PeerStoriesView$40$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass40 this$1;
            final /* synthetic */ View val$oldLikeButton;

            public AnonymousClass1(AnonymousClass40 r1, View r2) {
                this.this$1 = r1;
                this.val$oldLikeButton = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                AndroidUtilities.removeFromParent(this.val$oldLikeButton);
            }
        }

        /* renamed from: org.telegram.ui.Stories.PeerStoriesView$40$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass40 this$1;
            final /* synthetic */ boolean[] val$effectStarted;
            final /* synthetic */ StoriesLikeButton val$storiesLikeButtonFinal;

            public AnonymousClass2(AnonymousClass40 r1, boolean[] r2, StoriesLikeButton r3) {
                this.this$1 = r1;
                this.val$effectStarted = r2;
                this.val$storiesLikeButtonFinal = r3;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                PeerStoriesView.m19545$$Nest$fputmovingReaction(this.this$1.this$0, false);
                PeerStoriesView.m19549$$Nest$fputmovingReactionProgress(this.this$1.this$0, 1.0f);
                this.this$1.this$0.invalidate();
                boolean[] r42 = this.val$effectStarted;
                if (r42[0] == true) goto L6;
                r42[0] = true;
                PeerStoriesView.m19535$$Nest$fputdrawReactionEffect(this.this$1.this$0, true);
                this.this$1.this$0.performHapticFeedback(3);     // Catch: Exception -> L10
            L6:
                this.val$storiesLikeButtonFinal.setAllowDrawReaction(true);
                this.val$storiesLikeButtonFinal.animateVisibleReaction();
                if (PeerStoriesView.m19499$$Nest$fgetreactionMoveDrawable(this.this$1.this$0) == null) goto L13;
                PeerStoriesView.m19499$$Nest$fgetreactionMoveDrawable(this.this$1.this$0).removeView(this.this$1.this$0);
                PeerStoriesView.m19552$$Nest$fputreactionMoveDrawable(this.this$1.this$0, null);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$bOjwct9yPRlFl9QAPId5ZESDfWs(AnonymousClass40 r0, ValueAnimator r1, boolean[] r2, ValueAnimator r3) {
            r0.lambda$onReactionClicked$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$cVuxTDBLubNLmUTr1LKFP3lVX2o(AnonymousClass40 r0, ReactionsLayoutInBubble.VisibleReaction r1, View r2) {
            r0.lambda$onReactionClicked$1(r1, r2);
        }

        public AnonymousClass40(PeerStoriesView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onReactionClicked$0(ValueAnimator r1, boolean[] r2, ValueAnimator r3) {
            PeerStoriesView.m19549$$Nest$fputmovingReactionProgress(this.this$0, ((Float) r1.getAnimatedValue()).floatValue());
            this.this$0.invalidate();
            if (PeerStoriesView.m19486$$Nest$fgetmovingReactionProgress(this.this$0) > 0.8f) goto L5;
            return;
        L5:
            if (r2[0] == true) goto L13;
            r2[0] = true;
            PeerStoriesView.m19535$$Nest$fputdrawReactionEffect(this.this$0, true);
            this.this$0.performHapticFeedback(3);     // Catch: Exception -> L9
            return;
        L14:
            return;
        }

        private /* synthetic */ void lambda$onReactionClicked$1(ReactionsLayoutInBubble.VisibleReaction r27, View r28) {
            PeerStoriesView.m19545$$Nest$fputmovingReaction(this.this$0, true);
            boolean[] r2 = {false};
            StoriesLikeButton r5 = PeerStoriesView.m19519$$Nest$fgetstoriesLikeButton(this.this$0);
            r5.animate().alpha(0.0f).scaleX(0.8f).scaleY(0.8f).setListener(new AnonymousClass1(this, r5)).setDuration(150).start();
            int r52 = AndroidUtilities.dp(8.0f);
            PeerStoriesView.m19556$$Nest$fputstoriesLikeButton(this.this$0, new StoriesLikeButton(this.this$0.getContext(), this.this$0.sharedResources));
            PeerStoriesView.m19519$$Nest$fgetstoriesLikeButton(this.this$0).setPadding(r52, r52, r52, r52);
            PeerStoriesView.m19477$$Nest$fgetlikeButtonContainer(this.this$0).addView(PeerStoriesView.m19519$$Nest$fgetstoriesLikeButton(this.this$0), LayoutHelper.createFrame(40, 40, 3));
            if (PeerStoriesView.m19499$$Nest$fgetreactionMoveDrawable(this.this$0) == null) goto L6;
            PeerStoriesView.m19499$$Nest$fgetreactionMoveDrawable(this.this$0).removeView(this.this$0);
            PeerStoriesView.m19552$$Nest$fputreactionMoveDrawable(this.this$0, null);
        L6:
            if (PeerStoriesView.m19465$$Nest$fgetemojiReactionEffect(this.this$0) == null) goto L8;
            PeerStoriesView.m19465$$Nest$fgetemojiReactionEffect(this.this$0).removeView(this.this$0);
            PeerStoriesView.m19536$$Nest$fputemojiReactionEffect(this.this$0, null);
        L8:
            PeerStoriesView.m19534$$Nest$fputdrawAnimatedEmojiAsMovingReaction(this.this$0, false);
            if (r27.documentId == 0) goto L12;
            PeerStoriesView.m19534$$Nest$fputdrawAnimatedEmojiAsMovingReaction(this.this$0, true);
            PeerStoriesView.m19552$$Nest$fputreactionMoveDrawable(this.this$0, new AnimatedEmojiDrawable(2, PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0), r27.documentId));
            PeerStoriesView.m19499$$Nest$fgetreactionMoveDrawable(this.this$0).addView(this.this$0);
        L18:
            PeerStoriesView.m19519$$Nest$fgetstoriesLikeButton(this.this$0).setReaction(r27);
            PeerStoriesView r53 = this.this$0;
            if (r53.isChannel == false) goto L27;
            TL_stories.StoryItem r54 = r53.currentStory.storyItem;
            if (r54.sent_reaction != null) goto L27;
            if (r54.views != null) goto L25;
            r54.views = new TL_stories.TL_storyViews();
        L25:
            TL_stories.StoryItem r55 = this.this$0.currentStory.storyItem;
            TL_stories.StoryViews r9 = r55.views;
            r9.reactions_count++;
            ReactionsUtils.applyForStoryViews(null, r55.sent_reaction, r9);
            PeerStoriesView.m19592$$Nest$mupdateUserViews(this.this$0, true);
        L27:
            if (r27.documentId != 0) goto L29;
        L31:
            PeerStoriesView r56 = this.this$0;
            r56.storiesController.setStoryReaction(PeerStoriesView.m19463$$Nest$fgetdialogId(r56), this.this$0.currentStory.storyItem, r27);
            int[] r57 = new int[2];
            r28.getLocationInWindow(r57);
            int[] r92 = new int[2];
            this.this$0.getLocationInWindow(r92);
            PeerStoriesView.m19547$$Nest$fputmovingReactionFromX(this.this$0, r57[0] - r92[0]);
            PeerStoriesView.m19548$$Nest$fputmovingReactionFromY(this.this$0, r57[1] - r92[1]);
            PeerStoriesView.m19546$$Nest$fputmovingReactionFromSize(this.this$0, r28.getMeasuredHeight());
            ValueAnimator r3 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            PeerStoriesView.m19549$$Nest$fputmovingReactionProgress(this.this$0, 0.0f);
            this.this$0.invalidate();
            StoriesLikeButton r58 = PeerStoriesView.m19519$$Nest$fgetstoriesLikeButton(this.this$0);
            r58.setAllowDrawReaction(false);
            r58.prepareAnimateReaction(r27);
            r3.addUpdateListener(new PeerStoriesView$40$$ExternalSyntheticLambda1(this, r3, r2));
            r3.addListener(new AnonymousClass2(this, r2, r58));
            r3.setDuration(220);
            r3.start();
            PeerStoriesView.m19586$$Nest$mshowLikesReaction(this.this$0, false);
            return;
        L29:
            if (PeerStoriesView.m19519$$Nest$fgetstoriesLikeButton(this.this$0).emojiDrawable == null) goto L31;
            PeerStoriesView r59 = this.this$0;
            PeerStoriesView.m19536$$Nest$fputemojiReactionEffect(r59, AnimatedEmojiEffect.createFrom(PeerStoriesView.m19519$$Nest$fgetstoriesLikeButton(r59).emojiDrawable, false, true));
            PeerStoriesView.m19465$$Nest$fgetemojiReactionEffect(this.this$0).setView(this.this$0);
            goto L31
        L12:
            if (r27.emojicon == null) goto L18;
            TLRPC.TL_availableReaction r510 = MediaDataController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getReactionsMap().get(r27.emojicon);
            if (r510 == null) goto L18;
            PeerStoriesView.m19500$$Nest$fgetreactionMoveImageReceiver(this.this$0).setImage(null, null, ImageLocation.getForDocument(r510.select_animation), "60_60", null, null, null, 0, null, null, 0);
            PeerStoriesView.m19498$$Nest$fgetreactionEffectImageReceiver(this.this$0).setImage(ImageLocation.getForDocument(r510.around_animation), ReactionsEffectOverlay.getFilterForAroundAnimation(), null, null, null, 0);
            if (PeerStoriesView.m19498$$Nest$fgetreactionEffectImageReceiver(this.this$0).getLottieAnimation() == null) goto L18;
            PeerStoriesView.m19498$$Nest$fgetreactionEffectImageReceiver(this.this$0).getLottieAnimation().setCurrentFrame(0, false, true);
            goto L18
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public boolean needEnterText() {
            this.this$0.delegate.requestAdjust(false);
            return false;
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public void onReactionClicked(View r1, ReactionsLayoutInBubble.VisibleReaction r2, boolean r3, boolean r4) {
            PeerStoriesView$40$$ExternalSyntheticLambda0 r42 = new PeerStoriesView$40$$ExternalSyntheticLambda0(this, r2, r1);
            if (r3 == true) goto L6;
            PeerStoriesView.m19560$$Nest$mapplyMessageToChat(this.this$0, r42);
            return;
        L6:
            r42.run();
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$41, reason: invalid class name */
    public class AnonymousClass41 extends AnimatorListenerAdapter {
        final /* synthetic */ PeerStoriesView this$0;
        final /* synthetic */ boolean val$out;

        public AnonymousClass41(PeerStoriesView r1, boolean r2) {
            this.this$0 = r1;
            this.val$out = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r6) {
            PeerStoriesView r62 = this.this$0;
            float r1 = 0.0f;
            if (this.val$out == false) goto L5;
            float r0 = 1.0f;
        L6:
            PeerStoriesView.m19551$$Nest$fputoutT(r62, r0);
            this.this$0.headerView.setTranslationY((-AndroidUtilities.dp(8.0f)) * PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0));
            PeerStoriesView r63 = this.this$0;
            r63.headerView.setAlpha(1.0f - PeerStoriesView.m19492$$Nest$fgetoutT(r63));
            PeerStoriesView.m19491$$Nest$fgetoptionsIconView(this.this$0).setTranslationY((-AndroidUtilities.dp(8.0f)) * PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0));
            PeerStoriesView.m19491$$Nest$fgetoptionsIconView(this.this$0).setAlpha(1.0f - PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0));
            PeerStoriesView.m19494$$Nest$fgetpipIconView(this.this$0).setTranslationY((-AndroidUtilities.dp(8.0f)) * PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0));
            PeerStoriesView.m19494$$Nest$fgetpipIconView(this.this$0).setAlpha(1.0f - PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0));
            PeerStoriesView.m19487$$Nest$fgetmuteIconContainer(this.this$0).setTranslationY((-AndroidUtilities.dp(8.0f)) * PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0));
            PeerStoriesView.m19487$$Nest$fgetmuteIconContainer(this.this$0).setAlpha(PeerStoriesView.m19489$$Nest$fgetmuteIconViewAlpha(this.this$0) * (1.0f - PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0)));
            if (PeerStoriesView.m19511$$Nest$fgetselfView(this.this$0) == null) goto L10;
            PeerStoriesView.m19511$$Nest$fgetselfView(this.this$0).setTranslationY(AndroidUtilities.dp(8.0f) * PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0));
            PeerStoriesView.m19511$$Nest$fgetselfView(this.this$0).setAlpha(1.0f - PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0));
        L10:
            if (PeerStoriesView.m19497$$Nest$fgetprivacyButton(this.this$0) == null) goto L12;
            PeerStoriesView.m19497$$Nest$fgetprivacyButton(this.this$0).setTranslationY((-AndroidUtilities.dp(8.0f)) * PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0));
            PeerStoriesView.m19497$$Nest$fgetprivacyButton(this.this$0).setAlpha(1.0f - PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0));
        L12:
            PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).setAlpha(1.0f - PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0));
            Delegate r64 = this.this$0.delegate;
            if (r64 == null) goto L16;
            r1 = r64.getProgressToDismiss();
        L16:
            float r65 = PeerStoriesView.m19573$$Nest$mgetHideInterfaceAlpha(this.this$0);
            if (PeerStoriesView.m19477$$Nest$fgetlikeButtonContainer(this.this$0) == null) goto L20;
            PeerStoriesView.m19477$$Nest$fgetlikeButtonContainer(this.this$0).setAlpha(((1.0f - r1) * r65) * (1.0f - PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0)));
        L20:
            if (PeerStoriesView.m19513$$Nest$fgetshareButton(this.this$0) == null) goto L23;
            PeerStoriesView.m19513$$Nest$fgetshareButton(this.this$0).setAlpha(((1.0f - r1) * r65) * (1.0f - PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0)));
        L23:
            if (PeerStoriesView.m19504$$Nest$fgetrepostButtonContainer(this.this$0) == null) goto L25;
            PeerStoriesView.m19504$$Nest$fgetrepostButtonContainer(this.this$0).setAlpha((r65 * (1.0f - r1)) * (1.0f - PeerStoriesView.m19492$$Nest$fgetoutT(this.this$0)));
        L25:
            PeerStoriesView r66 = this.this$0;
            ChatActivityEnterView r02 = r66.chatActivityEnterView;
            if (r02 == null) goto L28;
            r02.setAlpha(1.0f - PeerStoriesView.m19492$$Nest$fgetoutT(r66));
            this.this$0.invalidate();
        L28:
            this.this$0.storyContainer.invalidate();
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$5, reason: invalid class name */
    public class AnonymousClass5 extends StoryCaptionView {
        final /* synthetic */ PeerStoriesView this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
        final /* synthetic */ StoryViewer val$storyViewer;

        /* renamed from: $r8$lambda$19qLg-RzKCZ015MUqoH4xCfh6bo, reason: not valid java name */
        public static /* synthetic */ void m19598$r8$lambda$19qLgRzKCZ015MUqoH4xCfh6bo(AnonymousClass5 r0, StoryCaptionView.Panel r1, StoryViewer r2, Theme.ResourcesProvider r3, TL_stories.StoryItem r4) {
            r0.lambda$onReplyClick$5(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$1E4t2u0dKMbr-MZ7UFmbteTV894, reason: not valid java name */
        public static /* synthetic */ void m19599$r8$lambda$1E4t2u0dKMbrMZ7UFmbteTV894(AnonymousClass5 r0, StoryViewer r1, Theme.ResourcesProvider r2, TLRPC.InputStickerSet r3) {
            r0.lambda$onEmojiClick$6(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$G8hRhuh_UY4CGT7ddCpkljJVwlM(AnonymousClass5 r0, URLSpan r1, View r2, String r3, Theme.ResourcesProvider r4, DialogInterface r5, int r6) {
            r0.lambda$onLinkLongPress$0(r1, r2, r3, r4, r5, r6);
        }

        /* renamed from: $r8$lambda$iHr2cE20mKLt_Y0wlNR-yHJxUWw, reason: not valid java name */
        public static /* synthetic */ void m19600$r8$lambda$iHr2cE20mKLt_Y0wlNRyHJxUWw(AnonymousClass5 r0, TLRPC.Document r1, Theme.ResourcesProvider r2) {
            r0.lambda$onReplyClick$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$jA1NwVM4jhaNIH8WCyicHYTLyZY(AnonymousClass5 r0, TLRPC.Document r1, Theme.ResourcesProvider r2) {
            r0.lambda$onReplyClick$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$uGEs5Z_7Y8hqnajrHgG8hF_kRLE(StoryViewer r0) {
            r0.updatePlayingMode();
        }

        public static /* synthetic */ void $r8$lambda$vQVmjw001mEV0hmimWoOIyeWV8Q(Runnable r0, DialogInterface r1) {
            r0.run();
        }

        public AnonymousClass5(PeerStoriesView r1, Context r2, Theme.ResourcesProvider r3, StoryViewer r4, Theme.ResourcesProvider r5) {
            this.this$0 = r1;
            this.val$storyViewer = r4;
            this.val$resourcesProvider = r5;
            super(r2, r3);
        }

        private /* synthetic */ void lambda$onEmojiClick$6(StoryViewer r3, Theme.ResourcesProvider r4, TLRPC.InputStickerSet r5) {
            ArrayList r0 = new ArrayList(1);
            r0.add(r5);
            EmojiPacksAlert r52 = new EmojiPacksAlert(r3.fragment, getContext(), r4, r0);
            Delegate r2 = this.this$0.delegate;
            if (r2 == null) goto L6;
            r2.showDialog(r52);
            return;
        }

        private /* synthetic */ void lambda$onLinkLongPress$0(URLSpan r1, View r2, String r3, Theme.ResourcesProvider r4, DialogInterface r5, int r6) {
            if (r6 != 0) goto L6;
            onLinkClick(r1, r2);
            return;
        L6:
            if (r6 != 1) goto L9;
            AndroidUtilities.addToClipboard(r3);
            BulletinFactory.of(this.this$0.storyContainer, r4).createCopyLinkBulletin().show();
            return;
        }

        private /* synthetic */ void lambda$onReplyClick$2(TLRPC.Document r21, Theme.ResourcesProvider r22) {
            SendMessagesHelper r1 = SendMessagesHelper.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0));
            TLRPC.TL_document r2 = (TLRPC.TL_document) r21;
            long r5 = UserConfig.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getClientUserId();
            StoryItemHolder r3 = this.this$0.currentStory;
            if (r3 == null) goto L6;
            TL_stories.StoryItem r32 = r3.storyItem;
        L7:
            r1.sendMessage(SendMessagesHelper.SendMessageParams.of(r2, null, null, r5, null, null, null, null, null, null, false, 0, 0, 0, r32, null, false));
            BulletinFactory.of(this.this$0.storyContainer, r22).createSimpleBulletin(R.raw.saved_messages, AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.StoryAudioAddToSavedMessagesToast), -1, 2, new BulletinFactory$$ExternalSyntheticLambda5())).show(true);
            return;
        L6:
            r32 = null;
            goto L7
        }

        private /* synthetic */ void lambda$onReplyClick$3(TLRPC.Document r6, Theme.ResourcesProvider r7) {
            TLRPC.TL_account_saveMusic r0 = new TLRPC.TL_account_saveMusic();
            TLRPC.TL_inputDocument r1 = new TLRPC.TL_inputDocument();
            r0.id = r1;
            r1.id = r6.id;
            r1.access_hash = r6.access_hash;
            r1.file_reference = r6.file_reference;
            if (MediaController.getInstance().currentSavedMusicList != null) goto L5;
        L7:
            ConnectionsManager.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r0, null);
            BulletinFactory.of(this.this$0.storyContainer, r7).createSimpleBulletin(R.raw.ic_save_to_music, LocaleController.getString(R.string.StoryAudioAddToProfileToast)).show(true);
            return;
        L5:
            if (MediaController.getInstance().currentSavedMusicList.dialogId != UserConfig.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getClientUserId()) goto L7;
            MediaController.getInstance().currentSavedMusicList.add(r6);
            goto L7
        }

        private /* synthetic */ void lambda$onReplyClick$5(StoryCaptionView.Panel r3, StoryViewer r4, Theme.ResourcesProvider r5, TL_stories.StoryItem r6) {
            if (r6 == null) goto L8;
            BaseFragment r52 = LaunchActivity.getLastFragment();
            if (r52 != null) goto L6;
            return;
        L6:
            r6.dialogId = r3.peerId.longValue();
            StoryViewer r32 = r52.createOverlayStoryViewer();
            r32.open(getContext(), r6, null);
            r32.setOnCloseListener(new PeerStoriesView$5$$ExternalSyntheticLambda4(r4));
            r4.updatePlayingMode();
            return;
        L8:
            BulletinFactory.of(this.this$0.storyContainer, r5).createSimpleBulletin(R.raw.story_bomb2, LocaleController.getString(R.string.StoryNotFound)).setTag(3).show(true);
        }

        private void processExternalUrl(int r18, String r19, CharacterStyle r20, boolean r21) {
            if (r21 == false) goto L5;
        L17:
            if (r18 == 0) goto L24;
            if (r18 == 2) goto L24;
            if (r18 != 1) goto L34;
            AlertsCreator.showOpenUrlAlert(this.val$storyViewer.fragment, r19, true, true, false, null, this.val$resourcesProvider);
            return;
        L34:
            return;
        L24:
            if ((r20 instanceof URLSpanReplacement) == false) goto L30;
            URLSpanReplacement r1 = (URLSpanReplacement) r20;
            if (r1.getTextStyleRun() == null) goto L30;
            if ((r1.getTextStyleRun().flags & 1024) == 0) goto L30;
            boolean r14 = true;
        L31:
            AlertsCreator.showOpenUrlAlert(this.val$storyViewer.fragment, r19, true, true, true, r14, null, this.val$resourcesProvider);
            return;
        L30:
            r14 = false;
            goto L31
        L5:
            if (AndroidUtilities.shouldShowUrlInAlert(r19) == true) goto L17;
            if (r18 != 0) goto L11;
            Browser.openUrl(getContext(), Uri.parse(r19), true, true, null);
            return;
        L11:
            if (r18 != 1) goto L14;
            Browser.openUrl(getContext(), Uri.parse(r19), false, false, null);
            return;
        L14:
            if (r18 != 2) goto L33;
            Browser.openUrl(getContext(), Uri.parse(r19), false, true, null);
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return super.dispatchTouchEvent(r1);
        }

        @Override // org.telegram.ui.Stories.StoryCaptionView
        public void onEmojiClick(AnimatedEmojiSpan r5) {
            if (r5 == null) goto L16;
            PeerStoriesView r0 = this.this$0;
            if (r0.delegate == null) goto L17;
            TLRPC.Document r1 = r5.document;
            if (r1 != null) goto L9;
            r1 = AnimatedEmojiDrawable.findDocument(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r0), r5.documentId);
        L9:
            if (r1 == null) goto L18;
            Bulletin r4 = BulletinFactory.of(this.this$0.storyContainer, this.val$resourcesProvider).createContainsEmojiBulletin(r1, 2, new PeerStoriesView$5$$ExternalSyntheticLambda3(this, this.val$storyViewer, this.val$resourcesProvider));
            if (r4 == null) goto L19;
            r4.tag = 1;
            r4.show(true);
            return;
        L19:
            return;
        L18:
            return;
        L17:
            return;
        }

        @Override // org.telegram.ui.Stories.StoryCaptionView
        public void onLinkClick(CharacterStyle r7, View r8) {
            if ((r7 instanceof URLSpanUserMention) == false) goto L9;
            TLRPC.User r1 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getUser(Utilities.parseLong(((URLSpanUserMention) r7).getURL()));
            if (r1 == null) goto L48;
            MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).openChatOrProfileWith(r1, null, this.val$storyViewer.fragment, 0, false);
            return;
        L48:
            return;
        L9:
            if ((r7 instanceof URLSpanNoUnderline) == false) goto L37;
            String r82 = ((URLSpanNoUnderline) r7).getURL();
            if (r82 != null) goto L13;
        L26:
            String r2 = Browser.extractUsername(r82);
            if (r2 == null) goto L34;
            String r22 = r2.toLowerCase();
            if (r82.startsWith("@") == false) goto L32;
            MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).openByUserName(r22, this.val$storyViewer.fragment, 0, null);
            return;
        L32:
            processExternalUrl(0, r82, r7, false);
            return;
        L34:
            processExternalUrl(0, r82, r7, false);
            return;
        L13:
            if (r82.startsWith("#") == true) goto L17;
            if (r82.startsWith("$") == false) goto L26;
        L17:
            if (r82.contains("@") == false) goto L22;
            StoryViewer r6 = this.val$storyViewer;
            if (r6 == null) goto L49;
            r6.presentFragment(new HashtagActivity(r82));
            return;
        L49:
            return;
        L22:
            Bundle r72 = new Bundle();
            r72.putInt("type", 3);
            r72.putString("hashtag", r82);
            StoryViewer r62 = this.val$storyViewer;
            if (r62 == null) goto L50;
            r62.presentFragment(new MediaActivity(r72, null));
            return;
        L50:
            return;
        L37:
            if ((r7 instanceof URLSpan) == false) goto L41;
            processExternalUrl(2, ((URLSpan) r7).getURL(), r7, r7 instanceof URLSpanReplacement);
            return;
        L41:
            if ((r7 instanceof URLSpanMono) == false) goto L45;
            ((URLSpanMono) r7).copyToClipboard();
            BulletinFactory.of(this.this$0.storyContainer, this.val$resourcesProvider).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
            return;
        L45:
            if ((r7 instanceof ClickableSpan) == false) goto L51;
            ((ClickableSpan) r7).onClick(r8);
            return;
        }

        @Override // org.telegram.ui.Stories.StoryCaptionView
        public void onLinkLongPress(URLSpan r9, View r10, Runnable r11) {
            String r4 = r9.getURL();
            String r1 = r9.getURL();
            Uri r0 = Uri.parse(r1);     // Catch: Exception -> L5
            r1 = Browser.replaceHostname(r0, Browser.IDN_toUnicode(r0.getHost()), null);     // Catch: Exception -> L5
        L7:
            String r02 = URLDecoder.decode(r1.replaceAll("\\+", "%2b"), "UTF-8");     // Catch: Exception -> L9
        L25:
            performHapticFeedback(VibratorUtils.getType(0), 1);     // Catch: Exception -> L22
        L13:
            BottomSheet.Builder r6 = new BottomSheet.Builder(getContext(), false, this.val$resourcesProvider);
            r6.setTitle(r02);
            r6.setTitleMultipleLines(true);
            StoryItemHolder r03 = this.this$0.currentStory;
            if (r03 != null) goto L16;
        L19:
            CharSequence[] r04 = {LocaleController.getString(R.string.Open), LocaleController.getString(R.string.Copy)};
        L20:
            r6.setItems(r04, new PeerStoriesView$5$$ExternalSyntheticLambda5(this, r9, r10, r4, this.val$resourcesProvider));
            r6.setOnPreDismissListener(new PeerStoriesView$5$$ExternalSyntheticLambda6(r11));
            BottomSheet r8 = r6.create();
            r8.fixNavigationBar(Theme.getColor(Theme.key_dialogBackground, this.val$resourcesProvider));
            this.this$0.delegate.showDialog(r8);
            return;
        L16:
            if (r03.allowScreenshots() == true) goto L19;
            r04 = new CharSequence[]{LocaleController.getString(R.string.Open)};
            goto L20
        L9:
            e = move-exception;
            FileLog.e(e);
            r02 = r1;
        L5:
            e = move-exception;
            FileLog.e(e);     // Catch: Exception -> L9
            goto L7
        }

        @Override // org.telegram.ui.Stories.StoryCaptionView
        public void onReplyClick(View r7, StoryCaptionView.Panel r8) {
            if (r8 != null) goto L4;
            return;
        L4:
            TLRPC.Document r72 = r8.music;
            if (r72 == null) goto L9;
            ItemOptions.makeOptions(this.val$storyViewer.containerView, this.val$resourcesProvider, PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0)).setGravity(3).translate(-AndroidUtilities.dp(8.0f), 0.0f).addIf(r72 instanceof TLRPC.TL_document, R.drawable.msg_saved, LocaleController.getString(R.string.StoryAudioAddToSavedMessages), new PeerStoriesView$5$$ExternalSyntheticLambda0(this, r72, this.val$resourcesProvider)).add(R.drawable.msg_tone_add, LocaleController.getString(R.string.StoryAudioAddToProfile), new PeerStoriesView$5$$ExternalSyntheticLambda1(this, r72, this.val$resourcesProvider)).show();
            return;
        L9:
            if (r8.isRepostMessage == false) goto L21;
            if (r8.peerId == null) goto L21;
            if (r8.messageId == null) goto L21;
            Bundle r73 = new Bundle();
            long r0 = r8.peerId.longValue();
            Long r1 = r8.peerId;
            if (r0 < 0) goto L17;
            r73.putLong("user_id", r1.longValue());
        L18:
            r73.putInt("message_id", r8.messageId.intValue());
            this.val$storyViewer.presentFragment(new ChatActivity(r73));
            return;
        L17:
            r73.putLong("chat_id", -r1.longValue());
        L21:
            if (r8.peerId != null) goto L23;
        L27:
            BulletinFactory.of(this.this$0.storyContainer, this.val$resourcesProvider).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.StoryHidAccount)).setTag(3).show(true);
            return;
        L23:
            if (r8.storyId == null) goto L27;
            MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getStoriesController().resolveStoryLink(r8.peerId.longValue(), r8.storyId.intValue(), new PeerStoriesView$5$$ExternalSyntheticLambda2(this, r8, this.val$storyViewer, this.val$resourcesProvider));
        }

        @Override // org.telegram.ui.Stories.StoryCaptionView, androidx.core.widget.NestedScrollView, android.view.View
        public boolean onTouchEvent(MotionEvent r1) {
            return super.onTouchEvent(r1);
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$6, reason: invalid class name */
    public class AnonymousClass6 extends FrameLayout {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass6(PeerStoriesView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            super.dispatchDraw(r4);
            PeerStoriesView r0 = this.this$0;
            if (r0.isChannel == true) goto L5;
            return;
        L5:
            if (PeerStoriesView.m19505$$Nest$fgetrepostCounter(r0) == null) goto L12;
            r4.save();
            float r1 = 0.0f;
            r4.translate((getMeasuredWidth() - PeerStoriesView.m19505$$Nest$fgetrepostCounter(this.this$0).getCurrentWidth()) - AndroidUtilities.dp(6.0f), 0.0f);
            AnimatedFloat r02 = PeerStoriesView.m19506$$Nest$fgetrepostCounterProgress(this.this$0);
            if (PeerStoriesView.m19507$$Nest$fgetrepostCounterVisible(this.this$0) == false) goto L9;
            r1 = 1.0f;
        L9:
            float r03 = r02.set(r1);
            r4.scale(r03, r03, PeerStoriesView.m19505$$Nest$fgetrepostCounter(this.this$0).getCurrentWidth() / 2.0f, AndroidUtilities.dp(20.0f));
            PeerStoriesView.m19505$$Nest$fgetrepostCounter(this.this$0).setAlpha(255);
            PeerStoriesView.m19505$$Nest$fgetrepostCounter(this.this$0).draw(r4);
            r4.restore();
            return;
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != PeerStoriesView.m19505$$Nest$fgetrepostCounter(this.this$0)) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$7, reason: invalid class name */
    public class AnonymousClass7 extends FrameLayout {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass7(PeerStoriesView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            super.dispatchDraw(r4);
            PeerStoriesView r0 = this.this$0;
            if (r0.isChannel == true) goto L5;
            return;
        L5:
            if (PeerStoriesView.m19501$$Nest$fgetreactionsCounter(r0) == null) goto L12;
            r4.save();
            float r1 = 0.0f;
            r4.translate((getMeasuredWidth() - PeerStoriesView.m19501$$Nest$fgetreactionsCounter(this.this$0).getCurrentWidth()) - AndroidUtilities.dp(6.0f), 0.0f);
            AnimatedFloat r02 = PeerStoriesView.m19502$$Nest$fgetreactionsCounterProgress(this.this$0);
            if (PeerStoriesView.m19503$$Nest$fgetreactionsCounterVisible(this.this$0) == false) goto L9;
            r1 = 1.0f;
        L9:
            float r03 = r02.set(r1);
            r4.scale(r03, r03, PeerStoriesView.m19501$$Nest$fgetreactionsCounter(this.this$0).getCurrentWidth() / 2.0f, AndroidUtilities.dp(20.0f));
            PeerStoriesView.m19501$$Nest$fgetreactionsCounter(this.this$0).setAlpha(255);
            PeerStoriesView.m19501$$Nest$fgetreactionsCounter(this.this$0).draw(r4);
            r4.restore();
            return;
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != PeerStoriesView.m19501$$Nest$fgetreactionsCounter(this.this$0)) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$8, reason: invalid class name */
    public class AnonymousClass8 extends CustomPopupMenu {
        private boolean edit;
        final /* synthetic */ PeerStoriesView this$0;
        final /* synthetic */ boolean val$canEditStory;
        final /* synthetic */ Context val$context;
        final /* synthetic */ boolean[] val$popupStillVisible;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
        final /* synthetic */ SharedResources val$sharedResources;
        final /* synthetic */ boolean val$speedControl;
        final /* synthetic */ StoryViewer val$storyViewer;
        final /* synthetic */ boolean val$userCanEditStory;

        /* renamed from: org.telegram.ui.Stories.PeerStoriesView$8$1, reason: invalid class name */
        public class AnonymousClass1 extends MessageStatisticActivity {
            final /* synthetic */ AnonymousClass8 this$1;

            public AnonymousClass1(AnonymousClass8 r1, MessageObject r2, long r3, boolean r5) {
                this.this$1 = r1;
                super(r2, r3, r5);
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public Theme.ResourcesProvider getResourceProvider() {
                return new DarkThemeResourceProvider();
            }

            @Override // org.telegram.ui.MessageStatisticActivity, org.telegram.ui.ActionBar.BaseFragment
            public boolean isLightStatusBar() {
                return false;
            }
        }

        /* renamed from: org.telegram.ui.Stories.PeerStoriesView$8$2, reason: invalid class name */
        public class AnonymousClass2 implements ChooseSpeedLayout.Callback {
            final /* synthetic */ AnonymousClass8 this$1;
            final /* synthetic */ ActionBarPopupWindow.ActionBarPopupWindowLayout val$popupLayout;

            public AnonymousClass2(AnonymousClass8 r1, ActionBarPopupWindow.ActionBarPopupWindowLayout r2) {
                this.this$1 = r1;
                this.val$popupLayout = r2;
            }

            @Override // org.telegram.ui.ChooseSpeedLayout.Callback
            public void onSpeedSelected(float r2, boolean r3, boolean r4) {
                StoryViewer r0 = this.this$1.val$storyViewer;
                if (r0 == null) goto L5;
                r0.setSpeed(r2);
            L5:
                PeerStoriesView.m19590$$Nest$mupdateSpeedItem(this.this$1.this$0, r3);
                if (r4 == true) goto L8;
                return;
            L8:
                if (this.val$popupLayout.getSwipeBack() == null) goto L12;
                this.val$popupLayout.getSwipeBack().closeForeground();
                return;
            }
        }

        /* renamed from: $r8$lambda$0oFrgp-js255_-WJCY35KZTgJFk, reason: not valid java name */
        public static /* synthetic */ void m19601$r8$lambda$0oFrgpjs255_WJCY35KZTgJFk(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$42(r1);
        }

        /* renamed from: $r8$lambda$1RJl846RgB8-uRF9_pGsJmSlots, reason: not valid java name */
        public static /* synthetic */ void m19602$r8$lambda$1RJl846RgB8uRF9_pGsJmSlots(AnonymousClass8 r0) {
            r0.lambda$onCreate$12();
        }

        public static /* synthetic */ void $r8$lambda$1ZD7Ov63f2xwPwhqfG_7_HMo2y8(StoryPrivacyBottomSheet r0) {
            r0.dismiss();
        }

        /* renamed from: $r8$lambda$1c3X3jH80hHvIYEomSJu8XyZ-OM, reason: not valid java name */
        public static /* synthetic */ void m19603$r8$lambda$1c3X3jH80hHvIYEomSJu8XyZOM(AnonymousClass8 r0) {
            r0.lambda$onDismissed$61();
        }

        public static /* synthetic */ void $r8$lambda$2F600qq6SCe66Y2IfCLhUWT9xBU(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$49(r1);
        }

        /* renamed from: $r8$lambda$2hEvh0RMwKFL1fFWPcy2-c6AsIs, reason: not valid java name */
        public static /* synthetic */ void m19604$r8$lambda$2hEvh0RMwKFL1fFWPcy2c6AsIs(Runnable r2, long r3) {
            AndroidUtilities.runOnUIThread(r2, Math.max(0, 500 - (System.currentTimeMillis() - r3)));
        }

        public static /* synthetic */ void $r8$lambda$3vPD5M6du5R37pCZM_SnvaAToIk(AnonymousClass8 r0, TL_stories.StoryItem r1, Theme.ResourcesProvider r2, StoriesController.StoryAlbum r3) {
            r0.lambda$addAlbumsLayout$4(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$44cxg47EmO7wGipUuO4ZEp56bsM(Runnable r0) {
            AndroidUtilities.cancelRunOnUIThread(r0);
            AndroidUtilities.runOnUIThread(r0);
        }

        public static /* synthetic */ void $r8$lambda$4QYZfkrikloNPLJQj7z40rxlC9o(AnonymousClass8 r0, TL_stories.StoryItem r1, boolean r2, Theme.ResourcesProvider r3, Boolean r4) {
            r0.lambda$onCreate$28(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$6W8gYdA48uK10i8YV1p_hH4il18(AnonymousClass8 r0, StoryPrivacyBottomSheet r1, StoryPrivacyBottomSheet.StoryPrivacy r2, boolean r3, boolean r4, boolean r5, boolean r6, TLRPC.InputPeer r7, int r8, Runnable r9, Runnable r10) {
            r0.lambda$onCreate$38(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
        }

        /* renamed from: $r8$lambda$7P-5gBZEytVPIFaD8sSyIZ5gfmY, reason: not valid java name */
        public static /* synthetic */ void m19605$r8$lambda$7P5gBZEytVPIFaD8sSyIZ5gfmY(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$34(r1);
        }

        public static /* synthetic */ void $r8$lambda$8NB5F54ITPkeSyO1ZS7tTwSmbLI(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$47(r1);
        }

        public static /* synthetic */ void $r8$lambda$8e7MtWemUOVX5eHasdy4fsogkzU(AnonymousClass8 r0, boolean r1, View r2) {
            r0.lambda$onCreate$33(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$93s1RGoVHBMKE1kd2Jx9tC2wRPw(Runnable r2, long r3) {
            AndroidUtilities.cancelRunOnUIThread(r2);
            AndroidUtilities.runOnUIThread(r2, Math.max(0, 32 - (System.currentTimeMillis() - r3)));
        }

        public static /* synthetic */ void $r8$lambda$CaFf18suJqOCEl4hGwZpkyzxGQY(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$45(r1);
        }

        /* renamed from: $r8$lambda$DD-BJZ0oKVWBPRWrpKb8KTG2UB0, reason: not valid java name */
        public static /* synthetic */ void m19606$r8$lambda$DDBJZ0oKVWBPRWrpKb8KTG2UB0(AnonymousClass8 r0, SharedResources r1, Long r2, Runnable r3, Boolean r4, Long r5) {
            r0.lambda$onCreate$25(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$EBTO2fCj0wmYiW7aOw_cT77gjw0(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$52(r1);
        }

        public static /* synthetic */ void $r8$lambda$EX9L_c5gDuu7LAumkWrBiHIT3Gk(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$10(r1);
        }

        public static /* synthetic */ void $r8$lambda$GnK3eE4xbinLw8D5ymTzZAS9tTs(AnonymousClass8 r0, StoryViewer r1, AlertDialog r2, int r3) {
            r0.lambda$onCreate$40(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$Hq4LVL2yv7H2csj7Kc3fmENuTsk(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$30(r1);
        }

        /* renamed from: $r8$lambda$KNBD-S3nX4u9u7cmzkafWXy6unU, reason: not valid java name */
        public static /* synthetic */ void m19607$r8$lambda$KNBDS3nX4u9u7cmzkafWXy6unU(AnonymousClass8 r0, TL_stories.StoryItem r1, StoryViewer r2, TLRPC.Chat r3, View r4) {
            r0.lambda$addViewStatistics$0(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$LLdYqe3OnhDahGphewkxWQ1nA7E(AnonymousClass8 r0, Theme.ResourcesProvider r1, StoryViewer r2, View r3) {
            r0.lambda$onCreate$39(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$LQELnlEsRlqTjzYnqV5juy43db0(AnonymousClass8 r0, String r1, Theme.ResourcesProvider r2, TLObject r3, String r4, View r5) {
            r0.lambda$onCreate$44(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$NV2WjvVVPH_8msYrDZ0Kte2fosg(Runnable r0) {
            AndroidUtilities.cancelRunOnUIThread(r0);
            AndroidUtilities.runOnUIThread(r0);
        }

        /* renamed from: $r8$lambda$Omm4uj9qQqwBBIf4-pXPWBfLXv4, reason: not valid java name */
        public static /* synthetic */ void m19608$r8$lambda$Omm4uj9qQqwBBIf4pXPWBfLXv4(AnonymousClass8 r0, Theme.ResourcesProvider r1, TL_stories.StoryItem r2) {
            r0.lambda$addAlbumsLayout$6(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$RdMStbOwJXiW6BcmoKusab0Upag(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$53(r1);
        }

        public static /* synthetic */ void $r8$lambda$T7TFN1lIBFGlHqXfI5RBdtCL_XI(AnonymousClass8 r0, TL_stories.StoryItem r1, Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$onCreate$20(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$U7EbgAgmMCeQNjtKogfzlVNd5U8(AnonymousClass8 r0, TL_stories.StoryItem r1, Theme.ResourcesProvider r2, String r3) {
            r0.lambda$addAlbumsLayout$5(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$VU5a7tw08M0tpjO9YNLN2d5ydoQ(Runnable r2, long r3) {
            AndroidUtilities.cancelRunOnUIThread(r2);
            AndroidUtilities.runOnUIThread(r2, Math.max(0, 32 - (System.currentTimeMillis() - r3)));
        }

        /* renamed from: $r8$lambda$XI_EGPJEJp0cUDX-Z6PcdPT1Jhk, reason: not valid java name */
        public static /* synthetic */ void m19609$r8$lambda$XI_EGPJEJp0cUDXZ6PcdPT1Jhk(AnonymousClass8 r0, SharedResources r1, Long r2, Runnable r3, Boolean r4, Long r5) {
            r0.lambda$onCreate$15(r1, r2, r3, r4, r5);
        }

        /* renamed from: $r8$lambda$XUOsEgjB5JBIDkyCtvv-PwUbVwc, reason: not valid java name */
        public static /* synthetic */ void m19610$r8$lambda$XUOsEgjB5JBIDkyCtvvPwUbVwc(AnonymousClass8 r0, TL_stories.StoryItem r1, TL_stories.StoryItem r2, Utilities.Callback r3) {
            r0.lambda$onCreate$21(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$XY1w8f2kppVtrGy59fpved89Z1s(AnonymousClass8 r0, StoryContainsEmojiButton r1, View r2) {
            r0.lambda$onCreate$60(r1, r2);
        }

        /* renamed from: $r8$lambda$ZOd_0mAZ8mLhn9Y_-3076o35eZk, reason: not valid java name */
        public static /* synthetic */ void m19611$r8$lambda$ZOd_0mAZ8mLhn9Y_3076o35eZk(AnonymousClass8 r0) {
            r0.lambda$onCreate$22();
        }

        /* renamed from: $r8$lambda$_UfH-cuc-uiDN-sSWCrfiX9PriY, reason: not valid java name */
        public static /* synthetic */ void m19612$r8$lambda$_UfHcucuiDNsSWCrfiX9PriY(AnonymousClass8 r0, TLObject r1, TL_stories.StoryItem r2, Utilities.Callback r3) {
            r0.lambda$onCreate$19(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$_yN-BXYv7gOPVoHtzsMoD1J64fI, reason: not valid java name */
        public static /* synthetic */ void m19613$r8$lambda$_yNBXYv7gOPVoHtzsMoD1J64fI(StoriesController.BotPreviewsList r7, TL_stories.StoryItem r8, Utilities.Callback r9) {
            int r0 = 0;
        L4:
            if (r0 >= r7.messageObjects.size()) goto L22;
            MessageObject r1 = r7.messageObjects.get(r0);
            if (r1 == null) goto L21;
            TL_stories.StoryItem r12 = r1.storyItem;
            if (r12 == null) goto L21;
            TLRPC.MessageMedia r13 = r12.media;
            if (r13 == null) goto L21;
            TLRPC.Document r2 = r8.media.document;
            if (r2 == null) goto L21;
            TLRPC.Document r14 = r13.document;
            if (r14 == null) goto L21;
            if (r14.id != r2.id) goto L21;
            r9.run(r14);
            return;
        L21:
            r0 = r0 + 1;
            goto L4
        L22:
            r9.run(null);
        }

        public static /* synthetic */ void $r8$lambda$ajWxDy9IHtL5QfTYFOeVqfPiDWg(AnonymousClass8 r0, Context r1, TL_stories.StoryItem r2, StoryViewer r3, SharedResources r4, View r5) {
            r0.lambda$onCreate$27(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$akppXdzFjSA1l0hMQ26mC93dnhU(AnonymousClass8 r0, View r1) {
            r0.lambda$addSpeedLayout$2(r1);
        }

        /* renamed from: $r8$lambda$d0LP-w0U0vUtbT-RXF_S2lVMGaU, reason: not valid java name */
        public static /* synthetic */ void m19614$r8$lambda$d0LPw0U0vUtbTRXF_S2lVMGaU(AnonymousClass8 r0, String r1, Theme.ResourcesProvider r2, TLObject r3, String r4, View r5) {
            r0.lambda$onCreate$43(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$dt0ACLa9UX1kNORJEhPkh8kA6CA(AnonymousClass8 r0, StoryViewer r1, Theme.ResourcesProvider r2, View r3) {
            r0.lambda$onCreate$59(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$e25WEZ1bWemBghdBicKEtsoum4g(AnonymousClass8 r0, View r1) {
            r0.lambda$addAlbumsLayout$9(r1);
        }

        public static /* synthetic */ void $r8$lambda$f2mS9OHREAaY0zfxPk0fkyfF6ck(ActionBarPopupWindow.ActionBarPopupWindowLayout r1, int r2) {
            if (r1.getSwipeBack() == null) goto L6;
            r1.getSwipeBack().openForeground(r2);
            return;
        }

        /* renamed from: $r8$lambda$hH-rlABH44zvPMhIKMd-GozMmAo, reason: not valid java name */
        public static /* synthetic */ void m19615$r8$lambda$hHrlABH44zvPMhIKMdGozMmAo(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$31(r1);
        }

        /* renamed from: $r8$lambda$iqv-39MkwqFBW-ymPm0bp7TPpbQ, reason: not valid java name */
        public static /* synthetic */ void m19616$r8$lambda$iqv39MkwqFBWymPm0bp7TPpbQ(AnonymousClass8 r0, HashSet r1, TL_stories.StoryItem r2, Theme.ResourcesProvider r3, StoriesController.StoryAlbum r4) {
            r0.lambda$addAlbumsLayout$7(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$jRN0EmOo6QG00zhZR7U9aUIB1x4(AnonymousClass8 r0, Theme.ResourcesProvider r1, Context r2, StoryViewer r3, SharedResources r4, View r5) {
            r0.lambda$onCreate$17(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$k0eiB15GJLW6ysbq_WyxqX2hwkw(AnonymousClass8 r0, Activity r1, StoryViewer r2, SharedResources r3) {
            r0.lambda$onCreate$16(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$kD-cVEBoJ8XPOqtjZgaOvrYUkbU, reason: not valid java name */
        public static /* synthetic */ void m19617$r8$lambda$kDcVEBoJ8XPOqtjZgaOvrYUkbU(AnonymousClass8 r0, StoryViewer r1, View r2) {
            r0.lambda$onCreate$35(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$kS6wJp3Urrglu0z2Ef8eGulSplc(AnonymousClass8 r0, StoryPrivacyBottomSheet.StoryPrivacy r1, TL_stories.StoryItem r2, View r3) {
            r0.lambda$onCreate$11(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$kkQekJEdPFrNqPha5yZIXlapoB4(ActionBarPopupWindow.ActionBarPopupWindowLayout r1, int r2) {
            if (r1.getSwipeBack() == null) goto L6;
            r1.getSwipeBack().openForeground(r2);
            return;
        }

        public static /* synthetic */ void $r8$lambda$nHOviH06EX5Zk9E1YtK7XVWSmiU(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$46(r1);
        }

        public static /* synthetic */ void $r8$lambda$o2MqVUVwTdXq7UNDMhZFB70exyk(AnonymousClass8 r0, Theme.ResourcesProvider r1, StoryViewer r2, View r3) {
            r0.lambda$onCreate$41(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$pDxAcVXZhWXLfqVNt3UdeX1SsnY(AnonymousClass8 r0, StoryViewer r1, View r2) {
            r0.lambda$onCreate$48(r1, r2);
        }

        /* renamed from: $r8$lambda$pa0oGFNWX-lIkZ2eSovCk1B8LH8, reason: not valid java name */
        public static /* synthetic */ void m19618$r8$lambda$pa0oGFNWXlIkZ2eSovCk1B8LH8(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$32(r1);
        }

        /* renamed from: $r8$lambda$qIIxq0p6H_-4FSV0yitshklDvJU, reason: not valid java name */
        public static /* synthetic */ void m19619$r8$lambda$qIIxq0p6H_4FSV0yitshklDvJU(AnonymousClass8 r0, TL_stories.StoryItem r1, boolean r2, Theme.ResourcesProvider r3, View r4) {
            r0.lambda$onCreate$29(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$rVsuPxLsudFzomSQr9kQ1d0unxs(ActionBarPopupWindow.ActionBarPopupWindowLayout r1) {
            if (r1.getSwipeBack() == null) goto L6;
            r1.getSwipeBack().closeForeground();
            return;
        }

        /* renamed from: $r8$lambda$rv4ddzDfrcC3oI-nSjhp75oHYjo, reason: not valid java name */
        public static /* synthetic */ void m19620$r8$lambda$rv4ddzDfrcC3oInSjhp75oHYjo(StoryViewer r0, Boolean r1) {
            if (r0 == null) goto L5;
            r0.setOverlayVisible(false);
            return;
        }

        public static /* synthetic */ void $r8$lambda$u7h6FzqOJBSbWbgkeN9UBuKztIM(AnonymousClass8 r0, StoryPrivacyBottomSheet r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onCreate$37(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$uUX6o7-19q781WfJ9S6tA1MtVv4, reason: not valid java name */
        public static /* synthetic */ void m19621$r8$lambda$uUX6o719q781WfJ9S6tA1MtVv4(AnonymousClass8 r0) {
            r0.lambda$onCreate$55();
        }

        /* renamed from: $r8$lambda$xXg-2049URIWcZODworf6rCL8S8, reason: not valid java name */
        public static /* synthetic */ void m19622$r8$lambda$xXg2049URIWcZODworf6rCL8S8(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$57(r1);
        }

        /* renamed from: $r8$lambda$xmngqCPBsWTUhsiM0e2_-zMyMa4, reason: not valid java name */
        public static /* synthetic */ void m19623$r8$lambda$xmngqCPBsWTUhsiM0e2_zMyMa4(AnonymousClass8 r0, Activity r1, TL_stories.StoryItem r2, StoryViewer r3, SharedResources r4) {
            r0.lambda$onCreate$26(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$xxIT4o15XSK87Z2qECucu2JCMUU(AnonymousClass8 r0, View r1) {
            r0.lambda$onCreate$54(r1);
        }

        public AnonymousClass8(PeerStoriesView r1, Context r2, Theme.ResourcesProvider r3, boolean r4, Theme.ResourcesProvider r5, StoryViewer r6, boolean r7, boolean r8, boolean r9, Context r10, SharedResources r11, boolean[] r12) {
            this.this$0 = r1;
            this.val$resourcesProvider = r5;
            this.val$storyViewer = r6;
            this.val$speedControl = r7;
            this.val$canEditStory = r8;
            this.val$userCanEditStory = r9;
            this.val$context = r10;
            this.val$sharedResources = r11;
            this.val$popupStillVisible = r12;
            super(r2, r3, r4);
        }

        private void addAlbumsLayout(ActionBarPopupWindow.ActionBarPopupWindowLayout r9, boolean r10) {
            TL_stories.StoryItem r0 = this.this$0.currentStory.storyItem;
            if (r0 != null) goto L6;
            return;
        L6:
            if (r0.albums == null) goto L9;
            HashSet r1 = new HashSet(r0.albums);
        L8:
            HashSet r4 = r1;
            ItemOptions r2 = ItemOptions.swipeback(r9, this.val$resourcesProvider);
            r2.add(R.drawable.ic_ab_back, LocaleController.getString(R.string.Back), new PeerStoriesView$8$$ExternalSyntheticLambda41(r9));
            r2.addGap();
            StoriesController.StoriesCollections r3 = this.this$0.getStoriesController().getStoryAlbumsList(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0));
            PeerStoriesView r12 = this.this$0;
            ItemOptions.addAlbumsItemOptions(r2, r3, r4, r12.storiesController.canCreateNewAlbum(PeerStoriesView.m19463$$Nest$fgetdialogId(r12)), new PeerStoriesView$8$$ExternalSyntheticLambda42(this, this.val$resourcesProvider, r0), new PeerStoriesView$8$$ExternalSyntheticLambda43(this, r4, r0, this.val$resourcesProvider));
            PeerStoriesView.m19530$$Nest$fputalbumLayout(this.this$0, r2.getLinearLayout());
            int r02 = r9.addViewToSwipeBack(PeerStoriesView.m19450$$Nest$fgetalbumLayout(this.this$0));
            PeerStoriesView.m19529$$Nest$fputalbumItem(this.this$0, new ActionBarMenuSubItem(this.this$0.getContext(), false, false, false, this.val$resourcesProvider));
            PeerStoriesView.m19449$$Nest$fgetalbumItem(this.this$0).setTextAndIcon(LocaleController.getString(R.string.StoriesAlbumAddToAlbum), R.drawable.menu_album_add, null);
            ActionBarMenuSubItem r13 = PeerStoriesView.m19449$$Nest$fgetalbumItem(this.this$0);
            r13.openSwipeBackLayout = new PeerStoriesView$8$$ExternalSyntheticLambda44(r9, r02);
            PeerStoriesView.m19449$$Nest$fgetalbumItem(this.this$0).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda45(this));
            r9.addView(PeerStoriesView.m19449$$Nest$fgetalbumItem(this.this$0));
            r9.swipeBackGravityRight = true;
            if (r10 == false) goto L14;
            ActionBarPopupWindow.GapView r102 = new ActionBarPopupWindow.GapView(this.this$0.getContext(), this.val$resourcesProvider, Theme.key_actionBarDefaultSubmenuSeparator);
            r102.setTag(R.id.fit_width_tag, 1);
            r9.addView(r102, LayoutHelper.createLinear(-1, 8));
            return;
        L14:
            return;
        L9:
            r1 = new HashSet();
            goto L8
        }

        private void addSpeedLayout(ActionBarPopupWindow.ActionBarPopupWindowLayout r11, boolean r12) {
            if (this.val$speedControl == false) goto L16;
            PeerStoriesView r0 = this.this$0;
            StoryItemHolder r2 = r0.currentStory;
            if (r2 != null) goto L7;
        L9:
            PeerStoriesView.m19555$$Nest$fputspeedLayout(r0, new ChooseSpeedLayout(r0.getContext(), r11.getSwipeBack(), new AnonymousClass2(this, r11)));
            PeerStoriesView.m19516$$Nest$fgetspeedLayout(this.this$0).update(StoryViewer.currentSpeed, true);
            PeerStoriesView.m19554$$Nest$fputspeedItem(this.this$0, new ActionBarMenuSubItem(this.this$0.getContext(), false, false, false, this.val$resourcesProvider));
            PeerStoriesView.m19515$$Nest$fgetspeedItem(this.this$0).setTextAndIcon(LocaleController.getString(R.string.Speed), R.drawable.msg_speed, null);
            PeerStoriesView.m19590$$Nest$mupdateSpeedItem(this.this$0, true);
            PeerStoriesView.m19515$$Nest$fgetspeedItem(this.this$0).setMinimumWidth(AndroidUtilities.dp(196.0f));
            PeerStoriesView.m19515$$Nest$fgetspeedItem(this.this$0).setRightIcon(R.drawable.msg_arrowright);
            r11.addView(PeerStoriesView.m19515$$Nest$fgetspeedItem(this.this$0));
            LinearLayout.LayoutParams r02 = (LinearLayout.LayoutParams) PeerStoriesView.m19515$$Nest$fgetspeedItem(this.this$0).getLayoutParams();
            if (LocaleController.isRTL == false) goto L12;
            r02.gravity = 5;
        L12:
            r02.width = -1;
            r02.height = AndroidUtilities.dp(48.0f);
            PeerStoriesView.m19515$$Nest$fgetspeedItem(this.this$0).setLayoutParams(r02);
            int r03 = r11.addViewToSwipeBack(PeerStoriesView.m19516$$Nest$fgetspeedLayout(this.this$0).speedSwipeBackLayout);
            ActionBarMenuSubItem r22 = PeerStoriesView.m19515$$Nest$fgetspeedItem(this.this$0);
            r22.openSwipeBackLayout = new PeerStoriesView$8$$ExternalSyntheticLambda39(r11, r03);
            PeerStoriesView.m19515$$Nest$fgetspeedItem(this.this$0).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda40(this));
            r11.swipeBackGravityRight = true;
            if (r12 == false) goto L18;
            ActionBarPopupWindow.GapView r122 = new ActionBarPopupWindow.GapView(this.this$0.getContext(), this.val$resourcesProvider, Theme.key_actionBarDefaultSubmenuSeparator);
            r122.setTag(R.id.fit_width_tag, 1);
            r11.addView(r122, LayoutHelper.createLinear(-1, 8));
            return;
        L18:
            return;
        L7:
            if (r2.uploadingStory == null) goto L9;
        L16:
            PeerStoriesView.m19555$$Nest$fputspeedLayout(this.this$0, null);
            PeerStoriesView.m19554$$Nest$fputspeedItem(this.this$0, null);
        }

        private void addViewStatistics(ActionBarPopupWindow.ActionBarPopupWindowLayout r10, TL_stories.StoryItem r11) {
            PeerStoriesView r0 = this.this$0;
            if (r0.isChannel == false) goto L17;
            if (r11 != null) goto L6;
            return;
        L6:
            if ((r11.media instanceof TLRPC.TL_messageMediaVideoStream) == true) goto L19;
            TLRPC.Chat r02 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r0)).getChat(Long.valueOf(-PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0)));
            if (r02 == null) goto L20;
            TLRPC.ChatFull r1 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getChatFull(r02.id);
            if (r1 != null) goto L12;
            r1 = MessagesStorage.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).loadChatInfo(r02.id, true, new CountDownLatch(1), false, false);
        L12:
            if (r1 != null) goto L14;
            return;
        L14:
            if (r1.can_view_stats == false) goto L22;
            ActionBarMenuItem.addItem(r10, R.drawable.msg_stats, LocaleController.getString(R.string.ViewStatistics), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda51(this, r11, this.val$storyViewer, r02));
            return;
        L22:
            return;
        L20:
            return;
        L19:
            return;
        }

        private /* synthetic */ void lambda$addAlbumsLayout$4(TL_stories.StoryItem r5, Theme.ResourcesProvider r6, StoriesController.StoryAlbum r7) {
            PeerStoriesView r0 = this.this$0;
            r0.storiesController.addStoryToAlbum(PeerStoriesView.m19463$$Nest$fgetdialogId(r0), r7.album_id, r5);
            BulletinFactory.of(this.this$0.storyContainer, r6).createSimpleBulletin(R.raw.contact_check, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.StoryAddedToAlbumX, new Object[]{r7.title}))).show();
        }

        private /* synthetic */ void lambda$addAlbumsLayout$5(TL_stories.StoryItem r5, Theme.ResourcesProvider r6, String r7) {
            this.this$0.getStoriesController().createAlbum(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), r7, new PeerStoriesView$8$$ExternalSyntheticLambda7(this, r5, r6));
        }

        private /* synthetic */ void lambda$addAlbumsLayout$6(Theme.ResourcesProvider r3, TL_stories.StoryItem r4) {
            AlertsCreator.createStoriesAlbumEnterNameForCreate(this.this$0.getContext(), null, r3, new PeerStoriesView$8$$ExternalSyntheticLambda59(this, r4, r3));
            CustomPopupMenu r2 = this.this$0.popupMenu;
            if (r2 == null) goto L6;
            r2.dismiss();
            return;
        }

        private /* synthetic */ void lambda$addAlbumsLayout$7(HashSet r4, TL_stories.StoryItem r5, Theme.ResourcesProvider r6, StoriesController.StoryAlbum r7) {
            boolean r42 = r4.contains(Integer.valueOf(r7.album_id));
            PeerStoriesView r0 = this.this$0;
            if (r42 == false) goto L5;
            r0.storiesController.addStoryToAlbum(PeerStoriesView.m19463$$Nest$fgetdialogId(r0), r7.album_id, r5);
            String r43 = LocaleController.formatString(R.string.StoryAddedToAlbumX, new Object[]{r7.title});
        L6:
            BulletinFactory.of(this.this$0.storyContainer, r6).createSimpleBulletin(R.raw.contact_check, AndroidUtilities.replaceTags(r43)).show();
            CustomPopupMenu r3 = this.this$0.popupMenu;
            if (r3 == null) goto L10;
            r3.dismiss();
            return;
        L10:
            return;
        L5:
            r0.storiesController.removeStoryFromAlbum(PeerStoriesView.m19463$$Nest$fgetdialogId(r0), r7.album_id, r5);
            r43 = LocaleController.formatString(R.string.StoryRemovedFromAlbumX, new Object[]{r7.title});
            goto L6
        }

        private /* synthetic */ void lambda$addAlbumsLayout$9(View r1) {
            PeerStoriesView.m19449$$Nest$fgetalbumItem(this.this$0).openSwipeBack();
        }

        private /* synthetic */ void lambda$addSpeedLayout$2(View r1) {
            PeerStoriesView.m19515$$Nest$fgetspeedItem(this.this$0).openSwipeBack();
        }

        private /* synthetic */ void lambda$addViewStatistics$0(TL_stories.StoryItem r7, StoryViewer r8, TLRPC.Chat r9, View r10) {
            CustomPopupMenu r102 = this.this$0.popupMenu;
            if (r102 == null) goto L5;
            r102.dismiss();
        L5:
            r7.dialogId = PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0);
            r7.messageId = r7.id;
            MessageObject r2 = new MessageObject(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0), r7);
            r2.generateThumbs(false);
            r8.presentFragment(new AnonymousClass1(this, r2, r9.id, false));
        }

        private /* synthetic */ void lambda$onCreate$10(View r1) {
            StoriesController.UploadingStory r12 = this.this$0.currentStory.uploadingStory;
            if (r12 == null) goto L5;
            r12.cancel();
            PeerStoriesView.m19591$$Nest$mupdateStoryItems(this.this$0);
        L5:
            CustomPopupMenu r0 = this.this$0.popupMenu;
            if (r0 == null) goto L9;
            r0.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$11(StoryPrivacyBottomSheet.StoryPrivacy r1, TL_stories.StoryItem r2, View r3) {
            PeerStoriesView.m19569$$Nest$meditPrivacy(this.this$0, r1, r2);
            CustomPopupMenu r0 = this.this$0.popupMenu;
            if (r0 == null) goto L6;
            r0.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$12() {
            PeerStoriesView r1 = this.this$0;
            r1.editOpened = true;
            r1.setActive(false);
        }

        private /* synthetic */ void lambda$onCreate$15(SharedResources r15, Long r16, Runnable r17, Boolean r18, Long r19) {
            long r1 = System.currentTimeMillis();
            PeerStoriesView r3 = this.this$0;
            VideoPlayerSharedScope r4 = r3.playerSharedScope;
            StoryViewer.VideoPlayerHolder r5 = r4.player;
            if (r5 != null) goto L9;
            r3.delegate.setPopupIsVisible(false);
            this.this$0.setActive(true);
            PeerStoriesView r12 = this.this$0;
            r12.editOpened = false;
            PeerStoriesView.m19550$$Nest$fputonImageReceiverThumbLoaded(r12, new PeerStoriesView$8$$ExternalSyntheticLambda3(r17));
            if (r18.booleanValue() == false) goto L7;
            this.this$0.updatePosition();
        L7:
            AndroidUtilities.runOnUIThread(r17, 400);
            return;
        L9:
            r5.firstFrameRendered = false;
            r4.firstFrameRendered = false;
            r5.setOnReadyListener(new PeerStoriesView$8$$ExternalSyntheticLambda4(r17, r1));
            this.this$0.delegate.setPopupIsVisible(false);
            if (PeerStoriesView.m19488$$Nest$fgetmuteIconView(this.this$0) == null) goto L13;
            PeerStoriesView.m19488$$Nest$fgetmuteIconView(this.this$0).setAnimation(r15.muteDrawable);
        L13:
            if (this.this$0.videoDuration > 0) goto L15;
        L17:
            Long r13 = r16;
        L18:
            this.this$0.setActive(r13.longValue(), true);
            this.this$0.editOpened = false;
            AndroidUtilities.runOnUIThread(r17, 400);
            if (r18.booleanValue() == false) goto L22;
            this.this$0.updatePosition();
            return;
        L22:
            return;
        L15:
            if (r16.longValue() <= (this.this$0.videoDuration - 1400)) goto L17;
            r13 = 0L;
            goto L18
        }

        private /* synthetic */ void lambda$onCreate$16(Activity r9, StoryViewer r10, SharedResources r11) {
            StoryRecorder r1 = StoryRecorder.getInstance(r9, PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0));
            VideoPlayerSharedScope r92 = this.this$0.playerSharedScope;
            if (r92 == null) goto L8;
            StoryViewer.VideoPlayerHolder r93 = r92.player;
            if (r93 == null) goto L8;
            long r2 = r93.currentPosition;
        L7:
            long r4 = r2;
            DraftsController r94 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getStoriesController().getDraftsController();
            TL_stories.StoryItem r0 = this.this$0.currentStory.storyItem;
            StoryEntry r95 = r94.getForEdit(r0.dialogId, r0);
            if (r95 != null) goto L12;
        L17:
            r95 = StoryEntry.fromStoryItem(this.this$0.currentStory.getPath(), this.this$0.currentStory.storyItem);
            r95.editStoryPeerId = PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0);
        L18:
            StoryEntry r3 = r95.copy();
            if (PeerStoriesView.m19576$$Nest$misBotsPreview(this.this$0) == false) goto L23;
            r3.botId = PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0);
            r3.editingBotPreview = MessagesController.toInputMedia(this.this$0.currentStory.storyItem.media);
            StoriesController.StoriesList r96 = r10.storiesList;
            if ((r96 instanceof StoriesController.BotPreviewsList) == false) goto L23;
            r3.botLang = ((StoriesController.BotPreviewsList) r96).lang_code;
        L23:
            r1.openEdit(StoryRecorder.SourceView.fromStoryViewer(r10), r3, r4, true);
            r1.setOnFullyOpenListener(new PeerStoriesView$8$$ExternalSyntheticLambda57(this));
            r1.setOnPrepareCloseListener(new PeerStoriesView$8$$ExternalSyntheticLambda58(this, r11));
            return;
        L12:
            if (r95.isRepostMessage == true) goto L17;
            File r02 = r95.file;
            if (r02 == null) goto L17;
            if (r02.exists() == true) goto L18;
        L8:
            r2 = 0;
            goto L7
        }

        private /* synthetic */ void lambda$onCreate$17(Theme.ResourcesProvider r3, Context r4, StoryViewer r5, SharedResources r6, View r7) {
            if (r7.getAlpha() >= 1.0f) goto L6;
            PeerStoriesView r42 = this.this$0;
            int r52 = -PeerStoriesView.m19514$$Nest$fgetshiftDp(r42);
            PeerStoriesView.m19553$$Nest$fputshiftDp(r42, r52);
            AndroidUtilities.shakeViewSpring(r7, r52);
            BulletinFactory.of(this.this$0.storyContainer, r3).createErrorBulletin("Wait until current upload is complete").show();
            return;
        L6:
            Activity r32 = AndroidUtilities.findActivity(r4);
            if (r32 == null) goto L17;
            this.edit = true;
            CustomPopupMenu r43 = this.this$0.popupMenu;
            if (r43 == null) goto L12;
            r43.dismiss();
        L12:
            PeerStoriesView$8$$ExternalSyntheticLambda50 r44 = new PeerStoriesView$8$$ExternalSyntheticLambda50(this, r32, r5, r6);
            if (this.this$0.delegate.releasePlayer(r44) == true) goto L16;
            r44.run();
            return;
        L16:
            return;
        }

        private /* synthetic */ void lambda$onCreate$19(TLObject r4, TL_stories.StoryItem r5, Utilities.Callback r6) {
            if ((r4 instanceof TL_stories.TL_stories_stories) == false) goto L12;
            TL_stories.TL_stories_stories r42 = (TL_stories.TL_stories_stories) r4;
            int r2 = 0;
            MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).putUsers(r42.users, false);
            MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).putChats(r42.chats, false);
        L6:
            if (r2 >= r42.stories.size()) goto L12;
            if (r42.stories.get(r2).id == r5.id) goto L9;
            r2 = r2 + 1;
            goto L6
        L9:
            r6.run(r42.stories.get(r2).media.document);
            return;
        L12:
            r6.run(null);
        }

        private /* synthetic */ void lambda$onCreate$20(TL_stories.StoryItem r1, Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new PeerStoriesView$8$$ExternalSyntheticLambda8(this, r3, r1, r2));
        }

        private /* synthetic */ void lambda$onCreate$21(TL_stories.StoryItem r4, TL_stories.StoryItem r5, Utilities.Callback r6) {
            if ((r4 instanceof StoriesController.BotPreview) == false) goto L8;
            StoriesController.BotPreviewsList r0 = ((StoriesController.BotPreview) r4).list;
            if (r0 == null) goto L8;
            r0.reload(new PeerStoriesView$8$$ExternalSyntheticLambda5(r0, r5, r6));
            return;
        L8:
            TL_stories.TL_stories_getStoriesByID r52 = new TL_stories.TL_stories_getStoriesByID();
            r52.peer = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getInputPeer(r4.dialogId);
            r52.id.add(Integer.valueOf(r4.id));
            ConnectionsManager.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r52, new PeerStoriesView$8$$ExternalSyntheticLambda6(this, r4, r6));
        }

        private /* synthetic */ void lambda$onCreate$22() {
            PeerStoriesView r1 = this.this$0;
            r1.editOpened = true;
            r1.setActive(false);
        }

        private /* synthetic */ void lambda$onCreate$25(SharedResources r15, Long r16, Runnable r17, Boolean r18, Long r19) {
            long r1 = System.currentTimeMillis();
            PeerStoriesView r3 = this.this$0;
            VideoPlayerSharedScope r4 = r3.playerSharedScope;
            StoryViewer.VideoPlayerHolder r5 = r4.player;
            if (r5 != null) goto L9;
            r3.delegate.setPopupIsVisible(false);
            this.this$0.setActive(true);
            PeerStoriesView r12 = this.this$0;
            r12.editOpened = false;
            PeerStoriesView.m19550$$Nest$fputonImageReceiverThumbLoaded(r12, new PeerStoriesView$8$$ExternalSyntheticLambda1(r17));
            if (r18.booleanValue() == false) goto L7;
            this.this$0.updatePosition();
        L7:
            AndroidUtilities.runOnUIThread(r17, 400);
            return;
        L9:
            r5.firstFrameRendered = false;
            r4.firstFrameRendered = false;
            r5.setOnReadyListener(new PeerStoriesView$8$$ExternalSyntheticLambda2(r17, r1));
            this.this$0.delegate.setPopupIsVisible(false);
            if (PeerStoriesView.m19488$$Nest$fgetmuteIconView(this.this$0) == null) goto L13;
            PeerStoriesView.m19488$$Nest$fgetmuteIconView(this.this$0).setAnimation(r15.muteDrawable);
        L13:
            if (this.this$0.videoDuration > 0) goto L15;
        L17:
            Long r13 = r16;
        L18:
            this.this$0.setActive(r13.longValue(), true);
            this.this$0.editOpened = false;
            AndroidUtilities.runOnUIThread(r17, 400);
            if (r18.booleanValue() == false) goto L22;
            this.this$0.updatePosition();
            return;
        L22:
            return;
        L15:
            if (r16.longValue() <= (this.this$0.videoDuration - 1400)) goto L17;
            r13 = 0L;
            goto L18
        }

        private /* synthetic */ void lambda$onCreate$26(Activity r8, TL_stories.StoryItem r9, StoryViewer r10, SharedResources r11) {
            StoryRecorder r1 = StoryRecorder.getInstance(r8, PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0));
            VideoPlayerSharedScope r82 = this.this$0.playerSharedScope;
            if (r82 == null) goto L8;
            StoryViewer.VideoPlayerHolder r83 = r82.player;
            if (r83 == null) goto L8;
            long r2 = r83.currentPosition;
        L7:
            long r4 = r2;
            StoryEntry r84 = StoryEntry.fromStoryItem(this.this$0.currentStory.getPath(), this.this$0.currentStory.storyItem);
            r84.editStoryPeerId = PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0);
            r84.cover = StoryEntry.getCoverTime(this.this$0.currentStory.storyItem);
            StoryEntry r3 = r84.copy();
            r3.isEditingCover = true;
            TL_stories.StoryItem r85 = this.this$0.currentStory.storyItem;
            r3.editingCoverDocument = r85.media.document;
            r3.updateDocumentRef = new PeerStoriesView$8$$ExternalSyntheticLambda54(this, r85, r9);
            if (PeerStoriesView.m19576$$Nest$misBotsPreview(this.this$0) == false) goto L14;
            r3.botId = PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0);
            r3.editingBotPreview = MessagesController.toInputMedia(this.this$0.currentStory.storyItem.media);
            StoriesController.StoriesList r86 = r10.storiesList;
            if ((r86 instanceof StoriesController.BotPreviewsList) == false) goto L14;
            r3.botLang = ((StoriesController.BotPreviewsList) r86).lang_code;
        L14:
            r1.openEdit(StoryRecorder.SourceView.fromStoryViewer(r10), r3, r4, true);
            r1.setOnFullyOpenListener(new PeerStoriesView$8$$ExternalSyntheticLambda55(this));
            r1.setOnPrepareCloseListener(new PeerStoriesView$8$$ExternalSyntheticLambda56(this, r11));
            return;
        L8:
            r2 = 0;
            goto L7
        }

        private /* synthetic */ void lambda$onCreate$27(Context r7, TL_stories.StoryItem r8, StoryViewer r9, SharedResources r10, View r11) {
            File r112 = this.this$0.currentStory.getPath();
            if (r112 == null) goto L17;
            if (r112.exists() == false) goto L17;
            Activity r2 = AndroidUtilities.findActivity(r7);
            if (r2 == null) goto L20;
            this.edit = true;
            CustomPopupMenu r72 = this.this$0.popupMenu;
            if (r72 == null) goto L13;
            r72.dismiss();
        L13:
            PeerStoriesView$8$$ExternalSyntheticLambda46 r0 = new PeerStoriesView$8$$ExternalSyntheticLambda46(this, r2, r8, r9, r10);
            if (this.this$0.delegate.releasePlayer(r0) == true) goto L19;
            r0.run();
            return;
        L19:
            return;
        L20:
            return;
        L17:
            PeerStoriesView.m19585$$Nest$mshowDownloadAlert(this.this$0);
        }

        private /* synthetic */ void lambda$onCreate$28(TL_stories.StoryItem r1, boolean r2, Theme.ResourcesProvider r3, Boolean r4) {
            if (r4.booleanValue() == false) goto L21;
            r1.pinned = r2;
            PeerStoriesView r0 = this.this$0;
            if (r0.isSelf == false) goto L16;
            BulletinFactory r02 = BulletinFactory.of(r0.storyContainer, r3);
            if (r2 == false) goto L9;
            int r12 = R.raw.contact_check;
        L10:
            if (r2 == false) goto L13;
            int r22 = R.string.StoryPinnedToProfile;
        L14:
            r02.createSimpleBulletin(r12, LocaleController.getString(r22)).show();
            return;
        L13:
            r22 = R.string.StoryArchivedFromProfile;
            goto L14
        L9:
            r12 = R.raw.chats_archived;
            goto L10
        L16:
            if (r2 == false) goto L19;
            BulletinFactory.of(r0.storyContainer, r3).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.StoryPinnedToPosts), LocaleController.getString(R.string.StoryPinnedToPostsDescription)).show();
            return;
        L19:
            BulletinFactory.of(r0.storyContainer, r3).createSimpleBulletin(R.raw.chats_archived, LocaleController.getString(R.string.StoryUnpinnedFromPosts)).show();
            return;
        L21:
            BulletinFactory.of(this.this$0.storyContainer, r3).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.UnknownError)).show();
        }

        private /* synthetic */ void lambda$onCreate$29(TL_stories.StoryItem r7, boolean r8, Theme.ResourcesProvider r9, View r10) {
            ArrayList<TL_stories.StoryItem> r3 = new ArrayList();
            r3.add(r7);
            MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getStoriesController().updateStoriesPinned(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), r3, r8, new PeerStoriesView$8$$ExternalSyntheticLambda38(this, r7, r8, r9));
            CustomPopupMenu r6 = this.this$0.popupMenu;
            if (r6 == null) goto L6;
            r6.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$30(View r1) {
            PeerStoriesView.m19583$$Nest$msaveToGallery(this.this$0);
            CustomPopupMenu r0 = this.this$0.popupMenu;
            if (r0 == null) goto L6;
            r0.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$31(View r1) {
            AndroidUtilities.addToClipboard(this.this$0.currentStory.createLink());
            PeerStoriesView.m19580$$Nest$monLinkCopied(this.this$0);
            CustomPopupMenu r0 = this.this$0.popupMenu;
            if (r0 == null) goto L6;
            r0.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$32(View r2) {
            PeerStoriesView.m19584$$Nest$mshareStory(this.this$0, false);
            CustomPopupMenu r1 = this.this$0.popupMenu;
            if (r1 == null) goto L6;
            r1.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$33(boolean r1, View r2) {
            LivePlayer r22 = LivePlayer.recording;
            if (r22 == null) goto L5;
            r22.setMuted(!r1);
        L5:
            CustomPopupMenu r0 = this.this$0.popupMenu;
            if (r0 == null) goto L9;
            r0.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$34(View r1) {
            LivePlayer r12 = LivePlayer.recording;
            if (r12 == null) goto L5;
            r12.switchCamera();
        L5:
            CustomPopupMenu r0 = this.this$0.popupMenu;
            if (r0 == null) goto L9;
            r0.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$35(StoryViewer r1, View r2) {
            CustomPopupMenu r0 = this.this$0.popupMenu;
            if (r0 == null) goto L5;
            r0.dismiss();
        L5:
            if (r1 == null) goto L8;
            r1.switchToPip();
            return;
        }

        private /* synthetic */ void lambda$onCreate$37(StoryPrivacyBottomSheet r1, TLObject r2, TLRPC.TL_error r3) {
            if ((r2 instanceof TLRPC.Updates) == false) goto L5;
            MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).processUpdates((TLRPC.Updates) r2, false);
        L5:
            AndroidUtilities.runOnUIThread(new PeerStoriesView$8$$ExternalSyntheticLambda0(r1));
        }

        private /* synthetic */ void lambda$onCreate$38(StoryPrivacyBottomSheet r7, StoryPrivacyBottomSheet.StoryPrivacy r8, boolean r9, boolean r10, boolean r11, boolean r12, TLRPC.InputPeer r13, int r14, Runnable r15, Runnable r16) {
            PeerStoriesView r82 = this.this$0;
            TL_stories.StoryItem r102 = r82.currentStory.storyItem;
            if (r102 != null) goto L5;
        L7:
            boolean r103 = false;
        L8:
            if (r103 == r11) goto L10;
            MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r82)).getStoriesController().updateStoriesPinned(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), this.this$0.storyItems, r11, null);
        L10:
            TL_stories.StoryItem r83 = this.this$0.currentStory.storyItem;
            if (r83 == null) goto L16;
            TLRPC.MessageMedia r84 = r83.media;
            if ((r84 instanceof TLRPC.TL_messageMediaVideoStream) == false) goto L17;
            TLRPC.InputGroupCall r85 = ((TLRPC.TL_messageMediaVideoStream) r84).call;
            TL_phone.toggleGroupCallSettings r104 = new TL_phone.toggleGroupCallSettings();
            r104.call = r85;
            r104.messages_enabled = Boolean.valueOf(r9);
            r104.send_paid_messages_stars = Long.valueOf(r14);
            ConnectionsManager.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r104, new PeerStoriesView$8$$ExternalSyntheticLambda53(this, r7));
            return;
        L17:
            return;
        L16:
            return;
        L5:
            if (r102.pinned == false) goto L7;
            r103 = true;
            goto L8
        }

        private /* synthetic */ void lambda$onCreate$39(Theme.ResourcesProvider r6, StoryViewer r7, View r8) {
            CustomPopupMenu r82 = this.this$0.popupMenu;
            if (r82 == null) goto L5;
            r82.dismiss();
        L5:
            StoryPrivacyBottomSheet r83 = new StoryPrivacyBottomSheet(this.this$0.getContext(), 86400, r6);
            boolean r62 = true;
            StoryPrivacyBottomSheet r84 = r83.setLive(true).setPeer(MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getInputPeer(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0))).setLiveSettings(true).allowCover(false).setCount(1).isEdit(false);
            LivePlayer r1 = r7.livePlayer;
            if (r1 != null) goto L8;
        L10:
            boolean r12 = false;
        L11:
            boolean r2 = this.this$0.currentStory.allowScreenshots();
            TL_stories.StoryItem r3 = this.this$0.currentStory.storyItem;
            if (r3 != null) goto L14;
        L16:
            r62 = false;
        L17:
            LivePlayer r72 = r7.livePlayer;
            if (r72 != null) goto L20;
            int r73 = 0;
        L21:
            StoryPrivacyBottomSheet r63 = r84.set(r12, r2, r62, r73);
            r63.whenSelectedRules(new PeerStoriesView$8$$ExternalSyntheticLambda37(this, r63), false);
            r63.show();
            return;
        L20:
            r73 = (int) r72.getSendPaidMessagesStars();
            goto L21
        L14:
            if (r3.pinned == false) goto L16;
        L8:
            if (r1.areMessagesEnabled() == false) goto L10;
            r12 = true;
            goto L11
        }

        private /* synthetic */ void lambda$onCreate$40(StoryViewer r1, AlertDialog r2, int r3) {
            LivePlayer r12 = r1.livePlayer;
            if (r12 == null) goto L6;
            r12.end();
            return;
        L6:
            PeerStoriesView.m19566$$Nest$mdeleteStory(this.this$0);
        }

        private /* synthetic */ void lambda$onCreate$41(Theme.ResourcesProvider r2, StoryViewer r3, View r4) {
            CustomPopupMenu r42 = this.this$0.popupMenu;
            if (r42 == null) goto L5;
            r42.dismiss();
        L5:
            new AlertDialog.Builder(this.this$0.getContext(), r2).setTitle(LocaleController.getString(R.string.LiveStoryEndAlertTitle)).setMessage(LocaleController.getString(R.string.LiveStoryEndAlertText)).setPositiveButton(LocaleController.getString(R.string.LiveStoryEndAlertButton), new PeerStoriesView$8$$ExternalSyntheticLambda49(this, r3)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).makeRed(-1).show();
        }

        private /* synthetic */ void lambda$onCreate$42(View r1) {
            PeerStoriesView.m19566$$Nest$mdeleteStory(this.this$0);
            CustomPopupMenu r0 = this.this$0.popupMenu;
            if (r0 == null) goto L6;
            r0.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$43(String r5, Theme.ResourcesProvider r6, TLObject r7, String r8, View r9) {
            MessagesController.getNotificationsSettings(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).edit().putBoolean(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r5, false).apply();
            NotificationsController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).updateServerNotificationsSettings(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), 0);
            BulletinFactory.of(this.this$0.storyContainer, r6).createUsersBulletin(Arrays.asList(new TLObject[]{r7}), AndroidUtilities.replaceTags(LocaleController.formatString("NotificationsStoryMutedHint", R.string.NotificationsStoryMutedHint, new Object[]{r8}))).setTag(2).show();
            CustomPopupMenu r4 = this.this$0.popupMenu;
            if (r4 == null) goto L6;
            r4.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$44(String r5, Theme.ResourcesProvider r6, TLObject r7, String r8, View r9) {
            MessagesController.getNotificationsSettings(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).edit().putBoolean(NotificationsSettingsFacade.PROPERTY_STORIES_NOTIFY + r5, true).apply();
            NotificationsController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).updateServerNotificationsSettings(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), 0);
            BulletinFactory.of(this.this$0.storyContainer, r6).createUsersBulletin(Arrays.asList(new TLObject[]{r7}), AndroidUtilities.replaceTags(LocaleController.formatString("NotificationsStoryUnmutedHint", R.string.NotificationsStoryUnmutedHint, new Object[]{r8}))).setTag(2).show();
            CustomPopupMenu r4 = this.this$0.popupMenu;
            if (r4 == null) goto L6;
            r4.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$45(View r7) {
            MediaDataController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).removePeer(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0));
            PeerStoriesView r72 = this.this$0;
            r72.storiesController.toggleHidden(PeerStoriesView.m19463$$Nest$fgetdialogId(r72), true, false, true);
            CustomPopupMenu r6 = this.this$0.popupMenu;
            if (r6 == null) goto L6;
            r6.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$46(View r3) {
            PeerStoriesView r32 = this.this$0;
            PeerStoriesView.m19588$$Nest$mtoggleArchiveForStory(r32, PeerStoriesView.m19463$$Nest$fgetdialogId(r32));
            CustomPopupMenu r2 = this.this$0.popupMenu;
            if (r2 == null) goto L6;
            r2.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$47(View r3) {
            PeerStoriesView r32 = this.this$0;
            PeerStoriesView.m19588$$Nest$mtoggleArchiveForStory(r32, PeerStoriesView.m19463$$Nest$fgetdialogId(r32));
            CustomPopupMenu r2 = this.this$0.popupMenu;
            if (r2 == null) goto L6;
            r2.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$48(StoryViewer r1, View r2) {
            CustomPopupMenu r0 = this.this$0.popupMenu;
            if (r0 == null) goto L5;
            r0.dismiss();
        L5:
            if (r1 == null) goto L8;
            r1.switchToPip();
            return;
        }

        private /* synthetic */ void lambda$onCreate$49(View r1) {
            PeerStoriesView.m19583$$Nest$msaveToGallery(this.this$0);
            CustomPopupMenu r0 = this.this$0.popupMenu;
            if (r0 == null) goto L6;
            r0.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$52(View r1) {
            AndroidUtilities.addToClipboard(this.this$0.currentStory.createLink());
            PeerStoriesView.m19580$$Nest$monLinkCopied(this.this$0);
            CustomPopupMenu r0 = this.this$0.popupMenu;
            if (r0 == null) goto L6;
            r0.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$53(View r2) {
            PeerStoriesView.m19584$$Nest$mshareStory(this.this$0, false);
            CustomPopupMenu r1 = this.this$0.popupMenu;
            if (r1 == null) goto L6;
            r1.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$54(View r4) {
            PeerStoriesView r42 = this.this$0;
            r42.currentStory.storyItem.translated = false;
            StoriesStorage r43 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r42)).getStoriesController().getStoriesStorage();
            TL_stories.StoryItem r0 = this.this$0.currentStory.storyItem;
            r43.updateStoryItem(r0.dialogId, r0);
            this.this$0.cancelTextSelection();
            this.this$0.updatePosition();
            CustomPopupMenu r3 = this.this$0.popupMenu;
            if (r3 == null) goto L6;
            r3.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$55() {
            Delegate r0 = this.this$0.delegate;
            if (r0 == null) goto L5;
            r0.setTranslating(false);
        L5:
            this.this$0.updatePosition();
            PeerStoriesView r2 = this.this$0;
            r2.checkBlackoutMode = true;
            PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(r2).expand(true);
        }

        private /* synthetic */ void lambda$onCreate$57(View r7) {
            PeerStoriesView r72 = this.this$0;
            r72.currentStory.storyItem.translated = true;
            r72.cancelTextSelection();
            Delegate r73 = this.this$0.delegate;
            if (r73 == null) goto L5;
            r73.setTranslating(true);
        L5:
            StoriesStorage r74 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getStoriesController().getStoriesStorage();
            TL_stories.StoryItem r0 = this.this$0.currentStory.storyItem;
            r74.updateStoryItem(r0.dialogId, r0);
            long r2 = System.currentTimeMillis();
            PeerStoriesView$8$$ExternalSyntheticLambda47 r75 = new PeerStoriesView$8$$ExternalSyntheticLambda47(this);
            MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getTranslateController().translateStory(this.this$0.currentStory.storyItem, new PeerStoriesView$8$$ExternalSyntheticLambda48(r75, r2));
            this.this$0.updatePosition();
            PeerStoriesView r76 = this.this$0;
            r76.checkBlackoutMode = true;
            PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(r76).expand(true);
            CustomPopupMenu r6 = this.this$0.popupMenu;
            if (r6 == null) goto L9;
            r6.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$59(StoryViewer r7, Theme.ResourcesProvider r8, View r9) {
            if (r7 == null) goto L4;
            r7.setOverlayVisible(true);
        L4:
            int r0 = PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0);
            Context r1 = this.this$0.getContext();
            PeerStoriesView r92 = this.this$0;
            ReportBottomSheet.openStory(r0, r1, r92.currentStory.storyItem, BulletinFactory.of(r92.storyContainer, r8), r8, new PeerStoriesView$8$$ExternalSyntheticLambda52(r7));
            CustomPopupMenu r6 = this.this$0.popupMenu;
            if (r6 == null) goto L8;
            r6.dismiss();
            return;
        }

        private /* synthetic */ void lambda$onCreate$60(StoryContainsEmojiButton r1, View r2) {
            EmojiPacksAlert r12 = r1.getAlert();
            if (r12 == null) goto L8;
            Delegate r22 = this.this$0.delegate;
            if (r22 == null) goto L9;
            r22.showDialog(r12);
            this.this$0.popupMenu.dismiss();
            return;
        L9:
            return;
        }

        private /* synthetic */ void lambda$onDismissed$61() {
            this.this$0.delegate.setPopupIsVisible(false);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r14v3 */
        /* JADX WARN: Type inference failed for: r14v4, types: [org.telegram.tgnet.TLRPC$User] */
        /* JADX WARN: Type inference failed for: r14v6 */
        /* JADX WARN: Type inference failed for: r21v0, types: [android.view.ViewGroup, org.telegram.ui.ActionBar.ActionBarPopupWindow$ActionBarPopupWindowLayout] */
        /* JADX WARN: Type inference failed for: r3v92, types: [org.telegram.tgnet.TLRPC$User] */
        @Override // org.telegram.ui.Components.CustomPopupMenu
        public void onCreate(ActionBarPopupWindow.ActionBarPopupWindowLayout r21) {
            boolean r7 = true;
            if (this.val$canEditStory == false) goto L5;
        L104:
            StoryItemHolder r0 = this.this$0.currentStory;
            TL_stories.StoryItem r12 = r0.storyItem;
            if (r0.uploadingStory == null) goto L107;
            ActionBarMenuItem.addItem(r21, R.drawable.msg_cancel, LocaleController.getString(R.string.Cancel), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda9(this));
        L107:
            if (r12 == null) goto L287;
            PeerStoriesView r02 = this.this$0;
            if (r02.isSelf == true) goto L114;
            if (r02.storiesController.canEditStories(PeerStoriesView.m19463$$Nest$fgetdialogId(r02)) == true) goto L114;
        L117:
            if (this.this$0.isSelf == false) goto L123;
            boolean r03 = r12.privacy.isEmpty();
            PeerStoriesView r2 = this.this$0;
            if (r03 == false) goto L121;
            StoryPrivacyBottomSheet.StoryPrivacy r04 = new StoryPrivacyBottomSheet.StoryPrivacy(3, PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r2), new ArrayList());
        L122:
            ActionBarMenuSubItem r22 = ActionBarMenuItem.addItem(r21, R.drawable.msg_view_file, LocaleController.getString(R.string.WhoCanSee), false, this.val$resourcesProvider);
            r22.setSubtext(r04.toString());
            r22.setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda20(this, r04, r12));
            r22.setItemHeight(56);
            goto L123
        L121:
            r04 = new StoryPrivacyBottomSheet.StoryPrivacy(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r2), r12.privacy);
        L123:
            addSpeedLayout(r21, false);
            if (this.this$0.isSelf == false) goto L126;
        L127:
            ActionBarPopupWindow.GapView r05 = new ActionBarPopupWindow.GapView(this.this$0.getContext(), this.val$resourcesProvider, Theme.key_actionBarDefaultSubmenuSeparator);
            r05.setTag(R.id.fit_width_tag, 1);
            r21.addView(r05, LayoutHelper.createLinear(-1, 8));
        L128:
            PeerStoriesView r06 = this.this$0;
            if (r06.unsupported == false) goto L131;
        L149:
            StoryItemHolder r07 = this.this$0.currentStory;
            if (r07.storyItem != null) goto L152;
        L160:
            TL_stories.StoryItem r3 = r12;
        L162:
            PeerStoriesView r08 = this.this$0;
            if (r08.isSelf == true) goto L169;
            if (r08.isChannel == true) goto L167;
        L184:
            addViewStatistics(r21, r3);
            PeerStoriesView r09 = this.this$0;
            if (r09.unsupported == true) goto L195;
            if (StoryItemHolder.m19634$$Nest$fgetisLive(r09.currentStory) == true) goto L195;
            if (this.this$0.currentStory.isVideo() == false) goto L192;
            int r010 = R.string.SaveVideo;
        L193:
            ActionBarMenuItem.addItem(r21, R.drawable.msg_gallery, LocaleController.getString(r010), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda31(this));
            goto L195
        L192:
            r010 = R.string.SaveImage;
        L195:
            if (MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).premiumFeaturesBlocked() == false) goto L197;
        L201:
            PeerStoriesView r011 = this.this$0;
            if (r011.isChannel == false) goto L207;
            if (PeerStoriesView.m19453$$Nest$fgetallowShareLink(r011) == false) goto L207;
            ActionBarMenuItem.addItem(r21, R.drawable.msg_link, LocaleController.getString(R.string.CopyLink), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda32(this));
        L207:
            if (PeerStoriesView.m19453$$Nest$fgetallowShareLink(this.this$0) == false) goto L209;
            ActionBarMenuItem.addItem(r21, R.drawable.msg_shareout, LocaleController.getString(R.string.BotShare), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda33(this));
        L209:
            TL_stories.StoryItem r012 = this.this$0.currentStory.storyItem;
            if (r012 == null) goto L232;
            TLRPC.MessageMedia r013 = r012.media;
            if ((r013 instanceof TLRPC.TL_messageMediaVideoStream) == false) goto L232;
            LivePlayer r23 = LivePlayer.recording;
            if (r23 == null) goto L232;
            if (r23.equals(((TLRPC.TL_messageMediaVideoStream) r013).call) == false) goto L232;
            LivePlayer r014 = LivePlayer.recording;
            if (r014 != null) goto L220;
        L222:
            boolean r015 = false;
        L223:
            if (r015 == false) goto L225;
            int r24 = R.drawable.msg_voice_unmuted;
        L226:
            if (r015 == false) goto L229;
            int r32 = R.string.Unmute;
        L230:
            ActionBarMenuItem.addItem(r21, r24, LocaleController.getString(r32), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda34(this, r015));
            ActionBarMenuItem.addItem(r21, R.drawable.menu_camera_retake, LocaleController.getString(R.string.AccDescrSwitchCamera), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda35(this));
            goto L232
        L229:
            r32 = R.string.Mute;
            goto L230
        L225:
            r24 = R.drawable.msg_voice_muted;
            goto L226
        L220:
            if (r014.isMuted() == false) goto L222;
            r015 = true;
        L232:
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == false) goto L235;
            ActionBarMenuItem.addItem(r21, R.drawable.menu_video_pip, LocaleController.getString(R.string.PipMinimize), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda10(this, this.val$storyViewer));
        L235:
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == false) goto L248;
            if (PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0) != UserConfig.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getClientUserId()) goto L239;
        L246:
            ActionBarMenuItem.addItem(r21, R.drawable.msg_settings_old, LocaleController.getString(R.string.LiveStorySettings), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda11(this, this.val$resourcesProvider, this.val$storyViewer));
            goto L248
        L239:
            if (ChatObject.canManageCalls(MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getChat(Long.valueOf(-PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0)))) == true) goto L246;
            LivePlayer r016 = this.val$storyViewer.livePlayer;
            if (r016 == null) goto L248;
            if (this.this$0.currentStory.isThisCall(r016.getCallId()) == false) goto L248;
            if (this.val$storyViewer.livePlayer.isCreator() == true) goto L246;
        L248:
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == false) goto L251;
            ActionBarMenuSubItem r017 = ActionBarMenuItem.addItem(r21, R.drawable.msg_remove, LocaleController.getString(R.string.LiveStoryEnd), false, this.val$resourcesProvider);
            int r33 = Theme.key_text_RedBold;
            r017.setSelectorColor(Theme.multAlpha(Theme.getColor(r33, this.val$resourcesProvider), 0.12f));
            r017.setColors(this.val$resourcesProvider.getColor(r33), this.val$resourcesProvider.getColor(r33));
            r017.setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda12(this, this.val$resourcesProvider, this.val$storyViewer));
        L251:
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == true) goto L257;
            PeerStoriesView r018 = this.this$0;
            if (r018.isSelf == false) goto L255;
        L256:
            ActionBarMenuSubItem r019 = ActionBarMenuItem.addItem(r21, R.drawable.msg_delete, LocaleController.getString(R.string.Delete), false, this.val$resourcesProvider);
            int r34 = Theme.key_text_RedBold;
            r019.setSelectorColor(Theme.multAlpha(Theme.getColor(r34, this.val$resourcesProvider), 0.12f));
            r019.setColors(this.val$resourcesProvider.getColor(r34), this.val$resourcesProvider.getColor(r34));
            r019.setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda13(this));
            goto L257
        L255:
            if (MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r018)).getStoriesController().canDeleteStory(this.this$0.currentStory.storyItem) == true) goto L256;
        L257:
            StoryItemHolder r020 = this.this$0.currentStory;
            if (r020 == null) goto L270;
            TL_stories.StoryItem r021 = r020.storyItem;
            if (r021 == null) goto L270;
            TLRPC.MessageMedia r022 = r021.media;
            if (r022 == null) goto L270;
            if (MessageObject.isDocumentHasAttachedStickers(r022.document) == true) goto L269;
            TLRPC.Photo r023 = this.this$0.currentStory.storyItem.media.photo;
            if (r023 == null) goto L270;
            if (r023.has_stickers == false) goto L270;
        L269:
            boolean r17 = true;
        L271:
            PeerStoriesView r024 = this.this$0;
            ArrayList r18 = PeerStoriesView.m19572$$Nest$mgetAnimatedEmojiSets(r024, r024.currentStory);
            if (r18 != null) goto L274;
        L276:
            r7 = false;
        L277:
            if (r17 == true) goto L281;
            if (r7 == true) goto L281;
            return;
        L281:
            ActionBarPopupWindow.GapView r025 = new ActionBarPopupWindow.GapView(this.val$context, this.val$resourcesProvider, Theme.key_actionBarDefaultSubmenuSeparator);
            r025.setTag(R.id.fit_width_tag, 1);
            r21.addView(r025, LayoutHelper.createLinear(-1, 8));
            TLRPC.MessageMedia r026 = this.this$0.currentStory.storyItem.media;
            TLObject r25 = r026.document;
            if (r25 != null) goto L285;
            r25 = r026.photo;
        L285:
            StoryContainsEmojiButton r122 = new StoryContainsEmojiButton(this.val$context, PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0), r25, this.this$0.currentStory.storyItem, r17, r18, this.val$resourcesProvider);
            r122.setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda27(this, r122));
            r122.setTag(R.id.fit_width_tag, 1);
            r21.addView(r122, LayoutHelper.createLinear(-1, -2));
            return;
        L274:
            if (r18.isEmpty() == true) goto L276;
        L270:
            r17 = false;
            goto L271
        L197:
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == true) goto L201;
            PeerStoriesView r027 = this.this$0;
            if (r027.isChannel == true) goto L201;
            PeerStoriesView.m19565$$Nest$mcreateStealthModeItem(r027, r21);
            goto L201
        L167:
            if (MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r08)).getStoriesController().canEditStories(r3.dialogId) == false) goto L184;
        L169:
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == true) goto L184;
            boolean r028 = r3.pinned;
            boolean r26 = !r028;
            if (this.this$0.isSelf == false) goto L176;
            if (r028 == true) goto L175;
            int r4 = R.string.SaveToProfile;
        L174:
            String r42 = LocaleController.getString(r4);
        L180:
            if (r028 == true) goto L182;
            int r029 = R.drawable.msg_save_story;
        L183:
            ActionBarMenuItem.addItem(r21, r029, r42, false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda30(this, r3, r26, this.val$resourcesProvider));
            goto L184
        L182:
            r029 = R.drawable.menu_unsave_story;
            goto L183
        L175:
            r4 = R.string.ArchiveStory;
            goto L174
        L176:
            if (r028 == true) goto L179;
            int r43 = R.string.SaveToPosts;
        L178:
            r42 = LocaleController.getString(r43);
            goto L180
        L179:
            r43 = R.string.RemoveFromPosts;
            goto L178
        L152:
            if (StoryItemHolder.m19635$$Nest$fgetisVideo(r07) == false) goto L160;
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == true) goto L160;
            PeerStoriesView r030 = this.this$0;
            if (r030.currentStory.storyItem.pinned == false) goto L158;
        L161:
            r3 = r12;
            ActionBarMenuItem.addItem(r21, R.drawable.menu_cover_stories, LocaleController.getString(R.string.StoryEditCoverMenu), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda29(this, this.val$context, r3, this.val$storyViewer, this.val$sharedResources));
            goto L162
        L158:
            if (PeerStoriesView.m19577$$Nest$misEditBotsPreview(r030) == false) goto L160;
        L131:
            if (StoryItemHolder.m19634$$Nest$fgetisLive(r06.currentStory) == true) goto L149;
            if (PeerStoriesView.m19576$$Nest$misBotsPreview(this.this$0) == true) goto L137;
            if (MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).storiesEnabled() == false) goto L149;
        L137:
            if (this.val$userCanEditStory == false) goto L149;
            PeerStoriesView r031 = this.this$0;
            int r27 = R.drawable.msg_edit;
            if (PeerStoriesView.m19576$$Nest$misBotsPreview(r031) == false) goto L141;
            int r35 = R.string.EditBotPreview;
        L142:
            r031.editStoryItem = ActionBarMenuItem.addItem(r21, r27, LocaleController.getString(r35), false, this.val$resourcesProvider);
            this.this$0.editStoryItem.setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda28(this, this.val$resourcesProvider, this.val$context, this.val$storyViewer, this.val$sharedResources));
            PeerStoriesView r032 = this.this$0;
            if (r032.storiesController.hasUploadingStories(PeerStoriesView.m19463$$Nest$fgetdialogId(r032)) == false) goto L149;
            if (StoryItemHolder.m19635$$Nest$fgetisVideo(this.this$0.currentStory) == false) goto L149;
            if (SharedConfig.allowPreparingHevcPlayers() == true) goto L149;
            this.this$0.editStoryItem.setAlpha(0.5f);
            goto L149
        L141:
            r35 = R.string.EditStory;
            goto L142
        L126:
            if (this.val$speedControl == false) goto L128;
        L114:
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == true) goto L117;
            addAlbumsLayout(r21, true);
            goto L117
        L287:
            return;
        L5:
            if (this.this$0.currentStory.uploadingStory != null) goto L104;
            addSpeedLayout(r21, true);
            String r28 = NotificationsController.getSharedPrefKey(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0), 0);
            boolean r033 = NotificationsCustomSettingsActivity.areStoriesNotMuted(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0), PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0));
            long r36 = PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0);
            PeerStoriesView r44 = this.this$0;
            if (r36 <= 0) goto L10;
            TLRPC.Chat r45 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r44)).getUser(Long.valueOf(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0)));
            ?? r14 = r45;
            TLRPC.Chat r15 = null;
        L11:
            if (r14 != 0) goto L15;
            if (r15 != null) goto L14;
            String r37 = _UrlKt.FRAGMENT_ENCODE_SET;
        L16:
            int r5 = r37.indexOf(" ");
            if (r5 <= 0) goto L19;
            r37 = r37.substring(0, r5);
        L19:
            String r52 = r37;
            if (UserObject.isService(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0)) == true) goto L62;
            if (PeerStoriesView.m19576$$Nest$misBotsPreview(this.this$0) == true) goto L62;
            if (r033 == false) goto L25;
            long r16 = 0;
            ActionBarMenuSubItem r123 = ActionBarMenuItem.addItem(r21, R.drawable.msg_mute, LocaleController.getString(R.string.NotificationsStoryMute2), false, this.val$resourcesProvider);
            r123.setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda14(this, r28, this.val$resourcesProvider, r45, r52));
            r123.setMultiline(false);
        L26:
            MediaDataController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).loadHints(true);
            if (r14 != 0) goto L29;
        L33:
            boolean r034 = false;
        L35:
            if (PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0) <= r16) goto L46;
            if (r14 != 0) goto L38;
        L40:
            boolean r29 = false;
        L41:
            if (r14 != 0) goto L43;
        L45:
            boolean r38 = false;
        L55:
            if (r034 == false) goto L57;
            ActionBarMenuItem.addItem(r21, R.drawable.msg_delete, LocaleController.getString(R.string.StoriesRemoveFromRecent), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda16(this));
            goto L62
        L57:
            if (r29 == false) goto L62;
            if (r38 == true) goto L60;
            ActionBarMenuItem.addItem(r21, R.drawable.msg_archive, LocaleController.getString(R.string.ArchivePeerStories), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda17(this));
            goto L62
        L60:
            ActionBarMenuItem.addItem(r21, R.drawable.msg_unarchive, LocaleController.getString(R.string.UnarchiveStories), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda18(this));
            goto L62
        L43:
            if (r14.stories_hidden == false) goto L45;
        L44:
            r38 = true;
            goto L55
        L38:
            if (r14.contact == false) goto L40;
            r29 = true;
            goto L41
        L46:
            if (r15 != null) goto L48;
        L50:
            r29 = false;
        L51:
            if (r15 == null) goto L45;
            if (r15.stories_hidden == false) goto L45;
        L48:
            if (ChatObject.isNotInChat(r15) == true) goto L50;
            r29 = true;
            goto L51
        L29:
            if (r14.contact == true) goto L33;
            if (MediaDataController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).containsTopPeer(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0)) == false) goto L33;
            r034 = true;
            goto L35
        L25:
            r16 = 0;
            ActionBarMenuSubItem r124 = ActionBarMenuItem.addItem(r21, R.drawable.msg_unmute, LocaleController.getString(R.string.NotificationsStoryUnmute2), false, this.val$resourcesProvider);
            r124.setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda15(this, r28, this.val$resourcesProvider, r45, r52));
            r124.setMultiline(false);
        L62:
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == false) goto L65;
            ActionBarMenuItem.addItem(r21, R.drawable.menu_video_pip, LocaleController.getString(R.string.PipMinimize), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda19(this, this.val$storyViewer));
        L65:
            if (MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).premiumFeaturesBlocked() == false) goto L67;
        L69:
            PeerStoriesView r035 = this.this$0;
            if (r035.unsupported == true) goto L77;
            if (PeerStoriesView.m19452$$Nest$fgetallowShare(r035) == false) goto L77;
            if (StoryItemHolder.m19634$$Nest$fgetisLive(this.this$0.currentStory) == true) goto L77;
            UserConfig.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).isPremium();
            ActionBarMenuItem.addItem(r21, R.drawable.msg_gallery, LocaleController.getString(R.string.SaveToGallery), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda21(this));
        L77:
            if (MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).premiumFeaturesBlocked() == true) goto L82;
            PeerStoriesView r036 = this.this$0;
            if (r036.isChannel == true) goto L82;
            PeerStoriesView.m19565$$Nest$mcreateStealthModeItem(r036, r21);
        L82:
            if (PeerStoriesView.m19453$$Nest$fgetallowShareLink(this.this$0) == false) goto L85;
            ActionBarMenuItem.addItem(r21, R.drawable.msg_link2, LocaleController.getString(R.string.CopyLink), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda22(this));
        L85:
            if (PeerStoriesView.m19453$$Nest$fgetallowShareLink(this.this$0) == false) goto L87;
            ActionBarMenuItem.addItem(r21, R.drawable.msg_shareout, LocaleController.getString(R.string.BotShare), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda23(this));
        L87:
            TL_stories.StoryItem r037 = this.this$0.currentStory.storyItem;
            if (r037 != null) goto L90;
        L97:
            addViewStatistics(r21, this.this$0.currentStory.storyItem);
            PeerStoriesView r038 = this.this$0;
            if (r038.unsupported == true) goto L257;
            if (UserObject.isService(PeerStoriesView.m19463$$Nest$fgetdialogId(r038)) == true) goto L257;
            if (PeerStoriesView.m19576$$Nest$misBotsPreview(this.this$0) == true) goto L257;
            ActionBarMenuItem.addItem(r21, R.drawable.msg_report, LocaleController.getString(R.string.ReportChat), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda26(this, this.val$storyViewer, this.val$resourcesProvider));
            goto L257
        L90:
            if (r037.translated == false) goto L95;
            if (TextUtils.equals(r037.translatedLng, TranslateAlert2.getToLanguage()) == false) goto L95;
            ActionBarMenuItem.addItem(r21, R.drawable.msg_translate, LocaleController.getString(R.string.HideTranslation), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda24(this));
        L95:
            if (MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getTranslateController().canTranslateStory(this.this$0.currentStory.storyItem) == false) goto L97;
            ActionBarMenuItem.addItem(r21, R.drawable.msg_translate, LocaleController.getString(R.string.TranslateMessage), false, this.val$resourcesProvider).setOnClickListener(new PeerStoriesView$8$$ExternalSyntheticLambda25(this));
            goto L97
        L67:
            if (StoryItemHolder.m19635$$Nest$fgetisVideo(this.this$0.currentStory) == false) goto L69;
            PeerStoriesView.m19564$$Nest$mcreateQualityItem(this.this$0, r21);
            goto L69
        L14:
            r37 = r15.title;
            goto L16
        L15:
            r37 = UserObject.getFirstName(r14).trim();
            goto L16
        L10:
            r45 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r44)).getChat(Long.valueOf(-PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0)));
            r15 = r45;
            r14 = 0;
            goto L11
        }

        @Override // org.telegram.ui.Components.CustomPopupMenu
        public void onDismissed() {
            if (this.edit == false) goto L5;
        L7:
            PeerStoriesView r2 = this.this$0;
            r2.popupMenu = null;
            r2.editStoryItem = null;
            return;
        L5:
            if (this.val$popupStillVisible[0] == true) goto L7;
            AndroidUtilities.runOnUIThread(new PeerStoriesView$8$$ExternalSyntheticLambda36(this));
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Stories.PeerStoriesView$9, reason: invalid class name */
    public class AnonymousClass9 extends FrameLayout {
        final /* synthetic */ PeerStoriesView this$0;

        public AnonymousClass9(PeerStoriesView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return super.dispatchTouchEvent(r1);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r1) {
            return super.onTouchEvent(r1);
        }
    }

    public interface Delegate {
        int getKeyboardHeight();

        float getProgressToDismiss();

        boolean isClosed();

        void onPeerSelected(long r1, int r3);

        void preparePlayer(ArrayList<TLRPC.Document> r1, ArrayList<Uri> r2);

        boolean releasePlayer(Runnable r1);

        void requestAdjust(boolean r1);

        void requestPlayer(TLRPC.Document r1, Uri r2, long r3, VideoPlayerSharedScope r5);

        void requestPlayer(TL_stories.StoryItem r1, long r2, int r4, boolean r5, TLRPC.InputGroupCall r6, VideoPlayerSharedScope r7);

        void setAllowTouchesByViewPager(boolean r1);

        void setBulletinIsVisible(boolean r1);

        void setHideEnterViewProgress(float r1);

        void setIsCaption(boolean r1);

        void setIsCaptionPartVisible(boolean r1);

        void setIsHintVisible(boolean r1);

        void setIsInPinchToZoom(boolean r1);

        void setIsInSelectionMode(boolean r1);

        void setIsLikesReaction(boolean r1);

        void setIsRecording(boolean r1);

        void setIsSwiping(boolean r1);

        void setIsWaiting(boolean r1);

        void setKeyboardVisible(boolean r1);

        void setPopupIsVisible(boolean r1);

        void setTranslating(boolean r1);

        void shouldSwitchToNext();

        void showDialog(Dialog r1);

        void switchToNextAndRemoveCurrentPeer();
    }

    public static class PeerHeaderView extends FrameLayout {
        public BackupImageView backupImageView;
        private float progressToUploading;
        RadialProgress radialProgress;
        Paint radialProgressPaint;
        StoryItemHolder storyItemHolder;
        private ValueAnimator subtitleAnimator;
        private TextView[] subtitleView;
        public SimpleTextView titleView;
        private boolean uploadedTooFast;
        private boolean uploading;

        /* renamed from: org.telegram.ui.Stories.PeerStoriesView$PeerHeaderView$1, reason: invalid class name */
        public class AnonymousClass1 extends BackupImageView {
            final /* synthetic */ PeerHeaderView this$0;

            public AnonymousClass1(PeerHeaderView r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.BackupImageView, android.view.View
            public void onDraw(Canvas r5) {
                if (this.imageReceiver.getVisible() == false) goto L5;
                RectF r0 = AndroidUtilities.rectTmp;
                r0.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
                this.this$0.drawUploadingProgress(r5, r0, true, 1.0f);
            L5:
                super.onDraw(r5);
            }
        }

        /* renamed from: org.telegram.ui.Stories.PeerStoriesView$PeerHeaderView$2, reason: invalid class name */
        public class AnonymousClass2 extends SimpleTextView {
            final /* synthetic */ PeerHeaderView this$0;

            public AnonymousClass2(PeerHeaderView r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.ActionBar.SimpleTextView, android.view.View
            public void onMeasure(int r1, int r2) {
                super.onMeasure(r1, r2);
                setPivotY(getMeasuredHeight() / 2.0f);
            }
        }

        /* renamed from: org.telegram.ui.Stories.PeerStoriesView$PeerHeaderView$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ PeerHeaderView this$0;

            public AnonymousClass3(PeerHeaderView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                PeerHeaderView.m19624$$Nest$fgetsubtitleView(this.this$0)[1].setVisibility(8);
                PeerHeaderView.m19624$$Nest$fgetsubtitleView(this.this$0)[0].setAlpha(1.0f);
                PeerHeaderView.m19624$$Nest$fgetsubtitleView(this.this$0)[0].setTranslationY(0.0f);
            }
        }

        public static /* synthetic */ void $r8$lambda$7PZf6mxRG8E0CydR3rEtKqAY_7E(PeerHeaderView r0, ValueAnimator r1) {
            r0.lambda$setSubtitle$0(r1);
        }

        /* renamed from: -$$Nest$fgetsubtitleView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView[] m19624$$Nest$fgetsubtitleView(PeerHeaderView r0) {
            return r0.subtitleView;
        }

        /* renamed from: -$$Nest$fputprogressToUploading, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19625$$Nest$fputprogressToUploading(PeerHeaderView r0, float r1) {
            r0.progressToUploading = r1;
        }

        public PeerHeaderView(Context r12, StoryItemHolder r13) {
            super(r12);
            this.subtitleView = new TextView[2];
            this.storyItemHolder = r13;
            AnonymousClass1 r132 = new AnonymousClass1(this, r12);
            this.backupImageView = r132;
            r132.setRoundRadius(ExteraConfig.getAvatarCorners(32.0f));
            addView(this.backupImageView, LayoutHelper.createFrame(32, 32.0f, 0, 12.0f, 2.0f, 0.0f, 0.0f));
            setClipChildren(false);
            AnonymousClass2 r1 = new AnonymousClass2(this, r12);
            this.titleView = r1;
            r1.setTextSize(14);
            this.titleView.setTypeface(AndroidUtilities.bold());
            this.titleView.setMaxLines(1);
            this.titleView.setEllipsizeByGradient(AndroidUtilities.dp(4.0f));
            this.titleView.setPivotX(0.0f);
            NotificationCenter.listenEmojiLoading(this.titleView);
            addView(this.titleView, LayoutHelper.createFrame(-2, -2.0f, 0, 54.0f, 0.0f, 86.0f, 0.0f));
            int r14 = 0;
        L4:
            if (r14 >= 2) goto L6;
            this.subtitleView[r14] = new TextView(r12);
            this.subtitleView[r14].setTextSize(1, 12.0f);
            this.subtitleView[r14].setMaxLines(1);
            this.subtitleView[r14].setSingleLine(true);
            this.subtitleView[r14].setEllipsize(TextUtils.TruncateAt.MIDDLE);
            this.subtitleView[r14].setTextColor(-1);
            this.subtitleView[r14].setPadding(AndroidUtilities.dp(3.0f), 0, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(1.0f));
            addView(this.subtitleView[r14], LayoutHelper.createFrame(-2, -2.0f, 0, 51.0f, 18.0f, 83.0f, 0.0f));
            r14 = r14 + 1;
            goto L4
        L6:
            this.titleView.setTextColor(-1);
        }

        private /* synthetic */ void lambda$setSubtitle$0(ValueAnimator r5) {
            float r52 = ((Float) r5.getAnimatedValue()).floatValue();
            this.subtitleView[0].setAlpha(r52);
            float r1 = 1.0f - r52;
            this.subtitleView[0].setTranslationY((-AndroidUtilities.dp(4.0f)) * r1);
            this.subtitleView[1].setAlpha(r1);
            this.subtitleView[1].setTranslationY(r52 * AndroidUtilities.dp(4.0f));
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (isEnabled() == true) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r2);
        }

        public void drawUploadingProgress(Canvas r18, RectF r19, boolean r20, float r21) {
            StoryItemHolder r3 = this.storyItemHolder;
            if (r3 == null) goto L7;
            if (r3.uploadingStory == null) goto L7;
        L9:
            if (r3 == null) goto L19;
            StoriesController.UploadingStory r32 = r3.uploadingStory;
            if (r32 == null) goto L19;
            if (r32.failed == true) goto L19;
            this.progressToUploading = 1.0f;
            float r33 = r32.progress;
            if (this.uploading == true) goto L17;
            this.uploading = true;
        L17:
            boolean r10 = false;
        L30:
            if (this.radialProgress != null) goto L32;
            RadialProgress r11 = new RadialProgress(this.backupImageView);
            this.radialProgress = r11;
            r11.setBackground(null, true, false);
        L32:
            this.radialProgress.setDiff(0);
            ImageReceiver r112 = this.backupImageView.getImageReceiver();
            float r12 = AndroidUtilities.dp(3.0f) - (AndroidUtilities.dp(6.0f) * (1.0f - this.progressToUploading));
            this.radialProgress.setProgressRect((int) (r19.left - r12), (int) (r19.top - r12), (int) (r19.right + r12), (int) (r19.bottom + r12));
            RadialProgress r2 = this.radialProgress;
            if (r10 == false) goto L35;
            float r34 = 1.0f;
        L36:
            r2.setProgress(r34, true);
            if (this.uploadedTooFast == false) goto L42;
            if (r10 == false) goto L42;
            if (this.radialProgress.getAnimatedProgress() < 0.9f) goto L42;
            this.progressToUploading = Utilities.clamp(this.progressToUploading - ((1000.0f / AndroidUtilities.screenRefreshRate) / 300.0f), 1.0f, 0.0f);
        L42:
            if (r20 == true) goto L44;
            return;
        L44:
            if (r21 == 1.0f) goto L47;
            Paint r22 = StoriesUtilities.getUnreadCirclePaint(r112, false);
            r22.setAlpha((int) (this.progressToUploading * 255.0f));
            this.radialProgress.setPaint(r22);
            this.radialProgress.draw(r18);
        L47:
            if (this.radialProgressPaint != null) goto L49;
            Paint r23 = new Paint(1);
            this.radialProgressPaint = r23;
            r23.setColor(-1);
            this.radialProgressPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
            this.radialProgressPaint.setStyle(Paint.Style.STROKE);
            this.radialProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        L49:
            this.radialProgressPaint.setAlpha((int) ((255.0f * r21) * this.progressToUploading));
            this.radialProgress.setPaint(this.radialProgressPaint);
            this.radialProgress.draw(r18);
            return;
        L35:
            r34 = Utilities.clamp(r33, 1.0f, 0.0f);
        L19:
            if (this.uploading == false) goto L26;
            this.uploading = false;
            if (this.radialProgress.getAnimatedProgress() >= 0.2f) goto L23;
            boolean r35 = true;
        L24:
            this.uploadedTooFast = r35;
            goto L26
        L23:
            r35 = false;
        L26:
            if (this.uploadedTooFast == true) goto L28;
            this.progressToUploading = Utilities.clamp(this.progressToUploading - ((1000.0f / AndroidUtilities.screenRefreshRate) / 300.0f), 1.0f, 0.0f);
        L28:
            r10 = true;
            r33 = 1.0f;
        L7:
            if (this.progressToUploading != 0.0f) goto L9;
        }

        public void setOnSubtitleClick(View.OnClickListener r4) {
            this.subtitleView[0].setOnClickListener(r4);
            TextView r0 = this.subtitleView[0];
            if (r4 == null) goto L5;
            boolean r2 = true;
        L6:
            r0.setClickable(r2);
            TextView r3 = this.subtitleView[0];
            if (r4 != null) goto L9;
            Drawable r42 = null;
        L10:
            r3.setBackground(r42);
            return;
        L9:
            r42 = Theme.createSelectorDrawable(822083583, 7);
            goto L10
        L5:
            r2 = false;
            goto L6
        }

        public void setSubtitle(CharSequence r2) {
            setSubtitle(r2, false);
        }

        public void setSubtitle(CharSequence r7, boolean r8) {
            ValueAnimator r0 = this.subtitleAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
            this.subtitleAnimator = null;
        L5:
            TextView[] r02 = this.subtitleView;
            if (r8 == false) goto L9;
            r02[1].setOnClickListener(null);
            TextView[] r82 = this.subtitleView;
            r82[1].setText(r82[0].getText());
            this.subtitleView[1].setVisibility(0);
            this.subtitleView[1].setAlpha(1.0f);
            this.subtitleView[1].setTranslationY(0.0f);
            this.subtitleView[0].setText(r7);
            this.subtitleView[0].setVisibility(0);
            this.subtitleView[0].setAlpha(0.0f);
            this.subtitleView[0].setTranslationY(-AndroidUtilities.dp(4.0f));
            ValueAnimator r72 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.subtitleAnimator = r72;
            r72.addUpdateListener(new PeerStoriesView$PeerHeaderView$$ExternalSyntheticLambda0(this));
            this.subtitleAnimator.addListener(new AnonymousClass3(this));
            this.subtitleAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.subtitleAnimator.setDuration(340);
            this.subtitleAnimator.start();
            return;
        L9:
            r02[0].setVisibility(0);
            this.subtitleView[0].setAlpha(1.0f);
            this.subtitleView[0].setText(r7);
            this.subtitleView[1].setVisibility(8);
            this.subtitleView[1].setAlpha(0.0f);
        }
    }

    public static class SharedResources {
        public final Paint barPaint;
        public final BitmapShaderTools bitmapShaderTools;
        private final RectF borderRect;
        private final Drawable bottomOverlayGradient;
        public Drawable deleteDrawable;
        private final Paint dimPaint;
        private final RectF finalRect;
        private final Paint gradientBackgroundPaint;
        public final Drawable imageBackgroundDrawable;
        public Drawable likeDrawable;
        public Drawable likeDrawableFilled;
        public RLottieDrawable muteDrawable;
        public RLottieDrawable noSoundDrawable;
        public Drawable optionsDrawable;
        public Drawable pipDrawable;
        private final RectF popupRect;
        private final RectF rect1;
        private final RectF rect2;
        public Drawable repostDrawable;
        public final Paint selectedBarPaint;
        public Drawable shareDrawable;
        private final Drawable topOverlayGradient;

        /* renamed from: -$$Nest$fgetbottomOverlayGradient, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m19626$$Nest$fgetbottomOverlayGradient(SharedResources r0) {
            return r0.bottomOverlayGradient;
        }

        /* renamed from: -$$Nest$fgetdimPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m19627$$Nest$fgetdimPaint(SharedResources r0) {
            return r0.dimPaint;
        }

        /* renamed from: -$$Nest$fgetfinalRect, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m19628$$Nest$fgetfinalRect(SharedResources r0) {
            return r0.finalRect;
        }

        /* renamed from: -$$Nest$fgetgradientBackgroundPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m19629$$Nest$fgetgradientBackgroundPaint(SharedResources r0) {
            return r0.gradientBackgroundPaint;
        }

        /* renamed from: -$$Nest$fgetpopupRect, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m19630$$Nest$fgetpopupRect(SharedResources r0) {
            return r0.popupRect;
        }

        /* renamed from: -$$Nest$fgetrect1, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m19631$$Nest$fgetrect1(SharedResources r0) {
            return r0.rect1;
        }

        /* renamed from: -$$Nest$fgetrect2, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m19632$$Nest$fgetrect2(SharedResources r0) {
            return r0.rect2;
        }

        /* renamed from: -$$Nest$fgettopOverlayGradient, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m19633$$Nest$fgettopOverlayGradient(SharedResources r0) {
            return r0.topOverlayGradient;
        }

        public SharedResources(Context r9) {
            this.bitmapShaderTools = new BitmapShaderTools();
            this.rect1 = new RectF();
            this.rect2 = new RectF();
            this.finalRect = new RectF();
            this.borderRect = new RectF();
            this.popupRect = new RectF();
            this.dimPaint = new Paint();
            this.shareDrawable = ContextCompat.getDrawable(r9, R.drawable.media_share);
            this.likeDrawable = ContextCompat.getDrawable(r9, R.drawable.media_like);
            this.repostDrawable = ContextCompat.getDrawable(r9, R.drawable.media_repost);
            Drawable r0 = ContextCompat.getDrawable(r9, R.drawable.media_like_active);
            this.likeDrawableFilled = r0;
            r0.setColorFilter(new PorterDuffColorFilter(-53704, PorterDuff.Mode.MULTIPLY));
            this.optionsDrawable = ContextCompat.getDrawable(r9, R.drawable.media_more);
            this.pipDrawable = ContextCompat.getDrawable(r9, R.drawable.menu_stream_pip);
            this.deleteDrawable = ContextCompat.getDrawable(r9, R.drawable.msg_delete);
            this.muteDrawable = new RLottieDrawable(R.raw.media_mute_unmute, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), true, null);
            RLottieDrawable r2 = new RLottieDrawable(R.raw.media_mute_unmute, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), true, null);
            this.noSoundDrawable = r2;
            r2.setCurrentFrame(20, false, true);
            this.noSoundDrawable.stop();
            Paint r02 = new Paint(1);
            this.barPaint = r02;
            r02.setColor(1442840575);
            Paint r03 = new Paint(1);
            this.selectedBarPaint = r03;
            r03.setColor(-1);
            int r04 = ColorUtils.setAlphaComponent(-16777216, 102);
            this.topOverlayGradient = ContextCompat.getDrawable(r9, R.drawable.shadow_story_top);
            this.bottomOverlayGradient = ContextCompat.getDrawable(r9, R.drawable.shadow_story_bottom);
            Paint r92 = new Paint();
            this.gradientBackgroundPaint = r92;
            r92.setColor(r04);
            this.imageBackgroundDrawable = new ColorDrawable(ColorUtils.blendARGB(-16777216, -1, 0.1f));
        }

        public void setIconMuted(boolean r4, boolean r5) {
            int r0 = 20;
            if (r5 == true) goto L14;
            RLottieDrawable r52 = this.muteDrawable;
            if (r4 == false) goto L7;
            int r2 = 20;
        L8:
            r52.setCurrentFrame(r2, false);
            RLottieDrawable r3 = this.muteDrawable;
            if (r4 == true) goto L12;
            r0 = 0;
        L12:
            r3.setCustomEndFrame(r0);
            return;
        L7:
            r2 = 0;
            goto L8
        L14:
            RLottieDrawable r53 = this.muteDrawable;
            if (r4 == false) goto L22;
            if (r53.getCurrentFrame() <= 20) goto L19;
            this.muteDrawable.setCurrentFrame(0, false);
        L19:
            this.muteDrawable.setCustomEndFrame(20);
            this.muteDrawable.start();
            return;
        L22:
            if (r53.getCurrentFrame() != 0) goto L24;
            return;
        L24:
            if (this.muteDrawable.getCurrentFrame() >= 43) goto L29;
            this.muteDrawable.setCustomEndFrame(43);
            this.muteDrawable.start();
            return;
        }
    }

    public class StoryItemHolder {
        public CharSequence caption;
        public boolean captionTranslated;
        public TL_stories.StoryItem editingSourceItem;
        private boolean isLive;
        private boolean isVideo;
        private StoryCaptionView.Panel musicPanel;
        private StoryCaptionView.Panel panel;
        boolean skipped;
        public TL_stories.StoryItem storyItem;
        final /* synthetic */ PeerStoriesView this$0;
        public StoriesController.UploadingStory uploadingStory;

        /* renamed from: -$$Nest$fgetisLive, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19634$$Nest$fgetisLive(StoryItemHolder r0) {
            return r0.isLive;
        }

        /* renamed from: -$$Nest$fgetisVideo, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m19635$$Nest$fgetisVideo(StoryItemHolder r0) {
            return r0.isVideo;
        }

        /* renamed from: -$$Nest$mgetMediaDebugString, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m19636$$Nest$mgetMediaDebugString(StoryItemHolder r0) {
            return r0.getMediaDebugString();
        }

        public StoryItemHolder(PeerStoriesView r1) {
            this.this$0 = r1;
            this.storyItem = null;
            this.uploadingStory = null;
        }

        private String getMediaDebugString() {
            TL_stories.StoryItem r0 = this.storyItem;
            if (r0 == null) goto L15;
            TLRPC.MessageMedia r02 = r0.media;
            if (r02 == null) goto L15;
            if (r02.photo == null) goto L11;
            return "photo#" + this.storyItem.media.photo.id + "at" + this.storyItem.media.photo.dc_id + "dc";
        L11:
            if (r02.document != null) goto L13;
            return "unknown";
        L13:
            return "doc#" + this.storyItem.media.document.id + "at" + this.storyItem.media.document.dc_id + "dc";
        L15:
            if (this.uploadingStory != null) goto L17;
            return "unknown";
        L17:
            return "uploading from " + this.uploadingStory.path;
        }

        private boolean isLiveInternal() {
            TL_stories.StoryItem r0 = this.storyItem;
            if (r0 == null) goto L10;
            TLRPC.MessageMedia r02 = r0.media;
            if (r02 != null) goto L7;
            return false;
        L7:
            if ((r02 instanceof TLRPC.TL_messageMediaVideoStream) == false) goto L13;
            return true;
        L13:
            return false;
        L10:
            return false;
        }

        private boolean isVideoInternal() {
            StoriesController.UploadingStory r0 = this.uploadingStory;
            if (r0 != null) goto L5;
            TL_stories.StoryItem r02 = this.storyItem;
            if (r02 == null) goto L19;
            TLRPC.MessageMedia r03 = r02.media;
            if (r03 == null) goto L19;
            if (r03.getDocument() == null) goto L19;
            TLRPC.Document r3 = this.storyItem.media.getDocument();
            if (MessageObject.isVideoDocument(r3) == false) goto L16;
            return true;
        L16:
            if ("video/mp4".equals(r3.mime_type) == false) goto L18;
            return true;
        L18:
            return false;
        L19:
            TL_stories.StoryItem r32 = this.storyItem;
            if (r32 != null) goto L22;
        L27:
            return false;
        L22:
            if (r32.media != null) goto L27;
            String r33 = r32.attachPath;
            if (r33 == null) goto L27;
            return r33.toLowerCase().endsWith(".mp4");
        L5:
            return r0.isVideo;
        }

        public boolean allowScreenshots() {
            StoriesController.UploadingStory r0 = this.uploadingStory;
            if (r0 != null) goto L5;
            TL_stories.StoryItem r02 = this.storyItem;
            if (r02 != null) goto L9;
            return true;
        L9:
            if (r02.noforwards == false) goto L12;
            return false;
        L12:
            if (r02.pinned == false) goto L20;
            long r03 = r02.dialogId;
            TLRPC.Chat r3 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getChat(Long.valueOf(-r03));
            if (r3 != null) goto L16;
            return true;
        L16:
            if (r3.noforwards == false) goto L22;
            return false;
        L22:
            return true;
        L20:
            return true;
        L5:
            return r0.entry.allowScreenshots;
        }

        public void cancelOrDelete() {
            TL_stories.StoryItem r0 = this.storyItem;
            if ((r0 instanceof StoriesController.BotPreview) == false) goto L6;
            ((StoriesController.BotPreview) r0).list.delete(r0.media);
            return;
        L6:
            if (r0 == null) goto L9;
            PeerStoriesView r02 = this.this$0;
            r02.storiesController.deleteStory(PeerStoriesView.m19463$$Nest$fgetdialogId(r02), this.storyItem);
            return;
        L9:
            StoriesController.UploadingStory r4 = this.uploadingStory;
            if (r4 == null) goto L13;
            r4.cancel();
            return;
        }

        public void checkSendView() {
            PeerStoriesView r0 = this.this$0;
            TL_stories.PeerStories r1 = r0.userStories;
            if (r1 != null) goto L10;
            r1 = r0.storiesController.getStories(PeerStoriesView.m19463$$Nest$fgetdialogId(r0));
            if (r1 != null) goto L10;
            TLRPC.UserFull r02 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getUserFull(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0));
            if (r02 == null) goto L10;
            r1 = r02.stories;
        L10:
            if (this.this$0.isActive == false) goto L32;
            TL_stories.StoryItem r03 = this.storyItem;
            if (r03 == null) goto L32;
            if (r1 == null) goto L32;
            if (StoriesUtilities.hasExpiredViews(r03) == true) goto L21;
            int r04 = this.storyItem.id;
            if (r04 > r1.max_read_id) goto L22;
            PeerStoriesView r12 = this.this$0;
            if (r04 <= r12.storiesController.dialogIdToMaxReadId.get(PeerStoriesView.m19463$$Nest$fgetdialogId(r12), 0)) goto L21;
        L22:
            TL_stories.PeerStories r05 = PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).overrideUserStories;
            PeerStoriesView r13 = this.this$0;
            if (r05 == null) goto L29;
            if (r13.storiesController.markStoryAsRead(PeerStoriesView.m19524$$Nest$fgetstoryViewer(r13).overrideUserStories, this.storyItem, true) == false) goto L42;
            PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).unreadStateChanged = true;
            return;
        L42:
            return;
        L29:
            if (r13.storiesController.markStoryAsRead(PeerStoriesView.m19463$$Nest$fgetdialogId(r13), this.storyItem) == false) goto L43;
            PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).unreadStateChanged = true;
            return;
        L43:
            return;
        L21:
            if (this.this$0.isSelf == true) goto L22;
        L32:
            PeerStoriesView r06 = this.this$0;
            if (r06.isActive == true) goto L35;
            return;
        L35:
            if (this.storyItem != null) goto L37;
            return;
        L37:
            if (PeerStoriesView.m19524$$Nest$fgetstoryViewer(r06).storiesList != null) goto L39;
            return;
        L39:
            if (PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).storiesList.markAsRead(this.storyItem.id) == false) goto L47;
            PeerStoriesView.m19524$$Nest$fgetstoryViewer(this.this$0).unreadStateChanged = true;
            return;
        }

        public void clear() {
            this.uploadingStory = null;
            this.storyItem = null;
        }

        public String createLink() {
            PeerStoriesView r0 = this.this$0;
            if (r0.currentStory.storyItem != null) goto L5;
            return null;
        L5:
            long r02 = PeerStoriesView.m19463$$Nest$fgetdialogId(r0);
            PeerStoriesView r1 = this.this$0;
            if (r02 <= 0) goto L16;
            TLRPC.User r03 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r1)).getUser(Long.valueOf(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0)));
            if (UserObject.getPublicUsername(r03) != null) goto L11;
            return null;
        L11:
            if (this.this$0.currentStory.isLive == false) goto L15;
            return String.format(Locale.US, "https://t.me/%1$s/s/live", new Object[]{UserObject.getPublicUsername(r03)});
        L15:
            return String.format(Locale.US, "https://t.me/%1$s/s/%2$s", new Object[]{UserObject.getPublicUsername(r03), Integer.valueOf(this.this$0.currentStory.storyItem.id)});
        L16:
            TLRPC.Chat r04 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(r1)).getChat(Long.valueOf(-PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0)));
            if (ChatObject.getPublicUsername(r04) != null) goto L20;
            return null;
        L20:
            if (this.this$0.currentStory.isLive == false) goto L24;
            return String.format(Locale.US, "https://t.me/%1$s/s/live", new Object[]{ChatObject.getPublicUsername(r04)});
        L24:
            return String.format(Locale.US, "https://t.me/%1$s/s/%2$s", new Object[]{ChatObject.getPublicUsername(r04), Integer.valueOf(this.this$0.currentStory.storyItem.id)});
        }

        public String getLocalPath() {
            TL_stories.StoryItem r0 = this.storyItem;
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.attachPath;
        }

        public StoryCaptionView.Panel getMusic() {
            if (this.musicPanel != null) goto L8;
            TL_stories.StoryItem r0 = this.storyItem;
            if (r0 == null) goto L8;
            this.musicPanel = StoryCaptionView.Panel.from(r0.music);
        L8:
            return this.musicPanel;
        }

        public File getPath() {
            if (getLocalPath() != null) goto L5;
            TL_stories.StoryItem r0 = this.storyItem;
            if (r0 == null) goto L23;
            TLRPC.MessageMedia r02 = r0.media;
            if (r02 != null) goto L11;
        L14:
            TLRPC.MessageMedia r03 = this.storyItem.media;
            if (r03 == null) goto L25;
            TLRPC.Photo r04 = r03.photo;
            if (r04 == null) goto L26;
            TLRPC.PhotoSize r05 = FileLoader.getClosestPhotoSizeWithSize(r04.sizes, Integer.MAX_VALUE);
            File r1 = FileLoader.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getPathToAttach(r05, true);
            if (r1.exists() == false) goto L21;
            return r1;
        L21:
            return FileLoader.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getPathToAttach(r05, false);
        L26:
            return null;
        L25:
            return null;
        L11:
            if (r02.getDocument() == null) goto L14;
            return FileLoader.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getPathToAttach(this.storyItem.media.getDocument());
        L23:
            return null;
        L5:
            return new File(getLocalPath());
        }

        public StoryCaptionView.Panel getReply() {
            if (this.panel != null) goto L11;
            if (this.storyItem == null) goto L7;
            this.panel = StoryCaptionView.Panel.from(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0), this.storyItem);
            goto L11
        L7:
            StoriesController.UploadingStory r0 = this.uploadingStory;
            if (r0 == null) goto L11;
            this.panel = StoryCaptionView.Panel.from(r0);
        L11:
            return this.panel;
        }

        public boolean hasSound() {
            if (this.isVideo == true) goto L5;
            return false;
        L5:
            TL_stories.StoryItem r0 = this.storyItem;
            if (r0 == null) goto L22;
            TLRPC.MessageMedia r02 = r0.media;
            if (r02 == null) goto L22;
            TLRPC.Document r03 = r02.getDocument();
            if (r03 == null) goto L22;
            int r5 = 0;
        L13:
            if (r5 >= r03.attributes.size()) goto L20;
            TLRPC.DocumentAttribute r3 = r03.attributes.get(r5);
            if ((r3 instanceof TLRPC.TL_documentAttributeVideo) == false) goto L19;
            if (r3.nosound == false) goto L19;
            return false;
        L19:
            r5 = r5 + 1;
            goto L13
        L20:
            return true;
        L22:
            if (this.uploadingStory != null) goto L24;
            return true;
        L24:
            return !r5.entry.muted;
        }

        public boolean isLive() {
            return this.isLive;
        }

        public boolean isThisCall(long r3) {
            TL_stories.StoryItem r2 = this.storyItem;
            if (r2 == null) goto L10;
            TLRPC.MessageMedia r22 = r2.media;
            if ((r22 instanceof TLRPC.TL_messageMediaVideoStream) == true) goto L7;
            return false;
        L7:
            if (r3 != ((TLRPC.TL_messageMediaVideoStream) r22).call.id) goto L13;
            return true;
        L13:
            return false;
        L10:
            return false;
        }

        public boolean isVideo() {
            return this.isVideo;
        }

        public void set(TL_stories.StoryItem r2) {
            this.storyItem = r2;
            this.panel = null;
            this.musicPanel = null;
            this.uploadingStory = null;
            this.skipped = r2 instanceof TL_stories.TL_storyItemSkipped;
            this.isVideo = isVideoInternal();
            this.isLive = isLiveInternal();
        }

        public void set(StoriesController.UploadingStory r1) {
            this.uploadingStory = r1;
            this.panel = null;
            this.musicPanel = null;
            this.storyItem = null;
            this.skipped = false;
            this.isVideo = isVideoInternal();
            this.isLive = isLiveInternal();
        }

        public void updateCaption() {
            int r0 = 0;
            this.captionTranslated = false;
            PeerStoriesView r1 = this.this$0;
            StoryItemHolder r2 = r1.currentStory;
            StoriesController.UploadingStory r3 = r2.uploadingStory;
            if (r3 == null) goto L14;
            CharSequence r22 = r3.entry.caption;
            this.caption = r22;
            CharSequence r02 = Emoji.replaceEmoji(r22, PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(r1).captionTextview.getPaint().getFontMetricsInt(), false);
            this.caption = r02;
            if (r02 != null) goto L7;
            SpannableStringBuilder r03 = new SpannableStringBuilder();
        L8:
            TLRPC.User r12 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getUser(Long.valueOf(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0)));
            if (PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0) >= 0) goto L11;
        L12:
            MessageObject.addLinks(true, r03);
            return;
        L11:
            if (MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).storyEntitiesAllowed(r12) == true) goto L12;
            return;
        L7:
            r03 = SpannableStringBuilder.valueOf(r02);
            goto L8
        L14:
            TL_stories.StoryItem r13 = r2.storyItem;
            if (r13 != null) goto L17;
            return;
        L17:
            if (r13.translated == true) goto L19;
        L35:
            PeerStoriesView r14 = this.this$0;
            String r23 = r14.currentStory.storyItem.caption;
            this.caption = r23;
            CharSequence r15 = Emoji.replaceEmoji(r23, PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(r14).captionTextview.getPaint().getFontMetricsInt(), false);
            this.caption = r15;
            if (r15 != null) goto L38;
            return;
        L38:
            if (this.this$0.currentStory.storyItem.entities == null) goto L53;
            SpannableStringBuilder r16 = new SpannableStringBuilder(this.this$0.currentStory.storyItem.caption);
            PeerStoriesView r24 = this.this$0;
            SpannableStringBuilder r7 = SpannableStringBuilder.valueOf(MessageObject.replaceAnimatedEmoji(r16, r24.currentStory.storyItem.entities, PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(r24).captionTextview.getPaint().getFontMetricsInt(), false));
            SpannableStringBuilder.valueOf(Emoji.replaceEmoji(r7, PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).captionTextview.getPaint().getFontMetricsInt(), false));
            TLRPC.User r17 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getUser(Long.valueOf(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0)));
            if (PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0) >= 0) goto L42;
        L43:
            r0 = 1;
        L44:
            if (r0 == 0) goto L46;
            MessageObject.addLinks(true, r7);
        L46:
            MessageObject.addEntitiesToText(r7, this.this$0.currentStory.storyItem.entities, false, true, true, false, r0 ^ 1);
            this.caption = r7;
            return;
        L42:
            if (MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).storyEntitiesAllowed(r17) == false) goto L44;
        L53:
            return;
        L19:
            if (r13.translatedText == null) goto L35;
            if (TextUtils.equals(r13.translatedLng, TranslateAlert2.getToLanguage()) == false) goto L35;
            this.captionTranslated = true;
            PeerStoriesView r18 = this.this$0;
            TLRPC.TL_textWithEntities r25 = r18.currentStory.storyItem.translatedText;
            String r32 = r25.text;
            this.caption = r32;
            CharSequence r19 = Emoji.replaceEmoji(r32, PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(r18).captionTextview.getPaint().getFontMetricsInt(), false);
            this.caption = r19;
            if (r19 != null) goto L25;
            return;
        L25:
            if (r25.entities == null) goto L51;
            SpannableStringBuilder r72 = SpannableStringBuilder.valueOf(MessageObject.replaceAnimatedEmoji(new SpannableStringBuilder(r25.text), r25.entities, PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).captionTextview.getPaint().getFontMetricsInt(), false));
            SpannableStringBuilder.valueOf(Emoji.replaceEmoji(r72, PeerStoriesView.m19521$$Nest$fgetstoryCaptionView(this.this$0).captionTextview.getPaint().getFontMetricsInt(), false));
            TLRPC.User r110 = MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).getUser(Long.valueOf(PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0)));
            if (PeerStoriesView.m19463$$Nest$fgetdialogId(this.this$0) >= 0) goto L29;
        L30:
            r0 = 1;
        L31:
            if (r0 == 0) goto L33;
            MessageObject.addLinks(true, r72);
        L33:
            MessageObject.addEntitiesToText(r72, r25.entities, false, true, true, false, r0 ^ 1);
            this.caption = r72;
            return;
        L29:
            if (MessagesController.getInstance(PeerStoriesView.m19461$$Nest$fgetcurrentAccount(this.this$0)).storyEntitiesAllowed(r110) == false) goto L31;
        }
    }

    public static class VideoPlayerSharedScope {
        public boolean firstFrameRendered;
        public LivePlayer livePlayer;
        public StoryViewer.VideoPlayerHolder player;
        public View renderView;
        public SurfaceView surfaceView;
        public TextureView textureView;
        public ArrayList<View> viewsToInvalidate;

        public VideoPlayerSharedScope() {
            this.viewsToInvalidate = new ArrayList();
        }

        public void invalidate() {
            int r0 = 0;
        L4:
            if (r0 >= this.viewsToInvalidate.size()) goto L6;
            this.viewsToInvalidate.get(r0).invalidate();
            r0 = r0 + 1;
            goto L4
        }

        public boolean isBuffering() {
            StoryViewer.VideoPlayerHolder r0 = this.player;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.isBuffering() == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    /* renamed from: $r8$lambda$-xtfHL-NjnamAIlhA98_odHTza8, reason: not valid java name */
    public static /* synthetic */ void m19427$r8$lambda$xtfHLNjnamAIlhA98_odHTza8(PeerStoriesView r0, View r1) {
        r0.lambda$createStealthModeItem$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$0ik2qzRppnVEXF6yez2nNoRQKek(MessagesController r6, long r7, boolean r9) {
        r6.getStoriesController().toggleHidden(r7, r9, true, true);
    }

    public static /* synthetic */ void $r8$lambda$1XDBPAFVIL65g_QKQe6Ak4MfJmY(PeerStoriesView r0, TL_stories.StoryItem r1, StoryPrivacyBottomSheet.StoryPrivacy r2, boolean r3, boolean r4, boolean r5, boolean r6, TLRPC.InputPeer r7, int r8, Runnable r9, Runnable r10) {
        r0.lambda$editPrivacy$57(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$1_uhfTrphZEJYmllXhHoMXfqU6w(PeerStoriesView r0, View r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$1vsNu69vGqr4c2a2THkjfbMVTMo(PeerStoriesView r0) {
        r0.openChat();
    }

    public static /* synthetic */ void $r8$lambda$3ioGz63XOjRoqhlICu7f3YW7QTo(PeerStoriesView r0, int r1, View r2) {
        r0.onSideControlButtonOnClick(r1, r2);
    }

    /* renamed from: $r8$lambda$3jgcyNkQyA7nr4-eEFpmPDDdZfQ, reason: not valid java name */
    public static /* synthetic */ void m19428$r8$lambda$3jgcyNkQyA7nr4eEFpmPDDdZfQ(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$4QfFWPrcWsFSMYACY9YyEqp58jw(PeerStoriesView r0, StoryRecorder r1, Long r2, Runnable r3, Boolean r4, Long r5) {
        r0.lambda$openRepostStory$37(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$5KJw3gRqyrfOl5vWB-O9Ab4AuMk, reason: not valid java name */
    public static /* synthetic */ void m19429$r8$lambda$5KJw3gRqyrfOl5vWBO9Ab4AuMk(PeerStoriesView r0) {
        r0.lambda$updatePosition$49();
    }

    /* renamed from: $r8$lambda$5LKF-z3khgZaUzGgbmsjLCOKKIU, reason: not valid java name */
    public static /* synthetic */ void m19430$r8$lambda$5LKFz3khgZaUzGgbmsjLCOKKIU(Runnable r2, long r3) {
        AndroidUtilities.cancelRunOnUIThread(r2);
        AndroidUtilities.runOnUIThread(r2, Math.max(0, 32 - (System.currentTimeMillis() - r3)));
    }

    /* renamed from: $r8$lambda$5xzY5ErVdMpVdCSqYpLFOwZKI-c, reason: not valid java name */
    public static /* synthetic */ void m19431$r8$lambda$5xzY5ErVdMpVdCSqYpLFOwZKIc(PeerStoriesView r0, AlertDialog r1, int r2) {
        r0.lambda$deleteStory$41(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$7oE_FylRRoL_vJqTS0F4cMrIsNc(PeerStoriesView r0, Theme.ResourcesProvider r1, StoryViewer r2, Context r3, SharedResources r4, View r5) {
        r0.lambda$new$8(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$8NMi2dZpQtON4zAjbfIwQ8oDV3g(PeerStoriesView r0) {
        r0.lambda$updatePosition$46();
    }

    public static /* synthetic */ void $r8$lambda$8pnBe_uVJxc1JQEJVstxae5hmfc(PeerStoriesView r0) {
        r0.lambda$new$9();
    }

    public static /* synthetic */ void $r8$lambda$9uU2MBUp6sdZJHyDEBxgzIVlnnA(PeerStoriesView r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$BYdA9VG7E_xKOnRsTCRlSFgmBiM(PeerStoriesView r0) {
        r0.lambda$new$43();
    }

    /* renamed from: $r8$lambda$CGrB28kLKakMFYO7pnVAyg-PJuQ, reason: not valid java name */
    public static /* synthetic */ boolean m19432$r8$lambda$CGrB28kLKakMFYO7pnVAygPJuQ(PeerStoriesView r0, View r1) {
        return r0.lambda$updatePosition$48(r1);
    }

    public static /* synthetic */ void $r8$lambda$FV05YVKLNnG6Nogkjo_sZGm3rJc(PeerStoriesView r0, boolean r1, View r2) {
        r0.lambda$createQualityItem$14(r1, r2);
    }

    /* renamed from: $r8$lambda$F_nRmlkXpd8b-5Oy0awO926MxC0, reason: not valid java name */
    public static /* synthetic */ void m19433$r8$lambda$F_nRmlkXpd8b5Oy0awO926MxC0(PeerStoriesView r0, TL_stories.TL_premium_boostsStatus r1, ChannelBoostsController.CanApplyBoost r2) {
        r0.lambda$showPremiumBlockedToast$22(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$HayBQhPDWnBIai626debILH914g(PeerStoriesView r0, long r1, View r3) {
        r0.lambda$updatePosition$45(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$KSHY7XvlCAUI6Ni0K_xjj2ck39Q(PeerStoriesView r0) {
        r0.lambda$updatePosition$47();
    }

    /* renamed from: $r8$lambda$O5m93U_myNI-8FXLnvSP_9n5Z2A, reason: not valid java name */
    public static /* synthetic */ void m19434$r8$lambda$O5m93U_myNI8FXLnvSP_9n5Z2A(PeerStoriesView r0, StoryViewer r1, View r2) {
        r0.lambda$new$6(r1, r2);
    }

    /* renamed from: $r8$lambda$PN1FrjSE21BsynY2F6vuqU-gX_Y, reason: not valid java name */
    public static /* synthetic */ void m19435$r8$lambda$PN1FrjSE21BsynY2F6vuqUgX_Y(PeerStoriesView r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ boolean $r8$lambda$Q6cAXzzauPjOkEqEQrjWrlwbhQo(PeerStoriesView r0, View r1) {
        return r0.lambda$createPaidReactionsButton$27(r1);
    }

    public static /* synthetic */ void $r8$lambda$R2WwYItA3rMERtNmuEtytpxNGm0(PeerStoriesView r0, boolean r1, Uri r2) {
        r0.lambda$saveToGallery$32(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$S4TktP5MG9zqBMsUaNWBVeHiby4(PeerStoriesView r0, ValueAnimator r1) {
        r0.lambda$onMeasure$53(r1);
    }

    public static /* synthetic */ void $r8$lambda$UmcccKOvVGe4OMdiW4bd6pocNXk(PeerStoriesView r0, View r1) {
        r0.lambda$createUnsupportedContainer$39(r1);
    }

    public static /* synthetic */ void $r8$lambda$WIQdYGX8NQkld8rM3v9aQ2HsozU(PeerStoriesView r0, ValueAnimator r1) {
        r0.lambda$showLikesReaction$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$Wb47ShyNFTzpt6g3X1rq9FKVKzs(PeerStoriesView r0, Runnable r1, TL_stories.StoryItem r2, StoryPrivacyBottomSheet.StoryPrivacy r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$editPrivacy$56(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$X4yNxQIlSQdvBUwjrOtO31hqVsw(PeerStoriesView r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.lambda$showPremiumBlockedToast$23(r1);
    }

    /* renamed from: $r8$lambda$YMmDo2roC-6nicrVE9FqmOlzLac, reason: not valid java name */
    public static /* synthetic */ boolean m19436$r8$lambda$YMmDo2roC6nicrVE9FqmOlzLac(PeerStoriesView r0, StoryViewer r1, View r2) {
        return r0.lambda$new$5(r1, r2);
    }

    /* renamed from: $r8$lambda$Z9uBd80okTFl8oyUeHO9OPZQ-QM, reason: not valid java name */
    public static /* synthetic */ void m19437$r8$lambda$Z9uBd80okTFl8oyUeHO9OPZQQM(PeerStoriesView r0, View r1) {
        r0.lambda$createSelfPeerView$40(r1);
    }

    public static /* synthetic */ void $r8$lambda$bAB5Tjs6HGRUntNB0i3LB6WqxzU(PeerStoriesView r0, StoryViewer r1, View r2) {
        r0.lambda$new$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$bQQLK47OWJYBUy4EPEsq22Ty2Qo(PeerStoriesView r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$c5s3qrKYqSp3aM3D-aaaySlX3Os, reason: not valid java name */
    public static /* synthetic */ void m19438$r8$lambda$c5s3qrKYqSp3aM3DaaaySlX3Os(PeerStoriesView r0) {
        r0.lambda$new$51();
    }

    /* renamed from: $r8$lambda$cf_0-6TVVv7-__CbfQXiWcZqoxg, reason: not valid java name */
    public static /* synthetic */ void m19439$r8$lambda$cf_06TVVv7__CbfQXiWcZqoxg(PeerStoriesView r0, Runnable r1, TLRPC.TL_error r2, TL_stories.StoryItem r3, StoryPrivacyBottomSheet.StoryPrivacy r4) {
        r0.lambda$editPrivacy$55(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$cxdbtxb8pQlafTJToZDEIZlL48A(PeerStoriesView r0, View r1) {
        r0.lambda$new$10(r1);
    }

    /* renamed from: $r8$lambda$dGoH3A6sk-lctbRIH2lSj7RSHyg, reason: not valid java name */
    public static /* synthetic */ void m19440$r8$lambda$dGoH3A6sklctbRIH2lSj7RSHyg(Runnable r0) {
        AndroidUtilities.cancelRunOnUIThread(r0);
        AndroidUtilities.runOnUIThread(r0);
    }

    public static /* synthetic */ void $r8$lambda$fVFkGTmttaqhqQpRRXIWpHYmyGw(PeerStoriesView r0, Long r1) {
        r0.lambda$onHighlightLiveMessage$52(r1);
    }

    /* renamed from: $r8$lambda$fuROC0CdNJ1yOf8-dtKQ8bXZIGc, reason: not valid java name */
    public static /* synthetic */ void m19441$r8$lambda$fuROC0CdNJ1yOf8dtKQ8bXZIGc(PeerStoriesView r0, StoryCaptionView.Panel r1, View r2) {
        r0.lambda$updatePosition$44(r1, r2);
    }

    /* renamed from: $r8$lambda$hd55xIhXjbo74cE5-o2aTpFbJ3E, reason: not valid java name */
    public static /* synthetic */ void m19442$r8$lambda$hd55xIhXjbo74cE5o2aTpFbJ3E(PeerStoriesView r0, View r1) {
        r0.lambda$createMuteButton$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$hdgaMXjz6yqSszZqq8ft9dfov7g(PeerStoriesView r0, boolean r1, AlertDialog r2, int r3) {
        r0.lambda$checkRecordLocked$58(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$jA4fkKqznuxN95Or9apwqDlfdeI(PeerStoriesView r0, ValueAnimator r1) {
        r0.lambda$animateOut$59(r1);
    }

    public static /* synthetic */ void $r8$lambda$jR00T5OmQpVeDzMqC5466hFMv2s(MessagesController r6, long r7, boolean r9) {
        r6.getStoriesController().toggleHidden(r7, !r9, false, true);
    }

    public static /* synthetic */ void $r8$lambda$jRkMhX5UHD4i59raDrtgVIXpqc8(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    /* renamed from: $r8$lambda$luXKqCCUWbDbt8ZcUnGhjc-YbZo, reason: not valid java name */
    public static /* synthetic */ void m19443$r8$lambda$luXKqCCUWbDbt8ZcUnGhjcYbZo(PeerStoriesView r0) {
        r0.openRepostStory();
    }

    public static /* synthetic */ void $r8$lambda$n8j5lYXqepsOFM0MCcC7k04E5FQ(PeerStoriesView r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$nc6_wodA1XVon4YBpxgGFC9Quu8(PeerStoriesView r0) {
        r0.lambda$showPremiumBlockedToast$24();
    }

    public static /* synthetic */ void $r8$lambda$oclH5F_UUYV_FQ4CsOjqSEBIeCs(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$p4uqUxirvZKRyFdxrFDlUQahTtY(PeerStoriesView r0) {
        r0.lambda$updatePosition$50();
    }

    public static /* synthetic */ void $r8$lambda$q3DfS3VR2ccA9RnyXSgBWMIRJAw(PeerStoriesView r0, View r1) {
        r0.lambda$createPaidReactionsButton$26(r1);
    }

    /* renamed from: $r8$lambda$r-zynHh0TQU2ngjF77eATLid5Bk, reason: not valid java name */
    public static /* synthetic */ void m19444$r8$lambda$rzynHh0TQU2ngjF77eATLid5Bk(PeerStoriesView r0) {
        r0.lambda$openRepostStory$33();
    }

    /* renamed from: $r8$lambda$sNhOCT8-iZ6oSy3jK5gQeSVpJjs, reason: not valid java name */
    public static /* synthetic */ void m19445$r8$lambda$sNhOCT8iZ6oSy3jK5gQeSVpJjs(PeerStoriesView r0) {
        r0.lambda$needEnterText$54();
    }

    public static /* synthetic */ void $r8$lambda$tdnXoK8I_D7AEKL67ibpIUlNKC0(PeerStoriesView r0, MessagesController r1, long r2, boolean r4, String r5, TLObject r6) {
        r0.lambda$toggleArchiveForStory$20(r1, r2, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$uFICWupu_4RPPg3nKb_c_sQ3uPs(PeerStoriesView r0, HintView2 r1) {
        r0.lambda$showPaidMessageHint$29(r1);
    }

    public static /* synthetic */ void $r8$lambda$uMZr2oFJQo_6IPHQMyJ80NyBLRA(DialogStoriesCell.StoryCell r2, DialogStoriesCell r3, Long r4, StoryRecorder r5, Runnable r6) {
        if (r2 != null) goto L4;
        r2 = r3.findStoryCell(r4.longValue());
    L4:
        r5.replaceSourceView(StoryRecorder.SourceView.fromStoryCell(r2));
        r6.run();
    }

    public static /* synthetic */ void $r8$lambda$vwvz4GSsncuxfnwBKyPYOh1YEgk(PeerStoriesView r0, View r1) {
        r0.lambda$createFailView$21(r1);
    }

    /* renamed from: $r8$lambda$xJ585UAb_mfEYS1its-uU-qc8m4, reason: not valid java name */
    public static /* synthetic */ void m19446$r8$lambda$xJ585UAb_mfEYS1itsuUqc8m4(PeerStoriesView r0, View r1) {
        r0.lambda$createCommentButton$25(r1);
    }

    /* renamed from: $r8$lambda$xhuXrgHK0uviQyFyVzNSs2yHv-A, reason: not valid java name */
    public static /* synthetic */ void m19447$r8$lambda$xhuXrgHK0uviQyFyVzNSs2yHvA(PeerStoriesView r0, Activity r1) {
        r0.lambda$openRepostStory$38(r1);
    }

    public static /* synthetic */ void $r8$lambda$yJHJR1bijBsghFqqIA0QqbKSXoo(StoryViewer r0, View r1) {
        if (r0 == null) goto L5;
        r0.switchToPip();
        return;
    }

    /* renamed from: -$$Nest$fgetBIG_SCREEN, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19448$$Nest$fgetBIG_SCREEN(PeerStoriesView r0) {
        return r0.BIG_SCREEN;
    }

    /* renamed from: -$$Nest$fgetalbumItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m19449$$Nest$fgetalbumItem(PeerStoriesView r0) {
        return r0.albumItem;
    }

    /* renamed from: -$$Nest$fgetalbumLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m19450$$Nest$fgetalbumLayout(PeerStoriesView r0) {
        return r0.albumLayout;
    }

    /* renamed from: -$$Nest$fgetallowDrawSurface, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19451$$Nest$fgetallowDrawSurface(PeerStoriesView r0) {
        return r0.allowDrawSurface;
    }

    /* renamed from: -$$Nest$fgetallowShare, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19452$$Nest$fgetallowShare(PeerStoriesView r0) {
        return r0.allowShare;
    }

    /* renamed from: -$$Nest$fgetallowShareLink, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19453$$Nest$fgetallowShareLink(PeerStoriesView r0) {
        return r0.allowShareLink;
    }

    /* renamed from: -$$Nest$fgetalpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19454$$Nest$fgetalpha(PeerStoriesView r0) {
        return r0.alpha;
    }

    /* renamed from: -$$Nest$fgetbitmapShaderTools, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BitmapShaderTools m19455$$Nest$fgetbitmapShaderTools(PeerStoriesView r0) {
        return r0.bitmapShaderTools;
    }

    /* renamed from: -$$Nest$fgetblurredBackgroundSourceRenderNodeWithSaturation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m19456$$Nest$fgetblurredBackgroundSourceRenderNodeWithSaturation(PeerStoriesView r0) {
        return r0.blurredBackgroundSourceRenderNodeWithSaturation;
    }

    /* renamed from: -$$Nest$fgetchangeBoundAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m19457$$Nest$fgetchangeBoundAnimator(PeerStoriesView r0) {
        return r0.changeBoundAnimator;
    }

    /* renamed from: -$$Nest$fgetchatAttachAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlert m19458$$Nest$fgetchatAttachAlert(PeerStoriesView r0) {
        return r0.chatAttachAlert;
    }

    /* renamed from: -$$Nest$fgetclassGuid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19459$$Nest$fgetclassGuid(PeerStoriesView r0) {
        return r0.classGuid;
    }

    /* renamed from: -$$Nest$fgetcommentButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CommentButton m19460$$Nest$fgetcommentButton(PeerStoriesView r0) {
        return r0.commentButton;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19461$$Nest$fgetcurrentAccount(PeerStoriesView r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcurrentImageTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m19462$$Nest$fgetcurrentImageTime(PeerStoriesView r2) {
        return r2.currentImageTime;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m19463$$Nest$fgetdialogId(PeerStoriesView r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetemojiAnimationsOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiAnimationsOverlay m19464$$Nest$fgetemojiAnimationsOverlay(PeerStoriesView r0) {
        return r0.emojiAnimationsOverlay;
    }

    /* renamed from: -$$Nest$fgetemojiReactionEffect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedEmojiEffect m19465$$Nest$fgetemojiReactionEffect(PeerStoriesView r0) {
        return r0.emojiReactionEffect;
    }

    /* renamed from: -$$Nest$fgetimageChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19466$$Nest$fgetimageChanged(PeerStoriesView r0) {
        return r0.imageChanged;
    }

    /* renamed from: -$$Nest$fgetimageReceiver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageReceiver m19467$$Nest$fgetimageReceiver(PeerStoriesView r0) {
        return r0.imageReceiver;
    }

    /* renamed from: -$$Nest$fgetinstantCameraView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ InstantCameraView m19468$$Nest$fgetinstantCameraView(PeerStoriesView r0) {
        return r0.instantCameraView;
    }

    /* renamed from: -$$Nest$fgetisCaptionPartVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19469$$Nest$fgetisCaptionPartVisible(PeerStoriesView r0) {
        return r0.isCaptionPartVisible;
    }

    /* renamed from: -$$Nest$fgetisEditing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19470$$Nest$fgetisEditing(PeerStoriesView r0) {
        return r0.isEditing;
    }

    /* renamed from: -$$Nest$fgetisFailed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19471$$Nest$fgetisFailed(PeerStoriesView r0) {
        return r0.isFailed;
    }

    /* renamed from: -$$Nest$fgetisLongPressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19472$$Nest$fgetisLongPressed(PeerStoriesView r0) {
        return r0.isLongPressed;
    }

    /* renamed from: -$$Nest$fgetisRecording, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19473$$Nest$fgetisRecording(PeerStoriesView r0) {
        return r0.isRecording;
    }

    /* renamed from: -$$Nest$fgetisUploading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19474$$Nest$fgetisUploading(PeerStoriesView r0) {
        return r0.isUploading;
    }

    /* renamed from: -$$Nest$fgetlastDrawTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m19475$$Nest$fgetlastDrawTime(PeerStoriesView r2) {
        return r2.lastDrawTime;
    }

    /* renamed from: -$$Nest$fgetlastNoThumb, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19476$$Nest$fgetlastNoThumb(PeerStoriesView r0) {
        return r0.lastNoThumb;
    }

    /* renamed from: -$$Nest$fgetlikeButtonContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m19477$$Nest$fgetlikeButtonContainer(PeerStoriesView r0) {
        return r0.likeButtonContainer;
    }

    /* renamed from: -$$Nest$fgetlikesReactionLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReactionsContainerLayout m19478$$Nest$fgetlikesReactionLayout(PeerStoriesView r0) {
        return r0.likesReactionLayout;
    }

    /* renamed from: -$$Nest$fgetlinesAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedFloat m19479$$Nest$fgetlinesAlpha(PeerStoriesView r0) {
        return r0.linesAlpha;
    }

    /* renamed from: -$$Nest$fgetlinesCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19480$$Nest$fgetlinesCount(PeerStoriesView r0) {
        return r0.linesCount;
    }

    /* renamed from: -$$Nest$fgetlinesPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19481$$Nest$fgetlinesPosition(PeerStoriesView r0) {
        return r0.linesPosition;
    }

    /* renamed from: -$$Nest$fgetlistPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19482$$Nest$fgetlistPosition(PeerStoriesView r0) {
        return r0.listPosition;
    }

    /* renamed from: -$$Nest$fgetmediaBanTooltip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HintView m19483$$Nest$fgetmediaBanTooltip(PeerStoriesView r0) {
        return r0.mediaBanTooltip;
    }

    /* renamed from: -$$Nest$fgetmentionContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MentionsContainerView m19484$$Nest$fgetmentionContainer(PeerStoriesView r0) {
        return r0.mentionContainer;
    }

    /* renamed from: -$$Nest$fgetmessageStars, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m19485$$Nest$fgetmessageStars(PeerStoriesView r2) {
        return r2.messageStars;
    }

    /* renamed from: -$$Nest$fgetmovingReactionProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19486$$Nest$fgetmovingReactionProgress(PeerStoriesView r0) {
        return r0.movingReactionProgress;
    }

    /* renamed from: -$$Nest$fgetmuteIconContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m19487$$Nest$fgetmuteIconContainer(PeerStoriesView r0) {
        return r0.muteIconContainer;
    }

    /* renamed from: -$$Nest$fgetmuteIconView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m19488$$Nest$fgetmuteIconView(PeerStoriesView r0) {
        return r0.muteIconView;
    }

    /* renamed from: -$$Nest$fgetmuteIconViewAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19489$$Nest$fgetmuteIconViewAlpha(PeerStoriesView r0) {
        return r0.muteIconViewAlpha;
    }

    /* renamed from: -$$Nest$fgetonImageReceiverThumbLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m19490$$Nest$fgetonImageReceiverThumbLoaded(PeerStoriesView r0) {
        return r0.onImageReceiverThumbLoaded;
    }

    /* renamed from: -$$Nest$fgetoptionsIconView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m19491$$Nest$fgetoptionsIconView(PeerStoriesView r0) {
        return r0.optionsIconView;
    }

    /* renamed from: -$$Nest$fgetoutT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19492$$Nest$fgetoutT(PeerStoriesView r0) {
        return r0.outT;
    }

    /* renamed from: -$$Nest$fgetpaused, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19493$$Nest$fgetpaused(PeerStoriesView r0) {
        return r0.paused;
    }

    /* renamed from: -$$Nest$fgetpipIconView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m19494$$Nest$fgetpipIconView(PeerStoriesView r0) {
        return r0.pipIconView;
    }

    /* renamed from: -$$Nest$fgetpremiumBlockedText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m19495$$Nest$fgetpremiumBlockedText(PeerStoriesView r0) {
        return r0.premiumBlockedText;
    }

    /* renamed from: -$$Nest$fgetpremiumBlockedText2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m19496$$Nest$fgetpremiumBlockedText2(PeerStoriesView r0) {
        return r0.premiumBlockedText2;
    }

    /* renamed from: -$$Nest$fgetprivacyButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryPrivacyButton m19497$$Nest$fgetprivacyButton(PeerStoriesView r0) {
        return r0.privacyButton;
    }

    /* renamed from: -$$Nest$fgetreactionEffectImageReceiver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageReceiver m19498$$Nest$fgetreactionEffectImageReceiver(PeerStoriesView r0) {
        return r0.reactionEffectImageReceiver;
    }

    /* renamed from: -$$Nest$fgetreactionMoveDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable m19499$$Nest$fgetreactionMoveDrawable(PeerStoriesView r0) {
        return r0.reactionMoveDrawable;
    }

    /* renamed from: -$$Nest$fgetreactionMoveImageReceiver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageReceiver m19500$$Nest$fgetreactionMoveImageReceiver(PeerStoriesView r0) {
        return r0.reactionMoveImageReceiver;
    }

    /* renamed from: -$$Nest$fgetreactionsCounter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView.AnimatedTextDrawable m19501$$Nest$fgetreactionsCounter(PeerStoriesView r0) {
        return r0.reactionsCounter;
    }

    /* renamed from: -$$Nest$fgetreactionsCounterProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedFloat m19502$$Nest$fgetreactionsCounterProgress(PeerStoriesView r0) {
        return r0.reactionsCounterProgress;
    }

    /* renamed from: -$$Nest$fgetreactionsCounterVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19503$$Nest$fgetreactionsCounterVisible(PeerStoriesView r0) {
        return r0.reactionsCounterVisible;
    }

    /* renamed from: -$$Nest$fgetrepostButtonContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m19504$$Nest$fgetrepostButtonContainer(PeerStoriesView r0) {
        return r0.repostButtonContainer;
    }

    /* renamed from: -$$Nest$fgetrepostCounter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView.AnimatedTextDrawable m19505$$Nest$fgetrepostCounter(PeerStoriesView r0) {
        return r0.repostCounter;
    }

    /* renamed from: -$$Nest$fgetrepostCounterProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedFloat m19506$$Nest$fgetrepostCounterProgress(PeerStoriesView r0) {
        return r0.repostCounterProgress;
    }

    /* renamed from: -$$Nest$fgetrepostCounterVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19507$$Nest$fgetrepostCounterVisible(PeerStoriesView r0) {
        return r0.repostCounterVisible;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m19508$$Nest$fgetresourcesProvider(PeerStoriesView r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetselfAvatarsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m19509$$Nest$fgetselfAvatarsContainer(PeerStoriesView r0) {
        return r0.selfAvatarsContainer;
    }

    /* renamed from: -$$Nest$fgetselfStatusView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m19510$$Nest$fgetselfStatusView(PeerStoriesView r0) {
        return r0.selfStatusView;
    }

    /* renamed from: -$$Nest$fgetselfView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m19511$$Nest$fgetselfView(PeerStoriesView r0) {
        return r0.selfView;
    }

    /* renamed from: -$$Nest$fgetsendAsPeersObj, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_channels_sendAsPeers m19512$$Nest$fgetsendAsPeersObj(PeerStoriesView r0) {
        return r0.sendAsPeersObj;
    }

    /* renamed from: -$$Nest$fgetshareButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m19513$$Nest$fgetshareButton(PeerStoriesView r0) {
        return r0.shareButton;
    }

    /* renamed from: -$$Nest$fgetshiftDp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19514$$Nest$fgetshiftDp(PeerStoriesView r0) {
        return r0.shiftDp;
    }

    /* renamed from: -$$Nest$fgetspeedItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m19515$$Nest$fgetspeedItem(PeerStoriesView r0) {
        return r0.speedItem;
    }

    /* renamed from: -$$Nest$fgetspeedLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChooseSpeedLayout m19516$$Nest$fgetspeedLayout(PeerStoriesView r0) {
        return r0.speedLayout;
    }

    /* renamed from: -$$Nest$fgetstarsButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaidReactionButton m19517$$Nest$fgetstarsButton(PeerStoriesView r0) {
        return r0.starsButton;
    }

    /* renamed from: -$$Nest$fgetstarsButtonEffectsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaidReactionButton.PaidReactionButtonEffectsView m19518$$Nest$fgetstarsButtonEffectsView(PeerStoriesView r0) {
        return r0.starsButtonEffectsView;
    }

    /* renamed from: -$$Nest$fgetstoriesLikeButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesLikeButton m19519$$Nest$fgetstoriesLikeButton(PeerStoriesView r0) {
        return r0.storiesLikeButton;
    }

    /* renamed from: -$$Nest$fgetstoryAreasView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryMediaAreasView m19520$$Nest$fgetstoryAreasView(PeerStoriesView r0) {
        return r0.storyAreasView;
    }

    /* renamed from: -$$Nest$fgetstoryCaptionView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryCaptionView m19521$$Nest$fgetstoryCaptionView(PeerStoriesView r0) {
        return r0.storyCaptionView;
    }

    /* renamed from: -$$Nest$fgetstoryLines, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryLinesDrawable m19522$$Nest$fgetstoryLines(PeerStoriesView r0) {
        return r0.storyLines;
    }

    /* renamed from: -$$Nest$fgetstoryPositionView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryPositionView m19523$$Nest$fgetstoryPositionView(PeerStoriesView r0) {
        return r0.storyPositionView;
    }

    /* renamed from: -$$Nest$fgetstoryViewer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryViewer m19524$$Nest$fgetstoryViewer(PeerStoriesView r0) {
        return r0.storyViewer;
    }

    /* renamed from: -$$Nest$fgetswitchEventSent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19525$$Nest$fgetswitchEventSent(PeerStoriesView r0) {
        return r0.switchEventSent;
    }

    /* renamed from: -$$Nest$fgetviewsThumbAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19526$$Nest$fgetviewsThumbAlpha(PeerStoriesView r0) {
        return r0.viewsThumbAlpha;
    }

    /* renamed from: -$$Nest$fgetviewsThumbImageReceiver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SelfStoriesPreviewView.ImageHolder m19527$$Nest$fgetviewsThumbImageReceiver(PeerStoriesView r0) {
        return r0.viewsThumbImageReceiver;
    }

    /* renamed from: -$$Nest$fgetviewsThumbScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19528$$Nest$fgetviewsThumbScale(PeerStoriesView r0) {
        return r0.viewsThumbScale;
    }

    /* renamed from: -$$Nest$fputalbumItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19529$$Nest$fputalbumItem(PeerStoriesView r0, ActionBarMenuSubItem r1) {
        r0.albumItem = r1;
    }

    /* renamed from: -$$Nest$fputalbumLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19530$$Nest$fputalbumLayout(PeerStoriesView r0, ViewGroup r1) {
        r0.albumLayout = r1;
    }

    /* renamed from: -$$Nest$fputanimatingKeyboardHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19531$$Nest$fputanimatingKeyboardHeight(PeerStoriesView r0, float r1) {
        r0.animatingKeyboardHeight = r1;
    }

    /* renamed from: -$$Nest$fputchangeBoundAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19532$$Nest$fputchangeBoundAnimator(PeerStoriesView r0, ValueAnimator r1) {
        r0.changeBoundAnimator = r1;
    }

    /* renamed from: -$$Nest$fputcurrentImageTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19533$$Nest$fputcurrentImageTime(PeerStoriesView r0, long r1) {
        r0.currentImageTime = r1;
    }

    /* renamed from: -$$Nest$fputdrawAnimatedEmojiAsMovingReaction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19534$$Nest$fputdrawAnimatedEmojiAsMovingReaction(PeerStoriesView r0, boolean r1) {
        r0.drawAnimatedEmojiAsMovingReaction = r1;
    }

    /* renamed from: -$$Nest$fputdrawReactionEffect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19535$$Nest$fputdrawReactionEffect(PeerStoriesView r0, boolean r1) {
        r0.drawReactionEffect = r1;
    }

    /* renamed from: -$$Nest$fputemojiReactionEffect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19536$$Nest$fputemojiReactionEffect(PeerStoriesView r0, AnimatedEmojiEffect r1) {
        r0.emojiReactionEffect = r1;
    }

    /* renamed from: -$$Nest$fputimageChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19537$$Nest$fputimageChanged(PeerStoriesView r0, boolean r1) {
        r0.imageChanged = r1;
    }

    /* renamed from: -$$Nest$fputisCaptionPartVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19538$$Nest$fputisCaptionPartVisible(PeerStoriesView r0, boolean r1) {
        r0.isCaptionPartVisible = r1;
    }

    /* renamed from: -$$Nest$fputisRecording, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19539$$Nest$fputisRecording(PeerStoriesView r0, boolean r1) {
        r0.isRecording = r1;
    }

    /* renamed from: -$$Nest$fputlastDrawTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19540$$Nest$fputlastDrawTime(PeerStoriesView r0, long r1) {
        r0.lastDrawTime = r1;
    }

    /* renamed from: -$$Nest$fputlastNoThumb, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19541$$Nest$fputlastNoThumb(PeerStoriesView r0, boolean r1) {
        r0.lastNoThumb = r1;
    }

    /* renamed from: -$$Nest$fputlikesReactionShowProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19542$$Nest$fputlikesReactionShowProgress(PeerStoriesView r0, float r1) {
        r0.likesReactionShowProgress = r1;
    }

    /* renamed from: -$$Nest$fputmediaBanTooltip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19543$$Nest$fputmediaBanTooltip(PeerStoriesView r0, HintView r1) {
        r0.mediaBanTooltip = r1;
    }

    /* renamed from: -$$Nest$fputmessageStars, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19544$$Nest$fputmessageStars(PeerStoriesView r0, long r1) {
        r0.messageStars = r1;
    }

    /* renamed from: -$$Nest$fputmovingReaction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19545$$Nest$fputmovingReaction(PeerStoriesView r0, boolean r1) {
        r0.movingReaction = r1;
    }

    /* renamed from: -$$Nest$fputmovingReactionFromSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19546$$Nest$fputmovingReactionFromSize(PeerStoriesView r0, int r1) {
        r0.movingReactionFromSize = r1;
    }

    /* renamed from: -$$Nest$fputmovingReactionFromX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19547$$Nest$fputmovingReactionFromX(PeerStoriesView r0, int r1) {
        r0.movingReactionFromX = r1;
    }

    /* renamed from: -$$Nest$fputmovingReactionFromY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19548$$Nest$fputmovingReactionFromY(PeerStoriesView r0, int r1) {
        r0.movingReactionFromY = r1;
    }

    /* renamed from: -$$Nest$fputmovingReactionProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19549$$Nest$fputmovingReactionProgress(PeerStoriesView r0, float r1) {
        r0.movingReactionProgress = r1;
    }

    /* renamed from: -$$Nest$fputonImageReceiverThumbLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19550$$Nest$fputonImageReceiverThumbLoaded(PeerStoriesView r0, Runnable r1) {
        r0.onImageReceiverThumbLoaded = r1;
    }

    /* renamed from: -$$Nest$fputoutT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19551$$Nest$fputoutT(PeerStoriesView r0, float r1) {
        r0.outT = r1;
    }

    /* renamed from: -$$Nest$fputreactionMoveDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19552$$Nest$fputreactionMoveDrawable(PeerStoriesView r0, AnimatedEmojiDrawable r1) {
        r0.reactionMoveDrawable = r1;
    }

    /* renamed from: -$$Nest$fputshiftDp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19553$$Nest$fputshiftDp(PeerStoriesView r0, int r1) {
        r0.shiftDp = r1;
    }

    /* renamed from: -$$Nest$fputspeedItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19554$$Nest$fputspeedItem(PeerStoriesView r0, ActionBarMenuSubItem r1) {
        r0.speedItem = r1;
    }

    /* renamed from: -$$Nest$fputspeedLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19555$$Nest$fputspeedLayout(PeerStoriesView r0, ChooseSpeedLayout r1) {
        r0.speedLayout = r1;
    }

    /* renamed from: -$$Nest$fputstoriesLikeButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19556$$Nest$fputstoriesLikeButton(PeerStoriesView r0, StoriesLikeButton r1) {
        r0.storiesLikeButton = r1;
    }

    /* renamed from: -$$Nest$fputstoryPositionView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19557$$Nest$fputstoryPositionView(PeerStoriesView r0, StoryPositionView r1) {
        r0.storyPositionView = r1;
    }

    /* renamed from: -$$Nest$fputswitchEventSent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19558$$Nest$fputswitchEventSent(PeerStoriesView r0, boolean r1) {
        r0.switchEventSent = r1;
    }

    /* renamed from: -$$Nest$mafterMessageSend, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19559$$Nest$mafterMessageSend(PeerStoriesView r0, boolean r1) {
        r0.afterMessageSend(r1);
    }

    /* renamed from: -$$Nest$mapplyMessageToChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19560$$Nest$mapplyMessageToChat(PeerStoriesView r0, Runnable r1) {
        return r0.applyMessageToChat(r1);
    }

    /* renamed from: -$$Nest$mcheckInstantCameraView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19561$$Nest$mcheckInstantCameraView(PeerStoriesView r0) {
        r0.checkInstantCameraView();
    }

    /* renamed from: -$$Nest$mcheckStealthMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19562$$Nest$mcheckStealthMode(PeerStoriesView r0, boolean r1) {
        r0.checkStealthMode(r1);
    }

    /* renamed from: -$$Nest$mcreateMentionsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19563$$Nest$mcreateMentionsContainer(PeerStoriesView r0) {
        r0.createMentionsContainer();
    }

    /* renamed from: -$$Nest$mcreateQualityItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19564$$Nest$mcreateQualityItem(PeerStoriesView r0, ActionBarPopupWindow.ActionBarPopupWindowLayout r1) {
        r0.createQualityItem(r1);
    }

    /* renamed from: -$$Nest$mcreateStealthModeItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19565$$Nest$mcreateStealthModeItem(PeerStoriesView r0, ActionBarPopupWindow.ActionBarPopupWindowLayout r1) {
        r0.createStealthModeItem(r1);
    }

    /* renamed from: -$$Nest$mdeleteStory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19566$$Nest$mdeleteStory(PeerStoriesView r0) {
        r0.deleteStory();
    }

    /* renamed from: -$$Nest$mdisabledPaidFeatures, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19567$$Nest$mdisabledPaidFeatures(PeerStoriesView r0, boolean r1) {
        return r0.disabledPaidFeatures(r1);
    }

    /* renamed from: -$$Nest$mdrawLinesAsCounter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19568$$Nest$mdrawLinesAsCounter(PeerStoriesView r0) {
        return r0.drawLinesAsCounter();
    }

    /* renamed from: -$$Nest$meditPrivacy, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19569$$Nest$meditPrivacy(PeerStoriesView r0, StoryPrivacyBottomSheet.StoryPrivacy r1, TL_stories.StoryItem r2) {
        r0.editPrivacy(r1, r2);
    }

    /* renamed from: -$$Nest$mfindActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Activity m19570$$Nest$mfindActivity(PeerStoriesView r0) {
        return r0.findActivity();
    }

    /* renamed from: -$$Nest$mgetAccountInstance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AccountInstance m19571$$Nest$mgetAccountInstance(PeerStoriesView r0) {
        return r0.getAccountInstance();
    }

    /* renamed from: -$$Nest$mgetAnimatedEmojiSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m19572$$Nest$mgetAnimatedEmojiSets(PeerStoriesView r0, StoryItemHolder r1) {
        return r0.getAnimatedEmojiSets(r1);
    }

    /* renamed from: -$$Nest$mgetHideInterfaceAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19573$$Nest$mgetHideInterfaceAlpha(PeerStoriesView r0) {
        return r0.getHideInterfaceAlpha();
    }

    /* renamed from: -$$Nest$mgetMessageMinPrice, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m19574$$Nest$mgetMessageMinPrice(PeerStoriesView r2) {
        return r2.getMessageMinPrice();
    }

    /* renamed from: -$$Nest$mhideCaptionWithInterface, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19575$$Nest$mhideCaptionWithInterface(PeerStoriesView r0) {
        return r0.hideCaptionWithInterface();
    }

    /* renamed from: -$$Nest$misBotsPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19576$$Nest$misBotsPreview(PeerStoriesView r0) {
        return r0.isBotsPreview();
    }

    /* renamed from: -$$Nest$misEditBotsPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19577$$Nest$misEditBotsPreview(PeerStoriesView r0) {
        return r0.isEditBotsPreview();
    }

    /* renamed from: -$$Nest$mlikeStory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19578$$Nest$mlikeStory(PeerStoriesView r0, ReactionsLayoutInBubble.VisibleReaction r1) {
        r0.likeStory(r1);
    }

    /* renamed from: -$$Nest$monHighlightLiveMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19579$$Nest$monHighlightLiveMessage(PeerStoriesView r0) {
        r0.onHighlightLiveMessage();
    }

    /* renamed from: -$$Nest$monLinkCopied, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19580$$Nest$monLinkCopied(PeerStoriesView r0) {
        r0.onLinkCopied();
    }

    /* renamed from: -$$Nest$mopenAttachMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19581$$Nest$mopenAttachMenu(PeerStoriesView r0) {
        r0.openAttachMenu();
    }

    /* renamed from: -$$Nest$mopenChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19582$$Nest$mopenChat(PeerStoriesView r0) {
        r0.openChat();
    }

    /* renamed from: -$$Nest$msaveToGallery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19583$$Nest$msaveToGallery(PeerStoriesView r0) {
        r0.saveToGallery();
    }

    /* renamed from: -$$Nest$mshareStory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19584$$Nest$mshareStory(PeerStoriesView r0, boolean r1) {
        r0.shareStory(r1);
    }

    /* renamed from: -$$Nest$mshowDownloadAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19585$$Nest$mshowDownloadAlert(PeerStoriesView r0) {
        r0.showDownloadAlert();
    }

    /* renamed from: -$$Nest$mshowLikesReaction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19586$$Nest$mshowLikesReaction(PeerStoriesView r0, boolean r1) {
        r0.showLikesReaction(r1);
    }

    /* renamed from: -$$Nest$mshowPremiumBlockedToast, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19587$$Nest$mshowPremiumBlockedToast(PeerStoriesView r0) {
        r0.showPremiumBlockedToast();
    }

    /* renamed from: -$$Nest$mtoggleArchiveForStory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19588$$Nest$mtoggleArchiveForStory(PeerStoriesView r0, long r1) {
        r0.toggleArchiveForStory(r1);
    }

    /* renamed from: -$$Nest$mtryToOpenRepostStory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19589$$Nest$mtryToOpenRepostStory(PeerStoriesView r0) {
        r0.tryToOpenRepostStory();
    }

    /* renamed from: -$$Nest$mupdateSpeedItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19590$$Nest$mupdateSpeedItem(PeerStoriesView r0, boolean r1) {
        r0.updateSpeedItem(r1);
    }

    /* renamed from: -$$Nest$mupdateStoryItems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19591$$Nest$mupdateStoryItems(PeerStoriesView r0) {
        r0.updateStoryItems();
    }

    /* renamed from: -$$Nest$mupdateUserViews, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19592$$Nest$mupdateUserViews(PeerStoriesView r0, boolean r1) {
        r0.updateUserViews(r1);
    }

    /* renamed from: -$$Nest$mupdateViewOffsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19593$$Nest$mupdateViewOffsets(PeerStoriesView r0) {
        r0.updateViewOffsets();
    }

    static {
    }

    public PeerStoriesView(Context r26, StoryViewer r27, SharedResources r28, Theme.ResourcesProvider r29) {
        super(r26);
        this.allowDrawSurface = true;
        this.preloadReactionHolders = new ArrayList();
        this.shiftDp = -5;
        this.alpha = 1.0f;
        this.previousSelectedPotision = -1;
        StoryItemHolder r9 = new StoryItemHolder(this);
        this.currentStory = r9;
        this.progressToKeyboard = -1.0f;
        this.progressToDismiss = -1.0f;
        this.lastAnimatingKeyboardHeight = -1.0f;
        this.classGuid = ConnectionsManager.generateClassGuid();
        this.progressToHideInterface = new AnimatedFloat(this);
        this.linesAlpha = new AnimatedFloat(this);
        this.pinchToZoomHelper = new PinchToZoomHelper();
        this.muteIconViewAlpha = 1.0f;
        this.updateStealthModeTimer = new PeerStoriesView$$ExternalSyntheticLambda3(this);
        this.showTapToSoundHint = new PeerStoriesView$$ExternalSyntheticLambda6(this);
        this.uriesToPrepare = new ArrayList();
        this.documentsToPrepare = new ArrayList();
        this.allowDrawSurfaceRunnable = new AnonymousClass34(this);
        this.progressToRecording = new AnimatedFloat(this);
        this.progressToTextA = new AnimatedFloat(this);
        this.progressToStickerExpanded = new AnimatedFloat(this);
        this.clipPath = new Path();
        this.pinchToZoomHelper.setCallback(new AnonymousClass1(this));
        this.playerSharedScope = new VideoPlayerSharedScope();
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.storyItems = new ArrayList();
        this.uploadingStories = new ArrayList();
        AnonymousClass2 r11 = new AnonymousClass2(this);
        this.imageReceiver = r11;
        r11.setCrossfadeWithOldImage(false);
        r11.setAllowLoadingOnAttachedOnly(true);
        r11.ignoreNotifications = true;
        r11.setFileLoadingPriority(0);
        ImageReceiver r0 = new ImageReceiver(this);
        this.reactionEffectImageReceiver = r0;
        r0.setAllowLoadingOnAttachedOnly(true);
        ImageReceiver r02 = this.reactionEffectImageReceiver;
        r02.ignoreNotifications = true;
        r02.setFileLoadingPriority(3);
        ImageReceiver r03 = new ImageReceiver(this);
        this.reactionMoveImageReceiver = r03;
        r03.setAllowLoadingOnAttachedOnly(true);
        ImageReceiver r04 = this.reactionMoveImageReceiver;
        r04.ignoreNotifications = true;
        r04.setFileLoadingPriority(3);
        ImageReceiver r05 = new ImageReceiver();
        this.leftPreloadImageReceiver = r05;
        r05.setAllowLoadingOnAttachedOnly(true);
        r05.ignoreNotifications = true;
        r05.setFileLoadingPriority(0);
        ImageReceiver r4 = new ImageReceiver();
        this.rightPreloadImageReceiver = r4;
        r4.setAllowLoadingOnAttachedOnly(true);
        r4.ignoreNotifications = true;
        r4.setFileLoadingPriority(0);
        r11.setPreloadingReceivers(Arrays.asList(new ImageReceiver[]{r05, r4}));
        this.avatarDrawable = new AvatarDrawable();
        this.storyViewer = r27;
        this.sharedResources = r28;
        this.bitmapShaderTools = r28.bitmapShaderTools;
        this.storiesController = MessagesController.getInstance(UserConfig.selectedAccount).getStoriesController();
        SharedResources.m19627$$Nest$fgetdimPaint(r28).setColor(-16777216);
        this.inputBackgroundPaint = new Paint(1);
        Paint r06 = new Paint(1);
        this.inputTopBorderPaint = r06;
        Paint.Style r42 = Paint.Style.STROKE;
        r06.setStyle(r42);
        this.inputTopBorderPaint.setColor(687865855);
        Paint r07 = new Paint(1);
        this.inputBottomBorderPaint = r07;
        r07.setStyle(r42);
        this.inputBottomBorderPaint.setColor(352321535);
        this.resourcesProvider = r29;
        setClipChildren(false);
        this.storyAreasView = new AnonymousClass3(this, r26, this.storyContainer, r29, r27);
        this.blurredBackgroundColorProvider = new BlurredBackgroundColorProviderThemed(r29, Theme.key_chat_messagePanelBackground, 0.8f);
        BlurredBackgroundSourceColor r08 = new BlurredBackgroundSourceColor();
        this.blurredBackgroundSourceFallback = r08;
        r08.setColor(ColorUtils.blendARGB(-16777216, -1, 0.2f));
        if (Build.VERSION.SDK_INT >= 31) goto L5;
    L7:
        this.blurredBackgroundSourceRenderNodeWithSaturation = null;
        this.blurredBackgroundSourceWithSaturation = r08;
    L8:
        BlurredBackgroundDrawableViewFactory r09 = new BlurredBackgroundDrawableViewFactory(new ViewPositionWatcher(this), this, this.blurredBackgroundSourceWithSaturation);
        this.blurredBackgroundDrawableFactory = r09;
        this.inputFieldBackground = r09.create(this, this.blurredBackgroundColorProvider);
        BlurredBackgroundDrawable r010 = this.blurredBackgroundDrawableFactory.create(this, this.blurredBackgroundColorProvider);
        this.emojiKeyboardBackground = r010;
        r010.setThickness(AndroidUtilities.dp(32.0f));
        AnonymousClass4 r011 = new AnonymousClass4(this, r26, r28, r27);
        this.storyContainer = r011;
        r011.setClipChildren(false);
        this.emojiAnimationsOverlay = new EmojiAnimationsOverlay(this.storyContainer, this.currentAccount);
        this.storyContainer.addView(this.storyAreasView, LayoutHelper.createFrame(-1, -1.0f));
        AnonymousClass5 r012 = new AnonymousClass5(this, getContext(), r27.resourcesProvider, r27, r29);
        this.storyCaptionView = r012;
        r012.captionTextview.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda7(this));
        ImageView r013 = new ImageView(r26);
        this.shareButton = r013;
        r013.setImageDrawable(r28.shareDrawable);
        int r2 = AndroidUtilities.dp(8.0f);
        r013.setPadding(r2, r2, r2, r2);
        r013.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda8(this));
        r013.setContentDescription(LocaleController.getString(R.string.ShareFile));
        ScaleStateListAnimator.apply(r013);
        if (DISABLE_STORY_REPOSTING == true) goto L14;
        ImageView r43 = new ImageView(r26);
        this.repostButton = r43;
        r43.setImageDrawable(r28.repostDrawable);
        this.repostButton.setPadding(r2, r2, r2, r2);
        AnonymousClass6 r44 = new AnonymousClass6(this, getContext());
        this.repostButtonContainer = r44;
        AnimatedTextView.AnimatedTextDrawable r5 = this.repostCounter;
        if (r5 == null) goto L13;
        r5.setCallback(r44);
    L13:
        this.repostButtonContainer.setWillNotDraw(false);
        this.repostButtonContainer.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda9(this));
    L14:
        AnonymousClass7 r45 = new AnonymousClass7(this, getContext());
        this.likeButtonContainer = r45;
        AnimatedTextView.AnimatedTextDrawable r52 = this.reactionsCounter;
        if (r52 == null) goto L17;
        r52.setCallback(r45);
    L17:
        r45.setWillNotDraw(false);
        r45.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda10(this));
        r45.setOnLongClickListener(new PeerStoriesView$$ExternalSyntheticLambda11(this, r27));
        StoriesLikeButton r53 = new StoriesLikeButton(r26, r28);
        this.storiesLikeButton = r53;
        r53.setPadding(r2, r2, r2, r2);
        r45.addView(this.storiesLikeButton, LayoutHelper.createFrame(40, 40, 3));
        FrameLayout r22 = this.repostButtonContainer;
        if (r22 == null) goto L20;
        r22.addView(this.repostButton, LayoutHelper.createFrame(40, 40, 3));
    L20:
        ScaleStateListAnimator.apply(r45, 0.3f, 5.0f);
        FrameLayout r13 = this.repostButtonContainer;
        if (r13 == null) goto L23;
        ScaleStateListAnimator.apply(r13, 0.3f, 5.0f);
    L23:
        r11.setAllowLoadingOnAttachedOnly(true);
        r11.setParentView(this.storyContainer);
        RoundRectOutlineProvider r23 = new RoundRectOutlineProvider(10);
        this.outlineProvider = r23;
        this.storyContainer.setOutlineProvider(r23);
        this.storyContainer.setClipToOutline(true);
        addView(this.storyContainer);
        PeerHeaderView r24 = new PeerHeaderView(r26, r9);
        this.headerView = r24;
        r24.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda12(this, r27));
        this.storyContainer.addView(this.headerView, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 17.0f, 0.0f, 0.0f));
        this.topBulletinContainer = new FrameLayout(r26);
        LayoutTransition r25 = new LayoutTransition();
        r25.setDuration(150);
        r25.disableTransitionType(2);
        r25.enableTransitionType(4);
        LinearLayout r7 = new LinearLayout(r26);
        this.bottomActionsLinearLayout = r7;
        r7.setOrientation(0);
        r7.setLayoutTransition(r25);
        r7.addView(r013, LayoutHelper.createLinear(40, 40, 5));
        FrameLayout r8 = this.repostButtonContainer;
        if (r8 == null) goto L26;
        r7.addView(r8, LayoutHelper.createLinear(40, 40, 5));
    L26:
        r7.addView(r45, LayoutHelper.createLinear(40, 40, 5));
        addView(r7, LayoutHelper.createFrame(-2, -2.0f, 5, 0.0f, 0.0f, 4.0f, 0.0f));
        ImageView r82 = new ImageView(r26);
        this.optionsIconView = r82;
        r82.setImageDrawable(r28.optionsDrawable);
        r82.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        r82.setBackground(Theme.createSelectorDrawable(-1));
        r82.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        this.storyContainer.addView(r82, LayoutHelper.createFrame(40, 40.0f, 53, 2.0f, 15.0f, 2.0f, 0.0f));
        ImageView r92 = new ImageView(r26);
        this.pipIconView = r92;
        r92.setImageDrawable(r28.pipDrawable);
        r92.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        r92.setBackground(Theme.createSelectorDrawable(-1));
        r92.setContentDescription(LocaleController.getString(R.string.AccDescrPipMode));
        this.storyContainer.addView(r92, LayoutHelper.createFrame(40, 40.0f, 53, 2.0f, 15.0f, 42.0f, 0.0f));
        r92.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda13(r27));
        r82.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda14(this, r29, r27, r26, r28));
        AnonymousClass9 r15 = new AnonymousClass9(this, r26);
        this.muteIconContainer = r15;
        this.storyContainer.addView(r15, LayoutHelper.createFrame(40, 40.0f, 53, 2.0f, 15.0f, 42.0f, 0.0f));
        RLottieImageView r014 = new RLottieImageView(r26);
        this.muteIconView = r014;
        r014.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));
        r15.addView(r014);
        ImageView r015 = new ImageView(r26);
        this.noSoundIconView = r015;
        r015.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));
        r015.setImageDrawable(r28.noSoundDrawable);
        r15.addView(r015);
        r015.setVisibility(8);
        StoryPrivacyButton r016 = new StoryPrivacyButton(r26);
        this.privacyButton = r016;
        r016.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda4(this));
        this.storyContainer.addView(r016, LayoutHelper.createFrame(60, 40.0f, 53, 2.0f, 15.0f, 42.0f, 0.0f));
        r15.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda5(this, r27));
        this.storyLines = new StoryLinesDrawable(this, r28);
        this.storyContainer.addView(r012, LayoutHelper.createFrame(-1, -1.0f, 0, 0.0f, 64.0f, 0.0f, 0.0f));
        View r54 = new View(r26);
        this.liveCommentsShadowView = r54;
        r54.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0, -16777216}));
        AnonymousClass10 r017 = new AnonymousClass10(this, r26, r27, r27.containerView, r54, this.topBulletinContainer, r27);
        this.liveCommentsView = r017;
        this.storyContainer.addView(r54, LayoutHelper.createFrame(-1, 200, 87));
        this.storyContainer.addView(r017, LayoutHelper.createFrame(-1, -1.0f, 0, 0.0f, 64.0f, 0.0f, 0.0f));
        this.storyContainer.addView(this.topBulletinContainer, LayoutHelper.createFrame(-1, 100.0f, 0, 0.0f, 55.0f, 0.0f, 0.0f));
        r15.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(20.0f), 0, ColorUtils.setAlphaComponent(-1, 100)));
        r82.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(20.0f), 0, ColorUtils.setAlphaComponent(-1, 100)));
        r92.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(20.0f), 0, ColorUtils.setAlphaComponent(-1, 100)));
        r013.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(20.0f), 0, ColorUtils.setAlphaComponent(-1, 100)));
        r45.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(20.0f), 0, ColorUtils.setAlphaComponent(-1, 100)));
        FrameLayout r3 = this.repostButtonContainer;
        if (r3 == null) goto L29;
        r3.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(20.0f), 0, ColorUtils.setAlphaComponent(-1, 100)));
    L29:
        View r018 = r012.textSelectionHelper.getOverlayView(r26);
        if (r018 == null) goto L32;
        AndroidUtilities.removeFromParent(r018);
        addView(r018);
    L32:
        r012.textSelectionHelper.setCallback(new AnonymousClass11(this));
        r012.textSelectionHelper.setParentView(this);
        return;
    L5:
        if (SharedConfig.canBlurChat() == false) goto L7;
        BlurredBackgroundSourceRenderNode r46 = new BlurredBackgroundSourceRenderNode(r08);
        this.blurredBackgroundSourceRenderNodeWithSaturation = r46;
        r46.setBlur(AndroidUtilities.dp(8.0f));
        this.blurredBackgroundSourceWithSaturation = r46;
        goto L8
    }

    private void afterMessageSend(boolean r9) {
        InstantCameraView r0 = this.instantCameraView;
        if (r0 == null) goto L5;
        r0.resetCameraFile();
        this.instantCameraView.cancel(false);
    L5:
        this.storyViewer.clearDraft(this.dialogId, this.currentStory.storyItem);
        this.messageSent = true;
        this.storyViewer.closeKeyboardOrEmoji();
        if (r9 == false) goto L10;
        BulletinFactory r2 = BulletinFactory.of(this.storyContainer, this.resourcesProvider);
        if (r2 == null) goto L10;
        r2.createSimpleBulletin(R.raw.forward, LocaleController.getString(R.string.MessageSent), LocaleController.getString(R.string.ViewInChat), 5000, new PeerStoriesView$$ExternalSyntheticLambda20(this)).hideAfterBottomSheet(false).show(false);
    L10:
        MessagesController.getInstance(this.currentAccount).ensureMessagesLoaded(this.dialogId, 0, null);
    }

    private void animateLikeButton() {
        StoriesLikeButton r0 = this.storiesLikeButton;
        r0.animate().alpha(0.0f).scaleX(0.8f).scaleY(0.8f).setListener(new AnonymousClass16(this, r0)).setDuration(150).start();
        int r02 = AndroidUtilities.dp(8.0f);
        StoriesLikeButton r1 = new StoriesLikeButton(getContext(), this.sharedResources);
        this.storiesLikeButton = r1;
        r1.setPadding(r02, r02, r02, r02);
        this.storiesLikeButton.setAlpha(0.0f);
        this.storiesLikeButton.setScaleX(0.8f);
        this.storiesLikeButton.setScaleY(0.8f);
        this.storiesLikeButton.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(150);
        this.likeButtonContainer.addView(this.storiesLikeButton, LayoutHelper.createFrame(40, 40, 3));
        this.drawReactionEffect = false;
    }

    private boolean applyMessageToChat(Runnable r5) {
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L6;
        AccountFrozenAlert.show(this.currentAccount);
        return true;
    L6:
        int r0 = SharedConfig.stealthModeSendMessageConfirm;
        if (r0 > 0) goto L9;
    L11:
        r5.run();
    L12:
        return true;
    L9:
        if (this.stealthModeIsActive == false) goto L11;
        int r02 = r0 - 1;
        SharedConfig.stealthModeSendMessageConfirm = r02;
        SharedConfig.updateStealthModeSendMessageConfirm(r02);
        AlertDialog r03 = new AlertDialog(getContext(), 0, this.resourcesProvider);
        r03.setTitle(LocaleController.getString(R.string.StealthModeConfirmTitle));
        r03.setMessage(LocaleController.getString(R.string.StealthModeConfirmMessage));
        r03.setPositiveButton(LocaleController.getString(R.string.Proceed), new PeerStoriesView$$ExternalSyntheticLambda38(r5));
        r03.setNegativeButton(LocaleController.getString(R.string.Cancel), new PeerStoriesView$$ExternalSyntheticLambda39());
        r03.show();
        goto L12
    }

    private void bindInternal(int r14) {
        this.deletedPeer = false;
        this.forceUpdateOffsets = true;
        this.userCanSeeViews = false;
        this.isChannel = false;
        this.isGroup = false;
        long r2 = this.dialogId;
        if (r2 >= 0) goto L5;
        this.isSelf = false;
        this.isChannel = true;
        if (this.storiesController.canEditStories(r2) == false) goto L18;
    L19:
        this.userCanSeeViews = true;
    L20:
        TLRPC.Chat r22 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        boolean r3 = ChatObject.isChannelAndNotMegaGroup(r22);
        this.isGroup = !r3;
        if (r3 == true) goto L26;
        if (MessagesController.getInstance(this.currentAccount).getChatFull(-this.dialogId) != null) goto L26;
        MessagesStorage.getInstance(this.currentAccount).loadChatInfo(-this.dialogId, true, new CountDownLatch(1), false, false);
    L26:
        if (this.isGroup == true) goto L28;
    L30:
        boolean r32 = false;
    L31:
        this.isPremiumBlocked = r32;
        this.starsPriceBlocked = MessagesController.getInstance(this.currentAccount).getSendPaidMessagesStars(this.dialogId);
        this.avatarDrawable.setInfo(this.currentAccount, r22);
        this.headerView.backupImageView.getImageReceiver().setForUserOrChat(r22, this.avatarDrawable);
        setTitle(true, this.dialogId, false);
    L33:
        if (this.isActive == true) goto L35;
    L39:
        updateStoryItems();
        this.selectedPosition = r14;
        if (r14 >= 0) goto L42;
        this.selectedPosition = 0;
    L42:
        this.currentImageTime = 0;
        this.switchEventSent = false;
        this.boostsStatus = null;
        this.canApplyBoost = null;
        int r5 = 8;
        if (this.isChannel == false) goto L87;
        createSelfPeerView();
        if (this.chatActivityEnterView != null) goto L52;
        if (this.isGroup == false) goto L49;
    L50:
        createEnterView();
        goto L52
    L49:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L50;
    L52:
        if (this.chatActivityEnterView == null) goto L76;
        TLRPC.Chat r33 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        ChatActivityEnterView r6 = this.chatActivityEnterView;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L56;
    L63:
        r5 = 0;
    L64:
        r6.setVisibility(r5);
        this.chatActivityEnterView.setLiveComment(StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory), disabledPaidFeatures(true));
        ChatActivityEnterView r52 = this.chatActivityEnterView;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L67;
    L73:
        boolean r62 = false;
    L74:
        r52.setSuggestionButtonVisible(r62, true);
        this.chatActivityEnterView.getEditField().setText(this.storyViewer.getDraft(this.dialogId, this.currentStory.storyItem));
        this.chatActivityEnterView.setDialogId(this.dialogId, this.currentAccount);
        this.chatActivityEnterView.updateRecordButton(r33, null);
        goto L76
    L67:
        if (disabledPaidFeatures(true) == true) goto L73;
        if (this.keyboardVisible == false) goto L71;
    L72:
        r62 = true;
        goto L74
    L71:
        if (this.chatActivityEnterView.emojiViewVisible == false) goto L73;
    L56:
        if (isBotsPreview() == true) goto L64;
        if (this.isGroup == false) goto L64;
        if (ChatObject.canSendPlain(r33) == true) goto L63;
        if (ChatObject.isPossibleRemoveChatRestrictionsByBoosts(r33) == false) goto L64;
    L76:
        if (this.reactionsCounter != null) goto L79;
        AnimatedTextView.AnimatedTextDrawable r1 = new AnimatedTextView.AnimatedTextDrawable();
        this.reactionsCounter = r1;
        r1.setCallback(this.likeButtonContainer);
        this.reactionsCounter.setTextColor(this.resourcesProvider.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.reactionsCounter.setTextSize(AndroidUtilities.dp(14.0f));
        this.reactionsCounterProgress = new AnimatedFloat(this.likeButtonContainer);
    L79:
        if (this.repostButtonContainer != null) goto L81;
    L83:
        if (r14 != (-1)) goto L85;
        updateSelectedPosition();
    L85:
        updatePosition();
        this.count = getStoriesCount();
        this.storyContainer.invalidate();
        invalidate();
    L193:
        checkStealthMode(false);
        return;
    L81:
        if (this.repostCounter != null) goto L83;
        AnimatedTextView.AnimatedTextDrawable r12 = new AnimatedTextView.AnimatedTextDrawable();
        this.repostCounter = r12;
        r12.setCallback(this.repostButtonContainer);
        this.repostCounter.setTextColor(this.resourcesProvider.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.repostCounter.setTextSize(AndroidUtilities.dp(14.0f));
        this.repostCounterProgress = new AnimatedFloat(this.repostButtonContainer);
        goto L83
    L87:
        if (this.isSelf == false) goto L139;
        createSelfPeerView();
        boolean r23 = StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory);
        FrameLayout r34 = this.selfView;
        if (r23 == false) goto L94;
        r34.setVisibility(8);
        if (this.chatActivityEnterView != null) goto L93;
        createEnterView();
    L93:
        this.chatActivityEnterView.setVisibility(0);
    L97:
        ChatActivityEnterView r24 = this.chatActivityEnterView;
        if (r24 == null) goto L110;
        r24.setLiveComment(StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory), disabledPaidFeatures(true));
        ChatActivityEnterView r25 = this.chatActivityEnterView;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L102;
    L108:
        boolean r35 = false;
    L109:
        r25.setSuggestionButtonVisible(r35, true);
        goto L110
    L102:
        if (disabledPaidFeatures(true) == true) goto L108;
        if (this.keyboardVisible == false) goto L106;
    L107:
        r35 = true;
        goto L109
    L106:
        if (this.chatActivityEnterView.emojiViewVisible == false) goto L108;
    L110:
        if (r14 != (-1)) goto L137;
        ArrayList<Integer> r142 = this.day;
        if (r142 == null) goto L125;
        int r143 = r142.indexOf(Integer.valueOf(this.storyViewer.dayStoryId));
        if (r143 < 0) goto L116;
    L123:
        this.selectedPosition = Math.max(0, r143);
        goto L137
    L116:
        if (this.day.isEmpty() == true) goto L123;
        if (this.storyViewer.dayStoryId <= this.day.get(0).intValue()) goto L120;
        r143 = 0;
        goto L123
    L120:
        int r26 = this.storyViewer.dayStoryId;
        ArrayList<Integer> r36 = this.day;
        if (r26 >= r36.get(r36.size() - 1).intValue()) goto L123;
        r143 = this.day.size() - 1;
        goto L123
    L125:
        if (this.uploadingStories.isEmpty() == true) goto L127;
        this.selectedPosition = this.storyItems.size();
        goto L137
    L127:
        int r144 = 0;
    L129:
        if (r144 >= this.storyItems.size()) goto L137;
        if (this.storyItems.get(r144).justUploaded == true) goto L136;
        if (this.storyItems.get(r144).id > this.storiesController.dialogIdToMaxReadId.get(this.dialogId)) goto L136;
        r144 = r144 + 1;
    L136:
        this.selectedPosition = r144;
    L137:
        updatePosition();
        this.storyContainer.invalidate();
        invalidate();
        goto L193
    L94:
        r34.setVisibility(0);
        ChatActivityEnterView r27 = this.chatActivityEnterView;
        if (r27 == null) goto L97;
        r27.setVisibility(8);
        goto L97
    L139:
        if (this.chatActivityEnterView != null) goto L142;
        createEnterView();
    L142:
        if (this.isPremiumBlocked == false) goto L147;
        if (this.premiumBlockedText != null) goto L147;
        createPremiumBlockedText();
    L147:
        if (this.premiumBlockedText != null) goto L149;
    L162:
        StoryFailView r37 = this.failView;
        if (r37 == null) goto L165;
        r37.setVisibility(8);
    L165:
        if (r14 != (-1)) goto L167;
        updateSelectedPosition();
    L167:
        updatePosition();
        ChatActivityEnterView r145 = this.chatActivityEnterView;
        if (r145 != null) goto L170;
    L189:
        this.count = getStoriesCount();
        FrameLayout r146 = this.selfView;
        if (r146 == null) goto L192;
        r146.setVisibility(8);
    L192:
        this.storyContainer.invalidate();
        invalidate();
        goto L193
    L170:
        if (isBotsPreview() == false) goto L172;
    L174:
        int r38 = 8;
    L175:
        r145.setVisibility(r38);
        this.chatActivityEnterView.setLiveComment(StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory), disabledPaidFeatures(true));
        ChatActivityEnterView r147 = this.chatActivityEnterView;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L178;
    L184:
        boolean r39 = false;
    L185:
        r147.setSuggestionButtonVisible(r39, true);
        this.chatActivityEnterView.getEditField().setText(this.storyViewer.getDraft(this.dialogId, this.currentStory.storyItem));
        this.chatActivityEnterView.setDialogId(this.dialogId, this.currentAccount);
        TLRPC.UserFull r148 = MessagesController.getInstance(this.currentAccount).getUserFull(this.dialogId);
        if (r148 == null) goto L188;
        this.chatActivityEnterView.updateRecordButton(null, r148);
        goto L189
    L188:
        MessagesController.getInstance(this.currentAccount).loadFullUser(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.dialogId)), this.classGuid, false);
        goto L189
    L178:
        if (disabledPaidFeatures(true) == true) goto L184;
        if (this.keyboardVisible == false) goto L182;
    L183:
        r39 = true;
        goto L185
    L182:
        if (this.chatActivityEnterView.emojiViewVisible == false) goto L184;
    L172:
        if (UserObject.isService(this.dialogId) == true) goto L174;
        r38 = 0;
        goto L175
    L149:
        if (this.isPremiumBlocked == false) goto L151;
    L152:
        updatePremiumBlockedText();
    L153:
        LinearLayout r310 = this.premiumBlockedText;
        if (this.isPremiumBlocked == false) goto L158;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L158;
    L159:
        int r63 = 0;
    L161:
        r310.setVisibility(r63);
    L158:
        if (this.areLiveCommentsDisabled == true) goto L159;
        r63 = 8;
        goto L161
    L151:
        if (this.areLiveCommentsDisabled == false) goto L153;
    L35:
        if (this.isSelf == false) goto L37;
    L38:
        this.storiesController.pollViewsForSelfStories(this.dialogId, true);
        goto L39
    L37:
        if (this.isChannel == false) goto L39;
    L28:
        if (ChatObject.canSendPlain(r22) == true) goto L30;
        r32 = true;
        goto L31
    L18:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L20;
    L5:
        if (r2 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L7;
        boolean r28 = true;
    L8:
        this.isSelf = r28;
        TLRPC.User r29 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.dialogId));
        TL_account.RequirementToContact r311 = MessagesController.getInstance(this.currentAccount).isUserContactBlocked(this.dialogId);
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L11;
    L13:
        boolean r64 = false;
    L14:
        this.isPremiumBlocked = r64;
        this.starsPriceBlocked = DialogObject.getMessagesStarsPrice(r311);
        this.avatarDrawable.setInfo(this.currentAccount, r29);
        this.headerView.backupImageView.getImageReceiver().setForUserOrChat(r29, this.avatarDrawable);
        setTitle(true, this.dialogId, false);
        goto L33
    L11:
        if (DialogObject.isPremiumBlocked(r311) == false) goto L13;
        r64 = true;
        goto L14
    L7:
        r28 = false;
        goto L8
    }

    private void cancelWaiting() {
        Runnable r0 = this.cancellableViews;
        if (r0 == null) goto L5;
        r0.run();
        this.cancellableViews = null;
    L5:
        this.showViewsProgress = false;
        if (this.isActive == false) goto L9;
        this.delegate.setIsWaiting(false);
        return;
    }

    private void checkInstantCameraView() {
        if (this.instantCameraView == null) goto L5;
        return;
    L5:
        if (CameraView.isCameraAllowed() == false) goto L10;
        this.instantCameraView = new InstantCameraView(getContext(), new AnonymousClass36(this), this.resourcesProvider, false);
        int r0 = Math.min(indexOfChild(this.chatActivityEnterView.getRecordCircle()), indexOfChild(this.chatActivityEnterView.controlsView));
        addView(this.instantCameraView, r0, LayoutHelper.createFrame(-1, -1, 51));
        return;
    }

    private void checkStealthMode(boolean r10) {
        if (this.chatActivityEnterView != null) goto L5;
        return;
    L5:
        if (this.isVisible == true) goto L7;
        return;
    L7:
        if (this.attachedToWindow == false) goto L51;
        AndroidUtilities.cancelRunOnUIThread(this.updateStealthModeTimer);
        TL_stories.TL_storiesStealthMode r1 = this.storiesController.getStealthMode();
        this.chatActivityEnterView.checkSendButton(true);
        if (this.isPremiumBlocked == false) goto L14;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L14;
    L15:
        this.stealthModeIsActive = false;
        this.chatActivityEnterView.setEnabled(false);
        this.chatActivityEnterView.setOverrideHint(" ", r10);
        return;
    L14:
        if (this.areLiveCommentsDisabled == true) goto L15;
        if (this.starsPriceBlocked <= 0) goto L22;
        this.stealthModeIsActive = false;
        this.chatActivityEnterView.setEnabled(true);
        this.chatActivityEnterView.setOverrideHint(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.TypeMessageForStars, new Object[]{LocaleController.formatNumber(this.starsPriceBlocked, ',')})), r10);
        return;
    L22:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L32;
        if (r1 == null) goto L32;
        int r2 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        int r12 = r1.active_until_date;
        if (r2 >= r12) goto L32;
        this.stealthModeIsActive = true;
        int r13 = r12 - ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        int r22 = r13 / 60;
        int r14 = r13 % 60;
        int r4 = R.string.StealthModeActiveHintShort;
        Locale r5 = Locale.US;
        int r0 = (int) this.chatActivityEnterView.getEditField().getPaint().measureText(LocaleController.formatString(r4, new Object[]{String.format(r5, "%02d:%02d", new Object[]{99, 99})}));
        this.chatActivityEnterView.setEnabled(true);
        float r02 = r0 * 1.2f;
        float r3 = this.chatActivityEnterView.getEditField().getMeasuredWidth();
        ChatActivityEnterView r32 = this.chatActivityEnterView;
        if (r02 < r3) goto L29;
        r32.setOverrideHint(LocaleController.formatString(R.string.StealthModeActiveHintShort, new Object[]{_UrlKt.FRAGMENT_ENCODE_SET}), String.format(r5, "%02d:%02d", new Object[]{Integer.valueOf(r22), Integer.valueOf(r14)}), r10);
    L30:
        AndroidUtilities.runOnUIThread(this.updateStealthModeTimer, 1000);
        return;
    L29:
        r32.setOverrideHint(LocaleController.formatString(R.string.StealthModeActiveHint, new Object[]{String.format(r5, "%02d:%02d", new Object[]{Integer.valueOf(r22), Integer.valueOf(r14)})}), r10);
    L32:
        this.stealthModeIsActive = false;
        this.chatActivityEnterView.setEnabled(true);
        boolean r03 = StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory);
        ChatActivityEnterView r15 = this.chatActivityEnterView;
        if (r03 == false) goto L43;
        long r04 = r15.getStarsPrice();
        ChatActivityEnterView r33 = this.chatActivityEnterView;
        if (r04 <= 0) goto L40;
        r33.setOverrideHint(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.CommentFor, new Object[]{LocaleController.formatNumber((int) r04, ',')}), this.chatActivityEnterView.spans), r10);
        ColoredImageSpan r9 = this.chatActivityEnterView.spans[0];
        if (r9 == null) goto L50;
        r9.spaceScaleX = 0.9f;
        return;
    L50:
        return;
    L40:
        r33.setOverrideHint(LocaleController.getString(R.string.Comment), r10);
        return;
    L43:
        if (this.isGroup == false) goto L45;
        int r92 = R.string.ReplyToGroupStory;
    L46:
        r15.setOverrideHint(LocaleController.getString(r92), r10);
        return;
    L45:
        r92 = R.string.ReplyPrivately;
        goto L46
    }

    private void createChatAttachView() {
        if (this.chatAttachAlert != null) goto L6;
        AnonymousClass23 r1 = new AnonymousClass23(this, getContext(), null, false, false, true, this.resourcesProvider);
        this.chatAttachAlert = r1;
        r1.setDelegate(new AnonymousClass24(this));
        this.chatAttachAlert.getPhotoLayout().loadGalleryPhotos();
        this.chatAttachAlert.setAllowEnterCaption(true);
        this.chatAttachAlert.init();
        this.chatAttachAlert.setDocumentsDelegate(new AnonymousClass25(this));
        this.chatAttachAlert.getCommentView().setText(this.chatActivityEnterView.getFieldText());
        return;
    }

    private void createCommentButton() {
        if (this.commentButton == null) goto L5;
        return;
    L5:
        if (getContext() == null) goto L10;
        CommentButton r0 = new CommentButton(getContext(), this.blurredBackgroundColorProvider);
        this.commentButton = r0;
        r0.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda25(this));
        addView(this.commentButton, LayoutHelper.createFrame(46, 42.0f, 83, 7.0f, 0.0f, 7.0f, 3.0f));
        return;
    }

    private void createEnterView() {
        AnonymousClass18 r6 = new AnonymousClass18(this, this.resourcesProvider);
        AnonymousClass19 r0 = new AnonymousClass19(this, AndroidUtilities.findActivity(getContext()), this, null, true, r6);
        this.chatActivityEnterView = r0;
        r0.getEditField().useAnimatedTextDrawable();
        this.chatActivityEnterView.getEditField().setScaleX(0.0f);
        this.chatActivityEnterView.setOverrideKeyboardAnimation(true);
        this.chatActivityEnterView.setClipChildren(false);
        this.chatActivityEnterView.setDelegate(new AnonymousClass20(this));
        setDelegate(this.chatActivityEnterView);
        ChatActivityEnterView r10 = this.chatActivityEnterView;
        r10.shouldDrawBackground = false;
        r10.shouldDrawRecordedAudioPanelInParent = true;
        boolean r102 = StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory);
        ChatActivityEnterView r3 = this.chatActivityEnterView;
        if (r102 == false) goto L5;
        r3.setAllowStickersAndGifs(true, false, false);
    L6:
        this.chatActivityEnterView.updateColors();
        ChatActivityEnterView r103 = this.chatActivityEnterView;
        r103.isStories = true;
        addView(r103, LayoutHelper.createFrame(-1, -2.0f, 83, 7.0f, 0.0f, 7.0f, 0.0f));
        if (this.sendAsPeersObj == null) goto L9;
        this.chatActivityEnterView.updateSendAsButton(false);
    L9:
        ChatActivityEnterView r104 = this.chatActivityEnterView;
        r104.recordingGuid = this.classGuid;
        this.playerSharedScope.viewsToInvalidate.add(this.storyContainer);
        this.playerSharedScope.viewsToInvalidate.add(this);
        if (this.attachedToWindow == false) goto L12;
        this.chatActivityEnterView.onResume();
    L12:
        checkStealthMode(false);
        if (isBotsPreview() == false) goto L15;
        this.chatActivityEnterView.setVisibility(8);
    L15:
        ChatActivitySideControlsButtonsLayout r105 = new ChatActivitySideControlsButtonsLayout(getContext(), this.resourcesProvider, this.blurredBackgroundColorProvider, this.blurredBackgroundDrawableFactory);
        this.sideControlsButtonsLayout = r105;
        r105.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda35(this));
        addView(this.sideControlsButtonsLayout, LayoutHelper.createFrame(57, 300, 85));
        this.sideControlsButtonsLayout.setVisibility(8);
        this.chatActivityEnterView.setSideButtonsForAttach(this.sideControlsButtonsLayout);
        this.reactionsContainerIndex = getChildCount();
        return;
    L5:
        r3.setAllowStickersAndGifs(true, true, true);
        goto L6
    }

    private void createFailView() {
        if (this.failView == null) goto L5;
        return;
    L5:
        StoryFailView r0 = new StoryFailView(getContext(), this.resourcesProvider);
        this.failView = r0;
        r0.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda30(this));
        this.failView.setAlpha(0.0f);
        this.failView.setVisibility(8);
        addView(this.failView, LayoutHelper.createFrame(-1, -2.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
    }

    private void createMentionsContainer() {
        this.mentionContainer = new AnonymousClass21(this, getContext(), this.dialogId, 0, this.storyViewer.fragment, this.resourcesProvider);
        AnonymousClass22 r9 = new AnonymousClass22(this);
        this.mentionsDelegate = r9;
        this.mentionContainer.withDelegate(r9);
        addView(this.mentionContainer, LayoutHelper.createFrame(-1, -1, 83));
    }

    private void createMuteButton() {
        if (this.muteButton == null) goto L5;
        return;
    L5:
        if (getContext() == null) goto L22;
        MuteButton r0 = new MuteButton(getContext(), this.blurredBackgroundColorProvider);
        this.muteButton = r0;
        r0.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda33(this));
        MuteButton r02 = this.muteButton;
        LivePlayer r1 = LivePlayer.recording;
        boolean r2 = true;
        if (r1 != null) goto L10;
    L12:
        boolean r12 = false;
    L13:
        r02.setMuted(r12, false);
        MuteButton r03 = this.muteButton;
        LivePlayer r13 = LivePlayer.recording;
        if (r13 != null) goto L16;
    L19:
        r03.setConnected(r2, false);
        addView(this.muteButton, LayoutHelper.createFrame(46, 42.0f, 85, 7.0f, 0.0f, 7.0f, 3.0f));
        return;
    L16:
        if (r13.isConnected() == true) goto L19;
        r2 = false;
        goto L19
    L10:
        if (r1.isMuted() == false) goto L12;
        r12 = true;
        goto L13
    }

    private void createPaidReactionsButton() {
        if (this.starsButton == null) goto L5;
        return;
    L5:
        if (getContext() == null) goto L10;
        this.starsButtonEffectsView = new PaidReactionButton.PaidReactionButtonEffectsView(getContext(), this.currentAccount);
        PaidReactionButton r0 = new PaidReactionButton(getContext(), this.starsButtonEffectsView, this.blurredBackgroundColorProvider);
        this.starsButton = r0;
        r0.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda36(this));
        this.starsButton.setOnLongClickListener(new PeerStoriesView$$ExternalSyntheticLambda37(this));
        addView(this.starsButton, LayoutHelper.createFrame(46, 42.0f, 85, 7.0f, 0.0f, 7.0f, 3.0f));
        addView(this.starsButtonEffectsView, LayoutHelper.createFrame(200, 200.0f, 85, 0.0f, 0.0f, 0.0f, 0.0f));
        return;
    }

    private void createPremiumBlockedText() {
        if (this.premiumBlockedText == null) goto L6;
        return;
    L6:
        if (this.chatActivityEnterView != null) goto L8;
        createEnterView();
    L8:
        LinearLayout r0 = new LinearLayout(getContext());
        this.premiumBlockedText = r0;
        r0.setOrientation(0);
        ImageView r02 = new ImageView(getContext());
        r02.setScaleType(ImageView.ScaleType.CENTER);
        r02.setScaleX(1.35f);
        r02.setScaleY(1.35f);
        r02.setImageResource(R.drawable.mini_switch_lock);
        r02.setColorFilter(new PorterDuffColorFilter(-8026747, PorterDuff.Mode.SRC_IN));
        TextView r2 = new TextView(getContext());
        this.premiumBlockedText1 = r2;
        r2.setTextColor(-8026747);
        this.premiumBlockedText1.setTextSize(1, 16.0f);
        TextView r22 = this.premiumBlockedText1;
        if (this.isGroup == false) goto L11;
        int r3 = R.string.StoryGroupRepliesLocked;
    L12:
        r22.setText(LocaleController.getString(r3));
        TextView r23 = new TextView(getContext());
        this.premiumBlockedText2 = r23;
        r23.setTextColor(-1);
        this.premiumBlockedText2.setTextSize(1, 12.0f);
        this.premiumBlockedText2.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(40.0f), 452984831, 855638015));
        this.premiumBlockedText2.setGravity(17);
        ScaleStateListAnimator.apply(this.premiumBlockedText2);
        this.premiumBlockedText2.setText(LocaleController.getString(R.string.StoryRepliesLockedButton));
        this.premiumBlockedText2.setPadding(AndroidUtilities.dp(7.0f), 0, AndroidUtilities.dp(7.0f), 0);
        this.premiumBlockedText.addView(r02, LayoutHelper.createLinear(22, 22, 16, 12, 1, 4, 0));
        this.premiumBlockedText.addView(this.premiumBlockedText1, LayoutHelper.createLinear(-2, -2, 16, 0.0f, -0.33f, 0.0f, 0.0f));
        this.premiumBlockedText.addView(this.premiumBlockedText2, LayoutHelper.createLinear(-2, 19, 16, 5.0f, -0.33f, 0.0f, 0.0f));
        this.chatActivityEnterView.addView(this.premiumBlockedText, LayoutHelper.createFrame(-1, -1.0f, 119, 14.0f, 0.0f, 8.0f, 0.0f));
        return;
    L11:
        r3 = R.string.StoryRepliesLocked;
        goto L12
    }

    private void createQualityItem(ActionBarPopupWindow.ActionBarPopupWindowLayout r6) {
        boolean r0 = MessagesController.getInstance(this.currentAccount).storyQualityFull;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L12;
        if (r0 == false) goto L6;
        int r1 = R.drawable.menu_quality_sd;
    L7:
        if (r0 == false) goto L9;
        int r2 = R.string.StoryQualityDecrease;
    L10:
        ActionBarMenuItem.addItem(r6, r1, LocaleController.getString(r2), false, this.resourcesProvider).setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda52(this, r0));
        return;
    L9:
        r2 = R.string.StoryQualityIncrease;
        goto L10
    L6:
        r1 = R.drawable.menu_quality_hd;
        goto L7
    }

    private void createReplyDisabledView() {
        if (this.replyDisabledTextView == null) goto L5;
        return;
    L5:
        AnonymousClass33 r0 = new AnonymousClass33(this, getContext());
        this.replyDisabledTextView = r0;
        r0.setTextSize(1, 14.0f);
        this.replyDisabledTextView.setTextColor(ColorUtils.blendARGB(-16777216, -1, 0.5f));
        this.replyDisabledTextView.setGravity(19);
        this.replyDisabledTextView.setText(LocaleController.getString(R.string.StoryReplyDisabled));
        addView(this.replyDisabledTextView, LayoutHelper.createFrame(-2, 40.0f, 3, 16.0f, 0.0f, 16.0f, 0.0f));
    }

    private void createSelfPeerView() {
        if (this.selfView == null) goto L5;
        return;
    L5:
        AnonymousClass30 r0 = new AnonymousClass30(this, getContext());
        this.selfView = r0;
        r0.setClickable(true);
        addView(this.selfView, LayoutHelper.createFrame(-1, 48.0f, 48, 0.0f, 0.0f, 136.0f, 0.0f));
        AnonymousClass31 r02 = new AnonymousClass31(this, getContext());
        this.selfAvatarsContainer = r02;
        r02.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda31(this));
        this.selfView.addView(this.selfAvatarsContainer, LayoutHelper.createFrame(-1, 32.0f, 0, 9.0f, 11.0f, 0.0f, 0.0f));
        HwAvatarsImageView r03 = new HwAvatarsImageView(getContext(), false);
        this.selfAvatarsView = r03;
        r03.setAvatarsTextSize(AndroidUtilities.dp(18.0f));
        this.selfView.addView(this.selfAvatarsView, LayoutHelper.createFrame(-1, 28.0f, 0, 13.0f, 13.0f, 0.0f, 0.0f));
        TextView r04 = new TextView(getContext());
        this.selfStatusView = r04;
        r04.setTextSize(1, 14.0f);
        this.selfStatusView.setTextColor(-1);
        this.selfView.addView(this.selfStatusView, LayoutHelper.createFrame(-2, -2.0f, 0, 0.0f, 16.0f, 0.0f, 9.0f));
        ImageView r05 = new ImageView(getContext());
        r05.setImageDrawable(this.sharedResources.deleteDrawable);
        this.selfAvatarsContainer.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(15.0f), 0, ColorUtils.setAlphaComponent(-1, 120)));
        r05.setBackground(Theme.createCircleSelectorDrawable(ColorUtils.setAlphaComponent(-1, 120), -AndroidUtilities.dp(2.0f), -AndroidUtilities.dp(2.0f)));
    }

    private void createStealthModeItem(ActionBarPopupWindow.ActionBarPopupWindowLayout r5) {
        if (isBotsPreview() == false) goto L5;
        return;
    L5:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L8;
        return;
    L8:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L12;
        ActionBarMenuItem.addItem(r5, R.drawable.msg_stories_stealth2, LocaleController.getString(R.string.StealthModeButton), false, this.resourcesProvider).setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda54(this));
        return;
    }

    private void createUnsupportedContainer() {
        if (this.unsupportedContainer == null) goto L5;
        return;
    L5:
        FrameLayout r0 = new FrameLayout(getContext());
        LinearLayout r1 = new LinearLayout(getContext());
        r1.setOrientation(1);
        TextView r3 = new TextView(getContext());
        r3.setTypeface(AndroidUtilities.bold());
        r3.setGravity(1);
        r3.setTextSize(1, 16.0f);
        r3.setText(LocaleController.getString(R.string.StoryUnsupported));
        r3.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        TextView r5 = new TextView(getContext());
        ScaleStateListAnimator.apply(r5);
        r5.setText(LocaleController.getString(R.string.AppUpdate));
        int r6 = Theme.key_featuredStickers_buttonText;
        r5.setTextColor(Theme.getColor(r6, this.resourcesProvider));
        r5.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(12.0f));
        r5.setGravity(17);
        r5.setTypeface(AndroidUtilities.bold());
        r5.setTextSize(1, 15.0f);
        r5.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), ColorUtils.setAlphaComponent(Theme.getColor(r6, this.resourcesProvider), 30)));
        r5.setOnClickListener(new PeerStoriesView$$ExternalSyntheticLambda28(this));
        r1.addView(r3, LayoutHelper.createLinear(-1, -2));
        r1.addView(r5, LayoutHelper.createLinear(-1, -2, 0.0f, 24.0f, 0.0f, 0.0f));
        r0.addView(r1, LayoutHelper.createFrame(-1, -2.0f, 17, 72.0f, 0.0f, 72.0f, 0.0f));
        this.storyContainer.addView(r0);
        this.unsupportedContainer = r0;
    }

    private void deleteStory() {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        if (isBotsPreview() == false) goto L5;
        int r1 = R.string.DeleteBotPreviewTitle;
    L6:
        r0.setTitle(LocaleController.getString(r1));
        if (isBotsPreview() == false) goto L9;
        int r12 = R.string.DeleteBotPreviewSubtitle;
    L10:
        r0.setMessage(LocaleController.getString(r12));
        r0.setPositiveButton(LocaleController.getString(R.string.Delete), new PeerStoriesView$$ExternalSyntheticLambda55(this));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), new PeerStoriesView$$ExternalSyntheticLambda56());
        AlertDialog r02 = r0.create();
        this.delegate.showDialog(r02);
        r02.redPositive();
        return;
    L9:
        r12 = R.string.DeleteStorySubtitle;
        goto L10
    L5:
        r1 = R.string.DeleteStoryTitle;
        goto L6
    }

    private boolean disabledPaidFeatures(boolean r10) {
        long r0 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        long r2 = this.dialogId;
        if (r2 >= 0) goto L21;
        LivePlayer r6 = this.storyViewer.livePlayer;
        if (r6 == null) goto L21;
        if (r10 == true) goto L8;
        return false;
    L8:
        TLRPC.Peer r102 = r6.getDefaultSendAs();
        TLRPC.Chat r02 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        if (this.storyViewer.livePlayer.isAdmin() == false) goto L11;
    L12:
        if (r102 != null) goto L14;
    L19:
        return true;
    L14:
        if (this.dialogId == DialogObject.getPeerDialogId(r102)) goto L19;
        if (DialogObject.getPeerDialogId(r102) == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L19;
    L18:
        return false;
    L11:
        if (ChatObject.canManageCalls(r02) == false) goto L18;
    L21:
        if (r2 < 0) goto L39;
        StoryViewer r22 = this.storyViewer;
        if (r22 == null) goto L39;
        LivePlayer r23 = r22.livePlayer;
        if (r23 == null) goto L39;
        if (r23.isAdmin() == false) goto L39;
        if (r10 == true) goto L30;
        return true;
    L30:
        TLRPC.Peer r103 = this.storyViewer.livePlayer.getDefaultSendAs();
        if (r103 != null) goto L33;
    L38:
        return true;
    L33:
        if (this.dialogId == DialogObject.getPeerDialogId(r103)) goto L38;
        if (DialogObject.getPeerDialogId(r103) == r0) goto L38;
        if (this.dialogId == r0) goto L38;
    L39:
        return false;
    }

    private boolean drawLinesAsCounter() {
        return false;
    }

    private void editPrivacy(StoryPrivacyBottomSheet.StoryPrivacy r6, TL_stories.StoryItem r7) {
        Delegate r0 = this.delegate;
        Context r2 = getContext();
        if (r7.pinned == false) goto L5;
        int r3 = Integer.MAX_VALUE;
    L6:
        r0.showDialog(new StoryPrivacyBottomSheet(r2, r3, this.resourcesProvider).setValue(r6).enableSharing(false).isEdit(true).whenSelectedRules(new PeerStoriesView$$ExternalSyntheticLambda40(this, r7), false));
        return;
    L5:
        r3 = r7.expire_date - r7.date;
        goto L6
    }

    private Activity findActivity() {
        StoryViewer r0 = this.storyViewer;
        if (r0 == null) goto L7;
        Activity r02 = r0.parentActivity;
        if (r02 == null) goto L7;
    L8:
        if (r02 == null) goto L10;
        return r02;
    L10:
        return LaunchActivity.instance;
    L7:
        r02 = AndroidUtilities.findActivity(getContext());
        goto L8
    }

    private BaseFragment fragmentForLimit() {
        return new AnonymousClass17(this);
    }

    private AccountInstance getAccountInstance() {
        return AccountInstance.getInstance(this.currentAccount);
    }

    private ArrayList<TLRPC.InputStickerSet> getAnimatedEmojiSets(StoryItemHolder r8) {
        if (r8 == null) goto L91;
        HashSet r0 = new HashSet();
        ArrayList<TLRPC.InputStickerSet> r1 = new ArrayList();
        TL_stories.StoryItem r2 = r8.storyItem;
        int r3 = 0;
        if (r2 != null) goto L6;
    L25:
        TL_stories.StoryItem r22 = r8.storyItem;
        if (r22 == null) goto L46;
        ArrayList<TLRPC.MessageEntity> r23 = r22.entities;
        if (r23 == null) goto L46;
        if (r23.isEmpty() == true) goto L46;
    L32:
        if (r3 >= r8.storyItem.entities.size()) goto L90;
        TLRPC.MessageEntity r24 = r8.storyItem.entities.get(r3);
        if ((r24 instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L45;
        TLRPC.TL_messageEntityCustomEmoji r25 = (TLRPC.TL_messageEntityCustomEmoji) r24;
        TLRPC.Document r4 = r25.document;
        if (r4 != null) goto L39;
        r4 = AnimatedEmojiDrawable.findDocument(this.currentAccount, r25.document_id);
    L39:
        if (r4 == null) goto L45;
        TLRPC.InputStickerSet r26 = MessageObject.getInputStickerSet(r4);
        if (r0.contains(Long.valueOf(r26.id)) == true) goto L45;
        r0.add(Long.valueOf(r26.id));
        r1.add(r26);
    L45:
        r3 = r3 + 1;
    L90:
        return r1;
    L46:
        StoriesController.UploadingStory r27 = r8.uploadingStory;
        if (r27 == null) goto L90;
        StoryEntry r28 = r27.entry;
        if (r28 == null) goto L90;
        if (r28.mediaEntities == null) goto L72;
        int r29 = 0;
    L54:
        if (r29 >= r8.uploadingStory.entry.mediaEntities.size()) goto L72;
        VideoEditedInfo.MediaEntity r42 = r8.uploadingStory.entry.mediaEntities.get(r29);
        if (r42.type != 4) goto L71;
        TL_stories.MediaArea r43 = r42.mediaArea;
        if (r43 == null) goto L71;
        TLRPC.Reaction r44 = r43.reaction;
        if ((r44 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L71;
        TLRPC.Document r45 = AnimatedEmojiDrawable.findDocument(this.currentAccount, ((TLRPC.TL_reactionCustomEmoji) r44).document_id);
        if (r45 == null) goto L71;
        TLRPC.InputStickerSet r46 = MessageObject.getInputStickerSet(r45);
        if (r46 == null) goto L71;
        if (r0.contains(Long.valueOf(r46.id)) == true) goto L71;
        r0.add(Long.valueOf(r46.id));
        r1.add(r46);
    L71:
        r29 = r29 + 1;
    L72:
        CharSequence r82 = r8.uploadingStory.entry.caption;
        if ((r82 instanceof Spanned) == false) goto L90;
        AnimatedEmojiSpan[] r83 = (AnimatedEmojiSpan[]) ((Spanned) r82).getSpans(0, r82.length(), AnimatedEmojiSpan.class);
        if (r83 == null) goto L90;
    L79:
        if (r3 >= r83.length) goto L90;
        AnimatedEmojiSpan r210 = r83[r3];
        TLRPC.Document r47 = r210.document;
        if (r47 != null) goto L83;
        r47 = AnimatedEmojiDrawable.findDocument(this.currentAccount, r210.documentId);
    L83:
        if (r47 == null) goto L89;
        TLRPC.InputStickerSet r211 = MessageObject.getInputStickerSet(r47);
        if (r0.contains(Long.valueOf(r211.id)) == true) goto L89;
        r0.add(Long.valueOf(r211.id));
        r1.add(r211);
    L89:
        r3 = r3 + 1;
        goto L79
    L6:
        if (r2.media_areas == null) goto L25;
        int r212 = 0;
    L9:
        if (r212 >= r8.storyItem.media_areas.size()) goto L25;
        TL_stories.MediaArea r48 = r8.storyItem.media_areas.get(r212);
        if ((r48 instanceof TL_stories.TL_mediaAreaSuggestedReaction) == false) goto L24;
        TLRPC.Reaction r49 = r48.reaction;
        if ((r49 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L24;
        TLRPC.Document r410 = AnimatedEmojiDrawable.findDocument(this.currentAccount, ((TLRPC.TL_reactionCustomEmoji) r49).document_id);
        if (r410 == null) goto L24;
        TLRPC.InputStickerSet r411 = MessageObject.getInputStickerSet(r410);
        if (r411 == null) goto L24;
        if (r0.contains(Long.valueOf(r411.id)) == true) goto L24;
        r0.add(Long.valueOf(r411.id));
        r1.add(r411);
    L24:
        r212 = r212 + 1;
        goto L9
    L91:
        return null;
    }

    private float getHideInterfaceAlpha() {
        return (1.0f - this.progressToHideInterface.get()) * (1.0f - this.storyViewer.getProgressToSelfViews());
    }

    private long getMessageMinPrice() {
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L5;
        return 0;
    L5:
        StoryViewer r0 = this.storyViewer;
        if (r0 != null) goto L8;
    L15:
        return 0;
    L8:
        if (r0.livePlayer == null) goto L15;
        if (disabledPaidFeatures(true) == false) goto L14;
        return 0;
    L14:
        return this.storyViewer.livePlayer.getSendPaidMessagesStars();
    }

    public static int getStoryId(TL_stories.StoryItem r0, StoriesController.UploadingStory r1) {
        if (r0 != null) goto L4;
        if (r1 == null) goto L10;
        StoryEntry r02 = r1.entry;
        if (r02 != null) goto L9;
        return 0;
    L9:
        return r02.editStoryId;
    L10:
        return 0;
    L4:
        return r0.id;
    }

    private boolean hideCaptionWithInterface() {
        return true;
    }

    private boolean hitButton(View r4, MotionEvent r5) {
        float r0 = (getX() + this.storyContainer.getX()) + r4.getX();
        float r1 = (getY() + this.storyContainer.getY()) + r4.getY();
        if (r5.getX() >= r0) goto L5;
        return false;
    L5:
        if (r5.getX() <= (r0 + r4.getWidth())) goto L7;
        return false;
    L7:
        if (r5.getY() >= r1) goto L9;
        return false;
    L9:
        if (r5.getY() > (r1 + r4.getHeight())) goto L16;
        return true;
    L16:
        return false;
    }

    private boolean isBotsPreview() {
        StoryViewer r1 = this.storyViewer;
        if (r1 == null) goto L10;
        StoriesController.StoriesList r12 = r1.storiesList;
        if (r12 != null) goto L7;
        return false;
    L7:
        if (r12.type != 4) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    private boolean isEditBotsPreview() {
        if (isBotsPreview() == true) goto L5;
        return false;
    L5:
        TLRPC.User r4 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.storyViewer.storiesList.dialogId));
        if (r4 != null) goto L8;
    L13:
        return false;
    L8:
        if (r4.bot == false) goto L13;
        if (r4.bot_can_edit == false) goto L13;
        return true;
    }

    private /* synthetic */ void lambda$animateOut$59(ValueAnimator r6) {
        this.outT = ((Float) r6.getAnimatedValue()).floatValue();
        this.headerView.setTranslationY((-AndroidUtilities.dp(8.0f)) * this.outT);
        this.headerView.setAlpha(1.0f - this.outT);
        this.optionsIconView.setTranslationY((-AndroidUtilities.dp(8.0f)) * this.outT);
        this.optionsIconView.setAlpha(1.0f - this.outT);
        this.pipIconView.setTranslationY((-AndroidUtilities.dp(8.0f)) * this.outT);
        this.pipIconView.setAlpha(1.0f - this.outT);
        this.muteIconContainer.setTranslationY((-AndroidUtilities.dp(8.0f)) * this.outT);
        this.muteIconContainer.setAlpha(this.muteIconViewAlpha * (1.0f - this.outT));
        FrameLayout r62 = this.selfView;
        if (r62 == null) goto L5;
        r62.setTranslationY(AndroidUtilities.dp(8.0f) * this.outT);
        this.selfView.setAlpha(1.0f - this.outT);
    L5:
        StoryPrivacyButton r63 = this.privacyButton;
        if (r63 == null) goto L8;
        r63.setTranslationY((-AndroidUtilities.dp(8.0f)) * this.outT);
        this.privacyButton.setAlpha(1.0f - this.outT);
    L8:
        this.storyCaptionView.setAlpha(1.0f - this.outT);
        Delegate r64 = this.delegate;
        if (r64 != null) goto L11;
        float r65 = 0.0f;
    L12:
        float r0 = getHideInterfaceAlpha();
        FrameLayout r1 = this.likeButtonContainer;
        if (r1 == null) goto L15;
        r1.setAlpha(((1.0f - r65) * r0) * (1.0f - this.outT));
    L15:
        ImageView r12 = this.shareButton;
        if (r12 == null) goto L18;
        r12.setAlpha(((1.0f - r65) * r0) * (1.0f - this.outT));
    L18:
        FrameLayout r13 = this.repostButtonContainer;
        if (r13 == null) goto L21;
        r13.setAlpha((r0 * (1.0f - r65)) * (1.0f - this.outT));
    L21:
        ChatActivityEnterView r66 = this.chatActivityEnterView;
        if (r66 == null) goto L24;
        r66.setAlpha(1.0f - this.outT);
        invalidate();
    L24:
        this.storyContainer.invalidate();
        return;
    L11:
        r65 = r64.getProgressToDismiss();
        goto L12
    }

    private /* synthetic */ void lambda$checkRecordLocked$58(boolean r1, AlertDialog r2, int r3) {
        ChatActivityEnterView r22 = this.chatActivityEnterView;
        if (r22 == null) goto L9;
        if (r1 == false) goto L7;
        this.storyViewer.close(true);
        return;
    L7:
        r22.cancelRecordingAudioVideo();
        return;
    }

    private /* synthetic */ void lambda$createCommentButton$25(View r2) {
        this.liveCommentsView.setCollapsed(!r1.isCollapsed(), true);
    }

    private /* synthetic */ void lambda$createFailView$21(View r1) {
        StoryItemHolder r12 = this.currentStory;
        if (r12 == null) goto L8;
        StoriesController.UploadingStory r13 = r12.uploadingStory;
        if (r13 == null) goto L9;
        r13.tryAgain();
        updatePosition();
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$createMuteButton$28(View r3) {
        LivePlayer r32 = LivePlayer.recording;
        if (r32 != null) goto L5;
        return;
    L5:
        boolean r33 = r32.isMuted();
        LivePlayer.recording.setMuted(!r33);
        this.muteButton.setMuted(!r33, true);
    }

    private /* synthetic */ void lambda$createPaidReactionsButton$26(View r5) {
        if (disabledPaidFeatures(false) == false) goto L6;
        this.liveCommentsView.openStarsSheet(disabledPaidFeatures(false));
        return;
    L6:
        StarsController r0 = StarsController.getInstance(this.currentAccount);
        if (r0.balanceAvailable() == true) goto L9;
    L12:
        this.liveCommentsView.sendStars(1, true);
        return;
    L9:
        if (r0.balance.amount > 0) goto L12;
        this.liveCommentsView.openStarsSheet(disabledPaidFeatures(false));
    }

    private /* synthetic */ boolean lambda$createPaidReactionsButton$27(View r2) {
        this.liveCommentsView.openStarsSheet(disabledPaidFeatures(false));
        return true;
    }

    private /* synthetic */ void lambda$createQualityItem$14(boolean r3, View r4) {
        MessagesController.getInstance(this.currentAccount).setStoryQuality(!r3);
        BulletinFactory r42 = BulletinFactory.of(this.storyContainer, this.resourcesProvider);
        int r0 = R.raw.chats_infotip;
        if (r3 == true) goto L5;
        int r1 = R.string.StoryQualityIncreasedTitle;
    L6:
        String r12 = LocaleController.getString(r1);
        if (r3 == true) goto L9;
        int r32 = R.string.StoryQualityIncreasedMessage;
    L10:
        r42.createSimpleBulletin(r0, r12, LocaleController.getString(r32)).show();
        CustomPopupMenu r2 = this.popupMenu;
        if (r2 == null) goto L14;
        r2.dismiss();
        return;
    L14:
        return;
    L9:
        r32 = R.string.StoryQualityDecreasedMessage;
        goto L10
    L5:
        r1 = R.string.StoryQualityDecreasedTitle;
        goto L6
    }

    private /* synthetic */ void lambda$createSelfPeerView$40(View r1) {
        showUserViewsDialog();
    }

    private /* synthetic */ void lambda$createStealthModeItem$12(View r5) {
        if (this.stealthModeIsActive == false) goto L5;
        StealthModeAlert.showStealthModeEnabledBulletin();
    L6:
        CustomPopupMenu r4 = this.popupMenu;
        if (r4 == null) goto L10;
        r4.dismiss();
        return;
    L10:
        return;
    L5:
        StealthModeAlert r52 = new StealthModeAlert(getContext(), getY() + this.storyContainer.getY(), 0, this.resourcesProvider);
        this.delegate.showDialog(r52);
        goto L6
    }

    private /* synthetic */ void lambda$createUnsupportedContainer$39(View r1) {
        Browser.openUrl(getContext(), BuildVars.RELEASES_URL);
    }

    private /* synthetic */ void lambda$deleteStory$41(AlertDialog r3, int r4) {
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L15;
        TL_stories.StoryItem r32 = this.currentStory.storyItem;
        if (r32 == null) goto L15;
        TLRPC.MessageMedia r33 = r32.media;
        if ((r33 instanceof TLRPC.TL_messageMediaVideoStream) == false) goto L15;
        TLRPC.InputGroupCall r34 = ((TLRPC.TL_messageMediaVideoStream) r33).call;
        LivePlayer r42 = LivePlayer.recording;
        if (r42 == null) goto L15;
        if (r42.equals(r34) == false) goto L15;
        LivePlayer.recording.destroy();
        if (LivePlayer.recording == null) goto L15;
        LivePlayer.recording = null;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.liveStoryUpdated, new Object[]{Long.valueOf(LivePlayer.recording.getCallId())});
    L15:
        this.currentStory.cancelOrDelete();
        updateStoryItems();
        if (this.isActive == true) goto L18;
    L21:
        int r35 = this.selectedPosition;
        int r43 = this.count;
        if (r35 < r43) goto L24;
        this.selectedPosition = r43 - 1;
    L26:
        updatePosition();
        StoryViewer r2 = this.storyViewer;
        if (r2 == null) goto L30;
        r2.checkSelfStoriesView();
        return;
    L30:
        return;
    L24:
        if (r35 >= 0) goto L26;
        this.selectedPosition = 0;
        goto L26
    L18:
        if (this.count != 0) goto L21;
        this.delegate.switchToNextAndRemoveCurrentPeer();
    }

    private /* synthetic */ void lambda$editPrivacy$55(Runnable r10, TLRPC.TL_error r11, TL_stories.StoryItem r12, StoryPrivacyBottomSheet.StoryPrivacy r13) {
        if (r10 == null) goto L4;
        r10.run();
    L4:
        if (r11 != null) goto L6;
    L9:
        r12.parsedPrivacy = r13;
        r12.privacy = r13.toValue();
        int r102 = r13.type;
        if (r102 != 1) goto L12;
        boolean r1 = true;
    L13:
        r12.close_friends = r1;
        if (r102 != 2) goto L16;
        boolean r2 = true;
    L17:
        r12.contacts = r2;
        if (r102 != 3) goto L20;
        boolean r103 = true;
    L21:
        r12.selected_contacts = r103;
        MessagesController.getInstance(this.currentAccount).getStoriesController().updateStoryItem(r12.dialogId, r12, true, true);
        this.editedPrivacy = true;
        int r104 = r13.type;
        if (r104 != 4) goto L24;
        BulletinFactory.of(this.storyContainer, this.resourcesProvider).createSimpleBulletin(R.raw.contact_check, LocaleController.getString("StorySharedToEveryone")).show();
    L37:
        updatePosition();
        return;
    L24:
        if (r104 != 1) goto L26;
        BulletinFactory.of(this.storyContainer, this.resourcesProvider).createSimpleBulletin(R.raw.contact_check, LocaleController.getString("StorySharedToCloseFriends")).show();
        goto L37
    L26:
        if (r104 != 2) goto L31;
        boolean r105 = r13.selectedUserIds.isEmpty();
        FrameLayout r122 = this.storyContainer;
        if (r105 == false) goto L30;
        BulletinFactory.of(r122, this.resourcesProvider).createSimpleBulletin(R.raw.contact_check, LocaleController.getString("StorySharedToAllContacts")).show();
        goto L37
    L30:
        BulletinFactory.of(r122, this.resourcesProvider).createSimpleBulletin(R.raw.contact_check, LocaleController.formatPluralString("StorySharedToAllContactsExcluded", r13.selectedUserIds.size(), new Object[0])).show();
        goto L37
    L31:
        if (r104 != 3) goto L37;
        HashSet r106 = new HashSet();
        r106.addAll(r13.selectedUserIds);
        Iterator<ArrayList<Long>> r123 = r13.selectedUserIdsByGroup.values().iterator();
    L34:
        if (r123.hasNext() == false) goto L36;
        r106.addAll(r123.next());
        goto L34
    L36:
        BulletinFactory.of(this.storyContainer, this.resourcesProvider).createSimpleBulletin(R.raw.contact_check, LocaleController.formatPluralString("StorySharedToContacts", r106.size(), new Object[0])).show();
        goto L37
    L20:
        r103 = false;
        goto L21
    L16:
        r2 = false;
        goto L17
    L12:
        r1 = false;
        goto L13
    L6:
        if ("STORY_NOT_MODIFIED".equals(r11.text) == true) goto L9;
        BulletinFactory.of(this.storyContainer, this.resourcesProvider).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.UnknownError)).show();
        goto L37
    }

    private /* synthetic */ void lambda$editPrivacy$56(Runnable r2, TL_stories.StoryItem r3, StoryPrivacyBottomSheet.StoryPrivacy r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new PeerStoriesView$$ExternalSyntheticLambda58(this, r2, r6, r3, r4));
    }

    private /* synthetic */ void lambda$editPrivacy$57(TL_stories.StoryItem r1, StoryPrivacyBottomSheet.StoryPrivacy r2, boolean r3, boolean r4, boolean r5, boolean r6, TLRPC.InputPeer r7, int r8, Runnable r9, Runnable r10) {
        TL_stories.TL_stories_editStory r32 = new TL_stories.TL_stories_editStory();
        r32.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r1.dialogId);
        r32.id = r1.id;
        r32.flags |= 4;
        r32.privacy_rules = r2.rules;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r32, new PeerStoriesView$$ExternalSyntheticLambda51(this, r9, r1, r2));
    }

    private /* synthetic */ void lambda$needEnterText$54() {
        this.delegate.requestAdjust(true);
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        StoryCaptionView r22 = this.storyCaptionView;
        if (r22.expanded == false) goto L10;
        boolean r23 = r22.textSelectionHelper.isInSelectionMode();
        StoryCaptionView r1 = this.storyCaptionView;
        if (r23 == true) goto L8;
        r1.collapse();
        return;
    L8:
        r1.checkCancelTextSelection();
        return;
    L10:
        this.checkBlackoutMode = true;
        r22.expand();
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        shareStory(true);
    }

    private /* synthetic */ void lambda$new$10(View r13) {
        TL_stories.StoryItem r132 = this.currentStory.storyItem;
        if (r132 != null) goto L6;
        return;
    L6:
        if (this.isSelf == false) goto L14;
        boolean r0 = r132.privacy.isEmpty();
        int r1 = this.currentAccount;
        if (r0 == false) goto L10;
        StoryPrivacyBottomSheet.StoryPrivacy r02 = new StoryPrivacyBottomSheet.StoryPrivacy(3, r1, new ArrayList());
    L11:
        editPrivacy(r02, r132);
        return;
    L10:
        r02 = new StoryPrivacyBottomSheet.StoryPrivacy(r1, r132.privacy);
        goto L11
    L14:
        if (this.privacyHint != null) goto L16;
        HintView2 r03 = new HintView2(getContext(), 1).setMultilineText(true).setTextAlign(Layout.Alignment.ALIGN_CENTER).setOnHiddenListener(new PeerStoriesView$$ExternalSyntheticLambda32(this));
        this.privacyHint = r03;
        r03.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.storyContainer.addView(this.privacyHint, LayoutHelper.createFrame(-1, 60.0f, 55, 0.0f, 52.0f, 0.0f, 0.0f));
    L16:
        TLRPC.User r04 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.dialogId));
        if (r04 == null) goto L43;
        String r05 = r04.first_name;
        int r4 = r05.indexOf(32);
        if (r4 <= 0) goto L23;
        r05 = r05.substring(0, r4);
    L23:
        if (r132.close_friends == false) goto L27;
        this.privacyHint.setInnerPadding(15.0f, 8.0f, 15.0f, 8.0f);
        SpannableStringBuilder r133 = AndroidUtilities.replaceTags(LocaleController.formatString("StoryCloseFriendsHint", R.string.StoryCloseFriendsHint, new Object[]{r05}));
    L25:
        boolean r06 = true;
    L32:
        CharSequence r134 = Emoji.replaceEmoji(r133, this.privacyHint.getTextPaint().getFontMetricsInt(), false);
        HintView2 r2 = this.privacyHint;
        if (r06 == false) goto L35;
        int r07 = HintView2.cutInFancyHalf(r134, r2.getTextPaint());
    L36:
        r2.setMaxWidthPx(r07);
        this.privacyHint.setText(r134);
        this.privacyHint.setJoint(1.0f, (-(this.storyContainer.getWidth() - this.privacyButton.getCenterX())) / AndroidUtilities.density);
        this.delegate.setIsHintVisible(true);
        if (this.privacyHint.shown() == false) goto L39;
        BotWebViewVibrationEffect.IMPACT_LIGHT.vibrate();
    L39:
        this.privacyHint.show();
        return;
    L35:
        r07 = this.storyContainer.getMeasuredWidth();
        goto L36
    L27:
        if (r132.contacts == false) goto L30;
        this.privacyHint.setInnerPadding(11.0f, 6.0f, 11.0f, 7.0f);
        r133 = AndroidUtilities.replaceTags(LocaleController.formatString("StoryContactsHint", R.string.StoryContactsHint, new Object[]{r05}));
        r06 = false;
        goto L32
    L30:
        if (r132.selected_contacts == false) goto L41;
        this.privacyHint.setInnerPadding(15.0f, 8.0f, 15.0f, 8.0f);
        r133 = AndroidUtilities.replaceTags(LocaleController.formatString("StorySelectedContactsHint", R.string.StorySelectedContactsHint, new Object[]{r05}));
        goto L25
    L41:
        return;
    }

    private /* synthetic */ void lambda$new$11(StoryViewer r3, View r4) {
        if (this.currentStory.hasSound() == false) goto L13;
        r3.toggleSilentMode();
        if (r3.soundEnabled() == false) goto L7;
        MessagesController.getGlobalMainSettings().edit().putInt("taptostorysoundhint", 3).apply();
    L7:
        FrameLayout r2 = this.muteIconContainer;
        if (r3.soundEnabled() == false) goto L10;
        int r32 = R.string.Mute;
    L11:
        r2.setContentDescription(LocaleController.getString(r32));
        return;
    L10:
        r32 = R.string.Unmute;
        goto L11
    L13:
        showNoSoundHint(true);
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        tryToOpenRepostStory();
    }

    private /* synthetic */ void lambda$new$3() {
        likeStory(null);
    }

    private /* synthetic */ void lambda$new$4(View r1) {
        TL_stories.StoryItem r12 = this.currentStory.storyItem;
        if (r12 != null) goto L5;
    L8:
        likeStory(null);
        return;
    L5:
        if (r12.sent_reaction != null) goto L8;
        applyMessageToChat(new PeerStoriesView$$ExternalSyntheticLambda34(this));
    }

    private /* synthetic */ void lambda$new$43() {
        checkStealthMode(true);
    }

    private /* synthetic */ boolean lambda$new$5(StoryViewer r2, View r3) {
        Runnable r32 = this.reactionsTooltipRunnable;
        if (r32 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r32);
        this.reactionsTooltipRunnable = null;
    L5:
        SharedConfig.setStoriesReactionsLongPressHintUsed(true);
        HintView2 r0 = this.reactionsLongpressTooltip;
        if (r0 == null) goto L8;
        r0.hide();
    L8:
        checkReactionsLayoutForLike();
        r2.windowView.dispatchTouchEvent(AndroidUtilities.emptyMotionEvent());
        showLikesReaction(true);
        return true;
    }

    private /* synthetic */ void lambda$new$51() {
        showNoSoundHint(false);
        MessagesController.getGlobalMainSettings().edit().putInt("taptostorysoundhint", MessagesController.getGlobalMainSettings().getInt("taptostorysoundhint", 0) + 1).apply();
    }

    private /* synthetic */ void lambda$new$6(StoryViewer r5, View r6) {
        long r0 = UserConfig.getInstance(this.currentAccount).clientUserId;
        long r2 = this.dialogId;
        if (r0 != r2) goto L7;
        Bundle r62 = new Bundle();
        r62.putInt("type", 1);
        r62.putLong("dialog_id", this.dialogId);
        r5.presentFragment(new MediaActivity(r62, null));
        return;
    L7:
        if (r2 <= 0) goto L10;
        r5.presentFragment(ProfileActivity.of(r2));
        return;
    L10:
        r5.presentFragment(ChatActivity.of(r2));
    }

    private /* synthetic */ void lambda$new$8(Theme.ResourcesProvider r15, StoryViewer r16, Context r17, SharedResources r18, View r19) {
        this.delegate.setPopupIsVisible(true);
        this.editStoryItem = null;
        boolean[] r12 = {false};
        if (this.isSelf == false) goto L6;
        MessagesController.getInstance(this.currentAccount).getStoriesController().loadBlocklistAtFirst();
        MessagesController.getInstance(this.currentAccount).getStoriesController().loadSendAs();
        MessagesController.getInstance(this.currentAccount).getStoriesController().getDraftsController().load();
    L6:
        if (this.isSelf == false) goto L8;
    L11:
        boolean r9 = true;
    L13:
        if (this.isSelf == false) goto L15;
    L21:
        boolean r8 = true;
    L22:
        AnonymousClass8 r0 = new AnonymousClass8(this, getContext(), r15, true, r15, r16, StoryItemHolder.m19635$$Nest$fgetisVideo(this.currentStory), r8, r9, r17, r18, r12);
        this.popupMenu = r0;
        r0.show(this.optionsIconView, 0, (-ActionBar.getCurrentActionBarHeight()) + AndroidUtilities.dp(6.0f));
        return;
    L15:
        if (this.isChannel == false) goto L17;
    L18:
        if (r9 == true) goto L21;
    L20:
        r8 = false;
        goto L22
    L17:
        if (isBotsPreview() == false) goto L20;
    L8:
        if (MessagesController.getInstance(this.currentAccount).getStoriesController().canEditStory(this.currentStory.storyItem) == true) goto L11;
        r9 = false;
        goto L13
    }

    private /* synthetic */ void lambda$new$9() {
        this.delegate.setIsHintVisible(false);
    }

    private /* synthetic */ void lambda$onHighlightLiveMessage$52(Long r3) {
        this.messageStars = r3.longValue();
        ChatActivityEnterView r32 = this.chatActivityEnterView;
        if (r32 == null) goto L5;
        r32.checkSendButton(true);
        this.chatActivityEnterView.updateSendButtonPaid();
    L5:
        checkStealthMode(true);
    }

    private /* synthetic */ void lambda$onMeasure$53(ValueAnimator r1) {
        this.animatingKeyboardHeight = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$openRepostStory$33() {
        this.editOpened = true;
        setActive(false);
    }

    private /* synthetic */ void lambda$openRepostStory$37(StoryRecorder r15, Long r16, Runnable r17, Boolean r18, Long r19) {
        if (r18.booleanValue() == false) goto L33;
        BaseFragment r0 = this.storyViewer.fragment;
        DialogStoriesCell.StoryCell r5 = null;
        if (r0 == null) goto L21;
        INavigationLayout r02 = r0.getParentLayout();
        if (r02 == null) goto L21;
        List<BaseFragment> r6 = r02.getFragmentStack();
        ArrayList r7 = new ArrayList();
        int r8 = r6.size() - 1;
    L9:
        if (r8 < 0) goto L21;
        BaseFragment r3 = r6.get(r8);
        if ((r3 instanceof DialogsActivity) == true) goto L12;
        r7.add(r3);
        r8 = r8 - 1;
        goto L9
    L12:
        DialogsActivity r32 = (DialogsActivity) r3;
        r32.closeSearching();
        DialogStoriesCell r33 = r32.dialogStoriesCell;
        if (r33 == null) goto L15;
        r5 = r33.findStoryCell(r19.longValue());
    L15:
        int r62 = 0;
    L17:
        if (r62 >= r7.size()) goto L19;
        r02.removeFragmentFromStack((BaseFragment) r7.get(r62));
        r62 = r62 + 1;
        goto L17
    L19:
        long r03 = 400;
        DialogStoriesCell r2 = r33;
    L22:
        BaseFragment r34 = this.storyViewer.fragment;
        if (r34 == null) goto L25;
        r34.clearSheets();
    L25:
        this.storyViewer.instantClose();
        this.editOpened = false;
        if (r2 != null) goto L28;
    L31:
        r15.replaceSourceView(StoryRecorder.SourceView.fromStoryCell(r5));
        AndroidUtilities.runOnUIThread(r17, r03);
        return;
    L28:
        if (r2.scrollTo(r19.longValue()) == false) goto L31;
        r2.afterNextLayout(new PeerStoriesView$$ExternalSyntheticLambda48(r5, r2, r19, r15, r17));
        return;
    L21:
        r03 = 400;
        r2 = null;
        goto L22
    L33:
        long r63 = System.currentTimeMillis();
        VideoPlayerSharedScope r152 = this.playerSharedScope;
        if (r152 != null) goto L36;
    L42:
        StoryViewer.VideoPlayerHolder r22 = r152.player;
        r22.firstFrameRendered = false;
        r152.firstFrameRendered = false;
        r22.setOnReadyListener(new PeerStoriesView$$ExternalSyntheticLambda50(r17, r63));
        this.delegate.setPopupIsVisible(false);
        RLottieImageView r153 = this.muteIconView;
        if (r153 == null) goto L46;
        r153.setAnimation(this.sharedResources.muteDrawable);
    L46:
        if (this.videoDuration > 0) goto L48;
    L50:
        Long r154 = r16;
    L51:
        setActive(r154.longValue(), true);
        this.editOpened = false;
        AndroidUtilities.runOnUIThread(r17, 400);
        if (r18.booleanValue() == false) goto L58;
        updatePosition();
        return;
    L58:
        return;
    L48:
        if (r16.longValue() <= (this.videoDuration - 1400)) goto L50;
        r154 = 0L;
        goto L51
    L36:
        if (r152.player != null) goto L42;
        this.delegate.setPopupIsVisible(false);
        setActive(true);
        this.editOpened = false;
        this.onImageReceiverThumbLoaded = new PeerStoriesView$$ExternalSyntheticLambda49(r17);
        if (r18.booleanValue() == false) goto L40;
        updatePosition();
    L40:
        AndroidUtilities.runOnUIThread(r17, 400);
    }

    private /* synthetic */ void lambda$openRepostStory$38(Activity r8) {
        StoryRecorder r1 = StoryRecorder.getInstance(r8, this.currentAccount);
        VideoPlayerSharedScope r82 = this.playerSharedScope;
        if (r82 == null) goto L8;
        StoryViewer.VideoPlayerHolder r83 = r82.player;
        if (r83 == null) goto L8;
        long r2 = r83.currentPosition;
    L7:
        long r4 = r2;
        StoryEntry r3 = StoryEntry.repostStoryItem(this.currentStory.getPath(), this.currentStory.storyItem);
        r1.openForward(StoryRecorder.SourceView.fromStoryViewer(this.storyViewer), r3, r4, true);
        r1.setOnFullyOpenListener(new PeerStoriesView$$ExternalSyntheticLambda45(this));
        r1.setOnPrepareCloseListener(new PeerStoriesView$$ExternalSyntheticLambda46(this, r1));
        return;
    L8:
        r2 = 0;
        goto L7
    }

    private /* synthetic */ void lambda$saveToGallery$32(boolean r1, Uri r2) {
        BulletinFactory.createSaveToGalleryBulletin(this.storyContainer, r1, this.resourcesProvider).show();
    }

    private /* synthetic */ void lambda$showLikesReaction$17(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.likesReactionShowProgress = r12;
        this.likesReactionLayout.setTransitionProgress(r12);
    }

    private /* synthetic */ void lambda$showPaidMessageHint$29(HintView2 r2) {
        removeView(r2);
        if (this.highlightMessageHintView != r2) goto L6;
        this.highlightMessageHintView = null;
        return;
    }

    private /* synthetic */ void lambda$showPremiumBlockedToast$22(TL_stories.TL_premium_boostsStatus r7, ChannelBoostsController.CanApplyBoost r8) {
        this.canApplyBoost = r8;
        LimitReachedBottomSheet.openBoostsForRemoveRestrictions(fragmentForLimit(), r7, r8, this.dialogId, true);
        StoryViewer r6 = this.storyViewer;
        if (r6 == null) goto L6;
        r6.setOverlayVisible(false);
        return;
    }

    private /* synthetic */ void lambda$showPremiumBlockedToast$23(TL_stories.TL_premium_boostsStatus r5) {
        if (r5 != null) goto L7;
        StoryViewer r4 = this.storyViewer;
        if (r4 == null) goto L9;
        r4.setOverlayVisible(false);
        return;
    L9:
        return;
    L7:
        this.boostsStatus = r5;
        MessagesController.getInstance(this.currentAccount).getBoostsController().userCanBoostChannel(this.dialogId, r5, new PeerStoriesView$$ExternalSyntheticLambda47(this, r5));
    }

    private /* synthetic */ void lambda$showPremiumBlockedToast$24() {
        StoryViewer r2 = this.storyViewer;
        if (r2 == null) goto L6;
        r2.presentFragment(new PremiumPreviewFragment("noncontacts"));
        return;
    }

    private /* synthetic */ void lambda$toggleArchiveForStory$20(MessagesController r7, long r8, boolean r10, String r11, TLObject r12) {
        r7.getStoriesController().toggleHidden(r8, r10, false, true);
        BulletinFactory.UndoObject r82 = new BulletinFactory.UndoObject();
        r82.onUndo = new PeerStoriesView$$ExternalSyntheticLambda0(r7, r8, r10);
        r82.onAction = new PeerStoriesView$$ExternalSyntheticLambda1(r7, r8, r10);
        if (r10 == true) goto L5;
        SpannableStringBuilder r72 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.StoriesMovedToDialogs, new Object[]{ContactsController.formatName(r11, null, 10)}));
    L6:
        BulletinFactory.of(this.topBulletinContainer, this.resourcesProvider).createUsersBulletin(Arrays.asList(new TLObject[]{r12}), r72, null, r82).setTag(2).show(true);
        return;
    L5:
        r72 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.StoriesMovedToContacts, new Object[]{ContactsController.formatName(r11, null, 10)}));
        goto L6
    }

    private /* synthetic */ void lambda$updatePosition$44(StoryCaptionView.Panel r5, View r6) {
        if (r5.peerId == null) goto L16;
        Bundle r62 = new Bundle();
        long r0 = r5.peerId.longValue();
        Long r1 = r5.peerId;
        if (r0 < 0) goto L7;
        r62.putLong("user_id", r1.longValue());
    L9:
        if (r5.isRepostMessage == false) goto L14;
        Integer r52 = r5.messageId;
        if (r52 == null) goto L14;
        r62.putInt("message_id", r52.intValue());
        this.storyViewer.presentFragment(new ChatActivity(r62));
        return;
    L14:
        this.storyViewer.presentFragment(new ProfileActivity(r62));
        return;
    L7:
        r62.putLong("chat_id", -r1.longValue());
        goto L9
    L16:
        BulletinFactory.of(this.storyContainer, this.resourcesProvider).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.StoryHidAccount)).setTag(3).show(true);
    }

    private /* synthetic */ void lambda$updatePosition$45(long r3, View r5) {
        Bundle r52 = new Bundle();
        if (r3 < 0) goto L5;
        r52.putLong("user_id", r3);
    L6:
        this.storyViewer.presentFragment(new ProfileActivity(r52));
        return;
    L5:
        r52.putLong("chat_id", -r3);
        goto L6
    }

    private /* synthetic */ void lambda$updatePosition$46() {
        onHighlightLiveMessage();
    }

    private /* synthetic */ void lambda$updatePosition$47() {
        this.messageStars = 0;
        ChatActivityEnterView r0 = this.chatActivityEnterView;
        if (r0 == null) goto L6;
        r0.checkSendButton(true);
        this.chatActivityEnterView.updateSendButtonPaid();
        checkStealthMode(true);
        return;
    }

    private /* synthetic */ boolean lambda$updatePosition$48(View r8) {
        boolean r2 = false;
        if (disabledPaidFeatures(true) == false) goto L5;
        return false;
    L5:
        ItemOptions r82 = ItemOptions.makeOptions(this.storyViewer.containerView, this.resourcesProvider, r8).add(R.drawable.msg_edit, LocaleController.getString(R.string.LiveStoryMessageEditStars), new PeerStoriesView$$ExternalSyntheticLambda26(this));
        if (this.messageStars <= 0) goto L8;
        r2 = true;
    L8:
        r82.addIf(r2, R.drawable.menu_delete_paid, LocaleController.getString(R.string.LiveStoryMessageRemoveStars), new PeerStoriesView$$ExternalSyntheticLambda27(this)).setGravity(5).forceTop(true).show();
        return true;
    }

    private /* synthetic */ void lambda$updatePosition$49() {
        this.failView.setVisibility(8);
    }

    private /* synthetic */ void lambda$updatePosition$50() {
        if (this.storyViewer.isShown() == true) goto L5;
        return;
    L5:
        this.reactionsTooltipRunnable = null;
        if (this.reactionsLongpressTooltip != null) goto L13;
        HintView2 r0 = new HintView2(getContext(), 3).setJoint(1.0f, -22.0f);
        this.reactionsLongpressTooltip = r0;
        r0.setBgColor(ColorUtils.setAlphaComponent(ColorUtils.blendARGB(-16777216, -1, 0.13f), 240));
        this.reactionsLongpressTooltip.setBounce(false);
        this.reactionsLongpressTooltip.setText(LocaleController.getString(R.string.ReactionLongTapHint));
        this.reactionsLongpressTooltip.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(1.0f));
        FrameLayout r02 = this.storyContainer;
        HintView2 r1 = this.reactionsLongpressTooltip;
        if (this.BIG_SCREEN == false) goto L11;
        float r2 = 0.0f;
    L12:
        r02.addView(r1, LayoutHelper.createFrame(-1, -2.0f, 85, 0.0f, 0.0f, 0.0f, r2));
        goto L13
    L11:
        r2 = 56.0f;
    L13:
        this.reactionsLongpressTooltip.show();
        SharedConfig.setStoriesReactionsLongPressHintUsed(true);
    }

    private void likeStory(ReactionsLayoutInBubble.VisibleReaction r14) {
        TL_stories.StoryItem r0 = this.currentStory.storyItem;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0 != null) goto L8;
    L10:
        boolean r3 = false;
    L11:
        TLRPC.Reaction r02 = r0.sent_reaction;
        if (r02 == null) goto L16;
        if (r14 != null) goto L16;
        animateLikeButton();
        this.storiesController.setStoryReaction(this.dialogId, this.currentStory.storyItem, null);
    L24:
        TL_stories.StoryItem r142 = this.currentStory.storyItem;
        if (r142 == null) goto L32;
        TLRPC.Reaction r143 = r142.sent_reaction;
        if (r143 == null) goto L32;
        r3 = !r3;
        this.storiesLikeButton.setReaction(ReactionsLayoutInBubble.VisibleReaction.fromTL(r143));
        this.likeButtonContainer.setContentDescription(LocaleController.getString(R.string.AccDescrLiked));
        performHapticFeedback(3);     // Catch: Exception -> L48
    L31:
        boolean r144 = true;
    L34:
        if (this.isChannel == false) goto L46;
        if (r3 == false) goto L46;
        TL_stories.StoryItem r32 = this.currentStory.storyItem;
        if (r32.views != null) goto L39;
        r32.views = new TL_stories.TL_storyViews();
    L39:
        TL_stories.StoryViews r33 = this.currentStory.storyItem.views;
        int r4 = r33.reactions_count;
        if (r144 == false) goto L42;
        int r145 = 1;
    L43:
        int r42 = r4 + r145;
        r33.reactions_count = r42;
        if (r42 >= 0) goto L46;
        r33.reactions_count = 0;
        goto L46
    L42:
        r145 = -1;
    L46:
        TL_stories.StoryItem r146 = this.currentStory.storyItem;
        ReactionsUtils.applyForStoryViews(r02, r146.sent_reaction, r146.views);
        updateUserViews(true);
        return;
    L32:
        this.storiesLikeButton.setReaction(null);
        this.likeButtonContainer.setContentDescription(LocaleController.getString(R.string.AccDescrLike));
        r144 = false;
    L16:
        if (r14 != null) goto L23;
        TLRPC.TL_availableReaction r147 = MediaDataController.getInstance(this.currentAccount).getReactionsMap().get("❤");
        if (r147 == null) goto L24;
        this.drawAnimatedEmojiAsMovingReaction = false;
        TLRPC.Document r5 = r147.around_animation;
        this.reactionEffectImageReceiver.setImage(ImageLocation.getForDocument(r5), ReactionsEffectOverlay.getFilterForAroundAnimation(), null, null, null, 0);
        if (this.reactionEffectImageReceiver.getLottieAnimation() == null) goto L22;
        this.reactionEffectImageReceiver.getLottieAnimation().setCurrentFrame(0, false, true);
    L22:
        this.drawReactionEffect = true;
        this.storiesController.setStoryReaction(this.dialogId, this.currentStory.storyItem, ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(r147));
        goto L24
    L23:
        animateLikeButton();
        this.storiesController.setStoryReaction(this.dialogId, this.currentStory.storyItem, r14);
        goto L24
    L8:
        if (r0.sent_reaction == null) goto L10;
        r3 = true;
        goto L11
    }

    private void loadSendAsPeers(boolean r5) {
        if (this.sendAsPeersObj != null) goto L19;
        StoryViewer r0 = this.storyViewer;
        if (r0 == null) goto L12;
        LivePlayer r02 = r0.livePlayer;
        if (r02 == null) goto L12;
        if (r02.sendAsDisabled() == false) goto L12;
        return;
    L12:
        TLRPC.TL_channels_sendAsPeers r03 = MessagesController.getInstance(this.currentAccount).getSendAsPeers(this.dialogId, true);
        this.sendAsPeersObj = r03;
        ChatActivityEnterView r4 = this.chatActivityEnterView;
        if (r4 == null) goto L17;
        if (r03 == null) goto L18;
        r4.updateSendAsButton(r5);
        return;
    L18:
        return;
    L17:
        return;
    }

    private void onHighlightLiveMessage() {
        MessagesController.getGlobalMainSettings().edit().putInt("taptostoryhighlighthint", 3).apply();
        TLRPC.TL_textWithEntities r6 = this.chatActivityEnterView.getTextWithEntities();
        long r0 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        TLRPC.Peer r2 = this.storyViewer.livePlayer.getDefaultSendAs();
        if (r2 == null) goto L5;
        r0 = DialogObject.getPeerDialogId(r2);
    L5:
        Context r1 = getContext();
        int r22 = this.currentAccount;
        HighlightMessageSheet.open(r1, r22, r0, DialogObject.getShortName(r22, this.dialogId), r6, getMessageMinPrice(), this.messageStars, new PeerStoriesView$$ExternalSyntheticLambda42(this), new DarkThemeResourceProvider());
    }

    private void onLinkCopied() {
        if (this.currentStory.storyItem != null) goto L5;
        return;
    L5:
        TL_stories.TL_stories_exportStoryLink r0 = new TL_stories.TL_stories_exportStoryLink();
        r0.id = this.currentStory.storyItem.id;
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new AnonymousClass29(this));
    }

    private void onSideControlButtonOnClick(int r1, View r2) {
        if (r1 != 0) goto L5;
        openAttachMenu();
        return;
    }

    private void openAttachMenu() {
        if (this.chatActivityEnterView != null) goto L5;
        return;
    L5:
        createChatAttachView();
        this.chatAttachAlert.getPhotoLayout().loadGalleryPhotos();
        this.chatAttachAlert.setMaxSelectedPhotos(-1, true);
        this.chatAttachAlert.setDialogId(this.dialogId);
        this.chatAttachAlert.init();
        this.chatAttachAlert.getCommentView().setText(this.chatActivityEnterView.getFieldText());
        this.delegate.showDialog(this.chatAttachAlert);
    }

    private void openChat() {
        Bundle r0 = new Bundle();
        long r1 = this.dialogId;
        if (r1 >= 0) goto L5;
        r0.putLong("chat_id", -r1);
    L6:
        TLRPC.Dialog r12 = MessagesController.getInstance(this.currentAccount).getDialog(this.dialogId);
        if (r12 == null) goto L9;
        r0.putInt("message_id", r12.top_message);
    L9:
        ChatActivity r13 = new ChatActivity(r0);
        this.storyViewer.presentFragment(r13);
        return;
    L5:
        r0.putLong("user_id", r1);
        goto L6
    }

    private void openRepostStory() {
        Activity r0 = AndroidUtilities.findActivity(getContext());
        if (r0 == null) goto L10;
        PeerStoriesView$$ExternalSyntheticLambda41 r1 = new PeerStoriesView$$ExternalSyntheticLambda41(this, r0);
        if (this.delegate.releasePlayer(r1) == true) goto L9;
        AndroidUtilities.runOnUIThread(r1, 80);
        return;
    L9:
        return;
    }

    private void requestVideoPlayer(long r14) {
        if (this.isActive == true) goto L5;
        this.playerSharedScope.renderView = null;
        return;
    L5:
        if (this.currentStory.isLive() == false) goto L8;
        Delegate r5 = this.delegate;
        TL_stories.StoryItem r6 = this.currentStory.storyItem;
        long r7 = this.dialogId;
        int r9 = r6.id;
        TLRPC.MessageMedia r142 = r6.media;
        r5.requestPlayer(r6, r7, r9, ((TLRPC.TL_messageMediaVideoStream) r142).rtmp_stream, ((TLRPC.TL_messageMediaVideoStream) r142).call, this.playerSharedScope);
        this.storyContainer.invalidate();
        return;
    L8:
        if (this.currentStory.isVideo() == true) goto L10;
        FileLog.d("PeerStoriesView.requestVideoPlayer(" + r14 + "): null, not a video");
        this.delegate.requestPlayer(null, null, 0, this.playerSharedScope);
        VideoPlayerSharedScope r13 = this.playerSharedScope;
        r13.renderView = null;
        r13.firstFrameRendered = false;
        return;
    L10:
        if (this.currentStory.getLocalPath() != null) goto L12;
    L14:
        TL_stories.StoryItem r3 = this.currentStory.storyItem;
        if (r3 == null) goto L29;
        r3.dialogId = this.dialogId;
        TLRPC.Document r32 = r3.media.getDocument();     // Catch: Exception -> L27
        TL_stories.StoryItem r8 = this.currentStory.storyItem;     // Catch: Exception -> L37
        if (r8.fileReference != 0) goto L21;
        r8.fileReference = FileLoader.getInstance(this.currentAccount).getFileReference(this.currentStory.storyItem);     // Catch: Exception -> L37
    L21:
        StringBuilder r82 = new StringBuilder("?account=");     // Catch: Exception -> L37
        r82.append(this.currentAccount);     // Catch: Exception -> L37
        r82.append("&id=");     // Catch: Exception -> L37
        r82.append(r32.id);     // Catch: Exception -> L37
        r82.append("&hash=");     // Catch: Exception -> L37
        r82.append(r32.access_hash);     // Catch: Exception -> L37
        r82.append("&dc=");     // Catch: Exception -> L37
        r82.append(r32.dc_id);     // Catch: Exception -> L37
        r82.append("&size=");     // Catch: Exception -> L37
        r82.append(r32.size);     // Catch: Exception -> L37
        r82.append("&mime=");     // Catch: Exception -> L37
        r82.append(URLEncoder.encode(r32.mime_type, "UTF-8"));     // Catch: Exception -> L37
        r82.append("&rid=");     // Catch: Exception -> L37
        r82.append(this.currentStory.storyItem.fileReference);     // Catch: Exception -> L37
        r82.append("&name=");     // Catch: Exception -> L37
        r82.append(URLEncoder.encode(FileLoader.getDocumentFileName(r32), "UTF-8"));     // Catch: Exception -> L37
        r82.append("&reference=");     // Catch: Exception -> L37
        byte[] r0 = r32.file_reference;     // Catch: Exception -> L37
        if (r0 != null) goto L25;
        r0 = new byte[0];     // Catch: Exception -> L37
    L25:
        r82.append(Utilities.bytesToHex(r0));     // Catch: Exception -> L37
        r82.append("&sid=");     // Catch: Exception -> L37
        r82.append(this.currentStory.storyItem.id);     // Catch: Exception -> L37
        r82.append("&did=");     // Catch: Exception -> L37
        r82.append(this.currentStory.storyItem.dialogId);     // Catch: Exception -> L37
        Uri r02 = Uri.parse("tg://" + FileLoader.getAttachFileName(r32) + r82.toString());     // Catch: Exception -> L37
        FileLog.d("StoryViewer requestVideoPlayer(" + r14 + "): playing from " + r02);     // Catch: Exception -> L37
        this.videoDuration = (long) (MessageObject.getDocumentDuration(r32) * 1000.0d);     // Catch: Exception -> L37
        Uri r83 = r02;
        TLRPC.Document r72 = r32;
    L30:
        if (r83 != null) goto L32;
        FileLog.d("PeerStoriesView.requestVideoPlayer(" + r14 + "): playing from null?");
    L32:
        this.delegate.requestPlayer(r72, r83, r14, this.playerSharedScope);
        this.storyContainer.invalidate();
        return;
    L28:
        r72 = r32;
        r83 = null;
    L27:
        r32 = null;
        goto L28
    L29:
        r72 = null;
        r83 = null;
        goto L30
    L12:
        if (new File(this.currentStory.getLocalPath()).exists() == false) goto L14;
        Uri r03 = Uri.fromFile(new File(this.currentStory.getLocalPath()));
        FileLog.d("StoryViewer requestVideoPlayer(" + r14 + "): playing from attachPath " + r03);
        this.videoDuration = 0;
        r83 = r03;
        r72 = null;
        goto L30
    }

    private void saveToGallery() {
        StoryItemHolder r0 = this.currentStory;
        TL_stories.StoryItem r1 = r0.storyItem;
        if (r1 != null) goto L8;
        if (r0.uploadingStory != null) goto L8;
        return;
    L8:
        if ((r1 instanceof TL_stories.TL_storyItemSkipped) == false) goto L10;
        return;
    L10:
        File r02 = r0.getPath();
        boolean r4 = this.currentStory.isVideo();
        if (r02 != null) goto L13;
    L16:
        showDownloadAlert();
        return;
    L13:
        if (r02.exists() == false) goto L16;
        String r2 = r02.toString();
        Context r3 = getContext();
        PeerStoriesView$$ExternalSyntheticLambda59 r7 = new PeerStoriesView$$ExternalSyntheticLambda59(this, r4);
        MediaController.saveFile(r2, r3, r4 ? 1 : 0, null, null, r7);
    }

    private void sendUriAsDocument(Uri r21) {
        if (r21 == null) goto L37;
        TL_stories.StoryItem r11 = this.currentStory.storyItem;
        if (r11 != null) goto L8;
        return;
    L8:
        if ((r11 instanceof TL_stories.TL_storyItemSkipped) == true) goto L38;
        String r0 = r21.toString();
        boolean r4 = true;
        if (r0.contains("com.google.android.apps.photos.contentprovider") == true) goto L33;
    L18:
        Uri r02 = r21;
    L19:
        String r2 = AndroidUtilities.getPath(r02);
        if (BuildVars.NO_SCOPED_STORAGE == true) goto L22;
    L21:
        String r3 = r2;
    L29:
        if (r4 == false) goto L31;
        SendMessagesHelper.prepareSendingDocument(getAccountInstance(), null, null, r02, null, null, this.dialogId, null, null, r11, null, null, true, 0, null, null, false);
        return;
    L31:
        SendMessagesHelper.prepareSendingDocument(getAccountInstance(), r2, r3, null, null, null, this.dialogId, null, null, r11, null, null, true, 0, null, null, false);
        return;
    L22:
        if (r2 != null) goto L28;
        String r22 = r02.toString();
        String r42 = MediaController.copyFileToCache(r02, "file");
        if (r42 != null) goto L27;
        showAttachmentError();
        return;
    L27:
        r3 = r22;
        r2 = r42;
        r4 = false;
        goto L29
    L28:
        r4 = false;
        goto L21
    L33:
        String r03 = r0.split("/1/")[1];     // Catch: Exception -> L16
        int r23 = r03.indexOf("/ACTUAL");     // Catch: Exception -> L16
        if (r23 == (-1)) goto L18;
        r02 = Uri.parse(URLDecoder.decode(r03.substring(0, r23), "UTF-8"));     // Catch: Exception -> L16
    L16:
        e = move-exception;
        FileLog.e(e);
        goto L18
    L38:
        return;
    }

    private void setStoryImage(TL_stories.StoryItem r14, ImageReceiver r15, String r16) {
        StoriesController.UploadingStory r0 = this.storiesController.findEditingStory(this.dialogId, r14);
        if (r0 == null) goto L6;
        setStoryImage(r0, r15, r16);
        return;
    L6:
        TLRPC.MessageMedia r13 = r14.media;
        if (r13 != null) goto L9;
    L11:
        boolean r132 = false;
    L12:
        String r02 = r14.attachPath;
        if (r02 != null) goto L15;
        TLRPC.MessageMedia r03 = r14.media;
        if (r132 == false) goto L27;
        TLRPC.PhotoSize r133 = FileLoader.getClosestPhotoSizeWithSize(r03.getDocument().thumbs, 1000);
        r15.setImage(ImageLocation.getForDocument(r14.media.getDocument()), r16 + "_pframe", ImageLocation.getForDocument(r133, r14.media.getDocument()), r16, null, null, null, 0, null, r14, 0);
        return;
    L27:
        if (r03 == null) goto L29;
        TLRPC.Photo r134 = r03.photo;
    L30:
        if (r134 == null) goto L35;
        ArrayList<TLRPC.PhotoSize> r04 = r134.sizes;
        if (r04 == null) goto L35;
        TLRPC.PhotoSize r05 = FileLoader.getClosestPhotoSizeWithSize(r04, Integer.MAX_VALUE);
        FileLoader.getClosestPhotoSizeWithSize(r134.sizes, 800);
        r15.setImage(null, null, ImageLocation.getForPhoto(r05, r134), r16, null, null, null, 0, null, r14, 0);
        return;
    L35:
        r15.clearImage();
        return;
    L29:
        r134 = null;
        goto L30
    L15:
        if (r14.media != null) goto L17;
        r132 = r02.toLowerCase().endsWith(".mp4");
    L17:
        String r06 = r14.attachPath;
        if (r132 == false) goto L21;
        r15.setImage(ImageLocation.getForPath(r06), r16 + "_pframe", ImageLocation.getForPath(r14.firstFramePath), r16, null, null, null, 0, null, null, 0);
        return;
    L21:
        r15.setImage(ImageLocation.getForPath(r06), r16, null, null, null, 0, null, null, 0);
        return;
    L9:
        if (MessageObject.isVideoDocument(r13.getDocument()) == false) goto L11;
        r132 = true;
        goto L12
    }

    private void setStoryImage(StoriesController.UploadingStory r26, ImageReceiver r27, String r28) {
        if (r26.isVideo == false) goto L6;
        r27.setImage(null, null, ImageLocation.getForPath(r26.firstFramePath), r28, null, null, null, 0, null, null, 0);
        return;
    L6:
        r27.setImage(ImageLocation.getForPath(r26.path), r28, null, null, null, 0, null, null, 0);
    }

    private void setTitle(boolean r5, long r6, boolean r8) {
        if (r5 == false) goto L4;
    L8:
        this.titleLastDialogId = r6;
        this.titleLastLive = r8;
        if (r6 >= 0) goto L11;
        TLRPC.Chat r52 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r6));
        SimpleTextView r62 = this.headerView.titleView;
        if (r52 != null) goto L29;
        String r7 = _UrlKt.FRAGMENT_ENCODE_SET;
    L30:
        r62.setText(AndroidUtilities.removeDiacritics(r7));
        if (r52 != null) goto L33;
    L36:
        this.headerView.titleView.setRightDrawable(null);
        return;
    L33:
        if (r52.verified == false) goto L36;
        Drawable r53 = ContextCompat.getDrawable(getContext(), R.drawable.verified_profile).mutate();
        r53.setAlpha(255);
        CombinedDrawable r63 = new CombinedDrawable(r53, null);
        r63.setFullsize(true);
        r63.setCustomSize(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        this.headerView.titleView.setRightDrawable(r63);
        return;
    L29:
        r7 = r52.title;
        goto L30
    L11:
        if (this.isSelf == false) goto L15;
        if (r8 == true) goto L15;
        this.headerView.titleView.setText(LocaleController.getString(R.string.SelfStoryTitle));
        this.headerView.titleView.setRightDrawable(null);
        return;
    L15:
        TLRPC.User r54 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r6));
        if (r54 != null) goto L18;
    L20:
        this.headerView.titleView.setRightDrawable(null);
    L21:
        if (r54 == null) goto L24;
        CharSequence r55 = Emoji.replaceEmoji(AndroidUtilities.removeDiacritics(ContactsController.formatName(r54)), this.headerView.titleView.getPaint().getFontMetricsInt(), false);
        this.headerView.titleView.setText(r55);
        return;
    L24:
        this.headerView.titleView.setText(null);
        return;
    L18:
        if (r54.verified == false) goto L20;
        Drawable r64 = ContextCompat.getDrawable(getContext(), R.drawable.verified_profile).mutate();
        r64.setAlpha(255);
        CombinedDrawable r72 = new CombinedDrawable(r64, null);
        r72.setFullsize(true);
        r72.setCustomSize(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        this.headerView.titleView.setRightDrawable(r72);
        goto L21
    L4:
        if (r6 != this.titleLastDialogId) goto L8;
        if (this.titleLastLive != r8) goto L8;
    }

    private void shareStory(boolean r18) {
        StoryItemHolder r0 = this.currentStory;
        if (r0.storyItem != null) goto L5;
        return;
    L5:
        if (this.storyViewer.fragment == null) goto L29;
        String r6 = r0.createLink();
        if (r18 == false) goto L26;
        AnonymousClass26 r15 = new AnonymousClass26(this, this.resourcesProvider);
        if (this.isChannel == false) goto L11;
        TLRPC.Chat r02 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
    L13:
        if (DISABLE_STORY_REPOSTING == false) goto L15;
    L23:
        boolean r13 = false;
    L24:
        AnonymousClass27 r03 = new AnonymousClass27(this, this.storyViewer.fragment.getContext(), null, null, null, r6, null, false, r6, null, false, false, r13, null, r15);
        this.shareAlert = r03;
        r03.forceDarkThemeForHint = true;
        TL_stories.StoryItem r2 = this.currentStory.storyItem;
        r2.dialogId = this.dialogId;
        r03.setStoryToShare(r2);
        this.shareAlert.setDelegate(new AnonymousClass28(this));
        this.delegate.showDialog(this.shareAlert);
        return;
    L15:
        if (MessagesController.getInstance(this.currentAccount).storiesEnabled() == false) goto L23;
        if (this.isChannel == true) goto L21;
        if (UserObject.isService(this.dialogId) == true) goto L21;
    L22:
        r13 = true;
    L21:
        if (ChatObject.isPublic(r02) == false) goto L23;
    L11:
        r02 = null;
        goto L13
    L26:
        Intent r04 = new Intent("android.intent.action.SEND");
        r04.setType("text/plain");
        r04.putExtra("android.intent.extra.TEXT", r6);
        LaunchActivity.instance.startActivityForResult(Intent.createChooser(r04, LocaleController.getString(R.string.StickersShare)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
        return;
    }

    private void showAttachmentError() {
        BulletinFactory.of(this.storyContainer, this.resourcesProvider).createErrorBulletin(LocaleController.getString(R.string.UnsupportedAttachment), this.resourcesProvider).show();
    }

    private void showDownloadAlert() {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        r0.setTitle(LocaleController.getString(R.string.AppName));
        r0.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r0.setMessage(LocaleController.getString(R.string.PleaseDownload));
        this.delegate.showDialog(r0.create());
    }

    private void showLikesReaction(boolean r5) {
        if (this.likesReactionShowing != r5) goto L5;
        return;
    L5:
        if (this.currentStory.storyItem == null) goto L23;
        this.likesReactionShowing = r5;
        if (r5 == false) goto L10;
        this.likesReactionLayout.setVisibility(0);
    L10:
        this.likesReactionLayout.setStoryItem(this.currentStory.storyItem);
        this.delegate.setIsLikesReaction(r5);
        float r1 = 0.0f;
        if (r5 == false) goto L18;
        float r2 = this.likesReactionShowProgress;
        if (r5 == false) goto L15;
        r1 = 1.0f;
    L15:
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{r2, r1});
        this.likesReactionLayout.setTransitionProgress(this.likesReactionShowProgress);
        r0.addUpdateListener(new PeerStoriesView$$ExternalSyntheticLambda23(this));
        r0.addListener(new AnonymousClass14(this, r5));
        r0.setDuration(200);
        r0.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        r0.start();
        return;
    L18:
        if (this.likesReactionLayout.getReactionsWindow() == null) goto L20;
        this.likesReactionLayout.getReactionsWindow().dismissWithAlpha();
    L20:
        this.likesReactionLayout.animate().alpha(0.0f).setDuration(150).setListener(new AnonymousClass15(this)).start();
        return;
    }

    private void showPaidMessageHint() {
        HintView2 r0 = this.highlightMessageHintView;
        if (r0 == null) goto L8;
        if (r0.shown() == true) goto L12;
        removeView(this.highlightMessageHintView);
        goto L8
    L12:
        return;
    L8:
        if (disabledPaidFeatures(true) == false) goto L11;
        return;
    L11:
        if (MessagesController.getGlobalMainSettings().getInt("taptostoryhighlighthint", 0) >= 3) goto L15;
        MessagesController.getGlobalMainSettings().edit().putInt("taptostoryhighlighthint", MessagesController.getGlobalMainSettings().getInt("taptostoryhighlighthint", 0) + 1).apply();
        HintView2 r02 = new HintView2(getContext(), 3);
        this.highlightMessageHintView = r02;
        r02.setText(LocaleController.getString(R.string.LiveStoryHighlightHint));
        this.highlightMessageHintView.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.highlightMessageHintView.setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
        this.highlightMessageHintView.setOnHiddenListener(new PeerStoriesView$$ExternalSyntheticLambda24(this, r02));
        addView(this.highlightMessageHintView, LayoutHelper.createFrame(-1, 100, 87));
        this.highlightMessageHintView.show();
        updateViewOffsets();
        return;
    }

    private void showPremiumBlockedToast() {
        if (this.areLiveCommentsDisabled == false) goto L6;
        return;
    L6:
        if (this.isGroup == true) goto L8;
        ChatActivityEnterView r0 = this.chatActivityEnterView;
        int r1 = -this.shiftDp;
        this.shiftDp = r1;
        AndroidUtilities.shakeViewSpring(r0, r1);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        if (this.dialogId < 0) goto L21;
        String r02 = UserObject.getUserName(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.dialogId)));
    L22:
        boolean r12 = MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked();
        FrameLayout r2 = this.storyContainer;
        if (r12 == false) goto L25;
        Bulletin r7 = BulletinFactory.of(r2, this.resourcesProvider).createSimpleBulletin(R.raw.star_premium_2, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserBlockedRepliesNonPremium, new Object[]{r02})));
    L26:
        r7.show();
        return;
    L25:
        r7 = BulletinFactory.of(r2, this.resourcesProvider).createSimpleBulletin(R.raw.star_premium_2, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserBlockedRepliesNonPremium, new Object[]{r02})), LocaleController.getString(R.string.UserBlockedNonPremiumButton), new PeerStoriesView$$ExternalSyntheticLambda44(this));
        goto L26
    L21:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L22
    L8:
        if (this.boostsStatus != null) goto L10;
    L13:
        StoryViewer r03 = this.storyViewer;
        if (r03 == null) goto L16;
        r03.setOverlayVisible(true);
    L16:
        MessagesController.getInstance(this.currentAccount).getBoostsController().getBoostsStats(this.dialogId, new PeerStoriesView$$ExternalSyntheticLambda43(this));
        return;
    L10:
        if (this.canApplyBoost == null) goto L13;
        LimitReachedBottomSheet.openBoostsForRemoveRestrictions(fragmentForLimit(), this.boostsStatus, this.canApplyBoost, this.dialogId, true);
    }

    private void showUserViewsDialog() {
        this.storyViewer.openViews();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void toggleArchiveForStory(long r12) {
        int r1 = this.currentAccount;
        if (r12 <= 0) goto L6;
        TLRPC.User r0 = MessagesController.getInstance(r1).getUser(Long.valueOf(r12));
        String r13 = r0.first_name;
        boolean r2 = r0.stories_hidden;
        TLRPC.Chat r02 = r0;
    L7:
        AndroidUtilities.runOnUIThread(new PeerStoriesView$$ExternalSyntheticLambda57(this, MessagesController.getInstance(this.currentAccount), r12, !r2, r13, r02), 200);
        return;
    L6:
        TLRPC.Chat r03 = MessagesController.getInstance(r1).getChat(Long.valueOf(-r12));
        r13 = r03.title;
        r2 = r03.stories_hidden;
        r02 = r03;
        goto L7
    }

    private void tryToOpenRepostStory() {
        if (MessagesController.getInstance(this.currentAccount).storiesEnabled() == true) goto L5;
        return;
    L5:
        File r0 = this.currentStory.getPath();
        if (r0 != null) goto L8;
    L14:
        showDownloadAlert();
        return;
    L8:
        if (r0.exists() == false) goto L14;
        ShareAlert r02 = this.shareAlert;
        if (r02 == null) goto L12;
        r02.dismiss();
    L12:
        AndroidUtilities.runOnUIThread(new PeerStoriesView$$ExternalSyntheticLambda29(this), 120);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updatePosition(boolean r44) {
        if (this.storyItems.isEmpty() == true) goto L5;
    L7:
        this.forceUpdateOffsets = true;
        StoryItemHolder r2 = this.currentStory;
        TL_stories.StoryItem r3 = r2.storyItem;
        StoriesController.UploadingStory r22 = r2.uploadingStory;
        String r8 = StoriesUtilities.getStoryImageFilter();
        this.lastNoThumb = false;
        this.unsupported = false;
        int r5 = this.selectedPosition;
        boolean r6 = this.isUploading;
        boolean r7 = this.isEditing;
        boolean r9 = this.isFailed;
        StoryViewer r10 = this.storyViewer;
        if (r10 == null) goto L25;
        StoriesController.StoriesList r102 = r10.storiesList;
        if (r102 == null) goto L25;
        if (r102.type != 4) goto L25;
        if (r5 >= 0) goto L15;
    L17:
        StoriesController.UploadingStory r103 = null;
    L18:
        int r13 = r5 - this.uploadingStories.size();
        if (r13 >= 0) goto L21;
    L23:
        TL_stories.StoryItem r132 = null;
    L24:
        TL_stories.StoryItem r15 = r132;
        this.currentStory.editingSourceItem = null;
        int r133 = 150;
        if (r103 == null) goto L51;
        this.isEditing = false;
        boolean r14 = r103.failed;
        this.isFailed = r14;
        this.isUploading = !r14;
        this.imageReceiver.setCrossfadeWithOldImage(false);
        this.imageReceiver.setCrossfadeDuration(150);
        Bitmap r134 = r103.entry.thumbBitmap;
        if (r134 == null) goto L41;
        Bitmap r135 = Bitmap.createBitmap(r134);
        Utilities.blurBitmap(r135, 3);
        BitmapDrawable r142 = new BitmapDrawable(r135);
    L43:
        if (r103.isVideo == false) goto L45;
    L46:
        int r26 = r5;
        boolean r27 = r6;
        boolean r28 = r7;
        boolean r29 = r9;
        StoriesController.UploadingStory r1 = r103;
        int r24 = 4;
        this.imageReceiver.setImage(null, null, ImageLocation.getForPath(r1.firstFramePath), r8, null, null, r142, 0, null, null, 0);
    L49:
        this.currentStory.set(r1);
        this.storyAreasView.set(null, StoryMediaAreasView.getMediaAreasFor(r1.entry), this.emojiAnimationsOverlay);
        this.allowShareLink = false;
        this.allowRepost = false;
        this.allowShare = false;
    L50:
        boolean r12 = r28;
    L198:
        TL_stories.StoryItem r4 = this.currentStory.storyItem;
        if (r4 == null) goto L202;
        if (r44 == true) goto L202;
        this.storyViewer.dayStoryId = r4.id;
    L202:
        this.storyViewer.storiesViewPager.checkAllowScreenshots();
        this.imageChanged = true;
        if (this.isSelf == false) goto L205;
    L206:
        updateUserViews(false);
    L207:
        StoryItemHolder r42 = this.currentStory;
        if (getStoryId(r42.storyItem, r42.uploadingStory) == getStoryId(r3, r22)) goto L216;
        if (r22 == null) goto L215;
        TL_stories.StoryItem r43 = this.currentStory.storyItem;
        if (r43 == null) goto L215;
        if (TextUtils.equals(r22.path, r43.attachPath) == true) goto L216;
    L215:
        boolean r45 = false;
    L217:
        if (r45 == true) goto L219;
    L225:
        boolean r16 = false;
    L226:
        StoryViewer r52 = this.storyViewer;
        if (r52 == null) goto L233;
        LivePlayer r53 = r52.livePlayer;
        if (r53 == null) goto L233;
        if (this.watchersCount == r53.getWatchersCount()) goto L233;
        boolean r54 = true;
    L235:
        if (r22 == null) goto L240;
        String r92 = r22.path;
        if (r92 == null) goto L240;
        if (r92.equals(this.currentStory.getLocalPath()) == false) goto L240;
    L269:
        if (r54 == true) goto L275;
        if (r22 != null) goto L272;
    L274:
        long r19 = 0;
        int r162 = 2;
    L363:
        StoryItemHolder r17 = this.currentStory;
        TL_stories.StoryItem r62 = r17.storyItem;
        if (r3 == r62) goto L366;
    L378:
        this.currentStory.updateCaption();
    L379:
        StoryItemHolder r18 = this.currentStory;
        if (r18.captionTranslated == false) goto L382;
    L383:
        Delegate r110 = this.delegate;
        if (r110 == null) goto L387;
        r110.setTranslating(false);
    L387:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L397;
        StoryViewer r111 = this.storyViewer;
        if (r111 == null) goto L397;
        LivePlayer r112 = r111.livePlayer;
        if (r112 == null) goto L397;
        if (this.currentStory.isThisCall(r112.getCallId()) == false) goto L397;
        if (this.storyViewer.livePlayer.commentsDisabled() == false) goto L397;
        boolean r113 = true;
    L399:
        if (r113 == this.areLiveCommentsDisabled) goto L422;
        this.areLiveCommentsDisabled = r113;
        if (r113 == false) goto L404;
        createPremiumBlockedText();
    L404:
        if (this.premiumBlockedText != null) goto L406;
    L410:
        ChatActivityEnterView r114 = this.chatActivityEnterView;
        if (r114 != null) goto L413;
    L421:
        checkStealthMode(true);
        goto L422
    L413:
        if (this.isPremiumBlocked == false) goto L417;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L417;
    L419:
        boolean r23 = false;
    L420:
        r114.setEnabled(r23);
        this.chatActivityEnterView.updateSendAsButton();
    L417:
        if (this.areLiveCommentsDisabled == true) goto L419;
        r23 = true;
        goto L420
    L406:
        if (this.isPremiumBlocked == false) goto L408;
    L409:
        updatePremiumBlockedText();
        goto L410
    L408:
        if (this.areLiveCommentsDisabled == false) goto L410;
    L422:
        LinearLayout r115 = this.premiumBlockedText;
        if (r115 == null) goto L434;
        if (this.isPremiumBlocked == false) goto L429;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L429;
    L430:
        int r63 = 0;
    L432:
        r115.setVisibility(r63);
    L429:
        if (this.areLiveCommentsDisabled == true) goto L430;
        r63 = 8;
    L434:
        if (this.unsupported == false) goto L445;
        createUnsupportedContainer();
        createReplyDisabledView();
        this.unsupportedContainer.setVisibility(0);
        this.replyDisabledTextView.setVisibility(0);
        this.allowShareLink = false;
        this.allowRepost = false;
        this.allowShare = false;
        ChatActivityEnterView r116 = this.chatActivityEnterView;
        if (r116 == null) goto L438;
        r116.setVisibility(8);
    L438:
        FrameLayout r117 = this.selfView;
        if (r117 == null) goto L441;
        r117.setVisibility(8);
    L441:
        LinearLayout r118 = this.bottomActionsLinearLayout;
        if (r118 == null) goto L551;
        r118.setVisibility(0);
    L551:
        ChatActivitySideControlsButtonsLayout r119 = this.sideControlsButtonsLayout;
        if (r119 == null) goto L562;
        ChatActivityEnterView r64 = this.chatActivityEnterView;
        if (r64 != null) goto L556;
    L560:
        int r65 = 8;
    L561:
        r119.setVisibility(r65);
        goto L562
    L556:
        if (r64.getVisibility() != 0) goto L560;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L560;
        r65 = 0;
    L562:
        CommentButton r120 = this.commentButton;
        if (r120 != null) goto L565;
    L571:
        MuteButton r121 = this.muteButton;
        if (r121 == null) goto L599;
        if (this.unsupported == false) goto L576;
    L582:
        int r66 = 8;
    L583:
        r121.setVisibility(r66);
        MuteButton r122 = this.muteButton;
        LivePlayer r67 = LivePlayer.recording;
        if (r67 != null) goto L586;
    L589:
        boolean r68 = false;
    L590:
        r122.setMuted(r68, true);
        MuteButton r123 = this.muteButton;
        LivePlayer r69 = LivePlayer.recording;
        if (r69 != null) goto L593;
    L596:
        boolean r610 = true;
    L597:
        r123.setConnected(r610, true);
        goto L599
    L593:
        if (r69.isConnected() == true) goto L596;
        r610 = false;
        goto L597
    L586:
        if (r67.isMuted() == false) goto L589;
        r68 = true;
        goto L590
    L576:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L582;
        LivePlayer r611 = LivePlayer.recording;
        if (r611 == null) goto L582;
        if (this.currentStory.isThisCall(r611.getCallId()) == false) goto L582;
        r66 = 0;
    L599:
        if (this.starsButton == null) goto L623;
        PaidReactionButton.PaidReactionButtonEffectsView r124 = this.starsButtonEffectsView;
        if (this.unsupported == false) goto L603;
    L605:
        int r612 = 8;
    L606:
        r124.setVisibility(r612);
        PaidReactionButton r125 = this.starsButton;
        if (this.unsupported == false) goto L609;
    L611:
        int r613 = 8;
    L612:
        r125.setVisibility(r613);
        FrameLayout.LayoutParams r126 = (FrameLayout.LayoutParams) this.starsButton.getLayoutParams();
        MuteButton r614 = this.muteButton;
        if (r614 != null) goto L615;
    L618:
        float r615 = 7.0f;
    L617:
        int r616 = AndroidUtilities.dp(r615);
        if (r126.rightMargin == r616) goto L623;
        r126.rightMargin = r616;
        this.starsButton.setLayoutParams(r126);
        goto L623
    L615:
        if (r614.getVisibility() != 0) goto L618;
        r615 = 54.0f;
        goto L617
    L609:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L611;
        r613 = 0;
        goto L612
    L603:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L605;
        r612 = 0;
    L623:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L651;
        StoryItemHolder r127 = this.currentStory;
        if (r127.caption != null) goto L633;
        if (r127.getReply() != null) goto L633;
        if (this.currentStory.getMusic() == null) goto L651;
    L633:
        if (this.unsupported == true) goto L651;
        StoryCaptionView.StoryCaptionTextView r128 = this.storyCaptionView.captionTextview;
        StoryItemHolder r617 = this.currentStory;
        CharSequence r72 = r617.caption;
        StoryCaptionView.Panel r292 = r617.getReply();
        StoryCaptionView.Panel r30 = this.currentStory.getMusic();
        if (this.storyViewer.isTranslating == false) goto L643;
        StoryItemHolder r618 = this.currentStory;
        if (r618.captionTranslated == true) goto L643;
        TL_stories.StoryItem r619 = r618.storyItem;
        if (r619 == null) goto L643;
        if (r619.translated == false) goto L643;
        boolean r31 = true;
    L645:
        if (r3 != this.currentStory.storyItem) goto L648;
        boolean r32 = true;
    L649:
        r128.setText(r72, r292, r30, r31, r32);
        this.storyCaptionView.setVisibility(0);
    L654:
        TL_stories.StoryItem r129 = this.currentStory.storyItem;
        if (r129 == null) goto L664;
        TLRPC.MessageMedia r130 = r129.media;
        if ((r130 instanceof TLRPC.TL_messageMediaVideoStream) == false) goto L664;
        if (this.liveCommentsView.setup(this.dialogId, ((TLRPC.TL_messageMediaVideoStream) r130).call) == false) goto L663;
        this.liveCommentsView.setCollapsed(false, false);
        this.messageStars = r19;
        ChatActivityEnterView r131 = this.chatActivityEnterView;
        if (r131 == null) goto L663;
        r131.checkSendButton(true);
        this.chatActivityEnterView.updateSendButtonPaid();
        checkStealthMode(true);
    L663:
        this.liveCommentsView.setVisibility(0);
    L665:
        this.storyContainer.invalidate();
        if (this.delegate != null) goto L668;
    L670:
        boolean r136 = this.isChannel;
        ImageView r33 = this.shareButton;
        if (r136 == false) goto L695;
        if (this.allowShare == true) goto L675;
    L677:
        int r137 = r24;
    L678:
        r33.setVisibility(r137);
        FrameLayout r138 = this.repostButtonContainer;
        if (r138 != null) goto L681;
    L687:
        FrameLayout r139 = this.likeButtonContainer;
        if (this.isFailed == false) goto L690;
    L692:
        int r34 = 8;
    L693:
        r139.setVisibility(r34);
    L710:
        this.likeButtonContainer.requestLayout();
        this.storyViewer.savedPositions.append(this.dialogId, r26);
        if (this.isActive == false) goto L713;
        requestVideoPlayer(0);
        updatePreloadImages();
        this.imageReceiver.bumpPriority();
    L713:
        this.liveCommentsView.setLivePlayer(this.storyViewer.livePlayer);
        this.listPosition = 0;
        if (this.storyViewer.storiesList == null) goto L726;
        TL_stories.StoryItem r140 = this.currentStory.storyItem;
        if (r140 == null) goto L726;
        int r141 = r140.id;
        int r35 = 0;
    L719:
        if (r35 >= this.storyViewer.storiesList.messageObjects.size()) goto L726;
        MessageObject r620 = this.storyViewer.storiesList.messageObjects.get(r35);
        if (r620 == null) goto L725;
        if (r620.getId() != r141) goto L725;
        this.listPosition = r35;
    L725:
        r35 = r35 + 1;
    L726:
        int r143 = this.selectedPosition;
        this.linesPosition = r143;
        int r36 = this.count;
        this.linesCount = r36;
        if (this.storyViewer.reversed == false) goto L729;
        this.linesPosition = (r36 - 1) - r143;
    L729:
        boolean r144 = this.currentStory.isVideo();
        FrameLayout r37 = this.muteIconContainer;
        if (r144 == false) goto L744;
        r37.setVisibility(0);
        if (this.currentStory.hasSound() == false) goto L734;
        float r145 = 1.0f;
    L735:
        this.muteIconViewAlpha = r145;
        boolean r146 = this.currentStory.hasSound();
        RLottieImageView r38 = this.muteIconView;
        if (r146 == false) goto L742;
        r38.setVisibility(0);
        this.noSoundIconView.setVisibility(8);
        FrameLayout r147 = this.muteIconContainer;
        if (this.storyViewer.soundEnabled() == false) goto L740;
        int r39 = R.string.Mute;
    L741:
        r147.setContentDescription(LocaleController.getString(r39));
    L743:
        this.muteIconContainer.setAlpha(this.muteIconViewAlpha * (1.0f - this.outT));
    L745:
        StoryItemHolder r148 = this.currentStory;
        StoriesController.UploadingStory r310 = r148.uploadingStory;
        if (r310 == null) goto L755;
        StoryPrivacyButton r149 = this.privacyButton;
        boolean r73 = this.isSelf;
        if (r45 == true) goto L750;
    L752:
        boolean r82 = false;
    L753:
        r149.set(r73, r310, r82);
    L772:
        this.editedPrivacy = false;
        StoryPrivacyButton r150 = this.privacyButton;
        if (this.muteIconContainer.getVisibility() != 0) goto L775;
        float r311 = -AndroidUtilities.dp(44.0f);
    L776:
        r150.setTranslationX(r311);
        if (r54 == false) goto L785;
        this.drawReactionEffect = false;
        TL_stories.StoryItem r151 = this.currentStory.storyItem;
        if (r151 == null) goto L784;
        TLRPC.Reaction r152 = r151.sent_reaction;
        if (r152 == null) goto L784;
        this.storiesLikeButton.setReaction(ReactionsLayoutInBubble.VisibleReaction.fromTL(r152));
    L784:
        this.storiesLikeButton.setReaction(null);
    L785:
        StoriesController.UploadingStory r153 = this.currentStory.uploadingStory;
        if (r153 == null) goto L797;
        if (r153.failed == false) goto L797;
        createFailView();
        this.failView.set(this.currentStory.uploadingStory.entry.error);
        this.failView.setVisibility(0);
        ViewPropertyAnimator r154 = this.failViewAnimator;
        if (r154 == null) goto L792;
        r154.cancel();
        this.failViewAnimator = null;
    L792:
        StoryFailView r155 = this.failView;
        if (r45 == false) goto L795;
        ViewPropertyAnimator r156 = r155.animate().alpha(1.0f).setDuration(180).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.failViewAnimator = r156;
        r156.start();
    L806:
        this.sharedResources.setIconMuted(!this.storyViewer.soundEnabled(), false);
        if (this.isActive == false) goto L812;
        if (this.currentStory.storyItem == null) goto L812;
        FileLog.d("StoryViewer displayed story dialogId=" + this.dialogId + " storyId=" + this.currentStory.storyItem.id + " " + StoryItemHolder.m19636$$Nest$mgetMediaDebugString(this.currentStory));
    L812:
        if (this.isSelf == false) goto L814;
        SelfStoryViewsPage.preload(this.currentAccount, this.dialogId, this.currentStory.storyItem);
    L814:
        SimpleTextView r157 = this.headerView.titleView;
        StoriesController.StoriesList r312 = this.storyViewer.storiesList;
        if (r312 != null) goto L817;
    L820:
        int r46 = 0;
    L821:
        r157.setPadding(0, 0, r46, 0);
        MessagesController.getInstance(this.currentAccount).getTranslateController().detectStoryLanguage(this.currentStory.storyItem);
        if (r44 == false) goto L824;
    L832:
        HintView2 r158 = this.soundTooltip;
        if (r158 == null) goto L839;
        if (r158.shown() == false) goto L839;
    L837:
        int r1310 = 0;
    L845:
        ImageView r159 = this.optionsIconView;
        if (r159 != null) goto L848;
    L858:
        ImageView r160 = this.pipIconView;
        if (r160 != null) goto L861;
        return;
    L861:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L863;
    L865:
        int r47 = 8;
    L866:
        r160.setVisibility(r47);
        return;
    L863:
        if (this.privacyButton.draw == true) goto L865;
        r47 = r1310;
        goto L866
    L848:
        if (isBotsPreview() == true) goto L850;
    L856:
        int r48 = r1310;
    L857:
        r159.setVisibility(r48);
        goto L858
    L850:
        if (isEditBotsPreview() == true) goto L856;
        StoryItemHolder r313 = this.currentStory;
        if (r313 != null) goto L854;
    L855:
        r48 = 8;
        goto L857
    L854:
        if (StoryItemHolder.m19635$$Nest$fgetisVideo(r313) == true) goto L856;
    L839:
        if (this.currentStory.hasSound() == false) goto L837;
        if (this.storyViewer.soundEnabled() == true) goto L837;
        r1310 = 0;
        if (MessagesController.getGlobalMainSettings().getInt("taptostorysoundhint", 0) >= r162) goto L845;
        AndroidUtilities.cancelRunOnUIThread(this.showTapToSoundHint);
        AndroidUtilities.runOnUIThread(this.showTapToSoundHint, 250);
        goto L845
    L824:
        if (this.isSelf == true) goto L832;
        if (this.reactionsTooltipRunnable != null) goto L832;
        if (SharedConfig.storyReactionsLongPressHint == true) goto L832;
        if (SharedConfig.storiesIntroShown == false) goto L832;
        PeerStoriesView$$ExternalSyntheticLambda19 r161 = new PeerStoriesView$$ExternalSyntheticLambda19(this);
        this.reactionsTooltipRunnable = r161;
        AndroidUtilities.runOnUIThread(r161, 500);
        goto L832
    L817:
        if (r312.getCount() == this.linesCount) goto L820;
        r46 = AndroidUtilities.dp(56.0f);
        goto L821
    L795:
        r155.setAlpha(1.0f);
    L797:
        if (this.failView == null) goto L806;
        ViewPropertyAnimator r163 = this.failViewAnimator;
        if (r163 == null) goto L801;
        r163.cancel();
        this.failViewAnimator = null;
    L801:
        if (r45 == true) goto L803;
    L805:
        this.failView.setAlpha(0.0f);
        this.failView.setVisibility(8);
        goto L806
    L803:
        if (this.failView.getVisibility() != 0) goto L805;
        ViewPropertyAnimator r164 = this.failView.animate().alpha(0.0f).setDuration(180).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).withEndAction(new PeerStoriesView$$ExternalSyntheticLambda18(this));
        this.failViewAnimator = r164;
        r164.start();
        goto L806
    L775:
        r311 = 0.0f;
        goto L776
    L750:
        if (this.editedPrivacy == false) goto L752;
        r82 = true;
        goto L753
    L755:
        TL_stories.StoryItem r165 = r148.storyItem;
        StoryPrivacyButton r314 = this.privacyButton;
        if (r165 == null) goto L764;
        boolean r74 = this.isSelf;
        if (r45 == true) goto L760;
    L762:
        boolean r83 = false;
    L763:
        r314.set(r74, r165, r83);
        goto L772
    L760:
        if (this.editedPrivacy == false) goto L762;
        r83 = true;
        goto L763
    L764:
        boolean r166 = this.isSelf;
        if (r45 == true) goto L767;
    L770:
        boolean r75 = false;
    L771:
        r314.set(r166, null, r75);
        goto L772
    L767:
        if (this.editedPrivacy == false) goto L770;
        r75 = true;
        goto L771
    L740:
        r39 = R.string.Unmute;
        goto L741
    L742:
        r38.setVisibility(8);
        this.noSoundIconView.setVisibility(0);
        this.muteIconContainer.setContentDescription(LocaleController.getString(R.string.NoSound));
        goto L743
    L734:
        r145 = 0.5f;
        goto L735
    L744:
        r37.setVisibility(8);
        goto L745
    L690:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L692;
        r34 = 0;
        goto L693
    L681:
        if (this.allowRepost == true) goto L683;
    L685:
        int r315 = 8;
    L686:
        r138.setVisibility(r315);
        goto L687
    L683:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L685;
        r315 = 0;
        goto L686
    L675:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L677;
        r137 = 0;
        goto L678
    L695:
        if (this.allowShare == true) goto L697;
    L699:
        int r167 = r24;
    L700:
        r33.setVisibility(r167);
        FrameLayout r168 = this.repostButtonContainer;
        if (r168 == null) goto L703;
        r168.setVisibility(8);
    L703:
        FrameLayout r169 = this.likeButtonContainer;
        if (this.isSelf == false) goto L706;
    L708:
        int r316 = 8;
    L709:
        r169.setVisibility(r316);
        this.likeButtonContainer.getLayoutParams().width = AndroidUtilities.dp(40.0f);
        goto L710
    L706:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L708;
        r316 = 0;
        goto L709
    L697:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L699;
        r167 = 0;
        goto L700
    L668:
        if (isSelectedPeer() == false) goto L670;
        this.delegate.onPeerSelected(this.dialogId, this.selectedPosition);
    L664:
        this.liveCommentsView.setup(this.dialogId, null);
        this.liveCommentsView.setVisibility(8);
        goto L665
    L648:
        r32 = false;
    L643:
        r31 = false;
    L651:
        if (this.isActive == false) goto L653;
        this.delegate.setIsCaption(false);
        Delegate r170 = this.delegate;
        this.isCaptionPartVisible = false;
        r170.setIsCaptionPartVisible(false);
    L653:
        this.storyCaptionView.setVisibility(8);
        goto L654
    L565:
        if (this.unsupported == false) goto L567;
    L569:
        int r621 = 8;
    L570:
        r120.setVisibility(r621);
        this.commentButton.setCollapsed(this.liveCommentsView.isCollapsed(), false);
        this.commentButton.setCount(this.liveCommentsView.getUnreadMessagesCount());
        goto L571
    L567:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L569;
        r621 = 0;
        goto L570
    L445:
        if (this.dialogId >= r19) goto L447;
        TLRPC.Chat r1210 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
    L449:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L455;
        if (this.chatActivityEnterView != null) goto L453;
        createEnterView();
    L453:
        createCommentButton();
        createPaidReactionsButton();
        createMuteButton();
        this.chatActivityEnterView.setVisibility(0);
    L476:
        ChatActivityEnterView r171 = this.chatActivityEnterView;
        if (r171 == null) goto L495;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L481;
        PeerStoriesView$$ExternalSyntheticLambda17 r1211 = new PeerStoriesView$$ExternalSyntheticLambda17(this);
    L482:
        r171.setOnSendButtonLongClick(r1211);
        this.chatActivityEnterView.setLiveComment(StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory), disabledPaidFeatures(true));
        ChatActivityEnterView r172 = this.chatActivityEnterView;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L485;
    L492:
        boolean r622 = false;
    L493:
        r172.setSuggestionButtonVisible(r622, true);
        goto L495
    L485:
        if (disabledPaidFeatures(true) == true) goto L492;
        if (this.keyboardVisible == false) goto L489;
    L490:
        r622 = true;
        goto L493
    L489:
        if (this.chatActivityEnterView.emojiViewVisible == false) goto L492;
    L481:
        r1211 = null;
    L495:
        if (this.isPremiumBlocked == false) goto L500;
        if (this.premiumBlockedText != null) goto L500;
        createPremiumBlockedText();
    L500:
        if (this.premiumBlockedText != null) goto L502;
    L515:
        ChatActivityEnterView r173 = this.chatActivityEnterView;
        if (r173 != null) goto L518;
    L526:
        FrameLayout r174 = this.selfView;
        if (r174 != null) goto L529;
    L535:
        FrameLayout r175 = this.unsupportedContainer;
        if (r175 == null) goto L539;
        r175.setVisibility(8);
    L539:
        if (UserObject.isService(this.dialogId) == false) goto L541;
        createReplyDisabledView();
        this.replyDisabledTextView.setVisibility(0);
    L544:
        LinearLayout r176 = this.bottomActionsLinearLayout;
        if (r176 == null) goto L551;
        if (isBotsPreview() == false) goto L549;
        int r623 = 8;
    L550:
        r176.setVisibility(r623);
        goto L551
    L549:
        r623 = 0;
        goto L550
    L541:
        TextView r177 = this.replyDisabledTextView;
        if (r177 == null) goto L544;
        r177.setVisibility(8);
        goto L544
    L529:
        if (this.isSelf == true) goto L531;
    L533:
        int r624 = 8;
    L534:
        r174.setVisibility(r624);
        goto L535
    L531:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L533;
        r624 = 0;
        goto L534
    L518:
        if (this.isPremiumBlocked == false) goto L522;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L522;
    L524:
        boolean r625 = false;
    L525:
        r173.setEnabled(r625);
    L522:
        if (this.areLiveCommentsDisabled == true) goto L524;
        r625 = true;
        goto L525
    L502:
        if (this.isPremiumBlocked == false) goto L504;
    L505:
        updatePremiumBlockedText();
    L506:
        LinearLayout r178 = this.premiumBlockedText;
        if (this.isPremiumBlocked == false) goto L511;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L511;
    L512:
        int r626 = 0;
    L514:
        r178.setVisibility(r626);
    L511:
        if (this.areLiveCommentsDisabled == true) goto L512;
        r626 = 8;
        goto L514
    L504:
        if (this.areLiveCommentsDisabled == false) goto L506;
    L455:
        if (UserObject.isService(this.dialogId) == false) goto L457;
    L458:
        ChatActivityEnterView r179 = this.chatActivityEnterView;
        if (r179 == null) goto L462;
        r179.setVisibility(8);
    L462:
        if (this.isSelf == true) goto L476;
        if (this.isChannel == true) goto L466;
    L471:
        ChatActivityEnterView r180 = this.chatActivityEnterView;
        if (r180 == null) goto L476;
        if (r180 != null) goto L475;
        createEnterView();
    L475:
        this.chatActivityEnterView.setVisibility(0);
        goto L476
    L466:
        if (this.isGroup == false) goto L476;
        if (ChatObject.canSendPlain(r1210) == true) goto L471;
        if (ChatObject.isPossibleRemoveChatRestrictionsByBoosts(r1210) == false) goto L476;
    L457:
        if (isBotsPreview() == false) goto L462;
    L447:
        r1210 = null;
    L397:
        r113 = false;
        goto L399
    L382:
        if (r3 == r18.storyItem) goto L387;
    L366:
        if (r22 != r17.uploadingStory) goto L378;
        boolean r181 = r17.captionTranslated;
        if (r62 != null) goto L370;
    L376:
        boolean r25 = false;
    L377:
        if (r181 == r25) goto L379;
    L370:
        if (r62.translated == false) goto L376;
        if (r62.translatedText == null) goto L376;
        if (TextUtils.equals(r62.translatedLng, TranslateAlert2.getToLanguage()) == false) goto L376;
        r25 = true;
        goto L377
    L272:
        if (this.currentStory.uploadingStory != null) goto L274;
    L275:
        this.headerView.setOnSubtitleClick(null);
        this.watchersCount = 0;
        setTitle(false, this.dialogId, StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory));
        StoriesController.UploadingStory r11 = this.currentStory.uploadingStory;
        if (r11 != null) goto L278;
        boolean r1110 = isBotsPreview();
        StoryItemHolder r1311 = this.currentStory;
        if (r1110 == false) goto L297;
        TL_stories.StoryItem r1111 = r1311.storyItem;
        if (r1111 == null) goto L296;
        TLRPC.MessageMedia r1112 = r1111.media;
        if (r1112 == null) goto L296;
        if (r1112.document == null) goto L294;
        CharSequence r1113 = LocaleController.formatStoryDate(r14.date);
    L280:
        r19 = 0;
        CharSequence r1114 = r1113;
    L281:
        r162 = 2;
        CharSequence r1115 = r1114;
    L343:
        if (r1115 == null) goto L357;
        StoryViewer r627 = this.storyViewer;
        CharSequence r1116 = r1115;
        if (r627 == null) goto L356;
        StoriesController.StoriesList r628 = r627.storiesList;
        r1116 = r1115;
        if (r628 == null) goto L356;
        TL_stories.StoryItem r76 = this.currentStory.storyItem;
        r1116 = r1115;
        if (r76 == null) goto L356;
        r1116 = r1115;
        if (r628.isPinned(r76.id) == false) goto L356;
        boolean r629 = r1115 instanceof SpannableStringBuilder;
        CharSequence r1117 = r1115;
        if (r629 == true) goto L355;
        r1117 = new SpannableStringBuilder(r1115);
    L355:
        SpannableString r630 = new SpannableString("p ");
        r630.setSpan(new ColoredImageSpan(R.drawable.msg_pin_mini), 0, 1, 33);
        ((SpannableStringBuilder) r1117).insert(0, r630);
        r1116 = r1117;
    L356:
        this.headerView.setSubtitle(r1116, r16);
    L357:
        HintView2 r182 = this.privacyHint;
        if (r182 == null) goto L360;
        r182.hide(false);
    L360:
        HintView2 r183 = this.soundTooltip;
        if (r183 == null) goto L363;
        r183.hide(false);
        goto L363
    L294:
        if (r1112.photo == null) goto L296;
        r1113 = LocaleController.formatStoryDate(r11.date);
    L296:
        r19 = 0;
        r1114 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L281
    L297:
        TL_stories.StoryItem r1118 = r1311.storyItem;
        if (r1118 != null) goto L300;
        r19 = 0;
        r162 = 2;
        r1115 = null;
        goto L343
    L300:
        if ((r1118.media instanceof TLRPC.TL_messageMediaVideoStream) == false) goto L308;
        SpannableStringBuilder r1119 = new SpannableStringBuilder(LocaleController.getString(R.string.LiveStoryBadge));
        r1119.setSpan(new AnonymousClass32(this), 0, r1119.length(), 33);
        r1119.append("  ");
        StoryViewer r1312 = this.storyViewer;
        if (r1312 == null) goto L306;
        LivePlayer r1313 = r1312.livePlayer;
        if (r1313 == null) goto L306;
        this.watchersCount = r1313.getWatchersCount();
    L306:
        r1119.append(LocaleController.formatPluralStringComma("LiveStoryWatching", Math.max(1, this.watchersCount)));
        r1113 = r1119;
        goto L280
    L308:
        if (r1118.date != (-1)) goto L310;
        r1113 = LocaleController.getString(R.string.CachedStory);
        goto L280
    L310:
        r162 = 2;
        if (r1311.getReply() == null) goto L325;
        StoryCaptionView.Panel r184 = this.currentStory.getReply();
        SpannableStringBuilder r1120 = new SpannableStringBuilder();
        r19 = 0;
        SpannableString r631 = new SpannableString("r");
        r631.setSpan(new ColoredImageSpan(R.drawable.mini_repost_story), 0, r631.length(), 33);
        r1120.append(r631).append(" ");
        if (r184.peerId == null) goto L320;
        AvatarSpan r632 = new AvatarSpan(PeerHeaderView.m19624$$Nest$fgetsubtitleView(this.headerView)[0], this.currentAccount, 15.0f);
        SpannableString r77 = new SpannableString("a");
        r77.setSpan(r632, 0, 1, 33);
        r1120.append(r77).append(" ");
        long r78 = r184.peerId.longValue();
        int r84 = this.currentAccount;
        if (r78 <= 0) goto L317;
        TLRPC.User r79 = MessagesController.getInstance(r84).getUser(r184.peerId);
        r632.setUser(r79);
        r1120.append(UserObject.getUserName(r79));
    L323:
        this.headerView.setOnSubtitleClick(new PeerStoriesView$$ExternalSyntheticLambda15(this, r184));
        SpannableString r185 = new SpannableString(".");
        DotDividerSpan r633 = new DotDividerSpan();
        r633.setTopPadding(AndroidUtilities.dp(1.5f));
        r633.setSize(5.0f);
        r185.setSpan(r633, 0, r185.length(), 33);
        r1120.append(" ").append(r185).append(" ").append(LocaleController.formatShortDate(this.currentStory.storyItem.date));
        SpannableStringBuilder r1121 = r1120;
    L324:
        r16 = false;
        r1115 = r1121;
        goto L343
    L317:
        TLRPC.Chat r710 = MessagesController.getInstance(r84).getChat(Long.valueOf(-r184.peerId.longValue()));
        r632.setChat(r710);
        if (r710 == null) goto L323;
        r1120.append(r710.title);
        goto L323
    L320:
        String r634 = this.currentStory.storyItem.fwd_from.from_name;
        if (r634 == null) goto L323;
        r1120.append(r634);
        goto L323
    L325:
        r19 = 0;
        if (this.isGroup == false) goto L338;
        TL_stories.StoryItem r635 = this.currentStory.storyItem;
        if (r635 == null) goto L338;
        if (r635.from_id == null) goto L338;
        SpannableStringBuilder r186 = new SpannableStringBuilder();
        AvatarSpan r636 = new AvatarSpan(PeerHeaderView.m19624$$Nest$fgetsubtitleView(this.headerView)[0], this.currentAccount, 15.0f);
        SpannableString r711 = new SpannableString("a");
        r711.setSpan(r636, 0, 1, 33);
        r186.append(r711).append(" ");
        long r712 = DialogObject.getPeerDialogId(this.currentStory.storyItem.from_id);
        int r1122 = this.currentAccount;
        if (r712 <= 0) goto L334;
        TLRPC.User r93 = MessagesController.getInstance(r1122).getUser(Long.valueOf(r712));
        r636.setUser(r93);
        r186.append(UserObject.getUserName(r93));
    L337:
        this.headerView.setOnSubtitleClick(new PeerStoriesView$$ExternalSyntheticLambda16(this, r712));
        SpannableString r637 = new SpannableString(".");
        DotDividerSpan r713 = new DotDividerSpan();
        r713.setTopPadding(AndroidUtilities.dp(1.5f));
        r713.setSize(5.0f);
        r637.setSpan(r713, 0, r637.length(), 33);
        r186.append(" ").append(r637).append(" ").append(LocaleController.formatShortDate(this.currentStory.storyItem.date));
        r1121 = r186;
        goto L324
    L334:
        TLRPC.Chat r94 = MessagesController.getInstance(r1122).getChat(Long.valueOf(-r712));
        r636.setChat(r94);
        if (r94 == null) goto L337;
        r186.append(r94.title);
    L338:
        String r638 = LocaleController.formatStoryDate(this.currentStory.storyItem.date);
        String r639 = r638;
        if (this.currentStory.storyItem.edited == false) goto L341;
        SpannableStringBuilder r640 = SpannableStringBuilder.valueOf(r638);
        DotDividerSpan r714 = new DotDividerSpan();
        r714.setTopPadding(AndroidUtilities.dp(1.5f));
        r714.setSize(5.0f);
        r640.append(" . ").setSpan(r714, r640.length() - 2, r640.length() - 1, 0);
        r640.append(LocaleController.getString(R.string.EditedMessage));
        r639 = r640;
    L341:
        r1115 = r639;
        goto L343
    L278:
        if (r11.failed == false) goto L282;
        r1113 = LocaleController.getString(R.string.FailedToUploadStory);
        goto L280
    L282:
        r1113 = StoriesUtilities.getUploadingStr(PeerHeaderView.m19624$$Nest$fgetsubtitleView(this.headerView)[0], false, this.isEditing);
    L240:
        if (r3 == null) goto L245;
        TL_stories.StoryItem r95 = this.currentStory.storyItem;
        if (r95 == null) goto L245;
        if (r3.id == r95.id) goto L269;
    L245:
        ChatActivityEnterView r55 = this.chatActivityEnterView;
        if (r55 == null) goto L255;
        if (r3 != null) goto L249;
    L251:
        this.chatActivityEnterView.getEditField().setText(this.storyViewer.getDraft(this.dialogId, this.currentStory.storyItem));
        boolean r56 = StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory);
        ChatActivityEnterView r96 = this.chatActivityEnterView;
        if (r56 == false) goto L254;
        boolean r57 = true;
        r96.setAllowStickersAndGifs(true, false, false);
    L257:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L259;
        loadSendAsPeers(r57);
    L259:
        this.emojiAnimationsOverlay.clear();
        this.currentImageTime = 0;
        this.switchEventSent = false;
        StoriesController.UploadingStory r58 = this.currentStory.uploadingStory;
        if (r58 == null) goto L265;
        RadialProgress r97 = this.headerView.radialProgress;
        if (r97 == null) goto L264;
        r97.setProgress(r58.progress, false);
    L264:
        this.headerView.backupImageView.invalidate();
    L267:
        Bulletin.hideVisible(this.storyContainer);
        this.storyCaptionView.reset();
        cancelWaiting();
        r54 = true;
        goto L269
    L265:
        if (r16 == true) goto L267;
        PeerHeaderView.m19625$$Nest$fputprogressToUploading(this.headerView, 0.0f);
        goto L267
    L254:
        r57 = true;
        r96.setAllowStickersAndGifs(true, true, true);
        goto L257
    L249:
        if (TextUtils.isEmpty(r55.getEditField().getText()) == true) goto L251;
        this.storyViewer.saveDraft(r3.dialogId, r3, this.chatActivityEnterView.getEditField().getText());
        goto L251
    L255:
        r57 = true;
    L233:
        r54 = false;
        goto L235
    L219:
        if (this.isEditing == r12) goto L221;
    L224:
        r16 = true;
        goto L226
    L221:
        if (this.isUploading != r27) goto L224;
        if (this.isFailed == r29) goto L225;
    L216:
        r45 = true;
        goto L217
    L205:
        if (this.isChannel == false) goto L207;
    L45:
        if (r103.hadFailed == true) goto L46;
        r24 = 4;
        r28 = r7;
        r26 = r5;
        r27 = r6;
        r29 = r9;
        r1 = r103;
        this.imageReceiver.setImage(null, null, ImageLocation.getForPath(r103.path), r8, null, null, r142, 0, null, null, 0);
        goto L49
    L41:
        r142 = null;
        goto L43
    L51:
        r26 = r5;
        r27 = r6;
        r28 = r7;
        r29 = r9;
        r24 = 4;
        this.isUploading = false;
        this.isEditing = false;
        this.isFailed = false;
        if (r15 != null) goto L57;
        StoryViewer r0 = this.storyViewer;
        if (r0 == null) goto L872;
        r0.close(true);
        return;
    L872:
        return;
    L57:
        StoriesController.UploadingStory r49 = this.storiesController.findEditingStory(this.dialogId, r15);
        if (r49 == null) goto L68;
        this.isEditing = true;
        this.imageReceiver.setCrossfadeWithOldImage(false);
        ImageReceiver r187 = this.imageReceiver;
        if (this.onImageReceiverThumbLoaded == null) goto L63;
        r133 = 0;
    L63:
        r187.setCrossfadeDuration(r133);
        boolean r188 = r49.isVideo;
        ImageReceiver r59 = this.imageReceiver;
        if (r188 == false) goto L66;
        StoriesController.UploadingStory r189 = r49;
        r59.setImage(null, null, ImageLocation.getForPath(r49.firstFramePath), r8, null, 0, null, null, 0);
    L67:
        this.currentStory.set(r189);
        this.storyAreasView.set(null, StoryMediaAreasView.getMediaAreasFor(r189.entry), this.emojiAnimationsOverlay);
        this.currentStory.editingSourceItem = r15;
        this.allowShareLink = false;
        this.allowRepost = false;
        this.allowShare = false;
        goto L50
    L66:
        r189 = r49;
        r59.setImage(null, null, ImageLocation.getForPath(r189.firstFramePath), r8, null, 0, null, null, 0);
        goto L67
    L68:
        TLRPC.MessageMedia r190 = r15.media;
        if (r190 != null) goto L71;
    L73:
        boolean r410 = false;
    L74:
        r15.dialogId = this.dialogId;
        this.imageReceiver.setCrossfadeWithOldImage(r28);
        this.imageReceiver.setCrossfadeDuration(150);
        TLRPC.MessageMedia r191 = r15.media;
        if ((r191 instanceof TLRPC.TL_messageMediaUnsupported) == false) goto L77;
        this.unsupported = true;
        MessagesController.getInstance(this.currentAccount).getStoriesController().checkUnsupportedStory(this.dialogId, r15.id);
        r12 = r28;
    L133:
        r15.dialogId = this.dialogId;
        StoryMediaAreasView r411 = this.storyAreasView;
        if (r44 == false) goto L136;
        TL_stories.StoryItem r1212 = null;
    L137:
        r411.set(r1212, this.emojiAnimationsOverlay);
        this.currentStory.set(r15);
        if (this.unsupported == true) goto L146;
        TL_stories.StoryItem r412 = this.currentStory.storyItem;
        if (r412 == null) goto L146;
        if ((r412 instanceof TL_stories.TL_storyItemDeleted) == true) goto L146;
        if ((r412 instanceof TL_stories.TL_storyItemSkipped) == true) goto L146;
        boolean r413 = true;
    L147:
        this.allowShareLink = r413;
        this.allowShare = r413;
        if (r413 == false) goto L157;
        if (this.currentStory.allowScreenshots() == true) goto L152;
    L154:
        boolean r414 = false;
    L155:
        this.allowShare = r414;
        goto L157
    L152:
        if (this.currentStory.storyItem.isPublic == false) goto L154;
        r414 = true;
    L157:
        if (this.allowShare == false) goto L166;
        TL_stories.StoryItem r415 = this.currentStory.storyItem;
        if (r415.pinned == false) goto L161;
    L164:
        boolean r416 = true;
    L165:
        this.allowShare = r416;
        goto L166
    L161:
        if (StoriesUtilities.isExpired(this.currentAccount, r415) == false) goto L164;
        r416 = false;
    L166:
        boolean r417 = this.allowShare;
        this.allowRepost = r417;
        if (r417 == false) goto L178;
        if (this.isChannel == false) goto L178;
        TLRPC.Chat r418 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        if (r418 != null) goto L173;
    L175:
        boolean r419 = false;
    L176:
        this.allowRepost = r419;
        goto L178
    L173:
        if (ChatObject.isPublic(r418) == false) goto L175;
        r419 = true;
    L178:
        if (this.allowShareLink == false) goto L197;
        boolean r420 = this.isChannel;
        int r510 = this.currentAccount;
        if (r420 == false) goto L188;
        TLRPC.Chat r421 = MessagesController.getInstance(r510).getChat(Long.valueOf(-this.dialogId));
        if (r421 != null) goto L184;
    L186:
        boolean r422 = false;
    L187:
        this.allowShareLink = r422;
        goto L197
    L184:
        if (ChatObject.getPublicUsername(r421) == null) goto L186;
        r422 = true;
        goto L187
    L188:
        TLRPC.User r423 = MessagesController.getInstance(r510).getUser(Long.valueOf(this.dialogId));
        if (r423 != null) goto L191;
    L195:
        boolean r424 = false;
    L196:
        this.allowShareLink = r424;
        goto L197
    L191:
        if (UserObject.getPublicUsername(r423) == null) goto L195;
        if (this.currentStory.storyItem.isPublic == false) goto L195;
        r424 = true;
    L197:
        NotificationsController.getInstance(this.currentAccount).processReadStories(this.dialogId, r15.id);
    L146:
        r413 = false;
        goto L147
    L136:
        r1212 = r15;
        goto L137
    L77:
        String r641 = r15.attachPath;
        if (r641 == null) goto L105;
        if (r191 != null) goto L81;
        r410 = r641.toLowerCase().endsWith(".mp4");
    L81:
        TLRPC.MessageMedia r192 = r15.media;
        if (r410 == false) goto L94;
        if (r192 == null) goto L85;
        Drawable r372 = ImageLoader.createStripedBitmap(r192.getDocument().thumbs);
    L87:
        if (r15.firstFramePath != null) goto L89;
    L93:
        boolean r1710 = r28;
        TL_stories.StoryItem r193 = r15;
        this.imageReceiver.setImage(null, null, ImageLocation.getForPath(r193.attachPath), r8 + "_pframe", null, null, r372, 0, null, null, 0);
    L91:
        r15 = r193;
    L92:
        r12 = r1710;
        goto L133
    L89:
        if (ImageLoader.getInstance().isInMemCache(ImageLocation.getForPath(r15.firstFramePath).getKey(null, null, false) + "@" + r8, false) == false) goto L93;
        r1710 = r28;
        r193 = r15;
        this.imageReceiver.setImage(null, null, ImageLocation.getForPath(r15.firstFramePath), r8, null, null, r372, 0, null, null, 0);
        goto L91
    L85:
        r372 = null;
        goto L87
    L94:
        r1710 = r28;
        if (r192 == null) goto L97;
        TLRPC.Photo r1213 = r192.photo;
    L98:
        if (r1213 == null) goto L100;
        Drawable r98 = ImageLoader.createStripedBitmap(r1213.sizes);
    L101:
        ImageReceiver r425 = this.imageReceiver;
        if (r1710 == false) goto L104;
        r425.setImage(ImageLocation.getForPath(r15.attachPath), r8, ImageLocation.getForPath(r15.firstFramePath), r8, r98, 0, null, null, 0);
        goto L92
    L104:
        r425.setImage(ImageLocation.getForPath(r15.attachPath), r8, null, null, r98, 0, null, null, 0);
        goto L92
    L100:
        r98 = null;
        goto L101
    L97:
        r1213 = null;
        goto L98
    L105:
        r1710 = r28;
        StoryViewer r194 = this.storyViewer;
        if (r194.storiesList == null) goto L108;
    L109:
        StoryViewer.TransitionViewHolder r195 = r194.transitionViewHolder;
        if (r195 == null) goto L116;
        ImageReceiver r511 = r195.storyImage;
        if (r511 == null) goto L116;
        if (r195.storyId != r15.id) goto L116;
        Drawable r1214 = r511.getDrawable();
    L117:
        r15.dialogId = this.dialogId;
        TLRPC.MessageMedia r196 = r15.media;
        if (r410 == false) goto L123;
        TLRPC.PhotoSize r197 = FileLoader.getClosestPhotoSizeWithSize(r196.getDocument().thumbs, 1000);
        if (r1214 != null) goto L122;
        r1214 = ImageLoader.createStripedBitmap(r15.media.getDocument().thumbs);
    L122:
        this.imageReceiver.setImage(null, null, ImageLocation.getForDocument(r15.media.getDocument()), r8 + "_pframe", ImageLocation.getForDocument(r197, r15.media.getDocument()), r8, r1214, 0, null, r15, 0);
        goto L92
    L123:
        if (r196 == null) goto L125;
        TLRPC.Photo r198 = r196.photo;
    L126:
        if (r198 == null) goto L132;
        ArrayList<TLRPC.PhotoSize> r426 = r198.sizes;
        if (r426 == null) goto L132;
        if (r1214 != null) goto L131;
        r1214 = ImageLoader.createStripedBitmap(r426);
    L131:
        TLRPC.PhotoSize r427 = FileLoader.getClosestPhotoSizeWithSize(r198.sizes, Integer.MAX_VALUE);
        FileLoader.getClosestPhotoSizeWithSize(r198.sizes, 800);
        ImageReceiver r512 = this.imageReceiver;
        ImageLocation r715 = ImageLocation.getForPhoto(r427, r198);
        r12 = r1710;
        r512.setImage(null, null, r715, r8, null, null, r1214, 0, null, r15, 0);
    L132:
        r12 = r1710;
        this.imageReceiver.clearImage();
        goto L133
    L125:
        r198 = null;
    L116:
        r1214 = null;
        goto L117
    L108:
        if (r194.isSingleStory == false) goto L116;
    L71:
        if (MessageObject.isVideoDocument(r190.getDocument()) == false) goto L73;
        r410 = true;
        goto L74
    L21:
        if (r13 >= this.storyItems.size()) goto L23;
        r132 = this.storyItems.get(r13);
        goto L24
    L15:
        if (r5 >= this.uploadingStories.size()) goto L17;
        r103 = this.uploadingStories.get(r5);
    L25:
        if (r5 >= 0) goto L27;
    L29:
        r132 = null;
    L30:
        int r104 = r5 - this.storyItems.size();
        if (r104 >= 0) goto L33;
    L35:
        r103 = null;
        goto L24
    L33:
        if (r104 >= this.uploadingStories.size()) goto L35;
        r103 = this.uploadingStories.get(r104);
        goto L24
    L27:
        if (r5 >= this.storyItems.size()) goto L29;
        r132 = this.storyItems.get(r5);
        goto L30
    L5:
        if (this.uploadingStories.isEmpty() == false) goto L7;
    }

    private void updatePreloadImages() {
        int r1 = (int) (Math.max(AndroidUtilities.getRealScreenSize().x, AndroidUtilities.getRealScreenSize().y) / AndroidUtilities.density);
        String r12 = r1 + "_" + r1;
        this.uriesToPrepare.clear();
        this.documentsToPrepare.clear();
        int r3 = 0;
    L3:
        int r4 = this.preloadReactionHolders.size();
        ArrayList<ReactionImageHolder> r5 = this.preloadReactionHolders;
        if (r3 >= r4) goto L6;
        r5.get(r3).onAttachedToWindow(false);
        r3 = r3 + 1;
        goto L3
    L6:
        r5.clear();
        int r32 = 0;
    L8:
        if (r32 >= 2) goto L55;
        int r42 = this.selectedPosition;
        if (r32 != 0) goto L14;
        int r43 = r42 - 1;
        ImageReceiver r52 = this.leftPreloadImageReceiver;
        if (r43 >= 0) goto L18;
        r52.clearImage();
    L54:
        r32 = r32 + 1;
    L18:
        if (this.uploadingStories.isEmpty() == true) goto L23;
        if (r43 < this.storyItems.size()) goto L23;
        setStoryImage(this.uploadingStories.get(r43 - this.storyItems.size()), r52, r12);
    L23:
        if (this.storyItems.isEmpty() == true) goto L54;
        if (r43 >= 0) goto L28;
        r43 = 0;
    L28:
        if (r43 < this.storyItems.size()) goto L30;
        r43 = this.storyItems.size() - 1;
    L30:
        TL_stories.StoryItem r44 = this.storyItems.get(r43);
        r44.dialogId = this.dialogId;
        setStoryImage(r44, r52, r12);
        TLRPC.MessageMedia r53 = r44.media;
        if (r53 == null) goto L46;
        if (MessageObject.isVideoDocument(r53.getDocument()) == false) goto L46;
        TLRPC.Document r54 = r44.media.getDocument();
        if (r44.fileReference != 0) goto L57;
        r44.fileReference = FileLoader.getInstance(this.currentAccount).getFileReference(r44);
    L57:
        StringBuilder r6 = new StringBuilder();     // Catch: UnsupportedEncodingException -> L43
        r6.append("?account=");     // Catch: UnsupportedEncodingException -> L43
        r6.append(this.currentAccount);     // Catch: UnsupportedEncodingException -> L43
        r6.append("&id=");     // Catch: UnsupportedEncodingException -> L43
        r6.append(r54.id);     // Catch: UnsupportedEncodingException -> L43
        r6.append("&hash=");     // Catch: UnsupportedEncodingException -> L43
        r6.append(r54.access_hash);     // Catch: UnsupportedEncodingException -> L43
        r6.append("&dc=");     // Catch: UnsupportedEncodingException -> L43
        r6.append(r54.dc_id);     // Catch: UnsupportedEncodingException -> L43
        r6.append("&size=");     // Catch: UnsupportedEncodingException -> L43
        r6.append(r54.size);     // Catch: UnsupportedEncodingException -> L43
        r6.append("&mime=");     // Catch: UnsupportedEncodingException -> L43
        r6.append(URLEncoder.encode(r54.mime_type, "UTF-8"));     // Catch: UnsupportedEncodingException -> L43
        r6.append("&rid=");     // Catch: UnsupportedEncodingException -> L43
        r6.append(r44.fileReference);     // Catch: UnsupportedEncodingException -> L43
        r6.append("&name=");     // Catch: UnsupportedEncodingException -> L43
        r6.append(URLEncoder.encode(FileLoader.getDocumentFileName(r54), "UTF-8"));     // Catch: UnsupportedEncodingException -> L43
        r6.append("&reference=");     // Catch: UnsupportedEncodingException -> L43
        byte[] r7 = r54.file_reference;     // Catch: UnsupportedEncodingException -> L43
        if (r7 != null) goto L41;
        r7 = new byte[0];     // Catch: UnsupportedEncodingException -> L43
    L41:
        r6.append(Utilities.bytesToHex(r7));     // Catch: UnsupportedEncodingException -> L43
        r6.append("&sid=");     // Catch: UnsupportedEncodingException -> L43
        r6.append(r44.id);     // Catch: UnsupportedEncodingException -> L43
        r6.append("&did=");     // Catch: UnsupportedEncodingException -> L43
        r6.append(r44.dialogId);     // Catch: UnsupportedEncodingException -> L43
        String r62 = r6.toString();     // Catch: UnsupportedEncodingException -> L43
        this.uriesToPrepare.add(Uri.parse("tg://" + FileLoader.getAttachFileName(r54) + r62));     // Catch: UnsupportedEncodingException -> L43
        this.documentsToPrepare.add(r54);     // Catch: UnsupportedEncodingException -> L43
    L43:
        e = move-exception;
        e.printStackTrace();
    L46:
        if (r44.media_areas == null) goto L54;
        int r55 = 0;
    L49:
        if (r55 >= r44.media_areas.size()) goto L54;
        if ((r44.media_areas.get(r55) instanceof TL_stories.TL_mediaAreaSuggestedReaction) == false) goto L53;
        TL_stories.TL_mediaAreaSuggestedReaction r63 = (TL_stories.TL_mediaAreaSuggestedReaction) r44.media_areas.get(r55);
        ReactionImageHolder r72 = new ReactionImageHolder(this);
        r72.setVisibleReaction(ReactionsLayoutInBubble.VisibleReaction.fromTL(r63.reaction));
        r72.onAttachedToWindow(this.attachedToWindow);
        this.preloadReactionHolders.add(r72);
    L53:
        r55 = r55 + 1;
        goto L49
    L14:
        r43 = r42 + 1;
        r52 = this.rightPreloadImageReceiver;
        if (r43 < getStoriesCount()) goto L18;
        r52.clearImage();
        goto L54
    L55:
        this.delegate.preparePlayer(this.documentsToPrepare, this.uriesToPrepare);
    }

    private void updatePremiumBlockedText() {
        boolean r0 = this.areLiveCommentsDisabled;
        TextView r1 = this.premiumBlockedText1;
        if (r0 == false) goto L10;
        if (r1 == null) goto L6;
        r1.setText(LocaleController.getString(R.string.LiveStoryCommentsDisabled));
    L6:
        TextView r2 = this.premiumBlockedText2;
        if (r2 == null) goto L20;
        r2.setVisibility(8);
        return;
    L20:
        return;
    L10:
        if (r1 != null) goto L12;
    L16:
        TextView r02 = this.premiumBlockedText2;
        if (r02 == null) goto L21;
        r02.setVisibility(0);
        this.premiumBlockedText2.setText(LocaleController.getString(R.string.StoryRepliesLockedButton));
        return;
    L21:
        return;
    L12:
        if (this.isGroup == false) goto L14;
        int r03 = R.string.StoryGroupRepliesLocked;
    L15:
        r1.setText(LocaleController.getString(r03));
        goto L16
    L14:
        r03 = R.string.StoryRepliesLocked;
        goto L15
    }

    private void updateSelectedPosition() {
        if (this.day == null) goto L28;
        ArrayList<StoriesController.UploadingStory> r0 = this.uploadingStories;
        if (r0 != null) goto L7;
    L16:
        int r02 = 0;
    L17:
        int r3 = this.day.indexOf(Integer.valueOf(this.storyViewer.dayStoryId));
        if (r3 < 0) goto L20;
    L27:
        this.selectedPosition = r02 + r3;
    L44:
        if (this.selectedPosition != (-1)) goto L51;
        this.selectedPosition = 0;
        return;
    L51:
        return;
    L20:
        if (this.day.isEmpty() == true) goto L27;
        if (this.storyViewer.dayStoryId <= this.day.get(0).intValue()) goto L25;
        r3 = 0;
        goto L27
    L25:
        if (this.storyViewer.dayStoryId >= this.day.get(r5.size() - 1).intValue()) goto L27;
        r3 = this.day.size() - 1;
        goto L27
    L7:
        if (r0.isEmpty() == true) goto L16;
        r02 = this.uploadingStories.size();
        int r32 = 0;
    L10:
        if (r32 >= this.uploadingStories.size()) goto L17;
        if (Long.hashCode(this.uploadingStories.get(r32).random_id) == this.storyViewer.dayStoryId) goto L13;
        r32 = r32 + 1;
        goto L10
    L13:
        this.selectedPosition = r32;
        return;
    L28:
        int r03 = this.storyViewer.savedPositions.get(this.dialogId, -1);
        this.selectedPosition = r03;
        if (r03 != (-1)) goto L44;
        if (this.storyViewer.isSingleStory == true) goto L44;
        TL_stories.PeerStories r04 = this.userStories;
        if (r04 == null) goto L44;
        if (r04.max_read_id <= 0) goto L44;
        int r05 = 0;
    L38:
        if (r05 >= this.storyItems.size()) goto L44;
        if (this.storyItems.get(r05).id > this.userStories.max_read_id) goto L41;
        r05 = r05 + 1;
        goto L38
    L41:
        this.selectedPosition = r05;
        goto L44
    }

    private void updateSpeedItem(boolean r5) {
        ActionBarMenuSubItem r0 = this.speedItem;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.speedLayout != null) goto L8;
        return;
    L8:
        if (r0.getVisibility() != 0) goto L31;
        if (r5 == true) goto L12;
    L27:
        this.speedLayout.update(StoryViewer.currentSpeed, r5);
        return;
    L12:
        if (Math.abs(StoryViewer.currentSpeed - 0.2f) >= 0.05f) goto L15;
        this.speedItem.setSubtext(LocaleController.getString(R.string.VideoSpeedVerySlow));
        goto L27
    L15:
        if (Math.abs(StoryViewer.currentSpeed - 0.5f) >= 0.05f) goto L18;
        this.speedItem.setSubtext(LocaleController.getString(R.string.VideoSpeedSlow));
        goto L27
    L18:
        if (Math.abs(StoryViewer.currentSpeed - 1.0f) >= 0.05f) goto L21;
        this.speedItem.setSubtext(LocaleController.getString(R.string.VideoSpeedNormal));
        goto L27
    L21:
        if (Math.abs(StoryViewer.currentSpeed - 1.5f) >= 0.05f) goto L23;
        this.speedItem.setSubtext(LocaleController.getString(R.string.VideoSpeedFast));
        goto L27
    L23:
        float r02 = Math.abs(StoryViewer.currentSpeed - 2.0f);
        ActionBarMenuSubItem r1 = this.speedItem;
        if (r02 >= 0.05f) goto L26;
        r1.setSubtext(LocaleController.getString(R.string.VideoSpeedVeryFast));
        goto L27
    L26:
        r1.setSubtext(LocaleController.formatString(R.string.VideoSpeedCustom, new Object[]{SpeedIconDrawable.formatNumber(StoryViewer.currentSpeed) + "x"}));
        goto L27
    }

    private void updateStoryItems() {
        this.storyItems.clear();
        StoryViewer r0 = this.storyViewer;
        if (r0.isSingleStory == true) goto L5;
        int r2 = 0;
        if (this.day == null) goto L34;
        StoriesController.StoriesList r1 = r0.storiesList;
        if (r1 == null) goto L34;
        if ((r1 instanceof StoriesController.BotPreviewsList) == false) goto L26;
        this.uploadingStories.clear();
        ArrayList<StoriesController.UploadingStory> r02 = MessagesController.getInstance(this.currentAccount).getStoriesController().getUploadingStories(this.dialogId);
        String r12 = ((StoriesController.BotPreviewsList) this.storyViewer.storiesList).lang_code;
        if (r02 == null) goto L26;
        int r3 = 0;
    L17:
        if (r3 >= r02.size()) goto L26;
        StoriesController.UploadingStory r4 = r02.get(r3);
        StoryEntry r5 = r4.entry;
        if (r5 == null) goto L25;
        if (r5.isEdit == true) goto L25;
        if (TextUtils.equals(r5.botLang, r12) == false) goto L25;
        this.uploadingStories.add(r4);
    L25:
        r3 = r3 + 1;
    L26:
        ArrayList<Integer> r03 = this.day;
        int r13 = r03.size();
    L27:
        if (r2 >= r13) goto L52;
        Integer r32 = r03.get(r2);
        r2 = r2 + 1;
        MessageObject r33 = this.storyViewer.storiesList.findMessageObject(r32.intValue());
        if (r33 == null) goto L27;
        TL_stories.StoryItem r34 = r33.storyItem;
        if (r34 == null) goto L27;
        this.storyItems.add(r34);
    L52:
        this.count = getStoriesCount();
        return;
    L34:
        if (r0.storiesList != null) goto L36;
        TL_stories.PeerStories r04 = r0.overrideUserStories;
        if (r04 != null) goto L41;
    L43:
        TL_stories.PeerStories r05 = this.storiesController.getStories(this.dialogId);
        this.userStories = r05;
        if (r05 != null) goto L46;
        this.userStories = this.storiesController.getStoriesFromFullPeer(this.dialogId);
    L46:
        this.totalStoriesCount = 0;
        TL_stories.PeerStories r06 = this.userStories;
        if (r06 == null) goto L49;
        this.totalStoriesCount = r06.stories.size();
        this.storyItems.addAll(this.userStories.stories);
    L49:
        this.uploadingStories.clear();
        ArrayList<StoriesController.UploadingStory> r07 = this.storiesController.getUploadingStories(this.dialogId);
        if (r07 == null) goto L52;
        this.uploadingStories.addAll(r07);
        goto L52
    L41:
        if (DialogObject.getPeerDialogId(r04.peer) != this.dialogId) goto L43;
        this.userStories = this.storyViewer.overrideUserStories;
    L36:
        if (r2 >= this.storyViewer.storiesList.messageObjects.size()) goto L52;
        this.storyItems.add(this.storyViewer.storiesList.messageObjects.get(r2).storyItem);
        r2 = r2 + 1;
        goto L36
    L5:
        if (r0.singleStoryDeleted == true) goto L52;
        this.storyItems.add(r0.singleStory);
        goto L52
    }

    private void updateUserViews(boolean r13) {
        StoryItemHolder r0 = this.currentStory;
        TL_stories.StoryItem r1 = r0.storyItem;
        if (r1 != null) goto L5;
        r1 = r0.editingSourceItem;
    L5:
        boolean r02 = this.isChannel;
        if (r02 == true) goto L11;
        if (this.isSelf == true) goto L11;
        return;
    L11:
        if (r1 == null) goto L118;
        TL_stories.StoryViews r4 = r1.views;
        if (r02 == false) goto L83;
        if (r4 != null) goto L16;
        r1.views = new TL_stories.TL_storyViews();
    L16:
        TL_stories.StoryViews r03 = r1.views;
        if (r03.views_count > 0) goto L19;
        r03.views_count = 1;
    L19:
        AnimatedTextView.AnimatedTextDrawable r42 = this.repostCounter;
        if (r42 == null) goto L30;
        int r04 = r03.forwards_count;
        if (r04 <= 0) goto L30;
        String r05 = Integer.toString(r04);
        if (r13 == true) goto L26;
    L28:
        boolean r10 = false;
    L29:
        r42.setText(r05, r10);
        this.repostCounterVisible = true;
    L31:
        int r06 = r1.views.reactions_count;
        if (r06 <= 0) goto L40;
        AnimatedTextView.AnimatedTextDrawable r43 = this.reactionsCounter;
        String r07 = Integer.toString(r06);
        if (r13 == true) goto L36;
    L38:
        boolean r102 = false;
    L39:
        r43.setText(r07, r102);
        this.reactionsCounterVisible = true;
    L41:
        float r08 = 0.0f;
        if (r13 == true) goto L54;
        AnimatedFloat r44 = this.reactionsCounterProgress;
        float r11 = 1.0f;
        if (this.reactionsCounterVisible == false) goto L46;
        float r103 = 1.0f;
    L47:
        r44.set(r103, true);
        AnimatedFloat r45 = this.repostCounterProgress;
        if (r45 == null) goto L54;
        if (this.repostCounterVisible == true) goto L53;
        r11 = 0.0f;
    L53:
        r45.set(r11, true);
        goto L54
    L46:
        r103 = 0.0f;
    L54:
        TLRPC.Chat r46 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        if (this.isGroup == false) goto L61;
        if (ChatObject.canSendPlain(r46) == false) goto L59;
    L70:
        this.selfStatusView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    L71:
        ViewGroup.LayoutParams r12 = this.likeButtonContainer.getLayoutParams();
        float r2 = AndroidUtilities.dp(40.0f);
        if (this.reactionsCounterVisible == false) goto L74;
        float r47 = this.reactionsCounter.getAnimateToWidth() + AndroidUtilities.dp(4.0f);
    L75:
        r12.width = (int) (r2 + r47);
        ((ViewGroup.MarginLayoutParams) this.selfView.getLayoutParams()).rightMargin = AndroidUtilities.dp(40.0f) + this.likeButtonContainer.getLayoutParams().width;
        FrameLayout r14 = this.repostButtonContainer;
        if (r14 == null) goto L81;
        ViewGroup.LayoutParams r15 = r14.getLayoutParams();
        float r22 = AndroidUtilities.dp(40.0f);
        if (this.repostCounterVisible == false) goto L80;
        r08 = this.repostCounter.getAnimateToWidth() + AndroidUtilities.dp(4.0f);
    L80:
        r15.width = (int) (r22 + r08);
        ((ViewGroup.MarginLayoutParams) this.selfView.getLayoutParams()).rightMargin += this.repostButtonContainer.getLayoutParams().width;
        this.repostButtonContainer.requestLayout();
    L81:
        this.selfView.requestLayout();
        this.likeButtonContainer.requestLayout();
        this.selfAvatarsView.setVisibility(8);
        this.selfAvatarsContainer.setVisibility(8);
        this.storyAreasView.onStoryItemUpdated(this.currentStory.storyItem, r13);
        return;
    L74:
        r47 = 0.0f;
        goto L75
    L59:
        if (ChatObject.isPossibleRemoveChatRestrictionsByBoosts(r46) == true) goto L70;
    L61:
        if (r1.views.views_count <= 0) goto L70;
        TextView r23 = this.selfStatusView;
        if (this.storyViewer.storiesList != null) goto L66;
        int r48 = R.string.NobodyViews;
    L67:
        r23.setText(LocaleController.getString(r48));
        this.selfStatusView.setTranslationX(AndroidUtilities.dp(16.0f));
        SpannableStringBuilder r24 = new SpannableStringBuilder();
        r24.append("d  ");
        r24.setSpan(new ColoredImageSpan(R.drawable.filled_views), r24.length() - 3, r24.length() - 2, 0);
        r24.append(AndroidUtilities.formatWholeNumber(r1.views.views_count, 0));
        this.selfStatusView.setText(r24);
        FrameLayout r16 = this.selfView;
        if (r16 == null) goto L71;
        ((FrameLayout.LayoutParams) r16.getLayoutParams()).rightMargin = (int) (AndroidUtilities.dp(80.0f) + this.selfStatusView.getPaint().measureText(r24.toString()));
        goto L71
    L66:
        r48 = R.string.NobodyViewsArchived;
        goto L67
    L36:
        if (this.reactionsCounterVisible == false) goto L38;
        r102 = true;
        goto L39
    L40:
        this.reactionsCounterVisible = false;
        goto L41
    L26:
        if (this.repostCounterVisible == false) goto L28;
        r10 = true;
    L30:
        this.repostCounterVisible = false;
        goto L31
    L83:
        if (r4 != null) goto L85;
    L110:
        TextView r132 = this.selfStatusView;
        if (this.storyViewer.storiesList != null) goto L114;
        int r09 = R.string.NobodyViews;
    L115:
        r132.setText(LocaleController.getString(r09));
        this.selfStatusView.setTranslationX(AndroidUtilities.dp(16.0f));
        this.selfAvatarsView.setVisibility(8);
        this.selfAvatarsContainer.setVisibility(8);
    L116:
        this.likeButtonContainer.getLayoutParams().width = AndroidUtilities.dp(40.0f);
        this.bottomActionsLinearLayout.requestLayout();
        return;
    L114:
        r09 = R.string.NobodyViewsArchived;
        goto L115
    L85:
        if (r4.views_count <= 0) goto L110;
        int r133 = 0;
        int r010 = 0;
    L88:
        if (r133 >= r1.views.recent_viewers.size()) goto L95;
        TLObject r25 = MessagesController.getInstance(this.currentAccount).getUserOrChat(r1.views.recent_viewers.get(r133).longValue());
        if (r25 == null) goto L92;
        this.selfAvatarsView.setObject(r010, this.currentAccount, r25);
        r010 = r010 + 1;
    L92:
        if (r010 >= 3) goto L95;
        r133 = r133 + 1;
    L95:
        int r134 = r010;
    L96:
        AvatarsImageView r26 = this.selfAvatarsView;
        if (r134 >= 3) goto L99;
        r26.setObject(r134, this.currentAccount, null);
        r134 = r134 + 1;
        goto L96
    L99:
        r26.commitTransition(false);
        SpannableStringBuilder r135 = new SpannableStringBuilder(LocaleController.formatPluralStringComma("Views", r1.views.views_count));
        if (r1.views.reactions_count <= 0) goto L103;
        r135.append("  d ");
        ColoredImageSpan r27 = new ColoredImageSpan(R.drawable.mini_views_likes);
        r27.setOverrideColor(-53704);
        r27.setTopOffset(AndroidUtilities.dp(0.2f));
        r135.setSpan(r27, r135.length() - 2, r135.length() - 1, 0);
        r135.append(String.valueOf(r1.views.reactions_count));
    L103:
        if (r1.views.forwards_count <= 0) goto L105;
        r135.append("  d ");
        ColoredImageSpan r28 = new ColoredImageSpan(R.drawable.mini_repost_story);
        r28.setOverrideColor(-14161823);
        r28.setTopOffset(AndroidUtilities.dp(0.2f));
        r135.setSpan(r28, r135.length() - 2, r135.length() - 1, 0);
        r135.append(String.valueOf(r1.views.forwards_count));
    L105:
        this.selfStatusView.setText(r135);
        AvatarsImageView r136 = this.selfAvatarsView;
        if (r010 != 0) goto L108;
        r136.setVisibility(8);
        this.selfStatusView.setTranslationX(AndroidUtilities.dp(16.0f));
    L109:
        this.selfAvatarsContainer.setVisibility(0);
        goto L116
    L108:
        r136.setVisibility(0);
        this.selfStatusView.setTranslationX(((AndroidUtilities.dp(13.0f) + AndroidUtilities.dp(24.0f)) + (AndroidUtilities.dp(20.0f) * (r010 - 1))) + AndroidUtilities.dp(10.0f));
        goto L109
    L118:
        this.selfStatusView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.selfAvatarsContainer.setVisibility(8);
        this.selfAvatarsView.setVisibility(8);
    }

    private void updateViewOffsets() {
        float r0 = this.delegate.getProgressToDismiss();
        AnimatedFloat r1 = this.progressToHideInterface;
        float r3 = 0.0f;
        if (this.isLongPressed == false) goto L5;
        float r2 = 1.0f;
    L6:
        r1.set(r2);
        int r12 = this.lastOpenedKeyboardHeight;
        if (r12 == 0) goto L12;
        if (this.animateKeyboardOpening == false) goto L12;
        float r13 = MathUtils.clamp(this.animatingKeyboardHeight / r12, 0.0f, 1.0f);
    L15:
        float r22 = this.progressToRecording.get();
        float r5 = this.progressToTextA.get();
        float r6 = this.progressToStickerExpanded.get();
        AnimatedFloat r7 = this.progressToRecording;
        if (this.isRecording == false) goto L18;
        float r8 = 1.0f;
    L19:
        r7.set(r8);
        if (this.messageSent == true) goto L28;
        AnimatedFloat r72 = this.progressToTextA;
        ChatActivityEnterView r82 = this.chatActivityEnterView;
        if (r82 != null) goto L24;
    L26:
        float r83 = 0.0f;
    L27:
        r72.set(r83);
        goto L28
    L24:
        if (TextUtils.isEmpty(r82.getFieldText()) == true) goto L26;
        r83 = 1.0f;
    L28:
        AnimatedFloat r73 = this.progressToStickerExpanded;
        ChatActivityEnterView r84 = this.chatActivityEnterView;
        if (r84 != null) goto L31;
    L33:
        float r85 = 0.0f;
    L34:
        r73.set(r85);
        ChatActivityEnterView r74 = this.chatActivityEnterView;
        if (r74 == null) goto L37;
        r74.checkAnimation();
    L37:
        ChatActivityEnterView r75 = this.chatActivityEnterView;
        boolean r86 = true;
        if (r75 != null) goto L40;
    L42:
        boolean r76 = false;
    L43:
        float r10 = getHideInterfaceAlpha();
        boolean r11 = this.BIG_SCREEN;
        Paint r122 = this.inputBackgroundPaint;
        if (r11 == false) goto L46;
        r122.setColor(ColorUtils.blendARGB(-14933463, Theme.multAlpha(-16777216, 0.44f), this.progressToKeyboard));
        this.inputBackgroundPaint.setAlpha((int) (((r11.getAlpha() * (1.0f - this.progressToDismiss)) * r10) * (1.0f - this.outT)));
    L48:
        if (this.forceUpdateOffsets == false) goto L50;
    L66:
        this.forceUpdateOffsets = false;
        this.lastAnimatingKeyboardHeight = this.animatingKeyboardHeight;
        if (this.progressToHideInterface.get() == this.prevToHideProgress) goto L69;
        this.storyContainer.invalidate();
    L69:
        FrameLayout r52 = this.storyContainer;
        if (r0 == 0.0f) goto L72;
        r52.setLayerType(2, null);
    L73:
        this.prevToHideProgress = this.progressToHideInterface.get();
        this.progressToDismiss = r0;
        this.progressToKeyboard = r13;
        this.progressToReply = this.storyViewer.swipeToReplyProgress;
        float r23 = this.animatingKeyboardHeight - (getHeight() - this.storyContainer.getBottom());
        if (this.chatActivityEnterView != null) goto L76;
        float r53 = 0.0f;
    L77:
        float r24 = Math.max(0.0f, r23 + r53);
        float r54 = this.lastKeyboardHeight - (getHeight() - this.storyContainer.getBottom());
        ChatActivityEnterView r77 = this.chatActivityEnterView;
        if (r77 != null) goto L80;
        float r78 = 0.0f;
    L85:
        float r55 = Math.max(0.0f, r54 + r78);
        ChatActivityEnterView r79 = this.chatActivityEnterView;
        if (r79 == null) goto L99;
        if (this.highlightMessageHintView == null) goto L93;
        ImageView r710 = r79.getSuggestButton();
        if (r710 == null) goto L92;
        this.highlightMessageHintView.setJointPx(1.0f, -(AndroidUtilities.dp(74.0f) - r710.getTranslationX()));
    L92:
        this.highlightMessageHintView.setTranslationY(-((getHeight() - this.chatActivityEnterView.getY()) + AndroidUtilities.dp(6.0f)));
    L93:
        EditTextCaption r711 = this.chatActivityEnterView.getEditField();
        SenderSelectView r132 = this.chatActivityEnterView.getSenderSelectView();
        if (r711 == null) goto L96;
        float r14 = AndroidUtilities.lerp(0.88f, 1.0f, r13);
        r711.setScaleX(r14);
        r711.setScaleY(r14);
    L96:
        if (r132 == null) goto L99;
        float r712 = AndroidUtilities.lerp(0.8f, 1.0f, r13);
        r132.setScaleX(r712);
        r132.setScaleY(r712);
        r132.setTranslationY(AndroidUtilities.lerp(-AndroidUtilities.dp(2.0f), 0, r13));
    L99:
        if (this.BIG_SCREEN == false) goto L101;
        float r713 = 0.0f;
    L102:
        float r714 = Math.max(0.0f, r713 + r24);
        if (this.BIG_SCREEN == false) goto L105;
    L108:
        int r133 = 0;
    L109:
        this.liveCommentsView.setKeyboardOffset(r13, r714 + (AndroidUtilities.dp(6.0f) * r13), Math.max(0.0f, r55 + r133));
        StoryViewer r15 = this.storyViewer;
        if (r15 == null) goto L114;
        LivePlayerView r16 = r15.liveView;
        if (r16 == null) goto L114;
        r16.setKeyboardOffset(r24);
    L114:
        ReactionsContainerLayout r17 = this.reactionsContainerLayout;
        if (r17 != null) goto L117;
    L125:
        int r18 = 0;
    L127:
        if (r18 >= getChildCount()) goto L218;
        View r25 = getChildAt(r18);
        if (r25.getVisibility() != 0) goto L212;
        if (r25 == this.selfView) goto L212;
        if (r25.getTag(R.id.parent_tag) != null) goto L212;
        if (r25 == this.storyCaptionView.textSelectionHelper.getOverlayView(getContext())) goto L212;
        ChatActivityEnterView r56 = this.chatActivityEnterView;
        if (r56 == null) goto L143;
        if (r25 != r56.getEmojiView()) goto L143;
        r25.setTranslationY(this.chatActivityEnterView.getEmojiView().getMeasuredHeight() - this.animatingKeyboardHeight);
    L217:
        r18 = r18 + 1;
    L143:
        if ((r25 instanceof HintView) == false) goto L146;
        ((HintView) r25).updatePosition();
        goto L217
    L146:
        if (r25 == this.instantCameraView) goto L217;
        if (r25 == this.storyContainer) goto L217;
        if (r25 == this.shareButton) goto L217;
        if (r25 == this.bottomActionsLinearLayout) goto L217;
        if (r25 == this.repostButtonContainer) goto L217;
        if (r25 == this.mediaBanTooltip) goto L217;
        if (r25 == this.highlightMessageHintView) goto L217;
        if (r25 == this.likeButtonContainer) goto L217;
        ReactionsContainerLayout r57 = this.likesReactionLayout;
        if (r57 != null) goto L164;
    L167:
        float r58 = this.progressToKeyboard;
        if (r25 != this.commentButton) goto L170;
    L175:
        r58 = 0.0f;
    L176:
        float r123 = 1.0f - r58;
        float r715 = (((((-this.enterViewBottomOffset) * r123) - (AndroidUtilities.dp(7.0f) * r58)) - this.animatingKeyboardHeight) - (AndroidUtilities.dp(8.0f) * r123)) - (AndroidUtilities.dp(20.0f) * this.storyViewer.swipeToReplyProgress);
        if (r25 != this.commentButton) goto L179;
    L184:
        r715 = r715 + this.animatingKeyboardHeight;
    L186:
        if (this.BIG_SCREEN == false) goto L188;
        float r124 = (1.0f - this.progressToDismiss) * r10;
    L190:
        if (r25 != this.replyDisabledTextView) goto L193;
        r715 = this.storyViewer.swipeToReplyProgress * (-AndroidUtilities.dp(20.0f));
    L193:
        if (r25 != this.mentionContainer) goto L196;
        r715 = r715 - (this.chatActivityEnterView.getMeasuredHeight() - this.chatActivityEnterView.getAnimatedTop());
        r124 = this.progressToKeyboard;
        r25.invalidate();
    L196:
        if (r25 != this.sideControlsButtonsLayout) goto L199;
        r715 = r715 - (this.chatActivityEnterView.getMeasuredHeight() - this.chatActivityEnterView.getAnimatedTop());
        r124 = r124 * this.progressToKeyboard;
    L199:
        if (r25 != this.reactionsContainerLayout) goto L206;
        float r59 = ((this.progressToKeyboard * (1.0f - this.progressToRecording.get())) * (1.0f - r6)) * (1.0f - this.progressToTextA.get());
        float r125 = (r124 * r59) * 1.0f;
        if (r25.getAlpha() != 0.0f) goto L203;
    L205:
        r25.setAlpha(r125);
        float r510 = (r59 * 0.2f) + 0.8f;
        r25.setScaleX(r510);
        r25.setScaleY(r510);
        goto L217
    L203:
        if (r125 != 0.0f) goto L205;
        this.reactionsContainerLayout.reset();
        goto L205
    L206:
        r25.setTranslationY(r715);
        ChatActivityEnterView r511 = this.chatActivityEnterView;
        if (r511 != null) goto L209;
    L210:
        r25.setAlpha(r124);
        goto L217
    L209:
        if (r25 == r511.controlsView) goto L217;
    L188:
        r124 = r10 * 1.0f;
        goto L190
    L179:
        if (r25 == this.starsButton) goto L184;
        if (r25 == this.muteButton) goto L184;
        if (r25 != this.starsButtonEffectsView) goto L186;
    L170:
        if (r25 == this.starsButton) goto L175;
        if (r25 == this.muteButton) goto L175;
        if (r25 != this.starsButtonEffectsView) goto L176;
    L164:
        if (r57.getReactionsWindow() == null) goto L167;
        if (r25 == this.likesReactionLayout.getReactionsWindow().windowView) goto L217;
    L212:
        if (r25 != this.selfView) goto L217;
        if (this.BIG_SCREEN == false) goto L216;
        r25.setAlpha(((1.0f - this.progressToDismiss) * r10) * (1.0f - this.outT));
        goto L217
    L216:
        r25.setAlpha((1.0f - this.outT) * r10);
        goto L217
    L218:
        float r02 = (1.0f - r0) * r10;
        this.shareButton.setAlpha((1.0f - this.outT) * r02);
        this.likeButtonContainer.setAlpha((1.0f - this.outT) * r02);
        FrameLayout r19 = this.repostButtonContainer;
        if (r19 == null) goto L221;
        r19.setAlpha(r02 * (1.0f - this.outT));
    L221:
        int r03 = 0;
    L223:
        if (r03 >= this.storyContainer.getChildCount()) goto L259;
        View r110 = this.storyContainer.getChildAt(r03);
        if (r110 == null) goto L257;
        if (r110 == this.liveCommentsShadowView) goto L257;
        if (r110 == this.headerView) goto L246;
        if (r110 == this.optionsIconView) goto L246;
        if (r110 == this.pipIconView) goto L246;
        if (r110 == this.muteIconContainer) goto L246;
        if (r110 == this.selfView) goto L246;
        if (r110 == this.storyCaptionView) goto L246;
        if (r110 == this.privacyButton) goto L246;
        r110.setAlpha(r10);
    L246:
        if (r110 != this.muteIconContainer) goto L248;
        float r26 = this.muteIconViewAlpha;
    L250:
        if (r110 == this.storyCaptionView) goto L252;
        r110.setAlpha((r26 * r10) * (1.0f - this.outT));
        goto L257
    L252:
        if (hideCaptionWithInterface() == false) goto L254;
        float r512 = r10;
    L255:
        r110.setAlpha((r26 * r512) * (1.0f - this.outT));
        goto L257
    L254:
        r512 = 1.0f;
        goto L255
    L248:
        r26 = 1.0f;
    L257:
        r03 = r03 + 1;
        goto L223
    L259:
        if (this.chatActivityEnterView == null) goto L320;
        float r111 = AndroidUtilities.dp(40.0f);
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L263;
        r3 = AndroidUtilities.dp(46.0f);
        r111 = AndroidUtilities.dp(46.0f);
        MuteButton r04 = this.muteButton;
        if (r04 != null) goto L279;
    L281:
        ChatActivityEnterView r05 = this.chatActivityEnterView;
        float r4 = -AndroidUtilities.dp(10.0f);
        float r112 = -((AndroidUtilities.dp(10.0f) + r111) + r3);
        float r27 = this.progressToKeyboard;
        if (this.allowShare == false) goto L284;
    L285:
        StoryItemHolder r32 = this.currentStory;
        if (r32 != null) goto L288;
    L291:
        r05.setHorizontalPadding(r4, r112, r27, r86);
        if (this.chatActivityEnterView.getEmojiView() == null) goto L321;
        this.chatActivityEnterView.getEmojiView().setAlpha(this.progressToKeyboard);
        return;
    L321:
        return;
    L288:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(r32) == false) goto L291;
    L290:
        r86 = false;
        goto L291
    L284:
        if (this.isGroup == false) goto L290;
    L279:
        if (r04.getVisibility() != 0) goto L281;
        int r06 = AndroidUtilities.dp(46.0f);
    L275:
        r111 = r111 + r06;
        goto L281
    L263:
        if (this.allowShare == false) goto L266;
        r111 = r111 + AndroidUtilities.dp(46.0f);
    L266:
        if (this.allowRepost == true) goto L268;
    L270:
        FrameLayout r28 = this.likeButtonContainer;
        if (r28 == null) goto L281;
        if (r28.getVisibility() != 0) goto L281;
        r111 = r111 - AndroidUtilities.dp(40.0f);
        r06 = this.likeButtonContainer.getLayoutParams().width;
        goto L275
    L268:
        if (this.isChannel == false) goto L270;
        r111 = r111 + AndroidUtilities.dp(46.0f);
        goto L270
    L320:
        return;
    L117:
        if (this.progressToKeyboard <= 0.0f) goto L123;
        StoryItemHolder r29 = this.currentStory;
        if (r29 != null) goto L121;
    L122:
        int r210 = 0;
    L124:
        r17.setVisibility(r210);
        goto L125
    L121:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(r29) == false) goto L122;
    L123:
        r210 = 8;
        goto L124
    L105:
        if (this.keyboardVisible == true) goto L108;
        r133 = AndroidUtilities.dp(58.0f);
        goto L109
    L101:
        r713 = AndroidUtilities.dp(58.0f) * (1.0f - r13);
        goto L102
    L80:
        float r716 = r77.getHeightWithTopView();
        if (this.keyboardVisible == false) goto L83;
        float r113 = 1.0f;
    L84:
        r78 = r716 * r113;
        goto L85
    L83:
        r113 = 0.0f;
        goto L84
    L76:
        r53 = r5.getHeightWithTopView() * r13;
        goto L77
    L72:
        r52.setLayerType(0, null);
        goto L73
    L50:
        if (this.progressToReply != this.storyViewer.swipeToReplyProgress) goto L66;
        if (this.progressToHideInterface.get() != this.prevToHideProgress) goto L66;
        if (this.lastAnimatingKeyboardHeight != this.animatingKeyboardHeight) goto L66;
        if (r13 != this.progressToKeyboard) goto L66;
        if (r0 != this.progressToDismiss) goto L66;
        if (r22 != this.progressToRecording.get()) goto L66;
        if (r76 == true) goto L66;
        if (r6 != this.progressToStickerExpanded.get()) goto L66;
        if (r5 != this.progressToTextA.get()) goto L66;
        return;
    L46:
        r122.setColor(ColorUtils.setAlphaComponent(-16777216, (int) ((137.70001f * r10) * (1.0f - this.outT))));
        goto L48
    L40:
        if (r75.isPopupShowing() == false) goto L42;
        r76 = true;
        goto L43
    L31:
        if (r84.isStickersExpanded() == false) goto L33;
        r85 = 1.0f;
        goto L34
    L18:
        r8 = 0.0f;
    L12:
        if (this.keyboardVisible == false) goto L14;
        r13 = 1.0f;
        goto L15
    L14:
        r13 = 0.0f;
        goto L15
    L5:
        r2 = 0.0f;
        goto L6
    }

    public void animateOut(boolean r5) {
        ValueAnimator r0 = this.outAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        float r02 = this.outT;
        if (r5 == false) goto L8;
        float r1 = 1.0f;
    L9:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
        this.outAnimator = r03;
        r03.addUpdateListener(new PeerStoriesView$$ExternalSyntheticLambda53(this));
        this.outAnimator.addListener(new AnonymousClass41(this, r5));
        this.outAnimator.setDuration(420);
        this.outAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.outAnimator.start();
        return;
    L8:
        r1 = 0.0f;
        goto L9
    }

    public void cancelTextSelection() {
        if (this.storyCaptionView.textSelectionHelper.isInSelectionMode() == false) goto L6;
        this.storyCaptionView.textSelectionHelper.clear();
        return;
    }

    public void cancelTouch() {
        this.storyCaptionView.cancelTouch();
    }

    public void checkPinchToZoom(MotionEvent r8) {
        this.pinchToZoomHelper.checkPinchToZoom(r8, this.storyContainer, null, null, null, null);
    }

    public boolean checkReactionEvent(MotionEvent r7) {
        ReactionsContainerLayout r0 = this.likesReactionLayout;
        if (r0 == null) goto L23;
        float r2 = 0.0f;
        View r4 = this;
        float r3 = 0.0f;
    L5:
        if (r4 == null) goto L10;
        if ((r4.getParent() instanceof View) == false) goto L10;
        r2 = r2 + r4.getX();
        r3 = r3 + r4.getY();
        r4 = (View) r4.getParent();
    L10:
        if (this.likesReactionLayout.getReactionsWindow() != null) goto L12;
    L15:
        Rect r42 = AndroidUtilities.rectTmp2;
        r0.getHitRect(r42);
        r42.offset((int) r2, (int) r3);
        if (r7.getAction() == 0) goto L18;
    L21:
        r7.offsetLocation(-r42.left, -r42.top);
        r0.dispatchTouchEvent(r7);
        return true;
    L18:
        if (r42.contains((int) r7.getX(), (int) r7.getY()) == true) goto L21;
        showLikesReaction(false);
        return true;
    L12:
        if (this.likesReactionLayout.getReactionsWindow().windowView == null) goto L15;
        r7.offsetLocation(-r2, (-r3) - this.likesReactionLayout.getReactionsWindow().windowView.getTranslationY());
        this.likesReactionLayout.getReactionsWindow().windowView.dispatchTouchEvent(r7);
        return true;
    L23:
        return false;
    }

    public void checkReactionsLayout() {
        if (this.reactionsContainerLayout != null) goto L9;
        ReactionsContainerLayout r1 = new ReactionsContainerLayout(1, LaunchActivity.getLastFragment(), getContext(), this.currentAccount, new AnonymousClass37(this, this.resourcesProvider));
        this.reactionsContainerLayout = r1;
        if (this.isGroup == false) goto L7;
        int r0 = R.string.StoryGroupReactionsHint;
    L8:
        r1.setHint(LocaleController.getString(r0));
        ReactionsContainerLayout r02 = this.reactionsContainerLayout;
        r02.skipEnterAnimation = true;
        addView(r02, this.reactionsContainerIndex, LayoutHelper.createFrame(-2, 72.0f, 49, 0.0f, 0.0f, 0.0f, 64.0f));
        this.reactionsContainerLayout.setDelegate(new AnonymousClass38(this));
        this.reactionsContainerLayout.setMessage(null, null, true);
        goto L9
    L7:
        r0 = R.string.StoryReactionsHint;
    L9:
        this.reactionsContainerLayout.setFragment(LaunchActivity.getLastFragment());
        ReactionsContainerLayout r03 = this.reactionsContainerLayout;
        if (this.isGroup == false) goto L12;
        int r10 = R.string.StoryGroupReactionsHint;
    L13:
        r03.setHint(LocaleController.getString(r10));
        return;
    L12:
        r10 = R.string.StoryReactionsHint;
        goto L13
    }

    public void checkReactionsLayoutForLike() {
        ReactionsContainerLayout r0 = this.likesReactionLayout;
        if (r0 != null) goto L5;
        ReactionsContainerLayout r1 = new ReactionsContainerLayout(2, LaunchActivity.getLastFragment(), getContext(), this.currentAccount, new AnonymousClass39(this, this.resourcesProvider));
        this.likesReactionLayout = r1;
        r1.setPadding(0, 0, 0, AndroidUtilities.dp(22.0f));
        addView(this.likesReactionLayout, getChildCount() - 1, LayoutHelper.createFrame(-2, 74.0f, 53, 0.0f, 0.0f, 12.0f, 64.0f));
        this.likesReactionLayout.setVisibility(8);
        this.likesReactionLayout.setDelegate(new AnonymousClass40(this));
        this.likesReactionLayout.setMessage(null, null, true);
    L6:
        this.likesReactionLayout.setFragment(LaunchActivity.getLastFragment());
        return;
    L5:
        bringChildToFront(r0);
        this.likesReactionLayout.reset();
        goto L6
    }

    public boolean checkRecordLocked(boolean r4) {
        ChatActivityEnterView r0 = this.chatActivityEnterView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isRecordLocked() == false) goto L14;
        AlertDialog.Builder r02 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        if (this.chatActivityEnterView.isInVideoMode() == false) goto L9;
        r02.setTitle(LocaleController.getString(R.string.DiscardVideoMessageTitle));
        r02.setMessage(LocaleController.getString(R.string.DiscardVideoMessageDescription));
    L10:
        r02.setPositiveButton(LocaleController.getString(R.string.DiscardVoiceMessageAction), new PeerStoriesView$$ExternalSyntheticLambda2(this, r4));
        r02.setNegativeButton(LocaleController.getString(R.string.Continue), null);
        this.delegate.showDialog(r02.create());
        return true;
    L9:
        r02.setTitle(LocaleController.getString(R.string.DiscardVoiceMessageTitle));
        r02.setMessage(LocaleController.getString(R.string.DiscardVoiceMessageDescription));
        goto L10
    L14:
        return false;
    }

    public boolean checkTextSelectionEvent(MotionEvent r5) {
        if (this.storyCaptionView.textSelectionHelper.isInSelectionMode() == false) goto L11;
        float r0 = getX();
        float r1 = getY() + ((View) getParent()).getY();
        r5.offsetLocation(-r0, -r1);
        if (this.storyCaptionView.textSelectionHelper.getOverlayView(getContext()).onTouchEvent(r5) == false) goto L8;
        return true;
    L8:
        r5.offsetLocation(r0, r1);
        return false;
    L11:
        return false;
    }

    public boolean closeKeyboardOrEmoji() {
        if (this.likesReactionShowing == true) goto L5;
        StoryMediaAreasView r0 = this.storyAreasView;
        if (r0 == null) goto L17;
        r0.closeHint();
    L17:
        if (this.storyCaptionView.textSelectionHelper.isInSelectionMode() == false) goto L20;
        this.storyCaptionView.textSelectionHelper.clear(false);
        return true;
    L20:
        HintView2 r02 = this.privacyHint;
        if (r02 == null) goto L23;
        r02.hide();
    L23:
        HintView2 r03 = this.soundTooltip;
        if (r03 == null) goto L26;
        r03.hide();
    L26:
        HintView r04 = this.mediaBanTooltip;
        if (r04 == null) goto L29;
        r04.hide(true);
    L29:
        CaptionContainerView r05 = this.storyEditCaptionView;
        if (r05 != null) goto L32;
    L34:
        CustomPopupMenu r06 = this.popupMenu;
        if (r06 == null) goto L41;
        if (r06.isShowing() == false) goto L41;
        this.popupMenu.dismiss();
        return true;
    L41:
        if (checkRecordLocked(false) == false) goto L43;
        return true;
    L43:
        ReactionsContainerLayout r07 = this.reactionsContainerLayout;
        if (r07 != null) goto L46;
    L51:
        ChatActivityEnterView r08 = this.chatActivityEnterView;
        if (r08 == null) goto L61;
        if (r08.isPopupShowing() == false) goto L61;
        int r09 = this.realKeyboardHeight;
        ChatActivityEnterView r6 = this.chatActivityEnterView;
        if (r09 <= 0) goto L58;
        AndroidUtilities.hideKeyboard(r6.getEmojiView());
    L59:
        return true;
    L58:
        r6.hidePopup(true, false);
    L61:
        if (getKeyboardHeight() < AndroidUtilities.dp(20.0f)) goto L68;
        ChatActivityEnterView r010 = this.chatActivityEnterView;
        if (r010 == null) goto L65;
        this.storyViewer.saveDraft(this.dialogId, this.currentStory.storyItem, r010.getEditText());
    L65:
        AndroidUtilities.hideKeyboard(this.chatActivityEnterView);
        return true;
    L68:
        if (this.storyCaptionView.getVisibility() == 0) goto L70;
    L73:
        return false;
    L70:
        if (this.storyCaptionView.getProgressToBlackout() <= 0.0f) goto L73;
        this.storyCaptionView.collapse();
        this.inBlackoutMode = false;
        this.storyContainer.invalidate();
        return true;
    L46:
        if (r07.getReactionsWindow() == null) goto L51;
        if (this.reactionsContainerLayout.getReactionsWindow().isShowing() == false) goto L51;
        this.reactionsContainerLayout.getReactionsWindow().dismiss();
        return true;
    L32:
        if (r05.onBackPressed() == false) goto L34;
        return true;
    L5:
        if (this.likesReactionLayout.getReactionsWindow() == null) goto L11;
        int r011 = this.realKeyboardHeight;
        ReactionsContainerLayout r62 = this.likesReactionLayout;
        if (r011 <= 0) goto L9;
        AndroidUtilities.hideKeyboard(r62.getReactionsWindow().windowView);
    L10:
        return true;
    L9:
        r62.getReactionsWindow().dismiss();
        goto L10
    L11:
        showLikesReaction(false);
        return true;
    }

    public void createBlurredBitmap(Canvas r3, Bitmap r4) {
        drawPlayingBitmap(r4.getWidth(), r4.getHeight(), r3);
        if (AndroidUtilities.computePerceivedBrightness(AndroidUtilities.getDominantColor(r4)) >= 0.15f) goto L5;
        r3.drawColor(ColorUtils.setAlphaComponent(-1, 102));
    L5:
        Utilities.blurBitmap(r4, 3);
        Utilities.blurBitmap(r4, 3);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r7, int r8, Object... r9) {
        if (r7 == NotificationCenter.storiesUpdated) goto L76;
        boolean r3 = false;
        if (r7 != NotificationCenter.storiesListUpdated) goto L10;
        if (this.storyViewer.storiesList == r9[0]) goto L76;
    L10:
        if (r7 != NotificationCenter.storyQualityUpdate) goto L14;
        updatePosition();
        return;
    L14:
        if (r7 != NotificationCenter.emojiLoaded) goto L18;
        this.storyCaptionView.captionTextview.invalidate();
        return;
    L18:
        if (r7 != NotificationCenter.stealthModeChanged) goto L22;
        checkStealthMode(true);
        return;
    L22:
        if (r7 != NotificationCenter.storiesLimitUpdate) goto L33;
        StoriesController.StoryLimit r72 = MessagesController.getInstance(this.currentAccount).getStoriesController().checkStoryLimit();
        if (r72 != null) goto L26;
        return;
    L26:
        if (r72.active(this.currentAccount) == true) goto L28;
        return;
    L28:
        if (this.delegate == null) goto L128;
        this.delegate.showDialog(new LimitReachedBottomSheet(fragmentForLimit(), findActivity(), r72.getLimitReachedType(), this.currentAccount, null));
        return;
    L128:
        return;
    L33:
        if (r7 != NotificationCenter.userIsPremiumBlockedUpadted) goto L48;
        TL_account.RequirementToContact r73 = MessagesController.getInstance(this.currentAccount).isUserContactBlocked(this.dialogId);
        if (this.dialogId < 0) goto L42;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L42;
        if (DialogObject.isPremiumBlocked(r73) == false) goto L42;
        r3 = true;
    L42:
        if (this.isPremiumBlocked == r3) goto L44;
    L45:
        this.isPremiumBlocked = r3;
        this.starsPriceBlocked = DialogObject.getMessagesStarsPrice(r73);
        updatePosition();
        checkStealthMode(true);
        return;
    L44:
        if (this.starsPriceBlocked != DialogObject.getMessagesStarsPrice(r73)) goto L45;
        return;
    L48:
        if (r7 != NotificationCenter.chatInfoDidLoad) goto L56;
        Object r74 = r9[0];
        if ((r74 instanceof TLRPC.ChatFull) == true) goto L52;
        return;
    L52:
        if (this.dialogId != (-((TLRPC.ChatFull) r74).id)) goto L119;
        updatePosition();
        return;
    L119:
        return;
    L56:
        if (r7 != NotificationCenter.liveStoryUpdated) goto L71;
        long r75 = ((Long) r9[0]).longValue();
        StoryViewer r92 = this.storyViewer;
        if (r92 == null) goto L120;
        LivePlayer r93 = r92.livePlayer;
        if (r93 != null) goto L62;
        return;
    L62:
        if (r93.getCallId() != r75) goto L122;
        updatePosition();
        ChatActivityEnterView r76 = this.chatActivityEnterView;
        if (r76 == null) goto L66;
        r76.checkSendButton(true);
        this.chatActivityEnterView.updateSendAsButton(true);
        checkStealthMode(true);
    L66:
        LiveCommentsView r6 = this.liveCommentsView;
        if (r6 == null) goto L123;
        r6.updatedMinStars();
        return;
    L123:
        return;
    L122:
        return;
    L120:
        return;
    L71:
        if (r7 == NotificationCenter.didLoadSendAsPeers) goto L73;
        return;
    L73:
        if (((Boolean) r9[2]).booleanValue() == false) goto L125;
        loadSendAsPeers(true);
        return;
    L125:
        return;
    L76:
        Delegate r77 = this.delegate;
        if (r77 == null) goto L82;
        if (r77.isClosed() == false) goto L82;
        return;
    L82:
        if (this.isActive == false) goto L97;
        updateStoryItems();
        if (this.count != 0) goto L90;
        if (this.deletedPeer == true) goto L126;
        this.deletedPeer = true;
        this.delegate.switchToNextAndRemoveCurrentPeer();
        return;
    L126:
        return;
    L90:
        if (this.selectedPosition < (this.storyItems.size() + this.uploadingStories.size())) goto L92;
        this.selectedPosition = (this.storyItems.size() + this.uploadingStories.size()) - 1;
    L92:
        updatePosition();
        if (this.isSelf == false) goto L95;
    L96:
        updateUserViews(true);
        goto L97
    L95:
        if (this.isChannel == true) goto L96;
    L97:
        TL_stories.PeerStories r78 = this.storyViewer.overrideUserStories;
        if (r78 == null) goto L100;
        this.storiesController.loadSkippedStories(r78, true);
    L103:
        ActionBarMenuSubItem r79 = this.editStoryItem;
        if (r79 == null) goto L127;
        ViewPropertyAnimator r710 = r79.animate();
        if (this.storiesController.hasUploadingStories(this.dialogId) == true) goto L108;
    L112:
        float r62 = 1.0f;
    L113:
        r710.alpha(r62).start();
        return;
    L108:
        if (StoryItemHolder.m19635$$Nest$fgetisVideo(this.currentStory) == false) goto L112;
        if (SharedConfig.allowPreparingHevcPlayers() == true) goto L112;
        r62 = 0.5f;
        goto L113
    L127:
        return;
    L100:
        long r711 = this.dialogId;
        if (r711 == 0) goto L103;
        this.storiesController.loadSkippedStories(r711);
        goto L103
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r10) {
        updateViewOffsets();
        if (this.isChannel == false) goto L8;
        AnimatedTextView.AnimatedTextDrawable r0 = this.reactionsCounter;
        if (r0 == null) goto L8;
        r0.setBounds(0, 0, getMeasuredWidth(), AndroidUtilities.dp(40.0f));
    L8:
        if (this.isChannel == false) goto L12;
        AnimatedTextView.AnimatedTextDrawable r02 = this.repostCounter;
        if (r02 == null) goto L12;
        r02.setBounds(0, 0, getMeasuredWidth(), AndroidUtilities.dp(40.0f));
    L12:
        super.dispatchDraw(r10);
        if (this.movingReaction == false) goto L21;
        float r03 = (this.bottomActionsLinearLayout.getX() + this.likeButtonContainer.getX()) + (this.likeButtonContainer.getMeasuredWidth() / 2.0f);
        float r3 = (this.bottomActionsLinearLayout.getY() + this.likeButtonContainer.getY()) + (this.likeButtonContainer.getMeasuredHeight() / 2.0f);
        int r4 = AndroidUtilities.dp(24.0f);
        float r6 = r4 / 2.0f;
        float r04 = AndroidUtilities.lerp(this.movingReactionFromX, r03 - r6, CubicBezierInterpolator.EASE_OUT.getInterpolation(this.movingReactionProgress));
        float r32 = AndroidUtilities.lerp(this.movingReactionFromY, r3 - r6, this.movingReactionProgress);
        int r42 = AndroidUtilities.lerp(this.movingReactionFromSize, r4, this.movingReactionProgress);
        if (this.drawAnimatedEmojiAsMovingReaction == false) goto L19;
        AnimatedEmojiDrawable r5 = this.reactionMoveDrawable;
        if (r5 == null) goto L21;
        float r43 = r42;
        r5.setBounds((int) r04, (int) r32, (int) (r04 + r43), (int) (r32 + r43));
        this.reactionMoveDrawable.draw(r10);
        goto L21
    L19:
        float r44 = r42;
        this.reactionMoveImageReceiver.setImageCoords(r04, r32, r44, r44);
        this.reactionMoveImageReceiver.draw(r10);
    L21:
        if (this.drawReactionEffect == false) goto L35;
        float r05 = (this.bottomActionsLinearLayout.getX() + this.likeButtonContainer.getX()) + (this.likeButtonContainer.getMeasuredWidth() / 2.0f);
        float r33 = (this.bottomActionsLinearLayout.getY() + this.likeButtonContainer.getY()) + (this.likeButtonContainer.getMeasuredHeight() / 2.0f);
        int r45 = AndroidUtilities.dp(120.0f);
        if (this.drawAnimatedEmojiAsMovingReaction == true) goto L29;
        float r46 = r45;
        float r1 = r46 / 2.0f;
        this.reactionEffectImageReceiver.setImageCoords(r05 - r1, r33 - r1, r46, r46);
        this.reactionEffectImageReceiver.draw(r10);
        if (this.reactionEffectImageReceiver.getLottieAnimation() == null) goto L35;
        if (this.reactionEffectImageReceiver.getLottieAnimation().isLastFrame() == false) goto L35;
        this.drawReactionEffect = false;
        goto L35
    L29:
        AnimatedEmojiEffect r52 = this.emojiReactionEffect;
        if (r52 == null) goto L34;
        float r47 = r45 / 2.0f;
        r52.setBounds((int) (r05 - r47), (int) (r33 - r47), (int) (r05 + r47), (int) (r33 + r47));
        this.emojiReactionEffect.draw(r10);
        if (this.emojiReactionEffect.isDone() == false) goto L35;
        this.emojiReactionEffect.removeView(this);
        this.emojiReactionEffect = null;
        this.drawReactionEffect = false;
        goto L35
    L34:
        this.drawReactionEffect = false;
    L35:
        ChatActivityEnterView r9 = this.chatActivityEnterView;
        if (r9 == null) goto L39;
        r9.drawRecordedPannel(r10);
        return;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r11, View r12, long r13) {
        float r1 = 0.0f;
        if (r12 != this.mentionContainer) goto L6;
        r11.save();
        r11.clipRect(0.0f, this.mentionContainer.getY(), getMeasuredWidth(), this.mentionContainer.getY() + this.mentionContainer.getMeasuredHeight());
        boolean r10 = super.drawChild(r11, r12, r13);
        r11.restore();
        return r10;
    L6:
        ChatActivityEnterView r0 = this.chatActivityEnterView;
        if (r12 == r0) goto L9;
        Canvas r3 = r11;
        if (r0 == null) goto L57;
        if (r0.isPopupView(r12) == false) goto L57;
        float r112 = AndroidUtilities.dp(30.0f);
        SharedResources.m19630$$Nest$fgetpopupRect(this.sharedResources).set(0.0f, r12.getY() + AndroidUtilities.dp(1.0f), getWidth(), getHeight() + AndroidUtilities.dp(20.0f));
        this.clipPath.rewind();
        this.clipPath.addRoundRect(SharedResources.m19630$$Nest$fgetpopupRect(this.sharedResources), r112, r112, Path.Direction.CW);
        r3.save();
        r3.clipPath(this.clipPath);
        BlurredBackgroundDrawable r02 = this.emojiKeyboardBackground;
        SharedResources r14 = this.sharedResources;
        if (r02 == null) goto L53;
        r02.setBounds((int) SharedResources.m19630$$Nest$fgetpopupRect(r14).left, (int) SharedResources.m19630$$Nest$fgetpopupRect(this.sharedResources).top, (int) SharedResources.m19630$$Nest$fgetpopupRect(this.sharedResources).right, (int) SharedResources.m19630$$Nest$fgetpopupRect(this.sharedResources).bottom);
        this.emojiKeyboardBackground.setRadius(r112, r112, r112, r112);
        this.emojiKeyboardBackground.setAlpha(255);
        this.emojiKeyboardBackground.draw(r3);
    L54:
        boolean r102 = super.drawChild(r3, r12, r13);
        r3.restore();
        return r102;
    L53:
        r3.drawRoundRect(SharedResources.m19630$$Nest$fgetpopupRect(r14), r112, r112, this.inputBackgroundPaint);
    L57:
        if (r12 != this.reactionsContainerLayout) goto L62;
        if (this.chatActivityEnterView == null) goto L62;
        r12.setTranslationY(((-r11.getMeasuredHeight()) + (this.chatActivityEnterView.getY() + this.chatActivityEnterView.getAnimatedTop())) - AndroidUtilities.dp(18.0f));
    L76:
        return super.drawChild(r3, r12, r13);
    L62:
        if (r12 != this.likesReactionLayout) goto L65;
        r12.setTranslationY((((-(r11.getMeasuredHeight() - this.likesReactionLayout.getPaddingBottom())) + this.likeButtonContainer.getY()) + this.bottomActionsLinearLayout.getY()) - AndroidUtilities.dp(18.0f));
        goto L76
    L65:
        if (r12 != this.storyContainer) goto L76;
        if (Build.VERSION.SDK_INT < 31) goto L76;
        if (r3.isHardwareAccelerated() == false) goto L76;
        BlurredBackgroundSourceRenderNode r113 = this.blurredBackgroundSourceRenderNodeWithSaturation;
        if (r113 == null) goto L76;
        if (r113.inRecording() == true) goto L76;
        RecordingCanvas r114 = this.blurredBackgroundSourceRenderNodeWithSaturation.beginRecording(getMeasuredWidth(), getMeasuredHeight());
        r114.drawColor(ColorUtils.blendARGB(-16777216, -1, 0.2f));
        r114.translate(this.storyContainer.getX(), this.storyContainer.getY());
        r12.draw(r114);
        this.blurredBackgroundSourceRenderNodeWithSaturation.endRecording();
        goto L76
    L9:
        if (this.progressToKeyboard > 0.0f) goto L11;
    L13:
        r3 = r11;
    L14:
        SharedResources.m19631$$Nest$fgetrect1(this.sharedResources).set(this.chatActivityEnterView.getX(), (this.chatActivityEnterView.getY() + this.chatActivityEnterView.getAnimatedTop()) + AndroidUtilities.dp(1.33f), this.chatActivityEnterView.getX() + this.chatActivityEnterView.getMeasuredWidth(), this.chatActivityEnterView.getY() + this.chatActivityEnterView.getMeasuredHeight());
        float r03 = AndroidUtilities.dp(40.0f);
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L17;
        float r115 = AndroidUtilities.dp(46.0f);
        r03 = AndroidUtilities.dp(46.0f);
        MuteButton r4 = this.muteButton;
        if (r4 != null) goto L33;
    L35:
        SharedResources.m19632$$Nest$fgetrect2(this.sharedResources).set(AndroidUtilities.dp(10.0f) + r115, ((this.chatActivityEnterView.getY() + this.chatActivityEnterView.getMeasuredHeight()) - AndroidUtilities.dp(5.0f)) - AndroidUtilities.dp(38.0f), (getMeasuredWidth() - AndroidUtilities.dp(10.0f)) - r03, (this.chatActivityEnterView.getY() + this.chatActivityEnterView.getMeasuredHeight()) - AndroidUtilities.dp(5.0f));
        this.chatActivityEnterView.setTranslationX(r115 * (1.0f - this.progressToKeyboard));
        if (this.chatActivityEnterView.getMeasuredHeight() <= AndroidUtilities.dp(50.0f)) goto L38;
        r1 = 0.0f + ((1.0f - this.progressToKeyboard) * (this.chatActivityEnterView.getMeasuredHeight() - AndroidUtilities.dp(50.0f)));
    L38:
        this.chatActivityEnterView.getEditField().setTranslationY(r1 + ((-AndroidUtilities.dp(2.0f)) * (1.0f - this.progressToKeyboard)));
        float r04 = AndroidUtilities.dp(50.0f) / 2.0f;
        AndroidUtilities.lerp(SharedResources.m19632$$Nest$fgetrect2(this.sharedResources), SharedResources.m19631$$Nest$fgetrect1(this.sharedResources), this.progressToKeyboard, SharedResources.m19628$$Nest$fgetfinalRect(this.sharedResources));
        BlurredBackgroundDrawable r116 = this.inputFieldBackground;
        SharedResources r15 = this.sharedResources;
        if (r116 == null) goto L41;
        r116.setBounds((int) SharedResources.m19628$$Nest$fgetfinalRect(r15).left, (int) SharedResources.m19628$$Nest$fgetfinalRect(this.sharedResources).top, (int) SharedResources.m19628$$Nest$fgetfinalRect(this.sharedResources).right, (int) SharedResources.m19628$$Nest$fgetfinalRect(this.sharedResources).bottom);
        this.inputFieldBackground.setRadius(r04);
        this.inputFieldBackground.setAlpha((int) ((((1.0f - this.progressToDismiss) * 255.0f) * getHideInterfaceAlpha()) * (1.0f - this.outT)));
        this.inputFieldBackground.draw(r3);
    L43:
        if (this.progressToKeyboard >= 0.5f) goto L76;
        r3.save();
        r3.clipRect(SharedResources.m19628$$Nest$fgetfinalRect(this.sharedResources));
        boolean r103 = super.drawChild(r3, r12, r13);
        r3.restore();
        return r103;
    L41:
        r3.drawRoundRect(SharedResources.m19628$$Nest$fgetfinalRect(r15), r04, r04, this.inputBackgroundPaint);
        goto L43
    L33:
        if (r4.getVisibility() != 0) goto L35;
        r03 = r03 + AndroidUtilities.dp(46.0f);
        goto L35
    L17:
        if (this.allowShare == false) goto L20;
        r03 = r03 + AndroidUtilities.dp(46.0f);
    L20:
        if (this.allowRepost == true) goto L22;
    L24:
        FrameLayout r42 = this.likeButtonContainer;
        if (r42 != null) goto L27;
    L29:
        r115 = 0.0f;
        goto L35
    L27:
        if (r42.getVisibility() != 0) goto L29;
        r03 = (r03 - AndroidUtilities.dp(40.0f)) + this.likeButtonContainer.getLayoutParams().width;
        goto L29
    L22:
        if (this.isChannel == false) goto L24;
        r03 = r03 + AndroidUtilities.dp(46.0f);
        goto L24
    L11:
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L13;
        SharedResources.m19627$$Nest$fgetdimPaint(this.sharedResources).setAlpha((int) (this.progressToKeyboard * 63.75f));
        r3 = r11;
        r3.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), SharedResources.m19627$$Nest$fgetdimPaint(this.sharedResources));
        goto L14
    }

    public void drawPlayingBitmap(int r6, int r7, Canvas r8) {
        VideoPlayerSharedScope r0 = this.playerSharedScope;
        View r1 = r0.renderView;
        if (r1 != null) goto L5;
    L10:
        if (r1 == null) goto L17;
        TextureView r02 = r0.textureView;
        if (r02 == null) goto L17;
        Bitmap r5 = r02.getBitmap(r6, r7);
        if (r5 == null) goto L20;
        r8.drawBitmap(r5, 0.0f, 0.0f, null);
        return;
    L20:
        return;
    L17:
        r8.save();
        r8.scale(r6 / this.storyContainer.getMeasuredWidth(), r7 / this.storyContainer.getMeasuredHeight());
        this.imageReceiver.draw(r8);
        r8.restore();
        return;
    L5:
        if (r0.surfaceView == null) goto L10;
        Bitmap r62 = Bitmap.createBitmap(r6, r7, Bitmap.Config.ARGB_8888);
        AndroidUtilities.getBitmapFromSurface(this.playerSharedScope.surfaceView, r62);
        if (r62 == null) goto L19;
        r8.drawBitmap(r62, 0.0f, 0.0f, null);
        return;
    }

    public boolean findClickableView(ViewGroup r9, float r10, float r11, boolean r12) {
        if (r9 != null) goto L5;
        return false;
    L5:
        HintView2 r1 = this.privacyHint;
        if (r1 != null) goto L8;
    L10:
        HintView2 r13 = this.soundTooltip;
        if (r13 != null) goto L13;
    L15:
        int r14 = 0;
    L17:
        if (r14 >= r9.getChildCount()) goto L96;
        View r3 = r9.getChildAt(r14);
        if (r3.getVisibility() != 0) goto L95;
        if (r3 != this.storyCaptionView) goto L28;
        Rect r4 = AndroidUtilities.rectTmp2;
        r3.getHitRect(r4);
        if (r4.contains((int) r10, (int) r11) == false) goto L28;
        if (this.storyCaptionView.allowInterceptTouchEvent(r10, r11 - r3.getTop()) == false) goto L28;
        return true;
    L28:
        Rect r42 = AndroidUtilities.rectTmp2;
        r3.getHitRect(r42);
        if (r3 != this.storyContainer) goto L38;
        VideoPlayerSharedScope r5 = this.playerSharedScope;
        if (r5 == null) goto L38;
        View r52 = r5.renderView;
        if ((r52 instanceof ViewGroup) == false) goto L38;
        if (findClickableView((ViewGroup) r52, r10 - r3.getX(), r11 - r3.getY(), r12) == false) goto L38;
        return true;
    L38:
        if (r3.isClickable() == true) goto L40;
    L42:
        StoryMediaAreasView r53 = this.storyAreasView;
        if (r3 == r53) goto L45;
    L53:
        LiveCommentsView r54 = this.liveCommentsView;
        if (r3 != r54) goto L68;
        r54.topListView.getHitRect(r42);
        if (r42.contains((int) ((r10 - this.liveCommentsView.getX()) - this.liveCommentsView.topListView.getX()), (int) ((r11 - this.liveCommentsView.getY()) - this.liveCommentsView.topListView.getY())) == true) goto L57;
        if (this.liveCommentsView.isCollapsed() == true) goto L95;
        if (this.keyboardVisible == true) goto L66;
        if (r11 > (this.liveCommentsView.getY() + this.liveCommentsView.top())) goto L66;
        LiveCommentsView r32 = this.liveCommentsView;
        if (r32.listView.findChildViewUnder(r10, (r11 - r32.getY()) - this.liveCommentsView.listView.getY()) == null) goto L95;
    L66:
        return true;
    L57:
        return true;
    L68:
        if (this.keyboardVisible == true) goto L70;
    L74:
        if (r12 == true) goto L89;
        if (r42.contains((int) r10, (int) r11) == false) goto L89;
        if (r3.isClickable() == true) goto L82;
        if (r3 == this.reactionsContainerLayout) goto L82;
    L83:
        ChatActivityEnterView r43 = this.chatActivityEnterView;
        if (r43 == null) goto L89;
        if (r3 != r43.getRecordCircle()) goto L89;
    L87:
        return true;
    L82:
        if (r3.isEnabled() == true) goto L87;
    L89:
        if (r3.isEnabled() == false) goto L95;
        if ((r3 instanceof ViewGroup) == false) goto L95;
        if (findClickableView((ViewGroup) r3, r10 - r3.getX(), r11 - r3.getY(), r12) == false) goto L95;
        return true;
    L70:
        if (r3 != this.chatActivityEnterView) goto L74;
        if (r11 <= r42.top) goto L74;
        return true;
    L45:
        if (r53.hasSelected() == true) goto L53;
        if (r10 < AndroidUtilities.dp(60.0f)) goto L51;
        if (r10 <= (r9.getMeasuredWidth() - AndroidUtilities.dp(60.0f))) goto L53;
    L51:
        if (this.storyAreasView.hasClickableViews(r10, r11) == false) goto L95;
        return true;
    L40:
        if (r42.contains((int) r10, (int) r11) == false) goto L42;
        return true;
    L95:
        r14 = r14 + 1;
        goto L17
    L96:
        return false;
    L13:
        if (r13.shown() == false) goto L15;
        return true;
    L8:
        if (r1.shown() == false) goto L10;
        return true;
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public ArrayList<Integer> getCurrentDay() {
        return this.day;
    }

    public long getCurrentPeer() {
        return this.dialogId;
    }

    public int getListPosition() {
        return this.listPosition;
    }

    public Bitmap getPlayingBitmap() {
        Bitmap r0 = Bitmap.createBitmap(this.storyContainer.getWidth(), this.storyContainer.getHeight(), Bitmap.Config.ARGB_8888);
        drawPlayingBitmap(r0.getWidth(), r0.getHeight(), new Canvas(r0));
        return r0;
    }

    public int getSelectedPosition() {
        return this.selectedPosition;
    }

    public StoriesController getStoriesController() {
        return MessagesController.getInstance(this.currentAccount).getStoriesController();
    }

    public int getStoriesCount() {
        return this.uploadingStories.size() + Math.max(this.totalStoriesCount, this.storyItems.size());
    }

    public ArrayList<TL_stories.StoryItem> getStoryItems() {
        return this.storyItems;
    }

    public abstract boolean isSelectedPeer();

    public boolean needEnterText() {
        ChatActivityEnterView r0 = this.chatActivityEnterView;
        if (r0 != null) goto L6;
        return false;
    L6:
        boolean r02 = r0.isKeyboardVisible();
        if (r02 == false) goto L9;
        this.chatActivityEnterView.showEmojiView();
    L9:
        AndroidUtilities.runOnUIThread(new PeerStoriesView$$ExternalSyntheticLambda21(this), 300);
        return r02;
    }

    public void onActionDown(MotionEvent r5) {
        HintView2 r0 = this.privacyHint;
        if (r0 != null) goto L5;
    L13:
        HintView2 r02 = this.soundTooltip;
        if (r02 != null) goto L16;
        return;
    L16:
        if (r02.shown() == true) goto L18;
        return;
    L18:
        if (this.muteIconContainer != null) goto L20;
        return;
    L20:
        if (this.soundTooltip.containsTouch(r5, (getX() + this.storyContainer.getX()) + this.soundTooltip.getX(), (getY() + this.storyContainer.getY()) + this.soundTooltip.getY()) == false) goto L22;
        return;
    L22:
        if (hitButton(this.muteIconContainer, r5) == true) goto L29;
        this.soundTooltip.hide();
        return;
    L29:
        return;
    L5:
        if (r0.shown() == false) goto L13;
        if (this.privacyButton == null) goto L13;
        if (this.privacyHint.containsTouch(r5, (getX() + this.storyContainer.getX()) + this.privacyHint.getX(), (getY() + this.storyContainer.getY()) + this.privacyHint.getY()) == true) goto L13;
        if (hitButton(this.privacyButton, r5) == true) goto L13;
        this.privacyHint.hide();
        goto L13
    }

    public void onActivityResult(int r2, int r3, Intent r4) {
        if (r3 != (-1)) goto L33;
        if (r2 != 0) goto L6;
    L28:
        createChatAttachView();
        ChatAttachAlert r1 = this.chatAttachAlert;
        if (r1 == null) goto L35;
        r1.getPhotoLayout().onActivityResultFragment(r2, r4, null);
        return;
    L35:
        return;
    L6:
        if (r2 == 2) goto L28;
        if (r2 != 21) goto L34;
        if (r4 != null) goto L14;
        showAttachmentError();
        return;
    L14:
        if (r4.getData() == null) goto L17;
        sendUriAsDocument(r4.getData());
    L23:
        ChatAttachAlert r22 = this.chatAttachAlert;
        if (r22 == null) goto L26;
        r22.dismiss();
    L26:
        afterMessageSend(true);
        return;
    L17:
        if (r4.getClipData() == null) goto L22;
        ClipData r23 = r4.getClipData();
        int r32 = 0;
    L20:
        if (r32 >= r23.getItemCount()) goto L23;
        sendUriAsDocument(r23.getItemAt(r32).getUri());
        r32 = r32 + 1;
        goto L20
    L22:
        showAttachmentError();
        goto L23
    L34:
        return;
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
        this.imageReceiver.onAttachedToWindow();
        this.rightPreloadImageReceiver.onAttachedToWindow();
        this.leftPreloadImageReceiver.onAttachedToWindow();
        this.reactionEffectImageReceiver.onAttachedToWindow();
        this.reactionMoveImageReceiver.onAttachedToWindow();
        ChatActivityEnterView r1 = this.chatActivityEnterView;
        if (r1 == null) goto L5;
        r1.onResume();
    L5:
        int r12 = 0;
    L7:
        if (r12 >= this.preloadReactionHolders.size()) goto L9;
        this.preloadReactionHolders.get(r12).onAttachedToWindow(true);
        r12 = r12 + 1;
        goto L7
    L9:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatInfoDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.liveStoryUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storyQualityUpdate);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesListUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.stealthModeChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesLimitUpdate);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.userIsPremiumBlockedUpadted);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didLoadSendAsPeers);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
        this.imageReceiver.onDetachedFromWindow();
        this.rightPreloadImageReceiver.onDetachedFromWindow();
        this.leftPreloadImageReceiver.onDetachedFromWindow();
        this.reactionEffectImageReceiver.onDetachedFromWindow();
        this.reactionMoveImageReceiver.onDetachedFromWindow();
        ChatActivityEnterView r1 = this.chatActivityEnterView;
        if (r1 == null) goto L5;
        r1.onPause();
        this.chatActivityEnterView.onDestroy();
    L5:
        AnimatedEmojiDrawable r12 = this.reactionMoveDrawable;
        if (r12 == null) goto L8;
        r12.removeView(this);
        this.reactionMoveDrawable = null;
    L8:
        AnimatedEmojiEffect r13 = this.emojiReactionEffect;
        if (r13 == null) goto L11;
        r13.removeView(this);
        this.emojiReactionEffect = null;
    L11:
        int r14 = 0;
    L13:
        if (r14 >= this.preloadReactionHolders.size()) goto L15;
        this.preloadReactionHolders.get(r14).onAttachedToWindow(false);
        r14 = r14 + 1;
        goto L13
    L15:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatInfoDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.liveStoryUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storyQualityUpdate);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesListUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.stealthModeChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesLimitUpdate);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.userIsPremiumBlockedUpadted);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didLoadSendAsPeers);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        SharedResources.m19633$$Nest$fgettopOverlayGradient(this.sharedResources).setBounds(0, 0, getMeasuredWidth(), AndroidUtilities.dp(72.0f));
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r18, int r19) {
        if (this.storyViewer.ATTACH_TO_FRAGMENT == false) goto L6;
        ((FrameLayout.LayoutParams) getLayoutParams()).topMargin = AndroidUtilities.statusBarHeight;
    L6:
        if (this.isActive == true) goto L8;
    L10:
        this.realKeyboardHeight = 0;
    L12:
        if (this.storyViewer.ATTACH_TO_FRAGMENT == false) goto L14;
        int r1 = View.MeasureSpec.getSize(r19);
    L15:
        int r3 = (int) ((View.MeasureSpec.getSize(r18) * 16.0f) / 9.0f);
        if (r1 <= r3) goto L18;
        if (r3 > r1) goto L18;
    L20:
        if (this.realKeyboardHeight >= AndroidUtilities.dp(20.0f)) goto L22;
        this.realKeyboardHeight = 0;
    L22:
        int r4 = this.realKeyboardHeight;
        ReactionsContainerLayout r5 = this.likesReactionLayout;
        if (r5 != null) goto L25;
    L29:
        ChatActivityEnterView r52 = this.chatActivityEnterView;
        if (r52 != null) goto L32;
    L42:
        boolean r53 = this.keyboardVisible;
        if (this.lastKeyboardHeight == r4) goto L102;
        this.keyboardVisible = false;
        if (r4 > 0) goto L47;
    L49:
        ChatActivityEnterView r8 = this.chatActivityEnterView;
        if (r8 == null) goto L52;
        this.storyViewer.saveDraft(this.dialogId, this.currentStory.storyItem, r8.getEditText());
    L52:
        ChatActivityEnterView r82 = this.chatActivityEnterView;
        if (r82 == null) goto L64;
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == true) goto L57;
    L61:
        boolean r10 = false;
    L62:
        r82.setSuggestionButtonVisible(r10, true);
        goto L64
    L57:
        if (disabledPaidFeatures(true) == true) goto L61;
        if (this.keyboardVisible == false) goto L61;
        r10 = true;
    L64:
        if (this.keyboardVisible == false) goto L69;
        MentionsContainerView r83 = this.mentionContainer;
        if (r83 == null) goto L69;
        r83.setVisibility(0);
    L69:
        if (this.keyboardVisible == true) goto L73;
        ReactionsContainerLayout r84 = this.reactionsContainerLayout;
        if (r84 == null) goto L73;
        r84.reset();
    L73:
        this.headerView.setEnabled(!this.keyboardVisible);
        ChatActivityEnterView r85 = this.chatActivityEnterView;
        if (r85 == null) goto L77;
        r85.checkReactionsButton(!this.keyboardVisible);
    L77:
        if (this.isActive == true) goto L79;
    L81:
        this.lastKeyboardHeight = r4;
        ValueAnimator r86 = this.keyboardAnimator;
        if (r86 == null) goto L84;
        r86.cancel();
    L84:
        this.notificationsLocker.lock();
        ValueAnimator r87 = ValueAnimator.ofFloat(new float[]{this.animatingKeyboardHeight, r4});
        this.keyboardAnimator = r87;
        r87.addUpdateListener(new PeerStoriesView$$ExternalSyntheticLambda22(this));
        this.keyboardAnimator.addListener(new AnonymousClass35(this));
        boolean r88 = this.keyboardVisible;
        ValueAnimator r102 = this.keyboardAnimator;
        if (r88 == false) goto L87;
        r102.setDuration(250);
        this.keyboardAnimator.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        this.storyViewer.cancelSwipeToReply();
    L88:
        this.keyboardAnimator.start();
        boolean r6 = this.keyboardVisible;
        if (r6 == r53) goto L101;
        if (r6 == false) goto L94;
        createBlurredBitmap(this.bitmapShaderTools.getCanvas(), this.bitmapShaderTools.getBitmap());
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L100;
        showPaidMessageHint();
    L100:
        this.animateKeyboardOpening = true;
        goto L102
    L94:
        ChatActivityEnterView r54 = this.chatActivityEnterView;
        if (r54 == null) goto L97;
        r54.getEditField().clearFocus();
    L97:
        HintView2 r55 = this.highlightMessageHintView;
        if (r55 == null) goto L100;
        r55.hide();
        goto L100
    L101:
        this.animateKeyboardOpening = false;
        goto L102
    L87:
        r102.setDuration(500);
        this.keyboardAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        goto L88
    L79:
        if (this.keyboardVisible == false) goto L81;
        this.delegate.setKeyboardVisible(true);
        goto L81
    L47:
        if (this.isActive == false) goto L49;
        this.keyboardVisible = true;
        this.messageSent = false;
        this.lastOpenedKeyboardHeight = r4;
        checkReactionsLayout();
        ReactionsEffectOverlay.dismissAll();
    L102:
        ChatActivityEnterView r56 = this.chatActivityEnterView;
        if (r56 != null) goto L105;
    L107:
        FrameLayout.LayoutParams r57 = (FrameLayout.LayoutParams) this.storyContainer.getLayoutParams();
        r57.height = r3;
        if ((r1 - r3) <= AndroidUtilities.dp(64.0f)) goto L110;
        boolean r62 = true;
    L111:
        this.BIG_SCREEN = r62;
        if (r62 == false) goto L114;
        int r63 = AndroidUtilities.dp(64.0f);
    L115:
        int r64 = (r1 - (r63 + r3)) >> 1;
        r57.topMargin = r64;
        if (this.BIG_SCREEN == false) goto L118;
        this.enterViewBottomOffset = (((-r64) + r1) - r3) - AndroidUtilities.dp(64.0f);
    L120:
        if (this.BIG_SCREEN == this.wasBigScreen) goto L122;
        this.storyContainer.setLayoutParams(r57);
    L122:
        FrameLayout r58 = this.selfView;
        if (r58 == null) goto L128;
        FrameLayout.LayoutParams r59 = (FrameLayout.LayoutParams) r58.getLayoutParams();
        if (this.BIG_SCREEN == false) goto L127;
        r59.topMargin = (r64 + r3) + AndroidUtilities.dp(8.0f);
        goto L128
    L127:
        r59.topMargin = (r64 + r3) - AndroidUtilities.dp(48.0f);
    L128:
        TextView r510 = this.replyDisabledTextView;
        if (r510 == null) goto L133;
        FrameLayout.LayoutParams r511 = (FrameLayout.LayoutParams) r510.getLayoutParams();
        boolean r13 = this.BIG_SCREEN;
        TextView r14 = this.replyDisabledTextView;
        if (r13 == true) goto L134;
        r14.setTextColor(ColorUtils.setAlphaComponent(-1, 191));
        r511.topMargin = ((r64 + r3) - AndroidUtilities.dp(12.0f)) - AndroidUtilities.dp(40.0f);
        goto L133
    L134:
        char r16 = 0;
        r14.setTextColor(ColorUtils.blendARGB(-16777216, -1, 0.5f));
        r511.topMargin = (r64 + r3) + AndroidUtilities.dp(12.0f);
    L135:
        InstantCameraView r2 = this.instantCameraView;
        if (r2 == null) goto L141;
        FrameLayout.LayoutParams r22 = (FrameLayout.LayoutParams) r2.getLayoutParams();
        if (r4 != 0) goto L140;
        r22.bottomMargin = r1 - ((r64 + r3) - AndroidUtilities.dp(64.0f));
        goto L141
    L140:
        r22.bottomMargin = r4 + AndroidUtilities.dp(64.0f);
    L141:
        boolean r23 = this.BIG_SCREEN;
        LinearLayout r42 = this.bottomActionsLinearLayout;
        if (r23 == true) goto L151;
        ((FrameLayout.LayoutParams) r42.getLayoutParams()).topMargin = ((r64 + r3) - AndroidUtilities.dp(12.0f)) - AndroidUtilities.dp(40.0f);
        if (this.isSelf == false) goto L146;
        int r24 = AndroidUtilities.dp(40.0f);
    L147:
        ((FrameLayout.LayoutParams) this.storyCaptionView.getLayoutParams()).bottomMargin = r24;
        if (this.wasBigScreen == this.BIG_SCREEN) goto L150;
        StoryCaptionView r32 = this.storyCaptionView;
        r32.setLayoutParams((FrameLayout.LayoutParams) r32.getLayoutParams());
    L150:
        this.storyCaptionView.blackoutBottomOffset = r24;
    L155:
        this.forceUpdateOffsets = true;
        float r25 = AndroidUtilities.dp(48.0f);
        if (this.privacyButton.getVisibility() != 0) goto L159;
        r25 = r25 + AndroidUtilities.dp(60.0f);
    L159:
        if (this.muteIconContainer.getVisibility() != 0) goto L161;
        r25 = r25 + AndroidUtilities.dp(40.0f);
    L161:
        FrameLayout.LayoutParams r33 = (FrameLayout.LayoutParams) this.headerView.titleView.getLayoutParams();
        if (r33.rightMargin == r25) goto L164;
        int r26 = (int) r25;
        r33.rightMargin = r26;
        ((FrameLayout.LayoutParams) PeerHeaderView.m19624$$Nest$fgetsubtitleView(this.headerView)[r16].getLayoutParams()).rightMargin = r26;
        ((FrameLayout.LayoutParams) PeerHeaderView.m19624$$Nest$fgetsubtitleView(this.headerView)[1].getLayoutParams()).rightMargin = r26;
        this.headerView.forceLayout();
    L164:
        super.onMeasure(r18, View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30));
        this.wasBigScreen = this.BIG_SCREEN;
        return;
    L146:
        r24 = AndroidUtilities.dp(56.0f);
        goto L147
    L151:
        ((FrameLayout.LayoutParams) r42.getLayoutParams()).topMargin = (r64 + r3) + AndroidUtilities.dp(12.0f);
        ((FrameLayout.LayoutParams) this.storyCaptionView.getLayoutParams()).bottomMargin = AndroidUtilities.dp(8.0f);
        if (this.wasBigScreen == this.BIG_SCREEN) goto L154;
        StoryCaptionView r27 = this.storyCaptionView;
        r27.setLayoutParams((FrameLayout.LayoutParams) r27.getLayoutParams());
    L154:
        this.storyCaptionView.blackoutBottomOffset = AndroidUtilities.dp(8.0f);
    L133:
        r16 = 0;
        goto L135
    L118:
        this.enterViewBottomOffset = ((-r64) + r1) - r3;
        goto L120
    L114:
        r63 = 0;
        goto L115
    L110:
        r62 = false;
        goto L111
    L105:
        if (r56.getEmojiView() == null) goto L107;
        ((FrameLayout.LayoutParams) this.chatActivityEnterView.getEmojiView().getLayoutParams()).gravity = 80;
        goto L107
    L32:
        if (r52.isPopupShowing() == false) goto L34;
    L35:
        int r43 = this.chatActivityEnterView.getEmojiView().getMeasuredHeight();
        ChatActivityEnterView r512 = this.chatActivityEnterView;
        if (r43 != 0) goto L38;
        r4 = r512.getEmojiPadding();
        goto L42
    L38:
        boolean r44 = r512.isStickersExpanded();
        ChatActivityEnterView r513 = this.chatActivityEnterView;
        if (r44 == false) goto L41;
        r513.checkStickresExpandHeight();
        r4 = this.chatActivityEnterView.getStickersExpandedHeight();
        goto L42
    L41:
        r4 = r513.getVisibleEmojiPadding();
        goto L42
    L34:
        if (this.chatActivityEnterView.isWaitingForKeyboard() == false) goto L42;
    L25:
        if (r5.getReactionsWindow() == null) goto L29;
        if (this.likesReactionLayout.getReactionsWindow().isShowing() == false) goto L29;
        this.likesReactionLayout.getReactionsWindow().windowView.animate().translationY(-this.realKeyboardHeight).setDuration(250).setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator).start();
        r4 = 0;
    L18:
        r3 = r1;
        goto L20
    L14:
        r1 = View.MeasureSpec.getSize(r19) + this.realKeyboardHeight;
        goto L15
    L8:
        if (this.shareAlert != null) goto L10;
        this.realKeyboardHeight = this.delegate.getKeyboardHeight();
        goto L12
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        this.progressToKeyboard = -1.0f;
        this.forceUpdateOffsets = true;
        invalidate();
    }

    public void preloadMainImage(long r3) {
        if (this.dialogId == r3) goto L5;
    L7:
        this.dialogId = r3;
        updateStoryItems();
        updateSelectedPosition();
        updatePosition(true);
        TL_stories.PeerStories r1 = this.storyViewer.overrideUserStories;
        StoriesController r2 = this.storiesController;
        if (r1 == null) goto L11;
        r2.loadSkippedStories(r1, true);
        return;
    L11:
        r2.loadSkippedStories(r3);
        return;
    L5:
        if (this.day != null) goto L7;
    }

    public void progressToDismissUpdated() {
        if (this.BIG_SCREEN == false) goto L6;
        invalidate();
        return;
    }

    public void reset() {
        this.headerView.backupImageView.getImageReceiver().setVisible(true, true);
        if (this.changeBoundAnimator == null) goto L5;
        this.chatActivityEnterView.reset();
        this.chatActivityEnterView.setAlpha(1.0f - this.outT);
    L5:
        ReactionsContainerLayout r0 = this.reactionsContainerLayout;
        if (r0 == null) goto L8;
        r0.reset();
    L8:
        ReactionsContainerLayout r02 = this.likesReactionLayout;
        if (r02 == null) goto L11;
        r02.reset();
    L11:
        InstantCameraView r03 = this.instantCameraView;
        if (r03 == null) goto L14;
        AndroidUtilities.removeFromParent(r03);
        this.instantCameraView.hideCamera(true);
        this.instantCameraView = null;
    L14:
        setActive(false);
        setIsVisible(false);
        this.isLongPressed = false;
        this.progressToHideInterface.set(0.0f, false);
        this.viewsThumbImageReceiver = null;
        this.messageSent = false;
        cancelTextSelection();
    }

    public void selectPosition(int r2) {
        if (this.selectedPosition == r2) goto L6;
        this.selectedPosition = r2;
        updatePosition();
        return;
    }

    public void setAccount(int r4) {
        this.currentAccount = r4;
        this.storiesController = MessagesController.getInstance(r4).storiesController;
        this.emojiAnimationsOverlay.setAccount(r4);
        ReactionsContainerLayout r0 = this.reactionsContainerLayout;
        if (r0 == null) goto L5;
        r0.setCurrentAccount(r4);
        this.reactionsContainerLayout.setMessage(null, null, true);
    L5:
        ReactionsContainerLayout r3 = this.likesReactionLayout;
        if (r3 == null) goto L9;
        r3.setCurrentAccount(r4);
        return;
    }

    public void setActive(long r5, boolean r7) {
        if (this.isActive == r7) goto L34;
        int r0 = activeCount;
        if (r7 == false) goto L7;
        int r2 = 1;
    L8:
        activeCount = r0 + r2;
        this.isActive = r7;
        int r02 = 0;
        if (r7 == true) goto L11;
        cancelTextSelection();
        this.muteIconView.clearAnimationDrawable();
        this.viewsThumbImageReceiver = null;
        this.isLongPressed = false;
        this.progressToHideInterface.set(0.0f, true);
        this.storyContainer.invalidate();
        invalidate();
        cancelWaiting();
        this.delegate.setIsRecording(false);
    L17:
        ImageReceiver r52 = this.imageReceiver;
        if (this.isActive == false) goto L20;
        int r6 = 3;
    L21:
        r52.setFileLoadingPriority(r6);
        ImageReceiver r53 = this.leftPreloadImageReceiver;
        if (this.isActive == false) goto L24;
        int r62 = 2;
    L25:
        r53.setFileLoadingPriority(r62);
        ImageReceiver r54 = this.rightPreloadImageReceiver;
        if (this.isActive == false) goto L28;
        r02 = 2;
    L28:
        r54.setFileLoadingPriority(r02);
        if (this.isSelf == false) goto L31;
    L32:
        this.storiesController.pollViewsForSelfStories(this.dialogId, this.isActive);
        return;
    L31:
        if (this.isChannel == true) goto L32;
        return;
    L24:
        r62 = 0;
        goto L25
    L20:
        r6 = 2;
        goto L21
    L11:
        if (useSurfaceInViewPagerWorkAround() == false) goto L13;
        this.delegate.setIsSwiping(true);
        AndroidUtilities.cancelRunOnUIThread(this.allowDrawSurfaceRunnable);
        AndroidUtilities.runOnUIThread(this.allowDrawSurfaceRunnable, 100);
    L13:
        requestVideoPlayer(r5);
        updatePreloadImages();
        this.muteIconView.setAnimation(this.sharedResources.muteDrawable);
        this.isActive = true;
        this.headerView.backupImageView.getImageReceiver().setVisible(true, true);
        if (this.currentStory.storyItem == null) goto L17;
        FileLog.d("StoryViewer displayed story dialogId=" + this.dialogId + " storyId=" + this.currentStory.storyItem.id + " " + StoryItemHolder.m19636$$Nest$mgetMediaDebugString(this.currentStory));
        goto L17
    L7:
        r2 = -1;
        goto L8
    }

    public void setActive(boolean r3) {
        setActive(0, r3);
    }

    public void setDay(long r1, ArrayList<Integer> r3, int r4) {
        this.dialogId = r1;
        this.day = r3;
        bindInternal(r4);
    }

    public void setDelegate(Delegate r1) {
        this.delegate = r1;
    }

    public void setDialogId(long r3, int r5) {
        if (this.dialogId == r3) goto L5;
        this.currentStory.clear();
    L5:
        this.dialogId = r3;
        this.day = null;
        bindInternal(r5);
        TL_stories.PeerStories r52 = this.storyViewer.overrideUserStories;
        StoriesController r2 = this.storiesController;
        if (r52 == null) goto L9;
        r2.loadSkippedStories(r52, true);
        return;
    L9:
        r2.loadSkippedStories(r3);
    }

    public void setIsVisible(boolean r2) {
        if (this.isVisible == r2) goto L10;
        this.isVisible = r2;
        if (r2 == false) goto L9;
        this.imageReceiver.setCurrentAlpha(1.0f);
        checkStealthMode(false);
        return;
    L9:
        return;
    }

    public void setLongpressed(boolean r2) {
        if (this.isActive == false) goto L6;
        this.isLongPressed = r2;
        invalidate();
        return;
    }

    public void setOffset(float r3) {
        if (r3 != 0.0f) goto L5;
        boolean r32 = true;
    L7:
        if (this.allowDrawSurface == r32) goto L18;
        this.allowDrawSurface = r32;
        this.storyContainer.invalidate();
        if (this.isActive == true) goto L11;
        return;
    L11:
        if (useSurfaceInViewPagerWorkAround() == false) goto L20;
        Runnable r1 = this.allowDrawSurfaceRunnable;
        if (r32 == false) goto L16;
        AndroidUtilities.cancelRunOnUIThread(r1);
        AndroidUtilities.runOnUIThread(this.allowDrawSurfaceRunnable, 250);
        return;
    L16:
        AndroidUtilities.cancelRunOnUIThread(r1);
        this.delegate.setIsSwiping(true);
        return;
    L20:
        return;
    L18:
        return;
    L5:
        r32 = false;
        goto L7
    }

    public void setPaused(boolean r3) {
        if (this.paused == r3) goto L6;
        this.paused = r3;
        stopPlaying(r3);
        this.lastDrawTime = 0;
        this.storyContainer.invalidate();
        return;
    }

    public void setViewsThumbImageReceiver(float r1, float r2, float r3, SelfStoriesPreviewView.ImageHolder r4) {
        this.viewsThumbAlpha = r1;
        this.viewsThumbScale = 1.0f / r2;
        this.viewsThumbPivotY = r3;
        if (this.viewsThumbImageReceiver == r4) goto L13;
        this.viewsThumbImageReceiver = r4;
        if (r4 != null) goto L8;
        return;
    L8:
        if (r4.receiver.getBitmap() == null) goto L12;
        this.imageReceiver.updateStaticDrawableThump(r4.receiver.getBitmap().copy(Bitmap.Config.ARGB_8888, false));
        return;
    L12:
        return;
    }

    public boolean showKeyboard() {
        if (this.chatActivityEnterView == null) goto L14;
        TextView r0 = this.replyDisabledTextView;
        if (r0 != null) goto L7;
    L9:
        EditTextCaption r2 = this.chatActivityEnterView.getEditField();
        if (r2 != null) goto L12;
        return false;
    L12:
        r2.requestFocus();
        AndroidUtilities.showKeyboard(r2);
        return true;
    L7:
        if (r0.getVisibility() != 0) goto L9;
    L14:
        return false;
    }

    public void showNoSoundHint(boolean r10) {
        if (this.soundTooltip != null) goto L5;
        HintView2 r0 = new HintView2(getContext(), 1).setJoint(1.0f, -56.0f);
        this.soundTooltip = r0;
        r0.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.storyContainer.addView(this.soundTooltip, LayoutHelper.createFrame(-1, -2.0f, 55, 0.0f, 52.0f, 0.0f, 0.0f));
    L5:
        HintView2 r02 = this.soundTooltip;
        if (r10 == false) goto L8;
        int r102 = R.string.StoryNoSound;
    L9:
        r02.setText(LocaleController.getString(r102));
        this.soundTooltip.show();
        return;
    L8:
        r102 = R.string.StoryTapToSound;
        goto L9
    }

    public void stopPlaying(boolean r2) {
        ImageReceiver r0 = this.imageReceiver;
        if (r2 == false) goto L6;
        r0.stopAnimation();
        this.imageReceiver.setAllowStartAnimation(false);
        return;
    L6:
        r0.startAnimation();
        this.imageReceiver.setAllowStartAnimation(true);
    }

    public boolean switchToNext(boolean r3) {
        if (this.storyViewer.reversed == false) goto L5;
        r3 = !r3;
    L5:
        int r0 = this.selectedPosition;
        if (r3 == true) goto L8;
        if (r0 <= 0) goto L16;
        this.selectedPosition = r0 - 1;
        updatePosition();
        return true;
    L16:
        return false;
    L8:
        if (r0 >= (getStoriesCount() - 1)) goto L14;
        this.selectedPosition++;
        updatePosition();
        return true;
    L14:
        return false;
    }

    public void updatePosition() {
        updatePosition(false);
    }

    public boolean useSurfaceInViewPagerWorkAround() {
        if (this.storyViewer.USE_SURFACE_VIEW == true) goto L5;
        return false;
    L5:
        if (Build.VERSION.SDK_INT >= 33) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean viewsAllowed() {
        if (StoryItemHolder.m19634$$Nest$fgetisLive(this.currentStory) == false) goto L6;
        return false;
    L6:
        if (this.isSelf == false) goto L8;
        return true;
    L8:
        if (this.isChannel == true) goto L10;
    L12:
        return false;
    L10:
        if (this.userCanSeeViews == false) goto L12;
        return true;
    }
}
