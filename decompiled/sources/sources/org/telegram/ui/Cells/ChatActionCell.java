package org.telegram.ui.Cells;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AlignmentSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.AvatarCornerType;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.chats.ChatUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import me.vkryl.core.BitwiseUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BotInlineKeyboard;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ChatThemeController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessageSuggestionParams;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.AvatarSpan;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.ChannelAdminLogActivity;
import org.telegram.ui.ChatBackgroundDrawable;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CommunityAvatarDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SuggestBirthdayActionLayout;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TopicSeparator;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.GradientClip;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.GiftOfferSheet;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stars.StarGiftUniqueActionLayout;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.StoriesUtilities;
import org.telegram.ui.Stories.UploadingDotsSpannable;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.Stories.recorder.PreviewView;
import org.telegram.ui.community.CommunitySheet;
import org.telegram.ui.community.CommunityUtils;

/* loaded from: classes6.dex */
public class ChatActionCell extends BaseCell implements DownloadController.FileDownloadProgressListener, NotificationCenter.NotificationCenterDelegate, IMessageCell {
    private static Map<Integer, String> monthsToEmoticon;
    private int TAG;
    private SpannableStringBuilder accessibilityText;
    private boolean actionPressed;
    private int adaptiveEmojiColor;
    private ColorFilter adaptiveEmojiColorFilter;
    private AnimatedEmojiSpan.EmojiGroupedSpans animatedEmojiStack;
    private boolean attachedToWindow;
    private AvatarDrawable avatarDrawable;
    StoriesUtilities.AvatarStoryParams avatarStoryParams;
    private int backgroundButtonTop;
    private int backgroundHeight;
    private int backgroundLeft;
    private Path backgroundPath;
    private final Path backgroundPath2;
    private RectF backgroundRect;
    private int backgroundRectHeight;
    private int backgroundRight;
    public SuggestBirthdayActionLayout birthdayLayout;
    private final Path botButtonPath;
    private final float[] botButtonRadii;
    private ArrayList<BotButton> botButtons;
    private BotInlineKeyboard.Source botInlineButtons;
    private final ButtonBounce bounce;
    private boolean buttonClickableAsImage;
    private boolean canDrawInParent;
    private GiftSheet.CardBackground cardBackground;
    private Path clipPath;
    private int currentAccount;
    private MessageObject currentMessageObject;
    private ImageLocation currentVideoLocation;
    private int customDate;
    private CharSequence customText;
    private ChatActionCellDelegate delegate;
    private float dimAmount;
    private final Paint dimPaint;
    public boolean firstInChat;
    private boolean forceWasUnread;
    private boolean giftButtonPressed;
    private RectF giftButtonRect;
    private TLRPC.VideoSize giftEffectAnimation;
    private int giftPremiumAdditionalHeight;
    private StaticLayout giftPremiumButtonLayout;
    private float giftPremiumButtonWidth;
    private Text giftPremiumReleasedText;
    private StaticLayout giftPremiumSubtitleLayout;
    private TextLayout giftPremiumText;
    private GradientClip giftPremiumTextClip;
    private boolean giftPremiumTextCollapsed;
    private int giftPremiumTextCollapsedHeight;
    private AnimatedFloat giftPremiumTextExpandedAnimated;
    private Text giftPremiumTextMore;
    private int giftPremiumTextMoreH;
    private int giftPremiumTextMoreX;
    private int giftPremiumTextMoreY;
    private boolean giftPremiumTextUncollapsed;
    private StaticLayout giftPremiumTitleLayout;
    private boolean giftRectEmpty;
    private int giftRectSize;
    private Paint giftReleasedBackgroundPaint;
    private CornerPathEffect giftRibbonPaintEffect;
    private ColorMatrixColorFilter giftRibbonPaintFilter;
    private boolean giftRibbonPaintFilterDark;
    private Path giftRibbonPath;
    private Text giftRibbonText;
    private TLRPC.Document giftSticker;
    private ImageReceiver.ImageReceiverDelegate giftStickerDelegate;
    private TextPaint giftSubtitlePaint;
    private TextPaint giftTextPaint;
    private TextPaint giftTitlePaint;
    private boolean hasReplyMessage;
    private boolean imagePressed;
    private ImageReceiver imageReceiver;
    private boolean invalidateColors;
    private Runnable invalidateListener;
    private boolean invalidatePath;
    private View invalidateWithParent;
    private boolean invalidatesParent;
    public boolean isAllChats;
    public boolean isBotForum;
    public boolean isForum;
    public boolean isMonoForum;
    public boolean isSideMenuEnabled;
    public boolean isSideMenued;
    private boolean isSpoilerRevealing;
    private float lastTouchX;
    private float lastTouchY;
    private ArrayList<Integer> lineHeights;
    private ArrayList<Integer> lineWidths;
    private LoadingDrawable loadingDrawable;
    private NotificationCenter.ObserversGroup observersGroup;
    private boolean offerExpired;
    private View.OnClickListener onActionClick;
    private View.OnLongClickListener onActionLongClick;
    private int overriddenMaxWidth;
    private int overrideBackground;
    private Paint overrideBackgroundPaint;
    private int overrideText;
    private TextPaint overrideTextPaint;
    private int pressedBotButton;
    private URLSpan pressedLink;
    private final int[] pressedState;
    private int previousWidth;
    float progressToProgress;
    RadialProgressView progressView;
    private RadialProgress2 radialProgress;
    private final float[] radii;
    public final ReactionsLayoutInBubble reactionsLayoutInBubble;
    private RectF rect;
    private View rippleView;
    private StaticLayout settingWallpaperLayout;
    TextPaint settingWallpaperPaint;
    private float settingWallpaperProgress;
    private StaticLayout settingWallpaperProgressTextLayout;
    public boolean showTopicSeparator;
    public float sideMenuAlpha;
    public int sideMenuWidth;
    private SpoilerEffect spoilerPressed;
    public List<SpoilerEffect> spoilers;
    private Stack<SpoilerEffect> spoilersPool;
    public final StarGiftUniqueActionLayout starGiftLayout;
    public float starGiftLayoutX;
    public float starGiftLayoutY;
    private StarParticlesView.Drawable starParticlesDrawable;
    private Path starsPath;
    private int starsSize;
    private int stickerSize;
    private int textHeight;
    private StaticLayout textLayout;
    TextPaint textPaint;
    private boolean textPressed;
    private int textWidth;
    private int textX;
    private int textXLeft;
    private int textY;
    private Theme.ResourcesProvider themeDelegate;
    private int titleHeight;
    private StaticLayout titleLayout;
    private int titleXLeft;
    public TopicSeparator topicSeparator;
    private int topicSeparatorTopPadding;
    public final TransitionParams transitionParams;
    private float viewTop;
    private float viewTranslationX;
    private boolean visiblePartSet;
    private Drawable wallpaperPreviewDrawable;
    private boolean wasLayout;

    /* renamed from: org.telegram.ui.Cells.ChatActionCell$1, reason: invalid class name */
    public class AnonymousClass1 extends ClickableSpan {
        final /* synthetic */ ChatActionCell this$0;
        final /* synthetic */ CharacterStyle val$link;

        public AnonymousClass1(ChatActionCell r1, CharacterStyle r2) {
            this.this$0 = r1;
            this.val$link = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            if (ChatActionCell.m6629$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            ChatActionCell.m6630$$Nest$mopenLink(this.this$0, this.val$link);
            return;
        }
    }

    public interface ChatActionCellDelegate {
        default boolean canDrawOutboundsContent() {
            return true;
        }

        default void didClickButton(ChatActionCell r1) {
        }

        default void didClickImage(ChatActionCell r1) {
        }

        default boolean didLongPress(ChatActionCell r1, float r2, float r3) {
            return false;
        }

        default void didOpenPremiumGift(ChatActionCell r1, TLRPC.TL_premiumGiftOption r2, String r3, boolean r4) {
        }

        default void didOpenPremiumGiftChannel(ChatActionCell r1, String r2, boolean r3) {
        }

        default void didPressReaction(ChatActionCell r1, TLRPC.ReactionCount r2, boolean r3, float r4, float r5) {
        }

        default void didPressReplyMessage(ChatActionCell r1, int r2) {
        }

        default void didPressTaskLink(ChatActionCell r1, int r2, int r3) {
        }

        default void forceUpdate(ChatActionCell r1, boolean r2) {
        }

        default BaseFragment getBaseFragment() {
            return null;
        }

        default long getDialogId() {
            return 0;
        }

        default long getTopicId() {
            return 0;
        }

        default void needOpenInviteLink(TLRPC.TL_chatInviteExported r1) {
        }

        default void needOpenUserProfile(long r1) {
        }

        default void needShowEffectOverlay(ChatActionCell r1, TLRPC.Document r2, TLRPC.VideoSize r3) {
        }

        default void onTopicClick(ChatActionCell r1) {
        }
    }

    public class TextLayout {
        public AnimatedEmojiSpan.EmojiGroupedSpans emoji;
        public StaticLayout layout;
        public TextPaint paint;
        public final AtomicReference<Layout> patchedLayout;
        public List<SpoilerEffect> spoilers;
        final /* synthetic */ ChatActionCell this$0;
        public int width;
        public float x;
        public float y;

        public TextLayout(ChatActionCell r1) {
            this.this$0 = r1;
            this.spoilers = new ArrayList();
            this.patchedLayout = new AtomicReference();
        }

        public void attach() {
            this.emoji = AnimatedEmojiSpan.update(0, this.this$0, false, this.emoji, new Layout[]{this.layout});
        }

        public void detach() {
            AnimatedEmojiSpan.release(this.this$0, this.emoji);
        }

        public void setText(CharSequence r9, TextPaint r10, int r11) {
            this.paint = r10;
            this.width = r11;
            this.layout = new StaticLayout(r9, r10, r11, Layout.Alignment.ALIGN_CENTER, 1.1f, 0.0f, false);
            if (ChatActionCell.m6628$$Nest$fgetcurrentMessageObject(this.this$0) != null) goto L5;
        L10:
            SpoilerEffect.addSpoilers(this.this$0, this.layout, -1, r11, null, this.spoilers);
        L11:
            attach();
            return;
        L5:
            if (ChatActionCell.m6628$$Nest$fgetcurrentMessageObject(this.this$0).isSpoilersRevealed == false) goto L10;
            List<SpoilerEffect> r0 = this.spoilers;
            if (r0 == null) goto L11;
            r0.clear();
            goto L11
        }
    }

    public class TransitionParams {
        public boolean animateChange;
        public float animateChangeProgress;
        final /* synthetic */ ChatActionCell this$0;
        public boolean wasDraw;

        public TransitionParams(ChatActionCell r1) {
            this.this$0 = r1;
            this.animateChangeProgress = 1.0f;
        }

        public boolean animateChange() {
            if (this.wasDraw == true) goto L7;
            return false;
        L7:
            return this.this$0.reactionsLayoutInBubble.animateChange();
        }

        public void onDetach() {
            this.wasDraw = false;
        }

        public void recordDrawingState() {
            this.wasDraw = true;
            this.this$0.reactionsLayoutInBubble.recordDrawingState();
        }

        public void resetAnimation() {
            this.animateChange = false;
            this.animateChangeProgress = 1.0f;
        }

        public boolean supportChangeAnimation() {
            return true;
        }
    }

    public static /* synthetic */ void $r8$lambda$2RIn_Zf8LWyEH9XnlMamf6fU6XU(ChatActionCell r0) {
        r0.lambda$onTouchEvent$2();
    }

    public static /* synthetic */ void $r8$lambda$3RNgYrTMc5lGds8qF9VCYiEorOk(ChatActionCell r0, BaseFragment r1, TLRPC.Updates r2, TLRPC.TL_error r3) {
        r0.lambda$didPressCustomBotButton$7(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$DjShJcte-RnsGOSskXRgodoFCnc, reason: not valid java name */
    public static /* synthetic */ void m6625$r8$lambda$DjShJcteRnsGOSskXRgodoFCnc(ChatActionCell r0) {
        r0.lambda$onTouchEvent$3();
    }

    public static /* synthetic */ void $r8$lambda$E1usMFRwAFVXQF9Ca1udUTZ5Yhc(ChatActionCell r0, BaseFragment r1) {
        r0.lambda$didPressCustomBotButton$8(r1);
    }

    /* renamed from: $r8$lambda$M94e-WoidXMW0sSXz8iNtWSX9_E, reason: not valid java name */
    public static /* synthetic */ void m6626$r8$lambda$M94eWoidXMW0sSXz8iNtWSX9_E(ChatActionCell r0, TLRPC.TL_premiumGiftOption r1, String r2) {
        r0.lambda$openPremiumGiftPreview$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$TRJArb3vcIMykXxWl_Y2rs47hvE(BaseFragment r0, TLRPC.TL_error r1) {
        BulletinFactory.of(r0).showForError(r1);
    }

    public static /* synthetic */ void $r8$lambda$VnGbzJWEt4xpNHRbT13CQWccGbo(ChatActionCell r0, TLRPC.TL_messageActionNoForwardsRequest r1) {
        r0.lambda$didPressCustomBotButton$9(r1);
    }

    /* renamed from: $r8$lambda$iCvTBejquFXrWNVa-zb0FYTwURs, reason: not valid java name */
    public static /* synthetic */ void m6627$r8$lambda$iCvTBejquFXrWNVazb0FYTwURs(ChatActionCell r0) {
        r0.lambda$setMessageObject$1();
    }

    public static /* synthetic */ void $r8$lambda$nIGBo2bL6KX1M_zm8GnMpYxcPME(ChatActionCell r0, ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$pZe4_IGdoW_ShdrRMWwL33ZDjhY(ChatActionCell r0, TLRPC.TL_messageActionNoForwardsRequest r1) {
        r0.lambda$didPressCustomBotButton$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$tEU47A0_xJJKpwEHXo718Vac4CQ(ChatActionCell r0, TLRPC.TL_messageActionGiftCode r1) {
        r0.lambda$openPremiumGiftChannel$4(r1);
    }

    /* renamed from: -$$Nest$fgetcurrentMessageObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m6628$$Nest$fgetcurrentMessageObject(ChatActionCell r0) {
        return r0.currentMessageObject;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActionCellDelegate m6629$$Nest$fgetdelegate(ChatActionCell r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$mopenLink, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6630$$Nest$mopenLink(ChatActionCell r0, CharacterStyle r1) {
        r0.openLink(r1);
    }

    static {
        HashMap r0 = new HashMap();
        monthsToEmoticon = r0;
        r0.put(1, "1⃣");
        monthsToEmoticon.put(3, "2⃣");
        monthsToEmoticon.put(6, "3⃣");
        monthsToEmoticon.put(12, "4⃣");
        monthsToEmoticon.put(24, "5⃣");
    }

    public ChatActionCell(Context r3) {
        this(r3, false, null);
    }

    public ChatActionCell(Context r11, boolean r12, Theme.ResourcesProvider r13) {
        super(r11);
        this.bounce = new ButtonBounce(this);
        this.currentAccount = UserConfig.selectedAccount;
        this.avatarStoryParams = new StoriesUtilities.AvatarStoryParams(false);
        this.showTopicSeparator = true;
        this.giftButtonRect = new RectF();
        this.spoilers = new ArrayList();
        this.spoilersPool = new Stack();
        this.reactionsLayoutInBubble = new ReactionsLayoutInBubble(this);
        this.overrideBackground = -1;
        this.overrideText = -1;
        this.lineWidths = new ArrayList();
        this.lineHeights = new ArrayList();
        this.backgroundPath = new Path();
        this.rect = new RectF();
        this.invalidatePath = true;
        this.invalidateColors = false;
        this.giftPremiumTextUncollapsed = false;
        this.giftPremiumTextCollapsed = false;
        this.giftPremiumTextExpandedAnimated = new AnimatedFloat(this, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.buttonClickableAsImage = true;
        this.giftTitlePaint = new TextPaint(1);
        this.giftTextPaint = new TextPaint(1);
        this.giftSubtitlePaint = new TextPaint(1);
        this.radialProgress = new RadialProgress2(this);
        this.giftStickerDelegate = new ChatActionCell$$ExternalSyntheticLambda5(this);
        this.starsPath = new Path();
        this.botButtons = new ArrayList();
        this.dimPaint = new Paint(1);
        this.backgroundPath2 = new Path();
        this.radii = new float[8];
        this.botButtonRadii = new float[8];
        this.botButtonPath = new Path();
        this.pressedState = new int[]{R.attr.state_enabled, R.attr.state_pressed};
        this.transitionParams = new TransitionParams(this);
        this.avatarStoryParams.drawSegments = false;
        this.canDrawInParent = r12;
        this.themeDelegate = r13;
        ImageReceiver r122 = new ImageReceiver(this);
        this.imageReceiver = r122;
        r122.setRoundRadius(ExteraConfig.getAvatarCorners(AndroidUtilities.roundMessageSize, true));
        this.avatarDrawable = new AvatarDrawable();
        this.TAG = DownloadController.getInstance(this.currentAccount).generateObserverTag();
        this.starGiftLayout = new StarGiftUniqueActionLayout(this.currentAccount, this, r13);
        this.giftTitlePaint.setTextSize(TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.giftSubtitlePaint.setTextSize(TypedValue.applyDimension(1, 15.0f, getResources().getDisplayMetrics()));
        this.giftTextPaint.setTextSize(TypedValue.applyDimension(1, 15.0f, getResources().getDisplayMetrics()));
        View r123 = new View(r11);
        this.rippleView = r123;
        r123.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(-16777216, 0.1f), 7, AndroidUtilities.dp(16.0f)));
        this.rippleView.setVisibility(8);
        addView(this.rippleView);
        StarParticlesView.Drawable r10 = new StarParticlesView.Drawable(10);
        this.starParticlesDrawable = r10;
        r10.type = 100;
        r10.isCircle = false;
        r10.roundEffect = true;
        r10.useRotate = false;
        r10.useBlur = true;
        r10.checkBounds = true;
        r10.size1 = 1;
        r10.k3 = 0.98f;
        r10.k2 = 0.98f;
        r10.k1 = 0.98f;
        r10.paused = false;
        r10.speedScale = 0.0f;
        r10.minLifeTime = 750;
        r10.randLifeTime = 750;
        r10.init();
    }

    private void buildLayout() {
        this.giftRectEmpty = false;
        MessageObject r1 = this.currentMessageObject;
        CharSequence r14 = null;
        if (r1 != null) goto L5;
        CharSequence r4 = this.customText;
    L47:
        MessageObject r5 = this.currentMessageObject;
        if (r5 == null) goto L53;
        if (r5.isRepostPreview == false) goto L53;
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L53:
        if (r5 == null) goto L70;
        TLRPC.Message r52 = r5.messageOwner;
        if (r52 == null) goto L70;
        TLRPC.MessageAction r53 = r52.action;
        if (r53 == null) goto L70;
        if ((r53 instanceof TLRPC.TL_messageActionTodoAppendTasks) == false) goto L62;
        int r54 = org.telegram.messenger.R.drawable.mini_checklist_add;
    L68:
        if (r54 == 0) goto L70;
        SpannableStringBuilder r7 = new SpannableStringBuilder(r4);
        r7.insert(0, "i ");
        r7.setSpan(new ColoredImageSpan(r54), 0, 1, 33);
        r4 = r7;
        goto L70
    L62:
        if ((r53 instanceof TLRPC.TL_messageActionTodoCompletions) == false) goto L67;
        TLRPC.TL_messageActionTodoCompletions r55 = (TLRPC.TL_messageActionTodoCompletions) r53;
        if (r55.incompleted.size() <= r55.completed.size()) goto L66;
        r54 = org.telegram.messenger.R.drawable.mini_checklist_undone;
        goto L68
    L66:
        r54 = org.telegram.messenger.R.drawable.mini_checklist_done;
        goto L68
    L67:
        r54 = 0;
    L70:
        createLayout(r4, this.previousWidth);
        if (this.birthdayLayout == null) goto L73;
        this.textLayout = null;
        this.textHeight = 0;
        this.titleLayout = null;
        this.titleHeight = 0;
        this.textY = 0;
    L73:
        if (r1 == null) goto L431;
        TLRPC.Message r56 = r1.messageOwner;
        if (r56 == null) goto L85;
        TLRPC.MessageAction r72 = r56.action;
        if ((r72 instanceof TLRPC.TL_messageActionSuggestedPostApproval) == false) goto L85;
        if (((TLRPC.TL_messageActionSuggestedPostApproval) r72).balance_too_low == false) goto L85;
        TLRPC.TL_messageActionSuggestedPostApproval r73 = (TLRPC.TL_messageActionSuggestedPostApproval) r72;
        if (ChatObject.canManageMonoForum(this.currentAccount, r1.getDialogId()) == true) goto L83;
        CharSequence r6 = LocaleController.getString(org.telegram.messenger.R.string.StarsBuy);
    L84:
        createGiftPremiumLayouts(null, null, null, r4, false, r6, 11, null, this.giftRectSize, false, true);
        this.textLayout = null;
        this.textHeight = 0;
        this.titleLayout = null;
        this.titleHeight = 0;
        this.textY = 0;
        this.giftRectEmpty = true;
        goto L431
    L83:
        r6 = null;
    L85:
        if (r56 == null) goto L91;
        TLRPC.MessageAction r74 = r56.action;
        if ((r74 instanceof TLRPC.TL_messageActionSuggestedPostApproval) == false) goto L91;
        if (((TLRPC.TL_messageActionSuggestedPostApproval) r74).rejected == false) goto L91;
        TLRPC.TL_messageActionSuggestedPostApproval r75 = (TLRPC.TL_messageActionSuggestedPostApproval) r74;
        createGiftPremiumLayouts(null, null, null, r4, false, null, 11, null, this.giftRectSize, false, true);
        this.textLayout = null;
        this.textHeight = 0;
        this.titleLayout = null;
        this.titleHeight = 0;
        this.textY = 0;
        this.giftRectEmpty = true;
    L91:
        int r76 = r1.type;
        if (r76 != 11) goto L95;
        float r3 = this.textHeight + AndroidUtilities.dp(19.0f);
        int r42 = AndroidUtilities.roundMessageSize;
        this.imageReceiver.setImageCoords((this.previousWidth - AndroidUtilities.roundMessageSize) / 2.0f, r3, r42, r42);
        goto L431
    L95:
        if (r76 != 25) goto L98;
        createGiftPremiumChannelLayouts();
        goto L431
    L98:
        if (r76 != 30) goto L286;
        TLRPC.User r43 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.currentMessageObject.getDialogId()));
        TLRPC.MessageAction r57 = r1.messageOwner.action;
        if ((r57 instanceof TLRPC.TL_messageActionGiftStars) == false) goto L107;
        CharSequence r12 = LocaleController.formatPluralStringComma("ActionGiftStarsTitle", (int) ((TLRPC.TL_messageActionGiftStars) r57).stars);
        if (this.currentMessageObject.isOutOwner() == false) goto L104;
        String r2 = LocaleController.formatString(org.telegram.messenger.R.string.ActionGiftStarsSubtitle, new Object[]{UserObject.getForcedFirstName(r43)});
    L105:
        createGiftPremiumLayouts(r12, null, null, AndroidUtilities.replaceTags(r2), false, LocaleController.getString(org.telegram.messenger.R.string.ActionGiftStarsView), 11, null, this.giftRectSize, true, false);
        goto L431
    L104:
        r2 = LocaleController.getString(org.telegram.messenger.R.string.ActionGiftStarsSubtitleYou);
        goto L105
    L107:
        if ((r57 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L125;
        if (((TLRPC.TL_messageActionStarGiftUnique) r57).refunded == false) goto L125;
        long r32 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        TLRPC.TL_messageActionStarGiftUnique r58 = (TLRPC.TL_messageActionStarGiftUnique) r1.messageOwner.action;
        if (r1.isOutOwner() == (!r58.upgrade)) goto L114;
        r32 = r1.getDialogId();
    L114:
        TLRPC.User r13 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r32));
        SpannableStringBuilder r33 = new SpannableStringBuilder();
        if (r58.prepaid_upgrade == false) goto L117;
        int r44 = org.telegram.messenger.R.string.Gift2ActionUpgradeTitle;
    L118:
        r33.append(LocaleController.getString(r44)).append(" ");
        if (r13 != null) goto L121;
    L123:
        r33.append(UserObject.getForcedFirstName(r13));
        createGiftPremiumLayouts(r33, null, null, LocaleController.getString(org.telegram.messenger.R.string.Gift2ActionUpgradeRefundedText), false, LocaleController.getString(org.telegram.messenger.R.string.ActionGiftStarsView), 12, LocaleController.getString(org.telegram.messenger.R.string.Gift2UniqueRibbon), this.giftRectSize, true, false);
        goto L431
    L121:
        if (r13.photo == null) goto L123;
        r33.append("a ");
        AvatarSpan r22 = new AvatarSpan(this, this.currentAccount, 18.0f);
        r22.setUser(r13);
        r33.setSpan(r22, r33.length() - 2, r33.length() - 1, 33);
        goto L123
    L117:
        r44 = org.telegram.messenger.R.string.Gift2ActionTitle;
    L125:
        if ((r57 instanceof TLRPC.TL_messageActionStarGift) == false) goto L283;
        TLRPC.TL_messageActionStarGift r59 = (TLRPC.TL_messageActionStarGift) r57;
        long r122 = r59.convert_stars;
        long r19 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        TLRPC.Peer r34 = r59.peer;
        if (r34 != null) goto L129;
    L133:
        boolean r35 = false;
    L135:
        if (r1.getDialogId() != r19) goto L138;
        if (r35 == true) goto L138;
        boolean r77 = true;
    L139:
        long r192 = r1.getFromChatId();
        if (r59.prepaid_upgrade == true) goto L144;
        TLRPC.Peer r9 = r59.from_id;
        if (r9 == null) goto L144;
        r192 = DialogObject.getPeerDialogId(r9);
    L144:
        SpannableStringBuilder r132 = new SpannableStringBuilder();
        TLObject r11 = MessagesController.getInstance(this.currentAccount).getUserOrChat(r192);
        boolean r20 = r77;
        long r62 = DialogObject.getPeerDialogId(r59.to_id);
        TLObject r92 = MessagesController.getInstance(this.currentAccount).getUserOrChat(r62);
        if (r59.can_upgrade == true) goto L147;
    L153:
        TLObject r26 = r92;
    L154:
        boolean r8 = false;
    L156:
        if (r62 != 0) goto L158;
    L165:
        if (r20 == false) goto L175;
        if (r59.gift_num <= 0) goto L173;
        TL_stars.StarGift r23 = r59.gift;
        if (r23 == null) goto L173;
        String r24 = r23.title;
        if (r24 == null) goto L173;
        r132.append(r24).append(" #").append(LocaleController.formatNumber(r59.gift_num, ','));
    L182:
        int r25 = MessagesController.getInstance(this.currentAccount).stargiftsConvertPeriodMax - (ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() - r1.messageOwner.date);
        if (r1.isOutOwner() == false) goto L186;
        if (r20 == true) goto L186;
    L188:
        if (r59.convert_stars <= 0) goto L193;
        if (r25 <= 0) goto L193;
        if (r59.refunded == true) goto L193;
        boolean r27 = true;
    L195:
        if (r59.refunded == false) goto L198;
        CharSequence r28 = LocaleController.getString(org.telegram.messenger.R.string.Gift2ActionConvertRefundedText);
    L197:
        CharSequence r45 = r28;
        TL_stars.StarGift r29 = r59.gift;
        if (r29 != null) goto L258;
    L264:
        CharSequence r210 = null;
    L265:
        CharSequence r36 = LocaleController.getString(org.telegram.messenger.R.string.ActionGiftStarsView);
        if (r1.isOutOwner() == false) goto L270;
        CharSequence r37 = r36;
        if (r59.forceIn == true) goto L270;
        if (r8 == true) goto L270;
    L274:
        CharSequence r63 = r37;
        TL_stars.StarGift r15 = r59.gift;
        if (r15 != null) goto L277;
    L281:
        createGiftPremiumLayouts(r132, null, r14, r45, false, r63, 11, r210, this.giftRectSize, true, false);
        goto L431
    L277:
        if (r15.released_by == null) goto L281;
        String r16 = DialogObject.getPublicUsername(MessagesController.getInstance(this.currentAccount).getUserOrChat(DialogObject.getPeerDialogId(r59.gift.released_by)));
        if (r16 == null) goto L281;
        r14 = StarGiftSheet.replaceSingleTagToLink(LocaleController.formatString(org.telegram.messenger.R.string.Gift2ActionReleasedBy, new Object[]{"@".concat(r16)}), null);
    L270:
        r37 = r36;
        r37 = r36;
        if (r1.isOutOwner() == true) goto L274;
        if (r8 == false) goto L274;
        SpannableStringBuilder r38 = new SpannableStringBuilder();
        r38.append("^  ");
        ColoredImageSpan r17 = new ColoredImageSpan(org.telegram.messenger.R.drawable.gift_unpack);
        r17.setScale(0.8f, 0.8f);
        r38.setSpan(r17, 0, 1, 33);
        r38.append(LocaleController.getString(org.telegram.messenger.R.string.Gift2Unpack));
        r37 = r38;
        goto L274
    L258:
        if (r29.limited == false) goto L264;
        int r39 = org.telegram.messenger.R.string.Gift2Limited1OfRibbon;
        int r211 = r29.availability_total;
        if (r211 <= 1500) goto L262;
        Object r212 = AndroidUtilities.formatWholeNumber(r211, 0);
    L263:
        r210 = LocaleController.formatString(r39, new Object[]{r212});
        goto L265
    L262:
        r212 = Integer.valueOf(r211);
        goto L263
    L198:
        TLRPC.TL_textWithEntities r64 = r59.message;
        if (r64 == null) goto L204;
        if (TextUtils.isEmpty(r64.text) == true) goto L204;
        SpannableStringBuilder r213 = new SpannableStringBuilder(r59.message.text);
        this.giftTextPaint.setTextSize(AndroidUtilities.dp(13.0f));
        MessageObject.addEntitiesToText(r213, r59.message.entities, false, false, true, true);
        r28 = MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(r213, this.giftTextPaint.getFontMetricsInt(), false, null), r59.message.entities, this.giftTextPaint.getFontMetricsInt());
    L204:
        if (r59.auction_acquired == false) goto L207;
        r28 = LocaleController.formatString(org.telegram.messenger.R.string.Gift2ActionWonActionText, new Object[]{LocaleController.formatNumber(r59.gift.stars + r59.upgrade_stars, ',')});
        goto L197
    L207:
        if (r35 == false) goto L218;
        if (r59.converted == false) goto L212;
        r28 = LocaleController.formatPluralStringComma("Gift2ActionConvertedInfo", (int) r122);
        goto L197
    L212:
        if (r27 == true) goto L214;
    L216:
        r28 = AndroidUtilities.replaceTags(LocaleController.getString(org.telegram.messenger.R.string.Gift2ActionInfoChannelNoConvert));
        goto L197
    L214:
        if (r122 <= 0) goto L216;
        r28 = AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("Gift2ActionInfoChannel", (int) r122));
        goto L197
    L218:
        if (r20 == true) goto L220;
        if (r8 == false) goto L235;
        if (r1.isOutOwner() == false) goto L232;
        String r214 = LocaleController.formatString(org.telegram.messenger.R.string.Gift2ActionUpgradeOut, new Object[]{UserObject.getForcedFirstName(r43)});
    L233:
        r28 = AndroidUtilities.replaceTags(r214);
        goto L197
    L232:
        r214 = LocaleController.getString(org.telegram.messenger.R.string.Gift2ActionUpgrade);
        goto L233
    L235:
        if (r1.isOutOwner() == false) goto L245;
        if (r27 == false) goto L241;
        if (r122 <= 0) goto L241;
        r28 = AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("Gift2ActionOutInfo", (int) r122, new Object[]{UserObject.getForcedFirstName(r43)}));
    L241:
        if (r59.can_upgrade == false) goto L243;
        r28 = AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.Gift2ActionOutInfoUpgrade, new Object[]{UserObject.getForcedFirstName(r43)}));
        goto L197
    L243:
        r28 = AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.Gift2ActionOutInfoNoConvert, new Object[]{UserObject.getForcedFirstName(r43)}));
        goto L197
    L245:
        if (r59.converted == false) goto L248;
        r28 = LocaleController.formatPluralStringComma("Gift2ActionConvertedInfo", (int) r122);
        goto L197
    L248:
        if (r59.saved == false) goto L252;
        if (r27 == true) goto L251;
        r28 = LocaleController.getString(org.telegram.messenger.R.string.Gift2ActionBotSavedInfo);
        goto L197
    L251:
        r28 = LocaleController.getString(org.telegram.messenger.R.string.Gift2ActionSavedInfo);
        goto L197
    L252:
        if (r27 == true) goto L254;
        r28 = LocaleController.getString(org.telegram.messenger.R.string.Gift2ActionBotInfo);
        goto L197
    L254:
        r28 = AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("Gift2ActionInfo", (int) r122));
        goto L197
    L220:
        if (r59.converted == false) goto L225;
        if (r122 <= 0) goto L225;
        r28 = LocaleController.formatPluralStringComma("Gift2ActionConvertedInfo", (int) r122);
    L225:
        if (r59.can_upgrade == false) goto L227;
        r28 = AndroidUtilities.replaceTags(LocaleController.getString(org.telegram.messenger.R.string.Gift2ActionSelfInfoUpgrade));
        goto L197
    L227:
        r28 = AndroidUtilities.replaceTags(LocaleController.getString(org.telegram.messenger.R.string.Gift2ActionSelfInfoNoConvert));
    L193:
        r27 = false;
    L186:
        if (r59.converted == true) goto L193;
    L173:
        r132.append(LocaleController.getString(org.telegram.messenger.R.string.Gift2ActionSelfTitle));
        goto L182
    L175:
        if (r59.prepaid_upgrade == false) goto L177;
        int r65 = org.telegram.messenger.R.string.Gift2ActionUpgradeTitle;
    L178:
        r132.append(LocaleController.getString(r65)).append(" ");
        if (DialogObject.hasPhoto(r11) == false) goto L181;
        r132.append("a ");
        AvatarSpan r215 = new AvatarSpan(this, this.currentAccount, 18.0f);
        r215.setObject(r11);
        r132.setSpan(r215, r132.length() - 2, r132.length() - 1, 33);
    L181:
        r132.append(DialogObject.getShortName(r11));
        goto L182
    L177:
        r65 = org.telegram.messenger.R.string.Gift2ActionTitle;
        goto L178
    L158:
        if (r59.auction_acquired == false) goto L165;
        if (r26 == null) goto L165;
        r132.append(LocaleController.getString(org.telegram.messenger.R.string.Gift2ActionTitleTo)).append(" ");
        if (DialogObject.hasPhoto(r26) == false) goto L163;
        r132.append("a ");
        AvatarSpan r216 = new AvatarSpan(this, this.currentAccount, 18.0f);
        TLObject r66 = r26;
        r216.setObject(r66);
        r132.setSpan(r216, r132.length() - 2, r132.length() - 1, 33);
    L164:
        r132.append(DialogObject.getShortName(r66));
        goto L182
    L163:
        r66 = r26;
        goto L164
    L147:
        if (r59.converted == true) goto L153;
        r26 = r92;
        if (r59.upgrade_stars <= 0) goto L154;
        if (r59.upgraded == true) goto L154;
        r8 = true;
    L138:
        r77 = false;
        goto L139
    L129:
        if (r59.prepaid_upgrade == true) goto L131;
    L132:
        r35 = true;
        goto L135
    L131:
        if ((r34 instanceof TLRPC.TL_peerChannel) == false) goto L133;
    L283:
        if ((r57 instanceof TLRPC.TL_messageActionGiftTon) == false) goto L285;
        createGiftPremiumLayouts(LocaleController.getString(org.telegram.messenger.R.string.ActionGiftTonTitle), null, null, this.currentMessageObject.messageText, false, LocaleController.getString(org.telegram.messenger.R.string.ActionGiftStarsView), 11, null, this.giftRectSize, true, false);
        this.textLayout = null;
        this.textHeight = 0;
        this.titleLayout = null;
        this.titleHeight = 0;
        this.textY = 0;
        goto L431
    L285:
        createGiftPremiumLayouts(LocaleController.getString(org.telegram.messenger.R.string.ActionStarGiveawayPrizeTitle), null, null, this.currentMessageObject.messageText, false, LocaleController.getString(org.telegram.messenger.R.string.ActionGiftStarsView), 11, null, this.giftRectSize, true, false);
        this.textLayout = null;
        this.textHeight = 0;
        this.titleLayout = null;
        this.titleHeight = 0;
        this.textY = 0;
        goto L431
    L286:
        long r162 = 0;
        if (r76 != 33) goto L303;
        TLRPC.TL_messageActionStarGiftPurchaseOffer r18 = (TLRPC.TL_messageActionStarGiftPurchaseOffer) r56.action;
        SpannableStringBuilder r310 = new SpannableStringBuilder(r4);
        r310.append("\n\n");
        if (r18.accepted == false) goto L292;
        r310.append(AndroidUtilities.replaceTags(LocaleController.getString(org.telegram.messenger.R.string.GiftOfferStatusAccepted)));
    L301:
        createGiftPremiumLayouts(null, null, null, r310, false, null, 11, null, this.giftRectSize, false, false);
        this.textLayout = null;
        this.textHeight = 0;
        this.titleLayout = null;
        this.titleHeight = 0;
        this.textY = 0;
        this.giftRectEmpty = false;
        goto L431
    L292:
        if (r18.declined == false) goto L294;
        r310.append(AndroidUtilities.replaceTags(LocaleController.getString(org.telegram.messenger.R.string.GiftOfferStatusRejected)));
        goto L301
    L294:
        int r110 = Math.max(0, r18.expires_at - ConnectionsManager.getInstance(this.currentAccount).getCurrentTime());
        if (r110 != 0) goto L297;
        r310.append(AndroidUtilities.replaceTags(LocaleController.getString(org.telegram.messenger.R.string.GiftOfferStatusExpired)));
        goto L301
    L297:
        String r111 = LocaleController.formatShortDuration2(r110);
        if (r111.endsWith(".") == false) goto L300;
        r111 = r111.substring(0, r111.length() - 1);
    L300:
        r310.append(AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.GiftOfferStatusPending, new Object[]{r111})));
        goto L301
    L303:
        if (r76 != 34) goto L306;
        createGiftPremiumLayouts(null, null, null, r4, false, null, 11, null, this.giftRectSize, false, true);
        this.textLayout = null;
        this.textHeight = 0;
        this.titleLayout = null;
        this.titleHeight = 0;
        this.textY = 0;
        this.giftRectEmpty = true;
        goto L431
    L306:
        if (r76 != 35) goto L325;
        TLRPC.TL_messageActionNoForwardsRequest r311 = (TLRPC.TL_messageActionNoForwardsRequest) r56.action;
        SpannableStringBuilder r46 = new SpannableStringBuilder();
        String r510 = DialogObject.getShortName(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r1.getDialogId())));
        if (r311.new_value == false) goto L315;
        if (r1.isOut() == false) goto L312;
        CharSequence r112 = LocaleController.getString(org.telegram.messenger.R.string.SharingOfferDisableHeaderYou);
    L313:
        r46.append(r112);
    L320:
        if (r311.new_value == false) goto L322;
        r46.append("\n\n");
        r46.append(createOption(LocaleController.getString(org.telegram.messenger.R.string.SharingOfferDisable1), org.telegram.messenger.R.drawable.floating_check));
        r46.append("\n\n");
        r46.append(createOption(LocaleController.getString(org.telegram.messenger.R.string.SharingOfferDisable2), org.telegram.messenger.R.drawable.floating_check));
        r46.append("\n\n");
        r46.append(createOption(LocaleController.getString(org.telegram.messenger.R.string.SharingOfferDisable3), org.telegram.messenger.R.drawable.floating_check));
        r46.append("\n\n");
        r46.append(createOption(LocaleController.getString(org.telegram.messenger.R.string.SharingOfferDisable4), org.telegram.messenger.R.drawable.floating_check));
    L323:
        createGiftPremiumLayouts(null, null, null, r46, false, null, 11, null, this.giftRectSize, false, true);
        this.textLayout = null;
        this.textHeight = 0;
        this.titleLayout = null;
        this.titleHeight = 0;
        this.textY = 0;
        this.giftRectEmpty = true;
        goto L431
    L322:
        r46.append("\n\n");
        r46.append(createOption(LocaleController.getString(org.telegram.messenger.R.string.SharingOfferEnable1), org.telegram.messenger.R.drawable.floating_check));
        r46.append("\n\n");
        r46.append(createOption(LocaleController.getString(org.telegram.messenger.R.string.SharingOfferEnable2), org.telegram.messenger.R.drawable.floating_check));
        r46.append("\n\n");
        r46.append(createOption(LocaleController.getString(org.telegram.messenger.R.string.SharingOfferEnable3), org.telegram.messenger.R.drawable.floating_check));
        r46.append("\n\n");
        r46.append(createOption(LocaleController.getString(org.telegram.messenger.R.string.SharingOfferEnable4), org.telegram.messenger.R.drawable.floating_check));
        goto L323
    L312:
        r112 = AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.SharingOfferDisableHeaderOther, new Object[]{r510}));
        goto L313
    L315:
        if (r1.isOut() == false) goto L317;
        CharSequence r113 = LocaleController.getString(org.telegram.messenger.R.string.SharingOfferEnableHeaderYou);
    L318:
        r46.append(r113);
        goto L320
    L317:
        r113 = AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.SharingOfferEnableHeaderOther, new Object[]{r510}));
        goto L318
    L325:
        if (r76 != 31) goto L332;
        String r217 = ((TLRPC.TL_chatThemeUniqueGift) ((TLRPC.TL_messageActionSetChatTheme) r56.action).theme).gift.title + " #" + LocaleController.formatNumber(r2.num, ',');
        long r312 = r1.getFromChatId();
        if (UserConfig.getInstance(this.currentAccount).getClientUserId() != r312) goto L329;
        String r114 = LocaleController.formatString(org.telegram.messenger.R.string.GiftThemesSetByYou, new Object[]{r217});
    L330:
        createGiftPremiumLayouts(null, null, null, AndroidUtilities.replaceTags(r114), false, LocaleController.getString(org.telegram.messenger.R.string.GiftThemesSetActionView), 11, null, this.giftRectSize, true, false);
        this.textLayout = null;
        this.textHeight = 0;
        this.titleLayout = null;
        this.titleHeight = 0;
        this.textY = 0;
        goto L431
    L329:
        r114 = LocaleController.formatString(org.telegram.messenger.R.string.GiftThemesSetByOther, new Object[]{DialogObject.getShortName(this.currentAccount, r312), r217});
        goto L330
    L332:
        if (r76 != 37) goto L339;
        TLRPC.TL_messageActionChangeCommunity r218 = (TLRPC.TL_messageActionChangeCommunity) r56.action;
        long r313 = DialogObject.getPeerDialogId(r56.peer_id);
        boolean r511 = ChatObject.isChannelAndNotMegaGroup(-r313, this.currentAccount);
        if (r313 <= 0) goto L336;
        boolean r314 = true;
    L337:
        String r47 = DialogObject.getShortName(this.currentAccount, DialogObject.getPeerDialogId(r1.messageOwner.from_id));
        String r219 = DialogObject.getShortName(this.currentAccount, -r218.community_id);
        SpannableStringBuilder r67 = new SpannableStringBuilder();
        r67.append(CommunityUtils.buildServiceMessageText(r1, r219, r47, r511, r314));
        createGiftPremiumLayouts(null, null, null, r67, false, LocaleController.getString(org.telegram.messenger.R.string.GiftThemesSetActionView), 11, null, this.giftRectSize, true, false);
        this.textLayout = null;
        this.textHeight = 0;
        this.titleLayout = null;
        this.titleHeight = 0;
        this.textY = 0;
        goto L431
    L336:
        r314 = false;
        goto L337
    L339:
        if (r76 != 18) goto L361;
        TLRPC.MessageAction r220 = r56.action;
        if ((r220 instanceof TLRPC.TL_messageActionGiftPremium) == false) goto L344;
        TLRPC.TL_textWithEntities r221 = ((TLRPC.TL_messageActionGiftPremium) r220).message;
    L347:
        if (r221 != null) goto L349;
    L351:
        if (r14 != null) goto L353;
        r14 = LocaleController.getString(org.telegram.messenger.R.string.ActionGiftPremiumText);
    L353:
        CharSequence r48 = r14;
        if (isGiftCode() == true) goto L356;
    L358:
        int r222 = org.telegram.messenger.R.string.ActionGiftPremiumView;
    L359:
        createGiftPremiumLayouts(LocaleController.formatPluralStringComma("ActionGiftPremiumTitle2", r1.messageOwner.action.months), null, null, r48, true, LocaleController.getString(r222), 11, null, this.giftRectSize, false, false);
        goto L431
    L356:
        if (isSelfGiftCode() == true) goto L358;
        r222 = org.telegram.messenger.R.string.GiftPremiumUseGiftBtn;
        goto L359
    L349:
        if (TextUtils.isEmpty(r221.text) == true) goto L351;
        SpannableStringBuilder r49 = new SpannableStringBuilder(r221.text);
        this.giftTextPaint.setTextSize(AndroidUtilities.dp(13.0f));
        MessageObject.addEntitiesToText(r49, r221.entities, false, false, true, true);
        r14 = MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(r49, this.giftTextPaint.getFontMetricsInt(), false, null), r221.entities, this.giftTextPaint.getFontMetricsInt());
        goto L351
    L344:
        if ((r220 instanceof TLRPC.TL_messageActionGiftCode) == false) goto L346;
        r221 = ((TLRPC.TL_messageActionGiftCode) r220).message;
        goto L347
    L346:
        r221 = null;
        goto L347
    L361:
        if (r76 != 21) goto L400;
        TLRPC.TL_messageActionSuggestProfilePhoto r223 = (TLRPC.TL_messageActionSuggestProfilePhoto) r56.action;
        MessagesController r315 = MessagesController.getInstance(this.currentAccount);
        if (r1.isOutOwner() == true) goto L366;
        r162 = r1.getDialogId();
    L366:
        TLRPC.User r316 = r315.getUser(Long.valueOf(r162));
        if (r223.video == true) goto L376;
        TLRPC.Photo r410 = r223.photo;
        if (r410 == null) goto L375;
        ArrayList<TLRPC.VideoSize> r411 = r410.video_sizes;
        if (r411 == null) goto L375;
        if (r411.isEmpty() == false) goto L376;
    L375:
        boolean r412 = false;
    L378:
        if (r316.id != UserConfig.getInstance(this.currentAccount).clientUserId) goto L384;
        TLRPC.User r115 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r1.getDialogId()));
        if (r412 == false) goto L382;
        CharSequence r116 = LocaleController.formatString(org.telegram.messenger.R.string.ActionSuggestVideoFromYouDescription, new Object[]{r115.first_name});
    L383:
        CharSequence r413 = r116;
        if (r223.video == true) goto L397;
        ArrayList<TLRPC.VideoSize> r117 = r223.photo.video_sizes;
        if (r117 != null) goto L393;
    L395:
        CharSequence r118 = LocaleController.getString(org.telegram.messenger.R.string.ViewPhotoAction);
    L398:
        createGiftPremiumLayouts(null, null, null, r413, false, r118, 11, null, this.giftRectSize, true, false);
        this.textLayout = null;
        this.textHeight = 0;
        this.titleLayout = null;
        this.titleHeight = 0;
        this.textY = 0;
        goto L431
    L393:
        if (r117.isEmpty() == true) goto L395;
    L397:
        r118 = LocaleController.getString(org.telegram.messenger.R.string.ViewVideoAction);
        goto L398
    L382:
        r116 = LocaleController.formatString(org.telegram.messenger.R.string.ActionSuggestPhotoFromYouDescription, new Object[]{r115.first_name});
        goto L383
    L384:
        String r119 = r316.first_name;
        if (r412 == false) goto L387;
        r116 = LocaleController.formatString(org.telegram.messenger.R.string.ActionSuggestVideoToYouDescription, new Object[]{r119});
        goto L383
    L387:
        r116 = LocaleController.formatString(org.telegram.messenger.R.string.ActionSuggestPhotoToYouDescription, new Object[]{r119});
    L376:
        r412 = true;
        goto L378
    L400:
        if (r76 != 22) goto L424;
        MessagesController r224 = MessagesController.getInstance(this.currentAccount);
        if (r1.isOutOwner() == false) goto L404;
        long r317 = 0;
    L405:
        TLRPC.User r225 = r224.getUser(Long.valueOf(r317));
        if (r1.getDialogId() >= 0) goto L411;
        CharSequence r120 = r1.messageText;
    L408:
        CharSequence r414 = r120;
        CharSequence r68 = null;
    L409:
        boolean r10 = true;
    L422:
        createGiftPremiumLayouts(null, null, null, r414, false, r68, 11, null, this.giftRectSize, r10, false);
        this.textLayout = null;
        this.textHeight = 0;
        this.titleLayout = null;
        this.titleHeight = 0;
        this.textY = 0;
        goto L431
    L411:
        if (r1.isOutOwner() == false) goto L413;
    L417:
        if (r225 != null) goto L419;
    L421:
        r414 = r1.messageText;
        r68 = LocaleController.getString(org.telegram.messenger.R.string.ViewWallpaperAction);
        goto L409
    L419:
        if (r225.id != UserConfig.getInstance(this.currentAccount).clientUserId) goto L421;
        r120 = r1.messageText;
        goto L408
    L413:
        if (r1.isWallpaperForBoth() == false) goto L417;
        if (r1.isCurrentWallpaper() == false) goto L417;
        r414 = r1.messageText;
        r68 = LocaleController.getString(org.telegram.messenger.R.string.RemoveWallpaperAction);
        r10 = false;
        goto L422
    L404:
        r317 = r1.getDialogId();
        goto L405
    L424:
        if (r1.isStoryMention() == false) goto L431;
        TLRPC.User r226 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r1.messageOwner.media.user_id));
        if (r226.self == false) goto L429;
        CharSequence r121 = AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.StoryYouMentionedTitle, new Object[]{MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r1.getDialogId())).first_name}));
    L430:
        createGiftPremiumLayouts(null, null, null, r121, false, LocaleController.getString(org.telegram.messenger.R.string.StoryMentionedAction), 11, null, this.giftRectSize, true, false);
        this.textLayout = null;
        this.textHeight = 0;
        this.titleLayout = null;
        this.titleHeight = 0;
        this.textY = 0;
        goto L431
    L429:
        r121 = AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.StoryMentionedTitle, new Object[]{r226.first_name}));
    L431:
        this.reactionsLayoutInBubble.x = AndroidUtilities.dp(12.0f);
        this.reactionsLayoutInBubble.measure(this.previousWidth - AndroidUtilities.dp(24.0f), 1);
        return;
    L5:
        if (r1.isExpiredStory() == true) goto L7;
        ChatActionCellDelegate r415 = this.delegate;
        if (r415 != null) goto L13;
    L17:
        r4 = null;
    L18:
        if (r4 != null) goto L47;
        TLRPC.Message r416 = r1.messageOwner;
        if (r416 == null) goto L42;
        TLRPC.MessageMedia r417 = r416.media;
        if (r417 == null) goto L42;
        if (r417.ttl_seconds == 0) goto L42;
        if (r417.photo == null) goto L28;
        r4 = LocaleController.getString(org.telegram.messenger.R.string.AttachPhotoExpired);
    L44:
        if (ChatUtils.getInstance().shouldAddTimestamp(this.currentMessageObject, r4) == false) goto L47;
        r4 = ChatUtils.getInstance().addTimestamp(r4, this.currentMessageObject.messageOwner.date, this.themeDelegate);
        goto L47
    L28:
        TLRPC.Document r512 = r417.document;
        if ((r512 instanceof TLRPC.TL_documentEmpty) == true) goto L36;
        if ((r417 instanceof TLRPC.TL_messageMediaDocument) == false) goto L34;
        if (r512 == null) goto L36;
    L34:
        r4 = AnimatedEmojiSpan.cloneSpans(r1.messageText);
    L36:
        if (r417.voice == false) goto L39;
        r4 = LocaleController.getString(org.telegram.messenger.R.string.AttachVoiceExpired);
        goto L44
    L39:
        if (r417.round == false) goto L41;
        r4 = LocaleController.getString(org.telegram.messenger.R.string.AttachRoundExpired);
        goto L44
    L41:
        r4 = LocaleController.getString(org.telegram.messenger.R.string.AttachVideoExpired);
    L42:
        r4 = AnimatedEmojiSpan.cloneSpans(r1.messageText);
        goto L44
    L13:
        if (r415.getTopicId() != 0) goto L17;
        if (MessageObject.isTopicActionMessage(r1) == false) goto L17;
        r4 = ForumUtilities.createActionTextWithTopic(MessagesController.getInstance(this.currentAccount).getTopicsController().findTopic(-r1.getDialogId(), MessageObject.getTopicId(this.currentAccount, r1.messageOwner, true)), r1);
        goto L18
    L7:
        if (r1.messageOwner.media.user_id == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L9;
        r4 = StoriesUtilities.createExpiredStoryString(true, org.telegram.messenger.R.string.ExpiredStoryMention, new Object[0]);
        goto L18
    L9:
        r4 = StoriesUtilities.createExpiredStoryString(true, org.telegram.messenger.R.string.ExpiredStoryMentioned, new Object[]{MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r1.getDialogId())).first_name});
        goto L18
    }

    private boolean checkBotButtonMotionEvent(MotionEvent r14) {
        if (this.botButtons.isEmpty() == false) goto L5;
        return false;
    L5:
        int r0 = (int) r14.getX();
        int r2 = (int) r14.getY();
        float r3 = (getWidth() - this.giftRectSize) / 2.0f;
        float r5 = (((this.textY + this.textHeight) + AndroidUtilities.dp(4.0f)) + this.backgroundRectHeight) + AndroidUtilities.dp(4.0f);
        float r7 = (this.giftRectSize - AndroidUtilities.dp(4.0f)) / 2.0f;
        if (r14.getAction() != 0) goto L19;
        this.pressedBotButton = -1;
        int r142 = 0;
    L9:
        if (r142 >= this.botButtons.size()) goto L42;
        BotButton r4 = this.botButtons.get(r142);
        float r9 = ((AndroidUtilities.dp(4.0f) + r7) * r142) + r3;
        RectF r11 = AndroidUtilities.rectTmp;
        r11.set(r9, r5, r9 + r7, r4.height + r5);
        float r92 = r0;
        float r10 = r2;
        if (r11.contains(r92, r10) == true) goto L12;
        r142 = r142 + 1;
        goto L9
    L12:
        this.pressedBotButton = r142;
        invalidateOutbounds();
        if (r4.selectorDrawable != null) goto L15;
        Drawable r143 = Theme.createRadSelectorDrawable(getThemedColor(Theme.key_chat_serviceBackgroundSelector), 6, 6);
        r4.selectorDrawable = r143;
        r143.setCallback(this);
    L15:
        r4.selectorDrawable.setHotspot(r92, r10);
        r4.selectorDrawable.setState(this.pressedState);
        r4.setPressed(!r4.isLocked);
        return true;
    L42:
        return false;
    L19:
        if (r14.getAction() != 1) goto L35;
        if (this.pressedBotButton == (-1)) goto L42;
        playSoundEffect(0);
        BotButton r144 = this.botButtons.get(this.pressedBotButton);
        Drawable r02 = r144.selectorDrawable;
        if (r02 == null) goto L25;
        r02.setState(StateSet.NOTHING);
    L25:
        r144.setPressed(false);
        if (this.delegate != null) goto L28;
    L32:
        this.pressedBotButton = -1;
        invalidateOutbounds();
        return false;
    L28:
        if (r144.isLocked == true) goto L32;
        BotInlineKeyboard.ButtonCustom r145 = r144.buttonCustom;
        if (r145 == null) goto L32;
        didPressCustomBotButton(r145);
        goto L32
    L35:
        if (r14.getAction() != 3) goto L42;
        int r146 = this.pressedBotButton;
        if (r146 == (-1)) goto L42;
        BotButton r147 = this.botButtons.get(r146);
        Drawable r03 = r147.selectorDrawable;
        if (r03 == null) goto L41;
        r03.setState(StateSet.NOTHING);
    L41:
        r147.setPressed(false);
        this.pressedBotButton = -1;
        invalidateOutbounds();
        goto L42
    }

    private void checkLeftRightBounds() {
        this.backgroundLeft = (int) Math.min(this.backgroundLeft, this.rect.left);
        this.backgroundRight = (int) Math.max(this.backgroundRight, this.rect.right);
    }

    private void createGiftPremiumChannelLayouts() {
        int r5 = this.giftRectSize - AndroidUtilities.dp(16.0f);
        this.giftTitlePaint.setTextSize(AndroidUtilities.dp(14.0f));
        this.giftTextPaint.setTextSize(AndroidUtilities.dp(13.0f));
        TLRPC.TL_messageActionGiftCode r0 = (TLRPC.TL_messageActionGiftCode) this.currentMessageObject.messageOwner.action;
        int r1 = r0.months;
        TLRPC.Chat r2 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-DialogObject.getPeerDialogId(r0.boost_peer)));
        if (r2 != null) goto L5;
        String r22 = null;
    L6:
        boolean r3 = r0.via_giveaway;
        if (r0.unclaimed == false) goto L9;
        String r4 = LocaleController.getString("BoostingUnclaimedPrize", org.telegram.messenger.R.string.BoostingUnclaimedPrize);
    L11:
        if (r1 != 12) goto L13;
        String r12 = LocaleController.formatPluralString("BoldYears", 1, new Object[0]);
    L15:
        if (r3 == false) goto L21;
        if (r0.unclaimed == false) goto L19;
        SpannableStringBuilder r02 = new SpannableStringBuilder(AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.BoostingYouHaveUnclaimedPrize, new Object[]{r22})));
        r02.append("\n\n");
        r02.append(AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.BoostingUnclaimedPrizeDuration, new Object[]{r12})));
    L25:
        String r13 = LocaleController.getString("BoostingReceivedGiftOpenBtn", org.telegram.messenger.R.string.BoostingReceivedGiftOpenBtn);
        SpannableStringBuilder r32 = SpannableStringBuilder.valueOf(r4);
        r32.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r32.length(), 33);
        TextPaint r42 = this.giftTitlePaint;
        Layout.Alignment r6 = Layout.Alignment.ALIGN_CENTER;
        this.giftPremiumTitleLayout = new StaticLayout(r32, r42, r5, r6, 1.1f, 0.0f, false);
        this.giftPremiumSubtitleLayout = null;
        this.giftPremiumReleasedText = null;
        TextLayout r23 = this.giftPremiumText;
        if (r23 == null) goto L28;
        r23.detach();
    L28:
        TextLayout r24 = new TextLayout(this);
        this.giftPremiumText = r24;
        r24.setText(r02, this.giftTextPaint, r5);
        SpannableStringBuilder r33 = SpannableStringBuilder.valueOf(r13);
        r33.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r33.length(), 33);
        this.giftPremiumTextCollapsed = false;
        this.giftPremiumTextCollapsedHeight = 0;
        this.giftPremiumTextMore = null;
        StaticLayout r25 = new StaticLayout(r33, (TextPaint) getThemedPaint("paintChatActionText"), r5, r6, 1.0f, 0.0f, false);
        this.giftPremiumButtonLayout = r25;
        this.buttonClickableAsImage = true;
        this.giftPremiumButtonWidth = measureLayoutWidth(r25);
        return;
    L19:
        r02 = new SpannableStringBuilder(AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.BoostingReceivedPrizeFrom, new Object[]{r22})));
        r02.append("\n\n");
        r02.append(AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.BoostingReceivedPrizeDuration, new Object[]{r12})));
        goto L25
    L21:
        if (r22 != null) goto L23;
        String r26 = LocaleController.getString(org.telegram.messenger.R.string.BoostingReceivedGiftNoName);
    L24:
        r02 = new SpannableStringBuilder(AndroidUtilities.replaceTags(r26));
        r02.append("\n\n");
        r02.append(AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.BoostingReceivedGiftDuration, new Object[]{r12})));
        goto L25
    L23:
        r26 = LocaleController.formatString("BoostingReceivedGiftFrom", org.telegram.messenger.R.string.BoostingReceivedGiftFrom, new Object[]{r22});
        goto L24
    L13:
        r12 = LocaleController.formatPluralString("BoldMonths", r1, new Object[0]);
        goto L15
    L9:
        r4 = LocaleController.getString("BoostingCongratulations", org.telegram.messenger.R.string.BoostingCongratulations);
        goto L11
    L5:
        r22 = r2.title;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v7 */
    private void createGiftPremiumLayouts(CharSequence r18, CharSequence r19, CharSequence r20, CharSequence r21, boolean r22, CharSequence r23, int r24, CharSequence r25, int r26, boolean r27, boolean r28) {
        CharSequence r2 = r21;
        int r5 = r26 - AndroidUtilities.dp(16.0f);
        MessageObject r6 = this.currentMessageObject;
        if (r6 != null) goto L5;
    L7:
        int r11 = r5;
        if (r18 == null) goto L16;
        MessageObject r9 = this.currentMessageObject;
        if (r9 != null) goto L12;
    L14:
        this.giftTitlePaint.setTextSize(AndroidUtilities.dp(16.0f));
    L15:
        SpannableStringBuilder r92 = SpannableStringBuilder.valueOf(r18);
        r92.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r92.length(), 33);
        ?? r4 = 0;
        this.giftPremiumTitleLayout = new StaticLayout(r92, this.giftTitlePaint, r11, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    L18:
        if (r19 == null) goto L20;
        this.giftSubtitlePaint.setTextSize(AndroidUtilities.dp(13.0f));
        this.giftPremiumSubtitleLayout = new StaticLayout(r19, this.giftSubtitlePaint, r11, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    L21:
        if (r20 == null) goto L23;
        Text r8 = new Text(r20, 10.0f);
        this.giftPremiumReleasedText = r8;
        r8.paint.linkColor = -1;
    L24:
        MessageObject r1 = this.currentMessageObject;
        if (r1 != null) goto L27;
    L29:
        if (r1 != null) goto L31;
    L42:
        this.giftTextPaint.setTextSize(AndroidUtilities.dp(15.0f));
    L43:
        int r12 = r11 - AndroidUtilities.dp(12.0f);
        MessageObject r7 = this.currentMessageObject;
        if (r7 != null) goto L46;
    L54:
        TextLayout r72 = this.giftPremiumText;
        if (r2 != 0) goto L59;
        if (r72 == null) goto L58;
        r72.detach();
        this.giftPremiumText = null;
    L58:
        this.giftPremiumTextCollapsed = r4;
    L74:
        if (r23 == null) goto L82;
        SpannableStringBuilder r93 = SpannableStringBuilder.valueOf(r23);
        r93.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r4, r93.length(), 33);
        boolean r13 = true;
        StaticLayout r82 = new StaticLayout(r93, (TextPaint) getThemedPaint("paintChatActionText"), r11, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        this.giftPremiumButtonLayout = r82;
        if (r27 == true) goto L78;
    L80:
        r13 = r4;
    L81:
        this.buttonClickableAsImage = r13;
        this.giftPremiumButtonWidth = measureLayoutWidth(r82);
    L83:
        if (r25 != null) goto L85;
        this.giftRibbonPath = null;
        this.giftRibbonText = null;
        return;
    L85:
        if (this.giftRibbonPaintEffect != null) goto L88;
        this.giftRibbonPaintEffect = new CornerPathEffect(AndroidUtilities.dp(5.0f));
    L88:
        if (this.giftRibbonPath != null) goto L90;
        Path r14 = new Path();
        this.giftRibbonPath = r14;
        GiftSheet.RibbonDrawable.fillRibbonPath(r14, 1.35f, r4);
    L90:
        Text r15 = new Text(r25, r24, AndroidUtilities.bold());
        this.giftRibbonText = r15;
        r15.ellipsize(AndroidUtilities.dp(62.0f));
        return;
    L78:
        if (this.giftPremiumTextCollapsed == true) goto L80;
    L82:
        this.giftPremiumButtonLayout = null;
        this.buttonClickableAsImage = r4;
        this.giftPremiumButtonWidth = 0.0f;
        goto L83
    L59:
        if (r72 != null) goto L94;
        this.giftPremiumText = new TextLayout(this);
    L94:
        r2 = Emoji.replaceEmoji(r2, this.giftTextPaint.getFontMetricsInt(), r4);     // Catch: Exception -> L93
    L62:
        this.giftPremiumText.setText(r2, this.giftTextPaint, r12);
        if (r22 == true) goto L65;
    L67:
        this.giftPremiumTextCollapsed = r4;
        this.giftPremiumTextExpandedAnimated.set(true, true);
        this.giftPremiumTextCollapsedHeight = r4;
    L69:
        if (this.giftPremiumTextCollapsed == false) goto L74;
        int r73 = this.giftPremiumText.layout.getLineEnd(2) - 1;
        TextLayout r94 = this.giftPremiumText;
        CharSequence r29 = r2;
        if (r73 < 0) goto L73;
        r29 = r2.subSequence(r4, r73);
    L73:
        r94.setText(r29, this.giftTextPaint, r12);
        goto L74
    L65:
        if (this.giftPremiumText.layout.getLineCount() <= 3) goto L67;
        this.giftPremiumTextCollapsed = !this.giftPremiumTextUncollapsed;
        this.giftPremiumTextCollapsedHeight = this.giftPremiumText.layout.getLineBottom(2);
        this.giftPremiumTextMore = new Text(LocaleController.getString(org.telegram.messenger.R.string.Gift2CaptionMore), this.giftTextPaint.getTextSize() / AndroidUtilities.density, AndroidUtilities.bold());
        int r95 = this.giftPremiumText.layout.getLineBottom(2);
        this.giftPremiumTextMoreY = r95;
        this.giftPremiumTextMoreH = r95 - this.giftPremiumText.layout.getLineTop(2);
        this.giftPremiumTextMoreX = (int) this.giftPremiumText.layout.getLineRight(2);
        goto L69
    L46:
        if (r7.type != 22) goto L54;
        if (r7.getDialogId() < 0) goto L54;
        int r74 = HintView2.cutInFancyHalf(r2, this.giftTextPaint);
        if (r74 >= r12) goto L54;
        if (r74 <= (r12 / 5.0f)) goto L54;
        r12 = r74;
        goto L54
    L31:
        if (isNewStyleButtonLayout() == true) goto L41;
        int r16 = this.currentMessageObject.type;
        if (r16 == 30) goto L41;
        if (r16 == 18) goto L41;
        if (r16 == 31) goto L41;
        if (r16 == 37) goto L41;
        if (r16 != 33) goto L42;
    L41:
        this.giftTextPaint.setTextSize(AndroidUtilities.dp(13.0f));
        goto L43
    L27:
        if (r1.type != 35) goto L29;
        this.giftTextPaint.setTextSize(AndroidUtilities.dp(14.3f));
        goto L43
    L23:
        this.giftPremiumReleasedText = null;
        goto L24
    L20:
        this.giftPremiumSubtitleLayout = null;
        goto L21
    L12:
        if (r9.type != 30) goto L14;
        this.giftTitlePaint.setTextSize(AndroidUtilities.dp(14.0f));
        goto L15
    L16:
        r4 = 0;
        this.giftPremiumTitleLayout = null;
        goto L18
    L5:
        if (r6.type != 30) goto L7;
        r5 = r5 - AndroidUtilities.dp(16.0f);
        goto L7
    }

    private void createLayout(CharSequence r20, int r21) {
        int r0 = r21 - AndroidUtilities.dp(30.0f);
        if (this.isSideMenued == false) goto L6;
        r0 = r0 - AndroidUtilities.dp(64.0f);
    L6:
        if (isMessageActionSuggestedPostApproval() == true) goto L8;
    L12:
        if (r0 < 0) goto L105;
        int r3 = this.overriddenMaxWidth;
        if (r3 <= 0) goto L17;
        r0 = Math.min(r3, r0);
    L17:
        int r9 = r0;
        this.invalidatePath = true;
        if (isMessageActionSuggestedPostApproval() == true) goto L32;
        MessageObject r32 = this.currentMessageObject;
        if (r32 == null) goto L26;
        int r4 = r32.type;
        if (r4 == 34) goto L32;
        if (r4 == 35) goto L32;
    L26:
        if (r32 != null) goto L28;
    L31:
        TextPaint r33 = (TextPaint) getThemedPaint("paintChatActionText");
    L30:
        TextPaint r10 = r33;
        r10.linkColor = r10.getColor();
        int r11 = 0;
        if (isMessageActionSuggestedPostApproval() == true) goto L36;
        CharSequence r34 = r20;
    L43:
        if (isMessageActionSuggestedPostApproval() == false) goto L46;
        Layout.Alignment r42 = Layout.Alignment.ALIGN_NORMAL;
    L47:
        StaticLayout r2 = new StaticLayout(r34, r10, r9, r42, 1.0f, 0.0f, false);
        CharSequence r102 = r34;
        this.textLayout = r2;
        this.titleLayout = null;
        MessageObject r22 = this.currentMessageObject;
        if (r22 == null) goto L59;
        TLRPC.Message r23 = r22.messageOwner;
        if (r23 == null) goto L59;
        TLRPC.MessageAction r24 = r23.action;
        if ((r24 instanceof TLRPC.TL_messageActionSuggestedPostApproval) == false) goto L59;
        if (((TLRPC.TL_messageActionSuggestedPostApproval) r24).rejected == true) goto L59;
        if (((TLRPC.TL_messageActionSuggestedPostApproval) r24).balance_too_low == true) goto L59;
        this.titleLayout = new StaticLayout(Emoji.replaceEmoji(AndroidUtilities.replaceTags(LocaleController.getString(org.telegram.messenger.R.string.SuggestionAgreementReached)), r10.getFontMetricsInt(), false, null, 0, 1.0f, 0), r10, r9, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    L59:
        if (this.canDrawInParent == false) goto L65;
        ChatActionCellDelegate r25 = this.delegate;
        if (r25 == null) goto L65;
        if (r25.canDrawOutboundsContent() == true) goto L65;
        boolean r26 = true;
    L66:
        this.animatedEmojiStack = AnimatedEmojiSpan.update(0, this, r26, this.animatedEmojiStack, new Layout[]{this.textLayout});
        this.textHeight = 0;
        this.textWidth = 0;
        this.titleHeight = 0;
        StaticLayout r02 = this.titleLayout;
        if (r02 == null) goto L69;
        int r03 = r02.getHeight();
        this.titleHeight = r03;
        this.titleHeight = r03 + AndroidUtilities.dp(12.0f);
    L69:
        MessageObject r04 = this.currentMessageObject;
        if (r04 != null) goto L72;
    L100:
        int r05 = this.textLayout.getLineCount();     // Catch: Exception -> L80
    L74:
        if (r11 >= r05) goto L86;
        float r27 = this.textLayout.getLineWidth(r11);     // Catch: Exception -> L82
        float r35 = r9;
        if (r27 <= r35) goto L78;
        r27 = r35;
    L78:
        this.textHeight = (int) Math.max(this.textHeight, Math.ceil(this.textLayout.getLineBottom(r11)));     // Catch: Exception -> L82
        this.textWidth = (int) Math.max(this.textWidth, Math.ceil(r27));     // Catch: Exception -> L80
        r11 = r11 + 1;     // Catch: Exception -> L80
        goto L74
    L82:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L80
        return;
    L86:
        this.textX = (r21 - this.textWidth) / 2;
        int r06 = AndroidUtilities.dp(7.0f);
        this.textY = r06;
        if (this.titleLayout == null) goto L90;
        this.textY = r06 + (this.titleHeight + AndroidUtilities.dp(11.0f));
    L90:
        if (isMessageActionSuggestedPostApproval() == false) goto L93;
        int r07 = this.textWidth;
    L94:
        this.textXLeft = (r21 - r07) / 2;
        this.titleXLeft = (r21 - r9) / 2;
        this.spoilersPool.addAll(this.spoilers);
        this.spoilers.clear();
        if ((r102 instanceof Spannable) == false) goto L104;
        StaticLayout r28 = this.textLayout;
        int r36 = this.textX;
        SpoilerEffect.addSpoilers(this, r28, r36, r36 + this.textWidth, (Spannable) r102, this.spoilersPool, this.spoilers, null);
        return;
    L104:
        return;
    L93:
        r07 = this.textLayout.getWidth();
    L80:
        e = move-exception;
        FileLog.e(e);
        goto L86
    L72:
        if (r04.isRepostPreview == true) goto L86;
    L65:
        r26 = false;
        goto L66
    L46:
        r42 = Layout.Alignment.ALIGN_CENTER;
        goto L47
    L36:
        if ((r20 instanceof Spannable) == false) goto L40;
        Spannable r37 = (Spannable) r20;
        Emoji.EmojiSpan[] r43 = (Emoji.EmojiSpan[]) r37.getSpans(0, r37.length(), Emoji.EmojiSpan.class);
        int r5 = r43.length;
        int r6 = 0;
    L38:
        if (r6 >= r5) goto L40;
        r37.removeSpan(r43[r6]);
        r6 = r6 + 1;
    L40:
        r34 = Emoji.replaceEmoji(r20, r10.getFontMetricsInt(), false, null, 0, 0.85f, 0);
        goto L43
    L28:
        if (r32.drawServiceWithDefaultTypeface == false) goto L31;
        r33 = (TextPaint) getThemedPaint("paintChatActionText2");
    L32:
        r33 = (TextPaint) getThemedPaint("paintChatActionText3");
        goto L30
    L105:
        return;
    L8:
        if (this.isSideMenued == false) goto L10;
        float r38 = 28.0f;
    L11:
        r0 = Math.min(r0 - AndroidUtilities.dp(r38), AndroidUtilities.dp(272.0f));
        goto L12
    L10:
        r38 = 82.0f;
        goto L11
    }

    private CharSequence createOption(String r8, int r9) {
        SpannableStringBuilder r7 = new SpannableStringBuilder(AndroidUtilities.replaceArrows(r8, false, AndroidUtilities.dp(6.0f), -AndroidUtilities.dp(1.3f), 0.8f, r9));
        r7.insert(0, "*");
        r7.setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(18.0f)), 0, 1, 33);
        if (Build.VERSION.SDK_INT < 29) goto L5;
        ChatActionCell$$ExternalSyntheticApiModelOutline1.m();
        r7.setSpan(ChatActionCell$$ExternalSyntheticApiModelOutline0.m(AndroidUtilities.dp(12.0f)), 0, r7.length(), 33);
    L5:
        r7.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL), 0, r7.length(), 33);
        return r7;
    }

    private void didPressCustomBotButton(BotInlineKeyboard.ButtonCustom r10) {
        if (getMessageObject() == null) goto L86;
        int r102 = r10.id;
        BaseFragment r1 = null;
        if (r102 != 5) goto L17;
        ChatActionCellDelegate r103 = this.delegate;
        if (r103 == null) goto L10;
        r1 = r103.getBaseFragment();
    L10:
        BaseFragment r2 = r1;
        if (r2 != null) goto L13;
        return;
    L13:
        if (this.currentMessageObject == null) goto L73;
        AlertsCreator.showSimpleConfirmAlert(r2, LocaleController.getString(org.telegram.messenger.R.string.GiftOfferRejectConfirmTitle), AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.GiftOfferRejectConfirmText, new Object[]{DialogObject.getShortName(this.currentMessageObject.getDialogId())})), LocaleController.getString(org.telegram.messenger.R.string.GiftOfferRejectConfirmConfirm), true, new ChatActionCell$$ExternalSyntheticLambda10(this, r2));
        return;
    L73:
        return;
    L17:
        if (r102 != 6) goto L27;
        MessageObject r104 = this.currentMessageObject;
        if (r104 == null) goto L74;
        TLRPC.Message r105 = r104.messageOwner;
        if (r105 == null) goto L75;
        TLRPC.MessageAction r106 = r105.action;
        if ((r106 instanceof TLRPC.TL_messageActionStarGiftPurchaseOffer) == false) goto L76;
        TLRPC.TL_messageActionStarGiftPurchaseOffer r8 = (TLRPC.TL_messageActionStarGiftPurchaseOffer) r106;
        GiftOfferSheet.openOfferAcceptAlert(LaunchActivity.getLastFragment(), getContext(), this.themeDelegate, this.currentAccount, this.currentMessageObject.getDialogId(), this.currentMessageObject.getId(), r8);
        return;
    L76:
        return;
    L75:
        return;
    L74:
        return;
    L27:
        if (r102 != 7) goto L50;
        ChatActionCellDelegate r107 = this.delegate;
        if (r107 == null) goto L31;
        r1 = r107.getBaseFragment();
    L31:
        BaseFragment r22 = r1;
        if (r22 == null) goto L77;
        MessageObject r108 = this.currentMessageObject;
        if (r108 == null) goto L78;
        TLRPC.Message r109 = r108.messageOwner;
        if (r109 == null) goto L79;
        TLRPC.MessageAction r1010 = r109.action;
        if ((r1010 instanceof TLRPC.TL_messageActionNoForwardsRequest) == false) goto L80;
        TLRPC.TL_messageActionNoForwardsRequest r1011 = (TLRPC.TL_messageActionNoForwardsRequest) r1010;
        if (r1011.prev_value == false) goto L42;
        int r0 = org.telegram.messenger.R.string.SharingOfferDisableCancelTitle;
    L43:
        String r3 = LocaleController.getString(r0);
        if (r1011.prev_value == false) goto L46;
        int r02 = org.telegram.messenger.R.string.SharingOfferDisableCancelText;
    L47:
        AlertsCreator.showSimpleConfirmAlert(r22, r3, LocaleController.getString(r02), LocaleController.getString(org.telegram.messenger.R.string.SharingOfferCancelYes), false, new ChatActionCell$$ExternalSyntheticLambda11(this, r1011));
        return;
    L46:
        r02 = org.telegram.messenger.R.string.SharingOfferEnableCancelText;
        goto L47
    L42:
        r0 = org.telegram.messenger.R.string.SharingOfferEnableCancelTitle;
        goto L43
    L80:
        return;
    L79:
        return;
    L78:
        return;
    L77:
        return;
    L50:
        if (r102 != 8) goto L81;
        ChatActionCellDelegate r1012 = this.delegate;
        if (r1012 == null) goto L54;
        r1 = r1012.getBaseFragment();
    L54:
        BaseFragment r23 = r1;
        if (r23 == null) goto L82;
        MessageObject r1013 = this.currentMessageObject;
        if (r1013 == null) goto L83;
        TLRPC.Message r1014 = r1013.messageOwner;
        if (r1014 == null) goto L84;
        TLRPC.MessageAction r1015 = r1014.action;
        if ((r1015 instanceof TLRPC.TL_messageActionNoForwardsRequest) == false) goto L85;
        TLRPC.TL_messageActionNoForwardsRequest r1016 = (TLRPC.TL_messageActionNoForwardsRequest) r1015;
        if (r1016.new_value == false) goto L65;
        int r03 = org.telegram.messenger.R.string.SharingOfferDisableCancelTitle;
    L66:
        String r32 = LocaleController.getString(r03);
        if (r1016.new_value == false) goto L69;
        int r04 = org.telegram.messenger.R.string.SharingOfferDisableConfirmText;
    L70:
        AlertsCreator.showSimpleConfirmAlert(r23, r32, LocaleController.getString(r04), LocaleController.getString(org.telegram.messenger.R.string.SharingOfferCancelYes), false, new ChatActionCell$$ExternalSyntheticLambda12(this, r1016));
        return;
    L69:
        r04 = org.telegram.messenger.R.string.SharingOfferEnableConfirmText;
        goto L70
    L65:
        r03 = org.telegram.messenger.R.string.SharingOfferEnableCancelTitle;
        goto L66
    L85:
        return;
    L84:
        return;
    L83:
        return;
    L82:
        return;
    L81:
        return;
    }

    private void drawBotButtons(Canvas r22, ArrayList<BotButton> r23) {
        if (r23 != null) goto L5;
        return;
    L5:
        if (r23.isEmpty() == true) goto L54;
        Theme.ResourcesProvider r3 = this.themeDelegate;
        float r4 = 4.0f;
        if (r3 == null) goto L10;
        r3.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(4.0f));
    L11:
        float r5 = 2.0f;
        float r32 = (getWidth() - this.giftRectSize) / 2.0f;
        float r6 = (((this.textY + this.textHeight) + AndroidUtilities.dp(4.0f)) + this.backgroundRectHeight) + AndroidUtilities.dp(4.0f);
        float r7 = (this.giftRectSize - AndroidUtilities.dp(4.0f)) / 2.0f;
        int r9 = 0;
    L13:
        if (r9 >= r23.size()) goto L55;
        BotButton r10 = r23.get(r9);
        float r11 = r10.getPressScale();
        float r12 = ((AndroidUtilities.dp(r4) + r7) * r9) + r32;
        float r13 = r12 + r7;
        this.rect.set(r12, r6, r13, r10.height + r6);
        r22.save();
        if (r11 == 1.0f) goto L17;
        float r16 = r4;
        r22.scale(r11, r11, this.rect.centerX(), this.rect.centerY());
    L18:
        Arrays.fill(this.botButtonRadii, AndroidUtilities.dp(Math.min(6.75f, SharedConfig.bubbleRadius)));
        if (r10.hasPositionFlag(9) == false) goto L22;
        float[] r42 = this.botButtonRadii;
        float r112 = AndroidUtilities.dp(SharedConfig.bubbleRadius);
        r42[7] = r112;
        r42[6] = r112;
    L22:
        if (r10.hasPositionFlag(10) == false) goto L24;
        float[] r43 = this.botButtonRadii;
        float r113 = AndroidUtilities.dp(SharedConfig.bubbleRadius);
        r43[5] = r113;
        r43[4] = r113;
    L24:
        this.botButtonPath.rewind();
        float r17 = r5;
        this.botButtonPath.addRoundRect(this.rect, this.botButtonRadii, Path.Direction.CW);
        r22.drawPath(this.botButtonPath, getThemedPaint("paintChatActionBackground"));
        if (hasGradientService() == false) goto L27;
        r22.drawPath(this.botButtonPath, Theme.chat_actionBackgroundGradientDarkenPaint);
    L27:
        r22.save();
        r22.clipPath(this.botButtonPath);
        Drawable r44 = r10.selectorDrawable;
        if (r44 == null) goto L30;
        int r15 = (int) r6;
        r44.setBounds((int) r12, r15, (int) r13, r10.height + r15);
        r10.selectorDrawable.setAlpha(255);
        r10.selectorDrawable.draw(r22);
    L30:
        r22.restore();
        r22.save();
        if (r10.iconDrawable == null) goto L33;
        int r45 = AndroidUtilities.dp(26.0f);
    L34:
        float r8 = r10.title.getWidth();
        if (r10.iconDrawable == null) goto L37;
        int r114 = AndroidUtilities.dp(r16);
    L38:
        float r82 = r7 - (r8 + r114);
        float r115 = r45;
        float r122 = r12 + ((r82 - r115) / r17);
        Drawable r83 = r10.iconDrawable;
        if (r83 == null) goto L45;
        int r132 = (int) r122;
        r83.setBounds(r132, (int) (((r10.height - AndroidUtilities.dp(24.0f)) / r17) + r6), r132 + AndroidUtilities.dp(24.0f), ((int) (((r10.height - AndroidUtilities.dp(24.0f)) / r17) + r6)) + AndroidUtilities.dp(24.0f));
        Drawable r52 = r10.iconDrawable;
        if (r10.isLocked == false) goto L43;
        int r84 = 128;
    L44:
        r52.setAlpha(r84);
        r10.iconDrawable.draw(r22);
        r122 = r122 + r115;
        goto L45
    L43:
        r84 = 255;
    L45:
        r10.title.ellipsize(Math.max(1, (((int) r7) - AndroidUtilities.dp(15.0f)) - r45));
        Text r46 = r10.title;
        float r53 = (AndroidUtilities.dp(40.0f) / r17) + r6;
        if (r10.isLocked == false) goto L48;
        float r14 = 0.5f;
    L49:
        r46.draw(r22, r122, r53, r14);
        r22.restore();
        r22.restore();
        r9 = r9 + 1;
        r4 = r16;
        r5 = r17;
        goto L13
    L48:
        r14 = 1.0f;
        goto L49
    L37:
        r114 = 0;
        goto L38
    L33:
        r45 = 0;
        goto L34
    L17:
        r16 = r4;
        goto L18
    L55:
        return;
    L10:
        Theme.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(4.0f));
        goto L11
    }

    private ColorFilter getAdaptiveEmojiColorFilter(int r3) {
        if (r3 == this.adaptiveEmojiColor) goto L5;
    L6:
        this.adaptiveEmojiColor = r3;
        this.adaptiveEmojiColorFilter = new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN);
    L8:
        return this.adaptiveEmojiColorFilter;
    L5:
        if (this.adaptiveEmojiColorFilter != null) goto L8;
        goto L6
    }

    private int getImageSize(MessageObject r4) {
        int r0 = this.stickerSize;
        int r1 = r4.type;
        if (r1 != 37) goto L6;
        r0 = AndroidUtilities.dp(52.0f);
    L11:
        if (isMessageActionSuggestedPostApproval() == true) goto L18;
        int r3 = r4.type;
        if (r3 != 34) goto L15;
        return 0;
    L15:
        if (r3 == 35) goto L21;
        return r0;
    L21:
        return 0;
    L18:
        return 0;
    L6:
        if (r1 != 21) goto L8;
    L9:
        r0 = AndroidUtilities.dp(78.0f);
        goto L11
    L8:
        if (isNewStyleButtonLayout() == false) goto L11;
        goto L9
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.themeDelegate);
    }

    private float getUploadingInfoProgress(MessageObject r3) {
        if (r3 == null) goto L17;
    L11:
        e = move-exception;
        FileLog.e(e);
        return 1.0f;
    L4:
        if (r3.type != 22) goto L18;
        MessagesController r2 = MessagesController.getInstance(this.currentAccount);     // Catch: Exception -> L11
        String r0 = r2.uploadingWallpaper;     // Catch: Exception -> L11
        if (r0 != null) goto L8;
        return 1.0f;
    L8:
        if (TextUtils.equals(r3.messageOwner.action.wallpaper.uploadingImage, r0) == false) goto L20;
        return r2.uploadingWallpaperInfo.uploadingProgress;
    L20:
        return 1.0f;
    L18:
        return 1.0f;
    L17:
        return 1.0f;
    }

    private boolean isButtonLayout(MessageObject r2) {
        if (r2 == null) goto L13;
        int r22 = r2.type;
        if (r22 != 30) goto L6;
        return true;
    L6:
        if (r22 != 18) goto L8;
        return true;
    L8:
        if (r22 != 25) goto L10;
        return true;
    L10:
        if (isNewStyleButtonLayout() == false) goto L18;
        return true;
    L18:
        return false;
    L13:
        return false;
    }

    private boolean isGiftChannel(MessageObject r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.type != 25) goto L9;
        return true;
    L9:
        return false;
    }

    private boolean isGiftCode() {
        MessageObject r0 = this.currentMessageObject;
        if (r0 != null) goto L5;
        return false;
    L5:
        if ((r0.messageOwner.action instanceof TLRPC.TL_messageActionGiftCode) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isMessageActionSuggestedPostApproval() {
        MessageObject r0 = this.currentMessageObject;
        if (r0 == null) goto L10;
        TLRPC.Message r02 = r0.messageOwner;
        if (r02 != null) goto L7;
        return false;
    L7:
        if ((r02.action instanceof TLRPC.TL_messageActionSuggestedPostApproval) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    private boolean isNewStyleButtonLayout() {
        if (this.starGiftLayout.has() == false) goto L5;
        return true;
    L5:
        if (this.birthdayLayout != null) goto L37;
        MessageObject r0 = this.currentMessageObject;
        int r1 = r0.type;
        if (r1 != 31) goto L9;
        return true;
    L9:
        if (r1 != 37) goto L11;
        return true;
    L11:
        if (r1 != 33) goto L13;
        return true;
    L13:
        if (r1 != 35) goto L15;
        return true;
    L15:
        if (r1 != 34) goto L17;
        return true;
    L17:
        if (r1 != 21) goto L19;
        return true;
    L19:
        if (r1 != 22) goto L21;
        return true;
    L21:
        if (r0.isStoryMention() == true) goto L45;
        TLRPC.Message r3 = this.currentMessageObject.messageOwner;
        if (r3 == null) goto L31;
        TLRPC.MessageAction r32 = r3.action;
        if ((r32 instanceof TLRPC.TL_messageActionSuggestedPostApproval) == true) goto L27;
        return false;
    L27:
        if (((TLRPC.TL_messageActionSuggestedPostApproval) r32).balance_too_low == false) goto L29;
        return true;
    L29:
        if (((TLRPC.TL_messageActionSuggestedPostApproval) r32).rejected == true) goto L47;
        return false;
    L47:
        return true;
    L31:
        return false;
    L45:
        return true;
    L37:
        return true;
    }

    private boolean isSelfGiftCode() {
        MessageObject r0 = this.currentMessageObject;
        if (r0 == null) goto L12;
        TLRPC.Message r02 = r0.messageOwner;
        TLRPC.MessageAction r1 = r02.action;
        if ((r1 instanceof TLRPC.TL_messageActionGiftCode) == true) goto L9;
        if ((r1 instanceof TLRPC.TL_messageActionGiftStars) == true) goto L9;
        return false;
    L9:
        if ((r02.from_id instanceof TLRPC.TL_peerUser) == true) goto L11;
        return false;
    L11:
        return UserObject.isUserSelf(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.currentMessageObject.messageOwner.from_id.user_id)));
    L12:
        return false;
    }

    private /* synthetic */ void lambda$didPressCustomBotButton$10(TLRPC.TL_messageActionNoForwardsRequest r8) {
        MessagesController.getInstance(this.currentAccount).toggleChatNoForwards(this.currentMessageObject.getDialogId(), this.currentMessageObject.getId(), r8.new_value, null);
    }

    private /* synthetic */ void lambda$didPressCustomBotButton$7(BaseFragment r2, TLRPC.Updates r3, TLRPC.TL_error r4) {
        if (r3 == null) goto L4;
        MessagesController.getInstance(this.currentAccount).processUpdates(r3, false);
    L4:
        if (r4 == null) goto L7;
        AndroidUtilities.runOnUIThread(new ChatActionCell$$ExternalSyntheticLambda15(r2, r4));
        return;
    }

    private /* synthetic */ void lambda$didPressCustomBotButton$8(BaseFragment r4) {
        TL_payments.TL_resolveStarGiftOffer r0 = new TL_payments.TL_resolveStarGiftOffer();
        r0.offer_msg_id = getMessageObject().getId();
        r0.decline = true;
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r0, new ChatActionCell$$ExternalSyntheticLambda14(this, r4));
    }

    private /* synthetic */ void lambda$didPressCustomBotButton$9(TLRPC.TL_messageActionNoForwardsRequest r8) {
        MessagesController.getInstance(this.currentAccount).toggleChatNoForwards(this.currentMessageObject.getDialogId(), this.currentMessageObject.getId(), r8.prev_value, null);
    }

    private /* synthetic */ void lambda$new$0(ImageReceiver r2, boolean r3, boolean r4, boolean r5) {
        if (r3 == false) goto L30;
        RLottieDrawable r22 = this.imageReceiver.getLottieAnimation();
        if (r22 == null) goto L31;
        MessageObject r32 = this.currentMessageObject;
        if (r32 == null) goto L24;
        if (r32.playedGiftAnimation == true) goto L24;
        r32.playedGiftAnimation = true;
        r22.setCurrentFrame(0, false);
        AndroidUtilities.runOnUIThread(new ChatActionCell$$ExternalSyntheticLambda13(r22));
        if (r32.wasUnread == false) goto L12;
    L13:
        r32.wasUnread = false;
        this.forceWasUnread = false;
        performHapticFeedback(3, 2);     // Catch: Exception -> L27
    L16:
        if ((getContext() instanceof LaunchActivity) == false) goto L18;
        ((LaunchActivity) getContext()).getFireworksOverlay().start();
    L18:
        TLRPC.VideoSize r23 = this.giftEffectAnimation;
        if (r23 == null) goto L33;
        ChatActionCellDelegate r33 = this.delegate;
        if (r33 == null) goto L34;
        r33.needShowEffectOverlay(this, this.giftSticker, r23);
        return;
    L34:
        return;
    L33:
        return;
    L12:
        if (this.forceWasUnread == true) goto L13;
        return;
    L24:
        if (r22.getCurrentFrame() >= 1) goto L36;
        r22.stop();
        r22.setCurrentFrame(r22.getFramesCount() - 1, false);
        return;
    L36:
        return;
    L31:
        return;
    }

    private /* synthetic */ void lambda$onTouchEvent$2() {
        this.isSpoilerRevealing = false;
        getMessageObject().isSpoilersRevealed = true;
        List<SpoilerEffect> r0 = this.giftPremiumText.spoilers;
        if (r0 == null) goto L5;
        r0.clear();
    L5:
        invalidate();
    }

    private /* synthetic */ void lambda$onTouchEvent$3() {
        post(new ChatActionCell$$ExternalSyntheticLambda7(this));
    }

    private /* synthetic */ void lambda$openPremiumGiftChannel$4(TLRPC.TL_messageActionGiftCode r3) {
        this.delegate.didOpenPremiumGiftChannel(this, r3.slug, false);
    }

    private /* synthetic */ void lambda$openPremiumGiftPreview$5(TLRPC.TL_premiumGiftOption r3, String r4) {
        this.delegate.didOpenPremiumGift(this, r3, r4, false);
    }

    private /* synthetic */ void lambda$setMessageObject$1() {
        ChatActionCellDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onTopicClick(this);
        return;
    }

    private float measureLayoutWidth(Layout r4) {
        float r3 = 0.0f;
        int r0 = 0;
    L4:
        if (r0 >= r4.getLineCount()) goto L9;
        float r1 = (int) Math.ceil(r4.getLineWidth(r0));
        if (r1 <= r3) goto L8;
        r3 = r1;
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        return r3;
    }

    private void openLink(CharacterStyle r4) {
        if (this.delegate != null) goto L5;
        return;
    L5:
        if ((r4 instanceof URLSpan) == false) goto L37;
        String r42 = ((URLSpan) r4).getURL();
        if (r42.startsWith("task") == false) goto L11;
        int r43 = Integer.parseInt(r42.substring(5));
        this.delegate.didPressTaskLink(this, this.currentMessageObject.getReplyMsgId(), r43);
        return;
    L11:
        if (r42.startsWith("topic") == false) goto L19;
        URLSpan r0 = this.pressedLink;
        if ((r0 instanceof URLSpanNoUnderline) == false) goto L19;
        TLObject r44 = ((URLSpanNoUnderline) r0).getObject();
        if ((r44 instanceof TLRPC.TL_forumTopic) == false) goto L38;
        BaseFragment r02 = this.delegate.getBaseFragment();
        long r1 = -this.delegate.getDialogId();
        ForumUtilities.openTopic(r02, r1, (TLRPC.TL_forumTopic) r44, 0);
        return;
    L38:
        return;
    L19:
        if (r42.startsWith("invite") == false) goto L27;
        URLSpan r03 = this.pressedLink;
        if ((r03 instanceof URLSpanNoUnderline) == false) goto L27;
        TLObject r45 = ((URLSpanNoUnderline) r03).getObject();
        if ((r45 instanceof TLRPC.TL_chatInviteExported) == false) goto L39;
        ChatActionCellDelegate r3 = this.delegate;
        r3.needOpenInviteLink((TLRPC.TL_chatInviteExported) r45);
        return;
    L39:
        return;
    L27:
        if (r42.startsWith("game") == false) goto L31;
        this.delegate.didPressReplyMessage(this, this.currentMessageObject.getReplyMsgId());
        return;
    L31:
        if (r42.startsWith("http") == false) goto L34;
        Browser.openUrl(getContext(), r42);
        return;
    L34:
        this.delegate.needOpenUserProfile(Long.parseLong(r42));
        return;
    }

    private void openPremiumGiftChannel() {
        if (this.delegate == null) goto L6;
        AndroidUtilities.runOnUIThread(new ChatActionCell$$ExternalSyntheticLambda8(this, (TLRPC.TL_messageActionGiftCode) this.currentMessageObject.messageOwner.action));
        return;
    }

    private void openPremiumGiftPreview() {
        TLRPC.TL_premiumGiftOption r0 = new TLRPC.TL_premiumGiftOption();
        TLRPC.MessageAction r1 = this.currentMessageObject.messageOwner.action;
        r0.amount = r1.amount;
        r0.months = r1.months;
        r0.currency = r1.currency;
        String r2 = null;
        if (isGiftCode() == false) goto L9;
        if (isSelfGiftCode() == true) goto L9;
        r2 = ((TLRPC.TL_messageActionGiftCode) this.currentMessageObject.messageOwner.action).slug;
    L9:
        if (this.delegate == null) goto L12;
        AndroidUtilities.runOnUIThread(new ChatActionCell$$ExternalSyntheticLambda9(this, r0, r2));
        return;
    }

    private void openStarsGiftTransaction() {
        MessageObject r0 = this.currentMessageObject;
        if (r0 == null) goto L45;
        TLRPC.Message r02 = r0.messageOwner;
        if (r02 == null) goto L49;
        TLRPC.MessageAction r03 = r02.action;
        if ((r03 instanceof TLRPC.TL_messageActionGiftStars) == false) goto L12;
        Context r2 = getContext();
        int r3 = this.currentAccount;
        TLRPC.Message r04 = this.currentMessageObject.messageOwner;
        StarsIntroActivity.showTransactionSheet(r2, r3, r04.date, r04.from_id, r04.peer_id, (TLRPC.TL_messageActionGiftStars) r04.action, this.avatarStoryParams.resourcesProvider);
        return;
    L12:
        if ((r03 instanceof TLRPC.TL_messageActionPrizeStars) == false) goto L16;
        Context r22 = getContext();
        int r32 = this.currentAccount;
        TLRPC.Message r05 = this.currentMessageObject.messageOwner;
        StarsIntroActivity.showTransactionSheet(r22, r32, r05.date, r05.from_id, r05.peer_id, (TLRPC.TL_messageActionPrizeStars) r05.action, this.avatarStoryParams.resourcesProvider);
        return;
    L16:
        if ((r03 instanceof TLRPC.TL_messageActionGiftTon) == false) goto L20;
        Context r23 = getContext();
        int r33 = this.currentAccount;
        TLRPC.Message r06 = this.currentMessageObject.messageOwner;
        StarsIntroActivity.showTransactionSheet(r23, r33, r06.date, r06.from_id, r06.peer_id, (TLRPC.TL_messageActionGiftTon) r06.action, this.avatarStoryParams.resourcesProvider);
        return;
    L20:
        if ((r03 instanceof TLRPC.TL_messageActionStarGift) == false) goto L27;
        if (((TLRPC.TL_messageActionStarGift) r03).forceIn == true) goto L50;
        new StarGiftSheet(getContext(), this.currentAccount, this.currentMessageObject.getDialogId(), this.themeDelegate).set(this.currentMessageObject).show();
        return;
    L50:
        return;
    L27:
        if ((r03 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L38;
        if (((TLRPC.TL_messageActionStarGiftUnique) r03).gift.burned == false) goto L35;
        BaseFragment r9 = LaunchActivity.getSafeLastFragment();
        if (r9 == null) goto L51;
        BulletinFactory.of(r9).createSimpleBulletin(org.telegram.messenger.R.raw.fire_on, LocaleController.getString(org.telegram.messenger.R.string.UniqueGiftNotFoundBurned)).show();
        return;
    L51:
        return;
    L35:
        new StarGiftSheet(getContext(), this.currentAccount, this.currentMessageObject.getDialogId(), this.themeDelegate).set(this.currentMessageObject).show();
        return;
    L38:
        if ((r03 instanceof TLRPC.TL_messageActionSetChatTheme) == false) goto L46;
        TLRPC.ChatTheme r07 = ((TLRPC.TL_messageActionSetChatTheme) r03).theme;
        if ((r07 instanceof TLRPC.TL_chatThemeUniqueGift) == false) goto L47;
        TL_stars.StarGift r08 = ((TLRPC.TL_chatThemeUniqueGift) r07).gift;
        if ((r08 instanceof TL_stars.TL_starGiftUnique) == false) goto L48;
        new StarGiftSheet(getContext(), this.currentAccount, this.currentMessageObject.getDialogId(), this.themeDelegate).set(r08.slug, (TL_stars.TL_starGiftUnique) r08, null).show();
        return;
    L48:
        return;
    L47:
        return;
    L46:
        return;
    L49:
        return;
    }

    private void openStarsNeedSheet() {
        MessageSuggestionParams r0 = this.currentMessageObject.obtainSuggestionOffer();
        AmountUtils$Amount r1 = r0.amount;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.currency != AmountUtils$Currency.STARS) goto L10;
        new StarsIntroActivity.StarsNeededSheet(getContext(), this.themeDelegate, r0.amount.asDecimal(), 13, ForumUtilities.getMonoForumTitle(this.currentAccount, this.currentMessageObject.getDialogId(), true), null, this.currentMessageObject.getDialogId()).show();
        return;
    }

    private void setStarsPaused(boolean r8) {
        StarParticlesView.Drawable r0 = this.starParticlesDrawable;
        if (r8 != r0.paused) goto L5;
        return;
    L5:
        r0.paused = r8;
        if (r8 == false) goto L9;
        r0.pausedTime = System.currentTimeMillis();
        return;
    L9:
        int r82 = 0;
    L11:
        if (r82 >= this.starParticlesDrawable.particles.size()) goto L13;
        this.starParticlesDrawable.particles.get(r82).lifeTime += System.currentTimeMillis() - this.starParticlesDrawable.pausedTime;
        r82 = r82 + 1;
        goto L11
    L13:
        invalidate();
    }

    private void updateTextInternal(boolean r3) {
        if (getMeasuredWidth() == 0) goto L6;
        createLayout(this.customText, getMeasuredWidth());
        invalidate();
    L6:
        if (this.wasLayout == true) goto L12;
        if (r3 == false) goto L10;
        AndroidUtilities.runOnUIThread(new ChatActionCell$$ExternalSyntheticLambda6(this));
        return;
    L10:
        requestLayout();
        return;
    L12:
        buildLayout();
    }

    public boolean checkUnreadReactions(float r4, int r5) {
        if (this.reactionsLayoutInBubble.hasUnreadReactions == true) goto L5;
        return false;
    L5:
        float r0 = getY();
        ReactionsLayoutInBubble r3 = this.reactionsLayoutInBubble;
        float r02 = r0 + r3.y;
        if (r02 > r4) goto L8;
    L11:
        return false;
    L8:
        if (((r02 + r3.height) - AndroidUtilities.dp(16.0f)) >= r5) goto L11;
        return true;
    }

    @Override // org.telegram.ui.Cells.IMessageCell
    public void didPressReactionFromLayout(TLRPC.ReactionCount r7, boolean r8, float r9, float r10) {
        ChatActionCellDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.didPressReaction(this, r7, r8, r9, r10);
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.startSpoilers) goto L7;
        setSpoilersSuppressed(false);
        return;
    L7:
        if (r3 != NotificationCenter.emojiLoaded) goto L11;
        invalidate();
        return;
    L11:
        if (r3 != NotificationCenter.stopSpoilers) goto L15;
        setSpoilersSuppressed(true);
        return;
    L15:
        if (r3 != NotificationCenter.didUpdatePremiumGiftStickers) goto L17;
    L29:
        MessageObject r32 = this.currentMessageObject;
        if (r32 == null) goto L36;
        setMessageObject(r32, true);
        return;
    L36:
        return;
    L17:
        if (r3 == NotificationCenter.starGiftsLoaded) goto L29;
        if (r3 == NotificationCenter.didUpdateTonGiftStickers) goto L29;
        if (r3 == NotificationCenter.diceStickersDidLoad) goto L24;
        return;
    L24:
        if (Objects.equals(r5[0], UserConfig.getInstance(this.currentAccount).premiumGiftsStickerPack) == false) goto L34;
        MessageObject r33 = this.currentMessageObject;
        if (r33 == null) goto L35;
        setMessageObject(r33, true);
        return;
    L35:
        return;
    }

    public void drawBackground(Canvas r34, boolean r35) {
        if (this.canDrawInParent == true) goto L5;
    L12:
        Paint r2 = getThemedPaint("paintChatActionBackground");
        Paint r3 = getThemedPaint("paintChatActionBackgroundDarken");
        this.textPaint = (TextPaint) getThemedPaint("paintChatActionText");
        int r4 = this.overrideBackground;
        boolean r5 = true;
        if (r4 < 0) goto L18;
        int r22 = getThemedColor(r4);
        if (this.overrideBackgroundPaint != null) goto L17;
        Paint r42 = new Paint(1);
        this.overrideBackgroundPaint = r42;
        r42.setColor(r22);
        TextPaint r23 = new TextPaint(1);
        this.overrideTextPaint = r23;
        r23.setTypeface(AndroidUtilities.bold());
        this.overrideTextPaint.setTextSize(AndroidUtilities.dp(Math.max(16, SharedConfig.fontSize) - 2));
        this.overrideTextPaint.setColor(getThemedColor(this.overrideText));
    L17:
        r2 = this.overrideBackgroundPaint;
        this.textPaint = this.overrideTextPaint;
    L18:
        float r7 = 8.0f;
        if (this.invalidatePath == false) goto L129;
        this.invalidatePath = false;
        this.backgroundLeft = getWidth();
        this.backgroundRight = 0;
        this.lineWidths.clear();
        StaticLayout r11 = this.textLayout;
        if (r11 != null) goto L23;
        int r112 = 0;
    L24:
        int r12 = AndroidUtilities.dp(11.0f);
        int r13 = AndroidUtilities.dp(8.0f);
        int r14 = 0;
        int r15 = 0;
    L26:
        if (r14 >= r112) goto L37;
        boolean r17 = r5;
        int r43 = (int) Math.ceil(this.textLayout.getLineWidth(r14));
        if (r14 == 0) goto L34;
        int r52 = r15 - r43;
        if (r52 <= 0) goto L34;
        float r19 = r7;
        if (r52 <= ((r12 * 1.5f) + r13)) goto L36;
    L35:
        r15 = r43;
    L36:
        this.lineWidths.add(Integer.valueOf(r15));
        r14 = r14 + 1;
        r5 = r17;
        r7 = r19;
    L34:
        r19 = r7;
        goto L35
    L37:
        float r192 = r7;
        float r18 = 6.0f;
        int r44 = r112 - 2;
    L38:
        if (r44 < 0) goto L46;
        int r53 = this.lineWidths.get(r44).intValue();
        int r6 = r15 - r53;
        if (r6 > 0) goto L42;
    L44:
        r15 = r53;
    L45:
        this.lineWidths.set(r44, Integer.valueOf(r15));
        r44 = r44 - 1;
        goto L38
    L42:
        if (r6 > ((r12 * 1.5f) + r13)) goto L44;
    L46:
        int r45 = AndroidUtilities.dp(4.0f);
        int r54 = getMeasuredWidth() / 2;
        int r72 = AndroidUtilities.dp(3.0f);
        int r142 = AndroidUtilities.dp(6.0f);
        int r62 = r12 - r72;
        float r20 = 2.0f;
        this.lineHeights.clear();
        this.backgroundPath.reset();
        float r21 = 4.0f;
        float r10 = r54;
        this.backgroundPath.moveTo(r10, r45);
        int r8 = 0;
        int r9 = 0;
    L47:
        if (r8 >= r112) goto L91;
        int r55 = this.lineWidths.get(r8).intValue();
        int r24 = r9;
        int r92 = this.textLayout.getLineBottom(r8);
        int r93 = r112 - 1;
        float r26 = r10;
        if (r8 >= r93) goto L51;
        int r27 = r112;
        int r102 = this.lineWidths.get(r8 + 1).intValue();
    L52:
        int r113 = r92 - r24;
        if (r8 == 0) goto L55;
        if (r55 > r15) goto L55;
    L56:
        if (r8 == r93) goto L60;
        if (r55 > r102) goto L60;
    L59:
        int r242 = r113;
        float r114 = r26 + (r55 / 2.0f);
        if (r8 == r93) goto L67;
        if (r55 >= r102) goto L67;
        if (r8 == 0) goto L67;
        if (r55 >= r15) goto L67;
        int r28 = r142;
    L68:
        if (r8 == 0) goto L70;
        if (r55 > r15) goto L70;
        if (r55 >= r15) goto L73;
        float r29 = r114;
        int r30 = r13;
        float r132 = r29 + r62;
        int r31 = r142;
        Paint r32 = r3;
        this.rect.set(r132, r45, (r28 * 2) + r132, r1 + r45);
        checkLeftRightBounds();
        this.backgroundPath.arcTo(this.rect, -90.0f, -90.0f);
    L75:
        r45 = r45 + r242;
        if (r8 == r93) goto L79;
        if (r55 >= r102) goto L79;
        r45 = r45 - AndroidUtilities.dp(3.0f);
        int r115 = r242 - AndroidUtilities.dp(3.0f);
    L80:
        if (r8 == 0) goto L83;
        if (r55 >= r15) goto L83;
        r45 = r45 - AndroidUtilities.dp(3.0f);
        r115 = r115 - AndroidUtilities.dp(3.0f);
    L83:
        this.lineHeights.add(Integer.valueOf(r115));
        if (r8 == r93) goto L89;
        if (r55 > r102) goto L89;
        if (r55 >= r102) goto L90;
        float r116 = r29 + r62;
        this.rect.set(r116, r45 - r3, (r28 * 2) + r116, r45);
        checkLeftRightBounds();
        this.backgroundPath.arcTo(this.rect, 180.0f, -90.0f);
    L90:
        r8 = r8 + 1;
        r15 = r55;
        r9 = r92;
        r10 = r26;
        r112 = r27;
        r13 = r30;
        r142 = r31;
        r3 = r32;
    L89:
        this.rect.set((r29 - r72) - r12, r45 - (r12 * 2), r29 + r62, r45);
        checkLeftRightBounds();
        this.backgroundPath.arcTo(this.rect, 0.0f, 90.0f);
    L79:
        r115 = r242;
        goto L80
    L73:
        r32 = r3;
        r29 = r114;
        r30 = r13;
        r31 = r142;
    L70:
        r32 = r3;
        r29 = r114;
        r30 = r13;
        r31 = r142;
        this.rect.set((r29 - r72) - r12, r45, r29 + r62, (r12 * 2) + r45);
        checkLeftRightBounds();
        this.backgroundPath.arcTo(this.rect, -90.0f, 90.0f);
    L67:
        r28 = r13;
    L60:
        r113 = r113 + AndroidUtilities.dp(3.0f);
    L55:
        r113 = r113 + AndroidUtilities.dp(3.0f);
        goto L56
    L51:
        r27 = r112;
        r102 = 0;
        goto L52
    L91:
        Paint r322 = r3;
        float r262 = r10;
        int r302 = r13;
        int r312 = r142;
        int r117 = r112 - 1;
        int r1 = r117;
    L92:
        if (r1 < 0) goto L124;
        if (r1 == 0) goto L95;
        int r33 = this.lineWidths.get(r1 - 1).intValue();
    L96:
        int r56 = this.lineWidths.get(r1).intValue();
        if (r1 == r117) goto L99;
        int r82 = this.lineWidths.get(r1 + 1).intValue();
    L100:
        this.textLayout.getLineBottom(r1);
        float r94 = r54 - (r56 / 2);
        if (r1 == r117) goto L106;
        if (r56 >= r82) goto L106;
        if (r1 == 0) goto L106;
        if (r56 >= r33) goto L106;
        int r103 = r312;
    L107:
        if (r1 == r117) goto L109;
        if (r56 > r82) goto L109;
        if (r56 >= r82) goto L112;
        float r133 = r94 - r62;
        float r16 = r94;
        this.rect.set(r133 - (r103 * 2), r45 - r14, r133, r45);
        checkLeftRightBounds();
        this.backgroundPath.arcTo(this.rect, 90.0f, -90.0f);
    L114:
        r45 = r45 - this.lineHeights.get(r1).intValue();
        if (r1 == 0) goto L122;
        if (r56 > r33) goto L122;
        if (r56 >= r33) goto L123;
        float r95 = r16 - r62;
        this.rect.set(r95 - (r103 * 2), r45, r95, r10 + r45);
        checkLeftRightBounds();
        this.backgroundPath.arcTo(this.rect, 0.0f, -90.0f);
    L123:
        r1 = r1 - 1;
    L122:
        this.rect.set(r16 - r62, r45, (r16 + r72) + r12, (r12 * 2) + r45);
        checkLeftRightBounds();
        this.backgroundPath.arcTo(this.rect, 180.0f, 90.0f);
        goto L123
    L112:
        r16 = r94;
    L109:
        r16 = r94;
        this.rect.set(r16 - r62, r45 - (r12 * 2), (r16 + r72) + r12, r45);
        checkLeftRightBounds();
        this.backgroundPath.arcTo(this.rect, 90.0f, 90.0f);
    L106:
        r103 = r302;
        goto L107
    L99:
        r82 = 0;
        goto L100
    L95:
        r33 = 0;
        goto L96
    L124:
        this.backgroundPath.close();
        if (isMessageActionSuggestedPostApproval() == false) goto L131;
        if (isNewStyleButtonLayout() == true) goto L131;
        this.rect.left = (r262 - (this.textWidth / 2.0f)) - AndroidUtilities.dp(17.0f);
        RectF r110 = this.rect;
        r110.top = r45;
        r110.right = (r262 + (this.textWidth / 2.0f)) + AndroidUtilities.dp(17.0f);
        this.rect.bottom = ((r45 + this.textHeight) + this.titleHeight) + AndroidUtilities.dp(28.0f);
        this.backgroundPath.reset();
        this.backgroundPath.addRoundRect(this.rect, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), Path.Direction.CW);
        this.backgroundPath.close();
    L131:
        if (this.visiblePartSet == true) goto L133;
        this.backgroundHeight = ((ViewGroup) getParent()).getMeasuredHeight();
    L133:
        Theme.ResourcesProvider r111 = this.themeDelegate;
        if (r111 == null) goto L136;
        r111.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(r21));
    L138:
        if (r35 == true) goto L140;
    L142:
        Paint r63 = r322;
        int r36 = -1;
        int r46 = -1;
    L143:
        MessageObject r57 = this.currentMessageObject;
        if (r57 != null) goto L146;
    L149:
        Canvas r73 = r34;
        r73.drawPath(this.backgroundPath, r2);
        if (hasGradientService() == true) goto L152;
    L154:
        float r222 = 0.0f;
        if (this.dimAmount <= 0.0f) goto L160;
        int r58 = this.dimPaint.getAlpha();
        if (r35 == false) goto L159;
        this.dimPaint.setAlpha((int) (r58 * getAlpha()));
    L159:
        r73.drawPath(this.backgroundPath, this.dimPaint);
        this.dimPaint.setAlpha(r58);
    L160:
        MessageObject r59 = this.currentMessageObject;
        if (this.starGiftLayout.has() == false) goto L172;
        float r118 = this.starGiftLayout.getWidth() + AndroidUtilities.dp(r192);
        float r510 = (getWidth() - r118) / r20;
        if (this.starGiftLayout.repost == false) goto L165;
        float r83 = r222;
    L166:
        RectF r96 = AndroidUtilities.rectTmp;
        r96.set(r510, r83, r118 + r510, (this.starGiftLayout.getHeight() + r83) + AndroidUtilities.dp(r192));
        if (this.backgroundRect != null) goto L169;
        this.backgroundRect = new RectF();
    L169:
        this.backgroundRect.set(r96);
        r73.drawRoundRect(this.backgroundRect, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), r2);
        if (hasGradientService() == false) goto L213;
        r73.drawRoundRect(this.backgroundRect, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), r63);
    L213:
        if (r36 < 0) goto L229;
        r2.setAlpha(r36);
        r63.setAlpha(r46);
        return;
    L229:
        return;
    L165:
        r83 = (this.textY + this.textHeight) + AndroidUtilities.dp(12.0f);
        goto L166
    L172:
        SuggestBirthdayActionLayout r84 = this.birthdayLayout;
        if (r84 == null) goto L181;
        float r119 = r84.width();
        float r511 = this.birthdayLayout.height();
        float r85 = (getWidth() - r119) / r20;
        if (this.backgroundRect != null) goto L177;
        this.backgroundRect = new RectF();
    L177:
        this.backgroundRect.set(r85, AndroidUtilities.dp(r21), r119 + r85, AndroidUtilities.dp(r21) + r511);
        r73.drawRoundRect(this.backgroundRect, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), r2);
        if (hasGradientService() == false) goto L213;
        r73.drawRoundRect(this.backgroundRect, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), r63);
        goto L213
    L181:
        if (isButtonLayout(r59) == false) goto L213;
        float r86 = (getWidth() - this.giftRectSize) / r20;
        float r1110 = this.textY + this.textHeight;
        if (isNewStyleButtonLayout() == false) goto L185;
        float r1111 = r1110 + AndroidUtilities.dp(r21);
        AndroidUtilities.rectTmp.set(r86, r1111, this.giftRectSize + r86, this.backgroundRectHeight + r1111);
    L186:
        if (r59 == null) goto L197;
        if (r59.type != 18) goto L197;
        if (this.giftPremiumTextCollapsed == true) goto L197;
        if (this.giftPremiumText == null) goto L197;
        if (this.giftPremiumTextCollapsedHeight <= 0) goto L197;
        AndroidUtilities.rectTmp.bottom -= (r8.layout.getHeight() - this.giftPremiumTextCollapsedHeight) * (1.0f - this.giftPremiumTextExpandedAnimated.get());
    L197:
        if (this.backgroundRect != null) goto L199;
        this.backgroundRect = new RectF();
    L199:
        this.backgroundRect.set(AndroidUtilities.rectTmp);
        if (r59 == null) goto L210;
        int r120 = r59.type;
        if (r120 == 33) goto L206;
        if (r120 != 35) goto L210;
    L206:
        if (this.botInlineButtons == null) goto L210;
        Arrays.fill(this.radii, AndroidUtilities.dp(16.0f));
        float[] r121 = this.radii;
        float r512 = AndroidUtilities.dp(r18);
        r121[7] = r512;
        r121[6] = r512;
        r121[5] = r512;
        r121[4] = r512;
        this.backgroundPath2.rewind();
        this.backgroundPath2.addRoundRect(this.backgroundRect, this.radii, Path.Direction.CW);
        r73.drawPath(this.backgroundPath2, r2);
        if (hasGradientService() == false) goto L213;
        r73.drawPath(this.backgroundPath2, r63);
    L210:
        r73.drawRoundRect(this.backgroundRect, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f), r2);
        if (hasGradientService() == false) goto L213;
        r73.drawRoundRect(this.backgroundRect, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f), r63);
        goto L213
    L185:
        float r1112 = r1110 + AndroidUtilities.dp(12.0f);
        RectF r97 = AndroidUtilities.rectTmp;
        int r122 = this.giftRectSize;
        r97.set(r86, r1112, r122 + r86, (r122 + r1112) + this.giftPremiumAdditionalHeight);
        goto L186
    L152:
        if (r63.getAlpha() <= 0) goto L154;
        r73.drawPath(this.backgroundPath, r63);
        goto L154
    L146:
        if (r57.isRepostPreview == false) goto L149;
        r73 = r34;
        r222 = 0.0f;
        goto L160
    L140:
        if (getAlpha() == 1.0f) goto L142;
        r36 = r2.getAlpha();
        r46 = r322.getAlpha();
        r2.setAlpha((int) (r36 * getAlpha()));
        r63 = r322;
        r63.setAlpha((int) (r46 * getAlpha()));
        goto L143
    L136:
        Theme.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(r21));
        goto L138
    L23:
        r112 = r11.getLineCount();
        goto L24
    L129:
        r322 = r3;
        r192 = 8.0f;
        r21 = 4.0f;
        r18 = 6.0f;
        r20 = 2.0f;
        goto L131
    L5:
        if (hasGradientService() == false) goto L9;
        if (r35 == true) goto L9;
        return;
    L9:
        if (hasGradientService() == true) goto L12;
        if (r35 == false) goto L12;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r6, View r7, long r8) {
        if (r7 != this.rippleView) goto L7;
        float r0 = this.bounce.getScale(0.02f);
        r6.save();
        r6.scale(r0, r0, r7.getX() + (r7.getMeasuredWidth() / 2.0f), r7.getY() + (r7.getMeasuredHeight() / 2.0f));
        boolean r5 = super.drawChild(r6, r7, r8);
        r6.restore();
        return r5;
    L7:
        return super.drawChild(r6, r7, r8);
    }

    public void drawOutboundsContent(Canvas r12) {
        r12.save();
        r12.translate(this.sideMenuWidth / 2.0f, getPaddingTop());
        r12.save();
        r12.translate(this.textXLeft, this.textY);
        StaticLayout r1 = this.textLayout;
        AnimatedEmojiSpan.EmojiGroupedSpans r2 = this.animatedEmojiStack;
        List<SpoilerEffect> r4 = this.spoilers;
        if (r1 == null) goto L6;
        ColorFilter r3 = getAdaptiveEmojiColorFilter(r1.getPaint().getColor());
    L7:
        AnimatedEmojiSpan.drawAnimatedEmojis(r12, r1, r2, 0.0f, r4, 0.0f, 0.0f, 0.0f, 1.0f, r3);
        r12.restore();
        if (this.starGiftLayout.has() == false) goto L15;
        r12.save();
        float r13 = (getWidth() - this.starGiftLayout.getWidth()) / 2.0f;
        if (this.starGiftLayout.repost == false) goto L13;
        int r22 = AndroidUtilities.dp(4.0f);
    L14:
        r12.translate(r13, r22);
        this.starGiftLayout.drawOutbounds(r12);
        r12.restore();
        goto L15
    L13:
        r22 = (this.textY + this.textHeight) + AndroidUtilities.dp(16.0f);
    L15:
        r12.restore();
        if (this.topicSeparator == null) goto L22;
        float r6 = getAlpha();
        Theme.ResourcesProvider r14 = this.themeDelegate;
        if (r14 == null) goto L20;
        r14.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + 0.0f);
    L21:
        this.topicSeparator.draw(r12, getWidth(), this.sideMenuWidth, 0.0f, 1.0f, r6, this.showTopicSeparator);
        goto L22
    L20:
        Theme.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + 0.0f);
    L22:
        drawBotButtons(r12, this.botButtons);
        return;
    L6:
        r3 = null;
        goto L7
    }

    public void drawReactions(Canvas r2, boolean r3, Integer r4) {
        if (this.canDrawInParent == true) goto L5;
    L12:
        drawReactionsLayout(r2, r3, r4);
        return;
    L5:
        if (hasGradientService() == false) goto L9;
        if (r3 == true) goto L9;
        return;
    L9:
        if (hasGradientService() == true) goto L12;
        if (r3 == false) goto L12;
    }

    public void drawReactionsLayout(Canvas r11, boolean r12, Integer r13) {
        float r0 = 1.0f;
        if (r12 == false) goto L5;
        float r122 = getAlpha();
    L7:
        if (r122 <= 0.0f) goto L39;
        Theme.ResourcesProvider r1 = this.themeDelegate;
        if (r1 == null) goto L12;
        r1.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(4.0f));
    L13:
        MessageObject r14 = this.currentMessageObject;
        if (r14 != null) goto L16;
        return;
    L16:
        if (r14.shouldDrawReactions() == false) goto L35;
        ReactionsLayoutInBubble r15 = this.reactionsLayoutInBubble;
        if (r15.isSmall == true) goto L20;
    L23:
        r15.drawServiceShaderBackground = 1.0f;
        if (r122 >= 1.0f) goto L26;
        Canvas r3 = r11;
        r3.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (r122 * 255.0f), 31);
    L27:
        ReactionsLayoutInBubble r112 = this.reactionsLayoutInBubble;
        TransitionParams r10 = this.transitionParams;
        if (r10.animateChange == false) goto L30;
        r0 = r10.animateChangeProgress;
    L30:
        r112.draw(r3, r0, r13);
        if (r122 >= 1.0f) goto L38;
        r3.restore();
        return;
    L38:
        return;
    L26:
        r3 = r11;
        goto L27
    L20:
        if (this.transitionParams.animateChange == true) goto L22;
        return;
    L22:
        if (r15.animateHeight == true) goto L23;
        return;
    L35:
        return;
    L12:
        Theme.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(4.0f));
        goto L13
    L39:
        return;
    L5:
        r122 = 1.0f;
        goto L7
    }

    public void drawReactionsLayoutOverlay(Canvas r11, boolean r12) {
        float r0 = 1.0f;
        if (r12 == false) goto L5;
        float r122 = getAlpha();
    L6:
        Theme.ResourcesProvider r1 = this.themeDelegate;
        if (r1 == null) goto L9;
        r1.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(4.0f));
    L10:
        MessageObject r13 = this.currentMessageObject;
        if (r13 != null) goto L13;
        return;
    L13:
        if (r13.shouldDrawReactions() == false) goto L32;
        ReactionsLayoutInBubble r14 = this.reactionsLayoutInBubble;
        if (r14.isSmall == true) goto L17;
    L20:
        r14.drawServiceShaderBackground = 1.0f;
        if (r122 >= 1.0f) goto L23;
        Canvas r3 = r11;
        r3.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (r122 * 255.0f), 31);
    L24:
        ReactionsLayoutInBubble r112 = this.reactionsLayoutInBubble;
        TransitionParams r10 = this.transitionParams;
        if (r10.animateChange == false) goto L27;
        r0 = r10.animateChangeProgress;
    L27:
        r112.drawOverlay(r3, r0);
        if (r122 >= 1.0f) goto L35;
        r3.restore();
        return;
    L35:
        return;
    L23:
        r3 = r11;
        goto L24
    L17:
        if (this.transitionParams.animateChange == true) goto L19;
        return;
    L19:
        if (r14.animateHeight == true) goto L20;
        return;
    L32:
        return;
    L9:
        Theme.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(4.0f));
        goto L10
    L5:
        r122 = 1.0f;
        goto L6
    }

    public void drawScrimReaction(Canvas r7, Integer r8, float r9, boolean r10) {
        if (this.reactionsLayoutInBubble.isSmall == true) goto L10;
        Theme.ResourcesProvider r0 = this.themeDelegate;
        if (r0 == null) goto L7;
        r0.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(4.0f));
    L8:
        this.reactionsLayoutInBubble.setScrimProgress(r9, r10);
        this.reactionsLayoutInBubble.draw(r7, this.transitionParams.animateChangeProgress, r8);
        return;
    L7:
        Theme.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(4.0f));
        goto L8
    }

    public void drawScrimReactionPreview(View r7, Canvas r8, int r9, Integer r10, float r11) {
        if (this.reactionsLayoutInBubble.isSmall == true) goto L10;
        Theme.ResourcesProvider r0 = this.themeDelegate;
        if (r0 == null) goto L7;
        r0.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(4.0f));
    L8:
        this.reactionsLayoutInBubble.setScrimProgress(r11);
        this.reactionsLayoutInBubble.drawPreview(r7, r8, r9, r10);
        return;
    L7:
        Theme.applyServiceShaderMatrix(getMeasuredWidth(), this.backgroundHeight, this.viewTranslationX, this.viewTop + AndroidUtilities.dp(4.0f));
        goto L8
    }

    @Override // org.telegram.ui.Cells.IMessageCell
    public /* bridge */ /* synthetic */ ImageReceiver getAvatarImage() {
        return super.getAvatarImage();
    }

    @Override // org.telegram.ui.Cells.BaseCell
    public int getBoundsLeft() {
        if (this.starGiftLayout.has() == false) goto L10;
        int r0 = ((int) (getWidth() - (this.starGiftLayout.getWidth() + AndroidUtilities.dp(8.0f)))) / 2;
        if (this.starGiftLayout.repost == false) goto L8;
        return r0;
    L8:
        return Math.min(this.backgroundLeft, r0);
    L10:
        if (isButtonLayout(this.currentMessageObject) == true) goto L12;
        int r02 = this.backgroundLeft;
        ImageReceiver r1 = this.imageReceiver;
        if (r1 == null) goto L19;
        if (r1.getVisible() == false) goto L19;
        r02 = Math.min((int) this.imageReceiver.getImageX(), r02);
    L19:
        return (this.sideMenuWidth / 2) + r02;
    L12:
        return (this.sideMenuWidth / 2) + ((getWidth() - this.giftRectSize) / 2);
    }

    @Override // org.telegram.ui.Cells.BaseCell
    public int getBoundsRight() {
        if (this.starGiftLayout.has() == false) goto L10;
        int r0 = ((int) (getWidth() + (this.starGiftLayout.getWidth() + AndroidUtilities.dp(8.0f)))) / 2;
        if (this.starGiftLayout.repost == false) goto L8;
        return r0;
    L8:
        return Math.max(this.backgroundRight, r0);
    L10:
        if (isButtonLayout(this.currentMessageObject) == true) goto L12;
        int r02 = this.backgroundRight;
        ImageReceiver r1 = this.imageReceiver;
        if (r1 == null) goto L19;
        if (r1.getVisible() == false) goto L19;
        r02 = Math.max((int) this.imageReceiver.getImageX2(), r02);
    L19:
        return (this.sideMenuWidth / 2) + r02;
    L12:
        return (this.sideMenuWidth / 2) + ((getWidth() + this.giftRectSize) / 2);
    }

    @Override // org.telegram.ui.Cells.IMessageCell
    public /* bridge */ /* synthetic */ float getCheckBoxTranslation() {
        return super.getCheckBoxTranslation();
    }

    @Override // org.telegram.ui.Cells.IMessageCell
    public /* bridge */ /* synthetic */ MessageObject.GroupedMessagePosition getCurrentPosition() {
        return super.getCurrentPosition();
    }

    public int getCustomDate() {
        return this.customDate;
    }

    public ChatActionCellDelegate getDelegate() {
        return this.delegate;
    }

    @Override // org.telegram.ui.Cells.IMessageCell
    public float getDeltaBottom() {
        return 0.0f;
    }

    public float getDeltaLeft() {
        return 0.0f;
    }

    public float getDeltaRight() {
        return 0.0f;
    }

    public float getDeltaTop() {
        return 0.0f;
    }

    @Override // org.telegram.ui.Cells.IMessageCell
    public /* bridge */ /* synthetic */ int getLayoutHeight() {
        return super.getLayoutHeight();
    }

    @Override // org.telegram.ui.Cells.IMessageCell
    public MessageObject getMessageObject() {
        return this.currentMessageObject;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.TAG;
    }

    public ImageReceiver getPhotoImage() {
        return this.imageReceiver;
    }

    public ReactionsLayoutInBubble.ReactionButton getReactionButton(ReactionsLayoutInBubble.VisibleReaction r1) {
        return this.reactionsLayoutInBubble.getReactionButton(r1);
    }

    public ReactionsLayoutInBubble getReactionsLayout() {
        return this.reactionsLayoutInBubble;
    }

    @Override // org.telegram.ui.Cells.IMessageCell
    public /* bridge */ /* synthetic */ float getSlidingOffsetX() {
        return super.getSlidingOffsetX();
    }

    public Paint getThemedPaint(String r1) {
        Theme.ResourcesProvider r0 = this.themeDelegate;
        if (r0 == null) goto L5;
        Paint r02 = r0.getPaint(r1);
    L6:
        if (r02 == null) goto L9;
        return r02;
    L9:
        return Theme.getThemePaint(r1);
    L5:
        r02 = null;
        goto L6
    }

    public TransitionParams getTransitionParams() {
        return this.transitionParams;
    }

    public boolean hasButton() {
        MessageObject r0 = this.currentMessageObject;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (isButtonLayout(r0) == true) goto L7;
        return false;
    L7:
        if (this.giftPremiumButtonLayout == null) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean hasGradientService() {
        if (this.overrideBackgroundPaint != null) goto L13;
        Theme.ResourcesProvider r1 = this.themeDelegate;
        if (r1 == null) goto L10;
        if (r1.hasGradientService() == false) goto L16;
        return true;
    L16:
        return false;
    L10:
        if (Theme.hasGradientService() == false) goto L17;
        return true;
    L17:
        return false;
    L13:
        return false;
    }

    @Override // org.telegram.ui.Cells.BaseCell, android.view.View
    public void invalidate() {
        super.invalidate();
        View r0 = this.invalidateWithParent;
        if (r0 == null) goto L5;
        r0.invalidate();
    L5:
        Runnable r02 = this.invalidateListener;
        if (r02 == null) goto L9;
        r02.run();
    L9:
        if (this.invalidatesParent == true) goto L11;
        return;
    L11:
        if (getParent() == null) goto L17;
        View r1 = (View) getParent();
        if (r1.getParent() == null) goto L18;
        r1.invalidate();
        ((View) r1.getParent()).invalidate();
        return;
    L18:
        return;
    }

    @Override // android.view.View
    public void invalidate(int r1, int r2, int r3, int r4) {
        super.invalidate(r1, r2, r3, r4);
        View r12 = this.invalidateWithParent;
        if (r12 == null) goto L6;
        r12.invalidate();
    L6:
        if (this.invalidatesParent == true) goto L8;
        return;
    L8:
        if (getParent() == null) goto L14;
        View r0 = (View) getParent();
        if (r0.getParent() == null) goto L15;
        r0.invalidate();
        ((View) r0.getParent()).invalidate();
        return;
    L15:
        return;
    }

    @Override // android.view.View
    public void invalidate(Rect r1) {
        super.invalidate(r1);
        View r12 = this.invalidateWithParent;
        if (r12 == null) goto L6;
        r12.invalidate();
    L6:
        if (this.invalidatesParent == true) goto L8;
        return;
    L8:
        if (getParent() == null) goto L14;
        View r0 = (View) getParent();
        if (r0.getParent() == null) goto L15;
        r0.invalidate();
        ((View) r0.getParent()).invalidate();
        return;
    L15:
        return;
    }

    public void invalidateOutbounds() {
        ChatActionCellDelegate r0 = this.delegate;
        if (r0 == null) goto L10;
        if (r0.canDrawOutboundsContent() == false) goto L10;
        super.invalidate();
        return;
    L10:
        if ((getParent() instanceof View) == false) goto L13;
        ((View) getParent()).invalidate();
        return;
    }

    public boolean isCellAttachedToWindow() {
        return this.attachedToWindow;
    }

    public void markReactionsAsRead() {
        this.reactionsLayoutInBubble.hasUnreadReactions = false;
        MessageObject r2 = this.currentMessageObject;
        if (r2 != null) goto L5;
        return;
    L5:
        r2.markReactionsAsRead();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
        this.imageReceiver.onAttachedToWindow();
        setStarsPaused(false);
        if (this.canDrawInParent == false) goto L9;
        ChatActionCellDelegate r2 = this.delegate;
        if (r2 == null) goto L9;
        if (r2.canDrawOutboundsContent() == true) goto L9;
        boolean r22 = true;
    L10:
        this.animatedEmojiStack = AnimatedEmojiSpan.update(0, this, r22, this.animatedEmojiStack, new Layout[]{this.textLayout});
        TextLayout r1 = this.giftPremiumText;
        if (r1 == null) goto L13;
        r1.attach();
    L13:
        NotificationCenter.ObserversGroup r12 = this.observersGroup;
        if (r12 == null) goto L16;
        r12.removeAllObservers();
        this.observersGroup = null;
    L16:
        this.observersGroup = NotificationCenter.getInstance(this.currentAccount).createObserversGroup(this).addGlobal(NotificationCenter.emojiLoaded).add(NotificationCenter.didUpdatePremiumGiftStickers).add(NotificationCenter.didUpdateTonGiftStickers).add(NotificationCenter.starGiftsLoaded).add(NotificationCenter.diceStickersDidLoad);
        MessageObject r13 = this.currentMessageObject;
        if (r13 != null) goto L19;
    L21:
        this.starGiftLayout.attach();
        this.reactionsLayoutInBubble.onAttachToWindow();
        TopicSeparator r0 = this.topicSeparator;
        if (r0 == null) goto L24;
        r0.attach();
    L24:
        SuggestBirthdayActionLayout r6 = this.birthdayLayout;
        if (r6 == null) goto L28;
        r6.attach();
        return;
    L28:
        return;
    L19:
        if (r13.type != 21) goto L21;
        setMessageObject(r13, true);
    L9:
        r22 = false;
        goto L10
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        this.imageReceiver.onDetachedFromWindow();
        setStarsPaused(true);
        this.wasLayout = false;
        AnimatedEmojiSpan.release(this, this.animatedEmojiStack);
        TextLayout r0 = this.giftPremiumText;
        if (r0 == null) goto L5;
        r0.detach();
    L5:
        NotificationCenter.ObserversGroup r02 = this.observersGroup;
        if (r02 == null) goto L8;
        r02.removeAllObservers();
        this.observersGroup = null;
    L8:
        this.avatarStoryParams.onDetachFromWindow();
        this.transitionParams.onDetach();
        this.starGiftLayout.detach();
        this.reactionsLayoutInBubble.onDetachFromWindow();
        TopicSeparator r03 = this.topicSeparator;
        if (r03 == null) goto L11;
        r03.detach();
    L11:
        SuggestBirthdayActionLayout r2 = this.birthdayLayout;
        if (r2 == null) goto L15;
        r2.detach();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r45) {
        Canvas r1 = r45;
        r1.save();
        r1.translate(this.sideMenuWidth / 2.0f, getPaddingTop());
        MessageObject r12 = this.currentMessageObject;
        float r14 = this.giftPremiumTextExpandedAnimated.set(!this.giftPremiumTextCollapsed);
        int r2 = this.stickerSize;
        if (this.starGiftLayout.has() == false) goto L5;
    L76:
        float r23 = 16.0f;
        float r22 = 1.0f;
    L75:
        int r11 = r2;
        drawBackground(r1, false);
        if (this.starGiftLayout.has() == false) goto L89;
        r1.save();
        float r24 = (getWidth() - this.starGiftLayout.getWidth()) / 2.0f;
        this.starGiftLayoutX = r24;
        if (this.starGiftLayout.repost == false) goto L82;
        float r5 = AndroidUtilities.dp(4.0f);
        float r28 = 14.0f;
    L83:
        this.starGiftLayoutY = r5;
        r1.translate(r24, r5);
        this.starGiftLayout.draw(r1);
        ChatActionCellDelegate r25 = this.delegate;
        if (r25 != null) goto L86;
    L87:
        this.starGiftLayout.drawOutbounds(r1);
    L88:
        r1.restore();
    L136:
        if (this.textPaint != null) goto L138;
    L159:
        int r15 = 22;
    L161:
        if (this.textPaint == null) goto L182;
        if (this.titleLayout == null) goto L182;
        r1.save();
        r1.translate(this.titleXLeft, this.textY - this.titleHeight);
        if (this.titleLayout.getPaint() == this.textPaint) goto L167;
        buildLayout();
    L167:
        r1.save();
        SpoilerEffect.clipOutCanvas(r1, this.spoilers);
        SpoilerEffect.layoutDrawMaybe(this.titleLayout, r1);
        ChatActionCellDelegate r26 = this.delegate;
        if (r26 != null) goto L170;
    L171:
        StaticLayout r27 = this.titleLayout;
        AnimatedEmojiSpan.EmojiGroupedSpans r3 = this.animatedEmojiStack;
        List<SpoilerEffect> r52 = this.spoilers;
        StaticLayout r4 = this.textLayout;
        if (r4 != null) goto L174;
        ColorFilter r10 = null;
    L175:
        AnimatedEmojiSpan.drawAnimatedEmojis(r1, r27, r3, 0.0f, r52, 0.0f, 0.0f, 0.0f, 1.0f, r10);
    L176:
        r1.restore();
        Iterator<SpoilerEffect> r29 = this.spoilers.iterator();
    L178:
        if (r29.hasNext() == false) goto L180;
        SpoilerEffect r32 = r29.next();
        r32.setColor(this.titleLayout.getPaint().getColor());
        r32.draw(r1);
        goto L178
    L180:
        r1.restore();
        goto L182
    L174:
        r10 = getAdaptiveEmojiColorFilter(r4.getPaint().getColor());
        goto L175
    L170:
        if (r26.canDrawOutboundsContent() == false) goto L176;
    L182:
        if (this.starGiftLayout.has() == false) goto L184;
    L432:
        ChatActionCell r152 = this;
    L433:
        r152.drawReactions(r1, false, null);
        r152.transitionParams.recordDrawingState();
        r1.restore();
        return;
    L184:
        if (isButtonLayout(r12) == false) goto L432;
        r1.save();
        float r210 = (this.previousWidth - this.giftRectSize) / 2.0f;
        if (r12.type == r15) goto L188;
        r210 = r210 + AndroidUtilities.dp(8.0f);
    L188:
        float r7 = r210;
        if (isNewStyleButtonLayout() == false) goto L198;
        RectF r211 = this.backgroundRect;
        if (r211 == null) goto L193;
        float r212 = r211.top;
    L194:
        int r42 = AndroidUtilities.dp(r23);
        if (r11 <= 0) goto L197;
        r42 = (r42 * 2) + r11;
    L197:
        r212 = r212 + r42;
    L213:
        int r43 = r12.type;
        if (r43 == 31) goto L219;
        char r9 = '%';
        int r53 = 33;
        if (r43 == 37) goto L220;
        if (r43 == 33) goto L220;
    L221:
        r1.translate(r7, r212);
        if (this.giftPremiumTitleLayout == null) goto L232;
        r1.save();
        r1.translate(((this.giftRectSize - AndroidUtilities.dp(r23)) - this.giftPremiumTitleLayout.getWidth()) / 2.0f, 0.0f);
        this.giftPremiumTitleLayout.draw(r1);
        r1.restore();
        float r33 = this.giftPremiumTitleLayout.getHeight() + r212;
        if (this.giftPremiumSubtitleLayout == null) goto L227;
        r1.save();
        r1.translate(((this.giftRectSize - AndroidUtilities.dp(r23)) - this.giftPremiumSubtitleLayout.getWidth()) / 2.0f, this.giftPremiumTitleLayout.getHeight() + AndroidUtilities.dp(4.0f));
        this.giftPremiumSubtitleLayout.draw(r1);
        r1.restore();
        r33 = r33 + (this.giftPremiumSubtitleLayout.getHeight() + AndroidUtilities.dp(10.0f));
    L227:
        if (r12.type != 25) goto L229;
        float r44 = 6.0f;
    L230:
        float r34 = r33 + AndroidUtilities.dp(r44);
    L231:
        float r17 = r34;
        r1.restore();
        int r242 = 268435456;
        if (this.giftPremiumTitleLayout == null) goto L245;
        Text r35 = this.giftPremiumReleasedText;
        if (r35 == null) goto L245;
        float r36 = r35.getWidth() + AndroidUtilities.dp(12.0f);
        float r46 = (((this.giftRectSize - AndroidUtilities.dp(r23)) - r36) / 2.0f) + r7;
        float r213 = (r212 + this.giftPremiumTitleLayout.getHeight()) + AndroidUtilities.dp(r28);
        if (this.giftReleasedBackgroundPaint != null) goto L240;
        this.giftReleasedBackgroundPaint = new Paint(1);
    L240:
        Paint r54 = this.giftReleasedBackgroundPaint;
        if (Theme.isCurrentThemeDark() == false) goto L243;
        int r6 = 285212671;
    L244:
        r54.setColor(r6);
        RectF r55 = AndroidUtilities.rectTmp;
        r55.set(r46, r213 - AndroidUtilities.dp(8.0f), r36 + r46, AndroidUtilities.dp(8.0f) + r213);
        r1.drawRoundRect(r55, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.giftReleasedBackgroundPaint);
        float r8 = 0.0f;
        int r262 = 33;
        this.giftPremiumReleasedText.draw(r45, AndroidUtilities.dp(6.0f) + r46, r213, -855638017, 1.0f);
        r1 = r45;
        r17 = r17 + AndroidUtilities.dp(24.0f);
    L246:
        float r172 = r17 + AndroidUtilities.dp(4.0f);
        if (r12.type != 18) goto L249;
        r172 = r172 + AndroidUtilities.dp(2.0f);
    L249:
        float r214 = r172;
        r1.save();
        r1.translate(r7, r214);
        if (r12.type == r15) goto L252;
        ChatActionCell r13 = this;
        float r173 = 0.2f;
        float r153 = r8;
        float r252 = 8.0f;
        float r352 = 1.0f;
        float r102 = r7;
        TextLayout r0 = r13.giftPremiumText;
        if (r0 != null) goto L296;
    L293:
        r152 = r13;
    L313:
        r1.restore();
        if (r152.giftPremiumTitleLayout != null) goto L316;
        AndroidUtilities.dp(r252);
    L316:
        TextLayout r02 = r152.giftPremiumText;
        if (r02 == null) goto L319;
        AndroidUtilities.lerp(r152.giftPremiumTextCollapsedHeight, r02.layout.getHeight(), r14);
    L319:
        StaticLayout r03 = r152.giftPremiumButtonLayout;
        if (r03 == null) goto L322;
        r03.getHeight();
    L322:
        r152.getHeight();
        AndroidUtilities.dp(r252);
        Theme.ResourcesProvider r04 = r152.themeDelegate;
        if (r04 == null) goto L325;
        r04.applyServiceShaderMatrix(r152.getMeasuredWidth(), r152.backgroundHeight, r152.viewTranslationX, r152.viewTop + AndroidUtilities.dp(4.0f));
    L326:
        float r05 = r152.bounce.getScale(0.02f);
        r1.save();
        r1.scale(r05, r05, r152.giftButtonRect.centerX(), r152.giftButtonRect.centerY());
        if (r152.giftPremiumButtonLayout == null) goto L365;
        r1.drawRoundRect(r152.giftButtonRect, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), r152.getThemedPaint("paintChatActionBackgroundSelected"));
        if (r152.hasGradientService() == false) goto L332;
        r1.drawRoundRect(r152.giftButtonRect, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), r152.getThemedPaint("paintChatActionBackgroundDarken"));
    L332:
        if (r152.dimAmount <= 0.0f) goto L335;
        r1.drawRoundRect(r152.giftButtonRect, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), r152.dimPaint);
    L335:
        if (r152.getMessageObject().type != 31) goto L337;
    L340:
        Theme.ResourcesProvider r06 = r152.themeDelegate;
        if (r06 == null) goto L343;
        boolean r07 = r06.isDark();
    L344:
        int r37 = r152.dimPaint.getColor();
        Paint r56 = r152.dimPaint;
        if (r07 == false) goto L347;
        r242 = 620756991;
    L347:
        r56.setColor(r242);
        r1.drawRoundRect(r152.giftButtonRect, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), r152.dimPaint);
        r152.dimPaint.setColor(r37);
    L349:
        if (r152.getMessageObject().type != 31) goto L351;
    L364:
        r152.invalidate();
        goto L365
    L351:
        if (r152.getMessageObject().type == 37) goto L364;
        if (r152.getMessageObject().type == 33) goto L364;
        if (r152.getMessageObject().type == 21) goto L364;
        if (r152.getMessageObject().type == 22) goto L364;
        if (r152.getMessageObject().type == 24) goto L364;
        r152.starsPath.rewind();
        r152.starsPath.addRoundRect(r152.giftButtonRect, AndroidUtilities.dp(r23), AndroidUtilities.dp(r23), Path.Direction.CW);
        r1.save();
        r1.clipPath(r152.starsPath);
        r152.starParticlesDrawable.onDraw(r1);
        if (r152.starParticlesDrawable.paused == true) goto L363;
        r152.invalidate();
    L363:
        r1.restore();
        goto L365
    L343:
        r07 = Theme.isCurrentThemeDark();
        goto L344
    L337:
        if (r152.getMessageObject().type == 37) goto L340;
        if (r152.getMessageObject().type != 33) goto L349;
    L365:
        boolean r08 = r12.settingAvatar;
        if (r08 == false) goto L371;
        float r47 = r152.progressToProgress;
        if (r47 == r352) goto L371;
        r152.progressToProgress = r47 + 0.10666667f;
    L370:
        float r82 = 0.0f;
    L375:
        float r09 = Utilities.clamp(r152.progressToProgress, r352, r82);
        r152.progressToProgress = r09;
        if (r09 == r82) goto L382;
        if (r152.progressView != null) goto L380;
        r152.progressView = new RadialProgressView(r152.getContext());
    L380:
        int r010 = AndroidUtilities.dp(r23);
        r1.save();
        float r38 = r152.progressToProgress;
        r1.scale(r38, r38, r152.giftButtonRect.centerX(), r152.giftButtonRect.centerY());
        r152.progressView.setSize(r010);
        r152.progressView.setProgressColor(Theme.getColor(Theme.key_chat_serviceText));
        r152.progressView.draw(r1, r152.giftButtonRect.centerX(), r152.giftButtonRect.centerY());
        r1.restore();
    L382:
        if (r152.progressToProgress != 1.0f) goto L384;
    L386:
        boolean r011 = r12.flickerLoading;
        LoadingDrawable r39 = r152.loadingDrawable;
        if (r011 == false) goto L391;
        if (r39 != null) goto L390;
        LoadingDrawable r012 = new LoadingDrawable(r152.themeDelegate);
        r152.loadingDrawable = r012;
        r012.setGradientScale(2.0f);
        r152.loadingDrawable.setAppearByGradient(true);
        float r57 = r173;
        r152.loadingDrawable.setColors(Theme.multAlpha(-1, 0.08f), Theme.multAlpha(-1, r57), Theme.multAlpha(-1, r57), Theme.multAlpha(-1, 0.7f));
        r152.loadingDrawable.strokePaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
    L390:
        r152.loadingDrawable.resetDisappear();
        r152.loadingDrawable.setBounds(r152.giftButtonRect);
        r152.loadingDrawable.setRadiiDp(r23);
        r152.loadingDrawable.draw(r1);
    L396:
        r1.restore();
        if (r152.backgroundRect == null) goto L433;
        if (r152.giftRibbonPath == null) goto L433;
        if (r152.giftRibbonText == null) goto L433;
        Paint r013 = r152.getThemedPaint("paintChatActionBackground");
        Paint r215 = r152.getThemedPaint("paintChatActionBackgroundDarken");
        float r310 = (r152.backgroundRect.right - AndroidUtilities.dp(65.0f)) + AndroidUtilities.dp(2.0f);
        float r48 = r152.backgroundRect.top - AndroidUtilities.dp(2.0f);
        Theme.ResourcesProvider r58 = r152.themeDelegate;
        if (r58 == null) goto L405;
        r58.applyServiceShaderMatrix(r152.getMeasuredWidth(), r152.backgroundHeight, r152.viewTranslationX + r310, (r152.viewTop + AndroidUtilities.dp(4.0f)) + r48);
    L406:
        r1.save();
        r1.translate(r310, r48);
        ColorFilter r311 = r013.getColorFilter();
        PathEffect r49 = r013.getPathEffect();
        Theme.ResourcesProvider r59 = r152.themeDelegate;
        if (r59 == null) goto L409;
        boolean r510 = r59.isDark();
    L411:
        if (r152.giftRibbonPaintFilter != null) goto L413;
    L414:
        ColorMatrix r62 = new ColorMatrix();
        if ((r013.getColorFilter() instanceof ColorMatrixColorFilter) == true) goto L417;
    L419:
        float r72 = 0.1f;
        if (r510 == false) goto L422;
        float r83 = 0.1f;
    L423:
        AndroidUtilities.adjustBrightnessColorMatrix(r62, r83);
        if (r510 == false) goto L426;
        r72 = 0.15f;
    L426:
        AndroidUtilities.adjustSaturationColorMatrix(r62, r72);
        r152.giftRibbonPaintFilter = new ColorMatrixColorFilter(r62);
        r152.giftRibbonPaintFilterDark = r510;
    L427:
        r013.setColorFilter(r152.giftRibbonPaintFilter);
        r013.setPathEffect(r152.giftRibbonPaintEffect);
        r1.drawPath(r152.giftRibbonPath, r013);
        r013.setColorFilter(r311);
        r013.setPathEffect(r49);
        if (r152.hasGradientService() == false) goto L430;
        PathEffect r014 = r215.getPathEffect();
        r215.setPathEffect(r152.giftRibbonPaintEffect);
        r1.drawPath(r152.giftRibbonPath, r215);
        r215.setPathEffect(r014);
    L430:
        r1.rotate(45.0f, AndroidUtilities.dp(40.43f), AndroidUtilities.dp(24.56f));
        r152.giftRibbonText.draw(r1, AndroidUtilities.dp(40.43f) - (r152.giftRibbonText.getCurrentWidth() / 2.0f), AndroidUtilities.dp(26.0f), -1, 1.0f);
        r1.restore();
        goto L433
    L422:
        r83 = -0.08f;
        goto L423
    L417:
        if (Build.VERSION.SDK_INT < 26) goto L419;
        ((ColorMatrixColorFilter) r013.getColorFilter()).getColorMatrix(r62);
        goto L419
    L413:
        if (r152.giftRibbonPaintFilterDark == r510) goto L427;
    L409:
        r510 = Theme.isCurrentThemeDark();
        goto L411
    L405:
        Theme.applyServiceShaderMatrix(r152.getMeasuredWidth(), r152.backgroundHeight, r152.viewTranslationX + r310, (r152.viewTop + AndroidUtilities.dp(4.0f)) + r48);
        goto L406
    L391:
        float r410 = r23;
        if (r39 == null) goto L396;
        r39.setBounds(r152.giftButtonRect);
        r152.loadingDrawable.setRadiiDp(r410);
        r152.loadingDrawable.disappear();
        r152.loadingDrawable.draw(r1);
        if (r152.loadingDrawable.isDisappeared() == false) goto L396;
        r152.loadingDrawable.reset();
        goto L396
    L384:
        if (r152.giftPremiumButtonLayout == null) goto L386;
        r1.save();
        float r015 = 1.0f - r152.progressToProgress;
        r1.scale(r015, r015, r152.giftButtonRect.centerX(), r152.giftButtonRect.centerY());
        r1.translate(r102, r152.giftButtonRect.top + AndroidUtilities.dp(7.0f));
        r1.translate(((r152.giftRectSize - AndroidUtilities.dp(r23)) - r152.giftPremiumButtonLayout.getWidth()) / 2.0f, 0.0f);
        r152.giftPremiumButtonLayout.draw(r1);
        r1.restore();
    L371:
        if (r08 == true) goto L370;
        float r016 = r152.progressToProgress;
        r82 = 0.0f;
        if (r016 == 0.0f) goto L375;
        r152.progressToProgress = r016 - 0.10666667f;
        goto L375
    L325:
        Theme.applyServiceShaderMatrix(r152.getMeasuredWidth(), r152.backgroundHeight, r152.viewTranslationX, r152.viewTop + AndroidUtilities.dp(4.0f));
        goto L326
    L296:
        float r017 = r0.layout.getHeight();
        if (r14 >= 1.0f) goto L299;
        r017 = AndroidUtilities.lerp(r13.giftPremiumTextCollapsedHeight, r017, r14);
        RectF r312 = AndroidUtilities.rectTmp;
        r312.set(r153, -AndroidUtilities.dp(20.0f), r13.getWidth(), r017);
        r1.saveLayerAlpha(r312, 255, 31);
    L300:
        r1.translate(((r13.giftRectSize - AndroidUtilities.dp(r23)) - r13.giftPremiumText.layout.getWidth()) / 2.0f, r153);
        r13.giftPremiumText.x = r102 + (((r13.giftRectSize - AndroidUtilities.dp(r23)) - r13.giftPremiumText.layout.getWidth()) / 2.0f);
        TextLayout r313 = r13.giftPremiumText;
        r313.y = r214;
        int r216 = r313.paint.getColor();
        TextLayout r314 = r13.giftPremiumText;
        float r132 = r017;
        SpoilerEffect.renderWithRipple(r13, false, r216, 0, r314.patchedLayout, 1, r314.layout, r314.spoilers, r45, false);
        TextLayout r16 = r13.giftPremiumText;
        r152 = r13;
        AnimatedEmojiSpan.drawAnimatedEmojis(r45, r16.layout, r16.emoji, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f, r13.getAdaptiveEmojiColorFilter(r13.giftTextPaint.getColor()));
        r1 = r45;
        if (r14 < 1.0f) goto L303;
    L308:
        r1.restore();
        if (r14 >= 1.0f) goto L313;
        Text r018 = r152.giftPremiumTextMore;
        if (r018 == null) goto L313;
        r018.draw(r1, (r152.giftPremiumTextMoreX - r018.getCurrentWidth()) + AndroidUtilities.dp(5.0f), (r152.giftPremiumTextMoreY - (r152.giftPremiumTextMoreH / 2.0f)) - AndroidUtilities.dp(1.0f), r152.giftPremiumText.paint.getColor(), 1.0f - r14);
        goto L313
    L303:
        if (r152.giftPremiumTextMore == null) goto L308;
        r1.save();
        if (r152.giftPremiumTextClip != null) goto L307;
        r152.giftPremiumTextClip = new GradientClip();
    L307:
        r1.translate((-((r152.giftRectSize - AndroidUtilities.dp(r23)) - r152.giftPremiumText.layout.getWidth())) / 2.0f, 0.0f);
        RectF r019 = AndroidUtilities.rectTmp;
        r019.set((r152.giftPremiumTextMoreX - r152.giftPremiumTextMore.getCurrentWidth()) + AndroidUtilities.dp(8.0f), (r152.giftPremiumTextMoreY - r152.giftPremiumTextMoreH) - AndroidUtilities.dp(6.0f), r152.giftPremiumTextMoreX + AndroidUtilities.dp(6.0f), r152.giftPremiumTextMoreY);
        float r315 = 1.0f - r14;
        r152.giftPremiumTextClip.clipOut(r1, r019, r315);
        r019.set((r152.giftPremiumTextMoreX - r152.giftPremiumTextMore.getCurrentWidth()) - AndroidUtilities.dp(r23), (r152.giftPremiumTextMoreY - r152.giftPremiumTextMoreH) - AndroidUtilities.dp(6.0f), (r152.giftPremiumTextMoreX - r152.giftPremiumTextMore.getCurrentWidth()) + AndroidUtilities.dp(8.0f), r152.giftPremiumTextMoreY);
        r152.giftPremiumTextClip.draw(r1, r019, 2, r315);
        r019.set(0.0f, r132 - AndroidUtilities.dp(12.0f), r152.getWidth(), r132);
        r152.giftPremiumTextClip.draw(r1, r019, 3, (r315 * 4.0f) * (1.0f - r315));
        r1.restore();
        goto L308
    L299:
        r1.save();
        goto L300
    L252:
        if (this.radialProgress.getTransitionProgress() != 1.0f) goto L260;
        int r511 = 4;
        if (this.radialProgress.getIcon() == 4) goto L257;
    L255:
        r173 = 0.2f;
        float r154 = r8;
        r252 = 8.0f;
        r352 = 1.0f;
        int r133 = r262;
        r102 = r7;
        if (this.settingWallpaperLayout != null) goto L276;
        TextPaint r020 = new TextPaint();
        this.settingWallpaperPaint = r020;
        r020.setTextSize(AndroidUtilities.dp(13.0f));
        SpannableStringBuilder r021 = new SpannableStringBuilder(LocaleController.getString(org.telegram.messenger.R.string.ActionSettingWallpaper));
        int r316 = r021.toString().indexOf("...");
        if (r316 >= 0) goto L266;
        r316 = r021.toString().indexOf("…");
        int r342 = 1;
    L267:
        if (r316 < 0) goto L270;
        SpannableString r63 = new SpannableString("…");
        UploadingDotsSpannable r411 = new UploadingDotsSpannable();
        r411.fixTop = true;
        r411.setParent(this, false);
        r63.setSpan(r411, 0, r63.length(), r133);
        r021.replace(r316, r316 + r342, r63);
    L270:
        TextPaint r317 = this.settingWallpaperPaint;
        TextLayout r412 = this.giftPremiumText;
        if (r412 != null) goto L273;
        int r392 = 1;
    L274:
        this.settingWallpaperLayout = new StaticLayout(r021, r317, r392, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        goto L276
    L273:
        r392 = r412.width;
        goto L274
    L266:
        r342 = 3;
    L276:
        float r022 = getUploadingInfoProgress(r12);
        if (this.settingWallpaperProgressTextLayout != null) goto L279;
    L280:
        this.settingWallpaperProgress = r022;
        String r372 = ((int) (r022 * 100.0f)) + "%";
        TextPaint r023 = this.giftTextPaint;
        TextLayout r318 = this.giftPremiumText;
        if (r318 != null) goto L283;
        int r393 = 1;
    L284:
        this.settingWallpaperProgressTextLayout = new StaticLayout(r372, r023, r393, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    L285:
        this.settingWallpaperPaint.setColor(this.giftTextPaint.getColor());
        if (this.radialProgress.getIcon() != r511) goto L292;
        float r024 = this.radialProgress.getTransitionProgress();
        int r319 = this.giftTextPaint.getColor();
        float r162 = 1.0f - r024;
        this.settingWallpaperPaint.setAlpha((int) (Color.alpha(r319) * r162));
        this.giftTextPaint.setAlpha((int) (Color.alpha(r319) * r024));
        TextPaint r413 = this.giftTextPaint;
        r413.linkColor = r413.getColor();
        if (this.giftPremiumText == null) goto L290;
        float r025 = (r024 * 0.2f) + 0.8f;
        r1.save();
        r1.scale(r025, r025, this.giftRectSize / 2.0f, this.giftPremiumText.layout.getHeight() / 2.0f);
        r1.translate((this.giftRectSize - this.giftPremiumText.layout.getWidth()) / 2.0f, r154);
        this.giftPremiumText.x = ((this.giftRectSize - r0.layout.getWidth()) / 2.0f) + r102;
        this.giftPremiumText.y = r214;
        int r217 = this.giftTextPaint.getColor();
        TextLayout r026 = this.giftPremiumText;
        int r112 = r319;
        SpoilerEffect.renderWithRipple(this, false, r217, 0, r026.patchedLayout, 1, r026.layout, r026.spoilers, r45, false);
        TextLayout r18 = this.giftPremiumText;
        r13 = this;
        AnimatedEmojiSpan.drawAnimatedEmojis(r45, r18.layout, r18.emoji, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f, getAdaptiveEmojiColorFilter(this.giftTextPaint.getColor()));
        r1 = r45;
        r1.restore();
    L291:
        r13.giftTextPaint.setAlpha((int) (Color.alpha(r112) * r162));
        TextPaint r027 = r13.giftTextPaint;
        r027.linkColor = r027.getColor();
        float r028 = (r162 * 0.2f) + 0.8f;
        r1.save();
        r1.scale(r028, r028, r13.giftRectSize / 2.0f, r13.settingWallpaperLayout.getHeight() / 2.0f);
        r1.translate((r13.giftRectSize - r13.settingWallpaperLayout.getWidth()) / 2.0f, r154);
        SpoilerEffect.layoutDrawMaybe(r13.settingWallpaperLayout, r1);
        r1.restore();
        r1.save();
        r1.translate(r154, r13.settingWallpaperLayout.getHeight() + AndroidUtilities.dp(4.0f));
        r1.scale(r028, r028, r13.giftRectSize / 2.0f, r13.settingWallpaperProgressTextLayout.getHeight() / 2.0f);
        r1.translate((r13.giftRectSize - r13.settingWallpaperProgressTextLayout.getWidth()) / 2.0f, r154);
        SpoilerEffect.layoutDrawMaybe(r13.settingWallpaperProgressTextLayout, r1);
        r1.restore();
        r13.giftTextPaint.setColor(r112);
        r13.giftTextPaint.linkColor = r112;
        goto L293
    L290:
        r13 = this;
        r112 = r319;
        goto L291
    L292:
        r13 = this;
        r1.save();
        r1.translate((r13.giftRectSize - r13.settingWallpaperLayout.getWidth()) / 2.0f, r154);
        r13.settingWallpaperLayout.draw(r1);
        r1.restore();
        r1.save();
        r1.translate((r13.giftRectSize - r13.settingWallpaperProgressTextLayout.getWidth()) / 2.0f, r13.settingWallpaperLayout.getHeight() + AndroidUtilities.dp(4.0f));
        SpoilerEffect.layoutDrawMaybe(r13.settingWallpaperProgressTextLayout, r1);
        r1.restore();
        goto L293
    L283:
        r393 = r318.width;
        goto L284
    L279:
        if (this.settingWallpaperProgress == r022) goto L285;
    L257:
        if (this.giftPremiumText == null) goto L259;
        r1.save();
        r1.translate((this.giftRectSize - this.giftPremiumText.layout.getWidth()) / 2.0f, r8);
        this.giftPremiumText.x = ((this.giftRectSize - r3.layout.getWidth()) / 2.0f) + r7;
        this.giftPremiumText.y = r214;
        int r218 = this.giftTextPaint.getColor();
        TextLayout r320 = this.giftPremiumText;
        r173 = 0.2f;
        r252 = 8.0f;
        r102 = r7;
        r352 = 1.0f;
        SpoilerEffect.renderWithRipple(this, false, r218, 0, r320.patchedLayout, 1, r320.layout, r320.spoilers, r45, false);
        TextLayout r19 = this.giftPremiumText;
        AnimatedEmojiSpan.drawAnimatedEmojis(r45, r19.layout, r19.emoji, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f, getAdaptiveEmojiColorFilter(this.giftTextPaint.getColor()));
        r1 = r45;
        r1.restore();
        r152 = this;
        goto L313
    L259:
        r173 = 0.2f;
        r252 = 8.0f;
        r352 = 1.0f;
        r102 = r7;
        r152 = this;
        goto L313
    L260:
        r511 = 4;
        goto L255
    L243:
        r6 = 268435456;
    L245:
        r262 = r53;
        r8 = 0.0f;
        goto L246
    L229:
        r44 = 0.0f;
        goto L230
    L232:
        r34 = r212 - AndroidUtilities.dp(4.0f);
    L220:
        r212 = r212 - AndroidUtilities.dp(3.66f);
        goto L221
    L219:
        r53 = 33;
        r9 = '%';
        goto L220
    L193:
        r212 = (this.textY + this.textHeight) + AndroidUtilities.dp(4.0f);
        goto L194
    L198:
        float r219 = (this.textY + this.textHeight) + (this.giftRectSize * 0.075f);
        if (r12.type == 21) goto L202;
        r11 = this.stickerSize;
    L202:
        r212 = (r219 + r11) + AndroidUtilities.dp(4.0f);
        if (r12.type != 21) goto L206;
        r212 = r212 + AndroidUtilities.dp(r23);
    L206:
        if (r12.isStarGiftAction() == false) goto L209;
        r42 = AndroidUtilities.dp(12.0f);
        goto L197
    L209:
        if (r12.type != 30) goto L213;
        if (r12.isStarGiftAction() == true) goto L213;
        r212 = r212 - AndroidUtilities.dp(3.66f);
        goto L213
    L138:
        if (this.textLayout == null) goto L159;
        r1.save();
        r1.translate(this.textXLeft, this.textY);
        if (this.textLayout.getPaint() == this.textPaint) goto L142;
        buildLayout();
    L142:
        r1.save();
        SpoilerEffect.clipOutCanvas(r1, this.spoilers);
        SpoilerEffect.layoutDrawMaybe(this.textLayout, r1);
        ChatActionCellDelegate r220 = this.delegate;
        if (r220 != null) goto L145;
    L148:
        StaticLayout r221 = this.textLayout;
        AnimatedEmojiSpan.EmojiGroupedSpans r321 = this.animatedEmojiStack;
        List<SpoilerEffect> r512 = this.spoilers;
        if (r221 != null) goto L152;
        ColorFilter r73 = null;
    L153:
        r15 = 22;
        AnimatedEmojiSpan.drawAnimatedEmojis(r1, r221, r321, 0.0f, r512, 0.0f, 0.0f, 0.0f, 1.0f, r73);
    L154:
        r1.restore();
        Iterator<SpoilerEffect> r222 = this.spoilers.iterator();
    L156:
        if (r222.hasNext() == false) goto L158;
        SpoilerEffect r322 = r222.next();
        r322.setColor(this.textLayout.getPaint().getColor());
        r322.draw(r1);
        goto L156
    L158:
        r1.restore();
        goto L161
    L152:
        r73 = getAdaptiveEmojiColorFilter(r221.getPaint().getColor());
        goto L153
    L145:
        if (r220.canDrawOutboundsContent() == true) goto L148;
        r15 = 22;
        goto L154
    L86:
        if (r25.canDrawOutboundsContent() == false) goto L88;
    L82:
        r28 = 14.0f;
        r5 = (this.textY + this.textHeight) + AndroidUtilities.dp(r23);
        goto L83
    L89:
        r28 = 14.0f;
        if (this.birthdayLayout == null) goto L93;
        r1.save();
        this.birthdayLayout.draw(r1);
        r1.restore();
        goto L136
    L93:
        if (isButtonLayout(r12) == true) goto L97;
        if (r12 == null) goto L136;
        if (r12.type != 11) goto L136;
    L97:
        GiftSheet.CardBackground r223 = this.cardBackground;
        if (r223 == null) goto L105;
        int r513 = r12.type;
        if (r513 == 31) goto L103;
        if (r513 == 37) goto L103;
        if (r513 != 33) goto L105;
    L103:
        r223.setBounds((int) (this.imageReceiver.getImageX() - AndroidUtilities.dp(13.33f)), (int) (this.imageReceiver.getImageY() - AndroidUtilities.dp(14.0f)), (int) ((this.imageReceiver.getImageX() + this.imageReceiver.getImageWidth()) + AndroidUtilities.dp(13.33f)), (int) ((this.imageReceiver.getImageY() + this.imageReceiver.getImageHeight()) + AndroidUtilities.dp(14.0f)));
        this.cardBackground.draw(r1);
    L105:
        if (this.wallpaperPreviewDrawable == null) goto L112;
        r1.save();
        r1.translate(this.imageReceiver.getImageX(), this.imageReceiver.getImageY());
        Path r224 = this.clipPath;
        if (r224 != null) goto L109;
        this.clipPath = new Path();
    L110:
        this.clipPath.addCircle(this.imageReceiver.getImageWidth() / 2.0f, this.imageReceiver.getImageHeight() / 2.0f, this.imageReceiver.getImageWidth() / 2.0f, Path.Direction.CW);
        r1.clipPath(this.clipPath);
        this.wallpaperPreviewDrawable.setBounds(0, 0, (int) this.imageReceiver.getImageWidth(), (int) this.imageReceiver.getImageHeight());
        this.wallpaperPreviewDrawable.draw(r1);
        r1.restore();
    L116:
        if (r12.type != 37) goto L118;
        DrawableUtils.drawCommunityCardDrawable(r1, Theme.dialogs_communityCardsDrawable, this.imageReceiver.getImageX() + AndroidUtilities.dp(26.0f), this.imageReceiver.getImageY() + AndroidUtilities.dp(26.0f), AndroidUtilities.dp(52.0f));
    L118:
        this.radialProgress.setProgressRect(this.imageReceiver.getImageX(), this.imageReceiver.getImageY(), this.imageReceiver.getImageX() + this.imageReceiver.getImageWidth(), this.imageReceiver.getImageY() + this.imageReceiver.getImageHeight());
        int r225 = r12.type;
        if (r225 != 21) goto L129;
        ImageUpdater r226 = MessagesController.getInstance(this.currentAccount).photoSuggestion.get(r12.messageOwner.local_id);
        if (r226 == null) goto L127;
        this.radialProgress.setProgress(r226.getCurrentImageProgress(), true);
        this.radialProgress.setCircleRadius(((int) (this.imageReceiver.getImageWidth() * 0.5f)) + 1);
        this.radialProgress.setMaxIconSize(AndroidUtilities.dp(24.0f));
        this.radialProgress.setColorKeys(Theme.key_chat_mediaLoaderPhoto, Theme.key_chat_mediaLoaderPhotoSelected, Theme.key_chat_mediaLoaderPhotoIcon, Theme.key_chat_mediaLoaderPhotoIconSelected);
        float r227 = r226.getCurrentImageProgress();
        RadialProgress2 r323 = this.radialProgress;
        if (r227 != r22) goto L125;
        r323.setIcon(4, true, true);
        goto L127
    L125:
        r323.setIcon(3, true, true);
    L127:
        this.radialProgress.draw(r1);
        goto L136
    L129:
        if (r225 != 22) goto L136;
        float r228 = getUploadingInfoProgress(r12);
        this.radialProgress.setProgress(r228, true);
        this.radialProgress.setCircleRadius(AndroidUtilities.dp(26.0f));
        this.radialProgress.setMaxIconSize(AndroidUtilities.dp(24.0f));
        this.radialProgress.setColorKeys(Theme.key_chat_mediaLoaderPhoto, Theme.key_chat_mediaLoaderPhotoSelected, Theme.key_chat_mediaLoaderPhotoIcon, Theme.key_chat_mediaLoaderPhotoIconSelected);
        RadialProgress2 r324 = this.radialProgress;
        if (r228 != r22) goto L133;
        r324.setIcon(4, true, true);
    L134:
        this.radialProgress.draw(r1);
        goto L136
    L133:
        r324.setIcon(3, true, true);
        goto L134
    L109:
        r224.rewind();
        goto L110
    L112:
        if (r12.isStoryMention() == false) goto L114;
        TLRPC.MessageMedia r229 = r12.messageOwner.media;
        long r74 = r229.user_id;
        StoriesUtilities.AvatarStoryParams r325 = this.avatarStoryParams;
        r325.storyId = r229.id;
        StoriesUtilities.drawAvatarWithStory(r74, r1, this.imageReceiver, r325);
        goto L116
    L114:
        this.imageReceiver.draw(r1);
        goto L116
    L5:
        if (this.birthdayLayout != null) goto L76;
        if (isButtonLayout(r12) == false) goto L76;
        this.stickerSize = this.giftRectSize - AndroidUtilities.dp(106.0f);
        if (isNewStyleButtonLayout() == false) goto L36;
        r2 = getImageSize(r12);
        r22 = 1.0f;
        float r103 = (this.previousWidth - r2) / 2.0f;
        float r326 = ((this.textY + this.textHeight) + AndroidUtilities.dp(4.0f)) + AndroidUtilities.dp(16.0f);
        if (r12.isStoryMention() == false) goto L13;
        r23 = 16.0f;
        this.avatarStoryParams.storyItem = r12.messageOwner.media.storyItem;
    L14:
        float r155 = r2;
        this.avatarStoryParams.originalAvatarRect.set(r103, r326, r103 + r155, r155 + r326);
        int r414 = r12.type;
        if (r414 == 31) goto L19;
        if (r414 == 33) goto L19;
        if (r414 == 34) goto L19;
        if (r414 == 35) goto L19;
    L21:
        if (r12.type != 37) goto L23;
        r103 = r103 + AndroidUtilities.dp(2.0f);
    L23:
        this.imageReceiver.setImageCoords(r103, r326, Math.max(0, r2), Math.max(0, r2));
        int r327 = r12.type;
        if (r327 == 31) goto L28;
        if (r327 == 33) goto L28;
        if (r327 == 34) goto L28;
        if (r327 == 35) goto L28;
    L29:
        ImageReceiver r328 = this.imageReceiver;
        float r415 = r2;
        if (r12.type != 37) goto L33;
        AvatarCornerType r514 = AvatarCornerType.COMMUNITY;
    L34:
        r328.setRoundRadius(ExteraConfig.getAvatarCorners(r415, true, r514));
    L58:
        TextPaint r329 = (TextPaint) getThemedPaint("paintChatActionText");
        this.textPaint = r329;
        if (r329 == null) goto L75;
        TextPaint r330 = this.giftTitlePaint;
        if (r330 != null) goto L63;
    L65:
        TextPaint r331 = this.giftSubtitlePaint;
        if (r331 != null) goto L68;
    L70:
        TextPaint r332 = this.giftTextPaint;
        if (r332 == null) goto L75;
        if (r332.getColor() == this.textPaint.getColor()) goto L75;
        this.giftTextPaint.setColor(this.textPaint.getColor());
        this.giftTextPaint.linkColor = this.textPaint.getColor();
        goto L75
    L68:
        if (r331.getColor() == this.textPaint.getColor()) goto L70;
        this.giftSubtitlePaint.setColor(this.textPaint.getColor());
        this.giftSubtitlePaint.linkColor = this.textPaint.getColor();
        goto L70
    L63:
        if (r330.getColor() == this.textPaint.getColor()) goto L65;
        this.giftTitlePaint.setColor(this.textPaint.getColor());
        goto L65
    L33:
        r514 = AvatarCornerType.DEFAULT;
    L28:
        r2 = r2 + AndroidUtilities.dp(20.0f);
    L19:
        r103 = r103 + AndroidUtilities.dp(10.0f);
        r326 = r326 + AndroidUtilities.dp(10.0f);
        r2 = r2 - AndroidUtilities.dp(20.0f);
        goto L21
    L13:
        r23 = 16.0f;
        goto L14
    L36:
        r23 = 16.0f;
        r22 = 1.0f;
        int r333 = r12.type;
        if (r333 != 11) goto L40;
        ImageReceiver r334 = this.imageReceiver;
        int r416 = this.previousWidth;
        int r515 = this.stickerSize;
        r334.setImageCoords((r416 - r515) / 2.0f, (this.textY + this.textHeight) + (this.giftRectSize * 0.075f), r515, r515);
        goto L58
    L40:
        if (r333 != 25) goto L47;
        float r230 = this.stickerSize;
        if (AndroidUtilities.isTablet() == false) goto L44;
        float r335 = 1.0f;
    L45:
        r2 = (int) (r230 * r335);
        float r417 = r2;
        this.imageReceiver.setImageCoords((this.previousWidth - r2) / 2.0f, ((this.textY + this.textHeight) + (this.giftRectSize * 0.075f)) - AndroidUtilities.dp(22.0f), r417, r417);
        goto L58
    L44:
        r335 = 1.2f;
        goto L45
    L47:
        if (r12.isStarGiftAction() == false) goto L49;
        float r113 = r2;
        this.imageReceiver.setImageCoords((this.previousWidth - r2) / 2.0f, ((this.textY + this.textHeight) + (this.giftRectSize * 0.075f)) + AndroidUtilities.dp(2.0f), r113, r113);
        goto L58
    L49:
        int r231 = r12.type;
        int r336 = this.stickerSize;
        if (r231 != 30) goto L57;
        r2 = (int) (r336 * 1.1f);
        TLRPC.Message r337 = r12.messageOwner;
        if (r337 != null) goto L54;
    L56:
        float r114 = r2;
        this.imageReceiver.setImageCoords((this.previousWidth - r2) / 2.0f, ((this.textY + this.textHeight) + (this.giftRectSize * 0.075f)) - AndroidUtilities.dp(12.0f), r114, r114);
        goto L58
    L54:
        if ((r337.action instanceof TLRPC.TL_messageActionStarGift) == true) goto L56;
        float r418 = r2;
        this.imageReceiver.setImageCoords((this.previousWidth - r2) / 2.0f, ((this.textY + this.textHeight) + (this.giftRectSize * 0.075f)) - AndroidUtilities.dp(22.0f), r418, r418);
        goto L58
    L57:
        r2 = (int) (r336 * 1.0f);
        float r115 = r2;
        this.imageReceiver.setImageCoords((this.previousWidth - r2) / 2.0f, ((this.textY + this.textHeight) + (this.giftRectSize * 0.075f)) - AndroidUtilities.dp(4.0f), r115, r115);
        goto L58
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r9) {
        super.onInitializeAccessibilityNodeInfo(r9);
        MessageObject r0 = this.currentMessageObject;
        if (TextUtils.isEmpty(this.customText) == false) goto L7;
        if (r0 != null) goto L7;
        return;
    L7:
        if (this.accessibilityText == null) goto L9;
    L16:
        r9.setText(this.accessibilityText);
        r9.setEnabled(true);
        return;
    L9:
        if (TextUtils.isEmpty(this.customText) == true) goto L11;
        CharSequence r02 = this.customText;
    L12:
        SpannableStringBuilder r1 = new SpannableStringBuilder(r02);
        int r3 = 0;
        CharacterStyle[] r03 = (CharacterStyle[]) r1.getSpans(0, r1.length(), ClickableSpan.class);
        int r2 = r03.length;
    L13:
        if (r3 >= r2) goto L15;
        CharacterStyle r4 = r03[r3];
        int r5 = r1.getSpanStart(r4);
        int r6 = r1.getSpanEnd(r4);
        r1.removeSpan(r4);
        r1.setSpan(new AnonymousClass1(this, r4), r5, r6, 33);
        r3 = r3 + 1;
        goto L13
    L15:
        this.accessibilityText = r1;
        goto L16
    L11:
        r02 = r0.messageText;
        goto L12
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        View r12 = this.rippleView;
        RectF r0 = this.giftButtonRect;
        r12.layout((int) r0.left, (int) r0.top, (int) r0.right, (int) r0.bottom);
    }

    @Override // org.telegram.ui.Cells.BaseCell
    public boolean onLongPress() {
        if (this.actionPressed == false) goto L10;
        View.OnLongClickListener r0 = this.onActionLongClick;
        if (r0 == null) goto L10;
        if (r0.onLongClick(this) == false) goto L10;
        this.actionPressed = false;
        return true;
    L10:
        ChatActionCellDelegate r02 = this.delegate;
        if (r02 != null) goto L13;
        return false;
    L13:
        return r02.didLongPress(this, this.lastTouchX, this.lastTouchY);
    }

    @Override // android.view.View
    public void onMeasure(int r25, int r26) {
        MessageObject r1 = this.currentMessageObject;
        if (r1 == null) goto L5;
    L8:
        int r9 = 0;
        if (isButtonLayout(r1) == true) goto L11;
    L37:
        int r10 = Math.max(AndroidUtilities.dp(30.0f), View.MeasureSpec.getSize(r25));
        if (this.previousWidth == r10) goto L41;
        this.wasLayout = true;
        this.previousWidth = r10;
        buildLayout();
    L41:
        if (r1 != null) goto L43;
    L49:
        int r13 = 0;
    L51:
        if (this.starGiftLayout.has() == true) goto L53;
        SuggestBirthdayActionLayout r14 = this.birthdayLayout;
        if (r14 == null) goto L66;
        r9 = r14.height() + AndroidUtilities.dp(12.0f);
        ReactionsLayoutInBubble r3 = this.reactionsLayoutInBubble;
        if (r3.isEmpty == false) goto L64;
    L59:
        float r262 = 14.0f;
        float r23 = 24.0f;
    L195:
        if (this.currentMessageObject == null) goto L200;
        ReactionsLayoutInBubble r2 = this.reactionsLayoutInBubble;
        if (r2.isEmpty == true) goto L200;
        r2.totalHeight = r2.height + AndroidUtilities.dp(8.0f);
        r13 = r13 + this.reactionsLayoutInBubble.totalHeight;
    L200:
        if (isMessageActionSuggestedPostApproval() == false) goto L202;
        r13 = r13 + (this.titleHeight + AndroidUtilities.dp(r23));
    L202:
        if (r1 != null) goto L204;
    L206:
        setMeasuredDimension(r10, ((this.topicSeparatorTopPadding + this.textHeight) + r13) + AndroidUtilities.dp(r262));
    L207:
        this.reactionsLayoutInBubble.y = (getMeasuredHeight() - getPaddingTop()) - this.reactionsLayoutInBubble.totalHeight;
        return;
    L204:
        if (isNewStyleButtonLayout() == false) goto L206;
        setMeasuredDimension(r10, this.topicSeparatorTopPadding + r9);
        goto L207
    L64:
        r3.totalHeight = r3.height + AndroidUtilities.dp(8.0f);
        int r32 = this.reactionsLayoutInBubble.totalHeight;
    L58:
        r9 = r9 + r32;
        goto L59
    L66:
        if (isButtonLayout(r1) == false) goto L59;
        boolean r142 = isGiftChannel(r1);
        r262 = 14.0f;
        int r22 = getImageSize(r1);
        boolean r18 = isNewStyleButtonLayout();
        int r33 = this.textY;
        if (r18 == false) goto L77;
        float r182 = 10.0f;
        int r34 = (r33 + this.textHeight) + AndroidUtilities.dp(4.0f);
        int r12 = AndroidUtilities.dp(16.0f);
        if (r22 <= 0) goto L72;
        r12 = (r12 * 2) + r22;
    L72:
        int r35 = r34 + r12;
        TextLayout r122 = this.giftPremiumText;
        if (r122 != null) goto L75;
        int r123 = 0;
    L76:
        float r36 = r35 + r123;
    L82:
        this.giftPremiumAdditionalHeight = 0;
        float r222 = 0.0f;
        if (this.giftPremiumTitleLayout == null) goto L97;
        float r37 = r36 + r12.getHeight();
        if (this.giftPremiumTitleLayout.getLineCount() <= 1) goto L87;
        r23 = 24.0f;
        this.giftPremiumAdditionalHeight += this.giftPremiumTitleLayout.getHeight() - this.giftPremiumTitleLayout.getLineTop(1);
    L88:
        if (r142 == false) goto L90;
        float r5 = 6.0f;
    L91:
        float r38 = r37 + AndroidUtilities.dp(r5);
        if (this.giftPremiumSubtitleLayout == null) goto L95;
        r38 = r38 + (r5.getHeight() + AndroidUtilities.dp(9.0f));
    L95:
        if (this.giftPremiumReleasedText == null) goto L98;
        r38 = r38 + AndroidUtilities.dp(r23);
    L98:
        TextLayout r52 = this.giftPremiumText;
        if (r52 != null) goto L101;
        int r53 = 0;
    L103:
        if (this.giftPremiumText != null) goto L106;
        this.giftPremiumAdditionalHeight = 0;
    L128:
        if (this.giftPremiumReleasedText == null) goto L130;
        this.giftPremiumAdditionalHeight += AndroidUtilities.dp(r23);
    L130:
        int r54 = this.giftPremiumAdditionalHeight;
        if (r142 == false) goto L133;
        r222 = 14.0f;
    L133:
        int r55 = r54 - AndroidUtilities.dp(r222);
        this.giftPremiumAdditionalHeight = r55;
        r13 = r13 + r55;
        int r56 = (this.textHeight + r13) + AndroidUtilities.dp(14.0f);
        StaticLayout r6 = this.giftPremiumButtonLayout;
        if (r6 == null) goto L147;
        float r57 = r56 - r38;
        if (r6 == null) goto L138;
        int r62 = r6.getHeight();
    L139:
        float r39 = r38 + (((r57 - r62) - AndroidUtilities.dp(8.0f)) / 2.0f);
        if (this.currentMessageObject.isStarGiftAction() == false) goto L142;
        r39 = r39 + AndroidUtilities.dp(4.0f);
    L142:
        float r58 = (this.previousWidth - this.giftPremiumButtonWidth) / 2.0f;
        RectF r63 = this.giftButtonRect;
        float r124 = r58 - AndroidUtilities.dp(18.0f);
        float r143 = r39 - AndroidUtilities.dp(8.0f);
        float r59 = (r58 + this.giftPremiumButtonWidth) + AndroidUtilities.dp(18.0f);
        StaticLayout r15 = this.giftPremiumButtonLayout;
        if (r15 == null) goto L145;
        int r152 = r15.getHeight();
    L146:
        r63.set(r124, r143, r59, (r39 + r152) + AndroidUtilities.dp(8.0f));
    L154:
        int r310 = getMeasuredWidth() << (getMeasuredHeight() + 16);
        this.starParticlesDrawable.rect.set(this.giftButtonRect);
        this.starParticlesDrawable.rect2.set(this.giftButtonRect);
        if (this.starsSize == r310) goto L158;
        this.starsSize = r310;
        this.starParticlesDrawable.resetPositions();
    L158:
        if (isNewStyleButtonLayout() == false) goto L193;
        int r311 = (this.textY + this.textHeight) + AndroidUtilities.dp(4.0f);
        this.backgroundRectHeight = 0;
        int r510 = AndroidUtilities.dp(16.0f);
        if (r22 <= 0) goto L162;
        r510 = (r510 * 2) + r22;
    L162:
        this.backgroundRectHeight = r510;
        StaticLayout r24 = this.giftPremiumSubtitleLayout;
        if (r24 == null) goto L166;
        this.backgroundRectHeight = r510 + (r24.getHeight() + AndroidUtilities.dp(r182));
    L166:
        if (this.giftPremiumReleasedText == null) goto L168;
        this.backgroundRectHeight += AndroidUtilities.dp(15.0f);
    L168:
        StaticLayout r27 = this.giftPremiumTitleLayout;
        if (r27 == null) goto L171;
        this.backgroundRectHeight += r27.getHeight() + AndroidUtilities.dp(4.0f);
    L171:
        int r28 = this.backgroundRectHeight;
        TextLayout r64 = this.giftPremiumText;
        if (r64 == null) goto L175;
        r9 = r64.layout.getHeight();
    L175:
        int r29 = r28 + r9;
        this.backgroundRectHeight = r29;
        float r65 = (this.previousWidth - this.giftPremiumButtonWidth) / 2.0f;
        if (this.giftPremiumButtonLayout == null) goto L179;
        this.backgroundButtonTop = (r29 + r311) + AndroidUtilities.dp(7.0f);
        this.giftButtonRect.set(r65 - AndroidUtilities.dp(18.0f), this.backgroundButtonTop, (r65 + this.giftPremiumButtonWidth) + AndroidUtilities.dp(18.0f), (this.backgroundButtonTop + this.giftPremiumButtonLayout.getHeight()) + (AndroidUtilities.dp(8.0f) * 2));
        this.backgroundRectHeight = (int) (this.backgroundRectHeight + (AndroidUtilities.dp(4.0f) + this.giftButtonRect.height()));
    L187:
        int r210 = this.backgroundRectHeight + AndroidUtilities.dp(15.0f);
        this.backgroundRectHeight = r210;
        int r312 = (r311 + r210) + AndroidUtilities.dp(6.0f);
        ReactionsLayoutInBubble r211 = this.reactionsLayoutInBubble;
        if (r211.isEmpty == true) goto L190;
        r211.totalHeight = r211.height + AndroidUtilities.dp(8.0f);
        r312 = r312 + this.reactionsLayoutInBubble.totalHeight;
    L190:
        r9 = r312;
        if (this.botInlineButtons == null) goto L193;
        r9 = r9 + AndroidUtilities.dp(44.0f);
        goto L193
    L179:
        if (isMessageActionSuggestedPostApproval() == true) goto L187;
        if (r1 == null) goto L186;
        int r212 = r1.type;
        if (r212 == 34) goto L187;
        if (r212 == 33) goto L187;
        if (r212 == 35) goto L187;
    L186:
        this.giftButtonRect.set(r65 - AndroidUtilities.dp(18.0f), this.backgroundButtonTop, (r65 + this.giftPremiumButtonWidth) + AndroidUtilities.dp(18.0f), (this.backgroundButtonTop + AndroidUtilities.dp(17.0f)) + (AndroidUtilities.dp(8.0f) * 2));
        this.backgroundRectHeight += AndroidUtilities.dp(17.0f);
    L193:
        this.giftButtonRect.inset(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(1.0f));
        goto L195
    L145:
        r152 = 0;
        goto L146
    L138:
        r62 = 0;
        goto L139
    L147:
        r13 = r13 - AndroidUtilities.dp(40.0f);
        this.giftPremiumAdditionalHeight -= AndroidUtilities.dp(40.0f);
        MessageObject r313 = this.currentMessageObject;
        if (r313 == null) goto L154;
        TLRPC.Message r314 = r313.messageOwner;
        if (r314 == null) goto L154;
        if ((r314.action instanceof TLRPC.TL_messageActionStarGift) == false) goto L154;
        r13 = r13 - AndroidUtilities.dp(8.0f);
        this.giftPremiumAdditionalHeight -= AndroidUtilities.dp(8.0f);
        goto L154
    L106:
        if (this.giftPremiumSubtitleLayout == null) goto L108;
        this.giftPremiumAdditionalHeight += r53 + AndroidUtilities.dp(r182);
        goto L128
    L108:
        MessageObject r8 = this.currentMessageObject;
        if (r8.type != 18) goto L111;
    L122:
        int r66 = this.giftPremiumAdditionalHeight;
        if (this.giftPremiumButtonLayout != null) goto L125;
        float r7 = 0.0f;
    L126:
        this.giftPremiumAdditionalHeight = r66 + (r53 - AndroidUtilities.dp(r7));
        goto L128
    L125:
        r7 = r182;
        goto L126
    L111:
        if (r8.isStarGiftAction() == true) goto L122;
        if (this.currentMessageObject.type != 30) goto L117;
        this.giftPremiumAdditionalHeight += r53 - AndroidUtilities.dp(20.0f);
        goto L128
    L117:
        if (this.giftPremiumTextCollapsed == false) goto L120;
        this.giftPremiumAdditionalHeight += r53;
        goto L128
    L120:
        if (this.giftPremiumText.layout.getLineCount() <= 2) goto L128;
        this.giftPremiumAdditionalHeight += ((this.giftPremiumText.layout.getLineBottom(0) - this.giftPremiumText.layout.getLineTop(0)) * this.giftPremiumText.layout.getLineCount()) - 2;
        goto L128
    L101:
        r53 = r52.layout.getHeight();
        goto L103
    L90:
        r5 = 0.0f;
        goto L91
    L87:
        r23 = 24.0f;
        goto L88
    L97:
        r23 = 24.0f;
        r38 = r36 - AndroidUtilities.dp(12.0f);
        this.giftPremiumAdditionalHeight -= AndroidUtilities.dp(30.0f);
        goto L98
    L75:
        r123 = r122.layout.getHeight() + AndroidUtilities.dp(4.0f);
        goto L76
    L77:
        r182 = 10.0f;
        float r315 = (((r33 + this.textHeight) + (this.giftRectSize * 0.075f)) + r22) + AndroidUtilities.dp(4.0f);
        TextLayout r125 = this.giftPremiumText;
        if (r125 != null) goto L80;
        int r126 = 0;
    L81:
        r36 = r315 + r126;
        goto L82
    L80:
        r126 = r125.layout.getHeight() + AndroidUtilities.dp(4.0f);
        goto L81
    L53:
        if (this.starGiftLayout.repost == true) goto L55;
        r9 = (this.textY + this.textHeight) + AndroidUtilities.dp(16.0f);
    L55:
        r9 = r9 + (((int) this.starGiftLayout.getHeight()) + AndroidUtilities.dp(8.0f));
        ReactionsLayoutInBubble r316 = this.reactionsLayoutInBubble;
        if (r316.isEmpty == true) goto L59;
        r316.totalHeight = r316.height + AndroidUtilities.dp(8.0f);
        r32 = this.reactionsLayoutInBubble.totalHeight;
        goto L58
    L43:
        if (r1.type != 11) goto L47;
        int r132 = AndroidUtilities.roundMessageSize;
        int r144 = AndroidUtilities.dp(10.0f);
    L45:
        r13 = r132 + r144;
        goto L51
    L47:
        if (isButtonLayout(r1) == false) goto L49;
        r132 = this.giftRectSize;
        r144 = AndroidUtilities.dp(12.0f);
        goto L45
    L11:
        if (AndroidUtilities.isTablet() == false) goto L13;
        float r317 = AndroidUtilities.getMinTabletSide() * 0.6f;
    L14:
        this.giftRectSize = Math.min((int) r317, ((AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(64.0f));
        if (AndroidUtilities.isTablet() == true) goto L22;
        int r318 = r1.type;
        if (r318 == 18) goto L23;
        if (r318 == 30) goto L23;
        if (isMessageActionSuggestedPostApproval() == false) goto L22;
    L23:
        this.giftRectSize = (int) (this.giftRectSize * 1.2f);
    L24:
        this.stickerSize = this.giftRectSize - AndroidUtilities.dp(106.0f);
        if (r1.type != 31) goto L28;
        this.giftRectSize = Math.min(this.giftRectSize, AndroidUtilities.dp(192.0f));
        this.stickerSize = AndroidUtilities.dp(78.0f);
    L28:
        if (r1.type != 33) goto L31;
        this.giftRectSize = Math.min(this.giftRectSize, AndroidUtilities.dp(220.0f));
        this.stickerSize = AndroidUtilities.dp(78.0f);
    L31:
        if (r1.type != 37) goto L33;
        this.stickerSize = AndroidUtilities.dp(52.0f);
        this.imageReceiver.setRoundRadius(ExteraConfig.getAvatarCorners(52.0f, false, AvatarCornerType.COMMUNITY));
        goto L37
    L33:
        boolean r319 = isNewStyleButtonLayout();
        ImageReceiver r102 = this.imageReceiver;
        if (r319 == false) goto L36;
        r102.setRoundRadius(ExteraConfig.getAvatarCorners(this.stickerSize * 0.7f, true));
        goto L37
    L36:
        r102.setRoundRadius(0);
    L22:
        if (r1.type != 35) goto L24;
    L13:
        r317 = (AndroidUtilities.displaySize.x * 0.62f) - AndroidUtilities.dp(34.0f);
        goto L14
    L5:
        if (this.customText != null) goto L8;
        setMeasuredDimension(View.MeasureSpec.getSize(r25), (this.topicSeparatorTopPadding + this.textHeight) + AndroidUtilities.dp(14.0f));
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r1, long r2, long r4) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r12) {
        MessageObject r9 = this.currentMessageObject;
        if (r9 != null) goto L5;
        return;
    L5:
        if (r9.type != 11) goto L18;
        int r122 = r9.photoThumbs.size();
        int r0 = 0;
    L7:
        if (r0 >= r122) goto L12;
        TLRPC.PhotoSize r1 = r9.photoThumbs.get(r0);
        if ((r1 instanceof TLRPC.TL_photoStrippedSize) == true) goto L13;
        r0 = r0 + 1;
    L13:
        TLRPC.PhotoSize r123 = r1;
        this.imageReceiver.setImage(this.currentVideoLocation, ImageLoader.AUTOPLAY_FILTER, ImageLocation.getForObject(r123, r9.photoThumbsObject), "50_50_b", this.avatarDrawable, 0, null, r9, 1);
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        return;
    L12:
        r1 = null;
        goto L13
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r17) {
        MessageObject r2 = this.currentMessageObject;
        float r3 = r17.getX() - (this.sideMenuWidth / 2.0f);
        this.lastTouchX = r3;
        float r4 = r17.getY() + getPaddingTop();
        this.lastTouchY = r4;
        boolean r7 = true;
        boolean r8 = false;
        if (r2 == null) goto L5;
        TopicSeparator r9 = this.topicSeparator;
        if (r9 != null) goto L40;
    L42:
        SuggestBirthdayActionLayout r92 = this.birthdayLayout;
        if (r92 == null) goto L48;
        if (r92.onTouchEvent(r17) == false) goto L48;
        return true;
    L48:
        if (this.starGiftLayout.has() == false) goto L53;
        if (this.starGiftLayout.onTouchEvent(this.starGiftLayoutX, this.starGiftLayoutY, r17) == false) goto L53;
        return true;
    L53:
        if (this.reactionsLayoutInBubble.checkTouchEvent(r17) == false) goto L56;
        return true;
    L56:
        if (r17.getAction() != 0) goto L111;
        if (this.delegate != null) goto L60;
    L122:
        boolean r5 = false;
    L264:
        if (r5 == false) goto L266;
    L325:
        if (r5 == true) goto L327;
        r5 = checkBotButtonMotionEvent(r17);
    L327:
        if (r5 == false) goto L329;
        return r5;
    L329:
        return super.onTouchEvent(r17);
    L266:
        if (r17.getAction() != 0) goto L268;
    L273:
        TextLayout r6 = this.giftPremiumText;
        if (r6 == null) goto L293;
        List<SpoilerEffect> r62 = r6.spoilers;
        if (r62 == null) goto L293;
        if (r62.isEmpty() == true) goto L293;
        if (this.isSpoilerRevealing == true) goto L293;
        Iterator<SpoilerEffect> r63 = this.giftPremiumText.spoilers.iterator();
    L283:
        if (r63.hasNext() == false) goto L293;
        SpoilerEffect r93 = r63.next();
        Rect r10 = r93.getBounds();
        TextLayout r11 = this.giftPremiumText;
        if (r10.contains((int) (r3 - r11.x), (int) (r4 - r11.y)) == false) goto L283;
        this.pressedLink = null;
        if (r17.getAction() != 0) goto L290;
        this.spoilerPressed = r93;
    L289:
        r5 = true;
        goto L293
    L290:
        SpoilerEffect r52 = this.spoilerPressed;
        if (r93 != r52) goto L289;
        this.isSpoilerRevealing = true;
        r52.setOnRippleEndCallback(new ChatActionCell$$ExternalSyntheticLambda4(this));
        float r53 = (float) Math.sqrt(Math.pow(this.giftPremiumText.layout.getWidth(), 2.0d) + Math.pow(this.giftPremiumText.layout.getHeight(), 2.0d));
        SpoilerEffect r64 = this.spoilerPressed;
        TextLayout r94 = this.giftPremiumText;
        r64.startRipple((int) (r3 - r94.x), (int) (r4 - r94.y), r53);
        invalidate();
    L293:
        if (r5 == true) goto L324;
        StaticLayout r65 = this.textLayout;
        if (r65 == null) goto L324;
        if (r3 < this.textX) goto L324;
        int r102 = this.textY;
        if (r4 < r102) goto L324;
        if (r3 > (r9 + this.textWidth)) goto L324;
        if (r4 > (this.textHeight + r102)) goto L324;
        float r42 = r4 - r102;
        float r32 = r3 - this.textXLeft;
        if (r5 == true) goto L325;
        int r43 = r65.getLineForVertical((int) r42);
        int r66 = this.textLayout.getOffsetForHorizontal(r43, r32);
        float r95 = this.textLayout.getLineLeft(r43);
        if (r95 <= r32) goto L309;
    L323:
        this.pressedLink = null;
        goto L325
    L309:
        if ((r95 + this.textLayout.getLineWidth(r43)) < r32) goto L323;
        CharSequence r22 = r2.messageText;
        if ((r22 instanceof Spannable) == false) goto L323;
        URLSpan[] r23 = (URLSpan[]) ((Spannable) r22).getSpans(r66, r66, URLSpan.class);
        if (r23.length != 0) goto L315;
        this.pressedLink = null;
    L321:
        r7 = r5;
    L322:
        r5 = r7;
        goto L325
    L315:
        if (r17.getAction() != 0) goto L317;
        this.pressedLink = r23[0];
        goto L322
    L317:
        URLSpan r24 = r23[0];
        URLSpan r33 = this.pressedLink;
        if (r24 != r33) goto L321;
        openLink(r33);
    L324:
        this.pressedLink = null;
        goto L325
    L268:
        if (this.pressedLink != null) goto L272;
        if (this.spoilerPressed == null) goto L325;
    L272:
        if (r17.getAction() != 1) goto L325;
    L60:
        if (r2.type == 11) goto L64;
        if (isButtonLayout(r2) == true) goto L64;
    L66:
        r5 = false;
    L68:
        if (this.radialProgress.getIcon() != 4) goto L76;
        int r67 = r2.type;
        if (r67 == 21) goto L74;
        if (r67 != 22) goto L76;
    L74:
        if (this.backgroundRect.contains(r3, r4) == false) goto L76;
        this.imagePressed = true;
        r5 = true;
    L76:
        TextLayout r68 = this.giftPremiumText;
        if (r68 == null) goto L84;
        if (this.giftPremiumTextCollapsed == false) goto L84;
        RectF r96 = AndroidUtilities.rectTmp;
        float r12 = r68.x;
        r96.set(r12, r68.y, r68.layout.getWidth() + r12, this.giftPremiumText.y + r15.layout.getHeight());
        if (r96.contains(r3, r4) == false) goto L84;
        this.textPressed = true;
        r5 = true;
    L84:
        if (isButtonLayout(r2) == true) goto L86;
    L94:
        if (r5 == false) goto L96;
    L98:
        if (r5 == true) goto L108;
        MessageObject r69 = this.currentMessageObject;
        if (r69 == null) goto L104;
        TLRPC.Message r610 = r69.messageOwner;
        if (r610 == null) goto L104;
        TLRPC.MessageAction r611 = r610.action;
    L106:
        if (TlUtils.isInstance(r611, new Class[]{TLRPC.TL_messageActionSuggestedPostRefund.class, TLRPC.TL_messageActionSuggestedPostSuccess.class}) == false) goto L108;
        this.textPressed = true;
        r5 = true;
    L104:
        r611 = null;
    L108:
        if (r5 == false) goto L264;
        startCheckLongPress();
        goto L264
    L96:
        if (isMessageActionSuggestedPostApproval() == false) goto L98;
        this.textPressed = true;
        r5 = true;
        goto L98
    L86:
        if (this.giftPremiumButtonLayout == null) goto L94;
        if (this.giftButtonRect.contains(r3, r4) == false) goto L90;
    L93:
        View r54 = this.rippleView;
        this.giftButtonPressed = true;
        r54.setPressed(true);
        this.bounce.setPressed(true);
        r5 = true;
        goto L94
    L90:
        if (this.buttonClickableAsImage == false) goto L94;
        if (this.backgroundRect.contains(r3, r4) == false) goto L94;
    L64:
        if (this.imageReceiver.isInsideImage(r3, r4) == false) goto L66;
        this.imagePressed = true;
        r5 = true;
        goto L68
    L111:
        if (r17.getAction() == 2) goto L114;
        cancelCheckLongPress();
    L114:
        if (this.actionPressed == false) goto L133;
        if (r17.getAction() != 2) goto L124;
        if (r3 >= this.backgroundLeft) goto L120;
    L121:
        this.actionPressed = false;
        goto L122
    L120:
        if (r3 <= this.backgroundRight) goto L122;
    L124:
        if (r17.getAction() != 1) goto L130;
        View.OnClickListener r55 = this.onActionClick;
        if (r55 == null) goto L128;
        r55.onClick(this);
    L128:
        this.actionPressed = false;
        goto L122
    L130:
        if (r17.getAction() != 3) goto L122;
        this.actionPressed = false;
        goto L122
    L133:
        if (this.textPressed == false) goto L178;
        int r97 = r17.getAction();
        if (r97 == 1) goto L150;
        if (r97 == 2) goto L140;
        if (r97 != 3) goto L122;
        this.textPressed = false;
        this.bounce.setPressed(false);
        goto L122
    L140:
        TextLayout r56 = this.giftPremiumText;
        if (r56 != null) goto L143;
    L149:
        this.textPressed = false;
    L148:
        r5 = true;
        goto L264
    L143:
        if (this.giftPremiumTextCollapsed == false) goto L149;
        RectF r612 = AndroidUtilities.rectTmp;
        float r98 = r56.x;
        r612.set(r98, r56.y, r56.layout.getWidth() + r98, this.giftPremiumText.y + r11.layout.getHeight());
        if (r612.contains(r3, r4) == true) goto L122;
        this.textPressed = false;
        goto L148
    L150:
        View r57 = this.rippleView;
        this.textPressed = false;
        r57.setPressed(false);
        this.bounce.setPressed(false);
        if (this.delegate == null) goto L160;
        if (r2.replyMessageObject == null) goto L160;
        TLRPC.Message r58 = r2.messageOwner;
        if (r58 == null) goto L160;
        if (TlUtils.isInstance(r58.action, new Class[]{TLRPC.TL_messageActionTodoAppendTasks.class, TLRPC.TL_messageActionTodoCompletions.class, TLRPC.TL_messageActionSuggestedPostApproval.class, TLRPC.TL_messageActionSuggestedPostRefund.class, TLRPC.TL_messageActionSuggestedPostSuccess.class}) == false) goto L160;
        this.delegate.didPressReplyMessage(this, this.currentMessageObject.getReplyMsgId());
    L160:
        if (this.giftPremiumTextCollapsed == false) goto L172;
        if (this.giftPremiumTextUncollapsed == true) goto L172;
        TextLayout r59 = this.giftPremiumText;
        if (r59 == null) goto L172;
        int r1 = r59.layout.getHeight() - this.giftPremiumTextCollapsedHeight;
        this.giftPremiumTextUncollapsed = true;
        ChatActionCellDelegate r25 = this.delegate;
        if (r25 == null) goto L170;
        r25.forceUpdate(this, false);
        if ((getParent() instanceof RecyclerListView) == false) goto L170;
        ((RecyclerListView) getParent()).smoothScrollBy(0, r1 + AndroidUtilities.dp(24.0f));
    L170:
        return true;
    L172:
        if (this.birthdayLayout == null) goto L122;
        if (this.backgroundRect.contains(r17.getX(), r17.getY()) == false) goto L122;
        this.birthdayLayout.open();
        return true;
    L178:
        if (this.giftButtonPressed == false) goto L221;
        int r99 = r17.getAction();
        if (r99 == 1) goto L192;
        if (r99 == 2) goto L186;
        if (r99 != 3) goto L122;
        this.imagePressed = false;
        View r510 = this.rippleView;
        this.giftButtonPressed = false;
        r510.setPressed(false);
        this.bounce.setPressed(false);
        goto L122
    L186:
        if (isButtonLayout(r2) == true) goto L188;
    L191:
        View r511 = this.rippleView;
        this.giftButtonPressed = false;
        r511.setPressed(false);
        this.bounce.setPressed(false);
        goto L122
    L188:
        if (this.giftButtonRect.contains(r3, r4) == true) goto L122;
        if (this.backgroundRect.contains(r3, r4) == true) goto L122;
    L192:
        this.imagePressed = false;
        View r512 = this.rippleView;
        this.giftButtonPressed = false;
        r512.setPressed(false);
        this.bounce.setPressed(false);
        if (this.delegate == null) goto L122;
        int r513 = r2.type;
        if (r513 != 37) goto L199;
        playSoundEffect(0);
        BaseFragment r514 = LaunchActivity.getSafeLastFragment();
        if (r514 == null) goto L122;
        new CommunitySheet(r514, ((TLRPC.TL_messageActionChangeCommunity) r2.messageOwner.action).community_id).show();
        goto L122
    L199:
        if (r513 != 31) goto L201;
        playSoundEffect(0);
        openStarsGiftTransaction();
        goto L122
    L201:
        if (r513 != 25) goto L203;
        playSoundEffect(0);
        openPremiumGiftChannel();
        goto L122
    L203:
        if (r513 != 18) goto L205;
        playSoundEffect(0);
        openPremiumGiftPreview();
        goto L122
    L205:
        if (r513 != 30) goto L207;
        playSoundEffect(0);
        openStarsGiftTransaction();
        goto L122
    L207:
        TLRPC.Message r515 = r2.messageOwner;
        if (r515 == null) goto L215;
        TLRPC.MessageAction r516 = r515.action;
        if ((r516 instanceof TLRPC.TL_messageActionSuggestedPostApproval) == false) goto L215;
        if (((TLRPC.TL_messageActionSuggestedPostApproval) r516).balance_too_low == false) goto L215;
        playSoundEffect(0);
        openStarsNeedSheet();
    L215:
        if (MessagesController.getInstance(this.currentAccount).photoSuggestion.get(r2.messageOwner.local_id) != null) goto L122;
        boolean r517 = this.buttonClickableAsImage;
        ChatActionCellDelegate r613 = this.delegate;
        if (r517 == false) goto L219;
        r613.didClickImage(this);
        goto L122
    L219:
        r613.didClickButton(this);
        goto L122
    L221:
        if (this.imagePressed == false) goto L122;
        int r910 = r17.getAction();
        if (r910 == 1) goto L236;
        if (r910 == 2) goto L229;
        if (r910 != 3) goto L122;
        this.imagePressed = false;
        goto L122
    L229:
        if (isNewStyleButtonLayout() == false) goto L234;
        if (this.backgroundRect.contains(r3, r4) == true) goto L122;
        this.imagePressed = false;
        goto L122
    L234:
        if (this.imageReceiver.isInsideImage(r3, r4) == true) goto L122;
        this.imagePressed = false;
        goto L122
    L236:
        this.imagePressed = false;
        if (this.giftPremiumTextCollapsed == true) goto L239;
    L248:
        int r518 = r2.type;
        if (r518 != 31) goto L251;
        openStarsGiftTransaction();
        goto L122
    L251:
        if (r518 != 25) goto L253;
        openPremiumGiftChannel();
        goto L122
    L253:
        if (r518 != 18) goto L255;
        openPremiumGiftPreview();
        goto L122
    L255:
        if (r518 != 30) goto L258;
        openStarsGiftTransaction();
        goto L122
    L258:
        if (this.delegate == null) goto L122;
        if (r518 != 21) goto L263;
        ImageUpdater r519 = MessagesController.getInstance(this.currentAccount).photoSuggestion.get(r2.messageOwner.local_id);
        if (r519 == null) goto L263;
        r519.cancel();
    L263:
        this.delegate.didClickImage(this);
        playSoundEffect(0);
        goto L122
    L239:
        if (this.giftPremiumTextUncollapsed == true) goto L248;
        TextLayout r520 = this.giftPremiumText;
        if (r520 == null) goto L248;
        int r13 = r520.layout.getHeight() - this.giftPremiumTextCollapsedHeight;
        this.giftPremiumTextUncollapsed = true;
        ChatActionCellDelegate r26 = this.delegate;
        if (r26 == null) goto L247;
        r26.forceUpdate(this, false);
        if ((getParent() instanceof RecyclerListView) == false) goto L247;
        ((RecyclerListView) getParent()).smoothScrollBy(0, r13 + AndroidUtilities.dp(16.0f));
    L247:
        return true;
    L40:
        if (r9.onTouchEvent(r17, false) == false) goto L42;
        return true;
    L5:
        if (this.onActionClick == null) goto L29;
        if (r17.getAction() != 0) goto L14;
        if (r3 < this.backgroundLeft) goto L29;
        if (r3 > this.backgroundRight) goto L29;
        this.actionPressed = true;
        startCheckLongPress();
        r8 = true;
        goto L29
    L14:
        if (this.actionPressed == false) goto L29;
        if (r17.getAction() != 1) goto L19;
        this.onActionClick.onClick(this);
        this.actionPressed = false;
        goto L29
    L19:
        if (r17.getAction() != 3) goto L22;
        this.actionPressed = false;
        goto L29
    L22:
        if (r17.getAction() != 2) goto L29;
        if (r3 >= this.backgroundLeft) goto L26;
    L27:
        this.actionPressed = false;
        cancelCheckLongPress();
        goto L29
    L26:
        if (r3 > this.backgroundRight) goto L27;
    L29:
        if (r17.getAction() != 0) goto L31;
    L33:
        if (r8 == false) goto L36;
        return true;
    L36:
        return super.onTouchEvent(r17);
    L31:
        if (r17.getAction() == 2) goto L33;
        cancelCheckLongPress();
        goto L33
    }

    public void setCustomDate(int r3, boolean r4, boolean r5) {
        int r0 = this.customDate;
        if (r0 != r3) goto L5;
        return;
    L5:
        if ((r0 / 3600) == (r3 / 3600)) goto L21;
        if (r4 == true) goto L9;
        String r42 = LocaleController.formatDateChat(r3);
    L13:
        this.customDate = r3;
        CharSequence r32 = this.customText;
        if (r32 != null) goto L16;
    L18:
        this.customText = r42;
        this.accessibilityText = null;
        updateTextInternal(r5);
        return;
    L16:
        if (TextUtils.equals(r42, r32) == false) goto L18;
        return;
    L9:
        if (r3 != 2147483646) goto L11;
        r42 = LocaleController.getString("MessageScheduledUntilOnline", org.telegram.messenger.R.string.MessageScheduledUntilOnline);
        goto L13
    L11:
        r42 = LocaleController.formatString("MessageScheduledOn", org.telegram.messenger.R.string.MessageScheduledOn, new Object[]{LocaleController.formatDateChat(r3)});
        goto L13
    }

    public void setCustomText(CharSequence r1) {
        this.customText = r1;
        if (r1 == null) goto L6;
        updateTextInternal(false);
        return;
    }

    public void setDelegate(ChatActionCellDelegate r1) {
        this.delegate = r1;
    }

    public void setInvalidateColors(boolean r2) {
        if (this.invalidateColors != r2) goto L5;
        return;
    L5:
        this.invalidateColors = r2;
        invalidate();
    }

    public void setInvalidateListener(Runnable r1) {
        this.invalidateListener = r1;
    }

    public void setInvalidateWithParent(View r1) {
        this.invalidateWithParent = r1;
    }

    public void setInvalidatesParent(boolean r1) {
        this.invalidatesParent = r1;
    }

    public void setMessageObject(MessageObject r2) {
        setMessageObject(r2, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v11, types: [org.telegram.tgnet.TLRPC$messages_StickerSet] */
    /* JADX WARN: Type inference failed for: r4v95 */
    /* JADX WARN: Type inference failed for: r4v96 */
    public void setMessageObject(MessageObject r34, boolean r35) {
        MessageObject r10 = r34;
        if (r10 != null) goto L6;
        return;
    L6:
        if (this.currentMessageObject != r10) goto L22;
        StaticLayout r2 = this.textLayout;
        if (r2 == null) goto L12;
        if (TextUtils.equals(r2.getText(), r10.messageText) == false) goto L22;
    L12:
        if (this.hasReplyMessage == false) goto L14;
    L15:
        if (r35 == true) goto L22;
        if (r10.type == 21) goto L22;
        if (r10.forceUpdate == true) goto L22;
        return;
    L14:
        if (r10.replyMessageObject == null) goto L15;
    L22:
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L24;
    L26:
        this.botButtons.clear();
        this.botInlineButtons = null;
        this.accessibilityText = null;
        MessageObject r22 = this.currentMessageObject;
        int r15 = 0;
        if (r22 != null) goto L29;
    L32:
        boolean r16 = true;
    L33:
        if (r22 == null) goto L35;
        r10.playedGiftAnimation = r22.playedGiftAnimation;
    L35:
        this.currentMessageObject = r10;
        r10.forceUpdate = false;
        if (r10.replyMessageObject == null) goto L38;
        boolean r23 = true;
    L39:
        this.hasReplyMessage = r23;
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        this.previousWidth = 0;
        this.isSpoilerRevealing = false;
        TextLayout r24 = this.giftPremiumText;
        if (r24 == null) goto L43;
        if (r16 == false) goto L43;
        r24.detach();
        this.giftPremiumText = null;
        this.giftPremiumTextUncollapsed = false;
    L43:
        if (r16 == false) goto L45;
    L46:
        r10.reactionsChanged = false;
        TLRPC.Message r25 = r10.messageOwner;
        if (r25 == null) goto L53;
        TLRPC.TL_messageReactions r26 = r25.reactions;
        if (r26 == null) goto L53;
        if (r26.reactions_as_tags == false) goto L53;
        boolean r27 = true;
    L55:
        if (r10.shouldDrawReactions() == false) goto L57;
        this.reactionsLayoutInBubble.setMessage(r10, !r10.shouldDrawReactionsInLayout(), r27, this.themeDelegate);
    L58:
        int r28 = r10.type;
        SuggestBirthdayActionLayout r5 = this.birthdayLayout;
        if (r28 != 32) goto L65;
        if (r5 != null) goto L64;
        this.birthdayLayout = new SuggestBirthdayActionLayout(this.currentAccount, this, this.themeDelegate);
        if (isCellAttachedToWindow() == false) goto L64;
        this.birthdayLayout.attach();
    L64:
        this.birthdayLayout.set(r10);
    L67:
        this.starGiftLayout.set(r10, !r16);
        this.imageReceiver.setAutoRepeatCount(0);
        this.imageReceiver.clearDecorators();
        if (r10.type == 22) goto L71;
        this.wallpaperPreviewDrawable = null;
    L71:
        if (r10.actionDeleteGroupEventId == (-1)) goto L75;
        ScaleStateListAnimator.apply(this, 0.02f, 1.2f);
        this.overriddenMaxWidth = Math.max(AndroidUtilities.dp(250.0f), HintView2.cutInFancyHalf(r10.messageText, (TextPaint) getThemedPaint("paintChatActionText")));
        ProfileActivity.ShowDrawable r29 = ChannelAdminLogActivity.findDrawable(r10.messageText);
        if (r29 == null) goto L77;
        r29.setView(this);
    L77:
        if (r10.isStoryMention() == false) goto L85;
        TLRPC.User r1 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r10.messageOwner.media.user_id));
        this.avatarDrawable.setInfo(this.currentAccount, r1);
        TL_stories.StoryItem r210 = r10.messageOwner.media.storyItem;
        if (r210 != null) goto L81;
    L83:
        StoriesUtilities.setImage(this.imageReceiver, r210);
    L84:
        this.imageReceiver.setRoundRadius(ExteraConfig.getAvatarCorners(this.stickerSize * 0.7f, true));
    L399:
        if (this.firstInChat == true) goto L401;
    L420:
        TopicSeparator r12 = this.topicSeparator;
        if (r12 == null) goto L423;
        r12.detach();
        this.topicSeparator = null;
    L423:
        int r211 = 0;
        this.topicSeparatorTopPadding = 0;
    L424:
        int r13 = getPaddingTop();
        int r3 = this.topicSeparatorTopPadding;
        if (r13 == r3) goto L427;
        setPadding(r211, r3, r211, r211);
    L427:
        View r14 = this.rippleView;
        if (isButtonLayout(r34) == true) goto L430;
    L432:
        int r152 = 8;
    L433:
        r14.setVisibility(r152);
        ForumUtilities.applyTopicToMessage(r10);
        requestLayout();
        return;
    L430:
        if (this.starGiftLayout.has() == true) goto L432;
        r152 = r211;
        goto L433
    L401:
        if (this.isAllChats == false) goto L420;
        if (this.isSideMenued == false) goto L420;
        if (this.isForum == false) goto L407;
    L410:
        this.topicSeparatorTopPadding = AndroidUtilities.dp(33.0f);
        if (this.topicSeparator != null) goto L414;
        TopicSeparator r17 = new TopicSeparator(this.currentAccount, this, this.themeDelegate, true);
        this.topicSeparator = r17;
        r17.setOnClickListener(new ChatActionCell$$ExternalSyntheticLambda3(this));
    L414:
        if (this.topicSeparator.update(this.currentMessageObject) == true) goto L417;
        this.topicSeparator.detach();
        this.topicSeparator = null;
        r211 = 0;
        this.topicSeparatorTopPadding = 0;
        goto L424
    L417:
        if (this.attachedToWindow == false) goto L419;
        this.topicSeparator.attach();
    L419:
        r211 = 0;
        goto L424
    L407:
        if (this.isMonoForum == true) goto L410;
        if (this.isBotForum == false) goto L420;
    L81:
        if (r210.noforwards == false) goto L83;
        this.imageReceiver.setForUserOrChat(r1, this.avatarDrawable, null, true, 0, true);
        goto L84
    L85:
        int r212 = r10.type;
        if (r212 != 22) goto L132;
        if (r10.strippedThumb != null) goto L95;
        int r18 = r10.photoThumbs.size();
        int r213 = 0;
    L90:
        if (r213 >= r18) goto L95;
        if ((r10.photoThumbs.get(r213) instanceof TLRPC.TL_photoStrippedSize) == true) goto L95;
        r213 = r213 + 1;
    L95:
        TLRPC.TL_channelAdminLogEvent r19 = r10.currentEvent;
        if (r19 == null) goto L100;
        TLRPC.ChannelAdminLogEventAction r110 = r19.action;
        if ((r110 instanceof TLRPC.TL_channelAdminLogEventActionChangeWallpaper) == false) goto L100;
        TLRPC.WallPaper r111 = ((TLRPC.TL_channelAdminLogEventActionChangeWallpaper) r110).new_value;
    L107:
        if (TextUtils.isEmpty(ChatThemeController.getWallpaperEmoticon(r111)) == true) goto L116;
        Theme.ResourcesProvider r214 = this.themeDelegate;
        if (r214 == null) goto L111;
        boolean r215 = r214.isDark();
    L112:
        this.imageReceiver.clearImage();
        Drawable r112 = PreviewView.getBackgroundDrawableFromTheme(this.currentAccount, ChatThemeController.getWallpaperEmoticon(r111), r215, false);
        this.wallpaperPreviewDrawable = r112;
        if (r112 == null) goto L127;
        r112.setCallback(this);
    L127:
        this.imageReceiver.setRoundRadius(ExteraConfig.getAvatarCorners(this.stickerSize * 0.7f, true));
        float r113 = getUploadingInfoProgress(r34);
        RadialProgress2 r216 = this.radialProgress;
        if (r113 != 1.0f) goto L130;
        r216.setProgress(1.0f, !r16);
        this.radialProgress.setIcon(4, !r16, !r16);
        goto L399
    L130:
        r216.setIcon(3, !r16, !r16);
        goto L399
    L111:
        r215 = Theme.isCurrentThemeDark();
        goto L112
    L116:
        if (r111 == null) goto L120;
        String r32 = r111.uploadingImage;
        if (r32 == null) goto L120;
        this.imageReceiver.setImage(ImageLocation.getForPath(r32), "150_150_wallpaper" + r111.id + ChatBackgroundDrawable.hash(r111.settings), null, null, ChatBackgroundDrawable.createThumb(r111), 0, null, r111, 1);
        this.wallpaperPreviewDrawable = null;
    L120:
        if (r111 == null) goto L126;
        TLObject r33 = r10.photoThumbsObject;
        if ((r33 instanceof TLRPC.Document) == false) goto L124;
        TLRPC.Document r36 = (TLRPC.Document) r33;
    L125:
        this.imageReceiver.setImage(ImageLocation.getForDocument(r36), "150_150_wallpaper" + r111.id + ChatBackgroundDrawable.hash(r111.settings), null, null, ChatBackgroundDrawable.createThumb(r111), 0, null, r111, 1);
        this.wallpaperPreviewDrawable = null;
        goto L127
    L124:
        r36 = r111.document;
        goto L125
    L126:
        this.wallpaperPreviewDrawable = null;
    L100:
        TLRPC.Message r114 = r10.messageOwner;
        if (r114 == null) goto L105;
        TLRPC.MessageAction r115 = r114.action;
        if (r115 == null) goto L105;
        r111 = r115.wallpaper;
    L105:
        r111 = null;
        goto L107
    L132:
        if (r212 != 21) goto L163;
        this.imageReceiver.setRoundRadius(ExteraConfig.getAvatarCorners(this.stickerSize * 0.7f, true));
        this.imageReceiver.setAllowStartLottieAnimation(true);
        this.imageReceiver.setDelegate(null);
        TLRPC.TL_messageActionSuggestProfilePhoto r116 = (TLRPC.TL_messageActionSuggestProfilePhoto) r10.messageOwner.action;
        TLRPC.VideoSize r217 = FileLoader.getClosestVideoSizeWithSize(r116.photo.video_sizes, 1000);
        ArrayList<TLRPC.VideoSize> r37 = r116.photo.video_sizes;
        if (r37 != null) goto L136;
    L138:
        ImageLocation r117 = null;
    L139:
        TLRPC.Photo r38 = r10.messageOwner.action.photo;
        if (r10.strippedThumb != null) goto L147;
        int r6 = r10.photoThumbs.size();
        int r11 = 0;
    L142:
        if (r11 >= r6) goto L147;
        TLRPC.PhotoSize r122 = r10.photoThumbs.get(r11);
        if ((r122 instanceof TLRPC.TL_photoStrippedSize) == true) goto L148;
        r11 = r11 + 1;
    L148:
        TLRPC.PhotoSize r52 = FileLoader.getClosestPhotoSizeWithSize(r10.photoThumbs, 1000);
        if (r52 == null) goto L154;
        ImageLocation r218 = r117;
        ImageReceiver r118 = this.imageReceiver;
        if (r217 == null) goto L153;
        float r142 = 1.0f;
        r118.setImage(r218, ImageLoader.AUTOPLAY_FILTER, ImageLocation.getForPhoto(r52, r38), "150_150", ImageLocation.getForObject(r122, r10.photoThumbsObject), "50_50_b", r10.strippedThumb, 0, null, r34, 0);
        r10 = r34;
    L155:
        this.imageReceiver.setAllowStartLottieAnimation(false);
        ImageUpdater r119 = MessagesController.getInstance(this.currentAccount).photoSuggestion.get(r10.messageOwner.local_id);
        if (r119 != null) goto L158;
    L161:
        this.radialProgress.setProgress(r142, !r16);
        this.radialProgress.setIcon(4, !r16, !r16);
        goto L399
    L158:
        if (r119.getCurrentImageProgress() == r142) goto L161;
        this.radialProgress.setIcon(3, !r16, !r16);
        goto L399
    L153:
        r142 = 1.0f;
        r118.setImage(ImageLocation.getForPhoto(r52, r38), "150_150", ImageLocation.getForObject(r122, r10.photoThumbsObject), "50_50_b", r10.strippedThumb, 0, null, r10, 0);
        goto L155
    L154:
        r142 = 1.0f;
    L147:
        r122 = null;
        goto L148
    L136:
        if (r37.isEmpty() == true) goto L138;
        r117 = ImageLocation.getForPhoto(r217, r116.photo);
        goto L139
    L163:
        if (r212 == 31) goto L208;
        if (r212 == 33) goto L208;
        if (r212 == 30) goto L208;
        if (r212 == 18) goto L208;
        if (r212 == 25) goto L208;
        if (r212 == 35) goto L208;
        if (r212 != 37) goto L174;
        TLRPC.Chat r120 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(((TLRPC.TL_messageActionChangeCommunity) r10.messageOwner.action).community_id));
        this.imageReceiver.setAllowStartLottieAnimation(true);
        this.imageReceiver.setDelegate(null);
        this.imageReceiver.setRoundRadius(ExteraConfig.getAvatarCorners(52.0f, false, AvatarCornerType.COMMUNITY));
        this.imageReceiver.setAutoRepeatCount(1);
        this.avatarDrawable.setInfo(r120);
        this.imageReceiver.setForUserOrChat(r120, new CommunityAvatarDrawable(getContext(), AndroidUtilities.dp(14.0f)), r120);
        goto L399
    L174:
        ImageReceiver r121 = this.imageReceiver;
        if (r212 != 11) goto L207;
        r121.setAllowStartLottieAnimation(true);
        this.imageReceiver.setDelegate(null);
        this.imageReceiver.setRoundRadius(ExteraConfig.getAvatarCorners(AndroidUtilities.roundMessageSize, true));
        this.imageReceiver.setAutoRepeatCount(1);
        this.avatarDrawable.setInfo(r10.getDialogId(), null, null);
        if ((r10.messageOwner.action instanceof TLRPC.TL_messageActionUserUpdatedPhoto) == false) goto L180;
        this.imageReceiver.setImage(null, null, this.avatarDrawable, null, r10, 0);
    L206:
        this.imageReceiver.setVisible(!PhotoViewer.isShowingImage(r10), false);
        goto L399
    L180:
        if (r10.strippedThumb != null) goto L187;
        int r123 = r10.photoThumbs.size();
        int r219 = 0;
    L182:
        if (r219 >= r123) goto L187;
        TLRPC.PhotoSize r39 = r10.photoThumbs.get(r219);
        if ((r39 instanceof TLRPC.TL_photoStrippedSize) == true) goto L188;
        r219 = r219 + 1;
    L188:
        TLRPC.PhotoSize r124 = FileLoader.getClosestPhotoSizeWithSize(r10.photoThumbs, 640);
        if (r124 == null) goto L205;
        TLRPC.Photo r220 = r10.messageOwner.action.photo;
        if (r220.video_sizes.isEmpty() == false) goto L193;
    L199:
        TLRPC.PhotoSize r4 = r124;
        TLRPC.VideoSize r53 = null;
    L201:
        ImageReceiver r125 = this.imageReceiver;
        if (r53 == null) goto L204;
        r125.setImage(ImageLocation.getForPhoto(r53, r220), ImageLoader.AUTOPLAY_FILTER, ImageLocation.getForObject(r39, r10.photoThumbsObject), "50_50_b", r10.strippedThumb, 0, null, r10, 1);
        goto L206
    L204:
        r125.setImage(ImageLocation.getForObject(r4, r10.photoThumbsObject), "150_150", ImageLocation.getForObject(r39, r10.photoThumbsObject), "50_50_b", r10.strippedThumb, 0, null, r10, 1);
        goto L206
    L193:
        if (SharedConfig.isAutoplayGifs() == false) goto L199;
        r53 = FileLoader.getClosestVideoSizeWithSize(r220.video_sizes, 1000);
        if (r10.mediaExists == false) goto L197;
    L200:
        r4 = r124;
        goto L201
    L197:
        if (DownloadController.getInstance(this.currentAccount).canDownloadMedia(4, r53.size) == true) goto L200;
        this.currentVideoLocation = ImageLocation.getForPhoto(r53, r220);
        DownloadController.getInstance(this.currentAccount).addLoadingFileObserver(FileLoader.getAttachFileName(r53), r10, this);
        goto L199
    L205:
        this.imageReceiver.setImageBitmap(this.avatarDrawable);
    L187:
        r39 = null;
        goto L188
    L207:
        r121.setAllowStartLottieAnimation(true);
        this.imageReceiver.setDelegate(null);
        this.imageReceiver.setImageBitmap(null);
    L208:
        this.imageReceiver.setRoundRadius(0);
        TLRPC.MessageAction r221 = r10.messageOwner.action;
        if ((r221 instanceof TLRPC.TL_messageActionNoForwardsRequest) == false) goto L228;
        TLRPC.TL_messageActionNoForwardsRequest r222 = (TLRPC.TL_messageActionNoForwardsRequest) r221;
        long r42 = MessagesController.getInstance(this.currentAccount).config.noForwardsRequestExpirePeriod.get(TimeUnit.SECONDS);
        if (r222.expired == false) goto L213;
    L216:
        boolean r43 = true;
    L217:
        this.offerExpired = r43;
        if (r10.isOut() == false) goto L220;
    L224:
        TLRPC.Document r223 = null;
    L225:
        String r310 = null;
        boolean r44 = false;
    L226:
        MessageObject r31 = null;
        boolean r45 = r44;
    L351:
        BotInlineKeyboard.Source r54 = this.botInlineButtons;
        if (r54 == null) goto L371;
        int r55 = r54.getRowsCount();
        int r62 = 0;
    L354:
        if (r62 >= r55) goto L371;
        int r7 = this.botInlineButtons.getColumnsCount(r62);
        int r8 = 0;
    L356:
        if (r8 >= r7) goto L370;
        BotInlineKeyboard.Button r9 = this.botInlineButtons.getButton(r62, r8);
        BotButton r1110 = new BotButton(new ChatActionCell$$ExternalSyntheticLambda2(this));
        r1110.buttonCustom = (BotInlineKeyboard.ButtonCustom) r9;
        int r126 = r9.getIconRes();
        if (r126 == 0) goto L360;
        Drawable r127 = getResources().getDrawable(r126);
        r1110.iconDrawable = r127;
        r127.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
    L360:
        r1110.height = AndroidUtilities.dp(40.0f);
        int r128 = r1110.positionFlags | 8;
        r1110.positionFlags = r128;
        if (r8 != 0) goto L364;
        boolean r132 = true;
    L365:
        int r129 = BitwiseUtils.setFlag(r128, 1, r132);
        r1110.positionFlags = r129;
        if (r8 != 1) goto L368;
        boolean r133 = true;
    L369:
        r1110.positionFlags = BitwiseUtils.setFlag(r129, 2, r133);
        r1110.title = new Text(r9.getText(), (TextPaint) getThemedPaint("paintChatBotButton"));
        this.botButtons.add(r1110);
        r8 = r8 + 1;
        goto L356
    L368:
        r133 = false;
        goto L369
    L364:
        r132 = false;
        goto L365
    L370:
        r62 = r62 + 1;
    L371:
        this.forceWasUnread = r10.wasUnread;
        this.giftSticker = r223;
        if (r223 == null) goto L391;
        this.imageReceiver.setAllowStartLottieAnimation(true);
        int r311 = r10.type;
        if (r311 != 31) goto L376;
    L380:
        this.giftEffectAnimation = null;
        int r312 = 0;
    L382:
        if (r312 >= r223.video_thumbs.size()) goto L387;
        if ("f".equals(r223.video_thumbs.get(r312).type) == true) goto L385;
        r312 = r312 + 1;
        goto L382
    L385:
        this.giftEffectAnimation = r223.video_thumbs.get(r312);
    L387:
        if (r16 == false) goto L389;
    L390:
        SvgHelper.SvgDrawable r292 = DocumentObject.getSvgThumb(r223, Theme.key_windowBackgroundGray, 0.3f);
        this.imageReceiver.setAutoRepeat(0);
        this.imageReceiver.setImage(ImageLocation.getForDocument(r223), String.format(Locale.US, "%d_%d_nr_messageId=%d", new Object[]{160, 160, Integer.valueOf(r10.stableId)}), r292, "tgs", r31, 1);
        goto L399
    L389:
        if (r10.type == 18) goto L399;
    L376:
        if (r311 == 37) goto L380;
        if (r311 == 33) goto L380;
        this.imageReceiver.setDelegate(this.giftStickerDelegate);
        goto L380
    L391:
        if (r310 == null) goto L399;
        MediaDataController r130 = MediaDataController.getInstance(this.currentAccount);
        if (r45 == true) goto L396;
        boolean r46 = true;
    L397:
        r130.loadStickersByEmojiOrName(r310, false, r46);
        goto L399
    L396:
        r46 = false;
        goto L397
    L220:
        if (r222.expired == true) goto L224;
        if (this.offerExpired == true) goto L224;
        BotInlineKeyboard.Builder r224 = new BotInlineKeyboard.Builder();
        r224.addSharingOfferKeyboard();
        this.botInlineButtons = r224.build();
        goto L224
    L213:
        if ((r10.messageOwner.date + r42) < ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L216;
        r43 = false;
        goto L217
    L228:
        if ((r221 instanceof TLRPC.TL_messageActionStarGiftPurchaseOffer) == false) goto L251;
        TLRPC.TL_messageActionStarGiftPurchaseOffer r225 = (TLRPC.TL_messageActionStarGiftPurchaseOffer) r221;
        TL_stars.StarGift r47 = r225.gift;
        if (r47 == null) goto L235;
        TLRPC.Document r72 = TlUtils.getGiftDocument(r47);
        if (this.cardBackground != null) goto L234;
        this.cardBackground = new GiftSheet.CardBackground(this, this.themeDelegate, false);
    L234:
        this.cardBackground.setBackdrop((TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r47.attributes, TL_stars.starGiftAttributeBackdrop.class));
        this.cardBackground.setPattern((TL_stars.starGiftAttributePattern) StarsController.findAttribute(r47.attributes, TL_stars.starGiftAttributePattern.class));
    L237:
        if (r225.expires_at >= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L239;
        boolean r48 = true;
    L240:
        this.offerExpired = r48;
        if (r10.isOut() == false) goto L243;
    L249:
        r223 = r72;
        goto L225
    L243:
        if (r225.accepted == true) goto L249;
        if (r225.declined == true) goto L249;
        if (this.offerExpired == true) goto L249;
        BotInlineKeyboard.Builder r226 = new BotInlineKeyboard.Builder();
        r226.addGiftOfferKeyboard();
        this.botInlineButtons = r226.build();
        goto L249
    L239:
        r48 = false;
        goto L240
    L235:
        r72 = null;
        goto L237
    L251:
        if ((r221 instanceof TLRPC.TL_messageActionSetChatTheme) == false) goto L261;
        TL_stars.StarGift r227 = ((TLRPC.TL_chatThemeUniqueGift) ((TLRPC.TL_messageActionSetChatTheme) r221).theme).gift;
        if (r227 == null) goto L258;
        TLRPC.Document r49 = TlUtils.getGiftDocument(r227);
        if (this.cardBackground != null) goto L257;
        this.cardBackground = new GiftSheet.CardBackground(this, this.themeDelegate, false);
    L257:
        this.cardBackground.setBackdrop((TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r227.attributes, TL_stars.starGiftAttributeBackdrop.class));
        this.cardBackground.setPattern((TL_stars.starGiftAttributePattern) StarsController.findAttribute(r227.attributes, TL_stars.starGiftAttributePattern.class));
    L259:
        r223 = r49;
        goto L225
    L258:
        r49 = null;
        goto L259
    L261:
        if ((r221 instanceof TLRPC.TL_messageActionStarGift) == false) goto L268;
        TL_stars.StarGift r228 = ((TLRPC.TL_messageActionStarGift) r221).gift;
        if (r228 == null) goto L265;
        r223 = r228.sticker;
    L266:
        r31 = r10;
        r310 = null;
        r45 = false;
    L265:
        r223 = null;
        goto L266
    L268:
        if ((r221 instanceof TLRPC.TL_messageActionStarGiftUnique) == true) goto L270;
    L274:
        boolean r229 = r221 instanceof TLRPC.TL_messageActionGiftTon;
        int r410 = this.currentAccount;
        if (r229 == false) goto L280;
        String r230 = UserConfig.getInstance(r410).premiumTonStickerPack;
        if (r230 != null) goto L284;
        MediaDataController.getInstance(this.currentAccount).checkTonGiftStickers();
        return;
    L284:
        TLRPC.TL_messages_stickerSet r411 = MediaDataController.getInstance(this.currentAccount).getStickerSetByName(r230);
        ?? r412 = r411;
        if (r411 != null) goto L287;
        r412 = MediaDataController.getInstance(this.currentAccount).getStickerSetByEmojiOrName(r230);
    L287:
        if (r412 == 0) goto L350;
        TLRPC.MessageAction r56 = r10.messageOwner.action;
        int r1111 = r56.months;
        if (r10.type == 30) goto L291;
        String r57 = monthsToEmoticon.get(Integer.valueOf(r1111));
        ArrayList<TLRPC.TL_stickerPack> r73 = r412.packs;
        int r1112 = r73.size();
        int r134 = 0;
        TLRPC.Document r1210 = null;
    L324:
        if (r134 >= r1112) goto L343;
        TLRPC.TL_stickerPack r143 = r73.get(r134);
        r134 = r134 + 1;
        TLRPC.TL_stickerPack r144 = r143;
        if (Objects.equals(r144.emoticon, r57) == false) goto L339;
        ArrayList<Long> r63 = r144.documents;
        int r145 = r63.size();
        int r82 = r15;
    L328:
        if (r82 >= r145) goto L339;
        Long r21 = r63.get(r82);
        r82 = r82 + 1;
        long r2110 = r21.longValue();
        ArrayList<TLRPC.Document> r313 = r412.documents;
        int r92 = r313.size();
    L330:
        if (r15 >= r92) goto L335;
        TLRPC.Document r262 = r313.get(r15);
        r15 = r15 + 1;
        String r272 = r230;
        TLRPC.Document r231 = r262;
        String r263 = r57;
        ArrayList<Long> r282 = r63;
        if (r231.id == r2110) goto L333;
        r57 = r263;
        r230 = r272;
        r63 = r282;
        goto L330
    L333:
        r1210 = r231;
    L336:
        if (r1210 != null) goto L340;
        r57 = r263;
        r230 = r272;
        r63 = r282;
        r15 = 0;
    L340:
        if (r1210 != null) goto L344;
        r57 = r263;
        r230 = r272;
        r15 = 0;
    L344:
        if (r1210 == null) goto L346;
    L349:
        r31 = r412;
        r223 = r1210;
    L348:
        r310 = r272;
        r45 = r412;
        goto L351
    L346:
        if (r412.documents.isEmpty() == true) goto L349;
        r223 = r412.documents.get(0);
        r31 = r412;
        goto L348
    L335:
        r272 = r230;
        r263 = r57;
        r282 = r63;
    L339:
        r272 = r230;
        r263 = r57;
        goto L340
    L343:
        r272 = r230;
        goto L344
    L291:
        if ((r56 instanceof TLRPC.TL_messageActionGiftTon) == false) goto L294;
        String r58 = StarsIntroActivity.getTonGiftEmoji(r56.cryptoAmount);
    L304:
        int r74 = 0;
    L306:
        if (r74 >= r412.packs.size()) goto L321;
        TLRPC.TL_stickerPack r1113 = r412.packs.get(r74);
        if (TextUtils.equals(r1113.emoticon, r58) == false) goto L320;
        if (r1113.documents.isEmpty() == true) goto L320;
        long r1114 = r1113.documents.get(0).longValue();
        int r59 = 0;
    L313:
        if (r59 >= r412.documents.size()) goto L321;
        TLRPC.Document r75 = r412.documents.get(r59);
        if (r75 == null) goto L319;
        if (r75.id != r1114) goto L319;
    L322:
        r272 = r230;
        r1210 = r75;
    L319:
        r59 = r59 + 1;
    L320:
        r74 = r74 + 1;
    L321:
        r75 = null;
        goto L322
    L294:
        if ((r56 instanceof TLRPC.TL_messageActionGiftStars) == false) goto L296;
        long r1115 = ((TLRPC.TL_messageActionGiftStars) r56).stars;
    L298:
        if (r1115 > 1000) goto L301;
        r58 = "2⃣";
        goto L304
    L301:
        if (r1115 >= 2500) goto L303;
        r58 = "3⃣";
        goto L304
    L303:
        r58 = "4⃣";
        goto L304
    L296:
        r1115 = ((TLRPC.TL_messageActionPrizeStars) r56).stars;
        goto L298
    L350:
        r310 = r230;
        r223 = null;
        r44 = r412;
        goto L226
    L280:
        r230 = UserConfig.getInstance(r410).premiumGiftsStickerPack;
        if (r230 != null) goto L284;
        MediaDataController.getInstance(this.currentAccount).checkPremiumGiftStickers();
        return;
    L270:
        if (((TLRPC.TL_messageActionStarGiftUnique) r221).refunded == false) goto L274;
        TL_stars.StarGift r232 = ((TLRPC.TL_messageActionStarGiftUnique) r221).gift;
        if (r232 == null) goto L265;
        r223 = r232.getDocument();
        goto L266
    L75:
        ScaleStateListAnimator.reset(this);
        this.overriddenMaxWidth = 0;
        goto L77
    L65:
        if (r5 == null) goto L67;
        r5.detach();
        this.birthdayLayout = null;
        goto L67
    L57:
        this.reactionsLayoutInBubble.setMessage(null, false, false, this.themeDelegate);
    L53:
        r27 = false;
        goto L55
    L45:
        if (r10.reactionsChanged == false) goto L58;
    L38:
        r23 = false;
        goto L39
    L29:
        if (r22.stableId != r10.stableId) goto L32;
        r16 = false;
        goto L33
    L24:
        if (Thread.currentThread() == ApplicationLoader.applicationHandler.getLooper().getThread()) goto L26;
        FileLog.e(new IllegalStateException("Wrong thread!!!"));
        goto L26
    }

    public void setOnActionClickListener(View.OnClickListener r1) {
        this.onActionClick = r1;
    }

    public void setOnActionLongClickListener(View.OnLongClickListener r1) {
        this.onActionLongClick = r1;
    }

    public void setOverrideColor(int r1, int r2) {
        this.overrideBackground = r1;
        this.overrideText = r2;
    }

    public void setOverrideTextMaxWidth(int r1) {
        this.overriddenMaxWidth = r1;
    }

    public void setScrimReaction(Integer r1) {
        this.reactionsLayoutInBubble.setScrimReaction(r1);
    }

    public void setShowTopic(boolean r2) {
        if (this.showTopicSeparator == r2) goto L6;
        this.showTopicSeparator = r2;
        invalidateOutbounds();
        invalidate();
        return;
    }

    public void setSpoilersSuppressed(boolean r2) {
        Iterator<SpoilerEffect> r1 = this.spoilers.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().setSuppressUpdates(r2);
        goto L4
    }

    public void setVisiblePart(float r2, float r3, int r4, float r5) {
        this.visiblePartSet = true;
        this.backgroundHeight = r4;
        this.viewTop = r2;
        this.viewTranslationX = r3;
        this.dimAmount = r5;
        this.dimPaint.setColor(ColorUtils.setAlphaComponent(-16777216, (int) (r5 * 255.0f)));
        invalidate();
    }

    public void setVisiblePart(float r2, int r3) {
        this.visiblePartSet = true;
        this.backgroundHeight = r3;
        this.viewTop = r2;
        this.viewTranslationX = 0.0f;
    }

    public boolean showingCancelButton() {
        RadialProgress2 r1 = this.radialProgress;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.getIcon() != 3) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.wallpaperPreviewDrawable) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
