package org.telegram.ui.Components;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public abstract class DialogsChannelsAdapter extends UniversalAdapter {
    private int allCount;
    private final Context context;
    private final int currentAccount;
    public boolean expandedMyChannels;
    public boolean expandedSearchChannels;
    private final int folderId;
    private boolean hasMore;
    public boolean loadingChannels;
    public boolean loadingMessages;
    public final ArrayList<MessageObject> messages;
    public final ArrayList<TLRPC.Chat> myChannels;
    private int nextRate;
    public String query;
    private final Theme.ResourcesProvider resourcesProvider;
    public final ArrayList<TLRPC.Chat> searchChannels;
    private int searchChannelsId;
    private final Runnable searchMessagesRunnable;
    public final ArrayList<TLRPC.Chat> searchMyChannels;
    public final ArrayList<TLRPC.Chat> searchRecommendedChannels;

    public static /* synthetic */ void $r8$lambda$0G0haSjFAol8ostpHiorw_k91rM(DialogsChannelsAdapter r0, TLRPC.TL_contacts_search r1, TLRPC.TL_contacts_found r2, TLRPC.TL_error r3) {
        r0.lambda$searchMessages$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$0MSiIE4gprn9orq0q_QzeuyjwfQ(DialogsChannelsAdapter r0, int r1, TLRPC.TL_messages_searchGlobal r2, boolean r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$searchMessages$1(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$7iGsB9e5ZYn91cbZXeLVu3Y-na4, reason: not valid java name */
    public static /* synthetic */ void m10124$r8$lambda$7iGsB9e5ZYn91cbZXeLVu3Yna4(DialogsChannelsAdapter r0) {
        r0.lambda$new$4();
    }

    public static /* synthetic */ void $r8$lambda$92KG1osQfrxOKHTtJh7vi8lNL48(DialogsChannelsAdapter r0, int r1, TLRPC.TL_messages_searchGlobal r2, boolean r3) {
        r0.lambda$searchMessages$2(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Qz2l3Q0X0nra50sksANFA-X4j1A, reason: not valid java name */
    public static /* synthetic */ void m10125$r8$lambda$Qz2l3Q0X0nra50sksANFAX4j1A(DialogsChannelsAdapter r0, int r1, TLRPC.TL_messages_searchGlobal r2, boolean r3, TLObject r4) {
        r0.lambda$searchMessages$0(r1, r2, r3, r4);
    }

    public DialogsChannelsAdapter(RecyclerListView r8, Context r9, int r10, int r11, Theme.ResourcesProvider r12) {
        super(r8, r9, r10, 0, null, r12);
        this.messages = new ArrayList();
        this.searchMyChannels = new ArrayList();
        this.searchRecommendedChannels = new ArrayList();
        this.searchChannels = new ArrayList();
        this.myChannels = new ArrayList();
        this.searchMessagesRunnable = new DialogsChannelsAdapter$$ExternalSyntheticLambda2(this);
        this.fillItems = new DialogsChannelsAdapter$$ExternalSyntheticLambda3(this);
        this.context = r9;
        this.currentAccount = r10;
        this.folderId = r11;
        this.resourcesProvider = r12;
        update(false);
    }

    private /* synthetic */ void lambda$new$4() {
        searchMessages(false);
    }

    private /* synthetic */ void lambda$searchMessages$0(int r6, TLRPC.TL_messages_searchGlobal r7, boolean r8, TLObject r9) {
        if (r6 == this.searchChannelsId) goto L5;
        return;
    L5:
        if (TextUtils.equals(r7.q, this.query) == false) goto L20;
        this.loadingMessages = false;
        if (r8 == true) goto L11;
        this.messages.clear();
    L11:
        if ((r9 instanceof TLRPC.messages_Messages) == false) goto L16;
        TLRPC.messages_Messages r92 = (TLRPC.messages_Messages) r9;
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r92.users, r92.chats, true, true);
        MessagesController.getInstance(this.currentAccount).putUsers(r92.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r92.chats, false);
        ArrayList<TLRPC.Message> r72 = r92.messages;
        int r0 = r72.size();
        int r1 = 0;
    L13:
        if (r1 >= r0) goto L15;
        TLRPC.Message r2 = r72.get(r1);
        r1 = r1 + 1;
        MessageObject r3 = new MessageObject(this.currentAccount, r2, false, true);
        r3.setQuery(this.query);
        this.messages.add(r3);
        goto L13
    L15:
        this.hasMore = r92 instanceof TLRPC.TL_messages_messagesSlice;
        this.allCount = Math.max(this.messages.size(), r92.count);
        this.nextRate = r92.next_rate;
    L16:
        update(true);
        return;
    }

    private /* synthetic */ void lambda$searchMessages$1(int r1, TLRPC.TL_messages_searchGlobal r2, boolean r3, TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.runOnUIThread(new DialogsChannelsAdapter$$ExternalSyntheticLambda7(this, r1, r2, r3, r4));
    }

    private /* synthetic */ void lambda$searchMessages$2(int r3, TLRPC.TL_messages_searchGlobal r4, boolean r5) {
        if (r3 == this.searchChannelsId) goto L5;
        return;
    L5:
        if (TextUtils.equals(r4.q, this.query) == false) goto L10;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r4, new DialogsChannelsAdapter$$ExternalSyntheticLambda6(this, r3, r4, r5));
        return;
    }

    private /* synthetic */ void lambda$searchMessages$3(TLRPC.TL_contacts_search r12, TLRPC.TL_contacts_found r13, TLRPC.TL_error r14) {
        if (TextUtils.equals(r12.q, this.query) == true) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(this.query) == true) goto L118;
        int r122 = 0;
        this.loadingChannels = false;
        if (r13 == null) goto L10;
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r13.users, r13.chats, true, true);
        MessagesController.getInstance(this.currentAccount).putUsers(r13.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r13.chats, false);
    L11:
        HashSet r0 = new HashSet();
        this.searchMyChannels.clear();
        if (r13 == null) goto L28;
        ArrayList<TLRPC.Peer> r1 = r13.my_results;
        int r2 = r1.size();
        int r3 = 0;
    L14:
        if (r3 >= r2) goto L28;
        TLRPC.Peer r4 = r1.get(r3);
        r3 = r3 + 1;
        TLRPC.Peer r42 = r4;
        if ((r42 instanceof TLRPC.TL_peerChannel) == false) goto L14;
        TLRPC.Chat r43 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r42.channel_id));
        if (r43 == null) goto L14;
        if (ChatObject.isChannelAndNotMegaGroup(r43) == false) goto L14;
        if (r0.contains(Long.valueOf(r43.id)) == true) goto L14;
        r0.add(Long.valueOf(r43.id));
        this.searchMyChannels.add(r43);
    L28:
        this.searchRecommendedChannels.clear();
        String r15 = this.query.toLowerCase();
        String r22 = AndroidUtilities.translitSafe(r15);
        MessagesController.ChannelRecommendations r32 = MessagesController.getInstance(this.currentAccount).getCachedChannelRecommendations(0);
        if (r32 != null) goto L31;
    L57:
        this.searchChannels.clear();
        if (r13 == null) goto L74;
        ArrayList<TLRPC.Peer> r132 = r13.results;
        int r16 = r132.size();
    L60:
        if (r122 >= r16) goto L74;
        TLRPC.Peer r23 = r132.get(r122);
        r122 = r122 + 1;
        TLRPC.Peer r24 = r23;
        if ((r24 instanceof TLRPC.TL_peerChannel) == false) goto L60;
        TLRPC.Chat r25 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r24.channel_id));
        if (r25 == null) goto L60;
        if (ChatObject.isChannelAndNotMegaGroup(r25) == false) goto L60;
        if (r0.contains(Long.valueOf(r25.id)) == true) goto L60;
        r0.add(Long.valueOf(r25.id));
        this.searchChannels.add(r25);
    L74:
        update(true);
        return;
    L31:
        if (r32.chats.isEmpty() == true) goto L57;
        ArrayList<TLObject> r33 = r32.chats;
        int r44 = r33.size();
        int r5 = 0;
    L33:
        if (r5 >= r44) goto L57;
        TLObject r6 = r33.get(r5);
        r5 = r5 + 1;
        TLObject r62 = r6;
        if ((r62 instanceof TLRPC.Chat) == false) goto L33;
        TLRPC.Chat r63 = (TLRPC.Chat) r62;
        if (ChatObject.isChannelAndNotMegaGroup(r63) == false) goto L33;
        TLRPC.Chat r7 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r63.id));
        if (ChatObject.isNotInChat(r63) == false) goto L33;
        if (r7 == null) goto L45;
        if (ChatObject.isNotInChat(r7) == false) goto L33;
    L45:
        String r72 = r63.title.toLowerCase();
        String r8 = AndroidUtilities.translitSafe(r72);
        if (r72.startsWith(r15) == true) goto L54;
        if (r72.contains(" " + r15) == true) goto L54;
        if (r8.startsWith(r22) == true) goto L54;
        if (r8.contains(" " + r22) == false) goto L33;
    L54:
        if (r0.contains(Long.valueOf(r63.id)) == true) goto L33;
        r0.add(Long.valueOf(r63.id));
        this.searchRecommendedChannels.add(r63);
        goto L33
    L10:
        r13 = null;
        goto L11
    }

    private void searchMessages(boolean r6) {
        this.loadingMessages = true;
        int r1 = this.searchChannelsId + 1;
        this.searchChannelsId = r1;
        TLRPC.TL_messages_searchGlobal r2 = new TLRPC.TL_messages_searchGlobal();
        r2.broadcasts_only = true;
        int r3 = this.folderId;
        if (r3 == 0) goto L5;
        r2.flags |= 1;
        r2.folder_id = r3;
    L5:
        r2.q = this.query;
        r2.limit = 25;
        r2.filter = new TLRPC.TL_inputMessagesFilterEmpty();
        if (r6 == true) goto L8;
    L13:
        r2.offset_rate = 0;
        r2.offset_id = 0;
        r2.offset_peer = new TLRPC.TL_inputPeerEmpty();
    L14:
        DialogsChannelsAdapter$$ExternalSyntheticLambda0 r32 = new DialogsChannelsAdapter$$ExternalSyntheticLambda0(this, r1, r2, r6);
        if (r6 == false) goto L17;
        long r12 = 800;
    L18:
        AndroidUtilities.runOnUIThread(r32, r12);
        if (r6 == true) goto L22;
        this.loadingChannels = true;
        TLRPC.TL_contacts_search r62 = new TLRPC.TL_contacts_search();
        r62.limit = 20;
        r62.broadcasts = true;
        r62.q = this.query;
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r62, new AiTonesController$$ExternalSyntheticLambda0(), new DialogsChannelsAdapter$$ExternalSyntheticLambda1(this, r62));
        return;
    L22:
        return;
    L17:
        r12 = 0;
        goto L18
    L8:
        if (this.messages.isEmpty() == true) goto L13;
        ArrayList<MessageObject> r33 = this.messages;
        MessageObject r34 = r33.get(r33.size() - 1);
        r2.offset_rate = this.nextRate;
        r2.offset_id = r34.getId();
        if (r34.messageOwner.peer_id != null) goto L12;
        r2.offset_peer = new TLRPC.TL_inputPeerEmpty();
        goto L14
    L12:
        r2.offset_peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r34.messageOwner.peer_id);
        goto L14
    }

    public void checkBottom() {
        if (this.hasMore == true) goto L5;
        return;
    L5:
        if (this.loadingMessages == false) goto L7;
        return;
    L7:
        if (TextUtils.isEmpty(this.query) == false) goto L9;
        return;
    L9:
        if (this.listView != null) goto L12;
        return;
    L12:
        if (seesLoading() == false) goto L18;
        searchMore();
        return;
    }

    public void fillItems(ArrayList<UItem> r11, UniversalAdapter r12) {
        int r1 = 0;
        if (TextUtils.isEmpty(this.query) == false) goto L42;
        ArrayList<TLRPC.Chat> r122 = this.myChannels;
        if (r122 != null) goto L7;
    L22:
        MessagesController.ChannelRecommendations r123 = MessagesController.getInstance(this.currentAccount).getCachedChannelRecommendations(0);
        if (r123 == null) goto L40;
        ArrayList r0 = new ArrayList();
        ArrayList<TLObject> r124 = r123.chats;
        int r2 = r124.size();
        int r3 = 0;
    L25:
        if (r3 >= r2) goto L35;
        TLObject r4 = r124.get(r3);
        r3 = r3 + 1;
        TLObject r42 = r4;
        if ((r42 instanceof TLRPC.Chat) == false) goto L25;
        TLRPC.Chat r43 = (TLRPC.Chat) r42;
        TLRPC.Chat r5 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r43.id));
        if (ChatObject.isNotInChat(r43) == false) goto L25;
        if (r5 == null) goto L33;
        if (ChatObject.isNotInChat(r5) == false) goto L25;
    L33:
        r0.add(r43);
        goto L25
    L35:
        if (r0.isEmpty() == true) goto L37;
        r11.add(UItem.asGraySection(LocaleController.getString(R.string.SearchRecommendedChannels)));
    L37:
        int r10 = r0.size();
    L38:
        if (r1 >= r10) goto L135;
        Object r125 = r0.get(r1);
        r1 = r1 + 1;
        r11.add(UItem.asProfileCell((TLRPC.Chat) r125));
        goto L38
    L135:
        return;
    L40:
        r11.add(UItem.asFlicker(30));
        r11.add(UItem.asFlicker(29));
        r11.add(UItem.asFlicker(29));
        r11.add(UItem.asFlicker(29));
        r11.add(UItem.asFlicker(29));
        return;
    L7:
        if (r122.isEmpty() == true) goto L22;
        if (this.myChannels.size() <= 5) goto L15;
        String r126 = LocaleController.getString(R.string.SearchMyChannels);
        if (this.expandedMyChannels == false) goto L13;
        int r32 = R.string.ShowLess;
    L14:
        r11.add(UItem.asGraySection(r126, LocaleController.getString(r32), new DialogsChannelsAdapter$$ExternalSyntheticLambda4(this)));
    L16:
        int r127 = this.myChannels.size();
        if (this.expandedMyChannels == true) goto L19;
        r127 = Math.min(5, r127);
    L19:
        int r02 = 0;
    L20:
        if (r02 >= r127) goto L22;
        r11.add(UItem.asProfileCell(this.myChannels.get(r02)).withUsername(true));
        r02 = r02 + 1;
        goto L20
    L13:
        r32 = R.string.ShowMore;
        goto L14
    L15:
        r11.add(UItem.asGraySection(LocaleController.getString(R.string.SearchMyChannels)));
        goto L16
    L42:
        ArrayList r128 = new ArrayList();
        ArrayList<TLRPC.Chat> r33 = this.searchMyChannels;
        int r44 = r33.size();
        int r52 = 0;
    L43:
        if (r52 >= r44) goto L50;
        TLRPC.Chat r6 = r33.get(r52);
        r52 = r52 + 1;
        TLRPC.Chat r62 = r6;
        TLRPC.Chat r7 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r62.id));
        if (ChatObject.isNotInChat(r62) == false) goto L43;
        if (r7 == null) goto L49;
        if (ChatObject.isNotInChat(r7) == false) goto L43;
    L49:
        r128.add(r62);
        goto L43
    L50:
        ArrayList<TLRPC.Chat> r34 = this.searchRecommendedChannels;
        int r45 = r34.size();
        int r53 = 0;
    L51:
        if (r53 >= r45) goto L58;
        TLRPC.Chat r63 = r34.get(r53);
        r53 = r53 + 1;
        TLRPC.Chat r64 = r63;
        TLRPC.Chat r72 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r64.id));
        if (ChatObject.isNotInChat(r64) == false) goto L51;
        if (r72 == null) goto L57;
        if (ChatObject.isNotInChat(r72) == false) goto L51;
    L57:
        r128.add(r64);
        goto L51
    L58:
        ArrayList<TLRPC.Chat> r35 = this.searchChannels;
        int r46 = r35.size();
        int r54 = 0;
    L59:
        if (r54 >= r46) goto L67;
        TLRPC.Chat r65 = r35.get(r54);
        r54 = r54 + 1;
        TLRPC.Chat r66 = r65;
        TLRPC.Chat r73 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r66.id));
        if (ChatObject.isNotInChat(r66) == false) goto L59;
        if (r73 == null) goto L65;
        if (ChatObject.isNotInChat(r73) == false) goto L59;
    L65:
        r128.add(r66);
        goto L59
    L67:
        if (r128.isEmpty() == true) goto L87;
        if (r128.size() > 5) goto L71;
    L77:
        r11.add(UItem.asGraySection(LocaleController.getString(R.string.SearchChannels)));
    L78:
        int r36 = r128.size();
        if (this.expandedSearchChannels == false) goto L81;
    L83:
        int r03 = 0;
    L84:
        if (r03 >= r36) goto L87;
        r11.add(UItem.asProfileCell((TLObject) r128.get(r03)));
        r03 = r03 + 1;
        goto L84
    L81:
        if (this.messages.isEmpty() == true) goto L83;
        r36 = Math.min(5, r36);
        goto L83
    L71:
        if (this.messages.isEmpty() == true) goto L77;
        String r37 = LocaleController.getString(R.string.SearchChannels);
        if (this.expandedSearchChannels == false) goto L75;
        int r47 = R.string.ShowLess;
    L76:
        r11.add(UItem.asGraySection(r37, LocaleController.getString(r47), new DialogsChannelsAdapter$$ExternalSyntheticLambda5(this)));
        goto L78
    L75:
        r47 = R.string.ShowMore;
    L87:
        if (this.messages.isEmpty() == true) goto L133;
        r11.add(UItem.asGraySection(LocaleController.getString(R.string.SearchMessages)));
        ArrayList<MessageObject> r129 = this.messages;
        int r04 = r129.size();
    L89:
        if (r1 >= r04) goto L92;
        MessageObject r38 = r129.get(r1);
        r1 = r1 + 1;
        r11.add(UItem.asSearchMessage(r38));
        goto L89
    L92:
        if (this.hasMore == false) goto L134;
        r11.add(UItem.asFlicker(1));
        return;
    L134:
        return;
    }

    public TLRPC.Chat getChat(int r1) {
        UItem r0 = getItem(r1);
        if (r0 == null) goto L8;
        Object r02 = r0.object;
        if ((r02 instanceof TLRPC.Chat) == true) goto L7;
        return null;
    L7:
        return (TLRPC.Chat) r02;
    L8:
        return null;
    }

    public ArrayList<TLRPC.Chat> getNextChannels(int r3) {
        ArrayList<TLRPC.Chat> r0 = new ArrayList();
    L3:
        r3 = r3 + 1;
        if (r3 >= getItemCount()) goto L9;
        TLRPC.Chat r1 = getChat(r3);
        if (r1 == null) goto L3;
        r0.add(r1);
        goto L3
    L9:
        return r0;
    }

    public Object getObject(int r1) {
        UItem r0 = getItem(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.object;
    }

    public abstract void hideKeyboard();

    public void search(String r6) {
        updateMyChannels();
        if (TextUtils.equals(r6, this.query) == true) goto L17;
        this.query = r6;
        AndroidUtilities.cancelRunOnUIThread(this.searchMessagesRunnable);
        boolean r62 = TextUtils.isEmpty(this.query);
        ArrayList<MessageObject> r0 = this.messages;
        if (r62 == false) goto L11;
        r0.clear();
        this.searchChannels.clear();
        this.searchRecommendedChannels.clear();
        this.searchMyChannels.clear();
        update(true);
        this.searchChannelsId++;
        this.loadingMessages = false;
        this.loadingChannels = false;
        this.hasMore = false;
        this.nextRate = 0;
        RecyclerListView r5 = this.listView;
        if (r5 == null) goto L15;
        r5.scrollToPosition(0);
        return;
    L15:
        return;
    L11:
        r0.clear();
        this.searchChannels.clear();
        this.searchRecommendedChannels.clear();
        this.searchMyChannels.clear();
        AndroidUtilities.runOnUIThread(this.searchMessagesRunnable, 1000);
        this.loadingMessages = true;
        this.loadingChannels = true;
        update(true);
        RecyclerListView r52 = this.listView;
        if (r52 == null) goto L16;
        r52.scrollToPosition(0);
        return;
    L16:
        return;
    }

    public void searchMore() {
        if (this.hasMore == true) goto L5;
        return;
    L5:
        if (this.loadingMessages == false) goto L7;
        return;
    L7:
        if (TextUtils.isEmpty(this.query) == true) goto L13;
        searchMessages(true);
        return;
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

    public void toggleExpandedMyChannels(View r2) {
        this.expandedMyChannels = !this.expandedMyChannels;
        update(true);
        if (this.expandedMyChannels == false) goto L6;
        hideKeyboard();
        return;
    }

    public void toggleExpandedSearchChannels(View r2) {
        this.expandedSearchChannels = !this.expandedSearchChannels;
        update(true);
        if (this.expandedSearchChannels == false) goto L6;
        hideKeyboard();
        return;
    }

    public void updateMyChannels() {
        ArrayList r0 = new ArrayList();
        ArrayList<TLRPC.Dialog> r1 = MessagesController.getInstance(this.currentAccount).getAllDialogs();
        int r2 = r1.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L15;
        TLRPC.Dialog r4 = r1.get(r3);
        r3 = r3 + 1;
        TLRPC.Chat r42 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r4.id));
        if (r42 == null) goto L3;
        if (ChatObject.isChannelAndNotMegaGroup(r42) == false) goto L3;
        if (ChatObject.isPublic(r42) == false) goto L3;
        if (ChatObject.isNotInChat(r42) == true) goto L3;
        r0.add(r42);
        if (r0.size() < 100) goto L3;
    L15:
        this.myChannels.clear();
        this.myChannels.addAll(r0);
    }
}
