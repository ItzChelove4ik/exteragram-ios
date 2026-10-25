package org.telegram.messenger;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.URLSpan;
import android.util.Base64;
import android.view.View;
import androidx.collection.LongSparseArray;
import androidx.core.graphics.ColorUtils;
import com.android.dx.AppDataDirGuesser;
import com.android.dx.io.Opcodes;
import com.android.dx.rop.code.RegisterSpec;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.chats.WidePosts;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.google.android.gms.cast.HlsSegmentFormat;
import j$.net.URLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import kotlin.time.DurationKt;
import me.vkryl.core.BitwiseUtils;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.BotInlineKeyboard;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.ringtone.RingtoneDataStore;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.messenger.utils.tlutils.TLKeyboardHelper;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Business.QuickRepliesController;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.EllipsizeSpanAnimator;
import org.telegram.ui.Components.FormattedDateSpan;
import org.telegram.ui.Components.Forum.ForumBubbleDrawable;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.Reactions.ReactionsUtils;
import org.telegram.ui.Components.SquigglyLinesSpan;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.TranscribeButton;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.URLSpanBotCommand;
import org.telegram.ui.Components.URLSpanBrowser;
import org.telegram.ui.Components.URLSpanMono;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.URLSpanNoUnderlineBold;
import org.telegram.ui.Components.URLSpanReplacement;
import org.telegram.ui.Components.URLSpanUserMention;
import org.telegram.ui.Components.VideoPlayer;
import org.telegram.ui.Components.WebPlayerView;
import org.telegram.ui.Components.poll.PollAttachedMediaPack;
import org.telegram.ui.Components.spoilers.SpoilerEffect;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.MultiLayoutTypingAnimator;
import org.telegram.ui.PeerColorActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.community.CommunityUtils;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes.dex */
public class MessageObject {
    public static final int ENTITIES_ALL = 0;
    public static final int ENTITIES_ONLY_HASHTAGS = 1;
    private static final int LINES_PER_BLOCK = 10;
    private static final int LINES_PER_BLOCK_WITH_EMOJI = 5;
    private static final int MESSAGE_ID_EPHEMERAL_BITS_MASK = 1610612736;
    private static final int MESSAGE_ID_RESERVED_BITS_MASK = 1879048192;
    public static final int MESSAGE_SEND_STATE_EDITING = 3;
    public static final int MESSAGE_SEND_STATE_SENDING = 1;
    public static final int MESSAGE_SEND_STATE_SEND_ERROR = 2;
    public static final int MESSAGE_SEND_STATE_SENT = 0;
    public static final int POSITION_FLAG_BOTTOM = 8;
    public static final int POSITION_FLAG_LEFT = 1;
    public static final int POSITION_FLAG_RIGHT = 2;
    public static final int POSITION_FLAG_TOP = 4;
    public static final int SUGGESTION_FLAG_EDIT_MEDIA = 8;
    public static final int SUGGESTION_FLAG_EDIT_PRCIE = 1;
    public static final int SUGGESTION_FLAG_EDIT_TEXT = 4;
    public static final int SUGGESTION_FLAG_EDIT_TIME = 2;
    public static final int TYPE_ACTION_PHOTO = 11;
    public static final int TYPE_ACTION_WALLPAPER = 22;
    public static final int TYPE_ANIMATED_STICKER = 15;
    public static final int TYPE_ARTICLE = 36;
    public static final int TYPE_COMMUNITY_CHANGED = 37;
    public static final int TYPE_CONTACT = 12;
    public static final int TYPE_DATE = 10;
    public static final int TYPE_EMOJIS = 19;
    public static final int TYPE_EXTENDED_MEDIA_PREVIEW = 20;
    public static final int TYPE_FILE = 9;
    public static final int TYPE_GEO = 4;
    public static final int TYPE_GIF = 8;
    public static final int TYPE_GIFT_OFFER = 33;
    public static final int TYPE_GIFT_OFFER_REJECTED = 34;
    public static final int TYPE_GIFT_PREMIUM = 18;
    public static final int TYPE_GIFT_PREMIUM_CHANNEL = 25;
    public static final int TYPE_GIFT_STARS = 30;
    public static final int TYPE_GIFT_THEME_UPDATE = 31;
    public static final int TYPE_GIVEAWAY = 26;
    public static final int TYPE_GIVEAWAY_RESULTS = 28;
    public static final int TYPE_JOINED_CHANNEL = 27;
    public static final int TYPE_LOADING = 6;
    public static final int TYPE_MUSIC = 14;
    public static final int TYPE_PAID_MEDIA = 29;
    public static final int TYPE_PHONE_CALL = 16;
    public static final int TYPE_PHOTO = 1;
    public static final int TYPE_POLL = 17;
    public static final int TYPE_ROUND_VIDEO = 5;
    public static final int TYPE_SHARING_OFFER = 35;
    public static final int TYPE_STICKER = 13;
    public static final int TYPE_STORY = 23;
    public static final int TYPE_STORY_MENTION = 24;
    public static final int TYPE_SUGGEST_BIRTHDAY = 32;
    public static final int TYPE_SUGGEST_PHOTO = 21;
    public static final int TYPE_TEXT = 0;
    public static final int TYPE_UNSUPPORTED = 38;
    public static final int TYPE_VIDEO = 3;
    public static final int TYPE_VOICE = 2;
    private static CharSequence channelSpan;
    static final String[] excludeWords = null;
    private static CharSequence groupSpan;
    public static Pattern instagramUrlPattern;
    private static Pattern loginCodePattern;
    public static Pattern urlPattern;
    private static CharSequence[] userSpan;
    public static Pattern videoTimeUrlPattern;
    public long actionDeleteGroupEventId;
    public boolean animateComments;
    public int animatedEmojiCount;
    public boolean attachPathExists;
    public double attributeDuration;
    public Bitmap audioCover;
    public int audioPlayerDuration;
    public float audioProgress;
    public int audioProgressMs;
    public int audioProgressSec;
    public StringBuilder botButtonsLayout;
    public float bufferedProgress;
    public boolean business;
    private Integer cachedApproximateHeight;
    public Boolean cachedIsSupergroup;
    public VideoPlayer.VideoUri cachedQuality;
    public Float cachedSavedTimestamp;
    private Integer cachedStartsTimestamp;
    private Integer cachedTextHeight;
    public boolean cancelEditing;
    public CharSequence caption;
    private boolean captionSummarized;
    private boolean captionTranslated;
    private boolean channelJoined;
    public boolean channelJoinedExpanded;
    public ArrayList<TLRPC.PollAnswer> checkedVotes;
    public int contentType;
    public int currentAccount;
    public TLRPC.TL_channelAdminLogEvent currentEvent;
    public Drawable customAvatarDrawable;
    public String customName;
    public String customReplyName;
    public String dateKey;
    public int dateKeyInt;
    public boolean deleted;
    public boolean deletedByThanos;
    public TLRPC.Document documentToPollAddOption;
    public boolean drawServiceWithDefaultTypeface;
    public CharSequence editingMessage;
    public ArrayList<TLRPC.MessageEntity> editingMessageEntities;
    public boolean editingMessageSearchWebPage;
    public TLRPC.Document emojiAnimatedSticker;
    public String emojiAnimatedStickerColor;
    public Long emojiAnimatedStickerId;
    private boolean emojiAnimatedStickerLoading;
    public TLRPC.VideoSize emojiMarkup;
    public int emojiOnlyCount;
    public long eventId;
    public boolean expandedExplanation;
    public HashSet<Integer> expandedQuotes;
    public long extendedMediaLastCheckTime;
    public boolean factCheckExpanded;
    private CharSequence factCheckText;
    public boolean flickerLoading;
    public boolean forceAvatar;
    public boolean forceExpired;
    public boolean forcePlayEffect;
    public float forceSeekTo;
    public boolean forceShowPollResults;
    public boolean forceUpdate;
    private float generatedWithDensity;
    private float generatedWithFontSize;
    private int generatedWithMinSize;
    public float gifState;
    public boolean hadAnimationNotReadyLoading;
    public boolean hasCode;
    public boolean hasCodeAtBottom;
    public boolean hasCodeAtTop;
    public boolean hasQuote;
    public boolean hasQuoteAtBottom;
    public boolean hasRtl;
    public boolean hasSingleCode;
    public boolean hasSingleQuote;
    private boolean hasUnwrappedEmoji;
    public boolean hasWideCode;
    public boolean hideSendersName;
    public VideoPlayer.VideoUri highestQuality;
    public ArrayList<String> highlightedWords;
    private BotInlineKeyboard.Source inlineKeyboardSource;
    public boolean isBotPendingDraft;
    public boolean isDateObject;
    public boolean isDownloadingFile;
    private Boolean isEmbedVideoCached;
    public boolean isLiveStoryPush;
    public boolean isMediaSpoilersRevealed;
    public boolean isMediaSpoilersRevealedInSharedMedia;
    public boolean isOauthPush;
    public Boolean isOutOwnerCached;
    public boolean isPlayingExplanationObject;
    public boolean isPrimaryGroupMessage;
    public boolean isReactionPush;
    public boolean isRepostPreview;
    public boolean isRepostVideoPreview;
    public boolean isRestrictedMessage;
    private int isRoundVideoCached;
    public boolean isSaved;
    public boolean isSavedFiltered;
    public Boolean isSensitiveCached;
    public boolean isSpoilersRevealed;
    public boolean isStoryMentionPush;
    public boolean isStoryPush;
    public boolean isStoryPushHidden;
    public boolean isStoryReactionPush;
    public boolean isTopicMainMessage;
    public boolean isVideoConversionObject;
    public Object lastGeoWebFileLoaded;
    public Object lastGeoWebFileSet;
    public int lastLineWidth;
    private boolean layoutCreated;
    public CharSequence linkDescription;
    public long loadedFileSize;
    public boolean loadingCancelled;
    public boolean localChannel;
    public boolean localEdit;
    public long localGroupId;
    public String localName;
    public long localSentGroupId;
    public boolean localSupergroup;
    public int localType;
    public String localUserName;
    public boolean mediaExists;
    public ImageLocation mediaSmallThumb;
    public ImageLocation mediaThumb;
    public TLRPC.Message messageOwner;
    public CharSequence messageText;
    public CharSequence messageTextForReply;
    public CharSequence messageTextShort;
    public CharSequence messageTrimmedToHighlight;
    public boolean messageTrimmedToHighlightCut;
    public String monthKey;
    public boolean notime;
    public boolean openedInViewer;
    public int overrideLinkColor;
    public long overrideLinkEmoji;
    public TLRPC.TL_peerColorCollectible overrideLinkPeerColor;
    public StoriesController.StoriesList parentStoriesList;
    public int parentWidth;
    public SvgHelper.SvgDrawable pathThumb;
    public ArrayList<TLRPC.PhotoSize> photoThumbs;
    public ArrayList<TLRPC.PhotoSize> photoThumbs2;
    public TLObject photoThumbsObject;
    public TLObject photoThumbsObject2;
    public boolean playedGiftAnimation;
    public long pollLastCheckTime;
    public ArrayList<Integer> pollMediaMapping;
    public boolean pollVisibleOnScreen;
    public boolean preview;
    public boolean previewForward;
    public String previousAttachPath;
    public TLRPC.MessageMedia previousMedia;
    public String previousMessage;
    public ArrayList<TLRPC.MessageEntity> previousMessageEntities;
    public boolean putInDownloadsStore;
    public TLRPC.Document qualityToSave;
    public String quick_reply_shortcut;
    public CharSequence quizExplanation;
    private byte[] randomWaveform;
    public boolean reactionsChanged;
    public long reactionsLastCheckTime;
    public int realDate;
    public MessageObject replyMessageObject;
    public boolean replyTextEllipsized;
    public boolean replyTextRevealed;
    public TLRPC.TL_forumTopic replyToForumTopic;
    public boolean resendAsIs;
    public boolean revealingMediaSpoilers;
    public boolean richCheckboxEcho;
    public RichMessageLayout richLayout;
    public int richMessageMediaType;
    public boolean scheduled;
    public boolean scheduledSent;
    public int searchType;
    private CharSequence secretOnceSpan;
    private CharSequence secretPlaySpan;
    public SendAnimationData sendAnimationData;
    public TLRPC.Peer sendAsPeer;
    public boolean sendPreview;
    public MediaController.PhotoEntry sendPreviewEntry;
    public boolean sentHighQuality;
    public boolean settingAvatar;
    public boolean shouldRemoveVideoEditedInfo;
    public boolean sideMenuEnabled;
    private boolean spoiledLoginCode;
    public String sponsoredAdditionalInfo;
    public String sponsoredButtonText;
    public boolean sponsoredCanReport;
    public TLRPC.PeerColor sponsoredColor;
    public byte[] sponsoredId;
    public String sponsoredInfo;
    public TLRPC.MessageMedia sponsoredMedia;
    public TLRPC.Photo sponsoredPhoto;
    public boolean sponsoredRecommended;
    public String sponsoredTitle;
    public String sponsoredUrl;
    public int stableId;
    public TL_stories.StoryItem storyItem;
    private TLRPC.WebPage storyMentionWebpage;
    public BitmapDrawable strippedThumb;
    public boolean summarized;
    public ArrayList<TextLayoutBlock> textLayoutBlocks;
    public int textWidth;
    public float textXOffset;
    public VideoPlayer.VideoUri thumbQuality;
    public Drawable[] topicIconDrawable;
    public int totalAnimatedEmojiCount;
    public boolean translated;
    public int type;
    public StoriesController.UploadingStory uploadingStory;
    public boolean useCustomPhoto;
    public CharSequence vCardData;
    public VideoEditedInfo videoEditedInfo;
    public ArrayList<VideoPlayer.Quality> videoQualities;
    private Boolean videoQualitiesCached;
    public boolean viewsReloaded;
    public int wantedBotKeyboardWidth;
    public boolean wasJustSent;
    public boolean wasUnread;
    public ArrayList<TLRPC.MessageEntity> webPageDescriptionEntities;
    private int widePostsTextWidth;
    public CharSequence youtubeDescription;

    /* renamed from: org.telegram.messenger.MessageObject$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 extends CharacterStyle {
        final /* synthetic */ MessageObject this$0;

        public AnonymousClass1(MessageObject r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r2) {
            r2.setTextSize(r2.getTextSize() * 0.8f);
            r2.setColor(Theme.chat_timePaint.getColor());
        }
    }

    /* renamed from: org.telegram.messenger.MessageObject$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass2 extends CharacterStyle {
        final /* synthetic */ MessageObject this$0;

        public AnonymousClass2(MessageObject r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setColor(((TextPaint) Theme.chat_msgTextPaint).linkColor);
        }
    }

    /* loaded from: classes5.dex */
    public static class GroupedMessagePosition {
        public float aspectRatio;
        public boolean edge;
        public int flags;
        public boolean last;
        public float left;
        public int leftSpanOffset;
        public byte maxX;
        public byte maxY;
        public byte minX;
        public byte minY;
        public float ph;
        public int photoHeight;
        public int photoWidth;
        public int pw;
        public float[] siblingHeights;
        public int spanSize;
        public float top;

        public GroupedMessagePosition() {
        }

        public void set(int r1, int r2, int r3, int r4, int r5, float r6, int r7) {
            this.minX = (byte) r1;
            this.maxX = (byte) r2;
            this.minY = (byte) r3;
            this.maxY = (byte) r4;
            this.pw = r5;
            this.spanSize = r5;
            this.ph = r6;
            this.flags = (byte) r7;
        }
    }

    public static class GroupedMessages {
        public static final int GROUPED_AVATAR_OFFSET = 108;
        public int cachedWidthForCaption;
        public boolean captionAbove;
        public MessageObject captionMessage;
        public long groupId;
        public boolean hasCaption;
        public boolean hasSibling;
        public boolean isDocuments;
        private int maxSizeWidth;
        public ArrayList<MessageObject> messages;
        public ArrayList<GroupedMessagePosition> posArray;
        public HashMap<MessageObject, GroupedMessagePosition> positions;
        public LongSparseArray<GroupedMessagePosition> positionsArray;
        public boolean reversed;
        public final TransitionParams transitionParams;

        /* loaded from: classes5.dex */
        public static class MessageGroupedLayoutAttempt {
            public float[] heights;
            public int[] lineCounts;

            public MessageGroupedLayoutAttempt(int r1, int r2, float r3, float r4) {
                this.lineCounts = new int[]{r1, r2};
                this.heights = new float[]{r3, r4};
            }

            public MessageGroupedLayoutAttempt(int r1, int r2, int r3, float r4, float r5, float r6) {
                this.lineCounts = new int[]{r1, r2, r3};
                this.heights = new float[]{r4, r5, r6};
            }

            public MessageGroupedLayoutAttempt(int r1, int r2, int r3, int r4, float r5, float r6, float r7, float r8) {
                this.lineCounts = new int[]{r1, r2, r3, r4};
                this.heights = new float[]{r5, r6, r7, r8};
            }
        }

        /* loaded from: classes5.dex */
        public static class TransitionParams {
            public boolean backgroundChangeBounds;
            public int bottom;
            public float captionEnterProgress;
            public ChatMessageCell cell;
            public boolean drawBackgroundForDeletedItems;
            public boolean drawCaptionLayout;
            public boolean isNewGroup;
            public int left;
            public float offsetBottom;
            public float offsetLeft;
            public float offsetRight;
            public float offsetTop;
            public boolean pinnedBotton;
            public boolean pinnedTop;
            public int right;
            public int top;

            public TransitionParams() {
                this.captionEnterProgress = 1.0f;
            }

            public void reset() {
                this.captionEnterProgress = 1.0f;
                this.offsetBottom = 0.0f;
                this.offsetTop = 0.0f;
                this.offsetRight = 0.0f;
                this.offsetLeft = 0.0f;
                this.backgroundChangeBounds = false;
            }
        }

        public GroupedMessages() {
            this.messages = new ArrayList();
            this.posArray = new ArrayList();
            this.positions = new HashMap();
            this.positionsArray = new LongSparseArray();
            this.cachedWidthForCaption = -1;
            this.maxSizeWidth = 800;
            this.transitionParams = new TransitionParams();
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void calculateInternal() {
            this.posArray.clear();
            this.positions.clear();
            this.positionsArray.clear();
            this.captionMessage = null;
            this.maxSizeWidth = 800;
            int r2 = this.messages.size();
            int r3 = 0;
            boolean r4 = 1;
            if (r2 != 1) goto L6;
            this.captionMessage = this.messages.get(0);
            return;
        L6:
            if (r2 < 1) goto L325;
            StringBuilder r5 = new StringBuilder();
            this.hasSibling = false;
            this.hasCaption = false;
            this.captionAbove = false;
            if (this.reversed == false) goto L11;
            int r6 = r2 - 1;
        L12:
            int r8 = 0;
            boolean r10 = false;
            boolean r11 = false;
            boolean r12 = true;
            float r9 = 1.0f;
        L14:
            if (this.reversed == false) goto L18;
            if (r6 < 0) goto L93;
        L19:
            MessageObject r13 = this.messages.get(r6);
            if (this.reversed == false) goto L22;
            int r14 = r2 - 1;
        L23:
            if (r6 != r14) goto L49;
            r13.isOutOwnerCached = null;
            r11 = r13.isOutOwner();
            if (r11 == true) goto L43;
            TLRPC.Message r82 = r13.messageOwner;
            TLRPC.MessageFwdHeader r142 = r82.fwd_from;
            if (r142 == null) goto L31;
            if (r142.saved_from_peer == null) goto L31;
        L42:
            r8 = r4;
        L45:
            if (r13.isMusic() == false) goto L47;
        L48:
            this.isDocuments = r4;
            goto L49
        L47:
            if (r13.isDocument() == false) goto L49;
        L31:
            if ((r82.from_id instanceof TLRPC.TL_peerUser) == false) goto L41;
            TLRPC.Peer r143 = r82.peer_id;
            if (r143.channel_id != 0) goto L42;
            if (r143.chat_id != 0) goto L42;
            if ((MessageObject.getMedia(r82) instanceof TLRPC.TL_messageMediaGame) == true) goto L42;
            if ((MessageObject.getMedia(r13.messageOwner) instanceof TLRPC.TL_messageMediaInvoice) == true) goto L42;
        L41:
            if (r13.searchType == 4) goto L42;
        L43:
            r8 = r3;
        L49:
            TLRPC.Message r7 = r13.messageOwner;
            if (r7 != null) goto L52;
        L54:
            TLRPC.PhotoSize r72 = FileLoader.getClosestPhotoSizeWithSize(r13.photoThumbs, AndroidUtilities.getPhotoSize());
            GroupedMessagePosition r144 = new GroupedMessagePosition();
            if (this.reversed == false) goto L60;
            if (r6 != 0) goto L58;
        L57:
            boolean r15 = r4;
        L62:
            r144.last = r15;
            if (r72 != null) goto L65;
            float r73 = 1.0f;
        L66:
            r144.aspectRatio = r73;
            if (r73 <= 1.2f) goto L70;
            r5.append("w");
        L73:
            float r74 = r144.aspectRatio;
            r9 = r9 + r74;
            if (r74 <= 2.0f) goto L76;
            r10 = r4;
        L76:
            this.positions.put(r13, r144);
            this.positionsArray.put(r13.getId(), r144);
            this.posArray.add(r144);
            if (r13.caption == null) goto L88;
            if (r12 == false) goto L84;
            if (this.captionMessage != null) goto L84;
            this.captionMessage = r13;
            r12 = false;
        L86:
            this.hasCaption = true;
        L84:
            if (this.isDocuments == true) goto L86;
            this.captionMessage = null;
        L88:
            if (this.reversed == false) goto L90;
            r6 = r6 - 1;
        L91:
            r3 = 0;
            r4 = 1;
            goto L14
        L90:
            r6 = r6 + 1;
            goto L91
        L70:
            if (r73 >= 0.8f) goto L72;
            r5.append("n");
            goto L73
        L72:
            r5.append("q");
            goto L73
        L65:
            r73 = r72.w / r72.h;
        L58:
            r15 = r3;
            goto L62
        L60:
            if (r6 != (r2 - 1)) goto L58;
        L52:
            if (r7.invert_media == false) goto L54;
            this.captionAbove = r4;
            goto L54
        L22:
            r14 = r3;
        L93:
            if (this.isDocuments == false) goto L104;
            int r1 = 0;
        L95:
            if (r1 >= r2) goto L365;
            GroupedMessagePosition r32 = this.posArray.get(r1);
            r32.flags = 3;
            if (r1 != 0) goto L99;
            r32.flags = 3 | 4;
            byte r52 = 0;
            r32.last = false;
            boolean r83 = true;
        L103:
            r32.edge = r83;
            r32.aspectRatio = 1.0f;
            r32.minX = r52;
            r32.maxX = r52;
            byte r53 = (byte) r1;
            r32.minY = r53;
            r32.maxY = r53;
            r32.spanSize = 1000;
            r32.pw = this.maxSizeWidth;
            r32.ph = 100.0f;
            r1 = r1 + 1;
            goto L95
        L99:
            r52 = 0;
            if (r1 != (r2 - 1)) goto L102;
            r32.flags = 3 | 8;
            r83 = true;
            r32.last = true;
            goto L103
        L102:
            r83 = true;
            r32.last = false;
            goto L103
        L365:
            return;
        L104:
            if (r8 == 0) goto L106;
            this.maxSizeWidth -= 50;
            int r33 = 250;
        L107:
            int r122 = AndroidUtilities.dp(120.0f);
            float r84 = AndroidUtilities.dp(120.0f);
            Point r132 = AndroidUtilities.displaySize;
            int r85 = (int) (r84 / (Math.min(r132.x, r132.y) / this.maxSizeWidth));
            float r133 = AndroidUtilities.dp(40.0f);
            Point r145 = AndroidUtilities.displaySize;
            float r16 = Math.min(r145.x, r145.y);
            int r146 = this.maxSizeWidth;
            int r17 = (int) (r133 / (r16 / r146));
            float r42 = r146 / 814.0f;
            float r92 = r9 / r2;
            float r147 = AndroidUtilities.dp(100.0f) / 814.0f;
            if (r2 != 1) goto L111;
            GroupedMessagePosition r18 = this.posArray.get(0);
            int r43 = this.maxSizeWidth;
            float r75 = r18.aspectRatio;
            r18.set(0, 0, 0, 0, this.maxSizeWidth, Math.round(Math.min(r43 / r75, Math.min(r43 / r75, 407.0f))) / 814.0f, 15);
            int r26 = r33;
            boolean r31 = r11;
            int r34 = 0;
        L110:
            int r19 = 0;
        L286:
            int r110 = r19;
        L287:
            if (r110 >= r2) goto L366;
            GroupedMessagePosition r44 = this.posArray.get(r110);
            if (r31 == false) goto L297;
            if (r44.minX != 0) goto L294;
            r44.spanSize += r26;
        L294:
            if ((r44.flags & 2) == 0) goto L304;
            r44.edge = true;
        L304:
            MessageObject r54 = this.messages.get(r110);
            if (r31 == true) goto L324;
            if (MessageObject.m4467$$Nest$mneedDrawAvatarInternal(r54) == false) goto L324;
            if (r44.edge == false) goto L316;
            int r55 = r44.spanSize;
            if (r55 == 1000) goto L313;
            r44.spanSize = r55 + 108;
        L313:
            r44.pw += 108;
            goto L324
        L316:
            if ((r44.flags & 2) == 0) goto L324;
            int r56 = r44.spanSize;
            if (r56 == 1000) goto L320;
            r44.spanSize = r56 - 108;
            goto L324
        L320:
            int r57 = r44.leftSpanOffset;
            if (r57 == 0) goto L324;
            r44.leftSpanOffset = r57 + 108;
        L324:
            r110 = r110 + 1;
            goto L287
        L297:
            if (r44.maxX != r34) goto L299;
        L300:
            r44.spanSize += r26;
        L302:
            if ((r44.flags & 1) == 0) goto L304;
            r44.edge = true;
            goto L304
        L299:
            if ((r44.flags & 2) == 0) goto L302;
        L366:
            return;
        L111:
            if (r10 == true) goto L117;
            if (r2 == 2) goto L118;
            if (r2 == 3) goto L118;
            if (r2 != 4) goto L117;
        L118:
            if (r2 != 2) goto L140;
            GroupedMessagePosition r111 = this.posArray.get(0);
            GroupedMessagePosition r76 = this.posArray.get(1);
            String r58 = r5.toString();
            if (r58.equals("ww") == false) goto L126;
            r31 = r11;
            float r162 = 0.4f;
            if (r92 <= (r42 * 1.4d)) goto L128;
            float r45 = r111.aspectRatio;
            float r93 = r76.aspectRatio;
            if ((r45 - r93) >= 0.2d) goto L128;
            int r59 = this.maxSizeWidth;
            float r29 = Math.round(Math.min(r59 / r45, Math.min(r59 / r93, 407.0f))) / 814.0f;
            r111.set(0, 0, 0, 0, this.maxSizeWidth, r29, 7);
            r76.set(0, 0, 1, 1, this.maxSizeWidth, r29, 11);
            int r112 = 0;
        L139:
            r26 = r33;
            r19 = 0;
            r34 = r112;
        L128:
            if (r58.equals("ww") == false) goto L130;
        L138:
            int r46 = this.maxSizeWidth / 2;
            float r510 = r46;
            float r292 = Math.round(Math.min(r510 / r111.aspectRatio, Math.min(r510 / r76.aspectRatio, 814.0f))) / 814.0f;
            r111.set(0, 0, 0, 0, r46, r292, 13);
            r76.set(1, 1, 0, 0, r46, r292, 14);
        L137:
            r112 = 1;
            goto L139
        L130:
            if (r58.equals("qq") == true) goto L138;
            int r47 = this.maxSizeWidth;
            float r62 = r111.aspectRatio;
            int r48 = (int) Math.max(r47 * r162, Math.round((r47 / r62) / ((1.0f / r62) + (1.0f / r76.aspectRatio))));
            int r511 = this.maxSizeWidth - r48;
            if (r511 >= r85) goto L135;
            r48 = r48 - (r85 - r511);
        L136:
            float r293 = Math.min(814.0f, Math.round(Math.min(r85 / r111.aspectRatio, r48 / r76.aspectRatio))) / 814.0f;
            r111.set(0, 0, 0, 0, r85, r293, 13);
            r76.set(1, 1, 0, 0, r48, r293, 14);
            goto L137
        L135:
            r85 = r511;
            goto L136
        L126:
            r31 = r11;
            r162 = 0.4f;
            goto L128
        L140:
            r31 = r11;
            ArrayList<GroupedMessagePosition> r49 = this.posArray;
            if (r2 != 3) goto L155;
            GroupedMessagePosition r410 = r49.get(0);
            GroupedMessagePosition r77 = this.posArray.get(1);
            GroupedMessagePosition r113 = this.posArray.get(2);
            if (r5.charAt(0) != 'n') goto L149;
            float r512 = r77.aspectRatio;
            float r513 = Math.min(407.0f, Math.round((this.maxSizeWidth * r512) / (r113.aspectRatio + r512)));
            int r86 = (int) Math.max(r85, Math.min(this.maxSizeWidth * 0.5f, Math.round(Math.min(r113.aspectRatio * r513, r77.aspectRatio * r6))));
            int r28 = Math.round(Math.min((r410.aspectRatio * 814.0f) + r17, this.maxSizeWidth - r86));
            r410.set(0, 0, 0, 1, r28, 1.0f, 13);
            float r294 = (814.0f - r513) / 814.0f;
            r77.set(1, 1, 0, 0, r86, r294, 6);
            float r295 = r513 / 814.0f;
            r113.set(0, 1, 1, 1, r86, r295, 10);
            int r514 = this.maxSizeWidth;
            r113.spanSize = r514;
            r410.siblingHeights = new float[]{r295, r294};
            if (r31 == false) goto L147;
            r410.spanSize = r514 - r86;
        L148:
            this.hasSibling = true;
        L154:
            r26 = r33;
            r34 = 1;
            goto L110
        L147:
            r77.spanSize = r514 - r28;
            r113.leftSpanOffset = r28;
            goto L148
        L149:
            float r296 = Math.round(Math.min(this.maxSizeWidth / r410.aspectRatio, 537.24005f)) / 814.0f;
            r410.set(0, 1, 0, 0, this.maxSizeWidth, r296, 7);
            int r114 = this.maxSizeWidth / 2;
            float r515 = r114;
            float r411 = Math.min(814.0f - r296, Math.round(Math.min(r515 / r77.aspectRatio, r515 / r113.aspectRatio))) / 814.0f;
            if (r411 >= r147) goto L152;
            float r297 = r147;
        L153:
            r77.set(0, 0, 1, 1, r114, r297, 9);
            r113.set(1, 1, 1, 1, r114, r297, 10);
            goto L154
        L152:
            r297 = r411;
            goto L153
        L155:
            GroupedMessagePosition r412 = r49.get(0);
            GroupedMessagePosition r63 = this.posArray.get(1);
            GroupedMessagePosition r115 = this.posArray.get(2);
            GroupedMessagePosition r152 = this.posArray.get(3);
            if (r5.charAt(0) != 'w') goto L165;
            float r298 = Math.round(Math.min(this.maxSizeWidth / r412.aspectRatio, 537.24005f)) / 814.0f;
            r412.set(0, 2, 0, 0, this.maxSizeWidth, r298, 7);
            float r116 = Math.round(this.maxSizeWidth / ((r63.aspectRatio + r115.aspectRatio) + r152.aspectRatio));
            float r413 = r85;
            int r516 = (int) Math.max(r413, Math.min(this.maxSizeWidth * 0.4f, r63.aspectRatio * r116));
            int r414 = (int) Math.max(Math.max(r413, this.maxSizeWidth * 0.33f), r152.aspectRatio * r116);
            int r78 = (this.maxSizeWidth - r516) - r414;
            if (r78 >= AndroidUtilities.dp(58.0f)) goto L160;
            int r94 = AndroidUtilities.dp(58.0f) - r78;
            r78 = AndroidUtilities.dp(58.0f);
            int r87 = r94 / 2;
            r516 = r516 - r87;
            r414 = r414 - (r94 - r87);
        L160:
            int r282 = r516;
            float r117 = Math.min(814.0f - r298, r116) / 814.0f;
            if (r117 >= r147) goto L163;
            float r299 = r147;
        L164:
            r63.set(0, 0, 1, 1, r282, r299, 9);
            r115.set(1, 1, 1, 1, r78, r299, 8);
            r152.set(2, 2, 1, 1, r414, r299, 10);
            r112 = 2;
            goto L139
        L163:
            r299 = r117;
            goto L164
        L165:
            int r517 = Math.max(r85, Math.round(814.0f / (((1.0f / r63.aspectRatio) + (1.0f / r115.aspectRatio)) + (1.0f / r152.aspectRatio))));
            float r88 = r122;
            float r95 = r517;
            float r102 = Math.min(0.33f, Math.max(r88, r95 / r63.aspectRatio) / 814.0f);
            float r89 = Math.min(0.33f, Math.max(r88, r95 / r115.aspectRatio) / 814.0f);
            float r96 = (1.0f - r102) - r89;
            int r283 = Math.round(Math.min((814.0f * r412.aspectRatio) + r17, this.maxSizeWidth - r517));
            r412.set(0, 0, 0, 2, r283, (r102 + r89) + r96, 13);
            r63.set(1, 1, 0, 0, r517, r102, 6);
            r115.set(0, 1, 1, 1, r517, r89, 2);
            r115.spanSize = this.maxSizeWidth;
            r152.set(0, 1, 2, 2, r517, r96, 10);
            int r97 = this.maxSizeWidth;
            r152.spanSize = r97;
            if (r31 == false) goto L168;
            r412.spanSize = r97 - r517;
        L169:
            r412.siblingHeights = new float[]{r102, r89, r96};
            this.hasSibling = true;
            goto L137
        L168:
            r63.spanSize = r97 - r283;
            r115.leftSpanOffset = r283;
            r152.leftSpanOffset = r283;
        L117:
            r31 = r11;
            int r322 = 2;
            int r118 = this.posArray.size();
            float[] r415 = new float[r118];
            int r518 = 0;
        L171:
            if (r518 >= r2) goto L177;
            ArrayList<GroupedMessagePosition> r103 = this.posArray;
            if (r92 <= 1.1f) goto L175;
            r415[r518] = Math.max(1.0f, r103.get(r518).aspectRatio);
        L176:
            r415[r518] = Math.max(0.66667f, Math.min(1.7f, r415[r518]));
            r518 = r518 + 1;
            goto L171
        L175:
            r415[r518] = Math.min(1.0f, r103.get(r518).aspectRatio);
            goto L176
        L177:
            ArrayList r519 = new ArrayList();
            int r64 = 1;
        L178:
            if (r64 >= r118) goto L185;
            int r104 = r118 - r64;
            if (r64 > 3) goto L184;
            if (r104 > 3) goto L184;
            r519.add(new MessageGroupedLayoutAttempt(r64, r104, multiHeight(r415, 0, r64), multiHeight(r415, r64, r118)));
        L184:
            r64 = r64 + 1;
            goto L178
        L185:
            int r65 = 1;
        L187:
            if (r65 >= (r118 - 1)) goto L203;
            int r105 = 1;
        L189:
            int r119 = r118 - r65;
            if (r105 >= r119) goto L202;
            int r1110 = r119 - r105;
            if (r65 <= 3) goto L194;
        L199:
            int r25 = r65;
            int r262 = r105;
        L201:
            r105 = r262 + 1;
            r65 = r25;
            goto L189
        L194:
            if (r92 >= 0.85f) goto L196;
            int r123 = 4;
        L197:
            if (r105 > r123) goto L199;
            if (r1110 > 3) goto L199;
            int r124 = r65 + r105;
            r25 = r65;
            r262 = r105;
            r519.add(new MessageGroupedLayoutAttempt(r25, r262, r1110, multiHeight(r415, 0, r65), multiHeight(r415, r65, r124), multiHeight(r415, r124, r118)));
            goto L201
        L196:
            r123 = 3;
            goto L197
        L202:
            r65 = r65 + 1;
            goto L187
        L203:
            int r66 = 1;
        L205:
            if (r66 >= (r118 - 2)) goto L222;
            int r98 = 1;
        L207:
            int r106 = r118 - r66;
            if (r98 >= r106) goto L221;
            int r1111 = 1;
        L210:
            int r125 = r106 - r98;
            if (r1111 >= r125) goto L220;
            int r126 = r125 - r1111;
            if (r66 > 3) goto L217;
            if (r98 > 3) goto L217;
            if (r1111 > 3) goto L217;
            if (r126 > 3) goto L217;
            int r134 = r66 + r98;
            int r153 = r134 + r1111;
            int r342 = r66;
            int r35 = r98;
            int r36 = r1111;
            r519.add(new MessageGroupedLayoutAttempt(r342, r35, r36, r126, multiHeight(r415, 0, r66), multiHeight(r415, r66, r134), multiHeight(r415, r134, r153), multiHeight(r415, r153, r118)));
        L219:
            r1111 = r36 + 1;
            r66 = r342;
            r98 = r35;
        L217:
            r342 = r66;
            r35 = r98;
            r36 = r1111;
            goto L219
        L220:
            r98 = r98 + 1;
            goto L207
        L221:
            r66 = r66 + 1;
            goto L205
        L222:
            float r120 = (this.maxSizeWidth / 3) * 4;
            int r99 = 0;
            MessageGroupedLayoutAttempt r107 = null;
            float r1112 = 0.0f;
        L224:
            if (r99 >= r519.size()) goto L258;
            MessageGroupedLayoutAttempt r127 = (MessageGroupedLayoutAttempt) r519.get(r99);
            float r154 = Float.MAX_VALUE;
            int r135 = 0;
            float r172 = 0.0f;
        L226:
            float[] r67 = r127.heights;
            if (r135 >= r67.length) goto L232;
            float r68 = r67[r135];
            r172 = r172 + r68;
            if (r68 >= r154) goto L231;
            r154 = r68;
        L231:
            r135 = r135 + 1;
            goto L226
        L232:
            float r69 = Math.abs(r172 - r120);
            int[] r79 = r127.lineCounts;
            float r173 = r120;
            if (r79.length <= 1) goto L249;
            int r136 = r79[0];
            int r121 = r79[1];
            if (r136 > r121) goto L247;
            int r263 = r33;
            int r37 = r322;
            if (r79.length > r37) goto L239;
        L242:
            char r137 = 3;
            if (r79.length > 3) goto L245;
        L251:
            if (r154 >= r85) goto L253;
            r69 = r69 * 1.5f;
        L253:
            if (r107 != null) goto L255;
        L256:
            r1112 = r69;
            r107 = r127;
        L257:
            r99 = r99 + 1;
            r120 = r173;
            r33 = r263;
            r322 = 2;
            goto L224
        L255:
            if (r69 >= r1112) goto L257;
        L245:
            if (r79[r37] <= r79[3]) goto L251;
        L248:
            r69 = r69 * 1.2f;
            goto L251
        L239:
            if (r121 <= r79[r37]) goto L242;
        L241:
            r137 = 3;
            goto L248
        L247:
            r263 = r33;
            goto L241
        L249:
            r263 = r33;
            r137 = 3;
            goto L251
        L258:
            r26 = r33;
            r19 = 0;
            if (r107 == null) goto L364;
            int r128 = 0;
            r34 = 0;
            int r520 = 0;
        L262:
            int[] r610 = r107.lineCounts;
            if (r520 >= r610.length) goto L286;
            int r611 = r610[r520];
            float r710 = r107.heights[r520];
            int r810 = this.maxSizeWidth;
            int r910 = r611 - 1;
            r34 = Math.max(r34, r910);
            int r1113 = r810;
            int r811 = 0;
            GroupedMessagePosition r129 = null;
        L265:
            if (r811 >= r611) goto L285;
            int r138 = (int) (r415[r128] * r710);
            r1113 = r1113 - r138;
            GroupedMessagePosition r332 = this.posArray.get(r128);
            if (r520 != 0) goto L270;
            int r155 = 4;
        L269:
            int r163 = r128;
            if (r520 != (r107.lineCounts.length - 1)) goto L274;
            r155 = r155 | 8;
        L274:
            if (r811 != 0) goto L278;
            r155 = r155 | 1;
            if (r31 == false) goto L278;
            r129 = r332;
        L278:
            if (r811 != r910) goto L283;
            r155 = r155 | 2;
            if (r31 == true) goto L283;
            int r40 = r155;
            r129 = r332;
        L284:
            int r343 = r811;
            r332.set(r343, r811, r520, r520, r138, Math.max(r147, r710 / 814.0f), r40);
            r128 = r163 + 1;
            r811 = r343 + 1;
        L283:
            r40 = r155;
            goto L284
        L270:
            r155 = 0;
            goto L269
        L285:
            r129.pw += r1113;
            r129.spanSize += r1113;
            r520 = r520 + 1;
            r128 = r128;
            goto L262
        L364:
            return;
        L106:
            r33 = 200;
            goto L107
        L18:
            if (r6 >= r2) goto L93;
        L11:
            r6 = 0;
            goto L12
        }

        private float multiHeight(float[] r3, int r4, int r5) {
            float r0 = 0.0f;
        L3:
            if (r4 >= r5) goto L6;
            r0 = r0 + r3[r4];
            r4 = r4 + 1;
            goto L3
        L6:
            return this.maxSizeWidth / r0;
        }

        public void calculate() {
            calculateInternal();
            WidePosts.updateGroupedLayout(this, WidePosts.getDefaultGroupWidth());
        }

        public boolean contains(int r4) {
            if (this.messages != null) goto L5;
            return false;
        L5:
            int r0 = 0;
        L7:
            if (r0 >= this.messages.size()) goto L15;
            MessageObject r2 = this.messages.get(r0);
            if (r2 == null) goto L14;
            if (r2.getId() != r4) goto L14;
            return true;
        L14:
            r0 = r0 + 1;
            goto L7
        L15:
            return false;
        }

        public MessageObject findCaptionMessageObject() {
            if (this.messages.isEmpty() == false) goto L5;
        L7:
            int r1 = 0;
            MessageObject r2 = null;
        L9:
            if (r1 >= this.messages.size()) goto L16;
            MessageObject r3 = this.messages.get(r1);
            if (TextUtils.isEmpty(r3.caption) == true) goto L15;
            if (r2 != null) goto L13;
            r2 = r3;
            goto L15
        L13:
            return null;
        L15:
            r1 = r1 + 1;
            goto L9
        L16:
            return r2;
        L5:
            if (this.positions.isEmpty() == false) goto L7;
            calculate();
            goto L7
        }

        public MessageObject findMessageWithFlags(int r4) {
            if (this.messages.isEmpty() == false) goto L5;
        L7:
            int r0 = 0;
        L9:
            if (r0 >= this.messages.size()) goto L16;
            MessageObject r1 = this.messages.get(r0);
            GroupedMessagePosition r2 = this.positions.get(r1);
            if (r2 == null) goto L15;
            if ((r2.flags & r4) != r4) goto L15;
            return r1;
        L15:
            r0 = r0 + 1;
            goto L9
        L16:
            return null;
        L5:
            if (this.positions.isEmpty() == false) goto L7;
            calculate();
            goto L7
        }

        public MessageObject findPrimaryMessageObject() {
            if (this.reversed == false) goto L5;
            int r0 = 10;
        L7:
            return findMessageWithFlags(r0);
        L5:
            r0 = 5;
            goto L7
        }

        public int getMaxEditDate() {
            int r0 = 0;
            int r1 = 0;
        L4:
            if (r0 >= this.messages.size()) goto L11;
            MessageObject r2 = this.messages.get(r0);
            if (r2 == null) goto L10;
            TLRPC.Message r22 = r2.messageOwner;
            if (r22 == null) goto L10;
            r1 = Math.max(r1, r22.edit_date);
        L10:
            r0 = r0 + 1;
            goto L4
        L11:
            return r1;
        }

        public GroupedMessagePosition getPosition(MessageObject r3) {
            if (r3 != null) goto L5;
            return null;
        L5:
            GroupedMessagePosition r0 = this.positions.get(r3);
            if (r0 == null) goto L8;
            return r0;
        L8:
            return this.positionsArray.get(r3.getId());
        }
    }

    public static class SendAnimationData {
        public float currentScale;
        public float currentX;
        public float currentY;
        public ChatMessageCell.TransitionParams fromParams;
        public boolean fromPreview;
        public float height;
        public float progress;
        public float timeAlpha;
        public float width;
        public float x;
        public float y;

        public SendAnimationData() {
        }
    }

    /* loaded from: classes5.dex */
    public static class TextLayoutBlock implements MultiLayoutTypingAnimator.Block {
        public static final int FLAG_NOT_RTL = 2;
        public static final int FLAG_RTL = 1;
        public int charactersEnd;
        public int charactersOffset;
        public boolean code;
        public ButtonBounce collapsedBounce;
        public int collapsedHeight;
        public Drawable copyIcon;
        public int copyIconColor;
        public Drawable copySelector;
        public int copySelectorColor;
        public Paint copySeparator;
        public Text copyText;
        public byte directionFlags;
        public boolean first;
        public boolean hasCodeCopyButton;
        public int height;
        public int index;
        public String language;
        public int languageHeight;
        public Text languageLayout;
        public boolean last;
        public float maxRight;
        public MessageObject messageObject;
        public int originalWidth;
        public int padBottom;
        public int padTop;
        public boolean quote;
        public boolean quoteCollapse;
        public List<SpoilerEffect> spoilers;
        public AtomicReference<Layout> spoilersPatchedTextLayout;
        public int start;
        public StaticLayout textLayout;

        public TextLayoutBlock() {
            this.spoilersPatchedTextLayout = new AtomicReference();
            this.spoilers = new ArrayList();
        }

        private static String capitalizeFirst(String r3) {
            return r3.substring(0, 1).toUpperCase() + r3.substring(1).toLowerCase();
        }

        public static String capitalizeLanguage(String r3) {
            if (r3 != null) goto L5;
            return null;
        L5:
            String r0 = r3.toLowerCase().replaceAll("\\W|lang$", _UrlKt.FRAGMENT_ENCODE_SET);
            r0.getClass();
            char r2 = 65535;
            switch(r0.hashCode()) {
                case -1886433663: goto L221;
                case -1408289185: goto L217;
                case -1395746259: goto L213;
                case -1351281305: goto L209;
                case -1265393852: goto L205;
                case -1081245651: goto L201;
                case -973197092: goto L197;
                case -522285947: goto L193;
                case 114: goto L189;
                case 3184: goto L185;
                case 3401: goto L181;
                case 3479: goto L177;
                case 3593: goto L173;
                case 3632: goto L169;
                case 3704: goto L165;
                case 3711: goto L161;
                case 96891: goto L157;
                case 98723: goto L153;
                case 98819: goto L149;
                case 98822: goto L145;
                case 104420: goto L141;
                case 105551: goto L137;
                case 110968: goto L133;
                case 112080: goto L129;
                case 114126: goto L125;
                case 114922: goto L121;
                case 115161: goto L117;
                case 118807: goto L113;
                case 119768: goto L109;
                case 3154628: goto L105;
                case 3175934: goto L101;
                case 3205725: goto L97;
                case 3213227: goto L93;
                case 3213448: goto L89;
                case 3271912: goto L85;
                case 3318169: goto L81;
                case 3373901: goto L77;
                case 3404364: goto L73;
                case 3511770: goto L69;
                case 3522862: goto L65;
                case 3524784: goto L61;
                case 3561037: goto L57;
                case 3642020: goto L53;
                case 3701415: goto L49;
                case 94833107: goto L45;
                case 101429325: goto L41;
                case 101429384: goto L37;
                case 112003953: goto L33;
                case 188995949: goto L29;
                case 213985633: goto L25;
                case 246938863: goto L21;
                case 499384161: goto L17;
                case 1064174568: goto L13;
                case 1067478602: goto L9;
                default: goto L224;
            };
        L224:
            switch(r2) {
                case 0: goto L263;
                case 1: goto L261;
                case 2: goto L259;
                case 3: goto L257;
                case 4: goto L255;
                case 5: goto L254;
                case 6: goto L251;
                case 7: goto L249;
                case 8: goto L254;
                case 9: goto L257;
                case 10: goto L247;
                case 11: goto L245;
                case 12: goto L251;
                case 13: goto L243;
                case 14: goto L254;
                case 15: goto L249;
                case 16: goto L254;
                case 17: goto L241;
                case 18: goto L254;
                case 19: goto L254;
                case 20: goto L254;
                case 21: goto L254;
                case 22: goto L254;
                case 23: goto L254;
                case 24: goto L254;
                case 25: goto L239;
                case 26: goto L254;
                case 27: goto L254;
                case 28: goto L254;
                case 29: goto L237;
                case 30: goto L254;
                case 31: goto L254;
                case 32: goto L254;
                case 33: goto L254;
                case 34: goto L254;
                case 35: goto L254;
                case 36: goto L254;
                case 37: goto L235;
                case 38: goto L243;
                case 39: goto L254;
                case 40: goto L254;
                case 41: goto L239;
                case 42: goto L254;
                case 43: goto L254;
                case 44: goto L254;
                case 45: goto L254;
                case 46: goto L254;
                case 47: goto L233;
                case 48: goto L247;
                case 49: goto L231;
                case 50: goto L245;
                case 51: goto L229;
                case 52: goto L227;
                case 53: goto L235;
                default: goto L226;
            };
        L227:
            return "GDScript";
        L229:
            return "Visual Basic";
        L231:
            return "AutoHotKey";
        L233:
            return "VB.NET";
        L235:
            return "Objective-C";
        L237:
            return "FunC";
        L239:
            return "TL-B";
        L241:
            return "C++";
        L243:
            return "Ruby";
        L245:
            return "Markdown";
        L247:
            return "JavaScript";
        L249:
            return "TypeScript";
        L251:
            return "Python";
        L255:
            return "F#";
        L257:
            return "C#";
        L259:
            return "BBCode";
        L261:
            return "ASP.NET";
        L263:
            return "ActionScript";
        L226:
            return capitalizeFirst(r3);
        L254:
            return r3.toUpperCase();
        L9:
            if (r0.equals("objectivec") == false) goto L224;
            r2 = '5';
            goto L224
        L13:
            if (r0.equals("gdscript") == false) goto L224;
            r2 = '4';
            goto L224
        L17:
            if (r0.equals("visual-basic") == false) goto L224;
            r2 = '3';
            goto L224
        L21:
            if (r0.equals("markdown") == false) goto L224;
            r2 = '2';
            goto L224
        L25:
            if (r0.equals("autohotkey") == false) goto L224;
            r2 = '1';
            goto L224
        L29:
            if (r0.equals("javascript") == false) goto L224;
            r2 = '0';
            goto L224
        L33:
            if (r0.equals("vbnet") == false) goto L224;
            r2 = '/';
            goto L224
        L37:
            if (r0.equals("jsonp") == false) goto L224;
            r2 = '.';
            goto L224
        L41:
            if (r0.equals("json5") == false) goto L224;
            r2 = SignatureVisitor.SUPER;
            goto L224
        L45:
            if (r0.equals("cobol") == false) goto L224;
            r2 = ',';
            goto L224
        L49:
            if (r0.equals("yaml") == false) goto L224;
            r2 = SignatureVisitor.EXTENDS;
            goto L224
        L53:
            if (r0.equals("wasm") == false) goto L224;
            r2 = '*';
            goto L224
        L57:
            if (r0.equals("tl-b") == false) goto L224;
            r2 = ')';
            goto L224
        L61:
            if (r0.equals("scss") == false) goto L224;
            r2 = '(';
            goto L224
        L65:
            if (r0.equals("sass") == false) goto L224;
            r2 = '\'';
            goto L224
        L69:
            if (r0.equals("ruby") == false) goto L224;
            r2 = Typography.amp;
            goto L224
        L73:
            if (r0.equals("objc") == false) goto L224;
            r2 = '%';
            goto L224
        L77:
            if (r0.equals("nasm") == false) goto L224;
            r2 = Typography.dollar;
            goto L224
        L81:
            if (r0.equals("less") == false) goto L224;
            r2 = '#';
            goto L224
        L85:
            if (r0.equals("json") == false) goto L224;
            r2 = Typography.quote;
            goto L224
        L89:
            if (r0.equals("http") == false) goto L224;
            r2 = '!';
            goto L224
        L93:
            if (r0.equals("html") == false) goto L224;
            r2 = ' ';
            goto L224
        L97:
            if (r0.equals("hlsl") == false) goto L224;
            r2 = 31;
            goto L224
        L101:
            if (r0.equals("glsl") == false) goto L224;
            r2 = 30;
            goto L224
        L105:
            if (r0.equals("func") == false) goto L224;
            r2 = 29;
            goto L224
        L109:
            if (r0.equals("yml") == false) goto L224;
            r2 = 28;
            goto L224
        L113:
            if (r0.equals("xml") == false) goto L224;
            r2 = 27;
            goto L224
        L117:
            if (r0.equals("tsx") == false) goto L224;
            r2 = 26;
            goto L224
        L121:
            if (r0.equals("tlb") == false) goto L224;
            r2 = 25;
            goto L224
        L125:
            if (r0.equals("sql") == false) goto L224;
            r2 = 24;
            goto L224
        L129:
            if (r0.equals("qml") == false) goto L224;
            r2 = 23;
            goto L224
        L133:
            if (r0.equals("php") == false) goto L224;
            r2 = 22;
            goto L224
        L137:
            if (r0.equals("jsx") == false) goto L224;
            r2 = 21;
            goto L224
        L141:
            if (r0.equals("ini") == false) goto L224;
            r2 = 20;
            goto L224
        L145:
            if (r0.equals("csv") == false) goto L224;
            r2 = 19;
            goto L224
        L149:
            if (r0.equals("css") == false) goto L224;
            r2 = 18;
            goto L224
        L153:
            if (r0.equals("cpp") == false) goto L224;
            r2 = 17;
            goto L224
        L157:
            if (r0.equals("asm") == false) goto L224;
            r2 = 16;
            goto L224
        L161:
            if (r0.equals(HlsSegmentFormat.TS) == false) goto L224;
            r2 = 15;
            goto L224
        L165:
            if (r0.equals("tl") == false) goto L224;
            r2 = 14;
            goto L224
        L169:
            if (r0.equals("rb") == false) goto L224;
            r2 = '\r';
            goto L224
        L173:
            if (r0.equals("py") == false) goto L224;
            r2 = '\f';
            goto L224
        L177:
            if (r0.equals("md") == false) goto L224;
            r2 = 11;
            goto L224
        L181:
            if (r0.equals("js") == false) goto L224;
            r2 = '\n';
            goto L224
        L185:
            if (r0.equals("cs") == false) goto L224;
            r2 = '\t';
            goto L224
        L189:
            if (r0.equals("r") == false) goto L224;
            r2 = '\b';
            goto L224
        L193:
            if (r0.equals("typescript") == false) goto L224;
            r2 = 7;
            goto L224
        L197:
            if (r0.equals(PluginsConstants.PYTHON) == false) goto L224;
            r2 = 6;
            goto L224
        L201:
            if (r0.equals("matlab") == false) goto L224;
            r2 = 5;
            goto L224
        L205:
            if (r0.equals("fsharp") == false) goto L224;
            r2 = 4;
            goto L224
        L209:
            if (r0.equals("csharp") == false) goto L224;
            r2 = 3;
            goto L224
        L213:
            if (r0.equals("bbcode") == false) goto L224;
            r2 = 2;
            goto L224
        L217:
            if (r0.equals("aspnet") == false) goto L224;
            r2 = 1;
            goto L224
        L221:
            if (r0.equals("actionscript") == false) goto L224;
            r2 = 0;
            goto L224
        }

        public float collapsed(ChatMessageCell.TransitionParams r4) {
            if (r4.animateExpandedQuotes == false) goto L11;
            HashSet<Integer> r0 = r4.animateExpandedQuotesFrom;
            boolean r1 = true;
            if (r0 != null) goto L8;
        L12:
            float r02 = 0.0f;
            if (r1 == false) goto L15;
            float r12 = 1.0f;
        L17:
            if (collapsed() == false) goto L20;
            r02 = 1.0f;
        L20:
            return AndroidUtilities.lerp(r12, r02, r4.animateChangeProgress);
        L15:
            r12 = 0.0f;
            goto L17
        L8:
            if (r0.contains(Integer.valueOf(this.index)) == false) goto L12;
            r1 = false;
            goto L12
        L11:
            r1 = collapsed();
            goto L12
        }

        public boolean collapsed() {
            MessageObject r0 = this.messageObject;
            if (r0 == null) goto L11;
            HashSet<Integer> r02 = r0.expandedQuotes;
            if (r02 != null) goto L7;
            return true;
        L7:
            if (r02.contains(Integer.valueOf(this.index)) == false) goto L14;
            return false;
        L14:
            return true;
        L11:
            return true;
        }

        public void drawCopyCodeButton(Canvas r11, RectF r12, int r13, int r14, float r15) {
            if (this.hasCodeCopyButton == true) goto L5;
            return;
        L5:
            int r0 = Theme.multAlpha(r13, 0.1f);
            if (this.copySelectorColor == r0) goto L8;
            Drawable r1 = this.copySelector;
            this.copySelectorColor = r0;
            Theme.setSelectorDrawableColor(r1, r0, true);
        L8:
            this.copySelector.setBounds(((int) r12.left) + AndroidUtilities.dp(3.0f), (int) (r12.bottom - AndroidUtilities.dp(38.0f)), (int) r12.right, (int) r12.bottom);
            int r16 = (int) (255.0f * r15);
            this.copySelector.setAlpha(r16);
            if (this.copySelector.getCallback() == null) goto L11;
            this.copySelector.draw(r11);
        L11:
            this.copySeparator.setColor(ColorUtils.setAlphaComponent(r14, 38));
            r11.drawRect(r12.left + AndroidUtilities.dp(10.0f), (r12.bottom - AndroidUtilities.dp(38.0f)) - AndroidUtilities.getShadowHeight(), r12.right - AndroidUtilities.dp(6.66f), r12.bottom - AndroidUtilities.dp(38.0f), this.copySeparator);
            float r2 = r12.centerX() - (Math.min(r12.width() - AndroidUtilities.dp(12.0f), ((this.copyIcon.getIntrinsicWidth() * 0.8f) + AndroidUtilities.dp(5.0f)) + this.copyText.getCurrentWidth()) / 2.0f);
            float r122 = r12.bottom - (AndroidUtilities.dp(38.0f) / 2.0f);
            if (this.copyIconColor == r13) goto L14;
            Drawable r3 = this.copyIcon;
            this.copyIconColor = r13;
            r3.setColorFilter(new PorterDuffColorFilter(r13, PorterDuff.Mode.SRC_IN));
        L14:
            this.copyIcon.setAlpha(r16);
            this.copyIcon.setBounds((int) r2, (int) (r122 - ((r1.getIntrinsicHeight() * 0.8f) / 2.0f)), (int) ((this.copyIcon.getIntrinsicWidth() * 0.8f) + r2), (int) (((this.copyIcon.getIntrinsicHeight() * 0.8f) / 2.0f) + r122));
            this.copyIcon.draw(r11);
            this.copyText.ellipsize(((int) (r14 - ((this.copyIcon.getIntrinsicWidth() * 0.8f) + AndroidUtilities.dp(5.0f)))) + AndroidUtilities.dp(12.0f)).draw(r11, r2 + ((this.copyIcon.getIntrinsicWidth() * 0.8f) + AndroidUtilities.dp(5.0f)), r122, r13, r15);
        }

        @Override // org.telegram.ui.MultiLayoutTypingAnimator.Block
        public Layout getLayout() {
            return this.textLayout;
        }

        @Override // org.telegram.ui.MultiLayoutTypingAnimator.Block
        public View getParentView() {
            return null;
        }

        public int height() {
            if (this.quoteCollapse == false) goto L9;
            if (collapsed() == false) goto L9;
            return this.collapsedHeight;
        L9:
            return this.height;
        }

        public int height(ChatMessageCell.TransitionParams r3) {
            boolean r0 = this.quoteCollapse;
            int r1 = this.height;
            if (r0 == true) goto L6;
            return r1;
        L6:
            return AndroidUtilities.lerp(r1, this.collapsedHeight, collapsed(r3));
        }

        public int heightCollapsed() {
            if (this.quoteCollapse == false) goto L7;
            return this.collapsedHeight;
        L7:
            return this.height;
        }

        public boolean isRtl() {
            byte r1 = this.directionFlags;
            if ((r1 & 1) != 0) goto L5;
            return false;
        L5:
            if ((r1 & 2) != 0) goto L10;
            return true;
        L10:
            return false;
        }

        public void layoutCode(String r6, int r7, boolean r8) {
            if (r7 < 75) goto L6;
            if (r8 == true) goto L6;
            boolean r82 = true;
        L7:
            this.hasCodeCopyButton = r82;
            if (r82 == false) goto L11;
            this.copyText = new Text(LocaleController.getString(R.string.CopyCode).toUpperCase(), SharedConfig.fontSize - 3, AndroidUtilities.bold());
            Drawable r83 = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.msg_copy).mutate();
            this.copyIcon = r83;
            r83.setColorFilter(new PorterDuffColorFilter(this.copyIconColor, PorterDuff.Mode.SRC_IN));
            this.copySelector = Theme.createRadSelectorDrawable(this.copySelectorColor, 0, 0, Math.min(5, SharedConfig.bubbleRadius), 0);
            this.copySeparator = new Paint(1);
        L11:
            if (TextUtils.isEmpty(r6) == false) goto L14;
            this.language = null;
            this.languageLayout = null;
            return;
        L14:
            this.language = r6;
            Text r84 = new Text(capitalizeLanguage(r6), (SharedConfig.fontSize - 1) - (CodeHighlighting.getTextSizeDecrement(r7) / 2), AndroidUtilities.bold());
            this.languageLayout = r84;
            this.languageHeight = ((int) (r84.getTextSize() * 1.714f)) + AndroidUtilities.dp(4.0f);
            return;
        L6:
            r82 = false;
            goto L7
        }

        public float textYOffset(ArrayList<TextLayoutBlock> r6) {
            if (r6 != null) goto L5;
            return 0.0f;
        L5:
            int r0 = 0;
            int r1 = 0;
        L7:
            if (r0 >= r6.size()) goto L13;
            TextLayoutBlock r2 = r6.get(r0);
            if (r2 == this) goto L13;
            r1 = r1 + ((r2.padTop + r2.height()) + r2.padBottom);
            r0 = r0 + 1;
        L13:
            return r1;
        }

        public float textYOffset(ArrayList<TextLayoutBlock> r6, ChatMessageCell.TransitionParams r7) {
            if (r6 != null) goto L5;
            return 0.0f;
        L5:
            int r0 = 0;
            int r1 = 0;
        L7:
            if (r0 >= r6.size()) goto L13;
            TextLayoutBlock r2 = r6.get(r0);
            if (r2 == this) goto L13;
            r1 = r1 + ((r2.padTop + r2.height(r7)) + r2.padBottom);
            r0 = r0 + 1;
        L13:
            return r1;
        }
    }

    /* loaded from: classes5.dex */
    public static class TextLayoutBlocks {
        public boolean hasCode;
        public boolean hasCodeAtBottom;
        public boolean hasCodeAtTop;
        public boolean hasQuote;
        public boolean hasQuoteAtBottom;
        public boolean hasRtl;
        public boolean hasSingleCode;
        public boolean hasSingleQuote;
        public int lastLineWidth;
        public final CharSequence text;
        public final ArrayList<TextLayoutBlock> textLayoutBlocks;
        public int textWidth;
        public float textXOffset;

        /* renamed from: org.telegram.messenger.MessageObject$TextLayoutBlocks$1, reason: invalid class name */
        public class AnonymousClass1 extends CharacterStyle {
            final /* synthetic */ TextLayoutBlocks this$0;

            public AnonymousClass1(TextLayoutBlocks r1) {
                this.this$0 = r1;
            }

            @Override // android.text.style.CharacterStyle
            public void updateDrawState(TextPaint r1) {
                r1.setColor(((TextPaint) Theme.chat_msgTextPaint).linkColor);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public TextLayoutBlocks(MessageObject r33, CharSequence r34, TextPaint r35, int r36) {
            MessageObject r2 = r33;
            this.textLayoutBlocks = new ArrayList();
            CharSequence r3 = LocaleUtils.insertHexColorsPreview(r34);
            this.text = r3;
            boolean r9 = false;
            this.textWidth = 0;
            int r10 = 1;
            if (r2 == null) goto L9;
            TLRPC.Message r0 = r2.messageOwner;
            if (r0 == null) goto L9;
            if (r0.noforwards == false) goto L9;
            boolean r02 = true;
        L10:
            if (r2 == null) goto L13;
            if (r02 == true) goto L13;
            r02 = MessagesController.getInstance(r2.currentAccount).isPeerNoForwards(r2.getDialogId());
        L13:
            boolean r11 = r02;
            boolean r03 = r3 instanceof Spanned;
            if (r03 == true) goto L16;
        L18:
            boolean r5 = false;
        L19:
            this.hasCode = r5;
            if (r03 == true) goto L22;
        L24:
            boolean r52 = false;
        L25:
            this.hasQuote = r52;
            this.hasSingleQuote = false;
            this.hasSingleCode = false;
            if (r03 == false) goto L49;
            Spanned r04 = (Spanned) r3;
            QuoteSpan[] r53 = (QuoteSpan[]) r04.getSpans(0, r04.length(), QuoteSpan.class);
            int r6 = 0;
        L29:
            if (r6 >= r53.length) goto L32;
            r53[r6].adaptLineHeight = false;
            r6 = r6 + 1;
            goto L29
        L32:
            if (r53.length == 1) goto L34;
        L38:
            boolean r54 = false;
        L39:
            this.hasSingleQuote = r54;
            CodeHighlighting.Span[] r4 = (CodeHighlighting.Span[]) r04.getSpans(0, r04.length(), CodeHighlighting.Span.class);
            if (r4.length == 1) goto L42;
        L46:
            boolean r05 = false;
        L47:
            this.hasSingleCode = r05;
            goto L49
        L42:
            if (r04.getSpanStart(r4[0]) != 0) goto L46;
            if (r04.getSpanEnd(r4[0]) != r04.length()) goto L46;
            r05 = true;
            goto L47
        L34:
            if (r04.getSpanStart(r53[0]) != 0) goto L38;
            if (r04.getSpanEnd(r53[0]) != r04.length()) goto L38;
            r54 = true;
        L49:
            if (this.hasSingleQuote == false) goto L53;
            int r06 = AndroidUtilities.dp(32.0f);
        L51:
            int r16 = r36 - r06;
        L341:
            StaticLayout r07 = MessageObject.makeStaticLayout(r3, r35, r16, 1.0f, 0.0f, false);     // Catch: Exception -> L332
            if (r2 != null) goto L60;
        L94:
            float r342 = 32.0f;
            float r20 = 15.0f;
            float r362 = 0.0f;
        L96:
            if (this.hasSingleQuote == false) goto L100;
            int r42 = AndroidUtilities.dp(r342);
        L98:
            int r43 = r16 + r42;
        L103:
            r07.getLineCount();
            ArrayList r55 = new ArrayList();
            if ((r3 instanceof Spanned) == true) goto L106;
        L110:
            r55.add(new TextRange(0, r07.getText().length()));
        L111:
            int r62 = r55.size();
            this.hasCodeAtTop = false;
            this.hasCodeAtBottom = false;
            this.hasQuoteAtBottom = false;
            this.hasSingleQuote = false;
            StaticLayout r7 = r07;
            int r12 = 0;
            int r8 = r16;
        L113:
            if (r12 >= r55.size()) goto L331;
            TextLayoutBlock r13 = new TextLayoutBlock();
            TextRange r08 = (TextRange) r55.get(r12);
            r13.code = r08.code;
            r13.quote = r08.quote;
            boolean r14 = r08.collapse;
            r13.quoteCollapse = r14;
            if (r14 == false) goto L117;
            r13.messageObject = r2;
        L117:
            r13.index = r12;
            r13.start = r08.start;
            if (r12 != 0) goto L120;
            boolean r142 = r10;
        L121:
            r13.first = r142;
            if (r12 != (r55.size() - r10)) goto L124;
            boolean r143 = r10;
        L125:
            r13.last = r143;
            boolean r15 = r13.first;
            if (r15 == false) goto L128;
            this.hasCodeAtTop = r13.code;
        L128:
            if (r143 == 0) goto L130;
            this.hasQuoteAtBottom = r13.quote;
            this.hasCodeAtBottom = r13.code;
        L130:
            if (r15 == false) goto L135;
            if (r143 == 0) goto L135;
            if (r13.quote == false) goto L135;
            boolean r92 = r10;
        L136:
            this.hasSingleQuote = r92;
            if (r13.quote == false) goto L146;
            float r93 = 6.0f;
            if (r15 == false) goto L142;
            if (r143 == 0) goto L142;
            int r94 = AndroidUtilities.dp(6.0f);
            r13.padBottom = r94;
            r13.padTop = r94;
        L160:
            boolean r95 = r13.code;
            if (r95 == false) goto L170;
            int r144 = r08.end - r08.start;
            if (r144 <= 220) goto L167;
            com.exteragram.messenger.utils.ui.TextPaint r145 = Theme.chat_msgTextCode3Paint;
        L165:
            TextPaint r22 = r145;
        L172:
            if (r13.quote == false) goto L175;
            int r96 = AndroidUtilities.dp(r342);
        L174:
            int r97 = r43 - r96;
        L179:
            if (r62 == r10) goto L181;
            int r146 = r08.start;
            int r152 = r08.end;
            if (r152 >= r146) goto L197;
            CharSequence r17 = r3;
            ArrayList r23 = r55;
            boolean r25 = r11;
            MessageObject r56 = r2;
            int r112 = r10;
            float r24 = r362;
        L330:
            r12 = r12 + 1;
            r362 = r24;
            r2 = r56;
            r10 = r112;
            r3 = r17;
            r55 = r23;
            r11 = r25;
            r9 = false;
            goto L113
        L197:
            r13.charactersOffset = r146;
            r13.charactersEnd = r152;
            if (r13.code == true) goto L201;
        L206:
            SpannableString r102 = SpannableString.valueOf(r3.subSequence(r146, r152));     // Catch: Exception -> L204
        L203:
            SpannableString r21 = r102;
            r13.originalWidth = r97;     // Catch: Exception -> L204
            StaticLayout r98 = MessageObject.makeStaticLayout(r21, r22, r97, 1.0f, 0.0f, false);     // Catch: Exception -> L204
            r13.textLayout = r98;     // Catch: Exception -> L204
            r13.height = r98.getHeight();     // Catch: Exception -> L204
            r13.collapsedHeight = (int) Math.min((r35.getTextSize() * 1.4f) * 3.0f, r13.height);     // Catch: Exception -> L204
        L209:
            if (r13.code == true) goto L211;
        L215:
            this.textLayoutBlocks.add(r13);
            int r99 = r13.textLayout.getLineCount();
            float r09 = r13.textLayout.getLineLeft(r99 - 1);     // Catch: Exception -> L222
            if (r12 == 0) goto L219;
        L224:
            float r103 = r09;
        L355:
            float r010 = r13.textLayout.getLineWidth(r99 - 1);     // Catch: Exception -> L230
        L232:
            int r011 = (int) Math.ceil(r010);
            if (r011 <= (r43 + 80)) goto L235;
            r011 = r43;
        L235:
            int r147 = r62 - 1;
            if (r12 != r147) goto L238;
            this.lastLineWidth = r011;
        L238:
            float r153 = r011;
            r17 = r3;
            StaticLayout r18 = r7;
            int r212 = r8;
            int r32 = (int) Math.ceil(r153 + Math.max(0.0f, r103));
            if (r13.quote == false) goto L249;
            r13.maxRight = 0.0f;
            int r72 = 0;
        L241:
            if (r72 >= r99) goto L249;
            int r222 = r011;
            r13.maxRight = Math.max(r13.maxRight, r13.textLayout.getLineRight(r72));     // Catch: Exception -> L335
        L248:
            r72 = r72 + 1;
            r011 = r222;
        L247:
            r13.maxRight = this.textWidth;
        L246:
            r222 = r011;
        L249:
            int r223 = r011;
            if (r99 <= 1) goto L293;
            r23 = r55;
            int r012 = r223;
            boolean r73 = false;
            float r82 = 0.0f;
            float r104 = 0.0f;
            int r154 = 0;
            int r57 = r32;
        L252:
            if (r154 >= r99) goto L285;
            boolean r224 = r73;
            float r242 = r13.textLayout.getLineWidth(r154);     // Catch: Exception -> L256
        L258:
            if (r13.quote == false) goto L262;
            int r74 = AndroidUtilities.dp(r342);
        L260:
            r242 = r242 + r74;
        L337:
            float r75 = r13.textLayout.getLineLeft(r154);     // Catch: Exception -> L266
        L267:
            float r252 = r75;
            if (r242 <= (r43 + 20)) goto L271;
            float r76 = r43;
            int r243 = r99;
            float r910 = 0.0f;
        L272:
            boolean r253 = r11;
            if (r910 <= 0.0f) goto L275;
        L278:
            this.textXOffset = Math.min(this.textXOffset, r910);
            int r113 = 1;
            r13.directionFlags = (byte) (r13.directionFlags | 1);
            this.hasRtl = true;
        L279:
            if (r224 == true) goto L284;
            if (r910 != 0.0f) goto L284;
            if (r13.textLayout.getParagraphDirection(r154) != r113) goto L284;
        L283:
            r224 = true;
        L284:
            float r83 = Math.max(r82, r76);
            float r911 = r910 + r76;
            r104 = Math.max(r104, r911);
            r012 = Math.max(r012, (int) Math.ceil(r76));
            r57 = Math.max(r57, (int) Math.ceil(r911));
            r154 = r154 + 1;
            r82 = r83;
            r73 = r224;
            r99 = r243;
            r11 = r253;
            goto L252
        L275:
            if (r13.textLayout.getParagraphDirection(r154) == (-1)) goto L278;
            r13.directionFlags = (byte) (r13.directionFlags | 2);
            r113 = 1;
            goto L279
        L271:
            r76 = r242;
            r243 = r99;
            r910 = r252;
        L266:
            r75 = 0.0f;
            goto L267
        L262:
            if (r13.code == false) goto L337;
            r74 = AndroidUtilities.dp(r20);
        L256:
            r242 = 0.0f;
            goto L258
        L285:
            r25 = r11;
            if (r73 == false) goto L289;
            if (r12 != r147) goto L292;
            this.lastLineWidth = r32;
        L292:
            this.textWidth = Math.max(this.textWidth, (int) Math.ceil(r104));
            r32 = r57;
            r24 = 0.0f;
            r112 = 1;
        L306:
            Text r013 = r13.languageLayout;
            if (r013 == null) goto L313;
            float r58 = this.textWidth;
            float r014 = r013.getCurrentWidth() + AndroidUtilities.dp(r20);
            StaticLayout r77 = r13.textLayout;
            if (r77 != null) goto L311;
            float r105 = r24;
        L312:
            this.textWidth = (int) Math.max(r58, Math.min(r014, r105));
            goto L313
        L311:
            r105 = r77.getWidth();
        L313:
            r56 = r33;
            if (r33 != null) goto L316;
        L328:
            r7 = r18;
            r8 = r212;
            goto L330
        L316:
            if (r56.isSpoilersRevealed == true) goto L328;
            if (MessageObject.m4466$$Nest$fgetspoiledLoginCode(r56) == true) goto L328;
            if (r13.quote == false) goto L325;
            int r015 = AndroidUtilities.dp(r342);
        L322:
            r32 = r32 - r015;
        L327:
            SpoilerEffect.addSpoilers(null, r13.textLayout, -1, r32, null, r13.spoilers);
            goto L328
        L325:
            if (r13.code == false) goto L327;
            r015 = AndroidUtilities.dp(r20);
            goto L322
        L289:
            if (r12 != r147) goto L291;
            this.lastLineWidth = r012;
        L291:
            r104 = r82;
            goto L292
        L293:
            r23 = r55;
            r25 = r11;
            r24 = 0.0f;
            if (r103 <= 0.0f) goto L304;
            float r016 = Math.min(this.textXOffset, r103);
            this.textXOffset = r016;
            if (r016 != 0.0f) goto L299;
            int r017 = (int) (r153 + r103);
        L298:
            r112 = 1;
            if (r62 == 1) goto L302;
            boolean r78 = true;
        L303:
            this.hasRtl = r78;
            r13.directionFlags = (byte) (r13.directionFlags | 1);
        L305:
            this.textWidth = Math.max(this.textWidth, Math.min(r43, r017));
            goto L306
        L302:
            r78 = false;
            goto L303
        L299:
            r017 = r223;
            goto L298
        L304:
            r112 = 1;
            r13.directionFlags = (byte) (r13.directionFlags | 2);
            r017 = r223;
        L230:
            e = move-exception;
            FileLog.e(e);
            r010 = 0.0f;
            goto L232
        L219:
            if (r09 < r362) goto L224;
            this.textXOffset = r09;     // Catch: Exception -> L222
        L222:
            e = move-exception;
            if (r12 != 0) goto L227;
            this.textXOffset = r362;
        L227:
            FileLog.e(e);
            r103 = 0.0f;
            goto L355
        L211:
            if ((r13.textLayout.getText() instanceof Spannable) == false) goto L215;
            if (TextUtils.isEmpty(r08.language) == false) goto L215;
            CodeHighlighting.highlight((Spannable) r13.textLayout.getText(), 0, r13.textLayout.getText().length(), r08.language, 0, null, true);
            goto L215
        L201:
            if (r13.quote == true) goto L206;
            r102 = CodeHighlighting.getHighlighted(r3.subSequence(r146, r152).toString(), r08.language);     // Catch: Exception -> L204
        L204:
            e = move-exception;
            r17 = r3;
            r23 = r55;
            r25 = r11;
            r112 = 1;
            r56 = r2;
            r24 = r362;
            FileLog.e(e);
            goto L330
        L181:
            if (r13.code == true) goto L183;
        L192:
            r13.originalWidth = r8;
        L193:
            r13.textLayout = r7;
            r13.charactersOffset = 0;
            r13.charactersEnd = r7.getText().length();
            r13.height = r7.getHeight();
            r13.collapsedHeight = (int) Math.min((r35.getTextSize() * 1.4f) * 3.0f, r13.height);
            goto L209
        L183:
            if (r13.quote == true) goto L192;
            if ((r7.getText() instanceof Spannable) == false) goto L192;
            if (TextUtils.isEmpty(r08.language) == true) goto L190;
            SpannableString r79 = CodeHighlighting.getHighlighted(r3.subSequence(r08.start, r08.end).toString(), r08.language);
        L189:
            SpannableString r213 = r79;
            r13.originalWidth = r97;
            r7 = MessageObject.makeStaticLayout(r213, r22, r97, 1.0f, 0.0f, false);
            r8 = r97;
            goto L193
        L190:
            r79 = new SpannableString(r3.subSequence(r08.start, r08.end));
            goto L189
        L175:
            if (r95 == false) goto L177;
            r96 = AndroidUtilities.dp(r20);
            goto L174
        L177:
            r97 = r43;
            goto L179
        L167:
            if (r144 <= 80) goto L169;
            r145 = Theme.chat_msgTextCode2Paint;
            goto L165
        L169:
            r145 = Theme.chat_msgTextCodePaint;
            goto L165
        L170:
            r22 = r35;
        L142:
            if (r15 == false) goto L144;
            r93 = 8.0f;
        L144:
            r13.padTop = AndroidUtilities.dp(r93);
            r13.padBottom = AndroidUtilities.dp(7.0f);
            goto L160
        L146:
            if (r13.code == false) goto L160;
            r13.layoutCode(r08.language, r08.end - r08.start, r11);
            int r148 = AndroidUtilities.dp(4.0f) + r13.languageHeight;
            if (r13.first == false) goto L150;
            int r155 = 0;
        L151:
            r13.padTop = r148 + r155;
            int r912 = AndroidUtilities.dp(4.0f);
            if (r13.last == false) goto L154;
            int r149 = 0;
        L155:
            int r913 = r912 + r149;
            if (r13.hasCodeCopyButton == false) goto L158;
            int r1410 = AndroidUtilities.dp(38.0f);
        L159:
            r13.padBottom = r913 + r1410;
            goto L160
        L158:
            r1410 = 0;
            goto L159
        L154:
            r149 = AndroidUtilities.dp(7.0f);
            goto L155
        L150:
            r155 = AndroidUtilities.dp(5.0f);
        L135:
            r92 = 0;
            goto L136
        L124:
            r143 = r9;
            goto L125
        L120:
            r142 = r9;
            goto L121
        L331:
            return;
        L106:
            if (this.hasQuote == false) goto L108;
        L109:
            MessageObject.cutIntoRanges(r3, r55);
            goto L111
        L108:
            if (this.hasCode == false) goto L110;
        L100:
            if (this.hasSingleCode == false) goto L102;
            r42 = AndroidUtilities.dp(r20);
            goto L98
        L102:
            r43 = r16;
            goto L103
        L60:
            if (r2.isRepostPreview == false) goto L94;
            if (r2.type != 0) goto L64;
            int r59 = 22;
        L69:
            if (r2.isWebpage() == false) goto L72;
            r59 = r59 - 8;
        L72:
            if (r07.getLineCount() <= r59) goto L94;
            String r63 = LocaleController.getString(R.string.ReadMore);
            r342 = 32.0f;
            r20 = 15.0f;
            int r710 = (int) Math.ceil(r35.measureText("… " + r63) + AndroidUtilities.dp(1.0f));
            int r122 = 0;
            float r132 = 0.0f;
        L74:
            if (r122 >= r59) goto L76;
            r132 = Math.max(r132, r07.getLineRight(r122));
            r122 = r122 + 1;
            goto L74
        L76:
            int r510 = r59 - 1;
            int r123 = r07.getLineStart(r510);
            int r511 = r07.getLineEnd(r510) - 1;
        L77:
            if (r511 < r123) goto L82;
            r362 = 0.0f;
            if (r07.getPrimaryHorizontal(r511) < (r132 - r710)) goto L83;
            r511 = r511 - 1;
        L83:
            if (r511 < r123) goto L88;
            if (Character.isWhitespace(r3.charAt(r511)) == true) goto L88;
            r511 = r511 - 1;
        L88:
            SpannableStringBuilder r018 = new SpannableStringBuilder(r3.subSequence(0, r511)).append("… ").append(r63);
            r018.setSpan(new AnonymousClass1(this), r018.length() - r63.length(), r018.length(), 33);
            CharSequence r1411 = LocaleUtils.insertHexColorsPreview(r018);
            r07 = MessageObject.makeStaticLayout(r1411, r35, r16, 1.0f, 0.0f, false);     // Catch: Exception -> L91
            r3 = r1411;
        L91:
            e = move-exception;
            FileLog.e(e);
            return;
        L82:
            r362 = 0.0f;
            goto L83
        L64:
            if (r2.hasValidGroupId() == false) goto L66;
            r59 = 7;
            goto L69
        L66:
            r59 = 12;
        L332:
            e = move-exception;
            FileLog.e(e);
            return;
        L53:
            if (this.hasSingleCode == false) goto L55;
            r06 = AndroidUtilities.dp(15.0f);
            goto L51
        L55:
            r16 = r36;
            goto L341
        L22:
            if (((QuoteSpan.QuoteStyleSpan[]) ((Spanned) r3).getSpans(0, r3.length(), QuoteSpan.QuoteStyleSpan.class)).length <= 0) goto L24;
            r52 = true;
            goto L25
        L16:
            if (((CodeHighlighting.Span[]) ((Spanned) r3).getSpans(0, r3.length(), CodeHighlighting.Span.class)).length <= 0) goto L18;
            r5 = true;
        L9:
            r02 = false;
            goto L10
        }

        public void bounceFrom(TextLayoutBlocks r4) {
            if (r4 == null) goto L8;
            int r0 = 0;
        L6:
            if (r0 >= Math.min(this.textLayoutBlocks.size(), r4.textLayoutBlocks.size())) goto L10;
            TextLayoutBlock r1 = this.textLayoutBlocks.get(r0);
            r1.collapsedBounce = r4.textLayoutBlocks.get(r0).collapsedBounce;
            r0 = r0 + 1;
            goto L6
        L10:
            return;
        }

        public int textHeight() {
            int r0 = 0;
            int r1 = 0;
        L4:
            if (r0 >= this.textLayoutBlocks.size()) goto L6;
            r1 = r1 + ((this.textLayoutBlocks.get(r0).padTop + this.textLayoutBlocks.get(r0).height()) + this.textLayoutBlocks.get(r0).padBottom);
            r0 = r0 + 1;
            goto L4
        L6:
            return r1;
        }

        public int textHeight(ChatMessageCell.TransitionParams r5) {
            int r0 = 0;
            int r1 = 0;
        L4:
            if (r0 >= this.textLayoutBlocks.size()) goto L6;
            r1 = r1 + ((this.textLayoutBlocks.get(r0).padTop + this.textLayoutBlocks.get(r0).height(r5)) + this.textLayoutBlocks.get(r0).padBottom);
            r0 = r0 + 1;
            goto L4
        L6:
            return r1;
        }
    }

    /* loaded from: classes5.dex */
    public static class TextRange {
        public boolean code;
        public boolean collapse;
        public int end;
        public String language;
        public boolean quote;
        public int start;

        public TextRange(int r1, int r2) {
            this.start = r1;
            this.end = r2;
        }

        public TextRange(int r1, int r2, boolean r3, boolean r4, boolean r5, String r6) {
            this.start = r1;
            this.end = r2;
            this.quote = r3;
            this.code = r4;
            if (r3 == false) goto L6;
            if (r5 == false) goto L6;
            boolean r12 = true;
        L7:
            this.collapse = r12;
            this.language = r6;
            return;
        L6:
            r12 = false;
            goto L7
        }
    }

    /* loaded from: classes5.dex */
    public static class VCardData {
        private String company;
        private final ArrayList<String> emails;
        private final ArrayList<String> phones;

        public VCardData() {
            this.emails = new ArrayList();
            this.phones = new ArrayList();
        }

        public static CharSequence parse(String r17) {
            BufferedReader r0 = new BufferedReader(new StringReader(r17));     // Catch: Throwable -> L13
            int r2 = 0;
            boolean r3 = false;
            VCardData r4 = null;
            StringBuilder r5 = null;
        L3:
            String r6 = r0.readLine();     // Catch: Throwable -> L13
            if (r6 == null) goto L72;
            if (r6.startsWith("PHOTO") == true) goto L3;
            if (r6.indexOf(58) >= 0) goto L11;
        L18:
            if (r5 == null) goto L22;
            r5.append(r6);     // Catch: Throwable -> L13
            r6 = r5.toString();     // Catch: Throwable -> L13
            r5 = null;
        L22:
            if (r6.contains("=QUOTED-PRINTABLE") == false) goto L26;
            if (r6.endsWith("=") == false) goto L26;
            r5 = new StringBuilder(r6.substring(r2, r6.length() - 1));     // Catch: Throwable -> L13
        L26:
            int r7 = r6.indexOf(":");     // Catch: Throwable -> L13
            if (r7 < 0) goto L29;
            String[] r62 = {r6.substring(r2, r7), r6.substring(r7 + 1).trim()};     // Catch: Throwable -> L13
        L31:
            if (r62.length < 2) goto L33;
            if (r4 == null) goto L33;
            if (r62[r2].startsWith("ORG") == false) goto L61;
            String[] r72 = r62[r2].split(";");     // Catch: Throwable -> L13
            int r11 = r72.length;     // Catch: Throwable -> L13
            int r12 = r2;
            String r13 = null;
            String r14 = null;
        L37:
            if (r12 >= r11) goto L50;
            String[] r15 = r72[r12].split("=");     // Catch: Throwable -> L13
            CharSequence r16 = null;
            if (r15.length == 2) goto L43;
            int r172 = r2;
        L49:
            r12 = r12 + 1;     // Catch: Throwable -> L107
            r2 = r172;
            goto L37
        L43:
            r172 = r2;
            if (r15[r2].equals("CHARSET") == false) goto L47;
            r14 = r15[1];     // Catch: Throwable -> L107
            goto L49
        L47:
            if (r15[r172].equals("ENCODING") == false) goto L49;
            r13 = r15[1];     // Catch: Throwable -> L107
        L136:
            return r16;
        L50:
            int r173 = r2;
            r4.company = r62[1];     // Catch: Throwable -> L107
            if (r13 != null) goto L53;
        L60:
            r4.company = r4.company.replace(';', ' ');     // Catch: Throwable -> L107
        L71:
            r2 = r173;
            goto L3
        L53:
            if (r13.equalsIgnoreCase("QUOTED-PRINTABLE") == false) goto L60;
            byte[] r1 = AndroidUtilities.decodeQuotedPrintable(AndroidUtilities.getStringBytes(r4.company));     // Catch: Throwable -> L107
            if (r1 == null) goto L60;
            if (r1.length == 0) goto L60;
            if (r14 == null) goto L60;
            r4.company = new String(r1, r14);     // Catch: Throwable -> L107
            goto L60
        L61:
            r173 = r2;
            if (r62[r173].startsWith("TEL") == false) goto L67;
            if (r62[1].length() <= 0) goto L71;
            r4.phones.add(r62[1]);     // Catch: Throwable -> L107
            goto L71
        L67:
            if (r62[r173].startsWith("EMAIL") == false) goto L71;
            String r18 = r62[1];     // Catch: Throwable -> L107
            if (r18.length() <= 0) goto L71;
            r4.emails.add(r18);     // Catch: Throwable -> L107
        L33:
            r173 = r2;
            goto L71
        L29:
            r62 = new String[]{r6.trim()};     // Catch: Throwable -> L13
            goto L31
        L11:
            if (r6.startsWith("BEGIN:VCARD") == false) goto L15;
            r4 = new VCardData();     // Catch: Throwable -> L13
            goto L18
        L15:
            if (r6.startsWith("END:VCARD") == false) goto L18;
            if (r4 == null) goto L18;
            r3 = true;
            goto L18
        L72:
            int r174 = r2;
            r16 = null;
            r0.close();     // Catch: Exception -> L75 Throwable -> L107
        L77:
            if (r3 == false) goto L135;
            StringBuilder r02 = new StringBuilder();     // Catch: Throwable -> L107
            int r19 = r174;
        L80:
            if (r19 >= r4.phones.size()) goto L92;
            if (r02.length() <= 0) goto L84;
            r02.append('\n');     // Catch: Throwable -> L107
        L84:
            String r22 = r4.phones.get(r19);     // Catch: Throwable -> L107
            if (r22.contains("#") == false) goto L87;
        L90:
            r02.append(r22);     // Catch: Throwable -> L107
        L91:
            r19 = r19 + 1;     // Catch: Throwable -> L107
            goto L80
        L87:
            if (r22.contains("*") == true) goto L90;
            r02.append(PhoneFormat.getInstance().format(r22));     // Catch: Throwable -> L107
            goto L91
        L92:
            int r23 = r174;
        L94:
            if (r23 >= r4.emails.size()) goto L100;
            if (r02.length() <= 0) goto L98;
            r02.append('\n');     // Catch: Throwable -> L107
        L98:
            r02.append(PhoneFormat.getInstance().format(r4.emails.get(r23)));     // Catch: Throwable -> L107
            r23 = r23 + 1;     // Catch: Throwable -> L107
            goto L94
        L100:
            if (TextUtils.isEmpty(r4.company) == false) goto L102;
        L105:
            return r02;
        L102:
            if (r02.length() <= 0) goto L104;
            r02.append('\n');     // Catch: Throwable -> L107
        L104:
            r02.append(r4.company);     // Catch: Throwable -> L107
        L135:
            return null;
        L75:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L107
        L106:
            return null;
        }
    }

    public static /* synthetic */ void $r8$lambda$43mr_jA3X9o0u_d1GPN92_1pDAA(MessageObject r0, TLRPC.Document r1) {
        r0.lambda$loadAnimatedEmojiDocument$0(r1);
    }

    /* renamed from: $r8$lambda$feekP6Io28BAe6tHNGoa-7H94Us, reason: not valid java name */
    public static /* synthetic */ void m4465$r8$lambda$feekP6Io28BAe6tHNGoa7H94Us(MessageObject r0, TLRPC.Document r1) {
        r0.lambda$loadAnimatedEmojiDocument$1(r1);
    }

    public static /* synthetic */ int $r8$lambda$gmQO2dzPCHe8iMwdGhi6M6Eo3m8(String r0, String r1) {
        return r1.length() - r0.length();
    }

    public static /* synthetic */ int $r8$lambda$w1b4brUQc9jkaGF7tJdOKyazR9k(TLRPC.MessageEntity r0, TLRPC.MessageEntity r1) {
        int r02 = r0.offset;
        int r12 = r1.offset;
        if (r02 <= r12) goto L6;
        return 1;
    L6:
        if (r02 >= r12) goto L9;
        return -1;
    L9:
        return 0;
    }

    /* renamed from: -$$Nest$fgetspoiledLoginCode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4466$$Nest$fgetspoiledLoginCode(MessageObject r0) {
        return r0.spoiledLoginCode;
    }

    /* renamed from: -$$Nest$mneedDrawAvatarInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4467$$Nest$mneedDrawAvatarInternal(MessageObject r0) {
        return r0.needDrawAvatarInternal();
    }

    static {
        excludeWords = new String[]{" vs. ", " vs ", " versus ", " ft. ", " ft ", " featuring ", " feat. ", " feat ", " presents ", " pres. ", " pres ", " and ", " & ", " . "};
    }

    public MessageObject(int r16, TLRPC.Message r17, LongSparseArray<TLRPC.User> r18, LongSparseArray<TLRPC.Chat> r19, boolean r20, boolean r21) {
        this(r16, r17, null, null, null, r18, r19, r20, r21, 0, false, false, false);
    }

    public MessageObject(int r16, TLRPC.Message r17, LongSparseArray<TLRPC.User> r18, LongSparseArray<TLRPC.Chat> r19, boolean r20, boolean r21, boolean r22) {
        this(r16, r17, null, null, null, r18, r19, r20, r21, 0, false, false, r22);
    }

    public MessageObject(int r8, TLRPC.Message r9, LongSparseArray<TLRPC.User> r10, boolean r11, boolean r12) {
        this(r8, r9, r10, null, r11, r12);
    }

    public MessageObject(int r4, TLRPC.Message r5, String r6, String r7, String r8, boolean r9, boolean r10, boolean r11, boolean r12) {
        this.type = 1000;
        this.forceSeekTo = -1.0f;
        this.actionDeleteGroupEventId = -1;
        this.overrideLinkColor = -1;
        this.overrideLinkEmoji = -1;
        int r0 = 1;
        this.messageTrimmedToHighlightCut = true;
        this.topicIconDrawable = new Drawable[1];
        this.spoiledLoginCode = false;
        this.translated = false;
        this.summarized = false;
        if (r9 == false) goto L5;
        r0 = 2;
    L5:
        this.localType = r0;
        this.currentAccount = r4;
        this.localName = r7;
        this.localUserName = r8;
        this.messageText = r6;
        this.messageOwner = r5;
        this.localChannel = r10;
        this.localSupergroup = r11;
        this.localEdit = r12;
    }

    public MessageObject(int r10, TLRPC.Message r11, AbstractMap<Long, TLRPC.User> r12, AbstractMap<Long, TLRPC.Chat> r13, boolean r14, boolean r15) {
        this(r10, r11, r12, r13, r14, r15, 0);
    }

    public MessageObject(int r13, TLRPC.Message r14, AbstractMap<Long, TLRPC.User> r15, AbstractMap<Long, TLRPC.Chat> r16, boolean r17, boolean r18, long r19) {
        this(r13, r14, null, r15, r16, null, null, r17, r18, r19);
    }

    public MessageObject(int r8, TLRPC.Message r9, AbstractMap<Long, TLRPC.User> r10, boolean r11, boolean r12) {
        this(r8, r9, r10, null, r11, r12);
    }

    public MessageObject(int r16, TLRPC.Message r17, MessageObject r18, AbstractMap<Long, TLRPC.User> r19, AbstractMap<Long, TLRPC.Chat> r20, LongSparseArray<TLRPC.User> r21, LongSparseArray<TLRPC.Chat> r22, boolean r23, boolean r24, long r25) {
        this(r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, false, false, false);
    }

    public MessageObject(int r17, TLRPC.Message r18, MessageObject r19, AbstractMap<Long, TLRPC.User> r20, AbstractMap<Long, TLRPC.Chat> r21, LongSparseArray<TLRPC.User> r22, LongSparseArray<TLRPC.Chat> r23, boolean r24, boolean r25, long r26, boolean r28, boolean r29, boolean r30) {
        this(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r28, r29, r30, 0);
    }

    public MessageObject(int r18, TLRPC.Message r19, MessageObject r20, AbstractMap<Long, TLRPC.User> r21, AbstractMap<Long, TLRPC.Chat> r22, LongSparseArray<TLRPC.User> r23, LongSparseArray<TLRPC.Chat> r24, boolean r25, boolean r26, long r27, boolean r29, boolean r30, boolean r31, int r32) {
        this.type = 1000;
        this.forceSeekTo = -1.0f;
        this.actionDeleteGroupEventId = -1;
        this.overrideLinkColor = -1;
        this.overrideLinkEmoji = -1;
        this.messageTrimmedToHighlightCut = true;
        this.topicIconDrawable = new Drawable[1];
        this.spoiledLoginCode = false;
        this.translated = false;
        this.summarized = false;
        Theme.createCommonMessageResources();
        this.isRepostPreview = r29;
        this.isRepostVideoPreview = r30;
        if (r31 == false) goto L5;
    L8:
        boolean r5 = true;
    L9:
        this.isSaved = r5;
        this.searchType = r32;
        this.currentAccount = r18;
        this.messageOwner = r19;
        this.replyMessageObject = r20;
        this.eventId = r27;
        if (r19.out == false) goto L12;
    L14:
        boolean r52 = false;
    L15:
        this.wasUnread = r52;
        TLRPC.Message r1 = r19.replyMessage;
        if (r1 == null) goto L18;
        AbstractMap<Long, TLRPC.User> r9 = r21;
        AbstractMap<Long, TLRPC.Chat> r10 = r22;
        LongSparseArray<TLRPC.User> r11 = r23;
        LongSparseArray<TLRPC.Chat> r12 = r24;
        this.replyMessageObject = new MessageObject(r18, r1, null, r9, r10, r11, r12, false, r26, r27);
    L19:
        updateMessageText(r9, r10, r11, r12);
        setType();
        if (r25 == false) goto L22;
        updateTranslation(false);
    L22:
        measureInlineBotButtons();
        GregorianCalendar r13 = new GregorianCalendar();
        r13.setTimeInMillis(this.messageOwner.date * 1000);
        int r53 = r13.get(6);
        int r6 = r13.get(1);
        int r14 = r13.get(2);
        this.dateKey = String.format("%d_%02d_%02d", new Object[]{Integer.valueOf(r6), Integer.valueOf(r14), Integer.valueOf(r53)});
        this.dateKeyInt = ((r14 * 10000) + r6) + (r53 * DurationKt.NANOS_IN_MILLIS);
        this.monthKey = String.format("%d_%02d", new Object[]{Integer.valueOf(r6), Integer.valueOf(r14)});
        createMessageSendInfo();
        generateCaption();
        if (r25 == true) goto L25;
    L38:
        this.layoutCreated = r25;
        generateThumbs(false);
        if (r26 == false) goto L42;
        checkMediaExistance();
        return;
    L42:
        return;
    L25:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGame) == false) goto L27;
        com.exteragram.messenger.utils.ui.TextPaint r15 = Theme.chat_msgGameTextPaint;
    L29:
        if (allowsBigEmoji() == false) goto L31;
        int[] r54 = new int[1];
    L32:
        CharSequence r62 = Emoji.replaceEmoji(this.messageText, r15.getFontMetricsInt(), false, r54);
        this.messageText = r62;
        Spannable r16 = replaceAnimatedEmoji(r62, r15.getFontMetricsInt());
        this.messageText = r16;
        if (r54 != null) goto L35;
    L37:
        checkEmojiOnly(r54);
        checkBigAnimatedEmoji();
        setType();
        createPathThumb();
        goto L38
    L35:
        if (r54[0] <= 1) goto L37;
        replaceEmojiToLottieFrame(r16, r54);
        goto L37
    L31:
        r54 = null;
        goto L32
    L27:
        r15 = Theme.chat_msgTextPaint;
        goto L29
    L18:
        r9 = r21;
        r10 = r22;
        r11 = r23;
        r12 = r24;
        goto L19
    L12:
        if (r19.unread == false) goto L14;
        r52 = true;
        goto L15
    L5:
        if (getDialogId(r19) == UserConfig.getInstance(r18).getClientUserId()) goto L8;
        r5 = false;
        goto L9
    }

    public MessageObject(int r13, TLRPC.Message r14, MessageObject r15, boolean r16, boolean r17) {
        this(r13, r14, r15, null, null, null, null, r16, r17, 0);
    }

    public MessageObject(int r13, TLRPC.Message r14, boolean r15, boolean r16) {
        this(r13, r14, null, null, null, null, null, r15, r16, 0);
    }

    public MessageObject(int r36, TLRPC.TL_channelAdminLogEvent r37, ArrayList<MessageObject> r38, HashMap<String, ArrayList<MessageObject>> r39, TLRPC.Chat r40, int[] r41, boolean r42) {
        TLRPC.Chat r1 = r40;
        this.type = 1000;
        this.forceSeekTo = -1.0f;
        this.actionDeleteGroupEventId = -1;
        this.overrideLinkColor = -1;
        this.overrideLinkEmoji = -1;
        this.messageTrimmedToHighlightCut = true;
        this.topicIconDrawable = new Drawable[1];
        this.spoiledLoginCode = false;
        this.translated = false;
        this.summarized = false;
        this.currentEvent = r37;
        this.currentAccount = r36;
        if (r37.user_id <= 0) goto L5;
        TLRPC.User r9 = MessagesController.getInstance(r36).getUser(Long.valueOf(r37.user_id));
    L6:
        GregorianCalendar r3 = new GregorianCalendar();
        r3.setTimeInMillis(r37.date * 1000);
        int r4 = r3.get(6);
        int r12 = r3.get(1);
        int r32 = r3.get(2);
        this.dateKey = String.format("%d_%02d_%02d", new Object[]{Integer.valueOf(r12), Integer.valueOf(r32), Integer.valueOf(r4)});
        this.dateKeyInt = ((r32 * 1000) + r12) + (r4 * AppDataDirGuesser.PER_USER_RANGE);
        this.monthKey = String.format("%d_%02d", new Object[]{Integer.valueOf(r12), Integer.valueOf(r32)});
        TLRPC.TL_peerChannel r33 = new TLRPC.TL_peerChannel();
        r33.channel_id = r1.id;
        TLRPC.ChannelAdminLogEventAction r43 = r37.action;
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeTitle) == false) goto L14;
        String r34 = ((TLRPC.TL_channelAdminLogEventActionChangeTitle) r43).new_value;
        if (r1.megagroup == false) goto L11;
        this.messageText = replaceWithLink(LocaleController.formatString("EventLogEditedGroupTitle", R.string.EventLogEditedGroupTitle, new Object[]{r34}), "un1", r9);
    L12:
        TLRPC.TL_channelAdminLogEvent r7 = r37;
        String r25 = _UrlKt.FRAGMENT_ENCODE_SET;
    L1146:
        TLRPC.Message r2 = null;
    L437:
        ArrayList<TLRPC.MessageEntity> r6 = null;
    L1148:
        if (this.messageOwner != null) goto L1150;
        this.messageOwner = new TLRPC.TL_messageService();
    L1150:
        this.messageOwner.message = this.messageText.toString();
        this.messageOwner.from_id = new TLRPC.TL_peerUser();
        TLRPC.Message r35 = this.messageOwner;
        r35.from_id.user_id = r7.user_id;
        r35.date = r7.date;
        int r44 = r41[0];
        r41[0] = r44 + 1;
        r35.id = r44;
        this.eventId = r7.id;
        r35.out = false;
        r35.peer_id = new TLRPC.TL_peerChannel();
        TLRPC.Message r310 = this.messageOwner;
        r310.peer_id.channel_id = r1.id;
        r310.unread = false;
        MediaController r8 = MediaController.getInstance();
        this.isOutOwnerCached = null;
        if ((r2 instanceof TLRPC.TL_messageEmpty) == false) goto L1153;
        TLRPC.Message r311 = null;
    L1154:
        if (r311 == null) goto L1172;
        r311.out = false;
        r311.realId = r311.id;
        int r22 = r41[0];
        r41[0] = r22 + 1;
        r311.id = r22;
        r311.flags &= -32769;
        r311.dialog_id = -r1.id;
        if ((r7.action instanceof TLRPC.TL_channelAdminLogEventActionDeleteMessage) == false) goto L1158;
        int r13 = r311.date;
        r311.date = r7.date;
    L1159:
        MessageObject r26 = new MessageObject(this.currentAccount, r311, null, null, true, true, this.eventId);
        r26.realDate = r13;
        r26.currentEvent = r7;
        if (r26.contentType >= 0) goto L1162;
        ArrayList<MessageObject> r312 = r38;
        TLRPC.TL_channelAdminLogEvent r23 = r7;
        this.contentType = -1;
    L1169:
        if (r6 == null) goto L1171;
        r26.webPageDescriptionEntities = r6;
        int[] r62 = null;
        r26.linkDescription = null;
        r26.generateLinkDescription();
    L1174:
        if ((r23.action instanceof TLRPC.TL_channelAdminLogEventActionDeleteMessage) == false) goto L1177;
        return;
    L1177:
        if (this.contentType < 0) goto L1213;
        createDateArray(this.currentAccount, r23, r312, r39, r42);
        if (r42 == false) goto L1181;
        r312.add(0, this);
    L1183:
        if (this.messageText != null) goto L1186;
        this.messageText = r25;
    L1186:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGame) == false) goto L1188;
        com.exteragram.messenger.utils.ui.TextPaint r14 = Theme.chat_msgGameTextPaint;
    L1190:
        if (allowsBigEmoji() == false) goto L1192;
        int[] r24 = new int[1];
    L1193:
        CharSequence r313 = Emoji.replaceEmoji(this.messageText, r14.getFontMetricsInt(), false, r24);
        this.messageText = r313;
        Spannable r15 = replaceAnimatedEmoji(r313, r14.getFontMetricsInt());
        this.messageText = r15;
        if (r24 != null) goto L1196;
    L1198:
        checkEmojiOnly(r24);
        setType();
        measureInlineBotButtons();
        generateCaption();
        if (r8.isPlayingMessage(this) == false) goto L1201;
        MessageObject r16 = r8.getPlayingMessageObject();
        this.audioProgress = r16.audioProgress;
        this.audioProgressSec = r16.audioProgressSec;
    L1201:
        generateLayout(r9);
        this.layoutCreated = true;
        generateThumbs(false);
        checkMediaExistance();
        return;
    L1196:
        if (r24[0] <= 1) goto L1198;
        replaceEmojiToLottieFrame(r15, r24);
        goto L1198
    L1192:
        r24 = r62;
        goto L1193
    L1188:
        r14 = Theme.chat_msgTextPaint;
        goto L1190
    L1181:
        r312.add(r312.size() - 1, this);
        goto L1183
    L1213:
        return;
    L1171:
        r62 = null;
        goto L1174
    L1162:
        if (r8.isPlayingMessage(r26) == false) goto L1164;
        MessageObject r17 = r8.getPlayingMessageObject();
        r26.audioProgress = r17.audioProgress;
        r26.audioProgressSec = r17.audioProgressSec;
    L1164:
        r312 = r38;
        r23 = r7;
        createDateArray(this.currentAccount, r23, r312, r39, r42);
        if (r42 == false) goto L1167;
        r312.add(0, r26);
        goto L1169
    L1167:
        r312.add(r312.size() - 1, r26);
        goto L1169
    L1158:
        r13 = 0;
        goto L1159
    L1172:
        r312 = r38;
        r23 = r7;
        goto L1171
    L1153:
        r311 = r2;
        goto L1154
    L11:
        this.messageText = replaceWithLink(LocaleController.formatString("EventLogEditedChannelTitle", R.string.EventLogEditedChannelTitle, new Object[]{r34}), "un1", r9);
        goto L12
    L14:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangePhoto) == false) goto L32;
        TLRPC.TL_channelAdminLogEventActionChangePhoto r45 = (TLRPC.TL_channelAdminLogEventActionChangePhoto) r43;
        TLRPC.TL_messageService r314 = new TLRPC.TL_messageService();
        this.messageOwner = r314;
        if ((r45.new_photo instanceof TLRPC.TL_photoEmpty) == false) goto L21;
        r314.action = new TLRPC.TL_messageActionChatDeletePhoto();
        if (r1.megagroup == false) goto L20;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogRemovedWGroupPhoto), "un1", r9);
        goto L12
    L20:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogRemovedChannelPhoto), "un1", r9);
        goto L12
    L21:
        r314.action = new TLRPC.TL_messageActionChatEditPhoto();
        this.messageOwner.action.photo = r45.new_photo;
        if (r1.megagroup == false) goto L28;
        if (isVideoAvatar() == false) goto L26;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogEditedGroupVideo), "un1", r9);
        goto L12
    L26:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogEditedGroupPhoto), "un1", r9);
        goto L12
    L28:
        if (isVideoAvatar() == false) goto L30;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogEditedChannelVideo), "un1", r9);
        goto L12
    L30:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogEditedChannelPhoto), "un1", r9);
        goto L12
    L32:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantJoin) == false) goto L38;
        if (r1.megagroup == false) goto L36;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogGroupJoined), "un1", r9);
        goto L12
    L36:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogChannelJoined), "un1", r9);
        goto L12
    L38:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantLeave) == false) goto L44;
        TLRPC.TL_messageService r315 = new TLRPC.TL_messageService();
        this.messageOwner = r315;
        r315.action = new TLRPC.TL_messageActionChatDeleteUser();
        this.messageOwner.action.user_id = r37.user_id;
        if (r1.megagroup == false) goto L42;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogLeftGroup), "un1", r9);
        goto L12
    L42:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogLeftChannel), "un1", r9);
        goto L12
    L44:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantInvite) == false) goto L59;
        TLRPC.TL_messageService r316 = new TLRPC.TL_messageService();
        this.messageOwner = r316;
        r316.action = new TLRPC.TL_messageActionChatAddUser();
        long r317 = getPeerId(((TLRPC.TL_channelAdminLogEventActionParticipantInvite) r43).participant.peer);
        int r132 = this.currentAccount;
        if (r317 <= 0) goto L48;
        TLObject r10 = MessagesController.getInstance(r132).getUser(Long.valueOf(r317));
    L49:
        TLRPC.Peer r63 = this.messageOwner.from_id;
        if ((r63 instanceof TLRPC.TL_peerUser) == true) goto L52;
    L57:
        CharSequence r318 = replaceWithLink(LocaleController.getString(R.string.EventLogAdded), "un2", r10);
        this.messageText = r318;
        this.messageText = replaceWithLink(r318, "un1", r9);
        goto L12
    L52:
        if (r317 != r63.user_id) goto L57;
        if (r1.megagroup == false) goto L56;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogGroupJoined), "un1", r9);
        goto L12
    L56:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogChannelJoined), "un1", r9);
        goto L12
    L48:
        r10 = MessagesController.getInstance(r132).getChat(Long.valueOf(-r317));
        goto L49
    L59:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantToggleAdmin) == false) goto L61;
    L66:
        r7 = r37;
        r25 = _UrlKt.FRAGMENT_ENCODE_SET;
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantToggleAdmin) == false) goto L988;
        TLRPC.TL_channelAdminLogEventActionParticipantToggleAdmin r46 = (TLRPC.TL_channelAdminLogEventActionParticipantToggleAdmin) r43;
        TLRPC.ChannelParticipant r18 = r46.prev_participant;
        TLRPC.ChannelParticipant r319 = r46.new_participant;
    L989:
        TLRPC.TL_message r47 = new TLRPC.TL_message();
        this.messageOwner = r47;
        r47.realId = -1;
        long r48 = getPeerId(r18.peer);
        int r82 = this.currentAccount;
        if (r48 <= 0) goto L992;
        TLRPC.User r49 = MessagesController.getInstance(r82).getUser(Long.valueOf(r48));
    L994:
        if ((r18 instanceof TLRPC.TL_channelParticipantCreator) == false) goto L996;
    L998:
        TLRPC.TL_chatAdminRights r5 = r18.admin_rights;
        TLRPC.TL_chatAdminRights r64 = r319.admin_rights;
        if (r5 != null) goto L1001;
        r5 = new TLRPC.TL_chatAdminRights();
    L1001:
        if (r64 != null) goto L1004;
        r64 = new TLRPC.TL_chatAdminRights();
    L1004:
        if (r64.other == false) goto L1006;
        String r83 = LocaleController.getString(R.string.EventLogPromotedNoRights);
    L1007:
        StringBuilder r102 = new StringBuilder(String.format(r83, new Object[]{getUserName(r49, this.messageOwner.entities, r83.indexOf("%1$s"))}));
        r102.append("\n");
        if (TextUtils.equals(r18.rank, r319.rank) == true) goto L1014;
        if (TextUtils.isEmpty(r319.rank) == false) goto L1012;
        r102.append("\n- ");
        r102.append(LocaleController.getString(R.string.EventLogPromotedRemovedTitle));
        goto L1014
    L1012:
        r102.append("\n+ ");
        r102.append(LocaleController.formatString("EventLogPromotedTitle", R.string.EventLogPromotedTitle, new Object[]{r319.rank}));
    L1014:
        if (r5.change_info == r64.change_info) goto L1025;
        r102.append('\n');
        if (r64.change_info == false) goto L1018;
        char r19 = '+';
    L1019:
        r102.append(r19);
        r102.append(' ');
        r1 = r40;
        if (r1.megagroup == false) goto L1023;
        int r27 = R.string.EventLogPromotedChangeGroupInfo;
    L1024:
        r102.append(LocaleController.getString(r27));
    L1027:
        if (r1.megagroup == true) goto L1043;
        if (r5.post_messages == r64.post_messages) goto L1036;
        r102.append('\n');
        if (r64.post_messages == false) goto L1033;
        char r28 = '+';
    L1034:
        r102.append(r28);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedPostMessages));
        goto L1036
    L1033:
        r28 = '-';
    L1036:
        if (r5.edit_messages == r64.edit_messages) goto L1043;
        r102.append('\n');
        if (r64.edit_messages == false) goto L1040;
        char r29 = '+';
    L1041:
        r102.append(r29);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedEditMessages));
        goto L1043
    L1040:
        r29 = '-';
    L1043:
        if (r5.post_stories == r64.post_stories) goto L1050;
        r102.append('\n');
        if (r64.post_stories == false) goto L1047;
        char r210 = '+';
    L1048:
        r102.append(r210);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedPostStories));
        goto L1050
    L1047:
        r210 = '-';
    L1050:
        if (r5.edit_stories == r64.edit_stories) goto L1057;
        r102.append('\n');
        if (r64.edit_stories == false) goto L1054;
        char r211 = '+';
    L1055:
        r102.append(r211);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedEditStories));
        goto L1057
    L1054:
        r211 = '-';
    L1057:
        if (r5.delete_stories == r64.delete_stories) goto L1064;
        r102.append('\n');
        if (r64.delete_stories == false) goto L1061;
        char r212 = '+';
    L1062:
        r102.append(r212);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedDeleteStories));
        goto L1064
    L1061:
        r212 = '-';
    L1064:
        if (r5.delete_messages == r64.delete_messages) goto L1071;
        r102.append('\n');
        if (r64.delete_messages == false) goto L1068;
        char r213 = '+';
    L1069:
        r102.append(r213);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedDeleteMessages));
        goto L1071
    L1068:
        r213 = '-';
    L1071:
        if (r5.add_admins == r64.add_admins) goto L1078;
        r102.append('\n');
        if (r64.add_admins == false) goto L1075;
        char r214 = '+';
    L1076:
        r102.append(r214);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedAddAdmins));
        goto L1078
    L1075:
        r214 = '-';
    L1078:
        if (r5.anonymous == r64.anonymous) goto L1085;
        r102.append('\n');
        if (r64.anonymous == false) goto L1082;
        char r215 = '+';
    L1083:
        r102.append(r215);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedSendAnonymously));
        goto L1085
    L1082:
        r215 = '-';
    L1085:
        if (r1.megagroup == false) goto L1101;
        if (r5.ban_users == r64.ban_users) goto L1094;
        r102.append('\n');
        if (r64.ban_users == false) goto L1091;
        char r216 = '+';
    L1092:
        r102.append(r216);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedBanUsers));
        goto L1094
    L1091:
        r216 = '-';
    L1094:
        if (r5.manage_call == r64.manage_call) goto L1101;
        r102.append('\n');
        if (r64.manage_call == false) goto L1098;
        char r217 = '+';
    L1099:
        r102.append(r217);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedManageCall));
        goto L1101
    L1098:
        r217 = '-';
    L1101:
        if (r5.invite_users == r64.invite_users) goto L1108;
        r102.append('\n');
        if (r64.invite_users == false) goto L1105;
        char r218 = '+';
    L1106:
        r102.append(r218);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedAddUsers));
        goto L1108
    L1105:
        r218 = '-';
    L1108:
        if (r1.megagroup == false) goto L1131;
        if (r5.pin_messages == r64.pin_messages) goto L1117;
        r102.append('\n');
        if (r64.pin_messages == false) goto L1114;
        char r219 = '+';
    L1115:
        r102.append(r219);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedPinMessages));
        goto L1117
    L1114:
        r219 = '-';
    L1117:
        if (r5.manage_ranks == r64.manage_ranks) goto L1124;
        r102.append('\n');
        if (r64.manage_ranks == false) goto L1121;
        char r220 = '+';
    L1122:
        r102.append(r220);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedEditRanks));
        goto L1124
    L1121:
        r220 = '-';
    L1124:
        if (r5.manage_topics == r64.manage_topics) goto L1131;
        r102.append('\n');
        if (r64.manage_topics == false) goto L1128;
        char r221 = '+';
    L1129:
        r102.append(r221);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedManageTopics));
        goto L1131
    L1128:
        r221 = '-';
    L1131:
        if (r5.manage_direct_messages == r64.manage_direct_messages) goto L1138;
        r102.append('\n');
        if (r64.manage_direct_messages == false) goto L1135;
        char r222 = '+';
    L1136:
        r102.append(r222);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedManageDirect));
        goto L1138
    L1135:
        r222 = '-';
    L1138:
        if (r5.manage_welcome_messages == r64.manage_welcome_messages) goto L1144;
        r102.append('\n');
        if (r64.manage_welcome_messages == false) goto L1142;
        char r223 = '+';
    L1143:
        r102.append(r223);
        r102.append(' ');
        r102.append(LocaleController.getString(R.string.EventLogPromotedManageWelcomeMessages));
        goto L1144
    L1142:
        r223 = '-';
    L1144:
        StringBuilder r320 = r102;
    L1145:
        this.messageText = r320.toString();
        goto L1146
    L1023:
        r27 = R.string.EventLogPromotedChangeChannelInfo;
        goto L1024
    L1018:
        r19 = '-';
        goto L1019
    L1025:
        r1 = r40;
        goto L1027
    L1006:
        r83 = LocaleController.getString(R.string.EventLogPromoted);
        goto L1007
    L996:
        if ((r319 instanceof TLRPC.TL_channelParticipantCreator) == false) goto L998;
        String r110 = LocaleController.getString(R.string.EventLogChangedOwnership);
        r320 = new StringBuilder(String.format(r110, new Object[]{getUserName(r49, this.messageOwner.entities, r110.indexOf("%1$s"))}));
        r1 = r40;
        goto L1145
    L992:
        r49 = MessagesController.getInstance(r82).getUser(Long.valueOf(-r48));
        goto L994
    L988:
        TLRPC.TL_channelAdminLogEventActionParticipantToggleBan r410 = (TLRPC.TL_channelAdminLogEventActionParticipantToggleBan) r43;
        r18 = r410.prev_participant;
        r319 = r410.new_participant;
        goto L989
    L61:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantToggleBan) == false) goto L68;
        if ((((TLRPC.TL_channelAdminLogEventActionParticipantToggleBan) r43).prev_participant instanceof TLRPC.TL_channelParticipantAdmin) == false) goto L68;
        if ((((TLRPC.TL_channelAdminLogEventActionParticipantToggleBan) r43).new_participant instanceof TLRPC.TL_channelParticipant) == true) goto L66;
    L68:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionDefaultBannedRights) == false) goto L171;
        TLRPC.TL_channelAdminLogEventActionDefaultBannedRights r411 = (TLRPC.TL_channelAdminLogEventActionDefaultBannedRights) r43;
        TLRPC.TL_message r321 = new TLRPC.TL_message();
        this.messageOwner = r321;
        r321.realId = -1;
        TLRPC.TL_chatBannedRights r322 = r411.prev_banned_rights;
        TLRPC.TL_chatBannedRights r412 = r411.new_banned_rights;
        StringBuilder r65 = new StringBuilder(LocaleController.getString(R.string.EventLogDefaultPermissions));
        if (r322 != null) goto L72;
        r322 = new TLRPC.TL_chatBannedRights();
    L72:
        if (r412 != null) goto L75;
        r412 = new TLRPC.TL_chatBannedRights();
    L75:
        if (r322.send_messages == r412.send_messages) goto L81;
        r65.append("\n\n");
        if (r412.send_messages == true) goto L79;
        char r72 = '+';
    L80:
        r65.append(r72);
        r65.append(' ');
        r65.append(LocaleController.getString(R.string.EventLogRestrictedSendMessages));
        boolean r73 = true;
    L83:
        if (r322.send_stickers == r412.send_stickers) goto L85;
    L90:
        if (r73 == true) goto L92;
        r65.append('\n');
        r73 = true;
    L92:
        r65.append('\n');
        if (r412.send_stickers == true) goto L95;
        char r84 = '+';
    L96:
        r65.append(r84);
        r65.append(' ');
        r65.append(LocaleController.getString(R.string.EventLogRestrictedSendStickers));
    L98:
        if (r322.send_media == r412.send_media) goto L107;
        if (r73 == true) goto L101;
        r65.append('\n');
        r73 = true;
    L101:
        r65.append('\n');
        if (r412.send_media == true) goto L104;
        char r85 = '+';
    L105:
        r65.append(r85);
        r65.append(' ');
        r65.append(LocaleController.getString(R.string.EventLogRestrictedSendMedia));
        goto L107
    L104:
        r85 = '-';
    L107:
        if (r322.send_polls == r412.send_polls) goto L116;
        if (r73 == true) goto L110;
        r65.append('\n');
        r73 = true;
    L110:
        r65.append('\n');
        if (r412.send_polls == true) goto L113;
        char r86 = '+';
    L114:
        r65.append(r86);
        r65.append(' ');
        r65.append(LocaleController.getString(R.string.EventLogRestrictedSendPolls));
        goto L116
    L113:
        r86 = '-';
    L116:
        if (r322.embed_links == r412.embed_links) goto L125;
        if (r73 == true) goto L119;
        r65.append('\n');
        r73 = true;
    L119:
        r65.append('\n');
        if (r412.embed_links == true) goto L122;
        char r87 = '+';
    L123:
        r65.append(r87);
        r65.append(' ');
        r65.append(LocaleController.getString(R.string.EventLogRestrictedSendEmbed));
        goto L125
    L122:
        r87 = '-';
    L125:
        if (r322.change_info == r412.change_info) goto L134;
        if (r73 == true) goto L128;
        r65.append('\n');
        r73 = true;
    L128:
        r65.append('\n');
        if (r412.change_info == true) goto L131;
        char r88 = '+';
    L132:
        r65.append(r88);
        r65.append(' ');
        r65.append(LocaleController.getString(R.string.EventLogRestrictedChangeInfo));
        goto L134
    L131:
        r88 = '-';
    L134:
        if (r322.invite_users == r412.invite_users) goto L143;
        if (r73 == true) goto L137;
        r65.append('\n');
        r73 = true;
    L137:
        r65.append('\n');
        if (r412.invite_users == true) goto L140;
        char r89 = '+';
    L141:
        r65.append(r89);
        r65.append(' ');
        r65.append(LocaleController.getString(R.string.EventLogRestrictedInviteUsers));
        goto L143
    L140:
        r89 = '-';
    L143:
        if (r322.pin_messages == r412.pin_messages) goto L152;
        if (r73 == true) goto L146;
        r65.append('\n');
    L146:
        r65.append('\n');
        if (r412.pin_messages == true) goto L149;
        char r810 = '+';
    L150:
        r65.append(r810);
        r65.append(' ');
        r65.append(LocaleController.getString(R.string.EventLogRestrictedPinMessages));
        goto L152
    L149:
        r810 = '-';
    L152:
        if (r322.edit_rank == r412.edit_rank) goto L161;
        if (r73 == true) goto L155;
        r65.append('\n');
    L155:
        r65.append('\n');
        if (r412.edit_rank == true) goto L158;
        char r811 = '+';
    L159:
        r65.append(r811);
        r65.append(' ');
        r65.append(LocaleController.getString(R.string.EventLogRestrictedEditRank));
        goto L161
    L158:
        r811 = '-';
    L161:
        if (r322.send_reactions == r412.send_reactions) goto L169;
        if (r73 == true) goto L164;
        r65.append('\n');
    L164:
        r65.append('\n');
        if (r412.send_reactions == true) goto L167;
        char r323 = '+';
    L168:
        r65.append(r323);
        r65.append(' ');
        r65.append(LocaleController.getString(R.string.EventLogRestrictedSendReactions));
        goto L169
    L167:
        r323 = '-';
    L169:
        this.messageText = r65.toString();
        goto L12
    L95:
        r84 = '-';
        goto L96
    L85:
        if (r322.send_inline != r412.send_inline) goto L90;
        if (r322.send_gifs != r412.send_gifs) goto L90;
        if (r322.send_games == r412.send_games) goto L98;
    L79:
        r72 = '-';
        goto L80
    L81:
        r73 = false;
        goto L83
    L171:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantToggleBan) == false) goto L343;
        TLRPC.TL_channelAdminLogEventActionParticipantToggleBan r413 = (TLRPC.TL_channelAdminLogEventActionParticipantToggleBan) r43;
        TLRPC.TL_message r324 = new TLRPC.TL_message();
        this.messageOwner = r324;
        r324.realId = -1;
        long r66 = getPeerId(r413.prev_participant.peer);
        int r122 = this.currentAccount;
        if (r66 <= 0) goto L175;
        TLObject r325 = MessagesController.getInstance(r122).getUser(Long.valueOf(r66));
    L176:
        TLRPC.TL_chatBannedRights r67 = r413.prev_participant.banned_rights;
        TLRPC.TL_chatBannedRights r414 = r413.new_participant.banned_rights;
        if (r1.megagroup == false) goto L185;
        if (r414 != null) goto L180;
    L186:
        if (r414 != null) goto L188;
    L212:
        TLRPC.TL_chatBannedRights r172 = r67;
        r25 = _UrlKt.FRAGMENT_ENCODE_SET;
        StringBuilder r74 = new StringBuilder(LocaleController.getString(R.string.UserRestrictionsUntilForever));
    L213:
        String r111 = LocaleController.getString(R.string.EventLogRestrictedUntil);
        StringBuilder r52 = new StringBuilder(String.format(r111, new Object[]{getUserName(r325, this.messageOwner.entities, r111.indexOf("%1$s")), r74}));
        if (r172 != null) goto L216;
        TLRPC.TL_chatBannedRights r68 = new TLRPC.TL_chatBannedRights();
    L217:
        if (r414 != null) goto L220;
        r414 = new TLRPC.TL_chatBannedRights();
    L220:
        if (r68.view_messages == r414.view_messages) goto L226;
        r52.append("\n\n");
        if (r414.view_messages == true) goto L224;
        char r112 = '+';
    L225:
        r52.append(r112);
        r52.append(' ');
        r52.append(LocaleController.getString(R.string.EventLogRestrictedReadMessages));
        boolean r152 = true;
    L228:
        if (r68.send_messages == r414.send_messages) goto L238;
        if (r152 == true) goto L232;
        r52.append('\n');
        r152 = true;
    L232:
        r52.append('\n');
        if (r414.send_messages == true) goto L235;
        char r113 = '+';
    L236:
        r52.append(r113);
        r52.append(' ');
        r52.append(LocaleController.getString(R.string.EventLogRestrictedSendMessages));
        goto L238
    L235:
        r113 = '-';
    L238:
        if (r68.send_stickers != r414.send_stickers) goto L246;
        if (r68.send_inline != r414.send_inline) goto L246;
        if (r68.send_gifs != r414.send_gifs) goto L246;
        if (r68.send_games != r414.send_games) goto L246;
    L254:
        if (r68.send_media == r414.send_media) goto L264;
        if (r152 == true) goto L258;
        r52.append('\n');
        r152 = true;
    L258:
        r52.append('\n');
        if (r414.send_media == true) goto L261;
        char r114 = '+';
    L262:
        r52.append(r114);
        r52.append(' ');
        r52.append(LocaleController.getString(R.string.EventLogRestrictedSendMedia));
        goto L264
    L261:
        r114 = '-';
    L264:
        if (r68.send_polls == r414.send_polls) goto L274;
        if (r152 == true) goto L268;
        r52.append('\n');
        r152 = true;
    L268:
        r52.append('\n');
        if (r414.send_polls == true) goto L271;
        char r115 = '+';
    L272:
        r52.append(r115);
        r52.append(' ');
        r52.append(LocaleController.getString(R.string.EventLogRestrictedSendPolls));
        goto L274
    L271:
        r115 = '-';
    L274:
        if (r68.embed_links == r414.embed_links) goto L284;
        if (r152 == true) goto L278;
        r52.append('\n');
        r152 = true;
    L278:
        r52.append('\n');
        if (r414.embed_links == true) goto L281;
        char r116 = '+';
    L282:
        r52.append(r116);
        r52.append(' ');
        r52.append(LocaleController.getString(R.string.EventLogRestrictedSendEmbed));
        goto L284
    L281:
        r116 = '-';
    L284:
        if (r68.change_info == r414.change_info) goto L294;
        if (r152 == true) goto L288;
        r52.append('\n');
        r152 = true;
    L288:
        r52.append('\n');
        if (r414.change_info == true) goto L291;
        char r117 = '+';
    L292:
        r52.append(r117);
        r52.append(' ');
        r52.append(LocaleController.getString(R.string.EventLogRestrictedChangeInfo));
        goto L294
    L291:
        r117 = '-';
    L294:
        if (r68.invite_users == r414.invite_users) goto L304;
        if (r152 == true) goto L298;
        r52.append('\n');
        r152 = true;
    L298:
        r52.append('\n');
        if (r414.invite_users == true) goto L301;
        char r118 = '+';
    L302:
        r52.append(r118);
        r52.append(' ');
        r52.append(LocaleController.getString(R.string.EventLogRestrictedInviteUsers));
        goto L304
    L301:
        r118 = '-';
    L304:
        if (r68.pin_messages == r414.pin_messages) goto L314;
        if (r152 == true) goto L308;
        r52.append('\n');
    L308:
        r52.append('\n');
        if (r414.pin_messages == true) goto L311;
        char r119 = '+';
    L312:
        r52.append(r119);
        r52.append(' ');
        r52.append(LocaleController.getString(R.string.EventLogRestrictedPinMessages));
        goto L314
    L311:
        r119 = '-';
    L314:
        if (r68.edit_rank == r414.edit_rank) goto L324;
        if (r152 == true) goto L318;
        r52.append('\n');
    L318:
        r52.append('\n');
        if (r414.edit_rank == true) goto L321;
        char r120 = '+';
    L322:
        r52.append(r120);
        r52.append(' ');
        r52.append(LocaleController.getString(R.string.EventLogRestrictedEditRank));
        goto L324
    L321:
        r120 = '-';
    L324:
        if (r68.send_reactions != r414.send_reactions) goto L326;
    L333:
        this.messageText = r52.toString();
    L341:
        r7 = r37;
    L342:
        r1 = r40;
        goto L1146
    L326:
        if (r152 == true) goto L328;
        r52.append('\n');
    L328:
        r52.append('\n');
        if (r414.send_reactions == true) goto L331;
        char r121 = '+';
    L332:
        r52.append(r121);
        r52.append(' ');
        r52.append(LocaleController.getString(R.string.EventLogRestrictedSendReactions));
        goto L333
    L331:
        r121 = '-';
    L246:
        if (r152 == true) goto L248;
        r52.append('\n');
        r152 = true;
    L248:
        r52.append('\n');
        if (r414.send_stickers == true) goto L251;
        char r123 = '+';
    L252:
        r52.append(r123);
        r52.append(' ');
        r52.append(LocaleController.getString(R.string.EventLogRestrictedSendStickers));
        goto L254
    L251:
        r123 = '-';
        goto L252
    L224:
        r112 = '-';
        goto L225
    L226:
        r152 = false;
        goto L228
    L216:
        r68 = r172;
        goto L217
    L188:
        if (AndroidUtilities.isBannedForever(r414) == true) goto L212;
        r74 = new StringBuilder();
        int r124 = r414.until_date - r37.date;
        int r142 = ((r124 / 60) / 60) / 24;
        int r125 = r124 - (r142 * 86400);
        r172 = r67;
        int r69 = (r125 / 60) / 60;
        int r126 = (r125 - (r69 * 3600)) / 60;
        r25 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r11 = 0;
        int r162 = 0;
    L191:
        if (r11 >= 3) goto L213;
        if (r11 != 0) goto L197;
        if (r142 == 0) goto L202;
        String r127 = LocaleController.formatPluralString("Days", r142, new Object[0]);
    L195:
        String r224 = r127;
        int r128 = r162 + 1;
    L203:
        if (r224 == null) goto L209;
        if (r74.length() <= 0) goto L207;
        r74.append(", ");
    L207:
        r74.append(r224);
    L209:
        if (r128 == 2) goto L213;
        r11 = r11 + 1;
        r162 = r128;
    L202:
        r128 = r162;
        r224 = null;
        goto L203
    L197:
        if (r11 != 1) goto L200;
        if (r69 == 0) goto L202;
        r127 = LocaleController.formatPluralString("Hours", r69, new Object[0]);
        goto L195
    L200:
        if (r126 == 0) goto L202;
        r127 = LocaleController.formatPluralString("Minutes", r126, new Object[0]);
        goto L195
    L180:
        if (r414.view_messages == false) goto L186;
        if (r67 == null) goto L185;
        if (r414.until_date != r67.until_date) goto L186;
    L185:
        r25 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r414 == null) goto L339;
        if (r67 != null) goto L337;
    L338:
        String r129 = LocaleController.getString(R.string.EventLogChannelRestricted);
    L340:
        this.messageText = String.format(r129, new Object[]{getUserName(r325, this.messageOwner.entities, r129.indexOf("%1$s"))});
        goto L341
    L337:
        if (r414.view_messages == true) goto L338;
    L339:
        r129 = LocaleController.getString(R.string.EventLogChannelUnrestricted);
        goto L340
    L175:
        r325 = MessagesController.getInstance(r122).getChat(Long.valueOf(-r66));
        goto L176
    L343:
        r25 = _UrlKt.FRAGMENT_ENCODE_SET;
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionUpdatePinned) == false) goto L369;
        TLRPC.TL_channelAdminLogEventActionUpdatePinned r415 = (TLRPC.TL_channelAdminLogEventActionUpdatePinned) r43;
        TLRPC.Message r130 = r415.message;
        if (r9 == null) goto L361;
        if (r9.id != 136817688) goto L361;
        TLRPC.MessageFwdHeader r225 = r130.fwd_from;
        if (r225 == null) goto L361;
        if ((r225.from_id instanceof TLRPC.TL_peerChannel) == false) goto L361;
        TLRPC.Chat r226 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r415.message.fwd_from.from_id.channel_id));
        TLRPC.Message r326 = r415.message;
        if ((r326 instanceof TLRPC.TL_messageEmpty) == false) goto L356;
    L359:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogUnpinnedMessages), "un1", r226);
    L367:
        r7 = r37;
        r2 = r130;
        r6 = null;
        r1 = r40;
        goto L1148
    L356:
        if (r326.pinned == false) goto L359;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogPinnedMessages), "un1", r226);
    L361:
        if ((r130 instanceof TLRPC.TL_messageEmpty) == false) goto L363;
    L366:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogUnpinnedMessages), "un1", r9);
        goto L367
    L363:
        if (r130.pinned == false) goto L366;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogPinnedMessages), "un1", r9);
        goto L367
    L369:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionStopPoll) == false) goto L377;
        r130 = ((TLRPC.TL_channelAdminLogEventActionStopPoll) r43).message;
        if ((getMedia(r130) instanceof TLRPC.TL_messageMediaPoll) == true) goto L373;
    L375:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogStopPoll), "un1", r9);
        goto L367
    L373:
        if (((TLRPC.TL_messageMediaPoll) getMedia(r130)).poll.quiz == false) goto L375;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogStopQuiz), "un1", r9);
        goto L367
    L377:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionToggleSignatures) == false) goto L383;
        if (((TLRPC.TL_channelAdminLogEventActionToggleSignatures) r43).new_value == false) goto L381;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogToggledSignaturesOn), "un1", r9);
        goto L341
    L381:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogToggledSignaturesOff), "un1", r9);
        goto L341
    L383:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantSubExtend) == false) goto L386;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogSubExtend), "un1", r9);
        goto L341
    L386:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionToggleSignatureProfiles) == false) goto L392;
        if (((TLRPC.TL_channelAdminLogEventActionToggleSignatureProfiles) r43).value == false) goto L390;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogToggledSignaturesProfilesOn), "un1", r9);
        goto L341
    L390:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogToggledSignaturesProfilesOff), "un1", r9);
        goto L341
    L392:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionToggleInvites) == false) goto L398;
        if (((TLRPC.TL_channelAdminLogEventActionToggleInvites) r43).new_value == false) goto L396;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogToggledInvitesOn), "un1", r9);
        goto L341
    L396:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogToggledInvitesOff), "un1", r9);
        goto L341
    L398:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionDeleteMessage) == false) goto L406;
        r130 = ((TLRPC.TL_channelAdminLogEventActionDeleteMessage) r43).message;
        if (r9 != null) goto L402;
    L404:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogDeletedMessages), "un1", r9);
        goto L367
    L402:
        if (r9.id != MessagesController.getInstance(this.currentAccount).telegramAntispamUserId) goto L404;
        this.messageText = LocaleController.getString(R.string.EventLogDeletedMessages).replace("un1", UserObject.getUserName(r9));
        goto L367
    L406:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeLinkedChat) == false) goto L418;
        long r131 = ((TLRPC.TL_channelAdminLogEventActionChangeLinkedChat) r43).new_value;
        long r327 = ((TLRPC.TL_channelAdminLogEventActionChangeLinkedChat) r43).prev_value;
        TLRPC.Chat r610 = r40;
        if (r610.megagroup == false) goto L413;
        int r75 = this.currentAccount;
        if (r131 != 0) goto L412;
        TLRPC.Chat r133 = MessagesController.getInstance(r75).getChat(Long.valueOf(r327));
        CharSequence r227 = replaceWithLink(LocaleController.getString(R.string.EventLogRemovedLinkedChannel), "un1", r9);
        this.messageText = r227;
        this.messageText = replaceWithLink(r227, "un2", r133);
    L417:
        r7 = r37;
        r1 = r610;
        goto L1146
    L412:
        TLRPC.Chat r134 = MessagesController.getInstance(r75).getChat(Long.valueOf(r131));
        CharSequence r228 = replaceWithLink(LocaleController.getString(R.string.EventLogChangedLinkedChannel), "un1", r9);
        this.messageText = r228;
        this.messageText = replaceWithLink(r228, "un2", r134);
        goto L417
    L413:
        int r76 = this.currentAccount;
        if (r131 != 0) goto L416;
        TLRPC.Chat r135 = MessagesController.getInstance(r76).getChat(Long.valueOf(r327));
        CharSequence r229 = replaceWithLink(LocaleController.getString(R.string.EventLogRemovedLinkedGroup), "un1", r9);
        this.messageText = r229;
        this.messageText = replaceWithLink(r229, "un2", r135);
        goto L417
    L416:
        TLRPC.Chat r136 = MessagesController.getInstance(r76).getChat(Long.valueOf(r131));
        CharSequence r230 = replaceWithLink(LocaleController.getString(R.string.EventLogChangedLinkedGroup), "un1", r9);
        this.messageText = r230;
        this.messageText = replaceWithLink(r230, "un2", r136);
        goto L417
    L418:
        r610 = r40;
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionTogglePreHistoryHidden) == false) goto L425;
        if (((TLRPC.TL_channelAdminLogEventActionTogglePreHistoryHidden) r43).new_value == false) goto L423;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogToggledInvitesHistoryOff), "un1", r9);
        goto L417
    L423:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogToggledInvitesHistoryOn), "un1", r9);
        goto L417
    L425:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeAbout) == true) goto L427;
        r7 = r37;
        String r812 = r25;
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeTheme) == false) goto L450;
        if (r610.megagroup == false) goto L444;
        int r137 = R.string.EventLogEditedGroupTheme;
    L445:
        this.messageText = replaceWithLink(LocaleController.getString(r137), "un1", r9);
        TLRPC.Message r138 = new TLRPC.TL_message();
        r138.out = false;
        r138.unread = false;
        TLRPC.TL_peerUser r231 = new TLRPC.TL_peerUser();
        r138.from_id = r231;
        r231.user_id = r7.user_id;
        r138.peer_id = r33;
        r138.date = r7.date;
        TLRPC.ChannelAdminLogEventAction r232 = r7.action;
        r138.message = ((TLRPC.TL_channelAdminLogEventActionChangeTheme) r232).new_value;
        if (TextUtils.isEmpty(((TLRPC.TL_channelAdminLogEventActionChangeTheme) r232).prev_value) == true) goto L448;
        TLRPC.TL_messageMediaWebPage r233 = new TLRPC.TL_messageMediaWebPage();
        r138.media = r233;
        r233.webpage = new TLRPC.TL_webPage();
        TLRPC.WebPage r234 = r138.media.webpage;
        r234.flags = 10;
        r234.display_url = r812;
        r234.url = r812;
        r234.site_name = LocaleController.getString(R.string.EventLogPreviousGroupTheme);
        r138.media.webpage.description = ((TLRPC.TL_channelAdminLogEventActionChangeTheme) r7.action).prev_value;
    L435:
        r2 = r138;
    L436:
        r1 = r610;
        r25 = r812;
        goto L437
    L448:
        r138.media = new TLRPC.TL_messageMediaEmpty();
        goto L435
    L444:
        r137 = R.string.EventLogEditedChannelTheme;
        goto L445
    L450:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeUsername) == false) goto L472;
        String r139 = ((TLRPC.TL_channelAdminLogEventActionChangeUsername) r43).new_value;
        boolean r235 = TextUtils.isEmpty(r139);
        boolean r416 = r610.megagroup;
        if (r235 == true) goto L458;
        if (r416 == false) goto L456;
        int r236 = R.string.EventLogChangedGroupLink;
    L457:
        this.messageText = replaceWithLink(LocaleController.getString(r236), "un1", r9);
    L463:
        r2 = new TLRPC.TL_message();
        r2.out = false;
        r2.unread = false;
        TLRPC.TL_peerUser r417 = new TLRPC.TL_peerUser();
        r2.from_id = r417;
        r417.user_id = r7.user_id;
        r2.peer_id = r33;
        r2.date = r7.date;
        if (TextUtils.isEmpty(r139) == true) goto L466;
        r2.message = "https://" + MessagesController.getInstance(this.currentAccount).linkPrefix + "/" + r139;
    L467:
        TLRPC.TL_messageEntityUrl r140 = new TLRPC.TL_messageEntityUrl();
        r140.offset = 0;
        r140.length = r2.message.length();
        r2.entities.add(r140);
        if (TextUtils.isEmpty(((TLRPC.TL_channelAdminLogEventActionChangeUsername) r7.action).prev_value) == true) goto L470;
        TLRPC.TL_messageMediaWebPage r141 = new TLRPC.TL_messageMediaWebPage();
        r2.media = r141;
        r141.webpage = new TLRPC.TL_webPage();
        TLRPC.WebPage r143 = r2.media.webpage;
        r143.flags = 10;
        r143.display_url = r812;
        r143.url = r812;
        r143.site_name = LocaleController.getString(R.string.EventLogPreviousLink);
        r2.media.webpage.description = "https://" + MessagesController.getInstance(this.currentAccount).linkPrefix + "/" + ((TLRPC.TL_channelAdminLogEventActionChangeUsername) r7.action).prev_value;
        goto L436
    L470:
        r2.media = new TLRPC.TL_messageMediaEmpty();
        goto L436
    L466:
        r2.message = r812;
        goto L467
    L456:
        r236 = R.string.EventLogChangedChannelLink;
        goto L457
    L458:
        if (r416 == false) goto L461;
        int r237 = R.string.EventLogRemovedGroupLink;
    L462:
        this.messageText = replaceWithLink(LocaleController.getString(r237), "un1", r9);
        goto L463
    L461:
        r237 = R.string.EventLogRemovedChannelLink;
        goto L462
    L472:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionEditMessage) == false) goto L537;
        TLRPC.Message r144 = new TLRPC.TL_message();
        r144.out = false;
        r144.unread = false;
        r144.peer_id = r33;
        r144.date = r7.date;
        TLRPC.ChannelAdminLogEventAction r238 = r7.action;
        TLRPC.Message r328 = ((TLRPC.TL_channelAdminLogEventActionEditMessage) r238).new_message;
        TLRPC.Message r239 = ((TLRPC.TL_channelAdminLogEventActionEditMessage) r238).prev_message;
        if (r239 == null) goto L476;
        r144.reply_to = r239.reply_to;
        r144.id = r239.id;
    L478:
        if (r328 == null) goto L482;
        TLRPC.Peer r418 = r328.from_id;
        if (r418 == null) goto L482;
        r144.from_id = r418;
    L484:
        if (getMedia(r328) != null) goto L486;
    L524:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogEditedMessages), "un1", r9);
        if ((r328.action instanceof TLRPC.TL_messageActionGroupCall) == false) goto L528;
        r328.media = new TLRPC.TL_messageMediaEmpty();
        r144 = r328;
    L527:
        ArrayList<TLRPC.MessageEntity> r240 = null;
    L532:
        r144.reply_markup = r328.reply_markup;
        TLRPC.WebPage r329 = r144.media.webpage;
        if (r329 == null) goto L535;
        r329.flags = 10;
        r329.display_url = r812;
        r329.url = r812;
    L535:
        ArrayList<TLRPC.MessageEntity> r252 = r240;
        r2 = r144;
        r1 = r610;
        r6 = r252;
        r25 = r812;
        goto L1148
    L528:
        r144.message = r328.message;
        r144.entities = r328.entities;
        TLRPC.TL_messageMediaWebPage r419 = new TLRPC.TL_messageMediaWebPage();
        r144.media = r419;
        r419.webpage = new TLRPC.TL_webPage();
        r144.media.webpage.site_name = LocaleController.getString(R.string.EventLogOriginalMessages);
        boolean r420 = TextUtils.isEmpty(r239.message);
        TLRPC.MessageMedia r53 = r144.media;
        if (r420 == false) goto L531;
        r53.webpage.description = LocaleController.getString(R.string.EventLogOriginalCaptionEmpty);
        goto L527
    L531:
        r53.webpage.description = r239.message;
        r240 = r239.entities;
        goto L532
    L486:
        if ((getMedia(r328) instanceof TLRPC.TL_messageMediaEmpty) == true) goto L524;
        if ((getMedia(r328) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L524;
        boolean r421 = TextUtils.equals(r328.message, r239.message);
        TLRPC.MessageMedia r54 = getMedia(r328);
        TLRPC.MessageMedia r103 = getMedia(r239);
        if (r103 != null) goto L493;
        boolean r55 = false;
        boolean r153 = true;
    L510:
        if (r153 == false) goto L512;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogAddedMedia), "un1", r9);
    L518:
        TLRPC.MessageMedia r56 = getMedia(r328);
        r144.media = r56;
        if (r421 == true) goto L527;
        r56.webpage = new TLRPC.TL_webPage();
        r144.media.webpage.site_name = LocaleController.getString(R.string.EventLogOriginalCaption);
        boolean r422 = TextUtils.isEmpty(r239.message);
        TLRPC.MessageMedia r57 = r144.media;
        if (r422 == false) goto L523;
        r57.webpage.description = LocaleController.getString(R.string.EventLogOriginalCaptionEmpty);
        goto L527
    L523:
        r57.webpage.description = r239.message;
        r240 = r239.entities;
        goto L532
    L512:
        if (r55 == false) goto L515;
        if (r421 == true) goto L515;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogEditedMediaCaption), "un1", r9);
    L515:
        if (r421 == true) goto L517;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogEditedCaption), "un1", r9);
        goto L518
    L517:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogEditedMedia), "un1", r9);
        goto L518
    L493:
        if (r54.getClass() != r103.getClass()) goto L509;
        TLRPC.Photo r1110 = r54.photo;
        if (r1110 == null) goto L501;
        TLRPC.Photo r1310 = r103.photo;
        if (r1310 == null) goto L501;
        if (r1110.id != r1310.id) goto L509;
    L501:
        if (r54.document != null) goto L503;
    L507:
        r55 = false;
    L508:
        r153 = false;
        goto L510
    L503:
        if (r103.document == null) goto L507;
        if (getMedia(r328).document.id == r103.document.id) goto L507;
    L509:
        r55 = true;
    L482:
        TLRPC.TL_peerUser r423 = new TLRPC.TL_peerUser();
        r144.from_id = r423;
        r423.user_id = r7.user_id;
        goto L484
    L476:
        if (r328 == null) goto L478;
        r144.reply_to = r328.reply_to;
        r144.id = r328.id;
        goto L478
    L537:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeEmojiStickerSet) == false) goto L547;
        TLRPC.InputStickerSet r145 = ((TLRPC.TL_channelAdminLogEventActionChangeEmojiStickerSet) r43).new_stickerset;
        TLRPC.InputStickerSet r241 = ((TLRPC.TL_channelAdminLogEventActionChangeEmojiStickerSet) r43).new_stickerset;
        if (r145 != null) goto L541;
    L544:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogRemovedEmojiPack), "un1", r9);
    L545:
        r1 = r610;
        r25 = r812;
        goto L1146
    L541:
        if ((r145 instanceof TLRPC.TL_inputStickerSetEmpty) == true) goto L544;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogChangedEmojiPack), "un1", r9);
        goto L545
    L547:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeStickerSet) == false) goto L556;
        TLRPC.InputStickerSet r146 = ((TLRPC.TL_channelAdminLogEventActionChangeStickerSet) r43).new_stickerset;
        if (r146 != null) goto L551;
    L554:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogRemovedStickersSet), "un1", r9);
        goto L545
    L551:
        if ((r146 instanceof TLRPC.TL_inputStickerSetEmpty) == true) goto L554;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogChangedStickersSet), "un1", r9);
        goto L545
    L556:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeLocation) == false) goto L562;
        TLRPC.ChannelLocation r147 = ((TLRPC.TL_channelAdminLogEventActionChangeLocation) r43).new_value;
        if ((r147 instanceof TLRPC.TL_channelLocationEmpty) == false) goto L560;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogRemovedLocation), "un1", r9);
        goto L545
    L560:
        this.messageText = replaceWithLink(LocaleController.formatString("EventLogChangedLocation", R.string.EventLogChangedLocation, new Object[]{((TLRPC.TL_channelLocation) r147).address}), "un1", r9);
        goto L545
    L562:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionToggleSlowMode) == false) goto L575;
        int r148 = ((TLRPC.TL_channelAdminLogEventActionToggleSlowMode) r43).new_value;
        if (r148 != 0) goto L567;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogToggledSlowmodeOff), "un1", r9);
        goto L545
    L567:
        if (r148 >= 60) goto L570;
        String r149 = LocaleController.formatPluralString("Seconds", r148, new Object[0]);
    L573:
        this.messageText = replaceWithLink(LocaleController.formatString(R.string.EventLogToggledSlowmodeOn, new Object[]{r149}), "un1", r9);
        goto L545
    L570:
        if (r148 >= 3600) goto L572;
        r149 = LocaleController.formatPluralString("Minutes", r148 / 60, new Object[0]);
        goto L573
    L572:
        r149 = LocaleController.formatPluralString("Hours", (r148 / 60) / 60, new Object[0]);
        goto L573
    L575:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionToggleAutotranslation) == false) goto L582;
        if (((TLRPC.TL_channelAdminLogEventActionToggleAutotranslation) r43).new_value == false) goto L579;
        int r150 = R.string.EventLogToggledAutotranslationOn;
    L580:
        this.messageText = replaceWithLink(LocaleController.getString(r150), "un1", r9);
        goto L545
    L579:
        r150 = R.string.EventLogToggledAutotranslationOff;
        goto L580
    L582:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionStartGroupCall) == false) goto L592;
        if (ChatObject.isChannel(r610) == true) goto L586;
    L590:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogStartedVoiceChat), "un1", r9);
        goto L545
    L586:
        if (r610.megagroup == true) goto L588;
    L589:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogStartedLiveStream), "un1", r9);
        goto L545
    L588:
        if (r610.gigagroup == false) goto L590;
    L592:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionDiscardGroupCall) == false) goto L602;
        if (ChatObject.isChannel(r610) == true) goto L596;
    L600:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogEndedVoiceChat), "un1", r9);
        goto L545
    L596:
        if (r610.megagroup == true) goto L598;
    L599:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogEndedLiveStream), "un1", r9);
        goto L545
    L598:
        if (r610.gigagroup == false) goto L600;
    L602:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantMute) == false) goto L609;
        long r151 = getPeerId(((TLRPC.TL_channelAdminLogEventActionParticipantMute) r43).participant.peer);
        int r424 = this.currentAccount;
        if (r151 <= 0) goto L606;
        TLObject r154 = MessagesController.getInstance(r424).getUser(Long.valueOf(r151));
    L607:
        CharSequence r242 = replaceWithLink(LocaleController.getString(R.string.EventLogVoiceChatMuted), "un1", r9);
        this.messageText = r242;
        this.messageText = replaceWithLink(r242, "un2", r154);
        goto L545
    L606:
        r154 = MessagesController.getInstance(r424).getChat(Long.valueOf(-r151));
        goto L607
    L609:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantUnmute) == false) goto L616;
        long r155 = getPeerId(((TLRPC.TL_channelAdminLogEventActionParticipantUnmute) r43).participant.peer);
        int r425 = this.currentAccount;
        if (r155 <= 0) goto L613;
        TLObject r156 = MessagesController.getInstance(r425).getUser(Long.valueOf(r155));
    L614:
        CharSequence r243 = replaceWithLink(LocaleController.getString(R.string.EventLogVoiceChatUnmuted), "un1", r9);
        this.messageText = r243;
        this.messageText = replaceWithLink(r243, "un2", r156);
        goto L545
    L613:
        r156 = MessagesController.getInstance(r425).getChat(Long.valueOf(-r155));
        goto L614
    L616:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionToggleGroupCallSetting) == false) goto L622;
        if (((TLRPC.TL_channelAdminLogEventActionToggleGroupCallSetting) r43).join_muted == false) goto L620;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogVoiceChatNotAllowedToSpeak), "un1", r9);
        goto L545
    L620:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogVoiceChatAllowedToSpeak), "un1", r9);
        goto L545
    L622:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantJoinByInvite) == false) goto L641;
        TLRPC.TL_channelAdminLogEventActionParticipantJoinByInvite r426 = (TLRPC.TL_channelAdminLogEventActionParticipantJoinByInvite) r43;
        if (r426.via_chatlist == false) goto L631;
        if (ChatObject.isChannelAndNotMegaGroup(r610) == false) goto L628;
        int r157 = R.string.ActionInviteChannelUserFolder;
    L629:
        this.messageText = replaceWithLink(LocaleController.getString(r157), "un1", r9);
    L635:
        TLRPC.TL_chatInviteExported r158 = r426.invite;
        if (r158 == null) goto L545;
        if (TextUtils.isEmpty(r158.link) == true) goto L545;
        this.messageText = TextUtils.concat(new CharSequence[]{this.messageText, " ", r426.invite.link});
        goto L545
    L628:
        r157 = R.string.ActionInviteUserFolder;
        goto L629
    L631:
        if (ChatObject.isChannelAndNotMegaGroup(r610) == false) goto L633;
        int r159 = R.string.ActionInviteChannelUser;
    L634:
        this.messageText = replaceWithLink(LocaleController.getString(r159), "un1", r9);
        goto L635
    L633:
        r159 = R.string.ActionInviteUser;
        goto L634
    L641:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionToggleNoForwards) == false) goto L657;
        TLRPC.TL_channelAdminLogEventActionToggleNoForwards r427 = (TLRPC.TL_channelAdminLogEventActionToggleNoForwards) r43;
        if (ChatObject.isChannel(r610) == true) goto L645;
    L647:
        boolean r1510 = false;
    L649:
        if (r427.new_value == false) goto L653;
        if (r1510 == false) goto L652;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionForwardsRestrictedChannel), "un1", r9);
        goto L545
    L652:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionForwardsRestrictedGroup), "un1", r9);
        goto L545
    L653:
        if (r1510 == false) goto L655;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionForwardsEnabledChannel), "un1", r9);
        goto L545
    L655:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionForwardsEnabledGroup), "un1", r9);
        goto L545
    L645:
        if (r610.megagroup == true) goto L647;
        r1510 = true;
        goto L649
    L657:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionExportedInviteDelete) == false) goto L660;
        CharSequence r160 = replaceWithLink(LocaleController.formatString(R.string.ActionDeletedInviteLinkClickable, new Object[0]), "un1", r9);
        this.messageText = r160;
        this.messageText = replaceWithLink(r160, "un2", ((TLRPC.TL_channelAdminLogEventActionExportedInviteDelete) r43).invite);
        goto L545
    L660:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionExportedInviteRevoke) == false) goto L663;
        TLRPC.TL_channelAdminLogEventActionExportedInviteRevoke r428 = (TLRPC.TL_channelAdminLogEventActionExportedInviteRevoke) r43;
        CharSequence r161 = replaceWithLink(LocaleController.formatString(R.string.ActionRevokedInviteLinkClickable, new Object[]{r428.invite.link}), "un1", r9);
        this.messageText = r161;
        this.messageText = replaceWithLink(r161, "un2", r428.invite);
        goto L545
    L663:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionExportedInviteEdit) == false) goto L672;
        TLRPC.TL_channelAdminLogEventActionExportedInviteEdit r429 = (TLRPC.TL_channelAdminLogEventActionExportedInviteEdit) r43;
        String r163 = r429.prev_invite.link;
        if (r163 != null) goto L667;
    L669:
        this.messageText = replaceWithLink(LocaleController.formatString(R.string.ActionEditedInviteLinkClickable, new Object[0]), "un1", r9);
    L670:
        CharSequence r164 = replaceWithLink(this.messageText, "un2", r429.prev_invite);
        this.messageText = r164;
        this.messageText = replaceWithLink(r164, "un3", r429.new_invite);
        goto L545
    L667:
        if (r163.equals(r429.new_invite.link) == false) goto L669;
        this.messageText = replaceWithLink(LocaleController.formatString(R.string.ActionEditedInviteLinkToSameClickable, new Object[0]), "un1", r9);
        goto L670
    L672:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantVolume) == false) goto L682;
        long r165 = getPeerId(((TLRPC.TL_channelAdminLogEventActionParticipantVolume) r43).participant.peer);
        int r58 = this.currentAccount;
        if (r165 <= 0) goto L676;
        TLObject r166 = MessagesController.getInstance(r58).getUser(Long.valueOf(r165));
    L677:
        double r244 = ChatObject.getParticipantVolume(r4.participant) / 100.0d;
        int r430 = R.string.ActionVolumeChanged;
        double r104 = 0.0d;
        if (r244 <= 0.0d) goto L680;
        r104 = Math.max(r244, 1.0d);
    L680:
        CharSequence r245 = replaceWithLink(LocaleController.formatString("ActionVolumeChanged", r430, new Object[]{Integer.valueOf((int) r104)}), "un1", r9);
        this.messageText = r245;
        this.messageText = replaceWithLink(r245, "un2", r166);
        goto L545
    L676:
        r166 = MessagesController.getInstance(r58).getChat(Long.valueOf(-r165));
        goto L677
    L682:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeHistoryTTL) == false) goto L704;
        TLRPC.TL_channelAdminLogEventActionChangeHistoryTTL r431 = (TLRPC.TL_channelAdminLogEventActionChangeHistoryTTL) r43;
        if (r610.megagroup == true) goto L689;
        int r167 = r431.new_value;
        if (r167 == 0) goto L688;
        this.messageText = LocaleController.formatString("ActionTTLChannelChanged", R.string.ActionTTLChannelChanged, new Object[]{LocaleController.formatTTLString(r167)});
        goto L545
    L688:
        this.messageText = LocaleController.getString(R.string.ActionTTLChannelDisabled);
        goto L545
    L689:
        int r168 = r431.new_value;
        if (r168 != 0) goto L693;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionTTLDisabled), "un1", r9);
        goto L545
    L693:
        if (r168 <= 86400) goto L696;
        String r169 = LocaleController.formatPluralString("Days", r168 / 86400, new Object[0]);
    L702:
        this.messageText = replaceWithLink(LocaleController.formatString(R.string.ActionTTLChanged, new Object[]{r169}), "un1", r9);
        goto L545
    L696:
        if (r168 < 3600) goto L699;
        r169 = LocaleController.formatPluralString("Hours", r168 / 3600, new Object[0]);
        goto L702
    L699:
        if (r168 < 60) goto L701;
        r169 = LocaleController.formatPluralString("Minutes", r168 / 60, new Object[0]);
        goto L702
    L701:
        r169 = LocaleController.formatPluralString("Seconds", r168, new Object[0]);
        goto L702
    L704:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantJoinByRequest) == false) goto L714;
        TLRPC.TL_channelAdminLogEventActionParticipantJoinByRequest r432 = (TLRPC.TL_channelAdminLogEventActionParticipantJoinByRequest) r43;
        TLRPC.ExportedChatInvite r170 = r432.invite;
        if ((r170 instanceof TLRPC.TL_chatInviteExported) == false) goto L710;
        if ("https://t.me/+PublicChat".equals(((TLRPC.TL_chatInviteExported) r170).link) == false) goto L710;
    L711:
        CharSequence r171 = replaceWithLink(LocaleController.getString(R.string.JoinedViaRequestApproved), "un1", r9);
        this.messageText = r171;
        this.messageText = replaceWithLink(r171, "un2", MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r432.approved_by)));
    L710:
        if ((r432.invite instanceof TLRPC.TL_chatInvitePublicJoinRequests) == true) goto L711;
        CharSequence r173 = replaceWithLink(LocaleController.getString(R.string.JoinedViaInviteLinkApproved), "un1", r9);
        this.messageText = r173;
        CharSequence r174 = replaceWithLink(r173, "un2", r432.invite);
        this.messageText = r174;
        this.messageText = replaceWithLink(r174, "un3", MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r432.approved_by)));
        goto L545
    L714:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionSendMessage) == false) goto L717;
        r138 = ((TLRPC.TL_channelAdminLogEventActionSendMessage) r43).message;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogSendMessages), "un1", r9);
        goto L435
    L717:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionParticipantEditRank) == false) goto L743;
        TLRPC.TL_channelAdminLogEventActionParticipantEditRank r433 = (TLRPC.TL_channelAdminLogEventActionParticipantEditRank) r43;
        if (r433.user_id == r7.user_id) goto L721;
        TLRPC.User r175 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r433.user_id));
        if (TextUtils.isEmpty(r433.prev_rank) == true) goto L737;
        if (TextUtils.isEmpty(r433.new_rank) == true) goto L737;
        String r246 = LocaleController.formatString(R.string.EventLogRankEdit, new Object[]{r433.prev_rank, r433.new_rank});
        this.messageText = r246;
        CharSequence r247 = replaceWithLink(r246, "un1", r9);
        this.messageText = r247;
        this.messageText = replaceWithLink(r247, "un2", r175);
    L737:
        if (TextUtils.isEmpty(r433.prev_rank) == true) goto L739;
    L741:
        String r248 = LocaleController.formatString(R.string.EventLogRankRemove, new Object[]{r433.prev_rank});
        this.messageText = r248;
        CharSequence r249 = replaceWithLink(r248, "un1", r9);
        this.messageText = r249;
        this.messageText = replaceWithLink(r249, "un2", r175);
        goto L545
    L739:
        if (TextUtils.isEmpty(r433.new_rank) == true) goto L741;
        String r250 = LocaleController.formatString(R.string.EventLogRankAdd, new Object[]{r433.new_rank});
        this.messageText = r250;
        CharSequence r251 = replaceWithLink(r250, "un1", r9);
        this.messageText = r251;
        this.messageText = replaceWithLink(r251, "un2", r175);
        goto L545
    L721:
        if (TextUtils.isEmpty(r433.prev_rank) == true) goto L726;
        if (TextUtils.isEmpty(r433.new_rank) == true) goto L726;
        String r176 = LocaleController.formatString(R.string.EventLogRankSelfEdit, new Object[]{r433.prev_rank, r433.new_rank});
        this.messageText = r176;
        this.messageText = replaceWithLink(r176, "un1", r9);
    L726:
        if (TextUtils.isEmpty(r433.prev_rank) == true) goto L728;
    L730:
        String r177 = LocaleController.formatString(R.string.EventLogRankSelfRemove, new Object[]{r433.prev_rank});
        this.messageText = r177;
        this.messageText = replaceWithLink(r177, "un1", r9);
        goto L545
    L728:
        if (TextUtils.isEmpty(r433.new_rank) == true) goto L730;
        String r178 = LocaleController.formatString(R.string.EventLogRankSelfAdd, new Object[]{r433.new_rank});
        this.messageText = r178;
        this.messageText = replaceWithLink(r178, "un1", r9);
        goto L545
    L743:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeAvailableReactions) == false) goto L764;
        TLRPC.TL_channelAdminLogEventActionChangeAvailableReactions r434 = (TLRPC.TL_channelAdminLogEventActionChangeAvailableReactions) r43;
        if ((r434.prev_value instanceof TLRPC.TL_chatReactionsSome) == true) goto L747;
    L749:
        boolean r1511 = false;
    L750:
        CharSequence r179 = getStringFrom(r434.new_value);
        if (r1511 == false) goto L756;
        SpannableStringBuilder r330 = new SpannableStringBuilder(replaceWithLink(LocaleController.formatString(R.string.ActionReactionsChangedList, new Object[]{"**new**"}), "un1", r9));
        int r253 = r330.toString().indexOf("**new**");
        if (r253 <= 0) goto L755;
        r330.replace(r253, r253 + 7, r179);
    L755:
        this.messageText = r330;
        goto L545
    L756:
        CharSequence r331 = getStringFrom(r434.prev_value);
        SpannableStringBuilder r435 = new SpannableStringBuilder(replaceWithLink(LocaleController.formatString(R.string.ActionReactionsChanged, new Object[]{"**old**", "**new**"}), "un1", r9));
        int r59 = r435.toString().indexOf("**old**");
        if (r59 <= 0) goto L759;
        r435.replace(r59, r59 + 7, r331);
    L759:
        int r254 = r435.toString().indexOf("**new**");
        if (r254 <= 0) goto L762;
        r435.replace(r254, r254 + 7, r179);
    L762:
        this.messageText = r435;
        goto L545
    L747:
        if ((r434.new_value instanceof TLRPC.TL_chatReactionsSome) == false) goto L749;
        r1511 = true;
        goto L750
    L764:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeUsernames) == false) goto L798;
        TLRPC.TL_channelAdminLogEventActionChangeUsernames r436 = (TLRPC.TL_channelAdminLogEventActionChangeUsernames) r43;
        ArrayList<String> r180 = r436.prev_value;
        ArrayList<String> r255 = r436.new_value;
        this.messageText = null;
        if (r180 == null) goto L795;
        if (r255 == null) goto L795;
        if ((r255.size() + 1) != r180.size()) goto L782;
        int r332 = 0;
        String r437 = null;
    L772:
        if (r332 >= r180.size()) goto L779;
        String r105 = r180.get(r332);
        if (r255.contains(r105) == true) goto L778;
        if (r437 != null) goto L777;
        r437 = r105;
        goto L778
    L777:
        r437 = null;
    L778:
        r332 = r332 + 1;
    L779:
        if (r437 == null) goto L795;
        this.messageText = replaceWithLink(LocaleController.formatString("EventLogDeactivatedUsername", R.string.EventLogDeactivatedUsername, new Object[]{"@".concat(r437)}), "un1", r9);
        goto L795
    L782:
        if ((r180.size() + 1) != r255.size()) goto L795;
        int r333 = 0;
        String r438 = null;
    L785:
        if (r333 >= r255.size()) goto L792;
        String r106 = r255.get(r333);
        if (r180.contains(r106) == true) goto L791;
        if (r438 != null) goto L790;
        r438 = r106;
        goto L791
    L790:
        r438 = null;
    L791:
        r333 = r333 + 1;
    L792:
        if (r438 == null) goto L795;
        this.messageText = replaceWithLink(LocaleController.formatString("EventLogActivatedUsername", R.string.EventLogActivatedUsername, new Object[]{"@".concat(r438)}), "un1", r9);
    L795:
        if (this.messageText != null) goto L545;
        this.messageText = replaceWithLink(LocaleController.formatString("EventLogChangeUsernames", R.string.EventLogChangeUsernames, new Object[]{getUsernamesString(r180), getUsernamesString(r255)}), "un1", r9);
        goto L545
    L798:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionToggleForum) == false) goto L804;
        if (((TLRPC.TL_channelAdminLogEventActionToggleForum) r43).new_value == false) goto L802;
        this.messageText = replaceWithLink(LocaleController.formatString("EventLogSwitchToForum", R.string.EventLogSwitchToForum, new Object[0]), "un1", r9);
        goto L545
    L802:
        this.messageText = replaceWithLink(LocaleController.formatString("EventLogSwitchToGroup", R.string.EventLogSwitchToGroup, new Object[0]), "un1", r9);
        goto L545
    L804:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionCreateTopic) == false) goto L807;
        CharSequence r181 = replaceWithLink(LocaleController.formatString("EventLogCreateTopic", R.string.EventLogCreateTopic, new Object[0]), "un1", r9);
        this.messageText = r181;
        this.messageText = replaceWithLink(r181, "un2", ((TLRPC.TL_channelAdminLogEventActionCreateTopic) r43).topic);
        goto L545
    L807:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionEditTopic) == false) goto L833;
        TLRPC.TL_channelAdminLogEventActionEditTopic r439 = (TLRPC.TL_channelAdminLogEventActionEditTopic) r43;
        TLRPC.ForumTopic r182 = r439.prev_topic;
        if ((r182 instanceof TLRPC.TL_forumTopic) == false) goto L821;
        TLRPC.ForumTopic r256 = r439.new_topic;
        if ((r256 instanceof TLRPC.TL_forumTopic) == false) goto L821;
        if (((TLRPC.TL_forumTopic) r182).hidden == ((TLRPC.TL_forumTopic) r256).hidden) goto L821;
        if (((TLRPC.TL_forumTopic) r256).hidden == false) goto L818;
        int r183 = R.string.TopicHidden2;
    L819:
        this.messageText = replaceWithLink(LocaleController.getString(r183), "%s", r9);
        goto L545
    L818:
        r183 = R.string.TopicShown2;
    L821:
        if ((r182 instanceof TLRPC.TL_forumTopic) == false) goto L831;
        TLRPC.ForumTopic r257 = r439.new_topic;
        if ((r257 instanceof TLRPC.TL_forumTopic) == false) goto L831;
        if (((TLRPC.TL_forumTopic) r182).closed == ((TLRPC.TL_forumTopic) r257).closed) goto L831;
        if (((TLRPC.TL_forumTopic) r257).closed == false) goto L829;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogClosedTopic), "%s", r9);
    L830:
        this.messageText = replaceWithLink(this.messageText, "un2", r439.new_topic);
        goto L545
    L829:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.EventLogReopenedTopic), "%s", r9);
    L831:
        CharSequence r184 = replaceWithLink(LocaleController.getString(R.string.EventLogEditTopic), "un1", r9);
        this.messageText = r184;
        CharSequence r185 = replaceWithLink(r184, "un2", r439.prev_topic);
        this.messageText = r185;
        this.messageText = replaceWithLink(r185, "un3", r439.new_topic);
        goto L545
    L833:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionDeleteTopic) == false) goto L836;
        CharSequence r186 = replaceWithLink(LocaleController.getString(R.string.EventLogDeleteTopic), "un1", r9);
        this.messageText = r186;
        this.messageText = replaceWithLink(r186, "un2", ((TLRPC.TL_channelAdminLogEventActionDeleteTopic) r43).topic);
        goto L545
    L836:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionPinTopic) == false) goto L844;
        TLRPC.TL_channelAdminLogEventActionPinTopic r440 = (TLRPC.TL_channelAdminLogEventActionPinTopic) r43;
        TLRPC.ForumTopic r187 = r440.new_topic;
        if ((r187 instanceof TLRPC.TL_forumTopic) == true) goto L840;
    L842:
        CharSequence r188 = replaceWithLink(LocaleController.formatString("EventLogUnpinTopic", R.string.EventLogUnpinTopic, new Object[0]), "un1", r9);
        this.messageText = r188;
        this.messageText = replaceWithLink(r188, "un2", r440.new_topic);
        goto L545
    L840:
        if (((TLRPC.TL_forumTopic) r187).pinned == false) goto L842;
        CharSequence r189 = replaceWithLink(LocaleController.formatString("EventLogPinTopic", R.string.EventLogPinTopic, new Object[0]), "un1", r9);
        this.messageText = r189;
        this.messageText = replaceWithLink(r189, "un2", r440.new_topic);
        goto L545
    L844:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionToggleAntiSpam) == false) goto L851;
        if (((TLRPC.TL_channelAdminLogEventActionToggleAntiSpam) r43).new_value == false) goto L848;
        String r190 = LocaleController.getString(R.string.EventLogEnabledAntiSpam);
    L849:
        this.messageText = replaceWithLink(r190, "un1", r9);
        goto L545
    L848:
        r190 = LocaleController.getString(R.string.EventLogDisabledAntiSpam);
        goto L849
    L851:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeColor) == false) goto L858;
        boolean r191 = ChatObject.isChannelAndNotMegaGroup(r610);
        TLRPC.TL_channelAdminLogEventActionChangeColor r258 = (TLRPC.TL_channelAdminLogEventActionChangeColor) r7.action;
        if (r191 == false) goto L855;
        int r192 = R.string.EventLogChangedColor;
    L856:
        this.messageText = replaceWithLink(LocaleController.formatString(r192, new Object[]{AvatarDrawable.colorName(r258.prev_value).toLowerCase(), AvatarDrawable.colorName(r258.new_value).toLowerCase()}), "un1", r9);
        goto L545
    L855:
        r192 = R.string.EventLogChangedColorGroup;
        goto L856
    L858:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangePeerColor) == false) goto L889;
        boolean r193 = ChatObject.isChannelAndNotMegaGroup(r610);
        TLRPC.TL_channelAdminLogEventActionChangePeerColor r441 = (TLRPC.TL_channelAdminLogEventActionChangePeerColor) r7.action;
        if (r193 == false) goto L862;
        int r194 = R.string.EventLogChangedPeerColorIcon;
    L863:
        SpannableStringBuilder r107 = new SpannableStringBuilder(LocaleController.getString(r194));
        SpannableStringBuilder r195 = new SpannableStringBuilder();
        if ((r441.prev_value.flags & 1) == 0) goto L866;
        r195.append("c");
        r25 = r812;
        r195.setSpan(new PeerColorActivity.PeerColorSpan(false, this.currentAccount, r441.prev_value.color).setSize(AndroidUtilities.dp(18.0f)), r195.length() - 1, r195.length(), 33);
    L868:
        if ((r441.prev_value.flags & 2) == 0) goto L874;
        if (r195.length() <= 0) goto L872;
        r195.append(", ");
    L872:
        r195.append("e");
        r195.setSpan(new AnimatedEmojiSpan(r441.prev_value.background_emoji_id, Theme.chat_actionTextPaint.getFontMetricsInt()), r195.length() - 1, r195.length(), 33);
    L874:
        if (r195.length() != 0) goto L876;
        r195.append(LocaleController.getString(R.string.EventLogEmojiNone));
    L876:
        SpannableStringBuilder r611 = new SpannableStringBuilder();
        if ((r441.new_value.flags & 1) == 0) goto L880;
        r611.append("c");
        r611.setSpan(new PeerColorActivity.PeerColorSpan(false, this.currentAccount, r441.new_value.color).setSize(AndroidUtilities.dp(18.0f)), r611.length() - 1, r611.length(), 33);
    L880:
        if ((r441.new_value.flags & 2) == 0) goto L886;
        if (r611.length() <= 0) goto L884;
        r611.append(", ");
    L884:
        r611.append("e");
        r611.setSpan(new AnimatedEmojiSpan(r441.new_value.background_emoji_id, Theme.chat_actionTextPaint.getFontMetricsInt()), r611.length() - 1, r611.length(), 33);
    L886:
        if (r611.length() != 0) goto L888;
        r611.append(LocaleController.getString(R.string.EventLogEmojiNone));
    L888:
        this.messageText = replaceWithLink(AndroidUtilities.replaceCharSequence("%2$s", AndroidUtilities.replaceCharSequence("%1$s", r107, r195), r611), "un1", r9);
        goto L342
    L866:
        r25 = r812;
        goto L868
    L862:
        r194 = R.string.EventLogChangedPeerColorIconGroup;
        goto L863
    L889:
        r25 = r812;
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeProfilePeerColor) == false) goto L921;
        boolean r196 = ChatObject.isChannelAndNotMegaGroup(r40);
        TLRPC.TL_channelAdminLogEventActionChangeProfilePeerColor r442 = (TLRPC.TL_channelAdminLogEventActionChangeProfilePeerColor) r7.action;
        if (r196 == false) goto L894;
        int r197 = R.string.EventLogChangedProfileColorIcon;
    L895:
        SpannableStringBuilder r612 = new SpannableStringBuilder(LocaleController.getString(r197));
        SpannableStringBuilder r198 = new SpannableStringBuilder();
        if ((r442.prev_value.flags & 1) == 0) goto L899;
        r198.append("c");
        r198.setSpan(new PeerColorActivity.PeerColorSpan(true, this.currentAccount, r442.prev_value.color).setSize(AndroidUtilities.dp(18.0f)), r198.length() - 1, r198.length(), 33);
    L899:
        if ((r442.prev_value.flags & 2) == 0) goto L905;
        if (r198.length() <= 0) goto L903;
        r198.append(", ");
    L903:
        r198.append("e");
        r198.setSpan(new AnimatedEmojiSpan(r442.prev_value.background_emoji_id, Theme.chat_actionTextPaint.getFontMetricsInt()), r198.length() - 1, r198.length(), 33);
    L905:
        if (r198.length() != 0) goto L907;
        r198.append(LocaleController.getString(R.string.EventLogEmojiNone));
    L907:
        SpannableStringBuilder r813 = new SpannableStringBuilder();
        if ((r442.new_value.flags & 1) == 0) goto L911;
        r813.append("c");
        r813.setSpan(new PeerColorActivity.PeerColorSpan(true, this.currentAccount, r442.new_value.color).setSize(AndroidUtilities.dp(18.0f)), r813.length() - 1, r813.length(), 33);
    L911:
        if ((r442.new_value.flags & 2) == 0) goto L917;
        if (r813.length() <= 0) goto L915;
        r813.append(", ");
    L915:
        r813.append("e");
        r813.setSpan(new AnimatedEmojiSpan(r442.new_value.background_emoji_id, Theme.chat_actionTextPaint.getFontMetricsInt()), r813.length() - 1, r813.length(), 33);
    L917:
        if (r813.length() != 0) goto L919;
        r813.append(LocaleController.getString(R.string.EventLogEmojiNone));
    L919:
        this.messageText = replaceWithLink(AndroidUtilities.replaceCharSequence("%2$s", AndroidUtilities.replaceCharSequence("%1$s", r612, r198), r813), "un1", r9);
        goto L342
    L894:
        r197 = R.string.EventLogChangedProfileColorIconGroup;
        goto L895
    L921:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeEmojiStatus) == false) goto L951;
        boolean r199 = ChatObject.isChannelAndNotMegaGroup(r40);
        TLRPC.TL_channelAdminLogEventActionChangeEmojiStatus r443 = (TLRPC.TL_channelAdminLogEventActionChangeEmojiStatus) r7.action;
        if ((r443.prev_value instanceof TLRPC.TL_emojiStatusEmpty) == false) goto L925;
        SpannableString r510 = new SpannableString(LocaleController.getString(R.string.EventLogEmojiNone));
        boolean r613 = true;
    L926:
        int r814 = DialogObject.getEmojiStatusUntil(r443.new_value);
        if ((r443.new_value instanceof TLRPC.TL_emojiStatusEmpty) == false) goto L929;
        SpannableString r444 = new SpannableString(LocaleController.getString(R.string.EventLogEmojiNone));
    L931:
        if (r613 == false) goto L939;
        if (r814 == 0) goto L936;
        if (r199 == false) goto L935;
        int r1100 = R.string.EventLogChangedEmojiStatusFor;
    L946:
        SpannableStringBuilder r1101 = AndroidUtilities.replaceCharSequence("%2$s", AndroidUtilities.replaceCharSequence("%1$s", new SpannableStringBuilder(LocaleController.getString(r1100)), r510), r444);
        if (r814 == 0) goto L949;
        r1101 = AndroidUtilities.replaceCharSequence("%3$s", r1101, LocaleController.formatTTLString((int) ((r814 - r7.date) * 1.05f)));
    L949:
        this.messageText = replaceWithLink(r1101, "un1", r9);
        goto L342
    L935:
        r1100 = R.string.EventLogChangedEmojiStatusForGroup;
        goto L946
    L936:
        if (r199 == false) goto L938;
        r1100 = R.string.EventLogChangedEmojiStatus;
        goto L946
    L938:
        r1100 = R.string.EventLogChangedEmojiStatusGroup;
        goto L946
    L939:
        if (r814 == 0) goto L943;
        if (r199 == false) goto L942;
        r1100 = R.string.EventLogChangedEmojiStatusFromFor;
        goto L946
    L942:
        r1100 = R.string.EventLogChangedEmojiStatusFromForGroup;
        goto L946
    L943:
        if (r199 == false) goto L945;
        r1100 = R.string.EventLogChangedEmojiStatusFrom;
        goto L946
    L945:
        r1100 = R.string.EventLogChangedEmojiStatusFromGroup;
        goto L946
    L929:
        SpannableString r108 = new SpannableString("e");
        r108.setSpan(new AnimatedEmojiSpan(DialogObject.getEmojiStatusDocumentId(r443.new_value), Theme.chat_actionTextPaint.getFontMetricsInt()), 0, 1, 33);
        r444 = r108;
        goto L931
    L925:
        r510 = new SpannableString("e");
        r510.setSpan(new AnimatedEmojiSpan(DialogObject.getEmojiStatusDocumentId(r443.prev_value), Theme.chat_actionTextPaint.getFontMetricsInt()), 0, 1, 33);
        r613 = false;
        goto L926
    L951:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeWallpaper) == false) goto L970;
        TLRPC.TL_channelAdminLogEventActionChangeWallpaper r445 = (TLRPC.TL_channelAdminLogEventActionChangeWallpaper) r43;
        boolean r1102 = ChatObject.isChannelAndNotMegaGroup(r40);
        TLRPC.WallPaper r259 = r445.new_value;
        if ((r259 instanceof TLRPC.TL_wallPaperNoFile) == true) goto L955;
    L962:
        ArrayList<TLRPC.PhotoSize> r260 = new ArrayList();
        this.photoThumbs = r260;
        TLRPC.Document r334 = r445.new_value.document;
        if (r334 == null) goto L965;
        r260.addAll(r334.thumbs);
        this.photoThumbsObject = r445.new_value.document;
    L965:
        if (r1102 == false) goto L967;
        int r1103 = R.string.EventLogChangedWallpaper;
    L968:
        this.messageText = replaceWithLink(LocaleController.getString(r1103), "un1", r9);
        goto L342
    L967:
        r1103 = R.string.EventLogChangedWallpaperGroup;
        goto L968
    L955:
        if (r259.id != 0) goto L962;
        if (r259.settings != null) goto L962;
        if (r1102 == false) goto L960;
        int r1104 = R.string.EventLogRemovedWallpaper;
    L961:
        this.messageText = replaceWithLink(LocaleController.getString(r1104), "un1", r9);
        goto L342
    L960:
        r1104 = R.string.EventLogRemovedWallpaperGroup;
        goto L961
    L970:
        if ((r43 instanceof TLRPC.TL_channelAdminLogEventActionChangeBackgroundEmoji) == false) goto L984;
        boolean r1105 = ChatObject.isChannelAndNotMegaGroup(r40);
        TLRPC.TL_channelAdminLogEventActionChangeBackgroundEmoji r446 = (TLRPC.TL_channelAdminLogEventActionChangeBackgroundEmoji) r7.action;
        if (r1105 == false) goto L974;
        int r1106 = R.string.EventLogChangedEmoji;
    L975:
        this.messageText = replaceWithLink(LocaleController.getString(r1106), "un1", r9);
        if (r446.prev_value != 0) goto L978;
        SpannableString r1107 = new SpannableString(LocaleController.getString(R.string.EventLogEmojiNone));
    L979:
        this.messageText = AndroidUtilities.replaceCharSequence("%1$s", this.messageText, r1107);
        if (r446.new_value != 0) goto L982;
        SpannableString r1108 = new SpannableString(LocaleController.getString(R.string.EventLogEmojiNone));
    L983:
        this.messageText = AndroidUtilities.replaceCharSequence("%2$s", this.messageText, r1108);
        goto L342
    L982:
        r1108 = new SpannableString("e");
        r1108.setSpan(new AnimatedEmojiSpan(r446.new_value, Theme.chat_actionTextPaint.getFontMetricsInt()), 0, 1, 33);
        goto L983
    L978:
        r1107 = new SpannableString("e");
        r1107.setSpan(new AnimatedEmojiSpan(r446.prev_value, Theme.chat_actionTextPaint.getFontMetricsInt()), 0, 1, 33);
        goto L979
    L974:
        r1106 = R.string.EventLogChangedEmojiGroup;
        goto L975
    L984:
        this.messageText = "unsupported " + r7.action;
        goto L342
    L427:
        if (r610.megagroup == false) goto L430;
        int r1109 = R.string.EventLogEditedGroupDescription;
    L431:
        this.messageText = replaceWithLink(LocaleController.getString(r1109), "un1", r9);
        r138 = new TLRPC.TL_message();
        r138.out = false;
        r138.unread = false;
        TLRPC.TL_peerUser r261 = new TLRPC.TL_peerUser();
        r138.from_id = r261;
        r7 = r37;
        r261.user_id = r7.user_id;
        r138.peer_id = r33;
        r138.date = r7.date;
        TLRPC.ChannelAdminLogEventAction r262 = r7.action;
        r138.message = ((TLRPC.TL_channelAdminLogEventActionChangeAbout) r262).new_value;
        if (TextUtils.isEmpty(((TLRPC.TL_channelAdminLogEventActionChangeAbout) r262).prev_value) == true) goto L434;
        TLRPC.TL_messageMediaWebPage r263 = new TLRPC.TL_messageMediaWebPage();
        r138.media = r263;
        r263.webpage = new TLRPC.TL_webPage();
        TLRPC.WebPage r264 = r138.media.webpage;
        r264.flags = 10;
        r812 = r25;
        r264.display_url = r812;
        r264.url = r812;
        r264.site_name = LocaleController.getString(R.string.EventLogPreviousGroupDescription);
        r138.media.webpage.description = ((TLRPC.TL_channelAdminLogEventActionChangeAbout) r7.action).prev_value;
        goto L435
    L434:
        r812 = r25;
        r138.media = new TLRPC.TL_messageMediaEmpty();
        goto L435
    L430:
        r1109 = R.string.EventLogEditedChannelDescription;
        goto L431
    L5:
        r9 = null;
        goto L6
    }

    public MessageObject(int r4, TL_stories.StoryItem r5) {
        this.type = 1000;
        this.forceSeekTo = -1.0f;
        this.actionDeleteGroupEventId = -1;
        this.overrideLinkColor = -1;
        this.overrideLinkEmoji = -1;
        this.messageTrimmedToHighlightCut = true;
        this.topicIconDrawable = new Drawable[1];
        this.spoiledLoginCode = false;
        this.translated = false;
        this.summarized = false;
        this.currentAccount = r4;
        this.storyItem = r5;
        if (r5 == null) goto L5;
        TLRPC.TL_message r42 = new TLRPC.TL_message();
        this.messageOwner = r42;
        r42.id = r5.messageId;
        r42.realId = r5.id;
        r42.date = r5.date;
        r42.dialog_id = r5.dialogId;
        r42.message = r5.caption;
        r42.entities = r5.entities;
        r42.media = r5.media;
        r42.attachPath = r5.attachPath;
    L5:
        this.photoThumbs = new ArrayList();
        this.photoThumbs2 = new ArrayList();
    }

    public static boolean addEntitiesToText(CharSequence r7, ArrayList<TLRPC.MessageEntity> r8, boolean r9, boolean r10, boolean r11, boolean r12) {
        return addEntitiesToText(r7, r8, r9, r10, r11, r12, 0);
    }

    public static boolean addEntitiesToText(CharSequence r22, ArrayList<TLRPC.MessageEntity> r23, boolean r24, boolean r25, boolean r26, boolean r27, int r28) {
        if ((r22 instanceof Spannable) == true) goto L5;
        return false;
    L5:
        CharSequence r1 = FormattedDateSpan.restoreFormatedDateEntities(r22);
        Spannable r3 = (Spannable) r1;
        URLSpan[] r5 = (URLSpan[]) r3.getSpans(0, r1.length(), URLSpan.class);
        if (r5 != null) goto L8;
    L10:
        boolean r7 = false;
    L11:
        if (r23 != null) goto L13;
    L352:
        return r7;
    L13:
        if (r23.isEmpty() == true) goto L352;
        if (r26 == false) goto L17;
        byte r9 = 2;
    L20:
        ArrayList r10 = new ArrayList();
        ArrayList r11 = new ArrayList(r23);
        Collections.sort(r11, new MessageObject$$ExternalSyntheticLambda3());
        int r0 = r11.size();
        int r12 = 0;
    L21:
        if (r12 >= r0) goto L189;
        TLRPC.MessageEntity r14 = (TLRPC.MessageEntity) r11.get(r12);
        if (r14.length <= 0) goto L28;
        int r4 = r14.offset;
        if (r4 < 0) goto L28;
        if (r4 >= r1.length()) goto L28;
        if ((r14.offset + r14.length) <= r1.length()) goto L32;
        r14.length = r1.length() - r14.offset;
    L32:
        if (r27 == true) goto L34;
    L67:
        if (r5 != null) goto L69;
    L85:
        if (r28 != 1) goto L90;
        if ((r14 instanceof TLRPC.TL_messageEntityHashtag) == false) goto L28;
    L90:
        if ((r14 instanceof TLRPC.TL_messageEntityCustomEmoji) == true) goto L28;
        if ((r14 instanceof TLRPC.TL_messageEntityBlockquote) == true) goto L28;
        if ((r14 instanceof TLRPC.TL_messageEntityPre) == true) goto L28;
        if ((r14 instanceof TLRPC.TL_messageEntityDiffReplace) == true) goto L28;
        TextStyleSpan.TextStyleRun r42 = new TextStyleSpan.TextStyleRun();
        int r6 = r14.offset;
        r42.start = r6;
        r42.end = r6 + r14.length;
        if ((r14 instanceof TLRPC.TL_messageEntitySpoiler) == false) goto L103;
        r42.flags = 256;
    L153:
        int r8 = r10.size();
        int r13 = 0;
    L154:
        if (r13 >= r8) goto L185;
        TextStyleSpan.TextStyleRun r142 = (TextStyleSpan.TextStyleRun) r10.get(r13);
        if ((r142.flags & 256) != 0) goto L158;
    L162:
        int r62 = r42.start;
        int r15 = r142.start;
        if (r62 <= r15) goto L174;
        int r152 = r142.end;
        if (r62 < r152) goto L168;
    L172:
        int r232 = r0;
    L184:
        r13 = r13 + 1;
        r0 = r232;
        goto L154
    L168:
        if (r42.end >= r152) goto L170;
        TextStyleSpan.TextStyleRun r63 = new TextStyleSpan.TextStyleRun(r42);
        r63.merge(r142);
        r10.add(r13 + 1, r63);
        TextStyleSpan.TextStyleRun r64 = new TextStyleSpan.TextStyleRun(r142);
        r64.start = r42.end;
        r13 = r13 + 2;
        r8 = r8 + 2;
        r10.add(r13, r64);
    L171:
        int r65 = r42.start;
        r42.start = r142.end;
        r142.end = r65;
        goto L172
    L170:
        TextStyleSpan.TextStyleRun r66 = new TextStyleSpan.TextStyleRun(r42);
        r66.merge(r142);
        r66.end = r142.end;
        r13 = r13 + 1;
        r8 = r8 + 1;
        r10.add(r13, r66);
        goto L171
    L174:
        int r67 = r42.end;
        if (r15 >= r67) goto L172;
        r232 = r0;
        int r02 = r142.end;
        if (r67 != r02) goto L180;
        r142.merge(r42);
    L183:
        r42.end = r15;
        goto L184
    L180:
        if (r67 >= r02) goto L182;
        TextStyleSpan.TextStyleRun r03 = new TextStyleSpan.TextStyleRun(r142);
        r03.merge(r42);
        r03.end = r42.end;
        r13 = r13 + 1;
        r8 = r8 + 1;
        r10.add(r13, r03);
        r142.start = r42.end;
        goto L183
    L182:
        TextStyleSpan.TextStyleRun r04 = new TextStyleSpan.TextStyleRun(r42);
        r04.start = r142.end;
        r13 = r13 + 1;
        r8 = r8 + 1;
        r10.add(r13, r04);
        r142.merge(r42);
        goto L183
    L158:
        if (r42.start < r142.start) goto L162;
        if (r42.end > r142.end) goto L162;
    L185:
        int r233 = r0;
        if (r42.start >= r42.end) goto L188;
        r10.add(r42);
    L188:
        r12 = r12 + 1;
        r0 = r233;
        goto L21
    L103:
        if ((r14 instanceof TLRPC.TL_messageEntityStrike) == false) goto L106;
        r42.flags = 8;
        goto L153
    L106:
        if ((r14 instanceof TLRPC.TL_messageEntityDiffDelete) == false) goto L109;
        r42.flags = 8192;
        goto L153
    L109:
        if ((r14 instanceof TLRPC.TL_messageEntityUnderline) == false) goto L112;
        r42.flags = 16;
        goto L153
    L112:
        if ((r14 instanceof TLRPC.TL_messageEntityBold) == false) goto L115;
        r42.flags = 1;
        goto L153
    L115:
        if ((r14 instanceof TLRPC.TL_messageEntityItalic) == false) goto L118;
        r42.flags = 2;
        goto L153
    L118:
        if ((r14 instanceof TLRPC.TL_messageEntityCode) == false) goto L121;
        r42.flags = 4;
        goto L153
    L121:
        if ((r14 instanceof TLRPC.TL_messageEntityDiffInsert) == false) goto L124;
        r42.flags = 4096;
        goto L153
    L124:
        if ((r14 instanceof TLRPC.TL_messageEntityMentionName) == false) goto L129;
        if (r25 == false) goto L28;
        r42.flags = 64;
        r42.urlEntity = r14;
        goto L153
    L129:
        if ((r14 instanceof TLRPC.TL_inputMessageEntityMentionName) == false) goto L133;
        if (r25 == false) goto L28;
        r42.flags = 64;
        r42.urlEntity = r14;
        goto L153
    L133:
        if (r27 == false) goto L140;
        if ((r14 instanceof TLRPC.TL_messageEntityTextUrl) == true) goto L140;
        if ((r14 instanceof TLRPC.TL_messageEntityFormattedDate) == false) goto L28;
    L140:
        if ((r14 instanceof TLRPC.TL_messageEntityUrl) == true) goto L144;
        if ((r14 instanceof TLRPC.TL_messageEntityTextUrl) == true) goto L144;
    L147:
        if ((r14 instanceof TLRPC.TL_messageEntityMention) == false) goto L150;
        if (r25 == false) goto L28;
    L150:
        r42.flags = 128;
        r42.urlEntity = r14;
        if ((r14 instanceof TLRPC.TL_messageEntityTextUrl) == false) goto L153;
        r42.flags = 128 | 1024;
    L144:
        if (Browser.isPassportUrl(r14.url) == false) goto L147;
    L69:
        if (r5.length <= 0) goto L85;
        int r43 = 0;
    L72:
        if (r43 >= r5.length) goto L85;
        URLSpan r132 = r5[r43];
        if (r132 == null) goto L84;
        int r133 = r3.getSpanStart(r132);
        int r82 = r3.getSpanEnd(r5[r43]);
        int r153 = r14.offset;
        if (r153 <= r133) goto L79;
    L80:
        if (r153 > r82) goto L84;
        if ((r153 + r14.length) < r82) goto L84;
    L83:
        r3.removeSpan(r5[r43]);
        r5[r43] = null;
        goto L84
    L79:
        if ((r14.length + r153) >= r133) goto L83;
    L84:
        r43 = r43 + 1;
        goto L72
    L34:
        if ((r14 instanceof TLRPC.TL_messageEntityBold) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntityItalic) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntityStrike) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntityUnderline) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntityBlockquote) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntityFormattedDate) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntityCode) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntityPre) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntityMentionName) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_inputMessageEntityMentionName) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntityTextUrl) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntitySpoiler) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntityCustomEmoji) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntityDiffInsert) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntityDiffReplace) == true) goto L67;
        if ((r14 instanceof TLRPC.TL_messageEntityDiffDelete) == false) goto L85;
    L28:
        r233 = r0;
        goto L188
    L189:
        int r05 = Math.min(1000, r10.size());
        int r44 = 0;
        int r52 = 0;
        int r68 = 0;
    L190:
        if (r44 >= r05) goto L297;
        TextStyleSpan.TextStyleRun r122 = (TextStyleSpan.TextStyleRun) r10.get(r44);
        int r134 = r122.start;
        if (r134 < 0) goto L197;
        int r143 = r122.end;
        if (r134 >= r143) goto L197;
        if (r143 > r1.length()) goto L197;
        if (r28 == 1) goto L201;
    L203:
        TLRPC.MessageEntity r144 = r122.urlEntity;
        if (r144 == null) goto L206;
        int r154 = r144.offset;
        String r145 = TextUtils.substring(r1, r154, r144.length + r154);
    L207:
        TLRPC.MessageEntity r155 = r122.urlEntity;
        if ((r155 instanceof TLRPC.TL_messageEntityBotCommand) == false) goto L216;
        if (r52 >= 250) goto L197;
        r52 = r52 + 1;
        r3.setSpan(new URLSpanBotCommand(r145, r9, r122), r122.start, r122.end, 33);
    L212:
        int r21 = r05;
    L213:
        Spannable r06 = r3;
        byte r83 = r9;
        boolean r32 = false;
    L288:
        if (r32 == true) goto L296;
        if ((r122.flags & 256) == 0) goto L296;
        if (r68 >= 100) goto L296;
        r68 = r68 + 1;
        r06.setSpan(new TextStyleSpan(r122), r122.start, r122.end, 33);
    L296:
        r44 = r44 + 1;
        r3 = r06;
        r9 = r83;
        r05 = r21;
        goto L190
    L216:
        if ((r155 instanceof TLRPC.TL_messageEntityHashtag) == false) goto L218;
    L284:
        r21 = r05;
        r06 = r3;
        r83 = r9;
        int r135 = 250;
    L285:
        if (r52 >= r135) goto L296;
        r52 = r52 + 1;
        r06.setSpan(new URLSpanNoUnderline(r145, r122), r122.start, r122.end, 33);
    L282:
        r32 = false;
        goto L288
    L218:
        if ((r155 instanceof TLRPC.TL_messageEntityMention) == true) goto L284;
        if ((r155 instanceof TLRPC.TL_messageEntityCashtag) == false) goto L223;
        r21 = r05;
        r06 = r3;
        r135 = 250;
        r83 = r9;
        goto L285
    L223:
        if ((r155 instanceof TLRPC.TL_messageEntityEmail) == false) goto L227;
        if (r52 >= 250) goto L197;
        r52 = r52 + 1;
        r3.setSpan(new URLSpanReplacement("mailto:" + r145, r122), r122.start, r122.end, 33);
        goto L212
    L227:
        r21 = r05;
        if ((r155 instanceof TLRPC.TL_messageEntityUrl) == false) goto L246;
        if (r52 >= 250) goto L270;
        r52 = r52 + 1;
        if (r145.toLowerCase().contains("://") == true) goto L239;
        StringBuilder r72 = new StringBuilder();
        if (BotWebViewContainer.isTonsite(r145) == false) goto L237;
        String r136 = "tonsite://";
    L238:
        r72.append(r136);
        r72.append(r145);
        r145 = r72.toString();
        goto L239
    L237:
        r136 = AndroidUtilities.defaultUrlScheme(r145);
    L239:
        String r07 = r145.replaceAll("∕|⁄|%E2%81%84|%E2%88%95", "/");
        if (Browser.isTonsitePunycode(r07) == false) goto L243;
        r06 = r3;
        r83 = r9;
        r7 = true;
        goto L296
    L243:
        r3.setSpan(new URLSpanBrowser(r07, r122), r122.start, r122.end, 33);
    L244:
        r06 = r3;
        r83 = r9;
        r32 = false;
        r7 = true;
    L270:
        r06 = r3;
        r83 = r9;
        goto L296
    L246:
        if ((r155 instanceof TLRPC.TL_messageEntityFormattedDate) == false) goto L252;
        if (r52 >= 250) goto L270;
        r52 = r52 + 1;
        r3.setSpan(new FormattedDateSpan(r145, r122, (TLRPC.TL_messageEntityFormattedDate) r122.urlEntity), r122.start, r122.end, 33);
        goto L213
    L252:
        if ((r155 instanceof TLRPC.TL_messageEntityBankCard) == false) goto L255;
        r3.setSpan(new URLSpanNoUnderline("card:" + r145, r122), r122.start, r122.end, 33);
        goto L244
    L255:
        if ((r155 instanceof TLRPC.TL_messageEntityPhone) == false) goto L261;
        String r08 = PhoneFormat.stripExceptNumbers(r145);
        if (r145.startsWith("+") == false) goto L259;
        r08 = "+" + r08;
    L259:
        r3.setSpan(new URLSpanNoUnderline("tel:" + r08, r122), r122.start, r122.end, 33);
        goto L244
    L261:
        if ((r155 instanceof TLRPC.TL_messageEntityTextUrl) == false) goto L273;
        if (r52 >= 250) goto L270;
        r52 = r52 + 1;
        String r137 = r155.url;
        if (r137 == null) goto L269;
        r137 = r137.replaceAll("∕|⁄|%E2%81%84|%E2%88%95", "/");
    L269:
        if (Browser.isTonsitePunycode(r137) == true) goto L270;
        r3.setSpan(new URLSpanReplacement(r137, r122), r122.start, r122.end, 33);
        goto L213
    L273:
        if ((r155 instanceof TLRPC.TL_messageEntityMentionName) == false) goto L277;
        r3.setSpan(new URLSpanUserMention(_UrlKt.FRAGMENT_ENCODE_SET + ((TLRPC.TL_messageEntityMentionName) r122.urlEntity).user_id, r9, r122), r122.start, r122.end, 33);
    L275:
        r06 = r3;
        r83 = r9;
        goto L282
    L277:
        if ((r155 instanceof TLRPC.TL_inputMessageEntityMentionName) == false) goto L280;
        r3.setSpan(new URLSpanUserMention(_UrlKt.FRAGMENT_ENCODE_SET + ((TLRPC.TL_inputMessageEntityMentionName) r122.urlEntity).user_id.user_id, r9, r122), r122.start, r122.end, 33);
        goto L275
    L280:
        if ((r122.flags & 4) == 0) goto L283;
        Spannable r234 = r3;
        byte r262 = r9;
        r06 = r234;
        r83 = r262;
        r06.setSpan(new URLSpanMono(r234, r122.start, r122.end, r262, r122), r122.start, r122.end, 33);
        goto L282
    L283:
        r06 = r3;
        r83 = r9;
        r06.setSpan(new TextStyleSpan(r122), r122.start, r122.end, 33);
        r32 = true;
        goto L288
    L206:
        r145 = null;
        goto L207
    L201:
        if ((r122.urlEntity instanceof TLRPC.TL_messageEntityHashtag) == true) goto L203;
    L197:
        r21 = r05;
        goto L270
    L297:
        Spannable r09 = r3;
        int r2 = r11.size();
        int r33 = -1;
        int r45 = -1;
        int r53 = -1;
        int r69 = 0;
        boolean r84 = false;
        int r92 = 0;
    L298:
        if (r69 >= r2) goto L350;
        TLRPC.MessageEntity r102 = (TLRPC.MessageEntity) r11.get(r69);
        if (r102.length <= 0) goto L349;
        int r123 = r102.offset;
        if (r123 < 0) goto L349;
        if (r123 >= r1.length()) goto L349;
        if ((r102.offset + r102.length) <= r1.length()) goto L310;
        r102.length = r1.length() - r102.offset;
    L310:
        if ((r102 instanceof TLRPC.TL_messageEntityBlockquote) == false) goto L341;
        int r124 = r102.offset;
        int r138 = r102.length + r124;
        if (r33 < 0) goto L317;
        if (r124 > r45) goto L317;
        if (r84 != r102.collapsed) goto L317;
        boolean r125 = true;
    L318:
        if (r125 == false) goto L332;
        if (r45 <= 0) goto L332;
        if (r45 > r1.length()) goto L332;
        if (r1.charAt(r45 - 1) != '\n') goto L332;
        int r126 = Math.max(0, r53);
    L325:
        if (r126 >= r45) goto L330;
        if (Character.isWhitespace(r1.charAt(r126)) == false) goto L328;
        r126 = r126 + 1;
        goto L325
    L328:
        r125 = false;
        goto L332
    L330:
        r125 = true;
    L332:
        if (r125 == false) goto L336;
        if (r138 <= r45) goto L349;
        r53 = r102.offset;
    L335:
        r45 = r138;
        goto L349
    L336:
        if (r33 < 0) goto L338;
        QuoteSpan.putQuote(r09, r33, r45, r84);
    L338:
        r33 = r102.offset;
        r84 = r102.collapsed;
        r53 = r33;
    L317:
        r125 = false;
        goto L318
    L341:
        if ((r102 instanceof TLRPC.TL_messageEntityPre) == false) goto L347;
        if (r92 >= 50) goto L349;
        r92 = r92 + 1;
        int r127 = r102.offset;
        int r139 = r102.length + r127;
        r09.setSpan(new CodeHighlighting.Span(true, 0, null, r102.language, r09.subSequence(r127, r139).toString()), r127, r139, 33);
        goto L349
    L347:
        if ((r102 instanceof TLRPC.TL_messageEntityDiffReplace) == false) goto L349;
        int r128 = r102.offset;
        r09.setSpan(new SquigglyLinesSpan(), r128, r102.length + r128, 33);
    L349:
        r69 = r69 + 1;
        goto L298
    L350:
        if (r33 < 0) goto L352;
        QuoteSpan.putQuote(r09, r33, r45, r84);
        goto L352
    L17:
        if (r24 == false) goto L19;
        r9 = 1;
        goto L20
    L19:
        r9 = 0;
        goto L20
    L8:
        if (r5.length <= 0) goto L10;
        r7 = true;
        goto L11
    }

    private boolean addEntitiesToText(CharSequence r2, boolean r3) {
        return addEntitiesToText(r2, false, r3);
    }

    public static void addLinks(boolean r2, CharSequence r3) {
        addLinks(r2, r3, true, false);
    }

    public static void addLinks(boolean r1, CharSequence r2, boolean r3, boolean r4) {
        addLinks(r1, r2, r3, r4, false);
    }

    public static void addLinks(boolean r7, CharSequence r8, boolean r9, boolean r10, boolean r11) {
        if ((r8 instanceof Spannable) == true) goto L5;
        return;
    L5:
        if (containsUrls(r8) == false) goto L15;
        AndroidUtilities.addLinksSafe((Spannable) r8, 1, r11, false);     // Catch: Exception -> L8
    L10:
        addPhoneLinks(r8);
        addUrlsByPattern(r7, r8, r9, 0, 0, r10);
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L10
    }

    public static void addPaidReactions(int r5, TLRPC.MessageReactions r6, int r7, long r8, boolean r10) {
        TLRPC.MessageReactor r0 = null;
        boolean r1 = false;
        TLRPC.ReactionCount r3 = null;
        int r2 = 0;
    L4:
        if (r2 >= r6.results.size()) goto L9;
        if ((r6.results.get(r2).reaction instanceof TLRPC.TL_reactionPaid) == false) goto L8;
        r3 = r6.results.get(r2);
    L8:
        r2 = r2 + 1;
        goto L4
    L9:
        int r22 = 0;
    L11:
        if (r22 >= r6.top_reactors.size()) goto L16;
        if (r6.top_reactors.get(r22).my == true) goto L14;
        r22 = r22 + 1;
        goto L11
    L14:
        r0 = r6.top_reactors.get(r22);
    L16:
        if (r3 != null) goto L19;
        if (r7 <= 0) goto L19;
        r3 = new TLRPC.TL_reactionCount();
        r3.reaction = new TLRPC.TL_reactionPaid();
        r6.results.add(0, r3);
    L19:
        if (r3 == null) goto L24;
        r3.chosen = r10;
        int r102 = Math.max(0, r3.count + r7);
        r3.count = r102;
        if (r102 > 0) goto L24;
        r6.results.remove(r3);
    L24:
        if (r0 != null) goto L27;
        if (r7 <= 0) goto L27;
        r0 = new TLRPC.TL_messageReactor();
        r0.my = true;
        r6.top_reactors.add(r0);
    L27:
        if (r0 == null) goto L46;
        r0.count = Math.max(0, r0.count + r7);
        if (r8 != UserObject.ANONYMOUS) goto L31;
        r1 = true;
    L31:
        r0.anonymous = r1;
        if (r8 == 0) goto L36;
        if (r8 == UserObject.ANONYMOUS) goto L36;
        r0.peer_id = MessagesController.getInstance(r5).getPeer(r8);
    L38:
        if (r0.count > 0) goto L47;
        r6.top_reactors.remove(r0);
        return;
    L47:
        return;
    L36:
        r0.peer_id = MessagesController.getInstance(r5).getPeer(UserConfig.getInstance(r5).getClientUserId());
        goto L38
    }

    public static void addPhoneLinks(CharSequence r0) {
    }

    public static void addUrlsByPattern(boolean r18, CharSequence r19, boolean r20, int r21, int r22, boolean r23) {
        if (r19 == null) goto L121;
        int r3 = 3;
        int r4 = 1;
        if (r21 == 3) goto L19;
        if (r21 == 4) goto L19;
        if (r21 != 1) goto L15;
        if (instagramUrlPattern != null) goto L13;
        instagramUrlPattern = Pattern.compile("(^|\\s|\\()@[a-zA-Z\\d_.]{1,32}|(^|\\s|\\()#[\\w.]+");     // Catch: Exception -> L112
    L13:
        Matcher r5 = instagramUrlPattern.matcher(r19);     // Catch: Exception -> L112
    L23:
        if ((r19 instanceof Spannable) == false) goto L111;
        Spannable r6 = (Spannable) r19;     // Catch: Exception -> L112
        int r8 = 0;
    L26:
        if (r5.find() == false) goto L122;
        if (r8 >= 100) goto L123;
        int r9 = r5.start();     // Catch: Exception -> L112
        int r10 = r5.end();     // Catch: Exception -> L112
        if (r21 == r3) goto L69;
        if (r21 == 4) goto L69;
        char r13 = r19.charAt(r9);     // Catch: Exception -> L112
        if (r21 == 0) goto L43;
        if (r13 == '@') goto L39;
        if (r13 == '#') goto L39;
        r9 = r9 + 1;     // Catch: Exception -> L112
    L39:
        r13 = r19.charAt(r9);     // Catch: Exception -> L112
        if (r13 == '@') goto L49;
        if (r13 == '#') goto L49;
        int r17 = r8;
    L92:
        r8 = r17;
        r3 = 3;
    L93:
        r4 = 1;
    L49:
        if (r21 != r4) goto L56;
        if (r13 != '@') goto L55;
        URLSpanNoUnderline r11 = new URLSpanNoUnderline("https://instagram.com/" + r19.subSequence(r9 + 1, r10));     // Catch: Exception -> L112
    L53:
        int r7 = r3;
        r17 = r8;
    L54:
        int r16 = 0;
    L100:
        if (r11 == null) goto L109;
        if (r23 == false) goto L107;
        Object[] r42 = (ClickableSpan[]) r6.getSpans(r9, r10, ClickableSpan.class);     // Catch: Exception -> L112
        if (r42 == null) goto L107;
        if (r42.length <= 0) goto L107;
        r6.removeSpan(r42[r16]);     // Catch: Exception -> L112
    L107:
        r6.setSpan(r11, r9, r10, r16);     // Catch: Exception -> L112
        r8 = r17 + 1;
    L110:
        r3 = r7;
        goto L93
    L109:
        r8 = r17;
        goto L110
    L55:
        r11 = new URLSpanNoUnderline("https://www.instagram.com/explore/tags/" + r19.subSequence(r9 + 1, r10));     // Catch: Exception -> L112
        goto L53
    L56:
        if (r21 != 2) goto L61;
        if (r13 != '@') goto L59;
        r11 = new URLSpanNoUnderline("https://twitter.com/" + r19.subSequence(r9 + 1, r10));     // Catch: Exception -> L112
        goto L53
    L59:
        r11 = new URLSpanNoUnderline("https://twitter.com/hashtag/" + r19.subSequence(r9 + 1, r10));     // Catch: Exception -> L112
        goto L53
    L61:
        if (r19.charAt(r9) != '/') goto L65;
        if (r20 == false) goto L64;
        r11 = new URLSpanBotCommand(r19.subSequence(r9, r10).toString(), r18 ? 1 : 0);     // Catch: Exception -> L112
        goto L53
    L64:
        r7 = r3;
        r17 = r8;
        r11 = null;
        goto L54
    L65:
        String r112 = r19.subSequence(r9, r10).toString();     // Catch: Exception -> L112
        if (r112 == null) goto L68;
        r112 = r112.replaceAll("∕|⁄|%E2%81%84|%E2%88%95", "/");     // Catch: Exception -> L112
    L68:
        r7 = r3;
        r17 = r8;
        r11 = new URLSpanNoUnderline(r112);     // Catch: Exception -> L112
        goto L54
    L43:
        if (r13 == '@') goto L49;
        if (r13 == '#') goto L49;
        if (r13 == '/') goto L49;
        if (r13 == '$') goto L49;
        r9 = r9 + 1;     // Catch: Exception -> L112
    L69:
        int r113 = r5.start(r4);     // Catch: Exception -> L112
        int r14 = r5.end(r4);     // Catch: Exception -> L112
        int r15 = r5.start(2);     // Catch: Exception -> L112
        int r12 = r5.end(2);     // Catch: Exception -> L112
        int r43 = r5.start(r3);     // Catch: Exception -> L112
        r16 = 0;
        int r72 = r5.end(r3);     // Catch: Exception -> L112
        int r32 = r5.start(4);     // Catch: Exception -> L112
        r17 = r8;
        int r82 = r5.end(4);     // Catch: Exception -> L112
        int r122 = Utilities.parseInt(r19.subSequence(r15, r12));     // Catch: Exception -> L112
        int r44 = Utilities.parseInt(r19.subSequence(r43, r72));     // Catch: Exception -> L112
        if (r113 < 0) goto L73;
        if (r14 < 0) goto L73;
        int r114 = Utilities.parseInt(r19.subSequence(r113, r14));     // Catch: Exception -> L112
    L74:
        if (r32 < 0) goto L78;
        if (r82 < 0) goto L78;
        String r142 = r19.subSequence(r32, r82).toString();     // Catch: Exception -> L112
    L79:
        if (r32 >= 0) goto L81;
        if (r82 >= 0) goto L81;
    L82:
        URLSpan[] r33 = (URLSpan[]) r6.getSpans(r9, r10, URLSpan.class);     // Catch: Exception -> L112
        if (r33 != null) goto L85;
    L87:
        int r45 = r44 + (r122 * 60);     // Catch: Exception -> L112
        if (r114 <= 0) goto L91;
        r45 = r45 + (r114 * 3600);     // Catch: Exception -> L112
    L91:
        if (r45 > r22) goto L92;
        r7 = 3;
        if (r21 != 3) goto L98;
        URLSpanNoUnderline r83 = new URLSpanNoUnderline("video?" + r45);     // Catch: Exception -> L112
    L97:
        r11 = r83;
        r11.label = r142;     // Catch: Exception -> L112
        goto L100
    L98:
        r83 = new URLSpanNoUnderline("audio?" + r45);     // Catch: Exception -> L112
        goto L97
    L85:
        if (r33.length <= 0) goto L87;
    L81:
        r10 = r72;
    L78:
        r142 = null;
    L73:
        r114 = -1;
        goto L74
    L123:
        return;
    L122:
        return;
    L111:
        return;
    L15:
        if (urlPattern != null) goto L17;
        urlPattern = Pattern.compile("(^|\\s)/[a-zA-Z@\\d_]{1,255}(?![a-zA-Z@\\d_/])|(^|\\s|\\()@[a-zA-Z\\d_]{1,32}|(^|\\s|\\()#[^0-9][\\w.]+(@[^0-9][\\w.]+)?|(^|\\s|\\()\\$[^0-9][\\w.]+(@[^0-9][\\w.]+)?|(^|\\s)\\$[A-Z]{3,8}([ ,.]|$)");     // Catch: Exception -> L112
    L17:
        r5 = urlPattern.matcher(r19);     // Catch: Exception -> L112
    L19:
        if (videoTimeUrlPattern != null) goto L21;
        videoTimeUrlPattern = Pattern.compile("\\b(?:(\\d{1,2}):)?(\\d{1,3}):([0-5][0-9])\\b(?: - |)([^\\n]*)");     // Catch: Exception -> L112
    L21:
        r5 = videoTimeUrlPattern.matcher(r19);     // Catch: Exception -> L112
    L112:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    private boolean allowsBigEmoji() {
        if (SharedConfig.allowBigEmoji == true) goto L5;
        return false;
    L5:
        TLRPC.Message r0 = this.messageOwner;
        if (r0 == null) goto L28;
        TLRPC.Peer r02 = r0.peer_id;
        if (r02 == null) goto L28;
        if (r02.channel_id == 0) goto L12;
    L14:
        MessagesController r03 = MessagesController.getInstance(this.currentAccount);
        TLRPC.Peer r7 = this.messageOwner.peer_id;
        long r3 = r7.channel_id;
        if (r3 != 0) goto L18;
        r3 = r7.chat_id;
    L18:
        TLRPC.Chat r72 = r03.getChat(Long.valueOf(r3));
        if (r72 == null) goto L23;
        if (r72.gigagroup == true) goto L28;
    L23:
        if (ChatObject.isActionBanned(r72, 8) == false) goto L28;
        if (ChatObject.hasAdminRights(r72) == true) goto L28;
        return false;
    L12:
        if (r02.chat_id != 0) goto L14;
    L28:
        return true;
    }

    private boolean applyEntities() {
        generateLinkDescription();
        spoilLoginCode();
        boolean r1 = false;
        if (this.messageOwner.send_state == 0) goto L5;
        boolean r0 = false;
    L6:
        if (r0 == false) goto L8;
    L32:
        if (r1 == false) goto L34;
        addLinks(isOutOwner(), this.messageText, true, true);
    L36:
        if (isYouTubeVideo() == false) goto L38;
        addUrlsByPattern(isOutOwner(), this.messageText, false, 3, Integer.MAX_VALUE, false);
    L40:
        if ((this.messageText instanceof Spannable) == true) goto L43;
        this.messageText = new SpannableStringBuilder(this.messageText);
    L43:
        return addEntitiesToText(this.messageText, r1);
    L38:
        applyTimestampsHighlightForReplyMsg();
        goto L40
    L34:
        addPhoneLinks(this.messageText);
        goto L36
    L8:
        if (this.eventId != 0) goto L31;
        TLRPC.Message r02 = this.messageOwner;
        if ((r02 instanceof TLRPC.TL_message_old) == true) goto L31;
        if ((r02 instanceof TLRPC.TL_message_old2) == true) goto L31;
        if ((r02 instanceof TLRPC.TL_message_old3) == true) goto L31;
        if ((r02 instanceof TLRPC.TL_message_old4) == true) goto L31;
        if ((r02 instanceof TLRPC.TL_messageForwarded_old) == true) goto L31;
        if ((r02 instanceof TLRPC.TL_messageForwarded_old2) == true) goto L31;
        if ((r02 instanceof TLRPC.TL_message_secret) == true) goto L31;
        if ((getMedia(r02) instanceof TLRPC.TL_messageMediaInvoice) == true) goto L31;
        if (isOut() == false) goto L30;
        if (this.messageOwner.send_state != 0) goto L31;
    L30:
        if (this.messageOwner.id >= 0) goto L32;
    L31:
        r1 = true;
        goto L32
    L5:
        r0 = !getEntities().isEmpty();
        goto L6
    }

    public static boolean canAutoplayAnimatedSticker(TLRPC.Document r2) {
        if (isAnimatedStickerDocument(r2, true) == true) goto L7;
        if (isVideoStickerDocument(r2) == true) goto L7;
        return false;
    L7:
        if (LiteMode.isEnabled(1) == false) goto L11;
        return true;
    L11:
        return false;
    }

    public static boolean canCreateStripedThubms() {
        if (SharedConfig.getDevicePerformanceClass() != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean canDeleteMessage(int r7, boolean r8, TLRPC.Message r9, TLRPC.Chat r10) {
        if (r9 != null) goto L6;
        return false;
    L6:
        if (ChatObject.isChannelAndNotMegaGroup(r10) == false) goto L11;
        if ((r9.action instanceof TLRPC.TL_messageActionChatJoinedByRequest) == false) goto L11;
        return false;
    L11:
        if (r9.id >= 0) goto L13;
        return true;
    L13:
        if (r10 != null) goto L20;
        TLRPC.Peer r1 = r9.peer_id;
        if (r1 == null) goto L20;
        if (r1.channel_id == 0) goto L20;
        r10 = MessagesController.getInstance(r7).getChat(Long.valueOf(r9.peer_id.channel_id));
    L20:
        if (ChatObject.isChannel(r10) == false) goto L65;
        if (r8 == true) goto L23;
    L35:
        boolean r72 = r9.out;
        if (r72 == true) goto L38;
    L45:
        if (r8 == false) goto L47;
    L64:
        return true;
    L47:
        if (r9.id != 1) goto L49;
    L63:
        return false;
    L49:
        if (r10.creator == true) goto L64;
        TLRPC.TL_chatAdminRights r82 = r10.admin_rights;
        if (r82 == null) goto L60;
        if (r82.delete_messages == true) goto L64;
        if (r72 == false) goto L60;
        if (r10.megagroup == true) goto L64;
        if (r82.post_messages == true) goto L64;
    L60:
        if (r10.megagroup == false) goto L63;
        if (r72 == false) goto L63;
    L38:
        if ((r9 instanceof TLRPC.TL_messageService) == false) goto L45;
        if (r9.id != 1) goto L42;
    L44:
        return false;
    L42:
        if (ChatObject.canUserDoAdminAction(r10, 13) == false) goto L44;
        return true;
    L23:
        if (r10.megagroup == true) goto L35;
        if (r10.creator == true) goto L34;
        TLRPC.TL_chatAdminRights r73 = r10.admin_rights;
        if (r73 != null) goto L29;
    L33:
        return false;
    L29:
        if (r73.delete_messages == true) goto L34;
        if (r9.out == false) goto L33;
    L34:
        return true;
    L65:
        if (r8 == false) goto L67;
    L72:
        return true;
    L67:
        if (isOut(r9) == true) goto L72;
        if (ChatObject.isChannel(r10) == false) goto L72;
        return false;
    }

    public static boolean canEditMessage(int r9, TLRPC.Message r10, TLRPC.Chat r11, boolean r12) {
        if (r12 == true) goto L5;
    L7:
        if (r11 != null) goto L9;
    L17:
        if (r10 != null) goto L19;
    L183:
        return false;
    L19:
        if (r10.peer_id == null) goto L183;
        TLRPC.MessageAction r1 = r10.action;
        if (r1 == null) goto L25;
        if ((r1 instanceof TLRPC.TL_messageActionEmpty) == false) goto L183;
    L25:
        if (isForwardedMessage(r10) == true) goto L183;
        if (r10.via_bot_id != 0) goto L183;
        if (r10.id < 0) goto L183;
        TLRPC.MessageMedia r13 = getMedia(r10);
        if (r13 == null) goto L45;
        if (r10.rich_message != null) goto L45;
        if (isRoundVideoDocument(r13.document) == false) goto L38;
    L43:
        return false;
    L38:
        if (isStickerDocument(r13.document) == true) goto L43;
        if (isAnimatedStickerDocument(r13.document, true) == true) goto L43;
        if (isLocationMessage(r10) == true) goto L43;
    L45:
        if (r10.paid_suggested_post_stars == true) goto L183;
        if (r10.paid_suggested_post_ton == true) goto L183;
        TLRPC.Peer r5 = r10.from_id;
        if ((r5 instanceof TLRPC.TL_peerUser) == false) goto L60;
        long r52 = r5.user_id;
        if (r52 != r10.peer_id.user_id) goto L60;
        if (r52 != UserConfig.getInstance(r9).getClientUserId()) goto L60;
        if (isLiveLocationMessage(r10) == true) goto L60;
        if ((r13 instanceof TLRPC.TL_messageMediaContact) == true) goto L60;
        return true;
    L60:
        if (r11 == null) goto L62;
    L66:
        if (r13 == null) goto L83;
        if (r10.rich_message != null) goto L83;
        if ((r13 instanceof TLRPC.TL_messageMediaEmpty) == true) goto L83;
        if ((r13 instanceof TLRPC.TL_messageMediaPhoto) == true) goto L83;
        if ((r13 instanceof TLRPC.TL_messageMediaDocument) == true) goto L83;
        if ((r13 instanceof TLRPC.TL_messageMediaWebPage) == true) goto L83;
        if ((r13 instanceof TLRPC.TL_messageMediaPaidMedia) == true) goto L83;
        if ((r13 instanceof TLRPC.TL_messageMediaToDo) == true) goto L83;
        return false;
    L83:
        if (ChatObject.isChannel(r11) == false) goto L94;
        if (r11.megagroup == true) goto L94;
        if (r11.creator == true) goto L92;
        TLRPC.TL_chatAdminRights r53 = r11.admin_rights;
        if (r53 == null) goto L94;
        if (r53.edit_messages == false) goto L94;
    L92:
        return true;
    L94:
        if (r10.out == false) goto L109;
        if (r11 == null) goto L109;
        if (r11.megagroup == false) goto L109;
        if (r11.creator == true) goto L108;
        TLRPC.TL_chatAdminRights r54 = r11.admin_rights;
        if (r54 != null) goto L103;
    L104:
        TLRPC.TL_chatBannedRights r55 = r11.default_banned_rights;
        if (r55 == null) goto L109;
        if (r55.pin_messages == true) goto L109;
    L103:
        if (r54.pin_messages == false) goto L104;
    L108:
        return true;
    L109:
        if (r12 == true) goto L114;
        if (Math.abs(r10.date - ConnectionsManager.getInstance(r9).getCurrentTime()) <= MessagesController.getInstance(r9).maxEditTime) goto L114;
        return false;
    L114:
        if (r10.peer_id.channel_id == 0) goto L116;
        if (r11 != null) goto L145;
    L148:
        if (r11 == null) goto L183;
        if (r11.megagroup == true) goto L183;
        if (r11.creator == true) goto L162;
        TLRPC.TL_chatAdminRights r92 = r11.admin_rights;
        if (r92 == null) goto L183;
        if (r92.edit_messages == true) goto L162;
        if (r10.out == false) goto L183;
        if (r92.post_messages == false) goto L183;
    L162:
        if (r10.post == false) goto L183;
    L164:
        if (r10.rich_message == null) goto L166;
    L182:
        return true;
    L166:
        if ((r13 instanceof TLRPC.TL_messageMediaPhoto) == true) goto L182;
        if ((r13 instanceof TLRPC.TL_messageMediaDocument) == false) goto L174;
        if (isStickerMessage(r10) == true) goto L174;
        if (isAnimatedStickerMessage(r10) == false) goto L182;
    L174:
        if ((r13 instanceof TLRPC.TL_messageMediaEmpty) == true) goto L182;
        if ((r13 instanceof TLRPC.TL_messageMediaWebPage) == true) goto L182;
        if ((r13 instanceof TLRPC.TL_messageMediaPaidMedia) == true) goto L182;
        if ((r13 instanceof TLRPC.TL_messageMediaToDo) == true) goto L182;
        if (r13 != null) goto L183;
    L145:
        if (r11.megagroup == false) goto L148;
        if (r10.out == true) goto L164;
    L116:
        if (r10.out == true) goto L122;
        TLRPC.Peer r112 = r10.from_id;
        if ((r112 instanceof TLRPC.TL_peerUser) == true) goto L120;
    L141:
        return false;
    L120:
        if (r112.user_id != UserConfig.getInstance(r9).getClientUserId()) goto L141;
    L122:
        if (r10.rich_message == null) goto L124;
    L142:
        return true;
    L124:
        if ((r13 instanceof TLRPC.TL_messageMediaPhoto) == true) goto L142;
        if ((r13 instanceof TLRPC.TL_messageMediaDocument) == false) goto L132;
        if (isStickerMessage(r10) == true) goto L132;
        if (isAnimatedStickerMessage(r10) == false) goto L142;
    L132:
        if ((r13 instanceof TLRPC.TL_messageMediaEmpty) == true) goto L142;
        if ((r13 instanceof TLRPC.TL_messageMediaWebPage) == true) goto L142;
        if ((r13 instanceof TLRPC.TL_messageMediaPaidMedia) == true) goto L142;
        if ((r13 instanceof TLRPC.TL_messageMediaToDo) == true) goto L142;
        if (r13 != null) goto L141;
    L62:
        if (r10.peer_id.channel_id == 0) goto L66;
        r11 = MessagesController.getInstance(r9).getChat(Long.valueOf(r10.peer_id.channel_id));
        if (r11 != null) goto L66;
        return false;
    L9:
        if (r11.left == true) goto L13;
        if (r11.kicked == false) goto L17;
    L13:
        if (r11.megagroup == true) goto L15;
    L16:
        return false;
    L15:
        if (r11.has_link == true) goto L17;
    L5:
        if (r10.date >= (ConnectionsManager.getInstance(r9).getCurrentTime() - 60)) goto L7;
        return false;
    }

    public static boolean canEditMessageAnytime(int r9, TLRPC.Message r10, TLRPC.Chat r11) {
        if (r10 != null) goto L5;
    L67:
        return false;
    L5:
        if (r10.peer_id == null) goto L67;
        if (getMedia(r10) != null) goto L9;
    L14:
        TLRPC.MessageAction r1 = r10.action;
        if (r1 == null) goto L19;
        if ((r1 instanceof TLRPC.TL_messageActionEmpty) == false) goto L67;
    L19:
        if (isForwardedMessage(r10) == true) goto L67;
        if (r10.via_bot_id != 0) goto L67;
        if (r10.id < 0) goto L67;
        TLRPC.Peer r12 = r10.from_id;
        if ((r12 instanceof TLRPC.TL_peerUser) == false) goto L34;
        long r3 = r12.user_id;
        if (r3 != r10.peer_id.user_id) goto L34;
        if (r3 != UserConfig.getInstance(r9).getClientUserId()) goto L34;
        if (isLiveLocationMessage(r10) == true) goto L34;
        return true;
    L34:
        if (r11 != null) goto L41;
        if (r10.peer_id.channel_id == 0) goto L41;
        r11 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(r10.peer_id.channel_id));
        if (r11 != null) goto L41;
        return false;
    L41:
        if (ChatObject.isChannel(r11) == false) goto L52;
        if (r11.megagroup == true) goto L52;
        if (r11.creator == true) goto L50;
        TLRPC.TL_chatAdminRights r92 = r11.admin_rights;
        if (r92 == null) goto L52;
        if (r92.edit_messages == false) goto L52;
    L50:
        return true;
    L52:
        if (r10.out == false) goto L67;
        if (r11 == null) goto L67;
        if (r11.megagroup == false) goto L67;
        if (r11.creator == true) goto L66;
        TLRPC.TL_chatAdminRights r93 = r11.admin_rights;
        if (r93 != null) goto L61;
    L62:
        TLRPC.TL_chatBannedRights r94 = r11.default_banned_rights;
        if (r94 == null) goto L67;
        if (r94.pin_messages == true) goto L67;
    L61:
        if (r93.pin_messages == false) goto L62;
    L66:
        return true;
    L9:
        if (isRoundVideoDocument(getMedia(r10).document) == true) goto L67;
        if (isStickerDocument(getMedia(r10).document) == true) goto L67;
        if (isAnimatedStickerDocument(getMedia(r10).document, true) == true) goto L67;
        goto L14
    }

    public static boolean canEditMessageScheduleTime(int r6, TLRPC.Message r7, TLRPC.Chat r8) {
        if (r7.video_processing_pending == false) goto L5;
        return false;
    L5:
        if (r8 != null) goto L12;
        if (r7.peer_id.channel_id == 0) goto L12;
        r8 = MessagesController.getInstance(r6).getChat(Long.valueOf(r7.peer_id.channel_id));
        if (r8 != null) goto L12;
        return false;
    L12:
        if (ChatObject.isChannel(r8) == true) goto L14;
    L26:
        return true;
    L14:
        if (r8.megagroup == true) goto L26;
        if (r8.creator == true) goto L26;
        TLRPC.TL_chatAdminRights r62 = r8.admin_rights;
        if (r62 != null) goto L21;
    L25:
        return false;
    L21:
        if (r62.edit_messages == false) goto L23;
    L24:
        return true;
    L23:
        if (r7.out == false) goto L25;
        goto L24
    }

    public static boolean canPreviewDocument(TLRPC.Document r5) {
        if (r5 == null) goto L39;
        String r1 = r5.mime_type;
        if (r1 == null) goto L39;
        if (isDocumentHasThumb(r5) == false) goto L15;
        if (r1.equalsIgnoreCase("image/png") == false) goto L11;
    L18:
        int r12 = 0;
    L20:
        if (r12 >= r5.attributes.size()) goto L39;
        TLRPC.DocumentAttribute r2 = r5.attributes.get(r12);
        if ((r2 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L23;
        r12 = r12 + 1;
        goto L20
    L23:
        TLRPC.TL_documentAttributeImageSize r22 = (TLRPC.TL_documentAttributeImageSize) r2;
        if (r22.w < 6000) goto L26;
    L28:
        return false;
    L26:
        if (r22.h >= 6000) goto L28;
        return true;
    L11:
        if (r1.equalsIgnoreCase("image/jpg") == true) goto L18;
        if (r1.equalsIgnoreCase("image/jpeg") == true) goto L18;
    L15:
        if (Build.VERSION.SDK_INT < 26) goto L31;
        if (r1.equalsIgnoreCase("image/heic") == true) goto L18;
    L31:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L39;
        String r52 = FileLoader.getDocumentFileName(r5);
        if (r52.startsWith("tg_secret_sticker") == false) goto L38;
        if (r52.endsWith("json") == false) goto L38;
        return true;
    L38:
        return r52.endsWith(".svg");
    L39:
        return false;
    }

    public static boolean canShowVotersList(TLRPC.TL_messageMediaPoll r3) {
        if (r3 == null) goto L21;
        TLRPC.PollResults r1 = r3.results;
        if (r1 == null) goto L21;
        if (r1.results.isEmpty() == true) goto L21;
        TLRPC.Poll r12 = r3.poll;
        if (r12.public_voters == false) goto L21;
        if (r12.closed == false) goto L14;
        return true;
    L14:
        if (r12.creator == false) goto L16;
        return true;
    L16:
        if (isVoted(r3) == false) goto L21;
        if (r3.poll.hide_results_until_close == true) goto L21;
        return true;
    L21:
        return false;
    }

    public static boolean canUnvote(TLRPC.TL_messageMediaPoll r4) {
        if (r4 == null) goto L18;
        TLRPC.PollResults r1 = r4.results;
        if (r1 == null) goto L18;
        if (r1.results.isEmpty() == true) goto L18;
        if (r4.poll.revoting_disabled == true) goto L18;
        int r12 = r4.results.results.size();
        int r2 = 0;
    L12:
        if (r2 >= r12) goto L18;
        if (r4.results.results.get(r2).chosen == true) goto L15;
        r2 = r2 + 1;
        goto L12
    L15:
        return true;
    L18:
        return false;
    }

    public static CharSequence channelSpan() {
        if (channelSpan != null) goto L6;
        channelSpan = new SpannableStringBuilder("c");
        ColoredImageSpan r0 = new ColoredImageSpan(R.drawable.msg_folders_channels);
        r0.setScale(0.7f, 0.7f);
        ((SpannableStringBuilder) channelSpan).setSpan(r0, 0, 1, 33);
    L6:
        return channelSpan;
    }

    private void checkEmojiOnly(Integer r12) {
        int r1 = -1;
        int r3 = 0;
        if (r12 != null) goto L5;
    L94:
        CharSequence r122 = this.messageText;
        AnimatedEmojiSpan[] r123 = (AnimatedEmojiSpan[]) ((Spannable) r122).getSpans(0, r122.length(), AnimatedEmojiSpan.class);
        if (r123 != null) goto L97;
    L103:
        this.totalAnimatedEmojiCount = 0;
        return;
    L97:
        if (r123.length <= 0) goto L103;
        this.totalAnimatedEmojiCount = r123.length;
        int r11 = 0;
    L100:
        if (r11 >= r123.length) goto L117;
        r123[r11].replaceFontMetrics(Theme.chat_msgTextPaint.getFontMetricsInt(), (int) (Theme.chat_msgTextPaint.getTextSize() + AndroidUtilities.dp(4.0f)), -1);
        r123[r11].full = false;
        r11 = r11 + 1;
        goto L100
    L117:
        return;
    L5:
        if (r12.intValue() < 1) goto L94;
        if (this.messageOwner == null) goto L94;
        if (hasNonEmojiEntities() == true) goto L94;
        CharSequence r4 = this.messageText;
        Emoji.EmojiSpan[] r42 = (Emoji.EmojiSpan[]) ((Spannable) r4).getSpans(0, r4.length(), Emoji.EmojiSpan.class);
        CharSequence r6 = this.messageText;
        AnimatedEmojiSpan[] r0 = (AnimatedEmojiSpan[]) ((Spannable) r6).getSpans(0, r6.length(), AnimatedEmojiSpan.class);
        int r124 = r12.intValue();
        if (r42 != null) goto L13;
        int r62 = 0;
    L14:
        if (r0 != null) goto L16;
        int r7 = 0;
    L17:
        this.emojiOnlyCount = Math.max(r124, r62 + r7);
        if (r0 != null) goto L20;
        int r125 = 0;
    L21:
        this.totalAnimatedEmojiCount = r125;
        this.animatedEmojiCount = 0;
        if (r0 == null) goto L29;
        int r126 = r0.length;
        int r63 = 0;
    L24:
        if (r63 >= r126) goto L29;
        if (r0[r63].standard == true) goto L28;
        this.animatedEmojiCount++;
    L28:
        r63 = r63 + 1;
    L29:
        int r127 = this.emojiOnlyCount;
        if (r42 != null) goto L32;
        int r64 = 0;
    L33:
        int r65 = r127 - r64;
        if (r0 != null) goto L36;
        int r72 = 0;
    L38:
        if ((r65 - r72) <= 0) goto L40;
        boolean r66 = true;
    L41:
        this.hasUnwrappedEmoji = r66;
        if (r66 == false) goto L50;
        if (r0 != null) goto L45;
        return;
    L45:
        if (r0.length <= 0) goto L112;
        int r112 = 0;
    L48:
        if (r112 >= r0.length) goto L115;
        r0[r112].replaceFontMetrics(Theme.chat_msgTextPaint.getFontMetricsInt(), (int) (Theme.chat_msgTextPaint.getTextSize() + AndroidUtilities.dp(4.0f)), -1);
        r0[r112].full = false;
        r112 = r112 + 1;
        goto L48
    L115:
        return;
    L112:
        return;
    L50:
        int r67 = this.animatedEmojiCount;
        if (r127 != r67) goto L53;
        boolean r73 = true;
    L54:
        int r10 = 2;
        switch(Math.max(r127, r67)) {
            case 0: goto L77;
            case 1: goto L77;
            case 2: goto L77;
            case 3: goto L73;
            case 4: goto L68;
            case 5: goto L64;
            case 6: goto L60;
            default: goto L57;
        };
    L60:
        com.exteragram.messenger.utils.ui.TextPaint[] r113 = Theme.chat_msgTextPaintEmoji;
        if (r73 == false) goto L63;
        com.exteragram.messenger.utils.ui.TextPaint r114 = r113[4];
    L81:
        int r128 = (int) (r114.getTextSize() + AndroidUtilities.dp(4.0f));
        if (r42 != null) goto L84;
    L88:
        if (r0 != null) goto L90;
        return;
    L90:
        if (r0.length <= 0) goto L114;
        int r13 = r0.length;
    L92:
        if (r3 >= r13) goto L116;
        AnimatedEmojiSpan r2 = r0[r3];
        r2.replaceFontMetrics(r114.getFontMetricsInt(), r128, r10);
        r2.full = true;
        r3 = r3 + 1;
        goto L92
    L116:
        return;
    L114:
        return;
    L84:
        if (r42.length <= 0) goto L88;
        int r14 = r42.length;
        int r22 = 0;
    L86:
        if (r22 >= r14) goto L88;
        r42[r22].replaceFontMetrics(r114.getFontMetricsInt(), r128);
        r22 = r22 + 1;
        goto L86
    L63:
        r114 = r113[5];
        goto L81
    L64:
        com.exteragram.messenger.utils.ui.TextPaint[] r115 = Theme.chat_msgTextPaintEmoji;
        if (r73 == false) goto L67;
        r114 = r115[3];
        goto L81
    L67:
        r114 = r115[5];
        goto L81
    L68:
        com.exteragram.messenger.utils.ui.TextPaint[] r116 = Theme.chat_msgTextPaintEmoji;
        if (r73 == false) goto L71;
        r114 = r116[2];
    L72:
        r10 = 1;
        goto L81
    L71:
        r114 = r116[4];
        goto L72
    L73:
        com.exteragram.messenger.utils.ui.TextPaint[] r117 = Theme.chat_msgTextPaintEmoji;
        if (r73 == false) goto L76;
        r114 = r117[1];
        goto L72
    L76:
        r114 = r117[3];
        goto L72
    L77:
        com.exteragram.messenger.utils.ui.TextPaint[] r118 = Theme.chat_msgTextPaintEmoji;
        if (r73 == false) goto L80;
        r114 = r118[0];
        goto L72
    L80:
        r114 = r118[2];
        goto L72
    L57:
        if (this.emojiOnlyCount <= 9) goto L59;
        r1 = 0;
    L59:
        r114 = Theme.chat_msgTextPaintEmoji[5];
        r10 = r1;
        goto L81
    L53:
        r73 = false;
        goto L54
    L40:
        r66 = false;
        goto L41
    L36:
        r72 = r0.length;
        goto L38
    L32:
        r64 = r42.length;
        goto L33
    L20:
        r125 = r0.length;
        goto L21
    L16:
        r7 = r0.length;
        goto L17
    L13:
        r62 = r42.length;
        goto L14
    }

    private void checkEmojiOnly(int[] r2) {
        if (r2 != null) goto L4;
        Integer r22 = null;
    L5:
        checkEmojiOnly(r22);
        return;
    L4:
        r22 = Integer.valueOf(r2[0]);
        goto L5
    }

    public static SpannableStringBuilder checkboxSpan(boolean r1) {
        if (r1 == false) goto L4;
        String r0 = "✅";
    L5:
        if (r1 == false) goto L7;
        int r12 = R.drawable.iv_checkbox_on;
    L9:
        return span(r0, r12);
    L7:
        r12 = R.drawable.iv_checkbox_off;
        goto L9
    L4:
        r0 = "☑️";
        goto L5
    }

    public static boolean containsUrls(CharSequence r14) {
        if (r14 != null) goto L5;
    L58:
        return false;
    L5:
        if (r14.length() < 2) goto L58;
        if (r14.length() > 20480) goto L58;
        int r1 = r14.length();
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        char r7 = 0;
    L10:
        if (r3 >= r1) goto L58;
        char r8 = r14.charAt(r3);
        if (r8 >= '0') goto L14;
    L19:
        if (r8 == ' ') goto L21;
        if (r4 <= 0) goto L21;
    L23:
        if (r8 != '@') goto L25;
    L29:
        if (r3 == 0) goto L57;
    L30:
        if (r3 == 0) goto L37;
        int r9 = r3 - 1;
        if (r14.charAt(r9) == ' ') goto L57;
        if (r14.charAt(r9) == '\n') goto L57;
    L37:
        if (r8 != ':') goto L41;
        if (r5 != 0) goto L40;
        r5 = 1;
    L56:
        r3 = r3 + 1;
        r7 = r8;
    L40:
        r5 = 0;
        goto L56
    L41:
        if (r8 != '/') goto L47;
        if (r5 == 2) goto L43;
        if (r5 != 1) goto L40;
        r5 = r5 + 1;
        goto L56
    L43:
        return true;
    L47:
        if (r8 != '.') goto L52;
        if (r6 != 0) goto L51;
        if (r7 == ' ') goto L51;
        r6 = r6 + 1;
    L51:
        r6 = 0;
        goto L56
    L52:
        if (r8 == ' ') goto L51;
        if (r7 != '.') goto L51;
        if (r6 != 1) goto L51;
        return true;
    L57:
        return true;
    L25:
        if (r8 == '#') goto L29;
        if (r8 == '/') goto L29;
        if (r8 != '$') goto L30;
    L21:
        r4 = 0;
        goto L23
    L14:
        if (r8 > '9') goto L19;
        r4 = r4 + 1;
        if (r4 >= 6) goto L17;
        r5 = 0;
        r6 = 0;
        goto L23
    L17:
        return true;
    }

    private void createDateArray(int r3, TLRPC.TL_channelAdminLogEvent r4, ArrayList<MessageObject> r5, HashMap<String, ArrayList<MessageObject>> r6, boolean r7) {
        if (r6.get(this.dateKey) != null) goto L10;
        r6.put(this.dateKey, new ArrayList());
        TLRPC.TL_message r2 = new TLRPC.TL_message();
        r2.message = LocaleController.formatDateChat(r4.date);
        r2.id = 0;
        r2.date = r4.date;
        MessageObject r42 = new MessageObject(r3, r2, false, false);
        r42.type = 10;
        r42.contentType = 1;
        r42.isDateObject = true;
        if (r7 == false) goto L8;
        r5.add(0, r42);
        return;
    L8:
        r5.add(r42);
        return;
    }

    private void createPathThumb() {
        TLRPC.Document r0 = getDocument();
        if (r0 != null) goto L5;
        return;
    L5:
        this.pathThumb = DocumentObject.getSvgThumb(r0, Theme.key_chat_serviceBackground, 1.0f);
    }

    public static void cutIntoRanges(CharSequence r19, ArrayList<TextRange> r20) {
        if (r19 != null) goto L6;
        return;
    L6:
        if ((r19 instanceof Spanned) == true) goto L9;
        r20.add(new TextRange(0, r19.length()));
        return;
    L9:
        TreeSet r2 = new TreeSet();
        HashMap r4 = new HashMap();
        HashMap r5 = new HashMap();
        HashMap r6 = new HashMap();
        Spanned r7 = (Spanned) r19;
        QuoteSpan.QuoteStyleSpan[] r8 = (QuoteSpan.QuoteStyleSpan[]) r7.getSpans(0, r7.length(), QuoteSpan.QuoteStyleSpan.class);
        int r9 = 0;
    L10:
        int r11 = 1;
        if (r9 >= r8.length) goto L23;
        QuoteSpan.QuoteStyleSpan r10 = r8[r9];
        r10.span.adaptLineHeight = false;
        int r102 = r7.getSpanStart(r10);
        int r12 = r7.getSpanEnd(r8[r9]);
        boolean r13 = r8[r9].span.isCollapsing;
        r2.add(Integer.valueOf(r102));
        Integer r14 = Integer.valueOf(r102);
        if (r4.containsKey(Integer.valueOf(r102)) == false) goto L15;
        int r15 = ((Integer) r4.get(Integer.valueOf(r102))).intValue();
    L16:
        if (r13 == false) goto L18;
        r11 = 16;
    L18:
        r4.put(r14, Integer.valueOf(r11 | r15));
        r5.put(Integer.valueOf(r102), Boolean.valueOf(r13));
        r2.add(Integer.valueOf(r12));
        Integer r103 = Integer.valueOf(r12);
        if (r4.containsKey(Integer.valueOf(r12)) == false) goto L21;
        int r112 = ((Integer) r4.get(Integer.valueOf(r12))).intValue();
    L22:
        r4.put(r103, Integer.valueOf(r112 | 2));
        r6.put(Integer.valueOf(r12), Boolean.valueOf(r13));
        r9 = r9 + 1;
        goto L10
    L21:
        r112 = 0;
        goto L22
    L15:
        r15 = 0;
        goto L16
    L23:
        Iterator r82 = r2.iterator();
    L25:
        if (r82.hasNext() == false) goto L50;
        Integer r92 = (Integer) r82.next();
        int r122 = r92.intValue();
        if (r122 < 0) goto L25;
        if (r122 >= r7.length()) goto L25;
        if (r4.containsKey(r92) == false) goto L25;
        int r132 = ((Integer) r4.get(r92)).intValue();
        if ((r132 & 17) == 0) goto L25;
        if ((r132 & 2) == 0) goto L25;
        if (r7.charAt(r122) == '\n') goto L25;
        int r123 = r122 - 1;
        if (r123 <= 0) goto L47;
        if (r7.charAt(r123) == '\n') goto L25;
    L47:
        if (Objects.equals(r5.get(r92), r6.get(r92)) == false) goto L25;
        r82.remove();
        r4.remove(r92);
        goto L25
    L50:
        CodeHighlighting.Span[] r52 = (CodeHighlighting.Span[]) r7.getSpans(0, r7.length(), CodeHighlighting.Span.class);
        int r62 = 0;
    L52:
        if (r62 >= r52.length) goto L62;
        int r83 = r7.getSpanStart(r52[r62]);
        int r93 = r7.getSpanEnd(r52[r62]);
        r2.add(Integer.valueOf(r83));
        Integer r124 = Integer.valueOf(r83);
        if (r4.containsKey(Integer.valueOf(r83)) == false) goto L56;
        int r84 = ((Integer) r4.get(Integer.valueOf(r83))).intValue();
    L57:
        r4.put(r124, Integer.valueOf(r84 | 4));
        r2.add(Integer.valueOf(r93));
        Integer r85 = Integer.valueOf(r93);
        if (r4.containsKey(Integer.valueOf(r93)) == false) goto L60;
        int r94 = ((Integer) r4.get(Integer.valueOf(r93))).intValue();
    L61:
        r4.put(r85, Integer.valueOf(r94 | 8));
        r62 = r62 + 1;
        goto L52
    L60:
        r94 = 0;
        goto L61
    L56:
        r84 = 0;
        goto L57
    L62:
        Iterator r22 = r2.iterator();
        int r63 = 0;
        int r72 = 0;
        int r86 = 0;
        int r133 = 0;
        boolean r17 = false;
    L64:
        if (r22.hasNext() == false) goto L112;
        Integer r95 = (Integer) r22.next();
        int r125 = r95.intValue();
        int r96 = ((Integer) r4.get(r95)).intValue();
        if (r133 == r125) goto L95;
        int r142 = r125 - 1;
        if (r142 >= 0) goto L70;
    L74:
        int r143 = r125;
        if ((r96 & 8) != 0) goto L77;
    L80:
        String r126 = null;
    L79:
        String r18 = r126;
        if (r63 <= 0) goto L84;
        boolean r152 = true;
    L85:
        if (r72 <= 0) goto L87;
        boolean r16 = true;
    L88:
        r20.add(new TextRange(r133, r143, r152, r16, r17, r18));
        r133 = r143 + 1;
        if (r133 < r19.length()) goto L91;
    L93:
        r133 = r143;
        goto L95
    L91:
        if (r19.charAt(r143) != '\n') goto L93;
    L87:
        r16 = false;
        goto L88
    L84:
        r152 = false;
        goto L85
    L77:
        if (r86 >= r52.length) goto L80;
        r126 = r52[r86].lng;
        r86 = r86 + 1;
        goto L79
    L70:
        if (r142 >= r19.length()) goto L74;
        if (r19.charAt(r142) != '\n') goto L74;
        r125 = r125 - 1;
    L95:
        if ((r96 & 2) == 0) goto L98;
        r63 = r63 - 1;
    L98:
        if ((r96 & 1) == 0) goto L100;
    L101:
        r63 = r63 + 1;
        if ((r96 & 16) == 0) goto L104;
        r17 = true;
    L106:
        if ((r96 & 8) == 0) goto L109;
        r72 = r72 - 1;
    L109:
        if ((r96 & 4) == 0) goto L64;
        r72 = r72 + 1;
        goto L64
    L104:
        r17 = false;
        goto L106
    L100:
        if ((r96 & 16) == 0) goto L106;
    L112:
        if (r133 >= r19.length()) goto L153;
        int r144 = r19.length();
        if (r63 <= 0) goto L116;
        boolean r153 = true;
    L117:
        if (r72 <= 0) goto L119;
        boolean r162 = true;
    L120:
        r20.add(new TextRange(r133, r144, r153, r162, r17, null));
        return;
    L119:
        r162 = false;
        goto L120
    L116:
        r153 = false;
        goto L117
    }

    public static int ephemeralMessageIdPack(int r1) {
        return (r1 & (-1879048193)) | MESSAGE_ID_EPHEMERAL_BITS_MASK;
    }

    public static int ephemeralMessageIdUnpack(int r1) {
        if (isEphemeralMessageId(r1) == true) goto L5;
        return r1;
    L5:
        return r1 & (-1879048193);
    }

    public static boolean expandedQuotesEquals(HashSet<Integer> r4, HashSet<Integer> r5) {
        if (r4 != null) goto L7;
        if (r5 != null) goto L7;
        return true;
    L7:
        if (r4 != null) goto L9;
        int r2 = 0;
    L10:
        if (r5 != null) goto L12;
        int r3 = 0;
    L13:
        if (r2 == r3) goto L15;
        return false;
    L15:
        if (r4 != null) goto L17;
    L19:
        return false;
    L17:
        if (r4.equals(r5) == false) goto L19;
        return true;
    L12:
        r3 = r5.size();
        goto L13
    L9:
        r2 = r4.size();
        goto L10
    }

    public static String findAnimatedEmojiEmoticon(TLRPC.Document r1) {
        return findAnimatedEmojiEmoticon(r1, "😀");
    }

    public static String findAnimatedEmojiEmoticon(TLRPC.Document r1, String r2) {
        return findAnimatedEmojiEmoticon(r1, r2, null);
    }

    public static String findAnimatedEmojiEmoticon(TLRPC.Document r6, String r7, Integer r8) {
        if (r6 == null) goto L29;
        int r0 = r6.attributes.size();
        int r1 = 0;
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L29;
        TLRPC.DocumentAttribute r3 = r6.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L12;
        if ((r3 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L12;
        r2 = r2 + 1;
    L12:
        if (r8 == null) goto L28;
        TLRPC.TL_messages_stickerSet r72 = MediaDataController.getInstance(r8.intValue()).getStickerSet(r3.stickerset, true);
        StringBuilder r82 = new StringBuilder(_UrlKt.FRAGMENT_ENCODE_SET);
        if (r72 == null) goto L24;
        if (r72.packs == null) goto L24;
    L18:
        if (r1 >= r72.packs.size()) goto L24;
        TLRPC.TL_stickerPack r02 = r72.packs.get(r1);
        if (r02.documents.contains(Long.valueOf(r6.id)) == false) goto L22;
        r82.append(r02.emoticon);
    L22:
        r1 = r1 + 1;
    L24:
        if (TextUtils.isEmpty(r82) == true) goto L28;
        return r82.toString();
    L28:
        return r3.alt;
    L29:
        return r7;
    }

    public static TLRPC.Document findAudio(ArrayList<TL_iv.PageBlock> r5, TL_iv.RichMessage r6) {
        if (r5 == null) goto L20;
        if (r6 == null) goto L20;
        int r1 = r5.size();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L20;
        TL_iv.PageBlock r3 = r5.get(r2);
        r2 = r2 + 1;
        TL_iv.PageBlock r32 = r3;
        if ((r32 instanceof TL_iv.pageBlockAudio) == true) goto L11;
        if ((r32 instanceof TL_iv.pageBlockCollage) == true) goto L15;
        if ((r32 instanceof TL_iv.pageBlockSlideshow) == false) goto L7;
        return findAudio(((TL_iv.pageBlockSlideshow) r32).items, r6);
    L15:
        return findAudio(((TL_iv.pageBlockCollage) r32).items, r6);
    L11:
        return AndroidUtilities.findDocument(r6.documents, ((TL_iv.pageBlockAudio) r32).audio_id);
    L20:
        return null;
    }

    public static TLRPC.Document findAudio(TL_iv.RichMessage r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return findAudio(r1.blocks, r1);
    }

    public static TLRPC.Photo findPhoto(ArrayList<TL_iv.PageBlock> r5, TL_iv.RichMessage r6) {
        if (r5 == null) goto L20;
        if (r6 == null) goto L20;
        int r1 = r5.size();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L20;
        TL_iv.PageBlock r3 = r5.get(r2);
        r2 = r2 + 1;
        TL_iv.PageBlock r32 = r3;
        if ((r32 instanceof TL_iv.pageBlockPhoto) == true) goto L11;
        if ((r32 instanceof TL_iv.pageBlockCollage) == true) goto L15;
        if ((r32 instanceof TL_iv.pageBlockSlideshow) == false) goto L7;
        return findPhoto(((TL_iv.pageBlockSlideshow) r32).items, r6);
    L15:
        return findPhoto(((TL_iv.pageBlockCollage) r32).items, r6);
    L11:
        return AndroidUtilities.findPhoto(r6.photos, ((TL_iv.pageBlockPhoto) r32).photo_id);
    L20:
        return null;
    }

    public static TLRPC.Photo findPhoto(TL_iv.RichMessage r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return findPhoto(r1.blocks, r1);
    }

    public static TLRPC.PollAnswer findPollItem(MessageObject r4, byte[] r5) {
        TLRPC.MessageMedia r42 = getMedia(r4);
        if ((r42 instanceof TLRPC.TL_messageMediaPoll) == true) goto L5;
        return null;
    L5:
        TLRPC.TL_messageMediaPoll r43 = (TLRPC.TL_messageMediaPoll) r42;
        TLRPC.Poll r0 = r43.poll;
        if (r0 != null) goto L8;
    L17:
        return null;
    L8:
        if (r0.answers == null) goto L17;
        int r02 = 0;
    L12:
        if (r02 >= r43.poll.answers.size()) goto L17;
        TLRPC.PollAnswer r2 = r43.poll.answers.get(r02);
        if (Arrays.equals(r2.option, r5) == true) goto L15;
        r02 = r02 + 1;
        goto L12
    L15:
        return r2;
    }

    public static int findQuoteStart(String r3, String r4, int r5) {
        if (r3 == null) goto L23;
        if (r4 == null) goto L23;
        if (r5 != (-1)) goto L10;
        return r3.indexOf(r4);
    L10:
        if ((r4.length() + r5) < r3.length()) goto L12;
    L14:
        int r1 = r3.indexOf(r4, r5);
        int r32 = r3.lastIndexOf(r4, r5);
        if (r1 == (-1)) goto L22;
        if (r32 != (-1)) goto L20;
    L21:
        return r1;
    L20:
        if ((r1 - r5) < (r5 - r32)) goto L21;
    L22:
        return r32;
    L12:
        if (r3.startsWith(r4, r5) == false) goto L14;
        return r5;
    L23:
        return -1;
    }

    public static ArrayList<String> findStickerEmoticons(TLRPC.Document r12, Integer r13) {
        if (r12 != null) goto L5;
        return null;
    L5:
        ArrayList<String> r1 = new ArrayList();
        int r2 = r12.attributes.size();
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L35;
        TLRPC.DocumentAttribute r5 = r12.attributes.get(r4);
        if ((r5 instanceof TLRPC.TL_documentAttributeCustomEmoji) == false) goto L10;
    L11:
        if (r13 == null) goto L29;
        TLRPC.TL_messages_stickerSet r6 = MediaDataController.getInstance(r13.intValue()).getStickerSet(r5.stickerset, true);
        if (r6 == null) goto L26;
        if (r6.packs == null) goto L26;
        int r7 = 0;
    L18:
        if (r7 >= r6.packs.size()) goto L26;
        TLRPC.TL_stickerPack r8 = r6.packs.get(r7);
        if (r8.documents.contains(Long.valueOf(r12.id)) == false) goto L24;
        if (Emoji.getEmojiDrawable(r8.emoticon) == null) goto L24;
        r1.add(r8.emoticon);
    L24:
        r7 = r7 + 1;
    L26:
        if (r1.isEmpty() == true) goto L29;
        return r1;
    L29:
        if (TextUtils.isEmpty(r5.alt) == true) goto L34;
        if (Emoji.getEmojiDrawable(r5.alt) == null) goto L34;
        r1.add(r5.alt);
        return r1;
    L34:
        r4 = r4 + 1;
        goto L6
    L10:
        if ((r5 instanceof TLRPC.TL_documentAttributeSticker) == false) goto L34;
    L35:
        return null;
    }

    public static TLRPC.TodoItem findTodoItem(MessageObject r4, int r5) {
        TLRPC.MessageMedia r42 = getMedia(r4);
        if ((r42 instanceof TLRPC.TL_messageMediaToDo) == true) goto L5;
        return null;
    L5:
        TLRPC.TL_messageMediaToDo r43 = (TLRPC.TL_messageMediaToDo) r42;
        TLRPC.TodoList r0 = r43.todo;
        if (r0 != null) goto L8;
    L17:
        return null;
    L8:
        if (r0.list == null) goto L17;
        int r02 = 0;
    L12:
        if (r02 >= r43.todo.list.size()) goto L17;
        TLRPC.TodoItem r2 = r43.todo.list.get(r02);
        if (r2.id == r5) goto L15;
        r02 = r02 + 1;
        goto L12
    L15:
        return r2;
    }

    public static TLRPC.Document findVideo(ArrayList<TL_iv.PageBlock> r5, TL_iv.RichMessage r6) {
        if (r5 == null) goto L20;
        if (r6 == null) goto L20;
        int r1 = r5.size();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L20;
        TL_iv.PageBlock r3 = r5.get(r2);
        r2 = r2 + 1;
        TL_iv.PageBlock r32 = r3;
        if ((r32 instanceof TL_iv.pageBlockVideo) == true) goto L11;
        if ((r32 instanceof TL_iv.pageBlockCollage) == true) goto L15;
        if ((r32 instanceof TL_iv.pageBlockSlideshow) == false) goto L7;
        return findVideo(((TL_iv.pageBlockSlideshow) r32).items, r6);
    L15:
        return findVideo(((TL_iv.pageBlockCollage) r32).items, r6);
    L11:
        return AndroidUtilities.findDocument(r6.documents, ((TL_iv.pageBlockVideo) r32).video_id);
    L20:
        return null;
    }

    public static TLRPC.Document findVideo(TL_iv.RichMessage r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return findVideo(r1.blocks, r1);
    }

    public static void fixMessagePeer(ArrayList<TLRPC.Message> r3, long r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.isEmpty() == false) goto L6;
        return;
    L6:
        if (r4 == 0) goto L20;
        int r0 = 0;
    L10:
        if (r0 >= r3.size()) goto L21;
        TLRPC.Message r1 = r3.get(r0);
        if ((r1 instanceof TLRPC.TL_messageEmpty) == false) goto L14;
        TLRPC.TL_peerChannel r2 = new TLRPC.TL_peerChannel();
        r1.peer_id = r2;
        r2.channel_id = r4;
    L14:
        r0 = r0 + 1;
        goto L10
    L21:
        return;
    }

    public static CharSequence formatRichBlock(TL_iv.PageBlock r13, boolean r14, boolean r15, int r16, SpannableStringBuilder r17, TL_iv.RichMessage r18) {
        if ((r13 instanceof TL_iv.pageBlockTitle) == false) goto L5;
    L225:
        formatRichText(r13.text, r14, r15, r16, r17, 1);
        return r17;
    L5:
        if ((r13 instanceof TL_iv.pageBlockHeader) == true) goto L225;
        if ((r13 instanceof TL_iv.pageBlockSubheader) == true) goto L225;
        if ((r13 instanceof TL_iv.pageBlockHeading1) == true) goto L225;
        if ((r13 instanceof TL_iv.pageBlockHeading2) == true) goto L225;
        if ((r13 instanceof TL_iv.pageBlockHeading3) == true) goto L225;
        if ((r13 instanceof TL_iv.pageBlockHeading4) == true) goto L225;
        if ((r13 instanceof TL_iv.pageBlockHeading5) == true) goto L225;
        if ((r13 instanceof TL_iv.pageBlockHeading6) == true) goto L225;
        if ((r13 instanceof TL_iv.pageBlockBlockquote) == true) goto L225;
        if ((r13 instanceof TL_iv.pageBlockPullquote) == true) goto L225;
        if ((r13 instanceof TL_iv.pageBlockParagraph) == false) goto L28;
    L223:
        formatRichText(r13.text, r14, r15, r16, r17, 0);
        return r17;
    L28:
        if ((r13 instanceof TL_iv.pageBlockFooter) == true) goto L223;
        if ((r13 instanceof TL_iv.pageBlockKicker) == true) goto L223;
        int r8 = 0;
        if ((r13 instanceof TL_iv.pageBlockBlockquoteBlocks) == false) goto L45;
        TL_iv.pageBlockBlockquoteBlocks r132 = (TL_iv.pageBlockBlockquoteBlocks) r13;
    L36:
        if (r8 >= r132.blocks.size()) goto L222;
        if (r8 <= 0) goto L39;
        r17.append("  ");
    L39:
        formatRichBlock(r132.blocks.get(r8), r14, r15, r16, r17, r18);
        if (r17.length() >= r16) goto L41;
        r8 = r8 + 1;
        goto L36
    L41:
        r17.delete(r16, r17.length());
        r17.append("…");
        return r17;
    L222:
        return r17;
    L45:
        if ((r13 instanceof TL_iv.pageBlockDetails) == false) goto L49;
        formatRichText(((TL_iv.pageBlockDetails) r13).title, r14, r15, r16, r17, 0);
        return r17;
    L49:
        if ((r13 instanceof TL_iv.pageBlockAuthorDate) == false) goto L53;
        formatRichText(((TL_iv.pageBlockAuthorDate) r13).author, r14, r15, r16, r17, 0);
        return r17;
    L53:
        if ((r13 instanceof TL_iv.pageBlockMath) == false) goto L57;
        r17.append(span("fx", R.drawable.iv_formula)).append(" ").append(LocaleController.getString(R.string.AccDescrIVFormula));
        return r17;
    L57:
        if ((r13 instanceof TL_iv.pageBlockMap) == false) goto L65;
        r17.append(LocaleController.getString(R.string.Map));
        TL_iv.PageCaption r0 = r13.caption;
        if (r0 == null) goto L222;
        if ((r0.text instanceof TL_iv.textEmpty) == true) goto L222;
        r17.append("  ");
        formatRichText(r13.caption.text, r14, r15, r16, r17, 0);
        return r17;
    L65:
        if ((r13 instanceof TL_iv.pageBlockPreformatted) == false) goto L68;
        formatRichText(r13.text, r14, r15, r16, r17, 4);
        return r17;
    L68:
        SpannableStringBuilder r4 = r17;
        if ((r13 instanceof TL_iv.pageBlockList) == false) goto L104;
        TL_iv.pageBlockList r133 = (TL_iv.pageBlockList) r13;
        int r10 = 0;
    L72:
        if (r10 >= r133.items.size()) goto L222;
        if (r10 <= 0) goto L75;
        r4.append("  ");
    L75:
        TL_iv.PageListItem r02 = r133.items.get(r10);
        if ((r02 instanceof TL_iv.TL_pageListItemText) == false) goto L83;
        TL_iv.TL_pageListItemText r03 = (TL_iv.TL_pageListItemText) r02;
        if (r03.checkbox == false) goto L80;
        r4.append(checkboxSpan(r03.checked)).append(" ");
    L81:
        int r3 = r16;
        formatRichText(r03.text, r14, r15, r3, r4, 0);
    L100:
        if (r4.length() >= r3) goto L101;
        r10 = r10 + 1;
        goto L72
    L101:
        r4.delete(r3, r4.length());
        r4.append("…");
        return r4;
    L80:
        r4.append("• ");
        goto L81
    L83:
        if ((r02 instanceof TL_iv.TL_pageListItemBlocks) == false) goto L98;
        TL_iv.TL_pageListItemBlocks r11 = (TL_iv.TL_pageListItemBlocks) r02;
        if (r11.checkbox == false) goto L87;
        r4.append(checkboxSpan(r11.checked)).append(" ");
    L88:
        int r12 = 0;
    L90:
        if (r12 >= r11.blocks.size()) goto L98;
        if (r12 <= 0) goto L93;
        r4.append("  ");
    L93:
        formatRichBlock(r11.blocks.get(r12), r14, r15, r16, r4, r18);
        if (r4.length() >= r16) goto L95;
        r12 = r12 + 1;
        goto L90
    L95:
        r4.delete(r16, r4.length());
        r4.append("…");
        return r4;
    L87:
        r4.append("• ");
    L98:
        r3 = r16;
        goto L100
    L104:
        int r32 = r16;
        if ((r13 instanceof TL_iv.pageBlockOrderedList) == false) goto L139;
        TL_iv.pageBlockOrderedList r134 = (TL_iv.pageBlockOrderedList) r13;
        int r102 = 0;
    L108:
        if (r102 >= r134.items.size()) goto L222;
        if (r102 <= 0) goto L111;
        r4.append("  ");
    L111:
        TL_iv.PageListOrderedItem r04 = r134.items.get(r102);
        if ((r04 instanceof TL_iv.TL_pageListOrderedItemText) == false) goto L119;
        TL_iv.TL_pageListOrderedItemText r05 = (TL_iv.TL_pageListOrderedItemText) r04;
        r4.append(r05.num);
        r4.append(". ");
        if (r05.checkbox == false) goto L116;
        r4.append(checkboxSpan(r05.checked)).append(" ");
    L116:
        formatRichText(r05.text, r14, r15, r32, r4, 0);
    L117:
        r32 = r16;
        if (r4.length() >= r32) goto L135;
        r102 = r102 + 1;
        goto L108
    L135:
        r4.delete(r32, r4.length());
        r4.append("…");
        return r4;
    L119:
        if ((r04 instanceof TL_iv.TL_pageListOrderedItemBlocks) == false) goto L117;
        TL_iv.TL_pageListOrderedItemBlocks r112 = (TL_iv.TL_pageListOrderedItemBlocks) r04;
        r4.append(r112.num);
        r4.append(". ");
        if (r112.checkbox == false) goto L123;
        r4.append(checkboxSpan(r112.checked)).append(" ");
    L123:
        int r122 = 0;
    L125:
        if (r122 >= r112.blocks.size()) goto L117;
        if (r122 <= 0) goto L128;
        r4.append("  ");
    L128:
        formatRichBlock(r112.blocks.get(r122), r14, r15, r16, r4, r18);
        if (r4.length() >= r16) goto L130;
        r122 = r122 + 1;
        goto L125
    L130:
        r4.delete(r16, r4.length());
        r4.append("…");
        return r4;
    L139:
        if ((r13 instanceof TL_iv.pageBlockTable) == false) goto L149;
        r4.append(span("⊞", R.drawable.iv_preview_table)).append(" ");
        TL_iv.RichText r06 = ((TL_iv.pageBlockTable) r13).title;
        if (r06 != null) goto L143;
    L146:
        r4.append(LocaleController.getString(R.string.AccDescrIVTable));
        return r4;
    L143:
        if ((r06 instanceof TL_iv.textEmpty) == true) goto L146;
        formatRichText(r06, r14, r15, r32, r4, 0);
        return r4;
    L149:
        if ((r13 instanceof TL_iv.pageBlockAudio) == false) goto L182;
        TL_iv.pageBlockAudio r135 = (TL_iv.pageBlockAudio) r13;
    L152:
        if (r8 >= r18.documents.size()) goto L157;
        if (r18.documents.get(r8).id == r135.audio_id) goto L155;
        r8 = r8 + 1;
        goto L152
    L155:
        TLRPC.Document r136 = r18.documents.get(r8);
    L158:
        if (r136 == null) goto L222;
        TLRPC.TL_documentAttributeAudio r142 = (TLRPC.TL_documentAttributeAudio) AndroidUtilities.find(r136.attributes, TLRPC.TL_documentAttributeAudio.class);
        TLRPC.TL_documentAttributeFilename r137 = (TLRPC.TL_documentAttributeFilename) AndroidUtilities.find(r136.attributes, TLRPC.TL_documentAttributeFilename.class);
        if (r142 != null) goto L162;
        if (r137 == null) goto L222;
        if (r137.file_name == null) goto L222;
        r4.append(span("🎵", R.drawable.iv_audio_preview)).append(" ").append(r137.file_name);
        return r4;
    L162:
        if (TextUtils.isEmpty(r142.title) == true) goto L168;
        if (TextUtils.isEmpty(r142.performer) == true) goto L168;
        r4.append(span("🎵", R.drawable.iv_audio_preview)).append(" ").append(r142.performer).append(" – ").append(r142.title);
        return r4;
    L168:
        if (TextUtils.isEmpty(r142.title) == true) goto L171;
        r4.append(span("🎵", R.drawable.iv_audio_preview)).append(" ").append(r142.title);
        return r4;
    L171:
        if (r137 == null) goto L222;
        if (r137.file_name == null) goto L222;
        r4.append(span("🎵", R.drawable.iv_audio_preview)).append(" ").append(r137.file_name);
        return r4;
    L157:
        r136 = null;
        goto L158
    L182:
        if ((r13 instanceof TL_iv.pageBlockDocument) == false) goto L191;
        TLRPC.Document r138 = AndroidUtilities.findDocument(r18.documents, ((TL_iv.pageBlockDocument) r13).document_id);
        if (r138 == null) goto L222;
        String r139 = FileLoader.getDocumentFileName(r138);
        SpannableStringBuilder r143 = r4.append(span("📎", R.drawable.msg_filled_data_files)).append(" ");
        if (TextUtils.isEmpty(r139) == false) goto L188;
        r139 = LocaleController.getString(R.string.AttachDocument);
    L188:
        r143.append(r139);
        return r4;
    L191:
        if ((r13 instanceof TL_iv.pageBlockCover) == false) goto L195;
        formatRichBlock(((TL_iv.pageBlockCover) r13).cover, r14, r15, r16, r4, r18);
        return r4;
    L195:
        if ((r13 instanceof TL_iv.pageBlockPhoto) == false) goto L199;
        r4.append(LocaleController.getString(R.string.AttachPhoto));
        return r4;
    L199:
        if ((r13 instanceof TL_iv.pageBlockVideo) == false) goto L203;
        r4.append(LocaleController.getString(R.string.AttachVideo));
        return r4;
    L203:
        if ((r13 instanceof TL_iv.pageBlockCollage) == false) goto L207;
        r4.append(LocaleController.getString(R.string.AccDescrCollage));
        return r4;
    L207:
        if ((r13 instanceof TL_iv.pageBlockSlideshow) == false) goto L211;
        r4.append(LocaleController.getString(R.string.AccDescrIVSlideshow));
        return r4;
    L211:
        if ((r13 instanceof TL_iv.pageBlockUnsupported) == false) goto L215;
        r4.append(LocaleController.getString(R.string.UnsupportedAttachment));
        return r4;
    L215:
        if ((r13 instanceof TL_iv.pageBlockButtonRow) == false) goto L222;
        TL_iv.pageBlockButtonRow r1310 = (TL_iv.pageBlockButtonRow) r13;
    L218:
        if (r8 >= r1310.buttons.size()) goto L222;
        if (r8 <= 0) goto L221;
        r4.append("  ");
    L221:
        formatRichText(r1310.buttons.get(r8).text, r14, r15, r16, r4, 0);
        r8 = r8 + 1;
        r4 = r17;
        goto L218
    }

    public static CharSequence formatRichMessage(TL_iv.RichMessage r2, boolean r3) {
        return formatRichMessage(r2, r3, false, 1024);
    }

    public static CharSequence formatRichMessage(TL_iv.RichMessage r7, boolean r8, boolean r9, int r10) {
        SpannableStringBuilder r4 = new SpannableStringBuilder();
        if (r7 == null) goto L15;
        int r6 = 0;
    L6:
        if (r6 >= r7.blocks.size()) goto L15;
        TL_iv.PageBlock r0 = r7.blocks.get(r6);
        if (r6 <= 0) goto L10;
        r4.append("  ");
    L10:
        TL_iv.RichMessage r5 = r7;
        boolean r1 = r8;
        boolean r2 = r9;
        int r3 = r10;
        formatRichBlock(r0, r1, r2, r3, r4, r5);
        if (r4.length() >= r3) goto L12;
        r6 = r6 + 1;
        r8 = r1;
        r9 = r2;
        r10 = r3;
        r7 = r5;
        goto L6
    L12:
        r4.delete(r3, r4.length());
        r4.append("…");
        return r4;
    L15:
        return r4;
    }

    public static CharSequence formatRichText(TL_iv.RichText r9, boolean r10, boolean r11, int r12, SpannableStringBuilder r13, int r14) {
        if (r9 != null) goto L4;
        return r13;
    L4:
        int r0 = r13.length();
        if ((r9 instanceof TL_iv.textPlain) == false) goto L10;
        r13.append(((TL_iv.textPlain) r9).text);
    L7:
        SpannableStringBuilder r7 = r13;
    L8:
        int r8 = r14;
    L60:
        if (r7.length() <= r0) goto L63;
        if (r8 == 0) goto L63;
        TextStyleSpan.TextStyleRun r92 = new TextStyleSpan.TextStyleRun();
        r92.flags = r8;
        r7.setSpan(new TextStyleSpan(r92), r0, r7.length(), 33);
    L63:
        return r7;
    L10:
        if ((r9 instanceof TL_iv.textButton) == false) goto L12;
        formatRichText(r9.text, r10, r11, r12, r13, r14);
        goto L7
    L12:
        r7 = r13;
        if ((r9 instanceof TL_iv.textBold) == false) goto L16;
        formatRichText(r9.text, r10, r11, r12, r7, r14 | 1);
        goto L8
    L16:
        if ((r9 instanceof TL_iv.textItalic) == false) goto L19;
        formatRichText(r9.text, r10, r11, r12, r7, r14 | 2);
        goto L8
    L19:
        if ((r9 instanceof TL_iv.textUnderline) == false) goto L22;
        formatRichText(r9.text, r10, r11, r12, r7, r14 | 16);
        goto L8
    L22:
        if ((r9 instanceof TL_iv.textStrike) == false) goto L25;
        formatRichText(r9.text, r10, r11, r12, r7, r14 | 8);
        goto L8
    L25:
        if ((r9 instanceof TL_iv.textFixed) == false) goto L28;
        formatRichText(r9.text, r10, r11, r12, r7, r14 | 4);
        goto L8
    L28:
        if ((r9 instanceof TL_iv.textSpoiler) == false) goto L31;
        formatRichText(r9.text, r10, r11, r12, r7, r14 | 256);
        goto L8
    L31:
        if ((r9 instanceof TL_iv.textUrl) == false) goto L35;
        r8 = r14;
        formatRichText(r9.text, r10, r11, r12, r7, r8);
        if (r7.length() <= r0) goto L60;
        TextStyleSpan.TextStyleRun r102 = new TextStyleSpan.TextStyleRun();
        r102.flags = r8;
        r7.setSpan(new URLSpanBrowser(r9.url, r102), r0, r7.length(), 33);
        goto L60
    L35:
        r8 = r14;
        if ((r9 instanceof TL_iv.textEmail) == false) goto L41;
        formatRichText(r9.text, r10, r11, r12, r7, r8);
        if (r7.length() <= r0) goto L60;
        TextStyleSpan.TextStyleRun r103 = new TextStyleSpan.TextStyleRun();
        r103.flags = r8;
        r7.setSpan(new URLSpanReplacement("mailto:" + r9.email, r103), r0, r7.length(), 33);
        goto L60
    L41:
        if ((r9 instanceof TL_iv.textMath) == false) goto L44;
        r7.append(span("fx", R.drawable.iv_formula)).append(" ").append(LocaleController.getString(R.string.AccDescrIVFormula));
        goto L60
    L44:
        if ((r9 instanceof TL_iv.textPhone) == false) goto L52;
        formatRichText(r9.text, r10, r11, r12, r7, r8);
        if (r7.length() <= r0) goto L60;
        TL_iv.textPhone r93 = (TL_iv.textPhone) r9;
        String r104 = PhoneFormat.stripExceptNumbers(r93.phone);
        if (r93.phone.startsWith("+") == false) goto L50;
        r104 = "+" + r104;
    L50:
        TextStyleSpan.TextStyleRun r94 = new TextStyleSpan.TextStyleRun();
        r94.flags = r8;
        r7.setSpan(new URLSpanNoUnderline("tel:" + r104, r94), r0, r7.length(), 33);
        goto L60
    L52:
        if ((r9 instanceof TL_iv.textConcat) == false) goto L60;
        ArrayList<TL_iv.RichText> r95 = ((TL_iv.textConcat) r9).texts;
        int r105 = r95.size();
        int r112 = 0;
    L54:
        if (r112 >= r105) goto L60;
        TL_iv.RichText r122 = r95.get(r112);
        r112 = r112 + 1;
        formatRichText(r122, r10, r11, r12, r7, r8);
        if (r7.length() < r12) goto L54;
        r7.delete(r12, r7.length());
        r7.append("…");
        return r7;
    }

    private CharSequence formatTaskTitle(TLRPC.TodoItem r5) {
        CharSequence r4 = formatTextWithEntities(r5.title, isOutOwner());
        if ((r4 instanceof Spannable) == true) goto L5;
        r4 = new SpannableStringBuilder(r4);
    L5:
        ((Spannable) r4).setSpan(new URLSpanNoUnderline("task?" + r5.id, true), 0, r4.length(), 33);
        return r4;
    }

    public static CharSequence formatTextWithEntities(TLRPC.TL_textWithEntities r1) {
        return formatTextWithEntities(r1, false);
    }

    public static CharSequence formatTextWithEntities(TLRPC.TL_textWithEntities r3, boolean r4) {
        Theme.createCommonChatResources();
        TextPaint r0 = Theme.chat_actionTextPaint;
        if (r0 != null) goto L6;
        r0 = new TextPaint(1);
        r0.setTypeface(AndroidUtilities.bold());
        r0.setTextSize(AndroidUtilities.dp(Math.max(16, SharedConfig.fontSize) - 2));
    L6:
        return formatTextWithEntities(r3, r4, r0);
    }

    public static CharSequence formatTextWithEntities(TLRPC.TL_textWithEntities r1, boolean r2, TextPaint r3) {
        return formatTextWithEntities(r1, r2, false, r3);
    }

    public static CharSequence formatTextWithEntities(TLRPC.TL_textWithEntities r6, boolean r7, boolean r8, TextPaint r9) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(r6.text);
        addEntitiesToText(r0, r6.entities, r7, false, r8, false);
        return replaceAnimatedEmoji(Emoji.replaceEmoji(r0, r9.getFontMetricsInt(), false), r6.entities, r9.getFontMetricsInt());
    }

    private CharSequence getActionSuggestionApprovalText(String r14, String r15) {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 == null) goto L7;
        TLRPC.MessageAction r02 = r0.action;
        if ((r02 instanceof TLRPC.TL_messageActionSuggestedPostApproval) == false) goto L7;
        TLRPC.TL_messageActionSuggestedPostApproval r03 = (TLRPC.TL_messageActionSuggestedPostApproval) r02;
    L8:
        if (r03 != null) goto L10;
        return null;
    L10:
        MessageSuggestionParams r1 = MessageSuggestionParams.of(r03);
        boolean r2 = ChatObject.canManageMonoForum(this.currentAccount, DialogObject.getPeerDialogId(this.messageOwner.peer_id));
        SpannableStringBuilder r3 = new SpannableStringBuilder();
        if (r03.balance_too_low == false) goto L14;
        r3.append(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.SuggestionAgreementNotEnoughStars, new Object[]{r15})));
        return r3;
    L14:
        boolean r6 = false;
        if (r03.rejected == false) goto L43;
        MessageObject r12 = this.replyMessageObject;
        if (r12 == null) goto L27;
        TLRPC.Message r13 = r12.messageOwner;
        if (r13 == null) goto L27;
        long r16 = DialogObject.getPeerDialogId(r13.from_id);
        if (r16 == DialogObject.getPeerDialogId(this.replyMessageObject.messageOwner.saved_peer_id)) goto L23;
        boolean r4 = false;
    L25:
        if (r16 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L29;
        r6 = true;
    L29:
        if (TextUtils.isEmpty(r03.reject_comment) == false) goto L33;
        if (r6 == false) goto L32;
        int r132 = R.string.SuggestionAgreementDeclinedYou;
    L36:
        if (r4 == true) goto L39;
        r14 = r15;
    L39:
        r3.append(AndroidUtilities.replaceTags(LocaleController.formatString(r132, new Object[]{r14})));
        if (TextUtils.isEmpty(r03.reject_comment) == true) goto L89;
        r3.append("\n\n");
        r3.setSpan(new RelativeSizeSpan(0.8f), r3.length() - 1, r3.length(), 33);
        int r133 = r3.length();
        r3.append(Typography.quote);
        r3.append(r03.reject_comment);
        r3.append(Typography.quote);
        r3.setSpan(new EllipsizeSpanAnimator.TextAlphaSpan(Opcodes.RSUB_INT_LIT8), r133, r3.length(), 33);
        return r3;
    L89:
        return r3;
    L32:
        r132 = R.string.SuggestionAgreementDeclinedThis;
        goto L36
    L33:
        if (r6 == false) goto L35;
        r132 = R.string.SuggestionAgreementDeclinedYouComment;
        goto L36
    L35:
        r132 = R.string.SuggestionAgreementDeclinedThisComment;
        goto L36
    L23:
        r4 = true;
    L27:
        r4 = true;
        goto L29
    L43:
        int r42 = (int) MessagesController.getInstance(this.currentAccount).config.starsSuggestedPostAgeMin.get(TimeUnit.HOURS);
        int r9 = r03.schedule_date;
        if (r9 <= 0) goto L54;
        String r92 = LocaleController.formatDateTime(r9, true);
        if (r03.schedule_date <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L50;
        if (r2 == false) goto L49;
        int r134 = R.string.SuggestionAgreementReachedAdmin1;
    L53:
        r3.append(AndroidUtilities.replaceTags(LocaleController.formatString(r134, new Object[]{r14, r92})));
    L58:
        AmountUtils$Amount r135 = r1.amount;
        if (r135 == null) goto L89;
        if (r135.isZero() == true) goto L89;
        AmountUtils$Amount r136 = r1.amount;
        AmountUtils$Currency r04 = r136.currency;
        AmountUtils$Currency r93 = AmountUtils$Currency.TON;
        if (r04 != r93) goto L65;
        boolean r05 = true;
    L66:
        if (r2 == false) goto L68;
        String r137 = LocaleController.formatString(R.string.SuggestionAgreementReachedAdmin2, new Object[]{r15, r136.asDecimalString()});
    L69:
        r3.append("\n\n");
        r3.setSpan(new RelativeSizeSpan(0.6f), r3.length() - 1, r3.length(), 33);
        if (r1.amount.currency != r93) goto L72;
        r6 = true;
    L72:
        r3.append(StarsIntroActivity.replaceStars(r6, AndroidUtilities.replaceTags(r137)));
        if (r05 == false) goto L77;
        if (r2 == false) goto L76;
        int r138 = R.string.SuggestionAgreementReachedAdmin3TON;
    L80:
        r3.append("\n\n");
        r3.setSpan(new RelativeSizeSpan(0.6f), r3.length() - 1, r3.length(), 33);
        r3.append(AndroidUtilities.replaceTags(LocaleController.formatString(r138, new Object[]{r14, Integer.valueOf(r42)})));
        if (r05 == false) goto L85;
        if (r2 == false) goto L84;
        int r139 = R.string.SuggestionAgreementReachedAdmin4TON;
    L88:
        r3.append("\n\n");
        r3.setSpan(new RelativeSizeSpan(0.6f), r3.length() - 1, r3.length(), 33);
        r3.append(AndroidUtilities.replaceTags(LocaleController.formatString(r139, new Object[]{r14, Integer.valueOf(r42)})));
        goto L89
    L84:
        r139 = R.string.SuggestionAgreementReachedUser4TON;
        goto L88
    L85:
        if (r2 == false) goto L87;
        r139 = R.string.SuggestionAgreementReachedAdmin4Stars;
        goto L88
    L87:
        r139 = R.string.SuggestionAgreementReachedUser4Stars;
        goto L88
    L76:
        r138 = R.string.SuggestionAgreementReachedUser3TON;
        goto L80
    L77:
        if (r2 == false) goto L79;
        r138 = R.string.SuggestionAgreementReachedAdmin3Stars;
        goto L80
    L79:
        r138 = R.string.SuggestionAgreementReachedUser3Stars;
        goto L80
    L68:
        r137 = LocaleController.formatString(R.string.SuggestionAgreementReachedUser2, new Object[]{r136.asDecimalString()});
        goto L69
    L65:
        r05 = false;
        goto L66
    L49:
        r134 = R.string.SuggestionAgreementReachedUser1;
        goto L53
    L50:
        if (r2 == false) goto L52;
        r134 = R.string.SuggestionAgreementReachedAdmin1PastSimple;
        goto L53
    L52:
        r134 = R.string.SuggestionAgreementReachedUser1PastSimple;
        goto L53
    L54:
        if (r2 == false) goto L56;
        int r1310 = R.string.SuggestionAgreementReachedAdmin1PresentPerfect;
    L57:
        r3.append(AndroidUtilities.replaceTags(LocaleController.formatString(r1310, new Object[]{r14})));
        goto L58
    L56:
        r1310 = R.string.SuggestionAgreementReachedUser1PresentPerfect;
    L7:
        r03 = null;
        goto L8
    }

    public static String getArtworkUrl(TLRPC.Document r11, boolean r12) {
        if (r11 != null) goto L6;
        return null;
    L6:
        if ("audio/ogg".equals(r11.mime_type) == false) goto L8;
        return null;
    L8:
        int r1 = r11.attributes.size();
        int r3 = 0;
    L9:
        if (r3 >= r1) goto L32;
        TLRPC.DocumentAttribute r4 = r11.attributes.get(r3);
        if ((r4 instanceof TLRPC.TL_documentAttributeAudio) == false) goto L31;
        if (r4.voice == true) goto L14;
        String r5 = r4.performer;
        String r42 = r4.title;
        if (TextUtils.isEmpty(r5) == true) goto L21;
        String[] r6 = excludeWords;
        int r7 = r6.length;
        int r8 = 0;
    L18:
        if (r8 >= r7) goto L21;
        r5 = r5.replace(r6[r8], " ");
        r8 = r8 + 1;
    L21:
        if (TextUtils.isEmpty(r5) == true) goto L23;
    L34:
        StringBuilder r62 = new StringBuilder();     // Catch: Exception -> L33
        r62.append("athumb://itunes.apple.com/search?term=");     // Catch: Exception -> L33
        r62.append(URLEncoder.encode(r5 + " - " + r42, "UTF-8"));     // Catch: Exception -> L33
        r62.append("&entity=song&limit=4");     // Catch: Exception -> L33
        if (r12 == false) goto L28;
        String r43 = "&s=1";
    L29:
        r62.append(r43);     // Catch: Exception -> L33
        return r62.toString();
    L28:
        r43 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L29
    L23:
        if (TextUtils.isEmpty(r42) == false) goto L34;
        return null;
    L14:
        return null;
    L31:
        r3 = r3 + 1;
        goto L9
    L32:
        return null;
    }

    public static long getChannelId(TLRPC.Message r2) {
        TLRPC.Peer r22 = r2.peer_id;
        if (r22 != null) goto L5;
        return 0;
    L5:
        return r22.channel_id;
    }

    private TLRPC.Chat getChat(AbstractMap<Long, TLRPC.Chat> r1, LongSparseArray<TLRPC.Chat> r2, long r3) {
        if (r1 == null) goto L4;
        TLRPC.Chat r12 = r1.get(Long.valueOf(r3));
    L7:
        if (r12 == null) goto L9;
        return r12;
    L9:
        return MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r3));
    L4:
        if (r2 == null) goto L6;
        r12 = r2.get(r3);
        goto L7
    L6:
        r12 = null;
        goto L7
    }

    public static long getChatId(TLRPC.Message r3) {
        if (r3 != null) goto L5;
        return 0;
    L5:
        TLRPC.Peer r32 = r3.peer_id;
        if ((r32 instanceof TLRPC.TL_peerChat) == false) goto L10;
        return r32.chat_id;
    L10:
        if ((r32 instanceof TLRPC.TL_peerChannel) == true) goto L12;
        return 0;
    L12:
        return r32.channel_id;
    }

    public static int getCompletionsCount(TLRPC.TL_messageMediaToDo r7) {
        if (r7 == null) goto L21;
        TLRPC.TodoList r1 = r7.todo;
        if (r1 == null) goto L21;
        if (r1.list == null) goto L21;
        int r12 = 0;
        int r2 = 0;
    L11:
        if (r12 >= r7.completions.size()) goto L20;
        TLRPC.TodoCompletion r3 = r7.completions.get(r12);
        int r4 = 0;
    L14:
        if (r4 >= r7.todo.list.size()) goto L19;
        if (r7.todo.list.get(r4).id == r3.id) goto L17;
        r4 = r4 + 1;
        goto L14
    L17:
        r2 = r2 + 1;
    L19:
        r12 = r12 + 1;
        goto L11
    L20:
        return r2;
    L21:
        return 0;
    }

    public static long getDialogId(TLRPC.Message r6) {
        if (r6.dialog_id != 0) goto L22;
        TLRPC.Peer r0 = r6.peer_id;
        if (r0 == null) goto L22;
        long r4 = r0.chat_id;
        if (r4 == 0) goto L9;
        r6.dialog_id = -r4;
        goto L22
    L9:
        long r02 = r0.channel_id;
        if (r02 == 0) goto L13;
        r6.dialog_id = -r02;
        goto L22
    L13:
        if (r6.from_id != null) goto L15;
    L20:
        r6.dialog_id = r6.peer_id.user_id;
        goto L22
    L15:
        if (isOut(r6) == true) goto L20;
        if (r6.guestchat_via_from != null) goto L20;
        r6.dialog_id = r6.from_id.user_id;
    L22:
        return r6.dialog_id;
    }

    public static TLRPC.Document getDocument(TLRPC.Message r3) {
        if (r3 == null) goto L8;
        TL_iv.RichMessage r0 = r3.rich_message;
        if (r0 == null) goto L8;
        return findVideo(r0);
    L8:
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L12;
        return getMedia(r3).webpage.document;
    L12:
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaGame) == false) goto L16;
        return getMedia(r3).game.document;
    L16:
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaStory) == false) goto L25;
        TL_stories.StoryItem r02 = ((TLRPC.TL_messageMediaStory) getMedia(r3)).storyItem;
        if (r02 == null) goto L33;
        TLRPC.MessageMedia r03 = r02.media;
        if (r03 == null) goto L33;
        TLRPC.Document r04 = r03.document;
        if (r04 == null) goto L33;
        return r04;
    L33:
        if (getMedia(r3) != null) goto L35;
        return null;
    L35:
        return getMedia(r3).document;
    L25:
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L33;
        TLRPC.TL_messageMediaPaidMedia r05 = (TLRPC.TL_messageMediaPaidMedia) getMedia(r3);
        if (r05.extended_media.size() != 1) goto L33;
        if ((r05.extended_media.get(0) instanceof TLRPC.TL_messageExtendedMedia) == false) goto L33;
        return ((TLRPC.TL_messageExtendedMedia) r05.extended_media.get(0)).media.document;
    }

    public static double getDocumentDuration(TLRPC.Document r6) {
        if (r6 != null) goto L5;
        return 0.0d;
    L5:
        int r2 = r6.attributes.size();
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L16;
        TLRPC.DocumentAttribute r4 = r6.attributes.get(r3);
        if ((r4 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L10;
        if ((r4 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L14;
        r3 = r3 + 1;
        goto L6
    L14:
        return r4.duration;
    L10:
        return r4.duration;
    L16:
        return 0.0d;
    }

    public static TLRPC.VideoSize getDocumentVideoThumb(TLRPC.Document r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        if (r1.video_thumbs.isEmpty() == false) goto L7;
        return null;
    L7:
        return r1.video_thumbs.get(0);
    }

    private TLRPC.Document getDocumentWithId(TLRPC.WebPage r5, long r6) {
        if (r5 != null) goto L5;
    L19:
        return null;
    L5:
        if (r5.cached_page == null) goto L19;
        TLRPC.Document r0 = r5.document;
        if (r0 != null) goto L10;
    L12:
        int r02 = 0;
    L14:
        if (r02 >= r5.cached_page.documents.size()) goto L19;
        TLRPC.Document r1 = r5.cached_page.documents.get(r02);
        if (r1.id == r6) goto L17;
        r02 = r02 + 1;
        goto L14
    L17:
        return r1;
    L10:
        if (r0.id != r6) goto L12;
        return r0;
    }

    public static String getEmoji(TLRPC.Document r3) {
        if (r3 != null) goto L5;
        return "😀";
    L5:
        TLRPC.TL_documentAttributeCustomEmoji r1 = (TLRPC.TL_documentAttributeCustomEmoji) AndroidUtilities.find(r3.attributes, TLRPC.TL_documentAttributeCustomEmoji.class);
        if (r1 != null) goto L8;
    L11:
        TLRPC.TL_documentAttributeSticker r32 = (TLRPC.TL_documentAttributeSticker) AndroidUtilities.find(r3.attributes, TLRPC.TL_documentAttributeSticker.class);
        if (r32 != null) goto L14;
    L17:
        return "😀";
    L14:
        if (TextUtils.isEmpty(r32.alt) == true) goto L17;
        return r32.alt;
    L8:
        if (TextUtils.isEmpty(r1.alt) == true) goto L11;
        return r1.alt;
    }

    public static String getFileName(TLRPC.Document r0) {
        return FileLoader.getAttachFileName(r0);
    }

    public static String getFileName(TLRPC.Message r1) {
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaDocument) == false) goto L7;
        return getFileName(getDocument(r1));
    L7:
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L15;
        ArrayList<TLRPC.PhotoSize> r12 = getMedia(r1).photo.sizes;
        if (r12.size() <= 0) goto L20;
        TLRPC.PhotoSize r13 = FileLoader.getClosestPhotoSizeWithSize(r12, AndroidUtilities.getPhotoSize());
        if (r13 != null) goto L13;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        return FileLoader.getAttachFileName(r13);
    L20:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L15:
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L17;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        if (getMedia(r1).webpage != null) goto L19;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L19:
        return getFileName(getMedia(r1).webpage.document);
    }

    public static String getFileName(TLRPC.MessageMedia r1) {
        if ((r1 instanceof TLRPC.TL_messageMediaDocument) == false) goto L7;
        return FileLoader.getAttachFileName(r1.document);
    L7:
        if ((r1 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L15;
        ArrayList<TLRPC.PhotoSize> r12 = r1.photo.sizes;
        if (r12.size() <= 0) goto L20;
        TLRPC.PhotoSize r13 = FileLoader.getClosestPhotoSizeWithSize(r12, AndroidUtilities.getPhotoSize());
        if (r13 != null) goto L13;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        return FileLoader.getAttachFileName(r13);
    L20:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L15:
        if ((r1 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L23;
        TLRPC.WebPage r14 = r1.webpage;
        if (r14 != null) goto L19;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L19:
        return FileLoader.getAttachFileName(r14.document);
    L23:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private static int getForumFlags(TLRPC.Chat r3, TLRPC.User r4) {
        return BitwiseUtils.setFlag(BitwiseUtils.setFlag(BitwiseUtils.setFlag(0, 1, ChatObject.isForum(r3)), 4, ChatObject.isMonoForum(r3)), 8, UserObject.isBotForum(r4));
    }

    public static long getFromChatId(TLRPC.Message r2) {
        return getPeerId(r2.from_id);
    }

    public static int getInlineResultDuration(TLRPC.BotInlineResult r2) {
        int r0 = (int) getWebDocumentDuration(r2.content);
        if (r0 == 0) goto L5;
        return r0;
    L5:
        return (int) getWebDocumentDuration(r2.thumb);
    }

    public static int[] getInlineResultWidthAndHeight(TLRPC.BotInlineResult r2) {
        int[] r0 = getWebDocumentWidthAndHeight(r2.content);
        if (r0 != null) goto L8;
        int[] r22 = getWebDocumentWidthAndHeight(r2.thumb);
        if (r22 == null) goto L7;
        return r22;
    L7:
        return new int[]{0, 0};
    L8:
        return r0;
    }

    public static TLRPC.InputStickerSet getInputStickerSet(TLRPC.Document r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        int r1 = r5.attributes.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L17;
        TLRPC.DocumentAttribute r3 = r5.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L13;
        if ((r3 instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L13;
        r2 = r2 + 1;
    L13:
        TLRPC.InputStickerSet r52 = r3.stickerset;
        if ((r52 instanceof TLRPC.TL_inputStickerSetEmpty) == false) goto L16;
        return null;
    L16:
        return r52;
    L17:
        return null;
    }

    public static TLRPC.InputStickerSet getInputStickerSet(TLRPC.Message r0) {
        TLRPC.Document r02 = getDocument(r0);
        if (r02 != null) goto L5;
        return null;
    L5:
        return getInputStickerSet(r02);
    }

    public static TLRPC.MessageMedia getMedia(MessageObject r1) {
        if (r1 == null) goto L11;
        TLRPC.Message r0 = r1.messageOwner;
        if (r0 == null) goto L13;
        TLRPC.MessageMedia r12 = r1.sponsoredMedia;
        if (r12 == null) goto L10;
        return r12;
    L10:
        return getMedia(r0);
    L13:
        return null;
    L11:
        return null;
    }

    public static TLRPC.MessageMedia getMedia(TLRPC.Message r2) {
        TLRPC.MessageMedia r0 = r2.media;
        if (r0 == null) goto L13;
        if ((r0 instanceof TLRPC.TL_messageMediaPaidMedia) == true) goto L13;
        if (r0.extended_media.isEmpty() == true) goto L13;
        if ((r2.media.extended_media.get(0) instanceof TLRPC.TL_messageExtendedMedia) == false) goto L13;
        return ((TLRPC.TL_messageExtendedMedia) r2.media.extended_media.get(0)).media;
    L13:
        return r2.media;
    }

    public static <T extends TLRPC.MessageMedia> T getMedia(TLRPC.Message r1, Class<T> r2) {
        TLRPC.MessageMedia r12 = getMedia(r1);
        if (r2.isInstance(r12) == true) goto L5;
        return null;
    L5:
        return r2.cast(r12);
    }

    public static long getMediaSize(TLRPC.MessageMedia r2) {
        if ((r2 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L8;
        TLRPC.WebPage r0 = r2.webpage;
        if (r0 == null) goto L8;
        TLRPC.Document r22 = r0.document;
    L13:
        if (r22 != null) goto L15;
        return 0;
    L15:
        return r22.size;
    L8:
        if ((r2 instanceof TLRPC.TL_messageMediaGame) == false) goto L10;
        r22 = r2.game.document;
        goto L13
    L10:
        if (r2 == null) goto L12;
        r22 = r2.document;
        goto L13
    L12:
        r22 = null;
        goto L13
    }

    private MessageObject getMessageObjectForBlock(TLRPC.WebPage r5, TL_iv.PageBlock r6) {
        if ((r6 instanceof TL_iv.pageBlockPhoto) == false) goto L9;
        TLRPC.Photo r62 = getPhotoWithId(r5, ((TL_iv.pageBlockPhoto) r6).photo_id);
        if (r62 == r5.photo) goto L12;
        TLRPC.TL_message r52 = new TLRPC.TL_message();
        TLRPC.TL_messageMediaPhoto r0 = new TLRPC.TL_messageMediaPhoto();
        r52.media = r0;
        r0.photo = r62;
    L15:
        r52.message = _UrlKt.FRAGMENT_ENCODE_SET;
        r52.realId = getId();
        r52.id = Utilities.random.nextInt();
        TLRPC.Message r63 = this.messageOwner;
        r52.date = r63.date;
        r52.peer_id = r63.peer_id;
        r52.out = r63.out;
        r52.from_id = r63.from_id;
        return new MessageObject(this.currentAccount, r52, false, true);
    L12:
        return this;
    L9:
        if ((r6 instanceof TL_iv.pageBlockVideo) == false) goto L14;
        TL_iv.pageBlockVideo r64 = (TL_iv.pageBlockVideo) r6;
        if (getDocumentWithId(r5, r64.video_id) == r5.document) goto L12;
        TLRPC.TL_message r02 = new TLRPC.TL_message();
        TLRPC.TL_messageMediaDocument r1 = new TLRPC.TL_messageMediaDocument();
        r02.media = r1;
        r1.document = getDocumentWithId(r5, r64.video_id);
        r52 = r02;
        goto L15
    L14:
        r52 = null;
        goto L15
    }

    public static long getMessageSize(TLRPC.Message r2) {
        return getMediaSize(getMedia(r2));
    }

    public static long getMonoForumTopicId(TLRPC.Message r5) {
        if (r5 == null) goto L16;
        TLRPC.Peer r52 = r5.saved_peer_id;
        if (r52 == null) goto L17;
        long r2 = r52.chat_id;
        if (r2 != 0) goto L9;
        long r22 = r52.channel_id;
        if (r22 == 0) goto L15;
        return -r22;
    L15:
        return r52.user_id;
    L9:
        return -r2;
    L17:
        return 0;
    L16:
        return 0;
    }

    public static String getMusicAuthor(TLRPC.Document r3, boolean r4) {
        if (r3 == null) goto L17;
        int r0 = 0;
    L5:
        if (r0 >= r3.attributes.size()) goto L17;
        TLRPC.DocumentAttribute r1 = r3.attributes.get(r0);
        if ((r1 instanceof TLRPC.TL_documentAttributeAudio) == false) goto L15;
        if (r1.voice == true) goto L15;
        String r32 = r1.performer;
        if (TextUtils.isEmpty(r32) == false) goto L22;
        if (r4 == true) goto L14;
        return r32;
    L14:
        return LocaleController.getString(R.string.AudioUnknownArtist);
    L22:
        return r32;
    L15:
        r0 = r0 + 1;
    L17:
        return LocaleController.getString(R.string.AudioUnknownArtist);
    }

    public static String getMusicTitle(TLRPC.Document r3, boolean r4) {
        if (r3 == null) goto L4;
        int r0 = 0;
    L7:
        if (r0 >= r3.attributes.size()) goto L22;
        TLRPC.DocumentAttribute r1 = r3.attributes.get(r0);
        if ((r1 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        String r02 = r1.title;
        if (r02 != null) goto L13;
    L16:
        String r32 = FileLoader.getDocumentFileName(r3);
        if (TextUtils.isEmpty(r32) == false) goto L29;
        if (r4 == true) goto L20;
        return r32;
    L20:
        return LocaleController.getString(R.string.AudioUnknownTitle);
    L29:
        return r32;
    L13:
        if (r02.isEmpty() == true) goto L16;
        return r02;
    L22:
        String r33 = FileLoader.getDocumentFileName(r3);
        if (TextUtils.isEmpty(r33) == true) goto L26;
        return r33;
    L26:
        return LocaleController.getString(R.string.AudioUnknownTitle);
    L4:
        return LocaleController.getString(R.string.AudioUnknownTitle);
    }

    public static Long getMyPaidReactionPeer(TLRPC.MessageReactions r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        ArrayList<TLRPC.MessageReactor> r52 = r5.top_reactors;
        if (r52 != null) goto L8;
        return null;
    L8:
        int r1 = r52.size();
        int r2 = 0;
    L9:
        if (r2 >= r1) goto L22;
        TLRPC.MessageReactor r3 = r52.get(r2);
        r2 = r2 + 1;
        TLRPC.MessageReactor r32 = r3;
        if (r32 == null) goto L9;
        if (r32.my == false) goto L9;
        if (r32.anonymous == true) goto L17;
        TLRPC.Peer r33 = r32.peer_id;
        if (r33 == null) goto L9;
        return Long.valueOf(DialogObject.getPeerDialogId(r33));
    L17:
        return Long.valueOf(UserObject.ANONYMOUS);
    L22:
        return null;
    }

    public static long getObjectPeerId(TLObject r3) {
        if (r3 != null) goto L6;
        return 0;
    L6:
        if ((r3 instanceof TLRPC.Chat) == false) goto L10;
        return -((TLRPC.Chat) r3).id;
    L10:
        if ((r3 instanceof TLRPC.User) == true) goto L12;
        return 0;
    L12:
        return ((TLRPC.User) r3).id;
    }

    private int getParentWidth() {
        if (this.preview == false) goto L8;
        int r1 = this.parentWidth;
        if (r1 <= 0) goto L8;
        return r1;
    L8:
        if (AndroidUtilities.isTablet() == true) goto L10;
        Point r12 = AndroidUtilities.displaySize;
        int r0 = r12.x;
        if (r0 > r12.y) goto L14;
        return r0;
    L14:
        return r0 - AndroidUtilities.dp(50.0f);
    L10:
        return AndroidUtilities.getMinTabletSide();
    }

    public static long getPeerId(TLRPC.Peer r2) {
        if (r2 != null) goto L6;
        return 0;
    L6:
        if ((r2 instanceof TLRPC.TL_peerChat) == false) goto L11;
        long r0 = r2.chat_id;
    L9:
        return -r0;
    L11:
        if ((r2 instanceof TLRPC.TL_peerChannel) == false) goto L14;
        r0 = r2.channel_id;
        goto L9
    L14:
        return r2.user_id;
    }

    public static String getPeerObjectName(TLObject r1) {
        if ((r1 instanceof TLRPC.User) == false) goto L7;
        return UserObject.getUserName((TLRPC.User) r1);
    L7:
        if ((r1 instanceof TLRPC.Chat) == true) goto L9;
        return "DELETED";
    L9:
        return ((TLRPC.Chat) r1).title;
    }

    public static TLRPC.Photo getPhoto(TLRPC.Message r1) {
        if (r1 == null) goto L8;
        TL_iv.RichMessage r0 = r1.rich_message;
        if (r0 == null) goto L8;
        return findPhoto(r0);
    L8:
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L12;
        return getMedia(r1).webpage.photo;
    L12:
        if (getMedia(r1) != null) goto L14;
        return null;
    L14:
        return getMedia(r1).photo;
    }

    private TLRPC.Photo getPhotoWithId(TLRPC.WebPage r5, long r6) {
        if (r5 != null) goto L5;
    L19:
        return null;
    L5:
        if (r5.cached_page == null) goto L19;
        TLRPC.Photo r0 = r5.photo;
        if (r0 != null) goto L10;
    L12:
        int r02 = 0;
    L14:
        if (r02 >= r5.cached_page.photos.size()) goto L19;
        TLRPC.Photo r1 = r5.cached_page.photos.get(r02);
        if (r1.id == r6) goto L17;
        r02 = r02 + 1;
        goto L14
    L17:
        return r1;
    L10:
        if (r0.id != r6) goto L12;
        return r0;
    }

    public static TLRPC.PollAnswerVoters getPollResult(TLRPC.TL_messageMediaPoll r5, byte[] r6) {
        if (r5 == null) goto L15;
        TLRPC.PollResults r1 = r5.results;
        if (r1 == null) goto L15;
        if (r1.results.isEmpty() == true) goto L15;
        int r12 = r5.results.results.size();
        int r2 = 0;
    L10:
        if (r2 >= r12) goto L15;
        TLRPC.PollAnswerVoters r3 = r5.results.results.get(r2);
        if (Arrays.equals(r3.option, r6) == true) goto L13;
        r2 = r2 + 1;
        goto L10
    L13:
        return r3;
    L15:
        return null;
    }

    public static TLRPC.VideoSize getPremiumStickerAnimation(TLRPC.Document r4) {
        if (r4 != null) goto L5;
    L15:
        return null;
    L5:
        if (r4.thumbs == null) goto L15;
        int r1 = 0;
    L9:
        if (r1 >= r4.video_thumbs.size()) goto L15;
        if ("f".equals(r4.video_thumbs.get(r1).type) == true) goto L13;
        r1 = r1 + 1;
        goto L9
    L13:
        return r4.video_thumbs.get(r1);
    }

    public static int getQuickReplyId(int r3, TLRPC.Message r4) {
        if (r4 != null) goto L6;
        return 0;
    L6:
        if ((r4.flags & TLObject.FLAG_30) != 0) goto L8;
        TLRPC.InputQuickReplyShortcut r1 = r4.quick_reply_shortcut;
        if ((r1 instanceof TLRPC.TL_inputQuickReplyShortcutId) == true) goto L12;
        String r42 = getQuickReplyName(r4);
        if (r42 == null) goto L19;
        QuickRepliesController.QuickReply r32 = QuickRepliesController.getInstance(r3).findReply(r42);
        if (r32 == null) goto L19;
        return r32.id;
    L19:
        return 0;
    L12:
        return ((TLRPC.TL_inputQuickReplyShortcutId) r1).shortcut_id;
    L8:
        return r4.quick_reply_shortcut_id;
    }

    public static int getQuickReplyId(TLRPC.Message r3) {
        if (r3 != null) goto L6;
        return 0;
    L6:
        if ((r3.flags & TLObject.FLAG_30) != 0) goto L8;
        TLRPC.InputQuickReplyShortcut r32 = r3.quick_reply_shortcut;
        if ((r32 instanceof TLRPC.TL_inputQuickReplyShortcutId) == true) goto L12;
        return 0;
    L12:
        return ((TLRPC.TL_inputQuickReplyShortcutId) r32).shortcut_id;
    L8:
        return r3.quick_reply_shortcut_id;
    }

    public static String getQuickReplyName(TLRPC.Message r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        TLRPC.InputQuickReplyShortcut r22 = r2.quick_reply_shortcut;
        if ((r22 instanceof TLRPC.TL_inputQuickReplyShortcut) == true) goto L8;
        return null;
    L8:
        return ((TLRPC.TL_inputQuickReplyShortcut) r22).shortcut;
    }

    public static long getReplyToDialogId(TLRPC.Message r2) {
        TLRPC.MessageReplyHeader r0 = r2.reply_to;
        if (r0 != null) goto L6;
        return 0;
    L6:
        TLRPC.Peer r02 = r0.reply_to_peer_id;
        if (r02 == null) goto L11;
        return getPeerId(r02);
    L11:
        return getDialogId(r2);
    }

    public static long getSavedDialogId(long r5, TLRPC.Message r7) {
        TLRPC.Peer r0 = r7.saved_peer_id;
        if (r0 == null) goto L15;
        long r52 = r0.chat_id;
        if (r52 != 0) goto L7;
        long r53 = r0.channel_id;
        if (r53 == 0) goto L13;
        return -r53;
    L13:
        return r0.user_id;
    L7:
        return -r52;
    L15:
        if (r7.from_id.user_id != r5) goto L29;
        TLRPC.MessageFwdHeader r72 = r7.fwd_from;
        if (r72 == null) goto L22;
        TLRPC.Peer r02 = r72.saved_from_peer;
        if (r02 == null) goto L22;
        return DialogObject.getPeerDialogId(r02);
    L22:
        if (r72 != null) goto L24;
    L26:
        if (r72 == null) goto L30;
        return UserObject.ANONYMOUS;
    L30:
        return r5;
    L24:
        if (r72.from_id == null) goto L26;
        return r5;
    L29:
        return 0;
    }

    public static TLRPC.Peer getSavedDialogPeer(long r2, TLRPC.Message r4) {
        TLRPC.Peer r0 = r4.saved_peer_id;
        if (r0 == null) goto L5;
        return r0;
    L5:
        TLRPC.Peer r02 = r4.peer_id;
        if (r02 != null) goto L8;
        return null;
    L8:
        if (r02.user_id != r2) goto L30;
        TLRPC.Peer r03 = r4.from_id;
        if (r03 != null) goto L12;
        return null;
    L12:
        if (r03.user_id != r2) goto L32;
        TLRPC.MessageFwdHeader r42 = r4.fwd_from;
        if (r42 == null) goto L18;
        TLRPC.Peer r04 = r42.saved_from_peer;
        if (r04 == null) goto L18;
        return r04;
    L18:
        if (r42 != null) goto L20;
    L23:
        if (r42 == null) goto L26;
        TLRPC.TL_peerUser r22 = new TLRPC.TL_peerUser();
        r22.user_id = UserObject.ANONYMOUS;
        return r22;
    L26:
        TLRPC.TL_peerUser r43 = new TLRPC.TL_peerUser();
        r43.user_id = r2;
        return r43;
    L20:
        if (r42.from_id == null) goto L23;
        TLRPC.TL_peerUser r44 = new TLRPC.TL_peerUser();
        r44.user_id = r2;
        return r44;
    L32:
        return null;
    L30:
        return null;
    }

    public static long getStakedDiceWinAmount(TLRPC.TL_messageMediaDice r4) {
        TLRPC.TL_messages_emojiGameOutcome r42 = r4.game_outcome;
        if (r42 == null) goto L9;
        long r2 = r42.ton_amount;
        if (r2 <= 0) goto L8;
        return r2;
    L8:
        return -r42.stake_ton_amount;
    L9:
        return 0;
    }

    public static long getStickerSetId(TLRPC.Document r5) {
        if (r5 != null) goto L5;
        return -1;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= r5.attributes.size()) goto L16;
        TLRPC.DocumentAttribute r3 = r5.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L10;
        r2 = r2 + 1;
        goto L7
    L10:
        TLRPC.InputStickerSet r52 = r3.stickerset;
        if ((r52 instanceof TLRPC.TL_inputStickerSetEmpty) == false) goto L14;
        return -1;
    L14:
        return r52.id;
    L16:
        return -1;
    }

    public static String getStickerSetName(TLRPC.Document r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r4.attributes.size()) goto L16;
        TLRPC.DocumentAttribute r2 = r4.attributes.get(r1);
        if ((r2 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L10;
        r1 = r1 + 1;
        goto L7
    L10:
        TLRPC.InputStickerSet r42 = r2.stickerset;
        if ((r42 instanceof TLRPC.TL_inputStickerSetEmpty) == false) goto L14;
        return null;
    L14:
        return r42.short_name;
    L16:
        return null;
    }

    private CharSequence getStringFrom(TLRPC.ChatReactions r5) {
        if ((r5 instanceof TLRPC.TL_chatReactionsAll) == false) goto L7;
        return LocaleController.getString(R.string.AllReactions);
    L7:
        if ((r5 instanceof TLRPC.TL_chatReactionsSome) == false) goto L16;
        TLRPC.TL_chatReactionsSome r52 = (TLRPC.TL_chatReactionsSome) r5;
        SpannableStringBuilder r4 = new SpannableStringBuilder();
        int r1 = 0;
    L10:
        if (r1 >= r52.reactions.size()) goto L14;
        if (r1 == 0) goto L13;
        r4.append(" ");
    L13:
        r4.append(Emoji.replaceEmoji(ReactionsUtils.reactionToCharSequence(r52.reactions.get(r1)), null, false));
        r1 = r1 + 1;
        goto L10
    L14:
        return r4;
    L16:
        return LocaleController.getString(R.string.NoReactions);
    }

    public static long getTopicId(int r1, TLRPC.Message r2) {
        return getTopicId(r1, r2, false);
    }

    public static long getTopicId(int r2, TLRPC.Message r3, int r4) {
        long r22 = getTopicId(r2, r3, BitwiseUtils.hasFlag(r4, 1), BitwiseUtils.hasFlag(r4, 4));
        if (r22 == 0) goto L5;
        return r22;
    L5:
        if (BitwiseUtils.hasFlag(r4, 8) == false) goto L9;
        return -1;
    L9:
        return r22;
    }

    @Deprecated
    public static long getTopicId(int r1, TLRPC.Message r2, boolean r3) {
        return getTopicId(r1, r2, r3, false);
    }

    @Deprecated
    private static long getTopicId(int r4, TLRPC.Message r5, boolean r6, boolean r7) {
        long r0 = UserConfig.getInstance(r4).getClientUserId();
        if (r7 == false) goto L7;
        return getMonoForumTopicId(r5);
    L7:
        if ((r5.flags & TLObject.FLAG_30) != 0) goto L9;
    L13:
        if (r6 == true) goto L19;
        if (r4 < 0) goto L19;
        if (DialogObject.getPeerDialogId(r5.peer_id) != r0) goto L19;
        return getSavedDialogId(r0, r5);
    L19:
        TLRPC.MessageAction r42 = r5.action;
        if ((r42 instanceof TLRPC.TL_messageActionTopicCreate) == false) goto L22;
        int r43 = r5.id;
    L12:
        return r43;
    L22:
        TLRPC.MessageReplyHeader r72 = r5.reply_to;
        if (r72 != null) goto L25;
    L41:
        if (r6 == false) goto L44;
        return 1;
    L44:
        return 0;
    L25:
        if (r72.forum_topic == false) goto L41;
        if ((r5 instanceof TLRPC.TL_messageService) == true) goto L30;
    L36:
        int r44 = r72.reply_to_top_id;
        if (r44 != 0) goto L40;
        r44 = r72.reply_to_msg_id;
    L40:
        return r44;
    L30:
        if ((r42 instanceof TLRPC.TL_messageActionPinMessage) == true) goto L36;
        int r45 = r72.reply_to_msg_id;
        if (r45 != 0) goto L35;
        r45 = r72.reply_to_top_id;
    L35:
        return r45;
    L9:
        if (DialogObject.getPeerDialogId(r5.peer_id) != r0) goto L13;
        r43 = r5.quick_reply_shortcut_id;
        goto L12
    }

    private static long getTopicId(MessageObject r2) {
        if (r2 != null) goto L6;
        return 0;
    L6:
        return getTopicId(r2.currentAccount, r2.messageOwner, false);
    }

    public static int getUnreadFlags(TLRPC.Message r1) {
        int r0 = !r1.unread ? 1 : 0;
        if (r1.media_unread == false) goto L5;
        return r0;
    L5:
        return r0 | 2;
    }

    private TLRPC.User getUser(AbstractMap<Long, TLRPC.User> r1, LongSparseArray<TLRPC.User> r2, long r3) {
        if (r1 == null) goto L4;
        TLRPC.User r12 = r1.get(Long.valueOf(r3));
    L7:
        if (r12 == null) goto L9;
        return r12;
    L9:
        return MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r3));
    L4:
        if (r2 == null) goto L6;
        r12 = r2.get(r3);
        goto L7
    L6:
        r12 = null;
        goto L7
    }

    private String getUserName(TLObject r5, ArrayList<TLRPC.MessageEntity> r6, int r7) {
        if (r5 != null) goto L5;
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r52 = null;
        long r0 = 0;
    L13:
        if (r7 < 0) goto L16;
        TLRPC.TL_messageEntityMentionName r2 = new TLRPC.TL_messageEntityMentionName();
        r2.user_id = r0;
        r2.offset = r7;
        r2.length = r4.length();
        r6.add(r2);
    L16:
        if (TextUtils.isEmpty(r52) == true) goto L21;
        if (r7 < 0) goto L20;
        TLRPC.TL_messageEntityMentionName r22 = new TLRPC.TL_messageEntityMentionName();
        r22.user_id = r0;
        r22.offset = (r7 + r4.length()) + 2;
        r22.length = r52.length() + 1;
        r6.add(r22);
    L20:
        return String.format("%1$s (@%2$s)", new Object[]{r4, r52});
    L21:
        return r4;
    L5:
        if ((r5 instanceof TLRPC.User) == false) goto L12;
        TLRPC.User r53 = (TLRPC.User) r5;
        if (r53.deleted == false) goto L9;
        r4 = LocaleController.getString(R.string.HiddenName);
    L10:
        String r02 = UserObject.getPublicUsername(r53);
        long r1 = r53.id;
    L11:
        r52 = r02;
        r0 = r1;
        goto L13
    L9:
        r4 = ContactsController.formatName(r53.first_name, r53.last_name);
        goto L10
    L12:
        TLRPC.Chat r54 = (TLRPC.Chat) r5;
        r4 = r54.title;
        r02 = ChatObject.getPublicUsername(r54);
        r1 = -r54.id;
        goto L11
    }

    private String getUsernamesString(ArrayList<String> r3) {
        if (r3 == null) goto L16;
        if (r3.size() == 0) goto L16;
        StringBuilder r2 = new StringBuilder();
        int r0 = 0;
    L8:
        if (r0 >= r3.size()) goto L14;
        r2.append("@");
        r2.append(r3.get(r0));
        if (r0 >= (r3.size() - 1)) goto L12;
        r2.append(", ");
    L12:
        r0 = r0 + 1;
        goto L8
    L14:
        return r2.toString();
    L16:
        return LocaleController.getString(R.string.UsernameEmpty).toLowerCase();
    }

    public static String getVideoCodec(TLRPC.Document r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        int r1 = r5.attributes.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        TLRPC.DocumentAttribute r3 = r5.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L10;
        r2 = r2 + 1;
        goto L6
    L10:
        return ((TLRPC.TL_documentAttributeVideo) r3).video_codec;
    L12:
        return null;
    }

    public static int getVideoHeight(TLRPC.Document r5) {
        if (r5 != null) goto L5;
        return 0;
    L5:
        int r1 = r5.attributes.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        TLRPC.DocumentAttribute r3 = r5.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L10;
        r2 = r2 + 1;
        goto L6
    L10:
        return r3.h;
    L12:
        return 0;
    }

    public static int getVideoWidth(TLRPC.Document r5) {
        if (r5 != null) goto L5;
        return 0;
    L5:
        int r1 = r5.attributes.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        TLRPC.DocumentAttribute r3 = r5.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L10;
        r2 = r2 + 1;
        goto L6
    L10:
        return r3.w;
    L12:
        return 0;
    }

    public static double getWebDocumentDuration(TLRPC.WebDocument r6) {
        if (r6 != null) goto L5;
        return 0.0d;
    L5:
        int r2 = r6.attributes.size();
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L16;
        TLRPC.DocumentAttribute r4 = r6.attributes.get(r3);
        if ((r4 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L10;
        if ((r4 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L14;
        r3 = r3 + 1;
        goto L6
    L14:
        return r4.duration;
    L10:
        return r4.duration;
    L16:
        return 0.0d;
    }

    public static int[] getWebDocumentWidthAndHeight(TLRPC.WebDocument r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        int r1 = r5.attributes.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L16;
        TLRPC.DocumentAttribute r3 = r5.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L10;
        if ((r3 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L14;
        r2 = r2 + 1;
        goto L6
    L14:
        return new int[]{r3.w, r3.h};
    L10:
        return new int[]{r3.w, r3.h};
    L16:
        return null;
    }

    public static CharSequence groupSpan() {
        if (groupSpan != null) goto L6;
        groupSpan = new SpannableStringBuilder(ImageLoader.AUTOPLAY_FILTER);
        ColoredImageSpan r0 = new ColoredImageSpan(R.drawable.msg_folders_groups);
        r0.setScale(0.7f, 0.7f);
        ((SpannableStringBuilder) groupSpan).setSpan(r0, 0, 1, 33);
    L6:
        return groupSpan;
    }

    private void handleFoundWords(ArrayList<String> r2, String[] r3, boolean r4) {
        handleFoundWords(r2, r3, r4, true);
    }

    private void handleFoundWords(ArrayList<String> r12, String[] r13, boolean r14, boolean r15) {
        if (r12.isEmpty() == true) goto L66;
        int r1 = 0;
        boolean r2 = false;
    L6:
        if (r1 >= r12.size()) goto L16;
        int r3 = r13.length;
        int r5 = 0;
    L8:
        if (r5 >= r3) goto L13;
        if (r12.get(r1).contains(r13[r5]) == true) goto L11;
        r5 = r5 + 1;
        goto L8
    L11:
        r2 = true;
    L13:
        if (r2 == true) goto L16;
        r1 = r1 + 1;
    L16:
        if (r2 == false) goto L33;
        int r16 = 0;
    L19:
        if (r16 >= r12.size()) goto L31;
        int r22 = r13.length;
        int r32 = 0;
    L21:
        if (r32 >= r22) goto L26;
        if (r12.get(r16).contains(r13[r32]) == true) goto L24;
        r32 = r32 + 1;
        goto L21
    L24:
        boolean r23 = true;
    L27:
        if (r23 == true) goto L29;
        r12.remove(r16);
        r16 = r16 - 1;
    L29:
        r16 = r16 + 1;
        goto L19
    L26:
        r23 = false;
        goto L27
    L31:
        if (r12.size() <= 0) goto L33;
        Collections.sort(r12, new MessageObject$$ExternalSyntheticLambda1());
        String r132 = r12.get(0);
        r12.clear();
        r12.add(r132);
    L33:
        this.highlightedWords = r12;
        if (this.messageOwner.message == null) goto L67;
        applyEntities();
        if (TextUtils.isEmpty(this.caption) == true) goto L38;
        CharSequence r133 = this.caption;
    L39:
        CharSequence r134 = AndroidUtilities.replaceMultipleCharSequence("\n", r133, " ");
        if (r14 == false) goto L48;
        TLRPC.Message r142 = this.messageOwner;
        if (r142 == null) goto L48;
        TLRPC.MessageReplyHeader r143 = r142.reply_to;
        if (r143 == null) goto L48;
        if (r143.quote_text == null) goto L48;
        SpannableStringBuilder r52 = new SpannableStringBuilder(this.messageOwner.reply_to.quote_text);
        addEntitiesToText(r52, this.messageOwner.reply_to.quote_entities, isOutOwner(), false, false, false);
        SpannableString r144 = new SpannableString("q ");
        ColoredImageSpan r17 = new ColoredImageSpan(R.drawable.mini_quote);
        r17.setOverrideColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
        r144.setSpan(r17, 0, 1, 33);
        r134 = new SpannableStringBuilder(r144).append(r52).append('\n').append(r134);
    L48:
        String r145 = r134.toString();
        int r18 = r145.length();
        int r122 = r145.toLowerCase().indexOf(r12.get(0));
        if (r122 >= 0) goto L52;
        r122 = 0;
    L52:
        if (r18 <= 120) goto L55;
        if (r15 == false) goto L55;
        int r146 = Math.max(0, r122 - 12);
        r134 = r134.subSequence(r146, Math.min(r18, ((r122 - r146) + r122) + 108));
    L55:
        this.messageTrimmedToHighlight = r134;
        this.messageTrimmedToHighlightCut = r15;
        return;
    L38:
        r133 = this.messageText;
        goto L39
    L67:
        return;
    }

    private boolean hasNonEmojiEntities() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L5;
    L22:
        return false;
    L5:
        if (r0.entities == null) goto L22;
        int r02 = 0;
    L9:
        if (r02 >= this.messageOwner.entities.size()) goto L22;
        TLRPC.MessageEntity r2 = this.messageOwner.entities.get(r02);
        if ((r2 instanceof TLRPC.TL_messageEntityCustomEmoji) == true) goto L19;
        if ((r2 instanceof TLRPC.TL_messageEntityTextUrl) == false) goto L30;
        String r22 = ((TLRPC.TL_messageEntityTextUrl) r2).url;
        if (r22 == null) goto L20;
        if (r22.startsWith("tg://emoji?id=") == true) goto L19;
        return true;
    L20:
        return true;
    L30:
        return true;
    L19:
        r02 = r02 + 1;
        goto L9
    }

    public static boolean hasUnreadReactions(TLRPC.Message r0) {
        if (r0 != null) goto L6;
        return false;
    L6:
        return hasUnreadReactions(r0.reactions);
    }

    public static boolean hasUnreadReactions(TLRPC.TL_messageReactions r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r3.recent_reactions.size()) goto L13;
        if (r3.recent_reactions.get(r1).unread == true) goto L10;
        r1 = r1 + 1;
        goto L7
    L10:
        return true;
    L13:
        return false;
    }

    public static boolean isAnimatedEmoji(TLRPC.Document r4) {
        if (r4 != null) goto L5;
        return false;
    L5:
        int r1 = r4.attributes.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        if ((r4.attributes.get(r2) instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    public static boolean isAnimatedStickerDocument(TLRPC.Document r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.mime_type.equals("video/webm") == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isAnimatedStickerDocument(TLRPC.Document r5, boolean r6) {
        if (r5 != null) goto L5;
    L23:
        return false;
    L5:
        if ("application/x-tgsticker".equals(r5.mime_type) == false) goto L9;
        if (r5.thumbs.isEmpty() == true) goto L9;
    L11:
        if (r6 == false) goto L13;
        return true;
    L13:
        int r62 = r5.attributes.size();
        int r2 = 0;
    L14:
        if (r2 >= r62) goto L23;
        TLRPC.DocumentAttribute r3 = r5.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L18;
        if ((r3 instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L21;
        r2 = r2 + 1;
        goto L14
    L21:
        return true;
    L18:
        return r3.stickerset instanceof TLRPC.TL_inputStickerSetShortName;
    L9:
        if ("application/x-tgsdice".equals(r5.mime_type) == false) goto L23;
        goto L23
    }

    public static boolean isAnimatedStickerMessage(TLRPC.Message r4) {
        boolean r0 = DialogObject.isEncryptedDialog(r4.dialog_id);
        if (r0 == false) goto L8;
        if (r4.stickerVerified == 1) goto L8;
        return false;
    L8:
        if (getMedia(r4) == null) goto L19;
        TLRPC.Document r3 = getMedia(r4).document;
        if (r0 == true) goto L12;
    L15:
        boolean r42 = true;
    L17:
        if (isAnimatedStickerDocument(r3, r42) == false) goto L19;
        return true;
    L12:
        if (r4.out == true) goto L15;
        r42 = false;
    L19:
        return false;
    }

    public static boolean isAnyKindOfStickerOrEmoji(TLRPC.Document r6) {
        if (r6 != null) goto L5;
        return false;
    L5:
        int r1 = r6.attributes.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L14;
        TLRPC.DocumentAttribute r3 = r6.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L9;
        if ((r3 instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L12;
        r2 = r2 + 1;
        goto L6
    L12:
        return true;
    L9:
        return true;
    L14:
        return false;
    }

    public static boolean isBlueBlock(TL_iv.PageBlock r1) {
        if ((r1 instanceof TL_iv.pageBlockTable) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof TL_iv.pageBlockMath) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TL_iv.pageBlockAudio) == false) goto L9;
        return true;
    L9:
        if ((r1 instanceof TL_iv.pageBlockDocument) == false) goto L11;
        return true;
    L11:
        if ((r1 instanceof TL_iv.pageBlockMap) == false) goto L13;
        return true;
    L13:
        if ((r1 instanceof TL_iv.pageBlockPhoto) == false) goto L15;
        return true;
    L15:
        if ((r1 instanceof TL_iv.pageBlockVideo) == false) goto L17;
        return true;
    L17:
        if ((r1 instanceof TL_iv.pageBlockCollage) == false) goto L19;
        return true;
    L19:
        if ((r1 instanceof TL_iv.pageBlockSlideshow) == true) goto L32;
        return false;
    L32:
        return true;
    }

    public static boolean isCompleted(MessageObject r2, int r3) {
        TLRPC.MessageMedia r22 = getMedia(r2);
        if ((r22 instanceof TLRPC.TL_messageMediaToDo) == true) goto L5;
        return false;
    L5:
        TLRPC.TL_messageMediaToDo r23 = (TLRPC.TL_messageMediaToDo) r22;
        TLRPC.TodoList r0 = r23.todo;
        if (r0 != null) goto L8;
    L12:
        return false;
    L8:
        if (r0.list == null) goto L12;
        return isCompleted(r23, r3);
    }

    public static boolean isCompleted(TLRPC.TL_messageMediaToDo r3, int r4) {
        int r1 = 0;
    L4:
        if (r1 >= r3.completions.size()) goto L10;
        if (r3.completions.get(r1).id == r4) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    public static boolean isContentUnread(TLRPC.Message r0) {
        return r0.media_unread;
    }

    public static boolean isDocumentHasAttachedStickers(TLRPC.Document r3) {
        if (r3 == null) goto L12;
        int r1 = 0;
    L6:
        if (r1 >= r3.attributes.size()) goto L12;
        if ((r3.attributes.get(r1) instanceof TLRPC.TL_documentAttributeHasStickers) == true) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    public static boolean isDocumentHasThumb(TLRPC.Document r5) {
        if (r5 != null) goto L5;
    L20:
        return false;
    L5:
        if (r5.thumbs.isEmpty() == true) goto L20;
        int r1 = r5.thumbs.size();
        int r2 = 0;
    L8:
        if (r2 >= r1) goto L20;
        TLRPC.PhotoSize r3 = r5.thumbs.get(r2);
        if (r3 == null) goto L19;
        if ((r3 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L19;
        if ((r3.location instanceof TLRPC.TL_fileLocationUnavailable) == false) goto L27;
        if (r3.bytes == null) goto L19;
        return true;
    L27:
        return true;
    L19:
        r2 = r2 + 1;
        goto L8
    }

    public static boolean isEphemeral(TLRPC.Message r4) {
        if (r4 != null) goto L4;
        return false;
    L4:
        if (isEphemeralMessageId(r4.id) == false) goto L6;
        return true;
    L6:
        if (r4.ephemeralReceiverBotId == 0) goto L12;
        return true;
    L12:
        return false;
    }

    public static boolean isEphemeralAndNotWelcome(TLRPC.Message r1) {
        if (isEphemeral(r1) == true) goto L5;
        return false;
    L5:
        if (isWelcomeMessage(r1) == true) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isEphemeralMessageId(int r1) {
        if ((r1 & MESSAGE_ID_RESERVED_BITS_MASK) != MESSAGE_ID_EPHEMERAL_BITS_MASK) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isExtendedVideo(TLRPC.MessageExtendedMedia r3) {
        if ((r3 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L11;
        TLRPC.MessageMedia r32 = ((TLRPC.TL_messageExtendedMedia) r3).media;
        if ((r32 instanceof TLRPC.TL_messageMediaDocument) == true) goto L7;
    L9:
        return false;
    L7:
        if (isVideoDocument(r32.document) == false) goto L9;
        return true;
    L11:
        if ((r3 instanceof TLRPC.TL_messageExtendedMediaPreview) == true) goto L13;
    L15:
        return false;
    L13:
        if ((((TLRPC.TL_messageExtendedMediaPreview) r3).flags & 4) == 0) goto L15;
        return true;
    }

    public static boolean isForwardedMessage(TLRPC.Message r1) {
        if ((r1.flags & 4) != 0) goto L5;
        return false;
    L5:
        if (r1.fwd_from == null) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isFreeEmoji(TLRPC.Document r5) {
        if (r5 != null) goto L5;
        return false;
    L5:
        int r1 = r5.attributes.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        TLRPC.DocumentAttribute r3 = r5.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L10;
        r2 = r2 + 1;
        goto L6
    L10:
        return ((TLRPC.TL_documentAttributeCustomEmoji) r3).free;
    L12:
        return false;
    }

    public static boolean isGameMessage(TLRPC.Message r0) {
        return getMedia(r0) instanceof TLRPC.TL_messageMediaGame;
    }

    public static boolean isGifDocument(WebFile r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (r2.mime_type.equals("image/gif") == false) goto L6;
        return true;
    L6:
        if (isNewGifDocument(r2) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public static boolean isGifDocument(TLRPC.Document r1) {
        return isGifDocument(r1, false);
    }

    public static boolean isGifDocument(TLRPC.Document r2, boolean r3) {
        if (r2 == null) goto L12;
        String r0 = r2.mime_type;
        if (r0 != null) goto L6;
        return false;
    L6:
        if (r0.equals("image/gif") == false) goto L9;
        if (r3 == true) goto L9;
        return true;
    L9:
        if (isNewGifDocument(r2) == false) goto L16;
        return true;
    L16:
        return false;
    L12:
        return false;
    }

    public static boolean isGifMessage(TLRPC.Message r6) {
        if ((getMedia(r6) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L7;
        return isGifDocument(getMedia(r6).webpage.document);
    L7:
        if (getMedia(r6) == null) goto L15;
        TLRPC.Document r0 = getMedia(r6).document;
        if (r6.grouped_id == 0) goto L11;
        boolean r62 = true;
    L13:
        if (isGifDocument(r0, r62) == false) goto L15;
        return true;
    L11:
        r62 = false;
    L15:
        return false;
    }

    public static boolean isImageWebDocument(WebFile r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (isGifDocument(r1) == false) goto L6;
        return false;
    L6:
        if (r1.mime_type.startsWith("image/") == false) goto L12;
        return true;
    L12:
        return false;
    }

    public static boolean isInvoiceMessage(TLRPC.Message r0) {
        return getMedia(r0) instanceof TLRPC.TL_messageMediaInvoice;
    }

    public static boolean isLiveLocationMessage(TLRPC.Message r0) {
        return getMedia(r0) instanceof TLRPC.TL_messageMediaGeoLive;
    }

    public static boolean isLocationMessage(TLRPC.Message r1) {
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaGeo) == false) goto L5;
        return true;
    L5:
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L7;
        return true;
    L7:
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaVenue) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public static boolean isMaskDocument(TLRPC.Document r4) {
        if (r4 == null) goto L14;
        int r1 = 0;
    L6:
        if (r1 >= r4.attributes.size()) goto L14;
        TLRPC.DocumentAttribute r2 = r4.attributes.get(r1);
        if ((r2 instanceof TLRPC.TL_documentAttributeSticker) == false) goto L13;
        if (r2.mask == false) goto L13;
        return true;
    L13:
        r1 = r1 + 1;
    L14:
        return false;
    }

    public static boolean isMaskMessage(TLRPC.Message r1) {
        if (getMedia(r1) != null) goto L5;
        return false;
    L5:
        if (isMaskDocument(getMedia(r1).document) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isMediaEmpty(TLRPC.Message r1) {
        return isMediaEmpty(r1, true);
    }

    public static boolean isMediaEmpty(TLRPC.Message r1, boolean r2) {
        if (r1 != null) goto L4;
        return true;
    L4:
        if (getMedia(r1) != null) goto L6;
        return true;
    L6:
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaEmpty) == true) goto L17;
        if (r2 == true) goto L9;
        return false;
    L9:
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L18;
        return false;
    L18:
        return true;
    L17:
        return true;
    }

    public static boolean isMediaEmptyWebpage(TLRPC.Message r1) {
        if (r1 != null) goto L4;
        return true;
    L4:
        if (getMedia(r1) != null) goto L6;
        return true;
    L6:
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaEmpty) == true) goto L13;
        return false;
    L13:
        return true;
    }

    public static boolean isMusicDocument(TLRPC.Document r5) {
        if (r5 == null) goto L34;
        int r1 = 0;
    L6:
        if (r1 >= r5.attributes.size()) goto L13;
        if ((r5.attributes.get(r1) instanceof TLRPC.TL_documentAttributeAudio) == true) goto L10;
        r1 = r1 + 1;
        goto L6
    L10:
        return !r2.voice;
    L13:
        if (TextUtils.isEmpty(r5.mime_type) == true) goto L34;
        String r12 = r5.mime_type.toLowerCase();
        if (r12.equals("audio/flac") == false) goto L17;
    L33:
        return true;
    L17:
        if (r12.equals("audio/ogg") == true) goto L33;
        if (r12.equals("audio/opus") == true) goto L33;
        if (r12.equals("audio/x-opus+ogg") == true) goto L33;
        if (r12.equals("audio/wav") == true) goto L33;
        if (r12.equals("audio/x-wav") == true) goto L33;
        if (r12.equals("application/octet-stream") == true) goto L30;
    L32:
        return false;
    L30:
        if (FileLoader.getDocumentFileName(r5).endsWith(".opus") == false) goto L32;
        return true;
    L34:
        return false;
    }

    public static boolean isMusicMessage(TLRPC.Message r1) {
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L7;
        return isMusicDocument(getMedia(r1).webpage.document);
    L7:
        if (getMedia(r1) != null) goto L9;
        return false;
    L9:
        if (isMusicDocument(getMedia(r1).document) == false) goto L14;
        return true;
    L14:
        return false;
    }

    public static boolean isNewGifDocument(WebFile r6) {
        if (r6 != null) goto L5;
    L21:
        return false;
    L5:
        if ("video/mp4".equals(r6.mime_type) == false) goto L21;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
    L8:
        if (r1 >= r6.attributes.size()) goto L17;
        TLRPC.DocumentAttribute r4 = r6.attributes.get(r1);
        if ((r4 instanceof TLRPC.TL_documentAttributeAnimated) == true) goto L15;
        if ((r4 instanceof TLRPC.TL_documentAttributeVideo) == false) goto L15;
        r2 = r4.w;
        r3 = r4.h;
    L15:
        r1 = r1 + 1;
        goto L8
    L17:
        if (r2 > 1280) goto L21;
        if (r3 > 1280) goto L21;
        return true;
    }

    public static boolean isNewGifDocument(TLRPC.Document r8) {
        if (r8 != null) goto L5;
    L21:
        return false;
    L5:
        if ("video/mp4".equals(r8.mime_type) == false) goto L21;
        int r1 = 0;
        boolean r2 = false;
        int r3 = 0;
        int r4 = 0;
    L8:
        if (r1 >= r8.attributes.size()) goto L16;
        TLRPC.DocumentAttribute r5 = r8.attributes.get(r1);
        if ((r5 instanceof TLRPC.TL_documentAttributeAnimated) == false) goto L13;
        r2 = true;
    L15:
        r1 = r1 + 1;
        goto L8
    L13:
        if ((r5 instanceof TLRPC.TL_documentAttributeVideo) == false) goto L15;
        r3 = r5.w;
        r4 = r5.h;
        goto L15
    L16:
        if (r2 == false) goto L21;
        if (r3 > 1280) goto L21;
        if (r4 > 1280) goto L21;
        return true;
    }

    public static boolean isNewGifMessage(TLRPC.Message r1) {
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L7;
        return isNewGifDocument(getMedia(r1).webpage.document);
    L7:
        if (getMedia(r1) != null) goto L9;
        return false;
    L9:
        if (isNewGifDocument(getMedia(r1).document) == false) goto L14;
        return true;
    L14:
        return false;
    }

    public static boolean isOut(TLRPC.Message r0) {
        return r0.out;
    }

    public static boolean isPaidVideo(TLRPC.MessageMedia r3) {
        if ((r3 instanceof TLRPC.TL_messageMediaPaidMedia) == true) goto L5;
    L9:
        return false;
    L5:
        if (r3.extended_media.size() != 1) goto L9;
        if (isExtendedVideo(r3.extended_media.get(0)) == false) goto L9;
        return true;
    }

    public static boolean isPhoto(TLRPC.Message r3) {
        if (r3 == null) goto L11;
        TL_iv.RichMessage r2 = r3.rich_message;
        if (r2 == null) goto L11;
        if (findPhoto(r2) == null) goto L9;
        return true;
    L9:
        return false;
    L11:
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L13;
        if (r3 == null) goto L25;
        TLRPC.MessageAction r22 = r3.action;
        if (r22 == null) goto L25;
        TLRPC.Photo r23 = r22.photo;
        if (r23 == null) goto L25;
        return r23 instanceof TLRPC.TL_photo;
    L25:
        TLRPC.MessageMedia r32 = getMedia(r3);
        if ((r32 instanceof TLRPC.TL_messageMediaPhoto) == true) goto L28;
    L30:
        return false;
    L28:
        if (((TLRPC.TL_messageMediaPhoto) r32).live_photo == true) goto L30;
        return true;
    L13:
        if ((getMedia(r3).webpage.photo instanceof TLRPC.TL_photo) == true) goto L15;
    L17:
        return false;
    L15:
        if ((getMedia(r3).webpage.document instanceof TLRPC.TL_document) == true) goto L17;
        return true;
    }

    public static boolean isPremiumEmojiPack(TLRPC.StickerSetCovered r3) {
        if (r3 == null) goto L10;
        TLRPC.StickerSet r1 = r3.set;
        if (r1 == null) goto L10;
        if (r1.emojis == true) goto L10;
        return false;
    L10:
        if ((r3 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L12;
        ArrayList<TLRPC.Document> r32 = ((TLRPC.TL_stickerSetFullCovered) r3).documents;
    L13:
        if (r32 == null) goto L22;
        int r12 = 0;
    L16:
        if (r12 >= r32.size()) goto L22;
        if (isFreeEmoji(r32.get(r12)) == false) goto L19;
        r12 = r12 + 1;
        goto L16
    L19:
        return true;
    L22:
        return false;
    L12:
        r32 = r3.covers;
        goto L13
    }

    public static boolean isPremiumEmojiPack(TLRPC.TL_messages_stickerSet r3) {
        if (r3 == null) goto L9;
        TLRPC.StickerSet r1 = r3.set;
        if (r1 == null) goto L9;
        if (r1.emojis == true) goto L9;
        return false;
    L9:
        if (r3 != null) goto L11;
    L20:
        return false;
    L11:
        if (r3.documents == null) goto L20;
        int r12 = 0;
    L14:
        if (r12 >= r3.documents.size()) goto L20;
        if (isFreeEmoji(r3.documents.get(r12)) == false) goto L17;
        r12 = r12 + 1;
        goto L14
    L17:
        return true;
    }

    public static boolean isPremiumSticker(TLRPC.Document r4) {
        if (r4 != null) goto L5;
    L15:
        return false;
    L5:
        if (r4.thumbs == null) goto L15;
        int r1 = 0;
    L9:
        if (r1 >= r4.video_thumbs.size()) goto L15;
        if ("f".equals(r4.video_thumbs.get(r1).type) == true) goto L12;
        r1 = r1 + 1;
        goto L9
    L12:
        return true;
    }

    public static boolean isQuickReply(TLRPC.Message r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if ((r2.flags & TLObject.FLAG_30) == 0) goto L6;
        return true;
    L6:
        if (r2.quick_reply_shortcut == null) goto L12;
        return true;
    L12:
        return false;
    }

    public static boolean isRoundVideoDocument(TLRPC.Document r7) {
        if (r7 != null) goto L5;
    L19:
        return false;
    L5:
        if ("video/mp4".equals(r7.mime_type) == false) goto L19;
        int r1 = 0;
        boolean r2 = false;
        int r3 = 0;
        int r4 = 0;
    L8:
        if (r1 >= r7.attributes.size()) goto L13;
        TLRPC.DocumentAttribute r5 = r7.attributes.get(r1);
        if ((r5 instanceof TLRPC.TL_documentAttributeVideo) == false) goto L12;
        r3 = r5.w;
        r4 = r5.h;
        r2 = r5.round_message;
    L12:
        r1 = r1 + 1;
        goto L8
    L13:
        if (r2 == false) goto L19;
        if (r3 > 1280) goto L19;
        if (r4 > 1280) goto L19;
        return true;
    }

    public static boolean isRoundVideoMessage(TLRPC.Message r1) {
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L9;
        if (getMedia(r1).webpage == null) goto L9;
        return isRoundVideoDocument(getMedia(r1).webpage.document);
    L9:
        if (getMedia(r1) != null) goto L11;
        return false;
    L11:
        if (isRoundVideoDocument(getMedia(r1).document) == false) goto L16;
        return true;
    L16:
        return false;
    }

    public static boolean isSecretMedia(TLRPC.Message r3) {
        if ((r3 instanceof TLRPC.TL_message_secret) == false) goto L15;
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaPhoto) == true) goto L11;
        if (isRoundVideoMessage(r3) == true) goto L11;
        if (isVideoMessage(r3) == true) goto L11;
    L13:
        return false;
    L11:
        if (getMedia(r3).ttl_seconds == 0) goto L13;
        return true;
    L15:
        if ((r3 instanceof TLRPC.TL_message) == true) goto L17;
    L23:
        return false;
    L17:
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaPhoto) == true) goto L21;
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaDocument) == false) goto L23;
    L21:
        if (getMedia(r3).ttl_seconds == 0) goto L23;
        return true;
    }

    public static boolean isSecretPhotoOrVideo(TLRPC.Message r3) {
        if ((r3 instanceof TLRPC.TL_message_secret) == false) goto L17;
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L7;
    L10:
        int r32 = r3.ttl;
        if (r32 > 0) goto L13;
    L15:
        return false;
    L13:
        if (r32 > 60) goto L15;
        return true;
    L7:
        if (isRoundVideoMessage(r3) == true) goto L10;
        if (isVideoMessage(r3) == false) goto L15;
    L17:
        if ((r3 instanceof TLRPC.TL_message) == true) goto L19;
    L25:
        return false;
    L19:
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaPhoto) == true) goto L23;
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaDocument) == false) goto L25;
    L23:
        if (getMedia(r3).ttl_seconds == 0) goto L25;
        return true;
    }

    public static boolean isStaticStickerDocument(TLRPC.Document r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.mime_type.equals("image/webp") == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isStickerDocument(TLRPC.Document r3) {
        if (r3 == null) goto L18;
        int r1 = 0;
    L6:
        if (r1 >= r3.attributes.size()) goto L18;
        if ((r3.attributes.get(r1) instanceof TLRPC.TL_documentAttributeSticker) == true) goto L10;
        r1 = r1 + 1;
        goto L6
    L10:
        if ("image/webp".equals(r3.mime_type) == false) goto L12;
        return true;
    L12:
        if ("video/webm".equals(r3.mime_type) == true) goto L21;
        return false;
    L21:
        return true;
    L18:
        return false;
    }

    public static boolean isStickerHasSet(TLRPC.Document r4) {
        if (r4 == null) goto L16;
        int r1 = 0;
    L6:
        if (r1 >= r4.attributes.size()) goto L16;
        TLRPC.DocumentAttribute r2 = r4.attributes.get(r1);
        if ((r2 instanceof TLRPC.TL_documentAttributeSticker) == false) goto L15;
        TLRPC.InputStickerSet r22 = r2.stickerset;
        if (r22 == null) goto L15;
        if ((r22 instanceof TLRPC.TL_inputStickerSetEmpty) == true) goto L15;
        return true;
    L15:
        r1 = r1 + 1;
    L16:
        return false;
    }

    public static boolean isStickerMessage(TLRPC.Message r1) {
        if (getMedia(r1) != null) goto L5;
        return false;
    L5:
        if (isStickerDocument(getMedia(r1).document) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isSystemSignUp(MessageObject r1) {
        if (r1 == null) goto L9;
        TLRPC.Message r12 = r1.messageOwner;
        if ((r12 instanceof TLRPC.TL_messageService) == true) goto L6;
        return false;
    L6:
        if ((((TLRPC.TL_messageService) r12).action instanceof TLRPC.TL_messageActionContactSignUp) == false) goto L12;
        return true;
    L12:
        return false;
    L9:
        return false;
    }

    public static boolean isTextColorEmoji(TLRPC.Document r5) {
        if (r5 != null) goto L5;
        return false;
    L5:
        int r1 = r5.attributes.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        TLRPC.DocumentAttribute r3 = r5.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L10;
        r2 = r2 + 1;
        goto L6
    L10:
        return ((TLRPC.TL_documentAttributeCustomEmoji) r3).text_color;
    L12:
        return false;
    }

    public static boolean isTextColorSet(TLRPC.TL_messages_stickerSet r2) {
        if (r2 == null) goto L18;
        TLRPC.StickerSet r1 = r2.set;
        if (r1 == null) goto L18;
        if (r1.text_color == false) goto L11;
        return true;
    L11:
        ArrayList<TLRPC.Document> r12 = r2.documents;
        if (r12 == null) goto L18;
        if (r12.isEmpty() == true) goto L18;
        return isTextColorEmoji(r2.documents.get(0));
    L18:
        return false;
    }

    public static boolean isTopicActionMessage(MessageObject r2) {
        if (r2 == null) goto L15;
        TLRPC.Message r22 = r2.messageOwner;
        if (r22 == null) goto L15;
        TLRPC.MessageAction r23 = r22.action;
        if ((r23 instanceof TLRPC.TL_messageActionTopicCreate) == false) goto L10;
        return true;
    L10:
        if ((r23 instanceof TLRPC.TL_messageActionTopicEdit) == true) goto L16;
        return false;
    L16:
        return true;
    L15:
        return false;
    }

    public static boolean isUnread(TLRPC.Message r0) {
        return r0.unread;
    }

    public static boolean isV(String r1) {
        if (r1 != null) goto L6;
        return true;
    L6:
        switch(r1.toLowerCase().hashCode()) {
            case -1535907675: goto L9;
            case -1422950858: goto L9;
            case -1253501876: goto L9;
            case -907685685: goto L9;
            case -788047292: goto L9;
            case -338481545: goto L9;
            case 3106: goto L9;
            case 3184: goto L9;
            case 3215: goto L9;
            case 3401: goto L9;
            case 3479: goto L9;
            case 3494: goto L9;
            case 3580: goto L9;
            case 3581: goto L9;
            case 3593: goto L9;
            case 3632: goto L9;
            case 3669: goto L9;
            case 3756: goto L9;
            case 3804: goto L9;
            case 96400: goto L9;
            case 96586: goto L9;
            case 96796: goto L9;
            case 96801: goto L9;
            case 96894: goto L9;
            case 97013: goto L9;
            case 97300: goto L9;
            case 97301: goto L9;
            case 97543: goto L9;
            case 98437: goto L9;
            case 98472: goto L9;
            case 98618: goto L9;
            case 98689: goto L9;
            case 98719: goto L9;
            case 98789: goto L9;
            case 98808: goto L9;
            case 98819: goto L9;
            case 99338: goto L9;
            case 99351: goto L9;
            case 99548: goto L9;
            case 99556: goto L9;
            case 99582: goto L9;
            case 99640: goto L9;
            case 99752: goto L9;
            case 100208: goto L9;
            case 100511: goto L9;
            case 100542: goto L9;
            case 100730: goto L9;
            case 100882: goto L9;
            case 100958: goto L9;
            case 101460: goto L9;
            case 101671: goto L9;
            case 101854: goto L9;
            case 102556: goto L9;
            case 102572: goto L9;
            case 103404: goto L9;
            case 103438: goto L9;
            case 103637: goto L9;
            case 103649: goto L9;
            case 104074: goto L9;
            case 104269: goto L9;
            case 104417: goto L9;
            case 104430: goto L9;
            case 104435: goto L9;
            case 104474: goto L9;
            case 104479: goto L9;
            case 104582: goto L9;
            case 104587: goto L9;
            case 104987: goto L9;
            case 105532: goto L9;
            case 105543: goto L9;
            case 105551: goto L9;
            case 106202: goto L9;
            case 106496: goto L9;
            case 107141: goto L9;
            case 107305: goto L9;
            case 107932: goto L9;
            case 107988: goto L9;
            case 107989: goto L9;
            case 108341: goto L9;
            case 108382: goto L9;
            case 108413: goto L9;
            case 108419: goto L9;
            case 108426: goto L9;
            case 108430: goto L9;
            case 108570: goto L9;
            case 109824: goto L9;
            case 109860: goto L9;
            case 110754: goto L9;
            case 110801: goto L9;
            case 110834: goto L9;
            case 110883: goto L9;
            case 110968: goto L9;
            case 110989: goto L9;
            case 111052: goto L9;
            case 111220: goto L9;
            case 111265: goto L9;
            case 111269: goto L9;
            case 111390: goto L9;
            case 111420: goto L9;
            case 111482: goto L9;
            case 111494: goto L9;
            case 112185: goto L9;
            case 112712: goto L9;
            case 112788: goto L9;
            case 112862: goto L9;
            case 113115: goto L9;
            case 113132: goto L9;
            case 113291: goto L9;
            case 113698: goto L9;
            case 113700: goto L9;
            case 113837: goto L9;
            case 113854: goto L9;
            case 114101: goto L9;
            case 114130: goto L9;
            case 114276: goto L9;
            case 114381: goto L9;
            case 114809: goto L9;
            case 114922: goto L9;
            case 114970: goto L9;
            case 115161: goto L9;
            case 115312: goto L9;
            case 115639: goto L9;
            case 116079: goto L9;
            case 116537: goto L9;
            case 116551: goto L9;
            case 116609: goto L9;
            case 117218: goto L9;
            case 117537: goto L9;
            case 117840: goto L9;
            case 117938: goto L9;
            case 118023: goto L9;
            case 118026: goto L9;
            case 118028: goto L9;
            case 118439: goto L9;
            case 118783: goto L9;
            case 118807: goto L9;
            case 118939: goto L9;
            case 120703: goto L9;
            case 3003834: goto L9;
            case 3016404: goto L9;
            case 3088960: goto L9;
            case 3213227: goto L9;
            case 3271912: goto L9;
            case 3358271: goto L9;
            case 3444044: goto L9;
            case 3446979: goto L9;
            case 3447940: goto L9;
            case 3524225: goto L9;
            case 3524692: goto L9;
            case 3526257: goto L9;
            case 3682393: goto L9;
            case 35379135: goto L9;
            case 114035747: goto L9;
            default: goto L7;
        };
    L7:
        return false;
    L9:
        return true;
    }

    public static boolean isVideoDocument(TLRPC.Document r10) {
        if (r10 != null) goto L5;
        return false;
    L5:
        String r1 = null;
        int r2 = 0;
        boolean r3 = false;
        int r4 = 0;
        int r5 = 0;
        boolean r6 = false;
    L7:
        if (r2 >= r10.attributes.size()) goto L21;
        TLRPC.DocumentAttribute r7 = r10.attributes.get(r2);
        if ((r7 instanceof TLRPC.TL_documentAttributeVideo) == false) goto L15;
        if (r7.round_message == true) goto L12;
        r4 = r7.w;
        r5 = r7.h;
        r6 = true;
    L20:
        r2 = r2 + 1;
        goto L7
    L12:
        return false;
    L15:
        if ((r7 instanceof TLRPC.TL_documentAttributeAnimated) == false) goto L18;
        r3 = true;
        goto L20
    L18:
        if ((r7 instanceof TLRPC.TL_documentAttributeFilename) == false) goto L20;
        r1 = r7.file_name;
        goto L20
    L21:
        if (r1 == null) goto L27;
        int r22 = r1.lastIndexOf(".");
        if (r22 < 0) goto L27;
        if (isV(r1.substring(r22 + 1)) == false) goto L27;
        return false;
    L27:
        if (r3 == false) goto L33;
        if (r4 > 1280) goto L31;
        if (r5 <= 1280) goto L33;
    L31:
        r3 = false;
    L33:
        if (SharedConfig.streamMkv == false) goto L38;
        if (r6 == true) goto L38;
        if ("video/x-matroska".equals(r10.mime_type) == false) goto L38;
        r6 = true;
    L38:
        if (r6 == false) goto L41;
        if (r3 == true) goto L41;
        return true;
    L41:
        return false;
    }

    public static boolean isVideoMessage(TLRPC.Message r2) {
        if (r2 == null) goto L8;
        TL_iv.RichMessage r0 = r2.rich_message;
        if (r0 == null) goto L8;
        return isVideoDocument(findVideo(r0));
    L8:
        if (getMedia(r2) == null) goto L13;
        if (isVideoSticker(getMedia(r2).document) == false) goto L13;
        return false;
    L13:
        if ((getMedia(r2) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L17;
        return isVideoDocument(getMedia(r2).webpage.document);
    L17:
        if (getMedia(r2) != null) goto L19;
    L22:
        return false;
    L19:
        if (isVideoDocument(getMedia(r2).document) == false) goto L22;
        return true;
    }

    public static boolean isVideoSticker(TLRPC.Document r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (isVideoStickerDocument(r0) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isVideoStickerDocument(TLRPC.Document r4) {
        if (r4 == null) goto L15;
        int r1 = 0;
    L6:
        if (r1 >= r4.attributes.size()) goto L15;
        TLRPC.DocumentAttribute r2 = r4.attributes.get(r1);
        if ((r2 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L14;
        if ((r2 instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L14;
        r1 = r1 + 1;
    L14:
        return "video/webm".equals(r4.mime_type);
    L15:
        return false;
    }

    public static boolean isVideoWebDocument(WebFile r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.mime_type.startsWith("video/") == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isVoiceDocument(TLRPC.Document r4) {
        if (r4 == null) goto L12;
        int r1 = 0;
    L6:
        if (r1 >= r4.attributes.size()) goto L12;
        TLRPC.DocumentAttribute r2 = r4.attributes.get(r1);
        if ((r2 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L10;
        r1 = r1 + 1;
        goto L6
    L10:
        return r2.voice;
    L12:
        return false;
    }

    public static boolean isVoiceMessage(TLRPC.Message r1) {
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L7;
        return isVoiceDocument(getMedia(r1).webpage.document);
    L7:
        if (getMedia(r1) != null) goto L9;
        return false;
    L9:
        if (isVoiceDocument(getMedia(r1).document) == false) goto L14;
        return true;
    L14:
        return false;
    }

    public static boolean isVoiceWebDocument(WebFile r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.mime_type.equals("audio/ogg") == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isVoteResultsIsNotEmpty(TLRPC.TL_messageMediaPoll r4) {
        if (r4 == null) goto L16;
        TLRPC.PollResults r1 = r4.results;
        if (r1 == null) goto L16;
        if (r1.results.isEmpty() == true) goto L16;
        int r12 = r4.results.results.size();
        int r2 = 0;
    L10:
        if (r2 >= r12) goto L16;
        if (r4.results.results.get(r2).voters > 0) goto L13;
        r2 = r2 + 1;
        goto L10
    L13:
        return true;
    L16:
        return false;
    }

    public static boolean isVoted(TLRPC.TL_messageMediaPoll r4) {
        if (r4 == null) goto L16;
        TLRPC.PollResults r1 = r4.results;
        if (r1 == null) goto L16;
        if (r1.results.isEmpty() == true) goto L16;
        int r12 = r4.results.results.size();
        int r2 = 0;
    L10:
        if (r2 >= r12) goto L16;
        if (r4.results.results.get(r2).chosen == true) goto L13;
        r2 = r2 + 1;
        goto L10
    L13:
        return true;
    L16:
        return false;
    }

    public static boolean isVotedButResultsHiddenUntilClose(TLRPC.TL_messageMediaPoll r2) {
        if (r2 == null) goto L13;
        TLRPC.Poll r0 = r2.poll;
        if (r0.closed == false) goto L6;
        return false;
    L6:
        if (r0.hide_results_until_close == true) goto L8;
        return false;
    L8:
        if (isVoted(r2) == true) goto L10;
        return false;
    L10:
        if (isVoteResultsIsNotEmpty(r2) == true) goto L18;
        return true;
    L18:
        return false;
    L13:
        return false;
    }

    public static boolean isWebM(TLRPC.Document r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if ("video/webm".equals(r1.mime_type) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isWelcomeAnchored(TLRPC.Message r1) {
        if (isEphemeral(r1) == true) goto L5;
        return false;
    L5:
        if (r1.ephemeralAnchorMsgId == 0) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isWelcomeMessage(TLRPC.Message r4) {
        if (isEphemeral(r4) == true) goto L5;
        return false;
    L5:
        if (r4.ephemeralReceiverBotId != (-1)) goto L7;
        return true;
    L7:
        if (r4.ephemeralAnchorMsgId == 0) goto L13;
        return true;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$loadAnimatedEmojiDocument$0(TLRPC.Document r2) {
        this.emojiAnimatedSticker = r2;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.animatedEmojiDocumentLoaded, new Object[]{this});
    }

    private /* synthetic */ void lambda$loadAnimatedEmojiDocument$1(TLRPC.Document r2) {
        AndroidUtilities.runOnUIThread(new MessageObject$$ExternalSyntheticLambda0(this, r2));
    }

    public static StaticLayout makeStaticLayout(CharSequence r7, TextPaint r8, int r9, float r10, float r11, boolean r12) {
        return makeStaticLayout(r7, r8, r9, r10, r11, r12, Layout.Alignment.ALIGN_NORMAL);
    }

    public static StaticLayout makeStaticLayout(CharSequence r6, TextPaint r7, int r8, float r9, float r10, boolean r11, Layout.Alignment r12) {
        if (r8 > 0) goto L5;
        r8 = 1;
    L5:
        StaticLayout.Builder r0 = StaticLayout.Builder.obtain(r6, 0, r6.length(), r7, r8).setLineSpacing(r10, r9).setBreakStrategy(1).setHyphenationFrequency(0).setAlignment(r12);
        if (r11 == false) goto L9;
        r0.setIncludePad(false);
    L9:
        if (Build.VERSION.SDK_INT < 28) goto L11;
        r0.setUseLineSpacingFromFallbacks(false);
    L11:
        StaticLayout r02 = r0.build();
        int r1 = 0;
    L13:
        if (r1 >= r02.getLineCount()) goto L25;
        if (r02.getLineRight(r1) > r8) goto L16;
        r1 = r1 + 1;
        goto L13
    L16:
        StaticLayout.Builder r62 = StaticLayout.Builder.obtain(r6, 0, r6.length(), r7, r8).setLineSpacing(r10, r9).setBreakStrategy(0).setHyphenationFrequency(0).setAlignment(r12);
        if (r11 == false) goto L20;
        r62.setIncludePad(false);
    L20:
        if (Build.VERSION.SDK_INT < 28) goto L23;
        r62.setUseLineSpacingFromFallbacks(false);
    L23:
        return r62.build();
    L25:
        return r02;
    }

    private boolean needDrawAvatarInternal() {
        if (isSponsored() == false) goto L9;
        if (this.searchType != 4) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (this.isRepostPreview == false) goto L12;
        return true;
    L12:
        if (this.isSaved == false) goto L15;
        return true;
    L15:
        if (this.forceAvatar == false) goto L17;
    L58:
        return true;
    L17:
        if (this.customAvatarDrawable != null) goto L58;
        TLRPC.Message r0 = this.messageOwner;
        if (r0 == null) goto L24;
        if (r0.guestchat_via_from != null) goto L58;
    L24:
        if (this.searchType == 0) goto L27;
        return true;
    L27:
        if (getDialogId() >= 0) goto L39;
        TLRPC.Chat r02 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-getDialogId()));
        if (isEphemeral() == true) goto L31;
    L33:
        if (r02 != null) goto L35;
    L37:
        boolean r03 = false;
    L42:
        if (isSponsored() == false) goto L44;
    L57:
        return false;
    L44:
        if (isFromChat() == false) goto L48;
        if (isFromUser() == false) goto L48;
    L56:
        return true;
    L48:
        if (isFromGroup() == true) goto L56;
        if (r03 == true) goto L56;
        if (this.eventId != 0) goto L56;
        TLRPC.MessageFwdHeader r9 = this.messageOwner.fwd_from;
        if (r9 == null) goto L57;
        if (r9.saved_from_peer == null) goto L57;
    L35:
        if (r02.signature_profiles == false) goto L37;
    L36:
        r03 = true;
        goto L42
    L31:
        if (ChatObject.isChannelAndNotMegaGroup(r02) == false) goto L33;
        return false;
    L39:
        if (getDialogId() != UserObject.VERIFY) goto L37;
        goto L37
    }

    public static void normalizeFlags(TLRPC.Message r2) {
        TLRPC.Peer r0 = r2.from_id;
        if (r0 != null) goto L5;
        r2.flags &= -257;
    L5:
        if (r0 != null) goto L8;
        r2.flags &= -5;
    L8:
        if (r2.reply_to != null) goto L11;
        r2.flags &= -9;
    L11:
        if (r2.media != null) goto L14;
        r2.flags &= -513;
    L14:
        if (r2.reply_markup != null) goto L17;
        r2.flags &= -65;
    L17:
        if (r2.replies != null) goto L20;
        r2.flags &= -8388609;
    L20:
        if (r2.reactions != null) goto L23;
        r2.flags &= -1048577;
        return;
    }

    public static CharSequence peerNameWithIcon(int r1, long r2) {
        return peerNameWithIcon(r1, r2, false);
    }

    public static CharSequence peerNameWithIcon(int r2, long r3, boolean r5) {
        if (r3 < 0) goto L8;
        TLRPC.User r22 = MessagesController.getInstance(r2).getUser(Long.valueOf(r3));
        if (r22 != null) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return AndroidUtilities.removeDiacritics(UserObject.getUserName(r22));
    L8:
        TLRPC.Chat r23 = MessagesController.getInstance(r2).getChat(Long.valueOf(-r3));
        if (r23 != null) goto L11;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        if (ChatObject.isChannelAndNotMegaGroup(r23) == false) goto L13;
        CharSequence r4 = channelSpan();
    L15:
        return new SpannableStringBuilder(r4).append(" ").append(AndroidUtilities.removeDiacritics(r23.title));
    L13:
        r4 = groupSpan();
        goto L15
    }

    public static CharSequence peerNameWithIcon(int r1, TLRPC.Peer r2) {
        return peerNameWithIcon(r1, r2, !(r2 instanceof TLRPC.TL_peerUser));
    }

    public static CharSequence peerNameWithIcon(int r4, TLRPC.Peer r5, boolean r6) {
        if ((r5 instanceof TLRPC.TL_peerUser) == false) goto L12;
        TLRPC.User r42 = MessagesController.getInstance(r4).getUser(Long.valueOf(r5.user_id));
        if (r42 == null) goto L37;
        if (r6 == false) goto L10;
        return new SpannableStringBuilder(userSpan()).append(" ").append(UserObject.getUserName(r42));
    L10:
        return UserObject.getUserName(r42);
    L37:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        if ((r5 instanceof TLRPC.TL_peerChat) == false) goto L25;
        TLRPC.Chat r43 = MessagesController.getInstance(r4).getChat(Long.valueOf(r5.chat_id));
        if (r43 == null) goto L39;
        if (r6 == false) goto L23;
        if (ChatObject.isChannelAndNotMegaGroup(r43) == false) goto L19;
        CharSequence r62 = channelSpan();
    L21:
        return new SpannableStringBuilder(r62).append(" ").append(r43.title);
    L19:
        r62 = groupSpan();
        goto L21
    L23:
        return r43.title;
    L39:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L25:
        if ((r5 instanceof TLRPC.TL_peerChannel) == false) goto L40;
        TLRPC.Chat r44 = MessagesController.getInstance(r4).getChat(Long.valueOf(r5.channel_id));
        if (r44 == null) goto L41;
        if (r6 == false) goto L36;
        if (ChatObject.isChannelAndNotMegaGroup(r44) == false) goto L32;
        CharSequence r63 = channelSpan();
    L34:
        return new SpannableStringBuilder(r63).append(" ").append(r44.title);
    L32:
        r63 = groupSpan();
        goto L34
    L36:
        return r44.title;
    L41:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L40:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static boolean peersEqual(TLRPC.Chat r4, TLRPC.Peer r5) {
        if (r4 != null) goto L7;
        if (r5 != null) goto L7;
        return true;
    L7:
        if (r4 == null) goto L25;
        if (r5 == null) goto L25;
        if (ChatObject.isChannel(r4) == false) goto L19;
        if ((r5 instanceof TLRPC.TL_peerChannel) == false) goto L19;
        if (r4.id != r5.channel_id) goto L17;
        return true;
    L17:
        return false;
    L19:
        if (ChatObject.isChannel(r4) == true) goto L25;
        if ((r5 instanceof TLRPC.TL_peerChat) == false) goto L25;
        if (r4.id != r5.chat_id) goto L25;
        return true;
    L25:
        return false;
    }

    public static boolean peersEqual(TLRPC.InputPeer r4, TLRPC.InputPeer r5) {
        if (r4 != null) goto L7;
        if (r5 != null) goto L7;
        return true;
    L7:
        if (r4 == null) goto L39;
        if (r5 == null) goto L39;
        if ((r4 instanceof TLRPC.TL_inputPeerChat) == false) goto L19;
        if ((r5 instanceof TLRPC.TL_inputPeerChat) == false) goto L19;
        if (r4.chat_id != r5.chat_id) goto L17;
        return true;
    L17:
        return false;
    L19:
        if ((r4 instanceof TLRPC.TL_inputPeerChannel) == false) goto L27;
        if ((r5 instanceof TLRPC.TL_inputPeerChannel) == false) goto L27;
        if (r4.channel_id != r5.channel_id) goto L25;
        return true;
    L25:
        return false;
    L27:
        if ((r4 instanceof TLRPC.TL_inputPeerUser) == false) goto L35;
        if ((r5 instanceof TLRPC.TL_inputPeerUser) == false) goto L35;
        if (r4.user_id != r5.user_id) goto L33;
        return true;
    L33:
        return false;
    L35:
        if ((r4 instanceof TLRPC.TL_inputPeerSelf) == false) goto L39;
        if ((r5 instanceof TLRPC.TL_inputPeerSelf) == false) goto L39;
        return true;
    L39:
        return false;
    }

    public static boolean peersEqual(TLRPC.InputPeer r4, TLRPC.Peer r5) {
        if (r4 != null) goto L7;
        if (r5 != null) goto L7;
        return true;
    L7:
        if (r4 == null) goto L33;
        if (r5 == null) goto L33;
        if ((r4 instanceof TLRPC.TL_inputPeerChat) == false) goto L19;
        if ((r5 instanceof TLRPC.TL_peerChat) == false) goto L19;
        if (r4.chat_id != r5.chat_id) goto L17;
        return true;
    L17:
        return false;
    L19:
        if ((r4 instanceof TLRPC.TL_inputPeerChannel) == false) goto L27;
        if ((r5 instanceof TLRPC.TL_peerChannel) == false) goto L27;
        if (r4.channel_id != r5.channel_id) goto L25;
        return true;
    L25:
        return false;
    L27:
        if ((r4 instanceof TLRPC.TL_inputPeerUser) == false) goto L33;
        if ((r5 instanceof TLRPC.TL_peerUser) == false) goto L33;
        if (r4.user_id != r5.user_id) goto L33;
        return true;
    L33:
        return false;
    }

    public static boolean peersEqual(TLRPC.Peer r4, TLRPC.Peer r5) {
        if (r4 != null) goto L7;
        if (r5 != null) goto L7;
        return true;
    L7:
        if (r4 == null) goto L33;
        if (r5 == null) goto L33;
        if ((r4 instanceof TLRPC.TL_peerChat) == false) goto L19;
        if ((r5 instanceof TLRPC.TL_peerChat) == false) goto L19;
        if (r4.chat_id != r5.chat_id) goto L17;
        return true;
    L17:
        return false;
    L19:
        if ((r4 instanceof TLRPC.TL_peerChannel) == false) goto L27;
        if ((r5 instanceof TLRPC.TL_peerChannel) == false) goto L27;
        if (r4.channel_id != r5.channel_id) goto L25;
        return true;
    L25:
        return false;
    L27:
        if ((r4 instanceof TLRPC.TL_peerUser) == false) goto L33;
        if ((r5 instanceof TLRPC.TL_peerUser) == false) goto L33;
        if (r4.user_id != r5.user_id) goto L33;
        return true;
    L33:
        return false;
    }

    public static TLRPC.TL_textWithEntities removeLinks(TLRPC.TL_textWithEntities r4) {
        TLRPC.TL_textWithEntities r0 = new TLRPC.TL_textWithEntities();
        r0.text = r4.text;
        int r1 = 0;
    L4:
        if (r1 >= r4.entities.size()) goto L11;
        TLRPC.MessageEntity r2 = r4.entities.get(r1);
        if ((r2 instanceof TLRPC.TL_messageEntityUrl) == true) goto L10;
        if ((r2 instanceof TLRPC.TL_messageEntityTextUrl) == true) goto L10;
        r0.entities.add(r2);
    L10:
        r1 = r1 + 1;
        goto L4
    L11:
        return r0;
    }

    public static Spannable replaceAnimatedEmoji(CharSequence r1, ArrayList<TLRPC.MessageEntity> r2, Paint.FontMetricsInt r3) {
        return replaceAnimatedEmoji(r1, r2, r3, false);
    }

    public static Spannable replaceAnimatedEmoji(CharSequence r6, ArrayList<TLRPC.MessageEntity> r7, Paint.FontMetricsInt r8, boolean r9) {
        return replaceAnimatedEmoji(r6, r7, r8, r9, 1.2f, 0);
    }

    public static Spannable replaceAnimatedEmoji(CharSequence r17, ArrayList<TLRPC.MessageEntity> r18, Paint.FontMetricsInt r19, boolean r20, float r21, int r22) {
        Emoji.EmojiSpan r4 = null;
        if (r17 != null) goto L6;
        return null;
    L6:
        if ((r17 instanceof Spannable) == false) goto L8;
        Spannable r0 = (Spannable) r17;
    L9:
        if (r18 != null) goto L12;
    L49:
        return r0;
    L12:
        if (SharedConfig.getDevicePerformanceClass() < 2) goto L14;
        int r5 = 100;
    L15:
        int r52 = r5 - r22;
        Emoji.EmojiSpan[] r6 = (Emoji.EmojiSpan[]) r0.getSpans(0, r0.length(), Emoji.EmojiSpan.class);
        int r7 = 0;
    L17:
        if (r7 >= r18.size()) goto L49;
        if (r52 <= 0) goto L49;
        TLRPC.MessageEntity r9 = r18.get(r7);
        if ((r9 instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L47;
        TLRPC.TL_messageEntityCustomEmoji r10 = (TLRPC.TL_messageEntityCustomEmoji) r9;
        int r11 = 0;
    L24:
        if (r11 >= r6.length) goto L32;
        Emoji.EmojiSpan r12 = r6[r11];
        if (r12 == null) goto L30;
        int r13 = r0.getSpanStart(r12);
        int r14 = r0.getSpanEnd(r12);
        int r15 = r10.offset;
        Emoji.EmojiSpan r16 = r4;
        if (AndroidUtilities.intersect1d(r15, r10.length + r15, r13, r14) == false) goto L31;
        r0.removeSpan(r12);
        r6[r11] = r16;
    L31:
        r11 = r11 + 1;
        r4 = r16;
        goto L24
    L30:
        r16 = r4;
        goto L31
    L32:
        Emoji.EmojiSpan r162 = r4;
        if ((r9.offset + r9.length) > r0.length()) goto L48;
        int r42 = r9.offset;
        AnimatedEmojiSpan[] r43 = (AnimatedEmojiSpan[]) r0.getSpans(r42, r9.length + r42, AnimatedEmojiSpan.class);
        if (r43 == null) goto L42;
        if (r43.length <= 0) goto L42;
        int r112 = r43.length;
        int r122 = 0;
    L39:
        if (r122 >= r112) goto L42;
        r0.removeSpan(r43[r122]);
        r122 = r122 + 1;
    L42:
        if (r10.document == null) goto L44;
        AnimatedEmojiSpan r44 = new AnimatedEmojiSpan(r10.document, r21, r19);
    L45:
        r44.local = r10.local;
        r44.top = r20;
        int r113 = r9.offset;
        r0.setSpan(r44, r113, r9.length + r113, 33);
        r52 = r52 - 1;
        goto L48
    L44:
        r44 = new AnimatedEmojiSpan(r10.document_id, r21, r19);
    L48:
        r7 = r7 + 1;
        r4 = r162;
        goto L17
    L47:
        r162 = r4;
        goto L48
    L14:
        r5 = 50;
        goto L15
    L8:
        r0 = new SpannableString(r17);
        goto L9
    }

    public static CharSequence replaceWithLink(CharSequence r2, String r3, CharSequence r4) {
        if (TextUtils.indexOf(r2, r3) >= 0) goto L5;
        return r2;
    L5:
        return TextUtils.replace(r2, new String[]{r3}, new CharSequence[]{r4});
    }

    public static CharSequence replaceWithLink(CharSequence r8, String r9, TLObject r10) {
        int r0 = TextUtils.indexOf(r8, r9);
        if (r0 < 0) goto L24;
        boolean r1 = r10 instanceof TLRPC.User;
        TLObject r3 = null;
        CharSequence r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 == false) goto L9;
        TLRPC.User r102 = (TLRPC.User) r10;
        String r12 = UserObject.getUserName(r102).replace('\n', ' ');
        String r103 = _UrlKt.FRAGMENT_ENCODE_SET + r102.id;
    L7:
        r4 = r12;
    L22:
        SpannableStringBuilder r13 = new SpannableStringBuilder(TextUtils.replace(r8, new String[]{r9}, new CharSequence[]{r4}));
        URLSpanNoUnderlineBold r82 = new URLSpanNoUnderlineBold(r103);
        r82.setObject(r3);
        r13.setSpan(r82, r0, r4.length() + r0, 33);
        return r13;
    L9:
        if ((r10 instanceof TLRPC.Chat) == false) goto L12;
        TLRPC.Chat r104 = (TLRPC.Chat) r10;
        r12 = r104.title.replace('\n', ' ');
        r103 = _UrlKt.FRAGMENT_ENCODE_SET + (-r104.id);
        goto L7
    L12:
        if ((r10 instanceof TLRPC.TL_game) == false) goto L15;
        r4 = ((TLRPC.TL_game) r10).title.replace('\n', ' ');
        r103 = "game";
        goto L22
    L15:
        if ((r10 instanceof TLRPC.TL_chatInviteExported) == false) goto L19;
        TLRPC.TL_chatInviteExported r105 = (TLRPC.TL_chatInviteExported) r10;
        r4 = r105.link.replace('\n', ' ');
        String r14 = "invite";
        TLObject r106 = r105;
    L17:
        r3 = r106;
        r103 = r14;
        goto L22
    L19:
        if ((r10 instanceof TLRPC.ForumTopic) == false) goto L21;
        r4 = ForumUtilities.getTopicSpannedName((TLRPC.ForumTopic) r10, null, false);
        r14 = "topic";
        r106 = r10;
        goto L17
    L21:
        r103 = MVEL.VERSION_SUB;
        goto L22
    L24:
        return r8;
    }

    public static void setUnreadFlags(TLRPC.Message r3, int r4) {
        boolean r1 = false;
        if ((r4 & 1) != 0) goto L5;
        boolean r0 = true;
    L6:
        r3.unread = r0;
        if ((r4 & 2) != 0) goto L9;
        r1 = true;
    L9:
        r3.media_unread = r1;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public static boolean shouldEncryptPhotoOrVideo(int r2, TLRPC.Message r3) {
        if (r3 == null) goto L14;
        if (r3.media == null) goto L14;
        if (isVoiceDocument(getDocument(r3)) == true) goto L11;
        if (isRoundVideoMessage(r3) == false) goto L14;
    L11:
        if (r3.media.ttl_seconds != Integer.MAX_VALUE) goto L14;
        return true;
    L14:
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L17;
        return true;
    L17:
        if ((r3 instanceof TLRPC.TL_message_secret) == false) goto L29;
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L21;
    L22:
        int r32 = r3.ttl;
        if (r32 > 0) goto L25;
    L27:
        return false;
    L25:
        if (r32 > 60) goto L27;
        return true;
    L21:
        if (isVideoMessage(r3) == false) goto L27;
    L29:
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaPhoto) == true) goto L33;
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaDocument) == true) goto L33;
    L35:
        return false;
    L33:
        if (getMedia(r3).ttl_seconds == 0) goto L35;
        return true;
    }

    public static SpannableStringBuilder span(String r3, int r4) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(r3);
        r0.setSpan(new ColoredImageSpan(r4), 0, r0.length(), 33);
        return r0;
    }

    public static void toggleTodo(int r2, long r3, TLRPC.TL_messageMediaToDo r5, int r6, boolean r7, int r8) {
        int r0 = 0;
    L4:
        if (r0 >= r5.completions.size()) goto L12;
        if (r5.completions.get(r0).id != r6) goto L11;
        r5.completions.remove(r0);
        if (r5.completions.isEmpty() == false) goto L10;
        r5.flags &= -2;
    L10:
        r0 = r0 - 1;
    L11:
        r0 = r0 + 1;
        goto L4
    L12:
        if (r7 == false) goto L18;
        TLRPC.TL_todoCompletion r72 = new TLRPC.TL_todoCompletion();
        r72.id = r6;
        r72.completed_by = MessagesController.getInstance(r2).getPeer(r3);
        r72.date = r8;
        r5.flags |= 1;
        r5.completions.add(r72);
        return;
    }

    private void updateMessageText(AbstractMap<Long, TLRPC.User> r32, AbstractMap<Long, TLRPC.Chat> r33, LongSparseArray<TLRPC.User> r34, LongSparseArray<TLRPC.Chat> r35) {
        TLRPC.Peer r3 = this.messageOwner.from_id;
        if ((r3 instanceof TLRPC.TL_peerUser) == false) goto L7;
        TLRPC.User r36 = getUser(r32, r34, r3.user_id);
    L5:
        TLRPC.Chat r6 = null;
    L10:
        if (r36 == null) goto L12;
        TLObject r8 = r36;
    L13:
        this.drawServiceWithDefaultTypeface = false;
        this.channelJoined = false;
        TLRPC.Message r10 = this.messageOwner;
        long r17 = 0;
        if ((r10 instanceof TLRPC.TL_messageService) == false) goto L1341;
        TLRPC.MessageAction r11 = r10.action;
        if (r11 != null) goto L18;
    L20:
        String r9 = _UrlKt.FRAGMENT_ENCODE_SET;
    L1538:
        if (this.messageText != null) goto L1540;
        this.messageText = r9;
    L1540:
        this.isEmbedVideoCached = null;
        this.cachedStartsTimestamp = null;
        this.cachedSavedTimestamp = null;
        return;
    L18:
        if ((r11 instanceof TLRPC.TL_messageActionNewCreatorPending) == false) goto L22;
        TLRPC.User r1 = getUser(r32, r34, ((TLRPC.TL_messageActionNewCreatorPending) r11).new_creator_id);
        String r2 = LocaleController.getString(R.string.ActionNewCreatorPending);
        this.messageText = r2;
        CharSequence r12 = replaceWithLink(r2, "un1", r1);
        this.messageText = r12;
        this.messageText = replaceWithLink(r12, "un2", r8);
        goto L20
    L22:
        if ((r11 instanceof TLRPC.TL_messageActionChangeCreator) == false) goto L25;
        TLRPC.User r13 = getUser(r32, r34, ((TLRPC.TL_messageActionChangeCreator) r11).new_creator_id);
        String r22 = LocaleController.getString(R.string.ActionChangeCreator);
        this.messageText = r22;
        CharSequence r23 = replaceWithLink(r22, "un1", r8);
        this.messageText = r23;
        this.messageText = replaceWithLink(r23, "un2", r13);
        goto L20
    L25:
        if ((r11 instanceof TLRPC.TL_messageActionManagedBotCreated) == false) goto L28;
        TLRPC.User r14 = getUser(r32, r34, ((TLRPC.TL_messageActionManagedBotCreated) r11).bot_id);
        TLRPC.User r24 = getUser(r32, r34, this.messageOwner.peer_id.user_id);
        String r37 = LocaleController.getString(R.string.ActionManagedBotCreated);
        this.messageText = r37;
        CharSequence r15 = replaceWithLink(r37, "un1", r14);
        this.messageText = r15;
        this.messageText = replaceWithLink(r15, "un2", r24);
        goto L20
    L28:
        if ((r11 instanceof TLRPC.TL_messageActionSetSameChatWallPaper) == false) goto L50;
        this.contentType = 1;
        this.type = 10;
        TLRPC.TL_messageActionSetSameChatWallPaper r112 = (TLRPC.TL_messageActionSetSameChatWallPaper) r11;
        if (isOutOwner() == true) goto L33;
        r17 = getDialogId();
    L33:
        TLRPC.User r16 = getUser(r32, r34, r17);
        ArrayList<TLRPC.PhotoSize> r25 = new ArrayList();
        this.photoThumbs = r25;
        TLRPC.Document r4 = r112.wallpaper.document;
        if (r4 == null) goto L36;
        r25.addAll(r4.thumbs);
        this.photoThumbsObject = r112.wallpaper.document;
    L36:
        if (r16 != null) goto L38;
        if (r6 != null) goto L43;
        if (r36 == null) goto L20;
        this.messageText = LocaleController.formatString(R.string.ActionSetWallpaperForThisGroupByUser, new Object[]{UserObject.getFirstName(r36)});
        goto L20
    L43:
        if (ChatObject.isChannelAndNotMegaGroup(r6) == false) goto L45;
        int r18 = R.string.ActionSetWallpaperForThisChannel;
    L46:
        this.messageText = LocaleController.getString(r18);
        goto L20
    L45:
        r18 = R.string.ActionSetWallpaperForThisGroup;
        goto L46
    L38:
        if (r16.id != UserConfig.getInstance(this.currentAccount).clientUserId) goto L40;
        this.messageText = LocaleController.formatString(R.string.ActionSetSameWallpaperForThisChatSelf, new Object[0]);
        goto L20
    L40:
        this.messageText = LocaleController.formatString(R.string.ActionSetSameWallpaperForThisChat, new Object[]{r16.first_name});
        goto L20
    L50:
        if ((r11 instanceof TLRPC.TL_messageActionSetChatWallPaper) == false) goto L87;
        this.contentType = 1;
        TLRPC.TL_messageActionSetChatWallPaper r113 = (TLRPC.TL_messageActionSetChatWallPaper) r11;
        this.type = 22;
        ArrayList<TLRPC.PhotoSize> r19 = new ArrayList();
        this.photoThumbs = r19;
        TLRPC.Document r26 = r113.wallpaper.document;
        if (r26 == null) goto L55;
        r19.addAll(r26.thumbs);
        this.photoThumbsObject = r113.wallpaper.document;
    L55:
        if (isOutOwner() == true) goto L58;
        r17 = getDialogId();
    L58:
        TLRPC.User r110 = getUser(r32, r34, r17);
        TLRPC.User r27 = getUser(r32, r34, getDialogId());
        if (r110 != null) goto L61;
        if (r6 != null) goto L80;
        if (r36 == null) goto L20;
        this.messageText = LocaleController.formatString(R.string.ActionSetWallpaperForThisGroupByUser, new Object[]{UserObject.getFirstName(r36)});
        goto L20
    L80:
        if (ChatObject.isChannelAndNotMegaGroup(r6) == false) goto L82;
        int r111 = R.string.ActionSetWallpaperForThisChannel;
    L83:
        this.messageText = LocaleController.getString(r111);
        goto L20
    L82:
        r111 = R.string.ActionSetWallpaperForThisGroup;
        goto L83
    L61:
        if (r110.id == UserConfig.getInstance(this.currentAccount).clientUserId) goto L63;
        SpannableString r28 = new SpannableString(UserObject.getFirstName(r110));
        r28.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r28.length(), 33);
        if (r113.same == false) goto L74;
        this.type = 10;
        this.messageText = LocaleController.getString(R.string.ActionSetSameWallpaperForThisChat);
    L77:
        this.messageText = AndroidUtilities.replaceCharSequence("%s", this.messageText, r28);
        goto L20
    L74:
        if (r113.for_both == false) goto L76;
        this.messageText = LocaleController.getString(R.string.ActionSetWallpaperForThisChatBoth);
        goto L77
    L76:
        this.messageText = LocaleController.getString(R.string.ActionSetWallpaperForThisChat);
        goto L77
    L63:
        if (r113.same == false) goto L66;
        this.type = 10;
        this.messageText = LocaleController.formatString(R.string.ActionSetSameWallpaperForThisChatSelf, new Object[0]);
        goto L20
    L66:
        if (r113.for_both == false) goto L69;
        if (r27 == null) goto L69;
        this.messageText = LocaleController.getString(R.string.ActionSetWallpaperForThisChatSelfBoth);
        SpannableString r114 = new SpannableString(UserObject.getFirstName(r27));
        r114.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r114.length(), 33);
        this.messageText = AndroidUtilities.replaceCharSequence("%s", this.messageText, r114);
    L69:
        this.messageText = LocaleController.getString(R.string.ActionSetWallpaperForThisChatSelf);
        goto L20
    L87:
        if ((r11 instanceof TLRPC.TL_messageActionGroupCallScheduled) == false) goto L96;
        TLRPC.TL_messageActionGroupCallScheduled r115 = (TLRPC.TL_messageActionGroupCallScheduled) r11;
        if ((r10.peer_id instanceof TLRPC.TL_peerChat) == true) goto L94;
        if (isSupergroup() == true) goto L94;
        this.messageText = LocaleController.formatString(R.string.ActionChannelCallScheduled, new Object[]{LocaleController.formatStartsTime(r115.schedule_date, 3, false)});
    L94:
        this.messageText = LocaleController.formatString(R.string.ActionGroupCallScheduled, new Object[]{LocaleController.formatStartsTime(r115.schedule_date, 3, false)});
        goto L20
    L96:
        if ((r11 instanceof TLRPC.TL_messageActionGroupCall) == false) goto L130;
        int r116 = r11.duration;
        if (r116 == 0) goto L120;
        int r29 = r116 / 86400;
        if (r29 <= 0) goto L102;
        String r117 = LocaleController.formatPluralString("Days", r29, new Object[0]);
    L110:
        if ((this.messageOwner.peer_id instanceof TLRPC.TL_peerChat) == true) goto L116;
        if (isSupergroup() == true) goto L116;
        this.messageText = LocaleController.formatString(R.string.ActionChannelCallEnded, new Object[]{r117});
    L116:
        if (isOut() == false) goto L118;
        this.messageText = LocaleController.formatString(R.string.ActionGroupCallEndedByYou, new Object[]{r117});
        goto L20
    L118:
        this.messageText = replaceWithLink(LocaleController.formatString(R.string.ActionGroupCallEndedBy, new Object[]{r117}), "un1", r8);
        goto L20
    L102:
        int r210 = r116 / 3600;
        if (r210 <= 0) goto L105;
        r117 = LocaleController.formatPluralString("Hours", r210, new Object[0]);
        goto L110
    L105:
        int r211 = r116 / 60;
        if (r211 <= 0) goto L108;
        r117 = LocaleController.formatPluralString("Minutes", r211, new Object[0]);
        goto L110
    L108:
        r117 = LocaleController.formatPluralString("Seconds", r116, new Object[0]);
        goto L110
    L120:
        if ((r10.peer_id instanceof TLRPC.TL_peerChat) == true) goto L126;
        if (isSupergroup() == true) goto L126;
        this.messageText = LocaleController.getString(R.string.ActionChannelCallJustStarted);
    L126:
        if (isOut() == false) goto L128;
        this.messageText = LocaleController.getString(R.string.ActionGroupCallStartedByYou);
        goto L20
    L128:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionGroupCallStarted), "un1", r8);
        goto L20
    L130:
        if ((r11 instanceof TLRPC.TL_messageActionInviteToGroupCall) == false) goto L150;
        long r118 = r11.user_id;
        if (r118 != 0) goto L137;
        if (r11.users.size() != 1) goto L137;
        r118 = this.messageOwner.action.users.get(0).longValue();
    L137:
        if (r118 == 0) goto L146;
        TLRPC.User r38 = getUser(r32, r34, r118);
        if (isOut() == false) goto L142;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionGroupCallYouInvited), "un2", r38);
        goto L20
    L142:
        if (r118 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L144;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionGroupCallInvitedYou), "un1", r8);
        goto L20
    L144:
        CharSequence r119 = replaceWithLink(LocaleController.getString(R.string.ActionGroupCallInvited), "un2", r38);
        this.messageText = r119;
        this.messageText = replaceWithLink(r119, "un1", r8);
        goto L20
    L146:
        if (isOut() == false) goto L148;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionGroupCallYouInvited), "un2", this.messageOwner.action.users, r32, r34);
        goto L20
    L148:
        CharSequence r120 = replaceWithLink(LocaleController.getString(R.string.ActionGroupCallInvited), "un2", this.messageOwner.action.users, r32, r34);
        this.messageText = r120;
        this.messageText = replaceWithLink(r120, "un1", r8);
        goto L20
    L150:
        if ((r11 instanceof TLRPC.TL_messageActionGeoProximityReached) == false) goto L167;
        TLRPC.TL_messageActionGeoProximityReached r1110 = (TLRPC.TL_messageActionGeoProximityReached) r11;
        TLRPC.Peer r39 = r1110.from_id;
        String r102 = _UrlKt.FRAGMENT_ENCODE_SET;
        long r132 = getPeerId(r39);
        if (r132 <= 0) goto L154;
        TLObject r310 = getUser(r32, r34, r132);
        String r252 = "un2";
    L155:
        long r82 = getPeerId(r1110.to_id);
        long r152 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        if (r82 != r152) goto L159;
        this.messageText = replaceWithLink(LocaleController.formatString(R.string.ActionUserWithinRadius, new Object[]{LocaleController.formatDistance(r1110.distance, 2)}), "un1", r310);
    L166:
        r9 = r102;
        goto L1538
    L159:
        if (r82 <= 0) goto L161;
        TLObject r121 = getUser(r32, r34, r82);
    L162:
        int r42 = r1110.distance;
        if (r132 != r152) goto L165;
        this.messageText = replaceWithLink(LocaleController.formatString(R.string.ActionUserWithinYouRadius, new Object[]{LocaleController.formatDistance(r42, 2)}), "un1", r121);
        goto L166
    L165:
        CharSequence r122 = replaceWithLink(LocaleController.formatString(R.string.ActionUserWithinOtherRadius, new Object[]{LocaleController.formatDistance(r42, 2)}), r252, r121);
        this.messageText = r122;
        this.messageText = replaceWithLink(r122, "un1", r310);
        goto L166
    L161:
        r121 = getChat(r33, r35, -r82);
        goto L162
    L154:
        r252 = "un2";
        r310 = getChat(r33, r35, -r132);
        goto L155
    L167:
        r102 = _UrlKt.FRAGMENT_ENCODE_SET;
        if ((r11 instanceof TLRPC.TL_messageActionCustomAction) == false) goto L171;
        this.messageText = r11.message;
        goto L166
    L171:
        if ((r11 instanceof TLRPC.TL_messageActionChatCreate) == false) goto L177;
        if (isOut() == false) goto L175;
        this.messageText = LocaleController.getString(R.string.ActionYouCreateGroup);
        goto L166
    L175:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionCreateGroup), "un1", r8);
        goto L166
    L177:
        if ((r11 instanceof TLRPC.TL_messageActionChatDeleteUser) == false) goto L194;
        if (isFromUser() == false) goto L186;
        TLRPC.Message r123 = this.messageOwner;
        if (r123.action.user_id != r123.from_id.user_id) goto L186;
        if (isOut() == false) goto L185;
        this.messageText = LocaleController.getString(R.string.ActionYouLeftUser);
        goto L166
    L185:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionLeftUser), "un1", r8);
    L186:
        TLRPC.User r124 = getUser(r32, r34, this.messageOwner.action.user_id);
        if (isOut() == false) goto L190;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionYouKickUser), "un2", r124);
        goto L166
    L190:
        if (this.messageOwner.action.user_id != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L192;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionKickUserYou), "un1", r8);
        goto L166
    L192:
        CharSequence r125 = replaceWithLink(LocaleController.getString(R.string.ActionKickUser), "un2", r124);
        this.messageText = r125;
        this.messageText = replaceWithLink(r125, "un1", r8);
        goto L166
    L194:
        if ((r11 instanceof TLRPC.TL_messageActionPaymentRefunded) == false) goto L202;
        TLRPC.TL_messageActionPaymentRefunded r1111 = (TLRPC.TL_messageActionPaymentRefunded) r11;
        long r83 = DialogObject.getPeerDialogId(r1111.peer);
        if (r83 < 0) goto L198;
        TLObject r126 = getUser(r32, r34, r83);
    L199:
        this.messageText = StarsIntroActivity.replaceStars(replaceWithLink(LocaleController.formatString(R.string.ActionRefunded, new Object[]{r1111.currency + " " + LocaleController.formatNumber(r1111.total_amount, ',')}), "un1", r126));
    L200:
        r9 = r102;
        goto L1538
    L198:
        r126 = getChat(r33, r35, -r83);
        goto L199
    L202:
        if (TlUtils.isInstance(r11, new Class[]{TLRPC.TL_messageActionSuggestedPostRefund.class, TLRPC.TL_messageActionSuggestedPostSuccess.class}) == false) goto L242;
        String r311 = ForumUtilities.getMonoForumTitle(this.currentAccount, DialogObject.getPeerDialogId(this.messageOwner.peer_id), true);
        if (r311 != null) goto L213;
        TLRPC.Chat r312 = getChat(r33, r35, -DialogObject.getPeerDialogId(this.messageOwner.peer_id));
        if (r312 == null) goto L212;
        long r62 = r312.linked_monoforum_id;
        if (r62 == 0) goto L212;
        TLRPC.Chat r127 = getChat(r33, r35, r62);
        if (r127 == null) goto L212;
        r312 = r127;
    L212:
        r311 = DialogObject.getDialogTitle(r312);
    L213:
        String r128 = DialogObject.getName(getUser(r32, r34, DialogObject.getPeerDialogId(this.messageOwner.saved_peer_id)));
        MessageSuggestionParams r212 = obtainSuggestionOfferFromReply();
        TLRPC.MessageAction r43 = this.messageOwner.action;
        if ((r43 instanceof TLRPC.TL_messageActionSuggestedPostRefund) == false) goto L232;
        boolean r44 = ((TLRPC.TL_messageActionSuggestedPostRefund) r43).payer_initiated;
        if (r212 == null) goto L227;
        AmountUtils$Amount r213 = r212.amount;
        if (r213 == null) goto L227;
        if (r44 == false) goto L221;
        int r45 = R.string.SuggestedOfferRefundByUserAmountF;
    L223:
        if (r213.currency != AmountUtils$Currency.TON) goto L225;
        boolean r92 = true;
    L226:
        this.messageText = StarsIntroActivity.replaceStars(r92, LocaleController.formatString(r45, new Object[]{r128, r311, r213.asDecimalString()}));
        goto L200
    L225:
        r92 = false;
        goto L226
    L221:
        r45 = R.string.SuggestedOfferRefundByAdminAmountF;
    L227:
        if (r44 == false) goto L229;
        int r214 = R.string.SuggestedOfferRefundByUserAmountUnknown;
    L230:
        this.messageText = LocaleController.formatString(r214, new Object[]{r128, r311});
        goto L200
    L229:
        r214 = R.string.SuggestedOfferRefundByAdminAmountUnknown;
        goto L230
    L232:
        if ((r43 instanceof TLRPC.TL_messageActionSuggestedPostSuccess) == false) goto L200;
        if (r212 == null) goto L241;
        AmountUtils$Amount r129 = r212.amount;
        if (r129 == null) goto L241;
        if (r129.currency != AmountUtils$Currency.TON) goto L239;
        boolean r93 = true;
    L240:
        this.messageText = StarsIntroActivity.replaceStars(r93, LocaleController.formatString(R.string.SuggestedOfferCompleteAmountF, new Object[]{r311, r129.asDecimalString()}));
        goto L200
    L239:
        r93 = false;
    L241:
        this.messageText = LocaleController.formatString(R.string.SuggestedOfferCompleteAmountUnknown, new Object[]{r311});
        goto L200
    L242:
        TLRPC.Message r103 = this.messageOwner;
        TLRPC.MessageAction r1112 = r103.action;
        if ((r1112 instanceof TLRPC.TL_messageActionChatAddUser) == false) goto L293;
        long r1210 = r1112.user_id;
        if (r1210 != 0) goto L250;
        if (r1112.users.size() != 1) goto L250;
        r1210 = this.messageOwner.action.users.get(0).longValue();
    L250:
        if (r1210 == 0) goto L289;
        TLRPC.User r313 = getUser(r32, r34, r1210);
        long r46 = this.messageOwner.peer_id.channel_id;
        if (r46 == 0) goto L254;
        TLRPC.Chat r130 = getChat(r33, r35, r46);
    L255:
        TLRPC.Peer r215 = this.messageOwner.from_id;
        if (r215 == null) goto L275;
        if (r1210 != r215.user_id) goto L275;
        if (ChatObject.isChannel(r130) == false) goto L265;
        if (r130.megagroup == true) goto L265;
        this.channelJoined = true;
        this.messageText = LocaleController.getString(R.string.ChannelJoined);
    L265:
        if (this.messageOwner.peer_id.channel_id == 0) goto L271;
        if (r1210 != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L269;
        this.messageText = LocaleController.getString(R.string.ChannelMegaJoined);
        goto L200
    L269:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionAddUserSelfMega), "un1", r8);
        goto L200
    L271:
        if (isOut() == false) goto L273;
        this.messageText = LocaleController.getString(R.string.ActionAddUserSelfYou);
        goto L200
    L273:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionAddUserSelf), "un1", r8);
    L275:
        if (isOut() == false) goto L278;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionYouAddUser), "un2", r313);
        goto L200
    L278:
        if (r1210 == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L280;
        CharSequence r131 = replaceWithLink(LocaleController.getString(R.string.ActionAddUser), "un2", r313);
        this.messageText = r131;
        this.messageText = replaceWithLink(r131, "un1", r8);
        goto L200
    L280:
        if (this.messageOwner.peer_id.channel_id == 0) goto L286;
        if (r130 != null) goto L283;
    L285:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ChannelAddedBy), "un1", r8);
        goto L200
    L283:
        if (r130.megagroup == false) goto L285;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.MegaAddedBy), "un1", r8);
        goto L200
    L286:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionAddUserYou), "un1", r8);
        goto L200
    L254:
        r130 = null;
        goto L255
    L289:
        if (isOut() == false) goto L291;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionYouAddUser), "un2", this.messageOwner.action.users, r32, r34);
        goto L200
    L291:
        CharSequence r133 = replaceWithLink(LocaleController.getString(R.string.ActionAddUser), "un2", this.messageOwner.action.users, r32, r34);
        this.messageText = r133;
        this.messageText = replaceWithLink(r133, "un1", r8);
        goto L200
    L293:
        if ((r1112 instanceof TLRPC.TL_messageActionChatJoinedViaCommunity) == false) goto L302;
        TLRPC.Chat r314 = getChat(r33, r35, ((TLRPC.TL_messageActionChatJoinedViaCommunity) r1112).community_id);
        boolean r134 = ChatObject.isChannelAndNotMegaGroup(getChat(r33, r35, -DialogObject.getPeerDialogId(this.messageOwner.peer_id)));
        if (isOut() == false) goto L300;
        if (r134 == false) goto L298;
        int r135 = R.string.ActionJoinedFromCommunityYouChannel;
    L299:
        this.messageText = replaceWithLink(LocaleController.getString(r135), "un1", r314);
        goto L200
    L298:
        r135 = R.string.ActionJoinedFromCommunityYou;
        goto L299
    L300:
        CharSequence r136 = replaceWithLink(LocaleController.getString(R.string.ActionJoinedFromCommunityUser), "un1", r314);
        this.messageText = r136;
        this.messageText = replaceWithLink(r136, "un2", r8);
        goto L200
    L302:
        if ((r1112 instanceof TLRPC.TL_messageActionChatJoinedByLink) == false) goto L308;
        if (isOut() == false) goto L306;
        this.messageText = LocaleController.getString(R.string.ActionInviteYou);
        goto L200
    L306:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionInviteUser), "un1", r8);
        goto L200
    L308:
        if ((r1112 instanceof TLRPC.TL_messageActionGiveawayLaunch) == false) goto L333;
        TLRPC.TL_messageActionGiveawayLaunch r1113 = (TLRPC.TL_messageActionGiveawayLaunch) r1112;
        TLRPC.Peer r315 = r103.peer_id;
        if (r315 == null) goto L314;
        long r316 = r315.channel_id;
        if (r316 == 0) goto L314;
        TLRPC.Chat r137 = getChat(r33, r35, r316);
    L315:
        boolean r216 = ChatObject.isChannelAndNotMegaGroup(r137);
        if ((r1113.flags & 1) == 0) goto L325;
        if (r216 == false) goto L319;
        String r217 = "BoostingStarsGiveawayJustStarted";
    L320:
        int r317 = (int) r1113.stars;
        if (r137 == null) goto L323;
        String r138 = r137.title;
    L324:
        this.messageText = LocaleController.formatPluralStringComma(r217, r317, new Object[]{r138});
        goto L200
    L323:
        r138 = r102;
        goto L324
    L319:
        r217 = "BoostingStarsGiveawayJustStartedGroup";
        goto L320
    L325:
        if (r216 == false) goto L327;
        int r218 = R.string.BoostingGiveawayJustStarted;
    L328:
        if (r137 == null) goto L330;
        String r139 = r137.title;
    L331:
        this.messageText = LocaleController.formatString(r218, new Object[]{r139});
        goto L200
    L330:
        r139 = r102;
        goto L331
    L327:
        r218 = R.string.BoostingGiveawayJustStartedGroup;
    L314:
        r137 = null;
        goto L315
    L333:
        if ((r1112 instanceof TLRPC.TL_messageActionBoostApply) == false) goto L371;
        TLRPC.Peer r318 = r103.peer_id;
        if (r318 == null) goto L339;
        long r319 = r318.channel_id;
        if (r319 == 0) goto L339;
        TLRPC.Chat r140 = getChat(r33, r35, r319);
    L340:
        boolean r141 = ChatObject.isChannelAndNotMegaGroup(r140);
        TLRPC.TL_messageActionBoostApply r219 = (TLRPC.TL_messageActionBoostApply) this.messageOwner.action;
        if ((r8 instanceof TLRPC.User) == false) goto L344;
        TLRPC.User r84 = (TLRPC.User) r8;
        boolean r320 = UserObject.isUserSelf(r84);
        String r47 = UserObject.getFirstName(r84);
    L348:
        if (r320 == false) goto L360;
        int r220 = r219.boosts;
        if (r220 > 1) goto L355;
        if (r141 == false) goto L353;
        int r142 = R.string.BoostingBoostsChannelByYouServiceMsg;
    L354:
        this.messageText = LocaleController.getString(r142);
        goto L200
    L353:
        r142 = R.string.BoostingBoostsGroupByYouServiceMsg;
        goto L354
    L355:
        if (r141 == false) goto L358;
        String r143 = "BoostingBoostsChannelByYouServiceMsgCount";
    L359:
        this.messageText = LocaleController.formatPluralString(r143, r220, new Object[0]);
        goto L200
    L358:
        r143 = "BoostingBoostsGroupByYouServiceMsgCount";
        goto L359
    L360:
        int r221 = r219.boosts;
        if (r221 > 1) goto L366;
        if (r141 == false) goto L364;
        int r144 = R.string.BoostingBoostsChannelByUserServiceMsg;
    L365:
        this.messageText = LocaleController.formatString(r144, new Object[]{r47});
        goto L200
    L364:
        r144 = R.string.BoostingBoostsGroupByUserServiceMsg;
        goto L365
    L366:
        if (r141 == false) goto L368;
        String r145 = "BoostingBoostsChannelByUserServiceMsgCount";
    L369:
        this.messageText = LocaleController.formatPluralString(r145, r221, new Object[]{r47});
        goto L200
    L368:
        r145 = "BoostingBoostsGroupByUserServiceMsgCount";
        goto L369
    L344:
        if ((r8 instanceof TLRPC.Chat) == false) goto L347;
        r47 = ((TLRPC.Chat) r8).title;
    L346:
        r320 = false;
        goto L348
    L347:
        r47 = r102;
    L339:
        r140 = null;
        goto L340
    L371:
        if ((r1112 instanceof TLRPC.TL_messageActionGiveawayResults) == false) goto L396;
        TLRPC.Peer r321 = r103.peer_id;
        if (r321 == null) goto L377;
        long r322 = r321.channel_id;
        if (r322 == 0) goto L377;
        TLRPC.Chat r146 = getChat(r33, r35, r322);
    L378:
        boolean r147 = ChatObject.isChannelAndNotMegaGroup(r146);
        TLRPC.TL_messageActionGiveawayResults r222 = (TLRPC.TL_messageActionGiveawayResults) this.messageOwner.action;
        SpannableStringBuilder r323 = new SpannableStringBuilder();
        boolean r48 = r222.stars;
        int r5 = r222.winners_count;
        if (r48 == false) goto L387;
        r323.append(LocaleController.formatPluralStringComma("BoostingStarsGiveawayServiceWinnersSelected", r5));
        if (r222.unclaimed_count <= 0) goto L394;
        r323.append("\n");
        if (r147 == false) goto L385;
        String r148 = "BoostingStarsGiveawayServiceUndistributed";
    L386:
        r323.append(LocaleController.formatPluralString(r148, r222.unclaimed_count, new Object[0]));
        goto L394
    L385:
        r148 = "BoostingStarsGiveawayServiceUndistributedGroup";
    L394:
        this.messageText = r323;
        goto L200
    L387:
        r323.append(LocaleController.formatPluralString("BoostingGiveawayServiceWinnersSelected", r5, new Object[0]));
        if (r222.unclaimed_count <= 0) goto L394;
        r323.append("\n");
        if (r147 == false) goto L392;
        String r149 = "BoostingGiveawayServiceUndistributed";
    L393:
        r323.append(LocaleController.formatPluralString(r149, r222.unclaimed_count, new Object[0]));
        goto L394
    L392:
        r149 = "BoostingGiveawayServiceUndistributedGroup";
    L377:
        r146 = null;
        goto L378
    L396:
        if ((r1112 instanceof TLRPC.TL_messageActionPrizeStars) == false) goto L399;
        TLRPC.TL_messageActionPrizeStars r1114 = (TLRPC.TL_messageActionPrizeStars) r1112;
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("ActionStarGiveawayPrize", (int) r1114.stars)), "un1", getChat(r33, r35, -DialogObject.getPeerDialogId(r1114.boost_peer)));
        goto L200
    L399:
        if ((r1112 instanceof TLRPC.TL_messageActionStarGift) == false) goto L485;
        TLRPC.TL_messageActionStarGift r1115 = (TLRPC.TL_messageActionStarGift) r1112;
        if (r1115.prepaid_upgrade == false) goto L403;
        int r324 = (int) r1115.upgrade_stars;
    L410:
        TLRPC.Peer r63 = r1115.peer;
        if (r63 != null) goto L413;
    L415:
        boolean r64 = false;
    L416:
        TLRPC.User r104 = getUser(r32, r34, this.messageOwner.peer_id.user_id);
        if (r1115.prepaid_upgrade == true) goto L424;
        TLRPC.Peer r1211 = r1115.from_id;
        if (r1211 == null) goto L424;
        long r1410 = DialogObject.getPeerDialogId(r1211);
        if (r1410 < 0) goto L423;
        r8 = getUser(r32, r34, r1410);
        goto L424
    L423:
        r8 = getChat(r33, r35, -r1410);
    L424:
        TLRPC.Peer r1212 = r1115.peer;
        if (r1212 == null) goto L430;
        long r1411 = DialogObject.getPeerDialogId(r1212);
        if (r1411 < 0) goto L429;
        TLObject r150 = getUser(r32, r34, r1411);
    L432:
        if (r1115.prepaid_upgrade == false) goto L442;
        if ((r8 instanceof TLRPC.User) == true) goto L436;
    L440:
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionPrepaidGiftInbound)), "un1", r8);
    L481:
        int r151 = this.messageText.toString().indexOf("un2");
        if (r151 == (-1)) goto L200;
        this.messageText = SpannableStringBuilder.valueOf(this.messageText).replace(r151, r151 + 3, LocaleController.formatPluralStringComma("Gift2StarsCount", r324));
        goto L200
    L436:
        if (((TLRPC.User) r8).self == false) goto L440;
        if (r1115.forceIn == true) goto L440;
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionPrepaidGiftOutbound)), "un1", r104);
        goto L481
    L442:
        if (UserObject.isService(getDialogId()) == true) goto L444;
    L450:
        if (r64 == false) goto L453;
        SpannableStringBuilder r223 = AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("ActionGiftChannel", r324));
        this.messageText = r223;
        CharSequence r224 = replaceWithLink(r223, "un1", r8);
        this.messageText = r224;
        this.messageText = replaceWithLink(r224, "un2", r150);
        this.messageTextShort = LocaleController.getString(R.string.ActionStarGift);
        goto L481
    L453:
        if (UserObject.isUserSelf(r104) == true) goto L455;
        boolean r153 = r8 instanceof TLRPC.User;
        if (r153 == true) goto L462;
    L471:
        if (r153 == true) goto L473;
    L475:
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionGiftInbound)), "un1", r8);
        TLRPC.TL_textWithEntities r154 = r1115.message;
        if (r154 != null) goto L478;
    L480:
        this.messageTextShort = LocaleController.getString(R.string.ActionStarGift);
        goto L481
    L478:
        if (TextUtils.isEmpty(r154.text) == true) goto L480;
        SpannableStringBuilder r155 = new SpannableStringBuilder(r1115.message.text);
        addEntitiesToText(r155, r1115.message.entities, isOutOwner(), false, false, false);
        this.messageTextShort = r155;
        goto L481
    L473:
        if (UserObject.isService(((TLRPC.User) r8).id) == false) goto L475;
        this.messageText = TextUtils.replace(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionGiftInbound)), new String[]{"un1"}, new CharSequence[]{LocaleController.getString(R.string.StarsTransactionUnknown)});
        goto L481
    L462:
        if (((TLRPC.User) r8).self == false) goto L471;
        if (r1115.forceIn == true) goto L471;
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionGiftOutbound)), "un1", r104);
        TLRPC.TL_textWithEntities r156 = r1115.message;
        if (r156 != null) goto L468;
    L470:
        this.messageTextShort = LocaleController.getString(R.string.ActionStarGift);
        goto L481
    L468:
        if (TextUtils.isEmpty(r156.text) == true) goto L470;
        SpannableStringBuilder r157 = new SpannableStringBuilder(r1115.message.text);
        addEntitiesToText(r157, r1115.message.entities, isOutOwner(), false, false, false);
        this.messageTextShort = r157;
        goto L481
    L455:
        if (r1115.auction_acquired == false) goto L457;
        int r158 = R.string.ActionGiftAuctionSelf;
    L458:
        this.messageText = AndroidUtilities.replaceTags(LocaleController.getString(r158));
        this.messageTextShort = LocaleController.getString(R.string.ActionStarGift);
        goto L481
    L457:
        r158 = R.string.ActionGiftSelf;
        goto L458
    L444:
        if (r1115.from_id != null) goto L450;
        if (r1115.auction_acquired == false) goto L448;
        int r159 = R.string.ActionGiftAuctionSelf;
    L449:
        this.messageText = AndroidUtilities.replaceTags(LocaleController.getString(r159));
        this.messageTextShort = LocaleController.getString(R.string.ActionStarGift);
        goto L481
    L448:
        r159 = R.string.ActionGiftSomeone;
        goto L449
    L429:
        r150 = getChat(r33, r35, -r1411);
        goto L432
    L430:
        r150 = null;
        goto L432
    L413:
        if (DialogObject.getPeerDialogId(r63) >= 0) goto L415;
        r64 = true;
        goto L416
    L403:
        TL_stars.StarGift r325 = r1115.gift;
        if (r325 == null) goto L406;
        r324 = (int) r325.stars;
    L408:
        if (r1115.upgrade_separate == true) goto L410;
        r324 = r324 + ((int) r1115.upgrade_stars);
        goto L410
    L406:
        r324 = 0;
        goto L408
    L485:
        if ((r1112 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L577;
        TLRPC.TL_messageActionStarGiftUnique r1116 = (TLRPC.TL_messageActionStarGiftUnique) r1112;
        TLRPC.User r326 = getUser(r32, r34, getDialogId());
        TL_stars.StarsAmount r65 = r1116.resale_amount;
        if (r65 == null) goto L535;
        AmountUtils$Amount r327 = AmountUtils$Amount.ofSafe(r65);
        long r1213 = getDialogId();
        TLRPC.Peer r66 = r1116.from_id;
        if (r66 == null) goto L492;
        r1213 = DialogObject.getPeerDialogId(r66);
    L492:
        if (r1213 < 0) goto L494;
        TLObject r67 = getUser(r32, r34, r1213);
    L496:
        if (r1116.craft == false) goto L498;
        this.messageText = AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionUniqueGiftCrafted));
        goto L200
    L498:
        TLRPC.Peer r85 = r1116.peer;
        if (r85 == null) goto L509;
        long r105 = DialogObject.getPeerDialogId(r85);
        if (r105 < 0) goto L503;
        TLObject r160 = getUser(r32, r34, r105);
    L505:
        if (r327.currency != AmountUtils$Currency.TON) goto L507;
        this.messageText = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.ActionUniqueGiftResaleServiceTON, new Object[]{r327.asFormatString()}));
    L508:
        CharSequence r225 = replaceWithLink(this.messageText, "un1", r67);
        this.messageText = r225;
        this.messageText = replaceWithLink(r225, "un2", r160);
        goto L200
    L507:
        this.messageText = AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("ActionUniqueGiftResaleService", (int) r327.asDecimal()));
        goto L508
    L503:
        r160 = getChat(r33, r35, -r105);
        goto L505
    L509:
        String r226 = "ActionUniqueGiftResaleOutbound";
        if (r1116.from_offer == false) goto L523;
        if (r327.currency != AmountUtils$Currency.TON) goto L519;
        if (isOutOwner() == false) goto L516;
        int r161 = R.string.ActionUniqueGiftResaleSoldOutboundTON;
    L517:
        this.messageText = AndroidUtilities.replaceTags(LocaleController.formatString(r161, new Object[]{r327.asFormatString()}));
        goto L200
    L516:
        r161 = R.string.ActionUniqueGiftResaleOutboundTON;
        goto L517
    L519:
        if (isOutOwner() == false) goto L521;
        r226 = "ActionUniqueGiftResaleSoldOutbound";
    L521:
        this.messageText = AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma(r226, (int) r327.asDecimal()));
        goto L200
    L523:
        if (r327.currency != AmountUtils$Currency.TON) goto L530;
        if (isOutOwner() == false) goto L527;
        int r162 = R.string.ActionUniqueGiftResaleOutboundTON;
    L528:
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.formatString(r162, new Object[]{r327.asFormatString()})), "un1", r67);
        goto L200
    L527:
        r162 = R.string.ActionUniqueGiftResaleInboundTON;
        goto L528
    L530:
        if (isOutOwner() == true) goto L533;
        r226 = "ActionUniqueGiftResaleInbound";
    L533:
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma(r226, (int) r327.asDecimal())), "un1", r67);
        goto L200
    L494:
        r67 = getChat(r33, r35, -r1213);
        goto L496
    L535:
        if (r1116.upgrade == false) goto L551;
        TLRPC.Peer r68 = r1116.peer;
        if (r68 == null) goto L544;
        long r86 = DialogObject.getPeerDialogId(r68);
        if (r86 < 0) goto L541;
        TLObject r163 = getUser(r32, r34, r86);
    L542:
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionUniqueGiftUpgradeInboundChannel)), "un1", r163);
        goto L200
    L541:
        r163 = getChat(r33, r35, -r86);
        goto L542
    L544:
        if (UserObject.isUserSelf(r326) == false) goto L547;
        this.messageText = AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionUniqueGiftUpgradeSelf));
        goto L200
    L547:
        if (isOutOwner() == false) goto L549;
        int r164 = R.string.ActionUniqueGiftUpgradeOutbound;
    L550:
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.getString(r164)), "un1", r326);
        goto L200
    L549:
        r164 = R.string.ActionUniqueGiftUpgradeInbound;
        goto L550
    L551:
        long r1412 = getDialogId();
        TLRPC.Peer r328 = r1116.from_id;
        if (r328 == null) goto L555;
        r1412 = DialogObject.getPeerDialogId(r328);
    L555:
        if (r1412 < 0) goto L557;
        TLObject r329 = getUser(r32, r34, r1412);
    L559:
        if (r1116.craft == false) goto L561;
        this.messageText = AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionUniqueGiftCrafted));
        goto L200
    L561:
        TLRPC.Peer r69 = r1116.peer;
        if (r69 == null) goto L569;
        long r106 = DialogObject.getPeerDialogId(r69);
        if (r106 < 0) goto L566;
        TLObject r165 = getUser(r32, r34, r106);
    L567:
        SpannableStringBuilder r227 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionUniqueGiftTransferService));
        this.messageText = r227;
        CharSequence r228 = replaceWithLink(r227, "un1", r329);
        this.messageText = r228;
        this.messageText = replaceWithLink(r228, "un2", r165);
        goto L200
    L566:
        r165 = getChat(r33, r35, -r106);
        goto L567
    L569:
        if (r1116.assigned == false) goto L572;
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.ActionUniqueGiftTransferOutboundAssigned, new Object[]{r1116.gift.title + " #" + LocaleController.formatNumber(r1116.gift.num, ',')})), "un1", r329);
        goto L200
    L572:
        if (isOutOwner() == false) goto L574;
        int r166 = R.string.ActionUniqueGiftTransferOutbound;
    L575:
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.getString(r166)), "un1", r329);
        goto L200
    L574:
        r166 = R.string.ActionUniqueGiftTransferInbound;
        goto L575
    L557:
        r329 = getChat(r33, r35, -r1412);
        goto L559
    L577:
        if ((r1112 instanceof TLRPC.TL_messageActionGiftStars) == false) goto L595;
        boolean r229 = r8 instanceof TLRPC.User;
        if (r229 == true) goto L581;
    L583:
        if (r229 == true) goto L585;
    L587:
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionGiftInbound)), "un1", r8);
    L588:
        int r230 = this.messageText.toString().indexOf("un2");
        if (r230 == (-1)) goto L200;
        SpannableStringBuilder r330 = SpannableStringBuilder.valueOf(this.messageText);
        BillingController r49 = BillingController.getInstance();
        TLRPC.MessageAction r52 = this.messageOwner.action;
        String r410 = r49.formatCurrency(r52.amount, r52.currency);
        if ((this.messageOwner.action.flags & 1) == 0) goto L593;
        r410 = String.format("%.2f", new Object[]{Double.valueOf(this.messageOwner.action.cryptoAmount * Math.pow(10.0d, -9.0d))}) + " " + this.messageOwner.action.cryptoCurrency + " (~ " + r410 + ")";
    L593:
        this.messageText = r330.replace(r230, r230 + 3, r410);
        goto L200
    L585:
        if (UserObject.isService(((TLRPC.User) r8).id) == false) goto L587;
        this.messageText = TextUtils.replace(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionGiftInbound)), new String[]{"un1"}, new CharSequence[]{LocaleController.getString(R.string.StarsTransactionUnknown)});
        goto L588
    L581:
        if (((TLRPC.User) r8).self == false) goto L583;
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionGiftOutbound)), "un1", getUser(r32, r34, r103.peer_id.user_id));
        goto L588
    L595:
        if ((r1112 instanceof TLRPC.TL_messageActionGiftCode) == true) goto L597;
    L599:
        TLRPC.Chat r282 = r6;
        if (TlUtils.isInstance(r1112, new Class[]{TLRPC.TL_messageActionGiftPremium.class, TLRPC.TL_messageActionGiftCode.class, TLRPC.TL_messageActionGiftTon.class}) == true) goto L602;
        TLRPC.Message r167 = this.messageOwner;
        TLRPC.MessageAction r231 = r167.action;
        if ((r231 instanceof TLRPC.TL_messageActionSuggestBirthday) == false) goto L624;
        if (isOutOwner() == false) goto L622;
        this.messageText = LocaleController.getString(R.string.ActionYouSuggestBirthday);
        goto L200
    L622:
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionSuggestBirthday)), "un1", r8);
        goto L200
    L624:
        if ((r231 instanceof TLRPC.TL_messageActionSuggestProfilePhoto) == false) goto L634;
        TLRPC.Photo r168 = r231.photo;
        if (r168 == null) goto L632;
        ArrayList<TLRPC.VideoSize> r169 = r168.video_sizes;
        if (r169 == null) goto L632;
        if (r169.isEmpty() == true) goto L632;
        this.messageText = LocaleController.getString(R.string.ActionSuggestVideoShort);
    L632:
        this.messageText = LocaleController.getString(R.string.ActionSuggestPhotoShort);
        goto L200
    L634:
        if ((r231 instanceof TLRPC.TL_messageActionChatEditPhoto) == false) goto L660;
        TLRPC.Peer r170 = r167.peer_id;
        if (r170 == null) goto L640;
        long r171 = r170.channel_id;
        if (r171 == 0) goto L640;
        TLRPC.Chat r172 = getChat(r33, r35, r171);
    L642:
        if (ChatObject.isChannel(r172) == false) goto L650;
        if (r172.megagroup == true) goto L650;
        if (isVideoAvatar() == false) goto L648;
        this.messageText = LocaleController.getString(R.string.ActionChannelChangedVideo);
        goto L200
    L648:
        this.messageText = LocaleController.getString(R.string.ActionChannelChangedPhoto);
    L650:
        if (isOut() == false) goto L656;
        if (isVideoAvatar() == false) goto L654;
        this.messageText = LocaleController.getString(R.string.ActionYouChangedVideo);
        goto L200
    L654:
        this.messageText = LocaleController.getString(R.string.ActionYouChangedPhoto);
        goto L200
    L656:
        if (isVideoAvatar() == false) goto L658;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionChangedVideo), "un1", r8);
        goto L200
    L658:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionChangedPhoto), "un1", r8);
    L640:
        r172 = null;
        goto L642
    L660:
        if ((r231 instanceof TLRPC.TL_messageActionChatEditTitle) == false) goto L677;
        TLRPC.Peer r173 = r167.peer_id;
        if (r173 == null) goto L666;
        long r174 = r173.channel_id;
        if (r174 == 0) goto L666;
        TLRPC.Chat r175 = getChat(r33, r35, r174);
    L668:
        if (ChatObject.isChannel(r175) == false) goto L673;
        if (r175.megagroup == true) goto L673;
        this.messageText = LocaleController.getString(R.string.ActionChannelChangedTitle).replace("un2", this.messageOwner.action.title);
    L673:
        if (isOut() == false) goto L675;
        this.messageText = LocaleController.getString(R.string.ActionYouChangedTitle).replace("un2", this.messageOwner.action.title);
        goto L200
    L675:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionChangedTitle).replace("un2", this.messageOwner.action.title), "un1", r8);
    L666:
        r175 = null;
        goto L668
    L677:
        if ((r231 instanceof TLRPC.TL_messageActionChatDeletePhoto) == false) goto L694;
        TLRPC.Peer r176 = r167.peer_id;
        if (r176 == null) goto L683;
        long r177 = r176.channel_id;
        if (r177 == 0) goto L683;
        TLRPC.Chat r178 = getChat(r33, r35, r177);
    L685:
        if (ChatObject.isChannel(r178) == false) goto L690;
        if (r178.megagroup == true) goto L690;
        this.messageText = LocaleController.getString(R.string.ActionChannelRemovedPhoto);
    L690:
        if (isOut() == false) goto L692;
        this.messageText = LocaleController.getString(R.string.ActionYouRemovedPhoto);
        goto L200
    L692:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionRemovedPhoto), "un1", r8);
    L683:
        r178 = null;
        goto L685
    L694:
        if ((r231 instanceof TLRPC.TL_messageActionTTLChange) == true) goto L696;
        String r1214 = ", ";
        if ((r231 instanceof TLRPC.TL_messageActionRequestedPeer) == false) goto L761;
        ArrayList r179 = new ArrayList();
        ArrayList<TLRPC.Peer> r232 = ((TLRPC.TL_messageActionRequestedPeer) this.messageOwner.action).peers;
        int r331 = r232.size();
        int r87 = 0;
        int r1117 = 0;
        int r1310 = 0;
        int r1413 = 0;
    L708:
        if (r1413 >= r331) goto L735;
        TLRPC.Peer r1510 = r232.get(r1413);
        r1413 = r1413 + 1;
        TLRPC.Peer r1511 = r1510;
        ArrayList<TLRPC.Peer> r1610 = r232;
        boolean r233 = r1511 instanceof TLRPC.TL_peerUser;
        if (r233 == false) goto L714;
        boolean r1710 = r233;
        String r30 = r1214;
        int r182 = r1310;
        TLObject r234 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r1511.user_id));
        if (r234 != null) goto L725;
        r234 = getUser(r32, r34, r1511.user_id);
    L725:
        if (r1710 == false) goto L729;
        r87 = r87 + 1;
    L727:
        r1310 = r182;
    L732:
        if (r234 == null) goto L734;
        r179.add(r234);
    L734:
        r232 = r1610;
        r1214 = r30;
        goto L708
    L729:
        if ((r1511 instanceof TLRPC.TL_peerChat) == false) goto L731;
        r1310 = r182 + 1;
        goto L732
    L731:
        r1117 = r1117 + 1;
        goto L727
    L714:
        r1710 = r233;
        r30 = r1214;
        r182 = r1310;
        if ((r1511 instanceof TLRPC.TL_peerChat) == false) goto L720;
        r234 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r1511.chat_id));
        if (r234 != null) goto L725;
        r234 = getChat(r33, r35, r1511.chat_id);
        goto L725
    L720:
        if ((r1511 instanceof TLRPC.TL_peerChannel) == false) goto L724;
        r234 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r1511.channel_id));
        if (r234 != null) goto L725;
        r234 = getChat(r33, r35, r1511.channel_id);
        goto L725
    L724:
        r234 = null;
        goto L725
    L735:
        String r302 = r1214;
        int r183 = r1310;
        if (r87 > 0) goto L738;
    L740:
        if (r1117 > 0) goto L742;
    L744:
        if (r183 > 0) goto L746;
    L748:
        SpannableStringBuilder r235 = new SpannableStringBuilder();
        int r332 = 0;
    L750:
        if (r332 >= r179.size()) goto L755;
        r235.append(replaceWithLink("un1", "un1", (TLObject) r179.get(r332)));
        String r1118 = r302;
        if (r332 >= (r179.size() - 1)) goto L754;
        r235.append(r1118);
    L754:
        r332 = r332 + 1;
        r302 = r1118;
        goto L750
    L755:
        this.messageText = AndroidUtilities.replaceCharSequence("un1", LocaleController.getString(R.string.ActionRequestedPeer), r235);
    L756:
        TLRPC.User r180 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(getDialogId()));
        if (r180 != null) goto L759;
        r180 = getUser(r32, r34, getDialogId());
    L759:
        this.messageText = replaceWithLink(this.messageText, "un2", r180);
        goto L200
    L746:
        if (r183 == r179.size()) goto L748;
        this.messageText = LocaleController.getPluralString("ActionRequestedPeerChatPlural", r183);
        goto L756
    L742:
        if (r1117 == r179.size()) goto L744;
        this.messageText = LocaleController.getPluralString("ActionRequestedPeerChannelPlural", r1117);
        goto L756
    L738:
        if (r87 == r179.size()) goto L740;
        this.messageText = LocaleController.getPluralString("ActionRequestedPeerUserPlural", r87);
        goto L756
    L761:
        if ((r231 instanceof TLRPC.TL_messageActionSetMessagesTTL) == false) goto L809;
        TLRPC.TL_messageActionSetMessagesTTL r236 = (TLRPC.TL_messageActionSetMessagesTTL) r231;
        TLRPC.Peer r181 = r167.peer_id;
        if (r181 == null) goto L767;
        long r1119 = r181.channel_id;
        if (r1119 == 0) goto L767;
        TLRPC.Chat r184 = getChat(r33, r35, r1119);
    L768:
        if (r184 != null) goto L770;
    L775:
        long r94 = r236.auto_setting_from;
        if (r94 == 0) goto L799;
        this.drawServiceWithDefaultTypeface = true;
        if (r94 != UserConfig.getInstance(this.currentAccount).clientUserId) goto L780;
        this.messageText = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AutoDeleteGlobalActionFromYou, new Object[]{LocaleController.formatTTLString(r236.period)}));
        goto L200
    L780:
        if (r34 == null) goto L782;
        TLRPC.Chat r185 = r34.get(r236.auto_setting_from);
    L783:
        if (r185 != null) goto L786;
        if (r32 == null) goto L786;
        r185 = r32.get(Long.valueOf(r236.auto_setting_from));
    L786:
        if (r185 != null) goto L789;
        if (r33 == null) goto L789;
        r185 = r33.get(Long.valueOf(r236.auto_setting_from));
    L789:
        if (r185 != null) goto L794;
        long r333 = r236.auto_setting_from;
        int r334 = this.currentAccount;
        if (r333 <= 0) goto L793;
        r185 = MessagesController.getInstance(r334).getUser(Long.valueOf(r236.auto_setting_from));
        goto L794
    L793:
        r185 = MessagesController.getInstance(r334).getChat(Long.valueOf(-r236.auto_setting_from));
    L794:
        if (r185 == null) goto L797;
        r8 = r185;
    L797:
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AutoDeleteGlobalAction, new Object[]{LocaleController.formatTTLString(r236.period)})), "un1", r8);
        goto L200
    L782:
        r185 = null;
        goto L783
    L799:
        if (r236.period == 0) goto L805;
        boolean r186 = isOut();
        int r237 = r236.period;
        if (r186 == false) goto L803;
        this.messageText = LocaleController.formatString(R.string.ActionTTLYouChanged, new Object[]{LocaleController.formatTTLString(r237)});
        goto L200
    L803:
        this.messageText = replaceWithLink(LocaleController.formatString(R.string.ActionTTLChanged, new Object[]{LocaleController.formatTTLString(r237)}), "un1", r8);
        goto L200
    L805:
        if (isOut() == false) goto L807;
        this.messageText = LocaleController.getString(R.string.ActionTTLYouDisabled);
        goto L200
    L807:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionTTLDisabled), "un1", r8);
        goto L200
    L770:
        if (r184.megagroup == true) goto L775;
        int r187 = r236.period;
        if (r187 == 0) goto L774;
        this.messageText = LocaleController.formatString(R.string.ActionTTLChannelChanged, new Object[]{LocaleController.formatTTLString(r187)});
        goto L200
    L774:
        this.messageText = LocaleController.getString(R.string.ActionTTLChannelDisabled);
    L767:
        r184 = null;
        goto L768
    L809:
        if ((r231 instanceof TLRPC.TL_messageActionLoginUnknownLocation) == false) goto L823;
        long r188 = r167.date * 1000;
        if (LocaleController.getInstance().getFormatterDay() != null) goto L813;
    L815:
        r9 = r102;
        String r189 = r9 + this.messageOwner.date;
    L816:
        TLRPC.User r238 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        if (r238 != null) goto L819;
        r238 = getUser(r32, r34, this.messageOwner.peer_id.user_id);
    L819:
        if (r238 == null) goto L821;
        String r239 = UserObject.getFirstName(r238);
    L822:
        int r335 = R.string.NotificationUnrecognizedDevice;
        TLRPC.MessageAction r411 = this.messageOwner.action;
        this.messageText = LocaleController.formatString(r335, new Object[]{r239, r189, r411.title, r411.address});
        goto L1538
    L821:
        r239 = r9;
        goto L822
    L813:
        if (LocaleController.getInstance().getFormatterYear() == null) goto L815;
        r189 = LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterYear().format(r188), LocaleController.getInstance().getFormatterDay().format(r188)});
        r9 = r102;
        goto L816
    L823:
        r9 = r102;
        if ((r231 instanceof TLRPC.TL_messageActionUserJoined) == false) goto L826;
    L1340:
        this.messageText = LocaleController.formatString(R.string.NotificationContactJoined, new Object[]{UserObject.getUserName(r36)});
        goto L1538
    L826:
        if ((r231 instanceof TLRPC.TL_messageActionContactSignUp) == true) goto L1340;
        if ((r231 instanceof TLRPC.TL_messageActionUserUpdatedPhoto) == false) goto L832;
        this.messageText = LocaleController.formatString(R.string.NotificationContactNewPhoto, new Object[]{UserObject.getUserName(r36)});
        goto L1538
    L832:
        if ((r231 instanceof TLRPC.TL_messageEncryptedAction) == false) goto L852;
        TLRPC.DecryptedMessageAction r190 = r231.encryptedAction;
        if ((r190 instanceof TLRPC.TL_decryptedMessageActionScreenshotMessages) == false) goto L840;
        if (isOut() == false) goto L838;
        this.messageText = LocaleController.getString(R.string.ActionTakeScreenshootYou);
        goto L1538
    L838:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionTakeScreenshoot), "un1", r8);
        goto L1538
    L840:
        if ((r190 instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL) == false) goto L1538;
        TLRPC.TL_decryptedMessageActionSetMessageTTL r191 = (TLRPC.TL_decryptedMessageActionSetMessageTTL) r190;
        if (r191.ttl_seconds == 0) goto L848;
        if (isOut() == false) goto L846;
        this.messageText = LocaleController.formatString(R.string.MessageLifetimeChangedOutgoing, new Object[]{LocaleController.formatTTLString(r191.ttl_seconds)});
        goto L1538
    L846:
        this.messageText = LocaleController.formatString(R.string.MessageLifetimeChanged, new Object[]{UserObject.getFirstName(r36), LocaleController.formatTTLString(r191.ttl_seconds)});
        goto L1538
    L848:
        if (isOut() == false) goto L850;
        this.messageText = LocaleController.getString(R.string.MessageLifetimeYouRemoved);
        goto L1538
    L850:
        this.messageText = LocaleController.formatString(R.string.MessageLifetimeRemoved, new Object[]{UserObject.getFirstName(r36)});
        goto L1538
    L852:
        if ((r231 instanceof TLRPC.TL_messageActionScreenshotTaken) == false) goto L858;
        if (isOut() == false) goto L856;
        this.messageText = LocaleController.getString(R.string.ActionTakeScreenshootYou);
        goto L1538
    L856:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.ActionTakeScreenshoot), "un1", r8);
        goto L1538
    L858:
        if ((r231 instanceof TLRPC.TL_messageActionCreatedBroadcastList) == false) goto L861;
        this.messageText = LocaleController.getString(R.string.YouCreatedBroadcastList);
        goto L1538
    L861:
        if ((r231 instanceof TLRPC.TL_messageActionChannelCreate) == false) goto L875;
        TLRPC.Peer r192 = r167.peer_id;
        if (r192 == null) goto L867;
        long r193 = r192.channel_id;
        if (r193 == 0) goto L867;
        TLRPC.Chat r194 = getChat(r33, r35, r193);
    L869:
        if (ChatObject.isChannel(r194) == true) goto L871;
    L873:
        this.messageText = LocaleController.getString(R.string.ActionCreateChannel);
        goto L1538
    L871:
        if (r194.megagroup == false) goto L873;
        this.messageText = LocaleController.getString(R.string.ActionCreateMega);
    L867:
        r194 = null;
        goto L869
    L875:
        if ((r231 instanceof TLRPC.TL_messageActionChatMigrateTo) == false) goto L878;
        this.messageText = LocaleController.getString(R.string.ActionMigrateFromGroup);
        goto L1538
    L878:
        if ((r231 instanceof TLRPC.TL_messageActionChannelMigrateFrom) == false) goto L881;
        this.messageText = LocaleController.getString(R.string.ActionMigrateFromGroup);
        goto L1538
    L881:
        if ((r231 instanceof TLRPC.TL_messageActionPinMessage) == false) goto L887;
        if (r36 != null) goto L884;
        TLRPC.Chat r195 = getChat(r33, r35, r167.peer_id.channel_id);
    L885:
        generatePinMessageText(r36, r195);
        goto L1538
    L884:
        r195 = null;
        goto L885
    L887:
        if ((r231 instanceof TLRPC.TL_messageActionHistoryClear) == false) goto L890;
        this.messageText = LocaleController.getString(R.string.HistoryCleared);
        goto L1538
    L890:
        if ((r231 instanceof TLRPC.TL_messageActionTopicCreate) == false) goto L893;
        this.messageText = LocaleController.getString(R.string.TopicCreated);
        TLRPC.TL_messageActionTopicCreate r196 = (TLRPC.TL_messageActionTopicCreate) this.messageOwner.action;
        TLRPC.TL_forumTopic r240 = new TLRPC.TL_forumTopic();
        r240.icon_emoji_id = r196.icon_emoji_id;
        r240.title = r196.title;
        r240.icon_color = r196.icon_color;
        this.messageTextShort = AndroidUtilities.replaceCharSequence("%s", LocaleController.getString(R.string.TopicWasCreatedAction), ForumUtilities.getTopicSpannedName(r240, null, false));
        goto L1538
    L893:
        if ((r231 instanceof TLRPC.TL_messageActionTopicEdit) == false) goto L927;
        TLRPC.TL_messageActionTopicEdit r241 = (TLRPC.TL_messageActionTopicEdit) r231;
        if (r36 == null) goto L897;
        String r197 = ContactsController.formatName(r36.first_name, r36.last_name);
    L900:
        if (r197 == null) goto L902;
        String r198 = r197.trim();
    L903:
        TLRPC.MessageAction r412 = this.messageOwner.action;
        int r53 = r412.flags;
        if ((r53 & 8) <= 0) goto L910;
        if (((TLRPC.TL_messageActionTopicEdit) r412).hidden == false) goto L908;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.TopicHidden2), "%s", r36);
        this.messageTextShort = LocaleController.getString(R.string.TopicHidden);
        goto L1538
    L908:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.TopicShown2), "%s", r36);
        this.messageTextShort = LocaleController.getString(R.string.TopicShown);
        goto L1538
    L910:
        if ((r53 & 4) <= 0) goto L916;
        if (((TLRPC.TL_messageActionTopicEdit) r412).closed == false) goto L914;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.TopicClosed2), "%s", r36);
        this.messageTextShort = LocaleController.getString(R.string.TopicClosed);
        goto L1538
    L914:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.TopicRestarted2), "%s", r36);
        this.messageTextShort = LocaleController.getString(R.string.TopicRestarted);
        goto L1538
    L916:
        if ((r53 & 2) == 0) goto L921;
        if ((r53 & 1) == 0) goto L921;
        TLRPC.TL_forumTopic r336 = new TLRPC.TL_forumTopic();
        r336.icon_emoji_id = r241.icon_emoji_id;
        r336.title = r241.title;
        r336.icon_color = ForumBubbleDrawable.serverSupportedColor[0];
        CharSequence r242 = ForumUtilities.getTopicSpannedName(r336, null, this.topicIconDrawable, false);
        this.messageText = AndroidUtilities.replaceCharSequence("%2$s", AndroidUtilities.replaceCharSequence("%1$s", LocaleController.getString(R.string.TopicChangeIconAndTitleTo), r198), r242);
        this.messageTextShort = LocaleController.getString(R.string.TopicRenamed);
        this.messageTextForReply = AndroidUtilities.replaceCharSequence("%s", LocaleController.getString(R.string.TopicChangeIconAndTitleToInReply), r242);
    L921:
        if ((r53 & 2) == 0) goto L924;
        TLRPC.TL_forumTopic r337 = new TLRPC.TL_forumTopic();
        r337.icon_emoji_id = r241.icon_emoji_id;
        r337.title = r9;
        r337.icon_color = ForumBubbleDrawable.serverSupportedColor[0];
        CharSequence r243 = ForumUtilities.getTopicSpannedName(r337, null, this.topicIconDrawable, false);
        this.messageText = AndroidUtilities.replaceCharSequence("%2$s", AndroidUtilities.replaceCharSequence("%1$s", LocaleController.getString(R.string.TopicIconChangedTo), r198), r243);
        this.messageTextShort = LocaleController.getString(R.string.TopicIconChanged);
        this.messageTextForReply = AndroidUtilities.replaceCharSequence("%s", LocaleController.getString(R.string.TopicIconChangedToInReply), r243);
        goto L1538
    L924:
        if ((r53 & 1) == 0) goto L1538;
        this.messageText = AndroidUtilities.replaceCharSequence("%2$s", AndroidUtilities.replaceCharSequence("%1$s", LocaleController.getString(R.string.TopicRenamedTo), r198), r241.title);
        this.messageTextShort = LocaleController.getString(R.string.TopicRenamed);
        this.messageTextForReply = AndroidUtilities.replaceCharSequence("%s", LocaleController.getString(R.string.TopicRenamedToInReply), r241.title);
        goto L1538
    L902:
        r198 = "DELETED";
        goto L903
    L897:
        if (r282 == null) goto L899;
        r197 = r282.title;
        r36 = r282;
        goto L900
    L899:
        r197 = null;
        r36 = null;
        goto L900
    L927:
        if ((r231 instanceof TLRPC.TL_messageActionGameScore) == false) goto L930;
        generateGameMessageText(r36);
        goto L1538
    L930:
        if ((r231 instanceof TLRPC.TL_messageActionConferenceCall) == false) goto L954;
        TLRPC.TL_messageActionConferenceCall r244 = (TLRPC.TL_messageActionConferenceCall) r231;
        if (isOutOwner() == false) goto L935;
        this.messageText = LocaleController.getString(R.string.ConferenceCallOutgoing);
    L938:
        int r199 = r244.duration;
        if (r199 <= 0) goto L1538;
        String r1100 = LocaleController.formatCallDuration(r199);
        String r245 = LocaleController.formatString(R.string.CallMessageWithDuration, new Object[]{this.messageText, r1100});
        this.messageText = r245;
        String r246 = r245.toString();
        int r338 = r246.indexOf(r1100);
        if (r338 == (-1)) goto L1538;
        SpannableString r413 = new SpannableString(this.messageText);
        int r1101 = r1100.length() + r338;
        if (r338 <= 0) goto L948;
        if (r246.charAt(r338 - 1) != '(') goto L948;
        r338 = r338 - 1;
    L948:
        if (r1101 < r246.length()) goto L950;
    L952:
        r413.setSpan(new TypefaceSpan(Typeface.DEFAULT), r338, r1101, 0);
        this.messageText = r413;
        goto L1538
    L950:
        if (r246.charAt(r1101) != ')') goto L952;
        r1101 = r1101 + 1;
        goto L952
    L935:
        if (r244.missed == false) goto L937;
        this.messageText = LocaleController.getString(R.string.ConferenceCallMissed);
        goto L938
    L937:
        this.messageText = LocaleController.getString(R.string.ConferenceCallIncoming);
        goto L938
    L954:
        if ((r231 instanceof TLRPC.TL_messageActionPhoneCall) == false) goto L996;
        TLRPC.TL_messageActionPhoneCall r247 = (TLRPC.TL_messageActionPhoneCall) r231;
        boolean r1102 = r247.reason instanceof TLRPC.TL_phoneCallDiscardReasonMissed;
        if (isFromUser() == true) goto L958;
    L967:
        if (r1102 == true) goto L969;
        boolean r1103 = r247.reason instanceof TLRPC.TL_phoneCallDiscardReasonBusy;
        boolean r339 = r247.video;
        if (r1103 == false) goto L977;
        if (r339 == false) goto L976;
        this.messageText = LocaleController.getString(R.string.CallMessageVideoIncomingDeclined);
    L980:
        int r1104 = r247.duration;
        if (r1104 <= 0) goto L1538;
        String r1105 = LocaleController.formatCallDuration(r1104);
        String r248 = LocaleController.formatString(R.string.CallMessageWithDuration, new Object[]{this.messageText, r1105});
        this.messageText = r248;
        String r249 = r248.toString();
        int r340 = r249.indexOf(r1105);
        if (r340 == (-1)) goto L1538;
        SpannableString r414 = new SpannableString(this.messageText);
        int r1106 = r1105.length() + r340;
        if (r340 <= 0) goto L990;
        if (r249.charAt(r340 - 1) != '(') goto L990;
        r340 = r340 - 1;
    L990:
        if (r1106 < r249.length()) goto L992;
    L994:
        r414.setSpan(new TypefaceSpan(Typeface.DEFAULT), r340, r1106, 0);
        this.messageText = r414;
        goto L1538
    L992:
        if (r249.charAt(r1106) != ')') goto L994;
        r1106 = r1106 + 1;
        goto L994
    L976:
        this.messageText = LocaleController.getString(R.string.CallMessageIncomingDeclined);
        goto L980
    L977:
        if (r339 == false) goto L979;
        this.messageText = LocaleController.getString(R.string.CallMessageVideoIncoming);
        goto L980
    L979:
        this.messageText = LocaleController.getString(R.string.CallMessageIncoming);
        goto L980
    L969:
        if (r247.video == false) goto L971;
        this.messageText = LocaleController.getString(R.string.CallMessageVideoIncomingMissed);
        goto L980
    L971:
        this.messageText = LocaleController.getString(R.string.CallMessageIncomingMissed);
        goto L980
    L958:
        if (this.messageOwner.from_id.user_id != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L967;
        boolean r341 = r247.video;
        if (r1102 == false) goto L964;
        if (r341 == false) goto L963;
        this.messageText = LocaleController.getString(R.string.CallMessageVideoOutgoingMissed);
        goto L980
    L963:
        this.messageText = LocaleController.getString(R.string.CallMessageOutgoingMissed);
        goto L980
    L964:
        if (r341 == false) goto L966;
        this.messageText = LocaleController.getString(R.string.CallMessageVideoOutgoing);
        goto L980
    L966:
        this.messageText = LocaleController.getString(R.string.CallMessageOutgoing);
        goto L980
    L996:
        if ((r231 instanceof TLRPC.TL_messageActionPaymentSent) == false) goto L999;
        generatePaymentSentMessageText(getUser(r32, r34, getDialogId()), false);
        goto L1538
    L999:
        if ((r231 instanceof TLRPC.TL_messageActionPaymentSentMe) == false) goto L1002;
        generatePaymentSentMessageText(getUser(r32, r34, getDialogId()), true);
        goto L1538
    L1002:
        if ((r231 instanceof TLRPC.TL_messageActionBotAllowed) == false) goto L1027;
        String r1107 = ((TLRPC.TL_messageActionBotAllowed) r231).domain;
        TLRPC.BotApp r342 = ((TLRPC.TL_messageActionBotAllowed) r231).app;
        if (((TLRPC.TL_messageActionBotAllowed) r231).from_request == false) goto L1006;
        this.messageText = LocaleController.getString(R.string.ActionBotAllowedWebapp);
        goto L1538
    L1006:
        if (r342 == null) goto L1018;
        String r1108 = r342.title;
        if (r1108 != null) goto L1010;
        String r253 = r9;
    L1011:
        String r1109 = LocaleController.getString(R.string.ActionBotAllowedApp);
        int r250 = r1109.indexOf("%1$s");
        SpannableString r610 = new SpannableString(String.format(r1109, new Object[]{r253}));
        TLRPC.User r1120 = getUser(r32, r34, getDialogId());
        if (r250 < 0) goto L1017;
        if (r1120 == null) goto L1017;
        String r1121 = UserObject.getPublicUsername(r1120);
        if (r1121 == null) goto L1017;
        r610.setSpan(new URLSpanNoUnderlineBold("https://" + MessagesController.getInstance(this.currentAccount).linkPrefix + "/" + r1121 + "/" + r342.short_name), r250, r253.length() + r250, 33);
    L1017:
        this.messageText = r610;
        goto L1538
    L1010:
        r253 = r1108;
        goto L1011
    L1018:
        if (r1107 != null) goto L1020;
        r1107 = r9;
    L1020:
        String r251 = LocaleController.getString(R.string.ActionBotAllowed);
        int r343 = r251.indexOf("%1$s");
        SpannableString r415 = new SpannableString(String.format(r251, new Object[]{r1107}));
        if (r343 >= 0) goto L1023;
    L1025:
        this.messageText = r415;
        goto L1538
    L1023:
        if (TextUtils.isEmpty(r1107) == true) goto L1025;
        r415.setSpan(new URLSpanNoUnderlineBold(AndroidUtilities.defaultUrlScheme(r1107) + r1107), r343, r1107.length() + r343, 33);
        goto L1025
    L1027:
        if ((r231 instanceof TLRPC.TL_messageActionAttachMenuBotAllowed) == false) goto L1029;
    L1339:
        this.messageText = LocaleController.getString(R.string.ActionAttachMenuBotAllowed);
        goto L1538
    L1029:
        if ((r231 instanceof TLRPC.TL_messageActionBotAllowed) == false) goto L1034;
        if (((TLRPC.TL_messageActionBotAllowed) r231).attach_menu == true) goto L1339;
    L1034:
        if ((r231 instanceof TLRPC.TL_messageActionSecureValuesSent) == false) goto L1086;
        TLRPC.TL_messageActionSecureValuesSent r254 = (TLRPC.TL_messageActionSecureValuesSent) r231;
        StringBuilder r1122 = new StringBuilder();
        int r344 = r254.types.size();
        int r611 = 0;
    L1036:
        if (r611 >= r344) goto L1080;
        TLRPC.SecureValueType r7 = r254.types.get(r611);
        if (r1122.length() <= 0) goto L1041;
        r1122.append(", ");
    L1041:
        if ((r7 instanceof TLRPC.TL_secureValueTypePhone) == false) goto L1044;
        r1122.append(LocaleController.getString(R.string.ActionBotDocumentPhone));
    L1079:
        r611 = r611 + 1;
        goto L1036
    L1044:
        if ((r7 instanceof TLRPC.TL_secureValueTypeEmail) == false) goto L1047;
        r1122.append(LocaleController.getString(R.string.ActionBotDocumentEmail));
        goto L1079
    L1047:
        if ((r7 instanceof TLRPC.TL_secureValueTypeAddress) == false) goto L1050;
        r1122.append(LocaleController.getString(R.string.ActionBotDocumentAddress));
        goto L1079
    L1050:
        if ((r7 instanceof TLRPC.TL_secureValueTypePersonalDetails) == false) goto L1053;
        r1122.append(LocaleController.getString(R.string.ActionBotDocumentIdentity));
        goto L1079
    L1053:
        if ((r7 instanceof TLRPC.TL_secureValueTypePassport) == false) goto L1056;
        r1122.append(LocaleController.getString(R.string.ActionBotDocumentPassport));
        goto L1079
    L1056:
        if ((r7 instanceof TLRPC.TL_secureValueTypeDriverLicense) == false) goto L1059;
        r1122.append(LocaleController.getString(R.string.ActionBotDocumentDriverLicence));
        goto L1079
    L1059:
        if ((r7 instanceof TLRPC.TL_secureValueTypeIdentityCard) == false) goto L1062;
        r1122.append(LocaleController.getString(R.string.ActionBotDocumentIdentityCard));
        goto L1079
    L1062:
        if ((r7 instanceof TLRPC.TL_secureValueTypeUtilityBill) == false) goto L1065;
        r1122.append(LocaleController.getString(R.string.ActionBotDocumentUtilityBill));
        goto L1079
    L1065:
        if ((r7 instanceof TLRPC.TL_secureValueTypeBankStatement) == false) goto L1068;
        r1122.append(LocaleController.getString(R.string.ActionBotDocumentBankStatement));
        goto L1079
    L1068:
        if ((r7 instanceof TLRPC.TL_secureValueTypeRentalAgreement) == false) goto L1071;
        r1122.append(LocaleController.getString(R.string.ActionBotDocumentRentalAgreement));
        goto L1079
    L1071:
        if ((r7 instanceof TLRPC.TL_secureValueTypeInternalPassport) == false) goto L1074;
        r1122.append(LocaleController.getString(R.string.ActionBotDocumentInternalPassport));
        goto L1079
    L1074:
        if ((r7 instanceof TLRPC.TL_secureValueTypePassportRegistration) == false) goto L1077;
        r1122.append(LocaleController.getString(R.string.ActionBotDocumentPassportRegistration));
        goto L1079
    L1077:
        if ((r7 instanceof TLRPC.TL_secureValueTypeTemporaryRegistration) == false) goto L1079;
        r1122.append(LocaleController.getString(R.string.ActionBotDocumentTemporaryRegistration));
        goto L1079
    L1080:
        TLRPC.Peer r255 = this.messageOwner.peer_id;
        if (r255 == null) goto L1083;
        TLRPC.User r345 = getUser(r32, r34, r255.user_id);
    L1084:
        this.messageText = LocaleController.formatString(R.string.ActionBotDocuments, new Object[]{UserObject.getFirstName(r345), r1122.toString()});
        goto L1538
    L1083:
        r345 = null;
        goto L1084
    L1086:
        if ((r231 instanceof TLRPC.TL_messageActionWebViewDataSent) == false) goto L1089;
        this.messageText = LocaleController.formatString(R.string.ActionBotWebViewData, new Object[]{((TLRPC.TL_messageActionWebViewDataSent) r231).text});
        goto L1538
    L1089:
        if ((r231 instanceof TLRPC.TL_messageActionSetChatTheme) == false) goto L1119;
        TLRPC.ChatTheme r1123 = ((TLRPC.TL_messageActionSetChatTheme) r231).theme;
        CharSequence r256 = TlUtils.getThemeEmoticonOrGiftTitle(r1123);
        if (r256 != null) goto L1093;
    L1095:
        String r1124 = UserObject.getFirstName(r36);
        if (r36 != null) goto L1099;
        if (r282 == null) goto L1099;
        boolean r72 = true;
    L1100:
        if (r72 == false) goto L1102;
        r1124 = r282.title;
    L1102:
        boolean r346 = UserObject.isUserSelf(r36);
        if (TextUtils.isEmpty(r256) == false) goto L1111;
        if (r346 == false) goto L1106;
        CharSequence r1125 = LocaleController.formatSpannable(R.string.ChatThemeDisabledYou, new Object[0]);
    L1110:
        this.messageText = r1125;
        goto L1538
    L1106:
        if (r72 == false) goto L1108;
        int r347 = R.string.ChannelThemeDisabled;
    L1109:
        r1125 = LocaleController.formatSpannable(r347, new Object[]{r1124, r256});
        goto L1110
    L1108:
        r347 = R.string.ChatThemeDisabled;
        goto L1109
    L1111:
        if (r346 == false) goto L1113;
        CharSequence r1126 = LocaleController.formatSpannable(R.string.ChatThemeChangedYou, new Object[]{r256});
    L1117:
        this.messageText = r1126;
        goto L1538
    L1113:
        if (r72 == false) goto L1115;
        int r348 = R.string.ChannelThemeChangedTo;
    L1116:
        r1126 = LocaleController.formatSpannable(r348, new Object[]{r1124, r256});
        goto L1117
    L1115:
        r348 = R.string.ChatThemeChangedTo;
    L1099:
        r72 = false;
        goto L1100
    L1093:
        if ((r1123 instanceof TLRPC.TL_chatThemeUniqueGift) == false) goto L1095;
        CharSequence r416 = new SpannableStringBuilder(r256);
        TlUtils.getGiftDocument(((TLRPC.TL_chatThemeUniqueGift) r1123).gift);
        r256 = r416;
        goto L1095
    L1119:
        if ((r231 instanceof TLRPC.TL_messageActionChatJoinedByRequest) == false) goto L1129;
        if (UserObject.isUserSelf(r36) == true) goto L1123;
        this.messageText = replaceWithLink(LocaleController.getString(R.string.UserAcceptedToGroupAction), "un1", r8);
        goto L1538
    L1123:
        if (ChatObject.isChannelAndNotMegaGroup(this.messageOwner.peer_id.channel_id, this.currentAccount) == false) goto L1125;
        String r1127 = LocaleController.getString(R.string.RequestToJoinChannelApproved);
    L1126:
        this.messageText = r1127;
        goto L1538
    L1125:
        r1127 = LocaleController.getString(R.string.RequestToJoinGroupApproved);
        goto L1126
    L1129:
        if ((r231 instanceof TLRPC.TL_messageActionPaidMessagesPrice) == false) goto L1147;
        TLRPC.TL_messageActionPaidMessagesPrice r257 = (TLRPC.TL_messageActionPaidMessagesPrice) r231;
        if (ChatObject.isChannel(r282) == false) goto L1143;
        if (r282 == null) goto L1143;
        if (r282.broadcast == false) goto L1143;
        if ((r257.flags & 1) == 0) goto L1141;
        long r1128 = r257.stars;
        if (r1128 <= 0) goto L1140;
        this.messageText = replaceWithLink(LocaleController.formatPluralStringComma("PostSuggestionsPriceUpdated", (int) r1128), "un1", r8);
        goto L1538
    L1140:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.PostSuggestionsEnabledUpdated), "un1", r8);
        goto L1538
    L1141:
        this.messageText = replaceWithLink(LocaleController.getString(R.string.PostSuggestionsDisabledUpdated), "un1", r8);
    L1143:
        if (isOutOwner() == false) goto L1145;
        this.messageText = LocaleController.formatPluralStringComma("PaidMessagesPriceUpdatedOut", (int) r257.stars);
        goto L1538
    L1145:
        this.messageText = replaceWithLink(LocaleController.formatPluralStringComma("PaidMessagesPriceUpdated", (int) r257.stars), "un1", r8);
        goto L1538
    L1147:
        if ((r231 instanceof TLRPC.TL_messageActionPaidMessagesRefunded) == false) goto L1157;
        TLRPC.TL_messageActionPaidMessagesRefunded r258 = (TLRPC.TL_messageActionPaidMessagesRefunded) r231;
        if (isOutOwner() == false) goto L1155;
        TLRPC.Message r1129 = this.messageOwner;
        TLRPC.Peer r349 = r1129.saved_peer_id;
        if (r349 == null) goto L1153;
        TLRPC.User r1130 = getUser(r32, r34, DialogObject.getPeerDialogId(r349));
    L1154:
        this.messageText = replaceWithLink(LocaleController.formatPluralStringComma("PaidMessagesRefundedOut", (int) r258.stars), "un1", r1130);
        goto L1538
    L1153:
        r1130 = getUser(r32, r34, DialogObject.getPeerDialogId(r1129.peer_id));
        goto L1154
    L1155:
        this.messageText = replaceWithLink(LocaleController.formatPluralStringComma("PaidMessagesRefunded", (int) r258.stars), "un1", r8);
        goto L1538
    L1157:
        if ((r231 instanceof TLRPC.TL_messageActionTodoCompletions) == false) goto L1199;
        TLRPC.TL_messageActionTodoCompletions r259 = (TLRPC.TL_messageActionTodoCompletions) r231;
        boolean r1131 = r259.completed.isEmpty();
        if (r259.completed.isEmpty() == true) goto L1162;
        int r260 = r259.completed.get(0).intValue();
    L1165:
        MessageObject r350 = this.replyMessageObject;
        if (r350 != null) goto L1168;
    L1176:
        TLRPC.TodoItem r351 = null;
    L1177:
        if (r351 == null) goto L1179;
        Theme.createCommonChatResources();
        CharSequence r261 = formatTaskTitle(r351);
        if (isOutOwner() == false) goto L1194;
        if (r1131 == true) goto L1192;
        int r1132 = R.string.TodoTaskCompletedOut;
    L1193:
        this.messageText = LocaleController.formatSpannable(r1132, new Object[]{r261});
        goto L1538
    L1192:
        r1132 = R.string.TodoTaskNotCompletedOut;
        goto L1193
    L1194:
        if (r1131 == true) goto L1196;
        int r1133 = R.string.TodoTaskCompleted;
    L1197:
        this.messageText = replaceWithLink(LocaleController.formatSpannable(r1133, new Object[]{r261}), "un1", r8);
        goto L1538
    L1196:
        r1133 = R.string.TodoTaskNotCompleted;
        goto L1197
    L1179:
        if (isOutOwner() == false) goto L1184;
        if (r1131 == true) goto L1182;
        int r1134 = R.string.TodoTaskCompletedOutUnknown;
    L1183:
        this.messageText = LocaleController.getString(r1134);
        goto L1538
    L1182:
        r1134 = R.string.TodoTaskNotCompletedOutUnknown;
        goto L1183
    L1184:
        if (r1131 == true) goto L1186;
        int r1135 = R.string.TodoTaskCompletedUnknown;
    L1187:
        this.messageText = replaceWithLink(LocaleController.getString(r1135), "un1", r8);
        goto L1538
    L1186:
        r1135 = R.string.TodoTaskNotCompletedUnknown;
        goto L1187
    L1168:
        if ((getMedia(r350.messageOwner) instanceof TLRPC.TL_messageMediaToDo) == false) goto L1176;
        TLRPC.TL_messageMediaToDo r352 = (TLRPC.TL_messageMediaToDo) getMedia(this.replyMessageObject.messageOwner);
        int r417 = 0;
    L1171:
        if (r417 >= r352.todo.list.size()) goto L1176;
        TLRPC.TodoItem r54 = r352.todo.list.get(r417);
        if (r54.id == r260) goto L1174;
        r417 = r417 + 1;
        goto L1171
    L1174:
        r351 = r54;
        goto L1177
    L1162:
        if (r259.incompleted.isEmpty() == true) goto L1164;
        r260 = r259.incompleted.get(0).intValue();
        goto L1165
    L1164:
        r260 = 0;
        goto L1165
    L1199:
        if ((r231 instanceof TLRPC.TL_messageActionTodoAppendTasks) == false) goto L1227;
        TLRPC.TL_messageActionTodoAppendTasks r262 = (TLRPC.TL_messageActionTodoAppendTasks) r231;
        if (isOutOwner() == false) goto L1214;
        int r1136 = r262.list.size();
        ArrayList<TLRPC.TodoItem> r353 = r262.list;
        if (r1136 != 1) goto L1205;
        this.messageText = LocaleController.formatSpannable(R.string.TodoAddedOneTaskOut, new Object[]{formatTaskTitle(r353.get(0))});
        goto L1538
    L1205:
        int r1137 = r353.size();
        ArrayList<TLRPC.TodoItem> r354 = r262.list;
        if (r1137 != 2) goto L1208;
        this.messageText = LocaleController.formatSpannable(R.string.TodoAddedTwoTaskOut, new Object[]{formatTaskTitle(r354.get(0)), formatTaskTitle(r262.list.get(1))});
        goto L1538
    L1208:
        int r1138 = r354.size();
        ArrayList<TLRPC.TodoItem> r355 = r262.list;
        if (r1138 != 3) goto L1212;
        this.messageText = LocaleController.formatSpannable(R.string.TodoAddedThreeTaskOut, new Object[]{formatTaskTitle(r355.get(0)), formatTaskTitle(r262.list.get(1)), formatTaskTitle(r262.list.get(2))});
        goto L1538
    L1212:
        if (r355.size() < 4) goto L1538;
        this.messageText = LocaleController.formatPluralSpannable("TodoAddedMoreTasksOut", r262.list.size() - 3, new CharSequence[]{formatTaskTitle(r262.list.get(0)), formatTaskTitle(r262.list.get(1)), formatTaskTitle(r262.list.get(2))});
        goto L1538
    L1214:
        int r1139 = r262.list.size();
        ArrayList<TLRPC.TodoItem> r356 = r262.list;
        if (r1139 != 1) goto L1217;
        this.messageText = replaceWithLink(LocaleController.formatSpannable(R.string.TodoAddedOneTask, new Object[]{formatTaskTitle(r356.get(0))}), "un1", r8);
        goto L1538
    L1217:
        int r1140 = r356.size();
        ArrayList<TLRPC.TodoItem> r357 = r262.list;
        if (r1140 != 2) goto L1220;
        this.messageText = replaceWithLink(LocaleController.formatSpannable(R.string.TodoAddedTwoTask, new Object[]{formatTaskTitle(r357.get(0)), formatTaskTitle(r262.list.get(1))}), "un1", r8);
        goto L1538
    L1220:
        int r1141 = r357.size();
        ArrayList<TLRPC.TodoItem> r358 = r262.list;
        if (r1141 != 3) goto L1224;
        this.messageText = replaceWithLink(LocaleController.formatSpannable(R.string.TodoAddedThreeTask, new Object[]{formatTaskTitle(r358.get(0)), formatTaskTitle(r262.list.get(1)), formatTaskTitle(r262.list.get(2))}), "un1", r8);
        goto L1538
    L1224:
        if (r358.size() < 4) goto L1538;
        this.messageText = replaceWithLink(LocaleController.formatPluralSpannable("TodoAddedMoreTasks", r262.list.size() - 3, new CharSequence[]{formatTaskTitle(r262.list.get(0)), formatTaskTitle(r262.list.get(1)), formatTaskTitle(r262.list.get(2))}), "un1", r8);
        goto L1538
    L1227:
        if ((r231 instanceof TLRPC.TL_messageActionSuggestedPostApproval) == false) goto L1240;
        String r1142 = ForumUtilities.getMonoForumTitle(this.currentAccount, DialogObject.getPeerDialogId(r167.peer_id), true);
        if (r1142 != null) goto L1238;
        TLRPC.Chat r1143 = getChat(r33, r35, -DialogObject.getPeerDialogId(this.messageOwner.peer_id));
        if (r1143 == null) goto L1237;
        long r263 = r1143.linked_monoforum_id;
        if (r263 == 0) goto L1237;
        TLRPC.Chat r264 = getChat(r33, r35, r263);
        if (r264 == null) goto L1237;
        r1143 = r264;
    L1237:
        r1142 = DialogObject.getDialogTitle(r1143);
    L1238:
        this.messageText = getActionSuggestionApprovalText(r1142, DialogObject.getName(getUser(r32, r34, DialogObject.getPeerDialogId(this.messageOwner.saved_peer_id))));
        goto L1538
    L1240:
        if ((r231 instanceof TLRPC.TL_messageActionStarGiftPurchaseOffer) == false) goto L1253;
        TLRPC.TL_messageActionStarGiftPurchaseOffer r265 = (TLRPC.TL_messageActionStarGiftPurchaseOffer) r231;
        AmountUtils$Amount r1144 = AmountUtils$Amount.ofSafe(r265.price);
        String r266 = r265.gift.title + " #" + LocaleController.formatNumber(r265.gift.num, ',');
        String r359 = DialogObject.getShortName(getUser(r32, r34, DialogObject.getPeerDialogId(this.messageOwner.peer_id)));
        SpannableStringBuilder r418 = new SpannableStringBuilder();
        if (r1144.currency != AmountUtils$Currency.STARS) goto L1248;
        if (isOut() == false) goto L1246;
        int r55 = R.string.GiftOfferOfferedTextStarsOut;
    L1251:
        r418.append(AndroidUtilities.replaceTags(LocaleController.formatString(r55, new Object[]{r359, r1144.asFormatString(), r266})));
        this.messageText = r418;
        goto L1538
    L1246:
        r55 = R.string.GiftOfferOfferedTextStars2;
        goto L1251
    L1248:
        if (isOut() == false) goto L1250;
        r55 = R.string.GiftOfferOfferedTextTONOut;
        goto L1251
    L1250:
        r55 = R.string.GiftOfferOfferedTextTON2;
        goto L1251
    L1253:
        if ((r231 instanceof TLRPC.TL_messageActionChangeCommunity) == false) goto L1269;
        TLRPC.TL_messageActionChangeCommunity r267 = (TLRPC.TL_messageActionChangeCommunity) r231;
        long r73 = DialogObject.getPeerDialogId(r167.peer_id);
        long r1145 = DialogObject.getPeerDialogId(this.messageOwner.from_id);
        if (r73 < 0) goto L1257;
    L1259:
        boolean r360 = false;
    L1260:
        if (r73 <= 0) goto L1262;
        boolean r1146 = true;
    L1263:
        String r268 = DialogObject.getShortName(getChat(r33, r35, r267.community_id));
        if (r1145 <= 0) goto L1266;
        String r419 = DialogObject.getShortName(getUser(r32, r34, r1145));
    L1267:
        SpannableStringBuilder r56 = new SpannableStringBuilder();
        r56.append(CommunityUtils.buildServiceMessageText(this, r268, r419, r360, r1146));
        this.messageText = r56;
        goto L1538
    L1266:
        r419 = DialogObject.getShortName(getChat(r33, r35, -r1145));
        goto L1267
    L1262:
        r1146 = false;
        goto L1263
    L1257:
        if (ChatObject.isChannelAndNotMegaGroup(getChat(r33, r35, -r73)) == false) goto L1259;
        r360 = true;
        goto L1260
    L1269:
        if ((r231 instanceof TLRPC.TL_messageActionStarGiftPurchaseOfferDeclined) == false) goto L1294;
        TLRPC.TL_messageActionStarGiftPurchaseOfferDeclined r269 = (TLRPC.TL_messageActionStarGiftPurchaseOfferDeclined) r231;
        AmountUtils$Amount r1147 = AmountUtils$Amount.ofSafe(r269.price);
        String r361 = r269.gift.title + " #" + LocaleController.formatNumber(r269.gift.num, ',');
        String r420 = DialogObject.getShortName(getUser(r32, r34, DialogObject.getPeerDialogId(this.messageOwner.peer_id)));
        String r57 = r1147.asFormatString();
        boolean r270 = r269.expired;
        AmountUtils$Currency r1148 = r1147.currency;
        if (r270 == false) goto L1283;
        if (r1148 != AmountUtils$Currency.STARS) goto L1279;
        if (isOut() == false) goto L1277;
        int r1149 = R.string.GiftOfferOfferedTextStarsExpiredOut;
    L1292:
        this.messageText = AndroidUtilities.replaceTags(LocaleController.formatString(r1149, new Object[]{r420, r57, r361}));
        goto L1538
    L1277:
        r1149 = R.string.GiftOfferOfferedTextStarsExpired;
        goto L1292
    L1279:
        if (isOut() == false) goto L1281;
        r1149 = R.string.GiftOfferOfferedTextTONExpiredOut;
        goto L1292
    L1281:
        r1149 = R.string.GiftOfferOfferedTextTONExpired;
        goto L1292
    L1283:
        if (r1148 != AmountUtils$Currency.STARS) goto L1289;
        if (isOut() == false) goto L1287;
        r1149 = R.string.GiftOfferOfferedTextStarsRejectedOut;
        goto L1292
    L1287:
        r1149 = R.string.GiftOfferOfferedTextStarsRejected;
        goto L1292
    L1289:
        if (isOut() == false) goto L1291;
        r1149 = R.string.GiftOfferOfferedTextTONRejectedOut;
        goto L1292
    L1291:
        r1149 = R.string.GiftOfferOfferedTextTONRejected;
        goto L1292
    L1294:
        if ((r231 instanceof TLRPC.TL_messageActionPollAppendAnswer) == false) goto L1300;
        TLRPC.TL_messageActionPollAppendAnswer r271 = (TLRPC.TL_messageActionPollAppendAnswer) r231;
        if (isOut() == false) goto L1298;
        this.messageText = LocaleController.formatSpannable(R.string.PollAddingActionYou, new Object[]{formatTextWithEntities(r271.answer.text, isOutOwner())});
        goto L1538
    L1298:
        this.messageText = LocaleController.formatSpannable(R.string.PollAddingActionOther, new Object[]{DialogObject.getShortName(getUser(r32, r34, DialogObject.getPeerDialogId(this.messageOwner.from_id))), formatTextWithEntities(r271.answer.text, isOutOwner())});
        goto L1538
    L1300:
        if ((r231 instanceof TLRPC.TL_messageActionPollDeleteAnswer) == false) goto L1306;
        TLRPC.TL_messageActionPollDeleteAnswer r272 = (TLRPC.TL_messageActionPollDeleteAnswer) r231;
        if (isOut() == false) goto L1304;
        this.messageText = LocaleController.formatSpannable(R.string.PollRemovedActionYou, new Object[]{formatTextWithEntities(r272.answer.text, isOutOwner())});
        goto L1538
    L1304:
        this.messageText = LocaleController.formatSpannable(R.string.PollRemovedActionOther, new Object[]{DialogObject.getShortName(getUser(r32, r34, DialogObject.getPeerDialogId(this.messageOwner.from_id))), formatTextWithEntities(r272.answer.text, isOutOwner())});
        goto L1538
    L1306:
        if ((r231 instanceof TLRPC.TL_messageActionNoForwardsToggle) == false) goto L1326;
        TLRPC.TL_messageActionNoForwardsToggle r273 = (TLRPC.TL_messageActionNoForwardsToggle) r231;
        boolean r1150 = r273.new_value;
        if (r1150 != r273.prev_value) goto L1314;
        if (r1150 == false) goto L1311;
        int r1151 = R.string.DisableSharingActionStillDisabled;
    L1312:
        this.messageText = LocaleController.getString(r1151);
        goto L1538
    L1311:
        r1151 = R.string.DisableSharingActionStillEnabled;
        goto L1312
    L1314:
        if (isOut() == true) goto L1316;
        String r1152 = DialogObject.getShortName(getUser(r32, r34, DialogObject.getPeerDialogId(this.messageOwner.peer_id)));
        if (r273.new_value == false) goto L1323;
        int r274 = R.string.DisableSharingActionOther;
    L1324:
        this.messageText = LocaleController.formatString(r274, new Object[]{r1152});
        goto L1538
    L1323:
        r274 = R.string.EnableSharingActionOther;
        goto L1324
    L1316:
        if (r273.new_value == false) goto L1318;
        int r1153 = R.string.DisableSharingActionYou;
    L1319:
        this.messageText = LocaleController.getString(r1153);
        goto L1538
    L1318:
        r1153 = R.string.EnableSharingActionYou;
        goto L1319
    L1326:
        if ((r231 instanceof TLRPC.TL_messageActionNoForwardsRequest) == false) goto L1538;
        String r1154 = DialogObject.getShortName(getUser(r32, r34, DialogObject.getPeerDialogId(r167.peer_id)));
        if (((TLRPC.TL_messageActionNoForwardsRequest) r231).new_value == false) goto L1335;
        if (isOut() == false) goto L1332;
        CharSequence r1155 = LocaleController.getString(R.string.SharingOfferDisableHeaderShortYou);
    L1333:
        this.messageText = r1155;
        goto L1538
    L1332:
        r1155 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.SharingOfferDisableHeaderShortOther, new Object[]{r1154}));
        goto L1333
    L1335:
        if (isOut() == false) goto L1337;
        CharSequence r1156 = LocaleController.getString(R.string.SharingOfferEnableHeaderShortYou);
    L1338:
        this.messageText = r1156;
        goto L1538
    L1337:
        r1156 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.SharingOfferEnableHeaderShortOther, new Object[]{r1154}));
        goto L1338
    L696:
        if (r231.ttl == 0) goto L702;
        if (isOut() == false) goto L700;
        this.messageText = LocaleController.formatString(R.string.MessageLifetimeChangedOutgoing, new Object[]{LocaleController.formatTTLString(this.messageOwner.action.ttl)});
        goto L200
    L700:
        this.messageText = LocaleController.formatString(R.string.MessageLifetimeChanged, new Object[]{UserObject.getFirstName(r36), LocaleController.formatTTLString(this.messageOwner.action.ttl)});
        goto L200
    L702:
        if (isOut() == false) goto L704;
        this.messageText = LocaleController.getString(R.string.MessageLifetimeYouRemoved);
        goto L200
    L704:
        this.messageText = LocaleController.formatString(R.string.MessageLifetimeRemoved, new Object[]{UserObject.getFirstName(r36)});
        goto L200
    L602:
        if ((r8 instanceof TLRPC.User) == false) goto L607;
        if (((TLRPC.User) r8).self == false) goto L607;
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionGiftOutbound)), "un1", getUser(r32, r34, this.messageOwner.peer_id.user_id));
    L610:
        int r275 = this.messageText.toString().indexOf("un2");
        if (r275 == (-1)) goto L616;
        SpannableStringBuilder r362 = SpannableStringBuilder.valueOf(this.messageText);
        BillingController r421 = BillingController.getInstance();
        TLRPC.MessageAction r58 = this.messageOwner.action;
        String r422 = r421.formatCurrency(r58.amount, r58.currency);
        if ((this.messageOwner.action.flags & 1) == 0) goto L615;
        r422 = String.format("%.2f", new Object[]{Double.valueOf(this.messageOwner.action.cryptoAmount * Math.pow(10.0d, -9.0d))}) + " " + this.messageOwner.action.cryptoCurrency + " (~ " + r422 + ")";
    L615:
        this.messageText = r362.replace(r275, r275 + 3, r422);
    L616:
        this.messageText = StarsIntroActivity.replaceStars(this.messageText);
    L607:
        if ((this.messageOwner.action instanceof TLRPC.TL_messageActionGiftTon) == false) goto L609;
        this.messageText = AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionGiftTonInbound));
        goto L610
    L609:
        this.messageText = replaceWithLink(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ActionGiftInbound)), "un1", r8);
        goto L610
    L597:
        if (((TLRPC.TL_messageActionGiftCode) r1112).boost_peer == null) goto L599;
        this.messageText = LocaleController.getString(R.string.BoostingReceivedGiftNoName);
        goto L200
    L1341:
        r9 = _UrlKt.FRAGMENT_ENCODE_SET;
        TLRPC.Chat r1215 = r6;
        this.isRestrictedMessage = false;
        String r1157 = MessagesController.getInstance(this.currentAccount).getRestrictionReason(this.messageOwner.restriction_reason);
        if (TextUtils.isEmpty(r1157) == true) goto L1344;
        this.messageText = r1157;
        this.isRestrictedMessage = true;
        goto L1538
    L1344:
        TL_iv.RichMessage r1158 = this.messageOwner.rich_message;
        if (r1158 == null) goto L1348;
        CharSequence r1159 = formatRichMessage(r1158, isOutOwner());
        this.messageText = r1159;
        this.messageText = AndroidUtilities.replaceNewLines(r1159);
        goto L1538
    L1348:
        if (isMediaEmpty() == false) goto L1350;
    L1527:
        String r1160 = this.messageOwner.message;
        if (r1160 != null) goto L1543;
        this.messageText = r1160;
        goto L1538
    L1543:
    L1535:
        this.messageText = this.messageOwner.message;
        goto L1538
    L1531:
        if (r1160.length() <= 200) goto L1533;
        this.messageText = AndroidUtilities.BAD_CHARS_MESSAGE_LONG_PATTERN.matcher(this.messageOwner.message).replaceAll("\u200c");     // Catch: Throwable -> L1535
    L1542:
        goto L1538
    L1533:
        this.messageText = AndroidUtilities.BAD_CHARS_MESSAGE_PATTERN.matcher(this.messageOwner.message).replaceAll("\u200c");     // Catch: Throwable -> L1535
        goto L1542
    L1350:
        if (isSponsored() == true) goto L1527;
        boolean r1161 = getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGiveaway;
        TLRPC.Message r276 = this.messageOwner;
        if (r1161 == false) goto L1364;
        TLRPC.MessageFwdHeader r1162 = r276.fwd_from;
        if (r1162 == null) goto L1358;
        TLRPC.Peer r1163 = r1162.from_id;
        if ((r1163 instanceof TLRPC.TL_peerChannel) == false) goto L1358;
        boolean r1164 = ChatObject.isChannelAndNotMegaGroup(getChat(r33, r35, r1163.channel_id));
    L1359:
        if (r1164 == false) goto L1361;
        int r1165 = R.string.BoostingGiveawayChannelStarted;
    L1362:
        this.messageText = LocaleController.getString(r1165);
        goto L1538
    L1361:
        r1165 = R.string.BoostingGiveawayGroupStarted;
    L1358:
        r1164 = ChatObject.isChannelAndNotMegaGroup(r1215);
        goto L1359
    L1364:
        if ((getMedia(r276) instanceof TLRPC.TL_messageMediaGiveawayResults) == false) goto L1366;
        this.messageText = LocaleController.getString(R.string.BoostingGiveawayResults);
        goto L1538
    L1366:
        boolean r1166 = getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaStory;
        TLRPC.Message r277 = this.messageOwner;
        if (r1166 == false) goto L1381;
        if (getMedia(r277).via_mention == false) goto L1379;
        TLRPC.User r1167 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(getMedia(this.messageOwner).user_id));
        if (r1167 == null) goto L1375;
        String r1168 = UserObject.getPublicUsername(r1167);
        if (r1168 == null) goto L1375;
        String r363 = MessagesController.getInstance(this.currentAccount).linkPrefix + "/" + r1168 + "/s/" + getMedia(this.messageOwner).id;
    L1376:
        if (r363 == null) goto L1378;
        SpannableString r1169 = new SpannableString(r363);
        this.messageText = r1169;
        r1169.setSpan(new URLSpanReplacement("https://".concat(r363), new TextStyleSpan.TextStyleRun()), 0, this.messageText.length(), 33);
        goto L1538
    L1378:
        this.messageText = r9;
    L1375:
        r363 = null;
        goto L1376
    L1379:
        this.messageText = LocaleController.getString(R.string.ForwardedStory);
        goto L1538
    L1381:
        if ((getMedia(r277) instanceof TLRPC.TL_messageMediaDice) == false) goto L1383;
        this.messageText = getDiceEmoji();
        goto L1538
    L1383:
        boolean r1170 = getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPoll;
        TLRPC.Message r278 = this.messageOwner;
        if (r1170 == false) goto L1390;
        if (((TLRPC.TL_messageMediaPoll) getMedia(r278)).poll.quiz == false) goto L1388;
        this.messageText = LocaleController.getString(R.string.QuizPoll);
        goto L1538
    L1388:
        this.messageText = LocaleController.getString(R.string.Poll);
        goto L1538
    L1390:
        if ((getMedia(r278) instanceof TLRPC.TL_messageMediaToDo) == false) goto L1400;
        TLRPC.TL_messageMediaToDo r1171 = (TLRPC.TL_messageMediaToDo) getMedia(this.messageOwner);
        TLRPC.TodoList r279 = r1171.todo;
        if (r279 == null) goto L1398;
        TLRPC.TL_textWithEntities r280 = r279.title;
        if (r280 == null) goto L1398;
        if (r280.text.isEmpty() == true) goto L1398;
        this.messageText = LocaleController.formatSpannable(R.string.MessageTodoName, new Object[]{formatTextWithEntities(r1171.todo.title, isOutOwner())});
    L1398:
        this.messageText = LocaleController.getString(R.string.MessageTodo);
        goto L1538
    L1400:
        if (isVoiceOnce() == false) goto L1403;
        this.messageText = LocaleController.getString(R.string.AttachOnceAudio);
        goto L1538
    L1403:
        if (isRoundOnce() == false) goto L1405;
        this.messageText = LocaleController.getString(R.string.AttachOnceRound);
        goto L1538
    L1405:
        boolean r1172 = getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPaidMedia;
        TLRPC.Message r281 = this.messageOwner;
        if (r1172 == false) goto L1439;
        TLRPC.TL_messageMediaPaidMedia r1173 = (TLRPC.TL_messageMediaPaidMedia) getMedia(r281);
        int r283 = r1173.extended_media.size();
        int r364 = 0;
        boolean r423 = false;
    L1408:
        if (r364 >= r283) goto L1427;
        TLRPC.MessageExtendedMedia r59 = r1173.extended_media.get(r364);
        if ((r59 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L1419;
        TLRPC.MessageMedia r424 = ((TLRPC.TL_messageExtendedMedia) r59).media;
        if ((r424 instanceof TLRPC.TL_messageMediaDocument) == true) goto L1414;
    L1416:
        r423 = false;
    L1424:
        if (r423 == true) goto L1427;
        r364 = r364 + 1;
        goto L1408
    L1414:
        if (isVideoDocument(r424.document) == false) goto L1416;
        r423 = true;
        goto L1424
    L1419:
        if ((r59 instanceof TLRPC.TL_messageExtendedMediaPreview) == false) goto L1424;
        if ((((TLRPC.TL_messageExtendedMediaPreview) r59).flags & 4) == 0) goto L1423;
        r423 = true;
        goto L1424
    L1423:
        r423 = false;
    L1427:
        if (r283 != 1) goto L1432;
        if (r423 == false) goto L1430;
        int r1174 = R.string.AttachVideo;
    L1431:
        String r1175 = LocaleController.getString(r1174);
    L1437:
        this.messageText = r1175;
        this.messageText = StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.AttachPaidMedia, new Object[]{r1175}));
        goto L1538
    L1430:
        r1174 = R.string.AttachPhoto;
        goto L1431
    L1432:
        if (r423 == false) goto L1435;
        String r1176 = "Media";
    L1436:
        r1175 = LocaleController.formatPluralString(r1176, r283, new Object[0]);
        goto L1437
    L1435:
        r1176 = "Photos";
        goto L1436
    L1439:
        if ((getMedia(r281) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L1453;
        if (getMedia(this.messageOwner).ttl_seconds == 0) goto L1446;
        if ((this.messageOwner instanceof TLRPC.TL_message_secret) == true) goto L1446;
        this.messageText = LocaleController.getString(R.string.AttachDestructingPhoto);
    L1446:
        if (getGroupId() == 0) goto L1449;
        this.messageText = LocaleController.getString(R.string.Album);
        goto L1538
    L1449:
        if (isLivePhoto() == false) goto L1451;
        this.messageText = LocaleController.getString(R.string.AttachLivePhoto);
        goto L1538
    L1451:
        this.messageText = LocaleController.getString(R.string.AttachPhoto);
        goto L1538
    L1453:
        if (isVideo() == true) goto L1516;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDocument) == false) goto L1464;
        if ((getDocument() instanceof TLRPC.TL_documentEmpty) == true) goto L1461;
        if (getDocument() != null) goto L1464;
    L1461:
        if (getMedia(this.messageOwner).ttl_seconds != 0) goto L1516;
    L1464:
        if (isVoice() == false) goto L1467;
        this.messageText = LocaleController.getString(R.string.AttachAudio);
        goto L1538
    L1467:
        if (isRoundVideo() == false) goto L1470;
        this.messageText = LocaleController.getString(R.string.AttachRound);
        goto L1538
    L1470:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGeo) == false) goto L1472;
    L1514:
        this.messageText = LocaleController.getString(R.string.AttachLocation);
        goto L1538
    L1472:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaVenue) == true) goto L1514;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L1478;
        this.messageText = LocaleController.getString(R.string.AttachLiveLocation);
        goto L1538
    L1478:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaContact) == false) goto L1482;
        this.messageText = LocaleController.getString(R.string.AttachContact);
        if (TextUtils.isEmpty(getMedia(this.messageOwner).vcard) == true) goto L1538;
        this.vCardData = VCardData.parse(getMedia(this.messageOwner).vcard);
        goto L1538
    L1482:
        boolean r1177 = getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGame;
        TLRPC.Message r284 = this.messageOwner;
        if (r1177 == false) goto L1485;
        this.messageText = r284.message;
        goto L1538
    L1485:
        boolean r1178 = getMedia(r284) instanceof TLRPC.TL_messageMediaInvoice;
        TLRPC.Message r285 = this.messageOwner;
        if (r1178 == false) goto L1489;
        this.messageText = getMedia(r285).description;
        goto L1538
    L1489:
        if ((getMedia(r285) instanceof TLRPC.TL_messageMediaUnsupported) == false) goto L1492;
        this.messageText = LocaleController.getString(R.string.UnsupportedMedia2);
        goto L1538
    L1492:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDocument) == false) goto L1538;
        if (isSticker() == false) goto L1496;
    L1508:
        String r1179 = getStickerChar();
        if (r1179 != null) goto L1511;
    L1513:
        this.messageText = LocaleController.getString(R.string.AttachSticker);
        goto L1538
    L1511:
        if (r1179.length() <= 0) goto L1513;
        this.messageText = String.format("%s %s", new Object[]{r1179, LocaleController.getString(R.string.AttachSticker)});
        goto L1538
    L1496:
        if (isAnimatedStickerDocument(getDocument(), true) == true) goto L1508;
        if (isMusic() == false) goto L1502;
        this.messageText = LocaleController.getString(R.string.AttachMusic);
        goto L1538
    L1502:
        if (isGif() == false) goto L1504;
        this.messageText = LocaleController.getString(R.string.AttachGif);
        goto L1538
    L1504:
        String r1180 = FileLoader.getDocumentFileName(getDocument());
        if (TextUtils.isEmpty(r1180) == true) goto L1507;
        this.messageText = r1180;
        goto L1538
    L1507:
        this.messageText = LocaleController.getString(R.string.AttachDocument);
    L1516:
        if (getMedia(this.messageOwner).ttl_seconds == 0) goto L1526;
        TLRPC.Message r1181 = this.messageOwner;
        if ((r1181 instanceof TLRPC.TL_message_secret) == true) goto L1526;
        if (getMedia(r1181).voice == false) goto L1523;
        this.messageText = LocaleController.getString(R.string.AttachVoiceExpired);
        goto L1538
    L1523:
        if (getMedia(this.messageOwner).round == false) goto L1525;
        this.messageText = LocaleController.getString(R.string.AttachRoundExpired);
        goto L1538
    L1525:
        this.messageText = LocaleController.getString(R.string.AttachDestructingVideo);
    L1526:
        this.messageText = LocaleController.getString(R.string.AttachVideo);
        goto L1538
    L12:
        r8 = r6;
        goto L13
    L7:
        if ((r3 instanceof TLRPC.TL_peerChannel) == false) goto L9;
        r6 = getChat(r33, r35, r3.channel_id);
        r36 = null;
        goto L10
    L9:
        r36 = null;
        goto L5
    }

    private static void updatePhotoSizeLocations(ArrayList<TLRPC.PhotoSize> r9, List<TLRPC.PhotoSize> r10) {
        int r0 = r9.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L20;
        TLRPC.PhotoSize r3 = r9.get(r2);
        if (r3 == null) goto L19;
        int r4 = r10.size();
        int r5 = 0;
    L8:
        if (r5 >= r4) goto L19;
        TLRPC.PhotoSize r6 = r10.get(r5);
        if ((r6 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L18;
        if ((r6 instanceof TLRPC.TL_photoCachedSize) == true) goto L18;
        if (r6 == null) goto L18;
        if (r6.type.equals(r3.type) == false) goto L18;
        r3.location = r6.location;
    L18:
        r5 = r5 + 1;
    L19:
        r2 = r2 + 1;
        goto L3
    }

    public static void updatePollResults(TLRPC.TL_messageMediaPoll r13, TLRPC.PollResults r14) {
        if (r13 == null) goto L80;
        if (r14 == null) goto L82;
        int r0 = r14.flags & 2;
        boolean r2 = r14.min;
        if (r0 != 0) goto L8;
        if (r2 == true) goto L59;
        TLRPC.Poll r02 = r13.poll;
        if (r02 == null) goto L59;
        if (r02.hide_results_until_close == false) goto L59;
        ArrayList<TLRPC.PollAnswerVoters> r03 = r14.results;
        if (r03 != null) goto L56;
    L57:
        r13.results.results = new ArrayList();
        TLRPC.PollResults r04 = r13.results;
        r04.flags = BitwiseUtils.setFlag(r04.flags, 2, false);
        goto L59
    L56:
        if (r03.isEmpty() == true) goto L57;
    L59:
        if ((r14.flags & 4) == 0) goto L62;
        TLRPC.PollResults r05 = r13.results;
        r05.total_voters = r14.total_voters;
        r05.flags |= 4;
    L62:
        if ((r14.flags & 8) == 0) goto L65;
        TLRPC.PollResults r06 = r13.results;
        r06.recent_voters = r14.recent_voters;
        r06.flags |= 8;
    L65:
        if ((r14.flags & 16) == 0) goto L68;
        TLRPC.PollResults r07 = r13.results;
        r07.solution = r14.solution;
        r07.solution_entities = r14.solution_entities;
        r07.flags |= 16;
    L68:
        if (r14.min == true) goto L81;
        TLRPC.PollResults r132 = r13.results;
        r132.has_unread_votes = r14.has_unread_votes;
        r132.can_view_stats = r14.can_view_stats;
        return;
    L81:
        return;
    L8:
        if (r2 == false) goto L22;
        ArrayList<TLRPC.PollAnswerVoters> r22 = r13.results.results;
        if (r22 == null) goto L22;
        int r23 = r22.size();
        ArrayList r5 = null;
        byte[] r6 = null;
        int r4 = 0;
    L12:
        if (r4 >= r23) goto L23;
        TLRPC.PollAnswerVoters r7 = r13.results.results.get(r4);
        if (r7.chosen == false) goto L19;
        if (r5 != null) goto L17;
        r5 = new ArrayList();
    L17:
        r5.add(r7.option);
    L19:
        if (r7.correct == false) goto L21;
        r6 = r7.option;
    L21:
        r4 = r4 + 1;
    L23:
        TLRPC.PollResults r24 = r13.results;
        ArrayList<TLRPC.PollAnswerVoters> r42 = r14.results;
        r24.results = r42;
        if (r5 != null) goto L26;
        if (r6 != null) goto L26;
    L47:
        TLRPC.PollResults r08 = r13.results;
        r08.flags = 2 | r08.flags;
    L26:
        int r25 = r42.size();
        int r43 = 0;
    L27:
        if (r43 >= r25) goto L47;
        TLRPC.PollAnswerVoters r72 = r13.results.results.get(r43);
        if (r5 == null) goto L39;
        int r9 = r5.size();
        int r10 = 0;
    L31:
        if (r10 >= r9) goto L37;
        if (Arrays.equals(r72.option, (byte[]) r5.get(r10)) == true) goto L34;
        r10 = r10 + 1;
        goto L31
    L34:
        r72.chosen = true;
        r5.remove(r10);
    L37:
        if (r5.isEmpty() == false) goto L39;
        r5 = null;
    L39:
        if (r6 != null) goto L41;
    L43:
        if (r5 != null) goto L46;
        if (r6 == null) goto L47;
    L46:
        r43 = r43 + 1;
        goto L27
    L41:
        if (Arrays.equals(r72.option, r6) == false) goto L43;
        r72.correct = true;
        r6 = null;
    L22:
        r5 = null;
        r6 = null;
        goto L23
    L82:
        return;
    }

    public static void updateReactions(TLRPC.Message r11, TLRPC.TL_messageReactions r12) {
        if (r11 == null) goto L34;
        if (r12 == null) goto L35;
        TLRPC.TL_messageReactions r0 = r11.reactions;
        if (r0 == null) goto L26;
        int r02 = r0.results.size();
        int r2 = 0;
        boolean r3 = false;
    L8:
        if (r2 >= r02) goto L26;
        TLRPC.ReactionCount r4 = r11.reactions.results.get(r2);
        int r5 = r12.results.size();
        int r6 = 0;
    L11:
        if (r6 >= r5) goto L23;
        TLRPC.ReactionCount r8 = r12.results.get(r6);
        if (ReactionsLayoutInBubble.equalsTLReaction(r4.reaction, r8.reaction) == false) goto L21;
        if (r3 == false) goto L16;
    L20:
        r8.lastDrawnPosition = r4.lastDrawnPosition;
        goto L21
    L16:
        if (r12.min == false) goto L20;
        if (r4.chosen == false) goto L20;
        r8.chosen = true;
        r3 = true;
    L21:
        r6 = r6 + 1;
        goto L11
    L23:
        if (r4.chosen == false) goto L25;
        r3 = true;
    L25:
        r2 = r2 + 1;
    L26:
        r11.reactions = r12;
        r11.flags |= 1048576;
        return;
    L35:
        return;
    }

    public static CharSequence userSpan() {
        return userSpan(0);
    }

    public static CharSequence userSpan(int r5) {
        if (userSpan != null) goto L5;
        userSpan = new CharSequence[2];
    L5:
        CharSequence[] r0 = userSpan;
        if (r0[r5] != null) goto L12;
        r0[r5] = new SpannableStringBuilder("u");
        ColoredImageSpan r02 = new ColoredImageSpan(R.drawable.mini_reply_user);
        r02.spaceScaleX = 0.9f;
        if (r5 != 0) goto L10;
        r02.translate(0.0f, AndroidUtilities.dp(1.0f));
    L10:
        ((SpannableStringBuilder) userSpan[r5]).setSpan(r02, 0, 1, 33);
    L12:
        return userSpan[r5];
    }

    public boolean addEntitiesToText(CharSequence r9, boolean r10, boolean r11) {
        if (r9 != null) goto L6;
        return false;
    L6:
        if (this.isRestrictedMessage == true) goto L12;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaUnsupported) == true) goto L12;
        return addEntitiesToText(r9, getEntities(), isOutOwner(), true, r10, r11);
    L12:
        ArrayList r1 = new ArrayList();
        TLRPC.TL_messageEntityItalic r92 = new TLRPC.TL_messageEntityItalic();
        r92.offset = 0;
        r92.length = r9.length();
        r1.add(r92);
        return addEntitiesToText(r9, r1, isOutOwner(), true, r10, r11);
    }

    public void addPaidReactions(int r10, boolean r11, long r12) {
        TLRPC.Message r0 = this.messageOwner;
        if (r0.reactions != null) goto L14;
        r0.reactions = new TLRPC.TL_messageReactions();
        TLRPC.Message r02 = this.messageOwner;
        TLRPC.TL_messageReactions r1 = r02.reactions;
        boolean r2 = false;
        if (getDialogId(r02) != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L7;
        boolean r03 = true;
    L8:
        r1.reactions_as_tags = r03;
        TLRPC.TL_messageReactions r04 = this.messageOwner.reactions;
        if (isFromGroup() == false) goto L11;
    L12:
        r2 = true;
    L13:
        r04.can_see_list = r2;
        goto L14
    L11:
        if (isFromUser() == false) goto L13;
    L7:
        r03 = false;
    L14:
        addPaidReactions(this.currentAccount, this.messageOwner.reactions, r10, r12, r11);
    }

    public void applyMediaExistanceFlags(int r4) {
        if (r4 != (-1)) goto L6;
        checkMediaExistance();
        return;
    L6:
        boolean r1 = false;
        if ((r4 & 1) == 0) goto L9;
        boolean r0 = true;
    L10:
        this.attachPathExists = r0;
        if ((r4 & 2) == 0) goto L13;
        r1 = true;
    L13:
        this.mediaExists = r1;
        return;
    L9:
        r0 = false;
        goto L10
    }

    public void applyNewText() {
        this.translated = false;
        this.summarized = false;
        applyNewText(this.messageOwner.message);
    }

    public void applyNewText(CharSequence r8) {
        if (TextUtils.isEmpty(r8) == false) goto L5;
        return;
    L5:
        int[] r1 = null;
        if (isFromUser() == false) goto L8;
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.messageOwner.from_id.user_id));
    L9:
        this.messageText = r8;
        ArrayList<TLRPC.MessageEntity> r82 = getEntities();
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGame) == false) goto L12;
        com.exteragram.messenger.utils.ui.TextPaint r2 = Theme.chat_msgGameTextPaint;
    L14:
        if (allowsBigEmoji() == false) goto L16;
        r1 = new int[1];
    L16:
        CharSequence r3 = Emoji.replaceEmoji(this.messageText, r2.getFontMetricsInt(), false, r1);
        this.messageText = r3;
        Spannable r83 = replaceAnimatedEmoji(r3, r82, r2.getFontMetricsInt());
        this.messageText = r83;
        if (r1 != null) goto L19;
    L21:
        checkEmojiOnly(r1);
        generateLayout(r0);
        setType();
        return;
    L19:
        if (r1[0] <= 1) goto L21;
        replaceEmojiToLottieFrame(r83, r1);
        goto L21
    L12:
        r2 = Theme.chat_msgTextPaint;
        goto L14
    L8:
        r0 = null;
        goto L9
    }

    public void applyQuickReply(String r3, int r4) {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r4 == 0) goto L8;
        r0.flags |= TLObject.FLAG_30;
        r0.quick_reply_shortcut_id = r4;
        TLRPC.TL_inputQuickReplyShortcutId r32 = new TLRPC.TL_inputQuickReplyShortcutId();
        r32.shortcut_id = r4;
        this.messageOwner.quick_reply_shortcut = r32;
        return;
    L8:
        if (r3 == null) goto L11;
        TLRPC.TL_inputQuickReplyShortcut r42 = new TLRPC.TL_inputQuickReplyShortcut();
        r42.shortcut = r3;
        this.messageOwner.quick_reply_shortcut = r42;
        return;
    L11:
        r0.flags &= -1073741825;
        r0.quick_reply_shortcut_id = 0;
        r0.quick_reply_shortcut = null;
    }

    public void applyTimestampsHighlightForReplyMsg() {
        applyTimestampsHighlightForReplyMsg(this.messageText);
    }

    public void applyTimestampsHighlightForReplyMsg(CharSequence r15) {
        MessageObject r0 = this.replyMessageObject;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.isYouTubeVideo() == false) goto L10;
        addUrlsByPattern(isOutOwner(), r15, false, 3, Integer.MAX_VALUE, false);
        return;
    L10:
        if (r0.isVideo() == false) goto L14;
        addUrlsByPattern(isOutOwner(), r15, false, 3, (int) r0.getDuration(), false);
        return;
    L14:
        if (r0.isMusic() == false) goto L16;
    L17:
        addUrlsByPattern(isOutOwner(), r15, false, 4, (int) r0.getDuration(), false);
    L19:
        if (r15 != this.messageText) goto L28;
        TLRPC.Message r152 = this.messageOwner;
        if (r152 == null) goto L29;
        TLRPC.MessageAction r153 = r152.action;
        if ((r153 instanceof TLRPC.TL_messageActionTodoCompletions) == false) goto L25;
    L26:
        updateMessageText();
        return;
    L25:
        if ((r153 instanceof TLRPC.TL_messageActionTodoAppendTasks) == true) goto L26;
        return;
    L29:
        return;
    L28:
        return;
    L16:
        if (r0.isVoice() == false) goto L19;
        goto L17
    }

    public boolean areTags() {
        TLRPC.Message r1 = this.messageOwner;
        if (r1 != null) goto L5;
        return false;
    L5:
        TLRPC.TL_messageReactions r12 = r1.reactions;
        if (r12 != null) goto L9;
        return false;
    L9:
        return r12.reactions_as_tags;
    }

    public boolean canAppendToTodo() {
        TLRPC.MessageMedia r0 = getMedia(this.messageOwner);
        if ((r0 instanceof TLRPC.TL_messageMediaToDo) == true) goto L6;
        return false;
    L6:
        if (isForwarded() == false) goto L8;
        return false;
    L8:
        TLRPC.TL_messageMediaToDo r02 = (TLRPC.TL_messageMediaToDo) r0;
        if (r02.todo.list.size() < MessagesController.getInstance(this.currentAccount).todoItemsMax) goto L12;
        return false;
    L12:
        if (isOutOwner() == true) goto L19;
        TLRPC.TodoList r4 = r02.todo;
        if (r4.others_can_complete == true) goto L16;
    L18:
        return false;
    L16:
        if (r4.others_can_append == false) goto L18;
        return true;
    L19:
        return true;
    }

    public boolean canBeSensitive() {
        if (this.messageOwner == null) goto L23;
        int r0 = this.type;
        if (r0 == 1) goto L15;
        if (r0 == 3) goto L15;
        if (r0 == 9) goto L15;
        if (r0 == 8) goto L15;
        if (r0 == 5) goto L15;
        return false;
    L15:
        if (this.sendPreview == false) goto L17;
        return false;
    L17:
        if (this.isRepostPreview == false) goto L19;
        return false;
    L19:
        if (isOutOwner() == false) goto L21;
        return false;
    L21:
        if (this.messageOwner.send_state != 0) goto L29;
        return true;
    L29:
        return false;
    L23:
        return false;
    }

    public boolean canCompleteTodo() {
        TLRPC.MessageMedia r0 = getMedia(this.messageOwner);
        if ((r0 instanceof TLRPC.TL_messageMediaToDo) == true) goto L6;
        return false;
    L6:
        if (isForwarded() == false) goto L8;
        return false;
    L8:
        TLRPC.TL_messageMediaToDo r02 = (TLRPC.TL_messageMediaToDo) r0;
        if (isOutOwner() == false) goto L11;
        return true;
    L11:
        if (r02.todo.others_can_complete == true) goto L16;
        return false;
    L16:
        return true;
    }

    public boolean canDeleteMessage(boolean r5, TLRPC.Chat r6) {
        if (isStory() == false) goto L9;
        TLRPC.Message r0 = this.messageOwner;
        if (r0 == null) goto L9;
        if (r0.dialog_id != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L9;
        return true;
    L9:
        if (this.eventId != 0) goto L15;
        if (this.sponsoredId != null) goto L15;
        if (canDeleteMessage(this.currentAccount, r5, this.messageOwner, r6) == false) goto L15;
        return true;
    L15:
        if (isEphemeral() == true) goto L21;
        return false;
    L21:
        return true;
    }

    public boolean canEditMedia() {
        if (isSecretMedia() == false) goto L5;
    L25:
        return false;
    L5:
        if (isEphemeralAndNotWelcome() == true) goto L25;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L11;
        return true;
    L11:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDocument) == false) goto L23;
        if (isVoice() == false) goto L15;
    L21:
        return false;
    L15:
        if (isSticker() == true) goto L21;
        if (isAnimatedSticker() == true) goto L21;
        if (isRoundVideo() == true) goto L21;
        return true;
    L23:
        if (isMediaEmpty() == false) goto L25;
        return true;
    }

    public boolean canEditMessage(TLRPC.Chat r3) {
        if (isEphemeralAndNotWelcome() == false) goto L5;
        return false;
    L5:
        if (canEditMessage(this.currentAccount, this.messageOwner, r3, this.scheduled) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean canEditMessageAnytime(TLRPC.Chat r2) {
        return canEditMessageAnytime(this.currentAccount, this.messageOwner, r2);
    }

    public boolean canEditMessageScheduleTime(TLRPC.Chat r2) {
        return canEditMessageScheduleTime(this.currentAccount, this.messageOwner, r2);
    }

    public boolean canForwardMessage() {
        if (isQuickReply() == false) goto L5;
        return false;
    L5:
        int r0 = this.type;
        if (r0 != 30) goto L8;
    L32:
        return false;
    L8:
        if (r0 == 31) goto L32;
        if (r0 == 32) goto L32;
        if (r0 == 33) goto L32;
        if (r0 == 35) goto L32;
        if (r0 == 37) goto L32;
        if ((this.messageOwner instanceof TLRPC.TL_message_secret) == true) goto L32;
        if (needDrawBluredPreview() == true) goto L32;
        if (isLiveLocation() == true) goto L32;
        if (this.type == 16) goto L32;
        if (isSponsored() == true) goto L32;
        if (this.messageOwner.noforwards == true) goto L32;
        return true;
    }

    public boolean canPreviewDocument() {
        return canPreviewDocument(getDocument());
    }

    public boolean canSetReaction() {
        if (isEphemeral() == false) goto L6;
        return false;
    L6:
        TLRPC.Message r1 = this.messageOwner;
        if ((r1 instanceof TLRPC.TL_messageService) == true) goto L9;
        return true;
    L9:
        return r1.reactions_are_possible;
    }

    public boolean canStreamVideo() {
        if (hasVideoQualities() == false) goto L5;
        return true;
    L5:
        TLRPC.Document r5 = getDocument();
        if (r5 != null) goto L8;
    L26:
        return false;
    L8:
        if ((r5 instanceof TLRPC.TL_documentEncrypted) == true) goto L26;
        if (SharedConfig.streamAllVideo == false) goto L13;
        return true;
    L13:
        int r2 = 0;
    L15:
        if (r2 >= r5.attributes.size()) goto L22;
        TLRPC.DocumentAttribute r3 = r5.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L19;
        r2 = r2 + 1;
        goto L15
    L19:
        return r3.supports_streaming;
    L22:
        if (SharedConfig.streamMkv == false) goto L26;
        if ("video/x-matroska".equals(r5.mime_type) == false) goto L26;
        return true;
    }

    public boolean canUnvote() {
        if (this.type == 17) goto L5;
        return false;
    L5:
        TLRPC.MessageMedia r3 = getMedia(this.messageOwner);
        if ((r3 instanceof TLRPC.TL_messageMediaPoll) == true) goto L9;
        return false;
    L9:
        return canUnvote((TLRPC.TL_messageMediaPoll) r3);
    }

    public boolean canViewThread() {
        if (this.messageOwner.action == null) goto L6;
        return false;
    L6:
        if (hasReplies() == true) goto L15;
        MessageObject r0 = this.replyMessageObject;
        if (r0 == null) goto L12;
        if (r0.messageOwner.replies == null) goto L12;
        return true;
    L12:
        if (getReplyTopMsgId() != 0) goto L18;
        return false;
    L18:
        return true;
    L15:
        return true;
    }

    public void checkBigAnimatedEmoji() {
        this.emojiAnimatedSticker = null;
        this.emojiAnimatedStickerId = null;
        if (this.emojiOnlyCount != 1) goto L56;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L56;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaInvoice) == true) goto L56;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaEmpty) == false) goto L11;
    L12:
        TLRPC.Message r1 = this.messageOwner;
        if (r1.grouped_id != 0) goto L56;
        if (r1.entities.isEmpty() == false) goto L43;
        CharSequence r12 = this.messageText;
        int r2 = TextUtils.indexOf(r12, "🏻");
        if (r2 < 0) goto L19;
        this.emojiAnimatedStickerColor = "_c1";
        r12 = r12.subSequence(0, r2);
    L33:
        if (TextUtils.isEmpty(this.emojiAnimatedStickerColor) == true) goto L38;
        int r22 = r2 + 2;
        if (r22 >= this.messageText.length()) goto L38;
        StringBuilder r3 = new StringBuilder();
        r3.append(r12.toString());
        CharSequence r13 = this.messageText;
        r3.append(r13.subSequence(r22, r13.length()));
        r12 = r3.toString();
    L38:
        if (TextUtils.isEmpty(this.emojiAnimatedStickerColor) == false) goto L40;
    L41:
        this.emojiAnimatedSticker = MediaDataController.getInstance(this.currentAccount).getEmojiAnimatedSticker(r12);
        goto L56
    L40:
        if (EmojiData.emojiColoredMap.contains(r12.toString()) == false) goto L56;
    L19:
        r2 = TextUtils.indexOf(r12, "🏼");
        if (r2 < 0) goto L22;
        this.emojiAnimatedStickerColor = "_c2";
        r12 = r12.subSequence(0, r2);
        goto L33
    L22:
        r2 = TextUtils.indexOf(r12, "🏽");
        if (r2 < 0) goto L25;
        this.emojiAnimatedStickerColor = "_c3";
        r12 = r12.subSequence(0, r2);
        goto L33
    L25:
        r2 = TextUtils.indexOf(r12, "🏾");
        if (r2 < 0) goto L28;
        this.emojiAnimatedStickerColor = "_c4";
        r12 = r12.subSequence(0, r2);
        goto L33
    L28:
        r2 = TextUtils.indexOf(r12, "🏿");
        if (r2 < 0) goto L31;
        this.emojiAnimatedStickerColor = "_c5";
        r12 = r12.subSequence(0, r2);
        goto L33
    L31:
        this.emojiAnimatedStickerColor = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L33
    L43:
        if (this.messageOwner.entities.size() != 1) goto L56;
        if ((this.messageOwner.entities.get(0) instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L56;
        Long r14 = Long.valueOf(((TLRPC.TL_messageEntityCustomEmoji) this.messageOwner.entities.get(0)).document_id);     // Catch: Exception -> L68
        this.emojiAnimatedStickerId = r14;     // Catch: Exception -> L68
        TLRPC.Document r15 = AnimatedEmojiDrawable.findDocument(this.currentAccount, r14.longValue());     // Catch: Exception -> L68
        this.emojiAnimatedSticker = r15;     // Catch: Exception -> L68
        if (r15 != null) goto L56;
        CharSequence r16 = this.messageText;     // Catch: Exception -> L68
        if ((r16 instanceof Spanned) == false) goto L56;
        AnimatedEmojiSpan[] r17 = (AnimatedEmojiSpan[]) ((Spanned) r16).getSpans(0, r16.length(), AnimatedEmojiSpan.class);     // Catch: Exception -> L68
        if (r17 == null) goto L56;
        if (r17.length != 1) goto L56;
        this.emojiAnimatedSticker = r17[0].document;     // Catch: Exception -> L68
        goto L56
    L11:
        if (getMedia(this.messageOwner) == null) goto L12;
    L56:
        if (this.emojiAnimatedSticker != null) goto L61;
        if (this.emojiAnimatedStickerId != null) goto L61;
        generateLayout(null);
        return;
    L61:
        if (isSticker() == false) goto L64;
        this.type = 13;
        return;
    L64:
        if (isAnimatedSticker() == false) goto L66;
        this.type = 15;
        return;
    L66:
        this.type = 1000;
    }

    public void checkForScam() {
    }

    public boolean checkLayout() {
        int r0 = this.type;
        if (r0 == 24) goto L5;
    L7:
        boolean r1 = false;
    L8:
        if (r0 == 0) goto L15;
        if (r0 == 19) goto L15;
        if (r0 == 36) goto L15;
        if (r1 == true) goto L15;
    L63:
        return false;
    L15:
        if (this.messageOwner.peer_id == null) goto L63;
        CharSequence r02 = this.messageText;
        if (r02 == null) goto L63;
        if (r02.length() != 0) goto L24;
        if (this.isBotPendingDraft == false) goto L63;
    L24:
        if (this.layoutCreated == false) goto L41;
        if (AndroidUtilities.isTablet() == false) goto L28;
        int r03 = AndroidUtilities.getMinTabletSide();
    L29:
        com.exteragram.messenger.utils.ui.TextPaint r12 = Theme.chat_msgTextPaint;
        if (r12 == null) goto L32;
        float r13 = r12.getTextSize();
    L34:
        if (Math.abs(this.generatedWithMinSize - r03) <= AndroidUtilities.dp(52.0f)) goto L36;
    L39:
        this.layoutCreated = false;
        goto L41
    L36:
        if (this.generatedWithDensity != AndroidUtilities.density) goto L39;
        if (this.generatedWithFontSize == r13) goto L41;
    L32:
        r13 = 0.0f;
        goto L34
    L28:
        r03 = AndroidUtilities.displaySize.x;
    L41:
        if (this.layoutCreated == true) goto L63;
        this.layoutCreated = true;
        if (isFromUser() == false) goto L45;
        TLRPC.User r04 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.messageOwner.from_id.user_id));
    L47:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGame) == false) goto L49;
        com.exteragram.messenger.utils.ui.TextPaint r4 = Theme.chat_msgGameTextPaint;
    L51:
        if (allowsBigEmoji() == false) goto L53;
        int[] r5 = new int[1];
    L54:
        CharSequence r6 = Emoji.replaceEmoji(this.messageText, r4.getFontMetricsInt(), false, r5);
        this.messageText = r6;
        Spannable r42 = replaceAnimatedEmoji(r6, r4.getFontMetricsInt());
        this.messageText = r42;
        if (r5 != null) goto L57;
    L59:
        checkEmojiOnly(r5);
        checkBigAnimatedEmoji();
        setType();
        generateLayout(r04);
        if (this.caption == null) goto L62;
        this.caption = null;
        generateCaption();
    L62:
        return true;
    L57:
        if (r5[0] <= 1) goto L59;
        replaceEmojiToLottieFrame(r42, r5);
        goto L59
    L53:
        r5 = null;
        goto L54
    L49:
        r4 = Theme.chat_msgTextPaint;
        goto L51
    L45:
        r04 = null;
        goto L47
    L5:
        if (this.widePostsTextWidth <= 0) goto L7;
        r1 = true;
        goto L8
    }

    public void checkMediaExistance() {
        checkMediaExistance(true);
    }

    public void checkMediaExistance(boolean r8) {
        this.attachPathExists = false;
        this.mediaExists = false;
        int r1 = this.type;
        if (r1 != 20) goto L16;
        TLRPC.TL_messageExtendedMediaPreview r12 = (TLRPC.TL_messageExtendedMediaPreview) this.messageOwner.media.extended_media.get(0);
        if (r12.thumb == null) goto L26;
        File r2 = FileLoader.getInstance(this.currentAccount).getPathToAttach(r12.thumb, r8);
        if (this.mediaExists == true) goto L26;
        if (r2.exists() == false) goto L11;
    L14:
        boolean r13 = true;
    L15:
        this.mediaExists = r13;
        goto L26
    L11:
        if ((r12.thumb instanceof TLRPC.TL_photoStrippedSize) == true) goto L14;
        r13 = false;
    L26:
        if (this.mediaExists == false) goto L28;
    L29:
        int r14 = this.type;
        if (r14 != 3) goto L32;
    L39:
        String r15 = this.messageOwner.attachPath;
        if (r15 == null) goto L45;
        if (r15.length() <= 0) goto L45;
        this.attachPathExists = new File(this.messageOwner.attachPath).exists();
    L45:
        if (this.attachPathExists == true) goto L59;
        File r16 = FileLoader.getInstance(this.currentAccount).getPathToMessage(this.messageOwner, r8);
        if (this.type != 3) goto L51;
        if (needDrawBluredPreview() == false) goto L51;
    L54:
        this.mediaExists = new File(r16.getAbsolutePath() + ".enc").exists();
    L56:
        if (this.mediaExists == true) goto L59;
        this.mediaExists = r16.exists();
    L51:
        if (isVoiceOnce() == true) goto L54;
        if (isRoundOnce() == false) goto L56;
    L59:
        if (this.mediaExists == true) goto L81;
        TLRPC.Document r17 = getDocument();
        if (r17 == null) goto L66;
        boolean r3 = isWallpaper();
        int r5 = this.currentAccount;
        if (r3 == false) goto L65;
        this.mediaExists = FileLoader.getInstance(r5).getPathToAttach(r17, null, true, r8).exists();
        goto L81
    L65:
        this.mediaExists = FileLoader.getInstance(r5).getPathToAttach(r17, null, false, r8).exists();
        goto L81
    L66:
        int r18 = this.type;
        if (r18 != 0) goto L73;
        TLRPC.PhotoSize r0 = FileLoader.getClosestPhotoSizeWithSize(this.photoThumbs, AndroidUtilities.getPhotoSize());
        if (r0 == null) goto L83;
        this.mediaExists = FileLoader.getInstance(this.currentAccount).getPathToAttach(r0, null, true, r8).exists();
        goto L81
    L83:
        return;
    L73:
        if (r18 != 11) goto L81;
        TLRPC.Photo r19 = this.messageOwner.action.photo;
        if (r19 != null) goto L77;
        return;
    L77:
        if (r19.video_sizes.isEmpty() == true) goto L84;
        this.mediaExists = FileLoader.getInstance(this.currentAccount).getPathToAttach(r19.video_sizes.get(0), null, true, r8).exists();
        goto L81
    L84:
        return;
    L81:
        updateQualitiesCached(r8);
        return;
    L32:
        if (r14 == 9) goto L39;
        if (r14 == 2) goto L39;
        if (r14 == 14) goto L39;
        if (r14 != 5) goto L59;
    L28:
        if (this.type == 8) goto L39;
    L16:
        if (r1 != 1) goto L26;
        if (FileLoader.getClosestPhotoSizeWithSize(this.photoThumbs, AndroidUtilities.getPhotoSize(true)) == null) goto L26;
        File r110 = FileLoader.getInstance(this.currentAccount).getPathToMessage(this.messageOwner, r8);
        if (needDrawBluredPreview() == false) goto L23;
        this.mediaExists = new File(r110.getAbsolutePath() + ".enc").exists();
    L23:
        if (this.mediaExists == true) goto L26;
        this.mediaExists = r110.exists();
        goto L26
    }

    public void copyStableParams(MessageObject r8) {
        this.stableId = r8.stableId;
        TLRPC.Message r0 = this.messageOwner;
        r0.premiumEffectWasPlayed = r8.messageOwner.premiumEffectWasPlayed;
        this.forcePlayEffect = r8.forcePlayEffect;
        this.wasJustSent = r8.wasJustSent;
        TLRPC.TL_messageReactions r02 = r0.reactions;
        int r1 = 0;
        if (r02 == null) goto L23;
        ArrayList<TLRPC.ReactionCount> r03 = r02.results;
        if (r03 == null) goto L23;
        if (r03.isEmpty() == true) goto L23;
        TLRPC.TL_messageReactions r04 = r8.messageOwner.reactions;
        if (r04 == null) goto L23;
        if (r04.results == null) goto L23;
        int r05 = 0;
    L14:
        if (r05 >= this.messageOwner.reactions.results.size()) goto L23;
        TLRPC.ReactionCount r2 = this.messageOwner.reactions.results.get(r05);
        int r3 = 0;
    L17:
        if (r3 >= r8.messageOwner.reactions.results.size()) goto L22;
        TLRPC.ReactionCount r4 = r8.messageOwner.reactions.results.get(r3);
        if (ReactionsLayoutInBubble.equalsTLReaction(r2.reaction, r4.reaction) == false) goto L21;
        r2.lastDrawnPosition = r4.lastDrawnPosition;
    L21:
        r3 = r3 + 1;
        goto L17
    L22:
        r05 = r05 + 1;
    L23:
        boolean r06 = r8.isSpoilersRevealed;
        this.isSpoilersRevealed = r06;
        TLRPC.Message r22 = this.messageOwner;
        TLRPC.Message r82 = r8.messageOwner;
        r22.replyStory = r82.replyStory;
        TLRPC.MessageMedia r23 = r22.media;
        if (r23 == null) goto L28;
        TLRPC.MessageMedia r83 = r82.media;
        if (r83 == null) goto L28;
        r23.storyItem = r83.storyItem;
    L28:
        if (r06 == false) goto L34;
        ArrayList<TextLayoutBlock> r7 = this.textLayoutBlocks;
        if (r7 == null) goto L40;
        int r84 = r7.size();
    L32:
        if (r1 >= r84) goto L41;
        TextLayoutBlock r07 = r7.get(r1);
        r1 = r1 + 1;
        r07.spoilers.clear();
        goto L32
    L41:
        return;
    L40:
        return;
    }

    public void createMediaThumbs() {
        if (isStoryMedia() == false) goto L15;
        TL_stories.StoryItem r0 = getMedia(this.messageOwner).storyItem;
        if (r0 == null) goto L26;
        TLRPC.MessageMedia r02 = r0.media;
        if (r02 == null) goto L27;
        TLRPC.Document r03 = r02.document;
        if (r03 == null) goto L12;
        TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r03.thumbs, 50);
        this.mediaThumb = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r03.thumbs, 320, false, null, true), r03);
        this.mediaSmallThumb = ImageLocation.getForDocument(r3, r03);
        return;
    L12:
        TLRPC.PhotoSize r04 = FileLoader.getClosestPhotoSizeWithSize(this.photoThumbs, 50);
        this.mediaThumb = ImageLocation.getForObject(FileLoader.getClosestPhotoSizeWithSize(this.photoThumbs, 320, false, r04, true), this.photoThumbsObject);
        this.mediaSmallThumb = ImageLocation.getForObject(r04, this.photoThumbsObject);
        return;
    L27:
        return;
    L26:
        return;
    L15:
        if (isVideo() == false) goto L19;
        TLRPC.Document r05 = getDocument();
        TLRPC.PhotoSize r1 = FileLoader.getClosestPhotoSizeWithSize(r05.thumbs, 50);
        this.mediaThumb = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r05.thumbs, 320), r05);
        this.mediaSmallThumb = ImageLocation.getForDocument(r1, r05);
        return;
    L19:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPhoto) == true) goto L21;
        return;
    L21:
        if (getMedia(this.messageOwner).photo != null) goto L23;
        return;
    L23:
        if (this.photoThumbs.isEmpty() == true) goto L30;
        TLRPC.PhotoSize r06 = FileLoader.getClosestPhotoSizeWithSize(this.photoThumbs, 50);
        this.mediaThumb = ImageLocation.getForObject(FileLoader.getClosestPhotoSizeWithSize(this.photoThumbs, 320, false, r06, false), this.photoThumbsObject);
        this.mediaSmallThumb = ImageLocation.getForObject(r06, this.photoThumbsObject);
        return;
    }

    public void createMessageSendInfo() {
        createMessageSendInfo(false);
    }

    public void createMessageSendInfo(boolean r7) {
        VideoEditedInfo r0 = this.videoEditedInfo;
        boolean r1 = true;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        TLRPC.Message r3 = this.messageOwner;
        if (r3.message != null) goto L11;
        return;
    L11:
        if (r3.id < 0) goto L15;
        if (isEditing() == true) goto L15;
        return;
    L15:
        if (this.messageOwner.params == null) goto L67;
        TLRPC.MessageMedia r32 = getMedia(this);
        if (r7 == false) goto L19;
    L30:
        boolean r72 = true;
    L31:
        if (r72 == false) goto L33;
    L44:
        r1 = r72;
    L45:
        String r73 = this.messageOwner.params.get("ve");
        if (r73 == null) goto L52;
        if (r1 == false) goto L52;
        VideoEditedInfo r12 = new VideoEditedInfo();
        this.videoEditedInfo = r12;
        if (r12.parseString(r73) == true) goto L51;
        this.videoEditedInfo = null;
        goto L52
    L51:
        this.videoEditedInfo.roundVideo = isRoundVideo();
        this.videoEditedInfo.notReadyYet = r02;
    L52:
        TLRPC.Message r74 = this.messageOwner;
        if (r74.send_state != 3) goto L68;
        String r75 = r74.params.get("prevMedia");
        if (r75 == null) goto L69;
        SerializedData r03 = new SerializedData(Base64.decode(r75, 0));
        this.previousMedia = TLRPC.MessageMedia.TLdeserialize(r03, r03.readInt32(false), false);
        this.previousMessage = r03.readString(false);
        this.previousAttachPath = r03.readString(false);
        int r76 = r03.readInt32(false);
        this.previousMessageEntities = new ArrayList(r76);
        int r13 = 0;
    L57:
        if (r13 >= r76) goto L59;
        this.previousMessageEntities.add(TLRPC.MessageEntity.TLdeserialize(r03, r03.readInt32(false), false));
        r13 = r13 + 1;
        goto L57
    L59:
        r03.cleanup();
        return;
    L69:
        return;
    L68:
        return;
    L33:
        if ((r32 instanceof TLRPC.TL_messageMediaPoll) == false) goto L44;
        String r4 = this.messageOwner.params.get("pollMediaIndex");
        if (r4 != null) goto L62;
    L38:
        int r42 = -1;
    L39:
        TLRPC.MessageMedia r33 = PollAttachedMediaPack.getMedia((TLRPC.TL_messageMediaPoll) r32, r42);
        if (r33 == null) goto L44;
        if (isVideoDocument(r33.document) == false) goto L44;
    L62:
        r42 = Integer.parseInt(r4);     // Catch: Throwable -> L61
    L19:
        if (isVideo() == true) goto L30;
        if (isNewGif() == true) goto L30;
        if (isRoundVideo() == true) goto L30;
        if (isVideoSticker() == true) goto L30;
        if (isPaidVideo(r32) == true) goto L30;
        r72 = false;
        goto L31
    L67:
        return;
    L5:
        if (r0.notReadyYet == false) goto L7;
        r02 = true;
        goto L8
    }

    public void createStrippedThumb() {
        if (this.photoThumbs != null) goto L5;
        return;
    L5:
        if (canCreateStripedThubms() == true) goto L9;
        if (hasExtendedMediaPreview() == true) goto L9;
        return;
    L9:
        if (this.strippedThumb != null) goto L30;
        String r0 = "b";
        if (isRoundVideo() == false) goto L14;
        r0 = "br";
    L14:
        int r1 = this.photoThumbs.size();     // Catch: Throwable -> L21
        int r2 = 0;
    L15:
        if (r2 >= r1) goto L31;
        TLRPC.PhotoSize r3 = this.photoThumbs.get(r2);     // Catch: Throwable -> L21
        if ((r3 instanceof TLRPC.TL_photoStrippedSize) == true) goto L18;
        r2 = r2 + 1;
        goto L15
    L18:
        this.strippedThumb = new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), ImageLoader.getStrippedPhotoBitmap(r3.bytes, r0));     // Catch: Throwable -> L21
        return;
    L31:
        return;
    L21:
        th = move-exception;
        FileLog.e(th);
        return;
    }

    public boolean didSpoilLoginCode() {
        return this.spoiledLoginCode;
    }

    public boolean doesPaidReactionExist() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0.reactions != null) goto L16;
        r0.reactions = new TLRPC.TL_messageReactions();
        TLRPC.Message r02 = this.messageOwner;
        TLRPC.TL_messageReactions r1 = r02.reactions;
        if (getDialogId(r02) != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L7;
        boolean r03 = true;
    L8:
        r1.reactions_as_tags = r03;
        TLRPC.TL_messageReactions r04 = this.messageOwner.reactions;
        if (isFromGroup() == false) goto L11;
    L14:
        boolean r12 = true;
    L15:
        r04.can_see_list = r12;
        goto L16
    L11:
        if (isFromUser() == true) goto L14;
        r12 = false;
        goto L15
    L7:
        r03 = false;
    L16:
        int r05 = 0;
    L18:
        if (r05 >= this.messageOwner.reactions.results.size()) goto L23;
        if ((this.messageOwner.reactions.results.get(r05).reaction instanceof TLRPC.TL_reactionPaid) == true) goto L21;
        r05 = r05 + 1;
        goto L18
    L21:
        return true;
    L23:
        return false;
    }

    public boolean ensurePaidReactionsExist(boolean r9) {
        TLRPC.Message r0 = this.messageOwner;
        if (r0.reactions != null) goto L16;
        r0.reactions = new TLRPC.TL_messageReactions();
        TLRPC.Message r02 = this.messageOwner;
        TLRPC.TL_messageReactions r1 = r02.reactions;
        if (getDialogId(r02) != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L7;
        boolean r03 = true;
    L8:
        r1.reactions_as_tags = r03;
        TLRPC.TL_messageReactions r04 = this.messageOwner.reactions;
        if (isFromGroup() == false) goto L11;
    L14:
        boolean r12 = true;
    L15:
        r04.can_see_list = r12;
        goto L16
    L11:
        if (isFromUser() == true) goto L14;
        r12 = false;
        goto L15
    L7:
        r03 = false;
    L16:
        TLRPC.ReactionCount r05 = null;
        int r13 = 0;
    L18:
        if (r13 >= this.messageOwner.reactions.results.size()) goto L23;
        if ((this.messageOwner.reactions.results.get(r13).reaction instanceof TLRPC.TL_reactionPaid) == false) goto L22;
        r05 = this.messageOwner.reactions.results.get(r13);
    L22:
        r13 = r13 + 1;
        goto L18
    L23:
        if (r05 != null) goto L26;
        TLRPC.TL_reactionCount r06 = new TLRPC.TL_reactionCount();
        r06.reaction = new TLRPC.TL_reactionPaid();
        r06.count = 1;
        r06.chosen = r9;
        this.messageOwner.reactions.results.add(0, r06);
        return true;
    L26:
        return false;
    }

    public boolean equals(MessageObject r4) {
        if (r4 != null) goto L6;
        return false;
    L6:
        if (getId() == r4.getId()) goto L8;
    L11:
        return false;
    L8:
        if (getDialogId() != r4.getDialogId()) goto L11;
        return true;
    }

    public void expandChannelRecommendations(boolean r5) {
        SharedPreferences.Editor r0 = MessagesController.getInstance(this.currentAccount).getMainSettings().edit();
        String r1 = "c" + getDialogId() + "_rec";
        this.channelJoinedExpanded = r5;
        r0.putBoolean(r1, r5).apply();
    }

    public void generateCaption() {
        if (isRoundVideo() == false) goto L6;
        return;
    L6:
        if (this.caption != null) goto L8;
    L22:
        TLRPC.Message r0 = this.messageOwner;
        String r3 = r0.message;
        ArrayList<TLRPC.MessageEntity> r4 = r0.entities;
        if (this.type != 23) goto L32;
        TLRPC.MessageMedia r02 = r0.media;
        if (r02 == null) goto L29;
        TL_stories.StoryItem r03 = r02.storyItem;
        if (r03 == null) goto L29;
        r3 = r03.caption;
        r4 = r03.entities;
        boolean r04 = true;
    L34:
        TLRPC.Message r5 = this.messageOwner;
        TLRPC.TL_textWithEntities r6 = r5.translatedSummaryText;
        if (r6 != null) goto L37;
    L41:
        TLRPC.TL_textWithEntities r62 = r5.summaryText;
        if (r62 != null) goto L44;
    L46:
        TLRPC.TL_textWithEntities r52 = r5.translatedText;
        if (r52 != null) goto L49;
    L51:
        this.captionSummarized = false;
        this.captionTranslated = false;
    L53:
        if (isMediaEmpty() == false) goto L55;
        return;
    L55:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGame) == false) goto L57;
        return;
    L57:
        if (TextUtils.isEmpty(r3) == true) goto L109;
        CharSequence r32 = Emoji.replaceEmoji(r3, Theme.chat_msgTextPaint.getFontMetricsInt(), false);
        this.caption = r32;
        this.caption = replaceAnimatedEmoji(r32, r4, Theme.chat_msgTextPaint.getFontMetricsInt(), false);
        if (this.messageOwner.send_state == 0) goto L61;
        boolean r33 = false;
    L62:
        if (r04 == true) goto L86;
        if (r33 == false) goto L65;
    L85:
        boolean r34 = false;
    L87:
        if (r34 == true) goto L89;
    L95:
        addEntitiesToText(this.caption, r34);
        this.caption = FormattedDateSpan.applyFormatedDateEntities(this.caption);
        if (isVideo() == false) goto L99;
        addUrlsByPattern(isOutOwner(), this.caption, true, 3, (int) getDuration(), false);
    L103:
        applyTimestampsHighlightForReplyMsg(this.caption);
        return;
    L99:
        if (isMusic() == false) goto L101;
    L102:
        addUrlsByPattern(isOutOwner(), this.caption, true, 4, (int) getDuration(), false);
        goto L103
    L101:
        if (isVoice() == false) goto L103;
    L89:
        if (containsUrls(this.caption) == true) goto L105;
    L94:
        addUrlsByPattern(isOutOwner(), this.caption, true, 0, 0, true);
        goto L95
    L105:
        AndroidUtilities.addLinksSafe((Spannable) this.caption, 5, false, true);     // Catch: Exception -> L92
    L92:
        e = move-exception;
        FileLog.e(e);
        goto L94
    L65:
        if (this.eventId != 0) goto L86;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPhoto_old) == true) goto L86;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPhoto_layer68) == true) goto L86;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPhoto_layer74) == true) goto L86;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDocument_old) == true) goto L86;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDocument_layer68) == true) goto L86;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDocument_layer74) == true) goto L86;
        if (isOut() == false) goto L83;
        if (this.messageOwner.send_state != 0) goto L86;
    L83:
        if (this.messageOwner.id >= 0) goto L85;
    L86:
        r34 = true;
        goto L87
    L61:
        r33 = !r4.isEmpty();
        goto L62
    L109:
        return;
    L49:
        if (this.translated == false) goto L51;
        this.captionSummarized = false;
        this.captionTranslated = true;
        r3 = r52.text;
        r4 = r52.entities;
        goto L53
    L44:
        if (this.summarized == false) goto L46;
        this.captionSummarized = true;
        this.captionTranslated = false;
        r3 = r62.text;
        r4 = r62.entities;
        goto L53
    L37:
        if (this.summarized == false) goto L41;
        if (this.translated == false) goto L41;
        this.captionSummarized = true;
        this.captionTranslated = true;
        r3 = r6.text;
        r4 = r6.entities;
    L29:
        r4 = new ArrayList();
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L30:
        r04 = false;
        goto L34
    L32:
        if (hasExtendedMedia() == false) goto L30;
        TLRPC.Message r05 = this.messageOwner;
        r3 = r05.media.description;
        r05.message = r3;
        goto L30
    L8:
        if (this.translated == false) goto L16;
        TLRPC.Message r06 = this.messageOwner;
        if (r06.translatedText == null) goto L12;
    L15:
        boolean r07 = true;
    L18:
        if (r07 != this.captionTranslated) goto L22;
        if (this.summarized != this.captionSummarized) goto L22;
        return;
    L12:
        if (this.summarized == false) goto L16;
        if (r06.translatedSummaryText != null) goto L15;
    L16:
        r07 = false;
        goto L18
    }

    public void generateExplanation() {
        if (this.type == 17) goto L5;
        return;
    L5:
        TLRPC.MessageMedia r0 = getMedia(this.messageOwner);
        if ((r0 instanceof TLRPC.TL_messageMediaPoll) == false) goto L10;
        TLRPC.PollResults r02 = ((TLRPC.TL_messageMediaPoll) r0).results;
        if (r02 == null) goto L10;
        String r1 = r02.solution;
        ArrayList<TLRPC.MessageEntity> r4 = r02.solution_entities;
    L11:
        if (r1 == null) goto L14;
        CharSequence r03 = Emoji.replaceEmoji(r1, Theme.chat_explanationTextPaint.getFontMetricsInt(), false);
        this.quizExplanation = r03;
        Spannable r3 = replaceAnimatedEmoji(r03, r4, Theme.chat_explanationTextPaint.getFontMetricsInt(), false);
        this.quizExplanation = r3;
        addEntitiesToText(r3, r4, isOutOwner(), true, false, false);
        return;
    L14:
        this.quizExplanation = null;
        return;
    L10:
        r1 = null;
        r4 = null;
        goto L11
    }

    public void generateGameMessageText(TLRPC.User r9) {
        if (r9 == null) goto L4;
    L6:
        MessageObject r0 = this.replyMessageObject;
        if (r0 != null) goto L9;
    L13:
        TLRPC.TL_game r02 = null;
    L15:
        if (r02 != null) goto L23;
        if (r9 != null) goto L18;
    L21:
        this.messageText = replaceWithLink(LocaleController.formatString("ActionUserScored", R.string.ActionUserScored, new Object[]{LocaleController.formatPluralString("Points", this.messageOwner.action.score, new Object[0])}), "un1", r9);
        return;
    L18:
        if (r9.id != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L21;
        this.messageText = LocaleController.formatString("ActionYouScored", R.string.ActionYouScored, new Object[]{LocaleController.formatPluralString("Points", this.messageOwner.action.score, new Object[0])});
        return;
    L23:
        if (r9 != null) goto L25;
    L27:
        this.messageText = replaceWithLink(LocaleController.formatString("ActionUserScoredInGame", R.string.ActionUserScoredInGame, new Object[]{LocaleController.formatPluralString("Points", this.messageOwner.action.score, new Object[0])}), "un1", r9);
    L28:
        this.messageText = replaceWithLink(this.messageText, "un2", r02);
        return;
    L25:
        if (r9.id != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L27;
        this.messageText = LocaleController.formatString("ActionYouScoredInGame", R.string.ActionYouScoredInGame, new Object[]{LocaleController.formatPluralString("Points", this.messageOwner.action.score, new Object[0])});
        goto L28
    L9:
        if (getMedia(r0) == null) goto L13;
        if (getMedia(this.replyMessageObject).game == null) goto L13;
        r02 = getMedia(this.replyMessageObject).game;
        goto L15
    L4:
        if (isFromUser() == false) goto L6;
        r9 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.messageOwner.from_id.user_id));
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void generateLayout(TLRPC.User r37) {
        int r0 = this.type;
        if (r0 != 36) goto L19;
        int r02 = getMaxMessageTextWidth();
        RichMessageLayout r2 = this.richLayout;
        if (r2 != null) goto L7;
    L10:
        RichMessageLayout r22 = this.richLayout;
        this.richLayout = new RichMessageLayout(this, r02, r22);
        if (r22 == null) goto L15;
        View r03 = r22.view;
        if (r03 == null) goto L15;
        r22.detach(r03);
    L15:
        int r04 = this.widePostsTextWidth;
        if (r04 <= 0) goto L509;
        this.textWidth = r04;
        return;
    L509:
        return;
    L7:
        if (this.messageOwner == null) goto L15;
        if (r2.needsUpdate(getDisplayRichMessage(), r02) == false) goto L15;
    L19:
        if (r0 == 0) goto L25;
        if (r0 == 19) goto L25;
        if (r0 == 24) goto L25;
        return;
    L25:
        if (this.messageOwner.peer_id != null) goto L27;
        return;
    L27:
        if (TextUtils.isEmpty(this.messageText) == true) goto L29;
    L31:
        applyEntities();
        TLRPC.Message r05 = this.messageOwner;
        boolean r3 = false;
        if (r05 != null) goto L34;
    L36:
        boolean r06 = false;
    L37:
        if (r06 == true) goto L39;
        r06 = MessagesController.getInstance(this.currentAccount).isPeerNoForwards(getDialogId());
    L39:
        boolean r4 = r06;
        this.textLayoutBlocks = new ArrayList();
        this.textWidth = 0;
        CharSequence r07 = this.messageText;
        if ((r07 instanceof Spanned) == true) goto L42;
    L44:
        boolean r08 = false;
    L45:
        this.hasCode = r08;
        CharSequence r09 = this.messageText;
        if ((r09 instanceof Spanned) == true) goto L48;
    L50:
        boolean r010 = false;
    L51:
        this.hasQuote = r010;
        this.hasSingleQuote = false;
        this.hasSingleCode = false;
        CharSequence r011 = FormattedDateSpan.applyFormatedDateEntities(this.messageText);
        this.messageText = r011;
        if ((r011 instanceof Spanned) == false) goto L75;
        Spanned r012 = (Spanned) r011;
        QuoteSpan[] r5 = (QuoteSpan[]) r012.getSpans(0, r012.length(), QuoteSpan.class);
        int r7 = 0;
    L55:
        if (r7 >= r5.length) goto L58;
        r5[r7].adaptLineHeight = false;
        r7 = r7 + 1;
        goto L55
    L58:
        if (r5.length == 1) goto L60;
    L64:
        boolean r52 = false;
    L65:
        this.hasSingleQuote = r52;
        CodeHighlighting.Span[] r53 = (CodeHighlighting.Span[]) r012.getSpans(0, r012.length(), CodeHighlighting.Span.class);
        if (r53.length == 1) goto L68;
    L72:
        boolean r013 = false;
    L73:
        this.hasSingleCode = r013;
        goto L75
    L68:
        if (r012.getSpanStart(r53[0]) != 0) goto L72;
        if (r012.getSpanEnd(r53[0]) != r012.length()) goto L72;
        r013 = true;
        goto L73
    L60:
        if (r012.getSpanStart(r5[0]) != 0) goto L64;
        if (r012.getSpanEnd(r5[0]) != r012.length()) goto L64;
        r52 = true;
    L75:
        if (this.isBotPendingDraft == false) goto L77;
        this.messageText = BotForumHelper.applyTypingAnimationSpan(this.messageText);
    L77:
        int r014 = getMaxMessageTextWidth();
        float r72 = 15.0f;
        if (this.hasSingleQuote == false) goto L83;
        int r54 = AndroidUtilities.dp(32.0f);
    L80:
        r014 = r014 - r54;
    L81:
        int r10 = r014;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGame) == false) goto L89;
        com.exteragram.messenger.utils.ui.TextPaint r015 = Theme.chat_msgGameTextPaint;
    L88:
        com.exteragram.messenger.utils.ui.TextPaint r9 = r015;
        CharSequence r8 = LocaleUtils.insertHexColorsPreview(this.messageText);     // Catch: Exception -> L465
        if (this.totalAnimatedEmojiCount < 4) goto L94;
        float r12 = -1.0f;
    L96:
        if (this.emojiOnlyCount <= 0) goto L98;
        boolean r13 = true;
    L99:
        StaticLayout r016 = makeStaticLayout(r8, r9, r10, 1.0f, r12, r13);     // Catch: Exception -> L465
        if (this.isRepostPreview == true) goto L103;
    L143:
        float r17 = 32.0f;
        float r19 = 15.0f;
        float r16 = 0.0f;
    L145:
        if (this.hasSingleQuote == false) goto L149;
        int r55 = AndroidUtilities.dp(r17);
    L147:
        int r56 = r55 + r10;
    L152:
        int r73 = r016.getLineCount();
        int r11 = this.totalAnimatedEmojiCount;
        if (r11 < 50) goto L155;
        int r14 = 5;
    L156:
        if (r11 >= 50) goto L158;
        boolean r112 = true;
    L159:
        if (r112 == false) goto L161;
        int r6 = 1;
        int r15 = r73;
    L162:
        ArrayList r74 = new ArrayList();
        if ((r8 instanceof Spanned) == true) goto L165;
    L169:
        if (r112 == true) goto L171;
        if (r6 == 1) goto L171;
        int r20 = 1;
        int r23 = 0;
        int r132 = 0;
    L173:
        if (r132 >= r6) goto L191;
        if (r112 == false) goto L176;
        int r32 = r15;
    L177:
        int r122 = r016.getLineStart(r23);
        int r33 = r32 + r23;
        int r222 = r23;
        int r24 = r016.getLineEnd(r33 - 1);
        StaticLayout r232 = r016;
        int r017 = r24 - 1;
        if (r017 < 0) goto L185;
        int r25 = r24;
        if (r017 >= r232.getText().length()) goto L186;
        if (r232.getText().charAt(r017) != '\n') goto L186;
        int r018 = r25 - 1;
    L187:
        if (r018 >= r122) goto L189;
        r23 = r222;
    L190:
        r132 = r132 + 1;
        r016 = r232;
        r3 = false;
        goto L173
    L189:
        r74.add(new TextRange(r122, r018));
        r23 = r33;
    L186:
        r018 = r25;
        goto L187
    L185:
        r25 = r24;
        goto L186
    L176:
        r32 = Math.min(r14, r15 - r23);
        goto L177
    L191:
        StaticLayout r233 = r016;
    L193:
        int r26 = r74.size();
        this.hasCodeAtTop = r3;
        this.hasCodeAtBottom = r3;
        this.hasQuoteAtBottom = r3;
        this.hasSingleQuote = r3;
        this.hasSingleCode = r3;
        int r34 = 0;
    L195:
        if (r34 >= r74.size()) goto L448;
        TextLayoutBlock r113 = new TextLayoutBlock();
        TextRange r019 = (TextRange) r74.get(r34);
        r113.code = r019.code;
        r113.quote = r019.quote;
        boolean r123 = r019.collapse;
        r113.quoteCollapse = r123;
        if (r123 == false) goto L199;
        r113.messageObject = this;
    L199:
        r113.index = r34;
        r113.start = r019.start;
        if (r34 != 0) goto L202;
        boolean r124 = r20;
    L203:
        r113.first = r124;
        if (r34 != (r74.size() - 1)) goto L206;
        boolean r125 = r20;
    L207:
        r113.last = r125;
        boolean r133 = r113.first;
        if (r133 == false) goto L210;
        this.hasCodeAtTop = r113.code;
    L210:
        if (r125 == 0) goto L212;
        this.hasQuoteAtBottom = r113.quote;
        this.hasCodeAtBottom = r113.code;
    L212:
        if (r133 == false) goto L217;
        if (r125 == 0) goto L217;
        if (r113.quote == false) goto L217;
        boolean r142 = r20;
    L218:
        this.hasSingleQuote = r142;
        if (r133 == false) goto L226;
        if (r125 == 0) goto L226;
        if (r113.quote == true) goto L226;
        if (r113.code == false) goto L226;
        boolean r143 = r20;
    L227:
        this.hasSingleCode = r143;
        if (r113.quote == false) goto L237;
        float r144 = 6.0f;
        if (r133 == false) goto L233;
        if (r125 == 0) goto L233;
        int r126 = AndroidUtilities.dp(6.0f);
        r113.padBottom = r126;
        r113.padTop = r126;
    L252:
        if (r113.code == false) goto L260;
        int r127 = r019.end - r019.start;
        if (r127 <= 220) goto L257;
        com.exteragram.messenger.utils.ui.TextPaint r62 = Theme.chat_msgTextCode3Paint;
    L256:
        com.exteragram.messenger.utils.ui.TextPaint r252 = r62;
    L261:
        CharSequence r128 = r8.subSequence(r019.start, r019.end);
        if (r113.quote == false) goto L266;
        int r63 = AndroidUtilities.dp(24.0f);
    L264:
        int r134 = r56 - r63;
    L270:
        if (r26 == r20) goto L272;
        int r18 = 0;
        int r64 = r019.start;
        int r145 = r019.end;
        if (r145 >= r64) goto L309;
        boolean r262 = r4;
        ArrayList r253 = r74;
        CharSequence r242 = r8;
        com.exteragram.messenger.utils.ui.TextPaint r28 = r9;
        int r146 = 1;
    L447:
        r34 = r34 + 1;
        r20 = r146;
        r8 = r242;
        r74 = r253;
        r4 = r262;
        r9 = r28;
        goto L195
    L309:
        r113.charactersOffset = r64;
        r113.charactersEnd = r145;
        if (r113.code == true) goto L313;
    L318:
        SpannableString r020 = SpannableString.valueOf(r128);     // Catch: Exception -> L316
    L315:
        SpannableString r243 = r020;
        r113.originalWidth = r134;     // Catch: Exception -> L316
        if (this.totalAnimatedEmojiCount < 4) goto L322;
        float r282 = -1.0f;
    L323:
        StaticLayout r021 = makeStaticLayout(r243, r252, r134, 1.0f, r282, false);     // Catch: Exception -> L316
        r113.textLayout = r021;     // Catch: Exception -> L316
        r113.height = r021.getHeight();     // Catch: Exception -> L316
        r113.collapsedHeight = (int) Math.min((r9.getTextSize() * 1.4f) * 3.0f, r113.height);     // Catch: Exception -> L316
    L324:
        this.textLayoutBlocks.add(r113);
        int r65 = r113.textLayout.getLineCount();
        float r022 = r113.textLayout.getLineLeft(r65 - 1);     // Catch: Exception -> L331
        if (r34 == 0) goto L328;
    L333:
        float r135 = r022;
    L481:
        float r023 = r113.textLayout.getLineWidth(r65 - 1);     // Catch: Exception -> L339
    L342:
        if (r113.quote == false) goto L346;
        int r147 = AndroidUtilities.dp(r17);
    L344:
        r023 = r023 + r147;
    L348:
        int r024 = (int) Math.ceil(r023);
        if (r024 <= (r56 + 80)) goto L351;
        r024 = r56;
    L351:
        int r148 = r26 - 1;
        if (r34 != r148) goto L354;
        this.lastLineWidth = r024;
    L354:
        float r152 = r024;
        r253 = r74;
        r242 = r8;
        int r75 = (int) Math.ceil(r152 + Math.max(0.0f, r135));
        if (r113.quote == false) goto L365;
        r113.maxRight = 0.0f;
        int r82 = r18;
    L357:
        if (r82 >= r65) goto L365;
        int r223 = r024;
        r113.maxRight = Math.max(r113.maxRight, r113.textLayout.getLineRight(r82));     // Catch: Exception -> L468
    L364:
        r82 = r82 + 1;
        r024 = r223;
    L363:
        r113.maxRight = this.textWidth;
    L362:
        r223 = r024;
    L365:
        int r224 = r024;
        if (r65 <= 1) goto L412;
        r262 = r4;
        int r42 = r75;
        int r83 = r18;
        int r129 = r83;
        int r025 = r224;
        float r136 = 0.0f;
        float r153 = 0.0f;
    L368:
        if (r83 >= r65) goto L404;
        int r27 = r65;
        float r225 = r113.textLayout.getLineWidth(r83);     // Catch: Exception -> L372
    L374:
        if (r113.quote == false) goto L378;
        int r66 = AndroidUtilities.dp(r17);
    L376:
        r225 = r225 + r66;
    L477:
        float r67 = r113.textLayout.getLineLeft(r83);     // Catch: Exception -> L382
    L383:
        float r283 = r67;
        if (r225 <= (r56 + 20)) goto L387;
        float r68 = r56;
        com.exteragram.messenger.utils.ui.TextPaint r284 = r9;
        float r92 = 0.0f;
    L388:
        int r29 = r10;
        if (r92 > 0.0f) goto L394;
        int r30 = r129;
        if (r113.textLayout.getParagraphDirection(r83) == (-1)) goto L395;
        r113.directionFlags = (byte) (r113.directionFlags | 2);
        int r1210 = 1;
    L396:
        if (r30 != 0) goto L402;
        if (r92 != 0.0f) goto L402;
        if (r113.textLayout.getParagraphDirection(r83) != r1210) goto L402;
        r30 = 1;
    L401:
        int r1211 = 1;
    L403:
        r153 = Math.max(r153, r68);
        float r93 = r92 + r68;
        float r137 = Math.max(r136, r93);
        r025 = Math.max(r025, (int) Math.ceil(r68));
        r42 = Math.max(r42, (int) Math.ceil(r93));
        r83 = r83 + 1;
        r129 = r1211;
        r136 = r137;
        r65 = r27;
        r9 = r284;
        r10 = r29;
    L402:
        r1211 = r30;
    L395:
        this.textXOffset = Math.min(this.textXOffset, r92);
        r1210 = 1;
        r113.directionFlags = (byte) (r113.directionFlags | 1);
        this.hasRtl = true;
        goto L396
    L394:
        r30 = r129;
        goto L395
    L387:
        r284 = r9;
        r92 = r283;
        r68 = r225;
    L382:
        r67 = 0.0f;
        goto L383
    L378:
        if (r113.code == false) goto L477;
        r66 = AndroidUtilities.dp(r19);
    L372:
        r225 = 0.0f;
        goto L374
    L404:
        r28 = r9;
        int r292 = r10;
        if (r129 == 0) goto L408;
        if (r34 != r148) goto L411;
        this.lastLineWidth = r75;
    L411:
        this.textWidth = Math.max(this.textWidth, (int) Math.ceil(r136));
        r75 = r42;
        r146 = 1;
        r16 = 0.0f;
    L425:
        Text r026 = r113.languageLayout;
        if (r026 == null) goto L432;
        float r43 = this.textWidth;
        float r027 = r026.getCurrentWidth() + AndroidUtilities.dp(r19);
        StaticLayout r69 = r113.textLayout;
        if (r69 != null) goto L430;
        float r138 = r16;
    L431:
        this.textWidth = (int) Math.max(r43, Math.min(r027, r138));
        goto L432
    L430:
        r138 = r69.getWidth();
    L432:
        r113.spoilers.clear();
        if (this.isSpoilersRevealed == false) goto L435;
    L445:
        r10 = r292;
        goto L447
    L435:
        if (this.spoiledLoginCode == true) goto L445;
        if (r113.quote == false) goto L442;
        int r028 = AndroidUtilities.dp(r17);
    L439:
        r75 = r75 - r028;
    L444:
        SpoilerEffect.addSpoilers(null, r113.textLayout, -1, r75, null, r113.spoilers);
        goto L445
    L442:
        if (r113.code == false) goto L444;
        r028 = AndroidUtilities.dp(r19);
        goto L439
    L408:
        if (r34 != r148) goto L410;
        this.lastLineWidth = r025;
    L410:
        r136 = r153;
        goto L411
    L412:
        r262 = r4;
        r28 = r9;
        r292 = r10;
        r16 = 0.0f;
        if (r135 <= 0.0f) goto L423;
        float r029 = Math.min(this.textXOffset, r135);
        this.textXOffset = r029;
        if (r029 != 0.0f) goto L418;
        int r030 = (int) (r152 + r135);
    L417:
        r146 = 1;
        if (r26 == 1) goto L421;
        boolean r44 = 1;
    L422:
        this.hasRtl = r44;
        r113.directionFlags = (byte) (r113.directionFlags | 1);
    L424:
        this.textWidth = Math.max(this.textWidth, Math.min(r56, r030));
        goto L425
    L421:
        r44 = r18;
        goto L422
    L418:
        r030 = r224;
        goto L417
    L423:
        r146 = 1;
        r113.directionFlags = (byte) (r113.directionFlags | 2);
        r030 = r224;
        goto L424
    L346:
        if (r113.code == false) goto L348;
        r147 = AndroidUtilities.dp(r19);
    L339:
        e = move-exception;
        FileLog.e(e);
        r023 = 0.0f;
        goto L342
    L328:
        if (r022 < r16) goto L333;
        this.textXOffset = r022;     // Catch: Exception -> L331
    L331:
        e = move-exception;
        if (r34 != 0) goto L336;
        this.textXOffset = r16;
    L336:
        FileLog.e(e);
        r135 = 0.0f;
        goto L481
    L322:
        r282 = r16;
        goto L323
    L313:
        if (r113.quote == true) goto L318;
        r020 = CodeHighlighting.getHighlighted(r128.toString(), r019.language);     // Catch: Exception -> L316
    L316:
        e = move-exception;
        r262 = r4;
        r253 = r74;
        r242 = r8;
        r28 = r9;
        r146 = 1;
        FileLog.e(e);
        goto L447
    L272:
        if (r113.code == true) goto L274;
    L293:
        int r1212 = 2;
        r113.originalWidth = r10;
    L292:
        StaticLayout r031 = r233;
        r113.textLayout = r031;
        r113.charactersOffset = 0;
        r113.charactersEnd = r031.getText().length();
        r113.height = r031.getHeight();
        r113.collapsedHeight = (int) Math.min((r9.getTextSize() * 1.4f) * 3.0f, r113.height);
        int r610 = this.emojiOnlyCount;
        if (r610 != 0) goto L297;
    L305:
        r233 = r031;
        r18 = 0;
        goto L324
    L297:
        if (r610 == 1) goto L304;
        if (r610 != r1212) goto L300;
        r113.padTop -= AndroidUtilities.dp(4.5f);
        goto L305
    L300:
        if (r610 != 3) goto L305;
        r113.padTop -= AndroidUtilities.dp(4.2f);
        goto L305
    L304:
        r113.padTop -= AndroidUtilities.dp(5.3f);
        goto L305
    L274:
        if (r113.quote == true) goto L293;
        if ((r233.getText() instanceof Spannable) == false) goto L293;
        if (TextUtils.isEmpty(r019.language) == true) goto L281;
        SpannableString r032 = CodeHighlighting.getHighlighted(r128.toString(), r019.language);
    L280:
        SpannableString r244 = r032;
        r113.originalWidth = r134;
        if (this.totalAnimatedEmojiCount < 4) goto L286;
        float r285 = -1.0f;
    L285:
        r1212 = 2;
        if (this.emojiOnlyCount <= 0) goto L290;
        boolean r293 = true;
    L291:
        r233 = makeStaticLayout(r244, r252, r134, 1.0f, r285, r293);
        r10 = r134;
        goto L292
    L290:
        r293 = false;
        goto L291
    L286:
        r285 = r16;
        goto L285
    L281:
        r032 = new SpannableString(r128.toString());
        goto L280
    L266:
        if (r113.code == false) goto L268;
        r63 = AndroidUtilities.dp(r19);
        goto L264
    L268:
        r134 = r56;
        goto L270
    L257:
        if (r127 <= 80) goto L259;
        r62 = Theme.chat_msgTextCode2Paint;
        goto L256
    L259:
        r62 = Theme.chat_msgTextCodePaint;
        goto L256
    L260:
        r252 = r9;
    L233:
        if (r133 == false) goto L235;
        r144 = 8.0f;
    L235:
        r113.padTop = AndroidUtilities.dp(r144);
        r113.padBottom = AndroidUtilities.dp(7.0f);
        goto L252
    L237:
        if (r113.code == false) goto L252;
        r113.layoutCode(r019.language, r019.end - r019.start, r4);
        int r139 = AndroidUtilities.dp(4.0f) + r113.languageHeight;
        if (r113.first == false) goto L241;
        int r149 = 0;
    L242:
        r113.padTop = r139 + r149;
        int r1213 = AndroidUtilities.dp(4.0f);
        if (r113.last == false) goto L245;
        int r1310 = 0;
    L246:
        int r1214 = r1213 + r1310;
        if (r113.hasCodeCopyButton == false) goto L249;
        int r1311 = AndroidUtilities.dp(38.0f);
    L250:
        r113.padBottom = r1214 + r1311;
        goto L252
    L249:
        r1311 = 0;
        goto L250
    L245:
        r1310 = AndroidUtilities.dp(7.0f);
        goto L246
    L241:
        r149 = AndroidUtilities.dp(5.0f);
    L226:
        r143 = 0;
    L217:
        r142 = 0;
        goto L218
    L206:
        r125 = 0;
        goto L207
    L202:
        r124 = 0;
        goto L203
    L448:
        int r1410 = r20;
        if (this.hasCode == false) goto L461;
        int r033 = this.textWidth;
        int r210 = this.generatedWithMinSize;
        if (needDrawAvatarInternal() == true) goto L453;
    L457:
        int r35 = 0;
    L459:
        if (r033 <= (r210 - AndroidUtilities.dp(80 + r35))) goto L461;
        boolean r211 = r1410;
    L462:
        this.hasWideCode = r211;
        this.factCheckText = null;
        int r034 = this.widePostsTextWidth;
        if (r034 <= 0) goto L512;
        this.textWidth = r034;
        return;
    L512:
        return;
    L453:
        if (isOutOwner() == true) goto L457;
        if (this.messageOwner.isThreadMessage == true) goto L457;
        r35 = 52;
    L461:
        r211 = 0;
    L171:
        r233 = r016;
        r20 = 1;
        r3 = false;
        r74.add(new TextRange(0, r233.getText().length()));
        goto L193
    L165:
        if (this.hasQuote == false) goto L167;
    L168:
        cutIntoRanges(r8, r74);
        r233 = r016;
        r20 = 1;
        goto L193
    L167:
        if (this.hasCode == false) goto L169;
    L161:
        r15 = r73;
        r6 = (int) Math.ceil(r73 / r14);
        goto L162
    L158:
        r112 = false;
        goto L159
    L155:
        r14 = 10;
        goto L156
    L149:
        if (this.hasSingleCode == false) goto L151;
        r55 = AndroidUtilities.dp(r19);
        goto L147
    L151:
        r56 = r10;
        goto L152
    L103:
        if (this.type != 0) goto L105;
        int r114 = 22;
    L110:
        if (isWebpage() == false) goto L113;
        r114 = r114 - 8;
    L113:
        if (r016.getLineCount() <= r114) goto L143;
        String r1215 = LocaleController.getString(R.string.ReadMore);
        r17 = 32.0f;
        r16 = 0.0f;
        int r1312 = (int) Math.ceil(r9.measureText("… " + r1215) + AndroidUtilities.dp(1.0f));
        int r154 = 0;
        float r1411 = 0.0f;
    L115:
        if (r154 >= r114) goto L117;
        r1411 = Math.max(r1411, r016.getLineRight(r154));
        r154 = r154 + 1;
        goto L115
    L117:
        int r115 = r114 - 1;
        int r611 = r016.getLineStart(r115);
        int r116 = r016.getLineEnd(r115) - 1;
    L118:
        if (r116 < r611) goto L123;
        r19 = r72;
        if (r016.getPrimaryHorizontal(r116) < (r1411 - r1312)) goto L124;
        r116 = r116 - 1;
        r72 = r19;
    L124:
        if (r116 < r611) goto L129;
        if (Character.isWhitespace(r8.charAt(r116)) == true) goto L129;
        r116 = r116 - 1;
    L129:
        SpannableStringBuilder r035 = new SpannableStringBuilder(r8.subSequence(0, r116)).append("… ").append(r1215);
        r035.setSpan(new AnonymousClass2(this), r035.length() - r1215.length(), r035.length(), 33);
        r8 = LocaleUtils.insertHexColorsPreview(r035);     // Catch: Exception -> L140
        if (this.totalAnimatedEmojiCount < 4) goto L133;
        float r1216 = -1.0f;
    L135:
        if (this.emojiOnlyCount <= 0) goto L137;
        boolean r1313 = true;
    L138:
        r016 = makeStaticLayout(r8, r9, r10, 1.0f, r1216, r1313);     // Catch: Exception -> L140
        goto L145
    L137:
        r1313 = false;
        goto L138
    L133:
        r1216 = 0.0f;
    L140:
        e = move-exception;
        FileLog.e(e);
        return;
    L123:
        r19 = r72;
        goto L124
    L105:
        if (hasValidGroupId() == false) goto L107;
        r114 = 7;
        goto L110
    L107:
        r114 = 12;
        goto L110
    L98:
        r13 = false;
        goto L99
    L94:
        r12 = 0.0f;
    L465:
        e = move-exception;
        FileLog.e(e);
        return;
    L89:
        r015 = Theme.chat_msgTextPaint;
        goto L88
    L83:
        if (this.hasSingleCode == false) goto L81;
        r54 = AndroidUtilities.dp(15.0f);
        goto L80
    L48:
        if (((QuoteSpan.QuoteStyleSpan[]) ((Spanned) r09).getSpans(0, r09.length(), QuoteSpan.QuoteStyleSpan.class)).length <= 0) goto L50;
        r010 = true;
        goto L51
    L42:
        if (((CodeHighlighting.Span[]) ((Spanned) r07).getSpans(0, r07.length(), CodeHighlighting.Span.class)).length <= 0) goto L44;
        r08 = true;
        goto L45
    L34:
        if (r05.noforwards == false) goto L36;
        r06 = true;
        goto L37
    L29:
        if (this.isBotPendingDraft == true) goto L31;
    }

    public void generateLinkDescription() {
        if (this.linkDescription != null) goto L82;
        TLRPC.WebPage r0 = this.storyMentionWebpage;
        if (r0 != null) goto L13;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L11;
        r0 = ((TLRPC.TL_messageMediaWebPage) getMedia(this.messageOwner)).webpage;
        goto L13
    L11:
        r0 = null;
    L13:
        if (r0 == null) goto L25;
        int r3 = 0;
    L16:
        if (r3 >= r0.attributes.size()) goto L25;
        TLRPC.WebPageAttribute r4 = r0.attributes.get(r3);
        if ((r4 instanceof TLRPC.TL_webPageAttributeStory) == false) goto L24;
        TLRPC.TL_webPageAttributeStory r42 = (TLRPC.TL_webPageAttributeStory) r4;
        TL_stories.StoryItem r5 = r42.storyItem;
        if (r5 == null) goto L24;
        if (r5.caption == null) goto L24;
        this.linkDescription = new SpannableStringBuilder(r42.storyItem.caption);
        this.webPageDescriptionEntities = r42.storyItem.entities;
        boolean r7 = true;
    L27:
        if (this.linkDescription == null) goto L29;
    L55:
        int r11 = 0;
    L57:
        if (TextUtils.isEmpty(this.linkDescription) == false) goto L59;
        return;
    L59:
        if (containsUrls(this.linkDescription) == true) goto L73;
    L64:
        CharSequence r43 = Emoji.replaceEmoji(this.linkDescription, Theme.chat_msgTextPaint.getFontMetricsInt(), false);
        this.linkDescription = r43;
        ArrayList<TLRPC.MessageEntity> r52 = this.webPageDescriptionEntities;
        if (r52 == null) goto L67;
        addEntitiesToText(r43, r52, isOut(), r7, false, !r7);
        replaceAnimatedEmoji(this.linkDescription, this.webPageDescriptionEntities, Theme.chat_msgTextPaint.getFontMetricsInt());
    L67:
        if (r11 != 0) goto L69;
        return;
    L69:
        if ((this.linkDescription instanceof Spannable) == true) goto L71;
        this.linkDescription = new SpannableStringBuilder(this.linkDescription);
    L71:
        addUrlsByPattern(isOutOwner(), this.linkDescription, false, r11, 0, false);
        return;
    L73:
        AndroidUtilities.addLinksSafe((Spannable) this.linkDescription, 1, false, true);     // Catch: Exception -> L62
    L62:
        e = move-exception;
        FileLog.e(e);
        goto L64
    L29:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L46;
        if ((getMedia(this.messageOwner).webpage instanceof TLRPC.TL_webPage) == false) goto L46;
        if (getMedia(this.messageOwner).webpage.description == null) goto L46;
        this.linkDescription = Spannable.Factory.getInstance().newSpannable(getMedia(this.messageOwner).webpage.description);
        String r02 = getMedia(this.messageOwner).webpage.site_name;
        if (r02 == null) goto L38;
        r02 = r02.toLowerCase();
    L38:
        if ("instagram".equals(r02) == false) goto L41;
        int r03 = 1;
    L44:
        r11 = r03;
        goto L57
    L41:
        if ("twitter".equals(r02) == false) goto L43;
        r03 = 2;
        goto L44
    L43:
        r03 = 0;
    L46:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGame) == false) goto L51;
        if (getMedia(this.messageOwner).game.description == null) goto L51;
        this.linkDescription = Spannable.Factory.getInstance().newSpannable(getMedia(this.messageOwner).game.description);
    L51:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaInvoice) == false) goto L55;
        if (getMedia(this.messageOwner).description == null) goto L55;
        this.linkDescription = Spannable.Factory.getInstance().newSpannable(getMedia(this.messageOwner).description);
    L24:
        r3 = r3 + 1;
    L25:
        r7 = false;
        goto L27
    }

    public void generatePaymentSentMessageText(TLRPC.User r6, boolean r7) {
        if (r6 != null) goto L5;
        r6 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(getDialogId()));
    L5:
        if (r6 == null) goto L7;
        String r62 = UserObject.getFirstName(r6);
    L43:
    L11:
        e = move-exception;
        FileLog.e(e);
        String r0 = "<error>";
    L16:
        MessageObject r1 = this.replyMessageObject;
        if (r1 != null) goto L19;
    L31:
        TLRPC.MessageAction r12 = this.messageOwner.action;
        int r3 = r12.subscription_until_date;
        if (r3 == 0) goto L37;
        if (r7 == false) goto L35;
        this.messageText = LocaleController.formatString(R.string.PaymentSuccessfullyPaidMeNoItemSubscription, new Object[]{r62, r0, LocaleController.formatDateTime(r3, false)});
    L41:
        this.messageText = StarsIntroActivity.replaceStars(this.messageText);
        return;
    L35:
        this.messageText = LocaleController.formatString(R.string.PaymentSuccessfullyPaidSubscriptionNoItem, new Object[]{r0, r62, LocaleController.formatDateTime(r3, false)});
        goto L41
    L37:
        if (r12.recurring_init == false) goto L40;
        if (r7 == true) goto L40;
        this.messageText = LocaleController.formatString(R.string.PaymentSuccessfullyPaidNoItemRecurrent, new Object[]{r0, r62});
    L40:
        this.messageText = LocaleController.formatString(R.string.PaymentSuccessfullyPaidNoItem, new Object[]{r0, r62});
        goto L41
    L19:
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaInvoice) == false) goto L31;
        TLRPC.MessageAction r13 = this.messageOwner.action;
        if (r13.subscription_until_date == 0) goto L27;
        MessageObject r14 = this.replyMessageObject;
        if (r7 == false) goto L25;
        this.messageText = LocaleController.formatString(R.string.PaymentSuccessfullyPaidMeSubscription, new Object[]{r62, r0, getMedia(r14).title, LocaleController.formatDateTime(this.messageOwner.action.subscription_until_date, false)});
        goto L41
    L25:
        this.messageText = LocaleController.formatString(R.string.PaymentSuccessfullyPaidSubscription, new Object[]{r0, r62, getMedia(r14).title, LocaleController.formatDateTime(this.messageOwner.action.subscription_until_date, false)});
        goto L41
    L27:
        if (r13.recurring_init == false) goto L30;
        if (r7 == true) goto L30;
        this.messageText = LocaleController.formatString(R.string.PaymentSuccessfullyPaidRecurrent, new Object[]{r0, r62, getMedia(this.replyMessageObject).title});
    L30:
        this.messageText = LocaleController.formatString(R.string.PaymentSuccessfullyPaid, new Object[]{r0, r62, getMedia(this.replyMessageObject).title});
        goto L41
    L9:
        if ("XTR".equals(this.messageOwner.action.currency) == false) goto L13;
        r0 = "XTR " + this.messageOwner.action.total_amount;     // Catch: Exception -> L11
        goto L16
    L13:
        LocaleController r02 = LocaleController.getInstance();     // Catch: Exception -> L11
        TLRPC.MessageAction r15 = this.messageOwner.action;     // Catch: Exception -> L11
        r0 = r02.formatCurrencyString(r15.total_amount, r15.currency);     // Catch: Exception -> L11
        goto L16
    L7:
        r62 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L43
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [org.telegram.tgnet.TLRPC$Chat] */
    /* JADX WARN: Type inference failed for: r9v1, types: [org.telegram.tgnet.TLRPC$Chat] */
    /* JADX WARN: Type inference failed for: r9v10, types: [org.telegram.tgnet.TLRPC$Chat] */
    /* JADX WARN: Type inference failed for: r9v7, types: [org.telegram.tgnet.TLRPC$Chat] */
    public void generatePinMessageText(TLRPC.User r8, TLRPC.Chat r9) {
        if (r8 != null) goto L14;
        if (r9 != 0) goto L14;
        if (isFromUser() == false) goto L7;
        r8 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.messageOwner.from_id.user_id));
    L7:
        if (r8 != null) goto L14;
        TLRPC.Peer r0 = this.messageOwner.peer_id;
        if ((r0 instanceof TLRPC.TL_peerChannel) == false) goto L12;
        r9 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.peer_id.channel_id));
        goto L14
    L12:
        if ((r0 instanceof TLRPC.TL_peerChat) == false) goto L14;
        r9 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.peer_id.chat_id));
    L14:
        MessageObject r02 = this.replyMessageObject;
        if (r02 == null) goto L177;
        TLRPC.Message r2 = r02.messageOwner;
        if ((r2 instanceof TLRPC.TL_messageEmpty) == true) goto L177;
        if ((r2.action instanceof TLRPC.TL_messageActionHistoryClear) == true) goto L177;
        if (r02.isMusic() == false) goto L30;
        String r03 = LocaleController.getString(R.string.ActionPinnedMusic);
        if (r8 != null) goto L27;
        r8 = r9;
    L27:
        this.messageText = replaceWithLink(r03, "un1", r8);
        return;
    L30:
        if (this.replyMessageObject.isVideo() == false) goto L38;
        String r04 = LocaleController.getString(R.string.ActionPinnedVideo);
        if (r8 != null) goto L35;
        r8 = r9;
    L35:
        this.messageText = replaceWithLink(r04, "un1", r8);
        return;
    L38:
        if (this.replyMessageObject.isGif() == false) goto L46;
        String r05 = LocaleController.getString(R.string.ActionPinnedGif);
        if (r8 != null) goto L43;
        r8 = r9;
    L43:
        this.messageText = replaceWithLink(r05, "un1", r8);
        return;
    L46:
        if (this.replyMessageObject.isVoice() == false) goto L54;
        String r06 = LocaleController.getString(R.string.ActionPinnedVoice);
        if (r8 != null) goto L51;
        r8 = r9;
    L51:
        this.messageText = replaceWithLink(r06, "un1", r8);
        return;
    L54:
        if (this.replyMessageObject.isRoundVideo() == false) goto L62;
        String r07 = LocaleController.getString(R.string.ActionPinnedRound);
        if (r8 != null) goto L59;
        r8 = r9;
    L59:
        this.messageText = replaceWithLink(r07, "un1", r8);
        return;
    L62:
        if (this.replyMessageObject.isSticker() == true) goto L66;
        if (this.replyMessageObject.isAnimatedSticker() == true) goto L66;
    L74:
        if ((getMedia(this.replyMessageObject) instanceof TLRPC.TL_messageMediaDocument) == false) goto L82;
        String r08 = LocaleController.getString(R.string.ActionPinnedFile);
        if (r8 != null) goto L79;
        r8 = r9;
    L79:
        this.messageText = replaceWithLink(r08, "un1", r8);
        return;
    L82:
        if ((getMedia(this.replyMessageObject) instanceof TLRPC.TL_messageMediaGeo) == false) goto L90;
        String r09 = LocaleController.getString(R.string.ActionPinnedGeo);
        if (r8 != null) goto L87;
        r8 = r9;
    L87:
        this.messageText = replaceWithLink(r09, "un1", r8);
        return;
    L90:
        if ((getMedia(this.replyMessageObject) instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L98;
        String r010 = LocaleController.getString(R.string.ActionPinnedGeoLive);
        if (r8 != null) goto L95;
        r8 = r9;
    L95:
        this.messageText = replaceWithLink(r010, "un1", r8);
        return;
    L98:
        if ((getMedia(this.replyMessageObject) instanceof TLRPC.TL_messageMediaContact) == false) goto L105;
        String r011 = LocaleController.getString(R.string.ActionPinnedContact);
        if (r8 != null) goto L103;
        r8 = r9;
    L103:
        this.messageText = replaceWithLink(r011, "un1", r8);
        return;
    L105:
        boolean r012 = getMedia(this.replyMessageObject) instanceof TLRPC.TL_messageMediaPoll;
        MessageObject r22 = this.replyMessageObject;
        if (r012 == false) goto L122;
        if (((TLRPC.TL_messageMediaPoll) getMedia(r22)).poll.quiz == false) goto L115;
        String r013 = LocaleController.getString(R.string.ActionPinnedQuiz);
        if (r8 != null) goto L113;
        r8 = r9;
    L113:
        this.messageText = replaceWithLink(r013, "un1", r8);
        return;
    L115:
        String r014 = LocaleController.getString(R.string.ActionPinnedPoll);
        if (r8 != null) goto L119;
        r8 = r9;
    L119:
        this.messageText = replaceWithLink(r014, "un1", r8);
        return;
    L122:
        if ((getMedia(r22) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L129;
        String r015 = LocaleController.getString(R.string.ActionPinnedPhoto);
        if (r8 != null) goto L127;
        r8 = r9;
    L127:
        this.messageText = replaceWithLink(r015, "un1", r8);
        return;
    L129:
        boolean r016 = getMedia(this.replyMessageObject) instanceof TLRPC.TL_messageMediaPaidMedia;
        MessageObject r23 = this.replyMessageObject;
        if (r016 == false) goto L137;
        TLRPC.TL_messageMediaPaidMedia r017 = (TLRPC.TL_messageMediaPaidMedia) getMedia(r23);
        if (r9 == 0) goto L134;
        String r82 = r9.title;
    L135:
        this.messageText = LocaleController.formatPluralString("NotificationPinnedPaidMedia", (int) r017.stars_amount, new Object[]{r82});
        return;
    L134:
        r82 = UserObject.getUserName(r8);
        goto L135
    L137:
        boolean r018 = getMedia(r23) instanceof TLRPC.TL_messageMediaGame;
        MessageObject r24 = this.replyMessageObject;
        if (r018 == false) goto L145;
        String r019 = LocaleController.formatString("ActionPinnedGame", R.string.ActionPinnedGame, new Object[]{"🎮 " + getMedia(r24).game.title});
        if (r8 != null) goto L143;
        r8 = r9;
    L143:
        CharSequence r83 = replaceWithLink(r019, "un1", r8);
        this.messageText = r83;
        this.messageText = Emoji.replaceEmoji(r83, Theme.chat_msgTextPaint.getFontMetricsInt(), false);
        return;
    L145:
        CharSequence r020 = r24.messageText;
        if (r020 != null) goto L148;
    L171:
        String r021 = LocaleController.getString(R.string.ActionPinnedNoText);
        if (r8 != null) goto L175;
        r8 = r9;
    L175:
        this.messageText = replaceWithLink(r021, "un1", r8);
        return;
    L148:
        if (r020.length() <= 0) goto L171;
        CharSequence r022 = AnimatedEmojiSpan.cloneSpans(this.replyMessageObject.messageText);
        if (r022.length() <= 20) goto L152;
        r022 = r022.subSequence(0, 20);
        boolean r25 = true;
    L153:
        CharSequence r023 = Emoji.replaceEmoji(r022, Theme.chat_msgTextPaint.getFontMetricsInt(), true);
        MessageObject r5 = this.replyMessageObject;
        if (r5 != null) goto L156;
    L158:
        MediaDataController.addTextStyleRuns(this.replyMessageObject, (Spannable) r023);
        if (r25 == true) goto L161;
    L165:
        SpannableStringBuilder r024 = AndroidUtilities.formatSpannable(LocaleController.getString(R.string.ActionPinnedText), new CharSequence[]{r023});
        if (r8 != null) goto L169;
        r8 = r9;
    L169:
        this.messageText = replaceWithLink(r024, "un1", r8);
        return;
    L161:
        if ((r023 instanceof SpannableStringBuilder) == false) goto L163;
        ((SpannableStringBuilder) r023).append("...");
        goto L165
    L163:
        if (r023 == null) goto L165;
        r023 = new SpannableStringBuilder(r023).append("...");
        goto L165
    L156:
        if (r5.messageOwner == null) goto L158;
        r023 = r5.replaceAnimatedEmoji(r023, Theme.chat_msgTextPaint.getFontMetricsInt());
        goto L158
    L152:
        r25 = false;
    L66:
        if (this.replyMessageObject.isAnimatedEmoji() == true) goto L74;
        String r025 = LocaleController.getString(R.string.ActionPinnedSticker);
        if (r8 != null) goto L71;
        r8 = r9;
    L71:
        this.messageText = replaceWithLink(r025, "un1", r8);
        return;
    L177:
        String r026 = LocaleController.getString(R.string.ActionPinnedNoText);
        if (r8 != null) goto L181;
        r8 = r9;
    L181:
        this.messageText = replaceWithLink(r026, "un1", r8);
    }

    @Deprecated
    public void generateSuggestionApprovalMessageText() {
    }

    public void generateThumbs(boolean r9) {
        boolean r0 = hasExtendedMediaPreview();
        TLRPC.Message r1 = this.messageOwner;
        int r2 = 0;
        if (r0 == false) goto L13;
        TLRPC.TL_messageExtendedMediaPreview r02 = (TLRPC.TL_messageExtendedMediaPreview) r1.media.extended_media.get(0);
        if (r9 == true) goto L7;
        this.photoThumbs = new ArrayList(Collections.singletonList(r02.thumb));
    L8:
        this.photoThumbsObject = this.messageOwner;
        if (this.strippedThumb != null) goto L324;
        createStrippedThumb();
        return;
    L324:
        return;
    L7:
        updatePhotoSizeLocations(this.photoThumbs, Collections.singletonList(r02.thumb));
        goto L8
    L13:
        if ((r1 instanceof TLRPC.TL_messageService) == false) goto L51;
        TLRPC.MessageAction r03 = r1.action;
        if ((r03 instanceof TLRPC.TL_messageActionChatEditPhoto) == false) goto L325;
        TLRPC.Photo r04 = r03.photo;
        if (r9 == true) goto L19;
        this.photoThumbs = new ArrayList(r04.sizes);
    L38:
        if (r04.dc_id == 0) goto L48;
        ArrayList<TLRPC.PhotoSize> r92 = this.photoThumbs;
        if (r92 == null) goto L48;
        int r93 = r92.size();
    L42:
        if (r2 >= r93) goto L48;
        TLRPC.FileLocation r12 = this.photoThumbs.get(r2).location;
        if (r12 == null) goto L47;
        r12.dc_id = r04.dc_id;
        r12.file_reference = r04.file_reference;
    L47:
        r2 = r2 + 1;
    L48:
        this.photoThumbsObject = this.messageOwner.action.photo;
        return;
    L19:
        ArrayList<TLRPC.PhotoSize> r94 = this.photoThumbs;
        if (r94 == null) goto L38;
        if (r94.isEmpty() == true) goto L38;
        int r95 = 0;
    L25:
        if (r95 >= this.photoThumbs.size()) goto L38;
        TLRPC.PhotoSize r13 = this.photoThumbs.get(r95);
        int r3 = 0;
    L28:
        if (r3 >= r04.sizes.size()) goto L36;
        TLRPC.PhotoSize r4 = r04.sizes.get(r3);
        if ((r4 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L35;
        if (r4.type.equals(r13.type) == false) goto L35;
        r13.location = r4.location;
    L35:
        r3 = r3 + 1;
    L36:
        r95 = r95 + 1;
        goto L25
    L325:
        return;
    L51:
        if (this.emojiAnimatedSticker != null) goto L280;
        if (this.emojiAnimatedStickerId != null) goto L280;
        if (getMedia(r1) != null) goto L58;
    L209:
        TLRPC.Message r05 = this.messageOwner;
        if (r05 == null) goto L239;
        TL_iv.RichMessage r06 = r05.rich_message;
        if (r06 == null) goto L239;
        TLRPC.Document r07 = findVideo(r06);
        TLRPC.Photo r14 = findPhoto(this.messageOwner.rich_message);
        if (r07 != null) goto L216;
        if (r14 == null) goto L333;
        if (r9 == false) goto L234;
        ArrayList<TLRPC.PhotoSize> r96 = this.photoThumbs;
        if (r96 == null) goto L234;
        if (r96.isEmpty() == true) goto L235;
        updatePhotoSizeLocations(this.photoThumbs, r14.sizes);
    L235:
        this.photoThumbsObject = r14;
        if (this.strippedThumb != null) goto L334;
        createStrippedThumb();
        return;
    L334:
        return;
    L234:
        this.photoThumbs = new ArrayList(r14.sizes);
        goto L235
    L333:
        return;
    L216:
        if (isDocumentHasThumb(r07) == false) goto L332;
        if (r9 == true) goto L219;
        ArrayList<TLRPC.PhotoSize> r97 = new ArrayList();
        this.photoThumbs = r97;
        r97.addAll(r07.thumbs);
    L224:
        this.photoThumbsObject = r07;
        return;
    L219:
        ArrayList<TLRPC.PhotoSize> r98 = this.photoThumbs;
        if (r98 == null) goto L224;
        if (r98.isEmpty() == true) goto L224;
        updatePhotoSizeLocations(this.photoThumbs, r07.thumbs);
        goto L224
    L332:
        return;
    L239:
        TLRPC.MessageMedia r08 = this.sponsoredMedia;
        if (r08 == null) goto L266;
        TLRPC.Photo r15 = r08.photo;
        TLRPC.Document r09 = r08.document;
        if (r15 == null) goto L253;
        if (r9 == false) goto L250;
        ArrayList<TLRPC.PhotoSize> r99 = this.photoThumbs;
        if (r99 == null) goto L250;
        if (r99.isEmpty() == true) goto L251;
        updatePhotoSizeLocations(this.photoThumbs, r15.sizes);
    L251:
        this.photoThumbsObject = r15;
        return;
    L250:
        this.photoThumbs = new ArrayList(r15.sizes);
        goto L251
    L253:
        if (r09 != null) goto L255;
        return;
    L255:
        if (isDocumentHasThumb(r09) == false) goto L336;
        if (r9 == true) goto L258;
        ArrayList<TLRPC.PhotoSize> r910 = new ArrayList();
        this.photoThumbs = r910;
        r910.addAll(r09.thumbs);
    L263:
        this.photoThumbsObject = r09;
        return;
    L258:
        ArrayList<TLRPC.PhotoSize> r911 = this.photoThumbs;
        if (r911 == null) goto L263;
        if (r911.isEmpty() == true) goto L263;
        updatePhotoSizeLocations(this.photoThumbs, r09.thumbs);
        goto L263
    L336:
        return;
    L266:
        if (this.sponsoredPhoto == null) goto L337;
        if (r9 == false) goto L274;
        ArrayList<TLRPC.PhotoSize> r912 = this.photoThumbs;
        if (r912 == null) goto L274;
        if (r912.isEmpty() == true) goto L275;
        updatePhotoSizeLocations(this.photoThumbs, this.sponsoredPhoto.sizes);
    L275:
        this.photoThumbsObject = this.sponsoredPhoto;
        if (this.strippedThumb != null) goto L338;
        createStrippedThumb();
        return;
    L338:
        return;
    L274:
        this.photoThumbs = new ArrayList(this.sponsoredPhoto.sizes);
        goto L275
    L337:
        return;
    L58:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaEmpty) == true) goto L209;
        boolean r010 = getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPhoto;
        TLRPC.Message r16 = this.messageOwner;
        if (r010 == false) goto L99;
        TLRPC.Photo r011 = getMedia(r16).photo;
        if (r9 == false) goto L96;
        ArrayList<TLRPC.PhotoSize> r913 = this.photoThumbs;
        if (r913 != null) goto L66;
    L68:
        ArrayList<TLRPC.PhotoSize> r914 = this.photoThumbs;
        if (r914 != null) goto L71;
    L97:
        this.photoThumbsObject = getMedia(this.messageOwner).photo;
        return;
    L71:
        if (r914.isEmpty() == true) goto L97;
        int r915 = 0;
    L74:
        if (r915 >= this.photoThumbs.size()) goto L97;
        TLRPC.PhotoSize r17 = this.photoThumbs.get(r915);
        if (r17 == null) goto L95;
        int r42 = 0;
    L80:
        if (r42 >= r011.sizes.size()) goto L95;
        TLRPC.PhotoSize r5 = r011.sizes.get(r42);
        if (r5 == null) goto L94;
        if ((r5 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L94;
        if (r5.type.equals(r17.type) == true) goto L88;
        if ("s".equals(r17.type) == false) goto L94;
        if ((r5 instanceof TLRPC.TL_photoStrippedSize) == false) goto L94;
        this.photoThumbs.set(r915, r5);
        goto L95
    L88:
        r17.location = r5.location;
    L94:
        r42 = r42 + 1;
    L95:
        r915 = r915 + 1;
        goto L74
    L66:
        if (r913.size() == r011.sizes.size()) goto L68;
    L96:
        this.photoThumbs = new ArrayList(r011.sizes);
        goto L97
    L99:
        boolean r012 = getMedia(r16) instanceof TLRPC.TL_messageMediaDocument;
        TLRPC.Message r18 = this.messageOwner;
        if (r012 == false) goto L152;
        TLRPC.Photo r013 = ((TLRPC.TL_messageMediaDocument) getMedia(r18)).video_cover;
        if (r013 == null) goto L140;
        if (r9 == false) goto L137;
        ArrayList<TLRPC.PhotoSize> r916 = this.photoThumbs;
        if (r916 != null) goto L107;
    L109:
        ArrayList<TLRPC.PhotoSize> r917 = this.photoThumbs;
        if (r917 != null) goto L112;
    L138:
        this.photoThumbsObject = r013;
        return;
    L112:
        if (r917.isEmpty() == true) goto L138;
        int r918 = 0;
    L115:
        if (r918 >= this.photoThumbs.size()) goto L138;
        TLRPC.PhotoSize r19 = this.photoThumbs.get(r918);
        if (r19 == null) goto L136;
        int r43 = 0;
    L121:
        if (r43 >= r013.sizes.size()) goto L136;
        TLRPC.PhotoSize r52 = r013.sizes.get(r43);
        if (r52 == null) goto L135;
        if ((r52 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L135;
        if (r52.type.equals(r19.type) == true) goto L129;
        if ("s".equals(r19.type) == false) goto L135;
        if ((r52 instanceof TLRPC.TL_photoStrippedSize) == false) goto L135;
        this.photoThumbs.set(r918, r52);
        goto L136
    L129:
        r19.location = r52.location;
    L135:
        r43 = r43 + 1;
    L136:
        r918 = r918 + 1;
        goto L115
    L107:
        if (r916.size() == r013.sizes.size()) goto L109;
    L137:
        this.photoThumbs = new ArrayList(r013.sizes);
        goto L138
    L140:
        TLRPC.Document r014 = getDocument();
        if (isDocumentHasThumb(r014) == false) goto L326;
        if (r9 == false) goto L149;
        ArrayList<TLRPC.PhotoSize> r919 = this.photoThumbs;
        if (r919 == null) goto L149;
        if (r919.isEmpty() == true) goto L150;
        updatePhotoSizeLocations(this.photoThumbs, r014.thumbs);
    L150:
        this.photoThumbsObject = r014;
        return;
    L149:
        ArrayList<TLRPC.PhotoSize> r920 = new ArrayList();
        this.photoThumbs = r920;
        r920.addAll(r014.thumbs);
        goto L150
    L326:
        return;
    L152:
        boolean r015 = getMedia(r18) instanceof TLRPC.TL_messageMediaGame;
        TLRPC.Message r110 = this.messageOwner;
        if (r015 == false) goto L184;
        TLRPC.Document r016 = getMedia(r110).game.document;
        if (r016 != null) goto L157;
    L166:
        TLRPC.Photo r017 = getMedia(this.messageOwner).game.photo;
        if (r017 == null) goto L178;
        if (r9 == false) goto L175;
        ArrayList<TLRPC.PhotoSize> r921 = this.photoThumbs2;
        if (r921 == null) goto L175;
        if (r921.isEmpty() == true) goto L176;
        updatePhotoSizeLocations(this.photoThumbs2, r017.sizes);
    L176:
        this.photoThumbsObject2 = r017;
    L175:
        this.photoThumbs2 = new ArrayList(r017.sizes);
    L178:
        if (this.photoThumbs != null) goto L327;
        ArrayList<TLRPC.PhotoSize> r922 = this.photoThumbs2;
        if (r922 == null) goto L328;
        this.photoThumbs = r922;
        this.photoThumbs2 = null;
        this.photoThumbsObject = this.photoThumbsObject2;
        this.photoThumbsObject2 = null;
        return;
    L328:
        return;
    L327:
        return;
    L157:
        if (isDocumentHasThumb(r016) == false) goto L166;
        if (r9 == true) goto L160;
        ArrayList<TLRPC.PhotoSize> r111 = new ArrayList();
        this.photoThumbs = r111;
        r111.addAll(r016.thumbs);
    L165:
        this.photoThumbsObject = r016;
        goto L166
    L160:
        ArrayList<TLRPC.PhotoSize> r112 = this.photoThumbs;
        if (r112 == null) goto L165;
        if (r112.isEmpty() == true) goto L165;
        updatePhotoSizeLocations(this.photoThumbs, r016.thumbs);
        goto L165
    L184:
        if ((getMedia(r110) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L329;
        TLRPC.Photo r018 = getMedia(this.messageOwner).webpage.photo;
        TLRPC.Document r113 = getMedia(this.messageOwner).webpage.document;
        if (r018 == null) goto L197;
        if (r9 == false) goto L194;
        ArrayList<TLRPC.PhotoSize> r923 = this.photoThumbs;
        if (r923 == null) goto L194;
        if (r923.isEmpty() == true) goto L195;
        updatePhotoSizeLocations(this.photoThumbs, r018.sizes);
    L195:
        this.photoThumbsObject = r018;
        return;
    L194:
        this.photoThumbs = new ArrayList(r018.sizes);
        goto L195
    L197:
        if (r113 != null) goto L199;
        return;
    L199:
        if (isDocumentHasThumb(r113) == false) goto L331;
        if (r9 == true) goto L202;
        ArrayList<TLRPC.PhotoSize> r924 = new ArrayList();
        this.photoThumbs = r924;
        r924.addAll(r113.thumbs);
    L207:
        this.photoThumbsObject = r113;
        return;
    L202:
        ArrayList<TLRPC.PhotoSize> r925 = this.photoThumbs;
        if (r925 == null) goto L207;
        if (r925.isEmpty() == true) goto L207;
        updatePhotoSizeLocations(this.photoThumbs, r113.thumbs);
        goto L207
    L331:
        return;
    L329:
        return;
    L280:
        if (TextUtils.isEmpty(this.emojiAnimatedStickerColor) == true) goto L282;
        return;
    L282:
        if (isDocumentHasThumb(this.emojiAnimatedSticker) == false) goto L340;
        if (r9 == false) goto L290;
        ArrayList<TLRPC.PhotoSize> r926 = this.photoThumbs;
        if (r926 == null) goto L290;
        if (r926.isEmpty() == true) goto L291;
        updatePhotoSizeLocations(this.photoThumbs, this.emojiAnimatedSticker.thumbs);
    L291:
        this.photoThumbsObject = this.emojiAnimatedSticker;
        return;
    L290:
        ArrayList<TLRPC.PhotoSize> r927 = new ArrayList();
        this.photoThumbs = r927;
        r927.addAll(this.emojiAnimatedSticker.thumbs);
        goto L291
    }

    public int getApproximateHeight() {
        return getApproximateHeight(false);
    }

    public int getApproximateHeight(boolean r9) {
        int r0 = this.type;
        int r1 = 0;
        if (r0 != 0) goto L17;
        if (r9 == false) goto L6;
        int r92 = textHeightCached();
    L8:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L10;
    L12:
        int r93 = r92 + r1;
        if (isReply() == true) goto L15;
        return r93;
    L15:
        return r93 + AndroidUtilities.dp(42.0f);
    L10:
        if ((getMedia(this.messageOwner).webpage instanceof TLRPC.TL_webPage) == false) goto L12;
        r1 = AndroidUtilities.dp(100.0f);
        goto L12
    L6:
        r92 = textHeight();
        goto L8
    L17:
        if (r0 != 20) goto L21;
        return AndroidUtilities.getPhotoSize();
    L21:
        if (r0 != 2) goto L25;
        return AndroidUtilities.dp(72.0f);
    L25:
        if (r0 != 12) goto L29;
        return AndroidUtilities.dp(71.0f);
    L29:
        if (r0 != 9) goto L33;
        return AndroidUtilities.dp(100.0f);
    L33:
        if (r0 != 4) goto L37;
        return AndroidUtilities.dp(114.0f);
    L37:
        if (r0 != 14) goto L41;
        return AndroidUtilities.dp(82.0f);
    L41:
        if (r0 == 10) goto L149;
        if (r0 == 35) goto L149;
        if (r0 == 33) goto L149;
        if (r0 == 34) goto L149;
        if (r0 == 11) goto L147;
        if (r0 == 18) goto L147;
        if (r0 == 31) goto L147;
        if (r0 == 30) goto L147;
        if (r0 == 25) goto L147;
        if (r0 == 21) goto L147;
        if (r0 != 32) goto L67;
        return AndroidUtilities.dp(234.0f);
    L67:
        if (r0 != 5) goto L71;
        return AndroidUtilities.roundMessageSize;
    L71:
        if (r0 != 19) goto L78;
        if (r9 == false) goto L74;
        int r8 = textHeightCached();
    L76:
        return r8 + AndroidUtilities.dp(30.0f);
    L74:
        r8 = textHeight();
        goto L76
    L78:
        if (r0 != 13) goto L80;
    L122:
        float r94 = AndroidUtilities.displaySize.y * 0.4f;
        if (AndroidUtilities.isTablet() == false) goto L126;
        int r02 = AndroidUtilities.getMinTabletSide();
    L125:
        float r03 = r02 * 0.5f;
        TLRPC.Document r82 = getDocument();
        if (r82 == null) goto L135;
        int r3 = r82.attributes.size();
        int r5 = 0;
    L130:
        if (r5 >= r3) goto L135;
        TLRPC.DocumentAttribute r6 = r82.attributes.get(r5);
        if ((r6 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L133;
        r5 = r5 + 1;
        goto L130
    L133:
        r1 = r6.w;
        int r83 = r6.h;
    L136:
        if (r1 != 0) goto L138;
        r83 = (int) r94;
        r1 = AndroidUtilities.dp(100.0f) + r83;
    L138:
        float r2 = r83;
        if (r2 <= r94) goto L141;
        r1 = (int) (r1 * (r94 / r2));
        r83 = (int) r94;
    L141:
        float r95 = r1;
        if (r95 <= r03) goto L145;
        r83 = (int) (r83 * (r03 / r95));
    L145:
        return r83 + AndroidUtilities.dp(14.0f);
    L135:
        r83 = 0;
        goto L136
    L126:
        r02 = AndroidUtilities.displaySize.x;
        goto L125
    L80:
        if (r0 == 15) goto L122;
        if (AndroidUtilities.isTablet() == false) goto L86;
        int r04 = AndroidUtilities.getMinTabletSide();
    L85:
        int r05 = (int) (r04 * 0.7f);
        int r32 = AndroidUtilities.dp(100.0f) + r05;
        if (r05 <= AndroidUtilities.getPhotoSize()) goto L91;
        r05 = AndroidUtilities.getPhotoSize();
    L91:
        if (r32 <= AndroidUtilities.getPhotoSize()) goto L93;
        r32 = AndroidUtilities.getPhotoSize();
    L93:
        ArrayList<TLRPC.PhotoSize> r62 = this.photoThumbs;
        if (r9 == false) goto L101;
        if (r62 != null) goto L97;
    L100:
        TLRPC.PhotoSize r12 = null;
    L102:
        if (r12 == null) goto L121;
        int r06 = (int) (r12.h / (r12.w / r05));
        if (r06 != 0) goto L106;
        r06 = AndroidUtilities.dp(100.0f);
    L106:
        if (r06 <= r32) goto L109;
    L112:
        if (r9 == true) goto L121;
        if (needDrawBluredPreview() == false) goto L121;
        if (AndroidUtilities.isTablet() == false) goto L119;
        int r84 = AndroidUtilities.getMinTabletSide();
    L118:
        r32 = (int) (r84 * 0.5f);
        goto L121
    L119:
        Point r85 = AndroidUtilities.displaySize;
        r84 = Math.min(r85.x, r85.y);
        goto L118
    L109:
        if (r06 >= AndroidUtilities.dp(120.0f)) goto L111;
        r32 = AndroidUtilities.dp(120.0f);
        goto L112
    L111:
        r32 = r06;
    L121:
        return r32 + AndroidUtilities.dp(14.0f);
    L97:
        if (r62.isEmpty() == true) goto L100;
        r12 = this.photoThumbs.get(0);
        goto L102
    L101:
        r12 = FileLoader.getClosestPhotoSizeWithSize(r62, AndroidUtilities.getPhotoSize());
        goto L102
    L86:
        Point r07 = AndroidUtilities.displaySize;
        r04 = Math.min(r07.x, r07.y);
    L147:
        return AndroidUtilities.dp(50.0f);
    L149:
        return AndroidUtilities.dp(30.0f);
    }

    public int getApproximateHeightCached() {
        Integer r0 = this.cachedApproximateHeight;
        if (r0 != null) goto L5;
        int r02 = getApproximateHeight(true);
        this.cachedApproximateHeight = Integer.valueOf(r02);
        return r02;
    L5:
        return r0.intValue();
    }

    public String getArtworkUrl(boolean r1) {
        return getArtworkUrl(getDocument(), r1);
    }

    public long getChannelId() {
        return getChannelId(this.messageOwner);
    }

    public long getChatId() {
        TLRPC.Peer r2 = this.messageOwner.peer_id;
        if ((r2 instanceof TLRPC.TL_peerChat) == false) goto L7;
        return r2.chat_id;
    L7:
        if ((r2 instanceof TLRPC.TL_peerChannel) == true) goto L9;
        return 0;
    L9:
        return r2.channel_id;
    }

    public int getChatMode() {
        if (this.scheduled == false) goto L7;
        return 1;
    L7:
        if (isWelcomeMessage() == false) goto L11;
        return 9;
    L11:
        if (isQuickReply() == false) goto L14;
        return 5;
    L14:
        return 0;
    }

    public ArrayList<ReactionsLayoutInBubble.VisibleReaction> getChoosenReactions() {
        ArrayList<ReactionsLayoutInBubble.VisibleReaction> r0 = new ArrayList();
        if (this.messageOwner.reactions == null) goto L12;
        int r1 = 0;
    L7:
        if (r1 >= this.messageOwner.reactions.results.size()) goto L12;
        if (this.messageOwner.reactions.results.get(r1).chosen == false) goto L11;
        r0.add(ReactionsLayoutInBubble.VisibleReaction.fromTL(this.messageOwner.reactions.results.get(r1).reaction));
    L11:
        r1 = r1 + 1;
    L12:
        return r0;
    }

    public long getDialogId() {
        return getDialogId(this.messageOwner);
    }

    public String getDiceEmoji() {
        if (isDice() == true) goto L6;
        return null;
    L6:
        TLRPC.TL_messageMediaDice r2 = (TLRPC.TL_messageMediaDice) getMedia(this.messageOwner);
        if (TextUtils.isEmpty(r2.emoticon) == false) goto L11;
        return "🎲";
    L11:
        return r2.emoticon.replace("️", _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public String getDiceEmoji(TLRPC.TL_messageMediaDice r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        if (TextUtils.isEmpty(r2.emoticon) == false) goto L10;
        return "🎲";
    L10:
        return r2.emoticon.replace("️", _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public int getDiceValue() {
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDice) == true) goto L5;
        return -1;
    L5:
        return ((TLRPC.TL_messageMediaDice) getMedia(this.messageOwner)).value;
    }

    public TL_iv.RichMessage getDisplayRichMessage() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L7;
        return null;
    L7:
        if (this.translated == false) goto L12;
        TL_iv.RichMessage r1 = r0.translatedRichMessage;
        if (r1 == null) goto L12;
        return r1;
    L12:
        return r0.rich_message;
    }

    public TLRPC.Document getDocument() {
        TLRPC.Document r0 = this.emojiAnimatedSticker;
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (hasVideoQualities() == false) goto L12;
        VideoPlayer.VideoUri r02 = this.highestQuality;
        if (r02 == null) goto L12;
        return r02.document;
    L12:
        return getDocument(this.messageOwner);
    }

    public TLRPC.Document getDocumentFast() {
        TLRPC.Document r0 = this.emojiAnimatedSticker;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return getDocument(this.messageOwner);
    }

    public String getDocumentName() {
        return FileLoader.getDocumentFileName(getDocument());
    }

    public double getDuration() {
        double r0 = this.attributeDuration;
        if (r0 <= 0.0d) goto L5;
        return r0;
    L5:
        TLRPC.Document r02 = getDocument();
        if (r02 == null) goto L8;
    L14:
        if (r02 != null) goto L16;
        return 0.0d;
    L16:
        int r1 = this.audioPlayerDuration;
        if (r1 > 0) goto L19;
        int r12 = 0;
    L22:
        if (r12 >= r02.attributes.size()) goto L33;
        TLRPC.DocumentAttribute r2 = r02.attributes.get(r12);
        if ((r2 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L25;
        if ((r2 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L29;
        r12 = r12 + 1;
        goto L22
    L29:
        double r03 = r2.duration;
        this.attributeDuration = r03;
        return r03;
    L25:
        double r04 = r2.duration;
        this.attributeDuration = r04;
        return r04;
    L33:
        return this.audioPlayerDuration;
    L19:
        return r1;
    L8:
        if (this.type != 23) goto L14;
        TL_stories.StoryItem r13 = getMedia(this.messageOwner).storyItem;
        if (r13 == null) goto L14;
        TLRPC.MessageMedia r14 = r13.media;
        if (r14 == null) goto L14;
        r02 = r14.document;
        goto L14
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int getEditedSuggestionFlags() {
        MessageObject r0 = this.replyMessageObject;
        TLRPC.MessageMedia r1 = null;
        if (r0 == null) goto L5;
        TLRPC.Message r02 = r0.messageOwner;
    L6:
        TLRPC.Message r2 = this.messageOwner;
        boolean r3 = false;
        if (r02 == null) goto L79;
        TLRPC.SuggestedPost r4 = r02.suggested_post;
        if (r4 == null) goto L79;
        if (r2 == null) goto L79;
        TLRPC.SuggestedPost r5 = r2.suggested_post;
        if (r5 == null) goto L79;
        int r6 = !AmountUtils$Amount.equals(r4.price, r5.price) ? 1 : 0;
        if (r4.schedule_date == r5.schedule_date) goto L18;
        r6 = r6 | 2;
    L18:
        if (TextUtils.equals(this.messageText, this.replyMessageObject.messageText) == true) goto L21;
        r6 = r6 | 4;
    L21:
        if (TextUtils.equals(this.caption, this.replyMessageObject.caption) == true) goto L23;
        r6 = r6 | 4;
    L23:
        TLRPC.MessageMedia r10 = r02.media;
        TLRPC.MessageMedia r03 = r2.media;
        if ((r10 instanceof TLRPC.TL_messageMediaEmpty) == false) goto L27;
        r10 = null;
    L27:
        if ((r03 instanceof TLRPC.TL_messageMediaEmpty) == true) goto L30;
        r1 = r03;
    L30:
        if (r10 == null) goto L70;
        if (r1 == null) goto L70;
        if (r10.getClass() != r1.getClass()) goto L69;
        TLRPC.Photo r04 = r10.photo;
        if (r04 == null) goto L41;
        TLRPC.Photo r22 = r1.photo;
        if (r22 == null) goto L41;
        if (r04.id == r22.id) goto L50;
    L40:
        r6 = r6 | 8;
    L50:
        TLRPC.Document r102 = r10.document;
        if (r102 == null) goto L58;
        TLRPC.Document r05 = r1.document;
        if (r05 == null) goto L58;
        if (r102.id != r05.id) goto L57;
    L67:
        return r6;
    L57:
        return r6 | 8;
    L58:
        if (r102 != null) goto L60;
        boolean r103 = true;
    L62:
        if (r1.document != null) goto L64;
        r3 = true;
    L64:
        if (r103 == r3) goto L67;
        return r6 | 8;
    L60:
        r103 = false;
    L41:
        if (r04 != null) goto L43;
        Object[] r06 = true;
    L45:
        if (r1.photo != null) goto L47;
        Object[] r23 = true;
    L48:
        if (r06 == r23) goto L50;
    L47:
        r23 = false;
        goto L48
    L43:
        r06 = false;
        goto L45
    L69:
        return r6 | 8;
    L70:
        if (r10 != null) goto L72;
        boolean r104 = true;
    L73:
        if (r1 != null) goto L75;
        r3 = true;
    L75:
        if (r104 != r3) goto L77;
        return r6;
    L77:
        return r6 | 8;
    L72:
        r104 = false;
    L79:
        return 0;
    L5:
        r02 = null;
        goto L6
    }

    public TLRPC.TL_availableEffect getEffect() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L5;
        return null;
    L5:
        if ((r0.flags2 & 4) != 0) goto L8;
        return null;
    L8:
        return MessagesController.getInstance(this.currentAccount).getEffect(this.messageOwner.effect);
    }

    public long getEffectId() {
        TLRPC.Message r2 = this.messageOwner;
        if (r2 != null) goto L5;
        return 0;
    L5:
        if ((r2.flags2 & 4) != 0) goto L8;
        return 0;
    L8:
        return r2.effect;
    }

    public int getEmojiOnlyCount() {
        return this.emojiOnlyCount;
    }

    public ArrayList<TLRPC.MessageEntity> getEntities() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L5;
        return null;
    L5:
        boolean r2 = this.summarized;
        boolean r3 = this.translated;
        if (r2 == false) goto L17;
        if (r3 == false) goto L12;
        TLRPC.TL_textWithEntities r32 = r0.translatedSummaryText;
        if (r32 == null) goto L12;
        return r32.entities;
    L12:
        TLRPC.TL_textWithEntities r33 = r0.summaryText;
        if (r33 != null) goto L15;
        return null;
    L15:
        return r33.entities;
    L17:
        if (r3 == false) goto L33;
        if (r0.voiceTranscriptionOpen == false) goto L25;
        TLRPC.TL_textWithEntities r34 = r0.translatedVoiceTranscription;
        if (r34 != null) goto L23;
        return null;
    L23:
        return r34.entities;
    L25:
        TLRPC.TL_textWithEntities r35 = r0.translatedText;
        if (r35 != null) goto L28;
        return null;
    L28:
        if (r35 != null) goto L30;
        return null;
    L30:
        return r35.entities;
    L33:
        return r0.entities;
    }

    public int getEphemeralId() {
        if (isEphemeral() == true) goto L5;
        return 0;
    L5:
        return ephemeralMessageIdUnpack(this.messageOwner.id);
    }

    public long getEphemeralReceiverBotId() {
        TLRPC.Message r2 = this.messageOwner;
        if (r2 != null) goto L5;
        return 0;
    L5:
        return r2.ephemeralReceiverBotId;
    }

    public String getExtension() {
        String r0 = getFileName();
        int r1 = r0.lastIndexOf(46);
        if (r1 == (-1)) goto L5;
        String r02 = r0.substring(r1 + 1);
    L6:
        if (r02 != null) goto L8;
    L9:
        r02 = getDocument().mime_type;
    L10:
        if (r02 != null) goto L13;
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        return r02.toUpperCase();
    L8:
        if (r02.length() != 0) goto L10;
    L5:
        r02 = null;
        goto L6
    }

    public TLRPC.TL_factCheck getFactCheck() {
        return FactCheckController.getInstance(this.currentAccount).getFactCheck(this);
    }

    public CharSequence getFactCheckText() {
        if (isFactCheckable() == true) goto L5;
        return null;
    L5:
        TLRPC.TL_factCheck r0 = getFactCheck();
        if (r0 != null) goto L8;
    L12:
        this.factCheckText = null;
        return null;
    L8:
        if (r0.text == null) goto L12;
        SpannableStringBuilder r3 = new SpannableStringBuilder(r0.text.text);
        addEntitiesToText(r3, r0.text.entities, isOutOwner(), false, false, false);
        this.factCheckText = r3;
        return r3;
    }

    public int getFeedRealId() {
        if (this.searchType != 4) goto L7;
        return getRealId();
    L7:
        return getId();
    }

    public String getFileName() {
        if (getDocument() == null) goto L7;
        return getFileName(getDocument());
    L7:
        return getFileName(this.messageOwner);
    }

    public String getFileNameFast() {
        if (getDocumentFast() == null) goto L7;
        return getFileName(getDocumentFast());
    L7:
        return getFileName(this.messageOwner);
    }

    public Long getForwardedFromId() {
        TLRPC.Message r2 = this.messageOwner;
        if (r2 != null) goto L5;
        return null;
    L5:
        TLRPC.MessageFwdHeader r22 = r2.fwd_from;
        if (r22 != null) goto L8;
        return null;
    L8:
        TLRPC.Peer r23 = r22.from_id;
        if (r23 != null) goto L12;
        return null;
    L12:
        return Long.valueOf(DialogObject.getPeerDialogId(r23));
    }

    public TLObject getForwardedFromPeerObject() {
        Long r0 = getForwardedFromId();
        if (r0 != null) goto L7;
        return null;
    L7:
        return MessagesController.getInstance(this.currentAccount).getUserOrChat(r0.longValue());
    }

    public String getForwardedName() {
        TLRPC.MessageFwdHeader r0 = this.messageOwner.fwd_from;
        if (r0 == null) goto L25;
        TLRPC.Peer r1 = r0.from_id;
        if ((r1 instanceof TLRPC.TL_peerChannel) == false) goto L11;
        TLRPC.Chat r3 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.fwd_from.from_id.channel_id));
        if (r3 != null) goto L9;
        return null;
    L9:
        return r3.title;
    L11:
        if ((r1 instanceof TLRPC.TL_peerChat) == false) goto L17;
        TLRPC.Chat r32 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.fwd_from.from_id.chat_id));
        if (r32 != null) goto L15;
        return null;
    L15:
        return r32.title;
    L17:
        if ((r1 instanceof TLRPC.TL_peerUser) == false) goto L22;
        TLRPC.User r33 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.messageOwner.fwd_from.from_id.user_id));
        if (r33 != null) goto L21;
        return null;
    L21:
        return UserObject.getUserName(r33);
    L22:
        String r34 = r0.from_name;
        if (r34 == null) goto L30;
        return r34;
    L30:
        return null;
    L25:
        return null;
    }

    public long getFromChatId() {
        return getFromChatId(this.messageOwner);
    }

    public TLRPC.Peer getFromPeer() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.from_id;
    }

    public TLObject getFromPeerObject() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 == null) goto L24;
        TLRPC.Peer r02 = r0.from_id;
        if ((r02 instanceof TLRPC.TL_peerChannel_layer131) == true) goto L23;
        if ((r02 instanceof TLRPC.TL_peerChannel) == true) goto L23;
        if ((r02 instanceof TLRPC.TL_peerUser_layer131) == true) goto L21;
        if ((r02 instanceof TLRPC.TL_peerUser) == true) goto L21;
        if ((r02 instanceof TLRPC.TL_peerChat_layer131) == true) goto L19;
        if ((r02 instanceof TLRPC.TL_peerChat) == true) goto L19;
        return null;
    L19:
        return MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.from_id.chat_id));
    L21:
        return MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.messageOwner.from_id.user_id));
    L23:
        return MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.from_id.channel_id));
    L24:
        return null;
    }

    public long getGroupId() {
        long r0 = this.localGroupId;
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return getGroupIdForUse();
    }

    public long getGroupIdForUse() {
        long r0 = this.localSentGroupId;
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return this.messageOwner.grouped_id;
    }

    public int getId() {
        TLRPC.Message r1 = this.messageOwner;
        int r0 = r1.ephemeralAnchorMsgId;
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return r1.id;
    }

    public BotInlineKeyboard.Source getInlineBotButtons() {
        return this.inlineKeyboardSource;
    }

    public TLRPC.InputStickerSet getInputStickerSet() {
        return getInputStickerSet(this.messageOwner);
    }

    public int getLastLineWidth() {
        RichMessageLayout r0 = this.richLayout;
        if (r0 == null) goto L7;
        return r0.getLastLineWidth();
    L7:
        return this.lastLineWidth;
    }

    public int getMaxMessageTextWidth() {
        if (AndroidUtilities.isTablet() == true) goto L5;
    L7:
        this.generatedWithMinSize = getParentWidth();
    L8:
        this.generatedWithDensity = AndroidUtilities.density;
        com.exteragram.messenger.utils.ui.TextPaint r0 = Theme.chat_msgTextPaint;
        if (r0 == null) goto L11;
        float r02 = r0.getTextSize();
    L12:
        this.generatedWithFontSize = r02;
        if (this.hasCode == false) goto L28;
        if (this.isSaved == true) goto L28;
        int r03 = this.generatedWithMinSize - AndroidUtilities.dp(60.0f);
        if (this.sideMenuEnabled == false) goto L21;
        int r3 = AndroidUtilities.dp(64.0f);
    L19:
        r03 = r03 - r3;
    L48:
        if (r03 != 0) goto L79;
        boolean r04 = needDrawAvatarInternal();
        int r32 = this.generatedWithMinSize;
        float r6 = 40.0f;
        if (this.type != 36) goto L52;
        float r4 = 40.0f;
    L53:
        int r33 = r32 - AndroidUtilities.dp(r4);
        if (this.sideMenuEnabled == false) goto L57;
        int r05 = AndroidUtilities.dp(64.0f);
    L56:
        r33 = r33 - r05;
    L64:
        if (needDrawShareButton() == true) goto L66;
    L76:
        r03 = r33;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGame) == false) goto L79;
        r03 = r03 - AndroidUtilities.dp(10.0f);
        goto L79
    L66:
        if (this.isSaved == true) goto L70;
        if (isOutOwner() == true) goto L76;
    L70:
        if (this.isSaved == true) goto L72;
    L74:
        r6 = 14.0f;
    L75:
        r33 = r33 - AndroidUtilities.dp(r6);
        goto L76
    L72:
        if (isOutOwner() == false) goto L74;
    L57:
        if (r04 == false) goto L64;
        if (isOutOwner() == true) goto L64;
        if (this.messageOwner.isThreadMessage == true) goto L64;
        r05 = AndroidUtilities.dp(52.0f);
        goto L56
    L52:
        r4 = 80.0f;
    L79:
        int r1 = this.emojiOnlyCount;
        if (r1 < 1) goto L92;
        int r2 = this.totalAnimatedEmojiCount;
        int r34 = 100;
        if (r2 > 100) goto L92;
        int r12 = r1 - r2;
        if (SharedConfig.getDevicePerformanceClass() < 2) goto L86;
        r34 = 200;
    L86:
        if (r12 >= r34) goto L92;
        if (hasValidReplyMessageObject() == false) goto L90;
    L91:
        r03 = Math.min(r03, (int) (this.generatedWithMinSize * 0.65f));
        goto L92
    L90:
        if (isForwarded() == true) goto L91;
    L92:
        int r8 = this.widePostsTextWidth;
        if (r8 > 0) goto L95;
        return r03;
    L95:
        return r8;
    L21:
        if (needDrawAvatarInternal() == false) goto L48;
        if (isOutOwner() == true) goto L48;
        if (this.messageOwner.isThreadMessage == true) goto L48;
        r3 = AndroidUtilities.dp(52.0f);
    L28:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L45;
        if (getMedia(this.messageOwner).webpage == null) goto L45;
        if ("telegram_background".equals(getMedia(this.messageOwner).webpage.type) == false) goto L45;
        Uri r06 = Uri.parse(getMedia(this.messageOwner).webpage.url);     // Catch: Exception -> L96
        String r5 = r06.getLastPathSegment();     // Catch: Exception -> L96
        if (r06.getQueryParameter("bg_color") == null) goto L37;
        r03 = AndroidUtilities.dp(220.0f);     // Catch: Exception -> L96
        goto L48
    L37:
        if (r5.length() != 6) goto L39;
    L42:
        r03 = AndroidUtilities.dp(200.0f);     // Catch: Exception -> L96
        goto L48
    L39:
        if (r5.length() != 13) goto L47;
        if (r5.charAt(6) == '-') goto L42;
    L47:
        r03 = 0;
    L45:
        if (isAndroidTheme() == false) goto L47;
        r03 = AndroidUtilities.dp(200.0f);
        goto L48
    L11:
        r02 = 0.0f;
        goto L12
    L5:
        if (this.eventId == 0) goto L7;
        this.generatedWithMinSize = AndroidUtilities.dp(530.0f);
        goto L8
    }

    public int getMediaExistanceFlags() {
        boolean r0 = this.attachPathExists;
        if (this.mediaExists == true) goto L5;
        return r0 ? 1 : 0;
    L5:
        return (r0 ? 1 : 0) | 2;
    }

    public CharSequence getMediaTitle(TLRPC.MessageMedia r7) {
        if ((r7 instanceof TLRPC.TL_messageMediaGiveaway) == false) goto L7;
        return LocaleController.getString(R.string.BoostingGiveaway);
    L7:
        if ((r7 instanceof TLRPC.TL_messageMediaGiveawayResults) == true) goto L9;
        String r1 = null;
        if ((r7 instanceof TLRPC.TL_messageMediaStory) == false) goto L27;
        if (r7.via_mention == false) goto L25;
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r7.user_id));
        if (r0 == null) goto L19;
        String r02 = UserObject.getPublicUsername(r0);
        if (r02 == null) goto L19;
        r1 = MessagesController.getInstance(this.currentAccount).linkPrefix + "/" + r02 + "/s/" + r7.id;
    L19:
        if (r1 == null) goto L22;
        SpannableString r6 = new SpannableString(r1);
        r6.setSpan(new URLSpanReplacement("https://".concat(r1), new TextStyleSpan.TextStyleRun()), 0, r6.length(), 33);
        return r6;
    L22:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L25:
        return LocaleController.getString(R.string.ForwardedStory);
    L27:
        if ((r7 instanceof TLRPC.TL_messageMediaDice) == false) goto L31;
        return getDiceEmoji((TLRPC.TL_messageMediaDice) r7);
    L31:
        if ((r7 instanceof TLRPC.TL_messageMediaPoll) == false) goto L39;
        if (((TLRPC.TL_messageMediaPoll) r7).poll.quiz == false) goto L37;
        return LocaleController.getString(R.string.QuizPoll);
    L37:
        return LocaleController.getString(R.string.Poll);
    L39:
        if ((r7 instanceof TLRPC.TL_messageMediaToDo) == false) goto L51;
        TLRPC.TL_messageMediaToDo r72 = (TLRPC.TL_messageMediaToDo) r7;
        TLRPC.TodoList r03 = r72.todo;
        if (r03 == null) goto L49;
        TLRPC.TL_textWithEntities r04 = r03.title;
        if (r04 == null) goto L49;
        if (r04.text.isEmpty() == true) goto L49;
        return LocaleController.formatSpannable(R.string.MessageTodoName, new Object[]{formatTextWithEntities(r72.todo.title, isOutOwner())});
    L49:
        return LocaleController.getString(R.string.MessageTodo);
    L51:
        if ((r7 instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L82;
        TLRPC.TL_messageMediaPaidMedia r73 = (TLRPC.TL_messageMediaPaidMedia) r7;
        int r62 = r73.extended_media.size();
        int r05 = 0;
        boolean r12 = false;
    L53:
        if (r05 >= r62) goto L70;
        TLRPC.MessageExtendedMedia r4 = r73.extended_media.get(r05);
        if ((r4 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L63;
        TLRPC.MessageMedia r13 = ((TLRPC.TL_messageExtendedMedia) r4).media;
        if ((r13 instanceof TLRPC.TL_messageMediaDocument) == true) goto L59;
    L61:
        r12 = false;
    L67:
        if (r12 == true) goto L70;
        r05 = r05 + 1;
        goto L53
    L59:
        if (isVideoDocument(r13.document) == false) goto L61;
    L60:
        r12 = true;
        goto L67
    L63:
        if ((r4 instanceof TLRPC.TL_messageExtendedMediaPreview) == false) goto L67;
        if ((((TLRPC.TL_messageExtendedMediaPreview) r4).flags & 4) == 0) goto L61;
    L70:
        if (r62 != 1) goto L75;
        if (r12 == false) goto L73;
        int r63 = R.string.AttachVideo;
    L74:
        String r64 = LocaleController.getString(r63);
    L80:
        return StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.AttachPaidMedia, new Object[]{r64}));
    L73:
        r63 = R.string.AttachPhoto;
        goto L74
    L75:
        if (r12 == false) goto L77;
        String r74 = "Media";
    L78:
        r64 = LocaleController.formatPluralString(r74, r62, new Object[0]);
        goto L80
    L77:
        r74 = "Photos";
        goto L78
    L82:
        if ((r7 instanceof TLRPC.TL_messageMediaPhoto) == true) goto L84;
        if (r7 != null) goto L101;
    L125:
        if (r7 != null) goto L127;
    L130:
        if (r7 == null) goto L136;
        if (isRoundVideoDocument(r7.document) == false) goto L136;
        return LocaleController.getString(R.string.AttachRound);
    L136:
        if ((r7 instanceof TLRPC.TL_messageMediaGeo) == true) goto L190;
        if ((r7 instanceof TLRPC.TL_messageMediaVenue) == true) goto L190;
        if ((r7 instanceof TLRPC.TL_messageMediaGeoLive) == false) goto L145;
        return LocaleController.getString(R.string.AttachLiveLocation);
    L145:
        if ((r7 instanceof TLRPC.TL_messageMediaContact) == false) goto L149;
        return LocaleController.getString(R.string.AttachContact);
    L149:
        if ((r7 instanceof TLRPC.TL_messageMediaGame) == false) goto L153;
        return this.messageOwner.message;
    L153:
        if ((r7 instanceof TLRPC.TL_messageMediaInvoice) == false) goto L157;
        return r7.description;
    L157:
        if ((r7 instanceof TLRPC.TL_messageMediaUnsupported) == false) goto L161;
        return LocaleController.getString(R.string.UnsupportedMedia2);
    L161:
        if ((r7 instanceof TLRPC.TL_messageMediaDocument) == true) goto L163;
        return null;
    L163:
        if (isStickerDocument(r7.document) == false) goto L165;
    L180:
        String r65 = getStickerChar();
        if (r65 == null) goto L187;
        if (r65.length() <= 0) goto L187;
        return String.format("%s %s", new Object[]{r65, LocaleController.getString(R.string.AttachSticker)});
    L187:
        return LocaleController.getString(R.string.AttachSticker);
    L165:
        if (isAnimatedStickerDocument(r7.document, true) == true) goto L180;
        if (isMusic() == false) goto L172;
        return LocaleController.getString(R.string.AttachMusic);
    L172:
        if (isGif() == true) goto L174;
        String r66 = FileLoader.getDocumentFileName(r7.document);
        if (TextUtils.isEmpty(r66) == true) goto L179;
        return r66;
    L179:
        return LocaleController.getString(R.string.AttachDocument);
    L174:
        return LocaleController.getString(R.string.AttachGif);
    L190:
        return LocaleController.getString(R.string.AttachLocation);
    L127:
        if (isVoiceDocument(r7.document) == false) goto L130;
        return LocaleController.getString(R.string.AttachAudio);
    L101:
        if (isVideoDocument(r7.document) == true) goto L110;
        if ((r7 instanceof TLRPC.TL_messageMediaDocument) == false) goto L125;
        TLRPC.Document r06 = r7.document;
        if ((r06 instanceof TLRPC.TL_documentEmpty) == true) goto L108;
        if (r06 != null) goto L125;
    L108:
        if (r7.ttl_seconds == 0) goto L125;
    L110:
        if (r7.ttl_seconds == 0) goto L124;
        if ((this.messageOwner instanceof TLRPC.TL_message_secret) == true) goto L124;
        if (r7.voice == false) goto L118;
        return LocaleController.getString(R.string.AttachVoiceExpired);
    L118:
        if (r7.round == false) goto L122;
        return LocaleController.getString(R.string.AttachRoundExpired);
    L122:
        return LocaleController.getString(R.string.AttachDestructingVideo);
    L124:
        return LocaleController.getString(R.string.AttachVideo);
    L84:
        if (r7.ttl_seconds == 0) goto L90;
        if ((this.messageOwner instanceof TLRPC.TL_message_secret) == true) goto L90;
        return LocaleController.getString(R.string.AttachDestructingPhoto);
    L90:
        if (getGroupId() == 0) goto L94;
        return LocaleController.getString(R.string.Album);
    L94:
        if (isLivePhoto() == false) goto L98;
        return LocaleController.getString(R.string.AttachLivePhoto);
    L98:
        return LocaleController.getString(R.string.AttachPhoto);
    L9:
        return LocaleController.getString(R.string.BoostingGiveawayResults);
    }

    public int getMediaType() {
        if (isVideo() == false) goto L7;
        return 2;
    L7:
        if (isVoice() == false) goto L11;
        return 1;
    L11:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDocument) == false) goto L15;
        return 3;
    L15:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L18;
        return 0;
    L18:
        return 4;
    }

    public CharSequence getMessageTextToTranslate(GroupedMessages r8, int[] r9) {
        if (this.translated == false) goto L5;
        return null;
    L5:
        if (this.isRestrictedMessage == false) goto L8;
        return null;
    L8:
        if (this.summarized == true) goto L10;
        int r0 = this.type;
        if (r0 != 19) goto L14;
        return null;
    L14:
        if (r0 != 15) goto L16;
        return null;
    L16:
        if (r0 == 13) goto L48;
        CharSequence r82 = ChatActivity.getMessageCaption(this, r8, r9);
        if (r82 == null) goto L20;
    L29:
        if (r82 == null) goto L31;
    L33:
        if (r82 == null) goto L38;
        if (Emoji.fullyConsistsOfEmojis(r82) == false) goto L38;
        return null;
    L38:
        return r82;
    L31:
        if (isMediaEmpty(this.messageOwner) == false) goto L33;
        r82 = ChatActivity.getMessageContent(this, 0, false);
        goto L33
    L20:
        if (isPoll() == false) goto L29;
        TLRPC.Poll r02 = ((TLRPC.TL_messageMediaPoll) this.messageOwner.media).poll;     // Catch: Exception -> L39
        StringBuilder r2 = new StringBuilder(r02.question.text);     // Catch: Exception -> L39
        r2.append("\n");     // Catch: Exception -> L39
        ArrayList<TLRPC.PollAnswer> r03 = r02.answers;     // Catch: Exception -> L39
        int r3 = r03.size();     // Catch: Exception -> L39
        int r4 = 0;
    L22:
        if (r4 >= r3) goto L28;
        TLRPC.PollAnswer r5 = r03.get(r4);     // Catch: Exception -> L39
        r4 = r4 + 1;     // Catch: Exception -> L39
        r2.append("\n🔘 ");     // Catch: Exception -> L39
        TLRPC.TL_textWithEntities r52 = r5.text;     // Catch: Exception -> L39
        if (r52 != null) goto L26;
        String r53 = _UrlKt.FRAGMENT_ENCODE_SET;
    L27:
        r2.append(r53);     // Catch: Exception -> L39
        goto L22
    L26:
        r53 = r52.text;     // Catch: Exception -> L39
        goto L27
    L28:
        r82 = r2.toString();     // Catch: Exception -> L39
        goto L29
    L48:
        return null;
    L10:
        return this.messageText;
    }

    public String getMimeType() {
        TLRPC.Document r0 = getDocument();
        if (r0 != null) goto L5;
        boolean r02 = getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaInvoice;
        TLRPC.Message r1 = this.messageOwner;
        if (r02 == false) goto L13;
        TLRPC.WebDocument r2 = ((TLRPC.TL_messageMediaInvoice) getMedia(r1)).webPhoto;
        if (r2 != null) goto L11;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        return r2.mime_type;
    L13:
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L16;
        return "image/jpeg";
    L16:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L18;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        if (getMedia(this.messageOwner).webpage.photo == null) goto L23;
        return "image/jpeg";
    L23:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return r0.mime_type;
    }

    public long getMonoForumTopicId() {
        return getMonoForumTopicId(this.messageOwner);
    }

    public String getMusicAuthor() {
        return getMusicAuthor(true);
    }

    public String getMusicAuthor(boolean r10) {
        TLRPC.Document r0 = getDocument();
        if (r0 == null) goto L74;
        int r1 = 0;
        boolean r2 = false;
    L6:
        if (r1 >= r0.attributes.size()) goto L74;
        TLRPC.DocumentAttribute r3 = r0.attributes.get(r1);
        if ((r3 instanceof TLRPC.TL_documentAttributeAudio) == false) goto L18;
        if (r3.voice == false) goto L12;
    L11:
        r2 = true;
    L22:
        if (r2 == false) goto L72;
        TLRPC.User r32 = null;
        if (r10 == false) goto L25;
        if (isOutOwner() == true) goto L71;
        TLRPC.MessageFwdHeader r4 = this.messageOwner.fwd_from;
        if (r4 == null) goto L35;
        TLRPC.Peer r42 = r4.from_id;
        if ((r42 instanceof TLRPC.TL_peerUser) == false) goto L35;
        if (r42.user_id == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L71;
    L35:
        TLRPC.Message r43 = this.messageOwner;
        TLRPC.MessageFwdHeader r5 = r43.fwd_from;
        if (r5 != null) goto L38;
    L40:
        if (r5 != null) goto L42;
    L44:
        if (r5 != null) goto L46;
    L49:
        if (r5 == null) goto L53;
        String r52 = r5.from_name;
        if (r52 == null) goto L53;
        return r52;
    L53:
        TLRPC.Peer r53 = r43.from_id;
        if ((r53 instanceof TLRPC.TL_peerChat) == false) goto L57;
        TLRPC.Chat r44 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.from_id.chat_id));
    L64:
        if (r32 != null) goto L66;
        if (r44 == null) goto L72;
        return r44.title;
    L66:
        return UserObject.getUserName(r32);
    L57:
        if ((r53 instanceof TLRPC.TL_peerChannel) == false) goto L59;
        r44 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.from_id.channel_id));
        goto L64
    L59:
        if (r53 == null) goto L61;
    L63:
        TLRPC.User r45 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.messageOwner.from_id.user_id));
    L48:
        TLRPC.User r8 = r45;
        r44 = null;
        r32 = r8;
        goto L64
    L61:
        if (r43.peer_id.channel_id == 0) goto L63;
        r44 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.peer_id.channel_id));
        goto L64
    L46:
        if ((r5.from_id instanceof TLRPC.TL_peerUser) == false) goto L49;
        r45 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.messageOwner.fwd_from.from_id.user_id));
        goto L48
    L42:
        if ((r5.from_id instanceof TLRPC.TL_peerChat) == false) goto L44;
        r44 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.fwd_from.from_id.chat_id));
        goto L64
    L38:
        if ((r5.from_id instanceof TLRPC.TL_peerChannel) == false) goto L40;
        r44 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.fwd_from.from_id.channel_id));
    L71:
        return LocaleController.getString(R.string.FromYou);
    L25:
        return null;
    L72:
        r1 = r1 + 1;
        goto L6
    L12:
        String r9 = r3.performer;
        if (TextUtils.isEmpty(r9) == false) goto L85;
        if (r10 == true) goto L16;
        return r9;
    L16:
        return LocaleController.getString(R.string.AudioUnknownArtist);
    L85:
        return r9;
    L18:
        if ((r3 instanceof TLRPC.TL_documentAttributeVideo) == false) goto L22;
        if (r3.round_message == false) goto L22;
    L74:
        return LocaleController.getString(R.string.AudioUnknownArtist);
    }

    public String getMusicTitle() {
        return getMusicTitle(true);
    }

    public String getMusicTitle(boolean r6) {
        TLRPC.Document r0 = getDocument();
        if (r0 == null) goto L42;
        int r1 = 0;
    L6:
        if (r1 >= r0.attributes.size()) goto L38;
        TLRPC.DocumentAttribute r2 = r0.attributes.get(r1);
        if ((r2 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L10;
        if ((r2 instanceof TLRPC.TL_documentAttributeVideo) == false) goto L37;
        if (r2.round_message == false) goto L37;
        if (isQuickReply() == false) goto L36;
        return LocaleController.formatString(R.string.BusinessInReplies, new Object[]{"/" + getQuickReplyDisplayName()});
    L36:
        return LocaleController.formatDateAudio(this.messageOwner.date, true);
    L37:
        r1 = r1 + 1;
        goto L6
    L10:
        if (r2.voice == false) goto L16;
        if (r6 == true) goto L15;
        return null;
    L15:
        return LocaleController.formatDateAudio(this.messageOwner.date, true);
    L16:
        String r5 = r2.title;
        if (r5 != null) goto L19;
    L22:
        String r52 = FileLoader.getDocumentFileName(r0);
        if (TextUtils.isEmpty(r52) == false) goto L48;
        if (r6 == true) goto L26;
        return r52;
    L26:
        return LocaleController.getString(R.string.AudioUnknownTitle);
    L48:
        return r52;
    L19:
        if (r5.length() == 0) goto L22;
        return r5;
    L38:
        String r53 = FileLoader.getDocumentFileName(r0);
        if (TextUtils.isEmpty(r53) == true) goto L42;
        return r53;
    L42:
        return LocaleController.getString(R.string.AudioUnknownTitle);
    }

    public Long getMyPaidReactionPeer() {
        TLRPC.Message r5 = this.messageOwner;
        if (r5 == null) goto L24;
        TLRPC.TL_messageReactions r52 = r5.reactions;
        if (r52 == null) goto L24;
        ArrayList<TLRPC.MessageReactor> r53 = r52.top_reactors;
        if (r53 != null) goto L10;
        return null;
    L10:
        int r1 = r53.size();
        int r2 = 0;
    L11:
        if (r2 >= r1) goto L24;
        TLRPC.MessageReactor r3 = r53.get(r2);
        r2 = r2 + 1;
        TLRPC.MessageReactor r32 = r3;
        if (r32 == null) goto L11;
        if (r32.my == false) goto L11;
        if (r32.anonymous == true) goto L19;
        TLRPC.Peer r33 = r32.peer_id;
        if (r33 == null) goto L11;
        return Long.valueOf(DialogObject.getPeerDialogId(r33));
    L19:
        return Long.valueOf(UserObject.ANONYMOUS);
    L24:
        return null;
    }

    public TLObject getPeerObject() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 == null) goto L24;
        TLRPC.Peer r02 = r0.peer_id;
        if ((r02 instanceof TLRPC.TL_peerChannel_layer131) == true) goto L23;
        if ((r02 instanceof TLRPC.TL_peerChannel) == true) goto L23;
        if ((r02 instanceof TLRPC.TL_peerUser_layer131) == true) goto L21;
        if ((r02 instanceof TLRPC.TL_peerUser) == true) goto L21;
        if ((r02 instanceof TLRPC.TL_peerChat_layer131) == true) goto L19;
        if ((r02 instanceof TLRPC.TL_peerChat) == true) goto L19;
        return null;
    L19:
        return MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.peer_id.chat_id));
    L21:
        return MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.messageOwner.peer_id.user_id));
    L23:
        return MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.peer_id.channel_id));
    L24:
        return null;
    }

    public TLRPC.Photo getPhoto() {
        return getPhoto(this.messageOwner);
    }

    public long getPollHash() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L5;
    L11:
        return 0;
    L5:
        if (this.type != 17) goto L11;
        TLRPC.MessageMedia r4 = getMedia(r0);
        if ((r4 instanceof TLRPC.TL_messageMediaPoll) == false) goto L11;
        return ((TLRPC.TL_messageMediaPoll) r4).poll.hash;
    }

    public long getPollId() {
        if (this.type == 17) goto L5;
        return 0;
    L5:
        TLRPC.MessageMedia r4 = getMedia(this.messageOwner);
        if ((r4 instanceof TLRPC.TL_messageMediaPoll) == true) goto L8;
        return 0;
    L8:
        return ((TLRPC.TL_messageMediaPoll) r4).poll.id;
    }

    public TLRPC.VideoSize getPremiumStickerAnimation() {
        return getPremiumStickerAnimation(getDocument());
    }

    public float getProgress() {
        return 0.0f;
    }

    public String getQuickReplyDisplayName() {
        String r0 = getQuickReplyName();
        if (r0 == null) goto L5;
        return r0;
    L5:
        QuickRepliesController.QuickReply r3 = QuickRepliesController.getInstance(this.currentAccount).findReply(getQuickReplyId());
        if (r3 != null) goto L8;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        return r3.name;
    }

    public int getQuickReplyId() {
        return getQuickReplyId(this.messageOwner);
    }

    public String getQuickReplyName() {
        return getQuickReplyName(this.messageOwner);
    }

    public TLRPC.MessagePeerReaction getRandomUnreadReaction() {
        TLRPC.TL_messageReactions r0 = this.messageOwner.reactions;
        if (r0 == null) goto L11;
        ArrayList<TLRPC.MessagePeerReaction> r02 = r0.recent_reactions;
        if (r02 != null) goto L7;
        return null;
    L7:
        if (r02.isEmpty() == false) goto L10;
        return null;
    L10:
        return this.messageOwner.reactions.recent_reactions.get(0);
    L11:
        return null;
    }

    public int getRealId() {
        TLRPC.Message r1 = this.messageOwner;
        int r0 = r1.realId;
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return r1.id;
    }

    public int getRepliesCount() {
        TLRPC.MessageReplies r0 = this.messageOwner.replies;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.replies;
    }

    public int getReplyAnyMsgId() {
        TLRPC.MessageReplyHeader r1 = this.messageOwner.reply_to;
        if (r1 == null) goto L9;
        int r0 = r1.reply_to_top_id;
        if (r0 == 0) goto L8;
        return r0;
    L8:
        return r1.reply_to_msg_id;
    L9:
        return 0;
    }

    public int getReplyMsgId() {
        TLRPC.MessageReplyHeader r0 = this.messageOwner.reply_to;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.reply_to_msg_id;
    }

    public CharSequence getReplyQuoteNameWithIcon() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        TLRPC.MessageReplyHeader r02 = r0.reply_to;
        CharSequence r2 = null;
        if (r02 != null) goto L14;
        boolean r03 = DialogObject.isChatDialog(getDialogId());
        int r4 = this.currentAccount;
        if (r03 == false) goto L11;
        CharSequence r04 = peerNameWithIcon(r4, getDialogId());
    L40:
        TLRPC.Peer r42 = this.messageOwner.reply_to.reply_to_peer_id;
        if (r42 != null) goto L43;
    L48:
        MessageObject r1 = this.replyMessageObject;
        if (r1 != null) goto L51;
    L62:
        if (r04 == null) goto L66;
        if (r2 == null) goto L66;
        return new SpannableStringBuilder(r2).append(" ").append(r04);
    L66:
        if (r04 == null) goto L68;
        return r04;
    L68:
        if (r2 == null) goto L71;
        return r2;
    L71:
        return LocaleController.getString(R.string.Loading);
    L51:
        if ((r1.messageOwner instanceof TLRPC.TL_messageEmpty) == false) goto L54;
        r2 = new SpannableStringBuilder(userSpan()).append(" ").append(LocaleController.getString(R.string.StarsTransactionUnknown));
        goto L62
    L54:
        if (DialogObject.isChatDialog(r1.getSenderId()) == false) goto L57;
        if (r04 != null) goto L62;
        r04 = peerNameWithIcon(this.currentAccount, this.replyMessageObject.getSenderId());
        goto L62
    L57:
        if (r2 != null) goto L62;
        MessageObject r12 = this.replyMessageObject;
        if (r12.messageOwner.fwd_from == null) goto L61;
        r2 = r12.getForwardedName();
        goto L62
    L61:
        r2 = peerNameWithIcon(this.currentAccount, r12.getSenderId());
        goto L62
    L43:
        if (DialogObject.getPeerDialogId(r42) == getDialogId()) goto L48;
        TLRPC.Peer r43 = this.messageOwner.reply_to.reply_to_peer_id;
        boolean r5 = r43 instanceof TLRPC.TL_peerUser;
        int r6 = this.currentAccount;
        if (r5 == false) goto L47;
        r2 = peerNameWithIcon(r6, r43, true);
        goto L48
    L47:
        r04 = peerNameWithIcon(r6, r43);
        goto L48
    L11:
        CharSequence r05 = peerNameWithIcon(r4, getDialogId());
    L12:
        r2 = r05;
        r04 = null;
        goto L40
    L14:
        if (r02.reply_from == null) goto L39;
        TLRPC.Peer r06 = r02.reply_to_peer_id;
        if (r06 != null) goto L18;
    L21:
        boolean r07 = true;
    L22:
        TLRPC.MessageFwdHeader r44 = this.messageOwner.reply_to.reply_from;
        TLRPC.Peer r52 = r44.from_id;
        if (r52 == null) goto L28;
        boolean r45 = r52 instanceof TLRPC.TL_peerUser;
        int r62 = this.currentAccount;
        if (r45 == false) goto L27;
        r05 = peerNameWithIcon(r62, r52, r07);
        goto L12
    L27:
        r04 = peerNameWithIcon(r62, r52, r07);
        goto L40
    L28:
        TLRPC.Peer r53 = r44.saved_from_peer;
        if (r53 == null) goto L35;
        boolean r46 = r53 instanceof TLRPC.TL_peerUser;
        int r63 = this.currentAccount;
        if (r46 == false) goto L33;
        r05 = peerNameWithIcon(r63, r53, r07);
        goto L12
    L33:
        r04 = peerNameWithIcon(r63, r53, r07);
        goto L40
    L35:
        if (TextUtils.isEmpty(r44.from_name) == true) goto L39;
        if (r07 == false) goto L38;
        r05 = new SpannableStringBuilder(userSpan()).append(" ").append(this.messageOwner.reply_to.reply_from.from_name);
        goto L12
    L38:
        r05 = new SpannableStringBuilder(this.messageOwner.reply_to.reply_from.from_name);
        goto L12
    L18:
        if (DialogObject.getPeerDialogId(r06) != getDialogId()) goto L21;
        r07 = false;
    L39:
        r04 = null;
        goto L40
    }

    public int getReplyTopMsgId() {
        TLRPC.MessageReplyHeader r0 = this.messageOwner.reply_to;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.reply_to_top_id;
    }

    public int getReplyTopMsgId(boolean r1) {
        TLRPC.MessageReplyHeader r0 = this.messageOwner.reply_to;
        if (r0 == null) goto L13;
        if (r1 == false) goto L12;
        if ((r0.flags & 2) <= 0) goto L12;
        if (r0.reply_to_top_id != 0) goto L12;
        return 1;
    L12:
        return r0.reply_to_top_id;
    L13:
        return 0;
    }

    public long getSavedDialogId() {
        return getSavedDialogId(UserConfig.getInstance(this.currentAccount).getClientUserId(), this.messageOwner);
    }

    public int getSecretTimeLeft() {
        TLRPC.Message r0 = this.messageOwner;
        int r1 = r0.ttl;
        int r02 = r0.destroyTime;
        if (r02 != 0) goto L5;
        return r1;
    L5:
        return Math.max(0, r02 - ConnectionsManager.getInstance(this.currentAccount).getCurrentTime());
    }

    public CharSequence getSecretTimeString() {
        if (isSecretMedia() == true) goto L7;
        return null;
    L7:
        if (this.messageOwner.ttl == Integer.MAX_VALUE) goto L9;
        int r0 = getSecretTimeLeft();
        if (r0 >= 60) goto L16;
        String r02 = r0 + "s";
    L18:
        if (this.secretPlaySpan != null) goto L21;
        this.secretPlaySpan = new SpannableString("p");
        ColoredImageSpan r1 = new ColoredImageSpan(R.drawable.play_mini_video);
        r1.setTranslateX(AndroidUtilities.dp(1.0f));
        r1.setWidth(AndroidUtilities.dp(13.0f));
        CharSequence r6 = this.secretPlaySpan;
        ((Spannable) r6).setSpan(r1, 0, r6.length(), 33);
    L21:
        return TextUtils.concat(new CharSequence[]{this.secretPlaySpan, r02});
    L16:
        r02 = (r0 / 60) + "m";
        goto L18
    L9:
        if (this.secretOnceSpan != null) goto L12;
        this.secretOnceSpan = new SpannableString(RegisterSpec.PREFIX);
        ColoredImageSpan r03 = new ColoredImageSpan(R.drawable.mini_viewonce);
        r03.setTranslateX(-AndroidUtilities.dp(3.0f));
        r03.setWidth(AndroidUtilities.dp(13.0f));
        CharSequence r12 = this.secretOnceSpan;
        ((Spannable) r12).setSpan(r03, 0, r12.length(), 33);
    L12:
        return TextUtils.concat(new CharSequence[]{this.secretOnceSpan, "1"});
    }

    public long getSenderId() {
        TLRPC.Message r0 = this.messageOwner;
        TLRPC.MessageFwdHeader r1 = r0.fwd_from;
        if (r1 == null) goto L47;
        TLRPC.Peer r4 = r1.saved_from_peer;
        if (r4 == null) goto L47;
        long r5 = r4.user_id;
        if (r5 == 0) goto L14;
        TLRPC.Peer r7 = r1.from_id;
        if ((r7 instanceof TLRPC.TL_peerUser) == true) goto L11;
        return r5;
    L11:
        return r7.user_id;
    L14:
        if (r4.channel_id != 0) goto L16;
        long r42 = r4.chat_id;
        if (r42 == 0) goto L63;
        TLRPC.Peer r72 = r1.from_id;
        if ((r72 instanceof TLRPC.TL_peerUser) == false) goto L38;
        return r72.user_id;
    L38:
        if ((r72 instanceof TLRPC.TL_peerChannel) == false) goto L42;
        return -r72.channel_id;
    L42:
        if ((r72 instanceof TLRPC.TL_peerChat) == false) goto L46;
        return -r72.chat_id;
    L46:
        return -r42;
    L63:
        return 0;
    L16:
        if (isSavedFromMegagroup() == false) goto L21;
        TLRPC.Peer r02 = this.messageOwner.fwd_from.from_id;
        if ((r02 instanceof TLRPC.TL_peerUser) == false) goto L21;
        return r02.user_id;
    L21:
        TLRPC.MessageFwdHeader r73 = this.messageOwner.fwd_from;
        TLRPC.Peer r03 = r73.from_id;
        if ((r03 instanceof TLRPC.TL_peerChannel) == false) goto L26;
        return -r03.channel_id;
    L26:
        if ((r03 instanceof TLRPC.TL_peerChat) == false) goto L30;
        return -r03.chat_id;
    L30:
        return -r73.saved_from_peer.channel_id;
    L47:
        TLRPC.Peer r74 = r0.from_id;
        if ((r74 instanceof TLRPC.TL_peerUser) == false) goto L52;
        return r74.user_id;
    L52:
        if ((r74 instanceof TLRPC.TL_peerChannel) == false) goto L56;
        return -r74.channel_id;
    L56:
        if ((r74 instanceof TLRPC.TL_peerChat) == false) goto L60;
        return -r74.chat_id;
    L60:
        if (r0.post == false) goto L63;
        return r0.peer_id.channel_id;
    }

    public long getSize() {
        VideoPlayer.VideoUri r0 = this.highestQuality;
        if (r0 != null) goto L5;
        VideoPlayer.VideoUri r02 = this.thumbQuality;
        if (r02 != null) goto L9;
        VideoPlayer.VideoUri r03 = this.cachedQuality;
        if (r03 == null) goto L15;
        return r03.document.size;
    L15:
        return getMessageSize(this.messageOwner);
    L9:
        return r02.document.size;
    L5:
        return r0.document.size;
    }

    public long getStakedDiceAmount() {
        TLRPC.MessageMedia r2 = getMedia(this.messageOwner);
        if ((r2 instanceof TLRPC.TL_messageMediaDice) == false) goto L8;
        TLRPC.TL_messages_emojiGameOutcome r22 = ((TLRPC.TL_messageMediaDice) r2).game_outcome;
        if (r22 != null) goto L7;
        return 0;
    L7:
        return r22.stake_ton_amount;
    L8:
        return 0;
    }

    public long getStakedDiceWinAmount() {
        TLRPC.MessageMedia r5 = getMedia(this.messageOwner);
        if ((r5 instanceof TLRPC.TL_messageMediaDice) == false) goto L11;
        TLRPC.TL_messages_emojiGameOutcome r52 = ((TLRPC.TL_messageMediaDice) r5).game_outcome;
        if (r52 == null) goto L11;
        long r3 = r52.ton_amount;
        if (r3 <= 0) goto L10;
        return r3;
    L10:
        return -r52.stake_ton_amount;
    L11:
        return 0;
    }

    public String getStickerChar() {
        TLRPC.Document r4 = getDocument();
        if (r4 == null) goto L10;
        ArrayList<TLRPC.DocumentAttribute> r42 = r4.attributes;
        int r0 = r42.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L15;
        TLRPC.DocumentAttribute r2 = r42.get(r1);
        r1 = r1 + 1;
        TLRPC.DocumentAttribute r22 = r2;
        if ((r22 instanceof TLRPC.TL_documentAttributeSticker) == false) goto L5;
        return r22.alt;
    L15:
        return null;
    L10:
        return null;
    }

    public String getStickerEmoji() {
        TLRPC.Document r4 = getDocument();
        if (r4 != null) goto L5;
        return null;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r4.attributes.size()) goto L20;
        TLRPC.DocumentAttribute r2 = r4.attributes.get(r1);
        if ((r2 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L14;
        if ((r2 instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L14;
        r1 = r1 + 1;
    L14:
        String r42 = r2.alt;
        if (r42 == null) goto L20;
        if (r42.length() <= 0) goto L20;
        return r2.alt;
    L20:
        return null;
    }

    public TLRPC.WebPage getStoryMentionWebpage() {
        if (isStoryMention() == true) goto L6;
        return null;
    L6:
        TLRPC.WebPage r0 = this.storyMentionWebpage;
        if (r0 == null) goto L9;
        return r0;
    L9:
        TLRPC.TL_webPage r02 = new TLRPC.TL_webPage();
        r02.type = "telegram_story";
        TLRPC.TL_webPageAttributeStory r1 = new TLRPC.TL_webPageAttributeStory();
        r1.id = this.messageOwner.media.id;
        r1.peer = MessagesController.getInstance(this.currentAccount).getPeer(this.messageOwner.media.user_id);
        TL_stories.StoryItem r2 = this.messageOwner.media.storyItem;
        if (r2 == null) goto L12;
        r1.flags |= 1;
        r1.storyItem = r2;
    L12:
        r02.attributes.add(r1);
        this.storyMentionWebpage = r02;
        return r02;
    }

    public TextPaint getTextPaint() {
        if (this.emojiOnlyCount < 1) goto L47;
        if (this.messageOwner == null) goto L47;
        if (hasNonEmojiEntities() == true) goto L47;
        int r0 = this.emojiOnlyCount;
        int r7 = this.animatedEmojiCount;
        if (r0 != r7) goto L11;
        boolean r3 = true;
    L13:
        switch(Math.max(r0, r7)) {
            case 0: goto L40;
            case 1: goto L40;
            case 2: goto L40;
            case 3: goto L34;
            case 4: goto L28;
            case 5: goto L22;
            case 6: goto L16;
            default: goto L15;
        };
    L16:
        com.exteragram.messenger.utils.ui.TextPaint[] r72 = Theme.chat_msgTextPaintEmoji;
        if (r3 == false) goto L21;
        return r72[4];
    L21:
        return r72[5];
    L22:
        com.exteragram.messenger.utils.ui.TextPaint[] r73 = Theme.chat_msgTextPaintEmoji;
        if (r3 == false) goto L27;
        return r73[3];
    L27:
        return r73[5];
    L28:
        com.exteragram.messenger.utils.ui.TextPaint[] r74 = Theme.chat_msgTextPaintEmoji;
        if (r3 == false) goto L33;
        return r74[2];
    L33:
        return r74[4];
    L34:
        com.exteragram.messenger.utils.ui.TextPaint[] r75 = Theme.chat_msgTextPaintEmoji;
        if (r3 == false) goto L39;
        return r75[1];
    L39:
        return r75[3];
    L40:
        com.exteragram.messenger.utils.ui.TextPaint[] r76 = Theme.chat_msgTextPaintEmoji;
        if (r3 == false) goto L45;
        return r76[0];
    L45:
        return r76[2];
    L15:
        return Theme.chat_msgTextPaintEmoji[5];
    L11:
        r3 = false;
    L47:
        return Theme.chat_msgTextPaint;
    }

    public long getTopicId() {
        TLRPC.Chat r0 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-getDialogId()));
        TLRPC.User r1 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(getDialogId()));
        return getTopicId(this.currentAccount, this.messageOwner, getForumFlags(r0, r1));
    }

    public TLRPC.Photo getVideoCover() {
        TLRPC.MessageMedia r1 = getMedia(this);
        if ((r1 instanceof TLRPC.TL_messageMediaDocument) == true) goto L5;
        if (r1 == null) goto L13;
        TLRPC.WebPage r12 = r1.webpage;
        if (r12 != null) goto L10;
        return null;
    L10:
        if (r12.video_cover_photo == true) goto L12;
        return null;
    L12:
        return r12.photo;
    L13:
        return null;
    L5:
        return ((TLRPC.TL_messageMediaDocument) r1).video_cover;
    }

    public float getVideoSavedProgress() {
        if (this.cachedSavedTimestamp != null) goto L5;
        float r0 = PhotoViewer.getSavedProgress(this);
        this.cachedSavedTimestamp = Float.valueOf(r0);
        return r0;
    L5:
        return PhotoViewer.getSavedProgressFast(this);
    }

    public int getVideoStartsTimestamp() {
        Integer r2 = this.cachedStartsTimestamp;
        if (r2 == null) goto L7;
        return r2.intValue();
    L7:
        if (isVideo() == true) goto L10;
        this.cachedStartsTimestamp = -1;
        return -1;
    L10:
        TLRPC.MessageMedia r22 = getMedia(this);
        int r3 = r22.video_timestamp;
        if (r3 == 0) goto L13;
        return r3;
    L13:
        TLRPC.WebPage r23 = r22.webpage;
        if (r23 == null) goto L19;
        String r24 = r23.url;
        if (r24 == null) goto L19;
        int r25 = LaunchActivity.getTimestampFromLink(Uri.parse(r24));     // Catch: Exception -> L21
        this.cachedStartsTimestamp = Integer.valueOf(r25);     // Catch: Exception -> L21
        return r25;
    L19:
        this.cachedStartsTimestamp = -1;
        return -1;
    }

    public CharSequence getVoiceTranscription() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 == null) goto L21;
        String r02 = r0.voiceTranscription;
        if (r02 != null) goto L8;
        return null;
    L8:
        if (TextUtils.isEmpty(r02) == false) goto L12;
        SpannableString r03 = new SpannableString(LocaleController.getString(R.string.NoWordsRecognized));
        r03.setSpan(new AnonymousClass1(this), 0, r03.length(), 33);
        return r03;
    L12:
        if (this.translated == false) goto L16;
        TLRPC.TL_textWithEntities r04 = this.messageOwner.translatedVoiceTranscription;
        if (r04 == null) goto L16;
        String r4 = r04.text;
    L18:
        if (TextUtils.isEmpty(r4) == false) goto L20;
        return r4;
    L20:
        return Emoji.replaceEmoji(r4, Theme.chat_msgTextPaint.getFontMetricsInt(), false);
    L16:
        r4 = this.messageOwner.voiceTranscription;
        goto L18
    L21:
        return null;
    }

    public byte[] getWaveform() {
        if (getDocument() != null) goto L5;
        return null;
    L5:
        int r0 = 0;
        int r2 = 0;
    L7:
        if (r2 >= getDocument().attributes.size()) goto L19;
        TLRPC.DocumentAttribute r3 = getDocument().attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L10;
        r2 = r2 + 1;
        goto L7
    L10:
        byte[] r02 = r3.waveform;
        if (r02 != null) goto L13;
    L14:
        MediaController.getInstance().generateWaveform(this);
    L16:
        return r3.waveform;
    L13:
        if (r02.length != 0) goto L16;
    L19:
        if (isRoundVideo() == true) goto L21;
        return null;
    L21:
        if (this.randomWaveform != null) goto L27;
        this.randomWaveform = new byte[120];
    L23:
        byte[] r1 = this.randomWaveform;
        if (r0 >= r1.length) goto L27;
        r1[r0] = (byte) (Math.random() * 255.0d);
        r0 = r0 + 1;
    L27:
        return this.randomWaveform;
    }

    public ArrayList<MessageObject> getWebPagePhotos(ArrayList<MessageObject> r7, ArrayList<TL_iv.PageBlock> r8) {
        if (r7 != null) goto L5;
        r7 = new ArrayList();
    L5:
        if (getMedia(this.messageOwner) != null) goto L7;
    L30:
        return r7;
    L7:
        if (getMedia(this.messageOwner).webpage == null) goto L30;
        TLRPC.WebPage r0 = getMedia(this.messageOwner).webpage;
        TL_iv.Page r1 = r0.cached_page;
        if (r1 == null) goto L30;
        if (r8 != null) goto L14;
        r8 = r1.blocks;
    L14:
        int r2 = 0;
    L16:
        if (r2 >= r8.size()) goto L30;
        TL_iv.PageBlock r3 = r8.get(r2);
        if ((r3 instanceof TL_iv.pageBlockSlideshow) == false) goto L24;
        TL_iv.pageBlockSlideshow r32 = (TL_iv.pageBlockSlideshow) r3;
        int r4 = 0;
    L21:
        if (r4 >= r32.items.size()) goto L29;
        r7.add(getMessageObjectForBlock(r0, r32.items.get(r4)));
        r4 = r4 + 1;
    L29:
        r2 = r2 + 1;
        goto L16
    L24:
        if ((r3 instanceof TL_iv.pageBlockCollage) == false) goto L29;
        TL_iv.pageBlockCollage r33 = (TL_iv.pageBlockCollage) r3;
        int r42 = 0;
    L27:
        if (r42 >= r33.items.size()) goto L29;
        r7.add(getMessageObjectForBlock(r0, r33.items.get(r42)));
        r42 = r42 + 1;
        goto L27
    }

    public boolean hasAttachedStickers() {
        boolean r0 = getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPhoto;
        TLRPC.Message r1 = this.messageOwner;
        if (r0 == false) goto L12;
        if (getMedia(r1).photo != null) goto L7;
    L10:
        return false;
    L7:
        if (getMedia(this.messageOwner).photo.has_stickers == false) goto L10;
        return true;
    L12:
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaDocument) == true) goto L14;
        return false;
    L14:
        return isDocumentHasAttachedStickers(getMedia(this.messageOwner).document);
    }

    public boolean hasChosenReaction(ReactionsLayoutInBubble.VisibleReaction r5) {
        if (hasReactions() == false) goto L14;
        if (r5 == null) goto L14;
        int r0 = 0;
    L8:
        if (r0 >= this.messageOwner.reactions.results.size()) goto L14;
        TLRPC.ReactionCount r2 = this.messageOwner.reactions.results.get(r0);
        if (r5.isSame(r2.reaction) == true) goto L12;
        r0 = r0 + 1;
        goto L8
    L12:
        return r2.chosen;
    L14:
        return false;
    }

    public boolean hasEntitiesFromServer() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L6;
        return false;
    L6:
        if (r0.entities != null) goto L8;
        return false;
    L8:
        int r02 = 0;
    L10:
        if (r02 >= this.messageOwner.entities.size()) goto L19;
        TLRPC.MessageEntity r2 = this.messageOwner.entities.get(r02);
        if ((r2 instanceof TLRPC.TL_messageEntityPhone) == true) goto L17;
        if ((r2 instanceof TLRPC.TL_messageEntityBankCard) == true) goto L23;
        r02 = r02 + 1;
        goto L10
    L23:
        return true;
    L17:
        return true;
    L19:
        return false;
    }

    public boolean hasExtendedMedia() {
        TLRPC.MessageMedia r1 = this.messageOwner.media;
        if (r1 != null) goto L5;
        return false;
    L5:
        if ((r1 instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L7;
        return false;
    L7:
        if (r1.extended_media.isEmpty() == true) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean hasExtendedMediaPreview() {
        TLRPC.MessageMedia r0 = this.messageOwner.media;
        if (r0 != null) goto L5;
    L12:
        return false;
    L5:
        if ((r0 instanceof TLRPC.TL_messageMediaPaidMedia) == true) goto L12;
        if (r0.extended_media.isEmpty() == true) goto L12;
        if ((this.messageOwner.media.extended_media.get(0) instanceof TLRPC.TL_messageExtendedMediaPreview) == false) goto L12;
        return true;
    }

    public boolean hasHighlightedWords() {
        ArrayList<String> r0 = this.highlightedWords;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isEmpty() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasInlineBotButtons() {
        if (this.isRestrictedMessage == false) goto L5;
        return false;
    L5:
        if (this.isRepostPreview == true) goto L19;
        TLRPC.Message r0 = this.messageOwner;
        if (r0 == null) goto L20;
        TLRPC.ReplyMarkup r02 = r0.reply_markup;
        if ((r02 instanceof TLRPC.TL_replyInlineMarkup) == false) goto L13;
        if (((TLRPC.TL_replyInlineMarkup) r02).rows.isEmpty() == true) goto L13;
        return true;
    L13:
        if (getInlineBotButtons() == null) goto L21;
        return true;
    L21:
        return false;
    L20:
        return false;
    L19:
        return false;
    }

    public boolean hasLinkMediaToMakeSmall() {
        if (this.isRestrictedMessage == false) goto L5;
    L9:
        boolean r0 = false;
    L10:
        String r3 = null;
        if (r0 == false) goto L13;
        TLRPC.WebPage r4 = getMedia(this.messageOwner).webpage;
    L14:
        if (r4 == null) goto L16;
        r3 = r4.type;
    L16:
        if (r0 == true) goto L18;
    L51:
        return false;
    L18:
        if (isGiveawayOrGiveawayResults() == true) goto L51;
        if (r4 == null) goto L51;
        if (r4.photo != null) goto L25;
        if (isVideoDocument(r4.document) == false) goto L51;
    L25:
        if (TextUtils.isEmpty(r4.description) == false) goto L29;
        if (TextUtils.isEmpty(r4.title) == true) goto L51;
    L29:
        if (isSponsored() == true) goto L51;
        if ("telegram_megagroup".equals(r3) == true) goto L51;
        if ("telegram_background".equals(r3) == true) goto L51;
        if ("telegram_voicechat".equals(r3) == true) goto L51;
        if ("telegram_videochat".equals(r3) == true) goto L51;
        if ("telegram_livestream".equals(r3) == true) goto L51;
        if ("telegram_user".equals(r3) == true) goto L51;
        if ("telegram_story".equals(r3) == true) goto L51;
        if ("telegram_channel_boost".equals(r3) == true) goto L51;
        if ("telegram_group_boost".equals(r3) == true) goto L51;
        if ("telegram_chat".equals(r3) == true) goto L51;
        return true;
    L13:
        r4 = null;
        goto L14
    L5:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L9;
        if ((getMedia(this.messageOwner).webpage instanceof TLRPC.TL_webPage) == false) goto L9;
        r0 = true;
        goto L10
    }

    public boolean hasMediaSpoilers() {
        if (this.isRepostPreview == true) goto L11;
        TLRPC.MessageMedia r0 = this.messageOwner.media;
        if (r0 == null) goto L9;
        if (r0.spoiler == false) goto L9;
        return true;
    L9:
        if (needDrawBluredPreview() == false) goto L11;
        return true;
    L11:
        if (isHiddenSensitive() == true) goto L17;
        return false;
    L17:
        return true;
    }

    public boolean hasPaidMediaPreview() {
        TLRPC.MessageMedia r0 = this.messageOwner.media;
        if (r0 != null) goto L5;
    L12:
        return false;
    L5:
        if ((r0 instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L12;
        if (r0.extended_media.isEmpty() == true) goto L12;
        if ((this.messageOwner.media.extended_media.get(0) instanceof TLRPC.TL_messageExtendedMediaPreview) == false) goto L12;
        return true;
    }

    public boolean hasReaction(ReactionsLayoutInBubble.VisibleReaction r4) {
        if (hasReactions() == false) goto L14;
        if (r4 == null) goto L14;
        int r0 = 0;
    L8:
        if (r0 >= this.messageOwner.reactions.results.size()) goto L14;
        if (r4.isSame(this.messageOwner.reactions.results.get(r0).reaction) == true) goto L11;
        r0 = r0 + 1;
        goto L8
    L11:
        return true;
    L14:
        return false;
    }

    public boolean hasReactions() {
        TLRPC.TL_messageReactions r0 = this.messageOwner.reactions;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.results.isEmpty() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasReplies() {
        TLRPC.MessageReplies r0 = this.messageOwner.replies;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.replies <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean hasRevealedExtendedMedia() {
        TLRPC.MessageMedia r0 = this.messageOwner.media;
        if (r0 != null) goto L5;
    L12:
        return false;
    L5:
        if ((r0 instanceof TLRPC.TL_messageMediaPaidMedia) == true) goto L12;
        if (r0.extended_media.isEmpty() == true) goto L12;
        if ((this.messageOwner.media.extended_media.get(0) instanceof TLRPC.TL_messageExtendedMedia) == false) goto L12;
        return true;
    }

    public boolean hasSuggestionInlineButtons() {
        TLRPC.Message r0 = this.messageOwner;
        boolean r1 = true;
        if (r0 == null) goto L15;
        TLRPC.SuggestedPost r02 = r0.suggested_post;
        if (r02 == null) goto L15;
        if (r02.rejected == true) goto L15;
        if (r02.accepted == true) goto L15;
        if (isSendError() == true) goto L15;
        if (isSending() == true) goto L15;
        boolean r03 = true;
    L16:
        if (r03 == false) goto L30;
        long r3 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        long r5 = DialogObject.getPeerDialogId(this.messageOwner.saved_peer_id);
        long r7 = DialogObject.getPeerDialogId(this.messageOwner.from_id);
        if (r3 != r5) goto L20;
        boolean r9 = true;
    L22:
        if (r5 == r7) goto L25;
        r1 = false;
    L25:
        if (r9 == false) goto L27;
        if (r1 == false) goto L27;
    L29:
        return false;
    L27:
        if (r9 == true) goto L30;
        if (r1 == true) goto L30;
    L20:
        r9 = false;
    L30:
        return r03;
    L15:
        r03 = false;
        goto L16
    }

    public boolean hasValidGroupId() {
        if (getGroupId() == 0) goto L16;
        ArrayList<TLRPC.PhotoSize> r0 = this.photoThumbs;
        if (r0 != null) goto L7;
    L8:
        int r02 = this.type;
        if (r02 == 3) goto L15;
        if (r02 == 1) goto L15;
        if (isMusic() == true) goto L15;
        if (isDocument() == true) goto L15;
        return false;
    L15:
        return true;
    L7:
        if (r0.isEmpty() == false) goto L15;
    L16:
        return false;
    }

    public boolean hasValidGroupIdFast() {
        if (getGroupId() == 0) goto L16;
        ArrayList<TLRPC.PhotoSize> r0 = this.photoThumbs;
        if (r0 != null) goto L7;
    L8:
        int r4 = this.type;
        if (r4 == 3) goto L15;
        if (r4 == 1) goto L15;
        if (r4 == 14) goto L15;
        if (r4 == 9) goto L15;
        return false;
    L15:
        return true;
    L7:
        if (r0.isEmpty() == false) goto L15;
    L16:
        return false;
    }

    public boolean hasValidReplyMessageObject() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 == null) goto L11;
        TLRPC.MessageReplyHeader r02 = r0.reply_to;
        if (r02 == null) goto L11;
        if (r02.forum_topic == false) goto L11;
        if (r02.reply_to_msg_id != r02.reply_to_top_id) goto L11;
        return false;
    L11:
        MessageObject r3 = this.replyMessageObject;
        if (r3 == null) goto L21;
        TLRPC.Message r32 = r3.messageOwner;
        if ((r32 instanceof TLRPC.TL_messageEmpty) == true) goto L21;
        TLRPC.MessageAction r33 = r32.action;
        if ((r33 instanceof TLRPC.TL_messageActionHistoryClear) == true) goto L21;
        if ((r33 instanceof TLRPC.TL_messageActionTopicCreate) == true) goto L21;
        return true;
    L21:
        return false;
    }

    public boolean hasVideoCover() {
        if (getVideoCover() == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasVideoQualities() {
        return hasVideoQualities(true);
    }

    public boolean hasVideoQualities(boolean r4) {
        if (this.videoQualitiesCached != null) goto L29;
        TLRPC.Message r0 = this.messageOwner;     // Catch: Exception -> L16
        boolean r1 = false;
        if (r0 == null) goto L25;
        TLRPC.MessageMedia r02 = r0.media;     // Catch: Exception -> L16
        if (r02 == null) goto L25;
        if (r02.document == null) goto L25;
        if (r02.alt_documents.isEmpty() == true) goto L25;
        int r03 = this.currentAccount;     // Catch: Exception -> L16
        TLRPC.Message r2 = this.messageOwner;     // Catch: Exception -> L16
        if (r2 == null) goto L18;
        TLRPC.MessageMedia r22 = r2.media;     // Catch: Exception -> L16
    L19:
        ArrayList<VideoPlayer.Quality> r42 = VideoPlayer.getQualities(r03, r22, r4);     // Catch: Exception -> L16
        this.videoQualities = r42;     // Catch: Exception -> L16
        if (r42 != null) goto L22;
    L24:
        this.videoQualitiesCached = Boolean.valueOf(r1);     // Catch: Exception -> L16
        this.highestQuality = VideoPlayer.getQualityForPlayer(this.videoQualities);     // Catch: Exception -> L16
        this.thumbQuality = VideoPlayer.getQualityForThumb(this.videoQualities);     // Catch: Exception -> L16
        this.cachedQuality = VideoPlayer.getCachedQuality(this.videoQualities);     // Catch: Exception -> L16
        goto L29
    L22:
        if (r42.size() <= 1) goto L24;
        r1 = true;
        goto L24
    L18:
        r22 = null;
    L25:
        this.videoQualitiesCached = Boolean.FALSE;     // Catch: Exception -> L16
        return false;
    L16:
        e = move-exception;
        FileLog.e(e);
        this.videoQualitiesCached = Boolean.FALSE;
    L29:
        return this.videoQualitiesCached.booleanValue();
    }

    public boolean hasVoteResults() {
        if (this.type == 17) goto L5;
        return false;
    L5:
        TLRPC.MessageMedia r3 = getMedia(this.messageOwner);
        if ((r3 instanceof TLRPC.TL_messageMediaPoll) == true) goto L9;
        return false;
    L9:
        return isVoteResultsIsNotEmpty((TLRPC.TL_messageMediaPoll) r3);
    }

    public boolean isAlbumSingle() {
        return getMedia(this) instanceof TLRPC.TL_messageMediaPaidMedia;
    }

    public boolean isAndroidTheme() {
        if (getMedia(this.messageOwner) != null) goto L5;
    L24:
        return false;
    L5:
        if (getMedia(this.messageOwner).webpage == null) goto L24;
        if (getMedia(this.messageOwner).webpage.attributes.isEmpty() == true) goto L24;
        int r0 = getMedia(this.messageOwner).webpage.attributes.size();
        int r2 = 0;
    L9:
        if (r2 >= r0) goto L24;
        TLRPC.WebPageAttribute r3 = getMedia(this.messageOwner).webpage.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_webPageAttributeTheme) == false) goto L23;
        TLRPC.TL_webPageAttributeTheme r32 = (TLRPC.TL_webPageAttributeTheme) r3;
        ArrayList<TLRPC.Document> r4 = r32.documents;
        int r5 = r4.size();
        int r6 = 0;
    L15:
        if (r6 >= r5) goto L21;
        if ("application/x-tgtheme-android".equals(r4.get(r6).mime_type) == true) goto L18;
        r6 = r6 + 1;
        goto L15
    L18:
        return true;
    L21:
        if (r32.settings == null) goto L23;
        return true;
    L23:
        r2 = r2 + 1;
        goto L9
    }

    public boolean isAnimatedAnimatedEmoji() {
        if (isAnimatedEmoji() == true) goto L5;
        return false;
    L5:
        if (isAnimatedEmoji(getDocument()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isAnimatedEmoji() {
        if (this.emojiAnimatedSticker == null) goto L5;
        return true;
    L5:
        if (this.emojiAnimatedStickerId != null) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isAnimatedEmojiStickerSingle() {
        if (this.emojiAnimatedStickerId == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isAnimatedEmojiStickers() {
        if (this.type != 19) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isAnimatedSticker() {
        int r0 = this.type;
        boolean r2 = false;
        if (r0 != 1000) goto L5;
        boolean r02 = DialogObject.isEncryptedDialog(getDialogId());
        if (r02 == false) goto L14;
        if (this.messageOwner.stickerVerified == 1) goto L14;
        return false;
    L14:
        if (this.emojiAnimatedStickerId != null) goto L16;
    L18:
        TLRPC.Document r1 = getDocument();
        if (this.emojiAnimatedSticker != null) goto L23;
        if (r02 == false) goto L23;
        if (isOut() == true) goto L23;
    L25:
        return isAnimatedStickerDocument(r1, r2);
    L23:
        r2 = true;
        goto L25
    L16:
        if (this.emojiAnimatedSticker != null) goto L18;
        return true;
    L5:
        if (r0 != 15) goto L7;
        return true;
    L7:
        return false;
    }

    public boolean isAnyGift() {
        int r1 = this.type;
        if (r1 != 30) goto L5;
        return true;
    L5:
        if (r1 != 18) goto L7;
        return true;
    L7:
        if (r1 == 25) goto L14;
        return false;
    L14:
        return true;
    }

    public boolean isAnyKindOfSticker() {
        int r1 = this.type;
        if (r1 != 13) goto L5;
        return true;
    L5:
        if (r1 != 15) goto L7;
        return true;
    L7:
        if (r1 == 19) goto L14;
        return false;
    L14:
        return true;
    }

    public boolean isBotPreview() {
        return this.storyItem instanceof StoriesController.BotPreview;
    }

    public boolean isComments() {
        TLRPC.MessageReplies r0 = this.messageOwner.replies;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.comments == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isConferenceCall() {
        return this.messageOwner.action instanceof TLRPC.TL_messageActionConferenceCall;
    }

    public boolean isContentUnread() {
        return this.messageOwner.media_unread;
    }

    public boolean isCurrentWallpaper() {
        if (isWallpaperAction() == false) goto L22;
        TLRPC.Message r0 = this.messageOwner;
        if (r0 == null) goto L22;
        TLRPC.MessageAction r02 = r0.action;
        if (r02 == null) goto L22;
        if (r02.wallpaper == null) goto L22;
        TLRPC.UserFull r03 = MessagesController.getInstance(this.currentAccount).getUserFull(getDialogId());
        if (r03 == null) goto L22;
        TLRPC.WallPaper r2 = r03.wallpaper;
        if (r2 == null) goto L22;
        if (r03.wallpaper_overridden == false) goto L22;
        if (this.messageOwner.action.wallpaper.id != r2.id) goto L22;
        return true;
    L22:
        return false;
    }

    public boolean isDice() {
        return getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDice;
    }

    public boolean isDocument() {
        if (getDocument() != null) goto L5;
        return false;
    L5:
        if (isVideo() == false) goto L7;
        return false;
    L7:
        if (isMusic() == false) goto L9;
        return false;
    L9:
        if (isVoice() == false) goto L11;
        return false;
    L11:
        if (isAnyKindOfSticker() == true) goto L19;
        return true;
    L19:
        return false;
    }

    public boolean isEdited() {
        TLRPC.Message r2 = this.messageOwner;
        if (r2 != null) goto L5;
        return false;
    L5:
        if ((r2.flags & 32768) != 0) goto L7;
        return false;
    L7:
        if (r2.edit_date != 0) goto L9;
        return false;
    L9:
        if (r2.edit_hide == true) goto L16;
        return true;
    L16:
        return false;
    }

    public boolean isEditedSuggestionOffer() {
        MessageObject r0 = this.replyMessageObject;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.messageOwner == null) goto L14;
        TLRPC.Message r1 = this.messageOwner;
        if (r1 != null) goto L9;
        return false;
    L9:
        if (r1.suggested_post == null) goto L16;
        return true;
    L16:
        return false;
    L14:
        return false;
    }

    public boolean isEditing() {
        TLRPC.Message r2 = this.messageOwner;
        if (r2.send_state == 3) goto L5;
        return false;
    L5:
        if (r2.id <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isEditingMedia() {
        boolean r0 = getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPhoto;
        TLRPC.Message r1 = this.messageOwner;
        if (r0 == false) goto L9;
        if (getMedia(r1).photo.id != 0) goto L7;
        return true;
    L7:
        return false;
    L9:
        if ((getMedia(r1) instanceof TLRPC.TL_messageMediaDocument) == true) goto L11;
    L13:
        return false;
    L11:
        if (getMedia(this.messageOwner).document.dc_id != 0) goto L13;
        return true;
    }

    public boolean isEmbedVideo() {
        Boolean r0 = this.isEmbedVideoCached;
        TLRPC.Message r1 = this.messageOwner;
        boolean r2 = false;
        if (r0 == null) goto L13;
        if (r1 == null) goto L12;
        TLRPC.MessageMedia r4 = r1.media;
        if (r4 == null) goto L12;
        if (r4.webpage == null) goto L12;
        if (r0.booleanValue() == false) goto L12;
        return true;
    L12:
        return false;
    L13:
        if (r1 == null) goto L21;
        TLRPC.MessageMedia r02 = r1.media;
        if (r02 == null) goto L21;
        TLRPC.WebPage r03 = r02.webpage;
        if (r03 == null) goto L21;
        if (TextUtils.isEmpty(WebPlayerView.getYouTubeVideoId(r03.url)) == true) goto L21;
        r2 = true;
    L21:
        this.isEmbedVideoCached = Boolean.valueOf(r2);
        return r2;
    }

    public boolean isEphemeral() {
        return isEphemeral(this.messageOwner);
    }

    public boolean isEphemeralAndNotWelcome() {
        return isEphemeralAndNotWelcome(this.messageOwner);
    }

    public boolean isExpiredLiveLocation(int r2) {
        TLRPC.Message r1 = this.messageOwner;
        if ((r1.date + getMedia(r1).period) > r2) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isExpiredStory() {
        int r0 = this.type;
        if (r0 == 23) goto L7;
        if (r0 == 24) goto L7;
        return false;
    L7:
        if ((this.messageOwner.media.storyItem instanceof TL_stories.TL_storyItemDeleted) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public boolean isFactCheckable() {
        if (getId() >= 0) goto L5;
        return false;
    L5:
        if (isSponsored() == true) goto L20;
        int r2 = this.type;
        if (r2 != 0) goto L9;
    L17:
        return true;
    L9:
        if (r2 == 2) goto L17;
        if (r2 == 1) goto L17;
        if (r2 == 3) goto L17;
        if (r2 == 8) goto L17;
        if (r2 == 9) goto L17;
        return false;
    L20:
        return false;
    }

    public boolean isFcmMessage() {
        if (this.localType == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isForwarded() {
        return isForwardedMessage(this.messageOwner);
    }

    public boolean isForwardedChannelPost() {
        TLRPC.Message r4 = this.messageOwner;
        TLRPC.Peer r0 = r4.from_id;
        if ((r0 instanceof TLRPC.TL_peerChannel) == false) goto L14;
        TLRPC.MessageFwdHeader r42 = r4.fwd_from;
        if (r42 != null) goto L7;
        return false;
    L7:
        if (r42.channel_post == 0) goto L17;
        TLRPC.Peer r43 = r42.saved_from_peer;
        if ((r43 instanceof TLRPC.TL_peerChannel) == true) goto L11;
        return false;
    L11:
        if (r0.channel_id != r43.channel_id) goto L19;
        return true;
    L19:
        return false;
    L17:
        return false;
    L14:
        return false;
    }

    public boolean isFromChannel() {
        TLRPC.Peer r0 = this.messageOwner.peer_id;
        TLRPC.Chat r3 = null;
        if (r0 == null) goto L7;
        long r4 = r0.channel_id;
        if (r4 == 0) goto L7;
        TLRPC.Chat r02 = getChat(null, null, r4);
    L9:
        if ((this.messageOwner.peer_id instanceof TLRPC.TL_peerChannel) == true) goto L11;
    L13:
        TLRPC.Peer r03 = this.messageOwner.from_id;
        if (r03 == null) goto L19;
        long r6 = r03.channel_id;
        if (r6 == 0) goto L19;
        r3 = getChat(null, null, r6);
    L19:
        if ((this.messageOwner.from_id instanceof TLRPC.TL_peerChannel) == true) goto L21;
        return false;
    L21:
        if (ChatObject.isChannelAndNotMegaGroup(r3) == false) goto L25;
        return true;
    L25:
        return false;
    L11:
        if (ChatObject.isChannelAndNotMegaGroup(r02) == false) goto L13;
        return true;
    L7:
        r02 = null;
        goto L9
    }

    public boolean isFromChat() {
        if (getDialogId() != UserConfig.getInstance(this.currentAccount).clientUserId) goto L5;
        return true;
    L5:
        TLRPC.Peer r0 = this.messageOwner.peer_id;
        TLRPC.Chat r2 = null;
        if (r0 == null) goto L11;
        long r5 = r0.channel_id;
        if (r5 == 0) goto L11;
        r2 = getChat(null, null, r5);
    L11:
        if (ChatObject.isChannel(r2) == true) goto L13;
    L14:
        TLRPC.Peer r7 = this.messageOwner.peer_id;
        if (r7 == null) goto L20;
        if (r7.chat_id == 0) goto L20;
    L18:
        return true;
    L20:
        if (r7 != null) goto L22;
    L27:
        return false;
    L22:
        if (r7.channel_id == 0) goto L27;
        if (r2 == null) goto L27;
        if (r2.megagroup == false) goto L27;
        return true;
    L13:
        if (r2.megagroup == true) goto L18;
        goto L14
    }

    public boolean isFromGroup() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L5;
        return false;
    L5:
        TLRPC.Peer r02 = r0.peer_id;
        TLRPC.Chat r2 = null;
        if (r02 == null) goto L11;
        long r3 = r02.channel_id;
        if (r3 == 0) goto L11;
        r2 = getChat(null, null, r3);
    L11:
        if ((this.messageOwner.from_id instanceof TLRPC.TL_peerChannel) == true) goto L13;
    L18:
        return false;
    L13:
        if (ChatObject.isChannel(r2) == false) goto L18;
        if (r2.megagroup == false) goto L18;
        return true;
    }

    public boolean isFromUser() {
        TLRPC.Message r1 = this.messageOwner;
        if ((r1.from_id instanceof TLRPC.TL_peerUser) == true) goto L5;
        return false;
    L5:
        if (r1.post == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isGame() {
        return isGameMessage(this.messageOwner);
    }

    public boolean isGif() {
        return isGifMessage(this.messageOwner);
    }

    public boolean isGiveaway() {
        if (this.type != 26) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isGiveawayOrGiveawayResults() {
        if (isGiveaway() == false) goto L5;
        return true;
    L5:
        if (isGiveawayResults() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isGiveawayResults() {
        if (this.type != 28) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isHiddenSensitive() {
        if (isSensitive() == true) goto L5;
        return false;
    L5:
        if (MessagesController.getInstance(this.currentAccount).showSensitiveContent() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isImportedForward() {
        TLRPC.MessageFwdHeader r0 = this.messageOwner.fwd_from;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.imported == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isInvoice() {
        return isInvoiceMessage(this.messageOwner);
    }

    public boolean isLinkMediaSmall() {
        String r1 = null;
        if (this.isRestrictedMessage == false) goto L5;
    L9:
        TLRPC.WebPage r2 = null;
    L10:
        if (r2 == null) goto L12;
        r1 = r2.type;
    L12:
        if (r2 == null) goto L18;
        if (TextUtils.isEmpty(r2.description) == false) goto L18;
        if (TextUtils.isEmpty(r2.title) == false) goto L18;
        return false;
    L18:
        if (Common.ASSET_APP.equals(r1) == false) goto L20;
        return true;
    L20:
        if ("profile".equals(r1) == false) goto L22;
        return true;
    L22:
        if ("article".equals(r1) == false) goto L24;
        return true;
    L24:
        if ("telegram_bot".equals(r1) == false) goto L26;
        return true;
    L26:
        if ("telegram_user".equals(r1) == false) goto L28;
        return true;
    L28:
        if ("telegram_channel".equals(r1) == false) goto L30;
        return true;
    L30:
        if ("telegram_megagroup".equals(r1) == false) goto L32;
        return true;
    L32:
        if ("telegram_voicechat".equals(r1) == false) goto L34;
        return true;
    L34:
        if ("telegram_videochat".equals(r1) == false) goto L36;
        return true;
    L36:
        if ("telegram_livestream".equals(r1) == false) goto L38;
        return true;
    L38:
        if ("telegram_channel_boost".equals(r1) == false) goto L40;
        return true;
    L40:
        if ("telegram_group_boost".equals(r1) == false) goto L42;
        return true;
    L42:
        if ("telegram_chat".equals(r1) == true) goto L60;
        return false;
    L60:
        return true;
    L5:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L9;
        if ((getMedia(this.messageOwner).webpage instanceof TLRPC.TL_webPage) == false) goto L9;
        r2 = getMedia(this.messageOwner).webpage;
        goto L10
    }

    public boolean isLinkedToChat(long r3) {
        TLRPC.MessageReplies r2 = this.messageOwner.replies;
        if (r2 != null) goto L5;
        return false;
    L5:
        if (r3 != 0) goto L7;
        return true;
    L7:
        if (r2.channel_id != r3) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isLiveLocation() {
        return isLiveLocationMessage(this.messageOwner);
    }

    public boolean isLivePhoto() {
        TLRPC.MessageMedia r0 = getMedia(this);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.live_photo == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isLocation() {
        return isLocationMessage(this.messageOwner);
    }

    public boolean isMask() {
        return isMaskMessage(this.messageOwner);
    }

    public boolean isMediaEmpty() {
        return isMediaEmpty(this.messageOwner);
    }

    public boolean isMediaEmpty(boolean r1) {
        return isMediaEmpty(this.messageOwner, r1);
    }

    public boolean isMediaEmptyWebpage() {
        return isMediaEmptyWebpage(this.messageOwner);
    }

    public boolean isMusic() {
        if (isMusicMessage(this.messageOwner) == true) goto L5;
        return false;
    L5:
        if (isVideo() == false) goto L7;
        return false;
    L7:
        if (isRoundVideo() == true) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isNewGif() {
        if (getMedia(this.messageOwner) != null) goto L5;
        return false;
    L5:
        if (isNewGifDocument(getDocument()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isOut() {
        return this.messageOwner.out;
    }

    public boolean isOutOwner() {
        boolean r1 = true;
        if (this.previewForward == false) goto L5;
        return true;
    L5:
        Boolean r0 = this.isOutOwnerCached;
        if (r0 == null) goto L10;
        return r0.booleanValue();
    L10:
        if (isWelcomeMessage() == true) goto L12;
    L15:
        long r3 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        if (this.isSaved == false) goto L18;
    L50:
        TLRPC.MessageFwdHeader r02 = this.messageOwner.fwd_from;
        if (r02 == null) goto L62;
        TLRPC.Peer r5 = r02.from_id;
        if (r5 == null) goto L57;
        if (r5.user_id != r3) goto L57;
    L60:
        this.isOutOwnerCached = Boolean.valueOf(r1);
        return r1;
    L57:
        if (r02.saved_out == true) goto L60;
        r1 = false;
        goto L60
    L62:
        this.isOutOwnerCached = Boolean.TRUE;
        return true;
    L18:
        if (getDialogId() == r3) goto L50;
        TLRPC.Peer r03 = this.messageOwner.peer_id;
        TLRPC.Chat r52 = null;
        if (r03 == null) goto L25;
        long r6 = r03.channel_id;
        if (r6 == 0) goto L25;
        r52 = getChat(null, null, r6);
    L25:
        TLRPC.Message r04 = this.messageOwner;
        if (r04.out == false) goto L48;
        TLRPC.Peer r05 = r04.from_id;
        if ((r05 instanceof TLRPC.TL_peerUser) == false) goto L30;
    L33:
        TLRPC.Message r06 = this.messageOwner;
        if (r06.post == true) goto L48;
        TLRPC.MessageFwdHeader r07 = r06.fwd_from;
        if (r07 != null) goto L40;
        this.isOutOwnerCached = Boolean.TRUE;
        return true;
    L40:
        TLRPC.Peer r08 = r07.saved_from_peer;
        if (r08 != null) goto L43;
    L46:
        this.isOutOwnerCached = Boolean.valueOf(r1);
        return r1;
    L43:
        if (r08.user_id == r3) goto L46;
        r1 = false;
        goto L46
    L30:
        if ((r05 instanceof TLRPC.TL_peerChannel) == false) goto L48;
        if (ChatObject.isChannelAndNotMegaGroup(r52) == false) goto L33;
    L48:
        this.isOutOwnerCached = Boolean.FALSE;
        return false;
    L12:
        if (isWelcomeAnchored() == true) goto L15;
        this.isOutOwnerCached = Boolean.FALSE;
        return false;
    }

    public boolean isPaid() {
        TLRPC.Message r4 = this.messageOwner;
        if (r4 != null) goto L5;
        return false;
    L5:
        if (r4.paid_message_stars <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isPaidReactionChosen() {
        if (this.messageOwner.reactions != null) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= this.messageOwner.reactions.results.size()) goto L13;
        if ((this.messageOwner.reactions.results.get(r0).reaction instanceof TLRPC.TL_reactionPaid) == true) goto L11;
        r0 = r0 + 1;
        goto L7
    L11:
        return this.messageOwner.reactions.results.get(r0).chosen;
    L13:
        return false;
    }

    public boolean isPaidSuggestedPost() {
        TLRPC.Message r1 = this.messageOwner;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.paid_suggested_post_stars == false) goto L7;
        return true;
    L7:
        if (r1.paid_suggested_post_ton == false) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isPaidSuggestedPostProtected() {
        if (isPaidSuggestedPost() == false) goto L8;
        int r0 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        if ((r0 - this.messageOwner.date) >= MessagesController.getInstance(this.currentAccount).config.starsSuggestedPostAgeMin.get(TimeUnit.SECONDS)) goto L10;
        return true;
    L10:
        return false;
    L8:
        return false;
    }

    public boolean isPhoto() {
        return isPhoto(this.messageOwner);
    }

    public boolean isPoll() {
        if (this.type == 17) goto L5;
        return false;
    L5:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPoll) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isPollClosed() {
        if (this.type == 17) goto L5;
        return false;
    L5:
        TLRPC.MessageMedia r3 = getMedia(this.messageOwner);
        if ((r3 instanceof TLRPC.TL_messageMediaPoll) == true) goto L9;
        return false;
    L9:
        return ((TLRPC.TL_messageMediaPoll) r3).poll.closed;
    }

    public boolean isPremiumSticker() {
        if (getMedia(this.messageOwner) == null) goto L9;
        if (getMedia(this.messageOwner).nopremium == false) goto L9;
        return false;
    L9:
        return isPremiumSticker(getDocument());
    }

    public boolean isPrivateForward() {
        TLRPC.MessageFwdHeader r0 = this.messageOwner.fwd_from;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (TextUtils.isEmpty(r0.from_name) == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isPublicPoll() {
        if (this.type == 17) goto L5;
        return false;
    L5:
        TLRPC.MessageMedia r3 = getMedia(this.messageOwner);
        if ((r3 instanceof TLRPC.TL_messageMediaPoll) == true) goto L9;
        return false;
    L9:
        return ((TLRPC.TL_messageMediaPoll) r3).poll.public_voters;
    }

    public boolean isQuickReply() {
        return isQuickReply(this.messageOwner);
    }

    public boolean isQuiz() {
        if (this.type == 17) goto L5;
        return false;
    L5:
        TLRPC.MessageMedia r3 = getMedia(this.messageOwner);
        if ((r3 instanceof TLRPC.TL_messageMediaPoll) == true) goto L9;
        return false;
    L9:
        return ((TLRPC.TL_messageMediaPoll) r3).poll.quiz;
    }

    public boolean isReactionsAvailable() {
        if (isEditing() == false) goto L5;
        return false;
    L5:
        if (isSponsored() == false) goto L7;
        return false;
    L7:
        if (isSent() == true) goto L9;
        return false;
    L9:
        if (isEphemeral() == false) goto L11;
        return false;
    L11:
        if (isExpiredStory() == false) goto L13;
        return false;
    L13:
        if (canSetReaction() == false) goto L22;
        return true;
    L22:
        return false;
    }

    public boolean isReply() {
        MessageObject r0 = this.replyMessageObject;
        if (r0 != null) goto L5;
    L6:
        TLRPC.Message r4 = this.messageOwner;
        TLRPC.MessageReplyHeader r02 = r4.reply_to;
        if (r02 != null) goto L9;
        return false;
    L9:
        if (r02.reply_to_msg_id != 0) goto L13;
        if (r02.reply_to_random_id != 0) goto L13;
        return false;
    L13:
        if ((r4.flags & 8) == 0) goto L20;
        return true;
    L20:
        return false;
    L5:
        if ((r0.messageOwner instanceof TLRPC.TL_messageEmpty) == false) goto L6;
        return false;
    }

    public boolean isReplyToStory() {
        MessageObject r0 = this.replyMessageObject;
        if (r0 != null) goto L5;
    L6:
        TLRPC.Message r1 = this.messageOwner;
        TLRPC.MessageReplyHeader r02 = r1.reply_to;
        if (r02 != null) goto L9;
        return false;
    L9:
        if (r02.story_id != 0) goto L11;
        return false;
    L11:
        if ((r1.flags & 8) == 0) goto L18;
        return true;
    L18:
        return false;
    L5:
        if ((r0.messageOwner instanceof TLRPC.TL_messageEmpty) == false) goto L6;
        return false;
    }

    public boolean isRoundOnce() {
        if (isRoundVideo() == false) goto L12;
        TLRPC.Message r1 = this.messageOwner;
        if (r1 == null) goto L14;
        TLRPC.MessageMedia r12 = r1.media;
        if (r12 != null) goto L9;
        return false;
    L9:
        if (r12.ttl_seconds != Integer.MAX_VALUE) goto L16;
        return true;
    L16:
        return false;
    L14:
        return false;
    L12:
        return false;
    }

    public boolean isRoundVideo() {
        if (this.isRoundVideoCached != 0) goto L13;
        if (this.type != 5) goto L7;
    L10:
        int r0 = 1;
    L11:
        this.isRoundVideoCached = r0;
        goto L13
    L7:
        if (isRoundVideoMessage(this.messageOwner) == true) goto L10;
        r0 = 2;
    L13:
        if (this.isRoundVideoCached != 1) goto L15;
        return true;
    L15:
        return false;
    }

    public boolean isSavedFromMegagroup() {
        TLRPC.MessageFwdHeader r0 = this.messageOwner.fwd_from;
        if (r0 == null) goto L10;
        TLRPC.Peer r02 = r0.saved_from_peer;
        if (r02 != null) goto L7;
        return false;
    L7:
        if (r02.channel_id != 0) goto L9;
        return false;
    L9:
        return ChatObject.isMegagroup(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.messageOwner.fwd_from.saved_from_peer.channel_id)));
    L10:
        return false;
    }

    public boolean isSecret() {
        return this.messageOwner instanceof TLRPC.TL_message_secret;
    }

    public boolean isSecretMedia() {
        TLRPC.Message r0 = this.messageOwner;
        if ((r0 instanceof TLRPC.TL_message_secret) == false) goto L22;
        if ((getMedia(r0) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L7;
    L8:
        int r02 = this.messageOwner.ttl;
        if (r02 > 0) goto L11;
    L13:
        if (isVoice() == false) goto L15;
    L20:
        return true;
    L15:
        if (isRoundVideo() == true) goto L20;
        if (isVideo() == true) goto L20;
        return false;
    L11:
        if (r02 <= 60) goto L20;
    L7:
        if (isGif() == false) goto L13;
    L22:
        if ((r0 instanceof TLRPC.TL_message) == true) goto L24;
    L32:
        return false;
    L24:
        if (getMedia(r0) == null) goto L32;
        if (getMedia(this.messageOwner).ttl_seconds == 0) goto L32;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L30;
    L31:
        return true;
    L30:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDocument) == false) goto L32;
        goto L31
    }

    public boolean isSendError() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0.send_state != 2) goto L7;
        if (r0.id >= 0) goto L7;
        return true;
    L7:
        if (this.scheduled == true) goto L9;
        return false;
    L9:
        if (r0.id <= 0) goto L21;
        int r02 = r0.date;
        int r1 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        if (this.messageOwner.video_processing_pending == false) goto L13;
        int r3 = 300;
    L15:
        if (r02 >= (r1 - r3)) goto L22;
        return true;
    L22:
        return false;
    L13:
        r3 = 60;
        goto L15
    L21:
        return false;
    }

    public boolean isSending() {
        TLRPC.Message r2 = this.messageOwner;
        if (r2.send_state == 1) goto L5;
        return false;
    L5:
        if (r2.id >= 0) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean isSensitive() {
        Boolean r0 = this.isSensitiveCached;
        if (r0 == null) goto L7;
        return r0.booleanValue();
    L7:
        if (this.messageOwner != null) goto L10;
        return false;
    L10:
        if (canBeSensitive() == true) goto L13;
        return false;
    L13:
        if (this.messageOwner.restriction_reason.isEmpty() == true) goto L33;
        int r02 = 0;
    L16:
        if (r02 >= this.messageOwner.restriction_reason.size()) goto L33;
        TLRPC.RestrictionReason r7 = this.messageOwner.restriction_reason.get(r02);
        if ("sensitive".equals(r7.reason) == false) goto L31;
        if ("all".equals(r7.platform) == true) goto L29;
        if ("android".equals(r7.platform) == false) goto L28;
        if (BuildVars.isBetaApp() == false) goto L29;
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L29;
    L28:
        if ("android-all".equals(r7.platform) == false) goto L31;
    L29:
        this.isSensitiveCached = Boolean.TRUE;
        return true;
    L31:
        r02 = r02 + 1;
    L33:
        if (getDialogId() >= 0) goto L56;
        TLRPC.Chat r03 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-getDialogId()));
        if (r03 == null) goto L56;
        if (r03.restriction_reason == null) goto L56;
        int r72 = 0;
    L40:
        if (r72 >= r03.restriction_reason.size()) goto L56;
        TLRPC.RestrictionReason r8 = r03.restriction_reason.get(r72);
        if ("sensitive".equals(r8.reason) == false) goto L55;
        if ("all".equals(r8.platform) == true) goto L53;
        if ("android".equals(r8.platform) == false) goto L52;
        if (BuildVars.isBetaApp() == false) goto L53;
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L53;
    L52:
        if ("android-all".equals(r8.platform) == false) goto L55;
    L53:
        this.isSensitiveCached = Boolean.TRUE;
        return true;
    L55:
        r72 = r72 + 1;
    L56:
        this.isSensitiveCached = Boolean.FALSE;
        return false;
    }

    public boolean isSent() {
        TLRPC.Message r1 = this.messageOwner;
        if (r1.send_state != 0) goto L5;
        return true;
    L5:
        if (r1.id > 0) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isSponsored() {
        if (this.sponsoredId == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isStakeableDice() {
        TLRPC.TL_messageMediaDice r1 = (TLRPC.TL_messageMediaDice) getMedia(this.messageOwner, TLRPC.TL_messageMediaDice.class);
        if (r1 != null) goto L5;
        return false;
    L5:
        if (TextUtils.equals("🎲", r1.emoticon) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isStakedDice() {
        TLRPC.MessageMedia r2 = getMedia(this.messageOwner);
        if ((r2 instanceof TLRPC.TL_messageMediaDice) == true) goto L5;
    L8:
        return false;
    L5:
        if (((TLRPC.TL_messageMediaDice) r2).game_outcome == null) goto L8;
        return true;
    }

    public boolean isStarGiftAction() {
        TLRPC.Message r1 = this.messageOwner;
        if (r1 == null) goto L10;
        TLRPC.MessageAction r12 = r1.action;
        if ((r12 instanceof TLRPC.TL_messageActionStarGift) == false) goto L7;
        return true;
    L7:
        if ((r12 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean isSticker() {
        int r0 = this.type;
        if (r0 == 1000) goto L9;
        if (r0 != 13) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (isStickerDocument(getDocument()) == false) goto L11;
    L14:
        return true;
    L11:
        if (isVideoSticker(getDocument()) == true) goto L14;
        return false;
    }

    public boolean isStory() {
        if (this.storyItem == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isStoryMedia() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L5;
        return false;
    L5:
        if ((r0.media instanceof TLRPC.TL_messageMediaStory) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isStoryMention() {
        if (this.type == 24) goto L5;
        return false;
    L5:
        if (isExpiredStory() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isSupergroup() {
        if (this.localSupergroup == false) goto L6;
        return true;
    L6:
        Boolean r0 = this.cachedIsSupergroup;
        if (r0 != null) goto L9;
        TLRPC.Peer r02 = this.messageOwner.peer_id;
        if (r02 == null) goto L19;
        long r2 = r02.channel_id;
        if (r2 == 0) goto L19;
        TLRPC.Chat r03 = getChat(null, null, r2);
        if (r03 == null) goto L18;
        boolean r04 = r03.megagroup;
        this.cachedIsSupergroup = Boolean.valueOf(r04);
        return r04;
    L18:
        return false;
    L19:
        this.cachedIsSupergroup = Boolean.FALSE;
        return false;
    L9:
        return r0.booleanValue();
    }

    public boolean isTheme() {
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L5;
        return false;
    L5:
        if (getMedia(this.messageOwner).webpage != null) goto L7;
        return false;
    L7:
        if ("telegram_theme".equals(getMedia(this.messageOwner).webpage.type) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isTodo() {
        if (this.type == 17) goto L5;
        return false;
    L5:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaToDo) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isUnread() {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.unread == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isUnsupported() {
        return getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaUnsupported;
    }

    public boolean isVideo() {
        return isVideoMessage(this.messageOwner);
    }

    public boolean isVideoAvatar() {
        TLRPC.MessageAction r0 = this.messageOwner.action;
        if (r0 == null) goto L10;
        TLRPC.Photo r02 = r0.photo;
        if (r02 != null) goto L7;
        return false;
    L7:
        if (r02.video_sizes.isEmpty() == true) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean isVideoCall() {
        TLRPC.MessageAction r1 = this.messageOwner.action;
        if ((r1 instanceof TLRPC.TL_messageActionPhoneCall) == false) goto L7;
        if (r1.video == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof TLRPC.TL_messageActionConferenceCall) == true) goto L9;
        return false;
    L9:
        if (r1.video == false) goto L15;
        return true;
    L15:
        return false;
    }

    public boolean isVideoSticker() {
        if (getDocument() != null) goto L5;
        return false;
    L5:
        if (isVideoStickerDocument(getDocument()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isVideoStory() {
        TLRPC.MessageMedia r1 = getMedia(this.messageOwner);
        if (r1 != null) goto L5;
        return false;
    L5:
        TL_stories.StoryItem r12 = r1.storyItem;
        if (r12 == null) goto L12;
        TLRPC.MessageMedia r13 = r12.media;
        if (r13 == null) goto L12;
        return isVideoDocument(r13.document);
    L12:
        return false;
    }

    public boolean isVoice() {
        return isVoiceMessage(this.messageOwner);
    }

    public boolean isVoiceOnce() {
        if (isVoice() == false) goto L12;
        TLRPC.Message r1 = this.messageOwner;
        if (r1 == null) goto L14;
        TLRPC.MessageMedia r12 = r1.media;
        if (r12 != null) goto L9;
        return false;
    L9:
        if (r12.ttl_seconds != Integer.MAX_VALUE) goto L16;
        return true;
    L16:
        return false;
    L14:
        return false;
    L12:
        return false;
    }

    public boolean isVoiceTranscriptionOpen() {
        if (this.messageOwner != null) goto L5;
        return false;
    L5:
        if (isVoice() == false) goto L7;
    L10:
        TLRPC.Message r0 = this.messageOwner;
        if (r0.voiceTranscriptionOpen == true) goto L13;
        return false;
    L13:
        if (r0.voiceTranscription != null) goto L15;
        return false;
    L15:
        if (r0.voiceTranscriptionFinal == false) goto L17;
        return true;
    L17:
        if (TranscribeButton.isTranscribing(this) == false) goto L27;
        return true;
    L27:
        return false;
    L7:
        if (isRoundVideo() == true) goto L9;
        return false;
    L9:
        if (TranscribeButton.isVideoTranscriptionOpen(this) == true) goto L10;
        return false;
    }

    public boolean isVoted() {
        if (this.type == 17) goto L5;
        return false;
    L5:
        TLRPC.MessageMedia r3 = getMedia(this.messageOwner);
        if ((r3 instanceof TLRPC.TL_messageMediaPoll) == true) goto L9;
        return false;
    L9:
        return isVoted((TLRPC.TL_messageMediaPoll) r3);
    }

    public boolean isVotedButResultsHiddenUntilClose() {
        if (this.type == 17) goto L5;
        return false;
    L5:
        TLRPC.MessageMedia r3 = getMedia(this.messageOwner);
        if ((r3 instanceof TLRPC.TL_messageMediaPoll) == true) goto L9;
        return false;
    L9:
        return isVotedButResultsHiddenUntilClose((TLRPC.TL_messageMediaPoll) r3);
    }

    public boolean isWallpaper() {
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L5;
        return false;
    L5:
        if (getMedia(this.messageOwner).webpage != null) goto L7;
        return false;
    L7:
        if ("telegram_background".equals(getMedia(this.messageOwner).webpage.type) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isWallpaperAction() {
        if (this.type == 22) goto L11;
        TLRPC.Message r2 = this.messageOwner;
        if (r2 != null) goto L7;
        return false;
    L7:
        if ((r2.action instanceof TLRPC.TL_messageActionSetSameChatWallPaper) == true) goto L14;
        return false;
    L14:
        return true;
    L11:
        return true;
    }

    public boolean isWallpaperForBoth() {
        if (isWallpaperAction() == false) goto L12;
        TLRPC.Message r1 = this.messageOwner;
        if (r1 == null) goto L14;
        TLRPC.MessageAction r12 = r1.action;
        if ((r12 instanceof TLRPC.TL_messageActionSetChatWallPaper) == true) goto L9;
        return false;
    L9:
        if (((TLRPC.TL_messageActionSetChatWallPaper) r12).for_both == false) goto L16;
        return true;
    L16:
        return false;
    L14:
        return false;
    L12:
        return false;
    }

    public boolean isWebpage() {
        return getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage;
    }

    public boolean isWebpageDocument() {
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L5;
        return false;
    L5:
        if (getMedia(this.messageOwner).webpage.document != null) goto L7;
        return false;
    L7:
        if (isGifDocument(getMedia(this.messageOwner).webpage.document) == true) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isWelcomeAnchored() {
        return isWelcomeAnchored(this.messageOwner);
    }

    public boolean isWelcomeMessage() {
        return isWelcomeMessage(this.messageOwner);
    }

    public boolean isYouTubeVideo() {
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L5;
        return false;
    L5:
        if (getMedia(this.messageOwner).webpage != null) goto L7;
        return false;
    L7:
        if (TextUtils.isEmpty(getMedia(this.messageOwner).webpage.embed_url) == false) goto L9;
        return false;
    L9:
        if ("YouTube".equals(getMedia(this.messageOwner).webpage.site_name) == false) goto L16;
        return true;
    L16:
        return false;
    }

    public void loadAnimatedEmojiDocument() {
        if (this.emojiAnimatedSticker == null) goto L5;
        return;
    L5:
        if (this.emojiAnimatedStickerId != null) goto L7;
        return;
    L7:
        if (this.emojiAnimatedStickerLoading == true) goto L13;
        this.emojiAnimatedStickerLoading = true;
        AnimatedEmojiDrawable.getDocumentFetcher(this.currentAccount).fetchDocument(this.emojiAnimatedStickerId.longValue(), new MessageObject$$ExternalSyntheticLambda2(this));
        return;
    }

    public void markPollVotesAsRead() {
        TLRPC.Message r1 = this.messageOwner;
        if (r1 == null) goto L8;
        TLRPC.MessageMedia r12 = r1.media;
        if ((r12 instanceof TLRPC.TL_messageMediaPoll) == false) goto L9;
        ((TLRPC.TL_messageMediaPoll) r12).results.has_unread_votes = false;
        return;
    L9:
        return;
    }

    public void markReactionsAsRead() {
        TLRPC.TL_messageReactions r0 = this.messageOwner.reactions;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.recent_reactions == null) goto L22;
        int r1 = 0;
        boolean r2 = false;
    L9:
        if (r1 >= this.messageOwner.reactions.recent_reactions.size()) goto L14;
        if (this.messageOwner.reactions.recent_reactions.get(r1).unread == false) goto L13;
        this.messageOwner.reactions.recent_reactions.get(r1).unread = false;
        r2 = true;
    L13:
        r1 = r1 + 1;
        goto L9
    L14:
        if (r2 == false) goto L21;
        MessagesStorage r12 = MessagesStorage.getInstance(this.currentAccount);
        TLRPC.Message r02 = this.messageOwner;
        r12.markMessageReactionsAsRead(r02.dialog_id, getTopicId(this.currentAccount, r02), this.messageOwner.id);
        return;
    L21:
        return;
    }

    public void measureInlineBotButtons() {
        if (this.isRestrictedMessage == true) goto L70;
        this.wantedBotKeyboardWidth = 0;
        this.inlineKeyboardSource = null;
        BotInlineKeyboard.Builder r2 = new BotInlineKeyboard.Builder();
        TLRPC.Message r3 = this.messageOwner;
        if (r3 == null) goto L13;
        TLRPC.ReplyMarkup r32 = r3.reply_markup;
        if ((r32 instanceof TLRPC.TL_replyInlineMarkup) == false) goto L13;
        if (((TLRPC.TL_replyInlineMarkup) r32).rows == null) goto L13;
        r2.addBotKeyboard((TLRPC.TL_replyInlineMarkup) r32);
    L13:
        if (hasSuggestionInlineButtons() == false) goto L16;
        r2.addSeparator();
        r2.addSuggestionKeyboard();
    L16:
        if (r2.isNotEmpty() == false) goto L18;
        this.inlineKeyboardSource = r2.build();
    L18:
        BotInlineKeyboard.Source r22 = this.inlineKeyboardSource;
        if (r22 != null) goto L21;
    L22:
        TLRPC.TL_messageReactions r33 = this.messageOwner.reactions;
        if (r33 != null) goto L25;
    L30:
        if (r22 != null) goto L32;
        return;
    L32:
        if (hasExtendedMedia() == true) goto L69;
        int r34 = 0;
    L35:
        if (r34 >= r22.getRowsCount()) goto L71;
        int r4 = r22.getColumnsCount(r34);
        int r5 = 0;
        int r6 = 0;
    L37:
        if (r5 >= r4) goto L63;
        BotInlineKeyboard.Button r7 = r22.getButton(r34, r5);
        StringBuilder r8 = this.botButtonsLayout;
        r8.append(r34);
        r8.append(r5);
        if ((r7 instanceof BotInlineKeyboard.ButtonBot) == true) goto L41;
    L46:
        String r82 = r7.getText();
        if (r82 != null) goto L49;
        r82 = _UrlKt.FRAGMENT_ENCODE_SET;
    L49:
        CharSequence r83 = Emoji.replaceEmoji(r82, Theme.chat_msgBotButtonPaint.getFontMetricsInt(), false);
    L50:
        StaticLayout r9 = new StaticLayout(r83, Theme.chat_msgBotButtonPaint, AndroidUtilities.dp(2000.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        if (r9.getLineCount() <= 0) goto L62;
        float r84 = r9.getLineWidth(0);
        float r92 = r9.getLineLeft(0);
        if (r92 >= r84) goto L56;
        r84 = r84 - r92;
    L56:
        if (r7.getIconRes() == 0) goto L59;
        r84 = r84 + AndroidUtilities.dp(36.0f);
    L59:
        if (r7.getIconEmoji() == 0) goto L61;
        r84 = r84 + AndroidUtilities.dp(36.0f);
    L61:
        r6 = Math.max(r6, ((int) Math.ceil(r84)) + AndroidUtilities.dp(4.0f));
    L62:
        r5 = r5 + 1;
        goto L37
    L41:
        if (TLKeyboardHelper.isType(((BotInlineKeyboard.ButtonBot) r7).button, TL_keyboard.TL_inlineButtonTypeBuy.class) == false) goto L46;
        if ((getMedia(this.messageOwner).flags & 4) == 0) goto L46;
        r83 = LocaleController.getString(R.string.PaymentReceipt);
        goto L50
    L63:
        this.wantedBotKeyboardWidth = Math.max(this.wantedBotKeyboardWidth, ((r6 + AndroidUtilities.dp(12.0f)) * r4) + (AndroidUtilities.dp(5.0f) * (r4 - 1)));
        r34 = r34 + 1;
        goto L35
    L71:
        return;
    L69:
        return;
    L25:
        if (r33.results.isEmpty() == true) goto L30;
    L26:
        Theme.createCommonMessageResources();
        StringBuilder r35 = this.botButtonsLayout;
        if (r35 != null) goto L29;
        this.botButtonsLayout = new StringBuilder();
        goto L30
    L29:
        r35.setLength(0);
        goto L30
    L21:
        if (hasExtendedMedia() == false) goto L26;
    }

    public float measureVoiceTranscriptionHeight() {
        return measureVoiceTranscriptionHeight(needDrawAvatar());
    }

    public float measureVoiceTranscriptionHeight(boolean r5) {
        float r1 = 0.0f;
        if (getVoiceTranscription() != null) goto L6;
        return 0.0f;
    L6:
        if (this.widePostsTextWidth <= 0) goto L11;
        int r52 = AndroidUtilities.dp(10.0f);
        int r2 = this.widePostsTextWidth;
        if (this.type == 5) goto L10;
        r1 = 14.0f;
    L10:
        int r4 = Math.max(r52, r2 - AndroidUtilities.dp(r1));
    L17:
        return StaticLayout.Builder.obtain(r0, 0, r0.length(), Theme.chat_msgTextPaint, r4).setBreakStrategy(1).setHyphenationFrequency(0).setAlignment(Layout.Alignment.ALIGN_NORMAL).build().getHeight();
    L11:
        int r42 = AndroidUtilities.displaySize.x;
        if (r5 == false) goto L14;
        float r53 = 147.0f;
    L15:
        r4 = r42 - AndroidUtilities.dp(r53);
        goto L17
    L14:
        r53 = 95.0f;
        goto L15
    }

    public boolean mediaExists() {
        if (hasVideoQualities() == false) goto L9;
        VideoPlayer.VideoUri r0 = this.highestQuality;
        if (r0 == null) goto L9;
        return r0.isCached();
    L9:
        return this.mediaExists;
    }

    public boolean needDrawAvatar() {
        if (this.type != 27) goto L6;
        return false;
    L6:
        if (isSponsored() == false) goto L12;
        if (this.searchType != 4) goto L10;
        return true;
    L10:
        return false;
    L12:
        if (this.isRepostPreview == false) goto L15;
        return true;
    L15:
        if (this.isSaved == false) goto L18;
        return true;
    L18:
        if (this.forceAvatar == false) goto L20;
    L55:
        return true;
    L20:
        if (this.customAvatarDrawable != null) goto L55;
        if (this.searchType == 0) goto L26;
        return true;
    L26:
        if (getDialogId() >= 0) goto L38;
        TLRPC.Chat r0 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-getDialogId()));
        if (isEphemeral() == true) goto L30;
    L32:
        if (r0 != null) goto L34;
    L36:
        boolean r02 = false;
    L41:
        if (isSponsored() == false) goto L43;
    L54:
        return false;
    L43:
        if (isFromUser() == false) goto L45;
    L53:
        return true;
    L45:
        if (isFromGroup() == true) goto L53;
        if (r02 == true) goto L53;
        if (this.eventId != 0) goto L53;
        TLRPC.MessageFwdHeader r9 = this.messageOwner.fwd_from;
        if (r9 == null) goto L54;
        if (r9.saved_from_peer == null) goto L54;
    L34:
        if (r0.signature_profiles == false) goto L36;
    L35:
        r02 = true;
        goto L41
    L30:
        if (ChatObject.isChannelAndNotMegaGroup(r0) == false) goto L32;
        return false;
    L38:
        if (getDialogId() != UserObject.VERIFY) goto L36;
        goto L36
    }

    public boolean needDrawBluredPreview() {
        if (this.isRepostPreview == false) goto L6;
        return false;
    L6:
        if (hasExtendedMediaPreview() == false) goto L8;
        return true;
    L8:
        TLRPC.Message r0 = this.messageOwner;
        if ((r0 instanceof TLRPC.TL_message_secret) == false) goto L25;
        int r02 = Math.max(r0.ttl, getMedia(r0).ttl_seconds);
        if (r02 > 0) goto L13;
    L23:
        return false;
    L13:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPhoto) == true) goto L19;
        if (isVideo() == true) goto L19;
        if (isGif() == true) goto L19;
    L21:
        if (isRoundVideo() == false) goto L23;
    L22:
        return true;
    L19:
        if (r02 <= 60) goto L22;
    L25:
        if ((r0 instanceof TLRPC.TL_message) == true) goto L27;
    L35:
        return false;
    L27:
        if (getMedia(r0) == null) goto L35;
        if (getMedia(this.messageOwner).ttl_seconds == 0) goto L35;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L33;
    L34:
        return true;
    L33:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDocument) == false) goto L35;
        goto L34
    }

    public boolean needDrawForwarded() {
        if (this.type != 23) goto L8;
        if (isExpiredStory() == true) goto L8;
        return true;
    L8:
        if (getDialogId() != UserObject.VERIFY) goto L10;
        return false;
    L10:
        boolean r0 = this.isSaved;
        TLRPC.Message r3 = this.messageOwner;
        if (r0 == false) goto L34;
        if (r3 != null) goto L14;
    L32:
        return false;
    L14:
        if (r3.fwd_from == null) goto L32;
        long r32 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        long r5 = getSavedDialogId(r32, this.messageOwner);
        long r7 = DialogObject.getPeerDialogId(this.messageOwner.fwd_from.saved_from_peer);
        if (r7 < 0) goto L20;
        r7 = DialogObject.getPeerDialogId(this.messageOwner.fwd_from.from_id);
    L20:
        if (r7 != 0) goto L28;
        if (r5 >= 0) goto L24;
    L26:
        return false;
    L24:
        if (r5 == UserObject.ANONYMOUS) goto L26;
        return true;
    L28:
        if (r5 == r7) goto L32;
        if (r7 == r32) goto L32;
        return true;
    L34:
        if ((r3.flags & 4) == 0) goto L36;
    L37:
        TLRPC.MessageFwdHeader r02 = this.messageOwner.fwd_from;
        if (r02 != null) goto L40;
    L50:
        return false;
    L40:
        if (r02.imported == true) goto L50;
        TLRPC.Peer r33 = r02.saved_from_peer;
        if (r33 == null) goto L48;
        TLRPC.Peer r03 = r02.from_id;
        if ((r03 instanceof TLRPC.TL_peerChannel) == false) goto L48;
        if (r33.channel_id == r03.channel_id) goto L50;
    L48:
        if (UserConfig.getInstance(this.currentAccount).getClientUserId() == getDialogId()) goto L50;
        return true;
    L36:
        if ((getMedia(this) instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L50;
        goto L37
    }

    public boolean needDrawShareButton() {
        if (this.isRepostPreview == false) goto L6;
        return false;
    L6:
        if (this.sideMenuEnabled == false) goto L9;
        return false;
    L9:
        if (getDialogId() != UserObject.VERIFY) goto L12;
        return false;
    L12:
        if (this.isSaved == false) goto L31;
        long r3 = UserConfig.getInstance(this.currentAccount).clientUserId;
        long r5 = getSavedDialogId(r3, this.messageOwner);
        if (r5 != r3) goto L16;
    L29:
        return false;
    L16:
        if (r5 == UserObject.ANONYMOUS) goto L29;
        TLRPC.Message r9 = this.messageOwner;
        if (r9 == null) goto L29;
        TLRPC.MessageFwdHeader r92 = r9.fwd_from;
        if (r92 == null) goto L29;
        if (r92.from_id == null) goto L26;
    L28:
        return true;
    L26:
        if (r92.saved_from_id != null) goto L28;
        return false;
    L31:
        if (this.type != 27) goto L34;
        return false;
    L34:
        if (isSponsored() == false) goto L37;
        return false;
    L37:
        if (this.hasCode == false) goto L40;
        return false;
    L40:
        if (this.preview == false) goto L43;
        return false;
    L43:
        if (this.scheduled == false) goto L46;
        return false;
    L46:
        if (this.eventId == 0) goto L49;
        return false;
    L49:
        if (this.searchType != 2) goto L51;
        return true;
    L51:
        TLRPC.Message r0 = this.messageOwner;
        if (r0.noforwards == false) goto L55;
        return false;
    L55:
        if (r0.fwd_from != null) goto L57;
    L63:
        int r02 = this.type;
        if (r02 != 13) goto L66;
    L157:
        return false;
    L66:
        if (r02 == 15) goto L157;
        if (r02 == 19) goto L157;
        TLRPC.MessageFwdHeader r03 = this.messageOwner.fwd_from;
        if (r03 == null) goto L78;
        if ((r03.from_id instanceof TLRPC.TL_peerChannel) == false) goto L78;
        if (isOutOwner() == true) goto L78;
        return true;
    L78:
        if (isFromUser() == false) goto L133;
        TLRPC.User r04 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.messageOwner.from_id.user_id));
        if (r04 == null) goto L89;
        if (r04.bot == false) goto L89;
        if ("reviews_bot".equals(UserObject.getPublicUsername(r04)) == false) goto L86;
    L87:
        return true;
    L86:
        if ("ReviewInsightsBot".equals(UserObject.getPublicUsername(r04)) == true) goto L87;
    L89:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaEmpty) == false) goto L91;
    L132:
        return false;
    L91:
        if (getMedia(this.messageOwner) == null) goto L132;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L95;
    L97:
        if (r04 == null) goto L104;
        if (r04.bot == false) goto L104;
        if (hasExtendedMedia() == true) goto L104;
        return true;
    L104:
        if (isOut() == false) goto L106;
    L138:
        return false;
    L106:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGame) == false) goto L108;
    L131:
        return true;
    L108:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaInvoice) == false) goto L112;
        if (hasExtendedMedia() == false) goto L131;
    L112:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L131;
        TLRPC.Peer r05 = this.messageOwner.peer_id;
        TLRPC.Chat r32 = null;
        if (r05 == null) goto L120;
        long r7 = r05.channel_id;
        if (r7 == 0) goto L120;
        r32 = getChat(null, null, r7);
    L120:
        if (ChatObject.isChannel(r32) == false) goto L138;
        if (r32.megagroup == false) goto L138;
        if (ChatObject.isPublic(r32) == true) goto L126;
    L130:
        return false;
    L126:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaContact) == true) goto L130;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGeo) == true) goto L130;
        return true;
    L95:
        if ((getMedia(this.messageOwner).webpage instanceof TLRPC.TL_webPage) == true) goto L97;
    L133:
        TLRPC.Message r06 = this.messageOwner;
        if ((r06.from_id instanceof TLRPC.TL_peerChannel) == true) goto L140;
        if (r06.post == false) goto L138;
    L140:
        if ((getMedia(r06) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L145;
        if (isOutOwner() == true) goto L145;
        return true;
    L145:
        if (isSupergroup() == false) goto L147;
        return false;
    L147:
        TLRPC.Message r07 = this.messageOwner;
        if (r07.peer_id.channel_id == 0) goto L157;
        if (r07.via_bot_id == 0) goto L152;
    L153:
        int r93 = this.type;
        if (r93 == 13) goto L157;
        if (r93 == 15) goto L157;
    L156:
        return true;
    L152:
        if (r07.reply_to == null) goto L156;
    L57:
        if (isOutOwner() == true) goto L63;
        if (this.messageOwner.fwd_from.saved_from_peer == null) goto L63;
        if (getDialogId() != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L63;
        return true;
    }

    public boolean needResendWhenEdit() {
        if (ChatObject.isMonoForum(this.currentAccount, getDialogId()) == true) goto L5;
        return false;
    L5:
        if (getFromChatId() != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L7;
        return false;
    L7:
        if (isOutOwner() == true) goto L13;
        return true;
    L13:
        return false;
    }

    public MessageSuggestionParams obtainSuggestionOffer() {
        TLRPC.Message r2 = this.messageOwner;
        if (r2 != null) goto L5;
        return null;
    L5:
        TLRPC.SuggestedPost r1 = r2.suggested_post;
        if (r1 != null) goto L8;
        TLRPC.MessageAction r22 = r2.action;
        if ((r22 instanceof TLRPC.TL_messageActionSuggestedPostApproval) == true) goto L12;
        return null;
    L12:
        return MessageSuggestionParams.of((TLRPC.TL_messageActionSuggestedPostApproval) r22);
    L8:
        return MessageSuggestionParams.of(r1);
    }

    public MessageSuggestionParams obtainSuggestionOfferFromReply() {
        MessageObject r0 = this.replyMessageObject;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.obtainSuggestionOffer();
    }

    public boolean probablyRingtone() {
        if (isVoiceOnce() == false) goto L6;
        return false;
    L6:
        if (getDocument() != null) goto L8;
    L21:
        return false;
    L8:
        if (RingtoneDataStore.ringtoneSupportedMimeType.contains(getDocument().mime_type) == false) goto L21;
        if (getDocument().size >= (MessagesController.getInstance(this.currentAccount).ringtoneSizeMax * 2)) goto L21;
        int r0 = 0;
    L13:
        if (r0 >= getDocument().attributes.size()) goto L21;
        TLRPC.DocumentAttribute r2 = getDocument().attributes.get(r0);
        if ((r2 instanceof TLRPC.TL_documentAttributeAudio) == false) goto L20;
        if (r2.duration >= 5.0d) goto L20;
        return true;
    L20:
        r0 = r0 + 1;
        goto L13
    }

    public Spannable replaceAnimatedEmoji(CharSequence r2, Paint.FontMetricsInt r3) {
        return replaceAnimatedEmoji(r2, getEntities(), r3, false);
    }

    public void replaceEmojiToLottieFrame(CharSequence r11, int[] r12) {
        if ((r11 instanceof Spannable) == true) goto L5;
        return;
    L5:
        if (SharedConfig.useSystemEmoji == true) goto L34;
        Spannable r112 = (Spannable) r11;
        Emoji.EmojiSpan[] r0 = (Emoji.EmojiSpan[]) r112.getSpans(0, r112.length(), Emoji.EmojiSpan.class);
        AnimatedEmojiSpan[] r1 = (AnimatedEmojiSpan[]) r112.getSpans(0, r112.length(), AnimatedEmojiSpan.class);
        if (r0 == null) goto L33;
        if (r12 != null) goto L11;
        int r122 = 0;
    L12:
        int r123 = r122 - r0.length;
        if (r1 != null) goto L15;
        int r13 = 0;
    L17:
        if ((r123 - r13) > 0) goto L35;
        int r124 = r0.length;
        int r14 = 0;
    L20:
        if (r14 >= r124) goto L36;
        Emoji.EmojiSpan r3 = r0[r14];
        CharSequence r4 = r3.emoji;
        if (Emoji.endsWithRightArrow(r4) == false) goto L24;
        r4 = r4.subSequence(0, r4.length() - 2);
        boolean r5 = true;
    L25:
        TLRPC.Document r42 = MediaDataController.getInstance(this.currentAccount).getEmojiAnimatedSticker(r4);
        if (r42 == null) goto L28;
        int r7 = r112.getSpanStart(r3);
        int r8 = r112.getSpanEnd(r3);
        r112.removeSpan(r3);
        AnimatedEmojiSpan r9 = new AnimatedEmojiSpan(r42, r3.fontMetrics);
        r9.standard = true;
        r9.invert = r5;
        r112.setSpan(r9, r7, r8, 33);
    L28:
        r14 = r14 + 1;
        goto L20
    L24:
        r5 = false;
        goto L25
    L36:
        return;
    L35:
        return;
    L15:
        r13 = r1.length;
        goto L17
    L11:
        r122 = r12[0];
        goto L12
    L33:
        return;
    }

    public CharSequence replaceWithLink(CharSequence r17, String r18, ArrayList<Long> r19, AbstractMap<Long, TLRPC.User> r20, LongSparseArray<TLRPC.User> r21) {
        if (TextUtils.indexOf(r17, r18) < 0) goto L26;
        SpannableStringBuilder r4 = new SpannableStringBuilder(_UrlKt.FRAGMENT_ENCODE_SET);
        int r7 = 0;
    L6:
        if (r7 >= r19.size()) goto L22;
        if (r20 == null) goto L9;
        TLRPC.User r8 = r20.get(r19.get(r7));
    L13:
        if (r8 != null) goto L15;
        r8 = MessagesController.getInstance(this.currentAccount).getUser(r19.get(r7));
    L15:
        if (r8 == null) goto L20;
        String r10 = UserObject.getUserName(r8);
        int r11 = r4.length();
        if (r4.length() == 0) goto L19;
        r4.append(", ");
    L19:
        r4.append(r10);
        r4.setSpan(new URLSpanNoUnderlineBold(_UrlKt.FRAGMENT_ENCODE_SET + r8.id), r11, r10.length() + r11, 33);
    L20:
        r7 = r7 + 1;
        goto L6
    L9:
        if (r21 == null) goto L11;
        r8 = r21.get(r19.get(r7).longValue());
        goto L13
    L11:
        r8 = null;
        goto L13
    L22:
        return TextUtils.replace(r17, new String[]{r18}, new CharSequence[]{r4});
    L26:
        return r17;
    }

    public void resetLayout() {
        this.layoutCreated = false;
    }

    public void resetPlayingProgress() {
        this.audioProgress = 0.0f;
        this.audioProgressSec = 0;
        this.bufferedProgress = 0.0f;
    }

    public boolean selectReaction(ReactionsLayoutInBubble.VisibleReaction r13, boolean r14, boolean r15) {
        TLRPC.Message r152 = this.messageOwner;
        if (r152.reactions != null) goto L16;
        r152.reactions = new TLRPC.TL_messageReactions();
        TLRPC.Message r153 = this.messageOwner;
        TLRPC.TL_messageReactions r0 = r153.reactions;
        if (getDialogId(r153) != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L7;
        boolean r154 = true;
    L8:
        r0.reactions_as_tags = r154;
        TLRPC.TL_messageReactions r155 = this.messageOwner.reactions;
        if (isFromGroup() == false) goto L11;
    L14:
        boolean r02 = true;
    L15:
        r155.can_see_list = r02;
        goto L16
    L11:
        if (isFromUser() == true) goto L14;
        r02 = false;
        goto L15
    L7:
        r154 = false;
    L16:
        ArrayList r156 = new ArrayList();
        TLRPC.ReactionCount r03 = null;
        int r3 = 0;
        int r4 = 0;
    L18:
        if (r3 >= this.messageOwner.reactions.results.size()) goto L44;
        TLRPC.ReactionCount r5 = this.messageOwner.reactions.results.get(r3);
        if (r5.chosen == true) goto L22;
    L26:
        TLRPC.Reaction r52 = this.messageOwner.reactions.results.get(r3).reaction;
        if ((r52 instanceof TLRPC.TL_reactionEmoji) == false) goto L35;
        String r8 = r13.emojicon;
        if (r8 == null) goto L42;
        if (((TLRPC.TL_reactionEmoji) r52).emoticon.equals(r8) == false) goto L35;
        r03 = this.messageOwner.reactions.results.get(r3);
    L42:
        r3 = r3 + 1;
    L35:
        if ((r52 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L42;
        long r82 = r13.documentId;
        if (r82 == 0) goto L42;
        if (((TLRPC.TL_reactionCustomEmoji) r52).document_id != r82) goto L42;
        r03 = this.messageOwner.reactions.results.get(r3);
        goto L42
    L22:
        if ((r5.reaction instanceof TLRPC.TL_reactionPaid) == true) goto L26;
        r156.add(r5);
        int r53 = r5.chosen_order;
        if (r53 <= r4) goto L26;
        r4 = r53;
        goto L26
    L44:
        if (r156.isEmpty() == false) goto L46;
    L49:
        int r142 = MessagesController.getInstance(this.currentAccount).getMaxUserReactionsCount();
        if (r156.isEmpty() == true) goto L71;
        if (r156.contains(r03) == false) goto L71;
        if (r03 == null) goto L58;
        r03.chosen = false;
        int r143 = r03.count - 1;
        r03.count = r143;
        if (r143 > 0) goto L58;
        this.messageOwner.reactions.results.remove(r03);
    L58:
        if (this.messageOwner.reactions.can_see_list == false) goto L68;
        int r144 = 0;
    L61:
        if (r144 >= this.messageOwner.reactions.recent_reactions.size()) goto L68;
        if (getPeerId(this.messageOwner.reactions.recent_reactions.get(r144).peer_id) != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L67;
        if (ReactionsUtils.compare(this.messageOwner.reactions.recent_reactions.get(r144).reaction, r13) == false) goto L67;
        this.messageOwner.reactions.recent_reactions.remove(r144);
        r144 = r144 - 1;
    L67:
        r144 = r144 + 1;
    L68:
        this.reactionsChanged = true;
        return false;
    L71:
        if (r156.isEmpty() == true) goto L97;
        if (r156.size() < r142) goto L97;
        int r54 = 0;
        int r32 = 1;
    L76:
        if (r32 >= r156.size()) goto L83;
        if ((((TLRPC.ReactionCount) r156.get(r32)).reaction instanceof TLRPC.TL_reactionPaid) == true) goto L82;
        if (((TLRPC.ReactionCount) r156.get(r32)).chosen_order >= ((TLRPC.ReactionCount) r156.get(r54)).chosen_order) goto L82;
        r54 = r32;
    L82:
        r32 = r32 + 1;
        goto L76
    L83:
        TLRPC.ReactionCount r33 = (TLRPC.ReactionCount) r156.get(r54);
        r33.chosen = false;
        int r55 = r33.count - 1;
        r33.count = r55;
        if (r55 > 0) goto L86;
        this.messageOwner.reactions.results.remove(r33);
    L86:
        r156.remove(r33);
        if (this.messageOwner.reactions.can_see_list == false) goto L71;
        int r34 = 0;
    L90:
        if (r34 >= this.messageOwner.reactions.recent_reactions.size()) goto L71;
        if (getPeerId(this.messageOwner.reactions.recent_reactions.get(r34).peer_id) != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L96;
        if (ReactionsUtils.compare(this.messageOwner.reactions.recent_reactions.get(r34).reaction, r13) == false) goto L96;
        this.messageOwner.reactions.recent_reactions.remove(r34);
        r34 = r34 - 1;
    L96:
        r34 = r34 + 1;
    L97:
        if (r03 != null) goto L112;
        int r157 = MessagesController.getInstance(this.currentAccount).getChatMaxUniqReactions(getDialogId());
        TLRPC.Message r04 = this.messageOwner;
        if (r04 == null) goto L107;
        TLRPC.TL_messageReactions r05 = r04.reactions;
        if (r05 == null) goto L107;
        ArrayList<TLRPC.ReactionCount> r06 = r05.results;
        int r35 = r06.size();
        int r56 = 0;
        int r83 = 0;
    L103:
        if (r83 >= r35) goto L109;
        TLRPC.ReactionCount r9 = r06.get(r83);
        r83 = r83 + 1;
        if ((r9.reaction instanceof TLRPC.TL_reactionPaid) == true) goto L103;
        r56 = r56 + 1;
    L109:
        if ((r56 + 1) <= r157) goto L111;
        return false;
    L111:
        r03 = new TLRPC.TL_reactionCount();
        r03.reaction = r13.toTLReaction();
        this.messageOwner.reactions.results.add(r03);
    L107:
        r56 = 0;
    L112:
        r03.chosen = true;
        r03.count++;
        r03.chosen_order = r4 + 1;
        TLRPC.Message r158 = this.messageOwner;
        if (r158.reactions.can_see_list == false) goto L115;
    L117:
        TLRPC.TL_messagePeerReaction r145 = new TLRPC.TL_messagePeerReaction();
        TLRPC.Message r159 = this.messageOwner;
        if (r159.isThreadMessage == true) goto L120;
    L122:
        r145.peer_id = MessagesController.getInstance(this.currentAccount).getSendAsSelectedPeer(getDialogId());
    L123:
        this.messageOwner.reactions.recent_reactions.add(0, r145);
        if (r13.emojicon == null) goto L126;
        TLRPC.TL_reactionEmoji r1510 = new TLRPC.TL_reactionEmoji();
        r145.reaction = r1510;
        r1510.emoticon = r13.emojicon;
    L127:
        this.reactionsChanged = true;
        return true;
    L126:
        TLRPC.TL_reactionCustomEmoji r1511 = new TLRPC.TL_reactionCustomEmoji();
        r145.reaction = r1511;
        r1511.document_id = r13.documentId;
        goto L127
    L120:
        if (r159.fwd_from == null) goto L122;
        r145.peer_id = MessagesController.getInstance(this.currentAccount).getSendAsSelectedPeer(getFromChatId());
        goto L123
    L115:
        if (r158.dialog_id <= 0) goto L127;
        if (r142 <= 1) goto L127;
    L46:
        if (r156.contains(r03) == false) goto L49;
        if (r14 == false) goto L49;
        return true;
    }

    public void setContentIsRead() {
        this.messageOwner.media_unread = false;
    }

    public void setIsRead() {
        this.messageOwner.unread = false;
    }

    public void setMyPaidReactionDialogId(long r8) {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 == null) goto L24;
        TLRPC.TL_messageReactions r02 = r0.reactions;
        if (r02 == null) goto L35;
        ArrayList<TLRPC.MessageReactor> r03 = r02.top_reactors;
        if (r03 == null) goto L36;
        int r1 = r03.size();
        int r3 = 0;
    L11:
        if (r3 >= r1) goto L37;
        TLRPC.MessageReactor r4 = r03.get(r3);
        r3 = r3 + 1;
        TLRPC.MessageReactor r42 = r4;
        if (r42 == null) goto L11;
        if (r42.my == false) goto L11;
        if (r8 != UserObject.ANONYMOUS) goto L19;
        boolean r5 = true;
    L20:
        r42.anonymous = r5;
        int r6 = r42.flags;
        if (r5 == true) goto L22;
        r42.flags = r6 | 8;
        r42.peer_id = MessagesController.getInstance(this.currentAccount).getPeer(r8);
        goto L11
    L22:
        r42.flags = r6 & (-9);
        r42.peer_id = null;
        goto L11
    L19:
        r5 = false;
        goto L20
    L37:
        return;
    L36:
        return;
    L35:
        return;
    }

    public void setQuery(String r2) {
        setQuery(r2, true);
    }

    public void setQuery(String r17, boolean r18) {
        if (TextUtils.isEmpty(r17) == false) goto L6;
        this.highlightedWords = null;
        this.messageTrimmedToHighlight = null;
        this.messageTrimmedToHighlightCut = true;
        return;
    L6:
        ArrayList<String> r2 = new ArrayList();
        String r4 = r17.trim().toLowerCase();
        String[] r6 = r4.split("[^\\p{L}#$]+");
        ArrayList r7 = new ArrayList();
        TLRPC.MessageReplyHeader r8 = this.messageOwner.reply_to;
        if (r8 != null) goto L9;
    L17:
        int r82 = 0;
        if (TextUtils.isEmpty(this.messageOwner.message) == true) goto L27;
        String r3 = this.messageOwner.message.trim().toLowerCase();
        if (r3.contains(r4) == true) goto L22;
    L25:
        r7.addAll(Arrays.asList(r3.split("[^\\p{L}#$]+")));
        goto L27
    L22:
        if (r2.contains(r4) == true) goto L25;
        r2.add(r4);
        handleFoundWords(r2, r6, false, r18);
        return;
    L27:
        if (getDocument() == null) goto L35;
        String r32 = FileLoader.getDocumentFileName(getDocument()).toLowerCase();
        if (r32.contains(r4) == true) goto L31;
    L33:
        r7.addAll(Arrays.asList(r32.split("[^\\p{L}#$]+")));
        goto L35
    L31:
        if (r2.contains(r4) == true) goto L33;
        r2.add(r4);
    L35:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaWebPage) == true) goto L37;
    L48:
        String r33 = getMusicAuthor();
        if (r33 == null) goto L56;
        String r34 = r33.toLowerCase();
        if (r34.contains(r4) == true) goto L53;
    L55:
        r7.addAll(Arrays.asList(r34.split("[^\\p{L}#$]+")));
        goto L56
    L53:
        if (r2.contains(r4) == true) goto L55;
        r2.add(r4);
    L56:
        int r35 = r6.length;
        int r42 = 0;
    L57:
        if (r42 >= r35) goto L83;
        String r5 = r6[r42];
        if (r5.length() < 2) goto L82;
        int r9 = r82;
    L63:
        if (r9 >= r7.size()) goto L82;
        if (r2.contains(r7.get(r9)) == true) goto L81;
        String r10 = (String) r7.get(r9);
        int r11 = r10.indexOf(r5.charAt(r82));
        if (r11 < 0) goto L81;
        int r12 = Math.max(r5.length(), r10.length());
        if (r11 == 0) goto L73;
        r10 = r10.substring(r11);
    L73:
        int r112 = Math.min(r5.length(), r10.length());
        int r13 = r82;
        int r14 = r13;
    L74:
        if (r13 >= r112) goto L79;
        if (r10.charAt(r13) != r5.charAt(r13)) goto L79;
        r14 = r14 + 1;
        r13 = r13 + 1;
    L79:
        if ((r14 / r12) < 0.5d) goto L81;
        r2.add((String) r7.get(r9));
    L81:
        r9 = r9 + 1;
        r82 = 0;
    L82:
        r42 = r42 + 1;
        r82 = 0;
        goto L57
    L83:
        handleFoundWords(r2, r6, r82, r18);
        return;
    L37:
        if ((getMedia(this.messageOwner).webpage instanceof TLRPC.TL_webPage) == false) goto L48;
        TLRPC.WebPage r36 = getMedia(this.messageOwner).webpage;
        String r92 = r36.title;
        if (r92 != null) goto L41;
        r92 = r36.site_name;
    L41:
        if (r92 == null) goto L48;
        String r37 = r92.toLowerCase();
        if (r37.contains(r4) == true) goto L45;
    L47:
        r7.addAll(Arrays.asList(r37.split("[^\\p{L}#$]+")));
        goto L48
    L45:
        if (r2.contains(r4) == true) goto L47;
        r2.add(r4);
        goto L47
    L9:
        if (TextUtils.isEmpty(r8.quote_text) == true) goto L17;
        String r83 = this.messageOwner.reply_to.quote_text.trim().toLowerCase();
        if (r83.contains(r4) == true) goto L13;
    L16:
        r7.addAll(Arrays.asList(r83.split("[^\\p{L}#$]+")));
        goto L17
    L13:
        if (r2.contains(r4) == true) goto L16;
        r2.add(r4);
        handleFoundWords(r2, r6, true, r18);
    }

    public void setType() {
        int r0 = this.type;
        this.type = 1000;
        this.isRoundVideoCached = 0;
        if (isSponsored() == false) goto L5;
        this.type = 0;
    L263:
        if (r0 == 1000) goto L269;
        int r1 = this.type;
        if (r0 == r1) goto L270;
        if (r1 == 19) goto L271;
        updateMessageText(MessagesController.getInstance(this.currentAccount).getUsers(), MessagesController.getInstance(this.currentAccount).getChats(), null, null);
        generateThumbs(false);
        return;
    L271:
        return;
    L270:
        return;
    L269:
        return;
    L5:
        int r5 = 1;
        if (this.channelJoined == false) goto L8;
        this.contentType = 0;
        this.type = 27;
        this.channelJoinedExpanded = MessagesController.getInstance(this.currentAccount).getMainSettings().getBoolean("c" + getDialogId() + "_rec", true);
        goto L263
    L8:
        TLRPC.Message r3 = this.messageOwner;
        if ((r3 instanceof TLRPC.TL_message) == true) goto L124;
        if ((r3 instanceof TLRPC.TL_messageForwarded_old2) == true) goto L124;
        TLRPC.TL_channelAdminLogEvent r6 = this.currentEvent;
        if (r6 == null) goto L28;
        TLRPC.ChannelAdminLogEventAction r62 = r6.action;
        if ((r62 instanceof TLRPC.TL_channelAdminLogEventActionChangeWallpaper) == false) goto L28;
        TLRPC.TL_channelAdminLogEventActionChangeWallpaper r63 = (TLRPC.TL_channelAdminLogEventActionChangeWallpaper) r62;
        this.contentType = 1;
        TLRPC.WallPaper r32 = r63.new_value;
        if ((r32 instanceof TLRPC.TL_wallPaperNoFile) == true) goto L20;
    L24:
        this.type = 22;
        ArrayList<TLRPC.PhotoSize> r33 = new ArrayList();
        this.photoThumbs = r33;
        TLRPC.Document r52 = r63.new_value.document;
        if (r52 == null) goto L263;
        r33.addAll(r52.thumbs);
        this.photoThumbsObject = r63.new_value.document;
        goto L263
    L20:
        if (r32.id != 0) goto L24;
        if (r32.settings != null) goto L24;
        this.type = 10;
    L28:
        if ((r3 instanceof TLRPC.TL_messageService) == false) goto L263;
        TLRPC.MessageAction r34 = r3.action;
        if ((r34 instanceof TLRPC.TL_messageActionChangeCreator) == false) goto L32;
    L122:
        this.contentType = 1;
        this.type = 10;
        goto L263
    L32:
        if ((r34 instanceof TLRPC.TL_messageActionNewCreatorPending) == true) goto L122;
        if ((r34 instanceof TLRPC.TL_messageActionManagedBotCreated) == true) goto L122;
        if ((r34 instanceof TLRPC.TL_messageActionSetSameChatWallPaper) == false) goto L40;
        this.contentType = 1;
        this.type = 10;
        goto L263
    L40:
        if ((r34 instanceof TLRPC.TL_messageActionSetChatWallPaper) == false) goto L45;
        this.contentType = 1;
        this.type = 22;
        TLRPC.TL_messageActionSetChatWallPaper r35 = (TLRPC.TL_messageActionSetChatWallPaper) r34;
        ArrayList<TLRPC.PhotoSize> r53 = new ArrayList();
        this.photoThumbs = r53;
        TLRPC.Document r64 = r35.wallpaper.document;
        if (r64 == null) goto L263;
        r53.addAll(r64.thumbs);
        this.photoThumbsObject = r35.wallpaper.document;
        goto L263
    L45:
        if ((r34 instanceof TLRPC.TL_messageActionSuggestProfilePhoto) == false) goto L48;
        this.contentType = 1;
        this.type = 21;
        ArrayList<TLRPC.PhotoSize> r36 = new ArrayList();
        this.photoThumbs = r36;
        r36.addAll(this.messageOwner.action.photo.sizes);
        this.photoThumbsObject = this.messageOwner.action.photo;
        goto L263
    L48:
        if ((r34 instanceof TLRPC.TL_messageActionLoginUnknownLocation) == false) goto L51;
        this.type = 0;
        goto L263
    L51:
        if ((r34 instanceof TLRPC.TL_messageActionGiftCode) == false) goto L56;
        if (((TLRPC.TL_messageActionGiftCode) r34).boost_peer == null) goto L56;
        this.contentType = 1;
        this.type = 25;
    L56:
        if ((r34 instanceof TLRPC.TL_messageActionGiftPremium) == false) goto L58;
    L121:
        this.contentType = 1;
        this.type = 18;
        goto L263
    L58:
        if ((r34 instanceof TLRPC.TL_messageActionGiftCode) == true) goto L121;
        if ((r34 instanceof TLRPC.TL_messageActionGiftStars) == false) goto L63;
    L120:
        this.contentType = 1;
        this.type = 30;
        goto L263
    L63:
        if ((r34 instanceof TLRPC.TL_messageActionStarGift) == true) goto L120;
        if ((r34 instanceof TLRPC.TL_messageActionPrizeStars) == true) goto L120;
        if ((r34 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L71;
        if (((TLRPC.TL_messageActionStarGiftUnique) r34).refunded == true) goto L120;
    L71:
        if ((r34 instanceof TLRPC.TL_messageActionGiftTon) == true) goto L120;
        if ((r34 instanceof TLRPC.TL_messageActionChatEditPhoto) == false) goto L76;
    L119:
        this.contentType = 1;
        this.type = 11;
        goto L263
    L76:
        if ((r34 instanceof TLRPC.TL_messageActionUserUpdatedPhoto) == true) goto L119;
        if ((r34 instanceof TLRPC.TL_messageEncryptedAction) == false) goto L88;
        TLRPC.DecryptedMessageAction r37 = r34.encryptedAction;
        if ((r37 instanceof TLRPC.TL_decryptedMessageActionScreenshotMessages) == false) goto L83;
    L86:
        this.contentType = 1;
        this.type = 10;
        goto L263
    L83:
        if ((r37 instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL) == true) goto L86;
        this.contentType = -1;
        this.type = -1;
        goto L263
    L88:
        if ((r34 instanceof TLRPC.TL_messageActionHistoryClear) == false) goto L91;
        this.contentType = -1;
        this.type = -1;
        goto L263
    L91:
        if ((r34 instanceof TLRPC.TL_messageActionPhoneCall) == false) goto L93;
    L118:
        this.type = 16;
        goto L263
    L93:
        if ((r34 instanceof TLRPC.TL_messageActionConferenceCall) == true) goto L118;
        if ((r34 instanceof TLRPC.TL_messageActionSetChatTheme) == false) goto L101;
        if ((((TLRPC.TL_messageActionSetChatTheme) r34).theme instanceof TLRPC.TL_chatThemeUniqueGift) == false) goto L101;
        this.type = 31;
        this.contentType = 1;
    L101:
        if ((r34 instanceof TLRPC.TL_messageActionStarGiftPurchaseOffer) == false) goto L104;
        this.type = 33;
        this.contentType = 1;
        goto L263
    L104:
        if ((r34 instanceof TLRPC.TL_messageActionChangeCommunity) == false) goto L109;
        if (((TLRPC.TL_messageActionChangeCommunity) r34).community_id == 0) goto L109;
        this.type = 37;
        this.contentType = 1;
    L109:
        if ((r34 instanceof TLRPC.TL_messageActionStarGiftPurchaseOfferDeclined) == false) goto L112;
        this.type = 34;
        this.contentType = 1;
        goto L263
    L112:
        if ((r34 instanceof TLRPC.TL_messageActionSuggestBirthday) == false) goto L115;
        this.type = 32;
        this.contentType = 1;
        goto L263
    L115:
        if ((r34 instanceof TLRPC.TL_messageActionNoForwardsRequest) == false) goto L117;
        this.type = 35;
        this.contentType = 1;
        goto L263
    L117:
        this.contentType = 1;
        this.type = 10;
    L124:
        if ((getMedia(r3) instanceof TLRPC.TL_messageMediaUnsupported) == false) goto L127;
        this.contentType = 10;
        this.type = 38;
        goto L263
    L127:
        if (this.isRestrictedMessage == false) goto L129;
        this.type = 0;
        goto L263
    L129:
        TLRPC.Message r38 = this.messageOwner;
        if (r38.rich_message == null) goto L133;
        this.type = 36;
        goto L263
    L133:
        if (this.emojiAnimatedSticker != null) goto L260;
        if (this.emojiAnimatedStickerId != null) goto L260;
        if ((r38.media instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L141;
        this.type = 29;
        goto L263
    L141:
        if (isMediaEmpty(false) == false) goto L156;
        if (isDice() == true) goto L156;
        if (isSponsored() == true) goto L156;
        if (this.emojiOnlyCount < 1) goto L156;
        if (this.hasUnwrappedEmoji == true) goto L156;
        if (this.messageOwner == null) goto L156;
        if (hasNonEmojiEntities() == true) goto L156;
        this.type = 19;
    L156:
        if (isMediaEmpty() == false) goto L163;
        this.type = 0;
        if (TextUtils.isEmpty(this.messageText) == false) goto L263;
        if (this.eventId != 0) goto L263;
        this.messageText = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L263
    L163:
        if (hasExtendedMediaPreview() == false) goto L166;
        this.type = 20;
        goto L263
    L166:
        if (getMedia(this.messageOwner).ttl_seconds == 0) goto L179;
        if ((getMedia(this.messageOwner).photo instanceof TLRPC.TL_photoEmpty) == false) goto L170;
    L177:
        this.contentType = 1;
        this.type = 10;
        goto L263
    L170:
        if ((getDocument() instanceof TLRPC.TL_documentEmpty) == true) goto L177;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDocument) == false) goto L176;
        if (getDocument() == null) goto L177;
    L176:
        if (this.forceExpired == true) goto L177;
    L179:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGiveaway) == false) goto L182;
        this.type = 26;
        goto L263
    L182:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGiveawayResults) == false) goto L185;
        this.type = 28;
        goto L263
    L185:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDice) == false) goto L190;
        this.type = 15;
        if (getMedia(this.messageOwner).document != null) goto L263;
        getMedia(this.messageOwner).document = new TLRPC.TL_document();
        getMedia(this.messageOwner).document.file_reference = new byte[0];
        getMedia(this.messageOwner).document.mime_type = "application/x-tgsdice";
        getMedia(this.messageOwner).document.dc_id = Integer.MIN_VALUE;
        getMedia(this.messageOwner).document.id = -2147483648L;
        TLRPC.TL_documentAttributeImageSize r39 = new TLRPC.TL_documentAttributeImageSize();
        r39.w = 512;
        r39.h = 512;
        getMedia(this.messageOwner).document.attributes.add(r39);
        goto L263
    L190:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L196;
        if (isVideo() == false) goto L194;
        r5 = 3;
    L194:
        this.type = r5;
        goto L263
    L196:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGeo) == false) goto L198;
    L258:
        this.type = 4;
        goto L263
    L198:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaVenue) == true) goto L258;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGeoLive) == true) goto L258;
        if (isRoundVideo() == false) goto L206;
        this.type = 5;
        goto L263
    L206:
        if (isVideo() == false) goto L209;
        this.type = 3;
        goto L263
    L209:
        if (isVoice() == false) goto L212;
        this.type = 2;
        goto L263
    L212:
        if (isMusic() == false) goto L215;
        this.type = 14;
        goto L263
    L215:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaContact) == false) goto L218;
        this.type = 12;
        goto L263
    L218:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaPoll) == false) goto L220;
    L257:
        this.type = 17;
        this.checkedVotes = new ArrayList();
        goto L263
    L220:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaToDo) == true) goto L257;
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaUnsupported) == false) goto L226;
        this.type = 0;
        goto L263
    L226:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaDocument) == false) goto L243;
        TLRPC.Document r310 = getDocument();
        if (r310 != null) goto L230;
    L241:
        this.type = 9;
        goto L263
    L230:
        if (r310.mime_type == null) goto L241;
        if (isGifDocument(r310, hasValidGroupId()) == false) goto L235;
        this.type = 8;
        goto L263
    L235:
        if (isSticker() == false) goto L238;
        this.type = 13;
        goto L263
    L238:
        if (isAnimatedSticker() == false) goto L240;
        this.type = 15;
        goto L263
    L240:
        this.type = 9;
        goto L263
    L243:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaGame) == false) goto L246;
        this.type = 0;
        goto L263
    L246:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaInvoice) == false) goto L249;
        this.type = 0;
        goto L263
    L249:
        if ((getMedia(this.messageOwner) instanceof TLRPC.TL_messageMediaStory) == false) goto L263;
        if (getMedia(this.messageOwner).via_mention == false) goto L253;
        int r311 = 24;
    L254:
        this.type = r311;
        if (r311 != 24) goto L263;
        this.contentType = 1;
        goto L263
    L253:
        r311 = 23;
    L260:
        if (isSticker() == false) goto L262;
        this.type = 13;
        goto L263
    L262:
        this.type = 15;
        goto L263
    }

    public void setWidePostsTextWidth(int r2) {
        int r22 = Math.max(0, r2);
        if (this.widePostsTextWidth == r22) goto L6;
        this.widePostsTextWidth = r22;
        this.cachedTextHeight = null;
        this.cachedApproximateHeight = null;
        resetLayout();
        return;
    }

    public boolean shouldAnimateSending() {
        if (this.wasJustSent == true) goto L5;
        return false;
    L5:
        if (this.type != 5) goto L7;
        return true;
    L7:
        if (isVoice() == false) goto L9;
        return true;
    L9:
        if (isAnyKindOfSticker() == false) goto L13;
        if (this.sendAnimationData == null) goto L13;
        return true;
    L13:
        if (this.messageText != null) goto L15;
        return false;
    L15:
        if (this.sendAnimationData == null) goto L24;
        return true;
    L24:
        return false;
    }

    public boolean shouldDrawReactions() {
        if (this.isRepostPreview == false) goto L6;
        return false;
    L6:
        return true;
    }

    public boolean shouldDrawReactionsInLayout() {
        return true;
    }

    public boolean shouldDrawWithoutBackground() {
        if (isSponsored() == true) goto L16;
        int r0 = this.type;
        if (r0 != 13) goto L7;
        return true;
    L7:
        if (r0 != 15) goto L9;
        return true;
    L9:
        if (r0 != 5) goto L11;
        return true;
    L11:
        if (r0 != 19) goto L13;
        return true;
    L13:
        if (isExpiredStory() == false) goto L22;
        return true;
    L22:
        return false;
    L16:
        return false;
    }

    public boolean shouldEncryptPhotoOrVideo() {
        return shouldEncryptPhotoOrVideo(this.currentAccount, this.messageOwner);
    }

    public void spoilLoginCode() {
        if (this.spoiledLoginCode == false) goto L5;
        return;
    L5:
        if (this.messageText == null) goto L30;
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L9;
        return;
    L9:
        if (r0.entities == null) goto L32;
        TLRPC.Peer r02 = r0.from_id;
        if ((r02 instanceof TLRPC.TL_peerUser) == false) goto L33;
        long r03 = r02.user_id;
        if (r03 == 777000) goto L17;
        if (r03 == UserObject.VERIFY) goto L17;
        return;
    L17:
        if (loginCodePattern != null) goto L27;
        loginCodePattern = Pattern.compile("[\\d\\-]{5,8}");
    L27:
        Matcher r04 = loginCodePattern.matcher(this.messageText);     // Catch: Exception -> L23
        if (r04.find() == false) goto L25;
        TLRPC.TL_messageEntitySpoiler r1 = new TLRPC.TL_messageEntitySpoiler();     // Catch: Exception -> L23
        r1.offset = r04.start();     // Catch: Exception -> L23
        r1.length = r04.end() - r1.offset;     // Catch: Exception -> L23
        this.messageOwner.entities.add(r1);     // Catch: Exception -> L23
    L25:
        this.spoiledLoginCode = true;
        return;
    L23:
        e = move-exception;
        FileLog.e(e);
        goto L25
    L33:
        return;
    L32:
        return;
    }

    public int textHeight() {
        RichMessageLayout r0 = this.richLayout;
        if (r0 != null) goto L5;
        int r1 = 0;
        if (this.textLayoutBlocks != null) goto L9;
        return 0;
    L9:
        int r02 = 0;
    L11:
        if (r1 >= this.textLayoutBlocks.size()) goto L13;
        r02 = r02 + ((this.textLayoutBlocks.get(r1).padTop + this.textLayoutBlocks.get(r1).height()) + this.textLayoutBlocks.get(r1).padBottom);
        r1 = r1 + 1;
        goto L11
    L13:
        return r02;
    L5:
        return r0.getHeight();
    }

    public int textHeight(ChatMessageCell.TransitionParams r5) {
        int r1 = 0;
        if (this.textLayoutBlocks != null) goto L5;
        return 0;
    L5:
        int r0 = 0;
    L7:
        if (r1 >= this.textLayoutBlocks.size()) goto L9;
        r0 = r0 + ((this.textLayoutBlocks.get(r1).padTop + this.textLayoutBlocks.get(r1).height(r5)) + this.textLayoutBlocks.get(r1).padBottom);
        r1 = r1 + 1;
        goto L7
    L9:
        return r0;
    }

    public int textHeightCached() {
        RichMessageLayout r0 = this.richLayout;
        if (r0 != null) goto L5;
        Integer r02 = this.cachedTextHeight;
        if (r02 != null) goto L9;
        int r1 = 0;
        if (this.textLayoutBlocks != null) goto L14;
        this.cachedTextHeight = 0;
        return 0;
    L14:
        int r03 = 0;
    L16:
        if (r1 >= this.textLayoutBlocks.size()) goto L18;
        r03 = r03 + ((this.textLayoutBlocks.get(r1).padTop + this.textLayoutBlocks.get(r1).heightCollapsed()) + this.textLayoutBlocks.get(r1).padBottom);
        r1 = r1 + 1;
        goto L16
    L18:
        this.cachedTextHeight = Integer.valueOf(r03);
        return r03;
    L9:
        return r02.intValue();
    L5:
        return r0.getHeight();
    }

    public void toggleChannelRecommendations() {
        expandChannelRecommendations(!this.channelJoinedExpanded);
    }

    public void updateMessageText() {
        updateMessageText(MessagesController.getInstance(this.currentAccount).getUsers(), MessagesController.getInstance(this.currentAccount).getChats(), null, null);
    }

    public void updateQualitiesCached(boolean r9) {
        ArrayList<VideoPlayer.Quality> r0 = this.videoQualities;
        if (r0 != null) goto L6;
        this.cachedQuality = null;
        hasVideoQualities(r9);
        return;
    L6:
        int r1 = r0.size();
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L11;
        VideoPlayer.Quality r4 = r0.get(r3);
        r3 = r3 + 1;
        ArrayList<VideoPlayer.VideoUri> r42 = r4.uris;
        int r5 = r42.size();
        int r6 = 0;
    L9:
        if (r6 >= r5) goto L7;
        VideoPlayer.VideoUri r7 = r42.get(r6);
        r6 = r6 + 1;
        r7.updateCached(r9);
        goto L9
    L11:
        this.highestQuality = VideoPlayer.getQualityForPlayer(this.videoQualities);
        this.thumbQuality = VideoPlayer.getQualityForThumb(this.videoQualities);
        this.cachedQuality = VideoPlayer.getCachedQuality(this.videoQualities);
    }

    public boolean updateSideMenuEnabled(boolean r2) {
        if (this.sideMenuEnabled != r2) goto L6;
        return false;
    L6:
        this.sideMenuEnabled = r2;
        generateLayout(null);
        return true;
    }

    public boolean updateTranslation() {
        return updateTranslation(false);
    }

    public boolean updateTranslation(boolean r12) {
        TLRPC.Message r0 = this.messageOwner;
        if (r0 != null) goto L5;
    L8:
        MessageObject r02 = this.replyMessageObject;
        if (r02 == null) goto L14;
        if (r02 == this) goto L14;
        if (r02.updateTranslation(r12) == false) goto L14;
        boolean r03 = true;
    L15:
        TranslateController r3 = MessagesController.getInstance(this.currentAccount).getTranslateController();
        TLRPC.Message r4 = this.messageOwner;
        if (r4 != null) goto L18;
        TLRPC.TL_textWithEntities r6 = null;
    L22:
        if (r4 != null) goto L24;
    L26:
        TLRPC.TL_textWithEntities r7 = null;
    L27:
        if (r4 != null) goto L29;
    L31:
        TLRPC.TL_textWithEntities r8 = null;
    L32:
        if (r8 == null) goto L53;
        if (r4 == null) goto L53;
        if (r4.summarizedOpen == false) goto L53;
        if (TranslateController.isSummarizable(this) == false) goto L53;
        if (TranslateController.isTranslatable(this) == false) goto L53;
        if (r3.isTranslatingDialog(getDialogId()) == false) goto L53;
        if (r3.isTranslateDialogHidden(getDialogId()) == true) goto L53;
        if (TextUtils.equals(r3.getDialogTranslateTo(getDialogId()), this.messageOwner.translatedSummaryLanguage) == false) goto L53;
        if (this.summarized == true) goto L49;
    L51:
        this.summarized = true;
        this.translated = true;
        applyNewText(r8.text);
        generateCaption();
        return true;
    L49:
        if (this.translated == false) goto L51;
        return r03;
    L53:
        TLRPC.Message r42 = this.messageOwner;
        if (r42 == null) goto L68;
        if (r42.summarizedOpen == false) goto L68;
        if (TranslateController.isSummarizable(this) == false) goto L68;
        if (r7 == null) goto L68;
        if (this.summarized == true) goto L63;
    L65:
        this.summarized = true;
        this.translated = false;
        applyNewText(r7.text);
        generateCaption();
        return true;
    L63:
        if (this.translated == true) goto L65;
        return r03;
    L68:
        if (this.messageOwner != null) goto L70;
    L93:
        TLRPC.Message r32 = this.messageOwner;
        if (r32 == null) goto L105;
        if (r12 == false) goto L97;
    L100:
        this.translated = false;
        this.summarized = false;
        if (this.type != 36) goto L103;
        generateLayout(null);
    L104:
        return true;
    L103:
        applyNewText(r32.message);
        generateCaption();
        goto L104
    L97:
        if (this.translated == true) goto L100;
        if (this.summarized == true) goto L100;
    L105:
        return r03;
    L70:
        if (TranslateController.isTranslatable(this) == false) goto L93;
        if (r3.isTranslatingDialog(getDialogId()) == false) goto L93;
        if (r3.isTranslateDialogHidden(getDialogId()) == true) goto L93;
        if (r6 != null) goto L81;
        TLRPC.Message r43 = this.messageOwner;
        if (r43.translatedPoll != null) goto L81;
        if (r43.translatedRichMessage == null) goto L93;
    L81:
        if (TextUtils.equals(r3.getDialogTranslateTo(getDialogId()), this.messageOwner.translatedToLanguage) == false) goto L93;
        if (this.translated == true) goto L85;
    L87:
        this.translated = true;
        this.summarized = false;
        if (this.type != 36) goto L90;
        generateLayout(null);
    L92:
        return true;
    L90:
        if (r6 == null) goto L92;
        applyNewText(r6.text);
        generateCaption();
        goto L92
    L85:
        if (this.summarized == true) goto L87;
        return r03;
    L29:
        if (r4.summarizedOpen == false) goto L31;
        r8 = r4.translatedSummaryText;
        goto L32
    L24:
        if (r4.summarizedOpen == false) goto L26;
        r7 = r4.summaryText;
        goto L27
    L18:
        if (r4.voiceTranscriptionOpen == false) goto L20;
        r6 = r4.translatedVoiceTranscription;
        goto L22
    L20:
        r6 = r4.translatedText;
    L14:
        r03 = false;
        goto L15
    L5:
        if (LocaleUtils.parseCustomEmojis(r0.message, r0.entities) == false) goto L8;
        if (r12 == true) goto L8;
        r12 = true;
        goto L8
    }
}
