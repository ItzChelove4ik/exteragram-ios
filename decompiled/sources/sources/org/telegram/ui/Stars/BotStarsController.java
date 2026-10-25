package org.telegram.ui.Stars;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChannelMonetizationLayout;

/* loaded from: classes7.dex */
public class BotStarsController {
    private static volatile BotStarsController[] Instance;
    private static final Object[] lockObjects = null;
    public ArrayList<TLRPC.User> adminedBots;
    public ArrayList<TLRPC.Chat> adminedChannels;
    private final HashMap<Long, TLRPC.TL_payments_starsRevenueStats> botStarsStats;
    private final HashMap<Long, ChannelConnectedBots> connectedBots;
    public final int currentAccount;
    private final HashMap<Long, Long> lastLoadedBotStarsStats;
    private final HashMap<Long, Long> lastLoadedTonStats;
    private boolean loadingAdminedBots;
    private boolean loadingAdminedChannels;
    private final HashMap<Long, ChannelSuggestedBots> suggestedBots;
    private final HashMap<Long, TLRPC.TL_payments_starsRevenueStats> tonStats;
    private final HashMap<Long, TransactionsState> transactions;

    public static class ChannelConnectedBots {
        public final ArrayList<TL_payments.connectedBotStarRef> bots;
        public int count;
        public final int currentAccount;
        public final long dialogId;
        public boolean endReached;
        private boolean error;
        public long lastRequestTime;
        private boolean loading;
        private int reqId;

        public static /* synthetic */ void $r8$lambda$FHkBt5dyUGiJM8fxL90s048dPFw(ChannelConnectedBots r0, TLObject r1) {
            r0.lambda$load$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$Ueob_mr4Myv3FZg1bY7xb4kcIoA(ChannelConnectedBots r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$load$1(r1, r2);
        }

        public ChannelConnectedBots(int r2, long r3) {
            this.bots = new ArrayList();
            this.loading = false;
            this.error = false;
            this.currentAccount = r2;
            this.dialogId = r3;
            check();
        }

        private /* synthetic */ void lambda$load$0(TLObject r5) {
            this.reqId = 0;
            boolean r2 = true;
            if ((r5 instanceof TL_payments.connectedStarRefBots) == false) goto L14;
            TL_payments.connectedStarRefBots r52 = (TL_payments.connectedStarRefBots) r5;
            MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
            if (this.count > 0) goto L7;
            this.bots.clear();
        L7:
            this.count = r52.count;
            this.bots.addAll(r52.connected_bots);
            if (r52.connected_bots.isEmpty() == false) goto L10;
        L13:
            this.endReached = r2;
        L15:
            this.loading = false;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.channelConnectedBotsUpdate, new Object[]{Long.valueOf(this.dialogId)});
            return;
        L10:
            if (this.bots.size() >= this.count) goto L13;
            r2 = false;
            goto L13
        L14:
            this.error = true;
            this.endReached = true;
            goto L15
        }

        private /* synthetic */ void lambda$load$1(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new BotStarsController$ChannelConnectedBots$$ExternalSyntheticLambda1(this, r1));
        }

        public void apply(TL_payments.connectedStarRefBots r4) {
            MessagesController.getInstance(this.currentAccount).putUsers(r4.users, false);
            clear();
            this.bots.clear();
            cancel();
            this.count = r4.count;
            this.bots.addAll(r4.connected_bots);
            if (r4.connected_bots.isEmpty() == false) goto L5;
        L8:
            boolean r42 = true;
        L9:
            this.endReached = r42;
            this.error = false;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.channelConnectedBotsUpdate, new Object[]{Long.valueOf(this.dialogId)});
            load();
            return;
        L5:
            if (this.bots.size() >= this.count) goto L8;
            r42 = false;
            goto L9
        }

        public void applyEdit(TL_payments.connectedStarRefBots r9) {
            MessagesController.getInstance(this.currentAccount).putUsers(r9.users, false);
            int r0 = 0;
        L4:
            if (r0 >= r9.connected_bots.size()) goto L16;
            TL_payments.connectedBotStarRef r1 = r9.connected_bots.get(r0);
            int r3 = 0;
        L7:
            if (r3 >= this.bots.size()) goto L15;
            if (this.bots.get(r3).bot_id == r1.bot_id) goto L10;
            r3 = r3 + 1;
            goto L7
        L10:
            boolean r4 = r1.revoked;
            ArrayList<TL_payments.connectedBotStarRef> r5 = this.bots;
            if (r4 == false) goto L13;
            r5.remove(r3);
            this.count = Math.max(this.count - 1, 0);
            goto L15
        L13:
            r5.set(r3, r1);
        L15:
            r0 = r0 + 1;
            goto L4
        L16:
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.channelConnectedBotsUpdate, new Object[]{Long.valueOf(this.dialogId)});
            load();
        }

        public void cancel() {
            if (this.reqId == 0) goto L5;
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
            this.reqId = 0;
        L5:
            this.loading = false;
        }

        public void check() {
            if ((System.currentTimeMillis() - this.lastRequestTime) <= 900000) goto L6;
            clear();
            cancel();
            load();
            return;
        }

        public void clear() {
            this.count = 0;
            this.error = false;
            this.endReached = false;
        }

        public boolean isLoading() {
            return this.loading;
        }

        public void load() {
            if (this.loading == false) goto L5;
            return;
        L5:
            if (this.error == false) goto L7;
            return;
        L7:
            if (this.endReached == true) goto L16;
            this.lastRequestTime = System.currentTimeMillis();
            this.loading = true;
            TL_payments.getConnectedStarRefBots r1 = new TL_payments.getConnectedStarRefBots();
            r1.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r1.limit = 20;
            if (this.bots.isEmpty() == true) goto L12;
            ArrayList<TL_payments.connectedBotStarRef> r2 = this.bots;
            TL_payments.connectedBotStarRef r0 = r2.get(r2.size() - 1);
            r1.flags |= 4;
            r1.offset_date = r0.date;
            r1.offset_link = r0.url;
        L12:
            this.reqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new BotStarsController$ChannelConnectedBots$$ExternalSyntheticLambda0(this));
            return;
        }
    }

    public static class ChannelSuggestedBots {
        public final ArrayList<TL_payments.starRefProgram> bots;
        public int count;
        public final int currentAccount;
        public final long dialogId;
        public boolean endReached;
        private boolean error;
        private String lastOffset;
        public long lastRequestTime;
        private boolean loading;
        private int reqId;
        private Sort sorting;

        public enum Sort extends Enum<Sort> {
            private static final /* synthetic */ Sort[] $VALUES = null;
            public static final Sort BY_DATE = null;
            public static final Sort BY_PROFITABILITY = null;
            public static final Sort BY_REVENUE = null;

            private static /* synthetic */ Sort[] $values() {
                return new Sort[]{BY_PROFITABILITY, BY_REVENUE, BY_DATE};
            }

            static {
                BY_PROFITABILITY = new Sort("BY_PROFITABILITY", 0);
                BY_REVENUE = new Sort("BY_REVENUE", 1);
                BY_DATE = new Sort("BY_DATE", 2);
                $VALUES = $values();
            }

            Sort(String r1, int r2) {
            }

            public static Sort valueOf(String r1) {
                return (Sort) Enum.valueOf(Sort.class, r1);
            }

            public static Sort[] values() {
                return (Sort[]) $VALUES.clone();
            }
        }

        /* renamed from: $r8$lambda$XtflGBZ_-mU6s-dPNMEuziHtI-I, reason: not valid java name */
        public static /* synthetic */ void m18886$r8$lambda$XtflGBZ_mU6sdPNMEuziHtII(ChannelSuggestedBots r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$load$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$iGl9jApffiLugrXbY4ytFNYlzLY(ChannelSuggestedBots r0, TLObject r1) {
            r0.lambda$load$0(r1);
        }

        public ChannelSuggestedBots(int r2, long r3) {
            this.bots = new ArrayList();
            this.sorting = Sort.BY_PROFITABILITY;
            this.loading = false;
            this.error = false;
            this.lastOffset = null;
            this.currentAccount = r2;
            this.dialogId = r3;
            check();
        }

        private /* synthetic */ void lambda$load$0(TLObject r5) {
            boolean r1 = true;
            if ((r5 instanceof TL_payments.suggestedStarRefBots) == false) goto L14;
            TL_payments.suggestedStarRefBots r52 = (TL_payments.suggestedStarRefBots) r5;
            MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
            if (this.count > 0) goto L7;
            this.bots.clear();
        L7:
            this.count = r52.count;
            this.bots.addAll(r52.suggested_bots);
            this.lastOffset = r52.next_offset;
            if (r52.suggested_bots.isEmpty() == false) goto L10;
        L13:
            this.endReached = r1;
        L15:
            this.loading = false;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.channelSuggestedBotsUpdate, new Object[]{Long.valueOf(this.dialogId)});
            return;
        L10:
            if (this.bots.size() >= this.count) goto L13;
            r1 = false;
            goto L13
        L14:
            this.error = true;
            this.endReached = true;
            goto L15
        }

        private /* synthetic */ void lambda$load$1(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new BotStarsController$ChannelSuggestedBots$$ExternalSyntheticLambda1(this, r1));
        }

        public void cancel() {
            if (this.reqId == 0) goto L5;
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
            this.reqId = 0;
        L5:
            this.loading = false;
        }

        public void check() {
            if ((System.currentTimeMillis() - this.lastRequestTime) <= 900000) goto L6;
            clear();
            cancel();
            load();
            return;
        }

        public void clear() {
            this.count = 0;
            this.endReached = false;
            this.error = false;
            this.lastRequestTime = 0;
            this.lastOffset = null;
        }

        public Sort getSort() {
            return this.sorting;
        }

        public boolean isLoading() {
            return this.loading;
        }

        public void load() {
            if (this.loading == false) goto L5;
            return;
        L5:
            if (this.error == false) goto L7;
            return;
        L7:
            if (this.endReached == true) goto L25;
            this.lastRequestTime = System.currentTimeMillis();
            boolean r0 = true;
            this.loading = true;
            TL_payments.getSuggestedStarRefBots r1 = new TL_payments.getSuggestedStarRefBots();
            r1.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            r1.limit = 20;
            Sort r2 = this.sorting;
            if (r2 != Sort.BY_DATE) goto L12;
            boolean r3 = true;
        L13:
            r1.order_by_date = r3;
            if (r2 == Sort.BY_REVENUE) goto L17;
            r0 = false;
        L17:
            r1.order_by_revenue = r0;
            if (TextUtils.isEmpty(this.lastOffset) == true) goto L20;
            r1.offset = this.lastOffset;
        L21:
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new BotStarsController$ChannelSuggestedBots$$ExternalSyntheticLambda0(this));
            return;
        L20:
            r1.offset = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L21
        L12:
            r3 = false;
            goto L13
        }

        public void reload() {
            clear();
            cancel();
            load();
        }

        public void remove(long r4) {
            int r0 = 0;
        L4:
            if (r0 >= this.bots.size()) goto L10;
            if (this.bots.get(r0).bot_id == r4) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            this.bots.remove(r0);
            this.count--;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.channelSuggestedBotsUpdate, new Object[]{Long.valueOf(this.dialogId)});
            return;
        }

        public void setSort(Sort r2) {
            if (this.sorting == r2) goto L6;
            this.sorting = r2;
            reload();
            return;
        }
    }

    public class TransactionsState {
        private final boolean[] endReached;
        private final boolean[] loading;
        private final String[] offset;
        final /* synthetic */ BotStarsController this$0;
        public final ArrayList<TL_stars.StarsTransaction>[] transactions;
        public final boolean[] transactionsExist;

        /* renamed from: -$$Nest$fgetendReached, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean[] m18887$$Nest$fgetendReached(TransactionsState r0) {
            return r0.endReached;
        }

        /* renamed from: -$$Nest$fgetloading, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean[] m18888$$Nest$fgetloading(TransactionsState r0) {
            return r0.loading;
        }

        /* renamed from: -$$Nest$fgetoffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String[] m18889$$Nest$fgetoffset(TransactionsState r0) {
            return r0.offset;
        }

        private TransactionsState(BotStarsController r3) {
            this.this$0 = r3;
            this.transactions = new ArrayList[]{new ArrayList(), new ArrayList(), new ArrayList()};
            this.transactionsExist = new boolean[3];
            this.offset = new String[3];
            this.loading = new boolean[3];
            this.endReached = new boolean[3];
        }

        public /* synthetic */ TransactionsState(BotStarsController r1, BotStarsControllerIA r2) {
            this(r1);
        }
    }

    /* renamed from: $r8$lambda$-ou_1qi2SFqEMcb-tC5jvRYqiAQ, reason: not valid java name */
    public static /* synthetic */ void m18877$r8$lambda$ou_1qi2SFqEMcbtC5jvRYqiAQ(BotStarsController r0, AlertDialog r1, TLObject r2, long r3, Utilities.Callback r5) {
        r0.lambda$getConnectedBot$10(r1, r2, r3, r5);
    }

    /* renamed from: $r8$lambda$2zu20Ne9WvOsA4ei-9WPuLb_eNc, reason: not valid java name */
    public static /* synthetic */ void m18878$r8$lambda$2zu20Ne9WvOsA4ei9WPuLb_eNc(BotStarsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadAdminedChannels$9(r1, r2);
    }

    /* renamed from: $r8$lambda$5FMgRaib-EuhB5Zcd94BWtXZqxM, reason: not valid java name */
    public static /* synthetic */ void m18879$r8$lambda$5FMgRaibEuhB5Zcd94BWtXZqxM(BotStarsController r0, TransactionsState r1, int r2, TLObject r3, long r4) {
        r0.lambda$loadTransactions$4(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$5x3AfgN_uFuy0MvWm4mSLQzBfPw(BotStarsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadAdminedBots$7(r1, r2);
    }

    /* renamed from: $r8$lambda$VLH7XNOoRLmyk4F-FwZhrokle6A, reason: not valid java name */
    public static /* synthetic */ void m18880$r8$lambda$VLH7XNOoRLmyk4FFwZhrokle6A(BotStarsController r0, TLObject r1, long r2) {
        r0.lambda$getTONRevenueStats$2(r1, r2);
    }

    /* renamed from: $r8$lambda$XdaZEpPlhj4NL1hu-HOmpK1BDQY, reason: not valid java name */
    public static /* synthetic */ void m18881$r8$lambda$XdaZEpPlhj4NL1huHOmpK1BDQY(BotStarsController r0, TLObject r1) {
        r0.lambda$loadAdminedChannels$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$cjoCYUX17tWp14akn1o6iPh5J4Q(BotStarsController r0, int r1, DialogInterface r2) {
        r0.lambda$getConnectedBot$12(r1, r2);
    }

    /* renamed from: $r8$lambda$fnBuZ8JjoxSpxZ9ozd9QQZ7fQ-o, reason: not valid java name */
    public static /* synthetic */ void m18882$r8$lambda$fnBuZ8JjoxSpxZ9ozd9QQZ7fQo(BotStarsController r0, TransactionsState r1, int r2, long r3, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$loadTransactions$5(r1, r2, r3, r5, r6);
    }

    /* renamed from: $r8$lambda$kU0yCi5RXEPs9TMjX-aDNLnIAwE, reason: not valid java name */
    public static /* synthetic */ void m18883$r8$lambda$kU0yCi5RXEPs9TMjXaDNLnIAwE(BotStarsController r0, TLObject r1, long r2) {
        r0.lambda$getStarsRevenueStats$0(r1, r2);
    }

    /* renamed from: $r8$lambda$nuVT8yrOM7X6PN5dH-7oYToUxD4, reason: not valid java name */
    public static /* synthetic */ void m18884$r8$lambda$nuVT8yrOM7X6PN5dH7oYToUxD4(BotStarsController r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$getTONRevenueStats$3(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$qMC-fMb6F2VFtHE6tfoRcFGK0KM, reason: not valid java name */
    public static /* synthetic */ void m18885$r8$lambda$qMCfMb6F2VFtHE6tfoRcFGK0KM(BotStarsController r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$getStarsRevenueStats$1(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$utaQDC2KuZBMY2GqaDbSqx1NaLk(BotStarsController r0, TLObject r1) {
        r0.lambda$loadAdminedBots$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$yjVf6zcQZpNNr_6t5lCoW5zKPeU(BotStarsController r0, AlertDialog r1, long r2, Utilities.Callback r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$getConnectedBot$11(r1, r2, r4, r5, r6);
    }

    static {
        Instance = new BotStarsController[16];
        lockObjects = new Object[16];
        int r1 = 0;
    L3:
        if (r1 >= 16) goto L5;
        lockObjects[r1] = new Object();
        r1 = r1 + 1;
        goto L3
    }

    private BotStarsController(int r2) {
        this.lastLoadedBotStarsStats = new HashMap();
        this.botStarsStats = new HashMap();
        this.lastLoadedTonStats = new HashMap();
        this.tonStats = new HashMap();
        this.transactions = new HashMap();
        this.connectedBots = new HashMap();
        this.suggestedBots = new HashMap();
        this.currentAccount = r2;
    }

    public static BotStarsController getInstance(int r3) {
        BotStarsController r0 = Instance[r3];
        if (r0 != null) goto L15;
        Object r1 = lockObjects[r3];
        monitor-enter(r1);
        BotStarsController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        BotStarsController[] r03 = Instance;     // Catch: Throwable -> L9
        BotStarsController r2 = new BotStarsController(r3);     // Catch: Throwable -> L9
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

    private TransactionsState getTransactionsState(long r3) {
        TransactionsState r0 = this.transactions.get(Long.valueOf(r3));
        if (r0 != null) goto L6;
        HashMap<Long, TransactionsState> r02 = this.transactions;
        Long r32 = Long.valueOf(r3);
        TransactionsState r4 = new TransactionsState(this, null);
        r02.put(r32, r4);
        return r4;
    L6:
        return r0;
    }

    private /* synthetic */ void lambda$getConnectedBot$10(AlertDialog r2, TLObject r3, long r4, Utilities.Callback r6) {
        r2.dismiss();
        if ((r3 instanceof TL_payments.connectedStarRefBots) == false) goto L14;
        TL_payments.connectedStarRefBots r32 = (TL_payments.connectedStarRefBots) r3;
        int r0 = 0;
        MessagesController.getInstance(this.currentAccount).putUsers(r32.users, false);
    L6:
        if (r0 >= r32.connected_bots.size()) goto L14;
        if (r32.connected_bots.get(r0).bot_id != r4) goto L13;
        if (r32.connected_bots.get(r0).revoked == true) goto L13;
        r6.run(r32.connected_bots.get(r0));
        return;
    L13:
        r0 = r0 + 1;
    L14:
        r6.run(null);
    }

    private /* synthetic */ void lambda$getConnectedBot$11(AlertDialog r4, long r5, Utilities.Callback r7, TLObject r8, TLRPC.TL_error r9) {
        AndroidUtilities.runOnUIThread(new BotStarsController$$ExternalSyntheticLambda9(this, r4, r8, r5, r7));
    }

    private /* synthetic */ void lambda$getConnectedBot$12(int r1, DialogInterface r2) {
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r1, true);
    }

    private /* synthetic */ void lambda$getStarsRevenueStats$0(TLObject r4, long r5) {
        boolean r0 = r4 instanceof TLRPC.TL_payments_starsRevenueStats;
        HashMap<Long, TLRPC.TL_payments_starsRevenueStats> r1 = this.botStarsStats;
        if (r0 == false) goto L5;
        Long r02 = Long.valueOf(r5);
        r1.put(r02, (TLRPC.TL_payments_starsRevenueStats) r4);
    L6:
        this.lastLoadedBotStarsStats.put(Long.valueOf(r5), Long.valueOf(System.currentTimeMillis()));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.botStarsUpdated, new Object[]{Long.valueOf(r5)});
        return;
    L5:
        r1.put(Long.valueOf(r5), null);
        goto L6
    }

    private /* synthetic */ void lambda$getStarsRevenueStats$1(long r1, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new BotStarsController$$ExternalSyntheticLambda4(this, r3, r1));
    }

    private /* synthetic */ void lambda$getTONRevenueStats$2(TLObject r4, long r5) {
        boolean r0 = r4 instanceof TLRPC.TL_payments_starsRevenueStats;
        HashMap<Long, TLRPC.TL_payments_starsRevenueStats> r1 = this.tonStats;
        if (r0 == false) goto L5;
        Long r02 = Long.valueOf(r5);
        r1.put(r02, (TLRPC.TL_payments_starsRevenueStats) r4);
    L6:
        this.lastLoadedTonStats.put(Long.valueOf(r5), Long.valueOf(System.currentTimeMillis()));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.botStarsUpdated, new Object[]{Long.valueOf(r5)});
        return;
    L5:
        r1.put(Long.valueOf(r5), null);
        goto L6
    }

    private /* synthetic */ void lambda$getTONRevenueStats$3(long r1, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new BotStarsController$$ExternalSyntheticLambda3(this, r3, r1));
    }

    private /* synthetic */ void lambda$loadAdminedBots$6(TLObject r5) {
        this.adminedBots = new ArrayList();
        this.loadingAdminedBots = false;
        if ((r5 instanceof Vector) == false) goto L11;
        Vector r52 = (Vector) r5;
        int r1 = 0;
    L6:
        if (r1 >= r52.objects.size()) goto L8;
        this.adminedBots.add((TLRPC.User) r52.objects.get(r1));
        r1 = r1 + 1;
        goto L6
    L8:
        MessagesController.getInstance(this.currentAccount).putUsers(this.adminedBots, false);
        return;
    }

    private /* synthetic */ void lambda$loadAdminedBots$7(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new BotStarsController$$ExternalSyntheticLambda12(this, r1));
    }

    private /* synthetic */ void lambda$loadAdminedChannels$8(TLObject r4) {
        this.adminedChannels = new ArrayList();
        this.loadingAdminedChannels = false;
        if ((r4 instanceof TLRPC.messages_Chats) == false) goto L5;
        TLRPC.messages_Chats r42 = (TLRPC.messages_Chats) r4;
        MessagesController.getInstance(this.currentAccount).putChats(r42.chats, false);
        this.adminedChannels.addAll(r42.chats);
    L5:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.adminedChannelsLoaded, new Object[0]);
    }

    private /* synthetic */ void lambda$loadAdminedChannels$9(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new BotStarsController$$ExternalSyntheticLambda11(this, r1));
    }

    private /* synthetic */ void lambda$loadTransactions$4(TransactionsState r5, int r6, TLObject r7, long r8) {
        boolean r1 = false;
        TransactionsState.m18888$$Nest$fgetloading(r5)[r6] = false;
        if ((r7 instanceof TL_stars.StarsStatus) == false) goto L20;
        TL_stars.StarsStatus r72 = (TL_stars.StarsStatus) r7;
        MessagesController.getInstance(this.currentAccount).putUsers(r72.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r72.chats, false);
        r5.transactions[r6].addAll(r72.history);
        boolean[] r0 = r5.transactionsExist;
        if (r5.transactions[r6].isEmpty() == true) goto L7;
    L10:
        boolean r2 = true;
    L11:
        r0[r6] = r2;
        boolean[] r02 = TransactionsState.m18887$$Nest$fgetendReached(r5);
        if ((r72.flags & 1) != 0) goto L14;
        r1 = true;
    L14:
        r02[r6] = r1;
        String[] r03 = TransactionsState.m18889$$Nest$fgetoffset(r5);
        if (TransactionsState.m18887$$Nest$fgetendReached(r5)[r6] == false) goto L17;
        String r52 = null;
    L18:
        r03[r6] = r52;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.botStarsTransactionsLoaded, new Object[]{Long.valueOf(r8)});
        return;
    L17:
        r52 = r72.next_offset;
        goto L18
    L7:
        if (r5.transactionsExist[r6] == true) goto L10;
        r2 = false;
        goto L11
    }

    private /* synthetic */ void lambda$loadTransactions$5(TransactionsState r3, int r4, long r5, TLObject r7, TLRPC.TL_error r8) {
        AndroidUtilities.runOnUIThread(new BotStarsController$$ExternalSyntheticLambda8(this, r3, r4, r7, r5));
    }

    public boolean botHasStars(long r3) {
        TLRPC.TL_payments_starsRevenueStats r2 = getStarsRevenueStats(r3);
        if (r2 == null) goto L14;
        TLRPC.TL_starsRevenueStatus r22 = r2.status;
        if (r22 != null) goto L7;
        return false;
    L7:
        if (r22.available_balance.amount <= 0) goto L9;
        return true;
    L9:
        if (r22.overall_revenue.amount <= 0) goto L11;
        return true;
    L11:
        if (r22.current_balance.amount <= 0) goto L19;
        return true;
    L19:
        return false;
    L14:
        return false;
    }

    public boolean botHasTON(long r4) {
        TLRPC.TL_payments_starsRevenueStats r3 = getTONRevenueStats(r4, false);
        if (r3 == null) goto L14;
        TLRPC.TL_starsRevenueStatus r32 = r3.status;
        if (r32 == null) goto L14;
        if (r32.current_balance.amount <= 0) goto L9;
        return true;
    L9:
        if (r32.available_balance.amount <= 0) goto L11;
        return true;
    L11:
        if (r32.overall_revenue.amount <= 0) goto L14;
        return true;
    L14:
        return false;
    }

    public boolean didFullyLoadTransactions(long r1, int r3) {
        return TransactionsState.m18887$$Nest$fgetendReached(getTransactionsState(r1))[r3];
    }

    public ArrayList<TLObject> getAdmined() {
        loadAdminedBots();
        loadAdminedChannels();
        ArrayList<TLObject> r0 = new ArrayList();
        ArrayList<TLRPC.User> r1 = this.adminedBots;
        if (r1 == null) goto L5;
        r0.addAll(r1);
    L5:
        ArrayList<TLRPC.Chat> r2 = this.adminedChannels;
        if (r2 == null) goto L8;
        r0.addAll(r2);
    L8:
        return r0;
    }

    public ArrayList<TLObject> getAdminedChannels() {
        loadAdminedChannels();
        ArrayList<TLObject> r0 = new ArrayList();
        ArrayList<TLRPC.Chat> r1 = this.adminedChannels;
        if (r1 == null) goto L5;
        r0.addAll(r1);
    L5:
        return r0;
    }

    public long getAvailableBalance(long r1) {
        TLRPC.TL_payments_starsRevenueStats r0 = getStarsRevenueStats(r1);
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.status.available_balance.amount;
    }

    public TL_stars.StarsAmount getBotStarsBalance(long r1) {
        TLRPC.TL_payments_starsRevenueStats r0 = getStarsRevenueStats(r1);
        if (r0 != null) goto L7;
        return TL_stars.StarsAmount.ofStars(0);
    L7:
        return r0.status.current_balance;
    }

    public ChannelConnectedBots getChannelConnectedBots(long r4) {
        ChannelConnectedBots r0 = this.connectedBots.get(Long.valueOf(r4));
        if (r0 != null) goto L6;
        HashMap<Long, ChannelConnectedBots> r02 = this.connectedBots;
        Long r1 = Long.valueOf(r4);
        ChannelConnectedBots r2 = new ChannelConnectedBots(this.currentAccount, r4);
        r02.put(r1, r2);
        return r2;
    L6:
        return r0;
    }

    public ChannelSuggestedBots getChannelSuggestedBots(long r4) {
        ChannelSuggestedBots r0 = this.suggestedBots.get(Long.valueOf(r4));
        if (r0 != null) goto L6;
        HashMap<Long, ChannelSuggestedBots> r02 = this.suggestedBots;
        Long r1 = Long.valueOf(r4);
        ChannelSuggestedBots r2 = new ChannelSuggestedBots(this.currentAccount, r4);
        r02.put(r1, r2);
        return r2;
    L6:
        return r0;
    }

    public void getConnectedBot(Context r5, long r6, long r8, Utilities.Callback<TL_payments.connectedBotStarRef> r10) {
        if (r10 != null) goto L4;
        return;
    L4:
        ChannelConnectedBots r0 = this.connectedBots.get(Long.valueOf(r6));
        if (r0 == null) goto L16;
        int r1 = 0;
    L8:
        if (r1 >= r0.bots.size()) goto L16;
        if (r0.bots.get(r1).revoked == true) goto L15;
        if (r0.bots.get(r1).bot_id != r8) goto L15;
        r10.run(r0.bots.get(r1));
        return;
    L15:
        r1 = r1 + 1;
    L16:
        AlertDialog r62 = new AlertDialog(r5, 3);
        TL_payments.getConnectedStarRefBot r2 = new TL_payments.getConnectedStarRefBot();
        r2.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r6);
        r2.bot = MessagesController.getInstance(this.currentAccount).getInputUser(r8);
        int r4 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new BotStarsController$$ExternalSyntheticLambda5(this, r62, r8, r10));
        r62.setCanCancel(true);
        r62.setOnCancelListener(new BotStarsController$$ExternalSyntheticLambda6(this, r4));
        r62.showDelayed(200);
    }

    public TLRPC.TL_payments_starsRevenueStats getStarsRevenueStats(long r2) {
        return getStarsRevenueStats(r2, false);
    }

    public TLRPC.TL_payments_starsRevenueStats getStarsRevenueStats(long r7, boolean r9) {
        Long r0 = this.lastLoadedBotStarsStats.get(Long.valueOf(r7));
        TLRPC.TL_payments_starsRevenueStats r1 = this.botStarsStats.get(Long.valueOf(r7));
        if (r0 != null) goto L5;
    L9:
        TLRPC.TL_payments_getStarsRevenueStats r92 = new TLRPC.TL_payments_getStarsRevenueStats();
        r92.dark = Theme.isCurrentThemeDark();
        r92.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r7);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r92, new BotStarsController$$ExternalSyntheticLambda0(this, r7));
        return r1;
    L5:
        if ((System.currentTimeMillis() - r0.longValue()) > 300000) goto L9;
        if (r9 == true) goto L9;
        return r1;
    }

    public long getTONBalance(long r2) {
        TLRPC.TL_payments_starsRevenueStats r1 = getTONRevenueStats(r2, false);
        if (r1 == null) goto L11;
        TLRPC.TL_starsRevenueStatus r12 = r1.status;
        if (r12 == null) goto L13;
        TL_stars.StarsAmount r13 = r12.current_balance;
        if (r13 != null) goto L10;
        return 0;
    L10:
        return r13.amount;
    L13:
        return 0;
    L11:
        return 0;
    }

    public TLRPC.TL_payments_starsRevenueStats getTONRevenueStats(long r12, boolean r14) {
        Long r0 = this.lastLoadedTonStats.get(Long.valueOf(r12));
        TLRPC.TL_payments_starsRevenueStats r1 = this.tonStats.get(Long.valueOf(r12));
        if (r0 != null) goto L5;
    L9:
        TLRPC.TL_payments_getStarsRevenueStats r3 = new TLRPC.TL_payments_getStarsRevenueStats();
        r3.ton = true;
        r3.dark = Theme.isCurrentThemeDark();
        r3.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r12);
        TLRPC.ChatFull r142 = MessagesController.getInstance(this.currentAccount).getChatFull(-r12);
        if (r142 == null) goto L13;
        int r143 = r142.stats_dc;
    L12:
        int r8 = r143;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r3, new BotStarsController$$ExternalSyntheticLambda1(this, r12), null, null, 0, r8, 1, true);
        return r1;
    L13:
        r143 = Integer.MAX_VALUE;
        goto L12
    L5:
        if ((System.currentTimeMillis() - r0.longValue()) > 300000) goto L9;
        if (r14 == true) goto L9;
        return r1;
    }

    public ArrayList<TL_stars.StarsTransaction> getTransactions(long r1, int r3) {
        return getTransactionsState(r1).transactions[r3];
    }

    public boolean hasTransactions(long r2) {
        return hasTransactions(r2, 0);
    }

    public boolean hasTransactions(long r1, int r3) {
        return !getTransactionsState(r1).transactions[r3].isEmpty();
    }

    public void invalidateStarsBalance(long r2) {
        getStarsRevenueStats(r2, true);
    }

    public void invalidateTransactions(long r6, boolean r8) {
        TransactionsState r0 = getTransactionsState(r6);
        int r2 = 0;
    L4:
        if (r2 >= 3) goto L12;
        if (TransactionsState.m18888$$Nest$fgetloading(r0)[r2] == true) goto L11;
        r0.transactions[r2].clear();
        TransactionsState.m18889$$Nest$fgetoffset(r0)[r2] = null;
        TransactionsState.m18888$$Nest$fgetloading(r0)[r2] = false;
        TransactionsState.m18887$$Nest$fgetendReached(r0)[r2] = false;
        if (r8 == false) goto L11;
        loadTransactions(r6, r2);
    L11:
        r2 = r2 + 1;
        goto L4
    }

    public boolean isStarsBalanceAvailable(long r1) {
        if (getStarsRevenueStats(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isTONBalanceAvailable(long r2) {
        if (getTONRevenueStats(r2, false) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void loadAdminedBots() {
        if (this.loadingAdminedBots == false) goto L5;
        return;
    L5:
        if (this.adminedBots != null) goto L9;
        this.loadingAdminedBots = true;
        TL_bots.getAdminedBots r0 = new TL_bots.getAdminedBots();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new BotStarsController$$ExternalSyntheticLambda10(this));
        return;
    }

    public void loadAdminedChannels() {
        if (this.loadingAdminedChannels == false) goto L5;
        return;
    L5:
        if (this.adminedChannels != null) goto L9;
        this.loadingAdminedChannels = true;
        TLRPC.TL_channels_getAdminedPublicChannels r0 = new TLRPC.TL_channels_getAdminedPublicChannels();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new BotStarsController$$ExternalSyntheticLambda7(this));
        return;
    }

    public void loadTransactions(long r9, int r11) {
        TransactionsState r2 = getTransactionsState(r9);
        if (TransactionsState.m18888$$Nest$fgetloading(r2)[r11] == false) goto L5;
        return;
    L5:
        if (TransactionsState.m18887$$Nest$fgetendReached(r2)[r11] == true) goto L21;
        boolean r1 = true;
        TransactionsState.m18888$$Nest$fgetloading(r2)[r11] = true;
        TL_stars.TL_payments_getStarsTransactions r6 = new TL_stars.TL_payments_getStarsTransactions();
        r6.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r9);
        if (r11 != 1) goto L10;
        boolean r3 = true;
    L11:
        r6.inbound = r3;
        if (r11 == 2) goto L15;
        r1 = false;
    L15:
        r6.outbound = r1;
        String r0 = TransactionsState.m18889$$Nest$fgetoffset(r2)[r11];
        r6.offset = r0;
        if (r0 != null) goto L18;
        r6.offset = _UrlKt.FRAGMENT_ENCODE_SET;
    L18:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r6, new BotStarsController$$ExternalSyntheticLambda2(this, r2, r11, r9));
        return;
    L10:
        r3 = false;
        goto L11
    }

    public void onUpdate(TL_update.TL_updateStarsRevenueStatus r6) {
        if (r6 == null) goto L19;
        long r0 = DialogObject.getPeerDialogId(r6.peer);
        if (r0 >= 0) goto L12;
        ChannelMonetizationLayout r5 = ChannelMonetizationLayout.instance;
        if (r5 != null) goto L9;
        return;
    L9:
        if (r5.dialogId != DialogObject.getPeerDialogId(r6.peer)) goto L18;
        ChannelMonetizationLayout r52 = ChannelMonetizationLayout.instance;
        TLRPC.TL_starsRevenueStatus r62 = r6.status;
        r52.setupBalances(r62.current_balance instanceof TL_stars.TL_starsTonAmount, r62);
        ChannelMonetizationLayout.instance.reloadTransactions();
        return;
    L18:
        return;
    L12:
        TLRPC.TL_payments_starsRevenueStats r3 = getStarsRevenueStats(r0, true);
        if (r3 == null) goto L15;
        r3.status = r6.status;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.botStarsUpdated, new Object[]{Long.valueOf(r0)});
    L15:
        invalidateTransactions(r0, true);
        return;
    }

    public void preloadStarsStats(long r6) {
        Long r0 = this.lastLoadedBotStarsStats.get(Long.valueOf(r6));
        if (r0 != null) goto L5;
    L8:
        boolean r02 = true;
    L9:
        getStarsRevenueStats(r6, r02);
        return;
    L5:
        if ((System.currentTimeMillis() - r0.longValue()) > NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L8;
        r02 = false;
        goto L9
    }

    public void preloadTonStats(long r6) {
        Long r0 = this.lastLoadedTonStats.get(Long.valueOf(r6));
        if (r0 != null) goto L5;
    L8:
        boolean r02 = true;
    L9:
        getTONRevenueStats(r6, r02);
        return;
    L5:
        if ((System.currentTimeMillis() - r0.longValue()) > NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L8;
        r02 = false;
        goto L9
    }
}
