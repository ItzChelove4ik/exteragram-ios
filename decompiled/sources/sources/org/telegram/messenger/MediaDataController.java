package org.telegram.messenger;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.URLSpan;
import android.util.Pair;
import android.util.SparseArray;
import android.widget.FrameLayout;
import androidx.collection.LongSparseArray;
import androidx.core.content.ContextCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.content.pm.ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0;
import androidx.core.content.pm.ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1;
import androidx.core.graphics.drawable.IconCompat;
import com.android.billingclient.api.ProductDetails;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.exteragram.messenger.utils.ui.UIUtil;
import j$.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationBadge;
import org.telegram.messenger.Timer;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.ringtone.RingtoneDataStore;
import org.telegram.messenger.ringtone.RingtoneUploader;
import org.telegram.messenger.utils.EphemeralMessagesHelper;
import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.tgnet.tl.TL_ephemeral;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.EmojiThemes;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.ChatThemeBottomSheet;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.FormattedDateSpan;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.StickerSetBulletinLayout;
import org.telegram.ui.Components.StickersArchiveAlert;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.URLSpanReplacement;
import org.telegram.ui.Components.URLSpanUserMention;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.StoriesStorage;

/* loaded from: classes.dex */
public class MediaDataController extends BaseController {
    public static final String ATTACH_MENU_BOT_ANIMATED_ICON_KEY = "android_animated";
    public static final String ATTACH_MENU_BOT_ANIMATED_ICON_KEY_2 = "android_active_animated";
    public static final String ATTACH_MENU_BOT_COLOR_DARK_ICON = "dark_icon";
    public static final String ATTACH_MENU_BOT_COLOR_DARK_TEXT = "dark_text";
    public static final String ATTACH_MENU_BOT_COLOR_LIGHT_ICON = "light_icon";
    public static final String ATTACH_MENU_BOT_COLOR_LIGHT_TEXT = "light_text";
    public static final String ATTACH_MENU_BOT_PLACEHOLDER_STATIC_KEY = "placeholder_static";
    public static final String ATTACH_MENU_BOT_SIDE_MENU = "android_side_menu_static";
    public static final String ATTACH_MENU_BOT_SIDE_MENU_ICON_KEY = "android_side_menu_static";
    public static final String ATTACH_MENU_BOT_STATIC_ICON_KEY = "default_static";
    private static Pattern BOLD_PATTERN = null;
    private static Pattern ITALIC_PATTERN = null;
    private static volatile MediaDataController[] Instance = null;
    private static final int MAX_LEGACY_STYLE_ENTITIES_COUNT = 100;
    private static final int MAX_LEGACY_STYLE_PROCESSING_STEPS = 10000;
    private static final int MAX_LEGACY_STYLE_RUNS_COUNT = 100;
    public static final int MAX_LINKS_COUNT = 250;
    private static final int MAX_STYLE_ENTITIES_COUNT = 1000;
    public static final int MAX_STYLE_RUNS_COUNT = 1000;
    public static final int MEDIA_AUDIO = 2;
    public static final int MEDIA_FILE = 1;
    public static final int MEDIA_GIF = 5;
    public static final int MEDIA_MUSIC = 4;
    public static final int MEDIA_PHOTOS_ONLY = 6;
    public static final int MEDIA_PHOTOVIDEO = 0;
    public static final int MEDIA_POLL = 8;
    public static final int MEDIA_TYPES_COUNT = 9;
    public static final int MEDIA_URL = 3;
    public static final int MEDIA_VIDEOS_ONLY = 7;
    public static String SHORTCUT_CATEGORY = null;
    public static int SHORTCUT_TYPE_ATTACHED_BOT = 0;
    public static int SHORTCUT_TYPE_USER_OR_CHAT = 0;
    private static Pattern SPOILER_PATTERN = null;
    private static Pattern STRIKE_PATTERN = null;
    private static final int TOP_PEER_TYPE_BOT_GUEST = 3;
    private static final int TOP_PEER_TYPE_BOT_INLINE = 1;
    public static final int TYPE_EMOJI = 4;
    public static final int TYPE_EMOJIPACKS = 5;
    public static final int TYPE_FAVE = 2;
    public static final int TYPE_FEATURED = 3;
    public static final int TYPE_FEATURED_EMOJIPACKS = 6;
    public static final int TYPE_GREETINGS = 3;
    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_MASK = 1;
    public static final int TYPE_PREMIUM_STICKERS = 7;
    private static RectF bitmapRect;
    private static Comparator<TLRPC.MessageEntity> entityComparator;
    private static Paint erasePaint;
    private static final Object[] lockObjects = null;
    private static Paint roundPaint;
    private static Path roundPath;
    private HashMap<String, ArrayList<TLRPC.Document>> allStickers;
    private HashMap<String, ArrayList<TLRPC.Document>> allStickersFeatured;
    private int[] archivedStickersCount;
    private TLRPC.TL_attachMenuBots attachMenuBots;
    private LongSparseArray<ArrayList<TLRPC.Message>> botDialogKeyboards;
    private HashMap<String, TL_bots.BotInfo> botInfos;
    private HashMap<MessagesStorage.TopicKey, TLRPC.Message> botKeyboards;
    private LongSparseArray<MessagesStorage.TopicKey> botKeyboardsByMids;
    private boolean cleanedupStickerSetCache;
    private HashMap<String, Boolean> currentFetchingEmoji;
    public final ArrayList<ChatThemeBottomSheet.ChatThemeItem> defaultEmojiThemes;
    private ArrayList<MessageObject> deletedFromResultMessages;
    private LongSparseArray<String> diceEmojiStickerSetsById;
    private HashMap<String, TLRPC.TL_messages_stickerSet> diceStickerSetsByEmoji;
    private String doubleTapReaction;
    private LongSparseArray<LongSparseArray<TLRPC.Message>> draftMessages;
    private SharedPreferences draftPreferences;
    public LongSparseArray<DraftVoice> draftVoices;
    private boolean draftVoicesLoaded;
    private LongSparseArray<LongSparseArray<TLRPC.DraftMessage>> drafts;
    private LongSparseArray<Integer> draftsFolderIds;
    private ArrayList<TLRPC.EmojiStatus>[] emojiStatuses;
    private Long[] emojiStatusesFetchDate;
    private boolean[] emojiStatusesFetching;
    private boolean[] emojiStatusesFromCacheFetched;
    private long[] emojiStatusesHash;
    private List<TLRPC.TL_availableReaction> enabledReactionsList;
    private ArrayList<TLRPC.StickerSetCovered>[] featuredStickerSets;
    private LongSparseArray<TLRPC.StickerSetCovered>[] featuredStickerSetsById;
    private boolean[] featuredStickersLoaded;
    private HashSet<String> fetchedEmoji;
    private int[] filteredMessagesSearchCount;
    private TLRPC.Document greetingsSticker;
    public TLRPC.TL_emojiList groupAvatarConstructorDefault;
    private LongSparseArray<TLRPC.TL_messages_stickerSet> groupStickerSets;
    public ArrayList<TLRPC.TL_topPeer> guestBots;
    public ArrayList<TLRPC.TL_topPeer> hints;
    private boolean inTransaction;
    public ArrayList<TLRPC.TL_topPeer> inlineBots;
    private ArrayList<Long> installedForceStickerSetsById;
    private LongSparseArray<TLRPC.TL_messages_stickerSet> installedStickerSetsById;
    private boolean isLoadingMenuBots;
    private boolean isLoadingPremiumPromo;
    private boolean isLoadingReactions;
    private long lastDialogId;
    private int lastGuid;
    private long lastMergeDialogId;
    private ReactionsLayoutInBubble.VisibleReaction lastReaction;
    private long lastReplyMessageId;
    private int lastReqId;
    private int lastReturnedNum;
    private TLRPC.Chat lastSearchChat;
    private String lastSearchQuery;
    private TLRPC.User lastSearchUser;
    private int[] loadDate;
    private int[] loadFeaturedDate;
    private long[] loadFeaturedHash;
    public boolean loadFeaturedPremium;
    private long[] loadHash;
    boolean loaded;
    private boolean loadedPredirectedSearchLocal;
    boolean loadedRecentReactions;
    boolean loadedSavedReactions;
    boolean loading;
    private boolean loadingDefaultTopicIcons;
    private HashSet<String> loadingDiceStickerSets;
    private boolean loadingDrafts;
    private boolean[] loadingFeaturedStickers;
    private boolean loadingGenericAnimations;
    private boolean loadingMoreSearchMessages;
    private LongSparseArray<Boolean> loadingPinnedMessages;
    private boolean loadingPremiumGiftStickers;
    private boolean loadingPremiumTonStickers;
    private boolean loadingRecentGifs;
    boolean loadingRecentReactions;
    private boolean[] loadingRecentStickers;
    boolean loadingSavedReactions;
    private boolean loadingSearchLocal;
    private final HashMap<SearchStickersKey, Integer> loadingSearchStickersKeys;
    private final HashMap<String, ArrayList<Utilities.Callback2<Boolean, TLRPC.TL_messages_stickerSet>>> loadingStickerSets;
    private final HashSet<String> loadingStickerSetsKeys;
    private boolean[] loadingStickers;
    private int menuBotsUpdateDate;
    private long menuBotsUpdateHash;
    private boolean menuBotsUpdatedLocal;
    private int mergeReqId;
    private int messagesLocalSearchCount;
    private int[] messagesSearchCount;
    private boolean[] messagesSearchEndReached;
    public final ArrayList<TLRPC.Document> premiumPreviewStickers;
    private TLRPC.TL_help_premiumPromo premiumPromo;
    private int premiumPromoUpdateDate;
    boolean previewStickersLoading;
    public TLRPC.TL_emojiList profileAvatarConstructorDefault;
    private boolean reactionsCacheGenerated;
    private List<TLRPC.TL_availableReaction> reactionsList;
    private HashMap<String, TLRPC.TL_availableReaction> reactionsMap;
    private int reactionsUpdateDate;
    private int reactionsUpdateHash;
    private ArrayList<Long>[] readingStickerSets;
    private ArrayList<TLRPC.Document> recentGifs;
    private boolean recentGifsLoaded;
    ArrayList<TLRPC.Reaction> recentReactions;
    private ArrayList<TLRPC.Document>[] recentStickers;
    private boolean[] recentStickersLoaded;
    private LongSparseArray<Runnable> removingStickerSetsUndos;
    public TLRPC.TL_emojiList replyIconsDefault;
    private int reqId;
    public TLRPC.TL_emojiList restrictedStatusEmojis;
    public final RingtoneDataStore ringtoneDataStore;
    public HashMap<String, RingtoneUploader> ringtoneUploaderHashMap;
    ArrayList<TLRPC.Reaction> savedReactions;
    private Runnable[] scheduledLoadStickers;
    public ArrayList<MessageObject> searchLocalResultMessages;
    public ArrayList<MessageObject> searchResultMessages;
    public ArrayList<MessageObject> searchServerResultMessages;
    private SparseArray<MessageObject>[] searchServerResultMessagesMap;
    private final android.util.LruCache<SearchStickersKey, SearchStickersResult> searchStickerResults;
    public final HashMap<String, Utilities.Callback<Boolean>> shortcutCallbacks;
    private TLRPC.TL_messages_stickerSet stickerSetDefaultChannelStatuses;
    private TLRPC.TL_messages_stickerSet stickerSetDefaultStatuses;
    private ArrayList<TLRPC.TL_messages_stickerSet>[] stickerSets;
    private LongSparseArray<TLRPC.TL_messages_stickerSet> stickerSetsById;
    private ConcurrentHashMap<String, TLRPC.TL_messages_stickerSet> stickerSetsByName;
    private LongSparseArray<String> stickersByEmoji;
    private LongSparseArray<TLRPC.Document>[] stickersByIds;
    private boolean[] stickersLoaded;
    ArrayList<TLRPC.Reaction> topReactions;
    private boolean triedLoadingEmojipacks;
    private ArrayList<Long> uninstalledForceStickerSetsById;
    private ArrayList<Long>[] unreadStickerSets;
    private HashMap<String, ArrayList<TLRPC.Message>> verifyingMessages;
    public ArrayList<TLRPC.TL_topPeer> webapps;

    /* renamed from: org.telegram.messenger.MediaDataController$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ MediaDataController this$0;
        final /* synthetic */ int val$classGuid;
        final /* synthetic */ int val$count;
        final /* synthetic */ int val$fromCache;
        final /* synthetic */ boolean val$isChannel;
        final /* synthetic */ int val$max_id;
        final /* synthetic */ int val$min_id;
        final /* synthetic */ int val$requestIndex;
        final /* synthetic */ ReactionsLayoutInBubble.VisibleReaction val$tag;
        final /* synthetic */ long val$topicId;
        final /* synthetic */ int val$type;
        final /* synthetic */ long val$uid;

        public static /* synthetic */ void $r8$lambda$2WzmZ4yiCSrqx0bPALAQLYduwos(AnonymousClass1 r0, Runnable r1, int r2) {
            r0.lambda$run$0(r1, r2);
        }

        public AnonymousClass1(MediaDataController r1, int r2, long r3, int r5, long r6, int r8, ReactionsLayoutInBubble.VisibleReaction r9, int r10, int r11, int r12, boolean r13, int r14) {
            this.this$0 = r1;
            this.val$count = r2;
            this.val$uid = r3;
            this.val$min_id = r5;
            this.val$topicId = r6;
            this.val$type = r8;
            this.val$tag = r9;
            this.val$max_id = r10;
            this.val$classGuid = r11;
            this.val$fromCache = r12;
            this.val$isChannel = r13;
            this.val$requestIndex = r14;
        }

        private /* synthetic */ void lambda$run$0(Runnable r1, int r2) {
            this.this$0.getMessagesStorage().completeTaskForGuid(r1, r2);
        }

        @Override // java.lang.Runnable
        public void run() {
            long r2 = this.this$0.getUserConfig().getClientUserId();
            TLRPC.TL_messages_messages r5 = new TLRPC.TL_messages_messages();
            boolean r17 = false;
            ArrayList<Long> r4 = new ArrayList();     // Catch: Throwable -> L10 Exception -> L12
            ArrayList r6 = new ArrayList();     // Catch: Throwable -> L10 Exception -> L12
            int r7 = this.val$count + 1;     // Catch: Throwable -> L10 Exception -> L12
            SQLiteDatabase r9 = this.this$0.getMessagesStorage().getDatabase();     // Catch: Throwable -> L10 Exception -> L12
            if (DialogObject.isEncryptedDialog(this.val$uid) == false) goto L6;
            SQLiteDatabase r12 = r9;
            long r19 = 0;
            long r10 = this.val$topicId;     // Catch: Throwable -> L10 Exception -> L12
            int r8 = this.val$max_id;
            if (r10 == 0) goto L128;
            if (r8 == 0) goto L125;
            SQLiteCursor r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid, r.random_id FROM media_topics as m LEFT JOIN randoms_v2 as r ON r.mid = m.mid WHERE m.uid = %d AND m.topic_id = %d AND m.mid > %d AND type = %d ORDER BY m.mid ASC LIMIT %d", new Object[]{Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(this.val$max_id), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
        L123:
            boolean r72 = false;
            boolean r82 = true;
        L135:
            if (this.val$tag == null) goto L137;
            HashSet r102 = new HashSet();     // Catch: Throwable -> L10 Exception -> L12
        L139:
            if (r0.next() == false) goto L159;
            NativeByteBuffer r13 = r0.byteBufferValue(0);     // Catch: Throwable -> L10 Exception -> L12
            if (r13 == null) goto L157;
            TLRPC.Message r15 = TLRPC.Message.TLdeserialize(r13, r13.readInt32(false), false);     // Catch: Throwable -> L10 Exception -> L12
            r15.readAttachPath(r13, r2);     // Catch: Throwable -> L10 Exception -> L12
            r13.reuse();     // Catch: Throwable -> L10 Exception -> L12
            r15.id = r0.intValue(1);     // Catch: Throwable -> L10 Exception -> L12
            SQLiteDatabase r18 = r12;
            long r11 = this.val$uid;     // Catch: Throwable -> L10 Exception -> L12
            r15.dialog_id = r11;     // Catch: Throwable -> L10 Exception -> L12
            if (DialogObject.isEncryptedDialog(r11) == false) goto L146;
            r15.random_id = r0.longValue(2);     // Catch: Throwable -> L10 Exception -> L12
        L146:
            long r132 = r15.grouped_id;     // Catch: Throwable -> L10 Exception -> L12
            if (r132 == r19) goto L150;
            if (r102 == null) goto L150;
            r102.add(Long.valueOf(r132));     // Catch: Throwable -> L10 Exception -> L12
        L150:
            ArrayList<TLRPC.Message> r133 = r5.messages;
            if (r72 == true) goto L153;
            r133.add(r15);     // Catch: Throwable -> L10 Exception -> L12
        L156:
            MessagesStorage.addUsersAndChatsFromMessage(r15, r4, r6, null);     // Catch: Throwable -> L10 Exception -> L12
        L158:
            r12 = r18;
            goto L139
        L153:
            r133.add(0, r15);     // Catch: Throwable -> L10 Exception -> L12
            goto L156
        L157:
            r18 = r12;
            goto L158
        L159:
            SQLiteDatabase r182 = r12;
            r0.dispose();     // Catch: Throwable -> L10 Exception -> L12
            if (this.val$tag == null) goto L188;
            if (r102.isEmpty() == true) goto L188;
            Iterator r02 = r102.iterator();     // Catch: Throwable -> L10 Exception -> L12
        L165:
            if (r02.hasNext() == false) goto L188;
            Long r103 = (Long) r02.next();     // Catch: Throwable -> L10 Exception -> L12
            long r134 = r103.longValue();     // Catch: Throwable -> L10 Exception -> L12
            int r112 = 0;
        L168:
            if (r112 >= r5.messages.size()) goto L173;
            long r192 = r134;
            if (r5.messages.get(r112).grouped_id == r192) goto L174;
            r112 = r112 + 1;     // Catch: Throwable -> L10 Exception -> L12
            r134 = r192;
        L174:
            if (r112 < 0) goto L165;
            SQLiteDatabase r14 = r182;
            SQLiteCursor r104 = r14.queryFinalized("SELECT data, mid FROM messages_v2 WHERE uid = ? AND group_id = ? ORDER BY mid DESC", new Object[]{Long.valueOf(this.val$uid), r103});     // Catch: Throwable -> L10 Exception -> L12
            ArrayList r122 = new ArrayList();     // Catch: Throwable -> L10 Exception -> L12
        L178:
            if (r104.next() == false) goto L183;
            int r152 = r104.intValue(1);     // Catch: Throwable -> L10 Exception -> L12
            NativeByteBuffer r135 = r104.byteBufferValue(0);     // Catch: Throwable -> L10 Exception -> L12
            if (r135 == null) goto L178;
            boolean r183 = r72;
            TLRPC.Message r73 = TLRPC.Message.TLdeserialize(r135, r135.readInt32(false), false);     // Catch: Throwable -> L10 Exception -> L12
            r73.readAttachPath(r135, r2);     // Catch: Throwable -> L10 Exception -> L12
            r135.reuse();     // Catch: Throwable -> L10 Exception -> L12
            r73.id = r152;     // Catch: Throwable -> L10 Exception -> L12
            r73.dialog_id = this.val$uid;     // Catch: Throwable -> L10 Exception -> L12
            r122.add(r73);     // Catch: Throwable -> L10 Exception -> L12
            MessagesStorage.addUsersAndChatsFromMessage(r73, r4, r6, null);     // Catch: Throwable -> L10 Exception -> L12
            r104 = r104;
            r72 = r183;
            goto L178
        L183:
            boolean r184 = r72;
            SQLiteCursor r136 = r104;
            if (r184 == false) goto L186;
            Collections.reverse(r122);     // Catch: Throwable -> L10 Exception -> L12
        L186:
            r5.messages.remove(r112);     // Catch: Throwable -> L10 Exception -> L12
            r5.messages.addAll(r112, r122);     // Catch: Throwable -> L10 Exception -> L12
            r136.dispose();     // Catch: Throwable -> L10 Exception -> L12
            r72 = r184;
            r182 = r14;
            goto L165
        L173:
            r112 = -1;
        L188:
            if (r4.isEmpty() == true) goto L191;
            this.this$0.getMessagesStorage().getUsersInternal(r4, r5.users);     // Catch: Throwable -> L10 Exception -> L12
        L191:
            if (r6.isEmpty() == true) goto L194;
            this.this$0.getMessagesStorage().getChatsInternal(TextUtils.join(",", r6), r5.chats);     // Catch: Throwable -> L10 Exception -> L12
        L194:
            if (r5.messages.size() <= this.val$count) goto L199;
            if (this.val$min_id != 0) goto L199;
            ArrayList<TLRPC.Message> r03 = r5.messages;     // Catch: Throwable -> L10 Exception -> L12
            r03.remove(r03.size() - 1);     // Catch: Throwable -> L10 Exception -> L12
        L202:
            MediaDataController$1$$ExternalSyntheticLambda0 r22 = new MediaDataController$1$$ExternalSyntheticLambda0(this, this, this.val$classGuid);
        L203:
            AndroidUtilities.runOnUIThread(r22);
            MediaDataController.m4464$$Nest$mprocessLoadedMedia(this.this$0, r5, this.val$uid, this.val$count, this.val$max_id, this.val$min_id, this.val$type, this.val$topicId, this.val$fromCache, this.val$classGuid, this.val$isChannel, r17, this.val$requestIndex);
            return;
        L199:
            if (this.val$min_id == 0) goto L201;
            r17 = false;
            goto L202
        L201:
            r17 = r82;
            goto L202
        L137:
            r102 = null;
            goto L139
        L125:
            if (this.val$min_id == 0) goto L127;
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid, r.random_id FROM media_topics as m LEFT JOIN randoms_v2 as r ON r.mid = m.mid WHERE m.uid = %d AND m.topic_id = %d AND m.mid < %d AND type = %d ORDER BY m.mid DESC LIMIT %d", new Object[]{Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(this.val$min_id), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L123
        L127:
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid, r.random_id FROM media_topics as m LEFT JOIN randoms_v2 as r ON r.mid = m.mid WHERE m.uid = %d AND m.topic_id = %d AND type = %d ORDER BY m.mid ASC LIMIT %d", new Object[]{Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L123
        L128:
            if (r8 == 0) goto L131;
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid, r.random_id FROM media_v4 as m LEFT JOIN randoms_v2 as r ON r.mid = m.mid WHERE m.uid = %d AND m.mid > %d AND type = %d ORDER BY m.mid ASC LIMIT %d", new Object[]{Long.valueOf(this.val$uid), Integer.valueOf(this.val$max_id), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L123
        L131:
            if (this.val$min_id == 0) goto L133;
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid, r.random_id FROM media_v4 as m LEFT JOIN randoms_v2 as r ON r.mid = m.mid WHERE m.uid = %d AND m.mid < %d AND type = %d ORDER BY m.mid DESC LIMIT %d", new Object[]{Long.valueOf(this.val$uid), Integer.valueOf(this.val$min_id), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L123
        L133:
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid, r.random_id FROM media_v4 as m LEFT JOIN randoms_v2 as r ON r.mid = m.mid WHERE m.uid = %d AND type = %d ORDER BY m.mid ASC LIMIT %d", new Object[]{Long.valueOf(this.val$uid), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L123
        L6:
            if (this.val$min_id == 0) goto L8;
            r12 = r9;
            r19 = 0;
            boolean r105 = false;
        L43:
            ReactionsLayoutInBubble.VisibleReaction r83 = this.val$tag;     // Catch: Throwable -> L10 Exception -> L12
            if (r83 == null) goto L52;
            boolean r84 = TextUtils.isEmpty(r83.emojicon);     // Catch: Throwable -> L10 Exception -> L12
            ReactionsLayoutInBubble.VisibleReaction r113 = this.val$tag;
            if (r84 == true) goto L49;
            boolean r153 = r105;
            long r92 = r113.emojicon.hashCode();     // Catch: Throwable -> L10 Exception -> L12
        L51:
            String r222 = "t.tag = " + r92 + " AND";     // Catch: Throwable -> L10 Exception -> L12
            String r21 = "INNER JOIN tag_message_id t ON m.mid = t.mid";
        L54:
            if (this.val$max_id != 0) goto L56;
            int r04 = this.val$min_id;     // Catch: Throwable -> L10 Exception -> L12
            long r93 = this.val$topicId;
            if (r04 == 0) goto L100;
            if (r93 == r19) goto L81;
            SQLiteCursor r05 = r12.queryFinalized(String.format(Locale.US, "SELECT start, end FROM media_holes_topics WHERE uid = %d AND topic_id = %d AND type = %d AND end >= %d ORDER BY end ASC LIMIT 1", new Object[]{Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(this.val$type), Integer.valueOf(this.val$min_id)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            int r94 = 0;
        L83:
            if (r05.next() == false) goto L85;
            int r85 = r05.intValue(r94);     // Catch: Throwable -> L10 Exception -> L12
            r05.intValue(1);     // Catch: Throwable -> L10 Exception -> L12
        L86:
            r05.dispose();     // Catch: Throwable -> L10 Exception -> L12
            if (this.val$topicId == r19) goto L95;
            if (r85 <= 1) goto L92;
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid FROM media_topics m %s WHERE %s m.uid = %d AND m.topic_id = %d AND m.mid > 0 AND m.mid >= %d AND m.mid <= %d AND m.type = %d ORDER BY m.date ASC, m.mid ASC LIMIT %d", new Object[]{r21, r222, Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(this.val$min_id), Integer.valueOf(r85), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
        L91:
            boolean r137 = r153;
        L98:
            r72 = true;
        L117:
            r82 = r137;
            goto L135
        L92:
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid FROM media_topics m %s WHERE %s m.uid = %d AND m.topic_id = %d AND m.mid > 0 AND m.mid >= %d AND m.type = %d ORDER BY m.date ASC, m.mid ASC LIMIT %d", new Object[]{r21, r222, Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(this.val$min_id), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
        L93:
            r137 = true;
            goto L98
        L95:
            if (r85 <= 1) goto L97;
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid FROM media_v4 m %s WHERE %s m.uid = %d AND m.mid > 0 AND m.mid >= %d AND m.mid <= %d AND m.type = %d ORDER BY m.date ASC, m.mid ASC LIMIT %d", new Object[]{r21, r222, Long.valueOf(this.val$uid), Integer.valueOf(this.val$min_id), Integer.valueOf(r85), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L91
        L97:
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid FROM media_v4 m %s WHERE %s m.uid = %d AND m.mid > 0 AND m.mid >= %d AND m.type = %d ORDER BY m.date ASC, m.mid ASC LIMIT %d", new Object[]{r21, r222, Long.valueOf(this.val$uid), Integer.valueOf(this.val$min_id), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L93
        L85:
            r85 = 0;
            goto L86
        L81:
            r94 = 0;
            r05 = r12.queryFinalized(String.format(Locale.US, "SELECT start, end FROM media_holes_v2 WHERE uid = %d AND type = %d AND end >= %d ORDER BY end ASC LIMIT 1", new Object[]{Long.valueOf(this.val$uid), Integer.valueOf(this.val$type), Integer.valueOf(this.val$min_id)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L83
        L100:
            if (r93 == r19) goto L102;
            SQLiteCursor r06 = r12.queryFinalized(String.format(Locale.US, "SELECT max(end) FROM media_holes_topics WHERE uid = %d AND topic_id = %d AND type = %d", new Object[]{Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(this.val$type)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            int r95 = 0;
        L104:
            if (r06.next() == false) goto L106;
            int r86 = r06.intValue(r95);     // Catch: Throwable -> L10 Exception -> L12
        L107:
            r06.dispose();     // Catch: Throwable -> L10 Exception -> L12
            if (this.val$topicId == r19) goto L114;
            if (r86 <= 1) goto L112;
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid FROM media_topics m %s WHERE %s m.uid = %d AND m.topic_id = %d AND m.mid >= %d AND m.type = %d ORDER BY m.date DESC, m.mid DESC LIMIT %d", new Object[]{r21, r222, Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(r86), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
        L74:
            r137 = r153;
            r72 = false;
            goto L117
        L112:
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid FROM media_topics m %s WHERE %s m.uid = %d AND m.topic_id = %d AND m.mid > 0 AND m.type = %d ORDER BY m.date DESC, m.mid DESC LIMIT %d", new Object[]{r21, r222, Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L74
        L114:
            if (r86 <= 1) goto L116;
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid FROM media_v4 m %s WHERE %s m.uid = %d AND m.mid >= %d AND m.type = %d ORDER BY m.date DESC, m.mid DESC LIMIT %d", new Object[]{r21, r222, Long.valueOf(this.val$uid), Integer.valueOf(r86), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L74
        L116:
            r12 = r12;
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid FROM media_v4 m %s WHERE %s m.uid = %d AND m.mid > 0 AND m.type = %d ORDER BY m.date DESC, m.mid DESC LIMIT %d", new Object[]{r21, r222, Long.valueOf(this.val$uid), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L74
        L106:
            r86 = 0;
            goto L107
        L102:
            r95 = 0;
            r06 = r12.queryFinalized(String.format(Locale.US, "SELECT max(end) FROM media_holes_v2 WHERE uid = %d AND type = %d", new Object[]{Long.valueOf(this.val$uid), Integer.valueOf(this.val$type)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L104
        L56:
            if (this.val$topicId == r19) goto L58;
            SQLiteCursor r07 = r12.queryFinalized(String.format(Locale.US, "SELECT start, end FROM media_holes_topics WHERE uid = %d AND topic_id = %d AND type = %d AND start <= %d ORDER BY end DESC LIMIT 1", new Object[]{Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(this.val$type), Integer.valueOf(this.val$max_id)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            int r96 = 0;
        L60:
            if (r07.next() == false) goto L62;
            r07.intValue(r96);     // Catch: Throwable -> L10 Exception -> L12
            int r87 = r07.intValue(1);     // Catch: Throwable -> L10 Exception -> L12
        L63:
            r07.dispose();     // Catch: Throwable -> L10 Exception -> L12
            if (this.val$topicId == r19) goto L71;
            if (r87 <= 1) goto L69;
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid FROM media_topics m %s WHERE %s m.uid = %d AND m.topic_id = %d AND m.mid > 0 AND m.mid < %d AND m.mid >= %d AND m.type = %d ORDER BY m.date DESC, m.mid DESC LIMIT %d", new Object[]{r21, r222, Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(this.val$max_id), Integer.valueOf(r87), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
        L68:
            r153 = false;
            goto L74
        L69:
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid FROM media_topics m %s WHERE %s m.uid = %d AND m.topic_id = %d AND m.mid > 0 AND m.mid < %d AND m.type = %d ORDER BY m.date DESC, m.mid DESC LIMIT %d", new Object[]{r21, r222, Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(this.val$max_id), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L74
        L71:
            if (r87 <= 1) goto L73;
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid FROM media_v4 m %s WHERE %s m.uid = %d AND m.mid > 0 AND m.mid < %d AND m.mid >= %d AND m.type = %d ORDER BY m.date DESC, m.mid DESC LIMIT %d", new Object[]{r21, r222, Long.valueOf(this.val$uid), Integer.valueOf(this.val$max_id), Integer.valueOf(r87), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L68
        L73:
            r0 = r12.queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid FROM media_v4 m %s WHERE %s m.uid = %d AND m.mid > 0 AND m.mid < %d AND m.type = %d ORDER BY m.date DESC, m.mid DESC LIMIT %d", new Object[]{r21, r222, Long.valueOf(this.val$uid), Integer.valueOf(this.val$max_id), Integer.valueOf(this.val$type), Integer.valueOf(r7)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L74
        L62:
            r87 = 0;
            goto L63
        L58:
            r96 = 0;
            r07 = r12.queryFinalized(String.format(Locale.US, "SELECT start, end FROM media_holes_v2 WHERE uid = %d AND type = %d AND start <= %d ORDER BY end DESC LIMIT 1", new Object[]{Long.valueOf(this.val$uid), Integer.valueOf(this.val$type), Integer.valueOf(this.val$max_id)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L60
        L49:
            r153 = r105;
            r92 = r113.documentId;     // Catch: Throwable -> L10 Exception -> L12
            goto L51
        L52:
            r153 = r105;
            r21 = _UrlKt.FRAGMENT_ENCODE_SET;
            r222 = r21;
            goto L54
        L8:
            if (this.val$topicId == 0) goto L14;
            r12 = r9;
            SQLiteCursor r88 = r12.queryFinalized(String.format(Locale.US, "SELECT start FROM media_holes_topics WHERE uid = %d AND topic_id = %d AND type = %d AND start IN (0, 1)", new Object[]{Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(this.val$type)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            int r97 = 0;
        L16:
            if (r88.next() == true) goto L18;
            r88.dispose();     // Catch: Throwable -> L10 Exception -> L12
            if (this.val$topicId == 0) goto L25;
            r12 = r12;
            r88 = r12.queryFinalized(String.format(Locale.US, "SELECT min(mid) FROM media_topics WHERE uid = %d AND topic_id = %d AND type = %d AND mid > 0", new Object[]{Long.valueOf(this.val$uid), Long.valueOf(this.val$topicId), Integer.valueOf(this.val$type)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            r19 = 0;
            int r98 = 0;
        L27:
            if (r88.next() == false) goto L40;
            int r106 = r88.intValue(r98);     // Catch: Throwable -> L10 Exception -> L12
            if (r106 == 0) goto L40;
            if (this.val$topicId == r19) goto L34;
            SQLitePreparedStatement r138 = r12.executeFast("REPLACE INTO media_holes_topics VALUES(?, ?, ?, ?, ?)");     // Catch: Throwable -> L10 Exception -> L12
        L33:
            SQLitePreparedStatement r154 = r138;
            r154.requery();     // Catch: Throwable -> L10 Exception -> L12
            r154.bindLong(1, this.val$uid);     // Catch: Throwable -> L10 Exception -> L12
            long r139 = this.val$topicId;     // Catch: Throwable -> L10 Exception -> L12
            if (r139 == r19) goto L38;
            r154.bindLong(2, r139);     // Catch: Throwable -> L10 Exception -> L12
            int r99 = 3;
        L39:
            r154.bindInteger(r99, this.val$type);     // Catch: Throwable -> L10 Exception -> L12
            r154.bindInteger(r99 + 1, 0);     // Catch: Throwable -> L10 Exception -> L12
            r154.bindInteger(r99 + 2, r106);     // Catch: Throwable -> L10 Exception -> L12
            r154.step();     // Catch: Throwable -> L10 Exception -> L12
            r154.dispose();     // Catch: Throwable -> L10 Exception -> L12
            goto L40
        L38:
            r99 = 2;
            goto L39
        L34:
            r138 = r12.executeFast("REPLACE INTO media_holes_v2 VALUES(?, ?, ?, ?)");     // Catch: Throwable -> L10 Exception -> L12
        L40:
            r105 = false;
        L41:
            r88.dispose();     // Catch: Throwable -> L10 Exception -> L12
            goto L43
        L25:
            r19 = 0;
            r98 = 0;
            r88 = r12.queryFinalized(String.format(Locale.US, "SELECT min(mid) FROM media_v4 WHERE uid = %d AND type = %d AND mid > 0", new Object[]{Long.valueOf(this.val$uid), Integer.valueOf(this.val$type)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
            goto L27
        L18:
            if (r88.intValue(r97) != 1) goto L20;
            r105 = true;
        L21:
            r19 = 0;
            goto L41
        L20:
            r105 = false;
            goto L21
        L14:
            r12 = r9;
            r97 = 0;
            r88 = r12.queryFinalized(String.format(Locale.US, "SELECT start FROM media_holes_v2 WHERE uid = %d AND type = %d AND start IN (0, 1)", new Object[]{Long.valueOf(this.val$uid), Integer.valueOf(this.val$type)}), new Object[0]);     // Catch: Throwable -> L10 Exception -> L12
        L12:
            e = move-exception;
            r5.messages.clear();     // Catch: Throwable -> L10
            r5.chats.clear();     // Catch: Throwable -> L10
            r5.users.clear();     // Catch: Throwable -> L10
            FileLog.e(e);     // Catch: Throwable -> L10
            r22 = new MediaDataController$1$$ExternalSyntheticLambda0(this, this, this.val$classGuid);
        L10:
            th = move-exception;
            AndroidUtilities.runOnUIThread(new MediaDataController$1$$ExternalSyntheticLambda0(this, this, this.val$classGuid));
            MediaDataController.m4464$$Nest$mprocessLoadedMedia(this.this$0, r5, this.val$uid, this.val$count, this.val$max_id, this.val$min_id, this.val$type, this.val$topicId, this.val$fromCache, this.val$classGuid, this.val$isChannel, false, this.val$requestIndex);
            throw th;
        }
    }

    /* renamed from: org.telegram.messenger.MediaDataController$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ MediaDataController this$0;
        final /* synthetic */ ArrayList val$previewItems;

        public static /* synthetic */ void $r8$lambda$y4qz5i3Q0y_kLcfqVYOxpvObckU(AnonymousClass2 r0, ArrayList r1) {
            r0.lambda$run$0(r1);
        }

        public AnonymousClass2(MediaDataController r1, ArrayList r2) {
            this.this$0 = r1;
            this.val$previewItems = r2;
        }

        private /* synthetic */ void lambda$run$0(ArrayList r2) {
            this.this$0.defaultEmojiThemes.clear();
            this.this$0.defaultEmojiThemes.addAll(r2);
        }

        @Override // java.lang.Runnable
        public void run() {
            int r1 = 0;
        L3:
            int r2 = this.val$previewItems.size();
            ArrayList r3 = this.val$previewItems;
            if (r1 >= r2) goto L11;
            if (r3.get(r1) == null) goto L10;
            if (((ChatThemeBottomSheet.ChatThemeItem) this.val$previewItems.get(r1)).chatTheme == null) goto L10;
            ((ChatThemeBottomSheet.ChatThemeItem) this.val$previewItems.get(r1)).chatTheme.loadPreviewColors(0);
        L10:
            r1 = r1 + 1;
            goto L3
        L11:
            AndroidUtilities.runOnUIThread(new MediaDataController$2$$ExternalSyntheticLambda0(this, r3));
        }
    }

    /* renamed from: org.telegram.messenger.MediaDataController$3, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ MediaDataController this$0;
        final /* synthetic */ int val$currentAccount;
        final /* synthetic */ ArrayList val$previewItems;

        public static /* synthetic */ void $r8$lambda$fYfj4gM3gHilz9HqZErvrb7mE_M(AnonymousClass3 r0, ArrayList r1) {
            r0.lambda$run$0(r1);
        }

        public AnonymousClass3(MediaDataController r1, ArrayList r2, int r3) {
            this.this$0 = r1;
            this.val$previewItems = r2;
            this.val$currentAccount = r3;
        }

        private /* synthetic */ void lambda$run$0(ArrayList r2) {
            this.this$0.defaultEmojiThemes.clear();
            this.this$0.defaultEmojiThemes.addAll(r2);
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.emojiPreviewThemesChanged, new Object[0]);
        }

        @Override // java.lang.Runnable
        public void run() {
            int r0 = 0;
        L3:
            int r1 = this.val$previewItems.size();
            ArrayList r2 = this.val$previewItems;
            if (r0 >= r1) goto L6;
            ((ChatThemeBottomSheet.ChatThemeItem) r2.get(r0)).chatTheme.loadPreviewColors(this.val$currentAccount);
            r0 = r0 + 1;
            goto L3
        L6:
            AndroidUtilities.runOnUIThread(new MediaDataController$3$$ExternalSyntheticLambda0(this, r2));
        }
    }

    /* renamed from: org.telegram.messenger.MediaDataController$4, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass4 implements RequestDelegate {
        final /* synthetic */ MediaDataController this$0;

        public AnonymousClass4(MediaDataController r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.tgnet.RequestDelegate
        public void run(TLObject r1, TLRPC.TL_error r2) {
        }
    }

    public static class DraftVoice {
        public long id;
        public float left;
        public boolean once;
        public String path;
        public short[] recordSamples;
        public long recordTimeCount;
        public float right;
        public long samplesCount;
        public int writedFrame;

        public DraftVoice() {
            this.left = 0.0f;
            this.right = 1.0f;
        }

        public static DraftVoice fromString(String r9) {
            if (r9 != null) goto L36;
            return null;
        L36:
        L18:
            e = move-exception;
            FileLog.e(e);
            return null;
        L6:
            if (r9.startsWith("@") == true) goto L8;
            return null;
        L8:
            boolean r3 = true;
            String[] r92 = r9.substring(1).split("\n");     // Catch: Exception -> L18
            if (r92.length >= 6) goto L11;
            return null;
        L11:
            DraftVoice r4 = new DraftVoice();     // Catch: Exception -> L18
            int r5 = 0;
            r4.path = r92[0];     // Catch: Exception -> L18
            r4.samplesCount = Long.parseLong(r92[1]);     // Catch: Exception -> L18
            r4.writedFrame = Integer.parseInt(r92[2]);     // Catch: Exception -> L18
            r4.recordTimeCount = Long.parseLong(r92[3]);     // Catch: Exception -> L18
            if (r92[4].contains(";") == false) goto L21;
            String[] r0 = r92[4].split(";");     // Catch: Exception -> L18
            if (Integer.parseInt(r0[0]) == 0) goto L16;
            boolean r7 = true;
        L17:
            r4.once = r7;     // Catch: Exception -> L18
            r4.left = Float.parseFloat(r0[1]);     // Catch: Exception -> L18
            r4.right = Float.parseFloat(r0[2]);     // Catch: Exception -> L18
        L25:
            int r02 = r92.length - 5;
            String[] r32 = new String[r02];     // Catch: Exception -> L18
            int r6 = 0;
        L26:
            if (r6 >= r02) goto L28;
            r32[r6] = r92[r6 + 5];     // Catch: Exception -> L18
            r6 = r6 + 1;     // Catch: Exception -> L18
            goto L26
        L28:
            String r93 = TextUtils.join("\n", r32);     // Catch: Exception -> L18
            r4.recordSamples = new short[r93.length()];     // Catch: Exception -> L18
        L29:
            short[] r03 = r4.recordSamples;     // Catch: Exception -> L18
            if (r5 >= r03.length) goto L33;
            r03[r5] = (short) r93.charAt(r5);     // Catch: Exception -> L18
            r5 = r5 + 1;
            goto L29
        L33:
            return r4;
        L16:
            r7 = false;
            goto L17
        L21:
            if (Integer.parseInt(r92[4]) != 0) goto L24;
            r3 = false;
        L24:
            r4.once = r3;     // Catch: Exception -> L18
            r4.left = 0.0f;     // Catch: Exception -> L18
            r4.right = 1.0f;     // Catch: Exception -> L18
            goto L25
        }

        public static DraftVoice of(MediaController r3, String r4, boolean r5, float r6, float r7) {
            if (r3.recordingAudio != null) goto L6;
            return null;
        L6:
            DraftVoice r0 = new DraftVoice();
            r0.path = r4;
            r0.samplesCount = r3.samplesCount;
            r0.writedFrame = r3.writtenFrame;
            r0.recordTimeCount = r3.recordTimeCount;
            r0.id = r3.recordingAudio.id;
            r0.recordSamples = r3.recordSamples;
            r0.once = r5;
            r0.left = r6;
            r0.right = r7;
            return r0;
        }

        public String toString() {
            char[] r0 = new char[this.recordSamples.length];
            int r1 = 0;
        L3:
            short[] r2 = this.recordSamples;
            if (r1 >= r2.length) goto L7;
            r0[r1] = (char) r2[r1];
            r1 = r1 + 1;
            goto L3
        L7:
            return "@" + this.path + "\n" + this.samplesCount + "\n" + this.writedFrame + "\n" + this.recordTimeCount + "\n" + (this.once ? 1 : 0) + ";" + this.left + ";" + this.right + "\n" + new String(r0);
        }
    }

    /* loaded from: classes5.dex */
    public static class KeywordResult {
        public String emoji;
        public String keyword;

        public KeywordResult() {
        }

        public KeywordResult(String r1, String r2) {
            this.emoji = r1;
            this.keyword = r2;
        }
    }

    /* loaded from: classes5.dex */
    public interface KeywordResultCallback {
        void run(ArrayList<KeywordResult> r1, String r2);
    }

    /* loaded from: classes5.dex */
    public static class SearchStickersKey {
        public final boolean emojis;
        public final String lang_code;
        public final String q;

        public SearchStickersKey(boolean r1, String r2, String r3) {
            this.emojis = r1;
            this.lang_code = r2;
            this.q = r3;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L17:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L17;
            SearchStickersKey r52 = (SearchStickersKey) r5;
            if (this.emojis != r52.emojis) goto L17;
            if (Objects.equals(this.lang_code, r52.lang_code) == false) goto L17;
            if (Objects.equals(this.q, r52.q) == false) goto L17;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Boolean.valueOf(this.emojis), this.lang_code, this.q});
        }
    }

    /* loaded from: classes5.dex */
    public static class SearchStickersResult {
        public final ArrayList<TLRPC.Document> documents;
        public Integer next_offset;

        private SearchStickersResult() {
            this.documents = new ArrayList();
        }

        public /* synthetic */ SearchStickersResult(MediaDataControllerIA r1) {
            this();
        }

        public void apply(TLRPC.TL_messages_foundStickers r3) {
            this.documents.addAll(r3.stickers);
            if ((r3.flags & 1) == 0) goto L5;
            Integer r32 = Integer.valueOf(r3.next_offset);
        L6:
            this.next_offset = r32;
            return;
        L5:
            r32 = null;
            goto L6
        }
    }

    /* renamed from: $r8$lambda$-N2vfhZkmBB38RNr_bl7-p-HvAI, reason: not valid java name */
    public static /* synthetic */ void m4370$r8$lambda$N2vfhZkmBB38RNr_bl7pHvAI(String r9, ArrayList r10, ArrayList r11, Utilities.Callback r12) {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        String r92 = r9.toLowerCase();
        int r3 = 0;
    L4:
        if (r3 >= r10.size()) goto L24;
        if (((TLRPC.TL_messages_stickerSet) r10.get(r3)).keywords == null) goto L23;
        ArrayList<TLRPC.TL_stickerKeyword> r4 = ((TLRPC.TL_messages_stickerSet) r10.get(r3)).keywords;
        int r5 = 0;
    L9:
        if (r5 >= r4.size()) goto L23;
        int r6 = 0;
    L12:
        if (r6 >= r4.get(r5).keyword.size()) goto L22;
        String r7 = r4.get(r5).keyword.get(r6);
        if (r92.equals(r7) == false) goto L17;
        r0.add(Long.valueOf(r4.get(r5).document_id));
    L21:
        r6 = r6 + 1;
        goto L12
    L17:
        if (r92.contains(r7) == false) goto L19;
    L20:
        r1.add(Long.valueOf(r4.get(r5).document_id));
        goto L21
    L19:
        if (r7.contains(r92) == false) goto L21;
    L22:
        r5 = r5 + 1;
    L23:
        r3 = r3 + 1;
        goto L4
    L24:
        int r102 = 0;
    L26:
        if (r102 >= r11.size()) goto L48;
        if ((r11.get(r102) instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L47;
        if (((TLRPC.TL_stickerSetFullCovered) r11.get(r102)).keywords == null) goto L47;
        ArrayList<TLRPC.TL_stickerKeyword> r32 = ((TLRPC.TL_stickerSetFullCovered) r11.get(r102)).keywords;
        int r42 = 0;
    L33:
        if (r42 >= r32.size()) goto L47;
        int r52 = 0;
    L36:
        if (r52 >= r32.get(r42).keyword.size()) goto L46;
        String r62 = r32.get(r42).keyword.get(r52);
        if (r92.equals(r62) == false) goto L41;
        r0.add(Long.valueOf(r32.get(r42).document_id));
    L45:
        r52 = r52 + 1;
        goto L36
    L41:
        if (r92.contains(r62) == false) goto L43;
    L44:
        r1.add(Long.valueOf(r32.get(r42).document_id));
        goto L45
    L43:
        if (r62.contains(r92) == false) goto L45;
    L46:
        r42 = r42 + 1;
    L47:
        r102 = r102 + 1;
        goto L26
    L48:
        r0.addAll(r1);
        if (r12 == null) goto L67;
        r12.run(r0);
        return;
    }

    public static /* synthetic */ void $r8$lambda$0P1uVySjEUrjfdPvf3nHaXF9n60(MediaDataController r0, long r1, long r3, int r5, int r6, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$getMediaCount$134(r1, r3, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$0kWtLeHeDKbGsN0dI9ZRDtzj_Mo(MediaDataController r0, TLRPC.TL_error r1, TLObject r2, String r3, boolean r4) {
        r0.lambda$loadStickersByEmojiOrName$85(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$0qsbiyJdK83D8IJyvE7btxB1wus(MediaDataController r0, TLRPC.contacts_TopPeers r1, TLRPC.TL_error r2) {
        r0.lambda$loadHints$150(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$1Ac5ipYTAwGLgQhnDgy2KP_KB3Y(MediaDataController r0, TLRPC.TL_messages_search r1, boolean r2, String r3, boolean r4, int r5, boolean r6, long r7, long r9, int r11, int r12, long r13, long r15, TLRPC.User r17, TLRPC.Chat r18, ReactionsLayoutInBubble.VisibleReaction r19, TLObject r20, TLRPC.TL_error r21) {
        r0.lambda$searchMessagesInChat$127(r1, r2, r3, r4, r5, r6, r7, r9, r11, r12, r13, r15, r17, r18, r19, r20, r21);
    }

    public static /* synthetic */ void $r8$lambda$1WLmCgC3S_9WydQRva_XKLVmkQI(MediaDataController r0, Timer.Task r1, long r2, LongSparseArray r4, boolean r5, Timer r6, AtomicInteger r7, Runnable r8, TLObject r9, TLRPC.TL_error r10) {
        r0.lambda$loadReplyMessagesForMessages$178(r1, r2, r4, r5, r6, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$1fXCGlJ_yQA0E0ahIBRGZ9J5K2o(MediaDataController r0, TLRPC.TL_messages_stickerSet r1) {
        r0.lambda$putSetToCache$48(r1);
    }

    public static /* synthetic */ void $r8$lambda$1pr0f8yw5PUZ7jCvOLZCjBchtAk(MediaDataController r0, TLRPC.StickerSet r1, ArrayList r2) {
        r0.lambda$toggleStickerSetInternal$113(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$1z5mWkoGlsYd_p9y0mTexbe3xww(MediaDataController r0, TLObject r1) {
        r0.lambda$checkTonGiftStickers$78(r1);
    }

    /* renamed from: $r8$lambda$2FSo-M_ruKXcOkbdUGsnnNR2nwQ, reason: not valid java name */
    public static /* synthetic */ void m4371$r8$lambda$2FSoM_ruKXcOkbdUGsnnNR2nwQ(MediaDataController r0, long r1, TLRPC.TL_messages_getMessages r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$loadPinnedMessageInternal$167(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$2FmAcPqaEFqTbEuKOPYjFBAQ814(MediaDataController r0) {
        r0.lambda$processLoadedReactions$15();
    }

    /* renamed from: $r8$lambda$2HhyfBx92QCiFkiz4UKpsgLR-qU, reason: not valid java name */
    public static /* synthetic */ void m4372$r8$lambda$2HhyfBx92QCiFkiz4UKpsgLRqU(MediaDataController r0, TLObject r1, SharedPreferences r2) {
        r0.lambda$loadRestrictedStatusEmojis$248(r1, r2);
    }

    /* renamed from: $r8$lambda$2UfnSD4GgwyK4-rCrbwCWP-4ezk, reason: not valid java name */
    public static /* synthetic */ void m4373$r8$lambda$2UfnSD4GgwyK4rCrbwCWP4ezk(MediaDataController r0, long r1, long r3, TLRPC.Message r5) {
        r0.lambda$saveDraftReplyMessage$195(r1, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$3AVIItRHPgzSOhQbHGeqd4cI4pU(MediaDataController r0, long r1, long r3, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$saveDraft$192(r1, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$3XCDsOVQmcgB1tVaqPJEqNqjs4Q(MediaDataController r0, TLRPC.TL_error r1, TLObject r2, int r3) {
        r0.lambda$loadArchivedStickersCount$72(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$3gx1Vj4L-S046id9KDOPh193430, reason: not valid java name */
    public static /* synthetic */ void m4374$r8$lambda$3gx1Vj4LS046id9KDOPh193430(MediaDataController r0, TLRPC.TL_error r1, TLRPC.messages_Messages r2, long r3, long r5, LongSparseArray r7, boolean r8, TLObject r9, TLRPC.TL_error r10) {
        r0.lambda$loadReplyMessagesForMessages$175(r1, r2, r3, r5, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$4LbfF3pRH11Zu_HAW8oHr4jSJOg(MediaDataController r0, TLRPC.TL_messages_stickerSet r1, String r2, int r3) {
        r0.lambda$putDiceStickersToCache$91(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$4S9s5EeVpjom6vfoXEqGa0K124w(MediaDataController r0, Timer.Task r1, TLRPC.TL_messages_getScheduledMessages r2, long r3, long r5, LongSparseArray r7, boolean r8, Timer r9, AtomicInteger r10, Runnable r11, TLObject r12, TLRPC.TL_error r13) {
        r0.lambda$loadReplyMessagesForMessages$176(r1, r2, r3, r5, r7, r8, r9, r10, r11, r12, r13);
    }

    public static /* synthetic */ void $r8$lambda$5AM5174zBSCNIG0kobI102RsOwA(MediaDataController r0, String[] r1, KeywordResultCallback r2, boolean r3, String r4, boolean r5, ArrayList r6, boolean r7, Integer r8, boolean r9, boolean r10, boolean r11, CountDownLatch r12) {
        r0.lambda$getEmojiSuggestions$227(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    public static /* synthetic */ void $r8$lambda$5KUOklu9fW7ieUMcffcEpfCd12g(MediaDataController r0, TLRPC.StickerSet r1, int r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$toggleStickerSetInternal$118(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$5VAxEMvdgk5yFiV98mR5SIFZD5Q(MediaDataController r0, String r1, TLRPC.TL_messages_stickerSet r2) {
        r0.lambda$processLoadedDiceStickers$89(r1, r2);
    }

    /* renamed from: $r8$lambda$5fFEPILEBQhfjTS7OEhs-fkjGek, reason: not valid java name */
    public static /* synthetic */ void m4375$r8$lambda$5fFEPILEBQhfjTS7OEhsfkjGek(MediaDataController r0, TLRPC.TL_messages_stickerSet r1) {
        r0.lambda$loadGroupStickerSet$44(r1);
    }

    public static /* synthetic */ void $r8$lambda$5lpYcmFaCpTHvvCJgijA4ZDXaj4(MediaDataController r0, SharedPreferences r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadRestrictedStatusEmojis$249(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$5xyB2T9b3dGaP_bEgHoNPULCccc(MediaDataController r0, long r1, TLObject r3, TLRPC.TL_messages_search r4, long r5, int r7, int r8, long r9, TLRPC.User r11, TLRPC.Chat r12, boolean r13, ReactionsLayoutInBubble.VisibleReaction r14) {
        r0.lambda$searchMessagesInChat$121(r1, r3, r4, r5, r7, r8, r9, r11, r12, r13, r14);
    }

    public static /* synthetic */ void $r8$lambda$66vkwpEdhIANJyqdva8h2BPIY1g(MediaDataController r0, int r1) {
        r0.lambda$processLoadedStickers$104(r1);
    }

    public static /* synthetic */ void $r8$lambda$69CBg_rRh9OxjL1PMBPxCU_dVdk(MediaDataController r0) {
        r0.lambda$loadDraftsIfNeed$189();
    }

    /* renamed from: $r8$lambda$6F481l-vAvkUkyswVD86nRpmWzY, reason: not valid java name */
    public static /* synthetic */ void m4376$r8$lambda$6F481lvAvkUkyswVD86nRpmWzY(MediaDataController r0) {
        r0.lambda$loadHints$148();
    }

    public static /* synthetic */ void $r8$lambda$6LEAapYUK6SfR0yKjaN8Wj81Lts(MediaDataController r0) {
        r0.lambda$clearRecentEmojiStatuses$232();
    }

    /* renamed from: $r8$lambda$6jTm-VaC3gpaV5w2O-01mBuyAkg, reason: not valid java name */
    public static /* synthetic */ void m4377$r8$lambda$6jTmVaC3gpaV5w2O01mBuyAkg(MediaDataController r0, int r1, Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadStickers$98(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$6yGRqdfDKuIrKJ4VpVct79JX8-o, reason: not valid java name */
    public static /* synthetic */ int m4378$r8$lambda$6yGRqdfDKuIrKJ4VpVct79JX8o(TLRPC.TL_topPeer r3, TLRPC.TL_topPeer r4) {
        double r0 = r3.rating;
        double r32 = r4.rating;
        if (r0 <= r32) goto L7;
        return -1;
    L7:
        if (r0 >= r32) goto L10;
        return 1;
    L10:
        return 0;
    }

    /* renamed from: $r8$lambda$7e0CmKsuM-3TZrPxlAvueyjOTEM, reason: not valid java name */
    public static /* synthetic */ TLRPC.MessageEntity m4379$r8$lambda$7e0CmKsuM3TZrPxlAvueyjOTEM(Void r0) {
        return new TLRPC.TL_messageEntitySpoiler();
    }

    public static /* synthetic */ void $r8$lambda$9wJTCP0tWRGg4oSgHptPnDbIeHo(MediaDataController r0, long r1, long r3, int r5, int r6) {
        r0.lambda$putMediaCountDatabase$140(r1, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$9yakpRm8BJi57MFbxlUzpv2KkAc(boolean[] r2, Runnable r3) {
        if (r2[0] == true) goto L6;
        r3.run();
        r2[0] = true;
        return;
    }

    public static /* synthetic */ void $r8$lambda$AMEYgCXWxN_aUHmYBBC9EZrI2J0(MediaDataController r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$preloadPremiumPreviewStickers$208(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ATHu0nIsQTDgrQWCP5FpBxqMKbU(MediaDataController r0, String r1, Utilities.Callback r2, Boolean r3, TLRPC.TL_messages_stickerSet r4) {
        r0.lambda$getStickerSet$36(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$AfkV-D-xta-5wZkSxChxmYm3yfw, reason: not valid java name */
    public static /* synthetic */ void m4380$r8$lambda$AfkVDxta5wZkSxChxmYm3yfw(MediaDataController r0, String r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$verifyAnimatedStickerMessageInternal$71(r1, r2, r3);
    }

    public static /* synthetic */ int $r8$lambda$AtkqwPYGFdjICp_6SLuDfSwq_l0(TLRPC.MessageEntity r0, TLRPC.MessageEntity r1) {
        return Integer.compare(r0.offset, r1.offset);
    }

    public static /* synthetic */ void $r8$lambda$AuRaI4A4TE9PRWRCqJ6lufMcyP4(MediaDataController r0, int r1, int r2, int r3, long r4, ArrayList r6, ArrayList r7, ArrayList r8, ArrayList r9) {
        r0.lambda$searchMessagesInChat$123(r1, r2, r3, r4, r6, r7, r8, r9);
    }

    /* renamed from: $r8$lambda$Av2Y0BV9f6e9-kr9zYsVUmTSUNs, reason: not valid java name */
    public static /* synthetic */ void m4381$r8$lambda$Av2Y0BV9f6e9kr9zYsVUmTSUNs(MediaDataController r0, String r1) {
        r0.lambda$fetchNewEmojiKeywords$216(r1);
    }

    public static /* synthetic */ void $r8$lambda$BFpssQdI6fsOlA50aYza6KUjJ24(MediaDataController r0, SharedPreferences r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadReplyIcons$247(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Bt8PMaDhl0mrie7nntIouqas0pE(MediaDataController r0, int r1, Utilities.Callback r2) {
        r0.lambda$loadStickers$94(r1, r2);
    }

    /* renamed from: $r8$lambda$Bzjcfvtjj7mVZfuRSlZJcFX-Iwk, reason: not valid java name */
    public static /* synthetic */ int m4382$r8$lambda$Bzjcfvtjj7mVZfuRSlZJcFXIwk(ArrayList r2, TLRPC.TL_messages_stickerSet r3, TLRPC.TL_messages_stickerSet r4) {
        int r32 = r2.indexOf(Long.valueOf(r3.set.id));
        int r22 = r2.indexOf(Long.valueOf(r4.set.id));
        if (r32 <= r22) goto L6;
        return 1;
    L6:
        if (r32 >= r22) goto L9;
        return -1;
    L9:
        return 0;
    }

    /* renamed from: $r8$lambda$C-7MDP_7roq4eRNNSuYdPqhVJWw, reason: not valid java name */
    public static /* synthetic */ void m4383$r8$lambda$C7MDP_7roq4eRNNSuYdPqhVJWw(MediaDataController r0, boolean r1, int r2) {
        r0.lambda$processLoadedFeaturedStickers$63(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$C3L6D9QUgagLa1l9hcF6LHz3kyo(MediaDataController r0, boolean r1, long r2) {
        r0.lambda$markFeaturedStickersByIdAsRead$68(r1, r2);
    }

    public static /* synthetic */ int $r8$lambda$CY6AGo9tHbOkKYq3h6lRfPFXpd8(TLRPC.MessageEntity r0, TLRPC.MessageEntity r1) {
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

    public static /* synthetic */ void $r8$lambda$CqDQVyvCSHlxRoY_gOHqru9Rvno(MediaDataController r0, int[] r1, long r2, long r4, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$getMediaCounts$131(r1, r2, r4, r6, r7);
    }

    /* renamed from: $r8$lambda$DRjYq2oSH-Z1psrftVLumNnQCtE, reason: not valid java name */
    public static /* synthetic */ void m4384$r8$lambda$DRjYq2oSHZ1psrftVLumNnQCtE(MediaDataController r0, boolean r1, TLRPC.TL_messages_stickerSet r2, int r3, String r4, boolean r5) {
        r0.lambda$processLoadedDiceStickers$90(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$DT0s-Y98ixSEXGVNmgb4xMi6mKg, reason: not valid java name */
    public static /* synthetic */ void m4385$r8$lambda$DT0sY98ixSEXGVNmgb4xMi6mKg(MediaDataController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadDraftsIfNeed$190(r1, r2);
    }

    /* renamed from: $r8$lambda$DxfR5PIoaxl9kcMQJj6e-9Um1lo, reason: not valid java name */
    public static /* synthetic */ void m4386$r8$lambda$DxfR5PIoaxl9kcMQJj6e9Um1lo(MediaDataController r0) {
        r0.lambda$processLoadedPremiumPromo$9();
    }

    /* renamed from: $r8$lambda$E4bSd8rclWzTHO-BuLFkuZajDBo, reason: not valid java name */
    public static /* synthetic */ void m4387$r8$lambda$E4bSd8rclWzTHOBuLFkuZajDBo(MediaDataController r0, boolean r1) {
        r0.lambda$processLoadedFeaturedStickers$60(r1);
    }

    public static /* synthetic */ void $r8$lambda$E6C2YqMnUW3US169uSQ_5Y8jL3U(MediaDataController r0, TLRPC.TL_messages_stickerSet r1) {
        r0.lambda$loadGroupStickerSet$46(r1);
    }

    public static /* synthetic */ void $r8$lambda$EAiDzgU45_D_7lg5B4HZI6d51tw(MediaDataController r0, String r1, boolean r2) {
        r0.lambda$loadStickersByEmojiOrName$84(r1, r2);
    }

    /* renamed from: $r8$lambda$EHNufkdaRk3kw3MQ7yIKId9-V90, reason: not valid java name */
    public static /* synthetic */ void m4388$r8$lambda$EHNufkdaRk3kw3MQ7yIKId9V90(MediaDataController r0, TLRPC.InputStickerSet r1, Integer r2, String r3, Utilities.Callback r4, boolean r5) {
        r0.lambda$getStickerSet$38(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$EJfhm6mfz-uEPrxUBGqEBmcp1B0, reason: not valid java name */
    public static /* synthetic */ void m4389$r8$lambda$EJfhm6mfzuEPrxUBGqEBmcp1B0(MediaDataController r0, int r1, ArrayList r2, boolean r3, long r4, int r6, int r7, long r8) {
        r0.lambda$putMediaDatabase$142(r1, r2, r3, r4, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$EjuzO67M6m-4PCGLxLYxP8FmOhI, reason: not valid java name */
    public static /* synthetic */ void m4390$r8$lambda$EjuzO67M6m4PCGLxLYxP8FmOhI(MediaDataController r0, long r1, boolean r3, int r4, int r5, int r6, long r7, int r9) {
        r0.lambda$processLoadedMediaCount$139(r1, r3, r4, r5, r6, r7, r9);
    }

    /* renamed from: $r8$lambda$Ep76sc_TTJSwi6-dRmbb0orioAc, reason: not valid java name */
    public static /* synthetic */ void m4391$r8$lambda$Ep76sc_TTJSwi6dRmbb0orioAc(boolean[] r2, ArrayList[] r3, Runnable r4, ArrayList r5) {
        if (r2[0] == true) goto L6;
        r3[0] = r5;
        r4.run();
        r2[0] = true;
        return;
    }

    public static /* synthetic */ void $r8$lambda$EpIv0MhOLwpROuRA5Kin5AxOID0(MediaDataController r0, TLRPC.TL_help_premiumPromo r1, int r2) {
        r0.lambda$putPremiumPromoToCache$10(r1, r2);
    }

    /* renamed from: $r8$lambda$Ex_Ge2zW3aQweJAgm6QF-rftidY, reason: not valid java name */
    public static /* synthetic */ void m4392$r8$lambda$Ex_Ge2zW3aQweJAgm6QFrftidY(String r11, BackupImageView r12, String r13, TLRPC.TL_messages_stickerSet r14) {
        if (r14 == null) goto L36;
        ArrayList<Emoji.EmojiSpanRange> r112 = Emoji.parseEmojis(r11);
        int r1 = 0;
    L6:
        if (r1 >= r112.size()) goto L8;
        r112.get(r1).code = Emoji.fixEmoji(r112.get(r1).code.toString());
        r1 = r1 + 1;
        goto L6
    L8:
        int r15 = 0;
    L10:
        if (r15 >= r14.documents.size()) goto L24;
        TLRPC.Document r2 = r14.documents.get(r15);
        int r4 = r112.size();
        int r5 = 0;
    L12:
        if (r5 >= r4) goto L23;
        Emoji.EmojiSpanRange r6 = r112.get(r5);
        r5 = r5 + 1;
        Emoji.EmojiSpanRange r62 = r6;
        int r7 = 0;
    L15:
        if (r7 >= r14.packs.size()) goto L22;
        if (r14.packs.get(r7).documents.contains(Long.valueOf(r2.id)) == false) goto L21;
        if (TextUtils.equals(Emoji.fixEmoji(r14.packs.get(r7).emoticon), r62.code) == true) goto L12;
    L21:
        r7 = r7 + 1;
        goto L15
    L22:
        r15 = r15 + 1;
        goto L10
    L23:
        TLRPC.Document r72 = r2;
    L25:
        if (r72 == null) goto L35;
        r12.setImage(ImageLocation.getForDocument(r72), r13, DocumentObject.getSvgThumb(r72, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f, 1.0f, null), 0, r72);
        r12.invalidate();
        return;
    L35:
        return;
    L24:
        r72 = null;
        goto L25
    }

    public static /* synthetic */ void $r8$lambda$F2_6dSsXNroEXDRBbR99omTEepU(MediaDataController r0, String r1) {
        r0.lambda$fetchNewEmojiKeywords$214(r1);
    }

    public static /* synthetic */ void $r8$lambda$FI7WP19QDNfbKjxXks2LklOt5ps(MediaDataController r0, ArrayList r1, long r2, boolean r4) {
        r0.lambda$processLoadedFeaturedStickers$61(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$FcXqRruAQic4oxDxaJnWOvMfzrU(MediaDataController r0, ArrayList r1, long r2, int r4) {
        r0.lambda$processLoadedStickers$105(r1, r2, r4);
    }

    /* renamed from: $r8$lambda$Fke06oyXxMW10al12m9Uo-nnvug, reason: not valid java name */
    public static /* synthetic */ void m4393$r8$lambda$Fke06oyXxMW10al12m9Uonnvug(MediaDataController r0, long r1, int r3, double r4) {
        r0.lambda$savePeer$160(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$GDQCBKg0j8tkQmJdTNbHWi7e_k8(MediaDataController r0, ArrayList r1, boolean r2, ArrayList r3, ArrayList r4, LongSparseArray r5, long r6) {
        r0.lambda$broadcastReplyMessages$181(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$GGECOU8yEd257MqUVDuQmMPqYo8(MediaDataController r0, TLRPC.StickerSet r1, BaseFragment r2, boolean r3, int r4, boolean r5, FrameLayout r6, Context r7, TLObject r8, TLRPC.Document r9, TLObject r10, TLRPC.TL_error r11) {
        r0.lambda$toggleStickerSetInternal$115(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    /* renamed from: $r8$lambda$GehAM-A2vy8gpcrvAkNFL38Vhro, reason: not valid java name */
    public static /* synthetic */ void m4394$r8$lambda$GehAMA2vy8gpcrvAkNFL38Vhro(MediaDataController r0, TLRPC.TL_messages_saveGif r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$removeRecentGif$24(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$I9xs9PtBzMtpEZVYvcnuzCPUBfQ(MediaDataController r0, long r1, long r3, TLRPC.TL_channels_getMessages r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$loadPinnedMessageInternal$166(r1, r3, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$IH6fOrTsyEpX7o8-LUfgJoPGmQs, reason: not valid java name */
    public static /* synthetic */ void m4395$r8$lambda$IH6fOrTsyEpX7o8LUfgJoPGmQs(MediaDataController r0, int r1, TL_account.TL_emojiStatuses r2) {
        r0.lambda$updateEmojiStatuses$237(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Id7KiHa1GWFmEzeH4Y3UgHZVfzI(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$IldpBZkgKlaVKJcFvrU37hFLxy0(MediaDataController r0) {
        r0.lambda$processLoadedMenuBots$5();
    }

    public static /* synthetic */ void $r8$lambda$IqCkhqPANRx2aYodZo8dyoaMF1o(MediaDataController r0, ArrayList r1, boolean r2, ArrayList r3, ArrayList r4, ArrayList r5, LongSparseArray r6, LongSparseArray r7) {
        r0.lambda$broadcastPinnedMessage$171(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$IsLsNkTOAcBoIBo7umWPNeW8dUg(MediaDataController r0) {
        r0.lambda$clearRecentStickers$17();
    }

    /* renamed from: $r8$lambda$J1y-1UWdmISrZLTsdUl4cwdCNGQ, reason: not valid java name */
    public static /* synthetic */ void m4396$r8$lambda$J1y1UWdmISrZLTsdUl4cwdCNGQ(MediaDataController r0) {
        r0.lambda$loadHints$147();
    }

    /* renamed from: $r8$lambda$J4WRq8C8iShmH-u3llBSJBivwwU, reason: not valid java name */
    public static /* synthetic */ void m4397$r8$lambda$J4WRq8C8iShmHu3llBSJBivwwU(MediaDataController r0, int r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadArchivedStickersCount$73(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$JAPNQuxL-5k40UGN3YhLTRVzJd8, reason: not valid java name */
    public static /* synthetic */ void m4398$r8$lambda$JAPNQuxL5k40UGN3YhLTRVzJd8(MediaDataController r0, int r1, TLRPC.TL_messages_search r2, long r3, int r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$loadPinnedMessages$164(r1, r2, r3, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$JErgNQvP4KdjyTwBJL1JhWNqrCU(MediaDataController r0, boolean[] r1, ArrayList[] r2, Runnable r3) {
        r0.lambda$fillWithAnimatedEmoji$230(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$JHAJqfAyO4cLNwERgiDeAmVl_jg(MediaDataController r0, Object r1, TLRPC.TL_messages_saveRecentSticker r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$addRecentSticker$22(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$JI8dxy-WWlpH8Eyc8QCZMIP0RjY, reason: not valid java name */
    public static /* synthetic */ void m4399$r8$lambda$JI8dxyWWlpH8Eyc8QCZMIP0RjY(MediaDataController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$checkDefaultTopicIcons$83(r1, r2);
    }

    /* renamed from: $r8$lambda$JL_AOD6W4SJIVCx-Kj9A9e_dNlo, reason: not valid java name */
    public static /* synthetic */ void m4400$r8$lambda$JL_AOD6W4SJIVCxKj9A9e_dNlo(MediaDataController r0, TLRPC.Message r1, String r2) {
        r0.lambda$verifyAnimatedStickerMessage$69(r1, r2);
    }

    /* renamed from: $r8$lambda$JsaN-isIMjuawJui93EIOKyb1ic, reason: not valid java name */
    public static /* synthetic */ void m4401$r8$lambda$JsaNisIMjuawJui93EIOKyb1ic(MediaDataController r0, int r1, BaseFragment r2, boolean r3, int r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$toggleStickerSets$120(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$K-dwH2uIxp53vjHwfOAVa-asqx4, reason: not valid java name */
    public static /* synthetic */ void m4402$r8$lambda$KdwH2uIxp53vjHwfOAVaasqx4(Utilities.Callback r1) {
        if (r1 == null) goto L5;
        r1.run(null);
        return;
    }

    public static /* synthetic */ void $r8$lambda$KMGP2t48nulqKLGlSAoaLgY1qbQ(MediaDataController r0, String r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$fetchStickerSetInternal$43(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$KoIGksBHZPanj9AfcoJ27eZlPOQ(MediaDataController r0, TLRPC.Message r1, MessagesStorage.TopicKey r2) {
        r0.lambda$loadBotKeyboard$198(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$KrGOfd133qWJLCUOdILajx70qcQ(MediaDataController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$preloadPremiumPreviewStickers$209(r1, r2);
    }

    /* renamed from: $r8$lambda$L-QinIOL5hucsJVuM3CdocV1Mco, reason: not valid java name */
    public static /* synthetic */ void m4403$r8$lambda$LQinIOL5hucsJVuM3CdocV1Mco(MediaDataController r0, String r1, Utilities.Callback r2, Boolean r3, TLRPC.TL_messages_stickerSet r4) {
        r0.lambda$getStickerSet$33(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$L3MIH5fRmKZ5M7_Wxb9xyaI-J0I, reason: not valid java name */
    public static /* synthetic */ void m4404$r8$lambda$L3MIH5fRmKZ5M7_Wxb9xyaIJ0I(MediaDataController r0) {
        r0.lambda$cleanup$2();
    }

    /* renamed from: $r8$lambda$LFvUJsugUjuneA2_Ew8-k9w947s, reason: not valid java name */
    public static /* synthetic */ void m4405$r8$lambda$LFvUJsugUjuneA2_Ew8k9w947s(MediaDataController r0, TLObject r1, boolean r2, long r3) {
        r0.lambda$loadFeaturedStickers$58(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$LZPbw98xfZ5KPnyNJo7ipeggr-s, reason: not valid java name */
    public static /* synthetic */ void m4406$r8$lambda$LZPbw98xfZ5KPnyNJo7ipeggrs(MediaDataController r0, long r1, int r3) {
        r0.lambda$deletePeer$161(r1, r3);
    }

    /* renamed from: $r8$lambda$LaHpliuVt0WzKc2-XfyQOt-93iA, reason: not valid java name */
    public static /* synthetic */ void m4407$r8$lambda$LaHpliuVt0WzKc2XfyQOt93iA(MediaDataController r0, int r1, LongSparseArray r2, HashMap r3, ArrayList r4, long r5, int r7, LongSparseArray r8, HashMap r9, LongSparseArray r10, Runnable r11) {
        r0.lambda$processLoadedStickers$106(r1, r2, r3, r4, r5, r7, r8, r9, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$LfaSk3QHQofX3vRpgncbP0suO6w(MediaDataController r0, Timer.Task r1, LongSparseArray r2, AtomicInteger r3, Runnable r4, int r5, Timer r6, LongSparseArray r7, LongSparseArray r8, boolean r9, long r10) {
        r0.lambda$loadReplyMessagesForMessages$179(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$M7TWE_HpJJfVMGKfhSX786fRXGk(MediaDataController r0, TLRPC.Document r1) {
        r0.lambda$addRecentGif$26(r1);
    }

    /* renamed from: $r8$lambda$MC-gn_-kD4rAE0HNL3MgQOZc3MU, reason: not valid java name */
    public static /* synthetic */ void m4408$r8$lambda$MCgn_kD4rAE0HNL3MgQOZc3MU(MediaDataController r0, ArrayList r1, ArrayList r2, ArrayList r3, ArrayList r4, ArrayList r5, ArrayList r6) {
        r0.lambda$loadHints$146(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$MIYQGC7mTvCUIjjy3tK1afK8goE(MediaDataController r0, TLRPC.Document r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$saveToRingtones$207(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$MMe9vJ5GLOwq-naw-F5yW_sxRJs, reason: not valid java name */
    public static /* synthetic */ void m4409$r8$lambda$MMe9vJ5GLOwqnawF5yW_sxRJs(MediaDataController r0, boolean r1, int r2, ArrayList r3, boolean r4, int r5) {
        r0.lambda$processLoadedRecentDocuments$53(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$MNd5ZyLzS0Y886dnaMojZUYZEyY(KeywordResultCallback r0, ArrayList r1, String r2) {
        r0.run(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$M_pVoSstaXsRdguw1I7XYz4sYhs(Utilities.Callback r1) {
        if (r1 == null) goto L5;
        r1.run(null);
        return;
    }

    public static /* synthetic */ void $r8$lambda$MfXat6NbMFN65pGW4jrWVSjbO7Q(MediaDataController r0, long r1, long r3, int[] r5) {
        r0.lambda$getMediaCounts$132(r1, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$MptAQtovr9XIRUrdLWqnEkdYXCc(MediaDataController r0, TLObject r1) {
        r0.lambda$loadReactions$13(r1);
    }

    /* renamed from: $r8$lambda$MqO6Cd2t9_f8-jTog_chgdHuf_U, reason: not valid java name */
    public static /* synthetic */ void m4410$r8$lambda$MqO6Cd2t9_f8jTog_chgdHuf_U(MediaDataController r0, int r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$fetchEmojiStatuses$236(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$NCn_yOEQysfc6P8b7Ggboq9u_oM(MediaDataController r0, int r1, TLRPC.Document r2) {
        r0.lambda$addRecentSticker$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$OkUErbks0KzldNJS7Ha6sNhiqBI(CountDownLatch r0, KeywordResultCallback r1, ArrayList r2, String r3) {
        if (r0 == null) goto L5;
        r1.run(r2, r3);
        r0.countDown();
        return;
    L5:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda170(r1, r2, r3));
    }

    public static /* synthetic */ void $r8$lambda$P07UD38xHwHnuBgLff3vTufHzN8() {
        ShortcutManagerCompat.removeAllDynamicShortcuts(ApplicationLoader.applicationContext);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ int $r8$lambda$Pep5JxVdFCOJQzfpa4tXPIUljyQ(TLRPC.TL_topPeer r3, TLRPC.TL_topPeer r4) {
        double r0 = r3.rating;
        double r32 = r4.rating;
        if (r0 <= r32) goto L7;
        return -1;
    L7:
        if (r0 >= r32) goto L10;
        return 1;
    L10:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$Pj0xEgyMXmipuBXfkzAy3XAvHfc(MediaDataController r0, ArrayList r1, int r2, LongSparseArray r3, TLRPC.StickerSet r4, TLRPC.TL_messages_allStickers r5, int r6, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$processLoadStickersResponse$75(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$Pr7utCRxJCepLe84YrEIDZfD_2c(MediaDataController r0) {
        r0.lambda$fetchNewEmojiKeywords$213();
    }

    /* renamed from: $r8$lambda$Ps-zCSYMNGfIpwn4KBIrmGd-bkI, reason: not valid java name */
    public static /* synthetic */ void m4411$r8$lambda$PszCSYMNGfIpwn4KBIrmGdbkI(MediaDataController r0, TLRPC.TL_messages_stickerSet r1, String r2, Utilities.Callback r3, boolean r4, TLRPC.InputStickerSet r5) {
        r0.lambda$getStickerSet$37(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$QG_W1RTGAlP4_99CvDoo03bbmKI(MediaDataController r0, boolean[] r1, ArrayList r2, int r3, int[] r4) {
        r0.lambda$removeMultipleStickerSets$111(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$QaRDiqkLiz5MLh9E9JcXSiJsFic(MediaDataController r0, long r1, long r3, int r5) {
        r0.lambda$getMediaCounts$133(r1, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$QnVGIsqtYnBBEqtaf_FyjzkyhZU(MediaDataController r0, long r1, long r3, int[] r5) {
        r0.lambda$getMediaCounts$130(r1, r3, r5);
    }

    /* renamed from: $r8$lambda$QqUVk1xA9_laD5nQ4yVHA-MAFNY, reason: not valid java name */
    public static /* synthetic */ void m4412$r8$lambda$QqUVk1xA9_laD5nQ4yVHAMAFNY(MediaDataController r0, TLRPC.StickerSet r1) {
        r0.lambda$loadGroupStickerSet$45(r1);
    }

    public static /* synthetic */ void $r8$lambda$Rg_7uqnGyItVMkhesdOmuxKyBuk(MediaDataController r0) {
        r0.lambda$loadAttachMenuBots$3();
    }

    public static /* synthetic */ void $r8$lambda$S4b4AUWKNBZIuo1K9Oj1dFcOY0M(MediaDataController r0, long r1, long r3, int[] r5) {
        r0.lambda$getMediaCounts$129(r1, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$SUeplJhyJA1Ku6ozx0bZd9G6LaM(MediaDataController r0, Object r1, TLRPC.TL_messages_faveSticker r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$addRecentSticker$21(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$SiqMIVWbvqIgXJo50QpJi-bEt3o, reason: not valid java name */
    public static /* synthetic */ void m4413$r8$lambda$SiqMIVWbvqIgXJo50QpJibEt3o(MediaDataController r0, Integer r1, ArrayList r2, boolean r3, boolean r4, ArrayList[] r5, Runnable r6) {
        r0.lambda$fillWithAnimatedEmoji$228(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$TBhhe9KENoKwQdlhFKzuWXvW-Bg, reason: not valid java name */
    public static /* synthetic */ void m4414$r8$lambda$TBhhe9KENoKwQdlhFKzuWXvWBg(MediaDataController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$checkGenericAnimations$81(r1, r2);
    }

    /* renamed from: $r8$lambda$TE3T0YYPluOwFFlqafylXObZY-A, reason: not valid java name */
    public static /* synthetic */ void m4415$r8$lambda$TE3T0YYPluOwFFlqafylXObZYA(MediaDataController r0, String r1, boolean r2) {
        r0.lambda$processLoadedDiceStickers$88(r1, r2);
    }

    public static /* synthetic */ TLRPC.MessageEntity $r8$lambda$TTZfwJCrpfiWZtEesLfe2rr2EDk(Void r0) {
        return new TLRPC.TL_messageEntityStrike();
    }

    public static /* synthetic */ void $r8$lambda$Tdd9244YBB3SznVc7O88fjmdAJ4(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$TsDNn6mp8BANEfG8Z802Y0C0lLM(MediaDataController r0, String r1) {
        r0.lambda$processLoadedDiceStickers$87(r1);
    }

    /* renamed from: $r8$lambda$TydTU6nir-PHrA8soFRPDpOaMsw, reason: not valid java name */
    public static /* synthetic */ void m4416$r8$lambda$TydTU6nirPHrA8soFRPDpOaMsw(MediaDataController r0, String r1) {
        r0.lambda$putEmojiKeywords$217(r1);
    }

    public static /* synthetic */ void $r8$lambda$UVpnZXvkMecKhChSeBdlMogq0Qc(MediaDataController r0) {
        r0.lambda$loadReactions$12();
    }

    /* renamed from: $r8$lambda$UXWJlQXYVMsq-qXvi8YmilGHTms, reason: not valid java name */
    public static /* synthetic */ void m4417$r8$lambda$UXWJlQXYVMsqqXvi8YmilGHTms(MediaDataController r0, boolean r1, int r2) {
        r0.lambda$loadRecents$50(r1, r2);
    }

    /* renamed from: $r8$lambda$V-B_E5WJwyPwJ0kS8gZyDMEd53Q, reason: not valid java name */
    public static /* synthetic */ void m4418$r8$lambda$VB_E5WJwyPwJ0kS8gZyDMEd53Q(MediaDataController r0, TLRPC.StickerSet r1, int r2) {
        r0.lambda$toggleStickerSetInternal$117(r1, r2);
    }

    /* renamed from: $r8$lambda$VE8NpVLw-iZBQWwReDpW6mRiND0, reason: not valid java name */
    public static /* synthetic */ void m4419$r8$lambda$VE8NpVLwiZBQWwReDpW6mRiND0(MediaDataController r0) {
        r0.lambda$cleanupStickerSetCache$40();
    }

    public static /* synthetic */ void $r8$lambda$VHwF1CZkDBIlAZ4n4sExzsCmDmI(MediaDataController r0, long r1, ArrayList r3, ArrayList r4) {
        r0.lambda$loadMusic$143(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$VZD9QVZdY0fwXzuOUr5ZRq_mUv4(MediaDataController r0) {
        r0.lambda$addRecentSticker$20();
    }

    /* renamed from: $r8$lambda$Veb6eRu9aAYt0yW5GB-rdqlGXwg, reason: not valid java name */
    public static /* synthetic */ void m4420$r8$lambda$Veb6eRu9aAYt0yW5GBrdqlGXwg(MediaDataController r0, SharedPreferences r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadSavedReactions$243(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$VjFfl5oR-aSCB4PtJKkttC-iizk, reason: not valid java name */
    public static /* synthetic */ void m4421$r8$lambda$VjFfl5oRaSCB4PtJKkttCiizk(MediaDataController r0, int r1, int r2) {
        r0.lambda$processLoadedStickers$107(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VvBQaSKVwrs66EJD6I2lNHoAXmU(MediaDataController r0, Utilities.Callback r1, TL_bots.BotInfo r2, int r3) {
        r0.lambda$loadBotInfo$200(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$W7lnRlgvg-zRpP5Bbx5Ydo8aKzk, reason: not valid java name */
    public static /* synthetic */ void m4422$r8$lambda$W7lnRlgvgzRpP5Bbx5Ydo8aKzk(MediaDataController r0, SharedPreferences r1, boolean[] r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadRecentAndTopReactions$239(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$WCwF7GQhV7IsM1s0J7WCM1FeQsE(MediaDataController r0, TLObject r1) {
        r0.lambda$checkPremiumGiftStickers$76(r1);
    }

    public static /* synthetic */ int $r8$lambda$WHkZ79EYDsLMv0oMO4V39gy3i30(TLRPC.TL_topPeer r3, TLRPC.TL_topPeer r4) {
        double r0 = r3.rating;
        double r32 = r4.rating;
        if (r0 <= r32) goto L7;
        return -1;
    L7:
        if (r0 >= r32) goto L10;
        return 1;
    L10:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$WK0txn5hFWzPa4HOB4yGNFyJ42Q(MediaDataController r0, SearchStickersKey r1, SearchStickersResult r2, Utilities.Callback r3, TLRPC.messages_FoundStickers r4, TLRPC.TL_error r5) {
        r0.lambda$searchStickers$250(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$WfTOOf68vUTkxGDmmfJ3TZKqMVc(MediaDataController r0, int r1, Utilities.Callback r2, long r3, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$loadStickers$102(r1, r2, r3, r5, r6);
    }

    /* renamed from: $r8$lambda$Wjnt49-L2HqOE5kQtSiwsjuVreY, reason: not valid java name */
    public static /* synthetic */ void m4423$r8$lambda$Wjnt49L2HqOE5kQtSiwsjuVreY(String r6, BackupImageView r7, int r8, Theme.ResourcesProvider r9, String r10, TLRPC.TL_messages_stickerSet r11) {
        if (r6.equals(r7.getTag()) == true) goto L5;
        return;
    L5:
        ColorFilter r62 = null;
        if (r11 == null) goto L26;
        ArrayList<TLRPC.Document> r0 = r11.documents;
        if (r0 == null) goto L26;
        if (r0.isEmpty() == true) goto L26;
        if (r8 >= 0) goto L14;
    L24:
        r7.setImageDrawable(null);
        return;
    L14:
        if (r8 >= r11.documents.size()) goto L24;
        TLRPC.Document r5 = r11.documents.get(r8);
        if (r5 == null) goto L22;
        Drawable r3 = DocumentObject.getSvgThumb(r5, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f, 1.0f, null);
        if (MessageObject.isTextColorEmoji(r5) == false) goto L20;
        r62 = Theme.getAnimatedEmojiColorFilter(r9);
    L20:
        r7.setColorFilter(r62);
        r7.setImage(ImageLocation.getForDocument(r5), r10, r3, 0, r5);
        r7.invalidate();
        return;
    L22:
        r7.setImageDrawable(null);
        return;
    L26:
        r7.setImageDrawable(null);
    }

    public static /* synthetic */ void $r8$lambda$WliAPJzbUkamXNnm26fjttHy5O4(MediaDataController r0, TLRPC.TL_contacts_topPeers r1) {
        r0.lambda$loadHints$149(r1);
    }

    /* renamed from: $r8$lambda$WuNtKh9H_VEqTh5lV6kf-wuVpxc, reason: not valid java name */
    public static /* synthetic */ void m4424$r8$lambda$WuNtKh9H_VEqTh5lV6kfwuVpxc(MediaDataController r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadAttachMenuBots$4(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$X4o-VuRuTj6IQvhMYCvwhvYb5mI, reason: not valid java name */
    public static /* synthetic */ void m4425$r8$lambda$X4oVuRuTj6IQvhMYCvwhvYb5mI(MediaDataController r0, ArrayList r1, boolean r2, ArrayList r3) {
        r0.lambda$broadcastPinnedMessage$169(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$XLM4lm9AbtNZLwVx3zJ0-DchJM4, reason: not valid java name */
    public static /* synthetic */ void m4426$r8$lambda$XLM4lm9AbtNZLwVx3zJ0DchJM4(MediaDataController r0, TLRPC.TL_emojiKeywordsDifference r1, String r2) {
        r0.lambda$putEmojiKeywords$218(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$XTkPGZyAzgsO1j2_0OQMwIiAknE(MediaDataController r0, TLRPC.TL_error r1, TLObject r2, SharedPreferences r3, boolean[] r4) {
        r0.lambda$loadRecentAndTopReactions$240(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$YCHdyuJWa9RuKRxluxM5zM7Ob40(MediaDataController r0, List r1, int r2, int r3) {
        r0.lambda$loadReactions$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$YGWHxbRSmwHbzSIJ14gJBD5W5hQ(MediaDataController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadReactions$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZOKg5I5YPmI0payrVbwE2TUpOCg(AtomicInteger r0, Runnable r1) {
        if (r0.decrementAndGet() != 0) goto L7;
        if (r1 == null) goto L8;
        AndroidUtilities.runOnUIThread(r1);
        return;
    L8:
        return;
    }

    public static /* synthetic */ void $r8$lambda$ZZJroqBN1_fvm2NlvYERMSqp2TA(MediaDataController r0, boolean[] r1, ArrayList r2, Context r3, BaseFragment r4, int r5) {
        r0.lambda$removeMultipleStickerSets$112(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$ZwI3Wsf6WJdgZ5__uqwpMNe7DwM(MediaDataController r0, TLRPC.messages_Messages r1, int r2, long r3, int r5, int r6, boolean r7, int r8, int r9) {
        r0.lambda$processLoadedMedia$138(r1, r2, r3, r5, r6, r7, r8, r9);
    }

    /* renamed from: $r8$lambda$_1PfEK7DyhLSWPkkMLsHtoE-MyU, reason: not valid java name */
    public static /* synthetic */ void m4427$r8$lambda$_1PfEK7DyhLSWPkkMLsHtoEMyU(MediaDataController r0, TLRPC.TL_attachMenuBots r1, long r2, int r4) {
        r0.lambda$putMenuBotsToCache$6(r1, r2, r4);
    }

    /* renamed from: $r8$lambda$_BCRf_va-Zd9KdWZ6p58RKK0KzM, reason: not valid java name */
    public static /* synthetic */ void m4428$r8$lambda$_BCRf_vaZd9KdWZ6p58RKK0KzM(Utilities.Callback r1) {
        if (r1 == null) goto L5;
        r1.run(null);
        return;
    }

    public static /* synthetic */ void $r8$lambda$_WVP4BOu0DIE7Zj0eyw_5dxi8Sc(MediaDataController r0, ArrayList r1, long r2) {
        r0.lambda$savePinnedMessages$168(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_wg_9CBiATJuYEa4BRradvrp8hI(MediaDataController r0, long r1, TLRPC.TL_messages_search r3, long r4, int r6, int r7, long r8, TLRPC.User r10, TLRPC.Chat r11, boolean r12, ReactionsLayoutInBubble.VisibleReaction r13, TLObject r14, TLRPC.TL_error r15) {
        r0.lambda$searchMessagesInChat$122(r1, r3, r4, r6, r7, r8, r10, r11, r12, r13, r14, r15);
    }

    public static /* synthetic */ void $r8$lambda$asqz6waovZvVGcvm3dY5ZjImRog(MediaDataController r0, boolean r1, ArrayList r2, long r3, Runnable r5, int r6) {
        r0.lambda$searchMessagesInChat$125(r1, r2, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$awU0yqIu8sTjjUe7z_itFdGrcG4(MediaDataController r0, TLObject r1, TLRPC.Document r2) {
        r0.lambda$saveToRingtones$206(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$bA2ItQv60sbFRF2Mu1q5PPKwrO8(MediaDataController r0, int r1) {
        r0.lambda$fetchEmojiStatuses$234(r1);
    }

    /* renamed from: $r8$lambda$bEgRg-MrjYqKX6X-ULMGLo-b89k, reason: not valid java name */
    public static /* synthetic */ void m4429$r8$lambda$bEgRgMrjYqKX6XULMGLob89k(MediaDataController r0, TLRPC.StickerSet r1, ArrayList r2) {
        r0.lambda$toggleStickerSetInternal$116(r1, r2);
    }

    /* renamed from: $r8$lambda$bLqcvxgkxYh-nwpEJ3zjS4bt_Fg, reason: not valid java name */
    public static /* synthetic */ void m4430$r8$lambda$bLqcvxgkxYhnwpEJ3zjS4bt_Fg(MediaDataController r0, TLRPC.TL_messages_stickerSet r1) {
        r0.lambda$replaceStickerSet$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$bTvSSYxJQDxybCGLV3lubjLmdU4(MediaDataController r0, Timer.Task r1, Timer r2, ArrayList r3, long r4, LongSparseArray r6, Runnable r7) {
        r0.lambda$loadReplyMessagesForMessages$173(r1, r2, r3, r4, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$bXoPtAtMMpSFm4ttQszCOWpfLYs(MediaDataController r0, boolean r1, ArrayList r2) {
        r0.lambda$buildShortcuts$145(r1, r2);
    }

    /* renamed from: $r8$lambda$bZ-lxvO-RYH1W_O2rPaCH7yF5UU, reason: not valid java name */
    public static /* synthetic */ void m4431$r8$lambda$bZlxvORYH1W_O2rPaCH7yF5UU(MediaDataController r0, TLRPC.TL_error r1, TLObject r2, SharedPreferences r3) {
        r0.lambda$loadSavedReactions$242(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$bzsgrJLdWOkHqFrAYUnvgkhRrwU(MediaDataController r0, ArrayList r1) {
        r0.lambda$broadcastPinnedMessage$170(r1);
    }

    /* renamed from: $r8$lambda$c-qkp625alSbuAb1BM_EGVbStdE, reason: not valid java name */
    public static /* synthetic */ void m4432$r8$lambda$cqkp625alSbuAb1BM_EGVbStdE(MediaDataController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$checkPremiumGiftStickers$77(r1, r2);
    }

    /* renamed from: $r8$lambda$c0Ii-9A-tbHC8ebW1Qs0kG2h5uM, reason: not valid java name */
    public static /* synthetic */ void m4433$r8$lambda$c0Ii9AtbHC8ebW1Qs0kG2h5uM(MediaDataController r0, boolean r1, int r2, ArrayList r3) {
        r0.lambda$processLoadedRecentDocuments$54(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$dC77rTn3eAdEylWOJyaLa8E38-U, reason: not valid java name */
    public static /* synthetic */ void m4434$r8$lambda$dC77rTn3eAdEylWOJyaLa8E38U(MediaDataController r0, long r1, int r3, int r4, int r5, int r6, long r7, int r9, boolean r10, int r11, TLObject r12, TLRPC.TL_error r13) {
        r0.lambda$loadMedia$128(r1, r3, r4, r5, r6, r7, r9, r10, r11, r12, r13);
    }

    /* renamed from: $r8$lambda$dfDA9tb__Wr-4aLA9mTKxgaH0lg, reason: not valid java name */
    public static /* synthetic */ void m4435$r8$lambda$dfDA9tb__Wr4aLA9mTKxgaH0lg(MediaDataController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadPremiumPromo$8(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$e6cSRQxRHAsAe1AaNb48qULq_es(MediaDataController r0, TLRPC.TL_messages_stickerSet r1) {
        r0.lambda$saveStickerSetIntoCache$41(r1);
    }

    public static /* synthetic */ void $r8$lambda$eE0qa4gDiEm7NBkLWEzFbtUa0mA(MediaDataController r0, ArrayList r1, int r2, int r3, long r4) {
        r0.lambda$putStickersToCache$103(r1, r2, r3, r4);
    }

    public static /* synthetic */ int $r8$lambda$eJb7onkloeKwgN39qjYJWu93o3U(ArrayList r3, KeywordResult r4, KeywordResult r5) {
        int r0 = r3.indexOf(r4.emoji);
        int r1 = Integer.MAX_VALUE;
        if (r0 >= 0) goto L5;
        r0 = Integer.MAX_VALUE;
    L5:
        int r32 = r3.indexOf(r5.emoji);
        if (r32 < 0) goto L9;
        r1 = r32;
    L9:
        if (r0 < r1) goto L22;
        if (r0 > r1) goto L23;
        int r33 = r4.keyword.length();
        int r42 = r5.keyword.length();
        if (r33 >= r42) goto L17;
        return -1;
    L17:
        if (r33 <= r42) goto L20;
        return 1;
    L20:
        return 0;
    L23:
        return 1;
    L22:
        return -1;
    }

    /* renamed from: $r8$lambda$eMls99-RzoLXggJ7fmhhDAO-Ke4, reason: not valid java name */
    public static /* synthetic */ void m4436$r8$lambda$eMls99RzoLXggJ7fmhhDAOKe4(Utilities.Callback r1) {
        if (r1 == null) goto L5;
        r1.run(null);
        return;
    }

    /* renamed from: $r8$lambda$eSOfYB9zzifd3JuVm8tS-SPGwcY, reason: not valid java name */
    public static /* synthetic */ void m4437$r8$lambda$eSOfYB9zzifd3JuVm8tSSPGwcY(MediaDataController r0, TLObject r1, int r2, Utilities.Callback r3, long r4) {
        r0.lambda$loadStickers$101(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$ef9mi7LpkV1YHBMF2dnDqeJP3Qg(Utilities.Callback r0, TLRPC.messages_FoundStickerSets r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.TL_messages_foundStickerSets) == false) goto L6;
        r0.run(((TLRPC.TL_messages_foundStickerSets) r1).sets);
        return;
    L6:
        r0.run(new ArrayList());
    }

    public static /* synthetic */ void $r8$lambda$ex9wSRHxy921v1s5lWHfcWuZZK8(MediaDataController r0, ArrayList r1, Runnable r2) {
        r0.lambda$processLoadedMedia$137(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$fV7MGhcRIu6jSZwd6vOyaY4PTKY(MediaDataController r0, String r1, Utilities.Callback r2) {
        r0.lambda$findStickerSetByNameInCache$30(r1, r2);
    }

    /* renamed from: $r8$lambda$fXTv663nou88RWjaWec-gW8aKi8, reason: not valid java name */
    public static /* synthetic */ void m4438$r8$lambda$fXTv663nou88RWjaWecgW8aKi8(MediaDataController r0, TLRPC.Document r1) {
        r0.lambda$removeRecentGif$25(r1);
    }

    public static /* synthetic */ void $r8$lambda$fhLV5Dbs8Sfpj5m2jXebTS0EdEg(MediaDataController r0, int r1, TLObject r2, BaseFragment r3, boolean r4, int r5) {
        r0.lambda$toggleStickerSets$119(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$gCMrUKtBNlNsbbcEuO28GxBStQc(MediaDataController r0, boolean r1, ArrayList r2, LongSparseArray r3) {
        r0.lambda$saveReplyMessages$180(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$gSU1CI1bfjLzICS3tDome0ek35k(MediaDataController r0, long r1, long r3, int r5, int r6) {
        r0.lambda$getMediaCountDatabase$141(r1, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$gdXbMriQCFrD3PYVT1rd4BVppcg(MediaDataController r0, TL_update.TL_updateBotCommands r1, long r2) {
        r0.lambda$updateBotInfo$205(r1, r2);
    }

    /* renamed from: $r8$lambda$gm3n5aisu2IeMSLJgk0TdCZE-ck, reason: not valid java name */
    public static /* synthetic */ void m4439$r8$lambda$gm3n5aisu2IeMSLJgk0TdCZEck(MediaDataController r0, long r1, double r3) {
        r0.lambda$increasePeerRaiting$158(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$hVItfl4t06DcsUMKfc6dCRilD88(MediaDataController r0, int r1, long r2, long r4, long r6, long r8) {
        r0.lambda$saveDraft$194(r1, r2, r4, r6, r8);
    }

    /* renamed from: $r8$lambda$hlj-BoR_G7QilVq3hinOx5E-Tto, reason: not valid java name */
    public static /* synthetic */ void m4440$r8$lambda$hljBoR_G7QilVq3hinOx5ETto(MediaDataController r0, long r1, long r3, TLRPC.TL_messages_search r5, int r6, int r7, int r8, ArrayList r9, TLRPC.messages_Messages r10, boolean r11, boolean r12, long r13, long r15, TLRPC.User r17, TLRPC.Chat r18) {
        r0.lambda$searchMessagesInChat$124(r1, r3, r5, r6, r7, r8, r9, r10, r11, r12, r13, r15, r17, r18);
    }

    /* renamed from: $r8$lambda$iJTrnC-x5JAPxA4rH6RDGDyxvvU, reason: not valid java name */
    public static /* synthetic */ void m4441$r8$lambda$iJTrnCx5JAPxA4rH6RDGDyxvvU(MediaDataController r0, TLRPC.messages_Messages r1, int r2, long r3, ArrayList r5, int r6, int r7, boolean r8, int r9, int r10) {
        r0.lambda$processLoadedMedia$135(r1, r2, r3, r5, r6, r7, r8, r9, r10);
    }

    /* renamed from: $r8$lambda$iUsDDFw3-6nat_lS6tdP5adYKhw, reason: not valid java name */
    public static /* synthetic */ void m4442$r8$lambda$iUsDDFw36nat_lS6tdP5adYKhw(MediaDataController r0, TLObject r1) {
        r0.lambda$clearRecentStickers$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$iZROuE5seU_1iszLVLkAkm5rHnQ(MediaDataController r0, SharedPreferences r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadAvatarConstructor$245(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$ilkQvm_mGENAQ3LNgI2ZEJXqmek(MediaDataController r0, TLObject r1) {
        r0.lambda$checkGenericAnimations$80(r1);
    }

    public static /* synthetic */ void $r8$lambda$itwJIZL85A_ye08SgsJVEctKXUY(Utilities.Callback r0, ArrayList r1) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$jTYtImWr6eV7uD4DpfYYhWE58y8(MediaDataController r0, boolean[] r1, Context r2, int r3, BaseFragment r4, FrameLayout r5, boolean r6, TLObject r7, TLRPC.StickerSet r8, TLRPC.Document r9, int r10) {
        r0.lambda$toggleStickerSet$110(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$jgFdHyE5KA60xhPAfM4pkK94Dsk(MediaDataController r0, MessagesStorage.TopicKey r1, boolean r2) {
        r0.lambda$loadBotKeyboard$199(r1, r2);
    }

    /* renamed from: $r8$lambda$k9V-9MJogaUuWUR7Okquj_zm9_g, reason: not valid java name */
    public static /* synthetic */ void m4443$r8$lambda$k9V9MJogaUuWUR7Okquj_zm9_g(MediaDataController r0, int r1, String r2, String r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$fetchNewEmojiKeywords$215(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$kqFeEXcU0jCpGID9QBPoYqea9ts(MediaDataController r0, TLRPC.TL_messages_stickerSet r1, String r2, Utilities.Callback r3, boolean r4, TLRPC.InputStickerSet r5) {
        r0.lambda$getStickerSet$34(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$krdduKsZn86HdkyYfwfqP7b6-As, reason: not valid java name */
    public static /* synthetic */ void m4444$r8$lambda$krdduKsZn86HdkyYfwfqP7b6As(Utilities.Callback r1) {
        if (r1 == null) goto L5;
        r1.run(null);
        return;
    }

    /* renamed from: $r8$lambda$lSIusQOAazR1W57UW_Yu-P_F0gU, reason: not valid java name */
    public static /* synthetic */ void m4445$r8$lambda$lSIusQOAazR1W57UW_YuP_F0gU(MediaDataController r0, String r1) {
        r0.lambda$fetchNewEmojiKeywords$211(r1);
    }

    public static /* synthetic */ void $r8$lambda$m3rTAbKOQaPTNnd9dCm7woLZP30(MediaDataController r0, String[] r1, String r2, Utilities.Callback r3) {
        r0.lambda$getEmojiNames$221(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$mkDPWbw8EtbnPRiWnvWqr3OQ8pw(MediaDataController r0, long r1) {
        r0.lambda$increasePeerRaiting$159(r1);
    }

    public static /* synthetic */ void $r8$lambda$nPeoljHvO4Kc31IDCPQichmStjQ(MediaDataController r0, int r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadRecents$51(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$nZ2jl6O0x4ZJZpujJM4oqnN7yxc(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$njMwykA0KEmBN-0YRgsTbqGkOvE, reason: not valid java name */
    public static /* synthetic */ void m4446$r8$lambda$njMwykA0KEmBN0YRgsTbqGkOvE(MediaDataController r0) {
        r0.lambda$clearTopPeers$151();
    }

    public static /* synthetic */ void $r8$lambda$njR5YTzxebinXcLEaRZqaDkcfck(MediaDataController r0, long r1) {
        r0.lambda$loadPinnedMessages$163(r1);
    }

    /* renamed from: $r8$lambda$nnAGg7z_7-z1aXrOiLfczU958IE, reason: not valid java name */
    public static /* synthetic */ void m4447$r8$lambda$nnAGg7z_7z1aXrOiLfczU958IE(MediaDataController r0, boolean[] r1, TLRPC.StickerSet r2, int r3, int r4, TLRPC.TL_messages_stickerSet r5, Runnable r6) {
        r0.lambda$toggleStickerSet$109(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$nyicmsCsnnyATaRtMB2rx-Y_I6E, reason: not valid java name */
    public static /* synthetic */ int m4448$r8$lambda$nyicmsCsnnyATaRtMB2rxY_I6E(TLRPC.MessageEntity r0, TLRPC.MessageEntity r1) {
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

    public static /* synthetic */ void $r8$lambda$o71RmJrJ3z_CCHY67dYXGCVMTsQ(MediaDataController r0, long r1, long r3, long r5) {
        r0.lambda$loadMusic$144(r1, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$oAYVKXIwq3GOEybolf6mGyUZftg(MediaDataController r0, boolean r1, long r2, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$loadFeaturedStickers$59(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$ofhoKebtAeEbV1nfaJBat7NKrp8(MediaDataController r0, TLObject r1, ArrayList r2, int r3, LongSparseArray r4, TLRPC.StickerSet r5, TLRPC.TL_messages_allStickers r6, int r7) {
        r0.lambda$processLoadStickersResponse$74(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$opp_VkG312dNpEEc3C8Q0Jp3Yj4(MediaDataController r0, long r1, long r3, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$saveDraft$193(r1, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$p4MIoLnh4iMqNK4bFQW3U8UY8vA(MediaDataController r0, long r1, long r3, ArrayList r5) {
        r0.lambda$loadPinnedMessages$165(r1, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$p6sTXctDDgM7gGCS44sOWr2Fipw(MediaDataController r0, TLObject r1, SharedPreferences r2) {
        r0.lambda$loadReplyIcons$246(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$pKLC_goJd0vmGBP8ZxJyc_yg8sM(MediaDataController r0, String r1, TLObject r2) {
        r0.lambda$verifyAnimatedStickerMessageInternal$70(r1, r2);
    }

    /* renamed from: $r8$lambda$pYkMIv-GzUG5pSXl1gOrDG_DO88, reason: not valid java name */
    public static /* synthetic */ void m4449$r8$lambda$pYkMIvGzUG5pSXl1gOrDG_DO88(MediaDataController r0, boolean r1, ArrayList r2, int r3) {
        r0.lambda$loadRecents$49(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$pzLB5uL2VmpDNcFZ1oEjC2tB4U0(MediaDataController r0, String r1) {
        r0.lambda$fetchNewEmojiKeywords$210(r1);
    }

    /* renamed from: $r8$lambda$q0mjMj9V-Tygqw3XFt2KiBruXwI, reason: not valid java name */
    public static /* synthetic */ void m4450$r8$lambda$q0mjMj9VTygqw3XFt2KiBruXwI(MediaDataController r0, boolean r1, int r2) {
        r0.lambda$loadFeaturedStickers$57(r1, r2);
    }

    /* renamed from: $r8$lambda$q6YA_BH4qB8C-np2FxS6ZQ14REY, reason: not valid java name */
    public static /* synthetic */ void m4451$r8$lambda$q6YA_BH4qB8Cnp2FxS6ZQ14REY(MediaDataController r0, Timer.Task r1, long r2, ArrayList r4) {
        r0.lambda$loadReplyMessagesForMessages$172(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$q7LD5EOpYGzxZhYl4Ro5aHUHGBw(MediaDataController r0, TLRPC.TL_messages_stickerSet r1, Utilities.Callback r2) {
        r0.lambda$findStickerSetByNameInCache$29(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$qQ3XnmtPA9yC_VVSqrib_5B1Kqo(MediaDataController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$checkTonGiftStickers$79(r1, r2);
    }

    /* renamed from: $r8$lambda$qZ_xRLvisWOZ-WDs6CgMEEvkERw, reason: not valid java name */
    public static /* synthetic */ void m4452$r8$lambda$qZ_xRLvisWOZWDs6CgMEEvkERw(MediaDataController r0, SharedPreferences r1, boolean[] r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadRecentAndTopReactions$241(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$rHrY4a_DFsmIUCaj-NDML8Ai9o4, reason: not valid java name */
    public static /* synthetic */ void m4453$r8$lambda$rHrY4a_DFsmIUCajNDML8Ai9o4(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$rtv3sQv11LEDGPEbXJEV0vc4GkE(MediaDataController r0, SearchStickersKey r1, SearchStickersResult r2, Utilities.Callback r3, ArrayList r4, String r5) {
        r0.lambda$searchStickers$251(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$sIv5Lc15INxeJmct85TJEWZVab0(MediaDataController r0, int r1, boolean r2, TLObject r3, long r4, long r6, TLRPC.TL_messages_search r8, int r9, int r10, int r11, ArrayList r12, boolean r13, long r14, long r16, TLRPC.User r18, TLRPC.Chat r19, ReactionsLayoutInBubble.VisibleReaction r20) {
        r0.lambda$searchMessagesInChat$126(r1, r2, r3, r4, r6, r8, r9, r10, r11, r12, r13, r14, r16, r18, r19, r20);
    }

    public static /* synthetic */ TLRPC.MessageEntity $r8$lambda$sRVhz0VV932VixK71DorRWVm4Ig(Void r0) {
        return new TLRPC.TL_messageEntityBold();
    }

    public static /* synthetic */ void $r8$lambda$sh6_OdNNEA7wk_jYe51izXywAuo(MediaDataController r0, String r1, Utilities.Callback r2, TLRPC.InputStickerSet r3, Boolean r4, TLRPC.TL_messages_stickerSet r5) {
        r0.lambda$getStickerSet$39(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$sm3Fyorn98jVQzDkt8JuuNbh500(TLRPC.Document r4) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{0, r4, 7});
    }

    /* renamed from: $r8$lambda$t8CkQe-1DfWiJvfmiaFxmebs-II, reason: not valid java name */
    public static /* synthetic */ void m4454$r8$lambda$t8CkQe1DfWiJvfmiaFxmebsII(MediaDataController r0, long r1, long r3, Utilities.Callback r5, int r6) {
        r0.lambda$loadBotInfo$202(r1, r3, r5, r6);
    }

    /* renamed from: $r8$lambda$t90YCi5spEH_TbdpJw0-4i_iadU, reason: not valid java name */
    public static /* synthetic */ void m4455$r8$lambda$t90YCi5spEH_TbdpJw04i_iadU(MediaDataController r0, ArrayList r1) {
        r0.lambda$loadRepliesOfDraftReplies$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$t9ZWSi69spcV8DggoL4ybpDSOqY(MediaDataController r0, String r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadStickersByEmojiOrName$86(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$u2nC4GcdCSv_HcW0VJPAoJO4c4A(MediaDataController r0, boolean r1, ArrayList r2, LongSparseArray r3, ArrayList r4, long r5, int r7, boolean r8) {
        r0.lambda$processLoadedFeaturedStickers$62(r1, r2, r3, r4, r5, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$uQfN7vjJC1O7GiZaUvvpcIkvUeQ(MediaDataController r0) {
        r0.lambda$fetchEmojiStatuses$235();
    }

    public static /* synthetic */ void $r8$lambda$ucLdq_PN5bdX4bHPDPU7GTkAi2Q(MediaDataController r0, ArrayList r1, int r2, int r3) {
        r0.lambda$putReactionsToCache$16(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$ugaRz4scCC9WF3E6mbbMinweLDs(MediaDataController r0, MessagesStorage.TopicKey r1, TLRPC.Message r2) {
        r0.lambda$putBotKeyboard$203(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$utbjS1XEEtjRfVc5JHMhQDfzKu0(MediaDataController r0, TL_bots.BotInfo r1, long r2) {
        r0.lambda$putBotInfo$204(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$vAkciiAUtnRnjGywixXmHbUGDNI(MediaDataController r0, ArrayList r1, ArrayList r2, boolean r3, int r4, long r5, boolean r7) {
        r0.lambda$putFeaturedStickersToCache$65(r1, r2, r3, r4, r5, r7);
    }

    public static /* synthetic */ void $r8$lambda$vUCrt_dLSDPU6A5japWnlR20UsI(MediaDataController r0, boolean r1) {
        r0.lambda$loadFeaturedStickers$56(r1);
    }

    public static /* synthetic */ void $r8$lambda$vfrQC3lNNWPJLLzNvjaM8DQjnCk(MediaDataController r0, TLRPC.StickerSet r1, TLObject r2, BaseFragment r3, boolean r4, int r5, TLRPC.TL_error r6, boolean r7, FrameLayout r8, Context r9, TLObject r10, TLRPC.Document r11) {
        r0.lambda$toggleStickerSetInternal$114(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$vntmLI7gZQwx7oyMWGW9A3lJaUE(Utilities.Callback r1) {
        if (r1 == null) goto L5;
        r1.run(null);
        return;
    }

    /* renamed from: $r8$lambda$wBCqQElA-b5fBVleqY_5HUB7OfQ, reason: not valid java name */
    public static /* synthetic */ void m4456$r8$lambda$wBCqQElAb5fBVleqY_5HUB7OfQ(MediaDataController r0, String[] r1, KeywordResultCallback r2, ArrayList r3) {
        r0.lambda$getEmojiSuggestions$222(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$wIoWPUVbMxWonKd4GiKo2tEHUuo(MediaDataController r0, TLRPC.InputStickerSet r1, Integer r2, String r3, Utilities.Callback r4, boolean r5) {
        r0.lambda$getStickerSet$35(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$wJDy_w77sy15BDCdDSY_9j44pbY(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$wNFYTRGiK-5eb4bmqyWTYYFfgYE, reason: not valid java name */
    public static /* synthetic */ void m4457$r8$lambda$wNFYTRGiK5eb4bmqyWTYYFfgYE(MediaDataController r0, Timer.Task r1, long r2, long r4, LongSparseArray r6, boolean r7, Timer r8, AtomicInteger r9, Runnable r10, TLObject r11, TLRPC.TL_error r12) {
        r0.lambda$loadReplyMessagesForMessages$177(r1, r2, r4, r6, r7, r8, r9, r10, r11, r12);
    }

    public static /* synthetic */ void $r8$lambda$wPyNyWKGpvA6N2Jb18dLDaUwLls(MediaDataController r0, String r1, TLObject r2) {
        r0.lambda$fetchStickerSetInternal$42(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$wRXZXkbkfnWAsQIcY1QotDYNxho(MediaDataController r0) {
        r0.lambda$fetchEmojiStatuses$233();
    }

    /* renamed from: $r8$lambda$wkqGqesk87AF-DYKQ4na9WBTVF8, reason: not valid java name */
    public static /* synthetic */ void m4458$r8$lambda$wkqGqesk87AFDYKQ4na9WBTVF8(MediaDataController r0, ArrayList r1, MessagesStorage.TopicKey r2) {
        r0.lambda$clearBotKeyboard$196(r1, r2);
    }

    /* renamed from: $r8$lambda$wrXfmJvxaW9l3T6mNNHaXq-OhqI, reason: not valid java name */
    public static /* synthetic */ void m4459$r8$lambda$wrXfmJvxaW9l3T6mNNHaXqOhqI(MediaDataController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadGroupStickerSet$47(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$x3indSW27duwDPRi0tmYTQGeSNE(MediaDataController r0, long r1) {
        r0.lambda$clearBotKeyboard$197(r1);
    }

    public static /* synthetic */ void $r8$lambda$x8dWzcUYTvFyICNC661cGgLayWQ(MediaDataController r0, TLObject r1) {
        r0.lambda$checkDefaultTopicIcons$82(r1);
    }

    /* renamed from: $r8$lambda$xBmJz-2Oigy5io5zDXW9YByhOM0, reason: not valid java name */
    public static /* synthetic */ void m4460$r8$lambda$xBmJz2Oigy5io5zDXW9YByhOM0(MediaDataController r0, TLRPC.messages_Messages r1, int r2, long r3, ArrayList r5, int r6, int r7, boolean r8, int r9, int r10) {
        r0.lambda$processLoadedMedia$136(r1, r2, r3, r5, r6, r7, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$xfSwQt4FL_cwDipbwNjWcOdZGKg(MediaDataController r0) {
        r0.lambda$loadPremiumPromo$7();
    }

    public static /* synthetic */ void $r8$lambda$xkhHStjyfIGfdy0IS0GXMCuefrs(MediaDataController r0, TLObject r1, SharedPreferences r2, boolean r3) {
        r0.lambda$loadAvatarConstructor$244(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$xlMDzw5lZE9j-cEKydaOUMwll9c, reason: not valid java name */
    public static /* synthetic */ void m4461$r8$lambda$xlMDzw5lZE9jcEKydaOUMwll9c(MediaDataController r0, TLRPC.TL_error r1, TLObject r2, SharedPreferences r3, boolean[] r4) {
        r0.lambda$loadRecentAndTopReactions$238(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$xrH5oeHMqUpRL0vvEqb3uiKm7vs(MediaDataController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$clearRecentStickers$19(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$xvNAmCWvBa0GUXVEv9YwmUus0D4(Utilities.Callback r0, ArrayList r1) {
        if (r0 == null) goto L5;
        r0.run(r1);
        return;
    }

    /* renamed from: $r8$lambda$xwEWwoewV1SdaQ5DuLjN9nC-NUY, reason: not valid java name */
    public static /* synthetic */ TLRPC.MessageEntity m4462$r8$lambda$xwEWwoewV1SdaQ5DuLjN9nCNUY(Void r0) {
        return new TLRPC.TL_messageEntityItalic();
    }

    public static /* synthetic */ void $r8$lambda$y33m_Bo151_krPfZ6QvUQgOT1R0(MediaDataController r0, String r1) {
        r0.lambda$fetchNewEmojiKeywords$212(r1);
    }

    /* renamed from: $r8$lambda$y9Odop3tNDV-MxhJH6dUOzeiCTM, reason: not valid java name */
    public static /* synthetic */ void m4463$r8$lambda$y9Odop3tNDVMxhJH6dUOzeiCTM(MediaDataController r0, boolean r1, ArrayList r2, int r3, long r4, int r6, Runnable r7) {
        r0.lambda$processLoadedStickers$108(r1, r2, r3, r4, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$yJQrXh3oiWH62O8EEEfq6rDhN8M(KeywordResultCallback r0, ArrayList r1, String r2) {
        r0.run(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$yNCvDVCrjGS4EaAhAgU1I0MeNrA(MediaDataController r0, int r1, boolean r2, Utilities.Callback r3) {
        r0.lambda$loadStickers$92(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$yWKDNT9tLf3cKEVxZr6KA4eWPto(MediaDataController r0, int r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadRecents$52(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$ybk13ydkw0x6tRUN2SeMGKQIgyo(MediaDataController r0, boolean r1, ArrayList r2, int r3, long r4, boolean r6, ArrayList r7, boolean r8) {
        r0.lambda$processLoadedFeaturedStickers$64(r1, r2, r3, r4, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$yewvlIu5hjxmoqHQvHUUHkULkM0(MediaDataController r0) {
        r0.lambda$loadDraftsIfNeed$188();
    }

    public static /* synthetic */ void $r8$lambda$zhqEPOObymLJy4FpHHGUHMD3j5o(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: -$$Nest$mprocessLoadedMedia, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4464$$Nest$mprocessLoadedMedia(MediaDataController r0, TLRPC.messages_Messages r1, long r2, int r4, int r5, int r6, int r7, long r8, int r10, int r11, boolean r12, boolean r13, int r14) {
        r0.processLoadedMedia(r1, r2, r4, r5, r6, r7, r8, r10, r11, r12, r13, r14);
    }

    static {
        BOLD_PATTERN = Pattern.compile("\\*\\*(.+?)\\*\\*");
        ITALIC_PATTERN = Pattern.compile("__(.+?)__");
        SPOILER_PATTERN = Pattern.compile("\\|\\|(.+?)\\|\\|");
        STRIKE_PATTERN = Pattern.compile("~~(.+?)~~");
        SHORTCUT_CATEGORY = "org.telegram.messenger.SHORTCUT_SHARE";
        Instance = new MediaDataController[16];
        lockObjects = new Object[16];
        int r2 = 0;
    L3:
        if (r2 >= 16) goto L5;
        lockObjects[r2] = new Object();
        r2 = r2 + 1;
        goto L3
    L5:
        SHORTCUT_TYPE_USER_OR_CHAT = 0;
        SHORTCUT_TYPE_ATTACHED_BOT = 1;
        entityComparator = new MediaDataController$$ExternalSyntheticLambda185();
    }

    public MediaDataController(int r13) {
        super(r13);
        this.attachMenuBots = new TLRPC.TL_attachMenuBots();
        this.reactionsList = new ArrayList();
        this.enabledReactionsList = new ArrayList();
        this.reactionsMap = new HashMap();
        this.stickerSets = new ArrayList[]{new ArrayList(), new ArrayList(), new ArrayList(0), new ArrayList(), new ArrayList(), new ArrayList()};
        this.stickersByIds = new LongSparseArray[]{new LongSparseArray(), new LongSparseArray(), new LongSparseArray(), new LongSparseArray(), new LongSparseArray(), new LongSparseArray()};
        this.stickerSetsById = new LongSparseArray();
        this.installedStickerSetsById = new LongSparseArray();
        this.installedForceStickerSetsById = new ArrayList();
        this.uninstalledForceStickerSetsById = new ArrayList();
        this.groupStickerSets = new LongSparseArray();
        this.stickerSetsByName = new ConcurrentHashMap(100, 1.0f, 1);
        this.stickerSetDefaultStatuses = null;
        this.stickerSetDefaultChannelStatuses = null;
        this.diceStickerSetsByEmoji = new HashMap();
        this.diceEmojiStickerSetsById = new LongSparseArray();
        this.loadingDiceStickerSets = new HashSet();
        this.removingStickerSetsUndos = new LongSparseArray();
        this.scheduledLoadStickers = new Runnable[7];
        this.loadingStickers = new boolean[7];
        this.stickersLoaded = new boolean[7];
        this.loadHash = new long[7];
        this.loadDate = new int[7];
        this.ringtoneUploaderHashMap = new HashMap();
        this.verifyingMessages = new HashMap();
        this.archivedStickersCount = new int[7];
        this.stickersByEmoji = new LongSparseArray();
        this.allStickers = new HashMap();
        this.allStickersFeatured = new HashMap();
        this.recentStickers = new ArrayList[]{new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList()};
        this.loadingRecentStickers = new boolean[9];
        this.recentStickersLoaded = new boolean[9];
        this.recentGifs = new ArrayList();
        this.loadFeaturedHash = new long[2];
        this.loadFeaturedDate = new int[2];
        this.featuredStickerSets = new ArrayList[]{new ArrayList(), new ArrayList()};
        this.featuredStickerSetsById = new LongSparseArray[]{new LongSparseArray(), new LongSparseArray()};
        this.unreadStickerSets = new ArrayList[]{new ArrayList(), new ArrayList()};
        this.readingStickerSets = new ArrayList[]{new ArrayList(), new ArrayList()};
        this.loadingFeaturedStickers = new boolean[2];
        this.featuredStickersLoaded = new boolean[2];
        this.defaultEmojiThemes = new ArrayList();
        this.premiumPreviewStickers = new ArrayList();
        this.emojiStatusesHash = new long[4];
        this.emojiStatuses = new ArrayList[4];
        this.emojiStatusesFetchDate = new Long[4];
        this.emojiStatusesFromCacheFetched = new boolean[4];
        this.emojiStatusesFetching = new boolean[4];
        this.loadingStickerSetsKeys = new HashSet();
        this.loadingStickerSets = new HashMap();
        this.messagesSearchCount = new int[]{0, 0};
        this.filteredMessagesSearchCount = new int[]{0, 0};
        this.messagesSearchEndReached = new boolean[]{false, false};
        this.searchResultMessages = new ArrayList();
        this.searchServerResultMessages = new ArrayList();
        this.searchLocalResultMessages = new ArrayList();
        this.searchServerResultMessagesMap = new SparseArray[]{new SparseArray(), new SparseArray()};
        this.deletedFromResultMessages = new ArrayList();
        this.hints = new ArrayList();
        this.inlineBots = new ArrayList();
        this.guestBots = new ArrayList();
        this.webapps = new ArrayList();
        this.shortcutCallbacks = new HashMap();
        this.loadingPinnedMessages = new LongSparseArray();
        this.draftsFolderIds = new LongSparseArray();
        this.drafts = new LongSparseArray();
        this.draftMessages = new LongSparseArray();
        this.botInfos = new HashMap();
        this.botDialogKeyboards = new LongSparseArray();
        this.botKeyboards = new HashMap();
        this.botKeyboardsByMids = new LongSparseArray();
        this.currentFetchingEmoji = new HashMap();
        this.fetchedEmoji = new HashSet();
        this.triedLoadingEmojipacks = false;
        this.recentReactions = new ArrayList();
        this.topReactions = new ArrayList();
        this.savedReactions = new ArrayList();
        this.draftVoicesLoaded = false;
        this.draftVoices = new LongSparseArray();
        this.loadingSearchStickersKeys = new HashMap();
        this.searchStickerResults = new android.util.LruCache(25);
        if (this.currentAccount != 0) goto L5;
        this.draftPreferences = ApplicationLoader.applicationContext.getSharedPreferences("drafts", 0);
    L6:
        ArrayList<TLRPC.Message> r0 = new ArrayList();
        Iterator<Map.Entry<String, ?>> r1 = this.draftPreferences.getAll().entrySet().iterator();
    L8:
        if (r1.hasNext() == false) goto L39;
        Map.Entry<String, ?> r2 = r1.next();
        String r4 = r2.getKey();     // Catch: Exception -> L41
        long r5 = Utilities.parseLong(r4).longValue();     // Catch: Exception -> L41
        SerializedData r7 = new SerializedData(Utilities.hexToBytes((String) r2.getValue()));     // Catch: Exception -> L41
        if (r4.startsWith("r_") == true) goto L25;
        boolean r22 = r4.startsWith("rt_");     // Catch: Exception -> L41
        if (r22 == true) goto L26;
        TLRPC.DraftMessage r23 = TLRPC.DraftMessage.TLdeserialize(r7, r7.readInt32(true), true);     // Catch: Exception -> L41
        if (r23 == null) goto L37;
        LongSparseArray<TLRPC.DraftMessage> r9 = this.drafts.get(r5);     // Catch: Exception -> L41
        if (r9 != null) goto L21;
        r9 = new LongSparseArray();     // Catch: Exception -> L41
        this.drafts.put(r5, r9);     // Catch: Exception -> L41
    L21:
        if (r4.startsWith("t_") == false) goto L23;
        long r42 = Utilities.parseLong(r4.substring(r4.lastIndexOf(95) + 1)).longValue();     // Catch: Exception -> L41
    L24:
        r9.put(r42, r23);     // Catch: Exception -> L41
        goto L37
    L23:
        r42 = 0;
    L37:
        r7.cleanup();     // Catch: Exception -> L41
    L26:
        TLRPC.Message r92 = TLRPC.Message.TLdeserialize(r7, r7.readInt32(true), true);     // Catch: Exception -> L41
        if (r92 == null) goto L37;
        r92.readAttachPath(r7, getUserConfig().clientUserId);     // Catch: Exception -> L41
        LongSparseArray<TLRPC.Message> r10 = this.draftMessages.get(r5);     // Catch: Exception -> L41
        if (r10 != null) goto L31;
        r10 = new LongSparseArray();     // Catch: Exception -> L41
        this.draftMessages.put(r5, r10);     // Catch: Exception -> L41
    L31:
        if (r22 == false) goto L33;
        int r24 = Utilities.parseInt(r4.substring(r4.lastIndexOf(95) + 1));     // Catch: Exception -> L41
    L34:
        r10.put(r24, r92);     // Catch: Exception -> L41
        if (r92.reply_to == null) goto L37;
        r0.add(r92);     // Catch: Exception -> L41
        goto L37
    L33:
        r24 = 0;
        goto L34
    L25:
        r22 = false;
        goto L26
    L39:
        loadRepliesOfDraftReplies(r0);
        loadStickersByEmojiOrName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME, false, true);
        loadEmojiThemes();
        loadRecentAndTopReactions(false);
        loadAvatarConstructor(false);
        loadAvatarConstructor(true);
        this.ringtoneDataStore = new RingtoneDataStore(this.currentAccount);
        this.menuBotsUpdateDate = getMessagesController().getMainSettings().getInt("menuBotsUpdateDate", 0);
        return;
    L5:
        this.draftPreferences = ApplicationLoader.applicationContext.getSharedPreferences("drafts" + this.currentAccount, 0);
        goto L6
    }

    public static void addAnimatedEmojiSpans(ArrayList<TLRPC.MessageEntity> r7, CharSequence r8, Paint.FontMetricsInt r9) {
        if ((r8 instanceof Spannable) == false) goto L27;
        if (r7 == null) goto L36;
        Spannable r82 = (Spannable) r8;
        int r2 = 0;
        AnimatedEmojiSpan[] r0 = (AnimatedEmojiSpan[]) r82.getSpans(0, r82.length(), AnimatedEmojiSpan.class);
        int r1 = 0;
    L8:
        if (r1 >= r0.length) goto L14;
        AnimatedEmojiSpan r3 = r0[r1];
        if (r3 == null) goto L12;
        r82.removeSpan(r3);
    L12:
        r1 = r1 + 1;
    L14:
        if (r2 >= r7.size()) goto L37;
        TLRPC.MessageEntity r02 = r7.get(r2);
        if ((r02 instanceof TLRPC.TL_messageEntityCustomEmoji) == false) goto L26;
        TLRPC.TL_messageEntityCustomEmoji r12 = (TLRPC.TL_messageEntityCustomEmoji) r02;
        int r32 = r02.offset;
        int r03 = r02.length + r32;
        if (r32 >= r03) goto L26;
        if (r03 > r82.length()) goto L26;
        if (r12.document == null) goto L24;
        AnimatedEmojiSpan r4 = new AnimatedEmojiSpan(r12.document, r9);
    L25:
        r4.local = r12.local;
        r82.setSpan(r4, r32, r03, 33);
        goto L26
    L24:
        r4 = new AnimatedEmojiSpan(r12.document_id, r9);
    L26:
        r2 = r2 + 1;
        goto L14
    L37:
        return;
    L36:
        return;
    }

    public static void addStyleToText(TextStyleSpan r11, int r12, int r13, Spannable r14, boolean r15) {
        CharacterStyle[] r0 = (CharacterStyle[]) r14.getSpans(r12, r13, CharacterStyle.class);     // Catch: Exception -> L55
        if (r0 != null) goto L5;
    L49:
        if (r11 == null) goto L65;
        if (r12 < r13) goto L52;
        return;
    L52:
        if (r12 >= r14.length()) goto L67;
        r14.setSpan(r11, r12, Math.min(r14.length(), r13), 33);     // Catch: Exception -> L55
        return;
    L67:
        return;
    L65:
        return;
    L5:
        if (r0.length <= 0) goto L49;
        int r2 = 0;
    L8:
        if (r2 >= r0.length) goto L49;
        CharacterStyle r3 = r0[r2];     // Catch: Exception -> L55
        if (r11 == null) goto L12;
        TextStyleSpan.TextStyleRun r4 = r11.getTextStyleRun();     // Catch: Exception -> L55
    L14:
        if ((r3 instanceof TextStyleSpan) == false) goto L17;
        TextStyleSpan.TextStyleRun r5 = ((TextStyleSpan) r3).getTextStyleRun();     // Catch: Exception -> L55
    L21:
        if (r5 == null) goto L48;
        int r6 = r14.getSpanStart(r3);     // Catch: Exception -> L55
        int r7 = r14.getSpanEnd(r3);     // Catch: Exception -> L55
        r14.removeSpan(r3);     // Catch: Exception -> L55
        if (r6 <= r12) goto L31;
        if (r13 <= r7) goto L31;
        r14.setSpan(createNewSpan(r3, r5, r4, r15), r6, r7, 33);     // Catch: Exception -> L55
        if (r11 == null) goto L29;
        r14.setSpan(new TextStyleSpan(new TextStyleSpan.TextStyleRun(r4)), r7, r13, 33);     // Catch: Exception -> L55
    L29:
        r13 = r6;
    L31:
        if (r6 > r12) goto L38;
        if (r6 == r12) goto L34;
        r14.setSpan(createNewSpan(r3, r5, null, r15), r6, r12, 33);     // Catch: Exception -> L55
    L34:
        if (r7 <= r12) goto L38;
        if (r11 == null) goto L37;
        r14.setSpan(createNewSpan(r3, r5, r4, r15), r12, Math.min(r7, r13), 33);     // Catch: Exception -> L55
    L37:
        int r9 = r7;
    L39:
        if (r7 < r13) goto L47;
        if (r7 == r13) goto L42;
        r14.setSpan(createNewSpan(r3, r5, null, r15), r13, r7, 33);     // Catch: Exception -> L55
    L42:
        if (r13 <= r6) goto L47;
        if (r7 > r12) goto L47;
        if (r11 == null) goto L46;
        r14.setSpan(createNewSpan(r3, r5, r4, r15), r6, Math.min(r7, r13), 33);     // Catch: Exception -> L55
    L46:
        r13 = r6;
    L47:
        r12 = r9;
    L38:
        r9 = r12;
    L48:
        r2 = r2 + 1;     // Catch: Exception -> L55
        goto L8
    L17:
        if ((r3 instanceof URLSpanReplacement) == false) goto L48;
        r5 = ((URLSpanReplacement) r3).getTextStyleRun();     // Catch: Exception -> L55
        if (r5 != null) goto L21;
        r5 = new TextStyleSpan.TextStyleRun();     // Catch: Exception -> L55
        goto L21
    L12:
        r4 = new TextStyleSpan.TextStyleRun();     // Catch: Exception -> L55
    L55:
        e = move-exception;
        FileLog.e(e);
    }

    public static void addTextStyleRuns(ArrayList<TLRPC.MessageEntity> r1, CharSequence r2, Spannable r3) {
        addTextStyleRuns(r1, r2, r3, -1);
    }

    public static void addTextStyleRuns(ArrayList<TLRPC.MessageEntity> r5, CharSequence r6, Spannable r7, int r8) {
        int r2 = 0;
        TextStyleSpan[] r0 = (TextStyleSpan[]) r7.getSpans(0, r7.length(), TextStyleSpan.class);
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        r7.removeSpan(r0[r3]);
        r3 = r3 + 1;
        goto L3
    L5:
        ArrayList<TextStyleSpan.TextStyleRun> r52 = getTextStyleRuns(r5, r6, r8);
    L7:
        if (r2 >= Math.min(1000, r52.size())) goto L9;
        TextStyleSpan.TextStyleRun r62 = r52.get(r2);
        addStyleToText(new TextStyleSpan(r62), r62.start, r62.end, r7, true);
        r2 = r2 + 1;
        goto L7
    }

    public static void addTextStyleRuns(MessageObject r2, Spannable r3) {
        addTextStyleRuns(r2.messageOwner.entities, r2.messageText, r3, -1);
    }

    public static void addTextStyleRuns(MessageObject r1, Spannable r2, int r3) {
        addTextStyleRuns(r1.messageOwner.entities, r1.messageText, r2, r3);
    }

    public static void addTextStyleRuns(TLRPC.DraftMessage r1, Spannable r2, int r3) {
        addTextStyleRuns(r1.entities, r1.message, r2, r3);
    }

    private ArrayList<MessageObject> broadcastPinnedMessage(ArrayList<TLRPC.Message> r14, ArrayList<TLRPC.User> r15, ArrayList<TLRPC.Chat> r16, boolean r17, boolean r18) {
        if (r14.isEmpty() == false) goto L5;
        return null;
    L5:
        LongSparseArray r7 = new LongSparseArray();
        int r1 = 0;
    L7:
        if (r1 >= r15.size()) goto L9;
        TLRPC.User r2 = r15.get(r1);
        r7.put(r2.id, r2);
        r1 = r1 + 1;
        goto L7
    L9:
        LongSparseArray r5 = new LongSparseArray();
        int r12 = 0;
    L11:
        if (r12 >= r16.size()) goto L13;
        TLRPC.Chat r22 = r16.get(r12);
        r5.put(r22.id, r22);
        r12 = r12 + 1;
        goto L11
    L13:
        ArrayList<MessageObject> r8 = new ArrayList();
        if (r18 == false) goto L30;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda75(this, r15, r17, r16));
        int r152 = r14.size();
        int r13 = 0;
        int r9 = 0;
    L16:
        if (r9 >= r152) goto L29;
        TLRPC.Message r3 = r14.get(r9);
        if ((MessageObject.getMedia(r3) instanceof TLRPC.TL_messageMediaDocument) == false) goto L20;
    L23:
        r13 = r13 + 1;
    L22:
        int r10 = r13;
        int r23 = this.currentAccount;
        if (r10 >= 30) goto L27;
        boolean r4 = true;
    L28:
        LongSparseArray r122 = r7;
        r7 = r122;
        r8.add(new MessageObject(r23, r3, r122, r5, false, r4));
        r9 = r9 + 1;
        r13 = r10;
        goto L16
    L27:
        r4 = false;
        goto L28
    L20:
        if ((MessageObject.getMedia(r3) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L22;
    L29:
        return r8;
    L30:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda76(this, r15, r17, r16, r14, r8, r7, r5));
        return null;
    }

    private void broadcastReplyMessages(ArrayList<TLRPC.Message> r14, LongSparseArray<SparseArray<ArrayList<MessageObject>>> r15, ArrayList<TLRPC.User> r16, ArrayList<TLRPC.Chat> r17, long r18, boolean r20) {
        LongSparseArray r3 = new LongSparseArray();
        int r1 = 0;
    L4:
        if (r1 >= r16.size()) goto L6;
        TLRPC.User r2 = r16.get(r1);
        r3.put(r2.id, r2);
        r1 = r1 + 1;
        goto L4
    L6:
        LongSparseArray r4 = new LongSparseArray();
        int r12 = 0;
    L8:
        if (r12 >= r17.size()) goto L10;
        TLRPC.Chat r22 = r17.get(r12);
        r4.put(r22.id, r22);
        r12 = r12 + 1;
        goto L8
    L10:
        ArrayList r9 = new ArrayList();
        int r10 = r14.size();
        int r11 = 0;
    L11:
        if (r11 >= r10) goto L13;
        r9.add(new MessageObject(this.currentAccount, r14.get(r11), r3, r4, false, false));
        r11 = r11 + 1;
        goto L11
    L13:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda139(this, r16, r20, r17, r9, r15, r18));
    }

    public static long calcDocumentsHash(ArrayList<TLRPC.Document> r2) {
        return calcDocumentsHash(r2, 200);
    }

    public static long calcDocumentsHash(ArrayList<TLRPC.Document> r5, int r6) {
        long r0 = 0;
        if (r5 != null) goto L5;
        return 0;
    L5:
        int r62 = Math.min(r6, r5.size());
        int r2 = 0;
    L6:
        if (r2 >= r62) goto L12;
        TLRPC.Document r3 = r5.get(r2);
        if (r3 == null) goto L11;
        r0 = calcHash(r0, r3.id);
    L11:
        r2 = r2 + 1;
        goto L6
    L12:
        return r0;
    }

    private long calcFeaturedStickersHash(boolean r8, ArrayList<TLRPC.StickerSetCovered> r9) {
        long r0 = 0;
        if (r9 != null) goto L5;
    L17:
        return r0;
    L5:
        if (r9.isEmpty() == true) goto L17;
        int r2 = 0;
    L9:
        if (r2 >= r9.size()) goto L17;
        TLRPC.StickerSet r3 = r9.get(r2).set;
        if (r3.archived == true) goto L16;
        r0 = calcHash(r0, r3.id);
        if (this.unreadStickerSets[r8 ? 1 : 0].contains(Long.valueOf(r3.id)) == false) goto L16;
        r0 = calcHash(r0, 1);
    L16:
        r2 = r2 + 1;
        goto L9
    }

    public static long calcHash(long r2, long r4) {
        long r22 = r2 ^ (r2 >>> 21);
        long r23 = r22 ^ (r22 << 35);
        return (r23 ^ (r23 >>> 4)) + r4;
    }

    private static long calcStickersHash(ArrayList<TLRPC.TL_messages_stickerSet> r5) {
        long r0 = 0;
        int r2 = 0;
    L4:
        if (r2 >= r5.size()) goto L13;
        if (r5.get(r2) == null) goto L12;
        if (r5.get(r2).set.archived == true) goto L12;
        r0 = calcHash(r0, r3.hash);
    L12:
        r2 = r2 + 1;
        goto L4
    L13:
        return r0;
    }

    public static boolean canAddMessageToMedia(TLRPC.Message r4) {
        boolean r0 = r4 instanceof TLRPC.TL_message_secret;
        if (r0 == true) goto L5;
    L15:
        if (r0 == true) goto L26;
        if ((r4 instanceof TLRPC.TL_message) == false) goto L26;
        if ((MessageObject.getMedia(r4) instanceof TLRPC.TL_messageMediaPhoto) == true) goto L23;
        if ((MessageObject.getMedia(r4) instanceof TLRPC.TL_messageMediaDocument) == false) goto L26;
    L23:
        if (MessageObject.getMedia(r4).ttl_seconds == 0) goto L26;
        return false;
    L26:
        if (getMediaType(r4) == (-1)) goto L29;
        return true;
    L29:
        return false;
    L5:
        if ((MessageObject.getMedia(r4) instanceof TLRPC.TL_messageMediaPhoto) == true) goto L11;
        if (MessageObject.isVideoMessage(r4) == true) goto L11;
        if (MessageObject.isGifMessage(r4) == false) goto L15;
    L11:
        if (MessageObject.getMedia(r4).ttl_seconds == 0) goto L15;
        if (MessageObject.getMedia(r4).ttl_seconds > 60) goto L15;
        return false;
    }

    public static boolean canShowAttachMenuBot(TLRPC.TL_attachMenuBot r10, TLObject r11) {
        TLRPC.Chat r1 = null;
        if ((r11 instanceof TLRPC.User) == false) goto L5;
        TLRPC.User r0 = (TLRPC.User) r11;
    L7:
        if ((r11 instanceof TLRPC.Chat) == false) goto L9;
        r1 = (TLRPC.Chat) r11;
    L9:
        ArrayList<TLRPC.AttachMenuPeerType> r112 = r10.peer_types;
        int r2 = r112.size();
        int r4 = 0;
    L10:
        if (r4 >= r2) goto L42;
        TLRPC.AttachMenuPeerType r5 = r112.get(r4);
        r4 = r4 + 1;
        TLRPC.AttachMenuPeerType r52 = r5;
        if ((r52 instanceof TLRPC.TL_attachMenuPeerTypeSameBotPM) == false) goto L19;
        if (r0 == null) goto L19;
        if (r0.bot == false) goto L19;
        if (r0.id != r10.bot_id) goto L19;
        return true;
    L19:
        if ((r52 instanceof TLRPC.TL_attachMenuPeerTypeBotPM) == false) goto L26;
        if (r0 == null) goto L26;
        if (r0.bot == false) goto L26;
        if (r0.id == r10.bot_id) goto L26;
        return true;
    L26:
        if ((r52 instanceof TLRPC.TL_attachMenuPeerTypePM) == false) goto L31;
        if (r0 == null) goto L31;
        if (r0.bot == true) goto L31;
        return true;
    L31:
        if ((r52 instanceof TLRPC.TL_attachMenuPeerTypeChat) == false) goto L36;
        if (r1 == null) goto L36;
        if (ChatObject.isChannelAndNotMegaGroup(r1) == true) goto L36;
        return true;
    L36:
        if ((r52 instanceof TLRPC.TL_attachMenuPeerTypeBroadcast) == false) goto L10;
        if (r1 == null) goto L10;
        if (ChatObject.isChannelAndNotMegaGroup(r1) == false) goto L10;
        return true;
    L42:
        return false;
    L5:
        r0 = null;
        goto L7
    }

    public static boolean canShowAttachMenuBotForTarget(TLRPC.TL_attachMenuBot r5, String r6) {
        ArrayList<TLRPC.AttachMenuPeerType> r52 = r5.peer_types;
        int r0 = r52.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L24;
        TLRPC.AttachMenuPeerType r3 = r52.get(r2);
        r2 = r2 + 1;
        TLRPC.AttachMenuPeerType r32 = r3;
        if ((r32 instanceof TLRPC.TL_attachMenuPeerTypeSameBotPM) == true) goto L9;
        if ((r32 instanceof TLRPC.TL_attachMenuPeerTypeBotPM) == true) goto L9;
    L11:
        if ((r32 instanceof TLRPC.TL_attachMenuPeerTypeBroadcast) == false) goto L15;
        if (r6.equals("channels") == false) goto L15;
        return true;
    L15:
        if ((r32 instanceof TLRPC.TL_attachMenuPeerTypeChat) == false) goto L19;
        if (r6.equals("groups") == false) goto L19;
        return true;
    L19:
        if ((r32 instanceof TLRPC.TL_attachMenuPeerTypePM) == false) goto L3;
        if (r6.equals("users") == false) goto L3;
        return true;
    L9:
        if (r6.equals("bots") == false) goto L11;
        return true;
    L24:
        return false;
    }

    private static boolean checkInclusion(int r5, List<TLRPC.MessageEntity> r6, boolean r7) {
        if (r6 != null) goto L5;
    L19:
        return false;
    L5:
        if (r6.isEmpty() == true) goto L19;
        int r1 = r6.size();
        int r2 = 0;
    L8:
        if (r2 >= r1) goto L19;
        TLRPC.MessageEntity r3 = r6.get(r2);
        int r4 = r3.offset;
        if (r7 == false) goto L13;
        if (r4 >= r5) goto L18;
    L15:
        if ((r3.offset + r3.length) <= r5) goto L18;
        return true;
    L18:
        r2 = r2 + 1;
        goto L8
    L13:
        if (r4 > r5) goto L18;
        goto L15
    }

    private static boolean checkIntersection(int r5, int r6, List<TLRPC.MessageEntity> r7) {
        if (r7 != null) goto L5;
    L16:
        return false;
    L5:
        if (r7.isEmpty() == true) goto L16;
        int r1 = r7.size();
        int r2 = 0;
    L8:
        if (r2 >= r1) goto L16;
        TLRPC.MessageEntity r3 = r7.get(r2);
        int r4 = r3.offset;
        if (r4 <= r5) goto L15;
        if ((r4 + r3.length) > r6) goto L15;
        return true;
    L15:
        r2 = r2 + 1;
        goto L8
    }

    private void cleanupStickerSetCache() {
        if (this.cleanedupStickerSetCache == false) goto L5;
        return;
    L5:
        this.cleanedupStickerSetCache = true;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda248(this));
    }

    private Intent createIntrnalAttachedBotShortcutIntent(long r5) {
        if (r5 != 0) goto L5;
    L11:
        return null;
    L5:
        if (canCreateAttachedMenuBotShortcut(r5) == false) goto L11;
        Intent r0 = new Intent(ApplicationLoader.applicationContext, OpenAttachedMenuBotReceiver.class);
        if (DialogObject.isUserDialog(r5) == false) goto L11;
        r0.putExtra("botId", r5);
        r0.putExtra("currentAccount", this.currentAccount);
        r0.setAction(OpenAttachedMenuBotReceiver.ACTION + r5);
        r0.addFlags(67108864);
        return r0;
    }

    private Intent createIntrnalShortcutIntent(long r5) {
        Intent r0 = new Intent(ApplicationLoader.applicationContext, OpenChatReceiver.class);
        if (DialogObject.isEncryptedDialog(r5) == false) goto L8;
        int r1 = DialogObject.getEncryptedChatId(r5);
        r0.putExtra("encId", r1);
        if (getMessagesController().getEncryptedChat(Integer.valueOf(r1)) != null) goto L13;
        return null;
    L13:
        r0.putExtra("currentAccount", this.currentAccount);
        r0.setAction("com.tmessages.openchat" + r5);
        r0.addFlags(67108864);
        return r0;
    L8:
        if (DialogObject.isUserDialog(r5) == false) goto L11;
        r0.putExtra("userId", r5);
        goto L13
    L11:
        if (DialogObject.isChatDialog(r5) == false) goto L15;
        r0.putExtra("chatId", -r5);
        goto L13
    L15:
        return null;
    }

    private static CharacterStyle createNewSpan(CharacterStyle r1, TextStyleSpan.TextStyleRun r2, TextStyleSpan.TextStyleRun r3, boolean r4) {
        TextStyleSpan.TextStyleRun r0 = new TextStyleSpan.TextStyleRun(r2);
        if (r3 == null) goto L8;
        if (r4 == false) goto L6;
        r0.merge(r3);
        goto L8
    L6:
        r0.replace(r3);
    L8:
        if ((r1 instanceof TextStyleSpan) == false) goto L12;
        return new TextStyleSpan(r0);
    L12:
        if ((r1 instanceof URLSpanReplacement) == true) goto L14;
        return null;
    L14:
        return new URLSpanReplacement(((URLSpanReplacement) r1).getURL(), r0);
    }

    private void deletePeer(long r3, int r5) {
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda210(this, r3, r5));
    }

    public static boolean entitiesEqual(ArrayList<TLRPC.MessageEntity> r4, ArrayList<TLRPC.MessageEntity> r5) {
        if (r4.size() == r5.size()) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= r4.size()) goto L12;
        if (entitiesEqual(r4.get(r0), r5.get(r0)) == false) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return false;
    L12:
        return true;
    }

    public static boolean entitiesEqual(TLRPC.MessageEntity r5, TLRPC.MessageEntity r6) {
        if (r5.getClass() == r6.getClass()) goto L5;
    L30:
        return false;
    L5:
        if (r5.offset != r6.offset) goto L30;
        if (r5.length != r6.length) goto L30;
        if (TextUtils.equals(r5.url, r6.url) == false) goto L30;
        if (TextUtils.equals(r5.language, r6.language) == false) goto L30;
        if ((r5 instanceof TLRPC.TL_inputMessageEntityMentionName) == false) goto L19;
        if (((TLRPC.TL_inputMessageEntityMentionName) r5).user_id == ((TLRPC.TL_inputMessageEntityMentionName) r6).user_id) goto L19;
        return false;
    L19:
        if ((r5 instanceof TLRPC.TL_messageEntityMentionName) == false) goto L24;
        if (((TLRPC.TL_messageEntityMentionName) r5).user_id == ((TLRPC.TL_messageEntityMentionName) r6).user_id) goto L24;
        return false;
    L24:
        if ((r5 instanceof TLRPC.TL_messageEntityCustomEmoji) == true) goto L26;
        return true;
    L26:
        if (((TLRPC.TL_messageEntityCustomEmoji) r5).document_id == ((TLRPC.TL_messageEntityCustomEmoji) r6).document_id) goto L31;
        return false;
    L31:
        return true;
    }

    private void fetchStickerSetInternal(TLRPC.InputStickerSet r4, Utilities.Callback2<Boolean, TLRPC.TL_messages_stickerSet> r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        String r0 = inputSetKey(r4);
        ArrayList<Utilities.Callback2<Boolean, TLRPC.TL_messages_stickerSet>> r1 = this.loadingStickerSets.get(r0);
        if (r1 != null) goto L7;
    L10:
        if (r1 != null) goto L12;
        HashMap<String, ArrayList<Utilities.Callback2<Boolean, TLRPC.TL_messages_stickerSet>>> r12 = this.loadingStickerSets;
        ArrayList<Utilities.Callback2<Boolean, TLRPC.TL_messages_stickerSet>> r2 = new ArrayList();
        r12.put(r0, r2);
        r1 = r2;
    L12:
        r1.add(r5);
        TLRPC.TL_messages_getStickerSet r52 = new TLRPC.TL_messages_getStickerSet();
        r52.stickerset = r4;
        getConnectionsManager().sendRequest(r52, new MediaDataController$$ExternalSyntheticLambda93(this, r0));
        return;
    L7:
        if (r1.size() <= 0) goto L10;
        r1.add(r5);
    }

    public static TLRPC.TL_attachMenuBotIcon getAnimatedAttachMenuBotIcon(TLRPC.TL_attachMenuBot r5, boolean r6) {
        ArrayList<TLRPC.TL_attachMenuBotIcon> r52 = r5.icons;
        int r0 = r52.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        TLRPC.TL_attachMenuBotIcon r2 = r52.get(r1);
        r1 = r1 + 1;
        TLRPC.TL_attachMenuBotIcon r22 = r2;
        String r3 = r22.name;
        if (r6 == false) goto L7;
        String r4 = ATTACH_MENU_BOT_ANIMATED_ICON_KEY_2;
    L9:
        if (r3.equals(r4) == false) goto L3;
        return r22;
    L7:
        r4 = ATTACH_MENU_BOT_ANIMATED_ICON_KEY;
        goto L9
    L11:
        return null;
    }

    private TLRPC.TL_messages_stickerSet getCachedStickerSetInternal(long r7, Integer r9) {
        NativeByteBuffer r0 = null;
        SQLiteCursor r6 = getMessagesStorage().getDatabase().queryFinalized("SELECT data, hash, date FROM stickersets2 WHERE id = ? LIMIT 1", new Object[]{Long.valueOf(r7)});     // Catch: Throwable -> L41
    L34:
        th = th;
        TLRPC.TL_messages_stickerSet r72 = null;
    L61:
        FileLog.e(th);     // Catch: Throwable -> L49
        if (r0 == null) goto L46;
        r0.reuse();
    L46:
        if (r6 == null) goto L48;
        r6.dispose();
    L48:
        return r72;
    L49:
        th = move-exception;
        if (r0 == null) goto L52;
        r0.reuse();
    L52:
        if (r6 == null) goto L54;
        r6.dispose();
    L54:
        throw th;
    L5:
        if (r6.next() == true) goto L7;
    L36:
        r72 = null;
    L37:
        if (r0 == null) goto L39;
        r0.reuse();
    L39:
        r6.dispose();
        return r72;
    L7:
        if (r6.isNull(0) == true) goto L36;
        NativeByteBuffer r8 = r6.byteBufferValue(0);     // Catch: Throwable -> L34
        if (r8 != null) goto L57;
        r72 = null;
    L30:
        r0 = r8;
        goto L37
    L57:
        r72 = TLRPC.messages_StickerSet.TLdeserialize(r8, r8.readInt32(false), false);     // Catch: Throwable -> L31
        int r1 = r6.intValue(1);     // Catch: Throwable -> L20
        long r2 = r6.longValue(2);     // Catch: Throwable -> L20
        if (r9 != null) goto L15;
    L22:
        if (r9 != null) goto L24;
    L25:
        if (r72 == null) goto L30;
        if ((System.currentTimeMillis() - r2) <= Duration.ofMinutes(15).toMillis()) goto L30;
    L18:
        r8.reuse();
        r6.dispose();
        return null;
    L24:
        if (r9.intValue() != 0) goto L30;
    L15:
        if (r9.intValue() == 0) goto L22;
        if (r9.intValue() == r1) goto L22;
    L20:
        th = th;
    L21:
        r0 = r8;
    L31:
        th = th;
        r72 = null;
    L41:
        th = th;
        r6 = null;
        r72 = null;
        goto L61
    }

    private TLRPC.TL_messages_stickerSet getCachedStickerSetInternal(String r8, Integer r9) {
        NativeByteBuffer r0 = null;
        SQLiteCursor r7 = getMessagesStorage().getDatabase().queryFinalized("SELECT data, hash, date FROM stickersets2 WHERE short_name = ? LIMIT 1", new Object[]{r8});     // Catch: Throwable -> L41
    L34:
        th = th;
        TLRPC.TL_messages_stickerSet r82 = null;
    L59:
        FileLog.e(th);     // Catch: Throwable -> L49
        if (r0 == null) goto L46;
        r0.reuse();
    L46:
        if (r7 == null) goto L48;
        r7.dispose();
    L48:
        return r82;
    L49:
        th = move-exception;
        if (r0 == null) goto L52;
        r0.reuse();
    L52:
        if (r7 == null) goto L54;
        r7.dispose();
    L54:
        throw th;
    L5:
        if (r7.next() == true) goto L7;
    L36:
        r82 = null;
    L37:
        if (r0 == null) goto L39;
        r0.reuse();
    L39:
        r7.dispose();
        return r82;
    L7:
        if (r7.isNull(0) == true) goto L36;
        NativeByteBuffer r1 = r7.byteBufferValue(0);     // Catch: Throwable -> L34
        if (r1 != null) goto L55;
        r82 = null;
    L30:
        r0 = r1;
        goto L37
    L55:
        r82 = TLRPC.messages_StickerSet.TLdeserialize(r1, r1.readInt32(false), false);     // Catch: Throwable -> L31
        int r2 = r7.intValue(1);     // Catch: Throwable -> L20
        long r3 = r7.longValue(2);     // Catch: Throwable -> L20
        if (r9 != null) goto L15;
    L22:
        if (r9 != null) goto L24;
    L25:
        if (r82 == null) goto L30;
        if ((System.currentTimeMillis() - r3) <= Duration.ofMinutes(15).toMillis()) goto L30;
    L18:
        r1.reuse();
        r7.dispose();
        return null;
    L24:
        if (r9.intValue() != 0) goto L30;
    L15:
        if (r9.intValue() == 0) goto L22;
        if (r9.intValue() == r2) goto L22;
    L20:
        th = th;
    L21:
        r0 = r1;
    L31:
        th = th;
        r82 = null;
    L41:
        th = th;
        r7 = null;
        r82 = null;
        goto L59
    }

    public static TLRPC.InputStickerSet getInputStickerSet(TLRPC.Document r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.attributes.size()) goto L12;
        TLRPC.DocumentAttribute r1 = r4.attributes.get(r0);
        if ((r1 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        TLRPC.InputStickerSet r42 = r1.stickerset;
        if ((r42 instanceof TLRPC.TL_inputStickerSetEmpty) == false) goto L10;
        return null;
    L10:
        return r42;
    L12:
        return null;
    }

    public static TLRPC.InputStickerSet getInputStickerSet(TLRPC.StickerSet r3) {
        if (r3 == null) goto L5;
        TLRPC.TL_inputStickerSetID r0 = new TLRPC.TL_inputStickerSetID();
        r0.id = r3.id;
        r0.access_hash = r3.access_hash;
        return r0;
    L5:
        return null;
    }

    public static TLRPC.TL_inputStickerSetItem getInputStickerSetItem(TLRPC.Document r4, String r5) {
        TLRPC.TL_inputStickerSetItem r0 = new TLRPC.TL_inputStickerSetItem();
        TLRPC.TL_inputDocument r1 = new TLRPC.TL_inputDocument();
        r0.document = r1;
        r1.id = r4.id;
        r1.access_hash = r4.access_hash;
        r1.file_reference = r4.file_reference;
        r0.emoji = r5;
        return r0;
    }

    public static MediaDataController getInstance(int r3) {
        MediaDataController r0 = Instance[r3];
        if (r0 != null) goto L15;
        Object[] r1 = lockObjects;
        monitor-enter(r1);
        MediaDataController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        MediaDataController[] r03 = Instance;     // Catch: Throwable -> L9
        MediaDataController r2 = new MediaDataController(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(r1);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    L15:
        return r0;
    }

    private void getMediaCountDatabase(long r10, long r12, int r14, int r15) {
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda176(this, r12, r10, r14, r15));
    }

    public static int getMediaType(TLRPC.Message r12) {
        if (r12 != null) goto L5;
        return -1;
    L5:
        TLRPC.MessageMedia r1 = MessageObject.getMedia(r12);
        if ((r1 instanceof TLRPC.TL_messageMediaPoll) == false) goto L9;
        return 8;
    L9:
        int r3 = 0;
        if ((r1 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L13;
        return 0;
    L13:
        if ((r1 instanceof TLRPC.TL_messageMediaDocument) == false) goto L52;
        TLRPC.Document r122 = r1.document;
        if (r122 != null) goto L17;
        return -1;
    L17:
        int r13 = 0;
        boolean r2 = false;
        boolean r4 = false;
        boolean r5 = false;
        boolean r6 = false;
        boolean r7 = false;
        boolean r8 = false;
    L19:
        if (r13 >= r122.attributes.size()) goto L33;
        TLRPC.DocumentAttribute r9 = r122.attributes.get(r13);
        if ((r9 instanceof TLRPC.TL_documentAttributeVideo) == false) goto L24;
        r2 = r9.round_message;
        r5 = !r2;
        r4 = r2;
    L32:
        r13 = r13 + 1;
        goto L19
    L24:
        if ((r9 instanceof TLRPC.TL_documentAttributeAnimated) == false) goto L27;
        r6 = true;
        goto L32
    L27:
        if ((r9 instanceof TLRPC.TL_documentAttributeAudio) == false) goto L30;
        r2 = r9.voice;
        r8 = !r2;
        goto L32
    L30:
        if ((r9 instanceof TLRPC.TL_documentAttributeSticker) == false) goto L32;
        r7 = true;
        goto L32
    L33:
        if (r2 == true) goto L49;
        if (r4 == true) goto L79;
        if (r5 == false) goto L40;
        if (r6 == true) goto L40;
        if (r7 == true) goto L40;
        return 0;
    L40:
        if (r7 == false) goto L42;
        return -1;
    L42:
        if (r6 == false) goto L45;
        return 5;
    L45:
        if (r8 == false) goto L48;
        return 4;
    L48:
        return 1;
    L79:
        return 2;
    L49:
        return 2;
    L52:
        if (r12.entities.isEmpty() == false) goto L54;
    L68:
        return -1;
    L54:
        if (LocaleUtils.isCustomEmojiOnlyLinkMessage(r12) == false) goto L57;
        return -1;
    L57:
        if (r3 >= r12.entities.size()) goto L68;
        TLRPC.MessageEntity r14 = r12.entities.get(r3);
        if ((r14 instanceof TLRPC.TL_messageEntityUrl) == true) goto L66;
        if ((r14 instanceof TLRPC.TL_messageEntityTextUrl) == true) goto L80;
        if ((r14 instanceof TLRPC.TL_messageEntityEmail) == true) goto L81;
        r3 = r3 + 1;
        goto L57
    L81:
        return 3;
    L80:
        return 3;
    L66:
        return 3;
    }

    public static TLRPC.TL_attachMenuBotIcon getPlaceholderStaticAttachMenuBotIcon(TLRPC.TL_attachMenuBot r5) {
        ArrayList<TLRPC.TL_attachMenuBotIcon> r52 = r5.icons;
        int r0 = r52.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        TLRPC.TL_attachMenuBotIcon r2 = r52.get(r1);
        r1 = r1 + 1;
        TLRPC.TL_attachMenuBotIcon r22 = r2;
        if (r22.name.equals(ATTACH_MENU_BOT_PLACEHOLDER_STATIC_KEY) == false) goto L3;
        return r22;
    L7:
        return null;
    }

    public static TLRPC.TL_attachMenuBotIcon getSideAttachMenuBotIcon(TLRPC.TL_attachMenuBot r5) {
        ArrayList<TLRPC.TL_attachMenuBotIcon> r52 = r5.icons;
        int r0 = r52.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        TLRPC.TL_attachMenuBotIcon r2 = r52.get(r1);
        r1 = r1 + 1;
        TLRPC.TL_attachMenuBotIcon r22 = r2;
        if (r22.name.equals("android_side_menu_static") == false) goto L3;
        return r22;
    L7:
        return null;
    }

    public static TLRPC.TL_attachMenuBotIcon getSideMenuBotIcon(TLRPC.TL_attachMenuBot r5) {
        ArrayList<TLRPC.TL_attachMenuBotIcon> r52 = r5.icons;
        int r0 = r52.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        TLRPC.TL_attachMenuBotIcon r2 = r52.get(r1);
        r1 = r1 + 1;
        TLRPC.TL_attachMenuBotIcon r22 = r2;
        if (r22.name.equals("android_side_menu_static") == false) goto L3;
        return r22;
    L7:
        return null;
    }

    public static TLRPC.TL_attachMenuBotIcon getStaticAttachMenuBotIcon(TLRPC.TL_attachMenuBot r5) {
        ArrayList<TLRPC.TL_attachMenuBotIcon> r52 = r5.icons;
        int r0 = r52.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        TLRPC.TL_attachMenuBotIcon r2 = r52.get(r1);
        r1 = r1 + 1;
        TLRPC.TL_attachMenuBotIcon r22 = r2;
        if (r22.name.equals(ATTACH_MENU_BOT_STATIC_ICON_KEY) == false) goto L3;
        return r22;
    L7:
        return null;
    }

    public static long getStickerSetId(TLRPC.Document r5) {
        if (r5 != null) goto L5;
        return -1;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= r5.attributes.size()) goto L22;
        TLRPC.DocumentAttribute r3 = r5.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L14;
        if ((r3 instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L14;
        r2 = r2 + 1;
    L14:
        TLRPC.InputStickerSet r52 = r3.stickerset;
        if ((r52 instanceof TLRPC.TL_inputStickerSetID) == true) goto L17;
        return -1;
    L17:
        return r52.id;
    L22:
        return -1;
    }

    public static ArrayList<TextStyleSpan.TextStyleRun> getTextStyleRuns(ArrayList<TLRPC.MessageEntity> r2, CharSequence r3, int r4) {
        if (r2 != null) goto L4;
    L7:
        ArrayList<TextStyleSpan.TextStyleRun> r0 = getTextStyleRunsLegacy(r2, r3, r4);
        if (r0 == null) goto L11;
        return r0;
    L11:
        return getTextStyleRunsSafe(r2, r3, r4);
    L4:
        if (r2.size() <= 100) goto L7;
        return getTextStyleRunsSafe(r2, r3, r4);
    }

    private static ArrayList<TextStyleSpan.TextStyleRun> getTextStyleRunsLegacy(ArrayList<TLRPC.MessageEntity> r16, CharSequence r17, int r18) {
        if (r16 == null) goto L120;
        if (r16.isEmpty() == true) goto L120;
        if (r17 == null) goto L120;
        if (r17.length() == 0) goto L120;
        if (r16.size() > 1000) goto L120;
        ArrayList r1 = new ArrayList(r16.size());
        int r2 = r16.size();
        int r3 = 0;
        int r4 = 0;
    L13:
        if (r4 >= r2) goto L18;
        TLRPC.MessageEntity r5 = r16.get(r4);
        if (r5 == null) goto L17;
        r1.add(r5);
    L17:
        r4 = r4 + 1;
        goto L13
    L18:
        Collections.sort(r1, new MediaDataController$$ExternalSyntheticLambda156());
        ArrayList<TextStyleSpan.TextStyleRun> r0 = new ArrayList();
        int r22 = r1.size();
        int r42 = 0;
        int r52 = 0;
    L20:
        if (r42 >= r22) goto L107;
        TLRPC.MessageEntity r7 = (TLRPC.MessageEntity) r1.get(r42);
        if (r7.length <= 0) goto L106;
        int r8 = r7.offset;
        if (r8 < 0) goto L106;
        if (r8 >= r17.length()) goto L106;
        if ((r7 instanceof TLRPC.TL_messageEntityCustomEmoji) == true) goto L106;
        TextStyleSpan.TextStyleRun r82 = new TextStyleSpan.TextStyleRun();
        int r9 = r7.offset;
        r82.start = r9;
        r82.end = r9 + Math.min(r7.length, r17.length() - r82.start);
        if ((r7 instanceof TLRPC.TL_messageEntitySpoiler) == false) goto L34;
        r82.flags = 256;
    L59:
        if ((r7 instanceof TLRPC.TL_messageEntityTextUrl) == false) goto L61;
        r82.flags |= 1024;
    L61:
        r82.flags &= r18;
        int r72 = r0.size();
        int r92 = 0;
    L63:
        if (r92 >= r72) goto L101;
        r52 = r52 + 1;
        if (r52 > 10000) goto L66;
        TextStyleSpan.TextStyleRun r12 = r0.get(r92);
        int r13 = r82.start;
        int r14 = r12.start;
        if (r13 <= r14) goto L83;
        int r142 = r12.end;
        if (r13 >= r142) goto L99;
        if (r82.end >= r142) goto L79;
        if (r0.size() > 98) goto L76;
        TextStyleSpan.TextStyleRun r11 = new TextStyleSpan.TextStyleRun(r82);
        r11.merge(r12);
        r0.add(r92 + 1, r11);
        TextStyleSpan.TextStyleRun r112 = new TextStyleSpan.TextStyleRun(r12);
        r112.start = r82.end;
        r92 = r92 + 2;
        r0.add(r92, r112);
        r72 = r72 + 2;
    L82:
        int r113 = r82.start;
        r82.start = r12.end;
        r12.end = r113;
        goto L99
    L76:
        return null;
    L79:
        if (r0.size() >= 100) goto L80;
        TextStyleSpan.TextStyleRun r114 = new TextStyleSpan.TextStyleRun(r82);
        r114.merge(r12);
        r114.end = r12.end;
        r92 = r92 + 1;
        r0.add(r92, r114);
        r72 = r72 + 1;
        goto L82
    L80:
        return null;
    L99:
        r92 = r92 + 1;
        goto L63
    L83:
        int r132 = r82.end;
        if (r14 >= r132) goto L99;
        int r15 = r12.end;
        if (r132 != r15) goto L89;
        r12.merge(r82);
    L98:
        r82.end = r14;
        goto L99
    L89:
        if (r132 >= r15) goto L95;
        if (r0.size() >= 100) goto L92;
        TextStyleSpan.TextStyleRun r115 = new TextStyleSpan.TextStyleRun(r12);
        r115.merge(r82);
        r115.end = r82.end;
        r92 = r92 + 1;
        r0.add(r92, r115);
        r72 = r72 + 1;
        r12.start = r82.end;
        goto L98
    L92:
        return null;
    L95:
        if (r0.size() >= 100) goto L96;
        TextStyleSpan.TextStyleRun r116 = new TextStyleSpan.TextStyleRun(r82);
        r116.start = r12.end;
        r92 = r92 + 1;
        r0.add(r92, r116);
        r72 = r72 + 1;
        r12.merge(r82);
        goto L98
    L96:
        return null;
    L66:
        return null;
    L101:
        if (r82.start >= r82.end) goto L106;
        if (r0.size() >= 100) goto L104;
        r0.add(r82);
        goto L106
    L104:
        return null;
    L34:
        if ((r7 instanceof TLRPC.TL_messageEntityStrike) == false) goto L37;
        r82.flags = 8;
        goto L59
    L37:
        if ((r7 instanceof TLRPC.TL_messageEntityUnderline) == false) goto L40;
        r82.flags = 16;
        goto L59
    L40:
        if ((r7 instanceof TLRPC.TL_messageEntityBold) == false) goto L43;
        r82.flags = 1;
        goto L59
    L43:
        if ((r7 instanceof TLRPC.TL_messageEntityItalic) == false) goto L46;
        r82.flags = 2;
        goto L59
    L46:
        if ((r7 instanceof TLRPC.TL_messageEntityCode) == false) goto L48;
    L57:
        r82.flags = 4;
        goto L59
    L48:
        if ((r7 instanceof TLRPC.TL_messageEntityPre) == true) goto L57;
        if ((r7 instanceof TLRPC.TL_messageEntityMentionName) == false) goto L54;
        r82.flags = 64;
        r82.urlEntity = r7;
        goto L59
    L54:
        if ((r7 instanceof TLRPC.TL_inputMessageEntityMentionName) == false) goto L56;
        r82.flags = 64;
        r82.urlEntity = r7;
        goto L59
    L56:
        r82.flags = 128;
        r82.urlEntity = r7;
    L106:
        r42 = r42 + 1;
        goto L20
    L107:
        int r19 = r0.size();
    L108:
        if (r3 >= r19) goto L118;
        TextStyleSpan.TextStyleRun r23 = r0.get(r3);
        int r43 = r23.start;
        if (r43 < 0) goto L117;
        int r24 = r23.end;
        if (r43 >= r24) goto L117;
        if (r24 > r17.length()) goto L117;
        r3 = r3 + 1;
    L117:
        return null;
    L118:
        return r0;
    L120:
        return new ArrayList();
    }

    private static ArrayList<TextStyleSpan.TextStyleRun> getTextStyleRunsSafe(ArrayList<TLRPC.MessageEntity> r16, CharSequence r17, int r18) {
        ArrayList<TextStyleSpan.TextStyleRun> r1 = new ArrayList();
        if (r16 != null) goto L5;
    L109:
        return r1;
    L5:
        if (r16.isEmpty() == true) goto L109;
        if (r17 == null) goto L109;
        if (r17.length() == 0) goto L109;
        if (r16.size() > 1000) goto L109;
        ArrayList r2 = new ArrayList(r16.size());
        int r4 = r16.size();
        int r5 = 0;
        int r6 = 0;
    L13:
        if (r6 >= r4) goto L18;
        TLRPC.MessageEntity r7 = r16.get(r6);
        if (r7 == null) goto L17;
        r2.add(r7);
    L17:
        r6 = r6 + 1;
        goto L13
    L18:
        Collections.sort(r2, new MediaDataController$$ExternalSyntheticLambda89());
        ArrayList r0 = new ArrayList(r2.size());
        int[] r42 = new int[r2.size() * 2];
        int r72 = r2.size();
        int r8 = 0;
        int r9 = 0;
    L20:
        if (r8 >= r72) goto L64;
        TLRPC.MessageEntity r11 = (TLRPC.MessageEntity) r2.get(r8);
        if (r11.length <= 0) goto L63;
        int r12 = r11.offset;
        if (r12 < 0) goto L63;
        if (r12 >= r17.length()) goto L63;
        int r122 = r11.offset;
        int r13 = Math.min(r11.length, r17.length() - r122) + r122;
        if ((r11 instanceof TLRPC.TL_messageEntityCustomEmoji) == true) goto L63;
        TextStyleSpan.TextStyleRun r14 = new TextStyleSpan.TextStyleRun();
        r14.start = r122;
        r14.end = r13;
        if ((r11 instanceof TLRPC.TL_messageEntitySpoiler) == false) goto L35;
        r14.flags = 256;
    L60:
        if ((r11 instanceof TLRPC.TL_messageEntityTextUrl) == false) goto L62;
        r14.flags |= 1024;
    L62:
        r14.flags &= r18;
        r0.add(r14);
        int r10 = r9 + 1;
        r42[r9] = r122;
        r9 = r9 + 2;
        r42[r10] = r13;
        goto L63
    L35:
        if ((r11 instanceof TLRPC.TL_messageEntityStrike) == false) goto L38;
        r14.flags = 8;
        goto L60
    L38:
        if ((r11 instanceof TLRPC.TL_messageEntityUnderline) == false) goto L41;
        r14.flags = 16;
        goto L60
    L41:
        if ((r11 instanceof TLRPC.TL_messageEntityBold) == false) goto L44;
        r14.flags = 1;
        goto L60
    L44:
        if ((r11 instanceof TLRPC.TL_messageEntityItalic) == false) goto L47;
        r14.flags = 2;
        goto L60
    L47:
        if ((r11 instanceof TLRPC.TL_messageEntityCode) == false) goto L49;
    L58:
        r14.flags = 4;
        goto L60
    L49:
        if ((r11 instanceof TLRPC.TL_messageEntityPre) == true) goto L58;
        if ((r11 instanceof TLRPC.TL_messageEntityMentionName) == false) goto L55;
        r14.flags = 64;
        r14.urlEntity = r11;
        goto L60
    L55:
        if ((r11 instanceof TLRPC.TL_inputMessageEntityMentionName) == false) goto L57;
        r14.flags = 64;
        r14.urlEntity = r11;
        goto L60
    L57:
        r14.flags = 128;
        r14.urlEntity = r11;
    L63:
        r8 = r8 + 1;
        goto L20
    L64:
        if (r9 == 0) goto L109;
        Arrays.sort(r42, 0, r9);
        int r22 = 0;
    L67:
        if (r22 >= r9) goto L109;
        int r62 = r42[r22];
    L69:
        if (r22 >= r9) goto L73;
        if (r42[r22] != r62) goto L73;
        r22 = r22 + 1;
    L73:
        if (r22 >= r9) goto L109;
        int r73 = r42[r22];
        int r82 = r0.size();
        TextStyleSpan.TextStyleRun r112 = null;
        int r123 = Integer.MIN_VALUE;
        int r132 = r5;
    L76:
        if (r132 >= r82) goto L92;
        TextStyleSpan.TextStyleRun r142 = (TextStyleSpan.TextStyleRun) r0.get(r132);
        if (r142.start > r62) goto L91;
        if (r142.end < r73) goto L91;
        if (r112 != null) goto L84;
        r112 = new TextStyleSpan.TextStyleRun();
        r112.start = r62;
        r112.end = r73;
    L84:
        r112.flags = r142.flags | r112.flags;
        TLRPC.MessageEntity r52 = r142.urlEntity;
        if (r52 == null) goto L91;
        if (r112.urlEntity != null) goto L89;
    L90:
        r112.urlEntity = r52;
        r123 = r142.start;
        goto L91
    L89:
        if (r142.start > r123) goto L90;
    L91:
        r132 = r132 + 1;
        goto L76
    L92:
        if (r112 == null) goto L108;
        if (r1.isEmpty() == true) goto L104;
        TextStyleSpan.TextStyleRun r53 = r1.get(r1.size() - 1);
        if (r53.end != r112.start) goto L104;
        if (r53.flags != r112.flags) goto L104;
        if (r53.urlEntity != r112.urlEntity) goto L104;
        r53.end = r112.end;
    L104:
        if (r1.size() >= 1000) goto L105;
        r1.add(r112);
        goto L108
    L105:
        r1.clear();
        return r1;
    L108:
        r5 = 0;
        goto L67
    }

    private void increaseInlineRating(long r13, boolean r15) {
        if (getUserConfig().suggestContacts == true) goto L5;
        return;
    L5:
        UserConfig r0 = getUserConfig();
        if (r15 == false) goto L8;
        int r02 = r0.botGuestRatingLoadTime;
    L9:
        int r1 = 1;
        if (r02 == 0) goto L12;
        int r03 = Math.max(1, ((int) (System.currentTimeMillis() / 1000)) - r02);
    L13:
        if (r15 == false) goto L15;
        ArrayList<TLRPC.TL_topPeer> r2 = this.guestBots;
    L16:
        int r4 = 0;
    L18:
        if (r4 >= r2.size()) goto L23;
        TLRPC.TL_topPeer r5 = r2.get(r4);
        if (r5.peer.user_id == r13) goto L24;
        r4 = r4 + 1;
    L24:
        if (r5 != null) goto L26;
        r5 = new TLRPC.TL_topPeer();
        TLRPC.TL_peerUser r42 = new TLRPC.TL_peerUser();
        r5.peer = r42;
        r42.user_id = r13;
        r2.add(r5);
    L26:
        r5.rating += Math.exp(r03 / getMessagesController().ratingDecay);
        Collections.sort(r2, new MediaDataController$$ExternalSyntheticLambda77());
        if (r2.size() <= 20) goto L29;
        r2.remove(r2.size() - 1);
    L29:
        if (r15 == false) goto L31;
        r1 = 3;
    L31:
        savePeer(r13, r1, r5.rating);
        NotificationCenter r12 = getNotificationCenter();
        if (r15 == false) goto L34;
        int r132 = NotificationCenter.reloadGuestBotHints;
    L35:
        r12.postNotificationName(r132, new Object[0]);
        return;
    L34:
        r132 = NotificationCenter.reloadInlineHints;
        goto L35
    L23:
        r5 = null;
        goto L24
    L15:
        r2 = this.inlineBots;
        goto L16
    L12:
        r03 = 60;
        goto L13
    L8:
        r02 = r0.botRatingLoadTime;
        goto L9
    }

    public static String inputSetKey(TLRPC.InputStickerSet r3) {
        if ((r3 instanceof TLRPC.TL_inputStickerSetID) == false) goto L7;
        return "id" + r3.id + "access_hash" + r3.access_hash;
    L7:
        if ((r3 instanceof TLRPC.TL_inputStickerSetShortName) == false) goto L11;
        return "short" + r3.short_name;
    L11:
        if ((r3 instanceof TLRPC.TL_inputStickerSetEmpty) == false) goto L15;
        return "empty";
    L15:
        if ((r3 instanceof TLRPC.TL_inputStickerSetAnimatedEmoji) == false) goto L19;
        return "animatedEmoji";
    L19:
        if ((r3 instanceof TLRPC.TL_inputStickerSetEmojiGenericAnimations) == false) goto L23;
        return "emojiGenericAnimations";
    L23:
        if ((r3 instanceof TLRPC.TL_inputStickerSetEmojiChannelDefaultStatuses) == false) goto L27;
        return "emojiChannelDefaultStatuses";
    L27:
        if ((r3 instanceof TLRPC.TL_inputStickerSetDice) == false) goto L31;
        return "dice" + ((TLRPC.TL_inputStickerSetDice) r3).emoticon;
    L31:
        if ((r3 instanceof TLRPC.TL_inputStickerSetPremiumGifts) == false) goto L35;
        return "premiumGifts";
    L35:
        if ((r3 instanceof TLRPC.TL_inputStickerSetEmojiDefaultTopicIcons) == false) goto L39;
        return "defaultTopicIcons";
    L39:
        if ((r3 instanceof TLRPC.TL_inputStickerSetEmojiDefaultStatuses) == false) goto L43;
        return "emojiDefaultStatuses";
    L43:
        if ((r3 instanceof TLRPC.TL_inputStickerSetTonGifts) == false) goto L46;
        return "tonGifts";
    L46:
        return "null";
    }

    private /* synthetic */ void lambda$addRecentGif$26(TLRPC.Document r5) {
        getMessagesStorage().getDatabase().executeFast("DELETE FROM web_recent_v3 WHERE id = '" + r5.id + "' AND type = 2").stepThis().dispose();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$addRecentSticker$20() {
        getMediaDataController().loadRecents(2, false, false, true);
    }

    private /* synthetic */ void lambda$addRecentSticker$21(Object r1, TLRPC.TL_messages_faveSticker r2, TLObject r3, TLRPC.TL_error r4) {
        if (r4 != null) goto L4;
    L8:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda146(this));
        return;
    L4:
        if (FileRefController.isFileRefError(r4.text) == false) goto L8;
        if (r1 == null) goto L8;
        getFileRefController().requestReference(r1, new Object[]{r2});
    }

    private /* synthetic */ void lambda$addRecentSticker$22(Object r1, TLRPC.TL_messages_saveRecentSticker r2, TLObject r3, TLRPC.TL_error r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (FileRefController.isFileRefError(r4.text) == false) goto L9;
        if (r1 == null) goto L10;
        getFileRefController().requestReference(r1, new Object[]{r2});
        return;
    L10:
        return;
    }

    private /* synthetic */ void lambda$addRecentSticker$23(int r5, TLRPC.Document r6) {
        if (r5 != 0) goto L6;
        int r52 = 3;
    L17:
        getMessagesStorage().getDatabase().executeFast("DELETE FROM web_recent_v3 WHERE id = '" + r6.id + "' AND type = " + r52).stepThis().dispose();     // Catch: Exception -> L14
        return;
    L14:
        e = move-exception;
        FileLog.e(e);
        return;
    L6:
        if (r5 != 1) goto L9;
        r52 = 4;
        goto L17
    L9:
        if (r5 != 5) goto L11;
        r52 = 7;
        goto L17
    L11:
        r52 = 5;
        goto L17
    }

    private /* synthetic */ void lambda$broadcastPinnedMessage$169(ArrayList r2, boolean r3, ArrayList r4) {
        getMessagesController().putUsers(r2, r3);
        getMessagesController().putChats(r4, r3);
    }

    private /* synthetic */ void lambda$broadcastPinnedMessage$170(ArrayList r13) {
        getNotificationCenter().postNotificationName(NotificationCenter.didLoadPinnedMessages, new Object[]{Long.valueOf(((MessageObject) r13.get(0)).getDialogId()), null, Boolean.TRUE, r13, null, 0, -1, Boolean.FALSE});
    }

    private /* synthetic */ void lambda$broadcastPinnedMessage$171(ArrayList r14, boolean r15, ArrayList r16, ArrayList r17, ArrayList r18, LongSparseArray r19, LongSparseArray r20) {
        getMessagesController().putUsers(r14, r15);
        getMessagesController().putChats(r16, r15);
        int r142 = r17.size();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r142) goto L14;
        TLRPC.Message r8 = (TLRPC.Message) r17.get(r2);
        if ((MessageObject.getMedia(r8) instanceof TLRPC.TL_messageMediaDocument) == false) goto L7;
    L8:
        r3 = r3 + 1;
    L9:
        int r7 = this.currentAccount;
        if (r3 >= 30) goto L12;
        boolean r12 = true;
    L13:
        r18.add(new MessageObject(r7, r8, r19, r20, false, r12));
        r2 = r2 + 1;
        goto L3
    L12:
        r12 = false;
        goto L13
    L7:
        if ((MessageObject.getMedia(r8) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L9;
    L14:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda51(this, r18));
    }

    private /* synthetic */ void lambda$broadcastReplyMessages$181(ArrayList r9, boolean r10, ArrayList r11, ArrayList r12, LongSparseArray r13, long r14) {
        getMessagesController().putUsers(r9, r10);
        getMessagesController().putChats(r11, r10);
        int r92 = r12.size();
        int r112 = 0;
        boolean r0 = false;
    L3:
        if (r112 >= r92) goto L30;
        MessageObject r1 = (MessageObject) r12.get(r112);
        SparseArray r2 = (SparseArray) r13.get(r1.getDialogId());
        if (r2 == null) goto L29;
        ArrayList r22 = (ArrayList) r2.get(r1.getId());
        if (r22 == null) goto L29;
        int r02 = 0;
    L11:
        if (r02 >= r22.size()) goto L28;
        MessageObject r3 = (MessageObject) r22.get(r02);
        r3.replyMessageObject = r1;
        r3.applyTimestampsHighlightForReplyMsg();
        TLRPC.MessageAction r5 = r3.messageOwner.action;
        if ((r5 instanceof TLRPC.TL_messageActionPinMessage) == false) goto L16;
        r3.generatePinMessageText(null, null);
    L27:
        r02 = r02 + 1;
        goto L11
    L16:
        if ((r5 instanceof TLRPC.TL_messageActionGameScore) == false) goto L19;
        r3.generateGameMessageText(null);
        goto L27
    L19:
        if ((r5 instanceof TLRPC.TL_messageActionPaymentSent) == false) goto L22;
        r3.generatePaymentSentMessageText(null, false);
        goto L27
    L22:
        if ((r5 instanceof TLRPC.TL_messageActionPaymentSentMe) == false) goto L25;
        r3.generatePaymentSentMessageText(null, true);
        goto L27
    L25:
        if ((r5 instanceof TLRPC.TL_messageActionSuggestedPostApproval) == false) goto L27;
        r3.generateSuggestionApprovalMessageText();
        goto L27
    L28:
        r0 = true;
    L29:
        r112 = r112 + 1;
        goto L3
    L30:
        if (r0 == false) goto L44;
        getNotificationCenter().postNotificationName(NotificationCenter.replyMessagesDidLoad, new Object[]{Long.valueOf(r14), r12, r13});
        return;
    }

    private /* synthetic */ void lambda$buildShortcuts$145(boolean r23, ArrayList r24) {
        ArrayList r1 = r24;
    L129:
        th = move-exception;
        FileLog.e("Failed to create shortcuts", th);
        return;
    L4:
        if (SharedConfig.directShareHash != null) goto L6;
        SharedConfig.directShareHash = UUID.randomUUID().toString();     // Catch: Throwable -> L129
        ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putString("directShareHash2", SharedConfig.directShareHash).apply();     // Catch: Throwable -> L129
    L6:
        ArrayList r2 = new ArrayList();     // Catch: Throwable -> L129
        ArrayList r4 = new ArrayList();     // Catch: Throwable -> L129
        ArrayList r5 = new ArrayList();     // Catch: Throwable -> L129
        if (r23 == false) goto L10;
        ShortcutManagerCompat.removeAllDynamicShortcuts(ApplicationLoader.applicationContext);     // Catch: Throwable -> L129
    L36:
        Intent r42 = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);     // Catch: Throwable -> L129
        r42.setAction("new_dialog");     // Catch: Throwable -> L129
        ArrayList r52 = new ArrayList();     // Catch: Throwable -> L129
        ShortcutInfoCompat r43 = new ShortcutInfoCompat.Builder(ApplicationLoader.applicationContext, "compose").setShortLabel(LocaleController.getString(R.string.NewConversationShortcut)).setLongLabel(LocaleController.getString(R.string.NewConversationShortcut)).setIcon(IconCompat.createWithAdaptiveBitmap(UIUtil.drawableToBitmap(new CombinedDrawable(UIUtil.createFabBackground(56, -10968099, -10968099), ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.floating_pencil).mutate()).setIconSize(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f)), 192, 192))).setRank(PluginsController.isPluginEngineSupported() ? 1 : 0).setIntent(r42).build();     // Catch: Throwable -> L129
        if (PluginsController.isPluginEngineSupported() == false) goto L39;
        Intent r9 = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);     // Catch: Throwable -> L129
        r9.setAction("com.exteragram.plugins.safemode");     // Catch: Throwable -> L129
        ShortcutInfoCompat r7 = new ShortcutInfoCompat.Builder(ApplicationLoader.applicationContext, "plugins_safemode").setShortLabel(LocaleController.getString(R.string.PluginsSafeMode)).setLongLabel(LocaleController.getString(R.string.PluginsSafeModeShortcut)).setIcon(IconCompat.createWithAdaptiveBitmap(UIUtil.drawableToBitmap(new CombinedDrawable(UIUtil.createFabBackground(56, -10968099, -10968099), ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.plugins_safe_mode).mutate()).setIconSize(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f)), 192, 192))).setRank(0).setIntent(r9).build();     // Catch: Throwable -> L129
    L40:
        if (r23 == false) goto L44;
        if (r7 == null) goto L43;
        ShortcutManagerCompat.pushDynamicShortcut(ApplicationLoader.applicationContext, r7);     // Catch: Throwable -> L129
    L43:
        ShortcutManagerCompat.pushDynamicShortcut(ApplicationLoader.applicationContext, r43);     // Catch: Throwable -> L129
    L54:
        boolean r8 = true;
        HashSet r44 = new HashSet(1);     // Catch: Throwable -> L129
        r44.add(SHORTCUT_CATEGORY);     // Catch: Throwable -> L129
        int r92 = 0;
    L55:
        if (r92 >= r1.size()) goto L156;
        Intent r11 = new Intent(ApplicationLoader.applicationContext, OpenChatReceiver.class);     // Catch: Throwable -> L129
        long r14 = MessageObject.getPeerId(((TLRPC.TL_topPeer) r1.get(r92)).peer);     // Catch: Throwable -> L129
        if (DialogObject.isUserDialog(r14) == false) goto L60;
        r11.putExtra("userId", r14);     // Catch: Throwable -> L129
        TLRPC.User r0 = getMessagesController().getUser(Long.valueOf(r14));     // Catch: Throwable -> L129
        TLRPC.Chat r3 = null;
    L61:
        if (r0 != null) goto L63;
    L64:
        if (r3 != null) goto L66;
        ShortcutInfoCompat r20 = r7;
        int r16 = r92;
        char r82 = 192;
        char r93 = 41949;
    L128:
        r1 = r24;
        r7 = r20;
        r8 = true;
        r92 = r16 + 1;
    L66:
        if (r0 == null) goto L71;
        String r32 = ContactsController.formatName(r0.first_name, r0.last_name);     // Catch: Throwable -> L129
        TLRPC.UserProfilePhoto r02 = r0.photo;     // Catch: Throwable -> L129
        if (r02 == null) goto L70;
        TLRPC.FileLocation r03 = r02.photo_small;     // Catch: Throwable -> L129
    L75:
        r11.putExtra("currentAccount", this.currentAccount);     // Catch: Throwable -> L129
        r11.setAction("com.tmessages.openchat" + r14);     // Catch: Throwable -> L129
        r11.putExtra("dialogId", r14);     // Catch: Throwable -> L129
        r11.putExtra("hash", SharedConfig.directShareHash);     // Catch: Throwable -> L129
        r11.addFlags(67108864);     // Catch: Throwable -> L129
        if (r03 != null) goto L139;
        String r18 = r32;
        r20 = r7;
        r16 = r92;
        Bitmap r10 = null;
    L107:
        String r04 = "did3_" + r14;     // Catch: Throwable -> L129
        if (TextUtils.isEmpty(r18) == false) goto L110;
        String r12 = " ";
    L111:
        ShortcutInfoCompat.Builder r13 = new ShortcutInfoCompat.Builder(ApplicationLoader.applicationContext, r04).setShortLabel(r12).setLongLabel(r12);     // Catch: Throwable -> L129
        int r94 = r16 + 1;     // Catch: Throwable -> L129
        if (r20 == null) goto L114;
        int r72 = 1;
    L115:
        ShortcutInfoCompat.Builder r15 = r13.setRank(r94 + r72).setIntent(r11);     // Catch: Throwable -> L129
        if (SharedConfig.directShare == false) goto L118;
        r15.setCategories(r44);     // Catch: Throwable -> L129
    L118:
        if (r10 == null) goto L120;
        r15.setIcon(IconCompat.createWithAdaptiveBitmap(r10));     // Catch: Throwable -> L129
        r82 = 192;
        r93 = 41949;
    L121:
        if (r23 == false) goto L123;
        ShortcutManagerCompat.pushDynamicShortcut(ApplicationLoader.applicationContext, r15.build());     // Catch: Throwable -> L129
        goto L128
    L123:
        r52.add(r15.build());     // Catch: Throwable -> L129
        if (r2.contains(r04) == false) goto L126;
        ShortcutManagerCompat.updateShortcuts(ApplicationLoader.applicationContext, r52);     // Catch: Throwable -> L129
    L127:
        r52.clear();     // Catch: Throwable -> L129
        goto L128
    L126:
        ShortcutManagerCompat.addDynamicShortcuts(ApplicationLoader.applicationContext, r52);     // Catch: Throwable -> L129
        goto L127
    L120:
        r93 = 41949;
        r82 = 192;
        r15.setIcon(IconCompat.createWithAdaptiveBitmap(UIUtil.drawableToBitmap(new CombinedDrawable(UIUtil.createFabBackground(64, -10968099, -10968099), ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.mini_reply_user).mutate()).setIconSize(AndroidUtilities.dp(32.0f), AndroidUtilities.dp(32.0f)), 192, 192)));     // Catch: Throwable -> L129
        goto L121
    L114:
        r72 = 0;
        goto L115
    L110:
        r12 = r18;
        goto L111
    L139:
        r10 = BitmapFactory.decodeFile(getFileLoader().getPathToAttach(r03, r8).toString());     // Catch: Throwable -> L103
        if (r10 != null) goto L135;
        r18 = r32;
        r20 = r7;
        r16 = r92;
        goto L107
    L135:
        int r05 = AndroidUtilities.dp(48.0f);     // Catch: Throwable -> L91
        Bitmap r122 = Bitmap.createBitmap(r05, r05, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L91
        Canvas r83 = new Canvas(r122);     // Catch: Throwable -> L91
        if (roundPaint != null) goto L93;
        roundPaint = new Paint(3);     // Catch: Throwable -> L91
        bitmapRect = new RectF();     // Catch: Throwable -> L91
        Paint r06 = new Paint(1);     // Catch: Throwable -> L91
        erasePaint = r06;     // Catch: Throwable -> L91
        r18 = r32;
        r06.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));     // Catch: Throwable -> L89
        Path r07 = new Path();     // Catch: Throwable -> L89
        roundPath = r07;     // Catch: Throwable -> L89
        r20 = r7;
        r16 = r92;
        r07.addCircle(r05 / 2, r05 / 2, (r05 / 2) - AndroidUtilities.dp(2.0f), Path.Direction.CW);     // Catch: Throwable -> L87
        roundPath.toggleInverseFillType();     // Catch: Throwable -> L87
    L94:
        bitmapRect.set(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(46.0f), AndroidUtilities.dp(46.0f));     // Catch: Throwable -> L87
        r83.drawBitmap(r10, null, bitmapRect, roundPaint);     // Catch: Throwable -> L99
        r83.drawPath(roundPath, erasePaint);     // Catch: Throwable -> L99
        r83.setBitmap(null);     // Catch: Throwable -> L99 Exception -> L132
    L101:
        r10 = r122;
    L99:
        th = th;
    L105:
        FileLog.e(th);     // Catch: Throwable -> L129
    L87:
        th = th;
    L89:
        th = th;
    L90:
        r20 = r7;
        r16 = r92;
        goto L105
    L93:
        r18 = r32;
        r20 = r7;
        r16 = r92;
    L91:
        th = th;
        r18 = r32;
    L103:
        th = th;
        r18 = r32;
        r20 = r7;
        r16 = r92;
        r10 = null;
    L70:
        r03 = null;
        goto L75
    L71:
        String r08 = r3.title;     // Catch: Throwable -> L129
        TLRPC.ChatPhoto r33 = r3.photo;     // Catch: Throwable -> L129
        if (r33 == null) goto L74;
        TLRPC.FileLocation r34 = r33.photo_small;     // Catch: Throwable -> L129
        r32 = r08;
        r03 = r34;
        goto L75
    L74:
        r32 = r08;
        goto L70
    L63:
        if (UserObject.isDeleted(r0) == false) goto L66;
    L60:
        long r123 = -r14;
        TLRPC.Chat r09 = getMessagesController().getChat(Long.valueOf(r123));     // Catch: Throwable -> L129
        r11.putExtra("chatId", r123);     // Catch: Throwable -> L129
        r3 = r09;
        r0 = null;
        goto L61
    L156:
        return;
    L44:
        if (r7 != null) goto L46;
    L49:
        r52.add(r43);     // Catch: Throwable -> L129
        if (r2.contains("compose") == false) goto L52;
        ShortcutManagerCompat.updateShortcuts(ApplicationLoader.applicationContext, r52);     // Catch: Throwable -> L129
    L53:
        r52.clear();     // Catch: Throwable -> L129
        goto L54
    L52:
        ShortcutManagerCompat.addDynamicShortcuts(ApplicationLoader.applicationContext, r52);     // Catch: Throwable -> L129
        goto L53
    L46:
        if (r2.contains("safemode") == false) goto L48;
        ShortcutManagerCompat.updateShortcuts(ApplicationLoader.applicationContext, Collections.singletonList(r7));     // Catch: Throwable -> L129
        goto L49
    L48:
        ShortcutManagerCompat.addDynamicShortcuts(ApplicationLoader.applicationContext, Collections.singletonList(r7));     // Catch: Throwable -> L129
        goto L49
    L39:
        r7 = null;
        goto L40
    L10:
        List<ShortcutInfoCompat> r95 = ShortcutManagerCompat.getDynamicShortcuts(ApplicationLoader.applicationContext);     // Catch: Throwable -> L129
        if (r95 == null) goto L34;
        if (r95.isEmpty() == true) goto L34;
        r4.add("compose");     // Catch: Throwable -> L129
        if (PluginsController.isPluginEngineSupported() == false) goto L17;
        r4.add("plugins_safemode");     // Catch: Throwable -> L129
    L17:
        int r102 = 0;
    L19:
        if (r102 >= r1.size()) goto L21;
        r4.add("did3_" + MessageObject.getPeerId(((TLRPC.TL_topPeer) r1.get(r102)).peer));     // Catch: Throwable -> L129
        r102 = r102 + 1;     // Catch: Throwable -> L129
        goto L19
    L21:
        int r103 = 0;
    L23:
        if (r103 >= r95.size()) goto L29;
        String r112 = r95.get(r103).getId();     // Catch: Throwable -> L129
        if (r4.remove(r112) == true) goto L27;
        r5.add(r112);     // Catch: Throwable -> L129
    L27:
        r2.add(r112);     // Catch: Throwable -> L129
        r103 = r103 + 1;     // Catch: Throwable -> L129
        goto L23
    L29:
        if (r4.isEmpty() == false) goto L34;
        if (r5.isEmpty() == false) goto L34;
        return;
    L34:
        if (r5.isEmpty() == true) goto L36;
        ShortcutManagerCompat.removeDynamicShortcuts(ApplicationLoader.applicationContext, r5);     // Catch: Throwable -> L129
        goto L36
    }

    private /* synthetic */ void lambda$checkDefaultTopicIcons$82(TLObject r8) {
        if ((r8 instanceof TLRPC.TL_messages_stickerSet) == false) goto L6;
        TLRPC.TL_messages_stickerSet r4 = (TLRPC.TL_messages_stickerSet) r8;
        UserConfig r82 = getUserConfig();
        r82.defaultTopicIcons = r4.set.short_name;
        UserConfig r83 = getUserConfig();
        r83.lastUpdatedDefaultTopicIcons = System.currentTimeMillis();
        getUserConfig().saveConfig(false);
        processLoadedDiceStickers(getUserConfig().defaultTopicIcons, false, r4, false, (int) (System.currentTimeMillis() / 1000));
        return;
    }

    private /* synthetic */ void lambda$checkDefaultTopicIcons$83(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda10(this, r1));
    }

    private /* synthetic */ void lambda$checkGenericAnimations$80(TLObject r10) {
        if ((r10 instanceof TLRPC.TL_messages_stickerSet) == false) goto L11;
        TLRPC.TL_messages_stickerSet r4 = (TLRPC.TL_messages_stickerSet) r10;
        getUserConfig().genericAnimationsStickerPack = r4.set.short_name;
        getUserConfig().lastUpdatedGenericAnimations = System.currentTimeMillis();
        getUserConfig().saveConfig(false);
        processLoadedDiceStickers(getUserConfig().genericAnimationsStickerPack, false, r4, false, (int) (System.currentTimeMillis() / 1000));
        int r9 = 0;
    L6:
        if (r9 >= r4.documents.size()) goto L15;
        if (this.currentAccount != UserConfig.selectedAccount) goto L10;
        preloadImage(ImageLocation.getForDocument(r4.documents.get(r9)), 0);
    L10:
        r9 = r9 + 1;
        goto L6
    L15:
        return;
    }

    private /* synthetic */ void lambda$checkGenericAnimations$81(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda11(this, r1));
    }

    private /* synthetic */ void lambda$checkPremiumGiftStickers$76(TLObject r10) {
        if ((r10 instanceof TLRPC.TL_messages_stickerSet) == false) goto L6;
        TLRPC.TL_messages_stickerSet r4 = (TLRPC.TL_messages_stickerSet) r10;
        UserConfig r102 = getUserConfig();
        r102.premiumGiftsStickerPack = r4.set.short_name;
        UserConfig r103 = getUserConfig();
        r103.lastUpdatedPremiumGiftsStickerPack = System.currentTimeMillis();
        getUserConfig().saveConfig(false);
        processLoadedDiceStickers(getUserConfig().premiumGiftsStickerPack, false, r4, false, (int) (System.currentTimeMillis() / 1000));
        getNotificationCenter().postNotificationName(NotificationCenter.didUpdatePremiumGiftStickers, new Object[0]);
        return;
    }

    private /* synthetic */ void lambda$checkPremiumGiftStickers$77(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda62(this, r1));
    }

    private /* synthetic */ void lambda$checkTonGiftStickers$78(TLObject r10) {
        if ((r10 instanceof TLRPC.TL_messages_stickerSet) == false) goto L6;
        TLRPC.TL_messages_stickerSet r4 = (TLRPC.TL_messages_stickerSet) r10;
        UserConfig r102 = getUserConfig();
        r102.premiumTonStickerPack = r4.set.short_name;
        UserConfig r103 = getUserConfig();
        r103.lastUpdatedTonGiftsStickerPack = System.currentTimeMillis();
        getUserConfig().saveConfig(false);
        processLoadedDiceStickers(getUserConfig().premiumTonStickerPack, false, r4, false, (int) (System.currentTimeMillis() / 1000));
        getNotificationCenter().postNotificationName(NotificationCenter.didUpdateTonGiftStickers, new Object[0]);
        return;
    }

    private /* synthetic */ void lambda$checkTonGiftStickers$79(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda97(this, r1));
    }

    private /* synthetic */ void lambda$cleanup$2() {
        getNotificationCenter().postNotificationName(NotificationCenter.reloadHints, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadInlineHints, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadGuestBotHints, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadWebappsHints, new Object[0]);
    }

    private /* synthetic */ void lambda$cleanupStickerSetCache$40() {
        long r1 = System.currentTimeMillis() - 604800000;     // Catch: Exception -> L5
        getMessagesStorage().getDatabase().executeFast("DELETE FROM stickersets2 WHERE date < " + r1).stepThis().dispose();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$clearBotKeyboard$196(ArrayList r10, MessagesStorage.TopicKey r11) {
        if (r10 == null) goto L25;
        int r1 = 0;
    L6:
        if (r1 >= r10.size()) goto L35;
        int r2 = ((Integer) r10.get(r1)).intValue();
        long r4 = r2;
        MessagesStorage.TopicKey r3 = this.botKeyboardsByMids.get(r4);
        if (r3 == null) goto L24;
        this.botKeyboards.remove(r3);
        ArrayList<TLRPC.Message> r6 = this.botDialogKeyboards.get(r3.dialogId);
        if (r6 == null) goto L23;
        int r7 = 0;
    L13:
        if (r7 >= r6.size()) goto L21;
        TLRPC.Message r8 = r6.get(r7);
        if (r8 != null) goto L17;
    L18:
        r6.remove(r7);
        r7 = r7 - 1;
    L19:
        r7 = r7 + 1;
        goto L13
    L17:
        if (r8.id != r2) goto L19;
    L21:
        if (r6.isEmpty() == false) goto L23;
        this.botDialogKeyboards.remove(r3.dialogId);
    L23:
        this.botKeyboardsByMids.remove(r4);
        getNotificationCenter().postNotificationName(NotificationCenter.botKeyboardDidLoad, new Object[]{null, r3});
    L24:
        r1 = r1 + 1;
        goto L6
    L35:
        return;
    L25:
        if (r11 == null) goto L34;
        this.botKeyboards.remove(r11);
        this.botDialogKeyboards.remove(r11.dialogId);
        getNotificationCenter().postNotificationName(NotificationCenter.botKeyboardDidLoad, new Object[]{null, r11});
        return;
    }

    private /* synthetic */ void lambda$clearBotKeyboard$197(long r7) {
        ArrayList<TLRPC.Message> r0 = this.botDialogKeyboards.get(r7);
        if (r0 == null) goto L8;
        int r1 = 0;
    L6:
        if (r1 >= r0.size()) goto L8;
        TLRPC.Message r2 = r0.get(r1);
        int r3 = this.currentAccount;
        MessagesStorage.TopicKey r22 = MessagesStorage.TopicKey.of(r7, MessageObject.getTopicId(r3, r2, ChatObject.isForum(r3, r7)));
        this.botKeyboards.remove(r22);
        getNotificationCenter().postNotificationName(NotificationCenter.botKeyboardDidLoad, new Object[]{null, r22});
        r1 = r1 + 1;
    L8:
        this.botDialogKeyboards.remove(r7);
    }

    private /* synthetic */ void lambda$clearRecentEmojiStatuses$232() {
        getMessagesStorage().getDatabase().executeFast("DELETE FROM emoji_statuses WHERE type = 0").stepThis().dispose();     // Catch: Exception -> L4
        return;
    }

    private /* synthetic */ void lambda$clearRecentStickers$17() {
        getMessagesStorage().getDatabase().executeFast("DELETE FROM web_recent_v3 WHERE type = 3").stepThis().dispose();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$clearRecentStickers$18(TLObject r3) {
        if ((r3 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda184(this));
        this.recentStickers[0].clear();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.recentDocumentsDidLoad, new Object[]{Boolean.FALSE, 0});
        return;
    }

    private /* synthetic */ void lambda$clearRecentStickers$19(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda94(this, r1));
    }

    private /* synthetic */ void lambda$clearTopPeers$151() {
        getMessagesStorage().getDatabase().executeFast("DELETE FROM chat_hints WHERE 1").stepThis().dispose();     // Catch: Exception -> L4
        return;
    }

    private /* synthetic */ void lambda$deletePeer$161(long r3, int r5) {
        getMessagesStorage().getDatabase().executeFast(String.format(Locale.US, "DELETE FROM chat_hints WHERE did = %d AND type = %d", new Object[]{Long.valueOf(r3), Integer.valueOf(r5)})).stepThis().dispose();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$fetchEmojiStatuses$233() {
        getNotificationCenter().postNotificationName(NotificationCenter.recentEmojiStatusesUpdate, new Object[0]);
    }

    private /* synthetic */ void lambda$fetchEmojiStatuses$234(int r9) {
        SQLiteCursor r0 = getMessagesStorage().getDatabase().queryFinalized("SELECT data FROM emoji_statuses WHERE type = " + r9 + " LIMIT 1", new Object[0]);     // Catch: Exception -> L15
        if (r0.next() == true) goto L6;
    L21:
        boolean r4 = false;
    L22:
        r0.dispose();     // Catch: Exception -> L19
    L25:
        this.emojiStatusesFromCacheFetched[r9] = true;
        this.emojiStatusesFetching[r9] = false;
        if (r4 == false) goto L28;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda190(this));
        return;
    L28:
        fetchEmojiStatuses(r9, false);
        return;
    L19:
        e = e;
    L24:
        FileLog.e(e);
        goto L25
    L6:
        if (r0.getColumnCount() <= 0) goto L21;
        if (r0.isNull(0) == true) goto L21;
        NativeByteBuffer r3 = r0.byteBufferValue(0);     // Catch: Exception -> L15
        if (r3 == null) goto L21;
        TL_account.EmojiStatuses r42 = TL_account.EmojiStatuses.TLdeserialize(r3, r3.readInt32(false), false);     // Catch: Exception -> L15
        if ((r42 instanceof TL_account.TL_emojiStatuses) == false) goto L17;
        this.emojiStatusesHash[r9] = r42.hash;     // Catch: Exception -> L15
        this.emojiStatuses[r9] = r42.statuses;     // Catch: Exception -> L15
        r4 = true;
    L30:
        r3.reuse();     // Catch: Exception -> L19
        goto L22
    L17:
        r4 = false;
    L15:
        e = e;
        r4 = false;
        goto L24
    }

    private /* synthetic */ void lambda$fetchEmojiStatuses$235() {
        getNotificationCenter().postNotificationName(NotificationCenter.recentEmojiStatusesUpdate, new Object[0]);
    }

    private /* synthetic */ void lambda$fetchEmojiStatuses$236(int r5, TLObject r6, TLRPC.TL_error r7) {
        this.emojiStatusesFetchDate[r5] = Long.valueOf(System.currentTimeMillis() / 1000);
        if ((r6 instanceof TL_account.TL_emojiStatusesNotModified) == false) goto L7;
        this.emojiStatusesFetching[r5] = false;
        return;
    L7:
        if ((r6 instanceof TL_account.TL_emojiStatuses) == false) goto L10;
        TL_account.TL_emojiStatuses r62 = (TL_account.TL_emojiStatuses) r6;
        this.emojiStatusesHash[r5] = r62.hash;
        this.emojiStatuses[r5] = r62.statuses;
        updateEmojiStatuses(r5, r62);
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda37(this));
        return;
    }

    private /* synthetic */ void lambda$fetchNewEmojiKeywords$210(String r2) {
        this.currentFetchingEmoji.remove(r2);
        this.fetchedEmoji.add(r2);
        getNotificationCenter().postNotificationName(NotificationCenter.emojiKeywordsLoaded, new Object[0]);
    }

    private /* synthetic */ void lambda$fetchNewEmojiKeywords$211(String r2) {
        this.currentFetchingEmoji.remove(r2);
        this.fetchedEmoji.add(r2);
        fetchNewEmojiKeywords(new String[]{r2});
        getNotificationCenter().postNotificationName(NotificationCenter.emojiKeywordsLoaded, new Object[0]);
    }

    private /* synthetic */ void lambda$fetchNewEmojiKeywords$212(String r3) {
        SQLitePreparedStatement r0 = getMessagesStorage().getDatabase().executeFast("DELETE FROM emoji_keywords_info_v2 WHERE lang = ?");     // Catch: Exception -> L4
        r0.bindString(1, r3);     // Catch: Exception -> L4
        r0.step();     // Catch: Exception -> L4
        r0.dispose();     // Catch: Exception -> L4
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda78(this, r3));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$fetchNewEmojiKeywords$213() {
        getNotificationCenter().postNotificationName(NotificationCenter.emojiKeywordsLoaded, new Object[0]);
    }

    private /* synthetic */ void lambda$fetchNewEmojiKeywords$214(String r2) {
        this.currentFetchingEmoji.remove(r2);
        this.fetchedEmoji.add(r2);
        getNotificationCenter().postNotificationName(NotificationCenter.emojiKeywordsLoaded, new Object[0]);
    }

    private /* synthetic */ void lambda$fetchNewEmojiKeywords$215(int r1, String r2, String r3, TLObject r4, TLRPC.TL_error r5) {
        if (r4 == null) goto L11;
        TLRPC.TL_emojiKeywordsDifference r42 = (TLRPC.TL_emojiKeywordsDifference) r4;
        if (r1 != (-1)) goto L6;
    L9:
        putEmojiKeywords(r3, r42);
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda68(this));
        return;
    L6:
        if (r42.lang_code.equals(r2) == true) goto L9;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda67(this, r3));
        return;
    L11:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda69(this, r3));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$fetchNewEmojiKeywords$216(String r9) {
        String r1 = null;
        long r2 = 0;
        SQLiteCursor r4 = getMessagesStorage().getDatabase().queryFinalized("SELECT alias, version, date FROM emoji_keywords_info_v2 WHERE lang = ?", new Object[]{r9});     // Catch: Exception -> L10
        if (r4.next() == false) goto L12;
        r1 = r4.stringValue(0);     // Catch: Exception -> L10
        int r5 = r4.intValue(1);     // Catch: Exception -> L10
        r2 = r4.longValue(2);     // Catch: Exception -> L8
    L13:
        r4.dispose();     // Catch: Exception -> L8
    L17:
        if (BuildVars.DEBUG_VERSION == false) goto L19;
    L22:
        if (r5 != (-1)) goto L24;
        TLRPC.TL_messages_getEmojiKeywords r0 = new TLRPC.TL_messages_getEmojiKeywords();
        r0.lang_code = r9;
        TLRPC.TL_messages_getEmojiKeywordsDifference r02 = r0;
    L25:
        getConnectionsManager().sendRequest(r02, new MediaDataController$$ExternalSyntheticLambda96(this, r5, r1, r9));
        return;
    L24:
        TLRPC.TL_messages_getEmojiKeywordsDifference r03 = new TLRPC.TL_messages_getEmojiKeywordsDifference();
        r03.lang_code = r9;
        r03.from_version = r5;
        r02 = r03;
        goto L25
    L19:
        if (Math.abs(System.currentTimeMillis() - r2) >= DurationKt.MILLIS_IN_HOUR) goto L22;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda95(this, r9));
        return;
    L8:
        e = e;
    L15:
        FileLog.e(e);
        goto L17
    L12:
        r5 = -1;
    L10:
        e = e;
        r5 = -1;
        goto L15
    }

    private /* synthetic */ void lambda$fetchStickerSetInternal$42(String r6, TLObject r7) {
        ArrayList<Utilities.Callback2<Boolean, TLRPC.TL_messages_stickerSet>> r0 = this.loadingStickerSets.get(r6);
        if (r0 == null) goto L11;
        int r1 = 0;
    L6:
        if (r1 >= r0.size()) goto L11;
        if (r7 == null) goto L9;
        r0.get(r1).run(Boolean.TRUE, (TLRPC.TL_messages_stickerSet) r7);
    L10:
        r1 = r1 + 1;
        goto L6
    L9:
        r0.get(r1).run(Boolean.FALSE, null);
    L11:
        this.loadingStickerSets.remove(r6);
    }

    private /* synthetic */ void lambda$fetchStickerSetInternal$43(String r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda152(this, r1, r2));
    }

    private /* synthetic */ void lambda$fillWithAnimatedEmoji$228(Integer r23, ArrayList r24, boolean r25, boolean r26, ArrayList[] r27, Runnable r28) {
        MediaDataController r0 = this;
        ArrayList<TLRPC.StickerSetCovered> r2 = r0.getFeaturedEmojiSets();
        ArrayList r3 = new ArrayList();
        HashSet r4 = new HashSet();
        ArrayList r5 = new ArrayList();
        boolean r6 = true;
        if (r23 == null) goto L5;
        int r8 = r23.intValue();
    L12:
        if (r23 != null) goto L14;
        int r7 = Math.min(15, r24.size());
    L15:
        int r10 = 0;
        if (UserConfig.getInstance(r0.currentAccount).isPremium() == true) goto L20;
        if (r25 == true) goto L20;
        r6 = false;
    L20:
        if (r26 == false) goto L30;
        String r11 = UserConfig.getInstance(r0.currentAccount).defaultTopicIcons;
        if (r27[0] == null) goto L31;
        if (r11 == null) goto L27;
        TLRPC.TL_messages_stickerSet r12 = getInstance(r0.currentAccount).getStickerSetByName(r11);
        if (r12 != null) goto L28;
        r12 = getInstance(r0.currentAccount).getStickerSetByEmojiOrName(r11);
    L28:
        if (r12 == null) goto L31;
        r27[0].add(r12);
        goto L31
    L27:
        r12 = null;
    L31:
        int r122 = 0;
    L32:
        if (r122 >= r7) goto L231;
        String r13 = ((KeywordResult) r24.get(r122)).emoji;
        if (TextUtils.isEmpty(r13) == false) goto L36;
        ArrayList<TLRPC.StickerSetCovered> r18 = r2;
        boolean r16 = r6;
        int r20 = r7;
        int r232 = r10;
    L230:
        r122 = r122 + 1;
        r0 = this;
        r10 = r232;
        r6 = r16;
        r2 = r18;
        r7 = r20;
        goto L32
    L36:
        r5.clear();
        if (Emoji.recentEmoji == null) goto L63;
        r232 = r10;
        int r14 = r232;
    L39:
        ArrayList<String> r102 = Emoji.recentEmoji;
        if (r14 >= r102.size()) goto L62;
        if (r102.get(r14).startsWith("animated_") == true) goto L239;
    L57:
        r16 = r6;
    L59:
        if (r5.size() >= r8) goto L65;
        r14 = r14 + 1;
        r6 = r16;
    L65:
        if (r5.size() < r8) goto L67;
    L159:
        r20 = r7;
    L161:
        if (r5.size() >= r8) goto L220;
        if (r2 == null) goto L220;
        int r02 = r232;
    L165:
        if (r02 >= r2.size()) goto L220;
        TLRPC.StickerSetCovered r62 = r2.get(r02);
        if (r62 != null) goto L170;
    L168:
        int r17 = r02;
        r18 = r2;
    L219:
        r02 = r17 + 1;
        r2 = r18;
        goto L165
    L170:
        if ((r62 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L172;
        ArrayList<TLRPC.Document> r72 = ((TLRPC.TL_stickerSetFullCovered) r62).documents;
    L173:
        if (r72 == null) goto L168;
        int r9 = r232;
    L177:
        if (r9 >= r72.size()) goto L215;
        TLRPC.Document r103 = r72.get(r9);
        if (r103 != null) goto L181;
    L213:
        r17 = r02;
        r18 = r2;
    L207:
        TLRPC.StickerSetCovered r262 = r62;
        ArrayList<TLRPC.Document> r03 = r72;
    L214:
        r9 = r9 + 1;
        r62 = r262;
        r72 = r03;
        r02 = r17;
        r2 = r18;
        goto L177
    L181:
        if (r103.attributes == null) goto L213;
        if (r5.contains(r103) == true) goto L213;
        int r142 = r232;
        r17 = r02;
    L186:
        if (r142 >= r103.attributes.size()) goto L191;
        TLRPC.DocumentAttribute r04 = r103.attributes.get(r142);
        r18 = r2;
        if ((r04 instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L189;
        r142 = r142 + 1;
        r2 = r18;
        goto L186
    L189:
        TLRPC.TL_documentAttributeCustomEmoji r05 = (TLRPC.TL_documentAttributeCustomEmoji) r04;
    L192:
        if (r05 == null) goto L207;
        if (TextUtils.isEmpty(r05.alt) == true) goto L207;
        if (r05.alt.contains(r13) == false) goto L207;
        if (r16 == false) goto L199;
    L206:
        r262 = r62;
        r03 = r72;
        if (r4.contains(Long.valueOf(r103.id)) == true) goto L214;
        r4.add(Long.valueOf(r103.id));
        r5.add(r103);
        if (r5.size() < r8) goto L214;
    L217:
        if (r5.size() < r8) goto L219;
    L222:
        if (r5.isEmpty() == true) goto L230;
        String r06 = ((KeywordResult) r24.get(r122)).keyword;
        int r22 = r232;
    L225:
        if (r22 >= r5.size()) goto L230;
        TLRPC.Document r63 = (TLRPC.Document) r5.get(r22);
        if (r63 == null) goto L229;
        KeywordResult r73 = new KeywordResult();
        r73.emoji = "animated_" + r63.id;
        r73.keyword = r06;
        r3.add(r73);
    L229:
        r22 = r22 + 1;
        goto L225
    L199:
        if (r05.free == true) goto L206;
        TLRPC.StickerSet r07 = r62.set;
        if (r07 == null) goto L207;
        String r08 = r07.short_name;
        if (r08 == null) goto L207;
        if (r08.equals(r11) == false) goto L207;
    L191:
        r18 = r2;
        r05 = null;
        goto L192
    L215:
        r17 = r02;
        r18 = r2;
        goto L217
    L172:
        r72 = r62.covers;
    L220:
        r18 = r2;
        goto L222
    L67:
        if (r27[r232] == null) goto L159;
        int r64 = r232;
    L70:
        if (r64 >= r27[r232].size()) goto L159;
        TLRPC.TL_messages_stickerSet r92 = (TLRPC.TL_messages_stickerSet) r27[r232].get(r64);
        if (r92 != null) goto L74;
    L112:
        int r263 = r64;
        r20 = r7;
        if (r92 != null) goto L115;
    L156:
        if (r5.size() >= r8) goto L161;
        r64 = r263 + 1;
        r7 = r20;
        goto L70
    L115:
        if (r92.documents == null) goto L156;
        int r09 = r232;
    L118:
        if (r09 >= r92.documents.size()) goto L156;
        TLRPC.Document r65 = r92.documents.get(r09);
        if (r65 != null) goto L122;
    L148:
        TLRPC.TL_messages_stickerSet r74 = r92;
    L154:
        r09 = r09 + 1;
        r92 = r74;
        goto L118
    L122:
        if (r65.attributes == null) goto L148;
        if (r5.contains(r65) == true) goto L148;
        int r75 = r232;
    L127:
        if (r75 >= r65.attributes.size()) goto L132;
        TLRPC.DocumentAttribute r104 = r65.attributes.get(r75);
        if ((r104 instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L130;
        r75 = r75 + 1;
        goto L127
    L130:
        TLRPC.TL_documentAttributeCustomEmoji r105 = (TLRPC.TL_documentAttributeCustomEmoji) r104;
    L133:
        if (r105 == null) goto L148;
        if (TextUtils.isEmpty(r105.alt) == true) goto L148;
        if (r105.alt.contains(r13) == false) goto L148;
        if (r16 == false) goto L140;
    L147:
        r74 = r92;
        if (r4.contains(Long.valueOf(r65.id)) == true) goto L154;
        r4.add(Long.valueOf(r65.id));
        r5.add(r65);
        if (r5.size() < r8) goto L154;
    L140:
        if (r105.free == true) goto L147;
        TLRPC.StickerSet r76 = r92.set;
        if (r76 == null) goto L148;
        String r77 = r76.short_name;
        if (r77 == null) goto L148;
        if (r77.equals(r11) == false) goto L148;
    L132:
        r105 = null;
        goto L133
    L74:
        if (r92.packs == null) goto L112;
        int r106 = r232;
    L77:
        if (r106 >= r92.packs.size()) goto L111;
        TLRPC.TL_stickerPack r143 = r92.packs.get(r106);
        if (r143 == null) goto L109;
        String r010 = r143.emoticon;
        if (r010 == null) goto L109;
        if (r010.contains(r13) == false) goto L109;
        int r011 = r232;
        int r264 = r64;
    L86:
        if (r011 >= r143.documents.size()) goto L108;
        long r172 = r143.documents.get(r011).longValue();
        int r66 = r232;
        int r19 = r011;
    L89:
        if (r66 >= r92.documents.size()) goto L96;
        TLRPC.Document r012 = r92.documents.get(r66);
        int r21 = r66;
        int r202 = r7;
        if (r012 == null) goto L95;
        if (r012.id != r172) goto L95;
    L97:
        if (r012 == null) goto L107;
        if (r012.attributes == null) goto L107;
        if (r5.contains(r012) == true) goto L107;
        if (r4.contains(Long.valueOf(r012.id)) == true) goto L107;
        r4.add(Long.valueOf(r012.id));
        r5.add(r012);
        if (r5.size() < r8) goto L107;
    L110:
        r106 = r106 + 1;
        r64 = r264;
        r7 = r202;
    L107:
        r011 = r19 + 1;
        r7 = r202;
    L95:
        r66 = r21 + 1;
        r7 = r202;
        goto L89
    L96:
        r202 = r7;
        r012 = null;
    L108:
        r202 = r7;
    L109:
        r264 = r64;
        goto L108
    L111:
        r263 = r64;
        r20 = r7;
        goto L156
    L239:
        r16 = r6;
        TLRPC.Document r67 = AnimatedEmojiDrawable.findDocument(r0.currentAccount, Long.parseLong(r102.get(r14).substring(9)));     // Catch: Exception -> L235
        String r107 = MessageObject.findAnimatedEmojiEmoticon(r67, null);     // Catch: Exception -> L235
        if (r67 == null) goto L59;
        if (r107 == null) goto L59;
        if (r107.contains(r13) == false) goto L59;
        if (r16 == true) goto L54;
        if (MessageObject.isFreeEmoji(r67) == false) goto L59;
    L54:
        if (r4.contains(Long.valueOf(r67.id)) == true) goto L59;
        r4.add(Long.valueOf(r67.id));     // Catch: Exception -> L235
        r5.add(r67);     // Catch: Exception -> L235
        goto L59
    L62:
        r16 = r6;
        goto L65
    L63:
        r16 = r6;
        r232 = r10;
        goto L65
    L231:
        r24.addAll(r10, r3);
        if (r28 == null) goto L282;
        r28.run();
        return;
    L282:
        return;
    L30:
        r11 = null;
        goto L31
    L14:
        r7 = r24.size();
        goto L15
    L5:
        if (r24.size() <= 5) goto L7;
        r8 = 1;
        goto L12
    L7:
        r8 = 2;
        if (r24.size() > 2) goto L12;
        r8 = 3;
        goto L12
    }

    private /* synthetic */ void lambda$fillWithAnimatedEmoji$230(boolean[] r7, ArrayList[] r8, Runnable r9) {
        loadStickers(5, true, false, false, new MediaDataController$$ExternalSyntheticLambda100(r7, r8, r9));
    }

    private /* synthetic */ void lambda$findStickerSetByNameInCache$29(TLRPC.TL_messages_stickerSet r2, Utilities.Callback r3) {
        putStickerSet(r2, false);
        r3.run(r2);
    }

    private /* synthetic */ void lambda$findStickerSetByNameInCache$30(String r2, Utilities.Callback r3) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda80(this, getCachedStickerSetInternal(r2.toLowerCase(), 0), r3));
    }

    private /* synthetic */ void lambda$getEmojiNames$221(String[] r8, String r9, Utilities.Callback r10) {
        SQLiteCursor r0 = null;
        Object[] r1 = new Object[r8.length + 1];     // Catch: Throwable -> L8 Exception -> L10
        r1[0] = r9;     // Catch: Throwable -> L8 Exception -> L10
        String r92 = "1 = 1";
        int r3 = 0;
    L5:
        if (r3 >= r8.length) goto L17;
        if (r3 != 0) goto L12;
        r92 = "lang = ?";
    L13:
        SQLiteCursor r02 = getMessagesStorage().getDatabase().queryFinalized("SELECT alias FROM emoji_keywords_info_v2 WHERE lang = ?", new Object[]{r8[r3]});     // Catch: Throwable -> L8 Exception -> L10
        if (r02.next() == false) goto L16;
        r8[r3] = r02.stringValue(0);     // Catch: Throwable -> L8 Exception -> L10
    L16:
        r02.dispose();     // Catch: Throwable -> L8 Exception -> L10
        int r4 = r3 + 1;     // Catch: Throwable -> L8 Exception -> L10
        r1[r4] = r8[r3];     // Catch: Throwable -> L8 Exception -> L10
        r3 = r4;
        goto L5
    L12:
        r92 = r92 + " OR lang = ?";     // Catch: Throwable -> L8 Exception -> L10
        goto L13
    L17:
        r0 = getMessagesStorage().getDatabase().executeFast("SELECT keyword FROM emoji_keywords_v2 WHERE emoji = ? AND (" + r92 + ")").query(r1);     // Catch: Throwable -> L8 Exception -> L10
        ArrayList r7 = new ArrayList();     // Catch: Throwable -> L8 Exception -> L10
    L19:
        if (r0.next() == false) goto L21;
        r7.add(r0.stringValue(0));     // Catch: Throwable -> L8 Exception -> L10
        goto L19
    L21:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda8(r10, r7));     // Catch: Throwable -> L8 Exception -> L10
        r0.dispose();
        return;
    L8:
        th = move-exception;
        if (r0 == null) goto L30;
        r0.dispose();
    L30:
        throw th;
    L10:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L8
        if (r0 == null) goto L37;
        r0.dispose();
        return;
    }

    private /* synthetic */ void lambda$getEmojiSuggestions$222(String[] r4, KeywordResultCallback r5, ArrayList r6) {
        int r0 = 0;
    L4:
        if (r0 >= r4.length) goto L9;
        if (this.currentFetchingEmoji.get(r4[r0]) != null) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return;
    L9:
        r5.run(r6, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [int] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [org.telegram.messenger.BaseController] */
    /* JADX WARN: Type inference failed for: r7v8 */
    private /* synthetic */ void lambda$getEmojiSuggestions$227(String[] r17, KeywordResultCallback r18, boolean r19, String r20, boolean r21, ArrayList r22, boolean r23, Integer r24, boolean r25, boolean r26, boolean r27, CountDownLatch r28) {
        ArrayList<KeywordResult> r3 = new ArrayList();
        HashMap r4 = new HashMap();
        String r9 = null;
        MediaDataController r7 = 0;
        boolean r8 = false;
    L73:
    L8:
        e = e;
        r7 = this;
    L65:
        FileLog.e(e);
    L66:
        Collections.sort(r3, new MediaDataController$$ExternalSyntheticLambda114(r22));
        if (r23 == false) goto L69;
        r7.fillWithAnimatedEmoji(r3, r24, r25, r26, r27, new MediaDataController$$ExternalSyntheticLambda115(r28, r18, r3, r9));
        return;
    L69:
        if (r28 == null) goto L71;
        r18.run(r3, r9);
        r28.countDown();
        return;
    L71:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda116(r18, r3, r9));
        return;
    L4:
        if (r7 >= r17.length) goto L75;
        SQLiteCursor r10 = getMessagesStorage().getDatabase().queryFinalized("SELECT alias FROM emoji_keywords_info_v2 WHERE lang = ?", new Object[]{r17[r7]});     // Catch: Exception -> L8
        if (r10.next() == false) goto L10;
        r9 = r10.stringValue(0);     // Catch: Exception -> L8
    L10:
        r10.dispose();     // Catch: Exception -> L8
        if (r9 == null) goto L13;
        r8 = true;
    L13:
        r7 = r7 + 1;     // Catch: Exception -> L8
        goto L73
    L75:
        if (r8 == true) goto L21;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda113(this, r17, r18, r3));     // Catch: Exception -> L19
        return;
    L21:
        r7 = this;
        if (r19 == false) goto L37;
        int[] r82 = new int[1];     // Catch: Exception -> L19
        String r102 = r20;
        ArrayList<Emoji.EmojiSpanRange> r12 = Emoji.parseEmojis(r102, r82);     // Catch: Exception -> L19
        if (r82[0] <= 0) goto L38;
        int r83 = 0;
    L27:
        if (r83 >= r12.size()) goto L38;
        String r13 = r12.get(r83).code.toString();     // Catch: Exception -> L19
        int r14 = 0;
    L30:
        if (r14 >= r3.size()) goto L35;
        if (TextUtils.equals(r3.get(r14).emoji, r13) == true) goto L36;
        r14 = r14 + 1;     // Catch: Exception -> L19
    L36:
        r83 = r83 + 1;     // Catch: Exception -> L19
        goto L27
    L35:
        KeywordResult r142 = new KeywordResult();     // Catch: Exception -> L19
        r142.emoji = r13;     // Catch: Exception -> L19
        r142.keyword = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Exception -> L19
        r3.add(r142);     // Catch: Exception -> L19
    L38:
        String r84 = r102.toLowerCase();     // Catch: Exception -> L19
        int r103 = 0;
    L40:
        if (r103 >= 2) goto L66;
        if (r103 != 1) goto L46;
        String r122 = LocaleController.getInstance().getTranslitString(r84, false, false);     // Catch: Exception -> L19
        if (r122.equals(r84) == true) goto L64;
        r84 = r122;
    L64:
        r103 = r103 + 1;
    L46:
        StringBuilder r123 = new StringBuilder(r84);     // Catch: Exception -> L19
        int r132 = r123.length();     // Catch: Exception -> L19
    L47:
        if (r132 <= 0) goto L51;
        r132 = r132 - 1;
        char r143 = (char) (r123.charAt(r132) + 1);     // Catch: Exception -> L19
        r123.setCharAt(r132, r143);     // Catch: Exception -> L19
        if (r143 == 0) goto L47;
        String r124 = r123.toString();     // Catch: Exception -> L19
    L52:
        if (r21 == false) goto L54;
        SQLiteCursor r125 = r7.getMessagesStorage().getDatabase().queryFinalized("SELECT emoji, keyword FROM emoji_keywords_v2 WHERE keyword = ?", new Object[]{r84});     // Catch: Exception -> L19
    L58:
        if (r125.next() == false) goto L63;
        String r133 = r125.stringValue(0).replace("️", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L19
        if (r4.get(r133) != null) goto L58;
        r4.put(r133, Boolean.TRUE);     // Catch: Exception -> L19
        KeywordResult r144 = new KeywordResult();     // Catch: Exception -> L19
        r144.emoji = r133;     // Catch: Exception -> L19
        r144.keyword = r125.stringValue(1);     // Catch: Exception -> L19
        r3.add(r144);     // Catch: Exception -> L19
        goto L58
    L63:
        r125.dispose();     // Catch: Exception -> L19
        goto L64
    L54:
        if (r124 == null) goto L56;
        r125 = r7.getMessagesStorage().getDatabase().queryFinalized("SELECT emoji, keyword FROM emoji_keywords_v2 WHERE keyword >= ? AND keyword < ?", new Object[]{r84, r124});     // Catch: Exception -> L19
        goto L58
    L56:
        r84 = r84 + "%";     // Catch: Exception -> L19
        r125 = r7.getMessagesStorage().getDatabase().queryFinalized("SELECT emoji, keyword FROM emoji_keywords_v2 WHERE keyword LIKE ?", new Object[]{r84});     // Catch: Exception -> L19
        goto L58
    L51:
        r124 = null;
        goto L52
    L37:
        r102 = r20;
    L19:
        e = e;
        goto L65
    }

    private /* synthetic */ void lambda$getMediaCount$134(long r12, long r14, int r16, int r17, TLObject r18, TLRPC.TL_error r19) {
        if ((r18 instanceof Vector) == false) goto L8;
        Vector r0 = (Vector) r18;
        if (r0.objects.isEmpty() == true) goto L9;
        processLoadedMediaCount(((TLRPC.TL_messages_searchCounter) r0.objects.get(0)).count, r12, r14, r16, r17, false, 0);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$getMediaCountDatabase$141(long r15, long r17, int r19, int r20) {
        if (r15 == 0) goto L5;
        SQLiteCursor r0 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT count, old FROM media_counts_topics WHERE uid = %d AND topic_id = %d AND type = %d LIMIT 1", new Object[]{Long.valueOf(r17), Long.valueOf(r15), Integer.valueOf(r19)}), new Object[0]);     // Catch: Exception -> L24
    L7:
        if (r0.next() == false) goto L9;
        int r2 = r0.intValue(0);     // Catch: Exception -> L24
        int r13 = r0.intValue(1);     // Catch: Exception -> L24
    L10:
        r0.dispose();     // Catch: Exception -> L24
        if (r2 == (-1)) goto L13;
    L21:
        int r5 = r2;
    L22:
        processLoadedMediaCount(r5, r17, r15, r19, r20, true, r13);     // Catch: Exception -> L24
        return;
    L13:
        if (DialogObject.isEncryptedDialog(r17) == false) goto L21;
        SQLiteCursor r02 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT COUNT(mid) FROM media_v4 WHERE uid = %d AND type = %d LIMIT 1", new Object[]{Long.valueOf(r17), Integer.valueOf(r19)}), new Object[0]);     // Catch: Exception -> L24
        if (r02.next() == false) goto L17;
        r2 = r02.intValue(0);     // Catch: Exception -> L24
    L17:
        int r6 = r2;
        r02.dispose();     // Catch: Exception -> L24
        if (r6 == (-1)) goto L20;
        putMediaCountDatabase(r17, r15, r19, r6);     // Catch: Exception -> L24
    L20:
        r5 = r6;
        goto L22
    L9:
        r13 = 0;
        r2 = -1;
        goto L10
    L5:
        r0 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT count, old FROM media_counts_v2 WHERE uid = %d AND type = %d LIMIT 1", new Object[]{Long.valueOf(r17), Integer.valueOf(r19)}), new Object[0]);     // Catch: Exception -> L24
    L24:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$getMediaCounts$129(long r2, long r4, int[] r6) {
        getNotificationCenter().postNotificationName(NotificationCenter.mediaCountsDidLoad, new Object[]{Long.valueOf(r2), Long.valueOf(r4), r6});
    }

    private /* synthetic */ void lambda$getMediaCounts$130(long r2, long r4, int[] r6) {
        getNotificationCenter().postNotificationName(NotificationCenter.mediaCountsDidLoad, new Object[]{Long.valueOf(r2), Long.valueOf(r4), r6});
    }

    private /* synthetic */ void lambda$getMediaCounts$131(int[] r14, long r15, long r17, TLObject r19, TLRPC.TL_error r20) {
        int r2 = 0;
    L4:
        if (r2 >= r14.length) goto L10;
        if (r14[r2] >= 0) goto L8;
        r14[r2] = 0;
    L8:
        r2 = r2 + 1;
        goto L4
    L10:
        if ((r19 instanceof Vector) == false) goto L43;
        Vector r0 = (Vector) r19;
        int r22 = r0.objects.size();
        int r3 = 0;
    L12:
        if (r3 >= r22) goto L43;
        TLRPC.TL_messages_searchCounter r4 = (TLRPC.TL_messages_searchCounter) r0.objects.get(r3);
        TLRPC.MessagesFilter r5 = r4.filter;
        if ((r5 instanceof TLRPC.TL_inputMessagesFilterPhotoVideo) == false) goto L17;
        int r11 = 0;
    L41:
        int r12 = r4.count;
        r14[r11] = r12;
        putMediaCountDatabase(r15, r17, r11, r12);
    L42:
        r3 = r3 + 1;
        goto L12
    L17:
        if ((r5 instanceof TLRPC.TL_inputMessagesFilterDocument) == false) goto L21;
        int r52 = 1;
    L19:
        r11 = r52;
        goto L41
    L21:
        if ((r5 instanceof TLRPC.TL_inputMessagesFilterRoundVoice) == false) goto L24;
        r52 = 2;
        goto L19
    L24:
        if ((r5 instanceof TLRPC.TL_inputMessagesFilterUrl) == false) goto L27;
        r52 = 3;
        goto L19
    L27:
        if ((r5 instanceof TLRPC.TL_inputMessagesFilterMusic) == false) goto L30;
        r52 = 4;
        goto L19
    L30:
        if ((r5 instanceof TLRPC.TL_inputMessagesFilterGif) == false) goto L33;
        r52 = 5;
        goto L19
    L33:
        if ((r5 instanceof TLRPC.TL_inputMessagesFilterPhotos) == false) goto L36;
        r52 = 6;
        goto L19
    L36:
        if ((r5 instanceof TLRPC.TL_inputMessagesFilterVideo) == false) goto L39;
        r52 = 7;
        goto L19
    L39:
        if ((r5 instanceof TLRPC.TL_inputMessagesFilterPoll) == false) goto L42;
        r52 = 8;
    L43:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda49(this, r15, r17, r14));
    }

    private /* synthetic */ void lambda$getMediaCounts$132(long r2, long r4, int[] r6) {
        getNotificationCenter().postNotificationName(NotificationCenter.mediaCountsDidLoad, new Object[]{Long.valueOf(r2), Long.valueOf(r4), r6});
    }

    private /* synthetic */ void lambda$getMediaCounts$133(long r20, long r22, int r24) {
        int r7 = 9;
        int[] r8 = new int[9];     // Catch: Exception -> L83
        int r9 = -1;
        r8[0] = -1;     // Catch: Exception -> L83
        r8[1] = -1;     // Catch: Exception -> L83
        int r1 = 2;
        r8[2] = -1;     // Catch: Exception -> L83
        r8[3] = -1;     // Catch: Exception -> L83
        int r2 = 4;
        r8[4] = -1;     // Catch: Exception -> L83
        r8[5] = -1;     // Catch: Exception -> L83
        r8[6] = -1;     // Catch: Exception -> L83
        r8[7] = -1;     // Catch: Exception -> L83
        r8[8] = -1;     // Catch: Exception -> L83
        int[] r12 = new int[9];     // Catch: Exception -> L83
        r12[0] = -1;     // Catch: Exception -> L83
        r12[1] = -1;     // Catch: Exception -> L83
        r12[2] = -1;     // Catch: Exception -> L83
        r12[3] = -1;     // Catch: Exception -> L83
        r12[4] = -1;     // Catch: Exception -> L83
        r12[5] = -1;     // Catch: Exception -> L83
        r12[6] = -1;     // Catch: Exception -> L83
        r12[7] = -1;     // Catch: Exception -> L83
        r12[8] = -1;     // Catch: Exception -> L83
        int[] r13 = new int[9];     // Catch: Exception -> L83
        r13[0] = 0;     // Catch: Exception -> L83
        r13[1] = 0;     // Catch: Exception -> L83
        r13[2] = 0;     // Catch: Exception -> L83
        r13[3] = 0;     // Catch: Exception -> L83
        r13[4] = 0;     // Catch: Exception -> L83
        r13[5] = 0;     // Catch: Exception -> L83
        r13[6] = 0;     // Catch: Exception -> L83
        r13[7] = 0;     // Catch: Exception -> L83
        r13[8] = 0;     // Catch: Exception -> L83
        if (r20 == 0) goto L6;
        SQLiteCursor r4 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT type, count, old FROM media_counts_topics WHERE uid = %d AND topic_id = %d", new Object[]{Long.valueOf(r22), Long.valueOf(r20)}), new Object[0]);     // Catch: Exception -> L83
    L8:
        if (r4.next() == false) goto L13;
        int r5 = r4.intValue(0);     // Catch: Exception -> L83
        if (r5 < 0) goto L8;
        if (r5 >= 9) goto L8;
        int r6 = r4.intValue(1);     // Catch: Exception -> L83
        r8[r5] = r6;     // Catch: Exception -> L83
        r12[r5] = r6;     // Catch: Exception -> L83
        r13[r5] = r4.intValue(2);     // Catch: Exception -> L83
        goto L8
    L13:
        r4.dispose();     // Catch: Exception -> L83
        if (DialogObject.isEncryptedDialog(r22) == false) goto L27;
        int r52 = 0;
    L16:
        if (r52 >= 9) goto L25;
        if (r8[r52] != (-1)) goto L24;
        SQLiteCursor r0 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT COUNT(mid) FROM media_v4 WHERE uid = %d AND type = %d LIMIT 1", new Object[]{Long.valueOf(r22), Integer.valueOf(r52)}), new Object[0]);     // Catch: Exception -> L83
        if (r0.next() == false) goto L22;
        r8[r52] = r0.intValue(0);     // Catch: Exception -> L83
    L23:
        r0.dispose();     // Catch: Exception -> L83
        putMediaCountDatabase(r22, r20, r52, r8[r52]);     // Catch: Exception -> L83
        goto L24
    L22:
        r8[r52] = 0;     // Catch: Exception -> L83
    L24:
        r52 = r52 + 1;     // Catch: Exception -> L83
        goto L16
    L25:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda223(this, r22, r20, r8));     // Catch: Exception -> L83
        return;
    L27:
        TLRPC.TL_messages_getSearchCounters r82 = new TLRPC.TL_messages_getSearchCounters();     // Catch: Exception -> L83
        r82.peer = getMessagesController().getInputPeer(r22);     // Catch: Exception -> L83
        if (r20 != 0) goto L30;
    L33:
        int r3 = 0;
        boolean r10 = false;
    L34:
        if (r3 >= r7) goto L74;
        if (r82.peer != null) goto L40;
        r8[r3] = 0;     // Catch: Exception -> L83
    L38:
        int r23 = r9;
    L72:
        r3 = r3 + 1;     // Catch: Exception -> L83
        r9 = r23;
        r1 = 2;
        r2 = 4;
        r7 = 9;
        goto L34
    L40:
        if (r8[r3] != r9) goto L42;
    L43:
        if (r3 != 0) goto L47;
        r82.filters.add(new TLRPC.TL_inputMessagesFilterPhotoVideo());     // Catch: Exception -> L83
    L66:
        r23 = -1;
        if (r8[r3] != (-1)) goto L70;
        r10 = true;
        goto L72
    L70:
        if (r13[r3] != 1) goto L72;
        r8[r3] = -1;     // Catch: Exception -> L83
        goto L72
    L47:
        if (r3 != 1) goto L49;
        r82.filters.add(new TLRPC.TL_inputMessagesFilterDocument());     // Catch: Exception -> L83
        goto L66
    L49:
        if (r3 != r1) goto L51;
        r82.filters.add(new TLRPC.TL_inputMessagesFilterRoundVoice());     // Catch: Exception -> L83
        goto L66
    L51:
        if (r3 != 3) goto L53;
        r82.filters.add(new TLRPC.TL_inputMessagesFilterUrl());     // Catch: Exception -> L83
        goto L66
    L53:
        if (r3 != r2) goto L56;
        r82.filters.add(new TLRPC.TL_inputMessagesFilterMusic());     // Catch: Exception -> L83
        goto L66
    L56:
        if (r3 != 6) goto L59;
        r82.filters.add(new TLRPC.TL_inputMessagesFilterPhotos());     // Catch: Exception -> L83
        goto L66
    L59:
        if (r3 != 7) goto L62;
        r82.filters.add(new TLRPC.TL_inputMessagesFilterVideo());     // Catch: Exception -> L83
        goto L66
    L62:
        ArrayList<TLRPC.MessagesFilter> r14 = r82.filters;
        if (r3 != 8) goto L65;
        r14.add(new TLRPC.TL_inputMessagesFilterPoll());     // Catch: Exception -> L83
        goto L66
    L65:
        r14.add(new TLRPC.TL_inputMessagesFilterGif());     // Catch: Exception -> L83
        goto L66
    L42:
        if (r13[r3] != 1) goto L38;
    L74:
        if (r82.filters.isEmpty() == true) goto L76;
        getConnectionsManager().bindRequestToGuid(getConnectionsManager().sendRequest(r82, new MediaDataController$$ExternalSyntheticLambda224(this, r8, r22, r20)), r24);     // Catch: Exception -> L83
    L76:
        if (r10 == true) goto L78;
    L81:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda225(this, r22, r20, r12));     // Catch: Exception -> L83
        return;
    L78:
        if (getConnectionsManager().getConnectionState() != 3) goto L81;
        return;
    L30:
        if (r22 != getUserConfig().getClientUserId()) goto L32;
        r82.saved_peer_id = getMessagesController().getInputPeer(r20);     // Catch: Exception -> L83
        r82.flags |= 4;
        goto L33
    L32:
        r82.top_msg_id = (int) r20;     // Catch: Exception -> L83
        r82.flags |= 1;
        goto L33
    L6:
        r4 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT type, count, old FROM media_counts_v2 WHERE uid = %d", new Object[]{Long.valueOf(r22)}), new Object[0]);     // Catch: Exception -> L83
    L83:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$getStickerSet$33(String r3, Utilities.Callback r4, Boolean r5, TLRPC.TL_messages_stickerSet r6) {
        this.loadingStickerSetsKeys.remove(r3);
        if (r4 == null) goto L5;
        r4.run(r6);
    L5:
        if (r6 == null) goto L10;
        TLRPC.StickerSet r32 = r6.set;
        if (r32 == null) goto L11;
        this.stickerSetsById.put(r32.id, r6);
        this.stickerSetsByName.put(r6.set.short_name.toLowerCase(), r6);
        saveStickerSetIntoCache(r6);
        getNotificationCenter().postNotificationName(NotificationCenter.groupStickersDidLoad, new Object[]{Long.valueOf(r6.set.id), r6});
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$getStickerSet$34(TLRPC.TL_messages_stickerSet r1, String r2, Utilities.Callback r3, boolean r4, TLRPC.InputStickerSet r5) {
        if (r1 == null) goto L11;
        this.loadingStickerSetsKeys.remove(r2);
        if (r3 == null) goto L6;
        r3.run(r1);
    L6:
        TLRPC.StickerSet r22 = r1.set;
        if (r22 == null) goto L9;
        this.stickerSetsById.put(r22.id, r1);
        this.stickerSetsByName.put(r1.set.short_name.toLowerCase(), r1);
    L9:
        getNotificationCenter().postNotificationName(NotificationCenter.groupStickersDidLoad, new Object[]{Long.valueOf(r1.set.id), r1});
        return;
    L11:
        if (r4 == true) goto L14;
        fetchStickerSetInternal(r5, new MediaDataController$$ExternalSyntheticLambda27(this, r2, r3));
        return;
    L14:
        this.loadingStickerSetsKeys.remove(r2);
    }

    private /* synthetic */ void lambda$getStickerSet$35(TLRPC.InputStickerSet r10, Integer r11, String r12, Utilities.Callback r13, boolean r14) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda127(this, getCachedStickerSetInternal(r10.id, r11), r12, r13, r14, r10));
    }

    private /* synthetic */ void lambda$getStickerSet$36(String r3, Utilities.Callback r4, Boolean r5, TLRPC.TL_messages_stickerSet r6) {
        this.loadingStickerSetsKeys.remove(r3);
        if (r4 == null) goto L5;
        r4.run(r6);
    L5:
        if (r6 == null) goto L10;
        TLRPC.StickerSet r32 = r6.set;
        if (r32 == null) goto L11;
        this.stickerSetsById.put(r32.id, r6);
        this.stickerSetsByName.put(r6.set.short_name.toLowerCase(), r6);
        saveStickerSetIntoCache(r6);
        getNotificationCenter().postNotificationName(NotificationCenter.groupStickersDidLoad, new Object[]{Long.valueOf(r6.set.id), r6});
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$getStickerSet$37(TLRPC.TL_messages_stickerSet r1, String r2, Utilities.Callback r3, boolean r4, TLRPC.InputStickerSet r5) {
        if (r1 == null) goto L11;
        this.loadingStickerSetsKeys.remove(r2);
        if (r3 == null) goto L6;
        r3.run(r1);
    L6:
        TLRPC.StickerSet r22 = r1.set;
        if (r22 == null) goto L9;
        this.stickerSetsById.put(r22.id, r1);
        this.stickerSetsByName.put(r1.set.short_name.toLowerCase(), r1);
    L9:
        getNotificationCenter().postNotificationName(NotificationCenter.groupStickersDidLoad, new Object[]{Long.valueOf(r1.set.id), r1});
        return;
    L11:
        if (r4 == true) goto L14;
        fetchStickerSetInternal(r5, new MediaDataController$$ExternalSyntheticLambda60(this, r2, r3));
        return;
    L14:
        this.loadingStickerSetsKeys.remove(r2);
    }

    private /* synthetic */ void lambda$getStickerSet$38(TLRPC.InputStickerSet r9, Integer r10, String r11, Utilities.Callback r12, boolean r13) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda110(this, getCachedStickerSetInternal(r9.short_name.toLowerCase(), r10), r11, r12, r13, r9));
    }

    private /* synthetic */ void lambda$getStickerSet$39(String r3, Utilities.Callback r4, TLRPC.InputStickerSet r5, Boolean r6, TLRPC.TL_messages_stickerSet r7) {
        this.loadingStickerSetsKeys.remove(r3);
        if (r4 == null) goto L5;
        r4.run(r7);
    L5:
        if (r7 == null) goto L15;
        TLRPC.StickerSet r32 = r7.set;
        if (r32 == null) goto L13;
        this.stickerSetsById.put(r32.id, r7);
        this.stickerSetsByName.put(r7.set.short_name.toLowerCase(), r7);
        boolean r33 = r5 instanceof TLRPC.TL_inputStickerSetEmojiDefaultStatuses;
        if (r33 == false) goto L11;
        this.stickerSetDefaultStatuses = r7;
    L11:
        if (r33 == false) goto L13;
        this.stickerSetDefaultChannelStatuses = r7;
    L13:
        saveStickerSetIntoCache(r7);
        getNotificationCenter().postNotificationName(NotificationCenter.groupStickersDidLoad, new Object[]{Long.valueOf(r7.set.id), r7});
        return;
    }

    private /* synthetic */ void lambda$increasePeerRaiting$158(long r10, double r12) {
        int r1 = 0;
    L4:
        if (r1 >= this.hints.size()) goto L9;
        TLRPC.TL_topPeer r2 = this.hints.get(r1);
        if (r2.peer.user_id == r10) goto L10;
        r1 = r1 + 1;
    L10:
        if (r2 != null) goto L12;
        r2 = new TLRPC.TL_topPeer();
        TLRPC.TL_peerUser r13 = new TLRPC.TL_peerUser();
        r2.peer = r13;
        r13.user_id = r10;
        this.hints.add(r2);
    L12:
        r2.rating += Math.exp(r12 / getMessagesController().ratingDecay);
        Collections.sort(this.hints, new MediaDataController$$ExternalSyntheticLambda84());
        savePeer(r10, 0, r2.rating);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadHints, new Object[0]);
        return;
    L9:
        r2 = null;
        goto L10
    }

    private /* synthetic */ void lambda$increasePeerRaiting$159(long r10) {
        double r1 = 0.0d;
        int r4 = 0;
        SQLiteCursor r0 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT MAX(mid), MAX(date) FROM messages_v2 WHERE uid = %d AND out = 1", new Object[]{Long.valueOf(r10)}), new Object[0]);     // Catch: Exception -> L6
        if (r0.next() == false) goto L8;
        r4 = r0.intValue(0);     // Catch: Exception -> L6
        int r3 = r0.intValue(1);     // Catch: Exception -> L6
    L9:
        r0.dispose();     // Catch: Exception -> L6
        if (r4 <= 0) goto L17;
        if (getUserConfig().ratingLoadTime == 0) goto L17;
        r1 = r3 - getUserConfig().ratingLoadTime;
    L17:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda12(this, r10, r1));
        return;
    L8:
        r3 = 0;
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L17
    }

    private /* synthetic */ void lambda$loadArchivedStickersCount$72(TLRPC.TL_error r3, TLObject r4, int r5) {
        if (r3 != null) goto L5;
        TLRPC.TL_messages_archivedStickers r42 = (TLRPC.TL_messages_archivedStickers) r4;
        this.archivedStickersCount[r5] = r42.count;
        MessagesController.getNotificationsSettings(this.currentAccount).edit().putInt("archivedStickersCount" + r5, r42.count).apply();
        getNotificationCenter().postNotificationName(NotificationCenter.archivedStickersCountDidLoad, new Object[]{Integer.valueOf(r5)});
        return;
    }

    private /* synthetic */ void lambda$loadArchivedStickersCount$73(int r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda18(this, r4, r3, r2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v2, types: [org.telegram.tgnet.TLRPC$TL_attachMenuBots] */
    private /* synthetic */ void lambda$loadAttachMenuBots$3() {
        SQLiteCursor r1 = null;
        long r2 = 0;
        int r4 = 0;
        SQLiteCursor r5 = getMessagesStorage().getDatabase().queryFinalized("SELECT data, hash, date FROM attach_menu_bots", new Object[0]);     // Catch: Throwable -> L30 Exception -> L32
    L11:
        th = move-exception;
        Throwable r14 = th;
        r1 = r5;
    L40:
        if (r1 == null) goto L51;
        r1.dispose();
        throw r14;
    L51:
        throw r14;
    L13:
        e = e;
        long r6 = r2;
        TLRPC.TL_attachMenuBots r22 = r1;
    L15:
        r1 = r5;
    L34:
        FileLog.e(e);     // Catch: Throwable -> L30
        if (r1 == null) goto L37;
        r1.dispose();
    L37:
        int r12 = r4;
    L38:
        processLoadedMenuBots(r22, r6, r12, true);
        return;
    L5:
        if (r5.next() == false) goto L19;
        NativeByteBuffer r0 = r5.byteBufferValue(0);     // Catch: Throwable -> L11 Exception -> L13
        if (r0 == null) goto L17;
        TLRPC.AttachMenuBots r7 = TLRPC.AttachMenuBots.TLdeserialize(r0, r0.readInt32(false), true);     // Catch: Throwable -> L11 Exception -> L13
        if ((r7 instanceof TLRPC.TL_attachMenuBots) == false) goto L16;
        r1 = (TLRPC.TL_attachMenuBots) r7;     // Catch: Throwable -> L11 Exception -> L13
    L16:
        r0.reuse();     // Catch: Throwable -> L11 Exception -> L13
    L17:
        r2 = r5.longValue(1);     // Catch: Throwable -> L11 Exception -> L13
        r6 = r2;
        r22 = r1;
        int r13 = r5.intValue(2);     // Catch: Throwable -> L11 Exception -> L13
    L20:
        if (r22 != null) goto L49;
    L28:
        r5.dispose();
        r12 = r13;
        goto L38
    L49:
        ArrayList<Long> r02 = new ArrayList();     // Catch: Throwable -> L11 Exception -> L25
    L23:
        if (r4 >= r22.bots.size()) goto L27;
        r02.add(Long.valueOf(r22.bots.get(r4).bot_id));     // Catch: Throwable -> L11 Exception -> L25
        r4 = r4 + 1;     // Catch: Throwable -> L11 Exception -> L25
        goto L23
    L27:
        r22.users.addAll(getMessagesStorage().getUsers(r02));     // Catch: Throwable -> L11 Exception -> L25
    L25:
        e = e;
        r4 = r13;
        goto L15
    L19:
        r6 = 0;
        r22 = null;
        r13 = 0;
    L32:
        e = e;
        r6 = 0;
        r22 = null;
    L30:
        th = move-exception;
        r14 = th;
        goto L40
    }

    private /* synthetic */ void lambda$loadAttachMenuBots$4(Runnable r9, TLObject r10, TLRPC.TL_error r11) {
        int r6 = (int) (System.currentTimeMillis() / 1000);
        if ((r10 instanceof TLRPC.TL_attachMenuBotsNotModified) == false) goto L6;
        processLoadedMenuBots(null, 0, r6, false);
    L8:
        if (r9 == null) goto L11;
        AndroidUtilities.runOnUIThread(r9);
        return;
    L11:
        return;
    L6:
        if ((r10 instanceof TLRPC.TL_attachMenuBots) == false) goto L8;
        TLRPC.TL_attachMenuBots r3 = (TLRPC.TL_attachMenuBots) r10;
        processLoadedMenuBots(r3, r3.hash, r6, false);
        goto L8
    }

    private /* synthetic */ void lambda$loadAvatarConstructor$244(TLObject r3, SharedPreferences r4, boolean r5) {
        if ((r3 instanceof TLRPC.TL_emojiList) == false) goto L10;
        SerializedData r0 = new SerializedData(r3.getObjectSize());
        r3.serializeToStream(r0);
        SharedPreferences.Editor r42 = r4.edit();
        if (r5 == false) goto L7;
        this.profileAvatarConstructorDefault = (TLRPC.TL_emojiList) r3;
        r42.putString("profile", Utilities.bytesToHex(r0.toByteArray()));
        r42.putLong("profile_last_check", System.currentTimeMillis());
    L8:
        r42.apply();
        return;
    L7:
        this.groupAvatarConstructorDefault = (TLRPC.TL_emojiList) r3;
        r42.putString("group", Utilities.bytesToHex(r0.toByteArray()));
        r42.putLong("group_last_check", System.currentTimeMillis());
        goto L8
    }

    private /* synthetic */ void lambda$loadAvatarConstructor$245(SharedPreferences r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda56(this, r3, r1, r2));
    }

    private /* synthetic */ void lambda$loadBotInfo$200(Utilities.Callback r1, TL_bots.BotInfo r2, int r3) {
        if (r1 == null) goto L4;
        r1.run(r2);
    L4:
        getNotificationCenter().postNotificationName(NotificationCenter.botInfoDidLoad, new Object[]{r2, Integer.valueOf(r3)});
    }

    private /* synthetic */ void lambda$loadBotInfo$202(long r1, long r3, Utilities.Callback r5, int r6) {
        TL_bots.BotInfo r12 = loadBotInfoInternal(r1, r3);     // Catch: Exception -> L9
        if (r12 == null) goto L6;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda57(this, r5, r12, r6));     // Catch: Exception -> L9
        return;
    L6:
        if (r5 == null) goto L13;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda58(r5));     // Catch: Exception -> L9
        return;
    L13:
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$loadBotKeyboard$198(TLRPC.Message r2, MessagesStorage.TopicKey r3) {
        getNotificationCenter().postNotificationName(NotificationCenter.botKeyboardDidLoad, new Object[]{r2, r3});
    }

    private /* synthetic */ void lambda$loadBotKeyboard$199(MessagesStorage.TopicKey r8, boolean r9) {
    L21:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (r8.topicId == 0) goto L5;
        SQLiteCursor r0 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT info FROM bot_keyboard_topics WHERE uid = %d AND tid = %d", new Object[]{Long.valueOf(r8.dialogId), Long.valueOf(r8.topicId)}), new Object[0]);     // Catch: Exception -> L21
    L7:
        if (r0.next() == true) goto L9;
    L13:
        TLRPC.Message r1 = null;
    L14:
        r0.dispose();     // Catch: Exception -> L21
        if (r1 != null) goto L19;
        if (r9 == true) goto L19;
        return;
    L19:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda137(this, r1, r8));     // Catch: Exception -> L21
        return;
    L9:
        if (r0.isNull(0) == true) goto L13;
        NativeByteBuffer r2 = r0.byteBufferValue(0);     // Catch: Exception -> L21
        if (r2 == null) goto L13;
        r1 = TLRPC.Message.TLdeserialize(r2, r2.readInt32(false), false);     // Catch: Exception -> L21
        r2.reuse();     // Catch: Exception -> L21
        goto L14
    L5:
        r0 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT info FROM bot_keyboard WHERE uid = %d", new Object[]{Long.valueOf(r8.dialogId)}), new Object[0]);     // Catch: Exception -> L21
        goto L7
    }

    private /* synthetic */ void lambda$loadDraftsIfNeed$188() {
        this.loadingDrafts = false;
    }

    private /* synthetic */ void lambda$loadDraftsIfNeed$189() {
        this.loadingDrafts = false;
        UserConfig r2 = getUserConfig();
        r2.draftsLoaded = true;
        r2.saveConfig(false);
    }

    private /* synthetic */ void lambda$loadDraftsIfNeed$190(TLObject r2, TLRPC.TL_error r3) {
        if (r3 == null) goto L5;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda34(this));
        return;
    L5:
        getMessagesController().processUpdates((TLRPC.Updates) r2, false);
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda35(this));
    }

    private /* synthetic */ void lambda$loadFeaturedStickers$56(boolean r14) {
        ArrayList<Long> r4 = new ArrayList();
        SQLiteCursor r1 = null;
        int r2 = 0;
        long r5 = 0;
        SQLiteDatabase r3 = getMessagesStorage().getDatabase();     // Catch: Throwable -> L39
        StringBuilder r7 = new StringBuilder("SELECT data, unread, date, hash, premium FROM stickers_featured WHERE emoji = ");     // Catch: Throwable -> L39
        r7.append(r14 ? 1 : 0);     // Catch: Throwable -> L39
        r7.append(" AND id = ");     // Catch: Throwable -> L39
        if (r14 == false) goto L6;
        int r9 = 2;
    L7:
        r7.append(r9);     // Catch: Throwable -> L39
        SQLiteCursor r32 = r3.queryFinalized(r7.toString(), new Object[0]);     // Catch: Throwable -> L39
    L21:
        th = th;
        ArrayList<TLRPC.StickerSetCovered> r92 = r1;
    L17:
        int r72 = 0;
    L18:
        r1 = r32;
    L58:
        FileLog.e(th);     // Catch: Throwable -> L47
        if (r1 == null) goto L44;
        r1.dispose();
    L44:
        ArrayList<TLRPC.StickerSetCovered> r33 = r92;
        long r8 = r5;
        boolean r52 = false;
    L45:
        processLoadedFeaturedStickers(r14, r33, r4, r52, true, r72, r8);
        return;
    L47:
        th = move-exception;
        if (r1 == null) goto L64;
        r1.dispose();
        throw th;
    L64:
        throw th;
    L9:
        if (r32.next() == false) goto L37;
        NativeByteBuffer r73 = r32.byteBufferValue(0);     // Catch: Throwable -> L21
        if (r73 == null) goto L23;
        r92 = new ArrayList();     // Catch: Throwable -> L21
        int r12 = r73.readInt32(false);     // Catch: Throwable -> L16
        int r10 = 0;
    L14:
        if (r10 >= r12) goto L19;
        r92.add(TLRPC.StickerSetCovered.TLdeserialize(r73, r73.readInt32(false), false));     // Catch: Throwable -> L16
        r10 = r10 + 1;     // Catch: Throwable -> L16
        goto L14
    L19:
        r73.reuse();     // Catch: Throwable -> L16
        r1 = r92;
    L16:
        th = th;
    L23:
        NativeByteBuffer r74 = r32.byteBufferValue(1);     // Catch: Throwable -> L21
        if (r74 == null) goto L29;
        int r93 = r74.readInt32(false);     // Catch: Throwable -> L21
        int r102 = 0;
    L26:
        if (r102 >= r93) goto L28;
        r4.add(Long.valueOf(r74.readInt64(false)));     // Catch: Throwable -> L21
        r102 = r102 + 1;     // Catch: Throwable -> L21
        goto L26
    L28:
        r74.reuse();     // Catch: Throwable -> L21
    L29:
        r72 = r32.intValue(2);     // Catch: Throwable -> L21
        r5 = r32.longValue(3);     // Catch: Throwable -> L35
        if (r32.intValue(4) != 1) goto L34;
        r2 = 1;
    L34:
        int r0 = r2;
        r2 = r72;
    L38:
        r32.dispose();
        r33 = r1;
        r72 = r2;
        r8 = r5;
        r52 = r0;
    L35:
        th = th;
        r92 = r1;
        goto L18
    L37:
        r0 = 0;
        goto L38
    L6:
        r9 = 1;
    L39:
        th = th;
        r92 = null;
        r72 = 0;
        goto L58
    }

    private /* synthetic */ void lambda$loadFeaturedStickers$57(boolean r3, int r4) {
        this.loadingFeaturedStickers[r3 ? 1 : 0] = false;
        this.featuredStickersLoaded[r3 ? 1 : 0] = true;
        this.loadFeaturedDate[r3 ? 1 : 0] = r4;
    }

    private /* synthetic */ void lambda$loadFeaturedStickers$58(TLObject r23, boolean r24, long r25) {
        if ((r23 instanceof TLRPC.TL_messages_featuredStickers) == false) goto L7;
        TLRPC.TL_messages_featuredStickers r0 = (TLRPC.TL_messages_featuredStickers) r23;
        processLoadedFeaturedStickers(r24, r0.sets, r0.unread, r0.premium, false, (int) (System.currentTimeMillis() / 1000), r0.hash);
        return;
    L7:
        if ((r23 instanceof TLRPC.TL_messages_featuredStickersNotModified) == false) goto L10;
        int r02 = (int) (System.currentTimeMillis() / 1000);
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda226(this, r24, r02));
        putFeaturedStickersToCache(r24, null, null, r02, r25, false);
        return;
    L10:
        processLoadedFeaturedStickers(r24, null, null, false, false, (int) (System.currentTimeMillis() / 1000), r25);
    }

    private /* synthetic */ void lambda$loadFeaturedStickers$59(boolean r3, long r4, TLObject r6, TLRPC.TL_error r7) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda19(this, r6, r3, r4));
    }

    private /* synthetic */ void lambda$loadGroupStickerSet$44(TLRPC.TL_messages_stickerSet r4) {
        this.groupStickerSets.put(r4.set.id, r4);
        getNotificationCenter().postNotificationName(NotificationCenter.groupStickersDidLoad, new Object[]{Long.valueOf(r4.set.id), r4});
    }

    private /* synthetic */ void lambda$loadGroupStickerSet$45(TLRPC.StickerSet r6) {
        SQLiteCursor r0 = getMessagesStorage().getDatabase().queryFinalized("SELECT document FROM web_recent_v3 WHERE id = 's_" + r6.id + "'", new Object[0]);     // Catch: Throwable -> L22
        TLRPC.TL_messages_stickerSet r3 = null;
        if (r0.next() == true) goto L6;
    L10:
        r0.dispose();     // Catch: Throwable -> L22
        if (r3 == null) goto L16;
        TLRPC.StickerSet r02 = r3.set;     // Catch: Throwable -> L22
        if (r02 == null) goto L16;
        if (r02.hash != r6.hash) goto L16;
    L17:
        if (r3 != null) goto L19;
        return;
    L19:
        if (r3.set == null) goto L28;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda228(this, r3));     // Catch: Throwable -> L22
        return;
    L28:
        return;
    L16:
        loadGroupStickerSet(r6, false);     // Catch: Throwable -> L22
        goto L17
    L6:
        if (r0.isNull(0) == true) goto L10;
        NativeByteBuffer r1 = r0.byteBufferValue(0);     // Catch: Throwable -> L22
        if (r1 == null) goto L10;
        r3 = TLRPC.messages_StickerSet.TLdeserialize(r1, r1.readInt32(false), false);     // Catch: Throwable -> L22
        r1.reuse();     // Catch: Throwable -> L22
    L22:
        th = move-exception;
        FileLog.e(th);
    }

    private /* synthetic */ void lambda$loadGroupStickerSet$46(TLRPC.TL_messages_stickerSet r4) {
        this.groupStickerSets.put(r4.set.id, r4);
        getNotificationCenter().postNotificationName(NotificationCenter.groupStickersDidLoad, new Object[]{Long.valueOf(r4.set.id), r4});
    }

    private /* synthetic */ void lambda$loadGroupStickerSet$47(TLObject r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda125(this, (TLRPC.TL_messages_stickerSet) r1));
        return;
    }

    private /* synthetic */ void lambda$loadHints$146(ArrayList r3, ArrayList r4, ArrayList r5, ArrayList r6, ArrayList r7, ArrayList r8) {
        getMessagesController().putUsers(r3, true);
        getMessagesController().putChats(r4, true);
        this.loading = false;
        this.loaded = true;
        this.hints = r5;
        this.inlineBots = r6;
        this.guestBots = r7;
        this.webapps = r8;
        buildShortcuts();
        getNotificationCenter().postNotificationName(NotificationCenter.reloadHints, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadInlineHints, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadGuestBotHints, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadWebappsHints, new Object[0]);
        if (Math.abs(getUserConfig().lastHintsSyncTime - ((int) (System.currentTimeMillis() / 1000))) < 86400) goto L5;
    L8:
        loadHints(false);
        return;
    L5:
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L8;
    }

    private /* synthetic */ void lambda$loadHints$147() {
        ArrayList r4 = new ArrayList();
        ArrayList r5 = new ArrayList();
        ArrayList r6 = new ArrayList();
        ArrayList r7 = new ArrayList();
        ArrayList<TLRPC.User> r2 = new ArrayList();
        ArrayList<TLRPC.Chat> r3 = new ArrayList();
        long r0 = getUserConfig().getClientUserId();
        ArrayList<Long> r8 = new ArrayList();     // Catch: Exception -> L32
        ArrayList r9 = new ArrayList();     // Catch: Exception -> L32
        int r12 = 0;
        SQLiteCursor r10 = getMessagesStorage().getDatabase().queryFinalized("SELECT did, type, rating FROM chat_hints WHERE 1 ORDER BY rating DESC", new Object[0]);     // Catch: Exception -> L32
    L5:
        if (r10.next() == false) goto L24;
        long r13 = r10.longValue(r12);     // Catch: Exception -> L32
        if (r13 == r0) goto L5;
        int r15 = r10.intValue(1);     // Catch: Exception -> L32
        TLRPC.TL_topPeer r122 = new TLRPC.TL_topPeer();     // Catch: Exception -> L32
        long r16 = r0;
        r122.rating = r10.doubleValue(2);     // Catch: Exception -> L32
        if (r13 <= 0) goto L12;
        TLRPC.TL_peerUser r02 = new TLRPC.TL_peerUser();     // Catch: Exception -> L32
        r122.peer = r02;     // Catch: Exception -> L32
        r02.user_id = r13;     // Catch: Exception -> L32
        r8.add(Long.valueOf(r13));     // Catch: Exception -> L32
    L13:
        if (r15 != 0) goto L16;
        r4.add(r122);     // Catch: Exception -> L32
    L23:
        r0 = r16;
        r12 = 0;
        goto L5
    L16:
        if (r15 != 1) goto L18;
        r5.add(r122);     // Catch: Exception -> L32
        goto L23
    L18:
        if (r15 != 2) goto L21;
        r7.add(r122);     // Catch: Exception -> L32
        goto L23
    L21:
        if (r15 != 3) goto L23;
        r6.add(r122);     // Catch: Exception -> L32
        goto L23
    L12:
        TLRPC.TL_peerChat r03 = new TLRPC.TL_peerChat();     // Catch: Exception -> L32
        r122.peer = r03;     // Catch: Exception -> L32
        long r132 = -r13;
        r03.chat_id = r132;     // Catch: Exception -> L32
        r9.add(Long.valueOf(r132));     // Catch: Exception -> L32
        goto L13
    L24:
        r10.dispose();     // Catch: Exception -> L32
        if (r8.isEmpty() == true) goto L28;
        getMessagesStorage().getUsersInternal(r8, r2);     // Catch: Exception -> L32
    L28:
        if (r9.isEmpty() == true) goto L30;
        getMessagesStorage().getChatsInternal(TextUtils.join(",", r9), r3);     // Catch: Exception -> L32
    L30:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda207(this, r2, r3, r4, r5, r6, r7));     // Catch: Exception -> L32
        return;
    L32:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$loadHints$148() {
        getUserConfig().suggestContacts = true;
        UserConfig r0 = getUserConfig();
        r0.lastHintsSyncTime = (int) (System.currentTimeMillis() / 1000);
        getUserConfig().saveConfig(false);
    }

    private /* synthetic */ void lambda$loadHints$149(TLRPC.TL_contacts_topPeers r13) {
        getMessagesStorage().getDatabase().executeFast("DELETE FROM chat_hints WHERE 1").stepThis().dispose();     // Catch: Exception -> L22
        getMessagesStorage().getDatabase().beginTransaction();     // Catch: Exception -> L22
        getMessagesStorage().putUsersAndChats(r13.users, r13.chats, false, false);     // Catch: Exception -> L22
        SQLitePreparedStatement r0 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO chat_hints VALUES(?, ?, ?, ?)");     // Catch: Exception -> L22
        int r1 = 0;
    L4:
        if (r1 >= r13.categories.size()) goto L20;
        TLRPC.TL_topPeerCategoryPeers r2 = r13.categories.get(r1);     // Catch: Exception -> L22
        TLRPC.TopPeerCategory r4 = r2.category;     // Catch: Exception -> L22
        if ((r4 instanceof TLRPC.TL_topPeerCategoryBotsInline) == false) goto L9;
        int r42 = 1;
    L15:
        int r5 = 0;
    L17:
        if (r5 >= r2.peers.size()) goto L19;
        TLRPC.TL_topPeer r9 = r2.peers.get(r5);     // Catch: Exception -> L22
        r0.requery();     // Catch: Exception -> L22
        r0.bindLong(1, MessageObject.getPeerId(r9.peer));     // Catch: Exception -> L22
        r0.bindInteger(2, r42);     // Catch: Exception -> L22
        r0.bindDouble(3, r9.rating);     // Catch: Exception -> L22
        r0.bindInteger(4, 0);     // Catch: Exception -> L22
        r0.step();     // Catch: Exception -> L22
        r5 = r5 + 1;     // Catch: Exception -> L22
        goto L17
    L19:
        r1 = r1 + 1;     // Catch: Exception -> L22
        goto L4
    L9:
        if ((r4 instanceof TLRPC.TL_topPeerCategoryBotsApp) == false) goto L12;
        r42 = 2;
        goto L15
    L12:
        if ((r4 instanceof TLRPC.TL_topPeerCategoryBotsGuestChat) == false) goto L14;
        r42 = 3;
        goto L15
    L14:
        r42 = 0;
        goto L15
    L20:
        r0.dispose();     // Catch: Exception -> L22
        getMessagesStorage().getDatabase().commitTransaction();     // Catch: Exception -> L22
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda214(this));     // Catch: Exception -> L22
        return;
    L22:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$loadHints$150(TLRPC.contacts_TopPeers r10, TLRPC.TL_error r11) {
        if ((r10 instanceof TLRPC.TL_contacts_topPeers) == false) goto L28;
        TLRPC.TL_contacts_topPeers r102 = (TLRPC.TL_contacts_topPeers) r10;
        getMessagesController().putUsers(r102.users, false);
        getMessagesController().putChats(r102.chats, false);
        int r112 = (int) (System.currentTimeMillis() / 1000);
        int r3 = 0;
    L6:
        if (r3 >= r102.categories.size()) goto L25;
        TLRPC.TL_topPeerCategoryPeers r4 = r102.categories.get(r3);
        TLRPC.TopPeerCategory r5 = r4.category;
        if ((r5 instanceof TLRPC.TL_topPeerCategoryBotsInline) == false) goto L11;
        this.inlineBots = r4.peers;
        getUserConfig().botRatingLoadTime = r112;
    L24:
        r3 = r3 + 1;
        goto L6
    L11:
        if ((r5 instanceof TLRPC.TL_topPeerCategoryBotsApp) == false) goto L13;
        this.webapps = r4.peers;
        getUserConfig().webappRatingLoadTime = r112;
        goto L24
    L13:
        boolean r52 = r5 instanceof TLRPC.TL_topPeerCategoryBotsGuestChat;
        ArrayList<TLRPC.TL_topPeer> r42 = r4.peers;
        if (r52 == false) goto L16;
        this.guestBots = r42;
        getUserConfig().botGuestRatingLoadTime = r112;
        goto L24
    L16:
        this.hints = r42;
        long r43 = getUserConfig().getClientUserId();
        int r6 = 0;
    L18:
        if (r6 >= this.hints.size()) goto L23;
        if (this.hints.get(r6).peer.user_id == r43) goto L21;
        r6 = r6 + 1;
        goto L18
    L21:
        this.hints.remove(r6);
    L23:
        getUserConfig().ratingLoadTime = (int) (System.currentTimeMillis() / 1000);
        goto L24
    L25:
        getUserConfig().saveConfig(false);
        buildShortcuts();
        getNotificationCenter().postNotificationName(NotificationCenter.reloadHints, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadInlineHints, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadGuestBotHints, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadWebappsHints, new Object[0]);
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda183(this, r102));
        return;
    L28:
        if ((r10 instanceof TLRPC.TL_contacts_topPeersDisabled) == false) goto L38;
        getUserConfig().suggestContacts = false;
        getUserConfig().lastHintsSyncTime = (int) (System.currentTimeMillis() / 1000);
        getUserConfig().saveConfig(false);
        clearTopPeers();
        return;
    }

    private /* synthetic */ void lambda$loadMedia$128(long r16, int r18, int r19, int r20, int r21, long r22, int r24, boolean r25, int r26, TLObject r27, TLRPC.TL_error r28) {
        if (r28 != null) goto L14;
        TLRPC.messages_Messages r1 = (TLRPC.messages_Messages) r27;
        getMessagesController().removeDeletedMessagesFromArray(r16, r1.messages);
        ArrayList<TLRPC.Message> r0 = r1.messages;
        boolean r2 = false;
        if (r18 == 0) goto L10;
        if (r0.size() > 1) goto L12;
    L7:
        r2 = true;
    L12:
        processLoadedMedia(r1, r16, r19, r20, r18, r21, r22, 0, r24, r25, r2, r26);
        return;
    L10:
        if (r0.size() != 0) goto L12;
    }

    private /* synthetic */ void lambda$loadMusic$143(long r2, ArrayList r4, ArrayList r5) {
        getNotificationCenter().postNotificationName(NotificationCenter.musicDidLoad, new Object[]{Long.valueOf(r2), r4, r5});
    }

    private /* synthetic */ void lambda$loadMusic$144(long r12, long r14, long r16) {
        ArrayList r5 = new ArrayList();
        ArrayList r6 = new ArrayList();
        int r2 = 0;
    L4:
        if (r2 >= 2) goto L30;
        if (r2 != 0) goto L7;
        ArrayList r3 = r5;
    L9:
        if (r2 != 0) goto L17;
        if (DialogObject.isEncryptedDialog(r12) == true) goto L15;
        SQLiteCursor r4 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT data, mid FROM media_v4 WHERE uid = %d AND mid < %d AND type = %d ORDER BY date DESC, mid DESC LIMIT 1000", new Object[]{Long.valueOf(r12), Long.valueOf(r14), 4}), new Object[0]);     // Catch: Exception -> L13
    L21:
        if (r4.next() == false) goto L27;
        NativeByteBuffer r7 = r4.byteBufferValue(0);     // Catch: Exception -> L13
        if (r7 == null) goto L21;
        TLRPC.Message r8 = TLRPC.Message.TLdeserialize(r7, r7.readInt32(false), false);     // Catch: Exception -> L13
        r8.readAttachPath(r7, getUserConfig().clientUserId);     // Catch: Exception -> L13
        r7.reuse();     // Catch: Exception -> L13
        if (MessageObject.isMusicMessage(r8) == false) goto L21;
        r8.id = r4.intValue(1);     // Catch: Exception -> L13
        r8.dialog_id = r12;     // Catch: Exception -> L13
        r3.add(0, new MessageObject(this.currentAccount, r8, false, true));     // Catch: Exception -> L13
        goto L21
    L27:
        r4.dispose();     // Catch: Exception -> L13
        r2 = r2 + 1;
        goto L4
    L15:
        r4 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT data, mid FROM media_v4 WHERE uid = %d AND mid > %d AND type = %d ORDER BY date DESC, mid DESC LIMIT 1000", new Object[]{Long.valueOf(r12), Long.valueOf(r14), 4}), new Object[0]);     // Catch: Exception -> L13
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L30
    L17:
        if (DialogObject.isEncryptedDialog(r12) == true) goto L19;
        r4 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT data, mid FROM media_v4 WHERE uid = %d AND mid > %d AND type = %d ORDER BY date DESC, mid DESC LIMIT 1000", new Object[]{Long.valueOf(r12), Long.valueOf(r16), 4}), new Object[0]);     // Catch: Exception -> L13
        goto L21
    L19:
        r4 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT data, mid FROM media_v4 WHERE uid = %d AND mid < %d AND type = %d ORDER BY date DESC, mid DESC LIMIT 1000", new Object[]{Long.valueOf(r12), Long.valueOf(r16), 4}), new Object[0]);     // Catch: Exception -> L13
        goto L21
    L7:
        r3 = r6;
    L30:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda36(this, r12, r5, r6));
    }

    private /* synthetic */ void lambda$loadPinnedMessageInternal$166(long r10, long r12, TLRPC.TL_channels_getMessages r14, TLObject r15, TLRPC.TL_error r16) {
        if (r16 != null) goto L7;
        TLRPC.messages_Messages r0 = (TLRPC.messages_Messages) r15;
        removeEmptyMessages(r0.messages);
        if (r0.messages.isEmpty() == true) goto L7;
        getMessagesController().getChat(Long.valueOf(r10));
        ImageLoader.saveMessagesThumbs(r0.messages);
        broadcastPinnedMessage(r0.messages, r0.users, r0.chats, false, false);
        getMessagesStorage().putUsersAndChats(r0.users, r0.chats, true, true);
        savePinnedMessages(r12, r0.messages);
        return;
    L7:
        getMessagesStorage().updatePinnedMessages(r12, r14.id, false, -1, 0, false, null);
    }

    private /* synthetic */ void lambda$loadPinnedMessageInternal$167(long r10, TLRPC.TL_messages_getMessages r12, TLObject r13, TLRPC.TL_error r14) {
        if (r14 != null) goto L7;
        TLRPC.messages_Messages r132 = (TLRPC.messages_Messages) r13;
        removeEmptyMessages(r132.messages);
        if (r132.messages.isEmpty() == true) goto L7;
        ImageLoader.saveMessagesThumbs(r132.messages);
        broadcastPinnedMessage(r132.messages, r132.users, r132.chats, false, false);
        getMessagesStorage().putUsersAndChats(r132.users, r132.chats, true, true);
        savePinnedMessages(r10, r132.messages);
        return;
    L7:
        getMessagesStorage().updatePinnedMessages(r10, r12.id, false, -1, 0, false, null);
    }

    private /* synthetic */ void lambda$loadPinnedMessages$163(long r1) {
        this.loadingPinnedMessages.remove(r1);
    }

    private /* synthetic */ void lambda$loadPinnedMessages$164(int r18, TLRPC.TL_messages_search r19, long r20, int r22, TLObject r23, TLRPC.TL_error r24) {
        ArrayList<Integer> r4 = new ArrayList();
        HashMap<Integer, MessageObject> r9 = new HashMap();
        int r3 = 1;
        if ((r23 instanceof TLRPC.messages_Messages) == false) goto L30;
        TLRPC.messages_Messages r1 = (TLRPC.messages_Messages) r23;
        LongSparseArray r13 = new LongSparseArray();
        int r2 = 0;
    L6:
        if (r2 >= r1.users.size()) goto L8;
        TLRPC.User r6 = r1.users.get(r2);
        r13.put(r6.id, r6);
        r2 = r2 + 1;
        goto L6
    L8:
        LongSparseArray r14 = new LongSparseArray();
        int r25 = 0;
    L10:
        if (r25 >= r1.chats.size()) goto L12;
        TLRPC.Chat r62 = r1.chats.get(r25);
        r14.put(r62.id, r62);
        r25 = r25 + 1;
        goto L10
    L12:
        getMessagesStorage().putUsersAndChats(r1.users, r1.chats, true, true);
        getMessagesController().putUsers(r1.users, false);
        getMessagesController().putChats(r1.chats, false);
        int r26 = r1.messages.size();
        int r63 = 0;
    L13:
        if (r63 >= r26) goto L21;
        TLRPC.Message r12 = r1.messages.get(r63);
        if ((r12 instanceof TLRPC.TL_messageService) == true) goto L20;
        if ((r12 instanceof TLRPC.TL_messageEmpty) == true) goto L20;
        r4.add(Integer.valueOf(r12.id));
        r9.put(Integer.valueOf(r12.id), new MessageObject(this.currentAccount, r12, r13, r14, false, false));
    L20:
        r63 = r63 + 1;
        goto L13
    L21:
        if (r18 == 0) goto L26;
        if (r4.isEmpty() == false) goto L26;
        r4.add(Integer.valueOf(r18));
    L26:
        if (r1.messages.size() < r19.limit) goto L29;
        r3 = 0;
    L29:
        int r64 = Math.max(r1.count, r1.messages.size());
        boolean r8 = r3;
    L34:
        getMessagesStorage().updatePinnedMessages(r20, r4, true, r64, r22, r8, r9);
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda74(this, r20));
        return;
    L30:
        if (r18 == 0) goto L32;
        r4.add(Integer.valueOf(r18));
    L33:
        r64 = r3;
        r8 = false;
        goto L34
    L32:
        r3 = 0;
        goto L33
    }

    private /* synthetic */ void lambda$loadPinnedMessages$165(long r8, long r10, ArrayList r12) {
        loadPinnedMessageInternal(r8, r10, r12, false);
    }

    private /* synthetic */ void lambda$loadPremiumPromo$7() {
        SQLiteCursor r1 = null;
        TLRPC.TL_help_premiumPromo r12 = null;
        r12 = null;
        r12 = null;
        r1 = null;
        int r2 = 0;
        SQLiteCursor r3 = getMessagesStorage().getDatabase().queryFinalized("SELECT data, date FROM premium_promo", new Object[0]);     // Catch: Throwable -> L15 Exception -> L17
    L11:
        e = e;
        TLRPC.TL_help_premiumPromo r32 = r12;
        r1 = r3;
    L19:
        FileLog.e(e);     // Catch: Throwable -> L15
        if (r1 == null) goto L22;
        r1.dispose();
    L22:
        r12 = r32;
    L23:
        processLoadedPremiumPromo(r12, r2, true);
        return;
    L9:
        th = th;
        r1 = r3;
    L25:
        if (r1 == null) goto L27;
        r1.dispose();
    L27:
        throw th;
    L5:
        if (r3.next() == false) goto L14;
        NativeByteBuffer r4 = r3.byteBufferValue(0);     // Catch: Throwable -> L9 Exception -> L11
        if (r4 == null) goto L13;
        r12 = TLRPC.TL_help_premiumPromo.TLdeserialize(r4, r4.readInt32(false), true);     // Catch: Throwable -> L9 Exception -> L11
        r4.reuse();     // Catch: Throwable -> L9 Exception -> L11
    L13:
        r2 = r3.intValue(1);     // Catch: Throwable -> L9 Exception -> L11
    L14:
        r3.dispose();
    L17:
        e = e;
        r32 = null;
    L15:
        th = th;
        goto L25
    }

    private /* synthetic */ void lambda$loadPremiumPromo$8(TLObject r5, TLRPC.TL_error r6) {
        int r62 = (int) (System.currentTimeMillis() / 1000);
        if ((r5 instanceof TLRPC.TL_help_premiumPromo) == false) goto L6;
        processLoadedPremiumPromo((TLRPC.TL_help_premiumPromo) r5, r62, false);
        return;
    }

    private /* synthetic */ void lambda$loadReactions$11(List r2, int r3, int r4) {
        processLoadedReactions(r2, r3, r4, true);
    }

    private /* synthetic */ void lambda$loadReactions$12() {
        SQLiteCursor r0 = null;
        ArrayList r02 = null;
        r02 = null;
        r02 = null;
        int r1 = 0;
        SQLiteCursor r2 = getMessagesStorage().getDatabase().queryFinalized("SELECT data, hash, date FROM reactions", new Object[0]);     // Catch: Throwable -> L28 Exception -> L30
    L12:
        th = th;
        r0 = r2;
    L38:
        if (r0 == null) goto L40;
        r0.dispose();
    L40:
        throw th;
    L18:
        e = move-exception;
        ArrayList r6 = r02;
        Exception e = e;
    L15:
        int r3 = 0;
    L32:
        FileLog.e(e);     // Catch: Throwable -> L12
        if (r2 == null) goto L35;
        r2.dispose();
    L35:
        int r03 = r3;
        int r32 = 0;
        r1 = r03;
        r02 = r6;
    L36:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda217(this, r02, r1, r32));
        return;
    L5:
        if (r2.next() == false) goto L26;
        NativeByteBuffer r33 = r2.byteBufferValue(0);     // Catch: Throwable -> L12 Exception -> L18
        if (r33 == null) goto L20;
        int r5 = r33.readInt32(false);     // Catch: Throwable -> L12 Exception -> L18
        r6 = new ArrayList(r5);     // Catch: Throwable -> L12 Exception -> L18
        int r04 = 0;
    L10:
        if (r04 >= r5) goto L16;
        r6.add(TLRPC.TL_availableReaction.TLdeserialize(r33, r33.readInt32(false), true));     // Catch: Throwable -> L12 Exception -> L14
        r04 = r04 + 1;     // Catch: Throwable -> L12 Exception -> L14
    L14:
        e = e;
        goto L15
    L16:
        r33.reuse();     // Catch: Throwable -> L12 Exception -> L14
        r02 = r6;
    L20:
        r3 = r2.intValue(1);     // Catch: Throwable -> L12 Exception -> L18
        r32 = r2.intValue(2);     // Catch: Throwable -> L12 Exception -> L24
        r1 = r3;
    L27:
        r2.dispose();
    L24:
        e = move-exception;
        r6 = r02;
        e = e;
        goto L32
    L26:
        r32 = 0;
        goto L27
    L30:
        e = move-exception;
        r6 = null;
        r3 = 0;
        e = e;
        r2 = null;
    L28:
        th = th;
        goto L38
    }

    private /* synthetic */ void lambda$loadReactions$13(TLObject r5) {
        int r0 = (int) (System.currentTimeMillis() / 1000);
        if ((r5 instanceof TLRPC.TL_messages_availableReactionsNotModified) == false) goto L7;
        processLoadedReactions(null, 0, r0, false);
        return;
    L7:
        if ((r5 instanceof TLRPC.TL_messages_availableReactions) == false) goto L10;
        TLRPC.TL_messages_availableReactions r52 = (TLRPC.TL_messages_availableReactions) r5;
        processLoadedReactions(r52.reactions, r52.hash, r0, false);
        return;
    }

    private /* synthetic */ void lambda$loadReactions$14(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda134(this, r1));
    }

    private /* synthetic */ void lambda$loadRecentAndTopReactions$238(TLRPC.TL_error r3, TLObject r4, SharedPreferences r5, boolean[] r6) {
        if (r3 == null) goto L4;
    L6:
        r6[0] = true;
        if (r6[1] == false) goto L10;
        this.loadingRecentReactions = false;
        return;
    L10:
        return;
    L4:
        if ((r4 instanceof TLRPC.TL_messages_reactions) == false) goto L6;
        TLRPC.TL_messages_reactions r42 = (TLRPC.TL_messages_reactions) r4;
        this.recentReactions.clear();
        this.recentReactions.addAll(r42.reactions);
        saveReactionsToPref(r5, r42.hash, r42.reactions);
        goto L6
    }

    private /* synthetic */ void lambda$loadRecentAndTopReactions$239(SharedPreferences r7, boolean[] r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda55(this, r10, r9, r7, r8));
    }

    private /* synthetic */ void lambda$loadRecentAndTopReactions$240(TLRPC.TL_error r3, TLObject r4, SharedPreferences r5, boolean[] r6) {
        if (r3 == null) goto L4;
    L6:
        r6[1] = true;
        if (r6[0] == false) goto L10;
        this.loadingRecentReactions = false;
        return;
    L10:
        return;
    L4:
        if ((r4 instanceof TLRPC.TL_messages_reactions) == false) goto L6;
        TLRPC.TL_messages_reactions r42 = (TLRPC.TL_messages_reactions) r4;
        this.topReactions.clear();
        this.topReactions.addAll(r42.reactions);
        saveReactionsToPref(r5, r42.hash, r42.reactions);
        goto L6
    }

    private /* synthetic */ void lambda$loadRecentAndTopReactions$241(SharedPreferences r7, boolean[] r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda70(this, r10, r9, r7, r8));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$loadRecents$49(boolean r5, ArrayList r6, int r7) {
        if (r5 == false) goto L5;
        this.recentGifs = r6;
        this.loadingRecentGifs = false;
        this.recentGifsLoaded = true;
    L7:
        if (r7 != 3) goto L9;
        preloadNextGreetingsSticker();
    L9:
        getNotificationCenter().postNotificationName(NotificationCenter.recentDocumentsDidLoad, new Object[]{Boolean.valueOf(r5), Integer.valueOf(r7)});
        loadRecents(r7, r5, false, false);
        return;
    L5:
        this.recentStickers[r7] = r6;
        this.loadingRecentStickers[r7] = false;
        this.recentStickersLoaded[r7] = true;
        goto L7
    }

    private /* synthetic */ void lambda$loadRecents$50(boolean r6, int r7) {
        if (r6 == false) goto L5;
        int r1 = 2;
    L36:
        SQLiteCursor r0 = getMessagesStorage().getDatabase().queryFinalized("SELECT document FROM web_recent_v3 WHERE type = " + r1 + " ORDER BY date DESC", new Object[0]);     // Catch: Throwable -> L29
        ArrayList r2 = new ArrayList();     // Catch: Throwable -> L29
    L21:
        if (r0.next() == false) goto L32;
        if (r0.isNull(0) == true) goto L21;
        NativeByteBuffer r3 = r0.byteBufferValue(0);     // Catch: Throwable -> L29
        if (r3 == null) goto L21;
        TLRPC.Document r4 = TLRPC.Document.TLdeserialize(r3, r3.readInt32(false), false);     // Catch: Throwable -> L29
        if (r4 == null) goto L31;
        r2.add(r4);     // Catch: Throwable -> L29
    L31:
        r3.reuse();     // Catch: Throwable -> L29
        goto L21
    L32:
        r0.dispose();     // Catch: Throwable -> L29
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda65(this, r6, r2, r7));     // Catch: Throwable -> L29
        return;
    L29:
        th = move-exception;
        getMessagesStorage().checkSQLException(th);
        return;
    L5:
        r1 = 3;
        if (r7 == 0) goto L36;
        if (r7 != 1) goto L11;
        r1 = 4;
        goto L36
    L11:
        if (r7 != 3) goto L13;
        r1 = 6;
        goto L36
    L13:
        r1 = 7;
        if (r7 == 5) goto L36;
        if (r7 != 7) goto L18;
        r1 = 8;
        goto L36
    L18:
        r1 = 5;
        goto L36
    }

    private /* synthetic */ void lambda$loadRecents$51(int r7, TLObject r8, TLRPC.TL_error r9) {
        if ((r8 instanceof TLRPC.TL_messages_savedGifs) == false) goto L6;
        ArrayList<TLRPC.Document> r82 = ((TLRPC.TL_messages_savedGifs) r8).gifs;
    L7:
        processLoadedRecentDocuments(r7, r82, true, 0, true);
        return;
    L6:
        r82 = null;
        goto L7
    }

    private /* synthetic */ void lambda$loadRecents$52(int r7, TLObject r8, TLRPC.TL_error r9) {
        if (r7 == 3) goto L17;
        if (r7 == 7) goto L17;
        if (r7 != 2) goto L14;
        if ((r8 instanceof TLRPC.TL_messages_favedStickers) == false) goto L19;
        ArrayList<TLRPC.Document> r82 = ((TLRPC.TL_messages_favedStickers) r8).stickers;
    L20:
        processLoadedRecentDocuments(r7, r82, false, 0, true);
        return;
    L19:
        r82 = null;
        goto L20
    L14:
        if ((r8 instanceof TLRPC.TL_messages_recentStickers) == false) goto L19;
        r82 = ((TLRPC.TL_messages_recentStickers) r8).stickers;
    L17:
        if ((r8 instanceof TLRPC.TL_messages_stickers) == false) goto L19;
        r82 = ((TLRPC.TL_messages_stickers) r8).stickers;
        goto L20
    }

    private /* synthetic */ void lambda$loadRepliesOfDraftReplies$0(ArrayList r8) {
        ArrayList<Long> r3 = new ArrayList();     // Catch: Exception -> L12
        ArrayList<Long> r4 = new ArrayList();     // Catch: Exception -> L12
        LongSparseArray<SparseArray<ArrayList<TLRPC.Message>>> r1 = new LongSparseArray();     // Catch: Exception -> L12
        LongSparseArray<ArrayList<Integer>> r2 = new LongSparseArray();     // Catch: Exception -> L12
        int r5 = 0;
    L4:
        if (r5 >= r8.size()) goto L10;
        MessagesStorage.addReplyMessages((TLRPC.Message) r8.get(r5), r1, r2);     // Catch: Exception -> L7
    L9:
        r5 = r5 + 1;     // Catch: Exception -> L12
    L7:
        e = move-exception;
        getMessagesStorage().checkSQLException(e);     // Catch: Exception -> L12
        goto L9
    L10:
        getMessagesStorage().loadReplyMessages(r1, r2, r3, r4, 0);     // Catch: Exception -> L12
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$loadReplyIcons$246(TLObject r3, SharedPreferences r4) {
        if ((r3 instanceof TLRPC.TL_emojiList) == false) goto L6;
        SerializedData r0 = new SerializedData(r3.getObjectSize());
        r3.serializeToStream(r0);
        SharedPreferences.Editor r42 = r4.edit();
        this.replyIconsDefault = (TLRPC.TL_emojiList) r3;
        r42.putString("replyicons", Utilities.bytesToHex(r0.toByteArray()));
        r42.putLong("replyicons_last_check", System.currentTimeMillis());
        r42.apply();
        return;
    }

    private /* synthetic */ void lambda$loadReplyIcons$247(SharedPreferences r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda205(this, r2, r1));
    }

    private /* synthetic */ void lambda$loadReplyMessagesForMessages$172(Timer.Task r1, long r2, ArrayList r4) {
        Timer.done(r1);
        getNotificationCenter().postNotificationName(NotificationCenter.replyMessagesDidLoad, new Object[]{Long.valueOf(r2), r4, null});
    }

    private /* synthetic */ void lambda$loadReplyMessagesForMessages$173(Timer.Task r9, Timer r10, ArrayList r11, long r12, LongSparseArray r14, Runnable r15) {
        Timer.done(r9);
        Timer.Task r92 = Timer.start(r10, "loadReplyMessagesForMessages: (encrypted) loading those messages from storage");
        ArrayList r5 = new ArrayList();     // Catch: Exception -> L31
        SQLiteCursor r112 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT m.data, m.mid, m.date, r.random_id FROM randoms_v2 as r INNER JOIN messages_v2 as m ON r.mid = m.mid AND r.uid = m.uid WHERE r.random_id IN(%s)", new Object[]{TextUtils.join(",", r11)}), new Object[0]);     // Catch: Exception -> L31
    L5:
        if (r112.next() == false) goto L14;
        NativeByteBuffer r0 = r112.byteBufferValue(0);     // Catch: Exception -> L31
        if (r0 == null) goto L5;
        TLRPC.Message r2 = TLRPC.Message.TLdeserialize(r0, r0.readInt32(false), false);     // Catch: Exception -> L31
        r2.readAttachPath(r0, getUserConfig().clientUserId);     // Catch: Exception -> L31
        r0.reuse();     // Catch: Exception -> L31
        r2.id = r112.intValue(1);     // Catch: Exception -> L31
        r2.date = r112.intValue(2);     // Catch: Exception -> L31
        r2.dialog_id = r12;     // Catch: Exception -> L31
        long r3 = r112.longValue(3);     // Catch: Exception -> L31
        ArrayList r02 = (ArrayList) r14.get(r3);     // Catch: Exception -> L31
        r14.remove(r3);     // Catch: Exception -> L31
        if (r02 == null) goto L5;
        MessageObject r32 = new MessageObject(this.currentAccount, r2, false, false);     // Catch: Exception -> L31
        r5.add(r32);     // Catch: Exception -> L31
        int r22 = 0;
    L12:
        if (r22 >= r02.size()) goto L5;
        MessageObject r4 = (MessageObject) r02.get(r22);     // Catch: Exception -> L31
        r4.replyMessageObject = r32;     // Catch: Exception -> L31
        r4.applyTimestampsHighlightForReplyMsg();     // Catch: Exception -> L31
        r4.messageOwner.reply_to = new TLRPC.TL_messageReplyHeader();     // Catch: Exception -> L31
        TLRPC.MessageReplyHeader r42 = r4.messageOwner.reply_to;     // Catch: Exception -> L31
        r42.flags |= 16;
        r42.reply_to_msg_id = r32.getRealId();     // Catch: Exception -> L31
        r22 = r22 + 1;     // Catch: Exception -> L31
        goto L12
    L14:
        r112.dispose();     // Catch: Exception -> L31
        if (r14.size() == 0) goto L27;
        int r113 = 0;
    L18:
        if (r113 >= r14.size()) goto L27;
        ArrayList r03 = (ArrayList) r14.valueAt(r113);     // Catch: Exception -> L31
        int r23 = 0;
    L21:
        if (r23 >= r03.size()) goto L26;
        TLRPC.MessageReplyHeader r33 = ((MessageObject) r03.get(r23)).messageOwner.reply_to;     // Catch: Exception -> L31
        if (r33 == null) goto L25;
        r33.reply_to_random_id = 0;     // Catch: Exception -> L31
    L25:
        r23 = r23 + 1;     // Catch: Exception -> L31
        goto L21
    L26:
        r113 = r113 + 1;     // Catch: Exception -> L31
    L27:
        Timer.done(r92);     // Catch: Exception -> L31
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda101(this, Timer.start(r10, "loadReplyMessagesForMessages (encrypted) runOnUIThread: posting notification"), r12, r5));     // Catch: Exception -> L31
        if (r15 == null) goto L47;
        r15.run();     // Catch: Exception -> L31
        return;
    L47:
        return;
    L31:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$loadReplyMessagesForMessages$175(TLRPC.TL_error r11, TLRPC.messages_Messages r12, long r13, long r15, LongSparseArray r17, boolean r18, TLObject r19, TLRPC.TL_error r20) {
        if (r11 != null) goto L15;
        TLRPC.messages_Messages r112 = (TLRPC.messages_Messages) r19;
        r12.messages.addAll(r112.messages);
        r12.users.addAll(r112.users);
        r12.chats.addAll(r112.chats);
        int r113 = 0;
    L4:
        int r0 = r12.messages.size();
        ArrayList<TLRPC.Message> r1 = r12.messages;
        if (r113 >= r0) goto L10;
        TLRPC.Message r02 = r1.get(r113);
        if (r02.dialog_id != 0) goto L9;
        r02.dialog_id = r13;
    L9:
        r113 = r113 + 1;
        goto L4
    L10:
        MessageObject.fixMessagePeer(r1, r15);
        ImageLoader.saveMessagesThumbs(r12.messages);
        broadcastReplyMessages(r12.messages, r17, r12.users, r12.chats, r13, false);
        getMessagesStorage().putUsersAndChats(r12.users, r12.chats, true, true);
        saveReplyMessages(r17, r12.messages, r18);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$loadReplyMessagesForMessages$176(Timer.Task r21, TLRPC.TL_messages_getScheduledMessages r22, long r23, long r25, LongSparseArray r27, boolean r28, Timer r29, AtomicInteger r30, Runnable r31, TLObject r32, TLRPC.TL_error r33) {
        Timer.done(r21);
        if (r33 != null) goto L26;
        TLRPC.messages_Messages r3 = (TLRPC.messages_Messages) r32;
        int r1 = 0;
        int r2 = 0;
    L5:
        int r4 = r3.messages.size();
        ArrayList<TLRPC.Message> r5 = r3.messages;
        if (r2 >= r4) goto L12;
        if ((r5.get(r2) instanceof TLRPC.TL_messageEmpty) == false) goto L10;
        r3.messages.remove(r2);
        r2 = r2 - 1;
    L10:
        r2 = r2 + 1;
        goto L5
    L12:
        if (r5.size() < r22.id.size()) goto L14;
    L19:
        int r0 = r3.messages.size();
        ArrayList<TLRPC.Message> r24 = r3.messages;
        if (r1 >= r0) goto L25;
        TLRPC.Message r02 = r24.get(r1);
        if (r02.dialog_id != 0) goto L24;
        r02.dialog_id = r25;
    L24:
        r1 = r1 + 1;
        goto L19
    L25:
        MessageObject.fixMessagePeer(r24, r23);
        ImageLoader.saveMessagesThumbs(r3.messages);
        broadcastReplyMessages(r3.messages, r27, r3.users, r3.chats, r25, false);
        getMessagesStorage().putUsersAndChats(r3.users, r3.chats, true, true);
        saveReplyMessages(r27, r3.messages, r28);
    L28:
        if (r30.decrementAndGet() != 0) goto L38;
        if (r31 == null) goto L39;
        AndroidUtilities.runOnUIThread(r31);
        return;
    L39:
        return;
    L38:
        return;
    L14:
        if (r23 == 0) goto L17;
        TLRPC.TL_channels_getMessages r12 = new TLRPC.TL_channels_getMessages();
        r12.channel = getMessagesController().getInputChannel(r23);
        r12.id = r22.id;
        TLRPC.TL_messages_getMessages r13 = r12;
    L18:
        getConnectionsManager().sendRequest(r13, new MediaDataController$$ExternalSyntheticLambda117(this, r33, r3, r25, r23, r27, r28));
        goto L28
    L17:
        TLRPC.TL_messages_getMessages r14 = new TLRPC.TL_messages_getMessages();
        r14.id = r22.id;
        r13 = r14;
        goto L18
    L26:
        Timer.log(r29, "getScheduledMessages error: " + r33.code + " " + r33.text);
        goto L28
    }

    private /* synthetic */ void lambda$loadReplyMessagesForMessages$177(Timer.Task r10, long r11, long r13, LongSparseArray r15, boolean r16, Timer r17, AtomicInteger r18, Runnable r19, TLObject r20, TLRPC.TL_error r21) {
        Timer.done(r10);
        if (r21 != null) goto L12;
        TLRPC.messages_Messages r102 = (TLRPC.messages_Messages) r20;
        int r0 = 0;
    L5:
        int r1 = r102.messages.size();
        ArrayList<TLRPC.Message> r2 = r102.messages;
        if (r0 >= r1) goto L11;
        TLRPC.Message r12 = r2.get(r0);
        if (r12.dialog_id != 0) goto L10;
        r12.dialog_id = r11;
    L10:
        r0 = r0 + 1;
        goto L5
    L11:
        MessageObject.fixMessagePeer(r2, r13);
        ImageLoader.saveMessagesThumbs(r102.messages);
        broadcastReplyMessages(r102.messages, r15, r102.users, r102.chats, r11, false);
        getMessagesStorage().putUsersAndChats(r102.users, r102.chats, true, true);
        saveReplyMessages(r15, r102.messages, r16);
    L14:
        if (r18.decrementAndGet() != 0) goto L21;
        if (r19 == null) goto L22;
        AndroidUtilities.runOnUIThread(r19);
        return;
    L22:
        return;
    L21:
        return;
    L12:
        Timer.log(r17, "channels.getMessages error: " + r21.code + " " + r21.text);
        goto L14
    }

    private /* synthetic */ void lambda$loadReplyMessagesForMessages$178(Timer.Task r10, long r11, LongSparseArray r13, boolean r14, Timer r15, AtomicInteger r16, Runnable r17, TLObject r18, TLRPC.TL_error r19) {
        Timer.done(r10);
        if (r19 != null) goto L12;
        TLRPC.messages_Messages r102 = (TLRPC.messages_Messages) r18;
        int r0 = 0;
    L5:
        int r1 = r102.messages.size();
        ArrayList<TLRPC.Message> r2 = r102.messages;
        if (r0 >= r1) goto L11;
        TLRPC.Message r12 = r2.get(r0);
        if (r12.dialog_id != 0) goto L10;
        r12.dialog_id = r11;
    L10:
        r0 = r0 + 1;
        goto L5
    L11:
        ImageLoader.saveMessagesThumbs(r2);
        broadcastReplyMessages(r102.messages, r13, r102.users, r102.chats, r11, false);
        getMessagesStorage().putUsersAndChats(r102.users, r102.chats, true, true);
        saveReplyMessages(r13, r102.messages, r14);
    L14:
        if (r16.decrementAndGet() != 0) goto L21;
        if (r17 == null) goto L22;
        AndroidUtilities.runOnUIThread(r17);
        return;
    L22:
        return;
    L21:
        return;
    L12:
        Timer.log(r15, "messages.getMessages error: " + r19.code + " " + r19.text);
        goto L14
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v11, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v14 */
    private /* synthetic */ void lambda$loadReplyMessagesForMessages$179(Timer.Task r28, LongSparseArray r29, AtomicInteger r30, Runnable r31, int r32, Timer r33, LongSparseArray r34, LongSparseArray r35, boolean r36, long r37) {
        LongSparseArray r5 = r34;
        LongSparseArray r0 = r35;
        long r3 = r37;
        Timer.done(r28);
        getMessagesController().getStoriesController().fillMessagesWithStories(r29, new MediaDataController$$ExternalSyntheticLambda239(r30, r31), r32, r33);     // Catch: Exception -> L10
        if (r5.isEmpty() == false) goto L12;
        Timer.log(r33, "loadReplyMessagesForMessages: empty replyMessageOwners");     // Catch: Exception -> L10
        if (r30.decrementAndGet() != 0) goto L121;
        if (r31 == null) goto L122;
        AndroidUtilities.runOnUIThread(r31);     // Catch: Exception -> L10
        return;
    L122:
        return;
    L121:
        return;
    L12:
        Timer.Task r1 = Timer.start(r33, "loadReplyMessagesForMessages: getting reply messages");     // Catch: Exception -> L10
        ArrayList<TLRPC.Message> r15 = new ArrayList();     // Catch: Exception -> L10
        ArrayList<TLRPC.User> r2 = new ArrayList();     // Catch: Exception -> L10
        ArrayList<TLRPC.Chat> r6 = new ArrayList();     // Catch: Exception -> L10
        ArrayList<Long> r10 = new ArrayList();     // Catch: Exception -> L10
        ArrayList r11 = new ArrayList();     // Catch: Exception -> L10
        int r12 = r5.size();     // Catch: Exception -> L10
        int r14 = 0;
    L14:
        Timer.Task r292 = r1;
        if (r14 >= r12) goto L59;
        long r8 = r5.keyAt(r14);     // Catch: Exception -> L10
        SparseArray r16 = (SparseArray) r5.valueAt(r14);     // Catch: Exception -> L10
        ArrayList r82 = (ArrayList) r0.get(-r8);     // Catch: Exception -> L10
        if (r82 != null) goto L19;
        ArrayList<TLRPC.Chat> r282 = r6;
        int r20 = r12;
        int r21 = r14;
    L58:
        r14 = r21 + 1;     // Catch: Exception -> L10
        r6 = r282;
        r1 = r292;
        r5 = r34;
        r12 = r20;
        goto L14
    L19:
        int r9 = 0;
    L21:
        if (r9 >= 2) goto L46;
        if (r9 != 1) goto L26;
        if (r36 == true) goto L26;
        ArrayList<TLRPC.Chat> r283 = r6;
        int r18 = r9;
        int r202 = r12;
        int r212 = r14;
    L45:
        r9 = r18 + 1;     // Catch: Exception -> L10
        r6 = r283;
        r12 = r202;
        r14 = r212;
    L26:
        if (r9 != 1) goto L28;
        r18 = r9;
        r202 = r12;
        r212 = r14;
        ?? r122 = 0;
        SQLiteCursor r52 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT data, mid, date, uid FROM scheduled_messages_v2 WHERE mid IN(%s) AND uid = %d", new Object[]{TextUtils.join(",", r82), Long.valueOf(r3)}), new Object[0]);     // Catch: Exception -> L10
    L30:
        if (r52.next() == false) goto L44;
        NativeByteBuffer r92 = r52.byteBufferValue(r122);     // Catch: Exception -> L10
        if (r92 == 0) goto L42;
        TLRPC.Message r13 = TLRPC.Message.TLdeserialize(r92, r92.readInt32(r122), r122);     // Catch: Exception -> L10
        ArrayList<TLRPC.Chat> r284 = r6;
        r13.readAttachPath(r92, getUserConfig().clientUserId);     // Catch: Exception -> L10
        r92.reuse();     // Catch: Exception -> L10
        r13.id = r52.intValue(1);     // Catch: Exception -> L10
        r13.date = r52.intValue(2);     // Catch: Exception -> L10
        r13.dialog_id = r3;     // Catch: Exception -> L10
        MessagesStorage.addUsersAndChatsFromMessage(r13, r10, r11, null);     // Catch: Exception -> L10
        r15.add(r13);     // Catch: Exception -> L10
        TLRPC.Peer r93 = r13.peer_id;     // Catch: Exception -> L10
        if (r93 == null) goto L36;
        long r62 = r93.channel_id;     // Catch: Exception -> L10
    L37:
        ArrayList r94 = (ArrayList) r0.get(r62);     // Catch: Exception -> L10
        if (r94 == null) goto L43;
        r94.remove(Integer.valueOf(r13.id));     // Catch: Exception -> L10
        if (r94.isEmpty() == false) goto L43;
        r0.remove(r62);     // Catch: Exception -> L10
    L43:
        r6 = r284;
        goto L30
    L36:
        r62 = 0;
        goto L37
    L42:
        r284 = r6;
        goto L43
    L44:
        r283 = r6;
        r52.dispose();     // Catch: Exception -> L10
        goto L45
    L28:
        r18 = r9;
        r202 = r12;
        r212 = r14;
        r122 = 0;
        r52 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT data, mid, date, uid FROM messages_v2 WHERE mid IN(%s) AND uid = %d", new Object[]{TextUtils.join(",", r82), Long.valueOf(r3)}), new Object[0]);     // Catch: Exception -> L10
        goto L30
    L46:
        r282 = r6;
        r20 = r12;
        r21 = r14;
        ArrayList<Integer> r17 = new ArrayList();     // Catch: Exception -> L10
        int r53 = r82.size();     // Catch: Exception -> L10
        int r63 = 0;
    L47:
        if (r63 >= r53) goto L52;
        Object r7 = r82.get(r63);     // Catch: Exception -> L10
        r63 = r63 + 1;     // Catch: Exception -> L10
        int r72 = ((Integer) r7).intValue();     // Catch: Exception -> L10
        if (MessageObject.isEphemeralMessageId(r72) == false) goto L47;
        r17.add(Integer.valueOf(MessageObject.ephemeralMessageIdUnpack(r72)));     // Catch: Exception -> L10
        goto L47
    L52:
        if (r17.isEmpty() == true) goto L58;
        ArrayList<TL_ephemeral.EphemeralMessage> r19 = getMessagesStorage().getEphemeralMessagesInternal(r3, r17);     // Catch: Exception -> L10
        if (r19 == null) goto L58;
        int r54 = r19.size();     // Catch: Exception -> L10
        int r64 = 0;
    L56:
        if (r64 >= r54) goto L58;
        TL_ephemeral.EphemeralMessage r73 = r19.get(r64);     // Catch: Exception -> L10
        r64 = r64 + 1;     // Catch: Exception -> L10
        TLRPC.Message r74 = EphemeralMessagesHelper.convertEphemeralToFakeDefault(r73);     // Catch: Exception -> L10
        MessagesStorage.addUsersAndChatsFromMessage(r74, r10, r11, null);     // Catch: Exception -> L10
        r15.add(r74);     // Catch: Exception -> L10
        goto L56
    L59:
        ArrayList<TLRPC.Chat> r285 = r6;
        if (r10.isEmpty() == true) goto L63;
        getMessagesStorage().getUsersInternal(r10, r2);     // Catch: Exception -> L10
    L63:
        if (r11.isEmpty() == true) goto L65;
        ArrayList<TLRPC.Chat> r65 = r285;
        getMessagesStorage().getChatsInternal(TextUtils.join(",", r11), r65);     // Catch: Exception -> L10
    L66:
        broadcastReplyMessages(r15, r34, r2, r65, r3, true);     // Catch: Exception -> L10
        if (r0.isEmpty() == true) goto L86;
        Timer.done(r292);     // Catch: Exception -> L10
        int r132 = r0.size();     // Catch: Exception -> L10
        int r142 = 0;
    L69:
        if (r142 >= r132) goto L125;
        long r55 = r0.keyAt(r142);     // Catch: Exception -> L10
        if (r36 == false) goto L76;
        Timer.Task r22 = Timer.start(r33, "loadReplyMessagesForMessages: load scheduled");     // Catch: Exception -> L10
        TLRPC.TL_messages_getScheduledMessages r110 = new TLRPC.TL_messages_getScheduledMessages();     // Catch: Exception -> L10
        r110.peer = getMessagesController().getInputPeer(r3);     // Catch: Exception -> L10
        r110.id = (ArrayList) r0.valueAt(r142);     // Catch: Exception -> L10
        int r286 = r132;
        LongSparseArray r133 = r35;
        int r02 = getConnectionsManager().sendRequest(r110, new MediaDataController$$ExternalSyntheticLambda240(this, r22, r110, r55, r3, r34, r36, r33, r30, r31));     // Catch: Exception -> L10
        if (r32 == 0) goto L85;
        getConnectionsManager().bindRequestToGuid(r02, r32);     // Catch: Exception -> L10
    L85:
        r142 = r142 + 1;     // Catch: Exception -> L10
        r3 = r37;
        r0 = r133;
        r132 = r286;
        goto L69
    L76:
        r286 = r132;
        LongSparseArray r134 = r0;
        if (r55 == 0) goto L82;
        Timer.Task r23 = Timer.start(r33, "loadReplyMessagesForMessages: load channel messages");     // Catch: Exception -> L10
        TLRPC.TL_channels_getMessages r152 = new TLRPC.TL_channels_getMessages();     // Catch: Exception -> L10
        r152.channel = getMessagesController().getInputChannel(r55);     // Catch: Exception -> L10
        r152.id = (ArrayList) r134.valueAt(r142);     // Catch: Exception -> L10
        int r03 = getConnectionsManager().sendRequest(r152, new MediaDataController$$ExternalSyntheticLambda241(this, r23, r37, r55, r34, r36, r33, r30, r31));     // Catch: Exception -> L10
        if (r32 == 0) goto L81;
        getConnectionsManager().bindRequestToGuid(r03, r32);     // Catch: Exception -> L10
    L81:
        r133 = r35;
        goto L85
    L82:
        Timer.Task r24 = Timer.start(r33, "loadReplyMessagesForMessages: load messages");     // Catch: Exception -> L10
        TLRPC.TL_messages_getMessages r102 = new TLRPC.TL_messages_getMessages();     // Catch: Exception -> L10
        r133 = r35;
        r102.id = (ArrayList) r133.valueAt(r142);     // Catch: Exception -> L10
        int r04 = getConnectionsManager().sendRequest(r102, new MediaDataController$$ExternalSyntheticLambda242(this, r24, r37, r34, r36, r33, r30, r31));     // Catch: Exception -> L10
        if (r32 == 0) goto L85;
        getConnectionsManager().bindRequestToGuid(r04, r32);     // Catch: Exception -> L10
        goto L85
    L125:
        return;
    L86:
        Timer.done(r292);     // Catch: Exception -> L10
        if (r30.decrementAndGet() != 0) goto L123;
        if (r31 == null) goto L124;
        AndroidUtilities.runOnUIThread(r31);     // Catch: Exception -> L10
        return;
    L124:
        return;
    L123:
        return;
    L65:
        r65 = r285;
    L10:
        e = move-exception;
        if (r31 == null) goto L93;
        AndroidUtilities.runOnUIThread(r31);
    L93:
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$loadRestrictedStatusEmojis$248(TLObject r3, SharedPreferences r4) {
        if ((r3 instanceof TLRPC.TL_emojiList) == false) goto L6;
        SerializedData r0 = new SerializedData(r3.getObjectSize());
        r3.serializeToStream(r0);
        SharedPreferences.Editor r42 = r4.edit();
        this.restrictedStatusEmojis = (TLRPC.TL_emojiList) r3;
        r42.putString("restrictedstatuses", Utilities.bytesToHex(r0.toByteArray()));
        r42.putLong("restrictedstatuses_last_check", System.currentTimeMillis());
        r42.apply();
        return;
    }

    private /* synthetic */ void lambda$loadRestrictedStatusEmojis$249(SharedPreferences r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda85(this, r2, r1));
    }

    private /* synthetic */ void lambda$loadSavedReactions$242(TLRPC.TL_error r3, TLObject r4, SharedPreferences r5) {
        if (r3 == null) goto L4;
    L6:
        this.loadingSavedReactions = false;
        return;
    L4:
        if ((r4 instanceof TLRPC.TL_messages_reactions) == false) goto L6;
        TLRPC.TL_messages_reactions r42 = (TLRPC.TL_messages_reactions) r4;
        this.savedReactions.clear();
        this.savedReactions.addAll(r42.reactions);
        saveReactionsToPref(r5, r42.hash, r42.reactions);
        getNotificationCenter().postNotificationName(NotificationCenter.savedReactionTagsUpdate, new Object[]{0L});
        goto L6
    }

    private /* synthetic */ void lambda$loadSavedReactions$243(SharedPreferences r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda174(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$loadStickers$101(TLObject r11, int r12, Utilities.Callback r13, long r14) {
        if ((r11 instanceof TLRPC.TL_messages_allStickers) == false) goto L6;
        processLoadStickersResponse(r12, (TLRPC.TL_messages_allStickers) r11, new MediaDataController$$ExternalSyntheticLambda164(r13));
        return;
    L6:
        processLoadedStickers(r12, null, false, (int) (System.currentTimeMillis() / 1000), r14, new MediaDataController$$ExternalSyntheticLambda165(r13));
    }

    private /* synthetic */ void lambda$loadStickers$102(int r3, Utilities.Callback r4, long r5, TLObject r7, TLRPC.TL_error r8) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda236(this, r7, r3, r4, r5));
    }

    private /* synthetic */ void lambda$loadStickers$92(int r7, boolean r8, Utilities.Callback r9) {
        loadStickers(r7, false, r8, false, r9);
    }

    private /* synthetic */ void lambda$loadStickers$94(int r10, Utilities.Callback r11) {
        ArrayList<TLRPC.TL_messages_stickerSet> r3 = new ArrayList();
        int r1 = 0;
        long r4 = 0;
        SQLiteCursor r2 = null;
        r2 = getMessagesStorage().getDatabase().queryFinalized("SELECT data, date, hash FROM stickers_v2 WHERE id = " + (r10 + 1), new Object[0]);     // Catch: Throwable -> L10
        if (r2.next() == false) goto L14;
        NativeByteBuffer r0 = r2.byteBufferValue(0);     // Catch: Throwable -> L10
        if (r0 == null) goto L13;
        int r6 = r0.readInt32(false);     // Catch: Throwable -> L10
        int r7 = 0;
    L8:
        if (r7 >= r6) goto L12;
        r3.add(TLRPC.messages_StickerSet.TLdeserialize(r0, r0.readInt32(false), false));     // Catch: Throwable -> L10
        r7 = r7 + 1;     // Catch: Throwable -> L10
        goto L8
    L12:
        r0.reuse();     // Catch: Throwable -> L10
    L13:
        r1 = r2.intValue(1);     // Catch: Throwable -> L10
        r4 = calcStickersHash(r3);     // Catch: Throwable -> L10
    L14:
        r2.dispose();
    L19:
        processLoadedStickers(r10, r3, true, r1, r4, new MediaDataController$$ExternalSyntheticLambda15(r11, r3));
        return;
    L10:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L21
        if (r2 == null) goto L19;
    L21:
        th = move-exception;
        if (r2 == null) goto L31;
        r2.dispose();
        throw th;
    L31:
        throw th;
    }

    private /* synthetic */ void lambda$loadStickers$98(int r22, Utilities.Callback r23, TLObject r24, TLRPC.TL_error r25) {
        if ((r24 instanceof TLRPC.TL_messages_stickerSet) == false) goto L6;
        ArrayList<TLRPC.TL_messages_stickerSet> r7 = new ArrayList();
        r7.add((TLRPC.TL_messages_stickerSet) r24);
        processLoadedStickers(r22, r7, false, (int) (System.currentTimeMillis() / 1000), calcStickersHash(r7), new MediaDataController$$ExternalSyntheticLambda39(r23));
        return;
    L6:
        processLoadedStickers(r22, null, false, (int) (System.currentTimeMillis() / 1000), 0, new MediaDataController$$ExternalSyntheticLambda40(r23));
    }

    private /* synthetic */ void lambda$loadStickersByEmojiOrName$84(String r12, boolean r13) {
        SQLiteCursor r1 = null;
        TLRPC.TL_messages_stickerSet r14 = null;
        r14 = null;
        r14 = null;
        int r2 = 0;
        SQLiteCursor r3 = getMessagesStorage().getDatabase().queryFinalized("SELECT data, date FROM stickers_dice WHERE emoji = ?", new Object[]{r12});     // Catch: Throwable -> L14
    L9:
        th = th;
        TLRPC.TL_messages_stickerSet r32 = r14;
        r1 = r3;
    L27:
        FileLog.e(th);     // Catch: Throwable -> L22
        if (r1 == null) goto L19;
        r1.dispose();
    L19:
        TLRPC.TL_messages_stickerSet r7 = r32;
    L20:
        processLoadedDiceStickers(r12, r13, r7, true, r2);
        return;
    L22:
        th = move-exception;
        if (r1 == null) goto L33;
        r1.dispose();
        throw th;
    L33:
        throw th;
    L5:
        if (r3.next() == false) goto L12;
        NativeByteBuffer r0 = r3.byteBufferValue(0);     // Catch: Throwable -> L9
        if (r0 == null) goto L11;
        r14 = TLRPC.messages_StickerSet.TLdeserialize(r0, r0.readInt32(false), false);     // Catch: Throwable -> L9
        r0.reuse();     // Catch: Throwable -> L9
    L11:
        r2 = r3.intValue(1);     // Catch: Throwable -> L9
    L12:
        r3.dispose();
        r7 = r14;
    L14:
        th = th;
        r32 = null;
        goto L27
    }

    private /* synthetic */ void lambda$loadStickersByEmojiOrName$85(TLRPC.TL_error r11, TLObject r12, String r13, boolean r14) {
        if ((r12 instanceof TLRPC.TL_messages_stickerSet) == false) goto L6;
        processLoadedDiceStickers(r13, r14, (TLRPC.TL_messages_stickerSet) r12, false, (int) (System.currentTimeMillis() / 1000));
        return;
    L6:
        processLoadedDiceStickers(r13, r14, null, false, (int) (System.currentTimeMillis() / 1000));
    }

    private /* synthetic */ void lambda$loadStickersByEmojiOrName$86(String r7, boolean r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda118(this, r10, r9, r7, r8));
    }

    private /* synthetic */ void lambda$markFeaturedStickersByIdAsRead$68(boolean r9, long r10) {
        this.unreadStickerSets[r9 ? 1 : 0].remove(Long.valueOf(r10));
        this.readingStickerSets[r9 ? 1 : 0].remove(Long.valueOf(r10));
        long[] r102 = this.loadFeaturedHash;
        long r0 = calcFeaturedStickersHash(r9, this.featuredStickerSets[r9 ? 1 : 0]);
        r102[r9 ? 1 : 0] = r0;
        NotificationCenter r103 = getNotificationCenter();
        if (r9 == false) goto L5;
        int r11 = NotificationCenter.featuredEmojiDidLoad;
    L6:
        r103.postNotificationName(r11, new Object[0]);
        putFeaturedStickersToCache(r9, this.featuredStickerSets[r9 ? 1 : 0], this.unreadStickerSets[r9 ? 1 : 0], this.loadFeaturedDate[r9 ? 1 : 0], this.loadFeaturedHash[r9 ? 1 : 0], this.loadFeaturedPremium);
        return;
    L5:
        r11 = NotificationCenter.featuredStickersDidLoad;
        goto L6
    }

    private /* synthetic */ void lambda$preloadPremiumPreviewStickers$208(TLRPC.TL_error r2, TLObject r3) {
        if (r2 == null) goto L4;
        return;
    L4:
        this.previewStickersLoading = false;
        this.premiumPreviewStickers.clear();
        this.premiumPreviewStickers.addAll(((TLRPC.TL_messages_stickers) r3).stickers);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.premiumStickersPreviewLoaded, new Object[0]);
    }

    private /* synthetic */ void lambda$preloadPremiumPreviewStickers$209(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda63(this, r3, r2));
    }

    private /* synthetic */ void lambda$processLoadStickersResponse$74(TLObject r8, ArrayList r9, int r10, LongSparseArray r11, TLRPC.StickerSet r12, TLRPC.TL_messages_allStickers r13, int r14) {
        TLRPC.TL_messages_stickerSet r82 = (TLRPC.TL_messages_stickerSet) r8;
        r9.set(r10, r82);
        r11.put(r12.id, r82);
        if (r11.size() != r13.sets.size()) goto L16;
        int r83 = 0;
    L6:
        if (r83 >= r9.size()) goto L11;
        if (r9.get(r83) != null) goto L10;
        r9.remove(r83);
        r83 = r83 - 1;
    L10:
        r83 = r83 + 1;
        goto L6
    L11:
        processLoadedStickers(r14, r9, false, (int) (System.currentTimeMillis() / 1000), r13.hash2);
        return;
    }

    private /* synthetic */ void lambda$processLoadStickersResponse$75(ArrayList r1, int r2, LongSparseArray r3, TLRPC.StickerSet r4, TLRPC.TL_messages_allStickers r5, int r6, TLObject r7, TLRPC.TL_error r8) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda92(this, r7, r1, r2, r3, r4, r5, r6));
    }

    private /* synthetic */ void lambda$processLoadedDiceStickers$87(String r1) {
        this.loadingDiceStickerSets.remove(r1);
    }

    private /* synthetic */ void lambda$processLoadedDiceStickers$88(String r2, boolean r3) {
        loadStickersByEmojiOrName(r2, r3, false);
    }

    private /* synthetic */ void lambda$processLoadedDiceStickers$89(String r4, TLRPC.TL_messages_stickerSet r5) {
        this.diceStickerSetsByEmoji.put(r4, r5);
        this.diceEmojiStickerSetsById.put(r5.set.id, r4);
        getNotificationCenter().postNotificationName(NotificationCenter.diceStickersDidLoad, new Object[]{r4});
    }

    private /* synthetic */ void lambda$processLoadedDiceStickers$90(boolean r7, TLRPC.TL_messages_stickerSet r8, int r9, String r10, boolean r11) {
        if (r7 == false) goto L15;
        long r0 = 1000;
        if (r8 != null) goto L6;
    L7:
        MediaDataController$$ExternalSyntheticLambda157 r2 = new MediaDataController$$ExternalSyntheticLambda157(this, r10, r11);
        if (r8 != null) goto L11;
        if (r7 == true) goto L11;
    L12:
        AndroidUtilities.runOnUIThread(r2, r0);
        if (r8 != null) goto L15;
        return;
    L11:
        r0 = 0;
        goto L12
    L6:
        if (Math.abs((System.currentTimeMillis() / 1000) - r9) >= 86400) goto L7;
    L15:
        if (r8 == null) goto L20;
        if (r7 == true) goto L18;
        putDiceStickersToCache(r10, r8, r9);
    L18:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda158(this, r10, r8));
        return;
    L20:
        if (r7 == true) goto L23;
        putDiceStickersToCache(r10, null, r9);
        return;
    }

    private /* synthetic */ void lambda$processLoadedFeaturedStickers$60(boolean r3) {
        this.loadingFeaturedStickers[r3 ? 1 : 0] = false;
        this.featuredStickersLoaded[r3 ? 1 : 0] = true;
    }

    private /* synthetic */ void lambda$processLoadedFeaturedStickers$61(ArrayList r3, long r4, boolean r6) {
        if (r3 != null) goto L4;
    L6:
        this.loadingFeaturedStickers[r6 ? 1 : 0] = false;
        loadFeaturedStickers(r6, false);
        return;
    L4:
        if (r4 == 0) goto L6;
        this.loadFeaturedHash[r6 ? 1 : 0] = r4;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$processLoadedFeaturedStickers$62(boolean r2, ArrayList r3, LongSparseArray r4, ArrayList r5, long r6, int r8, boolean r9) {
        this.unreadStickerSets[r2 ? 1 : 0] = r3;
        this.featuredStickerSetsById[r2 ? 1 : 0] = r4;
        this.featuredStickerSets[r2 ? 1 : 0] = r5;
        this.loadFeaturedHash[r2 ? 1 : 0] = r6;
        this.loadFeaturedDate[r2 ? 1 : 0] = r8;
        this.loadFeaturedPremium = r9;
        if (r2 == false) goto L5;
        int r32 = 6;
    L6:
        loadStickers(r32, true, false);
        NotificationCenter r1 = getNotificationCenter();
        if (r2 == false) goto L9;
        int r22 = NotificationCenter.featuredEmojiDidLoad;
    L10:
        r1.postNotificationName(r22, new Object[0]);
        return;
    L9:
        r22 = NotificationCenter.featuredStickersDidLoad;
        goto L10
    L5:
        r32 = 3;
        goto L6
    }

    private /* synthetic */ void lambda$processLoadedFeaturedStickers$63(boolean r1, int r2) {
        this.loadFeaturedDate[r1 ? 1 : 0] = r2;
    }

    private /* synthetic */ void lambda$processLoadedFeaturedStickers$64(boolean r12, ArrayList r13, int r14, long r15, boolean r17, ArrayList r18, boolean r19) {
        long r6 = 0;
        if (r12 == false) goto L7;
        if (r13 != null) goto L6;
    L11:
        MediaDataController$$ExternalSyntheticLambda128 r0 = new MediaDataController$$ExternalSyntheticLambda128(this, r13, r15, r17);
        if (r13 != null) goto L15;
        if (r12 == true) goto L15;
        r6 = 1000;
    L15:
        AndroidUtilities.runOnUIThread(r0, r6);
        if (r13 != null) goto L18;
        return;
    L18:
        if (r13 != null) goto L33;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda130(this, r17, r14));
        putFeaturedStickersToCache(r17, null, null, r14, 0, r19);
        return;
    L33:
        ArrayList<TLRPC.StickerSetCovered> r5 = new ArrayList();     // Catch: Throwable -> L28
        LongSparseArray r9 = new LongSparseArray();     // Catch: Throwable -> L28
        int r02 = 0;
    L21:
        if (r02 >= r13.size()) goto L23;
        TLRPC.StickerSetCovered r1 = (TLRPC.StickerSetCovered) r13.get(r02);     // Catch: Throwable -> L28
        r5.add(r1);     // Catch: Throwable -> L28
        r9.put(r1.set.id, r1);     // Catch: Throwable -> L28
        r02 = r02 + 1;     // Catch: Throwable -> L28
        goto L21
    L23:
        if (r12 == true) goto L25;
        ArrayList<TLRPC.StickerSetCovered> r2 = r5;
        putFeaturedStickersToCache(r17, r2, r18, r14, r15, r19);     // Catch: Throwable -> L28
    L26:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda129(this, r17, r18, r9, r2, r15, r14, r19));     // Catch: Throwable -> L28
        return;
    L25:
        r2 = r5;
    L28:
        th = move-exception;
        FileLog.e(th);
        return;
    L6:
        if (Math.abs((System.currentTimeMillis() / 1000) - r14) >= 3600) goto L11;
    L7:
        if (r12 == true) goto L18;
        if (r13 != null) goto L18;
        if (r15 != 0) goto L18;
        goto L11
    }

    private /* synthetic */ void lambda$processLoadedMedia$135(TLRPC.messages_Messages r14, int r15, long r16, ArrayList r18, int r19, int r20, boolean r21, int r22, int r23) {
        int r0 = r14.count;
        MessagesController r1 = getMessagesController();
        ArrayList<TLRPC.User> r2 = r14.users;
        boolean r3 = false;
        if (r15 == 0) goto L5;
        boolean r5 = true;
    L6:
        r1.putUsers(r2, r5);
        MessagesController r12 = getMessagesController();
        ArrayList<TLRPC.Chat> r142 = r14.chats;
        if (r15 == 0) goto L9;
        boolean r24 = true;
    L10:
        r12.putChats(r142, r24);
        NotificationCenter r13 = getNotificationCenter();
        int r143 = NotificationCenter.mediaDidLoad;
        Long r52 = Long.valueOf(r16);
        Integer r6 = Integer.valueOf(r0);
        Integer r8 = Integer.valueOf(r19);
        Integer r9 = Integer.valueOf(r20);
        Boolean r10 = Boolean.valueOf(r21);
        if (r22 == 0) goto L13;
        r3 = true;
    L13:
        r13.postNotificationName(r143, new Object[]{r52, r6, r18, r8, r9, r10, Boolean.valueOf(r3), Integer.valueOf(r23)});
        return;
    L9:
        r24 = false;
        goto L10
    L5:
        r5 = false;
        goto L6
    }

    private /* synthetic */ void lambda$processLoadedMedia$136(TLRPC.messages_Messages r13, int r14, long r15, ArrayList r17, int r18, int r19, boolean r20, int r21, int r22) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda219(this, r13, r14, r15, r17, r18, r19, r20, r21, r22));
    }

    private /* synthetic */ void lambda$processLoadedMedia$137(ArrayList r7, Runnable r8) {
        int r0 = 0;
    L4:
        if (r0 >= r7.size()) goto L6;
        MessageObject r1 = (MessageObject) r7.get(r0);
        TLRPC.Message r2 = getMessagesStorage().getMessageWithCustomParamsOnlyInternal(r1.getId(), r1.getDialogId());
        TLRPC.Message r3 = r1.messageOwner;
        r3.translatedToLanguage = r2.translatedToLanguage;
        r3.translatedText = r2.translatedText;
        r1.updateTranslation();
        r0 = r0 + 1;
        goto L4
    L6:
        r8.run();
    }

    private /* synthetic */ void lambda$processLoadedMedia$138(TLRPC.messages_Messages r13, int r14, long r15, int r17, int r18, boolean r19, int r20, int r21) {
        LongSparseArray r6 = new LongSparseArray();
        int r0 = 0;
        int r3 = 0;
    L4:
        if (r3 >= r13.users.size()) goto L6;
        TLRPC.User r4 = r13.users.get(r3);
        r6.put(r4.id, r4);
        r3 = r3 + 1;
        goto L4
    L6:
        ArrayList<MessageObject> r9 = new ArrayList();
    L8:
        if (r0 >= r13.messages.size()) goto L10;
        MessageObject r32 = new MessageObject(this.currentAccount, r13.messages.get(r0), r6, true, false);
        r32.createStrippedThumb();
        r9.add(r32);
        r0 = r0 + 1;
        goto L8
    L10:
        getFileLoader().checkMediaExistance(r9);
        MediaDataController$$ExternalSyntheticLambda98 r02 = new MediaDataController$$ExternalSyntheticLambda98(this, r13, r14, r15, r9, r17, r18, r19, r20, r21);
        if (getMessagesController().getTranslateController().isFeatureAvailable(r15) == false) goto L14;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda99(this, r9, r02));
        return;
    L14:
        r02.run();
    }

    private /* synthetic */ void lambda$processLoadedMediaCount$139(long r18, boolean r20, int r21, int r22, int r23, long r24, int r26) {
        int r6 = r21;
        boolean r0 = DialogObject.isEncryptedDialog(r18);
        if (r20 == false) goto L9;
        if (r6 == (-1)) goto L10;
        if (r6 != 0) goto L9;
        int r14 = r22;
        if (r14 == 2) goto L11;
    L13:
        boolean r2 = false;
    L14:
        if (r2 == false) goto L16;
    L18:
        getMediaCount(r18, r24, r14, r26, false);
    L19:
        if (r2 == true) goto L28;
        if (r20 == true) goto L22;
        putMediaCountDatabase(r18, r24, r22, r6);
    L22:
        NotificationCenter r02 = getNotificationCenter();
        int r1 = NotificationCenter.mediaCountDidLoad;
        Long r25 = Long.valueOf(r18);
        Long r3 = Long.valueOf(r24);
        if (r20 == false) goto L26;
        if (r6 != (-1)) goto L26;
        r6 = 0;
    L26:
        r02.postNotificationName(r1, new Object[]{r25, r3, Integer.valueOf(r6), Boolean.valueOf(r20), Integer.valueOf(r22)});
        return;
    L28:
        return;
    L16:
        if (r23 != 1) goto L19;
        if (r0 == true) goto L19;
    L11:
        if (r0 == true) goto L13;
        r2 = true;
        goto L14
    L10:
        r14 = r22;
    L9:
        r14 = r22;
        goto L13
    }

    private /* synthetic */ void lambda$processLoadedMenuBots$5() {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.attachMenuBotsDidLoad, new Object[0]);
    }

    private /* synthetic */ void lambda$processLoadedPremiumPromo$9() {
        getNotificationCenter().postNotificationName(NotificationCenter.premiumPromoUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$processLoadedReactions$15() {
        preloadDefaultReactions();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.reactionsDidLoad, new Object[0]);
    }

    private /* synthetic */ void lambda$processLoadedRecentDocuments$53(boolean r18, int r19, ArrayList r20, boolean r21, int r22) {
        SQLiteDatabase r4 = getMessagesStorage().getDatabase();     // Catch: Exception -> L48
        int r5 = 2;
        if (r18 == false) goto L6;
        int r8 = getMessagesController().maxRecentGifsCount;     // Catch: Exception -> L48
    L13:
        r4.beginTransaction();     // Catch: Exception -> L48
        SQLitePreparedStatement r9 = r4.executeFast("REPLACE INTO web_recent_v3 VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");     // Catch: Exception -> L48
        int r10 = r20.size();     // Catch: Exception -> L48
        if (r18 == false) goto L16;
        int r0 = 2;
    L27:
        if (r21 == false) goto L29;
        r4.executeFast("DELETE FROM web_recent_v3 WHERE type = " + r0).stepThis().dispose();     // Catch: Exception -> L48
    L29:
        int r11 = 0;
    L30:
        if (r11 >= r10) goto L38;
        if (r11 == r8) goto L38;
        TLRPC.Document r6 = (TLRPC.Document) r20.get(r11);     // Catch: Exception -> L48
        r9.requery();     // Catch: Exception -> L48
        r9.bindString(1, _UrlKt.FRAGMENT_ENCODE_SET + r6.id);     // Catch: Exception -> L48
        r9.bindInteger(r5, r0);     // Catch: Exception -> L48
        r9.bindString(3, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L48
        r9.bindString(4, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L48
        r9.bindString(5, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L48
        r9.bindInteger(6, 0);     // Catch: Exception -> L48
        r9.bindInteger(7, 0);     // Catch: Exception -> L48
        r9.bindInteger(8, 0);     // Catch: Exception -> L48
        if (r22 == 0) goto L36;
        int r3 = r22;
    L37:
        r9.bindInteger(9, r3);     // Catch: Exception -> L48
        NativeByteBuffer r32 = new NativeByteBuffer(r6.getObjectSize());     // Catch: Exception -> L48
        r6.serializeToStream(r32);     // Catch: Exception -> L48
        r9.bindByteBuffer(10, r32);     // Catch: Exception -> L48
        r9.step();     // Catch: Exception -> L48
        r32.reuse();     // Catch: Exception -> L48
        r11 = r11 + 1;     // Catch: Exception -> L48
        r5 = 2;
        goto L30
    L36:
        r3 = r10 - r11;     // Catch: Exception -> L48
    L38:
        r9.dispose();     // Catch: Exception -> L48
        r4.commitTransaction();     // Catch: Exception -> L48
        if (r21 == false) goto L41;
        return;
    L41:
        if (r20.size() < r8) goto L59;
        r4.beginTransaction();     // Catch: Exception -> L48
    L44:
        if (r8 >= r20.size()) goto L46;
        r4.executeFast("DELETE FROM web_recent_v3 WHERE id = '" + ((TLRPC.Document) r20.get(r8)).id + "' AND type = " + r0).stepThis().dispose();     // Catch: Exception -> L48
        r8 = r8 + 1;     // Catch: Exception -> L48
        goto L44
    L46:
        r4.commitTransaction();     // Catch: Exception -> L48
        return;
    L59:
        return;
    L16:
        if (r19 != 0) goto L18;
        r0 = 3;
        goto L27
    L18:
        if (r19 != 1) goto L20;
        r0 = 4;
        goto L27
    L20:
        if (r19 != 3) goto L22;
        r0 = 6;
        goto L27
    L22:
        if (r19 != 5) goto L24;
        r0 = 7;
        goto L27
    L24:
        if (r19 != 7) goto L26;
        r0 = 8;
        goto L27
    L26:
        r0 = 5;
        goto L27
    L6:
        if (r19 == 3) goto L12;
        if (r19 == 7) goto L12;
        if (r19 != 2) goto L11;
        r8 = getMessagesController().maxFaveStickersCount;     // Catch: Exception -> L48
        goto L13
    L11:
        r8 = getMessagesController().maxRecentStickersCount;     // Catch: Exception -> L48
    L12:
        r8 = 200;
    L48:
        e = move-exception;
        FileLog.e(e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$processLoadedRecentDocuments$54(boolean r6, int r7, ArrayList r8) {
        SharedPreferences.Editor r0 = MessagesController.getEmojiSettings(this.currentAccount).edit();
        if (r6 == false) goto L5;
        this.loadingRecentGifs = false;
        this.recentGifsLoaded = true;
        r0.putLong("lastGifLoadTime", System.currentTimeMillis()).apply();
    L19:
        if (r8 == null) goto L27;
        if (r6 == false) goto L22;
        this.recentGifs = r8;
    L23:
        if (r7 != 3) goto L25;
        preloadNextGreetingsSticker();
    L25:
        getNotificationCenter().postNotificationName(NotificationCenter.recentDocumentsDidLoad, new Object[]{Boolean.valueOf(r6), Integer.valueOf(r7)});
        return;
    L22:
        this.recentStickers[r7] = r8;
        goto L23
    L27:
        return;
    L5:
        this.loadingRecentStickers[r7] = false;
        this.recentStickersLoaded[r7] = true;
        if (r7 != 0) goto L8;
        r0.putLong("lastStickersLoadTime", System.currentTimeMillis()).apply();
        goto L19
    L8:
        if (r7 != 1) goto L10;
        r0.putLong("lastStickersLoadTimeMask", System.currentTimeMillis()).apply();
        goto L19
    L10:
        if (r7 != 3) goto L13;
        r0.putLong("lastStickersLoadTimeGreet", System.currentTimeMillis()).apply();
        goto L19
    L13:
        if (r7 != 5) goto L16;
        r0.putLong("lastStickersLoadTimeEmojiPacks", System.currentTimeMillis()).apply();
        goto L19
    L16:
        if (r7 != 7) goto L18;
        r0.putLong("lastStickersLoadTimePremiumStickers", System.currentTimeMillis()).apply();
        goto L19
    L18:
        r0.putLong("lastStickersLoadTimeFavs", System.currentTimeMillis()).apply();
        goto L19
    }

    private /* synthetic */ void lambda$processLoadedStickers$104(int r3) {
        this.loadingStickers[r3] = false;
        this.stickersLoaded[r3] = true;
        Runnable r0 = this.scheduledLoadStickers[r3];
        if (r0 == null) goto L6;
        r0.run();
        this.scheduledLoadStickers[r3] = null;
        return;
    }

    private /* synthetic */ void lambda$processLoadedStickers$105(ArrayList r3, long r4, int r6) {
        if (r3 != null) goto L4;
    L6:
        loadStickers(r6, false, false);
        return;
    L4:
        if (r4 == 0) goto L6;
        this.loadHash[r6] = r4;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$processLoadedStickers$106(int r11, LongSparseArray r12, HashMap r13, ArrayList r14, long r15, int r17, LongSparseArray r18, HashMap r19, LongSparseArray r20, Runnable r21) {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= this.stickerSets[r11].size()) goto L12;
        TLRPC.StickerSet r3 = this.stickerSets[r11].get(r2).set;
        this.stickerSetsById.remove(r3.id);
        this.stickerSetsByName.remove(r3.short_name);
        if (r11 == 3) goto L10;
        if (r11 == 6) goto L10;
        if (r11 == 4) goto L10;
        this.installedStickerSetsById.remove(r3.id);
    L10:
        r2 = r2 + 1;
    L12:
        if (r1 >= r12.size()) goto L19;
        this.stickerSetsById.put(r12.keyAt(r1), (TLRPC.TL_messages_stickerSet) r12.valueAt(r1));
        if (r11 == 3) goto L18;
        if (r11 == 6) goto L18;
        if (r11 == 4) goto L18;
        this.installedStickerSetsById.put(r12.keyAt(r1), (TLRPC.TL_messages_stickerSet) r12.valueAt(r1));
    L18:
        r1 = r1 + 1;
        goto L12
    L19:
        this.stickerSetsByName.putAll(r13);
        this.stickerSets[r11] = r14;
        this.loadHash[r11] = r15;
        this.loadDate[r11] = r17;
        this.stickersByIds[r11] = r18;
        if (r11 != 0) goto L22;
        this.allStickers = r19;
        this.stickersByEmoji = r20;
    L24:
        getNotificationCenter().postNotificationName(NotificationCenter.stickersDidLoad, new Object[]{Integer.valueOf(r11), Boolean.TRUE});
        if (r21 == null) goto L38;
        r21.run();
        return;
    L38:
        return;
    L22:
        if (r11 != 3) goto L24;
        this.allStickersFeatured = r19;
        goto L24
    }

    private /* synthetic */ void lambda$processLoadedStickers$107(int r1, int r2) {
        this.loadDate[r1] = r2;
    }

    private /* synthetic */ void lambda$processLoadedStickers$108(boolean r19, ArrayList r20, int r21, long r22, int r24, Runnable r25) {
        long r6 = 0;
        if (r19 == false) goto L9;
        if (r20 != null) goto L6;
    L13:
        ArrayList r2 = r20;
        MediaDataController$$ExternalSyntheticLambda180 r0 = new MediaDataController$$ExternalSyntheticLambda180(this, r2, r22, r24);
        MediaDataController r02 = this;
        if (r2 != null) goto L17;
        if (r19 == true) goto L17;
        r6 = 1000;
    L17:
        AndroidUtilities.runOnUIThread(r0, r6);
        if (r2 != null) goto L23;
        if (r25 == null) goto L96;
        r25.run();
        return;
    L96:
        return;
    L23:
        if (r2 != null) goto L85;
        if (r19 == true) goto L82;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda182(r02, r24, r21));
        r02.putStickersToCache(r24, null, r21, 0);
        if (r25 == null) goto L99;
        r25.run();
        return;
    L99:
        return;
    L82:
        if (r25 == null) goto L100;
        r25.run();
        return;
    L100:
        return;
    L85:
        ArrayList<TLRPC.TL_messages_stickerSet> r5 = new ArrayList();     // Catch: Throwable -> L41
        LongSparseArray r62 = new LongSparseArray();     // Catch: Throwable -> L41
        HashMap r7 = new HashMap();     // Catch: Throwable -> L41
        LongSparseArray r11 = new LongSparseArray();     // Catch: Throwable -> L41
        LongSparseArray r9 = new LongSparseArray();     // Catch: Throwable -> L41
        HashMap r10 = new HashMap();     // Catch: Throwable -> L41
        int r3 = 0;
    L26:
        if (r3 >= r2.size()) goto L69;
        TLRPC.TL_messages_stickerSet r4 = (TLRPC.TL_messages_stickerSet) r2.get(r3);     // Catch: Throwable -> L41
        if (r4 == null) goto L68;
        if (r02.removingStickerSetsUndos.indexOfKey(r4.set.id) >= 0) goto L68;
        r5.add(r4);     // Catch: Throwable -> L41
        r62.put(r4.set.id, r4);     // Catch: Throwable -> L41
        r7.put(r4.set.short_name, r4);     // Catch: Throwable -> L41
        int r12 = 0;
    L34:
        if (r12 >= r4.documents.size()) goto L45;
        TLRPC.Document r13 = r4.documents.get(r12);     // Catch: Throwable -> L41
        if (r13 == null) goto L43;
        if ((r13 instanceof TLRPC.TL_documentEmpty) == true) goto L43;
        r9.put(r13.id, r13);     // Catch: Throwable -> L41
    L43:
        r12 = r12 + 1;     // Catch: Throwable -> L41
        goto L34
    L45:
        if (r4.set.archived == true) goto L68;
        int r122 = 0;
    L48:
        if (r122 >= r4.packs.size()) goto L68;
        TLRPC.TL_stickerPack r132 = r4.packs.get(r122);     // Catch: Throwable -> L41
        if (r132 == null) goto L67;
        String r14 = r132.emoticon;     // Catch: Throwable -> L41
        if (r14 == null) goto L67;
        String r1 = r14.replace("️", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L41
        r132.emoticon = r1;     // Catch: Throwable -> L41
        ArrayList r15 = (ArrayList) r10.get(r1);     // Catch: Throwable -> L41
        if (r15 != null) goto L57;
        r15 = new ArrayList();     // Catch: Throwable -> L41
        r10.put(r132.emoticon, r15);     // Catch: Throwable -> L41
    L57:
        int r142 = 0;
    L59:
        if (r142 >= r132.documents.size()) goto L67;
        Long r152 = r132.documents.get(r142);     // Catch: Throwable -> L41
        int r16 = r3;
        if (r11.indexOfKey(r152.longValue()) >= 0) goto L63;
        r11.put(r152.longValue(), r132.emoticon);     // Catch: Throwable -> L41
    L63:
        TLRPC.Document r03 = (TLRPC.Document) r9.get(r152.longValue());     // Catch: Throwable -> L41
        if (r03 == null) goto L66;
        r15.add(r03);     // Catch: Throwable -> L41
    L66:
        r142 = r142 + 1;     // Catch: Throwable -> L41
        r3 = r16;
    L67:
        r122 = r122 + 1;     // Catch: Throwable -> L41
        r3 = r3;
    L68:
        r3 = r3 + 1;     // Catch: Throwable -> L41
        r02 = this;
        r2 = r20;
        goto L26
    L69:
        if (r19 == true) goto L71;
        ArrayList<TLRPC.TL_messages_stickerSet> r23 = r5;
        putStickersToCache(r24, r23, r21, r22);     // Catch: Throwable -> L41
    L72:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda181(this, r24, r62, r7, r23, r22, r21, r9, r10, r11, r25));     // Catch: Throwable -> L41
        return;
    L71:
        r23 = r5;
    L41:
        th = move-exception;
        FileLog.e(th);
        if (r25 == null) goto L97;
        r25.run();
        return;
    L97:
        return;
    L6:
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L13;
        if (Math.abs((System.currentTimeMillis() / 1000) - r21) >= 3600) goto L13;
    L9:
        if (r19 == true) goto L22;
        if (r20 != null) goto L22;
        if (r22 == 0) goto L13;
    L22:
        r02 = this;
        r2 = r20;
        goto L23
    }

    private /* synthetic */ void lambda$putBotInfo$204(TL_bots.BotInfo r4, long r5) {
        SQLitePreparedStatement r3 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO bot_info_v2 VALUES(?, ?, ?)");     // Catch: Exception -> L4
        r3.requery();     // Catch: Exception -> L4
        NativeByteBuffer r0 = new NativeByteBuffer(r4.getObjectSize());     // Catch: Exception -> L4
        r4.serializeToStream(r0);     // Catch: Exception -> L4
        r3.bindLong(1, r4.user_id);     // Catch: Exception -> L4
        r3.bindLong(2, r5);     // Catch: Exception -> L4
        r3.bindByteBuffer(3, r0);     // Catch: Exception -> L4
        r3.step();     // Catch: Exception -> L4
        r0.reuse();     // Catch: Exception -> L4
        r3.dispose();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$putBotKeyboard$203(MessagesStorage.TopicKey r6, TLRPC.Message r7) {
        TLRPC.Message r0 = this.botKeyboards.get(r6);
        this.botKeyboards.put(r6, r7);
        ArrayList<TLRPC.Message> r1 = this.botDialogKeyboards.get(r6.dialogId);
        if (r1 != null) goto L5;
        r1 = new ArrayList();
    L5:
        r1.add(r7);
        this.botDialogKeyboards.put(r6.dialogId, r1);
        if (MessageObject.getChannelId(r7) != 0) goto L10;
        if (r0 == null) goto L9;
        this.botKeyboardsByMids.delete(r0.id);
    L9:
        this.botKeyboardsByMids.put(r7.id, r6);
    L10:
        getNotificationCenter().postNotificationName(NotificationCenter.botKeyboardDidLoad, new Object[]{r7, r6});
    }

    private /* synthetic */ void lambda$putDiceStickersToCache$91(TLRPC.TL_messages_stickerSet r4, String r5, int r6) {
        if (r4 == null) goto L6;
        SQLitePreparedStatement r3 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO stickers_dice VALUES(?, ?, ?)");     // Catch: Exception -> L8
        r3.requery();     // Catch: Exception -> L8
        NativeByteBuffer r1 = new NativeByteBuffer(r4.getObjectSize());     // Catch: Exception -> L8
        r4.serializeToStream(r1);     // Catch: Exception -> L8
        r3.bindString(1, r5);     // Catch: Exception -> L8
        r3.bindByteBuffer(2, r1);     // Catch: Exception -> L8
        r3.bindInteger(3, r6);     // Catch: Exception -> L8
        r3.step();     // Catch: Exception -> L8
        r1.reuse();     // Catch: Exception -> L8
        r3.dispose();     // Catch: Exception -> L8
        return;
    L6:
        SQLitePreparedStatement r32 = getMessagesStorage().getDatabase().executeFast("UPDATE stickers_dice SET date = ?");     // Catch: Exception -> L8
        r32.requery();     // Catch: Exception -> L8
        r32.bindInteger(1, r6);     // Catch: Exception -> L8
        r32.step();     // Catch: Exception -> L8
        r32.dispose();     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$putEmojiKeywords$217(String r2) {
        this.currentFetchingEmoji.remove(r2);
        getNotificationCenter().postNotificationName(NotificationCenter.newEmojiSuggestionsAvailable, new Object[]{r2});
    }

    private /* synthetic */ void lambda$putEmojiKeywords$218(TLRPC.TL_emojiKeywordsDifference r14, String r15) {
    L20:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (r14.keywords.isEmpty() == true) goto L18;
        SQLitePreparedStatement r0 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO emoji_keywords_v2 VALUES(?, ?, ?)");     // Catch: Exception -> L20
        SQLitePreparedStatement r4 = getMessagesStorage().getDatabase().executeFast("DELETE FROM emoji_keywords_v2 WHERE lang = ? AND keyword = ? AND emoji = ?");     // Catch: Exception -> L20
        getMessagesStorage().getDatabase().beginTransaction();     // Catch: Exception -> L20
        int r5 = r14.keywords.size();     // Catch: Exception -> L20
        int r7 = 0;
    L5:
        if (r7 >= r5) goto L17;
        TLRPC.EmojiKeyword r8 = r14.keywords.get(r7);     // Catch: Exception -> L20
        if ((r8 instanceof TLRPC.TL_emojiKeyword) == false) goto L12;
        TLRPC.TL_emojiKeyword r82 = (TLRPC.TL_emojiKeyword) r8;     // Catch: Exception -> L20
        String r9 = r82.keyword.toLowerCase();     // Catch: Exception -> L20
        int r10 = r82.emoticons.size();     // Catch: Exception -> L20
        int r11 = 0;
    L9:
        if (r11 >= r10) goto L16;
        r0.requery();     // Catch: Exception -> L20
        r0.bindString(1, r14.lang_code);     // Catch: Exception -> L20
        r0.bindString(2, r9);     // Catch: Exception -> L20
        r0.bindString(3, r82.emoticons.get(r11));     // Catch: Exception -> L20
        r0.step();     // Catch: Exception -> L20
        r11 = r11 + 1;     // Catch: Exception -> L20
    L16:
        r7 = r7 + 1;     // Catch: Exception -> L20
        goto L5
    L12:
        if ((r8 instanceof TLRPC.TL_emojiKeywordDeleted) == false) goto L16;
        TLRPC.TL_emojiKeywordDeleted r83 = (TLRPC.TL_emojiKeywordDeleted) r8;     // Catch: Exception -> L20
        String r92 = r83.keyword.toLowerCase();     // Catch: Exception -> L20
        int r102 = r83.emoticons.size();     // Catch: Exception -> L20
        int r112 = 0;
    L14:
        if (r112 >= r102) goto L16;
        r4.requery();     // Catch: Exception -> L20
        r4.bindString(1, r14.lang_code);     // Catch: Exception -> L20
        r4.bindString(2, r92);     // Catch: Exception -> L20
        r4.bindString(3, r83.emoticons.get(r112));     // Catch: Exception -> L20
        r4.step();     // Catch: Exception -> L20
        r112 = r112 + 1;     // Catch: Exception -> L20
        goto L14
    L17:
        getMessagesStorage().getDatabase().commitTransaction();     // Catch: Exception -> L20
        r0.dispose();     // Catch: Exception -> L20
        r4.dispose();     // Catch: Exception -> L20
    L18:
        SQLitePreparedStatement r02 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO emoji_keywords_info_v2 VALUES(?, ?, ?, ?)");     // Catch: Exception -> L20
        r02.bindString(1, r15);     // Catch: Exception -> L20
        r02.bindString(2, r14.lang_code);     // Catch: Exception -> L20
        r02.bindInteger(3, r14.version);     // Catch: Exception -> L20
        r02.bindLong(4, System.currentTimeMillis());     // Catch: Exception -> L20
        r02.step();     // Catch: Exception -> L20
        r02.dispose();     // Catch: Exception -> L20
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda50(this, r15));     // Catch: Exception -> L20
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [org.telegram.SQLite.SQLitePreparedStatement] */
    /* JADX WARN: Type inference failed for: r5v5, types: [org.telegram.SQLite.SQLitePreparedStatement] */
    /* JADX WARN: Type inference failed for: r5v6 */
    private /* synthetic */ void lambda$putFeaturedStickersToCache$65(ArrayList r13, ArrayList r14, boolean r15, int r16, long r17, boolean r19) {
        int r4 = 1;
        NativeByteBuffer r5 = null;
        r5 = null;
        r5 = null;
        if (r13 == null) goto L42;
        SQLitePreparedStatement r12 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO stickers_featured VALUES(?, ?, ?, ?, ?, ?, ?)");     // Catch: Throwable -> L38 Exception -> L40
        int r6 = 0;
        int r8 = 0;
        int r9 = 4;
    L7:
        if (r8 >= r13.size()) goto L15;
        r9 = r9 + ((TLRPC.StickerSetCovered) r13.get(r8)).getObjectSize();     // Catch: Throwable -> L9 Exception -> L12
        r8 = r8 + 1;     // Catch: Throwable -> L9 Exception -> L12
        goto L7
    L15:
        NativeByteBuffer r82 = new NativeByteBuffer(r9);     // Catch: Throwable -> L9 Exception -> L12
        NativeByteBuffer r92 = new NativeByteBuffer((r14.size() * 8) + 4);     // Catch: Throwable -> L34 Exception -> L36
    L77:
        r82.writeInt32(r13.size());     // Catch: Throwable -> L21 Exception -> L23
        int r52 = 0;
    L19:
        if (r52 >= r13.size()) goto L25;
        ((TLRPC.StickerSetCovered) r13.get(r52)).serializeToStream(r82);     // Catch: Throwable -> L21 Exception -> L23
        r52 = r52 + 1;     // Catch: Throwable -> L21 Exception -> L23
        goto L19
    L25:
        r92.writeInt32(r14.size());     // Catch: Throwable -> L21 Exception -> L23
    L27:
        if (r6 >= r14.size()) goto L29;
        r92.writeInt64(((Long) r14.get(r6)).longValue());     // Catch: Throwable -> L21 Exception -> L23
        r6 = r6 + 1;     // Catch: Throwable -> L21 Exception -> L23
        goto L27
    L29:
        if (r15 == false) goto L31;
        int r132 = 2;
    L32:
        r12.bindInteger(1, r132);     // Catch: Throwable -> L21 Exception -> L23
        r12.bindByteBuffer(2, r82);     // Catch: Throwable -> L21 Exception -> L23
        r12.bindByteBuffer(3, r92);     // Catch: Throwable -> L21 Exception -> L23
        r12.bindInteger(4, r16);     // Catch: Throwable -> L21 Exception -> L23
        r12.bindLong(5, r17);     // Catch: Throwable -> L21 Exception -> L23
        r12.bindInteger(6, r19 ? 1 : 0);     // Catch: Throwable -> L21 Exception -> L23
        r12.bindInteger(7, r15 ? 1 : 0);     // Catch: Throwable -> L21 Exception -> L23
        r12.step();     // Catch: Throwable -> L21 Exception -> L23
        r5 = r82;
        SQLitePreparedStatement r122 = r12;
    L48:
        r122.dispose();
        if (r5 == null) goto L51;
        r5.reuse();
    L51:
        if (r92 == null) goto L82;
        r92.reuse();
        return;
    L82:
        return;
    L31:
        r132 = 1;
    L23:
        e = move-exception;
        Exception r133 = e;
        ?? r123 = r12;
    L14:
        ?? r53 = r123;
    L71:
        FileLog.e(r133);     // Catch: Throwable -> L62
        if (r53 == 0) goto L57;
        r53.dispose();
    L57:
        if (r82 == null) goto L59;
        r82.reuse();
    L59:
        if (r92 == null) goto L83;
        r92.reuse();
        return;
    L83:
        return;
    L62:
        th = move-exception;
        Throwable r134 = th;
        ?? r54 = r53;
    L64:
        if (r54 == 0) goto L66;
        r54.dispose();
    L66:
        if (r82 == null) goto L68;
        r82.reuse();
    L68:
        if (r92 == null) goto L84;
        r92.reuse();
        throw r134;
    L84:
        throw r134;
    L21:
        th = move-exception;
        r134 = th;
        ?? r124 = r12;
    L11:
        r54 = r124;
    L36:
        e = move-exception;
        r133 = e;
        r92 = null;
        r123 = r12;
    L34:
        th = move-exception;
        r134 = th;
        r92 = null;
        r124 = r12;
        goto L11
    L42:
        SQLitePreparedStatement r125 = getMessagesStorage().getDatabase().executeFast("UPDATE stickers_featured SET date = ? WHERE id = ? AND emoji = ?");     // Catch: Throwable -> L38 Exception -> L40
        r125.bindInteger(1, r16);     // Catch: Throwable -> L9 Exception -> L12
        if (r15 == false) goto L46;
        r4 = 2;
    L46:
        r125.bindInteger(2, r4);     // Catch: Throwable -> L9 Exception -> L12
        r125.bindInteger(3, r15 ? 1 : 0);     // Catch: Throwable -> L9 Exception -> L12
        r125.step();     // Catch: Throwable -> L9 Exception -> L12
        r92 = null;
        r122 = r125;
    L40:
        e = move-exception;
        r133 = e;
        r82 = r5;
        r92 = r82;
        r53 = r5;
    L38:
        th = move-exception;
        r134 = th;
        r82 = r5;
        r92 = r82;
        r54 = r5;
    L12:
        e = move-exception;
        r133 = e;
        r82 = null;
        r92 = null;
        this = this;
    L9:
        th = move-exception;
        r134 = th;
        r82 = null;
        r92 = null;
        this = this;
        goto L11
    }

    private /* synthetic */ void lambda$putMediaCountDatabase$140(long r3, long r5, int r7, int r8) {
        if (r3 == 0) goto L5;
        SQLitePreparedStatement r2 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO media_counts_topics VALUES(?, ?, ?, ?, ?)");     // Catch: Exception -> L12
    L6:
        r2.requery();     // Catch: Exception -> L12
        r2.bindLong(1, r5);     // Catch: Exception -> L12
        if (r3 == 0) goto L9;
        r2.bindLong(2, r3);     // Catch: Exception -> L12
        int r32 = 3;
    L10:
        r2.bindInteger(r32, r7);     // Catch: Exception -> L12
        r2.bindInteger(r32 + 1, r8);     // Catch: Exception -> L12
        r2.bindInteger(r32 + 2, 0);     // Catch: Exception -> L12
        r2.step();     // Catch: Exception -> L12
        r2.dispose();     // Catch: Exception -> L12
        return;
    L9:
        r32 = 2;
        goto L10
    L5:
        r2 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO media_counts_v2 VALUES(?, ?, ?, ?)");     // Catch: Exception -> L12
    L12:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$putMediaDatabase$142(int r15, ArrayList r16, boolean r17, long r18, int r20, int r21, long r22) {
        if (r15 == 0) goto L44;
    L8:
        long r6 = r22;
    L12:
        getMessagesStorage().getDatabase().beginTransaction();     // Catch: Exception -> L41
        if (r6 == 0) goto L15;
        SQLitePreparedStatement r2 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO media_topics VALUES(?, ?, ?, ?, ?, ?)");     // Catch: Exception -> L41
    L16:
        int r3 = r16.size();     // Catch: Exception -> L41
        int r5 = 0;
    L17:
        int r8 = 1;
        if (r5 >= r3) goto L26;
        Object r9 = r16.get(r5);     // Catch: Exception -> L41
        r5 = r5 + 1;     // Catch: Exception -> L41
        TLRPC.Message r92 = (TLRPC.Message) r9;     // Catch: Exception -> L41
        if (canAddMessageToMedia(r92) == false) goto L17;
        r2.requery();     // Catch: Exception -> L41
        MessageObject.normalizeFlags(r92);     // Catch: Exception -> L41
        NativeByteBuffer r10 = new NativeByteBuffer(r92.getObjectSize());     // Catch: Exception -> L41
        r92.serializeToStream(r10);     // Catch: Exception -> L41
        r2.bindInteger(1, r92.id);     // Catch: Exception -> L41
        r2.bindLong(2, r18);     // Catch: Exception -> L41
        int r82 = 3;
        if (r6 == 0) goto L24;
        r2.bindLong(3, r6);     // Catch: Exception -> L41
        r82 = 4;
    L24:
        r2.bindInteger(r82, r92.date);     // Catch: Exception -> L41
        r2.bindInteger(r82 + 1, r21);     // Catch: Exception -> L41
        r2.bindByteBuffer(r82 + 2, r10);     // Catch: Exception -> L41
        r2.step();     // Catch: Exception -> L41
        r10.reuse();     // Catch: Exception -> L41
        goto L17
    L26:
        r2.dispose();     // Catch: Exception -> L41
        if (r17 == false) goto L30;
        if (r20 != 0) goto L30;
        if (r15 != 0) goto L30;
    L39:
        getMessagesStorage().getDatabase().commitTransaction();     // Catch: Exception -> L41
        return;
    L41:
        e = move-exception;
        FileLog.e(e);
        return;
    L30:
        if (r17 == false) goto L33;
        if (r15 != 0) goto L33;
    L32:
        int r32 = r8;
        if (r15 == 0) goto L36;
        getMessagesStorage().closeHolesInMedia(r18, r32, ((TLRPC.Message) r16.get(0)).id, r21, r6);     // Catch: Exception -> L41
        goto L39
    L36:
        if (r20 == 0) goto L38;
        getMessagesStorage().closeHolesInMedia(r18, r32, r20, r21, r22);     // Catch: Exception -> L41
        goto L39
    L38:
        getMessagesStorage().closeHolesInMedia(r18, r32, Integer.MAX_VALUE, r21, r22);     // Catch: Exception -> L41
    L33:
        r8 = ((TLRPC.Message) r16.get(r16.size() - 1)).id;     // Catch: Exception -> L41
        goto L32
    L15:
        r2 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO media_v4 VALUES(?, ?, ?, ?, ?)");     // Catch: Exception -> L41
        goto L16
    L44:
        if (r16.isEmpty() == true) goto L9;
        if (r17 == false) goto L8;
    L9:
        r6 = r22;
        getMessagesStorage().doneHolesInMedia(r18, r20, r21, r6);     // Catch: Exception -> L41
        if (r16.isEmpty() == false) goto L12;
    }

    private /* synthetic */ void lambda$putMenuBotsToCache$6(TLRPC.TL_attachMenuBots r4, long r5, int r7) {
        if (r4 == null) goto L6;
        getMessagesStorage().getDatabase().executeFast("DELETE FROM attach_menu_bots").stepThis().dispose();     // Catch: Exception -> L8
        SQLitePreparedStatement r3 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO attach_menu_bots VALUES(?, ?, ?)");     // Catch: Exception -> L8
        r3.requery();     // Catch: Exception -> L8
        NativeByteBuffer r1 = new NativeByteBuffer(r4.getObjectSize());     // Catch: Exception -> L8
        r4.serializeToStream(r1);     // Catch: Exception -> L8
        r3.bindByteBuffer(1, r1);     // Catch: Exception -> L8
        r3.bindLong(2, r5);     // Catch: Exception -> L8
        r3.bindInteger(3, r7);     // Catch: Exception -> L8
        r3.step();     // Catch: Exception -> L8
        r1.reuse();     // Catch: Exception -> L8
        r3.dispose();     // Catch: Exception -> L8
        return;
    L6:
        SQLitePreparedStatement r32 = getMessagesStorage().getDatabase().executeFast("UPDATE attach_menu_bots SET date = ?");     // Catch: Exception -> L8
        r32.requery();     // Catch: Exception -> L8
        r32.bindLong(1, r7);     // Catch: Exception -> L8
        r32.step();     // Catch: Exception -> L8
        r32.dispose();     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$putPremiumPromoToCache$10(TLRPC.TL_help_premiumPromo r4, int r5) {
        if (r4 == null) goto L6;
        getMessagesStorage().getDatabase().executeFast("DELETE FROM premium_promo").stepThis().dispose();     // Catch: Exception -> L8
        SQLitePreparedStatement r3 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO premium_promo VALUES(?, ?)");     // Catch: Exception -> L8
        r3.requery();     // Catch: Exception -> L8
        NativeByteBuffer r1 = new NativeByteBuffer(r4.getObjectSize());     // Catch: Exception -> L8
        r4.serializeToStream(r1);     // Catch: Exception -> L8
        r3.bindByteBuffer(1, r1);     // Catch: Exception -> L8
        r3.bindInteger(2, r5);     // Catch: Exception -> L8
        r3.step();     // Catch: Exception -> L8
        r1.reuse();     // Catch: Exception -> L8
        r3.dispose();     // Catch: Exception -> L8
        return;
    L6:
        SQLitePreparedStatement r32 = getMessagesStorage().getDatabase().executeFast("UPDATE premium_promo SET date = ?");     // Catch: Exception -> L8
        r32.requery();     // Catch: Exception -> L8
        r32.bindInteger(1, r5);     // Catch: Exception -> L8
        r32.step();     // Catch: Exception -> L8
        r32.dispose();     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$putReactionsToCache$16(ArrayList r6, int r7, int r8) {
        if (r6 == null) goto L14;
        getMessagesStorage().getDatabase().executeFast("DELETE FROM reactions").stepThis().dispose();     // Catch: Exception -> L16
        SQLitePreparedStatement r5 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO reactions VALUES(?, ?, ?)");     // Catch: Exception -> L16
        r5.requery();     // Catch: Exception -> L16
        int r1 = 0;
        int r2 = 4;
        int r3 = 0;
    L6:
        if (r3 >= r6.size()) goto L8;
        r2 = r2 + ((TLRPC.TL_availableReaction) r6.get(r3)).getObjectSize();     // Catch: Exception -> L16
        r3 = r3 + 1;     // Catch: Exception -> L16
        goto L6
    L8:
        NativeByteBuffer r32 = new NativeByteBuffer(r2);     // Catch: Exception -> L16
        r32.writeInt32(r6.size());     // Catch: Exception -> L16
    L10:
        if (r1 >= r6.size()) goto L12;
        ((TLRPC.TL_availableReaction) r6.get(r1)).serializeToStream(r32);     // Catch: Exception -> L16
        r1 = r1 + 1;     // Catch: Exception -> L16
        goto L10
    L12:
        r5.bindByteBuffer(1, r32);     // Catch: Exception -> L16
        r5.bindInteger(2, r7);     // Catch: Exception -> L16
        r5.bindInteger(3, r8);     // Catch: Exception -> L16
        r5.step();     // Catch: Exception -> L16
        r32.reuse();     // Catch: Exception -> L16
        r5.dispose();     // Catch: Exception -> L16
        return;
    L14:
        SQLitePreparedStatement r52 = getMessagesStorage().getDatabase().executeFast("UPDATE reactions SET date = ?");     // Catch: Exception -> L16
        r52.requery();     // Catch: Exception -> L16
        r52.bindLong(1, r8);     // Catch: Exception -> L16
        r52.step();     // Catch: Exception -> L16
        r52.dispose();     // Catch: Exception -> L16
        return;
    L16:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$putSetToCache$48(TLRPC.TL_messages_stickerSet r6) {
        SQLitePreparedStatement r5 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO web_recent_v3 VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");     // Catch: Exception -> L5
        r5.requery();     // Catch: Exception -> L5
        r5.bindString(1, "s_" + r6.set.id);     // Catch: Exception -> L5
        r5.bindInteger(2, 6);     // Catch: Exception -> L5
        r5.bindString(3, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L5
        r5.bindString(4, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L5
        r5.bindString(5, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L5
        r5.bindInteger(6, 0);     // Catch: Exception -> L5
        r5.bindInteger(7, 0);     // Catch: Exception -> L5
        r5.bindInteger(8, 0);     // Catch: Exception -> L5
        r5.bindInteger(9, 0);     // Catch: Exception -> L5
        NativeByteBuffer r0 = new NativeByteBuffer(r6.getObjectSize());     // Catch: Exception -> L5
        r6.serializeToStream(r0);     // Catch: Exception -> L5
        r5.bindByteBuffer(10, r0);     // Catch: Exception -> L5
        r5.step();     // Catch: Exception -> L5
        r0.reuse();     // Catch: Exception -> L5
        r5.dispose();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$putStickersToCache$103(ArrayList r7, int r8, int r9, long r10) {
        if (r7 == null) goto L14;
        SQLitePreparedStatement r6 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO stickers_v2 VALUES(?, ?, ?, ?)");     // Catch: Exception -> L16
        r6.requery();     // Catch: Exception -> L16
        int r1 = 0;
        int r3 = 0;
        int r4 = 4;
    L6:
        if (r3 >= r7.size()) goto L8;
        r4 = r4 + ((TLRPC.TL_messages_stickerSet) r7.get(r3)).getObjectSize();     // Catch: Exception -> L16
        r3 = r3 + 1;     // Catch: Exception -> L16
        goto L6
    L8:
        NativeByteBuffer r32 = new NativeByteBuffer(r4);     // Catch: Exception -> L16
        r32.writeInt32(r7.size());     // Catch: Exception -> L16
    L10:
        if (r1 >= r7.size()) goto L12;
        ((TLRPC.TL_messages_stickerSet) r7.get(r1)).serializeToStream(r32);     // Catch: Exception -> L16
        r1 = r1 + 1;     // Catch: Exception -> L16
        goto L10
    L12:
        r6.bindInteger(1, r8 + 1);     // Catch: Exception -> L16
        r6.bindByteBuffer(2, r32);     // Catch: Exception -> L16
        r6.bindInteger(3, r9);     // Catch: Exception -> L16
        r6.bindLong(4, r10);     // Catch: Exception -> L16
        r6.step();     // Catch: Exception -> L16
        r32.reuse();     // Catch: Exception -> L16
        r6.dispose();     // Catch: Exception -> L16
        return;
    L14:
        SQLitePreparedStatement r62 = getMessagesStorage().getDatabase().executeFast("UPDATE stickers_v2 SET date = ?");     // Catch: Exception -> L16
        r62.requery();     // Catch: Exception -> L16
        r62.bindLong(1, r9);     // Catch: Exception -> L16
        r62.step();     // Catch: Exception -> L16
        r62.dispose();     // Catch: Exception -> L16
        return;
    L16:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$removeMultipleStickerSets$111(boolean[] r7, ArrayList r8, int r9, int[] r10) {
        if (r7[0] == false) goto L5;
        return;
    L5:
        r7[0] = true;
        int r72 = 0;
    L7:
        if (r72 >= r8.size()) goto L9;
        markSetUninstalling(((TLRPC.TL_messages_stickerSet) r8.get(r72)).set.id, false);
        ((TLRPC.TL_messages_stickerSet) r8.get(r72)).set.archived = false;
        this.stickerSets[r9].add(r10[r72], (TLRPC.TL_messages_stickerSet) r8.get(r72));
        this.stickerSetsById.put(((TLRPC.TL_messages_stickerSet) r8.get(r72)).set.id, (TLRPC.TL_messages_stickerSet) r8.get(r72));
        this.installedStickerSetsById.put(((TLRPC.TL_messages_stickerSet) r8.get(r72)).set.id, (TLRPC.TL_messages_stickerSet) r8.get(r72));
        this.stickerSetsByName.put(((TLRPC.TL_messages_stickerSet) r8.get(r72)).set.short_name, (TLRPC.TL_messages_stickerSet) r8.get(r72));
        this.removingStickerSetsUndos.remove(((TLRPC.TL_messages_stickerSet) r8.get(r72)).set.id);
        r72 = r72 + 1;
        goto L7
    L9:
        ArrayList<TLRPC.TL_messages_stickerSet> r2 = this.stickerSets[r9];
        int r3 = this.loadDate[r9];
        long[] r73 = this.loadHash;
        long r4 = calcStickersHash(r2);
        r73[r9] = r4;
        putStickersToCache(r9, r2, r3, r4);
        getNotificationCenter().postNotificationName(NotificationCenter.stickersDidLoad, new Object[]{Integer.valueOf(r9), Boolean.TRUE});
    }

    private /* synthetic */ void lambda$removeMultipleStickerSets$112(boolean[] r13, ArrayList r14, Context r15, BaseFragment r16, int r17) {
        int r0 = 0;
        if (r13[0] == true) goto L9;
        r13[0] = true;
    L7:
        if (r0 >= r14.size()) goto L11;
        toggleStickerSetInternal(r15, 0, r16, null, true, (TLObject) r14.get(r0), ((TLRPC.TL_messages_stickerSet) r14.get(r0)).set, null, r17, false);
        r0 = r0 + 1;
        goto L7
    L11:
        return;
    }

    private /* synthetic */ void lambda$removeRecentGif$24(TLRPC.TL_messages_saveGif r1, TLObject r2, TLRPC.TL_error r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (FileRefController.isFileRefError(r3.text) == false) goto L8;
        getFileRefController().requestReference("gif", new Object[]{r1});
        return;
    }

    private /* synthetic */ void lambda$removeRecentGif$25(TLRPC.Document r5) {
        getMessagesStorage().getDatabase().executeFast("DELETE FROM web_recent_v3 WHERE id = '" + r5.id + "' AND type = 2").stepThis().dispose();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$replaceStickerSet$28(TLRPC.TL_messages_stickerSet r5) {
        LongSparseArray<TLRPC.Document> r4 = getStickerByIds(4);
        int r0 = 0;
    L4:
        if (r0 >= r5.documents.size()) goto L6;
        TLRPC.Document r1 = r5.documents.get(r0);
        r4.put(r1.id, r1);
        r0 = r0 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$saveDraft$192(long r7, long r9, TLObject r11, TLRPC.TL_error r12) {
        if (r12 != null) goto L7;
        TLRPC.messages_Messages r112 = (TLRPC.messages_Messages) r11;
        if (r112.messages.isEmpty() == true) goto L8;
        saveDraftReplyMessage(r7, r9, r112.messages.get(0));
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$saveDraft$193(long r7, long r9, TLObject r11, TLRPC.TL_error r12) {
        if (r12 != null) goto L7;
        TLRPC.messages_Messages r112 = (TLRPC.messages_Messages) r11;
        if (r112.messages.isEmpty() == true) goto L8;
        saveDraftReplyMessage(r7, r9, r112.messages.get(0));
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$saveDraft$194(int r15, long r16, long r18, long r20, long r22) {
        SQLiteCursor r3 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT data, replydata FROM messages_v2 WHERE mid = %d and uid = %d", new Object[]{Integer.valueOf(r15), Long.valueOf(r16)}), new Object[0]);     // Catch: Exception -> L39
        if (r3.next() == false) goto L30;
        NativeByteBuffer r0 = r3.byteBufferValue(0);     // Catch: Exception -> L39
        if (r0 == null) goto L8;
        TLRPC.Message r6 = TLRPC.Message.TLdeserialize(r0, r0.readInt32(false), false);     // Catch: Exception -> L39
        r6.readAttachPath(r0, getUserConfig().clientUserId);     // Catch: Exception -> L39
        r0.reuse();     // Catch: Exception -> L39
    L9:
        if (r6 == null) goto L31;
        ArrayList<Long> r10 = new ArrayList();     // Catch: Exception -> L39
        ArrayList<Long> r11 = new ArrayList();     // Catch: Exception -> L39
        LongSparseArray<SparseArray<ArrayList<TLRPC.Message>>> r8 = new LongSparseArray();     // Catch: Exception -> L39
        LongSparseArray<ArrayList<Integer>> r9 = new LongSparseArray();     // Catch: Exception -> L39
        TLRPC.MessageReplyHeader r02 = r6.reply_to;     // Catch: Exception -> L22
        if (r02 != null) goto L14;
    L29:
        getMessagesStorage().loadReplyMessages(r8, r9, r10, r11, 0);     // Catch: Exception -> L39
        goto L31
    L14:
        if (r02.reply_to_msg_id == 0) goto L29;
        if (r3.isNull(1) == true) goto L25;
        NativeByteBuffer r03 = r3.byteBufferValue(1);     // Catch: Exception -> L22
        if (r03 == null) goto L25;
        TLRPC.Message r4 = TLRPC.Message.TLdeserialize(r03, r03.readInt32(false), false);     // Catch: Exception -> L22
        r6.replyMessage = r4;     // Catch: Exception -> L22
        r4.readAttachPath(r03, getUserConfig().clientUserId);     // Catch: Exception -> L22
        r03.reuse();     // Catch: Exception -> L22
        TLRPC.Message r04 = r6.replyMessage;     // Catch: Exception -> L22
        if (r04 == null) goto L25;
        MessagesStorage.addUsersAndChatsFromMessage(r04, r10, r11, null);     // Catch: Exception -> L22
    L25:
        if (r6.replyMessage != null) goto L29;
        MessagesStorage.addReplyMessages(r6, r8, r9);     // Catch: Exception -> L22
    L22:
        e = move-exception;
        getMessagesStorage().checkSQLException(e);     // Catch: Exception -> L39
    L31:
        r3.dispose();     // Catch: Exception -> L39
        if (r6 == null) goto L34;
        saveDraftReplyMessage(r20, r22, r6);     // Catch: Exception -> L39
        return;
    L34:
        if (r18 == 0) goto L36;
        TLRPC.TL_channels_getMessages r05 = new TLRPC.TL_channels_getMessages();     // Catch: Exception -> L39
        r05.channel = getMessagesController().getInputChannel(r18);     // Catch: Exception -> L39
        r05.id.add(Integer.valueOf(r15));     // Catch: Exception -> L39
        getConnectionsManager().sendRequest(r05, new MediaDataController$$ExternalSyntheticLambda171(this, r20, r22));     // Catch: Exception -> L39
        return;
    L36:
        TLRPC.TL_messages_getMessages r62 = new TLRPC.TL_messages_getMessages();     // Catch: Exception -> L39
        r62.id.add(Integer.valueOf(r15));     // Catch: Exception -> L39
        getConnectionsManager().sendRequest(r62, new MediaDataController$$ExternalSyntheticLambda172(this, r20, r22));     // Catch: Exception -> L39
        return;
    L8:
        r6 = null;
        goto L9
    L30:
        r6 = null;
    L39:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$saveDraftReplyMessage$195(long r4, long r6, TLRPC.Message r8) {
        LongSparseArray<TLRPC.DraftMessage> r0 = this.drafts.get(r4);
        if (r0 == null) goto L5;
        TLRPC.DraftMessage r02 = r0.get(r6);
    L6:
        if (r02 == null) goto L20;
        TLRPC.InputReplyTo r03 = r02.reply_to;
        if (r03 != null) goto L10;
        return;
    L10:
        if (r03.reply_to_msg_id != r8.id) goto L22;
        LongSparseArray<TLRPC.Message> r04 = this.draftMessages.get(r4);
        if (r04 != null) goto L14;
        r04 = new LongSparseArray();
        this.draftMessages.put(r4, r04);
    L14:
        r04.put(r6, r8);
        SerializedData r05 = new SerializedData(r8.getObjectSize());
        r8.serializeToStream(r05);
        SharedPreferences.Editor r82 = this.draftPreferences.edit();
        if (r6 != 0) goto L17;
        String r62 = "r_" + r4;
    L18:
        r82.putString(r62, Utilities.bytesToHex(r05.toByteArray())).apply();
        getNotificationCenter().postNotificationName(NotificationCenter.newDraftReceived, new Object[]{Long.valueOf(r4)});
        r05.cleanup();
        return;
    L17:
        r62 = "rt_" + r4 + "_" + r6;
        goto L18
    L22:
        return;
    L20:
        return;
    L5:
        r02 = null;
        goto L6
    }

    private /* synthetic */ void lambda$savePeer$160(long r2, int r4, double r5) {
        SQLitePreparedStatement r1 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO chat_hints VALUES(?, ?, ?, ?)");     // Catch: Exception -> L4
        r1.requery();     // Catch: Exception -> L4
        r1.bindLong(1, r2);     // Catch: Exception -> L4
        r1.bindInteger(2, r4);     // Catch: Exception -> L4
        r1.bindDouble(3, r5);     // Catch: Exception -> L4
        r1.bindInteger(4, ((int) System.currentTimeMillis()) / 1000);     // Catch: Exception -> L4
        r1.step();     // Catch: Exception -> L4
        r1.dispose();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$savePinnedMessages$168(ArrayList r7, long r8) {
        getMessagesStorage().getDatabase().beginTransaction();     // Catch: Exception -> L7
        SQLitePreparedStatement r0 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO chat_pinned_v2 VALUES(?, ?, ?)");     // Catch: Exception -> L7
        int r1 = r7.size();     // Catch: Exception -> L7
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        TLRPC.Message r3 = (TLRPC.Message) r7.get(r2);     // Catch: Exception -> L7
        MessageObject.normalizeFlags(r3);     // Catch: Exception -> L7
        NativeByteBuffer r4 = new NativeByteBuffer(r3.getObjectSize());     // Catch: Exception -> L7
        r3.serializeToStream(r4);     // Catch: Exception -> L7
        r0.requery();     // Catch: Exception -> L7
        r0.bindLong(1, r8);     // Catch: Exception -> L7
        r0.bindInteger(2, r3.id);     // Catch: Exception -> L7
        r0.bindByteBuffer(3, r4);     // Catch: Exception -> L7
        r0.step();     // Catch: Exception -> L7
        r4.reuse();     // Catch: Exception -> L7
        r2 = r2 + 1;     // Catch: Exception -> L7
        goto L3
    L5:
        r0.dispose();     // Catch: Exception -> L7
        getMessagesStorage().getDatabase().commitTransaction();     // Catch: Exception -> L7
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$saveReplyMessages$180(boolean r17, ArrayList r18, LongSparseArray r19) {
        getMessagesStorage().getDatabase().beginTransaction();     // Catch: Exception -> L35
        if (r17 == false) goto L5;
        SQLitePreparedStatement r0 = getMessagesStorage().getDatabase().executeFast("UPDATE scheduled_messages_v2 SET replydata = ?, reply_to_message_id = ? WHERE mid = ? AND uid = ?");     // Catch: Exception -> L35
        SQLitePreparedStatement r1 = null;
    L6:
        int r3 = 0;
    L8:
        if (r3 >= r18.size()) goto L30;
        TLRPC.Message r5 = (TLRPC.Message) r18.get(r3);     // Catch: Exception -> L35
        SparseArray r6 = (SparseArray) r19.get(MessageObject.getDialogId(r5));     // Catch: Exception -> L35
        if (r6 == null) goto L29;
        ArrayList r62 = (ArrayList) r6.get(r5.id);     // Catch: Exception -> L35
        if (r62 == null) goto L29;
        MessageObject.normalizeFlags(r5);     // Catch: Exception -> L35
        NativeByteBuffer r7 = new NativeByteBuffer(r5.getObjectSize());     // Catch: Exception -> L35
        r5.serializeToStream(r7);     // Catch: Exception -> L35
        int r9 = 0;
    L16:
        if (r9 >= r62.size()) goto L28;
        MessageObject r10 = (MessageObject) r62.get(r9);     // Catch: Exception -> L35
        int r11 = 0;
    L19:
        if (r11 >= 2) goto L27;
        if (r11 != 0) goto L22;
        SQLitePreparedStatement r13 = r0;
    L23:
        if (r13 == null) goto L26;
        r13.requery();     // Catch: Exception -> L35
        r13.bindByteBuffer(1, r7);     // Catch: Exception -> L35
        r13.bindInteger(2, r5.id);     // Catch: Exception -> L35
        r13.bindInteger(3, r10.getId());     // Catch: Exception -> L35
        r13.bindLong(4, r10.getDialogId());     // Catch: Exception -> L35
        r13.step();     // Catch: Exception -> L35
    L26:
        r11 = r11 + 1;     // Catch: Exception -> L35
        goto L19
    L22:
        r13 = r1;
        goto L23
    L27:
        r9 = r9 + 1;     // Catch: Exception -> L35
        goto L16
    L28:
        r7.reuse();     // Catch: Exception -> L35
    L29:
        r3 = r3 + 1;     // Catch: Exception -> L35
        goto L8
    L30:
        r0.dispose();     // Catch: Exception -> L35
        if (r1 == null) goto L33;
        r1.dispose();     // Catch: Exception -> L35
    L33:
        getMessagesStorage().getDatabase().commitTransaction();     // Catch: Exception -> L35
        return;
    L5:
        r0 = getMessagesStorage().getDatabase().executeFast("UPDATE messages_v2 SET replydata = ?, reply_to_message_id = ? WHERE mid = ? AND uid = ?");     // Catch: Exception -> L35
        r1 = getMessagesStorage().getDatabase().executeFast("UPDATE messages_topics SET replydata = ?, reply_to_message_id = ? WHERE mid = ? AND uid = ?");     // Catch: Exception -> L35
    L35:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$saveStickerSetIntoCache$41(TLRPC.TL_messages_stickerSet r5) {
        SQLitePreparedStatement r4 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO stickersets2 VALUES(?, ?, ?, ?, ?)");     // Catch: Exception -> L8
        r4.requery();     // Catch: Exception -> L8
        NativeByteBuffer r0 = new NativeByteBuffer(r5.getObjectSize());     // Catch: Exception -> L8
        r5.serializeToStream(r0);     // Catch: Exception -> L8
        r4.bindLong(1, r5.set.id);     // Catch: Exception -> L8
        r4.bindByteBuffer(2, r0);     // Catch: Exception -> L8
        r4.bindInteger(3, r5.set.hash);     // Catch: Exception -> L8
        r4.bindLong(4, System.currentTimeMillis());     // Catch: Exception -> L8
        String r52 = r5.set.short_name;     // Catch: Exception -> L8
        if (r52 != null) goto L5;
        String r53 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r4.bindString(5, r53);     // Catch: Exception -> L8
        r4.step();     // Catch: Exception -> L8
        r0.reuse();     // Catch: Exception -> L8
        r4.dispose();     // Catch: Exception -> L8
        return;
    L5:
        r53 = r52.toLowerCase();     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$saveToRingtones$206(TLObject r2, TLRPC.Document r3) {
        if (r2 == null) goto L9;
        boolean r0 = r2 instanceof TL_account.TL_savedRingtoneConverted;
        RingtoneDataStore r1 = this.ringtoneDataStore;
        if (r0 == false) goto L7;
        r1.addTone(((TL_account.TL_savedRingtoneConverted) r2).document);
        return;
    L7:
        r1.addTone(r3);
        return;
    }

    private /* synthetic */ void lambda$saveToRingtones$207(TLRPC.Document r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda120(this, r2, r1));
    }

    private /* synthetic */ void lambda$searchMessagesInChat$121(long r16, TLObject r18, TLRPC.TL_messages_search r19, long r20, int r22, int r23, long r24, TLRPC.User r26, TLRPC.Chat r27, boolean r28, ReactionsLayoutInBubble.VisibleReaction r29) {
        if (this.lastMergeDialogId != r16) goto L24;
        this.mergeReqId = 0;
        if (r18 == null) goto L19;
        TLRPC.messages_Messages r4 = (TLRPC.messages_Messages) r18;
        this.messagesSearchEndReached[1] = r4.messages.isEmpty();
        int r5 = 0;
        int r6 = 0;
    L8:
        if (r5 >= r4.messages.size()) goto L13;
        if (ChatUtils.hasRestrictionReason(r4.messages.get(r5).restriction_reason, "terms") == false) goto L12;
        r6 = r6 + 1;
    L12:
        r5 = r5 + 1;
        goto L8
    L13:
        this.filteredMessagesSearchCount[1] = r6;
        if ((r4 instanceof TLRPC.TL_messages_messagesSlice) == false) goto L16;
        int r42 = r4.count;
    L17:
        this.messagesSearchCount[1] = Math.max(r42 - this.filteredMessagesSearchCount[1], 0);
        searchMessagesInChat(r19.q, r20, r16, r22, r23, r24, true, r26, r27, r28, r29);
        return;
    L16:
        r42 = r4.messages.size();
        goto L17
    L19:
        this.messagesSearchEndReached[1] = true;
        this.messagesSearchCount[1] = 0;
        this.filteredMessagesSearchCount[1] = 0;
        searchMessagesInChat(r19.q, r20, r16, r22, r23, r24, true, r26, r27, r28, r29);
        return;
    }

    private /* synthetic */ void lambda$searchMessagesInChat$122(long r17, TLRPC.TL_messages_search r19, long r20, int r22, int r23, long r24, TLRPC.User r26, TLRPC.Chat r27, boolean r28, ReactionsLayoutInBubble.VisibleReaction r29, TLObject r30, TLRPC.TL_error r31) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda252(this, r17, r30, r19, r20, r22, r23, r24, r26, r27, r28, r29));
    }

    private /* synthetic */ void lambda$searchMessagesInChat$123(int r4, int r5, int r6, long r7, ArrayList r9, ArrayList r10, ArrayList r11, ArrayList r12) {
        if (r4 == this.lastReqId) goto L5;
        return;
    L5:
        if (r9.size() != r5) goto L7;
        boolean r42 = true;
    L8:
        this.loadedPredirectedSearchLocal = r42;
        this.loadingSearchLocal = false;
        getMessagesController().putUsers(r10, true);
        getMessagesController().putChats(r11, true);
        AnimatedEmojiDrawable.getDocumentFetcher(this.currentAccount).processDocuments(r12);
        this.searchLocalResultMessages = r9;
        updateSearchResults();
        getNotificationCenter().postNotificationName(NotificationCenter.chatSearchResultsAvailable, new Object[]{Integer.valueOf(r6), 0, Integer.valueOf(getMask()), Long.valueOf(r7), Integer.valueOf(this.lastReturnedNum), Integer.valueOf(getSearchCount()), Boolean.TRUE});
        return;
    L7:
        r42 = false;
        goto L8
    }

    private /* synthetic */ void lambda$searchMessagesInChat$124(long r23, long r25, TLRPC.TL_messages_search r27, int r28, int r29, int r30, ArrayList r31, TLRPC.messages_Messages r32, boolean r33, boolean r34, long r35, long r37, TLRPC.User r39, TLRPC.Chat r40) {
        if (r23 != r25) goto L5;
        char r4 = 0;
    L7:
        if (r27.offset_id != 0) goto L10;
        if (r23 != r25) goto L10;
        this.lastReturnedNum = 0;
        this.searchServerResultMessages.clear();
        this.searchServerResultMessagesMap[0].clear();
        this.searchServerResultMessagesMap[1].clear();
        this.messagesSearchCount[0] = 0;
        getNotificationCenter().postNotificationName(NotificationCenter.chatSearchResultsLoading, new Object[]{Integer.valueOf(r28)});
    L10:
        if (r29 != 0) goto L12;
        this.filteredMessagesSearchCount[r4] = 0;
    L12:
        int[] r6 = this.filteredMessagesSearchCount;
        r6[r4] = r6[r4] + r30;
        boolean r62 = r31.isEmpty();
        int r7 = 0;
    L14:
        if (r7 >= r31.size()) goto L16;
        MessageObject r9 = (MessageObject) r31.get(r7);
        this.searchServerResultMessages.add(r9);
        this.searchServerResultMessagesMap[r4].put(r9.getId(), r9);
        r7 = r7 + 1;
        goto L14
    L16:
        updateSearchResults();
        boolean[] r72 = this.messagesSearchEndReached;
        if (r32.messages.size() >= r27.limit) goto L19;
        boolean r1 = true;
    L20:
        r72[r4] = r1;
        if ((r32 instanceof TLRPC.TL_messages_messagesSlice) == false) goto L23;
    L26:
        int r12 = r32.count;
    L27:
        this.messagesSearchCount[r4] = Math.max(r12 - this.filteredMessagesSearchCount[r4], this.searchServerResultMessagesMap[r4].size());
        if (this.searchServerResultMessages.isEmpty() == false) goto L30;
        getNotificationCenter().postNotificationName(NotificationCenter.chatSearchResultsAvailable, new Object[]{Integer.valueOf(r28), 0, Integer.valueOf(getMask()), 0L, 0, 0, Boolean.valueOf(r33)});
    L37:
        if (r23 != r25) goto L47;
        boolean[] r13 = this.messagesSearchEndReached;
        if (r13[0] == true) goto L41;
        return;
    L41:
        if (r35 != 0) goto L43;
        return;
    L43:
        if (r13[1] == true) goto L50;
        searchMessagesInChat(this.lastSearchQuery, r25, r35, r28, 0, r37, true, r39, r40, r33, this.lastReaction);
        return;
    L50:
        return;
    L47:
        return;
    L30:
        if (r62 == false) goto L32;
        if (r34 == false) goto L37;
        getNotificationCenter().postNotificationName(NotificationCenter.chatSearchResultsAvailable, new Object[]{Integer.valueOf(r28), 0, Integer.valueOf(getMask()), Long.valueOf(r25), Integer.valueOf(this.lastReturnedNum), Integer.valueOf(getSearchCount()), Boolean.FALSE});
        goto L37
    L32:
        if (this.lastReturnedNum < this.searchResultMessages.size()) goto L34;
        this.lastReturnedNum = this.searchResultMessages.size() - 1;
    L34:
        MessageObject r14 = this.searchResultMessages.get(this.lastReturnedNum);
        getNotificationCenter().postNotificationName(NotificationCenter.chatSearchResultsAvailable, new Object[]{Integer.valueOf(r28), Integer.valueOf(r14.getId()), Integer.valueOf(getMask()), Long.valueOf(r14.getDialogId()), Integer.valueOf(this.lastReturnedNum), Integer.valueOf(getSearchCount()), Boolean.valueOf(r33)});
        goto L37
    L23:
        if ((r32 instanceof TLRPC.TL_messages_channelMessages) == true) goto L26;
        r12 = r32.messages.size();
        goto L27
    L19:
        r1 = false;
        goto L20
    L5:
        r4 = 1;
        goto L7
    }

    private /* synthetic */ void lambda$searchMessagesInChat$125(boolean r11, ArrayList r12, long r13, Runnable r15, int r16) {
        if (r11 == false) goto L5;
        loadReplyMessagesForMessages(r12, r13, 0, this.lastReplyMessageId, r15, r16, null);
        return;
    L5:
        r15.run();
    }

    private /* synthetic */ void lambda$searchMessagesInChat$126(int r21, boolean r22, TLObject r23, long r24, long r26, TLRPC.TL_messages_search r28, int r29, int r30, int r31, ArrayList r32, boolean r33, long r34, long r36, TLRPC.User r38, TLRPC.Chat r39, ReactionsLayoutInBubble.VisibleReaction r40) {
        if (r21 != this.lastReqId) goto L29;
        this.reqId = 0;
        if (r22 == true) goto L7;
        this.loadingMoreSearchMessages = false;
    L7:
        if (r23 == null) goto L30;
        TLRPC.messages_Messages r11 = (TLRPC.messages_Messages) r23;
        int r2 = 0;
    L10:
        if (r2 >= r11.messages.size()) goto L17;
        TLRPC.Message r3 = r11.messages.get(r2);
        if ((r3 instanceof TLRPC.TL_messageEmpty) == false) goto L14;
    L15:
        r11.messages.remove(r2);
        r2 = r2 - 1;
    L16:
        r2 = r2 + 1;
        goto L10
    L14:
        if ((r3.action instanceof TLRPC.TL_messageActionHistoryClear) == false) goto L16;
    L17:
        getMessagesStorage().putUsersAndChats(r11.users, r11.chats, true, true);
        getMessagesController().putUsers(r11.users, false);
        getMessagesController().putChats(r11.chats, false);
        MediaDataController$$ExternalSyntheticLambda148 r1 = new MediaDataController$$ExternalSyntheticLambda148(this, r33, r32, r26, new MediaDataController$$ExternalSyntheticLambda147(this, r24, r26, r28, r29, r30, r31, r32, r11, r22, r33, r34, r36, r38, r39), r29);
        if (r33 == false) goto L24;
        if (r40 == null) goto L24;
        if (r11.messages.isEmpty() == true) goto L24;
        getMessagesStorage().putMessages(r11, r26, -3, 0, false, 3, r36, r1);
        return;
    L24:
        r1.run();
        return;
    L30:
        return;
    }

    private /* synthetic */ void lambda$searchMessagesInChat$127(TLRPC.TL_messages_search r30, boolean r31, String r32, boolean r33, int r34, boolean r35, long r36, long r38, int r40, int r41, long r42, long r44, TLRPC.User r46, TLRPC.Chat r47, ReactionsLayoutInBubble.VisibleReaction r48, TLObject r49, TLRPC.TL_error r50) {
        ArrayList r13 = new ArrayList();
        int r0 = 0;
        if (r50 != null) goto L16;
        TLRPC.messages_Messages r1 = (TLRPC.messages_Messages) r49;
        TLRPC.TL_messages_search r9 = r30;
        int r2 = Math.min(r1.messages.size(), r9.limit - 1);
        int r3 = 0;
    L5:
        if (r0 >= r2) goto L14;
        TLRPC.Message r5 = r1.messages.get(r0);
        if (ChatUtils.hasRestrictionReason(r5.restriction_reason, "terms") == false) goto L9;
        r3 = r3 + 1;
    L13:
        r0 = r0 + 1;
        goto L5
    L9:
        MessageObject r14 = new MessageObject(this.currentAccount, r5, null, null, null, null, null, true, true, 0, false, false, r31);
        if (r14.hasValidGroupId() == false) goto L12;
        r14.isPrimaryGroupMessage = true;
    L12:
        r14.setQuery(r32, !r33);
        r13.add(r14);
        goto L13
    L14:
        int r12 = r3;
    L17:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda66(this, r34, r35, r49, r36, r38, r9, r40, r41, r12, r13, r31, r42, r44, r46, r47, r48));
        return;
    L16:
        r9 = r30;
        r12 = 0;
        goto L17
    }

    private /* synthetic */ void lambda$searchStickers$250(SearchStickersKey r1, SearchStickersResult r2, Utilities.Callback r3, TLRPC.messages_FoundStickers r4, TLRPC.TL_error r5) {
        this.loadingSearchStickersKeys.remove(r1);
        if (r2 != null) goto L7;
        r2 = new SearchStickersResult(null);
    L7:
        if ((r4 instanceof TLRPC.TL_messages_foundStickers) == false) goto L9;
        r2.apply((TLRPC.TL_messages_foundStickers) r4);
    L9:
        this.searchStickerResults.put(r1, r2);
        r3.run(r2.documents);
    }

    private /* synthetic */ void lambda$searchStickers$251(SearchStickersKey r7, SearchStickersResult r8, Utilities.Callback r9, ArrayList r10, String r11) {
        if (this.loadingSearchStickersKeys.containsKey(r7) == true) goto L5;
        return;
    L5:
        StringBuilder r112 = new StringBuilder();
        int r0 = r10.size();
        int r1 = 0;
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L12;
        Object r3 = r10.get(r2);
        r2 = r2 + 1;
        KeywordResult r32 = (KeywordResult) r3;
        if (TextUtils.isEmpty(r32.emoji) == true) goto L6;
        if (r32.emoji.startsWith("animated_") == true) goto L6;
        r112.append(r32.emoji);
        goto L6
    L12:
        TLRPC.TL_messages_searchStickers r102 = new TLRPC.TL_messages_searchStickers();
        r102.emojis = r7.emojis;
        if (TextUtils.isEmpty(r7.lang_code) == true) goto L15;
        r102.lang_code.add(r7.lang_code);
    L15:
        r102.emoticon = r112.toString();
        r102.q = r7.q;
        r102.limit = 100;
        if (r8 == null) goto L19;
        r1 = r8.next_offset.intValue();
    L19:
        r102.offset = r1;
        this.loadingSearchStickersKeys.put(r7, Integer.valueOf(getConnectionsManager().sendRequestTyped(r102, new AiTonesController$$ExternalSyntheticLambda0(), new MediaDataController$$ExternalSyntheticLambda200(this, r7, r8, r9))));
    }

    private /* synthetic */ void lambda$toggleStickerSet$109(boolean[] r7, TLRPC.StickerSet r8, int r9, int r10, TLRPC.TL_messages_stickerSet r11, Runnable r12) {
        if (r7[0] == false) goto L5;
        return;
    L5:
        r7[0] = true;
        markSetUninstalling(r8.id, false);
        r8.archived = false;
        this.stickerSets[r9].add(r10, r11);
        this.stickerSetsById.put(r8.id, r11);
        this.installedStickerSetsById.put(r8.id, r11);
        String r72 = r8.short_name;
        if (r72 == null) goto L8;
        this.stickerSetsByName.put(r72.toLowerCase(), r11);
    L8:
        this.removingStickerSetsUndos.remove(r8.id);
        this.loadHash[r9] = calcStickersHash(this.stickerSets[r9]);
        putStickersToCache(r9, this.stickerSets[r9], this.loadDate[r9], this.loadHash[r9]);
        if (r12 == null) goto L11;
        r12.run();
    L11:
        getNotificationCenter().postNotificationName(NotificationCenter.stickersDidLoad, new Object[]{Integer.valueOf(r9), Boolean.TRUE});
    }

    private /* synthetic */ void lambda$toggleStickerSet$110(boolean[] r3, Context r4, int r5, BaseFragment r6, FrameLayout r7, boolean r8, TLObject r9, TLRPC.StickerSet r10, TLRPC.Document r11, int r12) {
        if (r3[0] == false) goto L5;
        return;
    L5:
        r3[0] = true;
        toggleStickerSetInternal(r4, r5, r6, r7, r8, r9, r10, r11, r12, false);
    }

    private /* synthetic */ void lambda$toggleStickerSetInternal$113(TLRPC.StickerSet r2, ArrayList r3) {
        markSetInstalling(r2.id, false);
    }

    private /* synthetic */ void lambda$toggleStickerSetInternal$114(TLRPC.StickerSet r9, TLObject r10, BaseFragment r11, boolean r12, int r13, TLRPC.TL_error r14, boolean r15, FrameLayout r16, Context r17, TLObject r18, TLRPC.Document r19) {
        this.removingStickerSetsUndos.remove(r9.id);
        if ((r10 instanceof TLRPC.TL_messages_stickerSetInstallResultArchive) == false) goto L5;
        processStickerSetInstallResultArchive(r11, r12, r13, (TLRPC.TL_messages_stickerSetInstallResultArchive) r10);
    L5:
        loadStickers(r13, false, false, true, new MediaDataController$$ExternalSyntheticLambda234(this, r9));
        if (r14 != null) goto L19;
        if (r15 == true) goto L9;
        return;
    L9:
        if (r16 != null) goto L11;
        if (r11 == null) goto L21;
        Bulletin.make(r11, new StickerSetBulletinLayout(r17, r18, 2, r19, r11.getResourceProvider()), 1500).show();
        return;
    L21:
        return;
    L11:
        if (r11 == null) goto L13;
        Theme.ResourcesProvider r102 = r11.getResourceProvider();
    L14:
        Bulletin.make(r16, new StickerSetBulletinLayout(r17, r18, 2, r19, r102), 1500).show();
        return;
    L13:
        r102 = null;
        goto L14
    }

    private /* synthetic */ void lambda$toggleStickerSetInternal$115(TLRPC.StickerSet r14, BaseFragment r15, boolean r16, int r17, boolean r18, FrameLayout r19, Context r20, TLObject r21, TLRPC.Document r22, TLObject r23, TLRPC.TL_error r24) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda163(this, r14, r23, r15, r16, r17, r24, r18, r19, r20, r21, r22));
    }

    private /* synthetic */ void lambda$toggleStickerSetInternal$116(TLRPC.StickerSet r2, ArrayList r3) {
        markSetUninstalling(r2.id, false);
    }

    private /* synthetic */ void lambda$toggleStickerSetInternal$117(TLRPC.StickerSet r10, int r11) {
        this.removingStickerSetsUndos.remove(r10.id);
        loadStickers(r11, false, true, false, new MediaDataController$$ExternalSyntheticLambda42(this, r10));
    }

    private /* synthetic */ void lambda$toggleStickerSetInternal$118(TLRPC.StickerSet r1, int r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda29(this, r1, r2));
    }

    private /* synthetic */ void lambda$toggleStickerSets$119(int r3, TLObject r4, BaseFragment r5, boolean r6, int r7) {
        if (r3 != 0) goto L5;
        loadStickers(r7, false, true);
        return;
    L5:
        if ((r4 instanceof TLRPC.TL_messages_stickerSetInstallResultArchive) == false) goto L7;
        processStickerSetInstallResultArchive(r5, r6, r7, (TLRPC.TL_messages_stickerSetInstallResultArchive) r4);
    L7:
        loadStickers(r7, false, false, true);
    }

    private /* synthetic */ void lambda$toggleStickerSets$120(int r2, BaseFragment r3, boolean r4, int r5, TLObject r6, TLRPC.TL_error r7) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda87(this, r2, r6, r3, r4, r5));
    }

    private /* synthetic */ void lambda$updateBotInfo$205(TL_update.TL_updateBotCommands r4, long r5) {
        TL_bots.BotInfo r0 = loadBotInfoInternal(r4.bot_id, r5);     // Catch: Exception -> L7
        if (r0 == null) goto L5;
        r0.commands = r4.commands;     // Catch: Exception -> L7
    L5:
        SQLitePreparedStatement r3 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO bot_info_v2 VALUES(?, ?, ?)");     // Catch: Exception -> L7
        r3.requery();     // Catch: Exception -> L7
        NativeByteBuffer r42 = new NativeByteBuffer(r0.getObjectSize());     // Catch: Exception -> L7
        r0.serializeToStream(r42);     // Catch: Exception -> L7
        r3.bindLong(1, r0.user_id);     // Catch: Exception -> L7
        r3.bindLong(2, r5);     // Catch: Exception -> L7
        r3.bindByteBuffer(3, r42);     // Catch: Exception -> L7
        r3.step();     // Catch: Exception -> L7
        r42.reuse();     // Catch: Exception -> L7
        r3.dispose();     // Catch: Exception -> L7
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$updateEmojiStatuses$237(int r4, TL_account.TL_emojiStatuses r5) {
        getMessagesStorage().getDatabase().executeFast("DELETE FROM emoji_statuses WHERE type = " + r4).stepThis().dispose();     // Catch: Exception -> L5
        SQLitePreparedStatement r0 = getMessagesStorage().getDatabase().executeFast("INSERT INTO emoji_statuses VALUES(?, ?)");     // Catch: Exception -> L5
        r0.requery();     // Catch: Exception -> L5
        NativeByteBuffer r1 = new NativeByteBuffer(r5.getObjectSize());     // Catch: Exception -> L5
        r5.serializeToStream(r1);     // Catch: Exception -> L5
        r0.bindByteBuffer(1, r1);     // Catch: Exception -> L5
        r0.bindInteger(2, r4);     // Catch: Exception -> L5
        r0.step();     // Catch: Exception -> L5
        r1.reuse();     // Catch: Exception -> L5
        r0.dispose();     // Catch: Exception -> L5
    L7:
        this.emojiStatusesFetching[r4] = false;
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$verifyAnimatedStickerMessage$69(TLRPC.Message r1, String r2) {
        verifyAnimatedStickerMessageInternal(r1, r2);
    }

    private /* synthetic */ void lambda$verifyAnimatedStickerMessageInternal$70(String r14, TLObject r15) {
        ArrayList<TLRPC.Message> r142 = this.verifyingMessages.get(r14);
        int r1 = 0;
        if (r15 == null) goto L18;
        TLRPC.TL_messages_stickerSet r152 = (TLRPC.TL_messages_stickerSet) r15;
        storeTempStickerSet(r152);
        int r2 = r142.size();
        int r3 = 0;
    L5:
        if (r3 >= r2) goto L21;
        TLRPC.Message r4 = r142.get(r3);
        TLRPC.Document r5 = MessageObject.getDocument(r4);
        int r6 = r152.documents.size();
        int r7 = 0;
    L7:
        if (r7 >= r6) goto L15;
        TLRPC.Document r8 = r152.documents.get(r7);
        if (r8.id != r5.id) goto L13;
        if (r8.dc_id != r5.dc_id) goto L13;
        r4.stickerVerified = 1;
    L13:
        r7 = r7 + 1;
    L15:
        if (r4.stickerVerified != 0) goto L17;
        r4.stickerVerified = 2;
    L17:
        r3 = r3 + 1;
    L21:
        getNotificationCenter().postNotificationName(NotificationCenter.didVerifyMessagesStickers, new Object[]{r142});
        getMessagesStorage().updateMessageVerifyFlags(r142);
        return;
    L18:
        int r153 = r142.size();
    L19:
        if (r1 >= r153) goto L21;
        r142.get(r1).stickerVerified = 2;
        r1 = r1 + 1;
        goto L19
    }

    private /* synthetic */ void lambda$verifyAnimatedStickerMessageInternal$71(String r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda1(this, r1, r2));
    }

    private void loadAvatarConstructor(boolean r9) {
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("avatar_constructor" + this.currentAccount, 0);
        TLRPC.TL_emojiList r3 = null;
        if (r9 == false) goto L5;
        String r4 = r0.getString("profile", null);
        long r1 = r0.getLong("profile_last_check", 0);
    L6:
        if (r4 == null) goto L18;
        SerializedData r5 = new SerializedData(Utilities.hexToBytes(r4));
        TLRPC.TL_emojiList r42 = (TLRPC.TL_emojiList) TLRPC.EmojiList.TLdeserialize(r5, r5.readInt32(true), true);     // Catch: Throwable -> L15
        if (r9 == false) goto L13;
        this.profileAvatarConstructorDefault = r42;     // Catch: Throwable -> L11
    L28:
    L14:
        r3 = r42;
        goto L18
    L13:
        this.groupAvatarConstructorDefault = r42;     // Catch: Throwable -> L11
    L11:
        Throwable th = th;
    L17:
        FileLog.e(th);
    L15:
        th = move-exception;
        r42 = null;
        th = th;
    L18:
        if (r3 != null) goto L20;
    L23:
        TL_account.getDefaultProfilePhotoEmojis r12 = new TL_account.getDefaultProfilePhotoEmojis();
        if (r3 == null) goto L26;
        r12.hash = r3.hash;
    L26:
        getConnectionsManager().sendRequest(r12, new MediaDataController$$ExternalSyntheticLambda162(this, r0, r9));
        return;
    L20:
        if ((System.currentTimeMillis() - r1) > DurationKt.MILLIS_IN_DAY) goto L23;
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L23;
        return;
    L5:
        r4 = r0.getString("group", null);
        r1 = r0.getLong("group_last_check", 0);
        goto L6
    }

    private TL_bots.BotInfo loadBotInfoInternal(long r2, long r4) {
        SQLiteCursor r1 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT info FROM bot_info_v2 WHERE uid = %d AND dialogId = %d", new Object[]{Long.valueOf(r2), Long.valueOf(r4)}), new Object[0]);
        if (r1.next() == true) goto L5;
    L9:
        TL_bots.BotInfo r3 = null;
    L10:
        r1.dispose();
        return r3;
    L5:
        if (r1.isNull(0) == true) goto L9;
        NativeByteBuffer r22 = r1.byteBufferValue(0);
        if (r22 == null) goto L9;
        r3 = TL_bots.BotInfo.TLdeserialize(r22, r22.readInt32(false), false);
        r22.reuse();
        goto L10
    }

    private void loadDraftVoiceMessages() {
        if (this.draftVoicesLoaded == false) goto L5;
        return;
    L5:
        Set<Map.Entry<String, ?>> r0 = ApplicationLoader.applicationContext.getSharedPreferences("2voicedrafts_" + this.currentAccount, 0).getAll().entrySet();
        this.draftVoices.clear();
        Iterator<Map.Entry<String, ?>> r02 = r0.iterator();
    L7:
        if (r02.hasNext() == false) goto L12;
        Map.Entry<String, ?> r1 = r02.next();
        String r2 = r1.getKey();
        DraftVoice r12 = DraftVoice.fromString((String) r1.getValue());
        if (r12 == null) goto L7;
        this.draftVoices.put(Long.parseLong(r2), r12);
        goto L7
    L12:
        this.draftVoicesLoaded = true;
    }

    private void loadGroupStickerSet(TLRPC.StickerSet r4, boolean r5) {
        if (r5 == false) goto L5;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda82(this, r4));
        return;
    L5:
        TLRPC.TL_messages_getStickerSet r52 = new TLRPC.TL_messages_getStickerSet();
        TLRPC.TL_inputStickerSetID r0 = new TLRPC.TL_inputStickerSetID();
        r52.stickerset = r0;
        r0.id = r4.id;
        r0.access_hash = r4.access_hash;
        getConnectionsManager().sendRequest(r52, new MediaDataController$$ExternalSyntheticLambda83(this));
    }

    private void loadMediaDatabase(long r16, int r18, int r19, int r20, int r21, long r22, ReactionsLayoutInBubble.VisibleReaction r24, int r25, boolean r26, int r27, int r28) {
        AnonymousClass1 r0 = new AnonymousClass1(this, r18, r16, r20, r22, r21, r24, r19, r25, r27, r26, r28);
        MessagesStorage r15 = getMessagesStorage();
        r15.getStorageQueue().postRunnable(r0);
        r15.bindTaskToGuid(r0, r25);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v13, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v5 */
    private ArrayList<MessageObject> loadPinnedMessageInternal(long r20, long r22, ArrayList<Integer> r24, boolean r25) {
        ArrayList<Integer> r1 = new ArrayList(r24);     // Catch: Exception -> L11
        if (r22 == 0) goto L14;
        Object r10 = new StringBuilder();     // Catch: Exception -> L11
        int r11 = r24.size();     // Catch: Exception -> L11
        int r12 = 0;
    L7:
        if (r12 >= r11) goto L15;
        Integer r13 = r24.get(r12);     // Catch: Exception -> L11
        if (r10.length() == 0) goto L13;
        r10.append(",");     // Catch: Exception -> L11
    L13:
        r10.append(r13);     // Catch: Exception -> L11
        r12 = r12 + 1;     // Catch: Exception -> L11
    L15:
        ArrayList<TLRPC.Message> r112 = new ArrayList();     // Catch: Exception -> L11
        ArrayList<TLRPC.User> r122 = new ArrayList();     // Catch: Exception -> L11
        ArrayList<TLRPC.Chat> r132 = new ArrayList();     // Catch: Exception -> L11
        ArrayList<Long> r14 = new ArrayList();     // Catch: Exception -> L11
        ArrayList r15 = new ArrayList();     // Catch: Exception -> L11
        long r7 = getUserConfig().clientUserId;     // Catch: Exception -> L11
        SQLiteDatabase r0 = getMessagesStorage().getDatabase();     // Catch: Exception -> L11
        String r6 = String.format(Locale.US, "SELECT data, mid, date FROM messages_v2 WHERE mid IN (%s) AND uid = %d", new Object[]{r10, Long.valueOf(r20)});     // Catch: Exception -> L11
        ?? r9 = 0;
        SQLiteCursor r02 = r0.queryFinalized(r6, new Object[0]);     // Catch: Exception -> L11
    L17:
        if (r02.next() == false) goto L25;
        NativeByteBuffer r62 = r02.byteBufferValue(r9);     // Catch: Exception -> L11
        if (r62 == 0) goto L24;
        TLRPC.Message r102 = TLRPC.Message.TLdeserialize(r62, r62.readInt32(r9), r9);     // Catch: Exception -> L11
        if ((r102.action instanceof TLRPC.TL_messageActionHistoryClear) == true) goto L23;
        r102.readAttachPath(r62, r7);     // Catch: Exception -> L11
        r102.id = r02.intValue(1);     // Catch: Exception -> L11
        r102.date = r02.intValue(2);     // Catch: Exception -> L11
        r102.dialog_id = r20;     // Catch: Exception -> L11
        MessagesStorage.addUsersAndChatsFromMessage(r102, r14, r15, null);     // Catch: Exception -> L11
        r112.add(r102);     // Catch: Exception -> L11
        r1.remove(Integer.valueOf(r102.id));     // Catch: Exception -> L11
    L23:
        r62.reuse();     // Catch: Exception -> L11
    L24:
        r9 = 0;
        goto L17
    L25:
        r02.dispose();     // Catch: Exception -> L11
        if (r1.isEmpty() == true) goto L37;
        CharSequence r133 = ",";
        ArrayList<TLRPC.User> r17 = r122;
        SQLiteCursor r03 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT data FROM chat_pinned_v2 WHERE uid = %d AND mid IN (%s)", new Object[]{Long.valueOf(r20), TextUtils.join(r133, r1)}), new Object[0]);     // Catch: Exception -> L11
    L29:
        if (r03.next() == false) goto L36;
        NativeByteBuffer r63 = r03.byteBufferValue(0);     // Catch: Exception -> L11
        if (r63 == null) goto L29;
        TLRPC.Message r103 = TLRPC.Message.TLdeserialize(r63, r63.readInt32(false), false);     // Catch: Exception -> L11
        if ((r103.action instanceof TLRPC.TL_messageActionHistoryClear) == true) goto L35;
        r103.readAttachPath(r63, r7);     // Catch: Exception -> L11
        r103.dialog_id = r20;     // Catch: Exception -> L11
        MessagesStorage.addUsersAndChatsFromMessage(r103, r14, r15, null);     // Catch: Exception -> L11
        r112.add(r103);     // Catch: Exception -> L11
        r1.remove(Integer.valueOf(r103.id));     // Catch: Exception -> L11
    L35:
        r63.reuse();     // Catch: Exception -> L11
        goto L29
    L36:
        r03.dispose();     // Catch: Exception -> L11
    L39:
        if (r1.isEmpty() == true) goto L42;
        if (r22 == 0) goto L43;
        TLRPC.TL_channels_getMessages r64 = new TLRPC.TL_channels_getMessages();     // Catch: Exception -> L11
        r64.channel = getMessagesController().getInputChannel(r22);     // Catch: Exception -> L11
        r64.id = r1;     // Catch: Exception -> L11
        getConnectionsManager().sendRequest(r64, new MediaDataController$$ExternalSyntheticLambda106(this, r22, r20, r64));     // Catch: Exception -> L11
        goto L42
    L43:
        TLRPC.TL_messages_getMessages r04 = new TLRPC.TL_messages_getMessages();     // Catch: Exception -> L11
        r04.id = r1;     // Catch: Exception -> L11
        MediaDataController r3 = this;
        getConnectionsManager().sendRequest(r04, new MediaDataController$$ExternalSyntheticLambda107(r3, r20, r04));     // Catch: Exception -> L11
    L45:
        if (r112.isEmpty() == false) goto L47;
        return null;
    L47:
        if (r14.isEmpty() == true) goto L49;
        ArrayList<TLRPC.User> r16 = r17;
        r3.getMessagesStorage().getUsersInternal(r14, r16);     // Catch: Exception -> L11
    L51:
        if (r15.isEmpty() == true) goto L53;
        ArrayList<TLRPC.Chat> r4 = r132;
        r3.getMessagesStorage().getChatsInternal(TextUtils.join(r133, r15), r4);     // Catch: Exception -> L11
    L54:
        if (r25 == false) goto L57;
        MediaDataController r202 = r3;
        return r202.broadcastPinnedMessage(r112, r16, r4, true, true);
    L57:
        broadcastPinnedMessage(r112, r16, r4, true, false);     // Catch: Exception -> L11
        return null;
    L53:
        r4 = r132;
        goto L54
    L49:
        r16 = r17;
    L42:
        r3 = this;
        goto L45
    L37:
        r133 = ",";
        r17 = r122;
        goto L39
    L14:
        r10 = TextUtils.join(",", r24);     // Catch: Exception -> L11
    L11:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static ArrayList<TLRPC.Reaction> loadReactionsFromPref(SharedPreferences r6) {
        int r1 = 0;
        int r0 = r6.getInt(NotificationBadge.NewHtcHomeBadger.COUNT, 0);
        ArrayList<TLRPC.Reaction> r2 = new ArrayList(r0);
        if (r0 <= 0) goto L11;
    L4:
        if (r1 >= r0) goto L11;
        SerializedData r4 = new SerializedData(Utilities.hexToBytes(r6.getString("object_" + r1, _UrlKt.FRAGMENT_ENCODE_SET)));
        r2.add(TLRPC.Reaction.TLdeserialize(r4, r4.readInt32(true), true));     // Catch: Throwable -> L8
    L10:
        r1 = r1 + 1;
    L8:
        th = move-exception;
        FileLog.e(th);
    L11:
        return r2;
    }

    private void loadRepliesOfDraftReplies(ArrayList<TLRPC.Message> r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.isEmpty() == true) goto L9;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda141(this, r3));
        return;
    }

    private void loadStickerSetThumbInternal(TLRPC.PhotoSize r7, Object r8, TLRPC.Document r9, int r10) {
        ImageLocation r1 = ImageLocation.getForSticker(r7, r9, r10);
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.imageType != 1) goto L8;
        String r72 = "tgs";
    L7:
        String r3 = r72;
        getFileLoader().loadFile(r1, r8, r3, 3, 1);
        return;
    L8:
        r72 = "webp";
        goto L7
    }

    public static void offsetEntities(ArrayList<TLRPC.MessageEntity> r4, int r5) {
        if (r4 == null) goto L7;
        int r0 = r4.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L9;
        TLRPC.MessageEntity r2 = r4.get(r1);
        r1 = r1 + 1;
        r2.offset += r5;
        goto L5
    L9:
        return;
    }

    private CharSequence parsePattern(CharSequence r9, Pattern r10, ArrayList<TLRPC.MessageEntity> r11, GenericProvider<Void, TLRPC.MessageEntity> r12) {
        Matcher r8 = r10.matcher(r9);
        int r0 = 0;
    L4:
        if (r8.find() == false) goto L27;
        boolean r1 = true;
        String r2 = r8.group(1);
        if ((r9 instanceof Spannable) == false) goto L12;
        URLSpan[] r3 = (URLSpan[]) ((Spannable) r9).getSpans(r8.start() - r0, r8.end() - r0, URLSpan.class);
        if (r3 == null) goto L12;
        if (r3.length <= 0) goto L12;
        r1 = false;
    L12:
        if (r1 == false) goto L24;
        int r32 = 0;
    L15:
        if (r32 >= r11.size()) goto L24;
        TLRPC.MessageEntity r4 = r11.get(r32);
        if ((r4 instanceof TLRPC.TL_messageEntityPre) == false) goto L19;
    L20:
        int r5 = r8.start() - r0;
        int r6 = r8.end() - r0;
        int r7 = r4.offset;
        if (AndroidUtilities.intersect1d(r5, r6, r7, r4.length + r7) == false) goto L23;
        r1 = false;
    L23:
        r32 = r32 + 1;
        goto L15
    L19:
        if ((r4 instanceof TLRPC.TL_messageEntityCode) == false) goto L23;
    L24:
        if (r1 == false) goto L26;
        r9 = r9.subSequence(0, r8.start() - r0) + r2 + r9.subSequence(r8.end() - r0, r9.length());
        TLRPC.MessageEntity r13 = r12.provide(null);
        r13.offset = r8.start() - r0;
        int r33 = r2.length();
        r13.length = r33;
        int r42 = r13.offset;
        removeOffset4After(r42, r33 + r42, r11);
        r11.add(r13);
    L26:
        r0 = r0 + ((r8.end() - r8.start()) - r2.length());
        goto L4
    L27:
        return r9;
    }

    private void preloadNextGreetingsSticker() {
        if (this.recentStickers[3].isEmpty() == false) goto L5;
        return;
    L5:
        ArrayList<TLRPC.Document> r0 = this.recentStickers[3];
        this.greetingsSticker = r0.get(Utilities.random.nextInt(r0.size()));
        getFileLoader().loadFile(ImageLocation.getForDocument(this.greetingsSticker), this.greetingsSticker, null, 0, 1);
    }

    private void processLoadStickersResponse(int r2, TLRPC.TL_messages_allStickers r3) {
        processLoadStickersResponse(r2, r3, null);
    }

    private void processLoadStickersResponse(int r13, TLRPC.TL_messages_allStickers r14, Runnable r15) {
        ArrayList<TLRPC.TL_messages_stickerSet> r2 = new ArrayList();
        if (r14.sets.isEmpty() == false) goto L6;
        processLoadedStickers(r13, r2, false, (int) (System.currentTimeMillis() / 1000), r14.hash2, r15);
        return;
    L6:
        MediaDataController r0 = this;
        int r1 = r13;
        LongSparseArray r132 = new LongSparseArray();
        int r152 = 0;
    L8:
        if (r152 >= r14.sets.size()) goto L19;
        TLRPC.StickerSet r5 = r14.sets.get(r152);
        TLRPC.TL_messages_stickerSet r3 = r0.stickerSetsById.get(r5.id);
        if (r3 == null) goto L17;
        TLRPC.StickerSet r4 = r3.set;
        if (r4.hash != r5.hash) goto L17;
        r4.archived = r5.archived;
        r4.installed = r5.installed;
        r4.official = r5.official;
        r132.put(r4.id, r3);
        r2.add(r3);
        if (r132.size() != r14.sets.size()) goto L16;
        r0.processLoadedStickers(r1, r2, false, (int) (System.currentTimeMillis() / 1000), r14.hash2);
    L16:
        LongSparseArray r42 = r132;
        TLRPC.TL_messages_allStickers r6 = r14;
        int r32 = r152;
    L18:
        r152 = r32 + 1;
        r132 = r42;
        r14 = r6;
    L17:
        r2.add(null);
        TLRPC.TL_messages_getStickerSet r10 = new TLRPC.TL_messages_getStickerSet();
        TLRPC.TL_inputStickerSetID r33 = new TLRPC.TL_inputStickerSetID();
        r10.stickerset = r33;
        r33.id = r5.id;
        r33.access_hash = r5.access_hash;
        ConnectionsManager r11 = r0.getConnectionsManager();
        int r7 = r1;
        MediaDataController r12 = r0;
        r42 = r132;
        r6 = r14;
        r32 = r152;
        MediaDataController$$ExternalSyntheticLambda220 r02 = new MediaDataController$$ExternalSyntheticLambda220(r12, r2, r32, r42, r5, r6, r7);
        r0 = r12;
        r1 = r7;
        r11.sendRequest(r10, r02);
        goto L18
    L19:
        if (r15 == null) goto L25;
        r15.run();
        return;
    }

    private void processLoadedDiceStickers(String r9, boolean r10, TLRPC.TL_messages_stickerSet r11, boolean r12, int r13) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda25(this, r9));
        Utilities.stageQueue.postRunnable(new MediaDataController$$ExternalSyntheticLambda26(this, r12, r11, r13, r9, r10));
    }

    private void processLoadedFeaturedStickers(boolean r12, ArrayList<TLRPC.StickerSetCovered> r13, ArrayList<Long> r14, boolean r15, boolean r16, int r17, long r18) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda22(this, r12));
        Utilities.stageQueue.postRunnable(new MediaDataController$$ExternalSyntheticLambda23(this, r16, r13, r17, r18, r12, r14, r15));
    }

    private void processLoadedMedia(TLRPC.messages_Messages r15, long r16, int r18, int r19, int r20, int r21, long r22, int r24, int r25, boolean r26, boolean r27, int r28) {
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        if (r15 == null) goto L8;
        ArrayList<TLRPC.Message> r0 = r15.messages;
        if (r0 == null) goto L8;
        int r02 = r0.size();
    L9:
        StringBuilder r1 = new StringBuilder("process load media messagesCount ");
        r1.append(r02);
        r1.append(" did ");
        long r2 = r16;
        r1.append(r2);
        r1.append(" topicId ");
        long r7 = r22;
        r1.append(r7);
        r1.append(" count = ");
        int r03 = r18;
        r1.append(r03);
        r1.append(" max_id=");
        int r4 = r19;
        r1.append(r4);
        r1.append(" min_id=");
        r1.append(r20);
        r1.append(" type = ");
        int r6 = r21;
        r1.append(r6);
        r1.append(" cache = ");
        r1.append(r24);
        r1.append(" classGuid = ");
        r1.append(r25);
        r1.append(" topReached=");
        boolean r11 = r27;
        r1.append(r11);
        FileLog.d(r1.toString());
    L12:
        if (r24 == 0) goto L29;
        if (r15 == null) goto L29;
        ArrayList<TLRPC.Message> r12 = r15.messages;
        if (r12 == null) goto L29;
        if (r12.isEmpty() == false) goto L20;
        if (r20 != 0) goto L20;
    L23:
        if (DialogObject.isEncryptedDialog(r2) == true) goto L29;
        if (r24 != 2) goto L27;
        return;
    L27:
        loadMedia(r2, r03, r4, r20, r6, r7, 0, r25, r28, null, null);
        return;
    L20:
        if (r15.messages.size() > 1) goto L29;
        if (r20 != 0) goto L23;
    L29:
        if (r24 != 0) goto L31;
        ImageLoader.saveMessagesThumbs(r15.messages);
        getMessagesStorage().putUsersAndChats(r15.users, r15.chats, true, true);
        putMediaDatabase(r16, r22, r21, r15.messages, r19, r20, r11);
    L31:
        Utilities.searchQueue.postRunnable(new MediaDataController$$ExternalSyntheticLambda71(this, r15, r24, r16, r25, r21, r27, r20, r28));
        return;
    L8:
        r02 = 0;
        goto L9
    L10:
        r2 = r16;
        r03 = r18;
        r4 = r19;
        r6 = r21;
        r7 = r22;
        r11 = r27;
        goto L12
    }

    private void processLoadedMediaCount(int r12, long r13, long r15, int r17, int r18, boolean r19, int r20) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda28(this, r13, r19, r12, r17, r20, r15, r18));
    }

    private void processLoadedStickers(int r9, ArrayList<TLRPC.TL_messages_stickerSet> r10, boolean r11, int r12, long r13) {
        processLoadedStickers(r9, r10, r11, r12, r13, null);
    }

    private void processLoadedStickers(int r11, ArrayList<TLRPC.TL_messages_stickerSet> r12, boolean r13, int r14, long r15, Runnable r17) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda198(this, r11));
        Utilities.stageQueue.postRunnable(new MediaDataController$$ExternalSyntheticLambda199(this, r13, r12, r14, r15, r11, r17));
    }

    private void putDiceStickersToCache(String r3, TLRPC.TL_messages_stickerSet r4, int r5) {
        if (TextUtils.isEmpty(r3) == false) goto L5;
        return;
    L5:
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda91(this, r4, r3, r5));
    }

    private void putEmojiKeywords(String r3, TLRPC.TL_emojiKeywordsDifference r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda189(this, r4, r3));
    }

    private void putFeaturedStickersToCache(boolean r11, ArrayList<TLRPC.StickerSetCovered> r12, ArrayList<Long> r13, int r14, long r15, boolean r17) {
        if (r12 == null) goto L5;
        ArrayList r0 = new ArrayList(r12);
    L4:
        ArrayList r3 = r0;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda79(this, r3, r13, r11, r14, r15, r17));
        return;
    L5:
        r0 = null;
        goto L4
    }

    private void putMediaCountDatabase(long r10, long r12, int r14, int r15) {
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda206(this, r12, r10, r14, r15));
    }

    private void putMediaDatabase(long r13, long r15, int r17, ArrayList<TLRPC.Message> r18, int r19, int r20, boolean r21) {
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda243(this, r20, r18, r21, r13, r19, r17, r15));
    }

    private void putMenuBotsToCache(TLRPC.TL_attachMenuBots r8, long r9, int r11) {
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda126(this, r8, r9, r11));
    }

    private void putPremiumPromoToCache(TLRPC.TL_help_premiumPromo r3, int r4) {
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda121(this, r3, r4));
    }

    private void putReactionsToCache(List<TLRPC.TL_availableReaction> r3, int r4, int r5) {
        if (r3 == null) goto L4;
        ArrayList r0 = new ArrayList(r3);
    L5:
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda235(this, r0, r4, r5));
        return;
    L4:
        r0 = null;
        goto L5
    }

    private void putSetToCache(TLRPC.TL_messages_stickerSet r3) {
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda229(this, r3));
    }

    private void putStickersToCache(int r9, ArrayList<TLRPC.TL_messages_stickerSet> r10, int r11, long r12) {
        if (r10 == null) goto L5;
        ArrayList r0 = new ArrayList(r10);
    L4:
        ArrayList r3 = r0;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda119(this, r3, r9, r11, r12));
        return;
    L5:
        r0 = null;
        goto L4
    }

    private static void removeEmptyMessages(ArrayList<TLRPC.Message> r3) {
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L13;
        TLRPC.Message r1 = r3.get(r0);
        if (r1 != null) goto L8;
    L11:
        r3.remove(r0);
        r0 = r0 - 1;
    L12:
        r0 = r0 + 1;
        goto L4
    L8:
        if ((r1 instanceof TLRPC.TL_messageEmpty) == true) goto L11;
        if ((r1.action instanceof TLRPC.TL_messageActionHistoryClear) == false) goto L12;
    }

    private void removeInline(long r6, boolean r8) {
        if (r8 == false) goto L4;
        ArrayList<TLRPC.TL_topPeer> r0 = this.guestBots;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= r0.size()) goto L21;
        if (r0.get(r2).peer.user_id == r6) goto L10;
        r2 = r2 + 1;
        goto L7
    L10:
        r0.remove(r2);
        TLRPC.TL_contacts_resetTopPeerRating r02 = new TLRPC.TL_contacts_resetTopPeerRating();
        r02.category = new TLRPC.TL_topPeerCategoryBotsInline();
        r02.peer = getMessagesController().getInputPeer(r6);
        getConnectionsManager().sendRequest(r02, new MediaDataController$$ExternalSyntheticLambda142());
        if (r8 == false) goto L13;
        int r03 = 3;
    L14:
        deletePeer(r6, r03);
        NotificationCenter r5 = getNotificationCenter();
        if (r8 == false) goto L17;
        int r62 = NotificationCenter.reloadGuestBotHints;
    L18:
        r5.postNotificationName(r62, new Object[0]);
        return;
    L17:
        r62 = NotificationCenter.reloadInlineHints;
        goto L18
    L13:
        r03 = 1;
        goto L14
    L21:
        return;
    L4:
        r0 = this.inlineBots;
        goto L5
    }

    private static void removeOffset4After(int r4, int r5, ArrayList<TLRPC.MessageEntity> r6) {
        int r0 = r6.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        TLRPC.MessageEntity r2 = r6.get(r1);
        int r3 = r2.offset;
        if (r3 <= r5) goto L7;
        r2.offset = r3 - 4;
    L9:
        r1 = r1 + 1;
        goto L3
    L7:
        if (r3 <= r4) goto L9;
        r2.offset = r3 - 2;
        goto L9
    }

    private static boolean replyToEquals(TLRPC.InputReplyTo r4, TLRPC.InputReplyTo r5) {
        if (r4 != r5) goto L5;
        return true;
    L5:
        boolean r1 = r4 instanceof TLRPC.TL_inputReplyToMessage;
        if (r1 == (r5 instanceof TLRPC.TL_inputReplyToMessage)) goto L8;
        return false;
    L8:
        if (r1 == false) goto L20;
        if (MessageObject.peersEqual(r4.reply_to_peer_id, r5.reply_to_peer_id) == true) goto L13;
        return false;
    L13:
        if (TextUtils.equals(r4.quote_text, r5.quote_text) == true) goto L16;
        return false;
    L16:
        if (r4.reply_to_msg_id != r5.reply_to_msg_id) goto L18;
        return true;
    L18:
        return false;
    L20:
        if ((r4 instanceof TLRPC.TL_inputReplyToStory) == true) goto L22;
        return true;
    L22:
        if (MessageObject.peersEqual(r4.peer, r5.peer) == true) goto L24;
    L26:
        return false;
    L24:
        if (r4.story_id != r5.story_id) goto L26;
        return true;
    }

    private static boolean richMessageEquals(TL_iv.RichMessage r4, TL_iv.RichMessage r5) {
        boolean r0 = true;
        if (r4 != r5) goto L6;
        return true;
    L6:
        if (r4 != null) goto L8;
        boolean r2 = true;
    L9:
        if (r5 == null) goto L12;
        r0 = false;
    L12:
        if (r2 == r0) goto L19;
        return false;
    L19:
        SerializedData r02 = new SerializedData(r4.getObjectSize());     // Catch: Exception -> L16
        SerializedData r22 = new SerializedData(r5.getObjectSize());     // Catch: Exception -> L16
        r4.serializeToStream(r02);     // Catch: Exception -> L16
        r5.serializeToStream(r22);     // Catch: Exception -> L16
        return Arrays.equals(r02.toByteArray(), r22.toByteArray());
    L16:
        e = move-exception;
        FileLog.e(e);
        return false;
    L8:
        r2 = false;
        goto L9
    }

    private void saveDraftReplyMessage(long r8, long r10, TLRPC.Message r12) {
        if (r12 != null) goto L4;
        return;
    L4:
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda64(this, r8, r10, r12));
    }

    private void savePeer(long r9, int r11, double r12) {
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda104(this, r9, r11, r12));
    }

    private void savePinnedMessages(long r3, ArrayList<TLRPC.Message> r5) {
        if (r5.isEmpty() == false) goto L5;
        return;
    L5:
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda90(this, r5, r3));
    }

    public static void saveReactionsToPref(SharedPreferences r2, long r3, ArrayList<? extends TLObject> r5) {
        SharedPreferences.Editor r22 = r2.edit();
        r22.putInt(NotificationBadge.NewHtcHomeBadger.COUNT, r5.size());
        r22.putLong("hash", r3);
        int r32 = 0;
    L4:
        if (r32 >= r5.size()) goto L6;
        TLObject r4 = r5.get(r32);
        SerializedData r0 = new SerializedData(r4.getObjectSize());
        r4.serializeToStream(r0);
        r22.putString("object_" + r32, Utilities.bytesToHex(r0.toByteArray()));
        r32 = r32 + 1;
        goto L4
    L6:
        r22.apply();
    }

    private void saveReplyMessages(LongSparseArray<SparseArray<ArrayList<MessageObject>>> r3, ArrayList<TLRPC.Message> r4, boolean r5) {
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda227(this, r5, r4, r3));
    }

    private void saveStickerSetIntoCache(TLRPC.TL_messages_stickerSet r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.set == null) goto L9;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda112(this, r3));
        cleanupStickerSetCache();
        return;
    }

    private TLRPC.MessageEntity setEntityStartEnd(TLRPC.MessageEntity r1, int r2, int r3) {
        r1.offset = r2;
        r1.length = r3 - r2;
        return r1;
    }

    public static void sortEntities(ArrayList<TLRPC.MessageEntity> r1) {
        Collections.sort(r1, entityComparator);
    }

    public static boolean stringsEqual(CharSequence r3, CharSequence r4) {
        if (r3 != null) goto L7;
        if (r4 != null) goto L7;
        return true;
    L7:
        if (r3 == null) goto L15;
        if (r4 == null) goto L15;
        if (TextUtils.equals(r3, r4) == true) goto L14;
        return false;
    L14:
        return entitiesEqual(getInstance(UserConfig.selectedAccount).getEntities(new CharSequence[]{new SpannableStringBuilder(r3)}, true), getInstance(UserConfig.selectedAccount).getEntities(new CharSequence[]{new SpannableStringBuilder(r4)}, true));
    L15:
        return false;
    }

    private static boolean suggestedPostEquals(TLRPC.SuggestedPost r4, TLRPC.SuggestedPost r5) {
        if (r4 != r5) goto L6;
        return true;
    L6:
        if (r4 != null) goto L8;
        boolean r2 = true;
    L9:
        if (r5 != null) goto L11;
        boolean r3 = true;
    L12:
        if (r2 == r3) goto L15;
        return false;
    L15:
        if (AmountUtils$Amount.equals(r4.price, r5.price) == false) goto L17;
    L25:
        return false;
    L17:
        if (r4.schedule_date != r5.schedule_date) goto L25;
        if (r4.accepted != r5.accepted) goto L25;
        if (r4.rejected != r5.rejected) goto L25;
        return true;
    L11:
        r3 = false;
        goto L12
    L8:
        r2 = false;
        goto L9
    }

    private static TLRPC.InputReplyTo toInputReplyTo(int r2, TLRPC.MessageReplyHeader r3) {
        if ((r3 instanceof TLRPC.TL_messageReplyStoryHeader) == false) goto L7;
        TLRPC.TL_inputReplyToStory r0 = new TLRPC.TL_inputReplyToStory();
        r0.peer = MessagesController.getInstance(r2).getInputPeer(r3.peer);
        r0.story_id = r3.story_id;
        return r0;
    L7:
        if ((r3 instanceof TLRPC.TL_messageReplyHeader) == false) goto L23;
        TLRPC.TL_inputReplyToMessage r02 = new TLRPC.TL_inputReplyToMessage();
        r02.reply_to_msg_id = r3.reply_to_msg_id;
        if ((r3.flags & 1) == 0) goto L13;
        TLRPC.InputPeer r22 = MessagesController.getInstance(r2).getInputPeer(r3.reply_to_peer_id);
        r02.reply_to_peer_id = r22;
        if (r22 == null) goto L13;
        r02.flags |= 2;
    L13:
        int r23 = r3.flags;
        if ((r23 & 2) == 0) goto L17;
        r02.flags |= 1;
        r02.top_msg_id = r3.reply_to_top_id;
    L17:
        if ((r23 & 64) == 0) goto L20;
        r02.flags |= 4;
        r02.quote_text = r3.quote_text;
    L20:
        if ((r23 & 128) == 0) goto L22;
        r02.flags |= 8;
        r02.quote_entities = r3.quote_entities;
    L22:
        return r02;
    L23:
        return null;
    }

    private TL_iv.TL_inputRichMessage toInputRichMessage(TL_iv.RichMessage r7) {
        TL_iv.TL_inputRichMessage r6 = new TL_iv.TL_inputRichMessage();
        r6.rtl = r7.rtl;
        r6.blocks = new ArrayList(r7.blocks.size());
        int r1 = 0;
    L4:
        if (r1 >= r7.blocks.size()) goto L6;
        r6.blocks.add(SendMessagesHelper.toInputPageBlock(r7.blocks.get(r1)));
        r1 = r1 + 1;
        goto L4
    L6:
        ArrayList<TLRPC.Photo> r12 = r7.photos;
        if (r12 != null) goto L9;
    L19:
        ArrayList<TLRPC.Document> r13 = r7.documents;
        if (r13 != null) goto L22;
    L32:
        return r6;
    L22:
        if (r13.isEmpty() == true) goto L32;
        int r14 = 0;
    L25:
        if (r14 >= r7.documents.size()) goto L31;
        TLRPC.Document r2 = r7.documents.get(r14);
        TLRPC.TL_inputDocument r3 = new TLRPC.TL_inputDocument();
        r3.id = r2.id;
        r3.access_hash = r2.access_hash;
        byte[] r22 = r2.file_reference;
        if (r22 != null) goto L30;
        r22 = new byte[0];
    L30:
        r3.file_reference = r22;
        r6.documents.add(r3);
        r14 = r14 + 1;
        goto L25
    L31:
        r6.flags |= 8;
        goto L32
    L9:
        if (r12.isEmpty() == true) goto L19;
        int r15 = 0;
    L12:
        if (r15 >= r7.photos.size()) goto L18;
        TLRPC.Photo r23 = r7.photos.get(r15);
        TLRPC.TL_inputPhoto r32 = new TLRPC.TL_inputPhoto();
        r32.id = r23.id;
        r32.access_hash = r23.access_hash;
        byte[] r24 = r23.file_reference;
        if (r24 != null) goto L17;
        r24 = new byte[0];
    L17:
        r32.file_reference = r24;
        r6.photos.add(r32);
        r15 = r15 + 1;
        goto L12
    L18:
        r6.flags |= 4;
        goto L19
    }

    private void toggleStickerSetInternal(Context r13, int r14, BaseFragment r15, FrameLayout r16, boolean r17, TLObject r18, TLRPC.StickerSet r19, TLRPC.Document r20, int r21, boolean r22) {
        TLRPC.TL_inputStickerSetID r0 = new TLRPC.TL_inputStickerSetID();
        r0.access_hash = r19.access_hash;
        long r3 = r19.id;
        r0.id = r3;
        if (r14 == 0) goto L10;
        TLRPC.TL_messages_installStickerSet r11 = new TLRPC.TL_messages_installStickerSet();
        r11.stickerset = r0;
        if (r14 != 1) goto L7;
        boolean r142 = true;
    L8:
        r11.archived = r142;
        markSetInstalling(r19.id, true);
        getConnectionsManager().sendRequest(r11, new MediaDataController$$ExternalSyntheticLambda108(this, r19, r15, r17, r21, r22, r16, r13, r18, r20));
        return;
    L7:
        r142 = false;
        goto L8
    L10:
        markSetUninstalling(r3, true);
        TLRPC.TL_messages_uninstallStickerSet r143 = new TLRPC.TL_messages_uninstallStickerSet();
        r143.stickerset = r0;
        getConnectionsManager().sendRequest(r143, new MediaDataController$$ExternalSyntheticLambda109(this, r19, r21));
    }

    private void updateEmojiStatuses(int r3, TL_account.TL_emojiStatuses r4) {
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda150(this, r3, r4));
    }

    private void updateSearchResults() {
        ArrayList r0 = new ArrayList(this.searchResultMessages);
        this.searchResultMessages.clear();
        HashSet r1 = new HashSet();
        int r3 = 0;
    L3:
        MessageObject r5 = null;
        if (r3 >= this.searchServerResultMessages.size()) goto L25;
        MessageObject r4 = this.searchServerResultMessages.get(r3);
        if (ChatUtils.isTermsRestrictedMessage(r4) == true) goto L24;
        if (r4.hasValidGroupId() == false) goto L13;
        if (r4.isPrimaryGroupMessage == false) goto L24;
    L13:
        if (r1.contains(Integer.valueOf(r4.getId())) == true) goto L24;
        int r7 = 0;
    L16:
        if (r7 >= r0.size()) goto L21;
        if (((MessageObject) r0.get(r7)).getId() == r4.getId()) goto L19;
        r7 = r7 + 1;
        goto L16
    L19:
        r5 = (MessageObject) r0.get(r7);
    L21:
        if (r5 == null) goto L23;
        r4.copyStableParams(r5);
        r4.mediaExists = r5.mediaExists;
        r4.attachPathExists = r5.attachPathExists;
    L23:
        r4.isSavedFiltered = true;
        this.searchResultMessages.add(r4);
        r1.add(Integer.valueOf(r4.getId()));
    L24:
        r3 = r3 + 1;
        goto L3
    L25:
        int r32 = 0;
    L27:
        if (r32 >= this.searchLocalResultMessages.size()) goto L45;
        MessageObject r42 = this.searchLocalResultMessages.get(r32);
        if (ChatUtils.isTermsRestrictedMessage(r42) == true) goto L44;
        if (r1.contains(Integer.valueOf(r42.getId())) == true) goto L44;
        int r72 = 0;
    L35:
        if (r72 >= r0.size()) goto L40;
        if (((MessageObject) r0.get(r72)).getId() == r42.getId()) goto L38;
        r72 = r72 + 1;
        goto L35
    L38:
        MessageObject r73 = (MessageObject) r0.get(r72);
    L41:
        if (r73 == null) goto L43;
        r42.copyStableParams(r73);
        r42.mediaExists = r73.mediaExists;
        r42.attachPathExists = r73.attachPathExists;
    L43:
        r42.isSavedFiltered = true;
        this.searchResultMessages.add(r42);
        r1.add(Integer.valueOf(r42.getId()));
        goto L44
    L40:
        r73 = null;
    L44:
        r32 = r32 + 1;
        goto L27
    }

    private void verifyAnimatedStickerMessageInternal(TLRPC.Message r3, String r4) {
        ArrayList<TLRPC.Message> r0 = this.verifyingMessages.get(r4);
        if (r0 != null) goto L5;
        r0 = new ArrayList();
        this.verifyingMessages.put(r4, r0);
    L5:
        r0.add(r3);
        TLRPC.TL_messages_getStickerSet r02 = new TLRPC.TL_messages_getStickerSet();
        r02.stickerset = MessageObject.getInputStickerSet(r3);
        getConnectionsManager().sendRequest(r02, new MediaDataController$$ExternalSyntheticLambda5(this, r4));
    }

    public void addNewStickerSet(TLRPC.TL_messages_stickerSet r14) {
        if (this.stickerSetsById.indexOfKey(r14.set.id) < 0) goto L5;
        return;
    L5:
        if (this.stickerSetsByName.containsKey(r14.set.short_name) == true) goto L43;
        TLRPC.StickerSet r0 = r14.set;
        if (r0.masks == false) goto L11;
        int r02 = 1;
    L14:
        this.stickerSets[r02].add(0, r14);
        this.stickerSetsById.put(r14.set.id, r14);
        this.installedStickerSetsById.put(r14.set.id, r14);
        this.stickerSetsByName.put(r14.set.short_name, r14);
        LongSparseArray r1 = new LongSparseArray();
        int r4 = 0;
    L16:
        if (r4 >= r14.documents.size()) goto L18;
        TLRPC.Document r5 = r14.documents.get(r4);
        r1.put(r5.id, r5);
        r4 = r4 + 1;
        goto L16
    L18:
        int r42 = 0;
    L20:
        if (r42 >= r14.packs.size()) goto L35;
        TLRPC.TL_stickerPack r52 = r14.packs.get(r42);
        String r6 = r52.emoticon.replace("️", _UrlKt.FRAGMENT_ENCODE_SET);
        r52.emoticon = r6;
        ArrayList<TLRPC.Document> r62 = this.allStickers.get(r6);
        if (r62 != null) goto L24;
        r62 = new ArrayList();
        this.allStickers.put(r52.emoticon, r62);
    L24:
        int r7 = 0;
    L26:
        if (r7 >= r52.documents.size()) goto L34;
        Long r8 = r52.documents.get(r7);
        if (this.stickersByEmoji.indexOfKey(r8.longValue()) >= 0) goto L30;
        this.stickersByEmoji.put(r8.longValue(), r52.emoticon);
    L30:
        TLRPC.Document r82 = (TLRPC.Document) r1.get(r8.longValue());
        if (r82 == null) goto L33;
        r62.add(r82);
    L33:
        r7 = r7 + 1;
        goto L26
    L34:
        r42 = r42 + 1;
        goto L20
    L35:
        this.loadHash[r02] = calcStickersHash(this.stickerSets[r02]);
        getNotificationCenter().postNotificationName(NotificationCenter.stickersDidLoad, new Object[]{Integer.valueOf(r02), Boolean.TRUE});
        loadStickers(r02, false, true);
        return;
    L11:
        if (r0.emojis == false) goto L13;
        r02 = 5;
        goto L14
    L13:
        r02 = 0;
        goto L14
    }

    public void addRecentGif(TLRPC.Document r8, int r9, boolean r10) {
        if (r8 != null) goto L4;
        return;
    L4:
        int r1 = 0;
    L5:
        int r2 = this.recentGifs.size();
        ArrayList<TLRPC.Document> r3 = this.recentGifs;
        if (r1 >= r2) goto L11;
        TLRPC.Document r22 = r3.get(r1);
        if (r22.id == r8.id) goto L9;
        r1 = r1 + 1;
        goto L5
    L9:
        this.recentGifs.remove(r1);
        this.recentGifs.add(0, r22);
    L13:
        if (this.recentGifs.size() <= getMessagesController().savedGifsLimitDefault) goto L17;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L17;
    L18:
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda32(this, this.recentGifs.remove(r0.size() - 1)));
        if (r10 == false) goto L21;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda33(r8));
    L21:
        ArrayList<TLRPC.Document> r23 = new ArrayList();
        r23.add(r8);
        processLoadedRecentDocuments(0, r23, true, r9, false);
        return;
    L17:
        if (this.recentGifs.size() <= getMessagesController().savedGifsLimitPremium) goto L21;
    L11:
        r3.add(0, r8);
        goto L13
    }

    public void addRecentSticker(int r11, Object r12, TLRPC.Document r13, int r14, boolean r15) {
        if (r11 != 3) goto L5;
        return;
    L5:
        if (MessageObject.isStickerDocument(r13) == false) goto L7;
    L9:
        int r3 = 0;
    L11:
        if (r3 >= this.recentStickers[r11].size()) goto L18;
        TLRPC.Document r5 = this.recentStickers[r11].get(r3);
        if (r5.id == r13.id) goto L14;
        r3 = r3 + 1;
        goto L11
    L14:
        this.recentStickers[r11].remove(r3);
        if (r15 == true) goto L21;
        this.recentStickers[r11].add(0, r5);
    L21:
        if (r11 != 2) goto L37;
        if (r15 == false) goto L25;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{0, r13, 4});
    L33:
        TLRPC.TL_messages_faveSticker r1 = new TLRPC.TL_messages_faveSticker();
        TLRPC.TL_inputDocument r2 = new TLRPC.TL_inputDocument();
        r1.id = r2;
        r2.id = r13.id;
        r2.access_hash = r13.access_hash;
        byte[] r52 = r13.file_reference;
        r2.file_reference = r52;
        if (r52 != null) goto L36;
        r2.file_reference = new byte[0];
    L36:
        r1.unfave = r15;
        getConnectionsManager().sendRequest(r1, new MediaDataController$$ExternalSyntheticLambda143(this, r12, r1));
        int r122 = getMessagesController().maxFaveStickersCount;
    L45:
        if (this.recentStickers[r11].size() > r122) goto L47;
        if (r15 == true) goto L47;
    L51:
        if (r15 == true) goto L53;
        ArrayList<TLRPC.Document> r6 = new ArrayList();
        r6.add(r13);
        MediaDataController r4 = this;
        int r53 = r11;
        r4.processLoadedRecentDocuments(r53, r6, false, r14, false);
    L54:
        if (r53 == 2) goto L57;
        if (r53 != 0) goto L62;
        if (r15 == true) goto L57;
        return;
    L62:
        return;
    L57:
        r4.getNotificationCenter().postNotificationName(NotificationCenter.recentDocumentsDidLoad, new Object[]{Boolean.FALSE, Integer.valueOf(r53)});
        return;
    L53:
        r4 = this;
        r53 = r11;
    L47:
        if (r15 == false) goto L49;
        TLRPC.Document r123 = r13;
    L50:
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda145(this, r11, r123));
        goto L51
    L49:
        ArrayList<TLRPC.Document> r124 = this.recentStickers[r11];
        r123 = r124.remove(r124.size() - 1);
        goto L50
    L25:
        if (this.recentStickers[r11].size() <= getMessagesController().maxFaveStickersCount) goto L27;
        boolean r22 = true;
    L28:
        NotificationCenter r54 = NotificationCenter.getGlobalInstance();
        int r62 = NotificationCenter.showBulletin;
        if (r22 == false) goto L31;
        int r23 = 6;
    L32:
        r54.postNotificationName(r62, new Object[]{0, r13, Integer.valueOf(r23)});
        goto L33
    L31:
        r23 = 5;
        goto L32
    L27:
        r22 = false;
        goto L28
    L37:
        if (r11 != 0) goto L43;
        if (r15 == false) goto L43;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{0, r13, 3});
        TLRPC.TL_messages_saveRecentSticker r16 = new TLRPC.TL_messages_saveRecentSticker();
        TLRPC.TL_inputDocument r24 = new TLRPC.TL_inputDocument();
        r16.id = r24;
        r24.id = r13.id;
        r24.access_hash = r13.access_hash;
        byte[] r55 = r13.file_reference;
        r24.file_reference = r55;
        if (r55 != null) goto L42;
        r24.file_reference = new byte[0];
    L42:
        r16.unsave = true;
        getConnectionsManager().sendRequest(r16, new MediaDataController$$ExternalSyntheticLambda144(this, r12, r16));
    L43:
        r122 = getMessagesController().maxRecentStickersCount;
        goto L45
    L18:
        if (r15 == true) goto L21;
        this.recentStickers[r11].add(0, r13);
        goto L21
    L7:
        if (MessageObject.isAnimatedStickerDocument(r13, true) == true) goto L9;
    }

    public void addStyle(int r2, int r3, int r4, ArrayList<TLRPC.MessageEntity> r5) {
        if ((r2 & 256) == 0) goto L6;
        r5.add(setEntityStartEnd(new TLRPC.TL_messageEntitySpoiler(), r3, r4));
    L6:
        if ((r2 & 1) == 0) goto L9;
        r5.add(setEntityStartEnd(new TLRPC.TL_messageEntityBold(), r3, r4));
    L9:
        if ((r2 & 2) == 0) goto L12;
        r5.add(setEntityStartEnd(new TLRPC.TL_messageEntityItalic(), r3, r4));
    L12:
        if ((r2 & 4) == 0) goto L15;
        r5.add(setEntityStartEnd(new TLRPC.TL_messageEntityCode(), r3, r4));
    L15:
        if ((r2 & 8) == 0) goto L18;
        r5.add(setEntityStartEnd(new TLRPC.TL_messageEntityStrike(), r3, r4));
    L18:
        if ((r2 & 16) == 0) goto L21;
        r5.add(setEntityStartEnd(new TLRPC.TL_messageEntityUnderline(), r3, r4));
        return;
    }

    public void applyAttachMenuBot(TLRPC.TL_attachMenuBotsBot r2) {
        this.attachMenuBots.bots.add(r2.bot);
        loadAttachMenuBots(false, true);
    }

    public boolean areAllTrendingStickerSetsUnread(boolean r8) {
        int r0 = this.featuredStickerSets[r8 ? 1 : 0].size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L15;
        TLRPC.StickerSetCovered r3 = this.featuredStickerSets[r8 ? 1 : 0].get(r2);
        if (isStickerPackInstalled(r3.set.id) == true) goto L14;
        if (r3.covers.isEmpty() == false) goto L12;
        if (r3.cover == null) goto L14;
    L12:
        if (this.unreadStickerSets[r8 ? 1 : 0].contains(Long.valueOf(r3.set.id)) == true) goto L14;
        return false;
    L14:
        r2 = r2 + 1;
        goto L3
    L15:
        return true;
    }

    public boolean areStickersLoaded(int r1) {
        return this.stickersLoaded[r1];
    }

    public void beginTransaction() {
        this.inTransaction = true;
    }

    public boolean botInAttachMenu(long r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.attachMenuBots.bots.size()) goto L10;
        if (this.attachMenuBots.bots.get(r1).bot_id == r5) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    public void buildShortcuts() {
        int r0 = ShortcutManagerCompat.getMaxShortcutCountPerActivity(ApplicationLoader.applicationContext) - 2;
        if (r0 > 0) goto L5;
        r0 = 5;
    L5:
        ArrayList r1 = new ArrayList();
        boolean r3 = false;
        if (SharedConfig.passcodeHash.length() > 0) goto L15;
        int r2 = 0;
    L9:
        if (r2 >= this.hints.size()) goto L15;
        r1.add(this.hints.get(r2));
        if (r1.size() == (r0 - 2)) goto L15;
        r2 = r2 + 1;
    L15:
        if (Build.VERSION.SDK_INT < 30) goto L17;
        r3 = true;
    L17:
        Utilities.globalQueue.postRunnable(new MediaDataController$$ExternalSyntheticLambda81(this, r3, r1));
    }

    public void calcNewHash(int r4) {
        this.loadHash[r4] = calcStickersHash(this.stickerSets[r4]);
    }

    public boolean canAddStickerToFavorites() {
        if (this.stickersLoaded[0] == true) goto L5;
        return true;
    L5:
        if (this.stickerSets[0].size() < 5) goto L7;
        return true;
    L7:
        if (this.recentStickers[2].isEmpty() == false) goto L13;
        return false;
    L13:
        return true;
    }

    public boolean canCreateAttachedMenuBotShortcut(long r1) {
        return true;
    }

    public boolean cancelRemovingStickerSet(long r1) {
        Runnable r0 = this.removingStickerSetsUndos.get(r1);
        if (r0 == null) goto L6;
        r0.run();
        return true;
    L6:
        return false;
    }

    public void cancelSearchStickers(SearchStickersKey r2) {
        if (r2 == null) goto L12;
        Integer r22 = this.loadingSearchStickersKeys.remove(r2);
        if (r22 != null) goto L7;
        return;
    L7:
        if (r22.intValue() == 0) goto L11;
        getConnectionsManager().cancelRequest(r22.intValue(), true);
        return;
    L11:
        return;
    }

    public void checkAllMedia(boolean r3) {
        if (r3 == false) goto L5;
        this.reactionsUpdateDate = 0;
        int[] r32 = this.loadFeaturedDate;
        r32[0] = 0;
        r32[1] = 0;
    L5:
        loadRecents(2, false, true, false);
        loadRecents(3, false, true, false);
        loadRecents(7, false, false, true);
        checkFeaturedStickers();
        checkFeaturedEmoji();
        checkReactions();
        checkMenuBots(true);
        checkPremiumPromo();
        checkPremiumGiftStickers();
        checkTonGiftStickers();
        checkGenericAnimations();
        getMessagesController().getAvailableEffects();
    }

    public void checkDefaultTopicIcons() {
        if (getUserConfig().defaultTopicIcons == null) goto L10;
        String r0 = getUserConfig().defaultTopicIcons;
        TLRPC.TL_messages_stickerSet r2 = getStickerSetByName(r0);
        if (r2 != null) goto L7;
        r2 = getStickerSetByEmojiOrName(r0);
    L7:
        if (r2 != null) goto L10;
        getInstance(this.currentAccount).loadStickersByEmojiOrName(r0, false, true);
    L10:
        if (this.loadingDefaultTopicIcons == false) goto L12;
        return;
    L12:
        if ((System.currentTimeMillis() - getUserConfig().lastUpdatedDefaultTopicIcons) < DurationKt.MILLIS_IN_DAY) goto L17;
        this.loadingDefaultTopicIcons = true;
        TLRPC.TL_messages_getStickerSet r02 = new TLRPC.TL_messages_getStickerSet();
        r02.stickerset = new TLRPC.TL_inputStickerSetEmojiDefaultTopicIcons();
        getConnectionsManager().sendRequest(r02, new MediaDataController$$ExternalSyntheticLambda59(this));
        return;
    }

    public void checkFeaturedEmoji() {
        if (this.loadingFeaturedStickers[1] == false) goto L5;
        return;
    L5:
        if (this.featuredStickersLoaded[1] == true) goto L7;
    L8:
        loadFeaturedStickers(true, true);
        return;
    L7:
        if (Math.abs((System.currentTimeMillis() / 1000) - this.loadFeaturedDate[1]) >= 3600) goto L8;
    }

    public void checkFeaturedStickers() {
        if (this.loadingFeaturedStickers[0] == false) goto L5;
        return;
    L5:
        if (this.featuredStickersLoaded[0] == true) goto L7;
    L8:
        loadFeaturedStickers(false, true);
        return;
    L7:
        if (Math.abs((System.currentTimeMillis() / 1000) - this.loadFeaturedDate[0]) >= 3600) goto L8;
    }

    public void checkGenericAnimations() {
        if (getUserConfig().genericAnimationsStickerPack == null) goto L10;
        String r0 = getUserConfig().genericAnimationsStickerPack;
        TLRPC.TL_messages_stickerSet r2 = getStickerSetByName(r0);
        if (r2 != null) goto L7;
        r2 = getStickerSetByEmojiOrName(r0);
    L7:
        if (r2 != null) goto L10;
        getInstance(this.currentAccount).loadStickersByEmojiOrName(r0, false, true);
    L10:
        if (this.loadingGenericAnimations == false) goto L12;
        return;
    L12:
        if ((System.currentTimeMillis() - getUserConfig().lastUpdatedGenericAnimations) < DurationKt.MILLIS_IN_DAY) goto L17;
        this.loadingGenericAnimations = true;
        TLRPC.TL_messages_getStickerSet r02 = new TLRPC.TL_messages_getStickerSet();
        r02.stickerset = new TLRPC.TL_inputStickerSetEmojiGenericAnimations();
        getConnectionsManager().sendRequest(r02, new MediaDataController$$ExternalSyntheticLambda131(this));
        return;
    }

    public void checkMenuBots(boolean r5) {
        if (this.isLoadingMenuBots == true) goto L11;
        if (r5 == false) goto L8;
        if (this.menuBotsUpdatedLocal == true) goto L8;
    L9:
        loadAttachMenuBots(true, false);
        return;
    L8:
        if (Math.abs((System.currentTimeMillis() / 1000) - this.menuBotsUpdateDate) >= 3600) goto L9;
        return;
    }

    public void checkPremiumGiftStickers() {
        if (getUserConfig().premiumGiftsStickerPack == null) goto L10;
        String r0 = getUserConfig().premiumGiftsStickerPack;
        TLRPC.TL_messages_stickerSet r2 = getStickerSetByName(r0);
        if (r2 != null) goto L7;
        r2 = getStickerSetByEmojiOrName(r0);
    L7:
        if (r2 != null) goto L10;
        getInstance(this.currentAccount).loadStickersByEmojiOrName(r0, false, true);
    L10:
        if (this.loadingPremiumGiftStickers == false) goto L12;
        return;
    L12:
        if ((System.currentTimeMillis() - getUserConfig().lastUpdatedPremiumGiftsStickerPack) < DurationKt.MILLIS_IN_DAY) goto L17;
        this.loadingPremiumGiftStickers = true;
        TLRPC.TL_messages_getStickerSet r02 = new TLRPC.TL_messages_getStickerSet();
        r02.stickerset = new TLRPC.TL_inputStickerSetPremiumGifts();
        getConnectionsManager().sendRequest(r02, new MediaDataController$$ExternalSyntheticLambda30(this));
        return;
    }

    public void checkPremiumPromo() {
        if (this.isLoadingPremiumPromo == false) goto L5;
        return;
    L5:
        if (this.premiumPromo != null) goto L7;
    L8:
        loadPremiumPromo(true);
        return;
    L7:
        if (Math.abs((System.currentTimeMillis() / 1000) - this.premiumPromoUpdateDate) >= 3600) goto L8;
    }

    public void checkReactions() {
        if (this.isLoadingReactions == false) goto L5;
        return;
    L5:
        if (Math.abs((System.currentTimeMillis() / 1000) - this.reactionsUpdateDate) < 3600) goto L9;
        loadReactions(true, null);
        return;
    }

    public void checkRingtones(boolean r1) {
        this.ringtoneDataStore.loadUserRingtones(r1);
    }

    public void checkStickers(int r5) {
        if (this.loadingStickers[r5] == false) goto L5;
        return;
    L5:
        if (this.stickersLoaded[r5] == true) goto L7;
    L8:
        loadStickers(r5, true, false);
        return;
    L7:
        if (Math.abs((System.currentTimeMillis() / 1000) - this.loadDate[r5]) >= 3600) goto L8;
    }

    public void checkTonGiftStickers() {
        if (getUserConfig().premiumTonStickerPack == null) goto L10;
        String r0 = getUserConfig().premiumTonStickerPack;
        TLRPC.TL_messages_stickerSet r2 = getStickerSetByName(r0);
        if (r2 != null) goto L7;
        r2 = getStickerSetByEmojiOrName(r0);
    L7:
        if (r2 != null) goto L10;
        getInstance(this.currentAccount).loadStickersByEmojiOrName(r0, false, true);
    L10:
        if (this.loadingPremiumTonStickers == false) goto L12;
        return;
    L12:
        if ((System.currentTimeMillis() - getUserConfig().lastUpdatedTonGiftsStickerPack) < DurationKt.MILLIS_IN_DAY) goto L17;
        this.loadingPremiumTonStickers = true;
        TLRPC.TL_messages_getStickerSet r02 = new TLRPC.TL_messages_getStickerSet();
        r02.stickerset = new TLRPC.TL_inputStickerSetTonGifts();
        getConnectionsManager().sendRequest(r02, new MediaDataController$$ExternalSyntheticLambda86(this));
        return;
    }

    public void cleanDraft(long r15, long r17, boolean r19) {
        LongSparseArray<TLRPC.DraftMessage> r0 = this.drafts.get(r15);
        if (r0 == null) goto L5;
        TLRPC.DraftMessage r02 = r0.get(r17);
    L6:
        if (r02 != null) goto L9;
        return;
    L9:
        if (r19 == true) goto L26;
        LongSparseArray<TLRPC.DraftMessage> r03 = this.drafts.get(r15);
        if (r03 == null) goto L15;
        r03.remove(r17);
        if (r03.size() != 0) goto L15;
        this.drafts.remove(r15);
    L15:
        LongSparseArray<TLRPC.Message> r04 = this.draftMessages.get(r15);
        if (r04 == null) goto L20;
        r04.remove(r17);
        if (r04.size() != 0) goto L20;
        this.draftMessages.remove(r15);
    L20:
        SharedPreferences r7 = this.draftPreferences;
        if (r17 != 0) goto L24;
        r7.edit().remove(_UrlKt.FRAGMENT_ENCODE_SET + r15).remove("r_" + r15).apply();
        getMessagesController().sortDialogs(null);
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        return;
    L24:
        r7.edit().remove("t_" + r15 + "_" + r17).remove("rt_" + r15 + "_" + r17).apply();
        return;
    L26:
        TLRPC.InputReplyTo r5 = r02.reply_to;
        if (r5 != null) goto L29;
    L32:
        if (r5 == null) goto L34;
        r5.reply_to_msg_id = 0;
    L34:
        r02.flags &= -2;
        saveDraft(r15, r17, r02.message, r02.entities, null, null, null, 0, r02.no_webpage, true);
        return;
    L29:
        if (r5.reply_to_msg_id != 0) goto L32;
        return;
    L5:
        r02 = null;
        goto L6
    }

    public void cleanup() {
        int r1 = 0;
    L3:
        ArrayList<TLRPC.Document>[] r2 = this.recentStickers;
        if (r1 >= r2.length) goto L9;
        ArrayList<TLRPC.Document> r22 = r2[r1];
        if (r22 == null) goto L8;
        r22.clear();
    L8:
        this.loadingRecentStickers[r1] = false;
        this.recentStickersLoaded[r1] = false;
        r1 = r1 + 1;
        goto L3
    L9:
        int r12 = 0;
    L11:
        if (r12 >= 4) goto L13;
        this.loadHash[r12] = 0;
        this.loadDate[r12] = 0;
        this.stickerSets[r12].clear();
        this.loadingStickers[r12] = false;
        this.stickersLoaded[r12] = false;
        r12 = r12 + 1;
        goto L11
    L13:
        this.loadingPinnedMessages.clear();
        int[] r13 = this.loadFeaturedDate;
        r13[0] = 0;
        long[] r23 = this.loadFeaturedHash;
        r23[0] = 0;
        r13[1] = 0;
        r23[1] = 0;
        this.allStickers.clear();
        this.allStickersFeatured.clear();
        this.stickersByEmoji.clear();
        this.featuredStickerSetsById[0].clear();
        this.featuredStickerSets[0].clear();
        this.featuredStickerSetsById[1].clear();
        this.featuredStickerSets[1].clear();
        this.unreadStickerSets[0].clear();
        this.unreadStickerSets[1].clear();
        this.recentGifs.clear();
        this.stickerSetsById.clear();
        this.installedStickerSetsById.clear();
        this.stickerSetsByName.clear();
        this.diceStickerSetsByEmoji.clear();
        this.diceEmojiStickerSetsById.clear();
        this.loadingDiceStickerSets.clear();
        boolean[] r14 = this.loadingFeaturedStickers;
        r14[0] = false;
        boolean[] r24 = this.featuredStickersLoaded;
        r24[0] = false;
        r14[1] = false;
        r24[1] = false;
        this.loadingRecentGifs = false;
        this.recentGifsLoaded = false;
        this.currentFetchingEmoji.clear();
        if (Build.VERSION.SDK_INT < 25) goto L16;
        Utilities.globalQueue.postRunnable(new MediaDataController$$ExternalSyntheticLambda249());
    L16:
        this.verifyingMessages.clear();
        this.loading = false;
        this.loaded = false;
        this.hints.clear();
        this.inlineBots.clear();
        this.guestBots.clear();
        this.webapps.clear();
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda250(this));
        this.drafts.clear();
        this.draftMessages.clear();
        this.draftPreferences.edit().clear().apply();
        this.botInfos.clear();
        this.botKeyboards.clear();
        this.botKeyboardsByMids.clear();
    }

    public void clearAllDrafts(boolean r2) {
        this.drafts.clear();
        this.draftMessages.clear();
        this.draftsFolderIds.clear();
        this.draftPreferences.edit().clear().apply();
        if (r2 == false) goto L6;
        getMessagesController().sortDialogs(null);
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        return;
    }

    public void clearBotKeyboard(long r2) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda53(this, r2));
    }

    public void clearBotKeyboard(MessagesStorage.TopicKey r2, ArrayList<Integer> r3) {
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda251(this, r3, r2));
    }

    public void clearDraftsFolderIds() {
        this.draftsFolderIds.clear();
    }

    public void clearFoundMessageObjects() {
        this.searchResultMessages.clear();
        this.searchServerResultMessages.clear();
        this.searchLocalResultMessages.clear();
        int[] r2 = this.filteredMessagesSearchCount;
        r2[1] = 0;
        r2[0] = 0;
    }

    public ArrayList<TLRPC.EmojiStatus> clearRecentEmojiStatuses() {
        ArrayList<TLRPC.EmojiStatus> r0 = this.emojiStatuses[0];
        if (r0 == null) goto L5;
        r0.clear();
    L5:
        this.emojiStatusesHash[0] = 0;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda166(this));
        return this.emojiStatuses[0];
    }

    public void clearRecentReactions() {
        this.recentReactions.clear();
        ApplicationLoader.applicationContext.getSharedPreferences("recent_reactions_" + this.currentAccount, 0).edit().clear().apply();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TLRPC.TL_messages_clearRecentReactions(), new AnonymousClass4(this));
    }

    public void clearRecentStickers() {
        TLRPC.TL_messages_clearRecentStickers r0 = new TLRPC.TL_messages_clearRecentStickers();
        getConnectionsManager().sendRequest(r0, new MediaDataController$$ExternalSyntheticLambda186(this));
    }

    public void clearTopPeers() {
        this.hints.clear();
        this.inlineBots.clear();
        this.guestBots.clear();
        this.webapps.clear();
        getNotificationCenter().postNotificationName(NotificationCenter.reloadHints, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadInlineHints, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadGuestBotHints, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadWebappsHints, new Object[0]);
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda61(this));
        buildShortcuts();
    }

    public boolean containsTopPeer(long r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.hints.size()) goto L10;
        if (DialogObject.getPeerDialogId(this.hints.get(r1).peer) == r5) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    public void endTransaction() {
        this.inTransaction = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void fetchEmojiStatuses(int r4, boolean r5) {
        boolean[] r0 = this.emojiStatusesFetching;
        if (r0[r4] == false) goto L5;
        return;
    L5:
        r0[r4] = true;
        if (r5 == false) goto L9;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda13(this, r4));
        return;
    L9:
        if (r4 != 0) goto L11;
        TL_account.getRecentEmojiStatuses r52 = new TL_account.getRecentEmojiStatuses();
        r52.hash = this.emojiStatusesHash[r4];
        TL_account.getChannelDefaultEmojiStatuses r53 = r52;
    L14:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r53, new MediaDataController$$ExternalSyntheticLambda14(this, r4));
        return;
    L11:
        if (r4 != 1) goto L13;
        TL_account.getDefaultEmojiStatuses r54 = new TL_account.getDefaultEmojiStatuses();
        r54.hash = this.emojiStatusesHash[r4];
        r53 = r54;
        goto L14
    L13:
        TL_account.getChannelDefaultEmojiStatuses r55 = new TL_account.getChannelDefaultEmojiStatuses();
        r55.hash = this.emojiStatusesHash[r4];
        r53 = r55;
        goto L14
    }

    public void fetchNewEmojiKeywords(String[] r2) {
        fetchNewEmojiKeywords(r2, false);
    }

    public void fetchNewEmojiKeywords(String[] r5, boolean r6) {
        if (r5 == null) goto L17;
        int r0 = 0;
    L6:
        if (r0 >= r5.length) goto L24;
        String r1 = r5[r0];
        if (TextUtils.isEmpty(r1) == true) goto L25;
        if (this.currentFetchingEmoji.get(r1) != null) goto L26;
        if (r6 == false) goto L16;
        if (this.fetchedEmoji.contains(r1) == false) goto L16;
        return;
    L16:
        this.currentFetchingEmoji.put(r1, Boolean.TRUE);
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda0(this, r1));
        r0 = r0 + 1;
        goto L6
    L26:
        return;
    L25:
        return;
    L24:
        return;
    }

    public void fillWithAnimatedEmoji(ArrayList<KeywordResult> r9, Integer r10, boolean r11, boolean r12, boolean r13, Runnable r14) {
        if (r9 == null) goto L16;
        if (r9.isEmpty() == true) goto L16;
        ArrayList[] r6 = {getStickerSets(5)};
        MediaDataController$$ExternalSyntheticLambda177 r0 = new MediaDataController$$ExternalSyntheticLambda177(this, r10, r9, r12, r11, r6, r14);
        ArrayList r8 = r6[0];
        if (r8 == null) goto L11;
        if (r8.isEmpty() == true) goto L11;
    L14:
        r0.run();
        return;
    L11:
        if (this.triedLoadingEmojipacks == true) goto L14;
        this.triedLoadingEmojipacks = true;
        boolean[] r82 = new boolean[1];
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda178(this, r82, r6, r0));
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda179(r82, r0), 900);
        return;
    L16:
        if (r14 == null) goto L19;
        r14.run();
        return;
    }

    public TLRPC.TL_attachMenuBot findBotInAttachMenu(long r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.attachMenuBots.bots.size()) goto L10;
        if (this.attachMenuBots.bots.get(r0).bot_id == r4) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return this.attachMenuBots.bots.get(r0);
    L10:
        return null;
    }

    public void findStickerSetByNameInCache(String r3, Utilities.Callback<TLRPC.TL_messages_stickerSet> r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r3 != null) goto L7;
        r4.run(null);
        return;
    L7:
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda253(this, r3, r4));
    }

    public void generateEmojiPreviewThemes(ArrayList<TLRPC.TL_theme> r7, int r8) {
        int r2 = 0;
        SharedPreferences.Editor r0 = ApplicationLoader.applicationContext.getSharedPreferences("emojithemes_config_" + r8, 0).edit();
        r0.putInt(NotificationBadge.NewHtcHomeBadger.COUNT, r7.size());
        int r1 = 0;
    L4:
        if (r1 >= r7.size()) goto L6;
        TLRPC.TL_theme r3 = r7.get(r1);
        SerializedData r4 = new SerializedData(r3.getObjectSize());
        r3.serializeToStream(r4);
        r0.putString("theme_" + r1, Utilities.bytesToHex(r4.toByteArray()));
        r1 = r1 + 1;
        goto L4
    L6:
        r0.apply();
        if (r7.isEmpty() == true) goto L17;
        ArrayList r02 = new ArrayList();
        r02.add(new ChatThemeBottomSheet.ChatThemeItem(EmojiThemes.createHomePreviewTheme(r8)));
    L10:
        if (r2 >= r7.size()) goto L15;
        EmojiThemes r12 = EmojiThemes.createPreviewFullTheme(r8, r7.get(r2));
        ChatThemeBottomSheet.ChatThemeItem r32 = new ChatThemeBottomSheet.ChatThemeItem(r12);
        if (r12.items.size() < 4) goto L14;
        r02.add(r32);
    L14:
        r2 = r2 + 1;
        goto L10
    L15:
        ChatThemeController.chatThemeQueue.postRunnable(new AnonymousClass3(this, r02, r8));
        return;
    L17:
        this.defaultEmojiThemes.clear();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.emojiPreviewThemesChanged, new Object[0]);
    }

    public HashMap<String, ArrayList<TLRPC.Document>> getAllStickers() {
        return this.allStickers;
    }

    public HashMap<String, ArrayList<TLRPC.Document>> getAllStickersFeatured() {
        return this.allStickersFeatured;
    }

    public void getAnimatedEmojiByKeywords(String r4, Utilities.Callback<ArrayList<Long>> r5) {
        if (r4 != null) goto L6;
        if (r5 == null) goto L8;
        r5.run(new ArrayList());
        return;
    L8:
        return;
    L6:
        ArrayList<TLRPC.TL_messages_stickerSet> r0 = getStickerSets(5);
        ArrayList<TLRPC.StickerSetCovered> r3 = getFeaturedEmojiSets();
        Utilities.searchQueue.postRunnable(new MediaDataController$$ExternalSyntheticLambda135(r4, r0, r3, r5));
    }

    public int getArchivedStickersCount(int r1) {
        return this.archivedStickersCount[r1];
    }

    public TLRPC.TL_attachMenuBots getAttachMenuBots() {
        return this.attachMenuBots;
    }

    public TL_bots.BotInfo getBotInfoCached(long r2, long r4) {
        return this.botInfos.get(r2 + "_" + r4);
    }

    public ArrayList<TLRPC.EmojiStatus> getDefaultChannelEmojiStatuses() {
        if (this.emojiStatusesFromCacheFetched[2] == true) goto L6;
        fetchEmojiStatuses(2, true);
    L13:
        return this.emojiStatuses[2];
    L6:
        if (this.emojiStatuses[2] != null) goto L8;
    L11:
        fetchEmojiStatuses(2, false);
        goto L13
    L8:
        if (this.emojiStatusesFetchDate[2] == null) goto L13;
        if (((System.currentTimeMillis() / 1000) - this.emojiStatusesFetchDate[2].longValue()) <= 1800) goto L13;
        goto L11
    }

    public ArrayList<TLRPC.EmojiStatus> getDefaultEmojiStatuses() {
        if (this.emojiStatusesFromCacheFetched[1] == true) goto L6;
        fetchEmojiStatuses(1, true);
    L13:
        return this.emojiStatuses[1];
    L6:
        if (this.emojiStatuses[1] != null) goto L8;
    L11:
        fetchEmojiStatuses(1, false);
        goto L13
    L8:
        if (this.emojiStatusesFetchDate[1] == null) goto L13;
        if (((System.currentTimeMillis() / 1000) - this.emojiStatusesFetchDate[1].longValue()) <= 1800) goto L13;
        goto L11
    }

    public String getDoubleTapReaction() {
        String r0 = this.doubleTapReaction;
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (getReactionsList().isEmpty() == true) goto L17;
        String r02 = MessagesController.getEmojiSettings(this.currentAccount).getString("reaction_on_double_tap", null);
        if (r02 == null) goto L16;
        if (getReactionsMap().get(r02) == null) goto L12;
    L13:
        this.doubleTapReaction = r02;
        return r02;
    L12:
        if (r02.startsWith("animated_") == true) goto L13;
    L16:
        return getReactionsList().get(0).reaction;
    L17:
        return null;
    }

    public TLRPC.DraftMessage getDraft(long r1, long r3) {
        LongSparseArray<TLRPC.DraftMessage> r0 = this.drafts.get(r1);
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.get(r3);
    }

    public int getDraftFolderId(long r2) {
        return this.draftsFolderIds.get(r2, 0).intValue();
    }

    public TLRPC.Message getDraftMessage(long r1, long r3) {
        LongSparseArray<TLRPC.Message> r0 = this.draftMessages.get(r1);
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.get(r3);
    }

    public DraftVoice getDraftVoice(long r1, long r3) {
        loadDraftVoiceMessages();
        return this.draftVoices.get(Objects.hash(new Object[]{Long.valueOf(r1), Long.valueOf(r3)}));
    }

    public LongSparseArray<LongSparseArray<TLRPC.DraftMessage>> getDrafts() {
        return this.drafts;
    }

    public TLRPC.Document getEmojiAnimatedSticker(CharSequence r12) {
        if (r12 != null) goto L5;
        return null;
    L5:
        String r122 = r12.toString().replace("️", _UrlKt.FRAGMENT_ENCODE_SET);
        ArrayList<TLRPC.TL_messages_stickerSet> r2 = getStickerSets(4);
        int r3 = r2.size();
        int r5 = 0;
    L6:
        if (r5 >= r3) goto L17;
        TLRPC.TL_messages_stickerSet r6 = r2.get(r5);
        int r7 = r6.packs.size();
        int r8 = 0;
    L8:
        if (r8 >= r7) goto L16;
        TLRPC.TL_stickerPack r9 = r6.packs.get(r8);
        if (r9.documents.isEmpty() == true) goto L15;
        if (TextUtils.equals(r9.emoticon, r122) == false) goto L15;
        return getStickerByIds(4).get(r9.documents.get(0).longValue());
    L15:
        r8 = r8 + 1;
        goto L8
    L16:
        r5 = r5 + 1;
        goto L6
    L17:
        return null;
    }

    public String getEmojiForSticker(long r1) {
        String r0 = this.stickersByEmoji.get(r1);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public void getEmojiNames(String[] r3, String r4, Utilities.Callback<ArrayList<String>> r5) {
        if (r5 == null) goto L7;
        if (r4 == null) goto L8;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda169(this, r3, r4, r5));
        return;
    L8:
        return;
    }

    public void getEmojiSuggestions(String[] r11, String r12, boolean r13, KeywordResultCallback r14, CountDownLatch r15, boolean r16) {
        getEmojiSuggestions(r11, r12, r13, r14, r15, r16, false, false, null);
    }

    public void getEmojiSuggestions(String[] r13, String r14, boolean r15, KeywordResultCallback r16, CountDownLatch r17, boolean r18, boolean r19, boolean r20, Integer r21) {
        getEmojiSuggestions(r13, r14, r15, r16, r17, r18, r19, r20, false, r21, false);
    }

    public void getEmojiSuggestions(String[] r16, String r17, boolean r18, KeywordResultCallback r19, CountDownLatch r20, boolean r21, boolean r22, boolean r23, boolean r24, Integer r25, boolean r26) {
        if (r19 != null) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r17) == true) goto L12;
        if (r16 == null) goto L12;
        ArrayList r7 = new ArrayList(Emoji.recentEmoji);
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda218(this, r16, r19, r23, r17, r18, r7, r21, r25, r22, r24, r26, r20));
        if (r20 == null) goto L17;
        r20.await();     // Catch: Throwable -> L14
        return;
    L19:
        return;
    L17:
        return;
    L12:
        r19.run(new ArrayList(), null);
    }

    public void getEmojiSuggestions(String[] r11, String r12, boolean r13, KeywordResultCallback r14, boolean r15) {
        getEmojiSuggestions(r11, r12, r13, r14, null, r15, false, false, null);
    }

    public List<TLRPC.TL_availableReaction> getEnabledReactionsList() {
        return this.enabledReactionsList;
    }

    public ArrayList<TLRPC.MessageEntity> getEntities(CharSequence[] r2, boolean r3) {
        return getEntities(r2, r3, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r15v16 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r15v8, types: [int] */
    /* JADX WARN: Type inference failed for: r22v0, types: [org.telegram.messenger.BaseController, org.telegram.messenger.MediaDataController] */
    /* JADX WARN: Type inference failed for: r3v16, types: [android.text.Spanned] */
    public ArrayList<TLRPC.MessageEntity> getEntities(CharSequence[] r23, boolean r24, boolean r25) {
        ArrayList<TLRPC.MessageEntity> r0 = null;
        if (r23 == null) goto L302;
        char r2 = 0;
        if (r23[0] == null) goto L302;
        boolean r3 = ExteraConfig.getDisableMarkdown();
        if (r25 == false) goto L11;
        if (r3 == true) goto L11;
        boolean r5 = true;
    L12:
        int r6 = -1;
        char r7 = 0;
        int r8 = 0;
        int r9 = -1;
    L14:
        if (r5 == false) goto L115;
        CharSequence r14 = r23[r2];
        if (r7 != 0) goto L18;
        String r15 = "`";
    L19:
        int r82 = TextUtils.indexOf(r14, r15, r8);
        if (r82 == r6) goto L115;
        if (r9 == r6) goto L24;
        if (r0 != null) goto L37;
        r0 = new ArrayList();
    L37:
        if (r7 == 0) goto L39;
        int r16 = 3;
    L40:
        int r62 = r82 + r16;
    L42:
        if (r62 >= r23[r2].length()) goto L46;
        if (r23[r2].charAt(r62) != '`') goto L46;
        r82 = r82 + 1;
        r62 = r62 + 1;
    L46:
        if (r7 == 0) goto L48;
        int r63 = 3;
    L49:
        int r64 = r63 + r82;
        if (r7 == 0) goto L106;
        if (r9 <= 0) goto L53;
        char r10 = r23[r2].charAt(r9 - 1);
    L54:
        if (r10 == ' ') goto L58;
        if (r10 == '\n') goto L58;
        char r102 = r2;
    L59:
        int r152 = r9 + 3;
        int r142 = TextUtils.indexOf(r23[r2], '\n', r152);
        if (r142 >= 0) goto L62;
    L64:
        char r18 = 1;
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L65:
        CharSequence r11 = substring(r23[r2], r2, r9 - r102);
        int r153 = (r152 + r4.length()) + (!r4.isEmpty());
        if (r153 < 0) goto L105;
        if (r153 >= r23[r2].length()) goto L105;
        if (r153 > r82) goto L70;
        CharSequence r72 = substring(r23[r2], r153, r82);
        int r143 = r82 + 3;
        if (r143 >= r23[r2].length()) goto L74;
        char r154 = r23[r2].charAt(r143);
        char r19 = r2;
    L75:
        CharSequence r22 = r23[r19];
        if (r154 == ' ') goto L80;
        if (r154 == '\n') goto L80;
        char r12 = r19;
    L81:
        CharSequence r26 = substring(r22, r143 + r12, r22.length());
        if (r11.length() == 0) goto L84;
        int r122 = 2;
        CharSequence[] r155 = new CharSequence[2];
        r155[r19] = r11;
        r155[r18] = "\n";
        r11 = AndroidUtilities.concat(r155);
    L85:
        boolean r20 = r3;
        if (r26.length() <= 0) goto L91;
        char r156 = r19;
        if (r26.charAt(r156) == '\n') goto L91;
        CharSequence[] r32 = new CharSequence[r122];
        r32[r156] = "\n";
        r32[r18] = r26;
        r26 = AndroidUtilities.concat(r32);
    L91:
        if (r72.length() > 0) goto L93;
    L95:
        int r157 = 0;
        char r33 = 0;
    L97:
        if (TextUtils.isEmpty(r72) == true) goto L104;
        char r144 = r18;
        if (r72.length() > r144) goto L101;
    L103:
        CharSequence[] r13 = new CharSequence[3];
        r13[r157] = r11;
        r13[r144] = r72;
        r13[2] = r26;
        r23[r157] = AndroidUtilities.concat(r13);
        TLRPC.TL_messageEntityPre r27 = new TLRPC.TL_messageEntityPre();
        r27.offset = (r102 ^ 1) + r9;
        r27.length = ((((r82 - r9) - 3) - (r4.length() + (!r4.isEmpty()))) + (r102 ^ 1)) - r33;
        r27.language = CodeHighlighting.normalizeLanguage(r4);
        r0.add(r27);
        r64 = r64 - 6;
        goto L104
    L101:
        if (r72.charAt(r157) != '\n') goto L103;
        r72 = r72.subSequence(r144, r72.length());
        r82 = r82 - 1;
    L104:
        r8 = r64;
        r3 = r20;
        r2 = 0;
        r6 = -1;
        r7 = 0;
        r9 = -1;
        goto L14
    L93:
        if (r72.charAt(r72.length() - 1) != '\n') goto L95;
        r157 = 0;
        r72 = substring(r72, 0, r72.length() - 1);
        r33 = r18;
        goto L97
    L84:
        r122 = 2;
        r102 = r18;
    L80:
        r12 = r18;
        goto L81
    L74:
        r154 = r2;
        r19 = r154;
        goto L75
    L70:
        char r158 = r2;
        r20 = r3;
    L112:
        r8 = r64;
        r2 = r158;
        r3 = r20;
        r6 = -1;
    L105:
        r20 = r3;
        r158 = r2;
        goto L112
    L62:
        if ((r142 - r152) <= 0) goto L64;
        r18 = 1;
        r4 = r23[r2].toString().substring(r152, r142);
    L58:
        r102 = 1;
        goto L59
    L53:
        r10 = r2;
        goto L54
    L106:
        r20 = r3;
        int r28 = r9 + 1;
        if (r28 == r82) goto L104;
        r158 = 0;
        CharSequence r34 = r23[0];
        if ((r34 instanceof Spanned) == false) goto L113;
        if (((CodeHighlighting.Span[]) ((Spanned) r34).getSpans(Utilities.clamp(r9, r34.length(), 0), Utilities.clamp(r28, r23[0].length(), 0), CodeHighlighting.Span.class)).length > 0) goto L112;
    L113:
        CharSequence r35 = substring(r23[0], 0, r9);
        CharSequence r29 = substring(r23[0], r28, r82);
        CharSequence r42 = r23[0];
        r23[0] = AndroidUtilities.concat(new CharSequence[]{r35, r29, substring(r42, r82 + 1, r42.length())});
        TLRPC.TL_messageEntityCode r210 = new TLRPC.TL_messageEntityCode();
        r210.offset = r9;
        r210.length = (r82 - r9) - 1;
        r0.add(r210);
        r64 = r64 - 2;
        goto L104
    L48:
        r63 = 1;
        goto L49
    L39:
        r16 = 1;
        goto L40
    L24:
        if ((r23[r2].length() - r82) > 2) goto L26;
    L30:
        r7 = r2;
    L31:
        if (r7 == 0) goto L33;
        int r159 = 3;
    L34:
        r9 = r82;
        r8 = r82 + r159;
        goto L14
    L33:
        r159 = 1;
        goto L34
    L26:
        if (r23[r2].charAt(r82 + 1) != '`') goto L30;
        if (r23[r2].charAt(r82 + 2) != '`') goto L30;
        r7 = 1;
        goto L31
    L18:
        r15 = "```";
    L115:
        boolean r202 = r3;
        if (r9 == r6) goto L122;
        if (r7 == 0) goto L122;
        CharSequence r211 = substring(r23[0], 0, r9);
        CharSequence r36 = r23[0];
        r23[0] = AndroidUtilities.concat(new CharSequence[]{r211, substring(r36, r9 + 2, r36.length())});
        if (r0 != null) goto L121;
        r0 = new ArrayList();
    L121:
        TLRPC.TL_messageEntityCode r212 = new TLRPC.TL_messageEntityCode();
        r212.offset = r9;
        r212.length = 1;
        r0.add(r212);
    L122:
        if (r202 == true) goto L124;
        LocaleUtils.parseMarkdownLinks(r23);
    L124:
        ?? r1510 = 0;
        CharSequence r213 = r23[0];
        if ((r213 instanceof Spanned) == false) goto L263;
        ?? r37 = (Spanned) r213;
        TextStyleSpan[] r214 = (TextStyleSpan[]) r37.getSpans(0, r213.length(), TextStyleSpan.class);
        if (r214 != null) goto L129;
    L144:
        r1510 = 0;
    L145:
        URLSpanUserMention[] r215 = (URLSpanUserMention[]) r37.getSpans(r1510, r23[r1510].length(), URLSpanUserMention.class);
        if (r215 != null) goto L148;
    L161:
        URLSpanReplacement[] r216 = (URLSpanReplacement[]) r37.getSpans(0, r23[0].length(), URLSpanReplacement.class);
        if (r216 != null) goto L164;
    L174:
        AnimatedEmojiSpan[] r217 = (AnimatedEmojiSpan[]) r37.getSpans(0, r23[0].length(), AnimatedEmojiSpan.class);
        if (r217 != null) goto L177;
    L191:
        CodeHighlighting.Span[] r218 = (CodeHighlighting.Span[]) r37.getSpans(0, r23[0].length(), CodeHighlighting.Span.class);
        if (r218 != null) goto L194;
    L208:
        QuoteSpan[] r219 = (QuoteSpan[]) r37.getSpans(0, r23[0].length(), QuoteSpan.class);
        if (r219 != null) goto L211;
    L225:
        FormattedDateSpan[] r220 = (FormattedDateSpan[]) r37.getSpans(0, r23[0].length(), FormattedDateSpan.class);
        if (r220 == null) goto L243;
        if (r220.length <= 0) goto L243;
        if (r0 != null) goto L231;
        r0 = new ArrayList();
    L231:
        ArrayList<TLRPC.MessageEntity> r43 = r0;
        int r65 = 0;
    L233:
        if (r65 >= r220.length) goto L241;
        FormattedDateSpan r02 = r220[r65];
        if (r02 == null) goto L240;
        TLRPC.TL_messageEntityFormattedDate r73 = new TLRPC.TL_messageEntityFormattedDate();     // Catch: Exception -> L238
        r73.offset = r37.getSpanStart(r02);     // Catch: Exception -> L238
        r73.length = Math.min(r37.getSpanEnd(r02), r23[0].length()) - r73.offset;     // Catch: Exception -> L238
        TLRPC.TL_messageEntityFormattedDate r03 = r02.entity;     // Catch: Exception -> L238
        r73.relative = r03.relative;     // Catch: Exception -> L238
        r73.short_time = r03.short_time;     // Catch: Exception -> L238
        r73.long_time = r03.long_time;     // Catch: Exception -> L238
        r73.long_date = r03.long_date;     // Catch: Exception -> L238
        r73.short_date = r03.short_date;     // Catch: Exception -> L238
        r73.day_of_week = r03.day_of_week;     // Catch: Exception -> L238
        r73.date = r03.date;     // Catch: Exception -> L238
        r43.add(r73);     // Catch: Exception -> L238
    L238:
        e = move-exception;
        FileLog.e(e);
    L240:
        r65 = r65 + 1;
        goto L233
    L241:
        r0 = r43;
    L243:
        if ((r37 instanceof Spannable) == false) goto L262;
        Spannable r221 = (Spannable) r37;
        AndroidUtilities.addLinksSafe(r221, 1, false, false);
        URLSpan[] r44 = (URLSpan[]) r221.getSpans(0, r23[0].length(), URLSpan.class);
        if (r44 == null) goto L262;
        if (r44.length <= 0) goto L262;
        if (r0 != null) goto L250;
        r0 = new ArrayList();
    L250:
        int r66 = 0;
    L252:
        if (r66 >= r44.length) goto L262;
        URLSpan r74 = r44[r66];
        if ((r74 instanceof URLSpanReplacement) == true) goto L261;
        if ((r74 instanceof URLSpanUserMention) == true) goto L261;
        if ((r74 instanceof FormattedDateSpan) == true) goto L261;
        TLRPC.TL_messageEntityUrl r75 = new TLRPC.TL_messageEntityUrl();
        r75.offset = r37.getSpanStart(r44[r66]);
        r75.length = Math.min(r37.getSpanEnd(r44[r66]), r23[0].length()) - r75.offset;
        r75.url = r44[r66].getURL();
        r0.add(r75);
        r221.removeSpan(r44[r66]);
    L261:
        r66 = r66 + 1;
    L262:
        char r192 = 0;
    L264:
        CharSequence r222 = r23[r192];
        if (r0 != null) goto L267;
        r0 = new ArrayList();
    L267:
        if (r5 == false) goto L272;
        r222 = parsePattern(parsePattern(parsePattern(r222, BOLD_PATTERN, r0, new MediaDataController$$ExternalSyntheticLambda201()), ITALIC_PATTERN, r0, new MediaDataController$$ExternalSyntheticLambda202()), SPOILER_PATTERN, r0, new MediaDataController$$ExternalSyntheticLambda203());
        if (r24 == false) goto L272;
        r222 = parsePattern(r222, STRIKE_PATTERN, r0, new MediaDataController$$ExternalSyntheticLambda204());
    L272:
        if (r222.length() <= 0) goto L285;
        if (r222.charAt(0) != '\n') goto L276;
    L277:
        int r145 = 1;
        r222 = r222.subSequence(1, r222.length());
        int r1 = 0;
    L279:
        if (r1 >= r0.size()) goto L272;
        TLRPC.MessageEntity r38 = r0.get(r1);
        int r45 = r38.offset;
        if (r45 != 0) goto L283;
        r38.length -= r145;
    L283:
        r38.offset = Math.max(0, r45 - 1);
        r1 = r1 + 1;
        r145 = 1;
        goto L279
    L276:
        if (r222.charAt(0) == ' ') goto L277;
    L285:
        if (r222.length() <= 0) goto L301;
        if (r222.charAt(r222.length() - 1) != '\n') goto L289;
    L292:
        r222 = r222.subSequence(0, r222.length() - 1);
        int r1511 = 0;
    L294:
        if (r1511 >= r0.size()) goto L285;
        TLRPC.MessageEntity r17 = r0.get(r1511);
        if ((r17.offset + r17.length) <= r222.length()) goto L299;
        r17.length--;
    L299:
        r1511 = r1511 + 1;
        goto L294
    L289:
        if (r222.charAt(r222.length() - 1) == ' ') goto L292;
    L301:
        r23[0] = r222;
        goto L302
    L211:
        if (r219.length <= 0) goto L225;
        if (r0 != null) goto L214;
        r0 = new ArrayList();
    L214:
        ArrayList<TLRPC.MessageEntity> r46 = r0;
        int r67 = 0;
    L216:
        if (r67 >= r219.length) goto L224;
        QuoteSpan r04 = r219[r67];
        if (r04 == null) goto L223;
        TLRPC.TL_messageEntityBlockquote r76 = new TLRPC.TL_messageEntityBlockquote();     // Catch: Exception -> L221
        r76.offset = r37.getSpanStart(r04);     // Catch: Exception -> L221
        r76.length = Math.min(r37.getSpanEnd(r04), r23[0].length()) - r76.offset;     // Catch: Exception -> L221
        r76.collapsed = r04.isCollapsing;     // Catch: Exception -> L221
        r46.add(r76);     // Catch: Exception -> L221
    L221:
        e = move-exception;
        FileLog.e(e);
    L223:
        r67 = r67 + 1;
        goto L216
    L224:
        r0 = r46;
        goto L225
    L194:
        if (r218.length <= 0) goto L208;
        if (r0 != null) goto L197;
        r0 = new ArrayList();
    L197:
        ArrayList<TLRPC.MessageEntity> r47 = r0;
        int r68 = 0;
    L199:
        if (r68 >= r218.length) goto L207;
        CodeHighlighting.Span r05 = r218[r68];
        if (r05 == null) goto L206;
        TLRPC.TL_messageEntityPre r77 = new TLRPC.TL_messageEntityPre();     // Catch: Exception -> L204
        r77.offset = r37.getSpanStart(r05);     // Catch: Exception -> L204
        r77.length = Math.min(r37.getSpanEnd(r05), r23[0].length()) - r77.offset;     // Catch: Exception -> L204
        r77.language = CodeHighlighting.normalizeLanguage(r05.lng);     // Catch: Exception -> L204
        r47.add(r77);     // Catch: Exception -> L204
    L204:
        e = move-exception;
        FileLog.e(e);
    L206:
        r68 = r68 + 1;
        goto L199
    L207:
        r0 = r47;
        goto L208
    L177:
        if (r217.length <= 0) goto L191;
        if (r0 != null) goto L180;
        r0 = new ArrayList();
    L180:
        ArrayList<TLRPC.MessageEntity> r48 = r0;
        int r69 = 0;
    L182:
        if (r69 >= r217.length) goto L190;
        AnimatedEmojiSpan r06 = r217[r69];
        if (r06 == null) goto L189;
        TLRPC.TL_messageEntityCustomEmoji r78 = new TLRPC.TL_messageEntityCustomEmoji();     // Catch: Exception -> L187
        r78.offset = r37.getSpanStart(r06);     // Catch: Exception -> L187
        r78.length = Math.min(r37.getSpanEnd(r06), r23[0].length()) - r78.offset;     // Catch: Exception -> L187
        r78.document_id = r06.getDocumentId();     // Catch: Exception -> L187
        r78.document = r06.document;     // Catch: Exception -> L187
        r78.local = r06.local;     // Catch: Exception -> L187
        r48.add(r78);     // Catch: Exception -> L187
    L187:
        e = move-exception;
        FileLog.e(e);
    L189:
        r69 = r69 + 1;
        goto L182
    L190:
        r0 = r48;
        goto L191
    L164:
        if (r216.length <= 0) goto L174;
        if (r0 != null) goto L167;
        r0 = new ArrayList();
    L167:
        int r49 = 0;
    L169:
        if (r49 >= r216.length) goto L174;
        TLRPC.TL_messageEntityTextUrl r610 = new TLRPC.TL_messageEntityTextUrl();
        r610.offset = r37.getSpanStart(r216[r49]);
        r610.length = Math.min(r37.getSpanEnd(r216[r49]), r23[0].length()) - r610.offset;
        r610.url = r216[r49].getURL();
        r0.add(r610);
        TextStyleSpan.TextStyleRun r79 = r216[r49].getTextStyleRun();
        if (r79 == null) goto L173;
        int r710 = r79.flags;
        int r83 = r610.offset;
        addStyle(r710, r83, r610.length + r83, r0);
    L173:
        r49 = r49 + 1;
        goto L169
    L148:
        if (r215.length <= 0) goto L161;
        if (r0 != null) goto L151;
        r0 = new ArrayList();
    L151:
        int r410 = 0;
    L153:
        if (r410 >= r215.length) goto L161;
        TLRPC.TL_inputMessageEntityMentionName r611 = new TLRPC.TL_inputMessageEntityMentionName();
        TLRPC.InputUser r711 = getMessagesController().getInputUser(Utilities.parseLong(r215[r410].getURL()).longValue());
        r611.user_id = r711;
        if (r711 == null) goto L160;
        r611.offset = r37.getSpanStart(r215[r410]);
        r611.length = Math.min(r37.getSpanEnd(r215[r410]), r23[0].length()) - r611.offset;
        if (r23[0].charAt((r8 + r7) - 1) != ' ') goto L159;
        r611.length--;
    L159:
        r0.add(r611);
    L160:
        r410 = r410 + 1;
        goto L153
    L129:
        if (r214.length <= 0) goto L144;
        int r411 = 0;
    L132:
        if (r411 >= r214.length) goto L145;
        TextStyleSpan r612 = r214[r411];
        int r712 = r37.getSpanStart(r612);
        int r84 = r37.getSpanEnd(r612);
        if (checkInclusion(r712, r0, r1510) == true) goto L143;
        if (checkInclusion(r84, r0, true) == true) goto L143;
        if (checkIntersection(r712, r84, r0) == true) goto L143;
        if (r0 != null) goto L142;
        r0 = new ArrayList();
    L142:
        addStyle(r612.getStyleFlags(), r712, r84, r0);
    L143:
        r411 = r411 + 1;
        r1510 = 0;
        goto L132
    L263:
        r192 = 0;
    L11:
        r5 = false;
    L302:
        return r0;
    }

    public ArrayList<TLRPC.StickerSetCovered> getFeaturedEmojiSets() {
        return this.featuredStickerSets[1];
    }

    public ArrayList<TLRPC.StickerSetCovered> getFeaturedStickerSets() {
        return this.featuredStickerSets[0];
    }

    public long getFeaturedStickersHashWithoutUnread(boolean r6) {
        long r0 = 0;
        int r2 = 0;
    L4:
        if (r2 >= this.featuredStickerSets[r6 ? 1 : 0].size()) goto L10;
        TLRPC.StickerSet r3 = this.featuredStickerSets[r6 ? 1 : 0].get(r2).set;
        if (r3.archived == true) goto L9;
        r0 = calcHash(r0, r3.id);
    L9:
        r2 = r2 + 1;
        goto L4
    L10:
        return r0;
    }

    public ArrayList<MessageObject> getFoundMessageObjects() {
        return this.searchResultMessages;
    }

    public TLRPC.Document getGreetingsSticker() {
        TLRPC.Document r0 = this.greetingsSticker;
        preloadNextGreetingsSticker();
        return r0;
    }

    public TLRPC.TL_messages_stickerSet getGroupStickerSetById(TLRPC.StickerSet r4) {
        TLRPC.TL_messages_stickerSet r0 = this.stickerSetsById.get(r4.id);
        if (r0 != null) goto L14;
        r0 = this.groupStickerSets.get(r4.id);
        if (r0 == null) goto L13;
        TLRPC.StickerSet r1 = r0.set;
        if (r1 == null) goto L13;
        if (r1.hash == r4.hash) goto L12;
        loadGroupStickerSet(r4, false);
    L12:
        return r0;
    L13:
        loadGroupStickerSet(r4, true);
    L14:
        return r0;
    }

    public String getLastSearchQuery() {
        return this.lastSearchQuery;
    }

    public int getMask() {
        int r2 = 1;
        if (this.lastReturnedNum < (this.searchResultMessages.size() - 1)) goto L11;
        boolean[] r0 = this.messagesSearchEndReached;
        if (r0[0] == false) goto L11;
        if (r0[1] == false) goto L11;
        r2 = 0;
    L11:
        if (this.lastReturnedNum > 0) goto L13;
        return r2;
    L13:
        return r2 | 2;
    }

    public void getMediaCount(long r11, long r13, int r15, int r16, boolean r17) {
        if (r17 == true) goto L36;
        if (DialogObject.isEncryptedDialog(r11) == true) goto L36;
        TLRPC.TL_messages_getSearchCounters r8 = new TLRPC.TL_messages_getSearchCounters();
        if (r15 != 0) goto L9;
        r8.filters.add(new TLRPC.TL_inputMessagesFilterPhotoVideo());
    L26:
        if (r13 != 0) goto L28;
    L31:
        TLRPC.InputPeer r0 = getMessagesController().getInputPeer(r11);
        r8.peer = r0;
        if (r0 != null) goto L34;
        return;
    L34:
        getConnectionsManager().bindRequestToGuid(getConnectionsManager().sendRequest(r8, new MediaDataController$$ExternalSyntheticLambda138(this, r11, r13, r15, r16)), r16);
        return;
    L28:
        if (r11 != getUserConfig().getClientUserId()) goto L30;
        r8.saved_peer_id = getMessagesController().getInputPeer(r13);
        r8.flags = 4 | r8.flags;
        goto L31
    L30:
        r8.top_msg_id = (int) r13;
        r8.flags |= 1;
        goto L31
    L9:
        if (r15 != 1) goto L12;
        r8.filters.add(new TLRPC.TL_inputMessagesFilterDocument());
        goto L26
    L12:
        if (r15 != 2) goto L15;
        r8.filters.add(new TLRPC.TL_inputMessagesFilterRoundVoice());
        goto L26
    L15:
        if (r15 != 3) goto L17;
        r8.filters.add(new TLRPC.TL_inputMessagesFilterUrl());
        goto L26
    L17:
        if (r15 != 4) goto L20;
        r8.filters.add(new TLRPC.TL_inputMessagesFilterMusic());
        goto L26
    L20:
        if (r15 != 5) goto L23;
        r8.filters.add(new TLRPC.TL_inputMessagesFilterGif());
        goto L26
    L23:
        if (r15 != 8) goto L26;
        r8.filters.add(new TLRPC.TL_inputMessagesFilterPoll());
    L36:
        getMediaCountDatabase(r11, r13, r15, r16);
    }

    public void getMediaCounts(long r9, long r11, int r13) {
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda140(this, r11, r9, r13));
    }

    public Pair<Long, TLRPC.DraftMessage> getOneThreadDraft(long r3) {
        LongSparseArray<TLRPC.DraftMessage> r2 = this.drafts.get(r3);
        if (r2 != null) goto L5;
        return null;
    L5:
        if (r2.size() > 0) goto L8;
        return null;
    L8:
        return new Pair(Long.valueOf(r2.keyAt(0)), r2.valueAt(0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Integer getPremiumHintAnnualDiscount(boolean r18) {
        Integer r1 = null;
        if (r18 == true) goto L5;
    L8:
        TLRPC.TL_help_premiumPromo r2 = this.premiumPromo;
        if (r2 != null) goto L11;
    L10:
        return null;
    L11:
        ArrayList<TLRPC.TL_premiumSubscriptionOption> r22 = r2.period_options;
        int r3 = r22.size();
        double r5 = 0.0d;
        boolean r7 = false;
        int r8 = 0;
    L13:
        if (r8 >= r3) goto L47;
        TLRPC.TL_premiumSubscriptionOption r12 = r22.get(r8);
        r8 = r8 + 1;
        TLRPC.TL_premiumSubscriptionOption r122 = r12;
        if (r18 == true) goto L17;
        Integer r16 = r1;
        if (r122.months != 1) goto L46;
    L25:
        if (BuildVars.useInvoiceBilling() == true) goto L44;
        ProductDetails r13 = BillingController.PREMIUM_PRODUCT_DETAILS;
        if (r13 == null) goto L44;
        Iterator<ProductDetails.SubscriptionOfferDetails> r14 = r13.getSubscriptionOfferDetails().iterator();
    L30:
        if (r14.hasNext() == false) goto L39;
        ProductDetails.SubscriptionOfferDetails r52 = r14.next();
        String r6 = r52.getPricingPhases().getPricingPhaseList().get(0).getBillingPeriod();
        int r72 = r122.months;
        if (r72 == 12) goto L34;
        if (r6.equals(String.format(Locale.ROOT, "P%dM", new Object[]{Integer.valueOf(r72)})) == false) goto L30;
    L40:
        if (r52 != 0) goto L42;
        double r53 = r122.amount;
        int r15 = r122.months;
    L45:
        r5 = r53 / r15;
        r7 = true;
        goto L46
    L42:
        r53 = r52.getPricingPhases().getPricingPhaseList().get(0).getPriceAmountMicros();
        r15 = r122.months;
        goto L45
    L34:
        if (r6.equals("P1Y") == false) goto L30;
    L39:
        r52 = r16;
    L44:
        r53 = r122.amount;
        r15 = r122.months;
    L46:
        r1 = r16;
        goto L13
    L17:
        if (r122.current == false) goto L21;
        r16 = r1;
        if (Objects.equals(r122.transaction.replaceAll("^(.*?)(?:\\.\\.\\d*|)$", "$1"), BillingController.getInstance().getLastPremiumTransaction()) == false) goto L46;
    L21:
        r16 = r1;
        goto L46
    L47:
        Integer r162 = r1;
        ArrayList<TLRPC.TL_premiumSubscriptionOption> r0 = this.premiumPromo.period_options;
        int r17 = r0.size();
        int r23 = 0;
        int r32 = 0;
    L48:
        if (r32 >= r17) goto L74;
        TLRPC.TL_premiumSubscriptionOption r82 = r0.get(r32);
        r32 = r32 + 1;
        TLRPC.TL_premiumSubscriptionOption r83 = r82;
        if (r7 == false) goto L48;
        if (r83.months != 12) goto L48;
        if (BuildVars.useInvoiceBilling() == true) goto L72;
        ProductDetails r24 = BillingController.PREMIUM_PRODUCT_DETAILS;
        if (r24 == null) goto L72;
        Iterator<ProductDetails.SubscriptionOfferDetails> r25 = r24.getSubscriptionOfferDetails().iterator();
    L59:
        if (r25.hasNext() == false) goto L68;
        ProductDetails.SubscriptionOfferDetails r123 = r25.next();
        String r132 = r123.getPricingPhases().getPricingPhaseList().get(0).getBillingPeriod();
        int r142 = r83.months;
        if (r142 == 12) goto L63;
        if (r132.equals(String.format(Locale.ROOT, "P%dM", new Object[]{Integer.valueOf(r142)})) == false) goto L59;
    L69:
        if (r123 != 0) goto L71;
        double r124 = r83.amount;
        int r26 = r83.months;
    L73:
        r23 = (int) ((1.0d - ((r124 / r26) / r5)) * 100.0d);
        goto L48
    L71:
        r124 = r123.getPricingPhases().getPricingPhaseList().get(0).getPriceAmountMicros();
        r26 = r83.months;
        goto L73
    L63:
        if (r132.equals("P1Y") == false) goto L59;
    L68:
        r123 = r162;
    L72:
        r124 = r83.amount;
        r26 = r83.months;
        goto L73
    L74:
        if (r7 == false) goto L79;
        if (r23 <= 0) goto L79;
        return Integer.valueOf(r23);
    L79:
        return r162;
    L5:
        if (BillingController.getInstance().isReady() == false) goto L10;
        if (BillingController.getInstance().getLastPremiumTransaction() == null) goto L10;
        goto L8
    }

    public TLRPC.TL_help_premiumPromo getPremiumPromo() {
        return this.premiumPromo;
    }

    public List<TLRPC.TL_availableReaction> getReactionsList() {
        return this.reactionsList;
    }

    public HashMap<String, TLRPC.TL_availableReaction> getReactionsMap() {
        return this.reactionsMap;
    }

    public ArrayList<TLRPC.EmojiStatus> getRecentEmojiStatuses() {
        if (this.emojiStatusesFromCacheFetched[0] == true) goto L6;
        fetchEmojiStatuses(0, true);
    L13:
        return this.emojiStatuses[0];
    L6:
        if (this.emojiStatuses[0] != null) goto L8;
    L11:
        fetchEmojiStatuses(0, false);
        goto L13
    L8:
        if (this.emojiStatusesFetchDate[0] == null) goto L13;
        if (((System.currentTimeMillis() / 1000) - this.emojiStatusesFetchDate[0].longValue()) <= 1800) goto L13;
        goto L11
    }

    public ArrayList<TLRPC.Document> getRecentGifs() {
        return new ArrayList(this.recentGifs);
    }

    public ArrayList<TLRPC.Reaction> getRecentReactions() {
        return this.recentReactions;
    }

    public ArrayList<TLRPC.Document> getRecentStickers(int r2) {
        return getRecentStickers(r2, false);
    }

    public ArrayList<TLRPC.Document> getRecentStickers(int r3, boolean r4) {
        ArrayList<TLRPC.Document> r0 = this.recentStickers[r3];
        if (r3 == 7) goto L5;
        int r32 = r0.size();
        if (ExteraConfig.getUnlimitedRecentStickers() == false) goto L9;
        int r1 = Integer.MAX_VALUE;
    L10:
        ArrayList<TLRPC.Document> r2 = new ArrayList(r0.subList(0, Math.min(r32, r1)));
        if (r4 == true) goto L13;
    L15:
        return r2;
    L13:
        if (r2.isEmpty() == true) goto L15;
        r2.add(0, new TLRPC.TL_documentEmpty());
        goto L15
    L9:
        r1 = 20;
        goto L10
    L5:
        return new ArrayList(this.recentStickers[r3]);
    }

    public ArrayList<TLRPC.Document> getRecentStickersNoCopy(int r1) {
        return this.recentStickers[r1];
    }

    public ArrayList<TLRPC.Reaction> getSavedReactions() {
        return this.savedReactions;
    }

    public int getSearchCount() {
        boolean r0 = this.searchServerResultMessages.isEmpty();
        int[] r1 = this.messagesSearchCount;
        if (r0 == false) goto L7;
        return Math.max(Math.max(r1[0] + r1[1], this.messagesLocalSearchCount), this.searchServerResultMessages.size());
    L7:
        return Math.max(r1[0] + r1[1], this.searchServerResultMessages.size());
    }

    public int getSearchPosition() {
        return this.lastReturnedNum;
    }

    public LongSparseArray<TLRPC.Document> getStickerByIds(int r1) {
        return this.stickersByIds[r1];
    }

    public TLRPC.TL_messages_stickerSet getStickerSet(TLRPC.InputStickerSet r2, Integer r3, boolean r4) {
        return getStickerSet(r2, r3, r4, null);
    }

    public TLRPC.TL_messages_stickerSet getStickerSet(TLRPC.InputStickerSet r7, Integer r8, boolean r9, Utilities.Callback<TLRPC.TL_messages_stickerSet> r10) {
        return getStickerSet(r7, r8, r9, false, r10);
    }

    public TLRPC.TL_messages_stickerSet getStickerSet(TLRPC.InputStickerSet r11, Integer r12, boolean r13, boolean r14, Utilities.Callback<TLRPC.TL_messages_stickerSet> r15) {
        if (r11 != null) goto L5;
        return null;
    L5:
        boolean r1 = r11 instanceof TLRPC.TL_inputStickerSetID;
        if (r1 == false) goto L11;
        if (this.stickerSetsById.containsKey(r11.id) == false) goto L11;
        TLRPC.TL_messages_stickerSet r2 = this.stickerSetsById.get(r11.id);
    L28:
        if (r2 == null) goto L33;
        if (r14 == true) goto L32;
        if (r15 == null) goto L32;
        r15.run(r2);
    L32:
        return r2;
    L33:
        String r7 = inputSetKey(r11);
        if (r15 == null) goto L36;
    L38:
        this.loadingStickerSetsKeys.add(r7);
        if (r1 == false) goto L42;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda244(this, r11, r12, r7, r15, r13));
    L47:
        return null;
    L42:
        if ((r11 instanceof TLRPC.TL_inputStickerSetShortName) == false) goto L44;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda245(this, r11, r12, r7, r15, r13));
        goto L47
    L44:
        if (r13 == true) goto L46;
        fetchStickerSetInternal(r11, new MediaDataController$$ExternalSyntheticLambda246(this, r7, r15, r11));
        goto L47
    L46:
        this.loadingStickerSetsKeys.remove(r7);
        goto L47
    L36:
        if (this.loadingStickerSetsKeys.contains(r7) == false) goto L38;
        return null;
    L11:
        if ((r11 instanceof TLRPC.TL_inputStickerSetShortName) == false) goto L18;
        String r22 = r11.short_name;
        if (r22 == null) goto L18;
        if (this.stickerSetsByName.containsKey(r22.toLowerCase()) == false) goto L18;
        r2 = this.stickerSetsByName.get(r11.short_name.toLowerCase());
    L18:
        if ((r11 instanceof TLRPC.TL_inputStickerSetEmojiDefaultStatuses) == false) goto L23;
        r2 = this.stickerSetDefaultStatuses;
        if (r2 != null) goto L28;
    L23:
        if ((r11 instanceof TLRPC.TL_inputStickerSetEmojiChannelDefaultStatuses) == false) goto L27;
        r2 = this.stickerSetDefaultChannelStatuses;
        if (r2 != null) goto L28;
    L27:
        r2 = null;
        goto L28
    }

    public TLRPC.TL_messages_stickerSet getStickerSet(TLRPC.InputStickerSet r2, boolean r3) {
        return getStickerSet(r2, null, r3, null);
    }

    public TLRPC.TL_messages_stickerSet getStickerSet(TLRPC.StickerSet r4, boolean r5) {
        TLRPC.TL_inputStickerSetID r0 = new TLRPC.TL_inputStickerSetID();
        r0.id = r4.id;
        r0.access_hash = r4.access_hash;
        return getStickerSet(r0, r5);
    }

    public TLRPC.TL_messages_stickerSet getStickerSetByEmojiOrName(String r1) {
        return this.diceStickerSetsByEmoji.get(r1);
    }

    public TLRPC.TL_messages_stickerSet getStickerSetById(long r1) {
        return this.stickerSetsById.get(r1);
    }

    public TLRPC.TL_messages_stickerSet getStickerSetByName(String r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return this.stickerSetsByName.get(r1.toLowerCase());
    }

    public String getStickerSetName(long r3) {
        TLRPC.TL_messages_stickerSet r0 = this.stickerSetsById.get(r3);
        if (r0 != null) goto L5;
        TLRPC.StickerSetCovered r02 = this.featuredStickerSetsById[0].get(r3);
        if (r02 != null) goto L9;
        TLRPC.StickerSetCovered r2 = this.featuredStickerSetsById[1].get(r3);
        if (r2 != null) goto L13;
        return null;
    L13:
        return r2.set.short_name;
    L9:
        return r02.set.short_name;
    L5:
        return r0.set.short_name;
    }

    public ArrayList<TLRPC.TL_messages_stickerSet> getStickerSets(int r2) {
        ArrayList<TLRPC.TL_messages_stickerSet>[] r1 = this.stickerSets;
        if (r2 != 3) goto L7;
        return r1[2];
    L7:
        return r1[r2];
    }

    public ArrayList<TLRPC.Reaction> getTopReactions() {
        return this.topReactions;
    }

    public ArrayList<Long> getUnreadEmojiSets() {
        return this.unreadStickerSets[1];
    }

    public ArrayList<Long> getUnreadStickerSets() {
        return this.unreadStickerSets[0];
    }

    public boolean hasRecentGif(TLRPC.Document r8) {
        int r1 = 0;
    L4:
        if (r1 >= this.recentGifs.size()) goto L10;
        TLRPC.Document r2 = this.recentGifs.get(r1);
        if (r2.id == r8.id) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        this.recentGifs.remove(r1);
        this.recentGifs.add(0, r2);
        return true;
    L10:
        return false;
    }

    public void increaseGuestRating(long r2) {
        increaseInlineRating(r2, true);
    }

    public void increaseInlineRating(long r2) {
        increaseInlineRating(r2, false);
    }

    public void increasePeerRaiting(long r3) {
        if (getUserConfig().suggestContacts == true) goto L6;
        return;
    L6:
        if (DialogObject.isUserDialog(r3) == false) goto L20;
        TLRPC.User r0 = getMessagesController().getUser(Long.valueOf(r3));
        if (r0 != null) goto L11;
        return;
    L11:
        if (r0.bot == false) goto L13;
        return;
    L13:
        if (r0.self == true) goto L21;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda238(this, r3));
        return;
    L21:
        return;
    }

    public void increaseWebappRating(long r12) {
        TLRPC.User r0 = getMessagesController().getUser(Long.valueOf(r12));
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.bot == true) goto L8;
        return;
    L8:
        if (getUserConfig().webappRatingLoadTime == 0) goto L10;
        int r02 = Math.max(1, ((int) (System.currentTimeMillis() / 1000)) - getUserConfig().webappRatingLoadTime);
    L11:
        int r3 = 0;
    L13:
        if (r3 >= this.webapps.size()) goto L18;
        TLRPC.TL_topPeer r4 = this.webapps.get(r3);
        if (r4.peer.user_id == r12) goto L19;
        r3 = r3 + 1;
    L19:
        if (r4 != null) goto L21;
        r4 = new TLRPC.TL_topPeer();
        TLRPC.TL_peerUser r32 = new TLRPC.TL_peerUser();
        r4.peer = r32;
        r32.user_id = r12;
        this.webapps.add(r4);
    L21:
        r4.rating += Math.exp(r02 / getMessagesController().ratingDecay);
        Collections.sort(this.webapps, new MediaDataController$$ExternalSyntheticLambda230());
        if (this.webapps.size() <= 20) goto L24;
        ArrayList<TLRPC.TL_topPeer> r03 = this.webapps;
        r03.remove(r03.size() - 1);
    L24:
        savePeer(r12, 2, r4.rating);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadWebappsHints, new Object[0]);
        return;
    L18:
        r4 = null;
        goto L19
    L10:
        r02 = 60;
        goto L11
    }

    public void installShortcut(long r2, int r4) {
        installShortcut(r2, r4, null);
    }

    public void installShortcut(long r19, int r21, Utilities.Callback<Boolean> r22) {
    L156:
        e = move-exception;
        FileLog.e(e);
        return;
    L4:
        if (r21 != SHORTCUT_TYPE_USER_OR_CHAT) goto L7;
        Intent r0 = createIntrnalShortcutIntent(r19);     // Catch: Exception -> L156
    L6:
        Intent r6 = r0;
        if (r6 != null) goto L13;
        if (r22 == null) goto L172;
        r22.run(Boolean.FALSE);     // Catch: Exception -> L156
        return;
    L172:
        return;
    L13:
        if (DialogObject.isEncryptedDialog(r19) == false) goto L22;
        TLRPC.EncryptedChat r02 = getMessagesController().getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r19)));     // Catch: Exception -> L156
        if (r02 != null) goto L19;
        if (r22 == null) goto L173;
        r22.run(Boolean.FALSE);     // Catch: Exception -> L156
        return;
    L173:
        return;
    L19:
        TLRPC.User r03 = getMessagesController().getUser(Long.valueOf(r02.user_id));     // Catch: Exception -> L156
    L20:
        TLRPC.User r8 = r03;
        TLRPC.Chat r9 = null;
    L27:
        if (r8 != null) goto L33;
        if (r9 != null) goto L33;
        if (r22 == null) goto L174;
        r22.run(Boolean.FALSE);     // Catch: Exception -> L156
        return;
    L174:
        return;
    L33:
        if (r8 != null) goto L35;
        String r11 = r9.title;     // Catch: Exception -> L156
        TLRPC.ChatPhoto r12 = r9.photo;     // Catch: Exception -> L156
        if (r12 != null) goto L54;
    L40:
        boolean r13 = false;
    L41:
        TLRPC.FileLocation r122 = null;
    L56:
        if (r13 == true) goto L60;
        if (r122 != null) goto L60;
        PendingIntent r10 = null;
        Bitmap r123 = null;
    L109:
        if (Build.VERSION.SDK_INT >= 26) goto L111;
        Intent r04 = new Intent();     // Catch: Exception -> L156
        if (r123 == null) goto L141;
        r04.putExtra("android.intent.extra.shortcut.ICON", r123);     // Catch: Exception -> L156
    L152:
        r04.putExtra("android.intent.extra.shortcut.INTENT", r6);     // Catch: Exception -> L156
        r04.putExtra("android.intent.extra.shortcut.NAME", r11);     // Catch: Exception -> L156
        r04.putExtra("duplicate", false);     // Catch: Exception -> L156
        r04.setAction("com.android.launcher.action.INSTALL_SHORTCUT");     // Catch: Exception -> L156
        ApplicationLoader.applicationContext.sendBroadcast(r04);     // Catch: Exception -> L156
        return;
    L141:
        if (r8 == null) goto L147;
        if (r8.bot == false) goto L145;
        r04.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(ApplicationLoader.applicationContext, R.drawable.book_bot));     // Catch: Exception -> L156
        goto L152
    L145:
        r04.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(ApplicationLoader.applicationContext, R.drawable.book_user));     // Catch: Exception -> L156
        goto L152
    L147:
        if (ChatObject.isChannel(r9) == true) goto L149;
    L151:
        r04.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(ApplicationLoader.applicationContext, R.drawable.book_group));     // Catch: Exception -> L156
        goto L152
    L149:
        if (r9.megagroup == true) goto L151;
        r04.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(ApplicationLoader.applicationContext, R.drawable.book_channel));     // Catch: Exception -> L156
        goto L152
    L111:
        if (r21 != SHORTCUT_TYPE_USER_OR_CHAT) goto L113;
        String r05 = "sdid_";
    L114:
        ShortcutInfoCompat.Builder r06 = new ShortcutInfoCompat.Builder(ApplicationLoader.applicationContext, r05 + r19).setShortLabel(r11).setIntent(r6);     // Catch: Exception -> L156
        if (r123 == null) goto L117;
        r06.setIcon(IconCompat.createWithBitmap(r123));     // Catch: Exception -> L156
    L128:
        if (r22 == null) goto L130;
        byte[] r2 = new byte[16];     // Catch: Exception -> L156
        Utilities.fastRandom.nextBytes(r2);     // Catch: Exception -> L156
        String r23 = Utilities.bytesToHex(r2);     // Catch: Exception -> L156
        Intent r3 = new Intent(ApplicationLoader.applicationContext, ShortcutResultReceiver.class);     // Catch: Exception -> L156
        r3.putExtra("account", this.currentAccount);     // Catch: Exception -> L156
        r3.putExtra("req_id", r23);     // Catch: Exception -> L156
        PendingIntent r32 = PendingIntent.getBroadcast(ApplicationLoader.applicationContext, 0, r3, 167772160);     // Catch: Exception -> L156
        this.shortcutCallbacks.put(r23, r22);     // Catch: Exception -> L156
    L131:
        Context r1 = ApplicationLoader.applicationContext;     // Catch: Exception -> L156
        ShortcutInfoCompat r07 = r06.build();     // Catch: Exception -> L156
        if (r32 != null) goto L134;
        IntentSender r7 = r10;
    L135:
        ShortcutManagerCompat.requestPinShortcut(r1, r07, r7);     // Catch: Exception -> L156
        return;
    L134:
        r7 = r32.getIntentSender();     // Catch: Exception -> L156
        goto L135
    L130:
        r32 = r10;
        goto L131
    L117:
        if (r8 == null) goto L123;
        if (r8.bot == false) goto L121;
        r06.setIcon(IconCompat.createWithResource(ApplicationLoader.applicationContext, R.drawable.book_bot));     // Catch: Exception -> L156
        goto L128
    L121:
        r06.setIcon(IconCompat.createWithResource(ApplicationLoader.applicationContext, R.drawable.book_user));     // Catch: Exception -> L156
        goto L128
    L123:
        if (ChatObject.isChannel(r9) == true) goto L125;
    L127:
        r06.setIcon(IconCompat.createWithResource(ApplicationLoader.applicationContext, R.drawable.book_group));     // Catch: Exception -> L156
        goto L128
    L125:
        if (r9.megagroup == true) goto L127;
        r06.setIcon(IconCompat.createWithResource(ApplicationLoader.applicationContext, R.drawable.book_channel));     // Catch: Exception -> L156
        goto L128
    L113:
        r05 = "bdid_";
    L60:
        if (r13 == false) goto L165;
        r123 = null;
    L66:
        if (r13 == true) goto L160;
        if (r123 != null) goto L160;
        r10 = null;
    L160:
        int r14 = AndroidUtilities.dp(58.0f);     // Catch: Throwable -> L105
        Bitmap r15 = Bitmap.createBitmap(r14, r14, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L105
        r15.eraseColor(0);     // Catch: Throwable -> L105
        Canvas r72 = new Canvas(r15);     // Catch: Throwable -> L105
        if (r13 == false) goto L81;
        AvatarDrawable r132 = new AvatarDrawable(r8);     // Catch: Throwable -> L76
        if (UserObject.isReplyUser(r8) == false) goto L78;
        r132.setAvatarType(12);     // Catch: Throwable -> L76
    L79:
        r132.setBounds(0, 0, r14, r14);     // Catch: Throwable -> L76
        r132.draw(r72);     // Catch: Throwable -> L76
        Bitmap r17 = r123;
    L96:
        Drawable r08 = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.book_logo);     // Catch: Throwable -> L103
        int r102 = AndroidUtilities.dp(15.0f);     // Catch: Throwable -> L103
        int r142 = r14 - r102;     // Catch: Throwable -> L103
        int r133 = r142 - AndroidUtilities.dp(2.0f);     // Catch: Throwable -> L103
        int r143 = r142 - AndroidUtilities.dp(2.0f);     // Catch: Throwable -> L103
        r08.setBounds(r133, r143, r133 + r102, r102 + r143);     // Catch: Throwable -> L103
        r08.draw(r72);     // Catch: Throwable -> L103
        r10 = null;
        r72.setBitmap(null);     // Catch: Throwable -> L100 Exception -> L159
    L100:
        th = th;
    L101:
        r123 = r17;
    L107:
        FileLog.e(th);     // Catch: Exception -> L156
    L102:
        r123 = r15;
    L103:
        th = th;
        r10 = null;
        goto L101
    L78:
        r132.setAvatarType(1);     // Catch: Throwable -> L76
        goto L79
    L81:
        Shader.TileMode r103 = Shader.TileMode.CLAMP;     // Catch: Throwable -> L105
        BitmapShader r134 = new BitmapShader(r123, r103, r103);     // Catch: Throwable -> L105
        if (roundPaint != null) goto L85;
        roundPaint = new Paint(1);     // Catch: Throwable -> L76
        bitmapRect = new RectF();     // Catch: Throwable -> L76
    L85:
        float r09 = r14 / r123.getWidth();     // Catch: Throwable -> L105
        r72.save();     // Catch: Throwable -> L105
        r72.scale(r09, r09);     // Catch: Throwable -> L105
        roundPaint.setShader(r134);     // Catch: Throwable -> L105
        r17 = r123;
        bitmapRect.set(0.0f, 0.0f, r123.getWidth(), r123.getHeight());     // Catch: Throwable -> L103
        if (ExteraConfig.getSquareFab() == true) goto L163;
        int r010 = r14;
    L95:
        float r011 = r010;
        r72.drawRoundRect(bitmapRect, r011, r011, roundPaint);     // Catch: Throwable -> L103
        r72.restore();     // Catch: Throwable -> L103
        goto L96
    L163:
        r010 = AndroidUtilities.dp(16.0f);     // Catch: Throwable -> L92
    L92:
        th = th;
        r123 = r17;
    L77:
        r10 = null;
    L76:
        th = th;
    L105:
        th = th;
        goto L77
    L165:
        r123 = BitmapFactory.decodeFile(getFileLoader().getPathToAttach(r122, true).toString());     // Catch: Throwable -> L63
    L63:
        th = th;
        r10 = null;
        r123 = null;
        goto L107
    L54:
        r122 = r12.photo_small;     // Catch: Exception -> L156
    L39:
        r13 = false;
        goto L56
    L35:
        if (r21 != SHORTCUT_TYPE_ATTACHED_BOT) goto L43;
        r11 = UserObject.getUserName(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r19)));     // Catch: Exception -> L156
        TLRPC.UserProfilePhoto r124 = r8.photo;     // Catch: Exception -> L156
        if (r124 == null) goto L40;
        r122 = r124.photo_small;     // Catch: Exception -> L156
        goto L39
    L43:
        if (UserObject.isReplyUser(r8) == false) goto L47;
        r11 = LocaleController.getString(R.string.RepliesTitle);     // Catch: Exception -> L156
    L45:
        r13 = true;
        goto L41
    L47:
        if (UserObject.isUserSelf(r8) == false) goto L49;
        r11 = LocaleController.getString(R.string.SavedMessages);     // Catch: Exception -> L156
        goto L45
    L49:
        r11 = ContactsController.formatName(r8.first_name, r8.last_name);     // Catch: Exception -> L156
        TLRPC.UserProfilePhoto r125 = r8.photo;     // Catch: Exception -> L156
        if (r125 == null) goto L40;
        r122 = r125.photo_small;     // Catch: Exception -> L156
        goto L39
    L22:
        if (DialogObject.isUserDialog(r19) == false) goto L25;
        r03 = getMessagesController().getUser(Long.valueOf(r19));     // Catch: Exception -> L156
        goto L20
    L25:
        if (DialogObject.isChatDialog(r19) == false) goto L153;
        r9 = getMessagesController().getChat(Long.valueOf(-r19));     // Catch: Exception -> L156
        r8 = null;
        goto L27
    L153:
        if (r22 == null) goto L177;
        r22.run(Boolean.FALSE);     // Catch: Exception -> L156
        return;
    L177:
        return;
    L7:
        r0 = createIntrnalAttachedBotShortcutIntent(r19);     // Catch: Exception -> L156
        goto L6
    }

    public boolean isLoadingStickers(int r1) {
        return this.loadingStickers[r1];
    }

    public boolean isMenuBotsUpdatedLocal() {
        return this.menuBotsUpdatedLocal;
    }

    public boolean isMessageFound(int r1, boolean r2) {
        if (this.searchServerResultMessagesMap[r2 ? 1 : 0].indexOfKey(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isSearchLoading() {
        if (this.reqId == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isShortcutAdded(long r3, int r5) {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
    L16:
        return false;
    L5:
        if (r5 != SHORTCUT_TYPE_USER_OR_CHAT) goto L7;
        String r2 = "sdid_";
    L8:
        String r22 = r2 + r3;
        List<ShortcutInfoCompat> r32 = ShortcutManagerCompat.getShortcuts(ApplicationLoader.applicationContext, 4);
        int r4 = 0;
    L10:
        if (r4 >= r32.size()) goto L16;
        if (r32.get(r4).getId().equals(r22) == true) goto L13;
        r4 = r4 + 1;
        goto L10
    L13:
        return true;
    L7:
        r2 = "bdid_";
        goto L8
    }

    public boolean isStickerInFavorites(TLRPC.Document r8) {
        if (r8 != null) goto L5;
        return false;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= this.recentStickers[2].size()) goto L15;
        TLRPC.Document r2 = this.recentStickers[2].get(r1);
        if (r2.id != r8.id) goto L14;
        if (r2.dc_id != r8.dc_id) goto L14;
        return true;
    L14:
        r1 = r1 + 1;
        goto L7
    L15:
        return false;
    }

    public boolean isStickerPackInstalled(long r2) {
        return isStickerPackInstalled(r2, true);
    }

    public boolean isStickerPackInstalled(long r3, boolean r5) {
        if (this.installedStickerSetsById.indexOfKey(r3) >= 0) goto L7;
        if (r5 == true) goto L6;
        return false;
    L6:
        if (this.installedForceStickerSetsById.contains(Long.valueOf(r3)) == true) goto L7;
        return false;
    L7:
        if (r5 == true) goto L9;
        return true;
    L9:
        if (this.uninstalledForceStickerSetsById.contains(Long.valueOf(r3)) == false) goto L17;
        return false;
    L17:
        return true;
    }

    public boolean isStickerPackInstalled(String r1) {
        return this.stickerSetsByName.containsKey(r1);
    }

    public boolean isStickerPackUnread(boolean r1, long r2) {
        return this.unreadStickerSets[r1 ? 1 : 0].contains(Long.valueOf(r2));
    }

    public void jumpToSearchedMessage(int r10, int r11) {
        if (r11 >= 0) goto L4;
        return;
    L4:
        if (r11 >= this.searchResultMessages.size()) goto L9;
        this.lastReturnedNum = r11;
        MessageObject r112 = this.searchResultMessages.get(r11);
        getNotificationCenter().postNotificationName(NotificationCenter.chatSearchResultsAvailable, new Object[]{Integer.valueOf(r10), Integer.valueOf(r112.getId()), Integer.valueOf(getMask()), Long.valueOf(r112.getDialogId()), Integer.valueOf(this.lastReturnedNum), Integer.valueOf(getSearchCount()), Boolean.TRUE});
        return;
    }

    public void loadArchivedStickersCount(int r4, boolean r5) {
        boolean r0 = false;
        if (r5 == false) goto L10;
        int r52 = MessagesController.getNotificationsSettings(this.currentAccount).getInt("archivedStickersCount" + r4, -1);
        if (r52 != (-1)) goto L8;
        loadArchivedStickersCount(r4, false);
        return;
    L8:
        this.archivedStickersCount[r4] = r52;
        getNotificationCenter().postNotificationName(NotificationCenter.archivedStickersCountDidLoad, new Object[]{Integer.valueOf(r4)});
        return;
    L10:
        TLRPC.TL_messages_getArchivedStickers r53 = new TLRPC.TL_messages_getArchivedStickers();
        r53.limit = 0;
        if (r4 != 1) goto L13;
        boolean r2 = true;
    L14:
        r53.masks = r2;
        if (r4 != 5) goto L17;
        r0 = true;
    L17:
        r53.emojis = r0;
        getConnectionsManager().sendRequest(r53, new MediaDataController$$ExternalSyntheticLambda136(this, r4));
        return;
    L13:
        r2 = false;
        goto L14
    }

    public void loadAttachMenuBots(boolean r2, boolean r3) {
        loadAttachMenuBots(r2, r3, null);
    }

    public void loadAttachMenuBots(boolean r3, boolean r4, Runnable r5) {
        this.isLoadingMenuBots = true;
        if (r3 == false) goto L6;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda46(this));
        return;
    L6:
        TLRPC.TL_messages_getAttachMenuBots r32 = new TLRPC.TL_messages_getAttachMenuBots();
        if (r4 == false) goto L9;
        long r0 = 0;
    L10:
        r32.hash = r0;
        getConnectionsManager().sendRequest(r32, new MediaDataController$$ExternalSyntheticLambda47(this, r5));
        return;
    L9:
        r0 = this.menuBotsUpdateHash;
        goto L10
    }

    public void loadBotInfo(long r9, long r11, boolean r13, int r14) {
        loadBotInfo(r9, r11, r13, r14, null);
    }

    public void loadBotInfo(long r9, long r11, boolean r13, int r14, Utilities.Callback<TL_bots.BotInfo> r15) {
        if (r13 == false) goto L7;
        TL_bots.BotInfo r132 = this.botInfos.get(r9 + "_" + r11);
        if (r132 == null) goto L7;
        getNotificationCenter().postNotificationName(NotificationCenter.botInfoDidLoad, new Object[]{r132, Integer.valueOf(r14)});
        return;
    L7:
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda88(this, r9, r11, r15, r14));
    }

    public void loadBotKeyboard(MessagesStorage.TopicKey r2) {
        loadBotKeyboard(r2, false);
    }

    public void loadBotKeyboard(MessagesStorage.TopicKey r3, boolean r4) {
        TLRPC.Message r0 = this.botKeyboards.get(r3);
        if (r0 == null) goto L6;
        getNotificationCenter().postNotificationName(NotificationCenter.botKeyboardDidLoad, new Object[]{r0, r3});
        return;
    L6:
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda197(this, r3, r4));
    }

    public void loadDraftsIfNeed() {
        if (getUserConfig().draftsLoaded == false) goto L5;
        return;
    L5:
        if (this.loadingDrafts == true) goto L10;
        this.loadingDrafts = true;
        getConnectionsManager().sendRequest(new TLRPC.TL_messages_getAllDrafts(), new MediaDataController$$ExternalSyntheticLambda41(this));
        return;
    }

    public void loadEmojiThemes() {
        int r2 = 0;
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("emojithemes_config_" + this.currentAccount, 0);
        int r1 = r0.getInt(NotificationBadge.NewHtcHomeBadger.COUNT, 0);
        ArrayList r3 = new ArrayList();
        r3.add(new ChatThemeBottomSheet.ChatThemeItem(EmojiThemes.createHomePreviewTheme(this.currentAccount)));
    L3:
        if (r2 >= r1) goto L14;
        SerializedData r5 = new SerializedData(Utilities.hexToBytes(r0.getString("theme_" + r2, _UrlKt.FRAGMENT_ENCODE_SET)));
        EmojiThemes r4 = EmojiThemes.createPreviewFullTheme(this.currentAccount, TLRPC.Theme.TLdeserialize(r5, r5.readInt32(true), true));     // Catch: Throwable -> L8
        if (r4.items.size() < 4) goto L10;
        r3.add(new ChatThemeBottomSheet.ChatThemeItem(r4));     // Catch: Throwable -> L8
    L10:
        ChatThemeController.chatThemeQueue.postRunnable(new AnonymousClass2(this, r3));     // Catch: Throwable -> L8
    L13:
        r2 = r2 + 1;
    L8:
        th = move-exception;
        FileLog.e(th);
        goto L13
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void loadFeaturedStickers(boolean r5, boolean r6) {
        boolean[] r0 = this.loadingFeaturedStickers;
        if (r0[r5 ? 1 : 0] == false) goto L5;
        return;
    L5:
        r0[r5 ? 1 : 0] = true;
        if (r6 == false) goto L9;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda167(this, r5));
        return;
    L9:
        if (r5 == false) goto L11;
        TLRPC.TL_messages_getFeaturedEmojiStickers r62 = new TLRPC.TL_messages_getFeaturedEmojiStickers();
        long r1 = this.loadFeaturedHash[1];
        r62.hash = r1;
        TLRPC.TL_messages_getFeaturedStickers r63 = r62;
    L12:
        getConnectionsManager().sendRequest(r63, new MediaDataController$$ExternalSyntheticLambda168(this, r5, r1));
        return;
    L11:
        TLRPC.TL_messages_getFeaturedStickers r64 = new TLRPC.TL_messages_getFeaturedStickers();
        r1 = this.loadFeaturedHash[0];
        r64.hash = r1;
        r63 = r64;
        goto L12
    }

    public void loadHints(boolean r4) {
        if (this.loading == false) goto L5;
        return;
    L5:
        if (getUserConfig().suggestContacts == true) goto L8;
        return;
    L8:
        if (r4 == true) goto L10;
        this.loading = true;
        TLRPC.TL_contacts_getTopPeers r42 = new TLRPC.TL_contacts_getTopPeers();
        r42.hash = 0;
        r42.bots_pm = false;
        r42.correspondents = true;
        r42.groups = false;
        r42.channels = false;
        r42.bots_inline = true;
        r42.bots_guestchat = true;
        r42.bots_app = true;
        r42.offset = 0;
        r42.limit = 20;
        getConnectionsManager().sendRequestTyped(r42, new AiTonesController$$ExternalSyntheticLambda0(), new MediaDataController$$ExternalSyntheticLambda3(this));
        return;
    L10:
        if (this.loaded == true) goto L18;
        this.loading = true;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda2(this));
        this.loaded = true;
        return;
    }

    public void loadMedia(long r17, int r19, int r20, int r21, int r22, long r23, int r25, int r26, int r27, ReactionsLayoutInBubble.VisibleReaction r28, String r29) {
        if (DialogObject.isChatDialog(r17) == false) goto L7;
        MediaDataController r0 = this;
        if (ChatObject.isChannel(-r17, r0.currentAccount) == false) goto L8;
        boolean r11 = true;
    L10:
        if (BuildVars.LOGS_ENABLED == false) goto L12;
        FileLog.d("load media did " + r17 + " count = " + r19 + " max_id " + r20 + " type = " + r22 + " cache = " + r25 + " classGuid = " + r26);
    L12:
        if (r25 == 0) goto L18;
        if (TextUtils.isEmpty(r29) == false) goto L18;
    L20:
        r0.loadMediaDatabase(r17, r19, r20, r21, r22, r23, r28, r26, r11, r25, r27);
        return;
    L18:
        if (DialogObject.isEncryptedDialog(r17) == true) goto L20;
        TLRPC.TL_messages_search r13 = new TLRPC.TL_messages_search();
        r13.limit = r19;
        if (r21 == 0) goto L25;
        r13.offset_id = r21;
        r13.add_offset = -r19;
    L27:
        if (r28 == null) goto L30;
        r13.flags |= 8;
        r13.saved_reaction.add(r28.toTLReaction());
    L30:
        if (r22 != 0) goto L33;
        r13.filter = new TLRPC.TL_inputMessagesFilterPhotoVideo();
    L53:
        if (TextUtils.isEmpty(r29) == true) goto L55;
        r13.q = r29;
    L56:
        r13.peer = getMessagesController().getInputPeer(r17);
        if (r23 == 0) goto L63;
        if (r17 != getUserConfig().getClientUserId()) goto L61;
        r13.saved_peer_id = getMessagesController().getInputPeer(r23);
        r13.flags |= 4;
        goto L63
    L61:
        r13.top_msg_id = (int) r23;
        r13.flags |= 2;
    L63:
        if (r13.peer != null) goto L65;
        return;
    L65:
        getConnectionsManager().bindRequestToGuid(getConnectionsManager().sendRequest(r13, new MediaDataController$$ExternalSyntheticLambda149(this, r17, r21, r19, r20, r22, r23, r26, r11, r27)), r26);
        return;
    L55:
        r13.q = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L56
    L33:
        if (r22 != 6) goto L36;
        r13.filter = new TLRPC.TL_inputMessagesFilterPhotos();
        goto L53
    L36:
        if (r22 != 7) goto L38;
        r13.filter = new TLRPC.TL_inputMessagesFilterVideo();
        goto L53
    L38:
        if (r22 != 1) goto L40;
        r13.filter = new TLRPC.TL_inputMessagesFilterDocument();
        goto L53
    L40:
        if (r22 != 2) goto L43;
        r13.filter = new TLRPC.TL_inputMessagesFilterRoundVoice();
        goto L53
    L43:
        if (r22 != 3) goto L45;
        r13.filter = new TLRPC.TL_inputMessagesFilterUrl();
        goto L53
    L45:
        if (r22 != 4) goto L48;
        r13.filter = new TLRPC.TL_inputMessagesFilterMusic();
        goto L53
    L48:
        if (r22 != 5) goto L50;
        r13.filter = new TLRPC.TL_inputMessagesFilterGif();
        goto L53
    L50:
        if (r22 != 8) goto L53;
        r13.filter = new TLRPC.TL_inputMessagesFilterPoll();
        goto L53
    L25:
        r13.offset_id = r20;
    L8:
        r11 = false;
        goto L10
    L7:
        r0 = this;
        goto L8
    }

    public void loadMoreSearchMessages(boolean r17) {
        if (this.loadingMoreSearchMessages == false) goto L5;
        return;
    L5:
        if (this.reqId != 0) goto L16;
        boolean[] r1 = this.messagesSearchEndReached;
        if (r1[0] == true) goto L9;
    L13:
        int r15 = this.lastReturnedNum;
        this.lastReturnedNum = this.searchResultMessages.size();
        this.loadingMoreSearchMessages = true;
        searchMessagesInChat(null, this.lastDialogId, this.lastMergeDialogId, this.lastGuid, 1, this.lastReplyMessageId, false, this.lastSearchUser, this.lastSearchChat, false, this.lastReaction);
        this.lastReturnedNum = r15;
        return;
    L9:
        if (this.lastMergeDialogId != 0) goto L13;
        if (r1[1] == false) goto L13;
        return;
    }

    public void loadMusic(long r10, long r12, long r14) {
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda17(this, r10, r12, r14));
    }

    public ArrayList<MessageObject> loadPinnedMessages(long r8, long r10, ArrayList<Integer> r12, boolean r13) {
        if (r13 == false) goto L6;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda38(this, r8, r10, r12));
        return null;
    L6:
        return loadPinnedMessageInternal(r8, r10, r12, true);
    }

    public void loadPinnedMessages(long r10, int r12, int r13) {
        if (this.loadingPinnedMessages.indexOfKey(r10) < 0) goto L5;
        return;
    L5:
        this.loadingPinnedMessages.put(r10, Boolean.TRUE);
        TLRPC.TL_messages_search r5 = new TLRPC.TL_messages_search();
        r5.peer = getMessagesController().getInputPeer(r10);
        r5.limit = 40;
        r5.offset_id = r12;
        r5.q = _UrlKt.FRAGMENT_ENCODE_SET;
        r5.filter = new TLRPC.TL_inputMessagesFilterPinned();
        getConnectionsManager().sendRequest(r5, new MediaDataController$$ExternalSyntheticLambda45(this, r13, r5, r10, r12));
    }

    public void loadPremiumPromo(boolean r3) {
        this.isLoadingPremiumPromo = true;
        if (r3 == false) goto L6;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda215(this));
        return;
    L6:
        TLRPC.TL_help_getPremiumPromo r32 = new TLRPC.TL_help_getPremiumPromo();
        getConnectionsManager().sendRequest(r32, new MediaDataController$$ExternalSyntheticLambda216(this));
    }

    public void loadReactions(boolean r2, Integer r3) {
        this.isLoadingReactions = true;
        if (r2 == false) goto L6;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda20(this));
        return;
    L6:
        TLRPC.TL_messages_getAvailableReactions r22 = new TLRPC.TL_messages_getAvailableReactions();
        if (r3 == null) goto L9;
        int r32 = r3.intValue();
    L10:
        r22.hash = r32;
        getConnectionsManager().sendRequest(r22, new MediaDataController$$ExternalSyntheticLambda21(this));
        return;
    L9:
        r32 = this.reactionsUpdateHash;
        goto L10
    }

    public void loadRecentAndTopReactions(boolean r9) {
        if (this.loadingRecentReactions == false) goto L5;
        return;
    L5:
        if (this.loadedRecentReactions == false) goto L8;
        if (r9 == true) goto L8;
        return;
    L8:
        SharedPreferences r92 = ApplicationLoader.applicationContext.getSharedPreferences("recent_reactions_" + this.currentAccount, 0);
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("top_reactions_" + this.currentAccount, 0);
        this.recentReactions.clear();
        this.topReactions.clear();
        this.recentReactions.addAll(loadReactionsFromPref(r92));
        this.topReactions.addAll(loadReactionsFromPref(r0));
        this.loadingRecentReactions = true;
        this.loadedRecentReactions = true;
        boolean[] r1 = new boolean[2];
        TLRPC.TL_messages_getRecentReactions r2 = new TLRPC.TL_messages_getRecentReactions();
        r2.hash = r92.getLong("hash", 0);
        r2.limit = 50;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new MediaDataController$$ExternalSyntheticLambda43(this, r92, r1));
        TLRPC.TL_messages_getTopReactions r93 = new TLRPC.TL_messages_getTopReactions();
        r93.hash = r0.getLong("hash", 0);
        r93.limit = 100;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r93, new MediaDataController$$ExternalSyntheticLambda44(this, r0, r1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v14, types: [org.telegram.tgnet.TLRPC$TL_messages_getFavedStickers] */
    /* JADX WARN: Type inference failed for: r9v7, types: [org.telegram.tgnet.TLRPC$TL_messages_getStickers] */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9, types: [org.telegram.tgnet.TLRPC$TL_messages_getStickers] */
    public void loadRecents(int r7, boolean r8, boolean r9, boolean r10) {
        boolean r0 = false;
        if (r8 == true) goto L5;
        boolean[] r2 = this.loadingRecentStickers;
        if (r2[r7] == false) goto L13;
        return;
    L13:
        r2[r7] = true;
        if (this.recentStickersLoaded[r7] == true) goto L9;
    L16:
        if (r9 == false) goto L19;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda211(this, r8, r7));
        return;
    L19:
        SharedPreferences r92 = MessagesController.getEmojiSettings(this.currentAccount);
        if (r10 == false) goto L22;
    L43:
        if (r8 == false) goto L47;
        TLRPC.TL_messages_getSavedGifs r82 = new TLRPC.TL_messages_getSavedGifs();
        r82.hash = calcDocumentsHash(this.recentGifs);
        getConnectionsManager().sendRequest(r82, new MediaDataController$$ExternalSyntheticLambda212(this, r7));
        return;
    L47:
        if (r7 != 2) goto L50;
        ?? r83 = new TLRPC.TL_messages_getFavedStickers();
        r83.hash = calcDocumentsHash(this.recentStickers[r7]);
        TLRPC.TL_messages_getRecentStickers r84 = r83;
    L59:
        getConnectionsManager().sendRequest(r84, new MediaDataController$$ExternalSyntheticLambda213(this, r7));
        return;
    L50:
        if (r7 != 3) goto L53;
        ?? r93 = new TLRPC.TL_messages_getStickers();
        r93.emoticon = "👋" + Emoji.fixEmoji("⭐");
        r93.hash = calcDocumentsHash(this.recentStickers[r7]);
    L52:
        r84 = r93;
        goto L59
    L53:
        if (r7 != 7) goto L55;
        r93 = new TLRPC.TL_messages_getStickers();
        r93.emoticon = "📂" + Emoji.fixEmoji("⭐");
        r93.hash = calcDocumentsHash(this.recentStickers[r7]);
        goto L52
    L55:
        TLRPC.TL_messages_getRecentStickers r85 = new TLRPC.TL_messages_getRecentStickers();
        r85.hash = calcDocumentsHash(this.recentStickers[r7]);
        if (r7 != 1) goto L58;
        r0 = true;
    L58:
        r85.attached = r0;
        r84 = r85;
        goto L59
    L22:
        if (r8 == false) goto L24;
        long r94 = r92.getLong("lastGifLoadTime", 0);
    L37:
        if (Math.abs(System.currentTimeMillis() - r94) >= DurationKt.MILLIS_IN_HOUR) goto L43;
        if (r8 == false) goto L41;
        this.loadingRecentGifs = false;
        return;
    L41:
        this.loadingRecentStickers[r7] = false;
        return;
    L24:
        if (r7 != 0) goto L26;
        r94 = r92.getLong("lastStickersLoadTime", 0);
        goto L37
    L26:
        if (r7 != 1) goto L28;
        r94 = r92.getLong("lastStickersLoadTimeMask", 0);
        goto L37
    L28:
        if (r7 != 3) goto L31;
        r94 = r92.getLong("lastStickersLoadTimeGreet", 0);
        goto L37
    L31:
        if (r7 != 5) goto L33;
        r94 = r92.getLong("lastStickersLoadTimeEmojiPacks", 0);
        goto L37
    L33:
        if (r7 != 7) goto L35;
        r94 = r92.getLong("lastStickersLoadTimePremiumStickers", 0);
        goto L37
    L35:
        r94 = r92.getLong("lastStickersLoadTimeFavs", 0);
    L9:
        r9 = false;
        goto L16
    L5:
        if (this.loadingRecentGifs == true) goto L61;
        this.loadingRecentGifs = true;
        if (this.recentGifsLoaded == false) goto L16;
    }

    public void loadReplyIcons() {
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("replyicons_" + this.currentAccount, 0);
        TLRPC.TL_emojiList r2 = null;
        String r1 = r0.getString("replyicons", null);
        long r3 = r0.getLong("replyicons_last_check", 0);
        if (r1 == null) goto L13;
        SerializedData r5 = new SerializedData(Utilities.hexToBytes(r1));
        TLRPC.TL_emojiList r12 = (TLRPC.TL_emojiList) TLRPC.EmojiList.TLdeserialize(r5, r5.readInt32(true), true);     // Catch: Throwable -> L10
        this.replyIconsDefault = r12;     // Catch: Throwable -> L8
    L7:
        r2 = r12;
    L8:
        Throwable th = th;
    L12:
        FileLog.e(th);
    L10:
        th = move-exception;
        th = th;
        r12 = null;
    L13:
        if (r2 != null) goto L15;
    L18:
        TL_account.getDefaultBackgroundEmojis r13 = new TL_account.getDefaultBackgroundEmojis();
        if (r2 == null) goto L21;
        r13.hash = r2.hash;
    L21:
        getConnectionsManager().sendRequest(r13, new MediaDataController$$ExternalSyntheticLambda31(this, r0));
        return;
    L15:
        if ((System.currentTimeMillis() - r3) > DurationKt.MILLIS_IN_DAY) goto L18;
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L18;
    }

    public void loadReplyMessagesForMessages(ArrayList<MessageObject> r21, long r22, int r24, long r25, Runnable r27, int r28, Timer r29) {
        ArrayList<MessageObject> r0 = r21;
        int r1 = 0;
        boolean r2 = true;
        if (r24 != 1) goto L5;
        boolean r10 = true;
    L7:
        if (DialogObject.isEncryptedDialog(r22) == false) goto L32;
        Timer.Task r23 = Timer.start(r29, "loadReplyMessagesForMessages: (encrypted) finding messages to load");
        ArrayList r4 = new ArrayList();
        LongSparseArray r3 = new LongSparseArray();
    L10:
        if (r1 >= r0.size()) goto L26;
        MessageObject r5 = r0.get(r1);
        if (r5 == null) goto L24;
        if (r5.isReply() == false) goto L24;
        if (r5.replyMessageObject != null) goto L24;
        long r8 = r5.messageOwner.reply_to.reply_to_random_id;
        ArrayList r6 = (ArrayList) r3.get(r8);
        if (r6 != null) goto L21;
        r6 = new ArrayList();
        r3.put(r8, r6);
    L21:
        r6.add(r5);
        if (r4.contains(Long.valueOf(r8)) == true) goto L24;
        r4.add(Long.valueOf(r8));
    L24:
        r1 = r1 + 1;
        goto L10
    L26:
        if (r4.isEmpty() == false) goto L30;
        if (r27 == null) goto L226;
        r27.run();
        return;
    L226:
        return;
    L30:
        Timer.done(r23);
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda187(this, Timer.start(r29, "loadReplyMessagesForMessages (encrypted) storageQueue.postRunnable"), r29, r4, r22, r3, r27));
        return;
    L32:
        LongSparseArray r82 = new LongSparseArray();
        LongSparseArray r9 = new LongSparseArray();
        Timer.Task r42 = Timer.start(r29, "loadReplyMessagesForMessages: filling replies from the same array");
        int r52 = 0;
    L33:
        LongSparseArray r13 = null;
        if (r52 >= r0.size()) goto L75;
        MessageObject r62 = r0.get(r52);
        if (r62 != null) goto L39;
    L37:
        boolean r16 = r2;
    L74:
        r52 = r52 + 1;
        r2 = r16;
        goto L33
    L39:
        if (r62.isReplyToStory() == true) goto L37;
        if (r62.isReply() == false) goto L37;
        if (r62.getRealId() <= 0) goto L37;
        TLRPC.MessageReplyHeader r14 = r62.messageOwner.reply_to;
        if (r14.reply_to_peer_id != null) goto L37;
        int r142 = r14.reply_to_msg_id;
        int r15 = 0;
    L49:
        if (r15 >= r0.size()) goto L59;
        if (r52 != r15) goto L53;
    L73:
        r15 = r15 + 1;
        goto L49
    L53:
        if (r0.get(r15) == null) goto L73;
        if (r0.get(r15).getRealId() != r142) goto L73;
        r62.replyMessageObject = r0.get(r15);
        r62.applyTimestampsHighlightForReplyMsg();
        TLRPC.MessageAction r26 = r62.messageOwner.action;
        if ((r26 instanceof TLRPC.TL_messageActionPinMessage) == false) goto L61;
        r62.generatePinMessageText(null, null);
        goto L59
    L61:
        if ((r26 instanceof TLRPC.TL_messageActionGameScore) == false) goto L64;
        r62.generateGameMessageText(null);
        goto L59
    L64:
        if ((r26 instanceof TLRPC.TL_messageActionPaymentSent) == false) goto L67;
        r62.generatePaymentSentMessageText(null, false);
        goto L59
    L67:
        if ((r26 instanceof TLRPC.TL_messageActionPaymentSentMe) == false) goto L70;
        boolean r143 = true;
        r62.generatePaymentSentMessageText(null, true);
    L69:
        r16 = r143;
        goto L74
    L70:
        r143 = true;
        if ((r26 instanceof TLRPC.TL_messageActionSuggestedPostApproval) == false) goto L69;
        r62.generateSuggestionApprovalMessageText();
    L59:
        r16 = true;
        goto L74
    L75:
        Timer.done(r42);
        Timer.Task r210 = Timer.start(r29, "loadReplyMessagesForMessages: gathering ids of missing reply data");
        int r43 = 0;
    L77:
        if (r43 >= r0.size()) goto L196;
        MessageObject r53 = r0.get(r43);
        if (r53 != null) goto L81;
        Timer.Task r242 = r210;
        LongSparseArray r17 = r13;
    L139:
        int r19 = r43;
        r13 = r17;
    L195:
        r43 = r19 + 1;
        r0 = r21;
        r210 = r242;
        goto L77
    L81:
        int r63 = r53.type;
        if (r63 != 23) goto L84;
    L85:
        r242 = r210;
        r19 = r43;
        r17 = r13;
        TLRPC.MessageMedia r02 = r53.messageOwner.media;
        if (r02.storyItem != null) goto L166;
        long r03 = DialogObject.getPeerDialogId(r02.peer);
        if (r17 != null) goto L157;
        r13 = new LongSparseArray();
    L158:
        ArrayList r211 = (ArrayList) r13.get(r03);
        if (r211 != null) goto L161;
        r211 = new ArrayList();
        r13.put(r03, r211);
    L161:
        StringBuilder r44 = new StringBuilder("+story did=");
        r44.append(r03);
        r44.append(" at ");
        if (r53.type != 23) goto L164;
        String r04 = "forwarded";
    L165:
        r44.append(r04);
        r44.append(" #");
        r44.append(r53.getId());
        Timer.log(r29, r44.toString());
        r211.add(r53);
    L169:
        if (r53.type != 0) goto L195;
        TLRPC.Message r05 = r53.messageOwner;
        if (r05 == null) goto L195;
        TLRPC.MessageMedia r06 = r05.media;
        if (r06 == null) goto L195;
        TLRPC.WebPage r07 = r06.webpage;
        if (r07 == null) goto L195;
        if (r07.attributes == null) goto L195;
        int r08 = 0;
    L180:
        if (r08 >= r53.messageOwner.media.webpage.attributes.size()) goto L195;
        TLRPC.WebPageAttribute r12 = r53.messageOwner.media.webpage.attributes.get(r08);
        if ((r12 instanceof TLRPC.TL_webPageAttributeStory) == false) goto L192;
        TLRPC.TL_webPageAttributeStory r18 = (TLRPC.TL_webPageAttributeStory) r12;
        TL_stories.StoryItem r212 = r18.storyItem;
        TLRPC.Peer r45 = r18.peer;
        if (r212 != null) goto L193;
        long r110 = DialogObject.getPeerDialogId(r45);
        if (r13 != null) goto L188;
        r13 = new LongSparseArray();
    L188:
        ArrayList r46 = (ArrayList) r13.get(r110);
        if (r46 != null) goto L191;
        r46 = new ArrayList();
        r13.put(r110, r46);
    L191:
        Timer.log(r29, "+story did=" + r110 + " at webpage of #" + r53.getId());
        r46.add(r53);
        goto L192
    L193:
        MessageObject r213 = r53;
        r18.storyItem = StoriesStorage.checkExpiredStateLocal(this.currentAccount, DialogObject.getPeerDialogId(r45), r18.storyItem);
    L194:
        r08 = r08 + 1;
        r53 = r213;
    L192:
        r213 = r53;
        goto L194
    L164:
        r04 = "mentioned";
        goto L165
    L157:
        r13 = r17;
        goto L158
    L166:
        long r09 = DialogObject.getPeerDialogId(r02.peer);
        TLRPC.MessageMedia r214 = r53.messageOwner.media;
        r214.storyItem = StoriesStorage.checkExpiredStateLocal(this.currentAccount, r09, r214.storyItem);
    L167:
        r13 = r17;
        goto L169
    L84:
        if (r63 == 24) goto L85;
        if (r53.getRealId() > 0) goto L89;
    L101:
        r242 = r210;
        if (r53.getRealId() > 0) goto L104;
    L100:
        r19 = r43;
        r17 = r13;
        goto L167
    L104:
        if (r53.isReply() == false) goto L100;
        TLRPC.Message r010 = r53.messageOwner;
        TLRPC.MessageReplyHeader r111 = r010.reply_to;
        int r215 = r111.reply_to_msg_id;
        r17 = r13;
        if (r215 == r25) goto L139;
        TLRPC.Peer r112 = r111.reply_to_peer_id;
        if (r112 == null) goto L113;
        long r182 = 0;
        long r132 = r112.channel_id;
        if (r132 != 0) goto L117;
    L116:
        r132 = r182;
    L117:
        MessageObject r113 = r53.replyMessageObject;
        if (r113 == null) goto L130;
        TLRPC.Message r114 = r113.messageOwner;
        if (r114 == null) goto L139;
        TLRPC.Peer r115 = r114.peer_id;
        if (r115 == null) goto L139;
        if ((r010 instanceof TLRPC.TL_messageEmpty) == true) goto L139;
        if (r115.channel_id == r132) goto L139;
    L130:
        if (r22 != UserObject.REPLY_BOT) goto L132;
    L140:
        SparseArray r011 = (SparseArray) r82.get(r22);
        ArrayList r116 = (ArrayList) r9.get(r132);
        if (r011 != null) goto L143;
        r011 = new SparseArray();
        r82.put(r22, r011);
    L143:
        if (r116 != null) goto L145;
        r116 = new ArrayList();
        r9.put(r132, r116);
    L145:
        ArrayList r183 = (ArrayList) r011.get(r215);
        r19 = r43;
        if (r183 != null) goto L150;
        ArrayList r47 = new ArrayList();
        r011.put(r215, r47);
        if (r116.contains(Integer.valueOf(r215)) == true) goto L151;
        r116.add(Integer.valueOf(r215));
    L151:
        r47.add(r53);
        Timer.log(r29, "+message did=" + (-r132) + " mid=" + r215 + " at replied #" + r53.getId());
        goto L167
    L150:
        r47 = r183;
        goto L151
    L132:
        if (r132 == r182) goto L140;
        if (r53.getDialogId() == (-r132)) goto L140;
        TLRPC.Chat r012 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r132));
        if (r012 == null) goto L140;
        if (ChatObject.isPublic(r012) == true) goto L140;
    L113:
        r182 = 0;
        r132 = r010.peer_id.channel_id;
        if (r132 == 0) goto L116;
    L89:
        if (r53.isReplyToStory() == false) goto L101;
        TLRPC.Message r117 = r53.messageOwner;
        if (r117.replyStory != null) goto L99;
        long r013 = DialogObject.getPeerDialogId(r117.reply_to.peer);
        if (r13 != null) goto L95;
        r13 = new LongSparseArray();
    L95:
        ArrayList r144 = (ArrayList) r13.get(r013);
        if (r144 != null) goto L98;
        r144 = new ArrayList();
        r13.put(r013, r144);
    L98:
        r242 = r210;
        Timer.log(r29, "+story did=" + r013 + " at replied #" + r53.getId());
        r144.add(r53);
        r19 = r43;
        goto L169
    L99:
        r242 = r210;
        long r014 = DialogObject.getPeerDialogId(r117.reply_to.peer);
        TLRPC.Message r216 = r53.messageOwner;
        r216.replyStory = StoriesStorage.checkExpiredStateLocal(this.currentAccount, r014, r216.replyStory);
        goto L100
    L196:
        LongSparseArray r172 = r13;
        Timer.done(r210);
        if (r82.isEmpty() == false) goto L202;
        if (r172 != null) goto L202;
        if (r27 == null) goto L227;
        r27.run();
        return;
    L227:
        return;
    L202:
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda188(this, Timer.start(r29, "loadReplyMessagesForMessages: storageQueue.postRunnable"), r172, new AtomicInteger(2), r27, r28, r29, r82, r9, r10, r22));
        return;
    L5:
        r10 = false;
        goto L7
    }

    public void loadRestrictedStatusEmojis() {
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("restrictedstatuses_" + this.currentAccount, 0);
        TLRPC.TL_emojiList r2 = null;
        String r1 = r0.getString("restrictedstatuses", null);
        long r3 = r0.getLong("restrictedstatuses_last_check", 0);
        if (r1 == null) goto L13;
        SerializedData r5 = new SerializedData(Utilities.hexToBytes(r1));
        TLRPC.TL_emojiList r12 = (TLRPC.TL_emojiList) TLRPC.EmojiList.TLdeserialize(r5, r5.readInt32(true), true);     // Catch: Throwable -> L10
        this.restrictedStatusEmojis = r12;     // Catch: Throwable -> L8
    L7:
        r2 = r12;
    L8:
        Throwable th = th;
    L12:
        FileLog.e(th);
    L10:
        th = move-exception;
        th = th;
        r12 = null;
    L13:
        if (r2 != null) goto L15;
    L16:
        TL_account.getChannelRestrictedStatusEmojis r13 = new TL_account.getChannelRestrictedStatusEmojis();
        if (r2 == null) goto L19;
        r13.hash = r2.hash;
    L19:
        getConnectionsManager().sendRequest(r13, new MediaDataController$$ExternalSyntheticLambda102(this, r0));
        return;
    L15:
        if ((System.currentTimeMillis() - r3) > DurationKt.MILLIS_IN_DAY) goto L16;
    }

    public void loadSavedReactions(boolean r5) {
        if (this.loadingSavedReactions == false) goto L5;
        return;
    L5:
        if (this.loadedSavedReactions == false) goto L8;
        if (r5 == true) goto L8;
        return;
    L8:
        SharedPreferences r52 = ApplicationLoader.applicationContext.getSharedPreferences("saved_reactions_" + this.currentAccount, 0);
        this.savedReactions.clear();
        this.savedReactions.addAll(loadReactionsFromPref(r52));
        this.loadingSavedReactions = true;
        this.loadedSavedReactions = true;
        TLRPC.TL_messages_getDefaultTagReactions r0 = new TLRPC.TL_messages_getDefaultTagReactions();
        r0.hash = r52.getLong("hash", 0);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new MediaDataController$$ExternalSyntheticLambda132(this, r52));
    }

    public void loadStickers(int r7, boolean r8, boolean r9) {
        loadStickers(r7, r8, r9, false, null);
    }

    public void loadStickers(int r7, boolean r8, boolean r9, boolean r10) {
        loadStickers(r7, r8, r9, r10, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void loadStickers(int r7, boolean r8, boolean r9, boolean r10, Utilities.Callback<ArrayList<TLRPC.TL_messages_stickerSet>> r11) {
        if (this.loadingStickers[r7] == false) goto L10;
        if (r10 == false) goto L7;
        this.scheduledLoadStickers[r7] = new MediaDataController$$ExternalSyntheticLambda191(this, r7, r9, r11);
        return;
    L7:
        if (r11 == null) goto L70;
        r11.run(null);
        return;
    L70:
        return;
    L10:
        int r3 = 0;
        char r4 = 1;
        if (r7 == 3) goto L13;
        if (r7 == 6) goto L21;
        if (r7 == 4) goto L29;
        loadArchivedStickersCount(r7, r8);
    L29:
        this.loadingStickers[r7] = true;
        if (r8 == false) goto L33;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda192(this, r7, r11));
        return;
    L33:
        if (r7 == 3) goto L61;
        if (r7 == 6) goto L61;
        if (r7 != 4) goto L39;
        TLRPC.TL_messages_getStickerSet r82 = new TLRPC.TL_messages_getStickerSet();
        r82.stickerset = new TLRPC.TL_inputStickerSetAnimatedEmoji();
        getConnectionsManager().sendRequest(r82, new MediaDataController$$ExternalSyntheticLambda194(this, r7, r11));
        return;
    L39:
        long r0 = 0;
        if (r7 != 0) goto L48;
        TLRPC.TL_messages_getAllStickers r83 = new TLRPC.TL_messages_getAllStickers();
        if (r9 == true) goto L45;
        r0 = this.loadHash[r7];
    L45:
        r83.hash = r0;
        TLRPC.TL_messages_getMaskStickers r84 = r83;
    L59:
        getConnectionsManager().sendRequest(r84, new MediaDataController$$ExternalSyntheticLambda195(this, r7, r11, r0));
        return;
    L48:
        if (r7 != 5) goto L54;
        TLRPC.TL_messages_getEmojiStickers r85 = new TLRPC.TL_messages_getEmojiStickers();
        if (r9 == true) goto L53;
        r0 = this.loadHash[r7];
    L53:
        r85.hash = r0;
        r84 = r85;
        goto L59
    L54:
        TLRPC.TL_messages_getMaskStickers r86 = new TLRPC.TL_messages_getMaskStickers();
        if (r9 == true) goto L58;
        r0 = this.loadHash[r7];
    L58:
        r86.hash = r0;
        r84 = r86;
    L61:
        if (r7 == 6) goto L64;
        r4 = 0;
    L64:
        TLRPC.TL_messages_allStickers r87 = new TLRPC.TL_messages_allStickers();
        r87.hash2 = this.loadFeaturedHash[r4];
        int r92 = this.featuredStickerSets[r4].size();
    L65:
        if (r3 >= r92) goto L67;
        r87.sets.add(this.featuredStickerSets[r4].get(r3).set);
        r3 = r3 + 1;
        goto L65
    L67:
        processLoadStickersResponse(r7, r87, new MediaDataController$$ExternalSyntheticLambda193(r11));
        return;
    L21:
        if (this.featuredStickerSets[1].isEmpty() == false) goto L23;
    L24:
        if (r11 == null) goto L72;
        r11.run(null);
        return;
    L72:
        return;
    L23:
        if (getMessagesController().preloadFeaturedStickers == true) goto L29;
    L13:
        if (this.featuredStickerSets[0].isEmpty() == false) goto L15;
    L16:
        if (r11 == null) goto L71;
        r11.run(null);
        return;
    L71:
        return;
    L15:
        if (getMessagesController().preloadFeaturedStickers == true) goto L29;
        goto L16
    }

    public void loadStickersByEmojiOrName(String r3, boolean r4, boolean r5) {
        if (this.loadingDiceStickerSets.contains(r3) == true) goto L20;
        if (r4 == true) goto L6;
    L8:
        this.loadingDiceStickerSets.add(r3);
        if (r5 == false) goto L12;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda208(this, r3, r4));
        return;
    L12:
        TLRPC.TL_messages_getStickerSet r52 = new TLRPC.TL_messages_getStickerSet();
        if (Objects.equals(getUserConfig().premiumGiftsStickerPack, r3) == false) goto L15;
        r52.stickerset = new TLRPC.TL_inputStickerSetPremiumGifts();
    L18:
        getConnectionsManager().sendRequest(r52, new MediaDataController$$ExternalSyntheticLambda209(this, r3, r4));
        return;
    L15:
        if (r4 == false) goto L17;
        TLRPC.TL_inputStickerSetDice r0 = new TLRPC.TL_inputStickerSetDice();
        r0.emoticon = r3;
        r52.stickerset = r0;
        goto L18
    L17:
        TLRPC.TL_inputStickerSetShortName r02 = new TLRPC.TL_inputStickerSetShortName();
        r02.short_name = r3;
        r52.stickerset = r02;
        goto L18
    L6:
        if (this.diceStickerSetsByEmoji.get(r3) == null) goto L8;
        return;
    }

    public void markFeaturedStickersAsRead(boolean r10, boolean r11) {
        if (this.unreadStickerSets[r10 ? 1 : 0].isEmpty() == true) goto L14;
        this.unreadStickerSets[r10 ? 1 : 0].clear();
        long[] r0 = this.loadFeaturedHash;
        long r1 = calcFeaturedStickersHash(r10, this.featuredStickerSets[r10 ? 1 : 0]);
        r0[r10 ? 1 : 0] = r1;
        NotificationCenter r02 = getNotificationCenter();
        if (r10 == false) goto L8;
        int r12 = NotificationCenter.featuredEmojiDidLoad;
    L9:
        r02.postNotificationName(r12, new Object[0]);
        putFeaturedStickersToCache(r10, this.featuredStickerSets[r10 ? 1 : 0], this.unreadStickerSets[r10 ? 1 : 0], this.loadFeaturedDate[r10 ? 1 : 0], this.loadFeaturedHash[r10 ? 1 : 0], this.loadFeaturedPremium);
        if (r11 == false) goto L13;
        TLRPC.TL_messages_readFeaturedStickers r9 = new TLRPC.TL_messages_readFeaturedStickers();
        getConnectionsManager().sendRequest(r9, new MediaDataController$$ExternalSyntheticLambda48());
        return;
    L13:
        return;
    L8:
        r12 = NotificationCenter.featuredStickersDidLoad;
        goto L9
    }

    public void markFeaturedStickersByIdAsRead(boolean r4, long r5) {
        if (this.unreadStickerSets[r4 ? 1 : 0].contains(Long.valueOf(r5)) == true) goto L5;
        return;
    L5:
        if (this.readingStickerSets[r4 ? 1 : 0].contains(Long.valueOf(r5)) == true) goto L10;
        this.readingStickerSets[r4 ? 1 : 0].add(Long.valueOf(r5));
        TLRPC.TL_messages_readFeaturedStickers r0 = new TLRPC.TL_messages_readFeaturedStickers();
        r0.id.add(Long.valueOf(r5));
        getConnectionsManager().sendRequest(r0, new MediaDataController$$ExternalSyntheticLambda221());
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda222(this, r4, r5), 1000);
        return;
    }

    public void markSetInstalling(long r3, boolean r5) {
        this.uninstalledForceStickerSetsById.remove(Long.valueOf(r3));
        if (r5 == true) goto L5;
    L7:
        if (r5 == true) goto L10;
        this.installedForceStickerSetsById.remove(Long.valueOf(r3));
        return;
    L10:
        return;
    L5:
        if (this.installedForceStickerSetsById.contains(Long.valueOf(r3)) == true) goto L7;
        this.installedForceStickerSetsById.add(Long.valueOf(r3));
        goto L7
    }

    public void markSetUninstalling(long r3, boolean r5) {
        this.installedForceStickerSetsById.remove(Long.valueOf(r3));
        if (r5 == true) goto L5;
    L7:
        if (r5 == true) goto L10;
        this.uninstalledForceStickerSetsById.remove(Long.valueOf(r3));
        return;
    L10:
        return;
    L5:
        if (this.uninstalledForceStickerSetsById.contains(Long.valueOf(r3)) == true) goto L7;
        this.uninstalledForceStickerSetsById.add(Long.valueOf(r3));
        goto L7
    }

    public void moveStickerSetToTop(long r5, boolean r7, boolean r8) {
        if (r7 == false) goto L5;
        int r72 = 5;
    L8:
        ArrayList<TLRPC.TL_messages_stickerSet> r82 = getStickerSets(r72);
        if (r82 == null) goto L18;
        int r1 = 0;
    L12:
        if (r1 >= r82.size()) goto L21;
        if (r82.get(r1).set.id == r5) goto L15;
        r1 = r1 + 1;
        goto L12
    L15:
        TLRPC.TL_messages_stickerSet r52 = r82.get(r1);
        r82.remove(r1);
        r82.add(0, r52);
        getNotificationCenter().postNotificationName(NotificationCenter.stickersDidLoad, new Object[]{Integer.valueOf(r72), Boolean.FALSE});
        return;
    L21:
        return;
    L18:
        return;
    L5:
        if (r8 == false) goto L7;
        r72 = 1;
        goto L8
    L7:
        r72 = 0;
        goto L8
    }

    public void onRingtoneUploaded(String r2, TLRPC.Document r3, boolean r4) {
        this.ringtoneUploaderHashMap.remove(r2);
        this.ringtoneDataStore.onRingtoneUploaded(r2, r3, r4);
    }

    public void portSavedSearchResults(int r8, ReactionsLayoutInBubble.VisibleReaction r9, String r10, ArrayList<MessageObject> r11, ArrayList<MessageObject> r12, int r13, int r14, boolean r15) {
        this.lastReaction = r9;
        this.lastSearchQuery = r10;
        boolean[] r92 = this.messagesSearchEndReached;
        r92[0] = r15;
        r92[1] = true;
        this.searchServerResultMessages.clear();
        this.searchServerResultMessages.addAll(r12);
        this.searchLocalResultMessages.clear();
        this.searchLocalResultMessages.addAll(r11);
        updateSearchResults();
        int[] r93 = this.messagesSearchCount;
        r93[0] = r14;
        r93[1] = 0;
        int[] r94 = this.filteredMessagesSearchCount;
        r94[1] = 0;
        r94[0] = 0;
        this.lastReturnedNum = r13;
        getNotificationCenter().postNotificationName(NotificationCenter.chatSearchResultsAvailable, new Object[]{Integer.valueOf(r8), 0, Integer.valueOf(getMask()), Long.valueOf(getUserConfig().getClientUserId()), Integer.valueOf(this.lastReturnedNum), Integer.valueOf(getSearchCount()), Boolean.TRUE});
    }

    public void preloadDefaultReactions() {
        if (this.reactionsList != null) goto L5;
        return;
    L5:
        if (this.reactionsCacheGenerated == false) goto L7;
        return;
    L7:
        if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS) == true) goto L10;
        return;
    L10:
        if (this.currentAccount != UserConfig.selectedAccount) goto L23;
        this.reactionsCacheGenerated = true;
        ArrayList r0 = new ArrayList(this.reactionsList);
        int r1 = Math.min(r0.size(), 10);
        int r3 = 0;
    L13:
        if (r3 >= r1) goto L15;
        TLRPC.TL_availableReaction r4 = (TLRPC.TL_availableReaction) r0.get(r3);
        preloadImage(ImageLocation.getForDocument(r4.activate_animation), 0);
        preloadImage(ImageLocation.getForDocument(r4.appear_animation), 0);
        r3 = r3 + 1;
        goto L13
    L15:
        int r32 = 0;
    L16:
        if (r32 >= r1) goto L24;
        preloadImage(ImageLocation.getForDocument(((TLRPC.TL_availableReaction) r0.get(r32)).effect_animation), 0);
        r32 = r32 + 1;
        goto L16
    L24:
        return;
    }

    public void preloadImage(ImageLocation r7, int r8) {
        getFileLoader().loadFile(r7, null, null, r8, 11);
    }

    public void preloadImage(ImageReceiver r8, ImageLocation r9, String r10) {
        if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS) == true) goto L5;
        return;
    L5:
        r8.setUniqKeyPrefix("preload");
        r8.setFileLoadingPriority(0);
        r8.setImage(r9, r10, null, null, 0, 11);
    }

    public void preloadPremiumPreviewStickers() {
        if (this.previewStickersLoading == false) goto L5;
    L9:
        int r2 = 0;
    L11:
        if (r2 >= Math.min(this.premiumPreviewStickers.size(), 3)) goto L20;
        ArrayList<TLRPC.Document> r3 = this.premiumPreviewStickers;
        if (r2 != 2) goto L15;
        int r4 = r3.size() - 1;
    L16:
        TLRPC.Document r32 = r3.get(r4);
        if (MessageObject.isPremiumSticker(r32) == false) goto L19;
        ImageReceiver r5 = new ImageReceiver();
        r5.setAllowLoadingOnAttachedOnly(false);
        r5.setImage(ImageLocation.getForDocument(r32), null, null, "webp", null, 1);
        ImageLoader.getInstance().loadImageForImageReceiver(r5);
        ImageReceiver r6 = new ImageReceiver();
        r6.setAllowLoadingOnAttachedOnly(false);
        r6.setImage(ImageLocation.getForDocument(MessageObject.getPremiumStickerAnimation(r32), r32), null, null, null, "tgs", null, 1);
        ImageLoader.getInstance().loadImageForImageReceiver(r6);
    L19:
        r2 = r2 + 1;
        goto L11
    L15:
        r4 = r2;
        goto L16
    L20:
        return;
    L5:
        if (this.premiumPreviewStickers.isEmpty() == false) goto L9;
        TLRPC.TL_messages_getStickers r0 = new TLRPC.TL_messages_getStickers();
        r0.emoticon = Emoji.fixEmoji("⭐") + Emoji.fixEmoji("⭐");
        r0.hash = 0;
        this.previewStickersLoading = true;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new MediaDataController$$ExternalSyntheticLambda9(this));
    }

    public void preloadStickerSetThumb(TLRPC.StickerSetCovered r4) {
        if (r4 == null) goto L15;
        TLRPC.StickerSet r0 = r4.set;
        if (r0 == null) goto L16;
        TLRPC.PhotoSize r02 = FileLoader.getClosestPhotoSizeWithSize(r0.thumbs, 90);
        if (r02 == null) goto L17;
        TLRPC.Document r1 = r4.cover;
        if (r1 == null) goto L11;
    L13:
        loadStickerSetThumbInternal(r02, r4, r1, r4.set.thumb_version);
        return;
    L11:
        if (r4.covers.isEmpty() == true) goto L18;
        r1 = r4.covers.get(0);
        goto L13
    L18:
        return;
    L17:
        return;
    L16:
        return;
    }

    public void preloadStickerSetThumb(TLRPC.TL_messages_stickerSet r4) {
        if (r4 == null) goto L13;
        TLRPC.StickerSet r0 = r4.set;
        if (r0 == null) goto L14;
        TLRPC.PhotoSize r02 = FileLoader.getClosestPhotoSizeWithSize(r0.thumbs, 90);
        if (r02 == null) goto L15;
        ArrayList<TLRPC.Document> r1 = r4.documents;
        if (r1 != null) goto L10;
        return;
    L10:
        if (r1.isEmpty() == true) goto L17;
        loadStickerSetThumbInternal(r02, r4, r1.get(0), r4.set.thumb_version);
        return;
    L17:
        return;
    L15:
        return;
    L14:
        return;
    }

    public boolean processDeletedMessage(int r6, long[] r7) {
        boolean r0 = false;
        int r1 = 0;
    L4:
        if (r1 >= this.deletedFromResultMessages.size()) goto L9;
        if (this.deletedFromResultMessages.get(r1).getId() == r6) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        MessageObject r62 = this.deletedFromResultMessages.get(r1);
    L10:
        if (r62 != null) goto L12;
    L14:
        this.deletedFromResultMessages.remove(r62);
        return r0;
    L12:
        if (r62.getDialogId() != getUserConfig().getClientUserId()) goto L14;
        boolean r12 = getMessagesController().processDeletedReactionTags(r62.messageOwner);
        r7[0] = MessageObject.getSavedDialogId(getUserConfig().getClientUserId(), r62.messageOwner);
        r0 = r12;
        goto L14
    L9:
        r62 = null;
        goto L10
    }

    public void processLoadedMenuBots(TLRPC.TL_attachMenuBots r6, long r7, int r9, boolean r10) {
        if (r6 == null) goto L5;
        if (r9 == 0) goto L5;
        this.attachMenuBots = r6;
        this.menuBotsUpdateHash = r7;
    L5:
        SharedPreferences.Editor r0 = getMessagesController().getMainSettings().edit();
        this.menuBotsUpdateDate = r9;
        r0.putInt("menuBotsUpdateDate", r9).commit();
        this.menuBotsUpdatedLocal = true;
        if (r6 == null) goto L16;
        if (r10 == true) goto L9;
        getMessagesStorage().putUsersAndChats(r6.users, null, true, true);
    L9:
        getMessagesController().putUsers(r6.users, r10);
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda133(this));
        int r2 = 0;
        boolean r3 = false;
    L11:
        if (r2 >= r6.bots.size()) goto L17;
        if ((r6.bots.get(r2) instanceof TLRPC.TL_attachMenuBot_layer162) == false) goto L15;
        r6.bots.get(r2).show_in_attach_menu = true;
        r3 = true;
    L15:
        r2 = r2 + 1;
    L17:
        if (r10 == true) goto L20;
        putMenuBotsToCache(r6, r7, r9);
        return;
    L20:
        if (r3 == false) goto L22;
    L25:
        loadAttachMenuBots(false, true);
        return;
    L22:
        if (Math.abs((System.currentTimeMillis() / 1000) - r9) >= 3600) goto L25;
        return;
    L16:
        r3 = false;
        goto L17
    }

    public void processLoadedPremiumPromo(TLRPC.TL_help_premiumPromo r6, int r7, boolean r8) {
        if (r6 == null) goto L5;
        this.premiumPromo = r6;
        this.premiumPromoUpdateDate = r7;
        getMessagesController().putUsers(r6.users, r8);
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda237(this));
    L5:
        if (r8 == true) goto L10;
        if (r6 == null) goto L8;
        putPremiumPromoToCache(r6, r7);
    L8:
        this.isLoadingPremiumPromo = false;
        return;
    L10:
        if (r6 != null) goto L12;
    L16:
        loadPremiumPromo(false);
        return;
    L12:
        if (Math.abs((System.currentTimeMillis() / 1000) - r7) >= 86400) goto L16;
        this.isLoadingPremiumPromo = false;
    }

    public void processLoadedReactions(List<TLRPC.TL_availableReaction> r6, int r7, int r8, boolean r9) {
        if (r6 == null) goto L13;
        if (r8 == 0) goto L13;
        this.reactionsList.clear();
        this.reactionsMap.clear();
        this.enabledReactionsList.clear();
        this.reactionsList.addAll(r6);
        int r1 = 0;
    L7:
        if (r1 >= this.reactionsList.size()) goto L12;
        this.reactionsList.get(r1).positionInList = r1;
        this.reactionsMap.put(this.reactionsList.get(r1).reaction, this.reactionsList.get(r1));
        if (this.reactionsList.get(r1).inactive == true) goto L11;
        this.enabledReactionsList.add(this.reactionsList.get(r1));
    L11:
        r1 = r1 + 1;
        goto L7
    L12:
        this.reactionsUpdateHash = r7;
    L13:
        this.reactionsUpdateDate = r8;
        if (r6 == null) goto L16;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda151(this));
    L16:
        this.isLoadingReactions = false;
        if (r9 == true) goto L20;
        putReactionsToCache(r6, r7, r8);
        return;
    L20:
        Math.abs((System.currentTimeMillis() / 1000) - r8);
        loadReactions(false, Integer.valueOf(r7));
    }

    public void processLoadedRecentDocuments(int r9, ArrayList<TLRPC.Document> r10, boolean r11, int r12, boolean r13) {
        if (r10 == null) goto L4;
        MediaDataController r2 = this;
        int r4 = r9;
        ArrayList<TLRPC.Document> r5 = r10;
        boolean r3 = r11;
        int r7 = r12;
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda72(r2, r3, r4, r5, r13, r7));
    L5:
        if (r7 != 0) goto L8;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda73(r2, r3, r4, r5));
        return;
    L8:
        return;
    L4:
        r2 = this;
        r4 = r9;
        r5 = r10;
        r3 = r11;
        r7 = r12;
        goto L5
    }

    public void processStickerSetInstallResultArchive(BaseFragment r7, boolean r8, int r9, TLRPC.TL_messages_stickerSetInstallResultArchive r10) {
        int r0 = r10.sets.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        this.installedStickerSetsById.remove(r10.sets.get(r2).set.id);
        r2 = r2 + 1;
        goto L3
    L5:
        loadArchivedStickersCount(r9, false);
        getNotificationCenter().postNotificationName(NotificationCenter.needAddArchivedStickers, new Object[]{r10.sets});
        if (r7 != null) goto L8;
        return;
    L8:
        if (r7.getParentActivity() == null) goto L17;
        Activity r92 = r7.getParentActivity();
        if (r8 == false) goto L12;
        BaseFragment r82 = r7;
    L13:
        r7.showDialog(new StickersArchiveAlert(r92, r82, r10.sets).create());
        return;
    L12:
        r82 = null;
        goto L13
    }

    public void pushDraftVoiceMessage(long r4, long r6, DraftVoice r8) {
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("2voicedrafts_" + this.currentAccount, 0);
        long r42 = Objects.hash(new Object[]{Long.valueOf(r4), Long.valueOf(r6)});
        String r62 = r42 + _UrlKt.FRAGMENT_ENCODE_SET;
        if (r8 != null) goto L6;
        r0.edit().remove(r62).apply();
        this.draftVoices.remove(r42);
        return;
    L6:
        r0.edit().putString(r62, r8.toString()).apply();
        this.draftVoices.put(r42, r8);
    }

    public void pushRecentEmojiStatus(TLRPC.EmojiStatus r7) {
        if (this.emojiStatuses[0] != null) goto L5;
        return;
    L5:
        if ((r7 instanceof TLRPC.TL_emojiStatus) == false) goto L15;
        long r2 = ((TLRPC.TL_emojiStatus) r7).document_id;
        int r0 = 0;
    L8:
        if (r0 >= this.emojiStatuses[0].size()) goto L15;
        if ((this.emojiStatuses[0].get(r0) instanceof TLRPC.TL_emojiStatus) == false) goto L14;
        if (((TLRPC.TL_emojiStatus) this.emojiStatuses[0].get(r0)).document_id != r2) goto L14;
        this.emojiStatuses[0].remove(r0);
        r0 = r0 - 1;
    L14:
        r0 = r0 + 1;
    L15:
        this.emojiStatuses[0].add(0, r7);
    L17:
        if (this.emojiStatuses[0].size() <= 50) goto L19;
        this.emojiStatuses[0].remove(r7.size() - 1);
        goto L17
    L19:
        TL_account.TL_emojiStatuses r72 = new TL_account.TL_emojiStatuses();
        r72.hash = this.emojiStatusesHash[0];
        r72.statuses = this.emojiStatuses[0];
        updateEmojiStatuses(0, r72);
    }

    public void putBotInfo(long r5, TL_bots.BotInfo r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        this.botInfos.put(r7.user_id + "_" + r5, r7);
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda173(this, r7, r5));
    }

    public void putBotKeyboard(MessagesStorage.TopicKey r10, TLRPC.Message r11) {
        if (r10 == null) goto L34;
        int r1 = 0;
        if (r10.topicId == 0) goto L7;
        SQLiteCursor r0 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT mid FROM bot_keyboard_topics WHERE uid = %d AND tid = %d", new Object[]{Long.valueOf(r10.dialogId), Long.valueOf(r10.topicId)}), new Object[0]);     // Catch: Exception -> L29
    L9:
        if (r0.next() == false) goto L11;
        r1 = r0.intValue(0);     // Catch: Exception -> L29
    L11:
        r0.dispose();     // Catch: Exception -> L29
        if (r1 < r11.id) goto L19;
        if (MessageObject.isEphemeralMessageId(r1) == true) goto L19;
        if (MessageObject.isEphemeralMessageId(r11.id) == true) goto L19;
        return;
    L19:
        if (r10.topicId == 0) goto L21;
        SQLitePreparedStatement r02 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO bot_keyboard_topics VALUES(?, ?, ?, ?)");     // Catch: Exception -> L29
    L22:
        r02.requery();     // Catch: Exception -> L29
        MessageObject.normalizeFlags(r11);     // Catch: Exception -> L29
        NativeByteBuffer r12 = new NativeByteBuffer(r11.getObjectSize());     // Catch: Exception -> L29
        r11.serializeToStream(r12);     // Catch: Exception -> L29
        long r4 = r10.topicId;     // Catch: Exception -> L29
        long r3 = r10.dialogId;
        if (r4 == 0) goto L26;
        r02.bindLong(1, r3);     // Catch: Exception -> L29
        r02.bindLong(2, r10.topicId);     // Catch: Exception -> L29
        r02.bindInteger(3, r11.id);     // Catch: Exception -> L29
        r02.bindByteBuffer(4, r12);     // Catch: Exception -> L29
    L27:
        r02.step();     // Catch: Exception -> L29
        r12.reuse();     // Catch: Exception -> L29
        r02.dispose();     // Catch: Exception -> L29
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda103(this, r10, r11));     // Catch: Exception -> L29
        return;
    L26:
        r02.bindLong(1, r3);     // Catch: Exception -> L29
        r02.bindInteger(2, r11.id);     // Catch: Exception -> L29
        r02.bindByteBuffer(3, r12);     // Catch: Exception -> L29
        goto L27
    L21:
        r02 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO bot_keyboard VALUES(?, ?, ?)");     // Catch: Exception -> L29
        goto L22
    L7:
        r0 = getMessagesStorage().getDatabase().queryFinalized(String.format(Locale.US, "SELECT mid FROM bot_keyboard WHERE uid = %d", new Object[]{Long.valueOf(r10.dialogId)}), new Object[0]);     // Catch: Exception -> L29
    L29:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public void putGroupStickerSet(TLRPC.TL_messages_stickerSet r3) {
        this.groupStickerSets.put(r3.set.id, r3);
    }

    public void putStickerSet(TLRPC.TL_messages_stickerSet r2) {
        putStickerSet(r2, true);
    }

    public void putStickerSet(TLRPC.TL_messages_stickerSet r9, boolean r10) {
        if (r9 == null) goto L45;
        TLRPC.StickerSet r0 = r9.set;
        if (r0 == null) goto L47;
        this.stickerSetsById.put(r0.id, r9);
        if (TextUtils.isEmpty(r9.set.short_name) == true) goto L9;
        this.stickerSetsByName.put(r9.set.short_name.toLowerCase(), r9);
    L9:
        int r02 = 0;
        int r1 = 0;
    L10:
        ArrayList<TLRPC.TL_messages_stickerSet>[] r2 = this.stickerSets;
        if (r1 >= r2.length) goto L27;
        ArrayList<TLRPC.TL_messages_stickerSet> r22 = r2[r1];
        if (r22 == null) goto L25;
        int r3 = 0;
    L16:
        if (r3 >= r22.size()) goto L25;
        TLRPC.TL_messages_stickerSet r4 = r22.get(r3);
        if (r4 == null) goto L24;
        TLRPC.StickerSet r42 = r4.set;
        if (r42 == null) goto L24;
        if (r42.id != r9.set.id) goto L24;
        r22.set(r3, r9);
    L24:
        r3 = r3 + 1;
    L25:
        r1 = r1 + 1;
        goto L10
    L27:
        if (this.groupStickerSets.containsKey(r9.set.id) == false) goto L29;
        this.groupStickerSets.put(r9.set.id, r9);
    L29:
        saveStickerSetIntoCache(r9);
        TLRPC.StickerSet r92 = r9.set;
        if (r92.masks == false) goto L33;
        r02 = 1;
    L35:
        if (r10 == false) goto L46;
        getNotificationCenter().postNotificationName(NotificationCenter.stickersDidLoad, new Object[]{Integer.valueOf(r02), Boolean.TRUE});
        return;
    L46:
        return;
    L33:
        if (r92.emojis == false) goto L35;
        r02 = 5;
        goto L35
    L47:
        return;
    }

    public void removeInline(long r2) {
        removeInline(r2, false);
    }

    public void removeMessageFromResults(int r5) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= this.searchResultMessages.size()) goto L9;
        if (r5 != this.searchResultMessages.get(r1).getId()) goto L8;
        this.deletedFromResultMessages.add(this.searchResultMessages.remove(r1));
        r1 = r1 - 1;
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        int r12 = 0;
    L11:
        if (r12 >= this.searchServerResultMessages.size()) goto L17;
        if (r5 != this.searchServerResultMessages.get(r12).getId()) goto L15;
        this.searchServerResultMessages.remove(r12);
        r12 = r12 - 1;
    L15:
        r12 = r12 + 1;
    L17:
        if (r0 >= this.searchLocalResultMessages.size()) goto L22;
        if (r5 != this.searchLocalResultMessages.get(r0).getId()) goto L21;
        this.searchLocalResultMessages.remove(r0);
        r0 = r0 - 1;
    L21:
        r0 = r0 + 1;
        goto L17
    }

    public void removeMultipleStickerSets(Context r17, BaseFragment r18, ArrayList<TLRPC.TL_messages_stickerSet> r19) {
        if (r19 != null) goto L5;
        return;
    L5:
        if (r19.isEmpty() == true) goto L50;
        TLRPC.TL_messages_stickerSet r10 = r19.get(r19.size() - 1);
        if (r10 == null) goto L51;
        TLRPC.StickerSet r0 = r10.set;
        int r15 = 0;
        if (r0.masks == false) goto L14;
        int r4 = 1;
    L17:
        int r02 = 0;
    L19:
        if (r02 >= r19.size()) goto L21;
        r19.get(r02).set.archived = false;
        r02 = r02 + 1;
        goto L19
    L21:
        int[] r8 = new int[r19.size()];
        int r03 = 0;
    L22:
        int r2 = this.stickerSets[r4].size();
        ArrayList<TLRPC.TL_messages_stickerSet>[] r3 = this.stickerSets;
        if (r03 >= r2) goto L32;
        TLRPC.TL_messages_stickerSet r22 = r3[r4].get(r03);
        int r32 = 0;
    L26:
        if (r32 >= r19.size()) goto L31;
        if (r22.set.id == r19.get(r32).set.id) goto L29;
        r32 = r32 + 1;
        goto L26
    L29:
        r8[r32] = r03;
        this.stickerSets[r4].remove(r03);
        this.stickerSetsById.remove(r22.set.id);
        this.installedStickerSetsById.remove(r22.set.id);
        this.stickerSetsByName.remove(r22.set.short_name);
    L31:
        r03 = r03 + 1;
        goto L22
    L32:
        ArrayList<TLRPC.TL_messages_stickerSet> r23 = r3[r4];
        int r33 = this.loadDate[r4];
        long[] r04 = this.loadHash;
        int r1 = r4;
        long r42 = calcStickersHash(r23);
        r04[r1] = r42;
        putStickersToCache(r1, r23, r33, r42);
        getNotificationCenter().postNotificationName(NotificationCenter.stickersDidLoad, new Object[]{Integer.valueOf(r1), Boolean.TRUE});
        int r05 = 0;
    L34:
        if (r05 >= r19.size()) goto L36;
        markSetUninstalling(r19.get(r05).set.id, true);
        r05 = r05 + 1;
        goto L34
    L36:
        StickerSetBulletinLayout r82 = new StickerSetBulletinLayout(r17, r10, r19.size(), 0, null, r18.getResourceProvider());
        boolean[] r24 = new boolean[1];
        Bulletin.UndoButton r06 = new Bulletin.UndoButton(r17, false).setUndoAction(new MediaDataController$$ExternalSyntheticLambda153(this, r24, r19, r1, r8)).setDelayedAction(new MediaDataController$$ExternalSyntheticLambda154(this, r24, r19, r17, r18, r1));
        r82.setButton(r06);
    L38:
        if (r15 >= r19.size()) goto L40;
        LongSparseArray<Runnable> r25 = this.removingStickerSetsUndos;
        long r43 = r19.get(r15).set.id;
        Objects.requireNonNull(r06);
        r25.put(r43, new MediaDataController$$ExternalSyntheticLambda155(r06));
        r15 = r15 + 1;
        goto L38
    L40:
        Bulletin.make(r18, r82, 2750).show();
        return;
    L14:
        if (r0.emojis == false) goto L16;
        r4 = 5;
        goto L17
    L16:
        r4 = 0;
        goto L17
    L51:
        return;
    }

    public void removePeer(long r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.hints.size()) goto L10;
        if (this.hints.get(r1).peer.user_id == r5) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        this.hints.remove(r1);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadHints, new Object[0]);
        TLRPC.TL_contacts_resetTopPeerRating r12 = new TLRPC.TL_contacts_resetTopPeerRating();
        r12.category = new TLRPC.TL_topPeerCategoryCorrespondents();
        r12.peer = getMessagesController().getInputPeer(r5);
        deletePeer(r5, 0);
        getConnectionsManager().sendRequest(r12, new MediaDataController$$ExternalSyntheticLambda16());
        return;
    }

    public void removeRecentGif(TLRPC.Document r8) {
        int r0 = this.recentGifs.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        if (this.recentGifs.get(r2).id == r8.id) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        this.recentGifs.remove(r2);
    L8:
        TLRPC.TL_messages_saveGif r02 = new TLRPC.TL_messages_saveGif();
        TLRPC.TL_inputDocument r22 = new TLRPC.TL_inputDocument();
        r02.id = r22;
        r22.id = r8.id;
        r22.access_hash = r8.access_hash;
        byte[] r3 = r8.file_reference;
        r22.file_reference = r3;
        if (r3 != null) goto L11;
        r22.file_reference = new byte[0];
    L11:
        r02.unsave = true;
        getConnectionsManager().sendRequest(r02, new MediaDataController$$ExternalSyntheticLambda159(this, r02));
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda160(this, r8));
    }

    public void removeWebapp(long r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.webapps.size()) goto L10;
        if (this.webapps.get(r1).peer.user_id == r5) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        this.webapps.remove(r1);
        TLRPC.TL_contacts_resetTopPeerRating r12 = new TLRPC.TL_contacts_resetTopPeerRating();
        r12.category = new TLRPC.TL_topPeerCategoryBotsApp();
        r12.peer = getMessagesController().getInputPeer(r5);
        getConnectionsManager().sendRequest(r12, new MediaDataController$$ExternalSyntheticLambda54());
        deletePeer(r5, 2);
        getNotificationCenter().postNotificationName(NotificationCenter.reloadWebappsHints, new Object[0]);
        return;
    }

    public void reorderStickers(int r3, ArrayList<Long> r4, boolean r5) {
        Collections.sort(this.stickerSets[r3], new MediaDataController$$ExternalSyntheticLambda233(r4));
        this.loadHash[r3] = calcStickersHash(this.stickerSets[r3]);
        getNotificationCenter().postNotificationName(NotificationCenter.stickersDidLoad, new Object[]{Integer.valueOf(r3), Boolean.valueOf(r5)});
    }

    public void replaceStickerSet(TLRPC.TL_messages_stickerSet r14) {
        TLRPC.TL_messages_stickerSet r1 = this.stickerSetsById.get(r14.set.id);
        String r2 = this.diceEmojiStickerSetsById.get(r14.set.id);
        if (r2 == null) goto L5;
        this.diceStickerSetsByEmoji.put(r2, r14);
        putDiceStickersToCache(r2, r14, (int) (System.currentTimeMillis() / 1000));
    L5:
        if (r1 != null) goto L7;
        r1 = this.stickerSetsByName.get(r14.set.short_name);
    L7:
        int r22 = 0;
        if (r1 != null) goto L12;
        r1 = this.groupStickerSets.get(r14.set.id);
        if (r1 == null) goto L12;
        boolean r4 = true;
    L13:
        if (r1 != null) goto L16;
        return;
    L16:
        if ("AnimatedEmojies".equals(r14.set.short_name) == false) goto L18;
        r1.documents = r14.documents;
        r1.packs = r14.packs;
        r1.set = r14.set;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda247(this, r14));
        boolean r10 = true;
    L27:
        if (r10 == false) goto L46;
        if (r4 == false) goto L31;
        putSetToCache(r1);
        return;
    L31:
        TLRPC.StickerSet r12 = r14.set;
        if (r12.masks == false) goto L35;
        int r13 = 1;
    L38:
        putStickersToCache(r13, this.stickerSets[r13], this.loadDate[r13], this.loadHash[r13]);
        if ("AnimatedEmojies".equals(r14.set.short_name) == false) goto L47;
        putStickersToCache(4, this.stickerSets[4], this.loadDate[4], this.loadHash[4]);
        return;
    L47:
        return;
    L35:
        if (r12.emojis == false) goto L37;
        r22 = 5;
    L37:
        r13 = r22;
        goto L38
    L46:
        return;
    L18:
        LongSparseArray r5 = new LongSparseArray();
        int r8 = r14.documents.size();
        int r9 = 0;
    L19:
        if (r9 >= r8) goto L21;
        TLRPC.Document r102 = r14.documents.get(r9);
        r5.put(r102.id, r102);
        r9 = r9 + 1;
        goto L19
    L21:
        int r82 = r1.documents.size();
        int r92 = 0;
        r10 = false;
    L22:
        if (r92 >= r82) goto L27;
        TLRPC.Document r11 = (TLRPC.Document) r5.get(r1.documents.get(r92).id);
        if (r11 == null) goto L26;
        r1.documents.set(r92, r11);
        r10 = true;
    L26:
        r92 = r92 + 1;
    L12:
        r4 = false;
        goto L13
    }

    public void saveDraft(long r15, int r17, CharSequence r18, ArrayList<TLRPC.MessageEntity> r19, TLRPC.Message r20, boolean r21, long r22) {
        saveDraft(r15, r17, r18, r19, r20, null, null, r22, r21, false);
    }

    public void saveDraft(long r16, long r18, CharSequence r20, ArrayList<TLRPC.MessageEntity> r21, TLRPC.Message r22, ChatActivity.ReplyQuote r23, TLRPC.SuggestedPost r24, long r25, boolean r27, boolean r28) {
        saveDraft(r16, r18, r20, r21, r22, r23, r24, r25, r27, r28, null);
    }

    public void saveDraft(long r23, long r25, CharSequence r27, ArrayList<TLRPC.MessageEntity> r28, TLRPC.Message r29, ChatActivity.ReplyQuote r30, TLRPC.SuggestedPost r31, long r32, boolean r34, boolean r35, TL_iv.RichMessage r36) {
        if (getMessagesController().isForum(r23) == true) goto L5;
    L7:
        TLRPC.Message r11 = r29;
    L9:
        if (TextUtils.isEmpty(r27) == false) goto L14;
        if (r11 != null) goto L14;
        if (r36 != null) goto L14;
        TLRPC.DraftMessage r15 = new TLRPC.TL_draftMessageEmpty();
    L15:
        r15.rich_message = r36;
        r15.date = (int) (System.currentTimeMillis() / 1000);
        if (r27 != null) goto L18;
        String r10 = _UrlKt.FRAGMENT_ENCODE_SET;
    L19:
        r15.message = r10;
        r15.no_webpage = r34;
        if (r32 == 0) goto L22;
        r15.flags |= 128;
        r15.effect = r32;
    L22:
        if (r11 == null) goto L45;
        TLRPC.TL_inputReplyToMessage r8 = new TLRPC.TL_inputReplyToMessage();
        r15.reply_to = r8;
        r15.flags |= 16;
        r8.reply_to_msg_id = r11.id;
        if (r30 == null) goto L43;
        r8.quote_text = r30.getText();
        TLRPC.InputReplyTo r82 = r15.reply_to;
        if (r82.quote_text == null) goto L28;
        r82.flags |= 20;
        r82.quote_offset = r30.start;
    L28:
        r82.quote_entities = r30.getFilteredEntities();
        ArrayList<TLRPC.MessageEntity> r83 = r15.reply_to.quote_entities;
        if (r83 != null) goto L31;
    L33:
        MessageObject r84 = r30.message;
        if (r84 == null) goto L45;
        if (r84.messageOwner == null) goto L45;
        TLRPC.Peer r85 = getMessagesController().getPeer(r23);
        TLRPC.Peer r6 = r30.message.messageOwner.peer_id;
        if (r85 == null) goto L45;
        if (MessageObject.peersEqual(r85, r6) == true) goto L45;
        TLRPC.InputReplyTo r86 = r15.reply_to;
        r86.flags |= 2;
        r86.reply_to_peer_id = getMessagesController().getInputPeer(r6);
        goto L45
    L31:
        if (r83.isEmpty() == true) goto L33;
        r15.reply_to.quote_entities = new ArrayList(r15.reply_to.quote_entities);
        r15.reply_to.flags |= 8;
        goto L33
    L43:
        if (r23 == MessageObject.getDialogId(r11)) goto L45;
        TLRPC.InputReplyTo r62 = r15.reply_to;
        r62.flags |= 2;
        r62.reply_to_peer_id = getMessagesController().getInputPeer(getMessagesController().getPeer(MessageObject.getDialogId(r11)));
    L45:
        if (r28 != null) goto L47;
    L49:
        TLRPC.Chat r5 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r23));
        if (ChatObject.isMonoForum(r5) == true) goto L52;
    L57:
        if (r31 == null) goto L59;
        r15.suggested_post = r31;
    L59:
        LongSparseArray<TLRPC.DraftMessage> r63 = this.drafts.get(r23);
        if (r63 != null) goto L62;
        TLRPC.DraftMessage r64 = null;
    L63:
        if (r35 == true) goto L91;
        if (r64 == null) goto L79;
        if (r64.message.equals(r15.message) == false) goto L91;
        if (replyToEquals(r64.reply_to, r15.reply_to) == false) goto L91;
        if (suggestedPostEquals(r64.suggested_post, r15.suggested_post) == false) goto L91;
        if (richMessageEquals(r64.rich_message, r15.rich_message) == false) goto L91;
        if (r64.no_webpage != r15.no_webpage) goto L91;
        if (r64.effect != r15.effect) goto L91;
        return;
    L79:
        if (TextUtils.isEmpty(r15.message) == false) goto L91;
        TLRPC.InputReplyTo r65 = r15.reply_to;
        if (r65 == null) goto L85;
        if (r65.reply_to_msg_id != 0) goto L91;
    L85:
        if (r15.effect != 0) goto L91;
        if (r15.rich_message != null) goto L91;
        if (r15.suggested_post != null) goto L91;
        return;
    L91:
        saveDraft(r23, r25, r15, r11, false);
        if (r25 == 0) goto L98;
        if (ChatObject.isForum(r5) == true) goto L98;
        if (ChatObject.isMonoForum(r5) == true) goto L98;
        return;
    L98:
        if (DialogObject.isEncryptedDialog(r23) == true) goto L109;
        TLRPC.TL_messages_saveDraft r3 = new TLRPC.TL_messages_saveDraft();
        TLRPC.InputPeer r1 = getMessagesController().getInputPeer(r23);
        r3.peer = r1;
        if (r1 == null) goto L111;
        r3.message = r15.message;
        r3.no_webpage = r15.no_webpage;
        r3.reply_to = r15.reply_to;
        r3.suggested_post = r15.suggested_post;
        r3.entities = r15.entities;
        TL_iv.RichMessage r12 = r15.rich_message;
        if (r12 == null) goto L106;
        r3.rich_message = toInputRichMessage(r12);
    L106:
        if ((r15.flags & 128) == 0) goto L108;
        r3.effect = r15.effect;
        r3.flags |= 128;
    L108:
        getConnectionsManager().sendRequest(r3, new MediaDataController$$ExternalSyntheticLambda111());
        goto L109
    L111:
        return;
    L109:
        getMessagesController().sortDialogs(null);
        getNotificationCenter().postNotificationName(NotificationCenter.dialogsNeedReload, new Object[0]);
        return;
    L62:
        r64 = r63.get(r25);
        goto L63
    L52:
        if (ChatObject.canManageMonoForum(this.currentAccount, r5) == false) goto L57;
        r15.flags |= 16;
        TLRPC.InputReplyTo r66 = r15.reply_to;
        if (r66 != null) goto L56;
        TLRPC.TL_inputReplyToMonoForum r67 = new TLRPC.TL_inputReplyToMonoForum();
        r15.reply_to = r67;
        r67.monoforum_peer_id = getMessagesController().getInputPeer(r25);
        goto L57
    L56:
        r66.monoforum_peer_id = getMessagesController().getInputPeer(r25);
        r15.reply_to.flags |= 32;
        goto L57
    L47:
        if (r28.isEmpty() == true) goto L49;
        ArrayList<TLRPC.MessageEntity> r68 = new ArrayList(r28);
        r15.entities = r68;
        LocaleUtils.replaceCustomEmojis(this.currentAccount, r23, r68);
        r15.flags |= 8;
        goto L49
    L18:
        r10 = r27.toString();
    L14:
        r15 = new TLRPC.TL_draftMessage();
        goto L15
    L5:
        if (r25 != 0) goto L7;
        r11 = null;
        goto L9
    }

    public void saveDraft(long r20, long r22, TLRPC.DraftMessage r24, TLRPC.Message r25, boolean r26) {
        if (getMessagesController().isForum(r20) == true) goto L5;
    L11:
        SharedPreferences.Editor r5 = this.draftPreferences.edit();
        MessagesController r0 = getMessagesController();
        if (r24 != null) goto L14;
    L15:
        long r16 = 0;
        LongSparseArray<TLRPC.DraftMessage> r3 = this.drafts.get(r20);
        if (r3 == null) goto L38;
        r3.remove(r22);
        if (r3.size() != 0) goto L38;
        this.drafts.remove(r20);
    L38:
        LongSparseArray<TLRPC.Message> r32 = this.draftMessages.get(r20);
        if (r32 == null) goto L43;
        r32.remove(r22);
        if (r32.size() != 0) goto L43;
        this.draftMessages.remove(r20);
    L43:
        SharedPreferences r4 = this.draftPreferences;
        if (r22 != 0) goto L46;
        r4.edit().remove(_UrlKt.FRAGMENT_ENCODE_SET + r20).remove("r_" + r20).apply();
    L47:
        r0.removeDraftDialogIfNeed(r20);
    L48:
        LongSparseArray<TLRPC.Message> r02 = this.draftMessages.get(r20);
        TLRPC.User r33 = null;
        if (r25 != null) goto L55;
        if (r24 == null) goto L55;
        if (r24.reply_to == null) goto L55;
        if (r02 == null) goto L59;
        TLRPC.Message r42 = r02.get(r22);
    L60:
        if (r42 != null) goto L69;
        if (r02 == null) goto L66;
        r02.remove(r22);
        if (r02.size() != 0) goto L66;
        this.draftMessages.remove(r20);
    L66:
        if (r22 != r16) goto L68;
        r5.remove("r_" + r20);
    L81:
        r5.apply();
        if (r26 == true) goto L84;
        return;
    L84:
        if (r22 != r16) goto L86;
    L87:
        if (r24 == null) goto L112;
        TLRPC.InputReplyTo r03 = r24.reply_to;
        if (r03 == null) goto L112;
        if (r03.reply_to_msg_id == 0) goto L112;
        if (r42 == null) goto L98;
        if ((r42.reply_to instanceof TLRPC.TL_messageReplyHeader) == false) goto L112;
        if (r42.replyMessage != null) goto L112;
    L98:
        if ((r03.flags & 2) == 0) goto L100;
        long r43 = DialogObject.getPeerDialogId(r03.reply_to_peer_id);
    L102:
        if (DialogObject.isUserDialog(r43) == false) goto L104;
        r33 = getMessagesController().getUser(Long.valueOf(r43));
        TLRPC.Chat r04 = null;
    L105:
        if (r33 != null) goto L108;
        if (r04 == null) goto L112;
    L108:
        if (ChatObject.isChannel(r04) == false) goto L110;
        long r11 = r04.id;
    L111:
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda4(this, r24.reply_to.reply_to_msg_id, r43, r11, r20, r22));
        goto L112
    L110:
        r11 = r16;
        goto L111
    L104:
        r04 = getMessagesController().getChat(Long.valueOf(-r43));
        goto L105
    L100:
        r43 = r20;
    L112:
        getNotificationCenter().postNotificationName(NotificationCenter.newDraftReceived, new Object[]{Long.valueOf(r20)});
        return;
    L86:
        if (getMessagesController().isForum(r20) == true) goto L87;
        return;
    L68:
        r5.remove("rt_" + r20 + "_" + r22);
        goto L81
    L69:
        if (r02 != null) goto L71;
        r02 = new LongSparseArray();
        this.draftMessages.put(r20, r02);
    L71:
        r02.put(r22, r42);
        SerializedData r05 = new SerializedData(r42.getObjectSize());     // Catch: Exception -> L75
        r42.serializeToStream(r05);     // Catch: Exception -> L75
        if (r22 != r16) goto L77;
        String r6 = "r_" + r20;     // Catch: Exception -> L75
    L78:
        r5.putString(r6, Utilities.bytesToHex(r05.toByteArray()));     // Catch: Exception -> L75
        r05.cleanup();     // Catch: Exception -> L75
        goto L81
    L77:
        r6 = "rt_" + r20 + "_" + r22;     // Catch: Exception -> L75
    L75:
        e = move-exception;
        FileLog.e(e);
    L59:
        r42 = r25;
    L55:
        if (r24 == null) goto L59;
        if (r24.reply_to != null) goto L59;
        r42 = null;
        goto L60
    L46:
        r4.edit().remove("t_" + r20 + "_" + r22).remove("rt_" + r20 + "_" + r22).apply();
        goto L47
    L14:
        if ((r24 instanceof TLRPC.TL_draftMessageEmpty) == true) goto L15;
        LongSparseArray<TLRPC.DraftMessage> r15 = this.drafts.get(r20);
        if (r15 != null) goto L19;
        r15 = new LongSparseArray();
        r16 = 0;
        this.drafts.put(r20, r15);
    L20:
        r15.put(r22, r24);
        if (r22 != r16) goto L116;
        r0.putDraftDialogIfNeed(r20, r24);
    L116:
        SerializedData r06 = new SerializedData(r24.getObjectSize());     // Catch: Exception -> L27
        r24.serializeToStream(r06);     // Catch: Exception -> L27
        if (r22 != r16) goto L29;
        StringBuilder r34 = new StringBuilder(_UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Exception -> L27
        r34.append(r20);     // Catch: Exception -> L27
    L30:
        r5.putString(r34.toString(), Utilities.bytesToHex(r06.toByteArray()));     // Catch: Exception -> L27
        r06.cleanup();     // Catch: Exception -> L27
        goto L48
    L29:
        r34 = new StringBuilder("t_");     // Catch: Exception -> L27
        r34.append(r20);     // Catch: Exception -> L27
        r34.append("_");     // Catch: Exception -> L27
        r34.append(r22);     // Catch: Exception -> L27
    L27:
        e = move-exception;
        FileLog.e(e);
        goto L48
    L19:
        r16 = 0;
        goto L20
    L5:
        if (r22 != 0) goto L11;
        if (TextUtils.isEmpty(r24.message) == false) goto L11;
        TLRPC.InputReplyTo r07 = r24.reply_to;
        if ((r07 instanceof TLRPC.TL_inputReplyToMessage) == false) goto L11;
        ((TLRPC.TL_inputReplyToMessage) r07).reply_to_msg_id = 0;
        goto L11
    }

    public boolean saveToRingtones(TLRPC.Document r9) {
        if (r9 != null) goto L6;
        return false;
    L6:
        if (this.ringtoneDataStore.contains(r9.id) == false) goto L9;
        return true;
    L9:
        if (r9.size <= MessagesController.getInstance(this.currentAccount).ringtoneSizeMax) goto L12;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{4, LocaleController.formatString("TooLargeError", R.string.TooLargeError, new Object[0]), LocaleController.formatString("ErrorRingtoneSizeTooBig", R.string.ErrorRingtoneSizeTooBig, new Object[]{Integer.valueOf(MessagesController.getInstance(UserConfig.selectedAccount).ringtoneSizeMax / 1024)})});
        return false;
    L12:
        int r2 = 0;
    L14:
        if (r2 >= r9.attributes.size()) goto L22;
        TLRPC.DocumentAttribute r4 = r9.attributes.get(r2);
        if ((r4 instanceof TLRPC.TL_documentAttributeAudio) == false) goto L21;
        if (r4.duration <= MessagesController.getInstance(this.currentAccount).ringtoneDurationMax) goto L21;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{4, LocaleController.formatString("TooLongError", R.string.TooLongError, new Object[0]), LocaleController.formatString("ErrorRingtoneDurationTooLong", R.string.ErrorRingtoneDurationTooLong, new Object[]{Integer.valueOf(MessagesController.getInstance(UserConfig.selectedAccount).ringtoneDurationMax)})});
        return false;
    L21:
        r2 = r2 + 1;
        goto L14
    L22:
        TL_account.saveRingtone r0 = new TL_account.saveRingtone();
        TLRPC.TL_inputDocument r1 = new TLRPC.TL_inputDocument();
        r0.id = r1;
        r1.id = r9.id;
        r1.file_reference = r9.file_reference;
        r1.access_hash = r9.access_hash;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new MediaDataController$$ExternalSyntheticLambda175(this, r9));
        return true;
    }

    public boolean searchEndReached() {
        boolean[] r0 = this.messagesSearchEndReached;
        if (r0[0] == false) goto L9;
        if (this.lastMergeDialogId != 0) goto L9;
        if (r0[1] == false) goto L9;
    L14:
        return true;
    L9:
        if (this.loadingSearchLocal == true) goto L14;
        if (this.loadedPredirectedSearchLocal == true) goto L14;
        return false;
    }

    public void searchMessagesInChat(String r16, long r17, long r19, int r21, int r22, long r23, TLRPC.User r25, TLRPC.Chat r26, ReactionsLayoutInBubble.VisibleReaction r27) {
        searchMessagesInChat(r16, r17, r19, r21, r22, r23, false, r25, r26, true, r27);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v3 */
    public void searchMessagesInChat(String r31, long r32, long r34, int r36, int r37, long r38, boolean r40, TLRPC.User r41, TLRPC.Chat r42, boolean r43, ReactionsLayoutInBubble.VisibleReaction r44) {
        boolean r0 = !r40;
        if (this.reqId == 0) goto L6;
        this.loadingMoreSearchMessages = false;
        getConnectionsManager().cancelRequest(this.reqId, true);
        this.reqId = 0;
    L6:
        if (this.mergeReqId == 0) goto L9;
        getConnectionsManager().cancelRequest(this.mergeReqId, true);
        this.mergeReqId = 0;
    L9:
        if (r31 == null) goto L11;
        if (r40 == true) goto L52;
        boolean[] r4 = this.messagesSearchEndReached;
        r4[1] = false;
        r4[0] = false;
        int[] r45 = this.messagesSearchCount;
        r45[1] = 0;
        r45[0] = 0;
        int[] r46 = this.filteredMessagesSearchCount;
        r46[1] = 0;
        r46[0] = 0;
        this.searchResultMessages.clear();
        this.searchLocalResultMessages.clear();
        this.searchServerResultMessagesMap[0].clear();
        this.searchServerResultMessagesMap[1].clear();
        getNotificationCenter().postNotificationName(NotificationCenter.chatSearchResultsLoading, new Object[]{Integer.valueOf(r36)});
    L52:
        String r7 = r31;
        long r13 = r32;
        int r47 = 0;
    L54:
        if (r7 == null) goto L60;
        long r25 = 0;
        if (r7.trim().startsWith("#") == false) goto L58;
    L59:
        boolean r11 = true;
    L62:
        boolean[] r5 = this.messagesSearchEndReached;
        if (r5[0] == false) goto L70;
        if (r5[1] == true) goto L70;
        if (r34 == r25) goto L70;
        r13 = r34;
    L70:
        if (r13 != r32) goto L96;
        if (r0 == false) goto L96;
        if (r34 == r25) goto L95;
        TLRPC.InputPeer r02 = getMessagesController().getInputPeer(r34);
        if (r02 != null) goto L77;
        return;
    L77:
        TLRPC.TL_messages_search r48 = new TLRPC.TL_messages_search();
        r48.peer = r02;
        this.lastMergeDialogId = r34;
        r48.limit = 1;
        r48.q = r7;
        if (r41 == null) goto L80;
        r48.from_id = MessagesController.getInputPeer(r41);
        r48.flags |= 1;
    L83:
        if (r38 != r25) goto L85;
    L91:
        if (r44 == null) goto L93;
        r48.saved_reaction.add(r44.toTLReaction());
        r48.flags |= 8;
    L93:
        r48.filter = new TLRPC.TL_inputMessagesFilterEmpty();
        this.mergeReqId = getConnectionsManager().sendRequest(r48, new MediaDataController$$ExternalSyntheticLambda122(this, r34, r48, r32, r36, r37, r38, r41, r42, r43, r44), 2);
        return;
    L85:
        if (r32 != getUserConfig().getClientUserId()) goto L87;
    L90:
        r48.saved_peer_id = getMessagesController().getInputPeer(r38);
        r48.flags |= 4;
        goto L91
    L87:
        if (getMessagesStorage().isMonoForum(r13) == true) goto L90;
        r48.top_msg_id = (int) r38;
        r48.flags |= 2;
        goto L91
    L80:
        if (r42 == null) goto L83;
        r48.from_id = MessagesController.getInputPeer(r42);
        r48.flags |= 1;
        goto L83
    L95:
        long r2 = r32;
        TLRPC.User r8 = r41;
        TLRPC.Chat r12 = r42;
        boolean r17 = 1;
        String r20 = r7;
        ReactionsLayoutInBubble.VisibleReaction r15 = r44;
        this.lastMergeDialogId = r25;
        r5[1] = true;
        this.messagesSearchCount[1] = 0;
    L97:
        TLRPC.TL_messages_search r72 = new TLRPC.TL_messages_search();
        TLRPC.InputPeer r52 = getMessagesController().getInputPeer(r13);
        r72.peer = r52;
        if (r52 != null) goto L101;
        this.loadingMoreSearchMessages = false;
        return;
    L101:
        this.lastGuid = r36;
        this.lastDialogId = r2;
        this.lastSearchUser = r8;
        this.lastSearchChat = r12;
        this.lastReplyMessageId = r38;
        this.lastReaction = r15;
        r72.limit = 21;
        if (r20 == null) goto L104;
        String r6 = r20;
    L105:
        r72.q = r6;
        r72.offset_id = r47;
        if (r8 == null) goto L109;
        r72.from_id = MessagesController.getInputPeer(r8);
        r72.flags |= 1;
    L111:
        this.loadingSearchLocal = false;
        this.loadedPredirectedSearchLocal = false;
        int r62 = this.lastReqId + 1;
        this.lastReqId = r62;
        if (r2 != getUserConfig().getClientUserId()) goto L114;
        boolean r28 = r17;
    L115:
        if (r28 == false) goto L128;
        if (r15 == null) goto L128;
        if (r0 == false) goto L128;
        this.lastReturnedNum = 0;
        this.searchServerResultMessages.clear();
        this.searchServerResultMessagesMap[0].clear();
        this.searchServerResultMessagesMap[r17].clear();
        int r3 = getMessagesController().getSavedTagCount(this.lastReplyMessageId, r15);
        if (TextUtils.isEmpty(r72.q) == false) goto L121;
        int r03 = r3;
    L122:
        this.messagesLocalSearchCount = r03;
        this.loadingSearchLocal = r17;
        this.loadedPredirectedSearchLocal = false;
        MessagesStorage r16 = getMessagesStorage();
        TLRPC.Reaction r172 = r15.toTLReaction();
        long r82 = this.lastReplyMessageId;
        ArrayList<MessageObject> r04 = this.searchLocalResultMessages;
        if (r04 != null) goto L125;
        int r22 = 0;
    L126:
        int r23 = r62;
        r16.searchSavedByTag(r172, r82, r20, 300, r22, new MediaDataController$$ExternalSyntheticLambda123(this, r23, r3, r36, r32), true);
    L127:
        String r49 = r20;
        if (this.lastReplyMessageId != 0) goto L132;
    L138:
        if (r15 == null) goto L140;
        r72.saved_reaction.add(r15.toTLReaction());
        r72.flags |= 8;
    L140:
        r72.filter = new TLRPC.TL_inputMessagesFilterEmpty();
        this.lastSearchQuery = r49;
        boolean r53 = r11;
        boolean r33 = r28;
        this.reqId = getConnectionsManager().sendRequest(r72, new MediaDataController$$ExternalSyntheticLambda124(this, r72, r33, r49, r53, r23, r43, r13, r32, r36, r72.offset_id, r34, r38, r41, r12, r15), 2);
        return;
    L132:
        if (r13 != getUserConfig().getClientUserId()) goto L134;
    L137:
        r72.saved_peer_id = getMessagesController().getInputPeer(this.lastReplyMessageId);
        r72.flags |= 4;
        goto L138
    L134:
        if (getMessagesStorage().isMonoForum(r13) == true) goto L137;
        r72.top_msg_id = (int) this.lastReplyMessageId;
        r72.flags |= 2;
        goto L138
    L125:
        r22 = r04.size();
        goto L126
    L121:
        r03 = 0;
    L128:
        r23 = r62;
        goto L127
    L114:
        r28 = false;
        goto L115
    L109:
        if (r12 == null) goto L111;
        r72.from_id = MessagesController.getInputPeer(r12);
        r72.flags |= 1;
        goto L111
    L104:
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
    L96:
        r2 = r32;
        r8 = r41;
        r12 = r42;
        r17 = 1;
        r20 = r7;
        r15 = r44;
        goto L97
    L58:
        if (r7.trim().startsWith("$") == true) goto L59;
    L61:
        r11 = false;
        goto L62
    L60:
        r25 = 0;
        goto L61
    L11:
        if (this.searchResultMessages.isEmpty() == false) goto L14;
        this.loadingMoreSearchMessages = false;
        return;
    L14:
        if (r37 != 1) goto L38;
        int r05 = this.lastReturnedNum + 1;
        this.lastReturnedNum = r05;
        if (r05 >= this.searchResultMessages.size()) goto L19;
        MessageObject r06 = this.searchResultMessages.get(this.lastReturnedNum);
        getNotificationCenter().postNotificationName(NotificationCenter.chatSearchResultsAvailable, new Object[]{Integer.valueOf(r36), Integer.valueOf(r06.getId()), Integer.valueOf(getMask()), Long.valueOf(r06.getDialogId()), Integer.valueOf(this.lastReturnedNum), Integer.valueOf(getSearchCount()), Boolean.valueOf(r43)});
        this.loadingMoreSearchMessages = false;
        return;
    L19:
        boolean[] r07 = this.messagesSearchEndReached;
        if (r07[0] == true) goto L22;
    L27:
        String r08 = this.lastSearchQuery;
        ArrayList<MessageObject> r410 = this.searchResultMessages;
        MessageObject r411 = r410.get(r410.size() - 1);
        if (r411.getDialogId() != r32) goto L33;
        if (this.messagesSearchEndReached[0] == true) goto L33;
        r47 = r411.getId();
        r13 = r32;
    L37:
        r7 = r08;
        r0 = false;
    L33:
        if (r411.getDialogId() != r34) goto L35;
        r47 = r411.getId();
    L36:
        this.messagesSearchEndReached[1] = false;
        r13 = r34;
        goto L37
    L35:
        r47 = 0;
        goto L36
    L22:
        if (r34 != 0) goto L27;
        if (r07[1] == false) goto L27;
        this.lastReturnedNum--;
        this.loadingMoreSearchMessages = false;
        return;
    L38:
        if (r37 != 2) goto L48;
        int r09 = this.lastReturnedNum - 1;
        this.lastReturnedNum = r09;
        if (r09 >= 0) goto L44;
        this.lastReturnedNum = 0;
        return;
    L44:
        if (r09 < this.searchResultMessages.size()) goto L46;
        this.lastReturnedNum = this.searchResultMessages.size() - 1;
    L46:
        MessageObject r010 = this.searchResultMessages.get(this.lastReturnedNum);
        getNotificationCenter().postNotificationName(NotificationCenter.chatSearchResultsAvailable, new Object[]{Integer.valueOf(r36), Integer.valueOf(r010.getId()), Integer.valueOf(getMask()), Long.valueOf(r010.getDialogId()), Integer.valueOf(this.lastReturnedNum), Integer.valueOf(getSearchCount()), Boolean.valueOf(r43)});
        this.loadingMoreSearchMessages = false;
        return;
    L48:
        this.loadingMoreSearchMessages = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void searchStickerSets(boolean r2, String r3, Utilities.Callback<ArrayList<TLRPC.StickerSetCovered>> r4) {
        if (r2 == false) goto L4;
        TLRPC.TL_messages_searchEmojiStickerSets r22 = new TLRPC.TL_messages_searchEmojiStickerSets();
        r22.q = r3;
        TLRPC.TL_messages_searchStickerSets r23 = r22;
    L5:
        getConnectionsManager().sendRequestTyped(r23, new AiTonesController$$ExternalSyntheticLambda0(), new MediaDataController$$ExternalSyntheticLambda24(r4));
        return;
    L4:
        TLRPC.TL_messages_searchStickerSets r24 = new TLRPC.TL_messages_searchStickerSets();
        r24.q = r3;
        r23 = r24;
        goto L5
    }

    public SearchStickersKey searchStickers(boolean r7, String r8, String r9, Utilities.Callback<ArrayList<TLRPC.Document>> r10) {
        return searchStickers(r7, r8, r9, r10, false);
    }

    public SearchStickersKey searchStickers(boolean r8, String r9, String r10, Utilities.Callback<ArrayList<TLRPC.Document>> r11, boolean r12) {
        if (r11 != null) goto L5;
        return null;
    L5:
        SearchStickersKey r0 = new SearchStickersKey(r8, r9, r10);
        SearchStickersResult r82 = this.searchStickerResults.get(r0);
        if (r82 == null) goto L11;
        if (r82.next_offset == null) goto L14;
        if (r12 == true) goto L11;
    L14:
        if (r82 == null) goto L17;
        r11.run(r82.documents);
        return r0;
    L17:
        r11.run(new ArrayList());
        return r0;
    L11:
        if (this.loadingSearchStickersKeys.containsKey(r0) == true) goto L14;
        this.loadingSearchStickersKeys.put(r0, 0);
        getInstance(this.currentAccount).getEmojiSuggestions(new String[]{r9}, r10, true, new MediaDataController$$ExternalSyntheticLambda161(this, r0, r82, r11), false);
        return r0;
    }

    public void setDoubleTapReaction(String r3) {
        MessagesController.getEmojiSettings(this.currentAccount).edit().putString("reaction_on_double_tap", r3).apply();
        this.doubleTapReaction = r3;
    }

    public void setDraftFolderId(long r1, int r3) {
        this.draftsFolderIds.put(r1, Integer.valueOf(r3));
    }

    public void setDraftVoiceRegion(long r4, long r6, float r8, float r9) {
        DraftVoice r0 = getDraftVoice(r4, r6);
        if (r0 != null) goto L5;
        return;
    L5:
        if (Math.abs(r0.left - r8) < 0.001f) goto L7;
    L8:
        r0.left = r8;
        r0.right = r9;
        ApplicationLoader.applicationContext.getSharedPreferences("2voicedrafts_" + this.currentAccount, 0).edit().putString(Objects.hash(new Object[]{Long.valueOf(r4), Long.valueOf(r6)}) + _UrlKt.FRAGMENT_ENCODE_SET, r0.toString()).apply();
        return;
    L7:
        if (Math.abs(r0.right - r9) >= 0.001f) goto L8;
    }

    public void setPlaceholderImage(BackupImageView r4, String r5, String r6, String r7) {
        TLRPC.TL_inputStickerSetShortName r0 = new TLRPC.TL_inputStickerSetShortName();
        r0.short_name = r5;
        getInstance(this.currentAccount).getStickerSet(r0, 0, false, new MediaDataController$$ExternalSyntheticLambda105(r6, r4, r7));
    }

    public void setPlaceholderImageByIndex(BackupImageView r7, String r8, int r9, String r10) {
        setPlaceholderImageByIndex(r7, r8, r9, r10, null);
    }

    public void setPlaceholderImageByIndex(BackupImageView r9, String r10, int r11, String r12, Theme.ResourcesProvider r13) {
        TLRPC.TL_inputStickerSetShortName r0 = new TLRPC.TL_inputStickerSetShortName();
        r0.short_name = r10;
        String r3 = "sticker_" + r10 + "_" + r11;
        r9.setTag(r3);
        r9.setImageDrawable(null);
        r9.setColorFilter(null);
        getInstance(this.currentAccount).getStickerSet(r0, 0, false, new MediaDataController$$ExternalSyntheticLambda6(r3, r9, r11, r13, r12));
    }

    public void setSearchedPosition(int r2) {
        if (r2 >= 0) goto L4;
        return;
    L4:
        if (r2 >= this.searchResultMessages.size()) goto L9;
        this.lastReturnedNum = r2;
        return;
    }

    public void storeTempStickerSet(TLRPC.TL_messages_stickerSet r5) {
        if (r5 == null) goto L10;
        TLRPC.StickerSet r0 = r5.set;
        if (r0 == null) goto L12;
        this.stickerSetsById.put(r0.id, r5);
        String r02 = r5.set.short_name;
        if (r02 == null) goto L11;
        this.stickerSetsByName.put(r02.toLowerCase(), r5);
        return;
    L11:
        return;
    L12:
        return;
    }

    public CharSequence substring(CharSequence r1, int r2, int r3) {
        if ((r1 instanceof SpannableStringBuilder) == false) goto L7;
        return r1.subSequence(r2, r3);
    L7:
        if ((r1 instanceof SpannedString) == false) goto L11;
        return r1.subSequence(r2, r3);
    L11:
        return TextUtils.substring(r1, r2, r3);
    }

    public void toggleDraftVoiceOnce(long r4, long r6, boolean r8) {
        DraftVoice r0 = getDraftVoice(r4, r6);
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.once == r8) goto L9;
        r0.once = r8;
        ApplicationLoader.applicationContext.getSharedPreferences("2voicedrafts_" + this.currentAccount, 0).edit().putString(Objects.hash(new Object[]{Long.valueOf(r4), Long.valueOf(r6)}) + _UrlKt.FRAGMENT_ENCODE_SET, r0.toString()).apply();
        return;
    }

    public void toggleStickerSet(Context r12, TLObject r13, int r14, BaseFragment r15, FrameLayout r16, boolean r17, boolean r18, Runnable r19, boolean r20) {
        toggleStickerSet(r12, r13, null, r14, r15, r16, r17, r18, r19, r20);
    }

    public void toggleStickerSet(Context r10, TLObject r11, int r12, BaseFragment r13, boolean r14, boolean r15) {
        toggleStickerSet(r10, r11, r12, r13, r14, r15, null, true);
    }

    public void toggleStickerSet(Context r11, TLObject r12, int r13, BaseFragment r14, boolean r15, boolean r16, Runnable r17, boolean r18) {
        toggleStickerSet(r11, r12, r13, r14, null, r15, r16, r17, r18);
    }

    public void toggleStickerSet(Context r17, TLObject r18, TLRPC.Document r19, int r20, BaseFragment r21, FrameLayout r22, boolean r23, boolean r24, Runnable r25, boolean r26) {
        if ((r18 instanceof TLRPC.TL_messages_stickerSet) == false) goto L7;
        TLRPC.TL_messages_stickerSet r1 = (TLRPC.TL_messages_stickerSet) r18;
        TLRPC.StickerSet r2 = r1.set;
    L5:
        TLRPC.TL_messages_stickerSet r9 = r1;
        TLRPC.StickerSet r10 = r2;
        if (r10.masks == false) goto L18;
        int r12 = 1;
    L21:
        if (r20 != 1) goto L23;
        boolean r27 = true;
    L24:
        r10.archived = r27;
        int r28 = 0;
    L26:
        if (r28 >= this.stickerSets[r12].size()) goto L36;
        TLRPC.TL_messages_stickerSet r3 = this.stickerSets[r12].get(r28);
        if (r3.set.id == r10.id) goto L29;
        r28 = r28 + 1;
        goto L26
    L29:
        this.stickerSets[r12].remove(r28);
        if (r20 != 2) goto L32;
        r3.set.title = r10.title;
        this.stickerSets[r12].add(0, r3);
    L34:
        int r13 = r28;
    L37:
        this.loadHash[r12] = calcStickersHash(this.stickerSets[r12]);
        putStickersToCache(r12, this.stickerSets[r12], this.loadDate[r12], this.loadHash[r12]);
        if (r20 == 2) goto L40;
        int r7 = r12;
        if (r24 == false) goto L46;
        if (r21 == null) goto L46;
        StickerSetBulletinLayout r0 = new StickerSetBulletinLayout(r17, r18, r20, r19, r21.getResourceProvider());
        boolean[] r29 = new boolean[1];
        markSetUninstalling(r10.id, true);
        int r92 = r7;
        Bulletin.UndoButton r14 = new Bulletin.UndoButton(r17, false).setUndoAction(new MediaDataController$$ExternalSyntheticLambda231(this, r29, r10, r7, r13, r9, r25)).setDelayedAction(new MediaDataController$$ExternalSyntheticLambda232(this, r29, r17, r20, r21, r22, r23, r18, r10, r19, r7));
        r0.setButton(r14);
        LongSparseArray<Runnable> r210 = this.removingStickerSetsUndos;
        long r5 = r10.id;
        Objects.requireNonNull(r14);
        r210.put(r5, new MediaDataController$$ExternalSyntheticLambda155(r14));
        if (r22 == null) goto L50;
        Bulletin.make(r22, r0, 2750).show();
    L52:
        getNotificationCenter().postNotificationName(NotificationCenter.stickersDidLoad, new Object[]{Integer.valueOf(r92), Boolean.TRUE});
        return;
    L50:
        Bulletin.make(r21, r0, 2750).show();
    L46:
        r92 = r7;
        toggleStickerSetInternal(r17, r20, r21, r22, r23, r18, r10, r19, r92, false);
        goto L52
    L40:
        if (cancelRemovingStickerSet(r10.id) == true) goto L42;
        r92 = r12;
        toggleStickerSetInternal(r17, r20, r21, r22, r23, r18, r10, r19, r92, r24);
        goto L52
    L42:
        r92 = r12;
        goto L52
    L32:
        if (r26 == false) goto L34;
        this.stickerSetsById.remove(r3.set.id);
        this.installedStickerSetsById.remove(r3.set.id);
        this.stickerSetsByName.remove(r3.set.short_name);
        goto L34
    L36:
        r13 = 0;
        goto L37
    L23:
        r27 = false;
        goto L24
    L18:
        if (r10.emojis == false) goto L20;
        r12 = 5;
        goto L21
    L20:
        r12 = 0;
        goto L21
    L7:
        if ((r18 instanceof TLRPC.StickerSetCovered) == false) goto L54;
        r2 = ((TLRPC.StickerSetCovered) r18).set;
        if (r20 == 2) goto L13;
        r1 = this.stickerSetsById.get(r2.id);
        if (r1 != null) goto L5;
        return;
    L13:
        r1 = null;
        goto L5
    L54:
        Buffer$$ExternalSyntheticBUOutline4.m("Invalid type of the given stickerSetObject: ", r18.getClass());
    }

    public void toggleStickerSets(ArrayList<TLRPC.StickerSet> r17, int r18, int r19, BaseFragment r20, boolean r21) {
        int r1 = r17.size();
        ArrayList<TLRPC.InputStickerSet> r7 = new ArrayList(r1);
        int r3 = 0;
    L3:
        boolean r9 = true;
        if (r3 >= r1) goto L21;
        TLRPC.StickerSet r5 = r17.get(r3);
        TLRPC.TL_inputStickerSetID r10 = new TLRPC.TL_inputStickerSetID();
        r10.access_hash = r5.access_hash;
        r10.id = r5.id;
        r7.add(r10);
        if (r19 == 0) goto L11;
        if (r19 == 1) goto L10;
        r9 = false;
    L10:
        r5.archived = r9;
    L11:
        int r52 = this.stickerSets[r18].size();
        int r92 = 0;
    L12:
        if (r92 >= r52) goto L20;
        TLRPC.TL_messages_stickerSet r11 = this.stickerSets[r18].get(r92);
        if (r11.set.id == r10.id) goto L15;
        r92 = r92 + 1;
        goto L12
    L15:
        this.stickerSets[r18].remove(r92);
        if (r19 != 2) goto L18;
        this.stickerSets[r18].add(0, r11);
        goto L20
    L18:
        this.stickerSetsById.remove(r11.set.id);
        this.installedStickerSetsById.remove(r11.set.id);
        this.stickerSetsByName.remove(r11.set.short_name);
    L20:
        r3 = r3 + 1;
        goto L3
    L21:
        this.loadHash[r18] = calcStickersHash(this.stickerSets[r18]);
        putStickersToCache(r18, this.stickerSets[r18], this.loadDate[r18], this.loadHash[r18]);
        getNotificationCenter().postNotificationName(NotificationCenter.stickersDidLoad, new Object[]{Integer.valueOf(r18), Boolean.TRUE});
        TLRPC.TL_messages_toggleStickerSets r102 = new TLRPC.TL_messages_toggleStickerSets();
        r102.stickersets = r7;
        if (r19 == 0) goto L28;
        if (r19 == 1) goto L27;
        if (r19 != 2) goto L29;
        r102.unarchive = true;
    L29:
        getConnectionsManager().sendRequest(r102, new MediaDataController$$ExternalSyntheticLambda52(this, r19, r20, r21, r18));
        return;
    L27:
        r102.archive = true;
        goto L29
    L28:
        r102.uninstall = true;
        goto L29
    }

    public void uninstallShortcut(long r7, int r9) {
        int r3 = Build.VERSION.SDK_INT;     // Catch: Exception -> L46
        if (r3 < 26) goto L15;
        ArrayList r6 = new ArrayList();     // Catch: Exception -> L46
        if (r9 != SHORTCUT_TYPE_USER_OR_CHAT) goto L9;
        r6.add("sdid_" + r7);     // Catch: Exception -> L46
        r6.add("ndid_" + r7);     // Catch: Exception -> L46
    L9:
        if (r9 != SHORTCUT_TYPE_ATTACHED_BOT) goto L11;
        r6.add("bdid_" + r7);     // Catch: Exception -> L46
    L11:
        ShortcutManagerCompat.removeDynamicShortcuts(ApplicationLoader.applicationContext, r6);     // Catch: Exception -> L46
        if (r3 < 30) goto L50;
        ShortcutManagerCompat$$ExternalSyntheticApiModelOutline1.m(ApplicationLoader.applicationContext.getSystemService(ShortcutManagerCompat$$ExternalSyntheticApiModelOutline0.m())).removeLongLivedShortcuts(r6);     // Catch: Exception -> L46
        return;
    L50:
        return;
    L15:
        TLRPC.User r1 = null;
        if (DialogObject.isEncryptedDialog(r7) == false) goto L23;
        TLRPC.EncryptedChat r0 = getMessagesController().getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r7)));     // Catch: Exception -> L46
        if (r0 == null) goto L51;
        TLRPC.User r02 = getMessagesController().getUser(Long.valueOf(r0.user_id));     // Catch: Exception -> L46
    L21:
        r1 = r02;
        TLRPC.Chat r03 = null;
    L28:
        if (r1 != null) goto L31;
        if (r03 != null) goto L31;
        return;
    L31:
        if (r1 != null) goto L33;
        String r04 = r03.title;     // Catch: Exception -> L46
    L41:
        if (r9 != SHORTCUT_TYPE_USER_OR_CHAT) goto L43;
        Intent r62 = createIntrnalShortcutIntent(r7);     // Catch: Exception -> L46
    L44:
        Intent r72 = new Intent();     // Catch: Exception -> L46
        r72.putExtra("android.intent.extra.shortcut.INTENT", r62);     // Catch: Exception -> L46
        r72.putExtra("android.intent.extra.shortcut.NAME", r04);     // Catch: Exception -> L46
        r72.putExtra("duplicate", false);     // Catch: Exception -> L46
        r72.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");     // Catch: Exception -> L46
        ApplicationLoader.applicationContext.sendBroadcast(r72);     // Catch: Exception -> L46
        return;
    L43:
        r62 = createIntrnalAttachedBotShortcutIntent(r7);     // Catch: Exception -> L46
        goto L44
    L33:
        if (r9 != SHORTCUT_TYPE_USER_OR_CHAT) goto L36;
        r04 = ContactsController.formatName(r1.first_name, r1.last_name);     // Catch: Exception -> L46
        goto L41
    L36:
        if (r9 != SHORTCUT_TYPE_ATTACHED_BOT) goto L38;
        r04 = r1.first_name;     // Catch: Exception -> L46
        goto L41
    L38:
        r04 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L41
    L51:
        return;
    L23:
        if (DialogObject.isUserDialog(r7) == false) goto L26;
        r02 = getMessagesController().getUser(Long.valueOf(r7));     // Catch: Exception -> L46
        goto L21
    L26:
        if (DialogObject.isChatDialog(r7) == false) goto L52;
        r03 = getMessagesController().getChat(Long.valueOf(-r7));     // Catch: Exception -> L46
        goto L28
    L52:
        return;
    L46:
        e = move-exception;
        FileLog.e(e);
    }

    public void updateAttachMenuBotsInCache() {
        if (getAttachMenuBots() == null) goto L6;
        putMenuBotsToCache(getAttachMenuBots(), this.menuBotsUpdateHash, this.menuBotsUpdateDate);
        return;
    }

    public void updateBotInfo(long r5, TL_update.TL_updateBotCommands r7) {
        TL_bots.BotInfo r0 = this.botInfos.get(r7.bot_id + "_" + r5);
        if (r0 == null) goto L5;
        r0.commands = r7.commands;
        getNotificationCenter().postNotificationName(NotificationCenter.botInfoDidLoad, new Object[]{r0, 0});
    L5:
        getMessagesStorage().getStorageQueue().postRunnable(new MediaDataController$$ExternalSyntheticLambda7(this, r7, r5));
    }

    public void uploadRingtone(String r4) {
        if (this.ringtoneUploaderHashMap.containsKey(r4) == false) goto L5;
        return;
    L5:
        this.ringtoneUploaderHashMap.put(r4, new RingtoneUploader(r4, this.currentAccount));
        this.ringtoneDataStore.addUploadingTone(r4);
    }

    public void verifyAnimatedStickerMessage(TLRPC.Message r2) {
        verifyAnimatedStickerMessage(r2, false);
    }

    public void verifyAnimatedStickerMessage(TLRPC.Message r8, boolean r9) {
        if (r8 == null) goto L18;
        TLRPC.Document r0 = MessageObject.getDocument(r8);
        String r1 = MessageObject.getStickerSetName(r0);
        if (TextUtils.isEmpty(r1) == true) goto L28;
        TLRPC.TL_messages_stickerSet r2 = this.stickerSetsByName.get(r1);
        if (r2 == null) goto L19;
        int r7 = r2.documents.size();
        int r92 = 0;
    L10:
        if (r92 >= r7) goto L29;
        TLRPC.Document r12 = r2.documents.get(r92);
        if (r12.id != r0.id) goto L17;
        if (r12.dc_id != r0.dc_id) goto L17;
        r8.stickerVerified = 1;
        return;
    L17:
        r92 = r92 + 1;
        goto L10
    L29:
        return;
    L19:
        if (r9 == false) goto L22;
        AndroidUtilities.runOnUIThread(new MediaDataController$$ExternalSyntheticLambda196(this, r8, r1));
        return;
    L22:
        verifyAnimatedStickerMessageInternal(r8, r1);
        return;
    L28:
        return;
    }
}
