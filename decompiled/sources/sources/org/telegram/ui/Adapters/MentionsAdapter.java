package org.telegram.ui.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.SearchAdapterHelper;
import org.telegram.ui.Business.QuickRepliesActivity;
import org.telegram.ui.Business.QuickRepliesController;
import org.telegram.ui.Cells.BotSwitchCell;
import org.telegram.ui.Cells.ContextLinkCell;
import org.telegram.ui.Cells.MentionCell;
import org.telegram.ui.Cells.StickerCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.PremiumPreviewFragment;

/* loaded from: classes6.dex */
public class MentionsAdapter extends RecyclerListView.SelectionAdapter implements NotificationCenter.NotificationCenterDelegate {
    private final boolean USE_DIVIDERS;
    private boolean allowBots;
    private boolean allowChats;
    private boolean allowStickers;
    private LongSparseArray<TL_bots.BotInfo> botInfo;
    private int botsCount;
    private HashtagHint bottomHint;
    private Runnable cancelDelayRunnable;
    private int channelLastReqId;
    private int channelReqId;
    public TLRPC.Chat chat;
    private Runnable checkAgainRunnable;
    private boolean contextMedia;
    private int contextQueryReqid;
    private Runnable contextQueryRunnable;
    private int contextUsernameReqid;
    private int currentAccount;
    private boolean delayLocalResults;
    private MentionsAdapterDelegate delegate;
    private long dialog_id;
    private TLRPC.User foundContextBot;
    private String hintHashtag;
    private boolean hintHashtagDivider;
    private TLRPC.ChatFull info;
    private boolean inlineMediaEnabled;
    private boolean isDarkTheme;
    private boolean isReversed;
    private boolean isSearchingMentions;
    private Object[] lastData;
    private boolean lastForSearch;
    private int lastItemCount;
    private Location lastKnownLocation;
    private int lastPosition;
    private int lastReqId;
    private String[] lastSearchKeyboardLanguage;
    private String lastSticker;
    private String lastText;
    private boolean lastUsernameOnly;
    private SendMessagesHelper.LocationProvider locationProvider;
    private final Context mContext;
    private EmojiView.ChooseStickerActionTracker mentionsStickersActionTracker;
    private ArrayList<MessageObject> messages;
    private boolean needBotContext;
    private boolean needUsernames;
    private String nextQueryOffset;
    private boolean noUserName;
    private final NotificationCenter.ObserversGroup observersGroup;
    public ChatActivity parentFragment;
    private ArrayList<QuickRepliesController.QuickReply> quickReplies;
    private String quickRepliesQuery;
    private final Theme.ResourcesProvider resourcesProvider;
    private int resultLength;
    private int resultStartPosition;
    private SearchAdapterHelper searchAdapterHelper;
    private Runnable searchGlobalRunnable;
    private boolean searchInDialogs;
    private ArrayList<TLRPC.BotInlineResult> searchResultBotContext;
    private TLRPC.TL_inlineBotSwitchPM searchResultBotContextSwitch;
    private long searchResultBotContextSwitchUserId;
    private TLRPC.TL_inlineBotWebView searchResultBotWebViewSwitch;
    private ArrayList<String> searchResultCommands;
    private ArrayList<Boolean> searchResultCommandsEphemeral;
    private ArrayList<String> searchResultCommandsHelp;
    private ArrayList<TLRPC.User> searchResultCommandsUsers;
    private ArrayList<String> searchResultHashtags;
    private ArrayList<MediaDataController.KeywordResult> searchResultSuggestions;
    private ArrayList<TLObject> searchResultUsernames;
    private LongSparseArray<TLObject> searchResultUsernamesMap;
    private String searchingContextQuery;
    private String searchingContextUsername;
    private int senderSearchId;
    private Runnable senderSearchRunnable;
    private ArrayList<StickerResult> stickers;
    private HashMap<String, TLRPC.Document> stickersMap;
    private ArrayList<String> stickersToLoad;
    private final boolean stories;
    private long threadMessageId;
    private HashtagHint topHint;
    private TLRPC.User user;
    private boolean visibleByStickersSearch;

    /* renamed from: org.telegram.ui.Adapters.MentionsAdapter$1, reason: invalid class name */
    public class AnonymousClass1 implements SendMessagesHelper.LocationProvider.LocationProviderDelegate {
        final /* synthetic */ MentionsAdapter this$0;

        public AnonymousClass1(MentionsAdapter r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.messenger.SendMessagesHelper.LocationProvider.LocationProviderDelegate
        public void onLocationAcquired(Location r4) {
            if (MentionsAdapter.m5912$$Nest$fgetfoundContextBot(this.this$0) != null) goto L5;
            return;
        L5:
            if (MentionsAdapter.m5912$$Nest$fgetfoundContextBot(this.this$0).bot_inline_geo == false) goto L9;
            MentionsAdapter.m5930$$Nest$fputlastKnownLocation(this.this$0, r4);
            MentionsAdapter r42 = this.this$0;
            MentionsAdapter.m5936$$Nest$msearchForContextBotResults(r42, true, MentionsAdapter.m5912$$Nest$fgetfoundContextBot(r42), MentionsAdapter.m5923$$Nest$fgetsearchingContextQuery(this.this$0), _UrlKt.FRAGMENT_ENCODE_SET);
            return;
        }

        @Override // org.telegram.messenger.SendMessagesHelper.LocationProvider.LocationProviderDelegate
        public void onUnableLocationAcquire() {
            MentionsAdapter.m5934$$Nest$monLocationUnavailable(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Adapters.MentionsAdapter$2, reason: invalid class name */
    public class AnonymousClass2 extends SendMessagesHelper.LocationProvider {
        final /* synthetic */ MentionsAdapter this$0;

        public AnonymousClass2(MentionsAdapter r1, SendMessagesHelper.LocationProvider.LocationProviderDelegate r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.messenger.SendMessagesHelper.LocationProvider
        public void stop() {
            super.stop();
            MentionsAdapter.m5930$$Nest$fputlastKnownLocation(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Adapters.MentionsAdapter$3, reason: invalid class name */
    public class AnonymousClass3 implements SearchAdapterHelper.SearchAdapterHelperDelegate {
        final /* synthetic */ MentionsAdapter this$0;

        public AnonymousClass3(MentionsAdapter r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate
        public boolean canApplySearchResults(int r1) {
            if (r1 != 0) goto L4;
            return true;
        L4:
            if (r1 == MentionsAdapter.m5925$$Nest$fgetsenderSearchId(this.this$0)) goto L10;
            return false;
        L10:
            return true;
        }

        @Override // org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate
        public void onDataSetChanged(int r1) {
            if (r1 == 0) goto L4;
            return;
        L4:
            this.this$0.notifyDataSetChanged();
        }

        @Override // org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate
        public void onSetHashtags(ArrayList<SearchAdapterHelper.HashtagObject> r7, HashMap<String, SearchAdapterHelper.HashtagObject> r8) {
            if (MentionsAdapter.m5916$$Nest$fgetlastText(this.this$0) == null) goto L6;
            MentionsAdapter r0 = this.this$0;
            r0.searchUsernameOrHashtag(MentionsAdapter.m5916$$Nest$fgetlastText(r0), MentionsAdapter.m5915$$Nest$fgetlastPosition(this.this$0), MentionsAdapter.m5918$$Nest$fgetmessages(this.this$0), MentionsAdapter.m5917$$Nest$fgetlastUsernameOnly(this.this$0), MentionsAdapter.m5914$$Nest$fgetlastForSearch(this.this$0));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Adapters.MentionsAdapter$4, reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ MentionsAdapter this$0;
        final /* synthetic */ MessagesController val$messagesController;
        final /* synthetic */ MessagesStorage val$messagesStorage;
        final /* synthetic */ String val$query;
        final /* synthetic */ String val$username;

        public static /* synthetic */ void $r8$lambda$cFRs7nZxM1EZW_MoWKyLPas_nwA(AnonymousClass4 r0, String r1, MessagesController r2, MessagesStorage r3, TLObject r4, TLRPC.TL_error r5) {
            r0.lambda$run$1(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$j5OOHN7dE9rTtizfKFMcUZy8t6g(AnonymousClass4 r0, String r1, TLRPC.TL_error r2, TLObject r3, MessagesController r4, MessagesStorage r5) {
            r0.lambda$run$0(r1, r2, r3, r4, r5);
        }

        public AnonymousClass4(MentionsAdapter r1, String r2, String r3, MessagesController r4, MessagesStorage r5) {
            this.this$0 = r1;
            this.val$query = r2;
            this.val$username = r3;
            this.val$messagesController = r4;
            this.val$messagesStorage = r5;
        }

        private /* synthetic */ void lambda$run$0(String r2, TLRPC.TL_error r3, TLObject r4, MessagesController r5, MessagesStorage r6) {
            if (MentionsAdapter.m5924$$Nest$fgetsearchingContextUsername(this.this$0) != null) goto L5;
            return;
        L5:
            if (MentionsAdapter.m5924$$Nest$fgetsearchingContextUsername(this.this$0).equals(r2) == false) goto L15;
            TLRPC.User r22 = null;
            if (r3 != null) goto L12;
            TLRPC.TL_contacts_resolvedPeer r42 = (TLRPC.TL_contacts_resolvedPeer) r4;
            if (r42.users.isEmpty() == true) goto L12;
            TLRPC.User r32 = r42.users.get(0);
            r5.putUser(r32, false);
            r6.putUsersAndChats(r42.users, null, true, true);
            r22 = r32;
        L12:
            MentionsAdapter.m5935$$Nest$mprocessFoundUser(this.this$0, r22);
            MentionsAdapter.m5929$$Nest$fputcontextUsernameReqid(this.this$0, 0);
            return;
        }

        private /* synthetic */ void lambda$run$1(String r8, MessagesController r9, MessagesStorage r10, TLObject r11, TLRPC.TL_error r12) {
            AndroidUtilities.runOnUIThread(new MentionsAdapter$4$$ExternalSyntheticLambda1(this, r8, r12, r11, r9, r10));
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MentionsAdapter.m5909$$Nest$fgetcontextQueryRunnable(this.this$0) != this) goto L21;
            MentionsAdapter.m5928$$Nest$fputcontextQueryRunnable(this.this$0, null);
            if (MentionsAdapter.m5912$$Nest$fgetfoundContextBot(this.this$0) != null) goto L17;
            if (MentionsAdapter.m5919$$Nest$fgetnoUserName(this.this$0) == true) goto L17;
            MentionsAdapter.m5931$$Nest$fputsearchingContextUsername(this.this$0, this.val$username);
            TLObject r0 = this.val$messagesController.getUserOrChat(MentionsAdapter.m5924$$Nest$fgetsearchingContextUsername(this.this$0));
            if ((r0 instanceof TLRPC.User) == false) goto L14;
            MentionsAdapter.m5935$$Nest$mprocessFoundUser(this.this$0, (TLRPC.User) r0);
            return;
        L14:
            TLRPC.TL_contacts_resolveUsername r02 = new TLRPC.TL_contacts_resolveUsername();
            r02.username = MentionsAdapter.m5924$$Nest$fgetsearchingContextUsername(this.this$0);
            MentionsAdapter r1 = this.this$0;
            MentionsAdapter.m5929$$Nest$fputcontextUsernameReqid(r1, ConnectionsManager.getInstance(MentionsAdapter.m5910$$Nest$fgetcurrentAccount(r1)).sendRequest(r02, new MentionsAdapter$4$$ExternalSyntheticLambda0(this, this.val$username, this.val$messagesController, this.val$messagesStorage)));
            return;
        L17:
            if (MentionsAdapter.m5919$$Nest$fgetnoUserName(this.this$0) == false) goto L19;
            return;
        L19:
            MentionsAdapter r03 = this.this$0;
            MentionsAdapter.m5936$$Nest$msearchForContextBotResults(r03, true, MentionsAdapter.m5912$$Nest$fgetfoundContextBot(r03), this.val$query, _UrlKt.FRAGMENT_ENCODE_SET);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Adapters.MentionsAdapter$5, reason: invalid class name */
    public class AnonymousClass5 implements Comparator<StickerResult> {
        final /* synthetic */ MentionsAdapter this$0;
        final /* synthetic */ ArrayList val$favsStickers;
        final /* synthetic */ ArrayList val$recentStickers;

        public AnonymousClass5(MentionsAdapter r1, ArrayList r2, ArrayList r3) {
            this.this$0 = r1;
            this.val$favsStickers = r2;
            this.val$recentStickers = r3;
        }

        private int getIndex(StickerResult r7) {
            int r0 = 0;
            int r1 = 0;
        L4:
            if (r1 >= this.val$favsStickers.size()) goto L11;
            if (((TLRPC.Document) this.val$favsStickers.get(r1)).id == r7.sticker.id) goto L8;
            r1 = r1 + 1;
            goto L4
        L8:
            return r1 + 2000000;
        L11:
            if (r0 >= Math.min(20, this.val$recentStickers.size())) goto L17;
            if (((TLRPC.Document) this.val$recentStickers.get(r0)).id == r7.sticker.id) goto L15;
            r0 = r0 + 1;
            goto L11
        L15:
            return (this.val$recentStickers.size() - r0) + DurationKt.NANOS_IN_MILLIS;
        L17:
            return -1;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(StickerResult r1, StickerResult r2) {
            return compare2(r1, r2);
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(StickerResult r5, StickerResult r6) {
            boolean r0 = MessageObject.isAnimatedStickerDocument(r5.sticker, true);
            if (r0 != MessageObject.isAnimatedStickerDocument(r6.sticker, true)) goto L11;
            int r52 = getIndex(r5);
            int r4 = getIndex(r6);
            if (r52 <= r4) goto L7;
            return -1;
        L7:
            if (r52 >= r4) goto L9;
            return 1;
        L9:
            return 0;
        L11:
            if (r0 == false) goto L13;
            return -1;
        L13:
            return 1;
        }
    }

    /* renamed from: org.telegram.ui.Adapters.MentionsAdapter$6, reason: invalid class name */
    public class AnonymousClass6 implements Comparator<TLObject> {
        final /* synthetic */ MentionsAdapter this$0;
        final /* synthetic */ LongSparseArray val$newMap;
        final /* synthetic */ ArrayList val$users;

        public AnonymousClass6(MentionsAdapter r1, LongSparseArray r2, ArrayList r3) {
            this.this$0 = r1;
            this.val$newMap = r2;
            this.val$users = r3;
        }

        private long getId(TLObject r1) {
            if ((r1 instanceof TLRPC.User) == false) goto L7;
            return ((TLRPC.User) r1).id;
        L7:
            return -((TLRPC.Chat) r1).id;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(TLObject r1, TLObject r2) {
            return compare2(r1, r2);
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(TLObject r7, TLObject r8) {
            long r0 = getId(r7);
            long r72 = getId(r8);
            if (this.val$newMap.indexOfKey(r0) < 0) goto L8;
            if (this.val$newMap.indexOfKey(r72) < 0) goto L8;
            return 0;
        L8:
            if (this.val$newMap.indexOfKey(r0) < 0) goto L11;
            return -1;
        L11:
            if (this.val$newMap.indexOfKey(r72) < 0) goto L13;
            return 1;
        L13:
            int r02 = this.val$users.indexOf(Long.valueOf(r0));
            int r6 = this.val$users.indexOf(Long.valueOf(r72));
            if (r02 == (-1)) goto L21;
            if (r6 == (-1)) goto L21;
            if (r02 >= r6) goto L18;
            return -1;
        L18:
            if (r02 != r6) goto L20;
            return 0;
        L20:
            return 1;
        L21:
            if (r02 == (-1)) goto L24;
            if (r6 != (-1)) goto L24;
            return -1;
        L24:
            if (r02 != (-1)) goto L27;
            if (r6 == (-1)) goto L27;
            return 1;
        L27:
            return 0;
        }
    }

    /* renamed from: org.telegram.ui.Adapters.MentionsAdapter$7, reason: invalid class name */
    public class AnonymousClass7 implements Runnable {
        final /* synthetic */ MentionsAdapter this$0;
        final /* synthetic */ TLRPC.Chat val$chat;
        final /* synthetic */ MessagesController val$messagesController;
        final /* synthetic */ ArrayList val$newResult;
        final /* synthetic */ LongSparseArray val$newResultsMap;
        final /* synthetic */ long val$threadId;
        final /* synthetic */ String val$usernameString;

        public static /* synthetic */ void $r8$lambda$sAygAurSP9mNI8mbqknn4iuJ2EA(AnonymousClass7 r0, int r1, ArrayList r2, LongSparseArray r3, MessagesController r4, TLObject r5, TLRPC.TL_error r6) {
            r0.lambda$run$1(r1, r2, r3, r4, r5, r6);
        }

        public static /* synthetic */ void $r8$lambda$zKNGlFYhWSel0AkBXRWguEoa5p8(AnonymousClass7 r0, int r1, ArrayList r2, LongSparseArray r3, TLRPC.TL_error r4, TLObject r5, MessagesController r6) {
            r0.lambda$run$0(r1, r2, r3, r4, r5, r6);
        }

        public AnonymousClass7(MentionsAdapter r1, TLRPC.Chat r2, String r3, long r4, ArrayList r6, LongSparseArray r7, MessagesController r8) {
            this.this$0 = r1;
            this.val$chat = r2;
            this.val$usernameString = r3;
            this.val$threadId = r4;
            this.val$newResult = r6;
            this.val$newResultsMap = r7;
            this.val$messagesController = r8;
        }

        private /* synthetic */ void lambda$run$0(int r7, ArrayList r8, LongSparseArray r9, TLRPC.TL_error r10, TLObject r11, MessagesController r12) {
            if (MentionsAdapter.m5908$$Nest$fgetchannelReqId(this.this$0) != 0) goto L5;
        L40:
            MentionsAdapter.m5927$$Nest$fputchannelReqId(this.this$0, 0);
            return;
        L5:
            if (r7 != MentionsAdapter.m5907$$Nest$fgetchannelLastReqId(this.this$0)) goto L40;
            if (MentionsAdapter.m5922$$Nest$fgetsearchResultUsernamesMap(this.this$0) == null) goto L40;
            if (MentionsAdapter.m5921$$Nest$fgetsearchResultUsernames(this.this$0) == null) goto L40;
            MentionsAdapter.m5937$$Nest$mshowUsersResult(this.this$0, r8, r9, false);
            if (r10 != null) goto L39;
            TLRPC.TL_channels_channelParticipants r112 = (TLRPC.TL_channels_channelParticipants) r11;
            r12.putUsers(r112.users, false);
            r12.putChats(r112.chats, false);
            if (r112.participants.isEmpty() == true) goto L39;
            long r72 = UserConfig.getInstance(MentionsAdapter.m5910$$Nest$fgetcurrentAccount(this.this$0)).getClientUserId();
            int r92 = 0;
        L16:
            if (r92 >= r112.participants.size()) goto L39;
            long r2 = MessageObject.getPeerId(r112.participants.get(r92).peer);
            if (MentionsAdapter.m5922$$Nest$fgetsearchResultUsernamesMap(this.this$0).indexOfKey(r2) >= 0) goto L38;
            if (r2 != 0) goto L24;
            if (MentionsAdapter.m5922$$Nest$fgetsearchResultUsernamesMap(this.this$0).indexOfKey(r72) >= 0) goto L38;
        L24:
            if (MentionsAdapter.m5913$$Nest$fgetisSearchingMentions(this.this$0) == false) goto L26;
        L29:
            if (r2 < 0) goto L34;
            TLRPC.User r102 = r12.getUser(Long.valueOf(r2));
            if (r102 == null) goto L51;
            MentionsAdapter r0 = this.this$0;
            MentionsAdapter.m5933$$Nest$maddUserResult(r0, MentionsAdapter.m5921$$Nest$fgetsearchResultUsernames(r0), MentionsAdapter.m5922$$Nest$fgetsearchResultUsernamesMap(this.this$0), r102);
            goto L38
        L51:
            return;
        L34:
            TLRPC.Chat r103 = r12.getChat(Long.valueOf(-r2));
            if (r103 == null) goto L36;
            MentionsAdapter r02 = this.this$0;
            MentionsAdapter.m5932$$Nest$maddChatResult(r02, MentionsAdapter.m5921$$Nest$fgetsearchResultUsernames(r02), MentionsAdapter.m5922$$Nest$fgetsearchResultUsernamesMap(this.this$0), r103);
            goto L38
        L36:
            return;
        L26:
            if (r2 == r72) goto L38;
            if (r2 != 0) goto L29;
        L38:
            r92 = r92 + 1;
        L39:
            this.this$0.notifyDataSetChanged();
            MentionsAdapter.m5911$$Nest$fgetdelegate(this.this$0).needChangePanelVisibility(!MentionsAdapter.m5921$$Nest$fgetsearchResultUsernames(this.this$0).isEmpty());
            goto L40
        }

        private /* synthetic */ void lambda$run$1(int r9, ArrayList r10, LongSparseArray r11, MessagesController r12, TLObject r13, TLRPC.TL_error r14) {
            AndroidUtilities.runOnUIThread(new MentionsAdapter$7$$ExternalSyntheticLambda1(this, r9, r10, r11, r14, r13, r12));
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MentionsAdapter.m5920$$Nest$fgetsearchGlobalRunnable(this.this$0) == this) goto L5;
            return;
        L5:
            TLRPC.TL_channels_getParticipants r0 = new TLRPC.TL_channels_getParticipants();
            r0.channel = MessagesController.getInputChannel(this.val$chat);
            r0.limit = 20;
            r0.offset = 0;
            TLRPC.TL_channelParticipantsMentions r1 = new TLRPC.TL_channelParticipantsMentions();
            int r2 = r1.flags;
            r1.flags = r2 | 1;
            r1.q = this.val$usernameString;
            long r3 = this.val$threadId;
            if (r3 == 0) goto L8;
            r1.flags = r2 | 3;
            r1.top_msg_id = (int) r3;
        L8:
            r0.filter = r1;
            MentionsAdapter r12 = this.this$0;
            int r5 = MentionsAdapter.m5907$$Nest$fgetchannelLastReqId(r12) + 1;
            MentionsAdapter.m5926$$Nest$fputchannelLastReqId(r12, r5);
            MentionsAdapter r13 = this.this$0;
            MentionsAdapter.m5927$$Nest$fputchannelReqId(r13, ConnectionsManager.getInstance(MentionsAdapter.m5910$$Nest$fgetcurrentAccount(r13)).sendRequest(r0, new MentionsAdapter$7$$ExternalSyntheticLambda0(this, r5, this.val$newResult, this.val$newResultsMap, this.val$messagesController)));
        }
    }

    /* renamed from: org.telegram.ui.Adapters.MentionsAdapter$8, reason: invalid class name */
    public class AnonymousClass8 extends View {
        final /* synthetic */ MentionsAdapter this$0;

        public AnonymousClass8(MentionsAdapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(8.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Adapters.MentionsAdapter$9, reason: invalid class name */
    public class AnonymousClass9 extends EmojiView.ChooseStickerActionTracker {
        final /* synthetic */ MentionsAdapter this$0;

        public AnonymousClass9(MentionsAdapter r1, int r2, long r3, long r5) {
            this.this$0 = r1;
            super(r2, r3, r5);
        }

        @Override // org.telegram.ui.Components.EmojiView.ChooseStickerActionTracker
        public boolean isShown() {
            return this.this$0.isStickers();
        }
    }

    public static class EphemeralCommand {
        public final long botUserId;
        public final String command;

        public EphemeralCommand(String r1, long r2) {
            this.command = r1;
            this.botUserId = r2;
        }
    }

    public static class HashtagHint extends LinearLayout {
        private final AvatarDrawable avatarDrawable;
        private final BackupImageView imageView;
        private final Theme.ResourcesProvider resourcesProvider;
        private final LinearLayout textLayout;
        private final TextView textView;
        private final TextView titleView;
        private final boolean transparent;

        public HashtagHint(Context r10, boolean r11, Theme.ResourcesProvider r12) {
            super(r10);
            this.avatarDrawable = new AvatarDrawable();
            this.resourcesProvider = r12;
            this.transparent = r11;
            setOrientation(0);
            BackupImageView r0 = new BackupImageView(r10);
            this.imageView = r0;
            r0.setRoundRadius(AndroidUtilities.dp(28.0f));
            addView(r0, LayoutHelper.createLinear(28, 28, 19, 12, 0, 12, 0));
            LinearLayout r02 = new LinearLayout(r10);
            this.textLayout = r02;
            r02.setOrientation(1);
            addView(r02, LayoutHelper.createLinear(-1, -2, 55, 0, 4, 12, 4));
            TextView r2 = new TextView(r10);
            this.titleView = r2;
            r2.setTextSize(1, 15.0f);
            int r3 = Theme.key_windowBackgroundWhiteBlackText;
            r2.setTextColor(Theme.getColor(r3, r12));
            r02.addView(r2, LayoutHelper.createLinear(-1, -2));
            TextView r22 = new TextView(r10);
            this.textView = r22;
            r22.setTextSize(1, 13.0f);
            if (r11 == false) goto L5;
            int r9 = Theme.multAlpha(Theme.getColor(r3, r12), 0.5f);
        L6:
            r22.setTextColor(r9);
            r02.addView(r22, LayoutHelper.createLinear(-1, -2));
            return;
        L5:
            r9 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r12);
            goto L6
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void set(int r3, String r4, TLRPC.Chat r5) {
            if (r4 != null) goto L4;
            return;
        L4:
            if (r3 != 0) goto L7;
            CombinedDrawable r32 = new CombinedDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(28.0f), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider)), getContext().getResources().getDrawable(R.drawable.menu_hashtag).mutate());
            r32.setIconOffset(AndroidUtilities.dp(-0.66f), 0);
            r32.setIconSize(AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f));
            this.imageView.setImageDrawable(r32);
            this.titleView.setText(LocaleController.formatString(R.string.HashtagSuggestion1Title, new Object[]{r4}));
            this.textView.setText(LocaleController.getString(R.string.HashtagSuggestion1Text));
            return;
        L7:
            this.avatarDrawable.setInfo(r5);
            this.imageView.setForUserOrChat(r5, this.avatarDrawable);
            this.titleView.setText(PremiumPreviewFragment.applyNewSpan(LocaleController.formatString(R.string.HashtagSuggestion2Title, new Object[]{r4 + "@" + ChatObject.getPublicUsername(r5)}), 8));
            this.textView.setText(LocaleController.getString(R.string.HashtagSuggestion2Text));
        }
    }

    public interface MentionsAdapterDelegate {
        void needChangePanelVisibility(boolean r1);

        void onContextClick(TLRPC.BotInlineResult r1);

        void onContextSearch(boolean r1);

        void onItemCountUpdate(int r1, int r2);
    }

    public static class StickerResult {
        public Object parent;
        public TLRPC.Document sticker;

        public StickerResult(TLRPC.Document r1, Object r2) {
            this.sticker = r1;
            this.parent = r2;
        }
    }

    /* renamed from: $r8$lambda$2zMFKPtu-v5vS7Vm8_mqKTsfT0A, reason: not valid java name */
    public static /* synthetic */ void m5903$r8$lambda$2zMFKPtuv5vS7Vm8_mqKTsfT0A(MentionsAdapter r0, String r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$searchServerStickers$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$GGO6kSSOWX5_1t7erFmjGUF_0Fc(MentionsAdapter r0, ArrayList r1, LongSparseArray r2) {
        r0.lambda$searchUsernameOrHashtag$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$JsuwXgpzvB5jJTjQVuzz730StAQ(MentionsAdapter r0, ContextLinkCell r1) {
        r0.lambda$onCreateViewHolder$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$PTjPdEA5eLCYcEJtwYT4q5YxbEY(MentionsAdapter r0, CharSequence r1, int r2, ArrayList r3, boolean r4, boolean r5) {
        r0.lambda$searchUsernameOrHashtag$10(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$QA5nm5P_UjpFCA3LEpoZLZ9cWX0(MentionsAdapter r0, boolean[] r1, TLRPC.User r2, AlertDialog r3, int r4) {
        r0.lambda$processFoundUser$4(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$UzbXqokY507h45euhfH-BM-2GZk, reason: not valid java name */
    public static /* synthetic */ void m5904$r8$lambda$UzbXqokY507h45euhfHBM2GZk(MentionsAdapter r0, String r1, TLObject r2) {
        r0.lambda$searchServerStickers$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$cGyIfPlwaPcg3JLejiBMS9VV2_k(MentionsAdapter r0, String r1, boolean r2, TLObject r3, TLRPC.User r4, String r5, MessagesStorage r6, String r7) {
        r0.lambda$searchForContextBotResults$7(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$eSmWEhO7PS1dM50VbCGEkC9TabM(MentionsAdapter r0, boolean[] r1, AlertDialog r2, int r3) {
        r0.lambda$processFoundUser$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$kXeMbBZv2OiPZj0ucoq9JRDPEHA(MentionsAdapter r0, ArrayList r1, String r2) {
        r0.lambda$searchUsernameOrHashtag$12(r1, r2);
    }

    /* renamed from: $r8$lambda$lYBu-p90T3fM-namxdXeYKDXrhE, reason: not valid java name */
    public static /* synthetic */ void m5905$r8$lambda$lYBup90T3fMnamxdXeYKDXrhE(MentionsAdapter r0, boolean[] r1, DialogInterface r2) {
        r0.lambda$processFoundUser$6(r1, r2);
    }

    public static /* synthetic */ int $r8$lambda$oRQsfkpy4LJSylyBxJ0rRQZqBqc(TLRPC.TL_topPeer r2, TLRPC.TL_topPeer r3) {
        return Double.compare(r3.rating, r2.rating);
    }

    public static /* synthetic */ void $r8$lambda$wI5LA4AGMIw4wy5dsXJr4PCxfT8(MentionsAdapter r0, int r1, String r2, LongSparseArray r3, ArrayList r4) {
        r0.lambda$searchGlobalSenders$0(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$y15S-NNPIAPqL9JKgBB4gxgjN0M, reason: not valid java name */
    public static /* synthetic */ void m5906$r8$lambda$y15SNNPIAPqL9JKgBB4gxgjN0M(MentionsAdapter r0, String r1, boolean r2, TLRPC.User r3, String r4, MessagesStorage r5, String r6, TLObject r7, TLRPC.TL_error r8) {
        r0.lambda$searchForContextBotResults$8(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$yHm7g_Q706ukQ3hs8mtO2kyvrA0(MentionsAdapter r0, int r1, String r2, LongSparseArray r3, ArrayList r4) {
        r0.lambda$searchGlobalSenders$1(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetchannelLastReqId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5907$$Nest$fgetchannelLastReqId(MentionsAdapter r0) {
        return r0.channelLastReqId;
    }

    /* renamed from: -$$Nest$fgetchannelReqId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5908$$Nest$fgetchannelReqId(MentionsAdapter r0) {
        return r0.channelReqId;
    }

    /* renamed from: -$$Nest$fgetcontextQueryRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5909$$Nest$fgetcontextQueryRunnable(MentionsAdapter r0) {
        return r0.contextQueryRunnable;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5910$$Nest$fgetcurrentAccount(MentionsAdapter r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MentionsAdapterDelegate m5911$$Nest$fgetdelegate(MentionsAdapter r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetfoundContextBot, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.User m5912$$Nest$fgetfoundContextBot(MentionsAdapter r0) {
        return r0.foundContextBot;
    }

    /* renamed from: -$$Nest$fgetisSearchingMentions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5913$$Nest$fgetisSearchingMentions(MentionsAdapter r0) {
        return r0.isSearchingMentions;
    }

    /* renamed from: -$$Nest$fgetlastForSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5914$$Nest$fgetlastForSearch(MentionsAdapter r0) {
        return r0.lastForSearch;
    }

    /* renamed from: -$$Nest$fgetlastPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5915$$Nest$fgetlastPosition(MentionsAdapter r0) {
        return r0.lastPosition;
    }

    /* renamed from: -$$Nest$fgetlastText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m5916$$Nest$fgetlastText(MentionsAdapter r0) {
        return r0.lastText;
    }

    /* renamed from: -$$Nest$fgetlastUsernameOnly, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5917$$Nest$fgetlastUsernameOnly(MentionsAdapter r0) {
        return r0.lastUsernameOnly;
    }

    /* renamed from: -$$Nest$fgetmessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5918$$Nest$fgetmessages(MentionsAdapter r0) {
        return r0.messages;
    }

    /* renamed from: -$$Nest$fgetnoUserName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5919$$Nest$fgetnoUserName(MentionsAdapter r0) {
        return r0.noUserName;
    }

    /* renamed from: -$$Nest$fgetsearchGlobalRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5920$$Nest$fgetsearchGlobalRunnable(MentionsAdapter r0) {
        return r0.searchGlobalRunnable;
    }

    /* renamed from: -$$Nest$fgetsearchResultUsernames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5921$$Nest$fgetsearchResultUsernames(MentionsAdapter r0) {
        return r0.searchResultUsernames;
    }

    /* renamed from: -$$Nest$fgetsearchResultUsernamesMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m5922$$Nest$fgetsearchResultUsernamesMap(MentionsAdapter r0) {
        return r0.searchResultUsernamesMap;
    }

    /* renamed from: -$$Nest$fgetsearchingContextQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m5923$$Nest$fgetsearchingContextQuery(MentionsAdapter r0) {
        return r0.searchingContextQuery;
    }

    /* renamed from: -$$Nest$fgetsearchingContextUsername, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m5924$$Nest$fgetsearchingContextUsername(MentionsAdapter r0) {
        return r0.searchingContextUsername;
    }

    /* renamed from: -$$Nest$fgetsenderSearchId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5925$$Nest$fgetsenderSearchId(MentionsAdapter r0) {
        return r0.senderSearchId;
    }

    /* renamed from: -$$Nest$fputchannelLastReqId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5926$$Nest$fputchannelLastReqId(MentionsAdapter r0, int r1) {
        r0.channelLastReqId = r1;
    }

    /* renamed from: -$$Nest$fputchannelReqId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5927$$Nest$fputchannelReqId(MentionsAdapter r0, int r1) {
        r0.channelReqId = r1;
    }

    /* renamed from: -$$Nest$fputcontextQueryRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5928$$Nest$fputcontextQueryRunnable(MentionsAdapter r0, Runnable r1) {
        r0.contextQueryRunnable = r1;
    }

    /* renamed from: -$$Nest$fputcontextUsernameReqid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5929$$Nest$fputcontextUsernameReqid(MentionsAdapter r0, int r1) {
        r0.contextUsernameReqid = r1;
    }

    /* renamed from: -$$Nest$fputlastKnownLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5930$$Nest$fputlastKnownLocation(MentionsAdapter r0, Location r1) {
        r0.lastKnownLocation = r1;
    }

    /* renamed from: -$$Nest$fputsearchingContextUsername, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5931$$Nest$fputsearchingContextUsername(MentionsAdapter r0, String r1) {
        r0.searchingContextUsername = r1;
    }

    /* renamed from: -$$Nest$maddChatResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5932$$Nest$maddChatResult(MentionsAdapter r0, ArrayList r1, LongSparseArray r2, TLRPC.Chat r3) {
        return r0.addChatResult(r1, r2, r3);
    }

    /* renamed from: -$$Nest$maddUserResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5933$$Nest$maddUserResult(MentionsAdapter r0, ArrayList r1, LongSparseArray r2, TLRPC.User r3) {
        return r0.addUserResult(r1, r2, r3);
    }

    /* renamed from: -$$Nest$monLocationUnavailable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5934$$Nest$monLocationUnavailable(MentionsAdapter r0) {
        r0.onLocationUnavailable();
    }

    /* renamed from: -$$Nest$mprocessFoundUser, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5935$$Nest$mprocessFoundUser(MentionsAdapter r0, TLRPC.User r1) {
        r0.processFoundUser(r1);
    }

    /* renamed from: -$$Nest$msearchForContextBotResults, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5936$$Nest$msearchForContextBotResults(MentionsAdapter r0, boolean r1, TLRPC.User r2, String r3, String r4) {
        r0.searchForContextBotResults(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$mshowUsersResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5937$$Nest$mshowUsersResult(MentionsAdapter r0, ArrayList r1, LongSparseArray r2, boolean r3) {
        r0.showUsersResult(r1, r2, r3);
    }

    public MentionsAdapter(Context r5, boolean r6, long r7, long r9, MentionsAdapterDelegate r11, Theme.ResourcesProvider r12, boolean r13) {
        this.allowStickers = true;
        this.allowBots = true;
        this.allowChats = true;
        this.USE_DIVIDERS = false;
        this.currentAccount = UserConfig.selectedAccount;
        this.needUsernames = true;
        this.needBotContext = true;
        this.inlineMediaEnabled = true;
        this.searchInDialogs = false;
        this.stickersToLoad = new ArrayList();
        this.locationProvider = new AnonymousClass2(this, new AnonymousClass1(this));
        this.isReversed = false;
        this.lastItemCount = -1;
        this.resourcesProvider = r12;
        this.mContext = r5;
        this.delegate = r11;
        this.isDarkTheme = r6;
        this.dialog_id = r7;
        this.stories = r13;
        this.threadMessageId = r9;
        SearchAdapterHelper r52 = new SearchAdapterHelper(true);
        this.searchAdapterHelper = r52;
        r52.setDelegate(new AnonymousClass3(this));
        NotificationCenter.ObserversGroup r53 = NotificationCenter.getInstance(this.currentAccount).createWeakObserversGroup(this).add(NotificationCenter.recentDocumentsDidLoad).add(NotificationCenter.stickersDidLoad);
        this.observersGroup = r53;
        if (r6 == true) goto L6;
        r53.add(NotificationCenter.fileLoaded).add(NotificationCenter.fileLoadFailed);
        return;
    }

    private boolean addChatResult(ArrayList<TLObject> r3, LongSparseArray<TLObject> r4, TLRPC.Chat r5) {
        return SearchAdapterHelper.addUniqueObject(r3, r4, r5, -r5.id);
    }

    private void addStickerToResult(TLRPC.Document r4, Object r5) {
        if (r4 == null) goto L22;
        String r0 = r4.dc_id + "_" + r4.id;
        HashMap<String, TLRPC.Document> r1 = this.stickersMap;
        if (r1 == null) goto L10;
        if (r1.containsKey(r0) == false) goto L10;
        return;
    L10:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L15;
        if (MessageObject.isPremiumSticker(r4) == false) goto L15;
        return;
    L15:
        if (this.stickers != null) goto L17;
        this.stickers = new ArrayList();
        this.stickersMap = new HashMap();
    L17:
        this.stickers.add(new StickerResult(r4, r5));
        this.stickersMap.put(r0, r4);
        EmojiView.ChooseStickerActionTracker r3 = this.mentionsStickersActionTracker;
        if (r3 == null) goto L21;
        r3.checkVisibility();
        return;
    L21:
        return;
    }

    private void addStickersToResult(ArrayList<TLRPC.Document> r10, Object r11) {
        if (r10 != null) goto L4;
        return;
    L4:
        if (r10.isEmpty() == true) goto L36;
        int r0 = r10.size();
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L37;
        TLRPC.Document r3 = r10.get(r2);
        String r4 = r3.dc_id + "_" + r3.id;
        HashMap<String, TLRPC.Document> r5 = this.stickersMap;
        if (r5 == null) goto L14;
        if (r5.containsKey(r4) == false) goto L14;
    L28:
        r2 = r2 + 1;
    L14:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L16;
    L18:
        int r52 = r3.attributes.size();
        int r6 = 0;
    L19:
        if (r6 >= r52) goto L25;
        TLRPC.DocumentAttribute r7 = r3.attributes.get(r6);
        if ((r7 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L22;
        r6 = r6 + 1;
        goto L19
    L22:
        r11 = r7.stickerset;
    L25:
        if (this.stickers != null) goto L27;
        this.stickers = new ArrayList();
        this.stickersMap = new HashMap();
    L27:
        this.stickers.add(new StickerResult(r3, r11));
        this.stickersMap.put(r4, r3);
        goto L28
    L16:
        if (MessageObject.isPremiumSticker(r3) == false) goto L18;
    L37:
        return;
    }

    private boolean addUserResult(ArrayList<TLObject> r3, LongSparseArray<TLObject> r4, TLRPC.User r5) {
        return SearchAdapterHelper.addUniqueObject(r3, r4, r5, r5.id);
    }

    private void checkLocationPermissionsOrStart() {
        ChatActivity r0 = this.parentFragment;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getParentActivity() != null) goto L8;
        return;
    L8:
        if (this.parentFragment.getParentActivity().checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) goto L11;
        this.parentFragment.getParentActivity().requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, 2);
        return;
    L11:
        TLRPC.User r02 = this.foundContextBot;
        if (r02 != null) goto L14;
        return;
    L14:
        if (r02.bot_inline_geo == false) goto L19;
        this.locationProvider.start();
        return;
    }

    private boolean checkStickerFilesExistAndDownload() {
        int r1 = 0;
        if (this.stickers != null) goto L5;
        return false;
    L5:
        this.stickersToLoad.clear();
        int r0 = Math.min(6, this.stickers.size());
    L6:
        if (r1 >= r0) goto L16;
        StickerResult r2 = this.stickers.get(r1);
        TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r2.sticker.thumbs, 90);
        if ((r3 instanceof TLRPC.TL_photoSize) == true) goto L12;
        if ((r3 instanceof TLRPC.TL_photoSizeProgressive) == true) goto L12;
    L14:
        r1 = r1 + 1;
    L12:
        if (FileLoader.getInstance(this.currentAccount).getPathToAttach(r3, "webp", true).exists() == true) goto L14;
        this.stickersToLoad.add(FileLoader.getAttachFileName(r3, "webp"));
        FileLoader.getInstance(this.currentAccount).loadFile(ImageLocation.getForDocument(r3, r2.sticker), r2.parent, "webp", 1, 1);
        goto L14
    L16:
        return this.stickersToLoad.isEmpty();
    }

    private void clearStickers() {
        this.lastSticker = null;
        this.stickers = null;
        this.stickersMap = null;
        notifyDataSetChanged();
        this.visibleByStickersSearch = false;
        if (this.lastReqId == 0) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.lastReqId, true);
        this.lastReqId = 0;
    L5:
        EmojiView.ChooseStickerActionTracker r4 = this.mentionsStickersActionTracker;
        if (r4 == null) goto L9;
        r4.checkVisibility();
        return;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private boolean isValidSticker(TLRPC.Document r5, String r6) {
        int r4 = r5.attributes.size();
        int r1 = 0;
    L3:
        if (r1 >= r4) goto L13;
        TLRPC.DocumentAttribute r2 = r5.attributes.get(r1);
        if ((r2 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        String r42 = r2.alt;
        if (r42 == null) goto L13;
        if (r42.contains(r6) == false) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean itemsEqual(Object r6, Object r7) {
        if ((r6 instanceof QuickRepliesController.QuickReply) == false) goto L6;
        return false;
    L6:
        if (r6 != r7) goto L9;
        return true;
    L9:
        if ((r6 instanceof StickerResult) == false) goto L16;
        if ((r7 instanceof StickerResult) == false) goto L16;
        if (((StickerResult) r6).sticker != ((StickerResult) r7).sticker) goto L16;
        return true;
    L16:
        if ((r6 instanceof TLRPC.User) == false) goto L23;
        if ((r7 instanceof TLRPC.User) == false) goto L23;
        if (((TLRPC.User) r6).id != ((TLRPC.User) r7).id) goto L23;
        return true;
    L23:
        if ((r6 instanceof TLRPC.Chat) == false) goto L30;
        if ((r7 instanceof TLRPC.Chat) == false) goto L30;
        if (((TLRPC.Chat) r6).id != ((TLRPC.Chat) r7).id) goto L30;
        return true;
    L30:
        if ((r6 instanceof String) == false) goto L37;
        if ((r7 instanceof String) == false) goto L37;
        if (r6.equals(r7) == false) goto L37;
        return true;
    L37:
        if ((r6 instanceof MediaDataController.KeywordResult) == true) goto L39;
    L49:
        return false;
    L39:
        if ((r7 instanceof MediaDataController.KeywordResult) == false) goto L49;
        MediaDataController.KeywordResult r62 = (MediaDataController.KeywordResult) r6;
        String r1 = r62.keyword;
        if (r1 == null) goto L49;
        MediaDataController.KeywordResult r72 = (MediaDataController.KeywordResult) r7;
        if (r1.equals(r72.keyword) == false) goto L49;
        String r63 = r62.emoji;
        if (r63 == null) goto L49;
        if (r63.equals(r72.emoji) == false) goto L49;
        return true;
    }

    private /* synthetic */ void lambda$onCreateViewHolder$13(ContextLinkCell r1) {
        this.delegate.onContextClick(r1.getResult());
    }

    private /* synthetic */ void lambda$processFoundUser$4(boolean[] r3, TLRPC.User r4, AlertDialog r5, int r6) {
        r3[0] = true;
        if (r4 == null) goto L6;
        MessagesController.getNotificationsSettings(this.currentAccount).edit().putBoolean("inlinegeo_" + r4.id, true).apply();
        checkLocationPermissionsOrStart();
        return;
    }

    private /* synthetic */ void lambda$processFoundUser$5(boolean[] r1, AlertDialog r2, int r3) {
        r1[0] = true;
        onLocationUnavailable();
    }

    private /* synthetic */ void lambda$processFoundUser$6(boolean[] r1, DialogInterface r2) {
        if (r1[0] == true) goto L6;
        onLocationUnavailable();
        return;
    }

    private /* synthetic */ void lambda$searchForContextBotResults$7(String r2, boolean r3, TLObject r4, TLRPC.User r5, String r6, MessagesStorage r7, String r8) {
        if (r2.equals(this.searchingContextQuery) == false) goto L78;
        int r0 = 0;
        this.contextQueryReqid = 0;
        if (r3 == false) goto L9;
        if (r4 != null) goto L9;
        searchForContextBotResults(false, r5, r2, r6);
    L13:
        if ((r4 instanceof TLRPC.TL_messages_botResults) == false) goto L77;
        TLRPC.TL_messages_botResults r42 = (TLRPC.TL_messages_botResults) r4;
        if (r3 == false) goto L17;
    L19:
        this.nextQueryOffset = r42.next_offset;
        if (this.searchResultBotContextSwitch != null) goto L22;
        this.searchResultBotContextSwitch = r42.switch_pm;
    L22:
        this.searchResultBotWebViewSwitch = r42.switch_webview;
        int r22 = 0;
    L24:
        if (r22 >= r42.results.size()) goto L38;
        TLRPC.BotInlineResult r32 = r42.results.get(r22);
        if ((r32.document instanceof TLRPC.TL_document) == true) goto L36;
        if ((r32.photo instanceof TLRPC.TL_photo) == true) goto L36;
        if ("game".equals(r32.type) == true) goto L36;
        if (r32.content != null) goto L36;
        if ((r32.send_message instanceof TLRPC.TL_botInlineMessageMediaAuto) == false) goto L36;
        r42.results.remove(r22);
        r22 = r22 - 1;
    L36:
        r32.query_id = r42.query_id;
        r22 = r22 + 1;
        goto L24
    L38:
        if (this.searchResultBotContext != null) goto L40;
    L46:
        this.searchResultBotContext = r42.results;
        this.contextMedia = r42.gallery;
        boolean r23 = false;
    L47:
        Runnable r33 = this.cancelDelayRunnable;
        if (r33 == null) goto L50;
        AndroidUtilities.cancelRunOnUIThread(r33);
        this.cancelDelayRunnable = null;
    L50:
        this.searchResultHashtags = null;
        this.stickers = null;
        this.searchResultUsernames = null;
        this.searchResultUsernamesMap = null;
        this.searchResultCommands = null;
        this.searchResultCommandsEphemeral = null;
        this.quickReplies = null;
        this.searchResultSuggestions = null;
        this.searchResultCommandsHelp = null;
        this.searchResultCommandsUsers = null;
        this.visibleByStickersSearch = false;
        MentionsAdapterDelegate r34 = this.delegate;
        if (this.searchResultBotContext.isEmpty() == true) goto L53;
    L58:
        boolean r62 = true;
    L59:
        r34.needChangePanelVisibility(r62);
        if (r23 == true) goto L62;
        notifyDataSetChanged();
        return;
    L62:
        if (this.searchResultBotContextSwitch == null) goto L64;
    L65:
        r0 = 1;
    L66:
        notifyItemChanged(((this.searchResultBotContext.size() - r42.results.size()) + r0) - 1);
        notifyItemRangeInserted((this.searchResultBotContext.size() - r42.results.size()) + r0, r42.results.size());
        return;
    L64:
        if (this.searchResultBotWebViewSwitch == null) goto L66;
    L53:
        if (this.searchResultBotContextSwitch != null) goto L58;
        if (this.searchResultBotWebViewSwitch != null) goto L58;
        r62 = false;
        goto L59
    L40:
        if (r6.length() == 0) goto L46;
        this.searchResultBotContext.addAll(r42.results);
        if (r42.results.isEmpty() == false) goto L45;
        this.nextQueryOffset = _UrlKt.FRAGMENT_ENCODE_SET;
    L45:
        r23 = true;
        goto L47
    L17:
        if (r42.cache_time == 0) goto L19;
        r7.saveBotCache(r8, r42);
        goto L19
    L77:
        return;
    L9:
        MentionsAdapterDelegate r24 = this.delegate;
        if (r24 == null) goto L13;
        r24.onContextSearch(false);
        goto L13
    }

    private /* synthetic */ void lambda$searchForContextBotResults$8(String r1, boolean r2, TLRPC.User r3, String r4, MessagesStorage r5, String r6, TLObject r7, TLRPC.TL_error r8) {
        AndroidUtilities.runOnUIThread(new MentionsAdapter$$ExternalSyntheticLambda11(this, r1, r2, r7, r3, r4, r5, r6));
    }

    private /* synthetic */ void lambda$searchGlobalSenders$0(int r7, String r8, LongSparseArray r9, ArrayList r10) {
        if (r7 == this.senderSearchId) goto L5;
        return;
    L5:
        if (TextUtils.equals(r8, this.searchAdapterHelper.getLastFoundUsername()) == false) goto L38;
        ArrayList<TLObject> r72 = this.searchAdapterHelper.getGlobalSearch();
        int r82 = 0;
    L9:
        if (r82 >= r72.size()) goto L30;
        TLObject r0 = r72.get(r82);
        if ((r0 instanceof TLRPC.User) == false) goto L29;
        TLRPC.User r02 = (TLRPC.User) r0;
        TLObject r1 = (TLObject) r9.get(r02.id);
        if ((r1 instanceof TLRPC.User) == false) goto L15;
        TLRPC.User r2 = (TLRPC.User) r1;
    L16:
        if (r2 != null) goto L18;
    L28:
        addUserResult(r10, r9, r02);
        goto L29
    L18:
        if (r2.min == true) goto L22;
        if (r2.access_hash != 0) goto L28;
    L22:
        if (r02.min == true) goto L28;
        if (r02.access_hash == 0) goto L28;
        int r12 = r10.indexOf(r1);
        if (r12 < 0) goto L29;
        r10.set(r12, r02);
        r9.put(r02.id, r02);
        goto L29
    L15:
        r2 = null;
    L29:
        r82 = r82 + 1;
        goto L9
    L30:
        showUsersResult(r10, r9, true);
        return;
    }

    private /* synthetic */ void lambda$searchGlobalSenders$1(int r17, String r18, LongSparseArray r19, ArrayList r20) {
        this.senderSearchRunnable = null;
        if (r17 == this.senderSearchId) goto L5;
        return;
    L5:
        this.searchAdapterHelper.queryServerSearch(r18, true, false, true, true, false, 0, false, 0, r17, 0, new MentionsAdapter$$ExternalSyntheticLambda12(this, r17, r18, r19, r20));
    }

    private /* synthetic */ void lambda$searchServerStickers$2(String r5, TLObject r6) {
        boolean r0 = false;
        this.lastReqId = 0;
        if (r5.equals(this.lastSticker) == true) goto L5;
        return;
    L5:
        if ((r6 instanceof TLRPC.TL_messages_stickers) == false) goto L30;
        this.delayLocalResults = false;
        TLRPC.TL_messages_stickers r62 = (TLRPC.TL_messages_stickers) r6;
        ArrayList<StickerResult> r1 = this.stickers;
        if (r1 == null) goto L10;
        int r12 = r1.size();
    L11:
        addStickersToResult(r62.stickers, "sticker_search_".concat(r5));
        ArrayList<StickerResult> r52 = this.stickers;
        if (r52 == null) goto L14;
        int r53 = r52.size();
    L16:
        if (this.visibleByStickersSearch == true) goto L25;
        ArrayList<StickerResult> r63 = this.stickers;
        if (r63 == null) goto L25;
        if (r63.isEmpty() == true) goto L25;
        checkStickerFilesExistAndDownload();
        MentionsAdapterDelegate r64 = this.delegate;
        if (getItemCountInternal() <= 0) goto L24;
        r0 = true;
    L24:
        r64.needChangePanelVisibility(r0);
        this.visibleByStickersSearch = true;
    L25:
        if (r12 == r53) goto L29;
        notifyDataSetChanged();
        return;
    L29:
        return;
    L14:
        r53 = 0;
        goto L16
    L10:
        r12 = 0;
        goto L11
    }

    private /* synthetic */ void lambda$searchServerStickers$3(String r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new MentionsAdapter$$ExternalSyntheticLambda13(this, r1, r2));
    }

    private /* synthetic */ void lambda$searchUsernameOrHashtag$10(CharSequence r1, int r2, ArrayList r3, boolean r4, boolean r5) {
        searchUsernameOrHashtag(r1, r2, r3, r4, r5);
    }

    private /* synthetic */ void lambda$searchUsernameOrHashtag$11(ArrayList r2, LongSparseArray r3) {
        this.cancelDelayRunnable = null;
        showUsersResult(r2, r3, true);
    }

    private /* synthetic */ void lambda$searchUsernameOrHashtag$12(ArrayList r1, String r2) {
        this.searchResultSuggestions = r1;
        this.searchResultHashtags = null;
        this.stickers = null;
        this.searchResultUsernames = null;
        this.searchResultUsernamesMap = null;
        this.searchResultCommands = null;
        this.searchResultCommandsEphemeral = null;
        this.quickReplies = null;
        this.searchResultCommandsHelp = null;
        this.searchResultCommandsUsers = null;
        notifyDataSetChanged();
        MentionsAdapterDelegate r12 = this.delegate;
        ArrayList<MediaDataController.KeywordResult> r0 = this.searchResultSuggestions;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        r12.needChangePanelVisibility(r02);
        return;
    L5:
        if (r0.isEmpty() == true) goto L7;
        r02 = true;
        goto L8
    }

    private void onLocationUnavailable() {
        TLRPC.User r0 = this.foundContextBot;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.bot_inline_geo == false) goto L9;
        Location r02 = new Location("network");
        this.lastKnownLocation = r02;
        r02.setLatitude(-1000.0d);
        this.lastKnownLocation.setLongitude(-1000.0d);
        searchForContextBotResults(true, this.foundContextBot, this.searchingContextQuery, _UrlKt.FRAGMENT_ENCODE_SET);
        return;
    }

    private void processFoundUser(TLRPC.User r8) {
        this.contextUsernameReqid = 0;
        this.locationProvider.stop();
        if (r8 != null) goto L5;
    L29:
        this.foundContextBot = null;
        this.searchResultBotContextSwitch = null;
        this.inlineMediaEnabled = true;
    L31:
        if (this.foundContextBot != null) goto L34;
        this.noUserName = true;
        this.searchResultBotContextSwitch = null;
        return;
    L34:
        MentionsAdapterDelegate r82 = this.delegate;
        if (r82 == null) goto L37;
        r82.onContextSearch(true);
    L37:
        searchForContextBotResults(true, this.foundContextBot, this.searchingContextQuery, _UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L5:
        if (r8.bot == false) goto L29;
        if (r8.bot_inline_placeholder == null) goto L29;
        this.foundContextBot = r8;
        long r3 = r8.id;
        if (r3 == this.searchResultBotContextSwitchUserId) goto L11;
        this.searchResultBotContextSwitch = null;
        this.searchResultBotContextSwitchUserId = r3;
    L11:
        ChatActivity r83 = this.parentFragment;
        if (r83 == null) goto L20;
        TLRPC.Chat r84 = r83.getCurrentChat();
        if (r84 == null) goto L20;
        boolean r85 = ChatObject.canSendStickers(r84);
        this.inlineMediaEnabled = r85;
        if (r85 == true) goto L20;
        notifyDataSetChanged();
        this.delegate.needChangePanelVisibility(true);
        return;
    L20:
        if (this.foundContextBot.bot_inline_geo == false) goto L31;
        if (MessagesController.getNotificationsSettings(this.currentAccount).getBoolean("inlinegeo_" + this.foundContextBot.id, false) == true) goto L28;
        ChatActivity r86 = this.parentFragment;
        if (r86 == null) goto L28;
        if (r86.getParentActivity() == null) goto L28;
        TLRPC.User r87 = this.foundContextBot;
        AlertDialog.Builder r0 = new AlertDialog.Builder(this.parentFragment.getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.ShareYouLocationTitle));
        r0.setMessage(LocaleController.getString(R.string.ShareYouLocationInline));
        boolean[] r32 = new boolean[1];
        r0.setPositiveButton(LocaleController.getString(R.string.OK), new MentionsAdapter$$ExternalSyntheticLambda1(this, r32, r87));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), new MentionsAdapter$$ExternalSyntheticLambda2(this, r32));
        this.parentFragment.showDialog(r0.create(), new MentionsAdapter$$ExternalSyntheticLambda3(this, r32));
    L28:
        checkLocationPermissionsOrStart();
        goto L31
    }

    private void searchForContextBot(String r8, String r9) {
        TLRPC.User r0 = this.foundContextBot;
        if (r0 == null) goto L14;
        String r02 = r0.username;
        if (r02 == null) goto L14;
        if (r02.equals(r8) == false) goto L14;
        String r03 = this.searchingContextQuery;
        if (r03 == null) goto L14;
        if (r03.equals(r9) == false) goto L14;
        return;
    L14:
        if (this.foundContextBot != null) goto L16;
    L21:
        Runnable r04 = this.contextQueryRunnable;
        if (r04 == null) goto L25;
        AndroidUtilities.cancelRunOnUIThread(r04);
        this.contextQueryRunnable = null;
    L25:
        if (TextUtils.isEmpty(r8) == true) goto L31;
        String r05 = this.searchingContextUsername;
        if (r05 != null) goto L29;
    L43:
        if (r9 == null) goto L45;
        MentionsAdapterDelegate r06 = this.delegate;
        if (r06 != null) goto L54;
    L59:
        MessagesController r5 = MessagesController.getInstance(this.currentAccount);
        MessagesStorage r6 = MessagesStorage.getInstance(this.currentAccount);
        this.searchingContextQuery = r9;
        AnonymousClass4 r1 = new AnonymousClass4(this, r9, r8, r5, r6);
        this.contextQueryRunnable = r1;
        AndroidUtilities.runOnUIThread(r1, 400);
        return;
    L54:
        if (this.foundContextBot == null) goto L57;
        r06.onContextSearch(true);
        goto L59
    L57:
        if (r8.equals("gif") == false) goto L59;
        this.searchingContextUsername = "gif";
        this.delegate.onContextSearch(false);
        goto L59
    L45:
        if (this.contextQueryReqid == 0) goto L47;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.contextQueryReqid, true);
        this.contextQueryReqid = 0;
    L47:
        this.searchingContextQuery = null;
        MentionsAdapterDelegate r7 = this.delegate;
        if (r7 == null) goto L62;
        r7.onContextSearch(false);
        return;
    L62:
        return;
    L29:
        if (r05.equals(r8) == true) goto L43;
    L31:
        if (this.contextUsernameReqid == 0) goto L34;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.contextUsernameReqid, true);
        this.contextUsernameReqid = 0;
    L34:
        if (this.contextQueryReqid == 0) goto L36;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.contextQueryReqid, true);
        this.contextQueryReqid = 0;
    L36:
        this.foundContextBot = null;
        this.searchResultBotContextSwitch = null;
        this.inlineMediaEnabled = true;
        this.searchingContextUsername = null;
        this.searchingContextQuery = null;
        this.locationProvider.stop();
        this.noUserName = false;
        MentionsAdapterDelegate r07 = this.delegate;
        if (r07 == null) goto L39;
        r07.onContextSearch(false);
    L39:
        if (r8 != null) goto L41;
        return;
    L41:
        if (r8.length() != 0) goto L43;
        return;
    L16:
        if (this.inlineMediaEnabled == true) goto L20;
        if (r8 == null) goto L20;
        if (r9 == null) goto L20;
        return;
    L20:
        this.delegate.needChangePanelVisibility(false);
        goto L21
    }

    private void searchForContextBotResults(boolean r17, TLRPC.User r18, String r19, String r20) {
        if (this.contextQueryReqid == 0) goto L6;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.contextQueryReqid, true);
        this.contextQueryReqid = 0;
    L6:
        if (this.inlineMediaEnabled == true) goto L8;
    L46:
        MentionsAdapterDelegate r0 = this.delegate;
        if (r0 == null) goto L50;
        r0.onContextSearch(false);
        return;
    L50:
        return;
    L8:
        if (this.allowBots == false) goto L46;
        if (r19 == null) goto L44;
        if (r18 == null) goto L44;
        if (r18.bot_inline_geo == true) goto L17;
    L19:
        StringBuilder r02 = new StringBuilder();
        r02.append(this.dialog_id);
        r02.append("_");
        r02.append(r19);
        r02.append("_");
        r02.append(r20);
        r02.append("_");
        r02.append(this.dialog_id);
        r02.append("_");
        r02.append(r18.id);
        r02.append("_");
        if (r18.bot_inline_geo == true) goto L22;
    L24:
        Object r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L25:
        r02.append(r3);
        String r7 = r02.toString();
        MessagesStorage r6 = MessagesStorage.getInstance(this.currentAccount);
        MentionsAdapter$$ExternalSyntheticLambda8 r03 = new MentionsAdapter$$ExternalSyntheticLambda8(this, r19, r17, r18, r20, r6, r7);
        long r12 = r18.id;
        if (r12 == this.searchResultBotContextSwitchUserId) goto L28;
        this.searchResultBotContextSwitch = null;
        this.searchResultBotContextSwitchUserId = r12;
    L28:
        if (r17 == false) goto L31;
        r6.getBotCache(r7, r03);
        return;
    L31:
        TLRPC.TL_messages_getInlineBotResults r32 = new TLRPC.TL_messages_getInlineBotResults();
        r32.bot = MessagesController.getInstance(this.currentAccount).getInputUser(r18);
        r32.query = r19;
        r32.offset = r20;
        if (r18.bot_inline_geo == false) goto L39;
        Location r2 = this.lastKnownLocation;
        if (r2 == null) goto L39;
        if (r2.getLatitude() == (-1000.0d)) goto L39;
        r32.flags |= 1;
        TLRPC.TL_inputGeoPoint r22 = new TLRPC.TL_inputGeoPoint();
        r32.geo_point = r22;
        r22.lat = AndroidUtilities.fixLocationCoord(this.lastKnownLocation.getLatitude());
        r32.geo_point._long = AndroidUtilities.fixLocationCoord(this.lastKnownLocation.getLongitude());
    L39:
        if (DialogObject.isEncryptedDialog(this.dialog_id) == false) goto L41;
        r32.peer = new TLRPC.TL_inputPeerEmpty();
    L42:
        this.contextQueryReqid = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r32, r03, 2);
        return;
    L41:
        r32.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialog_id);
        goto L42
    L22:
        if (this.lastKnownLocation.getLatitude() == (-1000.0d)) goto L24;
        r3 = Double.valueOf(this.lastKnownLocation.getLatitude() + this.lastKnownLocation.getLongitude());
        goto L25
    L17:
        if (this.lastKnownLocation != null) goto L19;
        return;
    L44:
        this.searchingContextQuery = null;
    }

    private void searchGlobalSenders(String r14, ArrayList<TLObject> r15, LongSparseArray<TLObject> r16) {
        Runnable r0 = this.senderSearchRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.senderSearchRunnable = null;
    L5:
        int r12 = this.senderSearchId + 1;
        this.senderSearchId = r12;
        if (TextUtils.isEmpty(r14) == false) goto L9;
        this.searchAdapterHelper.queryServerSearch(null, true, false, true, true, false, 0, false, 0, r12);
        return;
    L9:
        MentionsAdapter$$ExternalSyntheticLambda0 r1 = new MentionsAdapter$$ExternalSyntheticLambda0(this, r12, r14, r16, r15);
        this.senderSearchRunnable = r1;
        AndroidUtilities.runOnUIThread(r1, 200);
    }

    private void searchServerStickers(String r4, String r5) {
        TLRPC.TL_messages_getStickers r0 = new TLRPC.TL_messages_getStickers();
        r0.emoticon = r5;
        r0.hash = 0;
        this.lastReqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new MentionsAdapter$$ExternalSyntheticLambda10(this, r4));
    }

    private void showUsersResult(ArrayList<TLObject> r3, LongSparseArray<TLObject> r4, boolean r5) {
        this.searchResultUsernames = r3;
        if (this.allowBots == true) goto L5;
    L6:
        if (r3 == null) goto L22;
        Iterator<TLObject> r32 = r3.iterator();
    L9:
        if (r32.hasNext() == false) goto L22;
        TLObject r0 = r32.next();
        if ((r0 instanceof TLRPC.Chat) == false) goto L16;
        if (this.allowChats == true) goto L16;
        r32.remove();
    L16:
        if ((r0 instanceof TLRPC.User) == false) goto L9;
        TLRPC.User r02 = (TLRPC.User) r0;
        if (r02.bot == true) goto L21;
        if (UserObject.isService(r02.id) == false) goto L9;
    L21:
        r32.remove();
    L22:
        this.searchResultUsernamesMap = r4;
        Runnable r33 = this.cancelDelayRunnable;
        if (r33 == null) goto L25;
        AndroidUtilities.cancelRunOnUIThread(r33);
        this.cancelDelayRunnable = null;
    L25:
        this.searchResultBotContext = null;
        this.stickers = null;
        if (r5 == false) goto L41;
        notifyDataSetChanged();
        this.delegate.needChangePanelVisibility(!this.searchResultUsernames.isEmpty());
        return;
    L41:
        return;
    L5:
        if (this.allowChats == true) goto L22;
        goto L6
    }

    private static ArrayList<TLRPC.TL_topPeer> sortAndDeduplicateTopPeers(ArrayList<TLRPC.TL_topPeer> r6) {
        r6.sort(new MentionsAdapter$$ExternalSyntheticLambda9());
        LinkedHashMap r0 = new LinkedHashMap();
        int r1 = r6.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        TLRPC.TL_topPeer r3 = r6.get(r2);
        r2 = r2 + 1;
        TLRPC.TL_topPeer r32 = r3;
        r0.putIfAbsent(Long.valueOf(DialogObject.getPeerDialogId(r32.peer)), r32);
        goto L3
    L6:
        return new ArrayList(r0.values());
    }

    public void addHashtagsFromMessage(CharSequence r1) {
        this.searchAdapterHelper.addHashtagsFromMessage(r1);
    }

    public void clear(boolean r2) {
        if (r2 == true) goto L4;
    L12:
        this.foundContextBot = null;
        this.hintHashtag = null;
        ArrayList<StickerResult> r0 = this.stickers;
        if (r0 == null) goto L15;
        r0.clear();
    L15:
        ArrayList<TLRPC.BotInlineResult> r02 = this.searchResultBotContext;
        if (r02 == null) goto L18;
        r02.clear();
    L18:
        this.searchResultBotContextSwitch = null;
        this.searchResultBotWebViewSwitch = null;
        ArrayList<TLObject> r22 = this.searchResultUsernames;
        if (r22 == null) goto L21;
        r22.clear();
    L21:
        ArrayList<String> r23 = this.searchResultHashtags;
        if (r23 == null) goto L24;
        r23.clear();
    L24:
        ArrayList<String> r24 = this.searchResultCommands;
        if (r24 == null) goto L27;
        r24.clear();
    L27:
        ArrayList<QuickRepliesController.QuickReply> r25 = this.quickReplies;
        if (r25 == null) goto L30;
        r25.clear();
    L30:
        ArrayList<MediaDataController.KeywordResult> r26 = this.searchResultSuggestions;
        if (r26 == null) goto L33;
        r26.clear();
    L33:
        notifyDataSetChanged();
        return;
    L4:
        if (this.channelReqId == 0) goto L6;
        return;
    L6:
        if (this.contextQueryReqid == 0) goto L8;
        return;
    L8:
        if (this.contextUsernameReqid == 0) goto L10;
        return;
    L10:
        if (this.lastReqId == 0) goto L12;
    }

    public void clearRecentHashtags() {
        this.searchAdapterHelper.clearRecentHashtags();
        this.searchResultHashtags.clear();
        notifyDataSetChanged();
        MentionsAdapterDelegate r1 = this.delegate;
        if (r1 == null) goto L6;
        r1.needChangePanelVisibility(false);
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        boolean r0 = false;
        if (r3 != NotificationCenter.fileLoaded) goto L5;
    L21:
        ArrayList<StickerResult> r32 = this.stickers;
        if (r32 != null) goto L24;
        return;
    L24:
        if (r32.isEmpty() == false) goto L26;
        return;
    L26:
        if (this.stickersToLoad.isEmpty() == false) goto L28;
        return;
    L28:
        if (this.visibleByStickersSearch == false) goto L43;
        String r33 = (String) r5[0];
        this.stickersToLoad.remove(r33);
        if (this.stickersToLoad.isEmpty() == false) goto L44;
        MentionsAdapterDelegate r34 = this.delegate;
        if (getItemCountInternal() <= 0) goto L34;
        r0 = true;
    L34:
        r34.needChangePanelVisibility(r0);
        return;
    L44:
        return;
    L43:
        return;
    L5:
        if (r3 == NotificationCenter.fileLoadFailed) goto L21;
        if (r3 != NotificationCenter.recentDocumentsDidLoad) goto L14;
        Runnable r35 = this.checkAgainRunnable;
        if (r35 == null) goto L36;
        AndroidUtilities.runOnUIThread(r35);
        this.checkAgainRunnable = null;
        return;
    L36:
        return;
    L14:
        if (r3 == NotificationCenter.stickersDidLoad) goto L16;
        return;
    L16:
        if (((Integer) r5[0]).intValue() != 0) goto L38;
        Runnable r36 = this.checkAgainRunnable;
        if (r36 == null) goto L39;
        AndroidUtilities.runOnUIThread(r36);
        this.checkAgainRunnable = null;
        return;
    L39:
        return;
    }

    public void doSomeStickersAction() {
        if (isStickers() == true) goto L5;
        return;
    L5:
        if (this.mentionsStickersActionTracker != null) goto L7;
        MentionsAdapter r2 = this;
        AnonymousClass9 r1 = new AnonymousClass9(r2, this.currentAccount, this.dialog_id, this.threadMessageId);
        r2.mentionsStickersActionTracker = r1;
        r1.checkVisibility();
    L8:
        r2.mentionsStickersActionTracker.doSomeAction();
        return;
    L7:
        r2 = this;
        goto L8
    }

    public String getBotCaption() {
        TLRPC.User r0 = this.foundContextBot;
        if (r0 != null) goto L5;
        String r1 = this.searchingContextUsername;
        if (r1 != null) goto L9;
        return null;
    L9:
        if (r1.equals("gif") == true) goto L11;
        return null;
    L11:
        return LocaleController.getString(R.string.SearchGifsTitle);
    L5:
        return r0.bot_inline_placeholder;
    }

    public TLRPC.TL_inlineBotSwitchPM getBotContextSwitch() {
        TLRPC.User r0 = this.foundContextBot;
        if (r0 == null) goto L9;
        if (r0.id == this.searchResultBotContextSwitchUserId) goto L9;
        return null;
    L9:
        return this.searchResultBotContextSwitch;
    }

    public TLRPC.TL_inlineBotWebView getBotWebViewSwitch() {
        return this.searchResultBotWebViewSwitch;
    }

    public long getContextBotId() {
        TLRPC.User r2 = this.foundContextBot;
        if (r2 != null) goto L5;
        return 0;
    L5:
        return r2.id;
    }

    public String getContextBotName() {
        TLRPC.User r0 = this.foundContextBot;
        if (r0 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return r0.username;
    }

    public TLRPC.User getContextBotUser() {
        return this.foundContextBot;
    }

    public TLRPC.User getFoundContextBot() {
        return this.foundContextBot;
    }

    public String getHashtagHint() {
        return this.hintHashtag;
    }

    public Object getItem(int r5) {
        TLRPC.User r1 = null;
        if (this.hintHashtag != null) goto L5;
    L8:
        ArrayList<StickerResult> r0 = this.stickers;
        if (r0 == null) goto L16;
        if (r5 >= 0) goto L12;
    L15:
        return null;
    L12:
        if (r5 >= r0.size()) goto L15;
        return this.stickers.get(r5).sticker;
    L16:
        ArrayList<TLRPC.BotInlineResult> r02 = this.searchResultBotContext;
        if (r02 == null) goto L34;
        TLRPC.TL_inlineBotWebView r2 = this.searchResultBotWebViewSwitch;
        if (r2 == null) goto L23;
        if (r5 != 0) goto L22;
        return r2;
    L22:
        r5 = r5 - 1;
    L27:
        if (r5 >= 0) goto L29;
    L33:
        return null;
    L29:
        if (r5 >= r02.size()) goto L33;
        return this.searchResultBotContext.get(r5);
    L23:
        TLRPC.TL_inlineBotSwitchPM r22 = this.searchResultBotContextSwitch;
        if (r22 == null) goto L27;
        if (r5 != 0) goto L22;
        return r22;
    L34:
        ArrayList<TLObject> r03 = this.searchResultUsernames;
        if (r03 == null) goto L43;
        if (r5 >= 0) goto L38;
    L42:
        return null;
    L38:
        if (r5 >= r03.size()) goto L42;
        return this.searchResultUsernames.get(r5);
    L43:
        ArrayList<String> r04 = this.searchResultHashtags;
        if (r04 == null) goto L52;
        if (r5 >= 0) goto L47;
    L51:
        return null;
    L47:
        if (r5 >= r04.size()) goto L51;
        return this.searchResultHashtags.get(r5);
    L52:
        ArrayList<MediaDataController.KeywordResult> r05 = this.searchResultSuggestions;
        if (r05 == null) goto L61;
        if (r5 >= 0) goto L56;
    L60:
        return null;
    L56:
        if (r5 >= r05.size()) goto L60;
        return this.searchResultSuggestions.get(r5);
    L61:
        ArrayList<QuickRepliesController.QuickReply> r06 = this.quickReplies;
        if (r06 == null) goto L64;
    L65:
        if (r06 == null) goto L74;
        if (r5 >= 0) goto L68;
    L71:
        ArrayList<QuickRepliesController.QuickReply> r07 = this.quickReplies;
        if (r07 == null) goto L74;
        r5 = r5 - r07.size();
        goto L74
    L68:
        if (r5 >= r06.size()) goto L71;
        return this.quickReplies.get(r5);
    L74:
        ArrayList<String> r08 = this.searchResultCommands;
        if (r08 == null) goto L106;
        if (r5 < 0) goto L106;
        if (r5 >= r08.size()) goto L106;
        ArrayList<TLRPC.User> r09 = this.searchResultCommandsUsers;
        if (r09 != null) goto L83;
    L94:
        String r010 = this.searchResultCommands.get(r5);
    L95:
        ArrayList<Boolean> r4 = this.searchResultCommandsEphemeral;
        if (r4 != null) goto L98;
    L105:
        return r010;
    L98:
        if (r4.get(r5).booleanValue() != true) goto L105;
        if (r1 == null) goto L102;
        long r12 = r1.id;
    L104:
        return new EphemeralCommand(r010, r12);
    L102:
        r12 = 0;
        goto L104
    L83:
        if (this.botsCount != 1) goto L87;
        if ((this.info instanceof TLRPC.TL_channelFull) == false) goto L94;
    L87:
        if (r09.get(r5) == null) goto L93;
        r1 = this.searchResultCommandsUsers.get(r5);
        String r011 = this.searchResultCommands.get(r5);
        if (r1 == null) goto L91;
        String r3 = UserObject.getPublicUsername(r1);
    L92:
        r010 = String.format("%s@%s", new Object[]{r011, r3});
        goto L95
    L91:
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L92
    L93:
        r010 = String.format("%s", new Object[]{this.searchResultCommands.get(r5)});
    L106:
        return null;
    L64:
        if (this.searchResultCommands == null) goto L106;
    L5:
        if (r5 >= 2) goto L7;
        return null;
    L7:
        r5 = r5 - 2;
        goto L8
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int r0 = getItemCountInternal();
        this.lastItemCount = r0;
        return r0;
    }

    public int getItemCountInternal() {
        int r1 = 1;
        if (this.foundContextBot != null) goto L5;
    L7:
        int r2 = 0;
        if (this.hintHashtag == null) goto L10;
        int r0 = 2;
    L11:
        ArrayList<StickerResult> r3 = this.stickers;
        if (r3 != null) goto L14;
        ArrayList<TLRPC.BotInlineResult> r32 = this.searchResultBotContext;
        if (r32 == null) goto L25;
        int r33 = r32.size();
        if (this.searchResultBotContextSwitch != null) goto L24;
        if (this.searchResultBotWebViewSwitch != null) goto L24;
        r1 = 0;
    L24:
        return r0 + (r33 + r1);
    L25:
        ArrayList<TLObject> r12 = this.searchResultUsernames;
        if (r12 != null) goto L28;
        ArrayList<String> r13 = this.searchResultHashtags;
        if (r13 == null) goto L34;
        return r0 + r13.size();
    L34:
        if (this.searchResultCommands == null) goto L36;
    L42:
        ArrayList<QuickRepliesController.QuickReply> r14 = this.quickReplies;
        if (r14 != null) goto L45;
        int r15 = 0;
    L46:
        ArrayList<String> r5 = this.searchResultCommands;
        if (r5 == null) goto L51;
        r2 = r5.size();
    L51:
        return r0 + (r15 + r2);
    L45:
        r15 = r14.size();
        goto L46
    L36:
        if (this.quickReplies != null) goto L42;
        ArrayList<MediaDataController.KeywordResult> r52 = this.searchResultSuggestions;
        if (r52 != null) goto L41;
        return r0;
    L41:
        return r0 + r52.size();
    L28:
        return r0 + r12.size();
    L14:
        return r0 + r3.size();
    L10:
        r0 = 0;
        goto L11
    L5:
        if (this.inlineMediaEnabled == true) goto L7;
        return 1;
    }

    public Object getItemParent(int r3) {
        if (this.hintHashtag != null) goto L5;
    L8:
        ArrayList<StickerResult> r0 = this.stickers;
        if (r0 == null) goto L15;
        if (r3 < 0) goto L15;
        if (r3 >= r0.size()) goto L15;
        return this.stickers.get(r3).parent;
    L15:
        return null;
    L5:
        if (r3 >= 2) goto L7;
        return null;
    L7:
        r3 = r3 - 2;
        goto L8
    }

    public int getItemPosition(int r2) {
        if (this.hintHashtag == null) goto L10;
        if (r2 >= 2) goto L8;
        return 0;
    L8:
        r2 = r2 - 2;
    L10:
        if (this.searchResultBotContext != null) goto L12;
        return r2;
    L12:
        if (this.searchResultBotContextSwitch != null) goto L16;
        if (this.searchResultBotWebViewSwitch != null) goto L16;
        return r2;
    L16:
        return r2 - 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r3) {
        if (this.hintHashtag == null) goto L9;
        if (r3 >= 2) goto L7;
        return 6;
    L7:
        r3 = r3 - 2;
    L9:
        if (this.stickers == null) goto L13;
        return 4;
    L13:
        if (this.foundContextBot == null) goto L19;
        if (this.inlineMediaEnabled == true) goto L19;
        return 3;
    L19:
        if (this.searchResultBotContext == null) goto L28;
        if (r3 == 0) goto L22;
        return 1;
    L22:
        if (this.searchResultBotContextSwitch == null) goto L24;
    L25:
        return 2;
    L24:
        if (this.searchResultBotWebViewSwitch != null) goto L25;
        return 1;
    L28:
        ArrayList<QuickRepliesController.QuickReply> r2 = this.quickReplies;
        if (r2 == null) goto L35;
        if (r3 >= 0) goto L32;
        return 0;
    L32:
        if (r3 >= r2.size()) goto L39;
        return 5;
    L39:
        return 0;
    L35:
        return 0;
    }

    public int getLastItemCount() {
        return this.lastItemCount;
    }

    public int getResultLength() {
        return this.resultLength;
    }

    public int getResultStartPosition() {
        return this.resultStartPosition;
    }

    public ArrayList<TLRPC.BotInlineResult> getSearchResultBotContext() {
        return this.searchResultBotContext;
    }

    public boolean isBannedInline() {
        if (this.foundContextBot != null) goto L5;
        return false;
    L5:
        if (this.inlineMediaEnabled == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isBotCommands() {
        if (this.searchResultCommands == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isBotContext() {
        if (this.searchResultBotContext == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r1) {
        if (this.foundContextBot == null) goto L7;
        if (this.inlineMediaEnabled == true) goto L7;
        return false;
    L7:
        if (this.stickers != null) goto L12;
        return true;
    L12:
        return false;
    }

    public boolean isGlobalHashtagHint(int r1) {
        if (this.hintHashtag == null) goto L7;
        if (r1 != 0) goto L9;
        return true;
    L9:
        return false;
    L7:
        return false;
    }

    public boolean isLocalHashtagHint(int r1) {
        if (this.hintHashtag != null) goto L5;
        return false;
    L5:
        if (r1 != 1) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean isLongClickEnabled() {
        if (this.searchResultHashtags == null) goto L5;
        return true;
    L5:
        if (this.searchResultCommands == null) goto L7;
        return true;
    L7:
        if (this.searchResultUsernames != null) goto L14;
        return false;
    L14:
        return true;
    }

    public boolean isMediaLayout() {
        if (this.contextMedia == false) goto L5;
        return true;
    L5:
        if (this.stickers != null) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isStickers() {
        if (this.stickers == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void notifyDataSetChanged() {
        int r0 = this.lastItemCount;
        int r2 = 0;
        if (r0 != (-1)) goto L5;
    L30:
        MentionsAdapterDelegate r02 = this.delegate;
        if (r02 == null) goto L33;
        r02.onItemCountUpdate(0, getItemCount());
    L33:
        super.notifyDataSetChanged();
        this.lastData = new Object[getItemCount()];
    L34:
        Object[] r03 = this.lastData;
        if (r2 >= r03.length) goto L37;
        r03[r2] = getItem(r2);
        r2 = r2 + 1;
        goto L34
    L37:
        return;
    L5:
        if (this.lastData == null) goto L30;
        int r1 = getItemCount();
        if (r0 == r1) goto L10;
        boolean r4 = true;
    L11:
        int r5 = Math.min(r0, r1);
        Object[] r6 = new Object[r1];
        int r7 = 0;
    L12:
        if (r7 >= r1) goto L14;
        r6[r7] = getItem(r7);
        r7 = r7 + 1;
    L14:
        if (r2 >= r5) goto L23;
        if (r2 < 0) goto L21;
        Object[] r72 = this.lastData;
        if (r2 >= r72.length) goto L21;
        if (r2 >= r1) goto L21;
        if (itemsEqual(r72[r2], r6[r2]) == false) goto L21;
    L22:
        r2 = r2 + 1;
    L21:
        notifyItemChanged(r2);
        r4 = true;
        goto L22
    L23:
        notifyItemRangeRemoved(r5, r0 - r5);
        notifyItemRangeInserted(r5, r1 - r5);
        if (r4 == false) goto L28;
        MentionsAdapterDelegate r22 = this.delegate;
        if (r22 == null) goto L28;
        r22.onItemCountUpdate(r0, r1);
    L28:
        this.lastData = r6;
        return;
    L10:
        r4 = false;
        goto L11
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r12, int r13) {
        if (this.hintHashtag == null) goto L5;
        r13 = r13 - 2;
    L5:
        int r0 = r12.getItemViewType();
        if (r0 != 4) goto L14;
        StickerCell r122 = (StickerCell) r12.itemView;
        if (r13 >= 0) goto L10;
        return;
    L10:
        if (r13 >= this.stickers.size()) goto L137;
        StickerResult r11 = this.stickers.get(r13);
        r122.setSticker(r11.sticker, r11.parent);
        r122.setClearsInputField(true);
        return;
    L137:
        return;
    L14:
        if (r0 != 3) goto L32;
        TextView r123 = (TextView) r12.itemView;
        TLRPC.Chat r112 = this.parentFragment.getCurrentChat();
        if (r112 != null) goto L18;
        return;
    L18:
        if (ChatObject.hasAdminRights(r112) == true) goto L26;
        TLRPC.TL_chatBannedRights r132 = r112.default_banned_rights;
        if (r132 == null) goto L26;
        if (r132.send_inline == false) goto L26;
        r123.setText(LocaleController.getString(R.string.GlobalAttachInlineRestricted));
        return;
    L26:
        if (AndroidUtilities.isBannedForever(r112.banned_rights) == false) goto L29;
        r123.setText(LocaleController.getString(R.string.AttachInlineRestrictedForever));
        return;
    L29:
        r123.setText(LocaleController.formatString("AttachInlineRestricted", R.string.AttachInlineRestricted, new Object[]{LocaleController.formatDateForBan(r112.banned_rights.until_date)}));
        return;
    L32:
        if (r0 != 5) goto L41;
        QuickRepliesActivity.QuickReplyView r124 = (QuickRepliesActivity.QuickReplyView) r12.itemView;
        ArrayList<QuickRepliesController.QuickReply> r02 = this.quickReplies;
        if (r02 == null) goto L139;
        if (r13 >= 0) goto L37;
        return;
    L37:
        if (r13 >= r02.size()) goto L141;
        r124.set(this.quickReplies.get(r13), this.quickRepliesQuery, false);
        return;
    L141:
        return;
    L139:
        return;
    L41:
        if (this.searchResultBotContext == null) goto L74;
        if (this.searchResultBotContextSwitch == null) goto L45;
    L48:
        boolean r03 = true;
    L50:
        if (r12.getItemViewType() != 2) goto L58;
        if (r03 == false) goto L142;
        BotSwitchCell r125 = (BotSwitchCell) r12.itemView;
        TLRPC.TL_inlineBotSwitchPM r133 = this.searchResultBotContextSwitch;
        if (r133 == null) goto L55;
        String r113 = r133.text;
    L56:
        r125.setText(r113);
        return;
    L55:
        r113 = this.searchResultBotWebViewSwitch.text;
        goto L56
    L142:
        return;
    L58:
        if (r03 == false) goto L60;
        r13 = r13 - 1;
    L60:
        if (r13 >= 0) goto L62;
        return;
    L62:
        if (r13 >= this.searchResultBotContext.size()) goto L144;
        ContextLinkCell r4 = (ContextLinkCell) r12.itemView;
        TLRPC.BotInlineResult r5 = this.searchResultBotContext.get(r13);
        TLRPC.User r6 = this.foundContextBot;
        boolean r7 = this.contextMedia;
        if (r13 == (this.searchResultBotContext.size() - 1)) goto L66;
        boolean r8 = true;
    L67:
        if (r03 == false) goto L70;
        if (r13 != 0) goto L70;
        boolean r9 = true;
    L71:
        r4.setLink(r5, r6, r7, r8, r9, "gif".equals(this.searchingContextUsername));
        return;
    L70:
        r9 = false;
        goto L71
    L66:
        r8 = false;
        goto L67
    L144:
        return;
    L45:
        if (this.searchResultBotWebViewSwitch != null) goto L48;
        r03 = false;
        goto L50
    L74:
        if (r0 != 6) goto L87;
        HashtagHint r126 = (HashtagHint) r12.itemView;
        int r134 = r13 + 2;
        if (r134 != 0) goto L78;
        this.topHint = r126;
    L79:
        TLRPC.Chat r04 = this.chat;
        if (r04 != null) goto L84;
        ChatActivity r1 = this.parentFragment;
        if (r1 == null) goto L84;
        r04 = r1.getCurrentChat();
    L84:
        r126.set(r134, this.hintHashtag, r04);
        return;
    L78:
        this.bottomHint = r126;
        goto L79
    L87:
        if (r0 == 7) goto L145;
        MentionCell r127 = (MentionCell) r12.itemView;
        ArrayList<TLObject> r05 = this.searchResultUsernames;
        if (r05 == null) goto L97;
        TLObject r114 = r05.get(r13);
        if ((r114 instanceof TLRPC.User) == false) goto L95;
        r127.setUser((TLRPC.User) r114);
    L135:
        r127.setDivider(false);
        return;
    L95:
        if ((r114 instanceof TLRPC.Chat) == false) goto L135;
        r127.setChat((TLRPC.Chat) r114);
        goto L135
    L97:
        ArrayList<String> r06 = this.searchResultHashtags;
        if (r06 == null) goto L103;
        if (r13 < 0) goto L103;
        if (r13 >= r06.size()) goto L103;
        r127.setText(this.searchResultHashtags.get(r13));
    L103:
        ArrayList<MediaDataController.KeywordResult> r07 = this.searchResultSuggestions;
        if (r07 == null) goto L109;
        if (r13 < 0) goto L109;
        if (r13 >= r07.size()) goto L109;
        r127.setEmojiSuggestion(this.searchResultSuggestions.get(r13));
    L109:
        ArrayList<String> r08 = this.searchResultCommands;
        if (r08 == null) goto L135;
        if (r13 < 0) goto L135;
        if (r13 >= r08.size()) goto L135;
        ArrayList<String> r09 = this.searchResultCommandsHelp;
        Boolean r14 = null;
        if (r09 == null) goto L120;
        if (r13 < 0) goto L120;
        if (r13 >= r09.size()) goto L120;
        String r010 = this.searchResultCommandsHelp.get(r13);
    L121:
        ArrayList<TLRPC.User> r2 = this.searchResultCommandsUsers;
        if (r2 == null) goto L127;
        if (r13 < 0) goto L127;
        if (r13 >= r2.size()) goto L127;
        TLRPC.User r22 = this.searchResultCommandsUsers.get(r13);
    L128:
        ArrayList<Boolean> r42 = this.searchResultCommandsEphemeral;
        if (r42 == null) goto L134;
        if (r13 < 0) goto L134;
        if (r13 >= r42.size()) goto L134;
        r14 = this.searchResultCommandsEphemeral.get(r13);
    L134:
        r127.setBotCommand(this.searchResultCommands.get(r13), r010, r22, r14.booleanValue());
    L127:
        r22 = null;
    L120:
        r010 = null;
        goto L121
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
        if (r8 != 0) goto L4;
        MentionCell r72 = new MentionCell(this.mContext, this.resourcesProvider);
        r72.setIsDarkTheme(this.isDarkTheme);
        View r73 = r72;
    L29:
        return new RecyclerListView.Holder(r73);
    L4:
        if (r8 != 1) goto L6;
        ContextLinkCell r74 = new ContextLinkCell(this.mContext);
        r74.setDelegate(new MentionsAdapter$$ExternalSyntheticLambda7(this));
        r73 = r74;
        goto L29
    L6:
        if (r8 != 2) goto L8;
        r73 = new BotSwitchCell(this.mContext);
        goto L29
    L8:
        if (r8 != 3) goto L10;
        TextView r82 = new TextView(this.mContext);
        r82.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        r82.setTextSize(1, 14.0f);
        r82.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText2));
        View r83 = r82;
    L21:
        r73 = r83;
        goto L29
    L10:
        if (r8 != 5) goto L12;
        r73 = new QuickRepliesActivity.QuickReplyView(this.mContext, false, this.resourcesProvider);
        goto L29
    L12:
        if (r8 != 6) goto L14;
        r73 = new HashtagHint(this.mContext, this.stories, this.resourcesProvider);
        goto L29
    L14:
        if (r8 == 7) goto L16;
        r73 = new StickerCell(this.mContext, this.resourcesProvider);
        goto L29
    L16:
        View r84 = new AnonymousClass8(this, this.mContext);
        if (this.stories == false) goto L19;
        int r3 = Theme.multAlpha(-1, 0.15f);
    L20:
        CombinedDrawable r0 = new CombinedDrawable(new ColorDrawable(r3), Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.getColor(Theme.key_windowBackgroundGrayShadow, this.resourcesProvider)), 0, 0);
        r0.setFullsize(true);
        r84.setBackground(r0);
        r83 = r84;
        goto L21
    L19:
        r3 = Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider);
        goto L20
    }

    public void onDestroy() {
        searchGlobalSenders(null, null, null);
        SendMessagesHelper.LocationProvider r1 = this.locationProvider;
        if (r1 == null) goto L5;
        r1.stop();
    L5:
        Runnable r12 = this.contextQueryRunnable;
        if (r12 == null) goto L9;
        AndroidUtilities.cancelRunOnUIThread(r12);
        this.contextQueryRunnable = null;
    L9:
        if (this.contextUsernameReqid == 0) goto L12;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.contextUsernameReqid, true);
        this.contextUsernameReqid = 0;
    L12:
        if (this.contextQueryReqid == 0) goto L14;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.contextQueryReqid, true);
        this.contextQueryReqid = 0;
    L14:
        this.foundContextBot = null;
        this.searchResultBotContextSwitch = null;
        this.inlineMediaEnabled = true;
        this.searchingContextUsername = null;
        this.searchingContextQuery = null;
        this.noUserName = false;
        this.observersGroup.removeAllObservers();
    }

    public void onRequestPermissionsResultFragment(int r1, String[] r2, int[] r3) {
        if (r1 != 2) goto L16;
        TLRPC.User r12 = this.foundContextBot;
        if (r12 != null) goto L7;
        return;
    L7:
        if (r12.bot_inline_geo == true) goto L9;
        return;
    L9:
        if (r3.length > 0) goto L11;
    L14:
        onLocationUnavailable();
        return;
    L11:
        if (r3[0] != 0) goto L14;
        this.locationProvider.start();
        return;
    }

    public void searchForContextBotForNextOffset() {
        if (this.contextQueryReqid != 0) goto L15;
        String r0 = this.nextQueryOffset;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.length() == 0) goto L17;
        TLRPC.User r02 = this.foundContextBot;
        if (r02 == null) goto L18;
        String r1 = this.searchingContextQuery;
        if (r1 == null) goto L19;
        searchForContextBotResults(true, r02, r1, this.nextQueryOffset);
        return;
    L19:
        return;
    L18:
        return;
    L17:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v20, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v48 */
    /* JADX WARN: Type inference failed for: r14v70 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v24, types: [androidx.collection.LongSparseArray<org.telegram.tgnet.TLObject>, java.util.ArrayList<java.lang.String>, java.util.ArrayList<org.telegram.messenger.MediaDataController$KeywordResult>, java.util.ArrayList<org.telegram.tgnet.TLObject>, java.util.ArrayList<org.telegram.tgnet.TLRPC$BotInlineResult>, java.util.ArrayList<org.telegram.ui.Adapters.MentionsAdapter$StickerResult>] */
    /* JADX WARN: Type inference failed for: r6v43 */
    public void searchUsernameOrHashtag(CharSequence r26, int r27, ArrayList<MessageObject> r28, boolean r29, boolean r30) {
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r26 != null) goto L5;
        String r7 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        TLRPC.Chat r3 = this.chat;
        ChatActivity r4 = this.parentFragment;
        if (r4 == null) goto L9;
        r3 = r4.getCurrentChat();
        this.parentFragment.getCurrentUser();
    L9:
        TLRPC.Chat r8 = r3;
        Runnable r32 = this.cancelDelayRunnable;
        if (r32 == null) goto L13;
        AndroidUtilities.cancelRunOnUIThread(r32);
        this.cancelDelayRunnable = null;
    L13:
        if (this.channelReqId == 0) goto L15;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.channelReqId, true);
        this.channelReqId = 0;
    L15:
        Runnable r33 = this.searchGlobalRunnable;
        if (r33 == null) goto L18;
        AndroidUtilities.cancelRunOnUIThread(r33);
        this.searchGlobalRunnable = null;
    L18:
        Runnable r34 = this.checkAgainRunnable;
        if (r34 == null) goto L22;
        AndroidUtilities.cancelRunOnUIThread(r34);
        this.checkAgainRunnable = null;
    L22:
        if (TextUtils.isEmpty(r7) == true) goto L606;
        if (r7.length() > MessagesController.getInstance(this.currentAccount).getMaxMessageLength()) goto L606;
        if (r7.length() <= 0) goto L29;
        int r12 = r27 - 1;
    L30:
        this.lastText = null;
        this.lastUsernameOnly = r29;
        this.lastForSearch = r30;
        StringBuilder r13 = new StringBuilder();
        if (r29 == false) goto L33;
    L37:
        boolean r35 = false;
    L38:
        if (r35 == false) goto L59;
        int r42 = r7.length();
        boolean r16 = true;
        int r15 = 0;
        CharSequence r10 = r7;
    L40:
        if (r15 >= r42) goto L58;
        char r9 = r10.charAt(r15);
        int r14 = r42 - 1;
        if (r15 >= r14) goto L44;
        char r11 = r10.charAt(r15 + 1);
    L45:
        if (r15 >= r14) goto L55;
        if (r9 != 55356) goto L55;
        if (r11 < 57339) goto L55;
        if (r11 > 57343) goto L55;
        r10 = TextUtils.concat(new CharSequence[]{r10.subSequence(0, r15), r10.subSequence(r15 + 2, r10.length())});
        r42 = r42 - 2;
    L53:
        r15 = r15 - 1;
    L57:
        r15 = r15 + 1;
    L55:
        if (r9 != 65039) goto L57;
        r10 = TextUtils.concat(new CharSequence[]{r10.subSequence(0, r15), r10.subSequence(r15 + 1, r10.length())});
        r42 = r42 - 1;
        goto L53
    L44:
        r11 = 0;
        goto L45
    L58:
        this.lastSticker = r10.toString().trim();
        String r92 = r7;
    L60:
        if (r35 == true) goto L62;
    L66:
        boolean r36 = false;
    L67:
        if (r36 == false) goto L78;
        if ((r26 instanceof Spanned) == false) goto L78;
        AnimatedEmojiSpan[] r37 = (AnimatedEmojiSpan[]) ((Spanned) r26).getSpans(0, r26.length(), AnimatedEmojiSpan.class);
        if (r37 != null) goto L73;
    L76:
        r36 = r16;
        goto L78
    L73:
        if (r37.length == 0) goto L76;
        r36 = false;
    L78:
        if (this.allowStickers == false) goto L84;
        if (r36 == false) goto L84;
        if (r8 != null) goto L82;
    L85:
        this.stickersToLoad.clear();
        int r02 = SharedConfig.suggestStickers;
        if (r02 == 2) goto L142;
        if (r36 == false) goto L142;
        this.stickers = null;
        this.stickersMap = null;
        if (this.lastReqId == 0) goto L92;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.lastReqId, r16);
        boolean r03 = false;
        this.lastReqId = 0;
    L93:
        boolean r38 = MessagesController.getInstance(this.currentAccount).suggestStickersApiOnly;
        this.delayLocalResults = r03;
        if (r38 == true) goto L120;
        boolean r18 = r03;
        ArrayList<MessageObject> r43 = r28;
        boolean r21 = r38;
        int r39 = r27;
        this.checkAgainRunnable = new MentionsAdapter$$ExternalSyntheticLambda4(this, r26, r39, r43, r29, r30);
        MediaDataController.getInstance(this.currentAccount).loadRecents(r18 ? 1 : 0, r18, true, r18);
        MediaDataController.getInstance(this.currentAccount).loadRecents(2, r18, true, r18);
        ArrayList<TLRPC.Document> r04 = MediaDataController.getInstance(this.currentAccount).getRecentStickersNoCopy(r18 ? 1 : 0);
        ArrayList<TLRPC.Document> r2 = MediaDataController.getInstance(this.currentAccount).getRecentStickersNoCopy(2);
        int r5 = Math.min(20, r04.size());
        int r6 = 0;
        int r142 = 0;
    L96:
        if (r6 >= r5) goto L103;
        TLRPC.Document r152 = r04.get(r6);
        if (isValidSticker(r152, this.lastSticker) == false) goto L102;
        addStickerToResult(r152, "recent");
        r142 = r142 + 1;
        if (r142 >= 5) goto L103;
    L102:
        r6 = r6 + 1;
    L103:
        int r52 = r2.size();
        int r62 = 0;
    L104:
        if (r62 >= r52) goto L109;
        TLRPC.Document r102 = r2.get(r62);
        if (isValidSticker(r102, this.lastSticker) == false) goto L108;
        addStickerToResult(r102, "fav");
    L108:
        r62 = r62 + 1;
        goto L104
    L109:
        MediaDataController.getInstance(this.currentAccount).checkStickers(0);
        HashMap<String, ArrayList<TLRPC.Document>> r53 = MediaDataController.getInstance(this.currentAccount).getAllStickers();
        if (r53 == null) goto L112;
        ArrayList<TLRPC.Document> r54 = r53.get(this.lastSticker);
    L113:
        if (r54 != null) goto L115;
    L117:
        ArrayList<StickerResult> r55 = this.stickers;
        if (r55 == null) goto L122;
        Collections.sort(r55, new AnonymousClass5(this, r2, r04));
    L122:
        if (SharedConfig.suggestStickers == 0) goto L124;
        if (r21 == true) goto L124;
    L125:
        ArrayList<StickerResult> r05 = this.stickers;
        if (r05 == null) goto L138;
        if (r05.isEmpty() == true) goto L138;
        if (SharedConfig.suggestStickers == 0) goto L132;
    L134:
        checkStickerFilesExistAndDownload();
        this.delegate.needChangePanelVisibility(this.stickersToLoad.isEmpty());
        this.visibleByStickersSearch = true;
    L135:
        notifyDataSetChanged();
    L140:
        char r06 = 4;
        String r63 = null;
    L206:
        if (this.foundContextBot != null) goto L693;
        MessagesController r22 = MessagesController.getInstance(this.currentAccount);
        String r56 = this.hintHashtag;
        this.hintHashtag = r63;
        this.hintHashtagDivider = false;
        if (r29 == false) goto L213;
        r13.append(r7.substring(1));
        this.resultStartPosition = 0;
        this.resultLength = r13.length();
        r06 = 0;
    L211:
        r12 = -1;
    L212:
        ?? r143 = 0;
    L278:
        if (r56 != null) goto L284;
        if (this.hintHashtag == null) goto L284;
        notifyItemRangeInserted(r143, 2);
    L294:
        if (r06 != 65535) goto L298;
        this.contextMedia = r143;
        this.searchResultBotContext = null;
        this.delegate.needChangePanelVisibility(r143);
        return;
    L298:
        if (r06 != 0) goto L512;
        this.contextMedia = r143;
        this.searchResultBotContext = null;
        ArrayList r07 = new ArrayList();
        if (r43 == null) goto L310;
        int r310 = 0;
    L303:
        if (r310 >= Math.min(100, r43.size())) goto L310;
        long r93 = r43.get(r310).getFromChatId();
        if (r93 <= 0) goto L309;
        if (r07.contains(Long.valueOf(r93)) == true) goto L309;
        r07.add(Long.valueOf(r93));
    L309:
        r310 = r310 + 1;
    L310:
        String r311 = r13.toString().toLowerCase();
        if (r311.indexOf(32) < 0) goto L314;
        boolean r44 = true;
    L313:
        long r94 = 0;
        ArrayList r64 = new ArrayList();
        LongSparseArray r72 = new LongSparseArray();
        LongSparseArray r57 = new LongSparseArray();
        ArrayList r132 = new ArrayList();
        r132.addAll(MediaDataController.getInstance(this.currentAccount).inlineBots);
        if (r8 != null) goto L318;
    L321:
        r132.addAll(MediaDataController.getInstance(this.currentAccount).guestBots);
    L322:
        ArrayList<TLRPC.TL_topPeer> r133 = sortAndDeduplicateTopPeers(r132);
        if (r29 == false) goto L325;
    L350:
        long r262 = r94;
    L351:
        ChatActivity r95 = this.parentFragment;
        if (r95 == null) goto L354;
        r8 = r95.getCurrentChat();
        long r112 = this.parentFragment.getThreadId();
    L358:
        TLRPC.User r96 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        if (r8 == null) goto L369;
        TLRPC.ChatFull r134 = this.info;
        if (r134 == null) goto L369;
        if (r134.participants == null) goto L369;
        if (ChatObject.isChannel(r8) == true) goto L367;
    L370:
        int r135 = -2;
        int r144 = -2;
    L372:
        if (r144 >= this.info.participants.participants.size()) goto L369;
        if (r144 != r135) goto L378;
        if (r96 == null) goto L376;
        if (r29 == false) goto L376;
        String r153 = r96.first_name;
        String r103 = r96.last_name;
        String r17 = UserObject.getPublicUsername(r96);
        int r136 = r144;
        long r20 = r112;
        long r113 = r96.id;
        String r145 = r103;
        TLRPC.Chat r104 = r96;
    L405:
        if (TextUtils.isEmpty(r17) == true) goto L409;
        boolean r222 = r44;
        if (r17.toLowerCase().startsWith(r311) == false) goto L411;
    L422:
        if ((r104 instanceof TLRPC.User) == false) goto L427;
        if (addUserResult(r64, r72, (TLRPC.User) r104) == false) goto L431;
        r57.put(r113, r104);
    L431:
        r144 = r136 + 1;
        r112 = r20;
        r44 = r222;
        r135 = -2;
        goto L372
    L427:
        if ((r104 instanceof TLRPC.Chat) == false) goto L431;
        if (addChatResult(r64, r72, r104) == false) goto L431;
        r57.put(r113, r104);
    L411:
        if (TextUtils.isEmpty(r153) == true) goto L415;
        if (r153.toLowerCase().startsWith(r311) == true) goto L422;
    L415:
        if (TextUtils.isEmpty(r145) == false) goto L417;
    L418:
        if (r222 == false) goto L431;
        if (ContactsController.formatName(r153, r145).toLowerCase().startsWith(r311) == false) goto L431;
    L417:
        if (r145.toLowerCase().startsWith(r311) == true) goto L422;
    L409:
        r222 = r44;
    L376:
        r222 = r44;
        r20 = r112;
        r136 = r144;
        goto L431
    L378:
        r136 = r144;
        if (r136 != (-1)) goto L386;
        if (r30 == true) goto L383;
    L381:
        r222 = r44;
        r20 = r112;
        goto L431
    L383:
        if (r311.length() != 0) goto L385;
        addChatResult(r64, r72, r8);
        goto L381
    L385:
        r153 = r8.title;
        r17 = ChatObject.getPublicUsername(r8);
        r20 = r112;
        r113 = -r8.id;
        r145 = null;
        r104 = r8;
        goto L405
    L386:
        r20 = r112;
        TLRPC.ChatParticipant r105 = this.info.participants.participants.get(r136);
        if (r96 != null) goto L389;
    L391:
        TLRPC.User r106 = r22.getUser(Long.valueOf(r105.user_id));
        if (r106 != 0) goto L394;
    L390:
        r222 = r44;
        goto L431
    L394:
        if (UserObject.isUserSelf(r106) == true) goto L390;
        if (r72.indexOfKey(r106.id) >= 0) goto L390;
        if (r311.length() == 0) goto L401;
    L403:
        r153 = r106.first_name;
        String r114 = r106.last_name;
        r17 = UserObject.getPublicUsername(r106);
        r145 = r114;
        r113 = r106.id;
        r104 = r106;
        goto L405
    L401:
        if (r106.deleted == true) goto L403;
        addUserResult(r64, r72, r106);
        goto L390
    L389:
        if (r105.user_id != r96.id) goto L391;
    L367:
        if (r8.megagroup == true) goto L370;
    L369:
        boolean r223 = r44;
        long r202 = r112;
        if (this.searchInDialogs == false) goto L493;
        ArrayList<TLRPC.Dialog> r45 = MessagesController.getInstance(this.currentAccount).getAllDialogs();
        int r115 = 0;
    L436:
        if (r115 >= r45.size()) goto L493;
        if (r45.get(r115).id <= r262) goto L470;
        TLRPC.User r97 = r22.getUser(Long.valueOf(r45.get(r115).id));
        if (r97 == null) goto L492;
        if (UserObject.isUserSelf(r97) == true) goto L492;
        if (r72.indexOfKey(r97.id) >= 0) goto L492;
        if (r311.length() == 0) goto L449;
    L451:
        String r107 = r97.first_name;
        String r122 = r97.last_name;
        String r137 = UserObject.getPublicUsername(r97);
        long r146 = r97.id;
        if (TextUtils.isEmpty(r137) == true) goto L456;
        if (r137.toLowerCase().startsWith(r311) == false) goto L456;
    L467:
        if (addUserResult(r64, r72, r97) == false) goto L492;
        r57.put(r146, r97);
    L456:
        if (TextUtils.isEmpty(r107) == true) goto L460;
        if (r107.toLowerCase().startsWith(r311) == true) goto L467;
    L460:
        if (TextUtils.isEmpty(r122) == false) goto L462;
    L463:
        if (r223 == false) goto L492;
        if (ContactsController.formatName(r107, r122).toLowerCase().startsWith(r311) == false) goto L492;
    L462:
        if (r122.toLowerCase().startsWith(r311) == true) goto L467;
    L449:
        if (r97.deleted == true) goto L451;
        addUserResult(r64, r72, r97);
    L492:
        r115 = r115 + 1;
        goto L436
    L470:
        if (TextUtils.isEmpty(r311) == true) goto L492;
        TLRPC.Chat r98 = r22.getChat(Long.valueOf(-r45.get(r115).id));
        if (r98 == null) goto L492;
        if (r98.username == null) goto L492;
        if (r72.indexOfKey(-r98.id) >= 0) goto L492;
        if (r311.length() != 0) goto L481;
        addChatResult(r64, r72, r98);
        goto L492
    L481:
        String r108 = r98.title;
        String r123 = r98.username;
        if (TextUtils.isEmpty(r123) == true) goto L486;
        if (r123.toLowerCase().startsWith(r311) == false) goto L486;
    L490:
        if (addChatResult(r64, r72, r98) == false) goto L492;
        r57.put(-r98.id, r98);
    L486:
        if (TextUtils.isEmpty(r108) == true) goto L492;
        if (r108.toLowerCase().startsWith(r311) == false) goto L492;
    L493:
        Collections.sort(r64, new AnonymousClass6(this, r57, r07));
        this.searchResultHashtags = null;
        this.stickers = null;
        this.quickReplies = null;
        this.searchResultCommands = null;
        this.searchResultCommandsEphemeral = null;
        this.searchResultCommandsHelp = null;
        this.searchResultCommandsUsers = null;
        this.searchResultSuggestions = null;
        if (r8 == null) goto L498;
        if (r8.megagroup == false) goto L498;
    L500:
        if (r311.length() > 0) goto L502;
    L506:
        showUsersResult(r64, r72, true);
    L507:
        if (r29 == false) goto L690;
        if (r30 == false) goto L691;
        searchGlobalSenders(r311, r64, r72);
        return;
    L691:
        return;
    L690:
        return;
    L502:
        if (r64.size() >= 5) goto L504;
        MentionsAdapter$$ExternalSyntheticLambda5 r08 = new MentionsAdapter$$ExternalSyntheticLambda5(this, r64, r72);
        this.cancelDelayRunnable = r08;
        AndroidUtilities.runOnUIThread(r08, 1000);
    L505:
        AnonymousClass7 r09 = new AnonymousClass7(this, r8, r311, r202, r64, r72, r22);
        this.searchGlobalRunnable = r09;
        AndroidUtilities.runOnUIThread(r09, 200);
        goto L507
    L504:
        showUsersResult(r64, r72, true);
    L498:
        if (this.searchInDialogs == false) goto L506;
    L354:
        TLRPC.ChatFull r99 = this.info;
        if (r99 == null) goto L357;
        r8 = r22.getChat(Long.valueOf(r99.id));
    L357:
        r112 = r262;
        goto L358
    L325:
        if (this.needBotContext == false) goto L350;
        if (r12 != 0) goto L350;
        if (r133.isEmpty() == true) goto L350;
        int r124 = 0;
        int r147 = 0;
    L331:
        if (r124 >= r133.size()) goto L350;
        r262 = r94;
        TLRPC.User r910 = r22.getUser(Long.valueOf(r133.get(r124).peer.user_id));
        if (r910 != null) goto L335;
        int r154 = r124;
    L349:
        r124 = r154 + 1;
        r94 = r262;
        goto L331
    L335:
        String r109 = UserObject.getPublicUsername(r910);
        if (TextUtils.isEmpty(r109) == false) goto L338;
    L342:
        r154 = r124;
    L347:
        if (r147 != 5) goto L349;
    L338:
        if (r311.length() == 0) goto L344;
        if (r109.toLowerCase().startsWith(r311) == false) goto L342;
    L344:
        if (addUserResult(r64, r72, r910) == false) goto L342;
        r154 = r124;
        r57.put(r910.id, r910);
        r147 = r147 + 1;
        goto L347
    L318:
        if (ChatObject.isMonoForum(r8) == true) goto L322;
        if (ChatObject.isChannelAndNotMegaGroup(r8) == true) goto L322;
    L314:
        r44 = false;
        goto L313
    L512:
        if (r06 != 1) goto L534;
        ArrayList<String> r010 = new ArrayList();
        String r23 = r13.toString().toLowerCase();
        ArrayList<SearchAdapterHelper.HashtagObject> r312 = this.searchAdapterHelper.getHashtags();
        int r46 = 0;
    L515:
        if (r46 >= r312.size()) goto L524;
        SearchAdapterHelper.HashtagObject r58 = r312.get(r46);
        if (r58 == null) goto L523;
        String r65 = r58.hashtag;
        if (r65 == null) goto L523;
        if (r65.startsWith(r23) == false) goto L523;
        r010.add(r58.hashtag);
    L523:
        r46 = r46 + 1;
        goto L515
    L524:
        this.searchResultHashtags = r010;
        this.stickers = null;
        this.searchResultUsernames = null;
        this.searchResultUsernamesMap = null;
        this.quickReplies = null;
        this.searchResultCommands = null;
        this.searchResultCommandsEphemeral = null;
        this.searchResultCommandsHelp = null;
        this.searchResultCommandsUsers = null;
        this.searchResultSuggestions = null;
        this.contextMedia = false;
        this.searchResultBotContext = null;
        notifyDataSetChanged();
        MentionsAdapterDelegate r011 = this.delegate;
        if (this.searchResultHashtags.isEmpty() == true) goto L527;
    L530:
        boolean r1010 = true;
    L531:
        r011.needChangePanelVisibility(r1010);
        return;
    L527:
        if (this.hintHashtag != null) goto L530;
        r1010 = false;
        goto L531
    L534:
        if (r06 != 2) goto L590;
        ArrayList<String> r012 = new ArrayList();
        ArrayList<String> r24 = new ArrayList();
        ArrayList<TLRPC.User> r313 = new ArrayList();
        ArrayList<Boolean> r47 = new ArrayList();
        String r59 = r13.toString().toLowerCase();
        int r66 = 0;
    L537:
        if (r66 >= this.botInfo.size()) goto L551;
        TL_bots.BotInfo r73 = this.botInfo.valueAt(r66);
        int r911 = 0;
    L540:
        if (r911 >= r73.commands.size()) goto L549;
        TLRPC.BotCommand r1011 = r73.commands.get(r911);
        if (r1011 == null) goto L548;
        String r116 = r1011.command;
        if (r116 == null) goto L548;
        if (r116.startsWith(r59) == false) goto L548;
        r012.add("/" + r1011.command);
        r24.add(r1011.description);
        r313.add(r22.getUser(Long.valueOf(r73.user_id)));
        r47.add(Boolean.valueOf(r1011.ephemeral));
    L548:
        r911 = r911 + 1;
        goto L540
    L549:
        r66 = r66 + 1;
        goto L537
    L551:
        if (this.parentFragment != null) goto L553;
    L577:
        ?? r67 = 0;
        this.quickRepliesQuery = null;
        this.quickReplies = null;
    L578:
        this.searchResultHashtags = r67;
        this.stickers = r67;
        this.searchResultUsernames = r67;
        this.searchResultUsernamesMap = r67;
        this.searchResultSuggestions = r67;
        this.searchResultCommands = r012;
        this.searchResultCommandsHelp = r24;
        this.searchResultCommandsUsers = r313;
        this.searchResultCommandsEphemeral = r47;
        this.contextMedia = false;
        this.searchResultBotContext = r67;
        notifyDataSetChanged();
        MentionsAdapterDelegate r25 = this.delegate;
        if (r012.isEmpty() == false) goto L586;
        ArrayList<QuickRepliesController.QuickReply> r013 = this.quickReplies;
        if (r013 != null) goto L583;
    L585:
        boolean r1012 = false;
    L587:
        r25.needChangePanelVisibility(r1012);
        return;
    L583:
        if (r013.isEmpty() == true) goto L585;
    L586:
        r1012 = true;
        goto L587
    L553:
        if (DialogObject.isEncryptedDialog(this.dialog_id) == true) goto L577;
        if (this.parentFragment.getChatMode() != 0) goto L577;
        if (this.parentFragment.getCurrentUser() == null) goto L577;
        if (this.parentFragment.getCurrentUser().bot == true) goto L577;
        if (UserObject.isReplyUser(this.parentFragment.getCurrentUser()) == true) goto L577;
        if (UserObject.isService(this.parentFragment.getCurrentUser().id) == true) goto L577;
        QuickRepliesController r68 = QuickRepliesController.getInstance(this.currentAccount);
        r68.load();
        this.quickRepliesQuery = r59;
        this.quickReplies = new ArrayList();
        int r74 = 0;
    L566:
        if (r74 >= r68.replies.size()) goto L576;
        QuickRepliesController.QuickReply r82 = r68.replies.get(r74);
        if (r82.isSpecial() == true) goto L575;
        String r912 = r82.name.toLowerCase();
        if (r912.startsWith(r59) == false) goto L573;
    L574:
        this.quickReplies.add(r82);
        goto L575
    L573:
        if (AndroidUtilities.translitSafe(r912).startsWith(r59) == true) goto L574;
    L575:
        r74 = r74 + 1;
        goto L566
    L576:
        r67 = 0;
        goto L578
    L590:
        if (r06 != 3) goto L603;
        String[] r014 = AndroidUtilities.getCurrentKeyboardLanguage();
        if (Arrays.equals(r014, this.lastSearchKeyboardLanguage) == true) goto L594;
        MediaDataController.getInstance(this.currentAccount).fetchNewEmojiKeywords(r014);
    L594:
        this.lastSearchKeyboardLanguage = r014;
        MediaDataController r015 = MediaDataController.getInstance(this.currentAccount);
        String[] r210 = this.lastSearchKeyboardLanguage;
        String r314 = r13.toString();
        MentionsAdapter$$ExternalSyntheticLambda6 r48 = new MentionsAdapter$$ExternalSyntheticLambda6(this);
        if (SharedConfig.suggestAnimatedEmoji == true) goto L597;
    L599:
        boolean r1013 = false;
    L600:
        r015.getEmojiSuggestions(r210, r314, false, r48, r1013);
        return;
    L597:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L599;
        r1013 = true;
        goto L600
    L603:
        if (r06 != 4) goto L692;
        this.searchResultHashtags = null;
        this.searchResultUsernames = null;
        this.searchResultUsernamesMap = null;
        this.searchResultSuggestions = null;
        this.searchResultCommands = null;
        this.searchResultCommandsEphemeral = null;
        this.quickReplies = null;
        this.searchResultCommandsHelp = null;
        this.searchResultCommandsUsers = null;
        return;
    L692:
        return;
    L284:
        if (r56 != null) goto L286;
    L288:
        HashtagHint r315 = this.topHint;
        if (r315 == 0) goto L291;
        r315.set(r143, this.hintHashtag, r8);
    L291:
        HashtagHint r316 = this.bottomHint;
        if (r316 == null) goto L294;
        r316.set(1, this.hintHashtag, r8);
        goto L294
    L286:
        if (this.hintHashtag != null) goto L288;
        notifyItemRangeRemoved(r143, 2);
    L213:
        if (r12 < 0) goto L277;
        if (r12 >= r7.length()) goto L276;
        char r69 = r7.charAt(r12);
        if (r12 == 0) goto L227;
        int r1014 = r12 - 1;
        if (r7.charAt(r1014) == ' ') goto L227;
        if (r7.charAt(r1014) == '\n') goto L227;
        if (r69 == ':') goto L227;
    L275:
        r13.insert(0, r69);
    L227:
        if (r69 != '@') goto L243;
        boolean r913 = this.searchInDialogs;
        if (r913 == true) goto L235;
        if (this.needUsernames == true) goto L235;
        if (this.needBotContext == false) goto L275;
        if (r12 != 0) goto L275;
    L235:
        if (r913 == false) goto L237;
    L241:
        this.resultStartPosition = r12;
        this.resultLength = r13.length() + 1;
        r06 = 0;
        goto L212
    L237:
        if (this.info != null) goto L241;
        if (r12 == 0) goto L241;
        this.lastText = r7;
        this.lastPosition = r39;
        this.messages = r43;
        this.delegate.needChangePanelVisibility(false);
        return;
    L243:
        if (r69 == '#') goto L245;
        if (r12 == 0) goto L261;
    L265:
        if (r69 != ':') goto L275;
        if (r13.length() <= 0) goto L275;
        if (" !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~\n".indexOf(r13.charAt(0)) < 0) goto L273;
        int r148 = 1;
        if (r13.length() <= 1) goto L275;
    L274:
        this.resultStartPosition = r12;
        this.resultLength = r13.length() + r148;
        r06 = 3;
        goto L211
    L273:
        r148 = 1;
        goto L274
    L261:
        if (this.botInfo == null) goto L265;
        if (r69 != '/') goto L265;
        this.resultStartPosition = r12;
        this.resultLength = r13.length() + 1;
        r06 = 2;
        goto L211
    L245:
        if (ChatObject.isChannelAndNotMegaGroup(r8) == false) goto L254;
        if (TextUtils.isEmpty(ChatObject.getPublicUsername(r8)) == true) goto L254;
        String r016 = r7.substring(r12);
        this.hintHashtag = r016;
        if (r016.length() >= 4) goto L251;
    L252:
        this.hintHashtag = null;
        goto L254
    L251:
        if (this.hintHashtag.matches("^[#$][\\p{L}_-]+$") == false) goto L252;
    L254:
        if (this.searchAdapterHelper.loadRecentHashtags() == false) goto L257;
        this.resultStartPosition = r12;
        this.resultLength = r13.length() + 1;
        boolean r149 = false;
        r13.insert(0, r69);
        r06 = 1;
    L256:
        r12 = -1;
        r143 = r149;
        goto L278
    L257:
        this.lastText = r7;
        this.lastPosition = r39;
        this.messages = r43;
        return;
    L276:
        r12 = r12 - 1;
        goto L213
    L277:
        r149 = false;
        goto L256
    L693:
        return;
    L132:
        if (this.stickers.size() >= 5) goto L134;
        this.delayLocalResults = true;
        this.delegate.needChangePanelVisibility(false);
        this.visibleByStickersSearch = false;
    L138:
        if (this.visibleByStickersSearch == false) goto L140;
        this.delegate.needChangePanelVisibility(false);
        this.visibleByStickersSearch = false;
    L124:
        searchServerStickers(this.lastSticker, r92);
        goto L125
    L115:
        if (r54.isEmpty() == true) goto L117;
        addStickersToResult(r54, null);
        goto L117
    L112:
        r54 = null;
        goto L113
    L120:
        r43 = r28;
        r21 = r38;
        r39 = r27;
        goto L122
    L92:
        r03 = false;
    L142:
        if (this.visibleByStickersSearch == true) goto L144;
        return;
    L144:
        if (r02 != 2) goto L689;
        this.visibleByStickersSearch = false;
        this.delegate.needChangePanelVisibility(false);
        notifyDataSetChanged();
        return;
    L689:
        return;
    L82:
        if (ChatObject.canSendStickers(r8) == true) goto L85;
    L84:
        r39 = r27;
        r43 = r28;
        if (r29 == true) goto L188;
        if (this.needBotContext == false) goto L188;
        if (r7.charAt(0) != '@') goto L188;
        int r510 = r7.indexOf(32);
        int r211 = r7.length();
        if (r510 <= 0) goto L156;
        String r212 = r7.substring(1, r510);
        String r610 = r7.substring(r510 + 1);
        String r511 = r212;
        int r213 = 1;
    L163:
        if (r511 != null) goto L165;
    L186:
        searchForContextBot(r0, r610);
        r63 = null;
    L204:
        r06 = 65535;
        goto L206
    L165:
        if (r511.length() < r213) goto L186;
        int r214 = 1;
    L168:
        if (r214 >= r511.length()) goto L185;
        char r914 = r511.charAt(r214);
        if (r914 < '0') goto L174;
        if (r914 > '9') goto L174;
    L184:
        r214 = r214 + 1;
    L174:
        if (r914 < 'a') goto L178;
        if (r914 <= 'z') goto L184;
    L178:
        if (r914 < 'A') goto L182;
        if (r914 <= 'Z') goto L184;
    L182:
        if (r914 == '_') goto L184;
    L185:
        r0 = r511;
        goto L186
    L156:
        if (r7.charAt(r211 - 1) == 't') goto L158;
    L162:
        r213 = 1;
        searchForContextBot(null, null);
        r511 = null;
        r610 = null;
        goto L163
    L158:
        if (r7.charAt(r211 - 2) != 'o') goto L162;
        if (r7.charAt(r211 - 3) != 'b') goto L162;
        r213 = 1;
        r511 = r7.substring(1);
        r610 = _UrlKt.FRAGMENT_ENCODE_SET;
    L188:
        if (this.allowStickers == false) goto L197;
        ChatActivity r017 = this.parentFragment;
        if (r017 == null) goto L197;
        if (r017.getCurrentEncryptedChat() != null) goto L197;
        if (r8 == null) goto L199;
        if (ChatObject.canSendStickers(r8) == false) goto L197;
    L199:
        if (r7.trim().length() < 2) goto L197;
        if (r7.trim().indexOf(32) >= 0) goto L197;
        r63 = null;
        searchForContextBot(null, null);
    L197:
        r63 = null;
        searchForContextBot(null, null);
        goto L204
    L62:
        if (Emoji.isValidEmoji(r92) == false) goto L64;
    L65:
        r36 = r16;
        goto L67
    L64:
        if (Emoji.isValidEmoji(this.lastSticker) == false) goto L66;
    L59:
        r16 = true;
        r92 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L60
    L33:
        if (r7.length() <= 0) goto L37;
        if (r7.length() > 14) goto L37;
        r35 = true;
        goto L38
    L29:
        r12 = r27;
    L606:
        if (r30 == false) goto L608;
        searchGlobalSenders(null, null, null);
    L608:
        searchForContextBot(null, null);
        this.delegate.needChangePanelVisibility(false);
        this.lastText = null;
        clearStickers();
        return;
    L5:
        r7 = r26.toString();
        goto L6
    }

    public void setAllowBots(boolean r1) {
        this.allowBots = r1;
    }

    public void setAllowChats(boolean r1) {
        this.allowChats = r1;
    }

    public void setAllowStickers(boolean r1) {
        this.allowStickers = r1;
    }

    public void setBotInfo(LongSparseArray<TL_bots.BotInfo> r1) {
        this.botInfo = r1;
    }

    public void setBotsCount(int r1) {
        this.botsCount = r1;
    }

    public void setChatInfo(TLRPC.ChatFull r7) {
        this.currentAccount = UserConfig.selectedAccount;
        this.info = r7;
        if (this.inlineMediaEnabled == false) goto L5;
    L13:
        String r1 = this.lastText;
        if (r1 == null) goto L17;
        searchUsernameOrHashtag(r1, this.lastPosition, this.messages, this.lastUsernameOnly, this.lastForSearch);
        return;
    L17:
        return;
    L5:
        if (this.foundContextBot == null) goto L13;
        ChatActivity r72 = this.parentFragment;
        if (r72 == null) goto L13;
        TLRPC.Chat r73 = r72.getCurrentChat();
        if (r73 == null) goto L13;
        boolean r74 = ChatObject.canSendStickers(r73);
        this.inlineMediaEnabled = r74;
        if (r74 == false) goto L13;
        this.searchResultUsernames = null;
        notifyDataSetChanged();
        this.delegate.needChangePanelVisibility(false);
        processFoundUser(this.foundContextBot);
        goto L13
    }

    public void setDialogId(long r3) {
        if (this.dialog_id == r3) goto L6;
        this.dialog_id = r3;
        return;
    }

    public void setIsReversed(boolean r2) {
        if (this.isReversed == r2) goto L11;
        this.isReversed = r2;
        int r22 = getLastItemCount();
        if (r22 <= 0) goto L8;
        notifyItemChanged(0);
    L8:
        if (r22 <= 1) goto L12;
        notifyItemChanged(r22 - 1);
        return;
    L12:
        return;
    }

    public void setNeedBotContext(boolean r1) {
        this.needBotContext = r1;
    }

    public void setNeedUsernames(boolean r1) {
        this.needUsernames = r1;
    }

    public void setParentFragment(ChatActivity r1) {
        this.parentFragment = r1;
    }

    public void setSearchInDialogs(boolean r1) {
        this.searchInDialogs = r1;
    }

    public void setSearchingMentions(boolean r1) {
        this.isSearchingMentions = r1;
    }

    public void setUserOrChat(TLRPC.User r1, TLRPC.Chat r2) {
        this.user = r1;
        this.chat = r2;
    }
}
