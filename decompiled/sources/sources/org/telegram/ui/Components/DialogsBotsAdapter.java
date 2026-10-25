package org.telegram.ui.Components;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class DialogsBotsAdapter extends UniversalAdapter {
    private int allCount;
    private final Context context;
    private final int currentAccount;
    public boolean expandedMyBots;
    public boolean expandedSearchBots;
    private boolean first;
    private final int folderId;
    private boolean hasMore;
    private final CharSequence infoText;
    public boolean loadingBots;
    public boolean loadingMessages;
    private int nextRate;
    private final Utilities.Callback<TLRPC.User> openBotCallback;
    private final PopularBots popular;
    public String query;
    private final Theme.ResourcesProvider resourcesProvider;
    private int searchBotsId;
    public final ArrayList<TLRPC.User> searchGlobal;
    public final ArrayList<MessageObject> searchMessages;
    private Runnable searchMessagesRunnable;
    public final ArrayList<TLRPC.User> searchMine;
    private final boolean showOnlyPopular;
    private int topPeersEnd;
    private int topPeersStart;

    /* renamed from: org.telegram.ui.Components.DialogsBotsAdapter$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends ClickableSpan {
        final /* synthetic */ DialogsBotsAdapter this$0;
        final /* synthetic */ AlertDialog[] val$alert;
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$username;

        public AnonymousClass1(DialogsBotsAdapter r1, AlertDialog[] r2, Context r3, String r4) {
            this.this$0 = r1;
            this.val$alert = r2;
            this.val$context = r3;
            this.val$username = r4;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r3) {
            AlertDialog r32 = this.val$alert[0];
            if (r32 == null) goto L5;
            r32.dismiss();
        L5:
            Browser.openUrl(this.val$context, "https://t.me/" + this.val$username);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            super.updateDrawState(r1);
            r1.setUnderlineText(false);
        }
    }

    public static class PopularBots {
        public final ArrayList<TLRPC.User> bots;
        private boolean cacheLoaded;
        private long cacheTime;
        private final int currentAccount;
        private boolean endReached;
        private String lastOffset;
        public boolean loading;
        private boolean savingCache;
        private final Runnable whenUpdated;

        public static /* synthetic */ void $r8$lambda$6BCdrVKBCv9vrjoWF9ZQWpRyT38(PopularBots r0) {
            r0.lambda$saveCache$2();
        }

        public static /* synthetic */ void $r8$lambda$8S_CP7Nl2fIoqEWJFsFlmVli3LE(PopularBots r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$load$6(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$B2H46VCOp5TPBi2W1DsTciyaqoM(PopularBots r0) {
            r0.lambda$load$4();
        }

        public static /* synthetic */ void $r8$lambda$KJQaZ_Z4KoX8oFHnKSFIHXz36Vo(PopularBots r0, ArrayList r1, long r2, String r4, Runnable r5) {
            r0.lambda$loadCache$0(r1, r2, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$Kyr4AKxIgD9OqJdVdjUyJHljQys(PopularBots r0, TLObject r1) {
            r0.lambda$load$5(r1);
        }

        public static /* synthetic */ void $r8$lambda$MXbOMFvfPy5pwYQVLaREmjMkMwE(PopularBots r0, MessagesStorage r1, ArrayList r2, long r3, String r5) {
            r0.lambda$saveCache$3(r1, r2, r3, r5);
        }

        public static /* synthetic */ void $r8$lambda$Vrj6JGUooGlyUxDDVQ4crnZBl74(PopularBots r0, MessagesStorage r1, Runnable r2) {
            r0.lambda$loadCache$1(r1, r2);
        }

        /* renamed from: -$$Nest$fgetendReached, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10123$$Nest$fgetendReached(PopularBots r0) {
            return r0.endReached;
        }

        public PopularBots(int r2, Runnable r3) {
            this.bots = new ArrayList();
            this.savingCache = false;
            this.currentAccount = r2;
            this.whenUpdated = r3;
        }

        private /* synthetic */ void lambda$load$4() {
            this.loading = false;
            this.whenUpdated.run();
            if (this.bots.isEmpty() == false) goto L5;
        L8:
            this.bots.clear();
            this.endReached = false;
            this.lastOffset = null;
            load();
            return;
        L5:
            if ((System.currentTimeMillis() - this.cacheTime) > DurationKt.MILLIS_IN_HOUR) goto L8;
        }

        private /* synthetic */ void lambda$load$5(TLObject r6) {
            boolean r2 = true;
            if ((r6 instanceof TL_bots.popularAppBots) == false) goto L10;
            TL_bots.popularAppBots r62 = (TL_bots.popularAppBots) r6;
            MessagesController.getInstance(this.currentAccount).putUsers(r62.users, false);
            MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r62.users, null, false, true);
            this.bots.addAll(r62.users);
            String r63 = r62.next_offset;
            this.lastOffset = r63;
            if (r63 == null) goto L8;
            r2 = false;
        L8:
            this.endReached = r2;
            this.cacheTime = System.currentTimeMillis();
            saveCache();
            this.loading = false;
            this.whenUpdated.run();
            return;
        L10:
            this.lastOffset = null;
            this.endReached = true;
            this.loading = false;
            this.whenUpdated.run();
        }

        private /* synthetic */ void lambda$load$6(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new DialogsBotsAdapter$PopularBots$$ExternalSyntheticLambda3(this, r1));
        }

        private /* synthetic */ void lambda$loadCache$0(ArrayList r3, long r4, String r6, Runnable r7) {
            MessagesController.getInstance(this.currentAccount).putUsers(r3, true);
            this.bots.addAll(r3);
            this.cacheTime = r4;
            this.lastOffset = r6;
            this.endReached = TextUtils.isEmpty(r6);
            this.cacheLoaded = true;
            r7.run();
        }

        private /* synthetic */ void lambda$loadCache$1(MessagesStorage r18, Runnable r19) {
            ArrayList r2 = new ArrayList();
            ArrayList<Long> r0 = new ArrayList();
            long r3 = 0;
            SQLiteCursor r1 = r18.getDatabase().queryFinalized("SELECT uid, time, offset FROM popular_bots ORDER BY pos", new Object[0]);     // Catch: Throwable -> L37 Exception -> L39
            String r6 = null;
        L56:
        L9:
            th = th;
            SQLiteCursor r5 = r1;
        L47:
            if (r5 == null) goto L49;
            r5.dispose();
        L49:
            throw th;
        L29:
            e = e;
            String r16 = r6;
        L26:
            r5 = r1;
        L54:
            FileLog.e(e);     // Catch: Throwable -> L46
            if (r5 == null) goto L44;
            r5.dispose();
        L44:
            AndroidUtilities.runOnUIThread(new DialogsBotsAdapter$PopularBots$$ExternalSyntheticLambda5(this, r2, r3, r16, r19));
            return;
        L46:
            th = th;
            goto L47
        L6:
            if (r1.next() == false) goto L13;
            r0.add(Long.valueOf(r1.longValue(0)));     // Catch: Throwable -> L9 Exception -> L11
            r3 = Math.max(r3, r1.longValue(1));     // Catch: Throwable -> L9 Exception -> L11
            r6 = r1.stringValue(2);     // Catch: Throwable -> L9 Exception -> L11
        L11:
            e = e;
            r5 = r1;
            r16 = r6;
            goto L54
        L13:
            r1.dispose();     // Catch: Throwable -> L9 Exception -> L29
            ArrayList<TLRPC.User> r8 = r18.getUsers(r0);     // Catch: Throwable -> L9 Exception -> L29
            if (r8 == null) goto L35;
            int r9 = r0.size();     // Catch: Throwable -> L9 Exception -> L29
            int r10 = 0;
        L16:
            if (r10 >= r9) goto L35;
            Long r11 = r0.get(r10);     // Catch: Throwable -> L9 Exception -> L29
            r10 = r10 + 1;     // Catch: Throwable -> L9 Exception -> L29
            long r112 = r11.longValue();     // Catch: Throwable -> L9 Exception -> L29
            int r13 = r8.size();     // Catch: Throwable -> L9 Exception -> L29
            int r14 = 0;
        L18:
            if (r14 >= r13) goto L31;
            TLRPC.User r15 = r8.get(r14);     // Catch: Throwable -> L9 Exception -> L29
            r14 = r14 + 1;     // Catch: Throwable -> L9 Exception -> L29
            TLRPC.User r152 = r15;     // Catch: Throwable -> L9 Exception -> L29
            if (r152 == null) goto L27;
            r16 = r6;
            if (r152.id != r112) goto L28;
        L32:
            if (r152 == null) goto L34;
            r2.add(r152);     // Catch: Throwable -> L9 Exception -> L25
        L34:
            r6 = r16;
        L28:
            r6 = r16;
        L25:
            e = e;
            goto L26
        L27:
            r16 = r6;
            goto L28
        L31:
            r16 = r6;
            r152 = null;
            goto L32
        L35:
            r16 = r6;
            r1.dispose();
            goto L44
        L39:
            e = e;
            r5 = null;
            r16 = null;
        L37:
            th = th;
            r5 = null;
            goto L47
        }

        private /* synthetic */ void lambda$saveCache$2() {
            this.savingCache = false;
        }

        private /* synthetic */ void lambda$saveCache$3(MessagesStorage r5, ArrayList r6, long r7, String r9) {
            SQLiteDatabase r52 = r5.getDatabase();
            SQLitePreparedStatement r0 = null;
            r52.executeFast("DELETE FROM popular_bots").stepThis().dispose();     // Catch: Throwable -> L8 Exception -> L10
            r0 = r52.executeFast("REPLACE INTO popular_bots VALUES(?, ?, ?, ?)");     // Catch: Throwable -> L8 Exception -> L10
            int r53 = 0;
        L4:
            if (r53 >= r6.size()) goto L12;
            r0.requery();     // Catch: Throwable -> L8 Exception -> L10
            r0.bindLong(1, ((Long) r6.get(r53)).longValue());     // Catch: Throwable -> L8 Exception -> L10
            r0.bindLong(2, r7);     // Catch: Throwable -> L8 Exception -> L10
            r0.bindString(3, r9);     // Catch: Throwable -> L8 Exception -> L10
            r0.bindInteger(4, r53);     // Catch: Throwable -> L8 Exception -> L10
            r0.step();     // Catch: Throwable -> L8 Exception -> L10
            r53 = r53 + 1;
            goto L4
        L12:
            if (r0 == null) goto L17;
        L13:
            r0.dispose();
        L17:
            AndroidUtilities.runOnUIThread(new DialogsBotsAdapter$PopularBots$$ExternalSyntheticLambda6(this));
            return;
        L8:
            th = move-exception;
            if (r0 == null) goto L21;
            r0.dispose();
        L21:
            throw th;
        L10:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L8
            if (r0 == null) goto L17;
            goto L17
        }

        private void loadCache(Runnable r4) {
            MessagesStorage r0 = MessagesStorage.getInstance(this.currentAccount);
            r0.getStorageQueue().postRunnable(new DialogsBotsAdapter$PopularBots$$ExternalSyntheticLambda2(this, r0, r4));
        }

        private void saveCache() {
            if (this.savingCache == false) goto L5;
            return;
        L5:
            this.savingCache = true;
            long r5 = this.cacheTime;
            String r0 = this.lastOffset;
            if (r0 != null) goto L8;
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        L8:
            String r7 = r0;
            ArrayList r4 = new ArrayList();
            int r02 = 0;
        L10:
            if (r02 >= this.bots.size()) goto L12;
            r4.add(Long.valueOf(this.bots.get(r02).id));
            r02 = r02 + 1;
            goto L10
        L12:
            MessagesStorage r3 = MessagesStorage.getInstance(this.currentAccount);
            r3.getStorageQueue().postRunnable(new DialogsBotsAdapter$PopularBots$$ExternalSyntheticLambda4(this, r3, r4, r5, r7));
        }

        public void load() {
            if (this.loading == false) goto L5;
            return;
        L5:
            if (this.endReached == true) goto L17;
            this.loading = true;
            if (this.cacheLoaded == true) goto L11;
            loadCache(new DialogsBotsAdapter$PopularBots$$ExternalSyntheticLambda0(this));
            return;
        L11:
            TL_bots.getPopularAppBots r0 = new TL_bots.getPopularAppBots();
            r0.limit = 20;
            String r1 = this.lastOffset;
            if (r1 != null) goto L14;
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        L14:
            r0.offset = r1;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new DialogsBotsAdapter$PopularBots$$ExternalSyntheticLambda1(this));
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$5liQLtT_AB52T0iqZtIlt5zxAxY(DialogsBotsAdapter r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$92KQ9nLkSE3h8qPWcTC8jSNcVGU(DialogsBotsAdapter r0) {
        r0.lambda$new$7();
    }

    public static /* synthetic */ void $r8$lambda$EOGIk9CaGT_CDhBN5lcgeWcIuVc(AlertDialog[] r1) {
        AlertDialog r12 = r1[0];
        if (r12 == null) goto L6;
        r12.dismiss();
        return;
    }

    /* renamed from: $r8$lambda$KUUL8gCV03CB31hPoZ5CGso-bpo, reason: not valid java name */
    public static /* synthetic */ void m10120$r8$lambda$KUUL8gCV03CB31hPoZ5CGsobpo(DialogsBotsAdapter r0, TLRPC.TL_contacts_search r1, TLRPC.TL_contacts_found r2, TLRPC.TL_error r3) {
        r0.lambda$searchMessages$6(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$MePrqTgtDaYmhQr36shQHor0bM0(DialogsBotsAdapter r0, int r1, TLRPC.TL_messages_searchGlobal r2, boolean r3) {
        r0.lambda$searchMessages$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$SFDqSlNIoEV5hSDxft8bR3JaIyQ(DialogsBotsAdapter r0, int r1, TLRPC.TL_messages_searchGlobal r2, boolean r3, TLObject r4) {
        r0.lambda$searchMessages$3(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$VSW-W2Y7bHSvrhDfi3p5FVP5GH0, reason: not valid java name */
    public static /* synthetic */ void m10121$r8$lambda$VSWW2Y7bHSvrhDfi3p5FVP5GH0(DialogsBotsAdapter r0, View r1) {
        r0.toggleExpandedSearchBots(r1);
    }

    public static /* synthetic */ void $r8$lambda$_mdNbZ4ghTD50U_EeELTaMQ0iwA(DialogsBotsAdapter r0, int r1, TLRPC.TL_messages_searchGlobal r2, boolean r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$searchMessages$4(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$jy6uOahn78UGeg2Wh5Xfk-hGPZ4, reason: not valid java name */
    public static /* synthetic */ void m10122$r8$lambda$jy6uOahn78UGeg2Wh5XfkhGPZ4(DialogsBotsAdapter r0, Theme.ResourcesProvider r1, Context r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$p5N9ECC0KYoy6gbWJkTGcOlhmQI(DialogsBotsAdapter r0, View r1) {
        r0.toggleExpandedMyBots(r1);
    }

    public DialogsBotsAdapter(RecyclerListView r9, Context r10, int r11, int r12, boolean r13, Theme.ResourcesProvider r14) {
        super(r9, r10, r11, 0, true, null, r14);
        this.searchMine = new ArrayList();
        this.searchGlobal = new ArrayList();
        this.searchMessages = new ArrayList();
        this.searchMessagesRunnable = new DialogsBotsAdapter$$ExternalSyntheticLambda0(this);
        this.first = true;
        this.openBotCallback = new DialogsBotsAdapter$$ExternalSyntheticLambda1(this);
        this.fillItems = new DialogsBotsAdapter$$ExternalSyntheticLambda2(this);
        this.context = r10;
        this.currentAccount = r11;
        this.folderId = r12;
        this.resourcesProvider = r14;
        this.showOnlyPopular = r13;
        this.popular = new PopularBots(r11, new DialogsBotsAdapter$$ExternalSyntheticLambda3(this));
        this.infoText = AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.AppsTabInfo), new DialogsBotsAdapter$$ExternalSyntheticLambda4(this, r14, r10)), true);
        update(false);
        MediaDataController.getInstance(r11).loadHints(true);
    }

    private /* synthetic */ void lambda$new$0() {
        update(true);
    }

    private /* synthetic */ void lambda$new$2(Theme.ResourcesProvider r9, Context r10) {
        AlertDialog[] r1 = new AlertDialog[1];
        SpannableStringBuilder r2 = AndroidUtilities.replaceTags(AndroidUtilities.replaceLinks(LocaleController.getString(R.string.AppsTabInfoText), r9, new DialogsBotsAdapter$$ExternalSyntheticLambda7(r1)));
        Matcher r3 = Pattern.compile("@([a-zA-Z0-9_-]+)").matcher(r2);
    L4:
        if (r3.find() == false) goto L6;
        r2.setSpan(new AnonymousClass1(this, r1, r10, r3.group(1)), r3.start(), r3.end(), 33);
        goto L4
    L6:
        r1[0] = new AlertDialog.Builder(r10, r9).setTitle(LocaleController.getString(R.string.AppsTabInfoTitle)).setMessage(r2).setPositiveButton(LocaleController.getString(R.string.AppsTabInfoButton), null).show();
    }

    private /* synthetic */ void lambda$new$7() {
        searchMessages(false);
    }

    private /* synthetic */ void lambda$searchMessages$3(int r6, TLRPC.TL_messages_searchGlobal r7, boolean r8, TLObject r9) {
        if (r6 == this.searchBotsId) goto L5;
        return;
    L5:
        if (TextUtils.equals(r7.q, this.query) == false) goto L20;
        this.loadingMessages = false;
        if (r8 == true) goto L11;
        this.searchMessages.clear();
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
        this.searchMessages.add(r3);
        goto L13
    L15:
        this.hasMore = r92 instanceof TLRPC.TL_messages_messagesSlice;
        this.allCount = Math.max(this.searchMessages.size(), r92.count);
        this.nextRate = r92.next_rate;
    L16:
        update(true);
        return;
    }

    private /* synthetic */ void lambda$searchMessages$4(int r1, TLRPC.TL_messages_searchGlobal r2, boolean r3, TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.runOnUIThread(new DialogsBotsAdapter$$ExternalSyntheticLambda11(this, r1, r2, r3, r4));
    }

    private /* synthetic */ void lambda$searchMessages$5(int r3, TLRPC.TL_messages_searchGlobal r4, boolean r5) {
        if (r3 == this.searchBotsId) goto L5;
        return;
    L5:
        if (TextUtils.equals(r4.q, this.query) == false) goto L10;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r4, new DialogsBotsAdapter$$ExternalSyntheticLambda10(this, r3, r4, r5));
        return;
    }

    private /* synthetic */ void lambda$searchMessages$6(TLRPC.TL_contacts_search r9, TLRPC.TL_contacts_found r10, TLRPC.TL_error r11) {
        if (TextUtils.equals(r9.q, this.query) == true) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(this.query) == true) goto L71;
        this.loadingBots = false;
        if (r10 == null) goto L10;
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r10.users, r10.chats, true, true);
        MessagesController.getInstance(this.currentAccount).putUsers(r10.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r10.chats, false);
    L11:
        HashSet r0 = new HashSet();
        this.searchMine.clear();
        if (r10 == null) goto L27;
        ArrayList<TLRPC.Peer> r1 = r10.my_results;
        int r2 = r1.size();
        int r3 = 0;
    L14:
        if (r3 >= r2) goto L27;
        TLRPC.Peer r4 = r1.get(r3);
        r3 = r3 + 1;
        TLRPC.Peer r42 = r4;
        if ((r42 instanceof TLRPC.TL_peerUser) == false) goto L14;
        TLRPC.User r43 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r42.user_id));
        if (r43 == null) goto L14;
        if (r43.bot == false) goto L14;
        if (r0.contains(Long.valueOf(r43.id)) == true) goto L14;
        r0.add(Long.valueOf(r43.id));
        this.searchMine.add(r43);
    L27:
        this.searchGlobal.clear();
        if (r10 == null) goto L43;
        ArrayList<TLRPC.Peer> r102 = r10.results;
        int r12 = r102.size();
        int r22 = 0;
    L30:
        if (r22 >= r12) goto L43;
        TLRPC.Peer r32 = r102.get(r22);
        r22 = r22 + 1;
        TLRPC.Peer r33 = r32;
        if ((r33 instanceof TLRPC.TL_peerUser) == false) goto L30;
        TLRPC.User r34 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r33.user_id));
        if (r34 == null) goto L30;
        if (r34.bot == false) goto L30;
        if (r0.contains(Long.valueOf(r34.id)) == true) goto L30;
        r0.add(Long.valueOf(r34.id));
        this.searchGlobal.add(r34);
    L43:
        RecyclerListView r103 = this.listView;
        if (r103 == null) goto L46;
        r103.scrollToPosition(0);
    L46:
        update(true);
        return;
    L10:
        r10 = null;
        goto L11
    }

    private void searchMessages(boolean r7) {
        this.loadingMessages = true;
        int r1 = this.searchBotsId + 1;
        this.searchBotsId = r1;
        TLRPC.TL_messages_searchGlobal r2 = new TLRPC.TL_messages_searchGlobal();
        r2.broadcasts_only = false;
        int r4 = this.folderId;
        if (r4 == 0) goto L5;
        r2.flags |= 1;
        r2.folder_id = r4;
    L5:
        r2.q = this.query;
        r2.limit = 25;
        r2.filter = new TLRPC.TL_inputMessagesFilterEmpty();
        if (r7 == true) goto L8;
    L13:
        r2.offset_rate = 0;
        r2.offset_id = 0;
        r2.offset_peer = new TLRPC.TL_inputPeerEmpty();
    L14:
        DialogsBotsAdapter$$ExternalSyntheticLambda8 r3 = new DialogsBotsAdapter$$ExternalSyntheticLambda8(this, r1, r2, r7);
        if (r7 == false) goto L17;
        long r12 = 800;
    L18:
        AndroidUtilities.runOnUIThread(r3, r12);
        if (r7 == true) goto L22;
        this.loadingBots = true;
        TLRPC.TL_contacts_search r72 = new TLRPC.TL_contacts_search();
        r72.limit = 30;
        r72.bots = true;
        r72.q = this.query;
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r72, new AiTonesController$$ExternalSyntheticLambda0(), new DialogsBotsAdapter$$ExternalSyntheticLambda9(this, r72));
        return;
    L22:
        return;
    L17:
        r12 = 0;
        goto L18
    L8:
        if (this.searchMessages.isEmpty() == true) goto L13;
        ArrayList<MessageObject> r32 = this.searchMessages;
        MessageObject r33 = r32.get(r32.size() - 1);
        r2.offset_rate = this.nextRate;
        r2.offset_id = r33.getId();
        if (r33.messageOwner.peer_id != null) goto L12;
        r2.offset_peer = new TLRPC.TL_inputPeerEmpty();
        goto L14
    L12:
        r2.offset_peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r33.messageOwner.peer_id);
        goto L14
    }

    private void toggleExpandedMyBots(View r2) {
        this.expandedMyBots = !this.expandedMyBots;
        update(true);
    }

    private void toggleExpandedSearchBots(View r2) {
        this.expandedSearchBots = !this.expandedSearchBots;
        update(true);
    }

    public void checkBottom() {
        if (TextUtils.isEmpty(this.query) == true) goto L12;
        if (this.hasMore == true) goto L7;
    L16:
        this.first = false;
        return;
    L7:
        if (this.loadingMessages == true) goto L16;
        if (seesLoading() == false) goto L16;
        searchMore();
        goto L16
    L12:
        if (this.first == false) goto L14;
    L15:
        this.popular.load();
        goto L16
    L14:
        if (seesLoading() == false) goto L16;
        goto L15
    }

    public void fillItems(ArrayList<UItem> r10, UniversalAdapter r11) {
        HashSet r112 = new HashSet();
        int r2 = 0;
        if (TextUtils.isEmpty(this.query) == true) goto L39;
        ArrayList r113 = new ArrayList();
        r113.addAll(this.searchMine);
        r113.addAll(this.searchGlobal);
        if (r113.isEmpty() == true) goto L29;
        if (r113.size() > 5) goto L9;
    L17:
        r10.add(UItem.asGraySection(LocaleController.getString(R.string.SearchApps)));
    L18:
        int r0 = r113.size();
        if (this.expandedSearchBots == false) goto L21;
    L25:
        int r1 = 0;
    L26:
        if (r1 >= r0) goto L29;
        r10.add(UItem.asProfileCell((TLObject) r113.get(r1)).withOpenButton(this.openBotCallback));
        r1 = r1 + 1;
        goto L26
    L21:
        if (this.searchMessages.isEmpty() == true) goto L25;
        if (this.showOnlyPopular == true) goto L25;
        r0 = Math.min(5, r0);
        goto L25
    L9:
        if (this.searchMessages.isEmpty() == true) goto L17;
        if (this.showOnlyPopular == true) goto L17;
        String r02 = LocaleController.getString(R.string.SearchApps);
        if (this.expandedSearchBots == false) goto L15;
        int r4 = R.string.ShowLess;
    L16:
        r10.add(UItem.asGraySection(r02, LocaleController.getString(r4), new DialogsBotsAdapter$$ExternalSyntheticLambda5(this)));
        goto L18
    L15:
        r4 = R.string.ShowMore;
    L29:
        if (this.searchMessages.isEmpty() == false) goto L31;
        return;
    L31:
        if (this.showOnlyPopular == true) goto L119;
        r10.add(UItem.asGraySection(LocaleController.getString(R.string.SearchMessages)));
        ArrayList<MessageObject> r114 = this.searchMessages;
        int r03 = r114.size();
    L33:
        if (r2 >= r03) goto L36;
        MessageObject r12 = r114.get(r2);
        r2 = r2 + 1;
        r10.add(UItem.asSearchMessage(r12));
        goto L33
    L36:
        if (this.hasMore == false) goto L120;
        r10.add(UItem.asFlicker(1));
        return;
    L120:
        return;
    L119:
        return;
    L39:
        ArrayList<TLRPC.TL_topPeer> r04 = MediaDataController.getInstance(this.currentAccount).webapps;
        ArrayList r42 = new ArrayList();
        if (r04 == null) goto L51;
        int r5 = 0;
    L43:
        if (r5 >= r04.size()) goto L51;
        TLRPC.User r6 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(DialogObject.getPeerDialogId(r04.get(r5).peer)));
        if (r6 == null) goto L50;
        if (r6.bot == false) goto L50;
        r42.add(r6);
    L50:
        r5 = r5 + 1;
    L51:
        this.topPeersStart = r10.size();
        if (r42.isEmpty() == false) goto L54;
    L75:
        r112.clear();
        this.topPeersEnd = r10.size();
        if (this.popular.bots.isEmpty() == false) goto L78;
        PopularBots r115 = this.popular;
        if (r115.loading == true) goto L99;
        if (PopularBots.m10123$$Nest$fgetendReached(r115) == false) goto L99;
    L102:
        if (r2 == 0) goto L121;
        r10.add(UItem.asShadow(this.infoText));
        return;
    L121:
        return;
    L99:
        if (this.showOnlyPopular == true) goto L101;
        r10.add(UItem.asFlicker(30));
    L101:
        r10.add(UItem.asFlicker(29));
        r10.add(UItem.asFlicker(29));
        r10.add(UItem.asFlicker(29));
        r10.add(UItem.asFlicker(29));
        goto L102
    L78:
        if (this.showOnlyPopular == true) goto L80;
        r10.add(UItem.asGraySection(LocaleController.getString(R.string.SearchAppsPopular)));
    L80:
        int r05 = 0;
    L81:
        int r43 = this.popular.bots.size();
        PopularBots r52 = this.popular;
        if (r2 >= r43) goto L89;
        TLRPC.User r44 = r52.bots.get(r2);
        if (r112.contains(Long.valueOf(r44.id)) == true) goto L87;
        r112.add(Long.valueOf(r44.id));
        r10.add(UItem.asProfileCell(r44).accent().red().withOpenButton(this.openBotCallback));
        r05 = 1;
    L87:
        r2 = r2 + 1;
        goto L81
    L89:
        if (r52.loading == false) goto L91;
    L92:
        r10.add(UItem.asFlicker(29));
        r10.add(UItem.asFlicker(29));
        r10.add(UItem.asFlicker(29));
    L93:
        r2 = r05;
        goto L102
    L91:
        if (PopularBots.m10123$$Nest$fgetendReached(r52) == true) goto L93;
    L54:
        if (this.showOnlyPopular == true) goto L75;
        if (r42.size() <= 5) goto L62;
        String r06 = LocaleController.getString(R.string.SearchAppsMine);
        if (this.expandedMyBots == false) goto L60;
        int r53 = R.string.ShowLess;
    L61:
        r10.add(UItem.asGraySection(r06, LocaleController.getString(r53), new DialogsBotsAdapter$$ExternalSyntheticLambda6(this)));
    L63:
        int r07 = 0;
    L65:
        if (r07 >= r42.size()) goto L75;
        if (r07 >= 5) goto L68;
    L70:
        TLRPC.User r54 = (TLRPC.User) r42.get(r07);
        if (r112.contains(Long.valueOf(r54.id)) == true) goto L74;
        r112.add(Long.valueOf(r54.id));
        r10.add(UItem.asProfileCell(r54).accent().withOpenButton(this.openBotCallback));
    L74:
        r07 = r07 + 1;
        goto L65
    L68:
        if (this.expandedMyBots == true) goto L70;
    L60:
        r53 = R.string.ShowMore;
        goto L61
    L62:
        r10.add(UItem.asGraySection(LocaleController.getString(R.string.SearchAppsMine)));
        goto L63
    }

    public Object getObject(int r1) {
        UItem r0 = getItem(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.object;
    }

    public Object getTopPeerObject(int r2) {
        if (r2 < this.topPeersStart) goto L10;
        if (r2 >= this.topPeersEnd) goto L10;
        return getObject(r2);
    L10:
        return Boolean.FALSE;
    }

    public void openBot(TLRPC.User r2) {
        MessagesController.getInstance(this.currentAccount).openApp(r2, 0);
    }

    public void search(String r6) {
        if (TextUtils.equals(r6, this.query) == true) goto L17;
        this.query = r6;
        AndroidUtilities.cancelRunOnUIThread(this.searchMessagesRunnable);
        boolean r62 = TextUtils.isEmpty(this.query);
        ArrayList<MessageObject> r0 = this.searchMessages;
        if (r62 == false) goto L11;
        r0.clear();
        update(true);
        this.searchBotsId++;
        this.loadingMessages = false;
        this.loadingBots = false;
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
        AndroidUtilities.runOnUIThread(this.searchMessagesRunnable, 1000);
        this.loadingMessages = true;
        this.loadingBots = true;
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
}
