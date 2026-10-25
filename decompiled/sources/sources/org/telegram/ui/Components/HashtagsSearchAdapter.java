package org.telegram.ui.Components;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.MessagesSearchAdapter;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes3.dex */
public abstract class HashtagsSearchAdapter extends UniversalAdapter {
    private final boolean[] cashtag;
    private final int currentAccount;
    private boolean endReached;
    private boolean hadStories;
    public boolean hasList;
    private String hashtagQuery;
    private String lastQuery;
    private int lastRate;
    public StoriesController.SearchStoriesList list;
    protected boolean loading;
    private final ArrayList<MessageObject> messages;
    private int reqId;
    private int searchId;
    private Runnable searchRunnable;
    private int totalCount;

    /* renamed from: $r8$lambda$98xqcYTRI2Y-FYXs3k4yy1I3DrU, reason: not valid java name */
    public static /* synthetic */ void m10903$r8$lambda$98xqcYTRI2YFYXs3k4yy1I3DrU(HashtagsSearchAdapter r0, int r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$search$2(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$PVDO3tpJVqGwdgXHTMKADVThEGw(HashtagsSearchAdapter r0) {
        r0.lambda$fillItems$0();
    }

    public static /* synthetic */ void $r8$lambda$f1zbmLHPO2HhSYgR1oWuQpF3bQA(HashtagsSearchAdapter r0, int r1, TLObject r2, String r3) {
        r0.lambda$search$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$gJu9Qn4P5lfECmK7dmjo7KALLi0(HashtagsSearchAdapter r0, int r1, String r2) {
        r0.lambda$search$3(r1, r2);
    }

    public HashtagsSearchAdapter(RecyclerListView r8, Context r9, int r10, int r11, Theme.ResourcesProvider r12) {
        super(r8, r9, r10, 0, null, r12);
        this.messages = new ArrayList();
        this.searchId = 0;
        this.reqId = -1;
        this.cashtag = new boolean[1];
        this.fillItems = new HashtagsSearchAdapter$$ExternalSyntheticLambda0(this);
        this.currentAccount = r10;
    }

    private /* synthetic */ void lambda$fillItems$0() {
        scrollToTop(true);
    }

    private /* synthetic */ void lambda$search$1(int r7, TLObject r8, String r9) {
        if (r7 != this.searchId) goto L29;
        boolean r72 = this.messages.isEmpty();
        this.loading = false;
        if ((r8 instanceof TLRPC.messages_Messages) == false) goto L22;
        TLRPC.messages_Messages r82 = (TLRPC.messages_Messages) r8;
        if ((r82 instanceof TLRPC.TL_messages_messages) == false) goto L11;
        this.totalCount = ((TLRPC.TL_messages_messages) r82).messages.size();
    L13:
        this.lastRate = r82.next_rate;
        MessagesController.getInstance(this.currentAccount).putUsers(r82.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r82.chats, false);
        int r1 = 0;
    L15:
        if (r1 >= r82.messages.size()) goto L18;
        MessageObject r4 = new MessageObject(this.currentAccount, r82.messages.get(r1), false, true);
        r4.setQuery(r9);
        this.messages.add(r4);
        r1 = r1 + 1;
        goto L15
    L18:
        if (this.messages.size() < this.totalCount) goto L20;
        boolean r83 = true;
    L21:
        this.endReached = r83;
        checkBottom();
    L23:
        update(true);
        if (r72 == false) goto L28;
        scrollToTop(false);
        return;
    L28:
        return;
    L20:
        r83 = false;
        goto L21
    L11:
        if ((r82 instanceof TLRPC.TL_messages_messagesSlice) == false) goto L13;
        this.totalCount = ((TLRPC.TL_messages_messagesSlice) r82).count;
        goto L13
    L22:
        this.endReached = true;
        this.totalCount = this.messages.size();
        goto L23
    }

    private /* synthetic */ void lambda$search$2(int r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new HashtagsSearchAdapter$$ExternalSyntheticLambda4(this, r1, r3, r2));
    }

    private /* synthetic */ void lambda$search$3(int r5, String r6) {
        if (r5 == this.searchId) goto L5;
        return;
    L5:
        StringBuilder r0 = new StringBuilder();
        if (this.cashtag[0] == false) goto L8;
        String r1 = "$";
    L9:
        r0.append(r1);
        r0.append(this.hashtagQuery);
        String r02 = r0.toString();
        StoriesController.SearchStoriesList r12 = this.list;
        if (r12 != null) goto L12;
    L13:
        this.list = new StoriesController.SearchStoriesList(this.currentAccount, null, r02);
    L15:
        if (this.list.getLoadedCount() > 0) goto L17;
        this.list.load(true, 4);
    L17:
        this.hasList = true;
        TLRPC.TL_channels_searchPosts r13 = new TLRPC.TL_channels_searchPosts();
        r13.flags |= 1;
        this.hashtagQuery = r6;
        r13.hashtag = r6;
        r13.limit = 10;
        if (this.messages.isEmpty() == true) goto L20;
        ArrayList<MessageObject> r62 = this.messages;
        MessageObject r63 = r62.get(r62.size() - 1);
        r13.offset_rate = this.lastRate;
        r13.offset_peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r63.messageOwner.peer_id);
    L21:
        this.reqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r13, new HashtagsSearchAdapter$$ExternalSyntheticLambda3(this, r5, r02));
        return;
    L20:
        r13.offset_peer = new TLRPC.TL_inputPeerEmpty();
        goto L21
    L12:
        if (TextUtils.equals(r12.query, r02) == true) goto L15;
    L8:
        r1 = "#";
        goto L9
    }

    public void cancel() {
        StoriesController.SearchStoriesList r0 = this.list;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.hasList = false;
        if (this.reqId < 0) goto L8;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
        this.reqId = -1;
    L8:
        AndroidUtilities.cancelRunOnUIThread(this.searchRunnable);
        this.searchId++;
        this.loading = false;
    }

    public void checkBottom() {
        if (TextUtils.isEmpty(this.lastQuery) == false) goto L5;
        return;
    L5:
        if (this.endReached == false) goto L7;
        return;
    L7:
        if (this.loading == false) goto L9;
        return;
    L9:
        if (seesLoading() == false) goto L15;
        search(this.lastQuery);
        return;
    }

    public void fillItems(ArrayList<UItem> r5, UniversalAdapter r6) {
        int r0 = 0;
        if (this.hasList == false) goto L9;
        StoriesController.SearchStoriesList r62 = this.list;
        if (r62 == null) goto L9;
        if (r62.getLoadedCount() <= 0) goto L9;
        boolean r63 = true;
    L10:
        if (r63 == false) goto L12;
        r5.add(MessagesSearchAdapter.StoriesView.Factory.asStoriesList(this.list));
    L12:
        this.hadStories = r63;
    L14:
        if (r0 >= this.messages.size()) goto L17;
        int r2 = r0 + 1;
        r5.add(UItem.asSearchMessage(r2, this.messages.get(r0)));
        r0 = r2;
        goto L14
    L17:
        if (this.loading == false) goto L19;
    L20:
        r5.add(UItem.asFlicker(-2, 1));
        r5.add(UItem.asFlicker(-3, 1));
        r5.add(UItem.asFlicker(-4, 1));
    L22:
        if (this.hadStories == true) goto L27;
        if (r63 == false) goto L28;
        AndroidUtilities.runOnUIThread(new HashtagsSearchAdapter$$ExternalSyntheticLambda2(this));
        return;
    L28:
        return;
    L27:
        return;
    L19:
        if (this.endReached == true) goto L22;
    L9:
        r63 = false;
        goto L10
    }

    public String getHashtag(String r2) {
        return getHashtag(r2, null);
    }

    public String getHashtag(String r6, boolean[] r7) {
        if (r7 == null) goto L6;
        r7[0] = false;
    L6:
        if (r6 != null) goto L8;
    L29:
        return null;
    L8:
        if (r6.isEmpty() == true) goto L29;
        String r62 = r6.trim();
        if (r62.length() > 1) goto L14;
        return null;
    L14:
        if (r62.charAt(0) == '#') goto L19;
        if (r62.charAt(0) == '$') goto L19;
        return null;
    L19:
        if (r62.indexOf(64) < 0) goto L21;
        return null;
    L21:
        if (r7 == null) goto L28;
        if (r62.charAt(0) != '$') goto L25;
        boolean r0 = true;
    L26:
        r7[0] = r0;
        goto L28
    L25:
        r0 = false;
    L28:
        return r62.substring(1);
    }

    public abstract void scrollToTop(boolean r1);

    public void search(String r3) {
        this.lastQuery = r3;
        String r32 = getHashtag(r3, this.cashtag);
        if (TextUtils.equals(this.hashtagQuery, r32) == true) goto L6;
        this.messages.clear();
        this.endReached = false;
        this.totalCount = 0;
        cancel();
    L8:
        int r0 = this.searchId + 1;
        this.searchId = r0;
        if (r32 != null) goto L11;
        return;
    L11:
        this.loading = true;
        update(true);
        HashtagsSearchAdapter$$ExternalSyntheticLambda1 r1 = new HashtagsSearchAdapter$$ExternalSyntheticLambda1(this, r0, r32);
        this.searchRunnable = r1;
        AndroidUtilities.runOnUIThread(r1, 300);
        return;
    L6:
        if (this.loading == false) goto L8;
    }

    public boolean seesLoading() {
        if (this.listView != null) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= this.listView.getChildCount()) goto L13;
        if ((this.listView.getChildAt(r0) instanceof FlickerLoadingView) == true) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return true;
    L13:
        return false;
    }

    public void setInitialData(String r2, ArrayList<MessageObject> r3, int r4, int r5) {
        if (TextUtils.equals(r2, this.hashtagQuery) == false) goto L5;
        return;
    L5:
        cancel();
        this.messages.clear();
        this.messages.addAll(r3);
        this.totalCount = r5;
        if (r5 <= r3.size()) goto L8;
        boolean r32 = true;
    L9:
        this.endReached = r32;
        this.lastRate = r4;
        this.hashtagQuery = r2;
        update(true);
        return;
    L8:
        r32 = false;
        goto L9
    }
}
