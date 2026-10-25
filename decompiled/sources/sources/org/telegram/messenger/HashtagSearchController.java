package org.telegram.messenger;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.exteragram.messenger.utils.chats.ChatUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationBadge;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class HashtagSearchController {
    public static final int HISTORY_LIMIT = 100;
    private static volatile HashtagSearchController[] Instance;
    private static final Object[] lockObjects = null;
    private final SearchResult channelPostsSearch;
    public final int currentAccount;
    public final ArrayList<String> history;
    private final SharedPreferences historyPreferences;
    private final SearchResult localPostsSearch;
    private final SearchResult myMessagesSearch;

    public static final class MessageCompositeID {
        final long dialog_id;
        final int id;

        public MessageCompositeID(long r1, int r3) {
            this.dialog_id = r1;
            this.id = r3;
        }

        public MessageCompositeID(TLRPC.Message r3) {
            this(MessageObject.getDialogId(r3), r3.id);
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L15:
            return false;
        L8:
            if (MessageCompositeID.class != r7.getClass()) goto L15;
            MessageCompositeID r72 = (MessageCompositeID) r7;
            if (this.dialog_id != r72.dialog_id) goto L15;
            if (this.id != r72.id) goto L15;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Long.valueOf(this.dialog_id), Integer.valueOf(this.id)});
        }
    }

    public static class SearchResult {
        public Runnable cancel;
        public int count;
        private final int currentAccount;
        public boolean endReached;
        public int filteredCount;
        public final HashMap<MessageCompositeID, Integer> generatedIds;
        public int lastGeneratedId;
        public String lastHashtag;
        public int lastOffsetId;
        public TLRPC.Peer lastOffsetPeer;
        public int lastOffsetRate;
        public boolean loading;
        public final ArrayList<MessageObject> messages;
        public int reqId;
        public int selectedIndex;

        public SearchResult(int r2) {
            this.messages = new ArrayList();
            this.generatedIds = new HashMap();
            this.reqId = -1;
            this.lastGeneratedId = Integer.MAX_VALUE;
            this.currentAccount = r2;
        }

        public void clear() {
            if (this.reqId < 0) goto L5;
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
            this.reqId = -1;
        L5:
            Runnable r0 = this.cancel;
            if (r0 == null) goto L8;
            r0.run();
            this.cancel = null;
        L8:
            this.messages.clear();
            this.generatedIds.clear();
            this.lastOffsetRate = 0;
            this.lastOffsetId = 0;
            this.lastOffsetPeer = null;
            this.lastGeneratedId = 2147483637;
            this.lastHashtag = null;
            this.selectedIndex = 0;
            this.count = 0;
            this.filteredCount = 0;
            this.endReached = false;
        }

        public int getMask() {
            int r2 = 1;
            if (this.selectedIndex < (this.messages.size() - 1)) goto L7;
            r2 = 0;
        L7:
            if (this.selectedIndex > 0) goto L9;
            return r2;
        L9:
            return r2 | 2;
        }
    }

    public static /* synthetic */ void $r8$lambda$20YmGPiF4odJSE86fK15_IASnPo(HashtagSearchController r0, SearchResult r1, String r2, String r3, Runnable[] r4, int r5, int r6, int r7, Long r8) {
        r0.lambda$searchHashtag$0(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$4qSxZ4d-JjfKGNfhtjvVef8gVzs, reason: not valid java name */
    public static /* synthetic */ void m4126$r8$lambda$4qSxZ4dJjfKGNfhtjvVef8gVzs(HashtagSearchController r0, int r1, String r2, int[] r3, SearchResult r4, int r5, int r6, int r7, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$searchHashtag$2(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$e4VdFpE2lKIPqNzZngFVpacV7Gc(HashtagSearchController r0, int[] r1, SearchResult r2, int r3, TLRPC.messages_Messages r4, ArrayList r5, int r6, int r7, int r8, int r9) {
        r0.lambda$searchHashtag$1(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    static {
        Instance = new HashtagSearchController[16];
        lockObjects = new Object[16];
        int r1 = 0;
    L3:
        if (r1 >= 16) goto L5;
        lockObjects[r1] = new Object();
        r1 = r1 + 1;
        goto L3
    }

    private HashtagSearchController(int r4) {
        this.history = new ArrayList();
        this.currentAccount = r4;
        this.myMessagesSearch = new SearchResult(r4);
        this.channelPostsSearch = new SearchResult(r4);
        this.localPostsSearch = new SearchResult(r4);
        this.historyPreferences = ApplicationLoader.applicationContext.getSharedPreferences("hashtag_search_history" + r4, 0);
        loadHistoryFromPref();
    }

    public static HashtagSearchController getInstance(int r3) {
        HashtagSearchController r0 = Instance[r3];
        if (r0 != null) goto L15;
        Object r1 = lockObjects[r3];
        monitor-enter(r1);
        HashtagSearchController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        HashtagSearchController[] r03 = Instance;     // Catch: Throwable -> L9
        HashtagSearchController r2 = new HashtagSearchController(r3);     // Catch: Throwable -> L9
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

    private /* synthetic */ void lambda$searchHashtag$0(SearchResult r7, String r8, String r9, Runnable[] r10, int r11, int r12, int r13, Long r14) {
        if (TextUtils.equals(r7.lastHashtag, r8) == true) goto L6;
        return;
    L6:
        if (MessagesController.getInstance(this.currentAccount).getUserOrChat(r9) == null) goto L8;
        searchHashtag(r8, r11, r12, r13);
        return;
    L8:
        if (r10[0] != r7.cancel) goto L13;
        r7.cancel = null;
        r7.loading = false;
        r7.endReached = true;
        r7.count = 0;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.hashtagSearchUpdated, new Object[]{Integer.valueOf(r11), Integer.valueOf(r7.count), Boolean.valueOf(r7.endReached), Integer.valueOf(r7.getMask()), Integer.valueOf(r7.selectedIndex), 0});
        return;
    }

    private /* synthetic */ void lambda$searchHashtag$1(int[] r21, SearchResult r22, int r23, TLRPC.messages_Messages r24, ArrayList r25, int r26, int r27, int r28, int r29) {
        if (r21[0] != r22.reqId) goto L37;
        r22.reqId = -1;
        r22.loading = false;
        if (r22.lastOffsetId == 0) goto L7;
    L9:
        r22.filteredCount += r23;
        r22.lastOffsetRate = r24.next_rate;
        int r4 = r25.size();
        int r5 = 0;
    L10:
        if (r5 >= r4) goto L15;
        Object r7 = r25.get(r5);
        r5 = r5 + 1;
        MessageObject r72 = (MessageObject) r7;
        MessageCompositeID r9 = new MessageCompositeID(r72.messageOwner);
        Integer r10 = r22.generatedIds.get(r9);
        if (r10 != null) goto L14;
        int r102 = r22.lastGeneratedId;
        r22.lastGeneratedId = r102 - 1;
        r10 = Integer.valueOf(r102);
        r22.generatedIds.put(r9, r10);
        r22.messages.add(r72);
    L14:
        TLRPC.Message r73 = r72.messageOwner;
        r73.realId = r73.id;
        r73.id = r10.intValue();
        goto L10
    L15:
        boolean r52 = true;
        if (r24.messages.isEmpty() == true) goto L22;
        ArrayList<TLRPC.Message> r42 = r24.messages;
        TLRPC.Message r43 = r42.get(r42.size() - 1);
        int r74 = r43.realId;
        if (r74 != 0) goto L21;
        r74 = r43.id;
    L21:
        r22.lastOffsetId = r74;
        r22.lastOffsetPeer = r43.peer_id;
    L22:
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r24.users, r24.chats, true, true);
        MessagesController.getInstance(this.currentAccount).putUsers(r24.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r24.chats, false);
        if (r24.messages.size() < r26) goto L26;
        r52 = false;
    L26:
        r22.endReached = r52;
        r22.count = Math.max(Math.max(r24.count - r22.filteredCount, 0), r22.messages.size());
        if (r25.isEmpty() == true) goto L29;
    L32:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.messagesDidLoad, new Object[]{0L, Integer.valueOf(r25.size()), r25, Boolean.FALSE, 0, 0, 0, 0, 2, Boolean.TRUE, Integer.valueOf(r27), Integer.valueOf(r29), 0, 0, 7});
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.hashtagSearchUpdated, new Object[]{Integer.valueOf(r27), Integer.valueOf(r22.count), Boolean.valueOf(r22.endReached), Integer.valueOf(r22.getMask()), Integer.valueOf(r22.selectedIndex), 0});
        return;
    L29:
        if (r22.endReached == true) goto L32;
        searchHashtag(null, r27, r28, r29);
        return;
    L7:
        if (r22.messages.isEmpty() == false) goto L9;
        r22.filteredCount = 0;
        goto L9
    }

    private /* synthetic */ void lambda$searchHashtag$2(int r26, String r27, int[] r28, SearchResult r29, int r30, int r31, int r32, TLObject r33, TLRPC.TL_error r34) {
        if ((r33 instanceof TLRPC.messages_Messages) == false) goto L21;
        TLRPC.messages_Messages r7 = (TLRPC.messages_Messages) r33;
        ArrayList r8 = new ArrayList();
        ArrayList<TLRPC.Message> r0 = r7.messages;
        int r1 = r0.size();
        int r3 = 0;
        int r6 = 0;
    L5:
        if (r3 >= r1) goto L13;
        TLRPC.Message r4 = r0.get(r3);
        r3 = r3 + 1;
        TLRPC.Message r11 = r4;
        if (ChatUtils.hasRestrictionReason(r11.restriction_reason, "terms") == true) goto L8;
        MessageObject r9 = new MessageObject(this.currentAccount, r11, null, null, null, null, null, true, true, 0, false, false, false, r26);
        if (r9.hasValidGroupId() == false) goto L12;
        r9.isPrimaryGroupMessage = true;
    L12:
        r9.setQuery(r27, false);
        r8.add(r9);
        goto L5
    L8:
        r6 = r6 + 1;
        goto L5
    L13:
        AndroidUtilities.runOnUIThread(new HashtagSearchController$$ExternalSyntheticLambda2(this, r28, r29, r6, r7, r8, r30, r31, r26, r32));
        return;
    }

    private void loadHistoryFromPref() {
        int r2 = 0;
        int r0 = this.historyPreferences.getInt(NotificationBadge.NewHtcHomeBadger.COUNT, 0);
        this.history.clear();
        this.history.ensureCapacity(r0);
    L3:
        if (r2 >= r0) goto L10;
        String r1 = this.historyPreferences.getString("e_" + r2, _UrlKt.FRAGMENT_ENCODE_SET);
        if (r1.startsWith("#") == true) goto L9;
        if (r1.startsWith("$") == true) goto L9;
        r1 = "#".concat(r1);
    L9:
        this.history.add(r1);
        r2 = r2 + 1;
        goto L3
    }

    private void saveHistoryToPref() {
        SharedPreferences.Editor r0 = this.historyPreferences.edit();
        r0.clear();
        r0.putInt(NotificationBadge.NewHtcHomeBadger.COUNT, this.history.size());
        int r1 = 0;
    L4:
        if (r1 >= this.history.size()) goto L6;
        r0.putString("e_" + r1, this.history.get(r1));
        r1 = r1 + 1;
        goto L4
    L6:
        r0.apply();
    }

    public void clearHistory() {
        this.history.clear();
        saveHistoryToPref();
    }

    public void clearSearchResults() {
        this.myMessagesSearch.clear();
        this.channelPostsSearch.clear();
    }

    public void clearSearchResults(int r1) {
        getSearchResult(r1).clear();
    }

    public int getCount(int r1) {
        return getSearchResult(r1).count;
    }

    public ArrayList<MessageObject> getMessages(int r1) {
        return getSearchResult(r1).messages;
    }

    public SearchResult getSearchResult(int r2) {
        if (r2 != 1) goto L7;
        return this.myMessagesSearch;
    L7:
        if (r2 != 2) goto L11;
        return this.channelPostsSearch;
    L11:
        if (r2 == 3) goto L13;
        GlShader$$ExternalSyntheticBUOutline1.m("Unknown search type");
        return null;
    L13:
        return this.localPostsSearch;
    }

    public boolean isEndReached(int r1) {
        return getSearchResult(r1).endReached;
    }

    public void jumpToMessage(int r8, int r9, int r10) {
        SearchResult r102 = getSearchResult(r10);
        if (r9 >= 0) goto L5;
        return;
    L5:
        if (r9 >= r102.messages.size()) goto L10;
        r102.selectedIndex = r9;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.hashtagSearchUpdated, new Object[]{Integer.valueOf(r8), Integer.valueOf(r102.count), Boolean.valueOf(r102.endReached), Integer.valueOf(r102.getMask()), Integer.valueOf(r102.selectedIndex), Integer.valueOf(r102.messages.get(r9).messageOwner.id)});
        return;
    }

    public void putToHistory(String r3) {
        if (r3.startsWith("#") == false) goto L5;
    L7:
        int r0 = this.history.indexOf(r3);
        if (r0 == (-1)) goto L12;
        if (r0 != 0) goto L11;
        return;
    L11:
        this.history.remove(r0);
    L12:
        this.history.add(0, r3);
        if (this.history.size() < 100) goto L15;
        ArrayList<String> r32 = this.history;
        r32.subList(99, r32.size()).clear();
    L15:
        saveHistoryToPref();
        return;
    L5:
        if (r3.startsWith("$") == true) goto L7;
    }

    public void removeHashtagFromHistory(String r2) {
        int r22 = this.history.indexOf(r2);
        if (r22 == (-1)) goto L6;
        this.history.remove(r22);
        saveHistoryToPref();
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void searchHashtag(String r11, int r12, int r13, int r14) {
        SearchResult r2 = getSearchResult(r13);
        if (r2.lastHashtag != null) goto L6;
        if (r11 != null) goto L6;
        return;
    L6:
        if (r11 != null) goto L8;
    L10:
        String r0 = r2.lastHashtag;
        if (r11 != null) goto L14;
        String r3 = r0;
    L20:
        r2.lastHashtag = r3;
        int r112 = r3.indexOf(64);
        TLObject r02 = null;
        if (r112 < 0) goto L23;
        String r1 = r3.substring(r112 + 1);
        String r113 = r3.substring(0, r112);
        String r4 = r1;
    L24:
        r2.loading = true;
        if (TextUtils.isEmpty(r4) == true) goto L31;
        r02 = MessagesController.getInstance(this.currentAccount).getUserOrChat(r4);
        if (r02 != null) goto L31;
        Runnable r10 = MessagesController.getInstance(this.currentAccount).getUserNameResolver().resolve(r4, new HashtagSearchController$$ExternalSyntheticLambda0(this, r2, r3, r4, r5, r12, r13, r14));
        r2.cancel = r10;
        Runnable[] r5 = {r10};
        return;
    L31:
        if (r13 != 1) goto L35;
        TLRPC.TL_messages_searchGlobal r114 = new TLRPC.TL_messages_searchGlobal();
        r114.limit = 21;
        r114.q = r3;
        r114.filter = new TLRPC.TL_inputMessagesFilterEmpty();
        r114.offset_peer = new TLRPC.TL_inputPeerEmpty();
        TLRPC.TL_channels_searchPosts r115 = r114;
        if (r2.lastOffsetPeer == null) goto L43;
        r114.offset_rate = r2.lastOffsetRate;
        r114.offset_id = r2.lastOffsetId;
        r114.offset_peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r2.lastOffsetPeer);
        r115 = r114;
    L43:
        int r102 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r115, new HashtagSearchController$$ExternalSyntheticLambda1(this, r13, r3, r4, r2, 21, r12, r14));
        r2.reqId = r102;
        int[] r42 = {r102};
        return;
    L35:
        if (r02 == null) goto L40;
        TLRPC.TL_messages_search r122 = new TLRPC.TL_messages_search();
        r122.filter = new TLRPC.TL_inputMessagesFilterEmpty();
        r122.peer = MessagesController.getInputPeer(r02);
        r122.q = r113;
        r122.limit = 21;
        int r116 = r2.lastOffsetId;
        if (r116 == 0) goto L39;
        r122.offset_id = r116;
    L39:
        r115 = r122;
        goto L43
    L40:
        TLRPC.TL_channels_searchPosts r117 = new TLRPC.TL_channels_searchPosts();
        r117.flags |= 1;
        r117.hashtag = r3;
        r117.limit = 21;
        r117.offset_peer = new TLRPC.TL_inputPeerEmpty();
        r115 = r117;
        if (r2.lastOffsetPeer == null) goto L43;
        r117.offset_rate = r2.lastOffsetRate;
        r117.offset_id = r2.lastOffsetId;
        r117.offset_peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r2.lastOffsetPeer);
        r115 = r117;
        goto L43
    L23:
        r4 = null;
        r113 = r3;
        goto L24
    L14:
        if (TextUtils.equals(r11, r0) == true) goto L17;
        r2.clear();
    L19:
        r3 = r11;
        goto L20
    L17:
        if (r2.loading == false) goto L19;
        return;
    L8:
        if (r11.isEmpty() == false) goto L10;
    }
}
