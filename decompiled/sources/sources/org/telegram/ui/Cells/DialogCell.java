package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ReplacementSpan;
import android.text.style.StyleSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.collection.LongSparseArray;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.AvatarCornerType;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import me.vkryl.android.animator.BoolAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ChatThemeController;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.DialogsAdapter;
import org.telegram.ui.AvatarSpan;
import org.telegram.ui.Cells.ShareDialogCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BubbleCounterPath;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CanvasButton;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.DialogCellTags;
import org.telegram.ui.Components.EmptyStubSpan;
import org.telegram.ui.Components.ForegroundColorSpanThemable;
import org.telegram.ui.Components.Forum.ForumBubbleDrawable;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.PhotoBubbleClip;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.PullForegroundDrawable;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.ScamDrawable;
import org.telegram.ui.Components.StaticLayoutEx;
import org.telegram.ui.Components.StatusDrawable;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TimerDrawable;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.URLSpanMono;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.URLSpanNoUnderlineBold;
import org.telegram.ui.Components.VectorAvatarThumbDrawable;
import org.telegram.ui.Components.dialogs.DialogMediaIconsHelper;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.FilterCreateActivity;
import org.telegram.ui.RightSlidingDialogContainer;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.StoriesListPlaceProvider;
import org.telegram.ui.Stories.StoriesUtilities;
import org.telegram.ui.Stories.StoryViewer;
import org.telegram.ui.community.CommunityArrowDrawable;
import org.telegram.ui.community.CommunitySheet;

/* loaded from: classes3.dex */
public class DialogCell extends BaseCell implements StoriesListPlaceProvider.AvatarOverlaysView, Theme.Colorable {
    private int[] adaptiveEmojiColor;
    private ColorFilter[] adaptiveEmojiColorFilter;
    public int addForumHeightForTags;
    public int addHeightForTags;
    private boolean allowBotOpenButton;
    private int animateFromStatusDrawableParams;
    private int animateToStatusDrawableParams;
    private AnimatedEmojiSpan.EmojiGroupedSpans animatedEmojiStack;
    private AnimatedEmojiSpan.EmojiGroupedSpans animatedEmojiStack2;
    private AnimatedEmojiSpan.EmojiGroupedSpans animatedEmojiStack3;
    private AnimatedEmojiSpan.EmojiGroupedSpans animatedEmojiStackName;
    private boolean animatingArchiveAvatar;
    private float animatingArchiveAvatarProgress;
    private BoolAnimator animatorPollVotesMentionVisible;
    private boolean applyName;
    private float archiveBackgroundProgress;
    private GradientDrawable archiveFadeGradientDrawable;
    private int archiveFadeGradientDrawableColor;
    private boolean archiveHidden;
    protected PullForegroundDrawable archivedChatsDrawable;
    private boolean attachedToWindow;
    private AvatarDrawable avatarDrawable;
    public ImageReceiver avatarImage;
    public int avatarStart;
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable botVerification;
    private final View botVerificationView;
    private int bottomClip;
    private PhotoBubbleClip bubbleClip;
    private Paint buttonBackgroundPaint;
    private boolean buttonCreated;
    private StaticLayout buttonLayout;
    private int buttonLeft;
    private int buttonTop;
    CanvasButton canvasButton;
    public boolean channelShouldUseLineWidth;
    private TLRPC.Chat chat;
    private float chatCallProgress;
    protected CheckBox2 checkBox;
    private int checkDrawLeft;
    private int checkDrawLeft1;
    private int checkDrawTop;
    public float chekBoxPaddingTop;
    private boolean clearingDialog;
    private float clipProgress;
    private int clockDrawLeft;
    public float collapseOffset;
    public boolean collapsed;
    private CommunityArrowDrawable communityArrowDrawable;
    private float cornerProgress;
    private StaticLayout countAnimationInLayout;
    private boolean countAnimationIncrement;
    private StaticLayout countAnimationStableLayout;
    private ValueAnimator countAnimator;
    private float countChangeProgress;
    private StaticLayout countLayout;
    private int countLeft;
    private int countLeftOld;
    private StaticLayout countOldLayout;
    private int countTop;
    private int countWidth;
    private int countWidthOld;
    private Paint counterPaintOutline;
    private Path counterPath;
    private RectF counterPathRect;
    private int currentAccount;
    private long currentDialogCommunityId;
    private int currentDialogFolderDialogsCount;
    private int currentDialogFolderId;
    private long currentDialogId;
    private int currentEditDate;
    private TextPaint currentMessagePaint;
    private float currentRevealBounceProgress;
    private float currentRevealProgress;
    private CustomDialog customDialog;
    private String customMessage;
    DialogCellDelegate delegate;
    private boolean dialogMuted;
    private float dialogMutedProgress;
    private int dialogsType;
    private TLRPC.DraftMessage draftMessage;
    private boolean draftVoice;
    public boolean drawArchive;
    public boolean drawAvatar;
    public boolean drawAvatarSelector;
    private boolean drawBadge;
    private boolean drawBotVerified;
    private boolean drawCheck1;
    private boolean drawCheck2;
    private boolean drawClock;
    private boolean drawCommunityAvatar;
    private boolean drawCount;
    private boolean drawCount2;
    private boolean drawError;
    private boolean drawForwardIcon;
    private boolean drawGiftIcon;
    private boolean drawMention;
    public boolean drawMonoforumAvatar;
    private boolean drawNameLock;
    private boolean drawPin;
    private boolean drawPinBackground;
    private boolean drawPinForced;
    private boolean[] drawPlay;
    private boolean drawPollVotesMention;
    private boolean drawPremium;
    private boolean drawReactionMention;
    private boolean drawReorder;
    private boolean drawRevealBackground;
    private int drawScam;
    private boolean[] drawSpoiler;
    private boolean drawUnmute;
    private boolean drawVerified;
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable emojiStatus;
    private final View emojiStatusView;
    private TLRPC.EncryptedChat encryptedChat;
    private int errorLeft;
    private int errorTop;
    private Paint fadePaint;
    private Paint fadePaintBack;
    private int folderId;
    protected boolean forbidDraft;
    protected boolean forbidVerified;
    private ForumFormattedNames forumFormattedNames;
    public TLRPC.TL_forumTopic forumTopic;
    public boolean fullSeparator;
    public boolean fullSeparator2;
    private ArrayList<MessageObject> groupMessages;
    private int halfCheckDrawLeft;
    private boolean hasCall;
    private boolean hasNameInMessage;
    private boolean hasUnmutedCommunityDialogs;
    private boolean hasUnmutedTopics;
    private boolean hasVideoThumb;
    public int heightDefault;
    public int heightThreeLines;
    public boolean inPreviewMode;
    private float innerProgress;
    public boolean insideCommunityList;
    public boolean insideCommunityListNoDialog;
    private BounceInterpolator interpolator;
    public boolean isDialogCell;
    public boolean isForChannelSubscriberCell;
    private boolean isForum;
    public boolean isHiddenInCommunity;
    public boolean isMonoForumTopicDialog;
    public boolean isSavedDialog;
    public boolean isSavedDialogCell;
    private boolean isSelected;
    private boolean isShareToStoryCell;
    private boolean isSliding;
    private boolean isTopic;
    public boolean isTransitionSupport;
    long lastDialogChangedTime;
    private int lastDrawSwipeMessageStringId;
    private RLottieDrawable lastDrawTranslationDrawable;
    private int lastMessageDate;
    private CharSequence lastMessageString;
    private CharSequence lastPrintString;
    private int lastSendState;
    int lastSize;
    private int lastStatusDrawableParams;
    private boolean lastTopicMessageUnread;
    private boolean lastUnreadState;
    private int lock2Left;
    private Drawable lockDrawable;
    private boolean markUnread;
    private int mentionCount;
    private StaticLayout mentionLayout;
    private int mentionLeft;
    private int mentionWidth;
    private MessageObject message;
    private final AvatarSpan messageAvatarSpan;
    private int messageId;
    private StaticLayout messageLayout;
    private int messageLeft;
    private StaticLayout messageNameLayout;
    private int messageNameLeft;
    private int messageNameTop;
    public int messagePaddingStart;
    private int messageTop;
    boolean moving;
    public int nameAdditionalsForChannelSubscriber;
    private boolean nameIsEllipsized;
    public StaticLayout nameLayout;
    private boolean nameLayoutEllipsizeByGradient;
    private boolean nameLayoutEllipsizeLeft;
    private boolean nameLayoutFits;
    public float nameLayoutTranslateX;
    public int nameLeft;
    private int nameLockLeft;
    private int nameLockTop;
    private int nameMuteLeft;
    private int nameMutedIconLeft;
    public int namePaddingEnd;
    public int nameWidth;
    private boolean needEmoji;
    private Utilities.Callback<TLRPC.User> onOpenButtonClick;
    private float onlineProgress;
    private boolean openBot;
    private final Paint openButtonBackgroundPaint;
    private final ButtonBounce openButtonBounce;
    private final RectF openButtonRect;
    private Text openButtonText;
    protected boolean overrideSwipeAction;
    protected int overrideSwipeActionBackgroundColorKey;
    protected RLottieDrawable overrideSwipeActionDrawable;
    protected int overrideSwipeActionRevealBackgroundColorKey;
    protected int overrideSwipeActionStringId;
    protected String overrideSwipeActionStringKey;
    private int paintIndex;
    private DialogsActivity parentFragment;
    private int pinLeft;
    private int pinTop;
    private int pollVotesMentionCount;
    private int pollVotesMentionLeft;
    private DialogsAdapter.DialogsPreloader preloader;
    private boolean premiumBlocked;
    private final AnimatedFloat premiumBlockedT;
    private PremiumGradient.PremiumGradientTools premiumGradient;
    private int printingStringType;
    private int progressStage;
    private boolean promoDialog;
    private int reactionMentionCount;
    private int reactionMentionLeft;
    private ValueAnimator reactionsMentionsAnimator;
    private float reactionsMentionsChangeProgress;
    private int readOutboxMaxId;
    private RectF rect;
    private int reorderGradientLastColor;
    private Paint reorderGradientPaint;
    private float reorderIconProgress;
    public ShareDialogCell.RepostStoryDrawable repostStoryDrawable;
    private final Theme.ResourcesProvider resourcesProvider;
    public float rightFragmentOffset;
    private float rightFragmentOpenedProgress;
    private boolean showTopicIconInName;
    private boolean showTtl;
    private List<SpoilerEffect> spoilers;
    private List<SpoilerEffect> spoilers2;
    private Stack<SpoilerEffect> spoilersPool;
    private Stack<SpoilerEffect> spoilersPool2;
    private Drawable starBg;
    private int starBgColor;
    private Drawable starFg;
    private final AnimatedFloat starsBlockedT;
    private long starsPriceBlocked;
    private boolean statusDrawableAnimationInProgress;
    private ValueAnimator statusDrawableAnimator;
    private int statusDrawableLeft;
    private float statusDrawableProgress;
    public final StoriesUtilities.AvatarStoryParams storyParams;
    public boolean swipeCanceled;
    private int swipeMessageTextId;
    private StaticLayout swipeMessageTextLayout;
    private int swipeMessageWidth;
    public DialogCellTags tags;
    private int tagsLeft;
    private int tagsRight;
    private Paint thumbBackgroundPaint;
    private ImageReceiver[] thumbImage;
    private boolean[] thumbImageSeen;
    private Path thumbPath;
    int thumbSize;
    private SpoilerEffect thumbSpoiler;
    private int thumbsCount;
    private StaticLayout timeLayout;
    private int timeLeft;
    private int timeTop;
    private TimerDrawable timerDrawable;
    private Paint timerPaint;
    private Paint timerPaint2;
    private String titleOverride;
    private int topClip;
    int topMessageTopicEndIndex;
    int topMessageTopicStartIndex;
    private Paint topicCounterPaint;
    protected Drawable[] topicIconInName;
    private boolean topicMuted;
    protected int translateY;
    private boolean translationAnimationStarted;
    private RLottieDrawable translationDrawable;
    protected float translationX;
    private int ttlPeriod;
    private float ttlProgress;
    private boolean twoLinesForName;
    private StaticLayout typingLayout;
    private int typingLeft;
    private int unreadCount;
    private Runnable unsubscribePremiumBlocked;
    private final DialogUpdateHelper updateHelper;
    private boolean updateLayout;
    public boolean useForceThreeLines;
    public boolean useFromUserAsAvatar;
    private boolean useMeForMyMessages;
    public boolean useSeparator;
    private TLRPC.User user;
    private boolean visibleOnScreen;
    private boolean wasDrawnOnline;
    protected float xOffset;

    /* renamed from: org.telegram.ui.Cells.DialogCell$1, reason: invalid class name */
    public class AnonymousClass1 extends StoriesUtilities.AvatarStoryParams {
        final /* synthetic */ DialogCell this$0;

        public AnonymousClass1(DialogCell r1, boolean r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Stories.StoriesUtilities.AvatarStoryParams
        public boolean isAvatarClickable(long r3, TLRPC.Chat r5, TLRPC.User r6) {
            if (r5 != null) goto L5;
        L6:
            if (r6 != null) goto L8;
            return false;
        L8:
            if (r6.linked_community_id != 0) goto L10;
            return false;
        L10:
            if (this.this$0.insideCommunityList == true) goto L16;
            return true;
        L16:
            return false;
        L5:
            if (r5.linked_community_id != 0) goto L10;
            goto L6
        }

        @Override // org.telegram.ui.Stories.StoriesUtilities.AvatarStoryParams
        public boolean onAvatarClick(View r8, long r9) {
            if (DialogCell.m6903$$Nest$fgetparentFragment(this.this$0) == null) goto L21;
            DialogCell r0 = this.this$0;
            if (r0.insideCommunityList == true) goto L21;
            if (r9 <= 0) goto L14;
            TLRPC.User r02 = MessagesController.getInstance(DialogCell.m6891$$Nest$fgetcurrentAccount(r0)).getUser(Long.valueOf(r9));
            if (r02 == null) goto L21;
            if (r02.linked_community_id == 0) goto L21;
            DialogCell.m6903$$Nest$fgetparentFragment(this.this$0).showDialog(new CommunitySheet(DialogCell.m6903$$Nest$fgetparentFragment(this.this$0), r02.linked_community_id));
            return true;
        L14:
            TLRPC.Chat r03 = MessagesController.getInstance(DialogCell.m6891$$Nest$fgetcurrentAccount(r0)).getChat(Long.valueOf(-r9));
            if (r03 == null) goto L21;
            if (r03.linked_community_id == 0) goto L21;
            DialogCell.m6903$$Nest$fgetparentFragment(this.this$0).showDialog(new CommunitySheet(DialogCell.m6903$$Nest$fgetparentFragment(this.this$0), r03.linked_community_id));
            return true;
        L21:
            return super.onAvatarClick(r8, r9);
        }

        @Override // org.telegram.ui.Stories.StoriesUtilities.AvatarStoryParams
        public void onLongPress() {
            DialogCell r1 = this.this$0;
            DialogCellDelegate r0 = r1.delegate;
            if (r0 != null) goto L5;
            return;
        L5:
            r0.showChatPreview(r1);
        }

        @Override // org.telegram.ui.Stories.StoriesUtilities.AvatarStoryParams
        public void openStory(long r1, Runnable r3) {
            DialogCell r12 = this.this$0;
            if (r12.delegate == null) goto L14;
            int r13 = DialogCell.m6892$$Nest$fgetcurrentDialogFolderId(r12);
            DialogCell r0 = this.this$0;
            if (r13 == 0) goto L9;
            r0.delegate.openHiddenStories();
            return;
        L9:
            DialogCellDelegate r14 = r0.delegate;
            if (r14 == null) goto L13;
            r14.openStory(r0, r3);
            return;
        L13:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Cells.DialogCell$2, reason: invalid class name */
    public class AnonymousClass2 extends View {
        final /* synthetic */ DialogCell this$0;

        public AnonymousClass2(DialogCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            DialogCell.m6900$$Nest$fgetemojiStatus(this.this$0).setBounds(0, 0, getWidth(), getHeight());
            DialogCell.m6900$$Nest$fgetemojiStatus(this.this$0).draw(r5);
        }
    }

    /* renamed from: org.telegram.ui.Cells.DialogCell$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends View {
        final /* synthetic */ DialogCell this$0;

        public AnonymousClass3(DialogCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            DialogCell.m6889$$Nest$fgetbotVerification(this.this$0).setBounds(0, 0, getWidth(), getHeight());
            DialogCell.m6889$$Nest$fgetbotVerification(this.this$0).draw(r5);
        }
    }

    /* renamed from: org.telegram.ui.Cells.DialogCell$4, reason: invalid class name */
    public class AnonymousClass4 extends CheckBox2 {
        final /* synthetic */ DialogCell this$0;

        public AnonymousClass4(DialogCell r1, Context r2, int r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Cells.DialogCell$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ DialogCell this$0;

        public AnonymousClass5(DialogCell r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            DialogCell.m6907$$Nest$fputcountChangeProgress(this.this$0, 1.0f);
            DialogCell.m6908$$Nest$fputcountOldLayout(this.this$0, null);
            DialogCell.m6906$$Nest$fputcountAnimationStableLayout(this.this$0, null);
            DialogCell.m6905$$Nest$fputcountAnimationInLayout(this.this$0, null);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Cells.DialogCell$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ DialogCell this$0;

        public AnonymousClass6(DialogCell r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            DialogCell.m6910$$Nest$fputreactionsMentionsChangeProgress(this.this$0, 1.0f);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Cells.DialogCell$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ DialogCell this$0;

        public AnonymousClass7(DialogCell r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            boolean r42 = DialogCell.m6898$$Nest$fgetdrawClock(this.this$0);
            if (DialogCell.m6896$$Nest$fgetdrawCheck1(this.this$0) == false) goto L5;
            int r0 = 2;
        L6:
            int r43 = (r42 ? 1 : 0) + r0;
            if (DialogCell.m6897$$Nest$fgetdrawCheck2(this.this$0) == false) goto L9;
            int r02 = 4;
        L10:
            int r44 = r43 + r02;
            int r03 = DialogCell.m6888$$Nest$fgetanimateToStatusDrawableParams(this.this$0);
            DialogCell r2 = this.this$0;
            if (r03 == r44) goto L13;
            DialogCell.m6912$$Nest$mcreateStatusDrawableAnimator(r2, DialogCell.m6888$$Nest$fgetanimateToStatusDrawableParams(r2), r44);
        L14:
            this.this$0.invalidate();
            return;
        L13:
            DialogCell.m6911$$Nest$fputstatusDrawableAnimationInProgress(r2, false);
            DialogCell r45 = this.this$0;
            DialogCell.m6909$$Nest$fputlastStatusDrawableParams(r45, DialogCell.m6888$$Nest$fgetanimateToStatusDrawableParams(r45));
            goto L14
        L9:
            r02 = 0;
            goto L10
        L5:
            r0 = 0;
            goto L6
        }
    }

    public static class BounceInterpolator implements Interpolator {
        public BounceInterpolator() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float r4) {
            if (r4 < 0.33f) goto L5;
            float r42 = r4 - 0.33f;
            if (r42 >= 0.33f) goto L11;
            return 0.1f - ((r42 / 0.34f) * 0.15f);
        L11:
            return (((r42 - 0.34f) / 0.33f) * 0.05f) - 0.05f;
        L5:
            return (r4 / 0.33f) * 0.1f;
        }
    }

    public static class CustomDialog {
        public int date;
        public int id;
        public boolean isMedia;
        public String message;
        public boolean muted;
        public String name;
        public boolean pinned;
        public int sent;
        public int type;
        public int unread_count;
        public boolean verified;

        public CustomDialog() {
            this.sent = -1;
        }
    }

    public interface DialogCellDelegate {
        boolean canClickButtonInside();

        void onButtonClicked(DialogCell r1);

        void onButtonLongPress(DialogCell r1);

        void openHiddenStories();

        void openStory(DialogCell r1, Runnable r2);

        void showChatPreview(DialogCell r1);
    }

    public class DialogUpdateHelper {
        public long lastDrawnDialogId;
        public boolean lastDrawnDialogIsFolder;
        public int lastDrawnDraftHash;
        public boolean lastDrawnHasCall;
        public long lastDrawnMessageId;
        public boolean lastDrawnPinned;
        public Integer lastDrawnPrintingType;
        public long lastDrawnReadState;
        public int lastDrawnSizeHash;
        public boolean lastDrawnTranslated;
        public int lastKnownTypingType;
        public int lastTopicsCount;
        long startWaitingTime;
        final /* synthetic */ DialogCell this$0;
        public boolean typingOutToTop;
        public float typingProgres;
        boolean waitngNewMessageFroTypingAnimation;

        private DialogUpdateHelper(DialogCell r1) {
            this.this$0 = r1;
            this.waitngNewMessageFroTypingAnimation = false;
        }

        public /* synthetic */ DialogUpdateHelper(DialogCell r1, DialogCellIA r2) {
            this(r1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v3 */
        /* JADX WARN: Type inference failed for: r10v5, types: [int] */
        /* JADX WARN: Type inference failed for: r10v6 */
        /* JADX WARN: Type inference failed for: r4v7, types: [int] */
        /* JADX WARN: Type inference failed for: r8v10, types: [int] */
        /* JADX WARN: Type inference failed for: r8v34 */
        /* JADX WARN: Type inference failed for: r8v9 */
        public boolean update() {
            TLRPC.Dialog r1 = MessagesController.getInstance(DialogCell.m6891$$Nest$fgetcurrentAccount(this.this$0)).dialogs_dict.get(DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0));
            DialogCell r2 = this.this$0;
            if (r1 != null) goto L12;
            if (DialogCell.m6894$$Nest$fgetdialogsType(r2) == 3) goto L7;
        L10:
            return false;
        L7:
            if (this.lastDrawnDialogId == DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0)) goto L10;
            this.lastDrawnDialogId = DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0);
            return true;
        L12:
            if (DialogCell.m6902$$Nest$fgetmessage(r2) != null) goto L14;
            int r22 = 0;
        L15:
            long r5 = r1.read_inbox_max_id + (r1.read_outbox_max_id << 8);
            int r7 = r1.unread_count;
            if (r1.unread_mark == false) goto L18;
            int r8 = -1;
        L19:
            long r52 = r5 + ((r7 + r8) << 16);
            if (r1.unread_reactions_count <= 0) goto L22;
            int r72 = 262144;
        L23:
            long r53 = r52 + r72;
            if (r1.unread_mentions_count <= 0) goto L26;
            int r73 = 524288;
        L27:
            long r54 = r53 + r73;
            if (r1.unread_poll_votes_count <= 0) goto L30;
            int r74 = TLObject.FLAG_21;
        L31:
            long r55 = r54 + r74;
            if (this.this$0.isForumCell() == false) goto L40;
            int[] r75 = MessagesController.getInstance(DialogCell.m6891$$Nest$fgetcurrentAccount(this.this$0)).getTopicsController().getForumUnreadCount(-DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0));
            if (r75[2] <= 0) goto L37;
            r55 = r55 | 1048576;
        L37:
            if (r75[4] <= 0) goto L40;
            r55 = r55 | 4194304;
        L40:
            if (this.this$0.isForumCell() == true) goto L48;
            DialogCell r76 = this.this$0;
            if (r76.isDialogCell == true) goto L46;
            if (DialogCell.m6901$$Nest$fgetisTopic(r76) == false) goto L48;
        L46:
            if (TextUtils.isEmpty(MessagesController.getInstance(DialogCell.m6891$$Nest$fgetcurrentAccount(this.this$0)).getPrintingString(DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0), DialogCell.m6913$$Nest$mgetTopicId(this.this$0), true)) == true) goto L48;
            Integer r77 = MessagesController.getInstance(DialogCell.m6891$$Nest$fgetcurrentAccount(this.this$0)).getPrintingStringType(DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0), DialogCell.m6913$$Nest$mgetTopicId(this.this$0));
        L49:
            int r11 = this.this$0.getMeasuredWidth() + (this.this$0.getMeasuredHeight() << 16);
            if (this.this$0.isForumCell() == false) goto L58;
            ArrayList<TLRPC.TL_forumTopic> r12 = MessagesController.getInstance(DialogCell.m6891$$Nest$fgetcurrentAccount(this.this$0)).getTopicsController().getTopics(-DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0));
            if (r12 != null) goto L54;
            int r122 = -1;
        L55:
            if (r122 == (-1)) goto L57;
        L59:
            boolean r9 = DialogCell.m6901$$Nest$fgetisTopic(this.this$0);
            DialogCell r13 = this.this$0;
            if (r9 == false) goto L73;
            boolean r17 = false;
            if (MediaDataController.getInstance(DialogCell.m6891$$Nest$fgetcurrentAccount(r13)).getDraftVoice(DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0), DialogCell.m6913$$Nest$mgetTopicId(this.this$0)) == null) goto L64;
            boolean r3 = true;
        L65:
            if (r3 == true) goto L67;
            TLRPC.DraftMessage r4 = MediaDataController.getInstance(DialogCell.m6891$$Nest$fgetcurrentAccount(this.this$0)).getDraft(DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0), DialogCell.m6913$$Nest$mgetTopicId(this.this$0));
        L68:
            if (r4 != null) goto L70;
        L72:
            TLRPC.DraftMessage r82 = r4;
        L82:
            if (r82 != null) goto L84;
            boolean r42 = r17;
        L90:
            if (DialogCell.m6890$$Nest$fgetchat(this.this$0) != null) goto L92;
        L96:
            boolean r83 = r17;
        L97:
            boolean r92 = MessagesController.getInstance(DialogCell.m6891$$Nest$fgetcurrentAccount(this.this$0)).getTranslateController().isTranslatingDialog(DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0));
            if (this.lastDrawnSizeHash != r11) goto L123;
            if (this.lastDrawnMessageId != r22) goto L123;
            if (this.lastDrawnTranslated != r92) goto L123;
            if (this.lastDrawnDialogId != DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0)) goto L123;
            if (this.lastDrawnDialogIsFolder != r1.isFolder) goto L123;
            if (this.lastDrawnReadState != r55) goto L123;
            if (Objects.equals(this.lastDrawnPrintingType, r77) == false) goto L123;
            if (this.lastTopicsCount != r122) goto L123;
            if (r42 != this.lastDrawnDraftHash) goto L123;
            if (this.lastDrawnPinned != DialogCell.m6899$$Nest$fgetdrawPin(this.this$0)) goto L123;
            if (this.lastDrawnHasCall != r83) goto L123;
            if (DialogCell.m6895$$Nest$fgetdraftVoice(this.this$0) != r3) goto L123;
            return r17;
        L123:
            if (this.lastDrawnDialogId == DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0)) goto L130;
            if (r77 != null) goto L126;
            float r32 = 0.0f;
        L127:
            this.typingProgres = r32;
            this.waitngNewMessageFroTypingAnimation = r17;
        L128:
            ?? r10 = r42;
        L146:
            if (r77 == null) goto L148;
            this.lastKnownTypingType = r77.intValue();
        L148:
            this.lastDrawnDialogId = DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0);
            this.lastDrawnMessageId = r22;
            this.lastDrawnDialogIsFolder = r1.isFolder;
            this.lastDrawnReadState = r55;
            this.lastDrawnPrintingType = r77;
            this.lastDrawnSizeHash = r11;
            this.lastDrawnDraftHash = r10;
            this.lastTopicsCount = r122;
            this.lastDrawnPinned = DialogCell.m6899$$Nest$fgetdrawPin(this.this$0);
            this.lastDrawnHasCall = r83;
            this.lastDrawnTranslated = r92;
            return true;
        L126:
            r32 = 1.0f;
            goto L127
        L130:
            if (Objects.equals(this.lastDrawnPrintingType, r77) == true) goto L132;
        L133:
            boolean r33 = this.waitngNewMessageFroTypingAnimation;
            if (r33 == true) goto L138;
            if (r77 != null) goto L138;
            this.waitngNewMessageFroTypingAnimation = true;
            this.startWaitingTime = System.currentTimeMillis();
        L142:
            r10 = r42;
            if (this.lastDrawnMessageId == r22) goto L145;
            this.typingOutToTop = false;
            goto L146
        L145:
            this.typingOutToTop = true;
        L138:
            if (r33 == false) goto L142;
            if (this.lastDrawnMessageId == r22) goto L142;
            this.waitngNewMessageFroTypingAnimation = false;
            goto L142
        L132:
            if (this.waitngNewMessageFroTypingAnimation == false) goto L128;
        L92:
            if (DialogCell.m6890$$Nest$fgetchat(this.this$0).call_active == false) goto L96;
            if (DialogCell.m6890$$Nest$fgetchat(this.this$0).call_not_empty == false) goto L96;
            r83 = true;
            goto L97
        L84:
            int r43 = r82.message.hashCode();
            TLRPC.InputReplyTo r84 = r82.reply_to;
            if (r84 == null) goto L87;
            ?? r85 = r84.reply_to_msg_id << 16;
        L88:
            r42 = r43 + r85;
            goto L90
        L87:
            r85 = r17;
            goto L88
        L70:
            if (TextUtils.isEmpty(r4.message) == false) goto L72;
        L71:
            r82 = null;
            goto L82
        L67:
            r4 = null;
            goto L68
        L64:
            r3 = false;
            goto L65
        L73:
            r17 = false;
            if (r13.isDialogCell == true) goto L76;
            r3 = false;
            goto L71
        L76:
            if (MediaDataController.getInstance(DialogCell.m6891$$Nest$fgetcurrentAccount(r13)).getDraftVoice(DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0), 0) == null) goto L78;
            r3 = true;
        L79:
            if (r3 == true) goto L71;
            r82 = MediaDataController.getInstance(DialogCell.m6891$$Nest$fgetcurrentAccount(this.this$0)).getDraft(DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0), 0);
            goto L82
        L78:
            r3 = false;
            goto L79
        L57:
            if (MessagesController.getInstance(DialogCell.m6891$$Nest$fgetcurrentAccount(this.this$0)).getTopicsController().endIsReached(-DialogCell.m6893$$Nest$fgetcurrentDialogId(this.this$0)) == false) goto L59;
        L54:
            r122 = r12.size();
        L58:
            r122 = 0;
        L48:
            r77 = null;
            goto L49
        L30:
            r74 = 0;
            goto L31
        L26:
            r73 = 0;
            goto L27
        L22:
            r72 = 0;
            goto L23
        L18:
            r8 = 0;
            goto L19
        L14:
            r22 = DialogCell.m6902$$Nest$fgetmessage(this.this$0).getId() + DialogCell.m6902$$Nest$fgetmessage(this.this$0).hashCode();
            goto L15
        }

        public void updateAnimationValues() {
            if (this.waitngNewMessageFroTypingAnimation == true) goto L19;
            if (this.lastDrawnPrintingType == null) goto L12;
            if (DialogCell.m6904$$Nest$fgettypingLayout(this.this$0) == null) goto L12;
            float r0 = this.typingProgres;
            if (r0 == 1.0f) goto L12;
            this.typingProgres = r0 + 0.08f;
            this.this$0.invalidate();
        L16:
            this.typingProgres = Utilities.clamp(this.typingProgres, 1.0f, 0.0f);
            return;
        L12:
            if (this.lastDrawnPrintingType != null) goto L16;
            float r02 = this.typingProgres;
            if (r02 == 0.0f) goto L16;
            this.typingProgres = r02 - 0.08f;
            this.this$0.invalidate();
            goto L16
        L19:
            if ((System.currentTimeMillis() - this.startWaitingTime) <= 100) goto L21;
            this.waitngNewMessageFroTypingAnimation = false;
        L21:
            this.this$0.invalidate();
        }
    }

    public static class FixedWidthSpan extends ReplacementSpan {
        private int width;

        public FixedWidthSpan(int r1) {
            this.width = r1;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            if (r5 != null) goto L4;
            r5 = r1.getFontMetricsInt();
        L4:
            if (r5 == null) goto L7;
            int r12 = 1 - (r5.descent - r5.ascent);
            r5.descent = r12;
            r5.bottom = r12;
            r5.ascent = -1;
            r5.top = -1;
        L7:
            return this.width;
        }
    }

    public static class ForumFormattedNames {
        HashMap<Long, AvatarSpan> avatarSpans;
        CharSequence formattedNames;
        boolean isLoadingState;
        int lastMessageId;
        boolean lastTopicMessageUnread;
        private final DialogCell parent;
        int topMessageTopicEndIndex;
        int topMessageTopicStartIndex;

        public static /* synthetic */ int $r8$lambda$uICq3jFQRNqxUSEcmTlTge6AEXo(TLRPC.TL_forumTopic r0) {
            return -r0.top_message;
        }

        /* renamed from: -$$Nest$mformatTopicsNames, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m6914$$Nest$mformatTopicsNames(ForumFormattedNames r0, int r1, MessageObject r2, TLRPC.Chat r3) {
            r0.formatTopicsNames(r1, r2, r3);
        }

        /* renamed from: -$$Nest$minvalidateSpans, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m6915$$Nest$minvalidateSpans(ForumFormattedNames r0) {
            r0.invalidateSpans();
        }

        public ForumFormattedNames(DialogCell r1) {
            this.parent = r1;
        }

        private void formatTopicsNames(int r17, MessageObject r18, TLRPC.Chat r19) {
            if (r18 == null) goto L7;
            if (r19 == null) goto L7;
            int r5 = r18.getId();
        L9:
            if (this.lastMessageId == r5) goto L11;
        L13:
            this.avatarSpans = null;
            this.topMessageTopicStartIndex = 0;
            this.topMessageTopicEndIndex = 0;
            this.lastTopicMessageUnread = false;
            this.isLoadingState = false;
            this.lastMessageId = r5;
            com.exteragram.messenger.utils.ui.TextPaint r52 = Theme.dialogs_messagePaint[0];
            if (r19 == null) goto L86;
            ArrayList<TLRPC.TL_forumTopic> r7 = MessagesController.getInstance(r17).getTopicsController().getTopics(r19.id);
            boolean r8 = true;
            if (r7 == null) goto L71;
            if (r7.isEmpty() == true) goto L71;
            ArrayList r9 = new ArrayList(r7);
            Collections.sort(r9, Comparator.comparingInt(new DialogCell$ForumFormattedNames$$ExternalSyntheticLambda0()));
            SpannableStringBuilder r72 = new SpannableStringBuilder();
            if (r18 != null) goto L22;
        L42:
            long r11 = 0;
            boolean r2 = false;
            int r15 = 0;
        L43:
            int r13 = 4;
            if (ChatObject.isMonoForum(r19) == false) goto L52;
            this.avatarSpans = new HashMap();
            int r22 = 0;
        L47:
            if (r22 >= Math.min(4, r9.size())) goto L66;
            if (r72.length() == 0) goto L51;
            r72.append("  ");
        L51:
            long r10 = DialogObject.getPeerDialogId(((TLRPC.TL_forumTopic) r9.get(r22)).from_id);
            AvatarSpan r3 = new AvatarSpan(this.parent, r17);
            r3.needDrawShadow = false;
            r3.setDialogId(r10);
            this.avatarSpans.put(Long.valueOf(r10), r3);
            SpannableStringBuilder r102 = SpannableStringBuilder.valueOf(DialogObject.getName(r10));
            r102.insert(0, "  ");
            r102.setSpan(r3, 0, 1, 33);
            r72.append(r102);
            r22 = r22 + 1;
        L66:
            if (r15 <= 0) goto L68;
            r72.setSpan(new TypefaceSpan(AndroidUtilities.bold(), 0, Theme.key_chats_name, null), 0, Math.min(r72.length(), r15 + 2), 0);
        L68:
            this.formattedNames = r72;
            return;
        L52:
            int r1 = 0;
        L54:
            if (r1 >= Math.min(r13, r9.size())) goto L66;
            if (((TLRPC.TL_forumTopic) r9.get(r1)).id == r11) goto L65;
            if (r72.length() == 0) goto L64;
            if (r8 == false) goto L63;
            if (r2 == false) goto L63;
            r72.append(" ");
        L63:
            r72.append(", ");
        L64:
            r72.append(ForumUtilities.getTopicSpannedName((TLRPC.ForumTopic) r9.get(r1), r52, false));
            r8 = false;
        L65:
            r1 = r1 + 1;
            r13 = 4;
            goto L54
        L22:
            if (ChatObject.isMonoForum(r19) == true) goto L42;
            r11 = MessageObject.getTopicId(r17, r18.messageOwner, true);
            TLRPC.TL_forumTopic r132 = MessagesController.getInstance(r17).getTopicsController().findTopic(r19.id, r11);
            if (r132 == null) goto L37;
            CharSequence r14 = ForumUtilities.getTopicSpannedName(r132, r52, false);
            r72.append(r14);
            if (r132.unread_count <= 0) goto L28;
            r15 = r14.length();
        L29:
            this.topMessageTopicStartIndex = 0;
            this.topMessageTopicEndIndex = r14.length();
            if (r18.isOutOwner() == false) goto L33;
            this.lastTopicMessageUnread = false;
        L39:
            if (this.lastTopicMessageUnread == false) goto L41;
            r72.append(" ");
            r72.setSpan(new FixedWidthSpan(AndroidUtilities.dp(3.0f)), r72.length() - 1, r72.length(), 0);
            r2 = true;
            goto L43
        L41:
            r2 = false;
            goto L43
        L33:
            if (r132.unread_count <= 0) goto L35;
            boolean r23 = true;
        L36:
            this.lastTopicMessageUnread = r23;
            goto L39
        L35:
            r23 = false;
            goto L36
        L28:
            r15 = 0;
            goto L29
        L37:
            this.lastTopicMessageUnread = false;
            r15 = 0;
        L71:
            if (MessagesController.getInstance(r17).getTopicsController().endIsReached(r19.id) == true) goto L75;
            MessagesController.getInstance(r17).getTopicsController().preloadTopics(r19.id);
            this.formattedNames = LocaleController.getString(R.string.Loading);
            this.isLoadingState = true;
            return;
        L75:
            if (ChatObject.isMonoForum(r19) == false) goto L77;
            int r12 = R.string.NoMonoforumTopicsCreated;
        L78:
            this.formattedNames = LocaleController.getString(r12);
            return;
        L77:
            r12 = R.string.NoTopicsCreated;
            goto L78
        L86:
            return;
        L11:
            if (this.isLoadingState == true) goto L13;
            return;
        L7:
            r5 = 0;
            goto L9
        }

        private void invalidateSpans() {
            HashMap<Long, AvatarSpan> r0 = this.avatarSpans;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isEmpty() == true) goto L13;
            Iterator<Map.Entry<Long, AvatarSpan>> r4 = this.avatarSpans.entrySet().iterator();
        L9:
            if (r4.hasNext() == false) goto L14;
            Map.Entry<Long, AvatarSpan> r02 = r4.next();
            r02.getValue().setDialogId(r02.getKey().longValue());
            goto L9
        L14:
            return;
        }
    }

    public static class ThumbnailSpan extends FixedWidthSpan {
        public ThumbnailSpan(int r1) {
            super(r1);
        }
    }

    /* renamed from: $r8$lambda$-gEK1C7dfgC5WOqgTxiAHTxccXY, reason: not valid java name */
    public static /* synthetic */ void m6886$r8$lambda$gEK1C7dfgC5WOqgTxiAHTxccXY(DialogCell r0) {
        r0.lambda$onDraw$2();
    }

    /* renamed from: $r8$lambda$H2PnsxVVis5UODdwhKaiS-dILUU, reason: not valid java name */
    public static /* synthetic */ void m6887$r8$lambda$H2PnsxVVis5UODdwhKaiSdILUU(DialogCell r0) {
        r0.lambda$onDraw$3();
    }

    public static /* synthetic */ void $r8$lambda$L10qvr8XxiAI9uac66JZcuoYvoo(DialogCell r0, ValueAnimator r1) {
        r0.lambda$createStatusDrawableAnimator$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$YwfXMRCzB4Q3fCCJ2ZhN3QFsLwc(DialogCell r0, ValueAnimator r1) {
        r0.lambda$update$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$osKkA8XL03pw11QUdOLufZauvyE(DialogCell r0, Object[] r1) {
        r0.lambda$showPremiumBlocked$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$t6EM9ISL74dRRjx25rpRTrBYfm4(DialogCell r0, ValueAnimator r1) {
        r0.lambda$update$1(r1);
    }

    /* renamed from: -$$Nest$fgetanimateToStatusDrawableParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6888$$Nest$fgetanimateToStatusDrawableParams(DialogCell r0) {
        return r0.animateToStatusDrawableParams;
    }

    /* renamed from: -$$Nest$fgetbotVerification, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m6889$$Nest$fgetbotVerification(DialogCell r0) {
        return r0.botVerification;
    }

    /* renamed from: -$$Nest$fgetchat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Chat m6890$$Nest$fgetchat(DialogCell r0) {
        return r0.chat;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6891$$Nest$fgetcurrentAccount(DialogCell r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcurrentDialogFolderId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6892$$Nest$fgetcurrentDialogFolderId(DialogCell r0) {
        return r0.currentDialogFolderId;
    }

    /* renamed from: -$$Nest$fgetcurrentDialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6893$$Nest$fgetcurrentDialogId(DialogCell r2) {
        return r2.currentDialogId;
    }

    /* renamed from: -$$Nest$fgetdialogsType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6894$$Nest$fgetdialogsType(DialogCell r0) {
        return r0.dialogsType;
    }

    /* renamed from: -$$Nest$fgetdraftVoice, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6895$$Nest$fgetdraftVoice(DialogCell r0) {
        return r0.draftVoice;
    }

    /* renamed from: -$$Nest$fgetdrawCheck1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6896$$Nest$fgetdrawCheck1(DialogCell r0) {
        return r0.drawCheck1;
    }

    /* renamed from: -$$Nest$fgetdrawCheck2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6897$$Nest$fgetdrawCheck2(DialogCell r0) {
        return r0.drawCheck2;
    }

    /* renamed from: -$$Nest$fgetdrawClock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6898$$Nest$fgetdrawClock(DialogCell r0) {
        return r0.drawClock;
    }

    /* renamed from: -$$Nest$fgetdrawPin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6899$$Nest$fgetdrawPin(DialogCell r0) {
        return r0.drawPin;
    }

    /* renamed from: -$$Nest$fgetemojiStatus, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m6900$$Nest$fgetemojiStatus(DialogCell r0) {
        return r0.emojiStatus;
    }

    /* renamed from: -$$Nest$fgetisTopic, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6901$$Nest$fgetisTopic(DialogCell r0) {
        return r0.isTopic;
    }

    /* renamed from: -$$Nest$fgetmessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m6902$$Nest$fgetmessage(DialogCell r0) {
        return r0.message;
    }

    /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DialogsActivity m6903$$Nest$fgetparentFragment(DialogCell r0) {
        return r0.parentFragment;
    }

    /* renamed from: -$$Nest$fgettypingLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StaticLayout m6904$$Nest$fgettypingLayout(DialogCell r0) {
        return r0.typingLayout;
    }

    /* renamed from: -$$Nest$fputcountAnimationInLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6905$$Nest$fputcountAnimationInLayout(DialogCell r0, StaticLayout r1) {
        r0.countAnimationInLayout = r1;
    }

    /* renamed from: -$$Nest$fputcountAnimationStableLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6906$$Nest$fputcountAnimationStableLayout(DialogCell r0, StaticLayout r1) {
        r0.countAnimationStableLayout = r1;
    }

    /* renamed from: -$$Nest$fputcountChangeProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6907$$Nest$fputcountChangeProgress(DialogCell r0, float r1) {
        r0.countChangeProgress = r1;
    }

    /* renamed from: -$$Nest$fputcountOldLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6908$$Nest$fputcountOldLayout(DialogCell r0, StaticLayout r1) {
        r0.countOldLayout = r1;
    }

    /* renamed from: -$$Nest$fputlastStatusDrawableParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6909$$Nest$fputlastStatusDrawableParams(DialogCell r0, int r1) {
        r0.lastStatusDrawableParams = r1;
    }

    /* renamed from: -$$Nest$fputreactionsMentionsChangeProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6910$$Nest$fputreactionsMentionsChangeProgress(DialogCell r0, float r1) {
        r0.reactionsMentionsChangeProgress = r1;
    }

    /* renamed from: -$$Nest$fputstatusDrawableAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6911$$Nest$fputstatusDrawableAnimationInProgress(DialogCell r0, boolean r1) {
        r0.statusDrawableAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$mcreateStatusDrawableAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6912$$Nest$mcreateStatusDrawableAnimator(DialogCell r0, int r1, int r2) {
        r0.createStatusDrawableAnimator(r1, r2);
    }

    /* renamed from: -$$Nest$mgetTopicId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6913$$Nest$mgetTopicId(DialogCell r0) {
        return r0.getTopicId();
    }

    public DialogCell(DialogsActivity r8, Context r9, boolean r10, boolean r11) {
        this(r8, r9, r10, r11, UserConfig.selectedAccount, null);
    }

    public DialogCell(DialogsActivity r11, Context r12, boolean r13, boolean r14, int r15, Theme.ResourcesProvider r16) {
        super(r12);
        this.drawArchive = true;
        this.drawAvatar = true;
        this.drawMonoforumAvatar = false;
        this.avatarStart = 11;
        this.messagePaddingStart = 72;
        this.heightDefault = 70;
        this.heightThreeLines = 76;
        this.addHeightForTags = 3;
        this.addForumHeightForTags = 11;
        this.chekBoxPaddingTop = 42.0f;
        AnonymousClass1 r9 = new AnonymousClass1(this, false);
        this.storyParams = r9;
        this.visibleOnScreen = true;
        this.collapseOffset = 0.0f;
        this.hasUnmutedTopics = false;
        this.hasUnmutedCommunityDialogs = false;
        this.openButtonBounce = new ButtonBounce(this);
        this.openButtonBackgroundPaint = new Paint(1);
        this.openButtonRect = new RectF();
        this.overrideSwipeAction = false;
        this.thumbImageSeen = new boolean[3];
        this.thumbImage = new ImageReceiver[3];
        this.drawPlay = new boolean[3];
        this.drawSpoiler = new boolean[3];
        this.avatarImage = new ImageReceiver(this);
        this.avatarDrawable = new AvatarDrawable();
        this.interpolator = new BounceInterpolator();
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.premiumBlockedT = new AnimatedFloat(this, 0, 350, r6);
        this.starsBlockedT = new AnimatedFloat(this, 0, 350, r6);
        this.spoilersPool = new Stack();
        this.spoilers = new ArrayList();
        this.spoilersPool2 = new Stack();
        this.spoilers2 = new ArrayList();
        this.drawCount2 = true;
        this.animatorPollVotesMentionVisible = new BoolAnimator(this, r6, 320);
        this.countChangeProgress = 1.0f;
        this.reactionsMentionsChangeProgress = 1.0f;
        this.rect = new RectF();
        this.lastStatusDrawableParams = -1;
        this.readOutboxMaxId = -1;
        this.updateHelper = new DialogUpdateHelper(this, null);
        r9.allowLongress = true;
        this.resourcesProvider = r16;
        this.parentFragment = r11;
        Theme.createDialogsResources(r12);
        this.drawMonoforumAvatar = false;
        this.drawCommunityAvatar = false;
        int r0 = 0;
    L3:
        ImageReceiver[] r2 = this.thumbImage;
        if (r0 >= r2.length) goto L6;
        r2[r0] = new ImageReceiver(this);
        ImageReceiver r22 = this.thumbImage[r0];
        r22.ignoreNotifications = true;
        r22.setRoundRadius(AndroidUtilities.dp(2.0f));
        this.thumbImage[r0].setAllowLoadingOnAttachedOnly(true);
        r0 = r0 + 1;
        goto L3
    L6:
        this.useForceThreeLines = r14;
        this.currentAccount = r15;
        setAvatarRadius(ExteraConfig.getAvatarCorners(getAvatarSizeDp()));
        AnonymousClass2 r02 = new AnonymousClass2(this, r12);
        this.emojiStatusView = r02;
        addView(r02);
        this.emojiStatus = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r02, AndroidUtilities.dp(22.0f));
        AnonymousClass3 r03 = new AnonymousClass3(this, r12);
        this.botVerificationView = r03;
        addView(r03);
        this.botVerification = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r03, AndroidUtilities.dp(17.0f));
        this.avatarImage.setAllowLoadingOnAttachedOnly(true);
        AvatarSpan r04 = new AvatarSpan(this, this.currentAccount, 18.0f);
        this.messageAvatarSpan = r04;
        r04.needDrawShadow = false;
    }

    private CharSequence addSenderAvatar(CharSequence r5, MessageObject r6) {
        if (ExteraConfig.getSenderMiniAvatars() == false) goto L13;
        if (r6 == null) goto L13;
        if (TextUtils.isEmpty(r5) == true) goto L13;
        long r0 = r6.getFromChatId();
        if (r0 == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L13;
        this.messageAvatarSpan.setDialogId(r0);
        SpannableStringBuilder r62 = new SpannableStringBuilder("A ");
        r62.setSpan(this.messageAvatarSpan, 0, 1, 33);
        int r4 = r62.length();
        r62.append(" ");
        r62.setSpan(new FixedWidthSpan(AndroidUtilities.dp(1.0f)), r4, r62.length(), 33);
        r62.append(r5);
        return r62;
    L13:
        return r5;
    }

    private CharSequence applyThumbs(CharSequence r4) {
        if (this.thumbsCount <= 0) goto L6;
        SpannableStringBuilder r42 = SpannableStringBuilder.valueOf(r4);
        r42.insert(0, " ");
        r42.setSpan(new ThumbnailSpan(AndroidUtilities.dp(((this.thumbSize + 2) * this.thumbsCount) + 3)), 0, 1, 33);
        return r42;
    L6:
        return r4;
    }

    private void checkChatTheme() {
        MessageObject r0 = this.message;
        if (r0 == null) goto L12;
        TLRPC.Message r02 = r0.messageOwner;
        if (r02 == null) goto L13;
        TLRPC.MessageAction r03 = r02.action;
        if ((r03 instanceof TLRPC.TL_messageActionSetChatTheme) == true) goto L9;
        return;
    L9:
        if (this.lastUnreadState == false) goto L15;
        ChatThemeController.getInstance(this.currentAccount).setDialogTheme(this.currentDialogId, ((TLRPC.TL_messageActionSetChatTheme) r03).theme, false);
        return;
    L15:
        return;
    L13:
        return;
    }

    private void checkGroupCall() {
        TLRPC.Chat r0 = this.chat;
        if (r0 != null) goto L5;
    L9:
        boolean r02 = false;
    L10:
        this.hasCall = r02;
        if (r02 == false) goto L13;
        float r03 = 1.0f;
    L14:
        this.chatCallProgress = r03;
        return;
    L13:
        r03 = 0.0f;
        goto L14
    L5:
        if (r0.call_active == false) goto L9;
        if (r0.call_not_empty == false) goto L9;
        r02 = true;
        goto L10
    }

    private void checkOnline() {
        if (this.user == null) goto L8;
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.user.id));
        if (r0 == null) goto L8;
        this.user = r0;
    L8:
        if (isOnline() == false) goto L10;
        float r02 = 1.0f;
    L11:
        this.onlineProgress = r02;
        return;
    L10:
        r02 = 0.0f;
        goto L11
    }

    private void checkTtl() {
        if (this.ttlPeriod > 0) goto L5;
    L15:
        boolean r0 = false;
    L16:
        this.showTtl = r0;
        if (r0 == false) goto L19;
        float r02 = 1.0f;
    L20:
        this.ttlProgress = r02;
        return;
    L19:
        r02 = 0.0f;
        goto L20
    L5:
        if (this.hasCall == true) goto L15;
        if (isOnline() == true) goto L15;
        CheckBox2 r03 = this.checkBox;
        if (r03 == null) goto L13;
        if (r03.isChecked() == true) goto L15;
    L13:
        if (this.storyParams.drawnLive == true) goto L15;
        r0 = true;
        goto L16
    }

    private void checkTwoLinesForName() {
        this.twoLinesForName = false;
        if (this.isTopic == true) goto L5;
        return;
    L5:
        if (hasTags() == true) goto L11;
        buildLayout();
        if (this.nameIsEllipsized == false) goto L12;
        this.twoLinesForName = true;
        buildLayout();
        return;
    L12:
        return;
    }

    private int computeHeight() {
        if (isForumCell() == false) goto L23;
        if (this.isTransitionSupport == true) goto L23;
        if (this.collapsed == true) goto L23;
        if (this.useForceThreeLines == false) goto L11;
    L14:
        float r0 = 86.0f;
    L15:
        int r02 = AndroidUtilities.dp(r0);
        if (this.useSeparator == false) goto L19;
        r02 = r02 + 1;
    L19:
        if (hasTags() == true) goto L21;
        return r02;
    L21:
        return r02 + AndroidUtilities.dp(this.addForumHeightForTags);
    L11:
        if (SharedConfig.useThreeLinesLayout == true) goto L14;
        r0 = 91.0f;
    L23:
        return getCollapsedHeight();
    }

    private void createStatusDrawableAnimator(int r4, int r5) {
        this.statusDrawableProgress = 0.0f;
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.statusDrawableAnimator = r0;
        r0.setDuration(220);
        this.statusDrawableAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animateFromStatusDrawableParams = r4;
        this.animateToStatusDrawableParams = r5;
        this.statusDrawableAnimator.addUpdateListener(new DialogCell$$ExternalSyntheticLambda6(this));
        this.statusDrawableAnimator.addListener(new AnonymousClass7(this));
        this.statusDrawableAnimationInProgress = true;
        this.statusDrawableAnimator.start();
    }

    private void drawCheckStatus(Canvas r7, boolean r8, boolean r9, boolean r10, boolean r11, float r12) {
        if (r12 != 0.0f) goto L6;
        if (r11 == true) goto L6;
        return;
    L6:
        float r3 = (r12 * 0.5f) + 0.5f;
        if (r8 == false) goto L16;
        BaseCell.setDrawableBounds(Theme.dialogs_clockDrawable, this.clockDrawLeft, this.checkDrawTop);
        if (r12 == 1.0f) goto L11;
        r7.save();
        r7.scale(r3, r3, Theme.dialogs_clockDrawable.getBounds().centerX(), Theme.dialogs_halfCheckDrawable.getBounds().centerY());
        Theme.dialogs_clockDrawable.setAlpha((int) (r12 * 255.0f));
    L11:
        Theme.dialogs_clockDrawable.draw(r7);
        if (r12 == 1.0f) goto L14;
        r7.restore();
        Theme.dialogs_clockDrawable.setAlpha(255);
    L14:
        invalidate();
        return;
    L16:
        if (r10 == false) goto L41;
        if (r9 == false) goto L34;
        BaseCell.setDrawableBounds(Theme.dialogs_halfCheckDrawable, this.halfCheckDrawLeft, this.checkDrawTop);
        if (r11 == false) goto L21;
        r7.save();
        r7.scale(r3, r3, Theme.dialogs_halfCheckDrawable.getBounds().centerX(), Theme.dialogs_halfCheckDrawable.getBounds().centerY());
        Theme.dialogs_halfCheckDrawable.setAlpha((int) (r12 * 255.0f));
    L21:
        if (r11 == true) goto L24;
        if (r12 == 0.0f) goto L24;
        r7.save();
        r7.scale(r3, r3, Theme.dialogs_halfCheckDrawable.getBounds().centerX(), Theme.dialogs_halfCheckDrawable.getBounds().centerY());
        int r92 = (int) (255.0f * r12);
        Theme.dialogs_halfCheckDrawable.setAlpha(r92);
        Theme.dialogs_checkReadDrawable.setAlpha(r92);
    L24:
        Theme.dialogs_halfCheckDrawable.draw(r7);
        if (r11 == false) goto L27;
        r7.restore();
        r7.save();
        r7.translate(AndroidUtilities.dp(4.0f) * (1.0f - r12), 0.0f);
    L27:
        BaseCell.setDrawableBounds(Theme.dialogs_checkReadDrawable, this.checkDrawLeft, this.checkDrawTop);
        Theme.dialogs_checkReadDrawable.draw(r7);
        if (r11 == false) goto L30;
        r7.restore();
        Theme.dialogs_halfCheckDrawable.setAlpha(255);
    L30:
        if (r11 == true) goto L42;
        if (r12 == 0.0f) goto L43;
        r7.restore();
        Theme.dialogs_halfCheckDrawable.setAlpha(255);
        Theme.dialogs_checkReadDrawable.setAlpha(255);
        return;
    L43:
        return;
    L42:
        return;
    L34:
        BaseCell.setDrawableBounds(Theme.dialogs_checkDrawable, this.checkDrawLeft1, this.checkDrawTop);
        if (r12 == 1.0f) goto L37;
        r7.save();
        r7.scale(r3, r3, Theme.dialogs_checkDrawable.getBounds().centerX(), Theme.dialogs_halfCheckDrawable.getBounds().centerY());
        Theme.dialogs_checkDrawable.setAlpha((int) (r12 * 255.0f));
    L37:
        Theme.dialogs_checkDrawable.draw(r7);
        if (r12 == 1.0f) goto L44;
        r7.restore();
        Theme.dialogs_checkDrawable.setAlpha(255);
        return;
    L44:
        return;
    }

    private void drawCounter(Canvas r23, boolean r24, int r25, int r26, int r27, float r28, boolean r29) {
        if (isForumCell() == false) goto L5;
    L8:
        boolean r5 = true;
    L10:
        if (this.drawCount == false) goto L14;
        if (this.drawCount2 == false) goto L14;
    L16:
        if (this.unreadCount == 0) goto L18;
    L20:
        float r8 = this.countChangeProgress;
    L21:
        int r10 = 255;
        if (r29 == false) goto L28;
        if (this.counterPaintOutline != null) goto L26;
        Paint r13 = new Paint();
        this.counterPaintOutline = r13;
        r13.setStyle(Paint.Style.STROKE);
        this.counterPaintOutline.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.counterPaintOutline.setStrokeJoin(Paint.Join.ROUND);
        this.counterPaintOutline.setStrokeCap(Paint.Cap.ROUND);
    L26:
        this.counterPaintOutline.setColor(ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhite), ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_chats_pinnedOverlay), 255), Color.alpha(r13) / 255.0f));
    L28:
        if (this.isTopic == true) goto L30;
    L44:
        if (r24 == false) goto L46;
    L49:
        Paint r6 = Theme.dialogs_countGrayPaint;
    L50:
        boolean r16 = false;
    L51:
        StaticLayout r7 = this.countOldLayout;
        if (r7 == null) goto L113;
        if (this.unreadCount == 0) goto L113;
        r6.setAlpha((int) ((1.0f - this.reorderIconProgress) * r10));
        Theme.dialogs_countTextPaint2.setAlpha((int) ((1.0f - this.reorderIconProgress) * 255.0f));
        float r72 = r8 * 2.0f;
        if (r72 <= 1.0f) goto L59;
        float r9 = 1.0f;
    L60:
        float r11 = 1.0f - r9;
        float r3 = (r26 * r9) + (r27 * r11);
        float r12 = r25;
        this.rect.set(r3, r12, (((this.countWidth * r9) + r3) + (this.countWidthOld * r11)) + AndroidUtilities.dp(12.666f), r25 + AndroidUtilities.dp(20.666f));
        if (r8 > 0.5f) goto L64;
        float r2 = CubicBezierInterpolator.EASE_OUT.getInterpolation(r72);
    L63:
        float r22 = (r2 * 0.1f) + 1.0f;
        r23.save();
        float r210 = r22 * r28;
        r23.scale(r210, r210, this.rect.centerX(), this.rect.centerY());
        if (r5 == true) goto L68;
        r23.drawRoundRect(this.rect, AndroidUtilities.dp(10.333f), AndroidUtilities.dp(10.333f), r6);
        if (r29 == false) goto L88;
        r23.drawRoundRect(this.rect, AndroidUtilities.dp(10.333f), AndroidUtilities.dp(10.333f), this.counterPaintOutline);
    L88:
        if (this.countAnimationStableLayout == null) goto L90;
        r23.save();
        r23.translate(AndroidUtilities.dpf2(6.333f) + r3, AndroidUtilities.dpf2(3.0f) + r12);
        this.countAnimationStableLayout.draw(r23);
        r23.restore();
    L90:
        int r211 = Theme.dialogs_countTextPaint2.getAlpha();
        float r52 = r211;
        Theme.dialogs_countTextPaint2.setAlpha((int) (r52 * r9));
        if (this.countAnimationInLayout == null) goto L98;
        r23.save();
        float r4 = AndroidUtilities.dpf2(6.333f) + r3;
        if (this.countAnimationIncrement == false) goto L95;
        int r73 = AndroidUtilities.dp(17.0f);
    L96:
        r23.translate(r4, ((r73 * r11) + r12) + AndroidUtilities.dpf2(3.0f));
        this.countAnimationInLayout.draw(r23);
        r23.restore();
    L105:
        if (this.countOldLayout == null) goto L111;
        Theme.dialogs_countTextPaint2.setAlpha((int) (r52 * r11));
        r23.save();
        float r32 = r3 + AndroidUtilities.dpf2(6.333f);
        if (this.countAnimationIncrement == false) goto L109;
        int r42 = -AndroidUtilities.dp(17.0f);
    L110:
        r23.translate(r32, ((r42 * r9) + r12) + AndroidUtilities.dpf2(3.0f));
        this.countOldLayout.draw(r23);
        r23.restore();
        goto L111
    L109:
        r42 = AndroidUtilities.dp(17.0f);
    L111:
        Theme.dialogs_countTextPaint2.setAlpha(r211);
        r23.restore();
    L146:
        if (r16 == false) goto L150;
        Theme.dialogs_countTextPaint2.setColor(Theme.getColor(Theme.key_chats_unreadCounterText));
        return;
    L150:
        return;
    L95:
        r73 = -AndroidUtilities.dp(17.0f);
        goto L96
    L98:
        if (this.countLayout == null) goto L105;
        r23.save();
        float r43 = AndroidUtilities.dpf2(6.333f) + r3;
        if (this.countAnimationIncrement == false) goto L102;
        int r74 = AndroidUtilities.dp(17.0f);
    L103:
        r23.translate(r43, ((r74 * r11) + r12) + AndroidUtilities.dpf2(3.0f));
        this.countLayout.draw(r23);
        r23.restore();
        goto L105
    L102:
        r74 = -AndroidUtilities.dp(17.0f);
        goto L103
    L68:
        if (this.counterPath == null) goto L73;
        RectF r212 = this.counterPathRect;
        if (r212 == null) goto L73;
        if (r212.equals(this.rect) == false) goto L73;
    L81:
        r23.drawPath(this.counterPath, r6);
        if (r29 == false) goto L88;
        r23.drawPath(this.counterPath, this.counterPaintOutline);
    L73:
        RectF r213 = this.counterPathRect;
        if (r213 != null) goto L76;
        this.counterPathRect = new RectF(this.rect);
    L78:
        if (this.counterPath != null) goto L80;
        this.counterPath = new Path();
    L80:
        BubbleCounterPath.addBubbleRect(this.counterPath, this.counterPathRect, AndroidUtilities.dp(10.333f));
        goto L81
    L76:
        r213.set(this.rect);
        goto L78
    L64:
        r2 = CubicBezierInterpolator.EASE_IN.getInterpolation(1.0f - ((r8 - 0.5f) * 2.0f));
        goto L63
    L59:
        r9 = r72;
    L113:
        if (this.unreadCount == 0) goto L116;
        r7 = this.countLayout;
    L116:
        r6.setAlpha((int) ((1.0f - this.reorderIconProgress) * r10));
        Theme.dialogs_countTextPaint2.setAlpha((int) ((1.0f - this.reorderIconProgress) * 255.0f));
        float r112 = r25;
        this.rect.set(r26, r112, (this.countWidth + r26) + AndroidUtilities.dp(12.666f), r25 + AndroidUtilities.dp(20.666f));
        int r214 = r23.save();
        if (r28 == 1.0f) goto L120;
        r23.scale(r28, r28, this.rect.centerX(), this.rect.centerY());
    L120:
        if (r8 == 1.0f) goto L122;
        r23.scale(r8, r8, this.rect.centerX(), this.rect.centerY());
    L122:
        if (r5 == true) goto L124;
        r23.drawRoundRect(this.rect, AndroidUtilities.dp(10.333f), AndroidUtilities.dp(10.333f), r6);
        if (r29 == false) goto L143;
        r23.drawRoundRect(this.rect, AndroidUtilities.dp(10.333f), AndroidUtilities.dp(10.333f), this.counterPaintOutline);
    L143:
        if (r7 == null) goto L145;
        r23.save();
        r23.translate(AndroidUtilities.dp(6.333f) + r26, r112 + AndroidUtilities.dpf2(3.0f));
        r7.draw(r23);
        r23.restore();
    L145:
        r23.restoreToCount(r214);
        goto L146
    L124:
        if (this.counterPath == null) goto L129;
        RectF r44 = this.counterPathRect;
        if (r44 == null) goto L129;
        if (r44.equals(this.rect) == false) goto L129;
    L137:
        r23.drawPath(this.counterPath, r6);
        if (r29 == false) goto L143;
        r23.drawPath(this.counterPath, this.counterPaintOutline);
    L129:
        RectF r45 = this.counterPathRect;
        if (r45 != null) goto L132;
        this.counterPathRect = new RectF(this.rect);
    L134:
        if (this.counterPath != null) goto L136;
        this.counterPath = new Path();
    L136:
        BubbleCounterPath.addBubbleRect(this.counterPath, this.counterPathRect, AndroidUtilities.dp(10.333f));
        goto L137
    L132:
        r45.set(this.rect);
        goto L134
    L46:
        if (this.currentDialogFolderId != 0) goto L49;
        r6 = Theme.dialogs_countPaint;
        goto L50
    L30:
        if (this.forumTopic.read_inbox_max_id != 0) goto L44;
        if (this.topicCounterPaint != null) goto L34;
        this.topicCounterPaint = new Paint();
    L34:
        r6 = this.topicCounterPaint;
        if (r24 == false) goto L37;
        int r75 = Theme.key_topics_unreadCounterMuted;
    L38:
        int r76 = Theme.getColor(r75, this.resourcesProvider);
        r6.setColor(r76);
        Theme.dialogs_countTextPaint2.setColor(r76);
        if (r24 == false) goto L42;
        int r77 = 30;
    L41:
        r10 = r77;
        r16 = true;
        goto L51
    L42:
        r77 = 40;
        goto L41
    L37:
        r75 = Theme.key_topics_unreadCounter;
        goto L38
    L18:
        if (this.markUnread == true) goto L20;
        r8 = 1.0f - this.countChangeProgress;
    L14:
        if (this.countChangeProgress != 1.0f) goto L16;
        return;
    L5:
        if (isFolderCell() == true) goto L8;
        r5 = false;
        goto L10
    }

    private MessageObject findFolderTopMessage() {
        DialogsActivity r0 = this.parentFragment;
        if (r0 != null) goto L5;
        return null;
    L5:
        ArrayList<TLRPC.Dialog> r02 = r0.getDialogsArray(this.currentAccount, this.dialogsType, this.currentDialogFolderId, false);
        if (r02 != null) goto L8;
    L30:
        return null;
    L8:
        if (r02.isEmpty() == true) goto L30;
        int r2 = r02.size();
        MessageObject r4 = null;
        int r3 = 0;
    L10:
        if (r3 >= r2) goto L29;
        TLRPC.Dialog r6 = r02.get(r3);
        LongSparseArray<ArrayList<MessageObject>> r7 = MessagesController.getInstance(this.currentAccount).dialogMessage;
        if (r7 == null) goto L28;
        ArrayList<MessageObject> r72 = r7.get(r6.id);
        if (r72 != null) goto L16;
    L18:
        MessageObject r73 = null;
    L19:
        if (r73 == null) goto L25;
        if (r4 != null) goto L22;
    L23:
        r4 = r73;
        goto L25
    L22:
        if (r73.messageOwner.date > r4.messageOwner.date) goto L23;
    L25:
        if (r6.pinnedNum != 0) goto L28;
        if (r4 == null) goto L28;
        return r4;
    L16:
        if (r72.isEmpty() == true) goto L18;
        r73 = r72.get(0);
    L28:
        r3 = r3 + 1;
        goto L10
    L29:
        return r4;
    }

    private CharSequence formatArchivedDialogNames() {
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        ArrayList<TLRPC.Dialog> r1 = r0.getDialogs(this.currentDialogFolderId);
        this.currentDialogFolderDialogsCount = r1.size();
        SpannableStringBuilder r2 = new SpannableStringBuilder();
        int r3 = r1.size();
        int r5 = 0;
    L4:
        if (r5 >= r3) goto L37;
        TLRPC.Dialog r7 = r1.get(r5);
        if (r0.isHiddenByUndo(r7.id) == true) goto L35;
        boolean r8 = DialogObject.isEncryptedDialog(r7.id);
        long r9 = r7.id;
        TLRPC.Chat r11 = null;
        if (r8 == false) goto L14;
        TLRPC.EncryptedChat r82 = r0.getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r9)));
        if (r82 == null) goto L13;
        TLRPC.User r83 = r0.getUser(Long.valueOf(r82.user_id));
    L19:
        if (r11 == null) goto L21;
        String r84 = r11.title.replace('\n', ' ');
    L27:
        if (r2.length() <= 0) goto L29;
        r2.append(", ");
    L29:
        int r92 = r2.length();
        int r10 = r84.length() + r92;
        r2.append(r84);
        if (r7.unread_count <= 0) goto L33;
        r2.setSpan(new TypefaceSpan(AndroidUtilities.bold(), 0, Theme.getColor(Theme.key_chats_nameArchived, this.resourcesProvider)), r92, r10, 33);
    L33:
        if (r2.length() <= 150) goto L35;
    L21:
        if (r83 == null) goto L35;
        if (UserObject.isDeleted(r83) == false) goto L25;
        r84 = LocaleController.getString(R.string.HiddenName);
        goto L27
    L25:
        r84 = AndroidUtilities.escape(ContactsController.formatName(r83.first_name, r83.last_name).replace('\n', ' '));
        goto L27
    L13:
        r83 = null;
        goto L19
    L14:
        boolean r85 = DialogObject.isUserDialog(r9);
        long r93 = r7.id;
        if (r85 == false) goto L17;
        r83 = r0.getUser(Long.valueOf(r93));
        goto L19
    L17:
        r11 = r0.getChat(Long.valueOf(-r93));
        r83 = null;
    L35:
        r5 = r5 + 1;
    L37:
        if (MessagesController.getInstance(this.currentAccount).storiesController.getTotalStoriesCount(true) <= 0) goto L43;
        int r02 = Math.max(1, MessagesController.getInstance(this.currentAccount).storiesController.getTotalStoriesCount(true));
        if (r2.length() <= 0) goto L41;
        r2.append(", ");
    L41:
        r2.append(LocaleController.formatPluralString("Stories", r02, new Object[0]));
    L43:
        return Emoji.replaceEmoji(r2, Theme.dialogs_messagePaint[this.paintIndex].getFontMetricsInt(), false);
    }

    private CharSequence formatCommunityDialogNames() {
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        ArrayList<TLRPC.Dialog> r1 = r0.getDialogsByCommunity(-this.currentDialogId);
        SpannableStringBuilder r2 = new SpannableStringBuilder();
        int r3 = r1.size();
        int r5 = 0;
    L3:
        if (r5 >= r3) goto L36;
        TLRPC.Dialog r6 = r1.get(r5);
        if (r0.isHiddenByUndo(r6.id) == true) goto L34;
        boolean r7 = DialogObject.isEncryptedDialog(r6.id);
        long r8 = r6.id;
        TLRPC.Chat r10 = null;
        if (r7 == false) goto L13;
        TLRPC.EncryptedChat r72 = r0.getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r8)));
        if (r72 == null) goto L12;
        TLRPC.User r73 = r0.getUser(Long.valueOf(r72.user_id));
    L18:
        if (r10 == null) goto L20;
        String r74 = r10.title.replace('\n', ' ');
    L26:
        if (r2.length() <= 0) goto L28;
        r2.append(", ");
    L28:
        int r82 = r2.length();
        int r9 = r74.length() + r82;
        r2.append(r74);
        if (r6.unread_count <= 0) goto L32;
        r2.setSpan(new TypefaceSpan(AndroidUtilities.bold(), 0, Theme.getColor(Theme.key_chats_nameArchived, this.resourcesProvider)), r82, r9, 33);
    L32:
        if (r2.length() <= 150) goto L34;
    L20:
        if (r73 == null) goto L34;
        if (UserObject.isDeleted(r73) == false) goto L24;
        r74 = LocaleController.getString(R.string.HiddenName);
        goto L26
    L24:
        r74 = AndroidUtilities.escape(ContactsController.formatName(r73.first_name, r73.last_name).replace('\n', ' '));
        goto L26
    L12:
        r73 = null;
        goto L18
    L13:
        boolean r75 = DialogObject.isUserDialog(r8);
        long r83 = r6.id;
        if (r75 == false) goto L16;
        r73 = r0.getUser(Long.valueOf(r83));
        goto L18
    L16:
        r10 = r0.getChat(Long.valueOf(-r83));
        r73 = null;
    L34:
        r5 = r5 + 1;
    L36:
        return Emoji.replaceEmoji(r2, Theme.dialogs_messagePaint[this.paintIndex].getFontMetricsInt(), false);
    }

    private SpannableStringBuilder formatInternal(int r3, CharSequence r4, CharSequence r5) {
        SpannableStringBuilder r2 = new SpannableStringBuilder();
        if (r3 != 1) goto L5;
        r2.append(r5).append(": \u2068").append(r4).append("\u2069");
        return r2;
    L5:
        if (r3 != 2) goto L7;
        r2.append("\u2068").append(r4).append("\u2069");
        return r2;
    L7:
        if (r3 != 3) goto L9;
        r2.append(r5).append(": ").append(r4);
        return r2;
    L9:
        if (r3 == 4) goto L11;
        return r2;
    L11:
        r2.append(r4);
        return r2;
    }

    private CharSequence formatTopicsNames() {
        if (this.forumFormattedNames != null) goto L5;
        this.forumFormattedNames = new ForumFormattedNames(this);
    L5:
        ForumFormattedNames.m6914$$Nest$mformatTopicsNames(this.forumFormattedNames, this.currentAccount, this.message, this.chat);
        ForumFormattedNames r0 = this.forumFormattedNames;
        this.topMessageTopicStartIndex = r0.topMessageTopicStartIndex;
        this.topMessageTopicEndIndex = r0.topMessageTopicEndIndex;
        this.lastTopicMessageUnread = r0.lastTopicMessageUnread;
        return r0.formattedNames;
    }

    private ColorFilter getAdaptiveEmojiColorFilter(int r4, int r5) {
        if (this.adaptiveEmojiColorFilter != null) goto L6;
        this.adaptiveEmojiColor = new int[4];
        this.adaptiveEmojiColorFilter = new ColorFilter[4];
    L6:
        if (r5 == this.adaptiveEmojiColor[r4]) goto L8;
    L9:
        ColorFilter[] r0 = this.adaptiveEmojiColorFilter;
        this.adaptiveEmojiColor[r4] = r5;
        r0[r4] = new PorterDuffColorFilter(r5, PorterDuff.Mode.SRC_IN);
    L11:
        return this.adaptiveEmojiColorFilter[r4];
    L8:
        if (this.adaptiveEmojiColorFilter[r4] != null) goto L11;
        goto L9
    }

    private int getAvatarSizeDp() {
        if (this.useForceThreeLines == false) goto L5;
        return 56;
    L5:
        if (SharedConfig.useThreeLinesLayout == true) goto L11;
        return 52;
    L11:
        return 56;
    }

    private MessageObject getCaptionMessage() {
        if (this.groupMessages != null) goto L10;
        MessageObject r6 = this.message;
        if (r6 != null) goto L7;
    L9:
        return null;
    L7:
        if (r6.caption == null) goto L9;
        return r6;
    L10:
        int r0 = 0;
        int r2 = 0;
        MessageObject r3 = null;
    L12:
        if (r0 >= this.groupMessages.size()) goto L23;
        MessageObject r4 = this.groupMessages.get(r0);
        if (r4 == null) goto L21;
        CharSequence r5 = r4.caption;
        if (r5 == null) goto L21;
        if (TextUtils.isEmpty(r5) == true) goto L20;
        r2 = r2 + 1;
    L20:
        r3 = r4;
    L21:
        r0 = r0 + 1;
        goto L12
    L23:
        if (r2 <= 1) goto L25;
        return null;
    L25:
        return r3;
    }

    private int getCollapsedHeight() {
        if (this.useForceThreeLines == false) goto L5;
    L9:
        int r0 = this.heightThreeLines;
    L10:
        int r02 = AndroidUtilities.dp(r0) + 1;
        if (this.twoLinesForName == false) goto L14;
        r02 = r02 + AndroidUtilities.dp(20.0f);
    L14:
        if (hasTags() == true) goto L16;
        return r02;
    L16:
        if (this.useForceThreeLines == true) goto L20;
        if (SharedConfig.useThreeLinesLayout == true) goto L20;
    L22:
        if (isForumCell() == false) goto L25;
        int r2 = this.addForumHeightForTags;
    L27:
        return r02 + AndroidUtilities.dp(r2);
    L25:
        r2 = this.addHeightForTags;
    L20:
        if (isForumCell() == true) goto L22;
        return r02;
    L5:
        if (SharedConfig.useThreeLinesLayout == true) goto L9;
        r0 = this.heightDefault;
        goto L10
    }

    private Paint getPaintReorderGradient() {
        int r0 = Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider);
        if (this.reorderGradientLastColor == r0) goto L5;
    L6:
        this.reorderGradientLastColor = r0;
        if (this.reorderGradientPaint != null) goto L9;
        this.reorderGradientPaint = new Paint(1);
    L9:
        this.reorderGradientPaint.setShader(new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(24.0f), 0.0f, new int[]{0, r0}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
    L11:
        return this.reorderGradientPaint;
    L5:
        if (this.reorderGradientPaint != null) goto L11;
        goto L6
    }

    private CharSequence getPreviewMessageNameString() {
        String r0 = getMessageNameString();
        if (r0 != null) goto L66;
        MessageObject r1 = this.message;
        if (r1 == null) goto L66;
        if (r1.messageOwner == null) goto L66;
        if (this.currentDialogId == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L12;
        return null;
    L12:
        if (this.message.isOutOwner() == true) goto L14;
        TLRPC.MessageFwdHeader r02 = this.message.messageOwner.fwd_from;
        if (r02 != null) goto L18;
    L48:
        TLRPC.Peer r03 = this.message.messageOwner.from_id;
        if (r03 == null) goto L67;
        long r5 = DialogObject.getPeerDialogId(r03);
        if (DialogObject.isUserDialog(r5) == false) goto L57;
        TLRPC.User r8 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r5));
        if (r8 != null) goto L55;
        return null;
    L55:
        return AndroidUtilities.escape(UserObject.getFirstName(r8).replace("\n", _UrlKt.FRAGMENT_ENCODE_SET));
    L57:
        if (r5 == 0) goto L69;
        TLRPC.Chat r82 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r5));
        if (r82 == null) goto L70;
        String r83 = r82.title;
        if (r83 != null) goto L63;
        return null;
    L63:
        return AndroidUtilities.escape(r83.replace("\n", _UrlKt.FRAGMENT_ENCODE_SET));
    L70:
        return null;
    L69:
        return null;
    L67:
        return null;
    L18:
        if (TextUtils.isEmpty(r02.from_name) == true) goto L22;
        return AndroidUtilities.escape(r02.from_name);
    L22:
        if (TextUtils.isEmpty(r02.saved_from_name) == false) goto L24;
        TLRPC.Peer r52 = r02.from_id;
        if (r52 == null) goto L28;
        long r53 = DialogObject.getPeerDialogId(r52);
    L30:
        if (r53 != 0) goto L35;
        TLRPC.Peer r04 = r02.saved_from_peer;
        if (r04 == null) goto L35;
        r53 = DialogObject.getPeerDialogId(r04);
    L35:
        if (r53 == 0) goto L48;
        boolean r05 = DialogObject.isUserDialog(r53);
        int r7 = this.currentAccount;
        if (r05 == false) goto L42;
        TLRPC.User r06 = MessagesController.getInstance(r7).getUser(Long.valueOf(r53));
        if (r06 == null) goto L48;
        return AndroidUtilities.escape(UserObject.getFirstName(r06).replace("\n", _UrlKt.FRAGMENT_ENCODE_SET));
    L42:
        TLRPC.Chat r07 = MessagesController.getInstance(r7).getChat(Long.valueOf(-r53));
        if (r07 == null) goto L48;
        String r08 = r07.title;
        if (r08 == null) goto L48;
        return AndroidUtilities.escape(r08.replace("\n", _UrlKt.FRAGMENT_ENCODE_SET));
    L28:
        r53 = 0;
        goto L30
    L24:
        return AndroidUtilities.escape(r02.saved_from_name);
    L14:
        return LocaleController.getString(R.string.FromYou);
    L66:
        return r0;
    }

    private TextPaint getTimeTextPaint() {
        if (this.drawCount == false) goto L11;
        if (isCounterMuted() == false) goto L9;
        return Theme.dialogs_timePaintBold;
    L9:
        return Theme.dialogs_timePaintBoldAccent;
    L11:
        return Theme.dialogs_timePaint;
    }

    private int getTopicId() {
        TLRPC.TL_forumTopic r0 = this.forumTopic;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.id;
    }

    private boolean isCounterMuted() {
        if (isDialogCommunity() == false) goto L7;
        return !this.hasUnmutedCommunityDialogs;
    L7:
        if (this.isTopic == true) goto L9;
        TLRPC.Chat r0 = this.chat;
        if (r0 == null) goto L22;
        if (r0.forum == false) goto L22;
        if (this.forumTopic != null) goto L22;
        if (this.hasUnmutedTopics == true) goto L19;
        return true;
    L19:
        return false;
    L22:
        return this.dialogMuted;
    L9:
        return this.topicMuted;
    }

    private boolean isOnline() {
        if (isForumCell() == false) goto L6;
        return false;
    L6:
        if (this.storyParams.drawnLive == false) goto L8;
        return false;
    L8:
        TLRPC.User r0 = this.user;
        if (r0 != null) goto L11;
    L25:
        return false;
    L11:
        if (r0.self == true) goto L25;
        TLRPC.UserStatus r02 = r0.status;
        if (r02 != null) goto L16;
    L20:
        TLRPC.UserStatus r03 = this.user.status;
        if (r03 == null) goto L25;
        if (r03.expires <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L25;
        return true;
    L16:
        if (r02.expires > 0) goto L20;
        if (MessagesController.getInstance(this.currentAccount).onlinePrivacy.containsKey(Long.valueOf(this.user.id)) == false) goto L20;
        return true;
    }

    private /* synthetic */ void lambda$createStatusDrawableAnimator$4(ValueAnimator r1) {
        this.statusDrawableProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$onDraw$2() {
        DialogCellDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onButtonClicked(this);
        return;
    }

    private /* synthetic */ void lambda$onDraw$3() {
        DialogCellDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onButtonLongPress(this);
        return;
    }

    private /* synthetic */ void lambda$showPremiumBlocked$5(Object[] r1) {
        updatePremiumBlocked(true);
    }

    private /* synthetic */ void lambda$update$0(ValueAnimator r1) {
        this.countChangeProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$update$1(ValueAnimator r1) {
        this.reactionsMentionsChangeProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private void prependForwardedIcon(SpannableStringBuilder r3, int r4) {
        this.drawForwardIcon = true;
        r3.insert(r4, "d ");
        ColoredImageSpan r0 = new ColoredImageSpan(ContextCompat.getDrawable(getContext(), R.drawable.mini_forwarded).mutate());
        r0.setAlpha(0.9f);
        r3.setSpan(r0, r4, r4 + 1, 33);
    }

    private void setAvatarRadius(int r3) {
        this.storyParams.avatarRadius = r3;
        this.avatarImage.setRoundRadius(r3);
    }

    private void setThumb(int r12, MessageObject r13) {
        ArrayList<TLRPC.PhotoSize> r0 = r13.photoThumbs;
        TLObject r1 = r13.photoThumbsObject;
        if (r13.isStoryMedia() == false) goto L14;
        TL_stories.StoryItem r2 = r13.messageOwner.media.storyItem;
        if (r2 == null) goto L61;
        TLRPC.MessageMedia r22 = r2.media;
        if (r22 == null) goto L62;
        TLRPC.Document r3 = r22.document;
        if (r3 == null) goto L11;
        r0 = r3.thumbs;
        r1 = r3;
        goto L14
    L11:
        TLRPC.Photo r23 = r22.photo;
        if (r23 == null) goto L14;
        r0 = r23.sizes;
        r1 = r23;
        goto L14
    L62:
        return;
    L61:
        return;
    L14:
        TLRPC.PhotoSize r24 = FileLoader.getStrippedPhotoSize(r0);
        if (r24 != null) goto L17;
        r24 = FileLoader.getClosestPhotoSizeWithSize(r0, 40);
    L17:
        TLRPC.PhotoSize r4 = null;
        TLRPC.PhotoSize r02 = FileLoader.getClosestPhotoSizeWithSize(r0, AndroidUtilities.getPhotoSize(), false, null, true);
        if (r24 == r02) goto L21;
        r4 = r02;
    L21:
        if (r4 != null) goto L23;
    L24:
        r4 = r24;
    L25:
        if (r24 != null) goto L27;
        return;
    L27:
        if (this.hasVideoThumb == false) goto L29;
    L34:
        boolean r03 = true;
    L35:
        this.hasVideoThumb = r03;
        int r04 = this.thumbsCount;
        if (r04 >= 3) goto L64;
        this.thumbsCount = r04 + 1;
        boolean[] r05 = this.drawPlay;
        if (r13.isVideo() == true) goto L42;
        if (r13.isRoundVideo() == true) goto L42;
    L44:
        boolean r32 = false;
    L45:
        r05[r12] = r32;
        this.drawSpoiler[r12] = r13.hasMediaSpoilers();
        if (r13.type != 1) goto L49;
        if (r4 == null) goto L49;
        int r06 = r4.size;
    L51:
        if (r13.hasMediaSpoilers() == false) goto L53;
        String r33 = "5_5_b";
    L54:
        String r25 = r33;
        this.thumbImage[r12].setImage(ImageLocation.getForObject(r4, r1), r25, ImageLocation.getForObject(r24, r1), r25, r06, null, r13, 0);
        ImageReceiver r07 = this.thumbImage[r12];
        if (r13.isRoundVideo() == false) goto L58;
        float r14 = 18.0f;
    L59:
        r07.setRoundRadius(AndroidUtilities.dp(r14));
        this.needEmoji = false;
        return;
    L58:
        r14 = 2.0f;
        goto L59
    L53:
        r33 = "20_20";
    L49:
        r06 = 0;
    L42:
        if (r13.hasMediaSpoilers() == true) goto L44;
        r32 = true;
        goto L45
    L64:
        return;
    L29:
        if (r13.isVideo() == true) goto L34;
        if (r13.isRoundVideo() == true) goto L34;
        r03 = false;
        goto L35
    L23:
        if (DownloadController.getInstance(this.currentAccount).canDownloadMedia(r13) == true) goto L25;
        goto L24
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setThumb(int r19, TLRPC.MessageMedia r20) {
        TLRPC.PhotoSize r3 = null;
        if ((r20 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L7;
        TLRPC.Photo r1 = r20.photo;
        ArrayList<TLRPC.PhotoSize> r2 = r1.sizes;
        TLRPC.Document r12 = r1;
    L5:
        boolean r5 = false;
        TLRPC.Document r13 = r12;
    L10:
        TLRPC.PhotoSize r6 = FileLoader.getStrippedPhotoSize(r2);
        if (r6 != null) goto L13;
        r6 = FileLoader.getClosestPhotoSizeWithSize(r2, 40);
    L13:
        TLRPC.PhotoSize r22 = FileLoader.getClosestPhotoSizeWithSize(r2, AndroidUtilities.getPhotoSize(), false, null, true);
        if (r6 == r22) goto L17;
        r3 = r22;
    L17:
        if (r3 != null) goto L19;
    L20:
        r3 = r6;
    L21:
        if (r6 != null) goto L23;
        return;
    L23:
        if (this.hasVideoThumb == true) goto L27;
        if (r5 == true) goto L27;
        boolean r23 = false;
    L28:
        this.hasVideoThumb = r23;
        int r24 = this.thumbsCount;
        if (r24 >= 3) goto L38;
        this.thumbsCount = r24 + 1;
        this.drawPlay[r19] = r5;
        this.drawSpoiler[r19] = false;
        if (r5 == true) goto L34;
        if (r3 == null) goto L34;
        int r25 = r3.size;
    L35:
        this.thumbImage[r19].setImage(ImageLocation.getForObject(r3, r13), "20_20", ImageLocation.getForObject(r6, r13), "20_20", r25, null, this.message, 0);
        this.thumbImage[r19].setRoundRadius(AndroidUtilities.dp(2.0f));
        this.needEmoji = false;
        return;
    L34:
        r25 = 0;
        goto L35
    L38:
        return;
    L27:
        r23 = true;
        goto L28
    L19:
        if (DownloadController.getInstance(this.currentAccount).canDownloadMedia(1, r3.size) == true) goto L21;
    L7:
        if ((r20 instanceof TLRPC.TL_messageMediaDocument) == false) goto L9;
        boolean r26 = MessageObject.isVideoDocument(r20.document);
        TLRPC.Document r14 = r20.document;
        r5 = r26;
        r2 = r14.thumbs;
        r13 = r14;
        goto L10
    L9:
        r12 = null;
        r2 = null;
        goto L5
    }

    private void setThumb(int r13, TLRPC.PhotoSize r14) {
        if (r13 >= 3) goto L12;
        if (r14 == null) goto L10;
        this.hasVideoThumb = false;
        int r2 = this.thumbsCount;
        if (r2 >= 3) goto L11;
        this.thumbsCount = r2 + 1;
        this.drawPlay[r13] = false;
        this.drawSpoiler[r13] = true;
        this.thumbImage[r13].setImage(ImageLocation.getForObject(r14, this.message.messageOwner), "2_2_b", null, null, 0, null, this.message, 0);
        this.thumbImage[r13].setRoundRadius(AndroidUtilities.dp(2.0f));
        this.needEmoji = false;
        return;
    L11:
        return;
    L10:
        return;
    }

    private boolean shouldDrawForwardPreviewIcon(MessageObject r3) {
        if (r3 == null) goto L31;
        TLRPC.Message r0 = r3.messageOwner;
        if (r0 != null) goto L6;
        return false;
    L6:
        if (this.currentDialogFolderId == 0) goto L8;
        return false;
    L8:
        if (r0.fwd_from != null) goto L10;
        return false;
    L10:
        if (r3.needDrawForwarded() == false) goto L12;
        return true;
    L12:
        if (r3.isOutOwner() == true) goto L14;
        return false;
    L14:
        if (r3.isSaved == true) goto L24;
        if (this.isSavedDialog == true) goto L24;
        TLRPC.Chat r02 = this.chat;
        if (r02 == null) goto L24;
        if (ChatObject.isChannel(r02) == true) goto L22;
        return true;
    L22:
        if (ChatObject.isMegagroup(this.chat) == false) goto L24;
        return true;
    L24:
        if (r3.isSaved == false) goto L26;
        return true;
    L26:
        if (this.isSavedDialog == false) goto L28;
        return true;
    L28:
        if (this.currentDialogId != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L42;
        return true;
    L42:
        return false;
    L31:
        return false;
    }

    private void updatePremiumBlocked(boolean r7) {
        if (this.unsubscribePremiumBlocked != null) goto L5;
    L7:
        TL_account.RequirementToContact r0 = null;
    L9:
        if (this.premiumBlocked == DialogObject.isPremiumBlocked(r0)) goto L11;
    L14:
        this.premiumBlocked = DialogObject.isPremiumBlocked(r0);
        this.starsPriceBlocked = DialogObject.getMessagesStarsPrice(r0);
        if (r7 == true) goto L21;
        this.premiumBlockedT.set(this.premiumBlocked, true);
        AnimatedFloat r72 = this.starsBlockedT;
        if (this.starsPriceBlocked <= 0) goto L19;
        boolean r02 = true;
    L20:
        r72.set(r02, true);
        goto L21
    L19:
        r02 = false;
    L21:
        invalidate();
        return;
    L11:
        if (this.starsPriceBlocked != DialogObject.getMessagesStarsPrice(r0)) goto L14;
        return;
    L5:
        if (this.user == null) goto L7;
        r0 = MessagesController.getInstance(this.currentAccount).isUserContactBlocked(this.user.id);
        goto L9
    }

    private void updateThumbsPosition() {
        if (this.thumbsCount > 0) goto L5;
        return;
    L5:
        if (isForumCell() == false) goto L7;
        StaticLayout r0 = this.buttonLayout;
    L9:
        if (isForumCell() == false) goto L11;
        int r1 = this.buttonLeft;
    L12:
        if (r0 == null) goto L47;
        CharSequence r2 = r0.getText();     // Catch: Exception -> L38
        if ((r2 instanceof Spanned) == false) goto L46;
        int r6 = 0;
        ThumbnailSpan[] r3 = (ThumbnailSpan[]) ((Spanned) r2).getSpans(0, r2.length(), ThumbnailSpan.class);     // Catch: Exception -> L38
        if (r3 != null) goto L19;
    L33:
        int r02 = 0;
    L35:
        if (r02 >= 3) goto L49;
        this.thumbImageSeen[r02] = false;     // Catch: Exception -> L38
        r02 = r02 + 1;
        goto L35
    L49:
        return;
    L19:
        if (r3.length <= 0) goto L33;
        int r22 = ((Spanned) r2).getSpanStart(r3[0]);     // Catch: Exception -> L38
        if (r22 >= 0) goto L23;
        r22 = 0;
    L23:
        int r03 = (int) Math.ceil(Math.min(r0.getPrimaryHorizontal(r22), r0.getPrimaryHorizontal(r22 + 1)));     // Catch: Exception -> L38
        if (r03 == 0) goto L31;
        if (this.drawForwardIcon == true) goto L31;
        if (this.drawGiftIcon == true) goto L31;
        r03 = r03 + AndroidUtilities.dp(3.0f);     // Catch: Exception -> L38
    L31:
        if (r6 >= this.thumbsCount) goto L48;
        this.thumbImage[r6].setImageX((r1 + r03) + AndroidUtilities.dp((this.thumbSize + 2) * r6));     // Catch: Exception -> L38
        this.thumbImageSeen[r6] = true;     // Catch: Exception -> L38
        r6 = r6 + 1;     // Catch: Exception -> L38
        goto L31
    L48:
        return;
    L46:
        return;
    L38:
        e = move-exception;
        FileLog.e(e);
        return;
    L47:
        return;
    L11:
        r1 = this.messageLeft;
        goto L12
    L7:
        r0 = this.messageLayout;
        goto L9
    }

    public DialogCell allowBotOpenButton(boolean r1, Utilities.Callback<TLRPC.User> r2) {
        this.allowBotOpenButton = r1;
        this.onOpenButtonClick = r2;
        return this;
    }

    public void animateArchiveAvatar() {
        if (this.avatarDrawable.getAvatarType() == 2) goto L5;
        return;
    L5:
        this.animatingArchiveAvatar = true;
        this.animatingArchiveAvatarProgress = 0.0f;
        Theme.dialogs_archiveAvatarDrawable.setProgress(0.0f);
        Theme.dialogs_archiveAvatarDrawable.start();
        invalidate();
    }

    public void buildLayout() {
        if (this.isTransitionSupport == false) goto L6;
        return;
    L6:
        if (this.isDialogCell == false) goto L17;
        if (this.updateHelper.update() == true) goto L17;
        if (this.currentDialogFolderId != 0) goto L17;
        if (this.currentDialogCommunityId != 0) goto L17;
        if (this.encryptedChat != null) goto L17;
        return;
    L17:
        if (this.useForceThreeLines == true) goto L19;
        int r0 = SharedConfig.PASSCODE_TYPE_PIN;
    L19:
        boolean r7 = false;
        Theme.dialogs_namePaint[0].setTextSize(AndroidUtilities.dp(17.0f));
        Theme.dialogs_nameEncryptedPaint[0].setTextSize(AndroidUtilities.dp(17.0f));
        Theme.dialogs_messagePaint[0].setTextSize(AndroidUtilities.dp(16.0f));
        Theme.dialogs_messagePrintingPaint[0].setTextSize(AndroidUtilities.dp(16.0f));
        Theme.dialogs_namePaint[1].setTextSize(AndroidUtilities.dp(16.0f));
        Theme.dialogs_nameEncryptedPaint[1].setTextSize(AndroidUtilities.dp(16.0f));
        float r6 = 15.0f;
        Theme.dialogs_messagePaint[1].setTextSize(AndroidUtilities.dp(15.0f));
        Theme.dialogs_messagePaint[1].setTypeface(AndroidUtilities.regular());
        Theme.dialogs_messagePrintingPaint[1].setTextSize(AndroidUtilities.dp(15.0f));
        com.exteragram.messenger.utils.ui.TextPaint r02 = Theme.dialogs_messagePaint[1];
        int r9 = Theme.getColor(Theme.key_chats_message_threeLines, this.resourcesProvider);
        ((TextPaint) r02).linkColor = r9;
        r02.setColor(r9);
        this.paintIndex = 1;
        this.thumbSize = 18;
        this.currentDialogFolderDialogsCount = 0;
        String r92 = null;
        if (isForumCell() == false) goto L22;
    L26:
        CharSequence r03 = null;
    L27:
        this.currentMessagePaint = Theme.dialogs_messagePaint[this.paintIndex];
        this.drawNameLock = false;
        this.drawVerified = false;
        this.drawBotVerified = false;
        this.drawBadge = false;
        this.drawPremium = false;
        this.drawForwardIcon = false;
        this.drawGiftIcon = false;
        this.drawScam = 0;
        this.drawPinBackground = false;
        this.thumbsCount = 0;
        this.hasVideoThumb = false;
        this.nameLayoutEllipsizeByGradient = false;
        if (UserObject.isUserSelf(this.user) == false) goto L30;
    L32:
        boolean r10 = false;
    L33:
        this.printingStringType = -1;
        if (isForumCell() == true) goto L36;
        this.buttonLayout = null;
    L36:
        setOpenBotButton(false);
        if (this.useForceThreeLines == true) goto L41;
        if (SharedConfig.useThreeLinesLayout == true) goto L41;
    L48:
        this.hasNameInMessage = true;
        int r12 = 1;
    L49:
        MessageObject r14 = this.message;
        if (r14 == null) goto L52;
        r14.updateTranslation();
    L52:
        MessageObject r142 = this.message;
        if (r142 == null) goto L55;
        CharSequence r143 = r142.messageText;
    L57:
        if ((r143 instanceof Spannable) == false) goto L65;
        SpannableStringBuilder r15 = new SpannableStringBuilder(r143);
        float r16 = 17.0f;
        URLSpanNoUnderlineBold[] r4 = (URLSpanNoUnderlineBold[]) r15.getSpans(0, r15.length(), URLSpanNoUnderlineBold.class);
        int r144 = r4.length;
        float r17 = 16.0f;
        int r5 = 0;
    L59:
        if (r5 >= r144) goto L61;
        r15.removeSpan(r4[r5]);
        r5 = r5 + 1;
        r6 = r6;
        goto L59
    L61:
        float r18 = r6;
        URLSpanNoUnderline[] r42 = (URLSpanNoUnderline[]) r15.getSpans(0, r15.length(), URLSpanNoUnderline.class);
        int r52 = r42.length;
        int r62 = 0;
    L62:
        if (r62 >= r52) goto L64;
        r15.removeSpan(r42[r62]);
        r62 = r62 + 1;
        goto L62
    L64:
        r143 = r15;
    L66:
        this.lastMessageString = r143;
        if (this.isShareToStoryCell == false) goto L69;
        this.drawPinBackground = true;
        boolean r43 = false;
        r10 = false;
    L70:
        CustomDialog r53 = this.customDialog;
        if (r53 != null) goto L73;
        char r26 = '\n';
        TLRPC.Chat r54 = this.chat;
        if (r54 != null) goto L142;
    L150:
        CharSequence r55 = null;
    L152:
        if (this.useForceThreeLines == true) goto L161;
        if (SharedConfig.useThreeLinesLayout == true) goto L161;
        if (LocaleController.isRTL == true) goto L159;
        this.nameLeft = AndroidUtilities.dp(this.messagePaddingStart + 4);
    L164:
        TLRPC.EncryptedChat r11 = this.encryptedChat;
        int r3 = this.currentDialogFolderId;
        if (r11 == null) goto L181;
        if (r3 != 0) goto L175;
        this.drawNameLock = true;
        if (this.useForceThreeLines == false) goto L170;
    L177:
        this.nameLockTop = AndroidUtilities.dp(12.5f);
        if (LocaleController.isRTL == true) goto L180;
        this.nameLockLeft = AndroidUtilities.dp(this.messagePaddingStart + 6);
        this.nameLeft = AndroidUtilities.dp(this.messagePaddingStart + 10) + Theme.dialogs_lockDrawable.getIntrinsicWidth();
        goto L175
    L180:
        this.nameLockLeft = (getMeasuredWidth() - AndroidUtilities.dp(this.messagePaddingStart + 6)) - Theme.dialogs_lockDrawable.getIntrinsicWidth();
        this.nameLeft = AndroidUtilities.dp(22.0f);
        goto L175
    L170:
        if (SharedConfig.useThreeLinesLayout == true) goto L177;
        this.nameLockTop = AndroidUtilities.dp(16.5f);
        if (LocaleController.isRTL == true) goto L176;
        this.nameLockLeft = AndroidUtilities.dp(this.messagePaddingStart + 4);
        this.nameLeft = AndroidUtilities.dp(this.messagePaddingStart + 8) + Theme.dialogs_lockDrawable.getIntrinsicWidth();
        goto L175
    L176:
        this.nameLockLeft = (getMeasuredWidth() - AndroidUtilities.dp(this.messagePaddingStart + 4)) - Theme.dialogs_lockDrawable.getIntrinsicWidth();
        this.nameLeft = AndroidUtilities.dp(18.0f);
    L175:
        boolean r30 = r10;
        int r31 = 2;
        CharSequence r34 = r143;
    L286:
        int r2 = this.lastMessageDate;
        if (r2 != 0) goto L292;
        MessageObject r32 = this.message;
        if (r32 == null) goto L292;
        r2 = r32.messageOwner.date;
    L292:
        if (this.isTopic == false) goto L308;
        if (MediaDataController.getInstance(this.currentAccount).getDraftVoice(this.currentDialogId, getTopicId()) == null) goto L296;
        boolean r33 = true;
    L297:
        this.draftVoice = r33;
        if (r33 == true) goto L300;
        TLRPC.DraftMessage r35 = MediaDataController.getInstance(this.currentAccount).getDraft(this.currentDialogId, getTopicId());
    L301:
        this.draftMessage = r35;
        if (r35 == null) goto L322;
        if (TextUtils.isEmpty(r35.message) == false) goto L322;
        this.draftMessage = null;
    L322:
        boolean r36 = this.draftVoice;
        if (r36 == false) goto L325;
    L326:
        if (r36 == true) goto L339;
        TLRPC.DraftMessage r37 = this.draftMessage;
        if (r37 == null) goto L339;
        if (TextUtils.isEmpty(r37.message) == false) goto L339;
        TLRPC.DraftMessage r38 = this.draftMessage;
        if (r38.rich_message != null) goto L339;
        TLRPC.InputReplyTo r39 = r38.reply_to;
        if (r39 != null) goto L336;
    L338:
        TLRPC.DraftMessage r112 = null;
        this.draftMessage = null;
        boolean r93 = false;
        this.draftVoice = false;
    L371:
        if (isForumCell() == false) goto L405;
        this.draftMessage = r112;
        this.draftVoice = r93;
        this.needEmoji = true;
        updateMessageThumbs();
        if (ChatObject.isMonoForum(this.chat) == false) goto L375;
        CharSequence r04 = null;
    L376:
        CharSequence r05 = addSenderAvatar(r04, this.message);
        if (ChatObject.isMonoForum(this.chat) == true) goto L379;
    L384:
        CharSequence r22 = formatTopicsNames();
        MessageObject r56 = this.message;
        if (r56 == null) goto L387;
        String r57 = MessagesController.getInstance(r56.currentAccount).getRestrictionReason(this.message.messageOwner.restriction_reason);
    L389:
        if (this.message == null) goto L391;
        CharSequence r58 = getMessageStringFormatted(r12, r57, r05, true);
    L393:
        if (this.applyName == true) goto L395;
    L400:
        CharSequence r59 = r58;
        if (shouldDrawForwardPreviewIcon(this.message) == false) goto L403;
        SpannableStringBuilder r510 = SpannableStringBuilder.valueOf(r58);
        prependForwardedIcon(r510, 0);
        r59 = r510;
    L403:
        this.currentMessagePaint = Theme.dialogs_messagePaint[this.paintIndex];
        CharSequence r28 = r59;
        CharSequence r94 = _UrlKt.FRAGMENT_ENCODE_SET;
        boolean r102 = r30;
        boolean r511 = true;
        int r19 = -1;
        CharSequence r23 = r22;
    L1071:
        CharSequence r24 = r23;
        if (this.drawForwardIcon == true) goto L1094;
        r24 = r23;
        if (isFolderCell() == true) goto L1094;
        r24 = r23;
        if (isForumCell() == true) goto L1094;
        r24 = r23;
        if (isDialogFolder() == true) goto L1094;
        r24 = r23;
        if (this.draftMessage != null) goto L1094;
        MessageObject r310 = this.message;
        r24 = r23;
        if (r310 == null) goto L1094;
        TLRPC.Message r311 = r310.messageOwner;
        r24 = r23;
        if (r311 == null) goto L1094;
        r24 = r23;
        if ((r311.action instanceof TLRPC.TL_messageActionStarGift) == false) goto L1094;
        this.drawGiftIcon = true;
        SpannableStringBuilder r312 = new SpannableStringBuilder(r23);
        r312.insert(0, "d ");
        ColoredImageSpan r25 = new ColoredImageSpan(ContextCompat.getDrawable(getContext(), R.drawable.mini_gift).mutate());
        r25.setScale(1.25f, 1.25f);
        r25.spaceScaleX = 0.9f;
        r25.setAlpha(0.9f);
        r312.setSpan(r25, 0, 1, 0);
        TLRPC.TL_textWithEntities r27 = ((TLRPC.TL_messageActionStarGift) this.message.messageOwner.action).message;
        if (r27 != null) goto L1090;
    L1092:
        r24 = r312;
        goto L1094
    L1090:
        if (TextUtils.isEmpty(r27.text) == true) goto L1092;
        this.currentMessagePaint = Theme.dialogs_messagePaint[this.paintIndex];
    L1094:
        if (TextUtils.isEmpty(this.customMessage) == true) goto L1097;
    L1095:
        String r313 = _UrlKt.FRAGMENT_ENCODE_SET;
    L1105:
        MessageObject r8 = this.message;
        if (r8 == null) goto L1214;
        if (this.isSavedDialog == true) goto L1214;
        if (this.currentDialogFolderId == 0) goto L1121;
        int r82 = this.unreadCount;
        int r113 = this.mentionCount;
        if ((r82 + r113) <= 0) goto L1118;
        if (r82 <= r113) goto L1116;
        this.drawCount = true;
        boolean r145 = false;
        this.drawMention = false;
        String r114 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r82 + r113)});
        String r83 = null;
    L1119:
        this.drawReactionMention = r145;
        this.drawPollVotesMention = r145;
    L1162:
        if (this.message.isOut() == true) goto L1164;
    L1212:
        boolean r146 = false;
        this.drawCheck1 = false;
        this.drawCheck2 = false;
        this.drawClock = false;
        this.drawError = false;
    L1213:
        String r103 = r83;
        String r84 = r114;
    L1223:
        this.promoDialog = r146;
        MessagesController r115 = MessagesController.getInstance(this.currentAccount);
        if (this.dialogsType == 0) goto L1226;
    L1239:
        boolean r147 = false;
        CharSequence r29 = r24;
    L1238:
        String r552 = r313;
        CharSequence r314 = r29;
        String r210 = r552;
    L1241:
        if (this.isForChannelSubscriberCell == false) goto L1243;
        this.drawCheck1 = r147;
        this.drawCheck2 = r147;
        this.drawClock = r147;
        this.drawError = r147;
    L1243:
        CharSequence r122 = this.titleOverride;
        if (r122 == null) goto L1247;
    L1245:
        CharSequence r332 = r94;
        r92 = r103;
        int r72 = r19;
        CharSequence r63 = r28;
        String r104 = r210;
        CharSequence r211 = r05;
        CharSequence r123 = r122;
    L1319:
        if (r43 == false) goto L1328;
        TextPaint r06 = getTimeTextPaint();
        float r272 = 24.0f;
        int r44 = (int) Math.ceil(r06.measureText(r104));
        this.timeLayout = new StaticLayout(r104, r06, r44, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        if (getIsPinned() == false) goto L1323;
        float r07 = 24.0f;
    L1324:
        int r08 = r44 + AndroidUtilities.dp(r07);
        if (LocaleController.isRTL == true) goto L1327;
        this.timeLeft = (getMeasuredWidth() - AndroidUtilities.dp(r18)) - r08;
    L1329:
        float r105 = 4.0f;
        if (drawLock2() == false) goto L1336;
        boolean r45 = LocaleController.isRTL;
        int r13 = this.timeLeft;
        if (r45 == false) goto L1334;
        this.lock2Left = (r13 + r08) + AndroidUtilities.dp(4.0f);
    L1335:
        int r46 = Theme.dialogs_lock2Drawable.getIntrinsicWidth() + AndroidUtilities.dp(4.0f);
        r08 = r08 + r46;
    L1338:
        if (LocaleController.isRTL == true) goto L1340;
        this.nameWidth = ((getMeasuredWidth() - this.nameLeft) - AndroidUtilities.dp(22.0f)) - r08;
    L1342:
        if (this.drawNameLock == false) goto L1348;
        int r132 = this.nameWidth;
        if (LocaleController.isRTL == false) goto L1346;
        r105 = 8.0f;
    L1346:
        this.nameWidth = r132 - (AndroidUtilities.dp(r105) + Theme.dialogs_lockDrawable.getIntrinsicWidth());
    L1348:
        if (this.drawClock == false) goto L1355;
        int r106 = Theme.dialogs_clockDrawable.getIntrinsicWidth() + AndroidUtilities.dp(5.0f);
        this.nameWidth -= r106;
        boolean r133 = LocaleController.isRTL;
        int r116 = this.timeLeft;
        if (r133 == true) goto L1352;
        this.clockDrawLeft = (r116 - r46) - r106;
    L1366:
        this.nameAdditionalsForChannelSubscriber = 0;
        if (this.dialogMuted == true) goto L1375;
        if (this.isHiddenInCommunity == true) goto L1375;
        if (this.drawUnmute == true) goto L1375;
        if (this.dialogMutedProgress > 0.0f) goto L1375;
    L1381:
        boolean r09 = false;
    L1383:
        if (this.drawPremium == true) goto L1385;
    L1392:
        if (r09 == false) goto L1400;
        int r010 = AndroidUtilities.dp(6.0f) + Theme.dialogs_muteDrawable.getIntrinsicWidth();
        if (this.drawPremium == false) goto L1396;
        r010 = r010 + AndroidUtilities.dp(36.0f);
    L1396:
        this.nameWidth -= r010;
        this.nameAdditionalsForChannelSubscriber += r010;
        if (LocaleController.isRTL == false) goto L1424;
        this.nameLeft += r010;
    L1424:
        if (this.drawBotVerified == false) goto L1426;
        this.nameWidth -= AndroidUtilities.dp(21.0f);
    L1426:
        int r011 = this.namePaddingEnd;
        if (r011 <= 0) goto L2154;
        this.nameWidth -= r011;
        if (LocaleController.isRTL == false) goto L2154;
        this.nameLeft += r011;
    L2154:
        int r012 = this.nameWidth - AndroidUtilities.dp(12.0f);     // Catch: Exception -> L1438
        if (r012 >= 0) goto L1435;
        r012 = 0;
    L1435:
        boolean r47 = r123 instanceof String;     // Catch: Exception -> L1438
        CharSequence r124 = r123;
        if (r47 == false) goto L2138;
        r124 = ((String) r123).replace(r26, ' ');     // Catch: Exception -> L1438
    L2138:
        if (this.nameLayoutEllipsizeByGradient == false) goto L1451;
        r26 = 0;
        if (r124.length() != TextUtils.ellipsize(r124, Theme.dialogs_namePaint[this.paintIndex], r012, TextUtils.TruncateAt.END).length()) goto L1447;
        boolean r48 = true;
    L1448:
        this.nameLayoutFits = r48;     // Catch: Exception -> L1449
        r012 = r012 + AndroidUtilities.dp(48.0f);     // Catch: Exception -> L1449
        this.channelShouldUseLineWidth = this.nameLayoutFits;     // Catch: Exception -> L1449
    L1458:
        float r107 = r012;
        if (Theme.dialogs_namePaint[this.paintIndex].measureText(r124.toString()) <= r107) goto L1461;
        boolean r49 = true;
    L1462:
        this.nameIsEllipsized = r49;     // Catch: Exception -> L1449
        CharSequence r125 = r124;
        if (this.twoLinesForName == true) goto L1465;
        r125 = TextUtils.ellipsize(r124, Theme.dialogs_namePaint[this.paintIndex], r107, TextUtils.TruncateAt.END);     // Catch: Exception -> L1449
    L1465:
        CharSequence r410 = Emoji.replaceEmoji(r125, Theme.dialogs_namePaint[this.paintIndex].getFontMetricsInt(), false);     // Catch: Exception -> L1449
        MessageObject r108 = this.message;     // Catch: Exception -> L1449
        if (r108 != null) goto L1468;
    L1472:
        CharSequence r442 = r410;
    L1474:
        if (this.twoLinesForName == false) goto L1476;
        this.nameLayout = StaticLayoutEx.createStaticLayout(r442, Theme.dialogs_namePaint[this.paintIndex], r012, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, r012, 2);     // Catch: Exception -> L1449
    L1478:
        if (this.nameLayoutEllipsizeByGradient == true) goto L1480;
    L1482:
        float r013 = 0.0f;
    L1483:
        this.nameLayoutTranslateX = r013;     // Catch: Exception -> L1449
        this.nameLayoutEllipsizeLeft = this.nameLayout.isRtlCharAt(0);     // Catch: Exception -> L1449
    L1486:
        this.animatedEmojiStackName = AnimatedEmojiSpan.update(0, this, this.animatedEmojiStackName, new Layout[]{this.nameLayout});
        if (this.useForceThreeLines == false) goto L1489;
    L1490:
        float r222 = 8.0f;
        float r443 = 0.0f;
        float r20 = 13.0f;
        float r422 = 20.0f;
        int r014 = AndroidUtilities.dp(11.0f);
        this.messageNameTop = AndroidUtilities.dp(32.0f);
        this.timeTop = AndroidUtilities.dp(13.0f);
        this.errorTop = AndroidUtilities.dp(42.33f);
        this.pinTop = AndroidUtilities.dp(43.0f);
        this.countTop = AndroidUtilities.dp(42.33f);
        this.checkDrawTop = AndroidUtilities.dp(13.0f);
        int r117 = getMeasuredWidth() - AndroidUtilities.dp(this.messagePaddingStart + 21);
        if (LocaleController.isRTL == false) goto L1526;
        int r411 = AndroidUtilities.dp(r17);
        this.messageNameLeft = r411;
        this.messageLeft = r411;
        this.typingLeft = r411;
        this.buttonLeft = r411;
        int r412 = getMeasuredWidth() - AndroidUtilities.dp(this.avatarStart + 56);
        int r109 = r412 - AndroidUtilities.dp(31.0f);
    L1527:
        this.storyParams.originalAvatarRect.set(r412, r014, r412 + AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f) + r014);
        int r413 = 0;
    L1528:
        ImageReceiver[] r126 = this.thumbImage;
        if (r413 >= r126.length) goto L1545;
        ImageReceiver r127 = r126[r413];
        float r134 = ((this.thumbSize + 2) * r413) + r109;
        int r148 = AndroidUtilities.dp(31.0f) + r014;
        if (this.twoLinesForName == false) goto L1533;
        int r152 = AndroidUtilities.dp(20.0f);
    L1534:
        int r149 = r148 + r152;
        if (this.useForceThreeLines == false) goto L1537;
    L1543:
        int r153 = 0;
    L1544:
        r127.setImageCoords(r134, r149 - r153, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f));
        r413 = r413 + 1;
        r014 = r014;
        goto L1528
    L1537:
        if (SharedConfig.useThreeLinesLayout == true) goto L1543;
        DialogCellTags r154 = this.tags;
        if (r154 == null) goto L1543;
        if (r154.isEmpty() == true) goto L1543;
        r153 = AndroidUtilities.dp(9.0f);
        goto L1544
    L1533:
        r152 = 0;
        goto L1534
    L1545:
        int r172 = r014;
    L1547:
        if (LocaleController.isRTL == false) goto L1549;
        this.tagsRight = getMeasuredWidth() - AndroidUtilities.dp(this.messagePaddingStart);
        this.tagsLeft = AndroidUtilities.dp(64.0f);
    L1551:
        if (this.twoLinesForName == false) goto L1554;
        this.messageNameTop += AndroidUtilities.dp(r422);
    L1554:
        if (this.useForceThreeLines == true) goto L1558;
        if (SharedConfig.useThreeLinesLayout == true) goto L1558;
    L1559:
        DialogCellTags r015 = this.tags;
        if (r015 == null) goto L1565;
        if (r015.isEmpty() == true) goto L1565;
        this.timeTop -= AndroidUtilities.dp(6.0f);
        this.checkDrawTop -= AndroidUtilities.dp(6.0f);
    L1565:
        if (getIsPinned() == false) goto L1571;
        if (LocaleController.isRTL == true) goto L1569;
        this.pinLeft = (getMeasuredWidth() - Theme.dialogs_pinnedDrawable.getIntrinsicWidth()) - AndroidUtilities.dp(14.0f);
        goto L1571
    L1569:
        this.pinLeft = AndroidUtilities.dp(14.0f);
    L1571:
        if (this.drawError == false) goto L1576;
        int r016 = AndroidUtilities.dp(29.0f);
        r117 = r117 - r016;
        if (LocaleController.isRTL == true) goto L1575;
        this.errorLeft = getMeasuredWidth() - AndroidUtilities.dp(36.3333f);
    L1712:
        if (r511 == false) goto L1733;
        if (r314 != null) goto L1716;
        r314 = _UrlKt.FRAGMENT_ENCODE_SET;
    L1716:
        if (r314.length() <= 150) goto L1719;
        r314 = r314.subSequence(0, 150);
    L1719:
        if (this.useForceThreeLines == true) goto L1723;
        if (SharedConfig.useThreeLinesLayout == true) goto L1723;
    L1727:
        CharSequence r017 = AndroidUtilities.replaceNewLines(r314);
    L1728:
        r314 = Emoji.replaceEmoji(r017, Theme.dialogs_messagePaint[this.paintIndex].getFontMetricsInt(), false);
        MessageObject r018 = this.message;
        if (r018 == null) goto L1733;
        CharSequence r019 = AndroidUtilities.highlightText(r314, r018.highlightedWords, this.resourcesProvider);
        if (r019 == null) goto L1733;
        r314 = r019;
    L1723:
        if (hasTags() == true) goto L1727;
        if (r211 != null) goto L1727;
        r017 = AndroidUtilities.replaceTwoNewLinesToOne(r314);
    L1733:
        int r472 = Math.max(AndroidUtilities.dp(12.0f), r117);
        if (this.useForceThreeLines == false) goto L1736;
    L1739:
        float r020 = 58.0f;
    L1740:
        this.buttonTop = AndroidUtilities.dp(r020);
        if (this.useForceThreeLines == true) goto L1745;
        if (SharedConfig.useThreeLinesLayout == true) goto L1745;
    L1747:
        if (hasTags() == false) goto L1753;
        int r021 = this.buttonTop;
        if (isForumCell() == false) goto L1751;
        float r414 = 10.0f;
    L1752:
        this.buttonTop = r021 - AndroidUtilities.dp(r414);
        goto L1753
    L1751:
        r414 = 12.0f;
    L1753:
        boolean r022 = isForumCell();
        boolean r415 = this.useForceThreeLines;
        if (r022 == false) goto L1765;
        if (r415 == false) goto L1757;
    L1760:
        float r023 = 34.0f;
    L1761:
        this.messageTop = AndroidUtilities.dp(r023);
        int r024 = 0;
    L1762:
        ImageReceiver[] r416 = this.thumbImage;
        if (r024 >= r416.length) goto L1820;
        r416[r024].setImageY(this.buttonTop);
        r024 = r024 + 1;
    L1820:
        CharSequence r282 = r211;
    L1822:
        if (this.twoLinesForName == false) goto L1824;
        this.messageTop += AndroidUtilities.dp(r422);
    L1824:
        this.animatedEmojiStack2 = AnimatedEmojiSpan.update(0, this, this.animatedEmojiStack2, new Layout[]{this.messageNameLayout});
        this.buttonCreated = false;     // Catch: Exception -> L2133
        if (TextUtils.isEmpty(r63) == true) goto L1828;
        this.buttonLayout = new StaticLayout(TextUtils.ellipsize(Emoji.replaceEmoji(r63, this.currentMessagePaint.getFontMetricsInt(), false), this.currentMessagePaint, r472 - AndroidUtilities.dp(26.0f), TextUtils.TruncateAt.END), this.currentMessagePaint, r472 - AndroidUtilities.dp(r422), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L2133
        this.spoilersPool2.addAll(this.spoilers2);     // Catch: Exception -> L2133
        this.spoilers2.clear();     // Catch: Exception -> L2133
        SpoilerEffect.addSpoilers(this, this.buttonLayout, this.spoilersPool2, this.spoilers2);     // Catch: Exception -> L2133
    L2147:
        goto L1829
    L1828:
        this.buttonLayout = null;     // Catch: Exception -> L2133
    L1829:
        this.animatedEmojiStack3 = AnimatedEmojiSpan.update(0, this, this.animatedEmojiStack3, new Layout[]{this.buttonLayout});
    L1838:
        e = e;
    L1856:
        FileLog.e(e);
    L2142:
    L1872:
        e = e;
    L1976:
        this.messageLayout = null;
        FileLog.e(e);
    L1971:
        int r025 = r472;
        this.animatedEmojiStack = AnimatedEmojiSpan.update(0, this, this.animatedEmojiStack, new Layout[]{this.messageLayout});
        boolean r212 = LocaleController.isRTL;
        StaticLayout r315 = this.nameLayout;
        if (r212 == false) goto L2067;
        if (r315 != null) goto L1981;
    L2026:
        StaticLayout r213 = this.messageLayout;
        int r316 = Integer.MAX_VALUE;
        if (r213 == null) goto L2038;
        int r214 = r213.getLineCount();
        if (r214 <= 0) goto L2038;
        int r512 = Integer.MAX_VALUE;
        int r417 = 0;
    L2031:
        if (r417 >= r214) goto L2036;
        if (this.messageLayout.getLineLeft(r417) != r443) goto L2035;
        r512 = Math.min(r512, (int) (r025 - Math.ceil(this.messageLayout.getLineWidth(r417))));
        r417 = r417 + 1;
        goto L2031
    L2035:
        r512 = 0;
    L2036:
        if (r512 == Integer.MAX_VALUE) goto L2038;
        this.messageLeft += r512;
    L2038:
        StaticLayout r215 = this.typingLayout;
        if (r215 == null) goto L2050;
        int r216 = r215.getLineCount();
        if (r216 <= 0) goto L2050;
        int r513 = Integer.MAX_VALUE;
        int r418 = 0;
    L2043:
        if (r418 >= r216) goto L2048;
        if (this.typingLayout.getLineLeft(r418) != r443) goto L2047;
        r513 = Math.min(r513, (int) (r025 - Math.ceil(this.typingLayout.getLineWidth(r418))));
        r418 = r418 + 1;
        goto L2043
    L2047:
        r513 = 0;
    L2048:
        if (r513 == Integer.MAX_VALUE) goto L2050;
        this.typingLeft += r513;
    L2050:
        StaticLayout r217 = this.messageNameLayout;
        if (r217 != null) goto L2053;
    L2059:
        StaticLayout r026 = this.buttonLayout;
        if (r026 == null) goto L2116;
        int r027 = r026.getLineCount();
        if (r027 <= 0) goto L2116;
        int r218 = 0;
    L2064:
        if (r218 >= r027) goto L2066;
        r316 = (int) Math.min(r316, this.buttonLayout.getWidth() - this.buttonLayout.getLineRight(r218));
        r218 = r218 + 1;
        goto L2064
    L2066:
        this.buttonLeft += r316;
    L2116:
        StaticLayout r028 = this.typingLayout;
        if (r028 != null) goto L2119;
    L2131:
        updateThumbsPosition();
        return;
    L2119:
        if (this.printingStringType < 0) goto L2131;
        if (r028.getText().length() <= 0) goto L2131;
        if (r72 < 0) goto L2126;
        int r029 = r72 + 1;
        if (r029 >= this.typingLayout.getText().length()) goto L2126;
        float r219 = this.typingLayout.getPrimaryHorizontal(r72);
        float r030 = this.typingLayout.getPrimaryHorizontal(r029);
    L2127:
        int r419 = this.typingLeft;
        if (r219 >= r030) goto L2130;
        this.statusDrawableLeft = (int) (r419 + r219);
        goto L2131
    L2130:
        this.statusDrawableLeft = (int) ((r419 + r030) + AndroidUtilities.dp(3.0f));
    L2126:
        r219 = this.typingLayout.getPrimaryHorizontal(0);
        r030 = this.typingLayout.getPrimaryHorizontal(1);
        goto L2127
    L2053:
        if (r217.getLineCount() <= 0) goto L2059;
        if (this.messageNameLayout.getLineLeft(0) != r443) goto L2059;
        double r420 = Math.ceil(this.messageNameLayout.getLineWidth(0));
        double r85 = r025;
        if (r420 >= r85) goto L2059;
        this.messageNameLeft = (int) (this.messageNameLeft + (r85 - r420));
        goto L2059
    L1981:
        if (r315.getLineCount() <= 0) goto L2026;
        float r220 = this.nameLayout.getLineLeft(0);
        double r317 = Math.ceil(this.nameLayout.getLineWidth(0));
        int r514 = this.nameLeft + AndroidUtilities.dp(12.0f);
        this.nameLeft = r514;
        if (this.drawBotVerified == false) goto L1986;
        this.nameLeft = r514 + AndroidUtilities.dp(21.0f);
    L1986:
        if (this.nameLayoutEllipsizeByGradient == false) goto L1989;
        r317 = Math.min(this.nameWidth, r317);
    L1989:
        if (this.dialogMuted == true) goto L1995;
        if (this.drawUnmute == true) goto L1995;
        if (this.dialogMutedProgress > r443) goto L1995;
    L2005:
        if (this.drawVerified == false) goto L2008;
        this.nameMuteLeft = (int) (((this.nameLeft + (this.nameWidth - r317)) - AndroidUtilities.dp(6.0f)) - Theme.dialogs_verifiedDrawable.getIntrinsicWidth());
    L2022:
        if (r220 != r443) goto L2026;
        int r221 = this.nameWidth;
        if (r317 >= r221) goto L2026;
        this.nameLeft = (int) (this.nameLeft + (r221 - r317));
        goto L2026
    L2008:
        if (this.drawBadge == false) goto L2011;
        this.nameMuteLeft = (int) (((this.nameLeft + (this.nameWidth - r317)) - AndroidUtilities.dp(6.0f)) - AndroidUtilities.dp(r272));
        goto L2022
    L2011:
        if (this.drawPremium == false) goto L2013;
        int r515 = (int) ((this.nameLeft + ((this.nameWidth - r317) - r220)) - AndroidUtilities.dp(r272));
        this.nameMuteLeft = r515;
        this.nameMutedIconLeft = (r515 - AndroidUtilities.dp(6.0f)) - Theme.dialogs_muteDrawable.getIntrinsicWidth();
        goto L2022
    L2013:
        int r516 = this.drawScam;
        int r64 = this.nameLeft;
        if (r516 == 0) goto L2020;
        double r517 = (r64 + (this.nameWidth - r317)) - AndroidUtilities.dp(6.0f);
        if (this.drawScam != 1) goto L2018;
        ScamDrawable r86 = Theme.dialogs_scamDrawable;
    L2019:
        this.nameMuteLeft = (int) (r517 - r86.getIntrinsicWidth());
        goto L2022
    L2018:
        r86 = Theme.dialogs_fakeDrawable;
        goto L2019
    L2020:
        this.nameMuteLeft = (int) (((r64 + (this.nameWidth - r317)) - AndroidUtilities.dp(6.0f)) - Theme.dialogs_muteDrawable.getIntrinsicWidth());
    L1995:
        if (this.drawVerified == true) goto L2005;
        if (this.drawBadge == true) goto L2005;
        if (this.drawScam != 0) goto L2005;
        boolean r518 = this.drawPremium;
        int r65 = this.nameLeft;
        if (r518 == false) goto L2003;
        int r519 = (int) ((r65 + ((this.nameWidth - r317) - r220)) - AndroidUtilities.dp(r272));
        this.nameMuteLeft = r519;
        this.nameMutedIconLeft = (r519 - AndroidUtilities.dp(6.0f)) - Theme.dialogs_muteDrawable.getIntrinsicWidth();
        goto L2022
    L2003:
        this.nameMuteLeft = (int) (((r65 + (this.nameWidth - r317)) - AndroidUtilities.dp(6.0f)) - Theme.dialogs_muteDrawable.getIntrinsicWidth());
        goto L2022
    L2067:
        if (r315 != null) goto L2069;
    L2087:
        StaticLayout r031 = this.messageLayout;
        if (r031 == null) goto L2095;
        int r032 = r031.getLineCount();
        if (r032 <= 0) goto L2095;
        float r223 = 2.1474836E9f;
        int r318 = 0;
    L2092:
        if (r318 >= r032) goto L2094;
        r223 = Math.min(r223, this.messageLayout.getLineLeft(r318));
        r318 = r318 + 1;
        goto L2092
    L2094:
        this.messageLeft = (int) (this.messageLeft - r223);
    L2095:
        StaticLayout r033 = this.buttonLayout;
        if (r033 == null) goto L2103;
        int r034 = r033.getLineCount();
        if (r034 <= 0) goto L2103;
        float r224 = 2.1474836E9f;
        int r319 = 0;
    L2100:
        if (r319 >= r034) goto L2102;
        r224 = Math.min(r224, this.buttonLayout.getLineLeft(r319));
        r319 = r319 + 1;
        goto L2100
    L2102:
        this.buttonLeft = (int) (this.buttonLeft - r224);
    L2103:
        StaticLayout r035 = this.typingLayout;
        if (r035 == null) goto L2111;
        int r036 = r035.getLineCount();
        if (r036 <= 0) goto L2111;
        float r225 = 2.1474836E9f;
        int r320 = 0;
    L2108:
        if (r320 >= r036) goto L2110;
        r225 = Math.min(r225, this.typingLayout.getLineLeft(r320));
        r320 = r320 + 1;
        goto L2108
    L2110:
        this.typingLeft = (int) (this.typingLeft - r225);
    L2111:
        StaticLayout r037 = this.messageNameLayout;
        if (r037 == null) goto L2116;
        if (r037.getLineCount() <= 0) goto L2116;
        this.messageNameLeft = (int) (this.messageNameLeft - this.messageNameLayout.getLineLeft(0));
        goto L2116
    L2069:
        if (r315.getLineCount() <= 0) goto L2087;
        float r038 = this.nameLayout.getLineRight(0);
        if (this.nameLayoutEllipsizeByGradient == false) goto L2074;
        r038 = Math.min(this.nameWidth, r038);
    L2074:
        if (r038 != this.nameWidth) goto L2082;
        double r226 = Math.ceil(this.nameLayout.getLineWidth(0));
        if (this.nameLayoutEllipsizeByGradient == false) goto L2078;
        r226 = Math.min(this.nameWidth, r226);
    L2078:
        int r421 = this.nameWidth;
        if (r226 >= r421) goto L2082;
        this.nameLeft = (int) (this.nameLeft - (r421 - r226));
    L2082:
        if (this.drawBotVerified == false) goto L2084;
        this.nameLeft += AndroidUtilities.dp(21.0f);
    L2084:
        int r039 = (int) ((this.nameLeft + r038) + AndroidUtilities.dp(6.0f));
        this.nameMuteLeft = r039;
        if (this.drawPremium == false) goto L2087;
        this.nameMutedIconLeft = r039 + AndroidUtilities.dp(30.0f);
        goto L2087
    L1858:
        if ((r314 instanceof Spannable) == false) goto L1894;
        Spannable r040 = (Spannable) r314;     // Catch: Exception -> L1872
        Object[] r227 = r040.getSpans(0, r040.length(), Object.class);     // Catch: Exception -> L1872
        int r423 = r227.length;     // Catch: Exception -> L1872
        int r520 = 0;
    L1860:
        if (r520 >= r423) goto L1894;
        Object r66 = r227[r520];     // Catch: Exception -> L1872
        if ((r66 instanceof ClickableSpan) == true) goto L1886;
        if ((r66 instanceof CodeHighlighting.Span) == true) goto L1886;
        if (this.currentDialogCommunityId != 0) goto L1875;
        if (isFolderCell() == true) goto L1875;
        if ((r66 instanceof TypefaceSpan) == true) goto L1886;
    L1875:
        if ((r66 instanceof CodeHighlighting.ColorSpan) == true) goto L1886;
        if ((r66 instanceof QuoteSpan) == true) goto L1886;
        if ((r66 instanceof QuoteSpan.QuoteStyleSpan) == true) goto L1886;
        if ((r66 instanceof StyleSpan) == true) goto L1883;
    L1888:
        if (Theme.isCurrentThemeMonet() == false) goto L1892;
        if ((r66 instanceof URLSpanMono) == false) goto L1892;
        ((URLSpanMono) r66).setCurrentType((byte) 2);     // Catch: Exception -> L1872
    L1892:
        r520 = r520 + 1;     // Catch: Exception -> L1872
        goto L1860
    L1883:
        if (((StyleSpan) r66).getStyle() != 1) goto L1888;
    L1886:
        r040.removeSpan(r66);     // Catch: Exception -> L1872
    L1894:
        if (this.useForceThreeLines == true) goto L1898;
        if (SharedConfig.useThreeLinesLayout == true) goto L1898;
    L1905:
        if (this.useForceThreeLines == true) goto L1909;
        if (SharedConfig.useThreeLinesLayout == true) goto L1909;
    L1916:
        if (isForumCell() == false) goto L1918;
    L1922:
        r314 = TextUtils.ellipsize(r314, this.currentMessagePaint, r472 - AndroidUtilities.dp(12.0f), TextUtils.TruncateAt.END);     // Catch: Exception -> L1872
    L1924:
        if (separateMessageNameLine() == true) goto L1926;
    L1930:
        CharSequence r333 = r314;
    L1932:
        if (this.isForum == true) goto L1934;
    L1937:
        Layout.Alignment r041 = Layout.Alignment.ALIGN_NORMAL;     // Catch: Exception -> L1872
    L1936:
        Layout.Alignment r362 = r041;
        if (this.useForceThreeLines == true) goto L1943;
        if (SharedConfig.useThreeLinesLayout == true) goto L1943;
    L1959:
        if (this.thumbsCount <= 0) goto L2150;
        r472 = r472 + AndroidUtilities.dp((r0 * (this.thumbSize + 2)) + 3);     // Catch: Exception -> L1872
        if (LocaleController.isRTL == false) goto L2150;
        if (isForumCell() == true) goto L2150;
        this.messageLeft -= AndroidUtilities.dp((this.thumbsCount * (this.thumbSize + 2)) + 3);
    L2150:
        int r363 = r472;
        this.messageLayout = new StaticLayout(r333, this.currentMessagePaint, r363, r362, 1.0f, 0.0f, false);     // Catch: Exception -> L1972
        r472 = r363;
    L1970:
        this.spoilersPool.addAll(this.spoilers);     // Catch: Exception -> L1872
        this.spoilers.clear();     // Catch: Exception -> L1872
        SpoilerEffect.addSpoilers(this, this.messageLayout, -2, -2, this.spoilersPool, this.spoilers);     // Catch: Exception -> L1872
    L1972:
        e = e;
        r472 = r363;
    L1974:
        e = e;
    L1943:
        if (hasTags() == true) goto L1959;
        if (this.thumbsCount <= 0) goto L1949;
        if (r282 == null) goto L1949;
        r472 = r472 + AndroidUtilities.dp(5.0f);
    L1949:
        int r352 = r472;
        TextPaint r042 = this.currentMessagePaint;     // Catch: Exception -> L1956
        float r228 = AndroidUtilities.dp(1.0f);     // Catch: Exception -> L1956
        TextUtils.TruncateAt r40 = TextUtils.TruncateAt.END;     // Catch: Exception -> L1956
        if (r282 == null) goto L1953;
        int r424 = 1;
    L1954:
        this.messageLayout = StaticLayoutEx.createStaticLayout(r333, r042, r352, r362, 1.0f, r228, false, r40, r352, r424);     // Catch: Exception -> L1956
        r472 = r352;
        goto L1970
    L1953:
        r424 = 2;
    L1956:
        e = e;
        r472 = r352;
        goto L1976
    L1934:
        if (LocaleController.isRTL == false) goto L1937;
        r041 = Layout.Alignment.ALIGN_OPPOSITE;     // Catch: Exception -> L1872
        goto L1936
    L1926:
        if (isForumCell() == true) goto L1930;
        if (shouldDrawForwardPreviewIcon(this.message) == false) goto L1930;
        SpannableStringBuilder r043 = new SpannableStringBuilder(r314);     // Catch: Exception -> L1872
        prependForwardedIcon(r043, 0);     // Catch: Exception -> L1872
        r333 = r043;
        goto L1932
    L1918:
        if ((r314 instanceof Spanned) == false) goto L1922;
        if (((FixedWidthSpan[]) ((Spanned) r314).getSpans(0, r314.length(), FixedWidthSpan.class)).length > 0) goto L1922;
        r314 = TextUtils.ellipsize(r314, this.currentMessagePaint, r472 - AndroidUtilities.dp((this.thumbsCount * (this.thumbSize + 2)) + 15), TextUtils.TruncateAt.END);     // Catch: Exception -> L1872
    L1909:
        if (hasTags() == true) goto L1916;
        if (r282 != null) goto L1916;
        if (ChatObject.isMonoForum(this.chat) == false) goto L1924;
        if (ChatObject.canManageMonoForum(this.currentAccount, this.chat) == false) goto L1924;
    L1898:
        if (hasTags() == true) goto L1905;
        if (this.currentDialogFolderId == 0) goto L1905;
        if (this.currentDialogFolderDialogsCount <= 1) goto L1905;
        this.currentMessagePaint = Theme.dialogs_messagePaint[this.paintIndex];     // Catch: Exception -> L1872
        r314 = r282;
        r282 = null;
        goto L1924
    L1831:
        if (TextUtils.isEmpty(r332) == true) goto L2142;
        if (this.useForceThreeLines == true) goto L1841;
        if (SharedConfig.useThreeLinesLayout == true) goto L1841;
    L2152:
        this.typingLayout = new StaticLayout(TextUtils.ellipsize(r332, this.currentMessagePaint, r472 - AndroidUtilities.dp(12.0f), TextUtils.TruncateAt.END), Theme.dialogs_messagePrintingPaint[this.paintIndex], r472, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L1838
    L1854:
        e = e;
        r472 = r472;
    L1841:
        if (hasTags() == true) goto L2152;
        this.typingLayout = StaticLayoutEx.createStaticLayout(r332, Theme.dialogs_messagePrintingPaint[this.paintIndex], r472, Layout.Alignment.ALIGN_NORMAL, 1.0f, AndroidUtilities.dp(1.0f), false, TextUtils.TruncateAt.END, r472, 1);     // Catch: Exception -> L1838
    L1846:
        e = e;
        r472 = r472;
        goto L1856
    L1757:
        if (SharedConfig.useThreeLinesLayout == true) goto L1760;
        r023 = 39.0f;
        goto L1761
    L1765:
        if (r415 == true) goto L1771;
        if (SharedConfig.useThreeLinesLayout == true) goto L1771;
    L1804:
        this.messageNameLayout = null;
        if (this.useForceThreeLines == false) goto L1807;
    L1810:
        this.messageTop = AndroidUtilities.dp(32.0f);
        if (this.nameIsEllipsized == true) goto L1813;
    L1815:
        int r044 = 0;
    L1816:
        int r425 = 0;
    L1817:
        ImageReceiver[] r521 = this.thumbImage;
        if (r425 >= r521.length) goto L1820;
        r521[r425].setImageY((r172 + r044) + AndroidUtilities.dp(21.0f));
        r425 = r425 + 1;
        goto L1817
    L1813:
        if (this.isTopic == false) goto L1815;
        r044 = AndroidUtilities.dp(r422);
        goto L1816
    L1807:
        if (SharedConfig.useThreeLinesLayout == true) goto L1810;
        this.messageTop = AndroidUtilities.dp(39.0f);
    L1771:
        if (hasTags() == true) goto L1804;
        if (r211 == null) goto L1804;
        if (this.currentDialogFolderId != 0) goto L1776;
    L1777:
        CharSequence r229 = Emoji.replaceEmoji(r211, Theme.dialogs_messagePaint[this.paintIndex].getFontMetricsInt(), false);
        MessageObject r045 = this.message;     // Catch: Exception -> L1785
        if (r045 != null) goto L1781;
    L1787:
        CharSequence r452 = r229;
    L2156:
        this.messageNameLayout = StaticLayoutEx.createStaticLayout(r452, Theme.dialogs_messageNamePaint, r472, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, r472, 1);     // Catch: Exception -> L1790
    L1793:
        this.messageTop = AndroidUtilities.dp(51.0f);
        if (this.nameIsEllipsized == true) goto L1796;
    L1798:
        int r046 = 0;
    L1799:
        int r230 = 0;
    L1800:
        ImageReceiver[] r426 = this.thumbImage;
        if (r230 >= r426.length) goto L1803;
        r426[r230].setImageY((r172 + r046) + AndroidUtilities.dp(40.0f));
        r230 = r230 + 1;
        goto L1800
    L1803:
        r282 = r452;
        goto L1822
    L1796:
        if (this.isTopic == false) goto L1798;
        r046 = AndroidUtilities.dp(r422);
    L1790:
        e = e;
        r229 = r452;
    L1792:
        FileLog.e(e);
        r452 = r229;
        goto L1793
    L1781:
        if (r045.hasHighlightedWords() == false) goto L1787;
        CharSequence r047 = AndroidUtilities.highlightText(r229, this.message.highlightedWords, this.resourcesProvider);     // Catch: Exception -> L1785
        if (r047 == null) goto L1787;
        r452 = r047;
    L1785:
        e = e;
        goto L1792
    L1776:
        if (this.currentDialogFolderDialogsCount != 1) goto L1804;
    L1745:
        if (isForumCell() == false) goto L1753;
    L1736:
        if (SharedConfig.useThreeLinesLayout == true) goto L1739;
        r020 = 62.0f;
        goto L1740
    L1575:
        this.errorLeft = AndroidUtilities.dp(15.666f);
        this.messageLeft += r016;
        this.typingLeft += r016;
        this.buttonLeft += r016;
        this.messageNameLeft += r016;
        goto L1712
    L1576:
        if (r84 != null) goto L1612;
        if (r92 != null) goto L1612;
        if (this.drawReactionMention == true) goto L1612;
        if (this.drawPollVotesMention == true) goto L1612;
        if (this.allowBotOpenButton == true) goto L1586;
    L1611:
        this.drawCount = false;
        this.drawMention = false;
        goto L1712
    L1586:
        if (isFolderCell() == true) goto L1611;
        if (isForumCell() == true) goto L1611;
        if (isDialogFolder() == true) goto L1611;
        if (UserObject.isBot(this.user) == false) goto L1611;
        if (this.user.bot_has_main_app == false) goto L1611;
        setOpenBotButton(true);
        int r048 = (int) (AndroidUtilities.dp(26.0f) + this.openButtonText.getCurrentWidth());
        int r427 = AndroidUtilities.dp(r20);
        r117 = r117 - r048;
        if (this.useForceThreeLines == false) goto L1598;
    L1604:
        int r87 = AndroidUtilities.dp(40.0f);
    L1605:
        boolean r95 = LocaleController.isRTL;
        RectF r1010 = this.openButtonRect;
        if (r95 == true) goto L1609;
        r1010.set((getMeasuredWidth() - r048) - AndroidUtilities.dp(r20), r87, getMeasuredWidth() - AndroidUtilities.dp(r20), r87 + AndroidUtilities.dp(28.0f));
    L1610:
        this.drawCount = false;
        this.drawMention = false;
        goto L1712
    L1609:
        r1010.set(AndroidUtilities.dp(r20), r87, AndroidUtilities.dp(r20) + r048, r87 + AndroidUtilities.dp(28.0f));
        int r049 = r048 + r427;
        this.messageLeft += r049;
        this.typingLeft += r049;
        this.buttonLeft += r049;
        this.messageNameLeft += r049;
        goto L1610
    L1598:
        if (SharedConfig.useThreeLinesLayout == true) goto L1604;
        if (this.isTopic == false) goto L1603;
        r87 = AndroidUtilities.dp(33.0f);
        goto L1605
    L1603:
        r87 = AndroidUtilities.dp(r26);
    L1612:
        if (r84 == null) goto L1619;
        this.countWidth = Math.max(AndroidUtilities.dp(r222), (int) Math.ceil(Theme.dialogs_countTextPaint2.measureText(r84)));
        this.countLayout = new StaticLayout(r84, Theme.dialogs_countTextPaint2, this.countWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        int r050 = this.countWidth + AndroidUtilities.dp(r16);
        r117 = r117 - r050;
        if (LocaleController.isRTL == true) goto L1617;
        this.countLeft = (getMeasuredWidth() - AndroidUtilities.dp(15.666f)) - (this.countWidth + AndroidUtilities.dp(12.666f));
    L1618:
        this.drawCount = true;
    L1620:
        if (r92 != null) goto L1622;
        this.mentionWidth = 0;
    L1641:
        if (this.drawReactionMention == false) goto L1674;
        int r051 = AndroidUtilities.dp(25.0f);
        r117 = r117 - r051;
        if (LocaleController.isRTL == true) goto L1658;
        int r052 = getMeasuredWidth() - AndroidUtilities.dp(36.332f);
        this.reactionMentionLeft = r052;
        if (this.drawMention == false) goto L1652;
        int r96 = this.mentionWidth;
        if (r96 == 0) goto L1649;
        int r97 = r96 + AndroidUtilities.dp(r16);
    L1650:
        this.reactionMentionLeft = r052 - r97;
        goto L1652
    L1649:
        r97 = 0;
    L1652:
        if (this.drawCount == false) goto L1674;
        int r053 = this.reactionMentionLeft;
        int r98 = this.countWidth;
        if (r98 == 0) goto L1656;
        int r99 = r98 + AndroidUtilities.dp(r16);
    L1657:
        this.reactionMentionLeft = r053 - r99;
        goto L1674
    L1656:
        r99 = 0;
        goto L1657
    L1658:
        int r910 = AndroidUtilities.dp(15.666f);
        this.reactionMentionLeft = r910;
        if (this.drawMention == false) goto L1666;
        int r1011 = this.mentionWidth;
        if (r1011 == 0) goto L1663;
        int r1012 = r1011 + AndroidUtilities.dp(r16);
    L1664:
        this.reactionMentionLeft = r910 + r1012;
        goto L1666
    L1663:
        r1012 = 0;
    L1666:
        if (this.drawCount == false) goto L1672;
        int r911 = this.reactionMentionLeft;
        int r1013 = this.countWidth;
        if (r1013 == 0) goto L1670;
        int r1014 = r1013 + AndroidUtilities.dp(r16);
    L1671:
        this.reactionMentionLeft = r911 + r1014;
        goto L1672
    L1670:
        r1014 = 0;
    L1672:
        this.messageLeft += r051;
        this.typingLeft += r051;
        this.buttonLeft += r051;
        this.messageNameLeft += r051;
    L1674:
        if (this.drawPollVotesMention == false) goto L1712;
        int r054 = AndroidUtilities.dp(25.0f);
        r117 = r117 - r054;
        if (LocaleController.isRTL == true) goto L1694;
        int r055 = getMeasuredWidth() - AndroidUtilities.dp(36.332f);
        this.pollVotesMentionLeft = r055;
        if (this.drawReactionMention == false) goto L1681;
        this.pollVotesMentionLeft = r055 - AndroidUtilities.dp(25.0f);
    L1681:
        if (this.drawMention == false) goto L1688;
        int r056 = this.pollVotesMentionLeft;
        int r428 = this.mentionWidth;
        if (r428 == 0) goto L1685;
        int r429 = r428 + AndroidUtilities.dp(r16);
    L1686:
        this.pollVotesMentionLeft = r056 - r429;
        goto L1688
    L1685:
        r429 = 0;
    L1688:
        if (this.drawCount == false) goto L1712;
        int r057 = this.pollVotesMentionLeft;
        int r430 = this.countWidth;
        if (r430 == 0) goto L1692;
        int r431 = r430 + AndroidUtilities.dp(r16);
    L1693:
        this.pollVotesMentionLeft = r057 - r431;
        goto L1712
    L1692:
        r431 = 0;
        goto L1693
    L1694:
        int r432 = AndroidUtilities.dp(15.666f);
        this.pollVotesMentionLeft = r432;
        if (this.drawReactionMention == false) goto L1698;
        this.pollVotesMentionLeft = r432 + AndroidUtilities.dp(25.0f);
    L1698:
        if (this.drawMention == false) goto L1705;
        int r433 = this.pollVotesMentionLeft;
        int r88 = this.mentionWidth;
        if (r88 == 0) goto L1702;
        int r89 = r88 + AndroidUtilities.dp(r16);
    L1703:
        this.pollVotesMentionLeft = r433 + r89;
        goto L1705
    L1702:
        r89 = 0;
    L1705:
        if (this.drawCount == false) goto L1711;
        int r434 = this.pollVotesMentionLeft;
        int r810 = this.countWidth;
        if (r810 == 0) goto L1709;
        int r811 = r810 + AndroidUtilities.dp(r16);
    L1710:
        this.pollVotesMentionLeft = r434 + r811;
        goto L1711
    L1709:
        r811 = 0;
    L1711:
        this.messageLeft += r054;
        this.typingLeft += r054;
        this.buttonLeft += r054;
        this.messageNameLeft += r054;
        goto L1712
    L1622:
        if (this.currentDialogFolderId == 0) goto L1624;
        this.mentionWidth = Math.max(AndroidUtilities.dp(r222), (int) Math.ceil(Theme.dialogs_countTextPaint2.measureText(r92)));
        this.mentionLayout = new StaticLayout(r92, Theme.dialogs_countTextPaint2, this.mentionWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    L1625:
        int r058 = this.mentionWidth + AndroidUtilities.dp(r16);
        r117 = r117 - r058;
        if (LocaleController.isRTL == true) goto L1633;
        int r059 = (getMeasuredWidth() - (this.mentionWidth + AndroidUtilities.dp(12.666f))) - AndroidUtilities.dp(15.666f);
        int r812 = this.countWidth;
        if (r812 == 0) goto L1630;
        int r813 = r812 + AndroidUtilities.dp(r16);
    L1631:
        this.mentionLeft = r059 - r813;
    L1638:
        this.drawMention = true;
        goto L1641
    L1630:
        r813 = 0;
        goto L1631
    L1633:
        int r814 = AndroidUtilities.dp(15.666f);
        int r912 = this.countWidth;
        if (r912 == 0) goto L1636;
        int r913 = r912 + AndroidUtilities.dp(r16);
    L1637:
        this.mentionLeft = r814 + r913;
        this.messageLeft += r058;
        this.typingLeft += r058;
        this.buttonLeft += r058;
        this.messageNameLeft += r058;
        goto L1638
    L1636:
        r913 = 0;
        goto L1637
    L1624:
        this.mentionWidth = AndroidUtilities.dp(r222);
        goto L1625
    L1617:
        this.countLeft = AndroidUtilities.dp(15.666f);
        this.messageLeft += r050;
        this.typingLeft += r050;
        this.buttonLeft += r050;
        this.messageNameLeft += r050;
        goto L1618
    L1619:
        this.countWidth = 0;
    L1558:
        if (isForumCell() == false) goto L1565;
    L1549:
        this.tagsLeft = this.messageLeft;
        this.tagsRight = getMeasuredWidth() - AndroidUtilities.dp(64.0f);
        goto L1551
    L1526:
        int r435 = AndroidUtilities.dp(this.messagePaddingStart + 6);
        this.messageNameLeft = r435;
        this.messageLeft = r435;
        this.typingLeft = r435;
        this.buttonLeft = r435;
        r412 = AndroidUtilities.dp(this.avatarStart);
        r109 = AndroidUtilities.dp(69.0f) + r412;
        goto L1527
    L1489:
        if (SharedConfig.useThreeLinesLayout == true) goto L1490;
        int r060 = AndroidUtilities.dp(9.0f);
        this.messageNameTop = AndroidUtilities.dp(31.0f);
        this.timeTop = AndroidUtilities.dp(r17);
        this.errorTop = AndroidUtilities.dp(38.0f);
        this.pinTop = AndroidUtilities.dp(39.0f);
        if (this.isTopic == false) goto L1495;
        float r118 = 35.0f;
    L1496:
        this.countTop = AndroidUtilities.dp(r118);
        this.checkDrawTop = AndroidUtilities.dp(r16);
        int r119 = getMeasuredWidth();
        int r436 = this.messagePaddingStart + 20;
        if (LocaleController.isRTL == false) goto L1499;
        int r173 = 0;
    L1500:
        r117 = r119 - AndroidUtilities.dp(r436 - r173);
        if (LocaleController.isRTL == false) goto L1503;
        int r437 = AndroidUtilities.dp(22.0f);
        this.messageNameLeft = r437;
        this.messageLeft = r437;
        this.typingLeft = r437;
        this.buttonLeft = r437;
        r20 = 13.0f;
        int r438 = getMeasuredWidth() - AndroidUtilities.dp(this.avatarStart + 52);
        r422 = 20.0f;
        int r1015 = r438 - AndroidUtilities.dp((this.thumbsCount * (this.thumbSize + 2)) + 9);
    L1504:
        r222 = 8.0f;
        r443 = 0.0f;
        this.storyParams.originalAvatarRect.set(r438, r060, r438 + AndroidUtilities.dp(52.0f), r060 + AndroidUtilities.dp(52.0f));
        int r439 = 0;
    L1505:
        ImageReceiver[] r128 = this.thumbImage;
        if (r439 >= r128.length) goto L1522;
        ImageReceiver r129 = r128[r439];
        float r135 = ((this.thumbSize + 2) * r439) + r1015;
        int r1410 = AndroidUtilities.dp(30.0f) + r060;
        if (this.twoLinesForName == false) goto L1510;
        int r155 = AndroidUtilities.dp(r422);
    L1511:
        int r1411 = r1410 + r155;
        if (this.useForceThreeLines == false) goto L1514;
    L1520:
        int r156 = 0;
    L1521:
        r129.setImageCoords(r135, r1411 - r156, AndroidUtilities.dp(this.thumbSize), AndroidUtilities.dp(this.thumbSize));
        r439 = r439 + 1;
        r060 = r060;
        goto L1505
    L1514:
        if (SharedConfig.useThreeLinesLayout == true) goto L1520;
        DialogCellTags r157 = this.tags;
        if (r157 == null) goto L1520;
        if (r157.isEmpty() == true) goto L1520;
        r156 = AndroidUtilities.dp(9.0f);
        goto L1521
    L1510:
        r155 = 0;
        goto L1511
    L1522:
        r172 = r060;
        goto L1547
    L1503:
        r20 = 13.0f;
        r422 = 20.0f;
        int r440 = AndroidUtilities.dp(this.messagePaddingStart + 4);
        this.messageNameLeft = r440;
        this.messageLeft = r440;
        this.typingLeft = r440;
        this.buttonLeft = r440;
        r438 = AndroidUtilities.dp(this.avatarStart);
        r1015 = AndroidUtilities.dp(67.0f) + r438;
        goto L1504
    L1499:
        r173 = 12;
        goto L1500
    L1495:
        r118 = 38.0f;
        goto L1496
    L1480:
        if (this.nameLayout.isRtlCharAt(0) == false) goto L1482;
        r013 = -AndroidUtilities.dp(r26);
        goto L1483
    L1476:
        this.nameLayout = new StaticLayout(r442, Theme.dialogs_namePaint[this.paintIndex], Math.max(r012, this.nameWidth), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L1449
        goto L1478
    L1468:
        if (r108.hasHighlightedWords() == false) goto L1472;
        CharSequence r1016 = AndroidUtilities.highlightText(r410, this.message.highlightedWords, this.resourcesProvider);     // Catch: Exception -> L1449
        if (r1016 == null) goto L1472;
        r442 = r1016;
        goto L1474
    L1461:
        r49 = false;
        goto L1462
    L1447:
        r48 = false;
        goto L1448
    L1451:
        r26 = 0;
        if (this.isForChannelSubscriberCell == false) goto L1458;
        if (r124.length() != TextUtils.ellipsize(r124, Theme.dialogs_namePaint[this.paintIndex], r012, TextUtils.TruncateAt.END).length()) goto L1456;
        boolean r441 = true;
    L1457:
        this.channelShouldUseLineWidth = r441;     // Catch: Exception -> L1449
        goto L1458
    L1456:
        r441 = false;
    L1449:
        e = e;
    L1485:
        FileLog.e(e);
    L1438:
        e = e;
        r26 = 0;
        goto L1485
    L1400:
        if (this.drawVerified == false) goto L1405;
        int r061 = AndroidUtilities.dp(6.0f) + Theme.dialogs_verifiedDrawable.getIntrinsicWidth();
        this.nameWidth -= r061;
        this.nameAdditionalsForChannelSubscriber += r061;
        if (LocaleController.isRTL == false) goto L1424;
        this.nameLeft += r061;
        goto L1424
    L1405:
        if (this.drawBadge == false) goto L1410;
        int r062 = AndroidUtilities.dp(36.0f);
        this.nameWidth -= r062;
        this.nameAdditionalsForChannelSubscriber += r062;
        if (LocaleController.isRTL == false) goto L1424;
        this.nameLeft += r062;
        goto L1424
    L1410:
        if (this.drawPremium == false) goto L1415;
        int r063 = AndroidUtilities.dp(36.0f);
        this.nameWidth -= r063;
        this.nameAdditionalsForChannelSubscriber += r063;
        if (LocaleController.isRTL == false) goto L1424;
        this.nameLeft += r063;
        goto L1424
    L1415:
        if (this.drawScam == 0) goto L1424;
        int r064 = AndroidUtilities.dp(6.0f);
        if (this.drawScam != 1) goto L1419;
        ScamDrawable r444 = Theme.dialogs_scamDrawable;
    L1420:
        int r065 = r064 + r444.getIntrinsicWidth();
        this.nameWidth -= r065;
        this.nameAdditionalsForChannelSubscriber += r065;
        if (LocaleController.isRTL == false) goto L1424;
        this.nameLeft += r065;
        goto L1424
    L1419:
        r444 = Theme.dialogs_fakeDrawable;
        goto L1420
    L1385:
        if (this.emojiStatus.getDrawable() == null) goto L1392;
        int r445 = AndroidUtilities.dp(36.0f);
        if (r09 == false) goto L1389;
        r445 = r445 + (AndroidUtilities.dp(6.0f) + Theme.dialogs_muteDrawable.getIntrinsicWidth());
    L1389:
        this.nameWidth -= r445;
        this.nameAdditionalsForChannelSubscriber += r445;
        if (LocaleController.isRTL == false) goto L1424;
        this.nameLeft += r445;
    L1375:
        if (this.drawVerified == true) goto L1381;
        if (this.drawBadge == true) goto L1381;
        if (this.drawScam != 0) goto L1381;
        r09 = true;
        goto L1383
    L1352:
        this.clockDrawLeft = (r116 + r08) + AndroidUtilities.dp(5.0f);
        this.nameLeft += r106;
        goto L1366
    L1355:
        if (this.drawCheck2 == false) goto L1366;
        int r1017 = Theme.dialogs_checkDrawable.getIntrinsicWidth() + AndroidUtilities.dp(5.0f);
        int r1110 = this.nameWidth - r1017;
        this.nameWidth = r1110;
        if (this.drawCheck1 == false) goto L1362;
        this.nameWidth = r1110 - (Theme.dialogs_halfCheckDrawable.getIntrinsicWidth() - AndroidUtilities.dp(8.0f));
        boolean r1111 = LocaleController.isRTL;
        int r136 = this.timeLeft;
        if (r1111 == true) goto L1361;
        int r137 = (r136 - r46) - r1017;
        this.halfCheckDrawLeft = r137;
        this.checkDrawLeft = r137 - AndroidUtilities.dp(5.5f);
        goto L1366
    L1361:
        int r138 = (r136 + r08) + AndroidUtilities.dp(5.0f);
        this.checkDrawLeft = r138;
        this.halfCheckDrawLeft = r138 + AndroidUtilities.dp(5.5f);
        this.nameLeft += (r1017 + Theme.dialogs_halfCheckDrawable.getIntrinsicWidth()) - AndroidUtilities.dp(8.0f);
        goto L1366
    L1362:
        boolean r1112 = LocaleController.isRTL;
        int r139 = this.timeLeft;
        if (r1112 == true) goto L1365;
        this.checkDrawLeft1 = (r139 - r46) - r1017;
        goto L1366
    L1365:
        this.checkDrawLeft1 = (r139 + r08) + AndroidUtilities.dp(5.0f);
        this.nameLeft += r1017;
        goto L1366
    L1340:
        this.nameWidth = ((getMeasuredWidth() - this.nameLeft) - AndroidUtilities.dp(this.messagePaddingStart + 13)) - r08;
        this.nameLeft += r08;
        goto L1342
    L1334:
        this.lock2Left = (r13 - Theme.dialogs_lock2Drawable.getIntrinsicWidth()) - AndroidUtilities.dp(4.0f);
        goto L1335
    L1336:
        r46 = 0;
        goto L1338
    L1327:
        this.timeLeft = AndroidUtilities.dp(r18);
        goto L1329
    L1323:
        r07 = 0.0f;
        goto L1324
    L1328:
        r272 = 24.0f;
        this.timeLayout = null;
        this.timeLeft = 0;
        r08 = 0;
        goto L1329
    L1247:
        if (this.currentDialogFolderId == 0) goto L1249;
        r122 = LocaleController.getString(R.string.ArchivedChats);
        goto L1245
    L1249:
        TLRPC.Chat r1210 = this.chat;
        if (r1210 != null) goto L1252;
        TLRPC.User r67 = this.user;
        if (r67 != null) goto L1285;
    L1262:
        r122 = _UrlKt.FRAGMENT_ENCODE_SET;
    L1314:
        if (r122 == null) goto L1245;
        if (r122.length() != 0) goto L1245;
        r122 = LocaleController.getString(R.string.HiddenName);
        goto L1245
    L1285:
        if (UserObject.isReplyUser(r67) == false) goto L1288;
        CharSequence r68 = LocaleController.getString(R.string.RepliesTitle);
    L1263:
        r122 = r68;
        goto L1314
    L1288:
        if (UserObject.isAnonymous(this.user) == false) goto L1291;
        r68 = LocaleController.getString(R.string.AnonymousForward);
        goto L1263
    L1291:
        if (UserObject.isUserSelf(this.user) == false) goto L1305;
        if (this.isMonoForumTopicDialog == true) goto L1305;
        if (this.isSavedDialog == false) goto L1298;
        r68 = LocaleController.getString(R.string.MyNotes);
        goto L1263
    L1298:
        if (this.useMeForMyMessages == false) goto L1301;
        r68 = LocaleController.getString(R.string.FromYou);
        goto L1263
    L1301:
        if (this.dialogsType != 3) goto L1303;
        this.drawPinBackground = true;
    L1303:
        r68 = LocaleController.getString(R.string.SavedMessages);
    L1305:
        if (this.isTopic == true) goto L1307;
        r68 = AndroidUtilities.escape(UserObject.getUserName(this.user));
        goto L1263
    L1307:
        if (this.topicIconInName != null) goto L1309;
        this.topicIconInName = new Drawable[1];
    L1309:
        Drawable[] r69 = this.topicIconInName;
        r69[0] = null;
        boolean r73 = this.showTopicIconInName;
        TLRPC.TL_forumTopic r1211 = this.forumTopic;
        if (r73 == false) goto L1312;
        r68 = ForumUtilities.getTopicSpannedName(r1211, Theme.dialogs_namePaint[this.paintIndex], r69, false);
        goto L1263
    L1312:
        r68 = AndroidUtilities.escape(r1211.title);
        goto L1263
    L1252:
        if (this.useFromUserAsAvatar == false) goto L1265;
        if (this.topicIconInName != null) goto L1256;
        this.topicIconInName = new Drawable[1];
    L1256:
        this.topicIconInName[0] = null;
        r68 = MessagesController.getInstance(this.currentAccount).getTopicsController().getTopicIconName(this.chat, this.message, this.currentMessagePaint, this.topicIconInName);
        if (r68 == null) goto L1259;
    L1261:
        if (r68 != null) goto L1263;
    L1259:
        if (this.message == null) goto L1261;
        r68 = MessagesController.getInstance(this.currentAccount).getTopicsController().getTopicIconNameSafe(this.chat, this.message.getTopicId(), this.currentMessagePaint, this.topicIconInName);
        goto L1261
    L1265:
        if (this.isTopic == false) goto L1274;
        if (this.topicIconInName != null) goto L1269;
        this.topicIconInName = new Drawable[1];
    L1269:
        Drawable[] r610 = this.topicIconInName;
        r610[0] = null;
        boolean r74 = this.showTopicIconInName;
        TLRPC.TL_forumTopic r1212 = this.forumTopic;
        if (r74 == false) goto L1272;
        r68 = ForumUtilities.getTopicSpannedName(r1212, Theme.dialogs_namePaint[this.paintIndex], r610, false);
        goto L1263
    L1272:
        r68 = AndroidUtilities.escape(r1212.title);
        goto L1263
    L1274:
        if (r1210.monoforum == true) goto L1276;
    L1281:
        r68 = AndroidUtilities.escape(r1210.title);
        goto L1263
    L1276:
        if (r1210.linked_monoforum_id == 0) goto L1281;
        TLRPC.Chat r611 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.chat.linked_monoforum_id));
        if (r611 == null) goto L1280;
        SpannableStringBuilder r1213 = new SpannableStringBuilder(AndroidUtilities.escape(r611.title));
        r1213.append(" ");
        int r612 = r1213.length();
        r1213.append(LocaleController.getString(R.string.MonoforumSpan));
        r1213.setSpan(new FilterCreateActivity.TextSpan(LocaleController.getString(R.string.MonoforumSpan), 9.33f, Theme.key_windowBackgroundWhiteGrayText, this.resourcesProvider), r612, r1213.length(), 33);
        r122 = r1213;
        goto L1314
    L1280:
        r122 = AndroidUtilities.escape(this.chat.title);
        goto L1314
    L1226:
        if (r115.isPromoDialog(this.currentDialogId, true) == false) goto L1239;
        this.drawPinBackground = true;
        this.promoDialog = true;
        int r1214 = r115.promoDialogType;
        if (r1214 != MessagesController.PROMO_TYPE_PROXY) goto L1231;
        r314 = r24;
        r210 = LocaleController.getString(R.string.UseProxySponsor);
        r147 = false;
        goto L1241
    L1231:
        if (r1214 != MessagesController.PROMO_TYPE_PSA) goto L1239;
        r313 = LocaleController.getString("PsaType_" + r115.promoPsaType);
        if (TextUtils.isEmpty(r313) == false) goto L1236;
        r313 = LocaleController.getString(R.string.PsaTypeDefault);
    L1236:
        if (TextUtils.isEmpty(r115.promoPsaMessage) == true) goto L1239;
        CharSequence r231 = r115.promoPsaMessage;
        r147 = false;
        this.thumbsCount = 0;
        r29 = r231;
        goto L1238
    L1164:
        if (this.draftMessage != null) goto L1212;
        if (r102 == false) goto L1212;
        MessageObject r1018 = this.message;
        if ((r1018.messageOwner.action instanceof TLRPC.TL_messageActionHistoryClear) == true) goto L1212;
        if (r1018.isSending() == false) goto L1171;
        r146 = false;
        this.drawCheck1 = false;
        this.drawCheck2 = false;
        this.drawClock = true;
        this.drawError = false;
        goto L1213
    L1171:
        r146 = false;
        if (this.message.isSendError() == false) goto L1175;
        this.drawCheck1 = false;
        this.drawCheck2 = false;
        this.drawClock = false;
        this.drawError = true;
        this.drawCount = false;
        this.drawMention = false;
        goto L1213
    L1175:
        if (this.message.isSent() == false) goto L1211;
        TLRPC.TL_forumTopic r1019 = this.forumTopic;
        if (r1019 == null) goto L1185;
        if (r1019.read_outbox_max_id < this.message.getId()) goto L1181;
        boolean r1020 = true;
    L1182:
        this.drawCheck1 = r1020;
    L1210:
        this.drawCheck2 = true;
        r146 = false;
        this.drawClock = false;
        this.drawError = false;
        goto L1213
    L1181:
        r1020 = false;
        goto L1182
    L1185:
        if (this.isDialogCell == false) goto L1201;
        int r1021 = this.readOutboxMaxId;
        if (r1021 <= 0) goto L1191;
        if (r1021 < this.message.getId()) goto L1191;
    L1198:
        boolean r1022 = true;
    L1199:
        this.drawCheck1 = r1022;
    L1191:
        if (this.message.isUnread() == false) goto L1198;
        if (ChatObject.isChannel(this.chat) == true) goto L1195;
    L1197:
        r1022 = false;
        goto L1199
    L1195:
        if (this.chat.megagroup == true) goto L1197;
    L1201:
        if (this.message.isUnread() == true) goto L1203;
    L1208:
        boolean r1023 = true;
    L1209:
        this.drawCheck1 = r1023;
        goto L1210
    L1203:
        if (ChatObject.isChannel(this.chat) == true) goto L1205;
    L1207:
        r1023 = false;
        goto L1209
    L1205:
        if (this.chat.megagroup == true) goto L1207;
    L1211:
        r146 = false;
        goto L1213
    L1116:
        r145 = false;
        this.drawCount = false;
        this.drawMention = true;
        r83 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r82 + r113)});
    L1117:
        r114 = null;
        goto L1119
    L1118:
        r145 = false;
        this.drawCount = false;
        this.drawMention = false;
        r83 = null;
        goto L1117
    L1121:
        if (this.clearingDialog == false) goto L1124;
        this.drawCount = false;
        r102 = false;
        r114 = null;
        boolean r1310 = true;
    L1123:
        boolean r1412 = false;
    L1132:
        if (this.mentionCount == 0) goto L1134;
        this.drawMention = r1310;
        r83 = "@";
    L1136:
        if (this.reactionMentionCount > 0) goto L1138;
        this.drawReactionMention = false;
    L1157:
        if (this.pollVotesMentionCount <= 0) goto L1159;
        boolean r1215 = true;
    L1160:
        this.drawPollVotesMention = r1215;
        goto L1162
    L1159:
        r1215 = false;
        goto L1160
    L1138:
        if (ExteraConfig.getHideReactionsInChannels() == false) goto L1142;
        if (ChatObject.isChannelAndNotMegaGroup(this.chat) == false) goto L1142;
    L1152:
        boolean r1216 = false;
    L1154:
        this.drawReactionMention = r1216;
    L1142:
        if (ExteraConfig.getHideReactionsInGroups() == false) goto L1148;
        if (ChatObject.isChannelAndNotMegaGroup(this.chat) == true) goto L1148;
        if (this.chat != null) goto L1152;
    L1148:
        if (ExteraConfig.getHideReactionsInPrivateChats() == true) goto L1150;
    L1153:
        r1216 = true;
        goto L1154
    L1150:
        if (this.user != null) goto L1152;
    L1134:
        this.drawMention = r1412;
        r83 = null;
        goto L1136
    L1124:
        int r815 = this.unreadCount;
        if (r815 == 0) goto L1127;
        r1310 = true;
        this.drawCount = true;
        r114 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r815)});
        goto L1123
    L1127:
        r1310 = true;
        if (this.markUnread == false) goto L1130;
        this.drawCount = true;
        r114 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L1123
    L1130:
        r1412 = false;
        this.drawCount = false;
        r114 = null;
    L1214:
        this.drawCheck1 = false;
        this.drawCheck2 = false;
        if (r8 != null) goto L1217;
    L1221:
        boolean r816 = false;
    L1222:
        this.drawClock = r816;
        r146 = false;
        this.drawCount = false;
        this.drawMention = false;
        this.drawReactionMention = false;
        this.drawPollVotesMention = false;
        this.drawError = false;
        r84 = null;
        r103 = null;
        goto L1223
    L1217:
        if (r8.isSending() == false) goto L1221;
        if (this.currentDialogId != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L1221;
        r816 = true;
        goto L1222
    L1097:
        if (this.draftMessage == null) goto L1099;
        r313 = LocaleController.stringForMessageListDate(r3.date);
        goto L1105
    L1099:
        int r321 = this.lastMessageDate;
        if (r321 == 0) goto L1103;
        r313 = LocaleController.stringForMessageListDate(r321);
        goto L1105
    L1103:
        if (this.message == null) goto L1095;
        r313 = LocaleController.stringForMessageListDate(r3.messageOwner.date);
        goto L1105
    L395:
        if (r58.length() < 0) goto L400;
        if (r05 == null) goto L400;
        SpannableStringBuilder r522 = SpannableStringBuilder.valueOf(r58);
        r522.setSpan(new ForegroundColorSpanThemable(Theme.key_chats_name, this.resourcesProvider), 0, Math.min(r522.length(), r05.length() + 1), 0);
        r59 = r522;
        if (shouldDrawForwardPreviewIcon(this.message) == false) goto L403;
        prependForwardedIcon(r522, Math.min(r05.length() + 2, r522.length()));
        r59 = r522;
        goto L403
    L391:
        r58 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L393
    L387:
        r57 = null;
        goto L389
    L379:
        if (r12 != 1) goto L381;
        r12 = r31;
    L381:
        if (r12 != 3) goto L383;
        r12 = 4;
    L383:
        r05 = null;
        goto L384
    L375:
        r04 = AndroidUtilities.escape(getMessageNameString());
        goto L376
    L405:
        if (TextUtils.isEmpty(this.customMessage) == true) goto L408;
        this.draftMessage = null;
        this.draftVoice = false;
        CharSequence r232 = this.customMessage;
        this.currentMessagePaint = Theme.dialogs_messagePaint[this.paintIndex];
        CharSequence r914 = _UrlKt.FRAGMENT_ENCODE_SET;
        r102 = r30;
        r05 = null;
        r511 = true;
        r19 = -1;
        CharSequence r233 = r232;
    L407:
        r28 = null;
        r23 = r233;
        r94 = r914;
        goto L1071
    L408:
        if (r03 == null) goto L421;
        this.lastPrintString = r03;
        int r234 = MessagesController.getInstance(this.currentAccount).getPrintingStringType(this.currentDialogId, getTopicId()).intValue();
        this.printingStringType = r234;
        StatusDrawable r235 = Theme.getChatStatusDrawable(r234);
        if (r235 == null) goto L412;
        int r236 = r235.getIntrinsicWidth() + AndroidUtilities.dp(3.0f);
    L413:
        SpannableStringBuilder r915 = new SpannableStringBuilder();
        CharSequence r066 = TextUtils.replace(r03, new String[]{"..."}, new String[]{_UrlKt.FRAGMENT_ENCODE_SET});
        if (this.printingStringType != 5) goto L416;
        int r1024 = r066.toString().indexOf("**oo**");
    L417:
        if (r1024 < 0) goto L419;
        r915.append(r066).setSpan(new FixedWidthSpan(Theme.getChatStatusDrawable(this.printingStringType).getIntrinsicWidth()), r1024, r1024 + 6, 0);
    L420:
        int r237 = r1024;
        boolean r067 = false;
        CharSequence r916 = r915;
    L423:
        if (this.draftVoice == false) goto L425;
    L426:
        int r1025 = r237;
        CharSequence r392 = r916;
        r05 = LocaleController.getString(R.string.Draft);
        TLRPC.DraftMessage r238 = this.draftMessage;
        if (r238 != null) goto L1023;
    L1035:
        TLRPC.DraftMessage r239 = this.draftMessage;
        if (r239 == null) goto L1041;
        TL_iv.RichMessage r322 = r239.rich_message;
        if (r322 == null) goto L1041;
        CharSequence r240 = MessageObject.formatRichMessage(r322, false, false, 150);
    L1048:
        SpannableString r323 = new SpannableString(r240);
        TLRPC.DraftMessage r241 = this.draftMessage;
        if (r241 == null) goto L1060;
        MediaDataController.addTextStyleRuns(r241, r323, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED);
        TLRPC.DraftMessage r242 = this.draftMessage;
        if (r242 == null) goto L1062;
        ArrayList<TLRPC.MessageEntity> r523 = r242.entities;
        if (r523 == null) goto L1062;
        LocaleUtils.parseCustomEmojis(r242.message, r523);
        ArrayList<TLRPC.MessageEntity> r243 = this.draftMessage.entities;
        TextPaint r524 = this.currentMessagePaint;
        if (r524 != null) goto L1057;
        Paint.FontMetricsInt r525 = null;
    L1058:
        MediaDataController.addAnimatedEmojiSpans(r243, r323, r525);
        goto L1062
    L1057:
        r525 = r524.getFontMetricsInt();
    L1062:
        SpannableStringBuilder r244 = formatInternal(r12, AndroidUtilities.replaceNewLines(r323), r05);
        if (this.useForceThreeLines == true) goto L1067;
        if (SharedConfig.useThreeLinesLayout == true) goto L1067;
    L1068:
        boolean r1413 = false;
        r244.setSpan(new ForegroundColorSpanThemable(Theme.key_chats_draft, this.resourcesProvider), 0, r05.length() + 1, 33);
    L1070:
        r19 = r1025;
        CharSequence r245 = Emoji.replaceEmoji(r244, Theme.dialogs_messagePaint[this.paintIndex].getFontMetricsInt(), r1413);
    L1033:
        r102 = r30;
        r914 = r392;
        r511 = false;
        r233 = r245;
    L1067:
        if (hasTags() == true) goto L1068;
        r1413 = false;
        goto L1070
    L1060:
        if (this.draftVoice == false) goto L1062;
        r323.setSpan(new ForegroundColorSpanThemable(Theme.key_chats_actionMessage, this.resourcesProvider), 0, r323.length(), 33);
    L1041:
        if (this.draftVoice == false) goto L1043;
        r240 = LocaleController.getString(R.string.AttachAudio);
        goto L1048
    L1043:
        if (r239 == null) goto L1047;
        r240 = r239.message;
        if (r240.length() <= 150) goto L1048;
        r240 = r240.subSequence(0, 150);
        goto L1048
    L1047:
        r240 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L1048
    L1023:
        if (TextUtils.isEmpty(r238.message) == false) goto L1035;
        if (this.draftMessage.rich_message != null) goto L1035;
        if (this.useForceThreeLines == true) goto L1031;
        if (SharedConfig.useThreeLinesLayout == true) goto L1031;
    L1034:
        SpannableStringBuilder r246 = SpannableStringBuilder.valueOf(r05);
        r246.setSpan(new ForegroundColorSpanThemable(Theme.key_chats_draft, this.resourcesProvider), 0, r05.length(), 33);
        r19 = r1025;
        r511 = false;
        CharSequence r247 = r246;
    L1015:
        r102 = r30;
        r914 = r392;
        r233 = r247;
    L1031:
        if (hasTags() == true) goto L1034;
        r19 = r1025;
        r245 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L1033
    L425:
        if (this.draftMessage != null) goto L426;
        if (this.clearingDialog == false) goto L431;
        this.currentMessagePaint = Theme.dialogs_messagePrintingPaint[this.paintIndex];
        CharSequence r526 = LocaleController.getString(R.string.HistoryCleared);
    L430:
        r19 = r237;
        r23 = r526;
        r102 = r30;
        r28 = null;
        r511 = r067;
        r05 = null;
        r94 = r916;
        goto L1071
    L431:
        MessageObject r1414 = this.message;
        if (r1414 == null) goto L434;
        String r1415 = MessagesController.getInstance(r1414.currentAccount).getRestrictionReason(this.message.messageOwner.restriction_reason);
        long r1311 = this.message.getFromChatId();
        boolean r1113 = DialogObject.isUserDialog(r1311);
        int r324 = this.currentAccount;
        if (r1113 == false) goto L472;
        MessagesController.getInstance(r324).getUser(Long.valueOf(r1311));
        TLRPC.Chat r1114 = null;
    L473:
        this.drawCount2 = true;
        if (this.dialogsType == 0) goto L476;
    L502:
        r392 = r916;
    L503:
        CharSequence r325 = _UrlKt.FRAGMENT_ENCODE_SET;
        boolean r817 = false;
    L504:
        if (r817 == false) goto L507;
        int r1026 = r237;
        CharSequence r248 = r325;
    L506:
        CharSequence r283 = null;
        CharSequence r249 = r248;
    L1012:
        if (this.currentDialogCommunityId == 0) goto L1017;
        CharSequence r326 = formatCommunityDialogNames();
    L1014:
        r511 = r067;
        r05 = r326;
        r19 = r1026;
        r247 = r249;
        goto L1015
    L1017:
        if (this.currentDialogFolderId == 0) goto L1019;
        r326 = formatArchivedDialogNames();
        goto L1014
    L1019:
        r511 = r067;
        r19 = r1026;
        r05 = r283;
        r247 = r249;
        goto L1015
    L507:
        int r327 = this.dialogsType;
        if (r327 != r31) goto L537;
        TLRPC.Chat r328 = this.chat;
        if (r328 != null) goto L512;
        CharSequence r329 = _UrlKt.FRAGMENT_ENCODE_SET;
    L534:
        this.drawCount2 = false;
    L535:
        r1026 = r237;
        r249 = r329;
        r43 = false;
        r283 = null;
        r30 = false;
        goto L1012
    L512:
        if (ChatObject.isChannel(r328) == false) goto L523;
        TLRPC.Chat r330 = this.chat;
        if (r330.megagroup == true) goto L523;
        int r446 = r330.participants_count;
        if (r446 == 0) goto L520;
        r329 = LocaleController.formatPluralStringComma("Subscribers", r446);
        goto L534
    L520:
        if (ChatObject.isPublic(r330) == true) goto L522;
        r329 = LocaleController.getString(R.string.ChannelPrivate).toLowerCase();
        goto L534
    L522:
        r329 = LocaleController.getString(R.string.ChannelPublic).toLowerCase();
    L523:
        TLRPC.Chat r331 = this.chat;
        int r447 = r331.participants_count;
        if (r447 == 0) goto L527;
        r329 = LocaleController.formatPluralStringComma("Members", r447);
        goto L534
    L527:
        if (r331.has_geo == false) goto L530;
        r329 = LocaleController.getString(R.string.MegaLocation);
        goto L534
    L530:
        if (ChatObject.isPublic(r331) == true) goto L532;
        r329 = LocaleController.getString(R.string.MegaPrivate).toLowerCase();
        goto L534
    L532:
        r329 = LocaleController.getString(R.string.MegaPublic).toLowerCase();
        goto L534
    L537:
        if (r327 == 3) goto L539;
    L547:
        boolean r334 = this.useForceThreeLines;
        if (r334 == false) goto L550;
    L556:
        if (r334 == false) goto L558;
    L562:
        MessageObject r335 = this.message;
        if ((r335.messageOwner instanceof TLRPC.TL_messageService) == true) goto L565;
    L569:
        CharSequence r1416 = r34;
        this.needEmoji = true;
        updateMessageThumbs();
        if (this.isSavedDialog == true) goto L607;
        TLRPC.User r336 = this.user;
        if (r336 == null) goto L607;
        if (r336.self == false) goto L607;
        if (this.message.isOutOwner() == true) goto L607;
        CharSequence r337 = AndroidUtilities.escape(getMessageNameString());
    L609:
        if (this.currentDialogId != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L611;
        boolean r818 = true;
    L612:
        boolean r917 = shouldDrawForwardPreviewIcon(this.message);
        if (r818 == false) goto L628;
        MessageObject r1027 = this.message;
        if (r1027 == null) goto L628;
        if (r1027.isOutOwner() == false) goto L619;
        if (r917 == false) goto L628;
    L619:
        TLRPC.Chat r1028 = this.chat;
        if (r1028 != null) goto L622;
    L627:
        boolean r1029 = true;
    L629:
        if (r818 == false) goto L636;
        MessageObject r1312 = this.message;
        if (r1312 == null) goto L636;
        if (r1312.isOutOwner() == false) goto L636;
        if (r917 == true) goto L636;
        boolean r1313 = true;
    L637:
        boolean r342 = r067;
        if (r1313 == false) goto L640;
    L651:
        if (r1029 == false) goto L652;
    L650:
        r1026 = r237;
        CharSequence r402 = r337;
    L920:
        if (this.message == null) goto L934;
        TLRPC.Chat r068 = this.chat;
        if (r068 == null) goto L934;
        if (ChatObject.isChannel(r068) == false) goto L934;
        if (ChatObject.isMegagroup(this.chat) == true) goto L934;
        if (this.chat.signature_profiles == false) goto L934;
        TLRPC.Message r069 = this.message.messageOwner;
        if (r069 == null) goto L934;
        if (r069.out == false) goto L934;
        CharSequence r070 = LocaleController.getString(R.string.FromYou);
    L947:
        TLRPC.Chat r250 = this.chat;
        if (r250 != null) goto L950;
    L965:
        CharSequence r251 = addSenderAvatar(r070, this.message);
        SpannableStringBuilder r338 = getMessageStringFormatted(r12, r1415, r251, false);
        if (r917 == true) goto L968;
    L971:
        int r071 = 0;
    L972:
        if (r071 == 0) goto L975;
        prependForwardedIcon(r338, r251.length() + 2);
    L975:
        if (this.useFromUserAsAvatar == false) goto L977;
    L995:
        int r527 = 0;
    L996:
        CharSequence r072 = Emoji.replaceEmoji(r338, Theme.dialogs_messagePaint[this.paintIndex].getFontMetricsInt(), false);
        if (this.message.hasHighlightedWords() == false) goto L1002;
        CharSequence r339 = AndroidUtilities.highlightText(r072, this.message.highlightedWords, this.resourcesProvider);
        if (r339 == null) goto L1002;
        r072 = r339;
    L1002:
        if (this.thumbsCount > 0) goto L1004;
    L1010:
        r283 = r251;
    L555:
        r249 = r072;
        r067 = false;
        goto L1012
    L1004:
        if ((r072 instanceof SpannableStringBuilder) == true) goto L1006;
        r072 = new SpannableStringBuilder(r072);
    L1006:
        SpannableStringBuilder r340 = (SpannableStringBuilder) r072;
        if (r527 < r340.length()) goto L1009;
        r340.append(" ");
        r340.setSpan(new ThumbnailSpan(AndroidUtilities.dp((this.thumbsCount * (this.thumbSize + 2)) + 3)), r340.length() - 1, r340.length(), 33);
        goto L1010
    L1009:
        r340.insert(r527, " ");
        r340.setSpan(new ThumbnailSpan(AndroidUtilities.dp((this.thumbsCount * (this.thumbSize + 2)) + 3)), r527, r527 + 1, 33);
        goto L1010
    L977:
        if (this.useForceThreeLines == true) goto L981;
        if (SharedConfig.useThreeLinesLayout == true) goto L981;
    L984:
        if (r251 != null) goto L2144;
        int r528 = 0;
    L990:
        r527 = r528 + r071;
        r338.setSpan(new ForegroundColorSpanThemable(Theme.key_chats_nameMessage, this.resourcesProvider), 0, r527, 33);     // Catch: Exception -> L993
    L993:
        e = e;
    L994:
        FileLog.e(e);
        goto L996
    L2144:
        r528 = r251.length() + 1;
    L987:
        e = e;
        r527 = 0;
    L981:
        if (this.currentDialogFolderId == 0) goto L995;
        if (r338.length() <= 0) goto L995;
    L968:
        if (separateMessageNameLine() == true) goto L971;
        if (r251 == null) goto L971;
        r071 = 3;
        goto L972
    L950:
        if (r250.forum == false) goto L965;
        if (this.isTopic == true) goto L965;
        if (this.useFromUserAsAvatar == true) goto L965;
        CharSequence r252 = MessagesController.getInstance(this.currentAccount).getTopicsController().getTopicIconName(this.chat, this.message, this.currentMessagePaint);
        if (TextUtils.isEmpty(r252) == true) goto L965;
        SpannableStringBuilder r341 = new SpannableStringBuilder("-");
        ColoredImageSpan r529 = new ColoredImageSpan(ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.msg_mini_forumarrow).mutate());
        if (this.useForceThreeLines == false) goto L960;
    L963:
        int r819 = -1;
    L964:
        r529.setColorKey(r819);
        r341.setSpan(r529, 0, 1, 0);
        SpannableStringBuilder r530 = new SpannableStringBuilder();
        r530.append(r070).append(r341).append(r252);
        r070 = r530;
        goto L965
    L960:
        if (SharedConfig.useThreeLinesLayout == true) goto L963;
        r819 = Theme.key_chats_nameMessage;
    L934:
        if (r402 == null) goto L936;
        r55 = r402;
    L939:
        if (r55 != null) goto L946;
        if (r818 == false) goto L946;
        MessageObject r073 = this.message;
        if (r073 == null) goto L946;
        if (r073.isOutOwner() == false) goto L946;
        r070 = LocaleController.getString(R.string.FromYou);
    L946:
        r070 = r55;
        goto L947
    L936:
        if (r55 != null) goto L939;
        r55 = getPreviewMessageNameString();
        goto L939
    L652:
        if (r1313 == true) goto L654;
        if (r337 != null) goto L650;
    L654:
        MessageObject r074 = this.message;
        if (r074 == null) goto L660;
        TLRPC.Message r075 = r074.messageOwner;
        if (r075 == null) goto L660;
        if (r075.guestchat_via_from != null) goto L650;
    L660:
        TLRPC.Chat r076 = this.chat;
        r1026 = r237;
        r402 = r337;
        if (r076 == null) goto L680;
        long r253 = r076.id;
        if (r253 <= 0) goto L680;
        if (r1114 == null) goto L668;
        if (r1114.id != r253) goto L668;
    L673:
        if (ChatObject.isChannel(this.chat) == false) goto L680;
        if (ChatObject.isMegagroup(this.chat) == true) goto L680;
        if (this.chat.signature_profiles == false) goto L680;
    L679:
        if (ForumUtilities.isTopicCreateMessage(this.message) == false) goto L920;
    L668:
        if (ChatObject.isChannel(r076) == false) goto L679;
        if (ChatObject.isMegagroup(this.chat) == true) goto L679;
        if (r55 != null) goto L679;
    L680:
        TLRPC.User r077 = this.user;
        if (r077 == null) goto L690;
        if (r077.id != UserObject.VERIFY) goto L690;
        MessageObject r078 = this.message;
        if (r078 == null) goto L690;
        if (r078.getForwardedFromId() != null) goto L920;
    L690:
        if (TextUtils.isEmpty(r1415) == true) goto L692;
        CharSequence r1417 = r1415;
    L888:
        if (this.message.isReplyToStory() == false) goto L891;
        SpannableStringBuilder r079 = new SpannableStringBuilder(r1417);
        r079.insert(0, "d ");
        r079.setSpan(new ColoredImageSpan(ContextCompat.getDrawable(getContext(), R.drawable.msg_mini_replystory).mutate()), 0, 1, 0);
        r1417 = r079;
    L891:
        if (this.thumbsCount > 0) goto L893;
    L913:
        if (r917 == true) goto L915;
    L918:
        CharSequence r254 = r1417;
    L917:
        r067 = r342;
        r248 = r254;
        goto L506
    L915:
        if (separateMessageNameLine() == true) goto L918;
        this.drawForwardIcon = true;
        SpannableStringBuilder r080 = new SpannableStringBuilder(r1417);
        r080.insert(0, "d ");
        ColoredImageSpan r255 = new ColoredImageSpan(ContextCompat.getDrawable(getContext(), R.drawable.mini_forwarded).mutate());
        r255.setAlpha(0.9f);
        r080.setSpan(r255, 0, 1, 0);
        r254 = r080;
        goto L917
    L893:
        if (this.message.hasHighlightedWords() == false) goto L901;
        if (TextUtils.isEmpty(this.message.messageOwner.message) == true) goto L901;
        CharSequence r081 = this.message.messageTrimmedToHighlight;
        int r256 = getMeasuredWidth() - AndroidUtilities.dp(((this.messagePaddingStart + 23) + ((this.thumbSize + 2) * this.thumbsCount)) + 3);
        MessageObject r343 = this.message;
        if (r343.messageTrimmedToHighlightCut == false) goto L905;
        r081 = AndroidUtilities.ellipsizeCenterEnd(r081, r343.highlightedWords.get(0), r256, this.currentMessagePaint, 130);
    L905:
        if ((r081 instanceof SpannableStringBuilder) == true) goto L907;
        r081 = new SpannableStringBuilder(r081);
    L907:
        SpannableStringBuilder r082 = (SpannableStringBuilder) r081;
        r082.insert(0, " ");
        r082.setSpan(new ThumbnailSpan(AndroidUtilities.dp(((this.thumbSize + 2) * this.thumbsCount) + 3)), 0, 1, 33);
        CharSequence r257 = Emoji.replaceEmoji(r082, Theme.dialogs_messagePaint[this.paintIndex].getFontMetricsInt(), false);
        if (this.message.hasHighlightedWords() == false) goto L912;
        r1417 = AndroidUtilities.highlightText(r082, this.message.highlightedWords, this.resourcesProvider);
        if (r1417 == null) goto L912;
    L911:
        r342 = false;
    L912:
        r1417 = r257;
    L901:
        if (r1417.length() <= 150) goto L903;
        r1417 = r1417.subSequence(0, 150);
    L903:
        r081 = AndroidUtilities.replaceNewLines(r1417);
        goto L905
    L692:
        boolean r083 = MessageObject.isTopicActionMessage(this.message);
        MessageObject r344 = this.message;
        if (r083 == false) goto L707;
        CharSequence r084 = r344.messageTextShort;
        if (r084 != null) goto L697;
    L701:
        r084 = r344.messageText;
    L700:
        r1417 = r084;
        if ((r344.topicIconDrawable[0] instanceof ForumBubbleDrawable) == false) goto L888;
        TLRPC.TL_forumTopic r085 = MessagesController.getInstance(this.currentAccount).getTopicsController().findTopic(-this.message.getDialogId(), MessageObject.getTopicId(this.currentAccount, this.message.messageOwner, true));
        if (r085 == null) goto L888;
        ((ForumBubbleDrawable) this.message.topicIconDrawable[0]).setColor(r085.icon_color);
        goto L888
    L697:
        if ((r344.messageOwner.action instanceof TLRPC.TL_messageActionTopicCreate) == false) goto L700;
        if (this.isTopic == true) goto L701;
    L707:
        TLRPC.MessageMedia r086 = r344.messageOwner.media;
        if ((r086 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L715;
        if ((r086.photo instanceof TLRPC.TL_photoEmpty) == false) goto L715;
        if (r086.ttl_seconds == 0) goto L715;
        r1417 = LocaleController.getString(R.string.AttachPhotoExpired);
    L715:
        if ((r086 instanceof TLRPC.TL_messageMediaDocument) == false) goto L729;
        TLRPC.Document r258 = r086.document;
        if ((r258 instanceof TLRPC.TL_documentEmpty) == true) goto L720;
        if (r258 != null) goto L729;
    L720:
        if (r086.ttl_seconds == 0) goto L729;
        if (r086.voice == false) goto L725;
        r1417 = LocaleController.getString(R.string.AttachVoiceExpired);
        goto L888
    L725:
        if (r086.round == false) goto L727;
        r1417 = LocaleController.getString(R.string.AttachRoundExpired);
        goto L888
    L727:
        r1417 = LocaleController.getString(R.string.AttachVideoExpired);
    L729:
        if (getCaptionMessage() != null) goto L731;
    L772:
        MessageObject r087 = this.message;
        TLRPC.Message r259 = r087.messageOwner;
        TLRPC.MessageMedia r345 = r259.media;
        if ((r345 instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L786;
        int r088 = ((TLRPC.TL_messageMediaPaidMedia) r345).extended_media.size();
        if (this.hasVideoThumb == false) goto L781;
        if (r088 <= 1) goto L779;
        String r089 = LocaleController.formatPluralString("Media", r088, new Object[0]);
    L784:
        r1417 = StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.AttachPaidMedia, new Object[]{r089}));
        this.currentMessagePaint = Theme.dialogs_messagePrintingPaint[this.paintIndex];
        goto L888
    L779:
        r089 = LocaleController.getString(R.string.AttachVideo);
        goto L784
    L781:
        if (r088 <= 1) goto L783;
        r089 = LocaleController.formatPluralString("Photos", r088, new Object[0]);
        goto L784
    L783:
        r089 = LocaleController.getString(R.string.AttachPhoto);
        goto L784
    L786:
        if (this.thumbsCount <= 1) goto L802;
        boolean r090 = this.hasVideoThumb;
        ArrayList<MessageObject> r260 = this.groupMessages;
        if (r090 == false) goto L796;
        if (r260 != null) goto L792;
        int r091 = 0;
    L793:
        CharSequence r092 = LocaleController.formatPluralString("Media", r091, new Object[0]);
    L794:
        r1417 = r092;
        this.currentMessagePaint = Theme.dialogs_messagePrintingPaint[this.paintIndex];
        goto L888
    L792:
        r091 = r260.size();
        goto L793
    L796:
        if (r260 != null) goto L798;
        int r093 = 0;
    L799:
        r092 = LocaleController.formatPluralString("Photos", r093, new Object[0]);
        goto L794
    L798:
        r093 = r260.size();
        goto L799
    L802:
        if ((r345 instanceof TLRPC.TL_messageMediaGiveaway) == false) goto L815;
        TLRPC.MessageFwdHeader r094 = r259.fwd_from;
        if (r094 == null) goto L808;
        TLRPC.Peer r095 = r094.from_id;
        if ((r095 instanceof TLRPC.TL_peerChannel) == false) goto L808;
        boolean r096 = ChatObject.isChannelAndNotMegaGroup(r095.channel_id, this.currentAccount);
    L809:
        if (r096 == false) goto L811;
        int r097 = R.string.BoostingGiveawayChannelStarted;
    L812:
        CharSequence r098 = LocaleController.getString(r097);
    L813:
        r1417 = r098;
        MessageObject r099 = this.message;
        if (r099.messageOwner.media != null) goto L879;
    L880:
        TL_iv.RichMessage r0100 = this.message.messageOwner.rich_message;
        if (r0100 == null) goto L888;
        if (r0100.blocks.size() != 1) goto L888;
        if (MessageObject.isBlueBlock(this.message.messageOwner.rich_message.blocks.get(0)) == false) goto L888;
    L886:
        this.currentMessagePaint = Theme.dialogs_messagePrintingPaint[this.paintIndex];
        goto L888
    L879:
        if (r099.isMediaEmpty() == false) goto L886;
    L811:
        r097 = R.string.BoostingGiveawayGroupStarted;
    L808:
        r096 = ChatObject.isChannelAndNotMegaGroup(this.chat);
        goto L809
    L815:
        if ((r345 instanceof TLRPC.TL_messageMediaGiveawayResults) == false) goto L818;
        r098 = LocaleController.getString(R.string.BoostingGiveawayResults);
        goto L813
    L818:
        if ((r345 instanceof TLRPC.TL_messageMediaPoll) == false) goto L826;
        TLRPC.TL_messageMediaPoll r346 = (TLRPC.TL_messageMediaPoll) r345;
        TLRPC.TL_textWithEntities r0101 = r346.poll.question;
        if (r0101 != null) goto L822;
    L824:
        r098 = DialogMediaIconsHelper.addDialogMediaSpan(r0101.text, R.drawable.dialog_media_poll_20, false);
        goto L813
    L822:
        if (r0101.entities == null) goto L824;
        SpannableString r0102 = new SpannableString(r346.poll.question.text);
        TLRPC.TL_textWithEntities r261 = r346.poll.question;
        MediaDataController.addTextStyleRuns(r261.entities, r261.text, r0102);
        MediaDataController.addAnimatedEmojiSpans(r346.poll.question.entities, r0102, Theme.dialogs_messagePaint[this.paintIndex].getFontMetricsInt());
        r098 = DialogMediaIconsHelper.addDialogMediaSpan(r0102, R.drawable.dialog_media_poll_20, false);
        goto L813
    L826:
        if ((r345 instanceof TLRPC.TL_messageMediaToDo) == false) goto L834;
        TLRPC.TL_messageMediaToDo r347 = (TLRPC.TL_messageMediaToDo) r345;
        TLRPC.TL_textWithEntities r0103 = r347.todo.title;
        if (r0103 != null) goto L830;
    L832:
        r098 = DialogMediaIconsHelper.addDialogMediaSpan(r0103.text, R.drawable.dialog_media_checklist_20, false);
        goto L813
    L830:
        if (r0103.entities == null) goto L832;
        SpannableString r0104 = new SpannableString(r347.todo.title.text);
        TLRPC.TL_textWithEntities r262 = r347.todo.title;
        MediaDataController.addTextStyleRuns(r262.entities, r262.text, r0104);
        MediaDataController.addAnimatedEmojiSpans(r347.todo.title.entities, r0104, Theme.dialogs_messagePaint[this.paintIndex].getFontMetricsInt());
        r098 = DialogMediaIconsHelper.addDialogMediaSpan(r0104, R.drawable.dialog_media_checklist_20, false);
        goto L813
    L834:
        if ((r345 instanceof TLRPC.TL_messageMediaGame) == false) goto L837;
        r098 = DialogMediaIconsHelper.addDialogMediaSpan(r345.game.title, R.drawable.dialog_media_game_20, false);
        goto L813
    L837:
        if ((r345 instanceof TLRPC.TL_messageMediaInvoice) == false) goto L840;
        r098 = r345.title;
        goto L813
    L840:
        if (r087.type != 14) goto L843;
        r098 = String.format("🎧 %s - %s", new Object[]{r087.getMusicAuthor(), this.message.getMusicTitle()});
        goto L813
    L843:
        if ((r345 instanceof TLRPC.TL_messageMediaStory) == false) goto L857;
        if (r345.via_mention == false) goto L857;
        if (r087.isOut() == false) goto L855;
        TLRPC.User r0105 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.message.getDialogId()));
        if (r0105 == null) goto L853;
        String r0106 = UserObject.getFirstName(r0105);
        int r348 = r0106.indexOf(32);
        if (r348 < 0) goto L854;
        r0106 = r0106.substring(0, r348);
    L854:
        r098 = LocaleController.formatString(R.string.StoryYouMentionInDialog, new Object[]{r0106});
        goto L813
    L853:
        r0106 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L854
    L855:
        r098 = LocaleController.getString(R.string.StoryMentionInDialog);
    L857:
        if (r087.hasHighlightedWords() == true) goto L859;
    L863:
        SpannableString r0107 = new SpannableString(r1416);
        MessageObject r263 = this.message;
        if (r263 == null) goto L866;
        r263.spoilLoginCode();
    L866:
        MediaDataController.addTextStyleRuns(this.message, r0107, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED);
        MessageObject r264 = this.message;
        CharSequence r0108 = r0107;
        if (r264 == null) goto L875;
        TLRPC.Message r265 = r264.messageOwner;
        r0108 = r0107;
        if (r265 == null) goto L875;
        ArrayList<TLRPC.MessageEntity> r266 = r265.entities;
        TextPaint r349 = this.currentMessagePaint;
        if (r349 != null) goto L873;
        Paint.FontMetricsInt r350 = null;
    L874:
        MediaDataController.addAnimatedEmojiSpans(r266, r0107, r350);
        r0108 = r0107;
        goto L875
    L873:
        r350 = r349.getFontMetricsInt();
    L875:
        AndroidUtilities.highlightText(r0108, this.message.highlightedWords, this.resourcesProvider);
        r098 = r0108;
        goto L813
    L859:
        if (TextUtils.isEmpty(this.message.messageOwner.message) == true) goto L863;
        CharSequence r0109 = this.message.messageTrimmedToHighlight;
        int r267 = getMeasuredWidth() - AndroidUtilities.dp(this.messagePaddingStart + 23);
        MessageObject r351 = this.message;
        r0108 = r0109;
        if (r351.messageTrimmedToHighlightCut == false) goto L875;
        r0108 = AndroidUtilities.ellipsizeCenterEnd(r0109, r351.highlightedWords.get(0), r267, this.currentMessagePaint, 130);
        goto L875
    L731:
        if ((this.message.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) == true) goto L772;
        MessageObject r0110 = getCaptionMessage();
        if (this.needEmoji == true) goto L736;
        String r268 = _UrlKt.FRAGMENT_ENCODE_SET;
    L749:
        if (r0110.hasHighlightedWords() == true) goto L751;
    L764:
        SpannableString r353 = new SpannableString(r0110.caption);
        if (r0110.messageOwner == null) goto L771;
        r0110.spoilLoginCode();
        MediaDataController.addTextStyleRuns(r0110.messageOwner.entities, r0110.caption, r353, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED);
        ArrayList<TLRPC.MessageEntity> r0111 = r0110.messageOwner.entities;
        TextPaint r531 = this.currentMessagePaint;
        if (r531 != null) goto L769;
        Paint.FontMetricsInt r532 = null;
    L770:
        MediaDataController.addAnimatedEmojiSpans(r0111, r353, r532);
        goto L771
    L769:
        r532 = r531.getFontMetricsInt();
    L771:
        CharSequence r0112 = new SpannableStringBuilder(r268).append(r353);
    L763:
        r1417 = r0112;
        goto L888
    L751:
        if (TextUtils.isEmpty(r0110.messageOwner.message) == true) goto L764;
        CharSequence r354 = r0110.messageTrimmedToHighlight;
        int r533 = getMeasuredWidth() - AndroidUtilities.dp(this.messagePaddingStart + 47);
        if (this.hasNameInMessage == false) goto L758;
        boolean r1217 = TextUtils.isEmpty(null);
        TextPaint r1418 = this.currentMessagePaint;
        if (r1217 == false) goto L757;
        r533 = (int) (r533 - r1418.measureText(": "));
        goto L758
    L757:
        throw null;
    L758:
        if (r533 > 0) goto L760;
    L762:
        r0112 = new SpannableStringBuilder(r268).append(r354);
        goto L763
    L760:
        if (r0110.messageTrimmedToHighlightCut == false) goto L762;
        r354 = AndroidUtilities.ellipsizeCenterEnd(r354, r0110.highlightedWords.get(0), r533, this.currentMessagePaint, 130);
        goto L762
    L736:
        if (r0110.isVideo() == false) goto L739;
        r268 = "📹 ";
        goto L749
    L739:
        if (r0110.isVoice() == false) goto L742;
        r268 = "🎤 ";
        goto L749
    L742:
        if (r0110.isMusic() == false) goto L745;
        r268 = "🎧 ";
        goto L749
    L745:
        if (r0110.isPhoto() == false) goto L747;
        r268 = "🖼 ";
        goto L749
    L747:
        r268 = "📎 ";
        goto L749
    L640:
        if (this.isSavedDialog == false) goto L651;
        TLRPC.User r0113 = this.user;
        if (r0113 == null) goto L651;
        if (r0113.self == true) goto L651;
        MessageObject r0114 = this.message;
        if (r0114 == null) goto L651;
        if (r0114.isOutOwner() == true) goto L650;
    L636:
        r1313 = false;
        goto L637
    L622:
        if (ChatObject.isChannel(r1028) == false) goto L627;
        if (ChatObject.isMegagroup(this.chat) == true) goto L627;
        if (this.chat.signature_profiles == true) goto L627;
    L628:
        r1029 = false;
        goto L629
    L611:
        r818 = false;
    L607:
        r337 = null;
        goto L609
    L565:
        if (MessageObject.isTopicActionMessage(r335) == true) goto L567;
    L570:
        MessageObject r355 = this.message;
        TLRPC.MessageAction r534 = r355.messageOwner.action;
        if ((r534 instanceof TLRPC.TL_messageActionPhoneCall) == false) goto L586;
        TLRPC.TL_messageActionPhoneCall r535 = (TLRPC.TL_messageActionPhoneCall) r534;
        if (r355.isOutOwner() == true) goto L575;
        CharSequence r1419 = r34;
        if (r535.video == false) goto L583;
        int r356 = R.drawable.dialog_media_incoming_video_call_20;
    L584:
        CharSequence r357 = DialogMediaIconsHelper.addDialogMediaSpan(r1419, r356, false);
    L593:
        CharSequence r1420 = r357;
    L585:
        boolean r1030 = r30;
    L594:
        this.currentMessagePaint = Theme.dialogs_messagePrintingPaint[this.paintIndex];
        if (this.message.type != 21) goto L597;
        updateMessageThumbs();
        r30 = r1030;
        r283 = null;
        r1026 = r237;
        r249 = applyThumbs(r1420);
        goto L1012
    L597:
        r30 = r1030;
        r283 = null;
        r1026 = r237;
        r249 = r1420;
        goto L1012
    L583:
        r356 = R.drawable.dialog_media_incoming_call_20;
        goto L584
    L575:
        if (r535.video == false) goto L578;
        int r358 = R.drawable.dialog_media_outgoing_video_call_20;
    L579:
        r357 = DialogMediaIconsHelper.addDialogMediaSpan(r34, r358, false);
        goto L593
    L578:
        r358 = R.drawable.dialog_media_outgoing_call_20;
        goto L579
    L586:
        r1420 = r34;
        if (ChatObject.isChannelAndNotMegaGroup(this.chat) == true) goto L589;
    L591:
        r357 = this.message.messageTextShort;
        if (r357 == null) goto L585;
    L589:
        if ((this.message.messageOwner.action instanceof TLRPC.TL_messageActionChannelMigrateFrom) == false) goto L591;
        r1420 = _UrlKt.FRAGMENT_ENCODE_SET;
        r1030 = false;
        goto L594
    L567:
        if ((this.message.messageOwner.action instanceof TLRPC.TL_messageActionTopicCreate) == false) goto L569;
    L558:
        if (SharedConfig.useThreeLinesLayout == true) goto L562;
        if (this.currentDialogFolderId == 0) goto L562;
        r072 = formatArchivedDialogNames();
    L554:
        r1026 = r237;
        r283 = null;
        goto L555
    L550:
        if (SharedConfig.useThreeLinesLayout == true) goto L556;
        if (this.currentDialogCommunityId == 0) goto L556;
        r072 = formatCommunityDialogNames();
        goto L554
    L539:
        if (UserObject.isUserSelf(this.user) == false) goto L547;
        DialogsActivity r359 = this.parentFragment;
        if (r359 != null) goto L543;
    L545:
        int r360 = R.string.SavedMessagesInfo;
    L546:
        r329 = LocaleController.getString(r360);
        goto L535
    L543:
        if (r359.isQuote == false) goto L545;
        r360 = R.string.SavedMessagesInfoQuote;
        goto L546
    L476:
        if (this.currentDialogId <= 0) goto L502;
        if (this.message.isOutOwner() == false) goto L502;
        TLRPC.TL_messageReactions r361 = this.message.messageOwner.reactions;
        if (r361 == null) goto L502;
        ArrayList<TLRPC.MessagePeerReaction> r364 = r361.recent_reactions;
        if (r364 == null) goto L502;
        if (r364.isEmpty() == true) goto L502;
        if (this.reactionMentionCount <= 0) goto L502;
        TLRPC.MessagePeerReaction r365 = this.message.messageOwner.reactions.recent_reactions.get(0);
        if (r365.unread == false) goto L502;
        long r1314 = r365.peer_id.user_id;
        if (r1314 == 0) goto L502;
        r392 = r916;
        if (r1314 == UserConfig.getInstance(this.currentAccount).clientUserId) goto L503;
        ReactionsLayoutInBubble.VisibleReaction r366 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r365.reaction);
        this.currentMessagePaint = Theme.dialogs_messagePrintingPaint[this.paintIndex];
        String r820 = r366.emojicon;
        if (r820 == null) goto L497;
        r325 = LocaleController.formatString(R.string.ReactionInDialog, new Object[]{r820});
    L496:
        r817 = true;
        goto L504
    L497:
        String r821 = LocaleController.formatString(R.string.ReactionInDialog, new Object[]{"**reaction**"});
        int r1031 = r821.indexOf("**reaction**");
        SpannableStringBuilder r918 = new SpannableStringBuilder(r821.replace("**reaction**", "d"));
        long r1315 = r366.documentId;
        TextPaint r367 = this.currentMessagePaint;
        if (r367 != null) goto L500;
        Paint.FontMetricsInt r368 = null;
    L501:
        r918.setSpan(new AnimatedEmojiSpan(r1315, r368), r1031, r1031 + 1, 0);
        r325 = r918;
        goto L496
    L500:
        r368 = r367.getFontMetricsInt();
        goto L501
    L472:
        r1114 = MessagesController.getInstance(r324).getChat(Long.valueOf(-r1311));
        goto L473
    L434:
        if (this.currentDialogCommunityId == 0) goto L437;
        r526 = formatCommunityDialogNames();
        goto L430
    L437:
        if (this.currentDialogFolderId == 0) goto L439;
        r526 = formatArchivedDialogNames();
        goto L430
    L439:
        TLRPC.EncryptedChat r536 = this.encryptedChat;
        if (r536 == null) goto L458;
        this.currentMessagePaint = Theme.dialogs_messagePrintingPaint[this.paintIndex];
        if ((r536 instanceof TLRPC.TL_encryptedChatRequested) == false) goto L445;
        r526 = LocaleController.getString(R.string.EncryptionProcessing);
        goto L430
    L445:
        if ((r536 instanceof TLRPC.TL_encryptedChatWaiting) == false) goto L448;
        r526 = LocaleController.formatString(R.string.AwaitingEncryption, new Object[]{UserObject.getFirstName(this.user)});
        goto L430
    L448:
        if ((r536 instanceof TLRPC.TL_encryptedChatDiscarded) == false) goto L451;
        r526 = LocaleController.getString(R.string.EncryptionRejected);
        goto L430
    L451:
        if ((r536 instanceof TLRPC.TL_encryptedChat) == true) goto L453;
    L456:
        r511 = r067;
        r19 = r237;
        r233 = _UrlKt.FRAGMENT_ENCODE_SET;
        r102 = r30;
        r05 = null;
        r914 = r916;
        goto L407
    L453:
        if (r536.admin_id != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L455;
        r526 = LocaleController.formatString(R.string.EncryptedChatStartedOutgoing, new Object[]{UserObject.getFirstName(this.user)});
        goto L430
    L455:
        r526 = LocaleController.getString(R.string.EncryptedChatStartedIncoming);
        goto L430
    L458:
        if (this.dialogsType != 3) goto L456;
        if (UserObject.isUserSelf(this.user) == false) goto L456;
        DialogsActivity r448 = this.parentFragment;
        if (r448 != null) goto L464;
    L466:
        int r449 = R.string.SavedMessagesInfo;
    L467:
        r511 = r067;
        r19 = r237;
        r233 = LocaleController.getString(r449);
        r05 = null;
        r43 = false;
        r102 = false;
        r914 = r916;
        goto L407
    L464:
        if (r448.isQuote == false) goto L466;
        r449 = R.string.SavedMessagesInfoQuote;
        goto L467
    L419:
        r915.append(" ").append(r066).setSpan(new FixedWidthSpan(r236), 0, 1, 0);
        goto L420
    L416:
        r1024 = -1;
        goto L417
    L412:
        r236 = 0;
        goto L413
    L421:
        this.lastPrintString = null;
        this.printingStringType = -1;
        r237 = -1;
        r916 = _UrlKt.FRAGMENT_ENCODE_SET;
        r067 = true;
        goto L423
    L336:
        if (r39.reply_to_msg_id == 0) goto L338;
    L339:
        TLRPC.DraftMessage r369 = this.draftMessage;
        if (r369 != null) goto L342;
    L346:
        if (ChatObject.isChannel(this.chat) == false) goto L355;
        TLRPC.Chat r269 = this.chat;
        if (r269.megagroup == true) goto L355;
        if (r269.creator == true) goto L355;
        TLRPC.TL_chatAdminRights r270 = r269.admin_rights;
        if (r270 == null) goto L338;
        if (r270.post_messages == false) goto L338;
    L355:
        TLRPC.Chat r271 = this.chat;
        if (r271 == null) goto L362;
        if (r271.left == true) goto L338;
        if (r271.kicked == true) goto L338;
    L362:
        if (this.forbidDraft == true) goto L338;
        if (ChatObject.isForum(r271) == true) goto L366;
    L368:
        r93 = false;
        r112 = null;
        goto L371
    L366:
        if (this.isTopic == true) goto L368;
    L342:
        if (r2 <= r369.date) goto L346;
        if (this.unreadCount != 0) goto L338;
    L325:
        if (this.draftMessage == null) goto L346;
    L300:
        r35 = null;
        goto L301
    L296:
        r33 = false;
        goto L297
    L308:
        if (this.isDialogCell == true) goto L314;
        if (this.isSavedDialogCell == true) goto L314;
        this.draftVoice = false;
        this.draftMessage = null;
    L314:
        if (MediaDataController.getInstance(this.currentAccount).getDraftVoice(this.currentDialogId, getTopicId()) == null) goto L316;
        boolean r370 = true;
    L317:
        this.draftVoice = r370;
        if (r370 == true) goto L320;
        TLRPC.DraftMessage r371 = MediaDataController.getInstance(this.currentAccount).getDraft(this.currentDialogId, 0);
    L321:
        this.draftMessage = r371;
        goto L322
    L320:
        r371 = null;
        goto L321
    L316:
        r370 = false;
        goto L317
    L181:
        if (r3 != 0) goto L175;
        if (this.isTopic == true) goto L175;
        TLRPC.Chat r372 = this.chat;
        if (r372 == null) goto L215;
        long r284 = DialogObject.getBotVerificationIcon(r372);
        BadgeDTO r373 = BadgesController.INSTANCE.getBadge(this.chat);
        TLRPC.Chat r1115 = this.chat;
        if (r1115.scam == false) goto L191;
        this.drawScam = 1;
        Theme.dialogs_scamDrawable.checkText();
    L189:
        r30 = r10;
    L214:
        r31 = 2;
        r34 = r143;
        long r273 = r284;
    L280:
        this.botVerification.setParticles(r7, false, 5);
        if (r273 != 0) goto L283;
    L285:
        this.botVerification.set(null, false);
        goto L286
    L283:
        if (this.drawBotVerified == false) goto L285;
        this.botVerification.set(r273, false);
        goto L286
    L191:
        if (r1115.fake == false) goto L194;
        this.drawScam = 2;
        Theme.dialogs_fakeDrawable.checkText();
        goto L189
    L194:
        if (DialogObject.getEmojiStatusDocumentId(r1115.emoji_status) == 0) goto L196;
        this.drawPremium = true;
        this.nameLayoutEllipsizeByGradient = true;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r274 = this.emojiStatus;
        r274.center = LocaleController.isRTL;
        r30 = r10;
        r274.set(DialogObject.getEmojiStatusDocumentId(this.chat.emoji_status), false);
        this.emojiStatus.setParticles(DialogObject.isEmojiStatusCollectible(this.chat.emoji_status), false);
        goto L214
    L196:
        r30 = r10;
        if (r373 != null) goto L199;
    L201:
        boolean r275 = this.forbidVerified;
        if (r275 == false) goto L204;
    L206:
        boolean r374 = false;
    L207:
        this.drawVerified = r374;
        if (r275 == false) goto L210;
    L212:
        boolean r276 = false;
    L213:
        this.drawBotVerified = r276;
        goto L214
    L210:
        if (this.chat.bot_verification_icon == 0) goto L212;
        r276 = true;
        goto L213
    L204:
        if (this.chat.verified == false) goto L206;
        r374 = true;
        goto L207
    L199:
        if (this.isTopic == true) goto L201;
        this.drawBadge = true;
        this.nameLayoutEllipsizeByGradient = true;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r277 = this.emojiStatus;
        r277.center = LocaleController.isRTL;
        r277.set(r373.getDocumentId(), false);
        this.emojiStatus.setParticles(true, false);
        goto L214
    L215:
        r30 = r10;
        TLRPC.User r278 = this.user;
        if (r278 == null) goto L279;
        r273 = DialogObject.getBotVerificationIcon(r278);
        TLRPC.User r919 = this.user;
        if (r919.scam == false) goto L221;
        this.drawScam = 1;
        Theme.dialogs_scamDrawable.checkText();
    L238:
        BadgesController r920 = BadgesController.INSTANCE;
        BadgeDTO r1032 = r920.getBadge(this.user);
        BadgeDTO r921 = r920.getSecondaryBadge(this.user);
        if (r1032 == null) goto L245;
        TLRPC.User r1116 = this.user;
        if (r1116 == null) goto L245;
        if (UserObject.isUserSelf(r1116) == true) goto L245;
        boolean r1117 = true;
    L247:
        if (this.drawScam == 0) goto L249;
    L252:
        boolean r613 = true;
    L253:
        this.drawBadge = false;
        r31 = 2;
        if (MessagesController.getInstance(this.currentAccount).isPremiumUser(this.user) == false) goto L260;
        BadgeDTO r3310 = r921;
        long r822 = UserConfig.getInstance(this.currentAccount).clientUserId;
        r34 = r143;
        long r1316 = this.user.id;
        if (r822 != r1316) goto L258;
    L261:
        boolean r75 = false;
    L262:
        this.drawPremium = r75;
        if (r75 == false) goto L271;
        Long r76 = UserObject.getEmojiStatusDocumentId(this.user);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r823 = this.emojiStatus;
        r823.center = LocaleController.isRTL;
        if (r76 == null) goto L267;
        boolean r1317 = true;
        this.nameLayoutEllipsizeByGradient = true;
        r823.set(r76.longValue(), false);
        this.emojiStatus.setParticles(DialogObject.isEmojiStatusCollectible(this.user.emoji_status), false);
        r613 = true;
    L274:
        if (r3310 == null) goto L278;
        if (r1117 == false) goto L278;
        if (r613 == false) goto L278;
        r273 = r3310.getDocumentId();
        this.drawBotVerified = r1317;
        r7 = true;
    L278:
        r7 = false;
        goto L280
    L267:
        r1317 = true;
        if (r1117 == false) goto L270;
        this.drawBadge = true;
        r823.set(r1032.getDocumentId(), false);
        r1317 = true;
        this.emojiStatus.setParticles(true, false);
        goto L274
    L270:
        this.nameLayoutEllipsizeByGradient = true;
        r823.set(PremiumGradient.getInstance().premiumStarDrawableMini, false);
        this.emojiStatus.setParticles(false, false);
        goto L274
    L271:
        r1317 = true;
        if (r1117 == false) goto L274;
        this.drawBadge = true;
        this.emojiStatus.set(r1032.getDocumentId(), false);
        r1317 = true;
        this.emojiStatus.setParticles(true, false);
        goto L274
    L258:
        if (r1316 == 0) goto L261;
        r75 = true;
        goto L262
    L260:
        r3310 = r921;
        r34 = r143;
        goto L261
    L249:
        if (this.drawVerified == true) goto L252;
        r613 = false;
    L245:
        r1117 = false;
        goto L247
    L221:
        if (r919.fake == false) goto L223;
        this.drawScam = 2;
        Theme.dialogs_fakeDrawable.checkText();
        goto L238
    L223:
        boolean r1033 = this.forbidVerified;
        if (r1033 == false) goto L226;
    L228:
        boolean r1118 = false;
    L229:
        this.drawVerified = r1118;
        if (r1033 == false) goto L232;
    L236:
        boolean r922 = false;
    L237:
        this.drawBotVerified = r922;
        goto L238
    L232:
        if (UserObject.isUserSelf(r919) == true) goto L236;
        if (this.user.bot_verification_icon == 0) goto L236;
        r922 = true;
        goto L237
    L226:
        if (r919.verified == false) goto L228;
        r1118 = true;
        goto L229
    L279:
        r31 = 2;
        r34 = r143;
        r273 = 0;
        goto L278
    L159:
        this.nameLeft = AndroidUtilities.dp(18.0f);
    L161:
        if (LocaleController.isRTL == true) goto L163;
        this.nameLeft = AndroidUtilities.dp(this.messagePaddingStart + 6);
        goto L164
    L163:
        this.nameLeft = AndroidUtilities.dp(22.0f);
        goto L164
    L142:
        if (r54.megagroup == true) goto L150;
        MessageObject r537 = this.message;
        if (r537 == null) goto L150;
        TLRPC.Message r538 = r537.messageOwner;
        if (r538 == null) goto L150;
        if (TextUtils.isEmpty(r538.post_author) == true) goto L150;
        r55 = this.message.messageOwner.post_author;
        goto L152
    L73:
        if (r53.type != 2) goto L88;
        this.drawNameLock = true;
        if (this.useForceThreeLines == false) goto L77;
    L83:
        this.nameLockTop = AndroidUtilities.dp(12.5f);
        if (LocaleController.isRTL == true) goto L86;
        this.nameLockLeft = AndroidUtilities.dp(this.messagePaddingStart + 6);
        this.nameLeft = AndroidUtilities.dp(this.messagePaddingStart + 10) + Theme.dialogs_lockDrawable.getIntrinsicWidth();
    L106:
        CustomDialog r0115 = this.customDialog;
        if (r0115.type != 1) goto L122;
        CharSequence r0116 = LocaleController.getString(R.string.FromYou);
        CustomDialog r539 = this.customDialog;
        if (r539.isMedia == false) goto L111;
        this.currentMessagePaint = Theme.dialogs_messagePrintingPaint[this.paintIndex];
        SpannableStringBuilder r540 = formatInternal(r12, this.message.messageText, null);
        r540.setSpan(new ForegroundColorSpanThemable(Theme.key_chats_attachMessage, this.resourcesProvider), 0, r540.length(), 33);
    L121:
        r314 = Emoji.replaceEmoji(r540, Theme.dialogs_messagePaint[this.paintIndex].getFontMetricsInt(), false);
        r511 = false;
    L126:
        r26 = '\n';
        r104 = LocaleController.stringForMessageListDate(this.customDialog.date);
        int r1119 = this.customDialog.unread_count;
        if (r1119 == 0) goto L129;
        this.drawCount = true;
        String r1120 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r1119)});
    L130:
        CustomDialog r1218 = this.customDialog;
        int r1421 = r1218.sent;
        if (r1421 != 0) goto L133;
        this.drawClock = true;
        this.drawCheck1 = false;
        this.drawCheck2 = false;
    L138:
        this.drawError = false;
        CharSequence r1219 = r1218.name;
        r211 = r0116;
        r63 = null;
        r84 = r1120;
        r332 = _UrlKt.FRAGMENT_ENCODE_SET;
        r72 = -1;
        r123 = r1219;
        goto L1319
    L133:
        if (r1421 != 2) goto L135;
        this.drawCheck1 = true;
        this.drawCheck2 = true;
        this.drawClock = false;
        goto L138
    L135:
        if (r1421 != 1) goto L137;
        this.drawCheck1 = false;
        this.drawCheck2 = true;
        this.drawClock = false;
        goto L138
    L137:
        this.drawClock = false;
        this.drawCheck1 = false;
        this.drawCheck2 = false;
        goto L138
    L129:
        this.drawCount = false;
        r1120 = null;
        goto L130
    L111:
        String r375 = r539.message;
        if (r375.length() <= 150) goto L115;
        r375 = r375.substring(0, 150);
    L115:
        if (this.useForceThreeLines == false) goto L117;
    L120:
        r540 = formatInternal(r12, r375, r0116);
        goto L121
    L117:
        if (SharedConfig.useThreeLinesLayout == true) goto L120;
        r540 = formatInternal(r12, r375.replace('\n', ' '), r0116);
        goto L121
    L122:
        r314 = r0115.message;
        if (r0115.isMedia == false) goto L125;
        this.currentMessagePaint = Theme.dialogs_messagePrintingPaint[this.paintIndex];
    L125:
        r511 = true;
        r0116 = null;
        goto L126
    L86:
        this.nameLockLeft = (getMeasuredWidth() - AndroidUtilities.dp(this.messagePaddingStart + 6)) - Theme.dialogs_lockDrawable.getIntrinsicWidth();
        this.nameLeft = AndroidUtilities.dp(22.0f);
        goto L106
    L77:
        if (SharedConfig.useThreeLinesLayout == true) goto L83;
        this.nameLockTop = AndroidUtilities.dp(16.5f);
        if (LocaleController.isRTL == true) goto L82;
        this.nameLockLeft = AndroidUtilities.dp(this.messagePaddingStart + 4);
        this.nameLeft = AndroidUtilities.dp(this.messagePaddingStart + 8) + Theme.dialogs_lockDrawable.getIntrinsicWidth();
        goto L106
    L82:
        this.nameLockLeft = (getMeasuredWidth() - AndroidUtilities.dp(this.messagePaddingStart + 4)) - Theme.dialogs_lockDrawable.getIntrinsicWidth();
        this.nameLeft = AndroidUtilities.dp(18.0f);
        goto L106
    L88:
        if (this.forbidVerified == false) goto L90;
    L92:
        boolean r0117 = false;
    L93:
        this.drawVerified = r0117;
        if (this.useForceThreeLines == true) goto L103;
        if (SharedConfig.useThreeLinesLayout == true) goto L103;
        if (LocaleController.isRTL == true) goto L101;
        this.nameLeft = AndroidUtilities.dp(this.messagePaddingStart + 4);
        goto L106
    L101:
        this.nameLeft = AndroidUtilities.dp(18.0f);
    L103:
        if (LocaleController.isRTL == true) goto L105;
        this.nameLeft = AndroidUtilities.dp(this.messagePaddingStart + 6);
        goto L106
    L105:
        this.nameLeft = AndroidUtilities.dp(22.0f);
        goto L106
    L90:
        if (r53.verified == false) goto L92;
        r0117 = true;
        goto L93
    L69:
        r43 = true;
        goto L70
    L65:
        r16 = 17.0f;
        r17 = 16.0f;
        r18 = 15.0f;
        goto L66
    L55:
        r143 = null;
    L41:
        if (this.currentDialogFolderId != 0) goto L48;
        if (isForumCell() == true) goto L48;
        if (hasTags() == true) goto L48;
        this.hasNameInMessage = false;
        r12 = 2;
        goto L49
    L30:
        if (this.useMeForMyMessages == true) goto L32;
        r10 = true;
        goto L33
    L22:
        if (this.isDialogCell == false) goto L24;
    L25:
        r03 = MessagesController.getInstance(this.currentAccount).getPrintingString(this.currentDialogId, getTopicId(), true);
        goto L27
    L24:
        if (this.isTopic == false) goto L26;
        goto L25
    }

    public boolean checkCurrentDialogIndex(boolean r1) {
        return false;
    }

    public void checkHeight() {
        if (getMeasuredHeight() > 0) goto L5;
        return;
    L5:
        if (getMeasuredHeight() == computeHeight()) goto L9;
        requestLayout();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r3) {
        if (this.isTopic == true) goto L9;
        if (this.isShareToStoryCell == true) goto L9;
        if (r3.getAction() != 1) goto L9;
    L10:
        this.storyParams.checkOnTouchEvent(r3, this);
    L12:
        return super.dispatchTouchEvent(r3);
    L9:
        if (r3.getAction() != 3) goto L12;
        goto L10
    }

    @Override // org.telegram.ui.Stories.StoriesListPlaceProvider.AvatarOverlaysView
    public boolean drawAvatarOverlays(Canvas r21) {
        TLRPC.Chat r2 = this.chat;
        boolean r6 = false;
        if (r2 != null) goto L5;
    L22:
        boolean r22 = false;
    L23:
        AnimatedFloat r7 = this.premiumBlockedT;
        if (this.premiumBlocked == false) goto L27;
        if (r22 == true) goto L27;
        boolean r8 = true;
    L28:
        float r72 = r7.set(r8);
        float r10 = 10.0f;
        if (r72 <= 0.0f) goto L39;
        float r23 = this.avatarImage.getCenterY() + AndroidUtilities.dp(18.0f);
        float r5 = this.avatarImage.getCenterX() + AndroidUtilities.dp(18.0f);
        r21.save();
        Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
        r21.drawCircle(r5, r23, AndroidUtilities.dp(11.33f) * r72, Theme.dialogs_onlineCirclePaint);
        if (this.premiumGradient != null) goto L33;
        this.premiumGradient = new PremiumGradient.PremiumGradientTools(Theme.key_premiumGradient1, Theme.key_premiumGradient2, -1, -1, -1, this.resourcesProvider);
    L33:
        this.premiumGradient.gradientMatrix((int) (r5 - AndroidUtilities.dp(10.0f)), (int) (r23 - AndroidUtilities.dp(10.0f)), (int) (AndroidUtilities.dp(10.0f) + r5), (int) (AndroidUtilities.dp(10.0f) + r23), 0.0f, 0.0f);
        r21.drawCircle(r5, r23, AndroidUtilities.dp(10.0f) * r72, this.premiumGradient.paint);
        if (this.lockDrawable != null) goto L36;
        Drawable r4 = getContext().getResources().getDrawable(R.drawable.msg_mini_lock2).mutate();
        this.lockDrawable = r4;
        r4.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
    L36:
        this.lockDrawable.setBounds((int) (r5 - (((r4.getIntrinsicWidth() / 2.0f) * 0.875f) * r72)), (int) (r23 - (((this.lockDrawable.getIntrinsicHeight() / 2.0f) * 0.875f) * r72)), (int) (r5 + (((this.lockDrawable.getIntrinsicWidth() / 2.0f) * 0.875f) * r72)), (int) (r23 + (((this.lockDrawable.getIntrinsicHeight() / 2.0f) * 0.875f) * r72)));
        this.lockDrawable.setAlpha((int) (r72 * 255.0f));
        this.lockDrawable.draw(r21);
        r21.restore();
        return false;
    L39:
        if (this.isDialogCell == true) goto L41;
    L238:
        return false;
    L41:
        if (this.currentDialogFolderId != 0) goto L238;
        if (r22 == true) goto L238;
        if (this.ttlPeriod > 0) goto L46;
    L52:
        boolean r24 = false;
    L53:
        this.showTtl = r24;
        if (this.rightFragmentOpenedProgress == 1.0f) goto L93;
        if (r24 == false) goto L57;
    L58:
        TimerDrawable r25 = this.timerDrawable;
        if (r25 == null) goto L63;
        int r26 = r25.getTime();
        int r3 = this.ttlPeriod;
        if (r26 == r3) goto L65;
        if (r3 > 0) goto L63;
    L65:
        if (this.timerPaint != null) goto L67;
        this.timerPaint = new Paint(1);
        Paint r27 = new Paint(1);
        this.timerPaint2 = r27;
        r27.setColor(838860800);
    L67:
        int r28 = (int) (this.avatarImage.getImageY2() - AndroidUtilities.dp(9.0f));
        boolean r32 = LocaleController.isRTL;
        StoriesUtilities.AvatarStoryParams r12 = this.storyParams;
        if (r32 == false) goto L71;
        float r33 = r12.originalAvatarRect.left + AndroidUtilities.dp(9.0f);
    L70:
        int r34 = (int) r33;
        this.timerDrawable.setBounds(0, 0, AndroidUtilities.dp(22.0f), AndroidUtilities.dp(22.0f));
        this.timerDrawable.setTime(this.ttlPeriod);
        if (this.avatarImage.updateThumbShaderMatrix() == false) goto L80;
        ImageReceiver r122 = this.avatarImage;
        BitmapShader r13 = r122.thumbShader;
        if (r13 == null) goto L77;
        this.timerPaint.setShader(r13);
    L89:
        r21.save();
        float r123 = this.ttlProgress * (1.0f - this.rightFragmentOpenedProgress);
        CheckBox2 r132 = this.checkBox;
        if (r132 == null) goto L92;
        r123 = r123 * (1.0f - r132.getProgress());
    L92:
        float r35 = r34;
        float r29 = r28;
        r21.scale(r123, r123, r35, r29);
        r21.drawCircle(r35, r29, AndroidUtilities.dpf2(11.0f), this.timerPaint);
        r21.drawCircle(r35, r29, AndroidUtilities.dpf2(11.0f), this.timerPaint2);
        r21.save();
        r21.translate(r35 - AndroidUtilities.dpf2(11.0f), r29 - AndroidUtilities.dpf2(11.0f));
        this.timerDrawable.draw(r21);
        r21.restore();
        r21.restore();
        goto L93
    L77:
        BitmapShader r124 = r122.staticThumbShader;
        if (r124 == null) goto L89;
        this.timerPaint.setShader(r124);
        goto L89
    L80:
        this.timerPaint.setShader(null);
        if (this.avatarImage.getBitmap() == null) goto L86;
        if (this.avatarImage.getBitmap().isRecycled() == true) goto L86;
        this.timerPaint.setColor(AndroidUtilities.getDominantColor(this.avatarImage.getBitmap()));
    L86:
        if ((this.avatarImage.getDrawable() instanceof VectorAvatarThumbDrawable) == false) goto L88;
        this.timerPaint.setColor(((VectorAvatarThumbDrawable) this.avatarImage.getDrawable()).gradientTools.getAverageColor());
        goto L89
    L88:
        this.timerPaint.setColor(this.avatarDrawable.getColor2());
        goto L89
    L71:
        r33 = r12.originalAvatarRect.right - AndroidUtilities.dp(9.0f);
    L63:
        this.timerDrawable = TimerDrawable.getTtlIconForDialogs(this.ttlPeriod);
        goto L65
    L57:
        if (this.ttlProgress > 0.0f) goto L58;
    L93:
        TLRPC.User r210 = this.user;
        float r36 = 8.0f;
        if (r210 != null) goto L96;
    L121:
        TLRPC.Chat r211 = this.chat;
        if (r211 != null) goto L124;
    L120:
        boolean r18 = true;
        float r19 = 0.10666667f;
    L226:
        boolean r1 = this.showTtl;
        float r212 = this.ttlProgress;
        if (r1 == false) goto L233;
        if (r212 >= 1.0f) goto L235;
        this.ttlProgress = r212 + r19;
    L231:
        boolean r42 = r18;
    L236:
        this.ttlProgress = Utilities.clamp(this.ttlProgress, 1.0f, 0.0f);
        return r42;
    L235:
        r42 = r6;
        goto L236
    L233:
        if (r212 <= 0.0f) goto L235;
        this.ttlProgress = r212 - r19;
        goto L231
    L124:
        if (r211.call_active == true) goto L126;
    L128:
        boolean r213 = false;
    L129:
        this.hasCall = r213;
        if (r213 == true) goto L134;
        if (this.chatCallProgress == 0.0f) goto L120;
    L134:
        if (this.rightFragmentOpenedProgress >= 1.0f) goto L120;
        CheckBox2 r214 = this.checkBox;
        if (r214 != null) goto L138;
    L140:
        float r215 = 1.0f;
    L141:
        float r14 = this.storyParams.originalAvatarRect.bottom;
        if (this.useForceThreeLines == false) goto L144;
    L145:
        r36 = 6.0f;
    L146:
        int r37 = (int) (r14 - AndroidUtilities.dp(r36));
        boolean r142 = LocaleController.isRTL;
        StoriesUtilities.AvatarStoryParams r15 = this.storyParams;
        if (r142 == false) goto L156;
        float r143 = r15.originalAvatarRect.left;
        if (this.useForceThreeLines == false) goto L151;
    L154:
        float r144 = r143 + AndroidUtilities.dp(r10);
    L155:
        int r102 = (int) r144;
        if (this.rightFragmentOpenedProgress == 0.0f) goto L166;
        r21.save();
        float r125 = 1.0f - this.rightFragmentOpenedProgress;
        r21.scale(r125, r125, r102, r37);
    L166:
        Paint r126 = Theme.dialogs_onlineCirclePaint;
        int r145 = Theme.key_windowBackgroundWhite;
        r126.setColor(Theme.getColor(r145, this.resourcesProvider));
        float r127 = r102;
        float r38 = r37;
        r21.drawCircle(r127, r38, (AndroidUtilities.dp(11.0f) * this.chatCallProgress) * r215, Theme.dialogs_onlineCirclePaint);
        Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_chats_onlineCircle, this.resourcesProvider));
        r21.drawCircle(r127, r38, (AndroidUtilities.dp(9.0f) * this.chatCallProgress) * r215, Theme.dialogs_onlineCirclePaint);
        Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(r145, this.resourcesProvider));
        if (LiteMode.isEnabled(LiteMode.FLAGS_CHAT) == true) goto L169;
        this.innerProgress = 0.65f;
    L169:
        int r9 = this.progressStage;
        if (r9 != 0) goto L173;
        int r17 = 360928;
        float r92 = AndroidUtilities.dp(1.0f) + (AndroidUtilities.dp(4.0f) * this.innerProgress);
        float r73 = AndroidUtilities.dp(3.0f);
        float r11 = AndroidUtilities.dp(2.0f);
        r19 = 0.10666667f;
        float r133 = this.innerProgress;
    L172:
        float r74 = r73 - (r11 * r133);
    L194:
        if (this.chatCallProgress >= 1.0f) goto L196;
    L197:
        r21.save();
        float r112 = this.chatCallProgress;
        r21.scale(r112 * r215, r112 * r215, r127, r38);
    L198:
        r18 = true;
        this.rect.set(r102 - AndroidUtilities.dp(1.0f), r38 - r92, r102 + AndroidUtilities.dp(1.0f), r92 + r38);
        r21.drawRoundRect(this.rect, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), Theme.dialogs_onlineCirclePaint);
        float r113 = r38 - r74;
        float r39 = r38 + r74;
        this.rect.set(r102 - AndroidUtilities.dp(5.0f), r113, r102 - AndroidUtilities.dp(3.0f), r39);
        r21.drawRoundRect(this.rect, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), Theme.dialogs_onlineCirclePaint);
        this.rect.set(AndroidUtilities.dp(3.0f) + r102, r113, r102 + AndroidUtilities.dp(5.0f), r39);
        r21.drawRoundRect(this.rect, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), Theme.dialogs_onlineCirclePaint);
        if (this.chatCallProgress >= 1.0f) goto L201;
    L202:
        r21.restore();
    L204:
        if (LiteMode.isEnabled(r17) == false) goto L211;
        float r216 = this.innerProgress + 0.04f;
        this.innerProgress = r216;
        if (r216 < 1.0f) goto L210;
        this.innerProgress = 0.0f;
        int r217 = this.progressStage + 1;
        this.progressStage = r217;
        if (r217 < 8) goto L210;
        this.progressStage = 0;
    L210:
        r6 = true;
    L211:
        boolean r218 = this.hasCall;
        float r310 = this.chatCallProgress;
        if (r218 == false) goto L219;
        if (r310 >= 1.0f) goto L224;
        float r311 = r310 + r19;
        this.chatCallProgress = r311;
        if (r311 <= 1.0f) goto L224;
        this.chatCallProgress = 1.0f;
    L224:
        if (this.rightFragmentOpenedProgress == 0.0f) goto L226;
        r21.restore();
        goto L226
    L219:
        if (r310 <= 0.0f) goto L224;
        float r312 = r310 - r19;
        this.chatCallProgress = r312;
        if (r312 >= 0.0f) goto L224;
        this.chatCallProgress = 0.0f;
        goto L224
    L201:
        if (r215 >= 1.0f) goto L204;
    L196:
        if (r215 >= 1.0f) goto L198;
    L173:
        r17 = 360928;
        r19 = 0.10666667f;
        if (r9 != 1) goto L178;
        r92 = AndroidUtilities.dp(5.0f) - (AndroidUtilities.dp(4.0f) * this.innerProgress);
        float r75 = AndroidUtilities.dp(1.0f);
        float r114 = AndroidUtilities.dp(4.0f);
        float r134 = this.innerProgress;
    L176:
        r74 = r75 + (r114 * r134);
        goto L194
    L178:
        if (r9 != 2) goto L181;
        r92 = (AndroidUtilities.dp(2.0f) * this.innerProgress) + AndroidUtilities.dp(1.0f);
        r73 = AndroidUtilities.dp(5.0f);
        r11 = AndroidUtilities.dp(4.0f);
        r133 = this.innerProgress;
        goto L172
    L181:
        if (r9 != 3) goto L184;
        r92 = AndroidUtilities.dp(3.0f) - (AndroidUtilities.dp(2.0f) * this.innerProgress);
        r75 = AndroidUtilities.dp(1.0f);
        r114 = AndroidUtilities.dp(2.0f);
        r134 = this.innerProgress;
        goto L176
    L184:
        if (r9 != 4) goto L187;
        r92 = (AndroidUtilities.dp(4.0f) * this.innerProgress) + AndroidUtilities.dp(1.0f);
        r73 = AndroidUtilities.dp(3.0f);
        r11 = AndroidUtilities.dp(2.0f);
        r133 = this.innerProgress;
        goto L172
    L187:
        if (r9 != 5) goto L190;
        r92 = AndroidUtilities.dp(5.0f) - (AndroidUtilities.dp(4.0f) * this.innerProgress);
        r75 = AndroidUtilities.dp(1.0f);
        r114 = AndroidUtilities.dp(4.0f);
        r134 = this.innerProgress;
        goto L176
    L190:
        if (r9 != 6) goto L192;
        r92 = (AndroidUtilities.dp(4.0f) * this.innerProgress) + AndroidUtilities.dp(1.0f);
        r73 = AndroidUtilities.dp(5.0f);
        r11 = AndroidUtilities.dp(4.0f);
        r133 = this.innerProgress;
        goto L172
    L192:
        r92 = AndroidUtilities.dp(5.0f) - (AndroidUtilities.dp(4.0f) * this.innerProgress);
        r75 = AndroidUtilities.dp(1.0f);
        r114 = AndroidUtilities.dp(2.0f);
        r134 = this.innerProgress;
        goto L176
    L151:
        if (SharedConfig.useThreeLinesLayout == true) goto L154;
        r10 = 6.0f;
        goto L154
    L156:
        float r146 = r15.originalAvatarRect.right;
        if (this.useForceThreeLines == false) goto L159;
    L162:
        r144 = r146 - AndroidUtilities.dp(r10);
        goto L155
    L159:
        if (SharedConfig.useThreeLinesLayout == true) goto L162;
        r10 = 6.0f;
        goto L162
    L144:
        if (SharedConfig.useThreeLinesLayout == false) goto L146;
    L138:
        if (r214.isChecked() == false) goto L140;
        r215 = 1.0f - this.checkBox.getProgress();
        goto L141
    L126:
        if (r211.call_not_empty == false) goto L128;
        r213 = true;
        goto L129
    L96:
        if (MessagesController.isSupportUser(r210) == true) goto L121;
        if (this.user.bot == true) goto L121;
        boolean r219 = isOnline();
        this.wasDrawnOnline = r219;
        if (r219 == false) goto L102;
    L103:
        float r76 = ExteraConfig.getOnlineDotOuterRadius();
        float r93 = ExteraConfig.getOnlineDotInnerRadius();
        float r103 = ExteraConfig.getOnlineDotOffset(AndroidUtilities.dp(6.0f), r76);
        float r313 = ExteraConfig.getOnlineDotOffset(AndroidUtilities.dp(8.0f), r76);
        RectF r115 = this.storyParams.originalAvatarRect;
        float r128 = r115.bottom - r313;
        if (LocaleController.isRTL == false) goto L106;
        float r314 = r115.left + r103;
    L107:
        Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
        r21.drawCircle(r314, r128, r76 * this.onlineProgress, Theme.dialogs_onlineCirclePaint);
        Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_chats_onlineCircle, this.resourcesProvider));
        r21.drawCircle(r314, r128, r93 * this.onlineProgress, Theme.dialogs_onlineCirclePaint);
        float r16 = this.onlineProgress;
        if (r219 == false) goto L116;
        if (r16 >= 1.0f) goto L120;
        float r110 = r16 + 0.10666667f;
        this.onlineProgress = r110;
        if (r110 <= 1.0f) goto L114;
        this.onlineProgress = 1.0f;
    L114:
        r6 = true;
        goto L120
    L116:
        if (r16 <= 0.0f) goto L120;
        float r111 = r16 - 0.10666667f;
        this.onlineProgress = r111;
        if (r111 >= 0.0f) goto L114;
        this.onlineProgress = 0.0f;
        goto L114
    L106:
        r314 = r115.right - r103;
        goto L107
    L102:
        if (this.onlineProgress == 0.0f) goto L120;
    L46:
        if (isOnline() == true) goto L52;
        if (this.hasCall == true) goto L52;
        if (this.storyParams.drawnLive == true) goto L52;
        r24 = true;
    L27:
        r8 = false;
        goto L28
    L5:
        if ((r2.flags2 & 2048) == 0) goto L22;
        float r220 = this.avatarImage.getImageY2();
        float r77 = this.avatarImage.getImageX2();
        CheckBox2 r82 = this.checkBox;
        if (r82 != null) goto L9;
    L11:
        float r83 = 1.0f;
    L13:
        if (this.starBg != null) goto L15;
        this.starBg = getContext().getResources().getDrawable(R.drawable.star_small_outline).mutate();
    L15:
        int r94 = Theme.getColor(Theme.key_windowBackgroundWhite);
        if (this.starBgColor == r94) goto L19;
        Drawable r104 = this.starBg;
        this.starBgColor = r94;
        r104.setColorFilter(new PorterDuffColorFilter(r94, PorterDuff.Mode.SRC_IN));
    L19:
        if (this.starFg != null) goto L21;
        this.starFg = getContext().getResources().getDrawable(R.drawable.star_small_inner).mutate();
    L21:
        int r95 = AndroidUtilities.dp(19.33f);
        Rect r105 = AndroidUtilities.rectTmp2;
        int r78 = (int) r77;
        int r221 = (int) r220;
        int r135 = r221 - r95;
        r105.set((AndroidUtilities.dp(1.66f) + r78) - r95, r135, AndroidUtilities.dp(1.66f) + r78, r221);
        r105.inset(-AndroidUtilities.dp(1.0f), -AndroidUtilities.dp(1.0f));
        this.starBg.setBounds(r105);
        int r84 = (int) (r83 * 255.0f);
        this.starBg.setAlpha(r84);
        this.starBg.draw(r21);
        r105.set((AndroidUtilities.dp(1.66f) + r78) - r95, r135, r78 + AndroidUtilities.dp(1.66f), r221);
        this.starFg.setBounds(r105);
        this.starFg.setAlpha(r84);
        this.starFg.draw(r21);
        r22 = true;
        goto L23
    L9:
        if (r82.isChecked() == false) goto L11;
        r83 = 1.0f - this.checkBox.getProgress();
        goto L13
    }

    public boolean drawLock2() {
        return false;
    }

    public float getClipProgress() {
        return this.clipProgress;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public int getCurrentDialogFolderId() {
        return this.currentDialogFolderId;
    }

    public long getDialogId() {
        return this.currentDialogId;
    }

    public boolean getHasUnread() {
        if (this.unreadCount == 0) goto L5;
        return true;
    L5:
        if (this.markUnread == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean getIsMuted() {
        return this.dialogMuted;
    }

    public boolean getIsPinned() {
        if (this.drawPin == false) goto L5;
        return true;
    L5:
        if (this.drawPinForced == true) goto L11;
        return false;
    L11:
        return true;
    }

    public MessageObject getMessage() {
        return this.message;
    }

    public int getMessageId() {
        return this.messageId;
    }

    public String getMessageNameString() {
        MessageObject r0 = this.message;
        if (r0 != null) goto L5;
        return null;
    L5:
        long r2 = r0.getFromChatId();
        long r4 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        if (this.isSavedDialog == true) goto L28;
        if (this.currentDialogId != r4) goto L28;
        long r8 = this.message.getSavedDialogId();
        if (r8 != r4) goto L13;
        return null;
    L13:
        if (r8 == UserObject.ANONYMOUS) goto L28;
        TLRPC.Message r02 = this.message.messageOwner;
        if (r02 == null) goto L26;
        TLRPC.MessageFwdHeader r03 = r02.fwd_from;
        if (r03 == null) goto L26;
        long r22 = DialogObject.getPeerDialogId(r03.saved_from_id);
        if (r22 != 0) goto L22;
        r22 = DialogObject.getPeerDialogId(this.message.messageOwner.fwd_from.from_id);
    L22:
        if (r22 <= 0) goto L26;
        if (r22 == r8) goto L26;
        return null;
    L26:
        r2 = r8;
    L28:
        if (this.isSavedDialog == false) goto L36;
        TLRPC.Message r04 = this.message.messageOwner;
        if (r04 == null) goto L36;
        TLRPC.MessageFwdHeader r05 = r04.fwd_from;
        if (r05 == null) goto L36;
        r2 = DialogObject.getPeerDialogId(r05.saved_from_id);
        if (r2 != 0) goto L36;
        r2 = DialogObject.getPeerDialogId(this.message.messageOwner.fwd_from.from_id);
    L36:
        boolean r06 = DialogObject.isUserDialog(r2);
        int r6 = this.currentAccount;
        if (r06 == false) goto L39;
        TLRPC.User r07 = MessagesController.getInstance(r6).getUser(Long.valueOf(r2));
        TLRPC.Chat r23 = null;
    L40:
        long r62 = this.currentDialogId;
        if (r62 != r4) goto L50;
        if (r07 != null) goto L44;
        if (r23 != null) goto L47;
        return null;
    L47:
        return AndroidUtilities.escape(r23.title.replace("\n", _UrlKt.FRAGMENT_ENCODE_SET));
    L44:
        return AndroidUtilities.escape(UserObject.getFirstName(r07).replace("\n", _UrlKt.FRAGMENT_ENCODE_SET));
    L50:
        if (r62 != UserObject.VERIFY) goto L71;
        MessageObject r1 = this.message;
        if (r1 == null) goto L71;
        TLRPC.Message r12 = r1.messageOwner;
        if (r12 == null) goto L71;
        TLRPC.MessageFwdHeader r13 = r12.fwd_from;
        if (r13 == null) goto L71;
        String r08 = r13.from_name;
        if (r08 != null) goto L60;
        long r09 = DialogObject.getPeerDialogId(r13.from_id);
        boolean r24 = DialogObject.isUserDialog(r09);
        int r122 = this.currentAccount;
        if (r24 == true) goto L64;
        TLRPC.Chat r123 = MessagesController.getInstance(r122).getChat(Long.valueOf(-r09));
        if (r123 != null) goto L69;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L69:
        return r123.title;
    L64:
        return UserObject.getUserName(MessagesController.getInstance(r122).getUser(Long.valueOf(r09)));
    L60:
        return AndroidUtilities.escape(r08);
    L71:
        if (this.message.isOutOwner() == false) goto L76;
        if (r07 == null) goto L76;
        return LocaleController.getString(R.string.FromYou);
    L76:
        if (this.isSavedDialog == true) goto L87;
        MessageObject r14 = this.message;
        if (r14 == null) goto L87;
        TLRPC.Message r15 = r14.messageOwner;
        if (r15 == null) goto L87;
        if ((r15.from_id instanceof TLRPC.TL_peerUser) == false) goto L87;
        TLRPC.User r16 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.message.messageOwner.from_id.user_id));
        if (r16 == null) goto L87;
        return AndroidUtilities.escape(UserObject.getFirstName(r16).replace("\n", _UrlKt.FRAGMENT_ENCODE_SET));
    L87:
        MessageObject r17 = this.message;
        if (r17 == null) goto L97;
        TLRPC.Message r18 = r17.messageOwner;
        if (r18 == null) goto L97;
        TLRPC.MessageFwdHeader r19 = r18.fwd_from;
        if (r19 == null) goto L97;
        String r110 = r19.from_name;
        if (r110 == null) goto L97;
        return AndroidUtilities.escape(r110);
    L97:
        if (r07 != null) goto L99;
        if (r23 == null) goto L116;
        String r124 = r23.title;
        if (r124 != null) goto L115;
        return "DELETED";
    L115:
        return AndroidUtilities.escape(r124.replace("\n", _UrlKt.FRAGMENT_ENCODE_SET));
    L116:
        return "DELETED";
    L99:
        if (this.useForceThreeLines == true) goto L106;
        if (SharedConfig.useThreeLinesLayout == true) goto L106;
        return AndroidUtilities.escape(UserObject.getFirstName(r07).replace("\n", _UrlKt.FRAGMENT_ENCODE_SET));
    L106:
        if (UserObject.isDeleted(r07) == false) goto L110;
        return LocaleController.getString(R.string.HiddenName);
    L110:
        return AndroidUtilities.escape(ContactsController.formatName(r07.first_name, r07.last_name).replace("\n", _UrlKt.FRAGMENT_ENCODE_SET));
    L39:
        r23 = MessagesController.getInstance(r6).getChat(Long.valueOf(-r2));
        r07 = null;
        goto L40
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [android.view.View, org.telegram.ui.Cells.DialogCell] */
    /* JADX WARN: Type inference failed for: r13v5, types: [android.text.Spannable, android.text.SpannableString, java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r13v6, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r13v7, types: [java.lang.CharSequence] */
    public SpannableStringBuilder getMessageStringFormatted(int r12, String r13, CharSequence r14, boolean r15) {
        MessageObject r0 = getCaptionMessage();
        MessageObject r1 = this.message;
        Paint.FontMetricsInt r2 = null;
        if (r1 == null) goto L5;
        CharSequence r16 = r1.messageText;
    L6:
        this.applyName = true;
        if (TextUtils.isEmpty(r13) == false) goto L9;
        MessageObject r132 = this.message;
        TLRPC.Message r4 = r132.messageOwner;
        int r6 = 0;
        if ((r4 instanceof TLRPC.TL_messageService) == false) goto L32;
        CharSequence r02 = r132.messageTextShort;
        if (r02 != null) goto L15;
    L19:
        r02 = r132.messageText;
    L21:
        if (MessageObject.isTopicActionMessage(r132) == false) goto L27;
        SpannableStringBuilder r122 = formatInternal(r12, r02, r14);
        if ((this.message.topicIconDrawable[0] instanceof ForumBubbleDrawable) == false) goto L28;
        TLRPC.TL_forumTopic r133 = MessagesController.getInstance(this.currentAccount).getTopicsController().findTopic(-this.message.getDialogId(), MessageObject.getTopicId(this.currentAccount, this.message.messageOwner, true));
        if (r133 == null) goto L28;
        ((ForumBubbleDrawable) this.message.topicIconDrawable[0]).setColor(r133.icon_color);
    L28:
        if (r15 == false) goto L30;
        applyThumbs(r122);
    L30:
        return r122;
    L27:
        this.applyName = false;
        r122 = SpannableStringBuilder.valueOf(r02);
        goto L28
    L15:
        if ((r4.action instanceof TLRPC.TL_messageActionTopicCreate) == false) goto L21;
        if (this.isTopic == true) goto L19;
    L32:
        if (r0 == null) goto L82;
        CharSequence r10 = r0.caption;
        if (r10 == null) goto L82;
        CharSequence r134 = r10.toString();
        if (this.needEmoji == true) goto L39;
        String r17 = _UrlKt.FRAGMENT_ENCODE_SET;
    L52:
        if (r0.hasHighlightedWords() == false) goto L68;
        if (TextUtils.isEmpty(r0.messageOwner.message) == true) goto L68;
        CharSequence r123 = r0.messageTrimmedToHighlight;
        int r135 = getMeasuredWidth() - AndroidUtilities.dp(this.messagePaddingStart + 47);
        if (this.hasNameInMessage == true) goto L58;
    L61:
        if (r135 <= 0) goto L66;
        if (r0.messageTrimmedToHighlightCut == false) goto L66;
        r123 = AndroidUtilities.ellipsizeCenterEnd(r123, r0.highlightedWords.get(0), r135, this.currentMessagePaint, 130);
    L66:
        return new SpannableStringBuilder(r17).append(r123);
    L58:
        if (TextUtils.isEmpty(r14) == true) goto L60;
        r135 = (int) (r135 - this.currentMessagePaint.measureText(r14.toString()));
    L60:
        r135 = (int) (r135 - this.currentMessagePaint.measureText(": "));
    L68:
        if (r134.length() <= 150) goto L70;
        r134 = r134.subSequence(0, 150);
    L70:
        SpannableString r3 = new SpannableString(r134);
        r0.spoilLoginCode();
        MediaDataController.addTextStyleRuns(r0.messageOwner.entities, r134, r3, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED);
        TLRPC.Message r136 = r0.messageOwner;
        if (r136 == null) goto L77;
        ArrayList<TLRPC.MessageEntity> r137 = r136.entities;
        TextPaint r03 = this.currentMessagePaint;
        if (r03 == null) goto L76;
        r2 = r03.getFontMetricsInt();
    L76:
        MediaDataController.addAnimatedEmojiSpans(r137, r3, r2);
    L77:
        CharSequence r138 = new SpannableStringBuilder(r17).append(AndroidUtilities.replaceNewLines(r3));
        if (r15 == false) goto L81;
        r138 = applyThumbs(r138);
    L81:
        return formatInternal(r12, r138, r14);
    L39:
        if (r0.isVideo() == false) goto L42;
        r17 = "📹 ";
        goto L52
    L42:
        if (r0.isVoice() == false) goto L45;
        r17 = "🎤 ";
        goto L52
    L45:
        if (r0.isMusic() == false) goto L48;
        r17 = "🎧 ";
        goto L52
    L48:
        if (r0.isPhoto() == false) goto L50;
        r17 = "🖼 ";
        goto L52
    L50:
        r17 = "📎 ";
    L82:
        TL_iv.RichMessage r04 = r4.rich_message;
        if (r04 == null) goto L102;
        if (r04.blocks.size() != 1) goto L87;
        boolean r139 = MessageObject.isBlueBlock(this.message.messageOwner.rich_message.blocks.get(0));
    L88:
        SpannableStringBuilder r124 = formatInternal(r12, this.message.messageText, r14);
        if (r139 == true) goto L91;
    L100:
        return r124;
    L91:
        if (isForumCell() == true) goto L100;
        ForegroundColorSpanThemable r1310 = new ForegroundColorSpanThemable(Theme.key_chats_actionMessage, this.resourcesProvider);     // Catch: Exception -> L95
        if (this.hasNameInMessage == false) goto L97;
        r6 = r14.length() + 2;     // Catch: Exception -> L95
    L97:
        r124.setSpan(r1310, r6, r124.length(), 33);     // Catch: Exception -> L95
        return r124;
    L95:
        e = move-exception;
        FileLog.e(e);
        goto L100
    L87:
        r139 = false;
        goto L88
    L102:
        if (r4.media != null) goto L104;
    L172:
        MessageObject r1311 = this.message;
        CharSequence r05 = r1311.messageOwner.message;
        if (r05 == null) goto L208;
        if (r1311.hasHighlightedWords() == false) goto L188;
        CharSequence r1312 = this.message.messageTrimmedToHighlight;
        if (r1312 == null) goto L179;
        r05 = r1312;
    L179:
        int r1313 = getMeasuredWidth() - AndroidUtilities.dp(this.messagePaddingStart + 33);
        if (this.hasNameInMessage == true) goto L182;
    L185:
        if (r1313 <= 0) goto L191;
        r05 = AndroidUtilities.ellipsizeCenterEnd(r05, this.message.highlightedWords.get(0), r1313, this.currentMessagePaint, 130);
    L191:
        ?? r1314 = new SpannableString(r05);
        MessageObject r06 = this.message;
        if (r06 == null) goto L194;
        r06.spoilLoginCode();
    L194:
        MediaDataController.addTextStyleRuns(this.message, r1314, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ANOTHER_SELECTED);
        MessageObject r07 = this.message;
        if (r07 == null) goto L203;
        TLRPC.Message r08 = r07.messageOwner;
        if (r08 == null) goto L203;
        ArrayList<TLRPC.MessageEntity> r09 = r08.entities;
        TextPaint r18 = this.currentMessagePaint;
        if (r18 == null) goto L202;
        r2 = r18.getFontMetricsInt();
    L202:
        MediaDataController.addAnimatedEmojiSpans(r09, r1314, r2);
    L203:
        if (r15 == false) goto L206;
        r1314 = applyThumbs(r1314);
    L206:
        return formatInternal(r12, r1314, r14);
    L182:
        if (TextUtils.isEmpty(r14) == true) goto L184;
        r1313 = (int) (r1313 - this.currentMessagePaint.measureText(r14.toString()));
    L184:
        r1313 = (int) (r1313 - this.currentMessagePaint.measureText(": "));
        goto L185
    L188:
        if (r05.length() <= 150) goto L190;
        r05 = r05.subSequence(0, 150);
    L190:
        r05 = AndroidUtilities.replaceNewLines(r05);
        goto L191
    L208:
        return new SpannableStringBuilder();
    L104:
        if (r132.isMediaEmpty() == true) goto L172;
        this.currentMessagePaint = Theme.dialogs_messagePrintingPaint[this.paintIndex];
        int r1315 = Theme.key_chats_attachMessage;
        MessageObject r010 = this.message;
        TLRPC.MessageMedia r22 = r010.messageOwner.media;
        if ((r22 instanceof TLRPC.TL_messageMediaPoll) == false) goto L114;
        TLRPC.TL_messageMediaPoll r23 = (TLRPC.TL_messageMediaPoll) r22;
        TLRPC.TL_textWithEntities r011 = r23.poll.question;
        if (r011 != null) goto L110;
    L112:
        CharSequence r012 = DialogMediaIconsHelper.addDialogMediaSpan(r011.text, R.drawable.dialog_media_poll_20, true);
    L157:
        if ((r012 instanceof String) == false) goto L159;
        r012 = ((String) r012).replace('\n', ' ');
    L159:
        if (r15 == false) goto L161;
        r012 = applyThumbs(r012);
    L161:
        SpannableStringBuilder r125 = formatInternal(r12, r012, r14);
        if (isForumCell() == false) goto L209;
    L171:
        return r125;
    L209:
        ForegroundColorSpanThemable r152 = new ForegroundColorSpanThemable(r1315, this.resourcesProvider);     // Catch: Exception -> L166
        if (this.hasNameInMessage == false) goto L168;
        r6 = r14.length() + 2;     // Catch: Exception -> L166
    L168:
        r125.setSpan(r152, r6, r125.length(), 33);     // Catch: Exception -> L166
    L166:
        e = move-exception;
        FileLog.e(e);
        goto L171
    L110:
        if (r011.entities == null) goto L112;
        SpannableString r013 = new SpannableString(r23.poll.question.text.replace('\n', ' '));
        TLRPC.TL_textWithEntities r19 = r23.poll.question;
        MediaDataController.addTextStyleRuns(r19.entities, r19.text, r013);
        MediaDataController.addAnimatedEmojiSpans(r23.poll.question.entities, r013, Theme.dialogs_messagePaint[this.paintIndex].getFontMetricsInt());
        r012 = DialogMediaIconsHelper.addDialogMediaSpan(r013, R.drawable.dialog_media_poll_20, true);
        goto L157
    L114:
        if ((r22 instanceof TLRPC.TL_messageMediaToDo) == false) goto L122;
        TLRPC.TL_messageMediaToDo r24 = (TLRPC.TL_messageMediaToDo) r22;
        TLRPC.TL_textWithEntities r014 = r24.todo.title;
        if (r014 != null) goto L118;
    L120:
        r012 = DialogMediaIconsHelper.addDialogMediaSpan(r014.text, R.drawable.dialog_media_checklist_20, true);
        goto L157
    L118:
        if (r014.entities == null) goto L120;
        SpannableString r015 = new SpannableString(r24.todo.title.text.replace('\n', ' '));
        TLRPC.TL_textWithEntities r110 = r24.todo.title;
        MediaDataController.addTextStyleRuns(r110.entities, r110.text, r015);
        MediaDataController.addAnimatedEmojiSpans(r24.todo.title.entities, r015, Theme.dialogs_messagePaint[this.paintIndex].getFontMetricsInt());
        r012 = DialogMediaIconsHelper.addDialogMediaSpan(r015, R.drawable.dialog_media_checklist_20, true);
        goto L157
    L122:
        if ((r22 instanceof TLRPC.TL_messageMediaGame) == false) goto L125;
        r012 = DialogMediaIconsHelper.addDialogMediaSpan(r22.game.title, R.drawable.dialog_media_game_20, true);
        goto L157
    L125:
        if ((r22 instanceof TLRPC.TL_messageMediaInvoice) == false) goto L128;
        r012 = r22.title;
        goto L157
    L128:
        if (r010.type != 14) goto L131;
        r012 = String.format("🎧 \u2068%s - %s\u2069", new Object[]{r010.getMusicAuthor(), this.message.getMusicTitle()});
        goto L157
    L131:
        if ((r22 instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L142;
        int r1316 = ((TLRPC.TL_messageMediaPaidMedia) r22).extended_media.size();
        if (this.hasVideoThumb == false) goto L137;
        if (r1316 <= 1) goto L136;
        String r1317 = LocaleController.formatPluralString("Media", r1316, new Object[0]);
    L140:
        r012 = StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.AttachPaidMedia, new Object[]{r1317}));
        r1315 = Theme.key_chats_actionMessage;
        goto L157
    L136:
        r1317 = LocaleController.getString(R.string.AttachVideo);
        goto L140
    L137:
        if (r1316 <= 1) goto L139;
        r1317 = LocaleController.formatPluralString("Photos", r1316, new Object[0]);
        goto L140
    L139:
        r1317 = LocaleController.getString(R.string.AttachPhoto);
        goto L140
    L142:
        if (this.thumbsCount <= 1) goto L155;
        boolean r1318 = this.hasVideoThumb;
        ArrayList<MessageObject> r111 = this.groupMessages;
        if (r1318 == false) goto L150;
        if (r111 != null) goto L147;
        int r1319 = 0;
    L148:
        String r1320 = LocaleController.formatPluralString("Media", r1319, new Object[0]);
    L149:
        r012 = r1320;
        r1315 = Theme.key_chats_actionMessage;
        goto L157
    L147:
        r1319 = r111.size();
        goto L148
    L150:
        if (r111 != null) goto L152;
        int r1321 = 0;
    L153:
        r1320 = LocaleController.formatPluralString("Photos", r1321, new Object[0]);
        goto L149
    L152:
        r1321 = r111.size();
        goto L153
    L155:
        r012 = r16.toString();
        r1315 = Theme.key_chats_actionMessage;
        goto L157
    L9:
        return formatInternal(r12, r13, r14);
    L5:
        r16 = null;
        goto L6
    }

    public long getStarsPrice() {
        return this.starsPriceBlocked;
    }

    @Override // android.view.View
    public float getTranslationX() {
        return this.translationX;
    }

    public boolean hasTags() {
        DialogCellTags r0 = this.tags;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isEmpty() == true) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.Cells.BaseCell, android.view.View
    public void invalidate() {
        if (StoryViewer.animationInProgress == false) goto L5;
        return;
    L5:
        super.invalidate();
    }

    @Override // android.view.View
    public void invalidate(int r2, int r3, int r4, int r5) {
        if (StoryViewer.animationInProgress == false) goto L5;
        return;
    L5:
        super.invalidate(r2, r3, r4, r5);
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r2) {
        if (r2 != this.translationDrawable) goto L5;
    L9:
        invalidate(r2.getBounds());
        return;
    L5:
        if (r2 == Theme.dialogs_archiveAvatarDrawable) goto L9;
        super.invalidateDrawable(r2);
    }

    public boolean isBlocked() {
        return this.premiumBlocked;
    }

    public boolean isDialogCommunity() {
        return ChatObject.isCommunity(this.chat);
    }

    public boolean isDialogFolder() {
        if (this.currentDialogFolderId <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isFolderCell() {
        if (this.currentDialogFolderId == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isForumCell() {
        if (isDialogFolder() == false) goto L5;
        return false;
    L5:
        if (this.insideCommunityListNoDialog == true) goto L20;
        TLRPC.Chat r0 = this.chat;
        if (r0 != null) goto L9;
        return false;
    L9:
        if (r0.forum == true) goto L15;
        if (ChatObject.isMonoForum(r0) == true) goto L13;
        return false;
    L13:
        if (ChatObject.canManageMonoForum(this.currentAccount, this.chat) == true) goto L15;
        return false;
    L15:
        if (this.isTopic == true) goto L24;
        return true;
    L24:
        return false;
    L20:
        return false;
    }

    public boolean isMoving() {
        return this.moving;
    }

    public boolean isPointInsideAvatar(float r4, float r5) {
        if (LocaleController.isRTL == true) goto L11;
        if (r4 >= 0.0f) goto L7;
    L9:
        return false;
    L7:
        if (r4 >= AndroidUtilities.dp(60.0f)) goto L9;
        return true;
    L11:
        if (r4 >= (getMeasuredWidth() - AndroidUtilities.dp(60.0f))) goto L13;
    L15:
        return false;
    L13:
        if (r4 >= getMeasuredWidth()) goto L15;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.avatarImage.onAttachedToWindow();
        int r1 = 0;
    L3:
        ImageReceiver[] r2 = this.thumbImage;
        if (r1 >= r2.length) goto L6;
        r2[r1].onAttachedToWindow();
        r1 = r1 + 1;
        goto L3
    L6:
        resetPinnedArchiveState();
        this.animatedEmojiStack = AnimatedEmojiSpan.update(0, this, this.animatedEmojiStack, new Layout[]{this.messageLayout});
        this.animatedEmojiStack2 = AnimatedEmojiSpan.update(0, this, this.animatedEmojiStack2, new Layout[]{this.messageNameLayout});
        this.animatedEmojiStack3 = AnimatedEmojiSpan.update(0, this, this.animatedEmojiStack3, new Layout[]{this.buttonLayout});
        this.animatedEmojiStackName = AnimatedEmojiSpan.update(0, this, this.animatedEmojiStackName, new Layout[]{this.nameLayout});
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.emojiStatus;
        if (r0 == null) goto L9;
        r0.attach();
    L9:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r5 = this.botVerification;
        if (r5 == null) goto L14;
        r5.attach();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isSliding = false;
        this.drawRevealBackground = false;
        this.currentRevealProgress = 0.0f;
        this.attachedToWindow = false;
        if (getIsPinned() == true) goto L5;
    L7:
        float r2 = 0.0f;
    L8:
        this.reorderIconProgress = r2;
        this.avatarImage.onDetachedFromWindow();
        int r22 = 0;
    L9:
        ImageReceiver[] r3 = this.thumbImage;
        if (r22 >= r3.length) goto L12;
        r3[r22].onDetachedFromWindow();
        r22 = r22 + 1;
        goto L9
    L12:
        RLottieDrawable r23 = this.translationDrawable;
        if (r23 == null) goto L15;
        r23.stop();
        this.translationDrawable.setProgress(0.0f);
        this.translationDrawable.setCallback(null);
        this.translationDrawable = null;
        this.translationAnimationStarted = false;
    L15:
        DialogsAdapter.DialogsPreloader r0 = this.preloader;
        if (r0 == null) goto L18;
        r0.remove(this.currentDialogId);
    L18:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r02 = this.emojiStatus;
        if (r02 == null) goto L21;
        r02.detach();
    L21:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r03 = this.botVerification;
        if (r03 == null) goto L24;
        r03.detach();
    L24:
        AnimatedEmojiSpan.release(this, this.animatedEmojiStack);
        AnimatedEmojiSpan.release(this, this.animatedEmojiStack2);
        AnimatedEmojiSpan.release(this, this.animatedEmojiStack3);
        AnimatedEmojiSpan.release(this, this.animatedEmojiStackName);
        this.storyParams.onDetachFromWindow();
        this.canvasButton = null;
        return;
    L5:
        if (this.drawReorder == false) goto L7;
        r2 = 1.0f;
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v2 */
    /* JADX WARN: Type inference failed for: r5v43 */
    @Override // android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas r51) {
        Canvas r2 = r51;
        if (this.currentDialogId != 0) goto L8;
        if (this.customDialog != null) goto L8;
        return;
    L8:
        if (this.visibleOnScreen == false) goto L1143;
        boolean r0 = this.drawArchive;
        if (r0 == false) goto L29;
        if (this.currentDialogFolderId == 0) goto L15;
    L20:
        PullForegroundDrawable r3 = this.archivedChatsDrawable;
        if (r3 == null) goto L29;
        if (r3.outProgress != 0.0f) goto L29;
        if (this.translationX != 0.0f) goto L29;
        r2.save();
        r2.translate(0.0f, (-this.translateY) - this.rightFragmentOffset);
        r2.clipRect(0.0f, getMeasuredHeight() * (1.0f - this.archivedChatsDrawable.getPullProgress()), getMeasuredWidth(), getMeasuredHeight());
        this.archivedChatsDrawable.draw(r2);
        r2.restore();
        this.emojiStatusView.setVisibility(4);
        this.botVerificationView.setVisibility(4);
        return;
    L15:
        if (this.isTopic == false) goto L29;
        TLRPC.TL_forumTopic r32 = this.forumTopic;
        if (r32 == null) goto L29;
        if (r32.id == 1) goto L20;
    L29:
        if (r0 == true) goto L31;
    L51:
        ?? r16 = false;
    L52:
        PullForegroundDrawable r02 = this.archivedChatsDrawable;
        if (r02 == null) goto L55;
        float r17 = r02.getPullProgress();
    L56:
        if (r16 == false) goto L59;
        r2.save();
        r2.clipRect(0.0f, getMeasuredHeight() * (1.0f - r17), getMeasuredWidth(), getMeasuredHeight());
    L59:
        if (this.clipProgress == 0.0f) goto L64;
        if (Build.VERSION.SDK_INT == 24) goto L64;
        r2.save();
        r2.clipRect(0.0f, this.topClip * this.clipProgress, getMeasuredWidth(), getMeasuredHeight() - ((int) (this.bottomClip * this.clipProgress)));
    L64:
        if (this.translationX == 0.0f) goto L66;
    L72:
        r2.save();
        r2.translate(0.0f, -this.translateY);
        if (this.overrideSwipeAction == false) goto L77;
        int r03 = Theme.getColor(this.overrideSwipeActionBackgroundColorKey, this.resourcesProvider);
        int r6 = Theme.getColor(this.overrideSwipeActionRevealBackgroundColorKey, this.resourcesProvider);
        String r7 = this.overrideSwipeActionStringKey;
        int r4 = this.overrideSwipeActionStringId;
        String r72 = LocaleController.getString(r7, r4);
        this.translationDrawable = this.overrideSwipeActionDrawable;
    L75:
        String r24 = r72;
        if (this.swipeCanceled == false) goto L121;
        RLottieDrawable r5 = this.lastDrawTranslationDrawable;
        if (r5 == null) goto L121;
        this.translationDrawable = r5;
        r4 = this.lastDrawSwipeMessageStringId;
    L123:
        if (this.translationAnimationStarted == false) goto L125;
    L127:
        float r52 = getMeasuredWidth() + this.translationX;
        long r33 = 0;
        if (this.currentRevealProgress >= 1.0f) goto L137;
        Theme.dialogs_pinnedPaint.setColor(r03);
        float r21 = 1.0f;
        float r37 = 0.0f;
        int r36 = 0;
        boolean r22 = true;
        float r11 = r52;
        int r15 = r4;
        int r10 = 2;
        int r9 = r6;
        String r04 = r24;
        r2.drawRect(r52 - AndroidUtilities.dp(8.0f), 0.0f, getMeasuredWidth(), getMeasuredHeight(), Theme.dialogs_pinnedPaint);
        if (this.currentRevealProgress == 0.0f) goto L132;
    L138:
        int r34 = (getMeasuredWidth() - AndroidUtilities.dp(43.0f)) - (this.translationDrawable.getIntrinsicWidth() / r10);
        int r42 = (getMeasuredHeight() - AndroidUtilities.dp(52.0f)) / r10;
        int r53 = (this.translationDrawable.getIntrinsicWidth() / r10) + r34;
        int r62 = (this.translationDrawable.getIntrinsicHeight() / r10) + r42;
        if (this.currentRevealProgress <= r37) goto L146;
        r2.save();
        int r38 = r10;
        int r26 = r15;
        float r25 = r11;
        r2.clipRect(r11 - AndroidUtilities.dp(8.0f), r37, getMeasuredWidth(), getMeasuredHeight());
        Theme.dialogs_pinnedPaint.setColor(r9);
        r2.drawCircle(r53, r62, ((float) Math.sqrt((r53 * r53) + ((r62 - getMeasuredHeight()) * (r62 - getMeasuredHeight())))) * AndroidUtilities.accelerateInterpolator.getInterpolation(this.currentRevealProgress), Theme.dialogs_pinnedPaint);
        r2.restore();
        if (Theme.dialogs_archiveDrawableRecolored == true) goto L144;
        Theme.dialogs_archiveDrawable.setLayerColor("Arrow", Theme.getNonAnimatedColor(Theme.key_chats_archivePinBackground));
        Theme.dialogs_archiveDrawableRecolored = r22;
    L144:
        if (Theme.dialogs_hidePsaDrawableRecolored == true) goto L147;
        Theme.dialogs_hidePsaDrawable.beginApplyLayerColors();
        RLottieDrawable r54 = Theme.dialogs_hidePsaDrawable;
        int r63 = Theme.key_chats_archivePinBackground;
        r54.setLayerColor("Line 1", Theme.getNonAnimatedColor(r63));
        Theme.dialogs_hidePsaDrawable.setLayerColor("Line 2", Theme.getNonAnimatedColor(r63));
        Theme.dialogs_hidePsaDrawable.setLayerColor("Line 3", Theme.getNonAnimatedColor(r63));
        Theme.dialogs_hidePsaDrawable.commitApplyLayerColors();
        Theme.dialogs_hidePsaDrawableRecolored = r22;
    L147:
        r2.save();
        r2.translate(r34, r42);
        float r35 = this.currentRevealBounceProgress;
        if (r35 != 0.0f) goto L150;
    L152:
        int r55 = r36;
        BaseCell.setDrawableBounds(this.translationDrawable, r55, r55);
        this.translationDrawable.draw(r2);
        r2.restore();
        r2.clipRect(r25, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        int r39 = (int) Math.ceil(Theme.dialogs_countTextPaint2.measureText(r04));
        int r152 = r26;
        if (this.swipeMessageTextId == r152) goto L155;
    L156:
        this.swipeMessageTextId = r152;
        this.swipeMessageWidth = getMeasuredWidth();
        com.exteragram.messenger.utils.ui.TextPaint r252 = Theme.dialogs_archiveTextPaint;
        int r262 = Math.min(AndroidUtilities.dp(80.0f), r39);
        Layout.Alignment r27 = Layout.Alignment.ALIGN_CENTER;
        String r242 = r04;
        StaticLayout r23 = new StaticLayout(r242, r252, r262, r27, 1.0f, 0.0f, false);
        this.swipeMessageTextLayout = r23;
        if (r23.getLineCount() <= r22) goto L160;
        this.swipeMessageTextLayout = new StaticLayout(r242, Theme.dialogs_archiveTextPaintSmall, Math.min(AndroidUtilities.dp(82.0f), r39), r27, 1.0f, 0.0f, false);
    L160:
        if (this.swipeMessageTextLayout == null) goto L166;
        r2.save();
        if (this.swipeMessageTextLayout.getLineCount() <= 1) goto L164;
        float r05 = -AndroidUtilities.dp(4.0f);
    L165:
        r2.translate((getMeasuredWidth() - AndroidUtilities.dp(43.0f)) - (this.swipeMessageTextLayout.getWidth() / 2.0f), (r42 + AndroidUtilities.dp(36.0f)) + r05);
        this.swipeMessageTextLayout.draw(r2);
        r2.restore();
        goto L166
    L164:
        r05 = 0.0f;
    L166:
        r2.restore();
    L168:
        if (this.translationX == 0.0f) goto L170;
        r2.save();
        r2.translate(this.translationX, 0.0f);
        float r06 = this.translationX + 0.0f;
    L171:
        float r92 = AndroidUtilities.dp(8.0f) * this.cornerProgress;
        if (this.isSelected == false) goto L174;
        float r64 = 0.0f;
        this.rect.set(0.0f, 0.0f, getMeasuredWidth(), AndroidUtilities.lerp(getMeasuredHeight(), getCollapsedHeight(), this.rightFragmentOpenedProgress));
        this.rect.offset(0.0f, (-this.translateY) + this.collapseOffset);
        r2.drawRoundRect(this.rect, r92, r92, Theme.dialogs_tabletSeletedPaint);
    L175:
        r2.save();
        r2.translate(r64, (-this.rightFragmentOffset) * this.rightFragmentOpenedProgress);
        if (this.currentDialogFolderId == 0) goto L183;
        if (SharedConfig.archiveHidden == true) goto L180;
    L181:
        float r73 = r21;
        Theme.dialogs_pinnedPaint.setColor(AndroidUtilities.getOffsetColor(0, Theme.getColor(Theme.key_chats_pinnedOverlay, this.resourcesProvider), this.archiveBackgroundProgress, r73));
        Theme.dialogs_pinnedPaint.setAlpha((int) (r3.getAlpha() * (r73 - this.rightFragmentOpenedProgress)));
    L187:
        r2.restore();
        this.updateHelper.updateAnimationValues();
        if (this.collapseOffset == 0.0f) goto L190;
        r2.save();
        r2.translate(0.0f, this.collapseOffset);
        float r12 = this.collapseOffset + 0.0f;
    L191:
        float r310 = this.rightFragmentOpenedProgress;
        if (r310 != 1.0f) goto L194;
        DialogCell r8 = this;
        int r93 = 17;
        int r153 = r38;
        float r253 = 6.0f;
        float r322 = 5.0f;
        int r352 = 4;
        float r40 = 19.0f;
        boolean r07 = false;
        boolean r112 = false;
        boolean r212 = false;
    L902:
        if (r8.animatingArchiveAvatar == false) goto L905;
        r2.save();
        float r1 = r8.interpolator.getInterpolation(r8.animatingArchiveAvatarProgress / 170.0f) + 1.0f;
        r2.scale(r1, r1, r8.avatarImage.getCenterX(), r8.avatarImage.getCenterY());
    L905:
        if (r8.drawAvatar == false) goto L960;
        if (r8.isTopic == false) goto L917;
        TLRPC.TL_forumTopic r13 = r8.forumTopic;
        if (r13 == null) goto L917;
        if (r13.id != 1) goto L917;
        PullForegroundDrawable r14 = r8.archivedChatsDrawable;
        if (r14 == null) goto L917;
        if (r14.isDraw() == true) goto L960;
    L917:
        if (r8.drawMonoforumAvatar == false) goto L923;
        if (r8.bubbleClip != null) goto L921;
        r8.bubbleClip = new PhotoBubbleClip();
    L921:
        r8.bubbleClip.setBounds((int) r8.storyParams.originalAvatarRect.centerX(), (int) r8.storyParams.originalAvatarRect.centerY(), (int) (r8.storyParams.originalAvatarRect.width() / 2.0f));
        r2.save();
        r2.clipPath(r8.bubbleClip);
        r8.avatarImage.setImageCoords(r8.storyParams.originalAvatarRect);
        r8.avatarImage.draw(r2);
        r2.restore();
    L940:
        if (r8.insideCommunityList == true) goto L960;
        TLRPC.Chat r18 = r8.chat;
        if (r18 != null) goto L944;
    L945:
        TLRPC.User r19 = r8.user;
        if (r19 == null) goto L960;
        if (r19.linked_community_id == r33) goto L960;
    L950:
        if (r8.drawCommunityAvatar == true) goto L960;
        if (r8.isDialogCell == false) goto L960;
        if (r8.isDialogFolder() == true) goto L960;
        float r110 = r8.storyParams.originalAvatarRect.centerX() + AndroidUtilities.dp(20.33f);
        float r311 = r8.storyParams.originalAvatarRect.centerY() + AndroidUtilities.dp(r40);
        if (r8.communityArrowDrawable != null) goto L958;
        r8.communityArrowDrawable = new CommunityArrowDrawable();
    L958:
        DrawableUtils.setBounds(r8.communityArrowDrawable, r110, r311, r93);
        r2.drawCircle(r8.communityArrowDrawable.getBounds().exactCenterX(), r8.communityArrowDrawable.getBounds().exactCenterY(), AndroidUtilities.dp(8.0f), Theme.fillingPaint(Theme.getColor(Theme.key_windowBackgroundWhite, r8.resourcesProvider)));
        r8.communityArrowDrawable.draw(r2);
        goto L960
    L944:
        if (r18.linked_community_id != r33) goto L950;
    L923:
        if (r8.drawCommunityAvatar == false) goto L925;
        DrawableUtils.setBounds(r8.avatarImage, r8.storyParams.originalAvatarRect.centerX() + AndroidUtilities.dpf2(1.0f), r8.storyParams.originalAvatarRect.centerY(), AndroidUtilities.dp(48.0f), AndroidUtilities.dp(48.0f), 17);
        DrawableUtils.drawCommunityCardDrawable(r2, Theme.dialogs_communityCardsDrawable, r8.avatarImage.getCenterX(), r8.avatarImage.getCenterY(), AndroidUtilities.dp(48.0f));
        r8.avatarImage.draw(r2);
        goto L940
    L925:
        StoriesUtilities.AvatarStoryParams r111 = r8.storyParams;
        boolean r312 = r8.isShareToStoryCell;
        if (r312 == false) goto L928;
    L931:
        boolean r43 = true;
    L932:
        r111.drawHiddenStoriesAsSegments = r43;
        int r44 = r111.forceState;
        if (r312 == false) goto L935;
        r111.forceState = 1;
    L935:
        StoriesUtilities.drawAvatarWithStory(r8.currentDialogId, r2, r8.avatarImage, r111);
        if (r8.storyParams.drawnLive == false) goto L938;
        r8.checkTtl();
    L938:
        r8.storyParams.forceState = r44;
        goto L940
    L928:
        if (r8.currentDialogFolderId != 0) goto L931;
        r43 = false;
    L960:
        if (r8.animatingArchiveAvatar == false) goto L963;
        r2.restore();
    L963:
        if (r8.avatarImage.getVisible() == false) goto L968;
        if (drawAvatarOverlays(r51) == false) goto L968;
        r212 = true;
    L968:
        if (r8.rightFragmentOpenedProgress > 0.0f) goto L970;
    L972:
        DialogCell r74 = r8;
    L974:
        if (r74.collapseOffset == 0.0f) goto L977;
        r2.restore();
    L977:
        if (r74.translationX == 0.0f) goto L980;
        r2.restore();
    L980:
        if (r74.drawArchive == false) goto L995;
        if (r74.currentDialogFolderId != 0) goto L990;
        if (r74.isTopic == false) goto L995;
        TLRPC.TL_forumTopic r113 = r74.forumTopic;
        if (r113 == null) goto L995;
        if (r113.id != 1) goto L995;
    L990:
        if (r74.translationX != 0.0f) goto L995;
        if (r74.archivedChatsDrawable == null) goto L995;
        r2.save();
        r2.translate(0.0f, (-r74.translateY) - (r74.rightFragmentOffset * r74.rightFragmentOpenedProgress));
        r2.clipRect(0.0f, r74.getMeasuredHeight() * (1.0f - r74.archivedChatsDrawable.getPullProgress()), r74.getMeasuredWidth(), r74.getMeasuredHeight());
        r74.archivedChatsDrawable.draw(r2);
        r2.restore();
    L995:
        if (r74.useSeparator == true) goto L997;
    L1023:
        boolean r222 = true;
        float r422 = 0.0f;
    L1025:
        if (r74.clipProgress != r422) goto L1027;
    L1029:
        Canvas r28 = r51;
    L1031:
        if (r16 == false) goto L1039;
        float r94 = 1.0f - r17;
        int r114 = (int) (r74.getMeasuredHeight() * r94);
        int r313 = Theme.getColor(Theme.key_windowBackgroundWhite);
        if (r74.archiveFadeGradientDrawable != null) goto L1036;
        r74.archiveFadeGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, null);
    L1036:
        if (r74.archiveFadeGradientDrawableColor == r313) goto L1038;
        r74.archiveFadeGradientDrawableColor = r313;
        r74.archiveFadeGradientDrawable.setColors(new int[]{r313, 16777215 & r313});
    L1038:
        float r45 = MathUtils.clamp((r94 - 0.05f) * 10.0f, 0.0f, 1.0f);
        r74.archiveFadeGradientDrawable.setBounds(0, r114, r74.getMeasuredWidth(), AndroidUtilities.dp(r253) + r114);
        r74.archiveFadeGradientDrawable.setAlpha((int) (r45 * 255.0f));
        r74.archiveFadeGradientDrawable.draw(r28);
        r28.restore();
    L1039:
        boolean r115 = r74.drawReorder;
        if (r115 == false) goto L1042;
    L1045:
        float r314 = r74.reorderIconProgress;
        if (r115 == true) goto L1048;
        float r65 = 0.0f;
        if (r314 <= 0.0f) goto L1059;
        float r315 = r314 - 0.09411765f;
        r74.reorderIconProgress = r315;
        if (r315 >= 0.0f) goto L1058;
        r74.reorderIconProgress = 0.0f;
    L1058:
        boolean r56 = r222;
    L1060:
        boolean r116 = r74.archiveHidden;
        float r316 = r74.archiveBackgroundProgress;
        if (r116 == false) goto L1072;
        if (r316 <= r65) goto L1080;
        float r317 = r316 - 0.069565214f;
        r74.archiveBackgroundProgress = r317;
        if (r317 >= r65) goto L1068;
        r74.archiveBackgroundProgress = r65;
    L1068:
        if (r74.avatarDrawable.getAvatarType() != r153) goto L1070;
        r74.avatarDrawable.setArchivedAvatarHiddenProgress(CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(r74.archiveBackgroundProgress));
    L1070:
        r56 = r222;
    L1080:
        if (r74.animatingArchiveAvatar == false) goto L1086;
        float r117 = r74.animatingArchiveAvatarProgress + 16.0f;
        r74.animatingArchiveAvatarProgress = r117;
        if (r117 < 170.0f) goto L1084;
        r74.animatingArchiveAvatarProgress = 170.0f;
        int r66 = 0;
        r74.animatingArchiveAvatar = false;
    L1085:
        r56 = r222;
    L1087:
        boolean r118 = r74.drawRevealBackground;
        float r318 = r74.currentRevealBounceProgress;
        if (r118 == false) goto L1102;
        if (r318 >= 1.0f) goto L1094;
        float r319 = r318 + 0.09411765f;
        r74.currentRevealBounceProgress = r319;
        if (r319 <= 1.0f) goto L1094;
        r74.currentRevealBounceProgress = 1.0f;
        r56 = r222;
    L1094:
        float r119 = r74.currentRevealProgress;
        if (r119 >= 1.0f) goto L1100;
        float r120 = r119 + 0.053333335f;
        r74.currentRevealProgress = r120;
        if (r120 <= 1.0f) goto L1099;
        r74.currentRevealProgress = 1.0f;
    L1099:
        boolean r154 = r222;
    L1109:
        View r121 = r74.emojiStatusView;
        if (r112 == false) goto L1112;
        int r1110 = r66;
    L1113:
        r121.setVisibility(r1110);
        View r122 = r74.botVerificationView;
        if (r07 == false) goto L1116;
        int r142 = r66;
    L1117:
        r122.setVisibility(r142);
        if (r154 == false) goto L1141;
        r74.invalidate();
        return;
    L1141:
        return;
    L1116:
        r142 = r352;
        goto L1117
    L1112:
        r1110 = r352;
    L1100:
        r154 = r56;
        goto L1109
    L1102:
        if (r318 != 1.0f) goto L1104;
        r74.currentRevealBounceProgress = 0.0f;
        r56 = r222;
    L1104:
        float r123 = r74.currentRevealProgress;
        if (r123 <= 0.0f) goto L1100;
        float r124 = r123 - 0.053333335f;
        r74.currentRevealProgress = r124;
        if (r124 >= 0.0f) goto L1099;
        r74.currentRevealProgress = 0.0f;
        goto L1099
    L1084:
        r66 = 0;
        goto L1085
    L1086:
        r66 = 0;
        goto L1087
    L1072:
        if (r316 >= 1.0f) goto L1080;
        float r320 = r316 + 0.069565214f;
        r74.archiveBackgroundProgress = r320;
        if (r320 <= 1.0f) goto L1077;
        r74.archiveBackgroundProgress = 1.0f;
    L1077:
        if (r74.avatarDrawable.getAvatarType() != r153) goto L1070;
        r74.avatarDrawable.setArchivedAvatarHiddenProgress(CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(r74.archiveBackgroundProgress));
    L1059:
        r56 = r212;
        goto L1060
    L1048:
        if (r314 < 1.0f) goto L1049;
    L1044:
        r65 = 0.0f;
        goto L1059
    L1049:
        float r321 = r314 + 0.09411765f;
        r74.reorderIconProgress = r321;
        if (r321 <= 1.0f) goto L1052;
        r74.reorderIconProgress = 1.0f;
    L1052:
        r56 = r222;
        r65 = 0.0f;
        goto L1060
    L1042:
        if (r74.reorderIconProgress == 0.0f) goto L1044;
    L1027:
        if (Build.VERSION.SDK_INT == 24) goto L1030;
        r51.restore();
        goto L1029
    L1030:
        Theme.dialogs_pinnedPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite, r74.resourcesProvider));
        r51.drawRect(0.0f, 0.0f, r74.getMeasuredWidth(), r74.topClip * r74.clipProgress, Theme.dialogs_pinnedPaint);
        r51.drawRect(0.0f, r74.getMeasuredHeight() - ((int) (r74.bottomClip * r74.clipProgress)), r74.getMeasuredWidth(), r74.getMeasuredHeight(), Theme.dialogs_pinnedPaint);
        r28 = r51;
        goto L1031
    L997:
        if (r74.fullSeparator == false) goto L999;
    L1010:
        int r125 = 0;
    L1012:
        if (r74.rightFragmentOpenedProgress == 1.0f) goto L1023;
        int r82 = Theme.dividerPaint.getAlpha();
        float r323 = r74.rightFragmentOpenedProgress;
        if (r323 == 0.0f) goto L1016;
        Theme.dividerPaint.setAlpha((int) (r82 * (1.0f - r323)));
    L1016:
        r222 = true;
        float r324 = (r74.getMeasuredHeight() - 1) - (r74.rightFragmentOffset * r74.rightFragmentOpenedProgress);
        if (LocaleController.isRTL == false) goto L1019;
        r51.drawLine(0.0f, r324, r74.getMeasuredWidth() - r125, r324, Theme.dividerPaint);
    L1020:
        r422 = 0.0f;
        if (r74.rightFragmentOpenedProgress == 0.0f) goto L1025;
        Theme.dividerPaint.setAlpha(r82);
        goto L1025
    L1019:
        r51.drawLine(r125, r324, r74.getMeasuredWidth(), r324, Theme.dividerPaint);
        goto L1020
    L999:
        if (r74.currentDialogFolderId == 0) goto L1005;
        if (r74.archiveHidden == false) goto L1005;
        if (r74.fullSeparator2 == false) goto L1010;
    L1005:
        if (r74.fullSeparator2 == true) goto L1007;
    L1009:
        r125 = AndroidUtilities.dp(r74.messagePaddingStart);
        goto L1012
    L1007:
        if (r74.archiveHidden == true) goto L1009;
    L970:
        if (r8.currentDialogFolderId != 0) goto L972;
        boolean r325 = r8.isCounterMuted();
        RectF r126 = r8.storyParams.originalAvatarRect;
        int r57 = (int) (((r126.left + r126.width()) - r8.countWidth) - AndroidUtilities.dp(r322));
        RectF r127 = r8.storyParams.originalAvatarRect;
        drawCounter(r2, r325, (int) ((r8.avatarImage.getImageY() + r8.storyParams.originalAvatarRect.height()) - AndroidUtilities.dp(22.0f)), r57, (int) (((r127.left + r127.width()) - r8.countWidthOld) - AndroidUtilities.dp(r322)), r8.rightFragmentOpenedProgress, true);
        r74 = this;
        goto L974
    L194:
        if (r310 == 0.0f) goto L201;
        float r326 = Utilities.clamp(r310 / 0.4f, 1.0f, 0.0f);
        if (SharedConfig.getDevicePerformanceClass() < r38) goto L198;
        r253 = 6.0f;
        int r327 = r2.saveLayerAlpha(AndroidUtilities.dp(RightSlidingDialogContainer.getRightPaddingSize() + 1) - (AndroidUtilities.dp(8.0f) * (1.0f - r326)), 0.0f, getMeasuredWidth(), getMeasuredHeight(), (int) ((1.0f - this.rightFragmentOpenedProgress) * 255.0f), 31);
    L199:
        r2.translate(((-(getMeasuredWidth() - AndroidUtilities.dp(74.0f))) * 0.7f) * this.rightFragmentOpenedProgress, 0.0f);
        r06 = r06 + (((-(getMeasuredWidth() - AndroidUtilities.dp(74.0f))) * 0.7f) * this.rightFragmentOpenedProgress);
    L200:
        float r263 = r06;
        if (this.translationX == 0.0f) goto L205;
    L206:
        r2.save();
        Theme.dialogs_pinnedPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider));
        this.rect.set(getMeasuredWidth() - AndroidUtilities.dp(64.0f), 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.rect.offset(0.0f, -this.translateY);
        r2.drawRoundRect(this.rect, r92, r92, Theme.dialogs_pinnedPaint);
        if (this.isSelected == false) goto L210;
        r2.drawRoundRect(this.rect, r92, r92, Theme.dialogs_tabletSeletedPaint);
    L210:
        if (this.currentDialogFolderId == 0) goto L217;
        if (SharedConfig.archiveHidden == true) goto L214;
    L215:
        Theme.dialogs_pinnedPaint.setColor(AndroidUtilities.getOffsetColor(0, Theme.getColor(Theme.key_chats_pinnedOverlay, this.resourcesProvider), this.archiveBackgroundProgress, 1.0f));
        Theme.dialogs_pinnedPaint.setAlpha((int) (r0.getAlpha() * (1.0f - this.rightFragmentOpenedProgress)));
    L221:
        r2.restore();
    L222:
        float r08 = this.translationX;
        float r46 = this.cornerProgress;
        if (r08 == 0.0f) goto L232;
        float r95 = 1.0f;
        if (r46 >= 1.0f) goto L231;
        float r47 = r46 + 0.10666667f;
        this.cornerProgress = r47;
        if (r47 <= 1.0f) goto L229;
        this.cornerProgress = 1.0f;
    L229:
        float r58 = 0.0f;
    L230:
        r212 = true;
    L239:
        if (this.drawNameLock == false) goto L242;
        BaseCell.setDrawableBounds(Theme.dialogs_lockDrawable, this.nameLockLeft, this.nameLockTop);
        Theme.dialogs_lockDrawable.draw(r2);
    L242:
        if (this.useForceThreeLines == false) goto L244;
    L247:
        float r09 = 10.0f;
    L248:
        int r010 = AndroidUtilities.dp(r09);
        if (this.useForceThreeLines == true) goto L253;
        if (SharedConfig.useThreeLinesLayout == true) goto L253;
    L255:
        if (hasTags() == false) goto L262;
        if (isForumCell() == false) goto L259;
        float r48 = 8.0f;
    L260:
        r010 = r010 - AndroidUtilities.dp(r48);
        goto L262
    L259:
        r48 = 9.0f;
    L262:
        if (this.nameLayout != null) goto L264;
        int r143 = r327;
        float r132 = r58;
        float r372 = r95;
        int r362 = -1;
        r153 = 2;
        r322 = 5.0f;
        r40 = 19.0f;
    L305:
        if (this.timeLayout == null) goto L329;
        if (this.currentDialogFolderId != 0) goto L329;
        r2.save();
        r2.translate(this.timeLeft, this.timeTop);
        TextPaint r96 = getTimeTextPaint();
        if (getIsPinned() == false) goto L318;
        r2.translate(AndroidUtilities.dp(20.0f), r132);
        float r49 = (this.timeLayout.getHeight() / 2.0f) - AndroidUtilities.dp(8.5f);
        float r011 = -AndroidUtilities.dp(20.0f);
        float r59 = this.timeLayout.getWidth() + AndroidUtilities.dp(r253);
        if (this.drawCount == true) goto L313;
    L316:
        Drawable r67 = Theme.dialogs_pinnedDrawable2;
    L315:
        Drawable r102 = r67;
        int r75 = (int) (((AndroidUtilities.dp(17.0f) - r102.getIntrinsicHeight()) / 2.0f) + r49);
        int r83 = ((int) r011) + AndroidUtilities.dp(4.0f);
        r102.setBounds(r83, r75, r102.getIntrinsicWidth() + r83, r75 + r102.getIntrinsicHeight());
        int r1111 = r96.getAlpha();
        r96.setAlpha(27);
        r2.drawRoundRect(r011, r49, r59, r49 + AndroidUtilities.dp(17.0f), AndroidUtilities.dp(8.5f), AndroidUtilities.dp(8.5f), r96);
        r96.setAlpha(r1111);
        r102.draw(r2);
        goto L318
    L313:
        if (isCounterMuted() == true) goto L316;
        r67 = Theme.dialogs_pinnedDrawable2Accent;
    L318:
        int r012 = this.timeLayout.getPaint().getColor();
        if (r012 == r96.getColor()) goto L321;
        boolean r1112 = true;
    L322:
        if (r1112 == false) goto L324;
        this.timeLayout.getPaint().setColor(r96.getColor());
    L324:
        SpoilerEffect.layoutDrawMaybe(this.timeLayout, r2);
        if (r1112 == false) goto L327;
        this.timeLayout.getPaint().setColor(r012);
    L327:
        r2.restore();
        goto L329
    L321:
        r1112 = false;
    L329:
        if (drawLock2() == false) goto L332;
        Theme.dialogs_lock2Drawable.setBounds(this.lock2Left, this.timeTop + ((this.timeLayout.getHeight() - Theme.dialogs_lock2Drawable.getIntrinsicHeight()) / r153), this.lock2Left + Theme.dialogs_lock2Drawable.getIntrinsicWidth(), (this.timeTop + ((this.timeLayout.getHeight() - Theme.dialogs_lock2Drawable.getIntrinsicHeight()) / r153)) + Theme.dialogs_lock2Drawable.getIntrinsicHeight());
        Theme.dialogs_lock2Drawable.draw(r2);
    L332:
        if (this.messageNameLayout != null) goto L334;
    L348:
        float r41 = 11.0f;
        if (this.messageLayout != null) goto L351;
        float r423 = r132;
    L472:
        if (this.buttonLayout == null) goto L527;
        r2.save();
        if (this.buttonBackgroundPaint != null) goto L477;
        this.buttonBackgroundPaint = new Paint(1);
    L477:
        if (this.canvasButton != null) goto L480;
        CanvasButton r013 = new CanvasButton(this);
        this.canvasButton = r013;
        r013.setDelegate(new DialogCell$$ExternalSyntheticLambda2(this));
        this.canvasButton.setLongPress(new DialogCell$$ExternalSyntheticLambda3(this));
    L480:
        if (this.lastTopicMessageUnread == true) goto L482;
    L515:
        r2.translate(this.buttonLeft, this.buttonTop);
        if (this.spoilers2.isEmpty() == false) goto L1127;
        SpoilerEffect.layoutDrawMaybe(this.buttonLayout, r2);
        StaticLayout r328 = this.buttonLayout;
        AnimatedEmojiSpan.drawAnimatedEmojis(r2, r328, this.animatedEmojiStack3, -0.075f, null, 0.0f, 0.0f, 0.0f, 1.0f, getAdaptiveEmojiColorFilter(3, r328.getPaint().getColor()));
    L525:
        r51.restore();
        goto L527
    L1127:
        r2.save();     // Catch: Exception -> L522
        SpoilerEffect.clipOutCanvas(r2, this.spoilers2);     // Catch: Exception -> L522
        SpoilerEffect.layoutDrawMaybe(this.buttonLayout, r2);     // Catch: Exception -> L522
        StaticLayout r329 = this.buttonLayout;     // Catch: Exception -> L522
        AnimatedEmojiSpan.drawAnimatedEmojis(r2, r329, this.animatedEmojiStack3, -0.075f, this.spoilers2, 0.0f, 0.0f, 0.0f, 1.0f, getAdaptiveEmojiColorFilter(3, r329.getPaint().getColor()));     // Catch: Exception -> L522
        r2.restore();     // Catch: Exception -> L522
        int r1113 = 0;
    L518:
        if (r1113 >= this.spoilers2.size()) goto L525;
        SpoilerEffect r014 = this.spoilers2.get(r1113);     // Catch: Exception -> L522
        r014.setColor(this.buttonLayout.getPaint().getColor());     // Catch: Exception -> L522
        r014.draw(r2);     // Catch: Exception -> L522
        r1113 = r1113 + 1;
    L522:
        e = move-exception;
        FileLog.e(e);
        goto L525
    L482:
        if (this.topMessageTopicEndIndex == this.topMessageTopicStartIndex) goto L515;
        int r015 = this.dialogsType;
        if (r015 != 0) goto L486;
    L489:
        CanvasButton r016 = this.canvasButton;
        int r330 = this.currentMessagePaint.getColor();
        if (Theme.isCurrentThemeDark() == false) goto L492;
        int r410 = 36;
    L493:
        r016.setColor(ColorUtils.setAlphaComponent(r330, r410));
        if (this.buttonCreated == true) goto L514;
        this.canvasButton.rewind();
        int r017 = this.topMessageTopicEndIndex;
        if (r017 == this.topMessageTopicStartIndex) goto L513;
        if (r017 <= 0) goto L513;
        float r018 = this.messageTop;
        if (this.useForceThreeLines == true) goto L503;
        if (SharedConfig.useThreeLinesLayout == true) goto L503;
    L505:
        if (hasTags() == true) goto L507;
    L510:
        RectF r331 = AndroidUtilities.rectTmp;
        float r411 = (this.messageLeft + AndroidUtilities.dp(2.0f)) + this.messageLayout.getPrimaryHorizontal(0);
        float r510 = this.messageLeft;
        StaticLayout r68 = this.messageLayout;
        r331.set(r411, r018, (r510 + r68.getPrimaryHorizontal(Math.min(r68.getText().length(), this.topMessageTopicEndIndex))) - AndroidUtilities.dp(3.0f), this.buttonTop - AndroidUtilities.dp(4.0f));
        r331.inset(-AndroidUtilities.dp(8.0f), -AndroidUtilities.dp(4.0f));
        if (r331.right <= r331.left) goto L513;
        this.canvasButton.addRect(r331);
        goto L513
    L507:
        if (isForumCell() == false) goto L509;
        r41 = 10.0f;
    L509:
        r018 = r018 - AndroidUtilities.dp(r41);
    L503:
        if (isForumCell() == false) goto L510;
    L513:
        float r019 = this.buttonLayout.getLineLeft(0);
        RectF r332 = AndroidUtilities.rectTmp;
        r332.set((this.buttonLeft + r019) + AndroidUtilities.dp(2.0f), this.buttonTop + AndroidUtilities.dp(2.0f), ((this.buttonLeft + r019) + this.buttonLayout.getLineWidth(0)) + AndroidUtilities.dp(12.0f), this.buttonTop + this.buttonLayout.getHeight());
        r332.inset(-AndroidUtilities.dp(8.0f), -AndroidUtilities.dp(3.0f));
        this.canvasButton.addRect(r332);
    L514:
        this.canvasButton.draw(r2);
        Theme.dialogs_forum_arrowDrawable.setAlpha(125);
        Drawable r020 = Theme.dialogs_forum_arrowDrawable;
        RectF r333 = AndroidUtilities.rectTmp;
        BaseCell.setDrawableBounds(r020, r333.right - AndroidUtilities.dp(18.0f), r333.top + ((r333.height() - Theme.dialogs_forum_arrowDrawable.getIntrinsicHeight()) / 2.0f));
        Theme.dialogs_forum_arrowDrawable.draw(r2);
        goto L515
    L492:
        r410 = 26;
        goto L493
    L486:
        if (r015 == 7) goto L489;
        if (r015 != 8) goto L515;
    L527:
        if (this.currentDialogFolderId != 0) goto L591;
        boolean r021 = this.drawClock;
        if (this.drawCheck1 == false) goto L531;
        int r1114 = r153;
    L532:
        int r022 = (r021 ? 1 : 0) + r1114;
        if (this.drawCheck2 == false) goto L535;
        int r1115 = 4;
    L536:
        int r023 = r022 + r1115;
        int r29 = this.lastStatusDrawableParams;
        if (r29 < 0) goto L542;
        if (r29 == r023) goto L542;
        if (this.statusDrawableAnimationInProgress == true) goto L542;
        createStatusDrawableAnimator(r29, r023);
    L542:
        boolean r210 = this.statusDrawableAnimationInProgress;
        if (r210 == false) goto L546;
        r023 = this.animateToStatusDrawableParams;
    L546:
        if ((r023 & 1) == 0) goto L548;
        boolean r334 = true;
    L550:
        if ((r023 & 2) == 0) goto L553;
        boolean r412 = true;
    L555:
        if ((r023 & 4) == 0) goto L557;
        boolean r511 = true;
    L558:
        if (r210 == false) goto L581;
        int r024 = this.animateFromStatusDrawableParams;
        if ((r024 & 1) == 0) goto L562;
        boolean r1116 = true;
    L564:
        if ((r024 & 2) == 0) goto L567;
        boolean r211 = true;
    L566:
        r352 = 4;
        if ((r024 & 4) == 0) goto L571;
        boolean r025 = r511;
        boolean r512 = true;
    L572:
        if (r334 == true) goto L579;
        if (r1116 == true) goto L579;
        if (r512 == false) goto L579;
        if (r211 == true) goto L579;
        if (r412 == false) goto L579;
        if (r025 == false) goto L579;
        drawCheckStatus(r51, r334, r412, r025, true, this.statusDrawableProgress);
    L580:
        DialogCell r76 = this;
        r2 = r51;
    L582:
        boolean r026 = r76.drawClock;
        if (r76.drawCheck1 == false) goto L585;
        int r1117 = r153;
    L586:
        int r027 = (r026 ? 1 : 0) + r1117;
        if (r76.drawCheck2 == false) goto L589;
        int r1118 = r352;
    L590:
        r76.lastStatusDrawableParams = r027 + r1118;
    L592:
        float r128 = 12.5f;
        if (r76.drawBotVerified == true) goto L595;
    L616:
        boolean r1119 = false;
    L618:
        if (r76.drawUnmute == false) goto L620;
    L625:
        boolean r028 = true;
    L627:
        if (r76.dialogsType == r153) goto L632;
        if (r028 == true) goto L634;
        if (r76.dialogMutedProgress <= r423) goto L632;
    L634:
        if (r76.drawVerified == true) goto L632;
        if (r76.drawBadge == true) goto L632;
        if (r76.drawScam != 0) goto L632;
        if (r028 == false) goto L646;
        float r413 = r76.dialogMutedProgress;
        if (r413 == r372) goto L646;
        float r414 = r413 + 0.10666667f;
        r76.dialogMutedProgress = r414;
        if (r414 <= r372) goto L645;
        r76.dialogMutedProgress = r372;
    L654:
        if (r76.drawPremium == false) goto L656;
        int r029 = r76.nameMutedIconLeft;
    L658:
        if (r76.useForceThreeLines == false) goto L660;
    L663:
        float r97 = 0.0f;
    L664:
        float r030 = r029 - AndroidUtilities.dp(r97);
        if (SharedConfig.useThreeLinesLayout == false) goto L667;
        float r415 = 13.5f;
    L668:
        float r416 = AndroidUtilities.dp(r415);
        if (r76.useForceThreeLines == true) goto L673;
        if (SharedConfig.useThreeLinesLayout == true) goto L673;
    L675:
        if (r76.hasTags() == true) goto L677;
    L681:
        BaseCell.setDrawableBounds(Theme.dialogs_muteDrawable, r030, r416);
        BaseCell.setDrawableBounds(Theme.dialogs_unmuteDrawable, r030, r416);
        r93 = 17;
        DrawableUtils.setBounds(Theme.dialogs_hiddenDrawable, Theme.dialogs_muteDrawable.getBounds().exactCenterX() + AndroidUtilities.dp(2.0f), Theme.dialogs_muteDrawable.getBounds().exactCenterY(), 17);
        if (r76.dialogMutedProgress == 1.0f) goto L692;
        r2.save();
        float r031 = r76.dialogMutedProgress;
        r2.scale(r031, r031, Theme.dialogs_muteDrawable.getBounds().centerX(), Theme.dialogs_muteDrawable.getBounds().centerY());
        if (r76.isHiddenInCommunity == false) goto L687;
        Theme.dialogs_hiddenDrawable.setAlpha((int) (r76.dialogMutedProgress * 255.0f));
        Theme.dialogs_hiddenDrawable.draw(r2);
        Theme.dialogs_hiddenDrawable.setAlpha(255);
    L690:
        r2.restore();
    L699:
        if (r76.drawVerified == false) goto L718;
        if (r76.useForceThreeLines == false) goto L703;
    L706:
        float r032 = 13.5f;
    L707:
        float r033 = AndroidUtilities.dp(r032);
        if (r76.useForceThreeLines == true) goto L712;
        if (SharedConfig.useThreeLinesLayout == true) goto L712;
    L714:
        if (r76.hasTags() == false) goto L716;
        r033 = r033 - AndroidUtilities.dp(9.0f);
    L716:
        BaseCell.setDrawableBounds(Theme.dialogs_verifiedDrawable, r76.nameMuteLeft - AndroidUtilities.dp(1.0f), r033);
        BaseCell.setDrawableBounds(Theme.dialogs_verifiedCheckDrawable, r76.nameMuteLeft - AndroidUtilities.dp(1.0f), r033);
        Theme.dialogs_verifiedDrawable.draw(r2);
        Theme.dialogs_verifiedCheckDrawable.draw(r2);
    L749:
        boolean r103 = false;
    L786:
        if (r76.drawReorder == true) goto L790;
        if (r76.reorderIconProgress != 0.0f) goto L790;
    L793:
        float r034 = r76.animatorPollVotesMentionVisible.getFloatValue();
        if (r76.drawError == false) goto L798;
        Theme.dialogs_errorDrawable.setAlpha((int) ((1.0f - r76.reorderIconProgress) * 255.0f));
        r76.rect.set(r76.errorLeft, r76.errorTop, r1 + AndroidUtilities.dp(20.666f), r76.errorTop + AndroidUtilities.dp(20.666f));
        RectF r035 = r76.rect;
        float r129 = AndroidUtilities.density;
        r2.drawRoundRect(r035, 10.5f * r129, r129 * 10.5f, Theme.dialogs_errorPaint);
        BaseCell.setDrawableBounds(Theme.dialogs_errorDrawable, r76.errorLeft + AndroidUtilities.dp(4.5f), r76.errorTop + AndroidUtilities.dp(r322));
        Theme.dialogs_errorDrawable.draw(r2);
    L796:
        r8 = r76;
    L853:
        if (r8.thumbsCount <= 0) goto L891;
        float r036 = r8.updateHelper.typingProgres;
        if (r036 == 1.0f) goto L891;
        if (r036 <= 0.0f) goto L864;
        r51.saveLayerAlpha(0.0f, 0.0f, r8.getWidth(), r8.getHeight(), (int) ((1.0f - r036) * 255.0f), 31);
        r2 = r51;
        if (r8.updateHelper.typingOutToTop == false) goto L862;
        float r037 = -AndroidUtilities.dp(14.0f);
        float r130 = r8.updateHelper.typingProgres;
    L863:
        r2.translate(0.0f, r037 * r130);
        goto L864
    L862:
        r037 = AndroidUtilities.dp(14.0f);
        r130 = r8.updateHelper.typingProgres;
    L864:
        int r038 = 0;
    L866:
        if (r038 >= r8.thumbsCount) goto L888;
        if (r8.thumbImageSeen[r038] == true) goto L871;
        int r417 = r362;
    L887:
        r038 = r038 + 1;
        r362 = r417;
        goto L866
    L871:
        if (r8.thumbBackgroundPaint != null) goto L873;
        Paint r131 = new Paint(1);
        r8.thumbBackgroundPaint = r131;
        r131.setShadowLayer(AndroidUtilities.dp(1.34f), 0.0f, AndroidUtilities.dp(0.34f), 402653184);
        r8.thumbBackgroundPaint.setColor(0);
    L873:
        RectF r133 = AndroidUtilities.rectTmp;
        r133.set(r8.thumbImage[r038].getImageX(), r8.thumbImage[r038].getImageY(), r8.thumbImage[r038].getImageX2(), r8.thumbImage[r038].getImageY2());
        r8.thumbImage[r038].draw(r2);
        if (r8.drawSpoiler[r038] == false) goto L883;
        Path r335 = r8.thumbPath;
        if (r335 != null) goto L878;
        r8.thumbPath = new Path();
    L879:
        r8.thumbPath.addRoundRect(r133, r8.thumbImage[r038].getRoundRadius()[0], r8.thumbImage[r038].getRoundRadius()[1], Path.Direction.CW);
        r2.save();
        r2.clipPath(r8.thumbPath);
        if (r8.thumbSpoiler != null) goto L882;
        r8.thumbSpoiler = new SpoilerEffect();
    L882:
        r417 = r362;
        r8.thumbSpoiler.setColor(ColorUtils.setAlphaComponent(r417, (int) (Color.alpha(r362) * 0.325f)));
        r8.thumbSpoiler.setBounds((int) r8.thumbImage[r038].getImageX(), (int) r8.thumbImage[r038].getImageY(), (int) r8.thumbImage[r038].getImageX2(), (int) r8.thumbImage[r038].getImageY2());
        r8.thumbSpoiler.draw(r2);
        r8.invalidate();
        r2.restore();
    L885:
        if (r8.drawPlay[r038] == false) goto L887;
        BaseCell.setDrawableBounds(Theme.dialogs_playDrawable, (int) (r8.thumbImage[r038].getCenterX() - (Theme.dialogs_playDrawable.getIntrinsicWidth() / r153)), (int) (r8.thumbImage[r038].getCenterY() - (Theme.dialogs_playDrawable.getIntrinsicHeight() / r153)));
        Theme.dialogs_playDrawable.draw(r2);
        goto L887
    L878:
        r335.rewind();
        goto L879
    L883:
        r417 = r362;
        goto L885
    L888:
        int r418 = r362;
        if (r8.updateHelper.typingProgres <= 0.0f) goto L892;
        r2.restore();
    L892:
        DialogCellTags r039 = r8.tags;
        if (r039 != null) goto L895;
    L897:
        if (r143 == r418) goto L899;
        r2.restoreToCount(r143);
    L899:
        r07 = r1119;
        r112 = r103;
        goto L902
    L895:
        if (r039.isEmpty() == true) goto L897;
        r2.save();
        r2.translate(r8.tagsLeft, (r8.getMeasuredHeight() - AndroidUtilities.dp(21.66f)) - (r8.useSeparator ? 1 : 0));
        r8.tags.draw(r2, r8.tagsRight - r8.tagsLeft);
        r2.restore();
    L891:
        r418 = r362;
        goto L892
    L798:
        if (r76.drawCount == true) goto L802;
        if (r76.drawMention == true) goto L802;
    L804:
        if (r76.countChangeProgress == 1.0f) goto L806;
    L821:
        boolean r336 = r76.isCounterMuted();
        r2 = r51;
        drawCounter(r2, r336, r76.countTop, r76.countLeft, r76.countLeftOld, 1.0f, false);
        r8 = this;
        if (r8.drawMention == false) goto L834;
        Theme.dialogs_countPaint.setAlpha((int) ((1.0f - r8.reorderIconProgress) * 255.0f));
        if (r8.mentionLayout == null) goto L832;
        r8.rect.set(r8.mentionLeft, r8.countTop, (r1 + r8.mentionWidth) + AndroidUtilities.dp(12.666f), r8.countTop + AndroidUtilities.dp(20.666f));
        if (r336 == true) goto L828;
    L830:
        Paint r134 = Theme.dialogs_countPaint;
    L831:
        RectF r337 = r8.rect;
        r2.drawRoundRect(r337, r337.height() / 2.0f, r8.rect.height() / 2.0f, r134);
        Theme.dialogs_countTextPaint2.setAlpha((int) ((1.0f - r8.reorderIconProgress) * 255.0f));
        r2.save();
        r2.translate(r8.mentionLeft + AndroidUtilities.dp(6.333f), r8.countTop + AndroidUtilities.dp(4.0f));
        r8.mentionLayout.draw(r2);
        r2.restore();
        goto L834
    L828:
        if (r8.folderId == 0) goto L830;
        r134 = Theme.dialogs_countGrayPaint;
        goto L831
    L832:
        Drawable r135 = Theme.dialogs_mentionDrawable;
        r135.setAlpha((int) ((1.0f - r8.reorderIconProgress) * 255.0f));
        DrawableUtils.setBounds(r135, r8.mentionLeft + AndroidUtilities.dp(10.333f), r8.countTop + AndroidUtilities.dp(10.333f), r93);
        r135.draw(r2);
    L834:
        if (r8.drawReactionMention == false) goto L836;
    L837:
        r8.rect.set(r8.reactionMentionLeft, r8.countTop, AndroidUtilities.dp(20.666f) + r1, r8.countTop + AndroidUtilities.dp(20.666f));
        float r338 = r8.reactionsMentionsChangeProgress;
        if (r338 != 1.0f) goto L840;
        r338 = 1.0f;
    L844:
        Drawable r513 = Theme.dialogs_reactionsMentionDrawable;
        r513.setAlpha((int) ((1.0f - r8.reorderIconProgress) * 255.0f));
        DrawableUtils.setBounds(r513, r1 + AndroidUtilities.dp(10.333f), r8.countTop + AndroidUtilities.dp(10.333f), r93);
        DrawableUtils.drawWithScale(r2, r513, r338);
    L846:
        if (r8.drawPollVotesMention == true) goto L850;
        if (r034 <= 0.0f) goto L853;
    L850:
        if (r034 == 0.0f) goto L853;
        r8.rect.set(r8.pollVotesMentionLeft, r8.countTop, AndroidUtilities.dp(20.666f) + r1, r8.countTop + AndroidUtilities.dp(20.666f));
        Drawable r339 = Theme.dialogs_pollMentionDrawable;
        r339.setAlpha((int) ((1.0f - r8.reorderIconProgress) * 255.0f));
        DrawableUtils.setBounds(r339, r1 + AndroidUtilities.dp(10.333f), r8.countTop + AndroidUtilities.dp(10.333f), r93);
        DrawableUtils.drawWithScale(r2, r339, r034);
        goto L853
    L840:
        if (r8.drawReactionMention == true) goto L844;
        r338 = 1.0f - r338;
        goto L844
    L836:
        if (r8.reactionsMentionsChangeProgress == 1.0f) goto L846;
    L806:
        if (r76.drawReactionMention == true) goto L821;
        if (r76.reactionsMentionsChangeProgress != 1.0f) goto L821;
        if (r76.drawPollVotesMention == true) goto L821;
        if (r034 > 0.0f) goto L821;
        if (r76.openBot == false) goto L820;
        r2.save();
        float r040 = r76.openButtonBounce.getScale(0.05f);
        r2.scale(r040, r040, r76.openButtonRect.centerX(), r76.openButtonRect.centerY());
        r76.openButtonBackgroundPaint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton, r76.resourcesProvider));
        RectF r041 = r76.openButtonRect;
        r2.drawRoundRect(r041, r041.height() / 2.0f, r76.openButtonRect.height() / 2.0f, r76.openButtonBackgroundPaint);
        Text r042 = r76.openButtonText;
        if (r042 == null) goto L819;
        r042.draw(r2, r76.openButtonRect.left + AndroidUtilities.dp(13.0f), r76.openButtonRect.centerY(), Theme.getColor(Theme.key_featuredStickers_buttonText, r76.resourcesProvider), 1.0f);
    L819:
        r51.restore();
    L820:
        r2 = r51;
    L802:
        if (r76.drawCount2 == true) goto L821;
    L790:
        if (LocaleController.isRTL == true) goto L792;
        Paint r69 = r76.getPaintReorderGradient();
        r69.setAlpha((int) (r76.reorderIconProgress * 255.0f));
        r2.save();
        r2.translate(r76.pinLeft - AndroidUtilities.dp(24.0f), r76.pinTop);
        r51.drawRect(0.0f, 0.0f, r76.getMeasuredWidth() - (r76.pinLeft - AndroidUtilities.dp(24.0f)), AndroidUtilities.dp(24.0f), r69);
        r2 = r51;
        r2.restore();
    L792:
        Theme.dialogs_reorderDrawable.setAlpha((int) (r76.reorderIconProgress * 255.0f));
        BaseCell.setDrawableBounds(Theme.dialogs_reorderDrawable, r76.pinLeft, r76.pinTop);
        Theme.dialogs_reorderDrawable.draw(r2);
    L712:
        if (r76.isForumCell() == false) goto L716;
    L703:
        if (SharedConfig.useThreeLinesLayout == true) goto L706;
        r032 = 16.5f;
        goto L707
    L718:
        if (r76.drawPremium == true) goto L751;
        if (r76.drawBadge == true) goto L751;
        if (r76.drawScam == 0) goto L749;
        if (r76.useForceThreeLines == false) goto L727;
    L730:
        float r043 = 12.0f;
    L731:
        int r044 = AndroidUtilities.dp(r043);
        if (r76.useForceThreeLines == true) goto L736;
        if (SharedConfig.useThreeLinesLayout == true) goto L736;
    L738:
        if (r76.hasTags() == false) goto L741;
        r044 = r044 - AndroidUtilities.dp(9.0f);
    L741:
        if (r76.drawScam != 1) goto L743;
        ScamDrawable r136 = Theme.dialogs_scamDrawable;
    L744:
        BaseCell.setDrawableBounds(r136, r76.nameMuteLeft, r044);
        if (r76.drawScam != 1) goto L747;
        ScamDrawable r045 = Theme.dialogs_scamDrawable;
    L748:
        r045.draw(r2);
        goto L749
    L747:
        r045 = Theme.dialogs_fakeDrawable;
        goto L748
    L743:
        r136 = Theme.dialogs_fakeDrawable;
    L736:
        if (r76.isForumCell() == false) goto L741;
    L727:
        if (SharedConfig.useThreeLinesLayout == true) goto L730;
        r043 = 15.0f;
    L751:
        if (r76.useForceThreeLines == false) goto L753;
    L756:
        float r046 = 12.5f;
    L757:
        int r047 = AndroidUtilities.dp(r046);
        if (r76.useForceThreeLines == true) goto L762;
        if (SharedConfig.useThreeLinesLayout == true) goto L762;
    L764:
        if (r76.hasTags() == false) goto L767;
        r047 = r047 - AndroidUtilities.dp(9.0f);
    L767:
        if (r76.emojiStatus != null) goto L769;
    L777:
        Drawable r048 = PremiumGradient.getInstance().premiumStarDrawableMini;
        int r419 = r76.nameMuteLeft - AndroidUtilities.dp(1.0f);
        if (r76.useForceThreeLines == false) goto L780;
    L783:
        BaseCell.setDrawableBounds(r048, r419, AndroidUtilities.dp(r128));
        r048.draw(r2);
        boolean r049 = false;
    L784:
        r103 = r049;
        goto L786
    L780:
        if (SharedConfig.useThreeLinesLayout == true) goto L783;
        r128 = 15.5f;
        goto L783
    L769:
        if (r76.drawPremium == false) goto L771;
    L772:
        r76.emojiStatusView.setTranslationX((r263 + r76.nameMuteLeft) - AndroidUtilities.dp(2.0f));
        r76.emojiStatusView.setTranslationY((r12 + r047) - AndroidUtilities.dp(4.0f));
        if (r76.rightFragmentOpenedProgress <= 0.0f) goto L775;
        r76.emojiStatus.setBounds(r76.nameMuteLeft - AndroidUtilities.dp(2.0f), r047 - AndroidUtilities.dp(4.0f), r76.nameMuteLeft + AndroidUtilities.dp(20.0f), (r047 - AndroidUtilities.dp(4.0f)) + AndroidUtilities.dp(22.0f));
        r76.emojiStatus.draw(r2);
        r049 = false;
    L776:
        r76.emojiStatus.setColor(Integer.valueOf(Theme.getColor(Theme.key_chats_verifiedBackground, r76.resourcesProvider)));
        goto L784
    L775:
        r049 = true;
        goto L776
    L771:
        if (r76.drawBadge == false) goto L777;
    L762:
        if (r76.isForumCell() == false) goto L767;
    L753:
        if (SharedConfig.useThreeLinesLayout == true) goto L756;
        r046 = 15.5f;
        goto L757
    L687:
        if (r76.drawUnmute == false) goto L689;
        Theme.dialogs_unmuteDrawable.setAlpha((int) (r76.dialogMutedProgress * 255.0f));
        Theme.dialogs_unmuteDrawable.draw(r2);
        Theme.dialogs_unmuteDrawable.setAlpha(255);
        goto L690
    L689:
        Theme.dialogs_muteDrawable.setAlpha((int) (r76.dialogMutedProgress * 255.0f));
        Theme.dialogs_muteDrawable.draw(r2);
        Theme.dialogs_muteDrawable.setAlpha(255);
        goto L690
    L692:
        if (r76.isHiddenInCommunity == false) goto L695;
        Theme.dialogs_hiddenDrawable.draw(r2);
        goto L699
    L695:
        if (r76.drawUnmute == false) goto L697;
        Theme.dialogs_unmuteDrawable.draw(r2);
        goto L699
    L697:
        Theme.dialogs_muteDrawable.draw(r2);
        goto L699
    L677:
        if (r76.isForumCell() == false) goto L679;
        float r514 = 8.0f;
    L680:
        r416 = r416 - AndroidUtilities.dp(r514);
        goto L681
    L679:
        r514 = 9.0f;
    L673:
        if (r76.isForumCell() == false) goto L681;
    L667:
        r415 = 17.5f;
        goto L668
    L660:
        if (SharedConfig.useThreeLinesLayout == true) goto L663;
        r97 = 1.0f;
        goto L664
    L656:
        r029 = r76.nameMuteLeft;
        goto L658
    L645:
        r76.invalidate();
    L646:
        if (r028 == true) goto L654;
        float r050 = r76.dialogMutedProgress;
        if (r050 == r423) goto L654;
        float r051 = r050 - 0.10666667f;
        r76.dialogMutedProgress = r051;
        if (r051 >= r423) goto L652;
        r76.dialogMutedProgress = r423;
        goto L654
    L652:
        r76.invalidate();
    L632:
        r93 = 17;
        goto L699
    L620:
        if (r76.dialogMuted == true) goto L625;
        if (r76.isHiddenInCommunity == true) goto L625;
        r028 = false;
        goto L627
    L595:
        if (r76.useForceThreeLines == false) goto L597;
    L600:
        float r052 = 12.5f;
    L601:
        int r053 = AndroidUtilities.dp(r052);
        if (r76.useForceThreeLines == true) goto L606;
        if (SharedConfig.useThreeLinesLayout == true) goto L606;
    L608:
        if (r76.hasTags() == false) goto L610;
        r053 = r053 - AndroidUtilities.dp(9.0f);
    L610:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r420 = r76.botVerification;
        if (r420 == null) goto L616;
        r420.setColor(Integer.valueOf(Theme.getColor(Theme.key_chats_verifiedBackground, r76.resourcesProvider)));
        r76.botVerificationView.setTranslationX((r263 + r76.nameLeft) - AndroidUtilities.dp(r40));
        r76.botVerificationView.setTranslationY((r053 + r12) + AndroidUtilities.dp(-1.0f));
        if (r76.rightFragmentOpenedProgress <= r423) goto L615;
        r76.botVerification.setBounds(r76.nameLeft - AndroidUtilities.dp(r40), AndroidUtilities.dp(-1.0f) + r053, r76.nameLeft - AndroidUtilities.dp(2.0f), r053 + AndroidUtilities.dp(16.0f));
        r76.botVerification.draw(r2);
        goto L616
    L615:
        r1119 = true;
    L606:
        if (r76.isForumCell() == false) goto L610;
    L597:
        if (SharedConfig.useThreeLinesLayout == true) goto L600;
        r052 = 15.5f;
        goto L601
    L589:
        r1118 = 0;
        goto L590
    L585:
        r1117 = 0;
    L579:
        boolean r98 = r025;
        boolean r340 = r1116;
        drawCheckStatus(r51, r340, r211, r512, false, r372 - this.statusDrawableProgress);
        drawCheckStatus(r51, r334, r412, r98, false, this.statusDrawableProgress);
        goto L580
    L571:
        r025 = r511;
        r512 = false;
        goto L572
    L567:
        r211 = false;
        goto L566
    L562:
        r1116 = false;
        goto L564
    L581:
        r352 = 4;
        r2 = r51;
        drawCheckStatus(r2, r334, r412, r511, false, 1.0f);
        r76 = this;
        goto L582
    L557:
        r511 = false;
        goto L558
    L553:
        r412 = false;
        goto L555
    L548:
        r334 = false;
        goto L550
    L535:
        r1115 = 0;
        goto L536
    L531:
        r1114 = 0;
        goto L532
    L591:
        r2 = r51;
        r76 = this;
        r352 = 4;
        goto L592
    L351:
        if (this.currentDialogFolderId != 0) goto L353;
        com.exteragram.messenger.utils.ui.TextPaint r054 = Theme.dialogs_messagePaint[this.paintIndex];
        int r341 = Theme.getColor(Theme.key_chats_message, this.resourcesProvider);
        ((TextPaint) r054).linkColor = r341;
        r054.setColor(r341);
    L357:
        float r342 = AndroidUtilities.dp(14.0f);
        DialogUpdateHelper r055 = this.updateHelper;
        boolean r421 = r055.typingOutToTop;
        int r515 = this.messageTop;
        if (r421 == false) goto L360;
        float r424 = r515 - (r055.typingProgres * r342);
    L362:
        if (this.useForceThreeLines == true) goto L366;
        if (SharedConfig.useThreeLinesLayout == true) goto L366;
    L368:
        if (hasTags() == false) goto L375;
        if (isForumCell() == false) goto L372;
        float r056 = 10.0f;
    L373:
        r424 = r424 - AndroidUtilities.dp(r056);
        goto L375
    L372:
        r056 = 11.0f;
    L375:
        if (this.updateHelper.typingProgres == r372) goto L405;
        r2.save();
        r2.translate(this.messageLeft, r424);
        int r425 = this.messageLayout.getPaint().getAlpha();
        this.messageLayout.getPaint().setAlpha((int) (r425 * (r372 - this.updateHelper.typingProgres)));
        if (this.spoilers.isEmpty() == false) goto L1131;
        float r432 = r342;
        r423 = r132;
        int r137 = r425;
        SpoilerEffect.layoutDrawMaybe(this.messageLayout, r2);
        StaticLayout r343 = this.messageLayout;
        AnimatedEmojiSpan.drawAnimatedEmojis(r2, r343, this.animatedEmojiStack, -0.075f, null, 0.0f, 0.0f, 0.0f, 1.0f, getAdaptiveEmojiColorFilter(r153, r343.getPaint().getColor()));
    L404:
        this.messageLayout.getPaint().setAlpha(r137);
        r2.restore();
    L406:
        r2.save();
        DialogUpdateHelper r057 = this.updateHelper;
        boolean r344 = r057.typingOutToTop;
        int r426 = this.messageTop;
        if (r344 == false) goto L409;
        float r345 = r426 + (r432 * (r372 - r057.typingProgres));
    L411:
        if (this.useForceThreeLines == true) goto L415;
        if (SharedConfig.useThreeLinesLayout == true) goto L415;
    L417:
        if (hasTags() == true) goto L419;
    L423:
        r2.translate(this.typingLeft, r345);
        StaticLayout r058 = this.typingLayout;
        if (r058 != null) goto L426;
    L428:
        r2.restore();
        if (this.typingLayout == null) goto L472;
        int r059 = this.printingStringType;
        if (r059 >= 0) goto L436;
        DialogUpdateHelper r346 = this.updateHelper;
        if (r346.typingProgres <= r423) goto L472;
        if (r346.lastKnownTypingType < 0) goto L472;
    L436:
        if (r059 >= 0) goto L439;
        r059 = this.updateHelper.lastKnownTypingType;
    L439:
        StatusDrawable r347 = Theme.getChatStatusDrawable(r059);
        if (r347 == null) goto L472;
        r2.save();
        r347.setColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_chats_actionMessage), (int) (Color.alpha(r4) * this.updateHelper.typingProgres)));
        DialogUpdateHelper r427 = this.updateHelper;
        boolean r516 = r427.typingOutToTop;
        int r610 = this.messageTop;
        if (r516 == false) goto L444;
        float r517 = r610 + (r432 * (r372 - r427.typingProgres));
    L446:
        if (this.useForceThreeLines == true) goto L450;
        if (SharedConfig.useThreeLinesLayout == true) goto L450;
    L452:
        if (hasTags() == false) goto L459;
        if (isForumCell() == false) goto L456;
        float r428 = 10.0f;
    L457:
        r517 = r517 - AndroidUtilities.dp(r428);
        goto L459
    L456:
        r428 = 11.0f;
    L459:
        if (r059 != 1) goto L461;
    L464:
        float r429 = this.statusDrawableLeft;
        if (r059 != 1) goto L467;
        int r1120 = AndroidUtilities.dp(r372);
    L468:
        r2.translate(r429, r517 + r1120);
    L469:
        r347.draw(r2);
        invalidate();
        r2.restore();
        goto L472
    L467:
        r1120 = 0;
        goto L468
    L461:
        if (r059 == 4) goto L464;
        r2.translate(this.statusDrawableLeft, r517 + ((AndroidUtilities.dp(18.0f) - r347.getIntrinsicHeight()) / 2.0f));
    L450:
        if (isForumCell() == false) goto L459;
    L444:
        r517 = r610 - (r432 * (r372 - r427.typingProgres));
        goto L446
    L426:
        if (this.updateHelper.typingProgres <= r423) goto L428;
        int r060 = r058.getPaint().getAlpha();
        this.typingLayout.getPaint().setAlpha((int) (r060 * this.updateHelper.typingProgres));
        this.typingLayout.draw(r2);
        this.typingLayout.getPaint().setAlpha(r060);
        goto L428
    L419:
        if (isForumCell() == false) goto L421;
        float r061 = 10.0f;
    L422:
        r345 = r345 - AndroidUtilities.dp(r061);
        goto L423
    L421:
        r061 = 11.0f;
    L415:
        if (isForumCell() == false) goto L423;
    L409:
        r345 = r426 - (r432 * (r372 - r057.typingProgres));
        goto L411
    L1131:
        r2.save();     // Catch: Exception -> L400
        SpoilerEffect.clipOutCanvas(r2, this.spoilers);     // Catch: Exception -> L400
        SpoilerEffect.layoutDrawMaybe(this.messageLayout, r2);     // Catch: Exception -> L400
        StaticLayout r348 = this.messageLayout;     // Catch: Exception -> L397
        r432 = r342;
        r423 = r132;
        r137 = r425;
        AnimatedEmojiSpan.drawAnimatedEmojis(r2, r348, this.animatedEmojiStack, -0.075f, this.spoilers, 0.0f, 0.0f, 0.0f, 1.0f, getAdaptiveEmojiColorFilter(r153, r348.getPaint().getColor()));     // Catch: Exception -> L391
        r2.restore();     // Catch: Exception -> L391
        int r1121 = 0;
    L387:
        if (r1121 >= this.spoilers.size()) goto L404;
        SpoilerEffect r062 = this.spoilers.get(r1121);     // Catch: Exception -> L391
        r062.setColor(this.messageLayout.getPaint().getColor());     // Catch: Exception -> L391
        r062.draw(r2);     // Catch: Exception -> L391
        r1121 = r1121 + 1;
    L391:
        e = e;
    L402:
        FileLog.e(e);
    L393:
        e = e;
        r432 = r342;
        r423 = r132;
        r137 = r425;
    L395:
        e = e;
        r432 = r342;
        r423 = r132;
        r137 = r425;
    L397:
        e = e;
        r432 = r342;
    L399:
        r423 = r132;
        r137 = r425;
    L400:
        e = e;
        r432 = r342;
        goto L399
    L405:
        r432 = r342;
        r423 = r132;
    L366:
        if (isForumCell() == false) goto L375;
    L360:
        r424 = r515 + (r055.typingProgres * r342);
        goto L362
    L353:
        if (this.chat == null) goto L355;
        com.exteragram.messenger.utils.ui.TextPaint r063 = Theme.dialogs_messagePaint[this.paintIndex];
        int r349 = Theme.getColor(Theme.key_chats_nameMessageArchived, this.resourcesProvider);
        ((TextPaint) r063).linkColor = r349;
        r063.setColor(r349);
        goto L357
    L355:
        com.exteragram.messenger.utils.ui.TextPaint r064 = Theme.dialogs_messagePaint[this.paintIndex];
        int r350 = Theme.getColor(Theme.key_chats_messageArchived, this.resourcesProvider);
        ((TextPaint) r064).linkColor = r350;
        r064.setColor(r350);
        goto L357
    L334:
        if (isForumCell() == true) goto L348;
        if (this.currentDialogFolderId == 0) goto L339;
        com.exteragram.messenger.utils.ui.TextPaint r065 = Theme.dialogs_messageNamePaint;
        int r351 = Theme.getColor(Theme.key_chats_nameMessageArchived_threeLines, this.resourcesProvider);
        ((TextPaint) r065).linkColor = r351;
        r065.setColor(r351);
    L342:
        r2.save();
        r2.translate(this.messageNameLeft, this.messageNameTop);
        SpoilerEffect.layoutDrawMaybe(this.messageNameLayout, r2);     // Catch: Exception -> L345
        StaticLayout r353 = this.messageNameLayout;     // Catch: Exception -> L345
        AnimatedEmojiSpan.drawAnimatedEmojis(r2, r353, this.animatedEmojiStack2, -0.075f, null, 0.0f, 0.0f, 0.0f, 1.0f, getAdaptiveEmojiColorFilter(1, r353.getPaint().getColor()));     // Catch: Exception -> L345
    L347:
        r2.restore();
    L345:
        e = move-exception;
        FileLog.e(e);
        goto L347
    L339:
        if (this.draftMessage == null) goto L341;
        com.exteragram.messenger.utils.ui.TextPaint r066 = Theme.dialogs_messageNamePaint;
        int r354 = Theme.getColor(Theme.key_chats_draft, this.resourcesProvider);
        ((TextPaint) r066).linkColor = r354;
        r066.setColor(r354);
        goto L342
    L341:
        com.exteragram.messenger.utils.ui.TextPaint r067 = Theme.dialogs_messageNamePaint;
        int r355 = Theme.getColor(Theme.key_chats_nameMessage_threeLines, this.resourcesProvider);
        ((TextPaint) r067).linkColor = r355;
        r067.setColor(r355);
        goto L342
    L264:
        if (this.nameLayoutEllipsizeByGradient == true) goto L266;
    L276:
        r143 = r327;
        float r373 = r58;
        r322 = 5.0f;
    L278:
        if (Theme.dialogs_namePaint != null) goto L281;
        Theme.createDialogsResources(getContext());
    L281:
        if (this.currentDialogFolderId == 0) goto L284;
        com.exteragram.messenger.utils.ui.TextPaint r356 = Theme.dialogs_namePaint[this.paintIndex];
        int r430 = Theme.getColor(Theme.key_chats_nameArchived, this.resourcesProvider);
        ((TextPaint) r356).linkColor = r430;
        r356.setColor(r430);
        int r77 = 2;
    L294:
        r2.save();
        r2.translate(this.nameLeft + this.nameLayoutTranslateX, r010);
        SpoilerEffect.layoutDrawMaybe(this.nameLayout, r2);
        StaticLayout r357 = this.nameLayout;
        r362 = -1;
        r132 = r373;
        r153 = r77;
        r40 = 19.0f;
        r372 = r95;
        AnimatedEmojiSpan.drawAnimatedEmojis(r2, r357, this.animatedEmojiStackName, -0.075f, null, 0.0f, 0.0f, 0.0f, 1.0f, getAdaptiveEmojiColorFilter(0, r357.getPaint().getColor()));
        r2.restore();
        if (this.nameLayoutEllipsizeByGradient == false) goto L305;
        if (this.nameLayoutFits == true) goto L305;
        r2.save();
        boolean r068 = this.nameLayoutEllipsizeLeft;
        int r358 = this.nameLeft;
        if (r068 == false) goto L301;
        r2.translate(r358, r132);
        r2.drawRect(0.0f, 0.0f, AndroidUtilities.dp(24.0f), getMeasuredHeight(), this.fadePaint);
    L302:
        r2.restore();
        r2.restore();
        goto L305
    L301:
        r2.translate((r358 + this.nameWidth) - AndroidUtilities.dp(24.0f), r132);
        r2.drawRect(0.0f, 0.0f, AndroidUtilities.dp(24.0f), getMeasuredHeight(), this.fadePaintBack);
        goto L302
    L284:
        if (this.encryptedChat != null) goto L292;
        CustomDialog r359 = this.customDialog;
        if (r359 == null) goto L290;
        r77 = 2;
        if (r359.type == 2) goto L293;
    L291:
        com.exteragram.messenger.utils.ui.TextPaint r360 = Theme.dialogs_namePaint[this.paintIndex];
        int r431 = Theme.getColor(Theme.key_chats_name, this.resourcesProvider);
        ((TextPaint) r360).linkColor = r431;
        r360.setColor(r431);
    L293:
        com.exteragram.messenger.utils.ui.TextPaint r361 = Theme.dialogs_namePaint[this.paintIndex];
        int r433 = Theme.getColor(Theme.key_chats_secretName, this.resourcesProvider);
        ((TextPaint) r361).linkColor = r433;
        r361.setColor(r433);
        goto L294
    L290:
        r77 = 2;
        goto L291
    L292:
        r77 = 2;
        goto L293
    L266:
        if (this.nameLayoutFits == true) goto L276;
        if (this.nameLayoutEllipsizeLeft == false) goto L273;
        if (this.fadePaint != null) goto L273;
        Paint r434 = new Paint();
        this.fadePaint = r434;
        r434.setShader(new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(24.0f), 0.0f, new int[]{-1, 0}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        this.fadePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    L275:
        r373 = r58;
        r143 = r327;
        r322 = 5.0f;
        r2.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), 255, 31);
        int r363 = this.nameLeft;
        r2.clipRect(r363, 0, this.nameWidth + r363, getMeasuredHeight());
    L273:
        if (this.fadePaintBack != null) goto L275;
        Paint r435 = new Paint();
        this.fadePaintBack = r435;
        r435.setShader(new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(24.0f), 0.0f, new int[]{0, -1}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        this.fadePaintBack.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    L253:
        if (isForumCell() == false) goto L262;
    L244:
        if (SharedConfig.useThreeLinesLayout == true) goto L247;
        r09 = 14.0f;
        goto L248
    L231:
        r58 = 0.0f;
    L237:
        r212 = false;
        goto L239
    L232:
        r58 = 0.0f;
        r95 = 1.0f;
        if (r46 <= 0.0f) goto L237;
        float r436 = r46 - 0.10666667f;
        this.cornerProgress = r436;
        if (r436 >= 0.0f) goto L230;
        this.cornerProgress = 0.0f;
        goto L230
    L214:
        if (this.archiveBackgroundProgress != 0.0f) goto L215;
    L217:
        if (getIsPinned() == false) goto L219;
    L220:
        Theme.dialogs_pinnedPaint.setColor(Theme.getColor(Theme.key_chats_pinnedOverlay, this.resourcesProvider));
        Theme.dialogs_pinnedPaint.setAlpha((int) (r0.getAlpha() * (1.0f - this.rightFragmentOpenedProgress)));
        goto L221
    L219:
        if (this.drawPinBackground == false) goto L221;
    L205:
        if (this.cornerProgress == 0.0f) goto L222;
    L198:
        r253 = 6.0f;
        int r437 = r2.save();
        r2.clipRect(AndroidUtilities.dp(RightSlidingDialogContainer.getRightPaddingSize() + 1) - (AndroidUtilities.dp(8.0f) * (1.0f - r326)), 0.0f, getMeasuredWidth(), getMeasuredHeight());
        r327 = r437;
        goto L199
    L201:
        r253 = 6.0f;
        r327 = -1;
        goto L200
    L190:
        r12 = 0.0f;
        goto L191
    L180:
        if (this.archiveBackgroundProgress != r64) goto L181;
    L183:
        if (getIsPinned() == false) goto L185;
    L186:
        Theme.dialogs_pinnedPaint.setColor(Theme.getColor(Theme.key_chats_pinnedOverlay, this.resourcesProvider));
        Theme.dialogs_pinnedPaint.setAlpha((int) (r3.getAlpha() * (1.0f - this.rightFragmentOpenedProgress)));
        goto L187
    L185:
        if (this.drawPinBackground == false) goto L187;
    L174:
        r64 = 0.0f;
        goto L175
    L170:
        r06 = 0.0f;
        goto L171
    L155:
        if (this.swipeMessageWidth == getMeasuredWidth()) goto L160;
    L150:
        if (r35 == r21) goto L152;
        float r364 = this.interpolator.getInterpolation(r35) + r21;
        r2.scale(r364, r364, this.translationDrawable.getIntrinsicWidth() / 2, this.translationDrawable.getIntrinsicHeight() / 2);
        goto L152
    L146:
        r38 = r10;
        r25 = r11;
        r26 = r15;
        goto L147
    L132:
        if (Theme.dialogs_archiveDrawableRecolored == false) goto L135;
        Theme.dialogs_archiveDrawable.setLayerColor("Arrow", Theme.getNonAnimatedColor(Theme.key_chats_archiveBackground));
        Theme.dialogs_archiveDrawableRecolored = false;
    L135:
        if (Theme.dialogs_hidePsaDrawableRecolored == false) goto L138;
        Theme.dialogs_hidePsaDrawable.beginApplyLayerColors();
        RLottieDrawable r365 = Theme.dialogs_hidePsaDrawable;
        int r438 = Theme.key_chats_archiveBackground;
        r365.setLayerColor("Line 1", Theme.getNonAnimatedColor(r438));
        Theme.dialogs_hidePsaDrawable.setLayerColor("Line 2", Theme.getNonAnimatedColor(r438));
        Theme.dialogs_hidePsaDrawable.setLayerColor("Line 3", Theme.getNonAnimatedColor(r438));
        Theme.dialogs_hidePsaDrawable.commitApplyLayerColors();
        Theme.dialogs_hidePsaDrawableRecolored = false;
        goto L138
    L137:
        r21 = 1.0f;
        r37 = 0.0f;
        r36 = 0;
        r22 = true;
        r04 = r24;
        r10 = 2;
        r15 = r4;
        r11 = r52;
        r9 = r6;
        goto L138
    L125:
        if (Math.abs(this.translationX) <= AndroidUtilities.dp(43.0f)) goto L127;
        this.translationAnimationStarted = true;
        this.translationDrawable.setProgress(0.0f);
        this.translationDrawable.setCallback(this);
        this.translationDrawable.start();
    L121:
        this.lastDrawTranslationDrawable = this.translationDrawable;
        this.lastDrawSwipeMessageStringId = r4;
        goto L123
    L77:
        if (this.currentDialogFolderId == 0) goto L83;
        if (this.archiveHidden == false) goto L81;
        r03 = Theme.getColor(Theme.key_chats_archivePinBackground, this.resourcesProvider);
        r6 = Theme.getColor(Theme.key_chats_archiveBackground, this.resourcesProvider);
        r4 = R.string.UnhideFromTop;
        r72 = LocaleController.getString(r4);
        this.translationDrawable = Theme.dialogs_unpinArchiveDrawable;
        goto L75
    L81:
        r03 = Theme.getColor(Theme.key_chats_archiveBackground, this.resourcesProvider);
        r6 = Theme.getColor(Theme.key_chats_archivePinBackground, this.resourcesProvider);
        r4 = R.string.HideOnTop;
        r72 = LocaleController.getString(r4);
        this.translationDrawable = Theme.dialogs_pinArchiveDrawable;
        goto L75
    L83:
        if (this.promoDialog == false) goto L86;
        r03 = Theme.getColor(Theme.key_chats_archiveBackground, this.resourcesProvider);
        r6 = Theme.getColor(Theme.key_chats_archivePinBackground, this.resourcesProvider);
        r4 = R.string.PsaHide;
        r72 = LocaleController.getString(r4);
        this.translationDrawable = Theme.dialogs_hidePsaDrawable;
        goto L75
    L86:
        if (this.folderId != 0) goto L115;
        r03 = Theme.getColor(Theme.key_chats_archiveBackground, this.resourcesProvider);
        r6 = Theme.getColor(Theme.key_chats_archivePinBackground, this.resourcesProvider);
        if (ChatObject.isCommunity(this.chat) == false) goto L91;
        r4 = R.string.SwipeUngroupCommunity;
        r72 = LocaleController.getString(r4);
        r03 = Theme.getColor(Theme.key_dialogSwipeRemove, this.resourcesProvider);
        this.translationDrawable = Theme.dialogs_swipeCommunityUngroup;
        r6 = r03;
        goto L75
    L91:
        if (SharedConfig.getChatSwipeAction(this.currentAccount) != 3) goto L97;
        if (this.dialogMuted == false) goto L95;
        r4 = R.string.SwipeUnmute;
        r72 = LocaleController.getString(r4);
        this.translationDrawable = Theme.dialogs_swipeUnmuteDrawable;
        goto L75
    L95:
        r4 = R.string.SwipeMute;
        r72 = LocaleController.getString(r4);
        this.translationDrawable = Theme.dialogs_swipeMuteDrawable;
        goto L75
    L97:
        if (SharedConfig.getChatSwipeAction(this.currentAccount) != 4) goto L100;
        r4 = R.string.SwipeDeleteChat;
        r72 = LocaleController.getString(r4);
        r03 = Theme.getColor(Theme.key_dialogSwipeRemove, this.resourcesProvider);
        this.translationDrawable = Theme.dialogs_swipeDeleteDrawable;
        goto L75
    L100:
        if (SharedConfig.getChatSwipeAction(this.currentAccount) != 1) goto L109;
        if (this.unreadCount <= 0) goto L104;
    L107:
        r4 = R.string.SwipeMarkAsRead;
        r72 = LocaleController.getString(r4);
        this.translationDrawable = Theme.dialogs_swipeReadDrawable;
        goto L75
    L104:
        if (this.markUnread == true) goto L107;
        r4 = R.string.SwipeMarkAsUnread;
        r72 = LocaleController.getString(r4);
        this.translationDrawable = Theme.dialogs_swipeUnreadDrawable;
        goto L75
    L109:
        if (SharedConfig.getChatSwipeAction(this.currentAccount) == 0) goto L111;
        r4 = R.string.Archive;
        r72 = LocaleController.getString(r4);
        this.translationDrawable = Theme.dialogs_archiveDrawable;
        goto L75
    L111:
        if (getIsPinned() == false) goto L113;
        r4 = R.string.SwipeUnpin;
        r72 = LocaleController.getString(r4);
        this.translationDrawable = Theme.dialogs_swipeUnpinDrawable;
        goto L75
    L113:
        r4 = R.string.SwipePin;
        r72 = LocaleController.getString(r4);
        this.translationDrawable = Theme.dialogs_swipePinDrawable;
        goto L75
    L115:
        r03 = Theme.getColor(Theme.key_chats_archivePinBackground, this.resourcesProvider);
        r6 = Theme.getColor(Theme.key_chats_archiveBackground, this.resourcesProvider);
        r4 = R.string.Unarchive;
        r72 = LocaleController.getString(r4);
        this.translationDrawable = Theme.dialogs_unarchiveDrawable;
        goto L75
    L66:
        if (this.cornerProgress != 0.0f) goto L72;
        RLottieDrawable r069 = this.translationDrawable;
        if (r069 == null) goto L71;
        r069.stop();
        this.translationDrawable.setProgress(0.0f);
        this.translationDrawable.setCallback(null);
        this.translationDrawable = null;
        this.translationAnimationStarted = false;
    L71:
        r21 = 1.0f;
        r33 = 0;
        r38 = 2;
        goto L168
    L55:
        r17 = 1.0f;
        goto L56
    L31:
        if (this.currentDialogFolderId != 0) goto L39;
        if (this.isTopic == false) goto L51;
        TLRPC.TL_forumTopic r070 = this.forumTopic;
        if (r070 == null) goto L51;
        if (r070.id != 1) goto L51;
    L39:
        if (this.archivedChatsDrawable == null) goto L51;
        if (this.translationX != 0.0f) goto L51;
        DialogsActivity r071 = this.parentFragment;
        if (r071 == null) goto L51;
        if (r071.hasHiddenArchive() == false) goto L51;
        RightSlidingDialogContainer r072 = this.parentFragment.rightSlidingDialogContainer;
        if (r072 != null) goto L49;
    L50:
        r16 = true;
        goto L52
    L49:
        if (r072.hasFragment() == true) goto L51;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
        super.onInitializeAccessibilityNodeInfo(r4);
        if (isFolderCell() == false) goto L11;
        PullForegroundDrawable r0 = this.archivedChatsDrawable;
        if (r0 == null) goto L11;
        if (SharedConfig.archiveHidden == false) goto L11;
        if (r0.getPullProgress() != 0.0f) goto L11;
        r4.setVisibleToUser(false);
    L16:
        CheckBox2 r3 = this.checkBox;
        if (r3 != null) goto L19;
        return;
    L19:
        if (r3.isChecked() == false) goto L23;
        r4.setClassName("android.widget.CheckBox");
        r4.setCheckable(true);
        r4.setChecked(true);
        return;
    L23:
        return;
    L11:
        r4.addAction(16);
        r4.addAction(32);
        if (isFolderCell() == true) goto L16;
        if (this.parentFragment == null) goto L16;
        r4.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.acc_action_chat_preview, LocaleController.getString(R.string.AccActionChatPreview)));
        goto L16
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        if (this.rightFragmentOpenedProgress != 0.0f) goto L13;
        if (this.isTopic == true) goto L13;
        if (this.isShareToStoryCell == true) goto L13;
        if (this.storyParams.checkOnTouchEvent(r3, this) == false) goto L13;
        return true;
    L13:
        return super.onInterceptTouchEvent(r3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        if (this.currentDialogId == 0) goto L5;
    L7:
        View r52 = this.emojiStatusView;
        if (r52 == null) goto L10;
        r52.layout(0, 0, AndroidUtilities.dp(22.0f), AndroidUtilities.dp(22.0f));
    L10:
        View r53 = this.botVerificationView;
        if (r53 == null) goto L14;
        r53.layout(0, 0, AndroidUtilities.dp(17.0f), AndroidUtilities.dp(17.0f));
    L14:
        if (this.checkBox == null) goto L37;
        int r54 = this.messagePaddingStart;
        if (this.useForceThreeLines == false) goto L18;
    L21:
        int r92 = 26;
    L22:
        int r55 = AndroidUtilities.dp(r54 - r92);
        if (this.inPreviewMode == false) goto L26;
        r55 = AndroidUtilities.dp(8.0f);
        int r62 = (getMeasuredHeight() - this.checkBox.getMeasuredHeight()) >> 1;
    L36:
        CheckBox2 r82 = this.checkBox;
        r82.layout(r55, r62, r82.getMeasuredWidth() + r55, this.checkBox.getMeasuredHeight() + r62);
        goto L37
    L26:
        if (LocaleController.isRTL == false) goto L28;
        r55 = (r8 - r6) - r55;
    L28:
        float r63 = this.chekBoxPaddingTop;
        if (this.useForceThreeLines == false) goto L31;
    L34:
        int r83 = 4;
    L35:
        r62 = AndroidUtilities.dp(r63 + r83);
        goto L36
    L31:
        if (SharedConfig.useThreeLinesLayout == true) goto L34;
        r83 = 0;
        goto L35
    L18:
        if (SharedConfig.useThreeLinesLayout == true) goto L21;
        r92 = 28;
    L37:
        int r56 = (getMeasuredHeight() + getMeasuredWidth()) << 16;
        if (r56 == this.lastSize) goto L40;
    L43:
        this.updateLayout = false;
        this.lastSize = r56;
        buildLayout();     // Catch: Exception -> L46
        return;
    L46:
        e = move-exception;
        FileLog.e(e);
        return;
    L40:
        if (this.updateLayout == true) goto L43;
        return;
    L5:
        if (this.customDialog != null) goto L7;
    }

    @Override // android.view.View
    public void onMeasure(int r4, int r5) {
        View r52 = this.emojiStatusView;
        if (r52 == null) goto L5;
        r52.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(22.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(22.0f), TLObject.FLAG_30));
    L5:
        View r53 = this.botVerificationView;
        if (r53 == null) goto L8;
        r53.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(17.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(17.0f), TLObject.FLAG_30));
    L8:
        CheckBox2 r54 = this.checkBox;
        if (r54 == null) goto L12;
        r54.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30));
    L12:
        if (this.isTopic == false) goto L34;
        int r55 = View.MeasureSpec.getSize(r4);
        if (this.useForceThreeLines == false) goto L16;
    L19:
        int r1 = this.heightThreeLines;
    L21:
        if (hasTags() == true) goto L23;
    L32:
        int r2 = 0;
    L33:
        setMeasuredDimension(r55, AndroidUtilities.dp(r1 + r2) + (this.useSeparator ? 1 : 0));
        checkTwoLinesForName();
        goto L34
    L23:
        if (this.useForceThreeLines == true) goto L27;
        if (SharedConfig.useThreeLinesLayout == true) goto L27;
    L29:
        if (isForumCell() == false) goto L31;
        r2 = this.addForumHeightForTags;
        goto L33
    L31:
        r2 = this.addHeightForTags;
    L27:
        if (isForumCell() == false) goto L32;
    L16:
        if (SharedConfig.useThreeLinesLayout == true) goto L19;
        r1 = this.heightDefault;
    L34:
        setMeasuredDimension(View.MeasureSpec.getSize(r4), computeHeight());
        this.topClip = 0;
        this.bottomClip = getMeasuredHeight();
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent r8) {
        super.onPopulateAccessibilityEvent(r8);
        StringBuilder r0 = new StringBuilder();
        String r1 = this.titleOverride;
        if (r1 == null) goto L6;
        r0.append(r1);
        r0.append(". ");
    L40:
        if (this.drawVerified == false) goto L43;
        r0.append(LocaleController.getString(R.string.AccDescrVerified));
        r0.append(". ");
    L43:
        if (this.dialogMuted == false) goto L46;
        r0.append(LocaleController.getString(R.string.AccDescrNotificationsMuted));
        r0.append(". ");
    L46:
        if (isOnline() == false) goto L48;
        r0.append(LocaleController.getString(R.string.AccDescrUserOnline));
        r0.append(". ");
    L48:
        int r12 = this.unreadCount;
        if (r12 <= 0) goto L51;
        r0.append(LocaleController.formatPluralString("NewMessages", r12, new Object[0]));
        r0.append(". ");
    L51:
        int r13 = this.mentionCount;
        if (r13 <= 0) goto L55;
        r0.append(LocaleController.formatPluralString("AccDescrMentionCount", r13, new Object[0]));
        r0.append(". ");
    L55:
        if (this.reactionMentionCount <= 0) goto L57;
        r0.append(LocaleController.getString(R.string.AccDescrMentionReaction));
        r0.append(". ");
    L57:
        MessageObject r14 = this.message;
        if (r14 != null) goto L60;
    L113:
        r8.setContentDescription(r0);
        setContentDescription(r0);
        return;
    L60:
        if (this.currentDialogFolderId != 0) goto L113;
        int r5 = this.lastMessageDate;
        if (r5 != 0) goto L65;
        r5 = r14.messageOwner.date;
    L65:
        String r15 = LocaleController.formatDateAudio(r5, true);
        if (this.message.isOut() == false) goto L68;
        r0.append(LocaleController.formatString("AccDescrSentDate", R.string.AccDescrSentDate, new Object[]{r15}));
    L69:
        r0.append(". ");
        if (this.chat == null) goto L81;
        if (this.message.isOut() == true) goto L81;
        if (this.message.isFromUser() == false) goto L81;
        if (this.message.messageOwner.action != null) goto L81;
        TLRPC.User r16 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.message.messageOwner.from_id.user_id));
        if (r16 == null) goto L81;
        r0.append(ContactsController.formatName(r16.first_name, r16.last_name));
        r0.append(". ");
    L81:
        if (this.encryptedChat != null) goto L111;
        StringBuilder r17 = new StringBuilder();
        r17.append(this.message.messageText);
        if (this.message.isMediaEmpty() == true) goto L92;
        MessageObject r2 = getCaptionMessage();
        if (r2 == null) goto L92;
        if (TextUtils.isEmpty(r2.caption) == true) goto L92;
        if (r17.length() <= 0) goto L91;
        r17.append(". ");
    L91:
        r17.append(r2.caption);
    L92:
        StaticLayout r22 = this.messageLayout;
        if (r22 != null) goto L95;
        int r23 = -1;
    L96:
        if (r23 <= 0) goto L110;
        int r3 = r17.length();
        int r52 = r17.indexOf("\n", r23);
        if (r52 >= r3) goto L101;
        if (r52 < 0) goto L101;
        r3 = r52;
    L101:
        int r53 = r17.indexOf("\t", r23);
        if (r53 >= r3) goto L105;
        if (r53 < 0) goto L105;
        r3 = r53;
    L105:
        int r24 = r17.indexOf(" ", r23);
        if (r24 >= r3) goto L109;
        if (r24 < 0) goto L109;
        r3 = r24;
    L109:
        r0.append(r17.substring(0, r3));
        goto L111
    L110:
        r0.append(r17);
        goto L111
    L95:
        r23 = r22.getText().length();
    L111:
        r8.setContentDescription(r0);
        setContentDescription(r0);
        return;
    L68:
        r0.append(LocaleController.formatString("AccDescrReceivedDate", R.string.AccDescrReceivedDate, new Object[]{r15}));
        goto L69
    L6:
        if (this.currentDialogFolderId != 1) goto L9;
        r0.append(LocaleController.getString(R.string.ArchivedChats));
        r0.append(". ");
        goto L40
    L9:
        if (this.encryptedChat == null) goto L12;
        r0.append(LocaleController.getString(R.string.AccDescrSecretChat));
        r0.append(". ");
    L12:
        if (this.isTopic == true) goto L14;
    L16:
        TLRPC.User r18 = this.user;
        if (r18 != null) goto L19;
        TLRPC.Chat r19 = this.chat;
        if (r19 == null) goto L40;
        if (r19.broadcast == false) goto L37;
        r0.append(LocaleController.getString(R.string.AccDescrChannel));
    L38:
        r0.append(". ");
        r0.append(this.chat.title);
        r0.append(". ");
        goto L40
    L37:
        r0.append(LocaleController.getString(R.string.AccDescrGroup));
        goto L38
    L19:
        if (UserObject.isReplyUser(r18) == false) goto L22;
        r0.append(LocaleController.getString(R.string.RepliesTitle));
    L31:
        r0.append(". ");
        goto L40
    L22:
        if (UserObject.isAnonymous(this.user) == false) goto L25;
        r0.append(LocaleController.getString(R.string.AnonymousForward));
        goto L31
    L25:
        if (this.user.bot == false) goto L27;
        r0.append(LocaleController.getString(R.string.Bot));
        r0.append(". ");
    L27:
        TLRPC.User r110 = this.user;
        if (r110.self == false) goto L30;
        r0.append(LocaleController.getString(R.string.SavedMessages));
        goto L31
    L30:
        r0.append(ContactsController.formatName(r110.first_name, r110.last_name));
        goto L31
    L14:
        if (this.forumTopic == null) goto L16;
        r0.append(LocaleController.getString(R.string.AccDescrTopic));
        r0.append(". ");
        r0.append(this.forumTopic.title);
        r0.append(". ");
        goto L40
    }

    public void onReorderStateChanged(boolean r3, boolean r4) {
        if (getIsPinned() == true) goto L6;
        if (r3 == false) goto L6;
    L8:
        if (getIsPinned() == true) goto L22;
        this.drawReorder = false;
        return;
    L22:
        return;
    L6:
        if (this.drawReorder == r3) goto L8;
        this.drawReorder = r3;
        float r0 = 1.0f;
        if (r4 == false) goto L16;
        if (r3 == false) goto L15;
        r0 = 0.0f;
    L15:
        this.reorderIconProgress = r0;
    L20:
        invalidate();
        return;
    L16:
        if (r3 == true) goto L19;
        r0 = 0.0f;
    L19:
        this.reorderIconProgress = r0;
        goto L20
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r6) {
        if (this.rightFragmentOpenedProgress == 0.0f) goto L5;
    L11:
        DialogCellDelegate r0 = this.delegate;
        if (r0 == null) goto L16;
        if (r0.canClickButtonInside() == true) goto L16;
    L56:
        return super.onTouchEvent(r6);
    L16:
        if (this.openBot == false) goto L41;
        boolean r02 = this.openButtonRect.contains(r6.getX(), r6.getY());
        if (r6.getAction() != 0) goto L20;
    L37:
        this.openButtonBounce.setPressed(r02);
    L38:
        if (r02 == false) goto L41;
        return true;
    L20:
        if (r6.getAction() == 2) goto L37;
        if (this.openButtonBounce.isPressed() == false) goto L32;
        if (r6.getAction() != 1) goto L32;
        Utilities.Callback<TLRPC.User> r62 = this.onOpenButtonClick;
        if (r62 == null) goto L29;
        r62.run(this.user);
    L29:
        this.openButtonBounce.setPressed(false);
        return true;
    L32:
        if (this.openButtonBounce.isPressed() == false) goto L38;
        if (r6.getAction() != 3) goto L38;
        this.openButtonBounce.setPressed(false);
        return true;
    L41:
        if (this.lastTopicMessageUnread == false) goto L56;
        CanvasButton r03 = this.canvasButton;
        if (r03 == null) goto L56;
        if (this.buttonLayout == null) goto L56;
        int r2 = this.dialogsType;
        if (r2 == 0) goto L53;
        if (r2 == 7) goto L53;
        if (r2 != 8) goto L56;
    L53:
        if (r03.checkTouchEvent(r6) == false) goto L56;
        return true;
    L5:
        if (this.isTopic == true) goto L11;
        if (this.isShareToStoryCell == true) goto L11;
        if (this.storyParams.checkOnTouchEvent(r6, this) == false) goto L11;
        return true;
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int r2, Bundle r3) {
        if (r2 != R.id.acc_action_chat_preview) goto L9;
        DialogsActivity r0 = this.parentFragment;
        if (r0 == null) goto L9;
        r0.showChatPreview(this);
        return true;
    L9:
        return super.performAccessibilityAction(r2, r3);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (getParent() != null) goto L5;
    L7:
        super.requestLayout();
        return;
    L5:
        if (getParent().isLayoutRequested() == false) goto L7;
        getParent().requestLayout();
        goto L7
    }

    public void resetPinnedArchiveState() {
        boolean r0 = SharedConfig.archiveHidden;
        this.archiveHidden = r0;
        float r1 = 1.0f;
        if (r0 == false) goto L5;
        float r02 = 0.0f;
    L6:
        this.archiveBackgroundProgress = r02;
        this.avatarDrawable.setArchivedAvatarHiddenProgress(r02);
        this.clipProgress = 0.0f;
        this.isSliding = false;
        if (getIsPinned() == true) goto L9;
    L11:
        r1 = 0.0f;
    L12:
        this.reorderIconProgress = r1;
        this.attachedToWindow = true;
        this.cornerProgress = 0.0f;
        setTranslationX(0.0f);
        setTranslationY(0.0f);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r03 = this.emojiStatus;
        if (r03 != null) goto L15;
    L17:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r04 = this.botVerification;
        if (r04 != null) goto L20;
        return;
    L20:
        if (this.attachedToWindow == false) goto L24;
        r04.attach();
        return;
    L24:
        return;
    L15:
        if (this.attachedToWindow == false) goto L17;
        r03.attach();
        goto L17
    L9:
        if (this.drawReorder == false) goto L11;
    L5:
        r02 = 1.0f;
        goto L6
    }

    public boolean separateMessageNameLine() {
        if (this.useForceThreeLines == true) goto L7;
        if (SharedConfig.useThreeLinesLayout == true) goto L7;
        return false;
    L7:
        if (hasTags() == true) goto L12;
        return true;
    L12:
        return false;
    }

    public void setArchivedPullAnimation(PullForegroundDrawable r1) {
        this.archivedChatsDrawable = r1;
    }

    public void setBottomClip(int r1) {
        this.bottomClip = r1;
    }

    public void setChecked(boolean r5, boolean r6) {
        CheckBox2 r0 = this.checkBox;
        if (r0 != null) goto L6;
        if (r5 == true) goto L6;
        return;
    L6:
        if (r0 != null) goto L8;
        AnonymousClass4 r02 = new AnonymousClass4(this, getContext(), 21, this.resourcesProvider);
        this.checkBox = r02;
        r02.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
        this.checkBox.setDrawUnchecked(false);
        this.checkBox.setDrawBackgroundAsArc(3);
        addView(this.checkBox);
    L8:
        this.checkBox.setChecked(r5, r6);
        checkTtl();
    }

    public void setClipProgress(float r1) {
        this.clipProgress = r1;
        invalidate();
    }

    public void setCurrentDialogId(long r1) {
        this.currentDialogId = r1;
    }

    public void setCustomMessage(String r2) {
        if (TextUtils.equals(this.customMessage, r2) == true) goto L6;
        this.customMessage = r2;
        buildLayout();
        requestLayout();
        return;
    }

    public void setCustomMessageWithoutRebuild(String r1) {
        this.customMessage = r1;
    }

    public void setDialog(long r3, MessageObject r5, int r6, boolean r7, boolean r8) {
        if (this.currentDialogId == r3) goto L5;
        this.lastStatusDrawableParams = -1;
    L5:
        this.currentDialogId = r3;
        this.lastDialogChangedTime = System.currentTimeMillis();
        this.message = r5;
        this.useMeForMyMessages = r7;
        this.isDialogCell = false;
        this.lastMessageDate = r6;
        if (r5 == null) goto L8;
        int r4 = r5.messageOwner.edit_date;
    L9:
        this.currentEditDate = r4;
        this.unreadCount = 0;
        this.markUnread = false;
        if (r5 == null) goto L12;
        int r42 = r5.getId();
    L13:
        this.messageId = r42;
        this.mentionCount = 0;
        this.reactionMentionCount = 0;
        this.pollVotesMentionCount = 0;
        if (r5 != null) goto L16;
    L18:
        boolean r43 = false;
    L19:
        this.lastUnreadState = r43;
        MessageObject r44 = this.message;
        if (r44 == null) goto L22;
        this.lastSendState = r44.messageOwner.send_state;
    L22:
        update(0, r8);
        return;
    L16:
        if (r5.isUnread() == false) goto L18;
        r43 = true;
        goto L19
    L12:
        r42 = 0;
        goto L13
    L8:
        r4 = 0;
        goto L9
    }

    public void setDialog(long r3, MessageObject r5, ArrayList<MessageObject> r6, int r7, boolean r8, boolean r9) {
        if (this.currentDialogId == r3) goto L5;
        this.lastStatusDrawableParams = -1;
    L5:
        this.currentDialogId = r3;
        this.lastDialogChangedTime = System.currentTimeMillis();
        this.message = r5;
        this.useMeForMyMessages = r8;
        this.isDialogCell = false;
        this.lastMessageDate = r7;
        if (r5 == null) goto L8;
        int r4 = r5.messageOwner.edit_date;
    L9:
        this.currentEditDate = r4;
        this.unreadCount = 0;
        this.markUnread = false;
        if (r5 == null) goto L12;
        int r42 = r5.getId();
    L13:
        this.messageId = r42;
        this.mentionCount = 0;
        this.reactionMentionCount = 0;
        this.pollVotesMentionCount = 0;
        if (r5 != null) goto L16;
    L18:
        boolean r43 = false;
    L19:
        this.lastUnreadState = r43;
        this.groupMessages = r6;
        MessageObject r44 = this.message;
        if (r44 == null) goto L22;
        this.lastSendState = r44.messageOwner.send_state;
    L22:
        update(0, r9);
        return;
    L16:
        if (r5.isUnread() == false) goto L18;
        r43 = true;
        goto L19
    L12:
        r42 = 0;
        goto L13
    L8:
        r4 = 0;
        goto L9
    }

    public void setDialog(TLRPC.Dialog r5, int r6, int r7) {
        if (this.currentDialogId == r5.id) goto L8;
        ValueAnimator r0 = this.statusDrawableAnimator;
        if (r0 == null) goto L7;
        r0.removeAllListeners();
        this.statusDrawableAnimator.cancel();
    L7:
        this.statusDrawableAnimationInProgress = false;
        this.lastStatusDrawableParams = -1;
    L8:
        this.currentDialogId = r5.id;
        this.lastDialogChangedTime = System.currentTimeMillis();
        boolean r02 = true;
        this.isDialogCell = true;
        if ((r5 instanceof TLRPC.TL_dialogCommunity) == false) goto L11;
        this.currentDialogCommunityId = r5.community_id;
    L13:
        if ((r5 instanceof TLRPC.TL_dialogFolder) == false) goto L17;
        this.currentDialogFolderId = ((TLRPC.TL_dialogFolder) r5).folder.id;
        PullForegroundDrawable r52 = this.archivedChatsDrawable;
        if (r52 == null) goto L18;
        r52.setCell(this);
    L18:
        this.dialogsType = r6;
        if (r6 == 3) goto L22;
        r02 = false;
    L22:
        showPremiumBlocked(r02);
        if (this.tags != null) goto L25;
        this.tags = new DialogCellTags(this);
    L25:
        this.folderId = r7;
        this.messageId = 0;
        if (update(0, false) == false) goto L28;
        requestLayout();
    L28:
        checkOnline();
        checkGroupCall();
        checkChatTheme();
        checkTtl();
        return;
    L17:
        this.currentDialogFolderId = 0;
        goto L18
    L11:
        this.currentDialogCommunityId = 0;
        goto L13
    }

    public void setDialog(CustomDialog r1) {
        this.customDialog = r1;
        this.messageId = 0;
        update(0);
        checkOnline();
        checkGroupCall();
        checkChatTheme();
        checkTtl();
    }

    public void setDialogCellDelegate(DialogCellDelegate r1) {
        this.delegate = r1;
    }

    public void setDialogSelected(boolean r2) {
        if (this.isSelected == r2) goto L5;
        invalidate();
    L5:
        this.isSelected = r2;
    }

    public void setForumTopic(TLRPC.TL_forumTopic r6, long r7, MessageObject r9, boolean r10, boolean r11) {
        this.forumTopic = r6;
        if (r6 == null) goto L5;
        boolean r2 = true;
    L6:
        this.isTopic = r2;
        if (this.currentDialogId == r7) goto L9;
        this.lastStatusDrawableParams = -1;
    L9:
        if (r9 == null) goto L13;
        Drawable r22 = r9.topicIconDrawable[0];
        if ((r22 instanceof ForumBubbleDrawable) == false) goto L13;
        ((ForumBubbleDrawable) r22).setColor(r6.icon_color);
    L13:
        this.currentDialogId = r7;
        this.lastDialogChangedTime = System.currentTimeMillis();
        this.message = r9;
        this.isDialogCell = false;
        this.showTopicIconInName = r10;
        if (r9 == null) goto L16;
        TLRPC.Message r72 = r9.messageOwner;
        this.lastMessageDate = r72.date;
        this.currentEditDate = r72.edit_date;
        this.markUnread = false;
        this.messageId = r9.getId();
        this.lastUnreadState = r9.isUnread();
    L16:
        MessageObject r73 = this.message;
        if (r73 == null) goto L19;
        this.lastSendState = r73.messageOwner.send_state;
    L19:
        if (r11 == true) goto L21;
        this.lastStatusDrawableParams = -1;
    L21:
        if (r6 == null) goto L23;
        this.groupMessages = r6.groupedMessages;
    L23:
        TLRPC.TL_forumTopic r62 = this.forumTopic;
        if (r62 != null) goto L26;
    L30:
        update(0, r11);
        return;
    L26:
        if (r62.id != 1) goto L30;
        PullForegroundDrawable r63 = this.archivedChatsDrawable;
        if (r63 == null) goto L30;
        r63.setCell(this);
        goto L30
    L5:
        r2 = false;
        goto L6
    }

    public void setIsShareToStoryCell() {
        this.repostStoryDrawable = new ShareDialogCell.RepostStoryDrawable(getContext(), this, R.drawable.forward_to_stories, this.resourcesProvider);
        this.isShareToStoryCell = true;
    }

    public void setIsTransitionSupport(boolean r1) {
        this.isTransitionSupport = r1;
    }

    public void setMoving(boolean r1) {
        this.moving = r1;
    }

    public void setOpenBotButton(boolean r5) {
        if (this.openBot != r5) goto L6;
        return;
    L6:
        if (this.openButtonText != null) goto L8;
        this.openButtonText = new Text(LocaleController.getString(R.string.BotOpen), 14.0f, AndroidUtilities.bold());
    L8:
        this.openBot = r5;
        this.openButtonBounce.setPressed(false);
    }

    public void setPinForced(boolean r1) {
        this.drawPinForced = r1;
        if (getMeasuredWidth() > 0) goto L5;
    L7:
        invalidate();
        return;
    L5:
        if (getMeasuredHeight() <= 0) goto L7;
        buildLayout();
        goto L7
    }

    public void setPreloader(DialogsAdapter.DialogsPreloader r1) {
        this.preloader = r1;
    }

    public void setRightFragmentOpenedProgress(float r2) {
        if (this.rightFragmentOpenedProgress == r2) goto L6;
        this.rightFragmentOpenedProgress = r2;
        invalidate();
        return;
    }

    public void setSliding(boolean r1) {
        this.isSliding = r1;
    }

    public void setTitleOverride(String r1) {
        this.titleOverride = r1;
    }

    public void setTopClip(int r1) {
        this.topClip = r1;
    }

    @Override // android.view.View
    public void setTranslationX(float r6) {
        if (r6 != this.translationX) goto L5;
        return;
    L5:
        this.translationX = r6;
        RLottieDrawable r0 = this.translationDrawable;
        boolean r1 = false;
        if (r0 != null) goto L8;
    L10:
        float r62 = this.translationX;
        if (r62 == 0.0f) goto L13;
        this.isSliding = true;
    L15:
        if (this.isSliding == true) goto L17;
    L27:
        invalidate();
        return;
    L17:
        if (this.swipeCanceled == true) goto L27;
        boolean r02 = this.drawRevealBackground;
        if (Math.abs(r62) < (getMeasuredWidth() * 0.45f)) goto L21;
        r1 = true;
    L21:
        this.drawRevealBackground = r1;
        if (r02 == r1) goto L27;
        if (this.archiveHidden != SharedConfig.archiveHidden) goto L27;
        performHapticFeedback(3, 2);     // Catch: Exception -> L29
        goto L27
    L13:
        this.currentRevealBounceProgress = 0.0f;
        this.currentRevealProgress = 0.0f;
        this.drawRevealBackground = false;
        goto L15
    L8:
        if (r6 != 0.0f) goto L10;
        r0.setProgress(0.0f);
        this.translationAnimationStarted = false;
        this.archiveHidden = SharedConfig.archiveHidden;
        this.currentRevealProgress = 0.0f;
        this.isSliding = false;
        goto L10
    }

    public void setVisible(boolean r2) {
        if (this.visibleOnScreen == r2) goto L10;
        this.visibleOnScreen = r2;
        if (r2 == false) goto L9;
        invalidate();
        return;
    L9:
        return;
    }

    public void showPremiumBlocked(boolean r3) {
        Runnable r0 = this.unsubscribePremiumBlocked;
        if (r0 == null) goto L5;
        boolean r1 = true;
    L6:
        if (r3 == r1) goto L14;
        if (r3 == true) goto L11;
        if (r0 == null) goto L11;
        r0.run();
        this.unsubscribePremiumBlocked = null;
        return;
    L11:
        if (r3 == false) goto L15;
        this.unsubscribePremiumBlocked = NotificationCenter.getInstance(this.currentAccount).listen(this, NotificationCenter.userIsPremiumBlockedUpadted, new DialogCell$$ExternalSyntheticLambda5(this));
        return;
    L15:
        return;
    L14:
        return;
    L5:
        r1 = false;
        goto L6
    }

    public void startOutAnimation() {
        PullForegroundDrawable r0 = this.archivedChatsDrawable;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.isTopic == false) goto L7;
        r0.outCy = AndroidUtilities.dp(24.0f);
        this.archivedChatsDrawable.outCx = AndroidUtilities.dp(24.0f);
        PullForegroundDrawable r02 = this.archivedChatsDrawable;
        r02.outRadius = 0.0f;
        r02.outImageSize = 0.0f;
    L11:
        this.archivedChatsDrawable.startOutAnimation();
        return;
    L7:
        r0.outCy = this.storyParams.originalAvatarRect.centerY();
        this.archivedChatsDrawable.outCx = this.storyParams.originalAvatarRect.centerX();
        this.archivedChatsDrawable.outRadius = this.storyParams.originalAvatarRect.width() / 2.0f;
        if (MessagesController.getInstance(this.currentAccount).getStoriesController().hasHiddenStories() == false) goto L10;
        this.archivedChatsDrawable.outRadius -= AndroidUtilities.dpf2(3.5f);
    L10:
        this.archivedChatsDrawable.outImageSize = this.avatarImage.getBitmapWidth();
        goto L11
    }

    public boolean update(int r2) {
        return update(r2, true);
    }

    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [org.telegram.tgnet.TLRPC$Chat, org.telegram.tgnet.TLRPC$EncryptedChat, org.telegram.tgnet.TLRPC$User] */
    /* JADX WARN: Type inference failed for: r7v68 */
    public boolean update(int r37, boolean r38) {
        boolean r2 = isForumCell();
        boolean r3 = false;
        this.drawAvatarSelector = false;
        this.ttlPeriod = 0;
        CustomDialog r4 = this.customDialog;
        ?? r7 = 0;
        if (r4 == null) goto L17;
        this.lastMessageDate = r4.date;
        int r9 = r4.unread_count;
        if (r9 == 0) goto L7;
        boolean r10 = true;
    L8:
        this.lastUnreadState = r10;
        this.unreadCount = r9;
        this.drawPin = r4.pinned;
        this.dialogMuted = r4.muted;
        this.hasUnmutedTopics = false;
        this.hasUnmutedCommunityDialogs = false;
        this.avatarDrawable.setInfo(r4.id, r4.name, null);
        boolean r42 = this.isShareToStoryCell;
        ImageReceiver r92 = this.avatarImage;
        if (r42 == false) goto L11;
        r92.setImage(null, "50_50", this.repostStoryDrawable, null, 0);
    L12:
        int r43 = 0;
    L13:
        ImageReceiver[] r93 = this.thumbImage;
        if (r43 >= r93.length) goto L16;
        r93[r43].setImageBitmap(null);
        r43 = r43 + 1;
        goto L13
    L16:
        this.drawMonoforumAvatar = false;
        this.drawCommunityAvatar = false;
        setAvatarRadius(ExteraConfig.getAvatarCorners(getAvatarSizeDp()));
        this.drawUnmute = false;
        boolean r5 = false;
        boolean r6 = false;
        boolean r15 = false;
        float r44 = 0.0f;
    L525:
        if (this.isTopic == false) goto L527;
    L531:
        if (r3 == false) goto L533;
    L543:
        if (r38 == true) goto L553;
        if (this.dialogMuted == false) goto L547;
    L548:
        r44 = 1.0f;
    L549:
        this.dialogMutedProgress = r44;
        ValueAnimator r45 = this.countAnimator;
        if (r45 == null) goto L553;
        r45.cancel();
        goto L553
    L547:
        if (this.drawUnmute == false) goto L549;
    L553:
        if (isForumCell() == r2) goto L555;
        boolean r46 = true;
    L556:
        if (r6 == true) goto L558;
    L561:
        if (r3 == true) goto L563;
        if (r6 == true) goto L563;
    L564:
        updatePremiumBlocked(r38);
        return r46;
    L563:
        invalidate();
        goto L564
    L558:
        if (this.attachedToWindow == false) goto L560;
        buildLayout();
        goto L561
    L560:
        this.updateLayout = true;
        goto L561
    L555:
        r46 = r5;
        goto L556
    L533:
        if (this.storyParams.currentState != 0) goto L535;
        boolean r72 = true;
    L537:
        if (StoriesUtilities.getPredictiveUnreadState(MessagesController.getInstance(this.currentAccount).getStoriesController(), getDialogId()) != 0) goto L539;
        r15 = true;
    L539:
        if (r15 == false) goto L542;
        if (r72 == true) goto L543;
        if (r15 == false) goto L543;
    L542:
        r3 = true;
        goto L543
    L535:
        r72 = r15;
        goto L537
    L527:
        if (getMeasuredWidth() == 0) goto L529;
    L530:
        r6 = true;
        goto L531
    L529:
        if (getMeasuredHeight() == 0) goto L531;
    L11:
        r92.setImage(null, "50_50", this.avatarDrawable, null, 0);
        goto L12
    L7:
        r10 = false;
        goto L8
    L17:
        int r47 = this.unreadCount;
        if (this.reactionMentionCount == 0) goto L20;
        boolean r94 = true;
    L21:
        boolean r102 = this.markUnread;
        this.hasUnmutedTopics = false;
        this.hasUnmutedCommunityDialogs = false;
        this.readOutboxMaxId = -1;
        if (this.isDialogCell == false) goto L104;
        TLRPC.Dialog r52 = MessagesController.getInstance(this.currentAccount).dialogs_dict.get(this.currentDialogId);
        if (r52 == null) goto L96;
        this.readOutboxMaxId = r52.read_outbox_max_id;
        this.ttlPeriod = r52.ttl_period;
        if (r37 != 0) goto L95;
        long r17 = 0;
        this.clearingDialog = MessagesController.getInstance(this.currentAccount).isClearingDialog(r52.id);
        ArrayList<MessageObject> r62 = MessagesController.getInstance(this.currentAccount).dialogMessage.get(r52.id);
        this.groupMessages = r62;
        if (r62 != null) goto L30;
    L32:
        MessageObject r63 = null;
    L33:
        this.message = r63;
        if (r63 != null) goto L36;
    L38:
        boolean r64 = false;
    L39:
        this.lastUnreadState = r64;
        TLRPC.Chat r65 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r52.id));
        if (r65 != null) goto L42;
    L44:
        if (r65 == null) goto L57;
        if (r65.forum == false) goto L48;
    L51:
        int[] r11 = MessagesController.getInstance(this.currentAccount).getTopicsController().getForumUnreadCount(r65.id);
        this.unreadCount = r11[0];
        this.mentionCount = r11[1];
        this.reactionMentionCount = r11[2];
        if (r11[3] == 0) goto L54;
        boolean r12 = true;
    L55:
        this.hasUnmutedTopics = r12;
        this.pollVotesMentionCount = r11[4];
    L64:
        if (ChatObject.isMonoForum(r65) == false) goto L66;
        this.mentionCount = 0;
    L66:
        this.markUnread = r52.unread_mark;
        MessageObject r66 = this.message;
        if (r66 == null) goto L69;
        int r67 = r66.messageOwner.edit_date;
    L70:
        this.currentEditDate = r67;
        this.lastMessageDate = r52.last_message_date;
        int r68 = this.dialogsType;
        if (r68 == 7) goto L81;
        if (r68 == 8) goto L81;
        if (this.currentDialogFolderId == 0) goto L77;
    L79:
        boolean r53 = false;
    L80:
        this.drawPin = r53;
    L92:
        MessageObject r54 = this.message;
        if (r54 == null) goto L97;
        this.lastSendState = r54.messageOwner.send_state;
    L97:
        long r55 = this.currentDialogId;
        if (r55 != r17) goto L100;
    L102:
        boolean r56 = false;
    L103:
        this.drawAvatarSelector = r56;
    L105:
        TLRPC.TL_forumTopic r57 = this.forumTopic;
        if (r57 == null) goto L109;
        this.unreadCount = r57.unread_count;
        this.mentionCount = r57.unread_mentions_count;
        this.reactionMentionCount = r57.unread_reactions_count;
        this.pollVotesMentionCount = r57.unread_poll_votes_count;
    L109:
        if (this.dialogsType != 2) goto L111;
        this.drawPin = false;
    L111:
        DialogCellTags r58 = this.tags;
        if (r58 == null) goto L121;
        boolean r59 = r58.isEmpty();
        int r13 = 2;
        char r19 = 4;
        if (this.tags.update(this.currentAccount, this.dialogsType, this.currentDialogId) == true) goto L116;
    L122:
        boolean r510 = false;
        r6 = false;
        boolean r112 = false;
    L123:
        if (r37 == 0) goto L322;
        TLRPC.User r122 = this.user;
        if (r122 == null) goto L136;
        if (MessagesController.isSupportUser(r122) == true) goto L136;
        if (this.user.bot == true) goto L136;
        if ((r37 & MessagesController.UPDATE_MASK_STATUS) == 0) goto L136;
        this.user = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.user.id));
        if (this.wasDrawnOnline == isOnline()) goto L136;
        r112 = true;
    L136:
        if ((r37 & MessagesController.UPDATE_MASK_EMOJI_STATUS) != 0) goto L138;
        int r21 = r47;
        int r20 = r13;
        boolean r32 = false;
    L193:
        if (this.isDialogCell == true) goto L197;
        if (this.isTopic == true) goto L197;
    L207:
        if (r32 == false) goto L209;
    L215:
        if (r32 == false) goto L217;
    L231:
        if (r32 == false) goto L233;
    L244:
        if (r32 == false) goto L246;
    L250:
        if (r32 == false) goto L252;
    L256:
        if (r32 == false) goto L258;
    L262:
        if (r32 == true) goto L308;
        MessageObject r73 = this.message;
        if (r73 == null) goto L269;
        if (this.lastUnreadState == r73.isUnread()) goto L269;
        this.lastUnreadState = this.message.isUnread();
        r32 = true;
    L269:
        if (this.isDialogCell == false) goto L308;
        TLRPC.Dialog r74 = MessagesController.getInstance(this.currentAccount).dialogs_dict.get(this.currentDialogId);
        if (r74 != null) goto L273;
        TLRPC.Chat r8 = null;
    L274:
        if (r8 == null) goto L287;
        if (r8.forum == false) goto L278;
    L281:
        int[] r123 = MessagesController.getInstance(this.currentAccount).getTopicsController().getForumUnreadCount(r8.id);
        int r132 = r123[0];
        int r14 = r123[1];
        int r23 = r123[r20];
        if (r123[3] == 0) goto L284;
        boolean r48 = true;
    L285:
        this.hasUnmutedTopics = r48;
        int r49 = r123[r19];
        int r152 = r132;
        int r133 = r14;
        int r142 = r23;
    L296:
        if (ChatObject.isMonoForum(r8) == false) goto L298;
        r133 = 0;
    L298:
        if (r74 == null) goto L308;
        if (this.unreadCount == r152) goto L302;
    L307:
        this.unreadCount = r152;
        this.mentionCount = r133;
        this.markUnread = r74.unread_mark;
        this.reactionMentionCount = r142;
        this.pollVotesMentionCount = r49;
        boolean r410 = true;
    L309:
        if (r410 == false) goto L311;
    L317:
        if (r410 == true) goto L321;
        if (r112 == false) goto L320;
        invalidate();
    L320:
        return r510;
    L321:
        r7 = 0;
    L323:
        this.user = r7;
        this.chat = r7;
        this.encryptedChat = r7;
        if (this.currentDialogCommunityId == r17) goto L327;
        this.message = MessagesController.getInstance(this.currentAccount).findCommunityLastMessage(this.currentDialogCommunityId);
    L327:
        if (this.currentDialogFolderId == 0) goto L332;
        this.dialogMuted = false;
        this.drawUnmute = false;
        MessageObject r33 = findFolderTopMessage();
        this.message = r33;
        if (r33 == null) goto L331;
        long r75 = r33.getDialogId();
    L347:
        if (r75 == r17) goto L372;
        if (DialogObject.isEncryptedDialog(r75) == false) goto L353;
        TLRPC.EncryptedChat r34 = MessagesController.getInstance(this.currentAccount).getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r75)));
        this.encryptedChat = r34;
        if (r34 == null) goto L365;
        this.user = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.encryptedChat.user_id));
    L365:
        if (this.useMeForMyMessages == false) goto L372;
        if (this.user == null) goto L372;
        if (this.message.isOutOwner() == false) goto L372;
        this.user = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(UserConfig.getInstance(this.currentAccount).clientUserId));
        goto L372
    L353:
        boolean r35 = DialogObject.isUserDialog(r75);
        int r124 = this.currentAccount;
        if (r35 == false) goto L356;
        this.user = MessagesController.getInstance(r124).getUser(Long.valueOf(r75));
        goto L365
    L356:
        TLRPC.Chat r36 = MessagesController.getInstance(r124).getChat(Long.valueOf(-r75));
        this.chat = r36;
        if (this.isDialogCell == true) goto L365;
        if (r36 == null) goto L365;
        if (r36.migrated_to == null) goto L365;
        TLRPC.Chat r39 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.chat.migrated_to.channel_id));
        if (r39 == null) goto L365;
        this.chat = r39;
    L372:
        if (this.insideCommunityList == false) goto L374;
    L378:
        boolean r310 = false;
    L379:
        this.drawCommunityAvatar = r310;
        if (this.currentDialogFolderId == 0) goto L384;
        Theme.dialogs_archiveAvatarDrawable.setCallback(this);
        this.avatarDrawable.setAvatarType(r20);
        this.avatarImage.setImage(null, null, this.avatarDrawable, null, this.user, 0);
    L382:
        boolean r311 = r510;
    L420:
        if (r38 == false) goto L470;
        int r143 = r21;
        if (r143 != this.unreadCount) goto L426;
        if (r102 == this.markUnread) goto L470;
    L426:
        if (this.isDialogCell == false) goto L430;
        int r212 = r143;
        if ((System.currentTimeMillis() - this.lastDialogChangedTime) <= 100) goto L470;
    L431:
        ValueAnimator r125 = this.countAnimator;
        if (r125 == null) goto L434;
        r125.cancel();
    L434:
        ValueAnimator r126 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.countAnimator = r126;
        r126.addUpdateListener(new DialogCell$$ExternalSyntheticLambda0(this));
        this.countAnimator.addListener(new AnonymousClass5(this));
        if (r212 == 0) goto L439;
        if (this.markUnread == true) goto L439;
    L442:
        int r103 = this.unreadCount;
        ValueAnimator r127 = this.countAnimator;
        if (r103 != 0) goto L445;
        r127.setDuration(150);
        this.countAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
    L448:
        if (this.drawCount == true) goto L450;
    L465:
        this.countWidthOld = this.countWidth;
        this.countLeftOld = this.countLeft;
        if (this.unreadCount <= r212) goto L468;
        boolean r153 = true;
    L469:
        this.countAnimationIncrement = r153;
        this.countAnimator.start();
        goto L470
    L468:
        r153 = false;
        goto L469
    L450:
        if (this.drawCount2 == false) goto L465;
        if (this.countLayout == null) goto L465;
        String r104 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r212)});
        String r128 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(this.unreadCount)});
        if (r104.length() != r128.length()) goto L464;
        SpannableStringBuilder r134 = new SpannableStringBuilder(r104);
        SpannableStringBuilder r144 = new SpannableStringBuilder(r128);
        SpannableStringBuilder r154 = new SpannableStringBuilder(r128);
        int r76 = 0;
    L457:
        if (r76 >= r104.length()) goto L463;
        if (r104.charAt(r76) != r128.charAt(r76)) goto L461;
        int r511 = r76 + 1;
        r134.setSpan(new EmptyStubSpan(), r76, r511, 0);
        r144.setSpan(new EmptyStubSpan(), r76, r511, 0);
    L462:
        r76 = r76 + 1;
        goto L457
    L461:
        r154.setSpan(new EmptyStubSpan(), r76, r76 + 1, 0);
        goto L462
    L463:
        int r25 = Math.max(AndroidUtilities.dp(8.0f), (int) Math.ceil(Theme.dialogs_countTextPaint2.measureText(r104)));
        com.exteragram.messenger.utils.ui.TextPaint r24 = Theme.dialogs_countTextPaint2;
        Layout.Alignment r26 = Layout.Alignment.ALIGN_CENTER;
        this.countOldLayout = new StaticLayout(r134, r24, r25, r26, 1.0f, 0.0f, false);
        this.countAnimationStableLayout = new StaticLayout(r154, Theme.dialogs_countTextPaint2, r25, r26, 1.0f, 0.0f, false);
        this.countAnimationInLayout = new StaticLayout(r144, Theme.dialogs_countTextPaint2, r25, r26, 1.0f, 0.0f, false);
        goto L465
    L464:
        this.countOldLayout = this.countLayout;
        goto L465
    L445:
        r127.setDuration(430);
        this.countAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
    L439:
        if (this.markUnread == true) goto L446;
        if (r102 == true) goto L442;
    L446:
        this.countAnimator.setDuration(220);
        this.countAnimator.setInterpolator(new OvershootInterpolator());
        goto L448
    L430:
        r212 = r143;
    L470:
        BoolAnimator r411 = this.animatorPollVotesMentionVisible;
        if (this.pollVotesMentionCount == 0) goto L473;
        boolean r155 = true;
    L474:
        r411.setValue(r155, r38);
        if (this.reactionMentionCount == 0) goto L477;
        boolean r156 = true;
    L478:
        if (r38 == false) goto L488;
        if (r156 == r94) goto L488;
        ValueAnimator r412 = this.reactionsMentionsAnimator;
        if (r412 == null) goto L483;
        r412.cancel();
    L483:
        r44 = 0.0f;
        this.reactionsMentionsChangeProgress = 0.0f;
        ValueAnimator r512 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.reactionsMentionsAnimator = r512;
        r512.addUpdateListener(new DialogCell$$ExternalSyntheticLambda1(this));
        this.reactionsMentionsAnimator.addListener(new AnonymousClass6(this));
        ValueAnimator r513 = this.reactionsMentionsAnimator;
        if (r156 == false) goto L486;
        r513.setDuration(220);
        this.reactionsMentionsAnimator.setInterpolator(new OvershootInterpolator());
    L487:
        this.reactionsMentionsAnimator.start();
    L490:
        if (isFolderCell() == true) goto L496;
        TLRPC.Chat r514 = this.chat;
        if (r514 == null) goto L496;
        if (r514.monoforum == false) goto L496;
        boolean r157 = true;
    L497:
        this.drawMonoforumAvatar = r157;
        if (r157 == false) goto L502;
        int r515 = 1;
    L500:
        r15 = false;
    L523:
        setAvatarRadius(r515);
        r5 = r311;
        r3 = r112;
        goto L525
    L502:
        if (this.drawCommunityAvatar == false) goto L504;
        r515 = ExteraConfig.getAvatarCorners(48.0f, false, AvatarCornerType.COMMUNITY);
        goto L500
    L504:
        TLRPC.Chat r516 = this.chat;
        if (r516 == null) goto L513;
        if (r516.forum == false) goto L513;
        if (this.currentDialogFolderId != 0) goto L513;
        if (this.useFromUserAsAvatar == true) goto L513;
    L520:
        boolean r517 = true;
    L522:
        r15 = false;
        r515 = ExteraConfig.getAvatarCorners(getAvatarSizeDp(), false, r517);
    L513:
        if (this.isSavedDialog == true) goto L521;
        TLRPC.User r518 = this.user;
        if (r518 == null) goto L521;
        if (r518.self == false) goto L521;
        if (MessagesController.getInstance(this.currentAccount).savedViewAsChats == true) goto L520;
    L521:
        r517 = false;
    L496:
        r157 = false;
        goto L497
    L486:
        r513.setDuration(150);
        this.reactionsMentionsAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
    L488:
        r44 = 0.0f;
        goto L490
    L477:
        r156 = false;
        goto L478
    L473:
        r155 = false;
        goto L474
    L384:
        if (this.useFromUserAsAvatar == false) goto L388;
        MessageObject r312 = this.message;
        if (r312 == null) goto L388;
        this.avatarDrawable.setInfo(this.currentAccount, r312.getFromPeerObject());
        this.avatarImage.setForUserOrChat(this.message.getFromPeerObject(), this.avatarDrawable);
    L388:
        TLRPC.User r313 = this.user;
        if (r313 == null) goto L411;
        this.avatarDrawable.setInfo(this.currentAccount, r313);
        if (UserObject.isReplyUser(this.user) == false) goto L394;
        this.avatarDrawable.setAvatarType(12);
        this.avatarImage.setImage(null, null, this.avatarDrawable, null, this.user, 0);
        goto L382
    L394:
        if (UserObject.isAnonymous(this.user) == false) goto L397;
        this.avatarDrawable.setAvatarType(21);
        this.avatarImage.setImage(null, null, this.avatarDrawable, null, this.user, 0);
        goto L382
    L397:
        if (UserObject.isUserSelf(this.user) == false) goto L404;
        if (this.isSavedDialog == false) goto L404;
        if (this.isMonoForumTopicDialog == true) goto L404;
        this.avatarDrawable.setAvatarType(22);
        this.avatarImage.setImage(null, null, this.avatarDrawable, null, this.user, 0);
    L404:
        if (UserObject.isUserSelf(this.user) == true) goto L406;
    L410:
        this.avatarImage.setForUserOrChat(this.user, this.avatarDrawable, null, true, 1, false);
        goto L382
    L406:
        if (this.useMeForMyMessages == true) goto L410;
        if (this.isMonoForumTopicDialog == true) goto L410;
        this.avatarDrawable.setAvatarType(1);
        this.avatarImage.setImage(null, null, this.avatarDrawable, null, this.user, 0);
        goto L382
    L411:
        TLRPC.Chat r314 = this.chat;
        if (r314 == null) goto L416;
        if (r314.monoforum == false) goto L417;
        ForumUtilities.setMonoForumAvatar(this.currentAccount, r314, this.avatarDrawable, this.avatarImage);
        this.avatarDrawable.setScaleSize(1.0f);
        goto L416
    L417:
        this.avatarDrawable.setInfo(this.currentAccount, r314);
        this.avatarImage.setForUserOrChat(r314, this.avatarDrawable);
    L416:
        r311 = r510;
        goto L420
    L374:
        if (ChatObject.isCommunity(this.chat) == false) goto L378;
        if (this.isDialogCell == false) goto L378;
        r310 = true;
        goto L379
    L331:
        r75 = r17;
        goto L347
    L332:
        this.drawUnmute = false;
        if (this.forumTopic == null) goto L339;
        boolean r315 = MessagesController.getInstance(this.currentAccount).isDialogMuted(this.currentDialogId, r17);
        boolean r413 = MessagesController.getInstance(this.currentAccount).isDialogMuted(this.currentDialogId, this.forumTopic.id);
        this.topicMuted = r413;
        if (r315 != r413) goto L337;
        this.dialogMuted = false;
        this.drawUnmute = false;
    L345:
        r75 = this.currentDialogId;
        r17 = 0;
        goto L347
    L337:
        this.dialogMuted = r413;
        this.drawUnmute = !r413;
        goto L345
    L339:
        if (this.isDialogCell == true) goto L341;
    L343:
        boolean r316 = false;
    L344:
        this.dialogMuted = r316;
        goto L345
    L341:
        if (MessagesController.getInstance(this.currentAccount).isDialogMuted(this.currentDialogId, getTopicId()) == false) goto L343;
        r316 = true;
        goto L344
    L311:
        if ((r37 & MessagesController.UPDATE_MASK_SEND_STATE) == 0) goto L317;
        MessageObject r317 = this.message;
        if (r317 == null) goto L317;
        int r77 = this.lastSendState;
        int r318 = r317.messageOwner.send_state;
        if (r77 == r318) goto L317;
        this.lastSendState = r318;
        r410 = true;
        goto L317
    L302:
        if (this.markUnread != r74.unread_mark) goto L307;
        if (this.mentionCount != r133) goto L307;
        if (this.reactionMentionCount == r142) goto L308;
    L284:
        r48 = false;
        goto L285
    L278:
        if (r8.monoforum == false) goto L287;
        if (ChatObject.canManageMonoForum(this.currentAccount, r8) == true) goto L281;
    L287:
        if ((r74 instanceof TLRPC.TL_dialogFolder) == false) goto L290;
        r152 = MessagesStorage.getInstance(this.currentAccount).getArchiveUnreadCount();
        r49 = 0;
        r133 = 0;
        r142 = 0;
        goto L296
    L290:
        if ((r74 instanceof TLRPC.TL_dialogCommunity) == false) goto L292;
        MessagesController.UnreadCounts r414 = MessagesController.getInstance(this.currentAccount).getCommunityUnreadCount(-r74.id);
        int r129 = r414.unreadCount;
        r133 = r414.mentionCount;
        r142 = r414.reactionMentionCount;
        int r158 = r414.pollVotesMentionCount;
        this.hasUnmutedCommunityDialogs = r414.hasUnmutedUnreadDialogs;
        r49 = r158;
        r152 = r129;
        goto L296
    L292:
        if (r74 == null) goto L294;
        r152 = r74.unread_count;
        int r415 = r74.unread_mentions_count;
        int r1210 = r74.unread_reactions_count;
        r133 = r415;
        r49 = r74.unread_poll_votes_count;
        r142 = r1210;
        goto L296
    L294:
        r49 = 0;
        r133 = 0;
        r142 = 0;
        r152 = 0;
        goto L296
    L273:
        r8 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r74.id));
    L308:
        r410 = r32;
        goto L309
    L258:
        if ((r37 & MessagesController.UPDATE_MASK_CHAT_NAME) == 0) goto L262;
        if (this.user != null) goto L262;
        r32 = true;
        goto L262
    L252:
        if ((r37 & MessagesController.UPDATE_MASK_CHAT_AVATAR) == 0) goto L256;
        if (this.user != null) goto L256;
        r32 = true;
        goto L256
    L246:
        if ((r37 & MessagesController.UPDATE_MASK_NAME) == 0) goto L250;
        if (this.chat != null) goto L250;
        r32 = true;
        goto L250
    L233:
        if ((r37 & MessagesController.UPDATE_MASK_AVATAR) == 0) goto L244;
        TLRPC.Chat r78 = this.chat;
        if (r78 != null) goto L237;
    L240:
        ForumFormattedNames r319 = this.forumFormattedNames;
        if (r319 == null) goto L243;
        ForumFormattedNames.m6915$$Nest$minvalidateSpans(r319);
    L243:
        r32 = true;
        goto L244
    L237:
        if (r78.monoforum == false) goto L244;
        if (ChatObject.canManageMonoForum(this.currentAccount, r78) == false) goto L244;
    L217:
        if ((r37 & MessagesController.UPDATE_MASK_CHAT) == 0) goto L231;
        if (this.chat == null) goto L231;
        TLRPC.Chat r79 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.chat.id));
        if (r79 != null) goto L223;
    L227:
        boolean r710 = false;
    L229:
        if (r710 == this.hasCall) goto L231;
        r32 = true;
        goto L231
    L223:
        if (r79.call_active == false) goto L227;
        if (r79.call_not_empty == false) goto L227;
        r710 = true;
        goto L229
    L209:
        if ((r37 & MessagesController.UPDATE_MASK_MESSAGE_TEXT) == 0) goto L215;
        MessageObject r711 = this.message;
        if (r711 == null) goto L215;
        if (r711.messageText == this.lastMessageString) goto L215;
        r32 = true;
    L197:
        if ((r37 & MessagesController.UPDATE_MASK_USER_PRINT) == 0) goto L207;
        CharSequence r712 = MessagesController.getInstance(this.currentAccount).getPrintingString(this.currentDialogId, getTopicId(), true);
        CharSequence r82 = this.lastPrintString;
        if (r82 == null) goto L201;
        if (r712 != null) goto L201;
    L206:
        r32 = true;
    L201:
        if (r82 != null) goto L203;
        if (r712 != null) goto L206;
    L203:
        if (r82 == null) goto L207;
        if (r82.equals(r712) == true) goto L207;
    L138:
        if (this.user == null) goto L177;
        TLRPC.User r113 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.user.id));
        this.user = r113;
        if (r113 == null) goto L174;
        this.nameLayoutEllipsizeByGradient = true;
        BadgesController r1211 = BadgesController.INSTANCE;
        BadgeDTO r114 = r1211.getBadge(r113);
        BadgeDTO r1212 = r1211.getSecondaryBadge(this.user);
        if (r114 == null) goto L148;
        TLRPC.User r145 = this.user;
        if (r145 == null) goto L148;
        if (UserObject.isUserSelf(r145) == true) goto L148;
        boolean r146 = true;
    L150:
        if (this.forbidVerified == false) goto L152;
    L155:
        TLRPC.User r159 = this.user;
        r20 = r13;
        if (r159.scam == false) goto L158;
    L161:
        boolean r135 = true;
    L163:
        if (DialogObject.getEmojiStatusDocumentId(this.user.emoji_status) == r17) goto L165;
        r21 = r47;
        this.emojiStatus.set(DialogObject.getEmojiStatusDocumentId(this.user.emoji_status), r38);
        this.emojiStatus.setParticles(DialogObject.isEmojiStatusCollectible(this.user.emoji_status), r38);
        r135 = true;
    L169:
        if (r1212 == null) goto L173;
        if (r146 == false) goto L173;
        if (r135 == false) goto L173;
        long r713 = r1212.getDocumentId();
        boolean r320 = true;
    L176:
        r112 = true;
    L179:
        if (this.chat == null) goto L187;
        TLRPC.Chat r714 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.chat.id));
        this.chat = r714;
        if (r714 != null) goto L183;
    L185:
        this.nameLayoutEllipsizeByGradient = true;
        this.emojiStatus.set(PremiumGradient.getInstance().premiumStarDrawableMini, r38);
        this.emojiStatus.setParticles(false, r38);
    L186:
        r713 = DialogObject.getBotVerificationIcon(this.chat);
        r112 = true;
        goto L187
    L183:
        if (DialogObject.getEmojiStatusDocumentId(r714.emoji_status) == r17) goto L185;
        this.nameLayoutEllipsizeByGradient = true;
        this.emojiStatus.set(DialogObject.getEmojiStatusDocumentId(this.chat.emoji_status), r38);
        this.emojiStatus.setParticles(DialogObject.isEmojiStatusCollectible(this.chat.emoji_status), r38);
    L187:
        this.botVerification.setParticles(r320, r38, 5);
        boolean r1213 = this.drawBotVerified;
        if (r713 == r17) goto L190;
        r32 = !r1213;
        this.botVerification.set(r713, r38);
        goto L193
    L190:
        this.botVerification.set(null, r38);
        r32 = r1213;
    L173:
        r713 = DialogObject.getBotVerificationIcon(this.user);
    L175:
        r320 = false;
        goto L176
    L165:
        r21 = r47;
        if (r114 == null) goto L169;
        if (r146 == false) goto L169;
        this.emojiStatus.set(r114.getDocumentId(), r38);
        this.emojiStatus.setParticles(true, r38);
        goto L169
    L158:
        if (r159.fake == true) goto L161;
        r135 = false;
        goto L163
    L152:
        if (this.user.verified == false) goto L155;
        r20 = r13;
    L148:
        r146 = false;
        goto L150
    L174:
        r21 = r47;
        r20 = r13;
        this.nameLayoutEllipsizeByGradient = true;
        this.emojiStatus.set(PremiumGradient.getInstance().premiumStarDrawableMini, r38);
        this.emojiStatus.setParticles(false, r38);
        r713 = r17;
        goto L175
    L177:
        r21 = r47;
        r20 = r13;
        r713 = r17;
        r320 = false;
        goto L179
    L322:
        r21 = r47;
        r20 = r13;
        goto L323
    L116:
        if (r59 == this.tags.isEmpty()) goto L119;
        r510 = true;
    L118:
        r6 = r510;
        r112 = true;
        goto L123
    L119:
        r510 = false;
        goto L118
    L121:
        r13 = 2;
        r19 = 4;
        goto L122
    L100:
        if (r55 != RightSlidingDialogContainer.fragmentDialogId) goto L102;
        r56 = true;
        goto L103
    L77:
        if (r52.pinned == false) goto L79;
        r53 = true;
    L81:
        MessagesController.DialogFilter[] r69 = MessagesController.getInstance(this.currentAccount).selectedDialogFilter;
        if (this.dialogsType != 8) goto L84;
        char r115 = 1;
    L85:
        MessagesController.DialogFilter r610 = r69[r115];
        if (r610 != null) goto L88;
    L90:
        boolean r519 = false;
    L91:
        this.drawPin = r519;
        goto L92
    L88:
        if (r610.pinnedDialogs.indexOfKey(r52.id) < 0) goto L90;
        r519 = true;
        goto L91
    L84:
        r115 = 0;
        goto L85
    L69:
        r67 = 0;
        goto L70
    L54:
        r12 = false;
        goto L55
    L48:
        if (r65.monoforum == false) goto L57;
        if (ChatObject.canManageMonoForum(this.currentAccount, r65) == true) goto L51;
    L57:
        if ((r52 instanceof TLRPC.TL_dialogFolder) == false) goto L60;
        this.unreadCount = MessagesStorage.getInstance(this.currentAccount).getArchiveUnreadCount();
        this.mentionCount = 0;
        this.reactionMentionCount = 0;
        this.pollVotesMentionCount = 0;
        goto L64
    L60:
        if ((r52 instanceof TLRPC.TL_dialogCommunity) == false) goto L62;
        MessagesController.UnreadCounts r116 = MessagesController.getInstance(this.currentAccount).getCommunityUnreadCount(-r52.id);
        this.unreadCount = r116.unreadCount;
        this.mentionCount = r116.mentionCount;
        this.reactionMentionCount = r116.reactionMentionCount;
        this.pollVotesMentionCount = r116.pollVotesMentionCount;
        this.hasUnmutedCommunityDialogs = r116.hasUnmutedUnreadDialogs;
        goto L64
    L62:
        this.unreadCount = r52.unread_count;
        this.mentionCount = r52.unread_mentions_count;
        this.reactionMentionCount = r52.unread_reactions_count;
        this.pollVotesMentionCount = r52.unread_poll_votes_count;
        goto L64
    L42:
        if (r65.forum == false) goto L44;
        boolean r117 = this.isTopic;
        goto L44
    L36:
        if (r63.isUnread() == false) goto L38;
        r64 = true;
        goto L39
    L30:
        if (r62.size() <= 0) goto L32;
        r63 = this.groupMessages.get(0);
        goto L33
    L95:
        r17 = 0;
        goto L97
    L96:
        r17 = 0;
        this.unreadCount = 0;
        this.mentionCount = 0;
        this.reactionMentionCount = 0;
        this.pollVotesMentionCount = 0;
        this.currentEditDate = 0;
        this.lastMessageDate = 0;
        this.clearingDialog = false;
        goto L97
    L104:
        r17 = 0;
        this.drawPin = false;
        goto L105
    L20:
        r94 = false;
        goto L21
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
    }

    public void updateMessageThumbs() {
        MessageObject r0 = this.message;
        if (r0 == null) goto L117;
        String r02 = MessagesController.getInstance(r0.currentAccount).getRestrictionReason(this.message.messageOwner.restriction_reason);
        MessageObject r1 = this.message;
        int r3 = 0;
        if (r1 == null) goto L24;
        TLRPC.Message r12 = r1.messageOwner;
        if (r12 == null) goto L24;
        TLRPC.MessageMedia r13 = r12.media;
        if ((r13 instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L24;
        this.thumbsCount = 0;
        this.hasVideoThumb = false;
        TLRPC.TL_messageMediaPaidMedia r14 = (TLRPC.TL_messageMediaPaidMedia) r13;
        int r03 = 0;
    L13:
        if (r3 >= r14.extended_media.size()) goto L118;
        if (this.thumbsCount >= 3) goto L119;
        TLRPC.MessageExtendedMedia r4 = r14.extended_media.get(r3);
        if ((r4 instanceof TLRPC.TL_messageExtendedMediaPreview) == false) goto L21;
        int r5 = r03 + 1;
        setThumb(r03, ((TLRPC.TL_messageExtendedMediaPreview) r4).thumb);
    L19:
        r03 = r5;
    L23:
        r3 = r3 + 1;
        goto L13
    L21:
        if ((r4 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L23;
        r5 = r03 + 1;
        setThumb(r03, ((TLRPC.TL_messageExtendedMedia) r4).media);
        goto L19
    L119:
        return;
    L118:
        return;
    L24:
        ArrayList<MessageObject> r15 = this.groupMessages;
        String r8 = null;
        if (r15 != null) goto L27;
    L66:
        MessageObject r04 = this.message;
        if (r04 != null) goto L69;
        return;
    L69:
        if (this.currentDialogFolderId != 0) goto L110;
        this.thumbsCount = 0;
        this.hasVideoThumb = false;
        if (r04.needDrawBluredPreview() == false) goto L73;
        return;
    L73:
        if (this.message.isPhoto() == true) goto L83;
        if (this.message.isNewGif() == true) goto L83;
        if (this.message.isVideo() == true) goto L83;
        if (this.message.isRoundVideo() == true) goto L83;
        if (this.message.isStoryMedia() == true) goto L83;
        return;
    L83:
        if (this.message.isWebpage() == false) goto L86;
        r8 = this.message.messageOwner.media.webpage.type;
    L86:
        if (Common.ASSET_APP.equals(r8) == false) goto L88;
        return;
    L88:
        if ("profile".equals(r8) == false) goto L90;
        return;
    L90:
        if ("article".equals(r8) == true) goto L115;
        if (r8 != null) goto L93;
    L94:
        setThumb(0, this.message);
        return;
    L93:
        if (r8.startsWith("telegram_") == false) goto L94;
        return;
    L115:
        return;
    L110:
        return;
    L27:
        if (r15.size() <= 1) goto L66;
        if (TextUtils.isEmpty(r02) == false) goto L66;
        if (this.currentDialogFolderId != 0) goto L66;
        if (this.encryptedChat != null) goto L66;
        this.thumbsCount = 0;
        this.hasVideoThumb = false;
        Collections.sort(this.groupMessages, Comparator.comparingInt(new DialogCell$$ExternalSyntheticLambda4()));
    L36:
        if (r3 >= Math.min(3, this.groupMessages.size())) goto L120;
        MessageObject r05 = this.groupMessages.get(r3);
        if (r05 == null) goto L65;
        if (r05.needDrawBluredPreview() == true) goto L65;
        if (r05.isPhoto() == true) goto L52;
        if (r05.isNewGif() == true) goto L52;
        if (r05.isVideo() == true) goto L52;
        if (r05.isRoundVideo() == true) goto L52;
        if (r05.isStoryMedia() == false) goto L65;
    L52:
        if (r05.isWebpage() == false) goto L54;
        String r16 = r05.messageOwner.media.webpage.type;
    L56:
        if (Common.ASSET_APP.equals(r16) == true) goto L65;
        if ("profile".equals(r16) == true) goto L65;
        if ("article".equals(r16) == true) goto L65;
        if (r16 != null) goto L63;
    L64:
        setThumb(r3, r05);
        goto L65
    L63:
        if (r16.startsWith("telegram_") == true) goto L65;
    L54:
        r16 = null;
    L65:
        r3 = r3 + 1;
        goto L36
    L120:
        return;
    }
}
