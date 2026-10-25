package org.telegram.ui;

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
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.IconPackType;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaError;
import com.google.android.material.timepicker.TimeModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import kotlin.time.DurationKt;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AndroidUtilities$$ExternalSyntheticApiModelOutline1;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ChatThemeController$$ExternalSyntheticLambda6;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.EmojiData;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.camera.CameraView$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.pip.PipSource;
import org.telegram.messenger.pip.utils.PipUtils;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.messenger.voip.ConferenceCall;
import org.telegram.messenger.voip.GroupCallMessage;
import org.telegram.messenger.voip.GroupCallMessagesController;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.messenger.voip.VoipAudioManager;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.AccountSelectCell;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.GroupCallInvitedCell;
import org.telegram.ui.Cells.GroupCallTextCell;
import org.telegram.ui.Cells.GroupCallUserCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.AudioPlayerAlert;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BlobDrawable;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CheckBoxSquare;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EditTextEmoji;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.FillLastGridLayoutManager;
import org.telegram.ui.Components.GroupCallFullscreenAdapter;
import org.telegram.ui.Components.GroupCallPip;
import org.telegram.ui.Components.GroupCallRecordAlert;
import org.telegram.ui.Components.GroupVoipInviteAlert;
import org.telegram.ui.Components.HintView;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.JoinCallAlert;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberPicker;
import org.telegram.ui.Components.PermissionRequest;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet;
import org.telegram.ui.Components.ProfileGalleryView;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.Reactions.CustomEmojiReactionsWindow;
import org.telegram.ui.Components.Reactions.ReactionsEffectOverlay;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.ReactionsContainerLayout;
import org.telegram.ui.Components.RecordStatusDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.UndoView;
import org.telegram.ui.Components.conference.GroupCallActivityButtonsLayout;
import org.telegram.ui.Components.conference.message.GroupCallMessageCell;
import org.telegram.ui.Components.conference.message.GroupCallMessagesListView;
import org.telegram.ui.Components.inset.WindowInsetsStateHolder;
import org.telegram.ui.Components.voip.CellFlickerDrawable;
import org.telegram.ui.Components.voip.GroupCallGridCell;
import org.telegram.ui.Components.voip.GroupCallMiniTextureView;
import org.telegram.ui.Components.voip.GroupCallRenderersContainer;
import org.telegram.ui.Components.voip.GroupCallStatusIcon;
import org.telegram.ui.Components.voip.PrivateVideoPreviewDialog;
import org.telegram.ui.Components.voip.RTMPStreamPipOverlay;
import org.telegram.ui.Components.voip.VoIPTextureView;
import org.telegram.ui.Components.voip.VoIPToggleButton;
import org.telegram.ui.PinchToZoomHelper;
import org.telegram.ui.Stories.recorder.DominantColors;
import org.webrtc.MediaStreamTrack;
import org.webrtc.voiceengine.WebRtcAudioTrack;

/* loaded from: classes3.dex */
public class GroupCallActivity extends BottomSheet implements NotificationCenter.NotificationCenterDelegate, VoIPService.StateListener, FactorAnimator.Target {
    public static GroupCallActivity groupCallInstance;
    public static boolean groupCallUiVisible;
    public static boolean isLandscapeMode;
    public static boolean isTabletMode;
    public static boolean paused;
    public static volatile DispatchQueue updateTextureLightningQueue;
    private final AccountInstance accountInstance;
    private final AccountSelectCell accountSelectCell;
    private final ActionBar actionBar;
    private AnimatorSet actionBarAnimation;
    private final View actionBarBackground;
    private final View actionBarShadow;
    ObjectAnimator additionalSubtitleYAnimator;
    private ActionBarMenuSubItem adminItem;
    private float amplitude;
    private float animateAmplitudeDiff;
    private float animateToAmplitude;
    private boolean animatingToFullscreenExpand;
    private final BoolAnimator animatorHasVideo;
    private final BoolAnimator animatorHideButtons;
    private final FactorAnimator animatorMessageInputHeight;
    private final BoolAnimator animatorMessageIsEmpty;
    private boolean anyEnterEventSent;
    private final ArrayList<GroupCallMiniTextureView> attachedRenderers;
    private final ArrayList<GroupCallMiniTextureView> attachedRenderersTmp;
    private final AvatarPreviewPagerIndicator avatarPagerIndicator;
    private final FrameLayout avatarPreviewContainer;
    private boolean avatarPriviewTransitionInProgress;
    AvatarUpdaterDelegate avatarUpdaterDelegate;
    private boolean avatarsPreviewShowed;
    private final ProfileGalleryView avatarsViewPager;
    private int backgroundColor;
    private final RLottieDrawable bigMicDrawable;
    private final BlobDrawable bigWaveDrawable;
    private final View blurredView;
    private final FrameLayout bulletinContainer;
    private GradientDrawable buttonsBackgroundGradient;
    private final View buttonsBackgroundGradientView;
    private final View buttonsBackgroundGradientView2;
    private final GroupCallActivityButtonsLayout buttonsContainer;
    private int buttonsVisibility;
    private Integer cacheAudioOutputValue;
    public ChatObject.Call call;
    private boolean callInitied;
    private final FrameLayout callMessageEnterContainer;
    private final Paint callMessageEnterContainerBgPaint;
    private final FrameLayout callMessageEnterUnderContainer;
    private final EditTextEmoji callMessageEnterView;
    private final ImageView callMessageHideButton;
    private final ImageView callMessageSendButton;
    private final VoIPToggleButton cameraButton;
    public CellFlickerDrawable cellFlickerDrawable;
    private boolean changingPermissions;
    private float colorProgress;
    private final int[] colorsTmp;
    private boolean contentFullyOverlayed;
    private long creatingServiceTime;
    ImageUpdater currentAvatarUpdater;
    private int currentCallState;
    public TLRPC.Chat currentChat;
    private ViewGroup currentOptionsLayout;
    private WeavingState currentState;
    private boolean delayedGroupCallUpdated;
    private DiffUtil.Callback diffUtilsCallback;
    private final ActionBarMenuSubItem disableComments;
    private boolean drawSpeakingSubtitle;
    public boolean drawingForBlur;
    private final ActionBarMenuSubItem editTitleItem;
    private final ActionBarMenuSubItem enableComments;
    private CallEncryptionCellDrawable encryptionDrawable;
    private boolean enterEventSent;
    private ActionBarMenuSubItem everyoneItem;
    private final ImageView expandOrMinimizeButton;
    private final VoIPToggleButton flipButton;
    private final RLottieDrawable flipIcon;
    private int flipIconCurrentEndFrame;
    GroupCallFullscreenAdapter fullscreenAdapter;
    private final DefaultItemAnimator fullscreenListItemAnimator;
    RecyclerListView fullscreenUsersListView;
    private final int[] gradientColors;
    private final GroupCallMessagesListView groupCallMessagesListView;
    private GroupVoipInviteAlert groupVoipInviteAlert;
    private final RLottieDrawable handDrawables;
    private boolean hasScrimAnchorView;
    private boolean hasVideo;
    private boolean invalidateColors;
    private final ActionBarMenuSubItem inviteItem;
    private String[] invites;
    private boolean isInDrawRenderNodeBlur;
    private boolean isInFullscreen;
    private final GroupCallItemAnimator itemAnimator;
    private long lastUpdateTime;
    private final FillLastGridLayoutManager layoutManager;
    private final VoIPToggleButton leaveButton;
    private final ActionBarMenuSubItem leaveItem;
    private final AnimatedTextView limitTextView;
    private final ListAdapter listAdapter;
    private RecyclerListView listView;
    private final Paint listViewBackgroundPaint;
    private boolean listViewVideoVisibility;
    private ValueAnimator liveLabelBgColorAnimator;
    private final Paint liveLabelPaint;
    private final TextView liveLabelTextView;
    private final int maxGroupCallMessageLength;
    private final LinearLayout menuItemsContainer;
    private final VoIPToggleButton messageButton;
    private final VoIPToggleButton muteButton;
    private final RLottieImageView muteButtonIcon;
    private int muteButtonState;
    private boolean needTextureLightning;
    private final ActionBarMenuSubItem noiseItem;
    private int oldAddMemberRow;
    private int oldConferenceAddPeopleRow;
    private int oldConferenceShareLinkRow;
    private int oldCount;
    private int oldEncryptionRow;
    private final ArrayList<Long> oldInvited;
    private int oldInvitedEndRow;
    private int oldInvitedStartRow;
    private final ArrayList<TLRPC.GroupCallParticipant> oldParticipants;
    private final ArrayList<Long> oldShadyJoin;
    private int oldShadyJoinEndRow;
    private int oldShadyJoinStartRow;
    private final ArrayList<Long> oldShadyLeft;
    private int oldShadyLeftEndRow;
    private int oldShadyLeftStartRow;
    private int oldUsersEndRow;
    private int oldUsersStartRow;
    private int oldUsersVideoEndRow;
    private int oldUsersVideoStartRow;
    private int oldVideoDividerRow;
    private int oldVideoNotAvailableRow;
    private final ArrayList<ChatObject.VideoParticipant> oldVideoParticipants;
    private final Runnable onUserLeaveHintListener;
    private final ActionBarMenuItem otherItem;
    private Paint paint;
    private Paint paintTmp;
    private LaunchActivity parentActivity;
    private Boolean pendingCommentsEnabled;
    private final ActionBarMenuSubItem permissionItem;
    PinchToZoomHelper pinchToZoomHelper;
    private final ActionBarMenuItem pipItem;
    private PipSource pipSource;
    private boolean playingHandAnimation;
    private int popupAnimationIndex;
    private final Runnable pressRunnable;
    private boolean pressed;
    private WeavingState prevState;
    PrivateVideoPreviewDialog previewDialog;
    private boolean previewTextureTransitionEnabled;
    private float progressToAvatarPreview;
    private RadialGradient radialGradient;
    private final Matrix radialMatrix;
    private final Paint radialPaint;
    private final RadialProgressView radialProgressView;
    private final ImageReceiver reactionEffectImageReceiver;
    private ReactionsContainerLayout reactionsContainerLayout;
    private RecordCallDrawable recordCallDrawable;
    private HintView recordHintView;
    private final ActionBarMenuSubItem recordItem;
    private final RectF rect;
    private HintView reminderHintView;
    private RenderNode renderNodeBlur;
    private float renderNodeBlurScale;
    private GroupCallRenderersContainer renderersContainer;
    ViewTreeObserver.OnPreDrawListener requestFullscreenListener;
    private ValueAnimator scheduleAnimator;
    private TextView scheduleButtonTextView;
    private final boolean scheduleHasFewPeers;
    private TextView scheduleInfoTextView;
    private TLRPC.InputPeer schedulePeer;
    private int scheduleStartAt;
    private SimpleTextView scheduleStartAtTextView;
    private SimpleTextView scheduleStartInTextView;
    private SimpleTextView scheduleTimeTextView;
    private LinearLayout scheduleTimerContainer;
    private boolean scheduled;
    private final String scheduledHash;
    private final ActionBarMenuSubItem screenItem;
    private final ActionBarMenuItem screenShareItem;
    private AnimatorSet scrimAnimatorSet;
    private GroupCallFullscreenAdapter.GroupCallUserCell scrimFullscreenView;
    private GroupCallGridCell scrimGridView;
    private Paint scrimPaint;
    private View scrimPopupLayout;
    private ActionBarPopupWindow scrimPopupWindow;
    private GroupCallMiniTextureView scrimRenderer;
    private GroupCallUserCell scrimView;
    private boolean scrimViewAttached;
    private float scrollOffsetY;
    private TLRPC.Peer selfPeer;
    private final Drawable shadowDrawable;
    private ShareAlert shareAlert;
    private float showLightingProgress;
    private float showWavesProgress;
    private final SizeNotifierFrameLayout sizeNotifierFrameLayout;
    private final VoIPToggleButton soundButton;
    private ActionBarMenuSubItem soundItem;
    private final View soundItemDivider;
    private final GridLayoutManager.SpanSizeLookup spanSizeLookup;
    private final VoIPToggleButton speakerButton;
    private int speakerIcon;
    private final ImageView speakerImageView;
    private boolean startingGroupCall;
    private WeavingState[] states;
    public final ArrayList<GroupCallStatusIcon> statusIconPool;
    ObjectAnimator subtitleYAnimator;
    private float switchProgress;
    private float switchToButtonInt2;
    private float switchToButtonProgress;
    GroupCallTabletGridAdapter tabletGridAdapter;
    RecyclerListView tabletVideoGridView;
    private LightningView textureLightningView;
    private float textureLightningViewAnimatingAlpha;
    private final BlobDrawable tinyWaveDrawable;
    private final LinearLayout titleLayout;
    private final AudioPlayerAlert.ClippingTextViewSwitcher titleTextView;
    private final UndoView[] undoView;
    private final Runnable unmuteRunnable;
    private Runnable updateCallRecordRunnable;
    private final Runnable updateSchedeulRunnable;
    private final Runnable updateTextureLightningRunnable;
    private boolean useBlur;
    private TLObject userSwitchObject;
    LongSparseIntArray visiblePeerIds;
    public final ArrayList<ChatObject.VideoParticipant> visibleVideoParticipants;
    private Boolean wasExpandBigSize;
    private Boolean wasNotInLayoutFullscreen;
    private WatchersView watchersView;
    private final WindowInsetsStateHolder windowInsetsStateHolder;

    /* renamed from: org.telegram.ui.GroupCallActivity$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass1(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (GroupCallActivity.m15194$$Nest$fgetscheduleTimeTextView(this.this$0) != null) goto L5;
            return;
        L5:
            if (this.this$0.isDismissed() == true) goto L24;
            GroupCallActivity r0 = this.this$0;
            ChatObject.Call r1 = r0.call;
            if (r1 == null) goto L10;
            int r02 = r1.call.schedule_date;
        L11:
            if (r02 == 0) goto L25;
            int r12 = r02 - GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getConnectionsManager().getCurrentTime();
            GroupCallActivity r2 = this.this$0;
            if (r12 < 86400) goto L16;
            GroupCallActivity.m15194$$Nest$fgetscheduleTimeTextView(r2).setText(LocaleController.formatPluralString("Days", Math.round(r12 / 86400.0f), new Object[0]));
        L21:
            GroupCallActivity.m15192$$Nest$fgetscheduleStartAtTextView(this.this$0).setText(LocaleController.formatStartsTime(r02, 3));
            AndroidUtilities.runOnUIThread(GroupCallActivity.m15224$$Nest$fgetupdateSchedeulRunnable(this.this$0), 1000);
            return;
        L16:
            GroupCallActivity.m15194$$Nest$fgetscheduleTimeTextView(r2).setText(AndroidUtilities.formatFullDuration(Math.abs(r12)));
            if (r12 >= 0) goto L21;
            if (GroupCallActivity.m15193$$Nest$fgetscheduleStartInTextView(this.this$0).getTag() != null) goto L21;
            GroupCallActivity.m15193$$Nest$fgetscheduleStartInTextView(this.this$0).setTag(1);
            GroupCallActivity.m15193$$Nest$fgetscheduleStartInTextView(this.this$0).setText(LocaleController.getString(R.string.VoipChatLateBy));
            goto L21
        L25:
            return;
        L10:
            r02 = GroupCallActivity.m15191$$Nest$fgetscheduleStartAt(r0);
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$10, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass10 extends RecyclerView.OnScrollListener {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass10(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r2, int r3) {
            if (r3 != 0) goto L14;
            if (((GroupCallActivity.m15205$$Nest$fgetscrollOffsetY(this.this$0) - AndroidUtilities.dp(74.0f)) + GroupCallActivity.access$1600(this.this$0)) < ActionBar.getCurrentActionBarHeight()) goto L6;
            return;
        L6:
            if (GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).canScrollVertically(1) == false) goto L21;
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildAt(0);
            RecyclerListView.Holder r22 = (RecyclerListView.Holder) GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).findViewHolderForAdapterPosition(0);
            if (r22 != null) goto L10;
            return;
        L10:
            if (r22.itemView.getTop() <= 0) goto L23;
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).smoothScrollBy(0, r22.itemView.getTop());
            return;
        L23:
            return;
        L21:
            return;
        L14:
            if (GroupCallActivity.m15182$$Nest$fgetrecordHintView(this.this$0) == null) goto L17;
            GroupCallActivity.m15182$$Nest$fgetrecordHintView(this.this$0).hide();
        L17:
            if (GroupCallActivity.m15185$$Nest$fgetreminderHintView(this.this$0) == null) goto L24;
            GroupCallActivity.m15185$$Nest$fgetreminderHintView(this.this$0).hide();
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildCount() <= 0) goto L19;
            GroupCallActivity r12 = this.this$0;
            ChatObject.Call r22 = r12.call;
            if (r22 != null) goto L8;
            return;
        L8:
            if (r22.loadingMembers == false) goto L10;
        L14:
            GroupCallActivity.m15289$$Nest$mupdateLayout(this.this$0, true);
            if (GroupCallActivity.m15218$$Nest$fgettextureLightningView(this.this$0) == null) goto L17;
            GroupCallActivity.m15218$$Nest$fgettextureLightningView(this.this$0).invalidate();
        L17:
            GroupCallActivity.access$1500(this.this$0).invalidate();
            return;
        L10:
            if (r22.membersLoadEndReached == true) goto L14;
            if (GroupCallActivity.m15123$$Nest$fgetlayoutManager(r12).findLastVisibleItemPosition() <= (GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0).getItemCount() - 5)) goto L14;
            this.this$0.call.loadMembers(false);
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$11, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass11 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass11(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r5) {
            if (GroupCallActivity.isLandscapeMode == false) goto L5;
            int r0 = 6;
        L7:
            if (GroupCallActivity.isTabletMode == false) goto L9;
        L29:
            return r0;
        L9:
            if (r5 < ListAdapter.m15332$$Nest$fgetusersVideoGridStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L29;
            if (r5 >= ListAdapter.m15331$$Nest$fgetusersVideoGridEndRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L29;
            int r02 = ListAdapter.m15331$$Nest$fgetusersVideoGridEndRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0)) - ListAdapter.m15332$$Nest$fgetusersVideoGridStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0));
            if (r5 == (ListAdapter.m15331$$Nest$fgetusersVideoGridEndRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0)) - 1)) goto L15;
        L19:
            int r4 = 1;
        L21:
            if (GroupCallActivity.isLandscapeMode == false) goto L28;
            if (r02 != 1) goto L24;
            return 6;
        L24:
            if (r02 != 2) goto L27;
            return 3;
        L27:
            return 2;
        L28:
            return r4;
        L15:
            if (GroupCallActivity.isLandscapeMode == false) goto L18;
        L16:
            r4 = 2;
            goto L21
        L18:
            if ((r02 % 2) != 0) goto L16;
        L5:
            r0 = 2;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$12, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass12 extends RecyclerView.ItemDecoration {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass12(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            int r22 = r3.getChildAdapterPosition(r2);
            if (r22 < 0) goto L22;
            r1.setEmpty();
            if (r22 >= ListAdapter.m15332$$Nest$fgetusersVideoGridStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L7;
            return;
        L7:
            if (r22 >= ListAdapter.m15331$$Nest$fgetusersVideoGridEndRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L24;
            int r23 = r22 - ListAdapter.m15332$$Nest$fgetusersVideoGridStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0));
            if (GroupCallActivity.isLandscapeMode == false) goto L11;
            int r0 = 6;
        L12:
            int r24 = r23 % r0;
            if (r24 != 0) goto L17;
            r1.right = AndroidUtilities.dp(2.0f);
            return;
        L17:
            if (r24 != (r0 - 1)) goto L20;
            r1.left = AndroidUtilities.dp(2.0f);
            return;
        L20:
            r1.left = AndroidUtilities.dp(1.0f);
            return;
        L11:
            r0 = 2;
            goto L12
        L24:
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$13, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass13 implements GroupVoipInviteAlert.GroupVoipInviteAlertDelegate {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass13(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.GroupVoipInviteAlert.GroupVoipInviteAlertDelegate
        public void copyInviteLink() {
            GroupCallActivity.m15273$$Nest$mgetLink(this.this$0, true);
        }

        @Override // org.telegram.ui.Components.GroupVoipInviteAlert.GroupVoipInviteAlertDelegate
        public void inviteUser(long r2) {
            GroupCallActivity.m15276$$Nest$minviteUserToCall(this.this$0, r2, true);
        }

        @Override // org.telegram.ui.Components.GroupVoipInviteAlert.GroupVoipInviteAlertDelegate
        public void needOpenSearch(MotionEvent r4, EditTextBoldCursor r5) {
            if (GroupCallActivity.m15109$$Nest$fgetenterEventSent(this.this$0) == false) goto L5;
            return;
        L5:
            if (r4.getX() > r5.getLeft()) goto L7;
        L14:
            GroupCallActivity r3 = this.this$0;
            r3.makeFocusable(GroupCallActivity.m15114$$Nest$fgetgroupVoipInviteAlert(r3), null, r5, false);
            return;
        L7:
            if (r4.getX() >= r5.getRight()) goto L14;
            if (r4.getY() <= r5.getTop()) goto L14;
            if (r4.getY() >= r5.getBottom()) goto L14;
            GroupCallActivity r32 = this.this$0;
            r32.makeFocusable(GroupCallActivity.m15114$$Nest$fgetgroupVoipInviteAlert(r32), null, r5, true);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$14, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass14 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass14(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r1) {
            return this.this$0.tabletGridAdapter.getSpanCount(r1);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$15, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass15 extends DefaultItemAnimator {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass15(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).invalidate();
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).invalidate();
            GroupCallActivity.access$1700(this.this$0).invalidate();
            GroupCallActivity.m15289$$Nest$mupdateLayout(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$16, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass16 extends RecyclerView.OnScrollListener {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass16(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            GroupCallActivity.access$1800(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$17, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass17 extends GroupCallActivityButtonsLayout {
        int currentLightColor;
        final OvershootInterpolator overshootInterpolator;
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass17(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.overshootInterpolator = new OvershootInterpolator(1.5f);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r28) {
            if (GroupCallActivity.m15101$$Nest$fgetcontentFullyOverlayed(this.this$0) == true) goto L5;
        L7:
            int r6 = (getMeasuredWidth() - getMeasuredHeight()) / 2;
            long r4 = SystemClock.elapsedRealtime();
            long r7 = r4 - GroupCallActivity.m15122$$Nest$fgetlastUpdateTime(this.this$0);
            GroupCallActivity.m15240$$Nest$fputlastUpdateTime(this.this$0, r4);
            if (r7 <= 20) goto L10;
            r7 = 17;
        L10:
            long r8 = r7;
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0) == null) goto L13;
            GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).update(0, r6, getMeasuredHeight(), r8, GroupCallActivity.m15076$$Nest$fgetamplitude(this.this$0));
        L13:
            GroupCallActivity.m15220$$Nest$fgettinyWaveDrawable(this.this$0).minRadius = AndroidUtilities.dp(62.0f) * 0.46296296f;
            GroupCallActivity.m15220$$Nest$fgettinyWaveDrawable(this.this$0).maxRadius = (AndroidUtilities.dp(62.0f) + (AndroidUtilities.dp(20.0f) * BlobDrawable.FORM_SMALL_MAX)) * 0.48076922f;
            GroupCallActivity.m15090$$Nest$fgetbigWaveDrawable(this.this$0).minRadius = AndroidUtilities.dp(65.0f) * 0.46296296f;
            GroupCallActivity.m15090$$Nest$fgetbigWaveDrawable(this.this$0).maxRadius = (AndroidUtilities.dp(65.0f) + (AndroidUtilities.dp(20.0f) * BlobDrawable.FORM_BIG_MAX)) * 0.48076922f;
            if (GroupCallActivity.m15078$$Nest$fgetanimateToAmplitude(this.this$0) == GroupCallActivity.m15076$$Nest$fgetamplitude(this.this$0)) goto L23;
            GroupCallActivity r2 = this.this$0;
            GroupCallActivity.m15230$$Nest$fputamplitude(r2, GroupCallActivity.m15076$$Nest$fgetamplitude(r2) + (GroupCallActivity.m15077$$Nest$fgetanimateAmplitudeDiff(this.this$0) * r8));
            float r22 = GroupCallActivity.m15077$$Nest$fgetanimateAmplitudeDiff(this.this$0);
            GroupCallActivity r5 = this.this$0;
            if (r22 <= 0.0f) goto L21;
            if (GroupCallActivity.m15076$$Nest$fgetamplitude(r5) <= GroupCallActivity.m15078$$Nest$fgetanimateToAmplitude(this.this$0)) goto L23;
            GroupCallActivity r23 = this.this$0;
            GroupCallActivity.m15230$$Nest$fputamplitude(r23, GroupCallActivity.m15078$$Nest$fgetanimateToAmplitude(r23));
            goto L23
        L21:
            if (GroupCallActivity.m15076$$Nest$fgetamplitude(r5) >= GroupCallActivity.m15078$$Nest$fgetanimateToAmplitude(this.this$0)) goto L23;
            GroupCallActivity r24 = this.this$0;
            GroupCallActivity.m15230$$Nest$fputamplitude(r24, GroupCallActivity.m15078$$Nest$fgetanimateToAmplitude(r24));
        L23:
            int r52 = 0;
            int r62 = 3;
            if (GroupCallActivity.m15173$$Nest$fgetprevState(this.this$0) == null) goto L31;
            if (GroupCallActivity.m15173$$Nest$fgetprevState(this.this$0).currentState != 3) goto L31;
            GroupCallActivity.m15179$$Nest$fgetradialProgressView(this.this$0).toCircle(true, true);
            if (GroupCallActivity.m15179$$Nest$fgetradialProgressView(this.this$0).isCircle() == true) goto L37;
            boolean r25 = false;
        L39:
            if (r25 == true) goto L41;
            int r17 = 2;
        L120:
            float r3 = (this.overshootInterpolator.getInterpolation(GroupCallActivity.m15209$$Nest$fgetshowWavesProgress(this.this$0)) * 0.6f) + 0.4f;
            GroupCallActivity.m15090$$Nest$fgetbigWaveDrawable(this.this$0).update(GroupCallActivity.m15076$$Nest$fgetamplitude(this.this$0), 1.0f);
            GroupCallActivity.m15220$$Nest$fgettinyWaveDrawable(this.this$0).update(GroupCallActivity.m15076$$Nest$fgetamplitude(this.this$0), 1.0f);
            float r11 = 0.5f;
            float r12 = 25.909092f;
            float r13 = 25.0f;
            float r14 = 255.0f;
            float r15 = 2.0f;
            if (GroupCallActivity.m15173$$Nest$fgetprevState(this.this$0) == null) goto L148;
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0) == null) goto L148;
            if (this.this$0.isRtmpStream() == true) goto L148;
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).currentState != 3) goto L129;
        L132:
            int r42 = GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).currentState;
            GroupCallActivity r53 = this.this$0;
            if (r42 != 3) goto L135;
            float r43 = GroupCallActivity.m15215$$Nest$fgetswitchProgress(r53);
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setShader(GroupCallActivity.m15173$$Nest$fgetprevState(this.this$0).shader);
        L136:
            GroupCallActivity.m15166$$Nest$fgetpaintTmp(this.this$0).setColor(AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_listViewBackgroundUnscrolled), Theme.getColor(Theme.key_voipgroup_disabledButton), GroupCallActivity.m15099$$Nest$fgetcolorProgress(this.this$0), 1.0f));
            int r54 = (int) (GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).getX() + (GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).getMeasuredWidth() / 2.0f));
            float r55 = r54;
            float r63 = (int) (GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).getY() + AndroidUtilities.dp(25.0f));
            GroupCallActivity.m15177$$Nest$fgetradialMatrix(this.this$0).setTranslate(r55, r63);
            GroupCallActivity.m15176$$Nest$fgetradialGradient(this.this$0).setLocalMatrix(GroupCallActivity.m15177$$Nest$fgetradialMatrix(this.this$0));
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setAlpha(76);
            r28.save();
            r28.scale(BlobDrawable.GLOBAL_SCALE * GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).getScaleX(), BlobDrawable.GLOBAL_SCALE * GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).getScaleY(), r55, r63);
            r28.save();
            float r72 = BlobDrawable.SCALE_BIG_MIN + ((BlobDrawable.SCALE_BIG * GroupCallActivity.m15076$$Nest$fgetamplitude(this.this$0)) * 0.5f);
            r28.scale(GroupCallActivity.m15208$$Nest$fgetshowLightingProgress(this.this$0) * r72, r72 * GroupCallActivity.m15208$$Nest$fgetshowLightingProgress(this.this$0), r55, r63);
            float r73 = BlobDrawable.LIGHT_GRADIENT_SIZE + 0.7f;
            r28.save();
            r28.scale(r73, r73, r55, r63);
            r28.drawCircle(r55, r63, AndroidUtilities.dp(160.0f), GroupCallActivity.m15178$$Nest$fgetradialPaint(this.this$0));
            r28.restore();
            r28.restore();
            if (this.this$0.call == null) goto L139;
            r28.save();
            float r74 = (BlobDrawable.SCALE_BIG_MIN + (BlobDrawable.SCALE_BIG * GroupCallActivity.m15076$$Nest$fgetamplitude(this.this$0))) * r3;
            r28.scale(r74, r74, r55, r63);
            GroupCallActivity.m15090$$Nest$fgetbigWaveDrawable(this.this$0).draw(r55, r63, r28, GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0));
            r28.restore();
            r28.save();
            float r75 = (BlobDrawable.SCALE_SMALL_MIN + (BlobDrawable.SCALE_SMALL * GroupCallActivity.m15076$$Nest$fgetamplitude(this.this$0))) * r3;
            r28.scale(r75, r75, r55, r63);
            GroupCallActivity.m15220$$Nest$fgettinyWaveDrawable(this.this$0).draw(r55, r63, r28, GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0));
            r28.restore();
        L139:
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setAlpha(255);
            if (r25 == false) goto L144;
            r28.drawCircle(r55, r63, AndroidUtilities.dp(25.909092f), GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0));
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setColor(Theme.getColor(Theme.key_voipgroup_connectingProgress));
            if (r43 == 0.0f) goto L144;
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setAlpha((int) (255.0f * r43));
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setShader(null);
            r28.drawCircle(r55, r63, AndroidUtilities.dp(25.909092f), GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0));
        L144:
            r28.drawCircle(r55, r63, AndroidUtilities.dp(25.0f) * r43, GroupCallActivity.m15166$$Nest$fgetpaintTmp(this.this$0));
            if (r25 == true) goto L147;
            GroupCallActivity.m15179$$Nest$fgetradialProgressView(this.this$0).draw(r28, r55, r63);
        L147:
            r28.restore();
        L220:
            super.dispatchDraw(r28);
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).isAnimating() == true) goto L229;
            invalidate();
            return;
        L229:
            return;
        L135:
            r43 = 1.0f - GroupCallActivity.m15215$$Nest$fgetswitchProgress(r53);
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setShader(GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).shader);
            goto L136
        L129:
            if (GroupCallActivity.m15173$$Nest$fgetprevState(this.this$0).currentState == 3) goto L132;
        L148:
            int r26 = r17;
        L149:
            if (r52 >= r26) goto L220;
            float r44 = AndroidUtilities.dp(r12);
            if (r52 == 0) goto L153;
        L161:
            if (r52 == 1) goto L163;
        L218:
            float r21 = r11;
            float r222 = r13;
            float r232 = r14;
            float r242 = r15;
            int r112 = r62;
        L219:
            r52 = r52 + 1;
            r62 = r112;
            r11 = r21;
            r13 = r222;
            r14 = r232;
            r15 = r242;
            r26 = 2;
            r12 = 25.909092f;
            goto L149
        L163:
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0) == null) goto L218;
            if (this.this$0.isRtmpStream() == true) goto L167;
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setShader(GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).shader);
        L167:
            float r9 = GroupCallActivity.m15215$$Nest$fgetswitchProgress(this.this$0);
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).currentState != r62) goto L171;
            int r10 = AndroidUtilities.dp(r15);
        L160:
            r44 = r44 - (r10 * r9);
        L171:
            if (GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).getShader() == null) goto L173;
        L175:
            r21 = r11;
            r222 = r13;
        L177:
            if (this.this$0.isRtmpStream() == false) goto L179;
            int r27 = Theme.getColor(Theme.key_voipgroup_rtmpButton);
            int r102 = Theme.key_voipgroup_disabledButton;
            int r29 = AndroidUtilities.getOffsetColor(r27, Theme.getColor(r102), GroupCallActivity.m15099$$Nest$fgetcolorProgress(this.this$0), 1.0f);
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setColor(r29);
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setShader(null);
            VoIPToggleButton r113 = GroupCallActivity.m15210$$Nest$fgetsoundButton(this.this$0);
            int r132 = Theme.key_voipgroup_listViewBackgroundUnscrolled;
            r232 = r14;
            r242 = r15;
            r113.setBackgroundColor(AndroidUtilities.getOffsetColor(Theme.getColor(r132), Theme.getColor(r102), GroupCallActivity.m15099$$Nest$fgetcolorProgress(this.this$0), 1.0f), r29);
            GroupCallActivity.m15136$$Nest$fgetmessageButton(this.this$0).setBackgroundColor(AndroidUtilities.getOffsetColor(Theme.getColor(r132), Theme.getColor(r102), GroupCallActivity.m15099$$Nest$fgetcolorProgress(this.this$0), 1.0f), r29);
        L180:
            int r210 = (int) (GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).getX() + (GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).getMeasuredWidth() / r242));
            float r211 = r210;
            float r64 = (int) (GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).getY() + AndroidUtilities.dp(r222));
            GroupCallActivity.m15177$$Nest$fgetradialMatrix(this.this$0).setTranslate(r211, r64);
            GroupCallActivity.m15176$$Nest$fgetradialGradient(this.this$0).setLocalMatrix(GroupCallActivity.m15177$$Nest$fgetradialMatrix(this.this$0));
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setAlpha((int) ((76.0f * r9) * GroupCallActivity.m15217$$Nest$fgetswitchToButtonProgress(this.this$0)));
            r28.save();
            r28.scale(BlobDrawable.GLOBAL_SCALE * GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).getScaleX(), BlobDrawable.GLOBAL_SCALE * GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).getScaleX(), r211, r64);
            r28.save();
            float r103 = BlobDrawable.SCALE_BIG_MIN + ((BlobDrawable.SCALE_BIG * GroupCallActivity.m15076$$Nest$fgetamplitude(this.this$0)) * r21);
            r28.scale(GroupCallActivity.m15208$$Nest$fgetshowLightingProgress(this.this$0) * r103, r103 * GroupCallActivity.m15208$$Nest$fgetshowLightingProgress(this.this$0), r211, r64);
            if (r52 != 1) goto L188;
            if (this.this$0.isRtmpStream() == true) goto L188;
            if (LiteMode.isEnabled(512) == false) goto L188;
            float r104 = BlobDrawable.LIGHT_GRADIENT_SIZE + 0.7f;
            r28.save();
            r28.scale(r104, r104, r211, r64);
            int r105 = GroupCallActivity.m15178$$Nest$fgetradialPaint(this.this$0).getAlpha();
            GroupCallActivity.m15178$$Nest$fgetradialPaint(this.this$0).setAlpha((int) ((r105 * GroupCallActivity.m15217$$Nest$fgetswitchToButtonProgress(this.this$0)) * (1.0f - GroupCallActivity.m15079$$Nest$fgetanimatorHideButtons(this.this$0).getFloatValue())));
            r28.drawCircle(r211, r64, AndroidUtilities.dp(160.0f), GroupCallActivity.m15178$$Nest$fgetradialPaint(this.this$0));
            GroupCallActivity.m15178$$Nest$fgetradialPaint(this.this$0).setAlpha(r105);
            r28.restore();
        L188:
            r28.restore();
            if (this.this$0.isRtmpStream() == true) goto L195;
            if (GroupCallActivity.m15217$$Nest$fgetswitchToButtonProgress(this.this$0) <= 0.0f) goto L195;
            r28.save();
            float r106 = BlobDrawable.SCALE_BIG_MIN + ((BlobDrawable.SCALE_BIG * GroupCallActivity.m15076$$Nest$fgetamplitude(this.this$0)) * r3);
            r28.scale(r106, r106, r211, r64);
            GroupCallActivity.m15090$$Nest$fgetbigWaveDrawable(this.this$0).draw(r211, r64, r28, GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0));
            r28.restore();
            r28.save();
            float r107 = BlobDrawable.SCALE_SMALL_MIN + ((BlobDrawable.SCALE_SMALL * GroupCallActivity.m15076$$Nest$fgetamplitude(this.this$0)) * r3);
            r28.scale(r107, r107, r211, r64);
            GroupCallActivity.m15220$$Nest$fgettinyWaveDrawable(this.this$0).draw(r211, r64, r28, GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0));
            r28.restore();
        L195:
            if (GroupCallActivity.isLandscapeMode == false) goto L200;
            GroupCallActivity r108 = this.this$0;
            if (r52 != 0) goto L199;
            GroupCallActivity.m15165$$Nest$fgetpaint(r108).setAlpha((int) (GroupCallActivity.m15216$$Nest$fgetswitchToButtonInt2(this.this$0) * r232));
        L204:
            float r92 = (getMeasuredWidth() / r242) - AndroidUtilities.dp(21.0f);
            float r109 = AndroidUtilities.dp(24.0f);
            float r93 = (r92 + (r44 - r92)) * GroupCallActivity.m15217$$Nest$fgetswitchToButtonProgress(this.this$0);
            float r1010 = (r109 + (r44 - r109)) * GroupCallActivity.m15217$$Nest$fgetswitchToButtonProgress(this.this$0);
            GroupCallActivity.m15184$$Nest$fgetrect(this.this$0).set(r211 - r93, r64 - r1010, r93 + r211, r1010 + r64);
            float r1011 = AndroidUtilities.dp(4.0f) + (r44 - AndroidUtilities.dp(4.0f));
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setAlpha((int) (GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).getAlpha() * GroupCallActivity.m15217$$Nest$fgetswitchToButtonProgress(this.this$0)));
            r28.drawRoundRect(GroupCallActivity.m15184$$Nest$fgetrect(this.this$0), r1011, r1011, GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0));
            if (r52 != 1) goto L212;
            r112 = 3;
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).currentState == 3) goto L209;
        L213:
            r28.restore();
            if (GroupCallActivity.m15189$$Nest$fgetscheduleButtonTextView(this.this$0) == null) goto L219;
            if (GroupCallActivity.m15189$$Nest$fgetscheduleButtonTextView(this.this$0).getVisibility() != 0) goto L219;
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setAlpha((int) (GroupCallActivity.m15189$$Nest$fgetscheduleButtonTextView(this.this$0).getAlpha() * r232));
            float r212 = GroupCallActivity.m15189$$Nest$fgetscheduleButtonTextView(this.this$0).getX() - getX();
            float r45 = GroupCallActivity.m15189$$Nest$fgetscheduleButtonTextView(this.this$0).getY() - getY();
            GroupCallActivity.m15184$$Nest$fgetrect(this.this$0).set(r212, r45, GroupCallActivity.m15189$$Nest$fgetscheduleButtonTextView(this.this$0).getMeasuredWidth() + r212, GroupCallActivity.m15189$$Nest$fgetscheduleButtonTextView(this.this$0).getMeasuredHeight() + r45);
            r28.save();
            r28.scale(GroupCallActivity.m15189$$Nest$fgetscheduleButtonTextView(this.this$0).getScaleX(), GroupCallActivity.m15189$$Nest$fgetscheduleButtonTextView(this.this$0).getScaleY(), GroupCallActivity.m15184$$Nest$fgetrect(this.this$0).centerX(), GroupCallActivity.m15184$$Nest$fgetrect(this.this$0).centerY());
            r28.drawRoundRect(GroupCallActivity.m15184$$Nest$fgetrect(this.this$0), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0));
            r28.restore();
            goto L219
        L209:
            if (this.this$0.isRtmpStream() == false) goto L211;
            GroupCallActivity.m15179$$Nest$fgetradialProgressView(this.this$0).setSize((int) ((r1011 * r242) - AndroidUtilities.dp(4.0f)));
        L211:
            GroupCallActivity.m15179$$Nest$fgetradialProgressView(this.this$0).draw(r28, r211, r64);
            goto L213
        L212:
            r112 = 3;
            goto L213
        L199:
            GroupCallActivity.m15165$$Nest$fgetpaint(r108).setAlpha((int) ((r9 * r232) * GroupCallActivity.m15216$$Nest$fgetswitchToButtonInt2(this.this$0)));
            goto L204
        L200:
            GroupCallActivity r1012 = this.this$0;
            if (r52 != 0) goto L203;
            GroupCallActivity.m15165$$Nest$fgetpaint(r1012).setAlpha(255);
            goto L204
        L203:
            GroupCallActivity.m15165$$Nest$fgetpaint(r1012).setAlpha((int) (r9 * r232));
            goto L204
        L179:
            r232 = r14;
            r242 = r15;
            goto L180
        L173:
            if (this.this$0.isRtmpStream() == true) goto L175;
            r21 = r11;
            r222 = r13;
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setColor(AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_listViewBackgroundUnscrolled), Theme.getColor(Theme.key_voipgroup_disabledButton), GroupCallActivity.m15099$$Nest$fgetcolorProgress(this.this$0), 1.0f));
            goto L177
        L153:
            if (GroupCallActivity.m15173$$Nest$fgetprevState(this.this$0) == null) goto L161;
            if (this.this$0.isRtmpStream() == true) goto L157;
            GroupCallActivity.m15165$$Nest$fgetpaint(this.this$0).setShader(GroupCallActivity.m15173$$Nest$fgetprevState(this.this$0).shader);
        L157:
            r9 = 1.0f - GroupCallActivity.m15215$$Nest$fgetswitchProgress(this.this$0);
            if (GroupCallActivity.m15173$$Nest$fgetprevState(this.this$0).currentState != r62) goto L171;
            r10 = AndroidUtilities.dp(r15);
            goto L160
        L41:
            if (GroupCallActivity.m15215$$Nest$fgetswitchProgress(this.this$0) == 1.0f) goto L57;
            if (GroupCallActivity.m15173$$Nest$fgetprevState(this.this$0) != null) goto L45;
        L47:
            GroupCallActivity r122 = this.this$0;
            GroupCallActivity.m15261$$Nest$fputswitchProgress(r122, GroupCallActivity.m15215$$Nest$fgetswitchProgress(r122) + (r8 / 180.0f));
        L49:
            if (GroupCallActivity.m15215$$Nest$fgetswitchProgress(this.this$0) < 1.0f) goto L55;
            GroupCallActivity.m15261$$Nest$fputswitchProgress(this.this$0, 1.0f);
            GroupCallActivity.m15246$$Nest$fputprevState(this.this$0, null);
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0) == null) goto L55;
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).currentState != 3) goto L55;
            GroupCallActivity.m15179$$Nest$fgetradialProgressView(this.this$0).toCircle(false, true);
        L55:
            GroupCallActivity.m15238$$Nest$fputinvalidateColors(this.this$0, true);
            goto L57
        L45:
            if (GroupCallActivity.m15173$$Nest$fgetprevState(this.this$0).currentState != 3) goto L47;
            GroupCallActivity r123 = this.this$0;
            GroupCallActivity.m15261$$Nest$fputswitchProgress(r123, GroupCallActivity.m15215$$Nest$fgetswitchProgress(r123) + (r8 / 100.0f));
        L57:
            if (GroupCallActivity.m15118$$Nest$fgetinvalidateColors(this.this$0) == true) goto L59;
        L69:
            r17 = 2;
            float r262 = 0.0f;
            float r18 = 1.0f;
            float r16 = 180.0f;
        L71:
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0) != null) goto L73;
            boolean r32 = false;
            boolean r46 = false;
        L87:
            if (GroupCallActivity.m15173$$Nest$fgetprevState(this.this$0) != null) goto L89;
        L95:
            if (r32 == true) goto L97;
        L101:
            if (r32 == false) goto L103;
        L107:
            if (r46 == true) goto L109;
        L113:
            if (r46 == true) goto L120;
            if (GroupCallActivity.m15208$$Nest$fgetshowLightingProgress(this.this$0) == 0.0f) goto L120;
            GroupCallActivity r33 = this.this$0;
            GroupCallActivity.m15258$$Nest$fputshowLightingProgress(r33, GroupCallActivity.m15208$$Nest$fgetshowLightingProgress(r33) - (r8 / 350.0f));
            if (GroupCallActivity.m15208$$Nest$fgetshowLightingProgress(this.this$0) >= 0.0f) goto L120;
            GroupCallActivity.m15258$$Nest$fputshowLightingProgress(this.this$0, 0.0f);
            goto L120
        L109:
            if (GroupCallActivity.m15208$$Nest$fgetshowLightingProgress(this.this$0) == 1.0f) goto L113;
            GroupCallActivity r34 = this.this$0;
            GroupCallActivity.m15258$$Nest$fputshowLightingProgress(r34, GroupCallActivity.m15208$$Nest$fgetshowLightingProgress(r34) + (r8 / 350.0f));
            if (GroupCallActivity.m15208$$Nest$fgetshowLightingProgress(this.this$0) <= 1.0f) goto L120;
            GroupCallActivity.m15258$$Nest$fputshowLightingProgress(this.this$0, 1.0f);
            goto L120
        L103:
            if (GroupCallActivity.m15209$$Nest$fgetshowWavesProgress(this.this$0) == 0.0f) goto L107;
            GroupCallActivity r35 = this.this$0;
            GroupCallActivity.m15259$$Nest$fputshowWavesProgress(r35, GroupCallActivity.m15209$$Nest$fgetshowWavesProgress(r35) - (r8 / 350.0f));
            if (GroupCallActivity.m15209$$Nest$fgetshowWavesProgress(this.this$0) >= 0.0f) goto L107;
            GroupCallActivity.m15259$$Nest$fputshowWavesProgress(this.this$0, 0.0f);
            goto L107
        L97:
            if (GroupCallActivity.m15209$$Nest$fgetshowWavesProgress(this.this$0) == r18) goto L101;
            GroupCallActivity r36 = this.this$0;
            GroupCallActivity.m15259$$Nest$fputshowWavesProgress(r36, GroupCallActivity.m15209$$Nest$fgetshowWavesProgress(r36) + (r8 / 350.0f));
            if (GroupCallActivity.m15209$$Nest$fgetshowWavesProgress(this.this$0) <= r18) goto L107;
            GroupCallActivity.m15259$$Nest$fputshowWavesProgress(this.this$0, r18);
            goto L107
        L89:
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0) == null) goto L95;
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).currentState != 3) goto L95;
            GroupCallActivity r37 = this.this$0;
            GroupCallActivity.m15259$$Nest$fputshowWavesProgress(r37, GroupCallActivity.m15209$$Nest$fgetshowWavesProgress(r37) - (r8 / r16));
            if (GroupCallActivity.m15209$$Nest$fgetshowWavesProgress(this.this$0) >= r262) goto L107;
            GroupCallActivity.m15259$$Nest$fputshowWavesProgress(this.this$0, r262);
            goto L107
        L73:
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).currentState != 1) goto L75;
        L80:
            r32 = true;
        L82:
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).currentState == 3) goto L84;
            r46 = true;
            goto L87
        L84:
            r46 = false;
            goto L87
        L75:
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).currentState == 0) goto L80;
            if (GroupCallActivity.isGradientState(GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).currentState) == true) goto L80;
            r32 = false;
            goto L82
        L59:
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0) == null) goto L69;
            GroupCallActivity.m15238$$Nest$fputinvalidateColors(this.this$0, false);
            WeavingState r124 = GroupCallActivity.m15173$$Nest$fgetprevState(this.this$0);
            GroupCallActivity r142 = this.this$0;
            if (r124 == null) goto L63;
            GroupCallActivity.m15271$$Nest$mfillColors(r142, GroupCallActivity.m15173$$Nest$fgetprevState(r142).currentState, GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0));
            int r125 = GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0)[0];
            int r143 = GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0)[1];
            int r152 = GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0)[2];
            r16 = 180.0f;
            int r133 = GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0)[3];
            r17 = 2;
            GroupCallActivity r38 = this.this$0;
            r18 = 1.0f;
            GroupCallActivity.m15271$$Nest$mfillColors(r38, GroupCallActivity.m15103$$Nest$fgetcurrentState(r38).currentState, GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0));
            int r39 = ColorUtils.blendARGB(r125, GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0)[0], GroupCallActivity.m15215$$Nest$fgetswitchProgress(this.this$0));
            int r1013 = ColorUtils.blendARGB(r143, GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0)[1], GroupCallActivity.m15215$$Nest$fgetswitchProgress(this.this$0));
            int r114 = ColorUtils.blendARGB(r152, GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0)[2], GroupCallActivity.m15215$$Nest$fgetswitchProgress(this.this$0));
            int r126 = ColorUtils.blendARGB(r133, GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0)[3], GroupCallActivity.m15215$$Nest$fgetswitchProgress(this.this$0));
        L65:
            if (this.currentLightColor == r39) goto L67;
            r262 = 0.0f;
            GroupCallActivity.m15248$$Nest$fputradialGradient(this.this$0, new RadialGradient(0.0f, 0.0f, AndroidUtilities.dp(45.454548f), new int[]{ColorUtils.setAlphaComponent(r39, 60), ColorUtils.setAlphaComponent(r39, 0)}, null, Shader.TileMode.CLAMP));
            GroupCallActivity.m15178$$Nest$fgetradialPaint(this.this$0).setShader(GroupCallActivity.m15176$$Nest$fgetradialGradient(this.this$0));
            this.currentLightColor = r39;
        L68:
            GroupCallActivity.m15136$$Nest$fgetmessageButton(this.this$0).setBackgroundColor(r114, r1013);
            GroupCallActivity.m15210$$Nest$fgetsoundButton(this.this$0).setBackgroundColor(r114, r1013);
            GroupCallActivity.m15098$$Nest$fgetcameraButton(this.this$0).setBackgroundColor(r114, r1013);
            GroupCallActivity.m15111$$Nest$fgetflipButton(this.this$0).setBackgroundColor(r114, r1013);
            VoIPToggleButton r310 = GroupCallActivity.m15124$$Nest$fgetleaveButton(this.this$0);
            int r47 = Theme.key_voipgroup_leaveButton;
            r310.setBackgroundColor(Theme.getColor(r47), Theme.getColor(r47));
            GroupCallActivity.m15213$$Nest$fgetspeakerButton(this.this$0).setBackgroundColor(r1013, r126);
            goto L71
        L67:
            r262 = 0.0f;
            goto L68
        L63:
            r17 = 2;
            r18 = 1.0f;
            r16 = 180.0f;
            GroupCallActivity.m15271$$Nest$mfillColors(r142, GroupCallActivity.m15103$$Nest$fgetcurrentState(r142).currentState, GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0));
            r39 = GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0)[0];
            r1013 = GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0)[1];
            r114 = GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0)[2];
            r126 = GroupCallActivity.m15100$$Nest$fgetcolorsTmp(this.this$0)[3];
        L37:
            r25 = true;
        L31:
            if (GroupCallActivity.m15173$$Nest$fgetprevState(this.this$0) == null) goto L37;
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0) == null) goto L37;
            if (GroupCallActivity.m15103$$Nest$fgetcurrentState(this.this$0).currentState != 3) goto L37;
            GroupCallActivity.m15179$$Nest$fgetradialProgressView(this.this$0).toCircle(true, false);
            goto L37
        L5:
            if (GroupCallActivity.m15226$$Nest$fgetuseBlur(this.this$0) == false) goto L7;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$18, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass18 extends RLottieImageView {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass18(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
            super.onInitializeAccessibilityNodeInfo(r3);
            r3.setClassName(Button.class.getName());
            if (GroupCallActivity.m15139$$Nest$fgetmuteButtonState(this.this$0) != 0) goto L5;
        L8:
            boolean r0 = true;
        L9:
            r3.setEnabled(r0);
            if (GroupCallActivity.m15139$$Nest$fgetmuteButtonState(this.this$0) != 1) goto L13;
            r3.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, LocaleController.getString(R.string.VoipMute)));
            return;
        L13:
            return;
        L5:
            if (GroupCallActivity.m15139$$Nest$fgetmuteButtonState(this.this$0) == 1) goto L8;
            r0 = false;
            goto L9
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r14) {
            if (this.this$0.isRtmpStream() == false) goto L7;
            return super.onTouchEvent(r14);
        L7:
            if (r14.getAction() != 0) goto L14;
            if (GroupCallActivity.m15139$$Nest$fgetmuteButtonState(this.this$0) != 0) goto L14;
            GroupCallActivity r0 = this.this$0;
            if (r0.call == null) goto L14;
            AndroidUtilities.runOnUIThread(GroupCallActivity.m15171$$Nest$fgetpressRunnable(r0), 300);
            GroupCallActivity.m15253$$Nest$fputscheduled(this.this$0, true);
        L33:
            return super.onTouchEvent(r14);
        L14:
            if (r14.getAction() != 1) goto L16;
        L17:
            boolean r02 = GroupCallActivity.m15195$$Nest$fgetscheduled(this.this$0);
            GroupCallActivity r3 = this.this$0;
            if (r02 == false) goto L21;
            AndroidUtilities.cancelRunOnUIThread(GroupCallActivity.m15171$$Nest$fgetpressRunnable(r3));
            GroupCallActivity.m15253$$Nest$fputscheduled(this.this$0, false);
            goto L33
        L21:
            if (GroupCallActivity.m15172$$Nest$fgetpressed(r3) == false) goto L33;
            AndroidUtilities.cancelRunOnUIThread(GroupCallActivity.m15223$$Nest$fgetunmuteRunnable(this.this$0));
            GroupCallActivity.m15290$$Nest$mupdateMuteButton(this.this$0, 0, true);
            if (VoIPService.getSharedInstance() == null) goto L26;
            VoIPService.getSharedInstance().setMicMute(true, true, false);
            GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).performHapticFeedback(3, 2);     // Catch: Exception -> L34
        L26:
            GroupCallActivity.m15083$$Nest$fgetattachedRenderersTmp(this.this$0).clear();
            GroupCallActivity.m15083$$Nest$fgetattachedRenderersTmp(this.this$0).addAll(GroupCallActivity.m15082$$Nest$fgetattachedRenderers(this.this$0));
            int r142 = 0;
        L27:
            int r03 = GroupCallActivity.m15083$$Nest$fgetattachedRenderersTmp(this.this$0).size();
            GroupCallActivity r2 = this.this$0;
            if (r142 >= r03) goto L30;
            ((GroupCallMiniTextureView) GroupCallActivity.m15083$$Nest$fgetattachedRenderersTmp(r2).get(r142)).updateAttachState(true);
            r142 = r142 + 1;
            goto L27
        L30:
            GroupCallActivity.m15245$$Nest$fputpressed(r2, false);
            MotionEvent r143 = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
            super.onTouchEvent(r143);
            r143.recycle();
            return true;
        L16:
            if (r14.getAction() != 3) goto L33;
            goto L17
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$19, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass19 implements View.OnClickListener {
        Runnable finishRunnable;
        final /* synthetic */ GroupCallActivity this$0;

        /* renamed from: org.telegram.ui.GroupCallActivity$19$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ AnonymousClass19 this$1;

            public AnonymousClass1(AnonymousClass19 r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                GroupCallActivity.m15138$$Nest$fgetmuteButtonIcon(this.this$1.this$0).setAnimation(GroupCallActivity.m15089$$Nest$fgetbigMicDrawable(this.this$1.this$0));
                GroupCallActivity.m15244$$Nest$fputplayingHandAnimation(this.this$1.this$0, false);
            }
        }

        public static /* synthetic */ void $r8$lambda$7XvB8YXWIaVVVGuTO4QnycqHR7U(AnonymousClass19 r0) {
            r0.lambda$onClick$0();
        }

        public static /* synthetic */ void $r8$lambda$WBvGn6F4pzOphu_G3yMuIFwkJL0(AnonymousClass19 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$onClick$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$dl9sXoysEmR31gchJio4oHQXUFA(Boolean r0) {
            r0.getClass();
        }

        public static /* synthetic */ void $r8$lambda$m6AvMWJ7bstuFz2Fl7eJI_OxDWc(AnonymousClass19 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$onClick$1(r1, r2);
        }

        public AnonymousClass19(GroupCallActivity r1) {
            this.this$0 = r1;
            this.finishRunnable = new AnonymousClass1(this);
        }

        private /* synthetic */ void lambda$onClick$0() {
            GroupCallActivity.m15263$$Nest$fputwasNotInLayoutFullscreen(this.this$0, null);
            GroupCallActivity r2 = this.this$0;
            GroupCallActivity.m15290$$Nest$mupdateMuteButton(r2, GroupCallActivity.m15139$$Nest$fgetmuteButtonState(r2), true);
        }

        private /* synthetic */ void lambda$onClick$1(TLObject r1, TLRPC.TL_error r2) {
            if (r1 == null) goto L5;
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getMessagesController().processUpdates((TLRPC.Updates) r1, false);
            return;
        }

        private /* synthetic */ void lambda$onClick$2(TLObject r1, TLRPC.TL_error r2) {
            if (r1 == null) goto L5;
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getMessagesController().processUpdates((TLRPC.Updates) r1, false);
            return;
        }

        @Override // android.view.View.OnClickListener
        @SuppressLint({"SourceLockedOrientationActivity"})
        public void onClick(View r14) {
            GroupCallActivity r0 = this.this$0;
            if (r0.call != null) goto L5;
            return;
        L5:
            if (GroupCallActivity.m15139$$Nest$fgetmuteButtonState(r0) == 3) goto L128;
            int r2 = 6;
            int r3 = 0;
            if (this.this$0.isRtmpStream() == true) goto L10;
        L39:
            int r02 = GroupCallActivity.m15139$$Nest$fgetmuteButtonState(this.this$0);
            GroupCallActivity r5 = this.this$0;
            if (r02 != 5) goto L47;
            if (GroupCallActivity.m15214$$Nest$fgetstartingGroupCall(r5) == true) goto L129;
            r14.performHapticFeedback(3, 2);     // Catch: Exception -> L114
        L45:
            GroupCallActivity.m15260$$Nest$fputstartingGroupCall(this.this$0, true);
            TL_phone.startScheduledGroupCall r142 = new TL_phone.startScheduledGroupCall();
            r142.call = this.this$0.call.getInputGroupCall();
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getConnectionsManager().sendRequest(r142, new GroupCallActivity$19$$ExternalSyntheticLambda1(this));
            return;
        L129:
            return;
        L47:
            if (GroupCallActivity.m15139$$Nest$fgetmuteButtonState(r5) == 7) goto L105;
            if (GroupCallActivity.m15139$$Nest$fgetmuteButtonState(this.this$0) == 6) goto L105;
            if (VoIPService.getSharedInstance() != null) goto L54;
            return;
        L54:
            if (GroupCallActivity.m15278$$Nest$misStillConnecting(this.this$0) == false) goto L57;
            return;
        L57:
            if (GroupCallActivity.m15139$$Nest$fgetmuteButtonState(this.this$0) == 2) goto L76;
            if (GroupCallActivity.m15139$$Nest$fgetmuteButtonState(this.this$0) == 4) goto L76;
            int r143 = GroupCallActivity.m15139$$Nest$fgetmuteButtonState(this.this$0);
            GroupCallActivity r03 = this.this$0;
            if (r143 == 0) goto L64;
            GroupCallActivity.m15290$$Nest$mupdateMuteButton(r03, 0, true);
            VoIPService.getSharedInstance().setMicMute(true, false, true);
            GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).performHapticFeedback(3, 2);     // Catch: Exception -> L116
            return;
        L64:
            if (r03.getParentActivity() != null) goto L66;
        L69:
            GroupCallActivity.m15290$$Nest$mupdateMuteButton(this.this$0, 1, true);
            VoIPService.getSharedInstance().setMicMute(false, false, true);
            GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).performHapticFeedback(3, 2);     // Catch: Exception -> L116
            return;
        L66:
            if (this.this$0.getParentActivity().checkSelfPermission("android.permission.RECORD_AUDIO") == 0) goto L69;
            PermissionRequest.ensurePermission(R.raw.permission_request_microphone, R.string.VoipNeedMicPermissionWithHint, "android.permission.RECORD_AUDIO", new GroupCallActivity$19$$ExternalSyntheticLambda3());
            return;
        L133:
            return;
        L76:
            if (this.this$0.isConference() == false) goto L79;
            return;
        L79:
            if (GroupCallActivity.m15169$$Nest$fgetplayingHandAnimation(this.this$0) == true) goto L132;
            GroupCallActivity.m15244$$Nest$fputplayingHandAnimation(this.this$0, true);
            AndroidUtilities.shakeView(GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).getTextView());
            r14.performHapticFeedback(3, 2);     // Catch: Exception -> L115
        L83:
            int r144 = Utilities.random.nextInt(100);
            int r1 = 120;
            if (r144 < 32) goto L96;
            r3 = 240;
            if (r144 >= 64) goto L89;
        L88:
            int r12 = r3;
            r3 = r1;
            r1 = r12;
            goto L96
        L89:
            r1 = MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN;
            if (r144 < 97) goto L96;
            r3 = 540;
            if (r144 == 98) goto L88;
            r1 = 720;
        L96:
            GroupCallActivity.m15115$$Nest$fgethandDrawables(this.this$0).setCustomEndFrame(r1);
            GroupCallActivity.m15115$$Nest$fgethandDrawables(this.this$0).setOnFinishCallback(this.finishRunnable, r1 - 1);
            GroupCallActivity.m15138$$Nest$fgetmuteButtonIcon(this.this$0).setAnimation(GroupCallActivity.m15115$$Nest$fgethandDrawables(this.this$0));
            GroupCallActivity.m15115$$Nest$fgethandDrawables(this.this$0).setCurrentFrame(r3);
            GroupCallActivity.m15138$$Nest$fgetmuteButtonIcon(this.this$0).playAnimation();
            if (GroupCallActivity.m15139$$Nest$fgetmuteButtonState(this.this$0) != 2) goto L126;
            GroupCallActivity r145 = this.this$0;
            long r04 = MessageObject.getPeerId(r145.call.participants.get(MessageObject.getPeerId(GroupCallActivity.m15206$$Nest$fgetselfPeer(r145))).peer);
            boolean r146 = DialogObject.isUserDialog(r04);
            GroupCallActivity r32 = this.this$0;
            if (r146 == false) goto L102;
            TLObject r147 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(r32).getMessagesController().getUser(Long.valueOf(r04));
        L103:
            VoIPService.getSharedInstance().editCallMember(r147, null, null, null, Boolean.TRUE, null);
            GroupCallActivity.m15290$$Nest$mupdateMuteButton(this.this$0, 4, true);
            return;
        L102:
            r147 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(r32).getMessagesController().getChat(Long.valueOf(-r04));
            goto L103
        L126:
            return;
        L132:
            return;
        L105:
            if (GroupCallActivity.m15139$$Nest$fgetmuteButtonState(this.this$0) == 6) goto L107;
        L109:
            TL_phone.toggleGroupCallStartSubscription r148 = new TL_phone.toggleGroupCallStartSubscription();
            r148.call = this.this$0.call.getInputGroupCall();
            GroupCallActivity r05 = this.this$0;
            TLRPC.GroupCall r13 = r05.call.call;
            boolean r33 = !r13.schedule_start_subscribed;
            r13.schedule_start_subscribed = r33;
            r148.subscribed = r33;
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(r05).getConnectionsManager().sendRequest(r148, new GroupCallActivity$19$$ExternalSyntheticLambda2(this));
            GroupCallActivity r132 = this.this$0;
            if (r132.call.call.schedule_start_subscribed == false) goto L112;
            r2 = 7;
        L112:
            GroupCallActivity.m15290$$Nest$mupdateMuteButton(r132, r2, true);
            return;
        L107:
            if (GroupCallActivity.m15185$$Nest$fgetreminderHintView(this.this$0) == null) goto L109;
            GroupCallActivity.m15185$$Nest$fgetreminderHintView(this.this$0).hide();
            goto L109
        L10:
            if (this.this$0.call.isScheduled() == true) goto L39;
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0) == null) goto L25;
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).inFullscreenMode == false) goto L25;
            if (AndroidUtilities.isTablet() == false) goto L18;
        L19:
            this.this$0.fullscreenFor(null);
            if (GroupCallActivity.isLandscapeMode == false) goto L22;
            AndroidUtilities.runOnUIThread(new GroupCallActivity$19$$ExternalSyntheticLambda0(this), 200);
        L22:
            GroupCallActivity.m15167$$Nest$fgetparentActivity(this.this$0).setRequestedOrientation(-1);
            return;
        L18:
            if (GroupCallActivity.isLandscapeMode == this.this$0.isRtmpLandscapeMode()) goto L19;
        L25:
            if (this.this$0.visibleVideoParticipants.isEmpty() == true) goto L123;
            ChatObject.VideoParticipant r149 = this.this$0.visibleVideoParticipants.get(0);
            if (AndroidUtilities.isTablet() == false) goto L31;
            this.this$0.fullscreenFor(r149);
            return;
        L31:
            if (GroupCallActivity.isLandscapeMode != this.this$0.isRtmpLandscapeMode()) goto L33;
            this.this$0.fullscreenFor(r149);
        L33:
            boolean r1410 = this.this$0.isRtmpLandscapeMode();
            GroupCallActivity r133 = this.this$0;
            if (r1410 == false) goto L37;
            GroupCallActivity.m15167$$Nest$fgetparentActivity(r133).setRequestedOrientation(6);
            return;
        L37:
            GroupCallActivity.m15167$$Nest$fgetparentActivity(r133).setRequestedOrientation(1);
            return;
        L123:
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ GroupCallActivity this$0;

        /* renamed from: $r8$lambda$Cd0iZ31MGfzhtMcp-h8DJW2we14, reason: not valid java name */
        public static /* synthetic */ void m15295$r8$lambda$Cd0iZ31MGfzhtMcph8DJW2we14(AnonymousClass2 r0, int[] r1) {
            r0.lambda$run$0(r1);
        }

        /* renamed from: $r8$lambda$T6Q5kiFPCH-6qBX5omoQfXuVwp4, reason: not valid java name */
        public static /* synthetic */ void m15296$r8$lambda$T6Q5kiFPCH6qBX5omoQfXuVwp4(AnonymousClass2 r0, VoIPTextureView r1) {
            r0.lambda$run$1(r1);
        }

        public AnonymousClass2(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$run$0(int[] r1) {
            GroupCallActivity.m15218$$Nest$fgettextureLightningView(this.this$0).setNewColors(r1);
        }

        private /* synthetic */ void lambda$run$1(VoIPTextureView r3) {
            Bitmap r32 = r3.blurRenderer.getBitmap(100, 100);     // Catch: Exception -> L7
            if (r32 != null) goto L5;
            return;
        L5:
            AndroidUtilities.runOnUIThread(new GroupCallActivity$2$$ExternalSyntheticLambda1(this, DominantColors.getColorsSync(false, r32, true)));     // Catch: Exception -> L7
            return;
        L7:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // java.lang.Runnable
        public void run() {
        L20:
            e = move-exception;
            FileLog.e(e);
            return;
        L3:
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0) != null) goto L5;
            return;
        L5:
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).inFullscreenMode == true) goto L26;
            GroupCallGridCell r0 = GroupCallActivity.m15272$$Nest$mfindGroupCallGridCell(this.this$0);     // Catch: Exception -> L20
            if (r0 != null) goto L10;
            return;
        L10:
            if (r0.isAttachedToWindow() == false) goto L28;
            GroupCallMiniTextureView r02 = r0.getRenderer();     // Catch: Exception -> L20
            if (r02 == null) goto L29;
            VoIPTextureView r03 = r02.textureView;     // Catch: Exception -> L20
            if (r03 == null) goto L30;
            GroupCallActivity.updateTextureLightningQueue.postRunnable(new GroupCallActivity$2$$ExternalSyntheticLambda0(this, r03));     // Catch: Exception -> L20
            return;
        L30:
            return;
        L29:
            return;
        L28:
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$20, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass20 extends AudioPlayerAlert.ClippingTextViewSwitcher {
        final /* synthetic */ GroupCallActivity this$0;
        final /* synthetic */ Activity val$context;

        public static /* synthetic */ void $r8$lambda$QNQYsaQkKwVgRXvljUx3Tjj9bng(AnonymousClass20 r0, TextView r1, View r2) {
            r0.lambda$createTextView$0(r1, r2);
        }

        public AnonymousClass20(GroupCallActivity r1, Context r2, Activity r3) {
            this.this$0 = r1;
            this.val$context = r3;
            super(r2);
        }

        private /* synthetic */ void lambda$createTextView$0(TextView r1, View r2) {
            GroupCallActivity r0 = this.this$0;
            ChatObject.Call r22 = r0.call;
            if (r22 != null) goto L5;
            return;
        L5:
            if (r22.recording == false) goto L9;
            GroupCallActivity.m15285$$Nest$mshowRecordHint(r0, r1);
            return;
        }

        @Override // org.telegram.ui.Components.AudioPlayerAlert.ClippingTextViewSwitcher
        public TextView createTextView() {
            TextView r0 = new TextView(this.val$context);
            r0.setTextColor(Theme.getColor(Theme.key_voipgroup_actionBarItems));
            r0.setTextSize(1, 20.0f);
            r0.setTypeface(AndroidUtilities.bold());
            r0.setGravity(51);
            r0.setSingleLine(true);
            r0.setEllipsize(TextUtils.TruncateAt.END);
            r0.setOnClickListener(new GroupCallActivity$20$$ExternalSyntheticLambda0(this, r0));
            return r0;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$21, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass21 extends View {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass21(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(View.MeasureSpec.getSize(r1), ActionBar.getCurrentActionBarHeight());
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$22, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass22 extends TextView {
        private RectF rect;
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass22(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new RectF();
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r5) {
            this.rect.set(0.0f, 0.0f, getWidth(), getHeight());
            r5.drawRoundRect(this.rect, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), GroupCallActivity.m15132$$Nest$fgetliveLabelPaint(this.this$0));
            super.onDraw(r5);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$23, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass23 extends UndoView {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass23(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.UndoView
        public void showWithAction(long r2, int r4, Object r5, Object r6, Runnable r7, Runnable r8) {
            if (this.this$0.previewDialog == null) goto L5;
            return;
        L5:
            super.showWithAction(r2, r4, r5, r6, r7, r8);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$24, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass24 extends RecyclerListView {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass24(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r7, View r8, long r9) {
            GroupCallFullscreenAdapter.GroupCallUserCell r0 = (GroupCallFullscreenAdapter.GroupCallUserCell) r8;
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).isAnimating() == true) goto L8;
            if (GroupCallActivity.m15112$$Nest$fgetfullscreenListItemAnimator(this.this$0).isRunning() == true) goto L8;
            r0.setAlpha(1.0f);
            r0.setTranslationX(0.0f);
            r0.setTranslationY(0.0f);
        L8:
            if (r0.isRemoving(this.this$0.fullscreenUsersListView) == false) goto L14;
            if (r0.getRenderer() == null) goto L14;
            return true;
        L14:
            if (r0.getTranslationY() == 0.0f) goto L22;
            if (r0.getRenderer() == null) goto L22;
            if (r0.getRenderer().primaryView == null) goto L22;
            float r02 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getTop() - getTop();
            float r4 = GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode;
            r7.save();
            float r2 = 1.0f - r4;
            r7.clipRect(0.0f, r02 * r2, getMeasuredWidth(), ((GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getMeasuredHeight() + r02) * r2) + (getMeasuredHeight() * r4));
            boolean r6 = super.drawChild(r7, r8, r9);
            r7.restore();
            return r6;
        L22:
            return super.drawChild(r7, r8, r9);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$25, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass25 extends DefaultItemAnimator {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass25(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).invalidate();
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).invalidate();
            GroupCallActivity.access$1900(this.this$0).invalidate();
            GroupCallActivity.m15289$$Nest$mupdateLayout(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$26, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass26 extends RecyclerView.OnScrollListener {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass26(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            GroupCallActivity.access$2000(this.this$0).invalidate();
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$27, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass27 extends RecyclerView.ItemDecoration {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass27(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            r3.getChildAdapterPosition(r2);
            if (GroupCallActivity.isLandscapeMode == true) goto L6;
            r1.set(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            return;
        L6:
            r1.set(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f));
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$28, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass28 extends GroupCallRenderersContainer {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass28(GroupCallActivity r1, Context r2, RecyclerView r3, RecyclerView r4, ArrayList r5, ChatObject.Call r6, GroupCallActivity r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Components.voip.GroupCallRenderersContainer
        public boolean canHideUI() {
            if (super.canHideUI() == true) goto L5;
            return false;
        L5:
            if (this.this$0.previewDialog != null) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.Components.voip.GroupCallRenderersContainer, android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != GroupCallActivity.m15203$$Nest$fgetscrimRenderer(this.this$0)) goto L7;
            return true;
        L7:
            return super.drawChild(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.voip.GroupCallRenderersContainer
        public void onBackPressed() {
            this.this$0.onBackPressed();
        }

        @Override // org.telegram.ui.Components.voip.GroupCallRenderersContainer
        public void onFullScreenModeChanged(boolean r7) {
            GroupCallActivity.m15235$$Nest$fputdelayedGroupCallUpdated(this.this$0, r7);
            int r2 = 0;
            if (GroupCallActivity.isTabletMode == false) goto L9;
            if (r7 == false) goto L6;
            return;
        L6:
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).inFullscreenMode == false) goto L42;
            GroupCallActivity r6 = this.this$0;
            r6.tabletGridAdapter.setVisibility(r6.tabletVideoGridView, false, true);
            return;
        L42:
            return;
        L9:
            GroupCallActivity r0 = this.this$0;
            if (r7 == false) goto L20;
            GroupCallActivity.m15222$$Nest$fgetundoView(r0)[0].hide(false, 1);
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).undoView[0].hide(false, 2);
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).inFullscreenMode == true) goto L16;
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).setVisibility(0);
            GroupCallActivity.m15072$$Nest$fgetactionBar(this.this$0).setVisibility(0);
            if (GroupCallActivity.m15227$$Nest$fgetwatchersView(this.this$0) == null) goto L16;
            GroupCallActivity.m15227$$Nest$fgetwatchersView(this.this$0).setVisibility(0);
        L16:
            GroupCallActivity.m15291$$Nest$mupdateState(this.this$0, true, false);
            GroupCallActivity.m15094$$Nest$fgetbuttonsContainer(this.this$0).requestLayout();
            int r02 = this.this$0.fullscreenUsersListView.getVisibility();
            GroupCallActivity r4 = this.this$0;
            if (r02 == 0) goto L19;
            r4.fullscreenUsersListView.setVisibility(0);
            GroupCallActivity r03 = this.this$0;
            r03.fullscreenAdapter.setVisibility(r03.fullscreenUsersListView, true);
            GroupCallActivity r04 = this.this$0;
            r04.fullscreenAdapter.update(false, r04.fullscreenUsersListView);
        L32:
            View r05 = GroupCallActivity.m15093$$Nest$fgetbuttonsBackgroundGradientView2(this.this$0);
            if (r7 == true) goto L36;
            r2 = 8;
        L36:
            r05.setVisibility(r2);
            if (GroupCallActivity.m15104$$Nest$fgetdelayedGroupCallUpdated(this.this$0) == true) goto L43;
            GroupCallActivity.m15264$$Nest$mapplyCallParticipantUpdates(this.this$0, true);
            return;
        L43:
            return;
        L19:
            r4.fullscreenAdapter.setVisibility(r4.fullscreenUsersListView, true);
            GroupCallActivity.m15264$$Nest$mapplyCallParticipantUpdates(this.this$0, true);
            goto L32
        L20:
            boolean r06 = GroupCallActivity.m15188$$Nest$fgetrenderersContainer(r0).inFullscreenMode;
            GroupCallActivity r42 = this.this$0;
            if (r06 == true) goto L23;
            r42.fullscreenUsersListView.setVisibility(8);
            GroupCallActivity r07 = this.this$0;
            r07.fullscreenAdapter.setVisibility(r07.fullscreenUsersListView, false);
        L27:
            if (this.this$0.fullscreenUsersListView.getVisibility() != 0) goto L32;
            int r08 = 0;
        L30:
            if (r08 >= this.this$0.fullscreenUsersListView.getChildCount()) goto L32;
            View r43 = this.this$0.fullscreenUsersListView.getChildAt(r08);
            r43.setAlpha(1.0f);
            r43.setScaleX(1.0f);
            r43.setScaleY(1.0f);
            r43.setTranslationX(0.0f);
            r43.setTranslationY(0.0f);
            ((GroupCallFullscreenAdapter.GroupCallUserCell) r43).setProgressToFullscreen(GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode);
            r08 = r08 + 1;
            goto L30
        L23:
            GroupCallActivity.m15072$$Nest$fgetactionBar(r42).setVisibility(8);
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).setVisibility(8);
            if (GroupCallActivity.m15227$$Nest$fgetwatchersView(this.this$0) == null) goto L27;
            GroupCallActivity.m15227$$Nest$fgetwatchersView(this.this$0).setVisibility(8);
            goto L27
        }

        @Override // org.telegram.ui.Components.voip.GroupCallRenderersContainer
        public void onUiVisibilityChanged() {
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0) != null) goto L5;
            return;
        L5:
            GroupCallActivity.m15079$$Nest$fgetanimatorHideButtons(this.this$0).setValue(!GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).isUiVisible(), true);
        }

        @Override // org.telegram.ui.Components.voip.GroupCallRenderersContainer
        public void update() {
            super.update();
            float r0 = GroupCallActivity.m15099$$Nest$fgetcolorProgress(this.this$0);
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0) != null) goto L5;
            float r1 = 0.0f;
        L6:
            float r02 = Math.max(r0, r1);
            GroupCallActivity.access$2102(this.this$0, AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_actionBarUnscrolled), Theme.getColor(Theme.key_voipgroup_actionBar), r02, 1.0f));
            GroupCallActivity.access$2200(this.this$0).invalidate();
            GroupCallActivity r5 = this.this$0;
            GroupCallActivity.m15282$$Nest$msetColorProgress(r5, GroupCallActivity.m15099$$Nest$fgetcolorProgress(r5));
            return;
        L5:
            r1 = GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$29, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass29 extends AvatarPreviewPagerIndicator {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass29(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.AvatarPreviewPagerIndicator, org.telegram.ui.Components.ProfileGalleryView.Callback
        public void onPhotosLoaded() {
            super.onPhotosLoaded();
            long r0 = GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).getDialogId();
            if (r0 <= 0) goto L6;
            TLRPC.User r02 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getMessagesController().getUser(Long.valueOf(r0));
            ImageLocation r1 = ImageLocation.getForUserOrChat(GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getCurrentAccount(), r02, 0);
            ImageLocation r03 = ImageLocation.getForUserOrChat(GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getCurrentAccount(), r02, 1);
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).initIfEmpty(null, r1, r03, false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 implements BottomSheet.BottomSheetDelegateInterface {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass3(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegateInterface
        public boolean canDismiss() {
            return true;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegateInterface
        public void onOpenAnimationEnd() {
            VoIPService r0 = VoIPService.getSharedInstance();
            if (r0 == null) goto L8;
            CountDownLatch r02 = r0.getGroupCallBottomSheetLatch();
            if (r02 == null) goto L8;
            r02.countDown();
        L8:
            if (GroupCallActivity.m15139$$Nest$fgetmuteButtonState(this.this$0) != 6) goto L11;
            GroupCallActivity.m15286$$Nest$mshowReminderHint(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$30, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass30 extends ProfileGalleryView {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass30(GroupCallActivity r1, Context r2, ActionBar r3, RecyclerListView r4, ProfileGalleryView.Callback r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            GroupCallActivity.access$2300(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$31, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass31 extends FrameLayout {
        final Path path;
        final Rect rect;
        final RectF rectF;
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass31(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new Rect();
            this.rectF = new RectF();
            this.path = new Path();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r12) {
            if (GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0) != 1.0f) goto L5;
        L16:
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).setAlpha(GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0));
            this.path.reset();
            this.rectF.set(0.0f, 0.0f, getMeasuredHeight(), getMeasuredWidth());
            this.path.addRoundRect(this.rectF, new float[]{AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), 0.0f, 0.0f, 0.0f, 0.0f}, Path.Direction.CCW);
            r12.save();
            r12.clipPath(this.path);
            View r0 = GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).findVideoActiveView();
            if (r0 != null) goto L19;
        L31:
            super.dispatchDraw(r12);
            r12.restore();
            return;
        L19:
            if (GroupCallActivity.m15203$$Nest$fgetscrimRenderer(this.this$0) == null) goto L31;
            if (GroupCallActivity.m15203$$Nest$fgetscrimRenderer(this.this$0).isAttached() == false) goto L31;
            if (this.this$0.drawingForBlur == true) goto L31;
            r12.save();
            this.rect.setEmpty();
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).getChildVisibleRect(r0, this.rect, null);
            int r02 = this.rect.left;
            int r1 = -GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).getMeasuredWidth();
            GroupCallActivity r3 = this.this$0;
            if (r02 >= r1) goto L28;
            r02 = r02 + (GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(r3).getMeasuredWidth() * 2);
        L30:
            r12.translate(r02, 0.0f);
            GroupCallActivity.m15203$$Nest$fgetscrimRenderer(this.this$0).draw(r12);
            r12.restore();
            goto L31
        L28:
            if (r02 <= GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(r3).getMeasuredWidth()) goto L30;
            r02 = r02 - (GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).getMeasuredWidth() * 2);
            goto L30
        L5:
            if (GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0) == null) goto L10;
            if (GroupCallActivity.m15116$$Nest$fgethasScrimAnchorView(this.this$0) == false) goto L10;
            r12.save();
            float r6 = ExteraConfig.getAvatarCorners(GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getAvatarImageView().getMeasuredHeight(), true) * (getMeasuredHeight() / GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getAvatarImageView().getMeasuredHeight());
            int r03 = (int) (((1.0f - GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0)) * r6) + (AndroidUtilities.dp(13.0f) * GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0)));
            int r13 = (int) (r6 * (1.0f - GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0)));
            GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getAvatarWavesDrawable().draw(r12, GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getAvatarImageView().getMeasuredHeight() / 2, GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getAvatarImageView().getMeasuredHeight() / 2, this);
            GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getAvatarImageView().getImageReceiver().setImageCoords(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getAvatarImageView().setRoundRadius(r03, r03, r13, r13);
            GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getAvatarImageView().getImageReceiver().draw(r12);
            GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getAvatarImageView().setRoundRadius(ExteraConfig.getAvatarCorners(GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getAvatarImageView().getMeasuredHeight(), true));
            r12.restore();
        L10:
            if (GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0) == null) goto L16;
            if (GroupCallActivity.m15203$$Nest$fgetscrimRenderer(this.this$0) != null) goto L16;
            if (GroupCallActivity.m15174$$Nest$fgetpreviewTextureTransitionEnabled(this.this$0) == false) goto L16;
            r12.save();
            float r62 = ExteraConfig.getAvatarCorners(GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).getAvatarImageView().getMeasuredHeight(), true) * (getMeasuredHeight() / GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).getAvatarImageView().getMeasuredHeight());
            int r04 = (int) (((1.0f - GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0)) * r62) + (AndroidUtilities.dp(13.0f) * GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0)));
            int r14 = (int) (r62 * (1.0f - GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0)));
            GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).getAvatarImageView().getImageReceiver().setImageCoords(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).getAvatarImageView().setRoundRadius(r04, r04, r14, r14);
            GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).getAvatarImageView().getImageReceiver().draw(r12);
            GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).getAvatarImageView().setRoundRadius(ExteraConfig.getAvatarCorners(GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).getAvatarImageView().getMeasuredHeight(), true));
            r12.restore();
            goto L16
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            GroupCallActivity.access$2400(this.this$0).invalidate();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            int r32 = Math.min(View.MeasureSpec.getSize(r3), View.MeasureSpec.getSize(r4));
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r32, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r32 + getPaddingBottom(), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$32, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass32 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass32(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r1) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r1, float r2, int r3) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r2) {
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).getRealPosition(r2);
            GroupCallActivity.m15084$$Nest$fgetavatarPagerIndicator(this.this$0).saveCurrentPageProgress();
            GroupCallActivity.m15084$$Nest$fgetavatarPagerIndicator(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$33, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass33 extends View {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass33(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setAlpha(float r2) {
            if (getAlpha() == r2) goto L6;
            super.setAlpha(r2);
            GroupCallActivity.m15267$$Nest$mcheckContentOverlayed(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$34, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass34 implements GroupCallMessagesListView.Delegate {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass34(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.conference.message.GroupCallMessagesListView.Delegate
        public void showReaction(GroupCallMessageCell r13, ReactionsLayoutInBubble.VisibleReaction r14) {
            ReactionsEffectOverlay r0 = new ReactionsEffectOverlay(this.this$0.getContext(), null, GroupCallActivity.m15181$$Nest$fgetreactionsContainerLayout(this.this$0), r13, null, 0.0f, 0.0f, r14, GroupCallActivity.access$2500(this.this$0), 1, false);
            ReactionsEffectOverlay.currentOverlay = r0;
            r0.windowView.setTag(R.id.parent_tag, 1);
            this.this$0.container.addView(r0.windowView);
            r0.started = true;
            r0.startTime = System.currentTimeMillis();
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$35, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass35 implements GroupCallMessageCell.Delegate {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass35(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        private void openSenderProfile(GroupCallMessage r8) {
            BaseFragment r0 = LaunchActivity.getLastFragment();
            if (r0 != null) goto L6;
            return;
        L6:
            if ((r0 instanceof ProfileActivity) == true) goto L8;
        L11:
            int r1 = GroupCallActivity.m15266$$Nest$mcalculateScrollTopOffset(this.this$0);
            Bundle r2 = new Bundle();
            long r3 = r8.fromId;
            if (r3 <= 0) goto L14;
            r2.putLong("user_id", r3);
        L15:
            boolean r32 = true;
            if (r8.fromId != GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getUserConfig().getClientUserId()) goto L18;
            r2.putBoolean("my_profile", true);
        L18:
            ProfileActivity r82 = new ProfileActivity(r2);
            if (r1 > 0) goto L21;
        L24:
            r0.presentFragment(r82, false, r32);
            this.this$0.dismiss();
            return;
        L21:
            if (r1 == Integer.MAX_VALUE) goto L24;
            r32 = false;
            goto L24
        L14:
            r2.putLong("chat_id", -r3);
            goto L15
        L8:
            if (((ProfileActivity) r0).getDialogId() != r8.fromId) goto L11;
            this.this$0.dismiss();
        }

        @Override // org.telegram.ui.Components.conference.message.GroupCallMessageCell.Delegate
        public void didClickAvatar(GroupCallMessageCell r1, GroupCallMessage r2, float r3, float r4) {
            openSenderProfile(r2);
        }

        @Override // org.telegram.ui.Components.conference.message.GroupCallMessageCell.Delegate
        public void didClickSenderName(GroupCallMessageCell r1, GroupCallMessage r2) {
            openSenderProfile(r2);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$36, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass36 extends EditTextEmoji {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass36(GroupCallActivity r1, Context r2, SizeNotifierFrameLayout r3, BaseFragment r4, int r5, boolean r6, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public boolean allowSearch() {
            return true;
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void createEmojiView() {
            super.createEmojiView();
            EmojiView r0 = getEmojiView();
            if (r0 == null) goto L6;
            r0.shouldLightenBackground = false;
            r0.fixBottomTabContainerTranslation = false;
            r0.setShouldDrawBackground(false);
            r0.setBottomInset(GroupCallActivity.access$2700(this.this$0).getPaddingBottom());
            return;
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r4, View r5, long r6) {
            if (r5 != getEditText()) goto L7;
            r4.save();
            GroupCallActivity.m15097$$Nest$fgetcallMessageEnterView(this.this$0).getEditText().setTranslationY(r5.getMeasuredHeight() - GroupCallActivity.m15080$$Nest$fgetanimatorMessageInputHeight(this.this$0).getFactor());
            boolean r3 = super.drawChild(r4, r5, r6);
            r4.restore();
            return r3;
        L7:
            return super.drawChild(r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void onEmojiKeyboardUpdate() {
            super.onEmojiKeyboardUpdate();
            if (isPopupShowing() == false) goto L6;
            int r0 = Math.max(0, getEmojiPadding());
        L9:
            GroupCallActivity r2 = this.this$0;
            if (r0 <= 0) goto L13;
            GroupCallActivity.m15228$$Nest$fgetwindowInsetsStateHolder(r2).requestInAppKeyboardHeight(r0);
            return;
        L13:
            GroupCallActivity.m15228$$Nest$fgetwindowInsetsStateHolder(r2).resetInAppKeyboardHeight(false);
            return;
        L6:
            if (isWaitingForKeyboardOpen() == false) goto L8;
            r0 = Math.max(0, getKeyboardHeight());
            goto L9
        L8:
            r0 = 0;
            goto L9
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            float r12 = GroupCallActivity.m15080$$Nest$fgetanimatorMessageInputHeight(this.this$0).getFactor();
            GroupCallActivity r22 = this.this$0;
            if (r12 != 0.0f) goto L6;
            GroupCallActivity.m15080$$Nest$fgetanimatorMessageInputHeight(r22).forceFactor(getMeasuredHeight());
            return;
        L6:
            GroupCallActivity.m15080$$Nest$fgetanimatorMessageInputHeight(r22).animateTo(getMeasuredHeight());
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void updatedEmojiExpanded() {
            super.updatedEmojiExpanded();
            GroupCallActivity.access$2600(this.this$0).requestApplyInsets();
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$37, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass37 implements TextWatcher {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass37(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r4) {
            GroupCallActivity.m15081$$Nest$fgetanimatorMessageIsEmpty(this.this$0).setValue(TextUtils.isEmpty(r4), true);
            int r42 = Character.codePointCount(r4, 0, r4.length());
            int r0 = GroupCallActivity.m15134$$Nest$fgetmaxGroupCallMessageLength(this.this$0);
            if ((r42 + 25) <= r0) goto L5;
            String r1 = _UrlKt.FRAGMENT_ENCODE_SET + (r0 - r42);
        L6:
            GroupCallActivity.m15126$$Nest$fgetlimitTextView(this.this$0).cancelAnimation();
            GroupCallActivity.m15126$$Nest$fgetlimitTextView(this.this$0).setText(r1);
            AnimatedTextView r3 = GroupCallActivity.m15126$$Nest$fgetlimitTextView(this.this$0);
            if (r42 < r0) goto L9;
            int r12 = -1280137;
        L10:
            r3.setTextColor(r12);
            if (r42 <= r0) goto L14;
            BotWebViewVibrationEffect.APP_ERROR.vibrate();
            return;
        L14:
            return;
        L9:
            r12 = -1;
            goto L10
        L5:
            r1 = null;
            goto L6
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$38, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass38 extends FrameLayout {
        private final Paint backgroundPaint;
        final /* synthetic */ GroupCallActivity this$0;
        private final RectF tmpRect;
        private final RectF tmpRect2;
        private final RectF tmpRect3;

        public AnonymousClass38(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.tmpRect = new RectF();
            this.tmpRect2 = new RectF();
            this.tmpRect3 = new RectF();
            this.backgroundPaint = new Paint(1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            this.tmpRect.set(0.0f, (GroupCallActivity.m15095$$Nest$fgetcallMessageEnterContainer(this.this$0).getY() + GroupCallActivity.m15095$$Nest$fgetcallMessageEnterContainer(this.this$0).getMeasuredHeight()) - GroupCallActivity.m15080$$Nest$fgetanimatorMessageInputHeight(this.this$0).getFactor(), getMeasuredWidth(), getMeasuredHeight());
            this.tmpRect2.set(0.0f, GroupCallActivity.m15095$$Nest$fgetcallMessageEnterContainer(this.this$0).getY() + GroupCallActivity.m15095$$Nest$fgetcallMessageEnterContainer(this.this$0).getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
            this.tmpRect3.set(0.0f, (GroupCallActivity.m15095$$Nest$fgetcallMessageEnterContainer(this.this$0).getY() + GroupCallActivity.m15095$$Nest$fgetcallMessageEnterContainer(this.this$0).getMeasuredHeight()) - GroupCallActivity.m15080$$Nest$fgetanimatorMessageInputHeight(this.this$0).getFactor(), getMeasuredWidth(), GroupCallActivity.m15095$$Nest$fgetcallMessageEnterContainer(this.this$0).getY() + GroupCallActivity.m15095$$Nest$fgetcallMessageEnterContainer(this.this$0).getMeasuredHeight());
            if (Build.VERSION.SDK_INT >= 29) goto L5;
        L9:
            this.backgroundPaint.setColor(-14933463);
            r7.drawRect(this.tmpRect3, this.backgroundPaint);
            this.backgroundPaint.setColor(ColorUtils.compositeColors(234881023, -14933463));
            r7.drawRect(this.tmpRect2, this.backgroundPaint);
        L10:
            super.dispatchDraw(r7);
            return;
        L5:
            if (GroupCallActivity.m15186$$Nest$fgetrenderNodeBlur(this.this$0) == null) goto L9;
            if (r7.isHardwareAccelerated() == false) goto L9;
            this.backgroundPaint.setColor(-14933463);
            r7.drawRect(this.tmpRect, this.backgroundPaint);
            r7.save();
            r7.clipRect(this.tmpRect);
            r7.translate(-getX(), -getY());
            r7.scale(GroupCallActivity.m15187$$Nest$fgetrenderNodeBlurScale(this.this$0), GroupCallActivity.m15187$$Nest$fgetrenderNodeBlurScale(this.this$0));
            r7.drawRenderNode(GroupCallActivity.m15186$$Nest$fgetrenderNodeBlur(this.this$0));
            r7.restore();
            this.backgroundPaint.setColor(234881023);
            r7.drawRect(this.tmpRect2, this.backgroundPaint);
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$39, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass39 extends FrameLayout {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass39(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (getAlpha() > 0.95f) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends Paint {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass4(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.Paint
        public void setAlpha(int r1) {
            super.setAlpha(r1);
            if (GroupCallActivity.access$000(this.this$0) == null) goto L6;
            GroupCallActivity.access$100(this.this$0).invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$40, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass40 extends NumberPicker {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass40(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Hours", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$41, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass41 extends NumberPicker {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass41(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Minutes", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$42, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass42 extends TextView {
        final /* synthetic */ GroupCallActivity this$0;
        private final Paint tmpPaint;
        private final RectF tmpRectF;

        public AnonymousClass42(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.tmpRectF = new RectF();
            Paint r12 = new Paint(1);
            this.tmpPaint = r12;
            r12.setStyle(Paint.Style.FILL);
            r12.setColor(-16711936);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r5) {
            this.tmpRectF.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            r5.drawRoundRect(this.tmpRectF, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.tmpPaint);
            super.dispatchDraw(r5);
        }

        @Override // android.view.View
        public void draw(Canvas r1) {
            super.draw(r1);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r5) {
            this.tmpPaint.setColor(-16711936);
            this.tmpRectF.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            r5.drawRoundRect(this.tmpRectF, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.tmpPaint);
            super.onDraw(r5);
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$43, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass43 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass43(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            GroupCallActivity.m15251$$Nest$fputscheduleAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$44, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass44 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ GroupCallActivity this$0;
        final /* synthetic */ NumberPicker val$dayPicker;
        final /* synthetic */ NumberPicker val$hourPicker;
        final /* synthetic */ NumberPicker val$minutePicker;

        public AnonymousClass44(GroupCallActivity r1, Context r2, NumberPicker r3, NumberPicker r4, NumberPicker r5) {
            this.this$0 = r1;
            this.val$dayPicker = r3;
            this.val$hourPicker = r4;
            this.val$minutePicker = r5;
            super(r2);
            this.ignoreLayout = false;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            this.ignoreLayout = true;
            this.val$dayPicker.setItemCount(5);
            this.val$hourPicker.setItemCount(5);
            this.val$minutePicker.setItemCount(5);
            ViewGroup.LayoutParams r0 = this.val$dayPicker.getLayoutParams();
            r0.height = AndroidUtilities.dp(54.0f) * 5;
            ViewGroup.LayoutParams r02 = this.val$hourPicker.getLayoutParams();
            r02.height = AndroidUtilities.dp(54.0f) * 5;
            ViewGroup.LayoutParams r03 = this.val$minutePicker.getLayoutParams();
            r03.height = AndroidUtilities.dp(54.0f) * 5;
            this.ignoreLayout = false;
            super.onMeasure(r5, r6);
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$45, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass45 extends PinchToZoomHelper {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass45(GroupCallActivity r1, ViewGroup r2, ViewGroup r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.PinchToZoomHelper
        public void drawOverlays(Canvas r2, float r3, float r4, float r5, float r6, float r7) {
            if (r3 <= 0.0f) goto L6;
            float r42 = GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getX() + GroupCallActivity.access$2800(this.this$0).getX();
            float r52 = GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getY() + GroupCallActivity.access$2900(this.this$0).getY();
            RectF r62 = AndroidUtilities.rectTmp;
            r62.set(r42, r52, GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).getMeasuredWidth() + r42, GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).getMeasuredHeight() + r52);
            r2.saveLayerAlpha(r62, (int) (r3 * 255.0f), 31);
            r2.translate(r42, r52);
            GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).draw(r2);
            r2.restore();
            return;
        }

        @Override // org.telegram.ui.PinchToZoomHelper
        public void invalidateViews() {
            super.invalidateViews();
            int r0 = 0;
        L4:
            if (r0 >= GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).getChildCount()) goto L6;
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).getChildAt(r0).invalidate();
            r0 = r0 + 1;
            goto L4
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$46, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass46 implements PinchToZoomHelper.Callback {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass46(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PinchToZoomHelper.Callback
        public void onZoomFinished(MessageObject r1) {
            GroupCallActivity.access$3100(this.this$0).invalidate();
        }

        @Override // org.telegram.ui.PinchToZoomHelper.Callback
        public void onZoomStarted(MessageObject r4) {
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).cancelClickRunnables(true);
            this.this$0.pinchToZoomHelper.getPhotoImage().setRoundRadius(AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), 0, 0);
            GroupCallActivity.access$3000(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$47, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass47 extends PrivateVideoPreviewDialog {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass47(GroupCallActivity r1, Context r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.voip.PrivateVideoPreviewDialog
        public void onDismiss(boolean r4, boolean r5) {
            GroupCallActivity r0 = this.this$0;
            boolean r1 = r0.previewDialog.micEnabled;
            r0.previewDialog = null;
            VoIPService r02 = VoIPService.getSharedInstance();
            if (r5 == false) goto L11;
            if (r02 == null) goto L6;
            r02.setupCaptureDevice(r4, r1);
        L6:
            if (r4 == false) goto L9;
            if (r02 == null) goto L9;
            r02.setVideoState(false, 0);
        L9:
            GroupCallActivity.m15291$$Nest$mupdateState(this.this$0, true, false);
            this.this$0.call.sortParticipants();
            GroupCallActivity.m15264$$Nest$mapplyCallParticipantUpdates(this.this$0, true);
            GroupCallActivity.m15094$$Nest$fgetbuttonsContainer(this.this$0).requestLayout();
            return;
        L11:
            if (r02 == null) goto L14;
            r02.setVideoState(false, 0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$48, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass48 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallActivity this$0;
        final /* synthetic */ GroupCallMiniTextureView val$miniTextureView;

        public AnonymousClass48(GroupCallActivity r1, GroupCallMiniTextureView r2) {
            this.this$0 = r1;
            this.val$miniTextureView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$miniTextureView.getParent() == null) goto L6;
            GroupCallActivity.access$3200(this.this$0).removeView(this.val$miniTextureView);
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$49, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass49 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallActivity this$0;
        final /* synthetic */ GroupCallMiniTextureView val$miniTextureView;

        public AnonymousClass49(GroupCallActivity r1, GroupCallMiniTextureView r2) {
            this.this$0 = r1;
            this.val$miniTextureView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$miniTextureView.getParent() == null) goto L6;
            GroupCallActivity.access$3300(this.this$0).removeView(this.val$miniTextureView);
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends ActionBar {
        final /* synthetic */ GroupCallActivity this$0;
        final /* synthetic */ RecordStatusDrawable val$recordStatusDrawable;

        public AnonymousClass5(GroupCallActivity r1, Context r2, RecordStatusDrawable r3) {
            this.this$0 = r1;
            this.val$recordStatusDrawable = r3;
            super(r2);
        }

        @Override // org.telegram.ui.ActionBar.ActionBar, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            super.dispatchDraw(r4);
            if (getAdditionalSubtitleTextView().getVisibility() != 0) goto L6;
            r4.save();
            r4.translate(getSubtitleTextView().getLeft(), getSubtitleTextView().getY() - AndroidUtilities.dp(1.0f));
            this.val$recordStatusDrawable.setAlpha((int) (getAdditionalSubtitleTextView().getAlpha() * 255.0f));
            this.val$recordStatusDrawable.draw(r4);
            r4.restore();
            invalidate();
            return;
        }

        @Override // android.view.View
        public void setAlpha(float r2) {
            if (getAlpha() == r2) goto L6;
            super.setAlpha(r2);
            GroupCallActivity.access$200(this.this$0).invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$50, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass50 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ GroupCallActivity this$0;
        final /* synthetic */ boolean val$updateScroll;
        final /* synthetic */ ChatObject.VideoParticipant val$videoParticipant;

        public AnonymousClass50(GroupCallActivity r1, ChatObject.VideoParticipant r2, boolean r3) {
            this.this$0 = r1;
            this.val$videoParticipant = r2;
            this.val$updateScroll = r3;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getViewTreeObserver().removeOnPreDrawListener(this);
            GroupCallActivity r0 = this.this$0;
            r0.requestFullscreenListener = null;
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(r0).requestFullscreen(this.val$videoParticipant);
            boolean r02 = GroupCallActivity.m15104$$Nest$fgetdelayedGroupCallUpdated(this.this$0);
            GroupCallActivity r1 = this.this$0;
            if (r02 == false) goto L10;
            GroupCallActivity.m15235$$Nest$fputdelayedGroupCallUpdated(r1, false);
            GroupCallActivity.m15264$$Nest$mapplyCallParticipantUpdates(this.this$0, true);
            if (this.val$updateScroll == true) goto L7;
        L9:
            GroupCallActivity.m15235$$Nest$fputdelayedGroupCallUpdated(this.this$0, true);
        L11:
            GroupCallActivity.access$3400(this.this$0).requestLayout();
            return false;
        L7:
            if (this.val$videoParticipant == null) goto L9;
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).scrollToPosition(0);
            goto L9
        L10:
            GroupCallActivity.m15264$$Nest$mapplyCallParticipantUpdates(r1, true);
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$51, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass51 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ GroupCallActivity this$0;
        final /* synthetic */ ChatObject.VideoParticipant val$videoParticipant;

        public AnonymousClass51(GroupCallActivity r1, ChatObject.VideoParticipant r2) {
            this.this$0 = r1;
            this.val$videoParticipant = r2;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getViewTreeObserver().removeOnPreDrawListener(this);
            GroupCallActivity r0 = this.this$0;
            r0.requestFullscreenListener = null;
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(r0).requestFullscreen(this.val$videoParticipant);
            AndroidUtilities.updateVisibleRows(this.this$0.fullscreenUsersListView);
            GroupCallActivity.access$3500(this.this$0).requestLayout();
            return false;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$52, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass52 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass52(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getViewTreeObserver().removeOnPreDrawListener(this);
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).requestFullscreen(null);
            AndroidUtilities.updateVisibleRows(this.this$0.fullscreenUsersListView);
            GroupCallActivity.access$3600(this.this$0).requestLayout();
            return false;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$53, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass53 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass53(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getViewTreeObserver().removeOnPreDrawListener(this);
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).requestFullscreen(null);
            AndroidUtilities.updateVisibleRows(this.this$0.fullscreenUsersListView);
            GroupCallActivity.access$3700(this.this$0).requestLayout();
            return false;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$54, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass54 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass54(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            GroupCallActivity.m15194$$Nest$fgetscheduleTimeTextView(this.this$0).setVisibility(4);
            GroupCallActivity.m15192$$Nest$fgetscheduleStartAtTextView(this.this$0).setVisibility(4);
            GroupCallActivity.m15193$$Nest$fgetscheduleStartInTextView(this.this$0).setVisibility(4);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$55, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass55 extends ShareAlert {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass55(GroupCallActivity r1, Context r2, ChatActivity r3, ArrayList r4, String r5, String r6, boolean r7, String r8, String r9, boolean r10, boolean r11) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onSend(LongSparseArray<TLRPC.Dialog> r9, int r10, TLRPC.TL_forumTopic r11, boolean r12) {
            if (r12 == true) goto L4;
            return;
        L4:
            int r112 = r9.size();
            GroupCallActivity r8 = this.this$0;
            if (r112 != 1) goto L8;
            r8.getUndoView().showWithAction(r9.valueAt(0).id, 41, Integer.valueOf(r10));
            return;
        L8:
            r8.getUndoView().showWithAction(0, 41, Integer.valueOf(r10), Integer.valueOf(r9.size()), null, null);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$56, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass56 implements ShareAlert.ShareAlertDelegate {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass56(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ShareAlert.ShareAlertDelegate
        public boolean didCopy() {
            if (AndroidUtilities.shouldShowClipboardToast() == false) goto L7;
            this.this$0.getUndoView().showWithAction(0, 33, null, null, null, null);
            return true;
        L7:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$57, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass57 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass57(GroupCallActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            GroupCallActivity r22 = this.this$0;
            r22.subtitleYAnimator = null;
            SimpleTextView r23 = GroupCallActivity.m15072$$Nest$fgetactionBar(r22).getSubtitleTextView();
            if (this.val$show == false) goto L5;
            float r1 = 0.0f;
        L6:
            r23.setTranslationY(r1);
            return;
        L5:
            r1 = AndroidUtilities.dp(20.0f);
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$58, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass58 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass58(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            GroupCallActivity.m15229$$Nest$fputactionBarAnimation(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$59, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass59 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass59(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            GroupCallActivity.m15242$$Nest$fputliveLabelBgColorAnimator(this.this$0, null);
            Paint r32 = GroupCallActivity.m15132$$Nest$fgetliveLabelPaint(this.this$0);
            if (GroupCallActivity.m15102$$Nest$fgetcurrentCallState(this.this$0) != 3) goto L5;
            int r0 = -1163700;
        L6:
            r32.setColor(r0);
            GroupCallActivity.m15133$$Nest$fgetliveLabelTextView(this.this$0).invalidate();
            return;
        L5:
            r0 = -12761513;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ GroupCallActivity this$0;
        final /* synthetic */ Activity val$context;

        /* renamed from: org.telegram.ui.GroupCallActivity$6$1, reason: invalid class name */
        public class AnonymousClass1 extends GroupCallRecordAlert {
            final /* synthetic */ AnonymousClass6 this$1;

            public static /* synthetic */ boolean $r8$lambda$AnupSWGd5zEZYNWrLpcnwqxJ9RM(AlertDialog.Builder r0, TextView r1, int r2, KeyEvent r3) {
                AndroidUtilities.hideKeyboard(r1);
                r0.create().getButton(-1).callOnClick();
                return false;
            }

            public static /* synthetic */ void $r8$lambda$SMs916TcHUgpZ_fXrYQeQl1Azh0(AnonymousClass1 r0, AlertDialog r1, EditTextBoldCursor r2, DialogInterface r3) {
                r0.lambda$onStartRecord$1(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$YhdvPf2pEx0d273x500kQpkogHs(EditTextBoldCursor r0, DialogInterface r1) {
                AndroidUtilities.hideKeyboard(r0);
            }

            public static /* synthetic */ void $r8$lambda$czYagH_o37owBlGMbaIo359Ae70(EditTextBoldCursor r0, AlertDialog r1, int r2) {
                AndroidUtilities.hideKeyboard(r0);
            }

            public static /* synthetic */ void $r8$lambda$xG63AqOkVoBpLYBd5po09VeoCpM(AnonymousClass1 r0, EditTextBoldCursor r1, int r2, AlertDialog r3, int r4) {
                r0.lambda$onStartRecord$3(r1, r2, r3, r4);
            }

            public AnonymousClass1(AnonymousClass6 r1, Context r2, TLRPC.Chat r3, boolean r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            private /* synthetic */ void lambda$onStartRecord$1(AlertDialog r2, EditTextBoldCursor r3, DialogInterface r4) {
                this.this$1.this$0.makeFocusable(null, r2, r3, true);
            }

            private /* synthetic */ void lambda$onStartRecord$3(EditTextBoldCursor r1, int r2, AlertDialog r3, int r4) {
                this.this$1.this$0.call.toggleRecord(r1.getText().toString(), r2);
                AndroidUtilities.hideKeyboard(r1);
                UndoView r0 = this.this$1.this$0.getUndoView();
                if (r2 != 0) goto L5;
                int r12 = 39;
            L6:
                r0.showWithAction(0, r12, null);
                if (VoIPService.getSharedInstance() == null) goto L10;
                VoIPService.getSharedInstance().playStartRecordSound();
                return;
            L10:
                return;
            L5:
                r12 = 100;
                goto L6
            }

            @Override // org.telegram.ui.Components.GroupCallRecordAlert
            public void onStartRecord(int r14) {
                AlertDialog.Builder r0 = new AlertDialog.Builder(getContext());
                r0.setDialogButtonColorKey(Theme.key_voipgroup_listeningText);
                GroupCallActivity.m15237$$Nest$fputenterEventSent(this.this$1.this$0, false);
                r0.setTitle(LocaleController.getString(R.string.VoipGroupStartRecordingTitle));
                AnonymousClass6 r1 = this.this$1;
                if (r14 == 0) goto L5;
                boolean r12 = ChatObject.isChannelOrGiga(r1.this$0.currentChat);
                AnonymousClass6 r3 = this.this$1;
                if (r12 == false) goto L17;
                if (r3.this$0.call.call.rtmp_stream == false) goto L14;
                int r13 = R.string.VoipGroupStartRecordingRtmpVideoText;
            L15:
                r0.setMessage(LocaleController.getString(r13));
            L21:
                r0.setCheckFocusable(false);
                EditTextBoldCursor r15 = new EditTextBoldCursor(getContext());
                r15.setBackgroundDrawable(Theme.createEditTextDrawable(getContext(), Theme.getColor(Theme.key_voipgroup_windowBackgroundWhiteInputField), Theme.getColor(Theme.key_voipgroup_windowBackgroundWhiteInputFieldActivated)));
                LinearLayout r32 = new LinearLayout(getContext());
                r32.setOrientation(1);
                r0.setView(r32);
                r15.setTextSize(1, 16.0f);
                int r5 = Theme.key_voipgroup_nameText;
                r15.setTextColor(Theme.getColor(r5));
                r15.setMaxLines(1);
                r15.setLines(1);
                r15.setInputType(16385);
                r15.setGravity(51);
                r15.setSingleLine(true);
                r15.setHint(LocaleController.getString(R.string.VoipGroupSaveFileHint));
                r15.setImeOptions(6);
                r15.setHintTextColor(Theme.getColor(Theme.key_voipgroup_lastSeenText));
                r15.setCursorColor(Theme.getColor(r5));
                r15.setCursorSize(AndroidUtilities.dp(20.0f));
                r15.setCursorWidth(1.5f);
                r15.setPadding(0, AndroidUtilities.dp(4.0f), 0, 0);
                r32.addView(r15, LayoutHelper.createLinear(-1, 36, 51, 24, 0, 24, 12));
                r15.setOnEditorActionListener(new GroupCallActivity$6$1$$ExternalSyntheticLambda0(r0));
                AlertDialog r2 = r0.create();
                r2.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_inviteMembersBackground));
                r2.setOnShowListener(new GroupCallActivity$6$1$$ExternalSyntheticLambda1(this, r2, r15));
                r2.setOnDismissListener(new GroupCallActivity$6$1$$ExternalSyntheticLambda2(r15));
                r0.setPositiveButton(LocaleController.getString(R.string.Start), new GroupCallActivity$6$1$$ExternalSyntheticLambda3(this, r15, r14));
                r0.setNegativeButton(LocaleController.getString(R.string.Cancel), new GroupCallActivity$6$1$$ExternalSyntheticLambda4(r15));
                AlertDialog r132 = r0.create();
                r132.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_dialogBackground));
                r132.show();
                r132.setTextColor(Theme.getColor(r5));
                r15.requestFocus();
                return;
            L14:
                r13 = R.string.VoipChannelStartRecordingVideoText;
                goto L15
            L17:
                if (r3.this$0.call.call.rtmp_stream == false) goto L19;
                int r16 = R.string.VoipGroupStartRecordingRtmpVideoText;
            L20:
                r0.setMessage(LocaleController.getString(r16));
                goto L21
            L19:
                r16 = R.string.VoipGroupStartRecordingVideoText;
                goto L20
            L5:
                if (r1.this$0.call.call.rtmp_stream == false) goto L7;
                int r17 = R.string.VoipGroupStartRecordingRtmpText;
            L8:
                r0.setMessage(LocaleController.getString(r17));
                goto L21
            L7:
                r17 = R.string.VoipGroupStartRecordingText;
                goto L8
            }
        }

        /* renamed from: org.telegram.ui.GroupCallActivity$6$2, reason: invalid class name */
        public class AnonymousClass2 implements TextWatcher {
            boolean ignoreTextChange;
            final /* synthetic */ AnonymousClass6 this$1;
            final /* synthetic */ EditTextBoldCursor val$editText;

            public AnonymousClass2(AnonymousClass6 r1, EditTextBoldCursor r2) {
                this.this$1 = r1;
                this.val$editText = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r3) {
                if (this.ignoreTextChange == false) goto L6;
                return;
            L6:
                if (r3.length() <= 40) goto L14;
                this.ignoreTextChange = true;
                r3.delete(40, r3.length());
                AndroidUtilities.shakeView(this.val$editText);
                this.val$editText.performHapticFeedback(3, 2);     // Catch: Exception -> L11
            L9:
                this.ignoreTextChange = false;
                return;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        public static /* synthetic */ void $r8$lambda$5J0jy07Czqe7s9BkAqr7s9jXXgA(EditTextBoldCursor r0, DialogInterface r1) {
            AndroidUtilities.hideKeyboard(r0);
        }

        public static /* synthetic */ void $r8$lambda$AJ3sP9r4YypphYq6I3ge1nV8Td0(EditTextBoldCursor r0, AlertDialog r1, int r2) {
            AndroidUtilities.hideKeyboard(r0);
        }

        public static /* synthetic */ boolean $r8$lambda$D1ltd_eU8okhssrfndqIA5Jm9ak(AlertDialog.Builder r0, TextView r1, int r2, KeyEvent r3) {
            AndroidUtilities.hideKeyboard(r1);
            r0.create().getButton(-1).callOnClick();
            return false;
        }

        public static /* synthetic */ void $r8$lambda$EdLiFeD1nBNemn7PAPXWSlYqr3U(AnonymousClass6 r0, AlertDialog r1, int r2) {
            r0.lambda$onItemClick$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$LuOevZIN53aluwxUtdC3kkr1uZE(AnonymousClass6 r0, EditTextBoldCursor r1, AlertDialog.Builder r2, AlertDialog r3, int r4) {
            r0.lambda$onItemClick$5(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$OaLFMPzyOZNBsAE0XwP8XBrQCiI(AnonymousClass6 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$onItemClick$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$U6KGXXoKDOZlfDQiS4TP9MF0T0o(AnonymousClass6 r0, int r1) {
            r0.lambda$onItemClick$10(r1);
        }

        /* renamed from: $r8$lambda$UEQm4r2e1jr-obNKwh773u-nbzE, reason: not valid java name */
        public static /* synthetic */ void m15297$r8$lambda$UEQm4r2e1jrobNKwh773unbzE(AnonymousClass6 r0, TLRPC.InputPeer r1, boolean r2, boolean r3, boolean r4) {
            r0.lambda$onItemClick$9(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$cXcws3989j4bDHUwA6lGubhzTg8(AnonymousClass6 r0, ArrayList r1, DialogInterface r2, int r3) {
            r0.lambda$onItemClick$11(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$kUmVbj8SqExdqrSbn90RlI0T70Y(AnonymousClass6 r0, AlertDialog r1, EditTextBoldCursor r2, DialogInterface r3) {
            r0.lambda$onItemClick$6(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$t-6jC1WSfURy5tDSUywPpDZ0crs, reason: not valid java name */
        public static /* synthetic */ void m15298$r8$lambda$t6jC1WSfURy5tDSUywPpDZ0crs(TLObject r0, TLRPC.TL_error r1) {
        }

        /* renamed from: $r8$lambda$xKNK_0f-CEndrrb9AIYNeY_LXhM, reason: not valid java name */
        public static /* synthetic */ void m15299$r8$lambda$xKNK_0fCEndrrb9AIYNeY_LXhM(AnonymousClass6 r0, boolean r1, AlertDialog r2, int r3) {
            r0.lambda$onItemClick$2(r1, r2, r3);
        }

        public AnonymousClass6(GroupCallActivity r1, Activity r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        private /* synthetic */ void lambda$onItemClick$0(TLObject r1, TLRPC.TL_error r2) {
            if ((r1 instanceof TLRPC.TL_updates) == false) goto L6;
            MessagesController r0 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getMessagesController();
            r0.processUpdates((TLRPC.TL_updates) r1, false);
            return;
        }

        private /* synthetic */ void lambda$onItemClick$1(AlertDialog r4, int r5) {
            if (this.this$0.call.isScheduled() == false) goto L9;
            TLRPC.ChatFull r42 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getMessagesController().getChatFull(this.this$0.getChatId());
            if (r42 == null) goto L7;
            r42.flags &= -2097153;
            r42.call = null;
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getNotificationCenter().postNotificationName(NotificationCenter.groupCallUpdated, new Object[]{Long.valueOf(this.this$0.getChatId()), Long.valueOf(this.this$0.call.call.id), Boolean.FALSE});
        L7:
            TL_phone.discardGroupCall r43 = new TL_phone.discardGroupCall();
            r43.call = this.this$0.call.getInputGroupCall();
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getConnectionsManager().sendRequest(r43, new GroupCallActivity$6$$ExternalSyntheticLambda10(this));
        L11:
            this.this$0.dismiss();
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didStartedCall, new Object[0]);
            return;
        L9:
            if (VoIPService.getSharedInstance() == null) goto L11;
            VoIPService.getSharedInstance().hangUp(1);
            goto L11
        }

        private /* synthetic */ void lambda$onItemClick$10(int r4) {
            this.this$0.setAudioOutputValue(r4);
            BulletinFactory.of(this.this$0.topBulletinContainer, new DarkBlueThemeResourcesProvider()).createSimpleBulletin(this.this$0.getContext().getResources().getDrawable(this.this$0.getAudioOutputToastIcon(r4)).mutate(), this.this$0.getAudioOutputToastText(r4)).show(GroupCallActivity.m15277$$Nest$misBulletinTop(this.this$0));
        }

        private /* synthetic */ void lambda$onItemClick$11(ArrayList r1, DialogInterface r2, int r3) {
            if (VoIPService.getSharedInstance() != null) goto L5;
            return;
        L5:
            Integer r12 = (Integer) r1.get(r3);
            int r22 = r12.intValue();
            GroupCallActivity.m15233$$Nest$fputcacheAudioOutputValue(this.this$0, r12);
            GroupCallActivity.m15291$$Nest$mupdateState(this.this$0, true, true);
            GroupCallActivity.m15233$$Nest$fputcacheAudioOutputValue(this.this$0, null);
            AndroidUtilities.runOnUIThread(new GroupCallActivity$6$$ExternalSyntheticLambda9(this, r22));
        }

        private /* synthetic */ void lambda$onItemClick$2(boolean r2, AlertDialog r3, int r4) {
            this.this$0.call.toggleRecord(null, 0);
            UndoView r1 = this.this$0.getUndoView();
            if (r2 == false) goto L5;
            int r22 = 101;
        L6:
            r1.showWithAction(0, r22, null);
            return;
        L5:
            r22 = 40;
            goto L6
        }

        private /* synthetic */ void lambda$onItemClick$5(EditTextBoldCursor r1, AlertDialog.Builder r2, AlertDialog r3, int r4) {
            AndroidUtilities.hideKeyboard(r1);
            this.this$0.call.setTitle(r1.getText().toString());
            r2.getDismissRunnable().run();
        }

        private /* synthetic */ void lambda$onItemClick$6(AlertDialog r2, EditTextBoldCursor r3, DialogInterface r4) {
            this.this$0.makeFocusable(null, r2, r3, true);
        }

        private /* synthetic */ void lambda$onItemClick$9(TLRPC.InputPeer r9, boolean r10, boolean r11, boolean r12) {
            GroupCallActivity r112 = this.this$0;
            if (r112.call == null) goto L38;
            boolean r122 = r9 instanceof TLRPC.TL_inputPeerUser;
            if (r122 == false) goto L10;
            TLObject r113 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(r112).getMessagesController().getUser(Long.valueOf(r9.user_id));
        L8:
            TLObject r4 = r113;
            if (this.this$0.call.isScheduled() == false) goto L32;
            this.this$0.getUndoView().showWithAction(0, 37, r4, this.this$0.currentChat, null, null);
            if ((r9 instanceof TLRPC.TL_inputPeerChannel) == false) goto L18;
            GroupCallActivity.m15257$$Nest$fputselfPeer(this.this$0, new TLRPC.TL_peerChannel());
            TLRPC.Peer r102 = GroupCallActivity.m15206$$Nest$fgetselfPeer(this.this$0);
            r102.channel_id = r9.channel_id;
        L23:
            GroupCallActivity.m15252$$Nest$fputschedulePeer(this.this$0, r9);
            TLRPC.ChatFull r103 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getMessagesController().getChatFull(this.this$0.getChatId());
            if (r103 == null) goto L29;
            r103.groupcall_default_join_as = GroupCallActivity.m15206$$Nest$fgetselfPeer(this.this$0);
            boolean r114 = r103 instanceof TLRPC.TL_chatFull;
            int r123 = r103.flags;
            if (r114 == false) goto L28;
            r103.flags = 32768 | r123;
            goto L29
        L28:
            r103.flags = 67108864 | r123;
        L29:
            TL_phone.saveDefaultGroupCallJoinAs r104 = new TL_phone.saveDefaultGroupCallJoinAs();
            r104.peer = MessagesController.getInputPeer(this.this$0.currentChat);
            r104.join_as = r9;
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getConnectionsManager().sendRequest(r104, new GroupCallActivity$6$$ExternalSyntheticLambda11());
            GroupCallActivity.m15288$$Nest$mupdateItems(this.this$0);
            return;
        L18:
            if (r122 == false) goto L21;
            GroupCallActivity.m15257$$Nest$fputselfPeer(this.this$0, new TLRPC.TL_peerUser());
            TLRPC.Peer r105 = GroupCallActivity.m15206$$Nest$fgetselfPeer(this.this$0);
            r105.user_id = r9.user_id;
            goto L23
        L21:
            if ((r9 instanceof TLRPC.TL_inputPeerChat) == false) goto L23;
            GroupCallActivity.m15257$$Nest$fputselfPeer(this.this$0, new TLRPC.TL_peerChat());
            TLRPC.Peer r106 = GroupCallActivity.m15206$$Nest$fgetselfPeer(this.this$0);
            r106.chat_id = r9.chat_id;
            goto L23
        L32:
            if (VoIPService.getSharedInstance() == null) goto L37;
            if (r10 == false) goto L39;
            GroupCallActivity r107 = this.this$0;
            TLRPC.GroupCallParticipant r108 = r107.call.participants.get(MessageObject.getPeerId(GroupCallActivity.m15206$$Nest$fgetselfPeer(r107)));
            VoIPService.getSharedInstance().setGroupCallPeer(r9);
            GroupCallActivity.m15262$$Nest$fputuserSwitchObject(this.this$0, r4);
            return;
        L39:
            return;
        L37:
            return;
        L10:
            if ((r9 instanceof TLRPC.TL_inputPeerChat) == false) goto L12;
            r113 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(r112).getMessagesController().getChat(Long.valueOf(r9.chat_id));
            goto L8
        L12:
            r113 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(r112).getMessagesController().getChat(Long.valueOf(r9.channel_id));
            goto L8
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r14) {
            if (r14 != (-1)) goto L6;
            this.this$0.onBackPressed();
            return;
        L6:
            int r1 = 0;
            if (r14 != 1) goto L11;
            GroupCallActivity r13 = this.this$0;
            r13.call.call.join_muted = false;
            GroupCallActivity.m15287$$Nest$mtoggleAdminSpeak(r13);
            return;
        L11:
            if (r14 != 2) goto L15;
            GroupCallActivity r132 = this.this$0;
            r132.call.call.join_muted = true;
            GroupCallActivity.m15287$$Nest$mtoggleAdminSpeak(r132);
            return;
        L15:
            if (r14 != 3) goto L19;
            GroupCallActivity.m15273$$Nest$mgetLink(this.this$0, false);
            return;
        L19:
            if (r14 != 12) goto L23;
            GroupCallActivity.m15283$$Nest$msetCommentsEnabled(this.this$0, true);
            return;
        L23:
            if (r14 != 13) goto L27;
            GroupCallActivity.m15283$$Nest$msetCommentsEnabled(this.this$0, false);
            return;
        L27:
            if (r14 != 4) goto L38;
            AlertDialog.Builder r142 = new AlertDialog.Builder(this.this$0.getContext());
            if (ChatObject.isChannelOrGiga(this.this$0.currentChat) == false) goto L31;
            r142.setTitle(LocaleController.getString(R.string.VoipChannelEndAlertTitle));
            r142.setMessage(LocaleController.getString(R.string.VoipChannelEndAlertText));
        L32:
            r142.setDialogButtonColorKey(Theme.key_voipgroup_listeningText);
            r142.setPositiveButton(LocaleController.getString(R.string.VoipGroupEnd), new GroupCallActivity$6$$ExternalSyntheticLambda0(this));
            r142.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            AlertDialog r133 = r142.create();
            r133.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_dialogBackground));
            r133.show();
            TextView r143 = (TextView) r133.getButton(-1);
            if (r143 == null) goto L35;
            r143.setTextColor(Theme.getColor(Theme.key_voipgroup_leaveCallMenu));
        L35:
            r133.setTextColor(Theme.getColor(Theme.key_voipgroup_actionBarItems));
            return;
        L31:
            r142.setTitle(LocaleController.getString(R.string.VoipGroupEndAlertTitle));
            r142.setMessage(LocaleController.getString(R.string.VoipGroupEndAlertText));
            goto L32
        L38:
            if (r14 != 9) goto L42;
            GroupCallActivity.m15197$$Nest$fgetscreenShareItem(this.this$0).callOnClick();
            return;
        L42:
            if (r14 != 5) goto L58;
            GroupCallActivity r144 = this.this$0;
            ChatObject.Call r0 = r144.call;
            if (r0.recording == false) goto L51;
            boolean r02 = r0.call.record_video_active;
            AlertDialog.Builder r12 = new AlertDialog.Builder(r144.getContext());
            r12.setDialogButtonColorKey(Theme.key_voipgroup_listeningText);
            r12.setTitle(LocaleController.getString(R.string.VoipGroupStopRecordingTitle));
            if (ChatObject.isChannelOrGiga(this.this$0.currentChat) == false) goto L48;
            r12.setMessage(LocaleController.getString(R.string.VoipChannelStopRecordingText));
        L49:
            r12.setPositiveButton(LocaleController.getString(R.string.Stop), new GroupCallActivity$6$$ExternalSyntheticLambda1(this, r02));
            r12.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            AlertDialog r134 = r12.create();
            r134.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_dialogBackground));
            r134.show();
            r134.setTextColor(Theme.getColor(Theme.key_voipgroup_nameText));
            return;
        L48:
            r12.setMessage(LocaleController.getString(R.string.VoipGroupStopRecordingText));
            goto L49
        L51:
            Context r03 = this.this$0.getContext();
            GroupCallActivity r15 = this.this$0;
            AnonymousClass1 r145 = new AnonymousClass1(this, r03, r15.currentChat, GroupCallActivity.m15117$$Nest$fgethasVideo(r15));
            if (this.this$0.isRtmpStream() == false) goto L55;
            r145.onStartRecord(2);
            return;
        L55:
            r145.show();
            return;
        L58:
            if (r14 != 7) goto L62;
            GroupCallActivity.m15234$$Nest$fputchangingPermissions(this.this$0, true);
            GroupCallActivity.m15110$$Nest$fgeteveryoneItem(this.this$0).setVisibility(0);
            GroupCallActivity.m15075$$Nest$fgetadminItem(this.this$0).setVisibility(0);
            GroupCallActivity.m15119$$Nest$fgetinviteItem(this.this$0).setVisibility(8);
            GroupCallActivity.m15107$$Nest$fgetenableComments(this.this$0).setVisibility(8);
            GroupCallActivity.m15105$$Nest$fgetdisableComments(this.this$0).setVisibility(8);
            GroupCallActivity.m15125$$Nest$fgetleaveItem(this.this$0).setVisibility(8);
            GroupCallActivity.m15168$$Nest$fgetpermissionItem(this.this$0).setVisibility(8);
            GroupCallActivity.m15106$$Nest$fgeteditTitleItem(this.this$0).setVisibility(8);
            GroupCallActivity.m15183$$Nest$fgetrecordItem(this.this$0).setVisibility(8);
            GroupCallActivity.m15196$$Nest$fgetscreenItem(this.this$0).setVisibility(8);
            GroupCallActivity.m15071$$Nest$fgetaccountSelectCell(this.this$0).setVisibility(8);
            GroupCallActivity.m15211$$Nest$fgetsoundItem(this.this$0).setVisibility(8);
            GroupCallActivity.m15141$$Nest$fgetnoiseItem(this.this$0).setVisibility(8);
            GroupCallActivity.m15164$$Nest$fgetotherItem(this.this$0).forceUpdatePopupPosition();
            return;
        L62:
            if (r14 != 6) goto L76;
            GroupCallActivity.m15237$$Nest$fputenterEventSent(this.this$0, false);
            EditTextBoldCursor r146 = new EditTextBoldCursor(this.this$0.getContext());
            r146.setBackgroundDrawable(Theme.createEditTextDrawable(this.this$0.getContext(), true));
            AlertDialog.Builder r3 = new AlertDialog.Builder(this.this$0.getContext());
            r3.setDialogButtonColorKey(Theme.key_voipgroup_listeningText);
            if (ChatObject.isChannelOrGiga(this.this$0.currentChat) == false) goto L66;
            r3.setTitle(LocaleController.getString(R.string.VoipChannelTitle));
        L67:
            r3.setCheckFocusable(false);
            r3.setNegativeButton(LocaleController.getString(R.string.Cancel), new GroupCallActivity$6$$ExternalSyntheticLambda2(r146));
            LinearLayout r4 = new LinearLayout(this.this$0.getContext());
            r4.setOrientation(1);
            r3.setView(r4);
            r146.setTextSize(1, 16.0f);
            int r5 = Theme.key_voipgroup_nameText;
            r146.setTextColor(Theme.getColor(r5));
            r146.setMaxLines(1);
            r146.setLines(1);
            r146.setInputType(16385);
            r146.setGravity(51);
            r146.setSingleLine(true);
            r146.setImeOptions(6);
            TLRPC.Chat r04 = this.this$0.currentChat;
            if (r04 == null) goto L70;
            String r05 = r04.title;
        L71:
            r146.setHint(r05);
            r146.setHintTextColor(Theme.getColor(Theme.key_voipgroup_lastSeenText));
            r146.setCursorColor(Theme.getColor(r5));
            r146.setCursorSize(AndroidUtilities.dp(20.0f));
            r146.setCursorWidth(1.5f);
            r146.setPadding(0, AndroidUtilities.dp(4.0f), 0, 0);
            r4.addView(r146, LayoutHelper.createLinear(-1, 36, 51, 24, 6, 24, 0));
            r146.setOnEditorActionListener(new GroupCallActivity$6$$ExternalSyntheticLambda3(r3));
            r146.addTextChangedListener(new AnonymousClass2(this, r146));
            if (TextUtils.isEmpty(this.this$0.call.call.title) == true) goto L74;
            r146.setText(this.this$0.call.call.title);
            r146.setSelection(r146.length());
        L74:
            r3.setPositiveButton(LocaleController.getString(R.string.Save), new GroupCallActivity$6$$ExternalSyntheticLambda4(this, r146, r3));
            AlertDialog r06 = r3.create();
            r06.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_inviteMembersBackground));
            r06.setOnShowListener(new GroupCallActivity$6$$ExternalSyntheticLambda5(this, r06, r146));
            r06.setOnDismissListener(new GroupCallActivity$6$$ExternalSyntheticLambda6(r146));
            r06.show();
            r06.setTextColor(Theme.getColor(r5));
            r146.requestFocus();
            return;
        L70:
            r05 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L71
        L66:
            r3.setTitle(LocaleController.getString(R.string.VoipGroupTitle));
            goto L67
        L76:
            if (r14 != 8) goto L80;
            JoinCallAlert.open(this.this$0.getContext(), -this.this$0.getChatId(), GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0), null, 2, GroupCallActivity.m15206$$Nest$fgetselfPeer(this.this$0), new GroupCallActivity$6$$ExternalSyntheticLambda7(this));
            return;
        L80:
            if (r14 != 11) goto L87;
            SharedConfig.toggleNoiseSupression();
            VoIPService r135 = VoIPService.getSharedInstance();
            if (r135 == null) goto L133;
            r135.setNoiseSupressionEnabled(SharedConfig.noiseSupression);
            return;
        L133:
            return;
        L87:
            if (r14 != 10) goto L128;
            VoIPService r147 = VoIPService.getSharedInstance();
            if (r147 == null) goto L134;
            ArrayList r07 = new ArrayList();
            ArrayList r52 = new ArrayList();
            ArrayList r6 = new ArrayList();
            r07.add(LocaleController.getString(R.string.VoipAudioRoutingSpeaker));
            r52.add(Integer.valueOf(R.drawable.msg_voice_speaker));
            r6.add(0);
            if (r147.hasEarpiece() == false) goto L104;
            if (r147.isHeadsetPlugged() == false) goto L97;
            int r7 = R.string.VoipAudioRoutingHeadset;
        L98:
            r07.add(LocaleController.getString(r7));
            if (r147.isHeadsetPlugged() == false) goto L101;
            int r72 = R.drawable.msg_voice_headphones;
        L102:
            r52.add(Integer.valueOf(r72));
            r6.add(1);
            goto L104
        L101:
            r72 = R.drawable.msg_voice_phone;
            goto L102
        L97:
            r7 = R.string.VoipAudioRoutingPhone;
        L104:
            if (r147.isBluetoothHeadsetConnected() == false) goto L110;
            String r73 = r147.currentBluetoothDeviceName;
            if (r73 != null) goto L109;
            r73 = LocaleController.getString(R.string.VoipAudioRoutingBluetooth);
        L109:
            r07.add(r73);
            r52.add(Integer.valueOf(R.drawable.msg_voice_bluetooth));
            r6.add(2);
        L110:
            int r74 = r07.size();
            CharSequence[] r8 = new CharSequence[r74];
            int[] r9 = new int[r74];
            int r10 = 0;
        L111:
            if (r10 >= r74) goto L113;
            r8[r10] = (CharSequence) r07.get(r10);
            r9[r10] = ((Integer) r52.get(r10)).intValue();
            r10 = r10 + 1;
            goto L111
        L113:
            BottomSheet.Builder r136 = new BottomSheet.Builder(this.val$context).setTitle(LocaleController.getString(R.string.VoipSelectAudioOutput), true).setItems(r8, r9, new GroupCallActivity$6$$ExternalSyntheticLambda8(this, r6));
            BottomSheet r08 = r136.create();
            int r53 = Theme.key_voipgroup_listViewBackgroundUnscrolled;
            r08.setBackgroundColor(Theme.getColor(r53));
            r08.fixNavigationBar(Theme.getColor(r53));
            if (r147.getCurrentAudioRoute() != 1) goto L117;
            int r148 = 0;
        L120:
            r136.show();
            r08.setTitleColor(Theme.getColor(Theme.key_voipgroup_nameText));
        L122:
            if (r1 >= r08.getItemViews().size()) goto L135;
            BottomSheet.BottomSheetCell r137 = r08.getItemViews().get(r1);
            if (r1 != r148) goto L126;
            int r54 = Theme.getColor(Theme.key_voipgroup_listeningText);
            r137.isSelected = true;
        L127:
            r137.setTextColor(r54);
            r137.setIconColor(r54);
            r137.setBackground(Theme.createSelectorDrawable(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_voipgroup_actionBarItems), 12), 2));
            r1 = r1 + 1;
            goto L122
        L126:
            r54 = Theme.getColor(Theme.key_voipgroup_nameText);
            goto L127
        L135:
            return;
        L117:
            if (r147.getCurrentAudioRoute() != 0) goto L119;
            r148 = 1;
            goto L120
        L119:
            r148 = 2;
            goto L120
        L134:
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$60, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass60 implements View.OnTouchListener {
        private int[] pos;
        final /* synthetic */ GroupCallActivity this$0;
        final /* synthetic */ Rect val$rect;

        public AnonymousClass60(GroupCallActivity r1, Rect r2) {
            this.this$0 = r1;
            this.val$rect = r2;
            this.pos = new int[2];
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View r8, MotionEvent r9) {
            if (r9.getActionMasked() != 0) goto L12;
            if (GroupCallActivity.m15202$$Nest$fgetscrimPopupWindow(this.this$0) != null) goto L7;
        L18:
            return false;
        L7:
            if (GroupCallActivity.m15202$$Nest$fgetscrimPopupWindow(this.this$0).isShowing() == false) goto L18;
            View r82 = GroupCallActivity.m15202$$Nest$fgetscrimPopupWindow(this.this$0).getContentView();
            r82.getLocationInWindow(this.pos);
            Rect r1 = this.val$rect;
            int[] r2 = this.pos;
            int r3 = r2[0];
            r1.set(r3, r2[1], r82.getMeasuredWidth() + r3, this.pos[1] + r82.getMeasuredHeight());
            if (this.val$rect.contains((int) r9.getX(), (int) r9.getY()) == true) goto L18;
            GroupCallActivity.m15202$$Nest$fgetscrimPopupWindow(this.this$0).dismiss();
            goto L18
        L12:
            if (r9.getActionMasked() != 4) goto L18;
            if (GroupCallActivity.m15202$$Nest$fgetscrimPopupWindow(this.this$0) == null) goto L18;
            if (GroupCallActivity.m15202$$Nest$fgetscrimPopupWindow(this.this$0).isShowing() == false) goto L18;
            GroupCallActivity.m15202$$Nest$fgetscrimPopupWindow(this.this$0).dismiss();
            goto L18
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$61, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass61 extends LinearLayout {
        final /* synthetic */ GroupCallActivity this$0;
        final /* synthetic */ LinearLayout val$buttonsLayout;
        final /* synthetic */ LinearLayout val$volumeLayout;

        public AnonymousClass61(GroupCallActivity r1, Context r2, LinearLayout r3, LinearLayout r4) {
            this.this$0 = r1;
            this.val$buttonsLayout = r3;
            this.val$volumeLayout = r4;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            int r32 = View.MeasureSpec.getSize(r3);
            this.val$buttonsLayout.measure(View.MeasureSpec.makeMeasureSpec(r32, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
            LinearLayout r33 = this.val$volumeLayout;
            LinearLayout r42 = this.val$buttonsLayout;
            if (r33 == null) goto L6;
            r33.measure(View.MeasureSpec.makeMeasureSpec(r42.getMeasuredWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
            setMeasuredDimension(this.val$buttonsLayout.getMeasuredWidth(), this.val$buttonsLayout.getMeasuredHeight() + this.val$volumeLayout.getMeasuredHeight());
            return;
        L6:
            setMeasuredDimension(r42.getMeasuredWidth(), this.val$buttonsLayout.getMeasuredHeight());
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$62, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass62 extends ScrollView {
        final /* synthetic */ GroupCallActivity this$0;
        final /* synthetic */ LinearLayout val$linearLayout;

        public AnonymousClass62(GroupCallActivity r1, Context r2, AttributeSet r3, int r4, int r5, LinearLayout r6) {
            this.this$0 = r1;
            this.val$linearLayout = r6;
            super(r2, r3, r4, r5);
        }

        @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            setMeasuredDimension(this.val$linearLayout.getMeasuredWidth(), getMeasuredHeight());
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$63, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass63 extends ActionBarPopupWindow {
        final /* synthetic */ GroupCallActivity this$0;

        /* renamed from: org.telegram.ui.GroupCallActivity$63$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass63 this$1;

            public AnonymousClass1(AnonymousClass63 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                GroupCallActivity.m15269$$Nest$mclearScrimView(this.this$1.this$0);
                GroupCallActivity.access$3800(this.this$1.this$0).invalidate();
                GroupCallActivity.m15128$$Nest$fgetlistView(this.this$1.this$0).invalidate();
                if (GroupCallActivity.m15104$$Nest$fgetdelayedGroupCallUpdated(this.this$1.this$0) == false) goto L6;
                GroupCallActivity.m15235$$Nest$fputdelayedGroupCallUpdated(this.this$1.this$0, false);
                GroupCallActivity.m15264$$Nest$mapplyCallParticipantUpdates(this.this$1.this$0, true);
                return;
            }
        }

        public AnonymousClass63(GroupCallActivity r1, View r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow, android.widget.PopupWindow
        public void dismiss() {
            super.dismiss();
            if (GroupCallActivity.m15202$$Nest$fgetscrimPopupWindow(this.this$0) == this) goto L5;
            return;
        L5:
            GroupCallActivity.m15256$$Nest$fputscrimPopupWindow(this.this$0, null);
            if (GroupCallActivity.m15198$$Nest$fgetscrimAnimatorSet(this.this$0) == null) goto L8;
            GroupCallActivity.m15198$$Nest$fgetscrimAnimatorSet(this.this$0).cancel();
            GroupCallActivity.m15254$$Nest$fputscrimAnimatorSet(this.this$0, null);
        L8:
            GroupCallActivity.m15123$$Nest$fgetlayoutManager(this.this$0).setCanScrollVertically(true);
            GroupCallActivity.m15254$$Nest$fputscrimAnimatorSet(this.this$0, new AnimatorSet());
            ArrayList r0 = new ArrayList();
            r0.add(ObjectAnimator.ofInt(GroupCallActivity.m15200$$Nest$fgetscrimPaint(this.this$0), AnimationProperties.PAINT_ALPHA, new int[]{0}));
            GroupCallActivity.m15198$$Nest$fgetscrimAnimatorSet(this.this$0).playTogether(r0);
            GroupCallActivity.m15198$$Nest$fgetscrimAnimatorSet(this.this$0).setDuration(220);
            GroupCallActivity.m15198$$Nest$fgetscrimAnimatorSet(this.this$0).addListener(new AnonymousClass1(this));
            GroupCallActivity.m15198$$Nest$fgetscrimAnimatorSet(this.this$0).start();
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$64, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass64 extends AnimatorListenerAdapter {
        final /* synthetic */ GroupCallActivity this$0;
        final /* synthetic */ boolean val$enter;
        final /* synthetic */ GroupCallMiniTextureView val$videoRenderer;

        public AnonymousClass64(GroupCallActivity r1, GroupCallMiniTextureView r2, boolean r3) {
            this.this$0 = r1;
            this.val$videoRenderer = r2;
            this.val$enter = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            GroupCallMiniTextureView r52 = this.val$videoRenderer;
            if (r52 == null) goto L5;
            r52.animateToScrimView = false;
        L5:
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getNotificationCenter().onAnimationFinish(GroupCallActivity.m15170$$Nest$fgetpopupAnimationIndex(this.this$0));
            GroupCallActivity.m15231$$Nest$fputavatarPriviewTransitionInProgress(this.this$0, false);
            GroupCallActivity r53 = this.this$0;
            if (this.val$enter == false) goto L8;
            float r1 = 1.0f;
        L9:
            GroupCallActivity.m15247$$Nest$fputprogressToAvatarPreview(r53, r1);
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToScrimView = GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0);
            boolean r54 = this.val$enter;
            GroupCallActivity r12 = this.this$0;
            if (r54 == true) goto L20;
            GroupCallActivity.m15200$$Nest$fgetscrimPaint(r12).setAlpha(0);
            GroupCallActivity.m15269$$Nest$mclearScrimView(this.this$0);
            if (GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).getParent() == null) goto L14;
            GroupCallActivity.access$3900(this.this$0).removeView(GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0));
        L14:
            GroupCallActivity.m15255$$Nest$fputscrimPopupLayout(this.this$0, null);
            GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).setVisibility(8);
            GroupCallActivity.m15232$$Nest$fputavatarsPreviewShowed(this.this$0, false);
            GroupCallActivity.m15123$$Nest$fgetlayoutManager(this.this$0).setCanScrollVertically(true);
            GroupCallActivity.m15091$$Nest$fgetblurredView(this.this$0).setVisibility(8);
            if (GroupCallActivity.m15104$$Nest$fgetdelayedGroupCallUpdated(this.this$0) == false) goto L18;
            GroupCallActivity.m15235$$Nest$fputdelayedGroupCallUpdated(this.this$0, false);
            GroupCallActivity.m15264$$Nest$mapplyCallParticipantUpdates(this.this$0, true);
        L18:
            if (GroupCallActivity.m15203$$Nest$fgetscrimRenderer(this.this$0) == null) goto L21;
            GroupCallActivity.m15203$$Nest$fgetscrimRenderer(this.this$0).textureView.setRoundCorners(0.0f);
        L21:
            GroupCallActivity.m15267$$Nest$mcheckContentOverlayed(this.this$0);
            GroupCallActivity.access$4000(this.this$0).invalidate();
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).invalidate();
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).invalidate();
            return;
        L20:
            GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(r12).setAlpha(1.0f);
            GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).setScaleX(1.0f);
            GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).setScaleY(1.0f);
            GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).setTranslationX(0.0f);
            GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).setTranslationY(0.0f);
            goto L21
        L8:
            r1 = 0.0f;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$65, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass65 extends DiffUtil.Callback {
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass65(GroupCallActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int r1, int r2) {
            return true;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int r8, int r9) {
            if (ListAdapter.m15318$$Nest$fgetaddMemberRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0)) < 0) goto L19;
            if (r8 != GroupCallActivity.m15142$$Nest$fgetoldAddMemberRow(this.this$0)) goto L10;
            if (r9 != ListAdapter.m15318$$Nest$fgetaddMemberRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L10;
            return true;
        L10:
            if (r8 != GroupCallActivity.m15142$$Nest$fgetoldAddMemberRow(this.this$0)) goto L14;
            if (r9 == ListAdapter.m15318$$Nest$fgetaddMemberRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L14;
        L17:
            return false;
        L14:
            if (r8 == GroupCallActivity.m15142$$Nest$fgetoldAddMemberRow(this.this$0)) goto L19;
            if (r9 == ListAdapter.m15318$$Nest$fgetaddMemberRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L17;
        L19:
            if (ListAdapter.m15319$$Nest$fgetconferenceAddPeopleRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0)) < 0) goto L35;
            if (r8 != GroupCallActivity.m15143$$Nest$fgetoldConferenceAddPeopleRow(this.this$0)) goto L26;
            if (r9 != ListAdapter.m15319$$Nest$fgetconferenceAddPeopleRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L26;
            return true;
        L26:
            if (r8 != GroupCallActivity.m15143$$Nest$fgetoldConferenceAddPeopleRow(this.this$0)) goto L30;
            if (r9 == ListAdapter.m15319$$Nest$fgetconferenceAddPeopleRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L30;
        L33:
            return false;
        L30:
            if (r8 == GroupCallActivity.m15143$$Nest$fgetoldConferenceAddPeopleRow(this.this$0)) goto L35;
            if (r9 == ListAdapter.m15319$$Nest$fgetconferenceAddPeopleRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L33;
        L35:
            if (ListAdapter.m15320$$Nest$fgetconferenceShareLinkRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0)) < 0) goto L51;
            if (r8 != GroupCallActivity.m15144$$Nest$fgetoldConferenceShareLinkRow(this.this$0)) goto L42;
            if (r9 != ListAdapter.m15320$$Nest$fgetconferenceShareLinkRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L42;
            return true;
        L42:
            if (r8 != GroupCallActivity.m15144$$Nest$fgetoldConferenceShareLinkRow(this.this$0)) goto L46;
            if (r9 == ListAdapter.m15320$$Nest$fgetconferenceShareLinkRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L46;
        L49:
            return false;
        L46:
            if (r8 == GroupCallActivity.m15144$$Nest$fgetoldConferenceShareLinkRow(this.this$0)) goto L51;
            if (r9 == ListAdapter.m15320$$Nest$fgetconferenceShareLinkRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L49;
        L51:
            if (ListAdapter.m15321$$Nest$fgetencryptionRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0)) < 0) goto L67;
            if (r8 != GroupCallActivity.m15146$$Nest$fgetoldEncryptionRow(this.this$0)) goto L58;
            if (r9 != ListAdapter.m15321$$Nest$fgetencryptionRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L58;
            return true;
        L58:
            if (r8 != GroupCallActivity.m15146$$Nest$fgetoldEncryptionRow(this.this$0)) goto L62;
            if (r9 == ListAdapter.m15321$$Nest$fgetencryptionRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L62;
        L65:
            return false;
        L62:
            if (r8 == GroupCallActivity.m15146$$Nest$fgetoldEncryptionRow(this.this$0)) goto L67;
            if (r9 == ListAdapter.m15321$$Nest$fgetencryptionRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L65;
        L67:
            if (ListAdapter.m15334$$Nest$fgetvideoNotAvailableRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0)) < 0) goto L83;
            if (r8 != GroupCallActivity.m15162$$Nest$fgetoldVideoNotAvailableRow(this.this$0)) goto L74;
            if (r9 != ListAdapter.m15334$$Nest$fgetvideoNotAvailableRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L74;
            return true;
        L74:
            if (r8 != GroupCallActivity.m15162$$Nest$fgetoldVideoNotAvailableRow(this.this$0)) goto L78;
            if (r9 == ListAdapter.m15334$$Nest$fgetvideoNotAvailableRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L78;
        L81:
            return false;
        L78:
            if (r8 == GroupCallActivity.m15162$$Nest$fgetoldVideoNotAvailableRow(this.this$0)) goto L83;
            if (r9 == ListAdapter.m15334$$Nest$fgetvideoNotAvailableRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L81;
        L83:
            if (ListAdapter.m15333$$Nest$fgetvideoGridDividerRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0)) < 0) goto L90;
            if (ListAdapter.m15333$$Nest$fgetvideoGridDividerRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0)) != r9) goto L90;
            if (r8 != GroupCallActivity.m15161$$Nest$fgetoldVideoDividerRow(this.this$0)) goto L90;
            return true;
        L90:
            if (r8 != (GroupCallActivity.m15145$$Nest$fgetoldCount(this.this$0) - 1)) goto L95;
            if (r9 != (ListAdapter.m15324$$Nest$fgetrowsCount(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0)) - 1)) goto L95;
            return true;
        L95:
            if (r8 != (GroupCallActivity.m15145$$Nest$fgetoldCount(this.this$0) - 1)) goto L97;
        L154:
            return false;
        L97:
            if (r9 == (ListAdapter.m15324$$Nest$fgetrowsCount(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0)) - 1)) goto L154;
            if (r9 < ListAdapter.m15332$$Nest$fgetusersVideoGridStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L110;
            if (r9 >= ListAdapter.m15331$$Nest$fgetusersVideoGridEndRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L110;
            if (r8 < GroupCallActivity.m15160$$Nest$fgetoldUsersVideoStartRow(this.this$0)) goto L110;
            if (r8 >= GroupCallActivity.m15159$$Nest$fgetoldUsersVideoEndRow(this.this$0)) goto L110;
            ChatObject.VideoParticipant r82 = (ChatObject.VideoParticipant) GroupCallActivity.m15163$$Nest$fgetoldVideoParticipants(this.this$0).get(r8 - GroupCallActivity.m15160$$Nest$fgetoldUsersVideoStartRow(this.this$0));
            GroupCallActivity r7 = this.this$0;
            return r82.equals(r7.visibleVideoParticipants.get(r9 - ListAdapter.m15332$$Nest$fgetusersVideoGridStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(r7))));
        L110:
            if (r9 < ListAdapter.m15330$$Nest$fgetusersStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L125;
            if (r9 >= ListAdapter.m15329$$Nest$fgetusersEndRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L125;
            if (r8 < GroupCallActivity.m15158$$Nest$fgetoldUsersStartRow(this.this$0)) goto L125;
            if (r8 >= GroupCallActivity.m15157$$Nest$fgetoldUsersEndRow(this.this$0)) goto L125;
            TLRPC.GroupCallParticipant r0 = (TLRPC.GroupCallParticipant) GroupCallActivity.m15150$$Nest$fgetoldParticipants(this.this$0).get(r8 - GroupCallActivity.m15158$$Nest$fgetoldUsersStartRow(this.this$0));
            GroupCallActivity r72 = this.this$0;
            if (MessageObject.getPeerId(r0.peer) != MessageObject.getPeerId(r72.call.visibleParticipants.get(r9 - ListAdapter.m15330$$Nest$fgetusersStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(r72))).peer)) goto L123;
            if (r8 != r9) goto L121;
        L122:
            return true;
        L121:
            if (r0.lastActiveDate == r0.active_date) goto L122;
        L123:
            return false;
        L125:
            if (r9 < ListAdapter.m15323$$Nest$fgetinvitedStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L135;
            if (r9 >= ListAdapter.m15322$$Nest$fgetinvitedEndRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L135;
            if (r8 < GroupCallActivity.m15149$$Nest$fgetoldInvitedStartRow(this.this$0)) goto L135;
            if (r8 >= GroupCallActivity.m15148$$Nest$fgetoldInvitedEndRow(this.this$0)) goto L135;
            Long r83 = (Long) GroupCallActivity.m15147$$Nest$fgetoldInvited(this.this$0).get(r8 - GroupCallActivity.m15149$$Nest$fgetoldInvitedStartRow(this.this$0));
            GroupCallActivity r73 = this.this$0;
            return r83.equals(r73.call.invitedUsers.get(r9 - ListAdapter.m15323$$Nest$fgetinvitedStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(r73))));
        L135:
            if (r9 < ListAdapter.m15326$$Nest$fgetshadyJoinStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L145;
            if (r9 >= ListAdapter.m15325$$Nest$fgetshadyJoinEndRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L145;
            if (r8 < GroupCallActivity.m15153$$Nest$fgetoldShadyJoinStartRow(this.this$0)) goto L145;
            if (r8 >= GroupCallActivity.m15152$$Nest$fgetoldShadyJoinEndRow(this.this$0)) goto L145;
            Long r84 = (Long) GroupCallActivity.m15151$$Nest$fgetoldShadyJoin(this.this$0).get(r8 - GroupCallActivity.m15153$$Nest$fgetoldShadyJoinStartRow(this.this$0));
            GroupCallActivity r74 = this.this$0;
            return r84.equals(r74.call.shadyJoinParticipants.get(r9 - ListAdapter.m15326$$Nest$fgetshadyJoinStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(r74))));
        L145:
            if (r9 < ListAdapter.m15328$$Nest$fgetshadyLeftStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L154;
            if (r9 >= ListAdapter.m15327$$Nest$fgetshadyLeftEndRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0))) goto L154;
            if (r8 < GroupCallActivity.m15156$$Nest$fgetoldShadyLeftStartRow(this.this$0)) goto L154;
            if (r8 >= GroupCallActivity.m15155$$Nest$fgetoldShadyLeftEndRow(this.this$0)) goto L154;
            Long r85 = (Long) GroupCallActivity.m15154$$Nest$fgetoldShadyLeft(this.this$0).get(r8 - GroupCallActivity.m15156$$Nest$fgetoldShadyLeftStartRow(this.this$0));
            GroupCallActivity r75 = this.this$0;
            return r85.equals(r75.call.shadyLeftParticipants.get(r9 - ListAdapter.m15328$$Nest$fgetshadyLeftStartRow(GroupCallActivity.m15127$$Nest$fgetlistAdapter(r75))));
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return ListAdapter.m15324$$Nest$fgetrowsCount(GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0));
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return GroupCallActivity.m15145$$Nest$fgetoldCount(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$66, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass66 implements ReactionsContainerLayout.ReactionsContainerDelegate {
        private final Paint bgPaint;
        private final Path clipPath;
        final /* synthetic */ GroupCallActivity this$0;

        public AnonymousClass66(GroupCallActivity r2) {
            this.this$0 = r2;
            this.clipPath = new Path();
            Paint r22 = new Paint(1);
            this.bgPaint = r22;
            r22.setColor(-14603467);
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public boolean allowLongPress() {
            return false;
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public boolean drawBackground() {
            return true;
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public void drawRoundRect(Canvas r1, RectF r2, float r3, float r4, float r5, int r6, boolean r7) {
            Paint r52 = this.bgPaint;
            if (r3 <= 0.0f) goto L5;
            r1.drawRoundRect(r2, r3, r3, r52);
        L7:
            if (Build.VERSION.SDK_INT >= 29) goto L9;
            return;
        L9:
            if (r1.isHardwareAccelerated() == true) goto L11;
            return;
        L11:
            if (GroupCallActivity.m15186$$Nest$fgetrenderNodeBlur(this.this$0) == null) goto L20;
            r1.save();
            if (r3 <= 0.0f) goto L15;
            this.clipPath.rewind();
            this.clipPath.addRoundRect(r2, r3, r3, Path.Direction.CW);
            this.clipPath.close();
            r1.clipPath(this.clipPath);
        L16:
            r1.translate(-GroupCallActivity.m15181$$Nest$fgetreactionsContainerLayout(this.this$0).getX(), -GroupCallActivity.m15181$$Nest$fgetreactionsContainerLayout(this.this$0).getY());
            r1.scale(GroupCallActivity.m15187$$Nest$fgetrenderNodeBlurScale(this.this$0), GroupCallActivity.m15187$$Nest$fgetrenderNodeBlurScale(this.this$0));
            r1.drawRenderNode(GroupCallActivity.m15186$$Nest$fgetrenderNodeBlur(this.this$0));
            r1.restore();
            return;
        L15:
            r1.clipRect(r2);
            goto L16
        L20:
            return;
        L5:
            r1.drawRect(r2, r52);
            goto L7
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public void onReactionClicked(View r5, ReactionsLayoutInBubble.VisibleReaction r6, boolean r7, boolean r8) {
            TLRPC.TL_messageEntityCustomEmoji r52 = new TLRPC.TL_messageEntityCustomEmoji();
            String r72 = r6.emojicon;
            if (r72 != null) goto L6;
            r72 = "👍";
        L6:
            TLRPC.TL_textWithEntities r82 = new TLRPC.TL_textWithEntities();
            r82.text = r72;
            long r0 = r6.documentId;
            if (r0 == 0) goto L9;
            r52.document_id = r0;
            r52.offset = 0;
            r52.length = r72.length();
            r82.entities.add(r52);
        L9:
            GroupCallActivity.m15281$$Nest$msendGroupCallMessage(this.this$0, r82);
            GroupCallActivity.m15274$$Nest$mhideKeyboardOrEmojiView(this.this$0);
            CustomEmojiReactionsWindow r53 = GroupCallActivity.m15181$$Nest$fgetreactionsContainerLayout(this.this$0).getReactionsWindow();
            if (r53 != null) goto L12;
            return;
        L12:
            if (r53.isShowing() == false) goto L16;
            GroupCallActivity.m15181$$Nest$fgetreactionsContainerLayout(this.this$0).getReactionsWindow().dismissWithAlpha();
            GroupCallActivity.m15181$$Nest$fgetreactionsContainerLayout(this.this$0).reset();
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends SizeNotifierFrameLayout {
        private boolean ignoreLayout;
        private int lastSize;
        HashMap<Object, View> listCells;
        boolean localHasVideo;
        private RectF rect;
        final /* synthetic */ GroupCallActivity this$0;
        private boolean updateRenderers;
        boolean wasLayout;

        public AnonymousClass7(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.ignoreLayout = false;
            this.rect = new RectF();
            this.listCells = new HashMap();
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r21) {
            int r8 = 0;
            if (GroupCallActivity.m15120$$Nest$fgetisInDrawRenderNodeBlur(this.this$0) == false) goto L5;
        L16:
            int r1 = 0;
        L17:
            int r3 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildCount();
            GroupCallActivity r4 = this.this$0;
            if (r1 >= r3) goto L30;
            View r32 = GroupCallActivity.m15128$$Nest$fgetlistView(r4).getChildAt(r1);
            if ((r32 instanceof GroupCallUserCell) == false) goto L23;
            ((GroupCallUserCell) r32).setDrawAvatar(true);
        L23:
            if ((r32 instanceof GroupCallGridCell) == true) goto L28;
            if (r32.getMeasuredWidth() == GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getMeasuredWidth()) goto L27;
            r32.setTranslationX((GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getMeasuredWidth() - r32.getMeasuredWidth()) >> 1);
            goto L28
        L27:
            r32.setTranslationX(0.0f);
        L28:
            r1 = r1 + 1;
            goto L17
        L30:
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(r4).isAnimating() == true) goto L32;
            int r12 = 0;
        L73:
            if (r12 >= this.this$0.fullscreenUsersListView.getChildCount()) goto L75;
            ((GroupCallFullscreenAdapter.GroupCallUserCell) this.this$0.fullscreenUsersListView.getChildAt(r12)).setProgressToFullscreen(1.0f);
            r12 = r12 + 1;
        L75:
            int r13 = 0;
        L77:
            if (r13 >= GroupCallActivity.m15082$$Nest$fgetattachedRenderers(this.this$0).size()) goto L79;
            GroupCallMiniTextureView r2 = (GroupCallMiniTextureView) GroupCallActivity.m15082$$Nest$fgetattachedRenderers(this.this$0).get(r13);
            RecyclerListView r33 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0);
            GroupCallActivity r42 = this.this$0;
            r2.updatePosition(r33, r42.tabletVideoGridView, r42.fullscreenUsersListView, GroupCallActivity.m15188$$Nest$fgetrenderersContainer(r42));
            r13 = r13 + 1;
            goto L77
        L79:
            boolean r14 = GroupCallActivity.isTabletMode;
            GroupCallActivity r22 = this.this$0;
            if (r14 == true) goto L82;
            GroupCallActivity.m15092$$Nest$fgetbuttonsBackgroundGradientView(r22).setAlpha(1.0f - GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode);
            GroupCallActivity.m15093$$Nest$fgetbuttonsBackgroundGradientView2(this.this$0).setAlpha(1.0f - GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode);
        L83:
            boolean r15 = GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).swipedBack;
            GroupCallActivity r23 = this.this$0;
            if (r15 == false) goto L86;
            GroupCallActivity.m15128$$Nest$fgetlistView(r23).setAlpha(1.0f - GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode);
        L88:
            if (GroupCallActivity.m15227$$Nest$fgetwatchersView(this.this$0) == null) goto L90;
            GroupCallActivity.m15227$$Nest$fgetwatchersView(this.this$0).setAlpha(1.0f - GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode);
            GroupCallActivity.m15227$$Nest$fgetwatchersView(this.this$0).setTranslationY(GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode * AndroidUtilities.dp(64.0f));
        L90:
            super.dispatchDraw(r21);
            GroupCallActivity r16 = this.this$0;
            if (r16.drawingForBlur == true) goto L213;
            boolean r17 = GroupCallActivity.m15086$$Nest$fgetavatarsPreviewShowed(r16);
            GroupCallActivity r24 = this.this$0;
            if (r17 == false) goto L151;
            if (GroupCallActivity.m15204$$Nest$fgetscrimView(r24) != null) goto L98;
            return;
        L98:
            if (GroupCallActivity.m15226$$Nest$fgetuseBlur(this.this$0) == true) goto L100;
            Canvas r18 = r21;
            r18.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), GroupCallActivity.m15200$$Nest$fgetscrimPaint(this.this$0));
        L101:
            float r132 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getY();
            float[] r142 = new float[8];
            Path r152 = new Path();
            int r25 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildCount();
            float r162 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getY() + GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getMeasuredHeight();
            if (GroupCallActivity.m15116$$Nest$fgethasScrimAnchorView(this.this$0) == false) goto L110;
            int r34 = 0;
        L104:
            if (r34 >= r25) goto L109;
            if (GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildAt(r34) == GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0)) goto L107;
            r34 = r34 + 1;
            goto L104
        L107:
            GroupCallUserCell r26 = GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0);
        L111:
            if (r26 == null) goto L142;
            if (r132 >= r162) goto L142;
            r18.save();
            if (GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0) != null) goto L117;
            r18.clipRect(0.0f, (1.0f - GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0)) * r132, getMeasuredWidth(), ((1.0f - GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0)) * r162) + (getMeasuredHeight() * GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0)));
        L117:
            boolean r35 = GroupCallActivity.m15116$$Nest$fgethasScrimAnchorView(this.this$0);
            GroupCallActivity r43 = this.this$0;
            if (r35 == true) goto L120;
            float r36 = GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(r43).getTop() + GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getMeasuredWidth();
            float r44 = GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getLeft();
        L121:
            r18.translate(r44, r36);
            if (GroupCallActivity.m15116$$Nest$fgethasScrimAnchorView(this.this$0) == true) goto L124;
            float r182 = r36;
            GroupCallUserCell r19 = r26;
            r18.saveLayerAlpha(0.0f, 0.0f, r26.getMeasuredWidth(), r26.getClipHeight(), (int) (GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0) * 255.0f), 31);
        L125:
            float r27 = (int) (r19.getMeasuredHeight() + ((r19.getClipHeight() - r19.getMeasuredHeight()) * (1.0f - CubicBezierInterpolator.EASE_OUT.getInterpolation(1.0f - GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0)))));
            this.rect.set(0.0f, 0.0f, r19.getMeasuredWidth(), r27);
            if (GroupCallActivity.m15116$$Nest$fgethasScrimAnchorView(this.this$0) == false) goto L129;
            float r37 = GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0);
        L128:
            GroupCallUserCell r45 = r19;
            r45.setProgressToAvatarPreview(r37);
        L132:
            if (r8 >= 4) goto L134;
            r142[r8] = AndroidUtilities.dp(13.0f) * (1.0f - GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0));
            r142[r8 + 4] = AndroidUtilities.dp(13.0f);
            r8 = r8 + 1;
            goto L132
        L134:
            r152.reset();
            r152.addRoundRect(this.rect, r142, Path.Direction.CW);
            r152.close();
            r18.drawPath(r152, GroupCallActivity.m15129$$Nest$fgetlistViewBackgroundPaint(this.this$0));
            r45.draw(r18);
            r18.restore();
            r18.restore();
            if (GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0) == null) goto L142;
            float r38 = r182 + r27;
            float r28 = (getMeasuredWidth() - GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).getMeasuredWidth()) - AndroidUtilities.dp(14.0f);
            if (GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0) == 1.0f) goto L139;
            r18.saveLayerAlpha(r28, r38, GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).getMeasuredWidth() + r28, GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).getMeasuredHeight() + r38, (int) (GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0) * 255.0f), 31);
        L140:
            GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).setTranslationX(r28 - GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).getLeft());
            GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).setTranslationY(r38 - GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).getTop());
            float r46 = (GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0) * 0.2f) + 0.8f;
            r18.scale(r46, r46, (GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).getMeasuredWidth() / 2.0f) + r28, r38);
            r18.translate(r28, r38);
            GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).draw(r18);
            r18.restore();
            goto L142
        L139:
            r18.save();
            goto L140
        L129:
            r37 = 1.0f;
            goto L128
        L124:
            r19 = r26;
            r182 = r36;
            r18.save();
            goto L125
        L120:
            r36 = ((GroupCallActivity.m15128$$Nest$fgetlistView(r43).getY() + r26.getY()) * (1.0f - GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0))) + ((GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getTop() + GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getMeasuredWidth()) * GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0));
            r44 = ((GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getLeft() + r26.getX()) * (1.0f - GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0))) + (GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getLeft() * GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0));
        L142:
            if (this.this$0.pinchToZoomHelper.isInOverlayMode() == true) goto L209;
            r18.save();
            if (GroupCallActivity.m15116$$Nest$fgethasScrimAnchorView(this.this$0) == true) goto L146;
        L148:
            r18.scale(GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getScaleX(), GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getScaleY(), GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getX(), GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getY());
            r18.translate(GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getX(), GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getY());
            GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).draw(r18);
            r18.restore();
            return;
        L146:
            if (GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0) != null) goto L148;
            r18.clipRect(0.0f, r132 * (1.0f - GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0)), getMeasuredWidth(), (r162 * (1.0f - GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0))) + (getMeasuredHeight() * GroupCallActivity.m15175$$Nest$fgetprogressToAvatarPreview(this.this$0)));
            goto L148
        L209:
            return;
        L109:
            r26 = null;
            goto L111
        L110:
            r26 = GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0);
            goto L111
        L100:
            r18 = r21;
            goto L101
        L151:
            if (GroupCallActivity.m15204$$Nest$fgetscrimView(r24) == null) goto L210;
            r21.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), GroupCallActivity.m15200$$Nest$fgetscrimPaint(this.this$0));
            float r133 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getY();
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getY();
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getMeasuredHeight();
            boolean r110 = GroupCallActivity.m15116$$Nest$fgethasScrimAnchorView(this.this$0);
            GroupCallActivity r29 = this.this$0;
            if (r110 == false) goto L168;
            int r143 = GroupCallActivity.m15128$$Nest$fgetlistView(r29).getChildCount();
        L155:
            if (r8 >= r143) goto L214;
            View r153 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildAt(r8);
            if (r153 != GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0)) goto L166;
            float r210 = Math.max(GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getLeft(), GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getLeft() + r153.getX());
            float r39 = Math.max(r133, GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getY() + r153.getY());
            float r47 = Math.min(GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getRight(), (GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getLeft() + r153.getX()) + r153.getMeasuredWidth());
            float r5 = Math.min(GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getY() + GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getMeasuredHeight(), (GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getY() + r153.getY()) + GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getClipHeight());
            if (r39 >= r5) goto L166;
            if (r153.getAlpha() == 1.0f) goto L164;
            Canvas r111 = r21;
            r111.saveLayerAlpha(r210, r39, r47, r5, (int) (r153.getAlpha() * 255.0f), 31);
        L165:
            r111.clipRect(r210, r39, r47, getMeasuredHeight());
            r111.translate(GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getLeft() + r153.getX(), GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getY() + r153.getY());
            float r211 = GroupCallActivity.m15200$$Nest$fgetscrimPaint(this.this$0).getAlpha() / 100.0f;
            this.rect.set(0.0f, 0.0f, r153.getMeasuredWidth(), (int) (GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getMeasuredHeight() + ((GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getClipHeight() - GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getMeasuredHeight()) * (1.0f - CubicBezierInterpolator.EASE_OUT.getInterpolation(1.0f - r211)))));
            GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).setAboutVisibleProgress(GroupCallActivity.m15129$$Nest$fgetlistViewBackgroundPaint(this.this$0).getColor(), r211);
            r111.drawRoundRect(this.rect, AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), GroupCallActivity.m15129$$Nest$fgetlistViewBackgroundPaint(this.this$0));
            r153.draw(r111);
            r111.restore();
            goto L166
        L164:
            r111 = r21;
            r111.save();
        L166:
            r8 = r8 + 1;
            goto L155
        L214:
            return;
        L168:
            if (GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(r29) == null) goto L180;
            r21.save();
            r21.translate((GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).getX() + this.this$0.fullscreenUsersListView.getX()) + GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).getX(), (GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).getY() + this.this$0.fullscreenUsersListView.getY()) + GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).getY());
            if (GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).getRenderer() != null) goto L172;
        L176:
            GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).draw(r21);
        L177:
            GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).drawOverlays(r21);
            r21.restore();
            return;
        L172:
            if (GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).getRenderer().isAttached() == false) goto L176;
            if (GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).getRenderer().showingInFullscreen == true) goto L176;
            GroupCallActivity.m15199$$Nest$fgetscrimFullscreenView(this.this$0).getRenderer().draw(r21);
            goto L177
        L180:
            if (GroupCallActivity.m15203$$Nest$fgetscrimRenderer(this.this$0) != null) goto L182;
            return;
        L182:
            if (GroupCallActivity.m15203$$Nest$fgetscrimRenderer(this.this$0).isAttached() == false) goto L212;
            r21.save();
            r21.translate(GroupCallActivity.m15203$$Nest$fgetscrimRenderer(this.this$0).getX() + GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).getX(), GroupCallActivity.m15203$$Nest$fgetscrimRenderer(this.this$0).getY() + GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).getY());
            GroupCallActivity.m15203$$Nest$fgetscrimRenderer(this.this$0).draw(r21);
            r21.restore();
            return;
        L212:
            return;
        L210:
            return;
        L213:
            return;
        L86:
            GroupCallActivity.m15128$$Nest$fgetlistView(r23).setAlpha(1.0f);
            goto L88
        L82:
            GroupCallActivity.m15092$$Nest$fgetbuttonsBackgroundGradientView(r22).setAlpha(1.0f);
            GroupCallActivity.m15093$$Nest$fgetbuttonsBackgroundGradientView2(this.this$0).setAlpha(1.0f);
            goto L83
        L32:
            if (this.this$0.fullscreenUsersListView.getVisibility() != 0) goto L75;
            this.listCells.clear();
            int r112 = 0;
        L35:
            if (r112 >= GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildCount()) goto L52;
            View r310 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildAt(r112);
            if (r310.isAttachedToWindow() == false) goto L51;
            if ((r310 instanceof GroupCallGridCell) == false) goto L47;
            if (GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildAdapterPosition(r310) < 0) goto L47;
            GroupCallGridCell r48 = (GroupCallGridCell) r310;
            if (r48.getRenderer() == GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).fullscreenTextureView) goto L51;
            this.listCells.put(r48.getParticipant(), r310);
        L47:
            if ((r310 instanceof GroupCallUserCell) == false) goto L51;
            if (GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildAdapterPosition(r310) < 0) goto L51;
            GroupCallUserCell r311 = (GroupCallUserCell) r310;
            this.listCells.put(r311.getParticipant(), r311);
        L51:
            r112 = r112 + 1;
            goto L35
        L52:
            int r113 = 0;
        L54:
            if (r113 >= this.this$0.fullscreenUsersListView.getChildCount()) goto L75;
            GroupCallFullscreenAdapter.GroupCallUserCell r312 = (GroupCallFullscreenAdapter.GroupCallUserCell) this.this$0.fullscreenUsersListView.getChildAt(r113);
            View r49 = this.listCells.get(r312.getVideoParticipant());
            if (r49 != null) goto L58;
            r49 = this.listCells.get(r312.getParticipant());
        L58:
            float r52 = GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode;
            if (GroupCallActivity.m15112$$Nest$fgetfullscreenListItemAnimator(this.this$0).isRunning() == true) goto L61;
            r312.setAlpha(1.0f);
        L61:
            if (r49 != null) goto L63;
            r312.setScaleX(1.0f);
            r312.setScaleY(1.0f);
            r312.setTranslationX(0.0f);
            r312.setTranslationY(0.0f);
            r312.setProgressToFullscreen(1.0f);
            if (r312.getRenderer() != null) goto L70;
            r312.setAlpha(r52);
        L70:
            r113 = r113 + 1;
            goto L54
        L63:
            if ((r49 instanceof GroupCallGridCell) == false) goto L65;
            GroupCallGridCell r410 = (GroupCallGridCell) r49;
            float r6 = (r410.getLeft() + GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getX()) - GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).getLeft();
            float r411 = (r410.getTop() + GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getY()) - GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).getTop();
            float r7 = r312.getLeft() + this.this$0.fullscreenUsersListView.getX();
            float r11 = r312.getTop() + this.this$0.fullscreenUsersListView.getY();
        L66:
            float r62 = r6 - r7;
            float r72 = 1.0f - r52;
            r312.setTranslationX(r62 * r72);
            r312.setTranslationY((r411 - r11) * r72);
            r312.setScaleX(1.0f);
            r312.setScaleY(1.0f);
            r312.setProgressToFullscreen(r52);
            goto L70
        L65:
            r6 = (((r4.getLeft() + GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getX()) - GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).getLeft()) + r4.getAvatarImageView().getLeft()) + (r4.getAvatarImageView().getMeasuredWidth() >> 1);
            float r73 = (((r4.getTop() + GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getY()) - GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).getTop()) + r4.getAvatarImageView().getTop()) + (r4.getAvatarImageView().getMeasuredHeight() >> 1);
            float r114 = (r312.getLeft() + this.this$0.fullscreenUsersListView.getX()) + (r312.getMeasuredWidth() >> 1);
            float r122 = (r312.getTop() + this.this$0.fullscreenUsersListView.getY()) + (r312.getMeasuredHeight() >> 1);
            ((GroupCallUserCell) r49).setDrawAvatar(false);
            r411 = r73;
            r7 = r114;
            r11 = r122;
            goto L66
        L5:
            if (this.this$0.drawingForBlur == true) goto L16;
            if (Build.VERSION.SDK_INT < 31) goto L16;
            if (r21.isHardwareAccelerated() == false) goto L16;
            if (AndroidUtilities.makingGlobalBlurBitmap == true) goto L16;
            if (GroupCallActivity.m15186$$Nest$fgetrenderNodeBlur(this.this$0) != null) goto L15;
            GroupCallActivity.m15249$$Nest$fputrenderNodeBlur(this.this$0, WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("CallActivity.Blur"));
            GroupCallActivity.m15250$$Nest$fputrenderNodeBlurScale(this.this$0, SizeNotifierFrameLayout.getRenderNodeScale());
            ColorMatrix r115 = new ColorMatrix(new float[]{0.5f, 0.0f, 0.0f, 0.0f, 8.5f, 0.0f, 0.5f, 0.0f, 0.0f, 8.5f, 0.0f, 0.0f, 0.5f, 0.0f, 8.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
            float r313 = SizeNotifierFrameLayout.getBlurRadius();
            GroupCallActivity.m15186$$Nest$fgetrenderNodeBlur(this.this$0).setRenderEffect(RenderEffect.createChainEffect(RenderEffect.createBlurEffect(r313, r313, CameraView$$ExternalSyntheticApiModelOutline0.m()), RenderEffect.createColorFilterEffect(new ColorMatrixColorFilter(r115))));
            GroupCallActivity.m15113$$Nest$fgetgroupCallMessagesListView(this.this$0).setBlurRoot(this);
            GroupCallActivity.m15113$$Nest$fgetgroupCallMessagesListView(this.this$0).setRenderNode(GroupCallActivity.m15186$$Nest$fgetrenderNodeBlur(this.this$0), GroupCallActivity.m15187$$Nest$fgetrenderNodeBlurScale(this.this$0));
        L15:
            int r116 = Math.round(getMeasuredWidth() / GroupCallActivity.m15187$$Nest$fgetrenderNodeBlurScale(this.this$0));
            int r314 = Math.round(getMeasuredHeight() / GroupCallActivity.m15187$$Nest$fgetrenderNodeBlurScale(this.this$0));
            GroupCallActivity.m15239$$Nest$fputisInDrawRenderNodeBlur(this.this$0, true);
            GroupCallActivity.m15186$$Nest$fgetrenderNodeBlur(this.this$0).setPosition(0, 0, r116, r314);
            RecordingCanvas r117 = GroupCallActivity.m15186$$Nest$fgetrenderNodeBlur(this.this$0).beginRecording();
            r117.scale(1.0f / GroupCallActivity.m15187$$Nest$fgetrenderNodeBlurScale(this.this$0), 1.0f / GroupCallActivity.m15187$$Nest$fgetrenderNodeBlurScale(this.this$0));
            dispatchDraw(r117);
            GroupCallActivity.m15186$$Nest$fgetrenderNodeBlur(this.this$0).endRecording();
            GroupCallActivity.m15239$$Nest$fputisInDrawRenderNodeBlur(this.this$0, false);
            goto L16
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r7, View r8, long r9) {
            if (GroupCallActivity.m15120$$Nest$fgetisInDrawRenderNodeBlur(this.this$0) == false) goto L21;
            if (r8 != GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0)) goto L13;
            int r0 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildCount();
            int r2 = 0;
        L7:
            if (r2 >= r0) goto L13;
            View r3 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildAt(r2);
            if (r3.getVisibility() != 0) goto L11;
            r7.save();
            r7.translate(r3.getX(), r3.getY());
            r3.draw(r7);
            r7.restore();
        L11:
            r2 = r2 + 1;
        L13:
            if (r8 == GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0)) goto L19;
            if (r8 == GroupCallActivity.m15094$$Nest$fgetbuttonsContainer(this.this$0)) goto L19;
            return true;
        L19:
            return super.drawChild(r7, r8, r9);
        L21:
            if (GroupCallActivity.isTabletMode == false) goto L23;
        L37:
            GroupCallActivity r02 = this.this$0;
            if (r02.drawingForBlur == false) goto L44;
            if (r8 != GroupCallActivity.m15188$$Nest$fgetrenderersContainer(r02)) goto L44;
            r7.save();
            r7.translate(GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).getX() + this.this$0.fullscreenUsersListView.getX(), GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).getY() + this.this$0.fullscreenUsersListView.getY());
            this.this$0.fullscreenUsersListView.draw(r7);
            r7.restore();
            return true;
        L44:
            if (r8 != GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0)) goto L46;
        L63:
            return true;
        L46:
            if (r8 == GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0)) goto L63;
            if (r8 == GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0)) goto L63;
            if (GroupCallActivity.m15101$$Nest$fgetcontentFullyOverlayed(this.this$0) == false) goto L62;
            if (GroupCallActivity.m15226$$Nest$fgetuseBlur(this.this$0) == false) goto L62;
            if (r8 != GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0)) goto L57;
        L60:
            return true;
        L57:
            if (r8 == GroupCallActivity.m15094$$Nest$fgetbuttonsContainer(this.this$0)) goto L60;
            if (r8 == GroupCallActivity.m15113$$Nest$fgetgroupCallMessagesListView(this.this$0)) goto L60;
        L62:
            return super.drawChild(r7, r8, r9);
        L23:
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode != 1.0f) goto L37;
            if (r8 != GroupCallActivity.m15072$$Nest$fgetactionBar(this.this$0)) goto L27;
        L36:
            return true;
        L27:
            if (r8 == GroupCallActivity.m15074$$Nest$fgetactionBarShadow(this.this$0)) goto L36;
            if (r8 == GroupCallActivity.m15073$$Nest$fgetactionBarBackground(this.this$0)) goto L36;
            if (r8 == GroupCallActivity.m15221$$Nest$fgettitleTextView(this.this$0)) goto L36;
            if (r8 == GroupCallActivity.m15135$$Nest$fgetmenuItemsContainer(this.this$0)) goto L36;
            if (r8 != GroupCallActivity.m15218$$Nest$fgettextureLightningView(this.this$0)) goto L37;
            goto L36
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            GroupCallActivity.m15180$$Nest$fgetreactionEffectImageReceiver(this.this$0).onAttachedToWindow();
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            GroupCallActivity.m15180$$Nest$fgetreactionEffectImageReceiver(this.this$0).onDetachedFromWindow();
        }

        @Override // android.view.View
        public void onDraw(Canvas r12) {
            int r2 = AndroidUtilities.dp(74.0f);
            float r3 = GroupCallActivity.m15205$$Nest$fgetscrollOffsetY(this.this$0) - r2;
            int r4 = (getMeasuredHeight() + AndroidUtilities.dp(15.0f)) + GroupCallActivity.access$300(this.this$0);
            if ((GroupCallActivity.access$400(this.this$0) + r3) >= ActionBar.getCurrentActionBarHeight()) goto L5;
            float r5 = Math.min(1.0f, ((ActionBar.getCurrentActionBarHeight() - r3) - GroupCallActivity.access$600(this.this$0)) / ((r2 - GroupCallActivity.access$500(this.this$0)) - AndroidUtilities.dp(14.0f)));
            int r22 = (int) ((ActionBar.getCurrentActionBarHeight() - r2) * r5);
            r3 = r3 - r22;
            r4 = r4 + r22;
            float r23 = 1.0f - r5;
        L6:
            float r32 = r3 + getPaddingTop();
            GroupCallActivity.m15293$$Nest$mupdateTopBulletinY(this.this$0);
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode == 1.0f) goto L15;
            GroupCallActivity.m15207$$Nest$fgetshadowDrawable(this.this$0).setBounds(0, (int) r32, getMeasuredWidth(), r4);
            GroupCallActivity.m15207$$Nest$fgetshadowDrawable(this.this$0).draw(r12);
            if (r23 == 1.0f) goto L11;
            Theme.dialogs_onlineCirclePaint.setColor(GroupCallActivity.m15088$$Nest$fgetbackgroundColor(this.this$0));
            this.rect.set(GroupCallActivity.access$700(this.this$0), GroupCallActivity.access$800(this.this$0) + r32, getMeasuredWidth() - GroupCallActivity.access$900(this.this$0), (GroupCallActivity.access$1000(this.this$0) + r32) + AndroidUtilities.dp(24.0f));
            r12.drawRoundRect(this.rect, AndroidUtilities.dp(12.0f) * r23, AndroidUtilities.dp(12.0f) * r23, Theme.dialogs_onlineCirclePaint);
        L11:
            Theme.dialogs_onlineCirclePaint.setColor(Color.argb((int) (GroupCallActivity.m15072$$Nest$fgetactionBar(this.this$0).getAlpha() * 255.0f), (int) (Color.red(GroupCallActivity.m15088$$Nest$fgetbackgroundColor(this.this$0)) * 0.8f), (int) (Color.green(GroupCallActivity.m15088$$Nest$fgetbackgroundColor(this.this$0)) * 0.8f), (int) (Color.blue(GroupCallActivity.m15088$$Nest$fgetbackgroundColor(this.this$0)) * 0.8f)));
            r12.drawRect(GroupCallActivity.access$1100(this.this$0), 0.0f, getMeasuredWidth() - GroupCallActivity.access$1200(this.this$0), this.this$0.getStatusBarHeight(), Theme.dialogs_onlineCirclePaint);
            PrivateVideoPreviewDialog r1 = this.this$0.previewDialog;
            if (r1 == null) goto L15;
            Theme.dialogs_onlineCirclePaint.setColor(r1.getBackgroundColor());
            r12.drawRect(GroupCallActivity.access$1300(this.this$0), 0.0f, getMeasuredWidth() - GroupCallActivity.access$1400(this.this$0), this.this$0.getStatusBarHeight(), Theme.dialogs_onlineCirclePaint);
        L15:
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode == 0.0f) goto L18;
            Theme.dialogs_onlineCirclePaint.setColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_voipgroup_actionBar), (int) (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode * 255.0f)));
            r12.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), Theme.dialogs_onlineCirclePaint);
        L18:
            if (this.this$0.isRtmpStream() == true) goto L20;
        L29:
            GroupCallActivity.m15180$$Nest$fgetreactionEffectImageReceiver(this.this$0).setImageCoords((getMeasuredWidth() / 2.0f) - AndroidUtilities.dp(30.0f), (getMeasuredHeight() / 2.0f) - AndroidUtilities.dp(30.0f), AndroidUtilities.dp(60.0f), AndroidUtilities.dp(60.0f));
            GroupCallActivity.m15180$$Nest$fgetreactionEffectImageReceiver(this.this$0).draw(r12);
            return;
        L20:
            if (LiteMode.isEnabled(512) == false) goto L29;
            double r24 = GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode;
            GroupCallActivity r33 = this.this$0;
            if (r24 >= 0.15d) goto L27;
            if (GroupCallActivity.m15140$$Nest$fgetneedTextureLightning(r33) == true) goto L29;
            GroupCallActivity.m15243$$Nest$fputneedTextureLightning(this.this$0, true);
            GroupCallActivity.m15280$$Nest$mrunUpdateTextureLightningRunnable(this.this$0);
            goto L29
        L27:
            if (GroupCallActivity.m15140$$Nest$fgetneedTextureLightning(r33) == false) goto L29;
            GroupCallActivity.m15243$$Nest$fputneedTextureLightning(this.this$0, false);
            AndroidUtilities.cancelRunOnUIThread(GroupCallActivity.m15225$$Nest$fgetupdateTextureLightningRunnable(this.this$0));
            goto L29
        L5:
            r23 = 1.0f;
            goto L6
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r11) {
            if (GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0) == null) goto L13;
            if (r11.getAction() != 0) goto L13;
            float r0 = r11.getX();
            float r2 = r11.getY();
            this.rect.set(GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).getX(), GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).getY(), GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).getX() + GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).getMeasuredWidth(), GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).getY() + GroupCallActivity.m15201$$Nest$fgetscrimPopupLayout(this.this$0).getMeasuredHeight());
            boolean r3 = !this.rect.contains(r0, r2);
            this.rect.set(GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getX(), GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getY(), GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getX() + GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getMeasuredWidth(), (GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getY() + GroupCallActivity.m15085$$Nest$fgetavatarPreviewContainer(this.this$0).getMeasuredWidth()) + GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0).getMeasuredHeight());
            if (this.rect.contains(r0, r2) == false) goto L9;
            r3 = false;
        L9:
            if (r3 == false) goto L13;
            GroupCallActivity.m15270$$Nest$mdismissAvatarPreview(this.this$0, true);
            return true;
        L13:
            if (r11.getAction() != 0) goto L29;
            if (GroupCallActivity.m15205$$Nest$fgetscrollOffsetY(this.this$0) == 0.0f) goto L29;
            if (r11.getY() >= (GroupCallActivity.m15205$$Nest$fgetscrollOffsetY(this.this$0) - AndroidUtilities.dp(37.0f))) goto L29;
            if (GroupCallActivity.m15072$$Nest$fgetactionBar(this.this$0).getAlpha() != 0.0f) goto L29;
            if (GroupCallActivity.m15086$$Nest$fgetavatarsPreviewShowed(this.this$0) == true) goto L29;
            GroupCallActivity r02 = this.this$0;
            if (r02.previewDialog != null) goto L29;
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(r02).inFullscreenMode == true) goto L29;
            this.this$0.dismiss();
            return true;
        L29:
            return super.onInterceptTouchEvent(r11);
        }

        @Override // android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int r2, KeyEvent r3) {
            if (GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0) == null) goto L9;
            if (r2 != 4) goto L9;
            GroupCallActivity.m15270$$Nest$mdismissAvatarPreview(this.this$0, true);
            return true;
        L9:
            return super.onKeyDown(r2, r3);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r7, int r8, int r9, int r10, int r11) {
            if (GroupCallActivity.isTabletMode == true) goto L5;
        L9:
            boolean r4 = false;
            float r0 = 0.0f;
        L10:
            this.localHasVideo = GroupCallActivity.m15117$$Nest$fgethasVideo(this.this$0);
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).inLayout = true;
            super.onLayout(r7, r8, r9, r10, r11);
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).inLayout = false;
            GroupCallActivity.m15289$$Nest$mupdateLayout(this.this$0, false);
            this.wasLayout = true;
            if (r4 == true) goto L13;
            return;
        L13:
            if (GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getLeft() == r0) goto L17;
            float r02 = r0 - GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getLeft();
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).setTranslationX(r02);
            GroupCallActivity.m15094$$Nest$fgetbuttonsContainer(this.this$0).setTranslationX(r02);
            GroupCallActivity.m15092$$Nest$fgetbuttonsBackgroundGradientView(this.this$0).setTranslationX(r02);
            GroupCallActivity.m15093$$Nest$fgetbuttonsBackgroundGradientView2(this.this$0).setTranslationX(r02);
            ViewPropertyAnimator r72 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).animate().translationX(0.0f).setDuration(350);
            CubicBezierInterpolator r102 = CubicBezierInterpolator.DEFAULT;
            r72.setInterpolator(r102).start();
            GroupCallActivity.m15092$$Nest$fgetbuttonsBackgroundGradientView(this.this$0).animate().translationX(0.0f).setDuration(350).setInterpolator(r102).start();
            GroupCallActivity.m15093$$Nest$fgetbuttonsBackgroundGradientView2(this.this$0).animate().translationX(0.0f).setDuration(350).setInterpolator(r102).start();
            GroupCallActivity.m15094$$Nest$fgetbuttonsContainer(this.this$0).animate().translationX(0.0f).setDuration(350).setInterpolator(r102).start();
            return;
        L17:
            return;
        L5:
            if (this.localHasVideo == GroupCallActivity.m15117$$Nest$fgethasVideo(this.this$0)) goto L9;
            if (this.wasLayout == false) goto L9;
            r0 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getX();
            r4 = true;
            goto L10
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r20, int r21) {
            int r1 = View.MeasureSpec.getSize(r21);
            this.ignoreLayout = true;
            if (View.MeasureSpec.getSize(r20) > r1) goto L5;
        L7:
            boolean r3 = false;
        L8:
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).listWidth = View.MeasureSpec.getSize(r20);
            if (AndroidUtilities.isTablet() == true) goto L11;
        L15:
            boolean r5 = false;
        L16:
            int r7 = 6;
            if (GroupCallActivity.isLandscapeMode == r3) goto L42;
            GroupCallActivity.isLandscapeMode = r3;
            if (GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).getMeasuredWidth() != 0) goto L21;
            int r6 = GroupCallActivity.m15137$$Nest$fgetmuteButton(this.this$0).getLayoutParams().width;
        L21:
            GroupCallActivity.m15275$$Nest$minvalidateLayoutFullscreen(this.this$0);
            FillLastGridLayoutManager r62 = GroupCallActivity.m15123$$Nest$fgetlayoutManager(this.this$0);
            if (GroupCallActivity.isLandscapeMode == false) goto L24;
            int r10 = 6;
        L25:
            r62.setSpanCount(r10);
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).invalidateItemDecorations();
            this.this$0.fullscreenUsersListView.invalidateItemDecorations();
            this.updateRenderers = true;
            if (GroupCallActivity.m15190$$Nest$fgetscheduleInfoTextView(this.this$0) == null) goto L33;
            TextView r63 = GroupCallActivity.m15190$$Nest$fgetscheduleInfoTextView(this.this$0);
            if (GroupCallActivity.isLandscapeMode == true) goto L30;
            int r102 = 0;
        L31:
            r63.setVisibility(r102);
            goto L33
        L30:
            r102 = 8;
        L33:
            if (this.this$0.isRtmpLandscapeMode() != r3) goto L42;
            if (this.this$0.isRtmpStream() == false) goto L42;
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).inFullscreenMode == true) goto L42;
            if (this.this$0.call.visibleVideoParticipants.isEmpty() == true) goto L42;
            GroupCallActivity r32 = this.this$0;
            r32.fullscreenFor(r32.call.visibleVideoParticipants.get(0));
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).delayHideUi();
            goto L42
        L24:
            r10 = 2;
        L42:
            if (GroupCallActivity.isTabletMode == r5) goto L49;
            GroupCallActivity.isTabletMode = r5;
            RecyclerListView r33 = this.this$0.tabletVideoGridView;
            if (r5 == false) goto L46;
            int r52 = 0;
        L47:
            r33.setVisibility(r52);
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).invalidateItemDecorations();
            this.this$0.fullscreenUsersListView.invalidateItemDecorations();
            this.updateRenderers = true;
            goto L49
        L46:
            r52 = 8;
        L49:
            if (this.updateRenderers == false) goto L95;
            GroupCallActivity.m15264$$Nest$mapplyCallParticipantUpdates(this.this$0, true);
            GroupCallActivity.m15127$$Nest$fgetlistAdapter(this.this$0).notifyDataSetChanged();
            GroupCallActivity r34 = this.this$0;
            r34.fullscreenAdapter.update(false, r34.tabletVideoGridView);
            if (GroupCallActivity.isTabletMode == false) goto L53;
            GroupCallActivity r35 = this.this$0;
            r35.tabletGridAdapter.update(false, r35.tabletVideoGridView);
        L53:
            RecyclerListView r36 = this.this$0.tabletVideoGridView;
            if (GroupCallActivity.isTabletMode == false) goto L56;
            int r53 = 0;
        L57:
            r36.setVisibility(r53);
            GroupCallActivity r37 = this.this$0;
            GroupCallTabletGridAdapter r54 = r37.tabletGridAdapter;
            RecyclerListView r64 = r37.tabletVideoGridView;
            if (GroupCallActivity.isTabletMode == true) goto L60;
        L62:
            boolean r38 = false;
        L63:
            r54.setVisibility(r64, r38, true);
            GroupCallActivity r39 = this.this$0;
            if (GroupCallActivity.isTabletMode == true) goto L66;
        L69:
            boolean r55 = true;
        L70:
            GroupCallActivity.m15241$$Nest$fputlistViewVideoVisibility(r39, r55);
            if (GroupCallActivity.isTabletMode == false) goto L73;
        L75:
            boolean r310 = false;
        L76:
            GroupCallActivity r56 = this.this$0;
            r56.fullscreenAdapter.setVisibility(r56.fullscreenUsersListView, r310);
            RecyclerListView r57 = this.this$0.fullscreenUsersListView;
            if (r310 == false) goto L79;
            int r311 = 0;
        L80:
            r57.setVisibility(r311);
            RecyclerListView r312 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0);
            if (GroupCallActivity.isTabletMode == false) goto L83;
        L86:
            int r58 = 0;
        L87:
            r312.setVisibility(r58);
            FillLastGridLayoutManager r313 = GroupCallActivity.m15123$$Nest$fgetlayoutManager(this.this$0);
            if (GroupCallActivity.isLandscapeMode == true) goto L91;
            r7 = 2;
        L91:
            r313.setSpanCount(r7);
            GroupCallActivity.m15291$$Nest$mupdateState(this.this$0, false, false);
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).invalidateItemDecorations();
            this.this$0.fullscreenUsersListView.invalidateItemDecorations();
            AndroidUtilities.updateVisibleRows(GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0));
            this.updateRenderers = false;
            GroupCallActivity.m15083$$Nest$fgetattachedRenderersTmp(this.this$0).clear();
            GroupCallActivity.m15083$$Nest$fgetattachedRenderersTmp(this.this$0).addAll(GroupCallActivity.m15082$$Nest$fgetattachedRenderers(this.this$0));
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).setIsTablet(GroupCallActivity.isTabletMode);
            int r314 = 0;
        L93:
            if (r314 >= GroupCallActivity.m15083$$Nest$fgetattachedRenderersTmp(this.this$0).size()) goto L95;
            ((GroupCallMiniTextureView) GroupCallActivity.m15083$$Nest$fgetattachedRenderersTmp(this.this$0).get(r314)).updateAttachState(true);
            r314 = r314 + 1;
            goto L93
        L83:
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).inFullscreenMode == false) goto L86;
            r58 = 8;
            goto L87
        L79:
            r311 = 8;
            goto L80
        L73:
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).inFullscreenMode == false) goto L75;
            r310 = true;
            goto L76
        L66:
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(r39).inFullscreenMode == true) goto L69;
            r55 = false;
            goto L70
        L60:
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(r37).inFullscreenMode == true) goto L62;
            r38 = true;
            goto L63
        L56:
            r53 = 8;
        L95:
            int r315 = r1 - getPaddingTop();
            if (this.this$0.isRtmpStream() == false) goto L99;
            int r59 = AndroidUtilities.dp(72.0f);
        L98:
            int r316 = r315 - r59;
            FrameLayout.LayoutParams r510 = (FrameLayout.LayoutParams) GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).getLayoutParams();
            if (GroupCallActivity.isTabletMode == false) goto L103;
            r510.topMargin = ActionBar.getCurrentActionBarHeight();
        L104:
            int r511 = 0;
        L105:
            GroupCallActivity r65 = this.this$0;
            if (r511 >= 2) goto L112;
            FrameLayout.LayoutParams r66 = (FrameLayout.LayoutParams) GroupCallActivity.m15222$$Nest$fgetundoView(r65)[r511].getLayoutParams();
            if (GroupCallActivity.isTabletMode == false) goto L110;
            r66.rightMargin = AndroidUtilities.dp(328.0f);
        L111:
            r511 = r511 + 1;
            goto L105
        L110:
            r66.rightMargin = AndroidUtilities.dp(8.0f);
            goto L111
        L112:
            RecyclerListView r512 = r65.tabletVideoGridView;
            if (r512 == null) goto L116;
            ((FrameLayout.LayoutParams) r512.getLayoutParams()).topMargin = ActionBar.getCurrentActionBarHeight();
        L116:
            if (GroupCallActivity.m15097$$Nest$fgetcallMessageEnterView(this.this$0).getEmojiView() == null) goto L119;
            ((FrameLayout.LayoutParams) GroupCallActivity.m15097$$Nest$fgetcallMessageEnterView(this.this$0).getEmojiView().getLayoutParams()).gravity = 80;
        L119:
            if (this.this$0.isRtmpStream() == false) goto L121;
            float r513 = 40.0f;
        L122:
            int r514 = AndroidUtilities.dp(r513);
            FrameLayout.LayoutParams r12 = (FrameLayout.LayoutParams) GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getLayoutParams();
            if (GroupCallActivity.isTabletMode == false) goto L130;
            if (GroupCallActivity.m15117$$Nest$fgethasVideo(this.this$0) == false) goto L127;
            int r13 = 5;
        L128:
            r12.gravity = r13;
            r12.width = AndroidUtilities.dp(320.0f);
            int r132 = AndroidUtilities.dp(4.0f);
            r12.leftMargin = r132;
            r12.rightMargin = r132;
            r12.bottomMargin = r514;
            r12.topMargin = ActionBar.getCurrentActionBarHeight();
            int r122 = AndroidUtilities.dp(60.0f);
        L133:
            int r133 = 85;
            if (GroupCallActivity.isLandscapeMode == true) goto L136;
        L138:
            GroupCallActivity.m15092$$Nest$fgetbuttonsBackgroundGradientView(this.this$0).setVisibility(0);
            FrameLayout.LayoutParams r8 = (FrameLayout.LayoutParams) GroupCallActivity.m15092$$Nest$fgetbuttonsBackgroundGradientView(this.this$0).getLayoutParams();
            r8.bottomMargin = r514;
            if (GroupCallActivity.isTabletMode == true) goto L141;
            r8.width = -1;
        L146:
            GroupCallActivity.m15093$$Nest$fgetbuttonsBackgroundGradientView2(this.this$0).setVisibility(0);
            FrameLayout.LayoutParams r82 = (FrameLayout.LayoutParams) GroupCallActivity.m15093$$Nest$fgetbuttonsBackgroundGradientView2(this.this$0).getLayoutParams();
            r82.height = r514;
            if (GroupCallActivity.isTabletMode == true) goto L149;
            r82.width = -1;
        L154:
            boolean r515 = GroupCallActivity.isLandscapeMode;
            GroupCallActivity r83 = this.this$0;
            if (r515 == false) goto L157;
            r83.fullscreenUsersListView.setPadding(0, AndroidUtilities.dp(9.0f), 0, AndroidUtilities.dp(9.0f));
        L158:
            FrameLayout.LayoutParams r516 = (FrameLayout.LayoutParams) GroupCallActivity.m15094$$Nest$fgetbuttonsContainer(this.this$0).getLayoutParams();
            if (GroupCallActivity.isTabletMode == false) goto L166;
            r516.width = AndroidUtilities.dp(320.0f);
            r516.height = AndroidUtilities.dp(120.0f);
            if (GroupCallActivity.m15117$$Nest$fgethasVideo(this.this$0) == true) goto L164;
            r133 = 81;
        L164:
            r516.gravity = r133;
            r516.rightMargin = 0;
        L170:
            if (GroupCallActivity.isLandscapeMode == true) goto L172;
        L174:
            ((FrameLayout.LayoutParams) GroupCallActivity.m15072$$Nest$fgetactionBar(this.this$0).getLayoutParams()).rightMargin = 0;
            ((FrameLayout.LayoutParams) GroupCallActivity.m15135$$Nest$fgetmenuItemsContainer(this.this$0).getLayoutParams()).rightMargin = 0;
            ((FrameLayout.LayoutParams) GroupCallActivity.m15073$$Nest$fgetactionBarBackground(this.this$0).getLayoutParams()).rightMargin = 0;
            ((FrameLayout.LayoutParams) GroupCallActivity.m15074$$Nest$fgetactionBarShadow(this.this$0).getLayoutParams()).rightMargin = 0;
        L175:
            FrameLayout.LayoutParams r517 = (FrameLayout.LayoutParams) this.this$0.fullscreenUsersListView.getLayoutParams();
            boolean r84 = GroupCallActivity.isLandscapeMode;
            GroupCallActivity r103 = this.this$0;
            if (r84 == false) goto L182;
            if (((LinearLayoutManager) r103.fullscreenUsersListView.getLayoutManager()).getOrientation() == 1) goto L180;
            ((LinearLayoutManager) this.this$0.fullscreenUsersListView.getLayoutManager()).setOrientation(1);
        L180:
            r517.height = -1;
            r517.width = AndroidUtilities.dp(80.0f);
            r517.gravity = 53;
            r517.rightMargin = AndroidUtilities.dp(100.0f);
            r517.bottomMargin = 0;
        L185:
            ((FrameLayout.LayoutParams) GroupCallActivity.m15074$$Nest$fgetactionBarShadow(this.this$0).getLayoutParams()).topMargin = ActionBar.getCurrentActionBarHeight();
            GroupCallActivity.m15096$$Nest$fgetcallMessageEnterUnderContainer(this.this$0).invalidate();
            FrameLayout.LayoutParams r518 = (FrameLayout.LayoutParams) GroupCallActivity.m15096$$Nest$fgetcallMessageEnterUnderContainer(this.this$0).getLayoutParams();
            r518.height = r1;
            r518.topMargin = -getPaddingTop();
            if (GroupCallActivity.m15097$$Nest$fgetcallMessageEnterView(this.this$0).getEmojiView() == null) goto L188;
            ((FrameLayout.LayoutParams) GroupCallActivity.m15097$$Nest$fgetcallMessageEnterView(this.this$0).getEmojiView().getLayoutParams()).bottomMargin = -getPaddingBottom();
        L188:
            int r519 = Math.max(AndroidUtilities.dp(259.0f), (r316 / 5) * 3);
            if (GroupCallActivity.isTabletMode == false) goto L191;
            int r520 = 0;
        L193:
            if (GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getPaddingTop() == r520) goto L195;
        L196:
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).setPadding(0, r520, 0, r122);
        L198:
            if (GroupCallActivity.m15227$$Nest$fgetwatchersView(this.this$0) == null) goto L203;
            FrameLayout.LayoutParams r67 = (FrameLayout.LayoutParams) GroupCallActivity.m15227$$Nest$fgetwatchersView(this.this$0).getLayoutParams();
            GroupCallGridCell r72 = GroupCallActivity.m15272$$Nest$mfindGroupCallGridCell(this.this$0);
            if (r72 == null) goto L203;
            int r85 = (GroupCallActivity.m15094$$Nest$fgetbuttonsContainer(this.this$0).getTop() + (GroupCallActivity.m15094$$Nest$fgetbuttonsContainer(this.this$0).getMeasuredHeight() / 2)) - (GroupCallActivity.m15124$$Nest$fgetleaveButton(this.this$0).getMeasuredHeight() / 2);
            int r9 = (ActionBar.getCurrentActionBarHeight() + r520) + r72.getMeasuredHeight();
            r67.topMargin = (r9 + ((r85 - r9) / 2)) - AndroidUtilities.dp(32.0f);
            r67.height = AndroidUtilities.dp(70.0f);
        L203:
            if (GroupCallActivity.m15218$$Nest$fgettextureLightningView(this.this$0) == null) goto L208;
            FrameLayout.LayoutParams r68 = (FrameLayout.LayoutParams) GroupCallActivity.m15218$$Nest$fgettextureLightningView(this.this$0).getLayoutParams();
            GroupCallGridCell r73 = GroupCallActivity.m15272$$Nest$mfindGroupCallGridCell(this.this$0);
            if (r73 == null) goto L208;
            r68.height = r73.getMeasuredHeight() - AndroidUtilities.dp(14.0f);
            r68.width = r73.getMeasuredWidth() - AndroidUtilities.dp(7.0f);
            int r74 = AndroidUtilities.dp(16.0f);
            r68.leftMargin = r74;
            r68.rightMargin = r74;
        L208:
            if (GroupCallActivity.m15192$$Nest$fgetscheduleStartAtTextView(this.this$0) == null) goto L216;
            int r521 = r520 + (((r316 - r520) + AndroidUtilities.dp(60.0f)) / 2);
            FrameLayout.LayoutParams r317 = (FrameLayout.LayoutParams) GroupCallActivity.m15193$$Nest$fgetscheduleStartInTextView(this.this$0).getLayoutParams();
            r317.topMargin = r521 - AndroidUtilities.dp(30.0f);
            FrameLayout.LayoutParams r69 = (FrameLayout.LayoutParams) GroupCallActivity.m15192$$Nest$fgetscheduleStartAtTextView(this.this$0).getLayoutParams();
            r69.topMargin = AndroidUtilities.dp(80.0f) + r521;
            FrameLayout.LayoutParams r75 = (FrameLayout.LayoutParams) GroupCallActivity.m15194$$Nest$fgetscheduleTimeTextView(this.this$0).getLayoutParams();
            if (r317.topMargin >= ActionBar.getCurrentActionBarHeight()) goto L212;
        L215:
            GroupCallActivity.m15193$$Nest$fgetscheduleStartInTextView(this.this$0).setVisibility(4);
            GroupCallActivity.m15192$$Nest$fgetscheduleStartAtTextView(this.this$0).setVisibility(4);
            r75.topMargin = r521 - AndroidUtilities.dp(20.0f);
            goto L216
        L212:
            if ((r69.topMargin + AndroidUtilities.dp(20.0f)) > (r1 - AndroidUtilities.dp(231.0f))) goto L215;
            GroupCallActivity.m15193$$Nest$fgetscheduleStartInTextView(this.this$0).setVisibility(0);
            GroupCallActivity.m15192$$Nest$fgetscheduleStartAtTextView(this.this$0).setVisibility(0);
            r75.topMargin = r521;
        L216:
            int r318 = 0;
        L218:
            if (r318 >= GroupCallActivity.m15082$$Nest$fgetattachedRenderers(this.this$0).size()) goto L220;
            ((GroupCallMiniTextureView) GroupCallActivity.m15082$$Nest$fgetattachedRenderers(this.this$0).get(r318)).setFullscreenMode(GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).inFullscreenMode, true);
            r318 = r318 + 1;
            goto L218
        L220:
            this.ignoreLayout = false;
            super.onMeasure(r20, View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30));
            int r14 = getMeasuredHeight() + (getMeasuredWidth() << 16);
            if (r14 == this.lastSize) goto L223;
            this.lastSize = r14;
            GroupCallActivity.m15270$$Nest$mdismissAvatarPreview(this.this$0, false);
        L223:
            this.this$0.cellFlickerDrawable.setParentWidth(getMeasuredWidth());
            GroupCallActivity.m15268$$Nest$mcheckGroupCallUiPositions_MessagesList(this.this$0);
            return;
        L195:
            if (GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getPaddingBottom() == r122) goto L198;
        L191:
            r520 = Math.max(0, (r316 - r519) + AndroidUtilities.dp(8.0f));
            goto L193
        L182:
            if (((LinearLayoutManager) r103.fullscreenUsersListView.getLayoutManager()).getOrientation() == 0) goto L184;
            ((LinearLayoutManager) this.this$0.fullscreenUsersListView.getLayoutManager()).setOrientation(0);
        L184:
            r517.height = AndroidUtilities.dp(80.0f);
            r517.width = -1;
            r517.gravity = 80;
            r517.rightMargin = 0;
            r517.bottomMargin = AndroidUtilities.dp(100.0f);
            goto L185
        L172:
            if (GroupCallActivity.isTabletMode == true) goto L174;
            ((FrameLayout.LayoutParams) GroupCallActivity.m15072$$Nest$fgetactionBar(this.this$0).getLayoutParams()).rightMargin = AndroidUtilities.dp(90.0f);
            ((FrameLayout.LayoutParams) GroupCallActivity.m15135$$Nest$fgetmenuItemsContainer(this.this$0).getLayoutParams()).rightMargin = AndroidUtilities.dp(90.0f);
            ((FrameLayout.LayoutParams) GroupCallActivity.m15073$$Nest$fgetactionBarBackground(this.this$0).getLayoutParams()).rightMargin = AndroidUtilities.dp(90.0f);
            ((FrameLayout.LayoutParams) GroupCallActivity.m15074$$Nest$fgetactionBarShadow(this.this$0).getLayoutParams()).rightMargin = AndroidUtilities.dp(90.0f);
            goto L175
        L166:
            if (GroupCallActivity.isLandscapeMode == false) goto L168;
            r516.width = AndroidUtilities.dp(90.0f);
            r516.height = -1;
            r516.gravity = 53;
            goto L170
        L168:
            r516.width = -1;
            r516.height = AndroidUtilities.dp(120.0f);
            r516.gravity = 81;
            r516.rightMargin = 0;
            goto L170
        L157:
            r83.fullscreenUsersListView.setPadding(AndroidUtilities.dp(9.0f), 0, AndroidUtilities.dp(9.0f), 0);
            goto L158
        L149:
            if (GroupCallActivity.m15117$$Nest$fgethasVideo(this.this$0) == false) goto L151;
            int r522 = 85;
        L152:
            r82.gravity = r522;
            r82.width = AndroidUtilities.dp(328.0f);
            goto L154
        L151:
            r522 = 81;
            goto L152
        L141:
            if (GroupCallActivity.m15117$$Nest$fgethasVideo(this.this$0) == false) goto L143;
            int r92 = 85;
        L144:
            r8.gravity = r92;
            r8.width = AndroidUtilities.dp(328.0f);
            goto L146
        L143:
            r92 = 81;
            goto L144
        L136:
            if (GroupCallActivity.isTabletMode == true) goto L138;
            GroupCallActivity.m15092$$Nest$fgetbuttonsBackgroundGradientView(this.this$0).setVisibility(8);
            GroupCallActivity.m15093$$Nest$fgetbuttonsBackgroundGradientView2(this.this$0).setVisibility(8);
            goto L154
        L127:
            r13 = 1;
            goto L128
        L130:
            if (GroupCallActivity.isLandscapeMode == false) goto L132;
            r12.gravity = 51;
            r12.width = -1;
            r12.topMargin = ActionBar.getCurrentActionBarHeight();
            r12.bottomMargin = AndroidUtilities.dp(14.0f);
            r12.rightMargin = AndroidUtilities.dp(90.0f);
            r12.leftMargin = AndroidUtilities.dp(14.0f);
            r122 = 0;
            goto L133
        L132:
            r12.gravity = 51;
            r12.width = -1;
            int r93 = AndroidUtilities.dp(60.0f);
            r12.bottomMargin = r514;
            r12.topMargin = ActionBar.getCurrentActionBarHeight() + AndroidUtilities.dp(14.0f);
            int r134 = AndroidUtilities.dp(14.0f);
            r12.leftMargin = r134;
            r12.rightMargin = r134;
            r122 = r93;
            goto L133
        L121:
            r513 = 90.0f;
            goto L122
        L103:
            r510.topMargin = 0;
            goto L104
        L99:
            r59 = AndroidUtilities.dp(245.0f);
            goto L98
        L11:
            if (View.MeasureSpec.getSize(r20) <= r1) goto L15;
            if (this.this$0.isRtmpStream() == true) goto L15;
            r5 = true;
            goto L16
        L5:
            if (AndroidUtilities.isTablet() == true) goto L7;
            r3 = true;
            goto L8
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.isDismissed() == false) goto L5;
            return false;
        L5:
            if (super.onTouchEvent(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            GroupCallActivity.m15293$$Nest$mupdateTopBulletinY(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 extends SimpleTextView {
        private float duration;
        private float gradientWidth;
        private int lastTextWidth;
        private long lastUpdateTime;
        private LinearGradient linearGradient;
        private Matrix matrix;
        private float startX;
        private float targetX;
        final /* synthetic */ GroupCallActivity this$0;
        private float time;

        public AnonymousClass8(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.matrix = new Matrix();
            this.targetX = -1.0f;
        }

        private void setTarget() {
            this.targetX = ((Utilities.random.nextInt(100) - 50) * 0.2f) / 50.0f;
        }

        @Override // org.telegram.ui.ActionBar.SimpleTextView
        public boolean createLayout(int r12) {
            boolean r122 = super.createLayout(r12);
            int r0 = getTextWidth();
            if (r0 == this.lastTextWidth) goto L5;
            float r1 = r0;
            this.gradientWidth = 1.3f * r1;
            float r5 = getTextHeight();
            float r6 = r1 * 2.0f;
            int r13 = Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient);
            int r2 = Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient3);
            int r4 = Theme.key_voipgroup_mutedByAdminGradient2;
            this.linearGradient = new LinearGradient(0.0f, r5, r6, 0.0f, new int[]{r13, r2, Theme.getColor(r4), Theme.getColor(r4)}, new float[]{0.0f, 0.38f, 0.76f, 1.0f}, Shader.TileMode.CLAMP);
            getPaint().setShader(this.linearGradient);
            this.lastTextWidth = r0;
        L5:
            return r122;
        }

        @Override // org.telegram.ui.ActionBar.SimpleTextView, android.view.View
        public void onDraw(Canvas r9) {
            if (this.linearGradient == null) goto L30;
            ChatObject.Call r0 = this.this$0.call;
            if (r0 != null) goto L7;
        L14:
            float r4 = 0.0f;
        L15:
            this.matrix.reset();
            this.matrix.postTranslate(((-this.lastTextWidth) * 0.7f) * r4, 0.0f);
            long r2 = SystemClock.elapsedRealtime();
            long r42 = r2 - this.lastUpdateTime;
            if (r42 <= 20) goto L18;
            r42 = 17;
        L18:
            this.lastUpdateTime = r2;
            float r02 = this.duration;
            if (r02 != 0.0f) goto L21;
        L22:
            this.duration = Utilities.random.nextInt(200) + 1500;
            this.time = 0.0f;
            if (this.targetX != (-1.0f)) goto L25;
            setTarget();
        L25:
            this.startX = this.targetX;
            setTarget();
        L26:
            float r22 = r42;
            float r03 = this.time + (((BlobDrawable.GRADIENT_SPEED_MIN + 0.5f) * r22) + ((r22 * (BlobDrawable.GRADIENT_SPEED_MAX * 2.0f)) * GroupCallActivity.m15076$$Nest$fgetamplitude(this.this$0)));
            this.time = r03;
            float r23 = this.duration;
            if (r03 <= r23) goto L29;
            this.time = r23;
        L29:
            float r04 = CubicBezierInterpolator.EASE_OUT.getInterpolation(this.time / r23);
            float r24 = this.gradientWidth;
            float r3 = this.startX;
            this.matrix.postTranslate(((r3 + ((this.targetX - r3) * r04)) * r24) - (r24 / 2.0f), 0.0f);
            this.linearGradient.setLocalMatrix(this.matrix);
            invalidate();
            goto L30
        L21:
            if (this.time < r02) goto L26;
        L7:
            if (r0.isScheduled() == false) goto L14;
            long r25 = (r0.call.call.schedule_date * 1000) - GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getConnectionsManager().getCurrentTimeMillis();
            r4 = 1.0f;
            if (r25 < 0) goto L15;
            if (r25 >= 5000) goto L14;
            r4 = 1.0f - (r25 / 5000.0f);
        L30:
            super.onDraw(r9);
        }
    }

    /* renamed from: org.telegram.ui.GroupCallActivity$9, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass9 extends RecyclerListView {
        final /* synthetic */ GroupCallActivity this$0;
        private final LongSparseIntArray visiblePeerTmp;

        public AnonymousClass9(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.visiblePeerTmp = new LongSparseIntArray();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r18) {
            int r5 = 1;
            if (GroupCallActivity.m15121$$Nest$fgetitemAnimator(this.this$0).outMinTop == Float.MAX_VALUE) goto L5;
            boolean r2 = true;
        L6:
            this.visiblePeerTmp.clear();
            int r6 = 0;
        L8:
            if (r6 >= this.this$0.visiblePeerIds.size()) goto L10;
            this.visiblePeerTmp.put(this.this$0.visiblePeerIds.keyAt(r6), 1);
            r6 = r6 + 1;
            goto L8
        L10:
            this.this$0.visiblePeerIds.clear();
            int r62 = getChildCount();
            float r10 = Float.MAX_VALUE;
            int r8 = 0;
            int r9 = 0;
            float r11 = 0.0f;
        L11:
            if (r8 >= r62) goto L41;
            View r12 = getChildAt(r8);
            RecyclerView.ViewHolder r13 = findContainingViewHolder(r12);
            if (r13 != null) goto L15;
        L24:
            int r15 = r62;
            int r16 = r8;
            int r82 = r5;
        L40:
            int r63 = r82;
            r8 = r16 + 1;
            r5 = r63;
            r62 = r15;
            goto L11
        L15:
            if (r13.getItemViewType() == 3) goto L24;
            if (r13.getItemViewType() == 4) goto L24;
            if (r13.getItemViewType() == 5) goto L24;
            if (r13.getItemViewType() == 6) goto L24;
            if (r13.getItemViewType() == 7) goto L24;
            if (r13.getItemViewType() != r5) goto L33;
            View r14 = r13.itemView;
            if ((r14 instanceof GroupCallUserCell) == false) goto L33;
            GroupCallUserCell r142 = (GroupCallUserCell) r14;
            r16 = r8;
            this.this$0.visiblePeerIds.append(r142.getPeerId(), r5);
            r82 = r5;
            r15 = r62;
            if (this.visiblePeerTmp.get(r142.getPeerId(), 0) != 0) goto L32;
            r9 = r82;
        L34:
            if (r2 == true) goto L36;
            r11 = Math.max(r11, r12.getY() + r12.getMeasuredHeight());
            r10 = Math.min(r10, Math.max(0.0f, r12.getY()));
            goto L40
        L36:
            if (GroupCallActivity.m15121$$Nest$fgetitemAnimator(this.this$0).removingHolders.contains(r13) == true) goto L40;
            r10 = Math.min(r10, Math.max(0, r12.getTop()));
            r11 = Math.max(r11, r12.getBottom());
            goto L40
        L32:
            this.visiblePeerTmp.delete(r142.getPeerId());
        L33:
            r15 = r62;
            r16 = r8;
            r82 = r5;
            goto L34
        L41:
            int r83 = r5;
            if (this.visiblePeerTmp.size() <= 0) goto L44;
            r9 = r83;
        L44:
            if (r9 == 0) goto L46;
            GroupCallActivity.m15292$$Nest$mupdateSubtitle(this.this$0);
        L46:
            if (r2 == false) goto L48;
            float r22 = (GroupCallActivity.m15121$$Nest$fgetitemAnimator(this.this$0).outMinTop * (1.0f - GroupCallActivity.m15121$$Nest$fgetitemAnimator(this.this$0).animationProgress)) + (GroupCallActivity.m15121$$Nest$fgetitemAnimator(this.this$0).animationProgress * r10);
            r11 = (r11 * GroupCallActivity.m15121$$Nest$fgetitemAnimator(this.this$0).animationProgress) + (GroupCallActivity.m15121$$Nest$fgetitemAnimator(this.this$0).outMaxBottom * (1.0f - GroupCallActivity.m15121$$Nest$fgetitemAnimator(this.this$0).animationProgress));
        L50:
            if (r10 != Float.MAX_VALUE) goto L52;
        L56:
            r18.save();
            r18.clipRect(0, 0, getMeasuredWidth(), getMeasuredHeight());
            super.dispatchDraw(r18);
            r18.restore();
            return;
        L52:
            if (AndroidUtilities.isTablet() == false) goto L54;
            int r3 = Math.min(AndroidUtilities.dp(420.0f), getMeasuredWidth());
        L55:
            GroupCallActivity.m15184$$Nest$fgetrect(this.this$0).set((getMeasuredWidth() - r3) >> 1, r22, getMeasuredWidth() - r3, Math.min(getMeasuredHeight() - getTranslationY(), r11));
            r18.drawRoundRect(GroupCallActivity.m15184$$Nest$fgetrect(this.this$0), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), GroupCallActivity.m15129$$Nest$fgetlistViewBackgroundPaint(this.this$0));
            goto L56
        L54:
            r3 = getMeasuredWidth();
            goto L55
        L48:
            r22 = r10;
            goto L50
        L5:
            r2 = false;
            goto L6
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != GroupCallActivity.m15204$$Nest$fgetscrimView(this.this$0)) goto L7;
            return false;
        L7:
            return super.drawChild(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            GroupCallActivity.m15121$$Nest$fgetitemAnimator(this.this$0).updateBackgroundBeforeAnimation();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.View
        public void setVisibility(int r6) {
            if (getVisibility() == r6) goto L16;
            int r1 = 0;
        L6:
            if (r1 >= getChildCount()) goto L16;
            View r2 = getChildAt(r1);
            if ((r2 instanceof GroupCallGridCell) == false) goto L15;
            GroupCallActivity r3 = this.this$0;
            GroupCallGridCell r4 = (GroupCallGridCell) r2;
            if (r2.isAttachedToWindow() == false) goto L13;
            if (r6 != 0) goto L13;
            boolean r22 = true;
        L14:
            GroupCallActivity.m15265$$Nest$mattachRenderer(r3, r4, r22);
        L13:
            r22 = false;
        L15:
            r1 = r1 + 1;
        L16:
            super.setVisibility(r6);
        }
    }

    /* loaded from: classes6.dex */
    public class AvatarUpdaterDelegate implements ImageUpdater.ImageUpdaterDelegate {
        private TLRPC.FileLocation avatar;
        private TLRPC.FileLocation avatarBig;
        private final long peerId;
        final /* synthetic */ GroupCallActivity this$0;
        private ImageLocation uploadingImageLocation;
        public float uploadingProgress;

        public static /* synthetic */ void $r8$lambda$5HKU8vqNUMB2xXTX5kxFXw49GdM(AvatarUpdaterDelegate r0, TLRPC.TL_error r1, TLObject r2, String r3) {
            r0.lambda$didUploadPhoto$0(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$sBCZ4oCvUtIV-jm9ZE3tz3sAV0Q, reason: not valid java name */
        public static /* synthetic */ void m15300$r8$lambda$sBCZ4oCvUtIVjm9ZE3tz3sAV0Q(AvatarUpdaterDelegate r0, String r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$didUploadPhoto$1(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$u-JG0TxANVXl9Am5m5pxwE0Hhe4, reason: not valid java name */
        public static /* synthetic */ void m15301$r8$lambda$uJG0TxANVXl9Am5m5pxwE0Hhe4(AvatarUpdaterDelegate r0, TLRPC.InputFile r1, TLRPC.InputFile r2, TLRPC.VideoSize r3, double r4, String r6, TLRPC.PhotoSize r7, TLRPC.PhotoSize r8) {
            r0.lambda$didUploadPhoto$3(r1, r2, r3, r4, r6, r7, r8);
        }

        public static /* synthetic */ void $r8$lambda$y5cjfyB4Y6bWRYom8TEpgGQBCoQ(AvatarUpdaterDelegate r0) {
            r0.lambda$didUploadPhoto$2();
        }

        /* renamed from: -$$Nest$fgetavatar, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.FileLocation m15302$$Nest$fgetavatar(AvatarUpdaterDelegate r0) {
            return r0.avatar;
        }

        /* renamed from: -$$Nest$fgetuploadingImageLocation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageLocation m15303$$Nest$fgetuploadingImageLocation(AvatarUpdaterDelegate r0) {
            return r0.uploadingImageLocation;
        }

        private AvatarUpdaterDelegate(GroupCallActivity r1, long r2) {
            this.this$0 = r1;
            this.peerId = r2;
        }

        public /* synthetic */ AvatarUpdaterDelegate(GroupCallActivity r1, long r2, GroupCallActivityIA r4) {
            this(r1, r2);
        }

        private /* synthetic */ void lambda$didUploadPhoto$0(TLRPC.TL_error r12, TLObject r13, String r14) {
            if (this.uploadingImageLocation == null) goto L6;
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).removeUploadingImage(this.uploadingImageLocation);
            this.uploadingImageLocation = null;
        L6:
            if (r12 != null) goto L38;
            TLRPC.User r122 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getMessagesController().getUser(Long.valueOf(GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getUserConfig().getClientUserId()));
            GroupCallActivity r3 = this.this$0;
            if (r122 != null) goto L13;
            r122 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(r3).getUserConfig().getCurrentUser();
            if (r122 != null) goto L12;
            return;
        L12:
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getMessagesController().putUser(r122, false);
        L14:
            TLRPC.TL_photos_photo r132 = (TLRPC.TL_photos_photo) r13;
            ArrayList<TLRPC.PhotoSize> r32 = r132.photo.sizes;
            TLRPC.PhotoSize r4 = FileLoader.getClosestPhotoSizeWithSize(r32, 150);
            TLRPC.PhotoSize r33 = FileLoader.getClosestPhotoSizeWithSize(r32, 800);
            if (r132.photo.video_sizes.isEmpty() == false) goto L17;
            TLRPC.VideoSize r5 = null;
        L18:
            TLRPC.TL_userProfilePhoto r6 = new TLRPC.TL_userProfilePhoto();
            r122.photo = r6;
            r6.photo_id = r132.photo.id;
            if (r4 == null) goto L21;
            r6.photo_small = r4.location;
        L21:
            if (r33 == null) goto L23;
            r6.photo_big = r33.location;
        L23:
            if (r4 != null) goto L25;
        L27:
            if (r33 != null) goto L29;
        L31:
            if (r5 == null) goto L34;
            if (r14 == null) goto L34;
            new File(r14).renameTo(FileLoader.getInstance(GroupCallActivity.access$4900(this.this$0)).getPathToAttach(r5, "mp4", true));
        L34:
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getMessagesController().getDialogPhotos(r122.id).reset();
            ArrayList r133 = new ArrayList();
            r133.add(r122);
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getMessagesStorage().putUsersAndChats(r133, null, false, true);
            TLRPC.User r123 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getMessagesController().getUser(Long.valueOf(this.peerId));
            ImageLocation r134 = ImageLocation.getForUser(GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getCurrentAccount(), r123, 0);
            ImageLocation r124 = ImageLocation.getForUser(GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getCurrentAccount(), r123, 1);
            if (ImageLocation.getForLocal(this.avatarBig) != null) goto L37;
            r124 = ImageLocation.getForLocal(this.avatar);
        L37:
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).setCreateThumbFromParent(false);
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).initIfEmpty(null, r134, r124, true);
            this.avatar = null;
            this.avatarBig = null;
            AndroidUtilities.updateVisibleRows(GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0));
            updateAvatarUploadingProgress(1.0f);
            goto L38
        L29:
            if (this.avatarBig == null) goto L31;
            FileLoader.getInstance(GroupCallActivity.access$4800(this.this$0)).getPathToAttach(this.avatarBig, true).renameTo(FileLoader.getInstance(GroupCallActivity.access$4700(this.this$0)).getPathToAttach(r33, true));
            goto L31
        L25:
            if (this.avatar == null) goto L27;
            FileLoader.getInstance(GroupCallActivity.access$4500(this.this$0)).getPathToAttach(this.avatar, true).renameTo(FileLoader.getInstance(GroupCallActivity.access$4400(this.this$0)).getPathToAttach(r4, true));
            ImageLoader.getInstance().replaceImageInCache(this.avatar.volume_id + "_" + this.avatar.local_id + "@50_50", r4.location.volume_id + "_" + r4.location.local_id + "@50_50", ImageLocation.getForUser(GroupCallActivity.access$4600(this.this$0), r122, 1), false);
            goto L27
        L17:
            r5 = r132.photo.video_sizes.get(0);
            goto L18
        L13:
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(r3).getUserConfig().setCurrentUser(r122);
        L38:
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_ALL)});
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getNotificationCenter().postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getUserConfig().saveConfig(true);
        }

        private /* synthetic */ void lambda$didUploadPhoto$1(String r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new GroupCallActivity$AvatarUpdaterDelegate$$ExternalSyntheticLambda3(this, r4, r3, r2));
        }

        private /* synthetic */ void lambda$didUploadPhoto$2() {
            if (this.uploadingImageLocation == null) goto L5;
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).removeUploadingImage(this.uploadingImageLocation);
            this.uploadingImageLocation = null;
        L5:
            TLRPC.Chat r0 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getMessagesController().getChat(Long.valueOf(-this.peerId));
            ImageLocation r3 = ImageLocation.getForChat(r0, 0);
            ImageLocation r02 = ImageLocation.getForChat(r0, 1);
            if (ImageLocation.getForLocal(this.avatarBig) != null) goto L8;
            r02 = ImageLocation.getForLocal(this.avatar);
        L8:
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).setCreateThumbFromParent(false);
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).initIfEmpty(null, r3, r02, true);
            this.avatar = null;
            this.avatarBig = null;
            AndroidUtilities.updateVisibleRows(GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0));
            updateAvatarUploadingProgress(1.0f);
        }

        private /* synthetic */ void lambda$didUploadPhoto$3(TLRPC.InputFile r16, TLRPC.InputFile r17, TLRPC.VideoSize r18, double r19, String r21, TLRPC.PhotoSize r22, TLRPC.PhotoSize r23) {
            if (r16 != null) goto L10;
            if (r17 != null) goto L10;
            if (r18 != null) goto L10;
            this.avatar = r22.location;
            TLRPC.FileLocation r0 = r23.location;
            this.avatarBig = r0;
            this.uploadingImageLocation = ImageLocation.getForLocal(r0);
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).addUploadingImage(this.uploadingImageLocation, ImageLocation.getForLocal(this.avatar));
            AndroidUtilities.updateVisibleRows(GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0));
            return;
        L10:
            if (this.peerId <= 0) goto L20;
            TLRPC.TL_photos_uploadProfilePhoto r02 = new TLRPC.TL_photos_uploadProfilePhoto();
            if (r16 == null) goto L14;
            r02.file = r16;
            r02.flags |= 1;
        L14:
            if (r17 == null) goto L16;
            r02.video = r17;
            int r1 = r02.flags;
            r02.video_start_ts = r19;
            r02.flags = r1 | 6;
        L16:
            if (r18 == null) goto L18;
            r02.video_emoji_markup = r18;
            r02.flags |= 16;
        L18:
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getConnectionsManager().sendRequest(r02, new GroupCallActivity$AvatarUpdaterDelegate$$ExternalSyntheticLambda1(this, r21));
            return;
        L20:
            GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0).getMessagesController().changeChatAvatar(-this.peerId, null, r16, r17, r18, r19, r21, r22.location, r23.location, new GroupCallActivity$AvatarUpdaterDelegate$$ExternalSyntheticLambda2(this));
        }

        @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
        public void didStartUpload(boolean r1, boolean r2) {
        }

        @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
        public void didUploadPhoto(TLRPC.InputFile r5, TLRPC.InputFile r6, double r7, String r9, TLRPC.PhotoSize r10, TLRPC.PhotoSize r11, boolean r12, TLRPC.VideoSize r13) {
            AndroidUtilities.runOnUIThread(new GroupCallActivity$AvatarUpdaterDelegate$$ExternalSyntheticLambda0(this, r5, r6, r13, r7, r9, r11, r10));
        }

        @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
        public void onUploadProgressChanged(float r3) {
            GroupCallActivity.m15087$$Nest$fgetavatarsViewPager(this.this$0).setUploadProgress(this.uploadingImageLocation, r3);
            updateAvatarUploadingProgress(r3);
        }

        public void updateAvatarUploadingProgress(float r4) {
            this.uploadingProgress = r4;
            if (GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0) == null) goto L14;
            int r0 = 0;
        L7:
            if (r0 >= GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildCount()) goto L19;
            View r1 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildAt(r0);
            if ((r1 instanceof GroupCallUserCell) == false) goto L13;
            GroupCallUserCell r12 = (GroupCallUserCell) r1;
            if (r12.isSelfUser() == false) goto L13;
            r12.setUploadProgress(r4, true);
        L13:
            r0 = r0 + 1;
            goto L7
        L19:
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static final class CallEncryptionCell extends View {
        private final CallEncryptionCellDrawable drawable;

        public static final class EncryptionCallDialog extends Dialog {
            private Bitmap blurBitmap;
            private Paint blurBitmapPaint;
            private BitmapShader blurBitmapShader;
            private final Matrix blurMatrix;
            private final CallEncryptionCell cell;
            private boolean dismissing;
            private float progress;
            private ValueAnimator progressAnimator;
            private final View windowView;

            /* renamed from: org.telegram.ui.GroupCallActivity$CallEncryptionCell$EncryptionCallDialog$1, reason: invalid class name */
            public class AnonymousClass1 extends View {
                private final int[] p;
                final /* synthetic */ EncryptionCallDialog this$0;
                final /* synthetic */ CallEncryptionCell val$cell;

                public AnonymousClass1(EncryptionCallDialog r1, Context r2, CallEncryptionCell r3) {
                    this.this$0 = r1;
                    this.val$cell = r3;
                    super(r2);
                    this.p = new int[2];
                }

                @Override // android.view.View
                public void dispatchDraw(Canvas r8) {
                    if (EncryptionCallDialog.m15314$$Nest$fgetprogress(this.this$0) > 0.0f) goto L5;
                L7:
                    Canvas r1 = r8;
                L8:
                    CallEncryptionCell r82 = this.val$cell;
                    if (r82 != null) goto L11;
                    return;
                L11:
                    if (r82.isAttachedToWindow() == true) goto L13;
                L15:
                    this.this$0.dismiss();
                L16:
                    r1.save();
                    r1.translate(this.p[0] - (this.val$cell.getMeasuredWidth() * (1.0f - this.val$cell.getScaleX())), this.p[1] - (this.val$cell.getMeasuredHeight() * (1.0f - this.val$cell.getScaleY())));
                    if (CallEncryptionCell.m15305$$Nest$fgetdrawable(this.val$cell).draw(r1, this.val$cell.getMeasuredWidth(), EncryptionCallDialog.m15314$$Nest$fgetprogress(this.this$0)) == false) goto L19;
                    invalidate();
                L19:
                    r1.restore();
                    return;
                L13:
                    if (this.val$cell.getAlpha() <= 0.5f) goto L15;
                    this.val$cell.getLocationInWindow(this.p);
                    goto L16
                L5:
                    if (EncryptionCallDialog.m15311$$Nest$fgetblurBitmapPaint(this.this$0) == null) goto L7;
                    EncryptionCallDialog.m15313$$Nest$fgetblurMatrix(this.this$0).reset();
                    float r0 = getWidth() / EncryptionCallDialog.m15310$$Nest$fgetblurBitmap(this.this$0).getWidth();
                    EncryptionCallDialog.m15313$$Nest$fgetblurMatrix(this.this$0).postScale(r0, r0);
                    EncryptionCallDialog.m15312$$Nest$fgetblurBitmapShader(this.this$0).setLocalMatrix(EncryptionCallDialog.m15313$$Nest$fgetblurMatrix(this.this$0));
                    EncryptionCallDialog.m15311$$Nest$fgetblurBitmapPaint(this.this$0).setAlpha((int) (EncryptionCallDialog.m15314$$Nest$fgetprogress(this.this$0) * 255.0f));
                    r1 = r8;
                    r1.drawRect(0.0f, 0.0f, getWidth(), getHeight(), EncryptionCallDialog.m15311$$Nest$fgetblurBitmapPaint(this.this$0));
                    goto L8
                }
            }

            /* renamed from: org.telegram.ui.GroupCallActivity$CallEncryptionCell$EncryptionCallDialog$2, reason: invalid class name */
            public class AnonymousClass2 extends AnimatorListenerAdapter {
                final /* synthetic */ EncryptionCallDialog this$0;
                final /* synthetic */ Runnable val$done;
                final /* synthetic */ float val$to;

                public AnonymousClass2(EncryptionCallDialog r1, float r2, Runnable r3) {
                    this.this$0 = r1;
                    this.val$to = r2;
                    this.val$done = r3;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r2) {
                    EncryptionCallDialog.m15316$$Nest$fputprogress(this.this$0, this.val$to);
                    EncryptionCallDialog.m15315$$Nest$fgetwindowView(this.this$0).invalidate();
                    Runnable r1 = this.val$done;
                    if (r1 == null) goto L6;
                    r1.run();
                    return;
                }
            }

            /* renamed from: $r8$lambda$FucXTGNbXahJhNvZMz01-fJo_eg, reason: not valid java name */
            public static /* synthetic */ void m15306$r8$lambda$FucXTGNbXahJhNvZMz01fJo_eg(EncryptionCallDialog r0, ValueAnimator r1) {
                r0.lambda$animate$5(r1);
            }

            /* renamed from: $r8$lambda$S0vTgan6JX3XMB-owzNvRm-EmoY, reason: not valid java name */
            public static /* synthetic */ void m15307$r8$lambda$S0vTgan6JX3XMBowzNvRmEmoY(EncryptionCallDialog r0) {
                r0.lambda$dismiss$3();
            }

            /* renamed from: $r8$lambda$WEE-z3ANQEW8AwzfVmuQ9F9TBpg, reason: not valid java name */
            public static /* synthetic */ void m15308$r8$lambda$WEEz3ANQEW8AwzfVmuQ9F9TBpg(EncryptionCallDialog r0, View r1) {
                r0.lambda$new$1(r1);
            }

            public static /* synthetic */ void $r8$lambda$h4sOFNV09juG5YpOmKJCz5E1m9E(EncryptionCallDialog r0) {
                r0.lambda$show$2();
            }

            /* renamed from: $r8$lambda$wq2f30Q9exIX_4hcv-0FbIZLgWk, reason: not valid java name */
            public static /* synthetic */ void m15309$r8$lambda$wq2f30Q9exIX_4hcv0FbIZLgWk(EncryptionCallDialog r0) {
                r0.lambda$dismiss$4();
            }

            public static /* synthetic */ void $r8$lambda$yLeUJ4lSxu5SFimfx3Spd6qkIOQ(EncryptionCallDialog r0, CallEncryptionCell r1, Bitmap r2) {
                r0.lambda$new$0(r1, r2);
            }

            /* renamed from: -$$Nest$fgetblurBitmap, reason: not valid java name */
            public static /* bridge */ /* synthetic */ Bitmap m15310$$Nest$fgetblurBitmap(EncryptionCallDialog r0) {
                return r0.blurBitmap;
            }

            /* renamed from: -$$Nest$fgetblurBitmapPaint, reason: not valid java name */
            public static /* bridge */ /* synthetic */ Paint m15311$$Nest$fgetblurBitmapPaint(EncryptionCallDialog r0) {
                return r0.blurBitmapPaint;
            }

            /* renamed from: -$$Nest$fgetblurBitmapShader, reason: not valid java name */
            public static /* bridge */ /* synthetic */ BitmapShader m15312$$Nest$fgetblurBitmapShader(EncryptionCallDialog r0) {
                return r0.blurBitmapShader;
            }

            /* renamed from: -$$Nest$fgetblurMatrix, reason: not valid java name */
            public static /* bridge */ /* synthetic */ Matrix m15313$$Nest$fgetblurMatrix(EncryptionCallDialog r0) {
                return r0.blurMatrix;
            }

            /* renamed from: -$$Nest$fgetprogress, reason: not valid java name */
            public static /* bridge */ /* synthetic */ float m15314$$Nest$fgetprogress(EncryptionCallDialog r0) {
                return r0.progress;
            }

            /* renamed from: -$$Nest$fgetwindowView, reason: not valid java name */
            public static /* bridge */ /* synthetic */ View m15315$$Nest$fgetwindowView(EncryptionCallDialog r0) {
                return r0.windowView;
            }

            /* renamed from: -$$Nest$fputprogress, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m15316$$Nest$fputprogress(EncryptionCallDialog r0, float r1) {
                r0.progress = r1;
            }

            public EncryptionCallDialog(Context r3, CallEncryptionCell r4) {
                super(r3, R.style.TransparentDialog);
                this.blurMatrix = new Matrix();
                this.cell = r4;
                if (r4 == null) goto L5;
                r4.setVisibility(4);
            L5:
                AndroidUtilities.makeGlobalBlurBitmap(new GroupCallActivity$CallEncryptionCell$EncryptionCallDialog$$ExternalSyntheticLambda0(this, r4), 14.0f);
                AnonymousClass1 r0 = new AnonymousClass1(this, r3, r4);
                this.windowView = r0;
                r0.setOnClickListener(new GroupCallActivity$CallEncryptionCell$EncryptionCallDialog$$ExternalSyntheticLambda1(this));
            }

            private void animate(float r4, Runnable r5) {
                ValueAnimator r0 = this.progressAnimator;
                if (r0 == null) goto L5;
                r0.cancel();
                this.progressAnimator = null;
            L5:
                ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.progress, r4});
                this.progressAnimator = r02;
                r02.addUpdateListener(new GroupCallActivity$CallEncryptionCell$EncryptionCallDialog$$ExternalSyntheticLambda5(this));
                this.progressAnimator.addListener(new AnonymousClass2(this, r4, r5));
                this.progressAnimator.setDuration(420);
                this.progressAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
                this.progressAnimator.start();
            }

            private /* synthetic */ void lambda$animate$5(ValueAnimator r1) {
                this.progress = ((Float) r1.getAnimatedValue()).floatValue();
                this.windowView.invalidate();
            }

            private /* synthetic */ void lambda$dismiss$3() {
                super.dismiss();
            }

            private /* synthetic */ void lambda$dismiss$4() {
                CallEncryptionCell r0 = this.cell;
                if (r0 == null) goto L5;
                r0.setVisibility(0);
            L5:
                AndroidUtilities.runOnUIThread(new GroupCallActivity$CallEncryptionCell$EncryptionCallDialog$$ExternalSyntheticLambda4(this), 16);
            }

            private /* synthetic */ void lambda$new$0(CallEncryptionCell r3, Bitmap r4) {
                if (r3 == null) goto L4;
                r3.setVisibility(0);
            L4:
                this.blurBitmap = r4;
                Paint r32 = new Paint(1);
                this.blurBitmapPaint = r32;
                Bitmap r0 = this.blurBitmap;
                Shader.TileMode r1 = Shader.TileMode.CLAMP;
                BitmapShader r42 = new BitmapShader(r0, r1, r1);
                this.blurBitmapShader = r42;
                r32.setShader(r42);
                ColorMatrix r33 = new ColorMatrix();
                if (Theme.isCurrentThemeDark() == false) goto L7;
                float r43 = 0.05f;
            L8:
                AndroidUtilities.adjustSaturationColorMatrix(r33, r43);
                if (Theme.isCurrentThemeDark() == false) goto L11;
                float r44 = -0.02f;
            L12:
                AndroidUtilities.adjustBrightnessColorMatrix(r33, r44);
                this.blurBitmapPaint.setColorFilter(new ColorMatrixColorFilter(r33));
                return;
            L11:
                r44 = -0.04f;
                goto L12
            L7:
                r43 = 0.25f;
                goto L8
            }

            private /* synthetic */ void lambda$new$1(View r1) {
                dismiss();
            }

            private /* synthetic */ void lambda$show$2() {
                CallEncryptionCell r1 = this.cell;
                if (r1 == null) goto L6;
                r1.setVisibility(4);
                return;
            }

            @Override // android.app.Dialog, android.content.DialogInterface
            public void dismiss() {
                if (this.dismissing == false) goto L5;
                return;
            L5:
                this.dismissing = true;
                animate(0.0f, new GroupCallActivity$CallEncryptionCell$EncryptionCallDialog$$ExternalSyntheticLambda2(this));
                WindowManager.LayoutParams r0 = getWindow().getAttributes();     // Catch: Exception -> L8
                r0.flags |= 16;
                getWindow().setAttributes(r0);     // Catch: Exception -> L8
                return;
            L8:
                e = move-exception;
                FileLog.e(e);
            }

            @Override // android.app.Dialog
            public void onCreate(Bundle r4) {
                super.onCreate(r4);
                Window r42 = getWindow();
                r42.setWindowAnimations(R.style.DialogNoAnimation);
                setContentView(this.windowView, new ViewGroup.LayoutParams(-1, -1));
                WindowManager.LayoutParams r3 = r42.getAttributes();
                r3.width = -1;
                r3.height = -1;
                r3.gravity = 119;
                r3.dimAmount = 0.0f;
                int r0 = r3.flags & (-3);
                r3.softInputMode = 48;
                r3.flags = (-2013069056) | r0;
                if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L5;
                r3.flags = r0 | (-2013060864);
                AndroidUtilities.logFlagSecure();
            L5:
                r3.flags |= 1152;
                if (Build.VERSION.SDK_INT < 28) goto L8;
                AndroidUtilities$$ExternalSyntheticApiModelOutline1.m(r3, 1);
            L8:
                r42.setAttributes(r3);
            }

            @Override // android.app.Dialog
            public void show() {
                super.show();
                animate(1.0f, null);
                AndroidUtilities.runOnUIThread(new GroupCallActivity$CallEncryptionCell$EncryptionCallDialog$$ExternalSyntheticLambda3(this), 16);
            }
        }

        /* renamed from: $r8$lambda$zHQcLhDJUMh64K-m-ZaK6_TzlSA, reason: not valid java name */
        public static /* synthetic */ void m15304$r8$lambda$zHQcLhDJUMh64KmZaK6_TzlSA(CallEncryptionCell r0, Context r1, View r2) {
            r0.lambda$new$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetdrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CallEncryptionCellDrawable m15305$$Nest$fgetdrawable(CallEncryptionCell r0) {
            return r0.drawable;
        }

        public CallEncryptionCell(Context r1, CallEncryptionCellDrawable r2) {
            super(r1);
            this.drawable = r2;
            NotificationCenter.listenEmojiLoading(this);
            setOnClickListener(new GroupCallActivity$CallEncryptionCell$$ExternalSyntheticLambda0(this, r1));
        }

        private /* synthetic */ void lambda$new$0(Context r1, View r2) {
            new EncryptionCallDialog(r1, this).show();
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r4) {
            super.dispatchDraw(r4);
            CallEncryptionCellDrawable r0 = this.drawable;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.draw(r4, getMeasuredWidth(), 0.0f) == false) goto L9;
            invalidate();
            return;
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            CallEncryptionCellDrawable r0 = this.drawable;
            if (r0 == null) goto L6;
            r0.setParentView(this);
            return;
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            CallEncryptionCellDrawable r0 = this.drawable;
            if (r0 == null) goto L6;
            r0.resetParentView(this);
            return;
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(View.MeasureSpec.getSize(r1), AndroidUtilities.dp(38.0f));
        }
    }

    /* loaded from: classes6.dex */
    public static final class CallEncryptionCellDrawable {
        private final Paint backgroundPaint;
        private final Path clipPath;
        private final Text closeText;
        private final Paint dividerPaint;
        private final RectF fromRect;
        private int listBackgroundColor;
        private boolean loading;
        private final AnimatedFloat loadingAlpha;
        private View parentView;
        private final EmojiSlot[] slots;
        private final Text text1;
        private final Text text2;
        private final RectF toRect;

        public static /* synthetic */ void $r8$lambda$7wIwYMJcG9MPyVBid_GE1VA0hRI(CallEncryptionCellDrawable r0) {
            r0.invalidate();
        }

        /* renamed from: -$$Nest$msetPaintBackgroundColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15317$$Nest$msetPaintBackgroundColor(CallEncryptionCellDrawable r0, int r1) {
            r0.setPaintBackgroundColor(r1);
        }

        public CallEncryptionCellDrawable(Context r9) {
            this.backgroundPaint = new Paint(1);
            this.dividerPaint = new Paint(1);
            this.slots = new EmojiSlot[4];
            this.loading = true;
            this.loadingAlpha = new AnimatedFloat(new GroupCallActivity$CallEncryptionCellDrawable$$ExternalSyntheticLambda0(this), 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.fromRect = new RectF();
            this.toRect = new RectF();
            this.clipPath = new Path();
            int r92 = 0;
        L3:
            EmojiSlot[] r0 = this.slots;
            if (r92 >= r0.length) goto L6;
            r0[r92] = new EmojiSlot(r92);
            r92 = r92 + 1;
            goto L3
        L6:
            this.backgroundPaint.setColor(Theme.getColor(Theme.key_voipgroup_listViewBackground));
            this.text1 = new Text(LocaleController.getString(R.string.ConferenceEncrypted), 12.0f, AndroidUtilities.bold());
            this.text2 = new Text(LocaleController.getString(R.string.ConferenceEncryptedInfo), 11.0f).multiline(99).setMaxWidth(AndroidUtilities.dp(200.0f)).lineSpacing(AndroidUtilities.dp(2.66f));
            this.closeText = new Text(LocaleController.getString(R.string.ConferenceEncryptedClose), 14.0f, AndroidUtilities.bold());
            setEmojis(null);
        }

        private void invalidate() {
            View r0 = this.parentView;
            if (r0 == null) goto L6;
            r0.invalidate();
            return;
        }

        private void setPaintBackgroundColor(int r1) {
            this.listBackgroundColor = r1;
            invalidate();
        }

        public boolean draw(Canvas r29, float r30, float r31) {
            r29.save();
            this.text1.ellipsize(r30 - AndroidUtilities.dp(132.0f));
            this.backgroundPaint.setColor(ColorUtils.blendARGB(this.listBackgroundColor, Theme.getColor(Theme.key_voipgroup_listViewBackground), r31));
            float r2 = this.loadingAlpha.set(this.loading);
            float r4 = (AndroidUtilities.dp(14.0f) + AndroidUtilities.dp(86.0f)) + this.text1.getWidth();
            float r5 = AndroidUtilities.dp(28.0f);
            float r8 = AndroidUtilities.dp(232.0f);
            float r10 = AndroidUtilities.dp(50.0f) + (AndroidUtilities.dp(54.0f) + this.text2.getHeight());
            float r42 = AndroidUtilities.lerp(r4, r8, r31);
            float r6 = AndroidUtilities.lerp(r5, r10, r31);
            float r3 = AndroidUtilities.lerp(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(16.0f), r31);
            RectF r12 = AndroidUtilities.rectTmp;
            r12.set((r30 - r42) / 2.0f, 0.0f, (r30 + r42) / 2.0f, r6);
            r29.drawRoundRect(r12, r3, r3, this.backgroundPaint);
            this.clipPath.rewind();
            this.clipPath.addRoundRect(r12, r3, r3, Path.Direction.CW);
            r29.clipPath(this.clipPath);
            int r32 = AndroidUtilities.dp(18.0f);
            int r62 = AndroidUtilities.dp(30.0f);
            int r16 = r32 / 2;
            int r13 = ((int) r12.centerY()) - r16;
            int r9 = ((int) r12.centerY()) + r16;
            float r21 = r32 / 2.0f;
            float r132 = r13;
            float r92 = r9;
            this.fromRect.set(((((int) r12.left) + AndroidUtilities.dp(7.0f)) + AndroidUtilities.dp(10.0f)) - r21, r132, ((((int) r12.left) + AndroidUtilities.dp(7.0f)) + AndroidUtilities.dp(10.0f)) + r21, r92);
            float r33 = r30 / 2.0f;
            float r11 = r33 - (r4 * 2);
            float r14 = (int) ((r8 - AndroidUtilities.dp(32.0f)) / 4.0f);
            float r43 = (int) ((0.5f * r14) + r11);
            float r22 = r62 / 2.0f;
            this.toRect.set(r43 - r22, (int) ((r12.top + AndroidUtilities.dp(27.33f)) - r22), r43 + r22, (int) ((r12.top + AndroidUtilities.dp(27.33f)) + r22));
            RectF r34 = this.fromRect;
            RectF r44 = this.toRect;
            AndroidUtilities.lerpCentered(r34, r44, r31, r44);
            boolean r35 = this.slots[0].draw(r29, this.toRect, r31);
            this.fromRect.set(((((int) r12.left) + AndroidUtilities.dp(27.0f)) + AndroidUtilities.dp(10.0f)) - r21, r132, ((((int) r12.left) + AndroidUtilities.dp(27.0f)) + AndroidUtilities.dp(10.0f)) + r21, r92);
            float r45 = (int) ((1.5f * r14) + r11);
            boolean r26 = r35;
            this.toRect.set(r45 - r22, (int) ((r12.top + AndroidUtilities.dp(27.33f)) - r22), r45 + r22, (int) ((r12.top + AndroidUtilities.dp(27.33f)) + r22));
            RectF r36 = this.fromRect;
            RectF r46 = this.toRect;
            AndroidUtilities.lerpCentered(r36, r46, r31, r46);
            boolean r82 = true;
            if (this.slots[1].draw(r29, this.toRect, r31) == false) goto L5;
            r26 = true;
        L5:
            Text r1 = this.text1;
            r1.draw(r29, r33 - (r1.getWidth() / 2.0f), r5 / 2.0f, -1, (1.0f - r31) * AndroidUtilities.lerp(1.0f, 0.75f, r2));
            this.fromRect.set(((((int) r12.right) - AndroidUtilities.dp(47.0f)) + AndroidUtilities.dp(10.0f)) - r21, r132, ((((int) r12.right) - AndroidUtilities.dp(47.0f)) + AndroidUtilities.dp(10.0f)) + r21, r92);
            float r23 = (int) ((2.5f * r14) + r11);
            this.toRect.set(r23 - r22, (int) ((r12.top + AndroidUtilities.dp(27.33f)) - r22), r23 + r22, (int) ((r12.top + AndroidUtilities.dp(27.33f)) + r22));
            RectF r24 = this.fromRect;
            RectF r37 = this.toRect;
            AndroidUtilities.lerpCentered(r24, r37, r31, r37);
            if (this.slots[2].draw(r29, this.toRect, r31) == false) goto L8;
            r26 = true;
        L8:
            this.fromRect.set(((((int) r12.right) - AndroidUtilities.dp(27.0f)) + AndroidUtilities.dp(10.0f)) - r21, r132, ((((int) r12.right) - AndroidUtilities.dp(27.0f)) + AndroidUtilities.dp(10.0f)) + r21, r92);
            float r25 = (int) (r11 + (r14 * 3.5f));
            this.toRect.set(r25 - r22, (int) ((r12.top + AndroidUtilities.dp(27.33f)) - r22), r25 + r22, (int) ((r12.top + AndroidUtilities.dp(27.33f)) + r22));
            RectF r27 = this.fromRect;
            RectF r38 = this.toRect;
            AndroidUtilities.lerpCentered(r27, r38, r31, r38);
            if (this.slots[3].draw(r29, this.toRect, r31) == true) goto L13;
            r82 = r26;
        L13:
            if (r31 <= 0.0f) goto L15;
            this.text2.draw(r29, AndroidUtilities.dp(16.0f) + (r12.centerX() - (r8 / 2.0f)), AndroidUtilities.dp(54.0f), -1, r31);
            this.dividerPaint.setColor(-16777216);
            this.dividerPaint.setAlpha((int) (255.0f * r31));
            r29.drawRect(r12.left, r10 - AndroidUtilities.dp(40.0f), r12.right, AndroidUtilities.dp(0.66f) + (r10 - AndroidUtilities.dp(40.0f)), this.dividerPaint);
            this.closeText.draw(r29, r12.centerX() - (this.closeText.getWidth() / 2.0f), r10 - AndroidUtilities.dp(20.0f), -1, r31);
        L15:
            r29.restore();
            return r82;
        }

        public void resetParentView(View r4) {
            if (this.parentView == r4) goto L11;
            int r0 = 0;
        L5:
            EmojiSlot[] r1 = this.slots;
            if (r0 >= r1.length) goto L8;
            r1[r0].detach(r4);
            r0 = r0 + 1;
            goto L5
        L8:
            this.parentView = null;
            return;
        }

        public void setEmojis(String[] r4) {
            int r0 = 0;
            if (r4 != null) goto L5;
            boolean r1 = true;
        L6:
            this.loading = r1;
        L8:
            if (r0 >= 4) goto L14;
            EmojiSlot r12 = this.slots[r0];
            if (r4 != null) goto L12;
            String r2 = null;
        L13:
            r12.set(r2);
            r0 = r0 + 1;
            goto L8
        L12:
            r2 = r4[r0];
            goto L13
        L14:
            invalidate();
            return;
        L5:
            r1 = false;
            goto L6
        }

        public void setParentView(View r4) {
            this.parentView = r4;
            int r0 = 0;
        L3:
            EmojiSlot[] r1 = this.slots;
            if (r0 >= r1.length) goto L6;
            r1[r0].attach(r4);
            r0 = r0 + 1;
            goto L3
        }
    }

    /* loaded from: classes6.dex */
    public static final class EmojiSlot {
        private final long INTERVAL;
        private boolean attached;
        private GradientClip clip;
        private final AnimatedEmojiSpan.InvalidateHolder invalidate;
        private String lastEmoji;
        private boolean loaded;
        private final int offset;
        private final HashSet<View> parents;
        private final Drawable[] pool;
        private AnimatedEmojiDrawable real;
        private boolean realAllowed;
        private Drawable realThumb;
        private final RectF rectF;
        private long startTime;

        public static /* synthetic */ void $r8$lambda$Kzce2H49DHyfwMeAUIqKsquWqRQ(EmojiSlot r0) {
            r0.lambda$new$0();
        }

        public static /* synthetic */ void $r8$lambda$qwTHbxkUGVdAtDhZN44bHwlh8XE(EmojiSlot r0, TLRPC.TL_messages_stickerSet r1) {
            r0.lambda$updateEmoji$1(r1);
        }

        public EmojiSlot(int r3) {
            this.INTERVAL = 180;
            this.pool = new Drawable[6];
            this.clip = new GradientClip();
            this.parents = new HashSet();
            int r0 = 0;
            this.attached = false;
            this.invalidate = new GroupCallActivity$EmojiSlot$$ExternalSyntheticLambda1(this);
            this.rectF = new RectF();
            this.offset = r3;
        L3:
            Drawable[] r32 = this.pool;
            if (r0 >= r32.length) goto L6;
            r32[r0] = Emoji.getEmojiDrawable(GroupCallActivity.m15294$$Nest$smgetRandomEmoji());
            r0 = r0 + 1;
            goto L3
        L6:
            this.startTime = System.currentTimeMillis();
        }

        private void checkAttach() {
            boolean r0 = this.parents.isEmpty();
            boolean r1 = !r0;
            if (this.attached == r1) goto L10;
            this.attached = r1;
            if (r0 == true) goto L8;
            onAttached();
            return;
        L8:
            onDetached();
            return;
        }

        private /* synthetic */ void lambda$new$0() {
            Iterator<View> r1 = this.parents.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            r1.next().invalidate();
            goto L4
        }

        private /* synthetic */ void lambda$updateEmoji$1(TLRPC.TL_messages_stickerSet r1) {
            updateEmoji();
        }

        private void onAttached() {
            AnimatedEmojiDrawable r0 = this.real;
            if (r0 == null) goto L6;
            r0.addView(this.invalidate);
            return;
        }

        private void onDetached() {
            AnimatedEmojiDrawable r0 = this.real;
            if (r0 == null) goto L6;
            r0.removeView(this.invalidate);
            return;
        }

        private void shiftPool() {
            int r0 = 0;
        L3:
            Drawable[] r1 = this.pool;
            if (r0 >= (r1.length - 1)) goto L6;
            int r2 = r0 + 1;
            r1[r0] = r1[r2];
            r0 = r2;
            goto L3
        L6:
            r1[r1.length - 1] = Emoji.getEmojiDrawable(GroupCallActivity.m15294$$Nest$smgetRandomEmoji());
        }

        private void updateEmoji() {
            if (this.real != null) goto L5;
            return;
        L5:
            if (this.lastEmoji == null) goto L25;
            int r0 = UserConfig.getProductionAccount();
            TLRPC.TL_inputStickerSetShortName r2 = new TLRPC.TL_inputStickerSetShortName();
            r2.short_name = "StaticEmoji";
            MediaDataController r1 = MediaDataController.getInstance(r0);
            int r02 = 0;
            TLRPC.TL_messages_stickerSet r12 = r1.getStickerSet(r2, 0, false, true, new GroupCallActivity$EmojiSlot$$ExternalSyntheticLambda0(this));
            if (r12 == null) goto L26;
            String r3 = this.lastEmoji.replace("️", _UrlKt.FRAGMENT_ENCODE_SET);
            ArrayList<TLRPC.Document> r13 = r12.documents;
            int r6 = r13.size();
        L11:
            TLRPC.Document r7 = null;
            if (r02 >= r6) goto L16;
            TLRPC.Document r8 = r13.get(r02);
            r02 = r02 + 1;
            TLRPC.Document r82 = r8;
            if (TextUtils.equals(MessageObject.findAnimatedEmojiEmoticon(r82, null).replace("️", _UrlKt.FRAGMENT_ENCODE_SET), r3) == false) goto L11;
            r7 = r82;
        L16:
            if (r7 == null) goto L19;
            this.real.setupDocument(r7);
            return;
        L19:
            FileLog.e("emoji \"" + this.lastEmoji + "\" not found in addemoji/" + r2.short_name);
            return;
        L26:
            return;
        }

        public void attach(View r2) {
            this.parents.add(r2);
            checkAttach();
        }

        public void detach(View r2) {
            this.parents.remove(r2);
            checkAttach();
        }

        public boolean draw(Canvas r21, RectF r22, float r23) {
            float r9 = AndroidUtilities.dp(6.0f);
            this.rectF.set(r22);
            float r10 = -r9;
            this.rectF.inset(r10, r10);
            RectF r1 = this.rectF;
            r21.saveLayerAlpha(r1.left, r1.top, r1.right, r1.bottom, 255, 31);
            long r2 = System.currentTimeMillis() + (this.offset * 45);
            long r4 = r2 - this.startTime;
            float r6 = r4 / 180.0f;
            float r11 = Math.min(1.0f, r6);
            boolean r7 = false;
            if (this.loaded == true) goto L5;
        L17:
            r21.save();
            this.rectF.set(r22);
            this.rectF.offset(0.0f, (r11 - 1.0f) * (r22.height() + r9));
            RectF r12 = this.rectF;
            r21.translate(r12.left, r12.top);
            this.pool[1].setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.pool[1].setAlpha(127);
            this.pool[1].draw(r21);
            this.pool[1].setAlpha(255);
            r21.restore();
        L18:
            this.rectF.set(r22);
            this.rectF.offset(0.0f, r11 * (r22.height() + r9));
            r21.save();
            RectF r112 = this.rectF;
            r21.translate(r112.left, r112.top);
            this.pool[0].setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            this.pool[0].setAlpha(127);
            this.pool[0].draw(r21);
            this.pool[0].setAlpha(255);
            r21.restore();
            if (r6 >= 1.0f) goto L21;
        L29:
            this.rectF.set(r22);
            float r3 = (int) r10;
            this.rectF.inset(r3, r3);
            RectF r24 = this.rectF;
            float r42 = r24.left;
            float r5 = r24.top;
            r24.set(r42, r5, r24.right, r5 + r9);
            this.clip.draw(r21, this.rectF, 1, 1.0f);
            this.rectF.set(r22);
            this.rectF.inset(r3, r3);
            RectF r25 = this.rectF;
            float r32 = r25.left;
            float r43 = r25.bottom;
            r25.set(r32, r43 - r9, r25.right, r43);
            this.clip.draw(r21, this.rectF, 3, 1.0f);
            r21.restore();
            return !r7;
        L21:
            if (this.loaded == true) goto L23;
        L26:
            this.startTime = r2 - (r4 % 180);
            shiftPool();
            if (this.loaded == false) goto L29;
            this.realAllowed = true;
            goto L29
        L23:
            if (this.realAllowed == false) goto L26;
            r7 = true;
            goto L29
        L5:
            if (this.real == null) goto L17;
            if (this.realThumb == null) goto L17;
            if (this.realAllowed == false) goto L17;
            this.rectF.set(r22);
            this.rectF.offset(0.0f, (r11 - 1.0f) * (r22.height() + r9));
            if (r23 >= 1.0f) goto L13;
            r21.save();
            float r18 = 255.0f;
            float r19 = 0.0f;
            this.realThumb.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            RectF r122 = this.rectF;
            r21.translate(r122.left, r122.top);
            this.realThumb.setAlpha((int) ((1.0f - r23) * 255.0f));
            this.realThumb.draw(r21);
            this.realThumb.setAlpha(255);
            r21.restore();
        L15:
            if (r23 <= r19) goto L18;
            r21.save();
            this.rectF.inset(AndroidUtilities.dp(-4.0f), AndroidUtilities.dp(-4.0f));
            this.real.setBounds(0, 0, (int) this.rectF.width(), (int) this.rectF.height());
            RectF r123 = this.rectF;
            r21.translate(r123.left, r123.top);
            this.real.setAlpha((int) (r23 * r18));
            this.real.draw(r21);
            this.real.setAlpha(255);
            r21.restore();
            goto L18
        L13:
            r19 = 0.0f;
            r18 = 255.0f;
            goto L15
        }

        public void set(String r6) {
            boolean r0 = this.loaded;
            if (r6 == null) goto L5;
            boolean r2 = true;
        L6:
            this.loaded = r2;
            if (r6 == null) goto L19;
            if (this.real != null) goto L11;
        L12:
            AnimatedEmojiDrawable r22 = this.real;
            if (r22 == null) goto L15;
            r22.removeView(this.invalidate);
        L15:
            this.realThumb = Emoji.getEmojiDrawable(r6);
            AnimatedEmojiDrawable r3 = new AnimatedEmojiDrawable(21, UserConfig.getProductionAccount());
            this.real = r3;
            this.lastEmoji = r6;
            r3.setupEmojiThumb(r6);
            updateEmoji();
            if (this.attached == false) goto L19;
            this.real.addView(this.invalidate);
            goto L19
        L11:
            if (TextUtils.equals(this.lastEmoji, r6) == false) goto L12;
        L19:
            if (this.loaded == false) goto L23;
            if (r0 == true) goto L24;
            this.realAllowed = false;
            return;
        L24:
            return;
        L23:
            return;
        L5:
            r2 = false;
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public class GroupCallItemAnimator extends DefaultItemAnimator {
        HashSet<RecyclerView.ViewHolder> addingHolders;
        public float animationProgress;
        public ValueAnimator animator;
        float outMaxBottom;
        float outMinTop;
        HashSet<RecyclerView.ViewHolder> removingHolders;
        final /* synthetic */ GroupCallActivity this$0;

        /* renamed from: org.telegram.ui.GroupCallActivity$GroupCallItemAnimator$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ GroupCallItemAnimator this$1;

            public AnonymousClass1(GroupCallItemAnimator r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                super.onAnimationEnd(r2);
                GroupCallItemAnimator r22 = this.this$1;
                r22.animator = null;
                GroupCallActivity.m15128$$Nest$fgetlistView(r22.this$0).invalidate();
                GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$1.this$0).invalidate();
                GroupCallActivity.access$5000(this.this$1.this$0).invalidate();
                GroupCallActivity.m15289$$Nest$mupdateLayout(this.this$1.this$0, true);
                this.this$1.addingHolders.clear();
                this.this$1.removingHolders.clear();
            }
        }

        public static /* synthetic */ void $r8$lambda$pkq_JUjOkoAJzF6fkUyygwaNWP8(GroupCallItemAnimator r0, ValueAnimator r1) {
            r0.lambda$runPendingAnimations$0(r1);
        }

        private GroupCallItemAnimator(GroupCallActivity r1) {
            this.this$0 = r1;
            this.addingHolders = new HashSet();
            this.removingHolders = new HashSet();
        }

        public /* synthetic */ GroupCallItemAnimator(GroupCallActivity r1, GroupCallActivityIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$runPendingAnimations$0(ValueAnimator r1) {
            this.animationProgress = ((Float) r1.getAnimatedValue()).floatValue();
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).invalidate();
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).invalidate();
            GroupCallActivity.access$5100(this.this$0).invalidate();
            GroupCallActivity.m15289$$Nest$mupdateLayout(this.this$0, true);
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
        public void endAnimations() {
            super.endAnimations();
            this.removingHolders.clear();
            this.addingHolders.clear();
            this.outMinTop = Float.MAX_VALUE;
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).invalidate();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
        public void runPendingAnimations() {
            boolean r0 = this.mPendingRemovals.isEmpty();
            boolean r1 = this.mPendingMoves.isEmpty();
            boolean r2 = this.mPendingAdditions.isEmpty();
            ValueAnimator r3 = this.animator;
            if (r3 == null) goto L5;
            r3.cancel();
            this.animator = null;
        L5:
            if (r0 == false) goto L8;
            if (r1 == false) goto L8;
            if (r2 == false) goto L8;
        L9:
            super.runPendingAnimations();
            return;
        L8:
            this.animationProgress = 0.0f;
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.animator = r02;
            r02.addUpdateListener(new GroupCallActivity$GroupCallItemAnimator$$ExternalSyntheticLambda0(this));
            this.animator.addListener(new AnonymousClass1(this));
            this.animator.setDuration(350);
            this.animator.setInterpolator(CubicBezierInterpolator.DEFAULT);
            this.animator.start();
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).invalidate();
            GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).invalidate();
            goto L9
        }

        public void updateBackgroundBeforeAnimation() {
            if (this.animator != null) goto L38;
            this.addingHolders.clear();
            this.addingHolders.addAll(this.mPendingAdditions);
            this.removingHolders.clear();
            this.removingHolders.addAll(this.mPendingRemovals);
            this.outMaxBottom = 0.0f;
            this.outMinTop = Float.MAX_VALUE;
            if (this.addingHolders.isEmpty() == true) goto L8;
        L11:
            int r1 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildCount();
            int r2 = 0;
        L12:
            if (r2 >= r1) goto L28;
            View r3 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).getChildAt(r2);
            RecyclerView.ViewHolder r4 = GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).findContainingViewHolder(r3);
            if (r4 == null) goto L27;
            if (r4.getItemViewType() == 3) goto L27;
            if (r4.getItemViewType() == 4) goto L27;
            if (r4.getItemViewType() == 5) goto L27;
            if (r4.getItemViewType() == 7) goto L27;
            if (this.addingHolders.contains(r4) == true) goto L27;
            this.outMaxBottom = Math.max(this.outMaxBottom, r3.getY() + r3.getMeasuredHeight());
            this.outMinTop = Math.min(this.outMinTop, Math.max(0.0f, r3.getY()));
        L27:
            r2 = r2 + 1;
            goto L12
        L28:
            this.animationProgress = 0.0f;
            GroupCallActivity.m15128$$Nest$fgetlistView(this.this$0).invalidate();
            return;
        L8:
            if (this.removingHolders.isEmpty() == false) goto L11;
            return;
        }
    }

    /* loaded from: classes6.dex */
    public class LightningView extends View {
        private int[] currentColors;
        private final Paint paint;
        private int parentBackgroundColor;
        private float shadowOffset;
        final /* synthetic */ GroupCallActivity this$0;

        public LightningView(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            Paint r12 = new Paint(1);
            this.paint = r12;
            r12.setStyle(Paint.Style.FILL);
            r12.setAlpha(0);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            if (GroupCallActivity.m15140$$Nest$fgetneedTextureLightning(this.this$0) == true) goto L5;
            return;
        L5:
            if (GroupCallActivity.m15188$$Nest$fgetrenderersContainer(this.this$0).progressToFullscreenMode >= 0.1d) goto L9;
            r8.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.paint);
            return;
        }

        public void setNewColors(int[] r11) {
            int[] r0 = this.currentColors;
            boolean r1 = true;
            boolean r2 = false;
            if (r0 != null) goto L5;
        L8:
            if (r0 != null) goto L10;
            this.paint.setAlpha(255);
        L10:
            this.currentColors = r11;
            if (GroupCallActivity.m15131$$Nest$fgetliveLabelBgColorAnimator(this.this$0) != null) goto L13;
        L15:
            this.paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, GroupCallActivity.m15218$$Nest$fgettextureLightningView(this.this$0).getMeasuredHeight(), this.currentColors, null, Shader.TileMode.CLAMP));
            r2 = true;
        L17:
            if (this.parentBackgroundColor == GroupCallActivity.m15088$$Nest$fgetbackgroundColor(this.this$0)) goto L19;
            this.paint.setShadowLayer(AndroidUtilities.dp(36.0f), 0.0f, this.shadowOffset, GroupCallActivity.m15088$$Nest$fgetbackgroundColor(this.this$0));
            this.parentBackgroundColor = GroupCallActivity.m15088$$Nest$fgetbackgroundColor(this.this$0);
        L20:
            if (r1 == false) goto L22;
            invalidate();
        L22:
            GroupCallActivity.m15280$$Nest$mrunUpdateTextureLightningRunnable(this.this$0);
            return;
        L19:
            r1 = r2;
            goto L20
        L13:
            if (GroupCallActivity.m15219$$Nest$fgettextureLightningViewAnimatingAlpha(this.this$0) == 1.0f) goto L15;
            int[] r112 = this.currentColors;
            r112[0] = ColorUtils.setAlphaComponent(r112[0], (int) (GroupCallActivity.m15219$$Nest$fgettextureLightningViewAnimatingAlpha(this.this$0) * 255.0f));
            int[] r113 = this.currentColors;
            r113[1] = ColorUtils.setAlphaComponent(r113[1], (int) (GroupCallActivity.m15219$$Nest$fgettextureLightningViewAnimatingAlpha(this.this$0) * 255.0f));
            goto L15
        L5:
            if (r11[0] != r0[0]) goto L8;
            if (r11[1] == r0[1]) goto L17;
            goto L8
        }

        public void setShadowOffset(int r6) {
            float r62 = r6;
            if (this.shadowOffset == r62) goto L6;
            this.paint.setShadowLayer(AndroidUtilities.dp(36.0f), 0.0f, this.shadowOffset, GroupCallActivity.m15088$$Nest$fgetbackgroundColor(this.this$0));
            this.shadowOffset = r62;
            invalidate();
            return;
        }
    }

    /* loaded from: classes6.dex */
    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private int addMemberRow;
        private int conferenceAddPeopleRow;
        private int conferenceShareLinkRow;
        private int encryptionRow;
        private boolean hasSelfUser;
        private int invitedEndRow;
        private int invitedStartRow;
        private int lastRow;
        private Context mContext;
        private int rowsCount;
        private int shadyJoinEndRow;
        private int shadyJoinStartRow;
        private int shadyLeftEndRow;
        private int shadyLeftStartRow;
        final /* synthetic */ GroupCallActivity this$0;
        private int usersEndRow;
        private int usersStartRow;
        private int usersVideoGridEndRow;
        private int usersVideoGridStartRow;
        private int videoGridDividerRow;
        private int videoNotAvailableRow;

        /* renamed from: org.telegram.ui.GroupCallActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends GroupCallTextCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass1(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Cells.GroupCallTextCell, android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                if (AndroidUtilities.isTablet() == false) goto L6;
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(420.0f), View.MeasureSpec.getSize(r2)), TLObject.FLAG_30), r3);
                return;
            L6:
                super.onMeasure(r2, r3);
            }
        }

        /* renamed from: org.telegram.ui.GroupCallActivity$ListAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends GroupCallUserCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass2(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Cells.GroupCallUserCell, android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                if (AndroidUtilities.isTablet() == false) goto L6;
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(420.0f), View.MeasureSpec.getSize(r2)), TLObject.FLAG_30), r3);
                return;
            L6:
                super.onMeasure(r2, r3);
            }

            @Override // org.telegram.ui.Cells.GroupCallUserCell
            public void onMuteClick(GroupCallUserCell r1) {
                GroupCallActivity.m15284$$Nest$mshowMenuForCell(this.this$1.this$0, r1);
            }
        }

        /* renamed from: org.telegram.ui.GroupCallActivity$ListAdapter$3, reason: invalid class name */
        public class AnonymousClass3 extends GroupCallInvitedCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass3(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Cells.GroupCallInvitedCell, android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                if (AndroidUtilities.isTablet() == false) goto L6;
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(420.0f), View.MeasureSpec.getSize(r2)), TLObject.FLAG_30), r3);
                return;
            L6:
                super.onMeasure(r2, r3);
            }
        }

        /* renamed from: org.telegram.ui.GroupCallActivity$ListAdapter$4, reason: invalid class name */
        public class AnonymousClass4 extends GroupCallGridCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass4(ListAdapter r1, Context r2, boolean r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Components.voip.GroupCallGridCell, android.view.ViewGroup, android.view.View
            public void onAttachedToWindow() {
                super.onAttachedToWindow();
                if (GroupCallActivity.m15128$$Nest$fgetlistView(this.this$1.this$0).getVisibility() == 0) goto L5;
                return;
            L5:
                if (GroupCallActivity.m15130$$Nest$fgetlistViewVideoVisibility(this.this$1.this$0) == false) goto L9;
                GroupCallActivity.m15265$$Nest$mattachRenderer(this.this$1.this$0, this, true);
                return;
            }

            @Override // org.telegram.ui.Components.voip.GroupCallGridCell, android.view.ViewGroup, android.view.View
            public void onDetachedFromWindow() {
                super.onDetachedFromWindow();
                GroupCallActivity.m15265$$Nest$mattachRenderer(this.this$1.this$0, this, false);
            }
        }

        /* renamed from: org.telegram.ui.GroupCallActivity$ListAdapter$5, reason: invalid class name */
        public class AnonymousClass5 extends View {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass5(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                if (GroupCallActivity.isLandscapeMode == false) goto L5;
                float r32 = 0.0f;
            L6:
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r32), TLObject.FLAG_30));
                return;
            L5:
                r32 = 8.0f;
                goto L6
            }
        }

        /* renamed from: -$$Nest$fgetaddMemberRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15318$$Nest$fgetaddMemberRow(ListAdapter r0) {
            return r0.addMemberRow;
        }

        /* renamed from: -$$Nest$fgetconferenceAddPeopleRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15319$$Nest$fgetconferenceAddPeopleRow(ListAdapter r0) {
            return r0.conferenceAddPeopleRow;
        }

        /* renamed from: -$$Nest$fgetconferenceShareLinkRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15320$$Nest$fgetconferenceShareLinkRow(ListAdapter r0) {
            return r0.conferenceShareLinkRow;
        }

        /* renamed from: -$$Nest$fgetencryptionRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15321$$Nest$fgetencryptionRow(ListAdapter r0) {
            return r0.encryptionRow;
        }

        /* renamed from: -$$Nest$fgetinvitedEndRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15322$$Nest$fgetinvitedEndRow(ListAdapter r0) {
            return r0.invitedEndRow;
        }

        /* renamed from: -$$Nest$fgetinvitedStartRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15323$$Nest$fgetinvitedStartRow(ListAdapter r0) {
            return r0.invitedStartRow;
        }

        /* renamed from: -$$Nest$fgetrowsCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15324$$Nest$fgetrowsCount(ListAdapter r0) {
            return r0.rowsCount;
        }

        /* renamed from: -$$Nest$fgetshadyJoinEndRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15325$$Nest$fgetshadyJoinEndRow(ListAdapter r0) {
            return r0.shadyJoinEndRow;
        }

        /* renamed from: -$$Nest$fgetshadyJoinStartRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15326$$Nest$fgetshadyJoinStartRow(ListAdapter r0) {
            return r0.shadyJoinStartRow;
        }

        /* renamed from: -$$Nest$fgetshadyLeftEndRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15327$$Nest$fgetshadyLeftEndRow(ListAdapter r0) {
            return r0.shadyLeftEndRow;
        }

        /* renamed from: -$$Nest$fgetshadyLeftStartRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15328$$Nest$fgetshadyLeftStartRow(ListAdapter r0) {
            return r0.shadyLeftStartRow;
        }

        /* renamed from: -$$Nest$fgetusersEndRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15329$$Nest$fgetusersEndRow(ListAdapter r0) {
            return r0.usersEndRow;
        }

        /* renamed from: -$$Nest$fgetusersStartRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15330$$Nest$fgetusersStartRow(ListAdapter r0) {
            return r0.usersStartRow;
        }

        /* renamed from: -$$Nest$fgetusersVideoGridEndRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15331$$Nest$fgetusersVideoGridEndRow(ListAdapter r0) {
            return r0.usersVideoGridEndRow;
        }

        /* renamed from: -$$Nest$fgetusersVideoGridStartRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15332$$Nest$fgetusersVideoGridStartRow(ListAdapter r0) {
            return r0.usersVideoGridStartRow;
        }

        /* renamed from: -$$Nest$fgetvideoGridDividerRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15333$$Nest$fgetvideoGridDividerRow(ListAdapter r0) {
            return r0.videoGridDividerRow;
        }

        /* renamed from: -$$Nest$fgetvideoNotAvailableRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15334$$Nest$fgetvideoNotAvailableRow(ListAdapter r0) {
            return r0.videoNotAvailableRow;
        }

        /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15335$$Nest$mupdateRows(ListAdapter r0) {
            r0.updateRows();
        }

        public ListAdapter(GroupCallActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private void updateRows() {
            ChatObject.Call r0 = this.this$0.call;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isScheduled() == false) goto L7;
            return;
        L7:
            if (GroupCallActivity.m15104$$Nest$fgetdelayedGroupCallUpdated(this.this$0) == true) goto L75;
            this.addMemberRow = -1;
            this.conferenceAddPeopleRow = -1;
            this.conferenceShareLinkRow = -1;
            this.videoGridDividerRow = -1;
            this.videoNotAvailableRow = -1;
            this.encryptionRow = -1;
            boolean r1 = false;
            this.rowsCount = 0;
            GroupCallActivity r2 = this.this$0;
            if (r2.call.participants.indexOfKey(MessageObject.getPeerId(GroupCallActivity.m15206$$Nest$fgetselfPeer(r2))) < 0) goto L12;
            r1 = true;
        L12:
            this.hasSelfUser = r1;
            if (this.this$0.isConference() == false) goto L15;
            int r12 = this.rowsCount;
            this.rowsCount = r12 + 1;
            this.encryptionRow = r12;
        L15:
            int r13 = this.rowsCount;
            this.usersVideoGridStartRow = r13;
            int r14 = r13 + this.this$0.visibleVideoParticipants.size();
            this.rowsCount = r14;
            this.usersVideoGridEndRow = r14;
            if (this.this$0.visibleVideoParticipants.size() <= 0) goto L19;
            int r15 = this.rowsCount;
            this.rowsCount = r15 + 1;
            this.videoGridDividerRow = r15;
        L19:
            if (this.this$0.visibleVideoParticipants.isEmpty() == false) goto L21;
        L25:
            this.usersStartRow = this.rowsCount;
            if (this.this$0.isRtmpStream() == true) goto L28;
            this.rowsCount += this.this$0.call.visibleParticipants.size();
        L28:
            this.usersEndRow = this.rowsCount;
            if (this.this$0.call.invitedUsers.isEmpty() == false) goto L31;
        L34:
            this.invitedStartRow = -1;
            this.invitedEndRow = -1;
        L36:
            if (this.this$0.call.shadyJoinParticipants.isEmpty() == false) goto L38;
        L41:
            this.shadyJoinStartRow = -1;
            this.shadyJoinEndRow = -1;
        L43:
            if (this.this$0.call.shadyLeftParticipants.isEmpty() == false) goto L45;
        L48:
            this.shadyLeftStartRow = -1;
            this.shadyLeftEndRow = -1;
        L50:
            if (this.this$0.isConference() == false) goto L53;
            int r02 = this.rowsCount;
            this.conferenceAddPeopleRow = r02;
            this.rowsCount = r02 + 2;
            this.conferenceShareLinkRow = r02 + 1;
        L71:
            int r03 = this.rowsCount;
            this.rowsCount = r03 + 1;
            this.lastRow = r03;
            return;
        L53:
            if (this.this$0.isRtmpStream() == true) goto L71;
            if (ChatObject.isChannel(this.this$0.currentChat) == false) goto L61;
            TLRPC.Chat r04 = this.this$0.currentChat;
            if (r04 == null) goto L63;
            if (r04.megagroup == true) goto L61;
        L63:
            if (ChatObject.isChannel(this.this$0.currentChat) == false) goto L71;
            TLRPC.Chat r05 = this.this$0.currentChat;
            if (r05 == null) goto L71;
            if (r05.megagroup == true) goto L71;
            if (ChatObject.isPublic(r05) == false) goto L71;
        L70:
            int r06 = this.rowsCount;
            this.rowsCount = r06 + 1;
            this.addMemberRow = r06;
        L61:
            if (ChatObject.canWriteToChat(this.this$0.currentChat) == true) goto L70;
        L45:
            if (this.this$0.isRtmpStream() == true) goto L48;
            int r07 = this.rowsCount;
            this.shadyLeftStartRow = r07;
            int r08 = r07 + this.this$0.call.shadyLeftParticipants.size();
            this.rowsCount = r08;
            this.shadyLeftEndRow = r08;
            goto L50
        L38:
            if (this.this$0.isRtmpStream() == true) goto L41;
            int r16 = this.rowsCount;
            this.shadyJoinStartRow = r16;
            int r17 = r16 + this.this$0.call.shadyJoinParticipants.size();
            this.rowsCount = r17;
            this.shadyJoinEndRow = r17;
            goto L43
        L31:
            if (this.this$0.isRtmpStream() == true) goto L34;
            int r18 = this.rowsCount;
            this.invitedStartRow = r18;
            int r19 = r18 + this.this$0.call.invitedUsers.size();
            this.rowsCount = r19;
            this.invitedEndRow = r19;
            goto L36
        L21:
            if (this.this$0.canManageCall() == false) goto L25;
            GroupCallActivity r110 = this.this$0;
            if (r110.call.call.participants_count <= GroupCallActivity.m15070$$Nest$fgetaccountInstance(r110).getMessagesController().groupCallVideoMaxParticipants) goto L25;
            int r111 = this.rowsCount;
            this.rowsCount = r111 + 1;
            this.videoNotAvailableRow = r111;
            goto L25
        }

        public boolean addSelfToCounter() {
            if (this.this$0.isRtmpStream() == false) goto L6;
            return false;
        L6:
            if (this.hasSelfUser == false) goto L8;
        L12:
            return false;
        L8:
            if (VoIPService.getSharedInstance() == null) goto L12;
            return !VoIPService.getSharedInstance().isJoined();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.rowsCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != this.lastRow) goto L7;
            return 3;
        L7:
            if (r2 != this.addMemberRow) goto L9;
            return 0;
        L9:
            if (r2 != this.conferenceAddPeopleRow) goto L11;
            return 0;
        L11:
            if (r2 != this.conferenceShareLinkRow) goto L14;
            return 0;
        L14:
            if (r2 != this.videoGridDividerRow) goto L18;
            return 5;
        L18:
            if (r2 < this.usersStartRow) goto L24;
            if (r2 >= this.usersEndRow) goto L24;
            return 1;
        L24:
            if (r2 < this.usersVideoGridStartRow) goto L30;
            if (r2 >= this.usersVideoGridEndRow) goto L30;
            return 4;
        L30:
            if (r2 != this.videoNotAvailableRow) goto L34;
            return 6;
        L34:
            if (r2 != this.encryptionRow) goto L37;
            return 7;
        L37:
            return 2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            int r0 = r1.getItemViewType();
            if (r0 != 3) goto L5;
            return false;
        L5:
            if (r0 != 4) goto L7;
            return false;
        L7:
            if (r0 != 5) goto L9;
            return false;
        L9:
            if (r0 == 6) goto L16;
            return true;
        L16:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            updateRows();
            super.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemChanged(int r1) {
            updateRows();
            super.notifyItemChanged(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemChanged(int r1, Object r2) {
            updateRows();
            super.notifyItemChanged(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemInserted(int r1) {
            updateRows();
            super.notifyItemInserted(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemMoved(int r1, int r2) {
            updateRows();
            super.notifyItemMoved(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeChanged(int r1, int r2) {
            updateRows();
            super.notifyItemRangeChanged(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeChanged(int r1, int r2, Object r3) {
            updateRows();
            super.notifyItemRangeChanged(r1, r2, r3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeInserted(int r1, int r2) {
            updateRows();
            super.notifyItemRangeInserted(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeRemoved(int r1, int r2) {
            updateRows();
            super.notifyItemRangeRemoved(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRemoved(int r1) {
            updateRows();
            super.notifyItemRemoved(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r18, int r19) {
            int r3 = r18.getItemViewType();
            float r4 = 1.0f;
            boolean r5 = true;
            if (r3 == 0) goto L105;
            TLRPC.FileLocation r7 = null;
            TLRPC.FileLocation r72 = null;
            r72 = null;
            Long r73 = null;
            r73 = null;
            r73 = null;
            r73 = null;
            r7 = null;
            if (r3 != 1) goto L7;
            GroupCallUserCell r8 = (GroupCallUserCell) r18.itemView;
            int r1 = r19 - this.usersStartRow;
            if (GroupCallActivity.m15104$$Nest$fgetdelayedGroupCallUpdated(this.this$0) == false) goto L84;
            if (r1 >= 0) goto L80;
        L83:
            TLRPC.GroupCallParticipant r10 = null;
        L88:
            if (r10 == null) goto L134;
            long r12 = MessageObject.getPeerId(r10.peer);
            long r122 = MessageObject.getPeerId(GroupCallActivity.m15206$$Nest$fgetselfPeer(this.this$0));
            if (r12 != r122) goto L94;
            AvatarUpdaterDelegate r32 = this.this$0.avatarUpdaterDelegate;
            if (r32 == null) goto L94;
            r7 = AvatarUpdaterDelegate.m15302$$Nest$fgetavatar(r32);
        L94:
            TLRPC.FileLocation r14 = r7;
            if (r14 == null) goto L98;
            r4 = this.this$0.avatarUpdaterDelegate.uploadingProgress;
        L98:
            if (r8.getParticipant() != null) goto L100;
        L102:
            boolean r15 = false;
        L103:
            r8.setData(GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0), r10, this.this$0.call, r122, r14, r15);
            r8.setUploadProgress(r4, r15);
            return;
        L100:
            if (MessageObject.getPeerId(r8.getParticipant().peer) != r12) goto L102;
            r15 = true;
            goto L103
        L134:
            return;
        L80:
            if (r1 >= GroupCallActivity.m15150$$Nest$fgetoldParticipants(this.this$0).size()) goto L83;
            TLRPC.GroupCallParticipant r13 = (TLRPC.GroupCallParticipant) GroupCallActivity.m15150$$Nest$fgetoldParticipants(this.this$0).get(r1);
        L82:
            r10 = r13;
            goto L88
        L84:
            if (r1 < 0) goto L83;
            if (r1 >= this.this$0.call.visibleParticipants.size()) goto L83;
            r13 = this.this$0.call.visibleParticipants.get(r1);
            goto L82
        L7:
            if (r3 != 2) goto L9;
            GroupCallInvitedCell r82 = (GroupCallInvitedCell) r18.itemView;
            int r16 = r19 - this.invitedStartRow;
            int r33 = this.shadyJoinStartRow;
            if ((r19 - r33) >= 0) goto L47;
        L50:
            int r34 = this.shadyLeftStartRow;
            if ((r19 - r34) < 0) goto L56;
            if ((r19 - r34) >= this.this$0.call.shadyLeftParticipants.size()) goto L56;
            r73 = this.this$0.call.shadyLeftParticipants.get(r19 - this.shadyLeftStartRow);
            boolean r132 = true;
            boolean r11 = false;
            boolean r123 = false;
        L49:
            Long r102 = r73;
            if (r102 == null) goto L133;
            r82.setData(GroupCallActivity.access$4300(this.this$0), r102, r11, r123, r132);
            return;
        L133:
            return;
        L56:
            if (GroupCallActivity.m15104$$Nest$fgetdelayedGroupCallUpdated(this.this$0) == false) goto L63;
            if (r16 >= 0) goto L59;
        L61:
            r11 = false;
            r123 = false;
        L62:
            r132 = r123;
            goto L49
        L59:
            if (r16 >= GroupCallActivity.m15147$$Nest$fgetoldInvited(this.this$0).size()) goto L61;
            r73 = (Long) GroupCallActivity.m15147$$Nest$fgetoldInvited(this.this$0).get(r16);
            goto L61
        L63:
            if (r16 < 0) goto L61;
            if (r16 >= this.this$0.call.invitedUsers.size()) goto L61;
            r73 = this.this$0.call.invitedUsers.get(r16);
            ChatObject.Call.InvitedUser r17 = this.this$0.call.invitedUsersMessageIds.get(r73);
            if (r17 != null) goto L69;
        L71:
            r5 = false;
        L72:
            r11 = r5;
            r123 = false;
            goto L62
        L69:
            if (r17.isCalling() == false) goto L71;
        L47:
            if ((r19 - r33) >= this.this$0.call.shadyJoinParticipants.size()) goto L50;
            r73 = this.this$0.call.shadyJoinParticipants.get(r19 - this.shadyJoinStartRow);
            r123 = true;
            r11 = false;
            r132 = false;
            goto L49
        L9:
            if (r3 != 4) goto L135;
            GroupCallGridCell r83 = (GroupCallGridCell) r18.itemView;
            ChatObject.VideoParticipant r110 = r83.getParticipant();
            int r35 = r19 - this.usersVideoGridStartRow;
            r83.spanCount = GroupCallActivity.m15212$$Nest$fgetspanSizeLookup(this.this$0).getSpanSize(r19);
            if (GroupCallActivity.m15104$$Nest$fgetdelayedGroupCallUpdated(this.this$0) == false) goto L19;
            if (r35 >= 0) goto L15;
        L18:
            ChatObject.VideoParticipant r103 = null;
        L23:
            if (r103 == null) goto L35;
            long r2 = MessageObject.getPeerId(r103.participant.peer);
            long r124 = MessageObject.getPeerId(GroupCallActivity.m15206$$Nest$fgetselfPeer(this.this$0));
            if (r2 != r124) goto L29;
            AvatarUpdaterDelegate r22 = this.this$0.avatarUpdaterDelegate;
            if (r22 == null) goto L29;
            r72 = AvatarUpdaterDelegate.m15302$$Nest$fgetavatar(r22);
        L29:
            if (r72 == null) goto L32;
            float r23 = this.this$0.avatarUpdaterDelegate.uploadingProgress;
        L32:
            if (r83.getParticipant() == null) goto L34;
            r83.getParticipant().equals(r103);
        L34:
            r83.setData(GroupCallActivity.m15070$$Nest$fgetaccountInstance(this.this$0), r103, this.this$0.call, r124);
        L35:
            if (r110 != null) goto L37;
            return;
        L37:
            if (r110.equals(r103) == false) goto L39;
            return;
        L39:
            if (r83.attached == true) goto L41;
            return;
        L41:
            if (r83.getRenderer() == null) goto L132;
            GroupCallActivity.m15265$$Nest$mattachRenderer(this.this$0, r83, false);
            GroupCallActivity.m15265$$Nest$mattachRenderer(this.this$0, r83, true);
            return;
        L132:
            return;
        L15:
            if (r35 >= GroupCallActivity.m15163$$Nest$fgetoldVideoParticipants(this.this$0).size()) goto L18;
            ChatObject.VideoParticipant r24 = (ChatObject.VideoParticipant) GroupCallActivity.m15163$$Nest$fgetoldVideoParticipants(this.this$0).get(r35);
        L17:
            r103 = r24;
            goto L23
        L19:
            if (r35 < 0) goto L18;
            if (r35 >= this.this$0.visibleVideoParticipants.size()) goto L18;
            r24 = this.this$0.visibleVideoParticipants.get(r35);
            goto L17
        L135:
            return;
        L105:
            GroupCallTextCell r111 = (GroupCallTextCell) r18.itemView;
            if (r19 != this.conferenceAddPeopleRow) goto L110;
            int r0 = Theme.getColor(Theme.key_windowBackgroundWhiteBlueIcon, GroupCallActivity.access$4100(this.this$0));
            r111.setColors(r0, r0);
            r111.setTextAndIcon(LocaleController.getString(R.string.VoipConferenceAddPeople), R.drawable.msg_contact_add, true);
            return;
        L110:
            if (r19 != this.conferenceShareLinkRow) goto L113;
            int r02 = Theme.getColor(Theme.key_windowBackgroundWhiteBlueIcon, GroupCallActivity.access$4200(this.this$0));
            r111.setColors(r02, r02);
            r111.setTextAndIcon(LocaleController.getString(R.string.VoipConferenceShareLink), R.drawable.msg_link2, false);
            return;
        L113:
            int r25 = Theme.getColor(Theme.key_voipgroup_lastSeenTextUnscrolled);
            int r36 = Theme.getColor(Theme.key_voipgroup_lastSeenText);
            if (GroupCallActivity.m15072$$Nest$fgetactionBar(this.this$0).getTag() == null) goto L116;
            float r52 = 1.0f;
        L117:
            int r26 = AndroidUtilities.getOffsetColor(r25, r36, r52, 1.0f);
            r111.setColors(r26, r26);
            if (ChatObject.isChannel(this.this$0.currentChat) == false) goto L127;
            TLRPC.Chat r03 = this.this$0.currentChat;
            if (r03 == null) goto L127;
            if (r03.megagroup == true) goto L127;
            if (ChatObject.isPublic(r03) == false) goto L127;
            r111.setTextAndIcon(LocaleController.getString(R.string.VoipGroupShareLink), R.drawable.msg_link, false);
            return;
        L127:
            r111.setTextAndIcon(LocaleController.getString(R.string.VoipGroupInviteMember), R.drawable.msg_contact_add, false);
            return;
        L116:
            r52 = 0.0f;
            goto L117
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L4;
            View r32 = new AnonymousClass1(this, this.mContext);
        L30:
            r32.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r32);
        L4:
            if (r4 != 1) goto L6;
            r32 = new AnonymousClass2(this, this.mContext);
            goto L30
        L6:
            if (r4 != 2) goto L8;
            r32 = new AnonymousClass3(this, this.mContext);
            goto L30
        L8:
            if (r4 != 4) goto L10;
            r32 = new AnonymousClass4(this, this.mContext, false);
            goto L30
        L10:
            if (r4 != 5) goto L12;
            r32 = new AnonymousClass5(this, this.mContext);
            goto L30
        L12:
            if (r4 != 6) goto L14;
            TextView r42 = new TextView(this.mContext);
            r42.setTextColor(-8682615);
            r42.setTextSize(1, 13.0f);
            r42.setGravity(1);
            r42.setPadding(0, 0, 0, AndroidUtilities.dp(10.0f));
            boolean r33 = ChatObject.isChannelOrGiga(this.this$0.currentChat);
            GroupCallActivity r2 = this.this$0;
            if (r33 == false) goto L23;
            r42.setText(LocaleController.formatString(R.string.VoipChannelVideoNotAvailableAdmin, new Object[]{LocaleController.formatPluralString("Participants", GroupCallActivity.m15070$$Nest$fgetaccountInstance(r2).getMessagesController().groupCallVideoMaxParticipants, new Object[0])}));
        L24:
            r32 = r42;
            goto L30
        L23:
            r42.setText(LocaleController.formatString(R.string.VoipVideoNotAvailableAdmin, new Object[]{LocaleController.formatPluralString("Members", GroupCallActivity.m15070$$Nest$fgetaccountInstance(r2).getMessagesController().groupCallVideoMaxParticipants, new Object[0])}));
            goto L24
        L14:
            if (r4 == 7) goto L17;
            r32 = new View(this.mContext);
            goto L30
        L17:
            if (GroupCallActivity.m15108$$Nest$fgetencryptionDrawable(this.this$0) != null) goto L19;
            GroupCallActivity.m15236$$Nest$fputencryptionDrawable(this.this$0, new CallEncryptionCellDrawable(this.mContext));
        L19:
            r32 = new CallEncryptionCell(this.mContext, GroupCallActivity.m15108$$Nest$fgetencryptionDrawable(this.this$0));
            goto L30
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r7) {
            int r0 = r7.getItemViewType();
            boolean r1 = false;
            if (r0 != 1) goto L13;
            GroupCallUserCell r02 = (GroupCallUserCell) r7.itemView;
            if (GroupCallActivity.m15072$$Nest$fgetactionBar(this.this$0).getTag() == null) goto L7;
            int r4 = Theme.key_voipgroup_mutedIcon;
        L8:
            r02.setGrayIconColor(r4, Theme.getColor(r4));
            if (r7.getAdapterPosition() == (getItemCount() - 2)) goto L11;
            r1 = true;
        L11:
            r02.setDrawDivider(r1);
            return;
        L7:
            r4 = Theme.key_voipgroup_mutedIconUnscrolled;
            goto L8
        L13:
            if (r0 != 2) goto L23;
            GroupCallInvitedCell r03 = (GroupCallInvitedCell) r7.itemView;
            if (GroupCallActivity.m15072$$Nest$fgetactionBar(this.this$0).getTag() == null) goto L17;
            int r42 = Theme.key_voipgroup_mutedIcon;
        L18:
            r03.setGrayIconColor(r42, Theme.getColor(r42));
            if (r7.getAdapterPosition() == (getItemCount() - 2)) goto L21;
            r1 = true;
        L21:
            r03.setDrawDivider(r1);
            return;
        L17:
            r42 = Theme.key_voipgroup_mutedIconUnscrolled;
            goto L18
        }
    }

    /* loaded from: classes6.dex */
    public static class RecordCallDrawable extends Drawable {
        private float alpha;
        private long lastUpdateTime;
        private Paint paint;
        private Paint paint2;
        private View parentView;
        private boolean recording;
        private int state;

        public RecordCallDrawable() {
            this.paint = new Paint(1);
            this.paint2 = new Paint(1);
            this.alpha = 1.0f;
            this.paint.setColor(-1);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(AndroidUtilities.dp(1.5f));
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r7) {
            float r0 = getBounds().centerX();
            float r1 = getBounds().centerY();
            r7.drawCircle(r0, r1, AndroidUtilities.dp(10.0f), this.paint);
            Paint r2 = this.paint2;
            if (this.recording == false) goto L5;
            int r3 = -1147527;
        L6:
            r2.setColor(r3);
            this.paint2.setAlpha((int) (this.alpha * 255.0f));
            r7.drawCircle(r0, r1, AndroidUtilities.dp(5.0f), this.paint2);
            if (this.recording == false) goto L22;
            long r02 = SystemClock.elapsedRealtime();
            long r22 = r02 - this.lastUpdateTime;
            if (r22 <= 17) goto L11;
            r22 = 17;
        L11:
            this.lastUpdateTime = r02;
            int r72 = this.state;
            if (r72 != 0) goto L16;
            float r73 = this.alpha + (r22 / 2000.0f);
            this.alpha = r73;
            if (r73 < 1.0f) goto L20;
            this.alpha = 1.0f;
            this.state = 1;
        L20:
            this.parentView.invalidate();
            return;
        L16:
            if (r72 != 1) goto L20;
            float r74 = this.alpha - (r22 / 2000.0f);
            this.alpha = r74;
            if (r74 >= 0.5f) goto L20;
            this.alpha = 0.5f;
            this.state = 0;
            goto L20
        L22:
            return;
        L5:
            r3 = -1;
            goto L6
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(24.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(24.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }

        public void setParentView(View r1) {
            this.parentView = r1;
        }

        public void setRecording(boolean r1) {
            this.recording = r1;
            this.alpha = 1.0f;
            invalidateSelf();
        }
    }

    /* loaded from: classes6.dex */
    public static class SmallRecordCallDrawable extends Drawable {
        private float alpha;
        private long lastUpdateTime;
        private Paint paint2;
        private View parentView;
        private int state;

        public SmallRecordCallDrawable(View r3) {
            this.paint2 = new Paint(1);
            this.alpha = 1.0f;
            this.parentView = r3;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r9) {
            int r0 = getBounds().centerX();
            int r1 = getBounds().centerY();
            if ((this.parentView instanceof SimpleTextView) == false) goto L5;
            int r12 = r1 + AndroidUtilities.dp(1.0f);
            r0 = r0 - AndroidUtilities.dp(3.0f);
        L6:
            this.paint2.setColor(-1147527);
            this.paint2.setAlpha((int) (this.alpha * 255.0f));
            r9.drawCircle(r0, r12, AndroidUtilities.dp(4.0f), this.paint2);
            long r02 = SystemClock.elapsedRealtime();
            long r4 = r02 - this.lastUpdateTime;
            if (r4 <= 17) goto L9;
            r4 = 17;
        L9:
            this.lastUpdateTime = r02;
            int r92 = this.state;
            if (r92 != 0) goto L14;
            float r93 = this.alpha + (r4 / 2000.0f);
            this.alpha = r93;
            if (r93 < 1.0f) goto L18;
            this.alpha = 1.0f;
            this.state = 1;
        L18:
            this.parentView.invalidate();
            return;
        L14:
            if (r92 != 1) goto L18;
            float r94 = this.alpha - (r4 / 2000.0f);
            this.alpha = r94;
            if (r94 >= 0.5f) goto L18;
            this.alpha = 0.5f;
            this.state = 0;
            goto L18
        L5:
            r12 = r1 + AndroidUtilities.dp(2.0f);
            goto L6
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(24.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(24.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* loaded from: classes6.dex */
    public static class UpdateCallback implements ListUpdateCallback {
        final RecyclerView.Adapter adapter;

        private UpdateCallback(RecyclerView.Adapter r1) {
            this.adapter = r1;
        }

        public /* synthetic */ UpdateCallback(RecyclerView.Adapter r1, GroupCallActivityIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onChanged(int r1, int r2, Object r3) {
            this.adapter.notifyItemRangeChanged(r1, r2, r3);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onInserted(int r1, int r2) {
            this.adapter.notifyItemRangeInserted(r1, r2);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onMoved(int r1, int r2) {
            this.adapter.notifyItemMoved(r1, r2);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onRemoved(int r1, int r2) {
            this.adapter.notifyItemRangeRemoved(r1, r2);
        }
    }

    /* loaded from: classes6.dex */
    public class VolumeSlider extends FrameLayout {
        private boolean captured;
        private float colorChangeProgress;
        private int currentColor;
        private TLRPC.GroupCallParticipant currentParticipant;
        private double currentProgress;
        private boolean dragging;
        private RLottieImageView imageView;
        private long lastUpdateTime;
        private int oldColor;
        private Paint paint;
        private Paint paint2;
        private Path path;
        private float[] radii;
        private RectF rect;
        private RLottieDrawable speakerDrawable;
        private float sx;
        private float sy;
        private TextView textView;
        final /* synthetic */ GroupCallActivity this$0;
        private int thumbX;
        private float[] volumeAlphas;

        public VolumeSlider(GroupCallActivity r19, Context r20, TLRPC.GroupCallParticipant r21) {
            this.this$0 = r19;
            super(r20);
            this.paint = new Paint(1);
            this.paint2 = new Paint(1);
            this.path = new Path();
            this.radii = new float[8];
            this.rect = new RectF();
            int r2 = 3;
            this.volumeAlphas = new float[3];
            setWillNotDraw(false);
            this.currentParticipant = r21;
            this.currentProgress = ChatObject.getParticipantVolume(r21) / 20000.0f;
            this.colorChangeProgress = 1.0f;
            setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
            this.speakerDrawable = new RLottieDrawable(R.raw.speaker, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), true, null);
            RLottieImageView r6 = new RLottieImageView(r20);
            this.imageView = r6;
            r6.setScaleType(ImageView.ScaleType.CENTER);
            this.imageView.setAnimation(this.speakerDrawable);
            RLottieImageView r62 = this.imageView;
            double r9 = 0.0d;
            if (this.currentProgress != 0.0d) goto L5;
            Integer r7 = 1;
        L6:
            r62.setTag(r7);
            RLottieImageView r63 = this.imageView;
            if (LocaleController.isRTL == false) goto L9;
            int r72 = 5;
        L10:
            addView(r63, LayoutHelper.createFrame(-2, 40.0f, r72 | 16, 0.0f, 0.0f, 0.0f, 0.0f));
            RLottieDrawable r64 = this.speakerDrawable;
            if (this.currentProgress != 0.0d) goto L13;
            int r73 = 17;
        L14:
            r64.setCustomEndFrame(r73);
            RLottieDrawable r65 = this.speakerDrawable;
            r65.setCurrentFrame(r65.getCustomEndFrame() - 1, false, true);
            TextView r66 = new TextView(r20);
            this.textView = r66;
            r66.setLines(1);
            this.textView.setSingleLine(true);
            this.textView.setGravity(3);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setTextColor(Theme.getColor(Theme.key_voipgroup_actionBarItems));
            this.textView.setTextSize(1, 16.0f);
            double r67 = ChatObject.getParticipantVolume(this.currentParticipant) / 100.0d;
            TextView r1 = this.textView;
            Locale r13 = Locale.US;
            if (r67 <= 0.0d) goto L17;
            r9 = Math.max(r67, 1.0d);
        L17:
            r1.setText(String.format(r13, "%d%%", new Object[]{Integer.valueOf((int) r9)}));
            TextView r12 = this.textView;
            if (LocaleController.isRTL == false) goto L20;
            int r68 = 0;
        L22:
            if (LocaleController.isRTL == false) goto L24;
            int r74 = AndroidUtilities.dp(43.0f);
        L25:
            r12.setPadding(r68, 0, r74, 0);
            TextView r14 = this.textView;
            if (LocaleController.isRTL == false) goto L28;
            r2 = 5;
        L28:
            addView(r14, LayoutHelper.createFrame(-2, -2, r2 | 16));
            this.paint2.setStyle(Paint.Style.STROKE);
            this.paint2.setStrokeWidth(AndroidUtilities.dp(1.5f));
            this.paint2.setStrokeCap(Paint.Cap.ROUND);
            this.paint2.setColor(-1);
            int r15 = (int) (ChatObject.getParticipantVolume(this.currentParticipant) / 100.0d);
            int r22 = 0;
        L29:
            float[] r69 = this.volumeAlphas;
            if (r22 >= r69.length) goto L40;
            if (r22 != 0) goto L33;
            int r75 = 0;
        L36:
            if (r15 <= r75) goto L38;
            r69[r22] = 1.0f;
        L39:
            r22 = r22 + 1;
            goto L29
        L38:
            r69[r22] = 0.0f;
            goto L39
        L33:
            if (r22 != 1) goto L35;
            r75 = 50;
            goto L36
        L35:
            r75 = 150;
            goto L36
        L40:
            return;
        L24:
            r74 = 0;
            goto L25
        L20:
            r68 = AndroidUtilities.dp(43.0f);
            goto L22
        L13:
            r73 = 34;
            goto L14
        L9:
            r72 = 3;
            goto L10
        L5:
            r7 = null;
            goto L6
        }

        private void onSeekBarDrag(double r13, boolean r15) {
            if (VoIPService.getSharedInstance() == null) goto L48;
            this.currentProgress = r13;
            TLRPC.GroupCallParticipant r0 = this.currentParticipant;
            r0.volume = (int) (r13 * 20000.0d);
            int r132 = 0;
            r0.volume_by_admin = false;
            r0.flags |= 128;
            double r02 = ChatObject.getParticipantVolume(r0) / 100.0d;
            TextView r14 = this.textView;
            Locale r2 = Locale.US;
            if (r02 <= 0.0d) goto L8;
            double r03 = Math.max(r02, 1.0d);
        L9:
            r14.setText(String.format(r2, "%d%%", new Object[]{Integer.valueOf((int) r03)}));
            VoIPService r142 = VoIPService.getSharedInstance();
            TLRPC.GroupCallParticipant r04 = this.currentParticipant;
            r142.setParticipantVolume(r04, r04.volume);
            Integer r05 = null;
            if (r15 == false) goto L28;
            long r1 = MessageObject.getPeerId(this.currentParticipant.peer);
            GroupCallActivity r5 = this.this$0;
            if (r1 <= 0) goto L15;
            TLObject r152 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(r5).getMessagesController().getUser(Long.valueOf(r1));
        L14:
            TLObject r6 = r152;
            if (this.currentParticipant.volume == 0) goto L19;
            VoIPService.getSharedInstance().editCallMember(r6, null, null, Integer.valueOf(this.currentParticipant.volume), null, null);
            goto L28
        L19:
            if (GroupCallActivity.m15202$$Nest$fgetscrimPopupWindow(this.this$0) == null) goto L21;
            GroupCallActivity.m15202$$Nest$fgetscrimPopupWindow(this.this$0).dismiss();
            GroupCallActivity.m15256$$Nest$fputscrimPopupWindow(this.this$0, null);
        L21:
            GroupCallActivity.m15270$$Nest$mdismissAvatarPreview(this.this$0, true);
            GroupCallActivity r153 = this.this$0;
            TLRPC.GroupCallParticipant r52 = this.currentParticipant;
            if (r153.canManageCall() == false) goto L24;
            int r62 = 0;
        L25:
            GroupCallActivity.m15279$$Nest$mprocessSelectedOption(r153, r52, r1, r62);
            goto L28
        L24:
            r62 = 5;
            goto L25
        L15:
            r152 = GroupCallActivity.m15070$$Nest$fgetaccountInstance(r5).getMessagesController().getChat(Long.valueOf(-r1));
        L28:
            if (this.currentProgress != 0.0d) goto L31;
            r05 = 1;
        L31:
            if (this.imageView.getTag() != null) goto L34;
            if (r05 == null) goto L34;
        L36:
            RLottieDrawable r143 = this.speakerDrawable;
            if (this.currentProgress != 0.0d) goto L39;
            int r154 = 17;
        L40:
            r143.setCustomEndFrame(r154);
            RLottieDrawable r144 = this.speakerDrawable;
            if (this.currentProgress == 0.0d) goto L44;
            r132 = 17;
        L44:
            r144.setCurrentFrame(r132);
            this.speakerDrawable.start();
            this.imageView.setTag(r05);
            return;
        L39:
            r154 = 34;
        L34:
            if (this.imageView.getTag() == null) goto L46;
            if (r05 == null) goto L36;
            return;
        L46:
            return;
        L8:
            r03 = 0.0d;
            goto L9
        }

        @Override // android.view.View
        public void onDraw(Canvas r20) {
            int r1 = this.currentColor;
            double r2 = this.currentProgress;
            if (r2 >= 0.25d) goto L6;
            this.currentColor = -3385513;
        L16:
            float r22 = 0.0f;
            float r3 = 1.0f;
            if (r1 != 0) goto L19;
            int r12 = this.currentColor;
            this.colorChangeProgress = 1.0f;
        L23:
            this.paint.setColor(r12);
            long r4 = SystemClock.elapsedRealtime();
            long r6 = r4 - this.lastUpdateTime;
            if (r6 <= 17) goto L26;
            r6 = 17;
        L26:
            this.lastUpdateTime = r4;
            float r13 = this.colorChangeProgress;
            if (r13 >= 1.0f) goto L32;
            float r14 = r13 + (r6 / 200.0f);
            this.colorChangeProgress = r14;
            if (r14 <= 1.0f) goto L31;
            this.colorChangeProgress = 1.0f;
            goto L32
        L31:
            invalidate();
        L32:
            this.path.reset();
            float[] r15 = this.radii;
            float r42 = 6.0f;
            float r5 = AndroidUtilities.dp(6.0f);
            r15[7] = r5;
            r15[6] = r5;
            int r8 = 1;
            r15[1] = r5;
            int r9 = 0;
            r15[0] = r5;
            if (this.thumbX >= AndroidUtilities.dp(12.0f)) goto L35;
            float r16 = Math.max(0.0f, (this.thumbX - AndroidUtilities.dp(6.0f)) / AndroidUtilities.dp(6.0f));
        L36:
            float[] r52 = this.radii;
            float r10 = AndroidUtilities.dp(6.0f) * r16;
            r52[5] = r10;
            r52[4] = r10;
            r52[3] = r10;
            r52[2] = r10;
            this.rect.set(0.0f, 0.0f, this.thumbX, getMeasuredHeight());
            this.path.addRoundRect(this.rect, this.radii, Path.Direction.CW);
            this.path.close();
            Canvas r11 = r20;
            r11.drawPath(this.path, this.paint);
            int r53 = (int) (ChatObject.getParticipantVolume(this.currentParticipant) / 100.0d);
            int r102 = (this.imageView.getLeft() + (this.imageView.getMeasuredWidth() / 2)) + AndroidUtilities.dp(5.0f);
            int r17 = this.imageView.getTop() + (this.imageView.getMeasuredHeight() / 2);
            int r122 = 0;
        L38:
            if (r122 >= this.volumeAlphas.length) goto L59;
            if (r122 != 0) goto L41;
            float r132 = AndroidUtilities.dp(r42);
            int r142 = r9;
        L44:
            float r152 = AndroidUtilities.dp(2.0f);
            float r172 = r22;
            float r23 = this.volumeAlphas[r122];
            float r153 = r152 * (r3 - r23);
            float r18 = r3;
            this.paint2.setAlpha((int) (r23 * 255.0f));
            float r32 = r102;
            float r82 = r17;
            this.rect.set((r32 - r132) + r153, (r82 - r132) + r153, (r32 + r132) - r153, (r82 + r132) - r153);
            int r92 = r122;
            int r33 = r142;
            r11.drawArc(this.rect, -50.0f, 100.0f, false, this.paint2);
            float[] r24 = this.volumeAlphas;
            if (r53 <= r33) goto L52;
            float r34 = r24[r92];
            if (r34 >= r18) goto L58;
            float r35 = r34 + (r6 / 180.0f);
            r24[r92] = r35;
            if (r35 <= r18) goto L51;
            r24[r92] = r18;
        L51:
            invalidate();
        L58:
            r122 = r92 + 1;
            r11 = r20;
            r22 = r172;
            r3 = r18;
            r42 = 6.0f;
            r8 = 1;
            r9 = 0;
            goto L38
        L52:
            float r36 = r24[r92];
            if (r36 <= r172) goto L58;
            float r37 = r36 - (r6 / 180.0f);
            r24[r92] = r37;
            if (r37 >= r172) goto L57;
            r24[r92] = r172;
        L57:
            invalidate();
            goto L58
        L41:
            if (r122 != r8) goto L43;
            r132 = AndroidUtilities.dp(10.0f);
            r142 = 50;
            goto L44
        L43:
            r132 = AndroidUtilities.dp(14.0f);
            r142 = 150;
            goto L44
        L59:
            return;
        L35:
            r16 = 1.0f;
            goto L36
        L19:
            int r43 = AndroidUtilities.getOffsetColor(this.oldColor, r1, this.colorChangeProgress, 1.0f);
            if (r1 == this.currentColor) goto L22;
            this.colorChangeProgress = 0.0f;
            this.oldColor = r43;
        L22:
            r12 = r43;
            goto L23
        L6:
            if (r2 <= 0.25d) goto L11;
            if (r2 >= 0.5d) goto L11;
            this.currentColor = -3562181;
        L11:
            if (r2 >= 0.5d) goto L13;
        L15:
            this.currentColor = -11688225;
            goto L16
        L13:
            if (r2 > 0.75d) goto L15;
            this.currentColor = -11027349;
            goto L16
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r1) {
            return onTouch(r1);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
            this.thumbX = (int) (View.MeasureSpec.getSize(r3) * this.currentProgress);
        }

        public boolean onTouch(MotionEvent r8) {
            if (r8.getAction() != 0) goto L7;
            this.sx = r8.getX();
            this.sy = r8.getY();
            return true;
        L7:
            if (r8.getAction() != 1) goto L9;
        L42:
            this.captured = false;
            if (r8.getAction() != 1) goto L54;
            ViewConfiguration r0 = ViewConfiguration.get(getContext());
            if (Math.abs(r8.getY() - this.sy) >= r0.getScaledTouchSlop()) goto L54;
            int r02 = (int) r8.getX();
            this.thumbX = r02;
            if (r02 >= 0) goto L50;
            this.thumbX = 0;
        L52:
            this.dragging = true;
            goto L54
        L50:
            if (r02 <= getMeasuredWidth()) goto L52;
            this.thumbX = getMeasuredWidth();
        L54:
            if (this.dragging == true) goto L56;
        L60:
            return false;
        L56:
            if (r8.getAction() != 1) goto L58;
            onSeekBarDrag(this.thumbX / getMeasuredWidth(), true);
        L58:
            this.dragging = false;
            invalidate();
            return true;
        L9:
            if (r8.getAction() == 3) goto L42;
            if (r8.getAction() != 2) goto L60;
            if (this.captured == true) goto L33;
            ViewConfiguration r03 = ViewConfiguration.get(getContext());
            if (Math.abs(r8.getY() - this.sy) <= r03.getScaledTouchSlop()) goto L19;
            return false;
        L19:
            if (Math.abs(r8.getX() - this.sx) <= r03.getScaledTouchSlop()) goto L60;
            this.captured = true;
            getParent().requestDisallowInterceptTouchEvent(true);
            if (r8.getY() < 0.0f) goto L60;
            if (r8.getY() > getMeasuredHeight()) goto L60;
            int r82 = (int) r8.getX();
            this.thumbX = r82;
            if (r82 >= 0) goto L28;
            this.thumbX = 0;
        L30:
            this.dragging = true;
            invalidate();
            return true;
        L28:
            if (r82 <= getMeasuredWidth()) goto L30;
            this.thumbX = getMeasuredWidth();
            goto L30
        L33:
            if (this.dragging == false) goto L60;
            int r83 = (int) r8.getX();
            this.thumbX = r83;
            if (r83 >= 0) goto L38;
            this.thumbX = 0;
        L40:
            onSeekBarDrag(this.thumbX / getMeasuredWidth(), false);
            invalidate();
            return true;
        L38:
            if (r83 <= getMeasuredWidth()) goto L40;
            this.thumbX = getMeasuredWidth();
            goto L40
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r1) {
            return onTouch(r1);
        }
    }

    /* loaded from: classes6.dex */
    public class WatchersView extends LinearLayout {
        private float lastWidth;
        final /* synthetic */ GroupCallActivity this$0;
        private final AnimatedTextView watchersCountTextView;

        public WatchersView(GroupCallActivity r4, Context r5) {
            this.this$0 = r4;
            super(r5);
            this.lastWidth = 0.0f;
            setOrientation(1);
            setGravity(17);
            AnimatedTextView r0 = new AnimatedTextView(r5, true, false, false);
            this.watchersCountTextView = r0;
            r0.setTextColor(-1);
            r0.setTextSize(AndroidUtilities.dp(46.0f));
            r0.setTypeface(AndroidUtilities.bold());
            r0.setGravity(1);
            TextView r2 = new TextView(r5);
            r2.setTextColor(-1);
            r2.setTextSize(1, 14.0f);
            r2.setTypeface(AndroidUtilities.bold());
            r2.setText(LocaleController.getString(R.string.VoipChannelWatching));
            addView(r0, LayoutHelper.createLinear(-1, 46));
            addView(r2, LayoutHelper.createLinear(-2, -2));
        }

        public void setWatchersCount(int r12) {
            String r122 = LocaleController.formatNumber(r12, ',');
            float r6 = this.watchersCountTextView.getPaint().measureText(r122, 0, r122.length());
            if (this.lastWidth == r6) goto L5;
            this.watchersCountTextView.getPaint().setShader(new LinearGradient(0.0f, 0.0f, r6, 0.0f, new int[]{GroupCallActivity.access$5200(this.this$0, Theme.key_premiumGradient1), GroupCallActivity.access$5300(this.this$0, Theme.key_premiumGradient3)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
            this.lastWidth = r6;
        L5:
            this.watchersCountTextView.setText(r122);
        }
    }

    /* loaded from: classes6.dex */
    public static class WeavingState {
        public int currentState;
        private float duration;
        private final Matrix matrix;
        public Shader shader;
        private float startX;
        private float startY;
        private float targetX;
        private float targetY;
        private float time;

        public WeavingState(int r2) {
            this.targetX = -1.0f;
            this.targetY = -1.0f;
            this.matrix = new Matrix();
            this.currentState = r2;
        }

        private void setTarget() {
            if (GroupCallActivity.isGradientState(this.currentState) == false) goto L7;
            this.targetX = ((Utilities.random.nextInt(100) * 0.2f) / 100.0f) + 0.85f;
            this.targetY = 1.0f;
            return;
        L7:
            if (this.currentState != 1) goto L10;
            this.targetX = ((Utilities.random.nextInt(100) * 0.3f) / 100.0f) + 0.2f;
            this.targetY = ((Utilities.random.nextInt(100) * 0.3f) / 100.0f) + 0.7f;
            return;
        L10:
            this.targetX = ((Utilities.random.nextInt(100) / 100.0f) * 0.2f) + 0.8f;
            this.targetY = Utilities.random.nextInt(100) / 100.0f;
        }

        public void update(int r4, int r5, int r6, long r7, float r9) {
            if (this.shader != null) goto L5;
            return;
        L5:
            float r0 = this.duration;
            if (r0 != 0.0f) goto L8;
        L9:
            this.duration = Utilities.random.nextInt(200) + 1500;
            this.time = 0.0f;
            if (this.targetX != (-1.0f)) goto L12;
            setTarget();
        L12:
            this.startX = this.targetX;
            this.startY = this.targetY;
            setTarget();
        L13:
            float r72 = r7;
            float r02 = this.time + (((BlobDrawable.GRADIENT_SPEED_MIN + 0.5f) * r72) + ((r72 * (BlobDrawable.GRADIENT_SPEED_MAX * 2.0f)) * r9));
            this.time = r02;
            float r73 = this.duration;
            if (r02 <= r73) goto L16;
            this.time = r73;
        L16:
            float r74 = CubicBezierInterpolator.EASE_OUT.getInterpolation(this.time / r73);
            float r62 = r6;
            float r8 = this.startX;
            float r52 = (r5 + ((r8 + ((this.targetX - r8) * r74)) * r62)) - 200.0f;
            float r92 = this.startY;
            float r42 = (r4 + (r62 * (r92 + ((this.targetY - r92) * r74)))) - 200.0f;
            if (GroupCallActivity.isGradientState(this.currentState) == false) goto L20;
            float r63 = 1.0f;
        L23:
            float r75 = (AndroidUtilities.dp(122.0f) / 400.0f) * r63;
            this.matrix.reset();
            this.matrix.postTranslate(r52, r42);
            this.matrix.postScale(r75, r75, r52 + 200.0f, r42 + 200.0f);
            this.shader.setLocalMatrix(this.matrix);
            return;
        L20:
            if (this.currentState != 1) goto L22;
            r63 = 4.0f;
            goto L23
        L22:
            r63 = 2.5f;
            goto L23
        L8:
            if (this.time < r0) goto L13;
            goto L9
        }
    }

    /* renamed from: $r8$lambda$-Uf5C4PepSZMADmv-j-0r9gGPVg, reason: not valid java name */
    public static /* synthetic */ void m15041$r8$lambda$Uf5C4PepSZMADmvj0r9gGPVg(EditTextBoldCursor r0) {
        AndroidUtilities.showKeyboard(r0);
    }

    /* renamed from: $r8$lambda$-iJ5yb8-KyeJUzdJeUK4O7yb2dk, reason: not valid java name */
    public static /* synthetic */ void m15042$r8$lambda$iJ5yb8KyeJUzdJeUK4O7yb2dk(GroupCallActivity r0, TLRPC.TL_error r1) {
        r0.lambda$new$43(r1);
    }

    public static /* synthetic */ void $r8$lambda$0H5g3hNOrQaPOtNEB2P8eV4lhq0(GroupCallActivity r0, DialogInterface r1) {
        r0.lambda$new$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$0xxdAVGfAvzSbrIre31xHR0566Y(GroupCallActivity r0, View r1) {
        r0.lambda$new$33(r1);
    }

    public static /* synthetic */ void $r8$lambda$1TxI6dO9B3e9XdTkBQErZlbXMo0(GroupCallActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$new$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$22voPbDCKEBJ_WjcrpBA96B5F6o(GroupCallActivity r0) {
        r0.lambda$processSelectedOption$72();
    }

    public static /* synthetic */ void $r8$lambda$5w6tLTwxz_75uKnWsnpVUxhxPJE(ChatObject.Call r0, CheckBoxCell[] r1, long r2, Runnable r4, AlertDialog r5, int r6) {
        boolean r52 = false;
        CheckBoxCell r12 = r1[0];
        if (r12 != null) goto L5;
    L7:
        processOnLeave(r0, r52, r2, r4);
        return;
    L5:
        if (r12.isChecked() == false) goto L7;
        r52 = true;
        goto L7
    }

    /* renamed from: $r8$lambda$6-lxsL_1Ig_iXmN1RQJ87LEysRE, reason: not valid java name */
    public static /* synthetic */ void m15043$r8$lambda$6lxsL_1Ig_iXmN1RQJ87LEysRE(GroupCallActivity r0, AlertDialog[] r1, int r2) {
        r0.lambda$inviteUserToCall$65(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$6C18FqzGtdgNCLAF6199A9iBxbo(GroupCallActivity r0, DialogInterface r1) {
        r0.lambda$openShareAlert$59(r1);
    }

    public static /* synthetic */ void $r8$lambda$8EurkvvsVUrHi4p4ZztICb4ueo0(DialogInterface r0) {
    }

    /* renamed from: $r8$lambda$93eSzpgS8X-VzTm6hjP7Nm8Wk7U, reason: not valid java name */
    public static /* synthetic */ void m15044$r8$lambda$93eSzpgS8XVzTm6hjP7Nm8Wk7U(EditTextBoldCursor r0) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ String $r8$lambda$9JGWSWivvP1j8p_Ke7dP1x9cofM(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    /* renamed from: $r8$lambda$AM6jzxfSYOwQgA-5OyXhG9yIB70, reason: not valid java name */
    public static /* synthetic */ void m15045$r8$lambda$AM6jzxfSYOwQgA5OyXhG9yIB70(GroupCallActivity r0, View r1) {
        r0.lambda$new$28(r1);
    }

    public static /* synthetic */ String $r8$lambda$BL2tH4_Wo81sqZYoIC0yrAchYCI(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    /* renamed from: $r8$lambda$BaAX-MN44_fvdTdVEP1g2z9JlPg, reason: not valid java name */
    public static /* synthetic */ boolean m15046$r8$lambda$BaAXMN44_fvdTdVEP1g2z9JlPg(GroupCallActivity r0, View r1, int r2) {
        return r0.lambda$new$38(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Bdm_jeAvVq5n_el5ziMMLdVt1DU(GroupCallActivity r0, float r1, float r2, float r3, int r4, ValueAnimator r5) {
        r0.lambda$runAvatarPreviewTransition$76(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$C9vvrKJrnmhgig_x5HUabkDCnf0(GroupCallActivity r0) {
        r0.onUserLeaveHint();
    }

    public static /* synthetic */ void $r8$lambda$D1GP7YTkxWHEzbMV1cSFRN_FLE4() {
        GroupCallActivity r0 = groupCallInstance;
        if (r0 == null) goto L6;
        r0.show();
        return;
    }

    /* renamed from: $r8$lambda$DdGFcCfesoTvpX-MRIW2qjO_dMo, reason: not valid java name */
    public static /* synthetic */ void m15047$r8$lambda$DdGFcCfesoTvpXMRIW2qjO_dMo(GroupCallActivity r0, View r1) {
        r0.lambda$new$36(r1);
    }

    public static /* synthetic */ void $r8$lambda$DdsCL8x11BH4s_lYjxiVFESoJnA(GroupCallActivity r0, View r1) {
        r0.lambda$new$40(r1);
    }

    /* renamed from: $r8$lambda$DncRDak6-TCD-n9SofHyBzzYwBY, reason: not valid java name */
    public static /* synthetic */ void m15048$r8$lambda$DncRDak6TCDn9SofHyBzzYwBY(GroupCallActivity r0) {
        r0.lambda$onUserLeaveHint$80();
    }

    public static /* synthetic */ void $r8$lambda$Fa3ZPhFXyZdUSkEgn5plm6EMoSA(BottomSheet r2, EditTextBoldCursor r3, boolean r4, AlertDialog r5) {
        if (r2 != null) goto L5;
    L10:
        if (r5 != null) goto L12;
        return;
    L12:
        if (r5.isShowing() == false) goto L19;
        r5.setFocusable(true);
        r3.requestFocus();
        if (r4 == false) goto L20;
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda80(r3));
        return;
    L20:
        return;
    L19:
        return;
    L5:
        if (r2.isDismissed() == true) goto L10;
        r2.setFocusable(true);
        r3.requestFocus();
        if (r4 == false) goto L17;
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda79(r3));
        return;
    }

    /* renamed from: $r8$lambda$GQJ_6YMlE_VcL-XhB_zmTwbQmZc, reason: not valid java name */
    public static /* synthetic */ void m15049$r8$lambda$GQJ_6YMlE_VcLXhB_zmTwbQmZc(GroupCallActivity r0, TLObject r1, AlertDialog r2, int r3) {
        r0.lambda$processSelectedOption$69(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$IX8U1jjNK8DwRu-uzSJl7nEW5fQ, reason: not valid java name */
    public static /* synthetic */ void m15050$r8$lambda$IX8U1jjNK8DwRuuzSJl7nEW5fQ(GroupCallActivity r0, View r1) {
        r0.lambda$updateTitle$54(r1);
    }

    /* renamed from: $r8$lambda$L5njyj90Y_44bw-8VxwPMhbGBOk, reason: not valid java name */
    public static /* synthetic */ void m15051$r8$lambda$L5njyj90Y_44bw8VxwPMhbGBOk(GroupCallActivity r0, View r1) {
        r0.lambda$new$39(r1);
    }

    public static /* synthetic */ void $r8$lambda$LEErXxYAtPCty8HMGvMko3wNP_0(GroupCallActivity r0, View r1) {
        r0.lambda$updateTitle$53(r1);
    }

    public static /* synthetic */ void $r8$lambda$LlkCsUiGn_fPBQUeDmhmC3NmUHo(GroupCallActivity r0, TLRPC.ChatFull r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$getLink$56(r1, r2, r3, r4);
    }

    public static /* synthetic */ String $r8$lambda$MzXaaBkHop8z0Fg5s2nMkqxc3uM(long r4, Calendar r6, int r7, int r8) {
        if (r8 == 0) goto L4;
        long r42 = r4 + (r8 * DurationKt.MILLIS_IN_DAY);
        r6.setTimeInMillis(r42);
        if (r6.get(1) != r7) goto L10;
        return LocaleController.getInstance().getFormatterWeek().format(r42) + " " + LocaleController.getInstance().getFormatterScheduleDay().format(r42);
    L10:
        return LocaleController.getInstance().getFormatterScheduleYear().format(r42);
    L4:
        return LocaleController.getString(R.string.MessageScheduleToday);
    }

    /* renamed from: $r8$lambda$N8DexAQRlll-WJP4k1v8E2EC2sQ, reason: not valid java name */
    public static /* synthetic */ void m15052$r8$lambda$N8DexAQRlllWJP4k1v8E2EC2sQ(GroupCallActivity r0) {
        r0.lambda$setCommentsEnabled$78();
    }

    public static /* synthetic */ void $r8$lambda$NKgAMU5gTswPd44yiEd1qepZPd4(GroupCallActivity r0, TLObject r1, TLRPC.ChatFull r2, boolean r3) {
        r0.lambda$getLink$55(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$NxCvj06LU4m2XxBSQ079D_NOWyk(GroupCallActivity r0, ChatObject.Call.InvitedUser r1, Long r2) {
        r0.lambda$new$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$OCAaDy50JxS_yignaPS1lLlzcc4(GroupCallActivity r0, AlertDialog[] r1, boolean r2, TLRPC.TL_error r3, long r4, TL_phone.inviteToGroupCall r6) {
        r0.lambda$inviteUserToCall$62(r1, r2, r3, r4, r6);
    }

    public static /* synthetic */ void $r8$lambda$PmWCw3YzSbkNLdUhSS1YvRxTxSQ(GroupCallActivity r0, TLRPC.Updates r1, TLRPC.TL_error r2) {
        r0.lambda$toggleAdminSpeak$77(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$RNugrt3EP5Ceecm_L9MGDdbNDBY(GroupCallActivity r0, View r1) {
        r0.lambda$new$34(r1);
    }

    public static /* synthetic */ void $r8$lambda$RSysMEdo37O6WxwsTVxkCM90IQg(GroupCallActivity r0, Activity r1, ChatObject.Call r2, View r3, int r4, float r5, float r6) {
        r0.lambda$new$24(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$SqcQtTXgU8E4qh4UOpTU6dZP03w(GroupCallActivity r0, long r1, AlertDialog[] r3, TLRPC.User r4) {
        r0.lambda$inviteUserToCall$61(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$T2lBmE1A1QWiyUX8_pZNxPivJ48(GroupCallActivity r0, View r1, int r2) {
        r0.lambda$new$37(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$TkIHExLtqEX83KeP3YmJk9PKuPk(GroupCallActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$new$13(r1, r2);
    }

    /* renamed from: $r8$lambda$UftCGfWsjTNC-gceE1afq_-m6GE, reason: not valid java name */
    public static /* synthetic */ WindowInsetsCompat m15053$r8$lambda$UftCGfWsjTNCgceE1afq_m6GE(GroupCallActivity r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VCdl4Dr8brDbYPCfeVCFFXSivVc(ArrayList r0, ArrayList r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda86(r3, r0, r1, r2));
    }

    public static /* synthetic */ void $r8$lambda$Vj6c4gyT8h8ali9hu4CMvuriggk(GroupCallActivity r0, View r1, int r2) {
        r0.lambda$new$26(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VySc520Ees6mNZtPHpJFl8XHJWY(TLObject r3, ArrayList r4, ArrayList r5, Runnable r6) {
        if ((r3 instanceof Vector) == false) goto L11;
        Vector r32 = (Vector) r3;
        int r0 = 0;
    L6:
        if (r0 >= Math.min(r4.size(), r32.objects.size())) goto L11;
        if ((r32.objects.get(r0) instanceof TL_account.requirementToContactPremium) == false) goto L10;
        r5.add(Long.valueOf(((TLRPC.User) r4.get(r0)).id));
    L10:
        r0 = r0 + 1;
    L11:
        r6.run();
    }

    public static /* synthetic */ void $r8$lambda$W73M_4THHHPW0aS6ADstcCLA7n8(GroupCallActivity r0) {
        r0.lambda$didReceivedNotification$1();
    }

    /* renamed from: $r8$lambda$WQnOZuJIA7q8jTzew0-Pr83gFmY, reason: not valid java name */
    public static /* synthetic */ void m15054$r8$lambda$WQnOZuJIA7q8jTzew0Pr83gFmY(GroupCallActivity r0, HashSet r1, ChatObject.Call r2, String r3) {
        r0.lambda$new$21(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$YIrAJVx_0i0X-QCO82r1FF0QJdE, reason: not valid java name */
    public static /* synthetic */ void m15055$r8$lambda$YIrAJVx_0i0XQCO82r1FF0QJdE(GroupCallActivity r0, ValueAnimator r1) {
        r0.lambda$new$41(r1);
    }

    /* renamed from: $r8$lambda$ZHKUbcBJ6RQ3VS0uxHQHVncr-YE, reason: not valid java name */
    public static /* synthetic */ void m15056$r8$lambda$ZHKUbcBJ6RQ3VS0uxHQHVncrYE(GroupCallActivity r0, int r1) {
        r0.lambda$new$29(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZQeyvNSTsWMp8JFnDxsEVrI0PvA(GroupCallActivity r0, long r1) {
        r0.lambda$processSelectedOption$70(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZQq8SiLE7tDH8UQxFpuXp37RPI4(GroupCallActivity r0, int r1) {
        r0.lambda$new$32(r1);
    }

    /* renamed from: $r8$lambda$_Yrqkqko-hdH6mXwgsnpz0Pkz9U, reason: not valid java name */
    public static /* synthetic */ void m15057$r8$lambda$_YrqkqkohdH6mXwgsnpz0Pkz9U() {
        if (VoIPService.getSharedInstance() != null) goto L5;
        return;
    L5:
        VoIPService.getSharedInstance().setMicMute(false, true, false);
    }

    public static /* synthetic */ void $r8$lambda$bjyiyCzYz3WlVUtDP7rjedHcEj0(GroupCallActivity r0, int[] r1, float[] r2, boolean[] r3) {
        r0.lambda$new$10(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$btic7TlfTKSMg06z7RLkOefD_dE(GroupCallActivity r0, TLObject r1, int r2, boolean r3) {
        r0.lambda$getLink$57(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$dWARVqQfLnIKwqYGcmzYjYzJodA(GroupCallActivity r0, View r1) {
        r0.lambda$new$35(r1);
    }

    public static /* synthetic */ void $r8$lambda$e5EcJl2pKjbWarQ0orCSsmsB6wg(GroupCallActivity r0, View r1) {
        r0.lambda$new$30(r1);
    }

    public static /* synthetic */ void $r8$lambda$eV4HKmbj9r3CbYajPQkxy31_XWk(GroupCallActivity r0, ChatObject.Call.InvitedUser r1, Long r2) {
        r0.lambda$new$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$flj3h48y5ikm2GhuBy8Jp0FszuM(GroupCallActivity r0, Activity r1, View r2) {
        r0.lambda$new$50(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$gWf8jHisIvKujFZcaRWyyNLgtyo(GroupCallActivity r0, NumberPicker r1, NumberPicker r2, NumberPicker r3, NumberPicker r4, int r5, int r6) {
        r0.lambda$new$47(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$glh4O64Syr1bs4wsTspjzT3-bXo, reason: not valid java name */
    public static /* synthetic */ void m15058$r8$lambda$glh4O64Syr1bs4wsTspjzT3bXo(GroupCallActivity r0, ArrayList r1) {
        r0.lambda$fullscreenFor$52(r1);
    }

    public static /* synthetic */ Long $r8$lambda$hgj3K_Zw0eqx1jMtAXz4jBKlX60(TLRPC.GroupCallParticipant r2) {
        return Long.valueOf(DialogObject.getPeerDialogId(r2.peer));
    }

    /* renamed from: $r8$lambda$iXhOnEjXTHfhcMIQzTqE-P3DY3M, reason: not valid java name */
    public static /* synthetic */ void m15059$r8$lambda$iXhOnEjXTHfhcMIQzTqEP3DY3M(GroupCallActivity r0, KeyEvent r1) {
        r0.lambda$showMenuForCell$74(r1);
    }

    /* renamed from: $r8$lambda$jzX359A-sX_Mgcj_JI2agdV11Rw, reason: not valid java name */
    public static /* synthetic */ void m15060$r8$lambda$jzX359AsX_Mgcj_JI2agdV11Rw(GroupCallActivity r0, TLRPC.User r1, long r2, AlertDialog r4, int r5) {
        r0.lambda$processSelectedOption$71(r1, r2, r4, r5);
    }

    /* renamed from: $r8$lambda$k5qsnXstvZb8wjm-WFJtxF_IYTo, reason: not valid java name */
    public static /* synthetic */ void m15061$r8$lambda$k5qsnXstvZb8wjmWFJtxF_IYTo(GroupCallActivity r0, DialogInterface r1) {
        r0.lambda$didReceivedNotification$2(r1);
    }

    /* renamed from: $r8$lambda$n9DO2qF2Y_JG75NPABC4Qn-lFlY, reason: not valid java name */
    public static /* synthetic */ void m15062$r8$lambda$n9DO2qF2Y_JG75NPABC4QnlFlY(GroupCallActivity r0) {
        r0.pressRunnableImpl();
    }

    public static /* synthetic */ void $r8$lambda$nAm3p07aR3PgntgHSq1jQ2YZeQw(GroupCallActivity r0, long r1, AlertDialog[] r3, TLRPC.User r4, boolean r5, TL_phone.inviteToGroupCall r6, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$inviteUserToCall$63(r1, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$nGemZ5gJBcCBqMBRiTeQz6lIUow(GroupCallActivity r0, TLRPC.Updates r1, long r2) {
        r0.lambda$new$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$nIL2sUHPjCIsIjcrTZDZlTtmUi8(EditTextBoldCursor r0) {
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$nMW1f4eu4kqWk4LbAsZupbEHlzw(GroupCallActivity r0, int r1, ArrayList r2, TLRPC.GroupCallParticipant r3, View r4) {
        r0.lambda$showMenuForCell$75(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$o99_PCSq3YUVHFBqoB8No3j975E(GroupCallActivity r0) {
        r0.lambda$openShareAlert$60();
    }

    public static /* synthetic */ void $r8$lambda$ohreGzJ60Pj1cDtCv10KIgejMUA(GroupCallActivity r0, long r1, HashSet r3, AtomicInteger r4, int r5, ChatObject.Call r6, String r7, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$new$22(r1, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$ojO9j7ARvGBkSIoskanFEsHm90A(GroupCallActivity r0, TLRPC.Chat r1, TLRPC.InputPeer r2, TL_update.TL_updateGroupCall r3) {
        r0.lambda$new$42(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$opzIU0YJpskOxbl96u2xTbixtBM(GroupCallActivity r0, int r1, DialogInterface r2) {
        r0.lambda$inviteUserToCall$64(r1, r2);
    }

    /* renamed from: $r8$lambda$p1O2q_YJuksmPVcCEi37jdpXu-I, reason: not valid java name */
    public static /* synthetic */ void m15063$r8$lambda$p1O2q_YJuksmPVcCEi37jdpXuI(GroupCallActivity r0, int r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$getLink$58(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$p3Redil0GBxx8AlJDZJJMY7eBIM(GroupCallActivity r0) {
        r0.checkInsets();
    }

    public static /* synthetic */ void $r8$lambda$pdCspUZ8BgjZuYr6CWQGJ3olakA(GroupCallActivity r0) {
        r0.lambda$updateItems$3();
    }

    /* renamed from: $r8$lambda$pmjW-dj5-FMoyX78FL-zyz6qNYs, reason: not valid java name */
    public static /* synthetic */ void m15064$r8$lambda$pmjWdj5FMoyX78FLzyz6qNYs(GroupCallActivity r0, AlertDialog r1, TL_phone.exportGroupCallInvite r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$openShareConferenceLink$82(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$s77cPWN3wnyVGjqbcBqdwyzoXYU(GroupCallActivity r0, TLRPC.Chat r1, TLRPC.InputPeer r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$new$44(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$tB6ybx_2HrPQy03TA2ufqnHy-pY, reason: not valid java name */
    public static /* synthetic */ void m15065$r8$lambda$tB6ybx_2HrPQy03TA2ufqnHypY(GroupCallActivity r0, ChatObject.Call r1, Boolean r2, HashSet r3) {
        r0.lambda$new$23(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$tEZPeunWaLtv6BuQXLJYU5KiS7I(GroupCallActivity r0, TLRPC.Updates r1, TLRPC.TL_error r2) {
        r0.lambda$setCommentsEnabled$79(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$tZf6rD8fOBq9VqvLaUxzFaMjRVA(GroupCallActivity r0, int r1, int r2, boolean r3, ValueAnimator r4) {
        r0.lambda$updateLiveLabel$66(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$u98G_sdRmQXxbLHGz8-WxFcOvnQ, reason: not valid java name */
    public static /* synthetic */ void m15066$r8$lambda$u98G_sdRmQXxbLHGz8WxFcOvnQ(GroupCallActivity r0, ArrayList r1, ArrayList r2, ArrayList r3, String r4) {
        r0.lambda$new$18(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$uPJJl1iOfo4Vco4OCF1JVJ0HREA(GroupCallActivity r0, View r1) {
        r0.lambda$new$27(r1);
    }

    public static /* synthetic */ void $r8$lambda$uYywbGIdZ0TKq1gzAzgDGvaqdt4(GroupCallActivity r0, Activity r1, View r2) {
        r0.lambda$new$31(r1, r2);
    }

    /* renamed from: $r8$lambda$vVdCU3gH89Cnir7zpk_mJfJCe-U, reason: not valid java name */
    public static /* synthetic */ void m15067$r8$lambda$vVdCU3gH89Cnir7zpk_mJfJCeU(GroupCallActivity r0, DialogInterface r1) {
        r0.lambda$new$15(r1);
    }

    /* renamed from: $r8$lambda$vXPGtw2yIgIryGax-4c7Cv3pKyI, reason: not valid java name */
    public static /* synthetic */ boolean m15068$r8$lambda$vXPGtw2yIgIryGax4c7Cv3pKyI(GroupCallActivity r0, View r1, int r2) {
        return r0.lambda$new$25(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$veJMWJ9m_DCSnrt48Db6epvQ0Pw(GroupCallActivity r0, NumberPicker r1, NumberPicker r2, NumberPicker r3, TLRPC.Chat r4, AccountInstance r5, TLRPC.InputPeer r6, View r7) {
        r0.lambda$new$45(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$xfyP-CY85X5D5xNT2PtIMqB8ihk, reason: not valid java name */
    public static /* synthetic */ void m15069$r8$lambda$xfyPCY85X5D5xNT2PtIMqB8ihk(GroupCallActivity r0, AlertDialog r1, TLObject r2, TL_phone.exportGroupCallInvite r3, TLRPC.TL_error r4) {
        r0.lambda$openShareConferenceLink$81(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$xggrtBdeIFugTWnxaOf_JzYSBF4(CheckBoxCell[] r1, View r2) {
        r1[((Integer) r2.getTag()).intValue()].setChecked(!r1[r2.intValue()].isChecked(), true);
    }

    public static /* synthetic */ void $r8$lambda$zfqgVtIkNfTUwLKCcx9usqY_uoY(GroupCallActivity r0, View r1) {
        r0.lambda$new$51(r1);
    }

    /* renamed from: -$$Nest$fgetaccountInstance, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AccountInstance m15070$$Nest$fgetaccountInstance(GroupCallActivity r0) {
        return r0.accountInstance;
    }

    /* renamed from: -$$Nest$fgetaccountSelectCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AccountSelectCell m15071$$Nest$fgetaccountSelectCell(GroupCallActivity r0) {
        return r0.accountSelectCell;
    }

    /* renamed from: -$$Nest$fgetactionBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBar m15072$$Nest$fgetactionBar(GroupCallActivity r0) {
        return r0.actionBar;
    }

    /* renamed from: -$$Nest$fgetactionBarBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m15073$$Nest$fgetactionBarBackground(GroupCallActivity r0) {
        return r0.actionBarBackground;
    }

    /* renamed from: -$$Nest$fgetactionBarShadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m15074$$Nest$fgetactionBarShadow(GroupCallActivity r0) {
        return r0.actionBarShadow;
    }

    /* renamed from: -$$Nest$fgetadminItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m15075$$Nest$fgetadminItem(GroupCallActivity r0) {
        return r0.adminItem;
    }

    /* renamed from: -$$Nest$fgetamplitude, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15076$$Nest$fgetamplitude(GroupCallActivity r0) {
        return r0.amplitude;
    }

    /* renamed from: -$$Nest$fgetanimateAmplitudeDiff, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15077$$Nest$fgetanimateAmplitudeDiff(GroupCallActivity r0) {
        return r0.animateAmplitudeDiff;
    }

    /* renamed from: -$$Nest$fgetanimateToAmplitude, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15078$$Nest$fgetanimateToAmplitude(GroupCallActivity r0) {
        return r0.animateToAmplitude;
    }

    /* renamed from: -$$Nest$fgetanimatorHideButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BoolAnimator m15079$$Nest$fgetanimatorHideButtons(GroupCallActivity r0) {
        return r0.animatorHideButtons;
    }

    /* renamed from: -$$Nest$fgetanimatorMessageInputHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FactorAnimator m15080$$Nest$fgetanimatorMessageInputHeight(GroupCallActivity r0) {
        return r0.animatorMessageInputHeight;
    }

    /* renamed from: -$$Nest$fgetanimatorMessageIsEmpty, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BoolAnimator m15081$$Nest$fgetanimatorMessageIsEmpty(GroupCallActivity r0) {
        return r0.animatorMessageIsEmpty;
    }

    /* renamed from: -$$Nest$fgetattachedRenderers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15082$$Nest$fgetattachedRenderers(GroupCallActivity r0) {
        return r0.attachedRenderers;
    }

    /* renamed from: -$$Nest$fgetattachedRenderersTmp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15083$$Nest$fgetattachedRenderersTmp(GroupCallActivity r0) {
        return r0.attachedRenderersTmp;
    }

    /* renamed from: -$$Nest$fgetavatarPagerIndicator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AvatarPreviewPagerIndicator m15084$$Nest$fgetavatarPagerIndicator(GroupCallActivity r0) {
        return r0.avatarPagerIndicator;
    }

    /* renamed from: -$$Nest$fgetavatarPreviewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m15085$$Nest$fgetavatarPreviewContainer(GroupCallActivity r0) {
        return r0.avatarPreviewContainer;
    }

    /* renamed from: -$$Nest$fgetavatarsPreviewShowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15086$$Nest$fgetavatarsPreviewShowed(GroupCallActivity r0) {
        return r0.avatarsPreviewShowed;
    }

    /* renamed from: -$$Nest$fgetavatarsViewPager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ProfileGalleryView m15087$$Nest$fgetavatarsViewPager(GroupCallActivity r0) {
        return r0.avatarsViewPager;
    }

    /* renamed from: -$$Nest$fgetbackgroundColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15088$$Nest$fgetbackgroundColor(GroupCallActivity r0) {
        return r0.backgroundColor;
    }

    /* renamed from: -$$Nest$fgetbigMicDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieDrawable m15089$$Nest$fgetbigMicDrawable(GroupCallActivity r0) {
        return r0.bigMicDrawable;
    }

    /* renamed from: -$$Nest$fgetbigWaveDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlobDrawable m15090$$Nest$fgetbigWaveDrawable(GroupCallActivity r0) {
        return r0.bigWaveDrawable;
    }

    /* renamed from: -$$Nest$fgetblurredView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m15091$$Nest$fgetblurredView(GroupCallActivity r0) {
        return r0.blurredView;
    }

    /* renamed from: -$$Nest$fgetbuttonsBackgroundGradientView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m15092$$Nest$fgetbuttonsBackgroundGradientView(GroupCallActivity r0) {
        return r0.buttonsBackgroundGradientView;
    }

    /* renamed from: -$$Nest$fgetbuttonsBackgroundGradientView2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m15093$$Nest$fgetbuttonsBackgroundGradientView2(GroupCallActivity r0) {
        return r0.buttonsBackgroundGradientView2;
    }

    /* renamed from: -$$Nest$fgetbuttonsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallActivityButtonsLayout m15094$$Nest$fgetbuttonsContainer(GroupCallActivity r0) {
        return r0.buttonsContainer;
    }

    /* renamed from: -$$Nest$fgetcallMessageEnterContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m15095$$Nest$fgetcallMessageEnterContainer(GroupCallActivity r0) {
        return r0.callMessageEnterContainer;
    }

    /* renamed from: -$$Nest$fgetcallMessageEnterUnderContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m15096$$Nest$fgetcallMessageEnterUnderContainer(GroupCallActivity r0) {
        return r0.callMessageEnterUnderContainer;
    }

    /* renamed from: -$$Nest$fgetcallMessageEnterView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextEmoji m15097$$Nest$fgetcallMessageEnterView(GroupCallActivity r0) {
        return r0.callMessageEnterView;
    }

    /* renamed from: -$$Nest$fgetcameraButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPToggleButton m15098$$Nest$fgetcameraButton(GroupCallActivity r0) {
        return r0.cameraButton;
    }

    /* renamed from: -$$Nest$fgetcolorProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15099$$Nest$fgetcolorProgress(GroupCallActivity r0) {
        return r0.colorProgress;
    }

    /* renamed from: -$$Nest$fgetcolorsTmp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m15100$$Nest$fgetcolorsTmp(GroupCallActivity r0) {
        return r0.colorsTmp;
    }

    /* renamed from: -$$Nest$fgetcontentFullyOverlayed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15101$$Nest$fgetcontentFullyOverlayed(GroupCallActivity r0) {
        return r0.contentFullyOverlayed;
    }

    /* renamed from: -$$Nest$fgetcurrentCallState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15102$$Nest$fgetcurrentCallState(GroupCallActivity r0) {
        return r0.currentCallState;
    }

    /* renamed from: -$$Nest$fgetcurrentState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WeavingState m15103$$Nest$fgetcurrentState(GroupCallActivity r0) {
        return r0.currentState;
    }

    /* renamed from: -$$Nest$fgetdelayedGroupCallUpdated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15104$$Nest$fgetdelayedGroupCallUpdated(GroupCallActivity r0) {
        return r0.delayedGroupCallUpdated;
    }

    /* renamed from: -$$Nest$fgetdisableComments, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m15105$$Nest$fgetdisableComments(GroupCallActivity r0) {
        return r0.disableComments;
    }

    /* renamed from: -$$Nest$fgeteditTitleItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m15106$$Nest$fgeteditTitleItem(GroupCallActivity r0) {
        return r0.editTitleItem;
    }

    /* renamed from: -$$Nest$fgetenableComments, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m15107$$Nest$fgetenableComments(GroupCallActivity r0) {
        return r0.enableComments;
    }

    /* renamed from: -$$Nest$fgetencryptionDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CallEncryptionCellDrawable m15108$$Nest$fgetencryptionDrawable(GroupCallActivity r0) {
        return r0.encryptionDrawable;
    }

    /* renamed from: -$$Nest$fgetenterEventSent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15109$$Nest$fgetenterEventSent(GroupCallActivity r0) {
        return r0.enterEventSent;
    }

    /* renamed from: -$$Nest$fgeteveryoneItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m15110$$Nest$fgeteveryoneItem(GroupCallActivity r0) {
        return r0.everyoneItem;
    }

    /* renamed from: -$$Nest$fgetflipButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPToggleButton m15111$$Nest$fgetflipButton(GroupCallActivity r0) {
        return r0.flipButton;
    }

    /* renamed from: -$$Nest$fgetfullscreenListItemAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DefaultItemAnimator m15112$$Nest$fgetfullscreenListItemAnimator(GroupCallActivity r0) {
        return r0.fullscreenListItemAnimator;
    }

    /* renamed from: -$$Nest$fgetgroupCallMessagesListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallMessagesListView m15113$$Nest$fgetgroupCallMessagesListView(GroupCallActivity r0) {
        return r0.groupCallMessagesListView;
    }

    /* renamed from: -$$Nest$fgetgroupVoipInviteAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupVoipInviteAlert m15114$$Nest$fgetgroupVoipInviteAlert(GroupCallActivity r0) {
        return r0.groupVoipInviteAlert;
    }

    /* renamed from: -$$Nest$fgethandDrawables, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieDrawable m15115$$Nest$fgethandDrawables(GroupCallActivity r0) {
        return r0.handDrawables;
    }

    /* renamed from: -$$Nest$fgethasScrimAnchorView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15116$$Nest$fgethasScrimAnchorView(GroupCallActivity r0) {
        return r0.hasScrimAnchorView;
    }

    /* renamed from: -$$Nest$fgethasVideo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15117$$Nest$fgethasVideo(GroupCallActivity r0) {
        return r0.hasVideo;
    }

    /* renamed from: -$$Nest$fgetinvalidateColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15118$$Nest$fgetinvalidateColors(GroupCallActivity r0) {
        return r0.invalidateColors;
    }

    /* renamed from: -$$Nest$fgetinviteItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m15119$$Nest$fgetinviteItem(GroupCallActivity r0) {
        return r0.inviteItem;
    }

    /* renamed from: -$$Nest$fgetisInDrawRenderNodeBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15120$$Nest$fgetisInDrawRenderNodeBlur(GroupCallActivity r0) {
        return r0.isInDrawRenderNodeBlur;
    }

    /* renamed from: -$$Nest$fgetitemAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallItemAnimator m15121$$Nest$fgetitemAnimator(GroupCallActivity r0) {
        return r0.itemAnimator;
    }

    /* renamed from: -$$Nest$fgetlastUpdateTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m15122$$Nest$fgetlastUpdateTime(GroupCallActivity r2) {
        return r2.lastUpdateTime;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FillLastGridLayoutManager m15123$$Nest$fgetlayoutManager(GroupCallActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetleaveButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPToggleButton m15124$$Nest$fgetleaveButton(GroupCallActivity r0) {
        return r0.leaveButton;
    }

    /* renamed from: -$$Nest$fgetleaveItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m15125$$Nest$fgetleaveItem(GroupCallActivity r0) {
        return r0.leaveItem;
    }

    /* renamed from: -$$Nest$fgetlimitTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView m15126$$Nest$fgetlimitTextView(GroupCallActivity r0) {
        return r0.limitTextView;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m15127$$Nest$fgetlistAdapter(GroupCallActivity r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m15128$$Nest$fgetlistView(GroupCallActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlistViewBackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m15129$$Nest$fgetlistViewBackgroundPaint(GroupCallActivity r0) {
        return r0.listViewBackgroundPaint;
    }

    /* renamed from: -$$Nest$fgetlistViewVideoVisibility, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15130$$Nest$fgetlistViewVideoVisibility(GroupCallActivity r0) {
        return r0.listViewVideoVisibility;
    }

    /* renamed from: -$$Nest$fgetliveLabelBgColorAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m15131$$Nest$fgetliveLabelBgColorAnimator(GroupCallActivity r0) {
        return r0.liveLabelBgColorAnimator;
    }

    /* renamed from: -$$Nest$fgetliveLabelPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m15132$$Nest$fgetliveLabelPaint(GroupCallActivity r0) {
        return r0.liveLabelPaint;
    }

    /* renamed from: -$$Nest$fgetliveLabelTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m15133$$Nest$fgetliveLabelTextView(GroupCallActivity r0) {
        return r0.liveLabelTextView;
    }

    /* renamed from: -$$Nest$fgetmaxGroupCallMessageLength, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15134$$Nest$fgetmaxGroupCallMessageLength(GroupCallActivity r0) {
        return r0.maxGroupCallMessageLength;
    }

    /* renamed from: -$$Nest$fgetmenuItemsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m15135$$Nest$fgetmenuItemsContainer(GroupCallActivity r0) {
        return r0.menuItemsContainer;
    }

    /* renamed from: -$$Nest$fgetmessageButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPToggleButton m15136$$Nest$fgetmessageButton(GroupCallActivity r0) {
        return r0.messageButton;
    }

    /* renamed from: -$$Nest$fgetmuteButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPToggleButton m15137$$Nest$fgetmuteButton(GroupCallActivity r0) {
        return r0.muteButton;
    }

    /* renamed from: -$$Nest$fgetmuteButtonIcon, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m15138$$Nest$fgetmuteButtonIcon(GroupCallActivity r0) {
        return r0.muteButtonIcon;
    }

    /* renamed from: -$$Nest$fgetmuteButtonState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15139$$Nest$fgetmuteButtonState(GroupCallActivity r0) {
        return r0.muteButtonState;
    }

    /* renamed from: -$$Nest$fgetneedTextureLightning, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15140$$Nest$fgetneedTextureLightning(GroupCallActivity r0) {
        return r0.needTextureLightning;
    }

    /* renamed from: -$$Nest$fgetnoiseItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m15141$$Nest$fgetnoiseItem(GroupCallActivity r0) {
        return r0.noiseItem;
    }

    /* renamed from: -$$Nest$fgetoldAddMemberRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15142$$Nest$fgetoldAddMemberRow(GroupCallActivity r0) {
        return r0.oldAddMemberRow;
    }

    /* renamed from: -$$Nest$fgetoldConferenceAddPeopleRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15143$$Nest$fgetoldConferenceAddPeopleRow(GroupCallActivity r0) {
        return r0.oldConferenceAddPeopleRow;
    }

    /* renamed from: -$$Nest$fgetoldConferenceShareLinkRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15144$$Nest$fgetoldConferenceShareLinkRow(GroupCallActivity r0) {
        return r0.oldConferenceShareLinkRow;
    }

    /* renamed from: -$$Nest$fgetoldCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15145$$Nest$fgetoldCount(GroupCallActivity r0) {
        return r0.oldCount;
    }

    /* renamed from: -$$Nest$fgetoldEncryptionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15146$$Nest$fgetoldEncryptionRow(GroupCallActivity r0) {
        return r0.oldEncryptionRow;
    }

    /* renamed from: -$$Nest$fgetoldInvited, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15147$$Nest$fgetoldInvited(GroupCallActivity r0) {
        return r0.oldInvited;
    }

    /* renamed from: -$$Nest$fgetoldInvitedEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15148$$Nest$fgetoldInvitedEndRow(GroupCallActivity r0) {
        return r0.oldInvitedEndRow;
    }

    /* renamed from: -$$Nest$fgetoldInvitedStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15149$$Nest$fgetoldInvitedStartRow(GroupCallActivity r0) {
        return r0.oldInvitedStartRow;
    }

    /* renamed from: -$$Nest$fgetoldParticipants, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15150$$Nest$fgetoldParticipants(GroupCallActivity r0) {
        return r0.oldParticipants;
    }

    /* renamed from: -$$Nest$fgetoldShadyJoin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15151$$Nest$fgetoldShadyJoin(GroupCallActivity r0) {
        return r0.oldShadyJoin;
    }

    /* renamed from: -$$Nest$fgetoldShadyJoinEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15152$$Nest$fgetoldShadyJoinEndRow(GroupCallActivity r0) {
        return r0.oldShadyJoinEndRow;
    }

    /* renamed from: -$$Nest$fgetoldShadyJoinStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15153$$Nest$fgetoldShadyJoinStartRow(GroupCallActivity r0) {
        return r0.oldShadyJoinStartRow;
    }

    /* renamed from: -$$Nest$fgetoldShadyLeft, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15154$$Nest$fgetoldShadyLeft(GroupCallActivity r0) {
        return r0.oldShadyLeft;
    }

    /* renamed from: -$$Nest$fgetoldShadyLeftEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15155$$Nest$fgetoldShadyLeftEndRow(GroupCallActivity r0) {
        return r0.oldShadyLeftEndRow;
    }

    /* renamed from: -$$Nest$fgetoldShadyLeftStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15156$$Nest$fgetoldShadyLeftStartRow(GroupCallActivity r0) {
        return r0.oldShadyLeftStartRow;
    }

    /* renamed from: -$$Nest$fgetoldUsersEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15157$$Nest$fgetoldUsersEndRow(GroupCallActivity r0) {
        return r0.oldUsersEndRow;
    }

    /* renamed from: -$$Nest$fgetoldUsersStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15158$$Nest$fgetoldUsersStartRow(GroupCallActivity r0) {
        return r0.oldUsersStartRow;
    }

    /* renamed from: -$$Nest$fgetoldUsersVideoEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15159$$Nest$fgetoldUsersVideoEndRow(GroupCallActivity r0) {
        return r0.oldUsersVideoEndRow;
    }

    /* renamed from: -$$Nest$fgetoldUsersVideoStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15160$$Nest$fgetoldUsersVideoStartRow(GroupCallActivity r0) {
        return r0.oldUsersVideoStartRow;
    }

    /* renamed from: -$$Nest$fgetoldVideoDividerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15161$$Nest$fgetoldVideoDividerRow(GroupCallActivity r0) {
        return r0.oldVideoDividerRow;
    }

    /* renamed from: -$$Nest$fgetoldVideoNotAvailableRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15162$$Nest$fgetoldVideoNotAvailableRow(GroupCallActivity r0) {
        return r0.oldVideoNotAvailableRow;
    }

    /* renamed from: -$$Nest$fgetoldVideoParticipants, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15163$$Nest$fgetoldVideoParticipants(GroupCallActivity r0) {
        return r0.oldVideoParticipants;
    }

    /* renamed from: -$$Nest$fgetotherItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m15164$$Nest$fgetotherItem(GroupCallActivity r0) {
        return r0.otherItem;
    }

    /* renamed from: -$$Nest$fgetpaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m15165$$Nest$fgetpaint(GroupCallActivity r0) {
        return r0.paint;
    }

    /* renamed from: -$$Nest$fgetpaintTmp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m15166$$Nest$fgetpaintTmp(GroupCallActivity r0) {
        return r0.paintTmp;
    }

    /* renamed from: -$$Nest$fgetparentActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LaunchActivity m15167$$Nest$fgetparentActivity(GroupCallActivity r0) {
        return r0.parentActivity;
    }

    /* renamed from: -$$Nest$fgetpermissionItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m15168$$Nest$fgetpermissionItem(GroupCallActivity r0) {
        return r0.permissionItem;
    }

    /* renamed from: -$$Nest$fgetplayingHandAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15169$$Nest$fgetplayingHandAnimation(GroupCallActivity r0) {
        return r0.playingHandAnimation;
    }

    /* renamed from: -$$Nest$fgetpopupAnimationIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15170$$Nest$fgetpopupAnimationIndex(GroupCallActivity r0) {
        return r0.popupAnimationIndex;
    }

    /* renamed from: -$$Nest$fgetpressRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m15171$$Nest$fgetpressRunnable(GroupCallActivity r0) {
        return r0.pressRunnable;
    }

    /* renamed from: -$$Nest$fgetpressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15172$$Nest$fgetpressed(GroupCallActivity r0) {
        return r0.pressed;
    }

    /* renamed from: -$$Nest$fgetprevState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WeavingState m15173$$Nest$fgetprevState(GroupCallActivity r0) {
        return r0.prevState;
    }

    /* renamed from: -$$Nest$fgetpreviewTextureTransitionEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15174$$Nest$fgetpreviewTextureTransitionEnabled(GroupCallActivity r0) {
        return r0.previewTextureTransitionEnabled;
    }

    /* renamed from: -$$Nest$fgetprogressToAvatarPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15175$$Nest$fgetprogressToAvatarPreview(GroupCallActivity r0) {
        return r0.progressToAvatarPreview;
    }

    /* renamed from: -$$Nest$fgetradialGradient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialGradient m15176$$Nest$fgetradialGradient(GroupCallActivity r0) {
        return r0.radialGradient;
    }

    /* renamed from: -$$Nest$fgetradialMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m15177$$Nest$fgetradialMatrix(GroupCallActivity r0) {
        return r0.radialMatrix;
    }

    /* renamed from: -$$Nest$fgetradialPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m15178$$Nest$fgetradialPaint(GroupCallActivity r0) {
        return r0.radialPaint;
    }

    /* renamed from: -$$Nest$fgetradialProgressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m15179$$Nest$fgetradialProgressView(GroupCallActivity r0) {
        return r0.radialProgressView;
    }

    /* renamed from: -$$Nest$fgetreactionEffectImageReceiver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageReceiver m15180$$Nest$fgetreactionEffectImageReceiver(GroupCallActivity r0) {
        return r0.reactionEffectImageReceiver;
    }

    /* renamed from: -$$Nest$fgetreactionsContainerLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReactionsContainerLayout m15181$$Nest$fgetreactionsContainerLayout(GroupCallActivity r0) {
        return r0.reactionsContainerLayout;
    }

    /* renamed from: -$$Nest$fgetrecordHintView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HintView m15182$$Nest$fgetrecordHintView(GroupCallActivity r0) {
        return r0.recordHintView;
    }

    /* renamed from: -$$Nest$fgetrecordItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m15183$$Nest$fgetrecordItem(GroupCallActivity r0) {
        return r0.recordItem;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m15184$$Nest$fgetrect(GroupCallActivity r0) {
        return r0.rect;
    }

    /* renamed from: -$$Nest$fgetreminderHintView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HintView m15185$$Nest$fgetreminderHintView(GroupCallActivity r0) {
        return r0.reminderHintView;
    }

    /* renamed from: -$$Nest$fgetrenderNodeBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RenderNode m15186$$Nest$fgetrenderNodeBlur(GroupCallActivity r0) {
        return r0.renderNodeBlur;
    }

    /* renamed from: -$$Nest$fgetrenderNodeBlurScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15187$$Nest$fgetrenderNodeBlurScale(GroupCallActivity r0) {
        return r0.renderNodeBlurScale;
    }

    /* renamed from: -$$Nest$fgetrenderersContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallRenderersContainer m15188$$Nest$fgetrenderersContainer(GroupCallActivity r0) {
        return r0.renderersContainer;
    }

    /* renamed from: -$$Nest$fgetscheduleButtonTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m15189$$Nest$fgetscheduleButtonTextView(GroupCallActivity r0) {
        return r0.scheduleButtonTextView;
    }

    /* renamed from: -$$Nest$fgetscheduleInfoTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m15190$$Nest$fgetscheduleInfoTextView(GroupCallActivity r0) {
        return r0.scheduleInfoTextView;
    }

    /* renamed from: -$$Nest$fgetscheduleStartAt, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15191$$Nest$fgetscheduleStartAt(GroupCallActivity r0) {
        return r0.scheduleStartAt;
    }

    /* renamed from: -$$Nest$fgetscheduleStartAtTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SimpleTextView m15192$$Nest$fgetscheduleStartAtTextView(GroupCallActivity r0) {
        return r0.scheduleStartAtTextView;
    }

    /* renamed from: -$$Nest$fgetscheduleStartInTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SimpleTextView m15193$$Nest$fgetscheduleStartInTextView(GroupCallActivity r0) {
        return r0.scheduleStartInTextView;
    }

    /* renamed from: -$$Nest$fgetscheduleTimeTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SimpleTextView m15194$$Nest$fgetscheduleTimeTextView(GroupCallActivity r0) {
        return r0.scheduleTimeTextView;
    }

    /* renamed from: -$$Nest$fgetscheduled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15195$$Nest$fgetscheduled(GroupCallActivity r0) {
        return r0.scheduled;
    }

    /* renamed from: -$$Nest$fgetscreenItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m15196$$Nest$fgetscreenItem(GroupCallActivity r0) {
        return r0.screenItem;
    }

    /* renamed from: -$$Nest$fgetscreenShareItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m15197$$Nest$fgetscreenShareItem(GroupCallActivity r0) {
        return r0.screenShareItem;
    }

    /* renamed from: -$$Nest$fgetscrimAnimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m15198$$Nest$fgetscrimAnimatorSet(GroupCallActivity r0) {
        return r0.scrimAnimatorSet;
    }

    /* renamed from: -$$Nest$fgetscrimFullscreenView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallFullscreenAdapter.GroupCallUserCell m15199$$Nest$fgetscrimFullscreenView(GroupCallActivity r0) {
        return r0.scrimFullscreenView;
    }

    /* renamed from: -$$Nest$fgetscrimPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m15200$$Nest$fgetscrimPaint(GroupCallActivity r0) {
        return r0.scrimPaint;
    }

    /* renamed from: -$$Nest$fgetscrimPopupLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m15201$$Nest$fgetscrimPopupLayout(GroupCallActivity r0) {
        return r0.scrimPopupLayout;
    }

    /* renamed from: -$$Nest$fgetscrimPopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarPopupWindow m15202$$Nest$fgetscrimPopupWindow(GroupCallActivity r0) {
        return r0.scrimPopupWindow;
    }

    /* renamed from: -$$Nest$fgetscrimRenderer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallMiniTextureView m15203$$Nest$fgetscrimRenderer(GroupCallActivity r0) {
        return r0.scrimRenderer;
    }

    /* renamed from: -$$Nest$fgetscrimView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallUserCell m15204$$Nest$fgetscrimView(GroupCallActivity r0) {
        return r0.scrimView;
    }

    /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15205$$Nest$fgetscrollOffsetY(GroupCallActivity r0) {
        return r0.scrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetselfPeer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Peer m15206$$Nest$fgetselfPeer(GroupCallActivity r0) {
        return r0.selfPeer;
    }

    /* renamed from: -$$Nest$fgetshadowDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m15207$$Nest$fgetshadowDrawable(GroupCallActivity r0) {
        return r0.shadowDrawable;
    }

    /* renamed from: -$$Nest$fgetshowLightingProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15208$$Nest$fgetshowLightingProgress(GroupCallActivity r0) {
        return r0.showLightingProgress;
    }

    /* renamed from: -$$Nest$fgetshowWavesProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15209$$Nest$fgetshowWavesProgress(GroupCallActivity r0) {
        return r0.showWavesProgress;
    }

    /* renamed from: -$$Nest$fgetsoundButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPToggleButton m15210$$Nest$fgetsoundButton(GroupCallActivity r0) {
        return r0.soundButton;
    }

    /* renamed from: -$$Nest$fgetsoundItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuSubItem m15211$$Nest$fgetsoundItem(GroupCallActivity r0) {
        return r0.soundItem;
    }

    /* renamed from: -$$Nest$fgetspanSizeLookup, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GridLayoutManager.SpanSizeLookup m15212$$Nest$fgetspanSizeLookup(GroupCallActivity r0) {
        return r0.spanSizeLookup;
    }

    /* renamed from: -$$Nest$fgetspeakerButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPToggleButton m15213$$Nest$fgetspeakerButton(GroupCallActivity r0) {
        return r0.speakerButton;
    }

    /* renamed from: -$$Nest$fgetstartingGroupCall, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15214$$Nest$fgetstartingGroupCall(GroupCallActivity r0) {
        return r0.startingGroupCall;
    }

    /* renamed from: -$$Nest$fgetswitchProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15215$$Nest$fgetswitchProgress(GroupCallActivity r0) {
        return r0.switchProgress;
    }

    /* renamed from: -$$Nest$fgetswitchToButtonInt2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15216$$Nest$fgetswitchToButtonInt2(GroupCallActivity r0) {
        return r0.switchToButtonInt2;
    }

    /* renamed from: -$$Nest$fgetswitchToButtonProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15217$$Nest$fgetswitchToButtonProgress(GroupCallActivity r0) {
        return r0.switchToButtonProgress;
    }

    /* renamed from: -$$Nest$fgettextureLightningView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LightningView m15218$$Nest$fgettextureLightningView(GroupCallActivity r0) {
        return r0.textureLightningView;
    }

    /* renamed from: -$$Nest$fgettextureLightningViewAnimatingAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15219$$Nest$fgettextureLightningViewAnimatingAlpha(GroupCallActivity r0) {
        return r0.textureLightningViewAnimatingAlpha;
    }

    /* renamed from: -$$Nest$fgettinyWaveDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlobDrawable m15220$$Nest$fgettinyWaveDrawable(GroupCallActivity r0) {
        return r0.tinyWaveDrawable;
    }

    /* renamed from: -$$Nest$fgettitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AudioPlayerAlert.ClippingTextViewSwitcher m15221$$Nest$fgettitleTextView(GroupCallActivity r0) {
        return r0.titleTextView;
    }

    /* renamed from: -$$Nest$fgetundoView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UndoView[] m15222$$Nest$fgetundoView(GroupCallActivity r0) {
        return r0.undoView;
    }

    /* renamed from: -$$Nest$fgetunmuteRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m15223$$Nest$fgetunmuteRunnable(GroupCallActivity r0) {
        return r0.unmuteRunnable;
    }

    /* renamed from: -$$Nest$fgetupdateSchedeulRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m15224$$Nest$fgetupdateSchedeulRunnable(GroupCallActivity r0) {
        return r0.updateSchedeulRunnable;
    }

    /* renamed from: -$$Nest$fgetupdateTextureLightningRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m15225$$Nest$fgetupdateTextureLightningRunnable(GroupCallActivity r0) {
        return r0.updateTextureLightningRunnable;
    }

    /* renamed from: -$$Nest$fgetuseBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15226$$Nest$fgetuseBlur(GroupCallActivity r0) {
        return r0.useBlur;
    }

    /* renamed from: -$$Nest$fgetwatchersView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WatchersView m15227$$Nest$fgetwatchersView(GroupCallActivity r0) {
        return r0.watchersView;
    }

    /* renamed from: -$$Nest$fgetwindowInsetsStateHolder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowInsetsStateHolder m15228$$Nest$fgetwindowInsetsStateHolder(GroupCallActivity r0) {
        return r0.windowInsetsStateHolder;
    }

    /* renamed from: -$$Nest$fputactionBarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15229$$Nest$fputactionBarAnimation(GroupCallActivity r0, AnimatorSet r1) {
        r0.actionBarAnimation = r1;
    }

    /* renamed from: -$$Nest$fputamplitude, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15230$$Nest$fputamplitude(GroupCallActivity r0, float r1) {
        r0.amplitude = r1;
    }

    /* renamed from: -$$Nest$fputavatarPriviewTransitionInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15231$$Nest$fputavatarPriviewTransitionInProgress(GroupCallActivity r0, boolean r1) {
        r0.avatarPriviewTransitionInProgress = r1;
    }

    /* renamed from: -$$Nest$fputavatarsPreviewShowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15232$$Nest$fputavatarsPreviewShowed(GroupCallActivity r0, boolean r1) {
        r0.avatarsPreviewShowed = r1;
    }

    /* renamed from: -$$Nest$fputcacheAudioOutputValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15233$$Nest$fputcacheAudioOutputValue(GroupCallActivity r0, Integer r1) {
        r0.cacheAudioOutputValue = r1;
    }

    /* renamed from: -$$Nest$fputchangingPermissions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15234$$Nest$fputchangingPermissions(GroupCallActivity r0, boolean r1) {
        r0.changingPermissions = r1;
    }

    /* renamed from: -$$Nest$fputdelayedGroupCallUpdated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15235$$Nest$fputdelayedGroupCallUpdated(GroupCallActivity r0, boolean r1) {
        r0.delayedGroupCallUpdated = r1;
    }

    /* renamed from: -$$Nest$fputencryptionDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15236$$Nest$fputencryptionDrawable(GroupCallActivity r0, CallEncryptionCellDrawable r1) {
        r0.encryptionDrawable = r1;
    }

    /* renamed from: -$$Nest$fputenterEventSent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15237$$Nest$fputenterEventSent(GroupCallActivity r0, boolean r1) {
        r0.enterEventSent = r1;
    }

    /* renamed from: -$$Nest$fputinvalidateColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15238$$Nest$fputinvalidateColors(GroupCallActivity r0, boolean r1) {
        r0.invalidateColors = r1;
    }

    /* renamed from: -$$Nest$fputisInDrawRenderNodeBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15239$$Nest$fputisInDrawRenderNodeBlur(GroupCallActivity r0, boolean r1) {
        r0.isInDrawRenderNodeBlur = r1;
    }

    /* renamed from: -$$Nest$fputlastUpdateTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15240$$Nest$fputlastUpdateTime(GroupCallActivity r0, long r1) {
        r0.lastUpdateTime = r1;
    }

    /* renamed from: -$$Nest$fputlistViewVideoVisibility, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15241$$Nest$fputlistViewVideoVisibility(GroupCallActivity r0, boolean r1) {
        r0.listViewVideoVisibility = r1;
    }

    /* renamed from: -$$Nest$fputliveLabelBgColorAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15242$$Nest$fputliveLabelBgColorAnimator(GroupCallActivity r0, ValueAnimator r1) {
        r0.liveLabelBgColorAnimator = r1;
    }

    /* renamed from: -$$Nest$fputneedTextureLightning, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15243$$Nest$fputneedTextureLightning(GroupCallActivity r0, boolean r1) {
        r0.needTextureLightning = r1;
    }

    /* renamed from: -$$Nest$fputplayingHandAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15244$$Nest$fputplayingHandAnimation(GroupCallActivity r0, boolean r1) {
        r0.playingHandAnimation = r1;
    }

    /* renamed from: -$$Nest$fputpressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15245$$Nest$fputpressed(GroupCallActivity r0, boolean r1) {
        r0.pressed = r1;
    }

    /* renamed from: -$$Nest$fputprevState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15246$$Nest$fputprevState(GroupCallActivity r0, WeavingState r1) {
        r0.prevState = r1;
    }

    /* renamed from: -$$Nest$fputprogressToAvatarPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15247$$Nest$fputprogressToAvatarPreview(GroupCallActivity r0, float r1) {
        r0.progressToAvatarPreview = r1;
    }

    /* renamed from: -$$Nest$fputradialGradient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15248$$Nest$fputradialGradient(GroupCallActivity r0, RadialGradient r1) {
        r0.radialGradient = r1;
    }

    /* renamed from: -$$Nest$fputrenderNodeBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15249$$Nest$fputrenderNodeBlur(GroupCallActivity r0, RenderNode r1) {
        r0.renderNodeBlur = r1;
    }

    /* renamed from: -$$Nest$fputrenderNodeBlurScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15250$$Nest$fputrenderNodeBlurScale(GroupCallActivity r0, float r1) {
        r0.renderNodeBlurScale = r1;
    }

    /* renamed from: -$$Nest$fputscheduleAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15251$$Nest$fputscheduleAnimator(GroupCallActivity r0, ValueAnimator r1) {
        r0.scheduleAnimator = r1;
    }

    /* renamed from: -$$Nest$fputschedulePeer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15252$$Nest$fputschedulePeer(GroupCallActivity r0, TLRPC.InputPeer r1) {
        r0.schedulePeer = r1;
    }

    /* renamed from: -$$Nest$fputscheduled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15253$$Nest$fputscheduled(GroupCallActivity r0, boolean r1) {
        r0.scheduled = r1;
    }

    /* renamed from: -$$Nest$fputscrimAnimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15254$$Nest$fputscrimAnimatorSet(GroupCallActivity r0, AnimatorSet r1) {
        r0.scrimAnimatorSet = r1;
    }

    /* renamed from: -$$Nest$fputscrimPopupLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15255$$Nest$fputscrimPopupLayout(GroupCallActivity r0, View r1) {
        r0.scrimPopupLayout = r1;
    }

    /* renamed from: -$$Nest$fputscrimPopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15256$$Nest$fputscrimPopupWindow(GroupCallActivity r0, ActionBarPopupWindow r1) {
        r0.scrimPopupWindow = r1;
    }

    /* renamed from: -$$Nest$fputselfPeer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15257$$Nest$fputselfPeer(GroupCallActivity r0, TLRPC.Peer r1) {
        r0.selfPeer = r1;
    }

    /* renamed from: -$$Nest$fputshowLightingProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15258$$Nest$fputshowLightingProgress(GroupCallActivity r0, float r1) {
        r0.showLightingProgress = r1;
    }

    /* renamed from: -$$Nest$fputshowWavesProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15259$$Nest$fputshowWavesProgress(GroupCallActivity r0, float r1) {
        r0.showWavesProgress = r1;
    }

    /* renamed from: -$$Nest$fputstartingGroupCall, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15260$$Nest$fputstartingGroupCall(GroupCallActivity r0, boolean r1) {
        r0.startingGroupCall = r1;
    }

    /* renamed from: -$$Nest$fputswitchProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15261$$Nest$fputswitchProgress(GroupCallActivity r0, float r1) {
        r0.switchProgress = r1;
    }

    /* renamed from: -$$Nest$fputuserSwitchObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15262$$Nest$fputuserSwitchObject(GroupCallActivity r0, TLObject r1) {
        r0.userSwitchObject = r1;
    }

    /* renamed from: -$$Nest$fputwasNotInLayoutFullscreen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15263$$Nest$fputwasNotInLayoutFullscreen(GroupCallActivity r0, Boolean r1) {
        r0.wasNotInLayoutFullscreen = r1;
    }

    /* renamed from: -$$Nest$mapplyCallParticipantUpdates, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15264$$Nest$mapplyCallParticipantUpdates(GroupCallActivity r0, boolean r1) {
        r0.applyCallParticipantUpdates(r1);
    }

    /* renamed from: -$$Nest$mattachRenderer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15265$$Nest$mattachRenderer(GroupCallActivity r0, GroupCallGridCell r1, boolean r2) {
        r0.attachRenderer(r1, r2);
    }

    /* renamed from: -$$Nest$mcalculateScrollTopOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15266$$Nest$mcalculateScrollTopOffset(GroupCallActivity r0) {
        return r0.calculateScrollTopOffset();
    }

    /* renamed from: -$$Nest$mcheckContentOverlayed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15267$$Nest$mcheckContentOverlayed(GroupCallActivity r0) {
        r0.checkContentOverlayed();
    }

    /* renamed from: -$$Nest$mcheckGroupCallUiPositions_MessagesList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15268$$Nest$mcheckGroupCallUiPositions_MessagesList(GroupCallActivity r0) {
        r0.checkGroupCallUiPositions_MessagesList();
    }

    /* renamed from: -$$Nest$mclearScrimView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15269$$Nest$mclearScrimView(GroupCallActivity r0) {
        r0.clearScrimView();
    }

    /* renamed from: -$$Nest$mdismissAvatarPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15270$$Nest$mdismissAvatarPreview(GroupCallActivity r0, boolean r1) {
        r0.dismissAvatarPreview(r1);
    }

    /* renamed from: -$$Nest$mfillColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15271$$Nest$mfillColors(GroupCallActivity r0, int r1, int[] r2) {
        r0.fillColors(r1, r2);
    }

    /* renamed from: -$$Nest$mfindGroupCallGridCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallGridCell m15272$$Nest$mfindGroupCallGridCell(GroupCallActivity r0) {
        return r0.findGroupCallGridCell();
    }

    /* renamed from: -$$Nest$mgetLink, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15273$$Nest$mgetLink(GroupCallActivity r0, boolean r1) {
        r0.getLink(r1);
    }

    /* renamed from: -$$Nest$mhideKeyboardOrEmojiView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15274$$Nest$mhideKeyboardOrEmojiView(GroupCallActivity r0) {
        r0.hideKeyboardOrEmojiView();
    }

    /* renamed from: -$$Nest$minvalidateLayoutFullscreen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15275$$Nest$minvalidateLayoutFullscreen(GroupCallActivity r0) {
        r0.invalidateLayoutFullscreen();
    }

    /* renamed from: -$$Nest$minviteUserToCall, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15276$$Nest$minviteUserToCall(GroupCallActivity r0, long r1, boolean r3) {
        r0.inviteUserToCall(r1, r3);
    }

    /* renamed from: -$$Nest$misBulletinTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15277$$Nest$misBulletinTop(GroupCallActivity r0) {
        return r0.isBulletinTop();
    }

    /* renamed from: -$$Nest$misStillConnecting, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15278$$Nest$misStillConnecting(GroupCallActivity r0) {
        return r0.isStillConnecting();
    }

    /* renamed from: -$$Nest$mprocessSelectedOption, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15279$$Nest$mprocessSelectedOption(GroupCallActivity r0, TLRPC.GroupCallParticipant r1, long r2, int r4) {
        r0.processSelectedOption(r1, r2, r4);
    }

    /* renamed from: -$$Nest$mrunUpdateTextureLightningRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15280$$Nest$mrunUpdateTextureLightningRunnable(GroupCallActivity r0) {
        r0.runUpdateTextureLightningRunnable();
    }

    /* renamed from: -$$Nest$msendGroupCallMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15281$$Nest$msendGroupCallMessage(GroupCallActivity r0, TLRPC.TL_textWithEntities r1) {
        r0.sendGroupCallMessage(r1);
    }

    /* renamed from: -$$Nest$msetColorProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15282$$Nest$msetColorProgress(GroupCallActivity r0, float r1) {
        r0.setColorProgress(r1);
    }

    /* renamed from: -$$Nest$msetCommentsEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15283$$Nest$msetCommentsEnabled(GroupCallActivity r0, boolean r1) {
        r0.setCommentsEnabled(r1);
    }

    /* renamed from: -$$Nest$mshowMenuForCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15284$$Nest$mshowMenuForCell(GroupCallActivity r0, View r1) {
        return r0.showMenuForCell(r1);
    }

    /* renamed from: -$$Nest$mshowRecordHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15285$$Nest$mshowRecordHint(GroupCallActivity r0, View r1) {
        r0.showRecordHint(r1);
    }

    /* renamed from: -$$Nest$mshowReminderHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15286$$Nest$mshowReminderHint(GroupCallActivity r0) {
        r0.showReminderHint();
    }

    /* renamed from: -$$Nest$mtoggleAdminSpeak, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15287$$Nest$mtoggleAdminSpeak(GroupCallActivity r0) {
        r0.toggleAdminSpeak();
    }

    /* renamed from: -$$Nest$mupdateItems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15288$$Nest$mupdateItems(GroupCallActivity r0) {
        r0.updateItems();
    }

    /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15289$$Nest$mupdateLayout(GroupCallActivity r0, boolean r1) {
        r0.updateLayout(r1);
    }

    /* renamed from: -$$Nest$mupdateMuteButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15290$$Nest$mupdateMuteButton(GroupCallActivity r0, int r1, boolean r2) {
        r0.updateMuteButton(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15291$$Nest$mupdateState(GroupCallActivity r0, boolean r1, boolean r2) {
        r0.updateState(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateSubtitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15292$$Nest$mupdateSubtitle(GroupCallActivity r0) {
        r0.updateSubtitle();
    }

    /* renamed from: -$$Nest$mupdateTopBulletinY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15293$$Nest$mupdateTopBulletinY(GroupCallActivity r0) {
        r0.updateTopBulletinY();
    }

    /* renamed from: -$$Nest$smgetRandomEmoji, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m15294$$Nest$smgetRandomEmoji() {
        return getRandomEmoji();
    }

    static {
        updateTextureLightningQueue = new DispatchQueue("updateTextureLightningQueue");
    }

    private GroupCallActivity(Activity r49, AccountInstance r50, ChatObject.Call r51, TLRPC.Chat r52, TLRPC.InputPeer r53, boolean r54, String r55) {
        super(r49, true, true, null);
        this.callMessageEnterContainerBgPaint = new Paint(1);
        this.undoView = new UndoView[2];
        this.visibleVideoParticipants = new ArrayList();
        this.rect = new RectF();
        this.listViewBackgroundPaint = new Paint(1);
        this.oldParticipants = new ArrayList();
        this.oldVideoParticipants = new ArrayList();
        this.oldInvited = new ArrayList();
        this.oldShadyJoin = new ArrayList();
        this.oldShadyLeft = new ArrayList();
        this.windowInsetsStateHolder = new WindowInsetsStateHolder(new GroupCallActivity$$ExternalSyntheticLambda6(this));
        this.muteButtonState = 0;
        this.animatingToFullscreenExpand = false;
        this.paint = new Paint(7);
        this.paintTmp = new Paint(7);
        this.states = new WeavingState[8];
        this.switchProgress = 1.0f;
        this.invalidateColors = true;
        this.colorsTmp = new int[4];
        this.attachedRenderers = new ArrayList();
        this.attachedRenderersTmp = new ArrayList();
        this.wasExpandBigSize = Boolean.TRUE;
        this.cellFlickerDrawable = new CellFlickerDrawable();
        this.statusIconPool = new ArrayList();
        this.onUserLeaveHintListener = new GroupCallActivity$$ExternalSyntheticLambda17(this);
        this.updateSchedeulRunnable = new AnonymousClass1(this);
        this.unmuteRunnable = new GroupCallActivity$$ExternalSyntheticLambda28();
        this.pressRunnable = new GroupCallActivity$$ExternalSyntheticLambda29(this);
        this.needTextureLightning = false;
        this.updateTextureLightningRunnable = new AnonymousClass2(this);
        this.visiblePeerIds = new LongSparseIntArray();
        this.gradientColors = new int[2];
        this.listViewVideoVisibility = true;
        this.invites = new String[2];
        this.popupAnimationIndex = -1;
        this.diffUtilsCallback = new AnonymousClass65(this);
        CubicBezierInterpolator r3 = CubicBezierInterpolator.DEFAULT;
        this.animatorHideButtons = new BoolAnimator(2, this, r3, 350);
        this.animatorMessageIsEmpty = new BoolAnimator(3, this, r3, 220, true);
        this.animatorMessageInputHeight = new FactorAnimator(4, this, r3, 350);
        GroupCallActivity r1 = this;
        r1.animatorHasVideo = new BoolAnimator(5, this, r3, 350);
        AndroidUtilities.enableEdgeToEdge(r1.getWindow());
        r1.setOpenNoDelay(true);
        r1.accountInstance = r50;
        r1.call = r51;
        r1.schedulePeer = r53;
        r1.currentChat = r52;
        r1.scheduledHash = r55;
        r1.currentAccount = r50.getCurrentAccount();
        r1.scheduleHasFewPeers = r54;
        r1.resourcesProvider = new DarkBlueThemeResourcesProvider();
        r1.smoothKeyboardAnimationEnabled = true;
        r1.smoothKeyboardByBottom = true;
        r1.maxGroupCallMessageLength = MessagesController.getInstance(r1.currentAccount).config.groupCallMessageLengthLimit.get();
        r1.fullWidth = true;
        isTabletMode = false;
        isLandscapeMode = false;
        paused = false;
        r1.setDelegate(new AnonymousClass3(r1));
        r1.drawDoubleNavigationBar = true;
        r1.drawNavigationBar = true;
        if (Build.VERSION.SDK_INT < 30) goto L5;
        r1.getWindow().setNavigationBarColor(-16777216);
    L5:
        r1.scrollNavBar = true;
        r1.navBarColorKey = -1;
        r1.scrimPaint = new AnonymousClass4(r1);
        r1.setOnDismissListener(new GroupCallActivity$$ExternalSyntheticLambda30(r1));
        r1.setDimBehindAlpha(75);
        ListAdapter r4 = new ListAdapter(r1, r49);
        r1.listAdapter = r4;
        RecordStatusDrawable r5 = new RecordStatusDrawable(true);
        int r17 = Theme.key_voipgroup_speakingText;
        r5.setColor(Theme.getColor(r17));
        r5.start();
        AnonymousClass5 r6 = new AnonymousClass5(r1, r49, r5);
        r1.actionBar = r6;
        r6.setSubtitle(_UrlKt.FRAGMENT_ENCODE_SET);
        r6.getSubtitleTextView().setVisibility(0);
        r6.createAdditionalSubtitleTextView();
        r6.getAdditionalSubtitleTextView().setPadding(AndroidUtilities.dp(24.0f), 0, 0, 0);
        AndroidUtilities.updateViewVisibilityAnimated(r6.getAdditionalSubtitleTextView(), r1.drawSpeakingSubtitle, 1.0f, false);
        r6.getAdditionalSubtitleTextView().setTextColor(Theme.getColor(r17));
        int r56 = Theme.key_voipgroup_lastSeenTextUnscrolled;
        r6.setSubtitleColor(Theme.getColor(r56));
        r6.setBackButtonImage(R.drawable.ic_ab_back);
        r6.setOccupyStatusBar(false);
        r6.setAllowOverlayTitle(false);
        int r14 = Theme.key_voipgroup_actionBarItems;
        r6.setItemsColor(Theme.getColor(r14), false);
        r6.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarActionModeDefaultSelector), false);
        r6.setTitleColor(Theme.getColor(r14));
        r6.setSubtitleColor(Theme.getColor(r56));
        r6.setActionBarMenuOnItemClick(new AnonymousClass6(r1, r49));
        if (r53 == null) goto L8;
        TLRPC.InputPeer r15 = r53;
    L9:
        if (r15 != null) goto L12;
        TLRPC.TL_peerUser r57 = new TLRPC.TL_peerUser();
        r1.selfPeer = r57;
        r57.user_id = r50.getUserConfig().getClientUserId();
    L20:
        VoIPService.audioLevelsCallback = new GroupCallActivity$$ExternalSyntheticLambda31(r1);
        r50.getNotificationCenter().addObserver(r1, NotificationCenter.groupCallUpdated);
        r50.getNotificationCenter().addObserver(r1, NotificationCenter.needShowAlert);
        r50.getNotificationCenter().addObserver(r1, NotificationCenter.chatInfoDidLoad);
        r50.getNotificationCenter().addObserver(r1, NotificationCenter.didLoadChatAdmins);
        r50.getNotificationCenter().addObserver(r1, NotificationCenter.applyGroupCallVisibleParticipants);
        r50.getNotificationCenter().addObserver(r1, NotificationCenter.userInfoDidLoad);
        r50.getNotificationCenter().addObserver(r1, NotificationCenter.mainUserInfoChanged);
        r50.getNotificationCenter().addObserver(r1, NotificationCenter.updateInterfaces);
        r50.getNotificationCenter().addObserver(r1, NotificationCenter.groupCallScreencastStateChanged);
        r50.getNotificationCenter().addObserver(r1, NotificationCenter.groupCallSpeakingUsersUpdated);
        r50.getNotificationCenter().addObserver(r1, NotificationCenter.conferenceEmojiUpdated);
        NotificationCenter.getGlobalInstance().addObserver(r1, NotificationCenter.webRtcMicAmplitudeEvent);
        NotificationCenter.getGlobalInstance().addObserver(r1, NotificationCenter.didEndCall);
        r1.shadowDrawable = r49.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        RLottieDrawable r21 = new RLottieDrawable(R.raw.voip_filled, AndroidUtilities.dp(46.0f), AndroidUtilities.dp(46.0f), true, null);
        r1.bigMicDrawable = r21;
        r1.handDrawables = new RLottieDrawable(R.raw.hand_2, AndroidUtilities.dp(46.0f), AndroidUtilities.dp(46.0f), true, null);
        AnonymousClass7 r58 = new AnonymousClass7(r1, r49);
        r1.sizeNotifierFrameLayout = r58;
        r1.containerView = r58;
        r58.setClipToPadding(false);
        r1.containerView.setFocusable(true);
        r1.containerView.setFocusableInTouchMode(true);
        r1.containerView.setWillNotDraw(false);
        ViewGroup r59 = r1.containerView;
        int r12 = r1.backgroundPaddingLeft;
        r59.setPadding(r12, 0, r12, 0);
        r1.containerView.setKeepScreenOn(true);
        r1.containerView.setClipChildren(false);
        r1.reactionEffectImageReceiver = new ImageReceiver(r1.containerView);
        if (r53 == null) goto L24;
        SimpleTextView r10 = new SimpleTextView(r49);
        r1.scheduleStartInTextView = r10;
        r10.setGravity(17);
        r1.scheduleStartInTextView.setTextColor(-1);
        r1.scheduleStartInTextView.setTypeface(AndroidUtilities.bold());
        r1.scheduleStartInTextView.setTextSize(18);
        r1.scheduleStartInTextView.setText(LocaleController.getString(R.string.VoipChatStartsIn));
        r1.containerView.addView(r1.scheduleStartInTextView, LayoutHelper.createFrame(-2, -2.0f, 49, 21.0f, 0.0f, 21.0f, 311.0f));
        AnonymousClass8 r102 = new AnonymousClass8(r1, r49);
        r1.scheduleTimeTextView = r102;
        r102.setGravity(17);
        r1.scheduleTimeTextView.setTextColor(-1);
        r1.scheduleTimeTextView.setTypeface(AndroidUtilities.bold());
        r1.scheduleTimeTextView.setTextSize(60);
        r1.containerView.addView(r1.scheduleTimeTextView, LayoutHelper.createFrame(-2, -2.0f, 49, 21.0f, 0.0f, 21.0f, 231.0f));
        SimpleTextView r103 = new SimpleTextView(r49);
        r1.scheduleStartAtTextView = r103;
        r103.setGravity(17);
        r1.scheduleStartAtTextView.setTextColor(-1);
        r1.scheduleStartAtTextView.setTypeface(AndroidUtilities.bold());
        r1.scheduleStartAtTextView.setTextSize(18);
        r1.containerView.addView(r1.scheduleStartAtTextView, LayoutHelper.createFrame(-2, -2.0f, 49, 21.0f, 0.0f, 21.0f, 201.0f));
    L24:
        if (r1.isRtmpStream() == false) goto L26;
        LightningView r104 = new LightningView(r1, r49);
        r1.textureLightningView = r104;
        r1.containerView.addView(r104, LayoutHelper.createFrame(-1, 80.0f, 51, 0.0f, 44.0f, 0.0f, 0.0f));
    L26:
        AnonymousClass9 r105 = new AnonymousClass9(r1, r49);
        r1.listView = r105;
        r105.setClipToPadding(false);
        r1.listView.setClipChildren(false);
        GroupCallItemAnimator r106 = new GroupCallItemAnimator(r1, null);
        r1.itemAnimator = r106;
        r106.setTranslationInterpolator(r3);
        r106.setRemoveDuration(350);
        r106.setAddDuration(350);
        r106.setMoveDuration(350);
        r106.setDelayAnimations(false);
        r1.listView.setItemAnimator(r106);
        r1.listView.setOnScrollListener(new AnonymousClass10(r1));
        r1.listView.setVerticalScrollBarEnabled(false);
        RecyclerListView r107 = r1.listView;
        Context r31 = r1.getContext();
        if (isLandscapeMode == false) goto L29;
        int r32 = 6;
    L30:
        FillLastGridLayoutManager r30 = new FillLastGridLayoutManager(r31, r32, 1, false, 0, r1.listView);
        r1.layoutManager = r30;
        r107.setLayoutManager(r30);
        AnonymousClass11 r108 = new AnonymousClass11(r1);
        r1.spanSizeLookup = r108;
        r30.setSpanSizeLookup(r108);
        r1.listView.addItemDecoration(new AnonymousClass12(r1));
        r30.setBind(false);
        r1.containerView.addView(r1.listView, LayoutHelper.createFrame(-1, -1.0f, 51, 14.0f, 14.0f, 14.0f, 231.0f));
        r1.listView.setAdapter(r4);
        r1.listView.setTopBottomSelectorRadius(13);
        r1.listView.setSelectorDrawableColor(Theme.getColor(Theme.key_voipgroup_listSelector));
        r1.listView.setOnItemClickListener(new GroupCallActivity$$ExternalSyntheticLambda32(r1, r49, r51));
        r1.listView.setOnItemLongClickListener(new GroupCallActivity$$ExternalSyntheticLambda33(r1));
        if (r1.isRtmpStream() == false) goto L33;
        WatchersView r42 = new WatchersView(r1, r1.getContext());
        r1.watchersView = r42;
        r1.containerView.addView(r42, LayoutHelper.createFrame(-1, 0.0f, 49, 0.0f, 0.0f, 0.0f, 0.0f));
    L33:
        RecyclerListView r43 = new RecyclerListView(r49);
        r1.tabletVideoGridView = r43;
        r1.containerView.addView(r43, LayoutHelper.createFrame(-1, -1.0f, 51, 14.0f, 14.0f, 324.0f, 14.0f));
        RecyclerListView r44 = r1.tabletVideoGridView;
        GroupCallTabletGridAdapter r510 = new GroupCallTabletGridAdapter(r51, r1.currentAccount, r1);
        r1.tabletGridAdapter = r510;
        r44.setAdapter(r510);
        GridLayoutManager r45 = new GridLayoutManager(r49, 6, 1, false);
        r1.tabletVideoGridView.setLayoutManager(r45);
        r45.setSpanSizeLookup(new AnonymousClass14(r1));
        r1.tabletVideoGridView.setOnItemClickListener(new GroupCallActivity$$ExternalSyntheticLambda34(r1));
        DefaultItemAnimator r46 = new DefaultItemAnimator();
        r46.setDelayAnimations(false);
        r46.setTranslationInterpolator(r3);
        r46.setRemoveDuration(350);
        r46.setAddDuration(350);
        r46.setMoveDuration(350);
        r1.tabletVideoGridView.setItemAnimator(new AnonymousClass15(r1));
        r1.tabletVideoGridView.setOnScrollListener(new AnonymousClass16(r1));
        r1.tabletGridAdapter.setVisibility(r1.tabletVideoGridView, false, false);
        r1.tabletVideoGridView.setVisibility(8);
        AnonymousClass17 r33 = new AnonymousClass17(r1, r49);
        r1.buttonsContainer = r33;
        int r47 = Theme.getColor(Theme.key_voipgroup_unmuteButton2);
        int r511 = Color.red(r47);
        int r62 = Color.green(r47);
        int r48 = Color.blue(r47);
        r1.radialMatrix = new Matrix();
        r1.radialGradient = new RadialGradient(0.0f, 0.0f, AndroidUtilities.dp(72.72727f), new int[]{Color.argb(50, r511, r62, r48), Color.argb(0, r511, r62, r48)}, null, Shader.TileMode.CLAMP);
        Paint r410 = new Paint(1);
        r1.radialPaint = r410;
        r410.setShader(r1.radialGradient);
        BlobDrawable r411 = new BlobDrawable(9);
        r1.tinyWaveDrawable = r411;
        BlobDrawable r63 = new BlobDrawable(12);
        r1.bigWaveDrawable = r63;
        r411.minRadius = AndroidUtilities.dp(62.0f) * 0.45454547f;
        r411.maxRadius = AndroidUtilities.dp(72.0f) * 0.45454547f;
        r411.generateBlob();
        r63.minRadius = AndroidUtilities.dp(65.0f) * 0.45454547f;
        r63.maxRadius = AndroidUtilities.dp(75.0f) * 0.45454547f;
        r63.generateBlob();
        Paint r412 = r411.paint;
        int r122 = Theme.key_voipgroup_unmuteButton;
        r412.setColor(ColorUtils.setAlphaComponent(Theme.getColor(r122), 38));
        r63.paint.setColor(ColorUtils.setAlphaComponent(Theme.getColor(r122), 76));
        VoIPToggleButton r413 = new VoIPToggleButton(r49, 50.0f);
        r1.soundButton = r413;
        r413.setCheckable(true);
        r413.setTextSize(12);
        r33.addButton(r413);
        r413.setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda35(r1));
        VoIPToggleButton r414 = new VoIPToggleButton(r49, 50.0f);
        r1.cameraButton = r414;
        r414.setCheckable(true);
        r414.setTextSize(12);
        r414.showText(false, false);
        IconManager r64 = IconManager.INSTANCE;
        IconPackType r123 = IconPackType.SOLAR;
        float r302 = 3.5f;
        if (r64.isBasePackOnly(r123) == false) goto L36;
        float r13 = 3.5f;
    L37:
        r414.setCrossOffset(-AndroidUtilities.dpf2(r13 + 3.5f));
        if (r64.isBasePackOnly(r123) == true) goto L41;
        r302 = 0.0f;
    L41:
        r414.setCrossOffsetY(-AndroidUtilities.dpf2(r302));
        r414.setData(R.drawable.calls_video, -1, 0, 1.0f, true, LocaleController.getString(R.string.VoipCamera), false, false);
        VoIPToggleButton r65 = new VoIPToggleButton(r49, 50.0f);
        r1.flipButton = r65;
        r65.setCheckable(true);
        r65.setTextSize(12);
        r65.showText(false, false);
        RLottieImageView r124 = new RLottieImageView(r49);
        r65.addView(r124, LayoutHelper.createFrame(32, 32.0f, 1, 0.0f, 10.0f, 0.0f, 0.0f));
        RLottieDrawable r303 = new RLottieDrawable(R.raw.camera_flip, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), true, null);
        r1.flipIcon = r303;
        r124.setAnimation(r303);
        r65.setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda7(r1));
        r33.addButton(r65);
        VoIPToggleButton r66 = new VoIPToggleButton(r49, 50.0f);
        r1.speakerButton = r66;
        r66.setCheckable(true);
        r66.setTextSize(12);
        r66.showText(false, false);
        ImageView r125 = new ImageView(r49);
        r1.speakerImageView = r125;
        r125.setScaleType(ImageView.ScaleType.FIT_CENTER);
        int r132 = R.drawable.filled_sound_on;
        r1.speakerIcon = r132;
        r125.setImageResource(r132);
        r125.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        r125.setScaleX(1.11f);
        r125.setScaleY(1.11f);
        r66.addView(r125, LayoutHelper.createFrame(30, 30.0f, 1, 0.0f, 11.0f, 0.0f, 0.0f));
        r66.setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda8(r1));
        r33.addButton(r66);
        r33.addButton(r414);
        VoIPToggleButton r415 = new VoIPToggleButton(r49, 50.0f);
        r1.leaveButton = r415;
        r415.setTextSize(12);
        r415.setData(R.drawable.calls_decline, -1, Theme.getColor(Theme.key_voipgroup_leaveButton), 0.3f, false, LocaleController.getString(R.string.VoipGroupLeave), false, false);
        r415.setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda9(r1, r49));
        VoIPToggleButton r512 = new VoIPToggleButton(r49, 50.0f);
        r1.messageButton = r512;
        r512.setCheckable(true);
        r512.setChecked(true, false);
        r512.setTextSize(12);
        r512.setData(R.drawable.filled_voice_comment_32, -1, 0, 1.0f, true, LocaleController.getString(R.string.VoipMessage), false, false);
        AnonymousClass18 r67 = new AnonymousClass18(r1, r49);
        r1.muteButtonIcon = r67;
        r67.setAnimation(r21);
        r67.setScaleType(ImageView.ScaleType.CENTER);
        VoIPToggleButton r109 = new VoIPToggleButton(r49, 50.0f);
        r1.muteButton = r109;
        r109.setDrawBackground(false);
        r109.setTextSize(12);
        r109.setData(0, 0, 0, "Text", false, false);
        r109.addView(r67, LayoutHelper.createFrame(50, 50, 49));
        r33.addButton(r109);
        r109.setOnClickListener(new AnonymousClass19(r1));
        r33.addButton(r512);
        r33.addButton(r415);
        ImageView r34 = new ImageView(r49);
        r1.expandOrMinimizeButton = r34;
        r34.setVisibility(8);
        r34.setImageResource(R.drawable.voice_expand);
        r109.addView(r34, LayoutHelper.createFrame(24, 24.0f, 49, 0.0f, 13.0f, 0.0f, 0.0f));
        if (r1.call != null) goto L44;
    L48:
        RadialProgressView r35 = new RadialProgressView(r49);
        r1.radialProgressView = r35;
        r35.setSize(AndroidUtilities.dp(50.0f));
        r35.setStrokeWidth(2.0f);
        r35.setProgressColor(Theme.getColor(Theme.key_voipgroup_connectingProgress));
        r6.setAlpha(0.0f);
        r6.getBackButton().setScaleX(0.9f);
        r6.getBackButton().setScaleY(0.9f);
        r6.getBackButton().setTranslationX(-AndroidUtilities.dp(14.0f));
        r6.getTitleTextView().setTranslationY(AndroidUtilities.dp(23.0f));
        r6.getSubtitleTextView().setTranslationY(AndroidUtilities.dp(20.0f));
        r6.getAdditionalSubtitleTextView().setTranslationY(AndroidUtilities.dp(20.0f));
        ActionBarMenuItem r416 = new ActionBarMenuItem(r49, null, 0, Theme.getColor(r14));
        r1.otherItem = r416;
        r416.setLongClickEnabled(false);
        r416.setIcon(R.drawable.ic_ab_other);
        r416.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        r416.setSubMenuOpenSide(2);
        r416.setDelegate(new GroupCallActivity$$ExternalSyntheticLambda10(r1));
        int r513 = Theme.key_voipgroup_actionBarItemsSelector;
        r416.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(r513), 6));
        r416.setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda11(r1));
        r416.setPopupItemsColor(Theme.getColor(r14), false);
        r416.setPopupItemsColor(Theme.getColor(r14), true);
        ActionBarMenuItem r68 = new ActionBarMenuItem(r49, null, 0, Theme.getColor(r14));
        r1.pipItem = r68;
        r68.setLongClickEnabled(false);
        r68.setIcon(R.drawable.msg_voice_pip);
        r68.setContentDescription(LocaleController.getString(R.string.AccDescrPipMode));
        r68.setBackground(Theme.createSelectorDrawable(Theme.getColor(r513), 6));
        r68.setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda12(r1));
        ActionBarMenuItem r11 = new ActionBarMenuItem(r49, null, 0, Theme.getColor(r14));
        r1.screenShareItem = r11;
        r11.setLongClickEnabled(false);
        r11.setIcon(R.drawable.msg_screencast);
        r11.setContentDescription(LocaleController.getString(R.string.AccDescrPipMode));
        r11.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(r513), 6));
        r11.setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda13(r1));
        AnonymousClass20 r514 = new AnonymousClass20(r1, r49, r49);
        r1.titleTextView = r514;
        AnonymousClass21 r1010 = new AnonymousClass21(r1, r49);
        r1.actionBarBackground = r1010;
        r1010.setAlpha(0.0f);
        Paint r126 = new Paint(1);
        r1.liveLabelPaint = r126;
        r126.setColor(-12761513);
        AnonymousClass22 r127 = new AnonymousClass22(r1, r1.getContext());
        r1.liveLabelTextView = r127;
        r127.setTextColor(r1.getThemedColor(r14));
        r127.setTextSize(1, 11.0f);
        r127.setText(LocaleController.getString(R.string.VoipChannelLabelLive));
        r127.setMaxLines(1);
        r127.setGravity(17);
        r127.setTypeface(AndroidUtilities.bold());
        r127.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(0.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(0.0f));
        r127.setTag(-1);
        if (r1.isRtmpStream() == true) goto L51;
        r127.setVisibility(8);
    L51:
        LinearLayout r0 = new LinearLayout(r1.getContext());
        r1.titleLayout = r0;
        r0.setOrientation(0);
        r0.addView(r514, LayoutHelper.createLinear(0, -2, 1.0f));
        r0.addView(r127, LayoutHelper.createLinear(-2, 18, 6.0f, 4.0f, 0.0f, 0.0f));
        r1.containerView.addView(r1010, LayoutHelper.createFrame(-1, -2.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        r1.containerView.addView(r0, LayoutHelper.createFrame(-2, -2.0f, 51, 23.0f, 0.0f, 48.0f, 0.0f));
        r1.containerView.addView(r6, LayoutHelper.createFrame(-1, -2.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        LinearLayout r02 = new LinearLayout(r49);
        r1.menuItemsContainer = r02;
        r02.setOrientation(0);
        r02.addView(r11, LayoutHelper.createLinear(48, 48));
        r02.addView(r68, LayoutHelper.createLinear(48, 48));
        r02.addView(r416, LayoutHelper.createLinear(48, 48));
        r1.containerView.addView(r02, LayoutHelper.createFrame(-2, 48, 53));
        View r03 = new View(r49);
        r1.actionBarShadow = r03;
        r03.setAlpha(0.0f);
        r03.setBackgroundColor(Theme.getColor(Theme.key_dialogShadowLine));
        r1.containerView.addView(r03, LayoutHelper.createFrame(-1, 1.0f));
        int r04 = 0;
    L53:
        if (r04 >= 2) goto L55;
        r1.undoView[r04] = new AnonymousClass23(r1, r49);
        r1.undoView[r04].setAdditionalTranslationY(AndroidUtilities.dp(10.0f));
        r1.undoView[r04].setTranslationZ(AndroidUtilities.dp(5.0f));
        r1.containerView.addView(r1.undoView[r04], LayoutHelper.createFrame(-1, -2.0f, 83, 8.0f, 0.0f, 8.0f, 8.0f));
        r04 = r04 + 1;
        goto L53
    L55:
        AccountSelectCell r05 = new AccountSelectCell(r49, true);
        r1.accountSelectCell = r05;
        r05.setTag(R.id.fit_width_tag, 240);
        r1.otherItem.addSubItem(8, r05, -2, AndroidUtilities.dp(48.0f));
        r1.otherItem.setShowSubmenuByMove(false);
        int r2 = Theme.key_voipgroup_listSelector;
        r05.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(r2), 6, 6));
        ActionBarMenuSubItem r06 = r1.otherItem.addSubItem(1, 0, LocaleController.getString(R.string.VoipGroupAllCanSpeak), true);
        r1.everyoneItem = r06;
        r06.updateSelectorBackground(true, false);
        ActionBarMenuSubItem r07 = r1.otherItem.addSubItem(2, 0, LocaleController.getString(R.string.VoipGroupOnlyAdminsCanSpeak), true);
        r1.adminItem = r07;
        r07.updateSelectorBackground(false, true);
        ActionBarMenuSubItem r08 = r1.everyoneItem;
        int r36 = Theme.key_voipgroup_checkMenu;
        r08.setCheckColor(r36);
        r1.everyoneItem.setColors(Theme.getColor(r36), Theme.getColor(r36));
        r1.adminItem.setCheckColor(r36);
        r1.adminItem.setColors(Theme.getColor(r36), Theme.getColor(r36));
        Paint r09 = new Paint(1);
        int r37 = Theme.key_voipgroup_actionBarItems;
        r09.setColor(Theme.getColor(r37));
        r09.setStyle(Paint.Style.STROKE);
        r09.setStrokeWidth(AndroidUtilities.dp(1.5f));
        r09.setStrokeCap(Paint.Cap.ROUND);
        ActionBarMenuSubItem r010 = r1.otherItem.addSubItem(10, R.drawable.msg_voice_speaker, null, LocaleController.getString(R.string.VoipGroupAudio), true, false);
        r1.soundItem = r010;
        r010.setItemHeight(56);
        ActionBarMenuSubItem r011 = r1.otherItem.addSubItem(11, R.drawable.msg_noise_on, null, LocaleController.getString(R.string.VoipNoiseCancellation), true, false);
        r1.noiseItem = r011;
        r011.setItemHeight(56);
        View r417 = r1.otherItem.addDivider(ColorUtils.blendARGB(Theme.getColor(Theme.key_voipgroup_actionBar), -16777216, 0.3f));
        r1.soundItemDivider = r417;
        ((ViewGroup.MarginLayoutParams) r417.getLayoutParams()).topMargin = 0;
        ((ViewGroup.MarginLayoutParams) r417.getLayoutParams()).bottomMargin = 0;
        ActionBarMenuItem r418 = r1.otherItem;
        int r322 = R.drawable.msg_edit;
        RecordCallDrawable r515 = r1.recordCallDrawable;
        if (ChatObject.isChannelOrGiga(r1.currentChat) == false) goto L59;
        int r69 = R.string.VoipChannelEditTitle;
    L60:
        ActionBarMenuSubItem r419 = r418.addSubItem(6, r322, r515, LocaleController.getString(r69), true, false);
        r1.editTitleItem = r419;
        ActionBarMenuSubItem r516 = r1.otherItem.addSubItem(7, R.drawable.msg_permissions, r1.recordCallDrawable, LocaleController.getString(R.string.VoipGroupEditPermissions), false, false);
        r1.permissionItem = r516;
        ActionBarMenuSubItem r610 = r1.otherItem.addSubItem(3, R.drawable.msg_link, LocaleController.getString(R.string.VoipGroupShareInviteLink));
        r1.inviteItem = r610;
        r1.recordCallDrawable = new RecordCallDrawable();
        ActionBarMenuSubItem r1011 = r1.otherItem.addSubItem(9, R.drawable.msg_screencast, LocaleController.getString(R.string.VoipChatStartScreenCapture));
        r1.screenItem = r1011;
        ActionBarMenuSubItem r112 = r1.otherItem.addSubItem(5, 0, r1.recordCallDrawable, LocaleController.getString(R.string.VoipGroupRecordCall), true, false);
        r1.recordItem = r112;
        r1.recordCallDrawable.setParentView(r112.getImageView());
        ActionBarMenuSubItem r9 = r1.otherItem.addSubItem(12, R.drawable.menu_stream_comments_24, LocaleController.getString(R.string.VoipChannelEnableComments));
        r1.enableComments = r9;
        ActionBarMenuSubItem r128 = r1.otherItem.addSubItem(13, R.drawable._menu_stream_comments_off_24, LocaleController.getString(R.string.VoipChannelDisableComments));
        r1.disableComments = r128;
        ActionBarMenuItem r22 = r1.otherItem;
        int r133 = R.drawable.msg_cancel;
        if (r1.isConference() == false) goto L65;
        int r142 = R.string.VoipGroupEndConference;
    L68:
        ActionBarMenuSubItem r23 = r22.addSubItem(4, r133, LocaleController.getString(r142));
        r1.leaveItem = r23;
        r1.otherItem.setPopupItemsSelectorColor(Theme.getColor(r2));
        r1.otherItem.getPopupLayout().setFitItems(true);
        r9.setColors(Theme.getColor(r37), Theme.getColor(r37));
        r128.setColors(Theme.getColor(r37), Theme.getColor(r37));
        r1.soundItem.setColors(Theme.getColor(r37), Theme.getColor(r37));
        r011.setColors(Theme.getColor(r37), Theme.getColor(r37));
        int r012 = Theme.key_voipgroup_leaveCallMenu;
        r23.setColors(Theme.getColor(r012), Theme.getColor(r012));
        r610.setColors(Theme.getColor(r37), Theme.getColor(r37));
        r419.setColors(Theme.getColor(r37), Theme.getColor(r37));
        r516.setColors(Theme.getColor(r37), Theme.getColor(r37));
        r112.setColors(Theme.getColor(r37), Theme.getColor(r37));
        r1011.setColors(Theme.getColor(r37), Theme.getColor(r37));
        if (r1.call == null) goto L72;
        r1.initCreatedGroupCall();
    L72:
        if (r1.isConference() == false) goto L80;
        r1.encryptionDrawable = new CallEncryptionCellDrawable(r49);
        VoIPService r013 = VoIPService.getSharedInstance();
        CallEncryptionCellDrawable r24 = r1.encryptionDrawable;
        if (r013 == null) goto L78;
        ConferenceCall r014 = r013.conference;
        if (r014 == null) goto L78;
        String[] r113 = r014.getEmojis();
    L79:
        r24.setEmojis(r113);
    L78:
        r113 = null;
    L80:
        r1.updateTitle(false);
        r1.actionBar.getTitleTextView().setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda14(r1));
        r1.fullscreenUsersListView = new AnonymousClass24(r1, r49);
        AnonymousClass25 r015 = new AnonymousClass25(r1);
        r1.fullscreenListItemAnimator = r015;
        r1.fullscreenUsersListView.setClipToPadding(false);
        r015.setDelayAnimations(false);
        r015.setTranslationInterpolator(CubicBezierInterpolator.DEFAULT);
        r015.setRemoveDuration(350);
        r015.setAddDuration(350);
        r015.setMoveDuration(350);
        r1.fullscreenUsersListView.setItemAnimator(r015);
        r1.fullscreenUsersListView.setOnScrollListener(new AnonymousClass26(r1));
        r1.fullscreenUsersListView.setClipChildren(false);
        LinearLayoutManager r016 = new LinearLayoutManager(r49);
        r016.setOrientation(0);
        r1.fullscreenUsersListView.setLayoutManager(r016);
        RecyclerListView r017 = r1.fullscreenUsersListView;
        GroupCallFullscreenAdapter r25 = new GroupCallFullscreenAdapter(r51, r1.currentAccount, r1);
        r1.fullscreenAdapter = r25;
        r017.setAdapter(r25);
        r1.fullscreenAdapter.setVisibility(r1.fullscreenUsersListView, false);
        r1.fullscreenUsersListView.setOnItemClickListener(new GroupCallActivity$$ExternalSyntheticLambda15(r1));
        r1.fullscreenUsersListView.setOnItemLongClickListener(new GroupCallActivity$$ExternalSyntheticLambda16(r1));
        r1.fullscreenUsersListView.setVisibility(8);
        r1.fullscreenUsersListView.addItemDecoration(new AnonymousClass27(r1));
        AnonymousClass28 r018 = new AnonymousClass28(r1, r49, r1.listView, r1.fullscreenUsersListView, r1.attachedRenderers, r1.call, this);
        r1.renderersContainer = r018;
        r018.setClipChildren(false);
        r1.fullscreenAdapter.setRenderersPool(r1.attachedRenderers, r1.renderersContainer);
        if (r1.tabletVideoGridView == null) goto L83;
        r1.tabletGridAdapter.setRenderersPool(r1.attachedRenderers, r1.renderersContainer);
    L83:
        AnonymousClass29 r517 = new AnonymousClass29(r1, r49);
        r1.avatarPagerIndicator = r517;
        AnonymousClass30 r019 = new AnonymousClass30(r1, r49, r1.actionBar, r1.listView, r517);
        r1.avatarsViewPager = r019;
        r019.setImagesLayerNum(Integer.MAX_VALUE);
        r019.setInvalidateWithParent(true);
        r517.setProfileGalleryView(r019);
        AnonymousClass31 r1012 = new AnonymousClass31(r1, r49);
        r1.avatarPreviewContainer = r1012;
        r1012.setVisibility(8);
        r019.setVisibility(0);
        r019.addOnPageChangeListener(new AnonymousClass32(r1));
        AnonymousClass33 r114 = new AnonymousClass33(r1, r49);
        r1.blurredView = r114;
        r1.containerView.addView(r1.renderersContainer);
        r1.renderersContainer.addView(r1.fullscreenUsersListView, LayoutHelper.createFrame(-1, 80.0f, 80, 0.0f, 0.0f, 0.0f, 100.0f));
        r1.buttonsContainer.setWillNotDraw(false);
        View r020 = new View(r49);
        r1.buttonsBackgroundGradientView = r020;
        int[] r38 = r1.gradientColors;
        r38[0] = r1.backgroundColor;
        r38[1] = 0;
        GradientDrawable r39 = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, r1.gradientColors);
        r1.buttonsBackgroundGradient = r39;
        r020.setBackground(r39);
        r1.containerView.addView(r020, LayoutHelper.createFrame(-1, 60, 83));
        View r021 = new View(r49);
        r1.buttonsBackgroundGradientView2 = r021;
        r021.setBackgroundColor(r1.gradientColors[0]);
        r1.containerView.addView(r021, LayoutHelper.createFrame(-1, 0, 83));
        GroupCallMessagesListView r022 = new GroupCallMessagesListView(r49);
        r1.groupCallMessagesListView = r022;
        r022.setDelegate(new AnonymousClass34(r1));
        r022.setClickCellDelegate(new AnonymousClass35(r1));
        if (r1.call == null) goto L86;
        r022.setGroupCall(r1.accountInstance.getCurrentAccount(), r1.call.getInputGroupCall(false));
    L86:
        r1.containerView.addView(r022, LayoutHelper.createFrame(-1, -1.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f));
        AnimatedTextView r023 = new AnimatedTextView(r49, false, true, true);
        r1.limitTextView = r023;
        r023.setGravity(17);
        r023.setTextSize(AndroidUtilities.dp(15.0f));
        r023.setTextColor(-1);
        r023.setAnimationProperties(0.4f, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        r023.setTypeface(AndroidUtilities.bold());
        r1.containerView.addView(r1.buttonsContainer);
        AnonymousClass36 r024 = new AnonymousClass36(r1, r49, r1.sizeNotifierFrameLayout, LaunchActivity.getLastFragment(), 5, true, r1.resourcesProvider);
        r1.callMessageEnterView = r024;
        r024.includeNavigationBar = true;
        r024.setFilters(new InputFilter[]{new InputFilter.LengthFilter(r1.maxGroupCallMessageLength)});
        r024.getEditText().setLinkTextColor(-11683585);
        r024.setHint(LocaleController.getString(R.string.TypeMessage));
        r024.getEditText().addTextChangedListener(new AnonymousClass37(r1));
        r024.onResume();
        AnonymousClass38 r26 = new AnonymousClass38(r1, r49);
        r1.callMessageEnterUnderContainer = r26;
        r1.containerView.addView(r26, LayoutHelper.createFrame(-1, -1.0f));
        AnonymousClass39 r27 = new AnonymousClass39(r1, r49);
        r1.callMessageEnterContainer = r27;
        r27.addView(r024, LayoutHelper.createFrame(-1, -2.0f, 80, 0.0f, 0.0f, 48.0f, 0.0f));
        r27.addView(r023, LayoutHelper.createFrame(52, 16.0f, 85, 0.0f, 0.0f, 0.0f, 32.0f));
        r023.setTranslationY(-AndroidUtilities.dp(20.0f));
        r1.containerView.addView(r27, LayoutHelper.createFrame(-1, -2, 80));
        ImageView r025 = new ImageView(r49);
        r1.callMessageHideButton = r025;
        int r310 = Theme.key_listSelector;
        r025.setBackground(Theme.createSelectorDrawable(r1.getThemedColor(r310)));
        int r611 = Theme.getColor(Theme.key_graySectionText, r1.resourcesProvider);
        PorterDuff.Mode r7 = PorterDuff.Mode.MULTIPLY;
        r025.setColorFilter(new PorterDuffColorFilter(r611, r7));
        ImageView.ScaleType r518 = ImageView.ScaleType.CENTER;
        r025.setScaleType(r518);
        r025.setImageResource(R.drawable.arrow_more);
        r025.setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda18(r1));
        ImageView r612 = new ImageView(r49);
        r1.callMessageSendButton = r612;
        r612.setBackground(Theme.createSelectorDrawable(r1.getThemedColor(r310)));
        r612.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlueIcon, r1.resourcesProvider), r7));
        r612.setScaleType(r518);
        r612.setImageResource(R.drawable.ic_send);
        r612.setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda19(r1));
        r27.addView(r025, LayoutHelper.createFrame(48, 48, 85));
        r27.addView(r612, LayoutHelper.createFrame(48, 48, 85));
        r1.containerView.addView(r114);
        r1012.addView(r019, LayoutHelper.createFrame(-1, -1.0f));
        r1012.addView(r517, LayoutHelper.createFrame(-1, -1.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f));
        r1.containerView.addView(r1012, LayoutHelper.createFrame(-1, -1.0f, 0, 14.0f, 14.0f, 14.0f, 14.0f));
        r1.applyCallParticipantUpdates(false);
        r1.listAdapter.notifyDataSetChanged();
        if (isTabletMode == false) goto L89;
        r1.tabletGridAdapter.update(false, r1.tabletVideoGridView);
    L89:
        r1.oldCount = r1.listAdapter.getItemCount();
        if (r53 == null) goto L103;
        TextView r026 = new TextView(r49);
        r1.scheduleInfoTextView = r026;
        r026.setGravity(17);
        r1.scheduleInfoTextView.setTextColor(-8682615);
        r1.scheduleInfoTextView.setTextSize(1, 14.0f);
        if (ChatObject.isChannel(r1.currentChat) == false) goto L98;
        TLRPC.Chat r027 = r1.currentChat;
        if (r027 == null) goto L98;
        if (r027.megagroup == true) goto L98;
        r1.scheduleInfoTextView.setTag(1);
    L98:
        r1.containerView.addView(r1.scheduleInfoTextView, LayoutHelper.createFrame(-2, -2.0f, 81, 21.0f, 0.0f, 21.0f, 100.0f));
        NumberPicker r28 = new NumberPicker(r49);
        r28.setTextColor(-1);
        r28.setSelectorColor(-9598483);
        r28.setTextOffset(AndroidUtilities.dp(10.0f));
        r28.setItemCount(5);
        AnonymousClass40 r420 = new AnonymousClass40(r1, r49);
        r420.setItemCount(5);
        r420.setTextColor(-1);
        r420.setSelectorColor(-9598483);
        r420.setTextOffset(-AndroidUtilities.dp(10.0f));
        AnonymousClass41 r421 = new AnonymousClass41(r1, r49);
        r421.setItemCount(5);
        r421.setTextColor(-1);
        r421.setSelectorColor(-9598483);
        r421.setTextOffset(-AndroidUtilities.dp(34.0f));
        AnonymousClass42 r028 = new AnonymousClass42(r1, r49);
        r1.scheduleButtonTextView = r028;
        r028.setLines(1);
        r1.scheduleButtonTextView.setSingleLine(true);
        r1.scheduleButtonTextView.setEllipsize(TextUtils.TruncateAt.END);
        r1.scheduleButtonTextView.setGravity(17);
        r1.scheduleButtonTextView.setTextColor(-1);
        r1.scheduleButtonTextView.setTypeface(AndroidUtilities.bold());
        r1.scheduleButtonTextView.setTextSize(1, 14.0f);
        r1.containerView.addView(r1.scheduleButtonTextView, LayoutHelper.createFrame(-1, 48.0f, 81, 21.0f, 0.0f, 21.0f, 20.5f));
        r1.scheduleButtonTextView.setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda20(r1, r28, r420, r421, r52, r50, r15));
        AnonymousClass44 r029 = new AnonymousClass44(this, r49, r28, r420, r421);
        r1 = this;
        Activity r29 = r49;
        r1.scheduleTimerContainer = r029;
        r029.setWeightSum(1.0f);
        r1.scheduleTimerContainer.setOrientation(0);
        r1.containerView.addView(r1.scheduleTimerContainer, LayoutHelper.createFrame(-1, 270.0f, 51, 0.0f, 50.0f, 0.0f, 0.0f));
        long r519 = System.currentTimeMillis();
        Calendar r72 = Calendar.getInstance();
        r72.setTimeInMillis(r519);
        int r8 = r72.get(1);
        int r115 = r72.get(6);
        r1.scheduleTimerContainer.addView(r28, LayoutHelper.createLinear(0, 270, 0.5f));
        r28.setMinValue(0);
        r28.setMaxValue(365);
        r28.setWrapSelectorWheel(false);
        r28.setFormatter(new GroupCallActivity$$ExternalSyntheticLambda21(r519, r72, r8));
        GroupCallActivity$$ExternalSyntheticLambda22 r82 = new GroupCallActivity$$ExternalSyntheticLambda22(r1, r28, r420, r421);
        r28.setOnValueChangedListener(r82);
        r420.setMinValue(0);
        r420.setMaxValue(23);
        r1.scheduleTimerContainer.addView(r420, LayoutHelper.createLinear(0, 270, 0.2f));
        r420.setFormatter(new GroupCallActivity$$ExternalSyntheticLambda23());
        r420.setOnValueChangedListener(r82);
        r421.setMinValue(0);
        r421.setMaxValue(59);
        r421.setValue(0);
        r421.setFormatter(new GroupCallActivity$$ExternalSyntheticLambda24());
        r1.scheduleTimerContainer.addView(r421, LayoutHelper.createLinear(0, 270, 0.3f));
        r421.setOnValueChangedListener(r82);
        r72.setTimeInMillis(r519 + 10800000);
        r72.set(12, 0);
        r72.set(13, 0);
        r72.set(14, 0);
        int r520 = r72.get(6);
        int r613 = r72.get(12);
        int r73 = r72.get(11);
        if (r115 == r520) goto L101;
        int r1013 = 1;
    L102:
        r28.setValue(r1013);
        r421.setValue(r613);
        r420.setValue(r73);
        AlertsCreator.checkScheduleDate(r1.scheduleButtonTextView, r1.scheduleInfoTextView, 604800, 2, r28, r420, r421);
    L104:
        AnonymousClass45 r311 = new AnonymousClass45(r1, (ViewGroup) r1.getWindow().getDecorView(), r1.containerView);
        r1.pinchToZoomHelper = r311;
        r311.setCallback(new AnonymousClass46(r1));
        r019.setPinchToZoomHelper(r1.pinchToZoomHelper);
        r1.cameraButton.setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda25(r1, r29));
        r1.updateScheduleUI(false);
        r1.updateItems();
        r1.updateSpeakerPhoneIcon(false);
        r1.updateState(false, false);
        r1.setColorProgress(0.0f);
        r1.updateSubtitle();
        FrameLayout r030 = new FrameLayout(r29);
        r1.bulletinContainer = r030;
        r1.containerView.addView(r030, LayoutHelper.createFrame(-1, 200, 87));
        r1.messageButton.setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda26(r1));
        r1.checkGroupCallUi();
        ScaleStateListAnimator.apply(r1.cameraButton);
        ScaleStateListAnimator.apply(r1.soundButton);
        ScaleStateListAnimator.apply(r1.flipButton);
        ScaleStateListAnimator.apply(r1.speakerButton);
        ScaleStateListAnimator.apply(r1.muteButton);
        ScaleStateListAnimator.apply(r1.leaveButton);
        ScaleStateListAnimator.apply(r1.messageButton);
        ViewCompat.setOnApplyWindowInsetsListener(r1.containerView, new GroupCallActivity$$ExternalSyntheticLambda27(r1));
        return;
    L101:
        r1013 = 0;
        goto L102
    L103:
        r29 = r49;
        goto L104
    L65:
        if (ChatObject.isChannelOrGiga(r1.currentChat) == false) goto L67;
        r142 = R.string.VoipChannelEndChat;
        goto L68
    L67:
        r142 = R.string.VoipGroupEndChat;
        goto L68
    L59:
        r69 = R.string.VoipGroupEditTitle;
        goto L60
    L44:
        if (r1.isRtmpStream() == false) goto L48;
        if (r1.call.isScheduled() == true) goto L48;
        r34.setVisibility(0);
        r67.setVisibility(8);
        goto L48
    L36:
        r13 = 0.0f;
        goto L37
    L29:
        r32 = 2;
        goto L30
    L12:
        if ((r15 instanceof TLRPC.TL_inputPeerChannel) == false) goto L15;
        TLRPC.TL_peerChannel r521 = new TLRPC.TL_peerChannel();
        r1.selfPeer = r521;
        r521.channel_id = r15.channel_id;
        goto L20
    L15:
        if ((r15 instanceof TLRPC.TL_inputPeerUser) == false) goto L18;
        TLRPC.TL_peerUser r522 = new TLRPC.TL_peerUser();
        r1.selfPeer = r522;
        r522.user_id = r15.user_id;
        goto L20
    L18:
        if ((r15 instanceof TLRPC.TL_inputPeerChat) == false) goto L20;
        TLRPC.TL_peerChat r523 = new TLRPC.TL_peerChat();
        r1.selfPeer = r523;
        r523.chat_id = r15.chat_id;
        goto L20
    L8:
        r15 = VoIPService.getSharedInstance().getGroupCallPeer();
        goto L9
    }

    public static /* synthetic */ ViewGroup access$000(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$100(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$1000(GroupCallActivity r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1100(GroupCallActivity r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1200(GroupCallActivity r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1300(GroupCallActivity r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1400(GroupCallActivity r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ ViewGroup access$1500(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$1600(GroupCallActivity r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ ViewGroup access$1700(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$1800(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$1900(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$200(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$2000(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$2102(GroupCallActivity r0, int r1) {
        r0.navBarColor = r1;
        return r1;
    }

    public static /* synthetic */ ViewGroup access$2200(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$2300(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$2400(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$2500(GroupCallActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ViewGroup access$2600(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$2700(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$2800(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$2900(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$300(GroupCallActivity r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ ViewGroup access$3000(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$3100(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$3200(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$3300(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$3400(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$3500(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$3600(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$3700(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$3800(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$3900(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$400(GroupCallActivity r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ ViewGroup access$4000(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4100(GroupCallActivity r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4200(GroupCallActivity r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$4300(GroupCallActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4400(GroupCallActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4500(GroupCallActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4600(GroupCallActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4700(GroupCallActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4800(GroupCallActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4900(GroupCallActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(GroupCallActivity r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ ViewGroup access$5000(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$5100(GroupCallActivity r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$5200(GroupCallActivity r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$5300(GroupCallActivity r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$600(GroupCallActivity r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$700(GroupCallActivity r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$800(GroupCallActivity r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$900(GroupCallActivity r0) {
        return r0.backgroundPaddingLeft;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void applyCallParticipantUpdates(boolean r26) {
        GroupCallRenderersContainer r0 = this.renderersContainer;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.listView != null) goto L7;
        return;
    L7:
        if (this.call != null) goto L9;
        return;
    L9:
        if (this.delayedGroupCallUpdated == false) goto L12;
        return;
    L12:
        if (r0.inFullscreenMode == false) goto L14;
        r0.setVisibleParticipant(true);
    L14:
        long r4 = MessageObject.getPeerId(this.call.selfPeer);
        if (r4 != MessageObject.getPeerId(this.selfPeer)) goto L17;
    L19:
        int r2 = this.listView.getChildCount();
        int r02 = Integer.MAX_VALUE;
        View r18 = null;
        int r6 = 0;
        int r7 = 0;
    L20:
        if (r6 >= r2) goto L32;
        View r8 = this.listView.getChildAt(r6);
        RecyclerView.ViewHolder r9 = this.listView.findContainingViewHolder(r8);
        if (r9 == null) goto L31;
        if (r9.getAdapterPosition() == (-1)) goto L31;
        if (r9.getLayoutPosition() == (-1)) goto L31;
        if (r18 != null) goto L29;
    L30:
        r7 = r9.getLayoutPosition();
        r02 = r8.getTop();
        r18 = r8;
        goto L31
    L29:
        if (r8.getTop() < r02) goto L30;
    L31:
        r6 = r6 + 1;
        goto L20
    L32:
        updateVideoParticipantList();
        if (this.listView.getItemAnimator() == null) goto L37;
        if (r26 == true) goto L37;
        this.listView.setItemAnimator(null);
    L124:
        UpdateCallback r03 = new UpdateCallback(this.listAdapter, null);     // Catch: Exception -> L50
        int r24 = r7;
        int r23 = r2;
        setOldRows(ListAdapter.m15321$$Nest$fgetencryptionRow(this.listAdapter), ListAdapter.m15318$$Nest$fgetaddMemberRow(this.listAdapter), ListAdapter.m15330$$Nest$fgetusersStartRow(this.listAdapter), ListAdapter.m15329$$Nest$fgetusersEndRow(this.listAdapter), ListAdapter.m15323$$Nest$fgetinvitedStartRow(this.listAdapter), ListAdapter.m15322$$Nest$fgetinvitedEndRow(this.listAdapter), ListAdapter.m15326$$Nest$fgetshadyJoinStartRow(this.listAdapter), ListAdapter.m15325$$Nest$fgetshadyJoinEndRow(this.listAdapter), ListAdapter.m15328$$Nest$fgetshadyLeftStartRow(this.listAdapter), ListAdapter.m15327$$Nest$fgetshadyLeftEndRow(this.listAdapter), ListAdapter.m15332$$Nest$fgetusersVideoGridStartRow(this.listAdapter), ListAdapter.m15331$$Nest$fgetusersVideoGridEndRow(this.listAdapter), ListAdapter.m15333$$Nest$fgetvideoGridDividerRow(this.listAdapter), ListAdapter.m15334$$Nest$fgetvideoNotAvailableRow(this.listAdapter), ListAdapter.m15319$$Nest$fgetconferenceAddPeopleRow(this.listAdapter), ListAdapter.m15320$$Nest$fgetconferenceShareLinkRow(this.listAdapter));     // Catch: Exception -> L46
        ListAdapter.m15335$$Nest$mupdateRows(this.listAdapter);     // Catch: Exception -> L46
        DiffUtil.calculateDiff(this.diffUtilsCallback).dispatchUpdatesTo(r03);     // Catch: Exception -> L46
    L53:
        this.call.saveActiveDates();
        if (r18 == null) goto L56;
        this.layoutManager.scrollToPositionWithOffset(r24, r18.getTop() - this.listView.getPaddingTop());
    L56:
        this.oldParticipants.clear();
        this.oldParticipants.addAll(this.call.visibleParticipants);
        this.oldVideoParticipants.clear();
        this.oldVideoParticipants.addAll(this.visibleVideoParticipants);
        this.oldInvited.clear();
        this.oldInvited.addAll(this.call.invitedUsers);
        this.oldShadyJoin.clear();
        this.oldShadyJoin.addAll(this.call.shadyJoinParticipants);
        this.oldShadyLeft.clear();
        this.oldShadyLeft.addAll(this.call.shadyLeftParticipants);
        this.oldCount = this.listAdapter.getItemCount();
        int r22 = r23;
        int r5 = 0;
    L57:
        if (r5 >= r22) goto L76;
        View r04 = this.listView.getChildAt(r5);
        boolean r3 = r04 instanceof GroupCallUserCell;
        if (r3 == false) goto L61;
    L62:
        RecyclerView.ViewHolder r42 = this.listView.findContainingViewHolder(r04);
        if (r42 == null) goto L75;
        if (r3 == false) goto L70;
        GroupCallUserCell r05 = (GroupCallUserCell) r04;
        if (r42.getAdapterPosition() == (this.listAdapter.getItemCount() - 2)) goto L68;
        boolean r32 = true;
    L69:
        r05.setDrawDivider(r32);
        goto L75
    L68:
        r32 = false;
        goto L69
    L70:
        GroupCallInvitedCell r06 = (GroupCallInvitedCell) r04;
        if (r42.getAdapterPosition() == (this.listAdapter.getItemCount() - 2)) goto L73;
        boolean r33 = true;
    L74:
        r06.setDrawDivider(r33);
        goto L75
    L73:
        r33 = false;
    L75:
        r5 = r5 + 1;
        goto L57
    L61:
        if ((r04 instanceof GroupCallInvitedCell) == false) goto L75;
    L76:
        boolean r07 = this.renderersContainer.autoPinEnabled();
        GroupCallRenderersContainer r25 = this.renderersContainer;
        if (r25.inFullscreenMode == false) goto L87;
        ChatObject.VideoParticipant r27 = r25.fullscreenParticipant;
        if (r27 == null) goto L87;
        if (ChatObject.Call.videoIsActive(r27.participant, r27.presentation, this.call) == true) goto L87;
        if (this.visibleVideoParticipants.isEmpty() == true) goto L86;
        if (r07 == false) goto L87;
        int r28 = 0;
        fullscreenFor(this.visibleVideoParticipants.get(0));
    L88:
        this.fullscreenAdapter.update(true, this.fullscreenUsersListView);
        if (this.fullscreenUsersListView.getVisibility() != 0) goto L92;
        AndroidUtilities.updateVisibleRows(this.fullscreenUsersListView);
    L92:
        if (isTabletMode == false) goto L95;
        this.tabletGridAdapter.update(true, this.tabletVideoGridView);
    L95:
        if (this.listView.getVisibility() != 0) goto L97;
        AndroidUtilities.updateVisibleRows(this.listView);
    L97:
        this.attachedRenderersTmp.clear();
        this.attachedRenderersTmp.addAll(this.attachedRenderers);
        int r52 = r28;
    L99:
        if (r52 >= this.attachedRenderersTmp.size()) goto L101;
        this.attachedRenderersTmp.get(r52).updateAttachState(true);
        r52 = r52 + 1;
        goto L99
    L101:
        ChatObject.Call r08 = this.call;
        if (r08 == null) goto L108;
        GroupCallRenderersContainer r34 = this.renderersContainer;
        if (r34.inFullscreenMode == false) goto L108;
        ChatObject.VideoParticipant r35 = r34.fullscreenParticipant;
        if (r35 == null) goto L108;
        r08.participants.get(MessageObject.getPeerId(r35.participant.peer));
    L108:
        ChatObject.Call r09 = this.call;
        if (r09 != null) goto L111;
    L113:
        boolean r36 = r28;
    L114:
        this.animatorHasVideo.setValue(r36, r26);
        if (r36 == this.hasVideo) goto L141;
        this.hasVideo = r36;
        if (isTabletMode == false) goto L142;
        this.containerView.requestLayout();
        return;
    L142:
        return;
    L141:
        return;
    L111:
        if (r09.visibleVideoParticipants.isEmpty() == true) goto L113;
        r36 = 1;
        goto L114
    L86:
        r28 = 0;
        fullscreenFor(null);
    L87:
        r28 = 0;
    L46:
        e = e;
    L52:
        FileLog.e(e);
        this.listAdapter.notifyDataSetChanged();
    L48:
        e = e;
        r24 = r7;
        r23 = r2;
    L50:
        e = e;
        r23 = r2;
        r24 = r7;
    L37:
        if (this.listView.getItemAnimator() != null) goto L124;
        if (r26 == false) goto L124;
        this.listView.setItemAnimator(this.itemAnimator);
        goto L124
    L17:
        if (this.call.participants.get(r4) == null) goto L19;
        this.selfPeer = this.call.selfPeer;
        goto L19
    }

    private void attachRenderer(GroupCallGridCell r10, boolean r11) {
        if (isDismissed() == true) goto L18;
        if (r11 == false) goto L11;
        if (r10.getRenderer() != null) goto L11;
        r10.setRenderer(GroupCallMiniTextureView.getOrCreate(this.attachedRenderers, this.renderersContainer, r10, null, null, r10.getParticipant(), this.call, this));
        return;
    L11:
        if (r11 == false) goto L13;
        return;
    L13:
        if (r10.getRenderer() == null) goto L17;
        r10.getRenderer().setPrimaryView(null);
        r10.setRenderer(null);
        return;
    L17:
        return;
    }

    private int calculateScrollTopOffset() {
        int r0 = this.listView.getChildCount();
        int r1 = Integer.MAX_VALUE;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        View r3 = this.listView.getChildAt(r2);
        if (this.listView.getChildAdapterPosition(r3) < 0) goto L7;
        r1 = Math.min(r1, r3.getTop());
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        return r1;
    }

    private void cancelMutePress() {
        if (this.scheduled == false) goto L6;
        this.scheduled = false;
        AndroidUtilities.cancelRunOnUIThread(this.pressRunnable);
    L6:
        if (this.pressed == false) goto L9;
        this.pressed = false;
        MotionEvent r0 = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0);
        this.muteButton.onTouchEvent(r0);
        r0.recycle();
        return;
    }

    private void checkContentOverlayed() {
        if (this.avatarPriviewTransitionInProgress == false) goto L5;
    L9:
        boolean r0 = false;
    L11:
        if (this.contentFullyOverlayed == r0) goto L14;
        this.contentFullyOverlayed = r0;
        this.buttonsContainer.invalidate();
        this.containerView.invalidate();
        this.listView.invalidate();
        return;
    L14:
        return;
    L5:
        if (this.blurredView.getVisibility() != 0) goto L9;
        if (this.blurredView.getAlpha() != 1.0f) goto L9;
        r0 = true;
        goto L11
    }

    private void checkGroupCallUi() {
        checkGroupCallUiPositions_EnterView();
        checkGroupCallUiPositions_ReactionsLayout();
        checkGroupCallUiPositions_MessagesList();
        checkGroupCallUiPositions_ButtonsList();
        checkGroupCallUiPositions_FullscreenUsersList();
        checkGroupCallUiPositions_SendButton();
        checkGroupCallUiAlpha_ButtonsList();
        checkGroupCallUiAlpha_EnterView();
        checkGroupCallUiAlpha_ReactionsLayout();
    }

    private void checkGroupCallUiAlpha_ButtonsList() {
        this.buttonsContainer.setAlpha(1.0f - this.animatorHideButtons.getFloatValue());
    }

    private void checkGroupCallUiAlpha_EnterView() {
        float r0 = this.windowInsetsStateHolder.getAnimatedKeyboardVisibility();
        if (r0 <= 0.0f) goto L5;
        int r1 = 0;
    L6:
        this.callMessageEnterContainer.setAlpha(r0);
        this.callMessageEnterUnderContainer.setAlpha(r0);
        if (this.callMessageEnterContainer.getVisibility() == r1) goto L14;
        this.callMessageEnterContainer.setVisibility(r1);
        this.callMessageEnterUnderContainer.setVisibility(r1);
        if (r1 == 8) goto L11;
        return;
    L11:
        if (this.callMessageEnterView.isFocused() == false) goto L16;
        this.callMessageEnterView.clearFocus();
        return;
    L16:
        return;
    L14:
        return;
    L5:
        r1 = 8;
        goto L6
    }

    private void checkGroupCallUiAlpha_ReactionsLayout() {
        if (this.reactionsContainerLayout == null) goto L19;
        float r0 = this.windowInsetsStateHolder.getAnimatedKeyboardVisibility() * this.animatorMessageIsEmpty.getFloatValue();
        this.reactionsContainerLayout.setAlpha(r0);
        if (r0 <= 0.0f) goto L7;
        int r1 = 0;
    L9:
        if (this.reactionsContainerLayout.getVisibility() == r1) goto L13;
        this.reactionsContainerLayout.setVisibility(r1);
        if (r1 != 8) goto L13;
        this.reactionsContainerLayout.reset();
    L13:
        ReactionsContainerLayout r4 = this.reactionsContainerLayout;
        if (r4.skipEnterAnimation == false) goto L16;
        return;
    L16:
        if (r0 != 1.0f) goto L21;
        r4.skipEnterAnimation = true;
        return;
    L21:
        return;
    L7:
        r1 = 8;
        goto L9
    }

    private void checkGroupCallUiPositions_ButtonsList() {
        if (isTabletMode == false) goto L6;
        this.buttonsContainer.setTranslationX(0.0f);
        this.buttonsContainer.setTranslationY(0.0f);
        return;
    L6:
        boolean r0 = isLandscapeMode;
        GroupCallActivityButtonsLayout r2 = this.buttonsContainer;
        if (r0 == false) goto L10;
        r2.setTranslationX(this.animatorHideButtons.getFloatValue() * AndroidUtilities.dp(94.0f));
        this.buttonsContainer.setTranslationY(0.0f);
        return;
    L10:
        r2.setTranslationX(0.0f);
        this.buttonsContainer.setTranslationY(this.animatorHideButtons.getFloatValue() * AndroidUtilities.dp(94.0f));
    }

    private void checkGroupCallUiPositions_EnterView() {
        this.callMessageEnterContainer.setTranslationY((-this.windowInsetsStateHolder.getAnimatedMaxBottomInset()) + this.containerView.getPaddingBottom());
        this.callMessageEnterUnderContainer.invalidate();
    }

    private void checkGroupCallUiPositions_FullscreenUsersList() {
        if (isTabletMode == false) goto L6;
        this.fullscreenUsersListView.setTranslationX(0.0f);
        this.fullscreenUsersListView.setTranslationY(0.0f);
        return;
    L6:
        boolean r0 = isLandscapeMode;
        RecyclerListView r2 = this.fullscreenUsersListView;
        if (r0 == false) goto L10;
        r2.setTranslationX(this.animatorHideButtons.getFloatValue() * AndroidUtilities.dp(94.0f));
        this.fullscreenUsersListView.setTranslationY(0.0f);
        return;
    L10:
        r2.setTranslationX(0.0f);
        this.fullscreenUsersListView.setTranslationY(this.animatorHideButtons.getFloatValue() * AndroidUtilities.dp(94.0f));
    }

    private void checkGroupCallUiPositions_MessagesList() {
        float r0 = this.animatorMessageInputHeight.getFactor();
        float r02 = -((((this.windowInsetsStateHolder.getAnimatedMaxBottomInset() - this.containerView.getPaddingBottom()) + r0) + (AndroidUtilities.dp(68.0f) * this.animatorMessageIsEmpty.getFloatValue())) + AndroidUtilities.dp(10.0f));
        if (isTabletMode == false) goto L6;
        float r2 = (1.0f - this.animatorHasVideo.getFloatValue()) * AndroidUtilities.dp(-91.0f);
    L9:
        float r03 = AndroidUtilities.lerp(r2, r02, this.windowInsetsStateHolder.getAnimatedKeyboardVisibility());
        float r1 = ((this.containerView.getMeasuredHeight() - this.scrollOffsetY) + r03) - this.backgroundPaddingTop;
        float r12 = Math.max((r1 / 3.0f) * 2.0f, r1 - AndroidUtilities.dp(250.0f));
        this.groupCallMessagesListView.setTranslationY(r03);
        this.groupCallMessagesListView.setVisibleHeight((int) r12);
        return;
    L6:
        if (isLandscapeMode == false) goto L8;
        r2 = 0.0f;
        goto L9
    L8:
        r2 = ((this.animatorHideButtons.getFloatValue() * AndroidUtilities.dp(94.0f)) - (AndroidUtilities.dp(104.0f) * this.renderersContainer.progressToFullscreenMode)) - AndroidUtilities.dp(91.0f);
        goto L9
    }

    private void checkGroupCallUiPositions_ReactionsLayout() {
        if (this.reactionsContainerLayout == null) goto L6;
        this.reactionsContainerLayout.setTranslationY(((-this.windowInsetsStateHolder.getAnimatedMaxBottomInset()) + this.containerView.getPaddingBottom()) + ((-this.animatorMessageIsEmpty.getFloatValue()) * AndroidUtilities.dp(64.0f)));
        return;
    }

    private void checkGroupCallUiPositions_SendButton() {
        float r0 = this.animatorMessageIsEmpty.getFloatValue();
        this.callMessageHideButton.setScaleX(AndroidUtilities.lerp(0.25f, 1.0f, r0));
        this.callMessageHideButton.setScaleY(AndroidUtilities.lerp(0.25f, 1.0f, r0));
        this.callMessageHideButton.setAlpha(r0);
        ImageView r1 = this.callMessageHideButton;
        boolean r6 = false;
        if (r0 <= 0.9f) goto L5;
        boolean r5 = true;
    L6:
        r1.setClickable(r5);
        float r02 = 1.0f - r0;
        this.callMessageSendButton.setScaleX(AndroidUtilities.lerp(0.25f, 1.0f, r02));
        this.callMessageSendButton.setScaleY(AndroidUtilities.lerp(0.25f, 1.0f, r02));
        this.callMessageSendButton.setAlpha(r02);
        ImageView r8 = this.callMessageSendButton;
        if (r02 <= 0.9f) goto L9;
        r6 = true;
    L9:
        r8.setClickable(r6);
        return;
    L5:
        r5 = false;
        goto L6
    }

    private void checkInsets() {
        checkGroupCallUiPositions_MessagesList();
        checkGroupCallUiAlpha_ReactionsLayout();
        checkGroupCallUiAlpha_EnterView();
        checkGroupCallUiPositions_EnterView();
        checkGroupCallUiPositions_ReactionsLayout();
        this.containerView.invalidate();
    }

    private void clearScrimView() {
        GroupCallMiniTextureView r0 = this.scrimRenderer;
        if (r0 == null) goto L5;
        r0.textureView.setRoundCorners(AndroidUtilities.dp(8.0f));
        this.scrimRenderer.setShowingAsScrimView(false, false);
        this.scrimRenderer.invalidate();
        this.renderersContainer.invalidate();
    L5:
        GroupCallUserCell r02 = this.scrimView;
        if (r02 != null) goto L8;
    L12:
        GroupCallUserCell r03 = this.scrimView;
        if (r03 == null) goto L15;
        r03.setProgressToAvatarPreview(0.0f);
        this.scrimView.setAboutVisible(false);
        this.scrimView.getAvatarImageView().setAlpha(1.0f);
    L15:
        GroupCallFullscreenAdapter.GroupCallUserCell r04 = this.scrimFullscreenView;
        if (r04 == null) goto L18;
        r04.getAvatarImageView().setAlpha(1.0f);
    L18:
        this.scrimView = null;
        this.scrimGridView = null;
        this.scrimFullscreenView = null;
        this.scrimRenderer = null;
        return;
    L8:
        if (this.hasScrimAnchorView == true) goto L12;
        if (r02.getParent() == null) goto L12;
        this.containerView.removeView(this.scrimView);
        goto L12
    }

    public static void create(LaunchActivity r13, AccountInstance r14, TLRPC.Chat r15, TLRPC.InputPeer r16, boolean r17, String r18) {
        if (groupCallInstance != null) goto L16;
        if (r16 == null) goto L6;
    L8:
        if (r16 == null) goto L10;
        groupCallInstance = new GroupCallActivity(r13, r14, r14.getMessagesController().getGroupCall(r15.id, false), r15, r16, r17, r18);
    L14:
        groupCallInstance.parentActivity = r13;
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda0());
        return;
    L10:
        ChatObject.Call r8 = VoIPService.getSharedInstance().groupCall;
        if (r8 == null) goto L18;
        TLRPC.Chat r9 = r14.getMessagesController().getChat(Long.valueOf(r8.chatId));
        r8.addSelfDummyParticipant(true);
        groupCallInstance = new GroupCallActivity(r13, r14, r8, r9, null, r17, r18);
        goto L14
    L18:
        return;
    L6:
        if (VoIPService.getSharedInstance() != null) goto L8;
        return;
    }

    private ReactionsContainerLayout createReactionsLayout() {
        ReactionsContainerLayout r0 = this.reactionsContainerLayout;
        if (r0 == null) goto L5;
        return r0;
    L5:
        BaseFragment r3 = LaunchActivity.getLastFragment();
        if (r3 != null) goto L8;
        return null;
    L8:
        ReactionsContainerLayout r1 = new ReactionsContainerLayout(1, r3, getContext(), this.currentAccount, this.resourcesProvider);
        this.reactionsContainerLayout = r1;
        r1.setDelegate(new AnonymousClass66(this));
        this.containerView.addView(this.reactionsContainerLayout, LayoutHelper.createFrame(-2, 52, 81));
        this.reactionsContainerLayout.setMessage(null, null, false);
        this.callMessageEnterUnderContainer.bringToFront();
        this.callMessageEnterContainer.bringToFront();
        return this.reactionsContainerLayout;
    }

    private void dismissAvatarPreview(boolean r4) {
        if (this.avatarPriviewTransitionInProgress == false) goto L5;
        return;
    L5:
        if (this.avatarsPreviewShowed == true) goto L8;
        return;
    L8:
        if (r4 == false) goto L11;
        this.avatarPriviewTransitionInProgress = true;
        runAvatarPreviewTransition(false, this.scrimView);
        return;
    L11:
        clearScrimView();
        this.containerView.removeView(this.scrimPopupLayout);
        this.scrimPopupLayout = null;
        this.avatarPreviewContainer.setVisibility(8);
        this.containerView.invalidate();
        this.avatarsPreviewShowed = false;
        this.layoutManager.setCanScrollVertically(true);
        this.listView.invalidate();
        this.blurredView.setVisibility(8);
        if (this.delayedGroupCallUpdated == false) goto L14;
        this.delayedGroupCallUpdated = false;
        applyCallParticipantUpdates(true);
    L14:
        checkContentOverlayed();
    }

    private void fillColors(int r8, int[] r9) {
        if (isRtmpStream() == false) goto L5;
        int r0 = Theme.key_voipgroup_disabledButton;
        r9[0] = Theme.getColor(r0);
        r9[1] = AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_rtmpButton), Theme.getColor(Theme.key_voipgroup_soundButtonActiveScrolled), this.colorProgress, 1.0f);
        r9[2] = AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_listViewBackgroundUnscrolled), Theme.getColor(r0), this.colorProgress, 1.0f);
    L14:
        if (isGradientState(r8) == false) goto L17;
        r9[3] = ColorUtils.blendARGB(Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient), Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient3), 0.5f);
        return;
    L17:
        if (r8 != 1) goto L20;
        r9[3] = ColorUtils.blendARGB(Theme.getColor(Theme.key_voipgroup_soundButtonActive2), ColorUtils.blendARGB(Theme.getColor(Theme.key_voipgroup_muteButton), Theme.getColor(Theme.key_voipgroup_muteButton3), 0.5f), 0.75f);
        return;
    L20:
        r9[3] = ColorUtils.blendARGB(Theme.getColor(Theme.key_voipgroup_unmuteButton2), Theme.getColor(Theme.key_voipgroup_unmuteButton), 0.5f);
        return;
    L5:
        if (r8 != 0) goto L7;
        r9[0] = Theme.getColor(Theme.key_voipgroup_unmuteButton2);
        r9[1] = AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_soundButtonActive), Theme.getColor(Theme.key_voipgroup_soundButtonActiveScrolled), this.colorProgress, 1.0f);
        r9[2] = Theme.getColor(Theme.key_voipgroup_soundButton);
        goto L14
    L7:
        if (r8 != 1) goto L10;
        r9[0] = Theme.getColor(Theme.key_voipgroup_muteButton2);
        r9[1] = AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_soundButtonActive2), Theme.getColor(Theme.key_voipgroup_soundButtonActive2Scrolled), this.colorProgress, 1.0f);
        r9[2] = Theme.getColor(Theme.key_voipgroup_soundButton2);
        goto L14
    L10:
        if (isGradientState(r8) == false) goto L12;
        r9[0] = Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient3);
        r9[1] = Theme.getColor(Theme.key_voipgroup_mutedByAdminMuteButton);
        r9[2] = Theme.getColor(Theme.key_voipgroup_mutedByAdminMuteButtonDisabled);
        goto L14
    L12:
        int r02 = Theme.key_voipgroup_disabledButton;
        r9[0] = Theme.getColor(r02);
        r9[1] = AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_disabledButtonActive), Theme.getColor(Theme.key_voipgroup_disabledButtonActiveScrolled), this.colorProgress, 1.0f);
        r9[2] = AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_listViewBackgroundUnscrolled), Theme.getColor(r02), this.colorProgress, 1.0f);
        goto L14
    }

    private GroupCallGridCell findGroupCallGridCell() {
        int r0 = 0;
    L4:
        if (r0 >= this.listView.getChildCount()) goto L14;
        View r1 = this.listView.getChildAt(r0);
        if (r1.isAttachedToWindow() == false) goto L13;
        if ((r1 instanceof GroupCallGridCell) == false) goto L13;
        if (this.listView.getChildAdapterPosition(r1) < 0) goto L13;
        return (GroupCallGridCell) r1;
    L13:
        r0 = r0 + 1;
        goto L4
    L14:
        return null;
    }

    private void getLink(boolean r7) {
        TLRPC.Chat r0 = this.accountInstance.getMessagesController().getChat(Long.valueOf(getChatId()));
        if (r0 == null) goto L21;
        if (ChatObject.isPublic(r0) == true) goto L21;
        TLRPC.ChatFull r02 = this.accountInstance.getMessagesController().getChatFull(getChatId());
        String r2 = ChatObject.getPublicUsername(this.currentChat);
        if (TextUtils.isEmpty(r2) == true) goto L9;
        String r22 = this.accountInstance.getMessagesController().linkPrefix + "/" + r2;
    L15:
        if (TextUtils.isEmpty(r22) == false) goto L18;
        TLRPC.TL_messages_exportChatInvite r1 = new TLRPC.TL_messages_exportChatInvite();
        r1.peer = MessagesController.getInputPeer(this.currentChat);
        this.accountInstance.getConnectionsManager().sendRequest(r1, new GroupCallActivity$$ExternalSyntheticLambda38(this, r02, r7));
        return;
    L18:
        openShareAlert(true, null, r22, r7);
        return;
    L9:
        if (r02 == null) goto L13;
        TLRPC.TL_chatInviteExported r23 = r02.exported_invite;
        if (r23 == null) goto L13;
        r22 = r23.link;
    L13:
        r22 = null;
    L21:
        if (this.call == null) goto L31;
        int r24 = 0;
    L25:
        if (r24 >= 2) goto L35;
        TL_phone.exportGroupCallInvite r3 = new TL_phone.exportGroupCallInvite();
        r3.call = this.call.getInputGroupCall();
        if (r24 != 1) goto L29;
        boolean r4 = true;
    L30:
        r3.can_self_unmute = r4;
        this.accountInstance.getConnectionsManager().sendRequest(r3, new GroupCallActivity$$ExternalSyntheticLambda39(this, r24, r7));
        r24 = r24 + 1;
        goto L25
    L29:
        r4 = false;
        goto L30
    L35:
        return;
    }

    private static String getRandomEmoji() {
        return EmojiData.data[(int) Math.floor(Math.random() * r0.length)][(int) Math.floor(Math.random() * r0.length)];
    }

    private void hideKeyboardOrEmojiView() {
        boolean r0 = this.callMessageEnterView.isPopupVisible();
        EditTextEmoji r1 = this.callMessageEnterView;
        if (r0 == false) goto L6;
        r1.hideEmojiView();
        return;
    L6:
        r1.closeKeyboard();
    }

    private void initCreatedGroupCall() {
        if (this.callInitied == true) goto L27;
        VoIPService r1 = VoIPService.getSharedInstance();
        if (r1 == null) goto L28;
        this.callInitied = true;
        this.oldParticipants.addAll(this.call.visibleParticipants);
        this.oldVideoParticipants.addAll(this.visibleVideoParticipants);
        this.oldInvited.addAll(this.call.invitedUsers);
        this.oldShadyJoin.addAll(this.call.shadyJoinParticipants);
        this.oldShadyLeft.addAll(this.call.shadyLeftParticipants);
        this.currentCallState = r1.getCallState();
        if (this.call != null) goto L11;
        ChatObject.Call r12 = r1.groupCall;
        this.call = r12;
        this.fullscreenAdapter.setGroupCall(r12);
        this.renderersContainer.setGroupCall(this.call);
        this.tabletGridAdapter.setGroupCall(this.call);
    L11:
        GroupCallMessagesListView r13 = this.groupCallMessagesListView;
        if (r13 == null) goto L14;
        r13.setGroupCall(this.accountInstance.getCurrentAccount(), this.call.getInputGroupCall(false));
    L14:
        this.actionBar.setTitleRightMargin(AndroidUtilities.dp(48.0f) * 2);
        this.call.saveActiveDates();
        VoIPService.getSharedInstance().registerStateListener(this);
        SimpleTextView r14 = this.scheduleTimeTextView;
        if (r14 != null) goto L17;
        return;
    L17:
        if (r14.getVisibility() != 0) goto L26;
        this.leaveButton.setData(R.drawable.calls_decline, -1, Theme.getColor(Theme.key_voipgroup_leaveButton), 0.3f, false, LocaleController.getString(R.string.VoipGroupLeave), false, true);
        updateSpeakerPhoneIcon(true);
        ActionBarMenuSubItem r15 = this.leaveItem;
        if (ChatObject.isChannelOrGiga(this.currentChat) == false) goto L22;
        int r4 = R.string.VoipChannelEndChat;
    L23:
        r15.setText(LocaleController.getString(r4));
        this.listView.setVisibility(0);
        this.pipItem.setVisibility(0);
        AnimatorSet r16 = new AnimatorSet();
        RecyclerListView r42 = this.listView;
        Property r6 = View.ALPHA;
        ObjectAnimator r43 = ObjectAnimator.ofFloat(r42, r6, new float[]{0.0f, 1.0f});
        ObjectAnimator r7 = ObjectAnimator.ofFloat(this.listView, View.TRANSLATION_Y, new float[]{AndroidUtilities.dp(200.0f), 0.0f});
        SimpleTextView r8 = this.scheduleTimeTextView;
        Property r10 = View.SCALE_X;
        ObjectAnimator r82 = ObjectAnimator.ofFloat(r8, r10, new float[]{0.0f});
        SimpleTextView r11 = this.scheduleTimeTextView;
        Property r122 = View.SCALE_Y;
        r16.playTogether(new Animator[]{r43, r7, r82, ObjectAnimator.ofFloat(r11, r122, new float[]{0.0f}), ObjectAnimator.ofFloat(this.scheduleTimeTextView, r6, new float[]{0.0f}), ObjectAnimator.ofFloat(this.scheduleStartInTextView, r10, new float[]{0.0f}), ObjectAnimator.ofFloat(this.scheduleStartInTextView, r122, new float[]{0.0f}), ObjectAnimator.ofFloat(this.scheduleStartInTextView, r6, new float[]{0.0f}), ObjectAnimator.ofFloat(this.scheduleStartAtTextView, r10, new float[]{0.0f}), ObjectAnimator.ofFloat(this.scheduleStartAtTextView, r122, new float[]{0.0f}), ObjectAnimator.ofFloat(this.scheduleStartAtTextView, r6, new float[]{0.0f}), ObjectAnimator.ofFloat(this.pipItem, r10, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(this.pipItem, r122, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(this.pipItem, r6, new float[]{0.0f, 1.0f})});
        r16.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        r16.addListener(new AnonymousClass54(this));
        r16.setDuration(300);
        r16.start();
        return;
    L22:
        r4 = R.string.VoipGroupEndChat;
        goto L23
    L26:
        return;
    L28:
        return;
    }

    private void invalidateLayoutFullscreen() {
        if (isRtmpStream() == true) goto L5;
        this.isFullscreen = false;
        return;
    L5:
        if (this.renderersContainer.isUiVisible() == false) goto L7;
    L14:
        boolean r0 = true;
    L15:
        Boolean r3 = this.wasNotInLayoutFullscreen;
        if (r3 != null) goto L18;
    L20:
        int r32 = this.containerView.getSystemUiVisibility();
        if (r0 == false) goto L23;
        int r33 = r32 & (-7);
        getWindow().clearFlags(1024);
        setHideSystemVerticalInsets(false);
    L24:
        this.containerView.setSystemUiVisibility(r33);
        this.wasNotInLayoutFullscreen = Boolean.valueOf(r0);
        this.isInFullscreen = !r0;
        this.containerView.requestApplyInsets();
        return;
    L23:
        setHideSystemVerticalInsets(true);
        r33 = r32 | 6;
        getWindow().addFlags(1024);
        goto L24
    L18:
        if (r0 != r3.booleanValue()) goto L20;
        return;
    L7:
        if (this.renderersContainer.inFullscreenMode == false) goto L14;
        if (isLandscapeMode != isRtmpLandscapeMode()) goto L11;
    L13:
        r0 = false;
        goto L15
    L11:
        if (AndroidUtilities.isTablet() == true) goto L13;
        goto L13
    }

    private void inviteUserToCall(long r11, boolean r13) {
        if (this.call == null) goto L13;
        TLRPC.User r7 = this.accountInstance.getMessagesController().getUser(Long.valueOf(r11));
        if (r7 == null) goto L14;
        AlertDialog[] r6 = {new AlertDialog(getContext(), 3)};
        TL_phone.inviteToGroupCall r9 = new TL_phone.inviteToGroupCall();
        r9.call = this.call.getInputGroupCall();
        TLRPC.TL_inputUser r0 = new TLRPC.TL_inputUser();
        r0.user_id = r7.id;
        r0.access_hash = r7.access_hash;
        r9.users.add(r0);
        int r10 = this.accountInstance.getConnectionsManager().sendRequest(r9, new GroupCallActivity$$ExternalSyntheticLambda81(this, r11, r6, r7, r13, r9));
        if (r10 == 0) goto L12;
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda82(this, r6, r10), 500);
        return;
    L12:
        return;
    L14:
        return;
    }

    private boolean isBulletinTop() {
        int r0 = AndroidUtilities.dp(74.0f);
        float r1 = this.scrollOffsetY - r0;
        if ((this.backgroundPaddingTop + r1) >= ActionBar.getCurrentActionBarHeight()) goto L5;
        float r4 = Math.min(1.0f, ((ActionBar.getCurrentActionBarHeight() - r1) - this.backgroundPaddingTop) / ((r0 - this.backgroundPaddingTop) - AndroidUtilities.dp(14.0f)));
    L7:
        if (r4 <= 0.5f) goto L10;
        return true;
    L10:
        return false;
    L5:
        r4 = 0.0f;
        goto L7
    }

    public static boolean isGradientState(int r1) {
        if (VoIPService.getSharedInstance() == null) goto L9;
        if (VoIPService.getSharedInstance().groupCall == null) goto L9;
        if (VoIPService.getSharedInstance().groupCall.call.rtmp_stream == false) goto L9;
        return true;
    L9:
        if (r1 != 2) goto L11;
        return true;
    L11:
        if (r1 != 4) goto L13;
        return true;
    L13:
        if (r1 != 5) goto L15;
        return true;
    L15:
        if (r1 != 6) goto L17;
        return true;
    L17:
        if (r1 == 7) goto L27;
        return false;
    L27:
        return true;
    }

    private boolean isStillConnecting() {
        int r2 = this.currentCallState;
        if (r2 != 1) goto L5;
    L13:
        return true;
    L5:
        if (r2 == 2) goto L13;
        if (r2 == 6) goto L13;
        if (r2 == 5) goto L13;
        return false;
    }

    private /* synthetic */ void lambda$didReceivedNotification$1() {
        if (isStillConnecting() == true) goto L5;
        return;
    L5:
        updateState(true, false);
    }

    private /* synthetic */ void lambda$didReceivedNotification$2(DialogInterface r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$fullscreenFor$52(ArrayList r4) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= this.attachedRenderers.size()) goto L10;
        if (this.attachedRenderers.get(r1).participant == null) goto L8;
        r4.remove(this.attachedRenderers.get(r1).participant);
    L8:
        r1 = r1 + 1;
    L10:
        if (r0 >= r4.size()) goto L20;
        ChatObject.VideoParticipant r3 = (ChatObject.VideoParticipant) r4.get(r0);
        if (r3.participant.self == false) goto L17;
        if (VoIPService.getSharedInstance() == null) goto L19;
        VoIPService.getSharedInstance().setLocalSink(null, r3.presentation);
    L19:
        r0 = r0 + 1;
        goto L10
    L17:
        if (VoIPService.getSharedInstance() == null) goto L19;
        VoIPService.getSharedInstance().removeRemoteSink(r3.participant, r3.presentation);
        goto L19
    }

    private /* synthetic */ void lambda$getLink$55(TLObject r2, TLRPC.ChatFull r3, boolean r4) {
        if ((r2 instanceof TLRPC.TL_chatInviteExported) == false) goto L10;
        TLRPC.TL_chatInviteExported r22 = (TLRPC.TL_chatInviteExported) r2;
        if (r3 == null) goto L8;
        r3.exported_invite = r22;
        return;
    L8:
        openShareAlert(true, null, r22.link, r4);
        return;
    }

    private /* synthetic */ void lambda$getLink$56(TLRPC.ChatFull r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda60(this, r3, r1, r2));
    }

    private /* synthetic */ void lambda$getLink$57(TLObject r4, int r5, boolean r6) {
        boolean r0 = r4 instanceof TL_phone.exportedGroupCallInvite;
        String[] r1 = this.invites;
        if (r0 == false) goto L5;
        r1[r5] = ((TL_phone.exportedGroupCallInvite) r4).link;
    L6:
        int r52 = 0;
    L8:
        if (r52 >= 2) goto L16;
        String r02 = this.invites[r52];
        if (r02 == null) goto L11;
        if (r02.length() != 0) goto L15;
        this.invites[r52] = null;
    L15:
        r52 = r52 + 1;
        goto L8
    L11:
        return;
    L16:
        if (r6 == false) goto L18;
    L22:
        String[] r53 = this.invites;
        if (r53[0] == null) goto L25;
    L30:
        String[] r54 = this.invites;
        openShareAlert(false, r54[0], r54[1], r6);
        return;
    L25:
        if (r53[1] != null) goto L30;
        if (ChatObject.isPublic(this.currentChat) == false) goto L30;
        openShareAlert(true, null, this.accountInstance.getMessagesController().linkPrefix + "/" + ChatObject.getPublicUsername(this.currentChat), r6);
        return;
    L18:
        if (canManageCall() == false) goto L22;
        if (this.call.call.join_muted == true) goto L22;
        this.invites[0] = null;
        goto L22
    L5:
        r1[r5] = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private /* synthetic */ void lambda$getLink$58(int r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda69(this, r3, r1, r2));
    }

    private /* synthetic */ void lambda$inviteUserToCall$61(long r9, AlertDialog[] r11, TLRPC.User r12) {
        ChatObject.Call r0 = this.call;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.delayedGroupCallUpdated == true) goto L17;
        r0.addInvitedUser(r9);
        applyCallParticipantUpdates(true);
        GroupVoipInviteAlert r92 = this.groupVoipInviteAlert;
        if (r92 == null) goto L14;
        r92.dismiss();
    L14:
        r11[0].dismiss();     // Catch: Throwable -> L13
    L11:
        r11[0] = null;
        getUndoView().showWithAction(0, 34, r12, this.currentChat, null, null);
        return;
    }

    private /* synthetic */ void lambda$inviteUserToCall$62(AlertDialog[] r3, boolean r4, TLRPC.TL_error r5, long r6, TL_phone.inviteToGroupCall r8) {
        r3[0].dismiss();     // Catch: Throwable -> L11
    L4:
        r3[0] = null;
        if (r4 == true) goto L7;
    L9:
        AlertsCreator.processError(this.currentAccount, r5, this.parentActivity.getActionBarLayout().getFragmentStack().get(this.parentActivity.getActionBarLayout().getFragmentStack().size() - 1), r8, new Object[0]);
        return;
    L7:
        if ("USER_NOT_PARTICIPANT".equals(r5.text) == false) goto L9;
        processSelectedOption(null, r6, 3);
    }

    private /* synthetic */ void lambda$inviteUserToCall$63(long r7, AlertDialog[] r9, TLRPC.User r10, boolean r11, TL_phone.inviteToGroupCall r12, TLObject r13, TLRPC.TL_error r14) {
        if (r13 == null) goto L5;
        this.accountInstance.getMessagesController().processUpdates((TLRPC.Updates) r13, false);
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda87(this, r7, r9, r10));
        return;
    L5:
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda88(this, r9, r11, r14, r7, r12));
    }

    private /* synthetic */ void lambda$inviteUserToCall$64(int r1, DialogInterface r2) {
        this.accountInstance.getConnectionsManager().cancelRequest(r1, true);
    }

    private /* synthetic */ void lambda$inviteUserToCall$65(AlertDialog[] r4, int r5) {
        AlertDialog r1 = r4[0];
        if (r1 != null) goto L5;
        return;
    L5:
        r1.setOnCancelListener(new GroupCallActivity$$ExternalSyntheticLambda85(this, r5));
        r4[0].show();
    }

    private /* synthetic */ void lambda$new$10(int[] r10, float[] r11, boolean[] r12) {
        int r0 = 0;
    L4:
        if (r0 >= r10.length) goto L33;
        TLRPC.GroupCallParticipant r1 = this.call.participantsBySources.get(r10[r0]);
        if (r1 == null) goto L32;
        if (this.renderersContainer.inFullscreenMode == false) goto L10;
        int r2 = 0;
    L26:
        if (r2 >= this.fullscreenUsersListView.getChildCount()) goto L31;
        GroupCallFullscreenAdapter.GroupCallUserCell r4 = (GroupCallFullscreenAdapter.GroupCallUserCell) this.fullscreenUsersListView.getChildAt(r2);
        if (MessageObject.getPeerId(r4.getParticipant().peer) != MessageObject.getPeerId(r1.peer)) goto L30;
        r4.setAmplitude(r11[r0] * 15.0f);
    L30:
        r2 = r2 + 1;
    L31:
        this.renderersContainer.setAmplitude(r1, r11[r0] * 15.0f);
        goto L32
    L10:
        if (this.delayedGroupCallUpdated == false) goto L12;
        ArrayList<TLRPC.GroupCallParticipant> r22 = this.oldParticipants;
    L13:
        int r23 = r22.indexOf(r1);
        if (r23 < 0) goto L31;
        RecyclerView.ViewHolder r24 = this.listView.findViewHolderForAdapterPosition(r23 + ListAdapter.m15330$$Nest$fgetusersStartRow(this.listAdapter));
        if (r24 == null) goto L31;
        View r42 = r24.itemView;
        if ((r42 instanceof GroupCallUserCell) == false) goto L31;
        ((GroupCallUserCell) r42).setAmplitude(r11[r0] * 15.0f);
        if (r24.itemView != this.scrimView) goto L31;
        if (this.contentFullyOverlayed == true) goto L31;
        this.containerView.invalidate();
        goto L31
    L12:
        r22 = this.call.visibleParticipants;
    L32:
        r0 = r0 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$new$11(TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.Updates) == false) goto L6;
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r1, false);
        return;
    }

    private /* synthetic */ void lambda$new$12(ChatObject.Call.InvitedUser r4, Long r5) {
        TL_phone.declineConferenceCallInvite r0 = new TL_phone.declineConferenceCallInvite();
        r0.msg_id = r4.msg_id;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new GroupCallActivity$$ExternalSyntheticLambda59(this));
        ChatObject.Call r02 = this.call;
        if (r02 == null) goto L6;
        r4.calling = false;
        r02.invitedUsersMessageIds.put(r5, r4);
        applyCallParticipantUpdates(true);
        return;
    }

    private /* synthetic */ void lambda$new$13(TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.Updates) == false) goto L6;
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r1, false);
        return;
    }

    private /* synthetic */ void lambda$new$14(ChatObject.Call.InvitedUser r13, Long r14) {
        TL_phone.declineConferenceCallInvite r0 = new TL_phone.declineConferenceCallInvite();
        r0.msg_id = r13.msg_id;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new GroupCallActivity$$ExternalSyntheticLambda58(this));
        ArrayList<Integer> r4 = new ArrayList();
        r4.add(Integer.valueOf(r13.msg_id));
        MessagesController.getInstance(this.currentAccount).deleteMessages(r4, null, null, 0, 0, true, 0);
        ChatObject.Call r132 = this.call;
        if (r132 == null) goto L6;
        r132.invitedUsers.remove(r14);
        this.call.invitedUsersMap.remove(r14);
        this.call.invitedUsersMessageIds.remove(r14);
        applyCallParticipantUpdates(true);
        return;
    }

    private /* synthetic */ void lambda$new$15(DialogInterface r1) {
        this.groupVoipInviteAlert = null;
    }

    private /* synthetic */ void lambda$new$17(TLRPC.Updates r5, long r6) {
        TLRPC.Update r0 = r5.update;
        int r2 = 0;
        if ((r0 instanceof TL_update.TL_updateNewMessage) == false) goto L10;
        TLRPC.Message r52 = ((TL_update.TL_updateNewMessage) r0).message;
        if (r52 != null) goto L7;
    L28:
        ChatObject.Call r53 = this.call;
        if (r53 == null) goto L39;
        if (r2 == 0) goto L40;
        r53.invitedUsersMessageIds.put(Long.valueOf(r6), ChatObject.Call.InvitedUser.make(r2));
        applyCallParticipantUpdates(true);
        return;
    L40:
        return;
    L39:
        return;
    L7:
        if ((r52.action instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L28;
        r2 = r52.id;
        goto L28
    L10:
        if ((r0 instanceof TL_update.TL_updateMessageID) == false) goto L13;
        r2 = ((TL_update.TL_updateMessageID) r0).id;
        goto L28
    L13:
        if (r5.updates == null) goto L28;
        int r02 = 0;
    L16:
        if (r02 >= r5.updates.size()) goto L28;
        TLRPC.Update r1 = r5.updates.get(r02);
        if ((r1 instanceof TL_update.TL_updateNewMessage) == false) goto L25;
        TLRPC.Message r12 = ((TL_update.TL_updateNewMessage) r1).message;
        if (r12 == null) goto L27;
        if ((r12.action instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L27;
        r2 = r12.id;
    L27:
        r02 = r02 + 1;
        goto L16
    L25:
        if ((r1 instanceof TL_update.TL_updateMessageID) == false) goto L27;
        r2 = ((TL_update.TL_updateMessageID) r1).id;
        goto L28
    }

    private /* synthetic */ void lambda$new$18(ArrayList r9, ArrayList r10, ArrayList r11, String r12) {
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (r1 != null) goto L5;
        return;
    L5:
        LimitReachedBottomSheet r0 = new LimitReachedBottomSheet(r1, r1.getContext(), 34, this.currentAccount, new DarkBlueThemeResourcesProvider());
        r0.setRestrictedUsers(null, r9, r10, r11, r12);
        r0.show();
    }

    private /* synthetic */ void lambda$new$21(HashSet r9, ChatObject.Call r10, String r11) {
        TL_account.getRequirementsToContact r0 = new TL_account.getRequirementsToContact();
        ArrayList r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList r5 = new ArrayList();
        Iterator r92 = r9.iterator();
    L4:
        if (r92.hasNext() == false) goto L9;
        Long r1 = (Long) r92.next();
        long r6 = r1.longValue();
        if (r10 == null) goto L8;
        r10.removeInvitedUser(r6);
    L8:
        r3.add(MessagesController.getInstance(this.currentAccount).getUser(r1));
        r0.id.add(MessagesController.getInstance(this.currentAccount).getInputUser(r6));
        goto L4
    L9:
        GroupCallActivity$$ExternalSyntheticLambda83 r12 = new GroupCallActivity$$ExternalSyntheticLambda83(this, r3, r4, r5, r11);
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L13;
        r12.run();
        return;
    L13:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new GroupCallActivity$$ExternalSyntheticLambda84(r3, r4, r12));
    }

    private /* synthetic */ void lambda$new$22(long r2, HashSet r4, AtomicInteger r5, int r6, ChatObject.Call r7, String r8, TLObject r9, TLRPC.TL_error r10) {
        if ((r9 instanceof TLRPC.Updates) == false) goto L5;
        TLRPC.Updates r92 = (TLRPC.Updates) r9;
        MessagesController.getInstance(this.currentAccount).processUpdates(r92, false);
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda74(this, r92, r2));
    L10:
        if (r5.incrementAndGet() == r6) goto L12;
        return;
    L12:
        if (r4.isEmpty() == true) goto L16;
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda75(this, r4, r7, r8));
        return;
    L16:
        return;
    L5:
        if (r10 == null) goto L10;
        if ("USER_PRIVACY_RESTRICTED".equalsIgnoreCase(r10.text) == false) goto L10;
        r4.add(Long.valueOf(r2));
        goto L10
    }

    private /* synthetic */ void lambda$new$23(ChatObject.Call r15, Boolean r16, HashSet r17) {
        ChatObject.Call r0 = this.call;
        if (r0 == null) goto L27;
        TLRPC.GroupCall r02 = r0.call;
        if (r02 == null) goto L30;
        String r9 = r02.invite_link;
        int r7 = r17.size();
        AtomicInteger r6 = new AtomicInteger(0);
        HashSet r5 = new HashSet();
        Iterator r10 = r17.iterator();
    L9:
        if (r10.hasNext() == false) goto L11;
        long r3 = ((Long) r10.next()).longValue();
        this.call.addInvitedUser(r3);
        TL_phone.inviteConferenceCallParticipant r11 = new TL_phone.inviteConferenceCallParticipant();
        TLRPC.TL_inputGroupCall r1 = new TLRPC.TL_inputGroupCall();
        r11.call = r1;
        TLRPC.GroupCall r2 = this.call.call;
        r1.id = r2.id;
        r1.access_hash = r2.access_hash;
        r11.user_id = MessagesController.getInstance(this.currentAccount).getInputUser(r3);
        r11.video = r16.booleanValue();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r11, new GroupCallActivity$$ExternalSyntheticLambda61(this, r3, r5, r6, r7, r15, r9));
        goto L9
    L11:
        applyCallParticipantUpdates(true);
        if (r16.booleanValue() == false) goto L28;
        VoIPService r12 = VoIPService.getSharedInstance();
        if (r12 != null) goto L17;
        return;
    L17:
        if (r12.getVideoState(false) != 2) goto L19;
        return;
    L19:
        if (r12.getVideoState(false) == 1) goto L32;
        r12.createCaptureDevice(false);
        if (r12.isFrontFaceCamera() == true) goto L24;
        r12.switchCamera();
    L24:
        r12.requestVideoCall(false);
        r12.setVideoState(false, 2);
        r12.setMicMute(false, false, true);
        r12.switchToSpeaker();
        updateState(true, true);
        return;
    L32:
        return;
    L28:
        return;
    L30:
        return;
    }

    private /* synthetic */ void lambda$new$24(Activity r10, ChatObject.Call r11, View r12, int r13, float r14, float r15) {
        if ((r12 instanceof GroupCallGridCell) == false) goto L7;
        fullscreenFor(((GroupCallGridCell) r12).getParticipant());
        return;
    L7:
        if ((r12 instanceof GroupCallUserCell) == false) goto L10;
        showMenuForCell((GroupCallUserCell) r12);
        return;
    L10:
        Collection<Long> r152 = null;
        Long r153 = null;
        r153 = null;
        r153 = null;
        r153 = null;
        boolean r0 = false;
        if ((r12 instanceof GroupCallInvitedCell) == false) goto L51;
        GroupCallInvitedCell r122 = (GroupCallInvitedCell) r12;
        if (r122.getUser() != null) goto L16;
        return;
    L16:
        if (isConference() == true) goto L18;
        this.parentActivity.switchToAccount(this.currentAccount, true);
        Bundle r102 = new Bundle();
        r102.putLong("user_id", r122.getUser().id);
        if (r122.hasAvatarSet() == false) goto L48;
        r102.putBoolean("expandPhoto", true);
    L48:
        this.parentActivity.presentFragment(new ProfileActivity(r102));
        dismiss();
        return;
    L18:
        if ((r13 - ListAdapter.m15326$$Nest$fgetshadyJoinStartRow(this.listAdapter)) < 0) goto L24;
        if ((r13 - ListAdapter.m15326$$Nest$fgetshadyJoinStartRow(this.listAdapter)) >= this.call.shadyJoinParticipants.size()) goto L24;
        r153 = this.call.shadyJoinParticipants.get(r13 - ListAdapter.m15326$$Nest$fgetshadyJoinStartRow(this.listAdapter));
    L22:
        r0 = true;
    L38:
        if (r0 == true) goto L86;
        ChatObject.Call.InvitedUser r103 = this.call.invitedUsersMessageIds.get(r153);
        if (r103 == null) goto L87;
        ItemOptions.makeOptions(this.container, this.resourcesProvider, r122).addIf(r103.isCalling(), R.drawable.msg_endcall, LocaleController.getString(R.string.GroupCallStopCallingInvite), new GroupCallActivity$$ExternalSyntheticLambda45(this, r103, r153)).add(R.drawable.msg_remove, LocaleController.getString(R.string.GroupCallDiscardInvite), new GroupCallActivity$$ExternalSyntheticLambda46(this, r103, r153)).setScrimViewBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.listViewBackgroundPaint.getColor())).setDimAlpha(96).show();
        return;
    L87:
        return;
    L86:
        return;
    L24:
        if ((r13 - ListAdapter.m15328$$Nest$fgetshadyLeftStartRow(this.listAdapter)) >= 0) goto L26;
    L28:
        int r132 = r13 - ListAdapter.m15323$$Nest$fgetinvitedStartRow(this.listAdapter);
        if (this.delayedGroupCallUpdated == false) goto L34;
        if (r132 < 0) goto L38;
        if (r132 >= this.oldInvited.size()) goto L38;
        r153 = this.oldInvited.get(r132);
        goto L38
    L34:
        if (r132 < 0) goto L38;
        if (r132 >= this.call.invitedUsers.size()) goto L38;
        r153 = this.call.invitedUsers.get(r132);
        goto L38
    L26:
        if ((r13 - ListAdapter.m15328$$Nest$fgetshadyLeftStartRow(this.listAdapter)) >= this.call.shadyLeftParticipants.size()) goto L28;
        r153 = this.call.shadyLeftParticipants.get(r13 - ListAdapter.m15328$$Nest$fgetshadyLeftStartRow(this.listAdapter));
        goto L22
    L51:
        if (r13 != ListAdapter.m15318$$Nest$fgetaddMemberRow(this.listAdapter)) goto L68;
        if (ChatObject.isChannel(this.currentChat) == false) goto L62;
        TLRPC.Chat r104 = this.currentChat;
        if (r104 == null) goto L62;
        if (r104.megagroup == true) goto L62;
        if (ChatObject.isPublic(r104) == false) goto L62;
        getLink(false);
        return;
    L62:
        TLRPC.ChatFull r5 = this.accountInstance.getMessagesController().getChatFull(getChatId());
        if (r5 == null) goto L88;
        this.enterEventSent = false;
        Context r2 = getContext();
        int r3 = this.accountInstance.getCurrentAccount();
        TLRPC.Chat r4 = this.currentChat;
        ChatObject.Call r105 = this.call;
        GroupVoipInviteAlert r1 = new GroupVoipInviteAlert(r2, r3, r4, r5, r105.participants, r105.invitedUsersMap);
        this.groupVoipInviteAlert = r1;
        r1.setOnDismissListener(new GroupCallActivity$$ExternalSyntheticLambda47(this));
        this.groupVoipInviteAlert.setDelegate(new AnonymousClass13(this));
        this.groupVoipInviteAlert.show();
        return;
    L88:
        return;
    L68:
        if (r13 != ListAdapter.m15319$$Nest$fgetconferenceAddPeopleRow(this.listAdapter)) goto L80;
        ChatObject.Call r123 = this.call;
        if (r123 != null) goto L72;
        return;
    L72:
        if (r123.call == null) goto L89;
        UserSelectorBottomSheet r02 = new UserSelectorBottomSheet(r10, this.currentAccount, 0, null, 4, true, new DarkBlueThemeResourcesProvider());
        ChatObject.Call r106 = this.call;
        if (r106 == null) goto L77;
        r152 = (Collection) r106.sortedParticipants.stream().map(new GroupCallActivity$$ExternalSyntheticLambda48()).collect(Collectors.toSet());
    L77:
        r02.exceptUsers(r152).setOnShareCallLinkListener(new GroupCallActivity$$ExternalSyntheticLambda49(this)).setOnUsersSelector(new GroupCallActivity$$ExternalSyntheticLambda50(this, r11)).show();
        return;
    L89:
        return;
    L80:
        if (r13 != ListAdapter.m15320$$Nest$fgetconferenceShareLinkRow(this.listAdapter)) goto L84;
        openShareConferenceLink();
        return;
    }

    private /* synthetic */ boolean lambda$new$25(View r2, int r3) {
        if (isRtmpStream() == false) goto L6;
        return false;
    L6:
        if ((r2 instanceof GroupCallGridCell) == false) goto L10;
        return showMenuForCell(r2);
    L10:
        if ((r2 instanceof GroupCallUserCell) == false) goto L13;
        updateItems();
        return ((GroupCallUserCell) r2).clickMuteButton();
    L13:
        return false;
    }

    private /* synthetic */ void lambda$new$26(View r1, int r2) {
        GroupCallGridCell r12 = (GroupCallGridCell) r1;
        if (r12.getParticipant() == null) goto L6;
        fullscreenFor(r12.getParticipant());
        return;
    }

    private /* synthetic */ void lambda$new$27(View r2) {
        ChatObject.Call r22 = this.call;
        if (r22 != null) goto L5;
    L14:
        getLink(false);
        return;
    L5:
        if (r22.isScheduled() == true) goto L14;
        if (isRtmpStream() == true) goto L14;
        if (VoIPService.getSharedInstance() != null) goto L12;
        return;
    L12:
        VoIPService.getSharedInstance().toggleSpeakerphoneOrShowRouteSheet(getContext(), false);
    }

    private /* synthetic */ void lambda$new$28(View r4) {
        this.renderersContainer.delayHideUi();
        VoIPService r42 = VoIPService.getSharedInstance();
        if (r42 == null) goto L18;
        int r0 = 0;
        if (r42.getVideoState(false) != 2) goto L24;
        r42.switchCamera();
        int r43 = this.flipIconCurrentEndFrame;
        RLottieDrawable r1 = this.flipIcon;
        if (r43 != 18) goto L9;
        this.flipIconCurrentEndFrame = 39;
        r1.setCustomEndFrame(39);
        this.flipIcon.start();
    L11:
        if (r0 >= this.attachedRenderers.size()) goto L25;
        GroupCallMiniTextureView r44 = this.attachedRenderers.get(r0);
        ChatObject.VideoParticipant r12 = r44.participant;
        if (r12.participant.self == false) goto L17;
        if (r12.presentation == true) goto L17;
        r44.startFlipAnimation();
    L17:
        r0 = r0 + 1;
        goto L11
    L25:
        return;
    L9:
        r1.setCurrentFrame(0, false);
        RLottieDrawable r45 = this.flipIcon;
        this.flipIconCurrentEndFrame = 18;
        r45.setCustomEndFrame(18);
        this.flipIcon.start();
        goto L11
    L24:
        return;
    }

    private /* synthetic */ void lambda$new$29(int r4) {
        setAudioOutputValue(r4);
        BulletinFactory.of(this.topBulletinContainer, new DarkBlueThemeResourcesProvider()).createSimpleBulletin(getContext().getResources().getDrawable(getAudioOutputToastIcon(r4)).mutate(), getAudioOutputToastText(r4)).show(isBulletinTop());
    }

    private /* synthetic */ void lambda$new$30(View r2) {
        int r22 = getNextAudioOutputValue();
        this.cacheAudioOutputValue = Integer.valueOf(r22);
        updateState(true, true);
        this.cacheAudioOutputValue = null;
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda44(this, r22));
    }

    private /* synthetic */ void lambda$new$31(Activity r1, View r2) {
        this.renderersContainer.delayHideUi();
        ChatObject.Call r22 = this.call;
        if (r22 != null) goto L5;
    L9:
        dismiss();
        return;
    L5:
        if (r22.isScheduled() == true) goto L9;
        updateItems();
        onLeaveClick(r1, new GroupCallActivity$$ExternalSyntheticLambda52(this), false);
    }

    private /* synthetic */ void lambda$new$32(int r1) {
        this.actionBar.getActionBarMenuOnItemClick().onItemClick(r1);
    }

    private /* synthetic */ void lambda$new$33(View r5) {
        ChatObject.Call r52 = this.call;
        if (r52 != null) goto L5;
        return;
    L5:
        if (this.renderersContainer.inFullscreenMode == true) goto L43;
        boolean r53 = r52.call.join_muted;
        ActionBarMenuSubItem r0 = this.everyoneItem;
        if (r53 == false) goto L10;
        int r54 = Theme.key_voipgroup_actionBarItems;
        r0.setColors(Theme.getColor(r54), Theme.getColor(r54));
        this.everyoneItem.setChecked(false);
        ActionBarMenuSubItem r55 = this.adminItem;
        int r02 = Theme.key_voipgroup_checkMenu;
        r55.setColors(Theme.getColor(r02), Theme.getColor(r02));
        this.adminItem.setChecked(true);
    L11:
        this.changingPermissions = false;
        this.otherItem.hideSubItem(1);
        this.otherItem.hideSubItem(2);
        if (VoIPService.getSharedInstance() != null) goto L14;
    L40:
        updateItems();
        this.otherItem.toggleSubMenu();
        return;
    L14:
        if (VoIPService.getSharedInstance().hasEarpiece() == false) goto L16;
    L17:
        int r56 = VoIPService.getSharedInstance().getCurrentAudioRoute();
        if (r56 != 2) goto L24;
        this.soundItem.setIcon(R.drawable.msg_voice_bluetooth);
        ActionBarMenuSubItem r57 = this.soundItem;
        if (VoIPService.getSharedInstance().currentBluetoothDeviceName == null) goto L22;
        String r03 = VoIPService.getSharedInstance().currentBluetoothDeviceName;
    L23:
        r57.setSubtext(r03);
        goto L40
    L22:
        r03 = LocaleController.getString(R.string.VoipAudioRoutingBluetooth);
        goto L23
    L24:
        if (r56 != 0) goto L35;
        ActionBarMenuSubItem r58 = this.soundItem;
        if (VoIPService.getSharedInstance().isHeadsetPlugged() == false) goto L28;
        int r04 = R.drawable.msg_voice_headphones;
    L29:
        r58.setIcon(r04);
        ActionBarMenuSubItem r59 = this.soundItem;
        if (VoIPService.getSharedInstance().isHeadsetPlugged() == false) goto L33;
        int r05 = R.string.VoipAudioRoutingHeadset;
    L34:
        r59.setSubtext(LocaleController.getString(r05));
        goto L40
    L33:
        r05 = R.string.VoipAudioRoutingPhone;
        goto L34
    L28:
        r04 = R.drawable.msg_voice_phone;
        goto L29
    L35:
        if (r56 != 1) goto L40;
        boolean r510 = VoipAudioManager.get().isSpeakerphoneOn();
        ActionBarMenuSubItem r06 = this.soundItem;
        if (r510 == false) goto L39;
        r06.setIcon(R.drawable.msg_voice_speaker);
        this.soundItem.setSubtext(LocaleController.getString(R.string.VoipAudioRoutingSpeaker));
        goto L40
    L39:
        r06.setIcon(R.drawable.msg_voice_phone);
        this.soundItem.setSubtext(LocaleController.getString(R.string.VoipAudioRoutingPhone));
        goto L40
    L16:
        if (VoIPService.getSharedInstance().isBluetoothHeadsetConnected() == false) goto L40;
    L10:
        int r511 = Theme.key_voipgroup_checkMenu;
        r0.setColors(Theme.getColor(r511), Theme.getColor(r511));
        this.everyoneItem.setChecked(true);
        ActionBarMenuSubItem r512 = this.adminItem;
        int r07 = Theme.key_voipgroup_actionBarItems;
        r512.setColors(Theme.getColor(r07), Theme.getColor(r07));
        this.adminItem.setChecked(false);
        goto L11
    }

    private /* synthetic */ void lambda$new$34(View r2) {
        boolean r22 = isRtmpStream();
        LaunchActivity r0 = this.parentActivity;
        if (r22 == false) goto L11;
        boolean r23 = PipUtils.checkAnyPipPermissions(r0);
        LaunchActivity r02 = this.parentActivity;
        if (r23 == false) goto L8;
        RTMPStreamPipOverlay.show(r02);
        dismiss();
        return;
    L8:
        AlertsCreator.createDrawOverlayPermissionDialog(r02, null, true).show();
        return;
    L11:
        if (AndroidUtilities.checkInlinePermissions(r0) == false) goto L14;
        GroupCallPip.clearForce();
        dismiss();
        return;
    L14:
        AlertsCreator.createDrawOverlayGroupCallPermissionDialog(getContext()).show();
    }

    private /* synthetic */ void lambda$new$35(View r3) {
        VoIPService r32 = VoIPService.getSharedInstance();
        if (r32 != null) goto L6;
        return;
    L6:
        if (r32.getVideoState(true) != 2) goto L9;
        r32.stopScreenCapture();
        return;
    L9:
        startScreenCapture();
    }

    private /* synthetic */ void lambda$new$36(View r1) {
        ChatObject.Call r12 = this.call;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.recording == false) goto L9;
        showRecordHint(this.actionBar.getTitleTextView());
        return;
    }

    private /* synthetic */ void lambda$new$37(View r2, int r3) {
        GroupCallFullscreenAdapter.GroupCallUserCell r22 = (GroupCallFullscreenAdapter.GroupCallUserCell) r2;
        if (r22.getVideoParticipant() != null) goto L6;
        fullscreenFor(new ChatObject.VideoParticipant(r22.getParticipant(), false, false));
        return;
    L6:
        fullscreenFor(r22.getVideoParticipant());
    }

    private /* synthetic */ boolean lambda$new$38(View r1, int r2) {
        if (showMenuForCell(r1) == true) goto L7;
    L5:
        return false;
    L7:
        this.listView.performHapticFeedback(0);     // Catch: Exception -> L6
        goto L5
    }

    private /* synthetic */ void lambda$new$39(View r1) {
        hideKeyboardOrEmojiView();
    }

    private /* synthetic */ void lambda$new$40(View r5) {
        Editable r52 = this.callMessageEnterView.getText();
        TLRPC.TL_textWithEntities r0 = new TLRPC.TL_textWithEntities();
        r0.text = r52.toString();
        r0.entities = MediaDataController.getInstance(this.currentAccount).getEntities(new CharSequence[]{r52}, true);
        sendGroupCallMessage(r0);
    }

    private /* synthetic */ void lambda$new$41(ValueAnimator r1) {
        this.switchToButtonProgress = ((Float) r1.getAnimatedValue()).floatValue();
        updateScheduleUI(true);
        this.buttonsContainer.invalidate();
        this.listView.invalidate();
    }

    private /* synthetic */ void lambda$new$42(TLRPC.Chat r6, TLRPC.InputPeer r7, TL_update.TL_updateGroupCall r8) {
        ChatObject.Call r0 = new ChatObject.Call();
        this.call = r0;
        r0.call = new TLRPC.TL_groupCall();
        ChatObject.Call r02 = this.call;
        TLRPC.GroupCall r1 = r02.call;
        r1.participants_count = 0;
        r1.version = 1;
        r1.can_start_video = true;
        r1.can_change_join_muted = true;
        if (r6 != null) goto L5;
        long r3 = 0;
    L6:
        r02.chatId = r3;
        r1.schedule_date = this.scheduleStartAt;
        r1.flags |= 128;
        r02.currentAccount = this.accountInstance;
        r02.setSelfPeer(r7);
        ChatObject.Call r62 = this.call;
        TLRPC.GroupCall r72 = r62.call;
        TLRPC.GroupCall r82 = r8.call;
        r72.access_hash = r82.access_hash;
        r72.id = r82.id;
        r62.createNoVideoParticipant();
        this.fullscreenAdapter.setGroupCall(this.call);
        this.renderersContainer.setGroupCall(this.call);
        this.tabletGridAdapter.setGroupCall(this.call);
        this.groupCallMessagesListView.setGroupCall(this.accountInstance.getCurrentAccount(), this.call.getInputGroupCall(false));
        MessagesController r63 = this.accountInstance.getMessagesController();
        ChatObject.Call r5 = this.call;
        r63.putGroupCall(r5.chatId, r5);
        return;
    L5:
        r3 = r6.id;
        goto L6
    }

    private /* synthetic */ void lambda$new$43(TLRPC.TL_error r4) {
        this.accountInstance.getNotificationCenter().postNotificationName(NotificationCenter.needShowAlert, new Object[]{6, r4.text});
        dismiss();
    }

    private /* synthetic */ void lambda$new$44(TLRPC.Chat r4, TLRPC.InputPeer r5, TLObject r6, TLRPC.TL_error r7) {
        if (r6 == null) goto L12;
        TLRPC.Updates r62 = (TLRPC.Updates) r6;
        int r0 = 0;
    L5:
        if (r0 >= r62.updates.size()) goto L10;
        TLRPC.Update r1 = r62.updates.get(r0);
        if ((r1 instanceof TL_update.TL_updateGroupCall) == true) goto L8;
        r0 = r0 + 1;
        goto L5
    L8:
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda67(this, r4, r5, (TL_update.TL_updateGroupCall) r1));
    L10:
        this.accountInstance.getMessagesController().processUpdates(r62, false);
        return;
    L12:
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda68(this, r7));
    }

    private /* synthetic */ void lambda$new$45(NumberPicker r11, NumberPicker r12, NumberPicker r13, TLRPC.Chat r14, AccountInstance r15, TLRPC.InputPeer r16, View r17) {
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.scheduleAnimator = r1;
        r1.setDuration(600);
        this.scheduleAnimator.addUpdateListener(new GroupCallActivity$$ExternalSyntheticLambda40(this));
        this.scheduleAnimator.addListener(new AnonymousClass43(this));
        this.scheduleAnimator.start();
        boolean r18 = ChatObject.isChannelOrGiga(this.currentChat);
        AudioPlayerAlert.ClippingTextViewSwitcher r2 = this.titleTextView;
        if (r18 == false) goto L5;
        r2.setText(LocaleController.getString(R.string.VoipChannelVoiceChat), true);
    L6:
        Calendar r19 = Calendar.getInstance();
        boolean r22 = AlertsCreator.checkScheduleDate(null, null, 604800, 3, r11, r12, r13);
        r19.setTimeInMillis(System.currentTimeMillis() + (r11.getValue() * DurationKt.MILLIS_IN_DAY));
        r19.set(11, r12.getValue());
        r19.set(12, r13.getValue());
        if (r22 == false) goto L9;
        r19.set(13, 0);
    L9:
        this.scheduleStartAt = (int) (r19.getTimeInMillis() / 1000);
        updateScheduleUI(false);
        TL_phone.createGroupCall r112 = new TL_phone.createGroupCall();
        r112.peer = MessagesController.getInputPeer(r14);
        r112.random_id = Utilities.random.nextInt();
        r112.schedule_date = this.scheduleStartAt;
        r112.flags |= 2;
        r15.getConnectionsManager().sendRequest(r112, new GroupCallActivity$$ExternalSyntheticLambda41(this, r14, r16), 2);
        return;
    L5:
        r2.setText(LocaleController.getString(R.string.VoipGroupVoiceChat), true);
        goto L6
    }

    private /* synthetic */ void lambda$new$47(NumberPicker r9, NumberPicker r10, NumberPicker r11, NumberPicker r12, int r13, int r14) {
        this.container.performHapticFeedback(3, 2);     // Catch: Exception -> L5
    L3:
        AlertsCreator.checkScheduleDate(this.scheduleButtonTextView, this.scheduleInfoTextView, 604800, 2, r9, r10, r11);
    }

    private /* synthetic */ void lambda$new$50(Activity r6, View r7) {
        LaunchActivity r72 = this.parentActivity;
        if (r72 == null) goto L9;
        if (r72.checkSelfPermission("android.permission.CAMERA") == 0) goto L9;
        this.parentActivity.requestPermissions(new String[]{"android.permission.CAMERA"}, 104);
        return;
    L9:
        if (VoIPService.getSharedInstance() == null) goto L32;
        boolean r0 = false;
        if (VoIPService.getSharedInstance().getVideoState(false) == 2) goto L27;
        this.undoView[0].hide(false, 1);
        if (this.previewDialog != null) goto L29;
        VoIPService r73 = VoIPService.getSharedInstance();
        if (r73 == null) goto L19;
        r73.createCaptureDevice(false);
    L19:
        if (VoIPService.getSharedInstance().getVideoState(true) == 2) goto L21;
        r0 = true;
    L21:
        AnonymousClass47 r3 = new AnonymousClass47(this, r6, true, r0);
        this.previewDialog = r3;
        r3.setBottomPadding(this.containerView.getPaddingBottom());
        this.container.addView(this.previewDialog);
        if (r73 != null) goto L24;
        return;
    L24:
        if (r73.isFrontFaceCamera() == true) goto L31;
        r73.switchCamera();
        return;
    L31:
        return;
    L29:
        return;
    L27:
        VoIPService.getSharedInstance().setVideoState(false, 0);
        updateState(true, false);
        updateSpeakerPhoneIcon(false);
        this.call.sortParticipants();
        applyCallParticipantUpdates(true);
        this.buttonsContainer.requestLayout();
        return;
    }

    private /* synthetic */ void lambda$new$51(View r3) {
        int r32 = calculateScrollTopOffset();
        if (r32 > 0) goto L5;
    L7:
        this.callMessageEnterView.openKeyboard();
        return;
    L5:
        if (r32 == Integer.MAX_VALUE) goto L7;
        this.listView.smoothScrollBy(0, r32);
        goto L7
    }

    private /* synthetic */ void lambda$new$9(DialogInterface r1) {
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (this.anyEnterEventSent == true) goto L5;
        return;
    L5:
        if ((r12 instanceof ChatActivity) == false) goto L9;
        ((ChatActivity) r12).onEditTextDialogClose(true, true);
        return;
    }

    private /* synthetic */ void lambda$onUserLeaveHint$80() {
        RTMPStreamPipOverlay.show(this.parentActivity);
    }

    private /* synthetic */ void lambda$openShareAlert$59(DialogInterface r1) {
        this.shareAlert = null;
    }

    private /* synthetic */ void lambda$openShareAlert$60() {
        ShareAlert r0 = this.shareAlert;
        if (r0 == null) goto L6;
        r0.show();
        return;
    }

    private /* synthetic */ void lambda$openShareConferenceLink$81(AlertDialog r8, TLObject r9, TL_phone.exportGroupCallInvite r10, TLRPC.TL_error r11) {
        r8.dismiss();
        if ((r9 instanceof TL_phone.exportedGroupCallInvite) == false) goto L15;
        Context r0 = getContext();
        int r1 = this.currentAccount;
        TLRPC.InputGroupCall r2 = r10.call;
        String r3 = ((TL_phone.exportedGroupCallInvite) r9).link;
        Theme.ResourcesProvider r4 = this.resourcesProvider;
        ChatObject.Call r7 = this.call;
        if (r7 == null) goto L12;
        TLRPC.GroupCall r72 = r7.call;
        if (r72 == null) goto L12;
        if (r72.creator == false) goto L12;
        boolean r73 = true;
    L13:
        CallLogActivity.showCallLinkSheet(r0, r1, r2, r3, r4, false, r73);
        return;
    L12:
        r73 = false;
        goto L13
    L15:
        if (r11 == null) goto L18;
        BulletinFactory.of(this.topBulletinContainer, new DarkBlueThemeResourcesProvider()).showForError(r11);
        return;
    }

    private /* synthetic */ void lambda$openShareConferenceLink$82(AlertDialog r7, TL_phone.exportGroupCallInvite r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda77(this, r7, r9, r8, r10));
    }

    private /* synthetic */ void lambda$processSelectedOption$69(TLObject r20, AlertDialog r21, int r22) {
        if (isConference() == false) goto L13;
        VoIPService r2 = VoIPService.getSharedInstance();
        if (r2 == null) goto L18;
        ConferenceCall r23 = r2.conference;
        if (r23 != null) goto L9;
        return;
    L9:
        if ((r20 instanceof TLRPC.User) == false) goto L20;
        TLRPC.User r8 = (TLRPC.User) r20;
        r23.kick(r8.id);
        this.call.addKickedUser(r8.id);
        getUndoView().showWithAction(0, 102, r8, null, null, null);
        return;
    L20:
        return;
    L18:
        return;
    L13:
        if ((r20 instanceof TLRPC.User) == false) goto L16;
        TLRPC.User r7 = (TLRPC.User) r20;
        this.accountInstance.getMessagesController().deleteParticipantFromChat(getChatId(), r7);
        getUndoView().showWithAction(0, 32, r7, null, null, null);
        return;
    L16:
        TLRPC.Chat r15 = (TLRPC.Chat) r20;
        this.accountInstance.getMessagesController().deleteParticipantFromChat(getChatId(), null, r15, false, false);
        getUndoView().showWithAction(0, 32, r15, null, null, null);
    }

    private /* synthetic */ void lambda$processSelectedOption$70(long r2) {
        inviteUserToCall(r2, false);
    }

    private /* synthetic */ void lambda$processSelectedOption$71(TLRPC.User r9, long r10, AlertDialog r12, int r13) {
        this.accountInstance.getMessagesController().addUserToChat(getChatId(), r9, 0, null, this.parentActivity.getActionBarLayout().getFragmentStack().get(this.parentActivity.getActionBarLayout().getFragmentStack().size() - 1), new GroupCallActivity$$ExternalSyntheticLambda76(this, r10));
    }

    private /* synthetic */ void lambda$processSelectedOption$72() {
        this.accountInstance.getMessagesController().deleteUserPhoto(null);
    }

    private /* synthetic */ void lambda$runAvatarPreviewTransition$76(float r3, float r4, float r5, int r6, ValueAnimator r7) {
        float r72 = ((Float) r7.getAnimatedValue()).floatValue();
        this.progressToAvatarPreview = r72;
        this.renderersContainer.progressToScrimView = r72;
        float r32 = (r3 * (1.0f - r72)) + (r72 * 1.0f);
        this.avatarPreviewContainer.setScaleX(r32);
        this.avatarPreviewContainer.setScaleY(r32);
        this.avatarPreviewContainer.setTranslationX(r4 * (1.0f - this.progressToAvatarPreview));
        this.avatarPreviewContainer.setTranslationY(r5 * (1.0f - this.progressToAvatarPreview));
        if (this.useBlur == true) goto L5;
        this.scrimPaint.setAlpha((int) (this.progressToAvatarPreview * 100.0f));
    L5:
        GroupCallMiniTextureView r33 = this.scrimRenderer;
        if (r33 == null) goto L8;
        r33.textureView.setRoundCorners(AndroidUtilities.dp(8.0f) * (1.0f - this.progressToAvatarPreview));
    L8:
        this.avatarPreviewContainer.invalidate();
        this.containerView.invalidate();
        ProfileGalleryView r34 = this.avatarsViewPager;
        float r42 = r6;
        float r2 = this.progressToAvatarPreview;
        r34.setRoundRadius((int) ((1.0f - r2) * r42), (int) (r42 * (1.0f - r2)));
    }

    private /* synthetic */ void lambda$setCommentsEnabled$78() {
        this.pendingCommentsEnabled = null;
        updateButtonsVisibility(true);
    }

    private /* synthetic */ void lambda$setCommentsEnabled$79(TLRPC.Updates r2, TLRPC.TL_error r3) {
        if (r2 == null) goto L4;
        this.accountInstance.getMessagesController().processUpdates(r2, false);
    L4:
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda78(this));
    }

    private /* synthetic */ void lambda$showMenuForCell$74(KeyEvent r3) {
        if (r3.getKeyCode() == 4) goto L5;
        return;
    L5:
        if (r3.getRepeatCount() != 0) goto L13;
        ActionBarPopupWindow r32 = this.scrimPopupWindow;
        if (r32 != null) goto L9;
        return;
    L9:
        if (r32.isShowing() == false) goto L15;
        this.scrimPopupWindow.dismiss();
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$showMenuForCell$75(int r3, ArrayList r4, TLRPC.GroupCallParticipant r5, View r6) {
        if (r3 >= r4.size()) goto L24;
        TLRPC.GroupCallParticipant r62 = this.call.participants.get(MessageObject.getPeerId(r5.peer));
        if (r62 == null) goto L9;
        r5 = r62;
    L9:
        processSelectedOption(r5, MessageObject.getPeerId(r5.peer), ((Integer) r4.get(r3)).intValue());
        ActionBarPopupWindow r52 = this.scrimPopupWindow;
        if (r52 == null) goto L14;
        r52.dismiss();
        return;
    L14:
        if (((Integer) r4.get(r3)).intValue() != 9) goto L16;
        return;
    L16:
        if (((Integer) r4.get(r3)).intValue() != 10) goto L18;
        return;
    L18:
        if (((Integer) r4.get(r3)).intValue() == 11) goto L23;
        dismissAvatarPreview(true);
        return;
    L23:
        return;
    }

    private /* synthetic */ void lambda$toggleAdminSpeak$77(TLRPC.Updates r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        this.accountInstance.getMessagesController().processUpdates(r1, false);
        return;
    }

    private /* synthetic */ void lambda$updateItems$3() {
        updateRecordCallText();
        AndroidUtilities.runOnUIThread(this.updateCallRecordRunnable, 1000);
    }

    private /* synthetic */ void lambda$updateLiveLabel$66(int r2, int r3, boolean r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        int r22 = AndroidUtilities.getOffsetColor(r2, r3, r52, 1.0f);
        this.liveLabelPaint.setColor(r22);
        this.liveLabelTextView.invalidate();
        if (r4 == true) goto L6;
        r52 = 1.0f - r52;
    L6:
        this.textureLightningViewAnimatingAlpha = r52;
        LightningView r23 = this.textureLightningView;
        if (r23 != null) goto L9;
        return;
    L9:
        if (this.needTextureLightning == false) goto L13;
        r23.invalidate();
        return;
    }

    private /* synthetic */ void lambda$updateTitle$53(View r1) {
        ChatObject.Call r12 = this.call;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.recording == false) goto L9;
        showRecordHint(this.actionBar.getTitleTextView());
        return;
    }

    private /* synthetic */ void lambda$updateTitle$54(View r1) {
        ChatObject.Call r12 = this.call;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.recording == false) goto L9;
        showRecordHint(this.actionBar.getTitleTextView());
        return;
    }

    private WindowInsetsCompat onApplyWindowInsets(View r9, WindowInsetsCompat r10) {
        Insets r92 = r10.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
        Insets r0 = r10.getInsets(WindowInsetsCompat.Type.ime());
        if (this.callMessageEnterView.isWaitingForKeyboardOpen() == false) goto L5;
    L8:
        int r1 = this.callMessageEnterView.getKeyboardHeight();
    L9:
        int r12 = Math.max(r0.bottom, r1);
        ViewGroup.LayoutParams r3 = this.blurredView.getLayoutParams();
        if ((r3 instanceof ViewGroup.MarginLayoutParams) == false) goto L13;
        ((ViewGroup.MarginLayoutParams) r3).bottomMargin = -r92.bottom;
    L13:
        if (this.callMessageEnterView.getEmojiView() == null) goto L15;
        this.callMessageEnterView.getEmojiView().setBottomInset(r92.bottom);
    L15:
        boolean r32 = this.isInFullscreen;
        ViewGroup r4 = this.containerView;
        if (r32 == false) goto L18;
        int r93 = this.backgroundPaddingLeft;
        r4.setPadding(r93, 0, r93, 0);
    L19:
        this.containerView.requestLayout();
        if (r12 == 0) goto L22;
    L28:
        if (r12 <= 0) goto L30;
        this.reactionsContainerLayout = createReactionsLayout();
    L30:
        this.callMessageEnterView.onSizeChanged(r0.bottom, false);
        this.windowInsetsStateHolder.setInsets(r10);
        return WindowInsetsCompat.CONSUMED;
    L22:
        if (this.callMessageEnterView.isWaitingForKeyboardOpen() == true) goto L28;
        if (this.callMessageEnterView.isPopupShowing() == true) goto L28;
        if (this.callMessageEnterView.isAnimatePopupClosing() == true) goto L28;
        this.callMessageEnterView.hideEmojiView();
        goto L28
    L18:
        int r33 = this.backgroundPaddingLeft;
        r4.setPadding(r92.left + r33, r92.top, r33 + r92.right, r92.bottom);
        goto L19
    L5:
        if (this.callMessageEnterView.isPopupShowing() == true) goto L8;
        r1 = 0;
        goto L9
    }

    public static void onLeaveClick(Context r1, Runnable r2, boolean r3) {
        onLeaveClick(r1, r2, r3, false);
    }

    public static void onLeaveClick(Context r12, Runnable r13, boolean r14, boolean r15) {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L66;
        TLRPC.Chat r1 = r0.getChat();
        ChatObject.Call r3 = r0.groupCall;
        long r5 = r0.getSelfId();
        if (r15 == true) goto L63;
        if (ChatObject.canManageCalls(r1) == false) goto L63;
        AlertDialog.Builder r152 = new AlertDialog.Builder(r12);
        if (r0.isConference() == false) goto L14;
        r152.setTitle(LocaleController.getString(R.string.VoipChannelLeaveConferenceAlertTitle));
        r152.setMessage(LocaleController.getString(R.string.VoipChannelLeaveConferenceAlertText));
    L17:
        r0.getAccount();
        CheckBoxCell[] r4 = new CheckBoxCell[1];
        LinearLayout r7 = new LinearLayout(r12);
        r7.setOrientation(1);
        if (r0.isConference() == false) goto L24;
        if (r3 == null) goto L44;
        TLRPC.GroupCall r9 = r3.call;
        if (r9 == null) goto L44;
        if (r9.creator == true) goto L24;
    L44:
        r152.setView(r7);
        r152.setDialogButtonColorKey(Theme.key_voipgroup_listeningText);
        r152.setPositiveButton(LocaleController.getString(R.string.VoipGroupLeave), new GroupCallActivity$$ExternalSyntheticLambda2(r3, r4, r5, r13));
        r152.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        if (r14 == false) goto L47;
        r152.setDimEnabled(false);
    L47:
        AlertDialog r122 = r152.create();
        if (r14 == true) goto L50;
    L54:
        if (r14 == true) goto L56;
        r122.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_dialogBackground));
    L56:
        r122.show();
        if (r14 == true) goto L65;
        TextView r132 = (TextView) r122.getButton(-1);
        if (r132 == null) goto L61;
        r132.setTextColor(Theme.getColor(Theme.key_voipgroup_leaveCallMenu));
    L61:
        r122.setTextColor(Theme.getColor(Theme.key_voipgroup_actionBarItems));
        return;
    L65:
        return;
    L50:
        if (Build.VERSION.SDK_INT < 26) goto L52;
        r122.getWindow().setType(2038);
    L53:
        r122.getWindow().clearFlags(2);
        goto L54
    L52:
        r122.getWindow().setType(CastStatusCodes.NOT_ALLOWED);
    L24:
        CheckBoxCell r92 = new CheckBoxCell(r12, 1);
        r4[0] = r92;
        r92.setBackground(Theme.getSelectorDrawable(false));
        if (r14 == false) goto L27;
        r4[0].setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
    L28:
        r4[0].setTag(0);
        if (r0.isConference() == false) goto L32;
        r4[0].setText(LocaleController.getString(R.string.VoipChannelLeaveConferenceAlertEndChat), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
    L35:
        CheckBoxCell r123 = r4[0];
        if (LocaleController.isRTL == false) goto L38;
        int r02 = AndroidUtilities.dp(16.0f);
    L40:
        if (LocaleController.isRTL == false) goto L42;
        int r16 = AndroidUtilities.dp(8.0f);
    L43:
        r123.setPadding(r02, 0, r16, 0);
        r7.addView(r4[0], LayoutHelper.createLinear(-1, -2));
        r4[0].setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda1(r4));
        goto L44
    L42:
        r16 = AndroidUtilities.dp(16.0f);
        goto L43
    L38:
        r02 = AndroidUtilities.dp(8.0f);
        goto L40
    L32:
        if (ChatObject.isChannelOrGiga(r1) == false) goto L34;
        r4[0].setText(LocaleController.getString(R.string.VoipChannelLeaveAlertEndChat), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        goto L35
    L34:
        r4[0].setText(LocaleController.getString(R.string.VoipGroupLeaveAlertEndChat), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        goto L35
    L27:
        r4[0].setTextColor(Theme.getColor(Theme.key_voipgroup_actionBarItems));
        ((CheckBoxSquare) r4[0].getCheckBoxView()).setColors(Theme.key_voipgroup_mutedIcon, Theme.key_voipgroup_listeningText, Theme.key_voipgroup_nameText);
        goto L28
    L14:
        if (ChatObject.isChannelOrGiga(r1) == false) goto L16;
        r152.setTitle(LocaleController.getString(R.string.VoipChannelLeaveAlertTitle));
        r152.setMessage(LocaleController.getString(R.string.VoipChannelLeaveAlertText));
        goto L17
    L16:
        r152.setTitle(LocaleController.getString(R.string.VoipGroupLeaveAlertTitle));
        r152.setMessage(LocaleController.getString(R.string.VoipGroupLeaveAlertText));
    L63:
        processOnLeave(r3, false, r5, r13);
        return;
    }

    private void onUserLeaveHint() {
        if (isRtmpStream() == true) goto L5;
        return;
    L5:
        if (AndroidUtilities.checkInlinePermissions(this.parentActivity) == true) goto L7;
        return;
    L7:
        if (RTMPStreamPipOverlay.isVisible() == true) goto L12;
        dismiss();
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda51(this), 100);
        return;
    }

    private void openShareAlert(boolean r14, String r15, String r16, boolean r17) {
        if (isRtmpStream() == false) goto L6;
        if (r15 == null) goto L6;
        String r0 = null;
    L7:
        if (r17 == false) goto L14;
        if (r15 == null) goto L10;
        r0 = r15;
    L10:
        AndroidUtilities.addToClipboard(r0);
        if (AndroidUtilities.shouldShowClipboardToast() == false) goto L39;
        getUndoView().showWithAction(0, 33, null, null, null, null);
        return;
    L39:
        return;
    L14:
        LaunchActivity r3 = this.parentActivity;
        if (r3 == null) goto L20;
        BaseFragment r32 = r3.getActionBarLayout().getFragmentStack().get(this.parentActivity.getActionBarLayout().getFragmentStack().size() - 1);
        if ((r32 instanceof ChatActivity) == false) goto L20;
        boolean r33 = ((ChatActivity) r32).needEnterText();
        this.anyEnterEventSent = true;
        this.enterEventSent = true;
    L19:
        boolean r12 = r33;
        if (r15 == null) goto L24;
        if (r0 != null) goto L24;
        String r8 = r15;
        String r6 = null;
    L25:
        if (r6 != null) goto L32;
        if (r14 == false) goto L32;
        if (ChatObject.isChannelOrGiga(this.currentChat) == false) goto L31;
        String r02 = LocaleController.formatString("VoipChannelInviteText", R.string.VoipChannelInviteText, new Object[]{r8});
    L30:
        String r5 = r02;
    L33:
        AnonymousClass55 r03 = new AnonymousClass55(this, getContext(), null, null, r5, r6, false, r8, r6, false, true);
        this.shareAlert = r03;
        r03.setDelegate(new AnonymousClass56(this));
        this.shareAlert.setOnDismissListener(new GroupCallActivity$$ExternalSyntheticLambda62(this));
        GroupCallActivity$$ExternalSyntheticLambda63 r04 = new GroupCallActivity$$ExternalSyntheticLambda63(this);
        if (r12 == false) goto L36;
        long r1 = 200;
    L37:
        AndroidUtilities.runOnUIThread(r04, r1);
        return;
    L36:
        r1 = 0;
        goto L37
    L31:
        r02 = LocaleController.formatString("VoipGroupInviteText", R.string.VoipGroupInviteText, new Object[]{r8});
    L32:
        r5 = r8;
    L24:
        r6 = r15;
        r8 = r0;
    L20:
        r33 = false;
    L6:
        r0 = r16;
        goto L7
    }

    private void prepareBlurBitmap() {
        if (this.blurredView != null) goto L5;
        return;
    L5:
        int r0 = (int) ((this.containerView.getMeasuredWidth() - (this.backgroundPaddingLeft * 2)) / 6.0f);
        int r1 = (int) ((this.containerView.getMeasuredHeight() - AndroidUtilities.statusBarHeight) / 6.0f);
        Bitmap r2 = Bitmap.createBitmap(r0, r1, Bitmap.Config.ARGB_8888);
        Canvas r3 = new Canvas(r2);
        r3.scale(0.16666667f, 0.16666667f);
        r3.save();
        r3.translate(0.0f, -AndroidUtilities.statusBarHeight);
        this.parentActivity.getActionBarLayout().getView().draw(r3);
        r3.drawColor(ColorUtils.setAlphaComponent(-16777216, 76));
        r3.restore();
        r3.save();
        r3.translate(this.containerView.getX(), -AndroidUtilities.statusBarHeight);
        this.drawingForBlur = true;
        this.containerView.draw(r3);
        this.drawingForBlur = false;
        Utilities.stackBlurBitmap(r2, Math.max(7, Math.max(r0, r1) / 180));
        this.blurredView.setBackground(new BitmapDrawable(r2));
        this.blurredView.setAlpha(0.0f);
        this.blurredView.setVisibility(0);
        this.blurredView.bringToFront();
    }

    private void pressRunnableImpl() {
        if (this.call != null) goto L5;
        return;
    L5:
        if (this.scheduled == true) goto L7;
        return;
    L7:
        if (VoIPService.getSharedInstance() == null) goto L17;
        this.muteButton.performHapticFeedback(3, 2);     // Catch: Exception -> L12
    L10:
        updateMuteButton(1, true);
        AndroidUtilities.runOnUIThread(this.unmuteRunnable, 80);
        this.scheduled = false;
        this.pressed = true;
        return;
    }

    private static void processOnLeave(ChatObject.Call r5, boolean r6, long r7, Runnable r9) {
        if (VoIPService.getSharedInstance() == null) goto L6;
        VoIPService.getSharedInstance().hangUp(r6 ? 1 : 0);
    L6:
        if (r5 == null) goto L21;
        TLRPC.GroupCallParticipant r0 = r5.participants.get(r7);
        if (r0 == null) goto L17;
        r5.participants.delete(r7);
        r5.sortedParticipants.remove(r0);
        r5.visibleParticipants.remove(r0);
        int r72 = 0;
    L11:
        if (r72 >= r5.visibleVideoParticipants.size()) goto L16;
        if (MessageObject.getPeerId(r5.visibleVideoParticipants.get(r72).participant.peer) != MessageObject.getPeerId(r0.peer)) goto L15;
        r5.visibleVideoParticipants.remove(r72);
        r72 = r72 - 1;
    L15:
        r72 = r72 + 1;
        goto L11
    L16:
        TLRPC.GroupCall r73 = r5.call;
        r73.participants_count--;
    L17:
        int r74 = 0;
    L19:
        if (r74 >= r5.sortedParticipants.size()) goto L21;
        TLRPC.GroupCallParticipant r8 = r5.sortedParticipants.get(r74);
        r8.lastActiveDate = r8.lastSpeakTime;
        r74 = r74 + 1;
    L21:
        if (r9 == null) goto L23;
        r9.run();
    L23:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didStartedCall, new Object[0]);
    }

    private void processSelectedOption(TLRPC.GroupCallParticipant r27, long r28, int r30) {
        VoIPService r5 = VoIPService.getSharedInstance();
        if (r5 == null) goto L146;
        AccountInstance r7 = this.accountInstance;
        if (r28 <= 0) goto L9;
        TLObject r72 = r7.getMessagesController().getUser(Long.valueOf(r28));
    L8:
        TLObject r15 = r72;
        boolean r13 = true;
        if (r30 == 0) goto L82;
        if (r30 == 2) goto L82;
        if (r30 == 3) goto L82;
        if (r30 != 6) goto L24;
        this.parentActivity.switchToAccount(this.currentAccount, true);
        Bundle r1 = new Bundle();
        if (r28 <= 0) goto L20;
        r1.putLong("user_id", r28);
    L21:
        this.parentActivity.presentFragment(new ChatActivity(r1));
        dismiss();
        return;
    L20:
        r1.putLong("chat_id", -r28);
        goto L21
    L24:
        if (r30 != 8) goto L38;
        this.parentActivity.switchToAccount(this.currentAccount, true);
        BaseFragment r12 = this.parentActivity.getActionBarLayout().getFragmentStack().get(this.parentActivity.getActionBarLayout().getFragmentStack().size() - 1);
        if ((r12 instanceof ChatActivity) == true) goto L28;
    L31:
        Bundle r14 = new Bundle();
        if (r28 <= 0) goto L34;
        r14.putLong("user_id", r28);
    L35:
        this.parentActivity.presentFragment(new ChatActivity(r14));
        dismiss();
        return;
    L34:
        r14.putLong("chat_id", -r28);
        goto L35
    L28:
        if (((ChatActivity) r12).getDialogId() != r28) goto L31;
        dismiss();
        return;
    L38:
        if (r30 != 7) goto L42;
        r5.editCallMember(r15, Boolean.TRUE, null, null, Boolean.FALSE, null);
        updateMuteButton(2, true);
        return;
    L42:
        if (r30 != 9) goto L59;
        ImageUpdater r16 = this.currentAvatarUpdater;
        if (r16 != null) goto L46;
    L48:
        TLRPC.User r17 = this.accountInstance.getUserConfig().getCurrentUser();
        ImageUpdater r4 = new ImageUpdater(true, 0, true);
        this.currentAvatarUpdater = r4;
        r4.setOpenWithFrontfaceCamera(true);
        this.currentAvatarUpdater.setForceDarkTheme(true);
        this.currentAvatarUpdater.setSearchAvailable(true, true);
        this.currentAvatarUpdater.setShowingFromDialog(true);
        this.currentAvatarUpdater.parentFragment = this.parentActivity.getActionBarLayout().getLastFragment();
        ImageUpdater r42 = this.currentAvatarUpdater;
        AvatarUpdaterDelegate r52 = new AvatarUpdaterDelegate(this, r28, null);
        this.avatarUpdaterDelegate = r52;
        r42.setDelegate(r52);
        ImageUpdater r2 = this.currentAvatarUpdater;
        TLRPC.UserProfilePhoto r18 = r17.photo;
        if (r18 != null) goto L51;
    L55:
        r13 = false;
    L56:
        r2.openMenu(r13, new GroupCallActivity$$ExternalSyntheticLambda72(this), new GroupCallActivity$$ExternalSyntheticLambda73(), 0);
        return;
    L51:
        if (r18.photo_big == null) goto L55;
        if ((r18 instanceof TLRPC.TL_userProfilePhotoEmpty) == true) goto L55;
    L46:
        if (r16.isUploadingImage() == false) goto L48;
        return;
    L59:
        if (r30 != 10) goto L63;
        AlertsCreator.createChangeBioAlert(r27.about, r28, getContext(), this.currentAccount);
        return;
    L63:
        if (r30 != 11) goto L66;
        AlertsCreator.createChangeNameAlert(r28, getContext(), this.currentAccount);
        return;
    L66:
        if (r30 != 5) goto L70;
        r5.editCallMember(r15, Boolean.TRUE, null, null, null, null);
        getUndoView().showWithAction(0, 35, r15);
        r5.setParticipantVolume(r27, 0);
        return;
    L70:
        if ((r27.flags & 128) != 0) goto L72;
    L74:
        r5.editCallMember(r15, Boolean.FALSE, null, null, null, null);
    L75:
        r5.setParticipantVolume(r27, ChatObject.getParticipantVolume(r27));
        UndoView r11 = getUndoView();
        if (r30 != 1) goto L79;
        int r0 = 31;
    L80:
        r11.showWithAction(0, r0, r15, null, null, null);
        return;
    L79:
        r0 = 36;
        goto L80
    L72:
        if (r27.volume != 0) goto L74;
        r27.volume = 10000;
        r27.volume_by_admin = false;
        r5.editCallMember(r15, Boolean.FALSE, null, 10000, null, null);
    L82:
        if (r30 == 0) goto L84;
        AlertDialog.Builder r19 = new AlertDialog.Builder(getContext());
        r19.setDialogButtonColorKey(Theme.key_voipgroup_listeningText);
        TextView r53 = new TextView(getContext());
        int r6 = Theme.key_voipgroup_actionBarItems;
        r53.setTextColor(Theme.getColor(r6));
        r53.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L91;
        int r9 = 5;
    L92:
        r53.setGravity(r9 | 48);
        FrameLayout r92 = new FrameLayout(getContext());
        r19.setView(r92);
        AvatarDrawable r10 = new AvatarDrawable();
        r10.setTextSize(AndroidUtilities.dp(12.0f));
        BackupImageView r142 = new BackupImageView(getContext());
        r142.setRoundRadius(AndroidUtilities.dp(20.0f));
        if (LocaleController.isRTL == false) goto L95;
        int r122 = 5;
    L96:
        r92.addView(r142, LayoutHelper.createFrame(40, 40.0f, r122 | 48, 22.0f, 5.0f, 22.0f, 0.0f));
        r10.setInfo(this.currentAccount, r15);
        boolean r123 = r15 instanceof TLRPC.User;
        if (r123 == false) goto L99;
        TLRPC.User r73 = (TLRPC.User) r15;
        r142.setForUserOrChat(r73, r10);
        String r74 = UserObject.getFirstName(r73);
    L100:
        TextView r102 = new TextView(getContext());
        r102.setTextColor(Theme.getColor(r6));
        r102.setTextSize(1, 20.0f);
        r102.setTypeface(AndroidUtilities.bold());
        r102.setLines(1);
        r102.setMaxLines(1);
        r102.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L103;
        int r62 = 5;
    L104:
        r102.setGravity(r62 | 16);
        r102.setEllipsize(TextUtils.TruncateAt.END);
        TLRPC.Chat r63 = this.currentChat;
        if (r63 == null) goto L107;
        String r64 = r63.title;
    L108:
        if (r30 == 2) goto L110;
        r102.setText(LocaleController.getString(R.string.VoipGroupAddMemberTitle));
        r53.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.VoipGroupAddMemberText, new Object[]{r74, r64})));
    L117:
        boolean r65 = LocaleController.isRTL;
        if (r65 == false) goto L120;
        int r75 = 5;
    L121:
        int r21 = r75 | 48;
        int r76 = 76;
        if (r65 == false) goto L124;
        int r132 = 21;
    L125:
        float r133 = r132;
        if (r65 == true) goto L129;
        r76 = 21;
    L129:
        r92.addView(r102, LayoutHelper.createFrame(-1, -2.0f, r21, r133, 11.0f, r76, 0.0f));
        if (LocaleController.isRTL == false) goto L132;
        int r112 = 5;
    L133:
        r92.addView(r53, LayoutHelper.createFrame(-2, -2.0f, r112 | 48, 24.0f, 57.0f, 24.0f, 9.0f));
        if (r30 != 2) goto L136;
        r19.setPositiveButton(LocaleController.getString(R.string.VoipGroupUserRemove), new GroupCallActivity$$ExternalSyntheticLambda70(this, r15));
    L138:
        r19.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r02 = r19.create();
        r02.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_dialogBackground));
        r02.show();
        if (r30 != 2) goto L144;
        TextView r03 = (TextView) r02.getButton(-1);
        if (r03 == null) goto L145;
        r03.setTextColor(Theme.getColor(Theme.key_voipgroup_leaveCallMenu));
        return;
    L145:
        return;
    L144:
        return;
    L136:
        if (r123 == false) goto L138;
        r19.setPositiveButton(LocaleController.getString(R.string.VoipGroupAdd), new GroupCallActivity$$ExternalSyntheticLambda71(this, (TLRPC.User) r15, r28));
        goto L138
    L132:
        r112 = 3;
        goto L133
    L124:
        r132 = 76;
        goto L125
    L120:
        r75 = 3;
        goto L121
    L110:
        if (isConference() == false) goto L112;
        r102.setText(LocaleController.getString(R.string.VoipConferenceRemoveMemberAlertTitle2));
        r53.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.VoipConferenceRemoveMemberAlertText2, new Object[]{r74})));
        goto L117
    L112:
        r102.setText(LocaleController.getString(R.string.VoipGroupRemoveMemberAlertTitle2));
        if (ChatObject.isChannelOrGiga(this.currentChat) == false) goto L115;
        r53.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.VoipChannelRemoveMemberAlertText2, new Object[]{r74, r64})));
        goto L117
    L115:
        r53.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.VoipGroupRemoveMemberAlertText2, new Object[]{r74, r64})));
        goto L117
    L107:
        r64 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L108
    L103:
        r62 = 3;
        goto L104
    L99:
        TLRPC.Chat r77 = (TLRPC.Chat) r15;
        r142.setForUserOrChat(r77, r10);
        r74 = r77.title;
        goto L100
    L95:
        r122 = 3;
        goto L96
    L91:
        r9 = 3;
        goto L92
    L84:
        if (VoIPService.getSharedInstance() == null) goto L148;
        VoIPService.getSharedInstance().editCallMember(r15, Boolean.TRUE, null, null, null, null);
        getUndoView().showWithAction(0, 30, r15, null, null, null);
        return;
    L148:
        return;
    L9:
        r72 = r7.getMessagesController().getChat(Long.valueOf(-r28));
        goto L8
    }

    private void runAvatarPreviewTransition(boolean r17, GroupCallUserCell r18) {
        float r2 = AndroidUtilities.dp(14.0f) + this.containerView.getPaddingLeft();
        float r0 = AndroidUtilities.dp(14.0f) + this.containerView.getPaddingTop();
        GroupCallMiniTextureView r7 = null;
        if (this.hasScrimAnchorView == false) goto L7;
        float r3 = ((r18.getAvatarImageView().getX() + r18.getX()) + this.listView.getX()) - r2;
        float r22 = ((r18.getAvatarImageView().getY() + r18.getY()) + this.listView.getY()) - r0;
        float r02 = r18.getAvatarImageView().getMeasuredHeight() / this.listView.getMeasuredWidth();
        int r10 = (int) (ExteraConfig.getAvatarCorners(r18.getAvatarImageView().getMeasuredHeight(), true) / r02);
    L5:
        float r15 = r22;
        float r23 = r02;
        float r03 = 1.0f;
        if (r17 == false) goto L39;
        this.avatarPreviewContainer.setScaleX(r23);
        this.avatarPreviewContainer.setScaleY(r23);
        this.avatarPreviewContainer.setTranslationX(r3);
        this.avatarPreviewContainer.setTranslationY(r15);
        this.avatarPagerIndicator.setAlpha(0.0f);
    L39:
        this.avatarsViewPager.setRoundRadius(r10, r10);
        if (this.useBlur == false) goto L48;
        if (r17 == false) goto L43;
        this.blurredView.setAlpha(0.0f);
    L43:
        ViewPropertyAnimator r11 = this.blurredView.animate();
        if (r17 == false) goto L46;
        float r14 = 1.0f;
    L47:
        r11.alpha(r14).setDuration(220).start();
        goto L48
    L46:
        r14 = 0.0f;
    L48:
        ViewPropertyAnimator r112 = this.avatarPagerIndicator.animate();
        if (r17 == false) goto L51;
        float r142 = 1.0f;
    L52:
        r112.alpha(r142).setDuration(220).start();
        if (r17 == true) goto L59;
        GroupCallMiniTextureView r113 = this.scrimRenderer;
        if (r113 == null) goto L59;
        r113.setShowingAsScrimView(false, true);
        ProfileGalleryView r114 = this.avatarsViewPager;
        if (r114.getRealPosition(r114.getCurrentItem()) == 0) goto L59;
        this.scrimRenderer.textureView.cancelAnimation();
        this.scrimGridView = null;
    L59:
        if (r17 == false) goto L61;
        float r115 = 0.0f;
    L62:
        if (r17 == true) goto L65;
        r03 = 0.0f;
    L65:
        ValueAnimator r9 = ValueAnimator.ofFloat(new float[]{r115, r03});
        r9.addUpdateListener(new GroupCallActivity$$ExternalSyntheticLambda4(this, r23, r3, r15, r10));
        this.popupAnimationIndex = this.accountInstance.getNotificationCenter().setAnimationInProgress(this.popupAnimationIndex, new int[]{NotificationCenter.dialogPhotosLoaded, NotificationCenter.fileLoaded, NotificationCenter.messagesDidLoad});
        if (this.scrimGridView == null) goto L69;
        r7 = this.scrimRenderer;
    L69:
        if (r7 == null) goto L71;
        r7.animateToScrimView = true;
    L71:
        r9.addListener(new AnonymousClass64(this, r7, r17));
        if (this.hasScrimAnchorView == false) goto L74;
    L76:
        r9.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r9.setDuration(220);
        r9.start();
    L77:
        checkContentOverlayed();
        return;
    L74:
        if (this.scrimRenderer == null) goto L76;
        r9.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r9.setDuration(220);
        this.scrimRenderer.textureView.setAnimateNextDuration(220);
        this.scrimRenderer.textureView.synchOrRunAnimation(r9);
        goto L77
    L61:
        r115 = 1.0f;
        goto L62
    L51:
        r142 = 0.0f;
        goto L52
    L7:
        if (this.scrimRenderer != null) goto L9;
        this.previewTextureTransitionEnabled = true;
    L16:
        GroupCallGridCell r32 = this.scrimGridView;
        if (r32 != null) goto L19;
    L23:
        GroupCallFullscreenAdapter.GroupCallUserCell r33 = this.scrimFullscreenView;
        if (r33 != null) goto L26;
    L31:
        r22 = 0.0f;
        r3 = 0.0f;
        r02 = 0.96f;
    L22:
        r10 = 0;
    L33:
        if (this.previewTextureTransitionEnabled == true) goto L5;
        GroupCallMiniTextureView r116 = this.scrimRenderer;
        if (r116 == null) goto L5;
        r116.invalidate();
        this.renderersContainer.invalidate();
        this.scrimRenderer.setShowingAsScrimView(false, false);
        this.scrimRenderer = null;
        goto L5
    L26:
        if (this.scrimRenderer != null) goto L29;
        r3 = (((r33.getAvatarImageView().getX() + this.scrimFullscreenView.getX()) + this.fullscreenUsersListView.getX()) + this.renderersContainer.getX()) - r2;
        r22 = (((this.scrimFullscreenView.getAvatarImageView().getY() + this.scrimFullscreenView.getY()) + this.fullscreenUsersListView.getY()) + this.renderersContainer.getY()) - r0;
        r02 = this.scrimFullscreenView.getAvatarImageView().getMeasuredHeight() / this.listView.getMeasuredWidth();
        r10 = (int) (ExteraConfig.getAvatarCorners(this.scrimFullscreenView.getAvatarImageView().getMeasuredHeight(), true) / r02);
        goto L33
    L29:
        if (this.previewTextureTransitionEnabled == false) goto L31;
        r3 = ((r33.getX() + this.fullscreenUsersListView.getX()) + this.renderersContainer.getX()) - r2;
        float r24 = this.scrimFullscreenView.getY() + this.fullscreenUsersListView.getY();
        float r102 = this.renderersContainer.getY();
    L21:
        r22 = (r24 + r102) - r0;
        r02 = 1.0f;
        goto L22
    L19:
        if (this.previewTextureTransitionEnabled == false) goto L23;
        r3 = (r32.getX() + this.listView.getX()) - r2;
        r24 = this.scrimGridView.getY() + this.listView.getY();
        r102 = AndroidUtilities.dp(2.0f);
        goto L21
    L9:
        if (r17 == true) goto L14;
        ProfileGalleryView r34 = this.avatarsViewPager;
        if (r34.getRealPosition(r34.getCurrentItem()) == 0) goto L14;
        boolean r35 = false;
    L15:
        this.previewTextureTransitionEnabled = r35;
    L14:
        r35 = true;
        goto L15
    }

    private void runUpdateTextureLightningRunnable() {
        AndroidUtilities.cancelRunOnUIThread(this.updateTextureLightningRunnable);
        if (this.needTextureLightning == true) goto L5;
        return;
    L5:
        if (this.textureLightningView != null) goto L7;
        return;
    L7:
        if (VoIPService.getSharedInstance() != null) goto L9;
        return;
    L9:
        if (isRtmpStream() == true) goto L11;
        return;
    L11:
        if (this.listView != null) goto L13;
        return;
    L13:
        if (LiteMode.isEnabled(512) == false) goto L22;
        AndroidUtilities.runOnUIThread(this.updateTextureLightningRunnable, 30);
        return;
    }

    private void sendGroupCallMessage(TLRPC.TL_textWithEntities r10) {
        this.callMessageEnterView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        ChatObject.Call r0 = this.call;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.call == null) goto L18;
        TLRPC.InputGroupCall r8 = r0.getInputGroupCall();
        if (r8 == null) goto L19;
        ChatObject.Call r02 = this.call;
        long r6 = r02.call.id;
        TLRPC.Peer r03 = r02.selfPeer;
        if (r03 == null) goto L14;
        long r04 = DialogObject.getPeerDialogId(r03);
    L13:
        long r3 = r04;
        GroupCallMessagesController.getInstance(this.currentAccount).sendCallMessage(r3, r10, r6, r8);
        return;
    L14:
        r04 = UserConfig.getInstance(this.currentAccount).clientUserId;
        goto L13
    L19:
        return;
    }

    private void setAmplitude(double r3) {
        float r32 = (float) (Math.min(8500.0d, r3) / 8500.0d);
        this.animateToAmplitude = r32;
        this.animateAmplitudeDiff = (r32 - this.amplitude) / ((BlobDrawable.AMPLITUDE_SPEED * 500.0f) + 100.0f);
    }

    private void setColorProgress(float r9) {
        this.colorProgress = r9;
        GroupCallRenderersContainer r0 = this.renderersContainer;
        if (r0 != null) goto L5;
        float r02 = 0.0f;
    L6:
        float r03 = Math.max(r9, r02);
        int r1 = Theme.key_voipgroup_actionBarUnscrolled;
        int r2 = Theme.getColor(r1);
        int r3 = Theme.key_voipgroup_actionBar;
        int r22 = AndroidUtilities.getOffsetColor(r2, Theme.getColor(r3), r9, 1.0f);
        this.backgroundColor = r22;
        this.actionBarBackground.setBackgroundColor(r22);
        this.otherItem.redrawPopup(-14472653);
        this.shadowDrawable.setColorFilter(new PorterDuffColorFilter(this.backgroundColor, PorterDuff.Mode.MULTIPLY));
        this.navBarColor = AndroidUtilities.getOffsetColor(Theme.getColor(r1), Theme.getColor(r3), r03, 1.0f);
        int r04 = AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_listViewBackgroundUnscrolled), Theme.getColor(Theme.key_voipgroup_listViewBackground), r9, 1.0f);
        CallEncryptionCellDrawable r12 = this.encryptionDrawable;
        if (r12 == null) goto L9;
        CallEncryptionCellDrawable.m15317$$Nest$msetPaintBackgroundColor(r12, r04);
    L9:
        this.listViewBackgroundPaint.setColor(r04);
        this.callMessageEnterContainerBgPaint.setColor(r04);
        this.callMessageEnterContainer.invalidate();
        this.listView.setGlowColor(r04);
        int r05 = this.muteButtonState;
        if (r05 != 3) goto L12;
    L13:
        this.muteButton.invalidate();
    L14:
        View r06 = this.buttonsBackgroundGradientView;
        int r13 = 0;
        if (r06 == null) goto L21;
        int[] r23 = this.gradientColors;
        r23[0] = this.backgroundColor;
        r23[1] = 0;
        if (Build.VERSION.SDK_INT <= 29) goto L19;
        this.buttonsBackgroundGradient.setColors(r23);
    L20:
        this.buttonsBackgroundGradientView2.setBackgroundColor(this.gradientColors[0]);
        goto L21
    L19:
        GradientDrawable r24 = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, this.gradientColors);
        this.buttonsBackgroundGradient = r24;
        r06.setBackground(r24);
    L21:
        int r07 = AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_leaveButton), Theme.getColor(Theme.key_voipgroup_leaveButtonScrolled), r9, 1.0f);
        this.leaveButton.setBackgroundColor(r07, r07);
        int r08 = AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_lastSeenTextUnscrolled), Theme.getColor(Theme.key_voipgroup_lastSeenText), r9, 1.0f);
        int r92 = AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_mutedIconUnscrolled), Theme.getColor(Theme.key_voipgroup_mutedIcon), r9, 1.0f);
        int r25 = Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider);
        int r32 = this.listView.getChildCount();
    L22:
        if (r13 >= r32) goto L44;
        View r4 = this.listView.getChildAt(r13);
        if ((r4 instanceof GroupCallTextCell) == false) goto L30;
        GroupCallTextCell r42 = (GroupCallTextCell) r4;
        if (isConference() == false) goto L28;
        r42.setColors(r25, r25);
    L43:
        r13 = r13 + 1;
        goto L22
    L28:
        r42.setColors(r92, r08);
        goto L43
    L30:
        if ((r4 instanceof GroupCallUserCell) == false) goto L37;
        GroupCallUserCell r43 = (GroupCallUserCell) r4;
        if (this.actionBar.getTag() == null) goto L34;
        int r5 = Theme.key_voipgroup_mutedIcon;
    L35:
        r43.setGrayIconColor(r5, r92);
        goto L43
    L34:
        r5 = Theme.key_voipgroup_mutedIconUnscrolled;
        goto L35
    L37:
        if ((r4 instanceof GroupCallInvitedCell) == false) goto L43;
        GroupCallInvitedCell r44 = (GroupCallInvitedCell) r4;
        if (this.actionBar.getTag() == null) goto L41;
        int r52 = Theme.key_voipgroup_mutedIcon;
    L42:
        r44.setGrayIconColor(r52, r92);
        goto L43
    L41:
        r52 = Theme.key_voipgroup_mutedIconUnscrolled;
        goto L42
    L44:
        this.containerView.invalidate();
        this.listView.invalidate();
        this.container.invalidate();
        return;
    L12:
        if (isGradientState(r05) == false) goto L14;
    L5:
        r02 = r0.progressToFullscreenMode;
        goto L6
    }

    private void setCommentsEnabled(boolean r4) {
        if (this.call != null) goto L5;
        return;
    L5:
        TL_phone.toggleGroupCallSettings r0 = new TL_phone.toggleGroupCallSettings();
        r0.call = this.call.getInputGroupCall();
        r0.messages_enabled = Boolean.valueOf(r4);
        this.pendingCommentsEnabled = Boolean.valueOf(r4);
        updateButtonsVisibility(true);
        ConnectionsManager r42 = this.accountInstance.getConnectionsManager();
        DispatchQueue r1 = Utilities.stageQueue;
        Objects.requireNonNull(r1);
        r42.sendRequestTyped(r0, new ChatThemeController$$ExternalSyntheticLambda6(r1), new GroupCallActivity$$ExternalSyntheticLambda64(this));
    }

    private void setMicAmplitude(float r9) {
        if (VoIPService.getSharedInstance() != null) goto L5;
    L6:
        r9 = 0.0f;
    L7:
        setAmplitude(4000.0f * r9);
        ChatObject.Call r0 = this.call;
        if (r0 != null) goto L10;
        return;
    L10:
        if (this.listView == null) goto L43;
        TLRPC.GroupCallParticipant r02 = r0.participants.get(MessageObject.getPeerId(this.selfPeer));
        if (r02 != null) goto L14;
        return;
    L14:
        if (this.renderersContainer.inFullscreenMode == false) goto L16;
        int r1 = 0;
    L32:
        if (r1 >= this.fullscreenUsersListView.getChildCount()) goto L37;
        GroupCallFullscreenAdapter.GroupCallUserCell r3 = (GroupCallFullscreenAdapter.GroupCallUserCell) this.fullscreenUsersListView.getChildAt(r1);
        if (MessageObject.getPeerId(r3.getParticipant().peer) != MessageObject.getPeerId(r02.peer)) goto L36;
        r3.setAmplitude(r9 * 15.0f);
    L36:
        r1 = r1 + 1;
    L37:
        this.renderersContainer.setAmplitude(r02, r9 * 15.0f);
        return;
    L16:
        if (this.delayedGroupCallUpdated == false) goto L18;
        ArrayList<TLRPC.GroupCallParticipant> r12 = this.oldParticipants;
    L19:
        int r13 = r12.indexOf(r02);
        if (r13 < 0) goto L37;
        RecyclerView.ViewHolder r14 = this.listView.findViewHolderForAdapterPosition(r13 + ListAdapter.m15330$$Nest$fgetusersStartRow(this.listAdapter));
        if (r14 == null) goto L37;
        View r32 = r14.itemView;
        if ((r32 instanceof GroupCallUserCell) == false) goto L37;
        ((GroupCallUserCell) r32).setAmplitude(r9 * 15.0f);
        if (r14.itemView != this.scrimView) goto L37;
        if (this.contentFullyOverlayed == true) goto L37;
        this.containerView.invalidate();
        goto L37
    L18:
        r12 = this.call.visibleParticipants;
        goto L19
    L43:
        return;
    L5:
        if (VoIPService.getSharedInstance().isMicMute() == false) goto L7;
        goto L6
    }

    private void setScrollOffsetY(float r10) {
        this.scrollOffsetY = r10;
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.listView.getLayoutParams();
        this.listView.setTopGlowOffset((int) (r10 - r0.topMargin));
        int r02 = AndroidUtilities.dp(74.0f);
        float r1 = r10 - r02;
        if ((this.backgroundPaddingTop + r1) >= (ActionBar.getCurrentActionBarHeight() * 2)) goto L15;
        float r03 = Math.min(1.0f, (((ActionBar.getCurrentActionBarHeight() * 2) - r1) - this.backgroundPaddingTop) / (((r02 - this.backgroundPaddingTop) - AndroidUtilities.dp(14.0f)) + ActionBar.getCurrentActionBarHeight()));
        if (AndroidUtilities.isTablet() == false) goto L7;
        float r12 = 17.0f;
    L8:
        int r13 = (int) (AndroidUtilities.dp(r12) * r03);
        LightningView r2 = this.textureLightningView;
        if (r2 == null) goto L12;
        r2.setShadowOffset((int) (AndroidUtilities.dp(8.0f) * r03));
    L12:
        if (Math.abs(Math.min(1.0f, r03) - this.colorProgress) <= 1.0E-4f) goto L14;
        setColorProgress(Math.min(1.0f, r03));
    L14:
        float r3 = 1.0f - ((0.1f * r03) * 1.2f);
        this.titleTextView.setScaleX(Math.max(0.9f, r3));
        this.titleTextView.setScaleY(Math.max(0.9f, r3));
        float r04 = 1.0f - (r03 * 1.2f);
        this.titleTextView.setAlpha(Math.max(0.0f, r04) * (1.0f - this.renderersContainer.progressToFullscreenMode));
        this.liveLabelTextView.setScaleX(Math.max(0.9f, r3));
        this.liveLabelTextView.setScaleY(Math.max(0.9f, r3));
        this.liveLabelTextView.setAlpha(Math.max(0.0f, r04) * (1.0f - this.renderersContainer.progressToFullscreenMode));
    L19:
        checkGroupCallUiPositions_MessagesList();
        float r14 = r13;
        this.menuItemsContainer.setTranslationY(Math.max(AndroidUtilities.dp(4.0f), (r10 - AndroidUtilities.dp(53.0f)) - r14));
        this.titleLayout.setTranslationY(Math.max(AndroidUtilities.dp(4.0f), (r10 - AndroidUtilities.dp(44.0f)) - r14));
        LightningView r05 = this.textureLightningView;
        if (r05 == null) goto L22;
        r05.setTranslationY(Math.max(AndroidUtilities.dp(4.0f), r10 - AndroidUtilities.dp(37.0f)));
    L22:
        LinearLayout r06 = this.scheduleTimerContainer;
        if (r06 == null) goto L25;
        r06.setTranslationY(Math.max(AndroidUtilities.dp(4.0f), (r10 - AndroidUtilities.dp(44.0f)) - r14));
    L25:
        this.containerView.invalidate();
        updateTopBulletinY();
        return;
    L7:
        r12 = 13.0f;
        goto L8
    L15:
        this.titleTextView.setScaleX(1.0f);
        this.titleTextView.setScaleY(1.0f);
        this.titleTextView.setAlpha(1.0f - this.renderersContainer.progressToFullscreenMode);
        this.liveLabelTextView.setScaleX(1.0f);
        this.liveLabelTextView.setScaleY(1.0f);
        this.liveLabelTextView.setAlpha(1.0f - this.renderersContainer.progressToFullscreenMode);
        if (this.colorProgress <= 1.0E-4f) goto L18;
        setColorProgress(0.0f);
    L18:
        r13 = 0;
        goto L19
    }

    private boolean showMenuForCell(View r34) {
        if (this.itemAnimator.isRunning() == true) goto L281;
        if (getContext() == null) goto L281;
        if (this.avatarPriviewTransitionInProgress == true) goto L272;
        if (this.avatarsPreviewShowed == true) goto L272;
        ActionBarPopupWindow r2 = this.scrimPopupWindow;
        if (r2 == null) goto L16;
        r2.dismiss();
        this.scrimPopupWindow = null;
        return false;
    L16:
        clearScrimView();
        if ((r34 instanceof GroupCallGridCell) == false) goto L31;
        GroupCallGridCell r0 = (GroupCallGridCell) r34;
        if (r0.getParticipant() != this.call.videoNotAvailableParticipant) goto L21;
        return false;
    L21:
        GroupCallUserCell r13 = new GroupCallUserCell(r0.getContext());
        r13.setData(this.accountInstance, r0.getParticipant().participant, this.call, MessageObject.getPeerId(this.selfPeer), null, false);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r22 = r13.rightDrawable;
        if (r22 == null) goto L24;
        r22.play();
    L24:
        this.hasScrimAnchorView = false;
        this.scrimGridView = r0;
        this.scrimRenderer = r0.getRenderer();
        if (isTabletMode == false) goto L27;
    L29:
        this.scrimViewAttached = false;
    L45:
        if (r13 != null) goto L48;
        return false;
    L48:
        if (isLandscapeMode == false) goto L50;
    L54:
        boolean r14 = false;
    L55:
        TLRPC.GroupCallParticipant r15 = r13.getParticipant();
        if (r15 != null) goto L58;
        return false;
    L58:
        Rect r02 = new Rect();
        ActionBarPopupWindow.ActionBarPopupWindowLayout r23 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(getContext());
        r23.setBackgroundDrawable(null);
        r23.setPadding(0, 0, 0, 0);
        r23.setOnTouchListener(new AnonymousClass60(this, r02));
        r23.setDispatchKeyEventListener(new GroupCallActivity$$ExternalSyntheticLambda42(this));
        LinearLayout r03 = new LinearLayout(getContext());
        if (r15.muted_by_you == true) goto L61;
        LinearLayout r3 = new LinearLayout(getContext());
    L62:
        this.currentOptionsLayout = r03;
        AnonymousClass61 r6 = new AnonymousClass61(this, getContext(), r03, r3);
        r6.setMinimumWidth(AndroidUtilities.dp(240.0f));
        r6.setOrientation(1);
        int r4 = AndroidUtilities.getOffsetColor(Theme.getColor(Theme.key_voipgroup_listViewBackgroundUnscrolled), Theme.getColor(Theme.key_voipgroup_listViewBackground), this.colorProgress, 1.0f);
        if (r3 != null) goto L65;
    L73:
        VolumeSlider r10 = null;
    L74:
        r03.setMinimumWidth(AndroidUtilities.dp(240.0f));
        r03.setOrientation(1);
        Drawable r32 = getContext().getResources().getDrawable(R.drawable.popup_fixed_alert).mutate();
        r32.setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.MULTIPLY));
        r03.setBackgroundDrawable(r32);
        if (r10 == null) goto L77;
        float r21 = -8.0f;
    L78:
        r6.addView(r03, LayoutHelper.createLinear(-2, -2, 0.0f, r21, 0.0f, 0.0f));
        AnonymousClass62 r04 = new AnonymousClass62(this, getContext(), null, 0, R.style.scrollbarShapeStyle, r6);
        r04.setClipToPadding(false);
        r23.addView(r04, LayoutHelper.createFrame(-2, -2.0f));
        long r42 = MessageObject.getPeerId(r15.peer);
        ArrayList r33 = new ArrayList(2);
        VolumeSlider r212 = r10;
        ArrayList r102 = new ArrayList(2);
        boolean r222 = r14;
        ArrayList r142 = new ArrayList(2);
        int r232 = 2;
        if ((r15.peer instanceof TLRPC.TL_peerUser) == false) goto L104;
        boolean r24 = ChatObject.isChannel(this.currentChat);
        AccountInstance r25 = this.accountInstance;
        if (r24 == false) goto L90;
        long r242 = r42;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r27 = r23;
        GroupCallUserCell r26 = r13;
        TLRPC.ChannelParticipant r28 = r25.getMessagesController().getAdminInChannel(r15.peer.user_id, getChatId());
        if (r28 != null) goto L85;
    L89:
        boolean r29 = false;
    L108:
        if (r26.isSelfUser() == false) goto L144;
        if (isConference() == false) goto L115;
        if (r26.isHandRaised() == false) goto L115;
        r33.add(LocaleController.getString(R.string.VoipGroupCancelRaiseHand));
        r102.add(Integer.valueOf(R.drawable.msg_handdown));
        r142.add(7);
    L115:
        if (r26.hasAvatarSet() == false) goto L118;
        int r210 = R.string.VoipAddPhoto;
    L119:
        r33.add(LocaleController.getString(r210));
        r102.add(Integer.valueOf(R.drawable.msg_addphoto));
        r142.add(9);
        String r43 = r15.about;
        if (r242 <= 0) goto L128;
        if (TextUtils.isEmpty(r43) == false) goto L125;
        int r44 = R.string.VoipAddBio;
    L126:
        r33.add(LocaleController.getString(r44));
    L134:
        if (TextUtils.isEmpty(r15.about) == false) goto L136;
        int r45 = R.drawable.msg_addbio;
    L137:
        r102.add(Integer.valueOf(r45));
        r142.add(10);
        if (r242 <= 0) goto L141;
        int r211 = R.string.VoipEditName;
    L142:
        r33.add(LocaleController.getString(r211));
        r102.add(Integer.valueOf(R.drawable.msg_edit));
        r142.add(11);
    L200:
        int r213 = r33.size();
        int r46 = 0;
    L201:
        if (r46 >= r213) goto L215;
        Context r7 = getContext();
        if (r46 != 0) goto L205;
        boolean r8 = true;
    L207:
        if (r46 != (r213 - 1)) goto L209;
        boolean r9 = true;
    L210:
        ActionBarMenuSubItem r5 = new ActionBarMenuSubItem(r7, r8, r9);
        if (((Integer) r142.get(r46)).intValue() == r232) goto L213;
        int r72 = Theme.key_voipgroup_actionBarItems;
        r5.setColors(Theme.getColor(r72), Theme.getColor(r72));
    L214:
        r5.setSelectorColor(Theme.getColor(Theme.key_voipgroup_listSelector));
        r5.setTextAndIcon((CharSequence) r33.get(r46), ((Integer) r102.get(r46)).intValue());
        r03.addView(r5);
        r5.setTag(r142.get(r46));
        r5.setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda43(this, r46, r142, r15));
        r46 = r46 + 1;
        r232 = 2;
        goto L201
    L213:
        int r73 = Theme.key_voipgroup_leaveCallMenu;
        r5.setColors(Theme.getColor(r73), Theme.getColor(r73));
        goto L214
    L209:
        r9 = false;
        goto L210
    L205:
        r8 = false;
        goto L207
    L215:
        r04.addView(r6, LayoutHelper.createScroll(-2, -2, 51));
        this.listView.stopScroll();
        this.layoutManager.setCanScrollVertically(false);
        GroupCallUserCell r05 = r26;
        this.scrimView = r05;
        r05.setAboutVisible(true);
        this.containerView.invalidate();
        this.listView.invalidate();
        AnimatorSet r35 = this.scrimAnimatorSet;
        if (r35 == null) goto L218;
        r35.cancel();
    L218:
        ActionBarPopupWindow.ActionBarPopupWindowLayout r47 = r27;
        this.scrimPopupLayout = r47;
        AccountInstance r52 = this.accountInstance;
        if (r242 <= 0) goto L223;
        TLRPC.User r36 = r52.getMessagesController().getUser(Long.valueOf(r242));
        ImageLocation r53 = ImageLocation.getForUserOrChat(this.accountInstance.getCurrentAccount(), r36, 0);
        ImageLocation r74 = ImageLocation.getForUserOrChat(this.accountInstance.getCurrentAccount(), r36, 1);
        long r92 = r242;
        if (MessagesController.getInstance(this.currentAccount).getUserFull(r92) != null) goto L224;
        MessagesController.getInstance(this.currentAccount).loadUserInfo(r36, false, 0);
    L224:
        GroupCallMiniTextureView r37 = this.scrimRenderer;
        if (r37 != null) goto L227;
    L229:
        boolean r38 = false;
    L230:
        if (r53 != null) goto L233;
        if (r38 == true) goto L233;
        boolean r143 = false;
    L248:
        if (r143 == false) goto L255;
        this.avatarsPreviewShowed = true;
        r47.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
        this.containerView.addView(this.scrimPopupLayout, LayoutHelper.createFrame(-2, -2.0f));
        this.useBlur = true;
        prepareBlurBitmap();
        this.avatarPriviewTransitionInProgress = true;
        this.avatarPreviewContainer.setVisibility(0);
        if (r212 == null) goto L252;
        r212.invalidate();
    L252:
        runAvatarPreviewTransition(true, r05);
        GroupCallFullscreenAdapter.GroupCallUserCell r06 = this.scrimFullscreenView;
        if (r06 == null) goto L279;
        r06.getAvatarImageView().setAlpha(0.0f);
        return true;
    L279:
        return true;
    L255:
        this.avatarsPreviewShowed = false;
        AnonymousClass63 r62 = new AnonymousClass63(this, r47, -2, -2);
        this.scrimPopupWindow = r62;
        r62.setPauseNotifications(true);
        this.scrimPopupWindow.setDismissAnimationDuration(Opcodes.REM_INT_LIT8);
        this.scrimPopupWindow.setOutsideTouchable(true);
        this.scrimPopupWindow.setClippingEnabled(true);
        this.scrimPopupWindow.setAnimationStyle(R.style.PopupContextAnimation);
        this.scrimPopupWindow.setFocusable(true);
        r47.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
        this.scrimPopupWindow.setInputMethodMode(2);
        this.scrimPopupWindow.setSoftInputMode(0);
        this.scrimPopupWindow.getContentView().setFocusableInTouchMode(true);
        GroupCallFullscreenAdapter.GroupCallUserCell r39 = this.scrimFullscreenView;
        if (r39 != null) goto L258;
        int r310 = (int) (((this.listView.getX() + this.listView.getMeasuredWidth()) + AndroidUtilities.dp(8.0f)) - r47.getMeasuredWidth());
        if (this.hasScrimAnchorView == false) goto L265;
        int r07 = (int) ((this.listView.getY() + r05.getY()) + r05.getClipHeight());
    L264:
        int r311 = r07;
        int r08 = r310;
    L270:
        this.scrimPopupWindow.showAtLocation(this.listView, 51, r08, r311);
        this.scrimAnimatorSet = new AnimatorSet();
        ArrayList r09 = new ArrayList();
        r09.add(ObjectAnimator.ofInt(this.scrimPaint, AnimationProperties.PAINT_ALPHA, new int[]{0, 100}));
        this.scrimAnimatorSet.playTogether(r09);
        this.scrimAnimatorSet.setDuration(150);
        this.scrimAnimatorSet.start();
        return true;
    L265:
        GroupCallGridCell r010 = this.scrimGridView;
        RecyclerListView r48 = this.listView;
        if (r010 == null) goto L269;
        float r011 = (r48.getY() + this.scrimGridView.getY()) + this.scrimGridView.getMeasuredHeight();
    L268:
        r07 = (int) r011;
        goto L264
    L269:
        r011 = r48.getY();
        goto L268
    L258:
        if (isLandscapeMode == false) goto L260;
        r08 = (((int) ((r39.getX() + this.fullscreenUsersListView.getX()) + this.renderersContainer.getX())) - r47.getMeasuredWidth()) + AndroidUtilities.dp(32.0f);
        r311 = ((int) ((this.scrimFullscreenView.getY() + this.fullscreenUsersListView.getY()) + this.renderersContainer.getY())) - AndroidUtilities.dp(6.0f);
        goto L270
    L260:
        r08 = ((int) ((r39.getX() + this.fullscreenUsersListView.getX()) + this.renderersContainer.getX())) - AndroidUtilities.dp(14.0f);
        r311 = (int) (((this.scrimFullscreenView.getY() + this.fullscreenUsersListView.getY()) + this.renderersContainer.getY()) - r47.getMeasuredHeight());
    L233:
        if (r222 == false) goto L246;
        this.avatarsViewPager.setParentAvatarImage(this.scrimView.getAvatarImageView());
        this.avatarsViewPager.setHasActiveVideo(r38);
        this.avatarsViewPager.setData(r92, true);
        this.avatarsViewPager.setCreateThumbFromParent(true);
        this.avatarsViewPager.initIfEmpty(null, r53, r74, true);
        GroupCallMiniTextureView r312 = this.scrimRenderer;
        if (r312 == null) goto L238;
        r312.setShowingAsScrimView(true, true);
    L238:
        if (MessageObject.getPeerId(this.selfPeer) != r92) goto L246;
        if (this.currentAvatarUpdater == null) goto L246;
        AvatarUpdaterDelegate r313 = this.avatarUpdaterDelegate;
        if (r313 == null) goto L246;
        if (AvatarUpdaterDelegate.m15302$$Nest$fgetavatar(r313) == null) goto L246;
        this.avatarsViewPager.addUploadingImage(AvatarUpdaterDelegate.m15303$$Nest$fgetuploadingImageLocation(this.avatarUpdaterDelegate), ImageLocation.getForLocal(AvatarUpdaterDelegate.m15302$$Nest$fgetavatar(this.avatarUpdaterDelegate)));
    L246:
        r143 = r222;
        goto L248
    L227:
        if (r37.isAttached() == false) goto L229;
        r38 = true;
        goto L230
    L223:
        r92 = r242;
        TLRPC.Chat r314 = r52.getMessagesController().getChat(Long.valueOf(-r92));
        r53 = ImageLocation.getForUserOrChat(this.accountInstance.getCurrentAccount(), r314, 0);
        r74 = ImageLocation.getForUserOrChat(this.accountInstance.getCurrentAccount(), r314, 1);
        goto L224
    L141:
        r211 = R.string.VoipEditTitle;
        goto L142
    L136:
        r45 = R.drawable.msg_info;
        goto L137
    L125:
        r44 = R.string.VoipEditBio;
        goto L126
    L128:
        if (TextUtils.isEmpty(r43) == false) goto L131;
        int r49 = R.string.VoipAddDescription;
    L132:
        r33.add(LocaleController.getString(r49));
        goto L134
    L131:
        r49 = R.string.VoipEditDescription;
        goto L132
    L118:
        r210 = R.string.VoipSetNewPhoto;
        goto L119
    L144:
        if (canManageCall() == false) goto L189;
        if (isConference() == true) goto L157;
        if (r29 == false) goto L157;
        if (r15.muted == false) goto L157;
        if (isConference() == true) goto L154;
    L167:
        TLRPC.Peer r410 = r15.peer;
        if (r410 == null) goto L174;
        long r411 = r410.channel_id;
        if (r411 == 0) goto L174;
        if (ChatObject.isMegagroup(this.currentAccount, r411) == true) goto L174;
        r33.add(LocaleController.getString(R.string.VoipGroupOpenChannel));
        r102.add(Integer.valueOf(R.drawable.msg_channel));
        r142.add(8);
    L176:
        if (isConference() == false) goto L184;
        ChatObject.Call r214 = this.call;
        if (r214 == null) goto L200;
        TLRPC.GroupCall r215 = r214.call;
        if (r215 == null) goto L200;
        if (r215.creator == false) goto L200;
    L187:
        r33.add(LocaleController.getString(R.string.VoipGroupUserRemove));
        r102.add(Integer.valueOf(R.drawable.msg_block2));
        r142.add(2);
        goto L200
    L184:
        if (r29 == true) goto L200;
        if (ChatObject.canBlockUsers(this.currentChat) == false) goto L200;
    L174:
        r33.add(LocaleController.getString(R.string.VoipGroupOpenProfile));
        r102.add(Integer.valueOf(R.drawable.msg_openprofile));
        r142.add(6);
        goto L176
    L154:
        if (r15.muted_by_you == false) goto L167;
        r33.add(LocaleController.getString(R.string.VoipGroupUnmuteForMe));
        r102.add(Integer.valueOf(R.drawable.msg_voice_unmuted));
        r142.add(4);
    L157:
        if (r15.muted == true) goto L159;
    L166:
        r33.add(LocaleController.getString(R.string.VoipGroupMute));
        r102.add(Integer.valueOf(R.drawable.msg_voice_muted));
        r142.add(0);
        goto L167
    L159:
        if (r15.can_self_unmute == true) goto L166;
        r33.add(LocaleController.getString(R.string.VoipGroupAllowToSpeak));
        if (r15.raise_hand_rating == 0) goto L164;
        r102.add(Integer.valueOf(R.drawable.msg_allowspeak));
    L165:
        r142.add(1);
        goto L167
    L164:
        r102.add(Integer.valueOf(R.drawable.msg_voice_unmuted));
        goto L165
    L189:
        if (r15.muted_by_you == false) goto L191;
        r33.add(LocaleController.getString(R.string.VoipGroupUnmuteForMe));
        r102.add(Integer.valueOf(R.drawable.msg_voice_unmuted));
        r142.add(4);
    L192:
        TLRPC.Peer r216 = r15.peer;
        if (r216 == null) goto L199;
        long r412 = r216.channel_id;
        if (r412 == 0) goto L199;
        if (ChatObject.isMegagroup(this.currentAccount, r412) == true) goto L199;
        r33.add(LocaleController.getString(R.string.VoipGroupOpenChannel));
        r102.add(Integer.valueOf(R.drawable.msg_msgbubble3));
        r142.add(8);
    L199:
        r33.add(LocaleController.getString(R.string.VoipGroupOpenChat));
        r102.add(Integer.valueOf(R.drawable.msg_msgbubble3));
        r142.add(6);
        goto L200
    L191:
        r33.add(LocaleController.getString(R.string.VoipGroupMuteForMe));
        r102.add(Integer.valueOf(R.drawable.msg_voice_muted));
        r142.add(5);
        goto L192
    L85:
        if ((r28 instanceof TLRPC.TL_channelParticipantCreator) == false) goto L87;
    L88:
        r29 = true;
        goto L108
    L87:
        if (r28.admin_rights.manage_call == false) goto L89;
    L90:
        r242 = r42;
        r27 = r23;
        r26 = r13;
        TLRPC.ChatFull r217 = r25.getMessagesController().getChatFull(getChatId());
        if (r217 == null) goto L89;
        TLRPC.ChatParticipants r413 = r217.participants;
        if (r413 == null) goto L89;
        int r414 = r413.participants.size();
        int r54 = 0;
    L95:
        if (r54 >= r414) goto L89;
        TLRPC.ChatParticipant r12 = r217.participants.participants.get(r54);
        int r132 = r414;
        int r282 = r54;
        TLRPC.ChatFull r292 = r217;
        if (r12.user_id == r15.peer.user_id) goto L99;
        r54 = r282 + 1;
        r414 = r132;
        r217 = r292;
        goto L95
    L99:
        if ((r12 instanceof TLRPC.TL_chatParticipantAdmin) == true) goto L88;
        if ((r12 instanceof TLRPC.TL_chatParticipantCreator) == false) goto L89;
    L104:
        r242 = r42;
        r27 = r23;
        r26 = r13;
        if (r242 != (-getChatId())) goto L89;
    L77:
        r21 = 0.0f;
        goto L78
    L65:
        if (r13.isSelfUser() == true) goto L73;
        if (r15.muted_by_you == true) goto L73;
        if (r15.muted == true) goto L71;
    L72:
        Drawable r55 = getContext().getResources().getDrawable(R.drawable.popup_fixed_alert).mutate();
        r55.setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.MULTIPLY));
        r3.setBackgroundDrawable(r55);
        r6.addView(r3, LayoutHelper.createLinear(-2, -2, 0.0f, 0.0f, 0.0f, 0.0f));
        VolumeSlider r56 = new VolumeSlider(this, getContext(), r15);
        r3.addView(r56, -1, 48);
        r10 = r56;
        goto L74
    L71:
        if (r15.can_self_unmute == false) goto L73;
    L61:
        r3 = null;
        goto L62
    L50:
        if (isTabletMode == true) goto L54;
        if (AndroidUtilities.isInMultiwindow == true) goto L54;
        r14 = true;
        goto L55
    L27:
        if (isLandscapeMode == true) goto L29;
        this.scrimViewAttached = true;
        this.containerView.addView(r13, LayoutHelper.createFrame(-1, -2.0f, 0, 14.0f, 0.0f, 14.0f, 0.0f));
        goto L45
    L31:
        if ((r34 instanceof GroupCallFullscreenAdapter.GroupCallUserCell) == false) goto L44;
        GroupCallFullscreenAdapter.GroupCallUserCell r012 = (GroupCallFullscreenAdapter.GroupCallUserCell) r34;
        if (r012.getParticipant() != this.call.videoNotAvailableParticipant.participant) goto L35;
        return false;
    L35:
        r13 = new GroupCallUserCell(r012.getContext());
        r13.setData(this.accountInstance, r012.getParticipant(), this.call, MessageObject.getPeerId(this.selfPeer), null, false);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r218 = r13.rightDrawable;
        if (r218 == null) goto L38;
        r218.play();
    L38:
        this.hasScrimAnchorView = false;
        this.scrimFullscreenView = r012;
        GroupCallMiniTextureView r013 = r012.getRenderer();
        this.scrimRenderer = r013;
        if (r013 != null) goto L41;
    L43:
        this.containerView.addView(r13, LayoutHelper.createFrame(-1, -2.0f, 0, 14.0f, 0.0f, 14.0f, 0.0f));
        this.scrimViewAttached = true;
        goto L45
    L41:
        if (r013.showingInFullscreen == false) goto L43;
        this.scrimRenderer = null;
        goto L43
    L44:
        r13 = (GroupCallUserCell) r34;
        this.hasScrimAnchorView = true;
        this.scrimViewAttached = true;
    L272:
        dismissAvatarPreview(true);
        return false;
    L281:
        return false;
    }

    private void showRecordHint(View r11) {
        if (this.recordHintView != null) goto L9;
        HintView r0 = new HintView(getContext(), 8, true);
        this.recordHintView = r0;
        r0.setAlpha(0.0f);
        this.recordHintView.setVisibility(4);
        this.recordHintView.setShowingDuration(3000);
        this.containerView.addView(this.recordHintView, LayoutHelper.createFrame(-2, -2.0f, 51, 19.0f, 0.0f, 19.0f, 0.0f));
        boolean r02 = ChatObject.isChannelOrGiga(this.currentChat);
        HintView r2 = this.recordHintView;
        if (r02 == false) goto L7;
        r2.setText(LocaleController.getString(R.string.VoipChannelRecording));
    L8:
        this.recordHintView.setBackgroundColor(-366530760, -1);
        goto L9
    L7:
        r2.setText(LocaleController.getString(R.string.VoipGroupRecording));
    L9:
        this.recordHintView.setExtraTranslationY(-AndroidUtilities.statusBarHeight);
        this.recordHintView.showForView(r11, true);
    }

    private void showReminderHint() {
        SharedPreferences r0 = MessagesController.getGlobalMainSettings();
        if (r0.getBoolean("reminderhint", false) == false) goto L5;
        return;
    L5:
        r0.edit().putBoolean("reminderhint", true).apply();
        if (this.reminderHintView != null) goto L8;
        HintView r02 = new HintView(getContext(), 8);
        this.reminderHintView = r02;
        r02.setAlpha(0.0f);
        this.reminderHintView.setVisibility(4);
        this.reminderHintView.setShowingDuration(4000);
        this.containerView.addView(this.reminderHintView, LayoutHelper.createFrame(-2, -2.0f, 51, 19.0f, 0.0f, 19.0f, 0.0f));
        this.reminderHintView.setText(LocaleController.getString(R.string.VoipChatReminderHint));
        this.reminderHintView.setBackgroundColor(-366530760, -1);
    L8:
        this.reminderHintView.setExtraTranslationY(-AndroidUtilities.statusBarHeight);
        this.reminderHintView.showForView(this.muteButton, true);
    }

    private void startScreenCapture() {
        LaunchActivity r0 = this.parentActivity;
        if (r0 != null) goto L5;
        return;
    L5:
        MediaProjectionManager r02 = (MediaProjectionManager) r0.getSystemService("media_projection");
        this.parentActivity.startActivityForResult(r02.createScreenCaptureIntent(), 520);
    }

    private void toggleAdminSpeak() {
        ChatObject.Call r0 = this.call;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.call == null) goto L10;
        TL_phone.toggleGroupCallSettings r02 = new TL_phone.toggleGroupCallSettings();
        r02.call = this.call.getInputGroupCall();
        r02.join_muted = Boolean.valueOf(this.call.call.join_muted);
        ConnectionsManager r1 = this.accountInstance.getConnectionsManager();
        DispatchQueue r2 = Utilities.stageQueue;
        Objects.requireNonNull(r2);
        r1.sendRequestTyped(r02, new ChatThemeController$$ExternalSyntheticLambda6(r2), new GroupCallActivity$$ExternalSyntheticLambda66(this));
        return;
    }

    private void updateButtonsVisibility(boolean r9) {
        ChatObject.Call r0 = this.call;
        boolean r1 = true;
        boolean r2 = false;
        if (r0 != null) goto L5;
    L49:
        GroupCallActivityButtonsLayout r02 = this.buttonsContainer;
        VoIPToggleButton r3 = this.muteButton;
        if (this.switchToButtonProgress <= 0.1f) goto L52;
        boolean r4 = true;
    L53:
        r02.setButtonVisibility(r3, r4, r9);
        GroupCallActivityButtonsLayout r03 = this.buttonsContainer;
        VoIPToggleButton r32 = this.leaveButton;
        if (this.switchToButtonProgress <= 0.1f) goto L56;
        boolean r42 = true;
    L57:
        r03.setButtonVisibility(r32, r42, r9);
        GroupCallActivityButtonsLayout r04 = this.buttonsContainer;
        VoIPToggleButton r33 = this.soundButton;
        if (this.switchToButtonProgress > 0.1f) goto L61;
        r1 = false;
    L61:
        r04.setButtonVisibility(r33, r1, r9);
        this.buttonsContainer.setButtonVisibility(this.cameraButton, false, r9);
        this.buttonsContainer.setButtonVisibility(this.flipButton, false, r9);
        this.buttonsContainer.setButtonVisibility(this.speakerButton, false, r9);
        this.buttonsContainer.setButtonVisibility(this.messageButton, false, r9);
        return;
    L56:
        r42 = false;
        goto L57
    L52:
        r4 = false;
        goto L53
    L5:
        if (r0.isScheduled() == true) goto L49;
        if (VoIPService.getSharedInstance() != null) goto L10;
    L12:
        boolean r05 = false;
    L13:
        TLRPC.GroupCallParticipant r34 = this.call.participants.get(MessageObject.getPeerId(this.selfPeer));
        if (r34 != null) goto L16;
    L22:
        boolean r35 = false;
    L23:
        Boolean r43 = this.pendingCommentsEnabled;
        if (r43 == null) goto L26;
        boolean r44 = r43.booleanValue();
    L32:
        if (r35 == false) goto L34;
    L35:
        if (r05 == true) goto L37;
    L39:
        boolean r6 = true;
        boolean r5 = false;
    L40:
        if (r05 == false) goto L42;
        boolean r36 = true;
        boolean r06 = false;
    L44:
        if (isLandscapeMode == false) goto L46;
        r06 = false;
    L47:
        this.buttonsContainer.setButtonVisibility(this.muteButton, true, r9);
        this.buttonsContainer.setButtonVisibility(this.leaveButton, true, r9);
        this.buttonsContainer.setButtonVisibility(this.cameraButton, r5, r9);
        this.buttonsContainer.setButtonVisibility(this.flipButton, r2, r9);
        this.buttonsContainer.setButtonVisibility(this.soundButton, r6, r9);
        this.buttonsContainer.setButtonVisibility(this.speakerButton, r06, r9);
        this.buttonsContainer.setButtonVisibility(this.messageButton, r44, r9);
        return;
    L46:
        r2 = r36;
        goto L47
    L42:
        r06 = !r35;
        r36 = false;
    L37:
        if (isRtmpStream() == true) goto L39;
        r5 = true;
        r6 = false;
        goto L40
    L34:
        if (this.call.canRecordVideo() == true) goto L37;
    L26:
        TLRPC.GroupCall r45 = this.call.call;
        if (r45 != null) goto L29;
    L31:
        r44 = false;
        goto L32
    L29:
        if (r45.messages_enabled == false) goto L31;
        r44 = true;
        goto L32
    L16:
        if (r34.can_self_unmute == true) goto L22;
        if (r34.muted == false) goto L22;
        if (canManageCall() == true) goto L22;
        r35 = true;
        goto L23
    L10:
        if (VoIPService.getSharedInstance().getVideoState(false) != 2) goto L12;
        r05 = true;
        goto L13
    }

    private void updateItems() {
        ChatObject.Call r0 = this.call;
        if (r0 != null) goto L5;
    L6:
        this.pipItem.setVisibility(4);
        this.screenShareItem.setVisibility(8);
        if (this.call != null) goto L11;
        this.otherItem.setVisibility(8);
        return;
    L11:
        if (this.changingPermissions == false) goto L13;
        return;
    L13:
        TLRPC.Chat r02 = this.accountInstance.getMessagesController().getChat(Long.valueOf(getChatId()));
        if (r02 == null) goto L17;
        this.currentChat = r02;
    L17:
        if (ChatObject.canUserDoAdminAction(this.currentChat, 3) == false) goto L19;
    L38:
        this.inviteItem.setVisibility(0);
    L39:
        ChatObject.Call r03 = this.call;
        if (r03 == null) goto L54;
        TLRPC.GroupCall r04 = r03.call;
        if (r04 == null) goto L54;
        if (r04.can_change_messages_enabled == false) goto L54;
        ActionBarMenuSubItem r3 = this.enableComments;
        if (r04.messages_enabled == false) goto L48;
        int r05 = 8;
    L49:
        r3.setVisibility(r05);
        ActionBarMenuSubItem r06 = this.disableComments;
        if (this.call.call.messages_enabled == false) goto L52;
        int r32 = 0;
    L53:
        r06.setVisibility(r32);
    L55:
        TLRPC.GroupCallParticipant r07 = this.call.participants.get(MessageObject.getPeerId(this.selfPeer));
        ChatObject.Call r33 = this.call;
        if (r33 != null) goto L58;
    L66:
        this.noiseItem.setVisibility(8);
    L67:
        ActionBarMenuSubItem r34 = this.noiseItem;
        if (SharedConfig.noiseSupression == false) goto L70;
        int r5 = R.drawable.msg_noise_on;
    L71:
        r34.setIcon(r5);
        ActionBarMenuSubItem r35 = this.noiseItem;
        if (SharedConfig.noiseSupression == false) goto L75;
        int r52 = R.string.VoipNoiseCancellationEnabled;
    L76:
        r35.setSubtext(LocaleController.getString(r52));
        boolean r6 = true;
        if (canManageCall() == false) goto L112;
        this.leaveItem.setVisibility(0);
        this.editTitleItem.setVisibility(0);
        if (isRtmpStream() == false) goto L81;
        this.recordItem.setVisibility(0);
        this.screenItem.setVisibility(8);
    L86:
        if (isConference() == false) goto L89;
        this.recordItem.setVisibility(8);
        this.editTitleItem.setVisibility(8);
    L89:
        if (this.call.canRecordVideo() == true) goto L91;
    L96:
        this.screenItem.setVisibility(8);
    L97:
        this.screenShareItem.setVisibility(8);
        this.recordCallDrawable.setRecording(this.call.recording);
        boolean r08 = this.call.recording;
        Runnable r36 = this.updateCallRecordRunnable;
        if (r08 == false) goto L102;
        if (r36 != null) goto L101;
        GroupCallActivity$$ExternalSyntheticLambda53 r09 = new GroupCallActivity$$ExternalSyntheticLambda53(this);
        this.updateCallRecordRunnable = r09;
        AndroidUtilities.runOnUIThread(r09, 1000);
    L101:
        this.recordItem.setText(LocaleController.getString(R.string.VoipGroupStopRecordCall));
    L106:
        if (VoIPService.getSharedInstance() != null) goto L108;
    L110:
        this.screenItem.setTextAndIcon(LocaleController.getString(R.string.VoipChatStartScreenCapture), R.drawable.msg_screencast);
    L111:
        updateRecordCallText();
    L141:
        if (canManageCall() == true) goto L143;
    L147:
        this.permissionItem.setVisibility(8);
    L148:
        ActionBarMenuItem r010 = this.otherItem;
        if (isConference() == false) goto L158;
        ChatObject.Call r37 = this.call;
        if (r37 == null) goto L157;
        TLRPC.GroupCall r38 = r37.call;
        if (r38 == null) goto L157;
        if (r38.creator == true) goto L158;
    L157:
        r6 = false;
    L158:
        r010.setSubItemShown(4, r6);
        ActionBarMenuSubItem r011 = this.soundItem;
        if (isRtmpStream() == true) goto L161;
    L163:
        int r1 = 0;
    L164:
        r011.setVisibility(r1);
        if (this.editTitleItem.getVisibility() != 0) goto L167;
    L178:
        this.soundItemDivider.setVisibility(0);
    L180:
        if (VoIPService.getSharedInstance() == null) goto L184;
        if (VoIPService.getSharedInstance().hasFewPeers == false) goto L184;
    L186:
        if (isRtmpStream() == false) goto L188;
    L194:
        this.accountSelectCell.setVisibility(8);
    L195:
        TLRPC.Chat r012 = this.currentChat;
        if (r012 != null) goto L198;
    L204:
        this.otherItem.setVisibility(0);
    L205:
        FrameLayout.LayoutParams r013 = (FrameLayout.LayoutParams) this.titleLayout.getLayoutParams();
        if (r013.rightMargin == AndroidUtilities.dp(96.0f)) goto L208;
        r013.rightMargin = AndroidUtilities.dp(96.0f);
        this.titleLayout.requestLayout();
    L208:
        ((FrameLayout.LayoutParams) this.menuItemsContainer.getLayoutParams()).rightMargin = 0;
        this.actionBar.setTitleRightMargin(AndroidUtilities.dp(48.0f) * 2);
        return;
    L198:
        if (ChatObject.isChannelOrGiga(r012) == true) goto L204;
        if (isRtmpStream() == false) goto L204;
        if (this.inviteItem.getVisibility() != 8) goto L204;
        this.otherItem.setVisibility(8);
        goto L205
    L188:
        if (this.selfPeer == null) goto L194;
        this.accountSelectCell.setVisibility(0);
        long r014 = MessageObject.getPeerId(this.selfPeer);
        boolean r39 = DialogObject.isUserDialog(r014);
        AccountInstance r62 = this.accountInstance;
        if (r39 == false) goto L192;
        TLObject r015 = r62.getMessagesController().getUser(Long.valueOf(r014));
    L193:
        this.accountSelectCell.setObject(r015);
        goto L195
    L192:
        r015 = r62.getMessagesController().getChat(Long.valueOf(-r014));
    L184:
        if (this.scheduleHasFewPeers == false) goto L194;
    L167:
        if (this.permissionItem.getVisibility() == 0) goto L178;
        if (this.inviteItem.getVisibility() == 0) goto L178;
        if (this.screenItem.getVisibility() == 0) goto L178;
        if (this.recordItem.getVisibility() == 0) goto L178;
        if (this.leaveItem.getVisibility() == 0) goto L178;
        this.soundItemDivider.setVisibility(8);
        goto L180
    L161:
        if (this.call.isScheduled() == true) goto L163;
        r1 = 8;
        goto L164
    L143:
        if (this.call.call.can_change_join_muted == false) goto L147;
        if (isConference() == true) goto L147;
        this.permissionItem.setVisibility(0);
        goto L148
    L108:
        if (VoIPService.getSharedInstance().getVideoState(true) != 2) goto L110;
        this.screenItem.setTextAndIcon(LocaleController.getString(R.string.VoipChatStopScreenCapture), R.drawable.msg_screencast_off);
        goto L111
    L102:
        if (r36 == null) goto L104;
        AndroidUtilities.cancelRunOnUIThread(r36);
        this.updateCallRecordRunnable = null;
    L104:
        this.recordItem.setText(LocaleController.getString(R.string.VoipGroupRecordCall));
        goto L106
    L91:
        if (this.call.isScheduled() == true) goto L96;
        if (isRtmpStream() == true) goto L96;
        this.screenItem.setVisibility(0);
        goto L97
    L81:
        boolean r016 = this.call.isScheduled();
        ActionBarMenuSubItem r310 = this.recordItem;
        if (r016 == false) goto L84;
        r310.setVisibility(8);
        this.screenItem.setVisibility(8);
        goto L86
    L84:
        r310.setVisibility(0);
        goto L86
    L112:
        if (r07 != null) goto L114;
    L120:
        boolean r017 = false;
    L122:
        if (VoIPService.getSharedInstance() != null) goto L124;
    L126:
        boolean r311 = false;
    L127:
        if (r017 == false) goto L129;
    L138:
        this.screenShareItem.setVisibility(8);
        this.screenItem.setVisibility(8);
    L139:
        this.leaveItem.setVisibility(8);
        this.editTitleItem.setVisibility(8);
        this.recordItem.setVisibility(8);
        goto L141
    L129:
        if (this.call.canRecordVideo() == true) goto L132;
        if (r311 == false) goto L138;
    L132:
        if (this.call.isScheduled() == true) goto L138;
        if (isRtmpStream() == true) goto L138;
        if (r311 == false) goto L137;
        this.screenShareItem.setVisibility(8);
        this.screenItem.setVisibility(0);
        this.screenItem.setTextAndIcon(LocaleController.getString(R.string.VoipChatStopScreenCapture), R.drawable.msg_screencast_off);
        this.screenItem.setContentDescription(LocaleController.getString(R.string.VoipChatStopScreenCapture));
        goto L139
    L137:
        this.screenItem.setTextAndIcon(LocaleController.getString(R.string.VoipChatStartScreenCapture), R.drawable.msg_screencast);
        this.screenItem.setContentDescription(LocaleController.getString(R.string.VoipChatStartScreenCapture));
        this.screenShareItem.setVisibility(8);
        this.screenItem.setVisibility(0);
        goto L139
    L124:
        if (VoIPService.getSharedInstance().getVideoState(true) != 2) goto L126;
        r311 = true;
        goto L127
    L114:
        if (r07.can_self_unmute == true) goto L120;
        if (r07.muted == false) goto L120;
        if (canManageCall() == true) goto L120;
        r017 = true;
        goto L122
    L75:
        r52 = R.string.VoipNoiseCancellationDisabled;
        goto L76
    L70:
        r5 = R.drawable.msg_noise_off;
        goto L71
    L58:
        if (r33.isScheduled() == true) goto L66;
        if (r07 != null) goto L61;
    L65:
        this.noiseItem.setVisibility(0);
        goto L67
    L61:
        if (r07.can_self_unmute == true) goto L65;
        if (r07.muted == false) goto L65;
    L52:
        r32 = 8;
        goto L53
    L48:
        r05 = 0;
    L54:
        this.enableComments.setVisibility(8);
        this.disableComments.setVisibility(8);
        goto L55
    L19:
        if (ChatObject.isChannel(this.currentChat) == false) goto L25;
        TLRPC.Chat r018 = this.currentChat;
        if (r018 == null) goto L29;
        if (r018.megagroup == true) goto L25;
    L29:
        if (ChatObject.isChannel(this.currentChat) == false) goto L37;
        TLRPC.Chat r019 = this.currentChat;
        if (r019 == null) goto L37;
        if (r019.megagroup == true) goto L37;
        if (ChatObject.isPublic(r019) == true) goto L38;
    L37:
        this.inviteItem.setVisibility(8);
    L25:
        if (ChatObject.isPublic(this.currentChat) == true) goto L38;
        if (ChatObject.canUserDoAdminAction(this.currentChat, 3) == true) goto L38;
    L5:
        if (r0.isScheduled() == false) goto L11;
        goto L6
    }

    private void updateLayout(boolean r17) {
        int r1 = this.listView.getChildCount();
        float r5 = 2.1474836E9f;
        int r4 = 0;
    L3:
        if (r4 >= r1) goto L8;
        if (this.listView.getChildAdapterPosition(this.listView.getChildAt(r4)) < 0) goto L7;
        r5 = Math.min(r5, r6.getTop());
    L7:
        r4 = r4 + 1;
        goto L3
    L8:
        float r42 = 0.0f;
        if (r5 >= 0.0f) goto L11;
    L12:
        if (r1 == 0) goto L14;
        r5 = 0.0f;
    L16:
        if (r5 > (ActionBar.getCurrentActionBarHeight() - AndroidUtilities.dp(14.0f))) goto L18;
        boolean r12 = true;
    L19:
        float r52 = r5 + (ActionBar.getCurrentActionBarHeight() + AndroidUtilities.dp(14.0f));
        if (r12 == true) goto L22;
    L23:
        if (r12 == false) goto L25;
    L86:
        if (this.scrollOffsetY == r52) goto L92;
        setScrollOffsetY(r52);
        return;
    L92:
        return;
    L25:
        if (this.actionBar.getTag() == null) goto L86;
    L26:
        ActionBar r7 = this.actionBar;
        if (r12 == false) goto L29;
        Integer r9 = 1;
    L30:
        r7.setTag(r9);
        AnimatorSet r72 = this.actionBarAnimation;
        if (r72 == null) goto L34;
        r72.cancel();
        this.actionBarAnimation = null;
    L34:
        if (this.actionBar.getTag() != null) goto L36;
        boolean r73 = true;
    L37:
        setUseLightStatusBar(r73);
        ViewPropertyAnimator r74 = this.actionBar.getBackButton().animate();
        float r8 = 0.9f;
        if (r12 == false) goto L40;
        float r10 = 1.0f;
    L41:
        ViewPropertyAnimator r75 = r74.scaleX(r10);
        if (r12 == false) goto L44;
        r8 = 1.0f;
    L44:
        ViewPropertyAnimator r76 = r75.scaleY(r8);
        if (r12 == false) goto L47;
        float r2 = 0.0f;
    L48:
        ViewPropertyAnimator r22 = r76.translationX(r2).setDuration(300);
        CubicBezierInterpolator r102 = CubicBezierInterpolator.DEFAULT;
        r22.setInterpolator(r102).start();
        ViewPropertyAnimator r23 = this.actionBar.getTitleTextView().animate();
        if (r12 == false) goto L51;
        float r11 = 0.0f;
    L52:
        r23.translationY(r11).setDuration(300).setInterpolator(r102).start();
        ObjectAnimator r24 = this.subtitleYAnimator;
        if (r24 == null) goto L55;
        r24.removeAllListeners();
        this.subtitleYAnimator.cancel();
    L55:
        SimpleTextView r25 = this.actionBar.getSubtitleTextView();
        Property r112 = View.TRANSLATION_Y;
        float r122 = this.actionBar.getSubtitleTextView().getTranslationY();
        if (r12 == false) goto L58;
        float r14 = 0.0f;
    L59:
        ObjectAnimator r26 = ObjectAnimator.ofFloat(r25, r112, new float[]{r122, r14});
        this.subtitleYAnimator = r26;
        r26.setDuration(300);
        this.subtitleYAnimator.setInterpolator(r102);
        this.subtitleYAnimator.addListener(new AnonymousClass57(this, r12));
        this.subtitleYAnimator.start();
        ObjectAnimator r27 = this.additionalSubtitleYAnimator;
        if (r27 == null) goto L62;
        r27.cancel();
    L62:
        SimpleTextView r28 = this.actionBar.getAdditionalSubtitleTextView();
        if (r12 == false) goto L65;
        float r3 = 0.0f;
    L66:
        ObjectAnimator r29 = ObjectAnimator.ofFloat(r28, r112, new float[]{r3});
        this.additionalSubtitleYAnimator = r29;
        r29.setDuration(300);
        this.additionalSubtitleYAnimator.setInterpolator(r102);
        this.additionalSubtitleYAnimator.start();
        AnimatorSet r210 = new AnimatorSet();
        this.actionBarAnimation = r210;
        r210.setDuration(140);
        AnimatorSet r211 = this.actionBarAnimation;
        ActionBar r32 = this.actionBar;
        Property r77 = View.ALPHA;
        if (r12 == false) goto L69;
        float r82 = 1.0f;
    L70:
        ObjectAnimator r33 = ObjectAnimator.ofFloat(r32, r77, new float[]{r82});
        View r83 = this.actionBarBackground;
        if (r12 == false) goto L73;
        float r103 = 1.0f;
    L74:
        ObjectAnimator r84 = ObjectAnimator.ofFloat(r83, r77, new float[]{r103});
        View r104 = this.actionBarShadow;
        if (r12 == false) goto L77;
        r42 = 1.0f;
    L77:
        r211.playTogether(new Animator[]{r33, r84, ObjectAnimator.ofFloat(r104, r77, new float[]{r42})});
        this.actionBarAnimation.addListener(new AnonymousClass58(this));
        this.actionBarAnimation.start();
        ImageView r212 = this.renderersContainer.pipView;
        if (r12 == true) goto L80;
    L83:
        boolean r34 = true;
    L84:
        r212.setClickable(r34);
        goto L86
    L80:
        if (isLandscapeMode == true) goto L83;
        r34 = false;
        goto L84
    L73:
        r103 = 0.0f;
        goto L74
    L69:
        r82 = 0.0f;
        goto L70
    L65:
        r3 = AndroidUtilities.dp(20.0f);
        goto L66
    L58:
        r14 = AndroidUtilities.dp(20.0f);
        goto L59
    L51:
        r11 = AndroidUtilities.dp(23.0f);
        goto L52
    L47:
        r2 = -AndroidUtilities.dp(14.0f);
        goto L48
    L40:
        r10 = 0.9f;
        goto L41
    L36:
        r73 = false;
        goto L37
    L29:
        r9 = null;
        goto L30
    L22:
        if (this.actionBar.getTag() == null) goto L26;
    L18:
        r12 = false;
        goto L19
    L14:
        r5 = this.listView.getPaddingTop();
        goto L16
    L11:
        if (r5 != 2.1474836E9f) goto L16;
        goto L12
    }

    private void updateLiveLabel(boolean r6) {
        boolean r0 = isRtmpStream();
        TextView r1 = this.liveLabelTextView;
        if (r0 == false) goto L28;
        boolean r02 = false;
        r1.setVisibility(0);
        if (((Integer) this.liveLabelTextView.getTag()).intValue() != 3) goto L7;
        boolean r12 = true;
    L8:
        int r4 = this.currentCallState;
        if (r4 != 3) goto L11;
        r02 = true;
    L11:
        this.liveLabelTextView.setTag(Integer.valueOf(r4));
        if (r12 == r02) goto L30;
        ValueAnimator r13 = this.liveLabelBgColorAnimator;
        if (r13 == null) goto L16;
        r13.cancel();
    L16:
        Paint r14 = this.liveLabelPaint;
        int r2 = -12761513;
        if (r6 == false) goto L24;
        int r62 = r14.getColor();
        if (r02 == false) goto L21;
        r2 = -1163700;
    L21:
        ValueAnimator r15 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.liveLabelBgColorAnimator = r15;
        r15.addUpdateListener(new GroupCallActivity$$ExternalSyntheticLambda3(this, r62, r2, r02));
        this.liveLabelBgColorAnimator.setDuration(300);
        this.liveLabelBgColorAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.liveLabelBgColorAnimator.addListener(new AnonymousClass59(this));
        this.liveLabelBgColorAnimator.start();
        return;
    L24:
        if (this.currentCallState != 3) goto L26;
        r2 = -1163700;
    L26:
        r14.setColor(r2);
        this.liveLabelTextView.invalidate();
        return;
    L30:
        return;
    L7:
        r12 = false;
        goto L8
    L28:
        r1.setVisibility(8);
    }

    private void updateMuteButton(int r22, boolean r23) {
        GroupCallRenderersContainer r2 = this.renderersContainer;
        if (r2 != null) goto L5;
    L11:
        boolean r24 = false;
    L13:
        if (isRtmpStream() == true) goto L19;
        if (this.muteButtonState != r22) goto L19;
        if (r23 == false) goto L19;
        return;
    L19:
        if (r22 != 7) goto L24;
        String r18 = LocaleController.getString(R.string.VoipGroupCancelReminderShort);
        boolean r6 = this.bigMicDrawable.setCustomEndFrame(202);
    L21:
        int r4 = 3;
        boolean r3 = r6;
        String r62 = _UrlKt.FRAGMENT_ENCODE_SET;
    L22:
        int r5 = 36;
    L23:
        boolean r16 = false;
    L110:
        if (isRtmpStream() == false) goto L122;
        if (r22 == r4) goto L122;
        if (this.call.isScheduled() == true) goto L122;
        if (r24 == false) goto L116;
        int r32 = R.string.VoipGroupMinimizeStream;
    L117:
        String r182 = LocaleController.getString(r32);
        if (this.animatingToFullscreenExpand == r24) goto L120;
        boolean r33 = true;
    L121:
        this.animatingToFullscreenExpand = r24;
        r62 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r25 = r182;
        boolean r17 = r33;
    L124:
        if (TextUtils.isEmpty(r62) == true) goto L126;
        String r34 = r25 + " " + r62;
    L127:
        this.muteButton.setContentDescription(r34);
        int r35 = r5;
        this.muteButton.setData(0, 0, 0, r25, false, r23);
        if (r23 == false) goto L207;
        if (r17 == false) goto L186;
        if (r22 != 5) goto L132;
        this.bigMicDrawable.setCurrentFrame(376);
        goto L186
    L132:
        if (r22 != 7) goto L134;
        this.bigMicDrawable.setCurrentFrame(173);
        goto L186
    L134:
        if (r22 != 6) goto L136;
        this.bigMicDrawable.setCurrentFrame(MediaError.DetailedErrorCode.HLS_NETWORK_MASTER_PLAYLIST);
        goto L186
    L136:
        if (r22 != 0) goto L149;
        int r36 = this.muteButtonState;
        if (r36 != 5) goto L140;
        this.bigMicDrawable.setCurrentFrame(376);
        goto L186
    L140:
        if (r36 != 7) goto L142;
        this.bigMicDrawable.setCurrentFrame(344);
        goto L186
    L142:
        if (r36 != 6) goto L144;
        this.bigMicDrawable.setCurrentFrame(202);
        goto L186
    L144:
        RLottieDrawable r42 = this.bigMicDrawable;
        if (r36 != 2) goto L147;
        r42.setCurrentFrame(0);
        goto L186
    L147:
        r42.setCurrentFrame(69);
        goto L186
    L149:
        if (r22 != 1) goto L151;
    L181:
        RLottieDrawable r43 = this.bigMicDrawable;
        if (this.muteButtonState != 4) goto L184;
        int r11 = 69;
    L185:
        r43.setCurrentFrame(r11);
        goto L186
    L184:
        r11 = r35;
        goto L185
    L151:
        if (this.muteButtonState != 2) goto L156;
        if (isConference() == true) goto L181;
    L156:
        if (r22 != 4) goto L158;
        this.bigMicDrawable.setCurrentFrame(99);
        goto L186
    L158:
        int r44 = this.muteButtonState;
        if (r16 == false) goto L168;
        if (r44 != 7) goto L162;
        this.bigMicDrawable.setCurrentFrame(274);
        goto L186
    L162:
        if (r44 != 6) goto L164;
        this.bigMicDrawable.setCurrentFrame(237);
        goto L186
    L164:
        RLottieDrawable r37 = this.bigMicDrawable;
        if (r44 != 1) goto L167;
        r37.setCurrentFrame(136);
        goto L186
    L167:
        r37.setCurrentFrame(99);
        goto L186
    L168:
        if (r44 != 5) goto L170;
        this.bigMicDrawable.setCurrentFrame(376);
        goto L186
    L170:
        if (r44 != 7) goto L172;
        this.bigMicDrawable.setCurrentFrame(344);
        goto L186
    L172:
        if (r44 != 6) goto L175;
        this.bigMicDrawable.setCurrentFrame(202);
        goto L186
    L175:
        if (r44 != 2) goto L177;
    L180:
        this.bigMicDrawable.setCurrentFrame(0);
        goto L186
    L177:
        if (r44 == 4) goto L180;
        this.bigMicDrawable.setCurrentFrame(69);
    L186:
        this.muteButtonIcon.playAnimation();
        if (isRtmpStream() == true) goto L189;
    L205:
        this.expandOrMinimizeButton.setVisibility(8);
        this.muteButtonIcon.setVisibility(0);
    L206:
        this.muteButtonState = r22;
    L227:
        updateMuteButtonState(r23);
        return;
    L189:
        if (this.call.isScheduled() == true) goto L205;
        GroupCallRenderersContainer r38 = this.renderersContainer;
        if (r38 != null) goto L193;
    L199:
        boolean r9 = false;
    L200:
        ImageView r39 = this.expandOrMinimizeButton;
        if (r9 == false) goto L203;
        int r45 = R.drawable.voice_minimize;
    L204:
        r39.setImageResource(r45);
        this.expandOrMinimizeButton.setVisibility(0);
        this.muteButtonIcon.setVisibility(8);
        goto L206
    L203:
        r45 = R.drawable.voice_expand;
        goto L204
    L193:
        if (r38.inFullscreenMode == false) goto L199;
        if (AndroidUtilities.isTablet() == false) goto L197;
    L198:
        r9 = true;
        goto L200
    L197:
        if (isLandscapeMode != isRtmpLandscapeMode()) goto L199;
    L207:
        this.muteButtonState = r22;
        RLottieDrawable r1 = this.bigMicDrawable;
        r1.setCurrentFrame(r1.getCustomEndFrame() - 1, false, true);
        if (isRtmpStream() == true) goto L210;
    L226:
        this.expandOrMinimizeButton.setVisibility(8);
        this.muteButtonIcon.setVisibility(0);
        goto L227
    L210:
        if (this.call.isScheduled() == true) goto L226;
        GroupCallRenderersContainer r12 = this.renderersContainer;
        if (r12 != null) goto L214;
    L220:
        boolean r92 = false;
    L221:
        ImageView r13 = this.expandOrMinimizeButton;
        if (r92 == false) goto L224;
        int r310 = R.drawable.voice_minimize;
    L225:
        r13.setImageResource(r310);
        this.expandOrMinimizeButton.setVisibility(0);
        this.muteButtonIcon.setVisibility(8);
        goto L227
    L224:
        r310 = R.drawable.voice_expand;
        goto L225
    L214:
        if (r12.inFullscreenMode == false) goto L220;
        if (AndroidUtilities.isTablet() == false) goto L218;
    L219:
        r92 = true;
        goto L221
    L218:
        if (isLandscapeMode != isRtmpLandscapeMode()) goto L220;
    L126:
        r34 = r25;
        goto L127
    L120:
        r33 = false;
        goto L121
    L116:
        r32 = R.string.VoipGroupExpandStream;
    L122:
        r17 = r3;
        r25 = r18;
        goto L124
    L24:
        if (r22 != 6) goto L26;
        r18 = LocaleController.getString(R.string.VoipGroupSetReminderShort);
        r6 = this.bigMicDrawable.setCustomEndFrame(344);
        goto L21
    L26:
        if (r22 != 5) goto L29;
        r18 = LocaleController.getString(R.string.VoipGroupStartNowShort);
        r6 = this.bigMicDrawable.setCustomEndFrame(377);
        goto L21
    L29:
        if (r22 != 0) goto L53;
        r18 = LocaleController.getString(R.string.VoipGroupUnmuteShort);
        r62 = LocaleController.getString(R.string.VoipHoldAndTalk);
        int r52 = this.muteButtonState;
        if (r52 != 3) goto L41;
        int r46 = this.bigMicDrawable.getCustomEndFrame();
        if (r46 == 136) goto L39;
        if (r46 == 173) goto L39;
        if (r46 == 274) goto L39;
        if (r46 == 311) goto L39;
        boolean r47 = false;
    L40:
        boolean r53 = r47;
        r4 = 3;
        r3 = r53;
    L39:
        r47 = this.bigMicDrawable.setCustomEndFrame(99);
        goto L40
    L41:
        if (r52 != 5) goto L43;
        r47 = this.bigMicDrawable.setCustomEndFrame(404);
        goto L40
    L43:
        if (r52 != 7) goto L45;
        r47 = this.bigMicDrawable.setCustomEndFrame(376);
        goto L40
    L45:
        if (r52 != 6) goto L47;
        r47 = this.bigMicDrawable.setCustomEndFrame(237);
        goto L40
    L47:
        RLottieDrawable r48 = this.bigMicDrawable;
        if (r52 != 2) goto L50;
        r5 = 36;
        r3 = r48.setCustomEndFrame(36);
        r4 = 3;
        goto L23
    L50:
        r3 = r48.setCustomEndFrame(99);
    L51:
        r4 = 3;
        goto L22
    L53:
        if (r22 != 1) goto L65;
        if (isConference() == false) goto L57;
        int r311 = R.string.VoipTapToMuteConferenceShort;
    L58:
        r18 = LocaleController.getString(r311);
        RLottieDrawable r312 = this.bigMicDrawable;
        if (this.muteButtonState != 4) goto L61;
        int r49 = 99;
    L62:
        boolean r63 = r312.setCustomEndFrame(r49);
    L63:
        r3 = r63;
        r62 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L51
    L61:
        r49 = 69;
        goto L62
    L57:
        r311 = R.string.VoipTapToMuteShort;
        goto L58
    L65:
        if (isConference() == false) goto L70;
        if (r22 != 2) goto L70;
        r18 = LocaleController.getString(R.string.VoipMutedByAdminShort);
        r63 = this.bigMicDrawable.setCustomEndFrame(99);
    L70:
        if (r22 != 4) goto L72;
        r18 = LocaleController.getString(R.string.VoipMutedTapedForSpeakShort);
        r62 = LocaleController.getString(R.string.VoipMutedTapedForSpeakInfo);
        r3 = this.bigMicDrawable.setCustomEndFrame(136);
        goto L51
    L72:
        TLRPC.GroupCallParticipant r313 = this.call.participants.get(MessageObject.getPeerId(this.selfPeer));
        if (r313 != null) goto L75;
    L81:
        boolean r314 = false;
    L82:
        int r54 = this.muteButtonState;
        if (r314 == false) goto L93;
        if (r54 != 7) goto L87;
        boolean r410 = this.bigMicDrawable.setCustomEndFrame(MediaError.DetailedErrorCode.HLS_NETWORK_MASTER_PLAYLIST);
    L86:
        boolean r64 = r410;
        r4 = 3;
        r5 = 36;
    L106:
        if (r22 != r4) goto L108;
        r18 = LocaleController.getString(R.string.Connecting);
        r16 = r314;
        r3 = r64;
        r62 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L110
    L108:
        r18 = LocaleController.getString(R.string.VoipMutedByAdminShort);
        r16 = r314;
        r3 = r64;
        r62 = LocaleController.getString(R.string.VoipMutedTapForSpeak);
        goto L110
    L87:
        if (r54 != 6) goto L89;
        r410 = this.bigMicDrawable.setCustomEndFrame(274);
        goto L86
    L89:
        RLottieDrawable r411 = this.bigMicDrawable;
        if (r54 != 1) goto L92;
        r410 = r411.setCustomEndFrame(173);
        goto L86
    L92:
        r410 = r411.setCustomEndFrame(136);
        goto L86
    L93:
        if (r54 != 5) goto L95;
        r410 = this.bigMicDrawable.setCustomEndFrame(404);
        goto L86
    L95:
        if (r54 != 7) goto L97;
        r410 = this.bigMicDrawable.setCustomEndFrame(376);
        goto L86
    L97:
        if (r54 != 6) goto L100;
        r410 = this.bigMicDrawable.setCustomEndFrame(237);
        goto L86
    L100:
        if (r54 != 2) goto L102;
    L105:
        r5 = 36;
        r64 = this.bigMicDrawable.setCustomEndFrame(36);
        r4 = 3;
        goto L106
    L102:
        if (r54 == 4) goto L105;
        r410 = this.bigMicDrawable.setCustomEndFrame(99);
        goto L86
    L75:
        if (r313.can_self_unmute == true) goto L81;
        if (r313.muted == false) goto L81;
        if (canManageCall() == true) goto L81;
        r314 = true;
        goto L82
    L5:
        if (r2.inFullscreenMode == false) goto L11;
        if (AndroidUtilities.isTablet() == false) goto L9;
    L10:
        r24 = true;
        goto L13
    L9:
        if (isLandscapeMode != isRtmpLandscapeMode()) goto L11;
        goto L10
    }

    private void updateMuteButtonState(boolean r15) {
        this.muteButton.invalidate();
        WeavingState[] r0 = this.states;
        int r1 = this.muteButtonState;
        boolean r5 = true;
        if (r0[r1] != null) goto L14;
        r0[r1] = new WeavingState(r1);
        int r02 = this.muteButtonState;
        if (r02 != 3) goto L8;
        this.states[r02].shader = null;
        goto L14
    L8:
        if (isGradientState(r02) == false) goto L10;
        this.states[this.muteButtonState].shader = new LinearGradient(0.0f, 400.0f, 400.0f, 0.0f, new int[]{Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient), Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient3), Theme.getColor(Theme.key_voipgroup_mutedByAdminGradient2)}, null, Shader.TileMode.CLAMP);
        goto L14
    L10:
        int r03 = this.muteButtonState;
        WeavingState[] r12 = this.states;
        if (r03 != 1) goto L13;
        r12[r03].shader = new RadialGradient(200.0f, 200.0f, 200.0f, new int[]{Theme.getColor(Theme.key_voipgroup_muteButton), Theme.getColor(Theme.key_voipgroup_muteButton3)}, null, Shader.TileMode.CLAMP);
        goto L14
    L13:
        r12[r03].shader = new RadialGradient(200.0f, 200.0f, 200.0f, new int[]{Theme.getColor(Theme.key_voipgroup_unmuteButton2), Theme.getColor(Theme.key_voipgroup_unmuteButton)}, null, Shader.TileMode.CLAMP);
    L14:
        WeavingState r04 = this.states[this.muteButtonState];
        WeavingState r13 = this.currentState;
        float r2 = 1.0f;
        if (r04 == r13) goto L22;
        this.prevState = r13;
        this.currentState = r04;
        if (r13 == null) goto L21;
        if (r15 == false) goto L21;
        this.switchProgress = 0.0f;
    L21:
        this.switchProgress = 1.0f;
        this.prevState = null;
    L22:
        if (r15 == true) goto L44;
        WeavingState r152 = this.currentState;
        boolean r05 = false;
        if (r152 == null) goto L35;
        int r153 = r152.currentState;
        if (r153 == 1) goto L30;
        if (r153 == 0) goto L30;
        boolean r14 = false;
    L31:
        if (r153 != 3) goto L34;
        r5 = false;
    L34:
        r05 = r14;
    L36:
        if (r05 == false) goto L38;
        float r154 = 1.0f;
    L39:
        this.showWavesProgress = r154;
        if (r5 == true) goto L43;
        r2 = 0.0f;
    L43:
        this.showLightingProgress = r2;
        goto L44
    L38:
        r154 = 0.0f;
    L30:
        r14 = true;
        goto L31
    L35:
        r5 = false;
    L44:
        this.buttonsContainer.invalidate();
    }

    private void updateRecordCallText() {
        if (this.call != null) goto L5;
        return;
    L5:
        int r0 = this.accountInstance.getConnectionsManager().getCurrentTime();
        ChatObject.Call r1 = this.call;
        int r02 = r0 - r1.call.record_start_date;
        boolean r12 = r1.recording;
        ActionBarMenuSubItem r3 = this.recordItem;
        if (r12 == false) goto L9;
        r3.setSubtext(AndroidUtilities.formatDuration(r02, false));
        return;
    L9:
        r3.setSubtext(null);
    }

    private void updateScheduleUI(boolean r7) {
        LinearLayout r0 = this.scheduleTimerContainer;
        if (r0 == null) goto L7;
        if (this.call != null) goto L7;
    L11:
        int r02 = 4;
        if (r7 == true) goto L24;
        AndroidUtilities.cancelRunOnUIThread(this.updateSchedeulRunnable);
        this.updateSchedeulRunnable.run();
        ChatObject.Call r72 = this.call;
        if (r72 != null) goto L16;
    L19:
        this.listView.setVisibility(4);
    L20:
        boolean r73 = ChatObject.isChannelOrGiga(this.currentChat);
        ActionBarMenuSubItem r3 = this.leaveItem;
        if (r73 == false) goto L23;
        r3.setText(LocaleController.getString(R.string.VoipChannelCancelChat));
        goto L24
    L23:
        r3.setText(LocaleController.getString(R.string.VoipGroupCancelChat));
        goto L24
    L16:
        if (r72.isScheduled() == true) goto L19;
        this.listView.setVisibility(0);
    L24:
        float r74 = this.switchToButtonProgress;
        if (r74 <= 0.6f) goto L27;
        float r5 = 1.05f - (CubicBezierInterpolator.DEFAULT.getInterpolation((r74 - 0.6f) / 0.4f) * 0.05f);
        this.switchToButtonInt2 = 1.0f;
        float r75 = 1.0f;
    L28:
        updateButtonsVisibility(true);
        float r1 = 1.0f - r75;
        this.scheduleTimerContainer.setAlpha(r1);
        this.scheduleStartInTextView.setAlpha(r75);
        this.scheduleStartAtTextView.setAlpha(r75);
        this.scheduleTimeTextView.setAlpha(r75);
        this.scheduleTimeTextView.setScaleX(r5);
        this.scheduleTimeTextView.setScaleY(r5);
        this.scheduleButtonTextView.setScaleX(r1);
        this.scheduleButtonTextView.setScaleY(r1);
        this.scheduleButtonTextView.setAlpha(r1);
        this.scheduleInfoTextView.setAlpha(r1);
        this.otherItem.setAlpha(r75);
        if (r1 == 0.0f) goto L33;
        r02 = 0;
    L33:
        if (r02 == this.scheduleTimerContainer.getVisibility()) goto L36;
        this.scheduleTimerContainer.setVisibility(r02);
        this.scheduleButtonTextView.setVisibility(r02);
        return;
    L36:
        return;
    L27:
        CubicBezierInterpolator r4 = CubicBezierInterpolator.DEFAULT;
        this.switchToButtonInt2 = r4.getInterpolation(r74 / 0.6f);
        r5 = 1.05f * r4.getInterpolation(this.switchToButtonProgress / 0.6f);
        r75 = this.switchToButtonProgress / 0.6f;
    L7:
        if (this.scheduleAnimator != null) goto L11;
        this.switchToButtonInt2 = 1.0f;
        this.switchToButtonProgress = 1.0f;
        if (r0 != null) goto L11;
    }

    private void updateSpeakerPhoneIcon(boolean r13) {
        VoIPToggleButton r1 = this.soundButton;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.getVisibility() != 0) goto L51;
        VoIPService r12 = VoIPService.getSharedInstance();
        boolean r10 = false;
        if (r12 != null) goto L10;
    L38:
        this.soundButton.setData(R.drawable.msg_voiceshare, -1, 0, 0.3f, true, LocaleController.getString(R.string.VoipChatShare), false, r13);
        GroupCallActivityButtonsLayout r14 = this.buttonsContainer;
        VoIPToggleButton r2 = this.soundButton;
        if (ChatObject.isPublic(this.currentChat) == false) goto L41;
    L46:
        boolean r3 = true;
    L47:
        r14.setButtonEnabled(r2, r3, r13);
        this.soundButton.setChecked(true, false);
        return;
    L41:
        if (ChatObject.hasAdminRights(this.currentChat) == true) goto L43;
    L45:
        r3 = false;
        goto L47
    L43:
        if (ChatObject.canAddUsers(this.currentChat) == false) goto L45;
    L10:
        if (isRtmpStream() == true) goto L38;
        this.buttonsContainer.setButtonEnabled(this.soundButton, true, r13);
        if (r12.isBluetoothOn() == false) goto L15;
    L18:
        boolean r22 = true;
    L19:
        if (r22 == false) goto L21;
    L23:
        if (r22 == false) goto L25;
        this.soundButton.setData(R.drawable.calls_bluetooth, -1, 0, 0.1f, true, LocaleController.getString(R.string.VoipAudioRoutingBluetooth), false, r13);
    L31:
        this.soundButton.setChecked(r10, r13);
        int r15 = getAudioOutputIcon(getAudioOutputValue());
        if (this.speakerIcon == r15) goto L35;
        ImageView r23 = this.speakerImageView;
        this.speakerIcon = r15;
        AndroidUtilities.updateImageViewImageAnimated(r23, r15);
    L35:
        if (this.speakerButton.getVisibility() != 0) goto L50;
        this.speakerButton.setData(0, -1, 0, 1.0f, true, getAudioOutputName(getAudioOutputValue()), false, r13);
        this.speakerButton.setChecked(getAudioOutputActive(getAudioOutputValue()), r13);
        return;
    L50:
        return;
    L25:
        if (r10 == false) goto L27;
        this.soundButton.setData(R.drawable.calls_speaker, -1, 0, 0.3f, true, LocaleController.getString(R.string.VoipSpeaker), false, r13);
        goto L31
    L27:
        boolean r16 = r12.isHeadsetPlugged();
        VoIPToggleButton r17 = this.soundButton;
        if (r16 == false) goto L30;
        r17.setData(R.drawable.calls_headphones, -1, 0, 0.1f, true, LocaleController.getString(R.string.VoipAudioRoutingHeadset), false, r13);
        goto L31
    L30:
        r17.setData(R.drawable.calls_speaker, -1, 0, 0.1f, true, LocaleController.getString(R.string.VoipSpeaker), false, r13);
        goto L31
    L21:
        if (r12.isSpeakerphoneOn() == false) goto L23;
        r10 = true;
        goto L23
    L15:
        if (r12.isBluetoothWillOn() == true) goto L18;
        r22 = false;
        goto L19
    }

    private void updateState(boolean r27, boolean r28) {
        ChatObject.Call r1 = this.call;
        int r2 = 5;
        if (r1 == null) goto L138;
        if (r1.isScheduled() == true) goto L138;
        updateButtonsVisibility(r27);
        VoIPService r12 = VoIPService.getSharedInstance();
        if (r12 == null) goto L148;
        int r5 = 4;
        if (r12.isConverting() == false) goto L13;
    L26:
        long r15 = 0;
    L28:
        if (this.userSwitchObject == null) goto L30;
        getUndoView().showWithAction(0, 37, this.userSwitchObject, this.currentChat, null, null);
        this.userSwitchObject = null;
    L30:
        TLRPC.GroupCallParticipant r22 = this.call.participants.get(MessageObject.getPeerId(this.selfPeer));
        if (r12.micSwitching == true) goto L44;
        if (r22 == null) goto L44;
        if (r22.can_self_unmute == true) goto L44;
        if (r22.muted == false) goto L44;
        if (canManageCall() == true) goto L44;
        cancelMutePress();
        if (r22.raise_hand_rating == r15) goto L42;
        updateMuteButton(4, r27);
    L43:
        r12.setMicMute(true, false, false);
    L56:
        if (VoIPService.getSharedInstance() != null) goto L58;
    L60:
        boolean r13 = false;
    L61:
        TLRPC.GroupCallParticipant r23 = this.call.participants.get(MessageObject.getPeerId(this.selfPeer));
        if (r23 != null) goto L64;
    L70:
        boolean r24 = false;
    L71:
        if (r24 == false) goto L73;
    L74:
        if (r13 == true) goto L76;
    L78:
        boolean r3 = false;
        boolean r132 = true;
    L79:
        if (r13 == false) goto L81;
        boolean r25 = false;
        int r4 = 1;
    L83:
        if (isLandscapeMode == false) goto L85;
        boolean r14 = false;
        int r152 = 0;
    L86:
        if (r132 == false) goto L88;
        int r26 = 2;
    L89:
        int r29 = r26 + r152;
        if (r3 == true) goto L93;
        r5 = 0;
    L93:
        int r210 = r29 + r5;
        GroupCallRenderersContainer r42 = this.renderersContainer;
        if (r42 != null) goto L96;
    L98:
        int r43 = 0;
    L99:
        int r211 = r210 + r43;
        if (r14 == false) goto L102;
        int r44 = 16;
    L103:
        int r212 = r211 + r44;
        if ((this.buttonsVisibility | 2) == (r212 | 2)) goto L106;
        boolean r16 = true;
    L107:
        this.buttonsVisibility = r212;
        if (r3 == false) goto L110;
        this.cameraButton.setData(R.drawable.calls_video, -1, 0, 1.0f, true, LocaleController.getString(R.string.VoipCamera), !r13, r27);
        this.cameraButton.setChecked(true, false);
    L110:
        if (r152 == 0) goto L112;
        this.flipButton.setData(0, -1, 0, 1.0f, true, LocaleController.getString(R.string.VoipFlip), false, false);
        this.flipButton.setChecked(true, false);
    L112:
        if (r14 == false) goto L117;
        int r17 = getAudioOutputIcon(getAudioOutputValue());
        if (this.speakerIcon == r17) goto L116;
        ImageView r213 = this.speakerImageView;
        this.speakerIcon = r17;
        AndroidUtilities.updateImageViewImageAnimated(r213, r17);
    L116:
        boolean r9 = r27;
        this.speakerButton.setData(0, -1, 0, 1.0f, true, getAudioOutputName(getAudioOutputValue()), false, r9);
        this.speakerButton.setChecked(getAudioOutputActive(getAudioOutputValue()), r9);
    L118:
        VoIPToggleButton r18 = this.leaveButton;
        if (isRtmpStream() == false) goto L122;
        int r214 = R.drawable.msg_voiceclose;
    L123:
        r18.setData(r214, -1, Theme.getColor(Theme.key_voipgroup_leaveButton), 0.3f, false, LocaleController.getString(R.string.VoipGroupLeave), false, false);
        if (r16 == false) goto L127;
        if (r132 == false) goto L127;
        updateSpeakerPhoneIcon(false);
    L127:
        this.cameraButton.showText(true, r9);
        this.flipButton.showText(true, r9);
        this.speakerButton.showText(true, r9);
        updateLiveLabel(r27);
        if (isRtmpStream() == true) goto L130;
        return;
    L130:
        if (LiteMode.isEnabled(512) == true) goto L132;
        return;
    L132:
        if (this.currentCallState != 3) goto L135;
        this.needTextureLightning = true;
        runUpdateTextureLightningRunnable();
        return;
    L135:
        this.needTextureLightning = false;
        AndroidUtilities.cancelRunOnUIThread(this.updateTextureLightningRunnable);
        return;
    L122:
        r214 = R.drawable.calls_decline;
        goto L123
    L117:
        r9 = r27;
        goto L118
    L106:
        r16 = false;
        goto L107
    L102:
        r44 = 0;
        goto L103
    L96:
        if (r42.inFullscreenMode == false) goto L98;
        r43 = 8;
        goto L99
    L88:
        r26 = 0;
        goto L89
    L85:
        r14 = r25;
        r152 = r4;
        goto L86
    L81:
        r25 = !r24;
        r4 = 0;
    L76:
        if (isRtmpStream() == true) goto L78;
        r132 = false;
        r3 = true;
        goto L79
    L73:
        if (this.call.canRecordVideo() == true) goto L76;
    L64:
        if (r23.can_self_unmute == true) goto L70;
        if (r23.muted == false) goto L70;
        if (canManageCall() == true) goto L70;
        r24 = true;
        goto L71
    L58:
        if (VoIPService.getSharedInstance().getVideoState(false) != 2) goto L60;
        r13 = true;
        goto L61
    L42:
        updateMuteButton(2, r27);
    L44:
        boolean r32 = r12.isMicMute();
        if (r12.micSwitching == true) goto L52;
        if (r28 == false) goto L52;
        if (r22 == null) goto L52;
        if (r22.muted == false) goto L52;
        if (r32 == true) goto L52;
        cancelMutePress();
        r12.setMicMute(true, false, false);
        r32 = true;
    L52:
        if (r32 == false) goto L54;
        updateMuteButton(0, r27);
        goto L56
    L54:
        updateMuteButton(1, r27);
        goto L56
    L13:
        if (r12.isSwitchingStream() == true) goto L26;
        if (this.creatingServiceTime == 0) goto L19;
        r15 = 0;
        if (Math.abs(SystemClock.elapsedRealtime() - this.creatingServiceTime) <= 3000) goto L28;
    L20:
        int r45 = this.currentCallState;
        if (r45 == 1) goto L25;
        if (r45 == 2) goto L25;
        if (r45 == 6) goto L25;
        if (r45 != 5) goto L28;
    L25:
        cancelMutePress();
        updateMuteButton(3, r27);
        goto L56
    L19:
        r15 = 0;
        goto L20
    L148:
        return;
    L138:
        if (canManageCall() == false) goto L141;
    L144:
        updateMuteButton(r2, r27);
        this.leaveButton.setData(R.drawable.calls_decline, -1, Theme.getColor(Theme.key_voipgroup_leaveButton), 0.3f, false, LocaleController.getString(R.string.Close), false, false);
        updateScheduleUI(false);
        return;
    L141:
        if (this.call.call.schedule_start_subscribed == false) goto L143;
        r2 = 7;
        goto L144
    L143:
        r2 = 6;
        goto L144
    }

    private void updateSubtitle() {
        if (this.actionBar != null) goto L5;
        return;
    L5:
        if (this.call == null) goto L78;
        SpannableStringBuilder r3 = null;
        int r2 = 0;
        int r4 = 0;
    L9:
        if (r2 >= this.call.currentSpeakingPeers.size()) goto L41;
        long r7 = this.call.currentSpeakingPeers.keyAt(r2);
        TLRPC.GroupCallParticipant r5 = this.call.currentSpeakingPeers.get(r7);
        if (r5.self == true) goto L40;
        if (this.renderersContainer.isVisible(r5) == true) goto L40;
        if (this.visiblePeerIds.get(r7, 0) == 1) goto L40;
        long r72 = MessageObject.getPeerId(r5.peer);
        if (r3 != null) goto L21;
        r3 = new SpannableStringBuilder();
    L21:
        if (r4 < 2) goto L23;
    L37:
        r4 = r4 + 1;
        if (r4 != 2) goto L40;
    L23:
        if (r72 <= 0) goto L25;
        TLRPC.User r10 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r72));
    L26:
        if (r72 > 0) goto L28;
        TLRPC.Chat r73 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r72));
    L29:
        if (r10 != null) goto L32;
        if (r73 == null) goto L40;
    L32:
        if (r4 == 0) goto L34;
        r3.append(", ");
    L34:
        if (r10 == null) goto L36;
        r3.append(UserObject.getFirstName(r10), new TypefaceSpan(AndroidUtilities.bold()), 0);
        goto L37
    L36:
        r3.append(r73.title, new TypefaceSpan(AndroidUtilities.bold()), 0);
        goto L37
    L28:
        r73 = null;
        goto L29
    L25:
        r10 = null;
    L40:
        r2 = r2 + 1;
    L41:
        if (r4 <= 0) goto L43;
        String r0 = LocaleController.getPluralString("MembersAreSpeakingToast", r4);
        int r22 = r0.indexOf("un1");
        SpannableStringBuilder r42 = new SpannableStringBuilder(r0);
        r42.replace(r22, r22 + 3, r3);
        this.actionBar.getAdditionalSubtitleTextView().setText(r42);
        boolean r02 = true;
    L44:
        SimpleTextView r23 = this.actionBar.getSubtitleTextView();
        if (isRtmpStream() == false) goto L47;
        String r32 = "ViewersWatching";
    L48:
        r23.setText(LocaleController.formatPluralString(r32, this.call.call.participants_count + (this.listAdapter.addSelfToCounter() ? 1 : 0), new Object[0]));
        if (isRtmpStream() == false) goto L54;
        WatchersView r1 = this.watchersView;
        if (r1 == null) goto L54;
        r1.setWatchersCount(this.call.call.participants_count);
    L54:
        if (r02 == this.drawSpeakingSubtitle) goto L77;
        this.drawSpeakingSubtitle = r02;
        this.actionBar.invalidate();
        float r12 = 0.0f;
        this.actionBar.getSubtitleTextView().setPivotX(0.0f);
        this.actionBar.getSubtitleTextView().setPivotY(this.actionBar.getMeasuredHeight() >> 1);
        ViewPropertyAnimator r03 = this.actionBar.getSubtitleTextView().animate();
        if (this.drawSpeakingSubtitle == false) goto L58;
        float r24 = 0.98f;
    L59:
        ViewPropertyAnimator r04 = r03.scaleX(r24);
        if (this.drawSpeakingSubtitle == false) goto L62;
        float r25 = 0.9f;
    L63:
        ViewPropertyAnimator r05 = r04.scaleY(r25);
        if (this.drawSpeakingSubtitle == true) goto L67;
        r12 = 1.0f;
    L67:
        r05.alpha(r12).setDuration(150);
        AndroidUtilities.updateViewVisibilityAnimated(this.actionBar.getAdditionalSubtitleTextView(), this.drawSpeakingSubtitle);
        return;
    L62:
        r25 = 1.0f;
        goto L63
    L58:
        r24 = 1.0f;
        goto L59
    L77:
        return;
    L47:
        r32 = "Participants";
        goto L48
    L43:
        r02 = false;
        goto L44
    }

    private void updateTitle(boolean r6) {
        ChatObject.Call r0 = this.call;
        if (r0 != null) goto L11;
        boolean r02 = ChatObject.isChannelOrGiga(this.currentChat);
        AudioPlayerAlert.ClippingTextViewSwitcher r5 = this.titleTextView;
        if (r02 == false) goto L8;
        r5.setText(LocaleController.getString(R.string.VoipChannelScheduleVoiceChat), r6);
        return;
    L8:
        r5.setText(LocaleController.getString(R.string.VoipGroupScheduleVoiceChat), r6);
        return;
    L11:
        if (TextUtils.isEmpty(r0.call.title) == false) goto L13;
        TLRPC.Chat r03 = this.currentChat;
        if (r03 == null) goto L35;
        if (r03.title.equals(this.actionBar.getTitle()) == true) goto L35;
        ActionBar r04 = this.actionBar;
        if (r6 == false) goto L26;
        r04.setTitleAnimated(this.currentChat.title, true, 180);
        this.actionBar.getTitleTextView().setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda55(this));
    L28:
        if (ChatObject.isChannelOrGiga(this.currentChat) == false) goto L33;
        boolean r05 = isRtmpStream();
        AudioPlayerAlert.ClippingTextViewSwitcher r1 = this.titleTextView;
        if (r05 == false) goto L32;
        r1.setText(this.currentChat.title, r6);
    L37:
        SimpleTextView r62 = this.actionBar.getTitleTextView();
        if (this.call.recording == false) goto L44;
        if (r62.getRightDrawable() != null) goto L47;
        r62.setRightDrawable(new SmallRecordCallDrawable(r62));
        TextView r63 = this.titleTextView.getTextView();
        r63.setCompoundDrawablesWithIntrinsicBounds(null, null, new SmallRecordCallDrawable(r63), null);
        TextView r52 = this.titleTextView.getNextTextView();
        r52.setCompoundDrawablesWithIntrinsicBounds(null, null, new SmallRecordCallDrawable(r52), null);
        return;
    L47:
        return;
    L44:
        if (r62.getRightDrawable() == null) goto L48;
        r62.setRightDrawable(null);
        this.titleTextView.getTextView().setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        this.titleTextView.getNextTextView().setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        return;
    L48:
        return;
    L32:
        r1.setText(LocaleController.getString(R.string.VoipChannelVoiceChat), r6);
        goto L37
    L33:
        this.titleTextView.setText(LocaleController.getString(R.string.VoipGroupVoiceChat), r6);
        goto L37
    L26:
        r04.setTitle(this.currentChat.title);
    L35:
        if (this.currentChat != null) goto L37;
        this.actionBar.setTitle(LocaleController.getString(R.string.ConferenceChat));
        this.titleTextView.setText(LocaleController.getString(R.string.ConferenceChat), r6);
        goto L37
    L13:
        if (this.call.call.title.equals(this.actionBar.getTitle()) == true) goto L37;
        ActionBar r06 = this.actionBar;
        if (r6 == false) goto L17;
        r06.setTitleAnimated(this.call.call.title, true, 180);
        this.actionBar.getTitleTextView().setOnClickListener(new GroupCallActivity$$ExternalSyntheticLambda54(this));
    L18:
        this.titleTextView.setText(this.call.call.title, r6);
        goto L37
    L17:
        r06.setTitle(this.call.call.title);
        goto L18
    }

    private void updateTopBulletinY() {
        if (this.topBulletinContainer == null) goto L27;
        int r0 = AndroidUtilities.dp(74.0f);
        float r1 = this.scrollOffsetY - r0;
        if ((this.backgroundPaddingTop + r1) >= ActionBar.getCurrentActionBarHeight()) goto L8;
        float r2 = Math.min(1.0f, ((ActionBar.getCurrentActionBarHeight() - r1) - this.backgroundPaddingTop) / ((r0 - this.backgroundPaddingTop) - AndroidUtilities.dp(14.0f)));
        r1 = r1 - ((int) ((ActionBar.getCurrentActionBarHeight() - r0) * r2));
    L9:
        FrameLayout r02 = this.topBulletinContainer;
        r02.setTranslationY(AndroidUtilities.lerp((((-r02.getTop()) - this.topBulletinContainer.getHeight()) + (r1 + this.containerView.getPaddingTop())) + AndroidUtilities.dp(10.0f), ((-this.topBulletinContainer.getTop()) + this.actionBar.getY()) + this.actionBar.getHeight(), r2));
        Bulletin r03 = Bulletin.getVisibleBulletin();
        if (r03 != null) goto L12;
        return;
    L12:
        if (r03.getLayout() != null) goto L14;
        return;
    L14:
        if (r03.getLayout().getParent() != null) goto L16;
        return;
    L16:
        if (r03.getLayout().getParent().getParent() != this.topBulletinContainer) goto L26;
        Bulletin.Layout r5 = r03.getLayout();
        if (r2 <= 0.5f) goto L20;
        boolean r04 = true;
    L21:
        r5.setTop(r04);
        return;
    L20:
        r04 = false;
        goto L21
    L26:
        return;
    L8:
        r2 = 0.0f;
        goto L9
    }

    private void updateVideoParticipantList() {
        this.visibleVideoParticipants.clear();
        if (isTabletMode == true) goto L5;
        this.visibleVideoParticipants.addAll(this.call.visibleVideoParticipants);
        return;
    L5:
        if (this.renderersContainer.inFullscreenMode == false) goto L12;
        this.visibleVideoParticipants.addAll(this.call.visibleVideoParticipants);
        ChatObject.VideoParticipant r0 = this.renderersContainer.fullscreenParticipant;
        if (r0 == null) goto L13;
        this.visibleVideoParticipants.remove(r0);
        return;
    L13:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithTouchOutside() {
        return !this.renderersContainer.inFullscreenMode;
    }

    public boolean canManageCall() {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L17;
        if (r0.isConference() == false) goto L17;
        ChatObject.Call r1 = this.call;
        if (r1 == null) goto L14;
        TLRPC.GroupCall r12 = r1.call;
        if (r12 != null) goto L11;
        return false;
    L11:
        if (r12.creator == false) goto L19;
        return true;
    L19:
        return false;
    L14:
        return false;
    L17:
        return ChatObject.canManageCalls(this.currentChat);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r13, int r14, Object... r15) {
        String[] r1 = null;
        int r2 = 0;
        if (r13 != NotificationCenter.groupCallUpdated) goto L96;
        Long r132 = (Long) r15[1];
        ChatObject.Call r142 = this.call;
        if (r142 != null) goto L7;
        return;
    L7:
        if (r142.call.id != r132.longValue()) goto L308;
        ChatObject.Call r133 = this.call;
        if ((r133.call instanceof TLRPC.TL_groupCallDiscarded) == false) goto L12;
        dismiss();
        return;
    L12:
        if (this.creatingServiceTime != 0) goto L26;
        int r143 = this.muteButtonState;
        if (r143 == 7) goto L19;
        if (r143 == 5) goto L19;
        if (r143 != 6) goto L26;
    L19:
        if (r133.isScheduled() == true) goto L26;
        Intent r134 = new Intent(this.parentActivity, VoIPService.class);     // Catch: Throwable -> L22
        r134.putExtra("chat_id", getChatId());     // Catch: Throwable -> L22
        r134.putExtra("createGroupCall", false);     // Catch: Throwable -> L22
        r134.putExtra("hasFewPeers", this.scheduleHasFewPeers);     // Catch: Throwable -> L22
        r134.putExtra("peerChannelId", this.schedulePeer.channel_id);     // Catch: Throwable -> L22
        r134.putExtra("peerChatId", this.schedulePeer.chat_id);     // Catch: Throwable -> L22
        r134.putExtra("peerUserId", this.schedulePeer.user_id);     // Catch: Throwable -> L22
        r134.putExtra("hash", this.scheduledHash);     // Catch: Throwable -> L22
        r134.putExtra("peerAccessHash", this.schedulePeer.access_hash);     // Catch: Throwable -> L22
        r134.putExtra("is_outgoing", true);     // Catch: Throwable -> L22
        r134.putExtra("start_incall_activity", false);     // Catch: Throwable -> L22
        r134.putExtra("account", this.accountInstance.getCurrentAccount());     // Catch: Throwable -> L22
        r134.putExtra("scheduleDate", this.scheduleStartAt);     // Catch: Throwable -> L22
        this.parentActivity.startService(r134);     // Catch: Throwable -> L22
    L24:
        this.creatingServiceTime = SystemClock.elapsedRealtime();
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda36(this), 3000);
    L22:
        th = move-exception;
        FileLog.e(th);
    L26:
        if (this.callInitied == false) goto L28;
    L30:
        updateItems();
        int r135 = this.listView.getChildCount();
        int r144 = 0;
    L31:
        if (r144 >= r135) goto L37;
        View r0 = this.listView.getChildAt(r144);
        if ((r0 instanceof GroupCallUserCell) == false) goto L35;
        ((GroupCallUserCell) r0).applyParticipantChanges(true);
    L35:
        r144 = r144 + 1;
        goto L31
    L37:
        if (this.scrimView == null) goto L39;
        this.delayedGroupCallUpdated = true;
    L40:
        updateSubtitle();
        boolean r136 = ((Boolean) r15[2]).booleanValue();
        if (this.muteButtonState != 4) goto L43;
        boolean r145 = true;
    L44:
        updateState(true, r136);
        updateTitle(true);
        if (r145 == false) goto L53;
        int r137 = this.muteButtonState;
        if (r137 == 1) goto L49;
        if (r137 != 0) goto L53;
    L49:
        getUndoView().showWithAction(0, 38, null);
        if (VoIPService.getSharedInstance() == null) goto L53;
        VoIPService.getSharedInstance().playAllowTalkSound();
    L53:
        if (r15.length < 4) goto L310;
        Long r138 = (Long) r15[3];
        long r146 = r138.longValue();
        if (r146 != 0) goto L57;
        return;
    L57:
        if (isRtmpStream() == false) goto L59;
        return;
    L59:
        if (isConference() == false) goto L277;
        VoIPService r02 = VoIPService.getSharedInstance();
        if (r02 == null) goto L277;
        if (r146 != r02.convertingFromCallWithUserId) goto L277;
        return;
    L277:
        ArrayList<TLRPC.Dialog> r03 = this.accountInstance.getMessagesController().getAllDialogs();     // Catch: Exception -> L274
        if (r03 == null) goto L73;
        int r12 = r03.size();     // Catch: Exception -> L274
        int r4 = 0;
    L68:
        if (r4 >= r12) goto L73;
        TLRPC.Dialog r5 = r03.get(r4);     // Catch: Exception -> L274
        r4 = r4 + 1;     // Catch: Exception -> L274
        if (r5.id != r146) goto L68;
        r2 = 1;
    L73:
        boolean r04 = DialogObject.isUserDialog(r146);
        AccountInstance r16 = this.accountInstance;
        if (r04 == false) goto L85;
        TLRPC.User r8 = r16.getMessagesController().getUser(r138);
        if (r8 != null) goto L78;
        return;
    L78:
        if (this.call.call.participants_count >= 250) goto L80;
    L84:
        getUndoView().showWithAction(0, 44, r8, this.currentChat, null, null);
        return;
    L80:
        if (UserObject.isContact(r8) == true) goto L84;
        if (r8.verified == true) goto L84;
        if (r2 != 0) goto L84;
        return;
    L85:
        TLRPC.Chat r82 = r16.getMessagesController().getChat(Long.valueOf(-r146));
        if (r82 != null) goto L88;
        return;
    L88:
        if (this.call.call.participants_count >= 250) goto L90;
    L94:
        getUndoView().showWithAction(0, 44, r82, this.currentChat, null, null);
        return;
    L90:
        if (ChatObject.isNotInChat(r82) == false) goto L94;
        if (r82.verified == true) goto L94;
        if (r2 != 0) goto L94;
        return;
    L310:
        return;
    L43:
        r145 = false;
        goto L44
    L39:
        applyCallParticipantUpdates(true);
        goto L40
    L28:
        if (VoIPService.getSharedInstance() == null) goto L30;
        this.call.addSelfDummyParticipant(false);
        initCreatedGroupCall();
        VoIPService.getSharedInstance().playConnectedSound();
        goto L30
    L308:
        return;
    L96:
        if (r13 != NotificationCenter.groupCallSpeakingUsersUpdated) goto L130;
        GroupCallRenderersContainer r139 = this.renderersContainer;
        if (r139.inFullscreenMode == true) goto L100;
    L127:
        this.renderersContainer.setVisibleParticipant(true);
        updateSubtitle();
        return;
    L100:
        if (this.call == null) goto L127;
        boolean r1310 = r139.autoPinEnabled();
        ChatObject.Call r147 = this.call;
        if (r147 == null) goto L110;
        GroupCallRenderersContainer r152 = this.renderersContainer;
        if (r152.inFullscreenMode == false) goto L110;
        ChatObject.VideoParticipant r153 = r152.fullscreenParticipant;
        if (r153 == null) goto L110;
        if (r147.participants.get(MessageObject.getPeerId(r153.participant.peer)) != null) goto L110;
        r1310 = true;
    L110:
        if (r1310 == false) goto L127;
        ChatObject.VideoParticipant r148 = null;
        int r1311 = 0;
    L113:
        if (r1311 >= this.visibleVideoParticipants.size()) goto L125;
        ChatObject.VideoParticipant r154 = this.visibleVideoParticipants.get(r1311);
        if (this.call.currentSpeakingPeers.get(MessageObject.getPeerId(r154.participant.peer), null) == null) goto L117;
        boolean r05 = true;
    L118:
        if (r05 == false) goto L124;
        TLRPC.GroupCallParticipant r06 = r154.participant;
        if (r06.muted_by_you == true) goto L124;
        if (this.renderersContainer.fullscreenPeerId == MessageObject.getPeerId(r06.peer)) goto L124;
        r148 = r154;
    L124:
        r1311 = r1311 + 1;
        goto L113
    L117:
        r05 = false;
        goto L118
    L125:
        if (r148 == null) goto L127;
        fullscreenFor(r148);
        goto L127
    L130:
        if (r13 != NotificationCenter.webRtcMicAmplitudeEvent) goto L134;
        setMicAmplitude(((Float) r15[0]).floatValue());
        return;
    L134:
        if (r13 != NotificationCenter.needShowAlert) goto L159;
        if (((Integer) r15[0]).intValue() != 6) goto L319;
        String r1312 = (String) r15[1];
        if ("GROUPCALL_PARTICIPANTS_TOO_MUCH".equals(r1312) == false) goto L144;
        if (ChatObject.isChannelOrGiga(this.currentChat) == false) goto L142;
        String r1313 = LocaleController.getString(R.string.VoipChannelTooMuch);
    L153:
        AlertDialog.Builder r1314 = AlertsCreator.createSimpleAlert(getContext(), LocaleController.getString(R.string.VoipGroupVoiceChat), r1313);
        r1314.setOnDismissListener(new GroupCallActivity$$ExternalSyntheticLambda37(this));
        r1314.show();     // Catch: Exception -> L156
        return;
    L156:
        e = move-exception;
        FileLog.e(e);
        return;
    L142:
        r1313 = LocaleController.getString(R.string.VoipGroupTooMuch);
        goto L153
    L144:
        if ("ANONYMOUS_CALLS_DISABLED".equals(r1312) == true) goto L150;
        if ("GROUPCALL_ANONYMOUS_FORBIDDEN".equals(r1312) == true) goto L150;
        r1313 = LocaleController.getString(R.string.ErrorOccurred) + "\n" + r1312;
    L150:
        if (ChatObject.isChannelOrGiga(this.currentChat) == false) goto L152;
        r1313 = LocaleController.getString(R.string.VoipChannelJoinAnonymousAdmin);
        goto L153
    L152:
        r1313 = LocaleController.getString(R.string.VoipGroupJoinAnonymousAdmin);
        goto L153
    L319:
        return;
    L159:
        if (r13 != NotificationCenter.didEndCall) goto L165;
        if (VoIPService.getSharedInstance() != null) goto L321;
        dismiss();
        return;
    L321:
        return;
    L165:
        if (r13 != NotificationCenter.chatInfoDidLoad) goto L197;
        TLRPC.ChatFull r1315 = (TLRPC.ChatFull) r15[0];
        if (r1315.id != getChatId()) goto L169;
        updateItems();
        updateState(isShowing(), false);
    L169:
        long r149 = MessageObject.getPeerId(this.selfPeer);
        ChatObject.Call r17 = this.call;
        if (r17 != null) goto L172;
        return;
    L172:
        if (r1315.id != (-r149)) goto L323;
        TLRPC.GroupCallParticipant r1410 = r17.participants.get(r149);
        if (r1410 == null) goto L324;
        r1410.about = r1315.about;
        applyCallParticipantUpdates(true);
        AndroidUtilities.updateVisibleRows(this.listView);
        if (this.currentOptionsLayout != null) goto L178;
        return;
    L178:
        if (r2 >= this.currentOptionsLayout.getChildCount()) goto L335;
        View r1316 = this.currentOptionsLayout.getChildAt(r2);
        if ((r1316 instanceof ActionBarMenuSubItem) == false) goto L195;
        if (r1316.getTag() == null) goto L195;
        if (((Integer) r1316.getTag()).intValue() != 10) goto L195;
        ActionBarMenuSubItem r1317 = (ActionBarMenuSubItem) r1316;
        if (TextUtils.isEmpty(r1410.about) == false) goto L189;
        int r155 = R.string.VoipAddDescription;
    L188:
        String r156 = LocaleController.getString(r155);
        if (TextUtils.isEmpty(r1410.about) == false) goto L193;
        int r18 = R.drawable.msg_addbio;
    L194:
        r1317.setTextAndIcon(r156, r18);
        goto L195
    L193:
        r18 = R.drawable.msg_info;
        goto L194
    L189:
        r155 = R.string.VoipEditDescription;
    L195:
        r2 = r2 + 1;
        goto L178
    L335:
        return;
    L324:
        return;
    L323:
        return;
    L197:
        if (r13 != NotificationCenter.didLoadChatAdmins) goto L203;
        if (((Long) r15[0]).longValue() != getChatId()) goto L326;
        updateItems();
        updateState(isShowing(), false);
        return;
    L326:
        return;
    L203:
        if (r13 != NotificationCenter.applyGroupCallVisibleParticipants) goto L215;
        int r1318 = this.listView.getChildCount();
        long r1411 = ((Long) r15[0]).longValue();
    L205:
        if (r2 >= r1318) goto L336;
        RecyclerView.ViewHolder r07 = this.listView.findContainingViewHolder(this.listView.getChildAt(r2));
        if (r07 == null) goto L213;
        View r08 = r07.itemView;
        if ((r08 instanceof GroupCallUserCell) == false) goto L213;
        GroupCallUserCell r09 = (GroupCallUserCell) r08;
        if (r09.getParticipant() == null) goto L213;
        r09.getParticipant().lastVisibleDate = r1411;
    L213:
        r2 = r2 + 1;
        goto L205
    L336:
        return;
    L215:
        if (r13 != NotificationCenter.userInfoDidLoad) goto L244;
        Long r1319 = (Long) r15[0];
        long r42 = MessageObject.getPeerId(this.selfPeer);
        if (this.call != null) goto L219;
        return;
    L219:
        if (r42 != r1319.longValue()) goto L328;
        TLRPC.GroupCallParticipant r1320 = this.call.participants.get(r42);
        if (r1320 == null) goto L329;
        r1320.about = ((TLRPC.UserFull) r15[1]).about;
        applyCallParticipantUpdates(true);
        AndroidUtilities.updateVisibleRows(this.listView);
        if (this.currentOptionsLayout != null) goto L225;
        return;
    L225:
        if (r2 >= this.currentOptionsLayout.getChildCount()) goto L337;
        View r1412 = this.currentOptionsLayout.getChildAt(r2);
        if ((r1412 instanceof ActionBarMenuSubItem) == false) goto L242;
        if (r1412.getTag() == null) goto L242;
        if (((Integer) r1412.getTag()).intValue() != 10) goto L242;
        ActionBarMenuSubItem r1413 = (ActionBarMenuSubItem) r1412;
        if (TextUtils.isEmpty(r1320.about) == false) goto L236;
        int r157 = R.string.VoipAddBio;
    L235:
        String r158 = LocaleController.getString(r157);
        if (TextUtils.isEmpty(r1320.about) == false) goto L240;
        int r19 = R.drawable.msg_addbio;
    L241:
        r1413.setTextAndIcon(r158, r19);
        goto L242
    L240:
        r19 = R.drawable.msg_info;
        goto L241
    L236:
        r157 = R.string.VoipEditBio;
    L242:
        r2 = r2 + 1;
        goto L225
    L337:
        return;
    L329:
        return;
    L328:
        return;
    L244:
        if (r13 != NotificationCenter.mainUserInfoChanged) goto L248;
        applyCallParticipantUpdates(true);
        AndroidUtilities.updateVisibleRows(this.listView);
        return;
    L248:
        if (r13 != NotificationCenter.updateInterfaces) goto L259;
        int r1321 = ((Integer) r15[0]).intValue();
        if ((MessagesController.UPDATE_MASK_CHAT_NAME & r1321) == 0) goto L253;
        applyCallParticipantUpdates(true);
    L253:
        if ((MessagesController.UPDATE_MASK_CHAT_NAME & r1321) == 0) goto L255;
    L256:
        AndroidUtilities.updateVisibleRows(this.listView);
        return;
    L255:
        if ((r1321 & MessagesController.UPDATE_MASK_EMOJI_STATUS) != 0) goto L256;
        return;
    L259:
        if (r13 != NotificationCenter.groupCallScreencastStateChanged) goto L266;
        PrivateVideoPreviewDialog r1322 = this.previewDialog;
        if (r1322 == null) goto L263;
        r1322.dismiss(true, true);
    L263:
        updateItems();
        return;
    L266:
        if (r13 != NotificationCenter.conferenceEmojiUpdated) goto L332;
        VoIPService r1323 = VoIPService.getSharedInstance();
        CallEncryptionCellDrawable r122 = this.encryptionDrawable;
        if (r1323 == null) goto L272;
        ConferenceCall r1324 = r1323.conference;
        if (r1324 == null) goto L272;
        r1 = r1324.getEmojis();
    L272:
        r122.setEmojis(r1);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        this.parentActivity.removeOnUserLeaveHintListener(this.onUserLeaveHintListener);
        this.parentActivity.setRequestedOrientation(-1);
        groupCallUiVisible = false;
        GroupVoipInviteAlert r1 = this.groupVoipInviteAlert;
        if (r1 == null) goto L5;
        r1.dismiss();
    L5:
        this.delayedGroupCallUpdated = true;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.groupCallVisibilityChanged, new Object[0]);
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.needShowAlert);
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.groupCallUpdated);
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.chatInfoDidLoad);
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.didLoadChatAdmins);
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.applyGroupCallVisibleParticipants);
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.userInfoDidLoad);
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.mainUserInfoChanged);
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.updateInterfaces);
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.groupCallScreencastStateChanged);
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.groupCallSpeakingUsersUpdated);
        this.accountInstance.getNotificationCenter().removeObserver(this, NotificationCenter.conferenceEmojiUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.webRtcMicAmplitudeEvent);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didEndCall);
        super.dismiss();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        if (this.renderersContainer != null) goto L5;
    L14:
        super.dismissInternal();
        if (VoIPService.getSharedInstance() == null) goto L18;
        VoIPService.getSharedInstance().unregisterStateListener(this);
        VoIPService.getSharedInstance().setSinks(null, null);
    L18:
        if (groupCallInstance != this) goto L20;
        groupCallInstance = null;
    L20:
        groupCallUiVisible = false;
        VoIPService.audioLevelsCallback = null;
        GroupCallPip.updateVisibility(getContext());
        ChatObject.Call r0 = this.call;
        if (r0 == null) goto L24;
        r0.clearVideFramesInfo();
    L24:
        if (VoIPService.getSharedInstance() == null) goto L26;
        VoIPService.getSharedInstance().clearRemoteSinks();
    L26:
        PipSource r02 = this.pipSource;
        if (r02 == null) goto L29;
        r02.destroy();
        this.pipSource = null;
    L29:
        ProfileGalleryView r5 = this.avatarsViewPager;
        if (r5 == null) goto L34;
        r5.onDestroy();
        return;
    L34:
        return;
    L5:
        if (this.requestFullscreenListener == null) goto L7;
        this.listView.getViewTreeObserver().removeOnPreDrawListener(this.requestFullscreenListener);
        this.requestFullscreenListener = null;
    L7:
        this.attachedRenderersTmp.clear();
        this.attachedRenderersTmp.addAll(this.attachedRenderers);
        int r03 = 0;
    L9:
        if (r03 >= this.attachedRenderersTmp.size()) goto L11;
        this.attachedRenderersTmp.get(r03).saveThumb();
        this.renderersContainer.removeView(this.attachedRenderersTmp.get(r03));
        this.attachedRenderersTmp.get(r03).release();
        this.attachedRenderersTmp.get(r03).forceDetach(true);
        r03 = r03 + 1;
        goto L9
    L11:
        this.attachedRenderers.clear();
        if (this.renderersContainer.getParent() == null) goto L14;
        this.attachedRenderers.clear();
        this.containerView.removeView(this.renderersContainer);
        goto L14
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent r6) {
        if (this.parentActivity != null) goto L7;
        return super.dispatchKeyEvent(r6);
    L7:
        if (r6.getAction() != 0) goto L29;
        if (r6.getKeyCode() == 24) goto L13;
        if (r6.getKeyCode() != 25) goto L29;
    L13:
        if (VoIPService.getSharedInstance() == null) goto L29;
        if (Build.VERSION.SDK_INT < 32) goto L29;
        boolean r0 = WebRtcAudioTrack.isSpeakerMuted();
        AudioManager r1 = (AudioManager) this.parentActivity.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        boolean r3 = false;
        int r4 = r1.getStreamMinVolume(0);
        if (r1.getStreamVolume(0) == r4) goto L19;
    L21:
        WebRtcAudioTrack.setSpeakerMute(r3);
        if (r0 == WebRtcAudioTrack.isSpeakerMuted()) goto L29;
        UndoView r02 = getUndoView();
        if (r3 == false) goto L26;
        int r12 = 42;
    L27:
        r02.showWithAction(0, r12, null);
        goto L29
    L26:
        r12 = 43;
        goto L27
    L19:
        if (r6.getKeyCode() != 25) goto L21;
        r3 = true;
    L29:
        return super.dispatchKeyEvent(r6);
    }

    public void enableCamera() {
        this.cameraButton.callOnClick();
    }

    public void fullscreenFor(ChatObject.VideoParticipant r10) {
        if (r10 != null) goto L5;
        this.parentActivity.setRequestedOrientation(-1);
    L5:
        if (VoIPService.getSharedInstance() != null) goto L7;
        return;
    L7:
        if (this.renderersContainer.isAnimating() == true) goto L77;
        boolean r0 = isTabletMode;
        ViewTreeObserver.OnPreDrawListener r1 = this.requestFullscreenListener;
        if (r0 == false) goto L51;
        if (r1 == null) goto L13;
        this.listView.getViewTreeObserver().removeOnPreDrawListener(this.requestFullscreenListener);
        this.requestFullscreenListener = null;
    L13:
        ArrayList r02 = new ArrayList();
        ArrayList<GroupCallMiniTextureView> r12 = this.attachedRenderersTmp;
        if (r10 != null) goto L29;
        r12.clear();
        this.attachedRenderersTmp.addAll(this.attachedRenderers);
        int r13 = 0;
    L17:
        if (r13 >= this.attachedRenderersTmp.size()) goto L28;
        GroupCallMiniTextureView r6 = this.attachedRenderersTmp.get(r13);
        GroupCallGridCell r7 = r6.primaryView;
        if (r7 == null) goto L27;
        r7.setRenderer(null);
        GroupCallFullscreenAdapter.GroupCallUserCell r72 = r6.secondaryView;
        if (r72 == null) goto L23;
        r72.setRenderer(null);
    L23:
        GroupCallGridCell r73 = r6.tabletGridView;
        if (r73 == null) goto L26;
        r73.setRenderer(null);
    L26:
        r02.add(r6.participant);
        r6.forceDetach(false);
        r6.animate().alpha(0.0f).setListener(new AnonymousClass48(this, r6));
    L27:
        r13 = r13 + 1;
        goto L17
    L28:
        this.listViewVideoVisibility = false;
        this.tabletGridAdapter.setVisibility(this.tabletVideoGridView, true, true);
    L49:
        boolean r03 = !this.renderersContainer.inFullscreenMode;
        ViewTreeObserver r14 = this.listView.getViewTreeObserver();
        AnonymousClass50 r2 = new AnonymousClass50(this, r10, r03);
        this.requestFullscreenListener = r2;
        r14.addOnPreDrawListener(r2);
        return;
    L29:
        r12.clear();
        this.attachedRenderersTmp.addAll(this.attachedRenderers);
        int r15 = 0;
    L31:
        if (r15 >= this.attachedRenderersTmp.size()) goto L46;
        GroupCallMiniTextureView r62 = this.attachedRenderersTmp.get(r15);
        if (r62.tabletGridView == null) goto L45;
        ChatObject.VideoParticipant r74 = r62.participant;
        if (r74 != null) goto L37;
    L38:
        r02.add(r62.participant);
        r62.forceDetach(false);
        GroupCallFullscreenAdapter.GroupCallUserCell r75 = r62.secondaryView;
        if (r75 == null) goto L41;
        r75.setRenderer(null);
    L41:
        GroupCallGridCell r76 = r62.primaryView;
        if (r76 == null) goto L44;
        r76.setRenderer(null);
    L44:
        r62.animate().alpha(0.0f).setListener(new AnonymousClass49(this, r62));
        goto L45
    L37:
        if (r74.equals(r10) == false) goto L38;
    L45:
        r15 = r15 + 1;
        goto L31
    L46:
        this.listViewVideoVisibility = true;
        this.tabletGridAdapter.setVisibility(this.tabletVideoGridView, false, false);
        if (r02.isEmpty() == true) goto L49;
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda5(this, r02));
        goto L49
    L51:
        if (r1 == null) goto L53;
        this.listView.getViewTreeObserver().removeOnPreDrawListener(this.requestFullscreenListener);
        this.requestFullscreenListener = null;
    L53:
        if (r10 != null) goto L55;
        int r102 = this.listView.getVisibility();
        RecyclerListView r04 = this.listView;
        if (r102 == 0) goto L67;
        r04.setVisibility(0);
        applyCallParticipantUpdates(false);
        this.delayedGroupCallUpdated = true;
        ViewTreeObserver r103 = this.listView.getViewTreeObserver();
        AnonymousClass52 r05 = new AnonymousClass52(this);
        this.requestFullscreenListener = r05;
        r103.addOnPreDrawListener(r05);
        return;
    L67:
        ViewTreeObserver r104 = r04.getViewTreeObserver();
        AnonymousClass53 r06 = new AnonymousClass53(this);
        this.requestFullscreenListener = r06;
        r104.addOnPreDrawListener(r06);
        return;
    L55:
        if (this.fullscreenUsersListView.getVisibility() == 0) goto L61;
        this.fullscreenUsersListView.setVisibility(0);
        this.fullscreenAdapter.update(false, this.fullscreenUsersListView);
        this.delayedGroupCallUpdated = true;
        if (this.renderersContainer.inFullscreenMode == true) goto L59;
        this.fullscreenAdapter.scrollTo(r10, this.fullscreenUsersListView);
    L59:
        ViewTreeObserver r07 = this.listView.getViewTreeObserver();
        AnonymousClass51 r16 = new AnonymousClass51(this, r10);
        this.requestFullscreenListener = r16;
        r07.addOnPreDrawListener(r16);
        return;
    L61:
        this.renderersContainer.requestFullscreen(r10);
        AndroidUtilities.updateVisibleRows(this.fullscreenUsersListView);
        return;
    }

    public boolean getAudioOutputActive(int r1) {
        if (r1 == 1) goto L5;
        return true;
    L5:
        return false;
    }

    public int getAudioOutputIcon(int r1) {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L9;
        if (r0.isBluetoothHeadsetConnected() == false) goto L9;
        return R.drawable.filled_calls_bluetooth_s;
    L9:
        return R.drawable.filled_sound_on;
    }

    public String getAudioOutputName(int r2) {
        VoIPService r1 = VoIPService.getSharedInstance();
        if (r2 == 0) goto L24;
        if (r2 != 1) goto L7;
        if (r1 == null) goto L22;
        if (r1.isHeadsetPlugged() == false) goto L22;
        return LocaleController.getString(R.string.VoipAudioRoutingHeadset);
    L22:
        return LocaleController.getString(R.string.VoipAudioRoutingPhone);
    L7:
        if (r2 == 2) goto L10;
        return null;
    L10:
        if (r1 == null) goto L15;
        String r12 = r1.currentBluetoothDeviceName;
        if (r12 == null) goto L15;
        return r12;
    L15:
        return LocaleController.getString(R.string.VoipAudioRoutingBluetooth);
    L24:
        return LocaleController.getString(R.string.VoipAudioRoutingSpeaker);
    }

    public int getAudioOutputToastIcon(int r1) {
        if (r1 == 2) goto L5;
        if (r1 == 0) goto L8;
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L16;
        if (r0.isHeadsetPlugged() == false) goto L16;
        return R.drawable.msg_voice_headphones;
    L16:
        return R.drawable.msg_voice_phone;
    L8:
        return R.drawable.msg_voice_speaker;
    L5:
        return R.drawable.msg_voice_bluetooth;
    }

    public String getAudioOutputToastText(int r2) {
        VoIPService r1 = VoIPService.getSharedInstance();
        if (r2 != 2) goto L12;
        if (r1 == null) goto L10;
        String r12 = r1.currentBluetoothDeviceName;
        if (r12 == null) goto L10;
        return LocaleController.formatString(R.string.VoipAudioSwitchedToBluetoothDevice, new Object[]{r12});
    L10:
        return LocaleController.getString(R.string.VoipAudioSwitchedToBluetooth);
    L12:
        if (r2 != 1) goto L21;
        if (r1 == null) goto L19;
        if (r1.isHeadsetPlugged() == false) goto L19;
        return LocaleController.getString(R.string.VoipAudioSwitchedToHeadset);
    L19:
        return LocaleController.getString(R.string.VoipAudioSwitchedToPhone);
    L21:
        return LocaleController.getString(R.string.VoipAudioSwitchedToSpeaker);
    }

    public int getAudioOutputValue() {
        Integer r2 = this.cacheAudioOutputValue;
        if (r2 != null) goto L5;
        VoIPService r22 = VoIPService.getSharedInstance();
        if (r22 != null) goto L9;
        return 0;
    L9:
        int r23 = r22.getCurrentAudioRoute();
        if (r23 == 0) goto L15;
        if (r23 == 1) goto L14;
        return 2;
    L14:
        return 0;
    L15:
        return 1;
    L5:
        return r2.intValue();
    }

    public long getChatId() {
        TLRPC.Chat r2 = this.currentChat;
        if (r2 != null) goto L7;
        return 0;
    L7:
        return r2.id;
    }

    public LinearLayout getMenuItemsContainer() {
        return this.menuItemsContainer;
    }

    public int getNextAudioOutputValue() {
        if (getAudioOutputActive(getAudioOutputValue()) == false) goto L6;
        return 1;
    L6:
        VoIPService r1 = VoIPService.getSharedInstance();
        if (r1 != null) goto L9;
        return 0;
    L9:
        if (r1.isBluetoothHeadsetConnected() == false) goto L14;
        return 2;
    L14:
        return 0;
    }

    public LaunchActivity getParentActivity() {
        return this.parentActivity;
    }

    public View getScrimView() {
        return this.scrimView;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        return new ArrayList();
    }

    public UndoView getUndoView() {
        if (isTabletMode == true) goto L9;
        GroupCallRenderersContainer r0 = this.renderersContainer;
        if (r0.inFullscreenMode == false) goto L9;
        return r0.getUndoView();
    L9:
        if (this.undoView[0].getVisibility() != 0) goto L12;
        UndoView[] r02 = this.undoView;
        UndoView r2 = r02[0];
        r02[0] = r02[1];
        r02[1] = r2;
        r2.hide(true, 2);
        this.containerView.removeView(this.undoView[0]);
        this.containerView.addView(this.undoView[0]);
    L12:
        return this.undoView[0];
    }

    public void invalidateActionBarAlpha() {
        ActionBar r0 = this.actionBar;
        if (r0.getTag() == null) goto L5;
        float r1 = 1.0f;
    L6:
        r0.setAlpha(r1 * (1.0f - this.renderersContainer.progressToFullscreenMode));
        return;
    L5:
        r1 = 0.0f;
        goto L6
    }

    public void invalidateScrollOffsetY() {
        setScrollOffsetY(this.scrollOffsetY);
    }

    public boolean isConference() {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isConference() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isRtmpLandscapeMode() {
        if (isRtmpStream() == true) goto L5;
    L12:
        return false;
    L5:
        if (this.call.visibleVideoParticipants.isEmpty() == true) goto L12;
        if (this.call.visibleVideoParticipants.get(0).aspectRatio != 0.0f) goto L9;
        return true;
    L9:
        if (this.call.visibleVideoParticipants.get(0).aspectRatio < 1.0f) goto L12;
        return true;
    }

    public boolean isRtmpStream() {
        ChatObject.Call r0 = this.call;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.call.rtmp_stream == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void makeFocusable(BottomSheet r4, AlertDialog r5, EditTextBoldCursor r6, boolean r7) {
        if (this.enterEventSent == true) goto L20;
        BaseFragment r0 = this.parentActivity.getActionBarLayout().getFragmentStack().get(this.parentActivity.getActionBarLayout().getFragmentStack().size() - 1);
        if ((r0 instanceof ChatActivity) == false) goto L12;
        boolean r02 = ((ChatActivity) r0).needEnterText();
        this.enterEventSent = true;
        this.anyEnterEventSent = true;
        GroupCallActivity$$ExternalSyntheticLambda56 r3 = new GroupCallActivity$$ExternalSyntheticLambda56(r4, r6, r7, r5);
        if (r02 == false) goto L9;
        long r42 = 200;
    L10:
        AndroidUtilities.runOnUIThread(r3, r42);
        return;
    L9:
        r42 = 0;
        goto L10
    L12:
        this.enterEventSent = true;
        this.anyEnterEventSent = true;
        if (r4 == null) goto L15;
        r4.setFocusable(true);
    L17:
        if (r7 == false) goto L21;
        AndroidUtilities.runOnUIThread(new GroupCallActivity$$ExternalSyntheticLambda57(r6), 100);
        return;
    L21:
        return;
    L15:
        if (r5 == null) goto L17;
        r5.setFocusable(true);
        goto L17
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onAudioSettingsChanged() {
        updateSpeakerPhoneIcon(true);
        if (VoIPService.getSharedInstance() != null) goto L5;
    L6:
        setMicAmplitude(0.0f);
    L8:
        if (this.listView.getVisibility() != 0) goto L11;
        AndroidUtilities.updateVisibleRows(this.listView);
    L11:
        if (this.fullscreenUsersListView.getVisibility() != 0) goto L13;
        AndroidUtilities.updateVisibleRows(this.fullscreenUsersListView);
    L13:
        this.attachedRenderersTmp.clear();
        this.attachedRenderersTmp.addAll(this.attachedRenderers);
        int r1 = 0;
    L15:
        if (r1 >= this.attachedRenderersTmp.size()) goto L17;
        this.attachedRenderersTmp.get(r1).updateAttachState(true);
        r1 = r1 + 1;
        goto L15
    L17:
        return;
    L5:
        if (VoIPService.getSharedInstance().isMicMute() == false) goto L8;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        PrivateVideoPreviewDialog r0 = this.previewDialog;
        if (r0 == null) goto L7;
        r0.dismiss(false, false);
        return;
    L7:
        if (this.avatarsPreviewShowed == false) goto L11;
        dismissAvatarPreview(true);
        return;
    L11:
        if (this.renderersContainer.inFullscreenMode == false) goto L14;
        fullscreenFor(null);
        return;
    L14:
        super.onBackPressed();
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onCameraSwitch(boolean r3) {
        this.attachedRenderersTmp.clear();
        this.attachedRenderersTmp.addAll(this.attachedRenderers);
        int r32 = 0;
    L4:
        if (r32 >= this.attachedRenderersTmp.size()) goto L6;
        this.attachedRenderersTmp.get(r32).updateAttachState(true);
        r32 = r32 + 1;
        goto L4
    L6:
        PrivateVideoPreviewDialog r2 = this.previewDialog;
        if (r2 == null) goto L11;
        r2.update();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onCreate(Bundle r1) {
        super.onCreate(r1);
        this.parentActivity.addOnUserLeaveHintListener(this.onUserLeaveHintListener);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean onCustomOpenAnimation() {
        groupCallUiVisible = true;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.groupCallVisibilityChanged, new Object[0]);
        GroupCallPip.updateVisibility(getContext());
        return super.onCustomOpenAnimation();
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 2) goto L6;
        checkGroupCallUiPositions_MessagesList();
        checkGroupCallUiPositions_ButtonsList();
        checkGroupCallUiPositions_FullscreenUsersList();
        checkGroupCallUiAlpha_ButtonsList();
        this.renderersContainer.setProgressToHideUi(r2);
        this.fullscreenUsersListView.invalidate();
        this.containerView.invalidate();
        this.buttonsContainer.invalidate();
    L6:
        if (r1 != 3) goto L9;
        checkGroupCallUiPositions_ReactionsLayout();
        checkGroupCallUiPositions_MessagesList();
        checkGroupCallUiPositions_SendButton();
        checkGroupCallUiAlpha_ReactionsLayout();
        this.containerView.invalidate();
    L9:
        if (r1 != 4) goto L12;
        checkGroupCallUiPositions_MessagesList();
        this.callMessageEnterUnderContainer.invalidate();
        this.callMessageEnterView.invalidate();
    L12:
        if (r1 != 5) goto L15;
        checkGroupCallUiPositions_MessagesList();
        this.containerView.invalidate();
        return;
    }

    public void onPause() {
        paused = true;
        this.attachedRenderersTmp.clear();
        this.attachedRenderersTmp.addAll(this.attachedRenderers);
        int r1 = 0;
    L4:
        if (r1 >= this.attachedRenderersTmp.size()) goto L6;
        this.attachedRenderersTmp.get(r1).updateAttachState(false);
        r1 = r1 + 1;
        goto L4
    L6:
        this.sizeNotifierFrameLayout.onPause();
        this.callMessageEnterView.onPause();
    }

    public void onResume() {
        int r0 = 0;
        paused = false;
        this.listAdapter.notifyDataSetChanged();
        if (this.fullscreenUsersListView.getVisibility() != 0) goto L6;
        this.fullscreenAdapter.update(false, this.fullscreenUsersListView);
    L6:
        if (isTabletMode == false) goto L8;
        this.tabletGridAdapter.update(false, this.tabletVideoGridView);
    L8:
        this.attachedRenderersTmp.clear();
        this.attachedRenderersTmp.addAll(this.attachedRenderers);
    L10:
        if (r0 >= this.attachedRenderersTmp.size()) goto L12;
        this.attachedRenderersTmp.get(r0).updateAttachState(true);
        r0 = r0 + 1;
        goto L10
    L12:
        this.sizeNotifierFrameLayout.onResume();
        this.callMessageEnterView.onResume();
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onStateChanged(int r2) {
        this.currentCallState = r2;
        updateState(isShowing(), false);
    }

    public void openShareConferenceLink() {
        ChatObject.Call r0 = this.call;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.call == null) goto L10;
        AlertDialog r02 = new AlertDialog(getContext(), 3);
        r02.showDelayed(300);
        TL_phone.exportGroupCallInvite r1 = new TL_phone.exportGroupCallInvite();
        TLRPC.TL_inputGroupCall r2 = new TLRPC.TL_inputGroupCall();
        r1.call = r2;
        TLRPC.GroupCall r3 = this.call.call;
        r2.id = r3.id;
        r2.access_hash = r3.access_hash;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new GroupCallActivity$$ExternalSyntheticLambda65(this, r02, r1));
        return;
    }

    public void setAudioOutputValue(int r2) {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setAudioOutput(r2);
        this.cacheAudioOutputValue = Integer.valueOf(r2);
    }

    public void setOldRows(int r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10, int r11, int r12, int r13, int r14, int r15, int r16) {
        this.oldEncryptionRow = r1;
        this.oldAddMemberRow = r2;
        this.oldUsersStartRow = r3;
        this.oldUsersEndRow = r4;
        this.oldInvitedStartRow = r5;
        this.oldInvitedEndRow = r6;
        this.oldShadyJoinStartRow = r7;
        this.oldShadyJoinEndRow = r8;
        this.oldShadyLeftStartRow = r9;
        this.oldShadyLeftEndRow = r10;
        this.oldUsersVideoStartRow = r11;
        this.oldUsersVideoEndRow = r12;
        this.oldVideoDividerRow = r13;
        this.oldVideoNotAvailableRow = r14;
        this.oldConferenceAddPeopleRow = r15;
        this.oldConferenceShareLinkRow = r16;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        if (RTMPStreamPipOverlay.isVisible() == false) goto L6;
        RTMPStreamPipOverlay.dismiss();
        return;
    }
}
