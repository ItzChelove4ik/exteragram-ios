package org.telegram.ui.Adapters;

import android.util.Pair;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Adapters.DialogsSearchAdapter;
import org.telegram.ui.Components.ShareAlert;

/* loaded from: classes3.dex */
public class SearchAdapterHelper {
    private boolean allResultsAreGlobal;
    private boolean allowGlobalResults;
    private int currentAccount;
    private SearchAdapterHelperDelegate delegate;
    private final ArrayList<TLObject> globalSearch;
    private final LongSparseArray<TLObject> globalSearchMap;
    private final ArrayList<TLObject> groupSearch;
    private final LongSparseArray<TLObject> groupSearchMap;
    private ArrayList<HashtagObject> hashtags;
    private HashMap<String, HashtagObject> hashtagsByText;
    private boolean hashtagsLoadedFromDb;
    private String lastFoundChannel;
    private String lastFoundUsername;
    private ArrayList<DialogsSearchAdapter.RecentSearchObject> localRecentResults;
    private ArrayList<Object> localSearchResults;
    private final ArrayList<TLObject> localServerSearch;
    private final ArrayList<Integer> pendingRequestIds;
    private final LongSparseArray<TLObject> phoneSearchMap;
    private final ArrayList<Object> phonesSearch;

    /* loaded from: classes6.dex */
    public static class HashtagObject {
        int date;
        String hashtag;

        public HashtagObject() {
        }
    }

    public interface SearchAdapterHelperDelegate {
        default boolean canApplySearchResults(int r1) {
            return true;
        }

        default LongSparseArray<TLRPC.GroupCallParticipant> getExcludeCallParticipants() {
            return null;
        }

        default LongSparseArray<TLRPC.User> getExcludeUsers() {
            return null;
        }

        void onDataSetChanged(int r1);

        default void onSetHashtags(ArrayList<HashtagObject> r1, HashMap<String, HashtagObject> r2) {
        }
    }

    public static /* synthetic */ void $r8$lambda$1Ewb0_tvdddDTi0nc_LFiAhkaYo(SearchAdapterHelper r0, ArrayList r1, HashMap r2) {
        r0.lambda$loadRecentHashtags$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$4T66PjvN4sk33OLhHZOIR1zOn9I(SearchAdapterHelper r0) {
        r0.lambda$loadRecentHashtags$6();
    }

    public static /* synthetic */ void $r8$lambda$OggLCBwhnURzNW_4igqukaKyivo(SearchAdapterHelper r0, ArrayList r1, int r2, AtomicInteger r3, AtomicInteger r4, ArrayList r5, int r6, Runnable r7, TLObject r8, TLRPC.TL_error r9) {
        r0.lambda$queryServerSearch$3(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$RtrlhroMtXSvAXmig9EimMIatTo(SearchAdapterHelper r0, String r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$queryServerSearch$0(r1, r2, r3, r4);
    }

    public static /* synthetic */ int $r8$lambda$T4q89PGiVpYuJ8ZXW8u0FTBmGek(HashtagObject r0, HashtagObject r1) {
        int r02 = r0.date;
        int r12 = r1.date;
        if (r02 >= r12) goto L6;
        return 1;
    L6:
        if (r02 <= r12) goto L9;
        return -1;
    L9:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$bC9BjpRQCCo0EChd9y4P__csqyY(SearchAdapterHelper r0, ArrayList r1, int r2, TLObject r3, TLRPC.TL_error r4, AtomicInteger r5, AtomicInteger r6, ArrayList r7, int r8, Runnable r9) {
        r0.lambda$queryServerSearch$2(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    /* renamed from: $r8$lambda$jsS1bHgYu20kHGdpO-33lptNK2w, reason: not valid java name */
    public static /* synthetic */ void m5950$r8$lambda$jsS1bHgYu20kHGdpO33lptNK2w(SearchAdapterHelper r0, ArrayList r1) {
        r0.lambda$putRecentHashtags$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$l6VjP7qW0qvuqm_zOjFDhTizDTA(SearchAdapterHelper r0, int r1, boolean r2, boolean r3, boolean r4, boolean r5, long r6, String r8, TLObject r9, TLRPC.TL_error r10) {
        r0.lambda$queryServerSearch$1(r1, r2, r3, r4, r5, r6, r8, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$r_ekvBtGwYf4SmIzHdRote4ixz8(SearchAdapterHelper r0) {
        r0.lambda$clearRecentHashtags$8();
    }

    public SearchAdapterHelper(boolean r2) {
        this.pendingRequestIds = new ArrayList();
        this.lastFoundUsername = null;
        this.localServerSearch = new ArrayList();
        this.globalSearch = new ArrayList();
        this.globalSearchMap = new LongSparseArray();
        this.groupSearch = new ArrayList();
        this.groupSearchMap = new LongSparseArray();
        this.phoneSearchMap = new LongSparseArray();
        this.phonesSearch = new ArrayList();
        this.currentAccount = UserConfig.selectedAccount;
        this.allowGlobalResults = true;
        this.hashtagsLoadedFromDb = false;
        this.allResultsAreGlobal = r2;
    }

    public static boolean addUniqueObject(ArrayList<TLObject> r6, LongSparseArray<TLObject> r7, TLObject r8, long r9) {
        return addUniqueObject(r6, null, r7, r8, r9);
    }

    public static boolean addUniqueObject(ArrayList<TLObject> r2, ArrayList<TLObject> r3, LongSparseArray<TLObject> r4, TLObject r5, long r6) {
        TLObject r0 = r4.get(r6);
        if (r0 == null) goto L11;
        if (r3 != null) goto L6;
        return false;
    L6:
        if (r3.remove(r0) == false) goto L13;
        r2.add(r5);
        r4.put(r6, r5);
        return true;
    L13:
        return false;
    L11:
        r2.add(r5);
        r4.put(r6, r5);
        return true;
    }

    private /* synthetic */ void lambda$clearRecentHashtags$8() {
        MessagesStorage.getInstance(this.currentAccount).getDatabase().executeFast("DELETE FROM hashtag_recent_v2 WHERE 1").stepThis().dispose();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$loadRecentHashtags$5(ArrayList r1, HashMap r2) {
        setHashtags(r1, r2);
    }

    private /* synthetic */ void lambda$loadRecentHashtags$6() {
        SQLiteCursor r0 = MessagesStorage.getInstance(this.currentAccount).getDatabase().queryFinalized("SELECT id, date FROM hashtag_recent_v2 WHERE 1", new Object[0]);     // Catch: Exception -> L8
        ArrayList r1 = new ArrayList();     // Catch: Exception -> L8
        HashMap r3 = new HashMap();     // Catch: Exception -> L8
    L4:
        if (r0.next() == false) goto L6;
        HashtagObject r4 = new HashtagObject();     // Catch: Exception -> L8
        r4.hashtag = r0.stringValue(0);     // Catch: Exception -> L8
        r4.date = r0.intValue(1);     // Catch: Exception -> L8
        r1.add(r4);     // Catch: Exception -> L8
        r3.put(r4.hashtag, r4);     // Catch: Exception -> L8
        goto L4
    L6:
        r0.dispose();     // Catch: Exception -> L8
        Collections.sort(r1, new SearchAdapterHelper$$ExternalSyntheticLambda7());     // Catch: Exception -> L8
        AndroidUtilities.runOnUIThread(new SearchAdapterHelper$$ExternalSyntheticLambda8(this, r1, r3));     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$putRecentHashtags$7(ArrayList r6) {
        MessagesStorage.getInstance(this.currentAccount).getDatabase().beginTransaction();     // Catch: Exception -> L17
        SQLitePreparedStatement r0 = MessagesStorage.getInstance(this.currentAccount).getDatabase().executeFast("REPLACE INTO hashtag_recent_v2 VALUES(?, ?)");     // Catch: Exception -> L17
        int r1 = 0;
    L3:
        int r3 = 100;
        if (r1 >= r6.size()) goto L8;
        if (r1 == 100) goto L8;
        HashtagObject r2 = (HashtagObject) r6.get(r1);     // Catch: Exception -> L17
        r0.requery();     // Catch: Exception -> L17
        r0.bindString(1, r2.hashtag);     // Catch: Exception -> L17
        r0.bindInteger(2, r2.date);     // Catch: Exception -> L17
        r0.step();     // Catch: Exception -> L17
        r1 = r1 + 1;     // Catch: Exception -> L17
    L8:
        r0.dispose();     // Catch: Exception -> L17
        if (r6.size() <= 100) goto L15;
        SQLitePreparedStatement r02 = MessagesStorage.getInstance(this.currentAccount).getDatabase().executeFast("DELETE FROM hashtag_recent_v2 WHERE id = ?");     // Catch: Exception -> L17
    L12:
        if (r3 >= r6.size()) goto L14;
        r02.requery();     // Catch: Exception -> L17
        r02.bindString(1, ((HashtagObject) r6.get(r3)).hashtag);     // Catch: Exception -> L17
        r02.step();     // Catch: Exception -> L17
        r3 = r3 + 1;     // Catch: Exception -> L17
        goto L12
    L14:
        r02.dispose();     // Catch: Exception -> L17
    L15:
        MessagesStorage.getInstance(this.currentAccount).getDatabase().commitTransaction();     // Catch: Exception -> L17
        return;
    L17:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$queryServerSearch$0(String r7, boolean r8, TLObject r9, TLRPC.TL_error r10) {
        if (r10 != null) goto L12;
        TLRPC.TL_channels_channelParticipants r92 = (TLRPC.TL_channels_channelParticipants) r9;
        this.lastFoundChannel = r7.toLowerCase();
        int r0 = 0;
        MessagesController.getInstance(this.currentAccount).putUsers(r92.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r92.chats, false);
        this.groupSearch.clear();
        this.groupSearchMap.clear();
        this.groupSearch.addAll(r92.participants);
        long r1 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        int r72 = r92.participants.size();
    L4:
        if (r0 >= r72) goto L16;
        TLRPC.ChannelParticipant r102 = r92.participants.get(r0);
        long r3 = MessageObject.getPeerId(r102.peer);
        if (r8 == false) goto L8;
    L10:
        this.groupSearchMap.put(r3, r102);
    L11:
        r0 = r0 + 1;
        goto L4
    L8:
        if (r3 != r1) goto L10;
        this.groupSearch.remove(r102);
        goto L11
    L16:
        return;
    }

    private /* synthetic */ void lambda$queryServerSearch$1(int r17, boolean r18, boolean r19, boolean r20, boolean r21, long r22, String r24, TLObject r25, TLRPC.TL_error r26) {
        if (this.delegate.canApplySearchResults(r17) == false) goto L142;
        if (r26 != null) goto L143;
        TLRPC.TL_contacts_found r1 = (TLRPC.TL_contacts_found) r25;
        this.globalSearch.clear();
        this.globalSearchMap.clear();
        this.localServerSearch.clear();
        int r4 = 0;
        MessagesController.getInstance(this.currentAccount).putChats(r1.chats, false);
        MessagesController.getInstance(this.currentAccount).putUsers(r1.users, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r1.users, r1.chats, true, true);
        LongSparseArray r2 = new LongSparseArray();
        LongSparseArray r3 = new LongSparseArray();
        int r5 = 0;
    L7:
        if (r5 >= r1.chats.size()) goto L9;
        TLRPC.Chat r7 = r1.chats.get(r5);
        r2.put(r7.id, r7);
        r5 = r5 + 1;
        goto L7
    L9:
        int r52 = 0;
    L11:
        if (r52 >= r1.users.size()) goto L13;
        TLRPC.User r72 = r1.users.get(r52);
        r3.put(r72.id, r72);
        r52 = r52 + 1;
        goto L11
    L13:
        int r53 = 0;
    L14:
        long r9 = 0;
        if (r53 >= 2) goto L72;
        if (r53 == 0) goto L18;
        ArrayList<TLRPC.Peer> r73 = r1.results;
    L22:
        int r11 = 0;
    L24:
        if (r11 >= r73.size()) goto L70;
        TLRPC.Peer r12 = r73.get(r11);
        long r13 = r12.user_id;
        if (r13 == r9) goto L28;
        TLRPC.User r132 = (TLRPC.User) r3.get(r13);
        TLRPC.Chat r122 = null;
    L36:
        if (r122 == null) goto L51;
        if (r18 == false) goto L42;
        if (r19 == false) goto L44;
        if (ChatObject.canAddBotsToChat(r122) == false) goto L42;
    L44:
        if (this.allowGlobalResults == true) goto L48;
        if (ChatObject.isNotInChat(r122) == true) goto L42;
    L48:
        if (filter(r122) == false) goto L42;
        long r252 = r9;
        addUniqueObject(this.globalSearch, this.globalSearchMap, r122, -r122.id);
    L69:
        r11 = r11 + 1;
        r9 = r252;
    L42:
        r252 = r9;
        goto L69
    L51:
        r252 = r9;
        if (r132 == null) goto L69;
        if (r19 == true) goto L69;
        if (r20 == false) goto L56;
    L57:
        if (r21 == true) goto L61;
        if (r132.self == true) goto L69;
    L61:
        if (this.allowGlobalResults == true) goto L66;
        if (r53 != 1) goto L66;
        if (r132.contact == false) goto L69;
    L66:
        if (filter(r132) == false) goto L69;
        addUniqueObject(this.globalSearch, this.globalSearchMap, r132, r132.id);
        goto L69
    L56:
        if (r132.bot == true) goto L69;
    L28:
        long r133 = r12.chat_id;
        if (r133 == r9) goto L32;
        r122 = (TLRPC.Chat) r2.get(r133);
    L31:
        r132 = null;
        goto L36
    L32:
        long r123 = r12.channel_id;
        if (r123 == r9) goto L35;
        r122 = (TLRPC.Chat) r2.get(r123);
        goto L31
    L35:
        r122 = null;
    L70:
        r53 = r53 + 1;
        goto L14
    L18:
        if (this.allResultsAreGlobal == false) goto L70;
        r73 = r1.my_results;
        goto L22
    L72:
        if (this.allResultsAreGlobal == false) goto L74;
    L113:
        this.lastFoundUsername = r24.toLowerCase();
        return;
    L74:
        if (r4 >= r1.my_results.size()) goto L113;
        TLRPC.Peer r54 = r1.my_results.get(r4);
        long r6 = r54.user_id;
        if (r6 == 0) goto L78;
        TLRPC.User r134 = (TLRPC.User) r3.get(r6);
        TLRPC.Chat r92 = null;
    L87:
        if (r92 == null) goto L98;
        if (r18 == false) goto L112;
        if (r19 == false) goto L93;
        if (ChatObject.canAddBotsToChat(r92) == false) goto L112;
    L93:
        if ((-r92.id) == r22) goto L112;
        if (filter(r92) == false) goto L112;
        addUniqueObject(this.localServerSearch, this.globalSearch, this.globalSearchMap, r92, -r92.id);
    L112:
        r4 = r4 + 1;
        goto L74
    L98:
        if (r134 == null) goto L112;
        if (r19 == true) goto L112;
        if (r20 == false) goto L102;
    L103:
        if (r21 == true) goto L107;
        if (r134.self == true) goto L112;
    L107:
        if (r134.id == r22) goto L112;
        if (filter(r134) == false) goto L112;
        addUniqueObject(this.localServerSearch, this.globalSearch, this.globalSearchMap, r134, r134.id);
        goto L112
    L102:
        if (r134.bot == true) goto L112;
    L78:
        long r62 = r54.chat_id;
        if (r62 == 0) goto L83;
        TLRPC.Chat r55 = (TLRPC.Chat) r2.get(r62);
    L81:
        r92 = r55;
    L82:
        r134 = null;
        goto L87
    L83:
        long r56 = r54.channel_id;
        if (r56 == 0) goto L86;
        r55 = (TLRPC.Chat) r2.get(r56);
        goto L81
    L86:
        r92 = null;
        goto L82
    L143:
        return;
    }

    private /* synthetic */ void lambda$queryServerSearch$2(ArrayList r2, int r3, TLObject r4, TLRPC.TL_error r5, AtomicInteger r6, AtomicInteger r7, ArrayList r8, int r9, Runnable r10) {
        r2.set(r3, new Pair(r4, r5));
        Integer r32 = Integer.valueOf(r6.get());
        if (this.pendingRequestIds.contains(r32) == false) goto L27;
        this.pendingRequestIds.remove(r32);
        if (r7.incrementAndGet() != r8.size()) goto L25;
        int r33 = 0;
    L9:
        if (r33 >= r8.size()) goto L15;
        RequestDelegate r42 = (RequestDelegate) ((Pair) r8.get(r33)).second;
        Pair r52 = (Pair) r2.get(r33);
        if (r52 == null) goto L14;
        r42.run((TLObject) r52.first, (TLRPC.TL_error) r52.second);
    L14:
        r33 = r33 + 1;
        goto L9
    L15:
        removeGroupSearchFromGlobal();
        ArrayList<Object> r22 = this.localSearchResults;
        if (r22 == null) goto L18;
        mergeResults(r22, this.localRecentResults);
    L18:
        mergeExcludeResults();
        this.delegate.onDataSetChanged(r9);
        if (r10 == null) goto L26;
        r10.run();
        return;
    L26:
        return;
    L25:
        return;
    }

    private /* synthetic */ void lambda$queryServerSearch$3(ArrayList r12, int r13, AtomicInteger r14, AtomicInteger r15, ArrayList r16, int r17, Runnable r18, TLObject r19, TLRPC.TL_error r20) {
        AndroidUtilities.runOnUIThread(new SearchAdapterHelper$$ExternalSyntheticLambda4(this, r12, r13, r19, r20, r14, r15, r16, r17, r18));
    }

    private void putRecentHashtags(ArrayList<HashtagObject> r3) {
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new SearchAdapterHelper$$ExternalSyntheticLambda3(this, r3));
    }

    private void removeGroupSearchFromGlobal() {
        if (this.globalSearchMap.size() == 0) goto L11;
        int r0 = this.groupSearchMap.size();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L15;
        long r2 = this.groupSearchMap.keyAt(r1);
        TLRPC.User r22 = (TLRPC.User) this.globalSearchMap.get(r2);
        if (r22 == null) goto L10;
        this.globalSearch.remove(r22);
        this.localServerSearch.remove(r22);
        this.globalSearchMap.remove(r22.id);
    L10:
        r1 = r1 + 1;
        goto L6
    L15:
        return;
    }

    public void addGroupMembers(ArrayList<TLObject> r7) {
        this.groupSearch.clear();
        this.groupSearch.addAll(r7);
        int r0 = r7.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        TLObject r2 = r7.get(r1);
        if ((r2 instanceof TLRPC.ChatParticipant) == false) goto L8;
        this.groupSearchMap.put(((TLRPC.ChatParticipant) r2).user_id, r2);
    L10:
        r1 = r1 + 1;
        goto L3
    L8:
        if ((r2 instanceof TLRPC.ChannelParticipant) == false) goto L10;
        this.groupSearchMap.put(MessageObject.getPeerId(((TLRPC.ChannelParticipant) r2).peer), r2);
        goto L10
    L11:
        removeGroupSearchFromGlobal();
    }

    public void addHashtagsFromMessage(CharSequence r9) {
        if (r9 == null) goto L27;
        Matcher r0 = Pattern.compile("(^|\\s)#[^0-9][\\w@.]+").matcher(r9);
        boolean r2 = false;
    L6:
        if (r0.find() == false) goto L20;
        int r22 = r0.start();
        int r3 = r0.end();
        if (r9.charAt(r22) != '@') goto L10;
    L12:
        String r23 = r9.subSequence(r22, r3).toString();
        if (this.hashtagsByText != null) goto L15;
        this.hashtagsByText = new HashMap();
        this.hashtags = new ArrayList();
    L15:
        HashtagObject r32 = this.hashtagsByText.get(r23);
        if (r32 != null) goto L18;
        r32 = new HashtagObject();
        r32.hashtag = r23;
        this.hashtagsByText.put(r23, r32);
    L19:
        r32.date = (int) (System.currentTimeMillis() / 1000);
        this.hashtags.add(0, r32);
        r2 = true;
        goto L6
    L18:
        this.hashtags.remove(r32);
        goto L19
    L10:
        if (r9.charAt(r22) == '#') goto L12;
        r22 = r22 + 1;
        goto L12
    L20:
        if (r2 == false) goto L26;
        putRecentHashtags(this.hashtags);
        return;
    L26:
        return;
    }

    public void clear() {
        this.globalSearch.clear();
        this.globalSearchMap.clear();
        this.localServerSearch.clear();
    }

    public void clearRecentHashtags() {
        this.hashtags = new ArrayList();
        this.hashtagsByText = new HashMap();
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new SearchAdapterHelper$$ExternalSyntheticLambda6(this));
    }

    public boolean filter(TLObject r1) {
        return true;
    }

    public ArrayList<TLObject> getGlobalSearch() {
        return this.globalSearch;
    }

    public ArrayList<TLObject> getGroupSearch() {
        return this.groupSearch;
    }

    public ArrayList<HashtagObject> getHashtags() {
        return this.hashtags;
    }

    public String getLastFoundChannel() {
        return this.lastFoundChannel;
    }

    public String getLastFoundUsername() {
        return this.lastFoundUsername;
    }

    public ArrayList<TLObject> getLocalServerSearch() {
        return this.localServerSearch;
    }

    public ArrayList<Object> getPhoneSearch() {
        return this.phonesSearch;
    }

    public boolean isSearchInProgress() {
        if (this.pendingRequestIds.size() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean loadRecentHashtags() {
        if (this.hashtagsLoadedFromDb == false) goto L6;
        return true;
    L6:
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new SearchAdapterHelper$$ExternalSyntheticLambda5(this));
        return false;
    }

    public void mergeExcludeResults() {
        SearchAdapterHelperDelegate r0 = this.delegate;
        if (r0 == null) goto L28;
        LongSparseArray<TLRPC.User> r02 = r0.getExcludeUsers();
        int r1 = 0;
        if (r02 == null) goto L13;
        int r2 = r02.size();
        int r3 = 0;
    L8:
        if (r3 >= r2) goto L13;
        TLRPC.User r4 = (TLRPC.User) this.globalSearchMap.get(r02.keyAt(r3));
        if (r4 == null) goto L12;
        this.globalSearch.remove(r4);
        this.localServerSearch.remove(r4);
        this.globalSearchMap.remove(r4.id);
    L12:
        r3 = r3 + 1;
    L13:
        LongSparseArray<TLRPC.GroupCallParticipant> r03 = this.delegate.getExcludeCallParticipants();
        if (r03 == null) goto L21;
        int r22 = r03.size();
    L16:
        if (r1 >= r22) goto L29;
        TLRPC.User r32 = (TLRPC.User) this.globalSearchMap.get(r03.keyAt(r1));
        if (r32 == null) goto L20;
        this.globalSearch.remove(r32);
        this.localServerSearch.remove(r32);
        this.globalSearchMap.remove(r32.id);
    L20:
        r1 = r1 + 1;
        goto L16
    L29:
        return;
    L21:
        return;
    }

    public void mergeResults(ArrayList<Object> r2) {
        mergeResults(r2, null);
    }

    public void mergeResults(ArrayList<Object> r9, ArrayList<DialogsSearchAdapter.RecentSearchObject> r10) {
        this.localSearchResults = r9;
        this.localRecentResults = r10;
        if (this.globalSearchMap.size() == 0) goto L42;
        if (r9 != null) goto L7;
        if (r10 != null) goto L7;
        return;
    L7:
        int r0 = 0;
        if (r9 != null) goto L10;
        int r1 = 0;
    L11:
        if (r10 != null) goto L13;
        int r2 = 0;
    L14:
        int r22 = r2 + r1;
    L15:
        if (r0 >= r22) goto L50;
        if (r0 >= r1) goto L18;
        Object r3 = r9.get(r0);
    L20:
        if ((r3 instanceof DialogsSearchAdapter.RecentSearchObject) == false) goto L23;
        r3 = ((DialogsSearchAdapter.RecentSearchObject) r3).object;
    L23:
        if ((r3 instanceof ShareAlert.DialogSearchResult) == false) goto L26;
        r3 = ((ShareAlert.DialogSearchResult) r3).object;
    L26:
        if ((r3 instanceof TLRPC.User) == false) goto L37;
        TLRPC.User r32 = (TLRPC.User) r3;
        TLRPC.User r4 = (TLRPC.User) this.globalSearchMap.get(r32.id);
        if (r4 == null) goto L30;
        this.globalSearch.remove(r4);
        this.localServerSearch.remove(r4);
        this.globalSearchMap.remove(r4.id);
    L30:
        TLObject r42 = this.groupSearchMap.get(r32.id);
        if (r42 == null) goto L33;
        this.groupSearch.remove(r42);
        this.groupSearchMap.remove(r32.id);
    L33:
        TLObject r43 = this.phoneSearchMap.get(r32.id);
        if (r43 == null) goto L41;
        this.phonesSearch.remove(r43);
        this.phoneSearchMap.remove(r32.id);
    L41:
        r0 = r0 + 1;
        goto L15
    L37:
        if ((r3 instanceof TLRPC.Chat) == false) goto L41;
        TLRPC.Chat r33 = (TLRPC.Chat) this.globalSearchMap.get(-((TLRPC.Chat) r3).id);
        if (r33 == null) goto L41;
        this.globalSearch.remove(r33);
        this.localServerSearch.remove(r33);
        this.globalSearchMap.remove(-r33.id);
        goto L41
    L18:
        r3 = r10.get(r0 - r1);
        goto L20
    L50:
        return;
    L13:
        r2 = r10.size();
        goto L14
    L10:
        r1 = r9.size();
        goto L11
    }

    public void queryServerSearch(String r16, boolean r17, boolean r18, boolean r19, boolean r20, boolean r21, long r22, boolean r24, int r25, int r26) {
        queryServerSearch(r16, r17, r18, r19, r20, r21, r22, r24, r25, r26, 0, null);
    }

    public void queryServerSearch(String r16, boolean r17, boolean r18, boolean r19, boolean r20, boolean r21, long r22, boolean r24, int r25, int r26, long r27) {
        queryServerSearch(r16, r17, r18, r19, r20, r21, r22, r24, r25, r26, r27, null);
    }

    public void queryServerSearch(String r17, boolean r18, boolean r19, boolean r20, boolean r21, boolean r22, long r23, boolean r25, int r26, int r27, long r28, Runnable r30) {
        ArrayList<Integer> r5 = this.pendingRequestIds;
        int r6 = r5.size();
        int r10 = 0;
        int r7 = 0;
    L4:
        if (r7 >= r6) goto L6;
        Integer r8 = r5.get(r7);
        r7 = r7 + 1;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r8.intValue(), true);
        goto L4
    L6:
        this.pendingRequestIds.clear();
        if (r17 != null) goto L10;
        this.groupSearch.clear();
        this.groupSearchMap.clear();
        this.globalSearch.clear();
        this.globalSearchMap.clear();
        this.localServerSearch.clear();
        this.phonesSearch.clear();
        this.phoneSearchMap.clear();
        this.delegate.onDataSetChanged(r27);
        return;
    L10:
        ArrayList r12 = new ArrayList();
        if (r17.length() > 0) goto L13;
        boolean r62 = r21;
        this.groupSearch.clear();
        this.groupSearchMap.clear();
        boolean r14 = true;
    L26:
        if (r18 == true) goto L28;
    L31:
        if (r22 == true) goto L56;
        if (r25 == false) goto L56;
        if (r17.startsWith("+") == false) goto L56;
        if (r17.length() <= 3) goto L56;
        this.phonesSearch.clear();
        this.phoneSearchMap.clear();
        String r0 = PhoneFormat.stripExceptNumbers(r17);
        ArrayList<TLRPC.TL_contact> r2 = ContactsController.getInstance(this.currentAccount).contacts;
        int r3 = r2.size();
        int r4 = 0;
        boolean r52 = false;
    L38:
        if (r4 >= r3) goto L53;
        TLRPC.User r63 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r2.get(r4).user_id));
        if (r63 == null) goto L52;
        String r72 = r63.phone;
        if (r72 == null) goto L52;
        if (r72.startsWith(r0) == false) goto L52;
        if (r52 == false) goto L48;
    L51:
        this.phonesSearch.add(r63);
        this.phoneSearchMap.put(r63.id, r63);
        goto L52
    L48:
        if (r63.phone.length() != r0.length()) goto L50;
        r52 = true;
        goto L51
    L50:
        r52 = false;
    L52:
        r4 = r4 + 1;
        goto L38
    L53:
        if (r52 == true) goto L55;
        this.phonesSearch.add("section");
        this.phonesSearch.add(r0);
    L55:
        r14 = false;
    L56:
        AtomicInteger r02 = new AtomicInteger(0);
        ArrayList r24 = new ArrayList();
    L58:
        if (r10 >= r12.size()) goto L61;
        TLObject r32 = (TLObject) ((Pair) r12.get(r10)).first;
        r24.add(null);
        AtomicInteger r42 = new AtomicInteger();
        AtomicInteger r222 = r02;
        ArrayList r192 = r24;
        ArrayList r232 = r12;
        r42.set(ConnectionsManager.getInstance(this.currentAccount).sendRequest(r32, new SearchAdapterHelper$$ExternalSyntheticLambda2(this, r192, r10, r42, r222, r232, r27, r30)));
        this.pendingRequestIds.add(Integer.valueOf(r42.get()));
        r10 = r10 + 1;
        r12 = r232;
        r02 = r222;
        r24 = r192;
        goto L58
    L61:
        if (r14 == false) goto L71;
        this.delegate.onDataSetChanged(r27);
        return;
    L71:
        return;
    L28:
        if (r17.length() <= 0) goto L30;
        TLRPC.TL_contacts_search r15 = new TLRPC.TL_contacts_search();
        r15.q = r17;
        r15.limit = 20;
        r12.add(new Pair(r15, new SearchAdapterHelper$$ExternalSyntheticLambda1(this, r27, r19, r22, r20, r62, r28, r17)));
        goto L31
    L30:
        this.globalSearch.clear();
        this.globalSearchMap.clear();
        this.localServerSearch.clear();
        r14 = false;
        goto L31
    L13:
        if (r23 == 0) goto L23;
        TLRPC.TL_channels_getParticipants r53 = new TLRPC.TL_channels_getParticipants();
        if (r26 != 1) goto L17;
        r53.filter = new TLRPC.TL_channelParticipantsAdmins();
    L22:
        r53.filter.q = r17;
        r53.limit = 50;
        r53.offset = 0;
        r53.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(r23);
        r62 = r21;
        r12.add(new Pair(r53, new SearchAdapterHelper$$ExternalSyntheticLambda0(this, r17, r62)));
    L24:
        r14 = false;
        goto L26
    L17:
        if (r26 != 3) goto L19;
        r53.filter = new TLRPC.TL_channelParticipantsBanned();
        goto L22
    L19:
        if (r26 != 0) goto L21;
        r53.filter = new TLRPC.TL_channelParticipantsKicked();
        goto L22
    L21:
        r53.filter = new TLRPC.TL_channelParticipantsSearch();
        goto L22
    L23:
        r62 = r21;
        this.lastFoundChannel = r17.toLowerCase();
        goto L24
    }

    public void removeUserId(long r3) {
        TLObject r0 = this.globalSearchMap.get(r3);
        if (r0 == null) goto L5;
        this.globalSearch.remove(r0);
    L5:
        TLObject r32 = this.groupSearchMap.get(r3);
        if (r32 == null) goto L9;
        this.groupSearch.remove(r32);
        return;
    }

    public void setAllowGlobalResults(boolean r1) {
        this.allowGlobalResults = r1;
    }

    public void setDelegate(SearchAdapterHelperDelegate r1) {
        this.delegate = r1;
    }

    public void setHashtags(ArrayList<HashtagObject> r2, HashMap<String, HashtagObject> r3) {
        this.hashtags = r2;
        this.hashtagsByText = r3;
        this.hashtagsLoadedFromDb = true;
        this.delegate.onSetHashtags(r2, r3);
    }

    public void unloadRecentHashtags() {
        this.hashtagsLoadedFromDb = false;
    }
}
