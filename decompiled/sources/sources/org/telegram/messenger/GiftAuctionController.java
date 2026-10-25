package org.telegram.messenger;

import android.text.TextUtils;
import android.util.LongSparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import me.vkryl.core.reference.ReferenceList;
import me.vkryl.core.reference.ReferenceMap;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.Gifts.AuctionBidSheet;
import org.telegram.ui.Stars.StarsController;

/* loaded from: classes.dex */
public class GiftAuctionController extends BaseController {
    private static volatile GiftAuctionController[] Instance;
    private final ArrayList<Auction> activeAuctions;
    private final LongSparseArray<AuctionInternal> auctions;
    private final ReferenceMap<Long, OnAuctionUpdateListener> listeners;
    private final ReferenceList<OnActiveAuctionsUpdateListeners> onActiveAuctionsUpdateListeners;
    private final LongSparseArray<Boolean> upgrades;
    private boolean wasRequestedActiveAuctions;

    /* loaded from: classes5.dex */
    public static class Auction {
        private int approximatedMyPlace;
        public TL_stars.StarGiftAuctionState auctionState;
        public TL_stars.TL_starGiftAuctionState auctionStateActive;
        public TL_stars.TL_starGiftAuctionStateFinished auctionStateFinished;
        public TL_stars.TL_StarGiftAuctionUserState auctionUserState;
        public final int currentAccount;
        public TL_stars.StarGift gift;
        public final String giftAuctionSlug;
        public final long giftDocumentId;
        public final long giftId;
        public ArrayList<TL_stars.StarGiftAttribute> previewAttributes;

        public enum BidStatus extends Enum<BidStatus> {
            private static final /* synthetic */ BidStatus[] $VALUES = null;
            public static final BidStatus NO_BID = null;
            public static final BidStatus OUTBID = null;
            public static final BidStatus RETURNED = null;
            public static final BidStatus WINNING = null;

            private static /* synthetic */ BidStatus[] $values() {
                return new BidStatus[]{WINNING, OUTBID, RETURNED, NO_BID};
            }

            static {
                WINNING = new BidStatus("WINNING", 0);
                OUTBID = new BidStatus("OUTBID", 1);
                RETURNED = new BidStatus("RETURNED", 2);
                NO_BID = new BidStatus("NO_BID", 3);
                $VALUES = $values();
            }

            BidStatus(String r1, int r2) {
            }

            public static BidStatus valueOf(String r1) {
                return (BidStatus) Enum.valueOf(BidStatus.class, r1);
            }

            public static BidStatus[] values() {
                return (BidStatus[]) $VALUES.clone();
            }

            public boolean isOutbid() {
                if (this != OUTBID) goto L5;
                return true;
            L5:
                if (this == RETURNED) goto L11;
                return false;
            L11:
                return true;
            }
        }

        /* renamed from: -$$Nest$mapplyAuctionState, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4095$$Nest$mapplyAuctionState(Auction r0, TL_stars.StarGiftAuctionState r1) {
            return r0.applyAuctionState(r1);
        }

        /* renamed from: -$$Nest$mapplyGift, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4096$$Nest$mapplyGift(Auction r0, TL_stars.StarGift r1) {
            return r0.applyGift(r1);
        }

        /* renamed from: -$$Nest$mapplyUserState, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4097$$Nest$mapplyUserState(Auction r0, TL_stars.TL_StarGiftAuctionUserState r1) {
            return r0.applyUserState(r1);
        }

        /* renamed from: -$$Nest$mgetVersion, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m4098$$Nest$mgetVersion(Auction r0) {
            return r0.getVersion();
        }

        private Auction(int r3, TL_stars.StarGift r4, TL_stars.StarGiftAuctionState r5, TL_stars.TL_StarGiftAuctionUserState r6) {
            this.currentAccount = r3;
            this.gift = r4;
            this.auctionState = r5;
            this.auctionUserState = r6;
            this.giftId = r4.id;
            TLRPC.Document r32 = r4.sticker;
            if (r32 == null) goto L5;
            long r0 = r32.id;
        L6:
            this.giftDocumentId = r0;
            this.giftAuctionSlug = r4.auction_slug;
            applyAuctionState(r5);
            return;
        L5:
            r0 = 0;
            goto L6
        }

        public /* synthetic */ Auction(int r1, TL_stars.StarGift r2, TL_stars.StarGiftAuctionState r3, TL_stars.TL_StarGiftAuctionUserState r4, GiftAuctionControllerIA r5) {
            this(r1, r2, r3, r4);
        }

        private boolean applyAuctionState(TL_stars.StarGiftAuctionState r5) {
            if ((r5 instanceof TL_stars.TL_starGiftAuctionState) == false) goto L9;
            TL_stars.TL_starGiftAuctionState r0 = (TL_stars.TL_starGiftAuctionState) r5;
            int r2 = getVersion();
            if (r0.version <= r2) goto L14;
            this.auctionState = r5;
            this.auctionStateActive = r0;
            onUpdateUserOrAuctionState();
            return true;
        L14:
            return false;
        L9:
            if ((r5 instanceof TL_stars.TL_starGiftAuctionStateFinished) == true) goto L11;
            return false;
        L11:
            if (isFinished() == true) goto L17;
            this.auctionState = r5;
            this.auctionStateFinished = (TL_stars.TL_starGiftAuctionStateFinished) r5;
            return true;
        L17:
            return false;
        }

        private boolean applyGift(TL_stars.StarGift r1) {
            this.gift = r1;
            return true;
        }

        private boolean applyUserState(TL_stars.TL_StarGiftAuctionUserState r1) {
            this.auctionUserState = r1;
            onUpdateUserOrAuctionState();
            return true;
        }

        private int approximateMyPlace() {
            TL_stars.TL_starGiftAuctionState r0 = this.auctionStateActive;
            if (r0 != null) goto L6;
            return -1;
        L6:
            if (r0.top_bidders == null) goto L15;
            long r2 = UserConfig.getInstance(this.currentAccount).getClientUserId();
            int r02 = 0;
        L9:
            if (r02 >= this.auctionStateActive.top_bidders.size()) goto L15;
            if (r2 == this.auctionStateActive.top_bidders.get(r02).longValue()) goto L13;
            r02 = r02 + 1;
            goto L9
        L13:
            return r02 + 1;
        L15:
            TL_stars.TL_StarGiftAuctionUserState r03 = this.auctionUserState;
            long r22 = r03.bid_amount;
            if (r22 > 0) goto L18;
        L21:
            return -1;
        L18:
            if (r03.returned == true) goto L21;
            return approximatePlaceFromStars(r22, r03.bid_date);
        }

        private int getVersion() {
            if (isFinished() == false) goto L6;
            return Integer.MAX_VALUE;
        L6:
            TL_stars.TL_starGiftAuctionState r1 = this.auctionStateActive;
            if (r1 != null) goto L9;
            return 0;
        L9:
            return r1.version;
        }

        private void onUpdateUserOrAuctionState() {
            this.approximatedMyPlace = approximateMyPlace();
        }

        public long approximateBidAmountFromPlace(int r6) {
            TL_stars.TL_starGiftAuctionState r0 = this.auctionStateActive;
            if (r0 == null) goto L16;
            ArrayList<TL_stars.TL_AuctionBidLevel> r02 = r0.bid_levels;
            if (r02 == null) goto L16;
            int r1 = r02.size();
            int r2 = 0;
        L8:
            if (r2 >= r1) goto L14;
            TL_stars.TL_AuctionBidLevel r3 = r02.get(r2);
            r2 = r2 + 1;
            TL_stars.TL_AuctionBidLevel r32 = r3;
            if (r6 > r32.pos) goto L8;
            return r32.amount;
        L14:
            return getMinimumBid();
        L16:
            return getMinimumBid();
        }

        public int approximatePlaceFromStars(long r2) {
            return approximatePlaceFromStars(r2, ConnectionsManager.getInstance(this.currentAccount).getCurrentTime());
        }

        public int approximatePlaceFromStars(long r7, int r9) {
            TL_stars.TL_starGiftAuctionState r6 = this.auctionStateActive;
            if (r6 == null) goto L21;
            ArrayList<TL_stars.TL_AuctionBidLevel> r62 = r6.bid_levels;
            if (r62 == null) goto L28;
            int r0 = r62.size();
            int r1 = 0;
            int r2 = 0;
        L8:
            if (r2 >= r0) goto L20;
            TL_stars.TL_AuctionBidLevel r12 = r62.get(r2);
            r2 = r2 + 1;
            TL_stars.TL_AuctionBidLevel r13 = r12;
            long r3 = r13.amount;
            if (r7 > r3) goto L18;
            if (r7 != r3) goto L16;
            if (r9 <= r13.date) goto L18;
        L16:
            r1 = r13.pos;
        L18:
            return r13.pos;
        L20:
            return r1 + 1;
        L28:
            return -1;
        L21:
            return -1;
        }

        public int getApproximatedMyPlace() {
            return this.approximatedMyPlace;
        }

        public BidStatus getBidStatus() {
            TL_stars.TL_StarGiftAuctionUserState r0 = this.auctionUserState;
            if (r0.returned == false) goto L7;
            return BidStatus.RETURNED;
        L7:
            if (r0.bid_amount != 0) goto L11;
            return BidStatus.NO_BID;
        L11:
            if (getApproximatedMyPlace() > this.gift.gifts_per_round) goto L15;
            return BidStatus.WINNING;
        L15:
            return BidStatus.OUTBID;
        }

        public long getCurrentMyBid() {
            return this.auctionUserState.bid_amount;
        }

        public long getCurrentTopBid() {
            TL_stars.TL_starGiftAuctionState r0 = this.auctionStateActive;
            if (r0 == null) goto L10;
            ArrayList<TL_stars.TL_AuctionBidLevel> r02 = r0.bid_levels;
            if (r02 != null) goto L7;
            return 0;
        L7:
            if (r02.isEmpty() == false) goto L9;
            return 0;
        L9:
            return this.auctionStateActive.bid_levels.get(0).amount;
        L10:
            return 0;
        }

        public long getMaximumBid() {
            return Math.max(50000, (getCurrentTopBid() * 3) / 2);
        }

        public long getMinimumBid() {
            TL_stars.TL_starGiftAuctionState r0 = this.auctionStateActive;
            if (r0 == null) goto L8;
            long r3 = this.auctionUserState.min_bid_amount;
            if (r3 <= 0) goto L8;
            return Math.max(r0.min_bid_amount, r3);
        L8:
            long r32 = this.auctionUserState.min_bid_amount;
            if (r32 <= 0) goto L11;
            return r32;
        L11:
            if (r0 != null) goto L13;
            return 0;
        L13:
            return r0.min_bid_amount;
        }

        public boolean isFinished() {
            if (this.auctionStateFinished == null) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isUpcoming() {
            return isUpcoming(ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime());
        }

        public boolean isUpcoming(int r1) {
            if (this.gift.auction_start_date <= r1) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* loaded from: classes5.dex */
    public static class AuctionInternal {
        private ArrayList<TL_stars.TL_StarGiftAuctionAcquiredGift> acquiredGifts;
        public final long giftId;
        private Auction internalState;
        private boolean pendingBid;
        private ArrayList<TL_stars.StarGiftAttribute> previewAttributes;
        private Runnable resubscribe;
        private boolean subscription;

        /* renamed from: -$$Nest$fgetacquiredGifts, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m4099$$Nest$fgetacquiredGifts(AuctionInternal r0) {
            return r0.acquiredGifts;
        }

        /* renamed from: -$$Nest$fgetinternalState, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Auction m4100$$Nest$fgetinternalState(AuctionInternal r0) {
            return r0.internalState;
        }

        /* renamed from: -$$Nest$fgetpendingBid, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4101$$Nest$fgetpendingBid(AuctionInternal r0) {
            return r0.pendingBid;
        }

        /* renamed from: -$$Nest$fgetpreviewAttributes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m4102$$Nest$fgetpreviewAttributes(AuctionInternal r0) {
            return r0.previewAttributes;
        }

        /* renamed from: -$$Nest$fgetresubscribe, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m4103$$Nest$fgetresubscribe(AuctionInternal r0) {
            return r0.resubscribe;
        }

        /* renamed from: -$$Nest$fgetsubscription, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4104$$Nest$fgetsubscription(AuctionInternal r0) {
            return r0.subscription;
        }

        /* renamed from: -$$Nest$fputacquiredGifts, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4105$$Nest$fputacquiredGifts(AuctionInternal r0, ArrayList r1) {
            r0.acquiredGifts = r1;
        }

        /* renamed from: -$$Nest$fputinternalState, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4106$$Nest$fputinternalState(AuctionInternal r0, Auction r1) {
            r0.internalState = r1;
        }

        /* renamed from: -$$Nest$fputpendingBid, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4107$$Nest$fputpendingBid(AuctionInternal r0, boolean r1) {
            r0.pendingBid = r1;
        }

        /* renamed from: -$$Nest$fputpreviewAttributes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4108$$Nest$fputpreviewAttributes(AuctionInternal r0, ArrayList r1) {
            r0.previewAttributes = r1;
        }

        /* renamed from: -$$Nest$fputresubscribe, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4109$$Nest$fputresubscribe(AuctionInternal r0, Runnable r1) {
            r0.resubscribe = r1;
        }

        /* renamed from: -$$Nest$fputsubscription, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4110$$Nest$fputsubscription(AuctionInternal r0, boolean r1) {
            r0.subscription = r1;
        }

        private AuctionInternal(long r1) {
            this.giftId = r1;
        }

        public /* synthetic */ AuctionInternal(long r1, GiftAuctionControllerIA r3) {
            this(r1);
        }

        public int getVersion() {
            Auction r0 = this.internalState;
            if (r0 != null) goto L5;
            return 0;
        L5:
            return Auction.m4098$$Nest$mgetVersion(r0);
        }

        public boolean hasBid() {
            Auction r4 = this.internalState;
            if (r4 != null) goto L5;
            return false;
        L5:
            if (r4.auctionUserState.bid_amount <= 0) goto L10;
            return true;
        L10:
            return false;
        }
    }

    public interface OnActiveAuctionsUpdateListeners {
        void onActiveAuctionsUpdate(List<Auction> r1);
    }

    /* loaded from: classes5.dex */
    public interface OnAuctionUpdateListener {
        void onUpdate(Auction r1);
    }

    /* renamed from: $r8$lambda$1vVjSRwWFeBHyt626cWTWP-EaH4, reason: not valid java name */
    public static /* synthetic */ void m4091$r8$lambda$1vVjSRwWFeBHyt626cWTWPEaH4(GiftAuctionController r0, TLRPC.TL_payments_paymentResult r1) {
        r0.lambda$sendBid$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$2pOWcDgpd2k3Tk8iB4hI6ogb71Y(GiftAuctionController r0, long r1, TL_payments.TL_StarGiftAuctionState r3, TLRPC.TL_error r4) {
        r0.lambda$subscribeToGiftAuctionStateInternal$1(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$57B5lgs5bt_KT3ZK3IumFIz34Ig(GiftAuctionController r0, Utilities.Callback r1, AuctionInternal r2, TL_payments.TL_StarGiftAuctionAcquiredGifts r3, TLRPC.TL_error r4) {
        r0.lambda$getOrRequestAcquiredGifts$11(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$GcqdVSb_zZsauDhLJI-B6ClKCqE, reason: not valid java name */
    public static /* synthetic */ int m4092$r8$lambda$GcqdVSb_zZsauDhLJIB6ClKCqE(Auction r0) {
        return r0.auctionUserState.bid_date;
    }

    public static /* synthetic */ void $r8$lambda$NK112d8PHwrjqN34nq821uSXi5I(GiftAuctionController r0, AuctionInternal r1, long r2) {
        r0.lambda$onGiftAuctionStateReceivedInternal$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$S9ujSQRJ3bvbpq6TMQQgGf4_ne4(GiftAuctionController r0, TL_payments.TL_StarGiftAuctionState r1, Utilities.Callback2 r2, TLRPC.TL_error r3, ArrayList r4) {
        r0.lambda$requestGiftAuctionInternal$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$UvTc60NBLKyfkrPxAvHgx2Yibkk(GiftAuctionController r0, Utilities.Callback2 r1, TL_payments.TL_StarGiftAuctionState r2, TLRPC.TL_error r3) {
        r0.lambda$requestGiftAuctionInternal$4(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$dnq_zUJa2huaIKew7G6fac-GuTM, reason: not valid java name */
    public static /* synthetic */ void m4093$r8$lambda$dnq_zUJa2huaIKew7G6facGuTM(GiftAuctionController r0, TL_payments.StarGiftActiveAuctions r1, TLRPC.TL_error r2) {
        r0.lambda$requestUserAuctions$10(r1, r2);
    }

    /* renamed from: $r8$lambda$e-Payqhs1GaCU6p7bT6ZhmAlt9g, reason: not valid java name */
    public static /* synthetic */ void m4094$r8$lambda$ePayqhs1GaCU6p7bT6ZhmAlt9g(GiftAuctionController r0, AuctionInternal r1, Utilities.Callback2 r2, TLRPC.payments_PaymentResult r3, TLRPC.TL_error r4) {
        r0.lambda$sendBid$8(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$m0WTiozIdKGkc19Ify0S1ASljAE(Utilities.Callback r0, TL_stars.starGiftUpgradeAttributes r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        r0.run(r1.attributes);
        return;
    L5:
        r0.run(null);
    }

    public static /* synthetic */ void $r8$lambda$s7J2yXbHC5CTR2Xbwb2mq5qZYA4(GiftAuctionController r0, Utilities.Callback2 r1, long r2, AuctionBidSheet.Params r4, long r5) {
        r0.lambda$sendBid$6(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$wf99L_PWDnScf1dEP1pMauQrPBI(GiftAuctionController r0, Utilities.Callback2 r1, AuctionInternal r2, TLRPC.TL_payments_getPaymentForm r3, TLRPC.PaymentForm r4, TLRPC.TL_error r5) {
        r0.lambda$sendBid$9(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$yReGjVA4j1TyXMyDRd9DvOg38us(GiftAuctionController r0, Utilities.Callback2 r1, long r2, TL_payments.TL_StarGiftAuctionState r4, TLRPC.TL_error r5) {
        r0.lambda$getOrRequestAuction$12(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$z0VZehep859EzyJgCG3137xF44k(GiftAuctionController r0, long r1, TL_payments.TL_StarGiftAuctionState r3, ArrayList r4) {
        r0.lambda$subscribeToGiftAuctionStateInternal$0(r1, r3, r4);
    }

    static {
        Instance = new GiftAuctionController[16];
    }

    private GiftAuctionController(int r2) {
        super(r2);
        this.listeners = new ReferenceMap(false);
        this.auctions = new LongSparseArray();
        this.activeAuctions = new ArrayList();
        this.upgrades = new LongSparseArray();
        this.onActiveAuctionsUpdateListeners = new ReferenceList(false);
    }

    private void applyGiftAuctionStateAndPerformUpdate(TL_stars.StarGift r9, TL_stars.StarGiftAuctionState r10, TL_stars.TL_StarGiftAuctionUserState r11) {
        AuctionInternal r0 = getOrCreateAuction(r9.id);
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r0) != null) goto L5;
        TL_stars.StarGift r4 = r9;
        AuctionInternal.m4106$$Nest$fputinternalState(r0, new Auction(this.currentAccount, r4, r10, r11, null));
        Auction r92 = AuctionInternal.m4100$$Nest$fgetinternalState(r0);
        r92.previewAttributes = AuctionInternal.m4102$$Nest$fgetpreviewAttributes(r0);
        boolean r93 = true;
    L6:
        if (r93 == false) goto L9;
        updateActiveAuctions();
        performAuctionUpdate(r4.id);
        return;
    L9:
        return;
    L5:
        r4 = r9;
        r93 = (Auction.m4096$$Nest$mapplyGift(AuctionInternal.m4100$$Nest$fgetinternalState(r0), r4) | Auction.m4095$$Nest$mapplyAuctionState(AuctionInternal.m4100$$Nest$fgetinternalState(r0), r10)) | Auction.m4097$$Nest$mapplyUserState(AuctionInternal.m4100$$Nest$fgetinternalState(r0), r11);
        goto L6
    }

    private long calculateUserAuctionsHash() {
        ArrayList r0 = new ArrayList();
        int r1 = this.auctions.size();
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r3 >= r1) goto L16;
        AuctionInternal r5 = this.auctions.valueAt(r3);
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r5) == null) goto L15;
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r5).isFinished() == true) goto L15;
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r5).auctionStateActive == null) goto L15;
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r5).auctionUserState.bid_date <= 0) goto L15;
        r0.add(Long.valueOf(AuctionInternal.m4100$$Nest$fgetinternalState(r5).auctionStateActive.version | (AuctionInternal.m4100$$Nest$fgetinternalState(r5).auctionUserState.bid_date << 32)));
    L15:
        r3 = r3 + 1;
        goto L4
    L16:
        Collections.sort(r0);
        int r11 = r0.size();
        long r52 = 0;
    L17:
        if (r2 >= r11) goto L19;
        Object r12 = r0.get(r2);
        r2 = r2 + 1;
        Long r13 = (Long) r12;
        r52 = MediaDataController.calcHash(MediaDataController.calcHash(r52, r13.longValue() & 4294967295L), r13.longValue() >> 32);
        goto L17
    L19:
        return r52;
    }

    public static ArrayList<TL_stars.StarGiftAttribute> filterAttributes(ArrayList<TL_stars.StarGiftAttribute> r6, boolean r7) {
        ArrayList<TL_stars.StarGiftAttribute> r0 = new ArrayList();
        int r1 = r6.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L14;
        TL_stars.StarGiftAttribute r4 = r6.get(r3);
        r3 = r3 + 1;
        TL_stars.StarGiftAttribute r42 = r4;
        if ((r42.rarity instanceof TL_stars.TL_starGiftAttributeRarity) == false) goto L11;
        if (r7 == true) goto L8;
    L10:
        boolean r5 = false;
    L12:
        if (r5 == true) goto L3;
        r0.add(r42);
        goto L3
    L8:
        if ((r42 instanceof TL_stars.starGiftAttributeModel) == false) goto L10;
        r5 = true;
        goto L12
    L11:
        r5 = !r7;
        goto L12
    L14:
        return r0;
    }

    private Auction findAuctionBySlug(String r6) {
        int r0 = this.auctions.size();
        int r1 = 0;
    L4:
        if (r1 >= r0) goto L15;
        AuctionInternal r3 = this.auctions.valueAt(r1);
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r3) == null) goto L14;
        if (TextUtils.equals(AuctionInternal.m4100$$Nest$fgetinternalState(r3).gift.auction_slug, r6) == false) goto L14;
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r3) != null) goto L12;
        return null;
    L12:
        return AuctionInternal.m4100$$Nest$fgetinternalState(r3);
    L14:
        r1 = r1 + 1;
        goto L4
    L15:
        return null;
    }

    public static GiftAuctionController getInstance(int r3) {
        GiftAuctionController r0 = Instance[r3];
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(GiftAuctionController.class);
        GiftAuctionController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        GiftAuctionController[] r03 = Instance;     // Catch: Throwable -> L9
        GiftAuctionController r2 = new GiftAuctionController(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(GiftAuctionController.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    private AuctionInternal getOrCreateAuction(long r3) {
        AuctionInternal r0 = this.auctions.get(r3);
        if (r0 != null) goto L6;
        AuctionInternal r02 = new AuctionInternal(r3, null);
        this.auctions.put(r3, r02);
        return r02;
    L6:
        return r0;
    }

    public static boolean hasAllAttributes(ArrayList<TL_stars.StarGiftAttribute> r1) {
        if (StarsController.findAttribute(r1, TL_stars.starGiftAttributeModel.class) != null) goto L5;
        return false;
    L5:
        if (StarsController.findAttribute(r1, TL_stars.starGiftAttributePattern.class) != null) goto L7;
        return false;
    L7:
        if (StarsController.findAttribute(r1, TL_stars.starGiftAttributeBackdrop.class) == null) goto L13;
        return true;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$getOrRequestAcquiredGifts$11(Utilities.Callback r3, AuctionInternal r4, TL_payments.TL_StarGiftAuctionAcquiredGifts r5, TLRPC.TL_error r6) {
        if (r5 != null) goto L5;
        r3.run(null);
        return;
    L5:
        getMessagesController().putUsers(r5.users, false);
        getMessagesController().putChats(r5.chats, false);
        AuctionInternal.m4105$$Nest$fputacquiredGifts(r4, r5.gifts);
        r3.run(AuctionInternal.m4099$$Nest$fgetacquiredGifts(r4));
    }

    private /* synthetic */ void lambda$getOrRequestAuction$12(Utilities.Callback2 r1, long r2, TL_payments.TL_StarGiftAuctionState r4, TLRPC.TL_error r5) {
        r1.run(getAuction(r2), r5);
    }

    private /* synthetic */ void lambda$onGiftAuctionStateReceivedInternal$2(AuctionInternal r2, long r3) {
        AuctionInternal.m4109$$Nest$fputresubscribe(r2, null);
        subscribeToGiftAuctionStateInternal(r3);
    }

    private /* synthetic */ void lambda$requestGiftAuctionInternal$3(TL_payments.TL_StarGiftAuctionState r3, Utilities.Callback2 r4, TLRPC.TL_error r5, ArrayList r6) {
        AuctionInternal.m4108$$Nest$fputpreviewAttributes(getOrCreateAuction(r3.gift.id), r6);
        onGiftAuctionStateReceivedInternal(r3.gift.id, r3);
        r4.run(r3, r5);
    }

    private /* synthetic */ void lambda$requestGiftAuctionInternal$4(Utilities.Callback2 r5, TL_payments.TL_StarGiftAuctionState r6, TLRPC.TL_error r7) {
        if (r6 == null) goto L4;
        getMessagesController().putUsers(r6.users, false);
        getMessagesController().putChats(r6.chats, false);
    L4:
        if (r6 != null) goto L6;
    L9:
        if (r6 == null) goto L11;
        onGiftAuctionStateReceivedInternal(r6.gift.id, r6);
    L11:
        r5.run(r6, r7);
        return;
    L6:
        if (this.upgrades.get(r6.gift.id, Boolean.FALSE).booleanValue() == true) goto L9;
        this.upgrades.put(r6.gift.id, Boolean.TRUE);
        requestAuctionUpgrades(r6.gift.id, new GiftAuctionController$$ExternalSyntheticLambda1(this, r6, r5, r7));
    }

    private /* synthetic */ void lambda$requestUserAuctions$10(TL_payments.StarGiftActiveAuctions r5, TLRPC.TL_error r6) {
        if (r5 == null) goto L10;
        if (r6 == null) goto L6;
        return;
    L6:
        if ((r5 instanceof TL_payments.TL_starGiftActiveAuctions) == false) goto L12;
        TL_payments.TL_starGiftActiveAuctions r52 = (TL_payments.TL_starGiftActiveAuctions) r5;
        int r1 = 0;
        getMessagesController().putUsers(r52.users, false);
        getMessagesController().putChats(r52.chats, false);
        ArrayList<TL_stars.TL_StarGiftActiveAuctionState> r53 = r52.auctions;
        int r62 = r53.size();
    L8:
        if (r1 >= r62) goto L14;
        TL_stars.TL_StarGiftActiveAuctionState r0 = r53.get(r1);
        r1 = r1 + 1;
        TL_stars.TL_StarGiftActiveAuctionState r02 = r0;
        applyGiftAuctionStateAndPerformUpdate(r02.gift, r02.state, r02.user_state);
        goto L8
    L14:
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$sendBid$6(Utilities.Callback2 r8, long r9, AuctionBidSheet.Params r11, long r12) {
        if (StarsController.getInstance(this.currentAccount).balanceAvailable() == true) goto L7;
        if (r8 == null) goto L9;
        r8.run(Boolean.FALSE, "NO_BALANCE");
        return;
    L9:
        return;
    L7:
        sendBid(r9, r11, r12, r8);
    }

    private /* synthetic */ void lambda$sendBid$7(TLRPC.TL_payments_paymentResult r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates(r2.updates, false);
    }

    private /* synthetic */ void lambda$sendBid$8(AuctionInternal r2, Utilities.Callback2 r3, TLRPC.payments_PaymentResult r4, TLRPC.TL_error r5) {
        AuctionInternal.m4107$$Nest$fputpendingBid(r2, false);
        if ((r4 instanceof TLRPC.TL_payments_paymentResult) == false) goto L6;
        Utilities.stageQueue.postRunnable(new GiftAuctionController$$ExternalSyntheticLambda3(this, (TLRPC.TL_payments_paymentResult) r4));
        r3.run(Boolean.TRUE, null);
        return;
    L6:
        if (r5 == null) goto L9;
        r3.run(Boolean.FALSE, r5.text);
        return;
    L9:
        r3.run(Boolean.FALSE, null);
    }

    private /* synthetic */ void lambda$sendBid$9(Utilities.Callback2 r3, AuctionInternal r4, TLRPC.TL_payments_getPaymentForm r5, TLRPC.PaymentForm r6, TLRPC.TL_error r7) {
        if (r7 == null) goto L7;
        r3.run(Boolean.FALSE, r7.text);
        AuctionInternal.m4107$$Nest$fputpendingBid(r4, false);
        return;
    L7:
        if ((r6 instanceof TLRPC.TL_payments_paymentFormStarGift) == true) goto L10;
        r3.run(Boolean.FALSE, "NO_PAYMENT_FORM");
        AuctionInternal.m4107$$Nest$fputpendingBid(r4, false);
        return;
    L10:
        TL_stars.TL_payments_sendStarsForm r72 = new TL_stars.TL_payments_sendStarsForm();
        r72.form_id = ((TLRPC.TL_payments_paymentFormStarGift) r6).form_id;
        r72.invoice = r5.invoice;
        getConnectionsManager().sendRequestTyped(r72, new AiTonesController$$ExternalSyntheticLambda0(), new GiftAuctionController$$ExternalSyntheticLambda0(this, r4, r3));
    }

    private /* synthetic */ void lambda$subscribeToGiftAuctionStateInternal$0(long r2, TL_payments.TL_StarGiftAuctionState r4, ArrayList r5) {
        AuctionInternal.m4108$$Nest$fputpreviewAttributes(getOrCreateAuction(r2), r5);
        onGiftAuctionStateReceivedInternal(r2, r4);
    }

    private /* synthetic */ void lambda$subscribeToGiftAuctionStateInternal$1(long r3, TL_payments.TL_StarGiftAuctionState r5, TLRPC.TL_error r6) {
        if (r5 == null) goto L4;
        getMessagesController().putUsers(r5.users, false);
        getMessagesController().putChats(r5.chats, false);
    L4:
        if (r5 != null) goto L6;
    L9:
        if (r5 == null) goto L12;
        onGiftAuctionStateReceivedInternal(r3, r5);
        return;
    L12:
        return;
    L6:
        if (this.upgrades.get(r3, Boolean.FALSE).booleanValue() == true) goto L9;
        this.upgrades.put(r3, Boolean.TRUE);
        requestAuctionUpgrades(r3, new GiftAuctionController$$ExternalSyntheticLambda5(this, r3, r5));
    }

    private void onGiftAuctionStateReceivedInternal(long r4, TL_payments.TL_StarGiftAuctionState r6) {
        applyGiftAuctionStateAndPerformUpdate(r6.gift, r6.state, r6.user_state);
        AuctionInternal r0 = this.auctions.get(r4);
        if (r0 != null) goto L5;
        return;
    L5:
        if (AuctionInternal.m4104$$Nest$fgetsubscription(r0) == false) goto L9;
        AuctionInternal.m4109$$Nest$fputresubscribe(r0, new GiftAuctionController$$ExternalSyntheticLambda2(this, r0, r4));
        AndroidUtilities.runOnUIThread(AuctionInternal.m4103$$Nest$fgetresubscribe(r0), r6.timeout * 1000);
        return;
    }

    private void performAuctionUpdate(long r2) {
        Auction r0 = getAuction(r2);
        Iterator<OnAuctionUpdateListener> r1 = this.listeners.iterator(Long.valueOf(r2));
        if (r0 == null) goto L9;
        if (r1 != null) goto L7;
        return;
    L7:
        if (r1.hasNext() == false) goto L12;
        r1.next().onUpdate(r0);
        goto L7
    L12:
        return;
    }

    private void performUpdateActiveAuctions() {
        Iterator<OnActiveAuctionsUpdateListeners> r0 = this.onActiveAuctionsUpdateListeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onActiveAuctionsUpdate(this.activeAuctions);
        goto L4
    L6:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.activeAuctionsUpdated, new Object[0]);
    }

    private int requestGiftAuctionInternal(TL_stars.InputStarGiftAuction r4, Utilities.Callback2<TL_payments.TL_StarGiftAuctionState, TLRPC.TL_error> r5) {
        TL_payments.TL_getStarGiftAuctionState r0 = new TL_payments.TL_getStarGiftAuctionState();
        r0.auction = r4;
        r0.version = 0;
        return getConnectionsManager().sendRequestTyped(r0, new AiTonesController$$ExternalSyntheticLambda0(), new GiftAuctionController$$ExternalSyntheticLambda7(this, r5));
    }

    private void subscribeToGiftAuctionStateInternal(long r6) {
        AuctionInternal r0 = getOrCreateAuction(r6);
        AuctionInternal.m4110$$Nest$fputsubscription(r0, true);
        if (AuctionInternal.m4103$$Nest$fgetresubscribe(r0) == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(AuctionInternal.m4103$$Nest$fgetresubscribe(r0));
        AuctionInternal.m4109$$Nest$fputresubscribe(r0, null);
    L5:
        TL_payments.TL_getStarGiftAuctionState r1 = new TL_payments.TL_getStarGiftAuctionState();
        TL_stars.TL_inputStarGiftAuction r2 = new TL_stars.TL_inputStarGiftAuction();
        r2.gift_id = r0.giftId;
        r1.auction = r2;
        r1.version = r0.getVersion();
        getConnectionsManager().sendRequestTyped(r1, new AiTonesController$$ExternalSyntheticLambda0(), new GiftAuctionController$$ExternalSyntheticLambda8(this, r6));
    }

    private void updateActiveAuctions() {
        getMessagesController().putLastGiftAuctionUpdate();
        this.activeAuctions.clear();
        int r0 = this.auctions.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L13;
        AuctionInternal r2 = this.auctions.valueAt(r1);
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r2) == null) goto L12;
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r2).isFinished() == true) goto L12;
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r2).auctionUserState.bid_amount <= 0) goto L12;
        this.activeAuctions.add(AuctionInternal.m4100$$Nest$fgetinternalState(r2));
    L12:
        r1 = r1 + 1;
        goto L3
    L13:
        this.activeAuctions.sort(Comparator.comparingInt(new GiftAuctionController$$ExternalSyntheticLambda4()));
        performUpdateActiveAuctions();
    }

    public ArrayList<Auction> getActiveAuctions() {
        return this.activeAuctions;
    }

    public Auction getAuction(long r1) {
        AuctionInternal r0 = this.auctions.get(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return AuctionInternal.m4100$$Nest$fgetinternalState(r0);
    }

    public void getOrRequestAcquiredGifts(long r4, Utilities.Callback<List<TL_stars.TL_StarGiftAuctionAcquiredGift>> r6) {
        AuctionInternal r0 = this.auctions.get(r4);
        if (r0 != null) goto L5;
    L16:
        r6.run(null);
        return;
    L5:
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r0) == null) goto L16;
        if (AuctionInternal.m4099$$Nest$fgetacquiredGifts(r0) != null) goto L10;
    L14:
        TL_payments.TL_getStarGiftAuctionAcquiredGifts r1 = new TL_payments.TL_getStarGiftAuctionAcquiredGifts();
        r1.gift_id = r4;
        getConnectionsManager().sendRequestTyped(r1, new AiTonesController$$ExternalSyntheticLambda0(), new GiftAuctionController$$ExternalSyntheticLambda9(this, r6, r0));
        return;
    L10:
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r0).auctionUserState.acquired_count != AuctionInternal.m4099$$Nest$fgetacquiredGifts(r0).size()) goto L14;
        r6.run(AuctionInternal.m4099$$Nest$fgetacquiredGifts(r0));
    }

    public void getOrRequestAuction(long r2, Utilities.Callback2<Auction, TLRPC.TL_error> r4) {
        Auction r0 = getAuction(r2);
        if (r0 == null) goto L6;
        r4.run(r0, null);
        return;
    L6:
        requestGiftAuctionById(r2, new GiftAuctionController$$ExternalSyntheticLambda10(this, r4, r2));
    }

    public boolean hasActiveAuctions() {
        if (this.wasRequestedActiveAuctions == true) goto L8;
        if (getMessagesController().giftAuctionUpdateWasRecently() == false) goto L8;
        this.wasRequestedActiveAuctions = true;
        requestUserAuctions();
    L8:
        return !this.activeAuctions.isEmpty();
    }

    public void processUpdate(TL_update.TL_updateStarGiftAuctionState r4) {
        AuctionInternal r0 = this.auctions.get(r4.gift_id);
        if (r0 != null) goto L5;
        return;
    L5:
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r0) != null) goto L8;
        return;
    L8:
        if (Auction.m4095$$Nest$mapplyAuctionState(AuctionInternal.m4100$$Nest$fgetinternalState(r0), r4.state) == false) goto L12;
        updateActiveAuctions();
        performAuctionUpdate(r0.giftId);
        return;
    }

    public void processUpdate(TL_update.TL_updateStarGiftAuctionUserState r4) {
        AuctionInternal r0 = this.auctions.get(r4.gift_id);
        if (r0 != null) goto L5;
        return;
    L5:
        if (AuctionInternal.m4100$$Nest$fgetinternalState(r0) != null) goto L8;
        return;
    L8:
        if (Auction.m4097$$Nest$mapplyUserState(AuctionInternal.m4100$$Nest$fgetinternalState(r0), r4.user_state) == false) goto L12;
        updateActiveAuctions();
        performAuctionUpdate(r0.giftId);
        return;
    }

    public void requestAuctionUpgrades(long r2, Utilities.Callback<ArrayList<TL_stars.StarGiftAttribute>> r4) {
        TL_stars.getStarGiftUpgradeAttributes r0 = new TL_stars.getStarGiftUpgradeAttributes();
        r0.gift_id = r2;
        getConnectionsManager().sendRequestTyped(r0, new AiTonesController$$ExternalSyntheticLambda0(), new GiftAuctionController$$ExternalSyntheticLambda6(r4));
    }

    public int requestGiftAuctionById(long r2, Utilities.Callback2<TL_payments.TL_StarGiftAuctionState, TLRPC.TL_error> r4) {
        TL_stars.TL_inputStarGiftAuction r0 = new TL_stars.TL_inputStarGiftAuction();
        r0.gift_id = r2;
        return requestGiftAuctionInternal(r0, r4);
    }

    public int requestGiftAuctionBySlug(String r2, Utilities.Callback2<TL_payments.TL_StarGiftAuctionState, TLRPC.TL_error> r3) {
        TL_stars.TL_inputStarGiftAuctionSlug r0 = new TL_stars.TL_inputStarGiftAuctionSlug();
        r0.slug = r2;
        return requestGiftAuctionInternal(r0, r3);
    }

    public void requestUserAuctions() {
        TL_payments.TL_getStarGiftActiveAuctions r0 = new TL_payments.TL_getStarGiftActiveAuctions();
        r0.hash = calculateUserAuctionsHash();
        getConnectionsManager().sendRequestTyped(r0, new AiTonesController$$ExternalSyntheticLambda0(), new GiftAuctionController$$ExternalSyntheticLambda13(this));
    }

    public void sendBid(long r10, AuctionBidSheet.Params r12, long r13, Utilities.Callback2<Boolean, String> r15) {
        AuctionInternal r0 = this.auctions.get(r10);
        if (r0 == null) goto L22;
        if (AuctionInternal.m4101$$Nest$fgetpendingBid(r0) == true) goto L22;
        if (StarsController.getInstance(this.currentAccount).balanceAvailable() == true) goto L11;
        StarsController.getInstance(this.currentAccount).getBalance(new GiftAuctionController$$ExternalSyntheticLambda11(this, r15, r10, r12, r13));
        return;
    L11:
        boolean r9 = r0.hasBid();
        AuctionInternal.m4107$$Nest$fputpendingBid(r0, true);
        TLRPC.TL_payments_getPaymentForm r102 = new TLRPC.TL_payments_getPaymentForm();
        TLRPC.TL_inputInvoiceStarGiftAuctionBid r11 = new TLRPC.TL_inputInvoiceStarGiftAuctionBid();
        r11.gift_id = r10;
        r11.bid_amount = r13;
        r11.update_bid = r9;
        if (r12 != null) goto L14;
        if (r9 == true) goto L20;
        r11.peer = new TLRPC.TL_inputPeerSelf();
        r11.hide_name = false;
    L20:
        r102.invoice = r11;
        getConnectionsManager().sendRequestTyped(r102, new AiTonesController$$ExternalSyntheticLambda0(), new GiftAuctionController$$ExternalSyntheticLambda12(this, r15, r0, r102));
        return;
    L14:
        if (r12.dialogId != 0) goto L16;
        r11.peer = new TLRPC.TL_inputPeerSelf();
    L17:
        r11.message = r12.message;
        r11.hide_name = r12.hideName;
        goto L20
    L16:
        r11.peer = getMessagesController().getInputPeer(r12.dialogId);
    L22:
        r15.run(Boolean.FALSE, null);
    }

    public void subscribeToActiveAuctionsUpdates(OnActiveAuctionsUpdateListeners r1) {
        this.onActiveAuctionsUpdateListeners.add(r1);
    }

    public Auction subscribeToGiftAuction(long r3, OnAuctionUpdateListener r5) {
        this.listeners.add(Long.valueOf(r3), r5);
        subscribeToGiftAuctionStateInternal(r3);
        return getAuction(r3);
    }

    public void unsubscribeFromActiveAuctionsUpdates(OnActiveAuctionsUpdateListeners r1) {
        this.onActiveAuctionsUpdateListeners.remove(r1);
    }

    public void unsubscribeFromGiftAuction(long r3, OnAuctionUpdateListener r5) {
        this.listeners.remove(Long.valueOf(r3), r5);
        AuctionInternal r52 = this.auctions.get(r3);
        if (r52 == null) goto L10;
        AuctionInternal.m4110$$Nest$fputsubscription(r52, this.listeners.has(Long.valueOf(r3)));
        if (AuctionInternal.m4103$$Nest$fgetresubscribe(r52) == null) goto L9;
        AndroidUtilities.cancelRunOnUIThread(AuctionInternal.m4103$$Nest$fgetresubscribe(r52));
        AuctionInternal.m4109$$Nest$fputresubscribe(r52, null);
        return;
    L9:
        return;
    }
}
